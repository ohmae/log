/*
 * Copyright (c) 2019 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.log;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static net.mm2d.log.Senders.*;

/**
 * Default implementation of {@link Sender}.
 *
 * @author <a href="mailto:ryo@mm2d.net">大前良介 (OHMAE Ryosuke)</a>
 */
public class DefaultSender implements Sender {
    private static final int MAX_TAG_LENGTH = 23;
    private static boolean sAppendCaller;
    private static boolean sAppendThread;

    /**
     * Set whether to append the caller's code position to the log.
     *
     * <p>If set true, append the caller's code position to the log.
     * This will act as a link to the source code of caller on the IntelliJ console.
     *
     * <p>Default value is false
     *
     * @param append if true, enabled.
     */
    public static void appendCaller(final boolean append) {
        sAppendCaller = append;
    }

    /**
     * Set whether to append caller's thread information to the log.
     *
     * <p>Default value is false
     *
     * @param append if true, enabled.
     */
    public static void appendThread(final boolean append) {
        sAppendThread = append;
    }

    /**
     * Delegate the log output method.
     */
    public interface Printer {
        /**
         * Output log.
         *
         * @param level   Log level
         * @param tag     TAG
         * @param message Log message
         */
        void print(
                final int level,
                @Nonnull final String tag,
                @Nonnull final String message);
    }

    @Nonnull
    private final Printer mPrinter;

    /**
     * Initialize DefaultSender with printer.
     *
     * @param printer Printer
     */
    public DefaultSender(@Nonnull final Printer printer) {
        mPrinter = printer;
    }

    @Override
    public void send(
            final int level,
            @Nonnull final String message,
            @Nullable final Throwable throwable) {
        final StackTraceElement[] trace = new Throwable().getStackTrace();
        // Sender#send -> Logger#send -> Logger#v/d/i/w/e -> ログコール場所
        final StackTraceElement element = trace.length < 4 ? null : trace[3];
        final String tag = makeTag(element);
        if (!sAppendCaller || element == null) {
            send(level, tag, makeMessage(message, throwable));
            return;
        }
        send(level, tag, element.toString() + " : " + makeMessage(message, throwable));
    }

    private void send(
            final int level,
            @Nonnull final String tag,
            @Nonnull final String message) {
        if (sAppendThread) {
            mPrinter.print(level, tag, makeThreadInfo() + message);
            return;
        }
        mPrinter.print(level, tag, message);
    }

    @Nonnull
    private static String makeTag(@Nullable final StackTraceElement element) {
        if (element == null) {
            return "tag";
        }
        final String className = extractSimpleClassName(element);
        if (className.length() > MAX_TAG_LENGTH) {
            return className.substring(0, MAX_TAG_LENGTH);
        }
        return className;
    }

    @Nonnull
    private static String extractSimpleClassName(@Nonnull final StackTraceElement element) {
        String className = element.getClassName();
        final int dot = className.lastIndexOf('.');
        if (dot >= 0) {
            className = className.substring(dot + 1);
        }
        final int dollar = className.indexOf('$');
        if (dollar >= 0) {
            return className.substring(0, dollar);
        }
        return className;
    }
}

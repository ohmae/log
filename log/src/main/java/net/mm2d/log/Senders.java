/*
 * Copyright (c) 2019 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static net.mm2d.log.Logger.*;

/**
 * Provide sender factory method and control method.
 *
 * @author [大前良介 (OHMAE Ryosuke)](mailto:ryo@mm2d.net)
 */
public final class Senders {
    /**
     * Set whether to append the caller's code position to the log.
     *
     * <p>If set true, append the caller's code position to the log.
     * This will act as a link to the source code of caller on the IntelliJ console.
     *
     * <p>Default value is false
     *
     * @param append if true, enabled.
     * @see DefaultSender
     */
    public static void appendCaller(final boolean append) {
        DefaultSender.appendCaller(append);
    }

    /**
     * Set whether to append caller's thread information to the log.
     *
     * <p>Default value is false
     *
     * @param append if true, enabled.
     * @see DefaultSender
     */
    public static void appendThread(final boolean append) {
        DefaultSender.appendThread(append);
    }

    /**
     * Default {@link Sender} that using System.out.println
     *
     * @return Sender
     */
    public static Sender create() {
        return new DefaultSender(new DefaultPrinter());
    }

    private static class DefaultPrinter implements DefaultSender.Printer {
        private static final ThreadLocal<DateFormat> FORMAT = new ThreadLocal<DateFormat>() {
            @Override
            protected DateFormat initialValue() {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
            }
        };

        @Nonnull
        private String getDateString() {
            return FORMAT.get().format(new Date(System.currentTimeMillis()));
        }

        @Override
        public void print(int level, @Nonnull String tag, @Nonnull String message) {
            final String[] lines = message.split("\n");
            final String prefix = getDateString()
                    + " " + levelToString(level)
                    + " [" + tag + "] ";
            for (final String line : lines) {
                System.out.println(prefix + line);
            }
        }
    }

    /**
     * Utility method for Sender.
     *
     * @return Thread info string.
     */
    @Nonnull
    public static String makeThreadInfo() {
        final Thread thread = Thread.currentThread();
        final int priority = thread.getPriority();
        final ThreadGroup group = thread.getThreadGroup();
        if (group == null) {
            return "[" + thread.getName() + "," + priority + "] ";
        }
        return "[" + thread.getName() + "," + priority + "," + group.getName() + "] ";
    }

    /**
     * Utility method for Sender.
     *
     * @param message Log message
     * @param tr      Throwable
     * @return message + stacktrace
     */
    @Nonnull
    public static String makeMessage(
            @Nonnull final String message,
            @Nullable final Throwable tr) {
        if (message.isEmpty()) {
            if (tr == null) {
                return "";
            }
            return makeStackTraceString(tr);
        }
        if (tr == null) {
            return message;
        }
        return message + "\n" + makeStackTraceString(tr);
    }

    /**
     * Utility method for Sender.
     *
     * @param tr throwable
     * @return Stacktrace string
     */
    @Nonnull
    public static String makeStackTraceString(@Nonnull final Throwable tr) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }

    /**
     * Utility method for Sender.
     *
     * @param level Log level
     * @return Log level string
     */
    @Nonnull
    public static String levelToString(final int level) {
        switch (level) {
            case VERBOSE:
                return "V";
            case DEBUG:
                return "D";
            case INFO:
                return "I";
            case WARN:
                return "W";
            case ERROR:
                return "E";
            default:
                return " ";
        }
    }
}

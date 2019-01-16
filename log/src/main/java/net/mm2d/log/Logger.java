/*
 * Copyright (c) 2019 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.log;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Logger interface.
 *
 * @author <a href="mailto:ryo@mm2d.net">大前良介 (OHMAE Ryosuke)</a>
 */
public final class Logger {
    /**
     * Log level VERBOSE
     */
    @SuppressWarnings("WeakerAccess")
    public static final int VERBOSE = 2;
    /**
     * Log level DEBUG
     */
    @SuppressWarnings("WeakerAccess")
    public static final int DEBUG = 3;
    /**
     * Log level INFO
     */
    @SuppressWarnings("WeakerAccess")
    public static final int INFO = 4;
    /**
     * Log level WARN
     */
    @SuppressWarnings("WeakerAccess")
    public static final int WARN = 5;
    /**
     * Log level ERROR
     */
    @SuppressWarnings("WeakerAccess")
    public static final int ERROR = 6;
    /**
     * Log level ASSERT
     */
    @SuppressWarnings({"WeakerAccess", "unused"})
    public static final int ASSERT = 7;

    private static int sLogLevel = Integer.MAX_VALUE;
    @Nonnull
    private static final Sender EMPTY_SENDER = (level, message, throwable) -> {
    };
    @Nonnull
    private static Sender sSender = EMPTY_SENDER;

    /**
     * Set log Sender.
     *
     * @param sender Sender, If it is null, nothing is done
     * @see Sender
     */
    public static void setSender(@Nullable final Sender sender) {
        sSender = sender != null ? sender : EMPTY_SENDER;
    }

    /**
     * Set the log level.
     *
     * <p>Output a log that is equal to or larger than the set value.
     * Log levels are defined in ascending order
     * {@link #VERBOSE}, {@link #DEBUG}, {@link #INFO}, {@link #WARN}, {@link #ERROR}, {@link #ASSERT},
     * If set {@link #ERROR}, output log of {@link #ERROR} and {@link #ASSERT} level.
     *
     * <p>Default value is {@link Integer:MAX_VALUE}, This means that nothing to output.
     *
     * @param level log level
     * @see #VERBOSE
     * @see #DEBUG
     * @see #INFO
     * @see #WARN
     * @see #ERROR
     * @see #ASSERT
     */
    public static void setLogLevel(final int level) {
        sLogLevel = level;
    }

    private static void send(
            final int level,
            @Nonnull final String message,
            @Nullable final Throwable throwable) {
        if (level < sLogLevel) {
            return;
        }
        sSender.send(level, message, throwable);
    }

    private static void send(
            final int level,
            @Nonnull final MessageSupplier supplier,
            @Nullable final Throwable throwable) {
        if (level < sLogLevel) {
            return;
        }
        sSender.send(level, supplier.get(), throwable);
    }

    /**
     * Send log at {@link Logger#VERBOSE} level
     *
     * @param message log message
     */
    public static void v(@Nonnull final String message) {
        send(VERBOSE, message, null);
    }

    /**
     * Send log at {@link Logger#VERBOSE} level
     *
     * @param supplier log message supplier
     */
    public static void v(@Nonnull final MessageSupplier supplier) {
        send(VERBOSE, supplier, null);
    }

    /**
     * Send log at {@link Logger#VERBOSE} level
     *
     * @param throwable Throwable
     */
    public static void v(@Nullable final Throwable throwable) {
        send(VERBOSE, "", throwable);
    }

    /**
     * Send log at {@link Logger#VERBOSE} level
     *
     * @param message   log message
     * @param throwable Throwable
     */
    public static void v(@Nonnull final String message, @Nullable final Throwable throwable) {
        send(VERBOSE, message, throwable);
    }

    /**
     * Send log at {@link Logger#VERBOSE} level
     *
     * @param supplier  log message supplier
     * @param throwable Throwable
     */
    public static void v(@Nonnull final MessageSupplier supplier, @Nullable final Throwable throwable) {
        send(VERBOSE, supplier, throwable);
    }

    /**
     * Send log at {@link Logger#DEBUG} level
     *
     * @param message log message
     */
    public static void d(@Nonnull final String message) {
        send(DEBUG, message, null);
    }

    /**
     * Send log at {@link Logger#DEBUG} level
     *
     * @param supplier log message supplier
     */
    public static void d(@Nonnull final MessageSupplier supplier) {
        send(DEBUG, supplier, null);
    }

    /**
     * Send log at {@link Logger#DEBUG} level
     *
     * @param throwable Throwable
     */
    public static void d(@Nullable final Throwable throwable) {
        send(DEBUG, "", throwable);
    }

    /**
     * Send log at {@link Logger#DEBUG} level
     *
     * @param message   log message
     * @param throwable Throwable
     */
    public static void d(@Nonnull final String message, @Nullable final Throwable throwable) {
        send(DEBUG, message, throwable);
    }

    /**
     * Send log at {@link Logger#DEBUG} level
     *
     * @param supplier  log message supplier
     * @param throwable Throwable
     */
    public static void d(@Nonnull final MessageSupplier supplier, @Nullable final Throwable throwable) {
        send(DEBUG, supplier, throwable);
    }

    /**
     * Send log at {@link Logger#INFO} level
     *
     * @param message log message
     */
    public static void i(@Nonnull final String message) {
        send(INFO, message, null);
    }

    /**
     * Send log at {@link Logger#INFO} level
     *
     * @param supplier log message supplier
     */
    public static void i(@Nonnull final MessageSupplier supplier) {
        send(INFO, supplier, null);
    }

    /**
     * Send log at {@link Logger#INFO} level
     *
     * @param throwable Throwable
     */
    public static void i(@Nullable final Throwable throwable) {
        send(INFO, "", throwable);
    }

    /**
     * Send log at {@link Logger#INFO} level
     *
     * @param message   log message
     * @param throwable Throwable
     */
    public static void i(@Nonnull final String message, @Nullable final Throwable throwable) {
        send(INFO, message, throwable);
    }

    /**
     * Send log at {@link Logger#INFO} level
     *
     * @param supplier  log message supplier
     * @param throwable Throwable
     */
    public static void i(@Nonnull final MessageSupplier supplier, @Nullable final Throwable throwable) {
        send(INFO, supplier, throwable);
    }

    /**
     * Send log at {@link Logger#WARN} level
     *
     * @param message log message
     */
    public static void w(@Nonnull final String message) {
        send(WARN, message, null);
    }

    /**
     * Send log at {@link Logger#WARN} level
     *
     * @param supplier log message supplier
     */
    public static void w(@Nonnull final MessageSupplier supplier) {
        send(WARN, supplier, null);
    }

    /**
     * Send log at {@link Logger#WARN} level
     *
     * @param throwable Throwable
     */
    public static void w(@Nullable final Throwable throwable) {
        send(WARN, "", throwable);
    }

    /**
     * Send log at {@link Logger#WARN} level
     *
     * @param message   log message
     * @param throwable Throwable
     */
    public static void w(@Nonnull final String message, @Nullable final Throwable throwable) {
        send(WARN, message, throwable);
    }

    /**
     * Send log at {@link Logger#WARN} level
     *
     * @param supplier  log message supplier
     * @param throwable Throwable
     */
    public static void w(@Nonnull final MessageSupplier supplier, @Nullable final Throwable throwable) {
        send(WARN, supplier, throwable);
    }

    /**
     * Send log at {@link Logger#ERROR} level
     *
     * @param message log message
     */
    public static void e(@Nonnull final String message) {
        send(ERROR, message, null);
    }

    /**
     * Send log at {@link Logger#ERROR} level
     *
     * @param supplier log message supplier
     */
    public static void e(@Nonnull final MessageSupplier supplier) {
        send(ERROR, supplier, null);
    }

    /**
     * Send log at {@link Logger#ERROR} level
     *
     * @param throwable Throwable
     */
    public static void e(@Nullable final Throwable throwable) {
        send(ERROR, "", throwable);
    }

    /**
     * Send log at {@link Logger#ERROR} level
     *
     * @param message   log message
     * @param throwable Throwable
     */
    public static void e(@Nonnull final String message, @Nullable final Throwable throwable) {
        send(ERROR, message, throwable);
    }

    /**
     * Send log at {@link Logger#ERROR} level
     *
     * @param supplier  log message supplier
     * @param throwable Throwable
     */
    public static void e(@Nonnull final MessageSupplier supplier, @Nullable final Throwable throwable) {
        send(ERROR, supplier, throwable);
    }
}

/*
 * Copyright (c) 2019 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.log

/**
 * Logger interface.
 *
 * @author [大前良介 (OHMAE Ryosuke)](mailto:ryo@mm2d.net)
 */
object Logger {
    /**
     * Log level VERBOSE
     */
    const val VERBOSE = 2
    /**
     * Log level DEBUG
     */
    const val DEBUG = 3
    /**
     * Log level INFO
     */
    const val INFO = 4
    /**
     * Log level WARN
     */
    const val WARN = 5
    /**
     * Log level ERROR
     */
    const val ERROR = 6
    /**
     * Log level ASSERT
     */
    const val ASSERT = 7

    private var logLevel = Integer.MAX_VALUE

    private var logSender: Sender? = null

    /**
     * Set log Sender.
     *
     * @param sender Sender, If it is null, nothing is done
     * @see Sender
     */
    @JvmStatic
    fun setSender(sender: Sender?) {
        logSender = sender
    }

    /**
     * Set the log level.
     *
     * Output a log that is equal to or larger than the set value.
     * Log levels are defined in ascending order
     * [VERBOSE], [DEBUG], [INFO], [WARN], [ERROR], [ASSERT],
     * If set [ERROR], output log of [ERROR] and [ASSERT] level.
     *
     * Default value is [Integer.MAX_VALUE], This means that nothing to output.
     *
     * @param level log level
     * @see .VERBOSE
     * @see .DEBUG
     * @see .INFO
     * @see .WARN
     * @see .ERROR
     * @see .ASSERT
     */
    @JvmStatic
    fun setLogLevel(level: Int) {
        logLevel = level
    }

    private fun send(level: Int, message: String, throwable: Throwable?) {
        if (level < logLevel) {
            return
        }
        logSender?.invoke(level, message, throwable)
    }

    private fun send(level: Int, supplier: () -> String, throwable: Throwable?) {
        if (level < logLevel) {
            return
        }
        logSender?.invoke(level, supplier(), throwable)
    }

    /**
     * Send log at [VERBOSE] level
     *
     * @param message log message
     */
    @JvmStatic
    fun v(message: String) {
        send(VERBOSE, message, null)
    }

    /**
     * Send log at [VERBOSE] level
     *
     * @param supplier log message supplier
     */
    @JvmStatic
    fun v(supplier: () -> String) {
        send(VERBOSE, supplier, null)
    }

    /**
     * Send log at [VERBOSE] level
     *
     * @param throwable Throwable
     */
    @JvmStatic
    fun v(throwable: Throwable?) {
        send(VERBOSE, "", throwable)
    }

    /**
     * Send log at [VERBOSE] level
     *
     * @param message   log message
     * @param throwable Throwable
     */
    @JvmStatic
    fun v(message: String, throwable: Throwable?) {
        send(VERBOSE, message, throwable)
    }

    /**
     * Send log at [VERBOSE] level
     *
     * @param supplier  log message supplier
     * @param throwable Throwable
     */
    @JvmStatic
    fun v(supplier: () -> String, throwable: Throwable?) {
        send(VERBOSE, supplier, throwable)
    }

    /**
     * Send log at [DEBUG] level
     *
     * @param message log message
     */
    @JvmStatic
    fun d(message: String) {
        send(DEBUG, message, null)
    }

    /**
     * Send log at [DEBUG] level
     *
     * @param supplier log message supplier
     */
    @JvmStatic
    fun d(supplier: () -> String) {
        send(DEBUG, supplier, null)
    }

    /**
     * Send log at [DEBUG] level
     *
     * @param throwable Throwable
     */
    @JvmStatic
    fun d(throwable: Throwable?) {
        send(DEBUG, "", throwable)
    }

    /**
     * Send log at [DEBUG] level
     *
     * @param message   log message
     * @param throwable Throwable
     */
    @JvmStatic
    fun d(message: String, throwable: Throwable?) {
        send(DEBUG, message, throwable)
    }

    /**
     * Send log at [DEBUG] level
     *
     * @param supplier  log message supplier
     * @param throwable Throwable
     */
    @JvmStatic
    fun d(supplier: () -> String, throwable: Throwable?) {
        send(DEBUG, supplier, throwable)
    }

    /**
     * Send log at [INFO] level
     *
     * @param message log message
     */
    @JvmStatic
    fun i(message: String) {
        send(INFO, message, null)
    }

    /**
     * Send log at [INFO] level
     *
     * @param supplier log message supplier
     */
    @JvmStatic
    fun i(supplier: () -> String) {
        send(INFO, supplier, null)
    }

    /**
     * Send log at [INFO] level
     *
     * @param throwable Throwable
     */
    @JvmStatic
    fun i(throwable: Throwable?) {
        send(INFO, "", throwable)
    }

    /**
     * Send log at [INFO] level
     *
     * @param message   log message
     * @param throwable Throwable
     */
    @JvmStatic
    fun i(message: String, throwable: Throwable?) {
        send(INFO, message, throwable)
    }

    /**
     * Send log at [INFO] level
     *
     * @param supplier  log message supplier
     * @param throwable Throwable
     */
    @JvmStatic
    fun i(supplier: () -> String, throwable: Throwable?) {
        send(INFO, supplier, throwable)
    }

    /**
     * Send log at [WARN] level
     *
     * @param message log message
     */
    @JvmStatic
    fun w(message: String) {
        send(WARN, message, null)
    }

    /**
     * Send log at [WARN] level
     *
     * @param supplier log message supplier
     */
    @JvmStatic
    fun w(supplier: () -> String) {
        send(WARN, supplier, null)
    }

    /**
     * Send log at [WARN] level
     *
     * @param throwable Throwable
     */
    @JvmStatic
    fun w(throwable: Throwable?) {
        send(WARN, "", throwable)
    }

    /**
     * Send log at [WARN] level
     *
     * @param message   log message
     * @param throwable Throwable
     */
    @JvmStatic
    fun w(message: String, throwable: Throwable?) {
        send(WARN, message, throwable)
    }

    /**
     * Send log at [WARN] level
     *
     * @param supplier  log message supplier
     * @param throwable Throwable
     */
    @JvmStatic
    fun w(supplier: () -> String, throwable: Throwable?) {
        send(WARN, supplier, throwable)
    }

    /**
     * Send log at [ERROR] level
     *
     * @param message log message
     */
    @JvmStatic
    fun e(message: String) {
        send(ERROR, message, null)
    }

    /**
     * Send log at [ERROR] level
     *
     * @param supplier log message supplier
     */
    @JvmStatic
    fun e(supplier: () -> String) {
        send(ERROR, supplier, null)
    }

    /**
     * Send log at [ERROR] level
     *
     * @param throwable Throwable
     */
    @JvmStatic
    fun e(throwable: Throwable?) {
        send(ERROR, "", throwable)
    }

    /**
     * Send log at [ERROR] level
     *
     * @param message   log message
     * @param throwable Throwable
     */
    @JvmStatic
    fun e(message: String, throwable: Throwable?) {
        send(ERROR, message, throwable)
    }

    /**
     * Send log at [ERROR] level
     *
     * @param supplier  log message supplier
     * @param throwable Throwable
     */
    @JvmStatic
    fun e(supplier: () -> String, throwable: Throwable?) {
        send(ERROR, supplier, throwable)
    }
}

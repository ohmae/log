/*
 * Copyright (c) 2019 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.log

/**
 * Log sender interface.
 *
 * @author [大前良介 (OHMAE Ryosuke)](mailto:ryo@mm2d.net)
 */
interface Sender {
    /**
     * Called at log output.
     *
     * When retrieving the caller refer to index 3 of stacktrace.
     *
     * @param level     Log level
     * @param message   Log message
     * @param throwable Throwable
     */
    fun send(level: Int, message: String, throwable: Throwable?)
}

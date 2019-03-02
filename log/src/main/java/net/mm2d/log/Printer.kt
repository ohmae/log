/*
 * Copyright (c) 2019 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.log

/**
 * Delegate the log output method.
 */
interface Printer {
    /**
     * Output log.
     *
     * @param level   Log level
     * @param tag     TAG
     * @param message Log message
     */
    fun print(level: Int, tag: String, message: String)
}

/*
 * Copyright (c) 2019 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.log

import java.io.PrintWriter
import java.io.StringWriter

/**
 * Log sender interface.
 */
typealias Sender = (Int, String, Throwable?) -> Unit

/**
 * Delegate the log output method.
 */
typealias Printer = (level: Int, tag: String, message: String) -> Unit

/**
 * Utility method for Sender.
 *
 * @return Thread info string.
 */
fun makeThreadInfo(): String {
    val thread = Thread.currentThread()
    return thread.threadGroup?.let { "[${thread.name},${thread.priority},${it.name}] " }
        ?: "[${thread.name},${thread.priority}] "
}

/**
 * Utility method for Sender.
 *
 * @param message Log message
 * @param tr      Throwable
 * @return message + stacktrace
 */
fun makeMessage(message: String, tr: Throwable?): String {
    if (message.isEmpty()) {
        return tr?.let { makeStackTraceString(it) } ?: ""
    }
    return if (tr == null) {
        message
    } else "$message\n" + makeStackTraceString(tr)
}

/**
 * Utility method for Sender.
 *
 * @param tr throwable
 * @return Stacktrace string
 */
fun makeStackTraceString(tr: Throwable): String {
    val sw = StringWriter()
    val pw = PrintWriter(sw)
    tr.printStackTrace(pw)
    pw.flush()
    return sw.toString()
}

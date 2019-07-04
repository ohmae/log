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
typealias Sender = (level: Int, message: String, throwable: Throwable?) -> Unit

/**
 * Delegate the log output method.
 */
typealias Printer = (level: Int, tag: String, message: String) -> Unit

/**
 * Make thread info string
 *
 * Utility method for Sender.
 *
 * @return Thread info string.
 */
fun makeThreadInfo(): String = Thread.currentThread().let { thread ->
    "[${thread.name},${thread.priority}" + (thread.threadGroup?.let { ",${it.name}] " } ?: "] ")
}

/**
 * Make message by join message and stack trace.
 *
 * Utility method for Sender.
 *
 * @param message Log message
 * @param tr      Throwable
 * @return message + stacktrace
 */
fun makeMessage(message: String, tr: Throwable?): String = if (message.isEmpty()) {
    tr?.let { makeStackTraceString(it) } ?: ""
} else {
    tr?.let { "$message\n" + makeStackTraceString(it) } ?: message
}

/**
 * Make stack trace string from Throwable.
 *
 * Utility method for Sender.
 *
 * @param tr throwable
 * @return Stacktrace string
 */
fun makeStackTraceString(tr: Throwable): String {
    val sw = StringWriter()
    val pw = PrintWriter(sw, false)
    tr.printStackTrace(pw)
    pw.flush()
    return sw.toString()
}

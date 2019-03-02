/*
 * Copyright (c) 2019 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.log

import net.mm2d.log.Logger.DEBUG
import net.mm2d.log.Logger.ERROR
import net.mm2d.log.Logger.INFO
import net.mm2d.log.Logger.VERBOSE
import net.mm2d.log.Logger.WARN
import java.io.PrintWriter
import java.io.StringWriter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Provide sender factory method and control method.
 *
 * @author [大前良介 (OHMAE Ryosuke)](mailto:ryo@mm2d.net)
 */
object Senders {
    /**
     * Set whether to append the caller's code position to the log.
     *
     * If set true, append the caller's code position to the log.
     * This will act as a link to the source code of caller on the IntelliJ console.
     *
     * Default value is false
     *
     * @param append if true, enabled.
     * @see DefaultSender
     */
    @JvmStatic
    fun appendCaller(append: Boolean) {
        DefaultSender.appendCaller(append)
    }

    /**
     * Set whether to append caller's thread information to the log.
     *
     * Default value is false
     *
     * @param append if true, enabled.
     * @see DefaultSender
     */
    @JvmStatic
    fun appendThread(append: Boolean) {
        DefaultSender.appendThread(append)
    }

    /**
     * Default [Sender] that using System.out.println
     *
     * @return Sender
     */
    @JvmStatic
    fun create(): Sender {
        return DefaultSender(DefaultPrinter())
    }

    private class DefaultPrinter : Printer {

        private val dateString: String
            get() = FORMAT.get().format(Date(System.currentTimeMillis()))

        override fun print(level: Int, tag: String, message: String) {
            val prefix = "$dateString ${level.toLogLevelString()} [$tag] "
            message.split("\n").forEach {
                println(prefix + it)
            }
        }

        /**
         * Utility method for Sender.
         *
         * @receiver Log level
         * @return Log level string
         */
        private fun Int.toLogLevelString(): String {
            return when (this) {
                VERBOSE -> "V"
                DEBUG -> "D"
                INFO -> "I"
                WARN -> "W"
                ERROR -> "E"
                else -> " "
            }
        }

        companion object {
            private val FORMAT = object : ThreadLocal<DateFormat>() {
                override fun initialValue(): DateFormat {
                    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US)
                }
            }
        }
    }
}

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

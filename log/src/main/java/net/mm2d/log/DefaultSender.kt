/*
 * Copyright (c) 2019 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.log

/**
 * Default implementation of [Sender].
 *
 * @author [大前良介 (OHMAE Ryosuke)](mailto:ryo@mm2d.net)
 *
 * @param printer Printer
 */
class DefaultSender(
    private val printer: Printer
) : Sender {

    override fun send(level: Int, message: String, throwable: Throwable?) {
        val trace = Throwable().stackTrace
        // Sender#send -> Logger#send -> Logger#v/d/i/w/e -> ログコール場所
        val element = if (trace.size < 4) null else trace[3]
        val tag = makeTag(element)
        if (!sAppendCaller || element == null) {
            send(level, tag, makeMessage(message, throwable))
            return
        }
        send(level, tag, element.toString() + " : " + makeMessage(message, throwable))
    }

    private fun makeTag(element: StackTraceElement?): String {
        element ?: return "tag"
        return element.className
            .substringAfterLast('.')
            .substringBefore('$')
            .let { if (it.length > MAX_TAG_LENGTH) it.substring(0, MAX_TAG_LENGTH) else it }
    }

    private fun send(level: Int, tag: String, message: String) {
        if (sAppendThread) {
            printer.print(level, tag, makeThreadInfo() + message)
            return
        }
        printer.print(level, tag, message)
    }

    companion object {
        private const val MAX_TAG_LENGTH = 23
        private var sAppendCaller: Boolean = false
        private var sAppendThread: Boolean = false

        /**
         * Set whether to append the caller's code position to the log.
         *
         * If set true, append the caller's code position to the log.
         * This will act as a link to the source code of caller on the IntelliJ console.
         *
         * Default value is false
         *
         * @param append if true, enabled.
         */
        @JvmStatic
        fun appendCaller(append: Boolean) {
            sAppendCaller = append
        }

        /**
         * Set whether to append caller's thread information to the log.
         *
         * Default value is false
         *
         * @param append if true, enabled.
         */
        @JvmStatic
        fun appendThread(append: Boolean) {
            sAppendThread = append
        }
    }
}

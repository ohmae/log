/*
 * Copyright (c) 2019 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.log.android

import android.util.Log
import net.mm2d.log.DefaultSender
import net.mm2d.log.Sender

/**
 * Provide sender factory method and control method for Android.
 *
 * @author [大前良介 (OHMAE Ryosuke)](mailto:ryo@mm2d.net)
 */
object AndroidSenders {
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
    fun appendThread(append: Boolean) {
        DefaultSender.appendThread(append)
    }

    /**
     * Default [Sender] that using System.out.println
     *
     * @return Sender
     */
    @JvmStatic
    fun create(): Sender = DefaultSender.create { level, tag, message ->
        message.split("\n").forEach {
            Log.println(level, tag, it)
        }
    }
}

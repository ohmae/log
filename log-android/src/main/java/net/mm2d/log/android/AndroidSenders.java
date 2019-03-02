/*
 * Copyright (c) 2019 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.log.android;

import android.util.Log;

import net.mm2d.log.DefaultSender;
import net.mm2d.log.Printer;
import net.mm2d.log.Sender;

import androidx.annotation.NonNull;

/**
 * Provide sender factory method and control method for Android.
 *
 * @author [大前良介 (OHMAE Ryosuke)](mailto:ryo@mm2d.net)
 */
public final class AndroidSenders {
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
        return new DefaultSender(new AndroidPrinter());
    }

    private static class AndroidPrinter implements Printer {
        @Override
        public void print(int level, @NonNull String tag, @NonNull String message) {
            final String[] lines = message.split("\n");
            for (final String line : lines) {
                Log.println(level, tag, line);
            }
        }
    }
}

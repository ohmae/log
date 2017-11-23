/*
 * Copyright (c) 2017 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.log;

import android.support.annotation.NonNull;

import net.mm2d.log.Log.Initializer;

/**
 * @author <a href="mailto:ryo@mm2d.net">大前良介 (OHMAE Ryosuke)</a>
 */
public class AndroidLogInitializer {

    /**
     * 唯一のインスタンスを返す。
     *
     * @return Printのインスタンス
     */
    @NonNull
    public static Initializer get() {
        return InitializerImpl.INSTANCE;
    }

    private static class InitializerImpl implements Initializer {
        private static final Initializer INSTANCE = new InitializerImpl();

        @Override
        public void invoke(
                final boolean debug,
                final boolean verbose) {
            if (debug) {
                Log.appendCaller(verbose);
                Log.appendThread(verbose);
                Log.setLogLevel(Log.VERBOSE);
                Log.setPrint(AndroidPrint.get());
                return;
            }
            Log.appendCaller(false);
            Log.appendThread(false);
            Log.setLogLevel(Integer.MAX_VALUE);
            Log.setPrint(Log.getEmptyPrint());
        }
    }
}

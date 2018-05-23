/*
 * Copyright (c) 2018 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.log.android;

import android.support.annotation.NonNull;

import net.mm2d.log.Log;
import net.mm2d.log.Log.Initializer;
import net.mm2d.log.Log.Print;

/**
 * Androidで{@link Log}を使うためのInitializerを提供する。
 *
 * @author <a href="mailto:ryo@mm2d.net">大前良介 (OHMAE Ryosuke)</a>
 */
public class AndroidLogInitializer {
    /**
     * {@link android.util.Log}を使用して出力するInitializer
     *
     * @return Printのインスタンス
     */
    @NonNull
    public static Initializer getDefault() {
        return DefaultInitializer.INSTANCE;
    }

    /**
     * {@link android.util.Log}を使用し、必ずMainThreadから出力を行うInitializer
     *
     * @return Printのインスタンス
     */
    @NonNull
    public static Initializer getMainThread() {
        return MainThreadInitializer.INSTANCE;
    }

    /**
     * {@link android.util.Log}を使用し、常に内部の同一スレッドから出力を行うInitializer
     *
     * @return Printのインスタンス
     */
    @NonNull
    public static Initializer getSingleThread() {
        return SingleThreadInitializer.INSTANCE;
    }

    private static abstract class BaseInitializer implements Initializer {
        @Override
        public void invoke(
                final boolean debug,
                final boolean verbose) {
            if (debug) {
                Log.appendCaller(verbose);
                Log.appendThread(verbose);
                Log.setLogLevel(Log.VERBOSE);
                Log.setPrint(getPrint());
                return;
            }
            Log.appendCaller(false);
            Log.appendThread(false);
            Log.setLogLevel(Integer.MAX_VALUE);
            Log.setPrint(Log.getEmptyPrint());
        }

        protected abstract Print getPrint();
    }

    private static class DefaultInitializer {
        private static final Initializer INSTANCE = new BaseInitializer() {
            @Override
            protected Print getPrint() {
                return DefaultLogPrint.get();
            }
        };
    }

    private static class MainThreadInitializer {
        private static final Initializer INSTANCE = new BaseInitializer() {
            @Override
            protected Print getPrint() {
                return MainThreadLogPrint.get();
            }
        };
    }

    private static class SingleThreadInitializer {
        private static final Initializer INSTANCE = new BaseInitializer() {
            @Override
            protected Print getPrint() {
                return SingleThreadLogPrint.get();
            }
        };
    }
}

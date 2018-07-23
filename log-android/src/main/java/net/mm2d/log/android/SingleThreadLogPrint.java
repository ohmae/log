/*
 * Copyright (c) 2018 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.log.android;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;

import net.mm2d.log.Log;
import net.mm2d.log.Log.Initializer;
import net.mm2d.log.Log.Print;

/**
 * {@link Log}をAndroid上で使用するための出力クラスを提供する。
 *
 * <p>以下のようにすることで出力先をLogcatに変更することができる。
 *
 * <pre>{@code
 * Log.setPrint(SingleThreadLogPrint.get());
 * }</pre>
 *
 * <p>{@link Log#initialize(boolean, boolean)}を使用する場合は、
 * {@link Log#setInitializer(Initializer)}で、
 * {@link AndroidLogInitializer#getSingleThread()}を指定することで、
 * このクラスを利用した初期化が行われるようになる。
 *
 * <p>また、この出力では必ず内部スレッドから出力を行うため、
 * 複数スレッドからのリクエストが重複した場合でも、1リクエストずつ出力される。
 *
 * @author <a href="mailto:ryo@mm2d.net">大前良介 (OHMAE Ryosuke)</a>
 */
public class SingleThreadLogPrint {
    /**
     * 唯一のインスタンスを返す。
     *
     * @return Printのインスタンス
     */
    @NonNull
    public static Print get() {
        return SingleThreadPrintImpl.getInstance();
    }


    private static class SingleThreadPrintImpl implements Print {
        @NonNull
        private static final SingleThreadPrintImpl INSTANCE = new SingleThreadPrintImpl();

        @NonNull
        static SingleThreadPrintImpl getInstance() {
            return INSTANCE;
        }

        @NonNull
        private final Handler mHandler;

        private SingleThreadPrintImpl() {
            final HandlerThread thread = new HandlerThread("logging");
            thread.start();
            mHandler = new Handler(thread.getLooper());
        }

        @Override
        public void println(
                final int level,
                @NonNull final String tag,
                @NonNull final String message) {
            mHandler.post(() -> printlnInner(level, tag, message));
        }

        private void printlnInner(
                final int level,
                @NonNull final String tag,
                @NonNull final String message) {
            final String[] lines = message.split("\n");
            for (final String line : lines) {
                android.util.Log.println(level, tag, line);
            }
        }
    }
}

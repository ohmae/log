/*
 * Copyright (c) 2018 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.log.android;

import net.mm2d.log.Log;
import net.mm2d.log.Log.Initializer;
import net.mm2d.log.Log.Print;

import androidx.annotation.NonNull;

/**
 * {@link Log}をAndroid上で使用するための出力クラスを提供する。
 *
 * <p>以下のようにすることで出力先をLogcatに変更することができる。
 *
 * <pre>{@code
 * Log.setPrint(DefaultLogPrint.get());
 * }</pre>
 *
 * <p>{@link Log#initialize(boolean, boolean)}を使用する場合は、
 * {@link Log#setInitializer(Initializer)}で、
 * {@link AndroidLogInitializer#getDefault()}を指定することで、
 * このクラスを利用した初期化が行われるようになる。
 *
 * @author <a href="mailto:ryo@mm2d.net">大前良介 (OHMAE Ryosuke)</a>
 */
public class DefaultLogPrint {
    /**
     * 唯一のインスタンスを返す。
     *
     * @return Printのインスタンス
     */
    @NonNull
    public static Print get() {
        return DefaultLogPrintImpl.INSTANCE;
    }

    private static class DefaultLogPrintImpl implements Print {
        private static final Print INSTANCE = new DefaultLogPrintImpl();

        @Override
        public void println(
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

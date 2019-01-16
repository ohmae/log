/*
 * Copyright (c) 2019 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.log;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Log sender interface.
 *
 * @author <a href="mailto:ryo@mm2d.net">大前良介 (OHMAE Ryosuke)</a>
 */
public interface Sender {
    /**
     * Called at log output.
     *
     * <p>When retrieving the caller refer to index 3 of stacktrace.
     *
     * @param level     Log level
     * @param message   Log message
     * @param throwable Throwable
     */
    void send(
            int level,
            @Nonnull String message,
            @Nullable Throwable throwable);
}

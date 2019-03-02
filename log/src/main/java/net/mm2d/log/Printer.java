/*
 * Copyright (c) 2019 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.log;

import javax.annotation.Nonnull;

/**
 * Delegate the log output method.
 */
public interface Printer {
    /**
     * Output log.
     *
     * @param level   Log level
     * @param tag     TAG
     * @param message Log message
     */
    void print(
            final int level,
            @Nonnull final String tag,
            @Nonnull final String message);
}

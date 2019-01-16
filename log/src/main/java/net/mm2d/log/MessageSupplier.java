/*
 * Copyright (c) 2019 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.log;

import javax.annotation.Nonnull;

/**
 * Log message supplier.
 *
 * <p>An interface for deferred evaluation of the message.
 *
 * @author <a href="mailto:ryo@mm2d.net">大前良介 (OHMAE Ryosuke)</a>
 */
public interface MessageSupplier {
    /**
     * Return log message.
     *
     * @return Log message
     */
    @Nonnull
    String get();
}

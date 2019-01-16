/*
 * Copyright (c) 2019 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.log;

import org.junit.Test;

import javax.annotation.Nonnull;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author [大前良介 (OHMAE Ryosuke)](mailto:ryo@mm2d.net)
 */
public class LoggerTest {

    @Test
    public void setLogLevel() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);
        final String message = "";

        Logger.setLogLevel(Logger.ASSERT);
        Logger.v(message);
        Logger.d(message);
        Logger.i(message);
        Logger.w(message);
        Logger.e(message);
        verify(sender, never()).send(anyInt(), eq(message), eq(null));
        reset(sender);

        Logger.setLogLevel(Logger.ERROR);
        Logger.v(message);
        Logger.d(message);
        Logger.i(message);
        Logger.w(message);
        verify(sender, never()).send(anyInt(), eq(message), eq(null));
        Logger.e(message);
        verify(sender, times(1)).send(anyInt(), eq(message), eq(null));
        reset(sender);

        Logger.setLogLevel(Logger.WARN);
        Logger.v(message);
        Logger.d(message);
        Logger.i(message);
        verify(sender, never()).send(anyInt(), eq(message), eq(null));
        Logger.w(message);
        Logger.e(message);
        verify(sender, times(2)).send(anyInt(), eq(message), eq(null));
        reset(sender);

        Logger.setLogLevel(Logger.INFO);
        Logger.v(message);
        Logger.d(message);
        verify(sender, never()).send(anyInt(), eq(message), eq(null));
        Logger.i(message);
        Logger.w(message);
        Logger.e(message);
        verify(sender, times(3)).send(anyInt(), eq(message), eq(null));
        reset(sender);

        Logger.setLogLevel(Logger.DEBUG);
        Logger.v(message);
        verify(sender, never()).send(anyInt(), eq(message), eq(null));
        Logger.d(message);
        Logger.i(message);
        Logger.w(message);
        Logger.e(message);
        verify(sender, times(4)).send(anyInt(), eq(message), eq(null));
        reset(sender);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.v(message);
        Logger.d(message);
        Logger.i(message);
        Logger.w(message);
        Logger.e(message);
        verify(sender, times(5)).send(anyInt(), eq(message), eq(null));
        reset(sender);
    }

    @Test
    public void v() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);
        final String message = "";

        Logger.setLogLevel(Logger.ASSERT);
        Logger.v(message);

        verify(sender, never()).send(Logger.VERBOSE, message, null);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.v(message);

        verify(sender).send(Logger.VERBOSE, message, null);
    }

    @Test
    public void v1() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);

        final String message = "";
        final MessageSupplier supplier = spy(new MessageSupplier() {
            @Nonnull
            @Override
            public String get() {
                return message;
            }
        });

        Logger.setLogLevel(Logger.ASSERT);
        Logger.v(supplier);

        verify(supplier, never()).get();
        verify(sender, never()).send(Logger.VERBOSE, message, null);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.v(supplier);

        verify(supplier).get();
        verify(sender).send(Logger.VERBOSE, message, null);
    }

    @Test
    public void v2() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);
        final Throwable throwable = new Throwable();

        Logger.setLogLevel(Logger.ASSERT);
        Logger.v(throwable);

        verify(sender, never()).send(Logger.VERBOSE, "", throwable);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.v(throwable);

        verify(sender).send(Logger.VERBOSE, "", throwable);
    }

    @Test
    public void v3() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);
        final String message = "";
        final Throwable throwable = new Throwable();

        Logger.setLogLevel(Logger.ASSERT);
        Logger.v(message, throwable);

        verify(sender, never()).send(Logger.VERBOSE, message, throwable);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.v(message, throwable);

        verify(sender).send(Logger.VERBOSE, message, throwable);
    }

    @Test
    public void v4() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);
        final String message = "";
        final Throwable throwable = new Throwable();
        final MessageSupplier supplier = spy(new MessageSupplier() {
            @Nonnull
            @Override
            public String get() {
                return message;
            }
        });

        Logger.setLogLevel(Logger.ASSERT);
        Logger.v(supplier, throwable);

        verify(supplier, never()).get();
        verify(sender, never()).send(Logger.VERBOSE, message, throwable);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.v(supplier, throwable);

        verify(supplier).get();
        verify(sender).send(Logger.VERBOSE, message, throwable);
    }

    @Test
    public void d() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);
        final String message = "";

        Logger.setLogLevel(Logger.ASSERT);
        Logger.d(message);

        verify(sender, never()).send(Logger.DEBUG, message, null);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.d(message);

        verify(sender).send(Logger.DEBUG, message, null);
    }

    @Test
    public void d1() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);

        final String message = "";
        final MessageSupplier supplier = spy(new MessageSupplier() {
            @Nonnull
            @Override
            public String get() {
                return message;
            }
        });

        Logger.setLogLevel(Logger.ASSERT);
        Logger.d(supplier);

        verify(supplier, never()).get();
        verify(sender, never()).send(Logger.DEBUG, message, null);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.d(supplier);

        verify(supplier).get();
        verify(sender).send(Logger.DEBUG, message, null);
    }

    @Test
    public void d2() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);
        final Throwable throwable = new Throwable();

        Logger.setLogLevel(Logger.ASSERT);
        Logger.d(throwable);

        verify(sender, never()).send(Logger.DEBUG, "", throwable);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.d(throwable);

        verify(sender).send(Logger.DEBUG, "", throwable);
    }

    @Test
    public void d3() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);
        final String message = "";
        final Throwable throwable = new Throwable();

        Logger.setLogLevel(Logger.ASSERT);
        Logger.d(message, throwable);

        verify(sender, never()).send(Logger.DEBUG, message, throwable);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.d(message, throwable);

        verify(sender).send(Logger.DEBUG, message, throwable);
    }

    @Test
    public void d4() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);
        final String message = "";
        final Throwable throwable = new Throwable();
        final MessageSupplier supplier = spy(new MessageSupplier() {
            @Nonnull
            @Override
            public String get() {
                return message;
            }
        });

        Logger.setLogLevel(Logger.ASSERT);
        Logger.d(supplier, throwable);

        verify(supplier, never()).get();
        verify(sender, never()).send(Logger.DEBUG, message, throwable);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.d(supplier, throwable);

        verify(supplier).get();
        verify(sender).send(Logger.DEBUG, message, throwable);
    }

    @Test
    public void i() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);
        final String message = "";

        Logger.setLogLevel(Logger.ASSERT);
        Logger.i(message);

        verify(sender, never()).send(Logger.INFO, message, null);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.i(message);

        verify(sender).send(Logger.INFO, message, null);
    }

    @Test
    public void i1() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);

        final String message = "";
        final MessageSupplier supplier = spy(new MessageSupplier() {
            @Nonnull
            @Override
            public String get() {
                return message;
            }
        });

        Logger.setLogLevel(Logger.ASSERT);
        Logger.i(supplier);

        verify(supplier, never()).get();
        verify(sender, never()).send(Logger.INFO, message, null);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.i(supplier);

        verify(supplier).get();
        verify(sender).send(Logger.INFO, message, null);
    }

    @Test
    public void i2() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);
        final Throwable throwable = new Throwable();

        Logger.setLogLevel(Logger.ASSERT);
        Logger.i(throwable);

        verify(sender, never()).send(Logger.INFO, "", throwable);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.i(throwable);

        verify(sender).send(Logger.INFO, "", throwable);
    }

    @Test
    public void i3() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);
        final String message = "";
        final Throwable throwable = new Throwable();

        Logger.setLogLevel(Logger.ASSERT);
        Logger.i(message, throwable);

        verify(sender, never()).send(Logger.INFO, message, throwable);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.i(message, throwable);

        verify(sender).send(Logger.INFO, message, throwable);
    }

    @Test
    public void i4() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);
        final String message = "";
        final Throwable throwable = new Throwable();
        final MessageSupplier supplier = spy(new MessageSupplier() {
            @Nonnull
            @Override
            public String get() {
                return message;
            }
        });

        Logger.setLogLevel(Logger.ASSERT);
        Logger.i(supplier, throwable);

        verify(supplier, never()).get();
        verify(sender, never()).send(Logger.INFO, message, throwable);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.i(supplier, throwable);

        verify(supplier).get();
        verify(sender).send(Logger.INFO, message, throwable);
    }

    @Test
    public void w() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);
        final String message = "";

        Logger.setLogLevel(Logger.ASSERT);
        Logger.w(message);

        verify(sender, never()).send(Logger.WARN, message, null);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.w(message);

        verify(sender).send(Logger.WARN, message, null);
    }

    @Test
    public void w1() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);

        final String message = "";
        final MessageSupplier supplier = spy(new MessageSupplier() {
            @Nonnull
            @Override
            public String get() {
                return message;
            }
        });

        Logger.setLogLevel(Logger.ASSERT);
        Logger.w(supplier);

        verify(supplier, never()).get();
        verify(sender, never()).send(Logger.WARN, message, null);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.w(supplier);

        verify(supplier).get();
        verify(sender).send(Logger.WARN, message, null);
    }

    @Test
    public void w2() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);
        final Throwable throwable = new Throwable();

        Logger.setLogLevel(Logger.ASSERT);
        Logger.w(throwable);

        verify(sender, never()).send(Logger.WARN, "", throwable);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.w(throwable);

        verify(sender).send(Logger.WARN, "", throwable);
    }

    @Test
    public void w3() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);
        final String message = "";
        final Throwable throwable = new Throwable();

        Logger.setLogLevel(Logger.ASSERT);
        Logger.w(message, throwable);

        verify(sender, never()).send(Logger.WARN, message, throwable);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.w(message, throwable);

        verify(sender).send(Logger.WARN, message, throwable);
    }

    @Test
    public void w4() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);
        final String message = "";
        final Throwable throwable = new Throwable();
        final MessageSupplier supplier = spy(new MessageSupplier() {
            @Nonnull
            @Override
            public String get() {
                return message;
            }
        });

        Logger.setLogLevel(Logger.ASSERT);
        Logger.w(supplier, throwable);

        verify(supplier, never()).get();
        verify(sender, never()).send(Logger.WARN, message, throwable);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.w(supplier, throwable);

        verify(supplier).get();
        verify(sender).send(Logger.WARN, message, throwable);
    }

    @Test
    public void e() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);
        final String message = "";

        Logger.setLogLevel(Logger.ASSERT);
        Logger.e(message);

        verify(sender, never()).send(Logger.ERROR, message, null);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.e(message);

        verify(sender).send(Logger.ERROR, message, null);
    }

    @Test
    public void e1() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);

        final String message = "";
        final MessageSupplier supplier = spy(new MessageSupplier() {
            @Nonnull
            @Override
            public String get() {
                return message;
            }
        });

        Logger.setLogLevel(Logger.ASSERT);
        Logger.e(supplier);

        verify(supplier, never()).get();
        verify(sender, never()).send(Logger.ERROR, message, null);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.e(supplier);

        verify(supplier).get();
        verify(sender).send(Logger.ERROR, message, null);
    }

    @Test
    public void e2() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);
        final Throwable throwable = new Throwable();

        Logger.setLogLevel(Logger.ASSERT);
        Logger.e(throwable);

        verify(sender, never()).send(Logger.ERROR, "", throwable);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.e(throwable);

        verify(sender).send(Logger.ERROR, "", throwable);
    }

    @Test
    public void e3() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);
        final String message = "";
        final Throwable throwable = new Throwable();

        Logger.setLogLevel(Logger.ASSERT);
        Logger.e(message, throwable);

        verify(sender, never()).send(Logger.ERROR, message, throwable);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.e(message, throwable);

        verify(sender).send(Logger.ERROR, message, throwable);
    }

    @Test
    public void e4() {
        final Sender sender = mock(Sender.class);
        Logger.setSender(sender);
        final String message = "";
        final Throwable throwable = new Throwable();
        final MessageSupplier supplier = spy(new MessageSupplier() {
            @Nonnull
            @Override
            public String get() {
                return message;
            }
        });

        Logger.setLogLevel(Logger.ASSERT);
        Logger.e(supplier, throwable);

        verify(supplier, never()).get();
        verify(sender, never()).send(Logger.ERROR, message, throwable);

        Logger.setLogLevel(Logger.VERBOSE);
        Logger.e(supplier, throwable);

        verify(supplier).get();
        verify(sender).send(Logger.ERROR, message, throwable);
    }
}
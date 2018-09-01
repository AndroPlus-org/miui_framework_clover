// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.os.Process;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentUtils
{

    private ConcurrentUtils()
    {
    }

    public static ExecutorService newFixedThreadPool(int i, String s, int j)
    {
        return Executors.newFixedThreadPool(i, new ThreadFactory(s, j) {

            public Thread newThread(Runnable runnable)
            {
                return linuxThreadPriority. new Thread(runnable) {

                    public void run()
                    {
                        Process.setThreadPriority(linuxThreadPriority);
                        r.run();
                    }

                    final _cls1 this$1;
                    final int val$linuxThreadPriority;
                    final Runnable val$r;

            
            {
                this$1 = final__pcls1;
                linuxThreadPriority = I.this;
                r = runnable;
                super(final_s);
            }
                }
;
            }

            private final AtomicInteger threadNum = new AtomicInteger(0);
            final int val$linuxThreadPriority;
            final String val$poolName;

            
            {
                poolName = s;
                linuxThreadPriority = i;
                super();
            }
        }
);
    }

    public static Object waitForFutureNoInterrupt(Future future, String s)
    {
        try
        {
            future = ((Future) (future.get()));
        }
        // Misplaced declaration of an exception variable
        catch(Future future)
        {
            Thread.currentThread().interrupt();
            throw new IllegalStateException((new StringBuilder()).append(s).append(" interrupted").toString());
        }
        // Misplaced declaration of an exception variable
        catch(Future future)
        {
            throw new RuntimeException((new StringBuilder()).append(s).append(" failed").toString(), future);
        }
        return future;
    }
}

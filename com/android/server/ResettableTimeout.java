// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server;

import android.os.ConditionVariable;
import android.os.SystemClock;

abstract class ResettableTimeout
{
    private class T extends Thread
    {

        public void run()
        {
            ResettableTimeout._2D_get0(ResettableTimeout.this).open();
_L2:
            this;
            JVM INSTR monitorenter ;
            long l = ResettableTimeout._2D_get1(ResettableTimeout.this) - SystemClock.uptimeMillis();
            if(l > 0L)
                break MISSING_BLOCK_LABEL_58;
            ResettableTimeout._2D_set0(ResettableTimeout.this, true);
            off();
            ResettableTimeout._2D_set1(ResettableTimeout.this, null);
            this;
            JVM INSTR monitorexit ;
            return;
            this;
            JVM INSTR monitorexit ;
            try
            {
                sleep(l);
            }
            catch(InterruptedException interruptedexception) { }
            if(true) goto _L2; else goto _L1
_L1:
            Exception exception;
            exception;
            throw exception;
        }

        final ResettableTimeout this$0;

        private T()
        {
            this$0 = ResettableTimeout.this;
            super();
        }

        T(T t)
        {
            this();
        }
    }


    static ConditionVariable _2D_get0(ResettableTimeout resettabletimeout)
    {
        return resettabletimeout.mLock;
    }

    static long _2D_get1(ResettableTimeout resettabletimeout)
    {
        return resettabletimeout.mOffAt;
    }

    static boolean _2D_set0(ResettableTimeout resettabletimeout, boolean flag)
    {
        resettabletimeout.mOffCalled = flag;
        return flag;
    }

    static Thread _2D_set1(ResettableTimeout resettabletimeout, Thread thread)
    {
        resettabletimeout.mThread = thread;
        return thread;
    }

    ResettableTimeout()
    {
        mLock = new ConditionVariable();
    }

    public void cancel()
    {
        this;
        JVM INSTR monitorenter ;
        mOffAt = 0L;
        if(mThread != null)
        {
            mThread.interrupt();
            mThread = null;
        }
        if(!mOffCalled)
        {
            mOffCalled = true;
            off();
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void go(long l)
    {
        this;
        JVM INSTR monitorenter ;
        mOffAt = SystemClock.uptimeMillis() + l;
        if(mThread != null) goto _L2; else goto _L1
_L1:
        boolean flag = false;
        mLock.close();
        T t = JVM INSTR new #6   <Class ResettableTimeout$T>;
        t.this. T(null);
        mThread = t;
        mThread.start();
        mLock.block();
        mOffCalled = false;
_L3:
        on(flag);
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        flag = true;
        mThread.interrupt();
          goto _L3
        Exception exception;
        exception;
        throw exception;
    }

    public abstract void off();

    public abstract void on(boolean flag);

    private ConditionVariable mLock;
    private volatile long mOffAt;
    private volatile boolean mOffCalled;
    private Thread mThread;
}

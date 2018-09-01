// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;


public final class SomeArgs
{

    private SomeArgs()
    {
        mWaitState = 0;
    }

    private void clear()
    {
        arg1 = null;
        arg2 = null;
        arg3 = null;
        arg4 = null;
        arg5 = null;
        arg6 = null;
        arg7 = null;
        argi1 = 0;
        argi2 = 0;
        argi3 = 0;
        argi4 = 0;
        argi5 = 0;
        argi6 = 0;
    }

    public static SomeArgs obtain()
    {
        Object obj = sPoolLock;
        obj;
        JVM INSTR monitorenter ;
        SomeArgs someargs;
        if(sPoolSize <= 0)
            break MISSING_BLOCK_LABEL_47;
        someargs = sPool;
        sPool = sPool.mNext;
        someargs.mNext = null;
        someargs.mInPool = false;
        sPoolSize--;
        obj;
        JVM INSTR monitorexit ;
        return someargs;
        someargs = new SomeArgs();
        obj;
        JVM INSTR monitorexit ;
        return someargs;
        Exception exception;
        exception;
        throw exception;
    }

    public void complete()
    {
        this;
        JVM INSTR monitorenter ;
        if(mWaitState != 1)
        {
            IllegalStateException illegalstateexception = JVM INSTR new #87  <Class IllegalStateException>;
            illegalstateexception.IllegalStateException("Not waiting");
            throw illegalstateexception;
        }
        break MISSING_BLOCK_LABEL_27;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        mWaitState = 2;
        notifyAll();
        this;
        JVM INSTR monitorexit ;
    }

    public void recycle()
    {
        if(mInPool)
            throw new IllegalStateException("Already recycled.");
        if(mWaitState != 0)
            return;
        Object obj = sPoolLock;
        obj;
        JVM INSTR monitorenter ;
        clear();
        if(sPoolSize < 10)
        {
            mNext = sPool;
            mInPool = true;
            sPool = this;
            sPoolSize++;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static final int MAX_POOL_SIZE = 10;
    static final int WAIT_FINISHED = 2;
    static final int WAIT_NONE = 0;
    static final int WAIT_WAITING = 1;
    private static SomeArgs sPool;
    private static Object sPoolLock = new Object();
    private static int sPoolSize;
    public Object arg1;
    public Object arg2;
    public Object arg3;
    public Object arg4;
    public Object arg5;
    public Object arg6;
    public Object arg7;
    public Object arg8;
    public int argi1;
    public int argi2;
    public int argi3;
    public int argi4;
    public int argi5;
    public int argi6;
    private boolean mInPool;
    private SomeArgs mNext;
    int mWaitState;

}

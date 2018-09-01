// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import android.os.SystemClock;
import java.util.concurrent.TimeoutException;

// Referenced classes of package android.util:
//            SparseIntArray, SparseArray

public abstract class TimedRemoteCaller
{

    public TimedRemoteCaller(long l)
    {
        mCallTimeoutMillis = l;
    }

    protected final Object getResultTimed(int i)
        throws TimeoutException
    {
        long l = SystemClock.uptimeMillis();
_L1:
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        if(mReceivedCalls.indexOfKey(i) < 0)
            break MISSING_BLOCK_LABEL_40;
        obj1 = mReceivedCalls.removeReturnOld(i);
        obj;
        JVM INSTR monitorexit ;
        return obj1;
        long l1;
        l1 = SystemClock.uptimeMillis();
        l1 = mCallTimeoutMillis - (l1 - l);
        if(l1 > 0L)
            break MISSING_BLOCK_LABEL_121;
        mAwaitedCalls.delete(i);
        TimeoutException timeoutexception = JVM INSTR new #47  <Class TimeoutException>;
        StringBuilder stringbuilder = JVM INSTR new #67  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        timeoutexception.TimeoutException(stringbuilder.append("No response for sequence: ").append(i).toString());
        throw timeoutexception;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        obj;
          goto _L1
        mLock.wait(l1);
        obj;
        JVM INSTR monitorexit ;
          goto _L1
    }

    protected final int onBeforeRemoteCall()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        do
        {
            i = mSequenceCounter;
            mSequenceCounter = i + 1;
        } while(mAwaitedCalls.get(i) != 0);
        mAwaitedCalls.put(i, 1);
        obj;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    protected final void onRemoteMethodResult(Object obj, int i)
    {
        Object obj1 = mLock;
        obj1;
        JVM INSTR monitorenter ;
        boolean flag;
        if(mAwaitedCalls.get(i) != 0)
            flag = true;
        else
            flag = false;
        if(!flag)
            break MISSING_BLOCK_LABEL_50;
        mAwaitedCalls.delete(i);
        mReceivedCalls.put(i, obj);
        mLock.notifyAll();
        obj1;
        JVM INSTR monitorexit ;
        return;
        obj;
        throw obj;
    }

    public static final long DEFAULT_CALL_TIMEOUT_MILLIS = 5000L;
    private final SparseIntArray mAwaitedCalls = new SparseIntArray(1);
    private final long mCallTimeoutMillis;
    private final Object mLock = new Object();
    private final SparseArray mReceivedCalls = new SparseArray(1);
    private int mSequenceCounter;
}

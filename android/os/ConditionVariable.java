// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


public class ConditionVariable
{

    public ConditionVariable()
    {
        mCondition = false;
    }

    public ConditionVariable(boolean flag)
    {
        mCondition = flag;
    }

    public void block()
    {
        this;
        JVM INSTR monitorenter ;
_L2:
        boolean flag = mCondition;
        if(flag)
            break; /* Loop/switch isn't completed */
        try
        {
            wait();
        }
        catch(InterruptedException interruptedexception) { }
        if(true) goto _L2; else goto _L1
_L1:
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean block(long l)
    {
        if(l == 0L)
            break MISSING_BLOCK_LABEL_75;
        this;
        JVM INSTR monitorenter ;
        long l1 = System.currentTimeMillis();
        long l2;
        l2 = l1 + l;
        l = l1;
_L1:
        boolean flag = mCondition;
        if(flag || l >= l2)
            break MISSING_BLOCK_LABEL_57;
        try
        {
            wait(l2 - l);
        }
        catch(InterruptedException interruptedexception) { }
        l = System.currentTimeMillis();
          goto _L1
        flag = mCondition;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
        block();
        return true;
    }

    public void close()
    {
        this;
        JVM INSTR monitorenter ;
        mCondition = false;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void open()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        flag = mCondition;
        mCondition = true;
        if(flag)
            break MISSING_BLOCK_LABEL_20;
        notifyAll();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private volatile boolean mCondition;
}

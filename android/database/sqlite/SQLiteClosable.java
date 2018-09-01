// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database.sqlite;

import java.io.Closeable;

public abstract class SQLiteClosable
    implements Closeable
{

    public SQLiteClosable()
    {
        mReferenceCount = 1;
    }

    public void acquireReference()
    {
        this;
        JVM INSTR monitorenter ;
        if(mReferenceCount <= 0)
        {
            IllegalStateException illegalstateexception = JVM INSTR new #18  <Class IllegalStateException>;
            StringBuilder stringbuilder = JVM INSTR new #20  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            illegalstateexception.IllegalStateException(stringbuilder.append("attempt to re-open an already-closed object: ").append(this).toString());
            throw illegalstateexception;
        }
        break MISSING_BLOCK_LABEL_45;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        mReferenceCount = mReferenceCount + 1;
        this;
        JVM INSTR monitorexit ;
    }

    public void close()
    {
        releaseReference();
    }

    protected abstract void onAllReferencesReleased();

    protected void onAllReferencesReleasedFromContainer()
    {
        onAllReferencesReleased();
    }

    public void releaseReference()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        i = mReferenceCount - 1;
        mReferenceCount = i;
        boolean flag;
        if(i == 0)
            flag = true;
        else
            flag = false;
        this;
        JVM INSTR monitorexit ;
        if(flag)
            onAllReferencesReleased();
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void releaseReferenceFromContainer()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        i = mReferenceCount - 1;
        mReferenceCount = i;
        boolean flag;
        if(i == 0)
            flag = true;
        else
            flag = false;
        this;
        JVM INSTR monitorexit ;
        if(flag)
            onAllReferencesReleasedFromContainer();
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private int mReferenceCount;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;


public abstract class Singleton
{

    public Singleton()
    {
    }

    protected abstract Object create();

    public final Object get()
    {
        this;
        JVM INSTR monitorenter ;
        Object obj;
        if(mInstance == null)
            mInstance = create();
        obj = mInstance;
        this;
        JVM INSTR monitorexit ;
        return obj;
        Exception exception;
        exception;
        throw exception;
    }

    private Object mInstance;
}

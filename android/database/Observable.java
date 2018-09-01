// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;

import java.util.ArrayList;

public abstract class Observable
{

    public Observable()
    {
    }

    public void registerObserver(Object obj)
    {
label0:
        {
            if(obj == null)
                throw new IllegalArgumentException("The observer is null.");
            synchronized(mObservers)
            {
                if(mObservers.contains(obj))
                {
                    IllegalStateException illegalstateexception = JVM INSTR new #33  <Class IllegalStateException>;
                    StringBuilder stringbuilder = JVM INSTR new #35  <Class StringBuilder>;
                    stringbuilder.StringBuilder();
                    illegalstateexception.IllegalStateException(stringbuilder.append("Observer ").append(obj).append(" is already registered.").toString());
                    throw illegalstateexception;
                }
                break label0;
            }
        }
        mObservers.add(obj);
        arraylist;
        JVM INSTR monitorexit ;
    }

    public void unregisterAll()
    {
        ArrayList arraylist = mObservers;
        arraylist;
        JVM INSTR monitorenter ;
        mObservers.clear();
        arraylist;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void unregisterObserver(Object obj)
    {
        if(obj == null)
            throw new IllegalArgumentException("The observer is null.");
        ArrayList arraylist = mObservers;
        arraylist;
        JVM INSTR monitorenter ;
        int i = mObservers.indexOf(obj);
        if(i != -1)
            break MISSING_BLOCK_LABEL_82;
        IllegalStateException illegalstateexception = JVM INSTR new #33  <Class IllegalStateException>;
        StringBuilder stringbuilder = JVM INSTR new #35  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        illegalstateexception.IllegalStateException(stringbuilder.append("Observer ").append(obj).append(" was not registered.").toString());
        throw illegalstateexception;
        obj;
        arraylist;
        JVM INSTR monitorexit ;
        throw obj;
        mObservers.remove(i);
        arraylist;
        JVM INSTR monitorexit ;
    }

    protected final ArrayList mObservers = new ArrayList();
}

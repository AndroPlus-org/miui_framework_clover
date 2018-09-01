// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;

import java.util.ArrayList;

// Referenced classes of package android.database:
//            Observable, DataSetObserver

public class DataSetObservable extends Observable
{

    public DataSetObservable()
    {
    }

    public void notifyChanged()
    {
        ArrayList arraylist = mObservers;
        arraylist;
        JVM INSTR monitorenter ;
        int i = mObservers.size() - 1;
_L2:
        if(i < 0)
            break; /* Loop/switch isn't completed */
        ((DataSetObserver)mObservers.get(i)).onChanged();
        i--;
        if(true) goto _L2; else goto _L1
_L1:
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void notifyInvalidated()
    {
        ArrayList arraylist = mObservers;
        arraylist;
        JVM INSTR monitorenter ;
        int i = mObservers.size() - 1;
_L2:
        if(i < 0)
            break; /* Loop/switch isn't completed */
        ((DataSetObserver)mObservers.get(i)).onInvalidated();
        i--;
        if(true) goto _L2; else goto _L1
_L1:
        return;
        Exception exception;
        exception;
        throw exception;
    }
}

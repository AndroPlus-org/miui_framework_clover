// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;

import android.net.Uri;
import java.util.Iterator;

// Referenced classes of package android.database:
//            Observable, ContentObserver

public class ContentObservable extends Observable
{

    public ContentObservable()
    {
    }

    public void dispatchChange(boolean flag)
    {
        dispatchChange(flag, null);
    }

    public void dispatchChange(boolean flag, Uri uri)
    {
        java.util.ArrayList arraylist = mObservers;
        arraylist;
        JVM INSTR monitorenter ;
        Iterator iterator = mObservers.iterator();
_L3:
        ContentObserver contentobserver;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_67;
        contentobserver = (ContentObserver)iterator.next();
        if(!flag) goto _L2; else goto _L1
_L1:
        if(!contentobserver.deliverSelfNotifications()) goto _L3; else goto _L2
_L2:
        contentobserver.dispatchChange(flag, uri);
          goto _L3
        uri;
        throw uri;
        arraylist;
        JVM INSTR monitorexit ;
    }

    public void notifyChange(boolean flag)
    {
        java.util.ArrayList arraylist = mObservers;
        arraylist;
        JVM INSTR monitorenter ;
        for(Iterator iterator = mObservers.iterator(); iterator.hasNext(); ((ContentObserver)iterator.next()).onChange(flag, null));
        break MISSING_BLOCK_LABEL_48;
        Exception exception;
        exception;
        throw exception;
        arraylist;
        JVM INSTR monitorexit ;
    }

    public void registerObserver(ContentObserver contentobserver)
    {
        super.registerObserver(contentobserver);
    }

    public volatile void registerObserver(Object obj)
    {
        registerObserver((ContentObserver)obj);
    }
}

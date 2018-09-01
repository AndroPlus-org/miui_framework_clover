// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import java.util.ArrayList;

// Referenced classes of package android.os:
//            Registrant, AsyncResult, Handler

public class RegistrantList
{

    public RegistrantList()
    {
        registrants = new ArrayList();
    }

    private void internalNotifyRegistrants(Object obj, Throwable throwable)
    {
        this;
        JVM INSTR monitorenter ;
        int i = 0;
        int j = registrants.size();
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        ((Registrant)registrants.get(i)).internalNotifyRegistrant(obj, throwable);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        return;
        obj;
        throw obj;
    }

    public void add(Handler handler, int i, Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        Registrant registrant = JVM INSTR new #28  <Class Registrant>;
        registrant.Registrant(handler, i, obj);
        add(registrant);
        this;
        JVM INSTR monitorexit ;
        return;
        handler;
        throw handler;
    }

    public void add(Registrant registrant)
    {
        this;
        JVM INSTR monitorenter ;
        removeCleared();
        registrants.add(registrant);
        this;
        JVM INSTR monitorexit ;
        return;
        registrant;
        throw registrant;
    }

    public void addUnique(Handler handler, int i, Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        remove(handler);
        Registrant registrant = JVM INSTR new #28  <Class Registrant>;
        registrant.Registrant(handler, i, obj);
        add(registrant);
        this;
        JVM INSTR monitorexit ;
        return;
        handler;
        throw handler;
    }

    public Object get(int i)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = registrants.get(i);
        this;
        JVM INSTR monitorexit ;
        return obj;
        Exception exception;
        exception;
        throw exception;
    }

    public void notifyException(Throwable throwable)
    {
        internalNotifyRegistrants(null, throwable);
    }

    public void notifyRegistrants()
    {
        internalNotifyRegistrants(null, null);
    }

    public void notifyRegistrants(AsyncResult asyncresult)
    {
        internalNotifyRegistrants(asyncresult.result, asyncresult.exception);
    }

    public void notifyResult(Object obj)
    {
        internalNotifyRegistrants(obj, null);
    }

    public void remove(Handler handler)
    {
        this;
        JVM INSTR monitorenter ;
        int i = 0;
        int j = registrants.size();
_L3:
        if(i >= j) goto _L2; else goto _L1
_L1:
        Registrant registrant;
        Handler handler1;
        registrant = (Registrant)registrants.get(i);
        handler1 = registrant.getHandler();
        if(handler1 != null && handler1 != handler)
            continue; /* Loop/switch isn't completed */
        registrant.clear();
        i++;
          goto _L3
_L2:
        removeCleared();
        this;
        JVM INSTR monitorexit ;
        return;
        handler;
        throw handler;
    }

    public void removeCleared()
    {
        this;
        JVM INSTR monitorenter ;
        int i = registrants.size() - 1;
_L3:
        if(i < 0) goto _L2; else goto _L1
_L1:
        if(((Registrant)registrants.get(i)).refH == null)
            registrants.remove(i);
        i--;
          goto _L3
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public int size()
    {
        this;
        JVM INSTR monitorenter ;
        int i = registrants.size();
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    ArrayList registrants;
}

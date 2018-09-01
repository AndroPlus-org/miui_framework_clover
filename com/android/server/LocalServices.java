// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server;

import android.util.ArrayMap;

public final class LocalServices
{

    private LocalServices()
    {
    }

    public static void addService(Class class1, Object obj)
    {
        synchronized(sLocalServiceObjects)
        {
            if(sLocalServiceObjects.containsKey(class1))
            {
                class1 = JVM INSTR new #26  <Class IllegalStateException>;
                class1.IllegalStateException("Overriding service registration");
                throw class1;
            }
            break MISSING_BLOCK_LABEL_33;
        }
        sLocalServiceObjects.put(class1, obj);
        arraymap;
        JVM INSTR monitorexit ;
    }

    public static Object getService(Class class1)
    {
        ArrayMap arraymap = sLocalServiceObjects;
        arraymap;
        JVM INSTR monitorenter ;
        class1 = ((Class) (sLocalServiceObjects.get(class1)));
        arraymap;
        JVM INSTR monitorexit ;
        return class1;
        class1;
        throw class1;
    }

    public static void removeServiceForTest(Class class1)
    {
        ArrayMap arraymap = sLocalServiceObjects;
        arraymap;
        JVM INSTR monitorenter ;
        sLocalServiceObjects.remove(class1);
        arraymap;
        JVM INSTR monitorexit ;
        return;
        class1;
        throw class1;
    }

    private static final ArrayMap sLocalServiceObjects = new ArrayMap();

}

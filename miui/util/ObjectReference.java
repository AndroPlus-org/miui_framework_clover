// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;


public final class ObjectReference
{

    public ObjectReference(Object obj)
    {
        mObject = obj;
    }

    public final Object get()
    {
        return mObject;
    }

    private final Object mObject;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.builder;


final class IDKey
{

    public IDKey(Object obj)
    {
        id = System.identityHashCode(obj);
        value = obj;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(!(obj instanceof IDKey))
            return false;
        obj = (IDKey)obj;
        if(id != ((IDKey) (obj)).id)
            return false;
        if(value == ((IDKey) (obj)).value)
            flag = true;
        return flag;
    }

    public int hashCode()
    {
        return id;
    }

    private final int id;
    private final Object value;
}

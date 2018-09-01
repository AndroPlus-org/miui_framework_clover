// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.mutable;

import java.io.Serializable;

// Referenced classes of package org.apache.miui.commons.lang3.mutable:
//            Mutable

public class MutableObject
    implements Mutable, Serializable
{

    public MutableObject()
    {
    }

    public MutableObject(Object obj)
    {
        value = obj;
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(getClass() == obj.getClass())
        {
            obj = (MutableObject)obj;
            return value.equals(((MutableObject) (obj)).value);
        } else
        {
            return false;
        }
    }

    public Object getValue()
    {
        return value;
    }

    public int hashCode()
    {
        int i;
        if(value == null)
            i = 0;
        else
            i = value.hashCode();
        return i;
    }

    public void setValue(Object obj)
    {
        value = obj;
    }

    public String toString()
    {
        String s;
        if(value == null)
            s = "null";
        else
            s = value.toString();
        return s;
    }

    private static final long serialVersionUID = 0x14146a94f5L;
    private Object value;
}

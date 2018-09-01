// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.mutable;

import java.io.Serializable;

// Referenced classes of package org.apache.miui.commons.lang3.mutable:
//            Mutable

public class MutableBoolean
    implements Mutable, Serializable, Comparable
{

    public MutableBoolean()
    {
    }

    public MutableBoolean(Boolean boolean1)
    {
        value = boolean1.booleanValue();
    }

    public MutableBoolean(boolean flag)
    {
        value = flag;
    }

    public boolean booleanValue()
    {
        return value;
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((MutableBoolean)obj);
    }

    public int compareTo(MutableBoolean mutableboolean)
    {
        boolean flag = mutableboolean.value;
        int i;
        if(value == flag)
            i = 0;
        else
        if(value)
            i = 1;
        else
            i = -1;
        return i;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof MutableBoolean)
        {
            if(value == ((MutableBoolean)obj).booleanValue())
                flag = true;
            return flag;
        } else
        {
            return false;
        }
    }

    public Boolean getValue()
    {
        return Boolean.valueOf(value);
    }

    public volatile Object getValue()
    {
        return getValue();
    }

    public int hashCode()
    {
        int i;
        if(value)
            i = Boolean.TRUE.hashCode();
        else
            i = Boolean.FALSE.hashCode();
        return i;
    }

    public boolean isFalse()
    {
        boolean flag;
        if(!value)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isTrue()
    {
        boolean flag;
        if(value)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void setValue(Boolean boolean1)
    {
        value = boolean1.booleanValue();
    }

    public volatile void setValue(Object obj)
    {
        setValue((Boolean)obj);
    }

    public void setValue(boolean flag)
    {
        value = flag;
    }

    public Boolean toBoolean()
    {
        return Boolean.valueOf(booleanValue());
    }

    public String toString()
    {
        return String.valueOf(value);
    }

    private static final long serialVersionUID = 0xbcf5ce5a3a90e379L;
    private boolean value;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.mutable;


// Referenced classes of package org.apache.miui.commons.lang3.mutable:
//            Mutable

public class MutableLong extends Number
    implements Comparable, Mutable
{

    public MutableLong()
    {
    }

    public MutableLong(long l)
    {
        value = l;
    }

    public MutableLong(Number number)
    {
        value = number.longValue();
    }

    public MutableLong(String s)
        throws NumberFormatException
    {
        value = Long.parseLong(s);
    }

    public void add(long l)
    {
        value = value + l;
    }

    public void add(Number number)
    {
        value = value + number.longValue();
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((MutableLong)obj);
    }

    public int compareTo(MutableLong mutablelong)
    {
        long l = mutablelong.value;
        byte byte0;
        if(value < l)
            byte0 = -1;
        else
        if(value == l)
            byte0 = 0;
        else
            byte0 = 1;
        return byte0;
    }

    public void decrement()
    {
        value = value - 1L;
    }

    public double doubleValue()
    {
        return (double)value;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof MutableLong)
        {
            if(value == ((MutableLong)obj).longValue())
                flag = true;
            return flag;
        } else
        {
            return false;
        }
    }

    public float floatValue()
    {
        return (float)value;
    }

    public Long getValue()
    {
        return Long.valueOf(value);
    }

    public volatile Object getValue()
    {
        return getValue();
    }

    public int hashCode()
    {
        return (int)(value ^ value >>> 32);
    }

    public void increment()
    {
        value = value + 1L;
    }

    public int intValue()
    {
        return (int)value;
    }

    public long longValue()
    {
        return value;
    }

    public void setValue(long l)
    {
        value = l;
    }

    public void setValue(Number number)
    {
        value = number.longValue();
    }

    public volatile void setValue(Object obj)
    {
        setValue((Number)obj);
    }

    public void subtract(long l)
    {
        value = value - l;
    }

    public void subtract(Number number)
    {
        value = value - number.longValue();
    }

    public Long toLong()
    {
        return Long.valueOf(longValue());
    }

    public String toString()
    {
        return String.valueOf(value);
    }

    private static final long serialVersionUID = 0xeaa4a2677L;
    private long value;
}

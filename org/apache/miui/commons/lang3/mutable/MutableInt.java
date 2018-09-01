// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.mutable;


// Referenced classes of package org.apache.miui.commons.lang3.mutable:
//            Mutable

public class MutableInt extends Number
    implements Comparable, Mutable
{

    public MutableInt()
    {
    }

    public MutableInt(int i)
    {
        value = i;
    }

    public MutableInt(Number number)
    {
        value = number.intValue();
    }

    public MutableInt(String s)
        throws NumberFormatException
    {
        value = Integer.parseInt(s);
    }

    public void add(int i)
    {
        value = value + i;
    }

    public void add(Number number)
    {
        value = value + number.intValue();
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((MutableInt)obj);
    }

    public int compareTo(MutableInt mutableint)
    {
        int i = mutableint.value;
        if(value < i)
            i = -1;
        else
        if(value == i)
            i = 0;
        else
            i = 1;
        return i;
    }

    public void decrement()
    {
        value = value - 1;
    }

    public double doubleValue()
    {
        return (double)value;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof MutableInt)
        {
            if(value == ((MutableInt)obj).intValue())
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

    public Integer getValue()
    {
        return Integer.valueOf(value);
    }

    public volatile Object getValue()
    {
        return getValue();
    }

    public int hashCode()
    {
        return value;
    }

    public void increment()
    {
        value = value + 1;
    }

    public int intValue()
    {
        return value;
    }

    public long longValue()
    {
        return (long)value;
    }

    public void setValue(int i)
    {
        value = i;
    }

    public void setValue(Number number)
    {
        value = number.intValue();
    }

    public volatile void setValue(Object obj)
    {
        setValue((Number)obj);
    }

    public void subtract(int i)
    {
        value = value - i;
    }

    public void subtract(Number number)
    {
        value = value - number.intValue();
    }

    public Integer toInteger()
    {
        return Integer.valueOf(intValue());
    }

    public String toString()
    {
        return String.valueOf(value);
    }

    private static final long serialVersionUID = 0x77401786b8L;
    private int value;
}

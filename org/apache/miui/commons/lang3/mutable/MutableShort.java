// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.mutable;


// Referenced classes of package org.apache.miui.commons.lang3.mutable:
//            Mutable

public class MutableShort extends Number
    implements Comparable, Mutable
{

    public MutableShort()
    {
    }

    public MutableShort(Number number)
    {
        value = number.shortValue();
    }

    public MutableShort(String s)
        throws NumberFormatException
    {
        value = Short.parseShort(s);
    }

    public MutableShort(short word0)
    {
        value = word0;
    }

    public void add(Number number)
    {
        value = (short)(value + number.shortValue());
    }

    public void add(short word0)
    {
        value = (short)(value + word0);
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((MutableShort)obj);
    }

    public int compareTo(MutableShort mutableshort)
    {
        short word0 = mutableshort.value;
        if(value < word0)
            word0 = -1;
        else
        if(value == word0)
            word0 = 0;
        else
            word0 = 1;
        return word0;
    }

    public void decrement()
    {
        value = (short)(value - 1);
    }

    public double doubleValue()
    {
        return (double)value;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof MutableShort)
        {
            if(value == ((MutableShort)obj).shortValue())
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

    public volatile Object getValue()
    {
        return getValue();
    }

    public Short getValue()
    {
        return Short.valueOf(value);
    }

    public int hashCode()
    {
        return value;
    }

    public void increment()
    {
        value = (short)(value + 1);
    }

    public int intValue()
    {
        return value;
    }

    public long longValue()
    {
        return (long)value;
    }

    public void setValue(Number number)
    {
        value = number.shortValue();
    }

    public volatile void setValue(Object obj)
    {
        setValue((Number)obj);
    }

    public void setValue(short word0)
    {
        value = word0;
    }

    public short shortValue()
    {
        return value;
    }

    public void subtract(Number number)
    {
        value = (short)(value - number.shortValue());
    }

    public void subtract(short word0)
    {
        value = (short)(value - word0);
    }

    public Short toShort()
    {
        return Short.valueOf(shortValue());
    }

    public String toString()
    {
        return String.valueOf(value);
    }

    private static final long serialVersionUID = 0xffffffff80b267c1L;
    private short value;
}

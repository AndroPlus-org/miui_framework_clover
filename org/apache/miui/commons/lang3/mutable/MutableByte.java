// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.mutable;


// Referenced classes of package org.apache.miui.commons.lang3.mutable:
//            Mutable

public class MutableByte extends Number
    implements Comparable, Mutable
{

    public MutableByte()
    {
    }

    public MutableByte(byte byte0)
    {
        value = byte0;
    }

    public MutableByte(Number number)
    {
        value = number.byteValue();
    }

    public MutableByte(String s)
        throws NumberFormatException
    {
        value = Byte.parseByte(s);
    }

    public void add(byte byte0)
    {
        value = (byte)(value + byte0);
    }

    public void add(Number number)
    {
        value = (byte)(value + number.byteValue());
    }

    public byte byteValue()
    {
        return value;
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((MutableByte)obj);
    }

    public int compareTo(MutableByte mutablebyte)
    {
        byte byte0 = mutablebyte.value;
        if(value < byte0)
            byte0 = -1;
        else
        if(value == byte0)
            byte0 = 0;
        else
            byte0 = 1;
        return byte0;
    }

    public void decrement()
    {
        value = (byte)(value - 1);
    }

    public double doubleValue()
    {
        return (double)value;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof MutableByte)
        {
            if(value == ((MutableByte)obj).byteValue())
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

    public Byte getValue()
    {
        return Byte.valueOf(value);
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
        value = (byte)(value + 1);
    }

    public int intValue()
    {
        return value;
    }

    public long longValue()
    {
        return (long)value;
    }

    public void setValue(byte byte0)
    {
        value = byte0;
    }

    public void setValue(Number number)
    {
        value = number.byteValue();
    }

    public volatile void setValue(Object obj)
    {
        setValue((Number)obj);
    }

    public void subtract(byte byte0)
    {
        value = (byte)(value - byte0);
    }

    public void subtract(Number number)
    {
        value = (byte)(value - number.byteValue());
    }

    public Byte toByte()
    {
        return Byte.valueOf(byteValue());
    }

    public String toString()
    {
        return String.valueOf(value);
    }

    private static final long serialVersionUID = 0xffffffffa17a41dfL;
    private byte value;
}

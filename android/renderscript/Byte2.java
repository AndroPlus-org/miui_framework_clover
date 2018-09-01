// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


public class Byte2
{

    public Byte2()
    {
    }

    public Byte2(byte byte0, byte byte1)
    {
        x = byte0;
        y = byte1;
    }

    public Byte2(Byte2 byte2)
    {
        x = byte2.x;
        y = byte2.y;
    }

    public static Byte2 add(Byte2 byte2, byte byte0)
    {
        Byte2 byte2_1 = new Byte2();
        byte2_1.x = (byte)(byte2.x + byte0);
        byte2_1.y = (byte)(byte2.y + byte0);
        return byte2_1;
    }

    public static Byte2 add(Byte2 byte2, Byte2 byte2_1)
    {
        Byte2 byte2_2 = new Byte2();
        byte2_2.x = (byte)(byte2.x + byte2_1.x);
        byte2_2.y = (byte)(byte2.y + byte2_1.y);
        return byte2_2;
    }

    public static Byte2 div(Byte2 byte2, byte byte0)
    {
        Byte2 byte2_1 = new Byte2();
        byte2_1.x = (byte)(byte2.x / byte0);
        byte2_1.y = (byte)(byte2.y / byte0);
        return byte2_1;
    }

    public static Byte2 div(Byte2 byte2, Byte2 byte2_1)
    {
        Byte2 byte2_2 = new Byte2();
        byte2_2.x = (byte)(byte2.x / byte2_1.x);
        byte2_2.y = (byte)(byte2.y / byte2_1.y);
        return byte2_2;
    }

    public static byte dotProduct(Byte2 byte2, Byte2 byte2_1)
    {
        return (byte)(byte2_1.x * byte2.x + byte2_1.y * byte2.y);
    }

    public static Byte2 mul(Byte2 byte2, byte byte0)
    {
        Byte2 byte2_1 = new Byte2();
        byte2_1.x = (byte)(byte2.x * byte0);
        byte2_1.y = (byte)(byte2.y * byte0);
        return byte2_1;
    }

    public static Byte2 mul(Byte2 byte2, Byte2 byte2_1)
    {
        Byte2 byte2_2 = new Byte2();
        byte2_2.x = (byte)(byte2.x * byte2_1.x);
        byte2_2.y = (byte)(byte2.y * byte2_1.y);
        return byte2_2;
    }

    public static Byte2 sub(Byte2 byte2, byte byte0)
    {
        Byte2 byte2_1 = new Byte2();
        byte2_1.x = (byte)(byte2.x - byte0);
        byte2_1.y = (byte)(byte2.y - byte0);
        return byte2_1;
    }

    public static Byte2 sub(Byte2 byte2, Byte2 byte2_1)
    {
        Byte2 byte2_2 = new Byte2();
        byte2_2.x = (byte)(byte2.x - byte2_1.x);
        byte2_2.y = (byte)(byte2.y - byte2_1.y);
        return byte2_2;
    }

    public void add(byte byte0)
    {
        x = (byte)(x + byte0);
        y = (byte)(y + byte0);
    }

    public void add(Byte2 byte2)
    {
        x = (byte)(x + byte2.x);
        y = (byte)(y + byte2.y);
    }

    public void addAt(int i, byte byte0)
    {
        switch(i)
        {
        default:
            throw new IndexOutOfBoundsException("Index: i");

        case 0: // '\0'
            x = (byte)(x + byte0);
            return;

        case 1: // '\001'
            y = (byte)(y + byte0);
            break;
        }
    }

    public void addMultiple(Byte2 byte2, byte byte0)
    {
        x = (byte)(x + byte2.x * byte0);
        y = (byte)(y + byte2.y * byte0);
    }

    public void copyTo(byte abyte0[], int i)
    {
        abyte0[i] = x;
        abyte0[i + 1] = y;
    }

    public void div(byte byte0)
    {
        x = (byte)(x / byte0);
        y = (byte)(y / byte0);
    }

    public void div(Byte2 byte2)
    {
        x = (byte)(x / byte2.x);
        y = (byte)(y / byte2.y);
    }

    public byte dotProduct(Byte2 byte2)
    {
        return (byte)(x * byte2.x + y * byte2.y);
    }

    public byte elementSum()
    {
        return (byte)(x + y);
    }

    public byte get(int i)
    {
        switch(i)
        {
        default:
            throw new IndexOutOfBoundsException("Index: i");

        case 0: // '\0'
            return x;

        case 1: // '\001'
            return y;
        }
    }

    public byte length()
    {
        return 2;
    }

    public void mul(byte byte0)
    {
        x = (byte)(x * byte0);
        y = (byte)(y * byte0);
    }

    public void mul(Byte2 byte2)
    {
        x = (byte)(x * byte2.x);
        y = (byte)(y * byte2.y);
    }

    public void negate()
    {
        x = (byte)(-x);
        y = (byte)(-y);
    }

    public void set(Byte2 byte2)
    {
        x = byte2.x;
        y = byte2.y;
    }

    public void setAt(int i, byte byte0)
    {
        switch(i)
        {
        default:
            throw new IndexOutOfBoundsException("Index: i");

        case 0: // '\0'
            x = byte0;
            return;

        case 1: // '\001'
            y = byte0;
            break;
        }
    }

    public void setValues(byte byte0, byte byte1)
    {
        x = byte0;
        y = byte1;
    }

    public void sub(byte byte0)
    {
        x = (byte)(x - byte0);
        y = (byte)(y - byte0);
    }

    public void sub(Byte2 byte2)
    {
        x = (byte)(x - byte2.x);
        y = (byte)(y - byte2.y);
    }

    public byte x;
    public byte y;
}

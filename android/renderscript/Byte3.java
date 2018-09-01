// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


public class Byte3
{

    public Byte3()
    {
    }

    public Byte3(byte byte0, byte byte1, byte byte2)
    {
        x = byte0;
        y = byte1;
        z = byte2;
    }

    public Byte3(Byte3 byte3)
    {
        x = byte3.x;
        y = byte3.y;
        z = byte3.z;
    }

    public static Byte3 add(Byte3 byte3, byte byte0)
    {
        Byte3 byte3_1 = new Byte3();
        byte3_1.x = (byte)(byte3.x + byte0);
        byte3_1.y = (byte)(byte3.y + byte0);
        byte3_1.z = (byte)(byte3.z + byte0);
        return byte3_1;
    }

    public static Byte3 add(Byte3 byte3, Byte3 byte3_1)
    {
        Byte3 byte3_2 = new Byte3();
        byte3_2.x = (byte)(byte3.x + byte3_1.x);
        byte3_2.y = (byte)(byte3.y + byte3_1.y);
        byte3_2.z = (byte)(byte3.z + byte3_1.z);
        return byte3_2;
    }

    public static Byte3 div(Byte3 byte3, byte byte0)
    {
        Byte3 byte3_1 = new Byte3();
        byte3_1.x = (byte)(byte3.x / byte0);
        byte3_1.y = (byte)(byte3.y / byte0);
        byte3_1.z = (byte)(byte3.z / byte0);
        return byte3_1;
    }

    public static Byte3 div(Byte3 byte3, Byte3 byte3_1)
    {
        Byte3 byte3_2 = new Byte3();
        byte3_2.x = (byte)(byte3.x / byte3_1.x);
        byte3_2.y = (byte)(byte3.y / byte3_1.y);
        byte3_2.z = (byte)(byte3.z / byte3_1.z);
        return byte3_2;
    }

    public static byte dotProduct(Byte3 byte3, Byte3 byte3_1)
    {
        return (byte)((byte)((byte)(byte3_1.x * byte3.x) + (byte)(byte3_1.y * byte3.y)) + (byte)(byte3_1.z * byte3.z));
    }

    public static Byte3 mul(Byte3 byte3, byte byte0)
    {
        Byte3 byte3_1 = new Byte3();
        byte3_1.x = (byte)(byte3.x * byte0);
        byte3_1.y = (byte)(byte3.y * byte0);
        byte3_1.z = (byte)(byte3.z * byte0);
        return byte3_1;
    }

    public static Byte3 mul(Byte3 byte3, Byte3 byte3_1)
    {
        Byte3 byte3_2 = new Byte3();
        byte3_2.x = (byte)(byte3.x * byte3_1.x);
        byte3_2.y = (byte)(byte3.y * byte3_1.y);
        byte3_2.z = (byte)(byte3.z * byte3_1.z);
        return byte3_2;
    }

    public static Byte3 sub(Byte3 byte3, byte byte0)
    {
        Byte3 byte3_1 = new Byte3();
        byte3_1.x = (byte)(byte3.x - byte0);
        byte3_1.y = (byte)(byte3.y - byte0);
        byte3_1.z = (byte)(byte3.z - byte0);
        return byte3_1;
    }

    public static Byte3 sub(Byte3 byte3, Byte3 byte3_1)
    {
        Byte3 byte3_2 = new Byte3();
        byte3_2.x = (byte)(byte3.x - byte3_1.x);
        byte3_2.y = (byte)(byte3.y - byte3_1.y);
        byte3_2.z = (byte)(byte3.z - byte3_1.z);
        return byte3_2;
    }

    public void add(byte byte0)
    {
        x = (byte)(x + byte0);
        y = (byte)(y + byte0);
        z = (byte)(z + byte0);
    }

    public void add(Byte3 byte3)
    {
        x = (byte)(x + byte3.x);
        y = (byte)(y + byte3.y);
        z = (byte)(z + byte3.z);
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
            return;

        case 2: // '\002'
            z = (byte)(z + byte0);
            break;
        }
    }

    public void addMultiple(Byte3 byte3, byte byte0)
    {
        x = (byte)(x + byte3.x * byte0);
        y = (byte)(y + byte3.y * byte0);
        z = (byte)(z + byte3.z * byte0);
    }

    public void copyTo(byte abyte0[], int i)
    {
        abyte0[i] = x;
        abyte0[i + 1] = y;
        abyte0[i + 2] = z;
    }

    public void div(byte byte0)
    {
        x = (byte)(x / byte0);
        y = (byte)(y / byte0);
        z = (byte)(z / byte0);
    }

    public void div(Byte3 byte3)
    {
        x = (byte)(x / byte3.x);
        y = (byte)(y / byte3.y);
        z = (byte)(z / byte3.z);
    }

    public byte dotProduct(Byte3 byte3)
    {
        return (byte)((byte)((byte)(x * byte3.x) + (byte)(y * byte3.y)) + (byte)(z * byte3.z));
    }

    public byte elementSum()
    {
        return (byte)(x + y + z);
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

        case 2: // '\002'
            return z;
        }
    }

    public byte length()
    {
        return 3;
    }

    public void mul(byte byte0)
    {
        x = (byte)(x * byte0);
        y = (byte)(y * byte0);
        z = (byte)(z * byte0);
    }

    public void mul(Byte3 byte3)
    {
        x = (byte)(x * byte3.x);
        y = (byte)(y * byte3.y);
        z = (byte)(z * byte3.z);
    }

    public void negate()
    {
        x = (byte)(-x);
        y = (byte)(-y);
        z = (byte)(-z);
    }

    public void set(Byte3 byte3)
    {
        x = byte3.x;
        y = byte3.y;
        z = byte3.z;
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
            return;

        case 2: // '\002'
            z = byte0;
            break;
        }
    }

    public void setValues(byte byte0, byte byte1, byte byte2)
    {
        x = byte0;
        y = byte1;
        z = byte2;
    }

    public void sub(byte byte0)
    {
        x = (byte)(x - byte0);
        y = (byte)(y - byte0);
        z = (byte)(z - byte0);
    }

    public void sub(Byte3 byte3)
    {
        x = (byte)(x - byte3.x);
        y = (byte)(y - byte3.y);
        z = (byte)(z - byte3.z);
    }

    public byte x;
    public byte y;
    public byte z;
}

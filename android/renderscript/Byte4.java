// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


public class Byte4
{

    public Byte4()
    {
    }

    public Byte4(byte byte0, byte byte1, byte byte2, byte byte3)
    {
        x = byte0;
        y = byte1;
        z = byte2;
        w = byte3;
    }

    public Byte4(Byte4 byte4)
    {
        x = byte4.x;
        y = byte4.y;
        z = byte4.z;
        w = byte4.w;
    }

    public static Byte4 add(Byte4 byte4, byte byte0)
    {
        Byte4 byte4_1 = new Byte4();
        byte4_1.x = (byte)(byte4.x + byte0);
        byte4_1.y = (byte)(byte4.y + byte0);
        byte4_1.z = (byte)(byte4.z + byte0);
        byte4_1.w = (byte)(byte4.w + byte0);
        return byte4_1;
    }

    public static Byte4 add(Byte4 byte4, Byte4 byte4_1)
    {
        Byte4 byte4_2 = new Byte4();
        byte4_2.x = (byte)(byte4.x + byte4_1.x);
        byte4_2.y = (byte)(byte4.y + byte4_1.y);
        byte4_2.z = (byte)(byte4.z + byte4_1.z);
        byte4_2.w = (byte)(byte4.w + byte4_1.w);
        return byte4_2;
    }

    public static Byte4 div(Byte4 byte4, byte byte0)
    {
        Byte4 byte4_1 = new Byte4();
        byte4_1.x = (byte)(byte4.x / byte0);
        byte4_1.y = (byte)(byte4.y / byte0);
        byte4_1.z = (byte)(byte4.z / byte0);
        byte4_1.w = (byte)(byte4.w / byte0);
        return byte4_1;
    }

    public static Byte4 div(Byte4 byte4, Byte4 byte4_1)
    {
        Byte4 byte4_2 = new Byte4();
        byte4_2.x = (byte)(byte4.x / byte4_1.x);
        byte4_2.y = (byte)(byte4.y / byte4_1.y);
        byte4_2.z = (byte)(byte4.z / byte4_1.z);
        byte4_2.w = (byte)(byte4.w / byte4_1.w);
        return byte4_2;
    }

    public static byte dotProduct(Byte4 byte4, Byte4 byte4_1)
    {
        return (byte)(byte4_1.x * byte4.x + byte4_1.y * byte4.y + byte4_1.z * byte4.z + byte4_1.w * byte4.w);
    }

    public static Byte4 mul(Byte4 byte4, byte byte0)
    {
        Byte4 byte4_1 = new Byte4();
        byte4_1.x = (byte)(byte4.x * byte0);
        byte4_1.y = (byte)(byte4.y * byte0);
        byte4_1.z = (byte)(byte4.z * byte0);
        byte4_1.w = (byte)(byte4.w * byte0);
        return byte4_1;
    }

    public static Byte4 mul(Byte4 byte4, Byte4 byte4_1)
    {
        Byte4 byte4_2 = new Byte4();
        byte4_2.x = (byte)(byte4.x * byte4_1.x);
        byte4_2.y = (byte)(byte4.y * byte4_1.y);
        byte4_2.z = (byte)(byte4.z * byte4_1.z);
        byte4_2.w = (byte)(byte4.w * byte4_1.w);
        return byte4_2;
    }

    public static Byte4 sub(Byte4 byte4, byte byte0)
    {
        Byte4 byte4_1 = new Byte4();
        byte4_1.x = (byte)(byte4.x - byte0);
        byte4_1.y = (byte)(byte4.y - byte0);
        byte4_1.z = (byte)(byte4.z - byte0);
        byte4_1.w = (byte)(byte4.w - byte0);
        return byte4_1;
    }

    public static Byte4 sub(Byte4 byte4, Byte4 byte4_1)
    {
        Byte4 byte4_2 = new Byte4();
        byte4_2.x = (byte)(byte4.x - byte4_1.x);
        byte4_2.y = (byte)(byte4.y - byte4_1.y);
        byte4_2.z = (byte)(byte4.z - byte4_1.z);
        byte4_2.w = (byte)(byte4.w - byte4_1.w);
        return byte4_2;
    }

    public void add(byte byte0)
    {
        x = (byte)(x + byte0);
        y = (byte)(y + byte0);
        z = (byte)(z + byte0);
        w = (byte)(w + byte0);
    }

    public void add(Byte4 byte4)
    {
        x = (byte)(x + byte4.x);
        y = (byte)(y + byte4.y);
        z = (byte)(z + byte4.z);
        w = (byte)(w + byte4.w);
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
            return;

        case 3: // '\003'
            w = (byte)(w + byte0);
            break;
        }
    }

    public void addMultiple(Byte4 byte4, byte byte0)
    {
        x = (byte)(x + byte4.x * byte0);
        y = (byte)(y + byte4.y * byte0);
        z = (byte)(z + byte4.z * byte0);
        w = (byte)(w + byte4.w * byte0);
    }

    public void copyTo(byte abyte0[], int i)
    {
        abyte0[i] = x;
        abyte0[i + 1] = y;
        abyte0[i + 2] = z;
        abyte0[i + 3] = w;
    }

    public void div(byte byte0)
    {
        x = (byte)(x / byte0);
        y = (byte)(y / byte0);
        z = (byte)(z / byte0);
        w = (byte)(w / byte0);
    }

    public void div(Byte4 byte4)
    {
        x = (byte)(x / byte4.x);
        y = (byte)(y / byte4.y);
        z = (byte)(z / byte4.z);
        w = (byte)(w / byte4.w);
    }

    public byte dotProduct(Byte4 byte4)
    {
        return (byte)(x * byte4.x + y * byte4.y + z * byte4.z + w * byte4.w);
    }

    public byte elementSum()
    {
        return (byte)(x + y + z + w);
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

        case 3: // '\003'
            return w;
        }
    }

    public byte length()
    {
        return 4;
    }

    public void mul(byte byte0)
    {
        x = (byte)(x * byte0);
        y = (byte)(y * byte0);
        z = (byte)(z * byte0);
        w = (byte)(w * byte0);
    }

    public void mul(Byte4 byte4)
    {
        x = (byte)(x * byte4.x);
        y = (byte)(y * byte4.y);
        z = (byte)(z * byte4.z);
        w = (byte)(w * byte4.w);
    }

    public void negate()
    {
        x = (byte)(-x);
        y = (byte)(-y);
        z = (byte)(-z);
        w = (byte)(-w);
    }

    public void set(Byte4 byte4)
    {
        x = byte4.x;
        y = byte4.y;
        z = byte4.z;
        w = byte4.w;
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
            return;

        case 3: // '\003'
            w = byte0;
            break;
        }
    }

    public void setValues(byte byte0, byte byte1, byte byte2, byte byte3)
    {
        x = byte0;
        y = byte1;
        z = byte2;
        w = byte3;
    }

    public void sub(byte byte0)
    {
        x = (byte)(x - byte0);
        y = (byte)(y - byte0);
        z = (byte)(z - byte0);
        w = (byte)(w - byte0);
    }

    public void sub(Byte4 byte4)
    {
        x = (byte)(x - byte4.x);
        y = (byte)(y - byte4.y);
        z = (byte)(z - byte4.z);
        w = (byte)(w - byte4.w);
    }

    public byte w;
    public byte x;
    public byte y;
    public byte z;
}

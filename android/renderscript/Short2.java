// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


public class Short2
{

    public Short2()
    {
    }

    public Short2(Short2 short2)
    {
        x = short2.x;
        y = short2.y;
    }

    public Short2(short word0)
    {
        y = word0;
        x = word0;
    }

    public Short2(short word0, short word1)
    {
        x = word0;
        y = word1;
    }

    public static Short2 add(Short2 short2, Short2 short2_1)
    {
        Short2 short2_2 = new Short2();
        short2_2.x = (short)(short2.x + short2_1.x);
        short2_2.y = (short)(short2.y + short2_1.y);
        return short2_2;
    }

    public static Short2 add(Short2 short2, short word0)
    {
        Short2 short2_1 = new Short2();
        short2_1.x = (short)(short2.x + word0);
        short2_1.y = (short)(short2.y + word0);
        return short2_1;
    }

    public static Short2 div(Short2 short2, Short2 short2_1)
    {
        Short2 short2_2 = new Short2();
        short2_2.x = (short)(short2.x / short2_1.x);
        short2_2.y = (short)(short2.y / short2_1.y);
        return short2_2;
    }

    public static Short2 div(Short2 short2, short word0)
    {
        Short2 short2_1 = new Short2();
        short2_1.x = (short)(short2.x / word0);
        short2_1.y = (short)(short2.y / word0);
        return short2_1;
    }

    public static short dotProduct(Short2 short2, Short2 short2_1)
    {
        return (short)(short2_1.x * short2.x + short2_1.y * short2.y);
    }

    public static Short2 mod(Short2 short2, Short2 short2_1)
    {
        Short2 short2_2 = new Short2();
        short2_2.x = (short)(short2.x % short2_1.x);
        short2_2.y = (short)(short2.y % short2_1.y);
        return short2_2;
    }

    public static Short2 mod(Short2 short2, short word0)
    {
        Short2 short2_1 = new Short2();
        short2_1.x = (short)(short2.x % word0);
        short2_1.y = (short)(short2.y % word0);
        return short2_1;
    }

    public static Short2 mul(Short2 short2, Short2 short2_1)
    {
        Short2 short2_2 = new Short2();
        short2_2.x = (short)(short2.x * short2_1.x);
        short2_2.y = (short)(short2.y * short2_1.y);
        return short2_2;
    }

    public static Short2 mul(Short2 short2, short word0)
    {
        Short2 short2_1 = new Short2();
        short2_1.x = (short)(short2.x * word0);
        short2_1.y = (short)(short2.y * word0);
        return short2_1;
    }

    public static Short2 sub(Short2 short2, Short2 short2_1)
    {
        Short2 short2_2 = new Short2();
        short2_2.x = (short)(short2.x - short2_1.x);
        short2_2.y = (short)(short2.y - short2_1.y);
        return short2_2;
    }

    public static Short2 sub(Short2 short2, short word0)
    {
        Short2 short2_1 = new Short2();
        short2_1.x = (short)(short2.x - word0);
        short2_1.y = (short)(short2.y - word0);
        return short2_1;
    }

    public void add(Short2 short2)
    {
        x = (short)(x + short2.x);
        y = (short)(y + short2.y);
    }

    public void add(short word0)
    {
        x = (short)(x + word0);
        y = (short)(y + word0);
    }

    public void addAt(int i, short word0)
    {
        switch(i)
        {
        default:
            throw new IndexOutOfBoundsException("Index: i");

        case 0: // '\0'
            x = (short)(x + word0);
            return;

        case 1: // '\001'
            y = (short)(y + word0);
            break;
        }
    }

    public void addMultiple(Short2 short2, short word0)
    {
        x = (short)(x + short2.x * word0);
        y = (short)(y + short2.y * word0);
    }

    public void copyTo(short aword0[], int i)
    {
        aword0[i] = x;
        aword0[i + 1] = y;
    }

    public void div(Short2 short2)
    {
        x = (short)(x / short2.x);
        y = (short)(y / short2.y);
    }

    public void div(short word0)
    {
        x = (short)(x / word0);
        y = (short)(y / word0);
    }

    public short dotProduct(Short2 short2)
    {
        return (short)(x * short2.x + y * short2.y);
    }

    public short elementSum()
    {
        return (short)(x + y);
    }

    public short get(int i)
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

    public short length()
    {
        return 2;
    }

    public void mod(Short2 short2)
    {
        x = (short)(x % short2.x);
        y = (short)(y % short2.y);
    }

    public void mod(short word0)
    {
        x = (short)(x % word0);
        y = (short)(y % word0);
    }

    public void mul(Short2 short2)
    {
        x = (short)(x * short2.x);
        y = (short)(y * short2.y);
    }

    public void mul(short word0)
    {
        x = (short)(x * word0);
        y = (short)(y * word0);
    }

    public void negate()
    {
        x = (short)(-x);
        y = (short)(-y);
    }

    public void set(Short2 short2)
    {
        x = short2.x;
        y = short2.y;
    }

    public void setAt(int i, short word0)
    {
        switch(i)
        {
        default:
            throw new IndexOutOfBoundsException("Index: i");

        case 0: // '\0'
            x = word0;
            return;

        case 1: // '\001'
            y = word0;
            break;
        }
    }

    public void setValues(short word0, short word1)
    {
        x = word0;
        y = word1;
    }

    public void sub(Short2 short2)
    {
        x = (short)(x - short2.x);
        y = (short)(y - short2.y);
    }

    public void sub(short word0)
    {
        x = (short)(x - word0);
        y = (short)(y - word0);
    }

    public short x;
    public short y;
}

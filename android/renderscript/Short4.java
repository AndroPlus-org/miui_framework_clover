// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


public class Short4
{

    public Short4()
    {
    }

    public Short4(Short4 short4)
    {
        x = short4.x;
        y = short4.y;
        z = short4.z;
        w = short4.w;
    }

    public Short4(short word0)
    {
        w = word0;
        z = word0;
        y = word0;
        x = word0;
    }

    public Short4(short word0, short word1, short word2, short word3)
    {
        x = word0;
        y = word1;
        z = word2;
        w = word3;
    }

    public static Short4 add(Short4 short4, Short4 short4_1)
    {
        Short4 short4_2 = new Short4();
        short4_2.x = (short)(short4.x + short4_1.x);
        short4_2.y = (short)(short4.y + short4_1.y);
        short4_2.z = (short)(short4.z + short4_1.z);
        short4_2.w = (short)(short4.w + short4_1.w);
        return short4_2;
    }

    public static Short4 add(Short4 short4, short word0)
    {
        Short4 short4_1 = new Short4();
        short4_1.x = (short)(short4.x + word0);
        short4_1.y = (short)(short4.y + word0);
        short4_1.z = (short)(short4.z + word0);
        short4_1.w = (short)(short4.w + word0);
        return short4_1;
    }

    public static Short4 div(Short4 short4, Short4 short4_1)
    {
        Short4 short4_2 = new Short4();
        short4_2.x = (short)(short4.x / short4_1.x);
        short4_2.y = (short)(short4.y / short4_1.y);
        short4_2.z = (short)(short4.z / short4_1.z);
        short4_2.w = (short)(short4.w / short4_1.w);
        return short4_2;
    }

    public static Short4 div(Short4 short4, short word0)
    {
        Short4 short4_1 = new Short4();
        short4_1.x = (short)(short4.x / word0);
        short4_1.y = (short)(short4.y / word0);
        short4_1.z = (short)(short4.z / word0);
        short4_1.w = (short)(short4.w / word0);
        return short4_1;
    }

    public static short dotProduct(Short4 short4, Short4 short4_1)
    {
        return (short)(short4_1.x * short4.x + short4_1.y * short4.y + short4_1.z * short4.z + short4_1.w * short4.w);
    }

    public static Short4 mod(Short4 short4, Short4 short4_1)
    {
        Short4 short4_2 = new Short4();
        short4_2.x = (short)(short4.x % short4_1.x);
        short4_2.y = (short)(short4.y % short4_1.y);
        short4_2.z = (short)(short4.z % short4_1.z);
        short4_2.w = (short)(short4.w % short4_1.w);
        return short4_2;
    }

    public static Short4 mod(Short4 short4, short word0)
    {
        Short4 short4_1 = new Short4();
        short4_1.x = (short)(short4.x % word0);
        short4_1.y = (short)(short4.y % word0);
        short4_1.z = (short)(short4.z % word0);
        short4_1.w = (short)(short4.w % word0);
        return short4_1;
    }

    public static Short4 mul(Short4 short4, Short4 short4_1)
    {
        Short4 short4_2 = new Short4();
        short4_2.x = (short)(short4.x * short4_1.x);
        short4_2.y = (short)(short4.y * short4_1.y);
        short4_2.z = (short)(short4.z * short4_1.z);
        short4_2.w = (short)(short4.w * short4_1.w);
        return short4_2;
    }

    public static Short4 mul(Short4 short4, short word0)
    {
        Short4 short4_1 = new Short4();
        short4_1.x = (short)(short4.x * word0);
        short4_1.y = (short)(short4.y * word0);
        short4_1.z = (short)(short4.z * word0);
        short4_1.w = (short)(short4.w * word0);
        return short4_1;
    }

    public static Short4 sub(Short4 short4, Short4 short4_1)
    {
        Short4 short4_2 = new Short4();
        short4_2.x = (short)(short4.x - short4_1.x);
        short4_2.y = (short)(short4.y - short4_1.y);
        short4_2.z = (short)(short4.z - short4_1.z);
        short4_2.w = (short)(short4.w - short4_1.w);
        return short4_2;
    }

    public static Short4 sub(Short4 short4, short word0)
    {
        Short4 short4_1 = new Short4();
        short4_1.x = (short)(short4.x - word0);
        short4_1.y = (short)(short4.y - word0);
        short4_1.z = (short)(short4.z - word0);
        short4_1.w = (short)(short4.w - word0);
        return short4_1;
    }

    public void add(Short4 short4)
    {
        x = (short)(x + short4.x);
        y = (short)(y + short4.y);
        z = (short)(z + short4.z);
        w = (short)(w + short4.w);
    }

    public void add(short word0)
    {
        x = (short)(x + word0);
        y = (short)(y + word0);
        z = (short)(z + word0);
        w = (short)(w + word0);
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
            return;

        case 2: // '\002'
            z = (short)(z + word0);
            return;

        case 3: // '\003'
            w = (short)(w + word0);
            break;
        }
    }

    public void addMultiple(Short4 short4, short word0)
    {
        x = (short)(x + short4.x * word0);
        y = (short)(y + short4.y * word0);
        z = (short)(z + short4.z * word0);
        w = (short)(w + short4.w * word0);
    }

    public void copyTo(short aword0[], int i)
    {
        aword0[i] = x;
        aword0[i + 1] = y;
        aword0[i + 2] = z;
        aword0[i + 3] = w;
    }

    public void div(Short4 short4)
    {
        x = (short)(x / short4.x);
        y = (short)(y / short4.y);
        z = (short)(z / short4.z);
        w = (short)(w / short4.w);
    }

    public void div(short word0)
    {
        x = (short)(x / word0);
        y = (short)(y / word0);
        z = (short)(z / word0);
        w = (short)(w / word0);
    }

    public short dotProduct(Short4 short4)
    {
        return (short)(x * short4.x + y * short4.y + z * short4.z + w * short4.w);
    }

    public short elementSum()
    {
        return (short)(x + y + z + w);
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

        case 2: // '\002'
            return z;

        case 3: // '\003'
            return w;
        }
    }

    public short length()
    {
        return 4;
    }

    public void mod(Short4 short4)
    {
        x = (short)(x % short4.x);
        y = (short)(y % short4.y);
        z = (short)(z % short4.z);
        w = (short)(w % short4.w);
    }

    public void mod(short word0)
    {
        x = (short)(x % word0);
        y = (short)(y % word0);
        z = (short)(z % word0);
        w = (short)(w % word0);
    }

    public void mul(Short4 short4)
    {
        x = (short)(x * short4.x);
        y = (short)(y * short4.y);
        z = (short)(z * short4.z);
        w = (short)(w * short4.w);
    }

    public void mul(short word0)
    {
        x = (short)(x * word0);
        y = (short)(y * word0);
        z = (short)(z * word0);
        w = (short)(w * word0);
    }

    public void negate()
    {
        x = (short)(-x);
        y = (short)(-y);
        z = (short)(-z);
        w = (short)(-w);
    }

    public void set(Short4 short4)
    {
        x = short4.x;
        y = short4.y;
        z = short4.z;
        w = short4.w;
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
            return;

        case 2: // '\002'
            z = word0;
            return;

        case 3: // '\003'
            w = word0;
            break;
        }
    }

    public void setValues(short word0, short word1, short word2, short word3)
    {
        x = word0;
        y = word1;
        z = word2;
        w = word3;
    }

    public void sub(Short4 short4)
    {
        x = (short)(x - short4.x);
        y = (short)(y - short4.y);
        z = (short)(z - short4.z);
        w = (short)(w - short4.w);
    }

    public void sub(short word0)
    {
        x = (short)(x - word0);
        y = (short)(y - word0);
        z = (short)(z - word0);
        w = (short)(w - word0);
    }

    public short w;
    public short x;
    public short y;
    public short z;
}

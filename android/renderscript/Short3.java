// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


public class Short3
{

    public Short3()
    {
    }

    public Short3(Short3 short3)
    {
        x = short3.x;
        y = short3.y;
        z = short3.z;
    }

    public Short3(short word0)
    {
        z = word0;
        y = word0;
        x = word0;
    }

    public Short3(short word0, short word1, short word2)
    {
        x = word0;
        y = word1;
        z = word2;
    }

    public static Short3 add(Short3 short3, Short3 short3_1)
    {
        Short3 short3_2 = new Short3();
        short3_2.x = (short)(short3.x + short3_1.x);
        short3_2.y = (short)(short3.y + short3_1.y);
        short3_2.z = (short)(short3.z + short3_1.z);
        return short3_2;
    }

    public static Short3 add(Short3 short3, short word0)
    {
        Short3 short3_1 = new Short3();
        short3_1.x = (short)(short3.x + word0);
        short3_1.y = (short)(short3.y + word0);
        short3_1.z = (short)(short3.z + word0);
        return short3_1;
    }

    public static Short3 div(Short3 short3, Short3 short3_1)
    {
        Short3 short3_2 = new Short3();
        short3_2.x = (short)(short3.x / short3_1.x);
        short3_2.y = (short)(short3.y / short3_1.y);
        short3_2.z = (short)(short3.z / short3_1.z);
        return short3_2;
    }

    public static Short3 div(Short3 short3, short word0)
    {
        Short3 short3_1 = new Short3();
        short3_1.x = (short)(short3.x / word0);
        short3_1.y = (short)(short3.y / word0);
        short3_1.z = (short)(short3.z / word0);
        return short3_1;
    }

    public static short dotProduct(Short3 short3, Short3 short3_1)
    {
        return (short)(short3_1.x * short3.x + short3_1.y * short3.y + short3_1.z * short3.z);
    }

    public static Short3 mod(Short3 short3, Short3 short3_1)
    {
        Short3 short3_2 = new Short3();
        short3_2.x = (short)(short3.x % short3_1.x);
        short3_2.y = (short)(short3.y % short3_1.y);
        short3_2.z = (short)(short3.z % short3_1.z);
        return short3_2;
    }

    public static Short3 mod(Short3 short3, short word0)
    {
        Short3 short3_1 = new Short3();
        short3_1.x = (short)(short3.x % word0);
        short3_1.y = (short)(short3.y % word0);
        short3_1.z = (short)(short3.z % word0);
        return short3_1;
    }

    public static Short3 mul(Short3 short3, Short3 short3_1)
    {
        Short3 short3_2 = new Short3();
        short3_2.x = (short)(short3.x * short3_1.x);
        short3_2.y = (short)(short3.y * short3_1.y);
        short3_2.z = (short)(short3.z * short3_1.z);
        return short3_2;
    }

    public static Short3 mul(Short3 short3, short word0)
    {
        Short3 short3_1 = new Short3();
        short3_1.x = (short)(short3.x * word0);
        short3_1.y = (short)(short3.y * word0);
        short3_1.z = (short)(short3.z * word0);
        return short3_1;
    }

    public static Short3 sub(Short3 short3, Short3 short3_1)
    {
        Short3 short3_2 = new Short3();
        short3_2.x = (short)(short3.x - short3_1.x);
        short3_2.y = (short)(short3.y - short3_1.y);
        short3_2.z = (short)(short3.z - short3_1.z);
        return short3_2;
    }

    public static Short3 sub(Short3 short3, short word0)
    {
        Short3 short3_1 = new Short3();
        short3_1.x = (short)(short3.x - word0);
        short3_1.y = (short)(short3.y - word0);
        short3_1.z = (short)(short3.z - word0);
        return short3_1;
    }

    public void add(Short3 short3)
    {
        x = (short)(x + short3.x);
        y = (short)(y + short3.y);
        z = (short)(z + short3.z);
    }

    public void add(short word0)
    {
        x = (short)(x + word0);
        y = (short)(y + word0);
        z = (short)(z + word0);
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
            break;
        }
    }

    public void addMultiple(Short3 short3, short word0)
    {
        x = (short)(x + short3.x * word0);
        y = (short)(y + short3.y * word0);
        z = (short)(z + short3.z * word0);
    }

    public void copyTo(short aword0[], int i)
    {
        aword0[i] = x;
        aword0[i + 1] = y;
        aword0[i + 2] = z;
    }

    public void div(Short3 short3)
    {
        x = (short)(x / short3.x);
        y = (short)(y / short3.y);
        z = (short)(z / short3.z);
    }

    public void div(short word0)
    {
        x = (short)(x / word0);
        y = (short)(y / word0);
        z = (short)(z / word0);
    }

    public short dotProduct(Short3 short3)
    {
        return (short)(x * short3.x + y * short3.y + z * short3.z);
    }

    public short elementSum()
    {
        return (short)(x + y + z);
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
        }
    }

    public short length()
    {
        return 3;
    }

    public void mod(Short3 short3)
    {
        x = (short)(x % short3.x);
        y = (short)(y % short3.y);
        z = (short)(z % short3.z);
    }

    public void mod(short word0)
    {
        x = (short)(x % word0);
        y = (short)(y % word0);
        z = (short)(z % word0);
    }

    public void mul(Short3 short3)
    {
        x = (short)(x * short3.x);
        y = (short)(y * short3.y);
        z = (short)(z * short3.z);
    }

    public void mul(short word0)
    {
        x = (short)(x * word0);
        y = (short)(y * word0);
        z = (short)(z * word0);
    }

    public void negate()
    {
        x = (short)(-x);
        y = (short)(-y);
        z = (short)(-z);
    }

    public void set(Short3 short3)
    {
        x = short3.x;
        y = short3.y;
        z = short3.z;
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
            break;
        }
    }

    public void setValues(short word0, short word1, short word2)
    {
        x = word0;
        y = word1;
        z = word2;
    }

    public void sub(Short3 short3)
    {
        x = (short)(x - short3.x);
        y = (short)(y - short3.y);
        z = (short)(z - short3.z);
    }

    public void sub(short word0)
    {
        x = (short)(x - word0);
        y = (short)(y - word0);
        z = (short)(z - word0);
    }

    public short x;
    public short y;
    public short z;
}

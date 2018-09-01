// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


public class Long3
{

    public Long3()
    {
    }

    public Long3(long l)
    {
        z = l;
        y = l;
        x = l;
    }

    public Long3(long l, long l1, long l2)
    {
        x = l;
        y = l1;
        z = l2;
    }

    public Long3(Long3 long3)
    {
        x = long3.x;
        y = long3.y;
        z = long3.z;
    }

    public static Long3 add(Long3 long3, long l)
    {
        Long3 long3_1 = new Long3();
        long3_1.x = long3.x + l;
        long3_1.y = long3.y + l;
        long3_1.z = long3.z + l;
        return long3_1;
    }

    public static Long3 add(Long3 long3, Long3 long3_1)
    {
        Long3 long3_2 = new Long3();
        long3_2.x = long3.x + long3_1.x;
        long3_2.y = long3.y + long3_1.y;
        long3_2.z = long3.z + long3_1.z;
        return long3_2;
    }

    public static Long3 div(Long3 long3, long l)
    {
        Long3 long3_1 = new Long3();
        long3_1.x = long3.x / l;
        long3_1.y = long3.y / l;
        long3_1.z = long3.z / l;
        return long3_1;
    }

    public static Long3 div(Long3 long3, Long3 long3_1)
    {
        Long3 long3_2 = new Long3();
        long3_2.x = long3.x / long3_1.x;
        long3_2.y = long3.y / long3_1.y;
        long3_2.z = long3.z / long3_1.z;
        return long3_2;
    }

    public static long dotProduct(Long3 long3, Long3 long3_1)
    {
        return long3_1.x * long3.x + long3_1.y * long3.y + long3_1.z * long3.z;
    }

    public static Long3 mod(Long3 long3, long l)
    {
        Long3 long3_1 = new Long3();
        long3_1.x = long3.x % l;
        long3_1.y = long3.y % l;
        long3_1.z = long3.z % l;
        return long3_1;
    }

    public static Long3 mod(Long3 long3, Long3 long3_1)
    {
        Long3 long3_2 = new Long3();
        long3_2.x = long3.x % long3_1.x;
        long3_2.y = long3.y % long3_1.y;
        long3_2.z = long3.z % long3_1.z;
        return long3_2;
    }

    public static Long3 mul(Long3 long3, long l)
    {
        Long3 long3_1 = new Long3();
        long3_1.x = long3.x * l;
        long3_1.y = long3.y * l;
        long3_1.z = long3.z * l;
        return long3_1;
    }

    public static Long3 mul(Long3 long3, Long3 long3_1)
    {
        Long3 long3_2 = new Long3();
        long3_2.x = long3.x * long3_1.x;
        long3_2.y = long3.y * long3_1.y;
        long3_2.z = long3.z * long3_1.z;
        return long3_2;
    }

    public static Long3 sub(Long3 long3, long l)
    {
        Long3 long3_1 = new Long3();
        long3_1.x = long3.x - l;
        long3_1.y = long3.y - l;
        long3_1.z = long3.z - l;
        return long3_1;
    }

    public static Long3 sub(Long3 long3, Long3 long3_1)
    {
        Long3 long3_2 = new Long3();
        long3_2.x = long3.x - long3_1.x;
        long3_2.y = long3.y - long3_1.y;
        long3_2.z = long3.z - long3_1.z;
        return long3_2;
    }

    public void add(long l)
    {
        x = x + l;
        y = y + l;
        z = z + l;
    }

    public void add(Long3 long3)
    {
        x = x + long3.x;
        y = y + long3.y;
        z = z + long3.z;
    }

    public void addAt(int i, long l)
    {
        switch(i)
        {
        default:
            throw new IndexOutOfBoundsException("Index: i");

        case 0: // '\0'
            x = x + l;
            return;

        case 1: // '\001'
            y = y + l;
            return;

        case 2: // '\002'
            z = z + l;
            break;
        }
    }

    public void addMultiple(Long3 long3, long l)
    {
        x = x + long3.x * l;
        y = y + long3.y * l;
        z = z + long3.z * l;
    }

    public void copyTo(long al[], int i)
    {
        al[i] = x;
        al[i + 1] = y;
        al[i + 2] = z;
    }

    public void div(long l)
    {
        x = x / l;
        y = y / l;
        z = z / l;
    }

    public void div(Long3 long3)
    {
        x = x / long3.x;
        y = y / long3.y;
        z = z / long3.z;
    }

    public long dotProduct(Long3 long3)
    {
        return x * long3.x + y * long3.y + z * long3.z;
    }

    public long elementSum()
    {
        return x + y + z;
    }

    public long get(int i)
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

    public long length()
    {
        return 3L;
    }

    public void mod(long l)
    {
        x = x % l;
        y = y % l;
        z = z % l;
    }

    public void mod(Long3 long3)
    {
        x = x % long3.x;
        y = y % long3.y;
        z = z % long3.z;
    }

    public void mul(long l)
    {
        x = x * l;
        y = y * l;
        z = z * l;
    }

    public void mul(Long3 long3)
    {
        x = x * long3.x;
        y = y * long3.y;
        z = z * long3.z;
    }

    public void negate()
    {
        x = -x;
        y = -y;
        z = -z;
    }

    public void set(Long3 long3)
    {
        x = long3.x;
        y = long3.y;
        z = long3.z;
    }

    public void setAt(int i, long l)
    {
        switch(i)
        {
        default:
            throw new IndexOutOfBoundsException("Index: i");

        case 0: // '\0'
            x = l;
            return;

        case 1: // '\001'
            y = l;
            return;

        case 2: // '\002'
            z = l;
            break;
        }
    }

    public void setValues(long l, long l1, long l2)
    {
        x = l;
        y = l1;
        z = l2;
    }

    public void sub(long l)
    {
        x = x - l;
        y = y - l;
        z = z - l;
    }

    public void sub(Long3 long3)
    {
        x = x - long3.x;
        y = y - long3.y;
        z = z - long3.z;
    }

    public long x;
    public long y;
    public long z;
}

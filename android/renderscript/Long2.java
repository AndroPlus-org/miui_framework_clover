// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


public class Long2
{

    public Long2()
    {
    }

    public Long2(long l)
    {
        y = l;
        x = l;
    }

    public Long2(long l, long l1)
    {
        x = l;
        y = l1;
    }

    public Long2(Long2 long2)
    {
        x = long2.x;
        y = long2.y;
    }

    public static Long2 add(Long2 long2, long l)
    {
        Long2 long2_1 = new Long2();
        long2_1.x = long2.x + l;
        long2_1.y = long2.y + l;
        return long2_1;
    }

    public static Long2 add(Long2 long2, Long2 long2_1)
    {
        Long2 long2_2 = new Long2();
        long2_2.x = long2.x + long2_1.x;
        long2_2.y = long2.y + long2_1.y;
        return long2_2;
    }

    public static Long2 div(Long2 long2, long l)
    {
        Long2 long2_1 = new Long2();
        long2_1.x = long2.x / l;
        long2_1.y = long2.y / l;
        return long2_1;
    }

    public static Long2 div(Long2 long2, Long2 long2_1)
    {
        Long2 long2_2 = new Long2();
        long2_2.x = long2.x / long2_1.x;
        long2_2.y = long2.y / long2_1.y;
        return long2_2;
    }

    public static long dotProduct(Long2 long2, Long2 long2_1)
    {
        return long2_1.x * long2.x + long2_1.y * long2.y;
    }

    public static Long2 mod(Long2 long2, long l)
    {
        Long2 long2_1 = new Long2();
        long2_1.x = long2.x % l;
        long2_1.y = long2.y % l;
        return long2_1;
    }

    public static Long2 mod(Long2 long2, Long2 long2_1)
    {
        Long2 long2_2 = new Long2();
        long2_2.x = long2.x % long2_1.x;
        long2_2.y = long2.y % long2_1.y;
        return long2_2;
    }

    public static Long2 mul(Long2 long2, long l)
    {
        Long2 long2_1 = new Long2();
        long2_1.x = long2.x * l;
        long2_1.y = long2.y * l;
        return long2_1;
    }

    public static Long2 mul(Long2 long2, Long2 long2_1)
    {
        Long2 long2_2 = new Long2();
        long2_2.x = long2.x * long2_1.x;
        long2_2.y = long2.y * long2_1.y;
        return long2_2;
    }

    public static Long2 sub(Long2 long2, long l)
    {
        Long2 long2_1 = new Long2();
        long2_1.x = long2.x - l;
        long2_1.y = long2.y - l;
        return long2_1;
    }

    public static Long2 sub(Long2 long2, Long2 long2_1)
    {
        Long2 long2_2 = new Long2();
        long2_2.x = long2.x - long2_1.x;
        long2_2.y = long2.y - long2_1.y;
        return long2_2;
    }

    public void add(long l)
    {
        x = x + l;
        y = y + l;
    }

    public void add(Long2 long2)
    {
        x = x + long2.x;
        y = y + long2.y;
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
            break;
        }
    }

    public void addMultiple(Long2 long2, long l)
    {
        x = x + long2.x * l;
        y = y + long2.y * l;
    }

    public void copyTo(long al[], int i)
    {
        al[i] = x;
        al[i + 1] = y;
    }

    public void div(long l)
    {
        x = x / l;
        y = y / l;
    }

    public void div(Long2 long2)
    {
        x = x / long2.x;
        y = y / long2.y;
    }

    public long dotProduct(Long2 long2)
    {
        return x * long2.x + y * long2.y;
    }

    public long elementSum()
    {
        return x + y;
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
        }
    }

    public long length()
    {
        return 2L;
    }

    public void mod(long l)
    {
        x = x % l;
        y = y % l;
    }

    public void mod(Long2 long2)
    {
        x = x % long2.x;
        y = y % long2.y;
    }

    public void mul(long l)
    {
        x = x * l;
        y = y * l;
    }

    public void mul(Long2 long2)
    {
        x = x * long2.x;
        y = y * long2.y;
    }

    public void negate()
    {
        x = -x;
        y = -y;
    }

    public void set(Long2 long2)
    {
        x = long2.x;
        y = long2.y;
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
            break;
        }
    }

    public void setValues(long l, long l1)
    {
        x = l;
        y = l1;
    }

    public void sub(long l)
    {
        x = x - l;
        y = y - l;
    }

    public void sub(Long2 long2)
    {
        x = x - long2.x;
        y = y - long2.y;
    }

    public long x;
    public long y;
}

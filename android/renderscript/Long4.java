// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


public class Long4
{

    public Long4()
    {
    }

    public Long4(long l)
    {
        w = l;
        z = l;
        y = l;
        x = l;
    }

    public Long4(long l, long l1, long l2, long l3)
    {
        x = l;
        y = l1;
        z = l2;
        w = l3;
    }

    public Long4(Long4 long4)
    {
        x = long4.x;
        y = long4.y;
        z = long4.z;
        w = long4.w;
    }

    public static Long4 add(Long4 long4, long l)
    {
        Long4 long4_1 = new Long4();
        long4_1.x = long4.x + l;
        long4_1.y = long4.y + l;
        long4_1.z = long4.z + l;
        long4_1.w = long4.w + l;
        return long4_1;
    }

    public static Long4 add(Long4 long4, Long4 long4_1)
    {
        Long4 long4_2 = new Long4();
        long4_2.x = long4.x + long4_1.x;
        long4_2.y = long4.y + long4_1.y;
        long4_2.z = long4.z + long4_1.z;
        long4_2.w = long4.w + long4_1.w;
        return long4_2;
    }

    public static Long4 div(Long4 long4, long l)
    {
        Long4 long4_1 = new Long4();
        long4_1.x = long4.x / l;
        long4_1.y = long4.y / l;
        long4_1.z = long4.z / l;
        long4_1.w = long4.w / l;
        return long4_1;
    }

    public static Long4 div(Long4 long4, Long4 long4_1)
    {
        Long4 long4_2 = new Long4();
        long4_2.x = long4.x / long4_1.x;
        long4_2.y = long4.y / long4_1.y;
        long4_2.z = long4.z / long4_1.z;
        long4_2.w = long4.w / long4_1.w;
        return long4_2;
    }

    public static long dotProduct(Long4 long4, Long4 long4_1)
    {
        return long4_1.x * long4.x + long4_1.y * long4.y + long4_1.z * long4.z + long4_1.w * long4.w;
    }

    public static Long4 mod(Long4 long4, long l)
    {
        Long4 long4_1 = new Long4();
        long4_1.x = long4.x % l;
        long4_1.y = long4.y % l;
        long4_1.z = long4.z % l;
        long4_1.w = long4.w % l;
        return long4_1;
    }

    public static Long4 mod(Long4 long4, Long4 long4_1)
    {
        Long4 long4_2 = new Long4();
        long4_2.x = long4.x % long4_1.x;
        long4_2.y = long4.y % long4_1.y;
        long4_2.z = long4.z % long4_1.z;
        long4_2.w = long4.w % long4_1.w;
        return long4_2;
    }

    public static Long4 mul(Long4 long4, long l)
    {
        Long4 long4_1 = new Long4();
        long4_1.x = long4.x * l;
        long4_1.y = long4.y * l;
        long4_1.z = long4.z * l;
        long4_1.w = long4.w * l;
        return long4_1;
    }

    public static Long4 mul(Long4 long4, Long4 long4_1)
    {
        Long4 long4_2 = new Long4();
        long4_2.x = long4.x * long4_1.x;
        long4_2.y = long4.y * long4_1.y;
        long4_2.z = long4.z * long4_1.z;
        long4_2.w = long4.w * long4_1.w;
        return long4_2;
    }

    public static Long4 sub(Long4 long4, long l)
    {
        Long4 long4_1 = new Long4();
        long4_1.x = long4.x - l;
        long4_1.y = long4.y - l;
        long4_1.z = long4.z - l;
        long4_1.w = long4.w - l;
        return long4_1;
    }

    public static Long4 sub(Long4 long4, Long4 long4_1)
    {
        Long4 long4_2 = new Long4();
        long4_2.x = long4.x - long4_1.x;
        long4_2.y = long4.y - long4_1.y;
        long4_2.z = long4.z - long4_1.z;
        long4_2.w = long4.w - long4_1.w;
        return long4_2;
    }

    public void add(long l)
    {
        x = x + l;
        y = y + l;
        z = z + l;
        w = w + l;
    }

    public void add(Long4 long4)
    {
        x = x + long4.x;
        y = y + long4.y;
        z = z + long4.z;
        w = w + long4.w;
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
            return;

        case 3: // '\003'
            w = w + l;
            break;
        }
    }

    public void addMultiple(Long4 long4, long l)
    {
        x = x + long4.x * l;
        y = y + long4.y * l;
        z = z + long4.z * l;
        w = w + long4.w * l;
    }

    public void copyTo(long al[], int i)
    {
        al[i] = x;
        al[i + 1] = y;
        al[i + 2] = z;
        al[i + 3] = w;
    }

    public void div(long l)
    {
        x = x / l;
        y = y / l;
        z = z / l;
        w = w / l;
    }

    public void div(Long4 long4)
    {
        x = x / long4.x;
        y = y / long4.y;
        z = z / long4.z;
        w = w / long4.w;
    }

    public long dotProduct(Long4 long4)
    {
        return x * long4.x + y * long4.y + z * long4.z + w * long4.w;
    }

    public long elementSum()
    {
        return x + y + z + w;
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

        case 3: // '\003'
            return w;
        }
    }

    public long length()
    {
        return 4L;
    }

    public void mod(long l)
    {
        x = x % l;
        y = y % l;
        z = z % l;
        w = w % l;
    }

    public void mod(Long4 long4)
    {
        x = x % long4.x;
        y = y % long4.y;
        z = z % long4.z;
        w = w % long4.w;
    }

    public void mul(long l)
    {
        x = x * l;
        y = y * l;
        z = z * l;
        w = w * l;
    }

    public void mul(Long4 long4)
    {
        x = x * long4.x;
        y = y * long4.y;
        z = z * long4.z;
        w = w * long4.w;
    }

    public void negate()
    {
        x = -x;
        y = -y;
        z = -z;
        w = -w;
    }

    public void set(Long4 long4)
    {
        x = long4.x;
        y = long4.y;
        z = long4.z;
        w = long4.w;
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
            return;

        case 3: // '\003'
            w = l;
            break;
        }
    }

    public void setValues(long l, long l1, long l2, long l3)
    {
        x = l;
        y = l1;
        z = l2;
        w = l3;
    }

    public void sub(long l)
    {
        x = x - l;
        y = y - l;
        z = z - l;
        w = w - l;
    }

    public void sub(Long4 long4)
    {
        x = x - long4.x;
        y = y - long4.y;
        z = z - long4.z;
        w = w - long4.w;
    }

    public long w;
    public long x;
    public long y;
    public long z;
}

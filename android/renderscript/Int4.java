// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


public class Int4
{

    public Int4()
    {
    }

    public Int4(int i)
    {
        w = i;
        z = i;
        y = i;
        x = i;
    }

    public Int4(int i, int j, int k, int l)
    {
        x = i;
        y = j;
        z = k;
        w = l;
    }

    public Int4(Int4 int4)
    {
        x = int4.x;
        y = int4.y;
        z = int4.z;
        w = int4.w;
    }

    public static Int4 add(Int4 int4, int i)
    {
        Int4 int4_1 = new Int4();
        int4_1.x = int4.x + i;
        int4_1.y = int4.y + i;
        int4_1.z = int4.z + i;
        int4_1.w = int4.w + i;
        return int4_1;
    }

    public static Int4 add(Int4 int4, Int4 int4_1)
    {
        Int4 int4_2 = new Int4();
        int4_2.x = int4.x + int4_1.x;
        int4_2.y = int4.y + int4_1.y;
        int4_2.z = int4.z + int4_1.z;
        int4_2.w = int4.w + int4_1.w;
        return int4_2;
    }

    public static Int4 div(Int4 int4, int i)
    {
        Int4 int4_1 = new Int4();
        int4_1.x = int4.x / i;
        int4_1.y = int4.y / i;
        int4_1.z = int4.z / i;
        int4_1.w = int4.w / i;
        return int4_1;
    }

    public static Int4 div(Int4 int4, Int4 int4_1)
    {
        Int4 int4_2 = new Int4();
        int4_2.x = int4.x / int4_1.x;
        int4_2.y = int4.y / int4_1.y;
        int4_2.z = int4.z / int4_1.z;
        int4_2.w = int4.w / int4_1.w;
        return int4_2;
    }

    public static int dotProduct(Int4 int4, Int4 int4_1)
    {
        return int4_1.x * int4.x + int4_1.y * int4.y + int4_1.z * int4.z + int4_1.w * int4.w;
    }

    public static Int4 mod(Int4 int4, int i)
    {
        Int4 int4_1 = new Int4();
        int4_1.x = int4.x % i;
        int4_1.y = int4.y % i;
        int4_1.z = int4.z % i;
        int4_1.w = int4.w % i;
        return int4_1;
    }

    public static Int4 mod(Int4 int4, Int4 int4_1)
    {
        Int4 int4_2 = new Int4();
        int4_2.x = int4.x % int4_1.x;
        int4_2.y = int4.y % int4_1.y;
        int4_2.z = int4.z % int4_1.z;
        int4_2.w = int4.w % int4_1.w;
        return int4_2;
    }

    public static Int4 mul(Int4 int4, int i)
    {
        Int4 int4_1 = new Int4();
        int4_1.x = int4.x * i;
        int4_1.y = int4.y * i;
        int4_1.z = int4.z * i;
        int4_1.w = int4.w * i;
        return int4_1;
    }

    public static Int4 mul(Int4 int4, Int4 int4_1)
    {
        Int4 int4_2 = new Int4();
        int4_2.x = int4.x * int4_1.x;
        int4_2.y = int4.y * int4_1.y;
        int4_2.z = int4.z * int4_1.z;
        int4_2.w = int4.w * int4_1.w;
        return int4_2;
    }

    public static Int4 sub(Int4 int4, int i)
    {
        Int4 int4_1 = new Int4();
        int4_1.x = int4.x - i;
        int4_1.y = int4.y - i;
        int4_1.z = int4.z - i;
        int4_1.w = int4.w - i;
        return int4_1;
    }

    public static Int4 sub(Int4 int4, Int4 int4_1)
    {
        Int4 int4_2 = new Int4();
        int4_2.x = int4.x - int4_1.x;
        int4_2.y = int4.y - int4_1.y;
        int4_2.z = int4.z - int4_1.z;
        int4_2.w = int4.w - int4_1.w;
        return int4_2;
    }

    public void add(int i)
    {
        x = x + i;
        y = y + i;
        z = z + i;
        w = w + i;
    }

    public void add(Int4 int4)
    {
        x = x + int4.x;
        y = y + int4.y;
        z = z + int4.z;
        w = w + int4.w;
    }

    public void addAt(int i, int j)
    {
        switch(i)
        {
        default:
            throw new IndexOutOfBoundsException("Index: i");

        case 0: // '\0'
            x = x + j;
            return;

        case 1: // '\001'
            y = y + j;
            return;

        case 2: // '\002'
            z = z + j;
            return;

        case 3: // '\003'
            w = w + j;
            break;
        }
    }

    public void addMultiple(Int4 int4, int i)
    {
        x = x + int4.x * i;
        y = y + int4.y * i;
        z = z + int4.z * i;
        w = w + int4.w * i;
    }

    public void copyTo(int ai[], int i)
    {
        ai[i] = x;
        ai[i + 1] = y;
        ai[i + 2] = z;
        ai[i + 3] = w;
    }

    public void div(int i)
    {
        x = x / i;
        y = y / i;
        z = z / i;
        w = w / i;
    }

    public void div(Int4 int4)
    {
        x = x / int4.x;
        y = y / int4.y;
        z = z / int4.z;
        w = w / int4.w;
    }

    public int dotProduct(Int4 int4)
    {
        return x * int4.x + y * int4.y + z * int4.z + w * int4.w;
    }

    public int elementSum()
    {
        return x + y + z + w;
    }

    public int get(int i)
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

    public int length()
    {
        return 4;
    }

    public void mod(int i)
    {
        x = x % i;
        y = y % i;
        z = z % i;
        w = w % i;
    }

    public void mod(Int4 int4)
    {
        x = x % int4.x;
        y = y % int4.y;
        z = z % int4.z;
        w = w % int4.w;
    }

    public void mul(int i)
    {
        x = x * i;
        y = y * i;
        z = z * i;
        w = w * i;
    }

    public void mul(Int4 int4)
    {
        x = x * int4.x;
        y = y * int4.y;
        z = z * int4.z;
        w = w * int4.w;
    }

    public void negate()
    {
        x = -x;
        y = -y;
        z = -z;
        w = -w;
    }

    public void set(Int4 int4)
    {
        x = int4.x;
        y = int4.y;
        z = int4.z;
        w = int4.w;
    }

    public void setAt(int i, int j)
    {
        switch(i)
        {
        default:
            throw new IndexOutOfBoundsException("Index: i");

        case 0: // '\0'
            x = j;
            return;

        case 1: // '\001'
            y = j;
            return;

        case 2: // '\002'
            z = j;
            return;

        case 3: // '\003'
            w = j;
            break;
        }
    }

    public void setValues(int i, int j, int k, int l)
    {
        x = i;
        y = j;
        z = k;
        w = l;
    }

    public void sub(int i)
    {
        x = x - i;
        y = y - i;
        z = z - i;
        w = w - i;
    }

    public void sub(Int4 int4)
    {
        x = x - int4.x;
        y = y - int4.y;
        z = z - int4.z;
        w = w - int4.w;
    }

    public int w;
    public int x;
    public int y;
    public int z;
}

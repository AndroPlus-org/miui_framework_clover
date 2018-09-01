// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


public class Int2
{

    public Int2()
    {
    }

    public Int2(int i)
    {
        y = i;
        x = i;
    }

    public Int2(int i, int j)
    {
        x = i;
        y = j;
    }

    public Int2(Int2 int2)
    {
        x = int2.x;
        y = int2.y;
    }

    public static Int2 add(Int2 int2, int i)
    {
        Int2 int2_1 = new Int2();
        int2_1.x = int2.x + i;
        int2_1.y = int2.y + i;
        return int2_1;
    }

    public static Int2 add(Int2 int2, Int2 int2_1)
    {
        Int2 int2_2 = new Int2();
        int2_2.x = int2.x + int2_1.x;
        int2_2.y = int2.y + int2_1.y;
        return int2_2;
    }

    public static Int2 div(Int2 int2, int i)
    {
        Int2 int2_1 = new Int2();
        int2_1.x = int2.x / i;
        int2_1.y = int2.y / i;
        return int2_1;
    }

    public static Int2 div(Int2 int2, Int2 int2_1)
    {
        Int2 int2_2 = new Int2();
        int2_2.x = int2.x / int2_1.x;
        int2_2.y = int2.y / int2_1.y;
        return int2_2;
    }

    public static int dotProduct(Int2 int2, Int2 int2_1)
    {
        return int2_1.x * int2.x + int2_1.y * int2.y;
    }

    public static Int2 mod(Int2 int2, int i)
    {
        Int2 int2_1 = new Int2();
        int2_1.x = int2.x % i;
        int2_1.y = int2.y % i;
        return int2_1;
    }

    public static Int2 mod(Int2 int2, Int2 int2_1)
    {
        Int2 int2_2 = new Int2();
        int2_2.x = int2.x % int2_1.x;
        int2_2.y = int2.y % int2_1.y;
        return int2_2;
    }

    public static Int2 mul(Int2 int2, int i)
    {
        Int2 int2_1 = new Int2();
        int2_1.x = int2.x * i;
        int2_1.y = int2.y * i;
        return int2_1;
    }

    public static Int2 mul(Int2 int2, Int2 int2_1)
    {
        Int2 int2_2 = new Int2();
        int2_2.x = int2.x * int2_1.x;
        int2_2.y = int2.y * int2_1.y;
        return int2_2;
    }

    public static Int2 sub(Int2 int2, int i)
    {
        Int2 int2_1 = new Int2();
        int2_1.x = int2.x - i;
        int2_1.y = int2.y - i;
        return int2_1;
    }

    public static Int2 sub(Int2 int2, Int2 int2_1)
    {
        Int2 int2_2 = new Int2();
        int2_2.x = int2.x - int2_1.x;
        int2_2.y = int2.y - int2_1.y;
        return int2_2;
    }

    public void add(int i)
    {
        x = x + i;
        y = y + i;
    }

    public void add(Int2 int2)
    {
        x = x + int2.x;
        y = y + int2.y;
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
            break;
        }
    }

    public void addMultiple(Int2 int2, int i)
    {
        x = x + int2.x * i;
        y = y + int2.y * i;
    }

    public void copyTo(int ai[], int i)
    {
        ai[i] = x;
        ai[i + 1] = y;
    }

    public void div(int i)
    {
        x = x / i;
        y = y / i;
    }

    public void div(Int2 int2)
    {
        x = x / int2.x;
        y = y / int2.y;
    }

    public int dotProduct(Int2 int2)
    {
        return x * int2.x + y * int2.y;
    }

    public int elementSum()
    {
        return x + y;
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
        }
    }

    public int length()
    {
        return 2;
    }

    public void mod(int i)
    {
        x = x % i;
        y = y % i;
    }

    public void mod(Int2 int2)
    {
        x = x % int2.x;
        y = y % int2.y;
    }

    public void mul(int i)
    {
        x = x * i;
        y = y * i;
    }

    public void mul(Int2 int2)
    {
        x = x * int2.x;
        y = y * int2.y;
    }

    public void negate()
    {
        x = -x;
        y = -y;
    }

    public void set(Int2 int2)
    {
        x = int2.x;
        y = int2.y;
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
            break;
        }
    }

    public void setValues(int i, int j)
    {
        x = i;
        y = j;
    }

    public void sub(int i)
    {
        x = x - i;
        y = y - i;
    }

    public void sub(Int2 int2)
    {
        x = x - int2.x;
        y = y - int2.y;
    }

    public int x;
    public int y;
}

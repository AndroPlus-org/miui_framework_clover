// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


public class Int3
{

    public Int3()
    {
    }

    public Int3(int i)
    {
        z = i;
        y = i;
        x = i;
    }

    public Int3(int i, int j, int k)
    {
        x = i;
        y = j;
        z = k;
    }

    public Int3(Int3 int3)
    {
        x = int3.x;
        y = int3.y;
        z = int3.z;
    }

    public static Int3 add(Int3 int3, int i)
    {
        Int3 int3_1 = new Int3();
        int3_1.x = int3.x + i;
        int3_1.y = int3.y + i;
        int3_1.z = int3.z + i;
        return int3_1;
    }

    public static Int3 add(Int3 int3, Int3 int3_1)
    {
        Int3 int3_2 = new Int3();
        int3_2.x = int3.x + int3_1.x;
        int3_2.y = int3.y + int3_1.y;
        int3_2.z = int3.z + int3_1.z;
        return int3_2;
    }

    public static Int3 div(Int3 int3, int i)
    {
        Int3 int3_1 = new Int3();
        int3_1.x = int3.x / i;
        int3_1.y = int3.y / i;
        int3_1.z = int3.z / i;
        return int3_1;
    }

    public static Int3 div(Int3 int3, Int3 int3_1)
    {
        Int3 int3_2 = new Int3();
        int3_2.x = int3.x / int3_1.x;
        int3_2.y = int3.y / int3_1.y;
        int3_2.z = int3.z / int3_1.z;
        return int3_2;
    }

    public static int dotProduct(Int3 int3, Int3 int3_1)
    {
        return int3_1.x * int3.x + int3_1.y * int3.y + int3_1.z * int3.z;
    }

    public static Int3 mod(Int3 int3, int i)
    {
        Int3 int3_1 = new Int3();
        int3_1.x = int3.x % i;
        int3_1.y = int3.y % i;
        int3_1.z = int3.z % i;
        return int3_1;
    }

    public static Int3 mod(Int3 int3, Int3 int3_1)
    {
        Int3 int3_2 = new Int3();
        int3_2.x = int3.x % int3_1.x;
        int3_2.y = int3.y % int3_1.y;
        int3_2.z = int3.z % int3_1.z;
        return int3_2;
    }

    public static Int3 mul(Int3 int3, int i)
    {
        Int3 int3_1 = new Int3();
        int3_1.x = int3.x * i;
        int3_1.y = int3.y * i;
        int3_1.z = int3.z * i;
        return int3_1;
    }

    public static Int3 mul(Int3 int3, Int3 int3_1)
    {
        Int3 int3_2 = new Int3();
        int3_2.x = int3.x * int3_1.x;
        int3_2.y = int3.y * int3_1.y;
        int3_2.z = int3.z * int3_1.z;
        return int3_2;
    }

    public static Int3 sub(Int3 int3, int i)
    {
        Int3 int3_1 = new Int3();
        int3_1.x = int3.x - i;
        int3_1.y = int3.y - i;
        int3_1.z = int3.z - i;
        return int3_1;
    }

    public static Int3 sub(Int3 int3, Int3 int3_1)
    {
        Int3 int3_2 = new Int3();
        int3_2.x = int3.x - int3_1.x;
        int3_2.y = int3.y - int3_1.y;
        int3_2.z = int3.z - int3_1.z;
        return int3_2;
    }

    public void add(int i)
    {
        x = x + i;
        y = y + i;
        z = z + i;
    }

    public void add(Int3 int3)
    {
        x = x + int3.x;
        y = y + int3.y;
        z = z + int3.z;
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
            break;
        }
    }

    public void addMultiple(Int3 int3, int i)
    {
        x = x + int3.x * i;
        y = y + int3.y * i;
        z = z + int3.z * i;
    }

    public void copyTo(int ai[], int i)
    {
        ai[i] = x;
        ai[i + 1] = y;
        ai[i + 2] = z;
    }

    public void div(int i)
    {
        x = x / i;
        y = y / i;
        z = z / i;
    }

    public void div(Int3 int3)
    {
        x = x / int3.x;
        y = y / int3.y;
        z = z / int3.z;
    }

    public int dotProduct(Int3 int3)
    {
        return x * int3.x + y * int3.y + z * int3.z;
    }

    public int elementSum()
    {
        return x + y + z;
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
        }
    }

    public int length()
    {
        return 3;
    }

    public void mod(int i)
    {
        x = x % i;
        y = y % i;
        z = z % i;
    }

    public void mod(Int3 int3)
    {
        x = x % int3.x;
        y = y % int3.y;
        z = z % int3.z;
    }

    public void mul(int i)
    {
        x = x * i;
        y = y * i;
        z = z * i;
    }

    public void mul(Int3 int3)
    {
        x = x * int3.x;
        y = y * int3.y;
        z = z * int3.z;
    }

    public void negate()
    {
        x = -x;
        y = -y;
        z = -z;
    }

    public void set(Int3 int3)
    {
        x = int3.x;
        y = int3.y;
        z = int3.z;
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
            break;
        }
    }

    public void setValues(int i, int j, int k)
    {
        x = i;
        y = j;
        z = k;
    }

    public void sub(int i)
    {
        x = x - i;
        y = y - i;
        z = z - i;
    }

    public void sub(Int3 int3)
    {
        x = x - int3.x;
        y = y - int3.y;
        z = z - int3.z;
    }

    public int x;
    public int y;
    public int z;
}

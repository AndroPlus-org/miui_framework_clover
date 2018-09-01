// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


public class Double4
{

    public Double4()
    {
    }

    public Double4(double d, double d1, double d2, double d3)
    {
        x = d;
        y = d1;
        z = d2;
        w = d3;
    }

    public Double4(Double4 double4)
    {
        x = double4.x;
        y = double4.y;
        z = double4.z;
        w = double4.w;
    }

    public static Double4 add(Double4 double4, double d)
    {
        Double4 double4_1 = new Double4();
        double4_1.x = double4.x + d;
        double4_1.y = double4.y + d;
        double4_1.z = double4.z + d;
        double4_1.w = double4.w + d;
        return double4_1;
    }

    public static Double4 add(Double4 double4, Double4 double4_1)
    {
        Double4 double4_2 = new Double4();
        double4_2.x = double4.x + double4_1.x;
        double4_2.y = double4.y + double4_1.y;
        double4_2.z = double4.z + double4_1.z;
        double4_2.w = double4.w + double4_1.w;
        return double4_2;
    }

    public static Double4 div(Double4 double4, double d)
    {
        Double4 double4_1 = new Double4();
        double4_1.x = double4.x / d;
        double4_1.y = double4.y / d;
        double4_1.z = double4.z / d;
        double4_1.w = double4.w / d;
        return double4_1;
    }

    public static Double4 div(Double4 double4, Double4 double4_1)
    {
        Double4 double4_2 = new Double4();
        double4_2.x = double4.x / double4_1.x;
        double4_2.y = double4.y / double4_1.y;
        double4_2.z = double4.z / double4_1.z;
        double4_2.w = double4.w / double4_1.w;
        return double4_2;
    }

    public static double dotProduct(Double4 double4, Double4 double4_1)
    {
        return double4_1.x * double4.x + double4_1.y * double4.y + double4_1.z * double4.z + double4_1.w * double4.w;
    }

    public static Double4 mul(Double4 double4, double d)
    {
        Double4 double4_1 = new Double4();
        double4_1.x = double4.x * d;
        double4_1.y = double4.y * d;
        double4_1.z = double4.z * d;
        double4_1.w = double4.w * d;
        return double4_1;
    }

    public static Double4 mul(Double4 double4, Double4 double4_1)
    {
        Double4 double4_2 = new Double4();
        double4_2.x = double4.x * double4_1.x;
        double4_2.y = double4.y * double4_1.y;
        double4_2.z = double4.z * double4_1.z;
        double4_2.w = double4.w * double4_1.w;
        return double4_2;
    }

    public static Double4 sub(Double4 double4, double d)
    {
        Double4 double4_1 = new Double4();
        double4_1.x = double4.x - d;
        double4_1.y = double4.y - d;
        double4_1.z = double4.z - d;
        double4_1.w = double4.w - d;
        return double4_1;
    }

    public static Double4 sub(Double4 double4, Double4 double4_1)
    {
        Double4 double4_2 = new Double4();
        double4_2.x = double4.x - double4_1.x;
        double4_2.y = double4.y - double4_1.y;
        double4_2.z = double4.z - double4_1.z;
        double4_2.w = double4.w - double4_1.w;
        return double4_2;
    }

    public void add(double d)
    {
        x = x + d;
        y = y + d;
        z = z + d;
        w = w + d;
    }

    public void add(Double4 double4)
    {
        x = x + double4.x;
        y = y + double4.y;
        z = z + double4.z;
        w = w + double4.w;
    }

    public void addAt(int i, double d)
    {
        switch(i)
        {
        default:
            throw new IndexOutOfBoundsException("Index: i");

        case 0: // '\0'
            x = x + d;
            return;

        case 1: // '\001'
            y = y + d;
            return;

        case 2: // '\002'
            z = z + d;
            return;

        case 3: // '\003'
            w = w + d;
            break;
        }
    }

    public void addMultiple(Double4 double4, double d)
    {
        x = x + double4.x * d;
        y = y + double4.y * d;
        z = z + double4.z * d;
        w = w + double4.w * d;
    }

    public void copyTo(double ad[], int i)
    {
        ad[i] = x;
        ad[i + 1] = y;
        ad[i + 2] = z;
        ad[i + 3] = w;
    }

    public void div(double d)
    {
        x = x / d;
        y = y / d;
        z = z / d;
        w = w / d;
    }

    public void div(Double4 double4)
    {
        x = x / double4.x;
        y = y / double4.y;
        z = z / double4.z;
        w = w / double4.w;
    }

    public double dotProduct(Double4 double4)
    {
        return x * double4.x + y * double4.y + z * double4.z + w * double4.w;
    }

    public double elementSum()
    {
        return x + y + z + w;
    }

    public double get(int i)
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

    public void mul(double d)
    {
        x = x * d;
        y = y * d;
        z = z * d;
        w = w * d;
    }

    public void mul(Double4 double4)
    {
        x = x * double4.x;
        y = y * double4.y;
        z = z * double4.z;
        w = w * double4.w;
    }

    public void negate()
    {
        x = -x;
        y = -y;
        z = -z;
        w = -w;
    }

    public void set(Double4 double4)
    {
        x = double4.x;
        y = double4.y;
        z = double4.z;
        w = double4.w;
    }

    public void setAt(int i, double d)
    {
        switch(i)
        {
        default:
            throw new IndexOutOfBoundsException("Index: i");

        case 0: // '\0'
            x = d;
            return;

        case 1: // '\001'
            y = d;
            return;

        case 2: // '\002'
            z = d;
            return;

        case 3: // '\003'
            w = d;
            break;
        }
    }

    public void setValues(double d, double d1, double d2, double d3)
    {
        x = d;
        y = d1;
        z = d2;
        w = d3;
    }

    public void sub(double d)
    {
        x = x - d;
        y = y - d;
        z = z - d;
        w = w - d;
    }

    public void sub(Double4 double4)
    {
        x = x - double4.x;
        y = y - double4.y;
        z = z - double4.z;
        w = w - double4.w;
    }

    public double w;
    public double x;
    public double y;
    public double z;
}

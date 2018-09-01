// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


public class Double3
{

    public Double3()
    {
    }

    public Double3(double d, double d1, double d2)
    {
        x = d;
        y = d1;
        z = d2;
    }

    public Double3(Double3 double3)
    {
        x = double3.x;
        y = double3.y;
        z = double3.z;
    }

    public static Double3 add(Double3 double3, double d)
    {
        Double3 double3_1 = new Double3();
        double3_1.x = double3.x + d;
        double3_1.y = double3.y + d;
        double3_1.z = double3.z + d;
        return double3_1;
    }

    public static Double3 add(Double3 double3, Double3 double3_1)
    {
        Double3 double3_2 = new Double3();
        double3_2.x = double3.x + double3_1.x;
        double3_2.y = double3.y + double3_1.y;
        double3_2.z = double3.z + double3_1.z;
        return double3_2;
    }

    public static Double3 div(Double3 double3, double d)
    {
        Double3 double3_1 = new Double3();
        double3_1.x = double3.x / d;
        double3_1.y = double3.y / d;
        double3_1.z = double3.z / d;
        return double3_1;
    }

    public static Double3 div(Double3 double3, Double3 double3_1)
    {
        Double3 double3_2 = new Double3();
        double3_2.x = double3.x / double3_1.x;
        double3_2.y = double3.y / double3_1.y;
        double3_2.z = double3.z / double3_1.z;
        return double3_2;
    }

    public static double dotProduct(Double3 double3, Double3 double3_1)
    {
        return double3_1.x * double3.x + double3_1.y * double3.y + double3_1.z * double3.z;
    }

    public static Double3 mul(Double3 double3, double d)
    {
        Double3 double3_1 = new Double3();
        double3_1.x = double3.x * d;
        double3_1.y = double3.y * d;
        double3_1.z = double3.z * d;
        return double3_1;
    }

    public static Double3 mul(Double3 double3, Double3 double3_1)
    {
        Double3 double3_2 = new Double3();
        double3_2.x = double3.x * double3_1.x;
        double3_2.y = double3.y * double3_1.y;
        double3_2.z = double3.z * double3_1.z;
        return double3_2;
    }

    public static Double3 sub(Double3 double3, double d)
    {
        Double3 double3_1 = new Double3();
        double3_1.x = double3.x - d;
        double3_1.y = double3.y - d;
        double3_1.z = double3.z - d;
        return double3_1;
    }

    public static Double3 sub(Double3 double3, Double3 double3_1)
    {
        Double3 double3_2 = new Double3();
        double3_2.x = double3.x - double3_1.x;
        double3_2.y = double3.y - double3_1.y;
        double3_2.z = double3.z - double3_1.z;
        return double3_2;
    }

    public void add(double d)
    {
        x = x + d;
        y = y + d;
        z = z + d;
    }

    public void add(Double3 double3)
    {
        x = x + double3.x;
        y = y + double3.y;
        z = z + double3.z;
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
            break;
        }
    }

    public void addMultiple(Double3 double3, double d)
    {
        x = x + double3.x * d;
        y = y + double3.y * d;
        z = z + double3.z * d;
    }

    public void copyTo(double ad[], int i)
    {
        ad[i] = x;
        ad[i + 1] = y;
        ad[i + 2] = z;
    }

    public void div(double d)
    {
        x = x / d;
        y = y / d;
        z = z / d;
    }

    public void div(Double3 double3)
    {
        x = x / double3.x;
        y = y / double3.y;
        z = z / double3.z;
    }

    public double dotProduct(Double3 double3)
    {
        return x * double3.x + y * double3.y + z * double3.z;
    }

    public double elementSum()
    {
        return x + y + z;
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
        }
    }

    public int length()
    {
        return 3;
    }

    public void mul(double d)
    {
        x = x * d;
        y = y * d;
        z = z * d;
    }

    public void mul(Double3 double3)
    {
        x = x * double3.x;
        y = y * double3.y;
        z = z * double3.z;
    }

    public void negate()
    {
        x = -x;
        y = -y;
        z = -z;
    }

    public void set(Double3 double3)
    {
        x = double3.x;
        y = double3.y;
        z = double3.z;
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
            break;
        }
    }

    public void setValues(double d, double d1, double d2)
    {
        x = d;
        y = d1;
        z = d2;
    }

    public void sub(double d)
    {
        x = x - d;
        y = y - d;
        z = z - d;
    }

    public void sub(Double3 double3)
    {
        x = x - double3.x;
        y = y - double3.y;
        z = z - double3.z;
    }

    public double x;
    public double y;
    public double z;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


public class Double2
{

    public Double2()
    {
    }

    public Double2(double d, double d1)
    {
        x = d;
        y = d1;
    }

    public Double2(Double2 double2)
    {
        x = double2.x;
        y = double2.y;
    }

    public static Double2 add(Double2 double2, double d)
    {
        Double2 double2_1 = new Double2();
        double2_1.x = double2.x + d;
        double2_1.y = double2.y + d;
        return double2_1;
    }

    public static Double2 add(Double2 double2, Double2 double2_1)
    {
        Double2 double2_2 = new Double2();
        double2_2.x = double2.x + double2_1.x;
        double2_2.y = double2.y + double2_1.y;
        return double2_2;
    }

    public static Double2 div(Double2 double2, double d)
    {
        Double2 double2_1 = new Double2();
        double2_1.x = double2.x / d;
        double2_1.y = double2.y / d;
        return double2_1;
    }

    public static Double2 div(Double2 double2, Double2 double2_1)
    {
        Double2 double2_2 = new Double2();
        double2_2.x = double2.x / double2_1.x;
        double2_2.y = double2.y / double2_1.y;
        return double2_2;
    }

    public static Double dotProduct(Double2 double2, Double2 double2_1)
    {
        return Double.valueOf(double2_1.x * double2.x + double2_1.y * double2.y);
    }

    public static Double2 mul(Double2 double2, double d)
    {
        Double2 double2_1 = new Double2();
        double2_1.x = double2.x * d;
        double2_1.y = double2.y * d;
        return double2_1;
    }

    public static Double2 mul(Double2 double2, Double2 double2_1)
    {
        Double2 double2_2 = new Double2();
        double2_2.x = double2.x * double2_1.x;
        double2_2.y = double2.y * double2_1.y;
        return double2_2;
    }

    public static Double2 sub(Double2 double2, double d)
    {
        Double2 double2_1 = new Double2();
        double2_1.x = double2.x - d;
        double2_1.y = double2.y - d;
        return double2_1;
    }

    public static Double2 sub(Double2 double2, Double2 double2_1)
    {
        Double2 double2_2 = new Double2();
        double2_2.x = double2.x - double2_1.x;
        double2_2.y = double2.y - double2_1.y;
        return double2_2;
    }

    public void add(double d)
    {
        x = x + d;
        y = y + d;
    }

    public void add(Double2 double2)
    {
        x = x + double2.x;
        y = y + double2.y;
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
            break;
        }
    }

    public void addMultiple(Double2 double2, double d)
    {
        x = x + double2.x * d;
        y = y + double2.y * d;
    }

    public void copyTo(double ad[], int i)
    {
        ad[i] = x;
        ad[i + 1] = y;
    }

    public void div(double d)
    {
        x = x / d;
        y = y / d;
    }

    public void div(Double2 double2)
    {
        x = x / double2.x;
        y = y / double2.y;
    }

    public double dotProduct(Double2 double2)
    {
        return x * double2.x + y * double2.y;
    }

    public double elementSum()
    {
        return x + y;
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
        }
    }

    public int length()
    {
        return 2;
    }

    public void mul(double d)
    {
        x = x * d;
        y = y * d;
    }

    public void mul(Double2 double2)
    {
        x = x * double2.x;
        y = y * double2.y;
    }

    public void negate()
    {
        x = -x;
        y = -y;
    }

    public void set(Double2 double2)
    {
        x = double2.x;
        y = double2.y;
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
            break;
        }
    }

    public void setValues(double d, double d1)
    {
        x = d;
        y = d1;
    }

    public void sub(double d)
    {
        x = x - d;
        y = y - d;
    }

    public void sub(Double2 double2)
    {
        x = x - double2.x;
        y = y - double2.y;
    }

    public double x;
    public double y;
}

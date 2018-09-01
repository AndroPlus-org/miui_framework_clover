// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.geometry;


public class Point
{

    public Point()
    {
    }

    public Point(float f, float f1)
    {
        x = f;
        y = f1;
    }

    public boolean IsInUnitRange()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(x >= 0.0F)
        {
            flag1 = flag;
            if(x <= 1.0F)
            {
                flag1 = flag;
                if(y >= 0.0F)
                {
                    flag1 = flag;
                    if(y <= 1.0F)
                        flag1 = true;
                }
            }
        }
        return flag1;
    }

    public float distanceTo(Point point)
    {
        return point.minus(this).length();
    }

    public float length()
    {
        return (float)Math.hypot(x, y);
    }

    public Point minus(float f, float f1)
    {
        return new Point(x - f, y - f1);
    }

    public Point minus(Point point)
    {
        return minus(point.x, point.y);
    }

    public Point mult(float f, float f1)
    {
        return new Point(x * f, y * f1);
    }

    public Point normalize()
    {
        return scaledTo(1.0F);
    }

    public Point plus(float f, float f1)
    {
        return new Point(x + f, y + f1);
    }

    public Point plus(Point point)
    {
        return plus(point.x, point.y);
    }

    public Point rotated(float f)
    {
        return new Point((float)(Math.cos(f) * (double)x - Math.sin(f) * (double)y), (float)(Math.sin(f) * (double)x + Math.cos(f) * (double)y));
    }

    public Point rotated90(int i)
    {
        float f = x;
        float f1 = y;
        for(int j = 0; j < i;)
        {
            float f2 = -f;
            j++;
            f = f1;
            f1 = f2;
        }

        return new Point(f, f1);
    }

    public Point rotatedAround(Point point, float f)
    {
        return minus(point).rotated(f).plus(point);
    }

    public Point scaledTo(float f)
    {
        return times(f / length());
    }

    public void set(float f, float f1)
    {
        x = f;
        y = f1;
    }

    public Point times(float f)
    {
        return new Point(x * f, y * f);
    }

    public String toString()
    {
        return (new StringBuilder()).append("(").append(x).append(", ").append(y).append(")").toString();
    }

    public float x;
    public float y;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.geometry;

import java.util.Arrays;
import java.util.Collections;

// Referenced classes of package android.filterfw.geometry:
//            Point, Rectangle

public class Quad
{

    public Quad()
    {
    }

    public Quad(Point point, Point point1, Point point2, Point point3)
    {
        p0 = point;
        p1 = point1;
        p2 = point2;
        p3 = point3;
    }

    public boolean IsInUnitRange()
    {
        boolean flag;
        if(p0.IsInUnitRange() && p1.IsInUnitRange() && p2.IsInUnitRange())
            flag = p3.IsInUnitRange();
        else
            flag = false;
        return flag;
    }

    public Rectangle boundingBox()
    {
        java.util.List list = Arrays.asList(new Float[] {
            Float.valueOf(p0.x), Float.valueOf(p1.x), Float.valueOf(p2.x), Float.valueOf(p3.x)
        });
        java.util.List list1 = Arrays.asList(new Float[] {
            Float.valueOf(p0.y), Float.valueOf(p1.y), Float.valueOf(p2.y), Float.valueOf(p3.y)
        });
        float f = ((Float)Collections.min(list)).floatValue();
        float f1 = ((Float)Collections.min(list1)).floatValue();
        return new Rectangle(f, f1, ((Float)Collections.max(list)).floatValue() - f, ((Float)Collections.max(list1)).floatValue() - f1);
    }

    public float getBoundingHeight()
    {
        java.util.List list = Arrays.asList(new Float[] {
            Float.valueOf(p0.y), Float.valueOf(p1.y), Float.valueOf(p2.y), Float.valueOf(p3.y)
        });
        return ((Float)Collections.max(list)).floatValue() - ((Float)Collections.min(list)).floatValue();
    }

    public float getBoundingWidth()
    {
        java.util.List list = Arrays.asList(new Float[] {
            Float.valueOf(p0.x), Float.valueOf(p1.x), Float.valueOf(p2.x), Float.valueOf(p3.x)
        });
        return ((Float)Collections.max(list)).floatValue() - ((Float)Collections.min(list)).floatValue();
    }

    public Quad scaled(float f)
    {
        return new Quad(p0.times(f), p1.times(f), p2.times(f), p3.times(f));
    }

    public Quad scaled(float f, float f1)
    {
        return new Quad(p0.mult(f, f1), p1.mult(f, f1), p2.mult(f, f1), p3.mult(f, f1));
    }

    public String toString()
    {
        return (new StringBuilder()).append("{").append(p0).append(", ").append(p1).append(", ").append(p2).append(", ").append(p3).append("}").toString();
    }

    public Quad translated(float f, float f1)
    {
        return new Quad(p0.plus(f, f1), p1.plus(f, f1), p2.plus(f, f1), p3.plus(f, f1));
    }

    public Quad translated(Point point)
    {
        return new Quad(p0.plus(point), p1.plus(point), p2.plus(point), p3.plus(point));
    }

    public Point p0;
    public Point p1;
    public Point p2;
    public Point p3;
}

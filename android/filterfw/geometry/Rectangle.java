// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.geometry;


// Referenced classes of package android.filterfw.geometry:
//            Quad, Point

public class Rectangle extends Quad
{

    public Rectangle()
    {
    }

    public Rectangle(float f, float f1, float f2, float f3)
    {
        super(new Point(f, f1), new Point(f + f2, f1), new Point(f, f1 + f3), new Point(f + f2, f1 + f3));
    }

    public Rectangle(Point point, Point point1)
    {
        super(point, point.plus(point1.x, 0.0F), point.plus(0.0F, point1.y), point.plus(point1.x, point1.y));
    }

    private Rectangle(Point point, Point point1, Point point2, Point point3)
    {
        super(point, point1, point2, point3);
    }

    public static Rectangle fromCenterVerticalAxis(Point point, Point point1, Point point2)
    {
        Point point3 = point1.scaledTo(point2.y / 2.0F);
        point1 = point1.rotated90(1).scaledTo(point2.x / 2.0F);
        return new Rectangle(point.minus(point1).minus(point3), point.plus(point1).minus(point3), point.minus(point1).plus(point3), point.plus(point1).plus(point3));
    }

    public static Rectangle fromRotatedRect(Point point, Point point1, float f)
    {
        Point point2 = new Point(point.x - point1.x / 2.0F, point.y - point1.y / 2.0F);
        Point point3 = new Point(point.x + point1.x / 2.0F, point.y - point1.y / 2.0F);
        Point point4 = new Point(point.x - point1.x / 2.0F, point.y + point1.y / 2.0F);
        point1 = new Point(point.x + point1.x / 2.0F, point.y + point1.y / 2.0F);
        return new Rectangle(point2.rotatedAround(point, f), point3.rotatedAround(point, f), point4.rotatedAround(point, f), point1.rotatedAround(point, f));
    }

    public Point center()
    {
        return p0.plus(p1).plus(p2).plus(p3).times(0.25F);
    }

    public float getHeight()
    {
        return p2.minus(p0).length();
    }

    public float getWidth()
    {
        return p1.minus(p0).length();
    }

    public volatile Quad scaled(float f)
    {
        return scaled(f);
    }

    public volatile Quad scaled(float f, float f1)
    {
        return scaled(f, f1);
    }

    public Rectangle scaled(float f)
    {
        return new Rectangle(p0.times(f), p1.times(f), p2.times(f), p3.times(f));
    }

    public Rectangle scaled(float f, float f1)
    {
        return new Rectangle(p0.mult(f, f1), p1.mult(f, f1), p2.mult(f, f1), p3.mult(f, f1));
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable.shapes;

import android.graphics.*;

// Referenced classes of package android.graphics.drawable.shapes:
//            RectShape, Shape

public class OvalShape extends RectShape
{

    public OvalShape()
    {
    }

    public OvalShape clone()
        throws CloneNotSupportedException
    {
        return (OvalShape)super.clone();
    }

    public volatile RectShape clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public volatile Shape clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public void draw(Canvas canvas, Paint paint)
    {
        canvas.drawOval(rect(), paint);
    }

    public void getOutline(Outline outline)
    {
        RectF rectf = rect();
        outline.setOval((int)Math.ceil(rectf.left), (int)Math.ceil(rectf.top), (int)Math.floor(rectf.right), (int)Math.floor(rectf.bottom));
    }
}

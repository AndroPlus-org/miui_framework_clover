// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable.shapes;

import android.graphics.*;

// Referenced classes of package android.graphics.drawable.shapes:
//            Shape

public class RectShape extends Shape
{

    public RectShape()
    {
        mRect = new RectF();
    }

    public RectShape clone()
        throws CloneNotSupportedException
    {
        RectShape rectshape = (RectShape)super.clone();
        rectshape.mRect = new RectF(mRect);
        return rectshape;
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
        canvas.drawRect(mRect, paint);
    }

    public void getOutline(Outline outline)
    {
        RectF rectf = rect();
        outline.setRect((int)Math.ceil(rectf.left), (int)Math.ceil(rectf.top), (int)Math.floor(rectf.right), (int)Math.floor(rectf.bottom));
    }

    protected void onResize(float f, float f1)
    {
        mRect.set(0.0F, 0.0F, f, f1);
    }

    protected final RectF rect()
    {
        return mRect;
    }

    private RectF mRect;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable.shapes;

import android.graphics.*;

public abstract class Shape
    implements Cloneable
{

    public Shape()
    {
    }

    public Shape clone()
        throws CloneNotSupportedException
    {
        return (Shape)super.clone();
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public abstract void draw(Canvas canvas, Paint paint);

    public final float getHeight()
    {
        return mHeight;
    }

    public void getOutline(Outline outline)
    {
    }

    public final float getWidth()
    {
        return mWidth;
    }

    public boolean hasAlpha()
    {
        return true;
    }

    protected void onResize(float f, float f1)
    {
    }

    public final void resize(float f, float f1)
    {
        float f2 = f;
        if(f < 0.0F)
            f2 = 0.0F;
        f = f1;
        if(f1 < 0.0F)
            f = 0.0F;
        if(mWidth != f2 || mHeight != f)
        {
            mWidth = f2;
            mHeight = f;
            onResize(f2, f);
        }
    }

    private float mHeight;
    private float mWidth;
}

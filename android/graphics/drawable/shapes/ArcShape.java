// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable.shapes;

import android.graphics.*;

// Referenced classes of package android.graphics.drawable.shapes:
//            RectShape, Shape

public class ArcShape extends RectShape
{

    public ArcShape(float f, float f1)
    {
        mStartAngle = f;
        mSweepAngle = f1;
    }

    public ArcShape clone()
        throws CloneNotSupportedException
    {
        return (ArcShape)super.clone();
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
        canvas.drawArc(rect(), mStartAngle, mSweepAngle, true, paint);
    }

    public void getOutline(Outline outline)
    {
    }

    public final float getStartAngle()
    {
        return mStartAngle;
    }

    public final float getSweepAngle()
    {
        return mSweepAngle;
    }

    private final float mStartAngle;
    private final float mSweepAngle;
}

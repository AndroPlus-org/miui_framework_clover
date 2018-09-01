// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable.shapes;

import android.graphics.*;

// Referenced classes of package android.graphics.drawable.shapes:
//            RectShape, Shape

public class RoundRectShape extends RectShape
{

    public RoundRectShape(float af[], RectF rectf, float af1[])
    {
        if(af != null && af.length < 8)
            throw new ArrayIndexOutOfBoundsException("outer radii must have >= 8 values");
        if(af1 != null && af1.length < 8)
            throw new ArrayIndexOutOfBoundsException("inner radii must have >= 8 values");
        mOuterRadii = af;
        mInset = rectf;
        mInnerRadii = af1;
        if(rectf != null)
            mInnerRect = new RectF();
        mPath = new Path();
    }

    public volatile RectShape clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public RoundRectShape clone()
        throws CloneNotSupportedException
    {
        RoundRectShape roundrectshape = (RoundRectShape)super.clone();
        float af[];
        if(mOuterRadii != null)
            af = (float[])mOuterRadii.clone();
        else
            af = null;
        roundrectshape.mOuterRadii = af;
        if(mInnerRadii != null)
            af = (float[])mInnerRadii.clone();
        else
            af = null;
        roundrectshape.mInnerRadii = af;
        roundrectshape.mInset = new RectF(mInset);
        roundrectshape.mInnerRect = new RectF(mInnerRect);
        roundrectshape.mPath = new Path(mPath);
        return roundrectshape;
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
        canvas.drawPath(mPath, paint);
    }

    public void getOutline(Outline outline)
    {
        if(mInnerRect != null)
            return;
        float f = 0.0F;
        if(mOuterRadii != null)
        {
            float f1 = mOuterRadii[0];
            int i = 1;
            do
            {
                f = f1;
                if(i >= 8)
                    break;
                if(mOuterRadii[i] != f1)
                {
                    outline.setConvexPath(mPath);
                    return;
                }
                i++;
            } while(true);
        }
        RectF rectf = rect();
        outline.setRoundRect((int)Math.ceil(rectf.left), (int)Math.ceil(rectf.top), (int)Math.floor(rectf.right), (int)Math.floor(rectf.bottom), f);
    }

    protected void onResize(float f, float f1)
    {
        super.onResize(f, f1);
        RectF rectf = rect();
        mPath.reset();
        if(mOuterRadii != null)
            mPath.addRoundRect(rectf, mOuterRadii, android.graphics.Path.Direction.CW);
        else
            mPath.addRect(rectf, android.graphics.Path.Direction.CW);
        if(mInnerRect != null)
        {
            mInnerRect.set(rectf.left + mInset.left, rectf.top + mInset.top, rectf.right - mInset.right, rectf.bottom - mInset.bottom);
            if(mInnerRect.width() < f && mInnerRect.height() < f1)
                if(mInnerRadii != null)
                    mPath.addRoundRect(mInnerRect, mInnerRadii, android.graphics.Path.Direction.CCW);
                else
                    mPath.addRect(mInnerRect, android.graphics.Path.Direction.CCW);
        }
    }

    private float mInnerRadii[];
    private RectF mInnerRect;
    private RectF mInset;
    private float mOuterRadii[];
    private Path mPath;
}

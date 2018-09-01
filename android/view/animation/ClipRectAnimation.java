// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.animation;

import android.graphics.Rect;

// Referenced classes of package android.view.animation:
//            Animation, Transformation

public class ClipRectAnimation extends Animation
{

    public ClipRectAnimation(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1)
    {
        mFromRect = new Rect();
        mToRect = new Rect();
        mFromRect.set(i, j, k, l);
        mToRect.set(i1, j1, k1, l1);
    }

    public ClipRectAnimation(Rect rect, Rect rect1)
    {
        mFromRect = new Rect();
        mToRect = new Rect();
        if(rect == null || rect1 == null)
        {
            throw new RuntimeException("Expected non-null animation clip rects");
        } else
        {
            mFromRect.set(rect);
            mToRect.set(rect1);
            return;
        }
    }

    protected void applyTransformation(float f, Transformation transformation)
    {
        transformation.setClipRect(mFromRect.left + (int)((float)(mToRect.left - mFromRect.left) * f), mFromRect.top + (int)((float)(mToRect.top - mFromRect.top) * f), mFromRect.right + (int)((float)(mToRect.right - mFromRect.right) * f), mFromRect.bottom + (int)((float)(mToRect.bottom - mFromRect.bottom) * f));
    }

    public boolean willChangeTransformationMatrix()
    {
        return false;
    }

    protected Rect mFromRect;
    protected Rect mToRect;
}

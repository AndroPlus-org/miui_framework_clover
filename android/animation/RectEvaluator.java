// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;

import android.graphics.Rect;

// Referenced classes of package android.animation:
//            TypeEvaluator

public class RectEvaluator
    implements TypeEvaluator
{

    public RectEvaluator()
    {
    }

    public RectEvaluator(Rect rect)
    {
        mRect = rect;
    }

    public Rect evaluate(float f, Rect rect, Rect rect1)
    {
        int i = rect.left + (int)((float)(rect1.left - rect.left) * f);
        int j = rect.top + (int)((float)(rect1.top - rect.top) * f);
        int k = rect.right + (int)((float)(rect1.right - rect.right) * f);
        int l = rect.bottom + (int)((float)(rect1.bottom - rect.bottom) * f);
        if(mRect == null)
        {
            return new Rect(i, j, k, l);
        } else
        {
            mRect.set(i, j, k, l);
            return mRect;
        }
    }

    public volatile Object evaluate(float f, Object obj, Object obj1)
    {
        return evaluate(f, (Rect)obj, (Rect)obj1);
    }

    private Rect mRect;
}

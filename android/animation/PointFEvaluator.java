// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;

import android.graphics.PointF;

// Referenced classes of package android.animation:
//            TypeEvaluator

public class PointFEvaluator
    implements TypeEvaluator
{

    public PointFEvaluator()
    {
    }

    public PointFEvaluator(PointF pointf)
    {
        mPoint = pointf;
    }

    public PointF evaluate(float f, PointF pointf, PointF pointf1)
    {
        float f1 = pointf.x + (pointf1.x - pointf.x) * f;
        f = pointf.y + (pointf1.y - pointf.y) * f;
        if(mPoint != null)
        {
            mPoint.set(f1, f);
            return mPoint;
        } else
        {
            return new PointF(f1, f);
        }
    }

    public volatile Object evaluate(float f, Object obj, Object obj1)
    {
        return evaluate(f, (PointF)obj, (PointF)obj1);
    }

    private PointF mPoint;
}

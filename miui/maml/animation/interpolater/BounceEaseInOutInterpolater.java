// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation.interpolater;

import android.view.animation.Interpolator;

// Referenced classes of package miui.maml.animation.interpolater:
//            BounceEaseInInterpolater, BounceEaseOutInterpolater

public class BounceEaseInOutInterpolater
    implements Interpolator
{

    public BounceEaseInOutInterpolater()
    {
    }

    public float getInterpolation(float f)
    {
        if(f < 0.5F)
            return BounceEaseInInterpolater.getInterpolationImp(f * 2.0F) * 0.5F;
        else
            return BounceEaseOutInterpolater.getInterpolationImp(f * 2.0F - 1.0F) * 0.5F + 0.5F;
    }
}

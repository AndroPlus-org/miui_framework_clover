// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation.interpolater;

import android.view.animation.Interpolator;

// Referenced classes of package miui.maml.animation.interpolater:
//            BounceEaseOutInterpolater

public class BounceEaseInInterpolater
    implements Interpolator
{

    public BounceEaseInInterpolater()
    {
    }

    public static float getInterpolationImp(float f)
    {
        return 1.0F - BounceEaseOutInterpolater.getInterpolationImp(1.0F - f);
    }

    public float getInterpolation(float f)
    {
        return getInterpolationImp(f);
    }
}

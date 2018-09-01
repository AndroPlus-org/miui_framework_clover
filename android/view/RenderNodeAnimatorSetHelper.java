// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.animation.TimeInterpolator;
import com.android.internal.view.animation.*;

// Referenced classes of package android.view:
//            RenderNodeAnimator, DisplayListCanvas, RenderNode

public class RenderNodeAnimatorSetHelper
{

    public RenderNodeAnimatorSetHelper()
    {
    }

    public static long createNativeInterpolator(TimeInterpolator timeinterpolator, long l)
    {
        if(timeinterpolator == null)
            return NativeInterpolatorFactoryHelper.createLinearInterpolator();
        if(RenderNodeAnimator.isNativeInterpolator(timeinterpolator))
            return ((NativeInterpolatorFactory)timeinterpolator).createNativeInterpolator();
        else
            return FallbackLUTInterpolator.createNativeInterpolator(timeinterpolator, l);
    }

    public static RenderNode getTarget(DisplayListCanvas displaylistcanvas)
    {
        return displaylistcanvas.mNode;
    }
}

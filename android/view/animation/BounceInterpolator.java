// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.animation;

import android.content.Context;
import android.util.AttributeSet;
import com.android.internal.view.animation.NativeInterpolatorFactory;
import com.android.internal.view.animation.NativeInterpolatorFactoryHelper;

// Referenced classes of package android.view.animation:
//            BaseInterpolator

public class BounceInterpolator extends BaseInterpolator
    implements NativeInterpolatorFactory
{

    public BounceInterpolator()
    {
    }

    public BounceInterpolator(Context context, AttributeSet attributeset)
    {
    }

    private static float bounce(float f)
    {
        return f * f * 8F;
    }

    public long createNativeInterpolator()
    {
        return NativeInterpolatorFactoryHelper.createBounceInterpolator();
    }

    public float getInterpolation(float f)
    {
        f *= 1.1226F;
        if(f < 0.3535F)
            return bounce(f);
        if(f < 0.7408F)
            return bounce(f - 0.54719F) + 0.7F;
        if(f < 0.9644F)
            return bounce(f - 0.8526F) + 0.9F;
        else
            return bounce(f - 1.0435F) + 0.95F;
    }
}

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

public class AccelerateDecelerateInterpolator extends BaseInterpolator
    implements NativeInterpolatorFactory
{

    public AccelerateDecelerateInterpolator()
    {
    }

    public AccelerateDecelerateInterpolator(Context context, AttributeSet attributeset)
    {
    }

    public long createNativeInterpolator()
    {
        return NativeInterpolatorFactoryHelper.createAccelerateDecelerateInterpolator();
    }

    public float getInterpolation(float f)
    {
        return (float)(Math.cos((double)(1.0F + f) * 3.1415926535897931D) / 2D) + 0.5F;
    }
}

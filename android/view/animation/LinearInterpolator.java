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

public class LinearInterpolator extends BaseInterpolator
    implements NativeInterpolatorFactory
{

    public LinearInterpolator()
    {
    }

    public LinearInterpolator(Context context, AttributeSet attributeset)
    {
    }

    public long createNativeInterpolator()
    {
        return NativeInterpolatorFactoryHelper.createLinearInterpolator();
    }

    public float getInterpolation(float f)
    {
        return f;
    }
}

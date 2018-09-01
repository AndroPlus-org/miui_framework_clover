// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.animation;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.android.internal.view.animation.NativeInterpolatorFactory;
import com.android.internal.view.animation.NativeInterpolatorFactoryHelper;

// Referenced classes of package android.view.animation:
//            BaseInterpolator

public class AccelerateInterpolator extends BaseInterpolator
    implements NativeInterpolatorFactory
{

    public AccelerateInterpolator()
    {
        mFactor = 1.0F;
        mDoubleFactor = 2D;
    }

    public AccelerateInterpolator(float f)
    {
        mFactor = f;
        mDoubleFactor = mFactor * 2.0F;
    }

    public AccelerateInterpolator(Context context, AttributeSet attributeset)
    {
        this(context.getResources(), context.getTheme(), attributeset);
    }

    public AccelerateInterpolator(Resources resources, android.content.res.Resources.Theme theme, AttributeSet attributeset)
    {
        if(theme != null)
            resources = theme.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.AccelerateInterpolator, 0, 0);
        else
            resources = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.AccelerateInterpolator);
        mFactor = resources.getFloat(0, 1.0F);
        mDoubleFactor = mFactor * 2.0F;
        setChangingConfiguration(resources.getChangingConfigurations());
        resources.recycle();
    }

    public long createNativeInterpolator()
    {
        return NativeInterpolatorFactoryHelper.createAccelerateInterpolator(mFactor);
    }

    public float getInterpolation(float f)
    {
        if(mFactor == 1.0F)
            return f * f;
        else
            return (float)Math.pow(f, mDoubleFactor);
    }

    private final double mDoubleFactor;
    private final float mFactor;
}

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

public class AnticipateOvershootInterpolator extends BaseInterpolator
    implements NativeInterpolatorFactory
{

    public AnticipateOvershootInterpolator()
    {
        mTension = 3F;
    }

    public AnticipateOvershootInterpolator(float f)
    {
        mTension = 1.5F * f;
    }

    public AnticipateOvershootInterpolator(float f, float f1)
    {
        mTension = f * f1;
    }

    public AnticipateOvershootInterpolator(Context context, AttributeSet attributeset)
    {
        this(context.getResources(), context.getTheme(), attributeset);
    }

    public AnticipateOvershootInterpolator(Resources resources, android.content.res.Resources.Theme theme, AttributeSet attributeset)
    {
        if(theme != null)
            resources = theme.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.AnticipateOvershootInterpolator, 0, 0);
        else
            resources = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.AnticipateOvershootInterpolator);
        mTension = resources.getFloat(0, 2.0F) * resources.getFloat(1, 1.5F);
        setChangingConfiguration(resources.getChangingConfigurations());
        resources.recycle();
    }

    private static float a(float f, float f1)
    {
        return f * f * ((1.0F + f1) * f - f1);
    }

    private static float o(float f, float f1)
    {
        return f * f * ((1.0F + f1) * f + f1);
    }

    public long createNativeInterpolator()
    {
        return NativeInterpolatorFactoryHelper.createAnticipateOvershootInterpolator(mTension);
    }

    public float getInterpolation(float f)
    {
        if(f < 0.5F)
            return a(f * 2.0F, mTension) * 0.5F;
        else
            return (o(f * 2.0F - 2.0F, mTension) + 2.0F) * 0.5F;
    }

    private final float mTension;
}

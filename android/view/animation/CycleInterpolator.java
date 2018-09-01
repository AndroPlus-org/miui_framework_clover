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

public class CycleInterpolator extends BaseInterpolator
    implements NativeInterpolatorFactory
{

    public CycleInterpolator(float f)
    {
        mCycles = f;
    }

    public CycleInterpolator(Context context, AttributeSet attributeset)
    {
        this(context.getResources(), context.getTheme(), attributeset);
    }

    public CycleInterpolator(Resources resources, android.content.res.Resources.Theme theme, AttributeSet attributeset)
    {
        if(theme != null)
            resources = theme.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.CycleInterpolator, 0, 0);
        else
            resources = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.CycleInterpolator);
        mCycles = resources.getFloat(0, 1.0F);
        setChangingConfiguration(resources.getChangingConfigurations());
        resources.recycle();
    }

    public long createNativeInterpolator()
    {
        return NativeInterpolatorFactoryHelper.createCycleInterpolator(mCycles);
    }

    public float getInterpolation(float f)
    {
        return (float)Math.sin((double)(mCycles * 2.0F) * 3.1415926535897931D * (double)f);
    }

    private float mCycles;
}

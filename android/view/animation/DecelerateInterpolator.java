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

public class DecelerateInterpolator extends BaseInterpolator
    implements NativeInterpolatorFactory
{

    public DecelerateInterpolator()
    {
        mFactor = 1.0F;
    }

    public DecelerateInterpolator(float f)
    {
        mFactor = 1.0F;
        mFactor = f;
    }

    public DecelerateInterpolator(Context context, AttributeSet attributeset)
    {
        this(context.getResources(), context.getTheme(), attributeset);
    }

    public DecelerateInterpolator(Resources resources, android.content.res.Resources.Theme theme, AttributeSet attributeset)
    {
        mFactor = 1.0F;
        if(theme != null)
            resources = theme.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.DecelerateInterpolator, 0, 0);
        else
            resources = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.DecelerateInterpolator);
        mFactor = resources.getFloat(0, 1.0F);
        setChangingConfiguration(resources.getChangingConfigurations());
        resources.recycle();
    }

    public long createNativeInterpolator()
    {
        return NativeInterpolatorFactoryHelper.createDecelerateInterpolator(mFactor);
    }

    public float getInterpolation(float f)
    {
        if(mFactor == 1.0F)
            f = 1.0F - (1.0F - f) * (1.0F - f);
        else
            f = (float)(1.0D - Math.pow(1.0F - f, mFactor * 2.0F));
        return f;
    }

    private float mFactor;
}

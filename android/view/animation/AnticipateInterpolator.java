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

public class AnticipateInterpolator extends BaseInterpolator
    implements NativeInterpolatorFactory
{

    public AnticipateInterpolator()
    {
        mTension = 2.0F;
    }

    public AnticipateInterpolator(float f)
    {
        mTension = f;
    }

    public AnticipateInterpolator(Context context, AttributeSet attributeset)
    {
        this(context.getResources(), context.getTheme(), attributeset);
    }

    public AnticipateInterpolator(Resources resources, android.content.res.Resources.Theme theme, AttributeSet attributeset)
    {
        if(theme != null)
            resources = theme.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.AnticipateInterpolator, 0, 0);
        else
            resources = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.AnticipateInterpolator);
        mTension = resources.getFloat(0, 2.0F);
        setChangingConfiguration(resources.getChangingConfigurations());
        resources.recycle();
    }

    public long createNativeInterpolator()
    {
        return NativeInterpolatorFactoryHelper.createAnticipateInterpolator(mTension);
    }

    public float getInterpolation(float f)
    {
        return f * f * ((mTension + 1.0F) * f - mTension);
    }

    private final float mTension;
}

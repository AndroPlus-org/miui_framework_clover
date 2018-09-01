// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.animation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.util.AttributeSet;

// Referenced classes of package android.view.animation:
//            Animation, Transformation

public class TranslateAnimation extends Animation
{

    public TranslateAnimation(float f, float f1, float f2, float f3)
    {
        mFromXType = 0;
        mToXType = 0;
        mFromYType = 0;
        mToYType = 0;
        mFromXValue = 0.0F;
        mToXValue = 0.0F;
        mFromYValue = 0.0F;
        mToYValue = 0.0F;
        mFromXValue = f;
        mToXValue = f1;
        mFromYValue = f2;
        mToYValue = f3;
        mFromXType = 0;
        mToXType = 0;
        mFromYType = 0;
        mToYType = 0;
    }

    public TranslateAnimation(int i, float f, int j, float f1, int k, float f2, int l, 
            float f3)
    {
        mFromXType = 0;
        mToXType = 0;
        mFromYType = 0;
        mToYType = 0;
        mFromXValue = 0.0F;
        mToXValue = 0.0F;
        mFromYValue = 0.0F;
        mToYValue = 0.0F;
        mFromXValue = f;
        mToXValue = f1;
        mFromYValue = f2;
        mToYValue = f3;
        mFromXType = i;
        mToXType = j;
        mFromYType = k;
        mToYType = l;
    }

    public TranslateAnimation(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mFromXType = 0;
        mToXType = 0;
        mFromYType = 0;
        mToYType = 0;
        mFromXValue = 0.0F;
        mToXValue = 0.0F;
        mFromYValue = 0.0F;
        mToYValue = 0.0F;
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.TranslateAnimation);
        attributeset = Animation.Description.parseValue(context.peekValue(0));
        mFromXType = ((Animation.Description) (attributeset)).type;
        mFromXValue = ((Animation.Description) (attributeset)).value;
        attributeset = Animation.Description.parseValue(context.peekValue(1));
        mToXType = ((Animation.Description) (attributeset)).type;
        mToXValue = ((Animation.Description) (attributeset)).value;
        attributeset = Animation.Description.parseValue(context.peekValue(2));
        mFromYType = ((Animation.Description) (attributeset)).type;
        mFromYValue = ((Animation.Description) (attributeset)).value;
        attributeset = Animation.Description.parseValue(context.peekValue(3));
        mToYType = ((Animation.Description) (attributeset)).type;
        mToYValue = ((Animation.Description) (attributeset)).value;
        context.recycle();
    }

    protected void applyTransformation(float f, Transformation transformation)
    {
        float f1 = mFromXDelta;
        float f2 = mFromYDelta;
        if(mFromXDelta != mToXDelta)
            f1 = mFromXDelta + (mToXDelta - mFromXDelta) * f;
        if(mFromYDelta != mToYDelta)
            f2 = mFromYDelta + (mToYDelta - mFromYDelta) * f;
        transformation.getMatrix().setTranslate(f1, f2);
    }

    public void initialize(int i, int j, int k, int l)
    {
        super.initialize(i, j, k, l);
        mFromXDelta = resolveSize(mFromXType, mFromXValue, i, k);
        mToXDelta = resolveSize(mToXType, mToXValue, i, k);
        mFromYDelta = resolveSize(mFromYType, mFromYValue, j, l);
        mToYDelta = resolveSize(mToYType, mToYValue, j, l);
    }

    protected float mFromXDelta;
    private int mFromXType;
    protected float mFromXValue;
    protected float mFromYDelta;
    private int mFromYType;
    protected float mFromYValue;
    protected float mToXDelta;
    private int mToXType;
    protected float mToXValue;
    protected float mToYDelta;
    private int mToYType;
    protected float mToYValue;
}

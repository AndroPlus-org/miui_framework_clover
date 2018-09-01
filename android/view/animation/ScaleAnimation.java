// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.animation;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.TypedValue;

// Referenced classes of package android.view.animation:
//            Animation, Transformation

public class ScaleAnimation extends Animation
{

    public ScaleAnimation(float f, float f1, float f2, float f3)
    {
        mFromXType = 0;
        mToXType = 0;
        mFromYType = 0;
        mToYType = 0;
        mFromXData = 0;
        mToXData = 0;
        mFromYData = 0;
        mToYData = 0;
        mPivotXType = 0;
        mPivotYType = 0;
        mPivotXValue = 0.0F;
        mPivotYValue = 0.0F;
        mResources = null;
        mFromX = f;
        mToX = f1;
        mFromY = f2;
        mToY = f3;
        mPivotX = 0.0F;
        mPivotY = 0.0F;
    }

    public ScaleAnimation(float f, float f1, float f2, float f3, float f4, float f5)
    {
        mFromXType = 0;
        mToXType = 0;
        mFromYType = 0;
        mToYType = 0;
        mFromXData = 0;
        mToXData = 0;
        mFromYData = 0;
        mToYData = 0;
        mPivotXType = 0;
        mPivotYType = 0;
        mPivotXValue = 0.0F;
        mPivotYValue = 0.0F;
        mResources = null;
        mFromX = f;
        mToX = f1;
        mFromY = f2;
        mToY = f3;
        mPivotXType = 0;
        mPivotYType = 0;
        mPivotXValue = f4;
        mPivotYValue = f5;
        initializePivotPoint();
    }

    public ScaleAnimation(float f, float f1, float f2, float f3, int i, float f4, int j, 
            float f5)
    {
        mFromXType = 0;
        mToXType = 0;
        mFromYType = 0;
        mToYType = 0;
        mFromXData = 0;
        mToXData = 0;
        mFromYData = 0;
        mToYData = 0;
        mPivotXType = 0;
        mPivotYType = 0;
        mPivotXValue = 0.0F;
        mPivotYValue = 0.0F;
        mResources = null;
        mFromX = f;
        mToX = f1;
        mFromY = f2;
        mToY = f3;
        mPivotXValue = f4;
        mPivotXType = i;
        mPivotYValue = f5;
        mPivotYType = j;
        initializePivotPoint();
    }

    public ScaleAnimation(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mFromXType = 0;
        mToXType = 0;
        mFromYType = 0;
        mToYType = 0;
        mFromXData = 0;
        mToXData = 0;
        mFromYData = 0;
        mToYData = 0;
        mPivotXType = 0;
        mPivotYType = 0;
        mPivotXValue = 0.0F;
        mPivotYValue = 0.0F;
        mResources = context.getResources();
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ScaleAnimation);
        attributeset = context.peekValue(2);
        mFromX = 0.0F;
        if(attributeset != null)
            if(((TypedValue) (attributeset)).type == 4)
            {
                mFromX = attributeset.getFloat();
            } else
            {
                mFromXType = ((TypedValue) (attributeset)).type;
                mFromXData = ((TypedValue) (attributeset)).data;
            }
        attributeset = context.peekValue(3);
        mToX = 0.0F;
        if(attributeset != null)
            if(((TypedValue) (attributeset)).type == 4)
            {
                mToX = attributeset.getFloat();
            } else
            {
                mToXType = ((TypedValue) (attributeset)).type;
                mToXData = ((TypedValue) (attributeset)).data;
            }
        attributeset = context.peekValue(4);
        mFromY = 0.0F;
        if(attributeset != null)
            if(((TypedValue) (attributeset)).type == 4)
            {
                mFromY = attributeset.getFloat();
            } else
            {
                mFromYType = ((TypedValue) (attributeset)).type;
                mFromYData = ((TypedValue) (attributeset)).data;
            }
        attributeset = context.peekValue(5);
        mToY = 0.0F;
        if(attributeset != null)
            if(((TypedValue) (attributeset)).type == 4)
            {
                mToY = attributeset.getFloat();
            } else
            {
                mToYType = ((TypedValue) (attributeset)).type;
                mToYData = ((TypedValue) (attributeset)).data;
            }
        attributeset = Animation.Description.parseValue(context.peekValue(0));
        mPivotXType = ((Animation.Description) (attributeset)).type;
        mPivotXValue = ((Animation.Description) (attributeset)).value;
        attributeset = Animation.Description.parseValue(context.peekValue(1));
        mPivotYType = ((Animation.Description) (attributeset)).type;
        mPivotYValue = ((Animation.Description) (attributeset)).value;
        context.recycle();
        initializePivotPoint();
    }

    private void initializePivotPoint()
    {
        if(mPivotXType == 0)
            mPivotX = mPivotXValue;
        if(mPivotYType == 0)
            mPivotY = mPivotYValue;
    }

    protected void applyTransformation(float f, Transformation transformation)
    {
        float f1 = 1.0F;
        float f2 = 1.0F;
        float f3 = getScaleFactor();
        if(mFromX != 1.0F || mToX != 1.0F)
            f1 = mFromX + (mToX - mFromX) * f;
        if(mFromY != 1.0F || mToY != 1.0F)
            f2 = mFromY + (mToY - mFromY) * f;
        if(mPivotX == 0.0F && mPivotY == 0.0F)
            transformation.getMatrix().setScale(f1, f2);
        else
            transformation.getMatrix().setScale(f1, f2, mPivotX * f3, mPivotY * f3);
    }

    public void initialize(int i, int j, int k, int l)
    {
        super.initialize(i, j, k, l);
        mFromX = resolveScale(mFromX, mFromXType, mFromXData, i, k);
        mToX = resolveScale(mToX, mToXType, mToXData, i, k);
        mFromY = resolveScale(mFromY, mFromYType, mFromYData, j, l);
        mToY = resolveScale(mToY, mToYType, mToYData, j, l);
        mPivotX = resolveSize(mPivotXType, mPivotXValue, i, k);
        mPivotY = resolveSize(mPivotYType, mPivotYValue, j, l);
    }

    float resolveScale(float f, int i, int j, int k, int l)
    {
        if(i == 6)
            f = TypedValue.complexToFraction(j, k, l);
        else
        if(i == 5)
            f = TypedValue.complexToDimension(j, mResources.getDisplayMetrics());
        else
            return f;
        if(k == 0)
            return 1.0F;
        else
            return f / (float)k;
    }

    private float mFromX;
    private int mFromXData;
    private int mFromXType;
    private float mFromY;
    private int mFromYData;
    private int mFromYType;
    private float mPivotX;
    private int mPivotXType;
    private float mPivotXValue;
    private float mPivotY;
    private int mPivotYType;
    private float mPivotYValue;
    private final Resources mResources;
    private float mToX;
    private int mToXData;
    private int mToXType;
    private float mToY;
    private int mToYData;
    private int mToYType;
}

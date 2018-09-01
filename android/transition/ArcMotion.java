// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.util.AttributeSet;

// Referenced classes of package android.transition:
//            PathMotion

public class ArcMotion extends PathMotion
{

    public ArcMotion()
    {
        mMinimumHorizontalAngle = 0.0F;
        mMinimumVerticalAngle = 0.0F;
        mMaximumAngle = 70F;
        mMinimumHorizontalTangent = 0.0F;
        mMinimumVerticalTangent = 0.0F;
        mMaximumTangent = DEFAULT_MAX_TANGENT;
    }

    public ArcMotion(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mMinimumHorizontalAngle = 0.0F;
        mMinimumVerticalAngle = 0.0F;
        mMaximumAngle = 70F;
        mMinimumHorizontalTangent = 0.0F;
        mMinimumVerticalTangent = 0.0F;
        mMaximumTangent = DEFAULT_MAX_TANGENT;
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ArcMotion);
        setMinimumVerticalAngle(context.getFloat(1, 0.0F));
        setMinimumHorizontalAngle(context.getFloat(0, 0.0F));
        setMaximumAngle(context.getFloat(2, 70F));
        context.recycle();
    }

    private static float toTangent(float f)
    {
        if(f < 0.0F || f > 90F)
            throw new IllegalArgumentException("Arc must be between 0 and 90 degrees");
        else
            return (float)Math.tan(Math.toRadians(f / 2.0F));
    }

    public float getMaximumAngle()
    {
        return mMaximumAngle;
    }

    public float getMinimumHorizontalAngle()
    {
        return mMinimumHorizontalAngle;
    }

    public float getMinimumVerticalAngle()
    {
        return mMinimumVerticalAngle;
    }

    public Path getPath(float f, float f1, float f2, float f3)
    {
        float f5;
        float f9;
        float f10;
        float f11;
        Path path = new Path();
        path.moveTo(f, f1);
        float f4 = f2 - f;
        f5 = f3 - f1;
        float f6 = f4 * f4 + f5 * f5;
        float f7 = (f + f2) / 2.0F;
        float f8 = (f1 + f3) / 2.0F;
        f9 = f6 * 0.25F;
        boolean flag;
        if(f1 > f3)
            flag = true;
        else
            flag = false;
        if(Math.abs(f4) < Math.abs(f5))
        {
            f4 = Math.abs(f6 / (2.0F * f5));
            if(flag)
            {
                f4 = f3 + f4;
                f6 = f2;
            } else
            {
                f4 = f1 + f4;
                f6 = f;
            }
            f5 = mMinimumVerticalTangent * f9 * mMinimumVerticalTangent;
        } else
        {
            f4 = f6 / (2.0F * f4);
            if(flag)
            {
                f6 = f + f4;
                f4 = f1;
            } else
            {
                f6 = f2 - f4;
                f4 = f3;
            }
            f5 = mMinimumHorizontalTangent * f9 * mMinimumHorizontalTangent;
        }
        f10 = f7 - f6;
        f11 = f8 - f4;
        f10 = f10 * f10 + f11 * f11;
        f9 = mMaximumTangent * f9 * mMaximumTangent;
        f11 = 0.0F;
        if(f10 >= f5) goto _L2; else goto _L1
_L1:
        f11 = f6;
        f9 = f4;
        if(f5 != 0.0F)
        {
            f5 = (float)Math.sqrt(f5 / f10);
            f11 = f7 + (f6 - f7) * f5;
            f9 = f8 + (f4 - f8) * f5;
        }
        path.cubicTo((f + f11) / 2.0F, (f1 + f9) / 2.0F, (f11 + f2) / 2.0F, (f9 + f3) / 2.0F, f2, f3);
        return path;
_L2:
        f5 = f11;
        if(f10 > f9)
            f5 = f9;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void setMaximumAngle(float f)
    {
        mMaximumAngle = f;
        mMaximumTangent = toTangent(f);
    }

    public void setMinimumHorizontalAngle(float f)
    {
        mMinimumHorizontalAngle = f;
        mMinimumHorizontalTangent = toTangent(f);
    }

    public void setMinimumVerticalAngle(float f)
    {
        mMinimumVerticalAngle = f;
        mMinimumVerticalTangent = toTangent(f);
    }

    private static final float DEFAULT_MAX_ANGLE_DEGREES = 70F;
    private static final float DEFAULT_MAX_TANGENT = (float)Math.tan(Math.toRadians(35D));
    private static final float DEFAULT_MIN_ANGLE_DEGREES = 0F;
    private float mMaximumAngle;
    private float mMaximumTangent;
    private float mMinimumHorizontalAngle;
    private float mMinimumHorizontalTangent;
    private float mMinimumVerticalAngle;
    private float mMinimumVerticalTangent;

}

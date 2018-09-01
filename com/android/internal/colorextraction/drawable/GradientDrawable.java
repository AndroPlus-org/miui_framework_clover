// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.colorextraction.drawable;

import android.animation.*;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.animation.DecelerateInterpolator;
import com.android.internal.graphics.ColorUtils;

public class GradientDrawable extends Drawable
{
    static final class Splat
    {

        final float colorIndex;
        final float radius;
        final float x;
        final float y;

        Splat(float f, float f1, float f2, float f3)
        {
            x = f;
            y = f1;
            radius = f2;
            colorIndex = f3;
        }
    }


    static ValueAnimator _2D_get0(GradientDrawable gradientdrawable)
    {
        return gradientdrawable.mColorAnimation;
    }

    static ValueAnimator _2D_set0(GradientDrawable gradientdrawable, ValueAnimator valueanimator)
    {
        gradientdrawable.mColorAnimation = valueanimator;
        return valueanimator;
    }

    public GradientDrawable(Context context)
    {
        mAlpha = 255;
        mDensity = context.getResources().getDisplayMetrics().density;
        mPaint.setStyle(android.graphics.Paint.Style.FILL);
    }

    private void buildPaints()
    {
        Object obj = mWindowBounds;
        if(((Rect) (obj)).width() == 0)
        {
            return;
        } else
        {
            float f = ((Rect) (obj)).width();
            float f1 = ((Rect) (obj)).height();
            obj = new RadialGradient(mSplat.x * f, mSplat.y * f1, mSplat.radius * mDensity, mSecondaryColor, mMainColor, android.graphics.Shader.TileMode.CLAMP);
            mPaint.setShader(((android.graphics.Shader) (obj)));
            return;
        }
    }

    public void draw(Canvas canvas)
    {
        Rect rect = mWindowBounds;
        if(rect.width() == 0)
        {
            throw new IllegalStateException("You need to call setScreenSize before drawing.");
        } else
        {
            float f = rect.width();
            float f1 = rect.height();
            float f2 = mSplat.x * f;
            float f3 = mSplat.y * f1;
            f = Math.max(f, f1);
            canvas.drawRect(f2 - f, f3 - f, f2 + f, f3 + f, mPaint);
            return;
        }
    }

    public int getAlpha()
    {
        return mAlpha;
    }

    public ColorFilter getColorFilter()
    {
        return mPaint.getColorFilter();
    }

    public int getMainColor()
    {
        return mMainColor;
    }

    public int getOpacity()
    {
        return -3;
    }

    public int getSecondaryColor()
    {
        return mSecondaryColor;
    }

    void lambda$_2D_com_android_internal_colorextraction_drawable_GradientDrawable_3291(int i, int j, int k, int l, ValueAnimator valueanimator)
    {
        float f = ((Float)valueanimator.getAnimatedValue()).floatValue();
        mMainColor = ColorUtils.blendARGB(i, j, f);
        mSecondaryColor = ColorUtils.blendARGB(k, l, f);
        buildPaints();
        invalidateSelf();
    }

    public void setAlpha(int i)
    {
        if(i != mAlpha)
        {
            mAlpha = i;
            mPaint.setAlpha(mAlpha);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        mPaint.setColorFilter(colorfilter);
    }

    public void setColors(int i, int j, boolean flag)
    {
        if(i == mMainColor && j == mSecondaryColor)
            return;
        if(mColorAnimation != null && mColorAnimation.isRunning())
            mColorAnimation.cancel();
        if(flag)
        {
            int k = mMainColor;
            int l = mSecondaryColor;
            ValueAnimator valueanimator = ValueAnimator.ofFloat(new float[] {
                0.0F, 1.0F
            });
            valueanimator.setDuration(2000L);
            valueanimator.addUpdateListener(new _.Lambda.D0plBYSeplKHUImgLxjOl14_7Rw(k, i, l, j, this));
            valueanimator.addListener(new AnimatorListenerAdapter() {

                public void onAnimationEnd(Animator animator, boolean flag1)
                {
                    if(GradientDrawable._2D_get0(GradientDrawable.this) == animator)
                        GradientDrawable._2D_set0(GradientDrawable.this, null);
                }

                final GradientDrawable this$0;

            
            {
                this$0 = GradientDrawable.this;
                super();
            }
            }
);
            valueanimator.setInterpolator(new DecelerateInterpolator());
            valueanimator.start();
            mColorAnimation = valueanimator;
        } else
        {
            mMainColor = i;
            mSecondaryColor = j;
            buildPaints();
            invalidateSelf();
        }
    }

    public void setColors(com.android.internal.colorextraction.ColorExtractor.GradientColors gradientcolors)
    {
        setColors(gradientcolors.getMainColor(), gradientcolors.getSecondaryColor(), true);
    }

    public void setColors(com.android.internal.colorextraction.ColorExtractor.GradientColors gradientcolors, boolean flag)
    {
        setColors(gradientcolors.getMainColor(), gradientcolors.getSecondaryColor(), flag);
    }

    public void setScreenSize(int i, int j)
    {
        mWindowBounds.set(0, 0, i, j);
        setBounds(0, 0, i, j);
        buildPaints();
    }

    public void setXfermode(Xfermode xfermode)
    {
        mPaint.setXfermode(xfermode);
        invalidateSelf();
    }

    private static final float CENTRALIZED_CIRCLE_1 = -2F;
    private static final long COLOR_ANIMATION_DURATION = 2000L;
    private static final int GRADIENT_RADIUS = 480;
    private static final String TAG = "GradientDrawable";
    private int mAlpha;
    private ValueAnimator mColorAnimation;
    private float mDensity;
    private int mMainColor;
    private final Paint mPaint = new Paint();
    private int mSecondaryColor;
    private final Splat mSplat = new Splat(0.5F, 1.0F, 480F, -2F);
    private final Rect mWindowBounds = new Rect();
}

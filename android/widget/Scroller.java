// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.util.BoostFramework;
import android.util.DisplayMetrics;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

public class Scroller
{
    static class ViscousFluidInterpolator
        implements Interpolator
    {

        private static float viscousFluid(float f)
        {
            f *= 8F;
            if(f < 1.0F)
                f -= 1.0F - (float)Math.exp(-f);
            else
                f = 0.3678795F + 0.6321205F * (1.0F - (float)Math.exp(1.0F - f));
            return f;
        }

        public float getInterpolation(float f)
        {
            f = VISCOUS_FLUID_NORMALIZE * viscousFluid(f);
            if(f > 0.0F)
                return VISCOUS_FLUID_OFFSET + f;
            else
                return f;
        }

        private static final float VISCOUS_FLUID_NORMALIZE;
        private static final float VISCOUS_FLUID_OFFSET;
        private static final float VISCOUS_FLUID_SCALE = 8F;

        static 
        {
            VISCOUS_FLUID_NORMALIZE = 1.0F / viscousFluid(1.0F);
            VISCOUS_FLUID_OFFSET = 1.0F - VISCOUS_FLUID_NORMALIZE * viscousFluid(1.0F);
        }

        ViscousFluidInterpolator()
        {
        }
    }


    public Scroller(Context context)
    {
        this(context, null);
    }

    public Scroller(Context context, Interpolator interpolator)
    {
        boolean flag;
        if(context.getApplicationInfo().targetSdkVersion >= 11)
            flag = true;
        else
            flag = false;
        this(context, interpolator, flag);
    }

    public Scroller(Context context, Interpolator interpolator, boolean flag)
    {
        mFlingFriction = ViewConfiguration.getScrollFriction();
        mPerf = null;
        mFinished = true;
        mContext = context;
        if(interpolator == null)
            mInterpolator = new ViscousFluidInterpolator();
        else
            mInterpolator = interpolator;
        mPpi = context.getResources().getDisplayMetrics().density * 160F;
        mDeceleration = computeDeceleration(ViewConfiguration.getScrollFriction());
        mFlywheel = flag;
        mPhysicalCoeff = computeDeceleration(0.84F);
        if(mPerf == null)
            mPerf = new BoostFramework();
    }

    private float computeDeceleration(float f)
    {
        return mPpi * 386.0878F * f;
    }

    private double getSplineDeceleration(float f)
    {
        return Math.log((Math.abs(f) * 0.35F) / (mFlingFriction * mPhysicalCoeff));
    }

    private double getSplineFlingDistance(float f)
    {
        double d = getSplineDeceleration(f);
        double d1 = DECELERATION_RATE;
        return (double)(mFlingFriction * mPhysicalCoeff) * Math.exp(((double)DECELERATION_RATE / (d1 - 1.0D)) * d);
    }

    private int getSplineFlingDuration(float f)
    {
        return (int)(Math.exp(getSplineDeceleration(f) / ((double)DECELERATION_RATE - 1.0D)) * 1000D);
    }

    public void abortAnimation()
    {
        mCurrX = mFinalX;
        mCurrY = mFinalY;
        mFinished = true;
    }

    public boolean computeScrollOffset()
    {
        int i;
        if(mFinished)
            return false;
        i = (int)(AnimationUtils.currentAnimationTimeMillis() - mStartTime);
        if(i >= mDuration) goto _L2; else goto _L1
_L1:
        mMode;
        JVM INSTR tableswitch 0 1: default 52
    //                   0 54
    //                   1 110;
           goto _L3 _L4 _L5
_L3:
        return true;
_L4:
        float f = mInterpolator.getInterpolation((float)i * mDurationReciprocal);
        mCurrX = mStartX + Math.round(mDeltaX * f);
        mCurrY = mStartY + Math.round(mDeltaY * f);
        continue; /* Loop/switch isn't completed */
_L5:
        float f2 = (float)i / (float)mDuration;
        i = (int)(100F * f2);
        float f3 = 1.0F;
        float f1 = 0.0F;
        if(i < 100)
        {
            float f4 = (float)i / 100F;
            f1 = (float)(i + 1) / 100F;
            f3 = SPLINE_POSITION[i];
            f1 = (SPLINE_POSITION[i + 1] - f3) / (f1 - f4);
            f3 += (f2 - f4) * f1;
        }
        mCurrVelocity = (((float)mDistance * f1) / (float)mDuration) * 1000F;
        mCurrX = mStartX + Math.round((float)(mFinalX - mStartX) * f3);
        mCurrX = Math.min(mCurrX, mMaxX);
        mCurrX = Math.max(mCurrX, mMinX);
        mCurrY = mStartY + Math.round((float)(mFinalY - mStartY) * f3);
        mCurrY = Math.min(mCurrY, mMaxY);
        mCurrY = Math.max(mCurrY, mMinY);
        if(mCurrX == mFinalX && mCurrY == mFinalY)
            mFinished = true;
        continue; /* Loop/switch isn't completed */
_L2:
        mCurrX = mFinalX;
        mCurrY = mFinalY;
        mFinished = true;
        if(true) goto _L3; else goto _L6
_L6:
    }

    public void extendDuration(int i)
    {
        mDuration = timePassed() + i;
        mDurationReciprocal = 1.0F / (float)mDuration;
        mFinished = false;
    }

    public void fling(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1)
    {
        int i2 = k;
        int j2 = l;
        if(mFlywheel)
        {
            i2 = k;
            j2 = l;
            if(mFinished ^ true)
            {
                float f = getCurrVelocity();
                float f2 = mFinalX - mStartX;
                float f4 = mFinalY - mStartY;
                float f6 = (float)Math.hypot(f2, f4);
                f2 /= f6;
                f4 /= f6;
                f2 *= f;
                f = f4 * f;
                i2 = k;
                j2 = l;
                if(Math.signum(k) == Math.signum(f2))
                {
                    i2 = k;
                    j2 = l;
                    if(Math.signum(l) == Math.signum(f))
                    {
                        i2 = (int)((float)k + f2);
                        j2 = (int)((float)l + f);
                    }
                }
            }
        }
        mMode = 1;
        mFinished = false;
        float f5 = (float)Math.hypot(i2, j2);
        mVelocity = f5;
        mDuration = getSplineFlingDuration(f5);
        mStartTime = AnimationUtils.currentAnimationTimeMillis();
        mStartX = i;
        mStartY = j;
        float f1;
        float f3;
        double d;
        if(f5 == 0.0F)
            f1 = 1.0F;
        else
            f1 = (float)i2 / f5;
        if(f5 == 0.0F)
            f3 = 1.0F;
        else
            f3 = (float)j2 / f5;
        d = getSplineFlingDistance(f5);
        mDistance = (int)((double)Math.signum(f5) * d);
        mMinX = i1;
        mMaxX = j1;
        mMinY = k1;
        mMaxY = l1;
        mFinalX = (int)Math.round((double)f1 * d) + i;
        mFinalX = Math.min(mFinalX, mMaxX);
        mFinalX = Math.max(mFinalX, mMinX);
        mFinalY = (int)Math.round((double)f3 * d) + j;
        mFinalY = Math.min(mFinalY, mMaxY);
        mFinalY = Math.max(mFinalY, mMinY);
    }

    public final void forceFinished(boolean flag)
    {
        mFinished = flag;
    }

    public float getCurrVelocity()
    {
        float f;
        if(mMode == 1)
            f = mCurrVelocity;
        else
            f = mVelocity - (mDeceleration * (float)timePassed()) / 2000F;
        return f;
    }

    public final int getCurrX()
    {
        return mCurrX;
    }

    public final int getCurrY()
    {
        return mCurrY;
    }

    public final int getDuration()
    {
        return mDuration;
    }

    public final int getFinalX()
    {
        return mFinalX;
    }

    public final int getFinalY()
    {
        return mFinalY;
    }

    public final int getStartX()
    {
        return mStartX;
    }

    public final int getStartY()
    {
        return mStartY;
    }

    public final boolean isFinished()
    {
        return mFinished;
    }

    public boolean isScrollingInDirection(float f, float f1)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(!mFinished)
        {
            flag1 = flag;
            if(Math.signum(f) == Math.signum(mFinalX - mStartX))
            {
                flag1 = flag;
                if(Math.signum(f1) == Math.signum(mFinalY - mStartY))
                    flag1 = true;
            }
        }
        return flag1;
    }

    public void setFinalX(int i)
    {
        mFinalX = i;
        mDeltaX = mFinalX - mStartX;
        mFinished = false;
    }

    public void setFinalY(int i)
    {
        mFinalY = i;
        mDeltaY = mFinalY - mStartY;
        mFinished = false;
    }

    public final void setFriction(float f)
    {
        mDeceleration = computeDeceleration(f);
        mFlingFriction = f;
    }

    public void startScroll(int i, int j, int k, int l)
    {
        startScroll(i, j, k, l, 250);
    }

    public void startScroll(int i, int j, int k, int l, int i1)
    {
        mMode = 0;
        mFinished = false;
        mDuration = i1;
        mStartTime = AnimationUtils.currentAnimationTimeMillis();
        mStartX = i;
        mStartY = j;
        mFinalX = i + k;
        mFinalY = j + l;
        mDeltaX = k;
        mDeltaY = l;
        mDurationReciprocal = 1.0F / (float)mDuration;
        if(mPerf != null && i1 != 0)
        {
            String s = mContext.getPackageName();
            mPerf.perfHint(4224, s, mDuration, 2);
        }
    }

    public int timePassed()
    {
        return (int)(AnimationUtils.currentAnimationTimeMillis() - mStartTime);
    }

    private static float DECELERATION_RATE = 0F;
    private static final int DEFAULT_DURATION = 250;
    private static final float END_TENSION = 1F;
    private static final int FLING_MODE = 1;
    private static final float INFLEXION = 0.35F;
    private static final int NB_SAMPLES = 100;
    private static final float P1 = 0.175F;
    private static final float P2 = 0.35F;
    private static final int SCROLL_MODE = 0;
    private static final float SPLINE_POSITION[];
    private static final float SPLINE_TIME[];
    private static final float START_TENSION = 0.5F;
    private Context mContext;
    private float mCurrVelocity;
    private int mCurrX;
    private int mCurrY;
    private float mDeceleration;
    private float mDeltaX;
    private float mDeltaY;
    private int mDistance;
    private int mDuration;
    private float mDurationReciprocal;
    private int mFinalX;
    private int mFinalY;
    private boolean mFinished;
    private float mFlingFriction;
    private boolean mFlywheel;
    private final Interpolator mInterpolator;
    private int mMaxX;
    private int mMaxY;
    private int mMinX;
    private int mMinY;
    private int mMode;
    private BoostFramework mPerf;
    private float mPhysicalCoeff;
    private final float mPpi;
    private long mStartTime;
    private int mStartX;
    private int mStartY;
    private float mVelocity;

    static 
    {
        float f;
        float f1;
        int i;
        DECELERATION_RATE = (float)(Math.log(0.78000000000000003D) / Math.log(0.90000000000000002D));
        SPLINE_POSITION = new float[101];
        SPLINE_TIME = new float[101];
        f = 0.0F;
        f1 = 0.0F;
        i = 0;
_L6:
        float f2;
        float f3;
        if(i >= 100)
            break MISSING_BLOCK_LABEL_283;
        f2 = (float)i / 100F;
        f3 = 1.0F;
_L3:
        float f4;
        float f5;
        float f7;
        f4 = f + (f3 - f) / 2.0F;
        f5 = 3F * f4 * (1.0F - f4);
        f7 = ((1.0F - f4) * 0.175F + 0.35F * f4) * f5 + f4 * f4 * f4;
        if((double)Math.abs(f7 - f2) >= 1.0000000000000001E-005D) goto _L2; else goto _L1
_L1:
        SPLINE_POSITION[i] = ((1.0F - f4) * 0.5F + f4) * f5 + f4 * f4 * f4;
        f3 = 1.0F;
_L4:
        f4 = f1 + (f3 - f1) / 2.0F;
        float f6 = 3F * f4 * (1.0F - f4);
        f7 = ((1.0F - f4) * 0.5F + f4) * f6 + f4 * f4 * f4;
        if((double)Math.abs(f7 - f2) < 1.0000000000000001E-005D)
        {
            SPLINE_TIME[i] = ((1.0F - f4) * 0.175F + 0.35F * f4) * f6 + f4 * f4 * f4;
            i++;
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_263;
_L2:
        if(f7 > f2)
            f3 = f4;
        else
            f = f4;
          goto _L3
        if(f7 > f2)
            f3 = f4;
        else
            f1 = f4;
          goto _L4
        float af[] = SPLINE_POSITION;
        SPLINE_TIME[100] = 1.0F;
        af[100] = 1.0F;
        if(true) goto _L6; else goto _L5
_L5:
    }
}

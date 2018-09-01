// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.*;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

public class OverScroller
{
    static class SplineOverScroller
    {

        static float _2D_get0(SplineOverScroller splineoverscroller)
        {
            return splineoverscroller.mCurrVelocity;
        }

        static int _2D_get1(SplineOverScroller splineoverscroller)
        {
            return splineoverscroller.mCurrentPosition;
        }

        static int _2D_get2(SplineOverScroller splineoverscroller)
        {
            return splineoverscroller.mDuration;
        }

        static int _2D_get3(SplineOverScroller splineoverscroller)
        {
            return splineoverscroller.mFinal;
        }

        static boolean _2D_get4(SplineOverScroller splineoverscroller)
        {
            return splineoverscroller.mFinished;
        }

        static int _2D_get5(SplineOverScroller splineoverscroller)
        {
            return splineoverscroller.mStart;
        }

        static long _2D_get6(SplineOverScroller splineoverscroller)
        {
            return splineoverscroller.mStartTime;
        }

        static int _2D_get7(SplineOverScroller splineoverscroller)
        {
            return splineoverscroller.mState;
        }

        static boolean _2D_set0(SplineOverScroller splineoverscroller, boolean flag)
        {
            splineoverscroller.mFinished = flag;
            return flag;
        }

        private void adjustDuration(int i, int j, int k)
        {
            float f = Math.abs((float)(k - i) / (float)(j - i));
            i = (int)(100F * f);
            if(i < 100)
            {
                float f1 = (float)i / 100F;
                float f2 = (float)(i + 1) / 100F;
                float f3 = SPLINE_TIME[i];
                float f4 = SPLINE_TIME[i + 1];
                f1 = (f - f1) / (f2 - f1);
                mDuration = (int)((float)mDuration * (f3 + f1 * (f4 - f3)));
            }
        }

        private void fitOnBounceCurve(int i, int j, int k)
        {
            float f = (float)(-k) / mDeceleration;
            float f1 = (float)Math.sqrt(((double)(((float)k * (float)k) / 2.0F / Math.abs(mDeceleration) + (float)Math.abs(j - i)) * 2D) / (double)Math.abs(mDeceleration));
            mStartTime = mStartTime - (long)(int)((f1 - f) * 1000F);
            mStart = j;
            mCurrentPosition = j;
            mVelocity = (int)(-mDeceleration * f1);
        }

        private static float getDeceleration(int i)
        {
            float f;
            if(i > 0)
                f = -2000F;
            else
                f = 2000F;
            return f;
        }

        private double getSplineDeceleration(int i)
        {
            return Math.log(((float)Math.abs(i) * 0.35F) / (mFlingFriction * mPhysicalCoeff));
        }

        private double getSplineFlingDistance(int i)
        {
            double d = getSplineDeceleration(i);
            double d1 = DECELERATION_RATE;
            return (double)(mFlingFriction * mPhysicalCoeff) * Math.exp(((double)DECELERATION_RATE / (d1 - 1.0D)) * d);
        }

        private int getSplineFlingDuration(int i)
        {
            return (int)(Math.exp(getSplineDeceleration(i) / ((double)DECELERATION_RATE - 1.0D)) * 1000D);
        }

        private void onEdgeReached()
        {
            float f = (float)mVelocity * (float)mVelocity;
            float f1 = f / (Math.abs(mDeceleration) * 2.0F);
            float f2 = Math.signum(mVelocity);
            float f3 = f1;
            if(f1 > (float)mOver)
            {
                mDeceleration = (-f2 * f) / ((float)mOver * 2.0F);
                f3 = mOver;
            }
            mOver = (int)f3;
            mState = 2;
            int i = mStart;
            if(mVelocity <= 0)
                f3 = -f3;
            mFinal = i + (int)f3;
            mDuration = -(int)(((float)mVelocity * 1000F) / mDeceleration);
        }

        private void startAfterEdge(int i, int j, int k, int l)
        {
            if(i > j && i < k)
            {
                Log.e("OverScroller", "startAfterEdge called from a valid position");
                mFinished = true;
                return;
            }
            boolean flag;
            int i1;
            int j1;
            boolean flag1;
            if(i > k)
                flag = true;
            else
                flag = false;
            if(flag)
                i1 = k;
            else
                i1 = j;
            j1 = i - i1;
            if(j1 * l >= 0)
                flag1 = true;
            else
                flag1 = false;
            if(flag1)
                startBounceAfterEdge(i, i1, l);
            else
            if(getSplineFlingDistance(l) > (double)Math.abs(j1))
            {
                if(!flag)
                    j = i;
                if(flag)
                    k = i;
                fling(i, l, j, k, mOver);
            } else
            {
                startSpringback(i, i1, l);
            }
        }

        private void startBounceAfterEdge(int i, int j, int k)
        {
            int l;
            if(k == 0)
                l = i - j;
            else
                l = k;
            mDeceleration = getDeceleration(l);
            fitOnBounceCurve(i, j, k);
            mDeceleration = mDeceleration * mDecelerationScale;
            onEdgeReached();
        }

        private void startSpringback(int i, int j, int k)
        {
            mFinished = false;
            mState = 1;
            mStart = i;
            mCurrentPosition = i;
            mFinal = j;
            i -= j;
            mDeceleration = getDeceleration(i) * mDecelerationScale;
            mVelocity = -i;
            mOver = Math.abs(i);
            mDuration = (int)(Math.sqrt(((double)i * -2D) / (double)mDeceleration) * 1000D);
        }

        boolean continueWhenFinished()
        {
            mState;
            JVM INSTR tableswitch 0 2: default 32
        //                       0 39
        //                       1 138
        //                       2 108;
               goto _L1 _L2 _L3 _L4
_L1:
            update();
            return true;
_L2:
            if(mDuration < mSplineDuration)
            {
                int i = mFinal;
                mStart = i;
                mCurrentPosition = i;
                mVelocity = (int)mCurrVelocity;
                mDeceleration = getDeceleration(mVelocity);
                mStartTime = mStartTime + (long)mDuration;
                onEdgeReached();
            } else
            {
                return false;
            }
            continue; /* Loop/switch isn't completed */
_L4:
            mStartTime = mStartTime + (long)mDuration;
            startSpringback(mFinal, mStart, 0);
            if(true) goto _L1; else goto _L3
_L3:
            return false;
        }

        boolean continueWhenFinished(boolean flag)
        {
            boolean flag1;
            boolean flag2;
            flag1 = false;
            flag2 = false;
            mState;
            JVM INSTR tableswitch 0 2: default 36
        //                       0 44
        //                       1 176
        //                       2 106;
               goto _L1 _L2 _L3 _L4
_L1:
            update(flag2);
            return true;
_L2:
            if(mDuration < mSplineDuration)
            {
                mStart = mFinal;
                mVelocity = (int)mCurrVelocity;
                mDeceleration = getDeceleration(mVelocity);
                mStartTime = mStartTime + (long)mDuration;
                onEdgeReached();
            } else
            {
                return false;
            }
            continue; /* Loop/switch isn't completed */
_L4:
            flag2 = flag1;
            if(flag)
            {
                mDecelerationScale = (float)(Math.pow((float)Math.abs(mOver) / (float)mTotalOverDistance, 3D) * 20D) + 1.0F;
                flag2 = true;
            }
            mStartTime = mStartTime + (long)mDuration;
            startSpringback(mFinal, mStart, 0);
            if(true) goto _L1; else goto _L3
_L3:
            return false;
        }

        void extendDuration(int i)
        {
            mDuration = (int)(AnimationUtils.currentAnimationTimeMillis() - mStartTime) + i;
            mFinished = false;
        }

        void finish()
        {
            if(mIsPerfLockAcquired && mPerf != null)
            {
                mPerf.perfLockRelease();
                mIsPerfLockAcquired = false;
            }
            mCurrentPosition = mFinal;
            mFinished = true;
        }

        void fling(int i, int j, int k, int l, int i1)
        {
            mOver = i1;
            mFinished = false;
            mVelocity = j;
            mCurrVelocity = j;
            mSplineDuration = 0;
            mDuration = 0;
            mStartTime = AnimationUtils.currentAnimationTimeMillis();
            mStart = i;
            mCurrentPosition = i;
            if(mIsPerfLockAcquired && mPerf != null)
            {
                mPerf.perfLockRelease();
                mIsPerfLockAcquired = false;
            }
            if(i > l || i < k)
            {
                startAfterEdge(i, k, l, j);
                return;
            }
            mState = 0;
            double d = 0.0D;
            if(j != 0)
            {
                i1 = getSplineFlingDuration(j);
                mSplineDuration = i1;
                mDuration = i1;
                d = getSplineFlingDistance(j);
            }
            mSplineDistance = (int)((double)Math.signum(j) * d);
            mFinal = mSplineDistance + i;
            if(mFinal < k)
            {
                adjustDuration(mStart, mFinal, k);
                mFinal = k;
            }
            if(mFinal > l)
            {
                adjustDuration(mStart, mFinal, l);
                mFinal = l;
            }
        }

        void notifyEdgeReached(int i, int j, int k)
        {
            if(mState == 0)
            {
                mOver = k;
                mStartTime = AnimationUtils.currentAnimationTimeMillis();
                startAfterEdge(i, j, j, (int)mCurrVelocity);
            }
        }

        void setDecelerationScale(float f)
        {
            mDecelerationScale = f;
        }

        void setFinalPosition(int i)
        {
            mFinal = i;
            mFinished = false;
        }

        void setFriction(float f)
        {
            mFlingFriction = f;
        }

        void setTotalOverDistance(int i)
        {
            mTotalOverDistance = i;
        }

        boolean springback(int i, int j, int k)
        {
            mFinished = true;
            mFinal = i;
            mStart = i;
            mCurrentPosition = i;
            mVelocity = 0;
            mStartTime = AnimationUtils.currentAnimationTimeMillis();
            mDuration = 0;
            if(i >= j) goto _L2; else goto _L1
_L1:
            startSpringback(i, j, 0);
_L4:
            return mFinished ^ true;
_L2:
            if(i > k)
                startSpringback(i, k, 0);
            if(true) goto _L4; else goto _L3
_L3:
        }

        void startScroll(int i, int j, int k)
        {
            mFinished = false;
            mStart = i;
            mCurrentPosition = i;
            mFinal = i + j;
            mStartTime = AnimationUtils.currentAnimationTimeMillis();
            mDuration = k;
            mDeceleration = 0.0F;
            mVelocity = 0;
        }

        boolean update()
        {
            long l;
            double d;
            l = AnimationUtils.currentAnimationTimeMillis() - mStartTime;
            if(l == 0L)
            {
                boolean flag;
                if(mDuration > 0)
                    flag = true;
                else
                    flag = false;
                return flag;
            }
            if(l > (long)mDuration)
                return false;
            if(mPerf != null && mIsPerfLockAcquired ^ true)
            {
                String s = mContext.getPackageName();
                mIsPerfLockAcquired = true;
                mPerf.perfHint(4224, s, mDuration, 1);
            }
            d = 0.0D;
            mState;
            JVM INSTR tableswitch 0 2: default 124
        //                       0 141
        //                       1 316
        //                       2 265;
               goto _L1 _L2 _L3 _L4
_L1:
            mCurrentPosition = mStart + (int)Math.round(d);
            return true;
_L2:
            float f = (float)l / (float)mSplineDuration;
            int i = (int)(100F * f);
            float f2 = 1.0F;
            float f4 = 0.0F;
            if(i < 100)
            {
                float f7 = (float)i / 100F;
                f4 = (float)(i + 1) / 100F;
                f2 = SPLINE_POSITION[i];
                f4 = (SPLINE_POSITION[i + 1] - f2) / (f4 - f7);
                f2 += (f - f7) * f4;
            }
            d = (float)mSplineDistance * f2;
            mCurrVelocity = (((float)mSplineDistance * f4) / (float)mSplineDuration) * 1000F;
            continue; /* Loop/switch isn't completed */
_L4:
            float f5 = (float)l / 1000F;
            mCurrVelocity = (float)mVelocity + mDeceleration * f5;
            d = (float)mVelocity * f5 + (mDeceleration * f5 * f5) / 2.0F;
            continue; /* Loop/switch isn't completed */
_L3:
            float f3 = (float)l / (float)mDuration;
            float f1 = f3 * f3;
            float f6 = Math.signum(mVelocity);
            d = (float)mOver * f6 * (3F * f1 - 2.0F * f3 * f1);
            mCurrVelocity = (float)mOver * f6 * 6F * (-f3 + f1);
            if(true) goto _L1; else goto _L5
_L5:
        }

        boolean update(boolean flag)
        {
            long l;
            double d;
            l = AnimationUtils.currentAnimationTimeMillis() - mStartTime;
            if(!flag && l > (long)mDuration)
                return false;
            if(mPerf != null && mIsPerfLockAcquired ^ true)
            {
                String s = mContext.getPackageName();
                mIsPerfLockAcquired = true;
                mPerf.perfHint(4224, s, mDuration, 1);
            }
            d = 0.0D;
            mState;
            JVM INSTR tableswitch 0 2: default 108
        //                       0 125
        //                       1 300
        //                       2 249;
               goto _L1 _L2 _L3 _L4
_L1:
            mCurrentPosition = mStart + (int)Math.round(d);
            return true;
_L2:
            float f = (float)l / (float)mSplineDuration;
            int i = (int)(100F * f);
            float f2 = 1.0F;
            float f5 = 0.0F;
            if(i < 100)
            {
                float f7 = (float)i / 100F;
                f5 = (float)(i + 1) / 100F;
                f2 = SPLINE_POSITION[i];
                f5 = (SPLINE_POSITION[i + 1] - f2) / (f5 - f7);
                f2 += (f - f7) * f5;
            }
            d = (float)mSplineDistance * f2;
            mCurrVelocity = (((float)mSplineDistance * f5) / (float)mSplineDuration) * 1000F;
            continue; /* Loop/switch isn't completed */
_L4:
            float f3 = (float)l / 1000F;
            mCurrVelocity = (float)mVelocity + mDeceleration * f3;
            d = (float)mVelocity * f3 + (mDeceleration * f3 * f3) / 2.0F;
            continue; /* Loop/switch isn't completed */
_L3:
            float f6 = (float)l / (float)mDuration;
            float f1 = f6 * f6;
            float f4 = Math.signum(mVelocity);
            d = (float)mOver * f4 * (3F * f1 - 2.0F * f6 * f1);
            mCurrVelocity = (float)mOver * f4 * 6F * (-f6 + f1);
            if(true) goto _L1; else goto _L5
_L5:
        }

        void updateScroll(float f)
        {
            mCurrentPosition = mStart + Math.round((float)(mFinal - mStart) * f);
        }

        private static final int BALLISTIC = 2;
        private static final int CUBIC = 1;
        private static float DECELERATION_RATE = 0F;
        private static final float END_TENSION = 1F;
        private static final float GRAVITY = 2000F;
        private static final float INFLEXION = 0.35F;
        private static final int NB_SAMPLES = 100;
        private static final float P1 = 0.175F;
        private static final float P2 = 0.35F;
        private static final int SPLINE = 0;
        private static final float SPLINE_POSITION[];
        private static final float SPLINE_TIME[];
        private static final float START_TENSION = 0.5F;
        private Context mContext;
        private float mCurrVelocity;
        private int mCurrentPosition;
        private float mDeceleration;
        private float mDecelerationScale;
        private int mDuration;
        private int mFinal;
        private boolean mFinished;
        private float mFlingFriction;
        private boolean mIsPerfLockAcquired;
        private int mOver;
        private BoostFramework mPerf;
        private float mPhysicalCoeff;
        private int mSplineDistance;
        private int mSplineDuration;
        private int mStart;
        private long mStartTime;
        private int mState;
        private int mTotalOverDistance;
        private int mVelocity;

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
            float f6;
            f4 = f + (f3 - f) / 2.0F;
            f5 = 3F * f4 * (1.0F - f4);
            f6 = ((1.0F - f4) * 0.175F + 0.35F * f4) * f5 + f4 * f4 * f4;
            if((double)Math.abs(f6 - f2) >= 1.0000000000000001E-005D) goto _L2; else goto _L1
_L1:
            SPLINE_POSITION[i] = ((1.0F - f4) * 0.5F + f4) * f5 + f4 * f4 * f4;
            f3 = 1.0F;
_L4:
            f4 = f1 + (f3 - f1) / 2.0F;
            f6 = 3F * f4 * (1.0F - f4);
            f5 = ((1.0F - f4) * 0.5F + f4) * f6 + f4 * f4 * f4;
            if((double)Math.abs(f5 - f2) < 1.0000000000000001E-005D)
            {
                SPLINE_TIME[i] = ((1.0F - f4) * 0.175F + 0.35F * f4) * f6 + f4 * f4 * f4;
                i++;
                continue; /* Loop/switch isn't completed */
            }
            break MISSING_BLOCK_LABEL_263;
_L2:
            if(f6 > f2)
                f3 = f4;
            else
                f = f4;
              goto _L3
            if(f5 > f2)
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

        SplineOverScroller(Context context)
        {
            mFlingFriction = ViewConfiguration.getScrollFriction();
            mState = 0;
            mDecelerationScale = 1.0F;
            mPerf = null;
            mIsPerfLockAcquired = false;
            mContext = context;
            mFinished = true;
            mPhysicalCoeff = 386.0878F * (context.getResources().getDisplayMetrics().density * 160F) * 0.84F;
            if(mPerf == null)
                mPerf = new BoostFramework();
        }
    }


    public OverScroller(Context context)
    {
        this(context, null);
    }

    public OverScroller(Context context, Interpolator interpolator)
    {
        this(context, interpolator, true);
    }

    public OverScroller(Context context, Interpolator interpolator, float f, float f1)
    {
        this(context, interpolator, true);
    }

    public OverScroller(Context context, Interpolator interpolator, float f, float f1, boolean flag)
    {
        this(context, interpolator, flag);
    }

    public OverScroller(Context context, Interpolator interpolator, boolean flag)
    {
        if(interpolator == null)
            mInterpolator = new Scroller.ViscousFluidInterpolator();
        else
            mInterpolator = interpolator;
        mFlywheel = flag;
        mScrollerX = new SplineOverScroller(context);
        mScrollerY = new SplineOverScroller(context);
    }

    public void abortAnimation()
    {
        mScrollerX.finish();
        mScrollerY.finish();
    }

    public final boolean checkSpringBackState()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(SplineOverScroller._2D_get7(mScrollerY) != 1)
            if(SplineOverScroller._2D_get7(mScrollerX) == 1)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public boolean computeScrollOffset()
    {
        if(isFinished())
            return false;
        mMode;
        JVM INSTR tableswitch 0 1: default 36
    //                   0 38
    //                   1 109;
           goto _L1 _L2 _L3
_L1:
        return true;
_L2:
        long l = AnimationUtils.currentAnimationTimeMillis() - SplineOverScroller._2D_get6(mScrollerX);
        int i = SplineOverScroller._2D_get2(mScrollerX);
        if(l < (long)i)
        {
            float f = mInterpolator.getInterpolation((float)l / (float)i);
            mScrollerX.updateScroll(f);
            mScrollerY.updateScroll(f);
        } else
        {
            abortAnimation();
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(!SplineOverScroller._2D_get4(mScrollerX) && !mScrollerX.update(false) && !mScrollerX.continueWhenFinished(mSpringOverScrollEnable))
            mScrollerX.finish();
        if(!SplineOverScroller._2D_get4(mScrollerY) && !mScrollerY.update(false) && !mScrollerY.continueWhenFinished(mSpringOverScrollEnable))
            mScrollerY.finish();
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void extendDuration(int i)
    {
        mScrollerX.extendDuration(i);
        mScrollerY.extendDuration(i);
    }

    public void fling(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1)
    {
        fling(i, j, k, l, i1, j1, k1, l1, 0, 0);
    }

    public void fling(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, int i2, int j2)
    {
        int k2 = k;
        int l2 = l;
        if(mFlywheel)
        {
            k2 = k;
            l2 = l;
            if(isFinished() ^ true)
            {
                float f = SplineOverScroller._2D_get0(mScrollerX);
                float f1 = SplineOverScroller._2D_get0(mScrollerY);
                k2 = k;
                l2 = l;
                if(Math.signum(k) == Math.signum(f))
                {
                    k2 = k;
                    l2 = l;
                    if(Math.signum(l) == Math.signum(f1))
                    {
                        k2 = (int)((float)k + f);
                        l2 = (int)((float)l + f1);
                    }
                }
            }
        }
        mMode = 1;
        mScrollerX.fling(i, k2, i1, j1, i2);
        mScrollerY.fling(j, l2, k1, l1, j2);
    }

    public final void forceFinished(boolean flag)
    {
        SplineOverScroller._2D_set0(mScrollerX, SplineOverScroller._2D_set0(mScrollerY, flag));
    }

    public float getCurrVelocity()
    {
        return (float)Math.hypot(SplineOverScroller._2D_get0(mScrollerX), SplineOverScroller._2D_get0(mScrollerY));
    }

    public final int getCurrX()
    {
        return SplineOverScroller._2D_get1(mScrollerX);
    }

    public final int getCurrY()
    {
        return SplineOverScroller._2D_get1(mScrollerY);
    }

    public float getCurrYVelocity()
    {
        return SplineOverScroller._2D_get0(mScrollerY);
    }

    public final int getDuration()
    {
        return Math.max(SplineOverScroller._2D_get2(mScrollerX), SplineOverScroller._2D_get2(mScrollerY));
    }

    public final int getFinalX()
    {
        return SplineOverScroller._2D_get3(mScrollerX);
    }

    public final int getFinalY()
    {
        return SplineOverScroller._2D_get3(mScrollerY);
    }

    public final int getStartX()
    {
        return SplineOverScroller._2D_get5(mScrollerX);
    }

    public final int getStartY()
    {
        return SplineOverScroller._2D_get5(mScrollerY);
    }

    public final boolean isFinished()
    {
        boolean flag;
        if(SplineOverScroller._2D_get4(mScrollerX))
            flag = SplineOverScroller._2D_get4(mScrollerY);
        else
            flag = false;
        return flag;
    }

    public boolean isOverScrolled()
    {
        boolean flag = true;
        if(SplineOverScroller._2D_get4(mScrollerX) || SplineOverScroller._2D_get7(mScrollerX) == 0) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(!SplineOverScroller._2D_get4(mScrollerY))
        {
            if(SplineOverScroller._2D_get7(mScrollerY) == 0)
                flag = false;
        } else
        {
            flag = false;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean isScrollingInDirection(float f, float f1)
    {
        boolean flag = false;
        int i = SplineOverScroller._2D_get3(mScrollerX);
        int j = SplineOverScroller._2D_get5(mScrollerX);
        int k = SplineOverScroller._2D_get3(mScrollerY);
        int l = SplineOverScroller._2D_get5(mScrollerY);
        boolean flag1 = flag;
        if(!isFinished())
        {
            flag1 = flag;
            if(Math.signum(f) == Math.signum(i - j))
            {
                flag1 = flag;
                if(Math.signum(f1) == Math.signum(k - l))
                    flag1 = true;
            }
        }
        return flag1;
    }

    public void notifyHorizontalEdgeReached(int i, int j, int k)
    {
        mScrollerX.notifyEdgeReached(i, j, k);
    }

    public void notifyVerticalEdgeReached(int i, int j, int k)
    {
        mScrollerY.notifyEdgeReached(i, j, k);
    }

    public final void setDecelerationScale(float f)
    {
        mScrollerX.setDecelerationScale(f);
        mScrollerY.setDecelerationScale(f);
    }

    public void setFinalX(int i)
    {
        mScrollerX.setFinalPosition(i);
    }

    public void setFinalY(int i)
    {
        mScrollerY.setFinalPosition(i);
    }

    public final void setFriction(float f)
    {
        mScrollerX.setFriction(f);
        mScrollerY.setFriction(f);
    }

    void setInterpolator(Interpolator interpolator)
    {
        if(interpolator == null)
            mInterpolator = new Scroller.ViscousFluidInterpolator();
        else
            mInterpolator = interpolator;
    }

    public void setSpringOverScrollEnable(boolean flag)
    {
        mSpringOverScrollEnable = flag;
    }

    public final void setTotalOverDistance(int i)
    {
        mScrollerX.setTotalOverDistance(i);
        mScrollerY.setTotalOverDistance(i);
    }

    public boolean springBack(int i, int j, int k, int l, int i1, int j1)
    {
        mMode = 1;
        boolean flag = mScrollerX.springback(i, k, l);
        boolean flag1 = mScrollerY.springback(j, i1, j1);
        if(flag)
            flag1 = true;
        return flag1;
    }

    public void startScroll(int i, int j, int k, int l)
    {
        startScroll(i, j, k, l, 250);
    }

    public void startScroll(int i, int j, int k, int l, int i1)
    {
        mMode = 0;
        mScrollerX.startScroll(i, k, i1);
        mScrollerY.startScroll(j, l, i1);
    }

    public int timePassed()
    {
        return (int)(AnimationUtils.currentAnimationTimeMillis() - Math.min(SplineOverScroller._2D_get6(mScrollerX), SplineOverScroller._2D_get6(mScrollerY)));
    }

    private static final int DEFAULT_DURATION = 250;
    private static final int FLING_MODE = 1;
    private static final int SCROLL_MODE = 0;
    private final boolean mFlywheel;
    private Interpolator mInterpolator;
    private int mMode;
    private final SplineOverScroller mScrollerX;
    private final SplineOverScroller mScrollerY;
    boolean mSpringOverScrollEnable;
}

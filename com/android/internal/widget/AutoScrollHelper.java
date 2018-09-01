// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.*;
import android.view.animation.*;
import android.widget.AbsListView;

public abstract class AutoScrollHelper
    implements android.view.View.OnTouchListener
{
    public static class AbsListViewAutoScroller extends AutoScrollHelper
    {

        public boolean canTargetScrollHorizontally(int i)
        {
            return false;
        }

        public boolean canTargetScrollVertically(int i)
        {
            AbsListView abslistview = mTarget;
            int j = abslistview.getCount();
            if(j == 0)
                return false;
            int k = abslistview.getChildCount();
            int l = abslistview.getFirstVisiblePosition();
            if(i > 0)
            {
                if(l + k >= j && abslistview.getChildAt(k - 1).getBottom() <= abslistview.getHeight())
                    return false;
            } else
            if(i < 0)
            {
                if(l <= 0 && abslistview.getChildAt(0).getTop() >= 0)
                    return false;
            } else
            {
                return false;
            }
            return true;
        }

        public void scrollTargetBy(int i, int j)
        {
            mTarget.scrollListBy(j);
        }

        private final AbsListView mTarget;

        public AbsListViewAutoScroller(AbsListView abslistview)
        {
            super(abslistview);
            mTarget = abslistview;
        }
    }

    private static class ClampedScroller
    {

        private float getValueAt(long l)
        {
            if(l < mStartTime)
                return 0.0F;
            if(mStopTime < 0L || l < mStopTime)
            {
                return AutoScrollHelper._2D_wrap1((float)(l - mStartTime) / (float)mRampUpDuration, 0.0F, 1.0F) * 0.5F;
            } else
            {
                long l1 = mStopTime;
                return (1.0F - mStopValue) + mStopValue * AutoScrollHelper._2D_wrap1((float)(l - l1) / (float)mEffectiveRampDown, 0.0F, 1.0F);
            }
        }

        private float interpolateValue(float f)
        {
            return -4F * f * f + 4F * f;
        }

        public void computeScrollDelta()
        {
            if(mDeltaTime == 0L)
            {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            } else
            {
                long l = AnimationUtils.currentAnimationTimeMillis();
                float f = interpolateValue(getValueAt(l));
                long l1 = l - mDeltaTime;
                mDeltaTime = l;
                mDeltaX = (int)((float)l1 * f * mTargetVelocityX);
                mDeltaY = (int)((float)l1 * f * mTargetVelocityY);
                return;
            }
        }

        public int getDeltaX()
        {
            return mDeltaX;
        }

        public int getDeltaY()
        {
            return mDeltaY;
        }

        public int getHorizontalDirection()
        {
            return (int)(mTargetVelocityX / Math.abs(mTargetVelocityX));
        }

        public int getVerticalDirection()
        {
            return (int)(mTargetVelocityY / Math.abs(mTargetVelocityY));
        }

        public boolean isFinished()
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(mStopTime > 0L)
            {
                flag1 = flag;
                if(AnimationUtils.currentAnimationTimeMillis() > mStopTime + (long)mEffectiveRampDown)
                    flag1 = true;
            }
            return flag1;
        }

        public void requestStop()
        {
            long l = AnimationUtils.currentAnimationTimeMillis();
            mEffectiveRampDown = AutoScrollHelper._2D_wrap2((int)(l - mStartTime), 0, mRampDownDuration);
            mStopValue = getValueAt(l);
            mStopTime = l;
        }

        public void setRampDownDuration(int i)
        {
            mRampDownDuration = i;
        }

        public void setRampUpDuration(int i)
        {
            mRampUpDuration = i;
        }

        public void setTargetVelocity(float f, float f1)
        {
            mTargetVelocityX = f;
            mTargetVelocityY = f1;
        }

        public void start()
        {
            mStartTime = AnimationUtils.currentAnimationTimeMillis();
            mStopTime = -1L;
            mDeltaTime = mStartTime;
            mStopValue = 0.5F;
            mDeltaX = 0;
            mDeltaY = 0;
        }

        private long mDeltaTime;
        private int mDeltaX;
        private int mDeltaY;
        private int mEffectiveRampDown;
        private int mRampDownDuration;
        private int mRampUpDuration;
        private long mStartTime;
        private long mStopTime;
        private float mStopValue;
        private float mTargetVelocityX;
        private float mTargetVelocityY;

        public ClampedScroller()
        {
            mStartTime = 0x8000000000000000L;
            mStopTime = -1L;
            mDeltaTime = 0L;
            mDeltaX = 0;
            mDeltaY = 0;
        }
    }

    private class ScrollAnimationRunnable
        implements Runnable
    {

        public void run()
        {
            if(!AutoScrollHelper._2D_get0(AutoScrollHelper.this))
                return;
            if(AutoScrollHelper._2D_get2(AutoScrollHelper.this))
            {
                AutoScrollHelper._2D_set2(AutoScrollHelper.this, false);
                AutoScrollHelper._2D_get3(AutoScrollHelper.this).start();
            }
            ClampedScroller clampedscroller = AutoScrollHelper._2D_get3(AutoScrollHelper.this);
            if(clampedscroller.isFinished() || AutoScrollHelper._2D_wrap0(AutoScrollHelper.this) ^ true)
            {
                AutoScrollHelper._2D_set0(AutoScrollHelper.this, false);
                return;
            }
            if(AutoScrollHelper._2D_get1(AutoScrollHelper.this))
            {
                AutoScrollHelper._2D_set1(AutoScrollHelper.this, false);
                AutoScrollHelper._2D_wrap3(AutoScrollHelper.this);
            }
            clampedscroller.computeScrollDelta();
            int i = clampedscroller.getDeltaX();
            int j = clampedscroller.getDeltaY();
            scrollTargetBy(i, j);
            AutoScrollHelper._2D_get4(AutoScrollHelper.this).postOnAnimation(this);
        }

        final AutoScrollHelper this$0;

        private ScrollAnimationRunnable()
        {
            this$0 = AutoScrollHelper.this;
            super();
        }

        ScrollAnimationRunnable(ScrollAnimationRunnable scrollanimationrunnable)
        {
            this();
        }
    }


    static boolean _2D_get0(AutoScrollHelper autoscrollhelper)
    {
        return autoscrollhelper.mAnimating;
    }

    static boolean _2D_get1(AutoScrollHelper autoscrollhelper)
    {
        return autoscrollhelper.mNeedsCancel;
    }

    static boolean _2D_get2(AutoScrollHelper autoscrollhelper)
    {
        return autoscrollhelper.mNeedsReset;
    }

    static ClampedScroller _2D_get3(AutoScrollHelper autoscrollhelper)
    {
        return autoscrollhelper.mScroller;
    }

    static View _2D_get4(AutoScrollHelper autoscrollhelper)
    {
        return autoscrollhelper.mTarget;
    }

    static boolean _2D_set0(AutoScrollHelper autoscrollhelper, boolean flag)
    {
        autoscrollhelper.mAnimating = flag;
        return flag;
    }

    static boolean _2D_set1(AutoScrollHelper autoscrollhelper, boolean flag)
    {
        autoscrollhelper.mNeedsCancel = flag;
        return flag;
    }

    static boolean _2D_set2(AutoScrollHelper autoscrollhelper, boolean flag)
    {
        autoscrollhelper.mNeedsReset = flag;
        return flag;
    }

    static boolean _2D_wrap0(AutoScrollHelper autoscrollhelper)
    {
        return autoscrollhelper.shouldAnimate();
    }

    static float _2D_wrap1(float f, float f1, float f2)
    {
        return constrain(f, f1, f2);
    }

    static int _2D_wrap2(int i, int j, int k)
    {
        return constrain(i, j, k);
    }

    static void _2D_wrap3(AutoScrollHelper autoscrollhelper)
    {
        autoscrollhelper.cancelTargetTouch();
    }

    public AutoScrollHelper(View view)
    {
        mTarget = view;
        view = Resources.getSystem().getDisplayMetrics();
        int i = (int)(((DisplayMetrics) (view)).density * 1575F + 0.5F);
        int j = (int)(((DisplayMetrics) (view)).density * 315F + 0.5F);
        setMaximumVelocity(i, i);
        setMinimumVelocity(j, j);
        setEdgeType(1);
        setMaximumEdges(3.402823E+038F, 3.402823E+038F);
        setRelativeEdges(0.2F, 0.2F);
        setRelativeVelocity(1.0F, 1.0F);
        setActivationDelay(DEFAULT_ACTIVATION_DELAY);
        setRampUpDuration(500);
        setRampDownDuration(500);
    }

    private void cancelTargetTouch()
    {
        long l = SystemClock.uptimeMillis();
        MotionEvent motionevent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
        mTarget.onTouchEvent(motionevent);
        motionevent.recycle();
    }

    private float computeTargetVelocity(int i, float f, float f1, float f2)
    {
        float f3 = getEdgeValue(mRelativeEdges[i], f1, mMaximumEdges[i], f);
        if(f3 == 0.0F)
            return 0.0F;
        float f4 = mRelativeVelocity[i];
        f1 = mMinimumVelocity[i];
        f = mMaximumVelocity[i];
        f2 = f4 * f2;
        if(f3 > 0.0F)
            return constrain(f3 * f2, f1, f);
        else
            return -constrain(-f3 * f2, f1, f);
    }

    private static float constrain(float f, float f1, float f2)
    {
        if(f > f2)
            return f2;
        if(f < f1)
            return f1;
        else
            return f;
    }

    private static int constrain(int i, int j, int k)
    {
        if(i > k)
            return k;
        if(i < j)
            return j;
        else
            return i;
    }

    private float constrainEdgeValue(float f, float f1)
    {
        if(f1 == 0.0F)
            return 0.0F;
        mEdgeType;
        JVM INSTR tableswitch 0 2: default 40
    //                   0 42
    //                   1 42
    //                   2 77;
           goto _L1 _L2 _L2 _L3
_L1:
        return 0.0F;
_L2:
        if(f < f1)
        {
            if(f >= 0.0F)
                return 1.0F - f / f1;
            if(mAnimating && mEdgeType == 1)
                return 1.0F;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(f < 0.0F)
            return f / -f1;
        if(true) goto _L1; else goto _L4
_L4:
    }

    private float getEdgeValue(float f, float f1, float f2, float f3)
    {
        f = constrain(f * f1, 0.0F, f2);
        f2 = constrainEdgeValue(f3, f);
        f = constrainEdgeValue(f1 - f3, f) - f2;
        if(f < 0.0F)
            f = -mEdgeInterpolator.getInterpolation(-f);
        else
        if(f > 0.0F)
            f = mEdgeInterpolator.getInterpolation(f);
        else
            return 0.0F;
        return constrain(f, -1F, 1.0F);
    }

    private void requestStop()
    {
        if(mNeedsReset)
            mAnimating = false;
        else
            mScroller.requestStop();
    }

    private boolean shouldAnimate()
    {
        boolean flag = false;
        ClampedScroller clampedscroller = mScroller;
        int i = clampedscroller.getVerticalDirection();
        int j = clampedscroller.getHorizontalDirection();
        if(i == 0 || !canTargetScrollVertically(i))
        {
            if(j != 0)
                flag = canTargetScrollHorizontally(j);
        } else
        {
            flag = true;
        }
        return flag;
    }

    private void startAnimating()
    {
        if(mRunnable == null)
            mRunnable = new ScrollAnimationRunnable(null);
        mAnimating = true;
        mNeedsReset = true;
        if(!mAlreadyDelayed && mActivationDelay > 0)
            mTarget.postOnAnimationDelayed(mRunnable, mActivationDelay);
        else
            mRunnable.run();
        mAlreadyDelayed = true;
    }

    public abstract boolean canTargetScrollHorizontally(int i);

    public abstract boolean canTargetScrollVertically(int i);

    public boolean isEnabled()
    {
        return mEnabled;
    }

    public boolean isExclusive()
    {
        return mExclusive;
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        boolean flag;
        flag = false;
        if(!mEnabled)
            return false;
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 3: default 44
    //                   0 58
    //                   1 148
    //                   2 68
    //                   3 148;
           goto _L1 _L2 _L3 _L4 _L3
_L1:
        if(mExclusive)
            flag = mAnimating;
        return flag;
_L2:
        mNeedsCancel = true;
        mAlreadyDelayed = false;
_L4:
        float f = computeTargetVelocity(0, motionevent.getX(), view.getWidth(), mTarget.getWidth());
        float f1 = computeTargetVelocity(1, motionevent.getY(), view.getHeight(), mTarget.getHeight());
        mScroller.setTargetVelocity(f, f1);
        if(!mAnimating && shouldAnimate())
            startAnimating();
        continue; /* Loop/switch isn't completed */
_L3:
        requestStop();
        if(true) goto _L1; else goto _L5
_L5:
    }

    public abstract void scrollTargetBy(int i, int j);

    public AutoScrollHelper setActivationDelay(int i)
    {
        mActivationDelay = i;
        return this;
    }

    public AutoScrollHelper setEdgeType(int i)
    {
        mEdgeType = i;
        return this;
    }

    public AutoScrollHelper setEnabled(boolean flag)
    {
        if(mEnabled && flag ^ true)
            requestStop();
        mEnabled = flag;
        return this;
    }

    public AutoScrollHelper setExclusive(boolean flag)
    {
        mExclusive = flag;
        return this;
    }

    public AutoScrollHelper setMaximumEdges(float f, float f1)
    {
        mMaximumEdges[0] = f;
        mMaximumEdges[1] = f1;
        return this;
    }

    public AutoScrollHelper setMaximumVelocity(float f, float f1)
    {
        mMaximumVelocity[0] = f / 1000F;
        mMaximumVelocity[1] = f1 / 1000F;
        return this;
    }

    public AutoScrollHelper setMinimumVelocity(float f, float f1)
    {
        mMinimumVelocity[0] = f / 1000F;
        mMinimumVelocity[1] = f1 / 1000F;
        return this;
    }

    public AutoScrollHelper setRampDownDuration(int i)
    {
        mScroller.setRampDownDuration(i);
        return this;
    }

    public AutoScrollHelper setRampUpDuration(int i)
    {
        mScroller.setRampUpDuration(i);
        return this;
    }

    public AutoScrollHelper setRelativeEdges(float f, float f1)
    {
        mRelativeEdges[0] = f;
        mRelativeEdges[1] = f1;
        return this;
    }

    public AutoScrollHelper setRelativeVelocity(float f, float f1)
    {
        mRelativeVelocity[0] = f / 1000F;
        mRelativeVelocity[1] = f1 / 1000F;
        return this;
    }

    private static final int DEFAULT_ACTIVATION_DELAY = ViewConfiguration.getTapTimeout();
    private static final int DEFAULT_EDGE_TYPE = 1;
    private static final float DEFAULT_MAXIMUM_EDGE = 3.402823E+038F;
    private static final int DEFAULT_MAXIMUM_VELOCITY_DIPS = 1575;
    private static final int DEFAULT_MINIMUM_VELOCITY_DIPS = 315;
    private static final int DEFAULT_RAMP_DOWN_DURATION = 500;
    private static final int DEFAULT_RAMP_UP_DURATION = 500;
    private static final float DEFAULT_RELATIVE_EDGE = 0.2F;
    private static final float DEFAULT_RELATIVE_VELOCITY = 1F;
    public static final int EDGE_TYPE_INSIDE = 0;
    public static final int EDGE_TYPE_INSIDE_EXTEND = 1;
    public static final int EDGE_TYPE_OUTSIDE = 2;
    private static final int HORIZONTAL = 0;
    public static final float NO_MAX = 3.402823E+038F;
    public static final float NO_MIN = 0F;
    public static final float RELATIVE_UNSPECIFIED = 0F;
    private static final int VERTICAL = 1;
    private int mActivationDelay;
    private boolean mAlreadyDelayed;
    private boolean mAnimating;
    private final Interpolator mEdgeInterpolator = new AccelerateInterpolator();
    private int mEdgeType;
    private boolean mEnabled;
    private boolean mExclusive;
    private float mMaximumEdges[] = {
        3.402823E+038F, 3.402823E+038F
    };
    private float mMaximumVelocity[] = {
        3.402823E+038F, 3.402823E+038F
    };
    private float mMinimumVelocity[] = {
        0.0F, 0.0F
    };
    private boolean mNeedsCancel;
    private boolean mNeedsReset;
    private float mRelativeEdges[] = {
        0.0F, 0.0F
    };
    private float mRelativeVelocity[] = {
        0.0F, 0.0F
    };
    private Runnable mRunnable;
    private final ClampedScroller mScroller = new ClampedScroller();
    private final View mTarget;

}

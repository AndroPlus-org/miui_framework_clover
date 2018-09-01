// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;

import android.view.animation.AnimationUtils;

// Referenced classes of package android.animation:
//            ValueAnimator

public class TimeAnimator extends ValueAnimator
{
    public static interface TimeListener
    {

        public abstract void onTimeUpdate(TimeAnimator timeanimator, long l, long l1);
    }


    public TimeAnimator()
    {
        mPreviousTime = -1L;
    }

    boolean animateBasedOnTime(long l)
    {
        if(mListener != null)
        {
            long l1 = mStartTime;
            long l2;
            if(mPreviousTime < 0L)
                l2 = 0L;
            else
                l2 = l - mPreviousTime;
            mPreviousTime = l;
            mListener.onTimeUpdate(this, l - l1, l2);
        }
        return false;
    }

    void animateValue(float f)
    {
    }

    void initAnimation()
    {
    }

    public void setCurrentPlayTime(long l)
    {
        long l1 = AnimationUtils.currentAnimationTimeMillis();
        mStartTime = Math.max(mStartTime, l1 - l);
        mStartTimeCommitted = true;
        animateBasedOnTime(l1);
    }

    public void setTimeListener(TimeListener timelistener)
    {
        mListener = timelistener;
    }

    public void start()
    {
        mPreviousTime = -1L;
        super.start();
    }

    private TimeListener mListener;
    private long mPreviousTime;
}

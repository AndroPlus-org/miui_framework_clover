// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.animation.*;
import android.app.Activity;
import android.content.*;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

public class SwipeDismissLayout extends FrameLayout
{
    private class DismissAnimator
        implements android.animation.ValueAnimator.AnimatorUpdateListener, android.animation.Animator.AnimatorListener
    {

        private void animate(float f, float f1, long l, TimeInterpolator timeinterpolator, boolean flag)
        {
            mDismissAnimator.cancel();
            mDismissOnComplete = flag;
            mDismissAnimator.setFloatValues(new float[] {
                f, f1
            });
            mDismissAnimator.setDuration(l);
            mDismissAnimator.setInterpolator(timeinterpolator);
            mDismissAnimator.start();
        }

        void animateDismissal(float f)
        {
            animate(f / (float)getWidth(), 1.0F, 250L, DISMISS_INTERPOLATOR, true);
        }

        void animateRecovery(float f)
        {
            animate(f / (float)getWidth(), 0.0F, 250L, DISMISS_INTERPOLATOR, false);
        }

        boolean isAnimating()
        {
            return mDismissAnimator.isStarted();
        }

        public void onAnimationCancel(Animator animator)
        {
            mWasCanceled = true;
        }

        public void onAnimationEnd(Animator animator)
        {
            if(!mWasCanceled)
                if(mDismissOnComplete)
                    SwipeDismissLayout._2D_wrap0(SwipeDismissLayout.this);
                else
                    cancel();
        }

        public void onAnimationRepeat(Animator animator)
        {
        }

        public void onAnimationStart(Animator animator)
        {
            mWasCanceled = false;
        }

        public void onAnimationUpdate(ValueAnimator valueanimator)
        {
            float f = ((Float)valueanimator.getAnimatedValue()).floatValue();
            SwipeDismissLayout._2D_wrap2(SwipeDismissLayout.this, (float)getWidth() * f);
        }

        private final long DISMISS_DURATION = 250L;
        private final TimeInterpolator DISMISS_INTERPOLATOR = new DecelerateInterpolator(1.5F);
        private final ValueAnimator mDismissAnimator = new ValueAnimator();
        private boolean mDismissOnComplete;
        private boolean mWasCanceled;
        final SwipeDismissLayout this$0;

        DismissAnimator()
        {
            this$0 = SwipeDismissLayout.this;
            super();
            mWasCanceled = false;
            mDismissOnComplete = false;
            mDismissAnimator.addUpdateListener(this);
            mDismissAnimator.addListener(this);
        }
    }

    public static interface OnDismissedListener
    {

        public abstract void onDismissed(SwipeDismissLayout swipedismisslayout);
    }

    public static interface OnSwipeProgressChangedListener
    {

        public abstract void onSwipeCancelled(SwipeDismissLayout swipedismisslayout);

        public abstract void onSwipeProgressChanged(SwipeDismissLayout swipedismisslayout, float f, float f1);
    }


    static boolean _2D_get0(SwipeDismissLayout swipedismisslayout)
    {
        return swipedismisslayout.mDismissed;
    }

    static void _2D_wrap0(SwipeDismissLayout swipedismisslayout)
    {
        swipedismisslayout.dismiss();
    }

    static void _2D_wrap1(SwipeDismissLayout swipedismisslayout)
    {
        swipedismisslayout.resetMembers();
    }

    static void _2D_wrap2(SwipeDismissLayout swipedismisslayout, float f)
    {
        swipedismisslayout.setProgress(f);
    }

    public SwipeDismissLayout(Context context)
    {
        super(context);
        mBlockGesture = false;
        mActivityTranslucencyConverted = false;
        mDismissAnimator = new DismissAnimator();
        mScreenOffFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
        mDismissable = true;
        init(context);
    }

    public SwipeDismissLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mBlockGesture = false;
        mActivityTranslucencyConverted = false;
        mDismissAnimator = new DismissAnimator();
        mScreenOffFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
        mDismissable = true;
        init(context);
    }

    public SwipeDismissLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mBlockGesture = false;
        mActivityTranslucencyConverted = false;
        mDismissAnimator = new DismissAnimator();
        mScreenOffFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
        mDismissable = true;
        init(context);
    }

    private void checkGesture(MotionEvent motionevent)
    {
        if(motionevent.getActionMasked() == 0)
            mBlockGesture = mDismissAnimator.isAnimating();
    }

    private void dismiss()
    {
        if(mDismissedListener != null)
            mDismissedListener.onDismissed(this);
    }

    private Activity findActivity()
    {
        for(Context context = getContext(); context instanceof ContextWrapper; context = ((ContextWrapper)context).getBaseContext())
            if(context instanceof Activity)
                return (Activity)context;

        return null;
    }

    private void init(Context context)
    {
        ViewConfiguration viewconfiguration = ViewConfiguration.get(context);
        mSlop = viewconfiguration.getScaledTouchSlop();
        mMinFlingVelocity = viewconfiguration.getScaledMinimumFlingVelocity();
        context = context.getTheme().obtainStyledAttributes(com.android.internal.R.styleable.Theme);
        mIsWindowNativelyTranslucent = context.getBoolean(5, false);
        context.recycle();
    }

    private float progressToAlpha(float f)
    {
        return 1.0F - f * f * f;
    }

    private void resetMembers()
    {
        if(mVelocityTracker != null)
            mVelocityTracker.recycle();
        mVelocityTracker = null;
        mDownX = 0.0F;
        mLastX = -2.147484E+009F;
        mDownY = 0.0F;
        mSwiping = false;
        mDismissed = false;
        mDiscardIntercept = false;
    }

    private void setProgress(float f)
    {
        if(mProgressListener != null && f >= 0.0F)
            mProgressListener.onSwipeProgressChanged(this, progressToAlpha(f / (float)getWidth()), f);
    }

    private void updateDismiss(MotionEvent motionevent)
    {
        float f;
        float f1;
        f = motionevent.getRawX() - mDownX;
        mVelocityTracker.computeCurrentVelocity(1000);
        f1 = mVelocityTracker.getXVelocity();
        if(mLastX == -2.147484E+009F)
            f1 = f / (float)((motionevent.getEventTime() - motionevent.getDownTime()) / 1000L);
        break MISSING_BLOCK_LABEL_55;
        if(!mDismissed && (f > (float)getWidth() * Math.max(Math.min((-0.23F * f1) / (float)mMinFlingVelocity + 0.33F, 0.33F), 0.1F) && motionevent.getRawX() >= mLastX || f1 >= (float)mMinFlingVelocity))
            mDismissed = true;
        if(mDismissed && mSwiping && f1 < (float)(-mMinFlingVelocity))
            mDismissed = false;
        return;
    }

    private void updateSwiping(MotionEvent motionevent)
    {
        boolean flag = false;
        boolean flag1 = mSwiping;
        if(!mSwiping)
        {
            float f = motionevent.getRawX() - mDownX;
            float f1 = motionevent.getRawY() - mDownY;
            if(f * f + f1 * f1 > (float)(mSlop * mSlop))
            {
                boolean flag2 = flag;
                if(f > (float)(mSlop * 2))
                {
                    flag2 = flag;
                    if(Math.abs(f1) < Math.abs(f))
                        flag2 = true;
                }
                mSwiping = flag2;
            } else
            {
                mSwiping = false;
            }
        }
        if(mSwiping && flag1 ^ true && !mIsWindowNativelyTranslucent)
        {
            motionevent = findActivity();
            if(motionevent != null)
                mActivityTranslucencyConverted = motionevent.convertToTranslucent(null, null);
        }
    }

    protected boolean canScroll(View view, boolean flag, float f, float f1, float f2)
    {
        if(view instanceof ViewGroup)
        {
            ViewGroup viewgroup = (ViewGroup)view;
            int i = view.getScrollX();
            int j = view.getScrollY();
            for(int k = viewgroup.getChildCount() - 1; k >= 0; k--)
            {
                View view1 = viewgroup.getChildAt(k);
                if((float)i + f1 >= (float)view1.getLeft() && (float)i + f1 < (float)view1.getRight() && (float)j + f2 >= (float)view1.getTop() && (float)j + f2 < (float)view1.getBottom() && canScroll(view1, true, f, ((float)i + f1) - (float)view1.getLeft(), ((float)j + f2) - (float)view1.getTop()))
                    return true;
            }

        }
        if(flag)
            flag = view.canScrollHorizontally((int)(-f));
        else
            flag = false;
        return flag;
    }

    protected void cancel()
    {
        if(!mIsWindowNativelyTranslucent)
        {
            Activity activity = findActivity();
            if(activity != null && mActivityTranslucencyConverted)
            {
                activity.convertFromTranslucent();
                mActivityTranslucencyConverted = false;
            }
        }
        if(mProgressListener != null)
            mProgressListener.onSwipeCancelled(this);
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        BroadcastReceiver broadcastreceiver = JVM INSTR new #6   <Class SwipeDismissLayout$1>;
        broadcastreceiver.this. _cls1();
        mScreenOffReceiver = broadcastreceiver;
        getContext().registerReceiver(mScreenOffReceiver, mScreenOffFilter);
_L1:
        return;
        ReceiverCallNotAllowedException receivercallnotallowedexception;
        receivercallnotallowedexception;
        mScreenOffReceiver = null;
          goto _L1
    }

    protected void onDetachedFromWindow()
    {
        if(mScreenOffReceiver != null)
        {
            getContext().unregisterReceiver(mScreenOffReceiver);
            mScreenOffReceiver = null;
        }
        super.onDetachedFromWindow();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        flag = false;
        checkGesture(motionevent);
        if(mBlockGesture)
            return true;
        if(!mDismissable)
            return super.onInterceptTouchEvent(motionevent);
        motionevent.offsetLocation(motionevent.getRawX() - motionevent.getX(), 0.0F);
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 6: default 88
    //                   0 102
    //                   1 207
    //                   2 214
    //                   3 207
    //                   4 88
    //                   5 152
    //                   6 167;
           goto _L1 _L2 _L3 _L4 _L3 _L1 _L5 _L6
_L1:
        if(!mDiscardIntercept)
            flag = mSwiping;
        return flag;
_L2:
        resetMembers();
        mDownX = motionevent.getRawX();
        mDownY = motionevent.getRawY();
        mActiveTouchId = motionevent.getPointerId(0);
        mVelocityTracker = VelocityTracker.obtain("int1");
        mVelocityTracker.addMovement(motionevent);
        continue; /* Loop/switch isn't completed */
_L5:
        mActiveTouchId = motionevent.getPointerId(motionevent.getActionIndex());
        continue; /* Loop/switch isn't completed */
_L6:
        int i = motionevent.getActionIndex();
        if(motionevent.getPointerId(i) == mActiveTouchId)
        {
            if(i == 0)
                i = 1;
            else
                i = 0;
            mActiveTouchId = motionevent.getPointerId(i);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        resetMembers();
        continue; /* Loop/switch isn't completed */
_L4:
        if(mVelocityTracker != null && !mDiscardIntercept)
        {
            int j = motionevent.findPointerIndex(mActiveTouchId);
            if(j == -1)
            {
                Log.e("SwipeDismissLayout", "Invalid pointer index: ignoring.");
                mDiscardIntercept = true;
            } else
            {
                float f = motionevent.getRawX() - mDownX;
                float f1 = motionevent.getX(j);
                float f2 = motionevent.getY(j);
                if(f != 0.0F && canScroll(this, false, f, f1, f2))
                    mDiscardIntercept = true;
                else
                    updateSwiping(motionevent);
            }
        }
        if(true) goto _L1; else goto _L7
_L7:
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        checkGesture(motionevent);
        if(mBlockGesture)
            return true;
        if(mVelocityTracker == null || mDismissable ^ true)
            return super.onTouchEvent(motionevent);
        motionevent.offsetLocation(motionevent.getRawX() - motionevent.getX(), 0.0F);
        motionevent.getActionMasked();
        JVM INSTR tableswitch 1 3: default 80
    //                   1 82
    //                   2 164
    //                   3 153;
           goto _L1 _L2 _L3 _L4
_L1:
        return true;
_L2:
        updateDismiss(motionevent);
        if(mDismissed)
            mDismissAnimator.animateDismissal(motionevent.getRawX() - mDownX);
        else
        if(mSwiping && mLastX != -2.147484E+009F)
            mDismissAnimator.animateRecovery(motionevent.getRawX() - mDownX);
        resetMembers();
        continue; /* Loop/switch isn't completed */
_L4:
        cancel();
        resetMembers();
        continue; /* Loop/switch isn't completed */
_L3:
        mVelocityTracker.addMovement(motionevent);
        mLastX = motionevent.getRawX();
        updateSwiping(motionevent);
        if(mSwiping)
            setProgress(motionevent.getRawX() - mDownX);
        if(true) goto _L1; else goto _L5
_L5:
    }

    public void setDismissable(boolean flag)
    {
        if(!flag && mDismissable)
        {
            cancel();
            resetMembers();
        }
        mDismissable = flag;
    }

    public void setOnDismissedListener(OnDismissedListener ondismissedlistener)
    {
        mDismissedListener = ondismissedlistener;
    }

    public void setOnSwipeProgressChangedListener(OnSwipeProgressChangedListener onswipeprogresschangedlistener)
    {
        mProgressListener = onswipeprogresschangedlistener;
    }

    private static final float MAX_DIST_THRESHOLD = 0.33F;
    private static final float MIN_DIST_THRESHOLD = 0.1F;
    private static final String TAG = "SwipeDismissLayout";
    private int mActiveTouchId;
    private boolean mActivityTranslucencyConverted;
    private boolean mBlockGesture;
    private boolean mDiscardIntercept;
    private final DismissAnimator mDismissAnimator;
    private boolean mDismissable;
    private boolean mDismissed;
    private OnDismissedListener mDismissedListener;
    private float mDownX;
    private float mDownY;
    private boolean mIsWindowNativelyTranslucent;
    private float mLastX;
    private int mMinFlingVelocity;
    private OnSwipeProgressChangedListener mProgressListener;
    private IntentFilter mScreenOffFilter;
    private BroadcastReceiver mScreenOffReceiver;
    private int mSlop;
    private boolean mSwiping;
    private VelocityTracker mVelocityTracker;

    // Unreferenced inner class com/android/internal/widget/SwipeDismissLayout$1

/* anonymous class */
    class _cls1 extends BroadcastReceiver
    {

        void lambda$_2D_com_android_internal_widget_SwipeDismissLayout$1_4841()
        {
            if(SwipeDismissLayout._2D_get0(SwipeDismissLayout.this))
                SwipeDismissLayout._2D_wrap0(SwipeDismissLayout.this);
            else
                cancel();
            SwipeDismissLayout._2D_wrap1(SwipeDismissLayout.this);
        }

        public void onReceive(Context context, Intent intent)
        {
            post(new _.Lambda.hZenqyGYSNt5KiruOSsv736MIDs((byte)2, this));
        }

        final SwipeDismissLayout this$0;

            
            {
                this$0 = SwipeDismissLayout.this;
                super();
            }
    }

}

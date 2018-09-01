// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.os.Vibrator;
import android.util.*;
import android.view.*;
import android.view.animation.*;
import android.widget.ImageView;
import android.widget.TextView;

public class SlidingTab extends ViewGroup
{
    public static interface OnTriggerListener
    {

        public abstract void onGrabbedStateChange(View view, int i);

        public abstract void onTrigger(View view, int i);

        public static final int LEFT_HANDLE = 1;
        public static final int NO_HANDLE = 0;
        public static final int RIGHT_HANDLE = 2;
    }

    private static class Slider
    {

        static ImageView _2D_get0(Slider slider)
        {
            return slider.tab;
        }

        static TextView _2D_get1(Slider slider)
        {
            return slider.text;
        }

        public int getTabHeight()
        {
            return tab.getMeasuredHeight();
        }

        public int getTabWidth()
        {
            return tab.getMeasuredWidth();
        }

        void hide()
        {
            int i;
            int j;
            TranslateAnimation translateanimation;
            if(alignment == 0 || alignment == 1)
                i = 1;
            else
                i = 0;
            if(i != 0)
            {
                if(alignment == 0)
                    j = alignment_value - tab.getRight();
                else
                    j = alignment_value - tab.getLeft();
            } else
            {
                j = 0;
            }
            if(i != 0)
                i = 0;
            else
            if(alignment == 2)
                i = alignment_value - tab.getBottom();
            else
                i = alignment_value - tab.getTop();
            translateanimation = new TranslateAnimation(0.0F, j, 0.0F, i);
            translateanimation.setDuration(250L);
            translateanimation.setFillAfter(true);
            tab.startAnimation(translateanimation);
            text.startAnimation(translateanimation);
            target.setVisibility(4);
        }

        public void hideTarget()
        {
            target.clearAnimation();
            target.setVisibility(4);
        }

        void layout(int i, int j, int k, int l, int i1)
        {
            alignment = i1;
            Drawable drawable = tab.getBackground();
            int j1 = drawable.getIntrinsicWidth();
            int k1 = drawable.getIntrinsicHeight();
            drawable = target.getDrawable();
            int l1 = drawable.getIntrinsicWidth();
            int i2 = drawable.getIntrinsicHeight();
            int j2 = k - i;
            int k2 = l - j;
            int l2 = ((int)((float)j2 * 0.6666667F) - l1) + j1 / 2;
            int i3 = (int)((float)j2 * 0.3333333F) - j1 / 2;
            int j3 = (j2 - j1) / 2;
            int k3 = j3 + j1;
            if(i1 == 0 || i1 == 1)
            {
                j = (k2 - i2) / 2;
                l = j + i2;
                i2 = (k2 - k1) / 2;
                k2 = (k2 + k1) / 2;
                if(i1 == 0)
                {
                    tab.layout(0, i2, j1, k2);
                    text.layout(0 - j2, i2, 0, k2);
                    text.setGravity(5);
                    target.layout(l2, j, l2 + l1, l);
                    alignment_value = i;
                } else
                {
                    tab.layout(j2 - j1, i2, j2, k2);
                    text.layout(j2, i2, j2 + j2, k2);
                    target.layout(i3, j, i3 + l1, l);
                    text.setGravity(48);
                    alignment_value = k;
                }
            } else
            {
                i = (j2 - l1) / 2;
                l1 = (j2 + l1) / 2;
                k = ((int)((float)k2 * 0.6666667F) + k1 / 2) - i2;
                j2 = (int)((float)k2 * 0.3333333F) - k1 / 2;
                if(i1 == 2)
                {
                    tab.layout(j3, 0, k3, k1);
                    text.layout(j3, 0 - k2, k3, 0);
                    target.layout(i, k, l1, k + i2);
                    alignment_value = j;
                } else
                {
                    tab.layout(j3, k2 - k1, k3, k2);
                    text.layout(j3, k2, k3, k2 + k2);
                    target.layout(i, j2, l1, j2 + i2);
                    alignment_value = l;
                }
            }
        }

        public void measure(int i, int j)
        {
            i = android.view.View.MeasureSpec.getSize(i);
            j = android.view.View.MeasureSpec.getSize(j);
            tab.measure(android.view.View.MeasureSpec.makeSafeMeasureSpec(i, 0), android.view.View.MeasureSpec.makeSafeMeasureSpec(j, 0));
            text.measure(android.view.View.MeasureSpec.makeSafeMeasureSpec(i, 0), android.view.View.MeasureSpec.makeSafeMeasureSpec(j, 0));
        }

        void reset(boolean flag)
        {
            setState(0);
            text.setVisibility(0);
            text.setTextAppearance(text.getContext(), 0x10303a0);
            tab.setVisibility(0);
            target.setVisibility(4);
            boolean flag1;
            int i;
            int j;
            if(alignment == 0 || alignment == 1)
                flag1 = true;
            else
                flag1 = false;
            if(flag1)
            {
                if(alignment == 0)
                    i = alignment_value - tab.getLeft();
                else
                    i = alignment_value - tab.getRight();
            } else
            {
                i = 0;
            }
            if(flag1)
                j = 0;
            else
            if(alignment == 2)
                j = alignment_value - tab.getTop();
            else
                j = alignment_value - tab.getBottom();
            if(flag)
            {
                TranslateAnimation translateanimation = new TranslateAnimation(0.0F, i, 0.0F, j);
                translateanimation.setDuration(250L);
                translateanimation.setFillAfter(false);
                text.startAnimation(translateanimation);
                tab.startAnimation(translateanimation);
            } else
            {
                if(flag1)
                {
                    text.offsetLeftAndRight(i);
                    tab.offsetLeftAndRight(i);
                } else
                {
                    text.offsetTopAndBottom(j);
                    tab.offsetTopAndBottom(j);
                }
                text.clearAnimation();
                tab.clearAnimation();
                target.clearAnimation();
            }
        }

        void setBarBackgroundResource(int i)
        {
            text.setBackgroundResource(i);
        }

        void setHintText(int i)
        {
            text.setText(i);
        }

        void setIcon(int i)
        {
            tab.setImageResource(i);
        }

        void setState(int i)
        {
            Object obj = text;
            boolean flag;
            if(i == 1)
                flag = true;
            else
                flag = false;
            ((TextView) (obj)).setPressed(flag);
            obj = tab;
            if(i == 1)
                flag = true;
            else
                flag = false;
            ((ImageView) (obj)).setPressed(flag);
            if(i == 2)
            {
                int ai[] = new int[1];
                ai[0] = 0x10100a2;
                if(text.getBackground().isStateful())
                    text.getBackground().setState(ai);
                if(tab.getBackground().isStateful())
                    tab.getBackground().setState(ai);
                text.setTextAppearance(text.getContext(), 0x103039f);
            } else
            {
                text.setTextAppearance(text.getContext(), 0x10303a0);
            }
            currentState = i;
        }

        void setTabBackgroundResource(int i)
        {
            tab.setBackgroundResource(i);
        }

        void setTarget(int i)
        {
            target.setImageResource(i);
        }

        void show(boolean flag)
        {
            text.setVisibility(0);
            tab.setVisibility(0);
            if(flag)
            {
                int i;
                int j;
                TranslateAnimation translateanimation;
                if(alignment == 0 || alignment == 1)
                    i = 1;
                else
                    i = 0;
                if(i != 0)
                {
                    if(alignment == 0)
                        j = tab.getWidth();
                    else
                        j = -tab.getWidth();
                } else
                {
                    j = 0;
                }
                if(i != 0)
                    i = 0;
                else
                if(alignment == 2)
                    i = tab.getHeight();
                else
                    i = -tab.getHeight();
                translateanimation = new TranslateAnimation(-j, 0.0F, -i, 0.0F);
                translateanimation.setDuration(250L);
                tab.startAnimation(translateanimation);
                text.startAnimation(translateanimation);
            }
        }

        void showTarget()
        {
            AlphaAnimation alphaanimation = new AlphaAnimation(0.0F, 1.0F);
            alphaanimation.setDuration(500L);
            target.startAnimation(alphaanimation);
            target.setVisibility(0);
        }

        public void startAnimation(Animation animation, Animation animation1)
        {
            tab.startAnimation(animation);
            text.startAnimation(animation1);
        }

        public void updateDrawableStates()
        {
            setState(currentState);
        }

        public static final int ALIGN_BOTTOM = 3;
        public static final int ALIGN_LEFT = 0;
        public static final int ALIGN_RIGHT = 1;
        public static final int ALIGN_TOP = 2;
        public static final int ALIGN_UNKNOWN = 4;
        private static final int STATE_ACTIVE = 2;
        private static final int STATE_NORMAL = 0;
        private static final int STATE_PRESSED = 1;
        private int alignment;
        private int alignment_value;
        private int currentState;
        private final ImageView tab;
        private final ImageView target;
        private final TextView text;

        Slider(ViewGroup viewgroup, int i, int j, int k)
        {
            currentState = 0;
            alignment = 4;
            tab = new ImageView(viewgroup.getContext());
            tab.setBackgroundResource(i);
            tab.setScaleType(android.widget.ImageView.ScaleType.CENTER);
            tab.setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, -2));
            text = new TextView(viewgroup.getContext());
            text.setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, -1));
            text.setBackgroundResource(j);
            text.setTextAppearance(viewgroup.getContext(), 0x10303a0);
            target = new ImageView(viewgroup.getContext());
            target.setImageResource(k);
            target.setScaleType(android.widget.ImageView.ScaleType.CENTER);
            target.setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, -2));
            target.setVisibility(4);
            viewgroup.addView(target);
            viewgroup.addView(tab);
            viewgroup.addView(text);
        }
    }


    static android.view.animation.Animation.AnimationListener _2D_get0(SlidingTab slidingtab)
    {
        return slidingtab.mAnimationDoneListener;
    }

    static Slider _2D_get1(SlidingTab slidingtab)
    {
        return slidingtab.mLeftSlider;
    }

    static Slider _2D_get2(SlidingTab slidingtab)
    {
        return slidingtab.mRightSlider;
    }

    static boolean _2D_set0(SlidingTab slidingtab, boolean flag)
    {
        slidingtab.mAnimating = flag;
        return flag;
    }

    static void _2D_wrap0(SlidingTab slidingtab)
    {
        slidingtab.onAnimationDone();
    }

    static void _2D_wrap1(SlidingTab slidingtab)
    {
        slidingtab.resetView();
    }

    public SlidingTab(Context context)
    {
        this(context, null);
    }

    public SlidingTab(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mHoldLeftOnTransition = true;
        mHoldRightOnTransition = true;
        mGrabbedState = 0;
        mTriggered = false;
        mAnimationDoneListener = new android.view.animation.Animation.AnimationListener() {

            public void onAnimationEnd(Animation animation)
            {
                SlidingTab._2D_wrap0(SlidingTab.this);
            }

            public void onAnimationRepeat(Animation animation)
            {
            }

            public void onAnimationStart(Animation animation)
            {
            }

            final SlidingTab this$0;

            
            {
                this$0 = SlidingTab.this;
                super();
            }
        }
;
        mTmpRect = new Rect();
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.SlidingTab);
        mOrientation = context.getInt(0, 0);
        context.recycle();
        mDensity = getResources().getDisplayMetrics().density;
        mLeftSlider = new Slider(this, 0x1080536, 0x1080525, 0x1080544);
        mRightSlider = new Slider(this, 0x108053f, 0x108052e, 0x1080544);
    }

    private void cancelGrab()
    {
        mTracking = false;
        mTriggered = false;
        mOtherSlider.show(true);
        mCurrentSlider.reset(false);
        mCurrentSlider.hideTarget();
        mCurrentSlider = null;
        mOtherSlider = null;
        setGrabbedState(0);
    }

    private void dispatchTriggerEvent(int i)
    {
        vibrate(40L);
        if(mOnTriggerListener != null)
            mOnTriggerListener.onTrigger(this, i);
    }

    private boolean isHorizontal()
    {
        boolean flag = false;
        if(mOrientation == 0)
            flag = true;
        return flag;
    }

    private void log(String s)
    {
        Log.d("SlidingTab", s);
    }

    private void moveHandle(float f, float f1)
    {
        ImageView imageview = Slider._2D_get0(mCurrentSlider);
        TextView textview = Slider._2D_get1(mCurrentSlider);
        if(isHorizontal())
        {
            int i = (int)f - imageview.getLeft() - imageview.getWidth() / 2;
            imageview.offsetLeftAndRight(i);
            textview.offsetLeftAndRight(i);
        } else
        {
            int j = (int)f1 - imageview.getTop() - imageview.getHeight() / 2;
            imageview.offsetTopAndBottom(j);
            textview.offsetTopAndBottom(j);
        }
        invalidate();
    }

    private void onAnimationDone()
    {
        resetView();
        mAnimating = false;
    }

    private void resetView()
    {
        mLeftSlider.reset(false);
        mRightSlider.reset(false);
    }

    private void setGrabbedState(int i)
    {
        if(i != mGrabbedState)
        {
            mGrabbedState = i;
            if(mOnTriggerListener != null)
                mOnTriggerListener.onGrabbedStateChange(this, mGrabbedState);
        }
    }

    private void vibrate(long l)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        if(android.provider.Settings.System.getIntForUser(mContext.getContentResolver(), "haptic_feedback_enabled", 1, -2) != 0)
            flag = true;
        else
            flag = false;
        if(!flag)
            break MISSING_BLOCK_LABEL_62;
        if(mVibrator == null)
            mVibrator = (Vibrator)getContext().getSystemService("vibrator");
        mVibrator.vibrate(l, VIBRATION_ATTRIBUTES);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private boolean withinView(float f, float f1, View view)
    {
        boolean flag;
        flag = true;
        break MISSING_BLOCK_LABEL_3;
        if((!isHorizontal() || f1 <= -50F || f1 >= (float)(view.getHeight() + 50)) && (isHorizontal() || f <= -50F || f >= (float)(view.getWidth() + 50)))
            flag = false;
        return flag;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        float f;
        float f1;
        int i;
        boolean flag;
        f = 0.6666667F;
        f1 = 0.3333333F;
        i = motionevent.getAction();
        float f2 = motionevent.getX();
        float f3 = motionevent.getY();
        if(mAnimating)
            return false;
        Slider._2D_get0(mLeftSlider).getHitRect(mTmpRect);
        flag = mTmpRect.contains((int)f2, (int)f3);
        Slider._2D_get0(mRightSlider).getHitRect(mTmpRect);
        boolean flag1 = mTmpRect.contains((int)f2, (int)f3);
        if(!mTracking)
        {
            if(flag)
                flag1 = true;
            if(flag1 ^ true)
                return false;
        }
        i;
        JVM INSTR tableswitch 0 0: default 140
    //                   0 142;
           goto _L1 _L2
_L1:
        return true;
_L2:
        mTracking = true;
        mTriggered = false;
        vibrate(30L);
        if(!flag)
            break; /* Loop/switch isn't completed */
        mCurrentSlider = mLeftSlider;
        mOtherSlider = mRightSlider;
        if(isHorizontal())
            f1 = f;
        else
            f1 = 0.3333333F;
        mThreshold = f1;
        setGrabbedState(1);
_L4:
        mCurrentSlider.setState(1);
        mCurrentSlider.showTarget();
        mOtherSlider.hide();
        if(true) goto _L1; else goto _L3
_L3:
        mCurrentSlider = mRightSlider;
        mOtherSlider = mLeftSlider;
        if(!isHorizontal())
            f1 = 0.6666667F;
        mThreshold = f1;
        setGrabbedState(2);
          goto _L4
        if(true) goto _L1; else goto _L5
_L5:
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        if(!flag)
            return;
        Slider slider = mLeftSlider;
        int i1;
        if(isHorizontal())
            i1 = 0;
        else
            i1 = 3;
        slider.layout(i, j, k, l, i1);
        slider = mRightSlider;
        if(isHorizontal())
            i1 = 1;
        else
            i1 = 2;
        slider.layout(i, j, k, l, i1);
    }

    protected void onMeasure(int i, int j)
    {
        android.view.View.MeasureSpec.getMode(i);
        int k = android.view.View.MeasureSpec.getSize(i);
        android.view.View.MeasureSpec.getMode(j);
        int l = android.view.View.MeasureSpec.getSize(j);
        mLeftSlider.measure(i, j);
        mRightSlider.measure(i, j);
        int i1 = mLeftSlider.getTabWidth();
        i = mRightSlider.getTabWidth();
        j = mLeftSlider.getTabHeight();
        int j1 = mRightSlider.getTabHeight();
        if(isHorizontal())
        {
            i = Math.max(k, i1 + i);
            j = Math.max(j, j1);
        } else
        {
            i = Math.max(i1, j1);
            j = Math.max(l, j + j1);
        }
        setMeasuredDimension(i, j);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        boolean flag = true;
        if(!mTracking) goto _L2; else goto _L1
_L1:
        int i;
        float f;
        float f1;
        i = motionevent.getAction();
        f = motionevent.getX();
        f1 = motionevent.getY();
        i;
        JVM INSTR tableswitch 1 3: default 52
    //                   1 322
    //                   2 72
    //                   3 322;
           goto _L2 _L3 _L4 _L3
_L2:
        boolean flag1 = flag;
        if(!mTracking)
            flag1 = super.onTouchEvent(motionevent);
        return flag1;
_L4:
        if(withinView(f, f1, this))
        {
            moveHandle(f, f1);
            int j;
            if(!isHorizontal())
                f = f1;
            f1 = mThreshold;
            if(isHorizontal())
                j = getWidth();
            else
                j = getHeight();
            f1 *= j;
            if(isHorizontal())
            {
                if(mCurrentSlider != mLeftSlider ? f < f1 : f > f1)
                    j = 1;
                else
                    j = 0;
            } else
            if(mCurrentSlider != mLeftSlider ? f > f1 : f < f1)
                j = 1;
            else
                j = 0;
            if(!mTriggered && j != 0)
            {
                mTriggered = true;
                mTracking = false;
                mCurrentSlider.setState(2);
                boolean flag2;
                int k;
                if(mCurrentSlider == mLeftSlider)
                    j = 1;
                else
                    j = 0;
                if(j != 0)
                    k = 1;
                else
                    k = 2;
                dispatchTriggerEvent(k);
                if(j != 0)
                    flag2 = mHoldLeftOnTransition;
                else
                    flag2 = mHoldRightOnTransition;
                startAnimating(flag2);
                setGrabbedState(0);
            }
            continue; /* Loop/switch isn't completed */
        }
_L3:
        cancelGrab();
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected void onVisibilityChanged(View view, int i)
    {
        super.onVisibilityChanged(view, i);
        if(view == this && i != 0 && mGrabbedState != 0)
            cancelGrab();
    }

    public void reset(boolean flag)
    {
        mLeftSlider.reset(flag);
        mRightSlider.reset(flag);
        if(!flag)
            mAnimating = false;
    }

    public void setHoldAfterTrigger(boolean flag, boolean flag1)
    {
        mHoldLeftOnTransition = flag;
        mHoldRightOnTransition = flag1;
    }

    public void setLeftHintText(int i)
    {
        if(isHorizontal())
            mLeftSlider.setHintText(i);
    }

    public void setLeftTabResources(int i, int j, int k, int l)
    {
        mLeftSlider.setIcon(i);
        mLeftSlider.setTarget(j);
        mLeftSlider.setBarBackgroundResource(k);
        mLeftSlider.setTabBackgroundResource(l);
        mLeftSlider.updateDrawableStates();
    }

    public void setOnTriggerListener(OnTriggerListener ontriggerlistener)
    {
        mOnTriggerListener = ontriggerlistener;
    }

    public void setRightHintText(int i)
    {
        if(isHorizontal())
            mRightSlider.setHintText(i);
    }

    public void setRightTabResources(int i, int j, int k, int l)
    {
        mRightSlider.setIcon(i);
        mRightSlider.setTarget(j);
        mRightSlider.setBarBackgroundResource(k);
        mRightSlider.setTabBackgroundResource(l);
        mRightSlider.updateDrawableStates();
    }

    public void setVisibility(int i)
    {
        if(i != getVisibility() && i == 4)
            reset(false);
        super.setVisibility(i);
    }

    void startAnimating(final boolean holdAfter)
    {
        mAnimating = true;
        Slider slider = mCurrentSlider;
        Slider slider1 = mOtherSlider;
        final int dx;
        final int dy;
        if(isHorizontal())
        {
            dx = Slider._2D_get0(slider).getRight();
            dy = Slider._2D_get0(slider).getWidth();
            int i = Slider._2D_get0(slider).getLeft();
            int k = getWidth();
            if(holdAfter)
                dy = 0;
            TranslateAnimation translateanimation;
            TranslateAnimation translateanimation1;
            if(slider == mRightSlider)
                dx = -((dx + k) - dy);
            else
                dx = ((k - i) + k) - dy;
            dy = 0;
        } else
        {
            int j = Slider._2D_get0(slider).getTop();
            int l = Slider._2D_get0(slider).getBottom();
            dy = Slider._2D_get0(slider).getHeight();
            int i1 = getHeight();
            if(holdAfter)
                dy = 0;
            dx = 0;
            if(slider == mRightSlider)
                dy = (j + i1) - dy;
            else
                dy = -(((i1 - l) + i1) - dy);
        }
        translateanimation1 = new TranslateAnimation(0.0F, dx, 0.0F, dy);
        translateanimation1.setDuration(250L);
        translateanimation1.setInterpolator(new LinearInterpolator());
        translateanimation1.setFillAfter(true);
        translateanimation = new TranslateAnimation(0.0F, dx, 0.0F, dy);
        translateanimation.setDuration(250L);
        translateanimation.setInterpolator(new LinearInterpolator());
        translateanimation.setFillAfter(true);
        translateanimation1.setAnimationListener(new android.view.animation.Animation.AnimationListener() {

            public void onAnimationEnd(Animation animation)
            {
                if(holdAfter)
                {
                    animation = new TranslateAnimation(dx, dx, dy, dy);
                    animation.setDuration(1000L);
                    SlidingTab._2D_set0(SlidingTab.this, false);
                } else
                {
                    animation = new AlphaAnimation(0.5F, 1.0F);
                    animation.setDuration(250L);
                    SlidingTab._2D_wrap1(SlidingTab.this);
                }
                animation.setAnimationListener(SlidingTab._2D_get0(SlidingTab.this));
                SlidingTab._2D_get1(SlidingTab.this).startAnimation(animation, animation);
                SlidingTab._2D_get2(SlidingTab.this).startAnimation(animation, animation);
            }

            public void onAnimationRepeat(Animation animation)
            {
            }

            public void onAnimationStart(Animation animation)
            {
            }

            final SlidingTab this$0;
            final int val$dx;
            final int val$dy;
            final boolean val$holdAfter;

            
            {
                this$0 = SlidingTab.this;
                holdAfter = flag;
                dx = i;
                dy = j;
                super();
            }
        }
);
        slider.hideTarget();
        slider.startAnimation(translateanimation1, translateanimation);
    }

    private static final int ANIM_DURATION = 250;
    private static final int ANIM_TARGET_TIME = 500;
    private static final boolean DBG = false;
    private static final int HORIZONTAL = 0;
    private static final String LOG_TAG = "SlidingTab";
    private static final float THRESHOLD = 0.6666667F;
    private static final int TRACKING_MARGIN = 50;
    private static final int VERTICAL = 1;
    private static final long VIBRATE_LONG = 40L;
    private static final long VIBRATE_SHORT = 30L;
    private static final AudioAttributes VIBRATION_ATTRIBUTES = (new android.media.AudioAttributes.Builder()).setContentType(4).setUsage(13).build();
    private boolean mAnimating;
    private final android.view.animation.Animation.AnimationListener mAnimationDoneListener;
    private Slider mCurrentSlider;
    private final float mDensity;
    private int mGrabbedState;
    private boolean mHoldLeftOnTransition;
    private boolean mHoldRightOnTransition;
    private final Slider mLeftSlider;
    private OnTriggerListener mOnTriggerListener;
    private final int mOrientation;
    private Slider mOtherSlider;
    private final Slider mRightSlider;
    private float mThreshold;
    private final Rect mTmpRect;
    private boolean mTracking;
    private boolean mTriggered;
    private Vibrator mVibrator;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.animation.*;
import android.content.Context;
import android.content.res.*;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.*;
import android.view.animation.DecelerateInterpolator;
import android.widget.ActionMenuPresenter;
import android.widget.ActionMenuView;

public abstract class AbsActionBarView extends ViewGroup
{
    protected class VisibilityAnimListener
        implements android.animation.Animator.AnimatorListener
    {

        public void onAnimationCancel(Animator animator)
        {
            mCanceled = true;
        }

        public void onAnimationEnd(Animator animator)
        {
            if(mCanceled)
                return;
            mVisibilityAnim = null;
            setVisibility(mFinalVisibility);
            if(mSplitView != null && mMenuView != null)
                mMenuView.setVisibility(mFinalVisibility);
        }

        public void onAnimationRepeat(Animator animator)
        {
        }

        public void onAnimationStart(Animator animator)
        {
            setVisibility(0);
            mVisibilityAnim = animator;
            mCanceled = false;
        }

        public VisibilityAnimListener withFinalVisibility(int i)
        {
            mFinalVisibility = i;
            return this;
        }

        private boolean mCanceled;
        int mFinalVisibility;
        final AbsActionBarView this$0;

        protected VisibilityAnimListener()
        {
            this$0 = AbsActionBarView.this;
            super();
            mCanceled = false;
        }
    }


    public AbsActionBarView(Context context)
    {
        this(context, null);
    }

    public AbsActionBarView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public AbsActionBarView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public AbsActionBarView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mVisAnimListener = new VisibilityAnimListener();
        attributeset = new TypedValue();
        if(context.getTheme().resolveAttribute(0x101048d, attributeset, true) && ((TypedValue) (attributeset)).resourceId != 0)
            mPopupContext = new ContextThemeWrapper(context, ((TypedValue) (attributeset)).resourceId);
        else
            mPopupContext = context;
    }

    protected static int next(int i, int j, boolean flag)
    {
        if(flag)
            i -= j;
        else
            i += j;
        return i;
    }

    public void animateToVisibility(int i)
    {
        setupAnimatorToVisibility(i, 200L).start();
    }

    public boolean canShowOverflowMenu()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(isOverflowReserved())
        {
            flag1 = flag;
            if(getVisibility() == 0)
                flag1 = true;
        }
        return flag1;
    }

    public void dismissPopupMenus()
    {
        if(mActionMenuPresenter != null)
            mActionMenuPresenter.dismissPopupMenus();
    }

    public int getAnimatedVisibility()
    {
        if(mVisibilityAnim != null)
            return mVisAnimListener.mFinalVisibility;
        else
            return getVisibility();
    }

    public int getContentHeight()
    {
        return mContentHeight;
    }

    public boolean hideOverflowMenu()
    {
        if(mActionMenuPresenter != null)
            return mActionMenuPresenter.hideOverflowMenu();
        else
            return false;
    }

    public boolean isOverflowMenuShowPending()
    {
        if(mActionMenuPresenter != null)
            return mActionMenuPresenter.isOverflowMenuShowPending();
        else
            return false;
    }

    public boolean isOverflowMenuShowing()
    {
        if(mActionMenuPresenter != null)
            return mActionMenuPresenter.isOverflowMenuShowing();
        else
            return false;
    }

    public boolean isOverflowReserved()
    {
        boolean flag;
        if(mActionMenuPresenter != null)
            flag = mActionMenuPresenter.isOverflowReserved();
        else
            flag = false;
        return flag;
    }

    protected int measureChildView(View view, int i, int j, int k)
    {
        view.measure(android.view.View.MeasureSpec.makeMeasureSpec(i, 0x80000000), j);
        return Math.max(0, i - view.getMeasuredWidth() - k);
    }

    protected void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        TypedArray typedarray = getContext().obtainStyledAttributes(null, com.android.internal.R.styleable.ActionBar, 0x10102ce, 0);
        setContentHeight(typedarray.getLayoutDimension(4, 0));
        typedarray.recycle();
        if(mSplitWhenNarrow)
            setSplitToolbar(getContext().getResources().getBoolean(0x11200f0));
        if(mActionMenuPresenter != null)
            mActionMenuPresenter.onConfigurationChanged(configuration);
    }

    public boolean onHoverEvent(MotionEvent motionevent)
    {
        int i = motionevent.getActionMasked();
        if(i == 9)
            mEatingHover = false;
        if(!mEatingHover)
        {
            boolean flag = super.onHoverEvent(motionevent);
            if(i == 9 && flag ^ true)
                mEatingHover = true;
        }
        if(i == 10 || i == 3)
            mEatingHover = false;
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i = motionevent.getActionMasked();
        if(i == 0)
            mEatingTouch = false;
        if(!mEatingTouch)
        {
            boolean flag = super.onTouchEvent(motionevent);
            if(i == 0 && flag ^ true)
                mEatingTouch = true;
        }
        if(i == 1 || i == 3)
            mEatingTouch = false;
        return true;
    }

    protected int positionChild(View view, int i, int j, int k, boolean flag)
    {
        int l = view.getMeasuredWidth();
        int i1 = view.getMeasuredHeight();
        j += (k - i1) / 2;
        if(flag)
            view.layout(i - l, j, i, j + i1);
        else
            view.layout(i, j, i + l, j + i1);
        i = l;
        if(flag)
            i = -l;
        return i;
    }

    public void postShowOverflowMenu()
    {
        post(new Runnable() {

            public void run()
            {
                showOverflowMenu();
            }

            final AbsActionBarView this$0;

            
            {
                this$0 = AbsActionBarView.this;
                super();
            }
        }
);
    }

    public void setContentHeight(int i)
    {
        mContentHeight = i;
        requestLayout();
    }

    public void setSplitToolbar(boolean flag)
    {
        mSplitActionBar = flag;
    }

    public void setSplitView(ViewGroup viewgroup)
    {
        mSplitView = viewgroup;
    }

    public void setSplitWhenNarrow(boolean flag)
    {
        mSplitWhenNarrow = flag;
    }

    public void setVisibility(int i)
    {
        if(i != getVisibility())
        {
            if(mVisibilityAnim != null)
                mVisibilityAnim.end();
            super.setVisibility(i);
        }
    }

    public Animator setupAnimatorToVisibility(int i, long l)
    {
        if(mVisibilityAnim != null)
            mVisibilityAnim.cancel();
        if(i == 0)
        {
            if(getVisibility() != 0)
            {
                setAlpha(0.0F);
                if(mSplitView != null && mMenuView != null)
                    mMenuView.setAlpha(0.0F);
            }
            ObjectAnimator objectanimator = ObjectAnimator.ofFloat(this, View.ALPHA, new float[] {
                1.0F
            });
            objectanimator.setDuration(l);
            objectanimator.setInterpolator(sAlphaInterpolator);
            if(mSplitView != null && mMenuView != null)
            {
                AnimatorSet animatorset = new AnimatorSet();
                ObjectAnimator objectanimator2 = ObjectAnimator.ofFloat(mMenuView, View.ALPHA, new float[] {
                    1.0F
                });
                objectanimator2.setDuration(l);
                animatorset.addListener(mVisAnimListener.withFinalVisibility(i));
                animatorset.play(objectanimator).with(objectanimator2);
                return animatorset;
            } else
            {
                objectanimator.addListener(mVisAnimListener.withFinalVisibility(i));
                return objectanimator;
            }
        }
        ObjectAnimator objectanimator3 = ObjectAnimator.ofFloat(this, View.ALPHA, new float[] {
            0.0F
        });
        objectanimator3.setDuration(l);
        objectanimator3.setInterpolator(sAlphaInterpolator);
        if(mSplitView != null && mMenuView != null)
        {
            AnimatorSet animatorset1 = new AnimatorSet();
            ObjectAnimator objectanimator1 = ObjectAnimator.ofFloat(mMenuView, View.ALPHA, new float[] {
                0.0F
            });
            objectanimator1.setDuration(l);
            animatorset1.addListener(mVisAnimListener.withFinalVisibility(i));
            animatorset1.play(objectanimator3).with(objectanimator1);
            return animatorset1;
        } else
        {
            objectanimator3.addListener(mVisAnimListener.withFinalVisibility(i));
            return objectanimator3;
        }
    }

    public boolean showOverflowMenu()
    {
        if(mActionMenuPresenter != null)
            return mActionMenuPresenter.showOverflowMenu();
        else
            return false;
    }

    private static final int FADE_DURATION = 200;
    private static final TimeInterpolator sAlphaInterpolator = new DecelerateInterpolator();
    protected ActionMenuPresenter mActionMenuPresenter;
    protected int mContentHeight;
    private boolean mEatingHover;
    private boolean mEatingTouch;
    protected ActionMenuView mMenuView;
    protected final Context mPopupContext;
    protected boolean mSplitActionBar;
    protected ViewGroup mSplitView;
    protected boolean mSplitWhenNarrow;
    protected final VisibilityAnimListener mVisAnimListener;
    protected Animator mVisibilityAnim;

}

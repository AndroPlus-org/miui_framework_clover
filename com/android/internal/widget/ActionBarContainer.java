// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.*;
import android.widget.FrameLayout;

// Referenced classes of package com.android.internal.widget:
//            ScrollingTabContainerView

public class ActionBarContainer extends FrameLayout
{
    private class ActionBarBackgroundDrawable extends Drawable
    {

        public void draw(Canvas canvas)
        {
            if(!ActionBarContainer._2D_get2(ActionBarContainer.this)) goto _L2; else goto _L1
_L1:
            if(ActionBarContainer._2D_get4(ActionBarContainer.this) != null)
                ActionBarContainer._2D_get4(ActionBarContainer.this).draw(canvas);
_L4:
            return;
_L2:
            if(ActionBarContainer._2D_get1(ActionBarContainer.this) != null)
                ActionBarContainer._2D_get1(ActionBarContainer.this).draw(canvas);
            if(ActionBarContainer._2D_get5(ActionBarContainer.this) != null && ActionBarContainer._2D_get3(ActionBarContainer.this))
                ActionBarContainer._2D_get5(ActionBarContainer.this).draw(canvas);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public int getOpacity()
        {
            if(ActionBarContainer._2D_get2(ActionBarContainer.this))
            {
                if(ActionBarContainer._2D_get4(ActionBarContainer.this) != null && ActionBarContainer._2D_get4(ActionBarContainer.this).getOpacity() == -1)
                    return -1;
            } else
            {
                if(ActionBarContainer._2D_get3(ActionBarContainer.this) && (ActionBarContainer._2D_get5(ActionBarContainer.this) == null || ActionBarContainer._2D_get5(ActionBarContainer.this).getOpacity() != -1))
                    return 0;
                if(!ActionBarContainer._2D_wrap0(ActionBarContainer._2D_get0(ActionBarContainer.this)) && ActionBarContainer._2D_get1(ActionBarContainer.this) != null && ActionBarContainer._2D_get1(ActionBarContainer.this).getOpacity() == -1)
                    return -1;
            }
            return 0;
        }

        public void getOutline(Outline outline)
        {
            if(!ActionBarContainer._2D_get2(ActionBarContainer.this)) goto _L2; else goto _L1
_L1:
            if(ActionBarContainer._2D_get4(ActionBarContainer.this) != null)
                ActionBarContainer._2D_get4(ActionBarContainer.this).getOutline(outline);
_L4:
            return;
_L2:
            if(ActionBarContainer._2D_get1(ActionBarContainer.this) != null)
                ActionBarContainer._2D_get1(ActionBarContainer.this).getOutline(outline);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void setAlpha(int i)
        {
        }

        public void setColorFilter(ColorFilter colorfilter)
        {
        }

        final ActionBarContainer this$0;

        private ActionBarBackgroundDrawable()
        {
            this$0 = ActionBarContainer.this;
            super();
        }

        ActionBarBackgroundDrawable(ActionBarBackgroundDrawable actionbarbackgrounddrawable)
        {
            this();
        }
    }


    static View _2D_get0(ActionBarContainer actionbarcontainer)
    {
        return actionbarcontainer.mActionBarView;
    }

    static Drawable _2D_get1(ActionBarContainer actionbarcontainer)
    {
        return actionbarcontainer.mBackground;
    }

    static boolean _2D_get2(ActionBarContainer actionbarcontainer)
    {
        return actionbarcontainer.mIsSplit;
    }

    static boolean _2D_get3(ActionBarContainer actionbarcontainer)
    {
        return actionbarcontainer.mIsStacked;
    }

    static Drawable _2D_get4(ActionBarContainer actionbarcontainer)
    {
        return actionbarcontainer.mSplitBackground;
    }

    static Drawable _2D_get5(ActionBarContainer actionbarcontainer)
    {
        return actionbarcontainer.mStackedBackground;
    }

    static boolean _2D_wrap0(View view)
    {
        return isCollapsed(view);
    }

    public ActionBarContainer(Context context)
    {
        this(context, null);
    }

    public ActionBarContainer(Context context, AttributeSet attributeset)
    {
        boolean flag;
        flag = true;
        super(context, attributeset);
        setBackground(new ActionBarBackgroundDrawable(null));
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ActionBar);
        mBackground = context.getDrawable(2);
        mStackedBackground = context.getDrawable(18);
        mHeight = context.getDimensionPixelSize(4, -1);
        if(getId() == 0x102040f)
        {
            mIsSplit = true;
            mSplitBackground = context.getDrawable(19);
        }
        context.recycle();
        if(!mIsSplit) goto _L2; else goto _L1
_L1:
        if(mSplitBackground != null)
            flag = false;
_L4:
        setWillNotDraw(flag);
        return;
_L2:
        if(mBackground != null || mStackedBackground != null)
            flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private int getMeasuredHeightWithMargins(View view)
    {
        android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)view.getLayoutParams();
        return view.getMeasuredHeight() + layoutparams.topMargin + layoutparams.bottomMargin;
    }

    private static boolean isCollapsed(View view)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(view == null) goto _L2; else goto _L1
_L1:
        if(view.getVisibility() != 8) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(view.getMeasuredHeight() != 0)
            flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        int ai[] = getDrawableState();
        boolean flag = false;
        Drawable drawable = mBackground;
        boolean flag1 = flag;
        if(drawable != null)
        {
            flag1 = flag;
            if(drawable.isStateful())
                flag1 = drawable.setState(ai);
        }
        drawable = mStackedBackground;
        flag = flag1;
        if(drawable != null)
        {
            flag = flag1;
            if(drawable.isStateful())
                flag = flag1 | drawable.setState(ai);
        }
        drawable = mSplitBackground;
        flag1 = flag;
        if(drawable != null)
        {
            flag1 = flag;
            if(drawable.isStateful())
                flag1 = flag | drawable.setState(ai);
        }
        if(flag1)
            invalidate();
    }

    public View getTabContainer()
    {
        return mTabContainer;
    }

    public void jumpDrawablesToCurrentState()
    {
        super.jumpDrawablesToCurrentState();
        if(mBackground != null)
            mBackground.jumpToCurrentState();
        if(mStackedBackground != null)
            mStackedBackground.jumpToCurrentState();
        if(mSplitBackground != null)
            mSplitBackground.jumpToCurrentState();
    }

    public void onFinishInflate()
    {
        super.onFinishInflate();
        mActionBarView = findViewById(0x102017d);
        mActionContextView = findViewById(0x1020182);
    }

    public boolean onHoverEvent(MotionEvent motionevent)
    {
        super.onHoverEvent(motionevent);
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        if(!mIsTransitioning)
            flag = super.onInterceptTouchEvent(motionevent);
        else
            flag = true;
        return flag;
    }

    public void onLayout(boolean flag, int i, int j, int k, int l)
    {
        View view;
        super.onLayout(flag, i, j, k, l);
        view = mTabContainer;
        if(view != null && view.getVisibility() != 8)
            flag = true;
        else
            flag = false;
        if(view != null && view.getVisibility() != 8)
        {
            j = getMeasuredHeight();
            android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)view.getLayoutParams();
            view.layout(i, j - view.getMeasuredHeight() - layoutparams.bottomMargin, k, j - layoutparams.bottomMargin);
        }
        j = 0;
        i = 0;
        if(!mIsSplit) goto _L2; else goto _L1
_L1:
        if(mSplitBackground != null)
        {
            mSplitBackground.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            i = 1;
        }
_L4:
        if(i != 0)
            invalidate();
        return;
_L2:
        if(mBackground != null)
        {
            if(mActionBarView.getVisibility() == 0)
                mBackground.setBounds(mActionBarView.getLeft(), mActionBarView.getTop(), mActionBarView.getRight(), mActionBarView.getBottom());
            else
            if(mActionContextView != null && mActionContextView.getVisibility() == 0)
                mBackground.setBounds(mActionContextView.getLeft(), mActionContextView.getTop(), mActionContextView.getRight(), mActionContextView.getBottom());
            else
                mBackground.setBounds(0, 0, 0, 0);
            j = 1;
        }
        mIsStacked = flag;
        i = j;
        if(flag)
        {
            i = j;
            if(mStackedBackground != null)
            {
                mStackedBackground.setBounds(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                i = 1;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onMeasure(int i, int j)
    {
        int k = j;
        if(mActionBarView == null)
        {
            k = j;
            if(android.view.View.MeasureSpec.getMode(j) == 0x80000000)
            {
                k = j;
                if(mHeight >= 0)
                    k = android.view.View.MeasureSpec.makeMeasureSpec(Math.min(mHeight, android.view.View.MeasureSpec.getSize(j)), 0x80000000);
            }
        }
        super.onMeasure(i, k);
        if(mActionBarView == null)
            return;
        if(mTabContainer != null && mTabContainer.getVisibility() != 8)
        {
            i = 0;
            int l = getChildCount();
            j = 0;
            while(j < l) 
            {
                View view = getChildAt(j);
                if(view != mTabContainer)
                {
                    int i1;
                    if(isCollapsed(view))
                        i1 = 0;
                    else
                        i1 = getMeasuredHeightWithMargins(view);
                    i = Math.max(i, i1);
                }
                j++;
            }
            if(android.view.View.MeasureSpec.getMode(k) == 0x80000000)
                j = android.view.View.MeasureSpec.getSize(k);
            else
                j = 0x7fffffff;
            setMeasuredDimension(getMeasuredWidth(), Math.min(getMeasuredHeightWithMargins(mTabContainer) + i, j));
        }
    }

    public void onResolveDrawables(int i)
    {
        super.onResolveDrawables(i);
        if(mBackground != null)
            mBackground.setLayoutDirection(i);
        if(mStackedBackground != null)
            mStackedBackground.setLayoutDirection(i);
        if(mSplitBackground != null)
            mSplitBackground.setLayoutDirection(i);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        super.onTouchEvent(motionevent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable)
    {
        boolean flag;
        flag = true;
        if(mBackground != null)
        {
            mBackground.setCallback(null);
            unscheduleDrawable(mBackground);
        }
        mBackground = drawable;
        if(drawable != null)
        {
            drawable.setCallback(this);
            if(mActionBarView != null)
                mBackground.setBounds(mActionBarView.getLeft(), mActionBarView.getTop(), mActionBarView.getRight(), mActionBarView.getBottom());
        }
        if(!mIsSplit) goto _L2; else goto _L1
_L1:
        if(mSplitBackground != null)
            flag = false;
_L4:
        setWillNotDraw(flag);
        invalidate();
        return;
_L2:
        if(mBackground != null || mStackedBackground != null)
            flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setSplitBackground(Drawable drawable)
    {
        boolean flag;
        flag = true;
        if(mSplitBackground != null)
        {
            mSplitBackground.setCallback(null);
            unscheduleDrawable(mSplitBackground);
        }
        mSplitBackground = drawable;
        if(drawable != null)
        {
            drawable.setCallback(this);
            if(mIsSplit && mSplitBackground != null)
                mSplitBackground.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        }
        if(!mIsSplit) goto _L2; else goto _L1
_L1:
        if(mSplitBackground != null)
            flag = false;
_L4:
        setWillNotDraw(flag);
        invalidate();
        return;
_L2:
        if(mBackground != null || mStackedBackground != null)
            flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setStackedBackground(Drawable drawable)
    {
        boolean flag;
        flag = true;
        if(mStackedBackground != null)
        {
            mStackedBackground.setCallback(null);
            unscheduleDrawable(mStackedBackground);
        }
        mStackedBackground = drawable;
        if(drawable != null)
        {
            drawable.setCallback(this);
            if(mIsStacked && mStackedBackground != null)
                mStackedBackground.setBounds(mTabContainer.getLeft(), mTabContainer.getTop(), mTabContainer.getRight(), mTabContainer.getBottom());
        }
        if(!mIsSplit) goto _L2; else goto _L1
_L1:
        if(mSplitBackground != null)
            flag = false;
_L4:
        setWillNotDraw(flag);
        invalidate();
        return;
_L2:
        if(mBackground != null || mStackedBackground != null)
            flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setTabContainer(ScrollingTabContainerView scrollingtabcontainerview)
    {
        if(mTabContainer != null)
            removeView(mTabContainer);
        mTabContainer = scrollingtabcontainerview;
        if(scrollingtabcontainerview != null)
        {
            addView(scrollingtabcontainerview);
            android.view.ViewGroup.LayoutParams layoutparams = scrollingtabcontainerview.getLayoutParams();
            layoutparams.width = -1;
            layoutparams.height = -2;
            scrollingtabcontainerview.setAllowCollapse(false);
        }
    }

    public void setTransitioning(boolean flag)
    {
        mIsTransitioning = flag;
        int i;
        if(flag)
            i = 0x60000;
        else
            i = 0x40000;
        setDescendantFocusability(i);
    }

    public void setVisibility(int i)
    {
        super.setVisibility(i);
        boolean flag;
        if(i == 0)
            flag = true;
        else
            flag = false;
        if(mBackground != null)
            mBackground.setVisible(flag, false);
        if(mStackedBackground != null)
            mStackedBackground.setVisible(flag, false);
        if(mSplitBackground != null)
            mSplitBackground.setVisible(flag, false);
    }

    public ActionMode startActionModeForChild(View view, android.view.ActionMode.Callback callback, int i)
    {
        if(i != 0)
            return super.startActionModeForChild(view, callback, i);
        else
            return null;
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        boolean flag;
        if((drawable != mBackground || !(mIsSplit ^ true)) && (drawable != mStackedBackground || !mIsStacked) && (drawable != mSplitBackground || !mIsSplit))
            flag = super.verifyDrawable(drawable);
        else
            flag = true;
        return flag;
    }

    private View mActionBarView;
    private View mActionContextView;
    private Drawable mBackground;
    private int mHeight;
    private boolean mIsSplit;
    private boolean mIsStacked;
    private boolean mIsTransitioning;
    private Drawable mSplitBackground;
    private Drawable mStackedBackground;
    private View mTabContainer;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import android.widget.*;
import com.android.internal.view.menu.MenuBuilder;

// Referenced classes of package com.android.internal.widget:
//            AbsActionBarView

public class ActionBarContextView extends AbsActionBarView
{

    public ActionBarContextView(Context context)
    {
        this(context, null);
    }

    public ActionBarContextView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010394);
    }

    public ActionBarContextView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public ActionBarContextView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ActionMode, i, j);
        setBackground(context.getDrawable(0));
        mTitleStyleRes = context.getResourceId(2, 0);
        mSubtitleStyleRes = context.getResourceId(3, 0);
        mContentHeight = context.getLayoutDimension(1, 0);
        mSplitBackground = context.getDrawable(4);
        mCloseItemLayout = context.getResourceId(5, 0x1090021);
        context.recycle();
    }

    private void initTitle()
    {
label0:
        {
            byte byte0 = 8;
            if(mTitleLayout == null)
            {
                LayoutInflater.from(getContext()).inflate(0x109001c, this);
                mTitleLayout = (LinearLayout)getChildAt(getChildCount() - 1);
                mTitleView = (TextView)mTitleLayout.findViewById(0x1020181);
                mSubtitleView = (TextView)mTitleLayout.findViewById(0x1020180);
                if(mTitleStyleRes != 0)
                    mTitleView.setTextAppearance(mTitleStyleRes);
                if(mSubtitleStyleRes != 0)
                    mSubtitleView.setTextAppearance(mSubtitleStyleRes);
            }
            mTitleView.setText(mTitle);
            mSubtitleView.setText(mSubtitle);
            boolean flag = TextUtils.isEmpty(mTitle);
            boolean flag1 = TextUtils.isEmpty(mSubtitle) ^ true;
            Object obj = mSubtitleView;
            int i;
            if(flag1)
                i = 0;
            else
                i = 8;
            ((TextView) (obj)).setVisibility(i);
            obj = mTitleLayout;
            if(!(flag ^ true))
            {
                i = byte0;
                if(!flag1)
                    break label0;
            }
            i = 0;
        }
        ((LinearLayout) (obj)).setVisibility(i);
        if(mTitleLayout.getParent() == null)
            addView(mTitleLayout);
    }

    public void closeMode()
    {
        if(mClose == null)
        {
            killMode();
            return;
        } else
        {
            return;
        }
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new android.view.ViewGroup.MarginLayoutParams(-1, -2);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new android.view.ViewGroup.MarginLayoutParams(getContext(), attributeset);
    }

    public CharSequence getSubtitle()
    {
        return mSubtitle;
    }

    public CharSequence getTitle()
    {
        return mTitle;
    }

    public boolean hideOverflowMenu()
    {
        if(mActionMenuPresenter != null)
            return mActionMenuPresenter.hideOverflowMenu();
        else
            return false;
    }

    public void initForMode(final ActionMode mode)
    {
        MenuBuilder menubuilder;
        if(mClose == null)
        {
            mClose = LayoutInflater.from(mContext).inflate(mCloseItemLayout, this, false);
            addView(mClose);
        } else
        if(mClose.getParent() == null)
            addView(mClose);
        mClose.findViewById(0x1020188).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                mode.finish();
            }

            final ActionBarContextView this$0;
            final ActionMode val$mode;

            
            {
                this$0 = ActionBarContextView.this;
                mode = actionmode;
                super();
            }
        }
);
        menubuilder = (MenuBuilder)mode.getMenu();
        if(mActionMenuPresenter != null)
            mActionMenuPresenter.dismissPopupMenus();
        mActionMenuPresenter = new ActionMenuPresenter(mContext);
        mActionMenuPresenter.setReserveOverflow(true);
        mode = new android.view.ViewGroup.LayoutParams(-2, -1);
        if(!mSplitActionBar)
        {
            menubuilder.addMenuPresenter(mActionMenuPresenter, mPopupContext);
            mMenuView = (ActionMenuView)mActionMenuPresenter.getMenuView(this);
            mMenuView.setBackground(null);
            addView(mMenuView, mode);
        } else
        {
            mActionMenuPresenter.setWidthLimit(getContext().getResources().getDisplayMetrics().widthPixels, true);
            mActionMenuPresenter.setItemLimit(0x7fffffff);
            mode.width = -1;
            mode.height = mContentHeight;
            menubuilder.addMenuPresenter(mActionMenuPresenter, mPopupContext);
            mMenuView = (ActionMenuView)mActionMenuPresenter.getMenuView(this);
            mMenuView.setBackgroundDrawable(mSplitBackground);
            mSplitView.addView(mMenuView, mode);
        }
    }

    public boolean isOverflowMenuShowing()
    {
        if(mActionMenuPresenter != null)
            return mActionMenuPresenter.isOverflowMenuShowing();
        else
            return false;
    }

    public boolean isTitleOptional()
    {
        return mTitleOptional;
    }

    public void killMode()
    {
        removeAllViews();
        if(mSplitView != null)
            mSplitView.removeView(mMenuView);
        mCustomView = null;
        mMenuView = null;
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if(mActionMenuPresenter != null)
        {
            mActionMenuPresenter.hideOverflowMenu();
            mActionMenuPresenter.hideSubMenus();
        }
    }

    public void onInitializeAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        if(accessibilityevent.getEventType() == 32)
        {
            accessibilityevent.setSource(this);
            accessibilityevent.setClassName(getClass().getName());
            accessibilityevent.setPackageName(getContext().getPackageName());
            accessibilityevent.setContentDescription(mTitle);
        } else
        {
            super.onInitializeAccessibilityEventInternal(accessibilityevent);
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        flag = isLayoutRtl();
        int i1;
        int j1;
        int k1;
        if(flag)
            i1 = k - i - getPaddingRight();
        else
            i1 = getPaddingLeft();
        j1 = getPaddingTop();
        k1 = l - j - getPaddingTop() - getPaddingBottom();
        j = i1;
        if(mClose != null)
        {
            j = i1;
            if(mClose.getVisibility() != 8)
            {
                android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)mClose.getLayoutParams();
                if(flag)
                    j = marginlayoutparams.rightMargin;
                else
                    j = marginlayoutparams.leftMargin;
                if(flag)
                    l = marginlayoutparams.leftMargin;
                else
                    l = marginlayoutparams.rightMargin;
                j = next(i1, j, flag);
                j = next(j + positionChild(mClose, j, j1, k1, flag), l, flag);
            }
        }
        l = j;
        if(mTitleLayout != null)
        {
            l = j;
            if(mCustomView == null)
            {
                l = j;
                if(mTitleLayout.getVisibility() != 8)
                    l = j + positionChild(mTitleLayout, j, j1, k1, flag);
            }
        }
        if(mCustomView != null)
            positionChild(mCustomView, l, j1, k1, flag);
        if(flag)
            i = getPaddingLeft();
        else
            i = k - i - getPaddingRight();
        if(mMenuView != null)
            positionChild(mMenuView, i, j1, k1, flag ^ true);
    }

    protected void onMeasure(int i, int j)
    {
        if(android.view.View.MeasureSpec.getMode(i) != 0x40000000)
            throw new IllegalStateException((new StringBuilder()).append(getClass().getSimpleName()).append(" can only be used ").append("with android:layout_width=\"match_parent\" (or fill_parent)").toString());
        if(android.view.View.MeasureSpec.getMode(j) == 0)
            throw new IllegalStateException((new StringBuilder()).append(getClass().getSimpleName()).append(" can only be used ").append("with android:layout_height=\"wrap_content\"").toString());
        int k = android.view.View.MeasureSpec.getSize(i);
        int l;
        int i1;
        int j1;
        int k1;
        if(mContentHeight > 0)
            l = mContentHeight;
        else
            l = android.view.View.MeasureSpec.getSize(j);
        i1 = getPaddingTop() + getPaddingBottom();
        i = k - getPaddingLeft() - getPaddingRight();
        j1 = l - i1;
        k1 = android.view.View.MeasureSpec.makeMeasureSpec(j1, 0x80000000);
        j = i;
        if(mClose != null)
        {
            i = measureChildView(mClose, i, k1, 0);
            android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)mClose.getLayoutParams();
            j = i - (marginlayoutparams.leftMargin + marginlayoutparams.rightMargin);
        }
        i = j;
        if(mMenuView != null)
        {
            i = j;
            if(mMenuView.getParent() == this)
                i = measureChildView(mMenuView, j, k1, 0);
        }
        j = i;
        if(mTitleLayout != null)
        {
            j = i;
            if(mCustomView == null)
                if(mTitleOptional)
                {
                    j = android.view.View.MeasureSpec.makeSafeMeasureSpec(k, 0);
                    mTitleLayout.measure(j, k1);
                    int l1 = mTitleLayout.getMeasuredWidth();
                    Object obj;
                    if(l1 <= i)
                        k1 = 1;
                    else
                        k1 = 0;
                    j = i;
                    if(k1 != 0)
                        j = i - l1;
                    obj = mTitleLayout;
                    if(k1 != 0)
                        i = 0;
                    else
                        i = 8;
                    ((LinearLayout) (obj)).setVisibility(i);
                } else
                {
                    j = measureChildView(mTitleLayout, i, k1, 0);
                }
        }
        if(mCustomView != null)
        {
            obj = mCustomView.getLayoutParams();
            if(((android.view.ViewGroup.LayoutParams) (obj)).width != -2)
                i = 0x40000000;
            else
                i = 0x80000000;
            if(((android.view.ViewGroup.LayoutParams) (obj)).width >= 0)
                j = Math.min(((android.view.ViewGroup.LayoutParams) (obj)).width, j);
            if(((android.view.ViewGroup.LayoutParams) (obj)).height != -2)
                k1 = 0x40000000;
            else
                k1 = 0x80000000;
            if(((android.view.ViewGroup.LayoutParams) (obj)).height >= 0)
                j1 = Math.min(((android.view.ViewGroup.LayoutParams) (obj)).height, j1);
            mCustomView.measure(android.view.View.MeasureSpec.makeMeasureSpec(j, i), android.view.View.MeasureSpec.makeMeasureSpec(j1, k1));
        }
        if(mContentHeight <= 0)
        {
            l = 0;
            j1 = getChildCount();
            for(j = 0; j < j1;)
            {
                k1 = getChildAt(j).getMeasuredHeight() + i1;
                i = l;
                if(k1 > l)
                    i = k1;
                j++;
                l = i;
            }

            setMeasuredDimension(k, l);
        } else
        {
            setMeasuredDimension(k, l);
        }
    }

    public void setContentHeight(int i)
    {
        mContentHeight = i;
    }

    public void setCustomView(View view)
    {
        if(mCustomView != null)
            removeView(mCustomView);
        mCustomView = view;
        if(view != null && mTitleLayout != null)
        {
            removeView(mTitleLayout);
            mTitleLayout = null;
        }
        if(view != null)
            addView(view);
        requestLayout();
    }

    public void setSplitToolbar(boolean flag)
    {
        if(mSplitActionBar != flag)
        {
            if(mActionMenuPresenter != null)
            {
                android.view.ViewGroup.LayoutParams layoutparams = new android.view.ViewGroup.LayoutParams(-2, -1);
                if(!flag)
                {
                    mMenuView = (ActionMenuView)mActionMenuPresenter.getMenuView(this);
                    mMenuView.setBackground(null);
                    ViewGroup viewgroup = (ViewGroup)mMenuView.getParent();
                    if(viewgroup != null)
                        viewgroup.removeView(mMenuView);
                    addView(mMenuView, layoutparams);
                } else
                {
                    mActionMenuPresenter.setWidthLimit(getContext().getResources().getDisplayMetrics().widthPixels, true);
                    mActionMenuPresenter.setItemLimit(0x7fffffff);
                    layoutparams.width = -1;
                    layoutparams.height = mContentHeight;
                    mMenuView = (ActionMenuView)mActionMenuPresenter.getMenuView(this);
                    mMenuView.setBackground(mSplitBackground);
                    ViewGroup viewgroup1 = (ViewGroup)mMenuView.getParent();
                    if(viewgroup1 != null)
                        viewgroup1.removeView(mMenuView);
                    mSplitView.addView(mMenuView, layoutparams);
                }
            }
            super.setSplitToolbar(flag);
        }
    }

    public void setSubtitle(CharSequence charsequence)
    {
        mSubtitle = charsequence;
        initTitle();
    }

    public void setTitle(CharSequence charsequence)
    {
        mTitle = charsequence;
        initTitle();
    }

    public void setTitleOptional(boolean flag)
    {
        if(flag != mTitleOptional)
            requestLayout();
        mTitleOptional = flag;
    }

    public boolean shouldDelayChildPressedState()
    {
        return false;
    }

    public boolean showOverflowMenu()
    {
        if(mActionMenuPresenter != null)
            return mActionMenuPresenter.showOverflowMenu();
        else
            return false;
    }

    private static final String TAG = "ActionBarContextView";
    private View mClose;
    private int mCloseItemLayout;
    private View mCustomView;
    private Drawable mSplitBackground;
    private CharSequence mSubtitle;
    private int mSubtitleStyleRes;
    private TextView mSubtitleView;
    private CharSequence mTitle;
    private LinearLayout mTitleLayout;
    private boolean mTitleOptional;
    private int mTitleStyleRes;
    private TextView mTitleView;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ListView;
import java.util.ArrayList;

public class WatchListDecorLayout extends FrameLayout
    implements android.view.ViewTreeObserver.OnScrollChangedListener
{

    public WatchListDecorLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mForegroundPaddingLeft = 0;
        mForegroundPaddingTop = 0;
        mForegroundPaddingRight = 0;
        mForegroundPaddingBottom = 0;
        mMatchParentChildren = new ArrayList(1);
    }

    public WatchListDecorLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mForegroundPaddingLeft = 0;
        mForegroundPaddingTop = 0;
        mForegroundPaddingRight = 0;
        mForegroundPaddingBottom = 0;
        mMatchParentChildren = new ArrayList(1);
    }

    public WatchListDecorLayout(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mForegroundPaddingLeft = 0;
        mForegroundPaddingTop = 0;
        mForegroundPaddingRight = 0;
        mForegroundPaddingBottom = 0;
        mMatchParentChildren = new ArrayList(1);
    }

    private void applyMeasureToChild(View view, int i, int j)
    {
        android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)view.getLayoutParams();
        if(marginlayoutparams.width == -1)
            i = android.view.View.MeasureSpec.makeMeasureSpec(Math.max(0, getMeasuredWidth() - getPaddingLeftWithForeground() - getPaddingRightWithForeground() - marginlayoutparams.leftMargin - marginlayoutparams.rightMargin), 0x40000000);
        else
            i = getChildMeasureSpec(i, getPaddingLeftWithForeground() + getPaddingRightWithForeground() + marginlayoutparams.leftMargin + marginlayoutparams.rightMargin, marginlayoutparams.width);
        if(marginlayoutparams.height == -1)
            j = android.view.View.MeasureSpec.makeMeasureSpec(Math.max(0, getMeasuredHeight() - getPaddingTopWithForeground() - getPaddingBottomWithForeground() - marginlayoutparams.topMargin - marginlayoutparams.bottomMargin), 0x40000000);
        else
            j = getChildMeasureSpec(j, getPaddingTopWithForeground() + getPaddingBottomWithForeground() + marginlayoutparams.topMargin + marginlayoutparams.bottomMargin, marginlayoutparams.height);
        view.measure(i, j);
    }

    private int getPaddingBottomWithForeground()
    {
        int i;
        if(isForegroundInsidePadding())
            i = Math.max(mPaddingBottom, mForegroundPaddingBottom);
        else
            i = mPaddingBottom + mForegroundPaddingBottom;
        return i;
    }

    private int getPaddingLeftWithForeground()
    {
        int i;
        if(isForegroundInsidePadding())
            i = Math.max(mPaddingLeft, mForegroundPaddingLeft);
        else
            i = mPaddingLeft + mForegroundPaddingLeft;
        return i;
    }

    private int getPaddingRightWithForeground()
    {
        int i;
        if(isForegroundInsidePadding())
            i = Math.max(mPaddingRight, mForegroundPaddingRight);
        else
            i = mPaddingRight + mForegroundPaddingRight;
        return i;
    }

    private int getPaddingTopWithForeground()
    {
        int i;
        if(isForegroundInsidePadding())
            i = Math.max(mPaddingTop, mForegroundPaddingTop);
        else
            i = mPaddingTop + mForegroundPaddingTop;
        return i;
    }

    private int measureAndGetHeight(View view, int i, int j)
    {
        if(view != null)
        {
            if(view.getVisibility() != 8)
            {
                applyMeasureToChild(mBottomPanel, i, j);
                return view.getMeasuredHeight();
            }
            if(getMeasureAllChildren())
                applyMeasureToChild(mBottomPanel, i, j);
        }
        return 0;
    }

    private void setScrolling(View view, float f)
    {
        if(view.getTranslationY() != f)
            view.setTranslationY(f);
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        mPendingScroll = 0;
        int i = 0;
        while(i < getChildCount()) 
        {
            View view = getChildAt(i);
            if(view instanceof ListView)
            {
                if(mListView != null)
                    throw new IllegalArgumentException("only one ListView child allowed");
                mListView = (ListView)view;
                mListView.setNestedScrollingEnabled(true);
                mObserver = mListView.getViewTreeObserver();
                mObserver.addOnScrollChangedListener(this);
            } else
            {
                int j = ((android.widget.FrameLayout.LayoutParams)view.getLayoutParams()).gravity & 0x70;
                if(j == 48 && mTopPanel == null)
                    mTopPanel = view;
                else
                if(j == 80 && mBottomPanel == null)
                    mBottomPanel = view;
            }
            i++;
        }
    }

    public void onDetachedFromWindow()
    {
        mListView = null;
        mBottomPanel = null;
        mTopPanel = null;
        if(mObserver != null)
        {
            if(mObserver.isAlive())
                mObserver.removeOnScrollChangedListener(this);
            mObserver = null;
        }
    }

    protected void onMeasure(int i, int j)
    {
        int k = getChildCount();
        int l;
        int k1;
        int l1;
        int i2;
        if(android.view.View.MeasureSpec.getMode(i) == 0x40000000)
        {
            if(android.view.View.MeasureSpec.getMode(j) != 0x40000000)
                l = 1;
            else
                l = 0;
        } else
        {
            l = 1;
        }
        mMatchParentChildren.clear();
        k1 = 0;
        l1 = 0;
        i2 = 0;
        for(int j2 = 0; j2 < k;)
        {
            int k2;
            int l2;
            int j3;
label0:
            {
                View view = getChildAt(j2);
                if(!getMeasureAllChildren())
                {
                    k2 = i2;
                    l2 = k1;
                    j3 = l1;
                    if(view.getVisibility() == 8)
                        break label0;
                }
                measureChildWithMargins(view, i, 0, j, 0);
                android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)view.getLayoutParams();
                l1 = Math.max(l1, view.getMeasuredWidth() + layoutparams.leftMargin + layoutparams.rightMargin);
                k1 = Math.max(k1, view.getMeasuredHeight() + layoutparams.topMargin + layoutparams.bottomMargin);
                i2 = combineMeasuredStates(i2, view.getMeasuredState());
                k2 = i2;
                l2 = k1;
                j3 = l1;
                if(!l)
                    break label0;
                if(layoutparams.width != -1)
                {
                    k2 = i2;
                    l2 = k1;
                    j3 = l1;
                    if(layoutparams.height != -1)
                        break label0;
                }
                mMatchParentChildren.add(view);
                j3 = l1;
                l2 = k1;
                k2 = i2;
            }
            j2++;
            i2 = k2;
            k1 = l2;
            l1 = j3;
        }

        int i3 = getPaddingLeftWithForeground();
        l = getPaddingRightWithForeground();
        k1 = Math.max(k1 + (getPaddingTopWithForeground() + getPaddingBottomWithForeground()), getSuggestedMinimumHeight());
        i3 = Math.max(l1 + (i3 + l), getSuggestedMinimumWidth());
        Drawable drawable = getForeground();
        l1 = k1;
        l = i3;
        if(drawable != null)
        {
            l1 = Math.max(k1, drawable.getMinimumHeight());
            l = Math.max(i3, drawable.getMinimumWidth());
        }
        setMeasuredDimension(resolveSizeAndState(l, i, i2), resolveSizeAndState(l1, j, i2 << 16));
        if(mListView != null)
        {
            if(mPendingScroll != 0)
            {
                mListView.scrollListBy(mPendingScroll);
                mPendingScroll = 0;
            }
            int i1 = Math.max(mListView.getPaddingTop(), measureAndGetHeight(mTopPanel, i, j));
            i2 = Math.max(mListView.getPaddingBottom(), measureAndGetHeight(mBottomPanel, i, j));
            if(i1 != mListView.getPaddingTop() || i2 != mListView.getPaddingBottom())
            {
                mPendingScroll = mPendingScroll + (mListView.getPaddingTop() - i1);
                mListView.setPadding(mListView.getPaddingLeft(), i1, mListView.getPaddingRight(), i2);
            }
        }
        i2 = mMatchParentChildren.size();
        if(i2 > 1)
        {
            for(int j1 = 0; j1 < i2; j1++)
            {
                View view1 = (View)mMatchParentChildren.get(j1);
                if(mListView == null || view1 != mTopPanel && view1 != mBottomPanel)
                    applyMeasureToChild(view1, i, j);
            }

        }
    }

    public void onScrollChanged()
    {
        if(mListView == null)
            return;
        if(mTopPanel != null)
            if(mListView.getChildCount() > 0)
            {
                if(mListView.getFirstVisiblePosition() == 0)
                {
                    View view = mListView.getChildAt(0);
                    setScrolling(mTopPanel, view.getY() - (float)mTopPanel.getHeight() - (float)mTopPanel.getTop());
                } else
                {
                    setScrolling(mTopPanel, -mTopPanel.getHeight());
                }
            } else
            {
                setScrolling(mTopPanel, 0.0F);
            }
        if(mBottomPanel != null)
            if(mListView.getChildCount() > 0)
            {
                if(mListView.getLastVisiblePosition() >= mListView.getCount() - 1)
                {
                    View view1 = mListView.getChildAt(mListView.getChildCount() - 1);
                    setScrolling(mBottomPanel, Math.max(0.0F, (view1.getY() + (float)view1.getHeight()) - (float)mBottomPanel.getTop()));
                } else
                {
                    setScrolling(mBottomPanel, mBottomPanel.getHeight());
                }
            } else
            {
                setScrolling(mBottomPanel, 0.0F);
            }
    }

    public void setForegroundGravity(int i)
    {
        if(getForegroundGravity() != i)
        {
            super.setForegroundGravity(i);
            Drawable drawable = getForeground();
            if(getForegroundGravity() == 119 && drawable != null)
            {
                Rect rect = new Rect();
                if(drawable.getPadding(rect))
                {
                    mForegroundPaddingLeft = rect.left;
                    mForegroundPaddingTop = rect.top;
                    mForegroundPaddingRight = rect.right;
                    mForegroundPaddingBottom = rect.bottom;
                }
            } else
            {
                mForegroundPaddingLeft = 0;
                mForegroundPaddingTop = 0;
                mForegroundPaddingRight = 0;
                mForegroundPaddingBottom = 0;
            }
        }
    }

    private View mBottomPanel;
    private int mForegroundPaddingBottom;
    private int mForegroundPaddingLeft;
    private int mForegroundPaddingRight;
    private int mForegroundPaddingTop;
    private ListView mListView;
    private final ArrayList mMatchParentChildren;
    private ViewTreeObserver mObserver;
    private int mPendingScroll;
    private View mTopPanel;
}

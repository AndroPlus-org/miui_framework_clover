// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.content.res.*;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

public class ButtonBarLayout extends LinearLayout
{

    public ButtonBarLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mLastWidthSize = -1;
        mMinimumHeight = 0;
        boolean flag;
        if(context.getResources().getConfiguration().screenHeightDp >= 320)
            flag = true;
        else
            flag = false;
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ButtonBarLayout);
        mAllowStacking = context.getBoolean(0, flag);
        context.recycle();
    }

    private int getNextVisibleChildIndex(int i)
    {
        for(int j = getChildCount(); i < j; i++)
            if(getChildAt(i).getVisibility() == 0)
                return i;

        return -1;
    }

    private boolean isStacked()
    {
        boolean flag = true;
        if(getOrientation() != 1)
            flag = false;
        return flag;
    }

    private void setStacked(boolean flag)
    {
        int i = 0;
        if(flag)
            i = 1;
        setOrientation(i);
        View view;
        if(flag)
            i = 5;
        else
            i = 80;
        setGravity(i);
        view = findViewById(0x1020408);
        if(view != null)
        {
            if(flag)
                i = 8;
            else
                i = 4;
            view.setVisibility(i);
        }
        for(i = getChildCount() - 2; i >= 0; i--)
            bringChildToFront(getChildAt(i));

    }

    public int getMinimumHeight()
    {
        return Math.max(mMinimumHeight, super.getMinimumHeight());
    }

    protected void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getSize(i);
        if(mAllowStacking)
        {
            if(k > mLastWidthSize && isStacked())
                setStacked(false);
            mLastWidthSize = k;
        }
        int l = 0;
        if(!isStacked() && android.view.View.MeasureSpec.getMode(i) == 0x40000000)
        {
            k = android.view.View.MeasureSpec.makeMeasureSpec(k, 0x80000000);
            l = 1;
        } else
        {
            k = i;
        }
        super.onMeasure(k, j);
        k = l;
        if(mAllowStacking)
        {
            k = l;
            if(isStacked() ^ true)
            {
                k = l;
                if((getMeasuredWidthAndState() & 0xff000000) == 0x1000000)
                {
                    setStacked(true);
                    k = 1;
                }
            }
        }
        if(k != 0)
            super.onMeasure(i, j);
        i = 0;
        l = getNextVisibleChildIndex(0);
        if(l >= 0)
        {
            View view = getChildAt(l);
            android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)view.getLayoutParams();
            j = getPaddingTop() + view.getMeasuredHeight() + layoutparams.topMargin + layoutparams.bottomMargin + 0;
            if(isStacked())
            {
                l = getNextVisibleChildIndex(l + 1);
                i = j;
                if(l >= 0)
                    i = (int)((float)j + ((float)getChildAt(l).getPaddingTop() + getResources().getDisplayMetrics().density * 16F));
            } else
            {
                i = j + getPaddingBottom();
            }
        }
        if(getMinimumHeight() != i)
            setMinimumHeight(i);
    }

    public void setAllowStacking(boolean flag)
    {
        if(mAllowStacking != flag)
        {
            mAllowStacking = flag;
            if(!mAllowStacking && getOrientation() == 1)
                setStacked(false);
            requestLayout();
        }
    }

    private static final int ALLOW_STACKING_MIN_HEIGHT_DP = 320;
    private static final int PEEK_BUTTON_DP = 16;
    private boolean mAllowStacking;
    private int mLastWidthSize;
    private int mMinimumHeight;
}

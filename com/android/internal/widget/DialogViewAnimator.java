// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ViewAnimator;
import java.util.ArrayList;

public class DialogViewAnimator extends ViewAnimator
{

    public DialogViewAnimator(Context context)
    {
        super(context);
        mMatchParentChildren = new ArrayList(1);
    }

    public DialogViewAnimator(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mMatchParentChildren = new ArrayList(1);
    }

    protected void onMeasure(int i, int j)
    {
        boolean flag;
        int l;
        int j1;
        int l1;
        int i2;
        int j2;
        if(android.view.View.MeasureSpec.getMode(i) == 0x40000000)
        {
            if(android.view.View.MeasureSpec.getMode(j) != 0x40000000)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = true;
        }
        l = 0;
        j1 = 0;
        l1 = 0;
        i2 = getChildCount();
        j2 = 0;
        while(j2 < i2) 
        {
label0:
            {
                View view = getChildAt(j2);
                int k2;
                int l2;
                int j3;
                if(!getMeasureAllChildren())
                {
                    k2 = l1;
                    l2 = l;
                    j3 = j1;
                    if(view.getVisibility() == 8)
                        break label0;
                }
                android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)view.getLayoutParams();
                int l3;
                boolean flag1;
                if(layoutparams.width == -1)
                    l3 = 1;
                else
                    l3 = 0;
                if(layoutparams.height == -1)
                    k2 = 1;
                else
                    k2 = 0;
                if(flag && (l3 != 0 || k2 != 0))
                    mMatchParentChildren.add(view);
                measureChildWithMargins(view, i, 0, j, 0);
                flag1 = false;
                j3 = j1;
                l2 = ((flag1) ? 1 : 0);
                if(flag)
                {
                    j3 = j1;
                    l2 = ((flag1) ? 1 : 0);
                    if(l3 ^ true)
                    {
                        j3 = Math.max(j1, view.getMeasuredWidth() + layoutparams.leftMargin + layoutparams.rightMargin);
                        l2 = view.getMeasuredWidthAndState() & 0xff000000 | 0;
                    }
                }
                j1 = l;
                l3 = l2;
                if(flag)
                {
                    j1 = l;
                    l3 = l2;
                    if((k2 ^ 1) != 0)
                    {
                        j1 = Math.max(l, view.getMeasuredHeight() + layoutparams.topMargin + layoutparams.bottomMargin);
                        l3 = l2 | view.getMeasuredHeightAndState() >> 16 & 0xffffff00;
                    }
                }
                k2 = combineMeasuredStates(l1, l3);
                l2 = j1;
            }
            j2++;
            l1 = k2;
            l = l2;
            j1 = j3;
        }
        int k = getPaddingLeft();
        int i3 = getPaddingRight();
        int k3 = Math.max(l + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight());
        j1 = Math.max(j1 + (k + i3), getSuggestedMinimumWidth());
        Drawable drawable = getForeground();
        l = k3;
        k = j1;
        if(drawable != null)
        {
            l = Math.max(k3, drawable.getMinimumHeight());
            k = Math.max(j1, drawable.getMinimumWidth());
        }
        setMeasuredDimension(resolveSizeAndState(k, i, l1), resolveSizeAndState(l, j, l1 << 16));
        k3 = mMatchParentChildren.size();
        k = 0;
        while(k < k3) 
        {
            View view1 = (View)mMatchParentChildren.get(k);
            android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)view1.getLayoutParams();
            int i1;
            int k1;
            if(marginlayoutparams.width == -1)
                i1 = android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - marginlayoutparams.leftMargin - marginlayoutparams.rightMargin, 0x40000000);
            else
                i1 = getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginlayoutparams.leftMargin + marginlayoutparams.rightMargin, marginlayoutparams.width);
            if(marginlayoutparams.height == -1)
                k1 = android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - marginlayoutparams.topMargin - marginlayoutparams.bottomMargin, 0x40000000);
            else
                k1 = getChildMeasureSpec(j, getPaddingTop() + getPaddingBottom() + marginlayoutparams.topMargin + marginlayoutparams.bottomMargin, marginlayoutparams.height);
            view1.measure(i1, k1);
            k++;
        }
        mMatchParentChildren.clear();
    }

    private final ArrayList mMatchParentChildren;
}

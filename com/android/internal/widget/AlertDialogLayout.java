// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.*;
import android.widget.LinearLayout;

public class AlertDialogLayout extends LinearLayout
{

    public AlertDialogLayout(Context context)
    {
        super(context);
    }

    public AlertDialogLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public AlertDialogLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    public AlertDialogLayout(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
    }

    private void forceUniformWidth(int i, int j)
    {
        int k = android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0x40000000);
        for(int l = 0; l < i; l++)
        {
            View view = getChildAt(l);
            if(view.getVisibility() == 8)
                continue;
            android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)view.getLayoutParams();
            if(layoutparams.width == -1)
            {
                int i1 = layoutparams.height;
                layoutparams.height = view.getMeasuredHeight();
                measureChildWithMargins(view, k, 0, j, 0);
                layoutparams.height = i1;
            }
        }

    }

    private int resolveMinimumHeight(View view)
    {
        int i = view.getMinimumHeight();
        if(i > 0)
            return i;
        if(view instanceof ViewGroup)
        {
            view = (ViewGroup)view;
            if(view.getChildCount() == 1)
                return resolveMinimumHeight(view.getChildAt(0));
        }
        return 0;
    }

    private void setChildFrame(View view, int i, int j, int k, int l)
    {
        view.layout(i, j, i + k, j + l);
    }

    private boolean tryOnMeasure(int i, int j)
    {
        View view;
        View view1;
        View view2;
        int k;
        int l;
        view = null;
        view1 = null;
        view2 = null;
        k = getChildCount();
        l = 0;
_L5:
        if(l >= k) goto _L2; else goto _L1
_L1:
        View view3 = getChildAt(l);
        if(view3.getVisibility() != 8) goto _L4; else goto _L3
_L3:
        l++;
          goto _L5
_L4:
        switch(view3.getId())
        {
        default:
            return false;

        case 16909412: 
            view = view3;
            break;

        case 16908762: 
            view1 = view3;
            break;

        case 16908806: 
        case 16908814: 
            if(view2 != null)
                return false;
            view2 = view3;
            break;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        int j1 = android.view.View.MeasureSpec.getMode(j);
        int k1 = android.view.View.MeasureSpec.getSize(j);
        int l1 = android.view.View.MeasureSpec.getMode(i);
        int i2 = 0;
        int i1 = getPaddingTop() + getPaddingBottom();
        int j2 = i1;
        if(view != null)
        {
            view.measure(i, 0);
            j2 = i1 + view.getMeasuredHeight();
            i2 = combineMeasuredStates(0, view.getMeasuredState());
        }
        i1 = 0;
        int k2 = 0;
        int l2 = i2;
        int i3 = j2;
        if(view1 != null)
        {
            view1.measure(i, 0);
            i1 = resolveMinimumHeight(view1);
            k2 = view1.getMeasuredHeight() - i1;
            i3 = j2 + i1;
            l2 = combineMeasuredStates(i2, view1.getMeasuredState());
        }
        int j3 = 0;
        j2 = l2;
        i2 = i3;
        if(view2 != null)
        {
            View view4;
            int k3;
            if(j1 == 0)
                i2 = 0;
            else
                i2 = android.view.View.MeasureSpec.makeMeasureSpec(Math.max(0, k1 - i3), j1);
            view2.measure(i, i2);
            j3 = view2.getMeasuredHeight();
            i2 = i3 + j3;
            j2 = combineMeasuredStates(l2, view2.getMeasuredState());
        }
        k1 -= i2;
        i3 = j2;
        k3 = k1;
        l2 = i2;
        if(view1 != null)
        {
            k2 = Math.min(k1, k2);
            l2 = i1;
            i3 = k1;
            if(k2 > 0)
            {
                i3 = k1 - k2;
                l2 = i1 + k2;
            }
            view1.measure(i, android.view.View.MeasureSpec.makeMeasureSpec(l2, 0x40000000));
            l2 = (i2 - i1) + view1.getMeasuredHeight();
            i1 = combineMeasuredStates(j2, view1.getMeasuredState());
            k3 = i3;
            i3 = i1;
        }
        i2 = i3;
        i1 = l2;
        if(view2 != null)
        {
            i2 = i3;
            i1 = l2;
            if(k3 > 0)
            {
                view2.measure(i, android.view.View.MeasureSpec.makeMeasureSpec(j3 + k3, j1));
                i1 = (l2 - j3) + view2.getMeasuredHeight();
                i2 = combineMeasuredStates(i3, view2.getMeasuredState());
            }
        }
        i3 = 0;
        for(j2 = 0; j2 < k;)
        {
            view4 = getChildAt(j2);
            l2 = i3;
            if(view4.getVisibility() != 8)
                l2 = Math.max(i3, view4.getMeasuredWidth());
            j2++;
            i3 = l2;
        }

        setMeasuredDimension(resolveSizeAndState(i3 + (getPaddingLeft() + getPaddingRight()), i, i2), resolveSizeAndState(i1, j, 0));
        if(l1 != 0x40000000)
            forceUniformWidth(k, j);
        return true;
        if(true) goto _L3; else goto _L6
_L6:
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        i1 = mPaddingLeft;
        j1 = k - i;
        k1 = mPaddingRight;
        l1 = mPaddingRight;
        i = getMeasuredHeight();
        i2 = getChildCount();
        j2 = getGravity();
        j2 & 0x70;
        JVM INSTR lookupswitch 2: default 72
    //                   16: 294
    //                   80: 279;
           goto _L1 _L2 _L3
_L1:
        i = mPaddingTop;
_L11:
        Object obj;
        int k2;
        obj = getDividerDrawable();
        View view;
        int l2;
        int i3;
        if(obj == null)
            k = 0;
        else
            k = ((Drawable) (obj)).getIntrinsicHeight();
_L10:
        for(l = 0; l >= i2;)
            break MISSING_BLOCK_LABEL_371;

        view = getChildAt(l);
        j = i;
        if(view == null) goto _L5; else goto _L4
_L4:
        j = i;
        if(view.getVisibility() == 8) goto _L5; else goto _L6
_L6:
        k2 = view.getMeasuredWidth();
        l2 = view.getMeasuredHeight();
        obj = (android.widget.LinearLayout.LayoutParams)view.getLayoutParams();
        i3 = ((android.widget.LinearLayout.LayoutParams) (obj)).gravity;
        j = i3;
        if(i3 < 0)
            j = j2 & 0x800007;
        Gravity.getAbsoluteGravity(j, getLayoutDirection()) & 7;
        JVM INSTR lookupswitch 2: default 212
    //                   1: 321
    //                   5: 353;
           goto _L7 _L8 _L9
_L7:
        j = i1 + ((android.widget.LinearLayout.LayoutParams) (obj)).leftMargin;
_L12:
        i3 = i;
        if(hasDividerBeforeChildAt(l))
            i3 = i + k;
        i = i3 + ((android.widget.LinearLayout.LayoutParams) (obj)).topMargin;
        setChildFrame(view, j, i, k2, l2);
        j = i + (((android.widget.LinearLayout.LayoutParams) (obj)).bottomMargin + l2);
_L5:
        l++;
        i = j;
          goto _L10
_L3:
        i = (mPaddingTop + l) - j - i;
          goto _L11
_L2:
        i = mPaddingTop + (l - j - i) / 2;
          goto _L11
_L8:
        j = ((j1 - i1 - l1 - k2) / 2 + i1 + ((android.widget.LinearLayout.LayoutParams) (obj)).leftMargin) - ((android.widget.LinearLayout.LayoutParams) (obj)).rightMargin;
          goto _L12
_L9:
        j = j1 - k1 - k2 - ((android.widget.LinearLayout.LayoutParams) (obj)).rightMargin;
          goto _L12
          goto _L10
    }

    protected void onMeasure(int i, int j)
    {
        if(!tryOnMeasure(i, j))
            super.onMeasure(i, j);
    }
}

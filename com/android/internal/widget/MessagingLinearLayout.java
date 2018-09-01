// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package com.android.internal.widget:
//            ImageFloatingTextView

public class MessagingLinearLayout extends ViewGroup
{
    public static class LayoutParams extends android.view.ViewGroup.MarginLayoutParams
    {

        boolean hide;

        public LayoutParams(int i, int j)
        {
            super(i, j);
            hide = false;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            hide = false;
        }
    }


    public MessagingLinearLayout(Context context, AttributeSet attributeset)
    {
        int i;
        int j;
        super(context, attributeset);
        mLastMeasuredWidth = -1;
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.MessagingLinearLayout, 0, 0);
        i = context.getIndexCount();
        j = 0;
_L6:
        if(j >= i) goto _L2; else goto _L1
_L1:
        context.getIndex(j);
        JVM INSTR tableswitch 0 0: default 60
    //                   0 66;
           goto _L3 _L4
_L3:
        j++;
        continue; /* Loop/switch isn't completed */
_L4:
        mSpacing = context.getDimensionPixelSize(j, 0);
        if(true) goto _L3; else goto _L2
_L2:
        context.recycle();
        return;
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected boolean drawChild(Canvas canvas, View view, long l)
    {
        if(((LayoutParams)view.getLayoutParams()).hide)
            return true;
        else
            return super.drawChild(canvas, view, l);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return generateDefaultLayoutParams();
    }

    protected LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-1, -2);
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return generateLayoutParams(layoutparams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(mContext, attributeset);
    }

    protected LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        LayoutParams layoutparams1 = new LayoutParams(layoutparams.width, layoutparams.height);
        if(layoutparams instanceof android.view.ViewGroup.MarginLayoutParams)
            layoutparams1.copyMarginsFrom((android.view.ViewGroup.MarginLayoutParams)layoutparams);
        return layoutparams1;
    }

    public int getContractedChildId()
    {
        return mContractedChildId;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1 = mPaddingLeft;
        int j1 = mPaddingRight;
        int k1 = getLayoutDirection();
        int l1 = getChildCount();
        j = mPaddingTop;
        boolean flag1 = true;
        l = 0;
        while(l < l1) 
        {
            View view = getChildAt(l);
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            int i2 = j;
            int j2 = ((flag1) ? 1 : 0);
            if(view.getVisibility() != 8)
                if(layoutparams.hide)
                {
                    j2 = ((flag1) ? 1 : 0);
                    i2 = j;
                } else
                {
                    int k2 = view.getMeasuredWidth();
                    int l2 = view.getMeasuredHeight();
                    if(k1 == 1)
                        i2 = k - i - j1 - k2 - layoutparams.rightMargin;
                    else
                        i2 = i1 + layoutparams.leftMargin;
                    j2 = j;
                    if(!flag1)
                        j2 = j + mSpacing;
                    j = j2 + layoutparams.topMargin;
                    view.layout(i2, j, i2 + k2, j + l2);
                    i2 = j + (layoutparams.bottomMargin + l2);
                    j2 = 0;
                }
            l++;
            j = i2;
            flag1 = j2;
        }
        mLastMeasuredWidth = -1;
    }

    protected void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getSize(j);
        android.view.View.MeasureSpec.getMode(j);
        JVM INSTR tableswitch 0 0: default 28
    //                   0 96;
           goto _L1 _L2
_L1:
        int l;
        boolean flag;
        int i1;
        l = android.view.View.MeasureSpec.getSize(i);
        int j1;
        if(mLastMeasuredWidth == -1 || getMeasuredHeight() != k)
            flag = true;
        else
        if(mLastMeasuredWidth != l)
            flag = true;
        else
            flag = false;
        i1 = getChildCount();
        if(!flag) goto _L4; else goto _L3
_L3:
        int k1;
        int i2;
        int k2;
        for(j1 = 0; j1 < i1; j1++)
            ((LayoutParams)getChildAt(j1).getLayoutParams()).hide = true;

        k1 = mPaddingTop + mPaddingBottom;
        i2 = 1;
        k2 = i1 - 1;
          goto _L5
_L2:
        k = 0x7fffffff;
        continue; /* Loop/switch isn't completed */
_L5:
        if(k2 < 0 || k1 >= k) goto _L4; else goto _L6
_L6:
        if(getChildAt(k2).getVisibility() != 8) goto _L8; else goto _L7
_L7:
        k2--;
          goto _L5
_L8:
        View view = getChildAt(k2);
        LayoutParams layoutparams = (LayoutParams)getChildAt(k2).getLayoutParams();
        ImageFloatingTextView imagefloatingtextview = null;
        if(view instanceof ImageFloatingTextView)
        {
            imagefloatingtextview = (ImageFloatingTextView)view;
            int i3;
            int k3;
            if(mIndentLines == 2)
                i3 = 3;
            else
                i3 = mIndentLines;
            imagefloatingtextview.setNumIndentLines(i3);
        }
        if(i2 != 0)
            i2 = 0;
        else
            i2 = mSpacing;
        measureChildWithMargins(view, i, 0, j, (k1 - mPaddingTop - mPaddingBottom) + i2);
        k3 = view.getMeasuredHeight();
        i3 = Math.max(k1, k1 + k3 + layoutparams.topMargin + layoutparams.bottomMargin + i2);
        i2 = 0;
        k1 = 0;
        if(imagefloatingtextview != null)
            if(k3 < imagefloatingtextview.getLayoutHeight() + imagefloatingtextview.getPaddingTop() + imagefloatingtextview.getPaddingBottom())
                k1 = 1;
            else
                k1 = 0;
        if(i3 > k || !(k1 ^ true)) goto _L4; else goto _L9
_L9:
        k1 = i3;
        layoutparams.hide = false;
          goto _L7
_L4:
        int l3 = mPaddingLeft + mPaddingRight;
        int l1 = mIndentLines;
        int j2 = mPaddingTop + mPaddingBottom;
        boolean flag1 = true;
        int j3 = 0;
        while(j3 < i1) 
        {
            View view1 = getChildAt(j3);
            LayoutParams layoutparams1 = (LayoutParams)view1.getLayoutParams();
            int i4 = ((flag1) ? 1 : 0);
            int l2 = l1;
            int j4 = l3;
            int k4 = j2;
            if(view1.getVisibility() != 8)
                if(layoutparams1.hide)
                {
                    k4 = j2;
                    j4 = l3;
                    l2 = l1;
                    i4 = ((flag1) ? 1 : 0);
                } else
                {
                    l2 = l1;
                    if(view1 instanceof ImageFloatingTextView)
                    {
                        ImageFloatingTextView imagefloatingtextview1 = (ImageFloatingTextView)view1;
                        l2 = l1;
                        if(l1 == 2)
                        {
                            l2 = l1;
                            if(imagefloatingtextview1.getLineCount() > 2)
                                l2 = 3;
                        }
                        if(imagefloatingtextview1.setNumIndentLines(Math.max(0, l2)) || flag ^ true)
                            view1.measure(getChildMeasureSpec(i, mPaddingLeft + mPaddingRight + layoutparams1.leftMargin + layoutparams1.rightMargin, layoutparams1.width), getChildMeasureSpec(j, k - view1.getMeasuredHeight(), layoutparams1.height));
                        l2 -= imagefloatingtextview1.getLineCount();
                    }
                    j4 = Math.max(l3, view1.getMeasuredWidth() + layoutparams1.leftMargin + layoutparams1.rightMargin + mPaddingLeft + mPaddingRight);
                    i4 = view1.getMeasuredHeight();
                    l3 = layoutparams1.topMargin;
                    k4 = layoutparams1.bottomMargin;
                    if(flag1)
                        l1 = 0;
                    else
                        l1 = mSpacing;
                    k4 = Math.max(j2, l1 + (k4 + (i4 + j2 + l3)));
                    i4 = 0;
                }
            j3++;
            flag1 = i4;
            l1 = l2;
            l3 = j4;
            j2 = k4;
        }
        setMeasuredDimension(resolveSize(Math.max(getSuggestedMinimumWidth(), l3), i), resolveSize(Math.max(getSuggestedMinimumHeight(), j2), j));
        mLastMeasuredWidth = l;
        return;
        if(true) goto _L1; else goto _L10
_L10:
    }

    public void setContractedChildId(int i)
    {
        mContractedChildId = i;
    }

    public void setNumIndentLines(int i)
    {
        mIndentLines = i;
    }

    private static final int NOT_MEASURED_BEFORE = -1;
    private int mContractedChildId;
    private int mIndentLines;
    private int mLastMeasuredWidth;
    private int mMaxHeight;
    private int mSpacing;
}

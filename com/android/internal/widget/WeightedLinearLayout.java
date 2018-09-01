// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

public class WeightedLinearLayout extends LinearLayout
{

    public WeightedLinearLayout(Context context)
    {
        super(context);
    }

    public WeightedLinearLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.WeightedLinearLayout);
        mMajorWeightMin = context.getFloat(1, 0.0F);
        mMinorWeightMin = context.getFloat(3, 0.0F);
        mMajorWeightMax = context.getFloat(0, 0.0F);
        mMinorWeightMax = context.getFloat(2, 0.0F);
        context.recycle();
    }

    protected void onMeasure(int i, int j)
    {
        int k;
        boolean flag;
        int i1;
        boolean flag1;
        int j1;
        float f1;
        DisplayMetrics displaymetrics = getContext().getResources().getDisplayMetrics();
        k = displaymetrics.widthPixels;
        int l;
        float f;
        if(k < displaymetrics.heightPixels)
            flag = true;
        else
            flag = false;
        l = android.view.View.MeasureSpec.getMode(i);
        super.onMeasure(i, j);
        i1 = getMeasuredWidth();
        flag1 = false;
        j1 = android.view.View.MeasureSpec.makeMeasureSpec(i1, 0x40000000);
        if(flag)
            f = mMinorWeightMin;
        else
            f = mMajorWeightMin;
        if(flag)
            f1 = mMinorWeightMax;
        else
            f1 = mMajorWeightMax;
        flag = flag1;
        i = j1;
        if(l != 0x80000000) goto _L2; else goto _L1
_L1:
        i = (int)((float)k * f);
        k = (int)((float)k * f);
        if(f <= 0.0F || i1 >= i) goto _L4; else goto _L3
_L3:
        i = android.view.View.MeasureSpec.makeMeasureSpec(i, 0x40000000);
        flag = true;
_L2:
        if(flag)
            super.onMeasure(i, j);
        return;
_L4:
        flag = flag1;
        i = j1;
        if(f1 > 0.0F)
        {
            flag = flag1;
            i = j1;
            if(i1 > k)
            {
                i = android.view.View.MeasureSpec.makeMeasureSpec(k, 0x40000000);
                flag = true;
            }
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    private float mMajorWeightMax;
    private float mMajorWeightMin;
    private float mMinorWeightMax;
    private float mMinorWeightMin;
}

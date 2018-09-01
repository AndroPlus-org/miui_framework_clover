// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Comparator;

public class NotificationActionListLayout extends LinearLayout
{

    public NotificationActionListLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mTotalWidth = 0;
        mMeasureOrderTextViews = new ArrayList();
        mMeasureOrderOther = new ArrayList();
    }

    private void clearMeasureOrder()
    {
        mMeasureOrderOther.clear();
        mMeasureOrderTextViews.clear();
    }

    static int lambda$_2D_com_android_internal_widget_NotificationActionListLayout_10433(Pair pair, Pair pair1)
    {
        return ((Integer)pair.first).compareTo((Integer)pair1.first);
    }

    private void rebuildMeasureOrder(int i, int j)
    {
        clearMeasureOrder();
        mMeasureOrderTextViews.ensureCapacity(i);
        mMeasureOrderOther.ensureCapacity(j);
        j = getChildCount();
        i = 0;
        while(i < j) 
        {
            View view = getChildAt(i);
            if((view instanceof TextView) && ((TextView)view).getText().length() > 0)
                mMeasureOrderTextViews.add(Pair.create(Integer.valueOf(((TextView)view).getText().length()), (TextView)view));
            else
                mMeasureOrderOther.add(view);
            i++;
        }
        mMeasureOrderTextViews.sort(MEASURE_ORDER_COMPARATOR);
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        mDefaultPaddingEnd = getPaddingEnd();
        mDefaultBackground = getBackground();
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1;
        int j1;
        int k1;
        if(mMeasureLinearly)
        {
            super.onLayout(flag, i, j, k, l);
            return;
        }
        flag = isLayoutRtl();
        i1 = mPaddingTop;
        j1 = mPaddingBottom;
        k1 = getChildCount();
        Gravity.getAbsoluteGravity(0x800003, getLayoutDirection());
        JVM INSTR tableswitch 5 5: default 68
    //                   5 231;
           goto _L1 _L2
_L1:
        i = mPaddingLeft;
_L4:
        int l1 = 0;
        k = 1;
        if(flag)
        {
            l1 = k1 - 1;
            k = -1;
        }
        for(int i2 = 0; i2 < k1;)
        {
            View view = getChildAt(l1 + k * i2);
            int j2 = i;
            if(view.getVisibility() != 8)
            {
                j2 = view.getMeasuredWidth();
                int k2 = view.getMeasuredHeight();
                android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)view.getLayoutParams();
                int l2 = ((l - j - i1 - j1 - k2) / 2 + i1 + marginlayoutparams.topMargin) - marginlayoutparams.bottomMargin;
                i += marginlayoutparams.leftMargin;
                view.layout(i, l2, i + j2, l2 + k2);
                j2 = i + (marginlayoutparams.rightMargin + j2);
            }
            i2++;
            i = j2;
        }

        break; /* Loop/switch isn't completed */
_L2:
        i = (mPaddingLeft + k) - i - mTotalWidth;
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void onMeasure(int i, int j)
    {
        boolean flag;
        int j1;
        int k1;
        View view;
        int l1;
        int j3;
        if(mMeasureLinearly)
        {
            super.onMeasure(i, j);
            return;
        }
        int k = getChildCount();
        int l = 0;
        j1 = 0;
        k1 = 0;
        view = null;
        l1 = 0;
        while(l1 < k) 
        {
            View view1 = getChildAt(l1);
            int i2;
            if(view1 instanceof TextView)
                l++;
            else
                j1++;
            i2 = k1;
            if(view1.getVisibility() != 8)
            {
                i2 = k1 + 1;
                view = view1;
            }
            l1++;
            k1 = i2;
        }
        boolean flag1 = false;
        if(l != mMeasureOrderTextViews.size() || j1 != mMeasureOrderOther.size())
            flag1 = true;
        boolean flag2 = flag1;
        if(!flag1)
        {
            int i3 = mMeasureOrderTextViews.size();
            int j2 = 0;
            do
            {
                flag2 = flag1;
                if(j2 >= i3)
                    break;
                Pair pair = (Pair)mMeasureOrderTextViews.get(j2);
                if(((Integer)pair.first).intValue() != ((TextView)pair.second).getText().length())
                    flag1 = true;
                j2++;
            } while(true);
        }
        if(k1 > 1 && flag2)
            rebuildMeasureOrder(l, j1);
        int k2;
        int k3;
        if(android.view.View.MeasureSpec.getMode(i) != 0)
            flag = true;
        else
            flag = false;
        j3 = android.view.View.MeasureSpec.getSize(i) - mPaddingLeft - mPaddingRight;
        k3 = mMeasureOrderOther.size();
        flag1 = false;
        k2 = 0;
        j1 = 0;
        while(j1 < k && k1 > 1) 
        {
            View view2;
            if(j1 < k3)
                view2 = (View)mMeasureOrderOther.get(j1);
            else
                view2 = (View)((Pair)mMeasureOrderTextViews.get(j1 - k3)).second;
            if(view2.getVisibility() != 8)
            {
                android.view.ViewGroup.MarginLayoutParams marginlayoutparams1 = (android.view.ViewGroup.MarginLayoutParams)view2.getLayoutParams();
                int l2 = ((flag1) ? 1 : 0);
                if(flag)
                    l2 = j3 - (j3 - flag1) / (k1 - k2);
                measureChildWithMargins(view2, i, l2, j, 0);
                flag1 += view2.getMeasuredWidth() + marginlayoutparams1.rightMargin + marginlayoutparams1.leftMargin;
                k2++;
            }
            j1++;
        }
        j1 = ((flag1) ? 1 : 0);
        if(view == null) goto _L2; else goto _L1
_L1:
        if(!flag || flag1 >= j3) goto _L4; else goto _L3
_L3:
        android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)view.getLayoutParams();
        int i1 = ((flag1) ? 1 : 0);
        if(k1 > 1)
            i1 = flag1 - (view.getMeasuredWidth() + marginlayoutparams.rightMargin + marginlayoutparams.leftMargin);
        flag1 = marginlayoutparams.width;
        marginlayoutparams.width = -1;
        measureChildWithMargins(view, i, i1, j, 0);
        marginlayoutparams.width = ((flag1) ? 1 : 0);
        j1 = i1 + (view.getMeasuredWidth() + marginlayoutparams.rightMargin + marginlayoutparams.leftMargin);
_L2:
        mTotalWidth = mPaddingRight + j1 + mPaddingLeft;
        setMeasuredDimension(resolveSize(getSuggestedMinimumWidth(), i), resolveSize(getSuggestedMinimumHeight(), j));
        return;
_L4:
        j1 = ((flag1) ? 1 : 0);
        if(k1 != 1) goto _L2; else goto _L3
    }

    public void onViewAdded(View view)
    {
        super.onViewAdded(view);
        clearMeasureOrder();
    }

    public void onViewRemoved(View view)
    {
        super.onViewRemoved(view);
        clearMeasureOrder();
    }

    public void setEmphasizedMode(boolean flag)
    {
        mMeasureLinearly = flag;
        int i = getPaddingStart();
        int j = getPaddingTop();
        int k;
        Drawable drawable;
        if(flag)
            k = 0;
        else
            k = mDefaultPaddingEnd;
        setPaddingRelative(i, j, k, getPaddingBottom());
        if(flag)
            drawable = null;
        else
            drawable = mDefaultBackground;
        setBackground(drawable);
        requestLayout();
    }

    public static final Comparator MEASURE_ORDER_COMPARATOR;
    private Drawable mDefaultBackground;
    private int mDefaultPaddingEnd;
    private boolean mMeasureLinearly;
    private ArrayList mMeasureOrderOther;
    private ArrayList mMeasureOrderTextViews;
    private int mTotalWidth;

    static 
    {
        MEASURE_ORDER_COMPARATOR = _.Lambda.LaTFiUorkqfcqmu_zMQbCLeO77c.$INST$0;
    }
}

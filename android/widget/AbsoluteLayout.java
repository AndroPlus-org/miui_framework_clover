// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class AbsoluteLayout extends ViewGroup
{
    public static class LayoutParams extends android.view.ViewGroup.LayoutParams
    {

        public String debug(String s)
        {
            return (new StringBuilder()).append(s).append("Absolute.LayoutParams={width=").append(sizeToString(width)).append(", height=").append(sizeToString(height)).append(" x=").append(x).append(" y=").append(y).append("}").toString();
        }

        public int x;
        public int y;

        public LayoutParams(int i, int j, int k, int l)
        {
            super(i, j);
            x = k;
            y = l;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.AbsoluteLayout_Layout);
            x = context.getDimensionPixelOffset(0, 0);
            y = context.getDimensionPixelOffset(1, 0);
            context.recycle();
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
        }
    }


    public AbsoluteLayout(Context context)
    {
        this(context, null);
    }

    public AbsoluteLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public AbsoluteLayout(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public AbsoluteLayout(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof LayoutParams;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-2, -2, 0, 0);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return new LayoutParams(layoutparams);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        j = getChildCount();
        for(i = 0; i < j; i++)
        {
            View view = getChildAt(i);
            if(view.getVisibility() != 8)
            {
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                k = mPaddingLeft + layoutparams.x;
                l = mPaddingTop + layoutparams.y;
                view.layout(k, l, view.getMeasuredWidth() + k, view.getMeasuredHeight() + l);
            }
        }

    }

    protected void onMeasure(int i, int j)
    {
        int k = getChildCount();
        int l = 0;
        int i1 = 0;
        measureChildren(i, j);
        for(int j1 = 0; j1 < k;)
        {
            View view = getChildAt(j1);
            int l1 = l;
            int i2 = i1;
            if(view.getVisibility() != 8)
            {
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                i2 = layoutparams.x;
                int k2 = view.getMeasuredWidth();
                l1 = layoutparams.y;
                int l2 = view.getMeasuredHeight();
                i2 = Math.max(i1, i2 + k2);
                l1 = Math.max(l, l1 + l2);
            }
            j1++;
            l = l1;
            i1 = i2;
        }

        int j2 = mPaddingLeft;
        int k1 = mPaddingRight;
        l = Math.max(l + (mPaddingTop + mPaddingBottom), getSuggestedMinimumHeight());
        setMeasuredDimension(resolveSizeAndState(Math.max(i1 + (j2 + k1), getSuggestedMinimumWidth()), i, 0), resolveSizeAndState(l, j, 0));
    }

    public boolean shouldDelayChildPressedState()
    {
        return false;
    }
}

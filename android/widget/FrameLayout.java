// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.*;
import java.util.ArrayList;

public class FrameLayout extends ViewGroup
{
    public static class LayoutParams extends android.view.ViewGroup.MarginLayoutParams
    {

        public static final int UNSPECIFIED_GRAVITY = -1;
        public int gravity;

        public LayoutParams(int i, int j)
        {
            super(i, j);
            gravity = -1;
        }

        public LayoutParams(int i, int j, int k)
        {
            super(i, j);
            gravity = -1;
            gravity = k;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            gravity = -1;
            context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.FrameLayout_Layout);
            gravity = context.getInt(0, -1);
            context.recycle();
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            gravity = -1;
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
            gravity = -1;
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            super(layoutparams);
            gravity = -1;
            gravity = layoutparams.gravity;
        }
    }


    public FrameLayout(Context context)
    {
        super(context);
        mMeasureAllChildren = false;
        mForegroundPaddingLeft = 0;
        mForegroundPaddingTop = 0;
        mForegroundPaddingRight = 0;
        mForegroundPaddingBottom = 0;
        mMatchParentChildren = new ArrayList(1);
    }

    public FrameLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public FrameLayout(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public FrameLayout(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mMeasureAllChildren = false;
        mForegroundPaddingLeft = 0;
        mForegroundPaddingTop = 0;
        mForegroundPaddingRight = 0;
        mForegroundPaddingBottom = 0;
        mMatchParentChildren = new ArrayList(1);
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.FrameLayout, i, j);
        if(context.getBoolean(0, false))
            setMeasureAllChildren(true);
        context.recycle();
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

    private int getPaddingTopWithForeground()
    {
        int i;
        if(isForegroundInsidePadding())
            i = Math.max(mPaddingTop, mForegroundPaddingTop);
        else
            i = mPaddingTop + mForegroundPaddingTop;
        return i;
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof LayoutParams;
    }

    protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
    {
        super.encodeProperties(viewhierarchyencoder);
        viewhierarchyencoder.addProperty("measurement:measureAllChildren", mMeasureAllChildren);
        viewhierarchyencoder.addProperty("padding:foregroundPaddingLeft", mForegroundPaddingLeft);
        viewhierarchyencoder.addProperty("padding:foregroundPaddingTop", mForegroundPaddingTop);
        viewhierarchyencoder.addProperty("padding:foregroundPaddingRight", mForegroundPaddingRight);
        viewhierarchyencoder.addProperty("padding:foregroundPaddingBottom", mForegroundPaddingBottom);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return generateDefaultLayoutParams();
    }

    protected LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-1, -1);
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        if(sPreserveMarginParamsInLayoutParamConversion)
        {
            if(layoutparams instanceof LayoutParams)
                return new LayoutParams((LayoutParams)layoutparams);
            if(layoutparams instanceof android.view.ViewGroup.MarginLayoutParams)
                return new LayoutParams((android.view.ViewGroup.MarginLayoutParams)layoutparams);
        }
        return new LayoutParams(layoutparams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/FrameLayout.getName();
    }

    public boolean getConsiderGoneChildrenWhenMeasuring()
    {
        return getMeasureAllChildren();
    }

    public boolean getMeasureAllChildren()
    {
        return mMeasureAllChildren;
    }

    int getPaddingLeftWithForeground()
    {
        int i;
        if(isForegroundInsidePadding())
            i = Math.max(mPaddingLeft, mForegroundPaddingLeft);
        else
            i = mPaddingLeft + mForegroundPaddingLeft;
        return i;
    }

    int getPaddingRightWithForeground()
    {
        int i;
        if(isForegroundInsidePadding())
            i = Math.max(mPaddingRight, mForegroundPaddingRight);
        else
            i = mPaddingRight + mForegroundPaddingRight;
        return i;
    }

    void layoutChildren(int i, int j, int k, int l, boolean flag)
    {
        int i1;
        int j1;
        int k1;
        int l1;
        i1 = getChildCount();
        j1 = getPaddingLeftWithForeground();
        k1 = k - i - getPaddingRightWithForeground();
        l1 = getPaddingTopWithForeground();
        l = l - j - getPaddingBottomWithForeground();
        k = 0;
_L16:
        if(k >= i1) goto _L2; else goto _L1
_L1:
        View view = getChildAt(k);
        if(view.getVisibility() == 8) goto _L4; else goto _L3
_L3:
        LayoutParams layoutparams;
        int i2;
        int j2;
        layoutparams = (LayoutParams)view.getLayoutParams();
        i2 = view.getMeasuredWidth();
        j2 = view.getMeasuredHeight();
        j = layoutparams.gravity;
        i = j;
        if(j == -1)
            i = 0x800033;
        Gravity.getAbsoluteGravity(i, getLayoutDirection()) & 7;
        JVM INSTR lookupswitch 2: default 140
    //                   1: 218
    //                   5: 247;
           goto _L5 _L6 _L7
_L5:
        j = j1 + layoutparams.leftMargin;
_L12:
        i & 0x70;
        JVM INSTR lookupswitch 3: default 188
    //                   16: 279
    //                   48: 267
    //                   80: 308;
           goto _L8 _L9 _L10 _L11
_L8:
        i = l1 + layoutparams.topMargin;
_L14:
        view.layout(j, i, j + i2, i + j2);
_L4:
        k++;
        continue; /* Loop/switch isn't completed */
_L6:
        j = ((k1 - j1 - i2) / 2 + j1 + layoutparams.leftMargin) - layoutparams.rightMargin;
          goto _L12
_L7:
        if(flag) goto _L5; else goto _L13
_L13:
        j = k1 - i2 - layoutparams.rightMargin;
          goto _L12
_L10:
        i = l1 + layoutparams.topMargin;
          goto _L14
_L9:
        i = ((l - l1 - j2) / 2 + l1 + layoutparams.topMargin) - layoutparams.bottomMargin;
          goto _L14
_L11:
        i = l - j2 - layoutparams.bottomMargin;
          goto _L14
_L2:
        return;
        if(true) goto _L16; else goto _L15
_L15:
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        layoutChildren(i, j, k, l, false);
    }

    protected void onMeasure(int i, int j)
    {
        int k = getChildCount();
        int l;
        int j1;
        int k1;
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
        j1 = 0;
        k1 = 0;
        i2 = 0;
        for(int k2 = 0; k2 < k;)
        {
            int i3;
            int j3;
            int k3;
label0:
            {
                View view = getChildAt(k2);
                if(!mMeasureAllChildren)
                {
                    i3 = i2;
                    j3 = j1;
                    k3 = k1;
                    if(view.getVisibility() == 8)
                        break label0;
                }
                measureChildWithMargins(view, i, 0, j, 0);
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                k1 = Math.max(k1, view.getMeasuredWidth() + layoutparams.leftMargin + layoutparams.rightMargin);
                j1 = Math.max(j1, view.getMeasuredHeight() + layoutparams.topMargin + layoutparams.bottomMargin);
                i2 = combineMeasuredStates(i2, view.getMeasuredState());
                i3 = i2;
                j3 = j1;
                k3 = k1;
                if(!l)
                    break label0;
                if(layoutparams.width != -1)
                {
                    i3 = i2;
                    j3 = j1;
                    k3 = k1;
                    if(layoutparams.height != -1)
                        break label0;
                }
                mMatchParentChildren.add(view);
                k3 = k1;
                j3 = j1;
                i3 = i2;
            }
            k2++;
            i2 = i3;
            j1 = j3;
            k1 = k3;
        }

        int l2 = getPaddingLeftWithForeground();
        l = getPaddingRightWithForeground();
        j1 = Math.max(j1 + (getPaddingTopWithForeground() + getPaddingBottomWithForeground()), getSuggestedMinimumHeight());
        l2 = Math.max(k1 + (l2 + l), getSuggestedMinimumWidth());
        Drawable drawable = getForeground();
        k1 = j1;
        l = l2;
        if(drawable != null)
        {
            k1 = Math.max(j1, drawable.getMinimumHeight());
            l = Math.max(l2, drawable.getMinimumWidth());
        }
        setMeasuredDimension(resolveSizeAndState(l, i, i2), resolveSizeAndState(k1, j, i2 << 16));
        j1 = mMatchParentChildren.size();
        if(j1 > 1)
        {
            int i1 = 0;
            while(i1 < j1) 
            {
                View view1 = (View)mMatchParentChildren.get(i1);
                android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)view1.getLayoutParams();
                int l1;
                int j2;
                if(marginlayoutparams.width == -1)
                    j2 = android.view.View.MeasureSpec.makeMeasureSpec(Math.max(0, getMeasuredWidth() - getPaddingLeftWithForeground() - getPaddingRightWithForeground() - marginlayoutparams.leftMargin - marginlayoutparams.rightMargin), 0x40000000);
                else
                    j2 = getChildMeasureSpec(i, getPaddingLeftWithForeground() + getPaddingRightWithForeground() + marginlayoutparams.leftMargin + marginlayoutparams.rightMargin, marginlayoutparams.width);
                if(marginlayoutparams.height == -1)
                    l1 = android.view.View.MeasureSpec.makeMeasureSpec(Math.max(0, getMeasuredHeight() - getPaddingTopWithForeground() - getPaddingBottomWithForeground() - marginlayoutparams.topMargin - marginlayoutparams.bottomMargin), 0x40000000);
                else
                    l1 = getChildMeasureSpec(j, getPaddingTopWithForeground() + getPaddingBottomWithForeground() + marginlayoutparams.topMargin + marginlayoutparams.bottomMargin, marginlayoutparams.height);
                view1.measure(j2, l1);
                i1++;
            }
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
            requestLayout();
        }
    }

    public void setMeasureAllChildren(boolean flag)
    {
        mMeasureAllChildren = flag;
    }

    public boolean shouldDelayChildPressedState()
    {
        return false;
    }

    private static final int DEFAULT_CHILD_GRAVITY = 0x800033;
    private int mForegroundPaddingBottom;
    private int mForegroundPaddingLeft;
    private int mForegroundPaddingRight;
    private int mForegroundPaddingTop;
    private final ArrayList mMatchParentChildren;
    boolean mMeasureAllChildren;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.*;

public class LinearLayout extends ViewGroup
{
    public static class LayoutParams extends android.view.ViewGroup.MarginLayoutParams
    {

        public String debug(String s)
        {
            return (new StringBuilder()).append(s).append("LinearLayout.LayoutParams={width=").append(sizeToString(width)).append(", height=").append(sizeToString(height)).append(" weight=").append(weight).append("}").toString();
        }

        protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
        {
            super.encodeProperties(viewhierarchyencoder);
            viewhierarchyencoder.addProperty("layout:weight", weight);
            viewhierarchyencoder.addProperty("layout:gravity", gravity);
        }

        public int gravity;
        public float weight;

        public LayoutParams(int i, int j)
        {
            super(i, j);
            gravity = -1;
            weight = 0.0F;
        }

        public LayoutParams(int i, int j, float f)
        {
            super(i, j);
            gravity = -1;
            weight = f;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            gravity = -1;
            context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.LinearLayout_Layout);
            weight = context.getFloat(3, 0.0F);
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
            weight = layoutparams.weight;
            gravity = layoutparams.gravity;
        }
    }


    public LinearLayout(Context context)
    {
        this(context, null);
    }

    public LinearLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public LinearLayout(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public LinearLayout(Context context, AttributeSet attributeset, int i, int j)
    {
        boolean flag = true;
        super(context, attributeset, i, j);
        mBaselineAligned = true;
        mBaselineAlignedChildIndex = -1;
        mBaselineChildTop = 0;
        mGravity = 0x800033;
        mLayoutDirection = -1;
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.LinearLayout, i, j);
        i = attributeset.getInt(1, -1);
        if(i >= 0)
            setOrientation(i);
        i = attributeset.getInt(0, -1);
        if(i >= 0)
            setGravity(i);
        boolean flag1 = attributeset.getBoolean(2, true);
        if(!flag1)
            setBaselineAligned(flag1);
        mWeightSum = attributeset.getFloat(4, -1F);
        mBaselineAlignedChildIndex = attributeset.getInt(3, -1);
        mUseLargestChild = attributeset.getBoolean(6, false);
        mShowDividers = attributeset.getInt(7, 0);
        mDividerPadding = attributeset.getDimensionPixelSize(8, 0);
        setDividerDrawable(attributeset.getDrawable(5));
        if(context.getApplicationInfo().targetSdkVersion > 23)
            flag = false;
        mAllowInconsistentMeasurement = flag;
        attributeset.recycle();
    }

    private boolean allViewsAreGoneBefore(int i)
    {
        for(i--; i >= 0; i--)
        {
            View view = getVirtualChildAt(i);
            if(view != null && view.getVisibility() != 8)
                return false;
        }

        return true;
    }

    private void forceUniformHeight(int i, int j)
    {
        int k = android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0x40000000);
        for(int l = 0; l < i; l++)
        {
            View view = getVirtualChildAt(l);
            if(view == null || view.getVisibility() == 8)
                continue;
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            if(layoutparams.height == -1)
            {
                int i1 = layoutparams.width;
                layoutparams.width = view.getMeasuredWidth();
                measureChildWithMargins(view, j, 0, k, 0);
                layoutparams.width = i1;
            }
        }

    }

    private void forceUniformWidth(int i, int j)
    {
        int k = android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0x40000000);
        for(int l = 0; l < i; l++)
        {
            View view = getVirtualChildAt(l);
            if(view == null || view.getVisibility() == 8)
                continue;
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            if(layoutparams.width == -1)
            {
                int i1 = layoutparams.height;
                layoutparams.height = view.getMeasuredHeight();
                measureChildWithMargins(view, k, 0, j, 0);
                layoutparams.height = i1;
            }
        }

    }

    private View getLastNonGoneChild()
    {
        for(int i = getVirtualChildCount() - 1; i >= 0; i--)
        {
            View view = getVirtualChildAt(i);
            if(view != null && view.getVisibility() != 8)
                return view;
        }

        return null;
    }

    private boolean isShowingDividers()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mShowDividers != 0)
        {
            flag1 = flag;
            if(mDivider != null)
                flag1 = true;
        }
        return flag1;
    }

    private void setChildFrame(View view, int i, int j, int k, int l)
    {
        view.layout(i, j, i + k, j + l);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof LayoutParams;
    }

    void drawDividersHorizontal(Canvas canvas)
    {
        int i = getVirtualChildCount();
        boolean flag = isLayoutRtl();
        int j = 0;
        while(j < i) 
        {
            View view = getVirtualChildAt(j);
            if(view != null && view.getVisibility() != 8 && hasDividerBeforeChildAt(j))
            {
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                int l;
                if(flag)
                    l = view.getRight() + layoutparams.rightMargin;
                else
                    l = view.getLeft() - layoutparams.leftMargin - mDividerWidth;
                drawVerticalDivider(canvas, l);
            }
            j++;
        }
        if(hasDividerBeforeChildAt(i))
        {
            View view1 = getLastNonGoneChild();
            int k;
            if(view1 == null)
            {
                if(flag)
                    k = getPaddingLeft();
                else
                    k = getWidth() - getPaddingRight() - mDividerWidth;
            } else
            {
                LayoutParams layoutparams1 = (LayoutParams)view1.getLayoutParams();
                if(flag)
                    k = view1.getLeft() - layoutparams1.leftMargin - mDividerWidth;
                else
                    k = view1.getRight() + layoutparams1.rightMargin;
            }
            drawVerticalDivider(canvas, k);
        }
    }

    void drawDividersVertical(Canvas canvas)
    {
        int i = getVirtualChildCount();
        for(int j = 0; j < i; j++)
        {
            View view = getVirtualChildAt(j);
            if(view != null && view.getVisibility() != 8 && hasDividerBeforeChildAt(j))
            {
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                drawHorizontalDivider(canvas, view.getTop() - layoutparams.topMargin - mDividerHeight);
            }
        }

        if(hasDividerBeforeChildAt(i))
        {
            View view1 = getLastNonGoneChild();
            int k;
            if(view1 == null)
            {
                k = getHeight() - getPaddingBottom() - mDividerHeight;
            } else
            {
                LayoutParams layoutparams1 = (LayoutParams)view1.getLayoutParams();
                k = view1.getBottom() + layoutparams1.bottomMargin;
            }
            drawHorizontalDivider(canvas, k);
        }
    }

    void drawHorizontalDivider(Canvas canvas, int i)
    {
        mDivider.setBounds(getPaddingLeft() + mDividerPadding, i, getWidth() - getPaddingRight() - mDividerPadding, mDividerHeight + i);
        mDivider.draw(canvas);
    }

    void drawVerticalDivider(Canvas canvas, int i)
    {
        mDivider.setBounds(i, getPaddingTop() + mDividerPadding, mDividerWidth + i, getHeight() - getPaddingBottom() - mDividerPadding);
        mDivider.draw(canvas);
    }

    protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
    {
        super.encodeProperties(viewhierarchyencoder);
        viewhierarchyencoder.addProperty("layout:baselineAligned", mBaselineAligned);
        viewhierarchyencoder.addProperty("layout:baselineAlignedChildIndex", mBaselineAlignedChildIndex);
        viewhierarchyencoder.addProperty("measurement:baselineChildTop", mBaselineChildTop);
        viewhierarchyencoder.addProperty("measurement:orientation", mOrientation);
        viewhierarchyencoder.addProperty("measurement:gravity", mGravity);
        viewhierarchyencoder.addProperty("measurement:totalLength", mTotalLength);
        viewhierarchyencoder.addProperty("layout:totalLength", mTotalLength);
        viewhierarchyencoder.addProperty("layout:useLargestChild", mUseLargestChild);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return generateDefaultLayoutParams();
    }

    protected LayoutParams generateDefaultLayoutParams()
    {
        if(mOrientation == 0)
            return new LayoutParams(-2, -2);
        if(mOrientation == 1)
            return new LayoutParams(-1, -2);
        else
            return null;
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
        return new LayoutParams(getContext(), attributeset);
    }

    protected LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
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

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/LinearLayout.getName();
    }

    public int getBaseline()
    {
        View view;
        int i;
        int j;
        int k;
        if(mBaselineAlignedChildIndex < 0)
            return super.getBaseline();
        if(getChildCount() <= mBaselineAlignedChildIndex)
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        view = getChildAt(mBaselineAlignedChildIndex);
        i = view.getBaseline();
        if(i == -1)
            if(mBaselineAlignedChildIndex == 0)
                return -1;
            else
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        j = mBaselineChildTop;
        k = j;
        if(mOrientation != 1) goto _L2; else goto _L1
_L1:
        int l;
        l = mGravity & 0x70;
        k = j;
        if(l == 48) goto _L2; else goto _L3
_L3:
        l;
        JVM INSTR lookupswitch 2: default 136
    //                   16: 179
    //                   80: 155;
           goto _L4 _L5 _L6
_L4:
        k = j;
_L2:
        return ((LayoutParams)view.getLayoutParams()).topMargin + k + i;
_L6:
        k = mBottom - mTop - mPaddingBottom - mTotalLength;
        continue; /* Loop/switch isn't completed */
_L5:
        k = j + (mBottom - mTop - mPaddingTop - mPaddingBottom - mTotalLength) / 2;
        if(true) goto _L2; else goto _L7
_L7:
    }

    public int getBaselineAlignedChildIndex()
    {
        return mBaselineAlignedChildIndex;
    }

    int getChildrenSkipCount(View view, int i)
    {
        return 0;
    }

    public Drawable getDividerDrawable()
    {
        return mDivider;
    }

    public int getDividerPadding()
    {
        return mDividerPadding;
    }

    public int getDividerWidth()
    {
        return mDividerWidth;
    }

    public int getGravity()
    {
        return mGravity;
    }

    int getLocationOffset(View view)
    {
        return 0;
    }

    int getNextLocationOffset(View view)
    {
        return 0;
    }

    public int getOrientation()
    {
        return mOrientation;
    }

    public int getShowDividers()
    {
        return mShowDividers;
    }

    View getVirtualChildAt(int i)
    {
        return getChildAt(i);
    }

    int getVirtualChildCount()
    {
        return getChildCount();
    }

    public float getWeightSum()
    {
        return mWeightSum;
    }

    protected boolean hasDividerBeforeChildAt(int i)
    {
        boolean flag = true;
        boolean flag1 = true;
        boolean flag2 = true;
        if(i == getVirtualChildCount())
        {
            if((mShowDividers & 4) == 0)
                flag2 = false;
            return flag2;
        }
        if(allViewsAreGoneBefore(i))
        {
            if((mShowDividers & 1) != 0)
                flag2 = flag;
            else
                flag2 = false;
            return flag2;
        }
        if((mShowDividers & 2) != 0)
            flag2 = flag1;
        else
            flag2 = false;
        return flag2;
    }

    public boolean isBaselineAligned()
    {
        return mBaselineAligned;
    }

    public boolean isMeasureWithLargestChildEnabled()
    {
        return mUseLargestChild;
    }

    void layoutHorizontal(int i, int j, int k, int l)
    {
        boolean flag;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        boolean flag1;
        int ai[];
        int ai1[];
        flag = isLayoutRtl();
        i1 = mPaddingTop;
        j1 = l - j;
        k1 = mPaddingBottom;
        l1 = mPaddingBottom;
        i2 = getVirtualChildCount();
        j = mGravity;
        j2 = mGravity;
        flag1 = mBaselineAligned;
        ai = mMaxAscent;
        ai1 = mMaxDescent;
        Gravity.getAbsoluteGravity(j & 0x800007, getLayoutDirection());
        JVM INSTR lookupswitch 2: default 104
    //                   1: 200
    //                   5: 183;
           goto _L1 _L2 _L3
_L1:
        i = mPaddingLeft;
_L17:
        int k2;
        k2 = 0;
        l = 1;
        if(flag)
        {
            k2 = i2 - 1;
            l = -1;
        }
        j = 0;
        k = i;
_L8:
        if(j >= i2) goto _L5; else goto _L4
_L4:
        int l2;
        View view;
        l2 = k2 + l * j;
        view = getVirtualChildAt(l2);
        if(view != null) goto _L7; else goto _L6
_L6:
        int i3;
        i = k + measureNullChild(l2);
        i3 = j;
_L10:
        j = i3 + 1;
        k = i;
          goto _L8
_L3:
        i = (mPaddingLeft + k) - i - mTotalLength;
        continue; /* Loop/switch isn't completed */
_L2:
        i = mPaddingLeft + (k - i - mTotalLength) / 2;
        continue; /* Loop/switch isn't completed */
_L7:
        i = k;
        i3 = j;
        if(view.getVisibility() == 8) goto _L10; else goto _L9
_L9:
        int j3;
        int k3;
        LayoutParams layoutparams;
        j3 = view.getMeasuredWidth();
        k3 = view.getMeasuredHeight();
        i = -1;
        layoutparams = (LayoutParams)view.getLayoutParams();
        i3 = i;
        if(flag1)
        {
            i3 = i;
            if(layoutparams.height != -1)
                i3 = view.getBaseline();
        }
        int l3 = layoutparams.gravity;
        i = l3;
        if(l3 < 0)
            i = j2 & 0x70;
        i & 0x70;
        JVM INSTR lookupswitch 3: default 348
    //                   16: 465
    //                   48: 432
    //                   80: 497;
           goto _L11 _L12 _L13 _L14
_L14:
        break MISSING_BLOCK_LABEL_497;
_L11:
        i = i1;
_L15:
        i3 = k;
        if(hasDividerBeforeChildAt(l2))
            i3 = k + mDividerWidth;
        k = i3 + layoutparams.leftMargin;
        setChildFrame(view, k + getLocationOffset(view), i, j3, k3);
        i = k + (layoutparams.rightMargin + j3 + getNextLocationOffset(view));
        i3 = j + getChildrenSkipCount(view, l2);
          goto _L10
_L13:
        int i4 = i1 + layoutparams.topMargin;
        i = i4;
        if(i3 != -1)
            i = i4 + (ai[1] - i3);
          goto _L15
_L12:
        i = ((j1 - i1 - l1 - k3) / 2 + i1 + layoutparams.topMargin) - layoutparams.bottomMargin;
          goto _L15
        int j4 = j1 - k1 - k3 - layoutparams.bottomMargin;
        i = j4;
        if(i3 != -1)
        {
            i = view.getMeasuredHeight();
            i = j4 - (ai1[2] - (i - i3));
        }
          goto _L15
_L5:
        return;
        if(true) goto _L17; else goto _L16
_L16:
    }

    void layoutVertical(int i, int j, int k, int l)
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
        i2 = getVirtualChildCount();
        i = mGravity;
        j2 = mGravity;
        i & 0x70;
        JVM INSTR lookupswitch 2: default 72
    //                   16: 136
    //                   80: 118;
           goto _L1 _L2 _L3
_L1:
        i = mPaddingTop;
_L16:
        j = 0;
_L8:
        if(j >= i2) goto _L5; else goto _L4
_L4:
        View view = getVirtualChildAt(j);
        if(view != null) goto _L7; else goto _L6
_L6:
        k = i + measureNullChild(j);
        l = j;
_L10:
        j = l + 1;
        i = k;
          goto _L8
_L3:
        i = (mPaddingTop + l) - j - mTotalLength;
        continue; /* Loop/switch isn't completed */
_L2:
        i = mPaddingTop + (l - j - mTotalLength) / 2;
        continue; /* Loop/switch isn't completed */
_L7:
        k = i;
        l = j;
        if(view.getVisibility() == 8) goto _L10; else goto _L9
_L9:
        int k2;
        int l2;
        LayoutParams layoutparams;
        k2 = view.getMeasuredWidth();
        l2 = view.getMeasuredHeight();
        layoutparams = (LayoutParams)view.getLayoutParams();
        l = layoutparams.gravity;
        k = l;
        if(l < 0)
            k = j2 & 0x800007;
        Gravity.getAbsoluteGravity(k, getLayoutDirection()) & 7;
        JVM INSTR lookupswitch 2: default 256
    //                   1: 344
    //                   5: 376;
           goto _L11 _L12 _L13
_L13:
        break MISSING_BLOCK_LABEL_376;
_L11:
        k = i1 + layoutparams.leftMargin;
_L14:
        l = i;
        if(hasDividerBeforeChildAt(j))
            l = i + mDividerHeight;
        i = l + layoutparams.topMargin;
        setChildFrame(view, k, i + getLocationOffset(view), k2, l2);
        k = i + (layoutparams.bottomMargin + l2 + getNextLocationOffset(view));
        l = j + getChildrenSkipCount(view, j);
          goto _L10
_L12:
        k = ((j1 - i1 - l1 - k2) / 2 + i1 + layoutparams.leftMargin) - layoutparams.rightMargin;
          goto _L14
        k = j1 - k1 - k2 - layoutparams.rightMargin;
          goto _L14
_L5:
        return;
        if(true) goto _L16; else goto _L15
_L15:
    }

    void measureChildBeforeLayout(View view, int i, int j, int k, int l, int i1)
    {
        measureChildWithMargins(view, j, k, l, i1);
    }

    void measureHorizontal(int i, int j)
    {
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        float f;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        int ai[];
        int ai1[];
        boolean flag;
        boolean flag1;
        boolean flag2;
        int i3;
        int j3;
        mTotalLength = 0;
        k = 0;
        l = 0;
        i1 = 0;
        j1 = 0;
        k1 = 1;
        f = 0.0F;
        l1 = getVirtualChildCount();
        i2 = android.view.View.MeasureSpec.getMode(i);
        j2 = android.view.View.MeasureSpec.getMode(j);
        k2 = 0;
        l2 = 0;
        if(mMaxAscent == null || mMaxDescent == null)
        {
            mMaxAscent = new int[4];
            mMaxDescent = new int[4];
        }
        ai = mMaxAscent;
        ai1 = mMaxDescent;
        ai[3] = -1;
        ai[2] = -1;
        ai[1] = -1;
        ai[0] = -1;
        ai1[3] = -1;
        ai1[2] = -1;
        ai1[1] = -1;
        ai1[0] = -1;
        flag = mBaselineAligned;
        flag1 = mUseLargestChild;
        int k3;
        int k4;
        if(i2 == 0x40000000)
            flag2 = true;
        else
            flag2 = false;
        i3 = 0x80000000;
        j3 = 0;
        k3 = 0;
        k4 = 0;
        while(k4 < l1) 
        {
            View view1 = getVirtualChildAt(k4);
            int i5;
            int k5;
            if(view1 == null)
            {
                mTotalLength = mTotalLength + measureNullChild(k4);
                i5 = k3;
                k5 = i3;
            } else
            if(view1.getVisibility() == 8)
            {
                k4 += getChildrenSkipCount(view1, k4);
                k5 = i3;
                i5 = k3;
            } else
            {
                int i6 = k3 + 1;
                if(hasDividerBeforeChildAt(k4))
                    mTotalLength = mTotalLength + mDividerWidth;
                LayoutParams layoutparams1 = (LayoutParams)view1.getLayoutParams();
                f += layoutparams1.weight;
                if(layoutparams1.width == 0 && layoutparams1.weight > 0.0F)
                    k3 = 1;
                else
                    k3 = 0;
                if(i2 == 0x40000000 && k3 != 0)
                {
                    int k6;
                    int j7;
                    if(flag2)
                    {
                        mTotalLength = mTotalLength + (layoutparams1.leftMargin + layoutparams1.rightMargin);
                    } else
                    {
                        k3 = mTotalLength;
                        mTotalLength = Math.max(k3, layoutparams1.leftMargin + k3 + layoutparams1.rightMargin);
                    }
                    if(flag)
                    {
                        view1.measure(android.view.View.MeasureSpec.makeSafeMeasureSpec(android.view.View.MeasureSpec.getSize(i), 0), android.view.View.MeasureSpec.makeSafeMeasureSpec(android.view.View.MeasureSpec.getSize(j), 0));
                        k3 = l2;
                        k5 = i3;
                    } else
                    {
                        k3 = 1;
                        k5 = i3;
                    }
                } else
                {
                    if(k3 != 0)
                        layoutparams1.width = -2;
                    int l6;
                    if(f == 0.0F)
                        k5 = mTotalLength;
                    else
                        k5 = 0;
                    measureChildBeforeLayout(view1, k4, i, k5, j, 0);
                    l6 = view1.getMeasuredWidth();
                    i5 = j3;
                    if(k3 != 0)
                    {
                        layoutparams1.width = 0;
                        i5 = j3 + l6;
                    }
                    if(flag2)
                    {
                        mTotalLength = mTotalLength + (layoutparams1.leftMargin + l6 + layoutparams1.rightMargin + getNextLocationOffset(view1));
                    } else
                    {
                        j3 = mTotalLength;
                        mTotalLength = Math.max(j3, j3 + l6 + layoutparams1.leftMargin + layoutparams1.rightMargin + getNextLocationOffset(view1));
                    }
                    k5 = i3;
                    k3 = l2;
                    j3 = i5;
                    if(flag1)
                    {
                        k5 = Math.max(l6, i3);
                        k3 = l2;
                        j3 = i5;
                    }
                }
                i5 = 0;
                i3 = k2;
                l2 = i5;
                if(j2 != 0x40000000)
                {
                    i3 = k2;
                    l2 = i5;
                    if(layoutparams1.height == -1)
                    {
                        i3 = 1;
                        l2 = 1;
                    }
                }
                i5 = layoutparams1.topMargin + layoutparams1.bottomMargin;
                k2 = view1.getMeasuredHeight() + i5;
                k6 = combineMeasuredStates(l, view1.getMeasuredState());
                if(flag)
                {
                    j7 = view1.getBaseline();
                    if(j7 != -1)
                    {
                        if(layoutparams1.gravity < 0)
                            l = mGravity;
                        else
                            l = layoutparams1.gravity;
                        l = ((l & 0x70) >> 4 & -2) >> 1;
                        ai[l] = Math.max(ai[l], j7);
                        ai1[l] = Math.max(ai1[l], k2 - j7);
                    }
                }
                k = Math.max(k, k2);
                if(k1 != 0 && layoutparams1.height == -1)
                    k1 = 1;
                else
                    k1 = 0;
                if(layoutparams1.weight > 0.0F)
                {
                    if(l2 != 0)
                        k2 = i5;
                    j1 = Math.max(j1, k2);
                } else
                {
                    if(l2 == 0)
                        i5 = k2;
                    i1 = Math.max(i1, i5);
                }
                k4 += getChildrenSkipCount(view1, k4);
                l = k6;
                k2 = i3;
                i5 = i6;
                l2 = k3;
            }
            k4++;
            i3 = k5;
            k3 = i5;
        }
        if(k3 > 0 && hasDividerBeforeChildAt(l1))
            mTotalLength = mTotalLength + mDividerWidth;
          goto _L1
_L4:
        int l4 = Math.max(k, Math.max(ai[3], Math.max(ai[0], Math.max(ai[1], ai[2]))) + Math.max(ai1[3], Math.max(ai1[0], Math.max(ai1[1], ai1[2]))));
          goto _L2
_L1:
        if(ai[1] != -1 || ai[0] != -1 || ai[2] != -1) goto _L4; else goto _L3
_L3:
        l4 = k;
        if(ai[3] == -1) goto _L2; else goto _L4
_L2:
        int k7;
        if(flag1 && (i2 == 0x80000000 || i2 == 0))
        {
            mTotalLength = 0;
            k = 0;
            while(k < l1) 
            {
                View view2 = getVirtualChildAt(k);
                if(view2 == null)
                    mTotalLength = mTotalLength + measureNullChild(k);
                else
                if(view2.getVisibility() == 8)
                {
                    k += getChildrenSkipCount(view2, k);
                } else
                {
                    LayoutParams layoutparams2 = (LayoutParams)view2.getLayoutParams();
                    if(flag2)
                    {
                        mTotalLength = mTotalLength + (layoutparams2.leftMargin + i3 + layoutparams2.rightMargin + getNextLocationOffset(view2));
                    } else
                    {
                        int l3 = mTotalLength;
                        mTotalLength = Math.max(l3, l3 + i3 + layoutparams2.leftMargin + layoutparams2.rightMargin + getNextLocationOffset(view2));
                    }
                }
                k++;
            }
        }
        mTotalLength = mTotalLength + (mPaddingLeft + mPaddingRight);
        k7 = resolveSizeAndState(Math.max(mTotalLength, getSuggestedMinimumWidth()), i, 0);
        k = mTotalLength;
        if(mAllowInconsistentMeasurement)
            j3 = 0;
        j3 = ((k7 & 0xffffff) - k) + j3;
        if(l2 == 0 && (j3 == 0 || f <= 0.0F)) goto _L6; else goto _L5
_L5:
        if(mWeightSum > 0.0F)
            f = mWeightSum;
        ai[3] = -1;
        ai[2] = -1;
        ai[1] = -1;
        ai[0] = -1;
        ai1[3] = -1;
        ai1[2] = -1;
        ai1[1] = -1;
        ai1[0] = -1;
        j1 = -1;
        mTotalLength = 0;
        k = 0;
        l2 = j3;
        while(k < l1) 
        {
            View view3 = getVirtualChildAt(k);
            int j5 = k1;
            int l5 = i1;
            int j6 = l;
            int i4 = j1;
            int i7 = l2;
            float f1 = f;
            if(view3 != null)
                if(view3.getVisibility() == 8)
                {
                    f1 = f;
                    i7 = l2;
                    i4 = j1;
                    j6 = l;
                    l5 = i1;
                    j5 = k1;
                } else
                {
                    LayoutParams layoutparams = (LayoutParams)view3.getLayoutParams();
                    f1 = layoutparams.weight;
                    l4 = l;
                    j3 = l2;
                    float f2 = f;
                    if(f1 > 0.0F)
                    {
                        j3 = (int)(((float)l2 * f1) / f);
                        l4 = l2 - j3;
                        f2 = f - f1;
                        int l7;
                        if(mUseLargestChild && i2 != 0x40000000)
                            j3 = i3;
                        else
                        if(layoutparams.width != 0 || mAllowInconsistentMeasurement && i2 != 0x40000000)
                            j3 = view3.getMeasuredWidth() + j3;
                        view3.measure(android.view.View.MeasureSpec.makeMeasureSpec(Math.max(0, j3), 0x40000000), getChildMeasureSpec(j, mPaddingTop + mPaddingBottom + layoutparams.topMargin + layoutparams.bottomMargin, layoutparams.height));
                        l = combineMeasuredStates(l, view3.getMeasuredState() & 0xff000000);
                        j3 = l4;
                        l4 = l;
                    }
                    if(flag2)
                    {
                        mTotalLength = mTotalLength + (view3.getMeasuredWidth() + layoutparams.leftMargin + layoutparams.rightMargin + getNextLocationOffset(view3));
                    } else
                    {
                        l = mTotalLength;
                        mTotalLength = Math.max(l, view3.getMeasuredWidth() + l + layoutparams.leftMargin + layoutparams.rightMargin + getNextLocationOffset(view3));
                    }
                    if(j2 != 0x40000000)
                    {
                        if(layoutparams.height == -1)
                            l = 1;
                        else
                            l = 0;
                    } else
                    {
                        l = 0;
                    }
                    i4 = layoutparams.topMargin + layoutparams.bottomMargin;
                    l2 = view3.getMeasuredHeight() + i4;
                    j1 = Math.max(j1, l2);
                    if(l != 0)
                        l = i4;
                    else
                        l = l2;
                    l = Math.max(i1, l);
                    if(k1 != 0 && layoutparams.height == -1)
                        i1 = 1;
                    else
                        i1 = 0;
                    j5 = i1;
                    l5 = l;
                    j6 = l4;
                    i4 = j1;
                    i7 = j3;
                    f1 = f2;
                    if(flag)
                    {
                        l7 = view3.getBaseline();
                        j5 = i1;
                        l5 = l;
                        j6 = l4;
                        i4 = j1;
                        i7 = j3;
                        f1 = f2;
                        if(l7 != -1)
                        {
                            if(layoutparams.gravity < 0)
                                k1 = mGravity;
                            else
                                k1 = layoutparams.gravity;
                            k1 = ((k1 & 0x70) >> 4 & -2) >> 1;
                            ai[k1] = Math.max(ai[k1], l7);
                            ai1[k1] = Math.max(ai1[k1], l2 - l7);
                            j5 = i1;
                            l5 = l;
                            j6 = l4;
                            i4 = j1;
                            i7 = j3;
                            f1 = f2;
                        }
                    }
                }
            k++;
            k1 = j5;
            i1 = l5;
            l = j6;
            j1 = i4;
            l2 = i7;
            f = f1;
        }
        mTotalLength = mTotalLength + (mPaddingLeft + mPaddingRight);
          goto _L7
_L9:
        int j4;
        j3 = Math.max(j1, Math.max(ai[3], Math.max(ai[0], Math.max(ai[1], ai[2]))) + Math.max(ai1[3], Math.max(ai1[0], Math.max(ai1[1], ai1[2]))));
        k = l;
        l2 = i1;
        j4 = k1;
_L10:
        i3 = j3;
        if(j4 == 0)
        {
            i3 = j3;
            if(j2 != 0x40000000)
                i3 = l2;
        }
        setMeasuredDimension(0xff000000 & k | k7, resolveSizeAndState(Math.max(i3 + (mPaddingTop + mPaddingBottom), getSuggestedMinimumHeight()), j, k << 16));
        if(k2 != 0)
            forceUniformHeight(l1, i);
        return;
_L7:
        if(ai[1] != -1 || ai[0] != -1 || ai[2] != -1) goto _L9; else goto _L8
_L8:
        j4 = k1;
        l2 = i1;
        k = l;
        j3 = j1;
        if(ai[3] == -1) goto _L10; else goto _L9
_L6:
        j1 = Math.max(i1, j1);
        j4 = k1;
        l2 = j1;
        k = l;
        j3 = l4;
        if(!flag1) goto _L10; else goto _L11
_L11:
        j4 = k1;
        l2 = j1;
        k = l;
        j3 = l4;
        if(i2 == 0x40000000) goto _L10; else goto _L12
_L12:
        i1 = 0;
_L14:
        j4 = k1;
        l2 = j1;
        k = l;
        j3 = l4;
        if(i1 >= l1) goto _L10; else goto _L13
_L13:
        View view = getVirtualChildAt(i1);
        if(view != null && view.getVisibility() != 8 && ((LayoutParams)view.getLayoutParams()).weight > 0.0F)
            view.measure(android.view.View.MeasureSpec.makeMeasureSpec(i3, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 0x40000000));
        i1++;
          goto _L14
    }

    int measureNullChild(int i)
    {
        return 0;
    }

    void measureVertical(int i, int j)
    {
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        float f;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        boolean flag;
        int j3;
        int k3;
        int l3;
        int i4;
        mTotalLength = 0;
        k = 0;
        l = 0;
        i1 = 0;
        j1 = 0;
        k1 = 1;
        f = 0.0F;
        l1 = getVirtualChildCount();
        i2 = android.view.View.MeasureSpec.getMode(i);
        j2 = android.view.View.MeasureSpec.getMode(j);
        k2 = 0;
        l2 = 0;
        i3 = mBaselineAlignedChildIndex;
        flag = mUseLargestChild;
        j3 = 0x80000000;
        k3 = 0;
        l3 = 0;
        i4 = 0;
_L2:
        View view;
        int j4;
        int l4;
        if(i4 >= l1)
            break MISSING_BLOCK_LABEL_679;
        view = getVirtualChildAt(i4);
        if(view != null)
            break; /* Loop/switch isn't completed */
        mTotalLength = mTotalLength + measureNullChild(i4);
        j4 = l3;
        l4 = j3;
_L3:
        i4++;
        j3 = l4;
        l3 = j4;
        if(true) goto _L2; else goto _L1
_L1:
label0:
        {
            if(view.getVisibility() != 8)
                break label0;
            i4 += getChildrenSkipCount(view, i4);
            l4 = j3;
            j4 = l3;
        }
          goto _L3
        int j5;
        LayoutParams layoutparams1;
        j5 = l3 + 1;
        if(hasDividerBeforeChildAt(i4))
            mTotalLength = mTotalLength + mDividerHeight;
        layoutparams1 = (LayoutParams)view.getLayoutParams();
        f += layoutparams1.weight;
        if(layoutparams1.height == 0 && layoutparams1.weight > 0.0F)
            l3 = 1;
        else
            l3 = 0;
        if(j2 != 0x40000000 || l3 == 0) goto _L5; else goto _L4
_L4:
        l2 = mTotalLength;
        mTotalLength = Math.max(l2, layoutparams1.topMargin + l2 + layoutparams1.bottomMargin);
        l3 = 1;
        l4 = j3;
_L7:
        if(i3 >= 0 && i3 == i4 + 1)
            mBaselineChildTop = mTotalLength;
        if(i4 < i3 && layoutparams1.weight > 0.0F)
            throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
        break; /* Loop/switch isn't completed */
_L5:
        if(l3 != 0)
            layoutparams1.height = -2;
        int k5;
        if(f == 0.0F)
            l4 = mTotalLength;
        else
            l4 = 0;
        measureChildBeforeLayout(view, i4, i, 0, j, l4);
        k5 = view.getMeasuredHeight();
        j4 = k3;
        if(l3 != 0)
        {
            layoutparams1.height = 0;
            j4 = k3 + k5;
        }
        k3 = mTotalLength;
        mTotalLength = Math.max(k3, k3 + k5 + layoutparams1.topMargin + layoutparams1.bottomMargin + getNextLocationOffset(view));
        k3 = j4;
        l4 = j3;
        l3 = l2;
        if(flag)
        {
            l4 = Math.max(k5, j3);
            k3 = j4;
            l3 = l2;
        }
        if(true) goto _L7; else goto _L6
_L6:
        j4 = 0;
        j3 = k2;
        l2 = j4;
        if(i2 != 0x40000000)
        {
            j3 = k2;
            l2 = j4;
            if(layoutparams1.width == -1)
            {
                j3 = 1;
                l2 = 1;
            }
        }
        k2 = layoutparams1.leftMargin + layoutparams1.rightMargin;
        j4 = view.getMeasuredWidth() + k2;
        int l5 = Math.max(k, j4);
        l = combineMeasuredStates(l, view.getMeasuredState());
        if(k1 != 0 && layoutparams1.width == -1)
            k = 1;
        else
            k = 0;
        if(layoutparams1.weight > 0.0F)
        {
            if(l2 == 0)
                k2 = j4;
            j1 = Math.max(j1, k2);
        } else
        {
            if(l2 == 0)
                k2 = j4;
            i1 = Math.max(i1, k2);
        }
        i4 += getChildrenSkipCount(view, i4);
        k1 = k;
        k2 = j3;
        k = l5;
        j4 = j5;
        l2 = l3;
          goto _L3
        if(l3 > 0 && hasDividerBeforeChildAt(l1))
            mTotalLength = mTotalLength + mDividerHeight;
        if(flag && (j2 == 0x80000000 || j2 == 0))
        {
            mTotalLength = 0;
            i4 = 0;
            while(i4 < l1) 
            {
                View view2 = getVirtualChildAt(i4);
                if(view2 == null)
                    mTotalLength = mTotalLength + measureNullChild(i4);
                else
                if(view2.getVisibility() == 8)
                {
                    i4 += getChildrenSkipCount(view2, i4);
                } else
                {
                    LayoutParams layoutparams = (LayoutParams)view2.getLayoutParams();
                    l3 = mTotalLength;
                    mTotalLength = Math.max(l3, l3 + j3 + layoutparams.topMargin + layoutparams.bottomMargin + getNextLocationOffset(view2));
                }
                i4++;
            }
        }
        mTotalLength = mTotalLength + (mPaddingTop + mPaddingBottom);
        j5 = resolveSizeAndState(Math.max(mTotalLength, getSuggestedMinimumHeight()), j, 0);
        i4 = mTotalLength;
        if(mAllowInconsistentMeasurement)
            k3 = 0;
        i4 = ((j5 & 0xffffff) - i4) + k3;
        if(l2 == 0 && (i4 == 0 || f <= 0.0F)) goto _L9; else goto _L8
_L8:
        if(mWeightSum > 0.0F)
            f = mWeightSum;
        mTotalLength = 0;
        l2 = 0;
        while(l2 < l1) 
        {
            View view1 = getVirtualChildAt(l2);
            l3 = k1;
            int i5 = i1;
            j1 = l;
            int k4 = k;
            k3 = i4;
            float f1 = f;
            if(view1 != null)
                if(view1.getVisibility() == 8)
                {
                    f1 = f;
                    k3 = i4;
                    k4 = k;
                    j1 = l;
                    i5 = i1;
                    l3 = k1;
                } else
                {
                    LayoutParams layoutparams2 = (LayoutParams)view1.getLayoutParams();
                    float f2 = layoutparams2.weight;
                    j1 = l;
                    k3 = i4;
                    f1 = f;
                    if(f2 > 0.0F)
                    {
                        k3 = (int)(((float)i4 * f2) / f);
                        j1 = i4 - k3;
                        f1 = f - f2;
                        if(mUseLargestChild && j2 != 0x40000000)
                            k3 = j3;
                        else
                        if(layoutparams2.height != 0 || mAllowInconsistentMeasurement && j2 != 0x40000000)
                            k3 = view1.getMeasuredHeight() + k3;
                        k3 = android.view.View.MeasureSpec.makeMeasureSpec(Math.max(0, k3), 0x40000000);
                        view1.measure(getChildMeasureSpec(i, mPaddingLeft + mPaddingRight + layoutparams2.leftMargin + layoutparams2.rightMargin, layoutparams2.width), k3);
                        l = combineMeasuredStates(l, view1.getMeasuredState() & 0xffffff00);
                        k3 = j1;
                        j1 = l;
                    }
                    i4 = layoutparams2.leftMargin + layoutparams2.rightMargin;
                    l = view1.getMeasuredWidth() + i4;
                    k4 = Math.max(k, l);
                    if(i2 != 0x40000000)
                    {
                        if(layoutparams2.width == -1)
                            k = 1;
                        else
                            k = 0;
                    } else
                    {
                        k = 0;
                    }
                    if(k != 0)
                        k = i4;
                    else
                        k = l;
                    i5 = Math.max(i1, k);
                    if(k1 != 0 && layoutparams2.width == -1)
                        k = 1;
                    else
                        k = 0;
                    i1 = mTotalLength;
                    mTotalLength = Math.max(i1, view1.getMeasuredHeight() + i1 + layoutparams2.topMargin + layoutparams2.bottomMargin + getNextLocationOffset(view1));
                    l3 = k;
                }
            l2++;
            k1 = l3;
            i1 = i5;
            l = j1;
            k = k4;
            i4 = k3;
            f = f1;
        }
        mTotalLength = mTotalLength + (mPaddingTop + mPaddingBottom);
        k3 = k;
        j1 = l;
        i4 = k1;
_L11:
        j3 = k3;
        if(i4 == 0)
        {
            j3 = k3;
            if(i2 != 0x40000000)
                j3 = i1;
        }
        setMeasuredDimension(resolveSizeAndState(Math.max(j3 + (mPaddingLeft + mPaddingRight), getSuggestedMinimumWidth()), i, j1), j5);
        if(k2 != 0)
            forceUniformWidth(l1, j);
        return;
_L9:
        l3 = Math.max(i1, j1);
        i4 = k1;
        i1 = l3;
        j1 = l;
        k3 = k;
        if(!flag) goto _L11; else goto _L10
_L10:
        i4 = k1;
        i1 = l3;
        j1 = l;
        k3 = k;
        if(j2 == 0x40000000) goto _L11; else goto _L12
_L12:
        l2 = 0;
_L14:
        i4 = k1;
        i1 = l3;
        j1 = l;
        k3 = k;
        if(l2 >= l1) goto _L11; else goto _L13
_L13:
        View view3 = getVirtualChildAt(l2);
        if(view3 != null && view3.getVisibility() != 8 && ((LayoutParams)view3.getLayoutParams()).weight > 0.0F)
            view3.measure(android.view.View.MeasureSpec.makeMeasureSpec(view3.getMeasuredWidth(), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(j3, 0x40000000));
        l2++;
          goto _L14
    }

    protected void onDraw(Canvas canvas)
    {
        if(mDivider == null)
            return;
        if(mOrientation == 1)
            drawDividersVertical(canvas);
        else
            drawDividersHorizontal(canvas);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        if(mOrientation == 1)
            layoutVertical(i, j, k, l);
        else
            layoutHorizontal(i, j, k, l);
    }

    protected void onMeasure(int i, int j)
    {
        if(mOrientation == 1)
            measureVertical(i, j);
        else
            measureHorizontal(i, j);
    }

    public void onRtlPropertiesChanged(int i)
    {
        super.onRtlPropertiesChanged(i);
        if(i != mLayoutDirection)
        {
            mLayoutDirection = i;
            if(mOrientation == 0)
                requestLayout();
        }
    }

    public void setBaselineAligned(boolean flag)
    {
        mBaselineAligned = flag;
    }

    public void setBaselineAlignedChildIndex(int i)
    {
        if(i < 0 || i >= getChildCount())
        {
            throw new IllegalArgumentException((new StringBuilder()).append("base aligned child index out of range (0, ").append(getChildCount()).append(")").toString());
        } else
        {
            mBaselineAlignedChildIndex = i;
            return;
        }
    }

    public void setDividerDrawable(Drawable drawable)
    {
        if(drawable == mDivider)
            return;
        mDivider = drawable;
        if(drawable != null)
        {
            mDividerWidth = drawable.getIntrinsicWidth();
            mDividerHeight = drawable.getIntrinsicHeight();
        } else
        {
            mDividerWidth = 0;
            mDividerHeight = 0;
        }
        setWillNotDraw(isShowingDividers() ^ true);
        requestLayout();
    }

    public void setDividerPadding(int i)
    {
        if(i == mDividerPadding)
            return;
        mDividerPadding = i;
        if(isShowingDividers())
        {
            requestLayout();
            invalidate();
        }
    }

    public void setGravity(int i)
    {
        if(mGravity != i)
        {
            int j = i;
            if((0x800007 & i) == 0)
                j = i | 0x800003;
            i = j;
            if((j & 0x70) == 0)
                i = j | 0x30;
            mGravity = i;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i)
    {
        i &= 0x800007;
        if((mGravity & 0x800007) != i)
        {
            mGravity = mGravity & 0xff7ffff8 | i;
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean flag)
    {
        mUseLargestChild = flag;
    }

    public void setOrientation(int i)
    {
        if(mOrientation != i)
        {
            mOrientation = i;
            requestLayout();
        }
    }

    public void setShowDividers(int i)
    {
        if(i == mShowDividers)
        {
            return;
        } else
        {
            mShowDividers = i;
            setWillNotDraw(isShowingDividers() ^ true);
            requestLayout();
            return;
        }
    }

    public void setVerticalGravity(int i)
    {
        i &= 0x70;
        if((mGravity & 0x70) != i)
        {
            mGravity = mGravity & 0xffffff8f | i;
            requestLayout();
        }
    }

    public void setWeightSum(float f)
    {
        mWeightSum = Math.max(0.0F, f);
    }

    public boolean shouldDelayChildPressedState()
    {
        return false;
    }

    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private final boolean mAllowInconsistentMeasurement;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int mLayoutDirection;
    private int mMaxAscent[];
    private int mMaxDescent[];
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;
}

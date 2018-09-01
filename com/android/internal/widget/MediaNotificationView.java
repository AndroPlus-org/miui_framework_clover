// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MediaNotificationView extends FrameLayout
{

    public MediaNotificationView(Context context)
    {
        this(context, null);
    }

    public MediaNotificationView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public MediaNotificationView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public MediaNotificationView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mNotificationContentMarginEnd = context.getResources().getDimensionPixelSize(0x1050109);
        mNotificationContentImageMarginEnd = context.getResources().getDimensionPixelSize(0x1050107);
    }

    private void resetHeaderIndention()
    {
        if(mHeader.getPaddingEnd() != mNotificationContentMarginEnd)
            mHeader.setPaddingRelative(mHeader.getPaddingStart(), mHeader.getPaddingTop(), mNotificationContentMarginEnd, mHeader.getPaddingBottom());
        android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)mHeader.getLayoutParams();
        marginlayoutparams.setMarginEnd(0);
        if(marginlayoutparams.getMarginEnd() != 0)
        {
            marginlayoutparams.setMarginEnd(0);
            mHeader.setLayoutParams(marginlayoutparams);
        }
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        mRightIcon = (ImageView)findViewById(0x10203b1);
        mActions = findViewById(0x10202f7);
        mHeader = findViewById(0x1020334);
        mMainColumn = findViewById(0x1020335);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if(mImagePushIn > 0)
            mRightIcon.layout(mRightIcon.getLeft() + mImagePushIn, mRightIcon.getTop(), mRightIcon.getRight() + mImagePushIn, mRightIcon.getBottom());
    }

    protected void onMeasure(int i, int j)
    {
        int k;
        int i1;
        int k1;
        int l;
        int j1;
        android.view.ViewGroup.MarginLayoutParams marginlayoutparams;
        if(mRightIcon.getVisibility() != 8)
            k = 1;
        else
            k = 0;
        if(k == 0)
            resetHeaderIndention();
        super.onMeasure(i, j);
        l = android.view.View.MeasureSpec.getMode(i);
        i1 = 0;
        j1 = 0;
        mImagePushIn = 0;
        k1 = i1;
        if(k == 0) goto _L2; else goto _L1
_L1:
        k1 = i1;
        if(l == 0) goto _L2; else goto _L3
_L3:
        k1 = android.view.View.MeasureSpec.getSize(i);
        k = mActions.getMeasuredWidth();
        marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)mRightIcon.getLayoutParams();
        l = marginlayoutparams.getMarginEnd();
        k = k1 - k - l;
        i1 = getMeasuredHeight();
        if(k <= i1) goto _L5; else goto _L4
_L4:
        k1 = i1;
_L7:
label0:
        {
            if(marginlayoutparams.width == i1)
            {
                k = j1;
                if(marginlayoutparams.height == i1)
                    break label0;
            }
            marginlayoutparams.width = i1;
            marginlayoutparams.height = i1;
            mRightIcon.setLayoutParams(marginlayoutparams);
            k = 1;
        }
        marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)mMainColumn.getLayoutParams();
        j1 = k1 + l + mNotificationContentMarginEnd;
        if(j1 != marginlayoutparams.getMarginEnd())
        {
            marginlayoutparams.setMarginEnd(j1);
            mMainColumn.setLayoutParams(marginlayoutparams);
            k = 1;
        }
        j1 = k1 + l;
        marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)mHeader.getLayoutParams();
        k1 = k;
        if(marginlayoutparams.getMarginEnd() != j1)
        {
            marginlayoutparams.setMarginEnd(j1);
            mHeader.setLayoutParams(marginlayoutparams);
            k1 = 1;
        }
        if(mHeader.getPaddingEnd() != mNotificationContentImageMarginEnd)
        {
            mHeader.setPaddingRelative(mHeader.getPaddingStart(), mHeader.getPaddingTop(), mNotificationContentImageMarginEnd, mHeader.getPaddingBottom());
            k1 = 1;
        }
_L2:
        if(k1 != 0)
            super.onMeasure(i, j);
        return;
_L5:
        k1 = k;
        if(k < i1)
        {
            k1 = Math.max(0, k);
            mImagePushIn = i1 - k1;
        }
        if(true) goto _L7; else goto _L6
_L6:
    }

    private View mActions;
    private View mHeader;
    private int mImagePushIn;
    private View mMainColumn;
    private final int mNotificationContentImageMarginEnd;
    private final int mNotificationContentMarginEnd;
    private ImageView mRightIcon;
}

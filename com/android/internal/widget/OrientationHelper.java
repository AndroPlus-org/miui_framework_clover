// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.graphics.Rect;
import android.view.View;

public abstract class OrientationHelper
{

    private OrientationHelper(RecyclerView.LayoutManager layoutmanager)
    {
        mLastTotalSpace = 0x80000000;
        mTmpRect = new Rect();
        mLayoutManager = layoutmanager;
    }

    OrientationHelper(RecyclerView.LayoutManager layoutmanager, OrientationHelper orientationhelper)
    {
        this(layoutmanager);
    }

    public static OrientationHelper createHorizontalHelper(RecyclerView.LayoutManager layoutmanager)
    {
        return new OrientationHelper(layoutmanager) {

            public int getDecoratedEnd(View view)
            {
                RecyclerView.LayoutParams layoutparams = (RecyclerView.LayoutParams)view.getLayoutParams();
                return mLayoutManager.getDecoratedRight(view) + layoutparams.rightMargin;
            }

            public int getDecoratedMeasurement(View view)
            {
                RecyclerView.LayoutParams layoutparams = (RecyclerView.LayoutParams)view.getLayoutParams();
                return mLayoutManager.getDecoratedMeasuredWidth(view) + layoutparams.leftMargin + layoutparams.rightMargin;
            }

            public int getDecoratedMeasurementInOther(View view)
            {
                RecyclerView.LayoutParams layoutparams = (RecyclerView.LayoutParams)view.getLayoutParams();
                return mLayoutManager.getDecoratedMeasuredHeight(view) + layoutparams.topMargin + layoutparams.bottomMargin;
            }

            public int getDecoratedStart(View view)
            {
                RecyclerView.LayoutParams layoutparams = (RecyclerView.LayoutParams)view.getLayoutParams();
                return mLayoutManager.getDecoratedLeft(view) - layoutparams.leftMargin;
            }

            public int getEnd()
            {
                return mLayoutManager.getWidth();
            }

            public int getEndAfterPadding()
            {
                return mLayoutManager.getWidth() - mLayoutManager.getPaddingRight();
            }

            public int getEndPadding()
            {
                return mLayoutManager.getPaddingRight();
            }

            public int getMode()
            {
                return mLayoutManager.getWidthMode();
            }

            public int getModeInOther()
            {
                return mLayoutManager.getHeightMode();
            }

            public int getStartAfterPadding()
            {
                return mLayoutManager.getPaddingLeft();
            }

            public int getTotalSpace()
            {
                return mLayoutManager.getWidth() - mLayoutManager.getPaddingLeft() - mLayoutManager.getPaddingRight();
            }

            public int getTransformedEndWithDecoration(View view)
            {
                mLayoutManager.getTransformedBoundingBox(view, true, mTmpRect);
                return mTmpRect.right;
            }

            public int getTransformedStartWithDecoration(View view)
            {
                mLayoutManager.getTransformedBoundingBox(view, true, mTmpRect);
                return mTmpRect.left;
            }

            public void offsetChild(View view, int i)
            {
                view.offsetLeftAndRight(i);
            }

            public void offsetChildren(int i)
            {
                mLayoutManager.offsetChildrenHorizontal(i);
            }

        }
;
    }

    public static OrientationHelper createOrientationHelper(RecyclerView.LayoutManager layoutmanager, int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException("invalid orientation");

        case 0: // '\0'
            return createHorizontalHelper(layoutmanager);

        case 1: // '\001'
            return createVerticalHelper(layoutmanager);
        }
    }

    public static OrientationHelper createVerticalHelper(RecyclerView.LayoutManager layoutmanager)
    {
        return new OrientationHelper(layoutmanager) {

            public int getDecoratedEnd(View view)
            {
                RecyclerView.LayoutParams layoutparams = (RecyclerView.LayoutParams)view.getLayoutParams();
                return mLayoutManager.getDecoratedBottom(view) + layoutparams.bottomMargin;
            }

            public int getDecoratedMeasurement(View view)
            {
                RecyclerView.LayoutParams layoutparams = (RecyclerView.LayoutParams)view.getLayoutParams();
                return mLayoutManager.getDecoratedMeasuredHeight(view) + layoutparams.topMargin + layoutparams.bottomMargin;
            }

            public int getDecoratedMeasurementInOther(View view)
            {
                RecyclerView.LayoutParams layoutparams = (RecyclerView.LayoutParams)view.getLayoutParams();
                return mLayoutManager.getDecoratedMeasuredWidth(view) + layoutparams.leftMargin + layoutparams.rightMargin;
            }

            public int getDecoratedStart(View view)
            {
                RecyclerView.LayoutParams layoutparams = (RecyclerView.LayoutParams)view.getLayoutParams();
                return mLayoutManager.getDecoratedTop(view) - layoutparams.topMargin;
            }

            public int getEnd()
            {
                return mLayoutManager.getHeight();
            }

            public int getEndAfterPadding()
            {
                return mLayoutManager.getHeight() - mLayoutManager.getPaddingBottom();
            }

            public int getEndPadding()
            {
                return mLayoutManager.getPaddingBottom();
            }

            public int getMode()
            {
                return mLayoutManager.getHeightMode();
            }

            public int getModeInOther()
            {
                return mLayoutManager.getWidthMode();
            }

            public int getStartAfterPadding()
            {
                return mLayoutManager.getPaddingTop();
            }

            public int getTotalSpace()
            {
                return mLayoutManager.getHeight() - mLayoutManager.getPaddingTop() - mLayoutManager.getPaddingBottom();
            }

            public int getTransformedEndWithDecoration(View view)
            {
                mLayoutManager.getTransformedBoundingBox(view, true, mTmpRect);
                return mTmpRect.bottom;
            }

            public int getTransformedStartWithDecoration(View view)
            {
                mLayoutManager.getTransformedBoundingBox(view, true, mTmpRect);
                return mTmpRect.top;
            }

            public void offsetChild(View view, int i)
            {
                view.offsetTopAndBottom(i);
            }

            public void offsetChildren(int i)
            {
                mLayoutManager.offsetChildrenVertical(i);
            }

        }
;
    }

    public abstract int getDecoratedEnd(View view);

    public abstract int getDecoratedMeasurement(View view);

    public abstract int getDecoratedMeasurementInOther(View view);

    public abstract int getDecoratedStart(View view);

    public abstract int getEnd();

    public abstract int getEndAfterPadding();

    public abstract int getEndPadding();

    public abstract int getMode();

    public abstract int getModeInOther();

    public abstract int getStartAfterPadding();

    public abstract int getTotalSpace();

    public int getTotalSpaceChange()
    {
        int i;
        if(0x80000000 == mLastTotalSpace)
            i = 0;
        else
            i = getTotalSpace() - mLastTotalSpace;
        return i;
    }

    public abstract int getTransformedEndWithDecoration(View view);

    public abstract int getTransformedStartWithDecoration(View view);

    public abstract void offsetChild(View view, int i);

    public abstract void offsetChildren(int i);

    public void onLayoutComplete()
    {
        mLastTotalSpace = getTotalSpace();
    }

    public static final int HORIZONTAL = 0;
    private static final int INVALID_SIZE = 0x80000000;
    public static final int VERTICAL = 1;
    private int mLastTotalSpace;
    protected final RecyclerView.LayoutManager mLayoutManager;
    final Rect mTmpRect;
}

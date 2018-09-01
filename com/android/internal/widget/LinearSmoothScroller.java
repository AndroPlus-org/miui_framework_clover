// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

public class LinearSmoothScroller extends RecyclerView.SmoothScroller
{

    public LinearSmoothScroller(Context context)
    {
        mInterimTargetDx = 0;
        mInterimTargetDy = 0;
        MILLISECONDS_PER_PX = calculateSpeedPerPixel(context.getResources().getDisplayMetrics());
    }

    private int clampApplyScroll(int i, int j)
    {
        j = i - j;
        if(i * j <= 0)
            return 0;
        else
            return j;
    }

    public int calculateDtToFit(int i, int j, int k, int l, int i1)
    {
        switch(i1)
        {
        default:
            throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");

        case -1: 
            return k - i;

        case 1: // '\001'
            return l - j;

        case 0: // '\0'
            i = k - i;
            break;
        }
        if(i > 0)
            return i;
        i = l - j;
        if(i < 0)
            return i;
        else
            return 0;
    }

    public int calculateDxToMakeVisible(View view, int i)
    {
        RecyclerView.LayoutManager layoutmanager = getLayoutManager();
        if(layoutmanager == null || layoutmanager.canScrollHorizontally() ^ true)
        {
            return 0;
        } else
        {
            RecyclerView.LayoutParams layoutparams = (RecyclerView.LayoutParams)view.getLayoutParams();
            return calculateDtToFit(layoutmanager.getDecoratedLeft(view) - layoutparams.leftMargin, layoutmanager.getDecoratedRight(view) + layoutparams.rightMargin, layoutmanager.getPaddingLeft(), layoutmanager.getWidth() - layoutmanager.getPaddingRight(), i);
        }
    }

    public int calculateDyToMakeVisible(View view, int i)
    {
        RecyclerView.LayoutManager layoutmanager = getLayoutManager();
        if(layoutmanager == null || layoutmanager.canScrollVertically() ^ true)
        {
            return 0;
        } else
        {
            RecyclerView.LayoutParams layoutparams = (RecyclerView.LayoutParams)view.getLayoutParams();
            return calculateDtToFit(layoutmanager.getDecoratedTop(view) - layoutparams.topMargin, layoutmanager.getDecoratedBottom(view) + layoutparams.bottomMargin, layoutmanager.getPaddingTop(), layoutmanager.getHeight() - layoutmanager.getPaddingBottom(), i);
        }
    }

    protected float calculateSpeedPerPixel(DisplayMetrics displaymetrics)
    {
        return 25F / (float)displaymetrics.densityDpi;
    }

    protected int calculateTimeForDeceleration(int i)
    {
        return (int)Math.ceil((double)calculateTimeForScrolling(i) / 0.33560000000000001D);
    }

    protected int calculateTimeForScrolling(int i)
    {
        return (int)Math.ceil((float)Math.abs(i) * MILLISECONDS_PER_PX);
    }

    public PointF computeScrollVectorForPosition(int i)
    {
        RecyclerView.LayoutManager layoutmanager = getLayoutManager();
        if(layoutmanager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)
        {
            return ((RecyclerView.SmoothScroller.ScrollVectorProvider)layoutmanager).computeScrollVectorForPosition(i);
        } else
        {
            Log.w("LinearSmoothScroller", (new StringBuilder()).append("You should override computeScrollVectorForPosition when the LayoutManager does not implement ").append(com/android/internal/widget/RecyclerView$SmoothScroller$ScrollVectorProvider.getCanonicalName()).toString());
            return null;
        }
    }

    protected int getHorizontalSnapPreference()
    {
        int i;
        if(mTargetVector == null || mTargetVector.x == 0.0F)
            i = 0;
        else
        if(mTargetVector.x > 0.0F)
            i = 1;
        else
            i = -1;
        return i;
    }

    protected int getVerticalSnapPreference()
    {
        int i;
        if(mTargetVector == null || mTargetVector.y == 0.0F)
            i = 0;
        else
        if(mTargetVector.y > 0.0F)
            i = 1;
        else
            i = -1;
        return i;
    }

    protected void onSeekTargetStep(int i, int j, RecyclerView.State state, RecyclerView.SmoothScroller.Action action)
    {
        if(getChildCount() == 0)
        {
            stop();
            return;
        }
        mInterimTargetDx = clampApplyScroll(mInterimTargetDx, i);
        mInterimTargetDy = clampApplyScroll(mInterimTargetDy, j);
        if(mInterimTargetDx == 0 && mInterimTargetDy == 0)
            updateActionForInterimTarget(action);
    }

    protected void onStart()
    {
    }

    protected void onStop()
    {
        mInterimTargetDy = 0;
        mInterimTargetDx = 0;
        mTargetVector = null;
    }

    protected void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action)
    {
        int i = calculateDxToMakeVisible(view, getHorizontalSnapPreference());
        int j = calculateDyToMakeVisible(view, getVerticalSnapPreference());
        int k = calculateTimeForDeceleration((int)Math.sqrt(i * i + j * j));
        if(k > 0)
            action.update(-i, -j, k, mDecelerateInterpolator);
    }

    protected void updateActionForInterimTarget(RecyclerView.SmoothScroller.Action action)
    {
        PointF pointf = computeScrollVectorForPosition(getTargetPosition());
        if(pointf == null || pointf.x == 0.0F && pointf.y == 0.0F)
        {
            action.jumpTo(getTargetPosition());
            stop();
            return;
        } else
        {
            normalize(pointf);
            mTargetVector = pointf;
            mInterimTargetDx = (int)(pointf.x * 10000F);
            mInterimTargetDy = (int)(pointf.y * 10000F);
            int i = calculateTimeForScrolling(10000);
            action.update((int)((float)mInterimTargetDx * 1.2F), (int)((float)mInterimTargetDy * 1.2F), (int)((float)i * 1.2F), mLinearInterpolator);
            return;
        }
    }

    private static final boolean DEBUG = false;
    private static final float MILLISECONDS_PER_INCH = 25F;
    public static final int SNAP_TO_ANY = 0;
    public static final int SNAP_TO_END = 1;
    public static final int SNAP_TO_START = -1;
    private static final String TAG = "LinearSmoothScroller";
    private static final float TARGET_SEEK_EXTRA_SCROLL_RATIO = 1.2F;
    private static final int TARGET_SEEK_SCROLL_DISTANCE_PX = 10000;
    private final float MILLISECONDS_PER_PX;
    protected final DecelerateInterpolator mDecelerateInterpolator = new DecelerateInterpolator();
    protected int mInterimTargetDx;
    protected int mInterimTargetDy;
    protected final LinearInterpolator mLinearInterpolator = new LinearInterpolator();
    protected PointF mTargetVector;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.policy;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.DisplayInfo;
import java.util.ArrayList;

// Referenced classes of package com.android.internal.policy:
//            DockedDividerUtils

public class DividerSnapAlgorithm
{
    public static class SnapTarget
    {

        static float _2D_get0(SnapTarget snaptarget)
        {
            return snaptarget.distanceMultiplier;
        }

        public static final int FLAG_DISMISS_END = 2;
        public static final int FLAG_DISMISS_START = 1;
        public static final int FLAG_NONE = 0;
        private final float distanceMultiplier;
        public final int flag;
        public final int position;
        public final int taskPosition;

        public SnapTarget(int i, int j, int k)
        {
            this(i, j, k, 1.0F);
        }

        public SnapTarget(int i, int j, int k, float f)
        {
            position = i;
            taskPosition = j;
            flag = k;
            distanceMultiplier = f;
        }
    }


    public DividerSnapAlgorithm(Resources resources, int i, int j, int k, boolean flag, Rect rect)
    {
        this(resources, i, j, k, flag, rect, false);
    }

    public DividerSnapAlgorithm(Resources resources, int i, int j, int k, boolean flag, Rect rect, boolean flag1)
    {
        mTargets = new ArrayList();
        mInsets = new Rect();
        mMinFlingVelocityPxPerSecond = resources.getDisplayMetrics().density * 400F;
        mMinDismissVelocityPxPerSecond = resources.getDisplayMetrics().density * 600F;
        mDividerSize = k;
        mDisplayWidth = i;
        mDisplayHeight = j;
        mIsHorizontalDivision = flag;
        mInsets.set(rect);
        if(flag1)
            i = 3;
        else
            i = resources.getInteger(0x10e0049);
        mSnapMode = i;
        mFixedRatio = resources.getFraction(0x1130004, 1, 1);
        mMinimalSizeResizableTask = resources.getDimensionPixelSize(0x105006b);
        mTaskHeightInMinimizedMode = resources.getDimensionPixelSize(0x105017e);
        calculateTargets(flag);
        mFirstSplitTarget = (SnapTarget)mTargets.get(1);
        mLastSplitTarget = (SnapTarget)mTargets.get(mTargets.size() - 2);
        mDismissStartTarget = (SnapTarget)mTargets.get(0);
        mDismissEndTarget = (SnapTarget)mTargets.get(mTargets.size() - 1);
        mMiddleTarget = (SnapTarget)mTargets.get(mTargets.size() / 2);
    }

    private void addFixedDivisionTargets(boolean flag, int i)
    {
        int j;
        int k;
        int l;
        if(flag)
            j = mInsets.top;
        else
            j = mInsets.left;
        if(flag)
            k = mDisplayHeight - mInsets.bottom;
        else
            k = mDisplayWidth - mInsets.right;
        l = (int)(mFixedRatio * (float)(k - j)) - mDividerSize / 2;
        addNonDismissingTargets(flag, j + l, k - l - mDividerSize, i);
    }

    private void addMiddleTarget(boolean flag)
    {
        int i = DockedDividerUtils.calculateMiddlePosition(flag, mInsets, mDisplayWidth, mDisplayHeight, mDividerSize);
        mTargets.add(new SnapTarget(i, i, 0));
    }

    private void addMinimizedTarget(boolean flag)
    {
        int i = mTaskHeightInMinimizedMode + mInsets.top;
        int j = i;
        if(!flag)
            j = i + mInsets.left;
        mTargets.add(new SnapTarget(j, j, 0));
    }

    private void addNonDismissingTargets(boolean flag, int i, int j, int k)
    {
        maybeAddTarget(i, i - mInsets.top);
        addMiddleTarget(flag);
        maybeAddTarget(j, k - mInsets.bottom - (mDividerSize + j));
    }

    private void addRatio16_9Targets(boolean flag, int i)
    {
        int j;
        int k;
        int l;
        int i1;
        if(flag)
            j = mInsets.top;
        else
            j = mInsets.left;
        if(flag)
            k = mDisplayHeight - mInsets.bottom;
        else
            k = mDisplayWidth - mInsets.right;
        if(flag)
            l = mInsets.left;
        else
            l = mInsets.top;
        if(flag)
            i1 = mDisplayWidth - mInsets.right;
        else
            i1 = mDisplayHeight - mInsets.bottom;
        l = (int)Math.floor(0.5625F * (float)(i1 - l));
        addNonDismissingTargets(flag, j + l, k - l - mDividerSize, i);
    }

    private void calculateTargets(boolean flag)
    {
        int i;
        mTargets.clear();
        int j;
        if(flag)
            i = mDisplayHeight;
        else
            i = mDisplayWidth;
        if(flag)
            j = mInsets.bottom;
        else
            j = mInsets.right;
        mTargets.add(new SnapTarget(-mDividerSize, -mDividerSize, 1, 0.35F));
        mSnapMode;
        JVM INSTR tableswitch 0 3: default 92
    //                   0 134
    //                   1 143
    //                   2 152
    //                   3 160;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        mTargets.add(new SnapTarget(i - j, i, 2, 0.35F));
        return;
_L2:
        addRatio16_9Targets(flag, i);
        continue; /* Loop/switch isn't completed */
_L3:
        addFixedDivisionTargets(flag, i);
        continue; /* Loop/switch isn't completed */
_L4:
        addMiddleTarget(flag);
        continue; /* Loop/switch isn't completed */
_L5:
        addMinimizedTarget(flag);
        if(true) goto _L1; else goto _L6
_L6:
    }

    public static DividerSnapAlgorithm create(Context context, Rect rect)
    {
        boolean flag = true;
        DisplayInfo displayinfo = new DisplayInfo();
        ((DisplayManager)context.getSystemService(android/hardware/display/DisplayManager)).getDisplay(0).getDisplayInfo(displayinfo);
        int i = context.getResources().getDimensionPixelSize(0x105007b);
        int j = context.getResources().getDimensionPixelSize(0x105007a);
        Resources resources = context.getResources();
        int k = displayinfo.logicalWidth;
        int l = displayinfo.logicalHeight;
        if(context.getApplicationContext().getResources().getConfiguration().orientation != 1)
            flag = false;
        return new DividerSnapAlgorithm(resources, k, l, i - j * 2, flag, rect);
    }

    private int getEndInset()
    {
        if(mIsHorizontalDivision)
            return mInsets.bottom;
        else
            return mInsets.right;
    }

    private int getStartInset()
    {
        if(mIsHorizontalDivision)
            return mInsets.top;
        else
            return mInsets.left;
    }

    private void maybeAddTarget(int i, int j)
    {
        if(j >= mMinimalSizeResizableTask)
            mTargets.add(new SnapTarget(i, i, 0));
    }

    private SnapTarget snap(int i, boolean flag)
    {
        int j = -1;
        float f = 3.402823E+038F;
        int k = mTargets.size();
        for(int l = 0; l < k;)
        {
            SnapTarget snaptarget = (SnapTarget)mTargets.get(l);
            float f1 = Math.abs(i - snaptarget.position);
            float f2 = f1;
            if(flag)
                f2 = f1 / SnapTarget._2D_get0(snaptarget);
            f1 = f;
            if(f2 < f)
            {
                j = l;
                f1 = f2;
            }
            l++;
            f = f1;
        }

        return (SnapTarget)mTargets.get(j);
    }

    public float calculateDismissingFraction(int i)
    {
        if(i < mFirstSplitTarget.position)
            return 1.0F - (float)(i - getStartInset()) / (float)(mFirstSplitTarget.position - getStartInset());
        if(i > mLastSplitTarget.position)
            return (float)(i - mLastSplitTarget.position) / (float)(mDismissEndTarget.position - mLastSplitTarget.position - mDividerSize);
        else
            return 0.0F;
    }

    public SnapTarget calculateNonDismissingSnapTarget(int i)
    {
        SnapTarget snaptarget = snap(i, false);
        if(snaptarget == mDismissStartTarget)
            return mFirstSplitTarget;
        if(snaptarget == mDismissEndTarget)
            return mLastSplitTarget;
        else
            return snaptarget;
    }

    public SnapTarget calculateSnapTarget(int i, float f)
    {
        return calculateSnapTarget(i, f, true);
    }

    public SnapTarget calculateSnapTarget(int i, float f, boolean flag)
    {
        if(i < mFirstSplitTarget.position && f < -mMinDismissVelocityPxPerSecond)
            return mDismissStartTarget;
        if(i > mLastSplitTarget.position && f > mMinDismissVelocityPxPerSecond)
            return mDismissEndTarget;
        if(Math.abs(f) < mMinFlingVelocityPxPerSecond)
            return snap(i, flag);
        if(f < 0.0F)
            return mFirstSplitTarget;
        else
            return mLastSplitTarget;
    }

    public SnapTarget cycleNonDismissTarget(SnapTarget snaptarget, int i)
    {
        int j = mTargets.indexOf(snaptarget);
        if(j != -1)
        {
            snaptarget = (SnapTarget)mTargets.get((mTargets.size() + j + i) % mTargets.size());
            if(snaptarget == mDismissStartTarget)
                return mLastSplitTarget;
            if(snaptarget == mDismissEndTarget)
                return mFirstSplitTarget;
            else
                return snaptarget;
        } else
        {
            return snaptarget;
        }
    }

    public SnapTarget getClosestDismissTarget(int i)
    {
        if(i < mFirstSplitTarget.position)
            return mDismissStartTarget;
        if(i > mLastSplitTarget.position)
            return mDismissEndTarget;
        if(i - mDismissStartTarget.position < mDismissEndTarget.position - i)
            return mDismissStartTarget;
        else
            return mDismissEndTarget;
    }

    public SnapTarget getDismissEndTarget()
    {
        return mDismissEndTarget;
    }

    public SnapTarget getDismissStartTarget()
    {
        return mDismissStartTarget;
    }

    public SnapTarget getFirstSplitTarget()
    {
        return mFirstSplitTarget;
    }

    public SnapTarget getLastSplitTarget()
    {
        return mLastSplitTarget;
    }

    public SnapTarget getMiddleTarget()
    {
        return mMiddleTarget;
    }

    public SnapTarget getNextTarget(SnapTarget snaptarget)
    {
        int i = mTargets.indexOf(snaptarget);
        if(i != -1 && i < mTargets.size() - 1)
            return (SnapTarget)mTargets.get(i + 1);
        else
            return snaptarget;
    }

    public SnapTarget getPreviousTarget(SnapTarget snaptarget)
    {
        int i = mTargets.indexOf(snaptarget);
        if(i != -1 && i > 0)
            return (SnapTarget)mTargets.get(i - 1);
        else
            return snaptarget;
    }

    public boolean isFirstSplitTargetAvailable()
    {
        boolean flag;
        if(mFirstSplitTarget != mMiddleTarget)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isLastSplitTargetAvailable()
    {
        boolean flag;
        if(mLastSplitTarget != mMiddleTarget)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isSplitScreenFeasible()
    {
        int i = mInsets.top;
        int j;
        int k;
        boolean flag;
        if(mIsHorizontalDivision)
            j = mInsets.bottom;
        else
            j = mInsets.right;
        if(mIsHorizontalDivision)
            k = mDisplayHeight;
        else
            k = mDisplayWidth;
        if((k - j - i - mDividerSize) / 2 >= mMinimalSizeResizableTask)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static final int MIN_DISMISS_VELOCITY_DP_PER_SECOND = 600;
    private static final int MIN_FLING_VELOCITY_DP_PER_SECOND = 400;
    private static final int SNAP_FIXED_RATIO = 1;
    private static final int SNAP_MODE_16_9 = 0;
    private static final int SNAP_MODE_MINIMIZED = 3;
    private static final int SNAP_ONLY_1_1 = 2;
    private final SnapTarget mDismissEndTarget;
    private final SnapTarget mDismissStartTarget;
    private final int mDisplayHeight;
    private final int mDisplayWidth;
    private final int mDividerSize;
    private final SnapTarget mFirstSplitTarget;
    private final float mFixedRatio;
    private final Rect mInsets;
    private boolean mIsHorizontalDivision;
    private final SnapTarget mLastSplitTarget;
    private final SnapTarget mMiddleTarget;
    private final float mMinDismissVelocityPxPerSecond;
    private final float mMinFlingVelocityPxPerSecond;
    private final int mMinimalSizeResizableTask;
    private final int mSnapMode;
    private final ArrayList mTargets;
    private final int mTaskHeightInMinimizedMode;
}

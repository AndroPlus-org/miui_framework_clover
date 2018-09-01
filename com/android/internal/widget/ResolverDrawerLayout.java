// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AnimationUtils;
import android.widget.*;

public class ResolverDrawerLayout extends ViewGroup
{
    public static class LayoutParams extends android.view.ViewGroup.MarginLayoutParams
    {

        public boolean alwaysShow;
        public boolean hasNestedScrollIndicator;
        public boolean ignoreOffset;

        public LayoutParams(int i, int j)
        {
            super(i, j);
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ResolverDrawerLayout_LayoutParams);
            alwaysShow = context.getBoolean(1, false);
            ignoreOffset = context.getBoolean(3, false);
            hasNestedScrollIndicator = context.getBoolean(2, false);
            context.recycle();
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            super(layoutparams);
            alwaysShow = layoutparams.alwaysShow;
            ignoreOffset = layoutparams.ignoreOffset;
            hasNestedScrollIndicator = layoutparams.hasNestedScrollIndicator;
        }
    }

    public static interface OnDismissedListener
    {

        public abstract void onDismissed();
    }

    private class RunOnDismissedListener
        implements Runnable
    {

        public void run()
        {
            dispatchOnDismissed();
        }

        final ResolverDrawerLayout this$0;

        private RunOnDismissedListener()
        {
            this$0 = ResolverDrawerLayout.this;
            super();
        }

        RunOnDismissedListener(RunOnDismissedListener runondismissedlistener)
        {
            this();
        }
    }

    static class SavedState extends android.view.View.BaseSavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            if(open)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        boolean open;


        private SavedState(Parcel parcel)
        {
            boolean flag = false;
            super(parcel);
            if(parcel.readInt() != 0)
                flag = true;
            open = flag;
        }

        SavedState(Parcel parcel, SavedState savedstate)
        {
            this(parcel);
        }

        SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }


    static boolean _2D_wrap0(ResolverDrawerLayout resolverdrawerlayout, View view)
    {
        return resolverdrawerlayout.isDescendantClipped(view);
    }

    static void _2D_wrap1(ResolverDrawerLayout resolverdrawerlayout, int i, float f)
    {
        resolverdrawerlayout.smoothScrollTo(i, f);
    }

    public ResolverDrawerLayout(Context context)
    {
        this(context, null);
    }

    public ResolverDrawerLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public ResolverDrawerLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mActivePointerId = -1;
        mTempRect = new Rect();
        mTouchModeChangeListener = new android.view.ViewTreeObserver.OnTouchModeChangeListener() {

            public void onTouchModeChanged(boolean flag)
            {
                if(!flag && hasFocus() && ResolverDrawerLayout._2D_wrap0(ResolverDrawerLayout.this, getFocusedChild()))
                    ResolverDrawerLayout._2D_wrap1(ResolverDrawerLayout.this, 0, 0.0F);
            }

            final ResolverDrawerLayout this$0;

            
            {
                this$0 = ResolverDrawerLayout.this;
                super();
            }
        }
;
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ResolverDrawerLayout, i, 0);
        mMaxWidth = attributeset.getDimensionPixelSize(0, -1);
        mMaxCollapsedHeight = attributeset.getDimensionPixelSize(1, 0);
        mMaxCollapsedHeightSmall = attributeset.getDimensionPixelSize(2, mMaxCollapsedHeight);
        attributeset.recycle();
        mScrollIndicatorDrawable = mContext.getDrawable(0x1080719);
        mScroller = new OverScroller(context, AnimationUtils.loadInterpolator(context, 0x10c0005));
        mVelocityTracker = VelocityTracker.obtain();
        context = ViewConfiguration.get(context);
        mTouchSlop = context.getScaledTouchSlop();
        mMinFlingVelocity = context.getScaledMinimumFlingVelocity();
        setImportantForAccessibility(1);
    }

    private void abortAnimation()
    {
        mScroller.abortAnimation();
        mRunOnDismissedListener = null;
        mDismissOnScrollerFinished = false;
    }

    private float distanceInfluenceForSnapDuration(float f)
    {
        return (float)Math.sin((float)((double)(f - 0.5F) * 0.4712389167638204D));
    }

    private View findChildUnder(float f, float f1)
    {
        return findChildUnder(((ViewGroup) (this)), f, f1);
    }

    private static View findChildUnder(ViewGroup viewgroup, float f, float f1)
    {
        for(int i = viewgroup.getChildCount() - 1; i >= 0; i--)
        {
            View view = viewgroup.getChildAt(i);
            if(isChildUnder(view, f, f1))
                return view;
        }

        return null;
    }

    private View findListChildUnder(float f, float f1)
    {
        View view;
        for(view = findChildUnder(f, f1); view != null;)
        {
            f -= view.getX();
            f1 -= view.getY();
            if(view instanceof AbsListView)
                return findChildUnder((ViewGroup)view, f, f1);
            if(view instanceof ViewGroup)
                view = findChildUnder((ViewGroup)view, f, f1);
            else
                view = null;
        }

        return view;
    }

    private int getMaxCollapsedHeight()
    {
        int i;
        if(isSmallCollapsed())
            i = mMaxCollapsedHeightSmall;
        else
            i = mMaxCollapsedHeight;
        return i + mCollapsibleHeightReserved;
    }

    private static boolean isChildUnder(View view, float f, float f1)
    {
        boolean flag = false;
        float f2 = view.getX();
        float f3 = view.getY();
        float f4 = view.getWidth();
        float f5 = view.getHeight();
        boolean flag1 = flag;
        if(f >= f2)
        {
            flag1 = flag;
            if(f1 >= f3)
            {
                flag1 = flag;
                if(f < f2 + f4)
                {
                    flag1 = flag;
                    if(f1 < f3 + f5)
                        flag1 = true;
                }
            }
        }
        return flag1;
    }

    private boolean isDescendantClipped(View view)
    {
        boolean flag = false;
        mTempRect.set(0, 0, view.getWidth(), view.getHeight());
        offsetDescendantRectToMyCoords(view, mTempRect);
        int i;
        int j;
        int k;
        if(view.getParent() != this)
        {
            Object obj = view;
            android.view.ViewParent viewparent = view.getParent();
            view = ((View) (obj));
            obj = viewparent;
            while(obj != this) 
            {
                view = (View)obj;
                obj = view.getParent();
            }
        }
        i = getHeight() - getPaddingBottom();
        j = getChildCount();
        k = indexOfChild(view) + 1;
        while(k < j) 
        {
            view = getChildAt(k);
            if(view.getVisibility() != 8)
                i = Math.min(i, view.getTop());
            k++;
        }
        if(mTempRect.bottom > i)
            flag = true;
        return flag;
    }

    private boolean isDismissable()
    {
        boolean flag;
        if(mOnDismissedListener != null)
            flag = mDismissLocked ^ true;
        else
            flag = false;
        return flag;
    }

    private boolean isDragging()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(!mIsDragging)
            if(getNestedScrollAxes() == 2)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    private boolean isListChildUnderClipped(float f, float f1)
    {
        View view = findListChildUnder(f, f1);
        boolean flag;
        if(view != null)
            flag = isDescendantClipped(view);
        else
            flag = false;
        return flag;
    }

    private boolean isMoving()
    {
        boolean flag;
        if(!mIsDragging)
            flag = mScroller.isFinished() ^ true;
        else
            flag = true;
        return flag;
    }

    private void onCollapsedChanged(boolean flag)
    {
        notifyViewAccessibilityStateChangedIfNeeded(0);
        if(mScrollIndicatorDrawable != null)
            setWillNotDraw(flag ^ true);
    }

    private void onSecondaryPointerUp(MotionEvent motionevent)
    {
        int i = motionevent.getActionIndex();
        if(motionevent.getPointerId(i) == mActivePointerId)
        {
            float f;
            if(i == 0)
                i = 1;
            else
                i = 0;
            mInitialTouchX = motionevent.getX(i);
            f = motionevent.getY(i);
            mLastTouchY = f;
            mInitialTouchY = f;
            mActivePointerId = motionevent.getPointerId(i);
        }
    }

    private float performDrag(float f)
    {
        float f1 = Math.max(0.0F, Math.min(mCollapseOffset + f, mCollapsibleHeight + mUncollapsibleHeight));
        if(f1 != mCollapseOffset)
        {
            f = f1 - mCollapseOffset;
            int i = getChildCount();
            for(int j = 0; j < i; j++)
            {
                View view = getChildAt(j);
                if(!((LayoutParams)view.getLayoutParams()).ignoreOffset)
                    view.offsetTopAndBottom((int)f);
            }

            boolean flag;
            boolean flag1;
            if(mCollapseOffset != 0.0F)
                flag = true;
            else
                flag = false;
            mCollapseOffset = f1;
            mTopOffset = (int)((float)mTopOffset + f);
            if(f1 != 0.0F)
                flag1 = true;
            else
                flag1 = false;
            if(flag != flag1)
                onCollapsedChanged(flag1);
            postInvalidateOnAnimation();
            return f;
        } else
        {
            return 0.0F;
        }
    }

    private void resetTouch()
    {
        mActivePointerId = -1;
        mIsDragging = false;
        mOpenOnClick = false;
        mLastTouchY = 0.0F;
        mInitialTouchY = 0.0F;
        mInitialTouchX = 0.0F;
        mVelocityTracker.clear();
    }

    private void smoothScrollTo(int i, float f)
    {
        abortAnimation();
        int j = (int)mCollapseOffset;
        int k = i - j;
        if(k == 0)
            return;
        int l = getHeight();
        i = l / 2;
        float f1 = Math.min(1.0F, ((float)Math.abs(k) * 1.0F) / (float)l);
        float f2 = i;
        float f3 = i;
        f1 = distanceInfluenceForSnapDuration(f1);
        f = Math.abs(f);
        if(f > 0.0F)
            i = Math.round(Math.abs((f2 + f3 * f1) / f) * 1000F) * 4;
        else
            i = (int)(((float)Math.abs(k) / (float)l + 1.0F) * 100F);
        i = Math.min(i, 300);
        mScroller.startScroll(0, j, 0, k, i);
        postInvalidateOnAnimation();
    }

    private boolean updateCollapseOffset(int i, boolean flag)
    {
        boolean flag1 = false;
        if(i == mCollapsibleHeight)
            return false;
        if(isLaidOut())
        {
            boolean flag2;
            if(mCollapseOffset != 0.0F)
                flag2 = true;
            else
                flag2 = false;
            if(flag && i < mCollapsibleHeight && mCollapseOffset == (float)i)
                mCollapseOffset = mCollapsibleHeight;
            else
                mCollapseOffset = Math.min(mCollapseOffset, mCollapsibleHeight);
            if(mCollapseOffset != 0.0F)
                flag = true;
            else
                flag = false;
            if(flag2 != flag)
                onCollapsedChanged(flag);
        } else
        {
            if(mOpenOnLayout)
                i = ((flag1) ? 1 : 0);
            else
                i = mCollapsibleHeight;
            mCollapseOffset = i;
        }
        return true;
    }

    public void computeScroll()
    {
        super.computeScroll();
        if(!mScroller.computeScrollOffset()) goto _L2; else goto _L1
_L1:
        boolean flag;
        flag = mScroller.isFinished();
        performDrag((float)mScroller.getCurrY() - mCollapseOffset);
        if(!(flag ^ true)) goto _L4; else goto _L3
_L3:
        postInvalidateOnAnimation();
_L2:
        return;
_L4:
        if(mDismissOnScrollerFinished && mOnDismissedListener != null)
        {
            mRunOnDismissedListener = new RunOnDismissedListener(null);
            post(mRunOnDismissedListener);
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    void dispatchOnDismissed()
    {
        if(mOnDismissedListener != null)
            mOnDismissedListener.onDismissed();
        if(mRunOnDismissedListener != null)
        {
            removeCallbacks(mRunOnDismissedListener);
            mRunOnDismissedListener = null;
        }
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-1, -2);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        if(layoutparams instanceof LayoutParams)
            return new LayoutParams((LayoutParams)layoutparams);
        if(layoutparams instanceof android.view.ViewGroup.MarginLayoutParams)
            return new LayoutParams((android.view.ViewGroup.MarginLayoutParams)layoutparams);
        else
            return new LayoutParams(layoutparams);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/ScrollView.getName();
    }

    public boolean isCollapsed()
    {
        boolean flag;
        if(mCollapseOffset > 0.0F)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isSmallCollapsed()
    {
        return mSmallCollapsed;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnTouchModeChangeListener(mTouchModeChangeListener);
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnTouchModeChangeListener(mTouchModeChangeListener);
        abortAnimation();
    }

    public void onDrawForeground(Canvas canvas)
    {
        if(mScrollIndicatorDrawable != null)
            mScrollIndicatorDrawable.draw(canvas);
        super.onDrawForeground(canvas);
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        if(isEnabled() && mCollapseOffset != 0.0F)
        {
            accessibilitynodeinfo.addAction(4096);
            accessibilitynodeinfo.setScrollable(true);
        }
        accessibilitynodeinfo.removeAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_ACCESSIBILITY_FOCUS);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        boolean flag1;
        int i;
        flag = true;
        flag1 = false;
        i = motionevent.getActionMasked();
        if(i == 0)
            mVelocityTracker.clear();
        mVelocityTracker.addMovement(motionevent);
        i;
        JVM INSTR tableswitch 0 6: default 76
    //                   0 106
    //                   1 291
    //                   2 174
    //                   3 291
    //                   4 76
    //                   5 76
    //                   6 283;
           goto _L1 _L2 _L3 _L4 _L3 _L1 _L1 _L5
_L1:
        if(mIsDragging)
            abortAnimation();
        boolean flag2 = flag;
        if(!mIsDragging)
            flag2 = mOpenOnClick;
        return flag2;
_L2:
        float f = motionevent.getX();
        float f2 = motionevent.getY();
        mInitialTouchX = f;
        mLastTouchY = f2;
        mInitialTouchY = f2;
        boolean flag3 = flag1;
        if(isListChildUnderClipped(f, f2))
        {
            flag3 = flag1;
            if(mCollapseOffset > 0.0F)
                flag3 = true;
        }
        mOpenOnClick = flag3;
        continue; /* Loop/switch isn't completed */
_L4:
        float f4 = motionevent.getX();
        float f3 = motionevent.getY();
        float f1 = f3 - mInitialTouchY;
        if(Math.abs(f1) > (float)mTouchSlop && findChildUnder(f4, f3) != null && (getNestedScrollAxes() & 2) == 0)
        {
            mActivePointerId = motionevent.getPointerId(0);
            mIsDragging = true;
            mLastTouchY = Math.max(mLastTouchY - (float)mTouchSlop, Math.min(mLastTouchY + f1, mLastTouchY + (float)mTouchSlop));
        }
        continue; /* Loop/switch isn't completed */
_L5:
        onSecondaryPointerUp(motionevent);
        continue; /* Loop/switch isn't completed */
_L3:
        resetTouch();
        if(true) goto _L1; else goto _L6
_L6:
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1 = getWidth();
        View view = null;
        j = mTopOffset;
        int j1 = getPaddingLeft();
        l = getPaddingRight();
        int k1 = getChildCount();
        i = 0;
        while(i < k1) 
        {
            View view1 = getChildAt(i);
            LayoutParams layoutparams = (LayoutParams)view1.getLayoutParams();
            if(layoutparams.hasNestedScrollIndicator)
                view = view1;
            if(view1.getVisibility() != 8)
            {
                k = j + layoutparams.topMargin;
                j = k;
                if(layoutparams.ignoreOffset)
                    j = (int)((float)k - mCollapseOffset);
                int l1 = j + view1.getMeasuredHeight();
                k = view1.getMeasuredWidth();
                int i2 = j1 + (i1 - l - j1 - k) / 2;
                view1.layout(i2, j, i2 + k, l1);
                j = l1 + layoutparams.bottomMargin;
            }
            i++;
        }
        if(mScrollIndicatorDrawable != null)
            if(view != null)
            {
                l = view.getLeft();
                j = view.getRight();
                i = view.getTop();
                k = mScrollIndicatorDrawable.getIntrinsicHeight();
                mScrollIndicatorDrawable.setBounds(l, i - k, j, i);
                setWillNotDraw(isCollapsed() ^ true);
            } else
            {
                mScrollIndicatorDrawable = null;
                setWillNotDraw(true);
            }
    }

    protected void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getSize(i);
        i = k;
        int l = android.view.View.MeasureSpec.getSize(j);
        if(mMaxWidth >= 0)
            i = Math.min(k, mMaxWidth);
        int i1 = android.view.View.MeasureSpec.makeMeasureSpec(i, 0x40000000);
        int j1 = android.view.View.MeasureSpec.makeMeasureSpec(l, 0x40000000);
        int k1 = getPaddingLeft() + getPaddingRight();
        i = getPaddingTop() + getPaddingBottom();
        int l1 = getChildCount();
        for(j = 0; j < l1;)
        {
            View view = getChildAt(j);
            int i2 = i;
            if(((LayoutParams)view.getLayoutParams()).alwaysShow)
            {
                i2 = i;
                if(view.getVisibility() != 8)
                {
                    measureChildWithMargins(view, i1, k1, j1, i);
                    i2 = i + view.getMeasuredHeight();
                }
            }
            j++;
            i = i2;
        }

        int j2 = 0;
        j = i;
        do
        {
            int k2 = j;
            if(j2 < l1)
            {
                View view1 = getChildAt(j2);
                j = k2;
                if(!((LayoutParams)view1.getLayoutParams()).alwaysShow)
                {
                    j = k2;
                    if(view1.getVisibility() != 8)
                    {
                        measureChildWithMargins(view1, i1, k1, j1, k2);
                        j = k2 + view1.getMeasuredHeight();
                    }
                }
                j2++;
            } else
            {
                j = mCollapsibleHeight;
                mCollapsibleHeight = Math.max(0, k2 - i - getMaxCollapsedHeight());
                mUncollapsibleHeight = k2 - mCollapsibleHeight;
                updateCollapseOffset(j, isDragging() ^ true);
                mTopOffset = Math.max(0, l - k2) + (int)mCollapseOffset;
                setMeasuredDimension(k, l);
                return;
            }
        } while(true);
    }

    public boolean onNestedFling(View view, float f, float f1, boolean flag)
    {
        int i = 0;
        if(!flag && Math.abs(f1) > mMinFlingVelocity)
        {
            if(isDismissable() && f1 < 0.0F && mCollapseOffset > (float)mCollapsibleHeight)
            {
                smoothScrollTo(mCollapsibleHeight + mUncollapsibleHeight, f1);
                mDismissOnScrollerFinished = true;
            } else
            {
                if(f1 <= 0.0F)
                    i = mCollapsibleHeight;
                smoothScrollTo(i, f1);
            }
            return true;
        } else
        {
            return false;
        }
    }

    public boolean onNestedPreFling(View view, float f, float f1)
    {
        if(f1 > mMinFlingVelocity && mCollapseOffset != 0.0F)
        {
            smoothScrollTo(0, f1);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean onNestedPrePerformAccessibilityAction(View view, int i, Bundle bundle)
    {
        if(super.onNestedPrePerformAccessibilityAction(view, i, bundle))
            return true;
        if(i == 4096 && mCollapseOffset != 0.0F)
        {
            smoothScrollTo(0, 0.0F);
            return true;
        } else
        {
            return false;
        }
    }

    public void onNestedPreScroll(View view, int i, int j, int ai[])
    {
        if(j > 0)
            ai[1] = (int)(-performDrag(-j));
    }

    public void onNestedScroll(View view, int i, int j, int k, int l)
    {
        if(l < 0)
            performDrag(-l);
    }

    public void onNestedScrollAccepted(View view, View view1, int i)
    {
        super.onNestedScrollAccepted(view, view1, i);
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        parcelable = (SavedState)parcelable;
        super.onRestoreInstanceState(parcelable.getSuperState());
        mOpenOnLayout = ((SavedState) (parcelable)).open;
    }

    protected Parcelable onSaveInstanceState()
    {
        boolean flag = false;
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        boolean flag1 = flag;
        if(mCollapsibleHeight > 0)
        {
            flag1 = flag;
            if(mCollapseOffset == 0.0F)
                flag1 = true;
        }
        savedstate.open = flag1;
        return savedstate;
    }

    public boolean onStartNestedScroll(View view, View view1, int i)
    {
        boolean flag = false;
        if((i & 2) != 0)
            flag = true;
        return flag;
    }

    public void onStopNestedScroll(View view)
    {
        super.onStopNestedScroll(view);
        if(mScroller.isFinished())
        {
            int i;
            if(mCollapseOffset < (float)(mCollapsibleHeight / 2))
                i = 0;
            else
                i = mCollapsibleHeight;
            smoothScrollTo(i, 0.0F);
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        i = motionevent.getActionMasked();
        mVelocityTracker.addMovement(motionevent);
        flag1 = false;
        flag2 = false;
        flag3 = flag2;
        i;
        JVM INSTR tableswitch 0 6: default 64
    //                   0 71
    //                   1 470
    //                   2 182
    //                   3 745
    //                   4 68
    //                   5 409
    //                   6 458;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L5:
        break MISSING_BLOCK_LABEL_745;
_L6:
        break; /* Loop/switch isn't completed */
_L1:
        flag3 = flag2;
_L10:
        return flag3;
_L2:
        float f = motionevent.getX();
        float f2 = motionevent.getY();
        mInitialTouchX = f;
        mLastTouchY = f2;
        mInitialTouchY = f2;
        mActivePointerId = motionevent.getPointerId(0);
        boolean flag;
        if(findChildUnder(mInitialTouchX, mInitialTouchY) != null)
            flag = true;
        else
            flag = false;
        if(isDismissable() || mCollapsibleHeight > 0)
            flag3 = true;
        else
            flag3 = false;
        if(flag)
            flag2 = flag3;
        else
            flag2 = false;
        mIsDragging = flag2;
        abortAnimation();
        continue; /* Loop/switch isn't completed */
_L4:
        int k1 = motionevent.findPointerIndex(mActivePointerId);
        int j = k1;
        if(k1 < 0)
        {
            Log.e("ResolverDrawerLayout", (new StringBuilder()).append("Bad pointer id ").append(mActivePointerId).append(", resetting").toString());
            j = 0;
            mActivePointerId = motionevent.getPointerId(0);
            mInitialTouchX = motionevent.getX();
            float f3 = motionevent.getY();
            mLastTouchY = f3;
            mInitialTouchY = f3;
        }
        float f7 = motionevent.getX(j);
        float f1 = motionevent.getY(j);
        flag3 = flag1;
        if(!mIsDragging)
        {
            float f4 = f1 - mInitialTouchY;
            flag3 = flag1;
            if(Math.abs(f4) > (float)mTouchSlop)
            {
                flag3 = flag1;
                if(findChildUnder(f7, f1) != null)
                {
                    mIsDragging = true;
                    flag3 = true;
                    mLastTouchY = Math.max(mLastTouchY - (float)mTouchSlop, Math.min(mLastTouchY + f4, mLastTouchY + (float)mTouchSlop));
                }
            }
        }
        if(mIsDragging)
            performDrag(f1 - mLastTouchY);
        mLastTouchY = f1;
        continue; /* Loop/switch isn't completed */
_L7:
        int k = motionevent.getActionIndex();
        mActivePointerId = motionevent.getPointerId(k);
        mInitialTouchX = motionevent.getX(k);
        float f5 = motionevent.getY(k);
        mLastTouchY = f5;
        mInitialTouchY = f5;
        flag3 = flag2;
        continue; /* Loop/switch isn't completed */
_L8:
        onSecondaryPointerUp(motionevent);
        flag3 = flag2;
        continue; /* Loop/switch isn't completed */
_L3:
        flag3 = mIsDragging;
        mIsDragging = false;
        if(!flag3 && findChildUnder(mInitialTouchX, mInitialTouchY) == null && findChildUnder(motionevent.getX(), motionevent.getY()) == null && isDismissable())
        {
            dispatchOnDismissed();
            resetTouch();
            return true;
        }
        if(mOpenOnClick && Math.abs(motionevent.getX() - mInitialTouchX) < (float)mTouchSlop && Math.abs(motionevent.getY() - mInitialTouchY) < (float)mTouchSlop)
        {
            smoothScrollTo(0, 0.0F);
            return true;
        }
        mVelocityTracker.computeCurrentVelocity(1000);
        float f6 = mVelocityTracker.getYVelocity(mActivePointerId);
        if(Math.abs(f6) <= mMinFlingVelocity)
            break; /* Loop/switch isn't completed */
        if(isDismissable() && f6 > 0.0F && mCollapseOffset > (float)mCollapsibleHeight)
        {
            smoothScrollTo(mCollapsibleHeight + mUncollapsibleHeight, f6);
            mDismissOnScrollerFinished = true;
        } else
        {
            int l;
            if(f6 < 0.0F)
                l = 0;
            else
                l = mCollapsibleHeight;
            smoothScrollTo(l, f6);
        }
_L11:
        resetTouch();
        flag3 = flag2;
        if(true) goto _L10; else goto _L9
_L9:
        int i1;
        if(mCollapseOffset < (float)(mCollapsibleHeight / 2))
            i1 = 0;
        else
            i1 = mCollapsibleHeight;
        smoothScrollTo(i1, 0.0F);
          goto _L11
        if(true) goto _L10; else goto _L12
_L12:
        if(mIsDragging)
        {
            int j1;
            if(mCollapseOffset < (float)(mCollapsibleHeight / 2))
                j1 = 0;
            else
                j1 = mCollapsibleHeight;
            smoothScrollTo(j1, 0.0F);
        }
        resetTouch();
        return true;
    }

    public boolean performAccessibilityActionInternal(int i, Bundle bundle)
    {
        if(i == android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_ACCESSIBILITY_FOCUS.getId())
            return false;
        if(super.performAccessibilityActionInternal(i, bundle))
            return true;
        if(i == 4096 && mCollapseOffset != 0.0F)
        {
            smoothScrollTo(0, 0.0F);
            return true;
        } else
        {
            return false;
        }
    }

    public void requestChildFocus(View view, View view1)
    {
        super.requestChildFocus(view, view1);
        if(!isInTouchMode() && isDescendantClipped(view1))
            smoothScrollTo(0, 0.0F);
    }

    public void setCollapsed(boolean flag)
    {
        if(!isLaidOut())
        {
            mOpenOnLayout = flag;
        } else
        {
            int i;
            if(flag)
                i = mCollapsibleHeight;
            else
                i = 0;
            smoothScrollTo(i, 0.0F);
        }
    }

    public void setCollapsibleHeightReserved(int i)
    {
        int j = mCollapsibleHeightReserved;
        mCollapsibleHeightReserved = i;
        i = mCollapsibleHeightReserved - j;
        if(i != 0 && mIsDragging)
            mLastTouchY = mLastTouchY - (float)i;
        i = mCollapsibleHeight;
        mCollapsibleHeight = Math.max(mCollapsibleHeight, getMaxCollapsedHeight());
        if(updateCollapseOffset(i, isDragging() ^ true))
        {
            return;
        } else
        {
            invalidate();
            return;
        }
    }

    public void setDismissLocked(boolean flag)
    {
        mDismissLocked = flag;
    }

    public void setOnDismissedListener(OnDismissedListener ondismissedlistener)
    {
        mOnDismissedListener = ondismissedlistener;
    }

    public void setSmallCollapsed(boolean flag)
    {
        mSmallCollapsed = flag;
        requestLayout();
    }

    private static final String TAG = "ResolverDrawerLayout";
    private int mActivePointerId;
    private float mCollapseOffset;
    private int mCollapsibleHeight;
    private int mCollapsibleHeightReserved;
    private boolean mDismissLocked;
    private boolean mDismissOnScrollerFinished;
    private float mInitialTouchX;
    private float mInitialTouchY;
    private boolean mIsDragging;
    private float mLastTouchY;
    private int mMaxCollapsedHeight;
    private int mMaxCollapsedHeightSmall;
    private int mMaxWidth;
    private final float mMinFlingVelocity;
    private OnDismissedListener mOnDismissedListener;
    private boolean mOpenOnClick;
    private boolean mOpenOnLayout;
    private RunOnDismissedListener mRunOnDismissedListener;
    private Drawable mScrollIndicatorDrawable;
    private final OverScroller mScroller;
    private boolean mSmallCollapsed;
    private final Rect mTempRect;
    private int mTopOffset;
    private final android.view.ViewTreeObserver.OnTouchModeChangeListener mTouchModeChangeListener;
    private final int mTouchSlop;
    private int mUncollapsibleHeight;
    private final VelocityTracker mVelocityTracker;
}

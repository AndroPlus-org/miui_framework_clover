// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.*;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AnimationUtils;
import java.util.List;

// Referenced classes of package android.widget:
//            FrameLayout, EdgeEffect, OverScroller

public class ScrollView extends FrameLayout
{
    static class SavedState extends android.view.View.BaseSavedState
    {

        public String toString()
        {
            return (new StringBuilder()).append("ScrollView.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" scrollPosition=").append(scrollPosition).append("}").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(scrollPosition);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel);
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
        public int scrollPosition;


        public SavedState(Parcel parcel)
        {
            super(parcel);
            scrollPosition = parcel.readInt();
        }

        SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }


    public ScrollView(Context context)
    {
        this(context, null);
    }

    public ScrollView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010080);
    }

    public ScrollView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public ScrollView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mTempRect = new Rect();
        mIsLayoutDirty = true;
        mChildToScrollTo = null;
        mIsBeingDragged = false;
        mSmoothScrollingEnabled = true;
        mActivePointerId = -1;
        mScrollOffset = new int[2];
        mScrollConsumed = new int[2];
        mScrollStrictSpan = null;
        mFlingStrictSpan = null;
        initScrollView();
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ScrollView, i, j);
        setFillViewport(attributeset.getBoolean(0, false));
        attributeset.recycle();
        if(context.getResources().getConfiguration().uiMode == 6)
            setRevealOnFocusHint(false);
    }

    private boolean canScroll()
    {
        boolean flag = false;
        View view = getChildAt(0);
        if(view != null)
        {
            int i = view.getHeight();
            if(getHeight() < mPaddingTop + i + mPaddingBottom)
                flag = true;
            return flag;
        } else
        {
            return false;
        }
    }

    private static int clamp(int i, int j, int k)
    {
        if(j >= k || i < 0)
            return 0;
        if(j + i > k)
            return k - j;
        else
            return i;
    }

    private void doScrollY(int i)
    {
        if(i != 0)
            if(mSmoothScrollingEnabled)
                smoothScrollBy(0, i);
            else
                scrollBy(0, i);
    }

    private void endDrag()
    {
        mIsBeingDragged = false;
        recycleVelocityTracker();
        if(mEdgeGlowTop != null)
        {
            mEdgeGlowTop.onRelease();
            mEdgeGlowBottom.onRelease();
        }
        if(mScrollStrictSpan != null)
        {
            mScrollStrictSpan.finish();
            mScrollStrictSpan = null;
        }
    }

    private View findFocusableViewInBounds(boolean flag, int i, int j)
    {
        java.util.ArrayList arraylist = getFocusables(2);
        View view = null;
        boolean flag1 = false;
        int k = arraylist.size();
        int l = 0;
        while(l < k) 
        {
            View view1 = (View)arraylist.get(l);
            int i1 = view1.getTop();
            int j1 = view1.getBottom();
            View view2 = view;
            boolean flag3 = flag1;
            if(i < j1)
            {
                view2 = view;
                flag3 = flag1;
                if(i1 < j)
                {
                    boolean flag4;
                    if(i < i1)
                    {
                        if(j1 < j)
                            flag4 = true;
                        else
                            flag4 = false;
                    } else
                    {
                        flag4 = false;
                    }
                    if(view == null)
                    {
                        view2 = view1;
                        flag3 = flag4;
                    } else
                    {
                        boolean flag2;
                        if(flag && i1 < view.getTop())
                            flag2 = true;
                        else
                        if(!flag && j1 > view.getBottom())
                            flag2 = true;
                        else
                            flag2 = false;
                        if(flag1)
                        {
                            view2 = view;
                            flag3 = flag1;
                            if(flag4)
                            {
                                view2 = view;
                                flag3 = flag1;
                                if(flag2)
                                {
                                    view2 = view1;
                                    flag3 = flag1;
                                }
                            }
                        } else
                        if(flag4)
                        {
                            view2 = view1;
                            flag3 = true;
                        } else
                        {
                            view2 = view;
                            flag3 = flag1;
                            if(flag2)
                            {
                                view2 = view1;
                                flag3 = flag1;
                            }
                        }
                    }
                }
            }
            l++;
            view = view2;
            flag1 = flag3;
        }
        return view;
    }

    private void flingWithNestedDispatch(int i)
    {
        boolean flag;
        if(mScrollY > 0 || i > 0)
        {
            if(mScrollY < getScrollRange() || i < 0)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        if(!dispatchNestedPreFling(0.0F, i))
        {
            dispatchNestedFling(0.0F, i, flag);
            if(flag)
                fling(i);
        }
    }

    private int getScrollRange()
    {
        int i = 0;
        if(getChildCount() > 0)
            i = Math.max(0, getChildAt(0).getHeight() - (getHeight() - mPaddingBottom - mPaddingTop));
        return i;
    }

    private boolean inChild(int i, int j)
    {
        boolean flag = false;
        if(getChildCount() > 0)
        {
            int k = mScrollY;
            View view = getChildAt(0);
            boolean flag1 = flag;
            if(j >= view.getTop() - k)
            {
                flag1 = flag;
                if(j < view.getBottom() - k)
                {
                    flag1 = flag;
                    if(i >= view.getLeft())
                    {
                        flag1 = flag;
                        if(i < view.getRight())
                            flag1 = true;
                    }
                }
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    private void initOrResetVelocityTracker()
    {
        if(mVelocityTracker == null)
            mVelocityTracker = VelocityTracker.obtain();
        else
            mVelocityTracker.clear();
    }

    private void initScrollView()
    {
        mScroller = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(0x40000);
        setWillNotDraw(false);
        ViewConfiguration viewconfiguration = ViewConfiguration.get(mContext);
        mTouchSlop = viewconfiguration.getScaledTouchSlop();
        mMinimumVelocity = viewconfiguration.getScaledMinimumFlingVelocity();
        mMaximumVelocity = viewconfiguration.getScaledMaximumFlingVelocity();
        mOverscrollDistance = viewconfiguration.getScaledOverscrollDistance();
        mOverflingDistance = viewconfiguration.getScaledOverflingDistance();
        mVerticalScrollFactor = viewconfiguration.getScaledVerticalScrollFactor();
    }

    private void initVelocityTrackerIfNotExists()
    {
        if(mVelocityTracker == null)
            mVelocityTracker = VelocityTracker.obtain();
    }

    private boolean isOffScreen(View view)
    {
        return isWithinDeltaOfScreen(view, 0, getHeight()) ^ true;
    }

    private static boolean isViewDescendantOf(View view, View view1)
    {
        if(view == view1)
            return true;
        view = view.getParent();
        boolean flag;
        if(view instanceof ViewGroup)
            flag = isViewDescendantOf((View)view, view1);
        else
            flag = false;
        return flag;
    }

    private boolean isWithinDeltaOfScreen(View view, int i, int j)
    {
        boolean flag = false;
        view.getDrawingRect(mTempRect);
        offsetDescendantRectToMyCoords(view, mTempRect);
        boolean flag1 = flag;
        if(mTempRect.bottom + i >= getScrollY())
        {
            flag1 = flag;
            if(mTempRect.top - i <= getScrollY() + j)
                flag1 = true;
        }
        return flag1;
    }

    private void onSecondaryPointerUp(MotionEvent motionevent)
    {
        int i = (motionevent.getAction() & 0xff00) >> 8;
        if(motionevent.getPointerId(i) == mActivePointerId)
        {
            if(i == 0)
                i = 1;
            else
                i = 0;
            mLastMotionY = (int)motionevent.getY(i);
            mActivePointerId = motionevent.getPointerId(i);
            if(mVelocityTracker != null)
                mVelocityTracker.clear();
        }
    }

    private void recycleVelocityTracker()
    {
        if(mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    private boolean scrollAndFocus(int i, int j, int k)
    {
        boolean flag = true;
        int l = getHeight();
        int i1 = getScrollY();
        l = i1 + l;
        boolean flag1;
        View view;
        Object obj;
        if(i == 33)
            flag1 = true;
        else
            flag1 = false;
        view = findFocusableViewInBounds(flag1, j, k);
        obj = view;
        if(view == null)
            obj = this;
        if(j >= i1 && k <= l)
        {
            flag1 = false;
        } else
        {
            if(flag1)
                j -= i1;
            else
                j = k - l;
            doScrollY(j);
            flag1 = flag;
        }
        if(obj != findFocus())
            ((View) (obj)).requestFocus(i);
        return flag1;
    }

    private void scrollToChild(View view)
    {
        view.getDrawingRect(mTempRect);
        offsetDescendantRectToMyCoords(view, mTempRect);
        int i = computeScrollDeltaToGetChildRectOnScreen(mTempRect);
        if(i != 0)
            scrollBy(0, i);
    }

    private boolean scrollToChildRect(Rect rect, boolean flag)
    {
        int i = computeScrollDeltaToGetChildRectOnScreen(rect);
        boolean flag1;
        if(i != 0)
            flag1 = true;
        else
            flag1 = false;
        if(flag1)
            if(flag)
                scrollBy(0, i);
            else
                smoothScrollBy(0, i);
        return flag1;
    }

    public void addView(View view)
    {
        if(getChildCount() > 0)
        {
            throw new IllegalStateException("ScrollView can host only one direct child");
        } else
        {
            super.addView(view);
            return;
        }
    }

    public void addView(View view, int i)
    {
        if(getChildCount() > 0)
        {
            throw new IllegalStateException("ScrollView can host only one direct child");
        } else
        {
            super.addView(view, i);
            return;
        }
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        if(getChildCount() > 0)
        {
            throw new IllegalStateException("ScrollView can host only one direct child");
        } else
        {
            super.addView(view, i, layoutparams);
            return;
        }
    }

    public void addView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        if(getChildCount() > 0)
        {
            throw new IllegalStateException("ScrollView can host only one direct child");
        } else
        {
            super.addView(view, layoutparams);
            return;
        }
    }

    public boolean arrowScroll(int i)
    {
        View view;
        View view1;
        int j;
        view = findFocus();
        view1 = view;
        if(view == this)
            view1 = null;
        view = FocusFinder.getInstance().findNextFocus(this, view1, i);
        j = getMaxScrollAmount();
        if(view == null || !isWithinDeltaOfScreen(view, j, getHeight())) goto _L2; else goto _L1
_L1:
        view.getDrawingRect(mTempRect);
        offsetDescendantRectToMyCoords(view, mTempRect);
        doScrollY(computeScrollDeltaToGetChildRectOnScreen(mTempRect));
        view.requestFocus(i);
_L8:
        if(view1 != null && view1.isFocused() && isOffScreen(view1))
        {
            i = getDescendantFocusability();
            setDescendantFocusability(0x20000);
            requestFocus();
            setDescendantFocusability(i);
        }
        return true;
_L2:
        int k = j;
        if(i != 33 || getScrollY() >= j) goto _L4; else goto _L3
_L3:
        int l = getScrollY();
_L6:
        if(l == 0)
            return false;
        break; /* Loop/switch isn't completed */
_L4:
        l = k;
        if(i == 130)
        {
            l = k;
            if(getChildCount() > 0)
            {
                int i1 = getChildAt(0).getBottom();
                int j1 = (getScrollY() + getHeight()) - mPaddingBottom;
                l = k;
                if(i1 - j1 < j)
                    l = i1 - j1;
            }
        }
        if(true) goto _L6; else goto _L5
_L5:
        if(i != 130)
            l = -l;
        doScrollY(l);
        if(true) goto _L8; else goto _L7
_L7:
    }

    public void computeScroll()
    {
        if(mScroller.computeScrollOffset())
        {
            int i = mScrollX;
            int j = mScrollY;
            int k = mScroller.getCurrX();
            int l = mScroller.getCurrY();
            if(i != k || j != l)
            {
                int i1 = getScrollRange();
                int j1 = getOverScrollMode();
                boolean flag;
                if(j1 != 0)
                {
                    if(j1 == 1 && i1 > 0)
                        flag = true;
                    else
                        flag = false;
                } else
                {
                    flag = true;
                }
                overScrollBy(k - i, l - j, i, j, 0, i1, 0, mOverflingDistance, false);
                onScrollChanged(mScrollX, mScrollY, i, j);
                if(flag)
                    if(l < 0 && j >= 0)
                        mEdgeGlowTop.onAbsorb((int)mScroller.getCurrVelocity());
                    else
                    if(l > i1 && j <= i1)
                        mEdgeGlowBottom.onAbsorb((int)mScroller.getCurrVelocity());
            }
            if(!awakenScrollBars())
                postInvalidateOnAnimation();
        } else
        if(mFlingStrictSpan != null)
        {
            mFlingStrictSpan.finish();
            mFlingStrictSpan = null;
        }
    }

    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect)
    {
        int i;
        int j;
        int i1;
        int j1;
        if(getChildCount() == 0)
            return 0;
        i = getHeight();
        j = getScrollY();
        int k = j + i;
        i1 = getVerticalFadingEdgeLength();
        j1 = j;
        if(rect.top > 0)
            j1 = j + i1;
        j = k;
        if(rect.bottom < getChildAt(0).getHeight())
            j = k - i1;
        i1 = 0;
        if(rect.bottom <= j || rect.top <= j1) goto _L2; else goto _L1
_L1:
        int l;
        if(rect.height() > i)
            l = (rect.top - j1) + 0;
        else
            l = (rect.bottom - j) + 0;
        l = Math.min(l, getChildAt(0).getBottom() - j);
_L4:
        return l;
_L2:
        l = i1;
        if(rect.top < j1)
        {
            l = i1;
            if(rect.bottom < j)
            {
                if(rect.height() > i)
                    l = 0 - (j - rect.bottom);
                else
                    l = 0 - (j1 - rect.top);
                l = Math.max(l, -getScrollY());
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected int computeVerticalScrollOffset()
    {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    protected int computeVerticalScrollRange()
    {
        int i;
        int l;
        int i1;
        i = getChildCount();
        int j = getHeight() - mPaddingBottom - mPaddingTop;
        if(i == 0)
            return j;
        i = getChildAt(0).getBottom();
        l = mScrollY;
        i1 = Math.max(0, i - j);
        if(l >= 0) goto _L2; else goto _L1
_L1:
        int k = i - l;
_L4:
        return k;
_L2:
        k = i;
        if(l > i1)
            k = i + (l - i1);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        boolean flag;
        if(!super.dispatchKeyEvent(keyevent))
            flag = executeKeyEvent(keyevent);
        else
            flag = true;
        return flag;
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        if(mEdgeGlowTop != null)
        {
            int i = mScrollY;
            boolean flag = getClipToPadding();
            int k;
            int l;
            float f;
            float f1;
            if(!mEdgeGlowTop.isFinished())
            {
                int j = canvas.save();
                if(flag)
                {
                    k = getWidth() - mPaddingLeft - mPaddingRight;
                    l = getHeight() - mPaddingTop - mPaddingBottom;
                    f = mPaddingLeft;
                    f1 = mPaddingTop;
                } else
                {
                    k = getWidth();
                    l = getHeight();
                    f = 0.0F;
                    f1 = 0.0F;
                }
                canvas.translate(f, (float)Math.min(0, i) + f1);
                mEdgeGlowTop.setSize(k, l);
                if(mEdgeGlowTop.draw(canvas))
                    postInvalidateOnAnimation();
                canvas.restoreToCount(j);
            }
            if(!mEdgeGlowBottom.isFinished())
            {
                j = canvas.save();
                if(flag)
                {
                    l = getWidth() - mPaddingLeft - mPaddingRight;
                    k = getHeight() - mPaddingTop - mPaddingBottom;
                    f1 = mPaddingLeft;
                    f = mPaddingTop;
                } else
                {
                    l = getWidth();
                    k = getHeight();
                    f1 = 0.0F;
                    f = 0.0F;
                }
                canvas.translate((float)(-l) + f1, (float)(Math.max(getScrollRange(), i) + k) + f);
                canvas.rotate(180F, l, 0.0F);
                mEdgeGlowBottom.setSize(l, k);
                if(mEdgeGlowBottom.draw(canvas))
                    postInvalidateOnAnimation();
                canvas.restoreToCount(j);
            }
        }
    }

    protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
    {
        super.encodeProperties(viewhierarchyencoder);
        viewhierarchyencoder.addProperty("fillViewport", mFillViewport);
    }

    public boolean executeKeyEvent(KeyEvent keyevent)
    {
        boolean flag;
        boolean flag2;
        flag = false;
        mTempRect.setEmpty();
        if(!canScroll())
            if(isFocused() && keyevent.getKeyCode() != 4)
            {
                View view = findFocus();
                keyevent = view;
                if(view == this)
                    keyevent = null;
                keyevent = FocusFinder.getInstance().findNextFocus(this, keyevent, 130);
                boolean flag1 = flag;
                if(keyevent != null)
                {
                    flag1 = flag;
                    if(keyevent != this)
                        flag1 = keyevent.requestFocus(130);
                }
                return flag1;
            } else
            {
                return false;
            }
        flag = false;
        flag2 = flag;
        if(keyevent.getAction() != 0) goto _L2; else goto _L1
_L1:
        keyevent.getKeyCode();
        JVM INSTR lookupswitch 3: default 136
    //                   19: 142
    //                   20: 171
    //                   62: 202;
           goto _L3 _L4 _L5 _L6
_L3:
        flag2 = flag;
_L2:
        return flag2;
_L4:
        if(!keyevent.isAltPressed())
            flag2 = arrowScroll(33);
        else
            flag2 = fullScroll(33);
        continue; /* Loop/switch isn't completed */
_L5:
        if(!keyevent.isAltPressed())
            flag2 = arrowScroll(130);
        else
            flag2 = fullScroll(130);
        continue; /* Loop/switch isn't completed */
_L6:
        char c;
        if(keyevent.isShiftPressed())
            c = '!';
        else
            c = '\202';
        pageScroll(c);
        flag2 = flag;
        if(true) goto _L2; else goto _L7
_L7:
    }

    public void fling(int i)
    {
        if(getChildCount() > 0)
        {
            int j = getHeight() - mPaddingBottom - mPaddingTop;
            int k = getChildAt(0).getHeight();
            mScroller.fling(mScrollX, mScrollY, 0, i, 0, 0, 0, Math.max(0, k - j), 0, j / 2);
            if(mFlingStrictSpan == null)
                mFlingStrictSpan = StrictMode.enterCriticalSpan("ScrollView-fling");
            postInvalidateOnAnimation();
        }
    }

    public boolean fullScroll(int i)
    {
        int j;
        int k;
        if(i == 130)
            j = 1;
        else
            j = 0;
        k = getHeight();
        mTempRect.top = 0;
        mTempRect.bottom = k;
        if(j != 0)
        {
            j = getChildCount();
            if(j > 0)
            {
                View view = getChildAt(j - 1);
                mTempRect.bottom = view.getBottom() + mPaddingBottom;
                mTempRect.top = mTempRect.bottom - k;
            }
        }
        return scrollAndFocus(i, mTempRect.top, mTempRect.bottom);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/ScrollView.getName();
    }

    protected float getBottomFadingEdgeStrength()
    {
        if(getChildCount() == 0)
            return 0.0F;
        int i = getVerticalFadingEdgeLength();
        int j = getHeight();
        int k = mPaddingBottom;
        j = getChildAt(0).getBottom() - mScrollY - (j - k);
        if(j < i)
            return (float)j / (float)i;
        else
            return 1.0F;
    }

    public int getMaxScrollAmount()
    {
        return (int)((float)(mBottom - mTop) * 0.5F);
    }

    protected float getTopFadingEdgeStrength()
    {
        if(getChildCount() == 0)
            return 0.0F;
        int i = getVerticalFadingEdgeLength();
        if(mScrollY < i)
            return (float)mScrollY / (float)i;
        else
            return 1.0F;
    }

    public boolean isFillViewport()
    {
        return mFillViewport;
    }

    public boolean isSmoothScrollingEnabled()
    {
        return mSmoothScrollingEnabled;
    }

    protected void measureChild(View view, int i, int j)
    {
        android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
        int k = getChildMeasureSpec(i, mPaddingLeft + mPaddingRight, layoutparams.width);
        i = mPaddingTop;
        int l = mPaddingBottom;
        view.measure(k, android.view.View.MeasureSpec.makeSafeMeasureSpec(Math.max(0, android.view.View.MeasureSpec.getSize(j) - (i + l)), 0));
    }

    protected void measureChildWithMargins(View view, int i, int j, int k, int l)
    {
        android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)view.getLayoutParams();
        int i1 = getChildMeasureSpec(i, mPaddingLeft + mPaddingRight + marginlayoutparams.leftMargin + marginlayoutparams.rightMargin + j, marginlayoutparams.width);
        i = mPaddingTop;
        int j1 = mPaddingBottom;
        int k1 = marginlayoutparams.topMargin;
        j = marginlayoutparams.bottomMargin;
        view.measure(i1, android.view.View.MeasureSpec.makeSafeMeasureSpec(Math.max(0, android.view.View.MeasureSpec.getSize(k) - (i + j1 + k1 + j + l)), 0));
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if(mScrollStrictSpan != null)
        {
            mScrollStrictSpan.finish();
            mScrollStrictSpan = null;
        }
        if(mFlingStrictSpan != null)
        {
            mFlingStrictSpan.finish();
            mFlingStrictSpan = null;
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionevent)
    {
        motionevent.getAction();
        JVM INSTR tableswitch 8 8: default 24
    //                   8 30;
           goto _L1 _L2
_L1:
        return super.onGenericMotionEvent(motionevent);
_L2:
        int i;
        int j;
        int l;
        float f;
        int k;
        if(motionevent.isFromSource(2))
            f = motionevent.getAxisValue(9);
        else
        if(motionevent.isFromSource(0x400000))
            f = motionevent.getAxisValue(26);
        else
            f = 0.0F;
        i = Math.round(mVerticalScrollFactor * f);
        if(i == 0)
            continue; /* Loop/switch isn't completed */
        j = getScrollRange();
        k = mScrollY;
        l = k - i;
        if(l >= 0)
            break; /* Loop/switch isn't completed */
        i = 0;
_L4:
        if(i != k)
        {
            super.scrollTo(mScrollX, i);
            return true;
        }
        if(true) goto _L1; else goto _L3
_L3:
        i = l;
        if(l > j)
            i = j;
          goto _L4
        if(true) goto _L1; else goto _L5
_L5:
    }

    public void onInitializeAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEventInternal(accessibilityevent);
        boolean flag;
        if(getScrollRange() > 0)
            flag = true;
        else
            flag = false;
        accessibilityevent.setScrollable(flag);
        accessibilityevent.setScrollX(mScrollX);
        accessibilityevent.setScrollY(mScrollY);
        accessibilityevent.setMaxScrollX(mScrollX);
        accessibilityevent.setMaxScrollY(getScrollRange());
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        if(isEnabled())
        {
            int i = getScrollRange();
            if(i > 0)
            {
                accessibilitynodeinfo.setScrollable(true);
                if(mScrollY > 0)
                {
                    accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
                    accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP);
                }
                if(mScrollY < i)
                {
                    accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
                    accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN);
                }
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        int i;
        i = motionevent.getAction();
        if(i == 2 && mIsBeingDragged)
            return true;
        if(super.onInterceptTouchEvent(motionevent))
            return true;
        if(getScrollY() == 0 && canScrollVertically(1) ^ true)
            return false;
        i & 0xff;
        JVM INSTR tableswitch 0 6: default 96
    //                   0 252
    //                   1 363
    //                   2 101
    //                   3 363
    //                   4 96
    //                   5 96
    //                   6 413;
           goto _L1 _L2 _L3 _L4 _L3 _L1 _L1 _L5
_L1:
        return mIsBeingDragged;
_L4:
        int l = mActivePointerId;
        if(l != -1)
        {
            int j = motionevent.findPointerIndex(l);
            if(j == -1)
            {
                Log.e("ScrollView", (new StringBuilder()).append("Invalid pointerId=").append(l).append(" in onInterceptTouchEvent").toString());
            } else
            {
                j = (int)motionevent.getY(j);
                if(Math.abs(j - mLastMotionY) > mTouchSlop && (getNestedScrollAxes() & 2) == 0)
                {
                    mIsBeingDragged = true;
                    mLastMotionY = j;
                    initVelocityTrackerIfNotExists();
                    mVelocityTracker.addMovement(motionevent);
                    mNestedYOffset = 0;
                    if(mScrollStrictSpan == null)
                        mScrollStrictSpan = StrictMode.enterCriticalSpan("ScrollView-scroll");
                    motionevent = getParent();
                    if(motionevent != null)
                        motionevent.requestDisallowInterceptTouchEvent(true);
                }
            }
        }
        continue; /* Loop/switch isn't completed */
_L2:
        int k = (int)motionevent.getY();
        if(!inChild((int)motionevent.getX(), k))
        {
            mIsBeingDragged = false;
            recycleVelocityTracker();
        } else
        {
            mLastMotionY = k;
            mActivePointerId = motionevent.getPointerId(0);
            initOrResetVelocityTracker();
            mVelocityTracker.addMovement(motionevent);
            mScroller.computeScrollOffset();
            mIsBeingDragged = mScroller.isFinished() ^ true;
            if(mIsBeingDragged && mScrollStrictSpan == null)
                mScrollStrictSpan = StrictMode.enterCriticalSpan("ScrollView-scroll");
            startNestedScroll(2);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        mIsBeingDragged = false;
        mActivePointerId = -1;
        recycleVelocityTracker();
        if(mScroller.springBack(mScrollX, mScrollY, 0, 0, 0, getScrollRange()))
            postInvalidateOnAnimation();
        stopNestedScroll();
        continue; /* Loop/switch isn't completed */
_L5:
        onSecondaryPointerUp(motionevent);
        if(true) goto _L1; else goto _L6
_L6:
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        mIsLayoutDirty = false;
        if(mChildToScrollTo != null && isViewDescendantOf(mChildToScrollTo, this))
            scrollToChild(mChildToScrollTo);
        mChildToScrollTo = null;
        if(isLaidOut()) goto _L2; else goto _L1
_L1:
        if(mSavedState != null)
        {
            mScrollY = mSavedState.scrollPosition;
            mSavedState = null;
        }
        if(getChildCount() > 0)
            i = getChildAt(0).getMeasuredHeight();
        else
            i = 0;
        i = Math.max(0, i - (l - j - mPaddingBottom - mPaddingTop));
        if(mScrollY <= i) goto _L4; else goto _L3
_L3:
        mScrollY = i;
_L2:
        scrollTo(mScrollX, mScrollY);
        return;
_L4:
        if(mScrollY < 0)
            mScrollY = 0;
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        if(!mFillViewport)
            return;
        if(android.view.View.MeasureSpec.getMode(j) == 0)
            return;
        if(getChildCount() > 0)
        {
            View view = getChildAt(0);
            j = getContext().getApplicationInfo().targetSdkVersion;
            FrameLayout.LayoutParams layoutparams = (FrameLayout.LayoutParams)view.getLayoutParams();
            int k;
            if(j >= 23)
            {
                j = mPaddingLeft + mPaddingRight + layoutparams.leftMargin + layoutparams.rightMargin;
                k = mPaddingTop + mPaddingBottom + layoutparams.topMargin + layoutparams.bottomMargin;
            } else
            {
                j = mPaddingLeft + mPaddingRight;
                k = mPaddingTop + mPaddingBottom;
            }
            k = getMeasuredHeight() - k;
            if(view.getMeasuredHeight() < k)
                view.measure(getChildMeasureSpec(i, j, layoutparams.width), android.view.View.MeasureSpec.makeMeasureSpec(k, 0x40000000));
        }
    }

    public boolean onNestedFling(View view, float f, float f1, boolean flag)
    {
        if(!flag)
        {
            flingWithNestedDispatch((int)f1);
            return true;
        } else
        {
            return false;
        }
    }

    public void onNestedScroll(View view, int i, int j, int k, int l)
    {
        i = mScrollY;
        scrollBy(0, l);
        i = mScrollY - i;
        dispatchNestedScroll(0, i, 0, l - i, null);
    }

    public void onNestedScrollAccepted(View view, View view1, int i)
    {
        super.onNestedScrollAccepted(view, view1, i);
        startNestedScroll(2);
    }

    protected void onOverScrolled(int i, int j, boolean flag, boolean flag1)
    {
        if(!mScroller.isFinished())
        {
            int k = mScrollX;
            int l = mScrollY;
            mScrollX = i;
            mScrollY = j;
            invalidateParentIfNeeded();
            onScrollChanged(mScrollX, mScrollY, k, l);
            if(flag1)
                mScroller.springBack(mScrollX, mScrollY, 0, 0, 0, getScrollRange());
        } else
        {
            super.scrollTo(i, j);
        }
        awakenScrollBars();
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect)
    {
        int j;
        View view;
        if(i == 2)
        {
            j = 130;
        } else
        {
            j = i;
            if(i == 1)
                j = 33;
        }
        if(rect == null)
            view = FocusFinder.getInstance().findNextFocus(this, null, j);
        else
            view = FocusFinder.getInstance().findNextFocusFromRect(this, rect, j);
        if(view == null)
            return false;
        if(isOffScreen(view))
            return false;
        else
            return view.requestFocus(j, rect);
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if(mContext.getApplicationInfo().targetSdkVersion <= 18)
        {
            super.onRestoreInstanceState(parcelable);
            return;
        } else
        {
            parcelable = (SavedState)parcelable;
            super.onRestoreInstanceState(parcelable.getSuperState());
            mSavedState = parcelable;
            requestLayout();
            return;
        }
    }

    protected Parcelable onSaveInstanceState()
    {
        if(mContext.getApplicationInfo().targetSdkVersion <= 18)
        {
            return super.onSaveInstanceState();
        } else
        {
            SavedState savedstate = new SavedState(super.onSaveInstanceState());
            savedstate.scrollPosition = mScrollY;
            return savedstate;
        }
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        View view = findFocus();
        if(view == null || this == view)
            return;
        if(isWithinDeltaOfScreen(view, 0, l))
        {
            view.getDrawingRect(mTempRect);
            offsetDescendantRectToMyCoords(view, mTempRect);
            doScrollY(computeScrollDeltaToGetChildRectOnScreen(mTempRect));
        }
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
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        MotionEvent motionevent1;
        int i;
        initVelocityTrackerIfNotExists();
        motionevent1 = MotionEvent.obtain(motionevent);
        i = motionevent.getActionMasked();
        if(i == 0)
            mNestedYOffset = 0;
        motionevent1.offsetLocation(0.0F, mNestedYOffset);
        i;
        JVM INSTR tableswitch 0 6: default 76
    //                   0 97
    //                   1 744
    //                   2 210
    //                   3 839
    //                   4 76
    //                   5 894
    //                   6 921;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L6 _L7
_L1:
        if(mVelocityTracker != null)
            mVelocityTracker.addMovement(motionevent1);
        motionevent1.recycle();
        return true;
_L2:
        if(getChildCount() == 0)
            return false;
        boolean flag = mScroller.isFinished() ^ true;
        mIsBeingDragged = flag;
        if(flag)
        {
            ViewParent viewparent = getParent();
            if(viewparent != null)
                viewparent.requestDisallowInterceptTouchEvent(true);
        }
        if(!mScroller.isFinished())
        {
            mScroller.abortAnimation();
            if(mFlingStrictSpan != null)
            {
                mFlingStrictSpan.finish();
                mFlingStrictSpan = null;
            }
        }
        mLastMotionY = (int)motionevent.getY();
        mActivePointerId = motionevent.getPointerId(0);
        startNestedScroll(2);
        continue; /* Loop/switch isn't completed */
_L4:
        int k;
        int l;
        int i1;
        k = motionevent.findPointerIndex(mActivePointerId);
        if(k == -1)
        {
            Log.e("ScrollView", (new StringBuilder()).append("Invalid pointerId=").append(mActivePointerId).append(" in onTouchEvent").toString());
            continue; /* Loop/switch isn't completed */
        }
        l = (int)motionevent.getY(k);
        i = mLastMotionY - l;
        i1 = i;
        if(dispatchNestedPreScroll(0, i, mScrollConsumed, mScrollOffset))
        {
            i1 = i - mScrollConsumed[1];
            motionevent1.offsetLocation(0.0F, mScrollOffset[1]);
            mNestedYOffset = mNestedYOffset + mScrollOffset[1];
        }
        i = i1;
        int j1;
        if(!mIsBeingDragged)
        {
            i = i1;
            if(Math.abs(i1) > mTouchSlop)
            {
                ViewParent viewparent1 = getParent();
                if(viewparent1 != null)
                    viewparent1.requestDisallowInterceptTouchEvent(true);
                mIsBeingDragged = true;
                int k1;
                if(i1 > 0)
                    i = i1 - mTouchSlop;
                else
                    i = i1 + mTouchSlop;
            }
        }
        if(!mIsBeingDragged)
            continue; /* Loop/switch isn't completed */
        mLastMotionY = l - mScrollOffset[1];
        j1 = mScrollY;
        l = getScrollRange();
        i1 = getOverScrollMode();
        if(i1 != 0)
        {
            if(i1 == 1 && l > 0)
                i1 = 1;
            else
                i1 = 0;
        } else
        {
            i1 = 1;
        }
        if(overScrollBy(0, i, 0, mScrollY, 0, l, 0, mOverscrollDistance, true) && hasNestedScrollingParent() ^ true)
            mVelocityTracker.clear();
        k1 = mScrollY - j1;
        if(dispatchNestedScroll(0, k1, 0, i - k1, mScrollOffset))
        {
            mLastMotionY = mLastMotionY - mScrollOffset[1];
            motionevent1.offsetLocation(0.0F, mScrollOffset[1]);
            mNestedYOffset = mNestedYOffset + mScrollOffset[1];
            continue; /* Loop/switch isn't completed */
        }
        if(i1 == 0)
            continue; /* Loop/switch isn't completed */
        i1 = j1 + i;
        if(i1 >= 0) goto _L9; else goto _L8
_L8:
        mEdgeGlowTop.onPull((float)i / (float)getHeight(), motionevent.getX(k) / (float)getWidth());
        if(!mEdgeGlowBottom.isFinished())
            mEdgeGlowBottom.onRelease();
_L10:
        if(mEdgeGlowTop != null && (!mEdgeGlowTop.isFinished() || mEdgeGlowBottom.isFinished() ^ true))
            postInvalidateOnAnimation();
        continue; /* Loop/switch isn't completed */
_L9:
        if(i1 > l)
        {
            mEdgeGlowBottom.onPull((float)i / (float)getHeight(), 1.0F - motionevent.getX(k) / (float)getWidth());
            if(!mEdgeGlowTop.isFinished())
                mEdgeGlowTop.onRelease();
        }
        if(true) goto _L10; else goto _L3
_L3:
        if(!mIsBeingDragged)
            continue; /* Loop/switch isn't completed */
        motionevent = mVelocityTracker;
        motionevent.computeCurrentVelocity(1000, mMaximumVelocity);
        i = (int)motionevent.getYVelocity(mActivePointerId);
        if(Math.abs(i) <= mMinimumVelocity) goto _L12; else goto _L11
_L11:
        flingWithNestedDispatch(-i);
_L13:
        mActivePointerId = -1;
        endDrag();
        continue; /* Loop/switch isn't completed */
_L12:
        if(mScroller.springBack(mScrollX, mScrollY, 0, 0, 0, getScrollRange()))
            postInvalidateOnAnimation();
        if(true) goto _L13; else goto _L5
_L5:
        if(mIsBeingDragged && getChildCount() > 0)
        {
            if(mScroller.springBack(mScrollX, mScrollY, 0, 0, 0, getScrollRange()))
                postInvalidateOnAnimation();
            mActivePointerId = -1;
            endDrag();
        }
        continue; /* Loop/switch isn't completed */
_L6:
        int j = motionevent.getActionIndex();
        mLastMotionY = (int)motionevent.getY(j);
        mActivePointerId = motionevent.getPointerId(j);
        continue; /* Loop/switch isn't completed */
_L7:
        onSecondaryPointerUp(motionevent);
        mLastMotionY = (int)motionevent.getY(motionevent.findPointerIndex(mActivePointerId));
        if(true) goto _L1; else goto _L14
_L14:
    }

    public boolean pageScroll(int i)
    {
        int k;
        int j;
        View view;
        if(i == 130)
            j = 1;
        else
            j = 0;
        k = getHeight();
        if(j == 0) goto _L2; else goto _L1
_L1:
        mTempRect.top = getScrollY() + k;
        j = getChildCount();
        if(j > 0)
        {
            view = getChildAt(j - 1);
            if(mTempRect.top + k > view.getBottom())
                mTempRect.top = view.getBottom() - k;
        }
_L4:
        mTempRect.bottom = mTempRect.top + k;
        return scrollAndFocus(i, mTempRect.top, mTempRect.bottom);
_L2:
        mTempRect.top = getScrollY() - k;
        if(mTempRect.top < 0)
            mTempRect.top = 0;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean performAccessibilityActionInternal(int i, Bundle bundle)
    {
        if(super.performAccessibilityActionInternal(i, bundle))
            return true;
        if(!isEnabled())
            return false;
        switch(i)
        {
        default:
            return false;

        case 4096: 
        case 16908346: 
            int j = getHeight();
            int l = mPaddingBottom;
            i = mPaddingTop;
            i = Math.min(mScrollY + (j - l - i), getScrollRange());
            if(i != mScrollY)
            {
                smoothScrollTo(0, i);
                return true;
            } else
            {
                return false;
            }

        case 8192: 
        case 16908344: 
            int i1 = getHeight();
            i = mPaddingBottom;
            int k = mPaddingTop;
            i = Math.max(mScrollY - (i1 - i - k), 0);
            break;
        }
        if(i != mScrollY)
        {
            smoothScrollTo(0, i);
            return true;
        } else
        {
            return false;
        }
    }

    public void requestChildFocus(View view, View view1)
    {
        if(view1 != null && view1.getRevealOnFocusHint())
            if(!mIsLayoutDirty)
                scrollToChild(view1);
            else
                mChildToScrollTo = view1;
        super.requestChildFocus(view, view1);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean flag)
    {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        return scrollToChildRect(rect, flag);
    }

    public void requestDisallowInterceptTouchEvent(boolean flag)
    {
        if(flag)
            recycleVelocityTracker();
        super.requestDisallowInterceptTouchEvent(flag);
    }

    public void requestLayout()
    {
        mIsLayoutDirty = true;
        super.requestLayout();
    }

    public void scrollTo(int i, int j)
    {
        if(getChildCount() > 0)
        {
            View view = getChildAt(0);
            i = clamp(i, getWidth() - mPaddingRight - mPaddingLeft, view.getWidth());
            j = clamp(j, getHeight() - mPaddingBottom - mPaddingTop, view.getHeight());
            if(i != mScrollX || j != mScrollY)
                super.scrollTo(i, j);
        }
    }

    public void setFillViewport(boolean flag)
    {
        if(flag != mFillViewport)
        {
            mFillViewport = flag;
            requestLayout();
        }
    }

    public void setOverScrollMode(int i)
    {
        if(i != 2)
        {
            if(mEdgeGlowTop == null)
            {
                Context context = getContext();
                mEdgeGlowTop = new EdgeEffect(context);
                mEdgeGlowBottom = new EdgeEffect(context);
            }
        } else
        {
            mEdgeGlowTop = null;
            mEdgeGlowBottom = null;
        }
        super.setOverScrollMode(i);
    }

    public void setSmoothScrollingEnabled(boolean flag)
    {
        mSmoothScrollingEnabled = flag;
    }

    public boolean shouldDelayChildPressedState()
    {
        return true;
    }

    public final void smoothScrollBy(int i, int j)
    {
        if(getChildCount() == 0)
            return;
        if(AnimationUtils.currentAnimationTimeMillis() - mLastScroll > 250L)
        {
            int k = getHeight();
            i = mPaddingBottom;
            int l = mPaddingTop;
            k = Math.max(0, getChildAt(0).getHeight() - (k - i - l));
            i = mScrollY;
            j = Math.max(0, Math.min(i + j, k));
            mScroller.startScroll(mScrollX, i, 0, j - i);
            postInvalidateOnAnimation();
        } else
        {
            if(!mScroller.isFinished())
            {
                mScroller.abortAnimation();
                if(mFlingStrictSpan != null)
                {
                    mFlingStrictSpan.finish();
                    mFlingStrictSpan = null;
                }
            }
            scrollBy(i, j);
        }
        mLastScroll = AnimationUtils.currentAnimationTimeMillis();
    }

    public final void smoothScrollTo(int i, int j)
    {
        smoothScrollBy(i - mScrollX, j - mScrollY);
    }

    static final int ANIMATED_SCROLL_GAP = 250;
    private static final int INVALID_POINTER = -1;
    static final float MAX_SCROLL_FACTOR = 0.5F;
    private static final String TAG = "ScrollView";
    private int mActivePointerId;
    private View mChildToScrollTo;
    private EdgeEffect mEdgeGlowBottom;
    private EdgeEffect mEdgeGlowTop;
    private boolean mFillViewport;
    private android.os.StrictMode.Span mFlingStrictSpan;
    private boolean mIsBeingDragged;
    private boolean mIsLayoutDirty;
    private int mLastMotionY;
    private long mLastScroll;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private int mNestedYOffset;
    private int mOverflingDistance;
    private int mOverscrollDistance;
    private SavedState mSavedState;
    private final int mScrollConsumed[];
    private final int mScrollOffset[];
    private android.os.StrictMode.Span mScrollStrictSpan;
    private OverScroller mScroller;
    private boolean mSmoothScrollingEnabled;
    private final Rect mTempRect;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private float mVerticalScrollFactor;
}

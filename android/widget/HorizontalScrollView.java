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
//            FrameLayout, OverScroller, EdgeEffect

public class HorizontalScrollView extends FrameLayout
{
    static class SavedState extends android.view.View.BaseSavedState
    {

        public String toString()
        {
            return (new StringBuilder()).append("HorizontalScrollView.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" scrollPosition=").append(scrollOffsetFromStart).append("}").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(scrollOffsetFromStart);
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
        public int scrollOffsetFromStart;


        public SavedState(Parcel parcel)
        {
            super(parcel);
            scrollOffsetFromStart = parcel.readInt();
        }

        SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }


    public HorizontalScrollView(Context context)
    {
        this(context, null);
    }

    public HorizontalScrollView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010353);
    }

    public HorizontalScrollView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public HorizontalScrollView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mTempRect = new Rect();
        mIsLayoutDirty = true;
        mChildToScrollTo = null;
        mIsBeingDragged = false;
        mSmoothScrollingEnabled = true;
        mActivePointerId = -1;
        initScrollView();
        attributeset = context.obtainStyledAttributes(attributeset, android.R.styleable.HorizontalScrollView, i, j);
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
            int i = view.getWidth();
            if(getWidth() < mPaddingLeft + i + mPaddingRight)
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

    private void doScrollX(int i)
    {
        if(i != 0)
            if(mSmoothScrollingEnabled)
                smoothScrollBy(i, 0);
            else
                scrollBy(i, 0);
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
            int i1 = view1.getLeft();
            int j1 = view1.getRight();
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
                        if(flag && i1 < view.getLeft())
                            flag2 = true;
                        else
                        if(!flag && j1 > view.getRight())
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

    private View findFocusableViewInMyBounds(boolean flag, int i, View view)
    {
        int j = getHorizontalFadingEdgeLength() / 2;
        int k = i + j;
        i = (getWidth() + i) - j;
        if(view != null && view.getLeft() < i && view.getRight() > k)
            return view;
        else
            return findFocusableViewInBounds(flag, k, i);
    }

    private int getScrollRange()
    {
        int i = 0;
        if(getChildCount() > 0)
            i = Math.max(0, getChildAt(0).getWidth() - (getWidth() - mPaddingLeft - mPaddingRight));
        return i;
    }

    private boolean inChild(int i, int j)
    {
        boolean flag = false;
        if(getChildCount() > 0)
        {
            int k = mScrollX;
            View view = getChildAt(0);
            boolean flag1 = flag;
            if(j >= view.getTop())
            {
                flag1 = flag;
                if(j < view.getBottom())
                {
                    flag1 = flag;
                    if(i >= view.getLeft() - k)
                    {
                        flag1 = flag;
                        if(i < view.getRight() - k)
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
        mHorizontalScrollFactor = viewconfiguration.getScaledHorizontalScrollFactor();
    }

    private void initVelocityTrackerIfNotExists()
    {
        if(mVelocityTracker == null)
            mVelocityTracker = VelocityTracker.obtain();
    }

    private boolean isOffScreen(View view)
    {
        return isWithinDeltaOfScreen(view, 0) ^ true;
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

    private boolean isWithinDeltaOfScreen(View view, int i)
    {
        boolean flag = false;
        view.getDrawingRect(mTempRect);
        offsetDescendantRectToMyCoords(view, mTempRect);
        boolean flag1 = flag;
        if(mTempRect.right + i >= getScrollX())
        {
            flag1 = flag;
            if(mTempRect.left - i <= getScrollX() + getWidth())
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
            mLastMotionX = (int)motionevent.getX(i);
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
        int l = getWidth();
        int i1 = getScrollX();
        l = i1 + l;
        boolean flag1;
        View view;
        Object obj;
        if(i == 17)
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
            doScrollX(j);
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
            scrollBy(i, 0);
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
                scrollBy(i, 0);
            else
                smoothScrollBy(i, 0);
        return flag1;
    }

    public void addView(View view)
    {
        if(getChildCount() > 0)
        {
            throw new IllegalStateException("HorizontalScrollView can host only one direct child");
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
            throw new IllegalStateException("HorizontalScrollView can host only one direct child");
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
            throw new IllegalStateException("HorizontalScrollView can host only one direct child");
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
            throw new IllegalStateException("HorizontalScrollView can host only one direct child");
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
        if(view == null || !isWithinDeltaOfScreen(view, j)) goto _L2; else goto _L1
_L1:
        view.getDrawingRect(mTempRect);
        offsetDescendantRectToMyCoords(view, mTempRect);
        doScrollX(computeScrollDeltaToGetChildRectOnScreen(mTempRect));
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
        if(i != 17 || getScrollX() >= j) goto _L4; else goto _L3
_L3:
        int l = getScrollX();
_L6:
        if(l == 0)
            return false;
        break; /* Loop/switch isn't completed */
_L4:
        l = k;
        if(i == 66)
        {
            l = k;
            if(getChildCount() > 0)
            {
                int i1 = getChildAt(0).getRight();
                int j1 = getScrollX() + getWidth();
                l = k;
                if(i1 - j1 < j)
                    l = i1 - j1;
            }
        }
        if(true) goto _L6; else goto _L5
_L5:
        if(i != 66)
            l = -l;
        doScrollX(l);
        if(true) goto _L8; else goto _L7
_L7:
    }

    protected int computeHorizontalScrollOffset()
    {
        return Math.max(0, super.computeHorizontalScrollOffset());
    }

    protected int computeHorizontalScrollRange()
    {
        int i;
        int l;
        int i1;
        i = getChildCount();
        int j = getWidth() - mPaddingLeft - mPaddingRight;
        if(i == 0)
            return j;
        i = getChildAt(0).getRight();
        l = mScrollX;
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

    public void computeScroll()
    {
        if(!mScroller.computeScrollOffset()) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        int k;
        int l;
        i = mScrollX;
        j = mScrollY;
        k = mScroller.getCurrX();
        l = mScroller.getCurrY();
        if(i == k && j == l) goto _L4; else goto _L3
_L3:
        int i1;
        i1 = getScrollRange();
        int j1 = getOverScrollMode();
        if(j1 != 0)
        {
            if(j1 == 1 && i1 > 0)
                j1 = 1;
            else
                j1 = 0;
        } else
        {
            j1 = 1;
        }
        overScrollBy(k - i, l - j, i, j, i1, 0, mOverflingDistance, 0, false);
        onScrollChanged(mScrollX, mScrollY, i, j);
        if(!j1) goto _L4; else goto _L5
_L5:
        if(k >= 0 || i < 0) goto _L7; else goto _L6
_L6:
        mEdgeGlowLeft.onAbsorb((int)mScroller.getCurrVelocity());
_L4:
        if(!awakenScrollBars())
            postInvalidateOnAnimation();
_L2:
        return;
_L7:
        if(k > i1 && i <= i1)
            mEdgeGlowRight.onAbsorb((int)mScroller.getCurrVelocity());
        if(true) goto _L4; else goto _L8
_L8:
    }

    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect)
    {
        int i;
        int j;
        int i1;
        int j1;
        if(getChildCount() == 0)
            return 0;
        i = getWidth();
        j = getScrollX();
        int k = j + i;
        i1 = getHorizontalFadingEdgeLength();
        j1 = j;
        if(rect.left > 0)
            j1 = j + i1;
        j = k;
        if(rect.right < getChildAt(0).getWidth())
            j = k - i1;
        i1 = 0;
        if(rect.right <= j || rect.left <= j1) goto _L2; else goto _L1
_L1:
        int l;
        if(rect.width() > i)
            l = (rect.left - j1) + 0;
        else
            l = (rect.right - j) + 0;
        l = Math.min(l, getChildAt(0).getRight() - j);
_L4:
        return l;
_L2:
        l = i1;
        if(rect.left < j1)
        {
            l = i1;
            if(rect.right < j)
            {
                if(rect.width() > i)
                    l = 0 - (j - rect.right);
                else
                    l = 0 - (j1 - rect.left);
                l = Math.max(l, -getScrollX());
            }
        }
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
        if(mEdgeGlowLeft != null)
        {
            int i = mScrollX;
            if(!mEdgeGlowLeft.isFinished())
            {
                int j = canvas.save();
                int l = getHeight() - mPaddingTop - mPaddingBottom;
                canvas.rotate(270F);
                canvas.translate(-l + mPaddingTop, Math.min(0, i));
                mEdgeGlowLeft.setSize(l, getWidth());
                if(mEdgeGlowLeft.draw(canvas))
                    postInvalidateOnAnimation();
                canvas.restoreToCount(j);
            }
            if(!mEdgeGlowRight.isFinished())
            {
                int j1 = canvas.save();
                int k1 = getWidth();
                int k = getHeight();
                int l1 = mPaddingTop;
                int i1 = mPaddingBottom;
                canvas.rotate(90F);
                canvas.translate(-mPaddingTop, -(Math.max(getScrollRange(), i) + k1));
                mEdgeGlowRight.setSize(k - l1 - i1, k1);
                if(mEdgeGlowRight.draw(canvas))
                    postInvalidateOnAnimation();
                canvas.restoreToCount(j1);
            }
        }
    }

    protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
    {
        super.encodeProperties(viewhierarchyencoder);
        viewhierarchyencoder.addProperty("layout:fillViewPort", mFillViewport);
    }

    public boolean executeKeyEvent(KeyEvent keyevent)
    {
        boolean flag;
        boolean flag2;
        flag = false;
        mTempRect.setEmpty();
        if(!canScroll())
            if(isFocused())
            {
                View view = findFocus();
                keyevent = view;
                if(view == this)
                    keyevent = null;
                keyevent = FocusFinder.getInstance().findNextFocus(this, keyevent, 66);
                boolean flag1 = flag;
                if(keyevent != null)
                {
                    flag1 = flag;
                    if(keyevent != this)
                        flag1 = keyevent.requestFocus(66);
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
        JVM INSTR lookupswitch 3: default 128
    //                   21: 134
    //                   22: 163
    //                   62: 192;
           goto _L3 _L4 _L5 _L6
_L3:
        flag2 = flag;
_L2:
        return flag2;
_L4:
        if(!keyevent.isAltPressed())
            flag2 = arrowScroll(17);
        else
            flag2 = fullScroll(17);
        continue; /* Loop/switch isn't completed */
_L5:
        if(!keyevent.isAltPressed())
            flag2 = arrowScroll(66);
        else
            flag2 = fullScroll(66);
        continue; /* Loop/switch isn't completed */
_L6:
        byte byte0;
        if(keyevent.isShiftPressed())
            byte0 = 17;
        else
            byte0 = 66;
        pageScroll(byte0);
        flag2 = flag;
        if(true) goto _L2; else goto _L7
_L7:
    }

    public void fling(int i)
    {
        if(getChildCount() > 0)
        {
            int j = getWidth() - mPaddingRight - mPaddingLeft;
            int k = getChildAt(0).getWidth();
            mScroller.fling(mScrollX, mScrollY, i, 0, 0, Math.max(0, k - j), 0, 0, j / 2, 0);
            boolean flag;
            View view;
            View view1;
            Object obj;
            if(i > 0)
                flag = true;
            else
                flag = false;
            view = findFocus();
            view1 = findFocusableViewInMyBounds(flag, mScroller.getFinalX(), view);
            obj = view1;
            if(view1 == null)
                obj = this;
            if(obj != view)
            {
                if(flag)
                    i = 66;
                else
                    i = 17;
                ((View) (obj)).requestFocus(i);
            }
            postInvalidateOnAnimation();
        }
    }

    public boolean fullScroll(int i)
    {
        boolean flag;
        int j;
        if(i == 66)
            flag = true;
        else
            flag = false;
        j = getWidth();
        mTempRect.left = 0;
        mTempRect.right = j;
        if(flag && getChildCount() > 0)
        {
            View view = getChildAt(0);
            mTempRect.right = view.getRight();
            mTempRect.left = mTempRect.right - j;
        }
        return scrollAndFocus(i, mTempRect.left, mTempRect.right);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/HorizontalScrollView.getName();
    }

    protected float getLeftFadingEdgeStrength()
    {
        if(getChildCount() == 0)
            return 0.0F;
        int i = getHorizontalFadingEdgeLength();
        if(mScrollX < i)
            return (float)mScrollX / (float)i;
        else
            return 1.0F;
    }

    public int getMaxScrollAmount()
    {
        return (int)((float)(mRight - mLeft) * 0.5F);
    }

    protected float getRightFadingEdgeStrength()
    {
        if(getChildCount() == 0)
            return 0.0F;
        int i = getHorizontalFadingEdgeLength();
        int j = getWidth();
        int k = mPaddingRight;
        k = getChildAt(0).getRight() - mScrollX - (j - k);
        if(k < i)
            return (float)k / (float)i;
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
        int k = mPaddingLeft;
        int l = mPaddingRight;
        view.measure(android.view.View.MeasureSpec.makeSafeMeasureSpec(Math.max(0, android.view.View.MeasureSpec.getSize(i) - (k + l)), 0), getChildMeasureSpec(j, mPaddingTop + mPaddingBottom, layoutparams.height));
    }

    protected void measureChildWithMargins(View view, int i, int j, int k, int l)
    {
        android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)view.getLayoutParams();
        int i1 = getChildMeasureSpec(k, mPaddingTop + mPaddingBottom + marginlayoutparams.topMargin + marginlayoutparams.bottomMargin + l, marginlayoutparams.height);
        k = mPaddingLeft;
        int j1 = mPaddingRight;
        int k1 = marginlayoutparams.leftMargin;
        l = marginlayoutparams.rightMargin;
        view.measure(android.view.View.MeasureSpec.makeSafeMeasureSpec(Math.max(0, android.view.View.MeasureSpec.getSize(i) - (k + j1 + k1 + l + j)), 0), i1);
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
        if(mIsBeingDragged)
            continue; /* Loop/switch isn't completed */
        float f;
        int k;
        if(motionevent.isFromSource(2))
        {
            if((motionevent.getMetaState() & 1) != 0)
                f = -motionevent.getAxisValue(9);
            else
                f = motionevent.getAxisValue(10);
        } else
        if(motionevent.isFromSource(0x400000))
            f = motionevent.getAxisValue(26);
        else
            f = 0.0F;
        i = Math.round(mHorizontalScrollFactor * f);
        if(i == 0)
            continue; /* Loop/switch isn't completed */
        j = getScrollRange();
        k = mScrollX;
        l = k + i;
        if(l >= 0)
            break; /* Loop/switch isn't completed */
        i = 0;
_L4:
        if(i != k)
        {
            super.scrollTo(i, mScrollY);
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
        boolean flag = false;
        super.onInitializeAccessibilityEventInternal(accessibilityevent);
        if(getScrollRange() > 0)
            flag = true;
        accessibilityevent.setScrollable(flag);
        accessibilityevent.setScrollX(mScrollX);
        accessibilityevent.setScrollY(mScrollY);
        accessibilityevent.setMaxScrollX(getScrollRange());
        accessibilityevent.setMaxScrollY(mScrollY);
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        int i = getScrollRange();
        if(i > 0)
        {
            accessibilitynodeinfo.setScrollable(true);
            if(isEnabled() && mScrollX > 0)
            {
                accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
                accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT);
            }
            if(isEnabled() && mScrollX < i)
            {
                accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
                accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT);
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
        i & 0xff;
        JVM INSTR tableswitch 0 6: default 76
    //                   0 202
    //                   1 275
    //                   2 81
    //                   3 275
    //                   4 76
    //                   5 317
    //                   6 344;
           goto _L1 _L2 _L3 _L4 _L3 _L1 _L5 _L6
_L1:
        return mIsBeingDragged;
_L4:
        int j = mActivePointerId;
        if(j != -1)
        {
            int j1 = motionevent.findPointerIndex(j);
            if(j1 == -1)
            {
                Log.e("HorizontalScrollView", (new StringBuilder()).append("Invalid pointerId=").append(j).append(" in onInterceptTouchEvent").toString());
            } else
            {
                int k = (int)motionevent.getX(j1);
                if(Math.abs(k - mLastMotionX) > mTouchSlop)
                {
                    mIsBeingDragged = true;
                    mLastMotionX = k;
                    initVelocityTrackerIfNotExists();
                    mVelocityTracker.addMovement(motionevent);
                    if(mParent != null)
                        mParent.requestDisallowInterceptTouchEvent(true);
                }
            }
        }
        continue; /* Loop/switch isn't completed */
_L2:
        int l = (int)motionevent.getX();
        if(!inChild(l, (int)motionevent.getY()))
        {
            mIsBeingDragged = false;
            recycleVelocityTracker();
        } else
        {
            mLastMotionX = l;
            mActivePointerId = motionevent.getPointerId(0);
            initOrResetVelocityTracker();
            mVelocityTracker.addMovement(motionevent);
            mIsBeingDragged = mScroller.isFinished() ^ true;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        mIsBeingDragged = false;
        mActivePointerId = -1;
        if(mScroller.springBack(mScrollX, mScrollY, 0, getScrollRange(), 0, 0))
            postInvalidateOnAnimation();
        continue; /* Loop/switch isn't completed */
_L5:
        int i1 = motionevent.getActionIndex();
        mLastMotionX = (int)motionevent.getX(i1);
        mActivePointerId = motionevent.getPointerId(i1);
        continue; /* Loop/switch isn't completed */
_L6:
        onSecondaryPointerUp(motionevent);
        mLastMotionX = (int)motionevent.getX(motionevent.findPointerIndex(mActivePointerId));
        if(true) goto _L1; else goto _L7
_L7:
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1 = 0;
        int j1 = 0;
        if(getChildCount() > 0)
        {
            i1 = getChildAt(0).getMeasuredWidth();
            FrameLayout.LayoutParams layoutparams = (FrameLayout.LayoutParams)getChildAt(0).getLayoutParams();
            j1 = layoutparams.leftMargin + layoutparams.rightMargin;
        }
        if(i1 > k - i - getPaddingLeftWithForeground() - getPaddingRightWithForeground() - j1)
            flag = true;
        else
            flag = false;
        layoutChildren(i, j, k, l, flag);
        mIsLayoutDirty = false;
        if(mChildToScrollTo != null && isViewDescendantOf(mChildToScrollTo, this))
            scrollToChild(mChildToScrollTo);
        mChildToScrollTo = null;
        if(!isLaidOut())
        {
            j = Math.max(0, i1 - (k - i - mPaddingLeft - mPaddingRight));
            if(mSavedState != null)
            {
                if(isLayoutRtl())
                    i = j - mSavedState.scrollOffsetFromStart;
                else
                    i = mSavedState.scrollOffsetFromStart;
                mScrollX = i;
                mSavedState = null;
            } else
            if(isLayoutRtl())
                mScrollX = j - mScrollX;
            if(mScrollX > j)
                mScrollX = j;
            else
            if(mScrollX < 0)
                mScrollX = 0;
        }
        scrollTo(mScrollX, mScrollY);
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        if(!mFillViewport)
            return;
        if(android.view.View.MeasureSpec.getMode(i) == 0)
            return;
        if(getChildCount() > 0)
        {
            View view = getChildAt(0);
            FrameLayout.LayoutParams layoutparams = (FrameLayout.LayoutParams)view.getLayoutParams();
            int k;
            if(getContext().getApplicationInfo().targetSdkVersion >= 23)
            {
                k = mPaddingLeft + mPaddingRight + layoutparams.leftMargin + layoutparams.rightMargin;
                i = mPaddingTop + mPaddingBottom + layoutparams.topMargin + layoutparams.bottomMargin;
            } else
            {
                k = mPaddingLeft + mPaddingRight;
                i = mPaddingTop + mPaddingBottom;
            }
            k = getMeasuredWidth() - k;
            if(view.getMeasuredWidth() < k)
                view.measure(android.view.View.MeasureSpec.makeMeasureSpec(k, 0x40000000), getChildMeasureSpec(j, i, layoutparams.height));
        }
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
            if(flag)
                mScroller.springBack(mScrollX, mScrollY, 0, getScrollRange(), 0, 0);
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
            j = 66;
        } else
        {
            j = i;
            if(i == 1)
                j = 17;
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
            return super.onSaveInstanceState();
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        int i;
        if(isLayoutRtl())
            i = -mScrollX;
        else
            i = mScrollX;
        savedstate.scrollOffsetFromStart = i;
        return savedstate;
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        View view = findFocus();
        if(view == null || this == view)
            return;
        if(isWithinDeltaOfScreen(view, mRight - mLeft))
        {
            view.getDrawingRect(mTempRect);
            offsetDescendantRectToMyCoords(view, mTempRect);
            doScrollX(computeScrollDeltaToGetChildRectOnScreen(mTempRect));
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        initVelocityTrackerIfNotExists();
        mVelocityTracker.addMovement(motionevent);
        motionevent.getAction() & 0xff;
        JVM INSTR tableswitch 0 6: default 64
    //                   0 66
    //                   1 548
    //                   2 148
    //                   3 679
    //                   4 64
    //                   5 64
    //                   6 760;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L1 _L6
_L1:
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
            mScroller.abortAnimation();
        mLastMotionX = (int)motionevent.getX();
        mActivePointerId = motionevent.getPointerId(0);
        continue; /* Loop/switch isn't completed */
_L4:
        int i;
        int j;
        int k;
        int l;
        i = motionevent.findPointerIndex(mActivePointerId);
        if(i == -1)
        {
            Log.e("HorizontalScrollView", (new StringBuilder()).append("Invalid pointerId=").append(mActivePointerId).append(" in onTouchEvent").toString());
            continue; /* Loop/switch isn't completed */
        }
        j = (int)motionevent.getX(i);
        k = mLastMotionX - j;
        l = k;
        if(!mIsBeingDragged)
        {
            l = k;
            if(Math.abs(k) > mTouchSlop)
            {
                ViewParent viewparent1 = getParent();
                if(viewparent1 != null)
                    viewparent1.requestDisallowInterceptTouchEvent(true);
                mIsBeingDragged = true;
                int i1;
                if(k > 0)
                    l = k - mTouchSlop;
                else
                    l = k + mTouchSlop;
            }
        }
        if(!mIsBeingDragged)
            continue; /* Loop/switch isn't completed */
        mLastMotionX = j;
        i1 = mScrollX;
        k = mScrollY;
        j = getScrollRange();
        k = getOverScrollMode();
        if(k != 0)
        {
            if(k == 1 && j > 0)
                k = 1;
            else
                k = 0;
        } else
        {
            k = 1;
        }
        if(overScrollBy(l, 0, mScrollX, 0, j, 0, mOverscrollDistance, 0, true))
            mVelocityTracker.clear();
        if(k == 0)
            continue; /* Loop/switch isn't completed */
        k = i1 + l;
        if(k >= 0) goto _L8; else goto _L7
_L7:
        mEdgeGlowLeft.onPull((float)l / (float)getWidth(), 1.0F - motionevent.getY(i) / (float)getHeight());
        if(!mEdgeGlowRight.isFinished())
            mEdgeGlowRight.onRelease();
_L9:
        if(mEdgeGlowLeft != null && (!mEdgeGlowLeft.isFinished() || mEdgeGlowRight.isFinished() ^ true))
            postInvalidateOnAnimation();
        continue; /* Loop/switch isn't completed */
_L8:
        if(k > j)
        {
            mEdgeGlowRight.onPull((float)l / (float)getWidth(), motionevent.getY(i) / (float)getHeight());
            if(!mEdgeGlowLeft.isFinished())
                mEdgeGlowLeft.onRelease();
        }
        if(true) goto _L9; else goto _L3
_L3:
        if(!mIsBeingDragged)
            continue; /* Loop/switch isn't completed */
        motionevent = mVelocityTracker;
        motionevent.computeCurrentVelocity(1000, mMaximumVelocity);
        l = (int)motionevent.getXVelocity(mActivePointerId);
        if(getChildCount() <= 0) goto _L11; else goto _L10
_L10:
        if(Math.abs(l) <= mMinimumVelocity) goto _L13; else goto _L12
_L12:
        fling(-l);
_L11:
        mActivePointerId = -1;
        mIsBeingDragged = false;
        recycleVelocityTracker();
        if(mEdgeGlowLeft != null)
        {
            mEdgeGlowLeft.onRelease();
            mEdgeGlowRight.onRelease();
        }
        continue; /* Loop/switch isn't completed */
_L13:
        if(mScroller.springBack(mScrollX, mScrollY, 0, getScrollRange(), 0, 0))
            postInvalidateOnAnimation();
        if(true) goto _L11; else goto _L5
_L5:
        if(mIsBeingDragged && getChildCount() > 0)
        {
            if(mScroller.springBack(mScrollX, mScrollY, 0, getScrollRange(), 0, 0))
                postInvalidateOnAnimation();
            mActivePointerId = -1;
            mIsBeingDragged = false;
            recycleVelocityTracker();
            if(mEdgeGlowLeft != null)
            {
                mEdgeGlowLeft.onRelease();
                mEdgeGlowRight.onRelease();
            }
        }
        continue; /* Loop/switch isn't completed */
_L6:
        onSecondaryPointerUp(motionevent);
        if(true) goto _L1; else goto _L14
_L14:
    }

    public boolean pageScroll(int i)
    {
        int j;
        boolean flag;
        View view;
        if(i == 66)
            flag = true;
        else
            flag = false;
        j = getWidth();
        if(!flag) goto _L2; else goto _L1
_L1:
        mTempRect.left = getScrollX() + j;
        if(getChildCount() > 0)
        {
            view = getChildAt(0);
            if(mTempRect.left + j > view.getRight())
                mTempRect.left = view.getRight() - j;
        }
_L4:
        mTempRect.right = mTempRect.left + j;
        return scrollAndFocus(i, mTempRect.left, mTempRect.right);
_L2:
        mTempRect.left = getScrollX() - j;
        if(mTempRect.left < 0)
            mTempRect.left = 0;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean performAccessibilityActionInternal(int i, Bundle bundle)
    {
        if(super.performAccessibilityActionInternal(i, bundle))
            return true;
        switch(i)
        {
        default:
            return false;

        case 4096: 
        case 16908347: 
            if(!isEnabled())
                return false;
            i = getWidth();
            int j = mPaddingLeft;
            int l = mPaddingRight;
            i = Math.min(mScrollX + (i - j - l), getScrollRange());
            if(i != mScrollX)
            {
                smoothScrollTo(i, 0);
                return true;
            } else
            {
                return false;
            }

        case 8192: 
        case 16908345: 
            break;
        }
        if(!isEnabled())
            return false;
        int i1 = getWidth();
        i = mPaddingLeft;
        int k = mPaddingRight;
        i = Math.max(0, mScrollX - (i1 - i - k));
        if(i != mScrollX)
        {
            smoothScrollTo(i, 0);
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
            if(mEdgeGlowLeft == null)
            {
                Context context = getContext();
                mEdgeGlowLeft = new EdgeEffect(context);
                mEdgeGlowRight = new EdgeEffect(context);
            }
        } else
        {
            mEdgeGlowLeft = null;
            mEdgeGlowRight = null;
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
            j = getWidth();
            int k = mPaddingRight;
            int l = mPaddingLeft;
            l = Math.max(0, getChildAt(0).getWidth() - (j - k - l));
            j = mScrollX;
            i = Math.max(0, Math.min(j + i, l));
            mScroller.startScroll(j, mScrollY, i - j, 0);
            postInvalidateOnAnimation();
        } else
        {
            if(!mScroller.isFinished())
                mScroller.abortAnimation();
            scrollBy(i, j);
        }
        mLastScroll = AnimationUtils.currentAnimationTimeMillis();
    }

    public final void smoothScrollTo(int i, int j)
    {
        smoothScrollBy(i - mScrollX, j - mScrollY);
    }

    private static final int ANIMATED_SCROLL_GAP = 250;
    private static final int INVALID_POINTER = -1;
    private static final float MAX_SCROLL_FACTOR = 0.5F;
    private static final String TAG = "HorizontalScrollView";
    private int mActivePointerId;
    private View mChildToScrollTo;
    private EdgeEffect mEdgeGlowLeft;
    private EdgeEffect mEdgeGlowRight;
    private boolean mFillViewport;
    private float mHorizontalScrollFactor;
    private boolean mIsBeingDragged;
    private boolean mIsLayoutDirty;
    private int mLastMotionX;
    private long mLastScroll;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private int mOverflingDistance;
    private int mOverscrollDistance;
    private SavedState mSavedState;
    private OverScroller mScroller;
    private boolean mSmoothScrollingEnabled;
    private final Rect mTempRect;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
}

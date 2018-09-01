// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.*;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Transformation;

// Referenced classes of package android.widget:
//            AbsSpinner, SpinnerAdapter, Scroller

public class Gallery extends AbsSpinner
    implements android.view.GestureDetector.OnGestureListener
{
    private class FlingRunnable
        implements Runnable
    {

        static Scroller _2D_get0(FlingRunnable flingrunnable)
        {
            return flingrunnable.mScroller;
        }

        static void _2D_wrap0(FlingRunnable flingrunnable, boolean flag)
        {
            flingrunnable.endFling(flag);
        }

        private void endFling(boolean flag)
        {
            mScroller.forceFinished(true);
            if(flag)
                Gallery._2D_wrap1(Gallery.this);
        }

        private void startCommon()
        {
            removeCallbacks(this);
        }

        public void run()
        {
            if(mItemCount == 0)
            {
                endFling(true);
                return;
            }
            Gallery._2D_set1(Gallery.this, false);
            Scroller scroller = mScroller;
            boolean flag = scroller.computeScrollOffset();
            int i = scroller.getCurrX();
            int j = mLastFlingX - i;
            int k;
            if(j > 0)
            {
                Gallery gallery = Gallery.this;
                if(Gallery._2D_get1(Gallery.this))
                    k = (mFirstPosition + getChildCount()) - 1;
                else
                    k = mFirstPosition;
                Gallery._2D_set0(gallery, k);
                k = Math.min(getWidth() - Gallery._2D_get2(Gallery.this) - Gallery._2D_get3(Gallery.this) - 1, j);
            } else
            {
                getChildCount();
                Gallery gallery1 = Gallery.this;
                if(Gallery._2D_get1(Gallery.this))
                    k = mFirstPosition;
                else
                    k = (mFirstPosition + getChildCount()) - 1;
                Gallery._2D_set0(gallery1, k);
                k = Math.max(-(getWidth() - Gallery._2D_get3(Gallery.this) - Gallery._2D_get2(Gallery.this) - 1), j);
            }
            trackMotionScroll(k);
            if(flag && Gallery._2D_get4(Gallery.this) ^ true)
            {
                mLastFlingX = i;
                post(this);
            } else
            {
                endFling(true);
            }
        }

        public void startUsingDistance(int i)
        {
            if(i == 0)
            {
                return;
            } else
            {
                startCommon();
                mLastFlingX = 0;
                mScroller.startScroll(0, 0, -i, 0, Gallery._2D_get0(Gallery.this));
                post(this);
                return;
            }
        }

        public void startUsingVelocity(int i)
        {
            if(i == 0)
                return;
            startCommon();
            int j;
            if(i < 0)
                j = 0x7fffffff;
            else
                j = 0;
            mLastFlingX = j;
            mScroller.fling(j, 0, i, 0, 0, 0x7fffffff, 0, 0x7fffffff);
            post(this);
        }

        public void stop(boolean flag)
        {
            removeCallbacks(this);
            endFling(flag);
        }

        private int mLastFlingX;
        private Scroller mScroller;
        final Gallery this$0;

        public FlingRunnable()
        {
            this$0 = Gallery.this;
            super();
            mScroller = new Scroller(getContext());
        }
    }

    public static class LayoutParams extends android.view.ViewGroup.LayoutParams
    {

        public LayoutParams(int i, int j)
        {
            super(i, j);
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
        }
    }


    static int _2D_get0(Gallery gallery)
    {
        return gallery.mAnimationDuration;
    }

    static boolean _2D_get1(Gallery gallery)
    {
        return gallery.mIsRtl;
    }

    static int _2D_get2(Gallery gallery)
    {
        return gallery.mPaddingLeft;
    }

    static int _2D_get3(Gallery gallery)
    {
        return gallery.mPaddingRight;
    }

    static boolean _2D_get4(Gallery gallery)
    {
        return gallery.mShouldStopFling;
    }

    static int _2D_set0(Gallery gallery, int i)
    {
        gallery.mDownTouchPosition = i;
        return i;
    }

    static boolean _2D_set1(Gallery gallery, boolean flag)
    {
        gallery.mShouldStopFling = flag;
        return flag;
    }

    static boolean _2D_set2(Gallery gallery, boolean flag)
    {
        gallery.mSuppressSelectionChanged = flag;
        return flag;
    }

    static void _2D_wrap0(Gallery gallery)
    {
        gallery.dispatchUnpress();
    }

    static void _2D_wrap1(Gallery gallery)
    {
        gallery.scrollIntoSlots();
    }

    public Gallery(Context context)
    {
        this(context, null);
    }

    public Gallery(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010070);
    }

    public Gallery(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public Gallery(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mSpacing = 0;
        mAnimationDuration = 400;
        mFlingRunnable = new FlingRunnable();
        mShouldCallbackDuringFling = true;
        mShouldCallbackOnUnselectedItemClick = true;
        mIsRtl = true;
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.Gallery, i, j);
        i = context.getInt(0, -1);
        if(i >= 0)
            setGravity(i);
        i = context.getInt(1, -1);
        if(i > 0)
            setAnimationDuration(i);
        setSpacing(context.getDimensionPixelOffset(2, 0));
        setUnselectedAlpha(context.getFloat(3, 0.5F));
        context.recycle();
        mGroupFlags = mGroupFlags | 0x400;
        mGroupFlags = mGroupFlags | 0x800;
    }

    private int calculateTop(View view, boolean flag)
    {
        int i;
        int j;
        boolean flag1;
        if(flag)
            i = getMeasuredHeight();
        else
            i = getHeight();
        if(flag)
            j = view.getMeasuredHeight();
        else
            j = view.getHeight();
        flag1 = false;
        mGravity;
        JVM INSTR lookupswitch 3: default 60
    //                   16: 93
    //                   48: 82
    //                   80: 135;
           goto _L1 _L2 _L3 _L4
_L1:
        i = ((flag1) ? 1 : 0);
_L6:
        return i;
_L3:
        i = mSpinnerPadding.top;
        continue; /* Loop/switch isn't completed */
_L2:
        int k = mSpinnerPadding.bottom;
        int l = mSpinnerPadding.top;
        i = mSpinnerPadding.top + (i - k - l - j) / 2;
        continue; /* Loop/switch isn't completed */
_L4:
        i = i - mSpinnerPadding.bottom - j;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void detachOffScreenChildren(boolean flag)
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        i = getChildCount();
        j = mFirstPosition;
        k = 0;
        l = 0;
        i1 = 0;
        j1 = 0;
        if(!flag) goto _L2; else goto _L1
_L1:
        int k1;
        k1 = mPaddingLeft;
        k = 0;
        i1 = j1;
_L6:
        if(k >= i) goto _L4; else goto _L3
_L3:
        View view;
        if(mIsRtl)
            j1 = i - 1 - k;
        else
            j1 = k;
        view = getChildAt(j1);
        if(view.getRight() < k1) goto _L5; else goto _L4
_L4:
        j1 = i1;
        if(!mIsRtl)
        {
            l = 0;
            j1 = i1;
        }
_L7:
        detachViewsFromParent(l, j1);
        if(flag != mIsRtl)
            mFirstPosition = mFirstPosition + j1;
        return;
_L5:
        l = j1;
        i1++;
        mRecycler.put(j + j1, view);
        k++;
          goto _L6
_L2:
        int l1;
        l1 = getWidth();
        k1 = mPaddingRight;
        l = i - 1;
_L8:
label0:
        {
            if(l >= 0)
            {
                if(mIsRtl)
                    j1 = i - 1 - l;
                else
                    j1 = l;
                view = getChildAt(j1);
                if(view.getLeft() > l1 - k1)
                    break label0;
            }
            j1 = i1;
            l = k;
            if(mIsRtl)
            {
                l = 0;
                j1 = i1;
            }
        }
          goto _L7
        k = j1;
        i1++;
        mRecycler.put(j + j1, view);
        l--;
          goto _L8
    }

    private boolean dispatchLongPress(View view, int i, long l, float f, float f1, boolean flag)
    {
        boolean flag1 = false;
        if(mOnItemLongClickListener != null)
            flag1 = mOnItemLongClickListener.onItemLongClick(this, mDownTouchView, mDownTouchPosition, l);
        boolean flag2 = flag1;
        if(!flag1)
        {
            mContextMenuInfo = new AdapterView.AdapterContextMenuInfo(view, i, l);
            if(flag)
                flag2 = super.showContextMenuForChild(view, f, f1);
            else
                flag2 = super.showContextMenuForChild(this);
        }
        if(flag2)
            performHapticFeedback(0);
        return flag2;
    }

    private void dispatchPress(View view)
    {
        if(view != null)
            view.setPressed(true);
        setPressed(true);
    }

    private void dispatchUnpress()
    {
        for(int i = getChildCount() - 1; i >= 0; i--)
            getChildAt(i).setPressed(false);

        setPressed(false);
    }

    private void fillToGalleryLeft()
    {
        if(mIsRtl)
            fillToGalleryLeftRtl();
        else
            fillToGalleryLeftLtr();
    }

    private void fillToGalleryLeftLtr()
    {
        int i = mSpacing;
        int j = mPaddingLeft;
        View view = getChildAt(0);
        int k;
        int l;
        if(view != null)
        {
            k = mFirstPosition - 1;
            l = view.getLeft() - i;
        } else
        {
            k = 0;
            l = mRight - mLeft - mPaddingRight;
            mShouldStopFling = true;
        }
        for(; l > j && k >= 0; k--)
        {
            View view1 = makeAndAddView(k, k - mSelectedPosition, l, false);
            mFirstPosition = k;
            l = view1.getLeft() - i;
        }

    }

    private void fillToGalleryLeftRtl()
    {
        int i = mSpacing;
        int j = mPaddingLeft;
        int k = getChildCount();
        int l = mItemCount;
        View view = getChildAt(k - 1);
        if(view != null)
        {
            l = mFirstPosition + k;
            k = view.getLeft() - i;
        } else
        {
            l = mItemCount - 1;
            mFirstPosition = l;
            k = mRight - mLeft - mPaddingRight;
            mShouldStopFling = true;
        }
        for(; k > j && l < mItemCount; l++)
            k = makeAndAddView(l, l - mSelectedPosition, k, false).getLeft() - i;

    }

    private void fillToGalleryRight()
    {
        if(mIsRtl)
            fillToGalleryRightRtl();
        else
            fillToGalleryRightLtr();
    }

    private void fillToGalleryRightLtr()
    {
        int i = mSpacing;
        int j = mRight;
        int k = mLeft;
        int l = mPaddingRight;
        int i1 = getChildCount();
        int j1 = mItemCount;
        View view = getChildAt(i1 - 1);
        int k1;
        if(view != null)
        {
            i1 = mFirstPosition + i1;
            k1 = view.getRight() + i;
        } else
        {
            i1 = mItemCount - 1;
            mFirstPosition = i1;
            k1 = mPaddingLeft;
            mShouldStopFling = true;
        }
        for(; k1 < j - k - l && i1 < j1; i1++)
            k1 = makeAndAddView(i1, i1 - mSelectedPosition, k1, true).getRight() + i;

    }

    private void fillToGalleryRightRtl()
    {
        int i = mSpacing;
        int j = mRight;
        int k = mLeft;
        int l = mPaddingRight;
        View view = getChildAt(0);
        int i1;
        int j1;
        if(view != null)
        {
            i1 = mFirstPosition - 1;
            j1 = view.getRight() + i;
        } else
        {
            i1 = 0;
            j1 = mPaddingLeft;
            mShouldStopFling = true;
        }
        for(; j1 < j - k - l && i1 >= 0; i1--)
        {
            View view1 = makeAndAddView(i1, i1 - mSelectedPosition, j1, true);
            mFirstPosition = i1;
            j1 = view1.getRight() + i;
        }

    }

    private int getCenterOfGallery()
    {
        return (getWidth() - mPaddingLeft - mPaddingRight) / 2 + mPaddingLeft;
    }

    private static int getCenterOfView(View view)
    {
        return view.getLeft() + view.getWidth() / 2;
    }

    private View makeAndAddView(int i, int j, int k, boolean flag)
    {
        if(!mDataChanged)
        {
            View view = mRecycler.get(i);
            if(view != null)
            {
                i = view.getLeft();
                mRightMost = Math.max(mRightMost, view.getMeasuredWidth() + i);
                mLeftMost = Math.min(mLeftMost, i);
                setUpChild(view, j, k, flag);
                return view;
            }
        }
        View view1 = mAdapter.getView(i, null, this);
        setUpChild(view1, j, k, flag);
        return view1;
    }

    private void offsetChildrenLeftAndRight(int i)
    {
        for(int j = getChildCount() - 1; j >= 0; j--)
            getChildAt(j).offsetLeftAndRight(i);

    }

    private void onFinishedMovement()
    {
        if(mSuppressSelectionChanged)
        {
            mSuppressSelectionChanged = false;
            super.selectionChanged();
        }
        mSelectedCenterOffset = 0;
        invalidate();
    }

    private void scrollIntoSlots()
    {
        if(getChildCount() == 0 || mSelectedChild == null)
            return;
        int i = getCenterOfView(mSelectedChild);
        i = getCenterOfGallery() - i;
        if(i != 0)
            mFlingRunnable.startUsingDistance(i);
        else
            onFinishedMovement();
    }

    private boolean scrollToChild(int i)
    {
        View view = getChildAt(i);
        if(view != null)
        {
            int j = getCenterOfGallery();
            i = getCenterOfView(view);
            mFlingRunnable.startUsingDistance(j - i);
            return true;
        } else
        {
            return false;
        }
    }

    private void setSelectionToCenterChild()
    {
        View view = mSelectedChild;
        if(mSelectedChild == null)
            return;
        int i = getCenterOfGallery();
        if(view.getLeft() <= i && view.getRight() >= i)
            return;
        int j = 0x7fffffff;
        int k = 0;
        int l = getChildCount() - 1;
        do
        {
            View view1;
label0:
            {
                int i1 = k;
                if(l >= 0)
                {
                    view1 = getChildAt(l);
                    if(view1.getLeft() > i || view1.getRight() < i)
                        break label0;
                    i1 = l;
                }
                l = mFirstPosition + i1;
                if(l != mSelectedPosition)
                {
                    setSelectedPositionInt(l);
                    setNextSelectedPositionInt(l);
                    checkSelectionChanged();
                }
                return;
            }
            int k1 = Math.min(Math.abs(view1.getLeft() - i), Math.abs(view1.getRight() - i));
            int j1 = j;
            if(k1 < j)
            {
                j1 = k1;
                k = l;
            }
            l--;
            j = j1;
        } while(true);
    }

    private void setUpChild(View view, int i, int j, boolean flag)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        LayoutParams layoutparams1 = layoutparams;
        if(layoutparams == null)
            layoutparams1 = (LayoutParams)generateDefaultLayoutParams();
        int k;
        boolean flag1;
        int l;
        int i1;
        if(flag != mIsRtl)
            k = -1;
        else
            k = 0;
        addViewInLayout(view, k, layoutparams1, true);
        if(i == 0)
            flag1 = true;
        else
            flag1 = false;
        view.setSelected(flag1);
        i = ViewGroup.getChildMeasureSpec(mHeightMeasureSpec, mSpinnerPadding.top + mSpinnerPadding.bottom, layoutparams1.height);
        view.measure(ViewGroup.getChildMeasureSpec(mWidthMeasureSpec, mSpinnerPadding.left + mSpinnerPadding.right, layoutparams1.width), i);
        l = calculateTop(view, true);
        k = view.getMeasuredHeight();
        i1 = view.getMeasuredWidth();
        if(flag)
        {
            i = j;
            j += i1;
        } else
        {
            i = j - i1;
        }
        view.layout(i, l, j, l + k);
    }

    private boolean showContextMenuForChildInternal(View view, float f, float f1, boolean flag)
    {
        int i = getPositionForView(view);
        if(i < 0)
            return false;
        else
            return dispatchLongPress(view, i, mAdapter.getItemId(i), f, f1, flag);
    }

    private boolean showContextMenuInternal(float f, float f1, boolean flag)
    {
        if(isPressed() && mSelectedPosition >= 0)
            return dispatchLongPress(getChildAt(mSelectedPosition - mFirstPosition), mSelectedPosition, mSelectedRowId, f, f1, flag);
        else
            return false;
    }

    private void updateSelectedItemMetadata()
    {
        View view = mSelectedChild;
        View view1 = getChildAt(mSelectedPosition - mFirstPosition);
        mSelectedChild = view1;
        if(view1 == null)
            return;
        view1.setSelected(true);
        view1.setFocusable(true);
        if(hasFocus())
            view1.requestFocus();
        if(view != null && view != view1)
        {
            view.setSelected(false);
            view.setFocusable(false);
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof LayoutParams;
    }

    protected int computeHorizontalScrollExtent()
    {
        return 1;
    }

    protected int computeHorizontalScrollOffset()
    {
        return mSelectedPosition;
    }

    protected int computeHorizontalScrollRange()
    {
        return mItemCount;
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        return keyevent.dispatch(this, null, null);
    }

    protected void dispatchSetPressed(boolean flag)
    {
        if(mSelectedChild != null)
            mSelectedChild.setPressed(flag);
    }

    public void dispatchSetSelected(boolean flag)
    {
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-2, -2);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return new LayoutParams(layoutparams);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/Gallery.getName();
    }

    protected int getChildDrawingOrder(int i, int j)
    {
        int k = mSelectedPosition - mFirstPosition;
        if(k < 0)
            return j;
        if(j == i - 1)
            return k;
        if(j >= k)
            return j + 1;
        else
            return j;
    }

    int getChildHeight(View view)
    {
        return view.getMeasuredHeight();
    }

    protected boolean getChildStaticTransformation(View view, Transformation transformation)
    {
        transformation.clear();
        float f;
        if(view == mSelectedChild)
            f = 1.0F;
        else
            f = mUnselectedAlpha;
        transformation.setAlpha(f);
        return true;
    }

    protected android.view.ContextMenu.ContextMenuInfo getContextMenuInfo()
    {
        return mContextMenuInfo;
    }

    int getLimitedMotionScrollAmount(boolean flag, int i)
    {
        int j;
        View view;
        if(flag != mIsRtl)
            j = mItemCount - 1;
        else
            j = 0;
        view = getChildAt(j - mFirstPosition);
        if(view == null)
            return i;
        j = getCenterOfView(view);
        int k = getCenterOfGallery();
        if(flag)
        {
            if(j <= k)
                return 0;
        } else
        if(j >= k)
            return 0;
        j = k - j;
        if(flag)
            i = Math.max(j, i);
        else
            i = Math.min(j, i);
        return i;
    }

    void layout(int i, boolean flag)
    {
        mIsRtl = isLayoutRtl();
        int j = mSpinnerPadding.left;
        i = mRight;
        int k = mLeft;
        int l = mSpinnerPadding.left;
        int i1 = mSpinnerPadding.right;
        if(mDataChanged)
            handleDataChanged();
        if(mItemCount == 0)
        {
            resetList();
            return;
        }
        if(mNextSelectedPosition >= 0)
            setSelectedPositionInt(mNextSelectedPosition);
        recycleAllViews();
        detachAllViewsFromParent();
        mRightMost = 0;
        mLeftMost = 0;
        mFirstPosition = mSelectedPosition;
        View view = makeAndAddView(mSelectedPosition, 0, 0, true);
        view.offsetLeftAndRight((((i - k - l - i1) / 2 + j) - view.getWidth() / 2) + mSelectedCenterOffset);
        fillToGalleryRight();
        fillToGalleryLeft();
        mRecycler.clear();
        invalidate();
        checkSelectionChanged();
        mDataChanged = false;
        mNeedSync = false;
        setNextSelectedPositionInt(mSelectedPosition);
        updateSelectedItemMetadata();
    }

    boolean moveDirection(int i)
    {
        int j = i;
        if(isLayoutRtl())
            j = -i;
        i = mSelectedPosition + j;
        if(mItemCount > 0 && i >= 0 && i < mItemCount)
        {
            scrollToChild(i - mFirstPosition);
            return true;
        } else
        {
            return false;
        }
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if(mGestureDetector == null)
        {
            mGestureDetector = new GestureDetector(getContext(), this);
            mGestureDetector.setIsLongpressEnabled(true);
        }
    }

    void onCancel()
    {
        onUp();
    }

    public boolean onDown(MotionEvent motionevent)
    {
        mFlingRunnable.stop(false);
        mDownTouchPosition = pointToPosition((int)motionevent.getX(), (int)motionevent.getY());
        if(mDownTouchPosition >= 0)
        {
            mDownTouchView = getChildAt(mDownTouchPosition - mFirstPosition);
            mDownTouchView.setPressed(true);
        }
        mIsFirstScroll = true;
        return true;
    }

    public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        if(!mShouldCallbackDuringFling)
        {
            removeCallbacks(mDisableSuppressSelectionChangedRunnable);
            if(!mSuppressSelectionChanged)
                mSuppressSelectionChanged = true;
        }
        mFlingRunnable.startUsingVelocity((int)(-f));
        return true;
    }

    protected void onFocusChanged(boolean flag, int i, Rect rect)
    {
        super.onFocusChanged(flag, i, rect);
        if(flag && mSelectedChild != null)
        {
            mSelectedChild.requestFocus(i);
            mSelectedChild.setSelected(true);
        }
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        boolean flag = true;
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        if(mItemCount <= 1)
            flag = false;
        accessibilitynodeinfo.setScrollable(flag);
        if(isEnabled())
        {
            if(mItemCount > 0 && mSelectedPosition < mItemCount - 1)
                accessibilitynodeinfo.addAction(4096);
            if(isEnabled() && mItemCount > 0 && mSelectedPosition > 0)
                accessibilitynodeinfo.addAction(8192);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        i;
        JVM INSTR lookupswitch 4: default 44
    //                   21: 51
    //                   22: 66
    //                   23: 81
    //                   66: 81;
           goto _L1 _L2 _L3 _L4 _L4
_L1:
        return super.onKeyDown(i, keyevent);
_L2:
        if(moveDirection(-1))
        {
            playSoundEffect(1);
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(moveDirection(1))
        {
            playSoundEffect(3);
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        mReceivedInvokeKeyDown = true;
        if(true) goto _L1; else goto _L5
_L5:
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        if(KeyEvent.isConfirmKey(i))
        {
            if(mReceivedInvokeKeyDown && mItemCount > 0)
            {
                dispatchPress(mSelectedChild);
                postDelayed(new Runnable() {

                    public void run()
                    {
                        Gallery._2D_wrap0(Gallery.this);
                    }

                    final Gallery this$0;

            
            {
                this$0 = Gallery.this;
                super();
            }
                }
, ViewConfiguration.getPressedStateDuration());
                performItemClick(getChildAt(mSelectedPosition - mFirstPosition), mSelectedPosition, mAdapter.getItemId(mSelectedPosition));
            }
            mReceivedInvokeKeyDown = false;
            return true;
        } else
        {
            return super.onKeyUp(i, keyevent);
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        mInLayout = true;
        layout(0, false);
        mInLayout = false;
    }

    public void onLongPress(MotionEvent motionevent)
    {
        if(mDownTouchPosition < 0)
        {
            return;
        } else
        {
            performHapticFeedback(0);
            long l = getItemIdAtPosition(mDownTouchPosition);
            dispatchLongPress(mDownTouchView, mDownTouchPosition, l, motionevent.getX(), motionevent.getY(), true);
            return;
        }
    }

    public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        mParent.requestDisallowInterceptTouchEvent(true);
        if(mShouldCallbackDuringFling) goto _L2; else goto _L1
_L1:
        if(mIsFirstScroll)
        {
            if(!mSuppressSelectionChanged)
                mSuppressSelectionChanged = true;
            postDelayed(mDisableSuppressSelectionChangedRunnable, 250L);
        }
_L4:
        trackMotionScroll((int)f * -1);
        mIsFirstScroll = false;
        return true;
_L2:
        if(mSuppressSelectionChanged)
            mSuppressSelectionChanged = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onShowPress(MotionEvent motionevent)
    {
    }

    public boolean onSingleTapUp(MotionEvent motionevent)
    {
        if(mDownTouchPosition >= 0)
        {
            scrollToChild(mDownTouchPosition - mFirstPosition);
            if(mShouldCallbackOnUnselectedItemClick || mDownTouchPosition == mSelectedPosition)
                performItemClick(mDownTouchView, mDownTouchPosition, mAdapter.getItemId(mDownTouchPosition));
            return true;
        } else
        {
            return false;
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        int i;
        flag = mGestureDetector.onTouchEvent(motionevent);
        i = motionevent.getAction();
        if(i != 1) goto _L2; else goto _L1
_L1:
        onUp();
_L4:
        return flag;
_L2:
        if(i == 3)
            onCancel();
        if(true) goto _L4; else goto _L3
_L3:
    }

    void onUp()
    {
        if(FlingRunnable._2D_get0(mFlingRunnable).isFinished())
            scrollIntoSlots();
        dispatchUnpress();
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
            if(isEnabled() && mItemCount > 0 && mSelectedPosition < mItemCount - 1)
                return scrollToChild((mSelectedPosition - mFirstPosition) + 1);
            else
                return false;

        case 8192: 
            break;
        }
        if(isEnabled() && mItemCount > 0 && mSelectedPosition > 0)
            return scrollToChild(mSelectedPosition - mFirstPosition - 1);
        else
            return false;
    }

    void selectionChanged()
    {
        if(!mSuppressSelectionChanged)
            super.selectionChanged();
    }

    public void setAnimationDuration(int i)
    {
        mAnimationDuration = i;
    }

    public void setCallbackDuringFling(boolean flag)
    {
        mShouldCallbackDuringFling = flag;
    }

    public void setCallbackOnUnselectedItemClick(boolean flag)
    {
        mShouldCallbackOnUnselectedItemClick = flag;
    }

    public void setGravity(int i)
    {
        if(mGravity != i)
        {
            mGravity = i;
            requestLayout();
        }
    }

    void setSelectedPositionInt(int i)
    {
        super.setSelectedPositionInt(i);
        updateSelectedItemMetadata();
    }

    public void setSpacing(int i)
    {
        mSpacing = i;
    }

    public void setUnselectedAlpha(float f)
    {
        mUnselectedAlpha = f;
    }

    public boolean showContextMenu()
    {
        return showContextMenuInternal(0.0F, 0.0F, false);
    }

    public boolean showContextMenu(float f, float f1)
    {
        return showContextMenuInternal(f, f1, true);
    }

    public boolean showContextMenuForChild(View view)
    {
        if(isShowingContextMenuWithCoords())
            return false;
        else
            return showContextMenuForChildInternal(view, 0.0F, 0.0F, false);
    }

    public boolean showContextMenuForChild(View view, float f, float f1)
    {
        return showContextMenuForChildInternal(view, f, f1, true);
    }

    void trackMotionScroll(int i)
    {
        if(getChildCount() == 0)
            return;
        boolean flag;
        int j;
        View view;
        if(i < 0)
            flag = true;
        else
            flag = false;
        j = getLimitedMotionScrollAmount(flag, i);
        if(j != i)
        {
            FlingRunnable._2D_wrap0(mFlingRunnable, false);
            onFinishedMovement();
        }
        offsetChildrenLeftAndRight(j);
        detachOffScreenChildren(flag);
        if(flag)
            fillToGalleryRight();
        else
            fillToGalleryLeft();
        mRecycler.clear();
        setSelectionToCenterChild();
        view = mSelectedChild;
        if(view != null)
            mSelectedCenterOffset = (view.getLeft() + view.getWidth() / 2) - getWidth() / 2;
        onScrollChanged(0, 0, 0, 0);
        invalidate();
    }

    private static final int SCROLL_TO_FLING_UNCERTAINTY_TIMEOUT = 250;
    private static final String TAG = "Gallery";
    private static final boolean localLOGV = false;
    private int mAnimationDuration;
    private AdapterView.AdapterContextMenuInfo mContextMenuInfo;
    private Runnable mDisableSuppressSelectionChangedRunnable = new Runnable() {

        public void run()
        {
            Gallery._2D_set2(Gallery.this, false);
            selectionChanged();
        }

        final Gallery this$0;

            
            {
                this$0 = Gallery.this;
                super();
            }
    }
;
    private int mDownTouchPosition;
    private View mDownTouchView;
    private FlingRunnable mFlingRunnable;
    private GestureDetector mGestureDetector;
    private int mGravity;
    private boolean mIsFirstScroll;
    private boolean mIsRtl;
    private int mLeftMost;
    private boolean mReceivedInvokeKeyDown;
    private int mRightMost;
    private int mSelectedCenterOffset;
    private View mSelectedChild;
    private boolean mShouldCallbackDuringFling;
    private boolean mShouldCallbackOnUnselectedItemClick;
    private boolean mShouldStopFling;
    private int mSpacing;
    private boolean mSuppressSelectionChanged;
    private float mUnselectedAlpha;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import java.util.*;

// Referenced classes of package com.android.internal.widget:
//            PagerAdapter

public class ViewPager extends ViewGroup
{
    static interface Decor
    {
    }

    static class ItemInfo
    {

        Object object;
        float offset;
        int position;
        boolean scrolling;
        float widthFactor;

        ItemInfo()
        {
        }
    }

    public static class LayoutParams extends android.view.ViewGroup.LayoutParams
    {

        int childIndex;
        public int gravity;
        public boolean isDecor;
        boolean needsMeasure;
        int position;
        float widthFactor;

        public LayoutParams()
        {
            super(-1, -1);
            widthFactor = 0.0F;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            widthFactor = 0.0F;
            context = context.obtainStyledAttributes(attributeset, ViewPager._2D_get0());
            gravity = context.getInteger(0, 48);
            context.recycle();
        }
    }

    static interface OnAdapterChangeListener
    {

        public abstract void onAdapterChanged(PagerAdapter pageradapter, PagerAdapter pageradapter1);
    }

    public static interface OnPageChangeListener
    {

        public abstract void onPageScrollStateChanged(int i);

        public abstract void onPageScrolled(int i, float f, int j);

        public abstract void onPageSelected(int i);
    }

    public static interface PageTransformer
    {

        public abstract void transformPage(View view, float f);
    }

    private class PagerObserver extends DataSetObserver
    {

        public void onChanged()
        {
            dataSetChanged();
        }

        public void onInvalidated()
        {
            dataSetChanged();
        }

        final ViewPager this$0;

        private PagerObserver()
        {
            this$0 = ViewPager.this;
            super();
        }

        PagerObserver(PagerObserver pagerobserver)
        {
            this();
        }
    }

    public static class SavedState extends AbsSavedState
    {

        public String toString()
        {
            return (new StringBuilder()).append("FragmentPager.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" position=").append(position).append("}").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(position);
            parcel.writeParcelable(adapterState, i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.ClassLoaderCreator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel, null);
            }

            public SavedState createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return new SavedState(parcel, classloader);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return createFromParcel(parcel, classloader);
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
        Parcelable adapterState;
        ClassLoader loader;
        int position;


        SavedState(Parcel parcel, ClassLoader classloader)
        {
            super(parcel, classloader);
            ClassLoader classloader1 = classloader;
            if(classloader == null)
                classloader1 = getClass().getClassLoader();
            position = parcel.readInt();
            adapterState = parcel.readParcelable(classloader1);
            loader = classloader1;
        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }

    public static class SimpleOnPageChangeListener
        implements OnPageChangeListener
    {

        public void onPageScrollStateChanged(int i)
        {
        }

        public void onPageScrolled(int i, float f, int j)
        {
        }

        public void onPageSelected(int i)
        {
        }

        public SimpleOnPageChangeListener()
        {
        }
    }

    static class ViewPositionComparator
        implements Comparator
    {

        public int compare(View view, View view1)
        {
            view = (LayoutParams)view.getLayoutParams();
            view1 = (LayoutParams)view1.getLayoutParams();
            if(((LayoutParams) (view)).isDecor != ((LayoutParams) (view1)).isDecor)
            {
                int i;
                if(((LayoutParams) (view)).isDecor)
                    i = 1;
                else
                    i = -1;
                return i;
            } else
            {
                return ((LayoutParams) (view)).position - ((LayoutParams) (view1)).position;
            }
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((View)obj, (View)obj1);
        }

        ViewPositionComparator()
        {
        }
    }


    static int[] _2D_get0()
    {
        return LAYOUT_ATTRS;
    }

    static void _2D_wrap0(ViewPager viewpager, int i)
    {
        viewpager.setScrollState(i);
    }

    public ViewPager(Context context)
    {
        this(context, null);
    }

    public ViewPager(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public ViewPager(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public ViewPager(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mItems = new ArrayList();
        mTempItem = new ItemInfo();
        mTempRect = new Rect();
        mRestoredCurItem = -1;
        mRestoredAdapterState = null;
        mRestoredClassLoader = null;
        mLeftIncr = -1;
        mFirstOffset = -3.402823E+038F;
        mLastOffset = 3.402823E+038F;
        mOffscreenPageLimit = 1;
        mActivePointerId = -1;
        mFirstLayout = true;
        mEndScrollRunnable = new Runnable() {

            public void run()
            {
                ViewPager._2D_wrap0(ViewPager.this, 0);
                populate();
            }

            final ViewPager this$0;

            
            {
                this$0 = ViewPager.this;
                super();
            }
        }
;
        mScrollState = 0;
        setWillNotDraw(false);
        setDescendantFocusability(0x40000);
        setFocusable(true);
        mScroller = new Scroller(context, sInterpolator);
        attributeset = ViewConfiguration.get(context);
        float f = context.getResources().getDisplayMetrics().density;
        mTouchSlop = attributeset.getScaledPagingTouchSlop();
        mMinimumVelocity = (int)(400F * f);
        mMaximumVelocity = attributeset.getScaledMaximumFlingVelocity();
        mLeftEdge = new EdgeEffect(context);
        mRightEdge = new EdgeEffect(context);
        mFlingDistance = (int)(25F * f);
        mCloseEnough = (int)(2.0F * f);
        mDefaultGutterSize = (int)(16F * f);
        if(getImportantForAccessibility() == 0)
            setImportantForAccessibility(1);
    }

    private void calculatePageOffsets(ItemInfo iteminfo, int i, ItemInfo iteminfo1)
    {
        int j = mAdapter.getCount();
        int k = getPaddedWidth();
        float f;
        if(k > 0)
            f = (float)mPageMargin / (float)k;
        else
            f = 0.0F;
        if(iteminfo1 != null)
        {
            k = iteminfo1.position;
            if(k < iteminfo.position)
            {
                int i1 = 0;
                float f1 = iteminfo1.offset + iteminfo1.widthFactor + f;
                int l1;
                for(k++; k <= iteminfo.position && i1 < mItems.size(); k = l1 + 1)
                {
                    iteminfo1 = (ItemInfo)mItems.get(i1);
                    float f4;
                    do
                    {
                        f4 = f1;
                        l1 = k;
                        if(k <= iteminfo1.position)
                            break;
                        f4 = f1;
                        l1 = k;
                        if(i1 >= mItems.size() - 1)
                            break;
                        i1++;
                        iteminfo1 = (ItemInfo)mItems.get(i1);
                    } while(true);
                    for(; l1 < iteminfo1.position; l1++)
                        f4 += mAdapter.getPageWidth(l1) + f;

                    iteminfo1.offset = f4;
                    f1 = f4 + (iteminfo1.widthFactor + f);
                }

            } else
            if(k > iteminfo.position)
            {
                int j1 = mItems.size() - 1;
                float f2 = iteminfo1.offset;
                int i2;
                for(k--; k >= iteminfo.position && j1 >= 0; k = i2 - 1)
                {
                    iteminfo1 = (ItemInfo)mItems.get(j1);
                    float f5;
                    do
                    {
                        f5 = f2;
                        i2 = k;
                        if(k >= iteminfo1.position)
                            break;
                        f5 = f2;
                        i2 = k;
                        if(j1 <= 0)
                            break;
                        j1--;
                        iteminfo1 = (ItemInfo)mItems.get(j1);
                    } while(true);
                    for(; i2 > iteminfo1.position; i2--)
                        f5 -= mAdapter.getPageWidth(i2) + f;

                    f2 = f5 - (iteminfo1.widthFactor + f);
                    iteminfo1.offset = f2;
                }

            }
        }
        int j2 = mItems.size();
        float f6 = iteminfo.offset;
        k = iteminfo.position - 1;
        int k1;
        float f3;
        if(iteminfo.position == 0)
            f3 = iteminfo.offset;
        else
            f3 = -3.402823E+038F;
        mFirstOffset = f3;
        if(iteminfo.position == j - 1)
            f3 = (iteminfo.offset + iteminfo.widthFactor) - 1.0F;
        else
            f3 = 3.402823E+038F;
        mLastOffset = f3;
        k1 = i - 1;
        f3 = f6;
        while(k1 >= 0) 
        {
            for(iteminfo1 = (ItemInfo)mItems.get(k1); k > iteminfo1.position; k--)
                f3 -= mAdapter.getPageWidth(k) + f;

            f3 -= iteminfo1.widthFactor + f;
            iteminfo1.offset = f3;
            if(iteminfo1.position == 0)
                mFirstOffset = f3;
            k1--;
            k--;
        }
        f3 = iteminfo.offset + iteminfo.widthFactor + f;
        k = iteminfo.position + 1;
        k1 = i + 1;
        i = k;
        for(int l = k1; l < j2;)
        {
            for(iteminfo = (ItemInfo)mItems.get(l); i < iteminfo.position; i++)
                f3 += mAdapter.getPageWidth(i) + f;

            if(iteminfo.position == j - 1)
                mLastOffset = (iteminfo.widthFactor + f3) - 1.0F;
            iteminfo.offset = f3;
            f3 += iteminfo.widthFactor + f;
            l++;
            i++;
        }

    }

    private boolean canScroll()
    {
        boolean flag = true;
        if(mAdapter == null || mAdapter.getCount() <= 1)
            flag = false;
        return flag;
    }

    private void completeScroll(boolean flag)
    {
        int i;
        boolean flag1;
        boolean flag2;
        if(mScrollState == 2)
            i = 1;
        else
            i = 0;
        if(i != 0)
        {
            setScrollingCacheEnabled(false);
            mScroller.abortAnimation();
            int j = getScrollX();
            int k = getScrollY();
            int l = mScroller.getCurrX();
            int i1 = mScroller.getCurrY();
            if(j != l || k != i1)
                scrollTo(l, i1);
        }
        mPopulatePending = false;
        flag1 = false;
        flag2 = i;
        for(i = ((flag1) ? 1 : 0); i < mItems.size(); i++)
        {
            ItemInfo iteminfo = (ItemInfo)mItems.get(i);
            if(iteminfo.scrolling)
            {
                flag2 = true;
                iteminfo.scrolling = false;
            }
        }

        if(flag2)
            if(flag)
                postOnAnimation(mEndScrollRunnable);
            else
                mEndScrollRunnable.run();
    }

    private int determineTargetPage(int i, float f, int j, int k)
    {
        if(Math.abs(k) > mFlingDistance && Math.abs(j) > mMinimumVelocity)
        {
            ItemInfo iteminfo;
            ItemInfo iteminfo1;
            if(j < 0)
                j = mLeftIncr;
            else
                j = 0;
            i -= j;
        } else
        {
            float f1;
            if(i >= mCurItem)
                f1 = 0.4F;
            else
                f1 = 0.6F;
            i = (int)((float)i - (float)mLeftIncr * (f + f1));
        }
        j = i;
        if(mItems.size() > 0)
        {
            iteminfo = (ItemInfo)mItems.get(0);
            iteminfo1 = (ItemInfo)mItems.get(mItems.size() - 1);
            j = MathUtils.constrain(i, iteminfo.position, iteminfo1.position);
        }
        return j;
    }

    private void enableLayers(boolean flag)
    {
        int i = getChildCount();
        int j = 0;
        while(j < i) 
        {
            byte byte0;
            if(flag)
                byte0 = 2;
            else
                byte0 = 0;
            getChildAt(j).setLayerType(byte0, null);
            j++;
        }
    }

    private void endDrag()
    {
        mIsBeingDragged = false;
        mIsUnableToDrag = false;
        if(mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    private Rect getChildRectInPagerCoordinates(Rect rect, View view)
    {
        Rect rect1 = rect;
        if(rect == null)
            rect1 = new Rect();
        if(view == null)
        {
            rect1.set(0, 0, 0, 0);
            return rect1;
        }
        rect1.left = view.getLeft();
        rect1.right = view.getRight();
        rect1.top = view.getTop();
        rect1.bottom = view.getBottom();
        for(rect = view.getParent(); (rect instanceof ViewGroup) && rect != this; rect = rect.getParent())
        {
            rect = (ViewGroup)rect;
            rect1.left = rect1.left + rect.getLeft();
            rect1.right = rect1.right + rect.getRight();
            rect1.top = rect1.top + rect.getTop();
            rect1.bottom = rect1.bottom + rect.getBottom();
        }

        return rect1;
    }

    private int getLeftEdgeForItem(int i)
    {
        ItemInfo iteminfo = infoForPosition(i);
        if(iteminfo == null)
            return 0;
        int j = getPaddedWidth();
        i = (int)((float)j * MathUtils.constrain(iteminfo.offset, mFirstOffset, mLastOffset));
        if(isLayoutRtl())
            return 0x1000000 - (int)((float)j * iteminfo.widthFactor + 0.5F) - i;
        else
            return i;
    }

    private int getPaddedWidth()
    {
        return getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }

    private int getScrollStart()
    {
        if(isLayoutRtl())
            return 0x1000000 - getScrollX();
        else
            return getScrollX();
    }

    private ItemInfo infoForFirstVisiblePage()
    {
        int i = getScrollStart();
        int j = getPaddedWidth();
        boolean flag;
        float f;
        float f1;
        int l;
        float f2;
        float f3;
        ItemInfo iteminfo;
        int i1;
        if(j > 0)
            f = (float)i / (float)j;
        else
            f = 0.0F;
        if(j > 0)
            f1 = (float)mPageMargin / (float)j;
        else
            f1 = 0.0F;
        l = -1;
        f2 = 0.0F;
        f3 = 0.0F;
        flag = true;
        iteminfo = null;
        i1 = mItems.size();
        for(int k = 0; k < i1;)
        {
            ItemInfo iteminfo1 = (ItemInfo)mItems.get(k);
            int j1 = k;
            ItemInfo iteminfo2 = iteminfo1;
            if(!flag)
            {
                j1 = k;
                iteminfo2 = iteminfo1;
                if(iteminfo1.position != l + 1)
                {
                    iteminfo2 = mTempItem;
                    iteminfo2.offset = f2 + f3 + f1;
                    iteminfo2.position = l + 1;
                    iteminfo2.widthFactor = mAdapter.getPageWidth(iteminfo2.position);
                    j1 = k - 1;
                }
            }
            f2 = iteminfo2.offset;
            if(flag || f >= f2)
            {
                if(f < iteminfo2.widthFactor + f2 + f1 || j1 == mItems.size() - 1)
                    return iteminfo2;
            } else
            {
                return iteminfo;
            }
            flag = false;
            l = iteminfo2.position;
            f3 = iteminfo2.widthFactor;
            k = j1 + 1;
            iteminfo = iteminfo2;
        }

        return iteminfo;
    }

    private boolean isGutterDrag(float f, float f1)
    {
        boolean flag;
        flag = true;
        break MISSING_BLOCK_LABEL_2;
        if((f >= (float)mGutterSize || f1 <= 0.0F) && (f <= (float)(getWidth() - mGutterSize) || f1 >= 0.0F))
            flag = false;
        return flag;
    }

    private void onSecondaryPointerUp(MotionEvent motionevent)
    {
        int i = motionevent.getActionIndex();
        if(motionevent.getPointerId(i) == mActivePointerId)
        {
            if(i == 0)
                i = 1;
            else
                i = 0;
            mLastMotionX = motionevent.getX(i);
            mActivePointerId = motionevent.getPointerId(i);
            if(mVelocityTracker != null)
                mVelocityTracker.clear();
        }
    }

    private boolean pageScrolled(int i)
    {
        if(mItems.size() == 0)
        {
            mCalledSuper = false;
            onPageScrolled(0, 0.0F, 0);
            if(!mCalledSuper)
                throw new IllegalStateException("onPageScrolled did not call superclass implementation");
            else
                return false;
        }
        if(isLayoutRtl())
            i = 0x1000000 - i;
        ItemInfo iteminfo = infoForFirstVisiblePage();
        int j = getPaddedWidth();
        int k = mPageMargin;
        float f = (float)mPageMargin / (float)j;
        int l = iteminfo.position;
        f = ((float)i / (float)j - iteminfo.offset) / (iteminfo.widthFactor + f);
        i = (int)((float)(j + k) * f);
        mCalledSuper = false;
        onPageScrolled(l, f, i);
        if(!mCalledSuper)
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        else
            return true;
    }

    private boolean performDrag(float f)
    {
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        int i = getPaddedWidth();
        float f1 = mLastMotionX;
        mLastMotionX = f;
        EdgeEffect edgeeffect;
        EdgeEffect edgeeffect1;
        ItemInfo iteminfo;
        boolean flag3;
        boolean flag4;
        float f2;
        if(isLayoutRtl())
        {
            edgeeffect = mRightEdge;
            edgeeffect1 = mLeftEdge;
        } else
        {
            edgeeffect = mLeftEdge;
            edgeeffect1 = mRightEdge;
        }
        f = (float)getScrollX() + (f1 - f);
        if(isLayoutRtl())
            f = 1.677722E+007F - f;
        iteminfo = (ItemInfo)mItems.get(0);
        if(iteminfo.position == 0)
            flag3 = true;
        else
            flag3 = false;
        if(flag3)
            f1 = iteminfo.offset * (float)i;
        else
            f1 = (float)i * mFirstOffset;
        iteminfo = (ItemInfo)mItems.get(mItems.size() - 1);
        if(iteminfo.position == mAdapter.getCount() - 1)
            flag4 = true;
        else
            flag4 = false;
        if(flag4)
            f2 = iteminfo.offset * (float)i;
        else
            f2 = (float)i * mLastOffset;
        if(f < f1)
        {
            if(flag3)
            {
                edgeeffect.onPull(Math.abs(f1 - f) / (float)i);
                flag2 = true;
            }
            f = f1;
        } else
        if(f > f2)
        {
            flag2 = flag1;
            if(flag4)
            {
                edgeeffect1.onPull(Math.abs(f - f2) / (float)i);
                flag2 = true;
            }
            f = f2;
        } else
        {
            flag2 = flag;
        }
        if(isLayoutRtl())
            f = 1.677722E+007F - f;
        mLastMotionX = mLastMotionX + (f - (float)(int)f);
        scrollTo((int)f, getScrollY());
        pageScrolled((int)f);
        return flag2;
    }

    private void recomputeScrollPosition(int i, int j, int k, int l)
    {
        if(j <= 0 || !(mItems.isEmpty() ^ true)) goto _L2; else goto _L1
_L1:
        int i1 = getPaddingLeft();
        int j1 = getPaddingRight();
        int k1 = getPaddingLeft();
        int l1 = getPaddingRight();
        float f = (float)getScrollX() / (float)((j - k1 - l1) + l);
        l = (int)((float)((i - i1 - j1) + k) * f);
        scrollTo(l, getScrollY());
        if(!mScroller.isFinished())
        {
            j = mScroller.getDuration();
            k = mScroller.timePassed();
            ItemInfo iteminfo = infoForPosition(mCurItem);
            mScroller.startScroll(l, 0, (int)(iteminfo.offset * (float)i), 0, j - k);
        }
_L4:
        return;
_L2:
        ItemInfo iteminfo1 = infoForPosition(mCurItem);
        float f1;
        if(iteminfo1 != null)
            f1 = Math.min(iteminfo1.offset, mLastOffset);
        else
            f1 = 0.0F;
        i = (int)((float)(i - getPaddingLeft() - getPaddingRight()) * f1);
        if(i != getScrollX())
        {
            completeScroll(false);
            scrollTo(i, getScrollY());
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void removeNonDecorViews()
    {
        int j;
        for(int i = 0; i < getChildCount(); i = j + 1)
        {
            j = i;
            if(!((LayoutParams)getChildAt(i).getLayoutParams()).isDecor)
            {
                removeViewAt(i);
                j = i - 1;
            }
        }

    }

    private void requestParentDisallowInterceptTouchEvent(boolean flag)
    {
        ViewParent viewparent = getParent();
        if(viewparent != null)
            viewparent.requestDisallowInterceptTouchEvent(flag);
    }

    private void scrollToItem(int i, boolean flag, int j, boolean flag1)
    {
        int k = getLeftEdgeForItem(i);
        if(flag)
        {
            smoothScrollTo(k, 0, j);
            if(flag1 && mOnPageChangeListener != null)
                mOnPageChangeListener.onPageSelected(i);
            if(flag1 && mInternalPageChangeListener != null)
                mInternalPageChangeListener.onPageSelected(i);
        } else
        {
            if(flag1 && mOnPageChangeListener != null)
                mOnPageChangeListener.onPageSelected(i);
            if(flag1 && mInternalPageChangeListener != null)
                mInternalPageChangeListener.onPageSelected(i);
            completeScroll(false);
            scrollTo(k, 0);
            pageScrolled(k);
        }
    }

    private void setScrollState(int i)
    {
        boolean flag = false;
        if(mScrollState == i)
            return;
        mScrollState = i;
        if(mPageTransformer != null)
        {
            if(i != 0)
                flag = true;
            enableLayers(flag);
        }
        if(mOnPageChangeListener != null)
            mOnPageChangeListener.onPageScrollStateChanged(i);
    }

    private void setScrollingCacheEnabled(boolean flag)
    {
        if(mScrollingCacheEnabled != flag)
            mScrollingCacheEnabled = flag;
    }

    private void sortChildDrawingOrder()
    {
        if(mDrawingOrder != 0)
        {
            int i;
            if(mDrawingOrderedChildren == null)
                mDrawingOrderedChildren = new ArrayList();
            else
                mDrawingOrderedChildren.clear();
            i = getChildCount();
            for(int j = 0; j < i; j++)
            {
                View view = getChildAt(j);
                mDrawingOrderedChildren.add(view);
            }

            Collections.sort(mDrawingOrderedChildren, sPositionComparator);
        }
    }

    public void addFocusables(ArrayList arraylist, int i, int j)
    {
        int k = arraylist.size();
        int l = getDescendantFocusability();
        if(l != 0x60000)
        {
            for(int i1 = 0; i1 < getChildCount(); i1++)
            {
                View view = getChildAt(i1);
                if(view.getVisibility() != 0)
                    continue;
                ItemInfo iteminfo = infoForChild(view);
                if(iteminfo != null && iteminfo.position == mCurItem)
                    view.addFocusables(arraylist, i, j);
            }

        }
        if(l != 0x40000 || k == arraylist.size())
        {
            if(!isFocusable())
                return;
            if((j & 1) == 1 && isInTouchMode() && isFocusableInTouchMode() ^ true)
                return;
            if(arraylist != null)
                arraylist.add(this);
        }
    }

    ItemInfo addNewItem(int i, int j)
    {
        ItemInfo iteminfo = new ItemInfo();
        iteminfo.position = i;
        iteminfo.object = mAdapter.instantiateItem(this, i);
        iteminfo.widthFactor = mAdapter.getPageWidth(i);
        if(j < 0 || j >= mItems.size())
            mItems.add(iteminfo);
        else
            mItems.add(j, iteminfo);
        return iteminfo;
    }

    public void addTouchables(ArrayList arraylist)
    {
        for(int i = 0; i < getChildCount(); i++)
        {
            View view = getChildAt(i);
            if(view.getVisibility() != 0)
                continue;
            ItemInfo iteminfo = infoForChild(view);
            if(iteminfo != null && iteminfo.position == mCurItem)
                view.addTouchables(arraylist);
        }

    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        android.view.ViewGroup.LayoutParams layoutparams1 = layoutparams;
        if(!checkLayoutParams(layoutparams))
            layoutparams1 = generateLayoutParams(layoutparams);
        layoutparams = (LayoutParams)layoutparams1;
        layoutparams.isDecor = ((LayoutParams) (layoutparams)).isDecor | (view instanceof Decor);
        if(mInLayout)
        {
            if(layoutparams != null && ((LayoutParams) (layoutparams)).isDecor)
                throw new IllegalStateException("Cannot add pager decor view during layout");
            layoutparams.needsMeasure = true;
            addViewInLayout(view, i, layoutparams1);
        } else
        {
            super.addView(view, i, layoutparams1);
        }
    }

    public boolean arrowScroll(int i)
    {
        View view = findFocus();
        if(view != this) goto _L2; else goto _L1
_L1:
        Object obj = null;
_L4:
        boolean flag = false;
        view = FocusFinder.getInstance().findNextFocus(this, ((View) (obj)), i);
        if(view != null && view != obj)
        {
            if(i == 17)
            {
                int j = getChildRectInPagerCoordinates(mTempRect, view).left;
                int l = getChildRectInPagerCoordinates(mTempRect, ((View) (obj))).left;
                boolean flag1;
                StringBuilder stringbuilder;
                if(obj != null && j >= l)
                    flag = pageLeft();
                else
                    flag = view.requestFocus();
            } else
            if(i == 66)
            {
                int i1 = getChildRectInPagerCoordinates(mTempRect, view).left;
                int k = getChildRectInPagerCoordinates(mTempRect, ((View) (obj))).left;
                if(obj != null && i1 <= k)
                    flag = pageRight();
                else
                    flag = view.requestFocus();
            }
        } else
        if(i == 17 || i == 1)
            flag = pageLeft();
        else
        if(i == 66 || i == 2)
            flag = pageRight();
        if(flag)
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
        return flag;
_L2:
        obj = view;
        if(view == null) goto _L4; else goto _L3
_L3:
        j = 0;
        obj = view.getParent();
_L5:
label0:
        {
            flag1 = j;
            if(obj instanceof ViewGroup)
            {
                if(obj != this)
                    break label0;
                flag1 = true;
            }
            obj = view;
            if(!flag1)
            {
                stringbuilder = new StringBuilder();
                stringbuilder.append(view.getClass().getSimpleName());
                for(obj = view.getParent(); obj instanceof ViewGroup; obj = ((ViewParent) (obj)).getParent())
                    stringbuilder.append(" => ").append(obj.getClass().getSimpleName());

                break MISSING_BLOCK_LABEL_214;
            }
        }
          goto _L4
        obj = ((ViewParent) (obj)).getParent();
          goto _L5
        Log.e("ViewPager", (new StringBuilder()).append("arrowScroll tried to find focus based on non-child current focused view ").append(stringbuilder.toString()).toString());
        obj = null;
          goto _L4
    }

    protected boolean canScroll(View view, boolean flag, int i, int j, int k)
    {
        if(view instanceof ViewGroup)
        {
            ViewGroup viewgroup = (ViewGroup)view;
            int l = view.getScrollX();
            int i1 = view.getScrollY();
            for(int j1 = viewgroup.getChildCount() - 1; j1 >= 0; j1--)
            {
                View view1 = viewgroup.getChildAt(j1);
                if(j + l >= view1.getLeft() && j + l < view1.getRight() && k + i1 >= view1.getTop() && k + i1 < view1.getBottom() && canScroll(view1, true, i, (j + l) - view1.getLeft(), (k + i1) - view1.getTop()))
                    return true;
            }

        }
        if(flag)
            flag = view.canScrollHorizontally(-i);
        else
            flag = false;
        return flag;
    }

    public boolean canScrollHorizontally(int i)
    {
        boolean flag = true;
        boolean flag1 = true;
        if(mAdapter == null)
            return false;
        int j = getPaddedWidth();
        int k = getScrollX();
        if(i < 0)
        {
            if(k <= (int)((float)j * mFirstOffset))
                flag1 = false;
            return flag1;
        }
        if(i > 0)
        {
            boolean flag2;
            if(k < (int)((float)j * mLastOffset))
                flag2 = flag;
            else
                flag2 = false;
            return flag2;
        } else
        {
            return false;
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        boolean flag;
        if(layoutparams instanceof LayoutParams)
            flag = super.checkLayoutParams(layoutparams);
        else
            flag = false;
        return flag;
    }

    public void computeScroll()
    {
        if(!mScroller.isFinished() && mScroller.computeScrollOffset())
        {
            int i = getScrollX();
            int j = getScrollY();
            int k = mScroller.getCurrX();
            int l = mScroller.getCurrY();
            if(i != k || j != l)
            {
                scrollTo(k, l);
                if(!pageScrolled(k))
                {
                    mScroller.abortAnimation();
                    scrollTo(0, l);
                }
            }
            postInvalidateOnAnimation();
            return;
        } else
        {
            completeScroll(true);
            return;
        }
    }

    void dataSetChanged()
    {
        int i = mAdapter.getCount();
        mExpectedAdapterCount = i;
        boolean flag;
        int k;
        boolean flag1;
        int i1;
        if(mItems.size() < mOffscreenPageLimit * 2 + 1)
        {
            if(mItems.size() < i)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        k = mCurItem;
        flag1 = false;
        i1 = 0;
        while(i1 < mItems.size()) 
        {
            ItemInfo iteminfo = (ItemInfo)mItems.get(i1);
            int j1 = mAdapter.getItemPosition(iteminfo.object);
            int k1;
            int l1;
            int i2;
            if(j1 == -1)
            {
                k1 = k;
                l1 = ((flag1) ? 1 : 0);
                i2 = i1;
            } else
            if(j1 == -2)
            {
                mItems.remove(i1);
                j1 = i1 - 1;
                i1 = ((flag1) ? 1 : 0);
                if(!flag1)
                {
                    mAdapter.startUpdate(this);
                    i1 = 1;
                }
                mAdapter.destroyItem(this, iteminfo.position, iteminfo.object);
                flag = true;
                i2 = j1;
                l1 = i1;
                k1 = k;
                if(mCurItem == iteminfo.position)
                {
                    k1 = Math.max(0, Math.min(mCurItem, i - 1));
                    flag = true;
                    i2 = j1;
                    l1 = i1;
                }
            } else
            {
                i2 = i1;
                l1 = ((flag1) ? 1 : 0);
                k1 = k;
                if(iteminfo.position != j1)
                {
                    if(iteminfo.position == mCurItem)
                        k = j1;
                    iteminfo.position = j1;
                    flag = true;
                    i2 = i1;
                    l1 = ((flag1) ? 1 : 0);
                    k1 = k;
                }
            }
            i1 = i2 + 1;
            flag1 = l1;
            k = k1;
        }
        if(flag1)
            mAdapter.finishUpdate(this);
        Collections.sort(mItems, COMPARATOR);
        if(flag)
        {
            int l = getChildCount();
            for(int j = 0; j < l; j++)
            {
                LayoutParams layoutparams = (LayoutParams)getChildAt(j).getLayoutParams();
                if(!layoutparams.isDecor)
                    layoutparams.widthFactor = 0.0F;
            }

            setCurrentItemInternal(k, false, true);
            requestLayout();
        }
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

    float distanceInfluenceForSnapDuration(float f)
    {
        return (float)Math.sin((float)((double)(f - 0.5F) * 0.4712389167638204D));
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        boolean flag = false;
        boolean flag1 = false;
        int i = getOverScrollMode();
        if(i == 0 || i == 1 && mAdapter != null && mAdapter.getCount() > 1)
        {
            if(!mLeftEdge.isFinished())
            {
                int l = canvas.save();
                int j = getHeight() - getPaddingTop() - getPaddingBottom();
                int j1 = getWidth();
                canvas.rotate(270F);
                canvas.translate(-j + getPaddingTop(), mFirstOffset * (float)j1);
                mLeftEdge.setSize(j, j1);
                flag1 = mLeftEdge.draw(canvas);
                canvas.restoreToCount(l);
            }
            flag = flag1;
            if(!mRightEdge.isFinished())
            {
                int l1 = canvas.save();
                int k1 = getWidth();
                int i1 = getHeight();
                int k = getPaddingTop();
                int i2 = getPaddingBottom();
                canvas.rotate(90F);
                canvas.translate(-getPaddingTop(), -(mLastOffset + 1.0F) * (float)k1);
                mRightEdge.setSize(i1 - k - i2, k1);
                flag = flag1 | mRightEdge.draw(canvas);
                canvas.restoreToCount(l1);
            }
        } else
        {
            mLeftEdge.finish();
            mRightEdge.finish();
        }
        if(flag)
            postInvalidateOnAnimation();
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        Drawable drawable = mMarginDrawable;
        if(drawable != null && drawable.isStateful() && drawable.setState(getDrawableState()))
            invalidateDrawable(drawable);
    }

    public boolean executeKeyEvent(KeyEvent keyevent)
    {
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = flag;
        if(keyevent.getAction() != 0) goto _L2; else goto _L1
_L1:
        keyevent.getKeyCode();
        JVM INSTR lookupswitch 3: default 48
    //                   21: 52
    //                   22: 62
    //                   61: 72;
           goto _L3 _L4 _L5 _L6
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = arrowScroll(17);
        continue; /* Loop/switch isn't completed */
_L5:
        flag1 = arrowScroll(66);
        continue; /* Loop/switch isn't completed */
_L6:
        if(keyevent.hasNoModifiers())
        {
            flag1 = arrowScroll(2);
        } else
        {
            flag1 = flag;
            if(keyevent.hasModifiers(1))
                flag1 = arrowScroll(1);
        }
        if(true) goto _L2; else goto _L7
_L7:
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams();
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return generateDefaultLayoutParams();
    }

    public PagerAdapter getAdapter()
    {
        return mAdapter;
    }

    protected int getChildDrawingOrder(int i, int j)
    {
        if(mDrawingOrder == 2)
            i = i - 1 - j;
        else
            i = j;
        return ((LayoutParams)((View)mDrawingOrderedChildren.get(i)).getLayoutParams()).childIndex;
    }

    public Object getCurrent()
    {
        Object obj = null;
        ItemInfo iteminfo = infoForPosition(getCurrentItem());
        if(iteminfo != null)
            obj = iteminfo.object;
        return obj;
    }

    public int getCurrentItem()
    {
        return mCurItem;
    }

    public int getOffscreenPageLimit()
    {
        return mOffscreenPageLimit;
    }

    public int getPageMargin()
    {
        return mPageMargin;
    }

    ItemInfo infoForAnyChild(View view)
    {
        do
        {
            ViewParent viewparent = view.getParent();
            if(viewparent != this)
            {
                if(viewparent == null || (viewparent instanceof View) ^ true)
                    return null;
                view = (View)viewparent;
            } else
            {
                return infoForChild(view);
            }
        } while(true);
    }

    ItemInfo infoForChild(View view)
    {
        for(int i = 0; i < mItems.size(); i++)
        {
            ItemInfo iteminfo = (ItemInfo)mItems.get(i);
            if(mAdapter.isViewFromObject(view, iteminfo.object))
                return iteminfo;
        }

        return null;
    }

    ItemInfo infoForPosition(int i)
    {
        for(int j = 0; j < mItems.size(); j++)
        {
            ItemInfo iteminfo = (ItemInfo)mItems.get(j);
            if(iteminfo.position == i)
                return iteminfo;
        }

        return null;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        mFirstLayout = true;
    }

    protected void onDetachedFromWindow()
    {
        removeCallbacks(mEndScrollRunnable);
        super.onDetachedFromWindow();
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if(mPageMargin <= 0 || mMarginDrawable == null || mItems.size() <= 0 || mAdapter == null) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        float f;
        int k;
        Object obj;
        float f1;
        int l;
        int i1;
        int j1;
        i = getScrollX();
        j = getWidth();
        f = (float)mPageMargin / (float)j;
        k = 0;
        obj = (ItemInfo)mItems.get(0);
        f1 = ((ItemInfo) (obj)).offset;
        l = mItems.size();
        i1 = ((ItemInfo) (obj)).position;
        j1 = ((ItemInfo)mItems.get(l - 1)).position;
_L6:
        if(i1 >= j1) goto _L2; else goto _L3
_L3:
        for(; i1 > ((ItemInfo) (obj)).position && k < l; obj = (ItemInfo)((ArrayList) (obj)).get(k))
        {
            obj = mItems;
            k++;
        }

        float f2;
        float f3;
        if(i1 == ((ItemInfo) (obj)).position)
        {
            f2 = ((ItemInfo) (obj)).offset;
            f1 = ((ItemInfo) (obj)).widthFactor;
        } else
        {
            f3 = mAdapter.getPageWidth(i1);
            f2 = f1;
            f1 = f3;
        }
        f3 = f2 * (float)j;
        if(isLayoutRtl())
            f3 = 1.677722E+007F - f3;
        else
            f3 += (float)j * f1;
        f1 = f2 + f1 + f;
        if((float)mPageMargin + f3 > (float)i)
        {
            mMarginDrawable.setBounds((int)f3, mTopPageBounds, (int)((float)mPageMargin + f3 + 0.5F), mBottomPageBounds);
            mMarginDrawable.draw(canvas);
        }
        if(f3 <= (float)(i + j)) goto _L4; else goto _L2
_L2:
        return;
_L4:
        i1++;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEvent(accessibilityevent);
        accessibilityevent.setClassName(com/android/internal/widget/ViewPager.getName());
        accessibilityevent.setScrollable(canScroll());
        if(accessibilityevent.getEventType() == 4096 && mAdapter != null)
        {
            accessibilityevent.setItemCount(mAdapter.getCount());
            accessibilityevent.setFromIndex(mCurItem);
            accessibilityevent.setToIndex(mCurItem);
        }
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
        accessibilitynodeinfo.setClassName(com/android/internal/widget/ViewPager.getName());
        accessibilitynodeinfo.setScrollable(canScroll());
        if(canScrollHorizontally(1))
        {
            accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
            accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT);
        }
        if(canScrollHorizontally(-1))
        {
            accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
            accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        int i;
        i = motionevent.getAction() & 0xff;
        if(i == 3 || i == 1)
        {
            mIsBeingDragged = false;
            mIsUnableToDrag = false;
            mActivePointerId = -1;
            if(mVelocityTracker != null)
            {
                mVelocityTracker.recycle();
                mVelocityTracker = null;
            }
            return false;
        }
        if(i != 0)
        {
            if(mIsBeingDragged)
                return true;
            if(mIsUnableToDrag)
                return false;
        }
        i;
        JVM INSTR lookupswitch 3: default 112
    //                   0: 382
    //                   2: 139
    //                   6: 520;
           goto _L1 _L2 _L3 _L4
_L1:
        if(mVelocityTracker == null)
            mVelocityTracker = VelocityTracker.obtain();
        mVelocityTracker.addMovement(motionevent);
        return mIsBeingDragged;
_L3:
        float f;
        float f1;
        float f2;
        float f3;
        float f4;
        int j = mActivePointerId;
        if(j == -1)
            continue; /* Loop/switch isn't completed */
        j = motionevent.findPointerIndex(j);
        f = motionevent.getX(j);
        f1 = f - mLastMotionX;
        f2 = Math.abs(f1);
        f3 = motionevent.getY(j);
        f4 = Math.abs(f3 - mInitialMotionY);
        if(f1 != 0.0F && isGutterDrag(mLastMotionX, f1) ^ true && canScroll(this, false, (int)f1, (int)f, (int)f3))
        {
            mLastMotionX = f;
            mLastMotionY = f3;
            mIsUnableToDrag = true;
            return false;
        }
        if(f2 <= (float)mTouchSlop || 0.5F * f2 <= f4) goto _L6; else goto _L5
_L5:
        mIsBeingDragged = true;
        requestParentDisallowInterceptTouchEvent(true);
        setScrollState(1);
        if(f1 > 0.0F)
            f4 = mInitialMotionX + (float)mTouchSlop;
        else
            f4 = mInitialMotionX - (float)mTouchSlop;
        mLastMotionX = f4;
        mLastMotionY = f3;
        setScrollingCacheEnabled(true);
_L7:
        if(mIsBeingDragged && performDrag(f))
            postInvalidateOnAnimation();
        continue; /* Loop/switch isn't completed */
_L6:
        if(f4 > (float)mTouchSlop)
            mIsUnableToDrag = true;
        if(true) goto _L7; else goto _L2
_L2:
        float f5 = motionevent.getX();
        mInitialMotionX = f5;
        mLastMotionX = f5;
        f5 = motionevent.getY();
        mInitialMotionY = f5;
        mLastMotionY = f5;
        mActivePointerId = motionevent.getPointerId(0);
        mIsUnableToDrag = false;
        mScroller.computeScrollOffset();
        if(mScrollState == 2 && Math.abs(mScroller.getFinalX() - mScroller.getCurrX()) > mCloseEnough)
        {
            mScroller.abortAnimation();
            mPopulatePending = false;
            populate();
            mIsBeingDragged = true;
            requestParentDisallowInterceptTouchEvent(true);
            setScrollState(1);
        } else
        {
            completeScroll(false);
            mIsBeingDragged = false;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        onSecondaryPointerUp(motionevent);
        if(true) goto _L1; else goto _L8
_L8:
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        i1 = getChildCount();
        j1 = k - i;
        k1 = l - j;
        j = getPaddingLeft();
        i = getPaddingTop();
        l1 = getPaddingRight();
        l = getPaddingBottom();
        i2 = getScrollX();
        j2 = 0;
        k2 = 0;
_L17:
        if(k2 >= i1) goto _L2; else goto _L1
_L1:
        View view;
        int i3;
        int j3;
        int k3;
        int i4;
        view = getChildAt(k2);
        i3 = j2;
        j3 = l;
        k3 = j;
        i4 = l1;
        k = i;
        if(view.getVisibility() == 8) goto _L4; else goto _L3
_L3:
        LayoutParams layoutparams1;
        layoutparams1 = (LayoutParams)view.getLayoutParams();
        i3 = j2;
        j3 = l;
        k3 = j;
        i4 = l1;
        k = i;
        if(!layoutparams1.isDecor) goto _L4; else goto _L5
_L5:
        k = layoutparams1.gravity;
        i4 = layoutparams1.gravity;
        k & 7;
        JVM INSTR tableswitch 1 5: default 184
    //                   1 316
    //                   2 184
    //                   3 301
    //                   4 184
    //                   5 338;
           goto _L6 _L7 _L6 _L8 _L6 _L9
_L6:
        k = j;
        k3 = j;
_L14:
        i4 & 0x70;
        JVM INSTR lookupswitch 3: default 228
    //                   16: 380
    //                   48: 367
    //                   80: 398;
           goto _L10 _L11 _L12 _L13
_L10:
        j = i;
_L15:
        k += i2;
        view.layout(k, j, view.getMeasuredWidth() + k, view.getMeasuredHeight() + j);
        i3 = j2 + 1;
        k = i;
        i4 = l1;
        j3 = l;
_L4:
        k2++;
        j2 = i3;
        l = j3;
        j = k3;
        l1 = i4;
        i = k;
        continue; /* Loop/switch isn't completed */
_L8:
        k = j;
        k3 = j + view.getMeasuredWidth();
          goto _L14
_L7:
        k = Math.max((j1 - view.getMeasuredWidth()) / 2, j);
        k3 = j;
          goto _L14
_L9:
        k = j1 - l1 - view.getMeasuredWidth();
        l1 += view.getMeasuredWidth();
        k3 = j;
          goto _L14
_L12:
        j = i;
        i += view.getMeasuredHeight();
          goto _L15
_L11:
        j = Math.max((k1 - view.getMeasuredHeight()) / 2, i);
          goto _L15
_L13:
        j = k1 - l - view.getMeasuredHeight();
        l += view.getMeasuredHeight();
          goto _L15
_L2:
        int l2 = j1 - j - l1;
        k = 0;
        while(k < i1) 
        {
            View view1 = getChildAt(k);
            if(view1.getVisibility() != 8)
            {
                LayoutParams layoutparams = (LayoutParams)view1.getLayoutParams();
                if(!layoutparams.isDecor)
                {
                    ItemInfo iteminfo = infoForChild(view1);
                    if(iteminfo != null)
                    {
                        if(layoutparams.needsMeasure)
                        {
                            layoutparams.needsMeasure = false;
                            view1.measure(android.view.View.MeasureSpec.makeMeasureSpec((int)((float)l2 * layoutparams.widthFactor), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(k1 - i - l, 0x40000000));
                        }
                        int j4 = view1.getMeasuredWidth();
                        int l3 = (int)((float)l2 * iteminfo.offset);
                        if(isLayoutRtl())
                            l3 = 0x1000000 - l1 - l3 - j4;
                        else
                            l3 = j + l3;
                        view1.layout(l3, i, l3 + j4, view1.getMeasuredHeight() + i);
                    }
                }
            }
            k++;
        }
        mTopPageBounds = i;
        mBottomPageBounds = k1 - l;
        mDecorChildCount = j2;
        if(mFirstLayout)
            scrollToItem(mCurItem, false, 0, false);
        mFirstLayout = false;
        return;
        if(true) goto _L17; else goto _L16
_L16:
    }

    protected void onMeasure(int i, int j)
    {
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, j));
        i = getMeasuredWidth();
        mGutterSize = Math.min(i / 10, mDefaultGutterSize);
        i = i - getPaddingLeft() - getPaddingRight();
        j = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        int k = getChildCount();
        int l = 0;
        do
        {
            if(l < k)
            {
                View view = getChildAt(l);
                int i1 = j;
                int k1 = i;
                if(view.getVisibility() != 8)
                {
                    LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                    i1 = j;
                    k1 = i;
                    if(layoutparams != null)
                    {
                        i1 = j;
                        k1 = i;
                        if(layoutparams.isDecor)
                        {
                            k1 = layoutparams.gravity & 7;
                            int l1 = layoutparams.gravity & 0x70;
                            int i2 = 0x80000000;
                            i1 = 0x80000000;
                            boolean flag;
                            boolean flag1;
                            int j2;
                            int k2;
                            if(l1 == 48 || l1 == 80)
                                flag = true;
                            else
                                flag = false;
                            if(k1 == 3 || k1 == 5)
                                flag1 = true;
                            else
                                flag1 = false;
                            if(flag)
                            {
                                k1 = 0x40000000;
                            } else
                            {
                                k1 = i2;
                                if(flag1)
                                {
                                    i1 = 0x40000000;
                                    k1 = i2;
                                }
                            }
                            j2 = i;
                            i2 = j;
                            k2 = k1;
                            k1 = j2;
                            if(layoutparams.width != -2)
                            {
                                int l2 = 0x40000000;
                                k2 = l2;
                                k1 = j2;
                                if(layoutparams.width != -1)
                                {
                                    k1 = layoutparams.width;
                                    k2 = l2;
                                }
                            }
                            j2 = i2;
                            if(layoutparams.height != -2)
                            {
                                int i3 = 0x40000000;
                                i1 = i3;
                                j2 = i2;
                                if(layoutparams.height != -1)
                                {
                                    j2 = layoutparams.height;
                                    i1 = i3;
                                }
                            }
                            view.measure(android.view.View.MeasureSpec.makeMeasureSpec(k1, k2), android.view.View.MeasureSpec.makeMeasureSpec(j2, i1));
                            if(flag)
                            {
                                i1 = j - view.getMeasuredHeight();
                                k1 = i;
                            } else
                            {
                                i1 = j;
                                k1 = i;
                                if(flag1)
                                {
                                    k1 = i - view.getMeasuredWidth();
                                    i1 = j;
                                }
                            }
                        }
                    }
                }
                l++;
                j = i1;
                i = k1;
                continue;
            }
            mChildWidthMeasureSpec = android.view.View.MeasureSpec.makeMeasureSpec(i, 0x40000000);
            mChildHeightMeasureSpec = android.view.View.MeasureSpec.makeMeasureSpec(j, 0x40000000);
            mInLayout = true;
            populate();
            mInLayout = false;
            int j1 = getChildCount();
            for(j = 0; j < j1; j++)
            {
                View view1 = getChildAt(j);
                if(view1.getVisibility() == 8)
                    continue;
                LayoutParams layoutparams1 = (LayoutParams)view1.getLayoutParams();
                if(layoutparams1 == null || layoutparams1.isDecor ^ true)
                    view1.measure(android.view.View.MeasureSpec.makeMeasureSpec((int)((float)i * layoutparams1.widthFactor), 0x40000000), mChildHeightMeasureSpec);
            }

            return;
        } while(true);
    }

    protected void onPageScrolled(int i, float f, int j)
    {
        int k;
        int l;
        int j1;
        int k1;
        int l1;
        int i2;
        if(mDecorChildCount <= 0)
            break MISSING_BLOCK_LABEL_247;
        k = getScrollX();
        l = getPaddingLeft();
        j1 = getPaddingRight();
        k1 = getWidth();
        l1 = getChildCount();
        i2 = 0;
_L2:
        View view;
        LayoutParams layoutparams;
        int j2;
        int k2;
        if(i2 >= l1)
            break MISSING_BLOCK_LABEL_247;
        view = getChildAt(i2);
        layoutparams = (LayoutParams)view.getLayoutParams();
        if(layoutparams.isDecor)
            break; /* Loop/switch isn't completed */
        j2 = j1;
        k2 = l;
_L7:
        i2++;
        l = k2;
        j1 = j2;
        if(true) goto _L2; else goto _L1
_L1:
        layoutparams.gravity & 7;
        JVM INSTR tableswitch 1 5: default 136
    //                   1 201
    //                   2 136
    //                   3 184
    //                   4 136
    //                   5 221;
           goto _L3 _L4 _L3 _L5 _L3 _L6
_L6:
        break MISSING_BLOCK_LABEL_221;
_L3:
        j2 = l;
_L8:
        int l2 = (j2 + k) - view.getLeft();
        k2 = l;
        j2 = j1;
        if(l2 != 0)
        {
            view.offsetLeftAndRight(l2);
            k2 = l;
            j2 = j1;
        }
          goto _L7
_L5:
        j2 = l;
        l += view.getWidth();
          goto _L8
_L4:
        j2 = Math.max((k1 - view.getMeasuredWidth()) / 2, l);
          goto _L8
        j2 = k1 - j1 - view.getMeasuredWidth();
        j1 += view.getMeasuredWidth();
          goto _L8
        if(mOnPageChangeListener != null)
            mOnPageChangeListener.onPageScrolled(i, f, j);
        if(mInternalPageChangeListener != null)
            mInternalPageChangeListener.onPageScrolled(i, f, j);
        if(mPageTransformer != null)
        {
            j = getScrollX();
            int i1 = getChildCount();
            i = 0;
            while(i < i1) 
            {
                View view1 = getChildAt(i);
                if(!((LayoutParams)view1.getLayoutParams()).isDecor)
                {
                    f = (float)(view1.getLeft() - j) / (float)getPaddedWidth();
                    mPageTransformer.transformPage(view1, f);
                }
                i++;
            }
        }
        mCalledSuper = true;
        return;
          goto _L7
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect)
    {
        int j = getChildCount();
        int k;
        byte byte0;
        if((i & 2) != 0)
        {
            k = 0;
            byte0 = 1;
        } else
        {
            k = j - 1;
            byte0 = -1;
            j = -1;
        }
        for(; k != j; k += byte0)
        {
            View view = getChildAt(k);
            if(view.getVisibility() != 0)
                continue;
            ItemInfo iteminfo = infoForChild(view);
            if(iteminfo != null && iteminfo.position == mCurItem && view.requestFocus(i, rect))
                return true;
        }

        return false;
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        if(!(parcelable instanceof SavedState))
        {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        parcelable = (SavedState)parcelable;
        super.onRestoreInstanceState(parcelable.getSuperState());
        if(mAdapter != null)
        {
            mAdapter.restoreState(((SavedState) (parcelable)).adapterState, ((SavedState) (parcelable)).loader);
            setCurrentItemInternal(((SavedState) (parcelable)).position, false, true);
        } else
        {
            mRestoredCurItem = ((SavedState) (parcelable)).position;
            mRestoredAdapterState = ((SavedState) (parcelable)).adapterState;
            mRestoredClassLoader = ((SavedState) (parcelable)).loader;
        }
    }

    public void onRtlPropertiesChanged(int i)
    {
        super.onRtlPropertiesChanged(i);
        if(i == 0)
            mLeftIncr = -1;
        else
            mLeftIncr = 1;
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        savedstate.position = mCurItem;
        if(mAdapter != null)
            savedstate.adapterState = mAdapter.saveState();
        return savedstate;
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        if(i != k)
            recomputeScrollPosition(i, k, mPageMargin, mPageMargin);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i;
        boolean flag;
        boolean flag1;
        if(motionevent.getAction() == 0 && motionevent.getEdgeFlags() != 0)
            return false;
        if(mAdapter == null || mAdapter.getCount() == 0)
            return false;
        if(mVelocityTracker == null)
            mVelocityTracker = VelocityTracker.obtain();
        mVelocityTracker.addMovement(motionevent);
        i = motionevent.getAction();
        flag = false;
        flag1 = flag;
        i & 0xff;
        JVM INSTR tableswitch 0 6: default 116
    //                   0 130
    //                   1 391
    //                   2 197
    //                   3 568
    //                   4 119
    //                   5 618
    //                   6 647;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L6:
        break; /* Loop/switch isn't completed */
_L1:
        flag1 = flag;
_L10:
        if(flag1)
            postInvalidateOnAnimation();
        return true;
_L2:
        mScroller.abortAnimation();
        mPopulatePending = false;
        populate();
        float f = motionevent.getX();
        mInitialMotionX = f;
        mLastMotionX = f;
        f = motionevent.getY();
        mInitialMotionY = f;
        mLastMotionY = f;
        mActivePointerId = motionevent.getPointerId(0);
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
_L4:
        if(!mIsBeingDragged)
        {
            int j = motionevent.findPointerIndex(mActivePointerId);
            float f1 = motionevent.getX(j);
            float f3 = Math.abs(f1 - mLastMotionX);
            float f4 = motionevent.getY(j);
            float f5 = Math.abs(f4 - mLastMotionY);
            if(f3 > (float)mTouchSlop && f3 > f5)
            {
                mIsBeingDragged = true;
                requestParentDisallowInterceptTouchEvent(true);
                ViewParent viewparent;
                if(f1 - mInitialMotionX > 0.0F)
                    f1 = mInitialMotionX + (float)mTouchSlop;
                else
                    f1 = mInitialMotionX - (float)mTouchSlop;
                mLastMotionX = f1;
                mLastMotionY = f4;
                setScrollState(1);
                setScrollingCacheEnabled(true);
                viewparent = getParent();
                if(viewparent != null)
                    viewparent.requestDisallowInterceptTouchEvent(true);
            }
        }
        flag1 = flag;
        if(mIsBeingDragged)
            flag1 = performDrag(motionevent.getX(motionevent.findPointerIndex(mActivePointerId)));
        continue; /* Loop/switch isn't completed */
_L3:
        flag1 = flag;
        if(mIsBeingDragged)
        {
            Object obj = mVelocityTracker;
            ((VelocityTracker) (obj)).computeCurrentVelocity(1000, mMaximumVelocity);
            int k = (int)((VelocityTracker) (obj)).getXVelocity(mActivePointerId);
            mPopulatePending = true;
            float f2 = (float)getScrollStart() / (float)getPaddedWidth();
            obj = infoForFirstVisiblePage();
            int i1 = ((ItemInfo) (obj)).position;
            if(isLayoutRtl())
                f2 = (((ItemInfo) (obj)).offset - f2) / ((ItemInfo) (obj)).widthFactor;
            else
                f2 = (f2 - ((ItemInfo) (obj)).offset) / ((ItemInfo) (obj)).widthFactor;
            setCurrentItemInternal(determineTargetPage(i1, f2, k, (int)(motionevent.getX(motionevent.findPointerIndex(mActivePointerId)) - mInitialMotionX)), true, true, k);
            mActivePointerId = -1;
            endDrag();
            mLeftEdge.onRelease();
            mRightEdge.onRelease();
            flag1 = true;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        flag1 = flag;
        if(mIsBeingDragged)
        {
            scrollToItem(mCurItem, true, 0, false);
            mActivePointerId = -1;
            endDrag();
            mLeftEdge.onRelease();
            mRightEdge.onRelease();
            flag1 = true;
        }
        continue; /* Loop/switch isn't completed */
_L7:
        int l = motionevent.getActionIndex();
        mLastMotionX = motionevent.getX(l);
        mActivePointerId = motionevent.getPointerId(l);
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
_L8:
        onSecondaryPointerUp(motionevent);
        mLastMotionX = motionevent.getX(motionevent.findPointerIndex(mActivePointerId));
        flag1 = flag;
        if(true) goto _L10; else goto _L9
_L9:
    }

    boolean pageLeft()
    {
        return setCurrentItemInternal(mCurItem + mLeftIncr, true, false);
    }

    boolean pageRight()
    {
        return setCurrentItemInternal(mCurItem - mLeftIncr, true, false);
    }

    public boolean performAccessibilityAction(int i, Bundle bundle)
    {
        if(super.performAccessibilityAction(i, bundle))
            return true;
        switch(i)
        {
        default:
            return false;

        case 4096: 
        case 16908347: 
            if(canScrollHorizontally(1))
            {
                setCurrentItem(mCurItem + 1);
                return true;
            } else
            {
                return false;
            }

        case 8192: 
        case 16908345: 
            break;
        }
        if(canScrollHorizontally(-1))
        {
            setCurrentItem(mCurItem - 1);
            return true;
        } else
        {
            return false;
        }
    }

    public void populate()
    {
        populate(mCurItem);
    }

    void populate(int i)
    {
        Object obj;
        byte byte0;
        int k;
        int l;
        int i1;
        Object obj2;
        obj = null;
        byte0 = 2;
        if(mCurItem != i)
        {
            byte byte1;
            if(mCurItem < i)
                byte1 = 66;
            else
                byte1 = 17;
            obj = infoForPosition(mCurItem);
            mCurItem = i;
            byte0 = byte1;
        }
        if(mAdapter == null)
        {
            sortChildDrawingOrder();
            return;
        }
        if(mPopulatePending)
        {
            sortChildDrawingOrder();
            return;
        }
        if(getWindowToken() == null)
            return;
        mAdapter.startUpdate(this);
        i = mOffscreenPageLimit;
        k = Math.max(0, mCurItem - i);
        l = mAdapter.getCount();
        i1 = Math.min(l - 1, mCurItem + i);
        if(l != mExpectedAdapterCount)
        {
            String s;
            try
            {
                s = getResources().getResourceName(getId());
            }
            catch(android.content.res.Resources.NotFoundException notfoundexception)
            {
                notfoundexception = Integer.toHexString(getId());
            }
            throw new IllegalStateException((new StringBuilder()).append("The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: ").append(mExpectedAdapterCount).append(", found: ").append(l).append(" Pager id: ").append(s).append(" Pager class: ").append(getClass()).append(" Problematic adapter: ").append(mAdapter.getClass()).toString());
        }
        obj2 = null;
        i = 0;
_L29:
        Object obj1 = obj2;
        if(i >= mItems.size()) goto _L2; else goto _L1
_L1:
        ItemInfo iteminfo = (ItemInfo)mItems.get(i);
        if(iteminfo.position < mCurItem) goto _L4; else goto _L3
_L3:
        obj1 = obj2;
        if(iteminfo.position == mCurItem)
            obj1 = iteminfo;
_L2:
        obj2 = obj1;
        if(obj1 == null)
        {
            obj2 = obj1;
            if(l > 0)
                obj2 = addNewItem(mCurItem, i);
        }
        if(obj2 == null) goto _L6; else goto _L5
_L5:
        int j;
        float f;
        int j1;
        int k1;
        float f1;
        int l1;
        int i2;
        f = 0.0F;
        j1 = i - 1;
        if(j1 >= 0)
            obj1 = (ItemInfo)mItems.get(j1);
        else
            obj1 = null;
        k1 = getPaddedWidth();
        if(k1 <= 0)
            f1 = 0.0F;
        else
            f1 = (2.0F - ((ItemInfo) (obj2)).widthFactor) + (float)getPaddingLeft() / (float)k1;
        l1 = mCurItem - 1;
        iteminfo = ((ItemInfo) (obj1));
        i2 = i;
_L16:
        if(l1 < 0) goto _L8; else goto _L7
_L7:
        if(f < f1 || l1 >= k) goto _L10; else goto _L9
_L9:
        if(iteminfo != null) goto _L11; else goto _L8
_L8:
        f = ((ItemInfo) (obj2)).widthFactor;
        l1 = i2 + 1;
        if(f >= 2.0F) goto _L13; else goto _L12
_L12:
        float f2;
        if(l1 < mItems.size())
            obj1 = (ItemInfo)mItems.get(l1);
        else
            obj1 = null;
        if(k1 <= 0)
            f1 = 0.0F;
        else
            f1 = (float)getPaddingRight() / (float)k1 + 2.0F;
        j = mCurItem + 1;
        iteminfo = ((ItemInfo) (obj1));
_L18:
        if(j >= l) goto _L13; else goto _L14
_L14:
        if(f < f1 || j <= i1)
            break MISSING_BLOCK_LABEL_1104;
        if(iteminfo != null) goto _L15; else goto _L13
_L13:
        calculatePageOffsets(((ItemInfo) (obj2)), i2, ((ItemInfo) (obj)));
_L6:
        obj = mAdapter;
        i = mCurItem;
        float f3;
        if(obj2 != null)
            obj1 = ((ItemInfo) (obj2)).object;
        else
            obj1 = null;
        ((PagerAdapter) (obj)).setPrimaryItem(this, i, obj1);
        mAdapter.finishUpdate(this);
        j = getChildCount();
        for(i = 0; i < j; i++)
        {
            obj = getChildAt(i);
            obj1 = (LayoutParams)((View) (obj)).getLayoutParams();
            obj1.childIndex = i;
            if(!((LayoutParams) (obj1)).isDecor && ((LayoutParams) (obj1)).widthFactor == 0.0F)
            {
                obj = infoForChild(((View) (obj)));
                if(obj != null)
                {
                    obj1.widthFactor = ((ItemInfo) (obj)).widthFactor;
                    obj1.position = ((ItemInfo) (obj)).position;
                }
            }
        }

        break MISSING_BLOCK_LABEL_1231;
_L4:
        i++;
        continue; /* Loop/switch isn't completed */
_L11:
        i = i2;
        f2 = f;
        obj1 = iteminfo;
        j = j1;
        if(l1 == iteminfo.position)
        {
            i = i2;
            f2 = f;
            obj1 = iteminfo;
            j = j1;
            if(iteminfo.scrolling ^ true)
            {
                mItems.remove(j1);
                mAdapter.destroyItem(this, l1, iteminfo.object);
                j = j1 - 1;
                i = i2 - 1;
                if(j >= 0)
                {
                    obj1 = (ItemInfo)mItems.get(j);
                    f2 = f;
                } else
                {
                    obj1 = null;
                    f2 = f;
                }
            }
        }
_L17:
        l1--;
        i2 = i;
        f = f2;
        iteminfo = ((ItemInfo) (obj1));
        j1 = j;
          goto _L16
_L10:
        if(iteminfo != null && l1 == iteminfo.position)
        {
            f2 = f + iteminfo.widthFactor;
            j = j1 - 1;
            if(j >= 0)
            {
                obj1 = (ItemInfo)mItems.get(j);
                i = i2;
            } else
            {
                obj1 = null;
                i = i2;
            }
        } else
        {
            f2 = f + addNewItem(l1, j1 + 1).widthFactor;
            i = i2 + 1;
            if(j1 >= 0)
            {
                obj1 = (ItemInfo)mItems.get(j1);
                j = j1;
            } else
            {
                obj1 = null;
                j = j1;
            }
        }
          goto _L17
_L15:
        f3 = f;
        obj1 = iteminfo;
        i = l1;
        if(j == iteminfo.position)
        {
            f3 = f;
            obj1 = iteminfo;
            i = l1;
            if(iteminfo.scrolling ^ true)
            {
                mItems.remove(l1);
                mAdapter.destroyItem(this, j, iteminfo.object);
                if(l1 < mItems.size())
                {
                    obj1 = (ItemInfo)mItems.get(l1);
                    i = l1;
                    f3 = f;
                } else
                {
                    obj1 = null;
                    f3 = f;
                    i = l1;
                }
            }
        }
_L19:
        j++;
        f = f3;
        iteminfo = ((ItemInfo) (obj1));
        l1 = i;
          goto _L18
        if(iteminfo != null && j == iteminfo.position)
        {
            f3 = f + iteminfo.widthFactor;
            i = l1 + 1;
            if(i < mItems.size())
                obj1 = (ItemInfo)mItems.get(i);
            else
                obj1 = null;
        } else
        {
            obj1 = addNewItem(j, l1);
            i = l1 + 1;
            f3 = f + ((ItemInfo) (obj1)).widthFactor;
            if(i < mItems.size())
                obj1 = (ItemInfo)mItems.get(i);
            else
                obj1 = null;
        }
          goto _L19
        sortChildDrawingOrder();
        if(!hasFocus()) goto _L21; else goto _L20
_L20:
        obj = findFocus();
        if(obj != null)
            obj1 = infoForAnyChild(((View) (obj)));
        else
            obj1 = null;
        if(obj1 != null && ((ItemInfo) (obj1)).position == mCurItem) goto _L21; else goto _L22
_L22:
        i = 0;
_L27:
        if(i >= getChildCount()) goto _L21; else goto _L23
_L23:
        obj2 = getChildAt(i);
        obj1 = infoForChild(((View) (obj2)));
        if(obj1 == null || ((ItemInfo) (obj1)).position != mCurItem) goto _L25; else goto _L24
_L24:
        if(obj == null)
        {
            obj1 = null;
        } else
        {
            obj1 = mTempRect;
            ((View) (obj)).getFocusedRect(mTempRect);
            offsetDescendantRectToMyCoords(((View) (obj)), mTempRect);
            offsetRectIntoDescendantCoords(((View) (obj2)), mTempRect);
        }
        if(!((View) (obj2)).requestFocus(byte0, ((Rect) (obj1)))) goto _L25; else goto _L21
_L21:
        return;
_L25:
        i++;
        if(true) goto _L27; else goto _L26
_L26:
        if(true) goto _L29; else goto _L28
_L28:
    }

    public void removeView(View view)
    {
        if(mInLayout)
            removeViewInLayout(view);
        else
            super.removeView(view);
    }

    public void setAdapter(PagerAdapter pageradapter)
    {
        if(mAdapter != null)
        {
            mAdapter.unregisterDataSetObserver(mObserver);
            mAdapter.startUpdate(this);
            for(int i = 0; i < mItems.size(); i++)
            {
                ItemInfo iteminfo = (ItemInfo)mItems.get(i);
                mAdapter.destroyItem(this, iteminfo.position, iteminfo.object);
            }

            mAdapter.finishUpdate(this);
            mItems.clear();
            removeNonDecorViews();
            mCurItem = 0;
            scrollTo(0, 0);
        }
        PagerAdapter pageradapter1 = mAdapter;
        mAdapter = pageradapter;
        mExpectedAdapterCount = 0;
        if(mAdapter != null)
        {
            if(mObserver == null)
                mObserver = new PagerObserver(null);
            mAdapter.registerDataSetObserver(mObserver);
            mPopulatePending = false;
            boolean flag = mFirstLayout;
            mFirstLayout = true;
            mExpectedAdapterCount = mAdapter.getCount();
            if(mRestoredCurItem >= 0)
            {
                mAdapter.restoreState(mRestoredAdapterState, mRestoredClassLoader);
                setCurrentItemInternal(mRestoredCurItem, false, true);
                mRestoredCurItem = -1;
                mRestoredAdapterState = null;
                mRestoredClassLoader = null;
            } else
            if(!flag)
                populate();
            else
                requestLayout();
        }
        if(mAdapterChangeListener != null && pageradapter1 != pageradapter)
            mAdapterChangeListener.onAdapterChanged(pageradapter1, pageradapter);
    }

    public void setCurrentItem(int i)
    {
        mPopulatePending = false;
        setCurrentItemInternal(i, mFirstLayout ^ true, false);
    }

    public void setCurrentItem(int i, boolean flag)
    {
        mPopulatePending = false;
        setCurrentItemInternal(i, flag, false);
    }

    boolean setCurrentItemInternal(int i, boolean flag, boolean flag1)
    {
        return setCurrentItemInternal(i, flag, flag1, 0);
    }

    boolean setCurrentItemInternal(int i, boolean flag, boolean flag1, int j)
    {
        if(mAdapter == null || mAdapter.getCount() <= 0)
        {
            setScrollingCacheEnabled(false);
            return false;
        }
        int k = MathUtils.constrain(i, 0, mAdapter.getCount() - 1);
        if(!flag1 && mCurItem == k && mItems.size() != 0)
        {
            setScrollingCacheEnabled(false);
            return false;
        }
        i = mOffscreenPageLimit;
        if(k > mCurItem + i || k < mCurItem - i)
            for(i = 0; i < mItems.size(); i++)
                ((ItemInfo)mItems.get(i)).scrolling = true;

        if(mCurItem != k)
            flag1 = true;
        else
            flag1 = false;
        if(mFirstLayout)
        {
            mCurItem = k;
            if(flag1 && mOnPageChangeListener != null)
                mOnPageChangeListener.onPageSelected(k);
            if(flag1 && mInternalPageChangeListener != null)
                mInternalPageChangeListener.onPageSelected(k);
            requestLayout();
        } else
        {
            populate(k);
            scrollToItem(k, flag, j, flag1);
        }
        return true;
    }

    OnPageChangeListener setInternalPageChangeListener(OnPageChangeListener onpagechangelistener)
    {
        OnPageChangeListener onpagechangelistener1 = mInternalPageChangeListener;
        mInternalPageChangeListener = onpagechangelistener;
        return onpagechangelistener1;
    }

    public void setOffscreenPageLimit(int i)
    {
        int j = i;
        if(i < 1)
        {
            Log.w("ViewPager", (new StringBuilder()).append("Requested offscreen page limit ").append(i).append(" too small; defaulting to ").append(1).toString());
            j = 1;
        }
        if(j != mOffscreenPageLimit)
        {
            mOffscreenPageLimit = j;
            populate();
        }
    }

    void setOnAdapterChangeListener(OnAdapterChangeListener onadapterchangelistener)
    {
        mAdapterChangeListener = onadapterchangelistener;
    }

    public void setOnPageChangeListener(OnPageChangeListener onpagechangelistener)
    {
        mOnPageChangeListener = onpagechangelistener;
    }

    public void setPageMargin(int i)
    {
        int j = mPageMargin;
        mPageMargin = i;
        int k = getWidth();
        recomputeScrollPosition(k, k, i, j);
        requestLayout();
    }

    public void setPageMarginDrawable(int i)
    {
        setPageMarginDrawable(getContext().getDrawable(i));
    }

    public void setPageMarginDrawable(Drawable drawable)
    {
        mMarginDrawable = drawable;
        if(drawable != null)
            refreshDrawableState();
        boolean flag;
        if(drawable == null)
            flag = true;
        else
            flag = false;
        setWillNotDraw(flag);
        invalidate();
    }

    public void setPageTransformer(boolean flag, PageTransformer pagetransformer)
    {
        int i = 1;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        if(pagetransformer != null)
            flag1 = true;
        else
            flag1 = false;
        if(mPageTransformer != null)
            flag2 = true;
        else
            flag2 = false;
        if(flag1 != flag2)
            flag3 = true;
        else
            flag3 = false;
        mPageTransformer = pagetransformer;
        setChildrenDrawingOrderEnabled(flag1);
        if(flag1)
        {
            if(flag)
                i = 2;
            mDrawingOrder = i;
        } else
        {
            mDrawingOrder = 0;
        }
        if(flag3)
            populate();
    }

    void smoothScrollTo(int i, int j)
    {
        smoothScrollTo(i, j, 0);
    }

    void smoothScrollTo(int i, int j, int k)
    {
        if(getChildCount() == 0)
        {
            setScrollingCacheEnabled(false);
            return;
        }
        int l = getScrollX();
        int i1 = getScrollY();
        int j1 = i - l;
        j -= i1;
        if(j1 == 0 && j == 0)
        {
            completeScroll(false);
            populate();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        i = getPaddedWidth();
        int k1 = i / 2;
        float f = Math.min(1.0F, ((float)Math.abs(j1) * 1.0F) / (float)i);
        float f1 = k1;
        float f3 = k1;
        f = distanceInfluenceForSnapDuration(f);
        k = Math.abs(k);
        if(k > 0)
        {
            i = Math.round(Math.abs((f1 + f3 * f) / (float)k) * 1000F) * 4;
        } else
        {
            float f2 = i;
            float f4 = mAdapter.getPageWidth(mCurItem);
            i = (int)((1.0F + (float)Math.abs(j1) / ((float)mPageMargin + f2 * f4)) * 100F);
        }
        i = Math.min(i, 600);
        mScroller.startScroll(l, i1, j1, j, i);
        postInvalidateOnAnimation();
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(!super.verifyDrawable(drawable))
            if(drawable == mMarginDrawable)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    private static final int CLOSE_ENOUGH = 2;
    private static final Comparator COMPARATOR = new Comparator() {

        public int compare(ItemInfo iteminfo, ItemInfo iteminfo1)
        {
            return iteminfo.position - iteminfo1.position;
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((ItemInfo)obj, (ItemInfo)obj1);
        }

    }
;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_GUTTER_SIZE = 16;
    private static final int DEFAULT_OFFSCREEN_PAGES = 1;
    private static final int DRAW_ORDER_DEFAULT = 0;
    private static final int DRAW_ORDER_FORWARD = 1;
    private static final int DRAW_ORDER_REVERSE = 2;
    private static final int INVALID_POINTER = -1;
    private static final int LAYOUT_ATTRS[] = {
        0x10100b3
    };
    private static final int MAX_SCROLL_X = 0x1000000;
    private static final int MAX_SETTLE_DURATION = 600;
    private static final int MIN_DISTANCE_FOR_FLING = 25;
    private static final int MIN_FLING_VELOCITY = 400;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "ViewPager";
    private static final boolean USE_CACHE = false;
    private static final Interpolator sInterpolator = new Interpolator() {

        public float getInterpolation(float f)
        {
            f--;
            return f * f * f * f * f + 1.0F;
        }

    }
;
    private static final ViewPositionComparator sPositionComparator = new ViewPositionComparator();
    private int mActivePointerId;
    private PagerAdapter mAdapter;
    private OnAdapterChangeListener mAdapterChangeListener;
    private int mBottomPageBounds;
    private boolean mCalledSuper;
    private int mChildHeightMeasureSpec;
    private int mChildWidthMeasureSpec;
    private final int mCloseEnough;
    private int mCurItem;
    private int mDecorChildCount;
    private final int mDefaultGutterSize;
    private int mDrawingOrder;
    private ArrayList mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable;
    private int mExpectedAdapterCount;
    private boolean mFirstLayout;
    private float mFirstOffset;
    private final int mFlingDistance;
    private int mGutterSize;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private OnPageChangeListener mInternalPageChangeListener;
    private boolean mIsBeingDragged;
    private boolean mIsUnableToDrag;
    private final ArrayList mItems;
    private float mLastMotionX;
    private float mLastMotionY;
    private float mLastOffset;
    private final EdgeEffect mLeftEdge;
    private int mLeftIncr;
    private Drawable mMarginDrawable;
    private final int mMaximumVelocity;
    private final int mMinimumVelocity;
    private PagerObserver mObserver;
    private int mOffscreenPageLimit;
    private OnPageChangeListener mOnPageChangeListener;
    private int mPageMargin;
    private PageTransformer mPageTransformer;
    private boolean mPopulatePending;
    private Parcelable mRestoredAdapterState;
    private ClassLoader mRestoredClassLoader;
    private int mRestoredCurItem;
    private final EdgeEffect mRightEdge;
    private int mScrollState;
    private final Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private final ItemInfo mTempItem;
    private final Rect mTempRect;
    private int mTopPageBounds;
    private final int mTouchSlop;
    private VelocityTracker mVelocityTracker;

}

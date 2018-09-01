// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;

// Referenced classes of package com.android.internal.widget:
//            ScrollbarHelper, OrientationHelper, LinearSmoothScroller, RecyclerView

public class LinearLayoutManager extends RecyclerView.LayoutManager
    implements com.android.internal.widget.helper.ItemTouchHelper.ViewDropHandler, RecyclerView.SmoothScroller.ScrollVectorProvider
{
    class AnchorInfo
    {

        void assignCoordinateFromPadding()
        {
            int i;
            if(mLayoutFromEnd)
                i = mOrientationHelper.getEndAfterPadding();
            else
                i = mOrientationHelper.getStartAfterPadding();
            mCoordinate = i;
        }

        public void assignFromView(View view)
        {
            if(mLayoutFromEnd)
                mCoordinate = mOrientationHelper.getDecoratedEnd(view) + mOrientationHelper.getTotalSpaceChange();
            else
                mCoordinate = mOrientationHelper.getDecoratedStart(view);
            mPosition = getPosition(view);
        }

        public void assignFromViewAndKeepVisibleRect(View view)
        {
            int i;
            i = mOrientationHelper.getTotalSpaceChange();
            if(i >= 0)
            {
                assignFromView(view);
                return;
            }
            mPosition = getPosition(view);
            if(!mLayoutFromEnd) goto _L2; else goto _L1
_L1:
            int j = mOrientationHelper.getEndAfterPadding() - i - mOrientationHelper.getDecoratedEnd(view);
            mCoordinate = mOrientationHelper.getEndAfterPadding() - j;
            if(j > 0)
            {
                int l = mOrientationHelper.getDecoratedMeasurement(view);
                int j1 = mCoordinate;
                i = mOrientationHelper.getStartAfterPadding();
                j1 = j1 - l - (i + Math.min(mOrientationHelper.getDecoratedStart(view) - i, 0));
                if(j1 < 0)
                    mCoordinate = mCoordinate + Math.min(j, -j1);
            }
_L4:
            return;
_L2:
            int k1 = mOrientationHelper.getDecoratedStart(view);
            int k = k1 - mOrientationHelper.getStartAfterPadding();
            mCoordinate = k1;
            if(k > 0)
            {
                int i1 = mOrientationHelper.getDecoratedMeasurement(view);
                int l1 = mOrientationHelper.getEndAfterPadding();
                int i2 = mOrientationHelper.getDecoratedEnd(view);
                k1 = mOrientationHelper.getEndAfterPadding() - Math.min(0, l1 - i - i2) - (k1 + i1);
                if(k1 < 0)
                    mCoordinate = mCoordinate - Math.min(k, -k1);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        boolean isViewValidAsAnchor(View view, RecyclerView.State state)
        {
            boolean flag = false;
            view = (RecyclerView.LayoutParams)view.getLayoutParams();
            boolean flag1 = flag;
            if(!view.isItemRemoved())
            {
                flag1 = flag;
                if(view.getViewLayoutPosition() >= 0)
                {
                    flag1 = flag;
                    if(view.getViewLayoutPosition() < state.getItemCount())
                        flag1 = true;
                }
            }
            return flag1;
        }

        void reset()
        {
            mPosition = -1;
            mCoordinate = 0x80000000;
            mLayoutFromEnd = false;
            mValid = false;
        }

        public String toString()
        {
            return (new StringBuilder()).append("AnchorInfo{mPosition=").append(mPosition).append(", mCoordinate=").append(mCoordinate).append(", mLayoutFromEnd=").append(mLayoutFromEnd).append(", mValid=").append(mValid).append('}').toString();
        }

        int mCoordinate;
        boolean mLayoutFromEnd;
        int mPosition;
        boolean mValid;
        final LinearLayoutManager this$0;

        AnchorInfo()
        {
            this$0 = LinearLayoutManager.this;
            super();
            reset();
        }
    }

    protected static class LayoutChunkResult
    {

        void resetInternal()
        {
            mConsumed = 0;
            mFinished = false;
            mIgnoreConsumed = false;
            mFocusable = false;
        }

        public int mConsumed;
        public boolean mFinished;
        public boolean mFocusable;
        public boolean mIgnoreConsumed;

        protected LayoutChunkResult()
        {
        }
    }

    static class LayoutState
    {

        private View nextViewFromScrapList()
        {
            int i;
            int j;
            i = mScrapList.size();
            j = 0;
_L3:
            View view;
            RecyclerView.LayoutParams layoutparams;
            if(j >= i)
                break; /* Loop/switch isn't completed */
            view = ((RecyclerView.ViewHolder)mScrapList.get(j)).itemView;
            layoutparams = (RecyclerView.LayoutParams)view.getLayoutParams();
              goto _L1
_L5:
            j++;
            if(true) goto _L3; else goto _L2
_L1:
            if(layoutparams.isItemRemoved() || mCurrentPosition != layoutparams.getViewLayoutPosition()) goto _L5; else goto _L4
_L4:
            assignPositionFromScrapList(view);
            return view;
_L2:
            return null;
        }

        public void assignPositionFromScrapList()
        {
            assignPositionFromScrapList(null);
        }

        public void assignPositionFromScrapList(View view)
        {
            view = nextViewInLimitedList(view);
            if(view == null)
                mCurrentPosition = -1;
            else
                mCurrentPosition = ((RecyclerView.LayoutParams)view.getLayoutParams()).getViewLayoutPosition();
        }

        boolean hasMore(RecyclerView.State state)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(mCurrentPosition >= 0)
            {
                flag1 = flag;
                if(mCurrentPosition < state.getItemCount())
                    flag1 = true;
            }
            return flag1;
        }

        void log()
        {
            Log.d("LLM#LayoutState", (new StringBuilder()).append("avail:").append(mAvailable).append(", ind:").append(mCurrentPosition).append(", dir:").append(mItemDirection).append(", offset:").append(mOffset).append(", layoutDir:").append(mLayoutDirection).toString());
        }

        View next(RecyclerView.Recycler recycler)
        {
            if(mScrapList != null)
            {
                return nextViewFromScrapList();
            } else
            {
                recycler = recycler.getViewForPosition(mCurrentPosition);
                mCurrentPosition = mCurrentPosition + mItemDirection;
                return recycler;
            }
        }

        public View nextViewInLimitedList(View view)
        {
            int i;
            View view1;
            int j;
            int k;
            i = mScrapList.size();
            view1 = null;
            j = 0x7fffffff;
            k = 0;
_L2:
            View view2;
            View view3;
            RecyclerView.LayoutParams layoutparams;
            int l;
            view2 = view1;
            if(k >= i)
                break MISSING_BLOCK_LABEL_159;
            view3 = ((RecyclerView.ViewHolder)mScrapList.get(k)).itemView;
            layoutparams = (RecyclerView.LayoutParams)view3.getLayoutParams();
            view2 = view1;
            l = j;
            if(view3 != view)
            {
                if(!layoutparams.isItemRemoved())
                    break; /* Loop/switch isn't completed */
                l = j;
                view2 = view1;
            }
_L4:
            k++;
            view1 = view2;
            j = l;
            if(true) goto _L2; else goto _L1
_L1:
            int i1;
            i1 = (layoutparams.getViewLayoutPosition() - mCurrentPosition) * mItemDirection;
            view2 = view1;
            l = j;
            if(i1 < 0) goto _L4; else goto _L3
_L3:
            view2 = view1;
            l = j;
            if(i1 >= j) goto _L4; else goto _L5
_L5:
            view1 = view3;
            l = i1;
            view2 = view1;
            if(i1 != 0) goto _L4; else goto _L6
_L6:
            view2 = view1;
            return view2;
        }

        static final int INVALID_LAYOUT = 0x80000000;
        static final int ITEM_DIRECTION_HEAD = -1;
        static final int ITEM_DIRECTION_TAIL = 1;
        static final int LAYOUT_END = 1;
        static final int LAYOUT_START = -1;
        static final int SCROLLING_OFFSET_NaN = 0x80000000;
        static final String TAG = "LLM#LayoutState";
        int mAvailable;
        int mCurrentPosition;
        int mExtra;
        boolean mInfinite;
        boolean mIsPreLayout;
        int mItemDirection;
        int mLastScrollDelta;
        int mLayoutDirection;
        int mOffset;
        boolean mRecycle;
        List mScrapList;
        int mScrollingOffset;

        LayoutState()
        {
            mRecycle = true;
            mExtra = 0;
            mIsPreLayout = false;
            mScrapList = null;
        }
    }

    public static class SavedState
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        boolean hasValidAnchor()
        {
            boolean flag = false;
            if(mAnchorPosition >= 0)
                flag = true;
            return flag;
        }

        void invalidateAnchor()
        {
            mAnchorPosition = -1;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mAnchorPosition);
            parcel.writeInt(mAnchorOffset);
            if(mAnchorLayoutFromEnd)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
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
        boolean mAnchorLayoutFromEnd;
        int mAnchorOffset;
        int mAnchorPosition;


        public SavedState()
        {
        }

        SavedState(Parcel parcel)
        {
            boolean flag = true;
            super();
            mAnchorPosition = parcel.readInt();
            mAnchorOffset = parcel.readInt();
            if(parcel.readInt() != 1)
                flag = false;
            mAnchorLayoutFromEnd = flag;
        }

        public SavedState(SavedState savedstate)
        {
            mAnchorPosition = savedstate.mAnchorPosition;
            mAnchorOffset = savedstate.mAnchorOffset;
            mAnchorLayoutFromEnd = savedstate.mAnchorLayoutFromEnd;
        }
    }


    public LinearLayoutManager(Context context)
    {
        this(context, 1, false);
    }

    public LinearLayoutManager(Context context, int i, boolean flag)
    {
        mReverseLayout = false;
        mShouldReverseLayout = false;
        mStackFromEnd = false;
        mSmoothScrollbarEnabled = true;
        mPendingScrollPosition = -1;
        mPendingScrollPositionOffset = 0x80000000;
        mPendingSavedState = null;
        mAnchorInfo = new AnchorInfo();
        mLayoutChunkResult = new LayoutChunkResult();
        mInitialItemPrefetchCount = 2;
        setOrientation(i);
        setReverseLayout(flag);
        setAutoMeasureEnabled(true);
    }

    public LinearLayoutManager(Context context, AttributeSet attributeset, int i, int j)
    {
        mReverseLayout = false;
        mShouldReverseLayout = false;
        mStackFromEnd = false;
        mSmoothScrollbarEnabled = true;
        mPendingScrollPosition = -1;
        mPendingScrollPositionOffset = 0x80000000;
        mPendingSavedState = null;
        mAnchorInfo = new AnchorInfo();
        mLayoutChunkResult = new LayoutChunkResult();
        mInitialItemPrefetchCount = 2;
        context = getProperties(context, attributeset, i, j);
        setOrientation(((RecyclerView.LayoutManager.Properties) (context)).orientation);
        setReverseLayout(((RecyclerView.LayoutManager.Properties) (context)).reverseLayout);
        setStackFromEnd(((RecyclerView.LayoutManager.Properties) (context)).stackFromEnd);
        setAutoMeasureEnabled(true);
    }

    private int computeScrollExtent(RecyclerView.State state)
    {
        if(getChildCount() == 0)
        {
            return 0;
        } else
        {
            ensureLayoutState();
            return ScrollbarHelper.computeScrollExtent(state, mOrientationHelper, findFirstVisibleChildClosestToStart(mSmoothScrollbarEnabled ^ true, true), findFirstVisibleChildClosestToEnd(mSmoothScrollbarEnabled ^ true, true), this, mSmoothScrollbarEnabled);
        }
    }

    private int computeScrollOffset(RecyclerView.State state)
    {
        if(getChildCount() == 0)
        {
            return 0;
        } else
        {
            ensureLayoutState();
            return ScrollbarHelper.computeScrollOffset(state, mOrientationHelper, findFirstVisibleChildClosestToStart(mSmoothScrollbarEnabled ^ true, true), findFirstVisibleChildClosestToEnd(mSmoothScrollbarEnabled ^ true, true), this, mSmoothScrollbarEnabled, mShouldReverseLayout);
        }
    }

    private int computeScrollRange(RecyclerView.State state)
    {
        if(getChildCount() == 0)
        {
            return 0;
        } else
        {
            ensureLayoutState();
            return ScrollbarHelper.computeScrollRange(state, mOrientationHelper, findFirstVisibleChildClosestToStart(mSmoothScrollbarEnabled ^ true, true), findFirstVisibleChildClosestToEnd(mSmoothScrollbarEnabled ^ true, true), this, mSmoothScrollbarEnabled);
        }
    }

    private View findFirstReferenceChild(RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        return findReferenceChild(recycler, state, 0, getChildCount(), state.getItemCount());
    }

    private View findFirstVisibleChildClosestToEnd(boolean flag, boolean flag1)
    {
        if(mShouldReverseLayout)
            return findOneVisibleChild(0, getChildCount(), flag, flag1);
        else
            return findOneVisibleChild(getChildCount() - 1, -1, flag, flag1);
    }

    private View findFirstVisibleChildClosestToStart(boolean flag, boolean flag1)
    {
        if(mShouldReverseLayout)
            return findOneVisibleChild(getChildCount() - 1, -1, flag, flag1);
        else
            return findOneVisibleChild(0, getChildCount(), flag, flag1);
    }

    private View findLastReferenceChild(RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        return findReferenceChild(recycler, state, getChildCount() - 1, -1, state.getItemCount());
    }

    private View findReferenceChildClosestToEnd(RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        if(mShouldReverseLayout)
            recycler = findFirstReferenceChild(recycler, state);
        else
            recycler = findLastReferenceChild(recycler, state);
        return recycler;
    }

    private View findReferenceChildClosestToStart(RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        if(mShouldReverseLayout)
            recycler = findLastReferenceChild(recycler, state);
        else
            recycler = findFirstReferenceChild(recycler, state);
        return recycler;
    }

    private int fixLayoutEndGap(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean flag)
    {
        int j = mOrientationHelper.getEndAfterPadding() - i;
        if(j > 0)
        {
            j = -scrollBy(-j, recycler, state);
            if(flag)
            {
                i = mOrientationHelper.getEndAfterPadding() - (i + j);
                if(i > 0)
                {
                    mOrientationHelper.offsetChildren(i);
                    return i + j;
                }
            }
        } else
        {
            return 0;
        }
        return j;
    }

    private int fixLayoutStartGap(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean flag)
    {
        int j = i - mOrientationHelper.getStartAfterPadding();
        if(j > 0)
        {
            j = -scrollBy(j, recycler, state);
            if(flag)
            {
                i = (i + j) - mOrientationHelper.getStartAfterPadding();
                if(i > 0)
                {
                    mOrientationHelper.offsetChildren(-i);
                    return j - i;
                }
            }
        } else
        {
            return 0;
        }
        return j;
    }

    private View getChildClosestToEnd()
    {
        int i;
        if(mShouldReverseLayout)
            i = 0;
        else
            i = getChildCount() - 1;
        return getChildAt(i);
    }

    private View getChildClosestToStart()
    {
        int i;
        if(mShouldReverseLayout)
            i = getChildCount() - 1;
        else
            i = 0;
        return getChildAt(i);
    }

    private void layoutForPredictiveAnimations(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int j)
    {
        while(!state.willRunPredictiveAnimations() || getChildCount() == 0 || state.isPreLayout() || supportsPredictiveItemAnimations() ^ true) 
            return;
        int k = 0;
        int l = 0;
        List list = recycler.getScrapList();
        int i1 = list.size();
        int j1 = getPosition(getChildAt(0));
        int k1 = 0;
        while(k1 < i1) 
        {
            RecyclerView.ViewHolder viewholder = (RecyclerView.ViewHolder)list.get(k1);
            if(!viewholder.isRemoved())
            {
                boolean flag;
                byte byte0;
                if(viewholder.getLayoutPosition() < j1)
                    flag = true;
                else
                    flag = false;
                if(flag != mShouldReverseLayout)
                    byte0 = -1;
                else
                    byte0 = 1;
                if(byte0 == -1)
                    k += mOrientationHelper.getDecoratedMeasurement(viewholder.itemView);
                else
                    l += mOrientationHelper.getDecoratedMeasurement(viewholder.itemView);
            }
            k1++;
        }
        mLayoutState.mScrapList = list;
        if(k > 0)
        {
            updateLayoutStateToFillStart(getPosition(getChildClosestToStart()), i);
            mLayoutState.mExtra = k;
            mLayoutState.mAvailable = 0;
            mLayoutState.assignPositionFromScrapList();
            fill(recycler, mLayoutState, state, false);
        }
        if(l > 0)
        {
            updateLayoutStateToFillEnd(getPosition(getChildClosestToEnd()), j);
            mLayoutState.mExtra = l;
            mLayoutState.mAvailable = 0;
            mLayoutState.assignPositionFromScrapList();
            fill(recycler, mLayoutState, state, false);
        }
        mLayoutState.mScrapList = null;
    }

    private void logChildren()
    {
        Log.d("LinearLayoutManager", "internal representation of views on the screen");
        for(int i = 0; i < getChildCount(); i++)
        {
            View view = getChildAt(i);
            Log.d("LinearLayoutManager", (new StringBuilder()).append("item ").append(getPosition(view)).append(", coord:").append(mOrientationHelper.getDecoratedStart(view)).toString());
        }

        Log.d("LinearLayoutManager", "==============");
    }

    private void recycleByLayoutState(RecyclerView.Recycler recycler, LayoutState layoutstate)
    {
        if(!layoutstate.mRecycle || layoutstate.mInfinite)
            return;
        if(layoutstate.mLayoutDirection == -1)
            recycleViewsFromEnd(recycler, layoutstate.mScrollingOffset);
        else
            recycleViewsFromStart(recycler, layoutstate.mScrollingOffset);
    }

    private void recycleChildren(RecyclerView.Recycler recycler, int i, int j)
    {
        if(i == j)
            return;
        if(j > i)
            for(j--; j >= i; j--)
                removeAndRecycleViewAt(j, recycler);

        else
            for(; i > j; i--)
                removeAndRecycleViewAt(i, recycler);

    }

    private void recycleViewsFromEnd(RecyclerView.Recycler recycler, int i)
    {
        int j = getChildCount();
        if(i < 0)
            return;
        int k = mOrientationHelper.getEnd() - i;
        if(mShouldReverseLayout)
            for(i = 0; i < j; i++)
            {
                View view = getChildAt(i);
                if(mOrientationHelper.getDecoratedStart(view) < k || mOrientationHelper.getTransformedStartWithDecoration(view) < k)
                {
                    recycleChildren(recycler, 0, i);
                    return;
                }
            }

        else
            for(i = j - 1; i >= 0; i--)
            {
                View view1 = getChildAt(i);
                if(mOrientationHelper.getDecoratedStart(view1) < k || mOrientationHelper.getTransformedStartWithDecoration(view1) < k)
                {
                    recycleChildren(recycler, j - 1, i);
                    return;
                }
            }

    }

    private void recycleViewsFromStart(RecyclerView.Recycler recycler, int i)
    {
        if(i < 0)
            return;
        int j = getChildCount();
        if(mShouldReverseLayout)
        {
            for(int k = j - 1; k >= 0; k--)
            {
                View view = getChildAt(k);
                if(mOrientationHelper.getDecoratedEnd(view) > i || mOrientationHelper.getTransformedEndWithDecoration(view) > i)
                {
                    recycleChildren(recycler, j - 1, k);
                    return;
                }
            }

        } else
        {
            for(int l = 0; l < j; l++)
            {
                View view1 = getChildAt(l);
                if(mOrientationHelper.getDecoratedEnd(view1) > i || mOrientationHelper.getTransformedEndWithDecoration(view1) > i)
                {
                    recycleChildren(recycler, 0, l);
                    return;
                }
            }

        }
    }

    private void resolveShouldLayoutReverse()
    {
        if(mOrientation == 1 || isLayoutRTL() ^ true)
            mShouldReverseLayout = mReverseLayout;
        else
            mShouldReverseLayout = mReverseLayout ^ true;
    }

    private boolean updateAnchorFromChildren(RecyclerView.Recycler recycler, RecyclerView.State state, AnchorInfo anchorinfo)
    {
        if(getChildCount() == 0)
            return false;
        View view = getFocusedChild();
        if(view != null && anchorinfo.isViewValidAsAnchor(view, state))
        {
            anchorinfo.assignFromViewAndKeepVisibleRect(view);
            return true;
        }
        if(mLastStackFromEnd != mStackFromEnd)
            return false;
        if(anchorinfo.mLayoutFromEnd)
            recycler = findReferenceChildClosestToEnd(recycler, state);
        else
            recycler = findReferenceChildClosestToStart(recycler, state);
        if(recycler != null)
        {
            anchorinfo.assignFromView(recycler);
            if(!state.isPreLayout() && supportsPredictiveItemAnimations())
            {
                int i;
                if(mOrientationHelper.getDecoratedStart(recycler) < mOrientationHelper.getEndAfterPadding())
                {
                    if(mOrientationHelper.getDecoratedEnd(recycler) < mOrientationHelper.getStartAfterPadding())
                        i = 1;
                    else
                        i = 0;
                } else
                {
                    i = 1;
                }
                if(i != 0)
                {
                    if(anchorinfo.mLayoutFromEnd)
                        i = mOrientationHelper.getEndAfterPadding();
                    else
                        i = mOrientationHelper.getStartAfterPadding();
                    anchorinfo.mCoordinate = i;
                }
            }
            return true;
        } else
        {
            return false;
        }
    }

    private boolean updateAnchorFromPendingData(RecyclerView.State state, AnchorInfo anchorinfo)
    {
        boolean flag = false;
        if(state.isPreLayout() || mPendingScrollPosition == -1)
            return false;
        if(mPendingScrollPosition < 0 || mPendingScrollPosition >= state.getItemCount())
        {
            mPendingScrollPosition = -1;
            mPendingScrollPositionOffset = 0x80000000;
            return false;
        }
        anchorinfo.mPosition = mPendingScrollPosition;
        if(mPendingSavedState != null && mPendingSavedState.hasValidAnchor())
        {
            anchorinfo.mLayoutFromEnd = mPendingSavedState.mAnchorLayoutFromEnd;
            if(anchorinfo.mLayoutFromEnd)
                anchorinfo.mCoordinate = mOrientationHelper.getEndAfterPadding() - mPendingSavedState.mAnchorOffset;
            else
                anchorinfo.mCoordinate = mOrientationHelper.getStartAfterPadding() + mPendingSavedState.mAnchorOffset;
            return true;
        }
        if(mPendingScrollPositionOffset == 0x80000000)
        {
            state = findViewByPosition(mPendingScrollPosition);
            if(state != null)
            {
                if(mOrientationHelper.getDecoratedMeasurement(state) > mOrientationHelper.getTotalSpace())
                {
                    anchorinfo.assignCoordinateFromPadding();
                    return true;
                }
                if(mOrientationHelper.getDecoratedStart(state) - mOrientationHelper.getStartAfterPadding() < 0)
                {
                    anchorinfo.mCoordinate = mOrientationHelper.getStartAfterPadding();
                    anchorinfo.mLayoutFromEnd = false;
                    return true;
                }
                if(mOrientationHelper.getEndAfterPadding() - mOrientationHelper.getDecoratedEnd(state) < 0)
                {
                    anchorinfo.mCoordinate = mOrientationHelper.getEndAfterPadding();
                    anchorinfo.mLayoutFromEnd = true;
                    return true;
                }
                int i;
                if(anchorinfo.mLayoutFromEnd)
                    i = mOrientationHelper.getDecoratedEnd(state) + mOrientationHelper.getTotalSpaceChange();
                else
                    i = mOrientationHelper.getDecoratedStart(state);
                anchorinfo.mCoordinate = i;
            } else
            {
                if(getChildCount() > 0)
                {
                    int j = getPosition(getChildAt(0));
                    boolean flag1;
                    if(mPendingScrollPosition < j)
                        flag1 = true;
                    else
                        flag1 = false;
                    if(flag1 == mShouldReverseLayout)
                        flag = true;
                    anchorinfo.mLayoutFromEnd = flag;
                }
                anchorinfo.assignCoordinateFromPadding();
            }
            return true;
        }
        anchorinfo.mLayoutFromEnd = mShouldReverseLayout;
        if(mShouldReverseLayout)
            anchorinfo.mCoordinate = mOrientationHelper.getEndAfterPadding() - mPendingScrollPositionOffset;
        else
            anchorinfo.mCoordinate = mOrientationHelper.getStartAfterPadding() + mPendingScrollPositionOffset;
        return true;
    }

    private void updateAnchorInfoForLayout(RecyclerView.Recycler recycler, RecyclerView.State state, AnchorInfo anchorinfo)
    {
        if(updateAnchorFromPendingData(state, anchorinfo))
            return;
        if(updateAnchorFromChildren(recycler, state, anchorinfo))
            return;
        anchorinfo.assignCoordinateFromPadding();
        int i;
        if(mStackFromEnd)
            i = state.getItemCount() - 1;
        else
            i = 0;
        anchorinfo.mPosition = i;
    }

    private void updateLayoutState(int i, int j, boolean flag, RecyclerView.State state)
    {
        byte byte0 = -1;
        boolean flag1 = true;
        mLayoutState.mInfinite = resolveIsInfinite();
        mLayoutState.mExtra = getExtraLayoutSpace(state);
        mLayoutState.mLayoutDirection = i;
        if(i == 1)
        {
            state = mLayoutState;
            state.mExtra = ((LayoutState) (state)).mExtra + mOrientationHelper.getEndPadding();
            View view = getChildClosestToEnd();
            state = mLayoutState;
            if(mShouldReverseLayout)
                i = byte0;
            else
                i = 1;
            state.mItemDirection = i;
            mLayoutState.mCurrentPosition = getPosition(view) + mLayoutState.mItemDirection;
            mLayoutState.mOffset = mOrientationHelper.getDecoratedEnd(view);
            i = mOrientationHelper.getDecoratedEnd(view) - mOrientationHelper.getEndAfterPadding();
        } else
        {
            state = getChildClosestToStart();
            LayoutState layoutstate = mLayoutState;
            layoutstate.mExtra = layoutstate.mExtra + mOrientationHelper.getStartAfterPadding();
            layoutstate = mLayoutState;
            if(mShouldReverseLayout)
                i = ((flag1) ? 1 : 0);
            else
                i = -1;
            layoutstate.mItemDirection = i;
            mLayoutState.mCurrentPosition = getPosition(state) + mLayoutState.mItemDirection;
            mLayoutState.mOffset = mOrientationHelper.getDecoratedStart(state);
            i = -mOrientationHelper.getDecoratedStart(state) + mOrientationHelper.getStartAfterPadding();
        }
        mLayoutState.mAvailable = j;
        if(flag)
        {
            state = mLayoutState;
            state.mAvailable = ((LayoutState) (state)).mAvailable - i;
        }
        mLayoutState.mScrollingOffset = i;
    }

    private void updateLayoutStateToFillEnd(int i, int j)
    {
        mLayoutState.mAvailable = mOrientationHelper.getEndAfterPadding() - j;
        LayoutState layoutstate = mLayoutState;
        int k;
        if(mShouldReverseLayout)
            k = -1;
        else
            k = 1;
        layoutstate.mItemDirection = k;
        mLayoutState.mCurrentPosition = i;
        mLayoutState.mLayoutDirection = 1;
        mLayoutState.mOffset = j;
        mLayoutState.mScrollingOffset = 0x80000000;
    }

    private void updateLayoutStateToFillEnd(AnchorInfo anchorinfo)
    {
        updateLayoutStateToFillEnd(anchorinfo.mPosition, anchorinfo.mCoordinate);
    }

    private void updateLayoutStateToFillStart(int i, int j)
    {
        mLayoutState.mAvailable = j - mOrientationHelper.getStartAfterPadding();
        mLayoutState.mCurrentPosition = i;
        LayoutState layoutstate = mLayoutState;
        if(mShouldReverseLayout)
            i = 1;
        else
            i = -1;
        layoutstate.mItemDirection = i;
        mLayoutState.mLayoutDirection = -1;
        mLayoutState.mOffset = j;
        mLayoutState.mScrollingOffset = 0x80000000;
    }

    private void updateLayoutStateToFillStart(AnchorInfo anchorinfo)
    {
        updateLayoutStateToFillStart(anchorinfo.mPosition, anchorinfo.mCoordinate);
    }

    public void assertNotInLayoutOrScroll(String s)
    {
        if(mPendingSavedState == null)
            super.assertNotInLayoutOrScroll(s);
    }

    public boolean canScrollHorizontally()
    {
        boolean flag = false;
        if(mOrientation == 0)
            flag = true;
        return flag;
    }

    public boolean canScrollVertically()
    {
        boolean flag = true;
        if(mOrientation != 1)
            flag = false;
        return flag;
    }

    public void collectAdjacentPrefetchPositions(int i, int j, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutprefetchregistry)
    {
        if(mOrientation != 0)
            i = j;
        if(getChildCount() == 0 || i == 0)
            return;
        if(i > 0)
            j = 1;
        else
            j = -1;
        updateLayoutState(j, Math.abs(i), true, state);
        collectPrefetchPositionsForLayoutState(state, mLayoutState, layoutprefetchregistry);
    }

    public void collectInitialPrefetchPositions(int i, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutprefetchregistry)
    {
        boolean flag;
        int j;
        byte byte0;
        int k;
        if(mPendingSavedState != null && mPendingSavedState.hasValidAnchor())
        {
            flag = mPendingSavedState.mAnchorLayoutFromEnd;
            j = mPendingSavedState.mAnchorPosition;
        } else
        {
            resolveShouldLayoutReverse();
            flag = mShouldReverseLayout;
            if(mPendingScrollPosition == -1)
            {
                if(flag)
                    j = i - 1;
                else
                    j = 0;
            } else
            {
                j = mPendingScrollPosition;
            }
        }
        if(flag)
            byte0 = -1;
        else
            byte0 = 1;
        for(k = 0; k < mInitialItemPrefetchCount && j >= 0 && j < i; k++)
        {
            layoutprefetchregistry.addPosition(j, 0);
            j += byte0;
        }

    }

    void collectPrefetchPositionsForLayoutState(RecyclerView.State state, LayoutState layoutstate, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutprefetchregistry)
    {
        int i = layoutstate.mCurrentPosition;
        if(i >= 0 && i < state.getItemCount())
            layoutprefetchregistry.addPosition(i, layoutstate.mScrollingOffset);
    }

    public int computeHorizontalScrollExtent(RecyclerView.State state)
    {
        return computeScrollExtent(state);
    }

    public int computeHorizontalScrollOffset(RecyclerView.State state)
    {
        return computeScrollOffset(state);
    }

    public int computeHorizontalScrollRange(RecyclerView.State state)
    {
        return computeScrollRange(state);
    }

    public PointF computeScrollVectorForPosition(int i)
    {
        boolean flag = false;
        if(getChildCount() == 0)
            return null;
        if(i < getPosition(getChildAt(0)))
            flag = true;
        if(flag != mShouldReverseLayout)
            i = -1;
        else
            i = 1;
        if(mOrientation == 0)
            return new PointF(i, 0.0F);
        else
            return new PointF(0.0F, i);
    }

    public int computeVerticalScrollExtent(RecyclerView.State state)
    {
        return computeScrollExtent(state);
    }

    public int computeVerticalScrollOffset(RecyclerView.State state)
    {
        return computeScrollOffset(state);
    }

    public int computeVerticalScrollRange(RecyclerView.State state)
    {
        return computeScrollRange(state);
    }

    int convertFocusDirectionToLayoutDirection(int i)
    {
        int j = -1;
        int k = 0x80000000;
        boolean flag = true;
        switch(i)
        {
        default:
            return 0x80000000;

        case 1: // '\001'
            if(mOrientation == 1)
                return -1;
            return !isLayoutRTL() ? -1 : 1;

        case 2: // '\002'
            if(mOrientation == 1)
                return 1;
            return !isLayoutRTL() ? 1 : -1;

        case 33: // '!'
            if(mOrientation != 1)
                j = 0x80000000;
            return j;

        case 130: 
            i = k;
            if(mOrientation == 1)
                i = 1;
            return i;

        case 17: // '\021'
            if(mOrientation != 0)
                j = 0x80000000;
            return j;

        case 66: // 'B'
            break;
        }
        if(mOrientation == 0)
            i = ((flag) ? 1 : 0);
        else
            i = 0x80000000;
        return i;
    }

    LayoutState createLayoutState()
    {
        return new LayoutState();
    }

    void ensureLayoutState()
    {
        if(mLayoutState == null)
            mLayoutState = createLayoutState();
        if(mOrientationHelper == null)
            mOrientationHelper = OrientationHelper.createOrientationHelper(this, mOrientation);
    }

    int fill(RecyclerView.Recycler recycler, LayoutState layoutstate, RecyclerView.State state, boolean flag)
    {
        int i;
        int j;
        LayoutChunkResult layoutchunkresult;
        i = layoutstate.mAvailable;
        if(layoutstate.mScrollingOffset != 0x80000000)
        {
            if(layoutstate.mAvailable < 0)
                layoutstate.mScrollingOffset = layoutstate.mScrollingOffset + layoutstate.mAvailable;
            recycleByLayoutState(recycler, layoutstate);
        }
        j = layoutstate.mAvailable + layoutstate.mExtra;
        layoutchunkresult = mLayoutChunkResult;
_L6:
        if(!layoutstate.mInfinite && j <= 0 || !layoutstate.hasMore(state)) goto _L2; else goto _L1
_L1:
        layoutchunkresult.resetInternal();
        layoutChunk(recycler, state, layoutstate, layoutchunkresult);
        if(!layoutchunkresult.mFinished) goto _L3; else goto _L2
_L2:
        return i - layoutstate.mAvailable;
_L3:
        layoutstate.mOffset = layoutstate.mOffset + layoutchunkresult.mConsumed * layoutstate.mLayoutDirection;
        if(layoutchunkresult.mIgnoreConsumed && mLayoutState.mScrapList == null) goto _L5; else goto _L4
_L4:
        int k;
        layoutstate.mAvailable = layoutstate.mAvailable - layoutchunkresult.mConsumed;
        k = j - layoutchunkresult.mConsumed;
_L7:
        if(layoutstate.mScrollingOffset != 0x80000000)
        {
            layoutstate.mScrollingOffset = layoutstate.mScrollingOffset + layoutchunkresult.mConsumed;
            if(layoutstate.mAvailable < 0)
                layoutstate.mScrollingOffset = layoutstate.mScrollingOffset + layoutstate.mAvailable;
            recycleByLayoutState(recycler, layoutstate);
        }
        j = k;
        if(!flag)
            break; /* Loop/switch isn't completed */
        j = k;
        if(!layoutchunkresult.mFocusable) goto _L6; else goto _L2
_L5:
        k = j;
        if(!(state.isPreLayout() ^ true)) goto _L7; else goto _L4
    }

    public int findFirstCompletelyVisibleItemPosition()
    {
        View view = findOneVisibleChild(0, getChildCount(), true, false);
        int i;
        if(view == null)
            i = -1;
        else
            i = getPosition(view);
        return i;
    }

    public int findFirstVisibleItemPosition()
    {
        View view = findOneVisibleChild(0, getChildCount(), false, true);
        int i;
        if(view == null)
            i = -1;
        else
            i = getPosition(view);
        return i;
    }

    public int findLastCompletelyVisibleItemPosition()
    {
        int i = -1;
        View view = findOneVisibleChild(getChildCount() - 1, -1, true, false);
        if(view != null)
            i = getPosition(view);
        return i;
    }

    public int findLastVisibleItemPosition()
    {
        int i = -1;
        View view = findOneVisibleChild(getChildCount() - 1, -1, false, true);
        if(view != null)
            i = getPosition(view);
        return i;
    }

    View findOneVisibleChild(int i, int j, boolean flag, boolean flag1)
    {
        View view;
label0:
        {
            ensureLayoutState();
            int k = mOrientationHelper.getStartAfterPadding();
            int l = mOrientationHelper.getEndAfterPadding();
            byte byte0;
            View view1;
            if(j > i)
                byte0 = 1;
            else
                byte0 = -1;
            view = null;
            do
            {
                if(i == j)
                    break label0;
                view1 = getChildAt(i);
                int i1 = mOrientationHelper.getDecoratedStart(view1);
                int j1 = mOrientationHelper.getDecoratedEnd(view1);
                View view2 = view;
                if(i1 < l)
                {
                    view2 = view;
                    if(j1 > k)
                    {
                        if(!flag)
                            break;
                        if(i1 >= k && j1 <= l)
                            return view1;
                        view2 = view;
                        if(flag1)
                        {
                            view2 = view;
                            if(view == null)
                                view2 = view1;
                        }
                    }
                }
                i += byte0;
                view = view2;
            } while(true);
            return view1;
        }
        return view;
    }

    View findReferenceChild(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int j, int k)
    {
        ensureLayoutState();
        state = null;
        recycler = null;
        int l = mOrientationHelper.getStartAfterPadding();
        int i1 = mOrientationHelper.getEndAfterPadding();
        int j1;
        if(j > i)
            j1 = 1;
        else
            j1 = -1;
        while(i != j) 
        {
            View view = getChildAt(i);
            int k1 = getPosition(view);
            Object obj = state;
            Object obj1 = recycler;
            if(k1 >= 0)
            {
                obj = state;
                obj1 = recycler;
                if(k1 < k)
                    if(((RecyclerView.LayoutParams)view.getLayoutParams()).isItemRemoved())
                    {
                        obj = state;
                        obj1 = recycler;
                        if(state == null)
                        {
                            obj = view;
                            obj1 = recycler;
                        }
                    } else
                    if(mOrientationHelper.getDecoratedStart(view) >= i1 || mOrientationHelper.getDecoratedEnd(view) < l)
                    {
                        obj = state;
                        obj1 = recycler;
                        if(recycler == null)
                        {
                            obj1 = view;
                            obj = state;
                        }
                    } else
                    {
                        return view;
                    }
            }
            i += j1;
            state = ((RecyclerView.State) (obj));
            recycler = ((RecyclerView.Recycler) (obj1));
        }
        if(recycler == null)
            recycler = state;
        return recycler;
    }

    public View findViewByPosition(int i)
    {
        int j = getChildCount();
        if(j == 0)
            return null;
        int k = i - getPosition(getChildAt(0));
        if(k >= 0 && k < j)
        {
            View view = getChildAt(k);
            if(getPosition(view) == i)
                return view;
        }
        return super.findViewByPosition(i);
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams()
    {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    protected int getExtraLayoutSpace(RecyclerView.State state)
    {
        if(state.hasTargetScrollPosition())
            return mOrientationHelper.getTotalSpace();
        else
            return 0;
    }

    public int getInitialItemPrefetchCount()
    {
        return mInitialItemPrefetchCount;
    }

    public int getOrientation()
    {
        return mOrientation;
    }

    public boolean getRecycleChildrenOnDetach()
    {
        return mRecycleChildrenOnDetach;
    }

    public boolean getReverseLayout()
    {
        return mReverseLayout;
    }

    public boolean getStackFromEnd()
    {
        return mStackFromEnd;
    }

    protected boolean isLayoutRTL()
    {
        boolean flag = true;
        if(getLayoutDirection() != 1)
            flag = false;
        return flag;
    }

    public boolean isSmoothScrollbarEnabled()
    {
        return mSmoothScrollbarEnabled;
    }

    void layoutChunk(RecyclerView.Recycler recycler, RecyclerView.State state, LayoutState layoutstate, LayoutChunkResult layoutchunkresult)
    {
        recycler = layoutstate.next(recycler);
        if(recycler == null)
        {
            layoutchunkresult.mFinished = true;
            return;
        }
        state = (RecyclerView.LayoutParams)recycler.getLayoutParams();
        int i;
        int j;
        int k;
        int l;
        if(layoutstate.mScrapList == null)
        {
            boolean flag = mShouldReverseLayout;
            boolean flag2;
            if(layoutstate.mLayoutDirection == -1)
                flag2 = true;
            else
                flag2 = false;
            if(flag == flag2)
                addView(recycler);
            else
                addView(recycler, 0);
        } else
        {
            boolean flag1 = mShouldReverseLayout;
            boolean flag3;
            if(layoutstate.mLayoutDirection == -1)
                flag3 = true;
            else
                flag3 = false;
            if(flag1 == flag3)
                addDisappearingView(recycler);
            else
                addDisappearingView(recycler, 0);
        }
        measureChildWithMargins(recycler, 0, 0);
        layoutchunkresult.mConsumed = mOrientationHelper.getDecoratedMeasurement(recycler);
        if(mOrientation == 1)
        {
            if(isLayoutRTL())
            {
                i = getWidth() - getPaddingRight();
                j = i - mOrientationHelper.getDecoratedMeasurementInOther(recycler);
            } else
            {
                j = getPaddingLeft();
                i = j + mOrientationHelper.getDecoratedMeasurementInOther(recycler);
            }
            if(layoutstate.mLayoutDirection == -1)
            {
                k = layoutstate.mOffset;
                l = layoutstate.mOffset - layoutchunkresult.mConsumed;
            } else
            {
                l = layoutstate.mOffset;
                k = layoutstate.mOffset + layoutchunkresult.mConsumed;
            }
        } else
        {
            l = getPaddingTop();
            k = l + mOrientationHelper.getDecoratedMeasurementInOther(recycler);
            if(layoutstate.mLayoutDirection == -1)
            {
                i = layoutstate.mOffset;
                j = layoutstate.mOffset - layoutchunkresult.mConsumed;
            } else
            {
                j = layoutstate.mOffset;
                i = layoutstate.mOffset + layoutchunkresult.mConsumed;
            }
        }
        layoutDecoratedWithMargins(recycler, j, l, i, k);
        if(state.isItemRemoved() || state.isItemChanged())
            layoutchunkresult.mIgnoreConsumed = true;
        layoutchunkresult.mFocusable = recycler.isFocusable();
    }

    void onAnchorReady(RecyclerView.Recycler recycler, RecyclerView.State state, AnchorInfo anchorinfo, int i)
    {
    }

    public void onDetachedFromWindow(RecyclerView recyclerview, RecyclerView.Recycler recycler)
    {
        super.onDetachedFromWindow(recyclerview, recycler);
        if(mRecycleChildrenOnDetach)
        {
            removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }

    public View onFocusSearchFailed(View view, int i, RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        resolveShouldLayoutReverse();
        if(getChildCount() == 0)
            return null;
        i = convertFocusDirectionToLayoutDirection(i);
        if(i == 0x80000000)
            return null;
        ensureLayoutState();
        if(i == -1)
            view = findReferenceChildClosestToStart(recycler, state);
        else
            view = findReferenceChildClosestToEnd(recycler, state);
        if(view == null)
            return null;
        ensureLayoutState();
        updateLayoutState(i, (int)((float)mOrientationHelper.getTotalSpace() * 0.3333333F), false, state);
        mLayoutState.mScrollingOffset = 0x80000000;
        mLayoutState.mRecycle = false;
        fill(recycler, mLayoutState, state, true);
        if(i == -1)
            recycler = getChildClosestToStart();
        else
            recycler = getChildClosestToEnd();
        if(recycler == view || recycler.isFocusable() ^ true)
            return null;
        else
            return recycler;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEvent(accessibilityevent);
        if(getChildCount() > 0)
        {
            accessibilityevent.setFromIndex(findFirstVisibleItemPosition());
            accessibilityevent.setToIndex(findLastVisibleItemPosition());
        }
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        if((mPendingSavedState != null || mPendingScrollPosition != -1) && state.getItemCount() == 0)
        {
            removeAndRecycleAllViews(recycler);
            return;
        }
        if(mPendingSavedState != null && mPendingSavedState.hasValidAnchor())
            mPendingScrollPosition = mPendingSavedState.mAnchorPosition;
        ensureLayoutState();
        mLayoutState.mRecycle = false;
        resolveShouldLayoutReverse();
        break MISSING_BLOCK_LABEL_72;
        int i;
        int j;
        int k;
        int l;
        int j1;
        if(!mAnchorInfo.mValid || mPendingScrollPosition != -1 || mPendingSavedState != null)
        {
            mAnchorInfo.reset();
            mAnchorInfo.mLayoutFromEnd = mShouldReverseLayout ^ mStackFromEnd;
            updateAnchorInfoForLayout(recycler, state, mAnchorInfo);
            mAnchorInfo.mValid = true;
        }
        i = getExtraLayoutSpace(state);
        if(mLayoutState.mLastScrollDelta >= 0)
        {
            j = 0;
        } else
        {
            j = i;
            i = 0;
        }
        k = j + mOrientationHelper.getStartAfterPadding();
        l = i + mOrientationHelper.getEndPadding();
        j = l;
        j1 = k;
        if(state.isPreLayout())
        {
            j = l;
            j1 = k;
            if(mPendingScrollPosition != -1)
            {
                j = l;
                j1 = k;
                if(mPendingScrollPositionOffset != 0x80000000)
                {
                    Object obj = findViewByPosition(mPendingScrollPosition);
                    j = l;
                    j1 = k;
                    if(obj != null)
                    {
                        if(mShouldReverseLayout)
                        {
                            i = mOrientationHelper.getEndAfterPadding() - mOrientationHelper.getDecoratedEnd(((View) (obj))) - mPendingScrollPositionOffset;
                        } else
                        {
                            i = mOrientationHelper.getDecoratedStart(((View) (obj)));
                            j = mOrientationHelper.getStartAfterPadding();
                            i = mPendingScrollPositionOffset - (i - j);
                        }
                        if(i > 0)
                        {
                            j1 = k + i;
                            j = l;
                        } else
                        {
                            j = l - i;
                            j1 = k;
                        }
                    }
                }
            }
        }
        if(mAnchorInfo.mLayoutFromEnd)
        {
            if(mShouldReverseLayout)
                i = 1;
            else
                i = -1;
        } else
        if(mShouldReverseLayout)
            i = -1;
        else
            i = 1;
        onAnchorReady(recycler, state, mAnchorInfo, i);
        detachAndScrapAttachedViews(recycler);
        mLayoutState.mInfinite = resolveIsInfinite();
        mLayoutState.mIsPreLayout = state.isPreLayout();
        if(mAnchorInfo.mLayoutFromEnd)
        {
            updateLayoutStateToFillStart(mAnchorInfo);
            mLayoutState.mExtra = j1;
            fill(recycler, mLayoutState, state, false);
            j1 = mLayoutState.mOffset;
            l = mLayoutState.mCurrentPosition;
            i = j;
            if(mLayoutState.mAvailable > 0)
                i = j + mLayoutState.mAvailable;
            updateLayoutStateToFillEnd(mAnchorInfo);
            mLayoutState.mExtra = i;
            obj = mLayoutState;
            obj.mCurrentPosition = ((LayoutState) (obj)).mCurrentPosition + mLayoutState.mItemDirection;
            fill(recycler, mLayoutState, state, false);
            k = mLayoutState.mOffset;
            i = k;
            j = j1;
            if(mLayoutState.mAvailable > 0)
            {
                i = mLayoutState.mAvailable;
                updateLayoutStateToFillStart(l, j1);
                mLayoutState.mExtra = i;
                fill(recycler, mLayoutState, state, false);
                j = mLayoutState.mOffset;
                i = k;
            }
        } else
        {
            updateLayoutStateToFillEnd(mAnchorInfo);
            mLayoutState.mExtra = j;
            fill(recycler, mLayoutState, state, false);
            k = mLayoutState.mOffset;
            int i1 = mLayoutState.mCurrentPosition;
            i = j1;
            if(mLayoutState.mAvailable > 0)
                i = j1 + mLayoutState.mAvailable;
            updateLayoutStateToFillStart(mAnchorInfo);
            mLayoutState.mExtra = i;
            LayoutState layoutstate = mLayoutState;
            layoutstate.mCurrentPosition = layoutstate.mCurrentPosition + mLayoutState.mItemDirection;
            fill(recycler, mLayoutState, state, false);
            j1 = mLayoutState.mOffset;
            i = k;
            j = j1;
            if(mLayoutState.mAvailable > 0)
            {
                i = mLayoutState.mAvailable;
                updateLayoutStateToFillEnd(i1, k);
                mLayoutState.mExtra = i;
                fill(recycler, mLayoutState, state, false);
                i = mLayoutState.mOffset;
                j = j1;
            }
        }
        k = i;
        j1 = j;
        if(getChildCount() > 0)
            if(mShouldReverseLayout ^ mStackFromEnd)
            {
                k = fixLayoutEndGap(i, recycler, state, true);
                j1 = j + k;
                j = fixLayoutStartGap(j1, recycler, state, false);
                j1 += j;
                k = i + k + j;
            } else
            {
                j1 = fixLayoutStartGap(j, recycler, state, true);
                k = i + j1;
                i = fixLayoutEndGap(k, recycler, state, false);
                j1 = j + j1 + i;
                k += i;
            }
        layoutForPredictiveAnimations(recycler, state, j1, k);
        if(!state.isPreLayout())
            mOrientationHelper.onLayoutComplete();
        else
            mAnchorInfo.reset();
        mLastStackFromEnd = mStackFromEnd;
        return;
    }

    public void onLayoutCompleted(RecyclerView.State state)
    {
        super.onLayoutCompleted(state);
        mPendingSavedState = null;
        mPendingScrollPosition = -1;
        mPendingScrollPositionOffset = 0x80000000;
        mAnchorInfo.reset();
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        if(parcelable instanceof SavedState)
        {
            mPendingSavedState = (SavedState)parcelable;
            requestLayout();
        }
    }

    public Parcelable onSaveInstanceState()
    {
        if(mPendingSavedState != null)
            return new SavedState(mPendingSavedState);
        SavedState savedstate = new SavedState();
        if(getChildCount() > 0)
        {
            ensureLayoutState();
            boolean flag = mLastStackFromEnd ^ mShouldReverseLayout;
            savedstate.mAnchorLayoutFromEnd = flag;
            if(flag)
            {
                View view = getChildClosestToEnd();
                savedstate.mAnchorOffset = mOrientationHelper.getEndAfterPadding() - mOrientationHelper.getDecoratedEnd(view);
                savedstate.mAnchorPosition = getPosition(view);
            } else
            {
                View view1 = getChildClosestToStart();
                savedstate.mAnchorPosition = getPosition(view1);
                savedstate.mAnchorOffset = mOrientationHelper.getDecoratedStart(view1) - mOrientationHelper.getStartAfterPadding();
            }
        } else
        {
            savedstate.invalidateAnchor();
        }
        return savedstate;
    }

    public void prepareForDrop(View view, View view1, int i, int j)
    {
        assertNotInLayoutOrScroll("Cannot drop a view during a scroll or layout calculation");
        ensureLayoutState();
        resolveShouldLayoutReverse();
        i = getPosition(view);
        j = getPosition(view1);
        if(i < j)
            i = 1;
        else
            i = -1;
        if(mShouldReverseLayout)
        {
            if(i == 1)
                scrollToPositionWithOffset(j, mOrientationHelper.getEndAfterPadding() - (mOrientationHelper.getDecoratedStart(view1) + mOrientationHelper.getDecoratedMeasurement(view)));
            else
                scrollToPositionWithOffset(j, mOrientationHelper.getEndAfterPadding() - mOrientationHelper.getDecoratedEnd(view1));
        } else
        if(i == -1)
            scrollToPositionWithOffset(j, mOrientationHelper.getDecoratedStart(view1));
        else
            scrollToPositionWithOffset(j, mOrientationHelper.getDecoratedEnd(view1) - mOrientationHelper.getDecoratedMeasurement(view));
    }

    boolean resolveIsInfinite()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mOrientationHelper.getMode() == 0)
        {
            flag1 = flag;
            if(mOrientationHelper.getEnd() == 0)
                flag1 = true;
        }
        return flag1;
    }

    int scrollBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        if(getChildCount() == 0 || i == 0)
            return 0;
        mLayoutState.mRecycle = true;
        ensureLayoutState();
        int j;
        int k;
        int l;
        if(i > 0)
            j = 1;
        else
            j = -1;
        k = Math.abs(i);
        updateLayoutState(j, k, true, state);
        l = mLayoutState.mScrollingOffset + fill(recycler, mLayoutState, state, false);
        if(l < 0)
            return 0;
        if(k > l)
            i = j * l;
        mOrientationHelper.offsetChildren(-i);
        mLayoutState.mLastScrollDelta = i;
        return i;
    }

    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        if(mOrientation == 1)
            return 0;
        else
            return scrollBy(i, recycler, state);
    }

    public void scrollToPosition(int i)
    {
        mPendingScrollPosition = i;
        mPendingScrollPositionOffset = 0x80000000;
        if(mPendingSavedState != null)
            mPendingSavedState.invalidateAnchor();
        requestLayout();
    }

    public void scrollToPositionWithOffset(int i, int j)
    {
        mPendingScrollPosition = i;
        mPendingScrollPositionOffset = j;
        if(mPendingSavedState != null)
            mPendingSavedState.invalidateAnchor();
        requestLayout();
    }

    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        if(mOrientation == 0)
            return 0;
        else
            return scrollBy(i, recycler, state);
    }

    public void setInitialPrefetchItemCount(int i)
    {
        mInitialItemPrefetchCount = i;
    }

    public void setOrientation(int i)
    {
        if(i != 0 && i != 1)
            throw new IllegalArgumentException((new StringBuilder()).append("invalid orientation:").append(i).toString());
        assertNotInLayoutOrScroll(null);
        if(i == mOrientation)
        {
            return;
        } else
        {
            mOrientation = i;
            mOrientationHelper = null;
            requestLayout();
            return;
        }
    }

    public void setRecycleChildrenOnDetach(boolean flag)
    {
        mRecycleChildrenOnDetach = flag;
    }

    public void setReverseLayout(boolean flag)
    {
        assertNotInLayoutOrScroll(null);
        if(flag == mReverseLayout)
        {
            return;
        } else
        {
            mReverseLayout = flag;
            requestLayout();
            return;
        }
    }

    public void setSmoothScrollbarEnabled(boolean flag)
    {
        mSmoothScrollbarEnabled = flag;
    }

    public void setStackFromEnd(boolean flag)
    {
        assertNotInLayoutOrScroll(null);
        if(mStackFromEnd == flag)
        {
            return;
        } else
        {
            mStackFromEnd = flag;
            requestLayout();
            return;
        }
    }

    boolean shouldMeasureTwice()
    {
        boolean flag;
        if(getHeightMode() != 0x40000000 && getWidthMode() != 0x40000000)
            flag = hasFlexibleChildInBothOrientations();
        else
            flag = false;
        return flag;
    }

    public void smoothScrollToPosition(RecyclerView recyclerview, RecyclerView.State state, int i)
    {
        recyclerview = new LinearSmoothScroller(recyclerview.getContext());
        recyclerview.setTargetPosition(i);
        startSmoothScroll(recyclerview);
    }

    public boolean supportsPredictiveItemAnimations()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mPendingSavedState == null)
        {
            flag1 = flag;
            if(mLastStackFromEnd == mStackFromEnd)
                flag1 = true;
        }
        return flag1;
    }

    void validateChildOrder()
    {
        boolean flag = true;
        boolean flag1 = true;
        Log.d("LinearLayoutManager", (new StringBuilder()).append("validating child count ").append(getChildCount()).toString());
        if(getChildCount() < 1)
            return;
        int i = getPosition(getChildAt(0));
        int j = mOrientationHelper.getDecoratedStart(getChildAt(0));
        if(mShouldReverseLayout)
        {
            for(int k = 1; k < getChildCount(); k++)
            {
                View view = getChildAt(k);
                int i1 = getPosition(view);
                int k1 = mOrientationHelper.getDecoratedStart(view);
                if(i1 < i)
                {
                    logChildren();
                    StringBuilder stringbuilder = (new StringBuilder()).append("detected invalid position. loc invalid? ");
                    if(k1 >= j)
                        flag1 = false;
                    throw new RuntimeException(stringbuilder.append(flag1).toString());
                }
                if(k1 > j)
                {
                    logChildren();
                    throw new RuntimeException("detected invalid location");
                }
            }

        } else
        {
            for(int l = 1; l < getChildCount(); l++)
            {
                View view1 = getChildAt(l);
                int j1 = getPosition(view1);
                int l1 = mOrientationHelper.getDecoratedStart(view1);
                if(j1 < i)
                {
                    logChildren();
                    StringBuilder stringbuilder1 = (new StringBuilder()).append("detected invalid position. loc invalid? ");
                    boolean flag2;
                    if(l1 < j)
                        flag2 = flag;
                    else
                        flag2 = false;
                    throw new RuntimeException(stringbuilder1.append(flag2).toString());
                }
                if(l1 < j)
                {
                    logChildren();
                    throw new RuntimeException("detected invalid location");
                }
            }

        }
    }

    static final boolean DEBUG = false;
    public static final int HORIZONTAL = 0;
    public static final int INVALID_OFFSET = 0x80000000;
    private static final float MAX_SCROLL_FACTOR = 0.3333333F;
    private static final String TAG = "LinearLayoutManager";
    public static final int VERTICAL = 1;
    final AnchorInfo mAnchorInfo;
    private int mInitialItemPrefetchCount;
    private boolean mLastStackFromEnd;
    private final LayoutChunkResult mLayoutChunkResult;
    private LayoutState mLayoutState;
    int mOrientation;
    OrientationHelper mOrientationHelper;
    SavedState mPendingSavedState;
    int mPendingScrollPosition;
    int mPendingScrollPositionOffset;
    private boolean mRecycleChildrenOnDetach;
    private boolean mReverseLayout;
    boolean mShouldReverseLayout;
    private boolean mSmoothScrollbarEnabled;
    private boolean mStackFromEnd;
}

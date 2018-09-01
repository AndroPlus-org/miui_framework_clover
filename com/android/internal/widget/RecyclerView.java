// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.accessibility.*;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

// Referenced classes of package com.android.internal.widget:
//            ScrollingView, NestedScrollingChild, ViewInfoStore, DefaultItemAnimator, 
//            RecyclerViewAccessibilityDelegate, ChildHelper, AdapterHelper, GapWorker

public class RecyclerView extends ViewGroup
    implements ScrollingView, NestedScrollingChild
{
    public static abstract class Adapter
    {

        public final void bindViewHolder(ViewHolder viewholder, int i)
        {
            viewholder.mPosition = i;
            if(hasStableIds())
                viewholder.mItemId = getItemId(i);
            viewholder.setFlags(1, 519);
            Trace.beginSection("RV OnBindView");
            onBindViewHolder(viewholder, i, viewholder.getUnmodifiedPayloads());
            viewholder.clearPayload();
            viewholder = viewholder.itemView.getLayoutParams();
            if(viewholder instanceof LayoutParams)
                ((LayoutParams)viewholder).mInsetsDirty = true;
            Trace.endSection();
        }

        public final ViewHolder createViewHolder(ViewGroup viewgroup, int i)
        {
            Trace.beginSection("RV CreateView");
            viewgroup = onCreateViewHolder(viewgroup, i);
            viewgroup.mItemViewType = i;
            Trace.endSection();
            return viewgroup;
        }

        public abstract int getItemCount();

        public long getItemId(int i)
        {
            return -1L;
        }

        public int getItemViewType(int i)
        {
            return 0;
        }

        public final boolean hasObservers()
        {
            return mObservable.hasObservers();
        }

        public final boolean hasStableIds()
        {
            return mHasStableIds;
        }

        public final void notifyDataSetChanged()
        {
            mObservable.notifyChanged();
        }

        public final void notifyItemChanged(int i)
        {
            mObservable.notifyItemRangeChanged(i, 1);
        }

        public final void notifyItemChanged(int i, Object obj)
        {
            mObservable.notifyItemRangeChanged(i, 1, obj);
        }

        public final void notifyItemInserted(int i)
        {
            mObservable.notifyItemRangeInserted(i, 1);
        }

        public final void notifyItemMoved(int i, int j)
        {
            mObservable.notifyItemMoved(i, j);
        }

        public final void notifyItemRangeChanged(int i, int j)
        {
            mObservable.notifyItemRangeChanged(i, j);
        }

        public final void notifyItemRangeChanged(int i, int j, Object obj)
        {
            mObservable.notifyItemRangeChanged(i, j, obj);
        }

        public final void notifyItemRangeInserted(int i, int j)
        {
            mObservable.notifyItemRangeInserted(i, j);
        }

        public final void notifyItemRangeRemoved(int i, int j)
        {
            mObservable.notifyItemRangeRemoved(i, j);
        }

        public final void notifyItemRemoved(int i)
        {
            mObservable.notifyItemRangeRemoved(i, 1);
        }

        public void onAttachedToRecyclerView(RecyclerView recyclerview)
        {
        }

        public abstract void onBindViewHolder(ViewHolder viewholder, int i);

        public void onBindViewHolder(ViewHolder viewholder, int i, List list)
        {
            onBindViewHolder(viewholder, i);
        }

        public abstract ViewHolder onCreateViewHolder(ViewGroup viewgroup, int i);

        public void onDetachedFromRecyclerView(RecyclerView recyclerview)
        {
        }

        public boolean onFailedToRecycleView(ViewHolder viewholder)
        {
            return false;
        }

        public void onViewAttachedToWindow(ViewHolder viewholder)
        {
        }

        public void onViewDetachedFromWindow(ViewHolder viewholder)
        {
        }

        public void onViewRecycled(ViewHolder viewholder)
        {
        }

        public void registerAdapterDataObserver(AdapterDataObserver adapterdataobserver)
        {
            mObservable.registerObserver(adapterdataobserver);
        }

        public void setHasStableIds(boolean flag)
        {
            if(hasObservers())
            {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            } else
            {
                mHasStableIds = flag;
                return;
            }
        }

        public void unregisterAdapterDataObserver(AdapterDataObserver adapterdataobserver)
        {
            mObservable.unregisterObserver(adapterdataobserver);
        }

        private boolean mHasStableIds;
        private final AdapterDataObservable mObservable = new AdapterDataObservable();

        public Adapter()
        {
            mHasStableIds = false;
        }
    }

    static class AdapterDataObservable extends Observable
    {

        public boolean hasObservers()
        {
            return mObservers.isEmpty() ^ true;
        }

        public void notifyChanged()
        {
            for(int i = mObservers.size() - 1; i >= 0; i--)
                ((AdapterDataObserver)mObservers.get(i)).onChanged();

        }

        public void notifyItemMoved(int i, int j)
        {
            for(int k = mObservers.size() - 1; k >= 0; k--)
                ((AdapterDataObserver)mObservers.get(k)).onItemRangeMoved(i, j, 1);

        }

        public void notifyItemRangeChanged(int i, int j)
        {
            notifyItemRangeChanged(i, j, null);
        }

        public void notifyItemRangeChanged(int i, int j, Object obj)
        {
            for(int k = mObservers.size() - 1; k >= 0; k--)
                ((AdapterDataObserver)mObservers.get(k)).onItemRangeChanged(i, j, obj);

        }

        public void notifyItemRangeInserted(int i, int j)
        {
            for(int k = mObservers.size() - 1; k >= 0; k--)
                ((AdapterDataObserver)mObservers.get(k)).onItemRangeInserted(i, j);

        }

        public void notifyItemRangeRemoved(int i, int j)
        {
            for(int k = mObservers.size() - 1; k >= 0; k--)
                ((AdapterDataObserver)mObservers.get(k)).onItemRangeRemoved(i, j);

        }

        AdapterDataObservable()
        {
        }
    }

    public static abstract class AdapterDataObserver
    {

        public void onChanged()
        {
        }

        public void onItemRangeChanged(int i, int j)
        {
        }

        public void onItemRangeChanged(int i, int j, Object obj)
        {
            onItemRangeChanged(i, j);
        }

        public void onItemRangeInserted(int i, int j)
        {
        }

        public void onItemRangeMoved(int i, int j, int k)
        {
        }

        public void onItemRangeRemoved(int i, int j)
        {
        }

        public AdapterDataObserver()
        {
        }
    }

    public static interface ChildDrawingOrderCallback
    {

        public abstract int onGetChildDrawingOrder(int i, int j);
    }

    public static abstract class ItemAnimator
    {

        static int buildAdapterChangeFlagsForAnimations(ViewHolder viewholder)
        {
            int i = ViewHolder._2D_get0(viewholder) & 0xe;
            if(viewholder.isInvalid())
                return 4;
            int j = i;
            if((i & 4) == 0)
            {
                int k = viewholder.getOldPosition();
                int l = viewholder.getAdapterPosition();
                j = i;
                if(k != -1)
                {
                    j = i;
                    if(l != -1)
                    {
                        j = i;
                        if(k != l)
                            j = i | 0x800;
                    }
                }
            }
            return j;
        }

        public abstract boolean animateAppearance(ViewHolder viewholder, ItemHolderInfo itemholderinfo, ItemHolderInfo itemholderinfo1);

        public abstract boolean animateChange(ViewHolder viewholder, ViewHolder viewholder1, ItemHolderInfo itemholderinfo, ItemHolderInfo itemholderinfo1);

        public abstract boolean animateDisappearance(ViewHolder viewholder, ItemHolderInfo itemholderinfo, ItemHolderInfo itemholderinfo1);

        public abstract boolean animatePersistence(ViewHolder viewholder, ItemHolderInfo itemholderinfo, ItemHolderInfo itemholderinfo1);

        public boolean canReuseUpdatedViewHolder(ViewHolder viewholder)
        {
            return true;
        }

        public boolean canReuseUpdatedViewHolder(ViewHolder viewholder, List list)
        {
            return canReuseUpdatedViewHolder(viewholder);
        }

        public final void dispatchAnimationFinished(ViewHolder viewholder)
        {
            onAnimationFinished(viewholder);
            if(mListener != null)
                mListener.onAnimationFinished(viewholder);
        }

        public final void dispatchAnimationStarted(ViewHolder viewholder)
        {
            onAnimationStarted(viewholder);
        }

        public final void dispatchAnimationsFinished()
        {
            int i = mFinishedListeners.size();
            for(int j = 0; j < i; j++)
                ((ItemAnimatorFinishedListener)mFinishedListeners.get(j)).onAnimationsFinished();

            mFinishedListeners.clear();
        }

        public abstract void endAnimation(ViewHolder viewholder);

        public abstract void endAnimations();

        public long getAddDuration()
        {
            return mAddDuration;
        }

        public long getChangeDuration()
        {
            return mChangeDuration;
        }

        public long getMoveDuration()
        {
            return mMoveDuration;
        }

        public long getRemoveDuration()
        {
            return mRemoveDuration;
        }

        public abstract boolean isRunning();

        public final boolean isRunning(ItemAnimatorFinishedListener itemanimatorfinishedlistener)
        {
            boolean flag = isRunning();
            if(itemanimatorfinishedlistener != null)
                if(!flag)
                    itemanimatorfinishedlistener.onAnimationsFinished();
                else
                    mFinishedListeners.add(itemanimatorfinishedlistener);
            return flag;
        }

        public ItemHolderInfo obtainHolderInfo()
        {
            return new ItemHolderInfo();
        }

        public void onAnimationFinished(ViewHolder viewholder)
        {
        }

        public void onAnimationStarted(ViewHolder viewholder)
        {
        }

        public ItemHolderInfo recordPostLayoutInformation(State state, ViewHolder viewholder)
        {
            return obtainHolderInfo().setFrom(viewholder);
        }

        public ItemHolderInfo recordPreLayoutInformation(State state, ViewHolder viewholder, int i, List list)
        {
            return obtainHolderInfo().setFrom(viewholder);
        }

        public abstract void runPendingAnimations();

        public void setAddDuration(long l)
        {
            mAddDuration = l;
        }

        public void setChangeDuration(long l)
        {
            mChangeDuration = l;
        }

        void setListener(ItemAnimatorListener itemanimatorlistener)
        {
            mListener = itemanimatorlistener;
        }

        public void setMoveDuration(long l)
        {
            mMoveDuration = l;
        }

        public void setRemoveDuration(long l)
        {
            mRemoveDuration = l;
        }

        public static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        public static final int FLAG_CHANGED = 2;
        public static final int FLAG_INVALIDATED = 4;
        public static final int FLAG_MOVED = 2048;
        public static final int FLAG_REMOVED = 8;
        private long mAddDuration;
        private long mChangeDuration;
        private ArrayList mFinishedListeners;
        private ItemAnimatorListener mListener;
        private long mMoveDuration;
        private long mRemoveDuration;

        public ItemAnimator()
        {
            mListener = null;
            mFinishedListeners = new ArrayList();
            mAddDuration = 120L;
            mRemoveDuration = 120L;
            mMoveDuration = 250L;
            mChangeDuration = 250L;
        }
    }

    public static interface ItemAnimator.ItemAnimatorFinishedListener
    {

        public abstract void onAnimationsFinished();
    }

    static interface ItemAnimator.ItemAnimatorListener
    {

        public abstract void onAnimationFinished(ViewHolder viewholder);
    }

    public static class ItemAnimator.ItemHolderInfo
    {

        public ItemAnimator.ItemHolderInfo setFrom(ViewHolder viewholder)
        {
            return setFrom(viewholder, 0);
        }

        public ItemAnimator.ItemHolderInfo setFrom(ViewHolder viewholder, int i)
        {
            viewholder = viewholder.itemView;
            left = viewholder.getLeft();
            top = viewholder.getTop();
            right = viewholder.getRight();
            bottom = viewholder.getBottom();
            return this;
        }

        public int bottom;
        public int changeFlags;
        public int left;
        public int right;
        public int top;

        public ItemAnimator.ItemHolderInfo()
        {
        }
    }

    private class ItemAnimatorRestoreListener
        implements ItemAnimator.ItemAnimatorListener
    {

        public void onAnimationFinished(ViewHolder viewholder)
        {
            viewholder.setIsRecyclable(true);
            if(viewholder.mShadowedHolder != null && viewholder.mShadowingHolder == null)
                viewholder.mShadowedHolder = null;
            viewholder.mShadowingHolder = null;
            if(!ViewHolder._2D_wrap1(viewholder) && !removeAnimatingView(viewholder.itemView) && viewholder.isTmpDetached())
                removeDetachedView(viewholder.itemView, false);
        }

        final RecyclerView this$0;

        ItemAnimatorRestoreListener()
        {
            this$0 = RecyclerView.this;
            super();
        }
    }

    public static abstract class ItemDecoration
    {

        public void getItemOffsets(Rect rect, int i, RecyclerView recyclerview)
        {
            rect.set(0, 0, 0, 0);
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerview, State state)
        {
            getItemOffsets(rect, ((LayoutParams)view.getLayoutParams()).getViewLayoutPosition(), recyclerview);
        }

        public void onDraw(Canvas canvas, RecyclerView recyclerview)
        {
        }

        public void onDraw(Canvas canvas, RecyclerView recyclerview, State state)
        {
            onDraw(canvas, recyclerview);
        }

        public void onDrawOver(Canvas canvas, RecyclerView recyclerview)
        {
        }

        public void onDrawOver(Canvas canvas, RecyclerView recyclerview, State state)
        {
            onDrawOver(canvas, recyclerview);
        }

        public ItemDecoration()
        {
        }
    }

    public static abstract class LayoutManager
    {

        static void _2D_wrap0(LayoutManager layoutmanager, SmoothScroller smoothscroller)
        {
            layoutmanager.onSmoothScrollerStopped(smoothscroller);
        }

        private void addViewInt(View view, int i, boolean flag)
        {
            ViewHolder viewholder;
            LayoutParams layoutparams;
            viewholder = RecyclerView.getChildViewHolderInt(view);
            if(flag || viewholder.isRemoved())
                mRecyclerView.mViewInfoStore.addToDisappearedInLayout(viewholder);
            else
                mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(viewholder);
            layoutparams = (LayoutParams)view.getLayoutParams();
            if(!viewholder.wasReturnedFromScrap() && !viewholder.isScrap()) goto _L2; else goto _L1
_L1:
            if(viewholder.isScrap())
                viewholder.unScrap();
            else
                viewholder.clearReturnedFromScrapFlag();
            mChildHelper.attachViewToParent(view, i, view.getLayoutParams(), false);
_L4:
            if(layoutparams.mPendingInvalidate)
            {
                viewholder.itemView.invalidate();
                layoutparams.mPendingInvalidate = false;
            }
            return;
_L2:
            if(view.getParent() == mRecyclerView)
            {
                int j = mChildHelper.indexOfChild(view);
                int k = i;
                if(i == -1)
                    k = mChildHelper.getChildCount();
                if(j == -1)
                    throw new IllegalStateException((new StringBuilder()).append("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:").append(mRecyclerView.indexOfChild(view)).toString());
                if(j != k)
                    mRecyclerView.mLayout.moveView(j, k);
            } else
            {
                mChildHelper.addView(view, i, false);
                layoutparams.mInsetsDirty = true;
                if(mSmoothScroller != null && mSmoothScroller.isRunning())
                    mSmoothScroller.onChildAttachedToWindow(view);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static int chooseSize(int i, int j, int k)
        {
            int l = android.view.View.MeasureSpec.getMode(i);
            i = android.view.View.MeasureSpec.getSize(i);
            switch(l)
            {
            default:
                return Math.max(j, k);

            case 1073741824: 
                return i;

            case -2147483648: 
                return Math.min(i, Math.max(j, k));
            }
        }

        private void detachViewInternal(int i, View view)
        {
            mChildHelper.detachViewFromParent(i);
        }

        public static int getChildMeasureSpec(int i, int j, int k, int l, boolean flag)
        {
            int i1;
            i1 = Math.max(0, i - k);
            k = 0;
            i = 0;
            if(!flag) goto _L2; else goto _L1
_L1:
            if(l < 0) goto _L4; else goto _L3
_L3:
            k = l;
            i = 0x40000000;
_L6:
            return android.view.View.MeasureSpec.makeMeasureSpec(k, i);
_L4:
            if(l == -1)
                switch(j)
                {
                case -2147483648: 
                case 1073741824: 
                    k = i1;
                    i = j;
                    break;

                case 0: // '\0'
                    k = 0;
                    i = 0;
                    break;
                }
            else
            if(l == -2)
            {
                k = 0;
                i = 0;
            }
            continue; /* Loop/switch isn't completed */
_L2:
            if(l >= 0)
            {
                k = l;
                i = 0x40000000;
            } else
            if(l == -1)
            {
                k = i1;
                i = j;
            } else
            if(l == -2)
            {
                k = i1;
                if(j == 0x80000000 || j == 0x40000000)
                    i = 0x80000000;
                else
                    i = 0;
            }
            if(true) goto _L6; else goto _L5
_L5:
        }

        public static int getChildMeasureSpec(int i, int j, int k, boolean flag)
        {
            int l;
            l = Math.max(0, i - j);
            j = 0;
            i = 0;
            if(!flag) goto _L2; else goto _L1
_L1:
            if(k >= 0)
            {
                j = k;
                i = 0x40000000;
            } else
            {
                j = 0;
                i = 0;
            }
_L4:
            return android.view.View.MeasureSpec.makeMeasureSpec(j, i);
_L2:
            if(k >= 0)
            {
                j = k;
                i = 0x40000000;
            } else
            if(k == -1)
            {
                j = l;
                i = 0x40000000;
            } else
            if(k == -2)
            {
                j = l;
                i = 0x80000000;
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static Properties getProperties(Context context, AttributeSet attributeset, int i, int j)
        {
            Properties properties = new Properties();
            context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.RecyclerView, i, j);
            properties.orientation = context.getInt(0, 1);
            properties.spanCount = context.getInt(4, 1);
            properties.reverseLayout = context.getBoolean(3, false);
            properties.stackFromEnd = context.getBoolean(5, false);
            context.recycle();
            return properties;
        }

        private static boolean isMeasurementUpToDate(int i, int j, int k)
        {
            boolean flag = true;
            boolean flag1 = true;
            int l = android.view.View.MeasureSpec.getMode(j);
            j = android.view.View.MeasureSpec.getSize(j);
            if(k > 0 && i != k)
                return false;
            switch(l)
            {
            default:
                return false;

            case 0: // '\0'
                return true;

            case -2147483648: 
                if(j < i)
                    flag1 = false;
                return flag1;

            case 1073741824: 
                break;
            }
            if(j == i)
                flag1 = flag;
            else
                flag1 = false;
            return flag1;
        }

        private void onSmoothScrollerStopped(SmoothScroller smoothscroller)
        {
            if(mSmoothScroller == smoothscroller)
                mSmoothScroller = null;
        }

        private void scrapOrRecycleView(Recycler recycler, int i, View view)
        {
            ViewHolder viewholder = RecyclerView.getChildViewHolderInt(view);
            if(viewholder.shouldIgnore())
                return;
            if(viewholder.isInvalid() && viewholder.isRemoved() ^ true && mRecyclerView.mAdapter.hasStableIds() ^ true)
            {
                removeViewAt(i);
                recycler.recycleViewHolderInternal(viewholder);
            } else
            {
                detachViewAt(i);
                recycler.scrapView(view);
                mRecyclerView.mViewInfoStore.onViewDetached(viewholder);
            }
        }

        public void addDisappearingView(View view)
        {
            addDisappearingView(view, -1);
        }

        public void addDisappearingView(View view, int i)
        {
            addViewInt(view, i, true);
        }

        public void addView(View view)
        {
            addView(view, -1);
        }

        public void addView(View view, int i)
        {
            addViewInt(view, i, false);
        }

        public void assertInLayoutOrScroll(String s)
        {
            if(mRecyclerView != null)
                mRecyclerView.assertInLayoutOrScroll(s);
        }

        public void assertNotInLayoutOrScroll(String s)
        {
            if(mRecyclerView != null)
                mRecyclerView.assertNotInLayoutOrScroll(s);
        }

        public void attachView(View view)
        {
            attachView(view, -1);
        }

        public void attachView(View view, int i)
        {
            attachView(view, i, (LayoutParams)view.getLayoutParams());
        }

        public void attachView(View view, int i, LayoutParams layoutparams)
        {
            ViewHolder viewholder = RecyclerView.getChildViewHolderInt(view);
            if(viewholder.isRemoved())
                mRecyclerView.mViewInfoStore.addToDisappearedInLayout(viewholder);
            else
                mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(viewholder);
            mChildHelper.attachViewToParent(view, i, layoutparams, viewholder.isRemoved());
        }

        public void calculateItemDecorationsForChild(View view, Rect rect)
        {
            if(mRecyclerView == null)
            {
                rect.set(0, 0, 0, 0);
                return;
            } else
            {
                rect.set(mRecyclerView.getItemDecorInsetsForChild(view));
                return;
            }
        }

        public boolean canScrollHorizontally()
        {
            return false;
        }

        public boolean canScrollVertically()
        {
            return false;
        }

        public boolean checkLayoutParams(LayoutParams layoutparams)
        {
            boolean flag;
            if(layoutparams != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public void collectAdjacentPrefetchPositions(int i, int j, State state, LayoutPrefetchRegistry layoutprefetchregistry)
        {
        }

        public void collectInitialPrefetchPositions(int i, LayoutPrefetchRegistry layoutprefetchregistry)
        {
        }

        public int computeHorizontalScrollExtent(State state)
        {
            return 0;
        }

        public int computeHorizontalScrollOffset(State state)
        {
            return 0;
        }

        public int computeHorizontalScrollRange(State state)
        {
            return 0;
        }

        public int computeVerticalScrollExtent(State state)
        {
            return 0;
        }

        public int computeVerticalScrollOffset(State state)
        {
            return 0;
        }

        public int computeVerticalScrollRange(State state)
        {
            return 0;
        }

        public void detachAndScrapAttachedViews(Recycler recycler)
        {
            for(int i = getChildCount() - 1; i >= 0; i--)
                scrapOrRecycleView(recycler, i, getChildAt(i));

        }

        public void detachAndScrapView(View view, Recycler recycler)
        {
            scrapOrRecycleView(recycler, mChildHelper.indexOfChild(view), view);
        }

        public void detachAndScrapViewAt(int i, Recycler recycler)
        {
            scrapOrRecycleView(recycler, i, getChildAt(i));
        }

        public void detachView(View view)
        {
            int i = mChildHelper.indexOfChild(view);
            if(i >= 0)
                detachViewInternal(i, view);
        }

        public void detachViewAt(int i)
        {
            detachViewInternal(i, getChildAt(i));
        }

        void dispatchAttachedToWindow(RecyclerView recyclerview)
        {
            mIsAttachedToWindow = true;
            onAttachedToWindow(recyclerview);
        }

        void dispatchDetachedFromWindow(RecyclerView recyclerview, Recycler recycler)
        {
            mIsAttachedToWindow = false;
            onDetachedFromWindow(recyclerview, recycler);
        }

        public void endAnimation(View view)
        {
            if(mRecyclerView.mItemAnimator != null)
                mRecyclerView.mItemAnimator.endAnimation(RecyclerView.getChildViewHolderInt(view));
        }

        public View findContainingItemView(View view)
        {
            if(mRecyclerView == null)
                return null;
            view = mRecyclerView.findContainingItemView(view);
            if(view == null)
                return null;
            if(mChildHelper.isHidden(view))
                return null;
            else
                return view;
        }

        public View findViewByPosition(int i)
        {
            int j;
            int k;
            j = getChildCount();
            k = 0;
_L3:
            View view;
            ViewHolder viewholder;
            if(k >= j)
                break; /* Loop/switch isn't completed */
            view = getChildAt(k);
            viewholder = RecyclerView.getChildViewHolderInt(view);
              goto _L1
_L5:
            k++;
            if(true) goto _L3; else goto _L2
_L1:
            if(viewholder == null || viewholder.getLayoutPosition() != i || !(viewholder.shouldIgnore() ^ true) || !mRecyclerView.mState.isPreLayout() && !(viewholder.isRemoved() ^ true)) goto _L5; else goto _L4
_L4:
            return view;
_L2:
            return null;
        }

        public abstract LayoutParams generateDefaultLayoutParams();

        public LayoutParams generateLayoutParams(Context context, AttributeSet attributeset)
        {
            return new LayoutParams(context, attributeset);
        }

        public LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            if(layoutparams instanceof LayoutParams)
                return new LayoutParams((LayoutParams)layoutparams);
            if(layoutparams instanceof android.view.ViewGroup.MarginLayoutParams)
                return new LayoutParams((android.view.ViewGroup.MarginLayoutParams)layoutparams);
            else
                return new LayoutParams(layoutparams);
        }

        public int getBaseline()
        {
            return -1;
        }

        public int getBottomDecorationHeight(View view)
        {
            return ((LayoutParams)view.getLayoutParams()).mDecorInsets.bottom;
        }

        public View getChildAt(int i)
        {
            View view = null;
            if(mChildHelper != null)
                view = mChildHelper.getChildAt(i);
            return view;
        }

        public int getChildCount()
        {
            int i;
            if(mChildHelper != null)
                i = mChildHelper.getChildCount();
            else
                i = 0;
            return i;
        }

        public boolean getClipToPadding()
        {
            boolean flag;
            if(mRecyclerView != null)
                flag = mRecyclerView.mClipToPadding;
            else
                flag = false;
            return flag;
        }

        public int getColumnCountForAccessibility(Recycler recycler, State state)
        {
            int i = 1;
            if(mRecyclerView == null || mRecyclerView.mAdapter == null)
                return 1;
            if(canScrollHorizontally())
                i = mRecyclerView.mAdapter.getItemCount();
            return i;
        }

        public int getDecoratedBottom(View view)
        {
            return view.getBottom() + getBottomDecorationHeight(view);
        }

        public void getDecoratedBoundsWithMargins(View view, Rect rect)
        {
            RecyclerView.getDecoratedBoundsWithMarginsInt(view, rect);
        }

        public int getDecoratedLeft(View view)
        {
            return view.getLeft() - getLeftDecorationWidth(view);
        }

        public int getDecoratedMeasuredHeight(View view)
        {
            Rect rect = ((LayoutParams)view.getLayoutParams()).mDecorInsets;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        public int getDecoratedMeasuredWidth(View view)
        {
            Rect rect = ((LayoutParams)view.getLayoutParams()).mDecorInsets;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        public int getDecoratedRight(View view)
        {
            return view.getRight() + getRightDecorationWidth(view);
        }

        public int getDecoratedTop(View view)
        {
            return view.getTop() - getTopDecorationHeight(view);
        }

        public View getFocusedChild()
        {
            if(mRecyclerView == null)
                return null;
            View view = mRecyclerView.getFocusedChild();
            if(view == null || mChildHelper.isHidden(view))
                return null;
            else
                return view;
        }

        public int getHeight()
        {
            return mHeight;
        }

        public int getHeightMode()
        {
            return mHeightMode;
        }

        public int getItemCount()
        {
            Adapter adapter;
            int i;
            if(mRecyclerView != null)
                adapter = mRecyclerView.getAdapter();
            else
                adapter = null;
            if(adapter != null)
                i = adapter.getItemCount();
            else
                i = 0;
            return i;
        }

        public int getItemViewType(View view)
        {
            return RecyclerView.getChildViewHolderInt(view).getItemViewType();
        }

        public int getLayoutDirection()
        {
            return mRecyclerView.getLayoutDirection();
        }

        public int getLeftDecorationWidth(View view)
        {
            return ((LayoutParams)view.getLayoutParams()).mDecorInsets.left;
        }

        public int getMinimumHeight()
        {
            return mRecyclerView.getMinimumHeight();
        }

        public int getMinimumWidth()
        {
            return mRecyclerView.getMinimumWidth();
        }

        public int getPaddingBottom()
        {
            int i;
            if(mRecyclerView != null)
                i = mRecyclerView.getPaddingBottom();
            else
                i = 0;
            return i;
        }

        public int getPaddingEnd()
        {
            int i;
            if(mRecyclerView != null)
                i = mRecyclerView.getPaddingEnd();
            else
                i = 0;
            return i;
        }

        public int getPaddingLeft()
        {
            int i;
            if(mRecyclerView != null)
                i = mRecyclerView.getPaddingLeft();
            else
                i = 0;
            return i;
        }

        public int getPaddingRight()
        {
            int i;
            if(mRecyclerView != null)
                i = mRecyclerView.getPaddingRight();
            else
                i = 0;
            return i;
        }

        public int getPaddingStart()
        {
            int i;
            if(mRecyclerView != null)
                i = mRecyclerView.getPaddingStart();
            else
                i = 0;
            return i;
        }

        public int getPaddingTop()
        {
            int i;
            if(mRecyclerView != null)
                i = mRecyclerView.getPaddingTop();
            else
                i = 0;
            return i;
        }

        public int getPosition(View view)
        {
            return ((LayoutParams)view.getLayoutParams()).getViewLayoutPosition();
        }

        public int getRightDecorationWidth(View view)
        {
            return ((LayoutParams)view.getLayoutParams()).mDecorInsets.right;
        }

        public int getRowCountForAccessibility(Recycler recycler, State state)
        {
            int i = 1;
            if(mRecyclerView == null || mRecyclerView.mAdapter == null)
                return 1;
            if(canScrollVertically())
                i = mRecyclerView.mAdapter.getItemCount();
            return i;
        }

        public int getSelectionModeForAccessibility(Recycler recycler, State state)
        {
            return 0;
        }

        public int getTopDecorationHeight(View view)
        {
            return ((LayoutParams)view.getLayoutParams()).mDecorInsets.top;
        }

        public void getTransformedBoundingBox(View view, boolean flag, Rect rect)
        {
            if(flag)
            {
                Rect rect1 = ((LayoutParams)view.getLayoutParams()).mDecorInsets;
                rect.set(-rect1.left, -rect1.top, view.getWidth() + rect1.right, view.getHeight() + rect1.bottom);
            } else
            {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if(mRecyclerView != null)
            {
                Matrix matrix = view.getMatrix();
                if(matrix != null && matrix.isIdentity() ^ true)
                {
                    RectF rectf = mRecyclerView.mTempRectF;
                    rectf.set(rect);
                    matrix.mapRect(rectf);
                    rect.set((int)Math.floor(rectf.left), (int)Math.floor(rectf.top), (int)Math.ceil(rectf.right), (int)Math.ceil(rectf.bottom));
                }
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public int getWidth()
        {
            return mWidth;
        }

        public int getWidthMode()
        {
            return mWidthMode;
        }

        boolean hasFlexibleChildInBothOrientations()
        {
            int i = getChildCount();
            for(int j = 0; j < i; j++)
            {
                android.view.ViewGroup.LayoutParams layoutparams = getChildAt(j).getLayoutParams();
                if(layoutparams.width < 0 && layoutparams.height < 0)
                    return true;
            }

            return false;
        }

        public boolean hasFocus()
        {
            boolean flag;
            if(mRecyclerView != null)
                flag = mRecyclerView.hasFocus();
            else
                flag = false;
            return flag;
        }

        public void ignoreView(View view)
        {
            if(view.getParent() != mRecyclerView || mRecyclerView.indexOfChild(view) == -1)
            {
                throw new IllegalArgumentException("View should be fully attached to be ignored");
            } else
            {
                view = RecyclerView.getChildViewHolderInt(view);
                view.addFlags(128);
                mRecyclerView.mViewInfoStore.removeViewHolder(view);
                return;
            }
        }

        public boolean isAttachedToWindow()
        {
            return mIsAttachedToWindow;
        }

        public boolean isAutoMeasureEnabled()
        {
            return mAutoMeasure;
        }

        public boolean isFocused()
        {
            boolean flag;
            if(mRecyclerView != null)
                flag = mRecyclerView.isFocused();
            else
                flag = false;
            return flag;
        }

        public final boolean isItemPrefetchEnabled()
        {
            return mItemPrefetchEnabled;
        }

        public boolean isLayoutHierarchical(Recycler recycler, State state)
        {
            return false;
        }

        public boolean isMeasurementCacheEnabled()
        {
            return mMeasurementCacheEnabled;
        }

        public boolean isSmoothScrolling()
        {
            boolean flag;
            if(mSmoothScroller != null)
                flag = mSmoothScroller.isRunning();
            else
                flag = false;
            return flag;
        }

        public void layoutDecorated(View view, int i, int j, int k, int l)
        {
            Rect rect = ((LayoutParams)view.getLayoutParams()).mDecorInsets;
            view.layout(rect.left + i, rect.top + j, k - rect.right, l - rect.bottom);
        }

        public void layoutDecoratedWithMargins(View view, int i, int j, int k, int l)
        {
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            Rect rect = layoutparams.mDecorInsets;
            view.layout(rect.left + i + layoutparams.leftMargin, rect.top + j + layoutparams.topMargin, k - rect.right - layoutparams.rightMargin, l - rect.bottom - layoutparams.bottomMargin);
        }

        public void measureChild(View view, int i, int j)
        {
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            Rect rect = mRecyclerView.getItemDecorInsetsForChild(view);
            int k = rect.left;
            int l = rect.right;
            int i1 = rect.top;
            int j1 = rect.bottom;
            i = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight() + (i + (k + l)), layoutparams.width, canScrollHorizontally());
            j = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom() + (j + (i1 + j1)), layoutparams.height, canScrollVertically());
            if(shouldMeasureChild(view, i, j, layoutparams))
                view.measure(i, j);
        }

        public void measureChildWithMargins(View view, int i, int j)
        {
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            Rect rect = mRecyclerView.getItemDecorInsetsForChild(view);
            int k = rect.left;
            int l = rect.right;
            int i1 = rect.top;
            int j1 = rect.bottom;
            i = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight() + layoutparams.leftMargin + layoutparams.rightMargin + (i + (k + l)), layoutparams.width, canScrollHorizontally());
            j = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom() + layoutparams.topMargin + layoutparams.bottomMargin + (j + (i1 + j1)), layoutparams.height, canScrollVertically());
            if(shouldMeasureChild(view, i, j, layoutparams))
                view.measure(i, j);
        }

        public void moveView(int i, int j)
        {
            View view = getChildAt(i);
            if(view == null)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Cannot move a child from non-existing index:").append(i).toString());
            } else
            {
                detachViewAt(i);
                attachView(view, j);
                return;
            }
        }

        public void offsetChildrenHorizontal(int i)
        {
            if(mRecyclerView != null)
                mRecyclerView.offsetChildrenHorizontal(i);
        }

        public void offsetChildrenVertical(int i)
        {
            if(mRecyclerView != null)
                mRecyclerView.offsetChildrenVertical(i);
        }

        public void onAdapterChanged(Adapter adapter, Adapter adapter1)
        {
        }

        public boolean onAddFocusables(RecyclerView recyclerview, ArrayList arraylist, int i, int j)
        {
            return false;
        }

        public void onAttachedToWindow(RecyclerView recyclerview)
        {
        }

        public void onDetachedFromWindow(RecyclerView recyclerview)
        {
        }

        public void onDetachedFromWindow(RecyclerView recyclerview, Recycler recycler)
        {
            onDetachedFromWindow(recyclerview);
        }

        public View onFocusSearchFailed(View view, int i, Recycler recycler, State state)
        {
            return null;
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
        {
            onInitializeAccessibilityEvent(mRecyclerView.mRecycler, mRecyclerView.mState, accessibilityevent);
        }

        public void onInitializeAccessibilityEvent(Recycler recycler, State state, AccessibilityEvent accessibilityevent)
        {
            boolean flag = true;
            if(mRecyclerView == null || accessibilityevent == null)
                return;
            boolean flag1 = flag;
            if(!mRecyclerView.canScrollVertically(1))
            {
                flag1 = flag;
                if(!mRecyclerView.canScrollVertically(-1))
                {
                    flag1 = flag;
                    if(!mRecyclerView.canScrollHorizontally(-1))
                        flag1 = mRecyclerView.canScrollHorizontally(1);
                }
            }
            accessibilityevent.setScrollable(flag1);
            if(mRecyclerView.mAdapter != null)
                accessibilityevent.setItemCount(mRecyclerView.mAdapter.getItemCount());
        }

        void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            onInitializeAccessibilityNodeInfo(mRecyclerView.mRecycler, mRecyclerView.mState, accessibilitynodeinfo);
        }

        public void onInitializeAccessibilityNodeInfo(Recycler recycler, State state, AccessibilityNodeInfo accessibilitynodeinfo)
        {
            if(mRecyclerView.canScrollVertically(-1) || mRecyclerView.canScrollHorizontally(-1))
            {
                accessibilitynodeinfo.addAction(8192);
                accessibilitynodeinfo.setScrollable(true);
            }
            if(mRecyclerView.canScrollVertically(1) || mRecyclerView.canScrollHorizontally(1))
            {
                accessibilitynodeinfo.addAction(4096);
                accessibilitynodeinfo.setScrollable(true);
            }
            accessibilitynodeinfo.setCollectionInfo(android.view.accessibility.AccessibilityNodeInfo.CollectionInfo.obtain(getRowCountForAccessibility(recycler, state), getColumnCountForAccessibility(recycler, state), isLayoutHierarchical(recycler, state), getSelectionModeForAccessibility(recycler, state)));
        }

        void onInitializeAccessibilityNodeInfoForItem(View view, AccessibilityNodeInfo accessibilitynodeinfo)
        {
            ViewHolder viewholder = RecyclerView.getChildViewHolderInt(view);
            if(viewholder != null && viewholder.isRemoved() ^ true && mChildHelper.isHidden(viewholder.itemView) ^ true)
                onInitializeAccessibilityNodeInfoForItem(mRecyclerView.mRecycler, mRecyclerView.mState, view, accessibilitynodeinfo);
        }

        public void onInitializeAccessibilityNodeInfoForItem(Recycler recycler, State state, View view, AccessibilityNodeInfo accessibilitynodeinfo)
        {
            int i;
            int j;
            if(canScrollVertically())
                i = getPosition(view);
            else
                i = 0;
            if(canScrollHorizontally())
                j = getPosition(view);
            else
                j = 0;
            accessibilitynodeinfo.setCollectionItemInfo(android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo.obtain(i, 1, j, 1, false, false));
        }

        public View onInterceptFocusSearch(View view, int i)
        {
            return null;
        }

        public void onItemsAdded(RecyclerView recyclerview, int i, int j)
        {
        }

        public void onItemsChanged(RecyclerView recyclerview)
        {
        }

        public void onItemsMoved(RecyclerView recyclerview, int i, int j, int k)
        {
        }

        public void onItemsRemoved(RecyclerView recyclerview, int i, int j)
        {
        }

        public void onItemsUpdated(RecyclerView recyclerview, int i, int j)
        {
        }

        public void onItemsUpdated(RecyclerView recyclerview, int i, int j, Object obj)
        {
            onItemsUpdated(recyclerview, i, j);
        }

        public void onLayoutChildren(Recycler recycler, State state)
        {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public void onLayoutCompleted(State state)
        {
        }

        public void onMeasure(Recycler recycler, State state, int i, int j)
        {
            mRecyclerView.defaultOnMeasure(i, j);
        }

        public boolean onRequestChildFocus(RecyclerView recyclerview, View view, View view1)
        {
            boolean flag;
            if(!isSmoothScrolling())
                flag = recyclerview.isComputingLayout();
            else
                flag = true;
            return flag;
        }

        public boolean onRequestChildFocus(RecyclerView recyclerview, State state, View view, View view1)
        {
            return onRequestChildFocus(recyclerview, view, view1);
        }

        public void onRestoreInstanceState(Parcelable parcelable)
        {
        }

        public Parcelable onSaveInstanceState()
        {
            return null;
        }

        public void onScrollStateChanged(int i)
        {
        }

        boolean performAccessibilityAction(int i, Bundle bundle)
        {
            return performAccessibilityAction(mRecyclerView.mRecycler, mRecyclerView.mState, i, bundle);
        }

        public boolean performAccessibilityAction(Recycler recycler, State state, int i, Bundle bundle)
        {
            boolean flag;
            boolean flag1;
            int j;
            int l;
            if(mRecyclerView == null)
                return false;
            flag = false;
            flag1 = false;
            j = 0;
            l = 0;
            i;
            JVM INSTR lookupswitch 2: default 48
        //                       4096: 131
        //                       8192: 62;
               goto _L1 _L2 _L3
_L2:
            break MISSING_BLOCK_LABEL_131;
_L1:
            i = j;
_L4:
            if(i == 0 && l == 0)
            {
                return false;
            } else
            {
                mRecyclerView.scrollBy(l, i);
                return true;
            }
_L3:
            j = ((flag) ? 1 : 0);
            if(mRecyclerView.canScrollVertically(-1))
                j = -(getHeight() - getPaddingTop() - getPaddingBottom());
            i = j;
            if(mRecyclerView.canScrollHorizontally(-1))
            {
                l = -(getWidth() - getPaddingLeft() - getPaddingRight());
                i = j;
            }
              goto _L4
            int k = ((flag1) ? 1 : 0);
            if(mRecyclerView.canScrollVertically(1))
                k = getHeight() - getPaddingTop() - getPaddingBottom();
            i = k;
            if(mRecyclerView.canScrollHorizontally(1))
            {
                l = getWidth() - getPaddingLeft() - getPaddingRight();
                i = k;
            }
              goto _L4
        }

        boolean performAccessibilityActionForItem(View view, int i, Bundle bundle)
        {
            return performAccessibilityActionForItem(mRecyclerView.mRecycler, mRecyclerView.mState, view, i, bundle);
        }

        public boolean performAccessibilityActionForItem(Recycler recycler, State state, View view, int i, Bundle bundle)
        {
            return false;
        }

        public void postOnAnimation(Runnable runnable)
        {
            if(mRecyclerView != null)
                mRecyclerView.postOnAnimation(runnable);
        }

        public void removeAllViews()
        {
            for(int i = getChildCount() - 1; i >= 0; i--)
                mChildHelper.removeViewAt(i);

        }

        public void removeAndRecycleAllViews(Recycler recycler)
        {
            for(int i = getChildCount() - 1; i >= 0; i--)
                if(!RecyclerView.getChildViewHolderInt(getChildAt(i)).shouldIgnore())
                    removeAndRecycleViewAt(i, recycler);

        }

        void removeAndRecycleScrapInt(Recycler recycler)
        {
            int i = recycler.getScrapCount();
            int j = i - 1;
            while(j >= 0) 
            {
                View view = recycler.getScrapViewAt(j);
                ViewHolder viewholder = RecyclerView.getChildViewHolderInt(view);
                if(!viewholder.shouldIgnore())
                {
                    viewholder.setIsRecyclable(false);
                    if(viewholder.isTmpDetached())
                        mRecyclerView.removeDetachedView(view, false);
                    if(mRecyclerView.mItemAnimator != null)
                        mRecyclerView.mItemAnimator.endAnimation(viewholder);
                    viewholder.setIsRecyclable(true);
                    recycler.quickRecycleScrapView(view);
                }
                j--;
            }
            recycler.clearScrap();
            if(i > 0)
                mRecyclerView.invalidate();
        }

        public void removeAndRecycleView(View view, Recycler recycler)
        {
            removeView(view);
            recycler.recycleView(view);
        }

        public void removeAndRecycleViewAt(int i, Recycler recycler)
        {
            View view = getChildAt(i);
            removeViewAt(i);
            recycler.recycleView(view);
        }

        public boolean removeCallbacks(Runnable runnable)
        {
            if(mRecyclerView != null)
                return mRecyclerView.removeCallbacks(runnable);
            else
                return false;
        }

        public void removeDetachedView(View view)
        {
            mRecyclerView.removeDetachedView(view, false);
        }

        public void removeView(View view)
        {
            mChildHelper.removeView(view);
        }

        public void removeViewAt(int i)
        {
            if(getChildAt(i) != null)
                mChildHelper.removeViewAt(i);
        }

        public boolean requestChildRectangleOnScreen(RecyclerView recyclerview, View view, Rect rect, boolean flag)
        {
            int i = getPaddingLeft();
            int j = getPaddingTop();
            int k = getWidth() - getPaddingRight();
            int l = getHeight();
            int i1 = getPaddingBottom();
            int j1 = (view.getLeft() + rect.left) - view.getScrollX();
            int k1 = (view.getTop() + rect.top) - view.getScrollY();
            int l1 = j1 + rect.width();
            int i2 = rect.height();
            int j2 = Math.min(0, j1 - i);
            int k2 = Math.min(0, k1 - j);
            int l2 = Math.max(0, l1 - k);
            i2 = Math.max(0, (k1 + i2) - (l - i1));
            if(getLayoutDirection() == 1)
            {
                if(l2 != 0)
                    j2 = l2;
                else
                    j2 = Math.max(j2, l1 - k);
            } else
            if(j2 == 0)
                j2 = Math.min(j1 - i, l2);
            if(k2 == 0)
                k2 = Math.min(k1 - j, i2);
            if(j2 != 0 || k2 != 0)
            {
                if(flag)
                    recyclerview.scrollBy(j2, k2);
                else
                    recyclerview.smoothScrollBy(j2, k2);
                return true;
            } else
            {
                return false;
            }
        }

        public void requestLayout()
        {
            if(mRecyclerView != null)
                mRecyclerView.requestLayout();
        }

        public void requestSimpleAnimationsInNextLayout()
        {
            mRequestedSimpleAnimations = true;
        }

        public int scrollHorizontallyBy(int i, Recycler recycler, State state)
        {
            return 0;
        }

        public void scrollToPosition(int i)
        {
        }

        public int scrollVerticallyBy(int i, Recycler recycler, State state)
        {
            return 0;
        }

        public void setAutoMeasureEnabled(boolean flag)
        {
            mAutoMeasure = flag;
        }

        void setExactMeasureSpecsFrom(RecyclerView recyclerview)
        {
            setMeasureSpecs(android.view.View.MeasureSpec.makeMeasureSpec(recyclerview.getWidth(), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(recyclerview.getHeight(), 0x40000000));
        }

        public final void setItemPrefetchEnabled(boolean flag)
        {
            if(flag != mItemPrefetchEnabled)
            {
                mItemPrefetchEnabled = flag;
                mPrefetchMaxCountObserved = 0;
                if(mRecyclerView != null)
                    mRecyclerView.mRecycler.updateViewCacheSize();
            }
        }

        void setMeasureSpecs(int i, int j)
        {
            mWidth = android.view.View.MeasureSpec.getSize(i);
            mWidthMode = android.view.View.MeasureSpec.getMode(i);
            if(mWidthMode == 0 && RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC ^ true)
                mWidth = 0;
            mHeight = android.view.View.MeasureSpec.getSize(j);
            mHeightMode = android.view.View.MeasureSpec.getMode(j);
            if(mHeightMode == 0 && RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC ^ true)
                mHeight = 0;
        }

        public void setMeasuredDimension(int i, int j)
        {
            RecyclerView._2D_wrap3(mRecyclerView, i, j);
        }

        public void setMeasuredDimension(Rect rect, int i, int j)
        {
            int k = rect.width();
            int l = getPaddingLeft();
            int i1 = getPaddingRight();
            int j1 = rect.height();
            int k1 = getPaddingTop();
            int l1 = getPaddingBottom();
            setMeasuredDimension(chooseSize(i, k + l + i1, getMinimumWidth()), chooseSize(j, j1 + k1 + l1, getMinimumHeight()));
        }

        void setMeasuredDimensionFromChildren(int i, int j)
        {
            int k = getChildCount();
            if(k == 0)
            {
                mRecyclerView.defaultOnMeasure(i, j);
                return;
            }
            int l = 0x7fffffff;
            int i1 = 0x7fffffff;
            int j1 = 0x80000000;
            int k1 = 0x80000000;
            for(int l1 = 0; l1 < k;)
            {
                View view = getChildAt(l1);
                Rect rect = mRecyclerView.mTempRect;
                getDecoratedBoundsWithMargins(view, rect);
                int i2 = l;
                if(rect.left < l)
                    i2 = rect.left;
                l = j1;
                if(rect.right > j1)
                    l = rect.right;
                int j2 = i1;
                if(rect.top < i1)
                    j2 = rect.top;
                i1 = k1;
                if(rect.bottom > k1)
                    i1 = rect.bottom;
                l1++;
                j1 = l;
                k1 = i1;
                l = i2;
                i1 = j2;
            }

            mRecyclerView.mTempRect.set(l, i1, j1, k1);
            setMeasuredDimension(mRecyclerView.mTempRect, i, j);
        }

        public void setMeasurementCacheEnabled(boolean flag)
        {
            mMeasurementCacheEnabled = flag;
        }

        void setRecyclerView(RecyclerView recyclerview)
        {
            if(recyclerview == null)
            {
                mRecyclerView = null;
                mChildHelper = null;
                mWidth = 0;
                mHeight = 0;
            } else
            {
                mRecyclerView = recyclerview;
                mChildHelper = recyclerview.mChildHelper;
                mWidth = recyclerview.getWidth();
                mHeight = recyclerview.getHeight();
            }
            mWidthMode = 0x40000000;
            mHeightMode = 0x40000000;
        }

        boolean shouldMeasureChild(View view, int i, int j, LayoutParams layoutparams)
        {
            boolean flag;
            if(!view.isLayoutRequested() && !(mMeasurementCacheEnabled ^ true) && !(isMeasurementUpToDate(view.getWidth(), i, layoutparams.width) ^ true))
                flag = isMeasurementUpToDate(view.getHeight(), j, layoutparams.height) ^ true;
            else
                flag = true;
            return flag;
        }

        boolean shouldMeasureTwice()
        {
            return false;
        }

        boolean shouldReMeasureChild(View view, int i, int j, LayoutParams layoutparams)
        {
            boolean flag;
            if(mMeasurementCacheEnabled && !(isMeasurementUpToDate(view.getMeasuredWidth(), i, layoutparams.width) ^ true))
                flag = isMeasurementUpToDate(view.getMeasuredHeight(), j, layoutparams.height) ^ true;
            else
                flag = true;
            return flag;
        }

        public void smoothScrollToPosition(RecyclerView recyclerview, State state, int i)
        {
            Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
        }

        public void startSmoothScroll(SmoothScroller smoothscroller)
        {
            if(mSmoothScroller != null && smoothscroller != mSmoothScroller && mSmoothScroller.isRunning())
                mSmoothScroller.stop();
            mSmoothScroller = smoothscroller;
            mSmoothScroller.start(mRecyclerView, this);
        }

        public void stopIgnoringView(View view)
        {
            view = RecyclerView.getChildViewHolderInt(view);
            view.stopIgnoring();
            view.resetInternal();
            view.addFlags(4);
        }

        void stopSmoothScroller()
        {
            if(mSmoothScroller != null)
                mSmoothScroller.stop();
        }

        public boolean supportsPredictiveItemAnimations()
        {
            return false;
        }

        boolean mAutoMeasure;
        ChildHelper mChildHelper;
        private int mHeight;
        private int mHeightMode;
        boolean mIsAttachedToWindow;
        private boolean mItemPrefetchEnabled;
        private boolean mMeasurementCacheEnabled;
        int mPrefetchMaxCountObserved;
        boolean mPrefetchMaxObservedInInitialPrefetch;
        RecyclerView mRecyclerView;
        boolean mRequestedSimpleAnimations;
        SmoothScroller mSmoothScroller;
        private int mWidth;
        private int mWidthMode;

        public LayoutManager()
        {
            mRequestedSimpleAnimations = false;
            mIsAttachedToWindow = false;
            mAutoMeasure = false;
            mMeasurementCacheEnabled = true;
            mItemPrefetchEnabled = true;
        }
    }

    public static interface LayoutManager.LayoutPrefetchRegistry
    {

        public abstract void addPosition(int i, int j);
    }

    public static class LayoutManager.Properties
    {

        public int orientation;
        public boolean reverseLayout;
        public int spanCount;
        public boolean stackFromEnd;

        public LayoutManager.Properties()
        {
        }
    }

    public static class LayoutParams extends android.view.ViewGroup.MarginLayoutParams
    {

        public int getViewAdapterPosition()
        {
            return mViewHolder.getAdapterPosition();
        }

        public int getViewLayoutPosition()
        {
            return mViewHolder.getLayoutPosition();
        }

        public int getViewPosition()
        {
            return mViewHolder.getPosition();
        }

        public boolean isItemChanged()
        {
            return mViewHolder.isUpdated();
        }

        public boolean isItemRemoved()
        {
            return mViewHolder.isRemoved();
        }

        public boolean isViewInvalid()
        {
            return mViewHolder.isInvalid();
        }

        public boolean viewNeedsUpdate()
        {
            return mViewHolder.needsUpdate();
        }

        final Rect mDecorInsets;
        boolean mInsetsDirty;
        boolean mPendingInvalidate;
        ViewHolder mViewHolder;

        public LayoutParams(int i, int j)
        {
            super(i, j);
            mDecorInsets = new Rect();
            mInsetsDirty = true;
            mPendingInvalidate = false;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            mDecorInsets = new Rect();
            mInsetsDirty = true;
            mPendingInvalidate = false;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            mDecorInsets = new Rect();
            mInsetsDirty = true;
            mPendingInvalidate = false;
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
            mDecorInsets = new Rect();
            mInsetsDirty = true;
            mPendingInvalidate = false;
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            super(layoutparams);
            mDecorInsets = new Rect();
            mInsetsDirty = true;
            mPendingInvalidate = false;
        }
    }

    public static interface OnChildAttachStateChangeListener
    {

        public abstract void onChildViewAttachedToWindow(View view);

        public abstract void onChildViewDetachedFromWindow(View view);
    }

    public static abstract class OnFlingListener
    {

        public abstract boolean onFling(int i, int j);

        public OnFlingListener()
        {
        }
    }

    public static interface OnItemTouchListener
    {

        public abstract boolean onInterceptTouchEvent(RecyclerView recyclerview, MotionEvent motionevent);

        public abstract void onRequestDisallowInterceptTouchEvent(boolean flag);

        public abstract void onTouchEvent(RecyclerView recyclerview, MotionEvent motionevent);
    }

    public static abstract class OnScrollListener
    {

        public void onScrollStateChanged(RecyclerView recyclerview, int i)
        {
        }

        public void onScrolled(RecyclerView recyclerview, int i, int j)
        {
        }

        public OnScrollListener()
        {
        }
    }

    public static class RecycledViewPool
    {

        private ScrapData getScrapDataForType(int i)
        {
            ScrapData scrapdata = (ScrapData)mScrap.get(i);
            ScrapData scrapdata1 = scrapdata;
            if(scrapdata == null)
            {
                scrapdata1 = new ScrapData();
                mScrap.put(i, scrapdata1);
            }
            return scrapdata1;
        }

        void attach(Adapter adapter)
        {
            mAttachCount = mAttachCount + 1;
        }

        public void clear()
        {
            for(int i = 0; i < mScrap.size(); i++)
                ((ScrapData)mScrap.valueAt(i)).mScrapHeap.clear();

        }

        void detach()
        {
            mAttachCount = mAttachCount - 1;
        }

        void factorInBindTime(int i, long l)
        {
            ScrapData scrapdata = getScrapDataForType(i);
            scrapdata.mBindRunningAverageNs = runningAverage(scrapdata.mBindRunningAverageNs, l);
        }

        void factorInCreateTime(int i, long l)
        {
            ScrapData scrapdata = getScrapDataForType(i);
            scrapdata.mCreateRunningAverageNs = runningAverage(scrapdata.mCreateRunningAverageNs, l);
        }

        public ViewHolder getRecycledView(int i)
        {
            Object obj = (ScrapData)mScrap.get(i);
            if(obj != null && ((ScrapData) (obj)).mScrapHeap.isEmpty() ^ true)
            {
                obj = ((ScrapData) (obj)).mScrapHeap;
                return (ViewHolder)((ArrayList) (obj)).remove(((ArrayList) (obj)).size() - 1);
            } else
            {
                return null;
            }
        }

        public int getRecycledViewCount(int i)
        {
            return getScrapDataForType(i).mScrapHeap.size();
        }

        void onAdapterChanged(Adapter adapter, Adapter adapter1, boolean flag)
        {
            if(adapter != null)
                detach();
            if(!flag && mAttachCount == 0)
                clear();
            if(adapter1 != null)
                attach(adapter1);
        }

        public void putRecycledView(ViewHolder viewholder)
        {
            int i = viewholder.getItemViewType();
            ArrayList arraylist = getScrapDataForType(i).mScrapHeap;
            if(((ScrapData)mScrap.get(i)).mMaxScrap <= arraylist.size())
            {
                return;
            } else
            {
                viewholder.resetInternal();
                arraylist.add(viewholder);
                return;
            }
        }

        long runningAverage(long l, long l1)
        {
            if(l == 0L)
                return l1;
            else
                return (l / 4L) * 3L + l1 / 4L;
        }

        public void setMaxRecycledViews(int i, int j)
        {
            Object obj = getScrapDataForType(i);
            obj.mMaxScrap = j;
            obj = ((ScrapData) (obj)).mScrapHeap;
            if(obj != null)
                for(; ((ArrayList) (obj)).size() > j; ((ArrayList) (obj)).remove(((ArrayList) (obj)).size() - 1));
        }

        int size()
        {
            int i = 0;
            for(int j = 0; j < mScrap.size();)
            {
                ArrayList arraylist = ((ScrapData)mScrap.valueAt(j)).mScrapHeap;
                int k = i;
                if(arraylist != null)
                    k = i + arraylist.size();
                j++;
                i = k;
            }

            return i;
        }

        boolean willBindInTime(int i, long l, long l1)
        {
            boolean flag = true;
            long l2 = getScrapDataForType(i).mBindRunningAverageNs;
            boolean flag1 = flag;
            if(l2 != 0L)
                if(l + l2 < l1)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        boolean willCreateInTime(int i, long l, long l1)
        {
            boolean flag = true;
            long l2 = getScrapDataForType(i).mCreateRunningAverageNs;
            boolean flag1 = flag;
            if(l2 != 0L)
                if(l + l2 < l1)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        private static final int DEFAULT_MAX_SCRAP = 5;
        private int mAttachCount;
        SparseArray mScrap;

        public RecycledViewPool()
        {
            mScrap = new SparseArray();
            mAttachCount = 0;
        }
    }

    static class RecycledViewPool.ScrapData
    {

        long mBindRunningAverageNs;
        long mCreateRunningAverageNs;
        int mMaxScrap;
        ArrayList mScrapHeap;

        RecycledViewPool.ScrapData()
        {
            mScrapHeap = new ArrayList();
            mMaxScrap = 5;
            mCreateRunningAverageNs = 0L;
            mBindRunningAverageNs = 0L;
        }
    }

    public final class Recycler
    {

        private void attachAccessibilityDelegate(View view)
        {
            if(isAccessibilityEnabled())
            {
                if(view.getImportantForAccessibility() == 0)
                    view.setImportantForAccessibility(1);
                if(view.getAccessibilityDelegate() == null)
                    view.setAccessibilityDelegate(mAccessibilityDelegate.getItemDelegate());
            }
        }

        private void invalidateDisplayListInt(ViewGroup viewgroup, boolean flag)
        {
            for(int i = viewgroup.getChildCount() - 1; i >= 0; i--)
            {
                View view = viewgroup.getChildAt(i);
                if(view instanceof ViewGroup)
                    invalidateDisplayListInt((ViewGroup)view, true);
            }

            if(!flag)
                return;
            if(viewgroup.getVisibility() == 4)
            {
                viewgroup.setVisibility(0);
                viewgroup.setVisibility(4);
            } else
            {
                int j = viewgroup.getVisibility();
                viewgroup.setVisibility(4);
                viewgroup.setVisibility(j);
            }
        }

        private void invalidateDisplayListInt(ViewHolder viewholder)
        {
            if(viewholder.itemView instanceof ViewGroup)
                invalidateDisplayListInt((ViewGroup)viewholder.itemView, false);
        }

        private boolean tryBindViewHolderByDeadline(ViewHolder viewholder, int i, int j, long l)
        {
            viewholder.mOwnerRecyclerView = RecyclerView.this;
            int k = viewholder.getItemViewType();
            long l1 = getNanoTime();
            if(l != 0x7fffffffffffffffL && mRecyclerPool.willBindInTime(k, l1, l) ^ true)
                return false;
            mAdapter.bindViewHolder(viewholder, i);
            l = getNanoTime();
            mRecyclerPool.factorInBindTime(viewholder.getItemViewType(), l - l1);
            attachAccessibilityDelegate(viewholder.itemView);
            if(mState.isPreLayout())
                viewholder.mPreLayoutPosition = j;
            return true;
        }

        void addViewHolderToRecycledViewPool(ViewHolder viewholder, boolean flag)
        {
            RecyclerView.clearNestedRecyclerViewIfNotNested(viewholder);
            viewholder.itemView.setAccessibilityDelegate(null);
            if(flag)
                dispatchViewRecycled(viewholder);
            viewholder.mOwnerRecyclerView = null;
            getRecycledViewPool().putRecycledView(viewholder);
        }

        public void bindViewToPosition(View view, int i)
        {
            ViewHolder viewholder = RecyclerView.getChildViewHolderInt(view);
            if(viewholder == null)
                throw new IllegalArgumentException("The view does not have a ViewHolder. You cannot pass arbitrary views to this method, they should be created by the Adapter");
            int j = mAdapterHelper.findPositionOffset(i);
            if(j < 0 || j >= mAdapter.getItemCount())
                throw new IndexOutOfBoundsException((new StringBuilder()).append("Inconsistency detected. Invalid item position ").append(i).append("(offset:").append(j).append(").").append("state:").append(mState.getItemCount()).toString());
            tryBindViewHolderByDeadline(viewholder, j, i, 0x7fffffffffffffffL);
            view = viewholder.itemView.getLayoutParams();
            boolean flag;
            if(view == null)
            {
                view = (LayoutParams)generateDefaultLayoutParams();
                viewholder.itemView.setLayoutParams(view);
            } else
            if(!checkLayoutParams(view))
            {
                view = (LayoutParams)generateLayoutParams(view);
                viewholder.itemView.setLayoutParams(view);
            } else
            {
                view = (LayoutParams)view;
            }
            view.mInsetsDirty = true;
            view.mViewHolder = viewholder;
            if(viewholder.itemView.getParent() == null)
                flag = true;
            else
                flag = false;
            view.mPendingInvalidate = flag;
        }

        public void clear()
        {
            mAttachedScrap.clear();
            recycleAndClearCachedViews();
        }

        void clearOldPositions()
        {
            int i = mCachedViews.size();
            for(int k = 0; k < i; k++)
                ((ViewHolder)mCachedViews.get(k)).clearOldPosition();

            i = mAttachedScrap.size();
            for(int l = 0; l < i; l++)
                ((ViewHolder)mAttachedScrap.get(l)).clearOldPosition();

            if(mChangedScrap != null)
            {
                int j = mChangedScrap.size();
                for(int i1 = 0; i1 < j; i1++)
                    ((ViewHolder)mChangedScrap.get(i1)).clearOldPosition();

            }
        }

        void clearScrap()
        {
            mAttachedScrap.clear();
            if(mChangedScrap != null)
                mChangedScrap.clear();
        }

        public int convertPreLayoutPositionToPostLayout(int i)
        {
            if(i < 0 || i >= mState.getItemCount())
                throw new IndexOutOfBoundsException((new StringBuilder()).append("invalid position ").append(i).append(". State ").append("item count is ").append(mState.getItemCount()).toString());
            if(!mState.isPreLayout())
                return i;
            else
                return mAdapterHelper.findPositionOffset(i);
        }

        void dispatchViewRecycled(ViewHolder viewholder)
        {
            if(mRecyclerListener != null)
                mRecyclerListener.onViewRecycled(viewholder);
            if(mAdapter != null)
                mAdapter.onViewRecycled(viewholder);
            if(mState != null)
                mViewInfoStore.removeViewHolder(viewholder);
        }

        ViewHolder getChangedScrapViewForPosition(int i)
        {
            int j;
label0:
            {
                if(mChangedScrap != null)
                {
                    j = mChangedScrap.size();
                    if(j != 0)
                        break label0;
                }
                return null;
            }
            for(int k = 0; k < j; k++)
            {
                ViewHolder viewholder = (ViewHolder)mChangedScrap.get(k);
                if(!viewholder.wasReturnedFromScrap() && viewholder.getLayoutPosition() == i)
                {
                    viewholder.addFlags(32);
                    return viewholder;
                }
            }

            if(mAdapter.hasStableIds())
            {
                i = mAdapterHelper.findPositionOffset(i);
                if(i > 0 && i < mAdapter.getItemCount())
                {
                    long l = mAdapter.getItemId(i);
                    for(i = 0; i < j; i++)
                    {
                        ViewHolder viewholder1 = (ViewHolder)mChangedScrap.get(i);
                        if(!viewholder1.wasReturnedFromScrap() && viewholder1.getItemId() == l)
                        {
                            viewholder1.addFlags(32);
                            return viewholder1;
                        }
                    }

                }
            }
            return null;
        }

        RecycledViewPool getRecycledViewPool()
        {
            if(mRecyclerPool == null)
                mRecyclerPool = new RecycledViewPool();
            return mRecyclerPool;
        }

        int getScrapCount()
        {
            return mAttachedScrap.size();
        }

        public List getScrapList()
        {
            return mUnmodifiableAttachedScrap;
        }

        ViewHolder getScrapOrCachedViewForId(long l, int i, boolean flag)
        {
            for(int j = mAttachedScrap.size() - 1; j >= 0; j--)
            {
                ViewHolder viewholder = (ViewHolder)mAttachedScrap.get(j);
                if(viewholder.getItemId() != l || !(viewholder.wasReturnedFromScrap() ^ true))
                    continue;
                if(i == viewholder.getItemViewType())
                {
                    viewholder.addFlags(32);
                    if(viewholder.isRemoved() && !mState.isPreLayout())
                        viewholder.setFlags(2, 14);
                    return viewholder;
                }
                if(!flag)
                {
                    mAttachedScrap.remove(j);
                    removeDetachedView(viewholder.itemView, false);
                    quickRecycleScrapView(viewholder.itemView);
                }
            }

            for(int k = mCachedViews.size() - 1; k >= 0; k--)
            {
                ViewHolder viewholder1 = (ViewHolder)mCachedViews.get(k);
                if(viewholder1.getItemId() != l)
                    continue;
                if(i == viewholder1.getItemViewType())
                {
                    if(!flag)
                        mCachedViews.remove(k);
                    return viewholder1;
                }
                if(!flag)
                {
                    recycleCachedViewAt(k);
                    return null;
                }
            }

            return null;
        }

        ViewHolder getScrapOrHiddenOrCachedHolderForPosition(int i, boolean flag)
        {
            int j = mAttachedScrap.size();
            for(int k = 0; k < j; k++)
            {
                ViewHolder viewholder = (ViewHolder)mAttachedScrap.get(k);
                if(!viewholder.wasReturnedFromScrap() && viewholder.getLayoutPosition() == i && viewholder.isInvalid() ^ true && (mState.mInPreLayout || viewholder.isRemoved() ^ true))
                {
                    viewholder.addFlags(32);
                    return viewholder;
                }
            }

            if(!flag)
            {
                View view = mChildHelper.findHiddenNonRemovedView(i);
                if(view != null)
                {
                    ViewHolder viewholder1 = RecyclerView.getChildViewHolderInt(view);
                    mChildHelper.unhide(view);
                    i = mChildHelper.indexOfChild(view);
                    if(i == -1)
                    {
                        throw new IllegalStateException((new StringBuilder()).append("layout index should not be -1 after unhiding a view:").append(viewholder1).toString());
                    } else
                    {
                        mChildHelper.detachViewFromParent(i);
                        scrapView(view);
                        viewholder1.addFlags(8224);
                        return viewholder1;
                    }
                }
            }
            j = mCachedViews.size();
            for(int l = 0; l < j; l++)
            {
                ViewHolder viewholder2 = (ViewHolder)mCachedViews.get(l);
                if(!viewholder2.isInvalid() && viewholder2.getLayoutPosition() == i)
                {
                    if(!flag)
                        mCachedViews.remove(l);
                    return viewholder2;
                }
            }

            return null;
        }

        View getScrapViewAt(int i)
        {
            return ((ViewHolder)mAttachedScrap.get(i)).itemView;
        }

        public View getViewForPosition(int i)
        {
            return getViewForPosition(i, false);
        }

        View getViewForPosition(int i, boolean flag)
        {
            return tryGetViewHolderForPositionByDeadline(i, flag, 0x7fffffffffffffffL).itemView;
        }

        void markItemDecorInsetsDirty()
        {
            int i = mCachedViews.size();
            for(int j = 0; j < i; j++)
            {
                LayoutParams layoutparams = (LayoutParams)((ViewHolder)mCachedViews.get(j)).itemView.getLayoutParams();
                if(layoutparams != null)
                    layoutparams.mInsetsDirty = true;
            }

        }

        void markKnownViewsInvalid()
        {
            if(mAdapter != null && mAdapter.hasStableIds())
            {
                int i = mCachedViews.size();
                for(int j = 0; j < i; j++)
                {
                    ViewHolder viewholder = (ViewHolder)mCachedViews.get(j);
                    if(viewholder != null)
                    {
                        viewholder.addFlags(6);
                        viewholder.addChangePayload(null);
                    }
                }

            } else
            {
                recycleAndClearCachedViews();
            }
        }

        void offsetPositionRecordsForInsert(int i, int j)
        {
            int k = mCachedViews.size();
            for(int l = 0; l < k; l++)
            {
                ViewHolder viewholder = (ViewHolder)mCachedViews.get(l);
                if(viewholder != null && viewholder.mPosition >= i)
                    viewholder.offsetPosition(j, true);
            }

        }

        void offsetPositionRecordsForMove(int i, int j)
        {
            int l;
            byte byte0;
            ViewHolder viewholder;
            int k;
            int i1;
            int j1;
            if(i < j)
            {
                k = i;
                l = j;
                byte0 = -1;
            } else
            {
                k = j;
                l = i;
                byte0 = 1;
            }
            i1 = mCachedViews.size();
            j1 = 0;
            if(j1 >= i1)
                break; /* Loop/switch isn't completed */
            viewholder = (ViewHolder)mCachedViews.get(j1);
            if(viewholder != null && viewholder.mPosition >= k && viewholder.mPosition <= l)
                if(viewholder.mPosition == i)
                    viewholder.offsetPosition(j - i, false);
                else
                    viewholder.offsetPosition(byte0, false);
            j1++;
            if(true) goto _L2; else goto _L1
_L2:
            break MISSING_BLOCK_LABEL_25;
_L1:
        }

        void offsetPositionRecordsForRemove(int i, int j, boolean flag)
        {
            int k = mCachedViews.size() - 1;
            while(k >= 0) 
            {
                ViewHolder viewholder = (ViewHolder)mCachedViews.get(k);
                if(viewholder != null)
                    if(viewholder.mPosition >= i + j)
                        viewholder.offsetPosition(-j, flag);
                    else
                    if(viewholder.mPosition >= i)
                    {
                        viewholder.addFlags(8);
                        recycleCachedViewAt(k);
                    }
                k--;
            }
        }

        void onAdapterChanged(Adapter adapter, Adapter adapter1, boolean flag)
        {
            clear();
            getRecycledViewPool().onAdapterChanged(adapter, adapter1, flag);
        }

        void quickRecycleScrapView(View view)
        {
            view = RecyclerView.getChildViewHolderInt(view);
            ViewHolder._2D_set1(view, null);
            ViewHolder._2D_set0(view, false);
            view.clearReturnedFromScrapFlag();
            recycleViewHolderInternal(view);
        }

        void recycleAndClearCachedViews()
        {
            for(int i = mCachedViews.size() - 1; i >= 0; i--)
                recycleCachedViewAt(i);

            mCachedViews.clear();
            if(RecyclerView._2D_get0())
                mPrefetchRegistry.clearPrefetchPositions();
        }

        void recycleCachedViewAt(int i)
        {
            addViewHolderToRecycledViewPool((ViewHolder)mCachedViews.get(i), true);
            mCachedViews.remove(i);
        }

        public void recycleView(View view)
        {
            ViewHolder viewholder;
            viewholder = RecyclerView.getChildViewHolderInt(view);
            if(viewholder.isTmpDetached())
                removeDetachedView(view, false);
            if(!viewholder.isScrap()) goto _L2; else goto _L1
_L1:
            viewholder.unScrap();
_L4:
            recycleViewHolderInternal(viewholder);
            return;
_L2:
            if(viewholder.wasReturnedFromScrap())
                viewholder.clearReturnedFromScrapFlag();
            if(true) goto _L4; else goto _L3
_L3:
        }

        void recycleViewHolderInternal(ViewHolder viewholder)
        {
            int k;
            boolean flag = false;
            if(viewholder.isScrap() || viewholder.itemView.getParent() != null)
            {
                StringBuilder stringbuilder = (new StringBuilder()).append("Scrapped or attached views may not be recycled. isScrap:").append(viewholder.isScrap()).append(" isAttached:");
                if(viewholder.itemView.getParent() != null)
                    flag = true;
                throw new IllegalArgumentException(stringbuilder.append(flag).toString());
            }
            if(viewholder.isTmpDetached())
                throw new IllegalArgumentException((new StringBuilder()).append("Tmp detached view should be removed from RecyclerView before it can be recycled: ").append(viewholder).toString());
            if(viewholder.shouldIgnore())
                throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.");
            boolean flag1 = ViewHolder._2D_wrap0(viewholder);
            int i;
            boolean flag2;
            boolean flag3;
            int j;
            if(mAdapter != null && flag1)
                flag = mAdapter.onFailedToRecycleView(viewholder);
            else
                flag = false;
            i = 0;
            flag2 = false;
            flag3 = false;
            if(flag) goto _L2; else goto _L1
_L1:
            j = ((flag3) ? 1 : 0);
            if(!viewholder.isRecyclable()) goto _L3; else goto _L2
_L2:
            k = ((flag2) ? 1 : 0);
            if(mViewCacheMax <= 0) goto _L5; else goto _L4
_L4:
            k = ((flag2) ? 1 : 0);
            if(!(viewholder.hasAnyOfTheFlags(526) ^ true)) goto _L5; else goto _L6
_L6:
            j = mCachedViews.size();
            k = j;
            if(j >= mViewCacheMax)
            {
                k = j;
                if(j > 0)
                {
                    recycleCachedViewAt(0);
                    k = j - 1;
                }
            }
            j = k;
            i = j;
            if(!RecyclerView._2D_get0()) goto _L8; else goto _L7
_L7:
            i = j;
            if(k <= 0) goto _L8; else goto _L9
_L9:
            i = j;
            if(!(mPrefetchRegistry.lastPrefetchIncludedPosition(viewholder.mPosition) ^ true)) goto _L8; else goto _L10
_L10:
            k--;
_L15:
            if(k < 0) goto _L12; else goto _L11
_L11:
            j = ((ViewHolder)mCachedViews.get(k)).mPosition;
            if(mPrefetchRegistry.lastPrefetchIncludedPosition(j)) goto _L13; else goto _L12
_L12:
            i = k + 1;
_L8:
            mCachedViews.add(i, viewholder);
            k = 1;
_L5:
            i = k;
            j = ((flag3) ? 1 : 0);
            if(k == 0)
            {
                addViewHolderToRecycledViewPool(viewholder, true);
                j = 1;
                i = k;
            }
_L3:
            mViewInfoStore.removeViewHolder(viewholder);
            if(i == 0 && (j ^ 1) != 0 && flag1)
                viewholder.mOwnerRecyclerView = null;
            return;
_L13:
            k--;
            if(true) goto _L15; else goto _L14
_L14:
        }

        void recycleViewInternal(View view)
        {
            recycleViewHolderInternal(RecyclerView.getChildViewHolderInt(view));
        }

        void scrapView(View view)
        {
            view = RecyclerView.getChildViewHolderInt(view);
            if(view.hasAnyOfTheFlags(12) || view.isUpdated() ^ true || canReuseUpdatedViewHolder(view))
            {
                if(view.isInvalid() && view.isRemoved() ^ true && mAdapter.hasStableIds() ^ true)
                    throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.");
                view.setScrapContainer(this, false);
                mAttachedScrap.add(view);
            } else
            {
                if(mChangedScrap == null)
                    mChangedScrap = new ArrayList();
                view.setScrapContainer(this, true);
                mChangedScrap.add(view);
            }
        }

        void setAdapterPositionsAsUnknown()
        {
            int i = mCachedViews.size();
            for(int j = 0; j < i; j++)
            {
                ViewHolder viewholder = (ViewHolder)mCachedViews.get(j);
                if(viewholder != null)
                    viewholder.addFlags(512);
            }

        }

        void setRecycledViewPool(RecycledViewPool recycledviewpool)
        {
            if(mRecyclerPool != null)
                mRecyclerPool.detach();
            mRecyclerPool = recycledviewpool;
            if(recycledviewpool != null)
                mRecyclerPool.attach(getAdapter());
        }

        void setViewCacheExtension(ViewCacheExtension viewcacheextension)
        {
            mViewCacheExtension = viewcacheextension;
        }

        public void setViewCacheSize(int i)
        {
            mRequestedCacheMax = i;
            updateViewCacheSize();
        }

        ViewHolder tryGetViewHolderForPositionByDeadline(int i, boolean flag, long l)
        {
            if(i < 0 || i >= mState.getItemCount())
                throw new IndexOutOfBoundsException((new StringBuilder()).append("Invalid item position ").append(i).append("(").append(i).append("). Item count:").append(mState.getItemCount()).toString());
            boolean flag1 = false;
            ViewHolder viewholder = null;
            Object obj;
            boolean flag2;
            int k;
            if(mState.isPreLayout())
            {
                viewholder = getChangedScrapViewForPosition(i);
                if(viewholder != null)
                    flag1 = true;
                else
                    flag1 = false;
            }
            obj = viewholder;
            flag2 = flag1;
            if(viewholder == null)
            {
                viewholder = getScrapOrHiddenOrCachedHolderForPosition(i, flag);
                obj = viewholder;
                flag2 = flag1;
                if(viewholder != null)
                    if(!validateViewHolderForOffsetPosition(viewholder))
                    {
                        if(!flag)
                        {
                            viewholder.addFlags(4);
                            if(viewholder.isScrap())
                            {
                                removeDetachedView(viewholder.itemView, false);
                                viewholder.unScrap();
                            } else
                            if(viewholder.wasReturnedFromScrap())
                                viewholder.clearReturnedFromScrapFlag();
                            recycleViewHolderInternal(viewholder);
                        }
                        obj = null;
                        flag2 = flag1;
                    } else
                    {
                        flag2 = true;
                        obj = viewholder;
                    }
            }
            viewholder = ((ViewHolder) (obj));
            k = ((flag2) ? 1 : 0);
            if(obj == null)
            {
                k = mAdapterHelper.findPositionOffset(i);
                if(k < 0 || k >= mAdapter.getItemCount())
                    throw new IndexOutOfBoundsException((new StringBuilder()).append("Inconsistency detected. Invalid item position ").append(i).append("(offset:").append(k).append(").").append("state:").append(mState.getItemCount()).toString());
                int i1 = mAdapter.getItemViewType(k);
                viewholder = ((ViewHolder) (obj));
                flag1 = flag2;
                if(mAdapter.hasStableIds())
                {
                    obj = getScrapOrCachedViewForId(mAdapter.getItemId(k), i1, flag);
                    viewholder = ((ViewHolder) (obj));
                    flag1 = flag2;
                    if(obj != null)
                    {
                        obj.mPosition = k;
                        flag1 = true;
                        viewholder = ((ViewHolder) (obj));
                    }
                }
                obj = viewholder;
                if(viewholder == null)
                {
                    obj = viewholder;
                    if(mViewCacheExtension != null)
                    {
                        View view = mViewCacheExtension.getViewForPositionAndType(this, i, i1);
                        obj = viewholder;
                        if(view != null)
                        {
                            viewholder = getChildViewHolder(view);
                            if(viewholder == null)
                                throw new IllegalArgumentException("getViewForPositionAndType returned a view which does not have a ViewHolder");
                            obj = viewholder;
                            if(viewholder.shouldIgnore())
                                throw new IllegalArgumentException("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view.");
                        }
                    }
                }
                ViewHolder viewholder1 = ((ViewHolder) (obj));
                if(obj == null)
                {
                    obj = getRecycledViewPool().getRecycledView(i1);
                    viewholder1 = ((ViewHolder) (obj));
                    if(obj != null)
                    {
                        ((ViewHolder) (obj)).resetInternal();
                        viewholder1 = ((ViewHolder) (obj));
                        if(RecyclerView.FORCE_INVALIDATE_DISPLAY_LIST)
                        {
                            invalidateDisplayListInt(((ViewHolder) (obj)));
                            viewholder1 = ((ViewHolder) (obj));
                        }
                    }
                }
                viewholder = viewholder1;
                k = ((flag1) ? 1 : 0);
                if(viewholder1 == null)
                {
                    long l1 = getNanoTime();
                    if(l != 0x7fffffffffffffffL && mRecyclerPool.willCreateInTime(i1, l1, l) ^ true)
                        return null;
                    viewholder = mAdapter.createViewHolder(RecyclerView.this, i1);
                    if(RecyclerView._2D_get0())
                    {
                        obj = RecyclerView.findNestedRecyclerView(viewholder.itemView);
                        if(obj != null)
                            viewholder.mNestedRecyclerView = new WeakReference(obj);
                    }
                    long l2 = getNanoTime();
                    mRecyclerPool.factorInCreateTime(i1, l2 - l1);
                    k = ((flag1) ? 1 : 0);
                }
            }
            if(k != 0 && mState.isPreLayout() ^ true && viewholder.hasAnyOfTheFlags(8192))
            {
                viewholder.setFlags(0, 8192);
                if(mState.mRunSimpleAnimations)
                {
                    int j = ItemAnimator.buildAdapterChangeFlagsForAnimations(viewholder);
                    obj = mItemAnimator.recordPreLayoutInformation(mState, viewholder, j | 0x1000, viewholder.getUnmodifiedPayloads());
                    recordAnimationInfoIfBouncedHiddenView(viewholder, ((ItemAnimator.ItemHolderInfo) (obj)));
                }
            }
            flag = false;
            if(mState.isPreLayout() && viewholder.isBound())
                viewholder.mPreLayoutPosition = i;
            else
            if(!viewholder.isBound() || viewholder.needsUpdate() || viewholder.isInvalid())
                flag = tryBindViewHolderByDeadline(viewholder, mAdapterHelper.findPositionOffset(i), i, l);
            obj = viewholder.itemView.getLayoutParams();
            if(obj == null)
            {
                obj = (LayoutParams)generateDefaultLayoutParams();
                viewholder.itemView.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
            } else
            if(!checkLayoutParams(((android.view.ViewGroup.LayoutParams) (obj))))
            {
                obj = (LayoutParams)generateLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
                viewholder.itemView.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
            } else
            {
                obj = (LayoutParams)obj;
            }
            obj.mViewHolder = viewholder;
            if(k == 0)
                flag = false;
            obj.mPendingInvalidate = flag;
            return viewholder;
        }

        void unscrapView(ViewHolder viewholder)
        {
            if(ViewHolder._2D_get1(viewholder))
                mChangedScrap.remove(viewholder);
            else
                mAttachedScrap.remove(viewholder);
            ViewHolder._2D_set1(viewholder, null);
            ViewHolder._2D_set0(viewholder, false);
            viewholder.clearReturnedFromScrapFlag();
        }

        void updateViewCacheSize()
        {
            int i;
            if(mLayout != null)
                i = mLayout.mPrefetchMaxCountObserved;
            else
                i = 0;
            mViewCacheMax = mRequestedCacheMax + i;
            for(i = mCachedViews.size() - 1; i >= 0 && mCachedViews.size() > mViewCacheMax; i--)
                recycleCachedViewAt(i);

        }

        boolean validateViewHolderForOffsetPosition(ViewHolder viewholder)
        {
            boolean flag = true;
            if(viewholder.isRemoved())
                return mState.isPreLayout();
            if(viewholder.mPosition < 0 || viewholder.mPosition >= mAdapter.getItemCount())
                throw new IndexOutOfBoundsException((new StringBuilder()).append("Inconsistency detected. Invalid view holder adapter position").append(viewholder).toString());
            if(!mState.isPreLayout() && mAdapter.getItemViewType(viewholder.mPosition) != viewholder.getItemViewType())
                return false;
            if(mAdapter.hasStableIds())
            {
                if(viewholder.getItemId() != mAdapter.getItemId(viewholder.mPosition))
                    flag = false;
                return flag;
            } else
            {
                return true;
            }
        }

        void viewRangeUpdate(int i, int j)
        {
            int k = mCachedViews.size() - 1;
            while(k >= 0) 
            {
                ViewHolder viewholder = (ViewHolder)mCachedViews.get(k);
                if(viewholder != null)
                {
                    int l = viewholder.getLayoutPosition();
                    if(l >= i && l < i + j)
                    {
                        viewholder.addFlags(2);
                        recycleCachedViewAt(k);
                    }
                }
                k--;
            }
        }

        static final int DEFAULT_CACHE_SIZE = 2;
        final ArrayList mAttachedScrap = new ArrayList();
        final ArrayList mCachedViews = new ArrayList();
        ArrayList mChangedScrap;
        RecycledViewPool mRecyclerPool;
        private int mRequestedCacheMax;
        private final List mUnmodifiableAttachedScrap;
        private ViewCacheExtension mViewCacheExtension;
        int mViewCacheMax;
        final RecyclerView this$0;

        public Recycler()
        {
            this$0 = RecyclerView.this;
            super();
            mChangedScrap = null;
            mUnmodifiableAttachedScrap = Collections.unmodifiableList(mAttachedScrap);
            mRequestedCacheMax = 2;
            mViewCacheMax = 2;
        }
    }

    public static interface RecyclerListener
    {

        public abstract void onViewRecycled(ViewHolder viewholder);
    }

    private class RecyclerViewDataObserver extends AdapterDataObserver
    {

        public void onChanged()
        {
            assertNotInLayoutOrScroll(null);
            mState.mStructureChanged = true;
            setDataSetChangedAfterLayout();
            if(!mAdapterHelper.hasPendingUpdates())
                requestLayout();
        }

        public void onItemRangeChanged(int i, int j, Object obj)
        {
            assertNotInLayoutOrScroll(null);
            if(mAdapterHelper.onItemRangeChanged(i, j, obj))
                triggerUpdateProcessor();
        }

        public void onItemRangeInserted(int i, int j)
        {
            assertNotInLayoutOrScroll(null);
            if(mAdapterHelper.onItemRangeInserted(i, j))
                triggerUpdateProcessor();
        }

        public void onItemRangeMoved(int i, int j, int k)
        {
            assertNotInLayoutOrScroll(null);
            if(mAdapterHelper.onItemRangeMoved(i, j, k))
                triggerUpdateProcessor();
        }

        public void onItemRangeRemoved(int i, int j)
        {
            assertNotInLayoutOrScroll(null);
            if(mAdapterHelper.onItemRangeRemoved(i, j))
                triggerUpdateProcessor();
        }

        void triggerUpdateProcessor()
        {
            if(RecyclerView.POST_UPDATES_ON_ANIMATION && mHasFixedSize && mIsAttached)
            {
                postOnAnimation(mUpdateChildViewsRunnable);
            } else
            {
                mAdapterUpdateDuringMeasure = true;
                requestLayout();
            }
        }

        final RecyclerView this$0;

        RecyclerViewDataObserver()
        {
            this$0 = RecyclerView.this;
            super();
        }
    }

    public static class SavedState extends AbsSavedState
    {

        void copyFrom(SavedState savedstate)
        {
            mLayoutState = savedstate.mLayoutState;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(mLayoutState, 0);
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
        Parcelable mLayoutState;


        SavedState(Parcel parcel)
        {
            super(parcel);
            mLayoutState = parcel.readParcelable(com/android/internal/widget/RecyclerView$LayoutManager.getClassLoader());
        }

        SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }

    public static class SimpleOnItemTouchListener
        implements OnItemTouchListener
    {

        public boolean onInterceptTouchEvent(RecyclerView recyclerview, MotionEvent motionevent)
        {
            return false;
        }

        public void onRequestDisallowInterceptTouchEvent(boolean flag)
        {
        }

        public void onTouchEvent(RecyclerView recyclerview, MotionEvent motionevent)
        {
        }

        public SimpleOnItemTouchListener()
        {
        }
    }

    public static abstract class SmoothScroller
    {

        static void _2D_wrap0(SmoothScroller smoothscroller, int i, int j)
        {
            smoothscroller.onAnimation(i, j);
        }

        private void onAnimation(int i, int j)
        {
            RecyclerView recyclerview;
            recyclerview = mRecyclerView;
            break MISSING_BLOCK_LABEL_5;
            if(!mRunning || mTargetPosition == -1 || recyclerview == null)
                stop();
            mPendingInitialRun = false;
            if(mTargetView != null)
                if(getChildPosition(mTargetView) == mTargetPosition)
                {
                    onTargetFound(mTargetView, recyclerview.mState, mRecyclingAction);
                    mRecyclingAction.runIfNecessary(recyclerview);
                    stop();
                } else
                {
                    Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                    mTargetView = null;
                }
            if(mRunning)
            {
                onSeekTargetStep(i, j, recyclerview.mState, mRecyclingAction);
                boolean flag = mRecyclingAction.hasJumpTarget();
                mRecyclingAction.runIfNecessary(recyclerview);
                if(flag)
                    if(mRunning)
                    {
                        mPendingInitialRun = true;
                        recyclerview.mViewFlinger.postOnAnimation();
                    } else
                    {
                        stop();
                    }
            }
            return;
        }

        public View findViewByPosition(int i)
        {
            return mRecyclerView.mLayout.findViewByPosition(i);
        }

        public int getChildCount()
        {
            return mRecyclerView.mLayout.getChildCount();
        }

        public int getChildPosition(View view)
        {
            return mRecyclerView.getChildLayoutPosition(view);
        }

        public LayoutManager getLayoutManager()
        {
            return mLayoutManager;
        }

        public int getTargetPosition()
        {
            return mTargetPosition;
        }

        public void instantScrollToPosition(int i)
        {
            mRecyclerView.scrollToPosition(i);
        }

        public boolean isPendingInitialRun()
        {
            return mPendingInitialRun;
        }

        public boolean isRunning()
        {
            return mRunning;
        }

        protected void normalize(PointF pointf)
        {
            double d = Math.sqrt(pointf.x * pointf.x + pointf.y * pointf.y);
            pointf.x = (float)((double)pointf.x / d);
            pointf.y = (float)((double)pointf.y / d);
        }

        protected void onChildAttachedToWindow(View view)
        {
            if(getChildPosition(view) == getTargetPosition())
                mTargetView = view;
        }

        protected abstract void onSeekTargetStep(int i, int j, State state, Action action);

        protected abstract void onStart();

        protected abstract void onStop();

        protected abstract void onTargetFound(View view, State state, Action action);

        public void setTargetPosition(int i)
        {
            mTargetPosition = i;
        }

        void start(RecyclerView recyclerview, LayoutManager layoutmanager)
        {
            mRecyclerView = recyclerview;
            mLayoutManager = layoutmanager;
            if(mTargetPosition == -1)
            {
                throw new IllegalArgumentException("Invalid target position");
            } else
            {
                State._2D_set0(mRecyclerView.mState, mTargetPosition);
                mRunning = true;
                mPendingInitialRun = true;
                mTargetView = findViewByPosition(getTargetPosition());
                onStart();
                mRecyclerView.mViewFlinger.postOnAnimation();
                return;
            }
        }

        protected final void stop()
        {
            if(!mRunning)
            {
                return;
            } else
            {
                onStop();
                State._2D_set0(mRecyclerView.mState, -1);
                mTargetView = null;
                mTargetPosition = -1;
                mPendingInitialRun = false;
                mRunning = false;
                LayoutManager._2D_wrap0(mLayoutManager, this);
                mLayoutManager = null;
                mRecyclerView = null;
                return;
            }
        }

        private LayoutManager mLayoutManager;
        private boolean mPendingInitialRun;
        private RecyclerView mRecyclerView;
        private final Action mRecyclingAction = new Action(0, 0);
        private boolean mRunning;
        private int mTargetPosition;
        private View mTargetView;

        public SmoothScroller()
        {
            mTargetPosition = -1;
        }
    }

    public static class SmoothScroller.Action
    {

        private void validate()
        {
            if(mInterpolator != null && mDuration < 1)
                throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
            if(mDuration < 1)
                throw new IllegalStateException("Scroll duration must be a positive number");
            else
                return;
        }

        public int getDuration()
        {
            return mDuration;
        }

        public int getDx()
        {
            return mDx;
        }

        public int getDy()
        {
            return mDy;
        }

        public Interpolator getInterpolator()
        {
            return mInterpolator;
        }

        boolean hasJumpTarget()
        {
            boolean flag = false;
            if(mJumpToPosition >= 0)
                flag = true;
            return flag;
        }

        public void jumpTo(int i)
        {
            mJumpToPosition = i;
        }

        void runIfNecessary(RecyclerView recyclerview)
        {
            if(mJumpToPosition >= 0)
            {
                int i = mJumpToPosition;
                mJumpToPosition = -1;
                recyclerview.jumpToPositionForSmoothScroller(i);
                mChanged = false;
                return;
            }
            if(mChanged)
            {
                validate();
                if(mInterpolator == null)
                {
                    if(mDuration == 0x80000000)
                        recyclerview.mViewFlinger.smoothScrollBy(mDx, mDy);
                    else
                        recyclerview.mViewFlinger.smoothScrollBy(mDx, mDy, mDuration);
                } else
                {
                    recyclerview.mViewFlinger.smoothScrollBy(mDx, mDy, mDuration, mInterpolator);
                }
                mConsecutiveUpdates = mConsecutiveUpdates + 1;
                if(mConsecutiveUpdates > 10)
                    Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                mChanged = false;
            } else
            {
                mConsecutiveUpdates = 0;
            }
        }

        public void setDuration(int i)
        {
            mChanged = true;
            mDuration = i;
        }

        public void setDx(int i)
        {
            mChanged = true;
            mDx = i;
        }

        public void setDy(int i)
        {
            mChanged = true;
            mDy = i;
        }

        public void setInterpolator(Interpolator interpolator)
        {
            mChanged = true;
            mInterpolator = interpolator;
        }

        public void update(int i, int j, int k, Interpolator interpolator)
        {
            mDx = i;
            mDy = j;
            mDuration = k;
            mInterpolator = interpolator;
            mChanged = true;
        }

        public static final int UNDEFINED_DURATION = 0x80000000;
        private boolean mChanged;
        private int mConsecutiveUpdates;
        private int mDuration;
        private int mDx;
        private int mDy;
        private Interpolator mInterpolator;
        private int mJumpToPosition;

        public SmoothScroller.Action(int i, int j)
        {
            this(i, j, 0x80000000, null);
        }

        public SmoothScroller.Action(int i, int j, int k)
        {
            this(i, j, k, null);
        }

        public SmoothScroller.Action(int i, int j, int k, Interpolator interpolator)
        {
            mJumpToPosition = -1;
            mChanged = false;
            mConsecutiveUpdates = 0;
            mDx = i;
            mDy = j;
            mDuration = k;
            mInterpolator = interpolator;
        }
    }

    public static interface SmoothScroller.ScrollVectorProvider
    {

        public abstract PointF computeScrollVectorForPosition(int i);
    }

    public static class State
    {

        static int _2D_set0(State state, int i)
        {
            state.mTargetPosition = i;
            return i;
        }

        void assertLayoutStep(int i)
        {
            if((mLayoutStep & i) == 0)
                throw new IllegalStateException((new StringBuilder()).append("Layout state should be one of ").append(Integer.toBinaryString(i)).append(" but it is ").append(Integer.toBinaryString(mLayoutStep)).toString());
            else
                return;
        }

        public boolean didStructureChange()
        {
            return mStructureChanged;
        }

        public Object get(int i)
        {
            if(mData == null)
                return null;
            else
                return mData.get(i);
        }

        public int getItemCount()
        {
            int i;
            if(mInPreLayout)
                i = mPreviousLayoutItemCount - mDeletedInvisibleItemCountSincePreviousLayout;
            else
                i = mItemCount;
            return i;
        }

        public int getTargetScrollPosition()
        {
            return mTargetPosition;
        }

        public boolean hasTargetScrollPosition()
        {
            boolean flag;
            if(mTargetPosition != -1)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean isMeasuring()
        {
            return mIsMeasuring;
        }

        public boolean isPreLayout()
        {
            return mInPreLayout;
        }

        void prepareForNestedPrefetch(Adapter adapter)
        {
            mLayoutStep = 1;
            mItemCount = adapter.getItemCount();
            mStructureChanged = false;
            mInPreLayout = false;
            mTrackOldChangeHolders = false;
            mIsMeasuring = false;
        }

        public void put(int i, Object obj)
        {
            if(mData == null)
                mData = new SparseArray();
            mData.put(i, obj);
        }

        public void remove(int i)
        {
            if(mData == null)
            {
                return;
            } else
            {
                mData.remove(i);
                return;
            }
        }

        State reset()
        {
            mTargetPosition = -1;
            if(mData != null)
                mData.clear();
            mItemCount = 0;
            mStructureChanged = false;
            mIsMeasuring = false;
            return this;
        }

        public String toString()
        {
            return (new StringBuilder()).append("State{mTargetPosition=").append(mTargetPosition).append(", mData=").append(mData).append(", mItemCount=").append(mItemCount).append(", mPreviousLayoutItemCount=").append(mPreviousLayoutItemCount).append(", mDeletedInvisibleItemCountSincePreviousLayout=").append(mDeletedInvisibleItemCountSincePreviousLayout).append(", mStructureChanged=").append(mStructureChanged).append(", mInPreLayout=").append(mInPreLayout).append(", mRunSimpleAnimations=").append(mRunSimpleAnimations).append(", mRunPredictiveAnimations=").append(mRunPredictiveAnimations).append('}').toString();
        }

        public boolean willRunPredictiveAnimations()
        {
            return mRunPredictiveAnimations;
        }

        public boolean willRunSimpleAnimations()
        {
            return mRunSimpleAnimations;
        }

        static final int STEP_ANIMATIONS = 4;
        static final int STEP_LAYOUT = 2;
        static final int STEP_START = 1;
        private SparseArray mData;
        int mDeletedInvisibleItemCountSincePreviousLayout;
        long mFocusedItemId;
        int mFocusedItemPosition;
        int mFocusedSubChildId;
        boolean mInPreLayout;
        boolean mIsMeasuring;
        int mItemCount;
        int mLayoutStep;
        int mPreviousLayoutItemCount;
        boolean mRunPredictiveAnimations;
        boolean mRunSimpleAnimations;
        boolean mStructureChanged;
        private int mTargetPosition;
        boolean mTrackOldChangeHolders;

        public State()
        {
            mTargetPosition = -1;
            mPreviousLayoutItemCount = 0;
            mDeletedInvisibleItemCountSincePreviousLayout = 0;
            mLayoutStep = 1;
            mItemCount = 0;
            mStructureChanged = false;
            mInPreLayout = false;
            mTrackOldChangeHolders = false;
            mIsMeasuring = false;
            mRunSimpleAnimations = false;
            mRunPredictiveAnimations = false;
        }
    }

    public static abstract class ViewCacheExtension
    {

        public abstract View getViewForPositionAndType(Recycler recycler, int i, int j);

        public ViewCacheExtension()
        {
        }
    }

    class ViewFlinger
        implements Runnable
    {

        private int computeScrollDuration(int i, int j, int k, int l)
        {
            int i1 = Math.abs(i);
            int j1 = Math.abs(j);
            boolean flag;
            float f;
            float f1;
            float f2;
            if(i1 > j1)
                flag = true;
            else
                flag = false;
            k = (int)Math.sqrt(k * k + l * l);
            j = (int)Math.sqrt(i * i + j * j);
            if(flag)
                i = getWidth();
            else
                i = getHeight();
            l = i / 2;
            f = Math.min(1.0F, ((float)j * 1.0F) / (float)i);
            f1 = l;
            f2 = l;
            f = distanceInfluenceForSnapDuration(f);
            if(k > 0)
            {
                i = Math.round(Math.abs((f1 + f2 * f) / (float)k) * 1000F) * 4;
            } else
            {
                if(flag)
                    j = i1;
                else
                    j = j1;
                i = (int)(((float)j / (float)i + 1.0F) * 300F);
            }
            return Math.min(i, 2000);
        }

        private void disableRunOnAnimationRequests()
        {
            mReSchedulePostAnimationCallback = false;
            mEatRunOnAnimationRequest = true;
        }

        private float distanceInfluenceForSnapDuration(float f)
        {
            return (float)Math.sin((float)((double)(f - 0.5F) * 0.4712389167638204D));
        }

        private void enableRunOnAnimationRequests()
        {
            mEatRunOnAnimationRequest = false;
            if(mReSchedulePostAnimationCallback)
                postOnAnimation();
        }

        public void fling(int i, int j)
        {
            setScrollState(2);
            mLastFlingY = 0;
            mLastFlingX = 0;
            mScroller.fling(0, 0, i, j, 0x80000000, 0x7fffffff, 0x80000000, 0x7fffffff);
            postOnAnimation();
        }

        void postOnAnimation()
        {
            if(mEatRunOnAnimationRequest)
            {
                mReSchedulePostAnimationCallback = true;
            } else
            {
                removeCallbacks(this);
                RecyclerView.this.postOnAnimation(this);
            }
        }

        public void run()
        {
            if(mLayout == null)
            {
                stop();
                return;
            }
            disableRunOnAnimationRequests();
            consumePendingUpdateOperations();
            OverScroller overscroller = mScroller;
            SmoothScroller smoothscroller = mLayout.mSmoothScroller;
            if(overscroller.computeScrollOffset())
            {
                int k;
                int l;
                int j1;
                int j2;
label0:
                {
                    int i = overscroller.getCurrX();
                    int j = overscroller.getCurrY();
                    k = i - mLastFlingX;
                    l = j - mLastFlingY;
                    int i1 = 0;
                    j1 = 0;
                    int k1 = 0;
                    int l1 = 0;
                    mLastFlingX = i;
                    mLastFlingY = j;
                    int i2 = 0;
                    j2 = 0;
                    int k2 = 0;
                    int l2 = 0;
                    if(mAdapter != null)
                    {
                        eatRequestLayout();
                        onEnterLayoutOrScroll();
                        Trace.beginSection("RV Scroll");
                        if(k != 0)
                        {
                            j1 = mLayout.scrollHorizontallyBy(k, mRecycler, mState);
                            j2 = k - j1;
                        }
                        if(l != 0)
                        {
                            l1 = mLayout.scrollVerticallyBy(l, mRecycler, mState);
                            l2 = l - l1;
                        }
                        Trace.endSection();
                        repositionShadowingViews();
                        onExitLayoutOrScroll();
                        resumeRequestLayout(false);
                        i1 = j1;
                        i2 = j2;
                        k2 = l2;
                        k1 = l1;
                        if(smoothscroller != null)
                        {
                            i1 = j1;
                            i2 = j2;
                            k2 = l2;
                            k1 = l1;
                            if(smoothscroller.isPendingInitialRun() ^ true)
                            {
                                i1 = j1;
                                i2 = j2;
                                k2 = l2;
                                k1 = l1;
                                if(smoothscroller.isRunning())
                                {
                                    i1 = mState.getItemCount();
                                    if(i1 == 0)
                                    {
                                        smoothscroller.stop();
                                        k1 = l1;
                                        k2 = l2;
                                        i2 = j2;
                                        i1 = j1;
                                    } else
                                    if(smoothscroller.getTargetPosition() >= i1)
                                    {
                                        smoothscroller.setTargetPosition(i1 - 1);
                                        SmoothScroller._2D_wrap0(smoothscroller, k - j2, l - l2);
                                        i1 = j1;
                                        i2 = j2;
                                        k2 = l2;
                                        k1 = l1;
                                    } else
                                    {
                                        SmoothScroller._2D_wrap0(smoothscroller, k - j2, l - l2);
                                        i1 = j1;
                                        i2 = j2;
                                        k2 = l2;
                                        k1 = l1;
                                    }
                                }
                            }
                        }
                    }
                    if(!mItemDecorations.isEmpty())
                        invalidate();
                    if(getOverScrollMode() != 2)
                        considerReleasingGlowsOnScroll(k, l);
                    if(i2 == 0 && k2 == 0)
                        break label0;
                    l1 = (int)overscroller.getCurrVelocity();
                    j2 = 0;
                    if(i2 != i)
                        if(i2 < 0)
                            j2 = -l1;
                        else
                        if(i2 > 0)
                            j2 = l1;
                        else
                            j2 = 0;
                    j1 = 0;
                    if(k2 != j)
                        if(k2 < 0)
                            j1 = -l1;
                        else
                        if(k2 > 0)
                            j1 = l1;
                        else
                            j1 = 0;
                    if(getOverScrollMode() != 2)
                        absorbGlows(j2, j1);
                }
                if((j2 != 0 || i2 == i || overscroller.getFinalX() == 0) && (j1 != 0 || k2 == j || overscroller.getFinalY() == 0))
                    overscroller.abortAnimation();
                if(i1 != 0 || k1 != 0)
                    dispatchOnScrolled(i1, k1);
                if(!RecyclerView._2D_wrap0(RecyclerView.this))
                    invalidate();
                if(l != 0 && mLayout.canScrollVertically())
                {
                    if(k1 == l)
                        j2 = 1;
                    else
                        j2 = 0;
                } else
                {
                    j2 = 0;
                }
                if(k != 0 && mLayout.canScrollHorizontally())
                {
                    if(i1 == k)
                        j1 = 1;
                    else
                        j1 = 0;
                } else
                {
                    j1 = 0;
                }
                if(k == 0 && l == 0 || j1 != 0)
                    j2 = 1;
                if(overscroller.isFinished() || (j2 ^ 1) != 0)
                {
                    setScrollState(0);
                    if(RecyclerView._2D_get0())
                        mPrefetchRegistry.clearPrefetchPositions();
                } else
                {
                    postOnAnimation();
                    if(mGapWorker != null)
                        mGapWorker.postFromTraversal(RecyclerView.this, k, l);
                }
            }
            if(smoothscroller != null)
            {
                if(smoothscroller.isPendingInitialRun())
                    SmoothScroller._2D_wrap0(smoothscroller, 0, 0);
                if(!mReSchedulePostAnimationCallback)
                    smoothscroller.stop();
            }
            enableRunOnAnimationRequests();
        }

        public void smoothScrollBy(int i, int j)
        {
            smoothScrollBy(i, j, 0, 0);
        }

        public void smoothScrollBy(int i, int j, int k)
        {
            smoothScrollBy(i, j, k, RecyclerView.sQuinticInterpolator);
        }

        public void smoothScrollBy(int i, int j, int k, int l)
        {
            smoothScrollBy(i, j, computeScrollDuration(i, j, k, l));
        }

        public void smoothScrollBy(int i, int j, int k, Interpolator interpolator)
        {
            if(mInterpolator != interpolator)
            {
                mInterpolator = interpolator;
                mScroller = new OverScroller(getContext(), interpolator);
            }
            setScrollState(2);
            mLastFlingY = 0;
            mLastFlingX = 0;
            mScroller.startScroll(0, 0, i, j, k);
            postOnAnimation();
        }

        public void smoothScrollBy(int i, int j, Interpolator interpolator)
        {
            int k = computeScrollDuration(i, j, 0, 0);
            Interpolator interpolator1 = interpolator;
            if(interpolator == null)
                interpolator1 = RecyclerView.sQuinticInterpolator;
            smoothScrollBy(i, j, k, interpolator1);
        }

        public void stop()
        {
            removeCallbacks(this);
            mScroller.abortAnimation();
        }

        private boolean mEatRunOnAnimationRequest;
        Interpolator mInterpolator;
        private int mLastFlingX;
        private int mLastFlingY;
        private boolean mReSchedulePostAnimationCallback;
        private OverScroller mScroller;
        final RecyclerView this$0;

        ViewFlinger()
        {
            this$0 = RecyclerView.this;
            super();
            mInterpolator = RecyclerView.sQuinticInterpolator;
            mEatRunOnAnimationRequest = false;
            mReSchedulePostAnimationCallback = false;
            mScroller = new OverScroller(getContext(), RecyclerView.sQuinticInterpolator);
        }
    }

    public static abstract class ViewHolder
    {

        static int _2D_get0(ViewHolder viewholder)
        {
            return viewholder.mFlags;
        }

        static boolean _2D_get1(ViewHolder viewholder)
        {
            return viewholder.mInChangeScrap;
        }

        static boolean _2D_set0(ViewHolder viewholder, boolean flag)
        {
            viewholder.mInChangeScrap = flag;
            return flag;
        }

        static Recycler _2D_set1(ViewHolder viewholder, Recycler recycler)
        {
            viewholder.mScrapContainer = recycler;
            return recycler;
        }

        static boolean _2D_wrap0(ViewHolder viewholder)
        {
            return viewholder.doesTransientStatePreventRecycling();
        }

        static boolean _2D_wrap1(ViewHolder viewholder)
        {
            return viewholder.shouldBeKeptAsChild();
        }

        static void _2D_wrap2(ViewHolder viewholder, RecyclerView recyclerview)
        {
            viewholder.onEnteredHiddenState(recyclerview);
        }

        static void _2D_wrap3(ViewHolder viewholder, RecyclerView recyclerview)
        {
            viewholder.onLeftHiddenState(recyclerview);
        }

        private void createPayloadsIfNeeded()
        {
            if(mPayloads == null)
            {
                mPayloads = new ArrayList();
                mUnmodifiedPayloads = Collections.unmodifiableList(mPayloads);
            }
        }

        private boolean doesTransientStatePreventRecycling()
        {
            boolean flag = false;
            if((mFlags & 0x10) == 0)
                flag = itemView.hasTransientState();
            return flag;
        }

        private void onEnteredHiddenState(RecyclerView recyclerview)
        {
            mWasImportantForAccessibilityBeforeHidden = itemView.getImportantForAccessibility();
            recyclerview.setChildImportantForAccessibilityInternal(this, 4);
        }

        private void onLeftHiddenState(RecyclerView recyclerview)
        {
            recyclerview.setChildImportantForAccessibilityInternal(this, mWasImportantForAccessibilityBeforeHidden);
            mWasImportantForAccessibilityBeforeHidden = 0;
        }

        private boolean shouldBeKeptAsChild()
        {
            boolean flag = false;
            if((mFlags & 0x10) != 0)
                flag = true;
            return flag;
        }

        void addChangePayload(Object obj)
        {
            if(obj != null) goto _L2; else goto _L1
_L1:
            addFlags(1024);
_L4:
            return;
_L2:
            if((mFlags & 0x400) == 0)
            {
                createPayloadsIfNeeded();
                mPayloads.add(obj);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        void addFlags(int i)
        {
            mFlags = mFlags | i;
        }

        void clearOldPosition()
        {
            mOldPosition = -1;
            mPreLayoutPosition = -1;
        }

        void clearPayload()
        {
            if(mPayloads != null)
                mPayloads.clear();
            mFlags = mFlags & 0xfffffbff;
        }

        void clearReturnedFromScrapFlag()
        {
            mFlags = mFlags & 0xffffffdf;
        }

        void clearTmpDetachFlag()
        {
            mFlags = mFlags & 0xfffffeff;
        }

        void flagRemovedAndOffsetPosition(int i, int j, boolean flag)
        {
            addFlags(8);
            offsetPosition(j, flag);
            mPosition = i;
        }

        public final int getAdapterPosition()
        {
            if(mOwnerRecyclerView == null)
                return -1;
            else
                return mOwnerRecyclerView.getAdapterPositionFor(this);
        }

        public final long getItemId()
        {
            return mItemId;
        }

        public final int getItemViewType()
        {
            return mItemViewType;
        }

        public final int getLayoutPosition()
        {
            int i;
            if(mPreLayoutPosition == -1)
                i = mPosition;
            else
                i = mPreLayoutPosition;
            return i;
        }

        public final int getOldPosition()
        {
            return mOldPosition;
        }

        public final int getPosition()
        {
            int i;
            if(mPreLayoutPosition == -1)
                i = mPosition;
            else
                i = mPreLayoutPosition;
            return i;
        }

        List getUnmodifiedPayloads()
        {
            if((mFlags & 0x400) == 0)
            {
                if(mPayloads == null || mPayloads.size() == 0)
                    return FULLUPDATE_PAYLOADS;
                else
                    return mUnmodifiedPayloads;
            } else
            {
                return FULLUPDATE_PAYLOADS;
            }
        }

        boolean hasAnyOfTheFlags(int i)
        {
            boolean flag = false;
            if((mFlags & i) != 0)
                flag = true;
            return flag;
        }

        boolean isAdapterPositionUnknown()
        {
            boolean flag;
            if((mFlags & 0x200) == 0)
                flag = isInvalid();
            else
                flag = true;
            return flag;
        }

        boolean isBound()
        {
            boolean flag = false;
            if((mFlags & 1) != 0)
                flag = true;
            return flag;
        }

        boolean isInvalid()
        {
            boolean flag = false;
            if((mFlags & 4) != 0)
                flag = true;
            return flag;
        }

        public final boolean isRecyclable()
        {
            boolean flag = false;
            if((mFlags & 0x10) == 0)
                flag = itemView.hasTransientState() ^ true;
            return flag;
        }

        boolean isRemoved()
        {
            boolean flag = false;
            if((mFlags & 8) != 0)
                flag = true;
            return flag;
        }

        boolean isScrap()
        {
            boolean flag;
            if(mScrapContainer != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        boolean isTmpDetached()
        {
            boolean flag = false;
            if((mFlags & 0x100) != 0)
                flag = true;
            return flag;
        }

        boolean isUpdated()
        {
            boolean flag = false;
            if((mFlags & 2) != 0)
                flag = true;
            return flag;
        }

        boolean needsUpdate()
        {
            boolean flag = false;
            if((mFlags & 2) != 0)
                flag = true;
            return flag;
        }

        void offsetPosition(int i, boolean flag)
        {
            if(mOldPosition == -1)
                mOldPosition = mPosition;
            if(mPreLayoutPosition == -1)
                mPreLayoutPosition = mPosition;
            if(flag)
                mPreLayoutPosition = mPreLayoutPosition + i;
            mPosition = mPosition + i;
            if(itemView.getLayoutParams() != null)
                ((LayoutParams)itemView.getLayoutParams()).mInsetsDirty = true;
        }

        void resetInternal()
        {
            mFlags = 0;
            mPosition = -1;
            mOldPosition = -1;
            mItemId = -1L;
            mPreLayoutPosition = -1;
            mIsRecyclableCount = 0;
            mShadowedHolder = null;
            mShadowingHolder = null;
            clearPayload();
            mWasImportantForAccessibilityBeforeHidden = 0;
            mPendingAccessibilityState = -1;
            RecyclerView.clearNestedRecyclerViewIfNotNested(this);
        }

        void saveOldPosition()
        {
            if(mOldPosition == -1)
                mOldPosition = mPosition;
        }

        void setFlags(int i, int j)
        {
            mFlags = mFlags & j | i & j;
        }

        public final void setIsRecyclable(boolean flag)
        {
            int i;
            if(flag)
                i = mIsRecyclableCount - 1;
            else
                i = mIsRecyclableCount + 1;
            mIsRecyclableCount = i;
            if(mIsRecyclableCount >= 0) goto _L2; else goto _L1
_L1:
            mIsRecyclableCount = 0;
            Log.e("View", (new StringBuilder()).append("isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for ").append(this).toString());
_L4:
            return;
_L2:
            if(!flag && mIsRecyclableCount == 1)
                mFlags = mFlags | 0x10;
            else
            if(flag && mIsRecyclableCount == 0)
                mFlags = mFlags & 0xffffffef;
            if(true) goto _L4; else goto _L3
_L3:
        }

        void setScrapContainer(Recycler recycler, boolean flag)
        {
            mScrapContainer = recycler;
            mInChangeScrap = flag;
        }

        boolean shouldIgnore()
        {
            boolean flag = false;
            if((mFlags & 0x80) != 0)
                flag = true;
            return flag;
        }

        void stopIgnoring()
        {
            mFlags = mFlags & 0xffffff7f;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder((new StringBuilder()).append("ViewHolder{").append(Integer.toHexString(hashCode())).append(" position=").append(mPosition).append(" id=").append(mItemId).append(", oldPos=").append(mOldPosition).append(", pLpos:").append(mPreLayoutPosition).toString());
            if(isScrap())
            {
                StringBuilder stringbuilder1 = stringbuilder.append(" scrap ");
                String s;
                if(mInChangeScrap)
                    s = "[changeScrap]";
                else
                    s = "[attachedScrap]";
                stringbuilder1.append(s);
            }
            if(isInvalid())
                stringbuilder.append(" invalid");
            if(!isBound())
                stringbuilder.append(" unbound");
            if(needsUpdate())
                stringbuilder.append(" update");
            if(isRemoved())
                stringbuilder.append(" removed");
            if(shouldIgnore())
                stringbuilder.append(" ignored");
            if(isTmpDetached())
                stringbuilder.append(" tmpDetached");
            if(!isRecyclable())
                stringbuilder.append(" not recyclable(").append(mIsRecyclableCount).append(")");
            if(isAdapterPositionUnknown())
                stringbuilder.append(" undefined adapter position");
            if(itemView.getParent() == null)
                stringbuilder.append(" no parent");
            stringbuilder.append("}");
            return stringbuilder.toString();
        }

        void unScrap()
        {
            mScrapContainer.unscrapView(this);
        }

        boolean wasReturnedFromScrap()
        {
            boolean flag = false;
            if((mFlags & 0x20) != 0)
                flag = true;
            return flag;
        }

        static final int FLAG_ADAPTER_FULLUPDATE = 1024;
        static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
        static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
        static final int FLAG_BOUND = 1;
        static final int FLAG_IGNORE = 128;
        static final int FLAG_INVALID = 4;
        static final int FLAG_MOVED = 2048;
        static final int FLAG_NOT_RECYCLABLE = 16;
        static final int FLAG_REMOVED = 8;
        static final int FLAG_RETURNED_FROM_SCRAP = 32;
        static final int FLAG_TMP_DETACHED = 256;
        static final int FLAG_UPDATE = 2;
        private static final List FULLUPDATE_PAYLOADS;
        static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
        public final View itemView;
        private int mFlags;
        private boolean mInChangeScrap;
        private int mIsRecyclableCount;
        long mItemId;
        int mItemViewType;
        WeakReference mNestedRecyclerView;
        int mOldPosition;
        RecyclerView mOwnerRecyclerView;
        List mPayloads;
        int mPendingAccessibilityState;
        int mPosition;
        int mPreLayoutPosition;
        private Recycler mScrapContainer;
        ViewHolder mShadowedHolder;
        ViewHolder mShadowingHolder;
        List mUnmodifiedPayloads;
        private int mWasImportantForAccessibilityBeforeHidden;

        static 
        {
            FULLUPDATE_PAYLOADS = Collections.EMPTY_LIST;
        }

        public ViewHolder(View view)
        {
            mPosition = -1;
            mOldPosition = -1;
            mItemId = -1L;
            mItemViewType = -1;
            mPreLayoutPosition = -1;
            mShadowedHolder = null;
            mShadowingHolder = null;
            mPayloads = null;
            mUnmodifiedPayloads = null;
            mIsRecyclableCount = 0;
            mScrapContainer = null;
            mInChangeScrap = false;
            mWasImportantForAccessibilityBeforeHidden = 0;
            mPendingAccessibilityState = -1;
            if(view == null)
            {
                throw new IllegalArgumentException("itemView may not be null");
            } else
            {
                itemView = view;
                return;
            }
        }
    }


    static boolean _2D_get0()
    {
        return ALLOW_THREAD_GAP_WORK;
    }

    static boolean _2D_wrap0(RecyclerView recyclerview)
    {
        return recyclerview.awakenScrollBars();
    }

    static void _2D_wrap1(RecyclerView recyclerview, View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        recyclerview.attachViewToParent(view, i, layoutparams);
    }

    static void _2D_wrap2(RecyclerView recyclerview, int i)
    {
        recyclerview.detachViewFromParent(i);
    }

    static void _2D_wrap3(RecyclerView recyclerview, int i, int j)
    {
        recyclerview.setMeasuredDimension(i, j);
    }

    public RecyclerView(Context context)
    {
        this(context, null);
    }

    public RecyclerView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public RecyclerView(Context context, AttributeSet attributeset, int i)
    {
        Object obj = null;
        super(context, attributeset, i);
        mObserver = new RecyclerViewDataObserver();
        mRecycler = new Recycler();
        mViewInfoStore = new ViewInfoStore();
        mUpdateChildViewsRunnable = new Runnable() {

            public void run()
            {
                if(!mFirstLayoutComplete || isLayoutRequested())
                    return;
                if(!mIsAttached)
                {
                    requestLayout();
                    return;
                }
                if(mLayoutFrozen)
                {
                    mLayoutRequestEaten = true;
                    return;
                } else
                {
                    consumePendingUpdateOperations();
                    return;
                }
            }

            final RecyclerView this$0;

            
            {
                this$0 = RecyclerView.this;
                super();
            }
        }
;
        mTempRect = new Rect();
        mTempRect2 = new Rect();
        mTempRectF = new RectF();
        mItemDecorations = new ArrayList();
        mOnItemTouchListeners = new ArrayList();
        mEatRequestLayout = 0;
        mDataSetHasChangedAfterLayout = false;
        mLayoutOrScrollCounter = 0;
        mDispatchScrollCounter = 0;
        mItemAnimator = new DefaultItemAnimator();
        mScrollState = 0;
        mScrollPointerId = -1;
        mScrollFactor = 1.401298E-045F;
        mPreserveFocusAfterLayout = true;
        mViewFlinger = new ViewFlinger();
        if(ALLOW_THREAD_GAP_WORK)
            obj = new GapWorker.LayoutPrefetchRegistryImpl();
        mPrefetchRegistry = ((GapWorker.LayoutPrefetchRegistryImpl) (obj));
        mState = new State();
        mItemsAddedOrRemoved = false;
        mItemsChanged = false;
        mItemAnimatorListener = new ItemAnimatorRestoreListener();
        mPostedAnimatorRunner = false;
        mMinMaxLayoutPositions = new int[2];
        mScrollOffset = new int[2];
        mScrollConsumed = new int[2];
        mNestedOffsets = new int[2];
        mPendingAccessibilityImportanceChange = new ArrayList();
        mViewInfoProcessCallback = new ViewInfoStore.ProcessCallback() {

            public void processAppeared(ViewHolder viewholder, ItemAnimator.ItemHolderInfo itemholderinfo, ItemAnimator.ItemHolderInfo itemholderinfo1)
            {
                animateAppearance(viewholder, itemholderinfo, itemholderinfo1);
            }

            public void processDisappeared(ViewHolder viewholder, ItemAnimator.ItemHolderInfo itemholderinfo, ItemAnimator.ItemHolderInfo itemholderinfo1)
            {
                mRecycler.unscrapView(viewholder);
                animateDisappearance(viewholder, itemholderinfo, itemholderinfo1);
            }

            public void processPersistent(ViewHolder viewholder, ItemAnimator.ItemHolderInfo itemholderinfo, ItemAnimator.ItemHolderInfo itemholderinfo1)
            {
                viewholder.setIsRecyclable(false);
                if(!mDataSetHasChangedAfterLayout) goto _L2; else goto _L1
_L1:
                if(mItemAnimator.animateChange(viewholder, viewholder, itemholderinfo, itemholderinfo1))
                    postAnimationRunner();
_L4:
                return;
_L2:
                if(mItemAnimator.animatePersistence(viewholder, itemholderinfo, itemholderinfo1))
                    postAnimationRunner();
                if(true) goto _L4; else goto _L3
_L3:
            }

            public void unused(ViewHolder viewholder)
            {
                mLayout.removeAndRecycleView(viewholder.itemView, mRecycler);
            }

            final RecyclerView this$0;

            
            {
                this$0 = RecyclerView.this;
                super();
            }
        }
;
        boolean flag;
        if(attributeset != null)
        {
            obj = context.obtainStyledAttributes(attributeset, CLIP_TO_PADDING_ATTR, i, 0);
            mClipToPadding = ((TypedArray) (obj)).getBoolean(0, true);
            ((TypedArray) (obj)).recycle();
        } else
        {
            mClipToPadding = true;
        }
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        obj = ViewConfiguration.get(context);
        mTouchSlop = ((ViewConfiguration) (obj)).getScaledTouchSlop();
        mMinFlingVelocity = ((ViewConfiguration) (obj)).getScaledMinimumFlingVelocity();
        mMaxFlingVelocity = ((ViewConfiguration) (obj)).getScaledMaximumFlingVelocity();
        if(getOverScrollMode() == 2)
            flag = true;
        else
            flag = false;
        setWillNotDraw(flag);
        mItemAnimator.setListener(mItemAnimatorListener);
        initAdapterManager();
        initChildrenHelper();
        if(getImportantForAccessibility() == 0)
            setImportantForAccessibility(1);
        mAccessibilityManager = (AccessibilityManager)getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
        flag = true;
        if(attributeset != null)
        {
            TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.RecyclerView, i, 0);
            String s = typedarray.getString(2);
            if(typedarray.getInt(1, -1) == -1)
                setDescendantFocusability(0x40000);
            typedarray.recycle();
            createLayoutManager(context, s, attributeset, i, 0);
            if(android.os.Build.VERSION.SDK_INT >= 21)
            {
                context = context.obtainStyledAttributes(attributeset, NESTED_SCROLLING_ATTRS, i, 0);
                flag = context.getBoolean(0, true);
                context.recycle();
            }
        } else
        {
            setDescendantFocusability(0x40000);
        }
        setNestedScrollingEnabled(flag);
    }

    private void addAnimatingView(ViewHolder viewholder)
    {
        View view = viewholder.itemView;
        boolean flag;
        if(view.getParent() == this)
            flag = true;
        else
            flag = false;
        mRecycler.unscrapView(getChildViewHolder(view));
        if(viewholder.isTmpDetached())
            mChildHelper.attachViewToParent(view, -1, view.getLayoutParams(), true);
        else
        if(!flag)
            mChildHelper.addView(view, true);
        else
            mChildHelper.hide(view);
    }

    private void animateChange(ViewHolder viewholder, ViewHolder viewholder1, ItemAnimator.ItemHolderInfo itemholderinfo, ItemAnimator.ItemHolderInfo itemholderinfo1, boolean flag, boolean flag1)
    {
        viewholder.setIsRecyclable(false);
        if(flag)
            addAnimatingView(viewholder);
        if(viewholder != viewholder1)
        {
            if(flag1)
                addAnimatingView(viewholder1);
            viewholder.mShadowedHolder = viewholder1;
            addAnimatingView(viewholder);
            mRecycler.unscrapView(viewholder);
            viewholder1.setIsRecyclable(false);
            viewholder1.mShadowingHolder = viewholder;
        }
        if(mItemAnimator.animateChange(viewholder, viewholder1, itemholderinfo, itemholderinfo1))
            postAnimationRunner();
    }

    private void cancelTouch()
    {
        resetTouch();
        setScrollState(0);
    }

    static void clearNestedRecyclerViewIfNotNested(ViewHolder viewholder)
    {
        if(viewholder.mNestedRecyclerView != null)
        {
            for(Object obj = (View)viewholder.mNestedRecyclerView.get(); obj != null;)
            {
                if(obj == viewholder.itemView)
                    return;
                obj = ((View) (obj)).getParent();
                if(obj instanceof View)
                    obj = (View)obj;
                else
                    obj = null;
            }

            viewholder.mNestedRecyclerView = null;
        }
    }

    private void createLayoutManager(Context context, String s, AttributeSet attributeset, int i, int j)
    {
        if(s == null) goto _L2; else goto _L1
_L1:
        s = s.trim();
        if(s.length() == 0) goto _L2; else goto _L3
_L3:
        String s1 = getFullClassName(context, s);
        if(!isInEditMode()) goto _L5; else goto _L4
_L4:
        s = getClass().getClassLoader();
_L6:
        Class class1 = s.loadClass(s1).asSubclass(com/android/internal/widget/RecyclerView$LayoutManager);
        Object obj = null;
        s = class1.getConstructor(LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
        context = ((Context) (new Object[] {
            context, attributeset, Integer.valueOf(i), Integer.valueOf(j)
        }));
_L7:
        s.setAccessible(true);
        setLayoutManager((LayoutManager)s.newInstance(context));
_L2:
        return;
_L5:
        try
        {
            s = context.getClassLoader();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new IllegalStateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Unable to find LayoutManager ").append(s1).toString(), context);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new IllegalStateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Could not instantiate the LayoutManager: ").append(s1).toString(), context);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new IllegalStateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Could not instantiate the LayoutManager: ").append(s1).toString(), context);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new IllegalStateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Cannot access non-public constructor ").append(s1).toString(), context);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new IllegalStateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Class is not a LayoutManager ").append(s1).toString(), context);
        }
          goto _L6
        context;
        s = class1.getConstructor(new Class[0]);
        context = obj;
          goto _L7
        s;
        s.initCause(context);
        context = JVM INSTR new #725 <Class IllegalStateException>;
        StringBuilder stringbuilder = JVM INSTR new #727 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        context.IllegalStateException(stringbuilder.append(attributeset.getPositionDescription()).append(": Error creating LayoutManager ").append(s1).toString(), s);
        throw context;
          goto _L6
    }

    private boolean didChildRangeChange(int i, int j)
    {
        boolean flag = true;
        findMinMaxChildLayoutPositions(mMinMaxLayoutPositions);
        boolean flag1 = flag;
        if(mMinMaxLayoutPositions[0] == i)
            if(mMinMaxLayoutPositions[1] != j)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    private void dispatchContentChangedIfNecessary()
    {
        int i = mEatenAccessibilityChangeFlags;
        mEatenAccessibilityChangeFlags = 0;
        if(i != 0 && isAccessibilityEnabled())
        {
            AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain();
            accessibilityevent.setEventType(2048);
            accessibilityevent.setContentChangeTypes(i);
            sendAccessibilityEventUnchecked(accessibilityevent);
        }
    }

    private void dispatchLayoutStep1()
    {
        mState.assertLayoutStep(1);
        mState.mIsMeasuring = false;
        eatRequestLayout();
        mViewInfoStore.clear();
        onEnterLayoutOrScroll();
        processAdapterUpdatesAndSetAnimationFlags();
        saveFocusInfo();
        State state = mState;
        boolean flag;
        if(mState.mRunSimpleAnimations)
            flag = mItemsChanged;
        else
            flag = false;
        state.mTrackOldChangeHolders = flag;
        mItemsChanged = false;
        mItemsAddedOrRemoved = false;
        mState.mInPreLayout = mState.mRunPredictiveAnimations;
        mState.mItemCount = mAdapter.getItemCount();
        findMinMaxChildLayoutPositions(mMinMaxLayoutPositions);
        if(mState.mRunSimpleAnimations)
        {
            int i = mChildHelper.getChildCount();
            int k = 0;
            while(k < i) 
            {
                ViewHolder viewholder1 = getChildViewHolderInt(mChildHelper.getChildAt(k));
                if(!viewholder1.shouldIgnore() && (!viewholder1.isInvalid() || !(mAdapter.hasStableIds() ^ true)))
                {
                    ItemAnimator.ItemHolderInfo itemholderinfo = mItemAnimator.recordPreLayoutInformation(mState, viewholder1, ItemAnimator.buildAdapterChangeFlagsForAnimations(viewholder1), viewholder1.getUnmodifiedPayloads());
                    mViewInfoStore.addToPreLayout(viewholder1, itemholderinfo);
                    if(mState.mTrackOldChangeHolders && viewholder1.isUpdated() && viewholder1.isRemoved() ^ true && viewholder1.shouldIgnore() ^ true && viewholder1.isInvalid() ^ true)
                    {
                        long l1 = getChangedHolderKey(viewholder1);
                        mViewInfoStore.addToOldChangeHolders(l1, viewholder1);
                    }
                }
                k++;
            }
        }
        if(!mState.mRunPredictiveAnimations) goto _L2; else goto _L1
_L1:
        saveOldPositions();
        boolean flag1 = mState.mStructureChanged;
        mState.mStructureChanged = false;
        mLayout.onLayoutChildren(mRecycler, mState);
        mState.mStructureChanged = flag1;
        int l = 0;
        do
        {
            if(l >= mChildHelper.getChildCount())
                break;
            ViewHolder viewholder = getChildViewHolderInt(mChildHelper.getChildAt(l));
            if(!viewholder.shouldIgnore() && !mViewInfoStore.isInPreLayout(viewholder))
            {
                int i1 = ItemAnimator.buildAdapterChangeFlagsForAnimations(viewholder);
                boolean flag2 = viewholder.hasAnyOfTheFlags(8192);
                int j = i1;
                if(!flag2)
                    j = i1 | 0x1000;
                ItemAnimator.ItemHolderInfo itemholderinfo1 = mItemAnimator.recordPreLayoutInformation(mState, viewholder, j, viewholder.getUnmodifiedPayloads());
                if(flag2)
                    recordAnimationInfoIfBouncedHiddenView(viewholder, itemholderinfo1);
                else
                    mViewInfoStore.addToAppearedInPreLayoutHolders(viewholder, itemholderinfo1);
            }
            l++;
        } while(true);
        clearOldPositions();
_L4:
        onExitLayoutOrScroll();
        resumeRequestLayout(false);
        mState.mLayoutStep = 2;
        return;
_L2:
        clearOldPositions();
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void dispatchLayoutStep2()
    {
        eatRequestLayout();
        onEnterLayoutOrScroll();
        mState.assertLayoutStep(6);
        mAdapterHelper.consumeUpdatesInOnePass();
        mState.mItemCount = mAdapter.getItemCount();
        mState.mDeletedInvisibleItemCountSincePreviousLayout = 0;
        mState.mInPreLayout = false;
        mLayout.onLayoutChildren(mRecycler, mState);
        mState.mStructureChanged = false;
        mPendingSavedState = null;
        State state = mState;
        boolean flag;
        if(mState.mRunSimpleAnimations && mItemAnimator != null)
            flag = true;
        else
            flag = false;
        state.mRunSimpleAnimations = flag;
        mState.mLayoutStep = 4;
        onExitLayoutOrScroll();
        resumeRequestLayout(false);
    }

    private void dispatchLayoutStep3()
    {
        mState.assertLayoutStep(4);
        eatRequestLayout();
        onEnterLayoutOrScroll();
        mState.mLayoutStep = 1;
        if(mState.mRunSimpleAnimations)
        {
            int i = mChildHelper.getChildCount() - 1;
            while(i >= 0) 
            {
                ViewHolder viewholder = getChildViewHolderInt(mChildHelper.getChildAt(i));
                if(!viewholder.shouldIgnore())
                {
                    long l = getChangedHolderKey(viewholder);
                    ItemAnimator.ItemHolderInfo itemholderinfo = mItemAnimator.recordPostLayoutInformation(mState, viewholder);
                    ViewHolder viewholder1 = mViewInfoStore.getFromOldChangeHolders(l);
                    if(viewholder1 != null && viewholder1.shouldIgnore() ^ true)
                    {
                        boolean flag = mViewInfoStore.isDisappearing(viewholder1);
                        boolean flag1 = mViewInfoStore.isDisappearing(viewholder);
                        if(flag && viewholder1 == viewholder)
                        {
                            mViewInfoStore.addToPostLayout(viewholder, itemholderinfo);
                        } else
                        {
                            ItemAnimator.ItemHolderInfo itemholderinfo1 = mViewInfoStore.popFromPreLayout(viewholder1);
                            mViewInfoStore.addToPostLayout(viewholder, itemholderinfo);
                            itemholderinfo = mViewInfoStore.popFromPostLayout(viewholder);
                            if(itemholderinfo1 == null)
                                handleMissingPreInfoForChangeError(l, viewholder, viewholder1);
                            else
                                animateChange(viewholder1, viewholder, itemholderinfo1, itemholderinfo, flag, flag1);
                        }
                    } else
                    {
                        mViewInfoStore.addToPostLayout(viewholder, itemholderinfo);
                    }
                }
                i--;
            }
            mViewInfoStore.process(mViewInfoProcessCallback);
        }
        mLayout.removeAndRecycleScrapInt(mRecycler);
        mState.mPreviousLayoutItemCount = mState.mItemCount;
        mDataSetHasChangedAfterLayout = false;
        mState.mRunSimpleAnimations = false;
        mState.mRunPredictiveAnimations = false;
        mLayout.mRequestedSimpleAnimations = false;
        if(mRecycler.mChangedScrap != null)
            mRecycler.mChangedScrap.clear();
        if(mLayout.mPrefetchMaxObservedInInitialPrefetch)
        {
            mLayout.mPrefetchMaxCountObserved = 0;
            mLayout.mPrefetchMaxObservedInInitialPrefetch = false;
            mRecycler.updateViewCacheSize();
        }
        mLayout.onLayoutCompleted(mState);
        onExitLayoutOrScroll();
        resumeRequestLayout(false);
        mViewInfoStore.clear();
        if(didChildRangeChange(mMinMaxLayoutPositions[0], mMinMaxLayoutPositions[1]))
            dispatchOnScrolled(0, 0);
        recoverFocusFromState();
        resetFocusInfo();
    }

    private boolean dispatchOnItemTouch(MotionEvent motionevent)
    {
        int i = motionevent.getAction();
        if(mActiveOnItemTouchListener == null) goto _L2; else goto _L1
_L1:
        if(i != 0) goto _L4; else goto _L3
_L3:
        mActiveOnItemTouchListener = null;
_L2:
        if(i == 0) goto _L6; else goto _L5
_L5:
        int j;
        j = mOnItemTouchListeners.size();
        i = 0;
_L7:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        OnItemTouchListener onitemtouchlistener = (OnItemTouchListener)mOnItemTouchListeners.get(i);
        if(onitemtouchlistener.onInterceptTouchEvent(this, motionevent))
        {
            mActiveOnItemTouchListener = onitemtouchlistener;
            return true;
        }
        i++;
        continue; /* Loop/switch isn't completed */
_L4:
        mActiveOnItemTouchListener.onTouchEvent(this, motionevent);
        if(i == 3 || i == 1)
            mActiveOnItemTouchListener = null;
        return true;
        if(true) goto _L7; else goto _L6
_L6:
        return false;
    }

    private boolean dispatchOnItemTouchIntercept(MotionEvent motionevent)
    {
        int i = motionevent.getAction();
        if(i == 3 || i == 0)
            mActiveOnItemTouchListener = null;
        int j = mOnItemTouchListeners.size();
        for(int k = 0; k < j; k++)
        {
            OnItemTouchListener onitemtouchlistener = (OnItemTouchListener)mOnItemTouchListeners.get(k);
            if(onitemtouchlistener.onInterceptTouchEvent(this, motionevent) && i != 3)
            {
                mActiveOnItemTouchListener = onitemtouchlistener;
                return true;
            }
        }

        return false;
    }

    private void findMinMaxChildLayoutPositions(int ai[])
    {
        int i = mChildHelper.getChildCount();
        if(i == 0)
        {
            ai[0] = -1;
            ai[1] = -1;
            return;
        }
        int j = 0x7fffffff;
        int k = 0x80000000;
        int l = 0;
        while(l < i) 
        {
            ViewHolder viewholder = getChildViewHolderInt(mChildHelper.getChildAt(l));
            int i1;
            if(viewholder.shouldIgnore())
            {
                i1 = k;
            } else
            {
                int j1 = viewholder.getLayoutPosition();
                int k1 = j;
                if(j1 < j)
                    k1 = j1;
                i1 = k;
                j = k1;
                if(j1 > k)
                {
                    i1 = j1;
                    j = k1;
                }
            }
            l++;
            k = i1;
        }
        ai[0] = j;
        ai[1] = k;
    }

    static RecyclerView findNestedRecyclerView(View view)
    {
        if(!(view instanceof ViewGroup))
            return null;
        if(view instanceof RecyclerView)
            return (RecyclerView)view;
        ViewGroup viewgroup = (ViewGroup)view;
        int i = viewgroup.getChildCount();
        for(int j = 0; j < i; j++)
        {
            view = findNestedRecyclerView(viewgroup.getChildAt(j));
            if(view != null)
                return view;
        }

        return null;
    }

    private View findNextViewToFocus()
    {
        int i;
        int k;
        ViewHolder viewholder;
        int j;
        if(mState.mFocusedItemPosition != -1)
            i = mState.mFocusedItemPosition;
        else
            i = 0;
        j = mState.getItemCount();
        k = i;
_L7:
        if(k >= j) goto _L2; else goto _L1
_L1:
        viewholder = findViewHolderForAdapterPosition(k);
        if(viewholder != null) goto _L3; else goto _L2
_L2:
        i = Math.min(j, i) - 1;
_L5:
        if(i < 0)
            break; /* Loop/switch isn't completed */
        viewholder = findViewHolderForAdapterPosition(i);
        if(viewholder == null)
            return null;
        if(viewholder.itemView.hasFocusable())
            return viewholder.itemView;
        i--;
        continue; /* Loop/switch isn't completed */
_L3:
        if(viewholder.itemView.hasFocusable())
            return viewholder.itemView;
        k++;
        continue; /* Loop/switch isn't completed */
        if(true) goto _L5; else goto _L4
_L4:
        return null;
        if(true) goto _L7; else goto _L6
_L6:
    }

    static ViewHolder getChildViewHolderInt(View view)
    {
        if(view == null)
            return null;
        else
            return ((LayoutParams)view.getLayoutParams()).mViewHolder;
    }

    static void getDecoratedBoundsWithMarginsInt(View view, Rect rect)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        Rect rect1 = layoutparams.mDecorInsets;
        rect.set(view.getLeft() - rect1.left - layoutparams.leftMargin, view.getTop() - rect1.top - layoutparams.topMargin, view.getRight() + rect1.right + layoutparams.rightMargin, view.getBottom() + rect1.bottom + layoutparams.bottomMargin);
    }

    private int getDeepestFocusedViewWithId(View view)
    {
        int i = view.getId();
        do
        {
            if(view.isFocused() || !(view instanceof ViewGroup) || !view.hasFocus())
                break;
            View view1 = ((ViewGroup)view).getFocusedChild();
            view = view1;
            if(view1.getId() != -1)
            {
                i = view1.getId();
                view = view1;
            }
        } while(true);
        return i;
    }

    private String getFullClassName(Context context, String s)
    {
        if(s.charAt(0) == '.')
            return (new StringBuilder()).append(context.getPackageName()).append(s).toString();
        if(s.contains("."))
            return s;
        else
            return (new StringBuilder()).append(com/android/internal/widget/RecyclerView.getPackage().getName()).append('.').append(s).toString();
    }

    private float getScrollFactor()
    {
label0:
        {
            if(mScrollFactor == 1.401298E-045F)
            {
                TypedValue typedvalue = new TypedValue();
                if(!getContext().getTheme().resolveAttribute(0x101004d, typedvalue, true))
                    break label0;
                mScrollFactor = typedvalue.getDimension(getContext().getResources().getDisplayMetrics());
            }
            return mScrollFactor;
        }
        return 0.0F;
    }

    private void handleMissingPreInfoForChangeError(long l, ViewHolder viewholder, ViewHolder viewholder1)
    {
        int i;
        int j;
        i = mChildHelper.getChildCount();
        j = 0;
_L3:
        ViewHolder viewholder2;
        if(j >= i)
            break; /* Loop/switch isn't completed */
        viewholder2 = getChildViewHolderInt(mChildHelper.getChildAt(j));
          goto _L1
_L5:
        j++;
        if(true) goto _L3; else goto _L2
_L1:
        if(viewholder2 == viewholder || getChangedHolderKey(viewholder2) != l) goto _L5; else goto _L4
_L4:
        if(mAdapter != null && mAdapter.hasStableIds())
            throw new IllegalStateException((new StringBuilder()).append("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:").append(viewholder2).append(" \n View Holder 2:").append(viewholder).toString());
        else
            throw new IllegalStateException((new StringBuilder()).append("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:").append(viewholder2).append(" \n View Holder 2:").append(viewholder).toString());
_L2:
        Log.e("RecyclerView", (new StringBuilder()).append("Problem while matching changed view holders with the newones. The pre-layout information for the change holder ").append(viewholder1).append(" cannot be found but it is necessary for ").append(viewholder).toString());
        return;
    }

    private boolean hasUpdatedView()
    {
        int i;
        int j;
        i = mChildHelper.getChildCount();
        j = 0;
_L3:
        ViewHolder viewholder;
        if(j >= i)
            break; /* Loop/switch isn't completed */
        viewholder = getChildViewHolderInt(mChildHelper.getChildAt(j));
          goto _L1
_L5:
        j++;
        if(true) goto _L3; else goto _L2
_L1:
        if(viewholder == null || viewholder.shouldIgnore() || !viewholder.isUpdated()) goto _L5; else goto _L4
_L4:
        return true;
_L2:
        return false;
    }

    private void initChildrenHelper()
    {
        mChildHelper = new ChildHelper(new ChildHelper.Callback() {

            public void addView(View view, int i)
            {
                RecyclerView.this.addView(view, i);
                dispatchChildAttached(view);
            }

            public void attachViewToParent(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
            {
                ViewHolder viewholder = RecyclerView.getChildViewHolderInt(view);
                if(viewholder != null)
                {
                    if(!viewholder.isTmpDetached() && viewholder.shouldIgnore() ^ true)
                        throw new IllegalArgumentException((new StringBuilder()).append("Called attach on a child which is not detached: ").append(viewholder).toString());
                    viewholder.clearTmpDetachFlag();
                }
                RecyclerView._2D_wrap1(RecyclerView.this, view, i, layoutparams);
            }

            public void detachViewFromParent(int i)
            {
                Object obj = getChildAt(i);
                if(obj != null)
                {
                    obj = RecyclerView.getChildViewHolderInt(((View) (obj)));
                    if(obj != null)
                    {
                        if(((ViewHolder) (obj)).isTmpDetached() && ((ViewHolder) (obj)).shouldIgnore() ^ true)
                            throw new IllegalArgumentException((new StringBuilder()).append("called detach on an already detached child ").append(obj).toString());
                        ((ViewHolder) (obj)).addFlags(256);
                    }
                }
                RecyclerView._2D_wrap2(RecyclerView.this, i);
            }

            public View getChildAt(int i)
            {
                return RecyclerView.this.getChildAt(i);
            }

            public int getChildCount()
            {
                return RecyclerView.this.getChildCount();
            }

            public ViewHolder getChildViewHolder(View view)
            {
                return RecyclerView.getChildViewHolderInt(view);
            }

            public int indexOfChild(View view)
            {
                return RecyclerView.this.indexOfChild(view);
            }

            public void onEnteredHiddenState(View view)
            {
                view = RecyclerView.getChildViewHolderInt(view);
                if(view != null)
                    ViewHolder._2D_wrap2(view, RecyclerView.this);
            }

            public void onLeftHiddenState(View view)
            {
                view = RecyclerView.getChildViewHolderInt(view);
                if(view != null)
                    ViewHolder._2D_wrap3(view, RecyclerView.this);
            }

            public void removeAllViews()
            {
                int i = getChildCount();
                for(int j = 0; j < i; j++)
                    dispatchChildDetached(getChildAt(j));

                RecyclerView.this.removeAllViews();
            }

            public void removeViewAt(int i)
            {
                View view = RecyclerView.this.getChildAt(i);
                if(view != null)
                    dispatchChildDetached(view);
                RecyclerView.this.removeViewAt(i);
            }

            final RecyclerView this$0;

            
            {
                this$0 = RecyclerView.this;
                super();
            }
        }
);
    }

    private boolean isPreferredNextFocus(View view, View view1, int i)
    {
        boolean flag = false;
        if(view1 == null || view1 == this)
            return false;
        if(view == null)
            return true;
        if(i == 2 || i == 1)
        {
            byte byte0;
            if(mLayout.getLayoutDirection() == 1)
                byte0 = 1;
            else
                byte0 = 0;
            if(i == 2)
                flag = true;
            if(flag ^ byte0)
                byte0 = 66;
            else
                byte0 = 17;
            if(isPreferredNextFocusAbsolute(view, view1, byte0))
                return true;
            if(i == 2)
                return isPreferredNextFocusAbsolute(view, view1, 130);
            else
                return isPreferredNextFocusAbsolute(view, view1, 33);
        } else
        {
            return isPreferredNextFocusAbsolute(view, view1, i);
        }
    }

    private boolean isPreferredNextFocusAbsolute(View view, View view1, int i)
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        boolean flag6;
        boolean flag7;
        flag = true;
        flag1 = true;
        flag2 = true;
        flag3 = true;
        flag4 = false;
        flag5 = false;
        flag6 = false;
        flag7 = false;
        mTempRect.set(0, 0, view.getWidth(), view.getHeight());
        mTempRect2.set(0, 0, view1.getWidth(), view1.getHeight());
        offsetDescendantRectToMyCoords(view, mTempRect);
        offsetDescendantRectToMyCoords(view1, mTempRect2);
        i;
        JVM INSTR lookupswitch 4: default 120
    //                   17: 148
    //                   33: 280
    //                   66: 212
    //                   130: 348;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("direction must be absolute. received:").append(i).toString());
_L2:
        if(mTempRect.right > mTempRect2.right || mTempRect.left >= mTempRect2.right)
            if(mTempRect.left > mTempRect2.left)
                flag7 = flag3;
            else
                flag7 = false;
        return flag7;
_L4:
        if(mTempRect.left < mTempRect2.left) goto _L7; else goto _L6
_L6:
        flag7 = flag4;
        if(mTempRect.right > mTempRect2.left) goto _L8; else goto _L7
_L7:
        if(mTempRect.right < mTempRect2.right)
            flag7 = flag;
        else
            flag7 = false;
_L8:
        return flag7;
_L3:
        if(mTempRect.bottom > mTempRect2.bottom) goto _L10; else goto _L9
_L9:
        flag7 = flag5;
        if(mTempRect.top < mTempRect2.bottom) goto _L11; else goto _L10
_L10:
        if(mTempRect.top > mTempRect2.top)
            flag7 = flag1;
        else
            flag7 = false;
_L11:
        return flag7;
_L5:
        if(mTempRect.top < mTempRect2.top) goto _L13; else goto _L12
_L12:
        flag7 = flag6;
        if(mTempRect.bottom > mTempRect2.top) goto _L14; else goto _L13
_L13:
        if(mTempRect.bottom < mTempRect2.bottom)
            flag7 = flag2;
        else
            flag7 = false;
_L14:
        return flag7;
    }

    private void onPointerUp(MotionEvent motionevent)
    {
        int i = motionevent.getActionIndex();
        if(motionevent.getPointerId(i) == mScrollPointerId)
        {
            int j;
            if(i == 0)
                i = 1;
            else
                i = 0;
            mScrollPointerId = motionevent.getPointerId(i);
            j = (int)(motionevent.getX(i) + 0.5F);
            mLastTouchX = j;
            mInitialTouchX = j;
            i = (int)(motionevent.getY(i) + 0.5F);
            mLastTouchY = i;
            mInitialTouchY = i;
        }
    }

    private boolean predictiveItemAnimationsEnabled()
    {
        boolean flag;
        if(mItemAnimator != null)
            flag = mLayout.supportsPredictiveItemAnimations();
        else
            flag = false;
        return flag;
    }

    private void processAdapterUpdatesAndSetAnimationFlags()
    {
        boolean flag = false;
        if(mDataSetHasChangedAfterLayout)
        {
            mAdapterHelper.reset();
            mLayout.onItemsChanged(this);
        }
        boolean flag1;
        State state;
        boolean flag2;
        if(predictiveItemAnimationsEnabled())
            mAdapterHelper.preProcess();
        else
            mAdapterHelper.consumeUpdatesInOnePass();
        if(!mItemsAddedOrRemoved)
            flag1 = mItemsChanged;
        else
            flag1 = true;
        state = mState;
        if(mFirstLayoutComplete && mItemAnimator != null && (mDataSetHasChangedAfterLayout || flag1 || mLayout.mRequestedSimpleAnimations))
        {
            if(mDataSetHasChangedAfterLayout)
                flag2 = mAdapter.hasStableIds();
            else
                flag2 = true;
        } else
        {
            flag2 = false;
        }
        state.mRunSimpleAnimations = flag2;
        state = mState;
        flag2 = flag;
        if(mState.mRunSimpleAnimations)
        {
            flag2 = flag;
            if(flag1)
            {
                flag2 = flag;
                if(mDataSetHasChangedAfterLayout ^ true)
                    flag2 = predictiveItemAnimationsEnabled();
            }
        }
        state.mRunPredictiveAnimations = flag2;
    }

    private void pullGlows(float f, float f1, float f2, float f3)
    {
        boolean flag = false;
        if(f1 < 0.0F)
        {
            ensureLeftGlow();
            mLeftGlow.onPull(-f1 / (float)getWidth(), 1.0F - f2 / (float)getHeight());
            flag = true;
        } else
        if(f1 > 0.0F)
        {
            ensureRightGlow();
            mRightGlow.onPull(f1 / (float)getWidth(), f2 / (float)getHeight());
            flag = true;
        }
        if(f3 < 0.0F)
        {
            ensureTopGlow();
            mTopGlow.onPull(-f3 / (float)getHeight(), f / (float)getWidth());
            flag = true;
        } else
        if(f3 > 0.0F)
        {
            ensureBottomGlow();
            mBottomGlow.onPull(f3 / (float)getHeight(), 1.0F - f / (float)getWidth());
            flag = true;
        }
        if(flag || f1 != 0.0F || f3 != 0.0F)
            postInvalidateOnAnimation();
    }

    private void recoverFocusFromState()
    {
        while(!mPreserveFocusAfterLayout || mAdapter == null || hasFocus() ^ true || getDescendantFocusability() == 0x60000 || getDescendantFocusability() == 0x20000 && isFocused()) 
            return;
        if(!isFocused())
        {
            View view = getFocusedChild();
            if(IGNORE_DETACHED_FOCUSED_CHILD && (view.getParent() == null || view.hasFocus() ^ true))
            {
                if(mChildHelper.getChildCount() == 0)
                {
                    requestFocus();
                    return;
                }
            } else
            if(!mChildHelper.isHidden(view))
                return;
        }
        Object obj1 = null;
        Object obj = obj1;
        if(mState.mFocusedItemId != -1L)
        {
            obj = obj1;
            if(mAdapter.hasStableIds())
                obj = findViewHolderForItemId(mState.mFocusedItemId);
        }
        obj1 = null;
        if(obj == null || mChildHelper.isHidden(((ViewHolder) (obj)).itemView) || ((ViewHolder) (obj)).itemView.hasFocusable() ^ true)
        {
            obj = obj1;
            if(mChildHelper.getChildCount() > 0)
                obj = findNextViewToFocus();
        } else
        {
            obj = ((ViewHolder) (obj)).itemView;
        }
        if(obj != null)
        {
            Object obj2 = obj;
            if((long)mState.mFocusedSubChildId != -1L)
            {
                View view1 = ((View) (obj)).findViewById(mState.mFocusedSubChildId);
                obj2 = obj;
                if(view1 != null)
                {
                    obj2 = obj;
                    if(view1.isFocusable())
                        obj2 = view1;
                }
            }
            ((View) (obj2)).requestFocus();
        }
    }

    private void releaseGlows()
    {
        boolean flag = false;
        if(mLeftGlow != null)
        {
            mLeftGlow.onRelease();
            flag = true;
        }
        if(mTopGlow != null)
        {
            mTopGlow.onRelease();
            flag = true;
        }
        if(mRightGlow != null)
        {
            mRightGlow.onRelease();
            flag = true;
        }
        if(mBottomGlow != null)
        {
            mBottomGlow.onRelease();
            flag = true;
        }
        if(flag)
            postInvalidateOnAnimation();
    }

    private void resetFocusInfo()
    {
        mState.mFocusedItemId = -1L;
        mState.mFocusedItemPosition = -1;
        mState.mFocusedSubChildId = -1;
    }

    private void resetTouch()
    {
        if(mVelocityTracker != null)
            mVelocityTracker.clear();
        stopNestedScroll();
        releaseGlows();
    }

    private void saveFocusInfo()
    {
        Object obj1;
        Object obj = null;
        obj1 = obj;
        if(mPreserveFocusAfterLayout)
        {
            obj1 = obj;
            if(hasFocus())
            {
                obj1 = obj;
                if(mAdapter != null)
                    obj1 = getFocusedChild();
            }
        }
        if(obj1 == null)
            obj1 = null;
        else
            obj1 = findContainingViewHolder(((View) (obj1)));
        if(obj1 != null) goto _L2; else goto _L1
_L1:
        resetFocusInfo();
_L4:
        return;
_L2:
        int i;
        State state = mState;
        long l;
        if(mAdapter.hasStableIds())
            l = ((ViewHolder) (obj1)).getItemId();
        else
            l = -1L;
        state.mFocusedItemId = l;
        state = mState;
        if(!mDataSetHasChangedAfterLayout)
            break; /* Loop/switch isn't completed */
        i = -1;
_L5:
        state.mFocusedItemPosition = i;
        mState.mFocusedSubChildId = getDeepestFocusedViewWithId(((ViewHolder) (obj1)).itemView);
        if(true) goto _L4; else goto _L3
_L3:
        if(((ViewHolder) (obj1)).isRemoved())
            i = ((ViewHolder) (obj1)).mOldPosition;
        else
            i = ((ViewHolder) (obj1)).getAdapterPosition();
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    private void setAdapterInternal(Adapter adapter, boolean flag, boolean flag1)
    {
        if(mAdapter != null)
        {
            mAdapter.unregisterAdapterDataObserver(mObserver);
            mAdapter.onDetachedFromRecyclerView(this);
        }
        if(!flag || flag1)
            removeAndRecycleViews();
        mAdapterHelper.reset();
        Adapter adapter1 = mAdapter;
        mAdapter = adapter;
        if(adapter != null)
        {
            adapter.registerAdapterDataObserver(mObserver);
            adapter.onAttachedToRecyclerView(this);
        }
        if(mLayout != null)
            mLayout.onAdapterChanged(adapter1, mAdapter);
        mRecycler.onAdapterChanged(adapter1, mAdapter, flag);
        mState.mStructureChanged = true;
        markKnownViewsInvalid();
    }

    private void stopScrollersInternal()
    {
        mViewFlinger.stop();
        if(mLayout != null)
            mLayout.stopSmoothScroller();
    }

    void absorbGlows(int i, int j)
    {
        if(i < 0)
        {
            ensureLeftGlow();
            mLeftGlow.onAbsorb(-i);
        } else
        if(i > 0)
        {
            ensureRightGlow();
            mRightGlow.onAbsorb(i);
        }
        if(j < 0)
        {
            ensureTopGlow();
            mTopGlow.onAbsorb(-j);
        } else
        if(j > 0)
        {
            ensureBottomGlow();
            mBottomGlow.onAbsorb(j);
        }
        if(i != 0 || j != 0)
            postInvalidateOnAnimation();
    }

    public void addFocusables(ArrayList arraylist, int i, int j)
    {
        if(mLayout == null || mLayout.onAddFocusables(this, arraylist, i, j) ^ true)
            super.addFocusables(arraylist, i, j);
    }

    public void addItemDecoration(ItemDecoration itemdecoration)
    {
        addItemDecoration(itemdecoration, -1);
    }

    public void addItemDecoration(ItemDecoration itemdecoration, int i)
    {
        if(mLayout != null)
            mLayout.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
        if(mItemDecorations.isEmpty())
            setWillNotDraw(false);
        if(i < 0)
            mItemDecorations.add(itemdecoration);
        else
            mItemDecorations.add(i, itemdecoration);
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void addOnChildAttachStateChangeListener(OnChildAttachStateChangeListener onchildattachstatechangelistener)
    {
        if(mOnChildAttachStateListeners == null)
            mOnChildAttachStateListeners = new ArrayList();
        mOnChildAttachStateListeners.add(onchildattachstatechangelistener);
    }

    public void addOnItemTouchListener(OnItemTouchListener onitemtouchlistener)
    {
        mOnItemTouchListeners.add(onitemtouchlistener);
    }

    public void addOnScrollListener(OnScrollListener onscrolllistener)
    {
        if(mScrollListeners == null)
            mScrollListeners = new ArrayList();
        mScrollListeners.add(onscrolllistener);
    }

    void animateAppearance(ViewHolder viewholder, ItemAnimator.ItemHolderInfo itemholderinfo, ItemAnimator.ItemHolderInfo itemholderinfo1)
    {
        viewholder.setIsRecyclable(false);
        if(mItemAnimator.animateAppearance(viewholder, itemholderinfo, itemholderinfo1))
            postAnimationRunner();
    }

    void animateDisappearance(ViewHolder viewholder, ItemAnimator.ItemHolderInfo itemholderinfo, ItemAnimator.ItemHolderInfo itemholderinfo1)
    {
        addAnimatingView(viewholder);
        viewholder.setIsRecyclable(false);
        if(mItemAnimator.animateDisappearance(viewholder, itemholderinfo, itemholderinfo1))
            postAnimationRunner();
    }

    void assertInLayoutOrScroll(String s)
    {
        if(!isComputingLayout())
        {
            if(s == null)
                throw new IllegalStateException("Cannot call this method unless RecyclerView is computing a layout or scrolling");
            else
                throw new IllegalStateException(s);
        } else
        {
            return;
        }
    }

    void assertNotInLayoutOrScroll(String s)
    {
        if(isComputingLayout())
            if(s == null)
                throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling");
            else
                throw new IllegalStateException(s);
        if(mDispatchScrollCounter > 0)
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks might be run during a measure & layout pass where you cannot change the RecyclerView data. Any method call that might change the structure of the RecyclerView or the adapter contents should be postponed to the next frame.", new IllegalStateException(""));
    }

    boolean canReuseUpdatedViewHolder(ViewHolder viewholder)
    {
        boolean flag;
        if(mItemAnimator != null)
            flag = mItemAnimator.canReuseUpdatedViewHolder(viewholder, viewholder.getUnmodifiedPayloads());
        else
            flag = true;
        return flag;
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        boolean flag;
        if(layoutparams instanceof LayoutParams)
            flag = mLayout.checkLayoutParams((LayoutParams)layoutparams);
        else
            flag = false;
        return flag;
    }

    void clearOldPositions()
    {
        int i = mChildHelper.getUnfilteredChildCount();
        for(int j = 0; j < i; j++)
        {
            ViewHolder viewholder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(j));
            if(!viewholder.shouldIgnore())
                viewholder.clearOldPosition();
        }

        mRecycler.clearOldPositions();
    }

    public void clearOnChildAttachStateChangeListeners()
    {
        if(mOnChildAttachStateListeners != null)
            mOnChildAttachStateListeners.clear();
    }

    public void clearOnScrollListeners()
    {
        if(mScrollListeners != null)
            mScrollListeners.clear();
    }

    public int computeHorizontalScrollExtent()
    {
        int i = 0;
        if(mLayout == null)
            return 0;
        if(mLayout.canScrollHorizontally())
            i = mLayout.computeHorizontalScrollExtent(mState);
        return i;
    }

    public int computeHorizontalScrollOffset()
    {
        int i = 0;
        if(mLayout == null)
            return 0;
        if(mLayout.canScrollHorizontally())
            i = mLayout.computeHorizontalScrollOffset(mState);
        return i;
    }

    public int computeHorizontalScrollRange()
    {
        int i = 0;
        if(mLayout == null)
            return 0;
        if(mLayout.canScrollHorizontally())
            i = mLayout.computeHorizontalScrollRange(mState);
        return i;
    }

    public int computeVerticalScrollExtent()
    {
        int i = 0;
        if(mLayout == null)
            return 0;
        if(mLayout.canScrollVertically())
            i = mLayout.computeVerticalScrollExtent(mState);
        return i;
    }

    public int computeVerticalScrollOffset()
    {
        int i = 0;
        if(mLayout == null)
            return 0;
        if(mLayout.canScrollVertically())
            i = mLayout.computeVerticalScrollOffset(mState);
        return i;
    }

    public int computeVerticalScrollRange()
    {
        int i = 0;
        if(mLayout == null)
            return 0;
        if(mLayout.canScrollVertically())
            i = mLayout.computeVerticalScrollRange(mState);
        return i;
    }

    void considerReleasingGlowsOnScroll(int i, int j)
    {
        boolean flag = false;
        int k = ((flag) ? 1 : 0);
        if(mLeftGlow != null)
        {
            k = ((flag) ? 1 : 0);
            if(mLeftGlow.isFinished() ^ true)
            {
                k = ((flag) ? 1 : 0);
                if(i > 0)
                {
                    mLeftGlow.onRelease();
                    k = 1;
                }
            }
        }
        flag = k;
        if(mRightGlow != null)
        {
            flag = k;
            if(mRightGlow.isFinished() ^ true)
            {
                flag = k;
                if(i < 0)
                {
                    mRightGlow.onRelease();
                    flag = true;
                }
            }
        }
        i = ((flag) ? 1 : 0);
        if(mTopGlow != null)
        {
            i = ((flag) ? 1 : 0);
            if(mTopGlow.isFinished() ^ true)
            {
                i = ((flag) ? 1 : 0);
                if(j > 0)
                {
                    mTopGlow.onRelease();
                    i = 1;
                }
            }
        }
        k = i;
        if(mBottomGlow != null)
        {
            k = i;
            if(mBottomGlow.isFinished() ^ true)
            {
                k = i;
                if(j < 0)
                {
                    mBottomGlow.onRelease();
                    k = 1;
                }
            }
        }
        if(k != 0)
            postInvalidateOnAnimation();
    }

    void consumePendingUpdateOperations()
    {
        if(!mFirstLayoutComplete || mDataSetHasChangedAfterLayout)
        {
            Trace.beginSection("RV FullInvalidate");
            dispatchLayout();
            Trace.endSection();
            return;
        }
        if(!mAdapterHelper.hasPendingUpdates())
            return;
        if(!mAdapterHelper.hasAnyUpdateTypes(4) || !(mAdapterHelper.hasAnyUpdateTypes(11) ^ true)) goto _L2; else goto _L1
_L1:
        Trace.beginSection("RV PartialInvalidate");
        eatRequestLayout();
        onEnterLayoutOrScroll();
        mAdapterHelper.preProcess();
        if(!mLayoutRequestEaten)
            if(hasUpdatedView())
                dispatchLayout();
            else
                mAdapterHelper.consumePostponedUpdates();
        resumeRequestLayout(true);
        onExitLayoutOrScroll();
        Trace.endSection();
_L4:
        return;
_L2:
        if(mAdapterHelper.hasPendingUpdates())
        {
            Trace.beginSection("RV FullInvalidate");
            dispatchLayout();
            Trace.endSection();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    void defaultOnMeasure(int i, int j)
    {
        setMeasuredDimension(LayoutManager.chooseSize(i, getPaddingLeft() + getPaddingRight(), getMinimumWidth()), LayoutManager.chooseSize(j, getPaddingTop() + getPaddingBottom(), getMinimumHeight()));
    }

    void dispatchChildAttached(View view)
    {
        ViewHolder viewholder = getChildViewHolderInt(view);
        onChildAttachedToWindow(view);
        if(mAdapter != null && viewholder != null)
            mAdapter.onViewAttachedToWindow(viewholder);
        if(mOnChildAttachStateListeners != null)
        {
            for(int i = mOnChildAttachStateListeners.size() - 1; i >= 0; i--)
                ((OnChildAttachStateChangeListener)mOnChildAttachStateListeners.get(i)).onChildViewAttachedToWindow(view);

        }
    }

    void dispatchChildDetached(View view)
    {
        ViewHolder viewholder = getChildViewHolderInt(view);
        onChildDetachedFromWindow(view);
        if(mAdapter != null && viewholder != null)
            mAdapter.onViewDetachedFromWindow(viewholder);
        if(mOnChildAttachStateListeners != null)
        {
            for(int i = mOnChildAttachStateListeners.size() - 1; i >= 0; i--)
                ((OnChildAttachStateChangeListener)mOnChildAttachStateListeners.get(i)).onChildViewDetachedFromWindow(view);

        }
    }

    void dispatchLayout()
    {
        if(mAdapter == null)
        {
            Log.e("RecyclerView", "No adapter attached; skipping layout");
            return;
        }
        if(mLayout == null)
        {
            Log.e("RecyclerView", "No layout manager attached; skipping layout");
            return;
        }
        mState.mIsMeasuring = false;
        if(mState.mLayoutStep == 1)
        {
            dispatchLayoutStep1();
            mLayout.setExactMeasureSpecsFrom(this);
            dispatchLayoutStep2();
        } else
        if(mAdapterHelper.hasUpdates() || mLayout.getWidth() != getWidth() || mLayout.getHeight() != getHeight())
        {
            mLayout.setExactMeasureSpecsFrom(this);
            dispatchLayoutStep2();
        } else
        {
            mLayout.setExactMeasureSpecsFrom(this);
        }
        dispatchLayoutStep3();
    }

    void dispatchOnScrollStateChanged(int i)
    {
        if(mLayout != null)
            mLayout.onScrollStateChanged(i);
        onScrollStateChanged(i);
        if(mScrollListener != null)
            mScrollListener.onScrollStateChanged(this, i);
        if(mScrollListeners != null)
        {
            for(int j = mScrollListeners.size() - 1; j >= 0; j--)
                ((OnScrollListener)mScrollListeners.get(j)).onScrollStateChanged(this, i);

        }
    }

    void dispatchOnScrolled(int i, int j)
    {
        mDispatchScrollCounter = mDispatchScrollCounter + 1;
        int k = getScrollX();
        int i1 = getScrollY();
        onScrollChanged(k, i1, k, i1);
        onScrolled(i, j);
        if(mScrollListener != null)
            mScrollListener.onScrolled(this, i, j);
        if(mScrollListeners != null)
        {
            for(int l = mScrollListeners.size() - 1; l >= 0; l--)
                ((OnScrollListener)mScrollListeners.get(l)).onScrolled(this, i, j);

        }
        mDispatchScrollCounter = mDispatchScrollCounter - 1;
    }

    void dispatchPendingImportantForAccessibilityChanges()
    {
        int i = mPendingAccessibilityImportanceChange.size() - 1;
        while(i >= 0) 
        {
            ViewHolder viewholder = (ViewHolder)mPendingAccessibilityImportanceChange.get(i);
            if(viewholder.itemView.getParent() == this && !viewholder.shouldIgnore())
            {
                int j = viewholder.mPendingAccessibilityState;
                if(j != -1)
                {
                    viewholder.itemView.setImportantForAccessibility(j);
                    viewholder.mPendingAccessibilityState = -1;
                }
            }
            i--;
        }
        mPendingAccessibilityImportanceChange.clear();
    }

    protected void dispatchRestoreInstanceState(SparseArray sparsearray)
    {
        dispatchThawSelfOnly(sparsearray);
    }

    protected void dispatchSaveInstanceState(SparseArray sparsearray)
    {
        dispatchFreezeSelfOnly(sparsearray);
    }

    public void draw(Canvas canvas)
    {
        boolean flag = false;
        super.draw(canvas);
        int i = mItemDecorations.size();
        for(int k = 0; k < i; k++)
            ((ItemDecoration)mItemDecorations.get(k)).onDrawOver(canvas, this, mState);

        boolean flag1 = false;
        boolean flag2 = flag1;
        int l;
        if(mLeftGlow != null)
        {
            flag2 = flag1;
            if(mLeftGlow.isFinished() ^ true)
            {
                int j = canvas.save();
                int i1;
                if(mClipToPadding)
                    l = getPaddingBottom();
                else
                    l = 0;
                canvas.rotate(270F);
                canvas.translate(-getHeight() + l, 0.0F);
                if(mLeftGlow != null)
                    flag2 = mLeftGlow.draw(canvas);
                else
                    flag2 = false;
                canvas.restoreToCount(j);
            }
        }
        flag1 = flag2;
        if(mTopGlow != null)
        {
            flag1 = flag2;
            if(mTopGlow.isFinished() ^ true)
            {
                l = canvas.save();
                if(mClipToPadding)
                    canvas.translate(getPaddingLeft(), getPaddingTop());
                if(mTopGlow != null)
                    flag1 = mTopGlow.draw(canvas);
                else
                    flag1 = false;
                flag1 = flag2 | flag1;
                canvas.restoreToCount(l);
            }
        }
        flag2 = flag1;
        if(mRightGlow != null)
        {
            flag2 = flag1;
            if(mRightGlow.isFinished() ^ true)
            {
                i1 = canvas.save();
                j = getWidth();
                if(mClipToPadding)
                    l = getPaddingTop();
                else
                    l = 0;
                canvas.rotate(90F);
                canvas.translate(-l, -j);
                if(mRightGlow != null)
                    flag2 = mRightGlow.draw(canvas);
                else
                    flag2 = false;
                flag2 = flag1 | flag2;
                canvas.restoreToCount(i1);
            }
        }
        flag1 = flag2;
        if(mBottomGlow != null)
        {
            flag1 = flag2;
            if(mBottomGlow.isFinished() ^ true)
            {
                l = canvas.save();
                canvas.rotate(180F);
                if(mClipToPadding)
                    canvas.translate(-getWidth() + getPaddingRight(), -getHeight() + getPaddingBottom());
                else
                    canvas.translate(-getWidth(), -getHeight());
                flag1 = flag;
                if(mBottomGlow != null)
                    flag1 = mBottomGlow.draw(canvas);
                flag1 = flag2 | flag1;
                canvas.restoreToCount(l);
            }
        }
        flag2 = flag1;
        if(!flag1)
        {
            flag2 = flag1;
            if(mItemAnimator != null)
            {
                flag2 = flag1;
                if(mItemDecorations.size() > 0)
                {
                    flag2 = flag1;
                    if(mItemAnimator.isRunning())
                        flag2 = true;
                }
            }
        }
        if(flag2)
            postInvalidateOnAnimation();
    }

    public boolean drawChild(Canvas canvas, View view, long l)
    {
        return super.drawChild(canvas, view, l);
    }

    void eatRequestLayout()
    {
        mEatRequestLayout = mEatRequestLayout + 1;
        if(mEatRequestLayout == 1 && mLayoutFrozen ^ true)
            mLayoutRequestEaten = false;
    }

    void ensureBottomGlow()
    {
        if(mBottomGlow != null)
            return;
        mBottomGlow = new EdgeEffect(getContext());
        if(mClipToPadding)
            mBottomGlow.setSize(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
        else
            mBottomGlow.setSize(getMeasuredWidth(), getMeasuredHeight());
    }

    void ensureLeftGlow()
    {
        if(mLeftGlow != null)
            return;
        mLeftGlow = new EdgeEffect(getContext());
        if(mClipToPadding)
            mLeftGlow.setSize(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
        else
            mLeftGlow.setSize(getMeasuredHeight(), getMeasuredWidth());
    }

    void ensureRightGlow()
    {
        if(mRightGlow != null)
            return;
        mRightGlow = new EdgeEffect(getContext());
        if(mClipToPadding)
            mRightGlow.setSize(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
        else
            mRightGlow.setSize(getMeasuredHeight(), getMeasuredWidth());
    }

    void ensureTopGlow()
    {
        if(mTopGlow != null)
            return;
        mTopGlow = new EdgeEffect(getContext());
        if(mClipToPadding)
            mTopGlow.setSize(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
        else
            mTopGlow.setSize(getMeasuredWidth(), getMeasuredHeight());
    }

    public View findChildViewUnder(float f, float f1)
    {
        for(int i = mChildHelper.getChildCount() - 1; i >= 0; i--)
        {
            View view = mChildHelper.getChildAt(i);
            float f2 = view.getTranslationX();
            float f3 = view.getTranslationY();
            if(f >= (float)view.getLeft() + f2 && f <= (float)view.getRight() + f2 && f1 >= (float)view.getTop() + f3 && f1 <= (float)view.getBottom() + f3)
                return view;
        }

        return null;
    }

    public View findContainingItemView(View view)
    {
        ViewParent viewparent;
        for(viewparent = view.getParent(); viewparent != null && viewparent != this && (viewparent instanceof View); viewparent = view.getParent())
            view = (View)viewparent;

        if(viewparent != this)
            view = null;
        return view;
    }

    public ViewHolder findContainingViewHolder(View view)
    {
        Object obj = null;
        view = findContainingItemView(view);
        if(view == null)
            view = obj;
        else
            view = getChildViewHolder(view);
        return view;
    }

    public ViewHolder findViewHolderForAdapterPosition(int i)
    {
        ViewHolder viewholder;
label0:
        {
            if(mDataSetHasChangedAfterLayout)
                return null;
            int j = mChildHelper.getUnfilteredChildCount();
            viewholder = null;
            int k = 0;
            ViewHolder viewholder1;
            do
            {
                if(k >= j)
                    break label0;
                viewholder1 = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(k));
                ViewHolder viewholder2 = viewholder;
                if(viewholder1 != null)
                {
                    viewholder2 = viewholder;
                    if(viewholder1.isRemoved() ^ true)
                    {
                        viewholder2 = viewholder;
                        if(getAdapterPositionFor(viewholder1) == i)
                        {
                            if(!mChildHelper.isHidden(viewholder1.itemView))
                                break;
                            viewholder2 = viewholder1;
                        }
                    }
                }
                k++;
                viewholder = viewholder2;
            } while(true);
            return viewholder1;
        }
        return viewholder;
    }

    public ViewHolder findViewHolderForItemId(long l)
    {
        ViewHolder viewholder;
label0:
        {
            if(mAdapter == null || mAdapter.hasStableIds() ^ true)
                return null;
            int i = mChildHelper.getUnfilteredChildCount();
            viewholder = null;
            int j = 0;
            ViewHolder viewholder1;
            do
            {
                if(j >= i)
                    break label0;
                viewholder1 = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(j));
                ViewHolder viewholder2 = viewholder;
                if(viewholder1 != null)
                {
                    viewholder2 = viewholder;
                    if(viewholder1.isRemoved() ^ true)
                    {
                        viewholder2 = viewholder;
                        if(viewholder1.getItemId() == l)
                        {
                            if(!mChildHelper.isHidden(viewholder1.itemView))
                                break;
                            viewholder2 = viewholder1;
                        }
                    }
                }
                j++;
                viewholder = viewholder2;
            } while(true);
            return viewholder1;
        }
        return viewholder;
    }

    public ViewHolder findViewHolderForLayoutPosition(int i)
    {
        return findViewHolderForPosition(i, false);
    }

    public ViewHolder findViewHolderForPosition(int i)
    {
        return findViewHolderForPosition(i, false);
    }

    ViewHolder findViewHolderForPosition(int i, boolean flag)
    {
        int j;
        ViewHolder viewholder;
        int k;
        j = mChildHelper.getUnfilteredChildCount();
        viewholder = null;
        k = 0;
_L8:
        ViewHolder viewholder1;
        ViewHolder viewholder2;
        if(k >= j)
            break MISSING_BLOCK_LABEL_122;
        viewholder1 = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(k));
        viewholder2 = viewholder;
        if(viewholder1 == null) goto _L2; else goto _L1
_L1:
        viewholder2 = viewholder;
        if(!(viewholder1.isRemoved() ^ true)) goto _L2; else goto _L3
_L3:
        if(!flag) goto _L5; else goto _L4
_L4:
        if(viewholder1.mPosition == i) goto _L7; else goto _L6
_L6:
        viewholder2 = viewholder;
_L2:
        k++;
        viewholder = viewholder2;
          goto _L8
_L5:
        viewholder2 = viewholder;
        if(viewholder1.getLayoutPosition() != i) goto _L2; else goto _L7
_L7:
        if(mChildHelper.isHidden(viewholder1.itemView))
            viewholder2 = viewholder1;
        else
            return viewholder1;
          goto _L2
        return viewholder;
    }

    public boolean fling(int i, int j)
    {
        boolean flag;
        boolean flag1;
        int k;
label0:
        {
            if(mLayout == null)
            {
                Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
                return false;
            }
            if(mLayoutFrozen)
                return false;
            flag = mLayout.canScrollHorizontally();
            flag1 = mLayout.canScrollVertically();
            if(flag)
            {
                k = i;
                if(Math.abs(i) >= mMinFlingVelocity)
                    break label0;
            }
            k = 0;
        }
label1:
        {
            if(flag1)
            {
                i = j;
                if(Math.abs(j) >= mMinFlingVelocity)
                    break label1;
            }
            i = 0;
        }
        if(k == 0 && i == 0)
            return false;
        if(!dispatchNestedPreFling(k, i))
        {
            if(flag)
                flag1 = true;
            dispatchNestedFling(k, i, flag1);
            if(mOnFlingListener != null && mOnFlingListener.onFling(k, i))
                return true;
            if(flag1)
            {
                j = Math.max(-mMaxFlingVelocity, Math.min(k, mMaxFlingVelocity));
                i = Math.max(-mMaxFlingVelocity, Math.min(i, mMaxFlingVelocity));
                mViewFlinger.fling(j, i);
                return true;
            }
        }
        return false;
    }

    public View focusSearch(View view, int i)
    {
        boolean flag = true;
        Object obj = mLayout.onInterceptFocusSearch(view, i);
        if(obj != null)
            return ((View) (obj));
        int j;
        int k;
        if(mAdapter != null && mLayout != null && isComputingLayout() ^ true)
            j = mLayoutFrozen ^ true;
        else
            j = 0;
        obj = FocusFinder.getInstance();
        if(j != 0 && (i == 2 || i == 1))
        {
            boolean flag1 = false;
            j = i;
            if(mLayout.canScrollVertically())
            {
                boolean flag3;
                if(i == 2)
                    k = 130;
                else
                    k = 33;
                if(((FocusFinder) (obj)).findNextFocus(this, view, k) == null)
                    flag3 = true;
                else
                    flag3 = false;
                flag1 = flag3;
                j = i;
                if(FORCE_ABS_FOCUS_SEARCH_DIRECTION)
                {
                    j = k;
                    flag1 = flag3;
                }
            }
            flag3 = flag1;
            k = j;
            if(!flag1)
            {
                flag3 = flag1;
                k = j;
                if(mLayout.canScrollHorizontally())
                {
                    boolean flag2;
                    if(mLayout.getLayoutDirection() == 1)
                        i = 1;
                    else
                        i = 0;
                    if(j == 2)
                        k = ((flag) ? 1 : 0);
                    else
                        k = 0;
                    if((k ^ i) != 0)
                        i = 66;
                    else
                        i = 17;
                    if(((FocusFinder) (obj)).findNextFocus(this, view, i) == null)
                        flag2 = true;
                    else
                        flag2 = false;
                    flag3 = flag2;
                    k = j;
                    if(FORCE_ABS_FOCUS_SEARCH_DIRECTION)
                    {
                        k = i;
                        flag3 = flag2;
                    }
                }
            }
            if(flag3)
            {
                consumePendingUpdateOperations();
                if(findContainingItemView(view) == null)
                    return null;
                eatRequestLayout();
                mLayout.onFocusSearchFailed(view, k, mRecycler, mState);
                resumeRequestLayout(false);
            }
            obj = ((FocusFinder) (obj)).findNextFocus(this, view, k);
        } else
        {
            View view1 = ((FocusFinder) (obj)).findNextFocus(this, view, i);
            obj = view1;
            k = i;
            if(view1 == null)
            {
                obj = view1;
                k = i;
                if(j != 0)
                {
                    consumePendingUpdateOperations();
                    if(findContainingItemView(view) == null)
                        return null;
                    eatRequestLayout();
                    obj = mLayout.onFocusSearchFailed(view, i, mRecycler, mState);
                    resumeRequestLayout(false);
                    k = i;
                }
            }
        }
        if(!isPreferredNextFocus(view, ((View) (obj)), k))
            obj = super.focusSearch(view, k);
        return ((View) (obj));
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        if(mLayout == null)
            throw new IllegalStateException("RecyclerView has no LayoutManager");
        else
            return mLayout.generateDefaultLayoutParams();
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        if(mLayout == null)
            throw new IllegalStateException("RecyclerView has no LayoutManager");
        else
            return mLayout.generateLayoutParams(getContext(), attributeset);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        if(mLayout == null)
            throw new IllegalStateException("RecyclerView has no LayoutManager");
        else
            return mLayout.generateLayoutParams(layoutparams);
    }

    public Adapter getAdapter()
    {
        return mAdapter;
    }

    int getAdapterPositionFor(ViewHolder viewholder)
    {
        if(viewholder.hasAnyOfTheFlags(524) || viewholder.isBound() ^ true)
            return -1;
        else
            return mAdapterHelper.applyPendingUpdatesToPosition(viewholder.mPosition);
    }

    public int getBaseline()
    {
        if(mLayout != null)
            return mLayout.getBaseline();
        else
            return super.getBaseline();
    }

    long getChangedHolderKey(ViewHolder viewholder)
    {
        long l;
        if(mAdapter.hasStableIds())
            l = viewholder.getItemId();
        else
            l = viewholder.mPosition;
        return l;
    }

    public int getChildAdapterPosition(View view)
    {
        view = getChildViewHolderInt(view);
        int i;
        if(view != null)
            i = view.getAdapterPosition();
        else
            i = -1;
        return i;
    }

    protected int getChildDrawingOrder(int i, int j)
    {
        if(mChildDrawingOrderCallback == null)
            return super.getChildDrawingOrder(i, j);
        else
            return mChildDrawingOrderCallback.onGetChildDrawingOrder(i, j);
    }

    public long getChildItemId(View view)
    {
        long l = -1L;
        if(mAdapter == null || mAdapter.hasStableIds() ^ true)
            return -1L;
        view = getChildViewHolderInt(view);
        if(view != null)
            l = view.getItemId();
        return l;
    }

    public int getChildLayoutPosition(View view)
    {
        view = getChildViewHolderInt(view);
        int i;
        if(view != null)
            i = view.getLayoutPosition();
        else
            i = -1;
        return i;
    }

    public int getChildPosition(View view)
    {
        return getChildAdapterPosition(view);
    }

    public ViewHolder getChildViewHolder(View view)
    {
        ViewParent viewparent = view.getParent();
        if(viewparent != null && viewparent != this)
            throw new IllegalArgumentException((new StringBuilder()).append("View ").append(view).append(" is not a direct child of ").append(this).toString());
        else
            return getChildViewHolderInt(view);
    }

    public boolean getClipToPadding()
    {
        return mClipToPadding;
    }

    public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate()
    {
        return mAccessibilityDelegate;
    }

    public void getDecoratedBoundsWithMargins(View view, Rect rect)
    {
        getDecoratedBoundsWithMarginsInt(view, rect);
    }

    public ItemAnimator getItemAnimator()
    {
        return mItemAnimator;
    }

    Rect getItemDecorInsetsForChild(View view)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if(!layoutparams.mInsetsDirty)
            return layoutparams.mDecorInsets;
        if(mState.isPreLayout() && (layoutparams.isItemChanged() || layoutparams.isViewInvalid()))
            return layoutparams.mDecorInsets;
        Rect rect = layoutparams.mDecorInsets;
        rect.set(0, 0, 0, 0);
        int i = mItemDecorations.size();
        for(int j = 0; j < i; j++)
        {
            mTempRect.set(0, 0, 0, 0);
            ((ItemDecoration)mItemDecorations.get(j)).getItemOffsets(mTempRect, view, this, mState);
            rect.left = rect.left + mTempRect.left;
            rect.top = rect.top + mTempRect.top;
            rect.right = rect.right + mTempRect.right;
            rect.bottom = rect.bottom + mTempRect.bottom;
        }

        layoutparams.mInsetsDirty = false;
        return rect;
    }

    public LayoutManager getLayoutManager()
    {
        return mLayout;
    }

    public int getMaxFlingVelocity()
    {
        return mMaxFlingVelocity;
    }

    public int getMinFlingVelocity()
    {
        return mMinFlingVelocity;
    }

    long getNanoTime()
    {
        if(ALLOW_THREAD_GAP_WORK)
            return System.nanoTime();
        else
            return 0L;
    }

    public OnFlingListener getOnFlingListener()
    {
        return mOnFlingListener;
    }

    public boolean getPreserveFocusAfterLayout()
    {
        return mPreserveFocusAfterLayout;
    }

    public RecycledViewPool getRecycledViewPool()
    {
        return mRecycler.getRecycledViewPool();
    }

    public int getScrollState()
    {
        return mScrollState;
    }

    public boolean hasFixedSize()
    {
        return mHasFixedSize;
    }

    public boolean hasPendingAdapterUpdates()
    {
        boolean flag;
        if(mFirstLayoutComplete && !mDataSetHasChangedAfterLayout)
            flag = mAdapterHelper.hasPendingUpdates();
        else
            flag = true;
        return flag;
    }

    void initAdapterManager()
    {
        mAdapterHelper = new AdapterHelper(new AdapterHelper.Callback() {

            void dispatchUpdate(AdapterHelper.UpdateOp updateop)
            {
                updateop.cmd;
                JVM INSTR tableswitch 1 8: default 52
            //                           1 53
            //                           2 78
            //                           3 52
            //                           4 103
            //                           5 52
            //                           6 52
            //                           7 52
            //                           8 132;
                   goto _L1 _L2 _L3 _L1 _L4 _L1 _L1 _L1 _L5
_L1:
                return;
_L2:
                mLayout.onItemsAdded(RecyclerView.this, updateop.positionStart, updateop.itemCount);
                continue; /* Loop/switch isn't completed */
_L3:
                mLayout.onItemsRemoved(RecyclerView.this, updateop.positionStart, updateop.itemCount);
                continue; /* Loop/switch isn't completed */
_L4:
                mLayout.onItemsUpdated(RecyclerView.this, updateop.positionStart, updateop.itemCount, updateop.payload);
                continue; /* Loop/switch isn't completed */
_L5:
                mLayout.onItemsMoved(RecyclerView.this, updateop.positionStart, updateop.itemCount, 1);
                if(true) goto _L1; else goto _L6
_L6:
            }

            public ViewHolder findViewHolder(int i)
            {
                ViewHolder viewholder = findViewHolderForPosition(i, true);
                if(viewholder == null)
                    return null;
                if(mChildHelper.isHidden(viewholder.itemView))
                    return null;
                else
                    return viewholder;
            }

            public void markViewHoldersUpdated(int i, int j, Object obj)
            {
                viewRangeUpdate(i, j, obj);
                mItemsChanged = true;
            }

            public void offsetPositionsForAdd(int i, int j)
            {
                offsetPositionRecordsForInsert(i, j);
                mItemsAddedOrRemoved = true;
            }

            public void offsetPositionsForMove(int i, int j)
            {
                offsetPositionRecordsForMove(i, j);
                mItemsAddedOrRemoved = true;
            }

            public void offsetPositionsForRemovingInvisible(int i, int j)
            {
                offsetPositionRecordsForRemove(i, j, true);
                mItemsAddedOrRemoved = true;
                State state = mState;
                state.mDeletedInvisibleItemCountSincePreviousLayout = state.mDeletedInvisibleItemCountSincePreviousLayout + j;
            }

            public void offsetPositionsForRemovingLaidOutOrNewView(int i, int j)
            {
                offsetPositionRecordsForRemove(i, j, false);
                mItemsAddedOrRemoved = true;
            }

            public void onDispatchFirstPass(AdapterHelper.UpdateOp updateop)
            {
                dispatchUpdate(updateop);
            }

            public void onDispatchSecondPass(AdapterHelper.UpdateOp updateop)
            {
                dispatchUpdate(updateop);
            }

            final RecyclerView this$0;

            
            {
                this$0 = RecyclerView.this;
                super();
            }
        }
);
    }

    void invalidateGlows()
    {
        mBottomGlow = null;
        mTopGlow = null;
        mRightGlow = null;
        mLeftGlow = null;
    }

    public void invalidateItemDecorations()
    {
        if(mItemDecorations.size() == 0)
            return;
        if(mLayout != null)
            mLayout.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout");
        markItemDecorInsetsDirty();
        requestLayout();
    }

    boolean isAccessibilityEnabled()
    {
        boolean flag;
        if(mAccessibilityManager != null)
            flag = mAccessibilityManager.isEnabled();
        else
            flag = false;
        return flag;
    }

    public boolean isAnimating()
    {
        boolean flag;
        if(mItemAnimator != null)
            flag = mItemAnimator.isRunning();
        else
            flag = false;
        return flag;
    }

    public boolean isAttachedToWindow()
    {
        return mIsAttached;
    }

    public boolean isComputingLayout()
    {
        boolean flag = false;
        if(mLayoutOrScrollCounter > 0)
            flag = true;
        return flag;
    }

    public boolean isLayoutFrozen()
    {
        return mLayoutFrozen;
    }

    void jumpToPositionForSmoothScroller(int i)
    {
        if(mLayout == null)
        {
            return;
        } else
        {
            mLayout.scrollToPosition(i);
            awakenScrollBars();
            return;
        }
    }

    void markItemDecorInsetsDirty()
    {
        int i = mChildHelper.getUnfilteredChildCount();
        for(int j = 0; j < i; j++)
            ((LayoutParams)mChildHelper.getUnfilteredChildAt(j).getLayoutParams()).mInsetsDirty = true;

        mRecycler.markItemDecorInsetsDirty();
    }

    void markKnownViewsInvalid()
    {
        int i = mChildHelper.getUnfilteredChildCount();
        for(int j = 0; j < i; j++)
        {
            ViewHolder viewholder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(j));
            if(viewholder != null && viewholder.shouldIgnore() ^ true)
                viewholder.addFlags(6);
        }

        markItemDecorInsetsDirty();
        mRecycler.markKnownViewsInvalid();
    }

    public void offsetChildrenHorizontal(int i)
    {
        int j = mChildHelper.getChildCount();
        for(int k = 0; k < j; k++)
            mChildHelper.getChildAt(k).offsetLeftAndRight(i);

    }

    public void offsetChildrenVertical(int i)
    {
        int j = mChildHelper.getChildCount();
        for(int k = 0; k < j; k++)
            mChildHelper.getChildAt(k).offsetTopAndBottom(i);

    }

    void offsetPositionRecordsForInsert(int i, int j)
    {
        int k = mChildHelper.getUnfilteredChildCount();
        for(int l = 0; l < k; l++)
        {
            ViewHolder viewholder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(l));
            if(viewholder != null && viewholder.shouldIgnore() ^ true && viewholder.mPosition >= i)
            {
                viewholder.offsetPosition(j, false);
                mState.mStructureChanged = true;
            }
        }

        mRecycler.offsetPositionRecordsForInsert(i, j);
        requestLayout();
    }

    void offsetPositionRecordsForMove(int i, int j)
    {
        int i1;
        byte byte0;
        ViewHolder viewholder;
        int k = mChildHelper.getUnfilteredChildCount();
        int l;
        int j1;
        if(i < j)
        {
            l = i;
            i1 = j;
            byte0 = -1;
        } else
        {
            l = j;
            i1 = i;
            byte0 = 1;
        }
        j1 = 0;
        if(j1 >= k)
            break; /* Loop/switch isn't completed */
        viewholder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(j1));
        if(viewholder != null && viewholder.mPosition >= l && viewholder.mPosition <= i1)
        {
            if(viewholder.mPosition == i)
                viewholder.offsetPosition(j - i, false);
            else
                viewholder.offsetPosition(byte0, false);
            mState.mStructureChanged = true;
        }
        j1++;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_25;
_L1:
        mRecycler.offsetPositionRecordsForMove(i, j);
        requestLayout();
        return;
    }

    void offsetPositionRecordsForRemove(int i, int j, boolean flag)
    {
        int k = mChildHelper.getUnfilteredChildCount();
        int l = 0;
        while(l < k) 
        {
            ViewHolder viewholder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(l));
            if(viewholder != null && viewholder.shouldIgnore() ^ true)
                if(viewholder.mPosition >= i + j)
                {
                    viewholder.offsetPosition(-j, flag);
                    mState.mStructureChanged = true;
                } else
                if(viewholder.mPosition >= i)
                {
                    viewholder.flagRemovedAndOffsetPosition(i - 1, -j, flag);
                    mState.mStructureChanged = true;
                }
            l++;
        }
        mRecycler.offsetPositionRecordsForRemove(i, j, flag);
        requestLayout();
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        mLayoutOrScrollCounter = 0;
        mIsAttached = true;
        boolean flag;
        if(mFirstLayoutComplete)
            flag = isLayoutRequested() ^ true;
        else
            flag = false;
        mFirstLayoutComplete = flag;
        if(mLayout != null)
            mLayout.dispatchAttachedToWindow(this);
        mPostedAnimatorRunner = false;
        if(ALLOW_THREAD_GAP_WORK)
        {
            mGapWorker = (GapWorker)GapWorker.sGapWorker.get();
            if(mGapWorker == null)
            {
                mGapWorker = new GapWorker();
                Display display = getDisplay();
                float f = 60F;
                float f1 = f;
                if(!isInEditMode())
                {
                    f1 = f;
                    if(display != null)
                    {
                        float f2 = display.getRefreshRate();
                        f1 = f;
                        if(f2 >= 30F)
                            f1 = f2;
                    }
                }
                mGapWorker.mFrameIntervalNs = (long)(1E+009F / f1);
                GapWorker.sGapWorker.set(mGapWorker);
            }
            mGapWorker.add(this);
        }
    }

    public void onChildAttachedToWindow(View view)
    {
    }

    public void onChildDetachedFromWindow(View view)
    {
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if(mItemAnimator != null)
            mItemAnimator.endAnimations();
        stopScroll();
        mIsAttached = false;
        if(mLayout != null)
            mLayout.dispatchDetachedFromWindow(this, mRecycler);
        mPendingAccessibilityImportanceChange.clear();
        removeCallbacks(mItemAnimatorRunner);
        mViewInfoStore.onDetach();
        if(ALLOW_THREAD_GAP_WORK)
        {
            mGapWorker.remove(this);
            mGapWorker = null;
        }
    }

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int i = mItemDecorations.size();
        for(int j = 0; j < i; j++)
            ((ItemDecoration)mItemDecorations.get(j)).onDraw(canvas, this, mState);

    }

    void onEnterLayoutOrScroll()
    {
        mLayoutOrScrollCounter = mLayoutOrScrollCounter + 1;
    }

    void onExitLayoutOrScroll()
    {
        mLayoutOrScrollCounter = mLayoutOrScrollCounter - 1;
        if(mLayoutOrScrollCounter < 1)
        {
            mLayoutOrScrollCounter = 0;
            dispatchContentChangedIfNecessary();
            dispatchPendingImportantForAccessibilityChanges();
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionevent)
    {
        if(mLayout == null)
            return false;
        if(mLayoutFrozen)
            return false;
        if((motionevent.getSource() & 2) != 0 && motionevent.getAction() == 8)
        {
            float f;
            float f1;
            if(mLayout.canScrollVertically())
                f = -motionevent.getAxisValue(9);
            else
                f = 0.0F;
            if(mLayout.canScrollHorizontally())
                f1 = motionevent.getAxisValue(10);
            else
                f1 = 0.0F;
            if(f != 0.0F || f1 != 0.0F)
            {
                float f2 = getScrollFactor();
                scrollByInternal((int)(f1 * f2), (int)(f * f2), motionevent);
            }
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        boolean flag1;
        int i;
        int k;
        if(mLayoutFrozen)
            return false;
        if(dispatchOnItemTouchIntercept(motionevent))
        {
            cancelTouch();
            return true;
        }
        if(mLayout == null)
            return false;
        flag = mLayout.canScrollHorizontally();
        flag1 = mLayout.canScrollVertically();
        if(mVelocityTracker == null)
            mVelocityTracker = VelocityTracker.obtain();
        mVelocityTracker.addMovement(motionevent);
        i = motionevent.getActionMasked();
        k = motionevent.getActionIndex();
        i;
        JVM INSTR tableswitch 0 6: default 128
    //                   0 140
    //                   1 601
    //                   2 343
    //                   3 615
    //                   4 128
    //                   5 280
    //                   6 593;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L6 _L7
_L1:
        break; /* Loop/switch isn't completed */
_L5:
        break MISSING_BLOCK_LABEL_615;
_L8:
        int j;
        int l;
        int i1;
        int j1;
        if(mScrollState == 1)
            flag1 = true;
        else
            flag1 = false;
        return flag1;
_L2:
        if(mIgnoreMotionEventTillDown)
            mIgnoreMotionEventTillDown = false;
        mScrollPointerId = motionevent.getPointerId(0);
        k = (int)(motionevent.getX() + 0.5F);
        mLastTouchX = k;
        mInitialTouchX = k;
        k = (int)(motionevent.getY() + 0.5F);
        mLastTouchY = k;
        mInitialTouchY = k;
        if(mScrollState == 2)
        {
            getParent().requestDisallowInterceptTouchEvent(true);
            setScrollState(1);
        }
        motionevent = mNestedOffsets;
        mNestedOffsets[1] = 0;
        motionevent[0] = 0;
        k = 0;
        if(flag)
            k = 1;
        j = k;
        if(flag1)
            j = k | 2;
        startNestedScroll(j);
          goto _L8
_L6:
        mScrollPointerId = motionevent.getPointerId(k);
        j = (int)(motionevent.getX(k) + 0.5F);
        mLastTouchX = j;
        mInitialTouchX = j;
        k = (int)(motionevent.getY(k) + 0.5F);
        mLastTouchY = k;
        mInitialTouchY = k;
          goto _L8
_L4:
        k = motionevent.findPointerIndex(mScrollPointerId);
        if(k < 0)
        {
            Log.e("RecyclerView", (new StringBuilder()).append("Error processing scroll; pointer index for id ").append(mScrollPointerId).append(" not found. Did any MotionEvents get skipped?").toString());
            return false;
        }
        j = (int)(motionevent.getX(k) + 0.5F);
        k = (int)(motionevent.getY(k) + 0.5F);
        if(mScrollState != 1)
        {
            l = j - mInitialTouchX;
            i1 = k - mInitialTouchY;
            j = 0;
            k = j;
            if(flag)
            {
                k = j;
                if(Math.abs(l) > mTouchSlop)
                {
                    j = mInitialTouchX;
                    j1 = mTouchSlop;
                    if(l < 0)
                        k = -1;
                    else
                        k = 1;
                    mLastTouchX = k * j1 + j;
                    k = 1;
                }
            }
            j = k;
            if(flag1)
            {
                j = k;
                if(Math.abs(i1) > mTouchSlop)
                {
                    l = mInitialTouchY;
                    j = mTouchSlop;
                    if(i1 < 0)
                        k = -1;
                    else
                        k = 1;
                    mLastTouchY = k * j + l;
                    j = 1;
                }
            }
            if(j != 0)
                setScrollState(1);
        }
          goto _L8
_L7:
        onPointerUp(motionevent);
          goto _L8
_L3:
        mVelocityTracker.clear();
        stopNestedScroll();
          goto _L8
        cancelTouch();
          goto _L8
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        Trace.beginSection("RV OnLayout");
        dispatchLayout();
        Trace.endSection();
        mFirstLayoutComplete = true;
    }

    protected void onMeasure(int i, int j)
    {
        if(mLayout == null)
        {
            defaultOnMeasure(i, j);
            return;
        }
        if(mLayout.mAutoMeasure)
        {
            int k = android.view.View.MeasureSpec.getMode(i);
            int l = android.view.View.MeasureSpec.getMode(j);
            boolean flag;
            if(k == 0x40000000)
            {
                if(l == 0x40000000)
                    flag = true;
                else
                    flag = false;
            } else
            {
                flag = false;
            }
            mLayout.onMeasure(mRecycler, mState, i, j);
            if(flag || mAdapter == null)
                return;
            if(mState.mLayoutStep == 1)
                dispatchLayoutStep1();
            mLayout.setMeasureSpecs(i, j);
            mState.mIsMeasuring = true;
            dispatchLayoutStep2();
            mLayout.setMeasuredDimensionFromChildren(i, j);
            if(mLayout.shouldMeasureTwice())
            {
                mLayout.setMeasureSpecs(android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0x40000000));
                mState.mIsMeasuring = true;
                dispatchLayoutStep2();
                mLayout.setMeasuredDimensionFromChildren(i, j);
            }
        } else
        {
            if(mHasFixedSize)
            {
                mLayout.onMeasure(mRecycler, mState, i, j);
                return;
            }
            if(mAdapterUpdateDuringMeasure)
            {
                eatRequestLayout();
                onEnterLayoutOrScroll();
                processAdapterUpdatesAndSetAnimationFlags();
                onExitLayoutOrScroll();
                if(mState.mRunPredictiveAnimations)
                {
                    mState.mInPreLayout = true;
                } else
                {
                    mAdapterHelper.consumeUpdatesInOnePass();
                    mState.mInPreLayout = false;
                }
                mAdapterUpdateDuringMeasure = false;
                resumeRequestLayout(false);
            }
            if(mAdapter != null)
                mState.mItemCount = mAdapter.getItemCount();
            else
                mState.mItemCount = 0;
            eatRequestLayout();
            mLayout.onMeasure(mRecycler, mState, i, j);
            resumeRequestLayout(false);
            mState.mInPreLayout = false;
        }
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect)
    {
        if(isComputingLayout())
            return false;
        else
            return super.onRequestFocusInDescendants(i, rect);
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if(!(parcelable instanceof SavedState))
        {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        mPendingSavedState = (SavedState)parcelable;
        super.onRestoreInstanceState(mPendingSavedState.getSuperState());
        if(mLayout != null && mPendingSavedState.mLayoutState != null)
            mLayout.onRestoreInstanceState(mPendingSavedState.mLayoutState);
    }

    protected Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        if(mPendingSavedState != null)
            savedstate.copyFrom(mPendingSavedState);
        else
        if(mLayout != null)
            savedstate.mLayoutState = mLayout.onSaveInstanceState();
        else
            savedstate.mLayoutState = null;
        return savedstate;
    }

    public void onScrollStateChanged(int i)
    {
    }

    public void onScrolled(int i, int j)
    {
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        if(i != k || j != l)
            invalidateGlows();
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        MotionEvent motionevent1;
        int i;
        int k;
        int i1;
        if(mLayoutFrozen || mIgnoreMotionEventTillDown)
            return false;
        if(dispatchOnItemTouch(motionevent))
        {
            cancelTouch();
            return true;
        }
        if(mLayout == null)
            return false;
        flag = mLayout.canScrollHorizontally();
        flag1 = mLayout.canScrollVertically();
        if(mVelocityTracker == null)
            mVelocityTracker = VelocityTracker.obtain();
        flag2 = false;
        motionevent1 = MotionEvent.obtain(motionevent);
        i = motionevent.getActionMasked();
        k = motionevent.getActionIndex();
        if(i == 0)
        {
            int ai[] = mNestedOffsets;
            mNestedOffsets[1] = 0;
            ai[0] = 0;
        }
        motionevent1.offsetLocation(mNestedOffsets[0], mNestedOffsets[1]);
        i1 = ((flag2) ? 1 : 0);
        i;
        JVM INSTR tableswitch 0 6: default 180
    //                   0 205
    //                   1 877
    //                   2 365
    //                   3 998
    //                   4 184
    //                   5 298
    //                   6 865;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L6:
        break; /* Loop/switch isn't completed */
_L1:
        i1 = ((flag2) ? 1 : 0);
_L10:
        if(i1 == 0)
            mVelocityTracker.addMovement(motionevent1);
        motionevent1.recycle();
        return true;
_L2:
        mScrollPointerId = motionevent.getPointerId(0);
        i1 = (int)(motionevent.getX() + 0.5F);
        mLastTouchX = i1;
        mInitialTouchX = i1;
        i1 = (int)(motionevent.getY() + 0.5F);
        mLastTouchY = i1;
        mInitialTouchY = i1;
        i1 = 0;
        if(flag)
            i1 = 1;
        k = i1;
        if(flag1)
            k = i1 | 2;
        startNestedScroll(k);
        i1 = ((flag2) ? 1 : 0);
        continue; /* Loop/switch isn't completed */
_L7:
        mScrollPointerId = motionevent.getPointerId(k);
        i1 = (int)(motionevent.getX(k) + 0.5F);
        mLastTouchX = i1;
        mInitialTouchX = i1;
        i1 = (int)(motionevent.getY(k) + 0.5F);
        mLastTouchY = i1;
        mInitialTouchY = i1;
        i1 = ((flag2) ? 1 : 0);
        continue; /* Loop/switch isn't completed */
_L4:
        i1 = motionevent.findPointerIndex(mScrollPointerId);
        if(i1 < 0)
        {
            Log.e("RecyclerView", (new StringBuilder()).append("Error processing scroll; pointer index for id ").append(mScrollPointerId).append(" not found. Did any MotionEvents get skipped?").toString());
            return false;
        }
        int j1 = (int)(motionevent.getX(i1) + 0.5F);
        int k1 = (int)(motionevent.getY(i1) + 0.5F);
        int l1 = mLastTouchX - j1;
        int j = mLastTouchY - k1;
        int l = l1;
        i1 = j;
        if(dispatchNestedPreScroll(l1, j, mScrollConsumed, mScrollOffset))
        {
            l = l1 - mScrollConsumed[0];
            i1 = j - mScrollConsumed[1];
            motionevent1.offsetLocation(mScrollOffset[0], mScrollOffset[1]);
            motionevent = mNestedOffsets;
            motionevent[0] = motionevent[0] + mScrollOffset[0];
            motionevent = mNestedOffsets;
            motionevent[1] = motionevent[1] + mScrollOffset[1];
        }
        l1 = l;
        j = i1;
        if(mScrollState != 1)
        {
            l1 = 0;
            int i2 = l;
            j = l1;
            if(flag)
            {
                i2 = l;
                j = l1;
                if(Math.abs(l) > mTouchSlop)
                {
                    int j2;
                    if(l > 0)
                        i2 = l - mTouchSlop;
                    else
                        i2 = l + mTouchSlop;
                    j = 1;
                }
            }
            l = i1;
            j2 = j;
            if(flag1)
            {
                l = i1;
                j2 = j;
                if(Math.abs(i1) > mTouchSlop)
                {
                    if(i1 > 0)
                        l = i1 - mTouchSlop;
                    else
                        l = i1 + mTouchSlop;
                    j2 = 1;
                }
            }
            l1 = i2;
            j = l;
            if(j2 != 0)
            {
                setScrollState(1);
                j = l;
                l1 = i2;
            }
        }
        i1 = ((flag2) ? 1 : 0);
        if(mScrollState != 1)
            continue; /* Loop/switch isn't completed */
        mLastTouchX = j1 - mScrollOffset[0];
        mLastTouchY = k1 - mScrollOffset[1];
        if(flag)
            i1 = l1;
        else
            i1 = 0;
        if(flag1)
            l = j;
        else
            l = 0;
        if(scrollByInternal(i1, l, motionevent1))
            getParent().requestDisallowInterceptTouchEvent(true);
        i1 = ((flag2) ? 1 : 0);
        if(mGapWorker == null)
            continue; /* Loop/switch isn't completed */
        if(l1 == 0)
        {
            i1 = ((flag2) ? 1 : 0);
            if(j == 0)
                continue; /* Loop/switch isn't completed */
        }
        mGapWorker.postFromTraversal(this, l1, j);
        i1 = ((flag2) ? 1 : 0);
        continue; /* Loop/switch isn't completed */
_L8:
        onPointerUp(motionevent);
        i1 = ((flag2) ? 1 : 0);
        continue; /* Loop/switch isn't completed */
_L3:
        mVelocityTracker.addMovement(motionevent1);
        i1 = 1;
        mVelocityTracker.computeCurrentVelocity(1000, mMaxFlingVelocity);
        float f;
        float f1;
        if(flag)
            f = -mVelocityTracker.getXVelocity(mScrollPointerId);
        else
            f = 0.0F;
        if(flag1)
            f1 = -mVelocityTracker.getYVelocity(mScrollPointerId);
        else
            f1 = 0.0F;
        if(f != 0.0F || f1 != 0.0F)
            flag = fling((int)f, (int)f1);
        else
            flag = false;
        if(!flag)
            setScrollState(0);
        resetTouch();
        continue; /* Loop/switch isn't completed */
_L5:
        cancelTouch();
        i1 = ((flag2) ? 1 : 0);
        if(true) goto _L10; else goto _L9
_L9:
    }

    void postAnimationRunner()
    {
        if(!mPostedAnimatorRunner && mIsAttached)
        {
            postOnAnimation(mItemAnimatorRunner);
            mPostedAnimatorRunner = true;
        }
    }

    void recordAnimationInfoIfBouncedHiddenView(ViewHolder viewholder, ItemAnimator.ItemHolderInfo itemholderinfo)
    {
        viewholder.setFlags(0, 8192);
        if(mState.mTrackOldChangeHolders && viewholder.isUpdated() && viewholder.isRemoved() ^ true && viewholder.shouldIgnore() ^ true)
        {
            long l = getChangedHolderKey(viewholder);
            mViewInfoStore.addToOldChangeHolders(l, viewholder);
        }
        mViewInfoStore.addToPreLayout(viewholder, itemholderinfo);
    }

    void removeAndRecycleViews()
    {
        if(mItemAnimator != null)
            mItemAnimator.endAnimations();
        if(mLayout != null)
        {
            mLayout.removeAndRecycleAllViews(mRecycler);
            mLayout.removeAndRecycleScrapInt(mRecycler);
        }
        mRecycler.clear();
    }

    boolean removeAnimatingView(View view)
    {
        eatRequestLayout();
        boolean flag = mChildHelper.removeViewIfHidden(view);
        if(flag)
        {
            view = getChildViewHolderInt(view);
            mRecycler.unscrapView(view);
            mRecycler.recycleViewHolderInternal(view);
        }
        resumeRequestLayout(flag ^ true);
        return flag;
    }

    protected void removeDetachedView(View view, boolean flag)
    {
        ViewHolder viewholder = getChildViewHolderInt(view);
        if(viewholder != null)
            if(viewholder.isTmpDetached())
                viewholder.clearTmpDetachFlag();
            else
            if(!viewholder.shouldIgnore())
                throw new IllegalArgumentException((new StringBuilder()).append("Called removeDetachedView with a view which is not flagged as tmp detached.").append(viewholder).toString());
        dispatchChildDetached(view);
        super.removeDetachedView(view, flag);
    }

    public void removeItemDecoration(ItemDecoration itemdecoration)
    {
        if(mLayout != null)
            mLayout.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
        mItemDecorations.remove(itemdecoration);
        if(mItemDecorations.isEmpty())
        {
            boolean flag;
            if(getOverScrollMode() == 2)
                flag = true;
            else
                flag = false;
            setWillNotDraw(flag);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void removeOnChildAttachStateChangeListener(OnChildAttachStateChangeListener onchildattachstatechangelistener)
    {
        if(mOnChildAttachStateListeners == null)
        {
            return;
        } else
        {
            mOnChildAttachStateListeners.remove(onchildattachstatechangelistener);
            return;
        }
    }

    public void removeOnItemTouchListener(OnItemTouchListener onitemtouchlistener)
    {
        mOnItemTouchListeners.remove(onitemtouchlistener);
        if(mActiveOnItemTouchListener == onitemtouchlistener)
            mActiveOnItemTouchListener = null;
    }

    public void removeOnScrollListener(OnScrollListener onscrolllistener)
    {
        if(mScrollListeners != null)
            mScrollListeners.remove(onscrolllistener);
    }

    void repositionShadowingViews()
    {
        int i = mChildHelper.getChildCount();
        for(int j = 0; j < i; j++)
        {
            View view = mChildHelper.getChildAt(j);
            Object obj = getChildViewHolder(view);
            if(obj == null || ((ViewHolder) (obj)).mShadowingHolder == null)
                continue;
            obj = ((ViewHolder) (obj)).mShadowingHolder.itemView;
            int k = view.getLeft();
            int l = view.getTop();
            if(k != ((View) (obj)).getLeft() || l != ((View) (obj)).getTop())
                ((View) (obj)).layout(k, l, ((View) (obj)).getWidth() + k, ((View) (obj)).getHeight() + l);
        }

    }

    public void requestChildFocus(View view, View view1)
    {
        if(!mLayout.onRequestChildFocus(this, mState, view, view1) && view1 != null)
        {
            mTempRect.set(0, 0, view1.getWidth(), view1.getHeight());
            Object obj = view1.getLayoutParams();
            if(obj instanceof LayoutParams)
            {
                obj = (LayoutParams)obj;
                if(!((LayoutParams) (obj)).mInsetsDirty)
                {
                    obj = ((LayoutParams) (obj)).mDecorInsets;
                    Rect rect = mTempRect;
                    rect.left = rect.left - ((Rect) (obj)).left;
                    rect = mTempRect;
                    rect.right = rect.right + ((Rect) (obj)).right;
                    rect = mTempRect;
                    rect.top = rect.top - ((Rect) (obj)).top;
                    rect = mTempRect;
                    rect.bottom = rect.bottom + ((Rect) (obj)).bottom;
                }
            }
            offsetDescendantRectToMyCoords(view1, mTempRect);
            offsetRectIntoDescendantCoords(view, mTempRect);
            requestChildRectangleOnScreen(view, mTempRect, mFirstLayoutComplete ^ true);
        }
        super.requestChildFocus(view, view1);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean flag)
    {
        return mLayout.requestChildRectangleOnScreen(this, view, rect, flag);
    }

    public void requestDisallowInterceptTouchEvent(boolean flag)
    {
        int i = mOnItemTouchListeners.size();
        for(int j = 0; j < i; j++)
            ((OnItemTouchListener)mOnItemTouchListeners.get(j)).onRequestDisallowInterceptTouchEvent(flag);

        super.requestDisallowInterceptTouchEvent(flag);
    }

    public void requestLayout()
    {
        if(mEatRequestLayout == 0 && mLayoutFrozen ^ true)
            super.requestLayout();
        else
            mLayoutRequestEaten = true;
    }

    void resumeRequestLayout(boolean flag)
    {
        if(mEatRequestLayout < 1)
            mEatRequestLayout = 1;
        if(!flag)
            mLayoutRequestEaten = false;
        if(mEatRequestLayout == 1)
        {
            if(flag && mLayoutRequestEaten && mLayoutFrozen ^ true && mLayout != null && mAdapter != null)
                dispatchLayout();
            if(!mLayoutFrozen)
                mLayoutRequestEaten = false;
        }
        mEatRequestLayout = mEatRequestLayout - 1;
    }

    void saveOldPositions()
    {
        int i = mChildHelper.getUnfilteredChildCount();
        for(int j = 0; j < i; j++)
        {
            ViewHolder viewholder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(j));
            if(!viewholder.shouldIgnore())
                viewholder.saveOldPosition();
        }

    }

    public void scrollBy(int i, int j)
    {
        if(mLayout == null)
        {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if(mLayoutFrozen)
            return;
        boolean flag = mLayout.canScrollHorizontally();
        boolean flag1 = mLayout.canScrollVertically();
        if(flag || flag1)
        {
            if(!flag)
                i = 0;
            if(!flag1)
                j = 0;
            scrollByInternal(i, j, null);
        }
    }

    boolean scrollByInternal(int i, int j, MotionEvent motionevent)
    {
        int k = 0;
        boolean flag = false;
        int l = 0;
        boolean flag1 = false;
        int i1 = 0;
        boolean flag2 = false;
        int j1 = 0;
        boolean flag3 = false;
        consumePendingUpdateOperations();
        if(mAdapter != null)
        {
            eatRequestLayout();
            onEnterLayoutOrScroll();
            Trace.beginSection("RV Scroll");
            i1 = ((flag2) ? 1 : 0);
            k = ((flag) ? 1 : 0);
            if(i != 0)
            {
                i1 = mLayout.scrollHorizontallyBy(i, mRecycler, mState);
                k = i - i1;
            }
            j1 = ((flag3) ? 1 : 0);
            l = ((flag1) ? 1 : 0);
            if(j != 0)
            {
                j1 = mLayout.scrollVerticallyBy(j, mRecycler, mState);
                l = j - j1;
            }
            Trace.endSection();
            repositionShadowingViews();
            onExitLayoutOrScroll();
            resumeRequestLayout(false);
        }
        if(!mItemDecorations.isEmpty())
            invalidate();
        boolean flag4;
        if(dispatchNestedScroll(i1, j1, k, l, mScrollOffset))
        {
            mLastTouchX = mLastTouchX - mScrollOffset[0];
            mLastTouchY = mLastTouchY - mScrollOffset[1];
            if(motionevent != null)
                motionevent.offsetLocation(mScrollOffset[0], mScrollOffset[1]);
            motionevent = mNestedOffsets;
            motionevent[0] = motionevent[0] + mScrollOffset[0];
            motionevent = mNestedOffsets;
            motionevent[1] = motionevent[1] + mScrollOffset[1];
        } else
        if(getOverScrollMode() != 2)
        {
            if(motionevent != null)
                pullGlows(motionevent.getX(), k, motionevent.getY(), l);
            considerReleasingGlowsOnScroll(i, j);
        }
        if(i1 != 0 || j1 != 0)
            dispatchOnScrolled(i1, j1);
        if(!awakenScrollBars())
            invalidate();
        if(i1 != 0 || j1 != 0)
            flag4 = true;
        else
            flag4 = false;
        return flag4;
    }

    public void scrollTo(int i, int j)
    {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void scrollToPosition(int i)
    {
        if(mLayoutFrozen)
            return;
        stopScroll();
        if(mLayout == null)
        {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        } else
        {
            mLayout.scrollToPosition(i);
            awakenScrollBars();
            return;
        }
    }

    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityevent)
    {
        if(shouldDeferAccessibilityEvent(accessibilityevent))
        {
            return;
        } else
        {
            super.sendAccessibilityEventUnchecked(accessibilityevent);
            return;
        }
    }

    public void setAccessibilityDelegateCompat(RecyclerViewAccessibilityDelegate recyclerviewaccessibilitydelegate)
    {
        mAccessibilityDelegate = recyclerviewaccessibilitydelegate;
        setAccessibilityDelegate(mAccessibilityDelegate);
    }

    public void setAdapter(Adapter adapter)
    {
        setLayoutFrozen(false);
        setAdapterInternal(adapter, false, true);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(ChildDrawingOrderCallback childdrawingordercallback)
    {
        if(childdrawingordercallback == mChildDrawingOrderCallback)
            return;
        mChildDrawingOrderCallback = childdrawingordercallback;
        boolean flag;
        if(mChildDrawingOrderCallback != null)
            flag = true;
        else
            flag = false;
        setChildrenDrawingOrderEnabled(flag);
    }

    boolean setChildImportantForAccessibilityInternal(ViewHolder viewholder, int i)
    {
        if(isComputingLayout())
        {
            viewholder.mPendingAccessibilityState = i;
            mPendingAccessibilityImportanceChange.add(viewholder);
            return false;
        } else
        {
            viewholder.itemView.setImportantForAccessibility(i);
            return true;
        }
    }

    public void setClipToPadding(boolean flag)
    {
        if(flag != mClipToPadding)
            invalidateGlows();
        mClipToPadding = flag;
        super.setClipToPadding(flag);
        if(mFirstLayoutComplete)
            requestLayout();
    }

    void setDataSetChangedAfterLayout()
    {
        if(mDataSetHasChangedAfterLayout)
            return;
        mDataSetHasChangedAfterLayout = true;
        int i = mChildHelper.getUnfilteredChildCount();
        for(int j = 0; j < i; j++)
        {
            ViewHolder viewholder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(j));
            if(viewholder != null && viewholder.shouldIgnore() ^ true)
                viewholder.addFlags(512);
        }

        mRecycler.setAdapterPositionsAsUnknown();
        markKnownViewsInvalid();
    }

    public void setHasFixedSize(boolean flag)
    {
        mHasFixedSize = flag;
    }

    public void setItemAnimator(ItemAnimator itemanimator)
    {
        if(mItemAnimator != null)
        {
            mItemAnimator.endAnimations();
            mItemAnimator.setListener(null);
        }
        mItemAnimator = itemanimator;
        if(mItemAnimator != null)
            mItemAnimator.setListener(mItemAnimatorListener);
    }

    public void setItemViewCacheSize(int i)
    {
        mRecycler.setViewCacheSize(i);
    }

    public void setLayoutFrozen(boolean flag)
    {
        if(flag != mLayoutFrozen)
        {
            assertNotInLayoutOrScroll("Do not setLayoutFrozen in layout or scroll");
            if(!flag)
            {
                mLayoutFrozen = false;
                if(mLayoutRequestEaten && mLayout != null && mAdapter != null)
                    requestLayout();
                mLayoutRequestEaten = false;
            } else
            {
                long l = SystemClock.uptimeMillis();
                onTouchEvent(MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0));
                mLayoutFrozen = true;
                mIgnoreMotionEventTillDown = true;
                stopScroll();
            }
        }
    }

    public void setLayoutManager(LayoutManager layoutmanager)
    {
        if(layoutmanager == mLayout)
            return;
        stopScroll();
        if(mLayout != null)
        {
            if(mItemAnimator != null)
                mItemAnimator.endAnimations();
            mLayout.removeAndRecycleAllViews(mRecycler);
            mLayout.removeAndRecycleScrapInt(mRecycler);
            mRecycler.clear();
            if(mIsAttached)
                mLayout.dispatchDetachedFromWindow(this, mRecycler);
            mLayout.setRecyclerView(null);
            mLayout = null;
        } else
        {
            mRecycler.clear();
        }
        mChildHelper.removeAllViewsUnfiltered();
        mLayout = layoutmanager;
        if(layoutmanager != null)
        {
            if(layoutmanager.mRecyclerView != null)
                throw new IllegalArgumentException((new StringBuilder()).append("LayoutManager ").append(layoutmanager).append(" is already attached to a RecyclerView: ").append(layoutmanager.mRecyclerView).toString());
            mLayout.setRecyclerView(this);
            if(mIsAttached)
                mLayout.dispatchAttachedToWindow(this);
        }
        mRecycler.updateViewCacheSize();
        requestLayout();
    }

    public void setOnFlingListener(OnFlingListener onflinglistener)
    {
        mOnFlingListener = onflinglistener;
    }

    public void setOnScrollListener(OnScrollListener onscrolllistener)
    {
        mScrollListener = onscrolllistener;
    }

    public void setPreserveFocusAfterLayout(boolean flag)
    {
        mPreserveFocusAfterLayout = flag;
    }

    public void setRecycledViewPool(RecycledViewPool recycledviewpool)
    {
        mRecycler.setRecycledViewPool(recycledviewpool);
    }

    public void setRecyclerListener(RecyclerListener recyclerlistener)
    {
        mRecyclerListener = recyclerlistener;
    }

    void setScrollState(int i)
    {
        if(i == mScrollState)
            return;
        mScrollState = i;
        if(i != 2)
            stopScrollersInternal();
        dispatchOnScrollStateChanged(i);
    }

    public void setScrollingTouchSlop(int i)
    {
        ViewConfiguration viewconfiguration = ViewConfiguration.get(getContext());
        i;
        JVM INSTR tableswitch 0 1: default 32
    //                   0 64
    //                   1 73;
           goto _L1 _L2 _L3
_L1:
        Log.w("RecyclerView", (new StringBuilder()).append("setScrollingTouchSlop(): bad argument constant ").append(i).append("; using default value").toString());
_L2:
        mTouchSlop = viewconfiguration.getScaledTouchSlop();
_L5:
        return;
_L3:
        mTouchSlop = viewconfiguration.getScaledPagingTouchSlop();
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void setViewCacheExtension(ViewCacheExtension viewcacheextension)
    {
        mRecycler.setViewCacheExtension(viewcacheextension);
    }

    boolean shouldDeferAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        if(isComputingLayout())
        {
            int i = 0;
            if(accessibilityevent != null)
                i = accessibilityevent.getContentChangeTypes();
            int j = i;
            if(i == 0)
                j = 0;
            mEatenAccessibilityChangeFlags = mEatenAccessibilityChangeFlags | j;
            return true;
        } else
        {
            return false;
        }
    }

    public void smoothScrollBy(int i, int j)
    {
        smoothScrollBy(i, j, null);
    }

    public void smoothScrollBy(int i, int j, Interpolator interpolator)
    {
        if(mLayout == null)
        {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if(mLayoutFrozen)
            return;
        if(!mLayout.canScrollHorizontally())
            i = 0;
        if(!mLayout.canScrollVertically())
            j = 0;
        if(i != 0 || j != 0)
            mViewFlinger.smoothScrollBy(i, j, interpolator);
    }

    public void smoothScrollToPosition(int i)
    {
        if(mLayoutFrozen)
            return;
        if(mLayout == null)
        {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        } else
        {
            mLayout.smoothScrollToPosition(this, mState, i);
            return;
        }
    }

    public void stopScroll()
    {
        setScrollState(0);
        stopScrollersInternal();
    }

    public void swapAdapter(Adapter adapter, boolean flag)
    {
        setLayoutFrozen(false);
        setAdapterInternal(adapter, true, flag);
        setDataSetChangedAfterLayout();
        requestLayout();
    }

    void viewRangeUpdate(int i, int j, Object obj)
    {
        int k = mChildHelper.getUnfilteredChildCount();
        int l = 0;
        do
        {
            if(l >= k)
                break;
            View view = mChildHelper.getUnfilteredChildAt(l);
            ViewHolder viewholder = getChildViewHolderInt(view);
            if(viewholder != null && !viewholder.shouldIgnore() && viewholder.mPosition >= i && viewholder.mPosition < i + j)
            {
                viewholder.addFlags(2);
                viewholder.addChangePayload(obj);
                ((LayoutParams)view.getLayoutParams()).mInsetsDirty = true;
            }
            l++;
        } while(true);
        mRecycler.viewRangeUpdate(i, j);
    }

    static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC;
    private static final boolean ALLOW_THREAD_GAP_WORK;
    private static final int CLIP_TO_PADDING_ATTR[] = {
        0x10100eb
    };
    static final boolean DEBUG = false;
    static final boolean DISPATCH_TEMP_DETACH = false;
    private static final boolean FORCE_ABS_FOCUS_SEARCH_DIRECTION;
    static final boolean FORCE_INVALIDATE_DISPLAY_LIST;
    static final long FOREVER_NS = 0x7fffffffffffffffL;
    public static final int HORIZONTAL = 0;
    private static final boolean IGNORE_DETACHED_FOCUSED_CHILD;
    private static final int INVALID_POINTER = -1;
    public static final int INVALID_TYPE = -1;
    private static final Class LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE[];
    static final int MAX_SCROLL_DURATION = 2000;
    private static final int NESTED_SCROLLING_ATTRS[] = {
        0x1010436
    };
    public static final long NO_ID = -1L;
    public static final int NO_POSITION = -1;
    static final boolean POST_UPDATES_ON_ANIMATION;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    static final String TAG = "RecyclerView";
    public static final int TOUCH_SLOP_DEFAULT = 0;
    public static final int TOUCH_SLOP_PAGING = 1;
    static final String TRACE_BIND_VIEW_TAG = "RV OnBindView";
    static final String TRACE_CREATE_VIEW_TAG = "RV CreateView";
    private static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG = "RV PartialInvalidate";
    static final String TRACE_NESTED_PREFETCH_TAG = "RV Nested Prefetch";
    private static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG = "RV FullInvalidate";
    private static final String TRACE_ON_LAYOUT_TAG = "RV OnLayout";
    static final String TRACE_PREFETCH_TAG = "RV Prefetch";
    static final String TRACE_SCROLL_TAG = "RV Scroll";
    public static final int VERTICAL = 1;
    static final Interpolator sQuinticInterpolator = new Interpolator() {

        public float getInterpolation(float f)
        {
            f--;
            return f * f * f * f * f + 1.0F;
        }

    }
;
    RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
    private final AccessibilityManager mAccessibilityManager;
    private OnItemTouchListener mActiveOnItemTouchListener;
    Adapter mAdapter;
    AdapterHelper mAdapterHelper;
    boolean mAdapterUpdateDuringMeasure;
    private EdgeEffect mBottomGlow;
    private ChildDrawingOrderCallback mChildDrawingOrderCallback;
    ChildHelper mChildHelper;
    boolean mClipToPadding;
    boolean mDataSetHasChangedAfterLayout;
    private int mDispatchScrollCounter;
    private int mEatRequestLayout;
    private int mEatenAccessibilityChangeFlags;
    boolean mFirstLayoutComplete;
    GapWorker mGapWorker;
    boolean mHasFixedSize;
    private boolean mIgnoreMotionEventTillDown;
    private int mInitialTouchX;
    private int mInitialTouchY;
    boolean mIsAttached;
    ItemAnimator mItemAnimator;
    private ItemAnimator.ItemAnimatorListener mItemAnimatorListener;
    private Runnable mItemAnimatorRunner = new Runnable() {

        public void run()
        {
            if(mItemAnimator != null)
                mItemAnimator.runPendingAnimations();
            mPostedAnimatorRunner = false;
        }

        final RecyclerView this$0;

            
            {
                this$0 = RecyclerView.this;
                super();
            }
    }
;
    final ArrayList mItemDecorations;
    boolean mItemsAddedOrRemoved;
    boolean mItemsChanged;
    private int mLastTouchX;
    private int mLastTouchY;
    LayoutManager mLayout;
    boolean mLayoutFrozen;
    private int mLayoutOrScrollCounter;
    boolean mLayoutRequestEaten;
    private EdgeEffect mLeftGlow;
    private final int mMaxFlingVelocity;
    private final int mMinFlingVelocity;
    private final int mMinMaxLayoutPositions[];
    private final int mNestedOffsets[];
    private final RecyclerViewDataObserver mObserver;
    private List mOnChildAttachStateListeners;
    private OnFlingListener mOnFlingListener;
    private final ArrayList mOnItemTouchListeners;
    final List mPendingAccessibilityImportanceChange;
    private SavedState mPendingSavedState;
    boolean mPostedAnimatorRunner;
    GapWorker.LayoutPrefetchRegistryImpl mPrefetchRegistry;
    private boolean mPreserveFocusAfterLayout;
    final Recycler mRecycler;
    RecyclerListener mRecyclerListener;
    private EdgeEffect mRightGlow;
    private final int mScrollConsumed[];
    private float mScrollFactor;
    private OnScrollListener mScrollListener;
    private List mScrollListeners;
    private final int mScrollOffset[];
    private int mScrollPointerId;
    private int mScrollState;
    final State mState;
    final Rect mTempRect;
    private final Rect mTempRect2;
    final RectF mTempRectF;
    private EdgeEffect mTopGlow;
    private int mTouchSlop;
    final Runnable mUpdateChildViewsRunnable;
    private VelocityTracker mVelocityTracker;
    final ViewFlinger mViewFlinger;
    private final ViewInfoStore.ProcessCallback mViewInfoProcessCallback;
    final ViewInfoStore mViewInfoStore;

    static 
    {
        boolean flag;
        if(android.os.Build.VERSION.SDK_INT == 18 || android.os.Build.VERSION.SDK_INT == 19)
            flag = true;
        else
        if(android.os.Build.VERSION.SDK_INT == 20)
            flag = true;
        else
            flag = false;
        FORCE_INVALIDATE_DISPLAY_LIST = flag;
        if(android.os.Build.VERSION.SDK_INT >= 23)
            flag = true;
        else
            flag = false;
        ALLOW_SIZE_IN_UNSPECIFIED_SPEC = flag;
        if(android.os.Build.VERSION.SDK_INT >= 16)
            flag = true;
        else
            flag = false;
        POST_UPDATES_ON_ANIMATION = flag;
        if(android.os.Build.VERSION.SDK_INT >= 21)
            flag = true;
        else
            flag = false;
        ALLOW_THREAD_GAP_WORK = flag;
        if(android.os.Build.VERSION.SDK_INT <= 15)
            flag = true;
        else
            flag = false;
        FORCE_ABS_FOCUS_SEARCH_DIRECTION = flag;
        if(android.os.Build.VERSION.SDK_INT <= 15)
            flag = true;
        else
            flag = false;
        IGNORE_DETACHED_FOCUSED_CHILD = flag;
        LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = (new Class[] {
            android/content/Context, android/util/AttributeSet, Integer.TYPE, Integer.TYPE
        });
    }
}

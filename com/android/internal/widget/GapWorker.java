// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.os.Trace;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.android.internal.widget:
//            RecyclerView, ChildHelper, AdapterHelper

final class GapWorker
    implements Runnable
{
    static class LayoutPrefetchRegistryImpl
        implements RecyclerView.LayoutManager.LayoutPrefetchRegistry
    {

        public void addPosition(int i, int j)
        {
            int k;
            if(j < 0)
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            k = mCount * 2;
            if(mPrefetchArray != null) goto _L2; else goto _L1
_L1:
            mPrefetchArray = new int[4];
            Arrays.fill(mPrefetchArray, -1);
_L4:
            mPrefetchArray[k] = i;
            mPrefetchArray[k + 1] = j;
            mCount = mCount + 1;
            return;
_L2:
            if(k >= mPrefetchArray.length)
            {
                int ai[] = mPrefetchArray;
                mPrefetchArray = new int[k * 2];
                System.arraycopy(ai, 0, mPrefetchArray, 0, ai.length);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        void clearPrefetchPositions()
        {
            if(mPrefetchArray != null)
                Arrays.fill(mPrefetchArray, -1);
        }

        void collectPrefetchPositionsFromView(RecyclerView recyclerview, boolean flag)
        {
            RecyclerView.LayoutManager layoutmanager;
            mCount = 0;
            if(mPrefetchArray != null)
                Arrays.fill(mPrefetchArray, -1);
            layoutmanager = recyclerview.mLayout;
            if(recyclerview.mAdapter == null || layoutmanager == null || !layoutmanager.isItemPrefetchEnabled()) goto _L2; else goto _L1
_L1:
            if(!flag) goto _L4; else goto _L3
_L3:
            if(!recyclerview.mAdapterHelper.hasPendingUpdates())
                layoutmanager.collectInitialPrefetchPositions(recyclerview.mAdapter.getItemCount(), this);
_L6:
            if(mCount > layoutmanager.mPrefetchMaxCountObserved)
            {
                layoutmanager.mPrefetchMaxCountObserved = mCount;
                layoutmanager.mPrefetchMaxObservedInInitialPrefetch = flag;
                recyclerview.mRecycler.updateViewCacheSize();
            }
_L2:
            return;
_L4:
            if(!recyclerview.hasPendingAdapterUpdates())
                layoutmanager.collectAdjacentPrefetchPositions(mPrefetchDx, mPrefetchDy, recyclerview.mState, this);
            if(true) goto _L6; else goto _L5
_L5:
        }

        boolean lastPrefetchIncludedPosition(int i)
        {
            if(mPrefetchArray != null)
            {
                int j = mCount;
                for(int k = 0; k < j * 2; k += 2)
                    if(mPrefetchArray[k] == i)
                        return true;

            }
            return false;
        }

        void setPrefetchVector(int i, int j)
        {
            mPrefetchDx = i;
            mPrefetchDy = j;
        }

        int mCount;
        int mPrefetchArray[];
        int mPrefetchDx;
        int mPrefetchDy;

        LayoutPrefetchRegistryImpl()
        {
        }
    }

    static class Task
    {

        public void clear()
        {
            immediate = false;
            viewVelocity = 0;
            distanceToItem = 0;
            view = null;
            position = 0;
        }

        public int distanceToItem;
        public boolean immediate;
        public int position;
        public RecyclerView view;
        public int viewVelocity;

        Task()
        {
        }
    }


    GapWorker()
    {
        mRecyclerViews = new ArrayList();
        mTasks = new ArrayList();
    }

    private void buildTaskList()
    {
        int i = mRecyclerViews.size();
        int j = 0;
        for(int l = 0; l < i; l++)
        {
            RecyclerView recyclerview = (RecyclerView)mRecyclerViews.get(l);
            recyclerview.mPrefetchRegistry.collectPrefetchPositionsFromView(recyclerview, false);
            j += recyclerview.mPrefetchRegistry.mCount;
        }

        mTasks.ensureCapacity(j);
        int i1 = 0;
        for(int k = 0; k < i; k++)
        {
            RecyclerView recyclerview1 = (RecyclerView)mRecyclerViews.get(k);
            LayoutPrefetchRegistryImpl layoutprefetchregistryimpl = recyclerview1.mPrefetchRegistry;
            int j1 = Math.abs(layoutprefetchregistryimpl.mPrefetchDx) + Math.abs(layoutprefetchregistryimpl.mPrefetchDy);
            int k1 = 0;
            while(k1 < layoutprefetchregistryimpl.mCount * 2) 
            {
                Task task;
                int l1;
                boolean flag;
                if(i1 >= mTasks.size())
                {
                    task = new Task();
                    mTasks.add(task);
                } else
                {
                    task = (Task)mTasks.get(i1);
                }
                l1 = layoutprefetchregistryimpl.mPrefetchArray[k1 + 1];
                if(l1 <= j1)
                    flag = true;
                else
                    flag = false;
                task.immediate = flag;
                task.viewVelocity = j1;
                task.distanceToItem = l1;
                task.view = recyclerview1;
                task.position = layoutprefetchregistryimpl.mPrefetchArray[k1];
                i1++;
                k1 += 2;
            }
        }

        Collections.sort(mTasks, sTaskComparator);
    }

    private void flushTaskWithDeadline(Task task, long l)
    {
        long l1;
        if(task.immediate)
            l1 = 0x7fffffffffffffffL;
        else
            l1 = l;
        task = prefetchPositionWithDeadline(task.view, task.position, l1);
        if(task != null && ((RecyclerView.ViewHolder) (task)).mNestedRecyclerView != null)
            prefetchInnerRecyclerViewWithDeadline((RecyclerView)((RecyclerView.ViewHolder) (task)).mNestedRecyclerView.get(), l);
    }

    private void flushTasksWithDeadline(long l)
    {
        int i = 0;
        do
        {
            Task task;
label0:
            {
                if(i < mTasks.size())
                {
                    task = (Task)mTasks.get(i);
                    if(task.view != null)
                        break label0;
                }
                return;
            }
            flushTaskWithDeadline(task, l);
            task.clear();
            i++;
        } while(true);
    }

    static boolean isPrefetchPositionAttached(RecyclerView recyclerview, int i)
    {
        int j = recyclerview.mChildHelper.getUnfilteredChildCount();
        for(int k = 0; k < j; k++)
        {
            RecyclerView.ViewHolder viewholder = RecyclerView.getChildViewHolderInt(recyclerview.mChildHelper.getUnfilteredChildAt(k));
            if(viewholder.mPosition == i && viewholder.isInvalid() ^ true)
                return true;
        }

        return false;
    }

    private void prefetchInnerRecyclerViewWithDeadline(RecyclerView recyclerview, long l)
    {
        LayoutPrefetchRegistryImpl layoutprefetchregistryimpl;
        if(recyclerview == null)
            return;
        if(recyclerview.mDataSetHasChangedAfterLayout && recyclerview.mChildHelper.getUnfilteredChildCount() != 0)
            recyclerview.removeAndRecycleViews();
        layoutprefetchregistryimpl = recyclerview.mPrefetchRegistry;
        layoutprefetchregistryimpl.collectPrefetchPositionsFromView(recyclerview, true);
        if(layoutprefetchregistryimpl.mCount == 0)
            break MISSING_BLOCK_LABEL_102;
        Trace.beginSection("RV Nested Prefetch");
        recyclerview.mState.prepareForNestedPrefetch(recyclerview.mAdapter);
        int i = 0;
_L2:
        if(i >= layoutprefetchregistryimpl.mCount * 2)
            break; /* Loop/switch isn't completed */
        prefetchPositionWithDeadline(recyclerview, layoutprefetchregistryimpl.mPrefetchArray[i], l);
        i += 2;
        if(true) goto _L2; else goto _L1
_L1:
        Trace.endSection();
        return;
        recyclerview;
        Trace.endSection();
        throw recyclerview;
    }

    private RecyclerView.ViewHolder prefetchPositionWithDeadline(RecyclerView recyclerview, int i, long l)
    {
        if(isPrefetchPositionAttached(recyclerview, i))
            return null;
        recyclerview = recyclerview.mRecycler;
        RecyclerView.ViewHolder viewholder = recyclerview.tryGetViewHolderForPositionByDeadline(i, false, l);
        if(viewholder != null)
            if(viewholder.isBound())
                recyclerview.recycleView(viewholder.itemView);
            else
                recyclerview.addViewHolderToRecycledViewPool(viewholder, false);
        return viewholder;
    }

    public void add(RecyclerView recyclerview)
    {
        mRecyclerViews.add(recyclerview);
    }

    void postFromTraversal(RecyclerView recyclerview, int i, int j)
    {
        if(recyclerview.isAttachedToWindow() && mPostTimeNs == 0L)
        {
            mPostTimeNs = recyclerview.getNanoTime();
            recyclerview.post(this);
        }
        recyclerview.mPrefetchRegistry.setPrefetchVector(i, j);
    }

    void prefetch(long l)
    {
        buildTaskList();
        flushTasksWithDeadline(l);
    }

    public void remove(RecyclerView recyclerview)
    {
        mRecyclerViews.remove(recyclerview);
    }

    public void run()
    {
        boolean flag;
        Trace.beginSection("RV Prefetch");
        flag = mRecyclerViews.isEmpty();
        if(flag)
        {
            mPostTimeNs = 0L;
            Trace.endSection();
            return;
        }
        long l = TimeUnit.MILLISECONDS.toNanos(((RecyclerView)mRecyclerViews.get(0)).getDrawingTime());
        if(l == 0L)
        {
            mPostTimeNs = 0L;
            Trace.endSection();
            return;
        }
        prefetch(l + mFrameIntervalNs);
        mPostTimeNs = 0L;
        Trace.endSection();
        return;
        Exception exception;
        exception;
        mPostTimeNs = 0L;
        Trace.endSection();
        throw exception;
    }

    static final ThreadLocal sGapWorker = new ThreadLocal();
    static Comparator sTaskComparator = new Comparator() {

        public int compare(Task task, Task task1)
        {
            byte byte0 = -1;
            boolean flag = true;
            int i;
            boolean flag1;
            if(task.view == null)
                i = 1;
            else
                i = 0;
            if(task1.view == null)
                flag1 = true;
            else
                flag1 = false;
            if(i != flag1)
            {
                if(task.view == null)
                    i = ((flag) ? 1 : 0);
                else
                    i = -1;
                return i;
            }
            if(task.immediate != task1.immediate)
            {
                if(task.immediate)
                    i = byte0;
                else
                    i = 1;
                return i;
            }
            i = task1.viewVelocity - task.viewVelocity;
            if(i != 0)
                return i;
            i = task.distanceToItem - task1.distanceToItem;
            if(i != 0)
                return i;
            else
                return 0;
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((Task)obj, (Task)obj1);
        }

    }
;
    long mFrameIntervalNs;
    long mPostTimeNs;
    ArrayList mRecyclerViews;
    private ArrayList mTasks;

}

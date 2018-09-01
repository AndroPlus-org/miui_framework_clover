// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.util.ArrayMap;
import android.util.LongSparseArray;

class ViewInfoStore
{
    static class InfoRecord
    {

        static void drainCache()
        {
            while(sPool.acquire() != null) ;
        }

        static InfoRecord obtain()
        {
            InfoRecord inforecord = (InfoRecord)sPool.acquire();
            InfoRecord inforecord1 = inforecord;
            if(inforecord == null)
                inforecord1 = new InfoRecord();
            return inforecord1;
        }

        static void recycle(InfoRecord inforecord)
        {
            inforecord.flags = 0;
            inforecord.preInfo = null;
            inforecord.postInfo = null;
            sPool.release(inforecord);
        }

        static final int FLAG_APPEAR = 2;
        static final int FLAG_APPEAR_AND_DISAPPEAR = 3;
        static final int FLAG_APPEAR_PRE_AND_POST = 14;
        static final int FLAG_DISAPPEARED = 1;
        static final int FLAG_POST = 8;
        static final int FLAG_PRE = 4;
        static final int FLAG_PRE_AND_POST = 12;
        static android.util.Pools.Pool sPool = new android.util.Pools.SimplePool(20);
        int flags;
        RecyclerView.ItemAnimator.ItemHolderInfo postInfo;
        RecyclerView.ItemAnimator.ItemHolderInfo preInfo;


        private InfoRecord()
        {
        }
    }

    static interface ProcessCallback
    {

        public abstract void processAppeared(RecyclerView.ViewHolder viewholder, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo1);

        public abstract void processDisappeared(RecyclerView.ViewHolder viewholder, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo1);

        public abstract void processPersistent(RecyclerView.ViewHolder viewholder, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo1);

        public abstract void unused(RecyclerView.ViewHolder viewholder);
    }


    ViewInfoStore()
    {
    }

    private RecyclerView.ItemAnimator.ItemHolderInfo popFromLayoutStep(RecyclerView.ViewHolder viewholder, int i)
    {
        int j = mLayoutHolderMap.indexOfKey(viewholder);
        if(j < 0)
            return null;
        InfoRecord inforecord = (InfoRecord)mLayoutHolderMap.valueAt(j);
        if(inforecord != null && (inforecord.flags & i) != 0)
        {
            inforecord.flags = inforecord.flags & i;
            if(i == 4)
                viewholder = inforecord.preInfo;
            else
            if(i == 8)
                viewholder = inforecord.postInfo;
            else
                throw new IllegalArgumentException("Must provide flag PRE or POST");
            if((inforecord.flags & 0xc) == 0)
            {
                mLayoutHolderMap.removeAt(j);
                InfoRecord.recycle(inforecord);
            }
            return viewholder;
        } else
        {
            return null;
        }
    }

    void addToAppearedInPreLayoutHolders(RecyclerView.ViewHolder viewholder, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo)
    {
        InfoRecord inforecord = (InfoRecord)mLayoutHolderMap.get(viewholder);
        InfoRecord inforecord1 = inforecord;
        if(inforecord == null)
        {
            inforecord1 = InfoRecord.obtain();
            mLayoutHolderMap.put(viewholder, inforecord1);
        }
        inforecord1.flags = inforecord1.flags | 2;
        inforecord1.preInfo = itemholderinfo;
    }

    void addToDisappearedInLayout(RecyclerView.ViewHolder viewholder)
    {
        InfoRecord inforecord = (InfoRecord)mLayoutHolderMap.get(viewholder);
        InfoRecord inforecord1 = inforecord;
        if(inforecord == null)
        {
            inforecord1 = InfoRecord.obtain();
            mLayoutHolderMap.put(viewholder, inforecord1);
        }
        inforecord1.flags = inforecord1.flags | 1;
    }

    void addToOldChangeHolders(long l, RecyclerView.ViewHolder viewholder)
    {
        mOldChangedHolders.put(l, viewholder);
    }

    void addToPostLayout(RecyclerView.ViewHolder viewholder, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo)
    {
        InfoRecord inforecord = (InfoRecord)mLayoutHolderMap.get(viewholder);
        InfoRecord inforecord1 = inforecord;
        if(inforecord == null)
        {
            inforecord1 = InfoRecord.obtain();
            mLayoutHolderMap.put(viewholder, inforecord1);
        }
        inforecord1.postInfo = itemholderinfo;
        inforecord1.flags = inforecord1.flags | 8;
    }

    void addToPreLayout(RecyclerView.ViewHolder viewholder, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo)
    {
        InfoRecord inforecord = (InfoRecord)mLayoutHolderMap.get(viewholder);
        InfoRecord inforecord1 = inforecord;
        if(inforecord == null)
        {
            inforecord1 = InfoRecord.obtain();
            mLayoutHolderMap.put(viewholder, inforecord1);
        }
        inforecord1.preInfo = itemholderinfo;
        inforecord1.flags = inforecord1.flags | 4;
    }

    void clear()
    {
        mLayoutHolderMap.clear();
        mOldChangedHolders.clear();
    }

    RecyclerView.ViewHolder getFromOldChangeHolders(long l)
    {
        return (RecyclerView.ViewHolder)mOldChangedHolders.get(l);
    }

    boolean isDisappearing(RecyclerView.ViewHolder viewholder)
    {
        boolean flag = false;
        viewholder = (InfoRecord)mLayoutHolderMap.get(viewholder);
        boolean flag1 = flag;
        if(viewholder != null)
        {
            flag1 = flag;
            if((((InfoRecord) (viewholder)).flags & 1) != 0)
                flag1 = true;
        }
        return flag1;
    }

    boolean isInPreLayout(RecyclerView.ViewHolder viewholder)
    {
        boolean flag = false;
        viewholder = (InfoRecord)mLayoutHolderMap.get(viewholder);
        boolean flag1 = flag;
        if(viewholder != null)
        {
            flag1 = flag;
            if((((InfoRecord) (viewholder)).flags & 4) != 0)
                flag1 = true;
        }
        return flag1;
    }

    void onDetach()
    {
        InfoRecord.drainCache();
    }

    public void onViewDetached(RecyclerView.ViewHolder viewholder)
    {
        removeFromDisappearedInLayout(viewholder);
    }

    RecyclerView.ItemAnimator.ItemHolderInfo popFromPostLayout(RecyclerView.ViewHolder viewholder)
    {
        return popFromLayoutStep(viewholder, 8);
    }

    RecyclerView.ItemAnimator.ItemHolderInfo popFromPreLayout(RecyclerView.ViewHolder viewholder)
    {
        return popFromLayoutStep(viewholder, 4);
    }

    void process(ProcessCallback processcallback)
    {
        int i = mLayoutHolderMap.size() - 1;
        while(i >= 0) 
        {
            RecyclerView.ViewHolder viewholder = (RecyclerView.ViewHolder)mLayoutHolderMap.keyAt(i);
            InfoRecord inforecord = (InfoRecord)mLayoutHolderMap.removeAt(i);
            int j;
            if((inforecord.flags & 3) == 3)
                processcallback.unused(viewholder);
            else
            if((inforecord.flags & 1) != 0)
            {
                if(inforecord.preInfo == null)
                    processcallback.unused(viewholder);
                else
                    processcallback.processDisappeared(viewholder, inforecord.preInfo, inforecord.postInfo);
            } else
            if((inforecord.flags & 0xe) == 14)
                processcallback.processAppeared(viewholder, inforecord.preInfo, inforecord.postInfo);
            else
            if((inforecord.flags & 0xc) == 12)
                processcallback.processPersistent(viewholder, inforecord.preInfo, inforecord.postInfo);
            else
            if((inforecord.flags & 4) != 0)
                processcallback.processDisappeared(viewholder, inforecord.preInfo, null);
            else
            if((inforecord.flags & 8) != 0)
                processcallback.processAppeared(viewholder, inforecord.preInfo, inforecord.postInfo);
            else
                j = inforecord.flags;
            InfoRecord.recycle(inforecord);
            i--;
        }
    }

    void removeFromDisappearedInLayout(RecyclerView.ViewHolder viewholder)
    {
        viewholder = (InfoRecord)mLayoutHolderMap.get(viewholder);
        if(viewholder == null)
        {
            return;
        } else
        {
            viewholder.flags = ((InfoRecord) (viewholder)).flags & -2;
            return;
        }
    }

    void removeViewHolder(RecyclerView.ViewHolder viewholder)
    {
        int i = mOldChangedHolders.size() - 1;
        do
        {
label0:
            {
                if(i >= 0)
                {
                    if(viewholder != mOldChangedHolders.valueAt(i))
                        break label0;
                    mOldChangedHolders.removeAt(i);
                }
                viewholder = (InfoRecord)mLayoutHolderMap.remove(viewholder);
                if(viewholder != null)
                    InfoRecord.recycle(viewholder);
                return;
            }
            i--;
        } while(true);
    }

    private static final boolean DEBUG = false;
    final ArrayMap mLayoutHolderMap = new ArrayMap();
    final LongSparseArray mOldChangedHolders = new LongSparseArray();
}

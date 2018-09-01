// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import java.util.*;

// Referenced classes of package com.android.internal.widget:
//            OpReorderer

class AdapterHelper
    implements OpReorderer.Callback
{
    static interface Callback
    {

        public abstract RecyclerView.ViewHolder findViewHolder(int i);

        public abstract void markViewHoldersUpdated(int i, int j, Object obj);

        public abstract void offsetPositionsForAdd(int i, int j);

        public abstract void offsetPositionsForMove(int i, int j);

        public abstract void offsetPositionsForRemovingInvisible(int i, int j);

        public abstract void offsetPositionsForRemovingLaidOutOrNewView(int i, int j);

        public abstract void onDispatchFirstPass(UpdateOp updateop);

        public abstract void onDispatchSecondPass(UpdateOp updateop);
    }

    static class UpdateOp
    {

        String cmdToString()
        {
            switch(cmd)
            {
            case 3: // '\003'
            case 5: // '\005'
            case 6: // '\006'
            case 7: // '\007'
            default:
                return "??";

            case 1: // '\001'
                return "add";

            case 2: // '\002'
                return "rm";

            case 4: // '\004'
                return "up";

            case 8: // '\b'
                return "mv";
            }
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null || getClass() != obj.getClass())
                return false;
            obj = (UpdateOp)obj;
            if(cmd != ((UpdateOp) (obj)).cmd)
                return false;
            if(cmd == 8 && Math.abs(itemCount - positionStart) == 1 && itemCount == ((UpdateOp) (obj)).positionStart && positionStart == ((UpdateOp) (obj)).itemCount)
                return true;
            if(itemCount != ((UpdateOp) (obj)).itemCount)
                return false;
            if(positionStart != ((UpdateOp) (obj)).positionStart)
                return false;
            if(payload != null)
            {
                if(!payload.equals(((UpdateOp) (obj)).payload))
                    return false;
            } else
            if(((UpdateOp) (obj)).payload != null)
                return false;
            return true;
        }

        public int hashCode()
        {
            return (cmd * 31 + positionStart) * 31 + itemCount;
        }

        public String toString()
        {
            return (new StringBuilder()).append(Integer.toHexString(System.identityHashCode(this))).append("[").append(cmdToString()).append(",s:").append(positionStart).append("c:").append(itemCount).append(",p:").append(payload).append("]").toString();
        }

        static final int ADD = 1;
        static final int MOVE = 8;
        static final int POOL_SIZE = 30;
        static final int REMOVE = 2;
        static final int UPDATE = 4;
        int cmd;
        int itemCount;
        Object payload;
        int positionStart;

        UpdateOp(int i, int j, int k, Object obj)
        {
            cmd = i;
            positionStart = j;
            itemCount = k;
            payload = obj;
        }
    }


    AdapterHelper(Callback callback)
    {
        this(callback, false);
    }

    AdapterHelper(Callback callback, boolean flag)
    {
        mUpdateOpPool = new android.util.Pools.SimplePool(30);
        mPendingUpdates = new ArrayList();
        mPostponedList = new ArrayList();
        mExistingUpdateTypes = 0;
        mCallback = callback;
        mDisableRecycler = flag;
        mOpReorderer = new OpReorderer(this);
    }

    private void applyAdd(UpdateOp updateop)
    {
        postponeAndUpdateViewHolders(updateop);
    }

    private void applyMove(UpdateOp updateop)
    {
        postponeAndUpdateViewHolders(updateop);
    }

    private void applyRemove(UpdateOp updateop)
    {
        int i = updateop.positionStart;
        int j = 0;
        int k = updateop.positionStart + updateop.itemCount;
        byte byte0 = -1;
        int l = updateop.positionStart;
        while(l < k) 
        {
            int i1 = 0;
            byte byte1 = 0;
            if(mCallback.findViewHolder(l) != null || canFindInPreLayout(l))
            {
                if(byte0 == 0)
                {
                    dispatchAndUpdateViewHolders(obtainUpdateOp(2, i, j, null));
                    byte1 = 1;
                }
                byte0 = 1;
                i1 = byte1;
                byte1 = byte0;
            } else
            {
                if(byte0 == 1)
                {
                    postponeAndUpdateViewHolders(obtainUpdateOp(2, i, j, null));
                    i1 = 1;
                }
                byte1 = 0;
            }
            if(i1 != 0)
            {
                l -= j;
                k -= j;
                i1 = 1;
            } else
            {
                i1 = j + 1;
            }
            l++;
            j = i1;
            byte0 = byte1;
        }
        UpdateOp updateop1 = updateop;
        if(j != updateop.itemCount)
        {
            recycleUpdateOp(updateop);
            updateop1 = obtainUpdateOp(2, i, j, null);
        }
        if(byte0 == 0)
            dispatchAndUpdateViewHolders(updateop1);
        else
            postponeAndUpdateViewHolders(updateop1);
    }

    private void applyUpdate(UpdateOp updateop)
    {
        int i = updateop.positionStart;
        int j = 0;
        int k = updateop.positionStart;
        int l = updateop.itemCount;
        byte byte0 = -1;
        int i1 = updateop.positionStart;
        while(i1 < k + l) 
        {
            int k1;
            int l1;
            if(mCallback.findViewHolder(i1) != null || canFindInPreLayout(i1))
            {
                int j1 = j;
                k1 = i;
                if(byte0 == 0)
                {
                    dispatchAndUpdateViewHolders(obtainUpdateOp(4, i, j, updateop.payload));
                    j1 = 0;
                    k1 = i1;
                }
                l1 = 1;
                i = k1;
                k1 = j1;
            } else
            {
                k1 = j;
                l1 = i;
                if(byte0 == 1)
                {
                    postponeAndUpdateViewHolders(obtainUpdateOp(4, i, j, updateop.payload));
                    k1 = 0;
                    l1 = i1;
                }
                j = 0;
                i = l1;
                l1 = j;
            }
            j = k1 + 1;
            i1++;
            byte0 = l1;
        }
        Object obj = updateop;
        if(j != updateop.itemCount)
        {
            obj = updateop.payload;
            recycleUpdateOp(updateop);
            obj = obtainUpdateOp(4, i, j, obj);
        }
        if(byte0 == 0)
            dispatchAndUpdateViewHolders(((UpdateOp) (obj)));
        else
            postponeAndUpdateViewHolders(((UpdateOp) (obj)));
    }

    private boolean canFindInPreLayout(int i)
    {
        int j = mPostponedList.size();
label0:
        for(int k = 0; k < j; k++)
        {
            UpdateOp updateop = (UpdateOp)mPostponedList.get(k);
            if(updateop.cmd == 8)
            {
                if(findPositionOffset(updateop.itemCount, k + 1) == i)
                    return true;
                continue;
            }
            if(updateop.cmd != 1)
                continue;
            int l = updateop.positionStart;
            int i1 = updateop.itemCount;
            int j1 = updateop.positionStart;
            do
            {
                if(j1 >= l + i1)
                    continue label0;
                if(findPositionOffset(j1, k + 1) == i)
                    return true;
                j1++;
            } while(true);
        }

        return false;
    }

    private void dispatchAndUpdateViewHolders(UpdateOp updateop)
    {
        int i;
        int j;
        int k;
        if(updateop.cmd == 1 || updateop.cmd == 8)
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        i = updatePositionWithPostponed(updateop.positionStart, updateop.cmd);
        j = 1;
        k = updateop.positionStart;
        updateop.cmd;
        JVM INSTR tableswitch 2 4: default 80
    //                   2 203
    //                   3 80
    //                   4 107;
           goto _L1 _L2 _L1 _L3
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("op should be remove or update.").append(updateop).toString());
_L3:
        int l = 1;
_L9:
        int i1 = 1;
_L8:
        int j1;
        boolean flag;
        int k1;
        if(i1 >= updateop.itemCount)
            break MISSING_BLOCK_LABEL_310;
        j1 = updatePositionWithPostponed(updateop.positionStart + l * i1, updateop.cmd);
        flag = false;
        k1 = ((flag) ? 1 : 0);
        updateop.cmd;
        JVM INSTR tableswitch 2 4: default 180
    //                   2 229
    //                   3 184
    //                   4 209;
           goto _L4 _L5 _L6 _L7
_L6:
        break; /* Loop/switch isn't completed */
_L4:
        k1 = ((flag) ? 1 : 0);
_L10:
        if(k1 != 0)
        {
            k1 = j + 1;
        } else
        {
            UpdateOp updateop1 = obtainUpdateOp(updateop.cmd, i, j, updateop.payload);
            dispatchFirstPassAndUpdateViewHolders(updateop1, k);
            recycleUpdateOp(updateop1);
            k1 = k;
            if(updateop.cmd == 4)
                k1 = k + j;
            i = j1;
            j = 1;
            k = k1;
            k1 = j;
        }
        i1++;
        j = k1;
          goto _L8
_L2:
        l = 0;
          goto _L9
_L7:
        if(j1 == i + 1)
            k1 = 1;
        else
            k1 = 0;
          goto _L10
_L5:
        if(j1 == i)
            k1 = 1;
        else
            k1 = 0;
          goto _L10
        Object obj = updateop.payload;
        recycleUpdateOp(updateop);
        if(j > 0)
        {
            updateop = obtainUpdateOp(updateop.cmd, i, j, obj);
            dispatchFirstPassAndUpdateViewHolders(updateop, k);
            recycleUpdateOp(updateop);
        }
        return;
          goto _L8
    }

    private void postponeAndUpdateViewHolders(UpdateOp updateop)
    {
        mPostponedList.add(updateop);
        updateop.cmd;
        JVM INSTR tableswitch 1 8: default 60
    //                   1 87
    //                   2 125
    //                   3 60
    //                   4 145
    //                   5 60
    //                   6 60
    //                   7 60
    //                   8 105;
           goto _L1 _L2 _L3 _L1 _L4 _L1 _L1 _L1 _L5
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Unknown update op type for ").append(updateop).toString());
_L2:
        mCallback.offsetPositionsForAdd(updateop.positionStart, updateop.itemCount);
_L7:
        return;
_L5:
        mCallback.offsetPositionsForMove(updateop.positionStart, updateop.itemCount);
        continue; /* Loop/switch isn't completed */
_L3:
        mCallback.offsetPositionsForRemovingLaidOutOrNewView(updateop.positionStart, updateop.itemCount);
        continue; /* Loop/switch isn't completed */
_L4:
        mCallback.markViewHoldersUpdated(updateop.positionStart, updateop.itemCount, updateop.payload);
        if(true) goto _L7; else goto _L6
_L6:
    }

    private int updatePositionWithPostponed(int i, int j)
    {
        int k = mPostponedList.size() - 1;
        int l = i;
        do
        {
            if(k >= 0)
            {
                UpdateOp updateop = (UpdateOp)mPostponedList.get(k);
                if(updateop.cmd == 8)
                {
                    int i1;
                    if(updateop.positionStart < updateop.itemCount)
                    {
                        i = updateop.positionStart;
                        i1 = updateop.itemCount;
                    } else
                    {
                        i = updateop.itemCount;
                        i1 = updateop.positionStart;
                    }
                    if(l >= i && l <= i1)
                    {
                        if(i == updateop.positionStart)
                        {
                            if(j == 1)
                                updateop.itemCount = updateop.itemCount + 1;
                            else
                            if(j == 2)
                                updateop.itemCount = updateop.itemCount - 1;
                            i = l + 1;
                        } else
                        {
                            if(j == 1)
                                updateop.positionStart = updateop.positionStart + 1;
                            else
                            if(j == 2)
                                updateop.positionStart = updateop.positionStart - 1;
                            i = l - 1;
                        }
                    } else
                    {
                        i = l;
                        if(l < updateop.positionStart)
                            if(j == 1)
                            {
                                updateop.positionStart = updateop.positionStart + 1;
                                updateop.itemCount = updateop.itemCount + 1;
                                i = l;
                            } else
                            {
                                i = l;
                                if(j == 2)
                                {
                                    updateop.positionStart = updateop.positionStart - 1;
                                    updateop.itemCount = updateop.itemCount - 1;
                                    i = l;
                                }
                            }
                    }
                } else
                if(updateop.positionStart <= l)
                {
                    if(updateop.cmd == 1)
                    {
                        i = l - updateop.itemCount;
                    } else
                    {
                        i = l;
                        if(updateop.cmd == 2)
                            i = l + updateop.itemCount;
                    }
                } else
                if(j == 1)
                {
                    updateop.positionStart = updateop.positionStart + 1;
                    i = l;
                } else
                {
                    i = l;
                    if(j == 2)
                    {
                        updateop.positionStart = updateop.positionStart - 1;
                        i = l;
                    }
                }
                k--;
                l = i;
                continue;
            }
            i = mPostponedList.size() - 1;
            while(i >= 0) 
            {
                UpdateOp updateop1 = (UpdateOp)mPostponedList.get(i);
                if(updateop1.cmd == 8)
                {
                    if(updateop1.itemCount == updateop1.positionStart || updateop1.itemCount < 0)
                    {
                        mPostponedList.remove(i);
                        recycleUpdateOp(updateop1);
                    }
                } else
                if(updateop1.itemCount <= 0)
                {
                    mPostponedList.remove(i);
                    recycleUpdateOp(updateop1);
                }
                i--;
            }
            return l;
        } while(true);
    }

    transient AdapterHelper addUpdateOp(UpdateOp aupdateop[])
    {
        Collections.addAll(mPendingUpdates, aupdateop);
        return this;
    }

    public int applyPendingUpdatesToPosition(int i)
    {
        int j;
        int k;
        int l;
        j = mPendingUpdates.size();
        k = 0;
        l = i;
_L6:
        UpdateOp updateop;
        if(k >= j)
            break MISSING_BLOCK_LABEL_212;
        updateop = (UpdateOp)mPendingUpdates.get(k);
        updateop.cmd;
        JVM INSTR lookupswitch 3: default 72
    //                   1: 84
    //                   2: 109
    //                   8: 152;
           goto _L1 _L2 _L3 _L4
_L4:
        break MISSING_BLOCK_LABEL_152;
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        i = l;
_L7:
        k++;
        l = i;
        if(true) goto _L6; else goto _L5
_L5:
        i = l;
        if(updateop.positionStart <= l)
            i = l + updateop.itemCount;
          goto _L7
_L3:
        i = l;
        if(updateop.positionStart <= l)
        {
            if(updateop.positionStart + updateop.itemCount > l)
                return -1;
            i = l - updateop.itemCount;
        }
          goto _L7
        if(updateop.positionStart == l)
        {
            i = updateop.itemCount;
        } else
        {
            int i1 = l;
            if(updateop.positionStart < l)
                i1 = l - 1;
            i = i1;
            if(updateop.itemCount <= i1)
                i = i1 + 1;
        }
          goto _L7
        return l;
    }

    void consumePostponedUpdates()
    {
        int i = mPostponedList.size();
        for(int j = 0; j < i; j++)
            mCallback.onDispatchSecondPass((UpdateOp)mPostponedList.get(j));

        recycleUpdateOpsAndClearList(mPostponedList);
        mExistingUpdateTypes = 0;
    }

    void consumeUpdatesInOnePass()
    {
        int i;
        int j;
        consumePostponedUpdates();
        i = mPendingUpdates.size();
        j = 0;
_L7:
        UpdateOp updateop;
        if(j >= i)
            break MISSING_BLOCK_LABEL_226;
        updateop = (UpdateOp)mPendingUpdates.get(j);
        updateop.cmd;
        JVM INSTR tableswitch 1 8: default 80
    //                   1 102
    //                   2 132
    //                   3 80
    //                   4 162
    //                   5 80
    //                   6 80
    //                   7 80
    //                   8 196;
           goto _L1 _L2 _L3 _L1 _L4 _L1 _L1 _L1 _L5
_L5:
        break MISSING_BLOCK_LABEL_196;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L8:
        if(mOnItemProcessedCallback != null)
            mOnItemProcessedCallback.run();
        j++;
        if(true) goto _L7; else goto _L6
_L6:
        mCallback.onDispatchSecondPass(updateop);
        mCallback.offsetPositionsForAdd(updateop.positionStart, updateop.itemCount);
          goto _L8
_L3:
        mCallback.onDispatchSecondPass(updateop);
        mCallback.offsetPositionsForRemovingInvisible(updateop.positionStart, updateop.itemCount);
          goto _L8
_L4:
        mCallback.onDispatchSecondPass(updateop);
        mCallback.markViewHoldersUpdated(updateop.positionStart, updateop.itemCount, updateop.payload);
          goto _L8
        mCallback.onDispatchSecondPass(updateop);
        mCallback.offsetPositionsForMove(updateop.positionStart, updateop.itemCount);
          goto _L8
        recycleUpdateOpsAndClearList(mPendingUpdates);
        mExistingUpdateTypes = 0;
        return;
    }

    void dispatchFirstPassAndUpdateViewHolders(UpdateOp updateop, int i)
    {
        mCallback.onDispatchFirstPass(updateop);
        updateop.cmd;
        JVM INSTR tableswitch 2 4: default 40
    //                   2 50
    //                   3 40
    //                   4 65;
           goto _L1 _L2 _L1 _L3
_L1:
        throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
_L2:
        mCallback.offsetPositionsForRemovingInvisible(i, updateop.itemCount);
_L5:
        return;
_L3:
        mCallback.markViewHoldersUpdated(i, updateop.itemCount, updateop.payload);
        if(true) goto _L5; else goto _L4
_L4:
    }

    int findPositionOffset(int i)
    {
        return findPositionOffset(i, 0);
    }

    int findPositionOffset(int i, int j)
    {
        int k = mPostponedList.size();
        int l = j;
        j = i;
        while(l < k) 
        {
            UpdateOp updateop = (UpdateOp)mPostponedList.get(l);
            if(updateop.cmd == 8)
            {
                if(updateop.positionStart == j)
                {
                    i = updateop.itemCount;
                } else
                {
                    int i1 = j;
                    if(updateop.positionStart < j)
                        i1 = j - 1;
                    i = i1;
                    if(updateop.itemCount <= i1)
                        i = i1 + 1;
                }
            } else
            {
                i = j;
                if(updateop.positionStart <= j)
                    if(updateop.cmd == 2)
                    {
                        if(j < updateop.positionStart + updateop.itemCount)
                            return -1;
                        i = j - updateop.itemCount;
                    } else
                    {
                        i = j;
                        if(updateop.cmd == 1)
                            i = j + updateop.itemCount;
                    }
            }
            l++;
            j = i;
        }
        return j;
    }

    boolean hasAnyUpdateTypes(int i)
    {
        boolean flag = false;
        if((mExistingUpdateTypes & i) != 0)
            flag = true;
        return flag;
    }

    boolean hasPendingUpdates()
    {
        boolean flag = false;
        if(mPendingUpdates.size() > 0)
            flag = true;
        return flag;
    }

    boolean hasUpdates()
    {
        boolean flag;
        if(!mPostponedList.isEmpty())
            flag = mPendingUpdates.isEmpty() ^ true;
        else
            flag = false;
        return flag;
    }

    public UpdateOp obtainUpdateOp(int i, int j, int k, Object obj)
    {
        UpdateOp updateop = (UpdateOp)mUpdateOpPool.acquire();
        if(updateop == null)
        {
            obj = new UpdateOp(i, j, k, obj);
        } else
        {
            updateop.cmd = i;
            updateop.positionStart = j;
            updateop.itemCount = k;
            updateop.payload = obj;
            obj = updateop;
        }
        return ((UpdateOp) (obj));
    }

    boolean onItemRangeChanged(int i, int j, Object obj)
    {
        boolean flag = true;
        if(j < 1)
            return false;
        mPendingUpdates.add(obtainUpdateOp(4, i, j, obj));
        mExistingUpdateTypes = mExistingUpdateTypes | 4;
        if(mPendingUpdates.size() != 1)
            flag = false;
        return flag;
    }

    boolean onItemRangeInserted(int i, int j)
    {
        boolean flag = true;
        if(j < 1)
            return false;
        mPendingUpdates.add(obtainUpdateOp(1, i, j, null));
        mExistingUpdateTypes = mExistingUpdateTypes | 1;
        if(mPendingUpdates.size() != 1)
            flag = false;
        return flag;
    }

    boolean onItemRangeMoved(int i, int j, int k)
    {
        boolean flag = true;
        if(i == j)
            return false;
        if(k != 1)
            throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
        mPendingUpdates.add(obtainUpdateOp(8, i, j, null));
        mExistingUpdateTypes = mExistingUpdateTypes | 8;
        if(mPendingUpdates.size() != 1)
            flag = false;
        return flag;
    }

    boolean onItemRangeRemoved(int i, int j)
    {
        boolean flag = true;
        if(j < 1)
            return false;
        mPendingUpdates.add(obtainUpdateOp(2, i, j, null));
        mExistingUpdateTypes = mExistingUpdateTypes | 2;
        if(mPendingUpdates.size() != 1)
            flag = false;
        return flag;
    }

    void preProcess()
    {
        int i;
        int j;
        mOpReorderer.reorderOps(mPendingUpdates);
        i = mPendingUpdates.size();
        j = 0;
_L7:
        UpdateOp updateop;
        if(j >= i)
            break MISSING_BLOCK_LABEL_142;
        updateop = (UpdateOp)mPendingUpdates.get(j);
        updateop.cmd;
        JVM INSTR tableswitch 1 8: default 88
    //                   1 110
    //                   2 118
    //                   3 88
    //                   4 126
    //                   5 88
    //                   6 88
    //                   7 88
    //                   8 134;
           goto _L1 _L2 _L3 _L1 _L4 _L1 _L1 _L1 _L5
_L5:
        break MISSING_BLOCK_LABEL_134;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L8:
        if(mOnItemProcessedCallback != null)
            mOnItemProcessedCallback.run();
        j++;
        if(true) goto _L7; else goto _L6
_L6:
        applyAdd(updateop);
          goto _L8
_L3:
        applyRemove(updateop);
          goto _L8
_L4:
        applyUpdate(updateop);
          goto _L8
        applyMove(updateop);
          goto _L8
        mPendingUpdates.clear();
        return;
    }

    public void recycleUpdateOp(UpdateOp updateop)
    {
        if(!mDisableRecycler)
        {
            updateop.payload = null;
            mUpdateOpPool.release(updateop);
        }
    }

    void recycleUpdateOpsAndClearList(List list)
    {
        int i = list.size();
        for(int j = 0; j < i; j++)
            recycleUpdateOp((UpdateOp)list.get(j));

        list.clear();
    }

    void reset()
    {
        recycleUpdateOpsAndClearList(mPendingUpdates);
        recycleUpdateOpsAndClearList(mPostponedList);
        mExistingUpdateTypes = 0;
    }

    private static final boolean DEBUG = false;
    static final int POSITION_TYPE_INVISIBLE = 0;
    static final int POSITION_TYPE_NEW_OR_LAID_OUT = 1;
    private static final String TAG = "AHT";
    final Callback mCallback;
    final boolean mDisableRecycler;
    private int mExistingUpdateTypes;
    Runnable mOnItemProcessedCallback;
    final OpReorderer mOpReorderer;
    final ArrayList mPendingUpdates;
    final ArrayList mPostponedList;
    private android.util.Pools.Pool mUpdateOpPool;
}

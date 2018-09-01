// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import java.util.List;

class OpReorderer
{
    static interface Callback
    {

        public abstract AdapterHelper.UpdateOp obtainUpdateOp(int i, int j, int k, Object obj);

        public abstract void recycleUpdateOp(AdapterHelper.UpdateOp updateop);
    }


    OpReorderer(Callback callback)
    {
        mCallback = callback;
    }

    private int getLastMoveOutOfOrder(List list)
    {
        boolean flag = false;
        for(int i = list.size() - 1; i >= 0;)
        {
            boolean flag1;
            if(((AdapterHelper.UpdateOp)list.get(i)).cmd == 8)
            {
                flag1 = flag;
                if(flag)
                    return i;
            } else
            {
                flag1 = true;
            }
            i--;
            flag = flag1;
        }

        return -1;
    }

    private void swapMoveAdd(List list, int i, AdapterHelper.UpdateOp updateop, int j, AdapterHelper.UpdateOp updateop1)
    {
        byte byte0 = 0;
        if(updateop.itemCount < updateop1.positionStart)
            byte0 = -1;
        int k = byte0;
        if(updateop.positionStart < updateop1.positionStart)
            k = byte0 + 1;
        if(updateop1.positionStart <= updateop.positionStart)
            updateop.positionStart = updateop.positionStart + updateop1.itemCount;
        if(updateop1.positionStart <= updateop.itemCount)
            updateop.itemCount = updateop.itemCount + updateop1.itemCount;
        updateop1.positionStart = updateop1.positionStart + k;
        list.set(i, updateop1);
        list.set(j, updateop);
    }

    private void swapMoveOp(List list, int i, int j)
    {
        AdapterHelper.UpdateOp updateop;
        AdapterHelper.UpdateOp updateop1;
        updateop = (AdapterHelper.UpdateOp)list.get(i);
        updateop1 = (AdapterHelper.UpdateOp)list.get(j);
        updateop1.cmd;
        JVM INSTR tableswitch 1 4: default 60
    //                   1 75
    //                   2 61
    //                   3 60
    //                   4 89;
           goto _L1 _L2 _L3 _L1 _L4
_L1:
        return;
_L3:
        swapMoveRemove(list, i, updateop, j, updateop1);
        continue; /* Loop/switch isn't completed */
_L2:
        swapMoveAdd(list, i, updateop, j, updateop1);
        continue; /* Loop/switch isn't completed */
_L4:
        swapMoveUpdate(list, i, updateop, j, updateop1);
        if(true) goto _L1; else goto _L5
_L5:
    }

    void reorderOps(List list)
    {
        do
        {
            int i = getLastMoveOutOfOrder(list);
            if(i != -1)
                swapMoveOp(list, i, i + 1);
            else
                return;
        } while(true);
    }

    void swapMoveRemove(List list, int i, AdapterHelper.UpdateOp updateop, int j, AdapterHelper.UpdateOp updateop1)
    {
        AdapterHelper.UpdateOp updateop2 = null;
        boolean flag = false;
        boolean flag3;
        boolean flag4;
        if(updateop.positionStart < updateop.itemCount)
        {
            boolean flag1 = false;
            flag3 = flag1;
            flag4 = flag;
            if(updateop1.positionStart == updateop.positionStart)
            {
                flag3 = flag1;
                flag4 = flag;
                if(updateop1.itemCount == updateop.itemCount - updateop.positionStart)
                {
                    flag4 = true;
                    flag3 = flag1;
                }
            }
        } else
        {
            boolean flag2 = true;
            flag3 = flag2;
            flag4 = flag;
            if(updateop1.positionStart == updateop.itemCount + 1)
            {
                flag3 = flag2;
                flag4 = flag;
                if(updateop1.itemCount == updateop.positionStart - updateop.itemCount)
                {
                    flag4 = true;
                    flag3 = flag2;
                }
            }
        }
        if(updateop.itemCount < updateop1.positionStart)
            updateop1.positionStart = updateop1.positionStart - 1;
        else
        if(updateop.itemCount < updateop1.positionStart + updateop1.itemCount)
        {
            updateop1.itemCount = updateop1.itemCount - 1;
            updateop.cmd = 2;
            updateop.itemCount = 1;
            if(updateop1.itemCount == 0)
            {
                list.remove(j);
                mCallback.recycleUpdateOp(updateop1);
            }
            return;
        }
        if(updateop.positionStart <= updateop1.positionStart)
            updateop1.positionStart = updateop1.positionStart + 1;
        else
        if(updateop.positionStart < updateop1.positionStart + updateop1.itemCount)
        {
            int i1 = updateop1.positionStart;
            int l = updateop1.itemCount;
            int k = updateop.positionStart;
            updateop2 = mCallback.obtainUpdateOp(2, updateop.positionStart + 1, (i1 + l) - k, null);
            updateop1.itemCount = updateop.positionStart - updateop1.positionStart;
        }
        if(flag4)
        {
            list.set(i, updateop1);
            list.remove(j);
            mCallback.recycleUpdateOp(updateop);
            return;
        }
        if(flag3)
        {
            if(updateop2 != null)
            {
                if(updateop.positionStart > updateop2.positionStart)
                    updateop.positionStart = updateop.positionStart - updateop2.itemCount;
                if(updateop.itemCount > updateop2.positionStart)
                    updateop.itemCount = updateop.itemCount - updateop2.itemCount;
            }
            if(updateop.positionStart > updateop1.positionStart)
                updateop.positionStart = updateop.positionStart - updateop1.itemCount;
            if(updateop.itemCount > updateop1.positionStart)
                updateop.itemCount = updateop.itemCount - updateop1.itemCount;
        } else
        {
            if(updateop2 != null)
            {
                if(updateop.positionStart >= updateop2.positionStart)
                    updateop.positionStart = updateop.positionStart - updateop2.itemCount;
                if(updateop.itemCount >= updateop2.positionStart)
                    updateop.itemCount = updateop.itemCount - updateop2.itemCount;
            }
            if(updateop.positionStart >= updateop1.positionStart)
                updateop.positionStart = updateop.positionStart - updateop1.itemCount;
            if(updateop.itemCount >= updateop1.positionStart)
                updateop.itemCount = updateop.itemCount - updateop1.itemCount;
        }
        list.set(i, updateop1);
        if(updateop.positionStart != updateop.itemCount)
            list.set(j, updateop);
        else
            list.remove(j);
        if(updateop2 != null)
            list.add(i, updateop2);
    }

    void swapMoveUpdate(List list, int i, AdapterHelper.UpdateOp updateop, int j, AdapterHelper.UpdateOp updateop1)
    {
        AdapterHelper.UpdateOp updateop2 = null;
        AdapterHelper.UpdateOp updateop3 = null;
        if(updateop.itemCount < updateop1.positionStart)
            updateop1.positionStart = updateop1.positionStart - 1;
        else
        if(updateop.itemCount < updateop1.positionStart + updateop1.itemCount)
        {
            updateop1.itemCount = updateop1.itemCount - 1;
            updateop2 = mCallback.obtainUpdateOp(4, updateop.positionStart, 1, updateop1.payload);
        }
        if(updateop.positionStart <= updateop1.positionStart)
            updateop1.positionStart = updateop1.positionStart + 1;
        else
        if(updateop.positionStart < updateop1.positionStart + updateop1.itemCount)
        {
            int k = (updateop1.positionStart + updateop1.itemCount) - updateop.positionStart;
            updateop3 = mCallback.obtainUpdateOp(4, updateop.positionStart + 1, k, updateop1.payload);
            updateop1.itemCount = updateop1.itemCount - k;
        }
        list.set(j, updateop);
        if(updateop1.itemCount > 0)
        {
            list.set(i, updateop1);
        } else
        {
            list.remove(i);
            mCallback.recycleUpdateOp(updateop1);
        }
        if(updateop2 != null)
            list.add(i, updateop2);
        if(updateop3 != null)
            list.add(i, updateop3);
    }

    final Callback mCallback;
}

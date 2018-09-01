// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import java.util.ArrayList;

// Referenced classes of package android.widget:
//            ExpandableListView

class ExpandableListPosition
{

    private ExpandableListPosition()
    {
    }

    private static ExpandableListPosition getRecycledOrCreate()
    {
        ArrayList arraylist = sPool;
        arraylist;
        JVM INSTR monitorenter ;
        ExpandableListPosition expandablelistposition;
        if(sPool.size() <= 0)
            break MISSING_BLOCK_LABEL_34;
        expandablelistposition = (ExpandableListPosition)sPool.remove(0);
        arraylist;
        JVM INSTR monitorexit ;
        expandablelistposition.resetState();
        return expandablelistposition;
        expandablelistposition = new ExpandableListPosition();
        arraylist;
        JVM INSTR monitorexit ;
        return expandablelistposition;
        Exception exception;
        exception;
        throw exception;
    }

    static ExpandableListPosition obtain(int i, int j, int k, int l)
    {
        ExpandableListPosition expandablelistposition = getRecycledOrCreate();
        expandablelistposition.type = i;
        expandablelistposition.groupPos = j;
        expandablelistposition.childPos = k;
        expandablelistposition.flatListPos = l;
        return expandablelistposition;
    }

    static ExpandableListPosition obtainChildPosition(int i, int j)
    {
        return obtain(1, i, j, 0);
    }

    static ExpandableListPosition obtainGroupPosition(int i)
    {
        return obtain(2, i, 0, 0);
    }

    static ExpandableListPosition obtainPosition(long l)
    {
        if(l == 0xffffffffL)
            return null;
        ExpandableListPosition expandablelistposition = getRecycledOrCreate();
        expandablelistposition.groupPos = ExpandableListView.getPackedPositionGroup(l);
        if(ExpandableListView.getPackedPositionType(l) == 1)
        {
            expandablelistposition.type = 1;
            expandablelistposition.childPos = ExpandableListView.getPackedPositionChild(l);
        } else
        {
            expandablelistposition.type = 2;
        }
        return expandablelistposition;
    }

    private void resetState()
    {
        groupPos = 0;
        childPos = 0;
        flatListPos = 0;
        type = 0;
    }

    long getPackedPosition()
    {
        if(type == 1)
            return ExpandableListView.getPackedPositionForChild(groupPos, childPos);
        else
            return ExpandableListView.getPackedPositionForGroup(groupPos);
    }

    public void recycle()
    {
        ArrayList arraylist = sPool;
        arraylist;
        JVM INSTR monitorenter ;
        if(sPool.size() < 5)
            sPool.add(this);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static final int CHILD = 1;
    public static final int GROUP = 2;
    private static final int MAX_POOL_SIZE = 5;
    private static ArrayList sPool = new ArrayList(5);
    public int childPos;
    int flatListPos;
    public int groupPos;
    public int type;

}

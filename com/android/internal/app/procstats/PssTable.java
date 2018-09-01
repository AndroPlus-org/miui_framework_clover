// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app.procstats;


// Referenced classes of package com.android.internal.app.procstats:
//            SparseMappingTable

public class PssTable extends SparseMappingTable.Table
{

    public PssTable(SparseMappingTable sparsemappingtable)
    {
        super(sparsemappingtable);
    }

    public void mergeStats(int i, int j, long l, long l1, long l2, long l3, long l4, long l5)
    {
        long l6;
        i = getOrAddKey((byte)i, 7);
        l6 = getValue(i, 0);
        if(l6 != 0L) goto _L2; else goto _L1
_L1:
        setValue(i, 0, j);
        setValue(i, 1, l);
        setValue(i, 2, l1);
        setValue(i, 3, l2);
        setValue(i, 4, l3);
        setValue(i, 5, l4);
        setValue(i, 6, l5);
_L4:
        return;
_L2:
        setValue(i, 0, (long)j + l6);
        if(getValue(i, 1) > l)
            setValue(i, 1, l);
        setValue(i, 2, (long)(((double)getValue(i, 2) * (double)l6 + (double)l1 * (double)j) / (double)((long)j + l6)));
        if(getValue(i, 3) < l2)
            setValue(i, 3, l2);
        if(getValue(i, 4) > l3)
            setValue(i, 4, l3);
        setValue(i, 5, (long)(((double)getValue(i, 5) * (double)l6 + (double)l4 * (double)j) / (double)((long)j + l6)));
        if(getValue(i, 6) < l5)
            setValue(i, 6, l5);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void mergeStats(PssTable psstable)
    {
        int i = psstable.getKeyCount();
        for(int j = 0; j < i; j++)
        {
            int k = psstable.getKeyAt(j);
            mergeStats(SparseMappingTable.getIdFromKey(k), (int)psstable.getValue(k, 0), psstable.getValue(k, 1), psstable.getValue(k, 2), psstable.getValue(k, 3), psstable.getValue(k, 4), psstable.getValue(k, 5), psstable.getValue(k, 6));
        }

    }
}

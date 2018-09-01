// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app.procstats;


// Referenced classes of package com.android.internal.app.procstats:
//            SparseMappingTable

public class DurationsTable extends SparseMappingTable.Table
{

    public DurationsTable(SparseMappingTable sparsemappingtable)
    {
        super(sparsemappingtable);
    }

    public void addDuration(int i, long l)
    {
        i = getOrAddKey((byte)i, 1);
        setValue(i, getValue(i) + l);
    }

    public void addDurations(DurationsTable durationstable)
    {
        int i = durationstable.getKeyCount();
        for(int j = 0; j < i; j++)
        {
            int k = durationstable.getKeyAt(j);
            addDuration(SparseMappingTable.getIdFromKey(k), durationstable.getValue(k));
        }

    }
}

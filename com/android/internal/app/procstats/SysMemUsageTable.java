// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app.procstats;

import android.util.DebugUtils;
import java.io.PrintWriter;

// Referenced classes of package com.android.internal.app.procstats:
//            DumpUtils, SparseMappingTable

public class SysMemUsageTable extends SparseMappingTable.Table
{

    public SysMemUsageTable(SparseMappingTable sparsemappingtable)
    {
        super(sparsemappingtable);
    }

    private void dumpCategory(PrintWriter printwriter, String s, String s1, int i, int j)
    {
        printwriter.print(s);
        printwriter.print(s1);
        printwriter.print(": ");
        DebugUtils.printSizeValue(printwriter, getValueForId((byte)i, j) * 1024L);
        printwriter.print(" min, ");
        DebugUtils.printSizeValue(printwriter, getValueForId((byte)i, j + 1) * 1024L);
        printwriter.print(" avg, ");
        DebugUtils.printSizeValue(printwriter, getValueForId((byte)i, j + 2) * 1024L);
        printwriter.println(" max");
    }

    public static void mergeSysMemUsage(long al[], int i, long al1[], int j)
    {
        long l = al[i + 0];
        long l1 = al1[j + 0];
        if(l == 0L)
        {
            al[i + 0] = l1;
            for(int k = 1; k < 16; k++)
                al[i + k] = al1[j + k];

        } else
        if(l1 > 0L)
        {
            al[i + 0] = l + l1;
            for(int i1 = 1; i1 < 16; i1 += 3)
            {
                if(al[i + i1] > al1[j + i1])
                    al[i + i1] = al1[j + i1];
                al[i + i1 + 1] = (long)(((double)al[i + i1 + 1] * (double)l + (double)al1[j + i1 + 1] * (double)l1) / (double)(l + l1));
                if(al[i + i1 + 2] < al1[j + i1 + 2])
                    al[i + i1 + 2] = al1[j + i1 + 2];
            }

        }
    }

    public void dump(PrintWriter printwriter, String s, int ai[], int ai1[])
    {
        int i = -1;
        for(int j = 0; j < ai.length; j++)
        {
            int k = -1;
            int l = 0;
            while(l < ai1.length) 
            {
                int i1 = ai[j];
                int j1 = ai1[l];
                int k1 = (i1 + j1) * 14;
                long l1 = getValueForId((byte)k1, 0);
                int i2 = k;
                int j2 = i;
                if(l1 > 0L)
                {
                    printwriter.print(s);
                    j2 = i;
                    if(ai.length > 1)
                    {
                        if(i != i1)
                            i = i1;
                        else
                            i = -1;
                        DumpUtils.printScreenLabel(printwriter, i);
                        j2 = i1;
                    }
                    i = k;
                    if(ai1.length > 1)
                    {
                        if(k != j1)
                            k = j1;
                        else
                            k = -1;
                        DumpUtils.printMemLabel(printwriter, k, '\0');
                        i = j1;
                    }
                    printwriter.print(": ");
                    printwriter.print(l1);
                    printwriter.println(" samples:");
                    dumpCategory(printwriter, s, "  Cached", k1, 1);
                    dumpCategory(printwriter, s, "  Free", k1, 4);
                    dumpCategory(printwriter, s, "  ZRam", k1, 7);
                    dumpCategory(printwriter, s, "  Kernel", k1, 10);
                    dumpCategory(printwriter, s, "  Native", k1, 13);
                    i2 = i;
                }
                l++;
                k = i2;
                i = j2;
            }
        }

    }

    public long[] getTotalMemUsage()
    {
        long al[] = new long[16];
        int i = getKeyCount();
        for(int j = 0; j < i; j++)
        {
            int k = getKeyAt(j);
            mergeSysMemUsage(al, 0, getArrayForKey(k), SparseMappingTable.getIndexFromKey(k));
        }

        return al;
    }

    public void mergeStats(int i, long al[], int j)
    {
        i = getOrAddKey((byte)i, 16);
        mergeSysMemUsage(getArrayForKey(i), SparseMappingTable.getIndexFromKey(i), al, j);
    }

    public void mergeStats(SysMemUsageTable sysmemusagetable)
    {
        int i = sysmemusagetable.getKeyCount();
        for(int j = 0; j < i; j++)
        {
            int k = sysmemusagetable.getKeyAt(j);
            mergeStats(SparseMappingTable.getIdFromKey(k), sysmemusagetable.getArrayForKey(k), SparseMappingTable.getIndexFromKey(k));
        }

    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.BatteryStats;
import android.util.LongSparseArray;

// Referenced classes of package com.android.internal.os:
//            PowerCalculator, PowerProfile, BatterySipper

public class MemoryPowerCalculator extends PowerCalculator
{

    public MemoryPowerCalculator(PowerProfile powerprofile)
    {
        int i = powerprofile.getNumElements("memory.bandwidths");
        powerAverages = new double[i];
        for(int j = 0; j < i; j++)
        {
            powerAverages[j] = powerprofile.getAveragePower("memory.bandwidths", j);
            double d = powerAverages[j];
        }

    }

    public void calculateApp(BatterySipper batterysipper, android.os.BatteryStats.Uid uid, long l, long l1, int i)
    {
    }

    public void calculateRemaining(BatterySipper batterysipper, BatteryStats batterystats, long l, long l1, int i)
    {
        double d = 0.0D;
        l1 = 0L;
        batterystats = batterystats.getKernelMemoryStats();
        for(int j = 0; j < batterystats.size() && j < powerAverages.length; j++)
        {
            double d1 = powerAverages[(int)batterystats.keyAt(j)];
            long l2 = ((android.os.BatteryStats.Timer)batterystats.valueAt(j)).getTotalTimeLocked(l, i);
            d += ((double)l2 * d1) / 60000D / 60D;
            l1 += l2;
        }

        batterysipper.usagePowerMah = d;
        batterysipper.usageTimeMs = l1;
    }

    private static final boolean DEBUG = false;
    public static final String TAG = "MemoryPowerCalculator";
    private final double powerAverages[];
}

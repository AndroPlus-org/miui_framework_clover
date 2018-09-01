// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.util.ArrayMap;

// Referenced classes of package com.android.internal.os:
//            PowerCalculator, BatterySipper, PowerProfile

public class CpuPowerCalculator extends PowerCalculator
{

    public CpuPowerCalculator(PowerProfile powerprofile)
    {
        mProfile = powerprofile;
    }

    public void calculateApp(BatterySipper batterysipper, android.os.BatteryStats.Uid uid, long l, long l1, int i)
    {
        batterysipper.cpuTimeMs = (uid.getUserCpuTimeUs(i) + uid.getSystemCpuTimeUs(i)) / 1000L;
        int j = mProfile.getNumCpuClusters();
        double d = 0.0D;
        for(int k = 0; k < j; k++)
        {
            int j1 = mProfile.getNumSpeedStepsInCpuCluster(k);
            for(int k1 = 0; k1 < j1; k1++)
                d += (double)uid.getTimeAtCpuSpeed(k, k1, i) * mProfile.getAveragePowerForCpu(k, k1);

        }

        batterysipper.cpuPowerMah = d / 3600000000D;
        double d2 = 0.0D;
        batterysipper.cpuFgTimeMs = 0L;
        ArrayMap arraymap = uid.getProcessStats();
        int i2 = arraymap.size();
        int i1 = 0;
        while(i1 < i2) 
        {
            uid = (android.os.BatteryStats.Uid.Proc)arraymap.valueAt(i1);
            String s = (String)arraymap.keyAt(i1);
            batterysipper.cpuFgTimeMs = batterysipper.cpuFgTimeMs + uid.getForegroundTime(i);
            l = uid.getUserTime(i) + uid.getSystemTime(i) + uid.getForegroundTime(i);
            double d1;
            if(batterysipper.packageWithHighestDrain == null || batterysipper.packageWithHighestDrain.startsWith("*"))
            {
                d1 = l;
                batterysipper.packageWithHighestDrain = s;
            } else
            {
                d1 = d2;
                if(d2 < (double)l)
                {
                    d1 = d2;
                    if(s.startsWith("*") ^ true)
                    {
                        d1 = l;
                        batterysipper.packageWithHighestDrain = s;
                    }
                }
            }
            i1++;
            d2 = d1;
        }
        if(batterysipper.cpuFgTimeMs > batterysipper.cpuTimeMs)
            batterysipper.cpuTimeMs = batterysipper.cpuFgTimeMs;
    }

    private static final boolean DEBUG = false;
    private static final long MICROSEC_IN_HR = 0xd693a400L;
    private static final String TAG = "CpuPowerCalculator";
    private final PowerProfile mProfile;
}

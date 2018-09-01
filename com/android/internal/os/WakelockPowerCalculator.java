// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.BatteryStats;
import android.util.ArrayMap;

// Referenced classes of package com.android.internal.os:
//            PowerCalculator, PowerProfile, BatterySipper

public class WakelockPowerCalculator extends PowerCalculator
{

    public WakelockPowerCalculator(PowerProfile powerprofile)
    {
        mTotalAppWakelockTimeMs = 0L;
        mPowerWakelock = powerprofile.getAveragePower("cpu.awake");
    }

    public void calculateApp(BatterySipper batterysipper, android.os.BatteryStats.Uid uid, long l, long l1, int i)
    {
        l1 = 0L;
        ArrayMap arraymap = uid.getWakelockStats();
        int j = arraymap.size();
        for(int k = 0; k < j;)
        {
            uid = ((android.os.BatteryStats.Uid.Wakelock)arraymap.valueAt(k)).getWakeTime(0);
            long l2 = l1;
            if(uid != null)
                l2 = l1 + uid.getTotalTimeLocked(l, i);
            k++;
            l1 = l2;
        }

        batterysipper.wakeLockTimeMs = l1 / 1000L;
        mTotalAppWakelockTimeMs = mTotalAppWakelockTimeMs + batterysipper.wakeLockTimeMs;
        batterysipper.wakeLockPowerMah = ((double)batterysipper.wakeLockTimeMs * mPowerWakelock) / 3600000D;
    }

    public void calculateRemaining(BatterySipper batterysipper, BatteryStats batterystats, long l, long l1, int i)
    {
        l = batterystats.getBatteryUptime(l1) / 1000L - (mTotalAppWakelockTimeMs + batterystats.getScreenOnTime(l, i) / 1000L);
        if(l > 0L)
        {
            double d = ((double)l * mPowerWakelock) / 3600000D;
            batterysipper.wakeLockTimeMs = batterysipper.wakeLockTimeMs + l;
            batterysipper.wakeLockPowerMah = batterysipper.wakeLockPowerMah + d;
        }
    }

    public void reset()
    {
        mTotalAppWakelockTimeMs = 0L;
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "WakelockPowerCalculator";
    private final double mPowerWakelock;
    private long mTotalAppWakelockTimeMs;
}

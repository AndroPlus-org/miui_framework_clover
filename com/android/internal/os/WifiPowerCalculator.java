// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.BatteryStats;

// Referenced classes of package com.android.internal.os:
//            PowerCalculator, PowerProfile, BatterySipper

public class WifiPowerCalculator extends PowerCalculator
{

    public WifiPowerCalculator(PowerProfile powerprofile)
    {
        mTotalAppPowerDrain = 0.0D;
        mTotalAppRunningTime = 0L;
        mIdleCurrentMa = powerprofile.getAveragePower("wifi.controller.idle");
        mTxCurrentMa = powerprofile.getAveragePower("wifi.controller.tx");
        mRxCurrentMa = powerprofile.getAveragePower("wifi.controller.rx");
    }

    public void calculateApp(BatterySipper batterysipper, android.os.BatteryStats.Uid uid, long l, long l1, int i)
    {
        android.os.BatteryStats.ControllerActivityCounter controlleractivitycounter = uid.getWifiControllerActivity();
        if(controlleractivitycounter == null)
        {
            return;
        } else
        {
            l = controlleractivitycounter.getIdleTimeCounter().getCountLocked(i);
            l1 = controlleractivitycounter.getTxTimeCounters()[0].getCountLocked(i);
            long l2 = controlleractivitycounter.getRxTimeCounter().getCountLocked(i);
            batterysipper.wifiRunningTimeMs = l + l2 + l1;
            mTotalAppRunningTime = mTotalAppRunningTime + batterysipper.wifiRunningTimeMs;
            batterysipper.wifiPowerMah = ((double)l * mIdleCurrentMa + (double)l1 * mTxCurrentMa + (double)l2 * mRxCurrentMa) / 3600000D;
            mTotalAppPowerDrain = mTotalAppPowerDrain + batterysipper.wifiPowerMah;
            batterysipper.wifiRxPackets = uid.getNetworkActivityPackets(2, i);
            batterysipper.wifiTxPackets = uid.getNetworkActivityPackets(3, i);
            batterysipper.wifiRxBytes = uid.getNetworkActivityBytes(2, i);
            batterysipper.wifiTxBytes = uid.getNetworkActivityBytes(3, i);
            return;
        }
    }

    public void calculateRemaining(BatterySipper batterysipper, BatteryStats batterystats, long l, long l1, int i)
    {
        batterystats = batterystats.getWifiControllerActivity();
        l1 = batterystats.getIdleTimeCounter().getCountLocked(i);
        l = batterystats.getTxTimeCounters()[0].getCountLocked(i);
        long l2 = batterystats.getRxTimeCounter().getCountLocked(i);
        batterysipper.wifiRunningTimeMs = Math.max(0L, (l1 + l2 + l) - mTotalAppRunningTime);
        double d = (double)batterystats.getPowerCounter().getCountLocked(i) / 3600000D;
        double d1 = d;
        if(d == 0.0D)
            d1 = ((double)l1 * mIdleCurrentMa + (double)l * mTxCurrentMa + (double)l2 * mRxCurrentMa) / 3600000D;
        batterysipper.wifiPowerMah = Math.max(0.0D, d1 - mTotalAppPowerDrain);
    }

    public void reset()
    {
        mTotalAppPowerDrain = 0.0D;
        mTotalAppRunningTime = 0L;
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "WifiPowerCalculator";
    private final double mIdleCurrentMa;
    private final double mRxCurrentMa;
    private double mTotalAppPowerDrain;
    private long mTotalAppRunningTime;
    private final double mTxCurrentMa;
}

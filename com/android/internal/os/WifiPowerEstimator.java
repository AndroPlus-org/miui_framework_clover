// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.BatteryStats;

// Referenced classes of package com.android.internal.os:
//            PowerCalculator, PowerProfile, BatterySipper

public class WifiPowerEstimator extends PowerCalculator
{

    public WifiPowerEstimator(PowerProfile powerprofile)
    {
        mTotalAppWifiRunningTimeMs = 0L;
        mWifiPowerPerPacket = getWifiPowerPerPacket(powerprofile);
        mWifiPowerOn = powerprofile.getAveragePower("wifi.on");
        mWifiPowerScan = powerprofile.getAveragePower("wifi.scan");
        mWifiPowerBatchScan = powerprofile.getAveragePower("wifi.batchedscan");
    }

    private static double getWifiPowerPerPacket(PowerProfile powerprofile)
    {
        return powerprofile.getAveragePower("wifi.active") / 3600D / 61.03515625D;
    }

    public void calculateApp(BatterySipper batterysipper, android.os.BatteryStats.Uid uid, long l, long l1, int i)
    {
        batterysipper.wifiRxPackets = uid.getNetworkActivityPackets(2, i);
        batterysipper.wifiTxPackets = uid.getNetworkActivityPackets(3, i);
        batterysipper.wifiRxBytes = uid.getNetworkActivityBytes(2, i);
        batterysipper.wifiTxBytes = uid.getNetworkActivityBytes(3, i);
        double d = batterysipper.wifiRxPackets + batterysipper.wifiTxPackets;
        double d1 = mWifiPowerPerPacket;
        batterysipper.wifiRunningTimeMs = uid.getWifiRunningTime(l, i) / 1000L;
        mTotalAppWifiRunningTimeMs = mTotalAppWifiRunningTimeMs + batterysipper.wifiRunningTimeMs;
        double d2 = ((double)batterysipper.wifiRunningTimeMs * mWifiPowerOn) / 3600000D;
        double d3 = ((double)(uid.getWifiScanTime(l, i) / 1000L) * mWifiPowerScan) / 3600000D;
        double d4 = 0.0D;
        for(int j = 0; j < 5; j++)
            d4 += ((double)(uid.getWifiBatchedScanTime(j, l, i) / 1000L) * mWifiPowerBatchScan) / 3600000D;

        batterysipper.wifiPowerMah = d * d1 + d2 + d3 + d4;
    }

    public void calculateRemaining(BatterySipper batterysipper, BatteryStats batterystats, long l, long l1, int i)
    {
        l = batterystats.getGlobalWifiRunningTime(l, i) / 1000L;
        double d = ((double)(l - mTotalAppWifiRunningTimeMs) * mWifiPowerOn) / 3600000D;
        batterysipper.wifiRunningTimeMs = l;
        batterysipper.wifiPowerMah = Math.max(0.0D, d);
    }

    public void reset()
    {
        mTotalAppWifiRunningTimeMs = 0L;
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "WifiPowerEstimator";
    private long mTotalAppWifiRunningTimeMs;
    private final double mWifiPowerBatchScan;
    private final double mWifiPowerOn;
    private final double mWifiPowerPerPacket;
    private final double mWifiPowerScan;
}

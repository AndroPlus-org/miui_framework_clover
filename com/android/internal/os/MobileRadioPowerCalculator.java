// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.BatteryStats;

// Referenced classes of package com.android.internal.os:
//            PowerCalculator, PowerProfile, BatterySipper

public class MobileRadioPowerCalculator extends PowerCalculator
{

    public MobileRadioPowerCalculator(PowerProfile powerprofile, BatteryStats batterystats)
    {
        mTotalAppMobileActiveMs = 0L;
        mPowerRadioOn = powerprofile.getAveragePower("radio.active");
        for(int i = 0; i < mPowerBins.length; i++)
            mPowerBins[i] = powerprofile.getAveragePower("radio.on", i);

        mPowerScan = powerprofile.getAveragePower("radio.scanning");
        mStats = batterystats;
    }

    private double getMobilePowerPerPacket(long l, int i)
    {
        double d = mPowerRadioOn / 3600D;
        long l1 = mStats.getNetworkActivityPackets(0, i) + mStats.getNetworkActivityPackets(1, i);
        l = mStats.getMobileRadioActiveTime(l, i) / 1000L;
        double d1;
        if(l1 != 0L && l != 0L)
            d1 = (double)l1 / (double)l;
        else
            d1 = 12.20703125D;
        return d / d1 / 3600D;
    }

    public void calculateApp(BatterySipper batterysipper, android.os.BatteryStats.Uid uid, long l, long l1, int i)
    {
        batterysipper.mobileRxPackets = uid.getNetworkActivityPackets(0, i);
        batterysipper.mobileTxPackets = uid.getNetworkActivityPackets(1, i);
        batterysipper.mobileActive = uid.getMobileRadioActiveTime(i) / 1000L;
        batterysipper.mobileActiveCount = uid.getMobileRadioActiveCount(i);
        batterysipper.mobileRxBytes = uid.getNetworkActivityBytes(0, i);
        batterysipper.mobileTxBytes = uid.getNetworkActivityBytes(1, i);
        if(batterysipper.mobileActive > 0L)
        {
            mTotalAppMobileActiveMs = mTotalAppMobileActiveMs + batterysipper.mobileActive;
            batterysipper.mobileRadioPowerMah = ((double)batterysipper.mobileActive * mPowerRadioOn) / 3600000D;
        } else
        {
            batterysipper.mobileRadioPowerMah = (double)(batterysipper.mobileRxPackets + batterysipper.mobileTxPackets) * getMobilePowerPerPacket(l, i);
        }
    }

    public void calculateRemaining(BatterySipper batterysipper, BatteryStats batterystats, long l, long l1, int i)
    {
        double d = 0.0D;
        l1 = 0L;
        long l2 = 0L;
        for(int j = 0; j < mPowerBins.length; j++)
        {
            long l3 = batterystats.getPhoneSignalStrengthTime(j, l, i) / 1000L;
            d += ((double)l3 * mPowerBins[j]) / 3600000D;
            l1 += l3;
            if(j == 0)
                l2 = l3;
        }

        double d1 = d + ((double)(batterystats.getPhoneSignalScanningTime(l, i) / 1000L) * mPowerScan) / 3600000D;
        l = mStats.getMobileRadioActiveTime(l, i) / 1000L - mTotalAppMobileActiveMs;
        d = d1;
        if(l > 0L)
            d = d1 + (mPowerRadioOn * (double)l) / 3600000D;
        if(d != 0.0D)
        {
            if(l1 != 0L)
                batterysipper.noCoveragePercent = ((double)l2 * 100D) / (double)l1;
            batterysipper.mobileActive = l;
            batterysipper.mobileActiveCount = batterystats.getMobileRadioActiveUnknownCount(i);
            batterysipper.mobileRadioPowerMah = d;
        }
    }

    public void reset()
    {
        mTotalAppMobileActiveMs = 0L;
    }

    public void reset(BatteryStats batterystats)
    {
        reset();
        mStats = batterystats;
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "MobileRadioPowerController";
    private final double mPowerBins[] = new double[6];
    private final double mPowerRadioOn;
    private final double mPowerScan;
    private BatteryStats mStats;
    private long mTotalAppMobileActiveMs;
}

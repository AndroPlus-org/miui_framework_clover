// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.BatteryStats;

// Referenced classes of package com.android.internal.os:
//            PowerCalculator, PowerProfile, BatterySipper

public class BluetoothPowerCalculator extends PowerCalculator
{

    public BluetoothPowerCalculator(PowerProfile powerprofile)
    {
        mAppTotalPowerMah = 0.0D;
        mAppTotalTimeMs = 0L;
        mIdleMa = powerprofile.getAveragePower("bluetooth.controller.idle");
        mRxMa = powerprofile.getAveragePower("bluetooth.controller.rx");
        mTxMa = powerprofile.getAveragePower("bluetooth.controller.tx");
    }

    public void calculateApp(BatterySipper batterysipper, android.os.BatteryStats.Uid uid, long l, long l1, int i)
    {
        android.os.BatteryStats.ControllerActivityCounter controlleractivitycounter = uid.getBluetoothControllerActivity();
        if(controlleractivitycounter == null)
            return;
        long l2 = controlleractivitycounter.getIdleTimeCounter().getCountLocked(i);
        long l3 = controlleractivitycounter.getRxTimeCounter().getCountLocked(i);
        l = controlleractivitycounter.getTxTimeCounters()[0].getCountLocked(i);
        l1 = l2 + l + l3;
        double d = (double)controlleractivitycounter.getPowerCounter().getCountLocked(i) / 3600000D;
        double d1 = d;
        if(d == 0.0D)
            d1 = ((double)l2 * mIdleMa + (double)l3 * mRxMa + (double)l * mTxMa) / 3600000D;
        batterysipper.bluetoothPowerMah = d1;
        batterysipper.bluetoothRunningTimeMs = l1;
        batterysipper.btRxBytes = uid.getNetworkActivityBytes(4, i);
        batterysipper.btTxBytes = uid.getNetworkActivityBytes(5, i);
        mAppTotalPowerMah = mAppTotalPowerMah + d1;
        mAppTotalTimeMs = mAppTotalTimeMs + l1;
    }

    public void calculateRemaining(BatterySipper batterysipper, BatteryStats batterystats, long l, long l1, int i)
    {
        batterystats = batterystats.getBluetoothControllerActivity();
        long l2 = batterystats.getIdleTimeCounter().getCountLocked(i);
        l = batterystats.getTxTimeCounters()[0].getCountLocked(i);
        l1 = batterystats.getRxTimeCounter().getCountLocked(i);
        double d = (double)batterystats.getPowerCounter().getCountLocked(i) / 3600000D;
        double d1 = d;
        if(d == 0.0D)
            d1 = ((double)l2 * mIdleMa + (double)l1 * mRxMa + (double)l * mTxMa) / 3600000D;
        batterysipper.bluetoothPowerMah = Math.max(0.0D, d1 - mAppTotalPowerMah);
        batterysipper.bluetoothRunningTimeMs = Math.max(0L, (l2 + l + l1) - mAppTotalTimeMs);
    }

    public void reset()
    {
        mAppTotalPowerMah = 0.0D;
        mAppTotalTimeMs = 0L;
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "BluetoothPowerCalculator";
    private double mAppTotalPowerMah;
    private long mAppTotalTimeMs;
    private final double mIdleMa;
    private final double mRxMa;
    private final double mTxMa;
}

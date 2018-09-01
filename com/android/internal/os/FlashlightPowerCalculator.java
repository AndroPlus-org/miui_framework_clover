// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;


// Referenced classes of package com.android.internal.os:
//            PowerCalculator, PowerProfile, BatterySipper

public class FlashlightPowerCalculator extends PowerCalculator
{

    public FlashlightPowerCalculator(PowerProfile powerprofile)
    {
        mFlashlightPowerOnAvg = powerprofile.getAveragePower("camera.flashlight");
    }

    public void calculateApp(BatterySipper batterysipper, android.os.BatteryStats.Uid uid, long l, long l1, int i)
    {
        uid = uid.getFlashlightTurnedOnTimer();
        if(uid != null)
        {
            l = uid.getTotalTimeLocked(l, i) / 1000L;
            batterysipper.flashlightTimeMs = l;
            batterysipper.flashlightPowerMah = ((double)l * mFlashlightPowerOnAvg) / 3600000D;
        } else
        {
            batterysipper.flashlightTimeMs = 0L;
            batterysipper.flashlightPowerMah = 0.0D;
        }
    }

    private final double mFlashlightPowerOnAvg;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;


// Referenced classes of package com.android.internal.os:
//            PowerCalculator, PowerProfile, BatterySipper

public class CameraPowerCalculator extends PowerCalculator
{

    public CameraPowerCalculator(PowerProfile powerprofile)
    {
        mCameraPowerOnAvg = powerprofile.getAveragePower("camera.avg");
    }

    public void calculateApp(BatterySipper batterysipper, android.os.BatteryStats.Uid uid, long l, long l1, int i)
    {
        uid = uid.getCameraTurnedOnTimer();
        if(uid != null)
        {
            l = uid.getTotalTimeLocked(l, i) / 1000L;
            batterysipper.cameraTimeMs = l;
            batterysipper.cameraPowerMah = ((double)l * mCameraPowerOnAvg) / 3600000D;
        } else
        {
            batterysipper.cameraTimeMs = 0L;
            batterysipper.cameraPowerMah = 0.0D;
        }
    }

    private final double mCameraPowerOnAvg;
}

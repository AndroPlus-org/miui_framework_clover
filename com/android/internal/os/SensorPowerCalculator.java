// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.SparseArray;
import java.util.List;

// Referenced classes of package com.android.internal.os:
//            PowerCalculator, PowerProfile, BatterySipper

public class SensorPowerCalculator extends PowerCalculator
{

    public SensorPowerCalculator(PowerProfile powerprofile, SensorManager sensormanager)
    {
        mSensors = sensormanager.getSensorList(-1);
        mGpsPowerOn = powerprofile.getAveragePower("gps.on");
    }

    public void calculateApp(BatterySipper batterysipper, android.os.BatteryStats.Uid uid, long l, long l1, int i)
    {
        int j;
        int k;
        uid = uid.getSensorStats();
        j = uid.size();
        k = 0;
_L11:
        if(k >= j) goto _L2; else goto _L1
_L1:
        int i1;
        android.os.BatteryStats.Uid.Sensor sensor = (android.os.BatteryStats.Uid.Sensor)uid.valueAt(k);
        i1 = uid.keyAt(k);
        l1 = sensor.getSensorTime().getTotalTimeLocked(l, i) / 1000L;
        i1;
        JVM INSTR tableswitch -10000 -10000: default 76
    //                   -10000 151;
           goto _L3 _L4
_L3:
        int j1;
        int k1;
        j1 = mSensors.size();
        k1 = 0;
_L9:
        if(k1 >= j1) goto _L6; else goto _L5
_L5:
        Sensor sensor1 = (Sensor)mSensors.get(k1);
        if(sensor1.getHandle() != i1) goto _L8; else goto _L7
_L7:
        batterysipper.sensorPowerMah = batterysipper.sensorPowerMah + (double)(((float)l1 * sensor1.getPower()) / 3600000F);
_L6:
        k++;
        continue; /* Loop/switch isn't completed */
_L4:
        batterysipper.gpsTimeMs = l1;
        batterysipper.gpsPowerMah = ((double)batterysipper.gpsTimeMs * mGpsPowerOn) / 3600000D;
        if(true) goto _L6; else goto _L8
_L8:
        k1++;
        if(true) goto _L9; else goto _L2
_L2:
        return;
        if(true) goto _L11; else goto _L10
_L10:
    }

    private final double mGpsPowerOn;
    private final List mSensors;
}

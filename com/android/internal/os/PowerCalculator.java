// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.BatteryStats;

// Referenced classes of package com.android.internal.os:
//            BatterySipper

public abstract class PowerCalculator
{

    public PowerCalculator()
    {
    }

    public abstract void calculateApp(BatterySipper batterysipper, android.os.BatteryStats.Uid uid, long l, long l1, int i);

    public void calculateRemaining(BatterySipper batterysipper, BatteryStats batterystats, long l, long l1, int i)
    {
    }

    public void reset()
    {
    }
}

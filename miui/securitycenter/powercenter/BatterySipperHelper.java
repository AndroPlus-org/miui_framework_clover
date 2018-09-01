// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securitycenter.powercenter;

import android.content.Context;
import android.text.TextUtils;
import com.android.internal.os.BatterySipper;

// Referenced classes of package miui.securitycenter.powercenter:
//            BatterySipper, UidUtils

class BatterySipperHelper
{

    BatterySipperHelper()
    {
    }

    static miui.securitycenter.powercenter.BatterySipper addBatterySipper(miui.securitycenter.powercenter.BatterySipper batterysipper, BatterySipper batterysipper1)
    {
        batterysipper.usageTime = batterysipper.usageTime + batterysipper1.usageTimeMs;
        batterysipper.value = batterysipper.value + batterysipper1.totalPowerMah;
        return batterysipper;
    }

    static miui.securitycenter.powercenter.BatterySipper makeBatterySipper(Context context, int i, BatterySipper batterysipper)
    {
        if(batterysipper == null)
            return new miui.securitycenter.powercenter.BatterySipper(context, i, -1, 0.0D);
        int j = -1;
        if(batterysipper.uidObj != null)
            j = UidUtils.getRealUid(batterysipper.uidObj.getUid());
        context = new miui.securitycenter.powercenter.BatterySipper(context, i, j, batterysipper.totalPowerMah);
        context.usageTime = batterysipper.usageTimeMs;
        context.cpuTime = batterysipper.cpuTimeMs;
        context.gpsTime = batterysipper.gpsTimeMs;
        context.wifiRunningTime = batterysipper.wifiRunningTimeMs;
        context.cpuFgTime = batterysipper.cpuFgTimeMs;
        context.wakeLockTime = batterysipper.wakeLockTimeMs;
        context.noCoveragePercent = batterysipper.noCoveragePercent;
        context.mobileRxBytes = batterysipper.mobileRxBytes;
        context.mobileTxBytes = batterysipper.mobileTxBytes;
        if(TextUtils.isEmpty(((miui.securitycenter.powercenter.BatterySipper) (context)).name))
            context.name = batterysipper.packageWithHighestDrain;
        return context;
    }
}

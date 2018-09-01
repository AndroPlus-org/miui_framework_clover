// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securitycenter.powercenter;

import android.os.BatteryStats;
import android.os.SystemClock;

// Referenced classes of package miui.securitycenter.powercenter:
//            HistoryItemWrapper, BatteryStatsUtils

public class BatteryHistoryHelper
{

    public BatteryHistoryHelper()
    {
        rec = new android.os.BatteryStats.HistoryItem();
    }

    public void finishIterate()
    {
        if(mStats == null)
        {
            return;
        } else
        {
            mStats.finishIteratingHistoryLocked();
            return;
        }
    }

    public long getBatteryUsageRealtime()
    {
        if(mStats == null)
            return 0L;
        else
            return mStats.computeBatteryRealtime(SystemClock.elapsedRealtime() * 1000L, 0);
    }

    public boolean getNextHistoryItem(HistoryItemWrapper historyitemwrapper)
    {
        boolean flag = false;
        if(mStats == null)
            return false;
        if(!mStats.getNextHistoryLocked(rec))
            return false;
        historyitemwrapper.cmd = rec.cmd;
        historyitemwrapper.time = rec.time;
        historyitemwrapper.batteryLevel = rec.batteryLevel;
        historyitemwrapper.batteryStatus = rec.batteryStatus;
        historyitemwrapper.batteryHealth = rec.batteryHealth;
        historyitemwrapper.batteryPlugType = rec.batteryPlugType;
        historyitemwrapper.batteryTemperature = rec.batteryTemperature;
        historyitemwrapper.batteryVoltage = rec.batteryVoltage;
        boolean flag1;
        int i;
        if((rec.states2 & 0x10000000) != 0)
            flag1 = true;
        else
            flag1 = false;
        historyitemwrapper.wifiOn = flag1;
        if((rec.states & 0x20000000) != 0)
            flag1 = true;
        else
            flag1 = false;
        historyitemwrapper.gpsOn = flag1;
        if((rec.states & 0x80000) != 0)
            flag1 = true;
        else
            flag1 = false;
        historyitemwrapper.charging = flag1;
        if((rec.states & 0x100000) != 0)
            flag1 = true;
        else
            flag1 = false;
        historyitemwrapper.screenOn = flag1;
        if((rec.states & 0x40000000) != 0)
            flag1 = true;
        else
            flag1 = false;
        historyitemwrapper.wakelockOn = flag1;
        flag1 = flag;
        if((rec.states & 0x80000000) != 0)
            flag1 = true;
        historyitemwrapper.cpuRunning = flag1;
        if((rec.states & 0x1c0) >> 6 == 3)
            i = 0;
        else
        if((rec.states & 0x200000) != 0)
            i = 1;
        else
            i = ((rec.states & 0x38) >> 3) + 2;
        historyitemwrapper.phoneSignalStrength = i;
        return true;
    }

    public long getScreenOnTime()
    {
        if(mStats == null)
        {
            return 0L;
        } else
        {
            long l = SystemClock.elapsedRealtime();
            l = mStats.getBatteryRealtime(l * 1000L);
            return mStats.getScreenOnTime(l, 0);
        }
    }

    public void refreshHistory()
    {
        mStats = BatteryStatsUtils.getBatteryStats();
    }

    public boolean startIterate()
    {
        if(mStats == null)
            return false;
        else
            return mStats.startIteratingHistoryLocked();
    }

    private BatteryStats mStats;
    private android.os.BatteryStats.HistoryItem rec;
}

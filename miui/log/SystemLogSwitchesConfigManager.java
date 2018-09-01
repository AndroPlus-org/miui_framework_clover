// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.log;

import android.content.Intent;
import android.os.SystemProperties;

// Referenced classes of package miui.log:
//            LogSwitchesConfigManager

public final class SystemLogSwitchesConfigManager
{

    public SystemLogSwitchesConfigManager()
    {
    }

    public static void enableLogSwitch(boolean flag)
    {
        logSwitchesManager.startMonitoring(flag, synchronizedReadInitialLogSwitches());
    }

    private static boolean synchronizedReadInitialLogSwitches()
    {
        return SystemProperties.getBoolean("sys.miui.sync_read_log_switch", false);
    }

    public static void updateLogSwitches(Intent intent)
    {
        logSwitchesManager.updateLogSwitches(intent);
    }

    public static void updatePackageName(String s)
    {
        logSwitchesManager.updatePackageName(s);
    }

    public static void updateProgramName()
    {
        logSwitchesManager.updateProgramName();
    }

    public static void updateProgramName(String s)
    {
        logSwitchesManager.updateProgramName(s);
    }

    private static final String logSwitchesFileName = "switches.config";
    private static final String logSwitchesFolder = "/data/system/miuilog/switches";
    private static final LogSwitchesConfigManager logSwitchesManager = new LogSwitchesConfigManager("/data/system/miuilog/switches", "switches.config");

}

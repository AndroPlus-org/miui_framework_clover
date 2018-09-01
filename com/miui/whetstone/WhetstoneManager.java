// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.app.PendingIntent;
import android.os.*;
import java.io.File;

// Referenced classes of package com.miui.whetstone:
//            IWhetstone, WhetstoneConfig

public abstract class WhetstoneManager
{
    public static class PermissionFile
    {

        public static final File getDataWhetstonePermissionFile()
        {
            return new File("/data/system/whetstone", "WhetstonePermission.apk");
        }

        public static final File getSystemWhetstonePermissionFile()
        {
            if(android.os.Build.VERSION.SDK_INT >= 21)
                return new File("/system/etc/WhetstonePermission", "WhetstonePermission.apk");
            else
                return new File("/system/etc", "WhetstonePermission.apk");
        }

        public static final int L_VERSION_START_NUMBER = 21;
        public static final String WHETSTONE_PERMISSION_ENTRY_NAME = "permissions.xml";
        public static final String WHETSTONE_PERMISSION_FILE_NAME = "WhetstonePermission.apk";
        public static final String WHETSTONE_VERSION_ENTRY_NAME = "version";

        public PermissionFile()
        {
        }
    }

    static class WhetstoneManagerDeath
        implements android.os.IBinder.DeathRecipient
    {

        public void binderDied()
        {
            WhetstoneManager._2D_set0(null);
            if(mToken != null)
                mToken.asBinder().unlinkToDeath(this, 0);
        }

        private IWhetstone mToken;

        WhetstoneManagerDeath(IWhetstone iwhetstone)
        {
            mToken = iwhetstone;
        }
    }


    static IWhetstone _2D_set0(IWhetstone iwhetstone)
    {
        ws = iwhetstone;
        return iwhetstone;
    }

    public WhetstoneManager()
    {
    }

    public static boolean checkIfSupportWhestone()
    {
        return false;
    }

    private static void checkService()
    {
        getService();
    }

    public static int deepClean(WhetstoneConfig whetstoneconfig)
    {
        return 0;
    }

    private static IWhetstone getService()
    {
        if(ws == null)
        {
            ws = IWhetstone.Stub.asInterface(ServiceManager.getService("miui.whetstone"));
            try
            {
                if(ws != null)
                {
                    WhetstoneManagerDeath whetstonemanagerdeath = JVM INSTR new #9   <Class WhetstoneManager$WhetstoneManagerDeath>;
                    whetstonemanagerdeath.WhetstoneManagerDeath(ws);
                    ws.asBinder().linkToDeath(whetstonemanagerdeath, 0);
                }
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
                ws = null;
            }
        }
        return ws;
    }

    public static void log(int i, byte abyte0[])
    {
    }

    public static void recordRTCWakeupInfo(int i, PendingIntent pendingintent, boolean flag)
    {
        checkService();
        if(ws == null)
            break MISSING_BLOCK_LABEL_20;
        ws.recordRTCWakeupInfo(i, pendingintent, flag);
_L1:
        return;
        pendingintent;
        pendingintent.printStackTrace();
          goto _L1
    }

    public static final String ANALYTIC_PROPERTY = "persist.sys.whetstone.analytic";
    public static final boolean DEBUG = SystemProperties.getBoolean("persist.sys.whetstone.debug", false);
    public static final String SERVICE_NAME = "miui.whetstone";
    private static IWhetstone ws = null;

}

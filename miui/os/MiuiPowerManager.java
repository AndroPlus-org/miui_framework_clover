// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.os;

import android.os.*;

public class MiuiPowerManager
{

    public MiuiPowerManager()
    {
    }

    public static void reboot(boolean flag, String s, boolean flag1)
    {
        IPowerManager ipowermanager = android.os.IPowerManager.Stub.asInterface(ServiceManager.getService("power"));
        if(ipowermanager == null)
            break MISSING_BLOCK_LABEL_22;
        ipowermanager.reboot(flag, s, flag1);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }
}

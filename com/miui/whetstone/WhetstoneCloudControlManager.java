// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.content.ComponentName;
import android.os.*;
import java.util.List;

// Referenced classes of package com.miui.whetstone:
//            IWhetstone, CloudControlInfo

public abstract class WhetstoneCloudControlManager
{
    static class WhetstoneCloudManagerDeath
        implements android.os.IBinder.DeathRecipient
    {

        public void binderDied()
        {
            WhetstoneCloudControlManager._2D_set0(null);
            if(mToken != null)
                mToken.asBinder().unlinkToDeath(this, 0);
        }

        private IWhetstone mToken;

        WhetstoneCloudManagerDeath(IWhetstone iwhetstone)
        {
            mToken = iwhetstone;
        }
    }


    static IWhetstone _2D_set0(IWhetstone iwhetstone)
    {
        ws = iwhetstone;
        return iwhetstone;
    }

    public WhetstoneCloudControlManager()
    {
    }

    private static void checkService()
    {
        getService();
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
                    WhetstoneCloudManagerDeath whetstonecloudmanagerdeath = JVM INSTR new #6   <Class WhetstoneCloudControlManager$WhetstoneCloudManagerDeath>;
                    whetstonecloudmanagerdeath.WhetstoneCloudManagerDeath(ws);
                    ws.asBinder().linkToDeath(whetstonecloudmanagerdeath, 0);
                }
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
        }
        return ws;
    }

    public static int registerMiuiWhetstoneCloudSync(ComponentName componentname, CloudControlInfo cloudcontrolinfo)
    {
        checkService();
        int i = REGISTER_FAIL;
        int j = i;
        if(ws != null)
            try
            {
                j = ws.registerMiuiWhetstoneCloudSync(componentname, cloudcontrolinfo);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                componentname.printStackTrace();
                j = i;
            }
        return j;
    }

    public static int registerMiuiWhetstoneCloudSyncList(ComponentName componentname, List list)
    {
        checkService();
        int i = REGISTER_FAIL;
        int j = i;
        if(ws != null)
            try
            {
                j = ws.registerMiuiWhetstoneCloudSyncList(componentname, list);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                componentname.printStackTrace();
                j = i;
            }
        return j;
    }

    public static int unregisterMiuiWhetstoneCloudSync(ComponentName componentname)
    {
        checkService();
        int i = UNREGISTER_FAIL;
        int j = i;
        if(ws != null)
            try
            {
                j = ws.unregisterMiuiWhetstoneCloudSync(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                componentname.printStackTrace();
                j = i;
            }
        return j;
    }

    public static int REGISTER_ALREADY = 0;
    public static int REGISTER_FAIL = 0;
    public static int REGISTER_SUCCESS = 0;
    public static final String SERVICE_NAME = "miui.whetstone";
    public static int UNREGISTER_FAIL = -1;
    public static int UNREGISTER_SUCCESS = 1;
    private static IWhetstone ws = getService();

    static 
    {
        REGISTER_FAIL = -1;
        REGISTER_ALREADY = 0;
        REGISTER_SUCCESS = 1;
    }
}

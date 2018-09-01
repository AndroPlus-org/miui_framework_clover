// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.os.RemoteException;
import java.util.List;

// Referenced classes of package com.miui.whetstone:
//            CloudControlInfo

public class WhetstoneServiceNative extends IWhetstone.Stub
{

    public WhetstoneServiceNative()
    {
    }

    public void log(int i, byte abyte0[])
        throws RemoteException
    {
    }

    public void recordRTCWakeupInfo(int i, PendingIntent pendingintent, boolean flag)
    {
    }

    public int registerMiuiWhetstoneCloudSync(ComponentName componentname, CloudControlInfo cloudcontrolinfo)
        throws RemoteException
    {
        return 0;
    }

    public int registerMiuiWhetstoneCloudSyncList(ComponentName componentname, List list)
        throws RemoteException
    {
        return 0;
    }

    public int unregisterMiuiWhetstoneCloudSync(ComponentName componentname)
        throws RemoteException
    {
        return 0;
    }
}

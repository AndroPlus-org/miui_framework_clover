// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.content.res;

import android.app.ActivityManagerNative;
import android.app.IActivityManager;
import android.content.res.Configuration;
import android.content.res.MiuiConfiguration;
import android.os.RemoteException;

public class GlobalConfiguration
{

    public GlobalConfiguration()
    {
    }

    public static Configuration get()
        throws RemoteException
    {
        return ActivityManagerNative.getDefault().getConfiguration();
    }

    public static MiuiConfiguration getExtraConfig(Configuration configuration)
    {
        return configuration.extraConfig;
    }

    public static void update(Configuration configuration)
        throws RemoteException
    {
        ActivityManagerNative.getDefault().updateConfiguration(configuration);
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.os;

import android.os.IBinder;
import android.os.ServiceManager;

public class ServiceManagerUtil
{

    public ServiceManagerUtil()
    {
    }

    public static IBinder getService(String s)
    {
        return ServiceManager.getService(s);
    }
}

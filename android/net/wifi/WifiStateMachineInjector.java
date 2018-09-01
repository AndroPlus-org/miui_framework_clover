// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Binder;
import android.util.Log;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

final class WifiStateMachineInjector
{

    WifiStateMachineInjector()
    {
    }

    public static boolean cancelScan(Context context, AtomicBoolean atomicboolean)
    {
label0:
        {
            if(atomicboolean == null || !atomicboolean.get())
                break label0;
            context = ((ActivityManager)context.getSystemService("activity")).getRunningAppProcesses();
            int i = Binder.getCallingPid();
            atomicboolean = context.iterator();
            do
            {
                if(!atomicboolean.hasNext())
                    break label0;
                context = (android.app.ActivityManager.RunningAppProcessInfo)atomicboolean.next();
            } while(((android.app.ActivityManager.RunningAppProcessInfo) (context)).pid != i);
            Log.d("WifiStateMachineInj", (new StringBuilder()).append("P2P has connected, the process( name : ").append(((android.app.ActivityManager.RunningAppProcessInfo) (context)).processName).append(", pid : ").append(i).append(" ) calls wifi scan.").toString());
            if(((android.app.ActivityManager.RunningAppProcessInfo) (context)).importance != 100 || sBannedProcessName.contains(((android.app.ActivityManager.RunningAppProcessInfo) (context)).processName))
            {
                Log.d("WifiStateMachineInj", (new StringBuilder()).append("Wifi scan request comes from process ").append(((android.app.ActivityManager.RunningAppProcessInfo) (context)).pid).append(" has canceled!").toString());
                return true;
            }
        }
        return false;
    }

    private static final String TAG = "WifiStateMachineInj";
    private static final HashSet sBannedProcessName;

    static 
    {
        sBannedProcessName = new HashSet();
        sBannedProcessName.add("com.amap.android.location");
    }
}

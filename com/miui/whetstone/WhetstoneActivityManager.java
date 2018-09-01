// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.content.ComponentName;
import android.os.*;
import android.util.Log;
import com.miui.whetstone.server.IWhetstoneActivityManager;
import java.util.List;

public abstract class WhetstoneActivityManager
{
    static class WhetstoneManagerDeath
        implements android.os.IBinder.DeathRecipient
    {

        public void binderDied()
        {
            WhetstoneActivityManager._2D_set0(null);
            if(mToken != null)
                mToken.asBinder().unlinkToDeath(this, 0);
        }

        private IWhetstoneActivityManager mToken;

        WhetstoneManagerDeath(IWhetstoneActivityManager iwhetstoneactivitymanager)
        {
            mToken = iwhetstoneactivitymanager;
        }
    }


    static IWhetstoneActivityManager _2D_set0(IWhetstoneActivityManager iwhetstoneactivitymanager)
    {
        ws = iwhetstoneactivitymanager;
        return iwhetstoneactivitymanager;
    }

    public WhetstoneActivityManager()
    {
    }

    public static void addAppToServiceControlWhitelist(List list)
    {
        checkService();
        if(ws == null)
            break MISSING_BLOCK_LABEL_18;
        ws.addAppToServiceControlWhitelist(list);
_L1:
        return;
        list;
        list.printStackTrace();
          goto _L1
    }

    public static void bindWhetstoneService(IBinder ibinder)
    {
        checkService();
        if(ws == null)
            break MISSING_BLOCK_LABEL_18;
        ws.bindWhetstoneService(ibinder);
_L1:
        return;
        ibinder;
        ibinder.printStackTrace();
          goto _L1
    }

    public static void checkApplicationsMemoryThreshold(String s, int i, long l)
    {
        checkService();
        if(ws == null)
            break MISSING_BLOCK_LABEL_20;
        ws.checkApplicationsMemoryThreshold(s, i, l);
_L1:
        return;
        s;
        s.printStackTrace();
          goto _L1
    }

    public static boolean checkIfPackageIsLocked(String s)
    {
        checkService();
        if(ws == null)
            break MISSING_BLOCK_LABEL_26;
        boolean flag = ws.checkIfPackageIsLocked(s);
        return flag;
        s;
        s.printStackTrace();
        return false;
    }

    public static boolean checkIfPackageIsLocked(String s, int i)
    {
        checkService();
        if(ws == null)
            break MISSING_BLOCK_LABEL_27;
        boolean flag = ws.checkIfPackageIsLockedWithUserId(s, i);
        return flag;
        s;
        s.printStackTrace();
        return false;
    }

    private static void checkService()
    {
        getService();
    }

    public static void clearDeadAppFromNative()
    {
        checkService();
        if(ws == null)
            break MISSING_BLOCK_LABEL_17;
        ws.clearDeadAppFromNative();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        remoteexception.printStackTrace();
          goto _L1
    }

    public static Long getAndroidCachedEmptyProcessMemory()
    {
        checkService();
        long l = 0L;
        long l1 = l;
        if(ws != null)
            try
            {
                l1 = ws.getAndroidCachedEmptyProcessMemory();
            }
            catch(RemoteException remoteexception)
            {
                remoteexception.printStackTrace();
                l1 = l;
            }
        return Long.valueOf(l1);
    }

    public static boolean getConnProviderNames(String s, int i, List list)
    {
        checkService();
        if(ws == null)
            break MISSING_BLOCK_LABEL_34;
        boolean flag = ws.getConnProviderNames(s, i, list);
        return flag;
        s;
        Log.e("whetstone.activity", Log.getStackTraceString(s));
        return false;
    }

    public static String getPackageNamebyPid(int i)
    {
        Object obj = null;
        checkService();
        String s = obj;
        if(ws != null)
            try
            {
                s = ws.getPackageNamebyPid(i);
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
                exception = obj;
            }
        return s;
    }

    private static IWhetstoneActivityManager getService()
    {
        if(ws == null)
        {
            ws = com.miui.whetstone.server.IWhetstoneActivityManager.Stub.asInterface(ServiceManager.getService("whetstone.activity"));
            try
            {
                if(ws != null)
                {
                    WhetstoneManagerDeath whetstonemanagerdeath = JVM INSTR new #6   <Class WhetstoneActivityManager$WhetstoneManagerDeath>;
                    whetstonemanagerdeath.WhetstoneManagerDeath(ws);
                    ws.asBinder().linkToDeath(whetstonemanagerdeath, 0);
                }
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
        }
        return ws;
    }

    public static int getSystemPid()
    {
        boolean flag = false;
        checkService();
        int i = ((flag) ? 1 : 0);
        if(ws != null)
            try
            {
                i = ws.getSystemPid();
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
                i = ((flag) ? 1 : 0);
            }
        return i;
    }

    public static void removeAppFromServiceControlWhitelist(String s)
    {
        checkService();
        if(ws == null)
            break MISSING_BLOCK_LABEL_18;
        ws.removeAppFromServiceControlWhitelist(s);
_L1:
        return;
        s;
        s.printStackTrace();
          goto _L1
    }

    public static boolean removeTaskById(int i, boolean flag)
    {
        boolean flag1 = false;
        checkService();
        boolean flag2 = flag1;
        if(ws != null)
            try
            {
                flag2 = ws.removeTaskById(i, flag);
            }
            catch(RemoteException remoteexception)
            {
                Log.e("whetstone.activity", Log.getStackTraceString(remoteexception));
                flag2 = flag1;
            }
        return flag2;
    }

    public static boolean scheduleDestoryActivities(int i)
    {
        boolean flag = false;
        checkService();
        boolean flag1 = flag;
        if(ws != null)
            try
            {
                flag1 = ws.distoryActivity(i);
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
                flag1 = flag;
            }
        return flag1;
    }

    public static boolean scheduleTrimMemory(int i, int j)
    {
        checkService();
        if(ws != null)
            try
            {
                ws.scheduleTrimMemory(i, j);
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
        return false;
    }

    public static void setWhetstonePackageInfo(List list, boolean flag)
    {
        checkService();
        if(ws == null)
            break MISSING_BLOCK_LABEL_19;
        ws.setWhetstonePackageInfo(list, flag);
_L1:
        return;
        list;
        Log.e("whetstone.activity", Log.getStackTraceString(list));
          goto _L1
    }

    public static void updateApplicationsMemoryThreshold(List list)
    {
        checkService();
        if(ws == null)
            break MISSING_BLOCK_LABEL_18;
        ws.updateApplicationsMemoryThreshold(list);
_L1:
        return;
        list;
        list.printStackTrace();
          goto _L1
    }

    public static void updateFrameworkCommonConfig(String s)
    {
        checkService();
        if(ws == null)
            break MISSING_BLOCK_LABEL_18;
        ws.updateFrameworkCommonConfig(s);
_L1:
        return;
        s;
        s.printStackTrace();
          goto _L1
    }

    public static void updateUserLockedAppList(List list)
    {
        checkService();
        if(ws == null)
            break MISSING_BLOCK_LABEL_18;
        ws.updateUserLockedAppList(list);
_L1:
        return;
        list;
        list.printStackTrace();
          goto _L1
    }

    public static void updateUserLockedAppList(List list, int i)
    {
        checkService();
        if(ws == null)
            break MISSING_BLOCK_LABEL_19;
        ws.updateUserLockedAppListWithUserId(list, i);
_L1:
        return;
        list;
        list.printStackTrace();
          goto _L1
    }

    public boolean setPerformanceComponents(ComponentName acomponentname[])
    {
        checkService();
        boolean flag = false;
        boolean flag1 = flag;
        if(ws != null)
            try
            {
                flag1 = ws.setPerformanceComponents(acomponentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName acomponentname[])
            {
                acomponentname.printStackTrace();
                flag1 = flag;
            }
        return flag1;
    }

    public static final String SERVICE_NAME = "whetstone.activity";
    private static IWhetstoneActivityManager ws = null;

}

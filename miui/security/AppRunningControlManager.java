// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.security;

import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.util.Slog;
import java.util.List;

// Referenced classes of package miui.security:
//            IAppRunningControlManager, ISecurityManager

public class AppRunningControlManager
{

    private AppRunningControlManager(IBinder ibinder)
    {
        mService = IAppRunningControlManager.Stub.asInterface(ibinder);
    }

    public static Intent getBlockActivityIntent(Context context, String s, Intent intent, boolean flag, int i)
    {
        AppRunningControlManager apprunningcontrolmanager = getInstance();
        Context context1 = null;
        if(apprunningcontrolmanager != null)
        {
            context = apprunningcontrolmanager.getBlockActivityIntentInner(context, s, intent, flag, i);
            context1 = context;
            if(context != null)
                return context;
        }
        return context1;
    }

    private Intent getBlockActivityIntentInner(Context context, String s, Intent intent, boolean flag, int i)
    {
        try
        {
            context = mService.getBlockActivityIntent(s, intent, flag, i);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Slog.e("AppRunningControlManager", "Remote service has died", context);
            return null;
        }
        return context;
    }

    public static AppRunningControlManager getInstance()
    {
        if(sInstance != null)
            break MISSING_BLOCK_LABEL_47;
        IBinder ibinder;
        AppRunningControlManager apprunningcontrolmanager;
        try
        {
            ibinder = ISecurityManager.Stub.asInterface(ServiceManager.getService("security")).getAppRunningControlIBinder();
        }
        catch(Exception exception)
        {
            throw new RuntimeException("system service died", exception);
        }
        if(ibinder != null)
            break MISSING_BLOCK_LABEL_34;
        Slog.d("AppRunningControlManager", "AppRunningControlIBinder is null");
        return null;
        apprunningcontrolmanager = JVM INSTR new #2   <Class AppRunningControlManager>;
        apprunningcontrolmanager.AppRunningControlManager(ibinder);
        sInstance = apprunningcontrolmanager;
        return sInstance;
    }

    public static boolean matchRule(String s, int i)
    {
        AppRunningControlManager apprunningcontrolmanager = getInstance();
        if(apprunningcontrolmanager != null)
            return apprunningcontrolmanager.matchRuleInner(s, i);
        else
            return false;
    }

    private boolean matchRuleInner(String s, int i)
    {
        boolean flag;
        try
        {
            flag = mService.matchRule(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Slog.e("AppRunningControlManager", "Remote service has died", s);
            return false;
        }
        return flag;
    }

    public void setBlackListEnable(boolean flag)
    {
        mService.setBlackListEnable(flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Slog.e("AppRunningControlManager", "Remote service has died", remoteexception);
          goto _L1
    }

    public void setDisallowRunningList(List list, Intent intent)
    {
        try
        {
            mService.setDisallowRunningList(list, intent);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            Slog.e("AppRunningControlManager", "Remote service has died", list);
        }
    }

    private static final String TAG = "AppRunningControlManager";
    private static AppRunningControlManager sInstance;
    private IAppRunningControlManager mService;
}

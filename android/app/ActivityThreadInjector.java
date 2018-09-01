// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.*;
import android.util.EventLog;
import miui.log.SystemLogSwitchesConfigManager;
import miui.os.Build;

// Referenced classes of package android.app:
//            ActivityThread, LoadedApk

public class ActivityThreadInjector
{

    public ActivityThreadInjector()
    {
    }

    static void bindApplicationInjector(Context context, ApplicationInfo applicationinfo)
    {
    }

    static void checkHandleMessageTime(long l, int i)
    {
        if(!mEnableLifecycleSample)
            return;
        l = SystemClock.uptimeMillis() - l;
        if(l >= (long)AM_LIFECYCLE_SAMPLE_THRESHOLD)
            EventLog.writeEvent(30100, new Object[] {
                Integer.valueOf(UserHandle.myUserId()), ActivityThread.currentProcessName(), Integer.valueOf(i), Long.valueOf(l)
            });
    }

    static void checkHandleMessageTime(long l, Message message)
    {
        int i;
        if(message != null)
            i = message.what;
        else
            i = -1;
        checkHandleMessageTime(l, i);
    }

    static void clearCachedDrawables()
    {
    }

    static void enableAppLogSwitch()
    {
        SystemLogSwitchesConfigManager.enableLogSwitch(false);
    }

    public static void enableStrictMode()
    {
        if(Build.IS_STABLE_VERSION)
            return;
        boolean flag = SystemProperties.getBoolean("persist.sys.strictmode.visual", false);
        android.os.StrictMode.ThreadPolicy.Builder builder = (new android.os.StrictMode.ThreadPolicy.Builder()).detectNetwork().penaltyLog();
        android.os.StrictMode.VmPolicy.Builder builder1 = (new android.os.StrictMode.VmPolicy.Builder()).detectLeakedClosableObjects().detectLeakedRegistrationObjects().detectLeakedSqlLiteObjects().penaltyLog();
        if(flag)
        {
            builder.penaltyDeath();
            builder1.penaltyDeath();
        }
        StrictMode.setThreadPolicy(builder.build());
        StrictMode.setVmPolicy(builder1.build());
    }

    public static Handler getHandler()
    {
        Handler handler = null;
        ActivityThread activitythread = ActivityThread.currentActivityThread();
        if(activitythread != null)
            handler = activitythread.getHandler();
        return handler;
    }

    public static boolean isPersistent(ActivityThread.AppBindData appbinddata)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(appbinddata != null)
        {
            flag1 = flag;
            if((appbinddata.appInfo.flags & 8) != 0)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isSystemThread()
    {
        ActivityThread activitythread = ActivityThread.currentActivityThread();
        boolean flag;
        if(activitythread != null)
            flag = activitythread.mSystemThread;
        else
            flag = false;
        return flag;
    }

    static void preloadSubActivity(ActivityThread activitythread, int i, LoadedApk loadedapk)
    {
    }

    static void raiseThreadPriority()
    {
    }

    static void updatePackageInfoForLogSwitch(ActivityThread.AppBindData appbinddata)
    {
        SystemLogSwitchesConfigManager.updatePackageName(appbinddata.appInfo.packageName);
        SystemLogSwitchesConfigManager.updateProgramName(appbinddata.processName);
    }

    private static final int AM_LIFECYCLE_SAMPLE_THRESHOLD;
    private static final int LOG_AM_LIFECYCLE_SAMPLE = 30100;
    private static final boolean mEnableLifecycleSample;

    static 
    {
        boolean flag = false;
        AM_LIFECYCLE_SAMPLE_THRESHOLD = SystemProperties.getInt("persist.sys.handler.threshold", 3000);
        if(AM_LIFECYCLE_SAMPLE_THRESHOLD > 0)
            flag = true;
        mEnableLifecycleSample = flag;
    }
}

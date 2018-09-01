// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioSystem;
import android.net.Uri;
import android.service.notification.Condition;
import android.service.notification.ZenModeConfig;
import android.util.Log;
import miui.securityspace.XSpaceUserHandle;

// Referenced classes of package android.app:
//            AppOpsManager, NotificationManager, ActivityManager

public class ExtraNotificationManager
{

    public ExtraNotificationManager()
    {
    }

    private static void applyRestriction(boolean flag, int i, AppOpsManager appopsmanager, String as[])
    {
        boolean flag1 = false;
        int j;
        if(flag)
            j = 0;
        else
            j = 1;
        appopsmanager.setRestriction(28, i, j, as);
        if(flag)
            j = ((flag1) ? 1 : 0);
        else
            j = 1;
        appopsmanager.setRestriction(3, i, j, as);
    }

    public static void enableVIPMode(Context context, boolean flag, int i)
    {
        Log.i("SilenceMode", (new StringBuilder()).append("enableVIPMode:").append(flag).toString());
        ZenModeConfig zenmodeconfig = getZenModeConfig(context);
        i = getZenMode(context);
        zenmodeconfig.allowCalls = flag;
        zenmodeconfig.allowMessages = flag;
        zenmodeconfig.allowEvents = false;
        if(!flag && i == 1)
            AudioSystem.setForceUse(8, 2);
        else
            AudioSystem.setForceUse(8, 0);
        setZenModeConfig(context, zenmodeconfig);
    }

    public static Uri getConditionId(Context context)
    {
        Object obj = null;
        context = getZenModeConfig(context);
        if(((ZenModeConfig) (context)).manualRule == null)
            context = obj;
        else
            context = ((ZenModeConfig) (context)).manualRule.conditionId;
        return context;
    }

    public static long getRemainTime(Context context)
    {
        long l = 0L;
        long l1 = ZenModeConfig.tryParseCountdownConditionId(getConditionId(context));
        if(l1 != 0L)
            l = l1 - System.currentTimeMillis();
        return l;
    }

    public static int getZenMode(Context context)
    {
        return NotificationManager.from(context).getZenMode();
    }

    public static ZenModeConfig getZenModeConfig(Context context)
    {
        return NotificationManager.from(context).getZenModeConfig();
    }

    public static boolean isQuietModeEnable(Context context, int i)
    {
        boolean flag = true;
        if(android.provider.MiuiSettings.SilenceMode.isSupported)
            return false;
        if(XSpaceUserHandle.isXSpaceUserCalling())
            i = 999;
        if(android.provider.Settings.Secure.getIntForUser(context.getContentResolver(), "quiet_mode_enable", 0, i) != 1)
            flag = false;
        return flag;
    }

    public static boolean isRepeatedCallEnable(Context context)
    {
        return getZenModeConfig(context).allowRepeatCallers;
    }

    public static boolean isSilenceModeEnable(Context context, int i)
    {
        boolean flag = false;
        if(!XSpaceUserHandle.isXSpaceUserCalling());
        if(getZenMode(context) != 0)
            flag = true;
        return flag;
    }

    public static boolean isVIPModeEnable(Context context)
    {
        return getZenModeConfig(context).allowCalls;
    }

    public static void setQuietMode(Context context, boolean flag, int i)
    {
        int j = getZenMode(context);
        if(flag)
        {
            if(j == 0)
                setZenMode(context, 1, null);
            android.provider.Settings.Secure.putIntForUser(context.getContentResolver(), "quiet_mode_enable", 1, i);
            android.provider.Settings.Secure.putIntForUser(context.getContentResolver(), "quiet_mode_enable", 1, 999);
        } else
        {
            if(j == 1)
                setZenMode(context, 0, null);
            android.provider.Settings.Secure.putIntForUser(context.getContentResolver(), "quiet_mode_enable", 0, i);
            android.provider.Settings.Secure.putIntForUser(context.getContentResolver(), "quiet_mode_enable", 0, 999);
        }
    }

    public static void setRepeatedCallEnable(Context context, boolean flag)
    {
        ZenModeConfig zenmodeconfig = getZenModeConfig(context).copy();
        zenmodeconfig.allowRepeatCallers = flag;
        setZenModeConfig(context, zenmodeconfig);
    }

    public static void setSilenceMode(Context context, int i, Uri uri)
    {
        Log.i("SilenceMode", (new StringBuilder()).append("setSilenceMode mode:").append(i).toString());
        setZenMode(context, i, uri);
    }

    public static void setZenMode(Context context, int i)
    {
        NotificationManager.from(context).setZenMode(i, null, null);
    }

    public static void setZenMode(Context context, int i, Uri uri)
    {
        if(!android.provider.MiuiSettings.SilenceMode.isSupported)
        {
            NotificationManager.from(context).setZenMode(i, null, null);
            return;
        }
        boolean flag;
        Object obj;
        int j;
        if(android.provider.Settings.System.getIntForUser(context.getContentResolver(), "vibrate_in_silent", 1, -2) == 0)
            flag = true;
        else
            flag = false;
        obj = (AudioManager)context.getSystemService("audio");
        j = i;
        if(((AudioManager) (obj)).getLastAudibleStreamVolume(2) == 0)
        {
            j = i;
            if(((AudioManager) (obj)).getLastAudibleStreamVolume(4) == 0)
            {
                j = i;
                if(((AudioManager) (obj)).getLastAudibleStreamVolume(3) == 0)
                {
                    j = i;
                    if(i == 1)
                    {
                        j = i;
                        if(flag)
                            j = 2;
                    }
                }
            }
        }
        obj = uri;
        if(uri == null)
            obj = (new Condition(Condition.newId(context).appendPath("forever").build(), "", "", "", 0, 1, 0)).id;
        if(context != null && context.getPackageName().contains("settings"))
            android.provider.MiuiSettings.SilenceMode.reportRingerModeInfo("silence_DND", android.provider.MiuiSettings.SilenceMode.MISTAT_RINGERMODE_LIST[j], "0", System.currentTimeMillis());
        NotificationManager.from(context).setZenMode(j, ((Uri) (obj)), "miui_manual");
    }

    public static boolean setZenModeConfig(Context context, ZenModeConfig zenmodeconfig)
    {
        NotificationManager.Policy policy = NotificationManager.from(context).getNotificationPolicy();
        int i = policy.priorityCategories;
        if(zenmodeconfig.allowEvents)
            i |= 2;
        else
            i &= -3;
        if(zenmodeconfig.allowCalls)
            i |= 8;
        else
            i &= -9;
        if(zenmodeconfig.allowMessages)
            i |= 4;
        else
            i &= -5;
        if(zenmodeconfig.allowRepeatCallers)
            i |= 0x10;
        else
            i &= 0xffffffef;
        zenmodeconfig = new NotificationManager.Policy(i, zenmodeconfig.allowCallsFrom, zenmodeconfig.allowMessagesFrom, policy.suppressedVisualEffects);
        NotificationManager.from(context).setNotificationPolicy(zenmodeconfig);
        return true;
    }

    public static void startCountDownSilenceMode(Context context, int i, int j)
    {
        if(j == 0)
        {
            setZenMode(context, i, null);
            return;
        } else
        {
            setZenMode(context, i, ZenModeConfig.toTimeCondition(context, j, ActivityManager.getCurrentUser()).id);
            return;
        }
    }

    public static void updateRestriction(Context context)
    {
        AppOpsManager appopsmanager;
        String as[];
        String as1[];
        boolean flag;
        boolean flag1;
        boolean flag2;
        if(!android.provider.MiuiSettings.SilenceMode.isSupported)
            return;
        appopsmanager = (AppOpsManager)context.getSystemService("appops");
        as = new String[1];
        as[0] = "com.android.cellbroadcastreceiver";
        as1 = new String[4];
        as1[0] = "com.android.systemui";
        as1[1] = "android";
        as1[2] = "com.android.cellbroadcastreceiver";
        as1[3] = "com.android.server.telecom";
        int i = getZenMode(context);
        context = getZenModeConfig(context);
        flag = false;
        switch(i)
        {
        default:
            flag1 = true;
            flag2 = true;
            break;

        case 1: // '\001'
            break MISSING_BLOCK_LABEL_128;
        }
_L1:
        if(flag)
            context = as1;
        else
            context = as;
        applyRestriction(flag2, 6, appopsmanager, context);
        if(!flag)
            as1 = as;
        applyRestriction(flag1, 5, appopsmanager, as1);
        return;
        flag2 = false;
        flag1 = false;
        if(!((ZenModeConfig) (context)).allowCalls)
            flag = ((ZenModeConfig) (context)).allowRepeatCallers;
        else
            flag = true;
          goto _L1
    }
}

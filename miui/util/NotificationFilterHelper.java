// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.app.*;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.miui.AppOpsUtils;
import android.net.Uri;
import android.os.*;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import java.util.*;
import miui.os.Build;
import miui.securityspace.XSpaceUserHandle;

public class NotificationFilterHelper
{

    public NotificationFilterHelper()
    {
    }

    private static boolean areNotificationsEnabled(Context context, String s)
    {
        return areNotificationsEnabled(context, s, getAppUid(context, s));
    }

    private static boolean areNotificationsEnabled(Context context, String s, int i)
    {
        boolean flag = true;
        boolean flag1;
        try
        {
            flag1 = nm.areNotificationsEnabledForPackage(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            flag1 = flag;
        }
        return flag1;
    }

    public static boolean canSendNotifications(Context context, String s)
    {
        boolean flag = true;
        if(getAppFlag(context, s, true) == 3)
            flag = false;
        return flag;
    }

    public static boolean canSystemNotificationBeBlocked(String s)
    {
        return sNotificationCanBeBlockedList.contains(s);
    }

    public static void enableNotifications(Context context, String s, int i, boolean flag)
    {
        boolean flag1;
        flag1 = false;
        if(!flag && isNotificationForcedFor(context, s))
            return;
        if(!XSpaceUserHandle.isUidBelongtoXSpace(i)) goto _L2; else goto _L1
_L1:
        resolveAssociatedUid(context, UserHandle.OWNER, s, flag);
_L8:
        nm.setNotificationsEnabledForPackage(s, i, flag);
        if(Build.IS_TABLET && android.os.Build.VERSION.SDK_INT < 26) goto _L4; else goto _L3
_L3:
        ApplicationInfo applicationinfo = context.getPackageManager().getApplicationInfo(s, 0);
        if(applicationinfo == null) goto _L4; else goto _L5
_L5:
        if((applicationinfo.flags & 1) != 0) goto _L4; else goto _L6
_L6:
        if(flag)
            i = ((flag1) ? 1 : 0);
        else
            i = 1;
        AppOpsUtils.setMode(context, 11, s, i);
_L4:
        return;
_L2:
        if(context.getUserId() != 0 || !XSpaceUserHandle.isAppInXSpace(context, s)) goto _L8; else goto _L7
_L7:
        UserHandle userhandle = JVM INSTR new #180 <Class UserHandle>;
        userhandle.UserHandle(999);
        resolveAssociatedUid(context, userhandle, s, flag);
          goto _L8
        context;
          goto _L4
        context;
          goto _L4
    }

    public static void enableNotifications(Context context, String s, boolean flag)
    {
        enableNotifications(context, s, getAppUid(context, s), flag);
    }

    private static void enableStatusIcon(Context context, String s, int i)
    {
        getSharedPreferences(context).edit().putInt(s, i).apply();
    }

    public static void enableStatusIcon(Context context, String s, boolean flag)
    {
        byte byte0;
        if(flag)
            byte0 = 2;
        else
            byte0 = 1;
        enableStatusIcon(context, s, byte0);
    }

    public static int getAppFlag(Context context, String s, int i, boolean flag)
    {
        int j;
        if(flag)
            flag = areNotificationsEnabled(context, s, i);
        else
            flag = true;
        if(flag)
        {
            j = getSharedPreferences(context).getInt(s, 0);
            i = j;
            if(j == 0)
                i = getDefaultFlag(context, s, false);
            j = i;
            if(i == 0)
                j = 1;
        } else
        {
            j = 3;
        }
        return j;
    }

    public static int getAppFlag(Context context, String s, boolean flag)
    {
        return getAppFlag(context, s, getAppUid(context, s), flag);
    }

    private static int getAppUid(Context context, String s)
    {
        int i = 0;
        int j = context.getPackageManager().getApplicationInfo(s, 0).uid;
        i = j;
_L2:
        return i;
        context;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static Uri getCustomSoundUri(Context context, Uri uri, StatusBarNotification statusbarnotification)
    {
        return uri;
    }

    private static int getDefaultFlag(Context context, String s, boolean flag)
    {
        byte byte0 = 2;
        initFilterList(context);
        if(!flag)
        {
            if(!sFloatWhiteList.contains(s))
                byte0 = 0;
            return byte0;
        }
        if(!sKeyguardWhiteList.contains(s))
            byte0 = 0;
        return byte0;
    }

    public static int getImportance(Context context, String s)
    {
        return getSharedPreferences(context).getInt((new StringBuilder()).append(s).append("_importance").toString(), 0);
    }

    public static HashSet getNotificationForcedEnabledList()
    {
        return sNotificationForcedEnabledList;
    }

    private static String getRealPackageName(StatusBarNotification statusbarnotification)
    {
        if(TextUtils.isEmpty(statusbarnotification.getNotification().extraNotification.getTargetPkg()))
            statusbarnotification = statusbarnotification.getPackageName().toString();
        else
            statusbarnotification = statusbarnotification.getNotification().extraNotification.getTargetPkg().toString();
        return statusbarnotification;
    }

    public static SharedPreferences getSharedPreferences(Context context)
    {
        Object obj = context;
        if(XSpaceUserHandle.isXSpaceUserId(context.getUserId()))
            try
            {
                obj = context.createPackageContextAsUser("com.android.systemui", 2, UserHandle.OWNER);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("NotificationFilterHelper", "Can't find pkg: com.android.systemui", ((Throwable) (obj)));
                obj = context;
            }
        context = ((Context) (obj));
        if(!((Context) (obj)).getPackageName().equals("com.android.systemui"))
            try
            {
                context = ((Context) (obj)).createPackageContext("com.android.systemui", 2);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                context.printStackTrace();
                context = ((Context) (obj));
            }
        return context.getSharedPreferences("app_notification", 4);
    }

    private static Set getWhiteListFromCache(Context context, String s)
    {
        Object obj = null;
        try
        {
            context = getSharedPreferences(context).getStringSet(s, null);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("NotificationFilterHelper", (new StringBuilder()).append("error get whiteList: ").append(s).toString());
            context = obj;
        }
        return context;
    }

    private static void initFilterList(Context context)
    {
        boolean flag = false;
        int i = android.provider.Settings.Global.getInt(context.getContentResolver(), "float_hashcode", -1);
        if(sFloatWhiteList.size() == 0 || i != sFloatHashCode)
        {
            sFloatWhiteList.clear();
            Set set = getWhiteListFromCache(context, "float_whitelist");
            int j;
            if(set != null && set.isEmpty() ^ true)
            {
                sFloatWhiteList.addAll(set);
            } else
            {
                String as[] = context.getResources().getStringArray(0x11090007);
                int i1 = as.length;
                int k = 0;
                while(k < i1) 
                {
                    String s = as[k];
                    sFloatWhiteList.add(s);
                    k++;
                }
            }
            sFloatHashCode = sFloatWhiteList.hashCode();
        }
        j = android.provider.Settings.Global.getInt(context.getContentResolver(), "keyguard_hashcode", -1);
        if(sKeyguardWhiteList.size() == 0 || j != sKeyguardHashCode)
        {
            sKeyguardWhiteList.clear();
            set = getWhiteListFromCache(context, "keyguard_whitelist");
            if(set != null && set.isEmpty() ^ true)
            {
                sKeyguardWhiteList.addAll(set);
            } else
            {
                context = context.getResources().getStringArray(0x11090008);
                int j1 = context.length;
                int l = ((flag) ? 1 : 0);
                while(l < j1) 
                {
                    Object obj = context[l];
                    sKeyguardWhiteList.add(obj);
                    l++;
                }
            }
            sKeyguardHashCode = sKeyguardWhiteList.hashCode();
        }
    }

    public static boolean isAllowed(Context context, StatusBarNotification statusbarnotification, String s)
    {
        return isAllowed(context, getRealPackageName(statusbarnotification), s);
    }

    public static boolean isAllowed(Context context, String s, String s1)
    {
        boolean flag = true;
        boolean flag1 = true;
        if("_keyguard".equals(s1) && getSharedPreferences(context).contains((new StringBuilder()).append(s).append(s1).toString()) ^ true)
        {
            if(getDefaultFlag(context, s, true) != 2)
                flag1 = false;
            return flag1;
        }
        if("_led".equals(s1) && getSharedPreferences(context).contains((new StringBuilder()).append(s).append(s1).toString()) ^ true)
        {
            boolean flag2;
            if(getDefaultFlag(context, s, false) == 2)
                flag2 = flag;
            else
                flag2 = false;
            return flag2;
        } else
        {
            return getSharedPreferences(context).getBoolean((new StringBuilder()).append(s).append(s1).toString(), true);
        }
    }

    public static boolean isNotificationForcedFor(Context context, String s)
    {
        if(sNotificationForcedEnabledList.contains(s))
            return true;
        if(canSystemNotificationBeBlocked(s))
            return false;
        for(int i = UserHandle.getAppId(getAppUid(context, s)); i == 1000 || i == 1001 || i == 0;)
            return true;

        return false;
    }

    public static boolean isSystemApp(String s, PackageManager packagemanager)
    {
        boolean flag = false;
        Boolean boolean1 = (Boolean)sIsSystemApp.get(s);
        Boolean boolean2 = boolean1;
        if(boolean1 == null)
        {
            boolean2 = null;
            boolean flag1;
            try
            {
                packagemanager = packagemanager.getApplicationInfo(s, 0);
            }
            // Misplaced declaration of an exception variable
            catch(PackageManager packagemanager)
            {
                packagemanager = boolean2;
            }
            flag1 = flag;
            if(packagemanager != null)
            {
                flag1 = flag;
                if((((ApplicationInfo) (packagemanager)).flags & 1) != 0)
                    flag1 = true;
            }
            boolean2 = Boolean.valueOf(flag1);
            sIsSystemApp.put(s, boolean2);
        }
        return boolean2.booleanValue();
    }

    private static void resolveAssociatedUid(Context context, UserHandle userhandle, String s, boolean flag)
    {
        int i = getAppUid(context.createPackageContextAsUser(s, 2, userhandle), s);
        nm.setNotificationsEnabledForPackage(s, i, flag);
_L1:
        return;
        context;
        Log.e("NotificationFilterHelper", (new StringBuilder()).append("Can't find pkg: ").append(s).toString(), context);
          goto _L1
        context;
        Log.e("NotificationFilterHelper", "Can't talk to NotificationManagerService", context);
          goto _L1
    }

    public static void setAllow(Context context, String s, String s1, boolean flag)
    {
        getSharedPreferences(context).edit().putBoolean((new StringBuilder()).append(s).append(s1).toString(), flag).commit();
    }

    public static void setImportance(Context context, String s, int i)
    {
        getSharedPreferences(context).edit().putInt((new StringBuilder()).append(s).append("_importance").toString(), i).apply();
    }

    public static void updateFloatWhiteList(Context context, List list)
    {
        sFloatWhiteList.clear();
        sFloatWhiteList.addAll(list);
        sFloatHashCode = sFloatWhiteList.hashCode();
        android.provider.Settings.Global.putInt(context.getContentResolver(), "float_hashcode", sFloatHashCode);
        getSharedPreferences(context).edit().putStringSet("float_whitelist", sFloatWhiteList).apply();
    }

    public static void updateKeyguardWhitelist(Context context, List list)
    {
        sKeyguardWhiteList.clear();
        sKeyguardWhiteList.addAll(list);
        sKeyguardHashCode = sKeyguardWhiteList.hashCode();
        android.provider.Settings.Global.putInt(context.getContentResolver(), "keyguard_hashcode", sKeyguardHashCode);
        getSharedPreferences(context).edit().putStringSet("keyguard_whitelist", sKeyguardWhiteList).apply();
    }

    private static final String APP_NOTIFICATION = "app_notification";
    public static final int DISABLE_ALL = 3;
    public static final int DISABLE_FLOATING = 1;
    public static final int ENABLE = 2;
    public static final String IMPORTANCE = "_importance";
    public static final int IMPORTANCE_DEFAULT = 0;
    public static final int IMPORTANCE_HIGH = 1;
    public static final int IMPORTANCE_LOW = -1;
    public static final String KEYGUARD = "_keyguard";
    private static final String KEY_FLOAT_HASHCODE = "float_hashcode";
    public static final String KEY_FLOAT_WHITELIST = "float_whitelist";
    private static final String KEY_KEYGUARD_HASHCODE = "keyguard_hashcode";
    public static final String KEY_KEYGUARD_WHITELIST = "keyguard_whitelist";
    public static final String LED = "_led";
    public static final String MESSAGE = "_message";
    public static final int NONE = 0;
    public static final String SOUND = "_sound";
    private static final String SYSTEMUI_PACKAGE_NAME = "com.android.systemui";
    private static final String TAG = "NotificationFilterHelper";
    public static final String VIBRATE = "_vibrate";
    private static INotificationManager nm = android.app.INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
    private static int sFloatHashCode = -1;
    private static HashSet sFloatWhiteList = new HashSet();
    private static HashMap sIsSystemApp = new HashMap();
    private static int sKeyguardHashCode = -1;
    private static HashSet sKeyguardWhiteList = new HashSet();
    private static HashSet sNotificationCanBeBlockedList;
    private static HashSet sNotificationForcedEnabledList;

    static 
    {
        sNotificationCanBeBlockedList = new HashSet();
        sNotificationForcedEnabledList = new HashSet();
        sNotificationCanBeBlockedList.add("com.miui.fm");
        sNotificationCanBeBlockedList.add("com.miui.antispam");
        sNotificationForcedEnabledList.add("android");
        sNotificationForcedEnabledList.add("com.xiaomi.xmsf");
        sNotificationForcedEnabledList.add("com.android.incallui");
        sNotificationForcedEnabledList.add("com.android.deskclock");
        sNotificationForcedEnabledList.add("com.android.mms");
        sNotificationForcedEnabledList.add("com.android.bluetooth");
        sNotificationForcedEnabledList.add("com.android.updater");
        sNotificationForcedEnabledList.add("com.android.providers.downloads");
        sNotificationForcedEnabledList.add("com.miui.hybrid");
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.security;

import android.app.Activity;
import android.app.AppGlobals;
import android.content.*;
import android.content.pm.*;
import android.graphics.Bitmap;
import android.miui.AppOpsUtils;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import android.util.Slog;
import com.android.internal.app.IWakePathCallback;
import java.lang.reflect.Method;
import java.util.List;
import miui.os.Build;
import miui.securityspace.XSpaceUserHandle;
import miui.util.FeatureParser;

// Referenced classes of package miui.security:
//            ISecurityManager, AppRunningControlManager

public class SecurityManager
{

    public SecurityManager(ISecurityManager isecuritymanager)
    {
        mService = isecuritymanager;
    }

    private void activityFinish(Activity activity)
    {
        activity.finish();
        if(activity.isFinishing())
            return;
        if(sActivityFinishMethod == null) goto _L2; else goto _L1
_L1:
        if(android.os.Build.VERSION.SDK_INT < 24) goto _L4; else goto _L3
_L3:
        sActivityFinishMethod.invoke(activity, new Object[] {
            Integer.valueOf(0)
        });
_L5:
        return;
_L4:
        try
        {
            sActivityFinishMethod.invoke(activity, new Object[] {
                Boolean.valueOf(false)
            });
        }
        // Misplaced declaration of an exception variable
        catch(Activity activity)
        {
            Log.e("SecurityManager", " FinishMethod.invoke error ", activity);
        }
          goto _L5
_L2:
        if(activity.getParent() != null) goto _L5; else goto _L6
_L6:
        activity.finishAffinity();
          goto _L5
    }

    public static boolean checkCallingPackage(Context context, String as[])
    {
        if(!_2D_assertionsDisabled && as == null)
            throw new AssertionError();
        int i = Binder.getCallingUid();
        String as1[] = context.getPackageManager().getPackagesForUid(i);
        if(as1 == null)
            return false;
        int k = as1.length;
        for(int j = 0; j < k; j++)
        {
            context = as1[j];
            int l = as.length;
            for(int i1 = 0; i1 < l; i1++)
                if(context.equals(as[i1]))
                    return true;

        }

        return false;
    }

    private static void checkTime(long l, String s)
    {
        long l1 = SystemClock.elapsedRealtime();
        if(l1 - l > 100L)
            Slog.w("SecurityManager", (new StringBuilder()).append("MIUILOG-checkTime:Slow operation: ").append(l1 - l).append("ms so far, now at ").append(s).toString());
    }

    public static Intent getCheckAccessIntent(boolean flag, String s, Intent intent, int i, boolean flag1, int j)
    {
        String s1 = "miui.intent.action.CHECK_ACCESS_CONTROL";
        if(!flag)
            s1 = "com.miui.gamebooster.action.ACCESS_WINDOWCALLACTIVITY";
        String s2 = "com.miui.securitycenter";
        Object obj = s1;
        s1 = s2;
        if(FeatureParser.getBoolean("is_pad", false))
        {
            obj = "android.app.action.CHECK_ACCESS_CONTROL_PAD";
            s1 = "com.android.settings";
        }
        obj = new Intent(((String) (obj)));
        ((Intent) (obj)).putExtra("android.intent.extra.shortcut.NAME", s);
        ((Intent) (obj)).addFlags(0x800000);
        ((Intent) (obj)).setPackage(s1);
        if(intent != null)
        {
            if((intent.getFlags() & 0x2000000) != 0)
                ((Intent) (obj)).addFlags(0x2000000);
            intent.addFlags(0x1000000);
            if(flag1)
            {
                if(i >= 0)
                    intent.addFlags(0x2000000);
                if((intent.getFlags() & 0x10000000) == 0)
                    ((Intent) (obj)).addFlags(0x20000000);
            } else
            {
                intent.addFlags(0x10000000);
                ((Intent) (obj)).addFlags(0x8000000);
            }
            ((Intent) (obj)).putExtra("android.intent.extra.INTENT", intent);
        } else
        {
            ((Intent) (obj)).addFlags(0x20000000);
        }
        if(j == 999)
            ((Intent) (obj)).putExtra("originating_uid", j);
        return ((Intent) (obj));
    }

    public static int getUserHandle(int i)
    {
        if(XSpaceUserHandle.isXSpaceUserId(i))
            return 0;
        else
            return i;
    }

    private static native void hook();

    public static void init()
    {
        if(android.os.Build.VERSION.SDK_INT == 19)
            hook();
    }

    public int activityResume(Intent intent)
    {
        int i;
        try
        {
            i = mService.activityResume(intent);
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            intent.printStackTrace();
            return 0;
        }
        return i;
    }

    public void addAccessControlPass(String s)
    {
        mService.addAccessControlPass(s);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void addAccessControlPassForUser(String s, int i)
    {
        try
        {
            mService.addAccessControlPassForUser(s, i);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("addAccessControlPassForUser exception", s);
        }
    }

    public boolean areNotificationsEnabledForPackage(String s, int i)
    {
        boolean flag;
        try
        {
            flag = mService.areNotificationsEnabledForPackage(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("security manager has died", s);
        }
        return flag;
    }

    public void checkAccessControl(Activity activity, int i)
    {
        String s;
        Intent intent;
        if(activity == null || activity.getParent() != null)
            break MISSING_BLOCK_LABEL_195;
        s = activity.getPackageName();
        intent = new Intent();
        intent.setComponent(new ComponentName(s, activity.getClass().getName()));
        if(!"com.miui.gallery".equals(s) || !"com.miui.gallery.activity.ExternalPhotoPageActivity".equals(activity.getClass().getName()))
            break MISSING_BLOCK_LABEL_97;
        Intent intent1 = activity.getIntent();
        int j;
        if(intent1 != null)
            try
            {
                intent.putExtra("skip_interception", intent1.getBooleanExtra("skip_interception", false));
            }
            catch(Throwable throwable)
            {
                Log.e("SecurityManager", "checkAccessControl sourceIntent", throwable);
            }
        j = activityResume(intent);
        if((j & 1) == 0 || (j & 2) == 0)
            return;
        if((j & 8) != 0)
        {
            activity.setResult(0);
            activityFinish(activity);
            return;
        }
        if((j & 4) != 0)
            return;
        intent = getCheckAccessIntent(true, s, null, -1, true, i);
        intent.putExtra("android.app.extra.PROTECTED_APP_TOKEN", activity.getActivityToken());
        activity.startActivityForResult(intent, -1, null);
_L1:
        return;
        activity;
        Log.e("SecurityManager", "checkAccessControl", activity);
          goto _L1
    }

    public boolean checkAccessControlPass(String s)
    {
        return checkAccessControlPass(s, null);
    }

    public boolean checkAccessControlPass(String s, Intent intent)
    {
        boolean flag = false;
        boolean flag1 = mService.checkAccessControlPass(s, intent);
        flag = flag1;
_L2:
        return flag;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean checkAccessControlPassAsUser(String s, int i)
    {
        return checkAccessControlPassAsUser(s, null, i);
    }

    public boolean checkAccessControlPassAsUser(String s, Intent intent, int i)
    {
        boolean flag = false;
        boolean flag1;
        try
        {
            flag1 = mService.checkAccessControlPassAsUser(s, intent, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            flag1 = flag;
        }
        return flag1;
    }

    public boolean checkAccessControlPassword(String s, String s1)
    {
        boolean flag;
        try
        {
            flag = mService.checkAccessControlPassword(s, s1, UserHandle.myUserId());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("security manager has died", s);
        }
        return flag;
    }

    public boolean checkAllowStartActivity(String s, String s1, Intent intent, int i)
    {
        long l;
        boolean flag;
        l = SystemClock.elapsedRealtime();
        flag = false;
        boolean flag1 = mService.checkAllowStartActivity(s, s1, intent, i);
        flag = flag1;
_L2:
        checkTime(l, "checkAllowStartActivity");
        return flag;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean checkGameBoosterAntimsgPassAsUser(String s, Intent intent, int i)
    {
        boolean flag;
        try
        {
            flag = mService.checkGameBoosterAntimsgPassAsUser(s, intent, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("checkGameBoosterAntimsgPassAsUser exception", s);
        }
        return flag;
    }

    public boolean checkSmsBlocked(Intent intent)
    {
        boolean flag;
        try
        {
            flag = mService.checkSmsBlocked(intent);
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            intent.printStackTrace();
            return false;
        }
        return flag;
    }

    public void finishAccessControl(String s)
    {
        mService.finishAccessControl(s, UserHandle.myUserId());
_L1:
        return;
        s;
        s.printStackTrace();
          goto _L1
    }

    public void finishAccessControl(String s, int i)
    {
        try
        {
            mService.finishAccessControl(s, i);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("finishAccessControl has failed", s);
        }
    }

    public String getAccessControlPasswordType()
    {
        String s;
        try
        {
            s = mService.getAccessControlPasswordType(UserHandle.myUserId());
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("security manager has died", remoteexception);
        }
        return s;
    }

    public boolean getAppPermissionControlOpen(int i)
    {
        boolean flag = false;
        try
        {
            i = mService.getAppPermissionControlOpen(i);
        }
        catch(Exception exception)
        {
            return false;
        }
        if(i != 0)
            flag = true;
        return flag;
    }

    public boolean getApplicationAccessControlEnabled(String s)
    {
        boolean flag = false;
        boolean flag1 = mService.getApplicationAccessControlEnabled(s);
        flag = flag1;
_L2:
        return flag;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean getApplicationAccessControlEnabledAsUser(String s, int i)
    {
        boolean flag = false;
        boolean flag1;
        try
        {
            flag1 = mService.getApplicationAccessControlEnabledAsUser(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            flag1 = flag;
        }
        return flag1;
    }

    public boolean getApplicationChildrenControlEnabled(String s)
    {
        boolean flag = false;
        boolean flag1 = mService.getApplicationChildrenControlEnabled(s);
        flag = flag1;
_L2:
        return flag;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean getApplicationMaskNotificationEnabledAsUser(String s, int i)
    {
        boolean flag;
        try
        {
            flag = mService.getApplicationMaskNotificationEnabledAsUser(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("getApplicationMaskNotificationEnabledAsUser exception", s);
        }
        return flag;
    }

    public Intent getCheckAccessControlIntent(Context context, String s, Intent intent, int i)
    {
        boolean flag;
        if(context != null)
            flag = context instanceof Activity;
        else
            flag = false;
        return getCheckAccessControlIntent(context, s, intent, flag, -1, i);
    }

    public Intent getCheckAccessControlIntent(Context context, String s, Intent intent, boolean flag, int i, int j)
    {
        if(context == null || isAccessControlActived(context, j) ^ true)
            return null;
        context = null;
        ApplicationInfo applicationinfo = AppGlobals.getPackageManager().getApplicationInfo(s, 0, j);
        context = applicationinfo;
_L2:
        if(context == null)
            return null;
        while(intent != null && context != null && ((ApplicationInfo) (context)).uid == Binder.getCallingUid() || checkAccessControlPassAsUser(s, intent, j) || intent != null && (intent.getFlags() & 0x100000) != 0) 
            return null;
        return getCheckAccessIntent(true, s, intent, i, flag, j);
        Exception exception;
        exception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public Intent getCheckGameBoosterAntimsgIntent(Context context, String s, String s1, Intent intent, boolean flag, int i, int j)
    {
        if(context == null || "com.miui.securitycenter".equals(s) || isGameBoosterActived(j) ^ true)
            return null;
        context = null;
        s = AppGlobals.getPackageManager().getApplicationInfo(s1, 0, j);
        context = s;
_L2:
        if(context == null)
            return null;
        if(checkGameBoosterAntimsgPassAsUser(s1, intent, j))
            return null;
        else
            return getCheckAccessIntent(false, s1, intent, i, flag, j);
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public Intent getCheckIntent(Context context, ApplicationInfo applicationinfo, String s, String s1, Intent intent, boolean flag, int i, 
            boolean flag1, int j, int k)
    {
        Intent intent1 = AppRunningControlManager.getBlockActivityIntent(context, s1, intent, flag, i);
        if(intent1 != null)
            return intent1;
        applicationinfo = getCheckStartActivityIntent(context, applicationinfo, s, s1, intent, flag, i, flag1, j, k);
        if(applicationinfo != null)
            return applicationinfo;
        applicationinfo = getCheckGameBoosterAntimsgIntent(context, s, s1, intent, flag, i, j);
        if(applicationinfo != null)
            return applicationinfo;
        else
            return getCheckAccessControlIntent(context, s1, intent, flag, i, j);
    }

    public Intent getCheckStartActivityIntent(Context context, ApplicationInfo applicationinfo, String s, String s1, Intent intent, boolean flag, int i, 
            boolean flag1, int j, int k)
    {
        long l;
        l = SystemClock.elapsedRealtime();
        if(intent == null || flag1 || AppOpsUtils.isXOptMode() || Build.IS_INTERNATIONAL_BUILD)
            return null;
        context = applicationinfo;
        if(applicationinfo != null) goto _L2; else goto _L1
_L1:
        if(TextUtils.isEmpty(s))
            return null;
        k = UserHandle.getUserId(k);
        context = AppGlobals.getPackageManager().getApplicationInfo(s, 0, k);
        applicationinfo = context;
_L3:
        context = applicationinfo;
        if(applicationinfo == null)
            return null;
        break; /* Loop/switch isn't completed */
        context;
        Log.e("SecurityManager", "getCheckStartActivityIntent", context);
        if(true) goto _L3; else goto _L2
_L2:
        if((((ApplicationInfo) (context)).flags & 0x81) != 0 || ((ApplicationInfo) (context)).uid < 10000)
            return null;
        applicationinfo = null;
        s = AppGlobals.getPackageManager().getApplicationInfo(s1, 0, j);
        applicationinfo = s;
_L5:
        if(applicationinfo == null)
            return null;
        break; /* Loop/switch isn't completed */
        s;
        Log.e("SecurityManager", "getCheckStartActivityIntent", s);
        if(true) goto _L5; else goto _L4
_L4:
        if((applicationinfo.flags & 0x81) != 0 || applicationinfo.uid < 10000)
            return null;
        if(TextUtils.equals(((ApplicationInfo) (context)).packageName, applicationinfo.packageName) || checkAllowStartActivity(((ApplicationInfo) (context)).packageName, s1, intent, j))
            return null;
        if(FeatureParser.getBoolean("is_pad", false))
            return null;
        s = new Intent("android.app.action.CHECK_ALLOW_START_ACTIVITY");
        s.putExtra("CallerPkgName", ((ApplicationInfo) (context)).packageName);
        s.putExtra("CalleePkgName", applicationinfo.packageName);
        s.putExtra("UserId", j);
        s.addFlags(0x800000);
        s.setPackage("com.miui.securitycenter");
        if(intent != null)
        {
            if((intent.getFlags() & 0x2000000) != 0)
                s.addFlags(0x2000000);
            intent.addFlags(0x1000000);
            if(flag)
            {
                if(i >= 0)
                    intent.addFlags(0x2000000);
            } else
            {
                intent.addFlags(0x10000000);
            }
            s.putExtra("android.intent.extra.INTENT", intent);
        }
        checkTime(l, "getCheckStartActivityIntent");
        return s;
    }

    public List getIncompatibleAppList()
    {
        List list;
        try
        {
            list = mService.getIncompatibleAppList();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("security manager has died", remoteexception);
        }
        return list;
    }

    public String getPackageNameByPid(int i)
    {
        String s;
        try
        {
            s = mService.getPackageNameByPid(i);
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
            return null;
        }
        return s;
    }

    public int getSysAppCracked()
    {
        int i;
        try
        {
            i = mService.getSysAppCracked();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("security manager has died", remoteexception);
        }
        return i;
    }

    public IBinder getTopActivity()
    {
        IBinder ibinder;
        try
        {
            ibinder = mService.getTopActivity();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("security manager has died", remoteexception);
        }
        return ibinder;
    }

    public ParceledListSlice getWakePathCallListLog()
    {
        ParceledListSlice parceledlistslice;
        try
        {
            parceledlistslice = mService.getWakePathCallListLog();
        }
        catch(Exception exception)
        {
            return null;
        }
        return parceledlistslice;
    }

    public List getWakePathComponents(String s)
    {
        try
        {
            s = mService.getWakePathComponents(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("security manager has died.", s);
        }
        if(s == null)
            return null;
        s = s.getList();
        return s;
    }

    public long getWakeUpTime(String s)
    {
        long l;
        try
        {
            l = mService.getWakeUpTime(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return 0L;
        }
        return l;
    }

    public void grantInstallPermission(String s, String s1)
    {
        try
        {
            mService.grantInstallPermission(s, s1);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("security manager has died", s);
        }
    }

    public void grantRuntimePermission(String s)
    {
        try
        {
            mService.grantRuntimePermission(s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("security manager has died", s);
        }
    }

    public boolean haveAccessControlPassword()
    {
        boolean flag;
        try
        {
            flag = mService.haveAccessControlPassword(UserHandle.myUserId());
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("security manager has died", remoteexception);
        }
        return flag;
    }

    public boolean isAccessControlActived(Context context)
    {
        return isAccessControlActived(context, UserHandle.getCallingUserId());
    }

    public boolean isAccessControlActived(Context context, int i)
    {
        boolean flag = true;
        i = getUserHandle(i);
        if(1 != android.provider.Settings.Secure.getIntForUser(context.getContentResolver(), "access_control_lock_enabled", 0, i))
            flag = false;
        return flag;
    }

    public boolean isAllowStartService(Intent intent, int i)
    {
        boolean flag;
        try
        {
            flag = mService.isAllowStartService(intent, i);
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw new RuntimeException("security manager has died", intent);
        }
        return flag;
    }

    public boolean isAppHide()
    {
        boolean flag;
        try
        {
            flag = mService.isAppHide();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("security manager has died", remoteexception);
        }
        return flag;
    }

    public boolean isAppPrivacyEnabled(String s)
    {
        boolean flag;
        try
        {
            flag = mService.isAppPrivacyEnabled(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("security manager has died", s);
        }
        return flag;
    }

    public boolean isFunctionOpen()
    {
        boolean flag;
        try
        {
            flag = mService.isFunctionOpen();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("security manager has died", remoteexception);
        }
        return flag;
    }

    public boolean isGameBoosterActived(int i)
    {
        boolean flag;
        try
        {
            flag = mService.getGameMode(i);
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("isGameBoosterActived exception", remoteexception);
        }
        return flag;
    }

    public boolean isRestrictedAppNet(Context context)
    {
        boolean flag;
        try
        {
            context = context.getPackageName();
            flag = mService.isRestrictedAppNet(context);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new RuntimeException("security manager has died", context);
        }
        return flag;
    }

    public boolean isValidDevice()
    {
        boolean flag;
        try
        {
            flag = mService.isValidDevice();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("security manager has died", remoteexception);
        }
        return flag;
    }

    public void killNativePackageProcesses(int i, String s)
    {
        mService.killNativePackageProcesses(i, s);
_L1:
        return;
        s;
        s.printStackTrace();
          goto _L1
    }

    public boolean needFinishAccessControl(IBinder ibinder)
    {
        boolean flag;
        try
        {
            flag = mService.needFinishAccessControl(ibinder);
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            ibinder.printStackTrace();
            return false;
        }
        return flag;
    }

    public void pushWakePathConfirmDialogWhiteList(int i, List list)
    {
        mService.pushWakePathConfirmDialogWhiteList(i, list);
_L1:
        return;
        list;
        Log.e("SecurityManager", "pushWakePathConfirmDialogWhiteList", list);
          goto _L1
    }

    public void pushWakePathData(int i, ParceledListSlice parceledlistslice, int j)
    {
        mService.pushWakePathData(i, parceledlistslice, j);
_L2:
        return;
        parceledlistslice;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void pushWakePathWhiteList(List list, int i)
    {
        mService.pushWakePathWhiteList(list, i);
_L2:
        return;
        list;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean putSystemDataStringFile(String s, String s1)
    {
        boolean flag;
        try
        {
            flag = mService.putSystemDataStringFile(s, s1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return false;
        }
        return flag;
    }

    public String readSystemDataStringFile(String s)
    {
        try
        {
            s = mService.readSystemDataStringFile(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        return s;
    }

    public void registerWakePathCallback(IWakePathCallback iwakepathcallback)
    {
        mService.registerWakePathCallback(iwakepathcallback);
_L2:
        return;
        iwakepathcallback;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void removeAccessControlPass(String s)
    {
        mService.removeAccessControlPass(s);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void removeAccessControlPassAsUser(String s, int i)
    {
        try
        {
            mService.removeAccessControlPassAsUser(s, i);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("security manager has died", s);
        }
    }

    public void removeWakePathData(int i)
    {
        mService.removeWakePathData(i);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void saveIcon(String s, Bitmap bitmap)
    {
        try
        {
            mService.saveIcon(s, bitmap);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("saveIcon exception", s);
        }
    }

    public void setAccessControlPassword(String s, String s1)
    {
        try
        {
            mService.setAccessControlPassword(s, s1, UserHandle.myUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("security manager has died", s);
        }
    }

    public boolean setAppHide(boolean flag)
    {
        try
        {
            flag = mService.setAppHide(flag);
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("security manager has died", remoteexception);
        }
        return flag;
    }

    public void setAppPermissionControlOpen(int i)
    {
        mService.setAppPermissionControlOpen(i);
_L2:
        return;
        Exception exception;
        exception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setAppPrivacyStatus(String s, boolean flag)
    {
        try
        {
            mService.setAppPrivacyStatus(s, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("security manager has died", s);
        }
    }

    public void setApplicationAccessControlEnabled(String s, boolean flag)
    {
        mService.setApplicationAccessControlEnabled(s, flag);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setApplicationAccessControlEnabledForUser(String s, boolean flag, int i)
    {
        try
        {
            mService.setApplicationAccessControlEnabledForUser(s, flag, i);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("setApplicationAccessControlEnabledForUser exception", s);
        }
    }

    public void setApplicationChildrenControlEnabled(String s, boolean flag)
    {
        mService.setApplicationChildrenControlEnabled(s, flag);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setApplicationMaskNotificationEnabledForUser(String s, boolean flag, int i)
    {
        try
        {
            mService.setApplicationMaskNotificationEnabledForUser(s, flag, i);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("setApplicationMaskNotificationEnabledForUser exception", s);
        }
    }

    public void setCoreRuntimePermissionEnabled(boolean flag, int i)
    {
        try
        {
            mService.setCoreRuntimePermissionEnabled(flag, i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("security manager has died", remoteexception);
        }
    }

    public boolean setCurrentNetworkState(int i)
    {
        boolean flag;
        try
        {
            flag = mService.setCurrentNetworkState(i);
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("security manager has died", remoteexception);
        }
        return flag;
    }

    public void setGameBoosterIBinder(IBinder ibinder, int i, boolean flag)
    {
        try
        {
            mService.setGameBoosterIBinder(ibinder, i, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            throw new RuntimeException("setGameBoosterIBinder exception", ibinder);
        }
    }

    public void setIncompatibleAppList(List list)
    {
        try
        {
            mService.setIncompatibleAppList(list);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            throw new RuntimeException("security manager has died", list);
        }
    }

    public boolean setMiuiFirewallRule(String s, int i, int j)
    {
        boolean flag;
        try
        {
            flag = mService.setMiuiFirewallRule(s, i, j);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("security manager has died", s);
        }
        return flag;
    }

    public void setNotificationsEnabledForPackage(String s, int i, boolean flag)
    {
        try
        {
            mService.setNotificationsEnabledForPackage(s, i, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("security manager has died", s);
        }
    }

    public void setTrackWakePathCallListLogEnabled(boolean flag)
    {
        mService.setTrackWakePathCallListLogEnabled(flag);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setWakeUpTime(String s, long l)
    {
        mService.setWakeUpTime(s, l);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean startInterceptSmsBySender(Context context, String s, int i)
    {
        boolean flag;
        try
        {
            context = context.getPackageName();
            flag = mService.startInterceptSmsBySender(context, s, i);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            context.printStackTrace();
            return false;
        }
        return flag;
    }

    public boolean stopInterceptSmsBySender()
    {
        boolean flag;
        try
        {
            flag = mService.stopInterceptSmsBySender();
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
            return false;
        }
        return flag;
    }

    public void watchGreenGuardProcess()
    {
        try
        {
            mService.watchGreenGuardProcess();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("security manager has died", remoteexception);
        }
    }

    public boolean writeAppHideConfig(boolean flag)
    {
        try
        {
            flag = mService.writeAppHideConfig(flag);
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("security manager has died", remoteexception);
        }
        return flag;
    }

    static final boolean _2D_assertionsDisabled = miui/security/SecurityManager.desiredAssertionStatus() ^ true;
    public static final int FLAG_AC_ENABLED = 1;
    public static final int FLAG_AC_PACKAGE_CANCELED = 8;
    public static final int FLAG_AC_PACKAGE_ENABLED = 2;
    public static final int FLAG_AC_PACKAGE_PASSED = 4;
    public static final int MODE_EACH = 0;
    public static final int MODE_LOCK_SCREEN = 1;
    public static final int MODE_TIME_OUT = 2;
    private static final String PACKAGE_SECURITYCENTER = "com.miui.securitycenter";
    public static final String SKIP_INTERCEPT = "skip_interception";
    public static final String SKIP_INTERCEPT_ACTIVITY = "com.miui.gallery.activity.ExternalPhotoPageActivity";
    public static final String SKIP_INTERCEPT_PACKAGE = "com.miui.gallery";
    private static final String START_ACTIVITY_CALLEE_PKGNAME = "CalleePkgName";
    private static final String START_ACTIVITY_CALLER_PKGNAME = "CallerPkgName";
    private static final String START_ACTIVITY_USERID = "UserId";
    private static final String TAG = "SecurityManager";
    private static Method sActivityFinishMethod;
    private final ISecurityManager mService;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT <= 19) goto _L2; else goto _L1
_L1:
        if(android.os.Build.VERSION.SDK_INT < 24) goto _L4; else goto _L3
_L3:
        sActivityFinishMethod = android/app/Activity.getDeclaredMethod("finish", new Class[] {
            Integer.TYPE
        });
_L5:
        sActivityFinishMethod.setAccessible(true);
_L2:
        if(android.os.Build.VERSION.SDK_INT == 19)
            System.loadLibrary("sechook");
        return;
_L4:
        sActivityFinishMethod = android/app/Activity.getDeclaredMethod("finish", new Class[] {
            Boolean.TYPE
        });
          goto _L5
        Exception exception;
        exception;
        Log.e("SecurityManager", " SecurityManager static init error", exception);
          goto _L2
    }
}

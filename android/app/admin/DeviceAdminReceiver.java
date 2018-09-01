// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.admin;

import android.content.*;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;

// Referenced classes of package android.app.admin:
//            DevicePolicyManager

public class DeviceAdminReceiver extends BroadcastReceiver
{

    public DeviceAdminReceiver()
    {
    }

    public DevicePolicyManager getManager(Context context)
    {
        if(mManager != null)
        {
            return mManager;
        } else
        {
            mManager = (DevicePolicyManager)context.getSystemService("device_policy");
            return mManager;
        }
    }

    public ComponentName getWho(Context context)
    {
        if(mWho != null)
        {
            return mWho;
        } else
        {
            mWho = new ComponentName(context, getClass());
            return mWho;
        }
    }

    public void onBugreportFailed(Context context, Intent intent, int i)
    {
    }

    public void onBugreportShared(Context context, Intent intent, String s)
    {
    }

    public void onBugreportSharingDeclined(Context context, Intent intent)
    {
    }

    public String onChoosePrivateKeyAlias(Context context, Intent intent, int i, Uri uri, String s)
    {
        return null;
    }

    public CharSequence onDisableRequested(Context context, Intent intent)
    {
        return null;
    }

    public void onDisabled(Context context, Intent intent)
    {
    }

    public void onEnabled(Context context, Intent intent)
    {
    }

    public void onLockTaskModeEntering(Context context, Intent intent, String s)
    {
    }

    public void onLockTaskModeExiting(Context context, Intent intent)
    {
    }

    public void onNetworkLogsAvailable(Context context, Intent intent, long l, int i)
    {
    }

    public void onPasswordChanged(Context context, Intent intent)
    {
    }

    public void onPasswordChanged(Context context, Intent intent, UserHandle userhandle)
    {
        onPasswordChanged(context, intent);
    }

    public void onPasswordExpiring(Context context, Intent intent)
    {
    }

    public void onPasswordExpiring(Context context, Intent intent, UserHandle userhandle)
    {
        onPasswordExpiring(context, intent);
    }

    public void onPasswordFailed(Context context, Intent intent)
    {
    }

    public void onPasswordFailed(Context context, Intent intent, UserHandle userhandle)
    {
        onPasswordFailed(context, intent);
    }

    public void onPasswordSucceeded(Context context, Intent intent)
    {
    }

    public void onPasswordSucceeded(Context context, Intent intent, UserHandle userhandle)
    {
        onPasswordSucceeded(context, intent);
    }

    public void onProfileProvisioningComplete(Context context, Intent intent)
    {
    }

    public void onReadyForUserInitialization(Context context, Intent intent)
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        String s = intent.getAction();
        if(!"android.app.action.ACTION_PASSWORD_CHANGED".equals(s)) goto _L2; else goto _L1
_L1:
        onPasswordChanged(context, intent, (UserHandle)intent.getParcelableExtra("android.intent.extra.USER"));
_L4:
        return;
_L2:
        if("android.app.action.ACTION_PASSWORD_FAILED".equals(s))
            onPasswordFailed(context, intent, (UserHandle)intent.getParcelableExtra("android.intent.extra.USER"));
        else
        if("android.app.action.ACTION_PASSWORD_SUCCEEDED".equals(s))
            onPasswordSucceeded(context, intent, (UserHandle)intent.getParcelableExtra("android.intent.extra.USER"));
        else
        if("android.app.action.DEVICE_ADMIN_ENABLED".equals(s))
            onEnabled(context, intent);
        else
        if("android.app.action.DEVICE_ADMIN_DISABLE_REQUESTED".equals(s))
        {
            context = onDisableRequested(context, intent);
            if(context != null)
                getResultExtras(true).putCharSequence("android.app.extra.DISABLE_WARNING", context);
        } else
        if("android.app.action.DEVICE_ADMIN_DISABLED".equals(s))
            onDisabled(context, intent);
        else
        if("android.app.action.ACTION_PASSWORD_EXPIRING".equals(s))
            onPasswordExpiring(context, intent, (UserHandle)intent.getParcelableExtra("android.intent.extra.USER"));
        else
        if("android.app.action.PROFILE_PROVISIONING_COMPLETE".equals(s))
            onProfileProvisioningComplete(context, intent);
        else
        if("android.app.action.CHOOSE_PRIVATE_KEY_ALIAS".equals(s))
            setResultData(onChoosePrivateKeyAlias(context, intent, intent.getIntExtra("android.app.extra.CHOOSE_PRIVATE_KEY_SENDER_UID", -1), (Uri)intent.getParcelableExtra("android.app.extra.CHOOSE_PRIVATE_KEY_URI"), intent.getStringExtra("android.app.extra.CHOOSE_PRIVATE_KEY_ALIAS")));
        else
        if("android.app.action.LOCK_TASK_ENTERING".equals(s))
            onLockTaskModeEntering(context, intent, intent.getStringExtra("android.app.extra.LOCK_TASK_PACKAGE"));
        else
        if("android.app.action.LOCK_TASK_EXITING".equals(s))
            onLockTaskModeExiting(context, intent);
        else
        if("android.app.action.NOTIFY_PENDING_SYSTEM_UPDATE".equals(s))
            onSystemUpdatePending(context, intent, intent.getLongExtra("android.app.extra.SYSTEM_UPDATE_RECEIVED_TIME", -1L));
        else
        if("android.app.action.BUGREPORT_SHARING_DECLINED".equals(s))
            onBugreportSharingDeclined(context, intent);
        else
        if("android.app.action.BUGREPORT_SHARE".equals(s))
            onBugreportShared(context, intent, intent.getStringExtra("android.app.extra.BUGREPORT_HASH"));
        else
        if("android.app.action.BUGREPORT_FAILED".equals(s))
            onBugreportFailed(context, intent, intent.getIntExtra("android.app.extra.BUGREPORT_FAILURE_REASON", 0));
        else
        if("android.app.action.SECURITY_LOGS_AVAILABLE".equals(s))
            onSecurityLogsAvailable(context, intent);
        else
        if("android.app.action.NETWORK_LOGS_AVAILABLE".equals(s))
            onNetworkLogsAvailable(context, intent, intent.getLongExtra("android.app.extra.EXTRA_NETWORK_LOGS_TOKEN", -1L), intent.getIntExtra("android.app.extra.EXTRA_NETWORK_LOGS_COUNT", 0));
        else
        if("android.app.action.USER_ADDED".equals(s))
            onUserAdded(context, intent, (UserHandle)intent.getParcelableExtra("android.intent.extra.USER"));
        else
        if("android.app.action.USER_REMOVED".equals(s))
            onUserRemoved(context, intent, (UserHandle)intent.getParcelableExtra("android.intent.extra.USER"));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onSecurityLogsAvailable(Context context, Intent intent)
    {
    }

    public void onSystemUpdatePending(Context context, Intent intent, long l)
    {
    }

    public void onUserAdded(Context context, Intent intent, UserHandle userhandle)
    {
    }

    public void onUserRemoved(Context context, Intent intent, UserHandle userhandle)
    {
    }

    public static final String ACTION_BUGREPORT_FAILED = "android.app.action.BUGREPORT_FAILED";
    public static final String ACTION_BUGREPORT_SHARE = "android.app.action.BUGREPORT_SHARE";
    public static final String ACTION_BUGREPORT_SHARING_DECLINED = "android.app.action.BUGREPORT_SHARING_DECLINED";
    public static final String ACTION_CHOOSE_PRIVATE_KEY_ALIAS = "android.app.action.CHOOSE_PRIVATE_KEY_ALIAS";
    public static final String ACTION_DEVICE_ADMIN_DISABLED = "android.app.action.DEVICE_ADMIN_DISABLED";
    public static final String ACTION_DEVICE_ADMIN_DISABLE_REQUESTED = "android.app.action.DEVICE_ADMIN_DISABLE_REQUESTED";
    public static final String ACTION_DEVICE_ADMIN_ENABLED = "android.app.action.DEVICE_ADMIN_ENABLED";
    public static final String ACTION_LOCK_TASK_ENTERING = "android.app.action.LOCK_TASK_ENTERING";
    public static final String ACTION_LOCK_TASK_EXITING = "android.app.action.LOCK_TASK_EXITING";
    public static final String ACTION_NETWORK_LOGS_AVAILABLE = "android.app.action.NETWORK_LOGS_AVAILABLE";
    public static final String ACTION_NOTIFY_PENDING_SYSTEM_UPDATE = "android.app.action.NOTIFY_PENDING_SYSTEM_UPDATE";
    public static final String ACTION_PASSWORD_CHANGED = "android.app.action.ACTION_PASSWORD_CHANGED";
    public static final String ACTION_PASSWORD_EXPIRING = "android.app.action.ACTION_PASSWORD_EXPIRING";
    public static final String ACTION_PASSWORD_FAILED = "android.app.action.ACTION_PASSWORD_FAILED";
    public static final String ACTION_PASSWORD_SUCCEEDED = "android.app.action.ACTION_PASSWORD_SUCCEEDED";
    public static final String ACTION_PROFILE_PROVISIONING_COMPLETE = "android.app.action.PROFILE_PROVISIONING_COMPLETE";
    public static final String ACTION_SECURITY_LOGS_AVAILABLE = "android.app.action.SECURITY_LOGS_AVAILABLE";
    public static final String ACTION_USER_ADDED = "android.app.action.USER_ADDED";
    public static final String ACTION_USER_REMOVED = "android.app.action.USER_REMOVED";
    public static final int BUGREPORT_FAILURE_FAILED_COMPLETING = 0;
    public static final int BUGREPORT_FAILURE_FILE_NO_LONGER_AVAILABLE = 1;
    public static final String DEVICE_ADMIN_META_DATA = "android.app.device_admin";
    public static final String EXTRA_BUGREPORT_FAILURE_REASON = "android.app.extra.BUGREPORT_FAILURE_REASON";
    public static final String EXTRA_BUGREPORT_HASH = "android.app.extra.BUGREPORT_HASH";
    public static final String EXTRA_CHOOSE_PRIVATE_KEY_ALIAS = "android.app.extra.CHOOSE_PRIVATE_KEY_ALIAS";
    public static final String EXTRA_CHOOSE_PRIVATE_KEY_RESPONSE = "android.app.extra.CHOOSE_PRIVATE_KEY_RESPONSE";
    public static final String EXTRA_CHOOSE_PRIVATE_KEY_SENDER_UID = "android.app.extra.CHOOSE_PRIVATE_KEY_SENDER_UID";
    public static final String EXTRA_CHOOSE_PRIVATE_KEY_URI = "android.app.extra.CHOOSE_PRIVATE_KEY_URI";
    public static final String EXTRA_DISABLE_WARNING = "android.app.extra.DISABLE_WARNING";
    public static final String EXTRA_LOCK_TASK_PACKAGE = "android.app.extra.LOCK_TASK_PACKAGE";
    public static final String EXTRA_NETWORK_LOGS_COUNT = "android.app.extra.EXTRA_NETWORK_LOGS_COUNT";
    public static final String EXTRA_NETWORK_LOGS_TOKEN = "android.app.extra.EXTRA_NETWORK_LOGS_TOKEN";
    public static final String EXTRA_SYSTEM_UPDATE_RECEIVED_TIME = "android.app.extra.SYSTEM_UPDATE_RECEIVED_TIME";
    private static String TAG = "DevicePolicy";
    private static boolean localLOGV = false;
    private DevicePolicyManager mManager;
    private ComponentName mWho;

}

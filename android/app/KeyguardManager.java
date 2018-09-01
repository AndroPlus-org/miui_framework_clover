// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.app.trust.ITrustManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.*;
import android.os.*;
import android.service.persistentdata.IPersistentDataBlockService;
import android.util.Log;
import android.view.IWindowManager;
import android.view.WindowManagerGlobal;
import com.android.internal.widget.LockPatternUtils;
import java.util.List;

// Referenced classes of package android.app:
//            ActivityManager, Activity, IActivityManager

public class KeyguardManager
{
    public static abstract class KeyguardDismissCallback
    {

        public void onDismissCancelled()
        {
        }

        public void onDismissError()
        {
        }

        public void onDismissSucceeded()
        {
        }

        public KeyguardDismissCallback()
        {
        }
    }

    public class KeyguardLock
    {

        public void disableKeyguard()
        {
            KeyguardManager._2D_get0(KeyguardManager.this).disableKeyguard(mToken, mTag);
_L2:
            return;
            RemoteException remoteexception;
            remoteexception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public void reenableKeyguard()
        {
            KeyguardManager._2D_get0(KeyguardManager.this).reenableKeyguard(mToken);
_L2:
            return;
            RemoteException remoteexception;
            remoteexception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        private final String mTag;
        private final IBinder mToken = new Binder();
        final KeyguardManager this$0;

        KeyguardLock(String s)
        {
            this$0 = KeyguardManager.this;
            super();
            mTag = s;
        }
    }

    public static interface OnKeyguardExitResult
    {

        public abstract void onKeyguardExitResult(boolean flag);
    }


    static IWindowManager _2D_get0(KeyguardManager keyguardmanager)
    {
        return keyguardmanager.mWM;
    }

    KeyguardManager(Context context)
        throws android.os.ServiceManager.ServiceNotFoundException
    {
        mContext = context;
    }

    private String getSettingsPackageForIntent(Intent intent)
    {
        intent = mContext.getPackageManager().queryIntentActivities(intent, 0x100000);
        if(intent.size() > 0)
            return ((ResolveInfo)intent.get(0)).activityInfo.packageName;
        else
            return "com.android.settings";
    }

    public Intent createConfirmDeviceCredentialIntent(CharSequence charsequence, CharSequence charsequence1)
    {
        if(!isDeviceSecure())
        {
            return null;
        } else
        {
            Intent intent = new Intent("android.app.action.CONFIRM_DEVICE_CREDENTIAL");
            intent.putExtra("android.app.extra.TITLE", charsequence);
            intent.putExtra("android.app.extra.DESCRIPTION", charsequence1);
            intent.setPackage(getSettingsPackageForIntent(intent));
            return intent;
        }
    }

    public Intent createConfirmDeviceCredentialIntent(CharSequence charsequence, CharSequence charsequence1, int i)
    {
        if(!isDeviceSecure(i))
        {
            return null;
        } else
        {
            Intent intent = new Intent("android.app.action.CONFIRM_DEVICE_CREDENTIAL_WITH_USER");
            intent.putExtra("android.app.extra.TITLE", charsequence);
            intent.putExtra("android.app.extra.DESCRIPTION", charsequence1);
            intent.putExtra("android.intent.extra.USER_ID", i);
            intent.setPackage(getSettingsPackageForIntent(intent));
            return intent;
        }
    }

    public Intent createConfirmFactoryResetCredentialIntent(CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2)
    {
        if(!LockPatternUtils.frpCredentialEnabled(mContext))
        {
            Log.w("KeyguardManager", "Factory reset credentials not supported.");
            return null;
        }
        if(android.provider.Settings.Global.getInt(mContext.getContentResolver(), "device_provisioned", 0) != 0)
        {
            Log.e("KeyguardManager", "Factory reset credential cannot be verified after provisioning.");
            return null;
        }
        IPersistentDataBlockService ipersistentdatablockservice;
        try
        {
            ipersistentdatablockservice = android.service.persistentdata.IPersistentDataBlockService.Stub.asInterface(ServiceManager.getService("persistent_data_block"));
        }
        // Misplaced declaration of an exception variable
        catch(CharSequence charsequence)
        {
            throw charsequence.rethrowFromSystemServer();
        }
        if(ipersistentdatablockservice != null)
            break MISSING_BLOCK_LABEL_71;
        Log.e("KeyguardManager", "No persistent data block service");
        return null;
        if(ipersistentdatablockservice.hasFrpCredentialHandle())
            break MISSING_BLOCK_LABEL_97;
        Log.i("KeyguardManager", "The persistent data block does not have a factory reset credential.");
        return null;
        Intent intent = new Intent("android.app.action.CONFIRM_FRP_CREDENTIAL");
        intent.putExtra("android.app.extra.TITLE", charsequence);
        intent.putExtra("android.app.extra.DESCRIPTION", charsequence1);
        intent.putExtra("android.app.extra.ALTERNATE_BUTTON_LABEL", charsequence2);
        intent.setPackage(getSettingsPackageForIntent(intent));
        return intent;
    }

    public void dismissKeyguard(Activity activity, KeyguardDismissCallback keyguarddismisscallback, Handler handler)
    {
        requestDismissKeyguard(activity, keyguarddismisscallback);
    }

    public void exitKeyguardSecurely(OnKeyguardExitResult onkeyguardexitresult)
    {
        IWindowManager iwindowmanager = mWM;
        android.view.IOnKeyguardExitResult.Stub stub = JVM INSTR new #8   <Class KeyguardManager$2>;
        stub.this. _cls2();
        iwindowmanager.exitKeyguardSecurely(stub);
_L2:
        return;
        onkeyguardexitresult;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean inKeyguardRestrictedInputMode()
    {
        boolean flag;
        try
        {
            flag = mWM.inKeyguardRestrictedInputMode();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public boolean isDeviceLocked()
    {
        return isDeviceLocked(UserHandle.myUserId());
    }

    public boolean isDeviceLocked(int i)
    {
        boolean flag;
        try
        {
            flag = mTrustManager.isDeviceLocked(i);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public boolean isDeviceSecure()
    {
        return isDeviceSecure(UserHandle.myUserId());
    }

    public boolean isDeviceSecure(int i)
    {
        boolean flag;
        try
        {
            flag = mTrustManager.isDeviceSecure(i);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public boolean isKeyguardLocked()
    {
        boolean flag;
        try
        {
            flag = mWM.isKeyguardLocked();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public boolean isKeyguardSecure()
    {
        boolean flag;
        try
        {
            flag = mWM.isKeyguardSecure();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public KeyguardLock newKeyguardLock(String s)
    {
        return new KeyguardLock(s);
    }

    public void requestDismissKeyguard(Activity activity, KeyguardDismissCallback keyguarddismisscallback)
    {
        IActivityManager iactivitymanager = mAm;
        IBinder ibinder = activity.getActivityToken();
        com.android.internal.policy.IKeyguardDismissCallback.Stub stub = JVM INSTR new #6   <Class KeyguardManager$1>;
        stub.this. _cls1();
        iactivitymanager.dismissKeyguard(ibinder, stub);
_L1:
        return;
        activity;
        Log.i("KeyguardManager", (new StringBuilder()).append("Failed to dismiss keyguard: ").append(activity).toString());
          goto _L1
    }

    public static final String ACTION_CONFIRM_DEVICE_CREDENTIAL = "android.app.action.CONFIRM_DEVICE_CREDENTIAL";
    public static final String ACTION_CONFIRM_DEVICE_CREDENTIAL_WITH_USER = "android.app.action.CONFIRM_DEVICE_CREDENTIAL_WITH_USER";
    public static final String ACTION_CONFIRM_FRP_CREDENTIAL = "android.app.action.CONFIRM_FRP_CREDENTIAL";
    public static final String EXTRA_ALTERNATE_BUTTON_LABEL = "android.app.extra.ALTERNATE_BUTTON_LABEL";
    public static final String EXTRA_DESCRIPTION = "android.app.extra.DESCRIPTION";
    public static final String EXTRA_TITLE = "android.app.extra.TITLE";
    public static final int RESULT_ALTERNATE = 1;
    private static final String TAG = "KeyguardManager";
    private final IActivityManager mAm = ActivityManager.getService();
    private final Context mContext;
    private final ITrustManager mTrustManager = android.app.trust.ITrustManager.Stub.asInterface(ServiceManager.getServiceOrThrow("trust"));
    private final IWindowManager mWM = WindowManagerGlobal.getWindowManagerService();

    // Unreferenced inner class android/app/KeyguardManager$1

/* anonymous class */
    class _cls1 extends com.android.internal.policy.IKeyguardDismissCallback.Stub
    {

        static void _2D_android_app_KeyguardManager$1_2D_mthref_2D_0(KeyguardDismissCallback keyguarddismisscallback)
        {
            keyguarddismisscallback.onDismissError();
        }

        static void _2D_android_app_KeyguardManager$1_2D_mthref_2D_1(KeyguardDismissCallback keyguarddismisscallback)
        {
            keyguarddismisscallback.onDismissSucceeded();
        }

        static void _2D_android_app_KeyguardManager$1_2D_mthref_2D_2(KeyguardDismissCallback keyguarddismisscallback)
        {
            keyguarddismisscallback.onDismissCancelled();
        }

        public void onDismissCancelled()
            throws RemoteException
        {
            if(callback != null && activity.isDestroyed() ^ true)
            {
                Handler handler = activity.mHandler;
                KeyguardDismissCallback keyguarddismisscallback = callback;
                keyguarddismisscallback.getClass();
                handler.post(new _.Lambda.aS31cHIhRx41653CMnd4gZqshIQ((byte)4, keyguarddismisscallback));
            }
        }

        public void onDismissError()
            throws RemoteException
        {
            if(callback != null && activity.isDestroyed() ^ true)
            {
                Handler handler = activity.mHandler;
                KeyguardDismissCallback keyguarddismisscallback = callback;
                keyguarddismisscallback.getClass();
                handler.post(new _.Lambda.aS31cHIhRx41653CMnd4gZqshIQ((byte)5, keyguarddismisscallback));
            }
        }

        public void onDismissSucceeded()
            throws RemoteException
        {
            if(callback != null && activity.isDestroyed() ^ true)
            {
                Handler handler = activity.mHandler;
                KeyguardDismissCallback keyguarddismisscallback = callback;
                keyguarddismisscallback.getClass();
                handler.post(new _.Lambda.aS31cHIhRx41653CMnd4gZqshIQ((byte)6, keyguarddismisscallback));
            }
        }

        final KeyguardManager this$0;
        final Activity val$activity;
        final KeyguardDismissCallback val$callback;

            
            {
                this$0 = KeyguardManager.this;
                callback = keyguarddismisscallback;
                activity = activity1;
                super();
            }
    }


    // Unreferenced inner class android/app/KeyguardManager$2

/* anonymous class */
    class _cls2 extends android.view.IOnKeyguardExitResult.Stub
    {

        public void onKeyguardExitResult(boolean flag)
            throws RemoteException
        {
            if(callback != null)
                callback.onKeyguardExitResult(flag);
        }

        final KeyguardManager this$0;
        final OnKeyguardExitResult val$callback;

            
            {
                this$0 = KeyguardManager.this;
                callback = onkeyguardexitresult;
                super();
            }
    }

}

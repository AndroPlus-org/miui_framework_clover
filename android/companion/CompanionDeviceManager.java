// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.companion;

import android.app.*;
import android.content.*;
import android.os.*;
import com.android.internal.util.Preconditions;
import java.util.Collections;
import java.util.List;

// Referenced classes of package android.companion:
//            ICompanionDeviceManager, AssociationRequest

public final class CompanionDeviceManager
{
    public static abstract class Callback
    {

        public abstract void onDeviceFound(IntentSender intentsender);

        public abstract void onFailure(CharSequence charsequence);

        public Callback()
        {
        }
    }

    private class CallbackProxy extends IFindDeviceCallback.Stub
        implements android.app.Application.ActivityLifecycleCallbacks
    {

        void lambda$_2D_android_companion_CompanionDeviceManager$CallbackProxy_10810(PendingIntent pendingintent)
        {
            Callback callback = mCallback;
            if(callback == null)
            {
                return;
            } else
            {
                callback.onDeviceFound(pendingintent.getIntentSender());
                return;
            }
        }

        void lambda$_2D_android_companion_CompanionDeviceManager$CallbackProxy_11183(CharSequence charsequence)
        {
            Callback callback = mCallback;
            if(callback == null)
            {
                return;
            } else
            {
                callback.onFailure(charsequence);
                return;
            }
        }

        public void onActivityCreated(Activity activity, Bundle bundle)
        {
        }

        public void onActivityDestroyed(Activity activity)
        {
            if(activity != CompanionDeviceManager._2D_wrap0(CompanionDeviceManager.this))
                return;
            try
            {
                CompanionDeviceManager._2D_get0(CompanionDeviceManager.this).stopScan(mRequest, this, CompanionDeviceManager._2D_wrap1(CompanionDeviceManager.this));
            }
            // Misplaced declaration of an exception variable
            catch(Activity activity)
            {
                activity.rethrowFromSystemServer();
            }
            CompanionDeviceManager._2D_wrap0(CompanionDeviceManager.this).getApplication().unregisterActivityLifecycleCallbacks(this);
            mCallback = null;
            mHandler = null;
            mRequest = null;
        }

        public void onActivityPaused(Activity activity)
        {
        }

        public void onActivityResumed(Activity activity)
        {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle)
        {
        }

        public void onActivityStarted(Activity activity)
        {
        }

        public void onActivityStopped(Activity activity)
        {
        }

        public void onFailure(CharSequence charsequence)
        {
            Handler handler = mHandler;
            if(handler == null)
            {
                return;
            } else
            {
                handler.post(new _.Lambda._cls5JzRO2PPKyhIE1CwHHamoNS_two((byte)0, this, charsequence));
                return;
            }
        }

        public void onSuccess(PendingIntent pendingintent)
        {
            Handler handler = mHandler;
            if(handler == null)
            {
                return;
            } else
            {
                handler.post(new _.Lambda._cls5JzRO2PPKyhIE1CwHHamoNS_two((byte)1, this, pendingintent));
                return;
            }
        }

        private Callback mCallback;
        private Handler mHandler;
        private AssociationRequest mRequest;
        final CompanionDeviceManager this$0;

        private CallbackProxy(AssociationRequest associationrequest, Callback callback, Handler handler)
        {
            this$0 = CompanionDeviceManager.this;
            super();
            mCallback = callback;
            mHandler = handler;
            mRequest = associationrequest;
            CompanionDeviceManager._2D_wrap0(CompanionDeviceManager.this).getApplication().registerActivityLifecycleCallbacks(this);
        }

        CallbackProxy(AssociationRequest associationrequest, Callback callback, Handler handler, CallbackProxy callbackproxy)
        {
            this(associationrequest, callback, handler);
        }
    }


    static ICompanionDeviceManager _2D_get0(CompanionDeviceManager companiondevicemanager)
    {
        return companiondevicemanager.mService;
    }

    static Activity _2D_wrap0(CompanionDeviceManager companiondevicemanager)
    {
        return companiondevicemanager.getActivity();
    }

    static String _2D_wrap1(CompanionDeviceManager companiondevicemanager)
    {
        return companiondevicemanager.getCallingPackage();
    }

    public CompanionDeviceManager(ICompanionDeviceManager icompaniondevicemanager, Context context)
    {
        mService = icompaniondevicemanager;
        mContext = context;
    }

    private boolean checkFeaturePresent()
    {
        boolean flag;
        if(mService != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private Activity getActivity()
    {
        return (Activity)mContext;
    }

    private String getCallingPackage()
    {
        return mContext.getPackageName();
    }

    public void associate(AssociationRequest associationrequest, Callback callback, Handler handler)
    {
        if(!checkFeaturePresent())
            return;
        Preconditions.checkNotNull(associationrequest, "Request cannot be null");
        Preconditions.checkNotNull(callback, "Callback cannot be null");
        try
        {
            ICompanionDeviceManager icompaniondevicemanager = mService;
            CallbackProxy callbackproxy = JVM INSTR new #9   <Class CompanionDeviceManager$CallbackProxy>;
            callbackproxy.this. CallbackProxy(associationrequest, callback, Handler.mainIfNull(handler), null);
            icompaniondevicemanager.associate(associationrequest, callbackproxy, getCallingPackage());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(AssociationRequest associationrequest)
        {
            throw associationrequest.rethrowFromSystemServer();
        }
    }

    public void disassociate(String s)
    {
        if(!checkFeaturePresent())
            return;
        try
        {
            mService.disassociate(s, getCallingPackage());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public List getAssociations()
    {
        if(!checkFeaturePresent())
            return Collections.emptyList();
        List list;
        try
        {
            list = mService.getAssociations(getCallingPackage(), mContext.getUserId());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public boolean hasNotificationAccess(ComponentName componentname)
    {
        if(!checkFeaturePresent())
            return false;
        boolean flag;
        try
        {
            flag = mService.hasNotificationAccess(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return flag;
    }

    public void requestNotificationAccess(ComponentName componentname)
    {
        if(!checkFeaturePresent())
            return;
        try
        {
            componentname = mService.requestNotificationAccess(componentname).getIntentSender();
            mContext.startIntentSender(componentname, null, 0, 0, 0);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw new RuntimeException(componentname);
        }
    }

    public static final String COMPANION_DEVICE_DISCOVERY_PACKAGE_NAME = "com.android.companiondevicemanager";
    private static final boolean DEBUG = false;
    public static final String EXTRA_DEVICE = "android.companion.extra.DEVICE";
    private static final String LOG_TAG = "CompanionDeviceManager";
    private final Context mContext;
    private final ICompanionDeviceManager mService;
}

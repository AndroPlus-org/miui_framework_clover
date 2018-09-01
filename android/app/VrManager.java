// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.ComponentName;
import android.os.Handler;
import android.os.RemoteException;
import android.service.vr.*;
import android.util.ArrayMap;
import java.util.Map;

// Referenced classes of package android.app:
//            VrStateCallback, Vr2dDisplayProperties

public class VrManager
{
    private static class CallbackEntry
    {

        final VrStateCallback mCallback;
        final Handler mHandler;
        final IPersistentVrStateCallbacks mPersistentStateCallback = new _cls2();
        final IVrStateCallbacks mStateCallback = new _cls1();

        CallbackEntry(VrStateCallback vrstatecallback, Handler handler)
        {
            mCallback = vrstatecallback;
            mHandler = handler;
        }
    }


    public VrManager(IVrManager ivrmanager)
    {
        mCallbackMap = new ArrayMap();
        mService = ivrmanager;
    }

    public boolean getPersistentVrModeEnabled()
    {
        boolean flag;
        try
        {
            flag = mService.getPersistentVrModeEnabled();
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.rethrowFromSystemServer();
            return false;
        }
        return flag;
    }

    public boolean getVrModeEnabled()
    {
        boolean flag;
        try
        {
            flag = mService.getVrModeState();
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.rethrowFromSystemServer();
            return false;
        }
        return flag;
    }

    public void registerVrStateCallback(VrStateCallback vrstatecallback, Handler handler)
    {
        if(vrstatecallback == null || mCallbackMap.containsKey(vrstatecallback))
            return;
        handler = new CallbackEntry(vrstatecallback, handler);
        mCallbackMap.put(vrstatecallback, handler);
        mService.registerListener(((CallbackEntry) (handler)).mStateCallback);
        mService.registerPersistentVrStateListener(((CallbackEntry) (handler)).mPersistentStateCallback);
_L1:
        return;
        handler;
        try
        {
            unregisterVrStateCallback(vrstatecallback);
        }
        // Misplaced declaration of an exception variable
        catch(VrStateCallback vrstatecallback)
        {
            handler.rethrowFromSystemServer();
        }
          goto _L1
    }

    public void setAndBindVrCompositor(ComponentName componentname)
    {
        Object obj = null;
        IVrManager ivrmanager = mService;
        if(componentname != null) goto _L2; else goto _L1
_L1:
        componentname = obj;
_L3:
        ivrmanager.setAndBindCompositor(componentname);
_L4:
        return;
_L2:
        componentname = componentname.flattenToString();
          goto _L3
        componentname;
        componentname.rethrowFromSystemServer();
          goto _L4
    }

    public void setPersistentVrModeEnabled(boolean flag)
    {
        mService.setPersistentVrModeEnabled(flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        remoteexception.rethrowFromSystemServer();
          goto _L1
    }

    public void setVr2dDisplayProperties(Vr2dDisplayProperties vr2ddisplayproperties)
    {
        mService.setVr2dDisplayProperties(vr2ddisplayproperties);
_L1:
        return;
        vr2ddisplayproperties;
        vr2ddisplayproperties.rethrowFromSystemServer();
          goto _L1
    }

    public void unregisterVrStateCallback(VrStateCallback vrstatecallback)
    {
        vrstatecallback = (CallbackEntry)mCallbackMap.remove(vrstatecallback);
        if(vrstatecallback == null)
            break MISSING_BLOCK_LABEL_44;
        try
        {
            mService.unregisterListener(((CallbackEntry) (vrstatecallback)).mStateCallback);
        }
        catch(RemoteException remoteexception) { }
        mService.unregisterPersistentVrStateListener(((CallbackEntry) (vrstatecallback)).mPersistentStateCallback);
_L2:
        return;
        vrstatecallback;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private Map mCallbackMap;
    private final IVrManager mService;

    // Unreferenced inner class android/app/VrManager$CallbackEntry$1

/* anonymous class */
    class CallbackEntry._cls1 extends android.service.vr.IVrStateCallbacks.Stub
    {

        void lambda$_2D_android_app_VrManager$CallbackEntry$1_902(boolean flag)
        {
            mCallback.onVrStateChanged(flag);
        }

        public void onVrStateChanged(boolean flag)
        {
            mHandler.post(new _.Lambda.BjtyKj7ksh5kcpFCATScxTJ5PrQ((byte)0, flag, this));
        }

        final CallbackEntry this$1;

            
            {
                this$1 = CallbackEntry.this;
                super();
            }
    }


    // Unreferenced inner class android/app/VrManager$CallbackEntry$2

/* anonymous class */
    class CallbackEntry._cls2 extends android.service.vr.IPersistentVrStateCallbacks.Stub
    {

        void lambda$_2D_android_app_VrManager$CallbackEntry$2_1220(boolean flag)
        {
            mCallback.onPersistentVrStateChanged(flag);
        }

        public void onPersistentVrStateChanged(boolean flag)
        {
            mHandler.post(new _.Lambda.BjtyKj7ksh5kcpFCATScxTJ5PrQ((byte)1, flag, this));
        }

        final CallbackEntry this$1;

            
            {
                this$1 = CallbackEntry.this;
                super();
            }
    }

}

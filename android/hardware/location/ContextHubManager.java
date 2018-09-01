// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.content.Context;
import android.os.*;
import android.util.Log;

// Referenced classes of package android.hardware.location:
//            IContextHubService, NanoAppFilter, ContextHubInfo, NanoAppInstanceInfo, 
//            NanoApp, ContextHubMessage

public final class ContextHubManager
{
    public static abstract class Callback
    {

        public abstract void onMessageReceipt(int i, int j, ContextHubMessage contexthubmessage);

        protected Callback()
        {
        }
    }

    public static interface ICallback
    {

        public abstract void onMessageReceipt(int i, int j, ContextHubMessage contexthubmessage);
    }


    static Callback _2D_get0(ContextHubManager contexthubmanager)
    {
        return contexthubmanager.mCallback;
    }

    static Handler _2D_get1(ContextHubManager contexthubmanager)
    {
        return contexthubmanager.mCallbackHandler;
    }

    static ICallback _2D_get2(ContextHubManager contexthubmanager)
    {
        return contexthubmanager.mLocalCallback;
    }

    static Looper _2D_get3(ContextHubManager contexthubmanager)
    {
        return contexthubmanager.mMainLooper;
    }

    public ContextHubManager(Context context, Looper looper)
        throws android.os.ServiceManager.ServiceNotFoundException
    {
        mClientCallback = new IContextHubCallback.Stub() {

            public void onMessageReceipt(int i, int j, ContextHubMessage contexthubmessage)
            {
                if(ContextHubManager._2D_get0(ContextHubManager.this) == null) goto _L2; else goto _L1
_L1:
                this;
                JVM INSTR monitorenter ;
                Callback callback = ContextHubManager._2D_get0(ContextHubManager.this);
                if(ContextHubManager._2D_get1(ContextHubManager.this) != null) goto _L4; else goto _L3
_L3:
                Handler handler;
                handler = JVM INSTR new #30  <Class Handler>;
                handler.Handler(ContextHubManager._2D_get3(ContextHubManager.this));
_L5:
                Runnable runnable = JVM INSTR new #8   <Class ContextHubManager$1$1>;
                j.contexthubmessage. _cls1();
                handler.post(runnable);
_L6:
                this;
                JVM INSTR monitorexit ;
_L7:
                return;
_L4:
                handler = ContextHubManager._2D_get1(ContextHubManager.this);
                  goto _L5
                contexthubmessage;
                throw contexthubmessage;
_L2:
                if(ContextHubManager._2D_get2(ContextHubManager.this) == null)
                    break MISSING_BLOCK_LABEL_127;
                this;
                JVM INSTR monitorenter ;
                ContextHubManager._2D_get2(ContextHubManager.this).onMessageReceipt(i, j, contexthubmessage);
                  goto _L6
                contexthubmessage;
                throw contexthubmessage;
                Log.d("ContextHubManager", "Context hub manager client callback is NULL");
                  goto _L7
            }

            final ContextHubManager this$0;

            
            {
                this$0 = ContextHubManager.this;
                super();
            }
        }
;
        mMainLooper = looper;
        mService = IContextHubService.Stub.asInterface(ServiceManager.getServiceOrThrow("contexthub"));
        mService.registerCallback(mClientCallback);
_L1:
        return;
        context;
        Log.w("ContextHubManager", (new StringBuilder()).append("Could not register callback:").append(context).toString());
          goto _L1
    }

    public int[] findNanoAppOnHub(int i, NanoAppFilter nanoappfilter)
    {
        try
        {
            nanoappfilter = mService.findNanoAppOnHub(i, nanoappfilter);
        }
        // Misplaced declaration of an exception variable
        catch(NanoAppFilter nanoappfilter)
        {
            throw nanoappfilter.rethrowFromSystemServer();
        }
        return nanoappfilter;
    }

    public int[] getContextHubHandles()
    {
        int ai[];
        try
        {
            ai = mService.getContextHubHandles();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return ai;
    }

    public ContextHubInfo getContextHubInfo(int i)
    {
        ContextHubInfo contexthubinfo;
        try
        {
            contexthubinfo = mService.getContextHubInfo(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return contexthubinfo;
    }

    public NanoAppInstanceInfo getNanoAppInstanceInfo(int i)
    {
        NanoAppInstanceInfo nanoappinstanceinfo;
        try
        {
            nanoappinstanceinfo = mService.getNanoAppInstanceInfo(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return nanoappinstanceinfo;
    }

    public int loadNanoApp(int i, NanoApp nanoapp)
    {
        try
        {
            i = mService.loadNanoApp(i, nanoapp);
        }
        // Misplaced declaration of an exception variable
        catch(NanoApp nanoapp)
        {
            throw nanoapp.rethrowFromSystemServer();
        }
        return i;
    }

    public int registerCallback(Callback callback)
    {
        return registerCallback(callback, null);
    }

    public int registerCallback(Callback callback, Handler handler)
    {
        this;
        JVM INSTR monitorenter ;
        if(mCallback == null)
            break MISSING_BLOCK_LABEL_21;
        Log.w("ContextHubManager", "Max number of callbacks reached!");
        this;
        JVM INSTR monitorexit ;
        return -1;
        mCallback = callback;
        mCallbackHandler = handler;
        this;
        JVM INSTR monitorexit ;
        return 0;
        callback;
        throw callback;
    }

    public int registerCallback(ICallback icallback)
    {
        if(mLocalCallback != null)
        {
            Log.w("ContextHubManager", "Max number of local callbacks reached!");
            return -1;
        } else
        {
            mLocalCallback = icallback;
            return 0;
        }
    }

    public int sendMessage(int i, int j, ContextHubMessage contexthubmessage)
    {
        try
        {
            i = mService.sendMessage(i, j, contexthubmessage);
        }
        // Misplaced declaration of an exception variable
        catch(ContextHubMessage contexthubmessage)
        {
            throw contexthubmessage.rethrowFromSystemServer();
        }
        return i;
    }

    public int unloadNanoApp(int i)
    {
        try
        {
            i = mService.unloadNanoApp(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public int unregisterCallback(Callback callback)
    {
        this;
        JVM INSTR monitorenter ;
        if(callback == mCallback)
            break MISSING_BLOCK_LABEL_22;
        Log.w("ContextHubManager", "Cannot recognize callback!");
        this;
        JVM INSTR monitorexit ;
        return -1;
        mCallback = null;
        mCallbackHandler = null;
        this;
        JVM INSTR monitorexit ;
        return 0;
        callback;
        throw callback;
    }

    public int unregisterCallback(ICallback icallback)
    {
        this;
        JVM INSTR monitorenter ;
        if(icallback == mLocalCallback)
            break MISSING_BLOCK_LABEL_22;
        Log.w("ContextHubManager", "Cannot recognize local callback!");
        this;
        JVM INSTR monitorexit ;
        return -1;
        mLocalCallback = null;
        this;
        JVM INSTR monitorexit ;
        return 0;
        icallback;
        throw icallback;
    }

    private static final String TAG = "ContextHubManager";
    private Callback mCallback;
    private Handler mCallbackHandler;
    private final IContextHubCallback.Stub mClientCallback;
    private ICallback mLocalCallback;
    private final Looper mMainLooper;
    private final IContextHubService mService;

    // Unreferenced inner class android/hardware/location/ContextHubManager$1$1

/* anonymous class */
    class _cls1
        implements Runnable
    {

        public void run()
        {
            callback.onMessageReceipt(hubId, nanoAppId, message);
        }

        final _cls1 this$1;
        final Callback val$callback;
        final int val$hubId;
        final ContextHubMessage val$message;
        final int val$nanoAppId;

            
            {
                this$1 = final__pcls1;
                callback = callback1;
                hubId = i;
                nanoAppId = j;
                message = ContextHubMessage.this;
                super();
            }
    }

}

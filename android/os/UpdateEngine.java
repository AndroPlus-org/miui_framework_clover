// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            ServiceManager, RemoteException, IUpdateEngine, IUpdateEngineCallback, 
//            UpdateEngineCallback, Handler

public class UpdateEngine
{
    public static final class ErrorCodeConstants
    {

        public static final int DOWNLOAD_PAYLOAD_VERIFICATION_ERROR = 12;
        public static final int DOWNLOAD_TRANSFER_ERROR = 9;
        public static final int ERROR = 1;
        public static final int FILESYSTEM_COPIER_ERROR = 4;
        public static final int INSTALL_DEVICE_OPEN_ERROR = 7;
        public static final int KERNEL_DEVICE_OPEN_ERROR = 8;
        public static final int PAYLOAD_HASH_MISMATCH_ERROR = 10;
        public static final int PAYLOAD_MISMATCHED_TYPE_ERROR = 6;
        public static final int PAYLOAD_SIZE_MISMATCH_ERROR = 11;
        public static final int POST_INSTALL_RUNNER_ERROR = 5;
        public static final int SUCCESS = 0;

        public ErrorCodeConstants()
        {
        }
    }

    public static final class UpdateStatusConstants
    {

        public static final int ATTEMPTING_ROLLBACK = 8;
        public static final int CHECKING_FOR_UPDATE = 1;
        public static final int DISABLED = 9;
        public static final int DOWNLOADING = 3;
        public static final int FINALIZING = 5;
        public static final int IDLE = 0;
        public static final int REPORTING_ERROR_EVENT = 7;
        public static final int UPDATED_NEED_REBOOT = 6;
        public static final int UPDATE_AVAILABLE = 2;
        public static final int VERIFYING = 4;

        public UpdateStatusConstants()
        {
        }
    }


    public UpdateEngine()
    {
        mUpdateEngineCallback = null;
        mUpdateEngine = IUpdateEngine.Stub.asInterface(ServiceManager.getService("android.os.UpdateEngineService"));
    }

    public void applyPayload(String s, long l, long l1, String as[])
    {
        try
        {
            mUpdateEngine.applyPayload(s, l, l1, as);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public boolean bind(UpdateEngineCallback updateenginecallback)
    {
        return bind(updateenginecallback, null);
    }

    public boolean bind(UpdateEngineCallback updateenginecallback, Handler handler)
    {
        Object obj = mUpdateEngineCallbackLock;
        obj;
        JVM INSTR monitorenter ;
        IUpdateEngineCallback.Stub stub = JVM INSTR new #6   <Class UpdateEngine$1>;
        stub.this. _cls1();
        mUpdateEngineCallback = stub;
        boolean flag = mUpdateEngine.bind(mUpdateEngineCallback);
        obj;
        JVM INSTR monitorexit ;
        return flag;
        updateenginecallback;
        throw updateenginecallback.rethrowFromSystemServer();
        updateenginecallback;
        obj;
        JVM INSTR monitorexit ;
        throw updateenginecallback;
    }

    public void cancel()
    {
        try
        {
            mUpdateEngine.cancel();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void resetStatus()
    {
        try
        {
            mUpdateEngine.resetStatus();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void resume()
    {
        try
        {
            mUpdateEngine.resume();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void suspend()
    {
        try
        {
            mUpdateEngine.suspend();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public boolean unbind()
    {
        Object obj = mUpdateEngineCallbackLock;
        obj;
        JVM INSTR monitorenter ;
        IUpdateEngineCallback iupdateenginecallback = mUpdateEngineCallback;
        if(iupdateenginecallback != null)
            break MISSING_BLOCK_LABEL_20;
        obj;
        JVM INSTR monitorexit ;
        return true;
        boolean flag;
        flag = mUpdateEngine.unbind(mUpdateEngineCallback);
        mUpdateEngineCallback = null;
        obj;
        JVM INSTR monitorexit ;
        return flag;
        Object obj1;
        obj1;
        throw ((RemoteException) (obj1)).rethrowFromSystemServer();
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
    }

    private static final String TAG = "UpdateEngine";
    private static final String UPDATE_ENGINE_SERVICE = "android.os.UpdateEngineService";
    private IUpdateEngine mUpdateEngine;
    private IUpdateEngineCallback mUpdateEngineCallback;
    private final Object mUpdateEngineCallbackLock = new Object();

    // Unreferenced inner class android/os/UpdateEngine$1

/* anonymous class */
    class _cls1 extends IUpdateEngineCallback.Stub
    {

        public void onPayloadApplicationComplete(int i)
        {
            if(handler != null)
                handler.post(i. new Runnable() {

                    public void run()
                    {
                        callback.onPayloadApplicationComplete(errorCode);
                    }

                    final _cls1 this$1;
                    final UpdateEngineCallback val$callback;
                    final int val$errorCode;

            
            {
                this$1 = final__pcls1;
                callback = updateenginecallback;
                errorCode = I.this;
                super();
            }
                }
);
            else
                callback.onPayloadApplicationComplete(i);
        }

        public void onStatusUpdate(final int status, float f)
        {
            if(handler != null)
                handler.post(f. new Runnable() {

                    public void run()
                    {
                        callback.onStatusUpdate(status, percent);
                    }

                    final _cls1 this$1;
                    final UpdateEngineCallback val$callback;
                    final float val$percent;
                    final int val$status;

            
            {
                this$1 = final__pcls1;
                callback = updateenginecallback;
                status = i;
                percent = F.this;
                super();
            }
                }
);
            else
                callback.onStatusUpdate(status, f);
        }

        final UpdateEngine this$0;
        final UpdateEngineCallback val$callback;
        final Handler val$handler;

            
            {
                this$0 = UpdateEngine.this;
                handler = handler1;
                callback = updateenginecallback;
                super();
            }
    }

}

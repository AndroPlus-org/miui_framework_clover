// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm.permission;

import android.content.*;
import android.content.pm.PackageManager;
import android.os.*;
import android.util.Log;
import com.android.internal.os.SomeArgs;
import java.util.*;

// Referenced classes of package android.content.pm.permission:
//            IRuntimePermissionPresenter

public final class RuntimePermissionPresenter
{
    public static abstract class OnResultCallback
    {

        public void onGetAppPermissions(List list)
        {
        }

        public OnResultCallback()
        {
        }
    }

    private static final class RemoteService extends Handler
        implements ServiceConnection
    {

        private void scheduleNextMessageIfNeededLocked()
        {
            if(mBound && mRemoteInstance != null && mPendingWork.isEmpty() ^ true)
                sendMessage((Message)mPendingWork.remove(0));
        }

        private void scheduleUnbind()
        {
            removeMessages(3);
            sendEmptyMessageDelayed(3, 10000L);
        }

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 4: default 36
        //                       1 50
        //                       2 36
        //                       3 171
        //                       4 213;
               goto _L1 _L2 _L1 _L3 _L4
_L1:
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            scheduleNextMessageIfNeededLocked();
            obj;
            JVM INSTR monitorexit ;
            return;
_L2:
            Object obj1;
            SomeArgs someargs = (SomeArgs)message.obj;
            obj = (String)someargs.arg1;
            message = (OnResultCallback)someargs.arg2;
            obj1 = (Handler)someargs.arg3;
            someargs.recycle();
            Object obj2 = mLock;
            obj2;
            JVM INSTR monitorenter ;
            IRuntimePermissionPresenter iruntimepermissionpresenter = mRemoteInstance;
            obj2;
            JVM INSTR monitorexit ;
              goto _L5
            message;
            throw message;
_L5:
            if(iruntimepermissionpresenter == null)
                return;
            try
            {
                RemoteCallback remotecallback = JVM INSTR new #127 <Class RemoteCallback>;
                android.os.RemoteCallback.OnResultListener onresultlistener = JVM INSTR new #11  <Class RuntimePermissionPresenter$RemoteService$1>;
                ((_cls1) (obj1)).message. _cls1();
                remotecallback.RemoteCallback(onresultlistener, this);
                iruntimepermissionpresenter.getAppPermissions(((String) (obj)), remotecallback);
            }
            // Misplaced declaration of an exception variable
            catch(Message message)
            {
                Log.e("RuntimePermPresenter", "Error getting app permissions", message);
            }
            scheduleUnbind();
            continue; /* Loop/switch isn't completed */
_L3:
            obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            if(mBound)
            {
                mContext.unbindService(this);
                mBound = false;
            }
            mRemoteInstance = null;
            obj;
            JVM INSTR monitorexit ;
            continue; /* Loop/switch isn't completed */
            message;
            throw message;
_L4:
            obj1 = (SomeArgs)message.obj;
            obj = (String)((SomeArgs) (obj1)).arg1;
            message = (String)((SomeArgs) (obj1)).arg2;
            ((SomeArgs) (obj1)).recycle();
            obj1 = mLock;
            obj1;
            JVM INSTR monitorenter ;
            iruntimepermissionpresenter = mRemoteInstance;
            obj1;
            JVM INSTR monitorexit ;
              goto _L6
            message;
            throw message;
_L6:
            if(iruntimepermissionpresenter == null)
                return;
            try
            {
                iruntimepermissionpresenter.revokeRuntimePermission(((String) (obj)), message);
            }
            // Misplaced declaration of an exception variable
            catch(Message message)
            {
                Log.e("RuntimePermPresenter", "Error getting app permissions", message);
            }
            if(true) goto _L1; else goto _L7
_L7:
            message;
            throw message;
        }

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            componentname = ((ComponentName) (mLock));
            componentname;
            JVM INSTR monitorenter ;
            mRemoteInstance = IRuntimePermissionPresenter.Stub.asInterface(ibinder);
            scheduleNextMessageIfNeededLocked();
            componentname;
            JVM INSTR monitorexit ;
            return;
            ibinder;
            throw ibinder;
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            componentname = ((ComponentName) (mLock));
            componentname;
            JVM INSTR monitorenter ;
            mRemoteInstance = null;
            componentname;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void processMessage(Message message)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            if(!mBound)
            {
                Intent intent = JVM INSTR new #172 <Class Intent>;
                intent.Intent("android.permissionpresenterservice.RuntimePermissionPresenterService");
                intent.setPackage(mContext.getPackageManager().getPermissionControllerPackageName());
                mBound = mContext.bindService(intent, this, 1);
            }
            mPendingWork.add(message);
            scheduleNextMessageIfNeededLocked();
            obj;
            JVM INSTR monitorexit ;
            return;
            message;
            throw message;
        }

        public static final int MSG_GET_APPS_USING_PERMISSIONS = 2;
        public static final int MSG_GET_APP_PERMISSIONS = 1;
        public static final int MSG_REVOKE_APP_PERMISSIONS = 4;
        public static final int MSG_UNBIND = 3;
        private static final long UNBIND_TIMEOUT_MILLIS = 10000L;
        private boolean mBound;
        private final Context mContext;
        private final Object mLock = new Object();
        private final List mPendingWork = new ArrayList();
        private IRuntimePermissionPresenter mRemoteInstance;

        public RemoteService(Context context)
        {
            super(context.getMainLooper(), null, false);
            mContext = context;
        }
    }


    private RuntimePermissionPresenter(Context context)
    {
        mRemoteService = new RemoteService(context);
    }

    public static RuntimePermissionPresenter getInstance(Context context)
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        if(sInstance == null)
        {
            RuntimePermissionPresenter runtimepermissionpresenter = JVM INSTR new #2   <Class RuntimePermissionPresenter>;
            runtimepermissionpresenter.RuntimePermissionPresenter(context.getApplicationContext());
            sInstance = runtimepermissionpresenter;
        }
        context = sInstance;
        obj;
        JVM INSTR monitorexit ;
        return context;
        context;
        throw context;
    }

    public void getAppPermissions(String s, OnResultCallback onresultcallback, Handler handler)
    {
        SomeArgs someargs = SomeArgs.obtain();
        someargs.arg1 = s;
        someargs.arg2 = onresultcallback;
        someargs.arg3 = handler;
        s = mRemoteService.obtainMessage(1, someargs);
        mRemoteService.processMessage(s);
    }

    public void revokeRuntimePermission(String s, String s1)
    {
        SomeArgs someargs = SomeArgs.obtain();
        someargs.arg1 = s;
        someargs.arg2 = s1;
        s = mRemoteService.obtainMessage(4, someargs);
        mRemoteService.processMessage(s);
    }

    public static final String KEY_RESULT = "android.content.pm.permission.RuntimePermissionPresenter.key.result";
    private static final String TAG = "RuntimePermPresenter";
    private static RuntimePermissionPresenter sInstance;
    private static final Object sLock = new Object();
    private final RemoteService mRemoteService;


    // Unreferenced inner class android/content/pm/permission/RuntimePermissionPresenter$RemoteService$1

/* anonymous class */
    class RemoteService._cls1
        implements android.os.RemoteCallback.OnResultListener
    {

        public void onResult(Bundle bundle)
        {
            ArrayList arraylist = null;
            if(bundle != null)
                arraylist = bundle.getParcelableArrayList("android.content.pm.permission.RuntimePermissionPresenter.key.result");
            bundle = arraylist;
            if(arraylist == null)
                bundle = Collections.emptyList();
            if(handler != null)
                handler.post(bundle. new Runnable() {

                    public void run()
                    {
                        callback.onGetAppPermissions(reportedPermissions);
                    }

                    final RemoteService._cls1 this$2;
                    final OnResultCallback val$callback;
                    final List val$reportedPermissions;

            
            {
                this$2 = final__pcls1;
                callback = onresultcallback;
                reportedPermissions = List.this;
                super();
            }
                }
);
            else
                callback.onGetAppPermissions(bundle);
        }

        final RemoteService this$1;
        final OnResultCallback val$callback;
        final Handler val$handler;

            
            {
                this$1 = final_remoteservice;
                handler = handler1;
                callback = OnResultCallback.this;
                super();
            }
    }

}

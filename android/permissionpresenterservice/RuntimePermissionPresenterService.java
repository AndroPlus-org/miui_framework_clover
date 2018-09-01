// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.permissionpresenterservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.*;
import com.android.internal.os.SomeArgs;
import java.util.List;

public abstract class RuntimePermissionPresenterService extends Service
{
    private final class MyHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 3: default 32
        //                       1 33
        //                       2 32
        //                       3 116;
               goto _L1 _L2 _L1 _L3
_L1:
            return;
_L2:
            Object obj = (SomeArgs)message.obj;
            String s1 = (String)((SomeArgs) (obj)).arg1;
            message = (RemoteCallback)((SomeArgs) (obj)).arg2;
            ((SomeArgs) (obj)).recycle();
            obj = onGetAppPermissions(s1);
            if(obj != null && ((List) (obj)).isEmpty() ^ true)
            {
                Bundle bundle = new Bundle();
                bundle.putParcelableList("android.content.pm.permission.RuntimePermissionPresenter.key.result", ((List) (obj)));
                message.sendResult(bundle);
            } else
            {
                message.sendResult(null);
            }
            continue; /* Loop/switch isn't completed */
_L3:
            message = (SomeArgs)message.obj;
            String s2 = (String)((SomeArgs) (message)).arg1;
            String s = (String)((SomeArgs) (message)).arg2;
            message.recycle();
            onRevokeRuntimePermission(s2, s);
            if(true) goto _L1; else goto _L4
_L4:
        }

        public static final int MSG_GET_APPS_USING_PERMISSIONS = 2;
        public static final int MSG_GET_APP_PERMISSIONS = 1;
        public static final int MSG_REVOKE_APP_PERMISSION = 3;
        final RuntimePermissionPresenterService this$0;

        public MyHandler(Looper looper)
        {
            this$0 = RuntimePermissionPresenterService.this;
            super(looper, null, false);
        }
    }


    static Handler _2D_get0(RuntimePermissionPresenterService runtimepermissionpresenterservice)
    {
        return runtimepermissionpresenterservice.mHandler;
    }

    public RuntimePermissionPresenterService()
    {
    }

    public final void attachBaseContext(Context context)
    {
        super.attachBaseContext(context);
        mHandler = new MyHandler(context.getMainLooper());
    }

    public final IBinder onBind(Intent intent)
    {
        return new android.content.pm.permission.IRuntimePermissionPresenter.Stub() {

            public void getAppPermissions(String s, RemoteCallback remotecallback)
            {
                SomeArgs someargs = SomeArgs.obtain();
                someargs.arg1 = s;
                someargs.arg2 = remotecallback;
                RuntimePermissionPresenterService._2D_get0(RuntimePermissionPresenterService.this).obtainMessage(1, someargs).sendToTarget();
            }

            public void revokeRuntimePermission(String s, String s1)
            {
                SomeArgs someargs = SomeArgs.obtain();
                someargs.arg1 = s;
                someargs.arg2 = s1;
                RuntimePermissionPresenterService._2D_get0(RuntimePermissionPresenterService.this).obtainMessage(3, someargs).sendToTarget();
            }

            final RuntimePermissionPresenterService this$0;

            
            {
                this$0 = RuntimePermissionPresenterService.this;
                super();
            }
        }
;
    }

    public abstract List onGetAppPermissions(String s);

    public abstract void onRevokeRuntimePermission(String s, String s1);

    public static final String SERVICE_INTERFACE = "android.permissionpresenterservice.RuntimePermissionPresenterService";
    private Handler mHandler;
}

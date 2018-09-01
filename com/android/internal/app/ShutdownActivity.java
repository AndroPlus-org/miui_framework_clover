// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.Activity;
import android.content.Intent;
import android.os.*;
import android.util.Slog;

public class ShutdownActivity extends Activity
{

    static boolean _2D_get0(ShutdownActivity shutdownactivity)
    {
        return shutdownactivity.mConfirm;
    }

    static boolean _2D_get1(ShutdownActivity shutdownactivity)
    {
        return shutdownactivity.mReboot;
    }

    static boolean _2D_get2(ShutdownActivity shutdownactivity)
    {
        return shutdownactivity.mUserRequested;
    }

    public ShutdownActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        bundle = getIntent();
        mReboot = "android.intent.action.REBOOT".equals(bundle.getAction());
        mConfirm = bundle.getBooleanExtra("android.intent.extra.KEY_CONFIRM", false);
        mUserRequested = bundle.getBooleanExtra("android.intent.extra.USER_REQUESTED_SHUTDOWN", false);
        Slog.i("ShutdownActivity", (new StringBuilder()).append("onCreate(): confirm=").append(mConfirm).toString());
        bundle = new Thread("ShutdownActivity") {

            public void run()
            {
                String s;
                IPowerManager ipowermanager;
                s = null;
                ipowermanager = android.os.IPowerManager.Stub.asInterface(ServiceManager.getService("power"));
                if(!ShutdownActivity._2D_get1(ShutdownActivity.this)) goto _L2; else goto _L1
_L1:
                ipowermanager.reboot(ShutdownActivity._2D_get0(ShutdownActivity.this), null, false);
_L3:
                return;
_L2:
                boolean flag = ShutdownActivity._2D_get0(ShutdownActivity.this);
                if(ShutdownActivity._2D_get2(ShutdownActivity.this))
                    s = "userrequested";
                try
                {
                    ipowermanager.shutdown(flag, s, false);
                }
                catch(RemoteException remoteexception) { }
                  goto _L3
            }

            final ShutdownActivity this$0;

            
            {
                this$0 = ShutdownActivity.this;
                super(s);
            }
        }
;
        bundle.start();
        finish();
        bundle.join();
_L2:
        return;
        bundle;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final String TAG = "ShutdownActivity";
    private boolean mConfirm;
    private boolean mReboot;
    private boolean mUserRequested;
}

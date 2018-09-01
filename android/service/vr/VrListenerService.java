// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.vr;

import android.app.ActivityManager;
import android.app.Service;
import android.content.*;
import android.os.*;

public abstract class VrListenerService extends Service
{
    private final class VrListenerHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            boolean flag = true;
            message.what;
            JVM INSTR tableswitch 1 1: default 24
        //                       1 25;
               goto _L1 _L2
_L1:
            return;
_L2:
            VrListenerService vrlistenerservice = VrListenerService.this;
            ComponentName componentname = (ComponentName)message.obj;
            if(message.arg1 != 1)
                flag = false;
            vrlistenerservice.onCurrentVrActivityChanged(componentname, flag, message.arg2);
            if(true) goto _L1; else goto _L3
_L3:
        }

        final VrListenerService this$0;

        public VrListenerHandler(Looper looper)
        {
            this$0 = VrListenerService.this;
            super(looper);
        }
    }


    static Handler _2D_get0(VrListenerService vrlistenerservice)
    {
        return vrlistenerservice.mHandler;
    }

    public VrListenerService()
    {
    }

    public static final boolean isVrModePackageEnabled(Context context, ComponentName componentname)
    {
        context = (ActivityManager)context.getSystemService(android/app/ActivityManager);
        if(context == null)
            return false;
        else
            return context.isVrModePackageEnabled(componentname);
    }

    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }

    public void onCurrentVrActivityChanged(ComponentName componentname)
    {
    }

    public void onCurrentVrActivityChanged(ComponentName componentname, boolean flag, int i)
    {
        if(flag)
            componentname = null;
        onCurrentVrActivityChanged(componentname);
    }

    private static final int MSG_ON_CURRENT_VR_ACTIVITY_CHANGED = 1;
    public static final String SERVICE_INTERFACE = "android.service.vr.VrListenerService";
    private final IVrListener.Stub mBinder = new IVrListener.Stub() {

        public void focusedActivityChanged(ComponentName componentname, boolean flag, int i)
        {
            Handler handler = VrListenerService._2D_get0(VrListenerService.this);
            int j;
            if(flag)
                j = 1;
            else
                j = 0;
            handler.obtainMessage(1, j, i, componentname).sendToTarget();
        }

        final VrListenerService this$0;

            
            {
                this$0 = VrListenerService.this;
                super();
            }
    }
;
    private final Handler mHandler = new VrListenerHandler(Looper.getMainLooper());
}

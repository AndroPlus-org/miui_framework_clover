// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.media;

import android.app.Service;
import android.content.Intent;
import android.os.*;

public abstract class CameraPrewarmService extends Service
{

    static boolean _2D_set0(CameraPrewarmService cameraprewarmservice, boolean flag)
    {
        cameraprewarmservice.mCameraIntentFired = flag;
        return flag;
    }

    public CameraPrewarmService()
    {
    }

    public IBinder onBind(Intent intent)
    {
        if("android.service.media.CameraPrewarmService.ACTION_PREWARM".equals(intent.getAction()))
        {
            onPrewarm();
            return (new Messenger(mHandler)).getBinder();
        } else
        {
            return null;
        }
    }

    public abstract void onCooldown(boolean flag);

    public abstract void onPrewarm();

    public boolean onUnbind(Intent intent)
    {
        if("android.service.media.CameraPrewarmService.ACTION_PREWARM".equals(intent.getAction()))
            onCooldown(mCameraIntentFired);
        return false;
    }

    public static final String ACTION_PREWARM = "android.service.media.CameraPrewarmService.ACTION_PREWARM";
    public static final int MSG_CAMERA_FIRED = 1;
    private boolean mCameraIntentFired;
    private final Handler mHandler = new Handler() {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 1: default 24
        //                       1 30;
               goto _L1 _L2
_L1:
            super.handleMessage(message);
_L4:
            return;
_L2:
            CameraPrewarmService._2D_set0(CameraPrewarmService.this, true);
            if(true) goto _L4; else goto _L3
_L3:
        }

        final CameraPrewarmService this$0;

            
            {
                this$0 = CameraPrewarmService.this;
                super();
            }
    }
;
}

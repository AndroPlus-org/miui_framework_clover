// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.hdmi;

import android.os.RemoteException;
import android.util.Log;

// Referenced classes of package android.hardware.hdmi:
//            HdmiClient, IHdmiControlService, HdmiDeviceInfo, IHdmiControlCallback

public final class HdmiPlaybackClient extends HdmiClient
{
    public static interface DisplayStatusCallback
    {

        public abstract void onComplete(int i);
    }

    public static interface OneTouchPlayCallback
    {

        public abstract void onComplete(int i);
    }


    HdmiPlaybackClient(IHdmiControlService ihdmicontrolservice)
    {
        super(ihdmicontrolservice);
    }

    private IHdmiControlCallback getCallbackWrapper(final DisplayStatusCallback callback)
    {
        return new IHdmiControlCallback.Stub() {

            public void onComplete(int i)
            {
                callback.onComplete(i);
            }

            final HdmiPlaybackClient this$0;
            final DisplayStatusCallback val$callback;

            
            {
                this$0 = HdmiPlaybackClient.this;
                callback = displaystatuscallback;
                super();
            }
        }
;
    }

    private IHdmiControlCallback getCallbackWrapper(final OneTouchPlayCallback callback)
    {
        return new IHdmiControlCallback.Stub() {

            public void onComplete(int i)
            {
                callback.onComplete(i);
            }

            final HdmiPlaybackClient this$0;
            final OneTouchPlayCallback val$callback;

            
            {
                this$0 = HdmiPlaybackClient.this;
                callback = onetouchplaycallback;
                super();
            }
        }
;
    }

    public int getDeviceType()
    {
        return 4;
    }

    public void oneTouchPlay(OneTouchPlayCallback onetouchplaycallback)
    {
        mService.oneTouchPlay(getCallbackWrapper(onetouchplaycallback));
_L1:
        return;
        onetouchplaycallback;
        Log.e("HdmiPlaybackClient", "oneTouchPlay threw exception ", onetouchplaycallback);
          goto _L1
    }

    public void queryDisplayStatus(DisplayStatusCallback displaystatuscallback)
    {
        mService.queryDisplayStatus(getCallbackWrapper(displaystatuscallback));
_L1:
        return;
        displaystatuscallback;
        Log.e("HdmiPlaybackClient", "queryDisplayStatus threw exception ", displaystatuscallback);
          goto _L1
    }

    public void sendStandby()
    {
        mService.sendStandby(getDeviceType(), HdmiDeviceInfo.idForCecDevice(0));
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("HdmiPlaybackClient", "sendStandby threw exception ", remoteexception);
          goto _L1
    }

    private static final int ADDR_TV = 0;
    private static final String TAG = "HdmiPlaybackClient";
}

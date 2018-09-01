// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.radio;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

class TunerCallbackAdapter extends ITunerCallback.Stub
{

    TunerCallbackAdapter(RadioTuner.Callback callback, Handler handler)
    {
        mCallback = callback;
        if(handler == null)
            mHandler = new Handler(Looper.getMainLooper());
        else
            mHandler = handler;
    }

    void lambda$_2D_android_hardware_radio_TunerCallbackAdapter_1493(int i)
    {
        mCallback.onError(i);
    }

    void lambda$_2D_android_hardware_radio_TunerCallbackAdapter_1643(RadioManager.BandConfig bandconfig)
    {
        mCallback.onConfigurationChanged(bandconfig);
    }

    void lambda$_2D_android_hardware_radio_TunerCallbackAdapter_1927(RadioManager.ProgramInfo programinfo)
    {
        mCallback.onProgramInfoChanged(programinfo);
        programinfo = programinfo.getMetadata();
        if(programinfo != null)
            mCallback.onMetadataChanged(programinfo);
    }

    void lambda$_2D_android_hardware_radio_TunerCallbackAdapter_2227(boolean flag)
    {
        mCallback.onTrafficAnnouncement(flag);
    }

    void lambda$_2D_android_hardware_radio_TunerCallbackAdapter_2376(boolean flag)
    {
        mCallback.onEmergencyAnnouncement(flag);
    }

    void lambda$_2D_android_hardware_radio_TunerCallbackAdapter_2521(boolean flag)
    {
        mCallback.onAntennaState(flag);
    }

    void lambda$_2D_android_hardware_radio_TunerCallbackAdapter_2682(boolean flag)
    {
        mCallback.onBackgroundScanAvailabilityChange(flag);
    }

    void lambda$_2D_android_hardware_radio_TunerCallbackAdapter_2836()
    {
        mCallback.onBackgroundScanComplete();
    }

    void lambda$_2D_android_hardware_radio_TunerCallbackAdapter_2965()
    {
        mCallback.onProgramListChanged();
    }

    public void onAntennaState(boolean flag)
    {
        mHandler.post(new _.Lambda.JnOBQcNE2QHtc2zY4hNL33J974o._cls3((byte)0, flag, this));
    }

    public void onBackgroundScanAvailabilityChange(boolean flag)
    {
        mHandler.post(new _.Lambda.JnOBQcNE2QHtc2zY4hNL33J974o._cls3((byte)1, flag, this));
    }

    public void onBackgroundScanComplete()
    {
        mHandler.post(new _.Lambda.JnOBQcNE2QHtc2zY4hNL33J974o((byte)0, this));
    }

    public void onConfigurationChanged(RadioManager.BandConfig bandconfig)
    {
        mHandler.post(new _.Lambda.JnOBQcNE2QHtc2zY4hNL33J974o._cls1((byte)0, this, bandconfig));
    }

    public void onCurrentProgramInfoChanged(RadioManager.ProgramInfo programinfo)
    {
        if(programinfo == null)
        {
            Log.e("BroadcastRadio.TunerCallbackAdapter", "ProgramInfo must not be null");
            return;
        } else
        {
            mHandler.post(new _.Lambda.JnOBQcNE2QHtc2zY4hNL33J974o._cls1((byte)1, this, programinfo));
            return;
        }
    }

    public void onEmergencyAnnouncement(boolean flag)
    {
        mHandler.post(new _.Lambda.JnOBQcNE2QHtc2zY4hNL33J974o._cls3((byte)2, flag, this));
    }

    public void onError(int i)
    {
        mHandler.post(new _.Lambda.JnOBQcNE2QHtc2zY4hNL33J974o._cls2(i, this));
    }

    public void onProgramListChanged()
    {
        mHandler.post(new _.Lambda.JnOBQcNE2QHtc2zY4hNL33J974o((byte)1, this));
    }

    public void onTrafficAnnouncement(boolean flag)
    {
        mHandler.post(new _.Lambda.JnOBQcNE2QHtc2zY4hNL33J974o._cls3((byte)3, flag, this));
    }

    private static final String TAG = "BroadcastRadio.TunerCallbackAdapter";
    private final RadioTuner.Callback mCallback;
    private final Handler mHandler;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.midi;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.util.Log;

// Referenced classes of package android.media.midi:
//            MidiDeviceServer, IMidiDeviceServer, IMidiManager, MidiReceiver, 
//            MidiDeviceInfo, MidiDeviceStatus

public abstract class MidiDeviceService extends Service
{

    public MidiDeviceService()
    {
    }

    public final MidiDeviceInfo getDeviceInfo()
    {
        return mDeviceInfo;
    }

    public final MidiReceiver[] getOutputPortReceivers()
    {
        if(mServer == null)
            return null;
        else
            return mServer.getOutputPortReceivers();
    }

    public IBinder onBind(Intent intent)
    {
        if("android.media.midi.MidiDeviceService".equals(intent.getAction()) && mServer != null)
            return mServer.getBinderInterface().asBinder();
        else
            return null;
    }

    public void onClose()
    {
    }

    public void onCreate()
    {
        mMidiManager = IMidiManager.Stub.asInterface(ServiceManager.getService("midi"));
        MidiDeviceInfo midideviceinfo = mMidiManager.getServiceDeviceInfo(getPackageName(), getClass().getName());
        if(midideviceinfo != null) goto _L2; else goto _L1
_L2:
        mDeviceInfo = midideviceinfo;
        amidireceiver1 = onGetInputPortReceivers();
        amidireceiver = amidireceiver1;
        if(amidireceiver1 != null)
            break MISSING_BLOCK_LABEL_86;
        amidireceiver = new MidiReceiver[0];
        obj = new MidiDeviceServer(mMidiManager, amidireceiver, midideviceinfo, mCallback);
_L4:
        mServer = ((MidiDeviceServer) (obj));
        return;
_L1:
        MidiReceiver amidireceiver[];
        Object obj;
        MidiReceiver amidireceiver1[];
        try
        {
            StringBuilder stringbuilder = JVM INSTR new #107 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.e("MidiDeviceService", stringbuilder.append("Could not find MidiDeviceInfo for MidiDeviceService ").append(this).toString());
            return;
        }
        catch(RemoteException remoteexception)
        {
            Log.e("MidiDeviceService", "RemoteException in IMidiManager.getServiceDeviceInfo");
        }
        remoteexception = null;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onDeviceStatusChanged(MidiDeviceStatus mididevicestatus)
    {
    }

    public abstract MidiReceiver[] onGetInputPortReceivers();

    public static final String SERVICE_INTERFACE = "android.media.midi.MidiDeviceService";
    private static final String TAG = "MidiDeviceService";
    private final MidiDeviceServer.Callback mCallback = new MidiDeviceServer.Callback() {

        public void onClose()
        {
            MidiDeviceService.this.onClose();
        }

        public void onDeviceStatusChanged(MidiDeviceServer midideviceserver, MidiDeviceStatus mididevicestatus)
        {
            MidiDeviceService.this.onDeviceStatusChanged(mididevicestatus);
        }

        final MidiDeviceService this$0;

            
            {
                this$0 = MidiDeviceService.this;
                super();
            }
    }
;
    private MidiDeviceInfo mDeviceInfo;
    private IMidiManager mMidiManager;
    private MidiDeviceServer mServer;
}

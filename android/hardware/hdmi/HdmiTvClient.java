// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.hdmi;

import android.os.RemoteException;
import android.util.Log;
import java.util.Collections;
import java.util.List;
import libcore.util.EmptyArray;

// Referenced classes of package android.hardware.hdmi:
//            HdmiClient, IHdmiControlService, IHdmiControlCallback, IHdmiInputChangeListener, 
//            IHdmiMhlVendorCommandListener, HdmiRecordListener, IHdmiRecordListener, HdmiDeviceInfo

public final class HdmiTvClient extends HdmiClient
{
    public static interface HdmiMhlVendorCommandListener
    {

        public abstract void onReceived(int i, int j, int k, byte abyte0[]);
    }

    public static interface InputChangeListener
    {

        public abstract void onChanged(HdmiDeviceInfo hdmideviceinfo);
    }

    public static interface SelectCallback
    {

        public abstract void onComplete(int i);
    }


    HdmiTvClient(IHdmiControlService ihdmicontrolservice)
    {
        super(ihdmicontrolservice);
    }

    private void checkTimerRecordingSourceType(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid source type:").append(i).toString());

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            return;
        }
    }

    static HdmiTvClient create(IHdmiControlService ihdmicontrolservice)
    {
        return new HdmiTvClient(ihdmicontrolservice);
    }

    private static IHdmiControlCallback getCallbackWrapper(SelectCallback selectcallback)
    {
        return new IHdmiControlCallback.Stub(selectcallback) {

            public void onComplete(int i)
            {
                callback.onComplete(i);
            }

            final SelectCallback val$callback;

            
            {
                callback = selectcallback;
                super();
            }
        }
;
    }

    private static IHdmiInputChangeListener getListenerWrapper(InputChangeListener inputchangelistener)
    {
        return new IHdmiInputChangeListener.Stub(inputchangelistener) {

            public void onChanged(HdmiDeviceInfo hdmideviceinfo)
            {
                listener.onChanged(hdmideviceinfo);
            }

            final InputChangeListener val$listener;

            
            {
                listener = inputchangelistener;
                super();
            }
        }
;
    }

    private IHdmiMhlVendorCommandListener getListenerWrapper(final HdmiMhlVendorCommandListener listener)
    {
        return new IHdmiMhlVendorCommandListener.Stub() {

            public void onReceived(int i, int j, int k, byte abyte0[])
            {
                listener.onReceived(i, j, k, abyte0);
            }

            final HdmiTvClient this$0;
            final HdmiMhlVendorCommandListener val$listener;

            
            {
                this$0 = HdmiTvClient.this;
                listener = hdmimhlvendorcommandlistener;
                super();
            }
        }
;
    }

    private static IHdmiRecordListener getListenerWrapper(HdmiRecordListener hdmirecordlistener)
    {
        return new IHdmiRecordListener.Stub(hdmirecordlistener) {

            public byte[] getOneTouchRecordSource(int i)
            {
                HdmiRecordSources.RecordSource recordsource = callback.onOneTouchRecordSourceRequested(i);
                if(recordsource == null)
                {
                    return EmptyArray.BYTE;
                } else
                {
                    byte abyte0[] = new byte[recordsource.getDataSize(true)];
                    recordsource.toByteArray(true, abyte0, 0);
                    return abyte0;
                }
            }

            public void onClearTimerRecordingResult(int i, int j)
            {
                callback.onClearTimerRecordingResult(i, j);
            }

            public void onOneTouchRecordResult(int i, int j)
            {
                callback.onOneTouchRecordResult(i, j);
            }

            public void onTimerRecordingResult(int i, int j)
            {
                callback.onTimerRecordingResult(i, HdmiRecordListener.TimerStatusData.parseFrom(j));
            }

            final HdmiRecordListener val$callback;

            
            {
                callback = hdmirecordlistener;
                super();
            }
        }
;
    }

    public void clearTimerRecording(int i, int j, HdmiTimerRecordSources.TimerRecordSource timerrecordsource)
    {
        if(timerrecordsource == null)
            throw new IllegalArgumentException("source must not be null.");
        checkTimerRecordingSourceType(j);
        byte abyte0[] = new byte[timerrecordsource.getDataSize()];
        timerrecordsource.toByteArray(abyte0, 0);
        mService.clearTimerRecording(i, j, abyte0);
_L1:
        return;
        timerrecordsource;
        Log.e("HdmiTvClient", "failed to start record: ", timerrecordsource);
          goto _L1
    }

    public void deviceSelect(int i, SelectCallback selectcallback)
    {
        if(selectcallback == null)
            throw new IllegalArgumentException("callback must not be null.");
        mService.deviceSelect(i, getCallbackWrapper(selectcallback));
_L1:
        return;
        selectcallback;
        Log.e("HdmiTvClient", "failed to select device: ", selectcallback);
          goto _L1
    }

    public List getDeviceList()
    {
        List list;
        try
        {
            list = mService.getDeviceList();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("TAG", "Failed to call getDeviceList():", remoteexception);
            return Collections.emptyList();
        }
        return list;
    }

    public int getDeviceType()
    {
        return 0;
    }

    public void portSelect(int i, SelectCallback selectcallback)
    {
        if(selectcallback == null)
            throw new IllegalArgumentException("Callback must not be null");
        mService.portSelect(i, getCallbackWrapper(selectcallback));
_L1:
        return;
        selectcallback;
        Log.e("HdmiTvClient", "failed to select port: ", selectcallback);
          goto _L1
    }

    public void sendMhlVendorCommand(int i, int j, int k, byte abyte0[])
    {
        if(abyte0 == null || abyte0.length != 16)
            throw new IllegalArgumentException("Invalid vendor command data.");
        if(j < 0 || j >= 16)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid offset:").append(j).toString());
        if(k < 0 || j + k > 16)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid length:").append(k).toString());
        mService.sendMhlVendorCommand(i, j, k, abyte0);
_L1:
        return;
        abyte0;
        Log.e("HdmiTvClient", "failed to send vendor command: ", abyte0);
          goto _L1
    }

    public void sendStandby(int i)
    {
        mService.sendStandby(getDeviceType(), i);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("HdmiTvClient", "sendStandby threw exception ", remoteexception);
          goto _L1
    }

    public void setHdmiMhlVendorCommandListener(HdmiMhlVendorCommandListener hdmimhlvendorcommandlistener)
    {
        if(hdmimhlvendorcommandlistener == null)
            throw new IllegalArgumentException("listener must not be null.");
        mService.addHdmiMhlVendorCommandListener(getListenerWrapper(hdmimhlvendorcommandlistener));
_L1:
        return;
        hdmimhlvendorcommandlistener;
        Log.e("HdmiTvClient", "failed to set hdmi mhl vendor command listener: ", hdmimhlvendorcommandlistener);
          goto _L1
    }

    public void setInputChangeListener(InputChangeListener inputchangelistener)
    {
        if(inputchangelistener == null)
            throw new IllegalArgumentException("listener must not be null.");
        mService.setInputChangeListener(getListenerWrapper(inputchangelistener));
_L1:
        return;
        inputchangelistener;
        Log.e("TAG", "Failed to set InputChangeListener:", inputchangelistener);
          goto _L1
    }

    public void setRecordListener(HdmiRecordListener hdmirecordlistener)
    {
        if(hdmirecordlistener == null)
            throw new IllegalArgumentException("listener must not be null.");
        mService.setHdmiRecordListener(getListenerWrapper(hdmirecordlistener));
_L1:
        return;
        hdmirecordlistener;
        Log.e("HdmiTvClient", "failed to set record listener.", hdmirecordlistener);
          goto _L1
    }

    public void setSystemAudioMode(boolean flag, SelectCallback selectcallback)
    {
        mService.setSystemAudioMode(flag, getCallbackWrapper(selectcallback));
_L1:
        return;
        selectcallback;
        Log.e("HdmiTvClient", "failed to set system audio mode:", selectcallback);
          goto _L1
    }

    public void setSystemAudioMute(boolean flag)
    {
        mService.setSystemAudioMute(flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("HdmiTvClient", "failed to set mute: ", remoteexception);
          goto _L1
    }

    public void setSystemAudioVolume(int i, int j, int k)
    {
        mService.setSystemAudioVolume(i, j, k);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("HdmiTvClient", "failed to set volume: ", remoteexception);
          goto _L1
    }

    public void startOneTouchRecord(int i, HdmiRecordSources.RecordSource recordsource)
    {
        if(recordsource == null)
            throw new IllegalArgumentException("source must not be null.");
        byte abyte0[] = new byte[recordsource.getDataSize(true)];
        recordsource.toByteArray(true, abyte0, 0);
        mService.startOneTouchRecord(i, abyte0);
_L1:
        return;
        recordsource;
        Log.e("HdmiTvClient", "failed to start record: ", recordsource);
          goto _L1
    }

    public void startTimerRecording(int i, int j, HdmiTimerRecordSources.TimerRecordSource timerrecordsource)
    {
        if(timerrecordsource == null)
            throw new IllegalArgumentException("source must not be null.");
        checkTimerRecordingSourceType(j);
        byte abyte0[] = new byte[timerrecordsource.getDataSize()];
        timerrecordsource.toByteArray(abyte0, 0);
        mService.startTimerRecording(i, j, abyte0);
_L1:
        return;
        timerrecordsource;
        Log.e("HdmiTvClient", "failed to start record: ", timerrecordsource);
          goto _L1
    }

    public void stopOneTouchRecord(int i)
    {
        mService.stopOneTouchRecord(i);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("HdmiTvClient", "failed to stop record: ", remoteexception);
          goto _L1
    }

    private static final String TAG = "HdmiTvClient";
    public static final int VENDOR_DATA_SIZE = 16;
}

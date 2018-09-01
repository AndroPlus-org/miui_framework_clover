// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.midi;

import android.bluetooth.BluetoothDevice;
import android.os.*;
import android.util.Log;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package android.media.midi:
//            MidiDeviceServer, IMidiManager, MidiDevice, MidiReceiver, 
//            MidiDeviceInfo, MidiDeviceStatus, IMidiDeviceServer

public final class MidiManager
{
    public static class DeviceCallback
    {

        public void onDeviceAdded(MidiDeviceInfo midideviceinfo)
        {
        }

        public void onDeviceRemoved(MidiDeviceInfo midideviceinfo)
        {
        }

        public void onDeviceStatusChanged(MidiDeviceStatus mididevicestatus)
        {
        }

        public DeviceCallback()
        {
        }
    }

    private class DeviceListener extends IMidiDeviceListener.Stub
    {

        static DeviceCallback _2D_get0(DeviceListener devicelistener)
        {
            return devicelistener.mCallback;
        }

        public void onDeviceAdded(MidiDeviceInfo midideviceinfo)
        {
            if(mHandler != null)
                mHandler.post(midideviceinfo. new Runnable() {

                    public void run()
                    {
                        DeviceListener._2D_get0(DeviceListener.this).onDeviceAdded(deviceF);
                    }

                    final DeviceListener this$1;
                    final MidiDeviceInfo val$deviceF;

            
            {
                this$1 = final_devicelistener;
                deviceF = MidiDeviceInfo.this;
                super();
            }
                }
);
            else
                mCallback.onDeviceAdded(midideviceinfo);
        }

        public void onDeviceRemoved(MidiDeviceInfo midideviceinfo)
        {
            if(mHandler != null)
                mHandler.post(midideviceinfo. new Runnable() {

                    public void run()
                    {
                        DeviceListener._2D_get0(DeviceListener.this).onDeviceRemoved(deviceF);
                    }

                    final DeviceListener this$1;
                    final MidiDeviceInfo val$deviceF;

            
            {
                this$1 = final_devicelistener;
                deviceF = MidiDeviceInfo.this;
                super();
            }
                }
);
            else
                mCallback.onDeviceRemoved(midideviceinfo);
        }

        public void onDeviceStatusChanged(MidiDeviceStatus mididevicestatus)
        {
            if(mHandler != null)
                mHandler.post(mididevicestatus. new Runnable() {

                    public void run()
                    {
                        DeviceListener._2D_get0(DeviceListener.this).onDeviceStatusChanged(statusF);
                    }

                    final DeviceListener this$1;
                    final MidiDeviceStatus val$statusF;

            
            {
                this$1 = final_devicelistener;
                statusF = MidiDeviceStatus.this;
                super();
            }
                }
);
            else
                mCallback.onDeviceStatusChanged(mididevicestatus);
        }

        private final DeviceCallback mCallback;
        private final Handler mHandler;
        final MidiManager this$0;

        public DeviceListener(DeviceCallback devicecallback, Handler handler)
        {
            this$0 = MidiManager.this;
            super();
            mCallback = devicecallback;
            mHandler = handler;
        }
    }

    public static interface OnDeviceOpenedListener
    {

        public abstract void onDeviceOpened(MidiDevice mididevice);
    }


    static IMidiManager _2D_get0(MidiManager midimanager)
    {
        return midimanager.mService;
    }

    static IBinder _2D_get1(MidiManager midimanager)
    {
        return midimanager.mToken;
    }

    static void _2D_wrap0(MidiManager midimanager, MidiDevice mididevice, OnDeviceOpenedListener ondeviceopenedlistener, Handler handler)
    {
        midimanager.sendOpenDeviceResponse(mididevice, ondeviceopenedlistener, handler);
    }

    public MidiManager(IMidiManager imidimanager)
    {
        mDeviceListeners = new ConcurrentHashMap();
        mService = imidimanager;
    }

    private void sendOpenDeviceResponse(final MidiDevice device, final OnDeviceOpenedListener listener, Handler handler)
    {
        if(handler != null)
            handler.post(new Runnable() {

                public void run()
                {
                    listener.onDeviceOpened(device);
                }

                final MidiManager this$0;
                final MidiDevice val$device;
                final OnDeviceOpenedListener val$listener;

            
            {
                this$0 = MidiManager.this;
                listener = ondeviceopenedlistener;
                device = mididevice;
                super();
            }
            }
);
        else
            listener.onDeviceOpened(device);
    }

    public MidiDeviceServer createDeviceServer(MidiReceiver amidireceiver[], int i, String as[], String as1[], Bundle bundle, int j, MidiDeviceServer.Callback callback)
    {
label0:
        {
            MidiDeviceServer midideviceserver;
            try
            {
                midideviceserver = JVM INSTR new #92  <Class MidiDeviceServer>;
                midideviceserver.MidiDeviceServer(mService, amidireceiver, i, callback);
                if(mService.registerDeviceServer(midideviceserver.getBinderInterface(), amidireceiver.length, i, as, as1, bundle, j) != null)
                    break label0;
                Log.e("MidiManager", "registerVirtualDevice failed");
            }
            // Misplaced declaration of an exception variable
            catch(MidiReceiver amidireceiver[])
            {
                throw amidireceiver.rethrowFromSystemServer();
            }
            return null;
        }
        return midideviceserver;
    }

    public MidiDeviceInfo[] getDevices()
    {
        MidiDeviceInfo amidideviceinfo[];
        try
        {
            amidideviceinfo = mService.getDevices();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return amidideviceinfo;
    }

    public void openBluetoothDevice(BluetoothDevice bluetoothdevice, final OnDeviceOpenedListener listenerF, final Handler handlerF)
    {
        listenerF = new IMidiDeviceOpenCallback.Stub() {

            public void onDeviceOpened(IMidiDeviceServer imidideviceserver, IBinder ibinder)
            {
                if(imidideviceserver == null) goto _L2; else goto _L1
_L1:
                MidiDevice mididevice;
                try
                {
                    MidiDeviceInfo midideviceinfo = imidideviceserver.getDeviceInfo();
                    mididevice = JVM INSTR new #39  <Class MidiDevice>;
                    mididevice.MidiDevice(midideviceinfo, imidideviceserver, MidiManager._2D_get0(MidiManager.this), MidiManager._2D_get1(MidiManager.this), ibinder);
                }
                // Misplaced declaration of an exception variable
                catch(IMidiDeviceServer imidideviceserver)
                {
                    Log.e("MidiManager", "remote exception in getDeviceInfo()");
                    imidideviceserver = null;
                    continue; /* Loop/switch isn't completed */
                }
                imidideviceserver = mididevice;
_L4:
                MidiManager._2D_wrap0(MidiManager.this, imidideviceserver, listenerF, handlerF);
                return;
_L2:
                imidideviceserver = null;
                if(true) goto _L4; else goto _L3
_L3:
            }

            final MidiManager this$0;
            final Handler val$handlerF;
            final OnDeviceOpenedListener val$listenerF;

            
            {
                this$0 = MidiManager.this;
                listenerF = ondeviceopenedlistener;
                handlerF = handler;
                super();
            }
        }
;
        try
        {
            mService.openBluetoothDevice(mToken, bluetoothdevice, listenerF);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(BluetoothDevice bluetoothdevice)
        {
            throw bluetoothdevice.rethrowFromSystemServer();
        }
    }

    public void openDevice(final MidiDeviceInfo deviceInfoF, final OnDeviceOpenedListener listenerF, final Handler handlerF)
    {
        listenerF = new IMidiDeviceOpenCallback.Stub() {

            public void onDeviceOpened(IMidiDeviceServer imidideviceserver, IBinder ibinder)
            {
                if(imidideviceserver != null)
                    imidideviceserver = new MidiDevice(deviceInfoF, imidideviceserver, MidiManager._2D_get0(MidiManager.this), MidiManager._2D_get1(MidiManager.this), ibinder);
                else
                    imidideviceserver = null;
                MidiManager._2D_wrap0(MidiManager.this, imidideviceserver, listenerF, handlerF);
            }

            final MidiManager this$0;
            final MidiDeviceInfo val$deviceInfoF;
            final Handler val$handlerF;
            final OnDeviceOpenedListener val$listenerF;

            
            {
                this$0 = MidiManager.this;
                deviceInfoF = midideviceinfo;
                listenerF = ondeviceopenedlistener;
                handlerF = handler;
                super();
            }
        }
;
        try
        {
            mService.openDevice(mToken, deviceInfoF, listenerF);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(final MidiDeviceInfo deviceInfoF)
        {
            throw deviceInfoF.rethrowFromSystemServer();
        }
    }

    public void registerDeviceCallback(DeviceCallback devicecallback, Handler handler)
    {
        handler = new DeviceListener(devicecallback, handler);
        try
        {
            mService.registerListener(mToken, handler);
        }
        // Misplaced declaration of an exception variable
        catch(DeviceCallback devicecallback)
        {
            throw devicecallback.rethrowFromSystemServer();
        }
        mDeviceListeners.put(devicecallback, handler);
    }

    public void unregisterDeviceCallback(DeviceCallback devicecallback)
    {
        devicecallback = (DeviceListener)mDeviceListeners.remove(devicecallback);
        if(devicecallback == null)
            break MISSING_BLOCK_LABEL_30;
        mService.unregisterListener(mToken, devicecallback);
        return;
        devicecallback;
        throw devicecallback.rethrowFromSystemServer();
    }

    public static final String BLUETOOTH_MIDI_SERVICE_CLASS = "com.android.bluetoothmidiservice.BluetoothMidiService";
    public static final String BLUETOOTH_MIDI_SERVICE_INTENT = "android.media.midi.BluetoothMidiService";
    public static final String BLUETOOTH_MIDI_SERVICE_PACKAGE = "com.android.bluetoothmidiservice";
    private static final String TAG = "MidiManager";
    private ConcurrentHashMap mDeviceListeners;
    private final IMidiManager mService;
    private final IBinder mToken = new Binder();
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.content.*;
import android.os.*;
import android.util.Log;
import java.util.*;

// Referenced classes of package android.bluetooth:
//            BluetoothProfile, BluetoothAdapter, IBluetoothManager, IBluetoothInputHost, 
//            BluetoothHidDeviceAppConfiguration, IBluetoothStateChangeCallback, BluetoothDevice, BluetoothHidDeviceAppSdpSettings, 
//            BluetoothHidDeviceAppQosSettings, BluetoothHidDeviceCallback

public final class BluetoothInputHost
    implements BluetoothProfile
{
    private static class BluetoothHidDeviceCallbackWrapper extends IBluetoothHidDeviceCallback.Stub
    {

        public void onAppStatusChanged(BluetoothDevice bluetoothdevice, BluetoothHidDeviceAppConfiguration bluetoothhiddeviceappconfiguration, boolean flag)
        {
            mCallback.onAppStatusChanged(bluetoothdevice, bluetoothhiddeviceappconfiguration, flag);
        }

        public void onConnectionStateChanged(BluetoothDevice bluetoothdevice, int i)
        {
            mCallback.onConnectionStateChanged(bluetoothdevice, i);
        }

        public void onGetReport(BluetoothDevice bluetoothdevice, byte byte0, byte byte1, int i)
        {
            mCallback.onGetReport(bluetoothdevice, byte0, byte1, i);
        }

        public void onIntrData(BluetoothDevice bluetoothdevice, byte byte0, byte abyte0[])
        {
            mCallback.onIntrData(bluetoothdevice, byte0, abyte0);
        }

        public void onSetProtocol(BluetoothDevice bluetoothdevice, byte byte0)
        {
            mCallback.onSetProtocol(bluetoothdevice, byte0);
        }

        public void onSetReport(BluetoothDevice bluetoothdevice, byte byte0, byte byte1, byte abyte0[])
        {
            mCallback.onSetReport(bluetoothdevice, byte0, byte1, abyte0);
        }

        public void onVirtualCableUnplug(BluetoothDevice bluetoothdevice)
        {
            mCallback.onVirtualCableUnplug(bluetoothdevice);
        }

        private BluetoothHidDeviceCallback mCallback;

        public BluetoothHidDeviceCallbackWrapper(BluetoothHidDeviceCallback bluetoothhiddevicecallback)
        {
            mCallback = bluetoothhiddevicecallback;
        }
    }


    static String _2D_get0()
    {
        return TAG;
    }

    static ServiceConnection _2D_get1(BluetoothInputHost bluetoothinputhost)
    {
        return bluetoothinputhost.mConnection;
    }

    static Context _2D_get2(BluetoothInputHost bluetoothinputhost)
    {
        return bluetoothinputhost.mContext;
    }

    static IBluetoothInputHost _2D_get3(BluetoothInputHost bluetoothinputhost)
    {
        return bluetoothinputhost.mService;
    }

    static BluetoothProfile.ServiceListener _2D_get4(BluetoothInputHost bluetoothinputhost)
    {
        return bluetoothinputhost.mServiceListener;
    }

    static IBluetoothInputHost _2D_set0(BluetoothInputHost bluetoothinputhost, IBluetoothInputHost ibluetoothinputhost)
    {
        bluetoothinputhost.mService = ibluetoothinputhost;
        return ibluetoothinputhost;
    }

    BluetoothInputHost(Context context, BluetoothProfile.ServiceListener servicelistener)
    {
        Log.v(TAG, "BluetoothInputHost");
        mContext = context;
        mServiceListener = servicelistener;
        mAdapter = BluetoothAdapter.getDefaultAdapter();
        context = mAdapter.getBluetoothManager();
        if(context != null)
            try
            {
                context.registerStateChangeCallback(mBluetoothStateChangeCallback);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                context.printStackTrace();
            }
        doBind();
    }

    void close()
    {
        Object obj;
        Log.v(TAG, "close()");
        obj = mAdapter.getBluetoothManager();
        if(obj != null)
            try
            {
                ((IBluetoothManager) (obj)).unregisterStateChangeCallback(mBluetoothStateChangeCallback);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                ((RemoteException) (obj)).printStackTrace();
            }
        obj = mConnection;
        obj;
        JVM INSTR monitorenter ;
        if(mService == null)
            break MISSING_BLOCK_LABEL_61;
        mService = null;
        mContext.unbindService(mConnection);
_L1:
        obj;
        JVM INSTR monitorexit ;
        mServiceListener = null;
        return;
        Object obj1;
        obj1;
        Log.e(TAG, "close: could not unbind HID Dev service: ", ((Throwable) (obj1)));
          goto _L1
        obj1;
        throw obj1;
    }

    public boolean connect(BluetoothDevice bluetoothdevice)
    {
        Log.v(TAG, (new StringBuilder()).append("connect(): device=").append(bluetoothdevice).toString());
        boolean flag = false;
        IBluetoothInputHost ibluetoothinputhost = mService;
        boolean flag1;
        if(ibluetoothinputhost != null)
        {
            try
            {
                flag1 = ibluetoothinputhost.connect(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e(TAG, bluetoothdevice.toString());
                flag1 = flag;
            }
        } else
        {
            Log.w(TAG, "Proxy not attached to service");
            flag1 = flag;
        }
        return flag1;
    }

    public boolean disconnect(BluetoothDevice bluetoothdevice)
    {
        boolean flag;
        IBluetoothInputHost ibluetoothinputhost;
        Log.v(TAG, (new StringBuilder()).append("disconnect(): device=").append(bluetoothdevice).toString());
        flag = false;
        ibluetoothinputhost = mService;
        if(ibluetoothinputhost == null) goto _L2; else goto _L1
_L1:
        boolean flag1;
        try
        {
            flag1 = ibluetoothinputhost.disconnect(bluetoothdevice);
        }
        // Misplaced declaration of an exception variable
        catch(BluetoothDevice bluetoothdevice)
        {
            Log.e(TAG, bluetoothdevice.toString());
            continue; /* Loop/switch isn't completed */
        }
        flag = flag1;
_L4:
        return flag;
_L2:
        Log.w(TAG, "Proxy not attached to service");
        if(true) goto _L4; else goto _L3
_L3:
    }

    boolean doBind()
    {
        Intent intent = new Intent(android/bluetooth/IBluetoothInputHost.getName());
        ComponentName componentname = intent.resolveSystemService(mContext.getPackageManager(), 0);
        intent.setComponent(componentname);
        if(componentname == null || mContext.bindServiceAsUser(intent, mConnection, 0, Process.myUserHandle()) ^ true)
        {
            Log.e(TAG, (new StringBuilder()).append("Could not bind to Bluetooth HID Device Service with ").append(intent).toString());
            return false;
        } else
        {
            Log.d(TAG, "Bound to HID Device Service");
            return true;
        }
    }

    public List getConnectedDevices()
    {
        Object obj;
        Log.v(TAG, "getConnectedDevices()");
        obj = mService;
        if(obj == null) goto _L2; else goto _L1
_L1:
        obj = ((IBluetoothInputHost) (obj)).getConnectedDevices();
        return ((List) (obj));
        RemoteException remoteexception;
        remoteexception;
        Log.e(TAG, remoteexception.toString());
_L4:
        return new ArrayList();
_L2:
        Log.w(TAG, "Proxy not attached to service");
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int getConnectionState(BluetoothDevice bluetoothdevice)
    {
        IBluetoothInputHost ibluetoothinputhost;
        Log.v(TAG, (new StringBuilder()).append("getConnectionState(): device=").append(bluetoothdevice).toString());
        ibluetoothinputhost = mService;
        if(ibluetoothinputhost == null) goto _L2; else goto _L1
_L1:
        int i = ibluetoothinputhost.getConnectionState(bluetoothdevice);
        return i;
        bluetoothdevice;
        Log.e(TAG, bluetoothdevice.toString());
_L4:
        return 0;
_L2:
        Log.w(TAG, "Proxy not attached to service");
        if(true) goto _L4; else goto _L3
_L3:
    }

    public List getDevicesMatchingConnectionStates(int ai[])
    {
        IBluetoothInputHost ibluetoothinputhost;
        Log.v(TAG, (new StringBuilder()).append("getDevicesMatchingConnectionStates(): states=").append(Arrays.toString(ai)).toString());
        ibluetoothinputhost = mService;
        if(ibluetoothinputhost == null) goto _L2; else goto _L1
_L1:
        ai = ibluetoothinputhost.getDevicesMatchingConnectionStates(ai);
        return ai;
        ai;
        Log.e(TAG, ai.toString());
_L4:
        return new ArrayList();
_L2:
        Log.w(TAG, "Proxy not attached to service");
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean registerApp(BluetoothHidDeviceAppSdpSettings bluetoothhiddeviceappsdpsettings, BluetoothHidDeviceAppQosSettings bluetoothhiddeviceappqossettings, BluetoothHidDeviceAppQosSettings bluetoothhiddeviceappqossettings1, BluetoothHidDeviceCallback bluetoothhiddevicecallback)
    {
        Log.v(TAG, (new StringBuilder()).append("registerApp(): sdp=").append(bluetoothhiddeviceappsdpsettings).append(" inQos=").append(bluetoothhiddeviceappqossettings).append(" outQos=").append(bluetoothhiddeviceappqossettings1).append(" callback=").append(bluetoothhiddevicecallback).toString());
        boolean flag = false;
        if(bluetoothhiddeviceappsdpsettings == null || bluetoothhiddevicecallback == null)
            return false;
        IBluetoothInputHost ibluetoothinputhost = mService;
        boolean flag1;
        if(ibluetoothinputhost != null)
        {
            try
            {
                BluetoothHidDeviceAppConfiguration bluetoothhiddeviceappconfiguration = JVM INSTR new #271 <Class BluetoothHidDeviceAppConfiguration>;
                bluetoothhiddeviceappconfiguration.BluetoothHidDeviceAppConfiguration();
                BluetoothHidDeviceCallbackWrapper bluetoothhiddevicecallbackwrapper = JVM INSTR new #12  <Class BluetoothInputHost$BluetoothHidDeviceCallbackWrapper>;
                bluetoothhiddevicecallbackwrapper.BluetoothHidDeviceCallbackWrapper(bluetoothhiddevicecallback);
                flag1 = ibluetoothinputhost.registerApp(bluetoothhiddeviceappconfiguration, bluetoothhiddeviceappsdpsettings, bluetoothhiddeviceappqossettings, bluetoothhiddeviceappqossettings1, bluetoothhiddevicecallbackwrapper);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothHidDeviceAppSdpSettings bluetoothhiddeviceappsdpsettings)
            {
                Log.e(TAG, bluetoothhiddeviceappsdpsettings.toString());
                flag1 = flag;
            }
        } else
        {
            Log.w(TAG, "Proxy not attached to service");
            flag1 = flag;
        }
        return flag1;
    }

    public boolean replyReport(BluetoothDevice bluetoothdevice, byte byte0, byte byte1, byte abyte0[])
    {
        Log.v(TAG, (new StringBuilder()).append("replyReport(): device=").append(bluetoothdevice).append(" type=").append(byte0).append(" id=").append(byte1).toString());
        boolean flag = false;
        IBluetoothInputHost ibluetoothinputhost = mService;
        boolean flag1;
        if(ibluetoothinputhost != null)
        {
            try
            {
                flag1 = ibluetoothinputhost.replyReport(bluetoothdevice, byte0, byte1, abyte0);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e(TAG, bluetoothdevice.toString());
                flag1 = flag;
            }
        } else
        {
            Log.w(TAG, "Proxy not attached to service");
            flag1 = flag;
        }
        return flag1;
    }

    public boolean reportError(BluetoothDevice bluetoothdevice, byte byte0)
    {
        Log.v(TAG, (new StringBuilder()).append("reportError(): device=").append(bluetoothdevice).append(" error=").append(byte0).toString());
        boolean flag = false;
        IBluetoothInputHost ibluetoothinputhost = mService;
        boolean flag1;
        if(ibluetoothinputhost != null)
        {
            try
            {
                flag1 = ibluetoothinputhost.reportError(bluetoothdevice, byte0);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e(TAG, bluetoothdevice.toString());
                flag1 = flag;
            }
        } else
        {
            Log.w(TAG, "Proxy not attached to service");
            flag1 = flag;
        }
        return flag1;
    }

    public boolean sendReport(BluetoothDevice bluetoothdevice, int i, byte abyte0[])
    {
        boolean flag;
        IBluetoothInputHost ibluetoothinputhost;
        flag = false;
        ibluetoothinputhost = mService;
        if(ibluetoothinputhost == null) goto _L2; else goto _L1
_L1:
        boolean flag1;
        try
        {
            flag1 = ibluetoothinputhost.sendReport(bluetoothdevice, i, abyte0);
        }
        // Misplaced declaration of an exception variable
        catch(BluetoothDevice bluetoothdevice)
        {
            Log.e(TAG, bluetoothdevice.toString());
            continue; /* Loop/switch isn't completed */
        }
        flag = flag1;
_L4:
        return flag;
_L2:
        Log.w(TAG, "Proxy not attached to service");
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean unplug(BluetoothDevice bluetoothdevice)
    {
        boolean flag;
        IBluetoothInputHost ibluetoothinputhost;
        Log.v(TAG, (new StringBuilder()).append("unplug(): device=").append(bluetoothdevice).toString());
        flag = false;
        ibluetoothinputhost = mService;
        if(ibluetoothinputhost == null) goto _L2; else goto _L1
_L1:
        boolean flag1;
        try
        {
            flag1 = ibluetoothinputhost.unplug(bluetoothdevice);
        }
        // Misplaced declaration of an exception variable
        catch(BluetoothDevice bluetoothdevice)
        {
            Log.e(TAG, bluetoothdevice.toString());
            continue; /* Loop/switch isn't completed */
        }
        flag = flag1;
_L4:
        return flag;
_L2:
        Log.w(TAG, "Proxy not attached to service");
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean unregisterApp(BluetoothHidDeviceAppConfiguration bluetoothhiddeviceappconfiguration)
    {
        Log.v(TAG, "unregisterApp()");
        boolean flag = false;
        IBluetoothInputHost ibluetoothinputhost = mService;
        boolean flag1;
        if(ibluetoothinputhost != null)
        {
            try
            {
                flag1 = ibluetoothinputhost.unregisterApp(bluetoothhiddeviceappconfiguration);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothHidDeviceAppConfiguration bluetoothhiddeviceappconfiguration)
            {
                Log.e(TAG, bluetoothhiddeviceappconfiguration.toString());
                flag1 = flag;
            }
        } else
        {
            Log.w(TAG, "Proxy not attached to service");
            flag1 = flag;
        }
        return flag1;
    }

    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.inputhost.profile.action.CONNECTION_STATE_CHANGED";
    public static final byte ERROR_RSP_INVALID_PARAM = 4;
    public static final byte ERROR_RSP_INVALID_RPT_ID = 2;
    public static final byte ERROR_RSP_NOT_READY = 1;
    public static final byte ERROR_RSP_SUCCESS = 0;
    public static final byte ERROR_RSP_UNKNOWN = 14;
    public static final byte ERROR_RSP_UNSUPPORTED_REQ = 3;
    public static final byte PROTOCOL_BOOT_MODE = 0;
    public static final byte PROTOCOL_REPORT_MODE = 1;
    public static final byte REPORT_TYPE_FEATURE = 3;
    public static final byte REPORT_TYPE_INPUT = 1;
    public static final byte REPORT_TYPE_OUTPUT = 2;
    public static final byte SUBCLASS1_COMBO = -64;
    public static final byte SUBCLASS1_KEYBOARD = 64;
    public static final byte SUBCLASS1_MOUSE = -128;
    public static final byte SUBCLASS1_NONE = 0;
    public static final byte SUBCLASS2_CARD_READER = 6;
    public static final byte SUBCLASS2_DIGITIZER_TABLED = 5;
    public static final byte SUBCLASS2_GAMEPAD = 2;
    public static final byte SUBCLASS2_JOYSTICK = 1;
    public static final byte SUBCLASS2_REMOTE_CONTROL = 3;
    public static final byte SUBCLASS2_SENSING_DEVICE = 4;
    public static final byte SUBCLASS2_UNCATEGORIZED = 0;
    private static final String TAG = android/bluetooth/BluetoothInputHost.getSimpleName();
    private BluetoothAdapter mAdapter;
    private final IBluetoothStateChangeCallback mBluetoothStateChangeCallback = new IBluetoothStateChangeCallback.Stub() {

        public void onBluetoothStateChange(boolean flag)
        {
            Log.d(BluetoothInputHost._2D_get0(), (new StringBuilder()).append("onBluetoothStateChange: up=").append(flag).toString());
            ServiceConnection serviceconnection = BluetoothInputHost._2D_get1(BluetoothInputHost.this);
            serviceconnection;
            JVM INSTR monitorenter ;
            if(flag) goto _L2; else goto _L1
_L1:
            Log.d(BluetoothInputHost._2D_get0(), "Unbinding service...");
            if(BluetoothInputHost._2D_get3(BluetoothInputHost.this) == null)
                break MISSING_BLOCK_LABEL_85;
            BluetoothInputHost._2D_set0(BluetoothInputHost.this, null);
            BluetoothInputHost._2D_get2(BluetoothInputHost.this).unbindService(BluetoothInputHost._2D_get1(BluetoothInputHost.this));
_L3:
            serviceconnection;
            JVM INSTR monitorexit ;
            return;
            Object obj;
            obj;
            Log.e(BluetoothInputHost._2D_get0(), "onBluetoothStateChange: could not unbind service:", ((Throwable) (obj)));
              goto _L3
            obj;
            throw obj;
_L2:
            if(BluetoothInputHost._2D_get3(BluetoothInputHost.this) != null) goto _L3; else goto _L4
_L4:
            Log.d(BluetoothInputHost._2D_get0(), "Binding HID Device service...");
            doBind();
              goto _L3
            obj;
            Log.e(BluetoothInputHost._2D_get0(), "onBluetoothStateChange: could not bind to HID Dev service: ", ((Throwable) (obj)));
              goto _L3
            obj;
            Log.e(BluetoothInputHost._2D_get0(), "onBluetoothStateChange: could not bind to HID Dev service: ", ((Throwable) (obj)));
              goto _L3
        }

        final BluetoothInputHost this$0;

            
            {
                this$0 = BluetoothInputHost.this;
                super();
            }
    }
;
    private final ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            Log.d(BluetoothInputHost._2D_get0(), "onServiceConnected()");
            BluetoothInputHost._2D_set0(BluetoothInputHost.this, IBluetoothInputHost.Stub.asInterface(ibinder));
            if(BluetoothInputHost._2D_get4(BluetoothInputHost.this) != null)
                BluetoothInputHost._2D_get4(BluetoothInputHost.this).onServiceConnected(19, BluetoothInputHost.this);
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            Log.d(BluetoothInputHost._2D_get0(), "onServiceDisconnected()");
            BluetoothInputHost._2D_set0(BluetoothInputHost.this, null);
            if(BluetoothInputHost._2D_get4(BluetoothInputHost.this) != null)
                BluetoothInputHost._2D_get4(BluetoothInputHost.this).onServiceDisconnected(19);
        }

        final BluetoothInputHost this$0;

            
            {
                this$0 = BluetoothInputHost.this;
                super();
            }
    }
;
    private Context mContext;
    private volatile IBluetoothInputHost mService;
    private BluetoothProfile.ServiceListener mServiceListener;

}

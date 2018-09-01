// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.content.*;
import android.os.*;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.bluetooth:
//            BluetoothProfile, BluetoothAdapter, IBluetoothManager, BluetoothDevice, 
//            IBluetoothInputDevice, IBluetoothStateChangeCallback

public final class BluetoothInputDevice
    implements BluetoothProfile
{

    static ServiceConnection _2D_get0(BluetoothInputDevice bluetoothinputdevice)
    {
        return bluetoothinputdevice.mConnection;
    }

    static Context _2D_get1(BluetoothInputDevice bluetoothinputdevice)
    {
        return bluetoothinputdevice.mContext;
    }

    static IBluetoothInputDevice _2D_get2(BluetoothInputDevice bluetoothinputdevice)
    {
        return bluetoothinputdevice.mService;
    }

    static BluetoothProfile.ServiceListener _2D_get3(BluetoothInputDevice bluetoothinputdevice)
    {
        return bluetoothinputdevice.mServiceListener;
    }

    static IBluetoothInputDevice _2D_set0(BluetoothInputDevice bluetoothinputdevice, IBluetoothInputDevice ibluetoothinputdevice)
    {
        bluetoothinputdevice.mService = ibluetoothinputdevice;
        return ibluetoothinputdevice;
    }

    BluetoothInputDevice(Context context, BluetoothProfile.ServiceListener servicelistener)
    {
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
                Log.e("BluetoothInputDevice", "", context);
            }
        doBind();
    }

    private boolean isEnabled()
    {
        boolean flag;
        if(mAdapter.getState() == 12)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isValidDevice(BluetoothDevice bluetoothdevice)
    {
        boolean flag;
        if(bluetoothdevice != null)
            flag = BluetoothAdapter.checkBluetoothAddress(bluetoothdevice.getAddress());
        else
            flag = false;
        return flag;
    }

    private static void log(String s)
    {
        Log.d("BluetoothInputDevice", s);
    }

    void close()
    {
        Object obj;
        obj = mAdapter.getBluetoothManager();
        IBluetoothInputDevice ibluetoothinputdevice;
        if(obj != null)
            try
            {
                ((IBluetoothManager) (obj)).unregisterStateChangeCallback(mBluetoothStateChangeCallback);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("BluetoothInputDevice", "", ((Throwable) (obj)));
            }
        obj = mConnection;
        obj;
        JVM INSTR monitorenter ;
        ibluetoothinputdevice = mService;
        if(ibluetoothinputdevice == null)
            break MISSING_BLOCK_LABEL_54;
        mService = null;
        mContext.unbindService(mConnection);
_L1:
        obj;
        JVM INSTR monitorexit ;
        mServiceListener = null;
        return;
        Object obj1;
        obj1;
        Log.e("BluetoothInputDevice", "", ((Throwable) (obj1)));
          goto _L1
        obj1;
        throw obj1;
    }

    public boolean connect(BluetoothDevice bluetoothdevice)
    {
        log((new StringBuilder()).append("connect(").append(bluetoothdevice).append(")").toString());
        IBluetoothInputDevice ibluetoothinputdevice = mService;
        if(ibluetoothinputdevice != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetoothinputdevice.connect(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothInputDevice", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return false;
            }
            return flag;
        }
        if(ibluetoothinputdevice == null)
            Log.w("BluetoothInputDevice", "Proxy not attached to service");
        return false;
    }

    public boolean disconnect(BluetoothDevice bluetoothdevice)
    {
        log((new StringBuilder()).append("disconnect(").append(bluetoothdevice).append(")").toString());
        IBluetoothInputDevice ibluetoothinputdevice = mService;
        if(ibluetoothinputdevice != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetoothinputdevice.disconnect(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothInputDevice", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return false;
            }
            return flag;
        }
        if(ibluetoothinputdevice == null)
            Log.w("BluetoothInputDevice", "Proxy not attached to service");
        return false;
    }

    boolean doBind()
    {
        Intent intent = new Intent(android/bluetooth/IBluetoothInputDevice.getName());
        ComponentName componentname = intent.resolveSystemService(mContext.getPackageManager(), 0);
        intent.setComponent(componentname);
        if(componentname == null || mContext.bindServiceAsUser(intent, mConnection, 0, Process.myUserHandle()) ^ true)
        {
            Log.e("BluetoothInputDevice", (new StringBuilder()).append("Could not bind to Bluetooth HID Service with ").append(intent).toString());
            return false;
        } else
        {
            return true;
        }
    }

    public List getConnectedDevices()
    {
        Object obj = mService;
        if(obj != null && isEnabled())
        {
            try
            {
                obj = ((IBluetoothInputDevice) (obj)).getConnectedDevices();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("BluetoothInputDevice", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return new ArrayList();
            }
            return ((List) (obj));
        }
        if(obj == null)
            Log.w("BluetoothInputDevice", "Proxy not attached to service");
        return new ArrayList();
    }

    public int getConnectionState(BluetoothDevice bluetoothdevice)
    {
        IBluetoothInputDevice ibluetoothinputdevice = mService;
        if(ibluetoothinputdevice != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            int i;
            try
            {
                i = ibluetoothinputdevice.getConnectionState(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothInputDevice", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return 0;
            }
            return i;
        }
        if(ibluetoothinputdevice == null)
            Log.w("BluetoothInputDevice", "Proxy not attached to service");
        return 0;
    }

    public List getDevicesMatchingConnectionStates(int ai[])
    {
        IBluetoothInputDevice ibluetoothinputdevice = mService;
        if(ibluetoothinputdevice != null && isEnabled())
        {
            try
            {
                ai = ibluetoothinputdevice.getDevicesMatchingConnectionStates(ai);
            }
            // Misplaced declaration of an exception variable
            catch(int ai[])
            {
                Log.e("BluetoothInputDevice", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return new ArrayList();
            }
            return ai;
        }
        if(ibluetoothinputdevice == null)
            Log.w("BluetoothInputDevice", "Proxy not attached to service");
        return new ArrayList();
    }

    public boolean getIdleTime(BluetoothDevice bluetoothdevice)
    {
        log((new StringBuilder()).append("getIdletime(").append(bluetoothdevice).append(")").toString());
        IBluetoothInputDevice ibluetoothinputdevice = mService;
        if(ibluetoothinputdevice != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetoothinputdevice.getIdleTime(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothInputDevice", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return false;
            }
            return flag;
        }
        if(ibluetoothinputdevice == null)
            Log.w("BluetoothInputDevice", "Proxy not attached to service");
        return false;
    }

    public int getPriority(BluetoothDevice bluetoothdevice)
    {
        IBluetoothInputDevice ibluetoothinputdevice = mService;
        if(ibluetoothinputdevice != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            int i;
            try
            {
                i = ibluetoothinputdevice.getPriority(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothInputDevice", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return 0;
            }
            return i;
        }
        if(ibluetoothinputdevice == null)
            Log.w("BluetoothInputDevice", "Proxy not attached to service");
        return 0;
    }

    public boolean getProtocolMode(BluetoothDevice bluetoothdevice)
    {
        IBluetoothInputDevice ibluetoothinputdevice = mService;
        if(ibluetoothinputdevice != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetoothinputdevice.getProtocolMode(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothInputDevice", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return false;
            }
            return flag;
        }
        if(ibluetoothinputdevice == null)
            Log.w("BluetoothInputDevice", "Proxy not attached to service");
        return false;
    }

    public boolean getReport(BluetoothDevice bluetoothdevice, byte byte0, byte byte1, int i)
    {
        IBluetoothInputDevice ibluetoothinputdevice = mService;
        if(ibluetoothinputdevice != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetoothinputdevice.getReport(bluetoothdevice, byte0, byte1, i);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothInputDevice", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return false;
            }
            return flag;
        }
        if(ibluetoothinputdevice == null)
            Log.w("BluetoothInputDevice", "Proxy not attached to service");
        return false;
    }

    public boolean sendData(BluetoothDevice bluetoothdevice, String s)
    {
        log((new StringBuilder()).append("sendData(").append(bluetoothdevice).append("), report=").append(s).toString());
        IBluetoothInputDevice ibluetoothinputdevice = mService;
        if(ibluetoothinputdevice != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetoothinputdevice.sendData(bluetoothdevice, s);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothInputDevice", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return false;
            }
            return flag;
        }
        if(ibluetoothinputdevice == null)
            Log.w("BluetoothInputDevice", "Proxy not attached to service");
        return false;
    }

    public boolean setIdleTime(BluetoothDevice bluetoothdevice, byte byte0)
    {
        log((new StringBuilder()).append("setIdletime(").append(bluetoothdevice).append("), idleTime=").append(byte0).toString());
        IBluetoothInputDevice ibluetoothinputdevice = mService;
        if(ibluetoothinputdevice != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetoothinputdevice.setIdleTime(bluetoothdevice, byte0);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothInputDevice", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return false;
            }
            return flag;
        }
        if(ibluetoothinputdevice == null)
            Log.w("BluetoothInputDevice", "Proxy not attached to service");
        return false;
    }

    public boolean setPriority(BluetoothDevice bluetoothdevice, int i)
    {
        log((new StringBuilder()).append("setPriority(").append(bluetoothdevice).append(", ").append(i).append(")").toString());
        IBluetoothInputDevice ibluetoothinputdevice = mService;
        if(ibluetoothinputdevice != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            if(i != 0 && i != 100)
                return false;
            boolean flag;
            try
            {
                flag = ibluetoothinputdevice.setPriority(bluetoothdevice, i);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothInputDevice", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return false;
            }
            return flag;
        }
        if(ibluetoothinputdevice == null)
            Log.w("BluetoothInputDevice", "Proxy not attached to service");
        return false;
    }

    public boolean setProtocolMode(BluetoothDevice bluetoothdevice, int i)
    {
        log((new StringBuilder()).append("setProtocolMode(").append(bluetoothdevice).append(")").toString());
        IBluetoothInputDevice ibluetoothinputdevice = mService;
        if(ibluetoothinputdevice != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetoothinputdevice.setProtocolMode(bluetoothdevice, i);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothInputDevice", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return false;
            }
            return flag;
        }
        if(ibluetoothinputdevice == null)
            Log.w("BluetoothInputDevice", "Proxy not attached to service");
        return false;
    }

    public boolean setReport(BluetoothDevice bluetoothdevice, byte byte0, String s)
    {
        IBluetoothInputDevice ibluetoothinputdevice = mService;
        if(ibluetoothinputdevice != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetoothinputdevice.setReport(bluetoothdevice, byte0, s);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothInputDevice", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return false;
            }
            return flag;
        }
        if(ibluetoothinputdevice == null)
            Log.w("BluetoothInputDevice", "Proxy not attached to service");
        return false;
    }

    public boolean virtualUnplug(BluetoothDevice bluetoothdevice)
    {
        log((new StringBuilder()).append("virtualUnplug(").append(bluetoothdevice).append(")").toString());
        IBluetoothInputDevice ibluetoothinputdevice = mService;
        if(ibluetoothinputdevice != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetoothinputdevice.virtualUnplug(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothInputDevice", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return false;
            }
            return flag;
        }
        if(ibluetoothinputdevice == null)
            Log.w("BluetoothInputDevice", "Proxy not attached to service");
        return false;
    }

    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED";
    public static final String ACTION_HANDSHAKE = "android.bluetooth.input.profile.action.HANDSHAKE";
    public static final String ACTION_IDLE_TIME_CHANGED = "android.bluetooth.input.profile.action.IDLE_TIME_CHANGED";
    public static final String ACTION_PROTOCOL_MODE_CHANGED = "android.bluetooth.input.profile.action.PROTOCOL_MODE_CHANGED";
    public static final String ACTION_REPORT = "android.bluetooth.input.profile.action.REPORT";
    public static final String ACTION_VIRTUAL_UNPLUG_STATUS = "android.bluetooth.input.profile.action.VIRTUAL_UNPLUG_STATUS";
    private static final boolean DBG = true;
    public static final String EXTRA_IDLE_TIME = "android.bluetooth.BluetoothInputDevice.extra.IDLE_TIME";
    public static final String EXTRA_PROTOCOL_MODE = "android.bluetooth.BluetoothInputDevice.extra.PROTOCOL_MODE";
    public static final String EXTRA_REPORT = "android.bluetooth.BluetoothInputDevice.extra.REPORT";
    public static final String EXTRA_REPORT_BUFFER_SIZE = "android.bluetooth.BluetoothInputDevice.extra.REPORT_BUFFER_SIZE";
    public static final String EXTRA_REPORT_ID = "android.bluetooth.BluetoothInputDevice.extra.REPORT_ID";
    public static final String EXTRA_REPORT_TYPE = "android.bluetooth.BluetoothInputDevice.extra.REPORT_TYPE";
    public static final String EXTRA_STATUS = "android.bluetooth.BluetoothInputDevice.extra.STATUS";
    public static final String EXTRA_VIRTUAL_UNPLUG_STATUS = "android.bluetooth.BluetoothInputDevice.extra.VIRTUAL_UNPLUG_STATUS";
    public static final int INPUT_CONNECT_FAILED_ALREADY_CONNECTED = 5001;
    public static final int INPUT_CONNECT_FAILED_ATTEMPT_FAILED = 5002;
    public static final int INPUT_DISCONNECT_FAILED_NOT_CONNECTED = 5000;
    public static final int INPUT_OPERATION_GENERIC_FAILURE = 5003;
    public static final int INPUT_OPERATION_SUCCESS = 5004;
    public static final int PROTOCOL_BOOT_MODE = 1;
    public static final int PROTOCOL_REPORT_MODE = 0;
    public static final int PROTOCOL_UNSUPPORTED_MODE = 255;
    public static final byte REPORT_TYPE_FEATURE = 3;
    public static final byte REPORT_TYPE_INPUT = 1;
    public static final byte REPORT_TYPE_OUTPUT = 2;
    private static final String TAG = "BluetoothInputDevice";
    private static final boolean VDBG = false;
    public static final int VIRTUAL_UNPLUG_STATUS_FAIL = 1;
    public static final int VIRTUAL_UNPLUG_STATUS_SUCCESS = 0;
    private BluetoothAdapter mAdapter;
    private final IBluetoothStateChangeCallback mBluetoothStateChangeCallback = new IBluetoothStateChangeCallback.Stub() {

        public void onBluetoothStateChange(boolean flag)
        {
            Log.d("BluetoothInputDevice", (new StringBuilder()).append("onBluetoothStateChange: up=").append(flag).toString());
            if(flag) goto _L2; else goto _L1
_L1:
            ServiceConnection serviceconnection = BluetoothInputDevice._2D_get0(BluetoothInputDevice.this);
            serviceconnection;
            JVM INSTR monitorenter ;
            BluetoothInputDevice._2D_set0(BluetoothInputDevice.this, null);
            BluetoothInputDevice._2D_get1(BluetoothInputDevice.this).unbindService(BluetoothInputDevice._2D_get0(BluetoothInputDevice.this));
_L3:
            serviceconnection;
            JVM INSTR monitorexit ;
            return;
            Object obj1;
            obj1;
            Log.e("BluetoothInputDevice", "", ((Throwable) (obj1)));
              goto _L3
            obj1;
            throw obj1;
_L2:
            ServiceConnection serviceconnection1 = BluetoothInputDevice._2D_get0(BluetoothInputDevice.this);
            serviceconnection1;
            JVM INSTR monitorenter ;
            serviceconnection = serviceconnection1;
            if(BluetoothInputDevice._2D_get2(BluetoothInputDevice.this) != null) goto _L3; else goto _L4
_L4:
            doBind();
            serviceconnection = serviceconnection1;
              goto _L3
            Object obj;
            obj;
            Log.e("BluetoothInputDevice", "", ((Throwable) (obj)));
            obj = serviceconnection1;
              goto _L3
            obj;
            throw obj;
        }

        final BluetoothInputDevice this$0;

            
            {
                this$0 = BluetoothInputDevice.this;
                super();
            }
    }
;
    private final ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            Log.d("BluetoothInputDevice", "Proxy object connected");
            BluetoothInputDevice._2D_set0(BluetoothInputDevice.this, IBluetoothInputDevice.Stub.asInterface(Binder.allowBlocking(ibinder)));
            if(BluetoothInputDevice._2D_get3(BluetoothInputDevice.this) != null)
                BluetoothInputDevice._2D_get3(BluetoothInputDevice.this).onServiceConnected(4, BluetoothInputDevice.this);
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            Log.d("BluetoothInputDevice", "Proxy object disconnected");
            BluetoothInputDevice._2D_set0(BluetoothInputDevice.this, null);
            if(BluetoothInputDevice._2D_get3(BluetoothInputDevice.this) != null)
                BluetoothInputDevice._2D_get3(BluetoothInputDevice.this).onServiceDisconnected(4);
        }

        final BluetoothInputDevice this$0;

            
            {
                this$0 = BluetoothInputDevice.this;
                super();
            }
    }
;
    private Context mContext;
    private volatile IBluetoothInputDevice mService;
    private BluetoothProfile.ServiceListener mServiceListener;
}

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
//            IBluetoothHealth, BluetoothHealthAppConfiguration, IBluetoothStateChangeCallback, BluetoothHealthCallback

public final class BluetoothHealth
    implements BluetoothProfile
{
    private static class BluetoothHealthCallbackWrapper extends IBluetoothHealthCallback.Stub
    {

        public void onHealthAppConfigurationStatusChange(BluetoothHealthAppConfiguration bluetoothhealthappconfiguration, int i)
        {
            mCallback.onHealthAppConfigurationStatusChange(bluetoothhealthappconfiguration, i);
        }

        public void onHealthChannelStateChange(BluetoothHealthAppConfiguration bluetoothhealthappconfiguration, BluetoothDevice bluetoothdevice, int i, int j, ParcelFileDescriptor parcelfiledescriptor, int k)
        {
            mCallback.onHealthChannelStateChange(bluetoothhealthappconfiguration, bluetoothdevice, i, j, parcelfiledescriptor, k);
        }

        private BluetoothHealthCallback mCallback;

        public BluetoothHealthCallbackWrapper(BluetoothHealthCallback bluetoothhealthcallback)
        {
            mCallback = bluetoothhealthcallback;
        }
    }


    static ServiceConnection _2D_get0(BluetoothHealth bluetoothhealth)
    {
        return bluetoothhealth.mConnection;
    }

    static Context _2D_get1(BluetoothHealth bluetoothhealth)
    {
        return bluetoothhealth.mContext;
    }

    static IBluetoothHealth _2D_get2(BluetoothHealth bluetoothhealth)
    {
        return bluetoothhealth.mService;
    }

    static BluetoothProfile.ServiceListener _2D_get3(BluetoothHealth bluetoothhealth)
    {
        return bluetoothhealth.mServiceListener;
    }

    static IBluetoothHealth _2D_set0(BluetoothHealth bluetoothhealth, IBluetoothHealth ibluetoothhealth)
    {
        bluetoothhealth.mService = ibluetoothhealth;
        return ibluetoothhealth;
    }

    BluetoothHealth(Context context, BluetoothProfile.ServiceListener servicelistener)
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
                Log.e("BluetoothHealth", "", context);
            }
        doBind();
    }

    private boolean checkAppParam(String s, int i, int j, BluetoothHealthCallback bluetoothhealthcallback)
    {
        while(s == null || i != 1 && i != 2 || j != 10 && j != 11 && j != 12 || bluetoothhealthcallback == null) 
            return false;
        return i != 1 || j != 12;
    }

    private boolean isEnabled()
    {
        BluetoothAdapter bluetoothadapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothadapter != null && bluetoothadapter.getState() == 12)
        {
            return true;
        } else
        {
            log("Bluetooth is Not enabled");
            return false;
        }
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
        Log.d("BluetoothHealth", s);
    }

    void close()
    {
        Object obj;
        obj = mAdapter.getBluetoothManager();
        IBluetoothHealth ibluetoothhealth;
        if(obj != null)
            try
            {
                ((IBluetoothManager) (obj)).unregisterStateChangeCallback(mBluetoothStateChangeCallback);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("BluetoothHealth", "", ((Throwable) (obj)));
            }
        obj = mConnection;
        obj;
        JVM INSTR monitorenter ;
        ibluetoothhealth = mService;
        if(ibluetoothhealth == null)
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
        Log.e("BluetoothHealth", "", ((Throwable) (obj1)));
          goto _L1
        obj1;
        throw obj1;
    }

    public boolean connectChannelToSink(BluetoothDevice bluetoothdevice, BluetoothHealthAppConfiguration bluetoothhealthappconfiguration, int i)
    {
        IBluetoothHealth ibluetoothhealth = mService;
        if(ibluetoothhealth == null || !isEnabled() || !isValidDevice(bluetoothdevice) || bluetoothhealthappconfiguration == null) goto _L2; else goto _L1
_L1:
        boolean flag = ibluetoothhealth.connectChannelToSink(bluetoothdevice, bluetoothhealthappconfiguration, i);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHealth", bluetoothdevice.toString());
_L4:
        return false;
_L2:
        Log.w("BluetoothHealth", "Proxy not attached to service");
        Log.d("BluetoothHealth", Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean connectChannelToSource(BluetoothDevice bluetoothdevice, BluetoothHealthAppConfiguration bluetoothhealthappconfiguration)
    {
        IBluetoothHealth ibluetoothhealth = mService;
        if(ibluetoothhealth == null || !isEnabled() || !isValidDevice(bluetoothdevice) || bluetoothhealthappconfiguration == null) goto _L2; else goto _L1
_L1:
        boolean flag = ibluetoothhealth.connectChannelToSource(bluetoothdevice, bluetoothhealthappconfiguration);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHealth", bluetoothdevice.toString());
_L4:
        return false;
_L2:
        Log.w("BluetoothHealth", "Proxy not attached to service");
        Log.d("BluetoothHealth", Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean disconnectChannel(BluetoothDevice bluetoothdevice, BluetoothHealthAppConfiguration bluetoothhealthappconfiguration, int i)
    {
        IBluetoothHealth ibluetoothhealth = mService;
        if(ibluetoothhealth == null || !isEnabled() || !isValidDevice(bluetoothdevice) || bluetoothhealthappconfiguration == null) goto _L2; else goto _L1
_L1:
        boolean flag = ibluetoothhealth.disconnectChannel(bluetoothdevice, bluetoothhealthappconfiguration, i);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHealth", bluetoothdevice.toString());
_L4:
        return false;
_L2:
        Log.w("BluetoothHealth", "Proxy not attached to service");
        Log.d("BluetoothHealth", Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    boolean doBind()
    {
        Intent intent = new Intent(android/bluetooth/IBluetoothHealth.getName());
        ComponentName componentname = intent.resolveSystemService(mContext.getPackageManager(), 0);
        intent.setComponent(componentname);
        if(componentname == null || mContext.bindServiceAsUser(intent, mConnection, 0, Process.myUserHandle()) ^ true)
        {
            Log.e("BluetoothHealth", (new StringBuilder()).append("Could not bind to Bluetooth Health Service with ").append(intent).toString());
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
                obj = ((IBluetoothHealth) (obj)).getConnectedHealthDevices();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("BluetoothHealth", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return new ArrayList();
            }
            return ((List) (obj));
        }
        if(obj == null)
            Log.w("BluetoothHealth", "Proxy not attached to service");
        return new ArrayList();
    }

    public int getConnectionState(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHealth ibluetoothhealth = mService;
        if(ibluetoothhealth == null || !isEnabled() || !isValidDevice(bluetoothdevice)) goto _L2; else goto _L1
_L1:
        int i = ibluetoothhealth.getHealthDeviceConnectionState(bluetoothdevice);
        return i;
        bluetoothdevice;
        Log.e("BluetoothHealth", bluetoothdevice.toString());
_L4:
        return 0;
_L2:
        Log.w("BluetoothHealth", "Proxy not attached to service");
        Log.d("BluetoothHealth", Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public List getDevicesMatchingConnectionStates(int ai[])
    {
        IBluetoothHealth ibluetoothhealth = mService;
        if(ibluetoothhealth != null && isEnabled())
        {
            try
            {
                ai = ibluetoothhealth.getHealthDevicesMatchingConnectionStates(ai);
            }
            // Misplaced declaration of an exception variable
            catch(int ai[])
            {
                Log.e("BluetoothHealth", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return new ArrayList();
            }
            return ai;
        }
        if(ibluetoothhealth == null)
            Log.w("BluetoothHealth", "Proxy not attached to service");
        return new ArrayList();
    }

    public ParcelFileDescriptor getMainChannelFd(BluetoothDevice bluetoothdevice, BluetoothHealthAppConfiguration bluetoothhealthappconfiguration)
    {
        IBluetoothHealth ibluetoothhealth = mService;
        if(ibluetoothhealth == null || !isEnabled() || !isValidDevice(bluetoothdevice) || bluetoothhealthappconfiguration == null) goto _L2; else goto _L1
_L1:
        bluetoothdevice = ibluetoothhealth.getMainChannelFd(bluetoothdevice, bluetoothhealthappconfiguration);
        return bluetoothdevice;
        bluetoothdevice;
        Log.e("BluetoothHealth", bluetoothdevice.toString());
_L4:
        return null;
_L2:
        Log.w("BluetoothHealth", "Proxy not attached to service");
        Log.d("BluetoothHealth", Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean registerAppConfiguration(String s, int i, int j, int k, BluetoothHealthCallback bluetoothhealthcallback)
    {
        boolean flag = false;
        if(!isEnabled() || checkAppParam(s, j, k, bluetoothhealthcallback) ^ true)
            return false;
        bluetoothhealthcallback = new BluetoothHealthCallbackWrapper(bluetoothhealthcallback);
        s = new BluetoothHealthAppConfiguration(s, i, j, k);
        IBluetoothHealth ibluetoothhealth = mService;
        boolean flag1;
        if(ibluetoothhealth != null)
        {
            try
            {
                flag1 = ibluetoothhealth.registerAppConfiguration(s, bluetoothhealthcallback);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Log.e("BluetoothHealth", s.toString());
                flag1 = flag;
            }
        } else
        {
            Log.w("BluetoothHealth", "Proxy not attached to service");
            Log.d("BluetoothHealth", Log.getStackTraceString(new Throwable()));
            flag1 = flag;
        }
        return flag1;
    }

    public boolean registerSinkAppConfiguration(String s, int i, BluetoothHealthCallback bluetoothhealthcallback)
    {
        if(!isEnabled() || s == null)
            return false;
        else
            return registerAppConfiguration(s, i, 2, 12, bluetoothhealthcallback);
    }

    public boolean unregisterAppConfiguration(BluetoothHealthAppConfiguration bluetoothhealthappconfiguration)
    {
        boolean flag = false;
        IBluetoothHealth ibluetoothhealth = mService;
        boolean flag1;
        if(ibluetoothhealth != null && isEnabled() && bluetoothhealthappconfiguration != null)
        {
            try
            {
                flag1 = ibluetoothhealth.unregisterAppConfiguration(bluetoothhealthappconfiguration);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothHealthAppConfiguration bluetoothhealthappconfiguration)
            {
                Log.e("BluetoothHealth", bluetoothhealthappconfiguration.toString());
                flag1 = flag;
            }
        } else
        {
            Log.w("BluetoothHealth", "Proxy not attached to service");
            Log.d("BluetoothHealth", Log.getStackTraceString(new Throwable()));
            flag1 = flag;
        }
        return flag1;
    }

    public static final int APP_CONFIG_REGISTRATION_FAILURE = 1;
    public static final int APP_CONFIG_REGISTRATION_SUCCESS = 0;
    public static final int APP_CONFIG_UNREGISTRATION_FAILURE = 3;
    public static final int APP_CONFIG_UNREGISTRATION_SUCCESS = 2;
    public static final int CHANNEL_TYPE_ANY = 12;
    public static final int CHANNEL_TYPE_RELIABLE = 10;
    public static final int CHANNEL_TYPE_STREAMING = 11;
    private static final boolean DBG = true;
    public static final int HEALTH_OPERATION_ERROR = 6001;
    public static final int HEALTH_OPERATION_GENERIC_FAILURE = 6003;
    public static final int HEALTH_OPERATION_INVALID_ARGS = 6002;
    public static final int HEALTH_OPERATION_NOT_ALLOWED = 6005;
    public static final int HEALTH_OPERATION_NOT_FOUND = 6004;
    public static final int HEALTH_OPERATION_SUCCESS = 6000;
    public static final int SINK_ROLE = 2;
    public static final int SOURCE_ROLE = 1;
    public static final int STATE_CHANNEL_CONNECTED = 2;
    public static final int STATE_CHANNEL_CONNECTING = 1;
    public static final int STATE_CHANNEL_DISCONNECTED = 0;
    public static final int STATE_CHANNEL_DISCONNECTING = 3;
    private static final String TAG = "BluetoothHealth";
    private static final boolean VDBG = false;
    BluetoothAdapter mAdapter;
    private final IBluetoothStateChangeCallback mBluetoothStateChangeCallback = new IBluetoothStateChangeCallback.Stub() {

        public void onBluetoothStateChange(boolean flag)
        {
            Log.d("BluetoothHealth", (new StringBuilder()).append("onBluetoothStateChange: up=").append(flag).toString());
            if(flag) goto _L2; else goto _L1
_L1:
            ServiceConnection serviceconnection = BluetoothHealth._2D_get0(BluetoothHealth.this);
            serviceconnection;
            JVM INSTR monitorenter ;
            BluetoothHealth._2D_set0(BluetoothHealth.this, null);
            BluetoothHealth._2D_get1(BluetoothHealth.this).unbindService(BluetoothHealth._2D_get0(BluetoothHealth.this));
_L3:
            serviceconnection;
            JVM INSTR monitorexit ;
            return;
            Object obj1;
            obj1;
            Log.e("BluetoothHealth", "", ((Throwable) (obj1)));
              goto _L3
            obj1;
            throw obj1;
_L2:
            ServiceConnection serviceconnection1 = BluetoothHealth._2D_get0(BluetoothHealth.this);
            serviceconnection1;
            JVM INSTR monitorenter ;
            serviceconnection = serviceconnection1;
            if(BluetoothHealth._2D_get2(BluetoothHealth.this) != null) goto _L3; else goto _L4
_L4:
            doBind();
            serviceconnection = serviceconnection1;
              goto _L3
            Object obj;
            obj;
            Log.e("BluetoothHealth", "", ((Throwable) (obj)));
            obj = serviceconnection1;
              goto _L3
            obj;
            throw obj;
        }

        final BluetoothHealth this$0;

            
            {
                this$0 = BluetoothHealth.this;
                super();
            }
    }
;
    private final ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            Log.d("BluetoothHealth", "Proxy object connected");
            BluetoothHealth._2D_set0(BluetoothHealth.this, IBluetoothHealth.Stub.asInterface(Binder.allowBlocking(ibinder)));
            if(BluetoothHealth._2D_get3(BluetoothHealth.this) != null)
                BluetoothHealth._2D_get3(BluetoothHealth.this).onServiceConnected(3, BluetoothHealth.this);
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            Log.d("BluetoothHealth", "Proxy object disconnected");
            BluetoothHealth._2D_set0(BluetoothHealth.this, null);
            if(BluetoothHealth._2D_get3(BluetoothHealth.this) != null)
                BluetoothHealth._2D_get3(BluetoothHealth.this).onServiceDisconnected(3);
        }

        final BluetoothHealth this$0;

            
            {
                this$0 = BluetoothHealth.this;
                super();
            }
    }
;
    private Context mContext;
    private volatile IBluetoothHealth mService;
    private BluetoothProfile.ServiceListener mServiceListener;
}

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
//            BluetoothProfile, BluetoothAdapter, IBluetoothManager, BluetoothClass, 
//            BluetoothDevice, IBluetoothMap, IBluetoothStateChangeCallback

public final class BluetoothMap
    implements BluetoothProfile
{

    static ServiceConnection _2D_get0(BluetoothMap bluetoothmap)
    {
        return bluetoothmap.mConnection;
    }

    static Context _2D_get1(BluetoothMap bluetoothmap)
    {
        return bluetoothmap.mContext;
    }

    static IBluetoothMap _2D_get2(BluetoothMap bluetoothmap)
    {
        return bluetoothmap.mService;
    }

    static BluetoothProfile.ServiceListener _2D_get3(BluetoothMap bluetoothmap)
    {
        return bluetoothmap.mServiceListener;
    }

    static IBluetoothMap _2D_set0(BluetoothMap bluetoothmap, IBluetoothMap ibluetoothmap)
    {
        bluetoothmap.mService = ibluetoothmap;
        return ibluetoothmap;
    }

    static void _2D_wrap0(String s)
    {
        log(s);
    }

    BluetoothMap(Context context, BluetoothProfile.ServiceListener servicelistener)
    {
        Log.d("BluetoothMap", "Create BluetoothMap proxy object");
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
                Log.e("BluetoothMap", "", context);
            }
        doBind();
    }

    public static boolean doesClassMatchSink(BluetoothClass bluetoothclass)
    {
        switch(bluetoothclass.getDeviceClass())
        {
        default:
            return false;

        case 256: 
        case 260: 
        case 264: 
        case 268: 
            return true;
        }
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
        Log.d("BluetoothMap", s);
    }

    public void close()
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = mAdapter.getBluetoothManager();
        if(obj == null)
            break MISSING_BLOCK_LABEL_24;
        ((IBluetoothManager) (obj)).unregisterStateChangeCallback(mBluetoothStateChangeCallback);
_L1:
        obj = mConnection;
        obj;
        JVM INSTR monitorenter ;
        IBluetoothMap ibluetoothmap = mService;
        if(ibluetoothmap == null)
            break MISSING_BLOCK_LABEL_56;
        mService = null;
        mContext.unbindService(mConnection);
_L2:
        obj;
        JVM INSTR monitorexit ;
        mServiceListener = null;
        this;
        JVM INSTR monitorexit ;
        return;
        obj;
        Log.e("BluetoothMap", "", ((Throwable) (obj)));
          goto _L1
        obj;
        throw obj;
        Object obj1;
        obj1;
        Log.e("BluetoothMap", "", ((Throwable) (obj1)));
          goto _L2
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
          goto _L1
    }

    public boolean connect(BluetoothDevice bluetoothdevice)
    {
        log((new StringBuilder()).append("connect(").append(bluetoothdevice).append(")").append("not supported for MAPS").toString());
        return false;
    }

    public boolean disconnect(BluetoothDevice bluetoothdevice)
    {
        log((new StringBuilder()).append("disconnect(").append(bluetoothdevice).append(")").toString());
        IBluetoothMap ibluetoothmap = mService;
        if(ibluetoothmap != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetoothmap.disconnect(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothMap", Log.getStackTraceString(new Throwable()));
                return false;
            }
            return flag;
        }
        if(ibluetoothmap == null)
            Log.w("BluetoothMap", "Proxy not attached to service");
        return false;
    }

    boolean doBind()
    {
        Intent intent = new Intent(android/bluetooth/IBluetoothMap.getName());
        ComponentName componentname = intent.resolveSystemService(mContext.getPackageManager(), 0);
        intent.setComponent(componentname);
        if(componentname == null || mContext.bindServiceAsUser(intent, mConnection, 0, Process.myUserHandle()) ^ true)
        {
            Log.e("BluetoothMap", (new StringBuilder()).append("Could not bind to Bluetooth MAP Service with ").append(intent).toString());
            return false;
        } else
        {
            return true;
        }
    }

    protected void finalize()
        throws Throwable
    {
        close();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public BluetoothDevice getClient()
    {
        Object obj = mService;
        if(obj == null) goto _L2; else goto _L1
_L1:
        obj = ((IBluetoothMap) (obj)).getClient();
        return ((BluetoothDevice) (obj));
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothMap", remoteexception.toString());
_L4:
        return null;
_L2:
        Log.w("BluetoothMap", "Proxy not attached to service");
        log(Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public List getConnectedDevices()
    {
        log("getConnectedDevices()");
        Object obj = mService;
        if(obj != null && isEnabled())
        {
            try
            {
                obj = ((IBluetoothMap) (obj)).getConnectedDevices();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("BluetoothMap", Log.getStackTraceString(new Throwable()));
                return new ArrayList();
            }
            return ((List) (obj));
        }
        if(obj == null)
            Log.w("BluetoothMap", "Proxy not attached to service");
        return new ArrayList();
    }

    public int getConnectionState(BluetoothDevice bluetoothdevice)
    {
        log((new StringBuilder()).append("getConnectionState(").append(bluetoothdevice).append(")").toString());
        IBluetoothMap ibluetoothmap = mService;
        if(ibluetoothmap != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            int i;
            try
            {
                i = ibluetoothmap.getConnectionState(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothMap", Log.getStackTraceString(new Throwable()));
                return 0;
            }
            return i;
        }
        if(ibluetoothmap == null)
            Log.w("BluetoothMap", "Proxy not attached to service");
        return 0;
    }

    public List getDevicesMatchingConnectionStates(int ai[])
    {
        log("getDevicesMatchingStates()");
        IBluetoothMap ibluetoothmap = mService;
        if(ibluetoothmap != null && isEnabled())
        {
            try
            {
                ai = ibluetoothmap.getDevicesMatchingConnectionStates(ai);
            }
            // Misplaced declaration of an exception variable
            catch(int ai[])
            {
                Log.e("BluetoothMap", Log.getStackTraceString(new Throwable()));
                return new ArrayList();
            }
            return ai;
        }
        if(ibluetoothmap == null)
            Log.w("BluetoothMap", "Proxy not attached to service");
        return new ArrayList();
    }

    public int getPriority(BluetoothDevice bluetoothdevice)
    {
        IBluetoothMap ibluetoothmap = mService;
        if(ibluetoothmap != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            int i;
            try
            {
                i = ibluetoothmap.getPriority(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothMap", Log.getStackTraceString(new Throwable()));
                return 0;
            }
            return i;
        }
        if(ibluetoothmap == null)
            Log.w("BluetoothMap", "Proxy not attached to service");
        return 0;
    }

    public int getState()
    {
        IBluetoothMap ibluetoothmap = mService;
        if(ibluetoothmap == null) goto _L2; else goto _L1
_L1:
        int i = ibluetoothmap.getState();
        return i;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothMap", remoteexception.toString());
_L4:
        return -1;
_L2:
        Log.w("BluetoothMap", "Proxy not attached to service");
        log(Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean isConnected(BluetoothDevice bluetoothdevice)
    {
        IBluetoothMap ibluetoothmap = mService;
        if(ibluetoothmap == null) goto _L2; else goto _L1
_L1:
        boolean flag = ibluetoothmap.isConnected(bluetoothdevice);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothMap", bluetoothdevice.toString());
_L4:
        return false;
_L2:
        Log.w("BluetoothMap", "Proxy not attached to service");
        log(Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean setPriority(BluetoothDevice bluetoothdevice, int i)
    {
        log((new StringBuilder()).append("setPriority(").append(bluetoothdevice).append(", ").append(i).append(")").toString());
        IBluetoothMap ibluetoothmap = mService;
        if(ibluetoothmap != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            if(i != 0 && i != 100)
                return false;
            boolean flag;
            try
            {
                flag = ibluetoothmap.setPriority(bluetoothdevice, i);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothMap", Log.getStackTraceString(new Throwable()));
                return false;
            }
            return flag;
        }
        if(ibluetoothmap == null)
            Log.w("BluetoothMap", "Proxy not attached to service");
        return false;
    }

    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.map.profile.action.CONNECTION_STATE_CHANGED";
    private static final boolean DBG = true;
    public static final int RESULT_CANCELED = 2;
    public static final int RESULT_FAILURE = 0;
    public static final int RESULT_SUCCESS = 1;
    public static final int STATE_ERROR = -1;
    private static final String TAG = "BluetoothMap";
    private static final boolean VDBG = false;
    private BluetoothAdapter mAdapter;
    private final IBluetoothStateChangeCallback mBluetoothStateChangeCallback = new IBluetoothStateChangeCallback.Stub() {

        public void onBluetoothStateChange(boolean flag)
        {
            Log.d("BluetoothMap", (new StringBuilder()).append("onBluetoothStateChange: up=").append(flag).toString());
            if(flag) goto _L2; else goto _L1
_L1:
            ServiceConnection serviceconnection = BluetoothMap._2D_get0(BluetoothMap.this);
            serviceconnection;
            JVM INSTR monitorenter ;
            BluetoothMap._2D_set0(BluetoothMap.this, null);
            BluetoothMap._2D_get1(BluetoothMap.this).unbindService(BluetoothMap._2D_get0(BluetoothMap.this));
_L3:
            serviceconnection;
            JVM INSTR monitorexit ;
            return;
            Object obj1;
            obj1;
            Log.e("BluetoothMap", "", ((Throwable) (obj1)));
              goto _L3
            obj1;
            throw obj1;
_L2:
            ServiceConnection serviceconnection1 = BluetoothMap._2D_get0(BluetoothMap.this);
            serviceconnection1;
            JVM INSTR monitorenter ;
            serviceconnection = serviceconnection1;
            if(BluetoothMap._2D_get2(BluetoothMap.this) != null) goto _L3; else goto _L4
_L4:
            doBind();
            serviceconnection = serviceconnection1;
              goto _L3
            Object obj;
            obj;
            Log.e("BluetoothMap", "", ((Throwable) (obj)));
            obj = serviceconnection1;
              goto _L3
            obj;
            throw obj;
        }

        final BluetoothMap this$0;

            
            {
                this$0 = BluetoothMap.this;
                super();
            }
    }
;
    private final ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            BluetoothMap._2D_wrap0("Proxy object connected");
            BluetoothMap._2D_set0(BluetoothMap.this, IBluetoothMap.Stub.asInterface(Binder.allowBlocking(ibinder)));
            if(BluetoothMap._2D_get3(BluetoothMap.this) != null)
                BluetoothMap._2D_get3(BluetoothMap.this).onServiceConnected(9, BluetoothMap.this);
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            BluetoothMap._2D_wrap0("Proxy object disconnected");
            BluetoothMap._2D_set0(BluetoothMap.this, null);
            if(BluetoothMap._2D_get3(BluetoothMap.this) != null)
                BluetoothMap._2D_get3(BluetoothMap.this).onServiceDisconnected(9);
        }

        final BluetoothMap this$0;

            
            {
                this$0 = BluetoothMap.this;
                super();
            }
    }
;
    private final Context mContext;
    private volatile IBluetoothMap mService;
    private BluetoothProfile.ServiceListener mServiceListener;
}

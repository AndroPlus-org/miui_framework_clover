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
//            IBluetoothSap, IBluetoothStateChangeCallback

public final class BluetoothSap
    implements BluetoothProfile
{

    static ServiceConnection _2D_get0(BluetoothSap bluetoothsap)
    {
        return bluetoothsap.mConnection;
    }

    static Context _2D_get1(BluetoothSap bluetoothsap)
    {
        return bluetoothsap.mContext;
    }

    static IBluetoothSap _2D_get2(BluetoothSap bluetoothsap)
    {
        return bluetoothsap.mService;
    }

    static BluetoothProfile.ServiceListener _2D_get3(BluetoothSap bluetoothsap)
    {
        return bluetoothsap.mServiceListener;
    }

    static IBluetoothSap _2D_set0(BluetoothSap bluetoothsap, IBluetoothSap ibluetoothsap)
    {
        bluetoothsap.mService = ibluetoothsap;
        return ibluetoothsap;
    }

    static void _2D_wrap0(String s)
    {
        log(s);
    }

    BluetoothSap(Context context, BluetoothProfile.ServiceListener servicelistener)
    {
        Log.d("BluetoothSap", "Create BluetoothSap proxy object");
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
                Log.e("BluetoothSap", "", context);
            }
        doBind();
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
        Log.d("BluetoothSap", s);
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
        IBluetoothSap ibluetoothsap = mService;
        if(ibluetoothsap == null)
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
        Log.e("BluetoothSap", "", ((Throwable) (obj)));
          goto _L1
        obj;
        throw obj;
        Object obj1;
        obj1;
        Log.e("BluetoothSap", "", ((Throwable) (obj1)));
          goto _L2
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
          goto _L1
    }

    public boolean connect(BluetoothDevice bluetoothdevice)
    {
        log((new StringBuilder()).append("connect(").append(bluetoothdevice).append(")").append("not supported for SAPS").toString());
        return false;
    }

    public boolean disconnect(BluetoothDevice bluetoothdevice)
    {
        log((new StringBuilder()).append("disconnect(").append(bluetoothdevice).append(")").toString());
        IBluetoothSap ibluetoothsap = mService;
        if(ibluetoothsap != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetoothsap.disconnect(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothSap", Log.getStackTraceString(new Throwable()));
                return false;
            }
            return flag;
        }
        if(ibluetoothsap == null)
            Log.w("BluetoothSap", "Proxy not attached to service");
        return false;
    }

    boolean doBind()
    {
        Intent intent = new Intent(android/bluetooth/IBluetoothSap.getName());
        ComponentName componentname = intent.resolveSystemService(mContext.getPackageManager(), 0);
        intent.setComponent(componentname);
        if(componentname == null || mContext.bindServiceAsUser(intent, mConnection, 0, Process.myUserHandle()) ^ true)
        {
            Log.e("BluetoothSap", (new StringBuilder()).append("Could not bind to Bluetooth SAP Service with ").append(intent).toString());
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
        obj = ((IBluetoothSap) (obj)).getClient();
        return ((BluetoothDevice) (obj));
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothSap", remoteexception.toString());
_L4:
        return null;
_L2:
        Log.w("BluetoothSap", "Proxy not attached to service");
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
                obj = ((IBluetoothSap) (obj)).getConnectedDevices();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("BluetoothSap", Log.getStackTraceString(new Throwable()));
                return new ArrayList();
            }
            return ((List) (obj));
        }
        if(obj == null)
            Log.w("BluetoothSap", "Proxy not attached to service");
        return new ArrayList();
    }

    public int getConnectionState(BluetoothDevice bluetoothdevice)
    {
        log((new StringBuilder()).append("getConnectionState(").append(bluetoothdevice).append(")").toString());
        IBluetoothSap ibluetoothsap = mService;
        if(ibluetoothsap != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            int i;
            try
            {
                i = ibluetoothsap.getConnectionState(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothSap", Log.getStackTraceString(new Throwable()));
                return 0;
            }
            return i;
        }
        if(ibluetoothsap == null)
            Log.w("BluetoothSap", "Proxy not attached to service");
        return 0;
    }

    public List getDevicesMatchingConnectionStates(int ai[])
    {
        log("getDevicesMatchingStates()");
        IBluetoothSap ibluetoothsap = mService;
        if(ibluetoothsap != null && isEnabled())
        {
            try
            {
                ai = ibluetoothsap.getDevicesMatchingConnectionStates(ai);
            }
            // Misplaced declaration of an exception variable
            catch(int ai[])
            {
                Log.e("BluetoothSap", Log.getStackTraceString(new Throwable()));
                return new ArrayList();
            }
            return ai;
        }
        if(ibluetoothsap == null)
            Log.w("BluetoothSap", "Proxy not attached to service");
        return new ArrayList();
    }

    public int getPriority(BluetoothDevice bluetoothdevice)
    {
        IBluetoothSap ibluetoothsap = mService;
        if(ibluetoothsap != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            int i;
            try
            {
                i = ibluetoothsap.getPriority(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothSap", Log.getStackTraceString(new Throwable()));
                return 0;
            }
            return i;
        }
        if(ibluetoothsap == null)
            Log.w("BluetoothSap", "Proxy not attached to service");
        return 0;
    }

    public int getState()
    {
        IBluetoothSap ibluetoothsap = mService;
        if(ibluetoothsap == null) goto _L2; else goto _L1
_L1:
        int i = ibluetoothsap.getState();
        return i;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothSap", remoteexception.toString());
_L4:
        return -1;
_L2:
        Log.w("BluetoothSap", "Proxy not attached to service");
        log(Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean isConnected(BluetoothDevice bluetoothdevice)
    {
        IBluetoothSap ibluetoothsap = mService;
        if(ibluetoothsap == null) goto _L2; else goto _L1
_L1:
        boolean flag = ibluetoothsap.isConnected(bluetoothdevice);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothSap", bluetoothdevice.toString());
_L4:
        return false;
_L2:
        Log.w("BluetoothSap", "Proxy not attached to service");
        log(Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean setPriority(BluetoothDevice bluetoothdevice, int i)
    {
        log((new StringBuilder()).append("setPriority(").append(bluetoothdevice).append(", ").append(i).append(")").toString());
        IBluetoothSap ibluetoothsap = mService;
        if(ibluetoothsap != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            if(i != 0 && i != 100)
                return false;
            boolean flag;
            try
            {
                flag = ibluetoothsap.setPriority(bluetoothdevice, i);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothSap", Log.getStackTraceString(new Throwable()));
                return false;
            }
            return flag;
        }
        if(ibluetoothsap == null)
            Log.w("BluetoothSap", "Proxy not attached to service");
        return false;
    }

    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.sap.profile.action.CONNECTION_STATE_CHANGED";
    private static final boolean DBG = true;
    public static final int RESULT_CANCELED = 2;
    public static final int RESULT_SUCCESS = 1;
    public static final int STATE_ERROR = -1;
    private static final String TAG = "BluetoothSap";
    private static final boolean VDBG = false;
    private BluetoothAdapter mAdapter;
    private final IBluetoothStateChangeCallback mBluetoothStateChangeCallback = new IBluetoothStateChangeCallback.Stub() {

        public void onBluetoothStateChange(boolean flag)
        {
            Log.d("BluetoothSap", (new StringBuilder()).append("onBluetoothStateChange: up=").append(flag).toString());
            if(flag) goto _L2; else goto _L1
_L1:
            ServiceConnection serviceconnection = BluetoothSap._2D_get0(BluetoothSap.this);
            serviceconnection;
            JVM INSTR monitorenter ;
            BluetoothSap._2D_set0(BluetoothSap.this, null);
            BluetoothSap._2D_get1(BluetoothSap.this).unbindService(BluetoothSap._2D_get0(BluetoothSap.this));
_L3:
            serviceconnection;
            JVM INSTR monitorexit ;
            return;
            Object obj1;
            obj1;
            Log.e("BluetoothSap", "", ((Throwable) (obj1)));
              goto _L3
            obj1;
            throw obj1;
_L2:
            ServiceConnection serviceconnection1 = BluetoothSap._2D_get0(BluetoothSap.this);
            serviceconnection1;
            JVM INSTR monitorenter ;
            serviceconnection = serviceconnection1;
            if(BluetoothSap._2D_get2(BluetoothSap.this) != null) goto _L3; else goto _L4
_L4:
            doBind();
            serviceconnection = serviceconnection1;
              goto _L3
            Object obj;
            obj;
            Log.e("BluetoothSap", "", ((Throwable) (obj)));
            obj = serviceconnection1;
              goto _L3
            obj;
            throw obj;
        }

        final BluetoothSap this$0;

            
            {
                this$0 = BluetoothSap.this;
                super();
            }
    }
;
    private final ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            BluetoothSap._2D_wrap0("Proxy object connected");
            BluetoothSap._2D_set0(BluetoothSap.this, IBluetoothSap.Stub.asInterface(Binder.allowBlocking(ibinder)));
            if(BluetoothSap._2D_get3(BluetoothSap.this) != null)
                BluetoothSap._2D_get3(BluetoothSap.this).onServiceConnected(10, BluetoothSap.this);
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            BluetoothSap._2D_wrap0("Proxy object disconnected");
            BluetoothSap._2D_set0(BluetoothSap.this, null);
            if(BluetoothSap._2D_get3(BluetoothSap.this) != null)
                BluetoothSap._2D_get3(BluetoothSap.this).onServiceDisconnected(10);
        }

        final BluetoothSap this$0;

            
            {
                this$0 = BluetoothSap.this;
                super();
            }
    }
;
    private final Context mContext;
    private volatile IBluetoothSap mService;
    private BluetoothProfile.ServiceListener mServiceListener;
}

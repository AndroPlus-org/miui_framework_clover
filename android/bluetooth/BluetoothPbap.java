// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.content.*;
import android.os.*;
import android.util.Log;

// Referenced classes of package android.bluetooth:
//            BluetoothAdapter, IBluetoothManager, BluetoothClass, IBluetoothPbap, 
//            IBluetoothStateChangeCallback, BluetoothDevice

public class BluetoothPbap
{
    public static interface ServiceListener
    {

        public abstract void onServiceConnected(BluetoothPbap bluetoothpbap);

        public abstract void onServiceDisconnected();
    }


    static ServiceConnection _2D_get0(BluetoothPbap bluetoothpbap)
    {
        return bluetoothpbap.mConnection;
    }

    static Context _2D_get1(BluetoothPbap bluetoothpbap)
    {
        return bluetoothpbap.mContext;
    }

    static IBluetoothPbap _2D_get2(BluetoothPbap bluetoothpbap)
    {
        return bluetoothpbap.mService;
    }

    static ServiceListener _2D_get3(BluetoothPbap bluetoothpbap)
    {
        return bluetoothpbap.mServiceListener;
    }

    static IBluetoothPbap _2D_set0(BluetoothPbap bluetoothpbap, IBluetoothPbap ibluetoothpbap)
    {
        bluetoothpbap.mService = ibluetoothpbap;
        return ibluetoothpbap;
    }

    static void _2D_wrap0(String s)
    {
        log(s);
    }

    public BluetoothPbap(Context context, ServiceListener servicelistener)
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
                Log.e("BluetoothPbap", "", context);
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

    private static void log(String s)
    {
        Log.d("BluetoothPbap", s);
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
        IBluetoothPbap ibluetoothpbap = mService;
        if(ibluetoothpbap == null)
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
        Log.e("BluetoothPbap", "", ((Throwable) (obj)));
          goto _L1
        obj;
        throw obj;
        Object obj1;
        obj1;
        Log.e("BluetoothPbap", "", ((Throwable) (obj1)));
          goto _L2
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
          goto _L1
    }

    public boolean disconnect()
    {
        IBluetoothPbap ibluetoothpbap;
        log("disconnect()");
        ibluetoothpbap = mService;
        if(ibluetoothpbap == null) goto _L2; else goto _L1
_L1:
        ibluetoothpbap.disconnect();
        return true;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothPbap", remoteexception.toString());
_L4:
        return false;
_L2:
        Log.w("BluetoothPbap", "Proxy not attached to service");
        log(Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    boolean doBind()
    {
        Intent intent = new Intent(android/bluetooth/IBluetoothPbap.getName());
        ComponentName componentname = intent.resolveSystemService(mContext.getPackageManager(), 0);
        intent.setComponent(componentname);
        if(componentname == null || mContext.bindServiceAsUser(intent, mConnection, 0, Process.myUserHandle()) ^ true)
        {
            Log.e("BluetoothPbap", (new StringBuilder()).append("Could not bind to Bluetooth Pbap Service with ").append(intent).toString());
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
        obj = ((IBluetoothPbap) (obj)).getClient();
        return ((BluetoothDevice) (obj));
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothPbap", remoteexception.toString());
_L4:
        return null;
_L2:
        Log.w("BluetoothPbap", "Proxy not attached to service");
        log(Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int getState()
    {
        IBluetoothPbap ibluetoothpbap = mService;
        if(ibluetoothpbap == null) goto _L2; else goto _L1
_L1:
        int i = ibluetoothpbap.getState();
        return i;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothPbap", remoteexception.toString());
_L4:
        return -1;
_L2:
        Log.w("BluetoothPbap", "Proxy not attached to service");
        log(Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean isConnected(BluetoothDevice bluetoothdevice)
    {
        IBluetoothPbap ibluetoothpbap = mService;
        if(ibluetoothpbap == null) goto _L2; else goto _L1
_L1:
        boolean flag = ibluetoothpbap.isConnected(bluetoothdevice);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothPbap", bluetoothdevice.toString());
_L4:
        return false;
_L2:
        Log.w("BluetoothPbap", "Proxy not attached to service");
        log(Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final boolean DBG = true;
    public static final String PBAP_PREVIOUS_STATE = "android.bluetooth.pbap.intent.PBAP_PREVIOUS_STATE";
    public static final String PBAP_STATE = "android.bluetooth.pbap.intent.PBAP_STATE";
    public static final String PBAP_STATE_CHANGED_ACTION = "android.bluetooth.pbap.intent.action.PBAP_STATE_CHANGED";
    public static final int RESULT_CANCELED = 2;
    public static final int RESULT_FAILURE = 0;
    public static final int RESULT_SUCCESS = 1;
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_ERROR = -1;
    private static final String TAG = "BluetoothPbap";
    private static final boolean VDBG = false;
    private BluetoothAdapter mAdapter;
    private final IBluetoothStateChangeCallback mBluetoothStateChangeCallback = new IBluetoothStateChangeCallback.Stub() {

        public void onBluetoothStateChange(boolean flag)
        {
            Log.d("BluetoothPbap", (new StringBuilder()).append("onBluetoothStateChange: up=").append(flag).toString());
            if(flag) goto _L2; else goto _L1
_L1:
            ServiceConnection serviceconnection = BluetoothPbap._2D_get0(BluetoothPbap.this);
            serviceconnection;
            JVM INSTR monitorenter ;
            BluetoothPbap._2D_set0(BluetoothPbap.this, null);
            BluetoothPbap._2D_get1(BluetoothPbap.this).unbindService(BluetoothPbap._2D_get0(BluetoothPbap.this));
_L3:
            serviceconnection;
            JVM INSTR monitorexit ;
            return;
            Object obj1;
            obj1;
            Log.e("BluetoothPbap", "", ((Throwable) (obj1)));
              goto _L3
            obj1;
            throw obj1;
_L2:
            ServiceConnection serviceconnection1 = BluetoothPbap._2D_get0(BluetoothPbap.this);
            serviceconnection1;
            JVM INSTR monitorenter ;
            serviceconnection = serviceconnection1;
            if(BluetoothPbap._2D_get2(BluetoothPbap.this) != null) goto _L3; else goto _L4
_L4:
            doBind();
            serviceconnection = serviceconnection1;
              goto _L3
            Object obj;
            obj;
            Log.e("BluetoothPbap", "", ((Throwable) (obj)));
            obj = serviceconnection1;
              goto _L3
            obj;
            throw obj;
        }

        final BluetoothPbap this$0;

            
            {
                this$0 = BluetoothPbap.this;
                super();
            }
    }
;
    private final ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            BluetoothPbap._2D_wrap0("Proxy object connected");
            BluetoothPbap._2D_set0(BluetoothPbap.this, IBluetoothPbap.Stub.asInterface(ibinder));
            if(BluetoothPbap._2D_get3(BluetoothPbap.this) != null)
                BluetoothPbap._2D_get3(BluetoothPbap.this).onServiceConnected(BluetoothPbap.this);
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            BluetoothPbap._2D_wrap0("Proxy object disconnected");
            BluetoothPbap._2D_set0(BluetoothPbap.this, null);
            if(BluetoothPbap._2D_get3(BluetoothPbap.this) != null)
                BluetoothPbap._2D_get3(BluetoothPbap.this).onServiceDisconnected();
        }

        final BluetoothPbap this$0;

            
            {
                this$0 = BluetoothPbap.this;
                super();
            }
    }
;
    private final Context mContext;
    private volatile IBluetoothPbap mService;
    private ServiceListener mServiceListener;
}

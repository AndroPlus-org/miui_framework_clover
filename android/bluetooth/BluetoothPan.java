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
//            IBluetoothPan, IBluetoothStateChangeCallback

public final class BluetoothPan
    implements BluetoothProfile
{

    static ServiceConnection _2D_get0(BluetoothPan bluetoothpan)
    {
        return bluetoothpan.mConnection;
    }

    static Context _2D_get1(BluetoothPan bluetoothpan)
    {
        return bluetoothpan.mContext;
    }

    static IBluetoothPan _2D_get2(BluetoothPan bluetoothpan)
    {
        return bluetoothpan.mPanService;
    }

    static BluetoothProfile.ServiceListener _2D_get3(BluetoothPan bluetoothpan)
    {
        return bluetoothpan.mServiceListener;
    }

    static IBluetoothPan _2D_set0(BluetoothPan bluetoothpan, IBluetoothPan ibluetoothpan)
    {
        bluetoothpan.mPanService = ibluetoothpan;
        return ibluetoothpan;
    }

    BluetoothPan(Context context, BluetoothProfile.ServiceListener servicelistener)
    {
        mContext = context;
        mServiceListener = servicelistener;
        mAdapter = BluetoothAdapter.getDefaultAdapter();
        try
        {
            mAdapter.getBluetoothManager().registerStateChangeCallback(mStateChangeCallback);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.w("BluetoothPan", "Unable to register BluetoothStateChangeCallback", context);
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
        Log.d("BluetoothPan", s);
    }

    void close()
    {
        Object obj;
        obj = mAdapter.getBluetoothManager();
        IBluetoothPan ibluetoothpan;
        if(obj != null)
            try
            {
                ((IBluetoothManager) (obj)).unregisterStateChangeCallback(mStateChangeCallback);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.w("BluetoothPan", "Unable to unregister BluetoothStateChangeCallback", ((Throwable) (obj)));
            }
        obj = mConnection;
        obj;
        JVM INSTR monitorenter ;
        ibluetoothpan = mPanService;
        if(ibluetoothpan == null)
            break MISSING_BLOCK_LABEL_54;
        mPanService = null;
        mContext.unbindService(mConnection);
_L1:
        obj;
        JVM INSTR monitorexit ;
        mServiceListener = null;
        return;
        Object obj1;
        obj1;
        Log.e("BluetoothPan", "", ((Throwable) (obj1)));
          goto _L1
        obj1;
        throw obj1;
    }

    public boolean connect(BluetoothDevice bluetoothdevice)
    {
        log((new StringBuilder()).append("connect(").append(bluetoothdevice).append(")").toString());
        IBluetoothPan ibluetoothpan = mPanService;
        if(ibluetoothpan != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetoothpan.connect(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothPan", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return false;
            }
            return flag;
        }
        if(ibluetoothpan == null)
            Log.w("BluetoothPan", "Proxy not attached to service");
        return false;
    }

    public boolean disconnect(BluetoothDevice bluetoothdevice)
    {
        log((new StringBuilder()).append("disconnect(").append(bluetoothdevice).append(")").toString());
        IBluetoothPan ibluetoothpan = mPanService;
        if(ibluetoothpan != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetoothpan.disconnect(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothPan", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return false;
            }
            return flag;
        }
        if(ibluetoothpan == null)
            Log.w("BluetoothPan", "Proxy not attached to service");
        return false;
    }

    boolean doBind()
    {
        Intent intent = new Intent(android/bluetooth/IBluetoothPan.getName());
        ComponentName componentname = intent.resolveSystemService(mContext.getPackageManager(), 0);
        intent.setComponent(componentname);
        if(componentname == null || mContext.bindServiceAsUser(intent, mConnection, 0, Process.myUserHandle()) ^ true)
        {
            Log.e("BluetoothPan", (new StringBuilder()).append("Could not bind to Bluetooth Pan Service with ").append(intent).toString());
            return false;
        } else
        {
            return true;
        }
    }

    protected void finalize()
    {
        close();
    }

    public List getConnectedDevices()
    {
        Object obj = mPanService;
        if(obj != null && isEnabled())
        {
            try
            {
                obj = ((IBluetoothPan) (obj)).getConnectedDevices();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("BluetoothPan", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return new ArrayList();
            }
            return ((List) (obj));
        }
        if(obj == null)
            Log.w("BluetoothPan", "Proxy not attached to service");
        return new ArrayList();
    }

    public int getConnectionState(BluetoothDevice bluetoothdevice)
    {
        IBluetoothPan ibluetoothpan = mPanService;
        if(ibluetoothpan != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            int i;
            try
            {
                i = ibluetoothpan.getConnectionState(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothPan", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return 0;
            }
            return i;
        }
        if(ibluetoothpan == null)
            Log.w("BluetoothPan", "Proxy not attached to service");
        return 0;
    }

    public List getDevicesMatchingConnectionStates(int ai[])
    {
        IBluetoothPan ibluetoothpan = mPanService;
        if(ibluetoothpan != null && isEnabled())
        {
            try
            {
                ai = ibluetoothpan.getDevicesMatchingConnectionStates(ai);
            }
            // Misplaced declaration of an exception variable
            catch(int ai[])
            {
                Log.e("BluetoothPan", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return new ArrayList();
            }
            return ai;
        }
        if(ibluetoothpan == null)
            Log.w("BluetoothPan", "Proxy not attached to service");
        return new ArrayList();
    }

    public boolean isTetheringOn()
    {
        IBluetoothPan ibluetoothpan;
        ibluetoothpan = mPanService;
        if(ibluetoothpan == null || !isEnabled())
            break MISSING_BLOCK_LABEL_60;
        boolean flag = ibluetoothpan.isTetheringOn();
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothPan", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
        return false;
    }

    public void setBluetoothTethering(boolean flag)
    {
        IBluetoothPan ibluetoothpan;
        log((new StringBuilder()).append("setBluetoothTethering(").append(flag).append(")").toString());
        ibluetoothpan = mPanService;
        if(ibluetoothpan == null || !isEnabled())
            break MISSING_BLOCK_LABEL_51;
        ibluetoothpan.setBluetoothTethering(flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothPan", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
          goto _L1
    }

    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.pan.profile.action.CONNECTION_STATE_CHANGED";
    private static final boolean DBG = true;
    public static final String EXTRA_LOCAL_ROLE = "android.bluetooth.pan.extra.LOCAL_ROLE";
    public static final int LOCAL_NAP_ROLE = 1;
    public static final int LOCAL_PANU_ROLE = 2;
    public static final int PAN_CONNECT_FAILED_ALREADY_CONNECTED = 1001;
    public static final int PAN_CONNECT_FAILED_ATTEMPT_FAILED = 1002;
    public static final int PAN_DISCONNECT_FAILED_NOT_CONNECTED = 1000;
    public static final int PAN_OPERATION_GENERIC_FAILURE = 1003;
    public static final int PAN_OPERATION_SUCCESS = 1004;
    public static final int PAN_ROLE_NONE = 0;
    public static final int REMOTE_NAP_ROLE = 1;
    public static final int REMOTE_PANU_ROLE = 2;
    private static final String TAG = "BluetoothPan";
    private static final boolean VDBG = false;
    private BluetoothAdapter mAdapter;
    private final ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            Log.d("BluetoothPan", "BluetoothPAN Proxy object connected");
            BluetoothPan._2D_set0(BluetoothPan.this, IBluetoothPan.Stub.asInterface(Binder.allowBlocking(ibinder)));
            if(BluetoothPan._2D_get3(BluetoothPan.this) != null)
                BluetoothPan._2D_get3(BluetoothPan.this).onServiceConnected(5, BluetoothPan.this);
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            Log.d("BluetoothPan", "BluetoothPAN Proxy object disconnected");
            BluetoothPan._2D_set0(BluetoothPan.this, null);
            if(BluetoothPan._2D_get3(BluetoothPan.this) != null)
                BluetoothPan._2D_get3(BluetoothPan.this).onServiceDisconnected(5);
        }

        final BluetoothPan this$0;

            
            {
                this$0 = BluetoothPan.this;
                super();
            }
    }
;
    private Context mContext;
    private volatile IBluetoothPan mPanService;
    private BluetoothProfile.ServiceListener mServiceListener;
    private final IBluetoothStateChangeCallback mStateChangeCallback = new IBluetoothStateChangeCallback.Stub() {

        public void onBluetoothStateChange(boolean flag)
        {
            Log.d("BluetoothPan", (new StringBuilder()).append("onBluetoothStateChange on: ").append(flag).toString());
            if(!flag) goto _L2; else goto _L1
_L1:
            if(BluetoothPan._2D_get2(BluetoothPan.this) == null)
                doBind();
_L3:
            return;
            Object obj;
            obj;
            Log.e("BluetoothPan", "onBluetoothStateChange: could not bind to PAN service: ", ((Throwable) (obj)));
              goto _L3
            obj;
            Log.e("BluetoothPan", "onBluetoothStateChange: could not bind to PAN service: ", ((Throwable) (obj)));
              goto _L3
_L2:
            ServiceConnection serviceconnection = BluetoothPan._2D_get0(BluetoothPan.this);
            serviceconnection;
            JVM INSTR monitorenter ;
            BluetoothPan._2D_set0(BluetoothPan.this, null);
            BluetoothPan._2D_get1(BluetoothPan.this).unbindService(BluetoothPan._2D_get0(BluetoothPan.this));
_L4:
            serviceconnection;
            JVM INSTR monitorexit ;
              goto _L3
            Object obj1;
            obj1;
            Log.e("BluetoothPan", "", ((Throwable) (obj1)));
              goto _L4
            obj1;
            throw obj1;
              goto _L3
        }

        final BluetoothPan this$0;

            
            {
                this$0 = BluetoothPan.this;
                super();
            }
    }
;
}

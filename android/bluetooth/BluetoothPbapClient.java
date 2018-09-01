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
//            BluetoothProfile, BluetoothAdapter, IBluetoothManager, IBluetoothPbapClient, 
//            BluetoothDevice, IBluetoothStateChangeCallback

public final class BluetoothPbapClient
    implements BluetoothProfile
{

    static ServiceConnection _2D_get0(BluetoothPbapClient bluetoothpbapclient)
    {
        return bluetoothpbapclient.mConnection;
    }

    static Context _2D_get1(BluetoothPbapClient bluetoothpbapclient)
    {
        return bluetoothpbapclient.mContext;
    }

    static IBluetoothPbapClient _2D_get2(BluetoothPbapClient bluetoothpbapclient)
    {
        return bluetoothpbapclient.mService;
    }

    static BluetoothProfile.ServiceListener _2D_get3(BluetoothPbapClient bluetoothpbapclient)
    {
        return bluetoothpbapclient.mServiceListener;
    }

    static IBluetoothPbapClient _2D_set0(BluetoothPbapClient bluetoothpbapclient, IBluetoothPbapClient ibluetoothpbapclient)
    {
        bluetoothpbapclient.mService = ibluetoothpbapclient;
        return ibluetoothpbapclient;
    }

    static boolean _2D_wrap0(BluetoothPbapClient bluetoothpbapclient)
    {
        return bluetoothpbapclient.doBind();
    }

    BluetoothPbapClient(Context context, BluetoothProfile.ServiceListener servicelistener)
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
                Log.e("BluetoothPbapClient", "", context);
            }
        doBind();
    }

    private boolean doBind()
    {
        Intent intent = new Intent(android/bluetooth/IBluetoothPbapClient.getName());
        ComponentName componentname = intent.resolveSystemService(mContext.getPackageManager(), 0);
        intent.setComponent(componentname);
        if(componentname == null || mContext.bindServiceAsUser(intent, mConnection, 0, Process.myUserHandle()) ^ true)
        {
            Log.e("BluetoothPbapClient", (new StringBuilder()).append("Could not bind to Bluetooth PBAP Client Service with ").append(intent).toString());
            return false;
        } else
        {
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
        Log.d("BluetoothPbapClient", s);
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
        IBluetoothPbapClient ibluetoothpbapclient = mService;
        if(ibluetoothpbapclient == null)
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
        Log.e("BluetoothPbapClient", "", ((Throwable) (obj)));
          goto _L1
        obj;
        throw obj;
        Object obj1;
        obj1;
        Log.e("BluetoothPbapClient", "", ((Throwable) (obj1)));
          goto _L2
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
          goto _L1
    }

    public boolean connect(BluetoothDevice bluetoothdevice)
    {
        IBluetoothPbapClient ibluetoothpbapclient = mService;
        if(ibluetoothpbapclient != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetoothpbapclient.connect(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothPbapClient", Log.getStackTraceString(new Throwable()));
                return false;
            }
            return flag;
        }
        if(ibluetoothpbapclient == null)
            Log.w("BluetoothPbapClient", "Proxy not attached to service");
        return false;
    }

    public boolean disconnect(BluetoothDevice bluetoothdevice)
    {
        IBluetoothPbapClient ibluetoothpbapclient = mService;
        if(ibluetoothpbapclient != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            try
            {
                ibluetoothpbapclient.disconnect(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothPbapClient", Log.getStackTraceString(new Throwable()));
                return false;
            }
            return true;
        }
        if(ibluetoothpbapclient == null)
            Log.w("BluetoothPbapClient", "Proxy not attached to service");
        return false;
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

    public List getConnectedDevices()
    {
        Object obj = mService;
        if(obj != null && isEnabled())
        {
            try
            {
                obj = ((IBluetoothPbapClient) (obj)).getConnectedDevices();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("BluetoothPbapClient", Log.getStackTraceString(new Throwable()));
                return new ArrayList();
            }
            return ((List) (obj));
        }
        if(obj == null)
            Log.w("BluetoothPbapClient", "Proxy not attached to service");
        return new ArrayList();
    }

    public int getConnectionState(BluetoothDevice bluetoothdevice)
    {
        IBluetoothPbapClient ibluetoothpbapclient = mService;
        if(ibluetoothpbapclient != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            int i;
            try
            {
                i = ibluetoothpbapclient.getConnectionState(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothPbapClient", Log.getStackTraceString(new Throwable()));
                return 0;
            }
            return i;
        }
        if(ibluetoothpbapclient == null)
            Log.w("BluetoothPbapClient", "Proxy not attached to service");
        return 0;
    }

    public List getDevicesMatchingConnectionStates(int ai[])
    {
        IBluetoothPbapClient ibluetoothpbapclient = mService;
        if(ibluetoothpbapclient != null && isEnabled())
        {
            try
            {
                ai = ibluetoothpbapclient.getDevicesMatchingConnectionStates(ai);
            }
            // Misplaced declaration of an exception variable
            catch(int ai[])
            {
                Log.e("BluetoothPbapClient", Log.getStackTraceString(new Throwable()));
                return new ArrayList();
            }
            return ai;
        }
        if(ibluetoothpbapclient == null)
            Log.w("BluetoothPbapClient", "Proxy not attached to service");
        return new ArrayList();
    }

    public int getPriority(BluetoothDevice bluetoothdevice)
    {
        IBluetoothPbapClient ibluetoothpbapclient = mService;
        if(ibluetoothpbapclient != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            int i;
            try
            {
                i = ibluetoothpbapclient.getPriority(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothPbapClient", Log.getStackTraceString(new Throwable()));
                return 0;
            }
            return i;
        }
        if(ibluetoothpbapclient == null)
            Log.w("BluetoothPbapClient", "Proxy not attached to service");
        return 0;
    }

    public boolean setPriority(BluetoothDevice bluetoothdevice, int i)
    {
        IBluetoothPbapClient ibluetoothpbapclient = mService;
        if(ibluetoothpbapclient != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            if(i != 0 && i != 100)
                return false;
            boolean flag;
            try
            {
                flag = ibluetoothpbapclient.setPriority(bluetoothdevice, i);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothPbapClient", Log.getStackTraceString(new Throwable()));
                return false;
            }
            return flag;
        }
        if(ibluetoothpbapclient == null)
            Log.w("BluetoothPbapClient", "Proxy not attached to service");
        return false;
    }

    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.pbap.profile.action.CONNECTION_STATE_CHANGED";
    private static final boolean DBG = false;
    public static final int RESULT_CANCELED = 2;
    public static final int RESULT_FAILURE = 0;
    public static final int RESULT_SUCCESS = 1;
    public static final int STATE_ERROR = -1;
    private static final String TAG = "BluetoothPbapClient";
    private static final boolean VDBG = false;
    private BluetoothAdapter mAdapter;
    private final IBluetoothStateChangeCallback mBluetoothStateChangeCallback = new IBluetoothStateChangeCallback.Stub() {

        public void onBluetoothStateChange(boolean flag)
        {
            if(flag) goto _L2; else goto _L1
_L1:
            ServiceConnection serviceconnection = BluetoothPbapClient._2D_get0(BluetoothPbapClient.this);
            serviceconnection;
            JVM INSTR monitorenter ;
            BluetoothPbapClient._2D_set0(BluetoothPbapClient.this, null);
            BluetoothPbapClient._2D_get1(BluetoothPbapClient.this).unbindService(BluetoothPbapClient._2D_get0(BluetoothPbapClient.this));
_L3:
            serviceconnection;
            JVM INSTR monitorexit ;
            return;
            Object obj1;
            obj1;
            Log.e("BluetoothPbapClient", "", ((Throwable) (obj1)));
              goto _L3
            obj1;
            throw obj1;
_L2:
            ServiceConnection serviceconnection1 = BluetoothPbapClient._2D_get0(BluetoothPbapClient.this);
            serviceconnection1;
            JVM INSTR monitorenter ;
            serviceconnection = serviceconnection1;
            if(BluetoothPbapClient._2D_get2(BluetoothPbapClient.this) != null) goto _L3; else goto _L4
_L4:
            BluetoothPbapClient._2D_wrap0(BluetoothPbapClient.this);
            serviceconnection = serviceconnection1;
              goto _L3
            Object obj;
            obj;
            Log.e("BluetoothPbapClient", "", ((Throwable) (obj)));
            obj = serviceconnection1;
              goto _L3
            obj;
            throw obj;
        }

        final BluetoothPbapClient this$0;

            
            {
                this$0 = BluetoothPbapClient.this;
                super();
            }
    }
;
    private final ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            BluetoothPbapClient._2D_set0(BluetoothPbapClient.this, IBluetoothPbapClient.Stub.asInterface(Binder.allowBlocking(ibinder)));
            if(BluetoothPbapClient._2D_get3(BluetoothPbapClient.this) != null)
                BluetoothPbapClient._2D_get3(BluetoothPbapClient.this).onServiceConnected(17, BluetoothPbapClient.this);
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            BluetoothPbapClient._2D_set0(BluetoothPbapClient.this, null);
            if(BluetoothPbapClient._2D_get3(BluetoothPbapClient.this) != null)
                BluetoothPbapClient._2D_get3(BluetoothPbapClient.this).onServiceDisconnected(17);
        }

        final BluetoothPbapClient this$0;

            
            {
                this$0 = BluetoothPbapClient.this;
                super();
            }
    }
;
    private final Context mContext;
    private volatile IBluetoothPbapClient mService;
    private BluetoothProfile.ServiceListener mServiceListener;
}

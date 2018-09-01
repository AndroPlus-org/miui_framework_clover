// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.app.PendingIntent;
import android.content.*;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.bluetooth:
//            BluetoothProfile, BluetoothAdapter, IBluetoothManager, BluetoothDevice, 
//            IBluetoothMapClient, IBluetoothStateChangeCallback

public final class BluetoothMapClient
    implements BluetoothProfile
{

    static boolean _2D_get0()
    {
        return DBG;
    }

    static boolean _2D_get1()
    {
        return VDBG;
    }

    static ServiceConnection _2D_get2(BluetoothMapClient bluetoothmapclient)
    {
        return bluetoothmapclient.mConnection;
    }

    static Context _2D_get3(BluetoothMapClient bluetoothmapclient)
    {
        return bluetoothmapclient.mContext;
    }

    static IBluetoothMapClient _2D_get4(BluetoothMapClient bluetoothmapclient)
    {
        return bluetoothmapclient.mService;
    }

    static BluetoothProfile.ServiceListener _2D_get5(BluetoothMapClient bluetoothmapclient)
    {
        return bluetoothmapclient.mServiceListener;
    }

    static IBluetoothMapClient _2D_set0(BluetoothMapClient bluetoothmapclient, IBluetoothMapClient ibluetoothmapclient)
    {
        bluetoothmapclient.mService = ibluetoothmapclient;
        return ibluetoothmapclient;
    }

    BluetoothMapClient(Context context, BluetoothProfile.ServiceListener servicelistener)
    {
        if(DBG)
            Log.d("BluetoothMapClient", "Create BluetoothMapClient proxy object");
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
                Log.e("BluetoothMapClient", "", context);
            }
        doBind();
    }

    private boolean isEnabled()
    {
        BluetoothAdapter bluetoothadapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothadapter != null && bluetoothadapter.getState() == 12)
            return true;
        if(DBG)
            Log.d("BluetoothMapClient", "Bluetooth is Not enabled");
        return false;
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

    public void close()
    {
        Object obj;
        obj = mAdapter.getBluetoothManager();
        IBluetoothMapClient ibluetoothmapclient;
        if(obj != null)
            try
            {
                ((IBluetoothManager) (obj)).unregisterStateChangeCallback(mBluetoothStateChangeCallback);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("BluetoothMapClient", "", ((Throwable) (obj)));
            }
        obj = mConnection;
        obj;
        JVM INSTR monitorenter ;
        ibluetoothmapclient = mService;
        if(ibluetoothmapclient == null)
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
        Log.e("BluetoothMapClient", "", ((Throwable) (obj1)));
          goto _L1
        obj1;
        throw obj1;
    }

    public boolean connect(BluetoothDevice bluetoothdevice)
    {
        IBluetoothMapClient ibluetoothmapclient;
        if(DBG)
            Log.d("BluetoothMapClient", (new StringBuilder()).append("connect(").append(bluetoothdevice).append(")").append("for MAPS MCE").toString());
        ibluetoothmapclient = mService;
        if(ibluetoothmapclient == null) goto _L2; else goto _L1
_L1:
        boolean flag = ibluetoothmapclient.connect(bluetoothdevice);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothMapClient", bluetoothdevice.toString());
_L4:
        return false;
_L2:
        Log.w("BluetoothMapClient", "Proxy not attached to service");
        if(DBG)
            Log.d("BluetoothMapClient", Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean disconnect(BluetoothDevice bluetoothdevice)
    {
        IBluetoothMapClient ibluetoothmapclient;
        if(DBG)
            Log.d("BluetoothMapClient", (new StringBuilder()).append("disconnect(").append(bluetoothdevice).append(")").toString());
        ibluetoothmapclient = mService;
        if(ibluetoothmapclient == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_86;
        boolean flag = ibluetoothmapclient.disconnect(bluetoothdevice);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothMapClient", Log.getStackTraceString(new Throwable()));
        if(ibluetoothmapclient == null)
            Log.w("BluetoothMapClient", "Proxy not attached to service");
        return false;
    }

    boolean doBind()
    {
        Intent intent = new Intent(android/bluetooth/IBluetoothMapClient.getName());
        ComponentName componentname = intent.resolveSystemService(mContext.getPackageManager(), 0);
        intent.setComponent(componentname);
        if(componentname == null || mContext.bindServiceAsUser(intent, mConnection, 0, Process.myUserHandle()) ^ true)
        {
            Log.e("BluetoothMapClient", (new StringBuilder()).append("Could not bind to Bluetooth MAP MCE Service with ").append(intent).toString());
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

    public List getConnectedDevices()
    {
        if(DBG)
            Log.d("BluetoothMapClient", "getConnectedDevices()");
        Object obj = mService;
        if(obj != null && isEnabled())
        {
            try
            {
                obj = ((IBluetoothMapClient) (obj)).getConnectedDevices();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("BluetoothMapClient", Log.getStackTraceString(new Throwable()));
                return new ArrayList();
            }
            return ((List) (obj));
        }
        if(obj == null)
            Log.w("BluetoothMapClient", "Proxy not attached to service");
        return new ArrayList();
    }

    public int getConnectionState(BluetoothDevice bluetoothdevice)
    {
        if(DBG)
            Log.d("BluetoothMapClient", (new StringBuilder()).append("getConnectionState(").append(bluetoothdevice).append(")").toString());
        IBluetoothMapClient ibluetoothmapclient = mService;
        if(ibluetoothmapclient != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            int i;
            try
            {
                i = ibluetoothmapclient.getConnectionState(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothMapClient", Log.getStackTraceString(new Throwable()));
                return 0;
            }
            return i;
        }
        if(ibluetoothmapclient == null)
            Log.w("BluetoothMapClient", "Proxy not attached to service");
        return 0;
    }

    public List getDevicesMatchingConnectionStates(int ai[])
    {
        if(DBG)
            Log.d("BluetoothMapClient", "getDevicesMatchingStates()");
        IBluetoothMapClient ibluetoothmapclient = mService;
        if(ibluetoothmapclient != null && isEnabled())
        {
            try
            {
                ai = ibluetoothmapclient.getDevicesMatchingConnectionStates(ai);
            }
            // Misplaced declaration of an exception variable
            catch(int ai[])
            {
                Log.e("BluetoothMapClient", Log.getStackTraceString(new Throwable()));
                return new ArrayList();
            }
            return ai;
        }
        if(ibluetoothmapclient == null)
            Log.w("BluetoothMapClient", "Proxy not attached to service");
        return new ArrayList();
    }

    public int getPriority(BluetoothDevice bluetoothdevice)
    {
        if(VDBG)
            Log.d("BluetoothMapClient", (new StringBuilder()).append("getPriority(").append(bluetoothdevice).append(")").toString());
        IBluetoothMapClient ibluetoothmapclient = mService;
        if(ibluetoothmapclient != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            int i;
            try
            {
                i = ibluetoothmapclient.getPriority(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothMapClient", Log.getStackTraceString(new Throwable()));
                return 0;
            }
            return i;
        }
        if(ibluetoothmapclient == null)
            Log.w("BluetoothMapClient", "Proxy not attached to service");
        return 0;
    }

    public boolean getUnreadMessages(BluetoothDevice bluetoothdevice)
    {
        if(DBG)
            Log.d("BluetoothMapClient", (new StringBuilder()).append("getUnreadMessages(").append(bluetoothdevice).append(")").toString());
        IBluetoothMapClient ibluetoothmapclient = mService;
        if(ibluetoothmapclient != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetoothmapclient.getUnreadMessages(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothMapClient", Log.getStackTraceString(new Throwable()));
                return false;
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean isConnected(BluetoothDevice bluetoothdevice)
    {
        IBluetoothMapClient ibluetoothmapclient;
        if(VDBG)
            Log.d("BluetoothMapClient", (new StringBuilder()).append("isConnected(").append(bluetoothdevice).append(")").toString());
        ibluetoothmapclient = mService;
        if(ibluetoothmapclient == null) goto _L2; else goto _L1
_L1:
        boolean flag = ibluetoothmapclient.isConnected(bluetoothdevice);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothMapClient", bluetoothdevice.toString());
_L4:
        return false;
_L2:
        Log.w("BluetoothMapClient", "Proxy not attached to service");
        if(DBG)
            Log.d("BluetoothMapClient", Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean sendMessage(BluetoothDevice bluetoothdevice, Uri auri[], String s, PendingIntent pendingintent, PendingIntent pendingintent1)
    {
        if(DBG)
            Log.d("BluetoothMapClient", (new StringBuilder()).append("sendMessage(").append(bluetoothdevice).append(", ").append(auri).append(", ").append(s).toString());
        IBluetoothMapClient ibluetoothmapclient = mService;
        if(ibluetoothmapclient != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetoothmapclient.sendMessage(bluetoothdevice, auri, s, pendingintent, pendingintent1);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothMapClient", Log.getStackTraceString(new Throwable()));
                return false;
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean setPriority(BluetoothDevice bluetoothdevice, int i)
    {
        if(DBG)
            Log.d("BluetoothMapClient", (new StringBuilder()).append("setPriority(").append(bluetoothdevice).append(", ").append(i).append(")").toString());
        IBluetoothMapClient ibluetoothmapclient = mService;
        if(ibluetoothmapclient != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            if(i != 0 && i != 100)
                return false;
            boolean flag;
            try
            {
                flag = ibluetoothmapclient.setPriority(bluetoothdevice, i);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothMapClient", Log.getStackTraceString(new Throwable()));
                return false;
            }
            return flag;
        }
        if(ibluetoothmapclient == null)
            Log.w("BluetoothMapClient", "Proxy not attached to service");
        return false;
    }

    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.mapmce.profile.action.CONNECTION_STATE_CHANGED";
    public static final String ACTION_MESSAGE_DELIVERED_SUCCESSFULLY = "android.bluetooth.mapmce.profile.action.MESSAGE_DELIVERED_SUCCESSFULLY";
    public static final String ACTION_MESSAGE_RECEIVED = "android.bluetooth.mapmce.profile.action.MESSAGE_RECEIVED";
    public static final String ACTION_MESSAGE_SENT_SUCCESSFULLY = "android.bluetooth.mapmce.profile.action.MESSAGE_SENT_SUCCESSFULLY";
    private static final boolean DBG = Log.isLoggable("BluetoothMapClient", 3);
    public static final String EXTRA_MESSAGE_HANDLE = "android.bluetooth.mapmce.profile.extra.MESSAGE_HANDLE";
    public static final String EXTRA_SENDER_CONTACT_NAME = "android.bluetooth.mapmce.profile.extra.SENDER_CONTACT_NAME";
    public static final String EXTRA_SENDER_CONTACT_URI = "android.bluetooth.mapmce.profile.extra.SENDER_CONTACT_URI";
    public static final int RESULT_CANCELED = 2;
    public static final int RESULT_FAILURE = 0;
    public static final int RESULT_SUCCESS = 1;
    public static final int STATE_ERROR = -1;
    private static final String TAG = "BluetoothMapClient";
    private static final boolean VDBG = Log.isLoggable("BluetoothMapClient", 2);
    private BluetoothAdapter mAdapter;
    private final IBluetoothStateChangeCallback mBluetoothStateChangeCallback = new IBluetoothStateChangeCallback.Stub() {

        public void onBluetoothStateChange(boolean flag)
        {
            if(BluetoothMapClient._2D_get0())
                Log.d("BluetoothMapClient", (new StringBuilder()).append("onBluetoothStateChange: up=").append(flag).toString());
            if(flag) goto _L2; else goto _L1
_L1:
            if(BluetoothMapClient._2D_get1())
                Log.d("BluetoothMapClient", "Unbinding service...");
            ServiceConnection serviceconnection = BluetoothMapClient._2D_get2(BluetoothMapClient.this);
            serviceconnection;
            JVM INSTR monitorenter ;
            BluetoothMapClient._2D_set0(BluetoothMapClient.this, null);
            BluetoothMapClient._2D_get3(BluetoothMapClient.this).unbindService(BluetoothMapClient._2D_get2(BluetoothMapClient.this));
_L3:
            serviceconnection;
            JVM INSTR monitorexit ;
            return;
            Object obj1;
            obj1;
            Log.e("BluetoothMapClient", "", ((Throwable) (obj1)));
              goto _L3
            obj1;
            throw obj1;
_L2:
            ServiceConnection serviceconnection1 = BluetoothMapClient._2D_get2(BluetoothMapClient.this);
            serviceconnection1;
            JVM INSTR monitorenter ;
            serviceconnection = serviceconnection1;
            if(BluetoothMapClient._2D_get4(BluetoothMapClient.this) != null) goto _L3; else goto _L4
_L4:
            if(BluetoothMapClient._2D_get1())
                Log.d("BluetoothMapClient", "Binding service...");
            doBind();
            serviceconnection = serviceconnection1;
              goto _L3
            Object obj;
            obj;
            Log.e("BluetoothMapClient", "", ((Throwable) (obj)));
            obj = serviceconnection1;
              goto _L3
            obj;
            throw obj;
        }

        final BluetoothMapClient this$0;

            
            {
                this$0 = BluetoothMapClient.this;
                super();
            }
    }
;
    private final ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            if(BluetoothMapClient._2D_get0())
                Log.d("BluetoothMapClient", "Proxy object connected");
            BluetoothMapClient._2D_set0(BluetoothMapClient.this, IBluetoothMapClient.Stub.asInterface(ibinder));
            if(BluetoothMapClient._2D_get5(BluetoothMapClient.this) != null)
                BluetoothMapClient._2D_get5(BluetoothMapClient.this).onServiceConnected(18, BluetoothMapClient.this);
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            if(BluetoothMapClient._2D_get0())
                Log.d("BluetoothMapClient", "Proxy object disconnected");
            BluetoothMapClient._2D_set0(BluetoothMapClient.this, null);
            if(BluetoothMapClient._2D_get5(BluetoothMapClient.this) != null)
                BluetoothMapClient._2D_get5(BluetoothMapClient.this).onServiceDisconnected(18);
        }

        final BluetoothMapClient this$0;

            
            {
                this$0 = BluetoothMapClient.this;
                super();
            }
    }
;
    private final Context mContext;
    private volatile IBluetoothMapClient mService;
    private BluetoothProfile.ServiceListener mServiceListener;

}

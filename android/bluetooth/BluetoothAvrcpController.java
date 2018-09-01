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
//            IBluetoothAvrcpController, IBluetoothStateChangeCallback, BluetoothAvrcpPlayerSettings

public final class BluetoothAvrcpController
    implements BluetoothProfile
{

    static ServiceConnection _2D_get0(BluetoothAvrcpController bluetoothavrcpcontroller)
    {
        return bluetoothavrcpcontroller.mConnection;
    }

    static Context _2D_get1(BluetoothAvrcpController bluetoothavrcpcontroller)
    {
        return bluetoothavrcpcontroller.mContext;
    }

    static IBluetoothAvrcpController _2D_get2(BluetoothAvrcpController bluetoothavrcpcontroller)
    {
        return bluetoothavrcpcontroller.mService;
    }

    static BluetoothProfile.ServiceListener _2D_get3(BluetoothAvrcpController bluetoothavrcpcontroller)
    {
        return bluetoothavrcpcontroller.mServiceListener;
    }

    static IBluetoothAvrcpController _2D_set0(BluetoothAvrcpController bluetoothavrcpcontroller, IBluetoothAvrcpController ibluetoothavrcpcontroller)
    {
        bluetoothavrcpcontroller.mService = ibluetoothavrcpcontroller;
        return ibluetoothavrcpcontroller;
    }

    BluetoothAvrcpController(Context context, BluetoothProfile.ServiceListener servicelistener)
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
                Log.e("BluetoothAvrcpController", "", context);
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
        Log.d("BluetoothAvrcpController", s);
    }

    void close()
    {
        Object obj;
        mServiceListener = null;
        obj = mAdapter.getBluetoothManager();
        IBluetoothAvrcpController ibluetoothavrcpcontroller;
        if(obj != null)
            try
            {
                ((IBluetoothManager) (obj)).unregisterStateChangeCallback(mBluetoothStateChangeCallback);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("BluetoothAvrcpController", "", ((Throwable) (obj)));
            }
        obj = mConnection;
        obj;
        JVM INSTR monitorenter ;
        ibluetoothavrcpcontroller = mService;
        if(ibluetoothavrcpcontroller == null)
            break MISSING_BLOCK_LABEL_59;
        mService = null;
        mContext.unbindService(mConnection);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        obj1;
        Log.e("BluetoothAvrcpController", "", ((Throwable) (obj1)));
          goto _L1
        obj1;
        throw obj1;
    }

    boolean doBind()
    {
        Intent intent = new Intent(android/bluetooth/IBluetoothAvrcpController.getName());
        ComponentName componentname = intent.resolveSystemService(mContext.getPackageManager(), 0);
        intent.setComponent(componentname);
        if(componentname == null || mContext.bindServiceAsUser(intent, mConnection, 0, Process.myUserHandle()) ^ true)
        {
            Log.e("BluetoothAvrcpController", (new StringBuilder()).append("Could not bind to Bluetooth AVRCP Controller Service with ").append(intent).toString());
            return false;
        } else
        {
            return true;
        }
    }

    public void finalize()
    {
        close();
    }

    public List getConnectedDevices()
    {
        Object obj = mService;
        if(obj != null && isEnabled())
        {
            try
            {
                obj = ((IBluetoothAvrcpController) (obj)).getConnectedDevices();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("BluetoothAvrcpController", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return new ArrayList();
            }
            return ((List) (obj));
        }
        if(obj == null)
            Log.w("BluetoothAvrcpController", "Proxy not attached to service");
        return new ArrayList();
    }

    public int getConnectionState(BluetoothDevice bluetoothdevice)
    {
        IBluetoothAvrcpController ibluetoothavrcpcontroller = mService;
        if(ibluetoothavrcpcontroller != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            int i;
            try
            {
                i = ibluetoothavrcpcontroller.getConnectionState(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothAvrcpController", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return 0;
            }
            return i;
        }
        if(ibluetoothavrcpcontroller == null)
            Log.w("BluetoothAvrcpController", "Proxy not attached to service");
        return 0;
    }

    public List getDevicesMatchingConnectionStates(int ai[])
    {
        IBluetoothAvrcpController ibluetoothavrcpcontroller = mService;
        if(ibluetoothavrcpcontroller != null && isEnabled())
        {
            try
            {
                ai = ibluetoothavrcpcontroller.getDevicesMatchingConnectionStates(ai);
            }
            // Misplaced declaration of an exception variable
            catch(int ai[])
            {
                Log.e("BluetoothAvrcpController", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return new ArrayList();
            }
            return ai;
        }
        if(ibluetoothavrcpcontroller == null)
            Log.w("BluetoothAvrcpController", "Proxy not attached to service");
        return new ArrayList();
    }

    public BluetoothAvrcpPlayerSettings getPlayerSettings(BluetoothDevice bluetoothdevice)
    {
        Object obj = null;
        IBluetoothAvrcpController ibluetoothavrcpcontroller = mService;
        BluetoothAvrcpPlayerSettings bluetoothavrcpplayersettings = obj;
        if(ibluetoothavrcpcontroller != null)
        {
            bluetoothavrcpplayersettings = obj;
            if(isEnabled())
                try
                {
                    bluetoothavrcpplayersettings = ibluetoothavrcpcontroller.getPlayerSettings(bluetoothdevice);
                }
                // Misplaced declaration of an exception variable
                catch(BluetoothDevice bluetoothdevice)
                {
                    Log.e("BluetoothAvrcpController", (new StringBuilder()).append("Error talking to BT service in getMetadata() ").append(bluetoothdevice).toString());
                    return null;
                }
        }
        return bluetoothavrcpplayersettings;
    }

    public void sendGroupNavigationCmd(BluetoothDevice bluetoothdevice, int i, int j)
    {
        Log.d("BluetoothAvrcpController", (new StringBuilder()).append("sendGroupNavigationCmd dev = ").append(bluetoothdevice).append(" key ").append(i).append(" State = ").append(j).toString());
        IBluetoothAvrcpController ibluetoothavrcpcontroller = mService;
        if(ibluetoothavrcpcontroller != null && isEnabled())
        {
            try
            {
                ibluetoothavrcpcontroller.sendGroupNavigationCmd(bluetoothdevice, i, j);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothAvrcpController", "Error talking to BT service in sendGroupNavigationCmd()", bluetoothdevice);
            }
            return;
        }
        if(ibluetoothavrcpcontroller == null)
            Log.w("BluetoothAvrcpController", "Proxy not attached to service");
    }

    public boolean setPlayerApplicationSetting(BluetoothAvrcpPlayerSettings bluetoothavrcpplayersettings)
    {
        IBluetoothAvrcpController ibluetoothavrcpcontroller = mService;
        if(ibluetoothavrcpcontroller != null && isEnabled())
        {
            boolean flag;
            try
            {
                flag = ibluetoothavrcpcontroller.setPlayerApplicationSetting(bluetoothavrcpplayersettings);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothAvrcpPlayerSettings bluetoothavrcpplayersettings)
            {
                Log.e("BluetoothAvrcpController", (new StringBuilder()).append("Error talking to BT service in setPlayerApplicationSetting() ").append(bluetoothavrcpplayersettings).toString());
                return false;
            }
            return flag;
        }
        if(ibluetoothavrcpcontroller == null)
            Log.w("BluetoothAvrcpController", "Proxy not attached to service");
        return false;
    }

    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.avrcp-controller.profile.action.CONNECTION_STATE_CHANGED";
    public static final String ACTION_PLAYER_SETTING = "android.bluetooth.avrcp-controller.profile.action.PLAYER_SETTING";
    private static final boolean DBG = false;
    public static final String EXTRA_PLAYER_SETTING = "android.bluetooth.avrcp-controller.profile.extra.PLAYER_SETTING";
    private static final String TAG = "BluetoothAvrcpController";
    private static final boolean VDBG = false;
    private BluetoothAdapter mAdapter;
    private final IBluetoothStateChangeCallback mBluetoothStateChangeCallback = new IBluetoothStateChangeCallback.Stub() {

        public void onBluetoothStateChange(boolean flag)
        {
            if(flag) goto _L2; else goto _L1
_L1:
            ServiceConnection serviceconnection = BluetoothAvrcpController._2D_get0(BluetoothAvrcpController.this);
            serviceconnection;
            JVM INSTR monitorenter ;
            BluetoothAvrcpController._2D_set0(BluetoothAvrcpController.this, null);
            BluetoothAvrcpController._2D_get1(BluetoothAvrcpController.this).unbindService(BluetoothAvrcpController._2D_get0(BluetoothAvrcpController.this));
_L3:
            serviceconnection;
            JVM INSTR monitorexit ;
            return;
            Object obj1;
            obj1;
            Log.e("BluetoothAvrcpController", "", ((Throwable) (obj1)));
              goto _L3
            obj1;
            throw obj1;
_L2:
            ServiceConnection serviceconnection1 = BluetoothAvrcpController._2D_get0(BluetoothAvrcpController.this);
            serviceconnection1;
            JVM INSTR monitorenter ;
            serviceconnection = serviceconnection1;
            if(BluetoothAvrcpController._2D_get2(BluetoothAvrcpController.this) != null) goto _L3; else goto _L4
_L4:
            doBind();
            serviceconnection = serviceconnection1;
              goto _L3
            Object obj;
            obj;
            Log.e("BluetoothAvrcpController", "", ((Throwable) (obj)));
            obj = serviceconnection1;
              goto _L3
            obj;
            throw obj;
        }

        final BluetoothAvrcpController this$0;

            
            {
                this$0 = BluetoothAvrcpController.this;
                super();
            }
    }
;
    private final ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            BluetoothAvrcpController._2D_set0(BluetoothAvrcpController.this, IBluetoothAvrcpController.Stub.asInterface(Binder.allowBlocking(ibinder)));
            if(BluetoothAvrcpController._2D_get3(BluetoothAvrcpController.this) != null)
                BluetoothAvrcpController._2D_get3(BluetoothAvrcpController.this).onServiceConnected(12, BluetoothAvrcpController.this);
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            BluetoothAvrcpController._2D_set0(BluetoothAvrcpController.this, null);
            if(BluetoothAvrcpController._2D_get3(BluetoothAvrcpController.this) != null)
                BluetoothAvrcpController._2D_get3(BluetoothAvrcpController.this).onServiceDisconnected(12);
        }

        final BluetoothAvrcpController this$0;

            
            {
                this$0 = BluetoothAvrcpController.this;
                super();
            }
    }
;
    private Context mContext;
    private volatile IBluetoothAvrcpController mService;
    private BluetoothProfile.ServiceListener mServiceListener;
}

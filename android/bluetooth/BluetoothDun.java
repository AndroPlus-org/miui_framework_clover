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
//            IBluetoothDun, IBluetoothStateChangeCallback

public final class BluetoothDun
    implements BluetoothProfile
{

    static ServiceConnection _2D_get0(BluetoothDun bluetoothdun)
    {
        return bluetoothdun.mConnection;
    }

    static Context _2D_get1(BluetoothDun bluetoothdun)
    {
        return bluetoothdun.mContext;
    }

    static IBluetoothDun _2D_get2(BluetoothDun bluetoothdun)
    {
        return bluetoothdun.mDunService;
    }

    static BluetoothProfile.ServiceListener _2D_get3(BluetoothDun bluetoothdun)
    {
        return bluetoothdun.mServiceListener;
    }

    static IBluetoothDun _2D_set0(BluetoothDun bluetoothdun, IBluetoothDun ibluetoothdun)
    {
        bluetoothdun.mDunService = ibluetoothdun;
        return ibluetoothdun;
    }

    BluetoothDun(Context context, BluetoothProfile.ServiceListener servicelistener)
    {
        mStateChangeCallback = new IBluetoothStateChangeCallback.Stub() {

            public void onBluetoothStateChange(boolean flag)
            {
                Log.d("BluetoothDun", (new StringBuilder()).append("onBluetoothStateChange on: ").append(flag).toString());
                if(!flag) goto _L2; else goto _L1
_L1:
                if(BluetoothDun._2D_get2(BluetoothDun.this) == null)
                {
                    Log.d("BluetoothDun", "onBluetoothStateChange call bindService");
                    doBind();
                }
_L3:
                return;
                Object obj;
                obj;
                Log.e("BluetoothDun", "onBluetoothStateChange: could not bind to DUN service: ", ((Throwable) (obj)));
                  goto _L3
                obj;
                Log.e("BluetoothDun", "onBluetoothStateChange: could not bind to DUN service: ", ((Throwable) (obj)));
                  goto _L3
_L2:
                ServiceConnection serviceconnection = BluetoothDun._2D_get0(BluetoothDun.this);
                serviceconnection;
                JVM INSTR monitorenter ;
                IBluetoothDun ibluetoothdun = BluetoothDun._2D_get2(BluetoothDun.this);
                if(ibluetoothdun == null)
                    break MISSING_BLOCK_LABEL_130;
                BluetoothDun._2D_set0(BluetoothDun.this, null);
                BluetoothDun._2D_get1(BluetoothDun.this).unbindService(BluetoothDun._2D_get0(BluetoothDun.this));
_L4:
                serviceconnection;
                JVM INSTR monitorexit ;
                  goto _L3
                Object obj1;
                obj1;
                Log.e("BluetoothDun", "", ((Throwable) (obj1)));
                  goto _L4
                obj1;
                throw obj1;
                  goto _L3
            }

            final BluetoothDun this$0;

            
            {
                this$0 = BluetoothDun.this;
                super();
            }
        }
;
        mConnection = new ServiceConnection() {

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                BluetoothDun._2D_set0(BluetoothDun.this, IBluetoothDun.Stub.asInterface(ibinder));
                if(BluetoothDun._2D_get3(BluetoothDun.this) != null)
                    BluetoothDun._2D_get3(BluetoothDun.this).onServiceConnected(20, BluetoothDun.this);
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                BluetoothDun._2D_set0(BluetoothDun.this, null);
                if(BluetoothDun._2D_get3(BluetoothDun.this) != null)
                    BluetoothDun._2D_get3(BluetoothDun.this).onServiceDisconnected(20);
            }

            final BluetoothDun this$0;

            
            {
                this$0 = BluetoothDun.this;
                super();
            }
        }
;
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
            Log.w("BluetoothDun", "Unable to register BluetoothStateChangeCallback", context);
        }
        Log.d("BluetoothDun", "BluetoothDun() call bindService");
        doBind();
    }

    private boolean isEnabled()
    {
        return mAdapter.getState() == 12;
    }

    private boolean isValidDevice(BluetoothDevice bluetoothdevice)
    {
        if(bluetoothdevice == null)
            return false;
        return BluetoothAdapter.checkBluetoothAddress(bluetoothdevice.getAddress());
    }

    private static void log(String s)
    {
        Log.d("BluetoothDun", s);
    }

    void close()
    {
        Object obj;
        mServiceListener = null;
        obj = mAdapter.getBluetoothManager();
        IBluetoothDun ibluetoothdun;
        if(obj != null)
            try
            {
                ((IBluetoothManager) (obj)).unregisterStateChangeCallback(mStateChangeCallback);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.w("BluetoothDun", "Unable to unregister BluetoothStateChangeCallback", ((Throwable) (obj)));
            }
        obj = mConnection;
        obj;
        JVM INSTR monitorenter ;
        ibluetoothdun = mDunService;
        if(ibluetoothdun == null)
            break MISSING_BLOCK_LABEL_59;
        mDunService = null;
        mContext.unbindService(mConnection);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        obj1;
        Log.e("BluetoothDun", "", ((Throwable) (obj1)));
          goto _L1
        obj1;
        throw obj1;
    }

    public boolean disconnect(BluetoothDevice bluetoothdevice)
    {
        if(mDunService != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = mDunService.disconnect(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothDun", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return false;
            }
            return flag;
        }
        if(mDunService == null)
            Log.w("BluetoothDun", "Proxy not attached to service");
        return false;
    }

    boolean doBind()
    {
        Intent intent = new Intent(android/bluetooth/IBluetoothDun.getName());
        ComponentName componentname = intent.resolveSystemService(mContext.getPackageManager(), 0);
        intent.setComponent(componentname);
        if(componentname == null || mContext.bindServiceAsUser(intent, mConnection, 0, Process.myUserHandle()) ^ true)
        {
            Log.e("BluetoothDun", (new StringBuilder()).append("Could not bind to Bluetooth Dun Service with ").append(intent).toString());
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
        if(mDunService != null && isEnabled())
        {
            List list;
            try
            {
                list = mDunService.getConnectedDevices();
            }
            catch(RemoteException remoteexception)
            {
                Log.e("BluetoothDun", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return new ArrayList();
            }
            return list;
        }
        if(mDunService == null)
            Log.w("BluetoothDun", "Proxy not attached to service");
        return new ArrayList();
    }

    public int getConnectionState(BluetoothDevice bluetoothdevice)
    {
        if(mDunService != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            int i;
            try
            {
                i = mDunService.getConnectionState(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothDun", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return 0;
            }
            return i;
        }
        if(mDunService == null)
            Log.w("BluetoothDun", "Proxy not attached to service");
        return 0;
    }

    public List getDevicesMatchingConnectionStates(int ai[])
    {
        if(mDunService != null && isEnabled())
        {
            try
            {
                ai = mDunService.getDevicesMatchingConnectionStates(ai);
            }
            // Misplaced declaration of an exception variable
            catch(int ai[])
            {
                Log.e("BluetoothDun", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return new ArrayList();
            }
            return ai;
        }
        if(mDunService == null)
            Log.w("BluetoothDun", "Proxy not attached to service");
        return new ArrayList();
    }

    public static final String ACTION_CONNECTION_STATE_CHANGED = "codeaurora.bluetooth.dun.profile.action.CONNECTION_STATE_CHANGED";
    private static final boolean DBG = false;
    private static final String TAG = "BluetoothDun";
    private static final boolean VDBG = false;
    private BluetoothAdapter mAdapter;
    private ServiceConnection mConnection;
    private Context mContext;
    private IBluetoothDun mDunService;
    private BluetoothProfile.ServiceListener mServiceListener;
    private IBluetoothStateChangeCallback mStateChangeCallback;
}

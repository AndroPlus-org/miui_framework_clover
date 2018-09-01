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
//            IBluetoothA2dpSink, IBluetoothStateChangeCallback, BluetoothAudioConfig

public final class BluetoothA2dpSink
    implements BluetoothProfile
{

    static ServiceConnection _2D_get0(BluetoothA2dpSink bluetootha2dpsink)
    {
        return bluetootha2dpsink.mConnection;
    }

    static Context _2D_get1(BluetoothA2dpSink bluetootha2dpsink)
    {
        return bluetootha2dpsink.mContext;
    }

    static IBluetoothA2dpSink _2D_get2(BluetoothA2dpSink bluetootha2dpsink)
    {
        return bluetootha2dpsink.mService;
    }

    static BluetoothProfile.ServiceListener _2D_get3(BluetoothA2dpSink bluetootha2dpsink)
    {
        return bluetootha2dpsink.mServiceListener;
    }

    static IBluetoothA2dpSink _2D_set0(BluetoothA2dpSink bluetootha2dpsink, IBluetoothA2dpSink ibluetootha2dpsink)
    {
        bluetootha2dpsink.mService = ibluetootha2dpsink;
        return ibluetootha2dpsink;
    }

    BluetoothA2dpSink(Context context, BluetoothProfile.ServiceListener servicelistener)
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
                Log.e("BluetoothA2dpSink", "", context);
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
        Log.d("BluetoothA2dpSink", s);
    }

    public static String stateToString(int i)
    {
        switch(i)
        {
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        default:
            return (new StringBuilder()).append("<unknown state ").append(i).append(">").toString();

        case 0: // '\0'
            return "disconnected";

        case 1: // '\001'
            return "connecting";

        case 2: // '\002'
            return "connected";

        case 3: // '\003'
            return "disconnecting";

        case 10: // '\n'
            return "playing";

        case 11: // '\013'
            return "not playing";
        }
    }

    void close()
    {
        Object obj;
        mServiceListener = null;
        obj = mAdapter.getBluetoothManager();
        IBluetoothA2dpSink ibluetootha2dpsink;
        if(obj != null)
            try
            {
                ((IBluetoothManager) (obj)).unregisterStateChangeCallback(mBluetoothStateChangeCallback);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("BluetoothA2dpSink", "", ((Throwable) (obj)));
            }
        obj = mConnection;
        obj;
        JVM INSTR monitorenter ;
        ibluetootha2dpsink = mService;
        if(ibluetootha2dpsink == null)
            break MISSING_BLOCK_LABEL_59;
        mService = null;
        mContext.unbindService(mConnection);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        obj1;
        Log.e("BluetoothA2dpSink", "", ((Throwable) (obj1)));
          goto _L1
        obj1;
        throw obj1;
    }

    public boolean connect(BluetoothDevice bluetoothdevice)
    {
        log((new StringBuilder()).append("connect(").append(bluetoothdevice).append(")").toString());
        IBluetoothA2dpSink ibluetootha2dpsink = mService;
        if(ibluetootha2dpsink != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetootha2dpsink.connect(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothA2dpSink", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return false;
            }
            return flag;
        }
        if(ibluetootha2dpsink == null)
            Log.w("BluetoothA2dpSink", "Proxy not attached to service");
        return false;
    }

    public boolean disconnect(BluetoothDevice bluetoothdevice)
    {
        log((new StringBuilder()).append("disconnect(").append(bluetoothdevice).append(")").toString());
        IBluetoothA2dpSink ibluetootha2dpsink = mService;
        if(ibluetootha2dpsink != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetootha2dpsink.disconnect(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothA2dpSink", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return false;
            }
            return flag;
        }
        if(ibluetootha2dpsink == null)
            Log.w("BluetoothA2dpSink", "Proxy not attached to service");
        return false;
    }

    boolean doBind()
    {
        Intent intent = new Intent(android/bluetooth/IBluetoothA2dpSink.getName());
        ComponentName componentname = intent.resolveSystemService(mContext.getPackageManager(), 0);
        intent.setComponent(componentname);
        if(componentname == null || mContext.bindServiceAsUser(intent, mConnection, 0, Process.myUserHandle()) ^ true)
        {
            Log.e("BluetoothA2dpSink", (new StringBuilder()).append("Could not bind to Bluetooth A2DP Service with ").append(intent).toString());
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

    public BluetoothAudioConfig getAudioConfig(BluetoothDevice bluetoothdevice)
    {
        IBluetoothA2dpSink ibluetootha2dpsink = mService;
        if(ibluetootha2dpsink != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            try
            {
                bluetoothdevice = ibluetootha2dpsink.getAudioConfig(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothA2dpSink", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return null;
            }
            return bluetoothdevice;
        }
        if(ibluetootha2dpsink == null)
            Log.w("BluetoothA2dpSink", "Proxy not attached to service");
        return null;
    }

    public List getConnectedDevices()
    {
        Object obj = mService;
        if(obj != null && isEnabled())
        {
            try
            {
                obj = ((IBluetoothA2dpSink) (obj)).getConnectedDevices();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("BluetoothA2dpSink", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return new ArrayList();
            }
            return ((List) (obj));
        }
        if(obj == null)
            Log.w("BluetoothA2dpSink", "Proxy not attached to service");
        return new ArrayList();
    }

    public int getConnectionState(BluetoothDevice bluetoothdevice)
    {
        IBluetoothA2dpSink ibluetootha2dpsink = mService;
        if(ibluetootha2dpsink != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            int i;
            try
            {
                i = ibluetootha2dpsink.getConnectionState(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothA2dpSink", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return 0;
            }
            return i;
        }
        if(ibluetootha2dpsink == null)
            Log.w("BluetoothA2dpSink", "Proxy not attached to service");
        return 0;
    }

    public List getDevicesMatchingConnectionStates(int ai[])
    {
        IBluetoothA2dpSink ibluetootha2dpsink = mService;
        if(ibluetootha2dpsink != null && isEnabled())
        {
            try
            {
                ai = ibluetootha2dpsink.getDevicesMatchingConnectionStates(ai);
            }
            // Misplaced declaration of an exception variable
            catch(int ai[])
            {
                Log.e("BluetoothA2dpSink", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return new ArrayList();
            }
            return ai;
        }
        if(ibluetootha2dpsink == null)
            Log.w("BluetoothA2dpSink", "Proxy not attached to service");
        return new ArrayList();
    }

    public int getPriority(BluetoothDevice bluetoothdevice)
    {
        IBluetoothA2dpSink ibluetootha2dpsink = mService;
        if(ibluetootha2dpsink != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            int i;
            try
            {
                i = ibluetootha2dpsink.getPriority(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothA2dpSink", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return 0;
            }
            return i;
        }
        if(ibluetootha2dpsink == null)
            Log.w("BluetoothA2dpSink", "Proxy not attached to service");
        return 0;
    }

    public boolean isA2dpPlaying(BluetoothDevice bluetoothdevice)
    {
        IBluetoothA2dpSink ibluetootha2dpsink = mService;
        if(ibluetootha2dpsink != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetootha2dpsink.isA2dpPlaying(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothA2dpSink", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return false;
            }
            return flag;
        }
        if(ibluetootha2dpsink == null)
            Log.w("BluetoothA2dpSink", "Proxy not attached to service");
        return false;
    }

    public boolean setPriority(BluetoothDevice bluetoothdevice, int i)
    {
        log((new StringBuilder()).append("setPriority(").append(bluetoothdevice).append(", ").append(i).append(")").toString());
        IBluetoothA2dpSink ibluetootha2dpsink = mService;
        if(ibluetootha2dpsink != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            if(i != 0 && i != 100)
                return false;
            boolean flag;
            try
            {
                flag = ibluetootha2dpsink.setPriority(bluetoothdevice, i);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothA2dpSink", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return false;
            }
            return flag;
        }
        if(ibluetootha2dpsink == null)
            Log.w("BluetoothA2dpSink", "Proxy not attached to service");
        return false;
    }

    public static final String ACTION_AUDIO_CONFIG_CHANGED = "android.bluetooth.a2dp-sink.profile.action.AUDIO_CONFIG_CHANGED";
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED";
    public static final String ACTION_PLAYING_STATE_CHANGED = "android.bluetooth.a2dp-sink.profile.action.PLAYING_STATE_CHANGED";
    private static final boolean DBG = true;
    public static final String EXTRA_AUDIO_CONFIG = "android.bluetooth.a2dp-sink.profile.extra.AUDIO_CONFIG";
    public static final int STATE_NOT_PLAYING = 11;
    public static final int STATE_PLAYING = 10;
    private static final String TAG = "BluetoothA2dpSink";
    private static final boolean VDBG = false;
    private BluetoothAdapter mAdapter;
    private final IBluetoothStateChangeCallback mBluetoothStateChangeCallback = new IBluetoothStateChangeCallback.Stub() {

        public void onBluetoothStateChange(boolean flag)
        {
            Log.d("BluetoothA2dpSink", (new StringBuilder()).append("onBluetoothStateChange: up=").append(flag).toString());
            if(flag) goto _L2; else goto _L1
_L1:
            ServiceConnection serviceconnection = BluetoothA2dpSink._2D_get0(BluetoothA2dpSink.this);
            serviceconnection;
            JVM INSTR monitorenter ;
            BluetoothA2dpSink._2D_set0(BluetoothA2dpSink.this, null);
            BluetoothA2dpSink._2D_get1(BluetoothA2dpSink.this).unbindService(BluetoothA2dpSink._2D_get0(BluetoothA2dpSink.this));
_L3:
            serviceconnection;
            JVM INSTR monitorexit ;
            return;
            Object obj1;
            obj1;
            Log.e("BluetoothA2dpSink", "", ((Throwable) (obj1)));
              goto _L3
            obj1;
            throw obj1;
_L2:
            ServiceConnection serviceconnection1 = BluetoothA2dpSink._2D_get0(BluetoothA2dpSink.this);
            serviceconnection1;
            JVM INSTR monitorenter ;
            serviceconnection = serviceconnection1;
            if(BluetoothA2dpSink._2D_get2(BluetoothA2dpSink.this) != null) goto _L3; else goto _L4
_L4:
            doBind();
            serviceconnection = serviceconnection1;
              goto _L3
            Object obj;
            obj;
            Log.e("BluetoothA2dpSink", "", ((Throwable) (obj)));
            obj = serviceconnection1;
              goto _L3
            obj;
            throw obj;
        }

        final BluetoothA2dpSink this$0;

            
            {
                this$0 = BluetoothA2dpSink.this;
                super();
            }
    }
;
    private final ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            Log.d("BluetoothA2dpSink", "Proxy object connected");
            BluetoothA2dpSink._2D_set0(BluetoothA2dpSink.this, IBluetoothA2dpSink.Stub.asInterface(Binder.allowBlocking(ibinder)));
            if(BluetoothA2dpSink._2D_get3(BluetoothA2dpSink.this) != null)
                BluetoothA2dpSink._2D_get3(BluetoothA2dpSink.this).onServiceConnected(11, BluetoothA2dpSink.this);
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            Log.d("BluetoothA2dpSink", "Proxy object disconnected");
            BluetoothA2dpSink._2D_set0(BluetoothA2dpSink.this, null);
            if(BluetoothA2dpSink._2D_get3(BluetoothA2dpSink.this) != null)
                BluetoothA2dpSink._2D_get3(BluetoothA2dpSink.this).onServiceDisconnected(11);
        }

        final BluetoothA2dpSink this$0;

            
            {
                this$0 = BluetoothA2dpSink.this;
                super();
            }
    }
;
    private Context mContext;
    private volatile IBluetoothA2dpSink mService;
    private BluetoothProfile.ServiceListener mServiceListener;
}

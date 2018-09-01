// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.content.*;
import android.os.*;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// Referenced classes of package android.bluetooth:
//            BluetoothProfile, BluetoothAdapter, IBluetoothManager, IBluetoothA2dp, 
//            BluetoothDevice, BluetoothUuid, IBluetoothStateChangeCallback, BluetoothCodecStatus, 
//            BluetoothCodecConfig

public final class BluetoothA2dp
    implements BluetoothProfile
{

    static ServiceConnection _2D_get0(BluetoothA2dp bluetootha2dp)
    {
        return bluetootha2dp.mConnection;
    }

    static Context _2D_get1(BluetoothA2dp bluetootha2dp)
    {
        return bluetootha2dp.mContext;
    }

    static IBluetoothA2dp _2D_get2(BluetoothA2dp bluetootha2dp)
    {
        return bluetootha2dp.mService;
    }

    static BluetoothProfile.ServiceListener _2D_get3(BluetoothA2dp bluetootha2dp)
    {
        return bluetootha2dp.mServiceListener;
    }

    static ReentrantReadWriteLock _2D_get4(BluetoothA2dp bluetootha2dp)
    {
        return bluetootha2dp.mServiceLock;
    }

    static IBluetoothA2dp _2D_set0(BluetoothA2dp bluetootha2dp, IBluetoothA2dp ibluetootha2dp)
    {
        bluetootha2dp.mService = ibluetootha2dp;
        return ibluetootha2dp;
    }

    BluetoothA2dp(Context context, BluetoothProfile.ServiceListener servicelistener)
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
                Log.e("BluetoothA2dp", "", context);
            }
        doBind();
    }

    private void enableDisableOptionalCodecs(boolean flag)
    {
        mServiceLock.readLock().lock();
        if(mService == null || !isEnabled())
            break MISSING_BLOCK_LABEL_37;
        if(!flag)
            break MISSING_BLOCK_LABEL_63;
        mService.enableOptionalCodecs();
_L1:
        if(mService == null)
            Log.w("BluetoothA2dp", "Proxy not attached to service");
        mServiceLock.readLock().unlock();
        return;
        mService.disableOptionalCodecs();
          goto _L1
        Object obj;
        obj;
        Log.e("BluetoothA2dp", "Error talking to BT service in enableDisableOptionalCodecs()", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        return;
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
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
        Log.d("BluetoothA2dp", s);
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

    public void adjustAvrcpAbsoluteVolume(int i)
    {
        Log.d("BluetoothA2dp", "adjustAvrcpAbsoluteVolume");
        mServiceLock.readLock().lock();
        if(mService != null && isEnabled())
            mService.adjustAvrcpAbsoluteVolume(i);
        if(mService == null)
            Log.w("BluetoothA2dp", "Proxy not attached to service");
        mServiceLock.readLock().unlock();
_L2:
        return;
        Object obj;
        obj;
        Log.e("BluetoothA2dp", "Error talking to BT service in adjustAvrcpAbsoluteVolume()", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    void close()
    {
        mServiceListener = null;
        IBluetoothManager ibluetoothmanager = mAdapter.getBluetoothManager();
        if(ibluetoothmanager != null)
            try
            {
                ibluetoothmanager.unregisterStateChangeCallback(mBluetoothStateChangeCallback);
            }
            catch(Exception exception)
            {
                Log.e("BluetoothA2dp", "", exception);
            }
        mServiceLock.writeLock().lock();
        if(mService != null)
        {
            mService = null;
            mContext.unbindService(mConnection);
        }
        mServiceLock.writeLock().unlock();
_L2:
        return;
        Object obj;
        obj;
        Log.e("BluetoothA2dp", "", ((Throwable) (obj)));
        mServiceLock.writeLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.writeLock().unlock();
        throw obj;
    }

    public boolean connect(BluetoothDevice bluetoothdevice)
    {
        log((new StringBuilder()).append("connect(").append(bluetoothdevice).append(")").toString());
        boolean flag;
        mServiceLock.readLock().lock();
        if(mService == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_82;
        flag = mService.connect(bluetoothdevice);
        mServiceLock.readLock().unlock();
        return flag;
        if(mService == null)
            Log.w("BluetoothA2dp", "Proxy not attached to service");
        mServiceLock.readLock().unlock();
        return false;
        bluetoothdevice;
        bluetoothdevice = JVM INSTR new #186 <Class StringBuilder>;
        bluetoothdevice.StringBuilder();
        bluetoothdevice = bluetoothdevice.append("Stack:");
        Throwable throwable = JVM INSTR new #258 <Class Throwable>;
        throwable.Throwable();
        Log.e("BluetoothA2dp", bluetoothdevice.append(Log.getStackTraceString(throwable)).toString());
        mServiceLock.readLock().unlock();
        return false;
        bluetoothdevice;
        mServiceLock.readLock().unlock();
        throw bluetoothdevice;
    }

    public void disableOptionalCodecs()
    {
        Log.d("BluetoothA2dp", "disableOptionalCodecs");
        enableDisableOptionalCodecs(false);
    }

    public boolean disconnect(BluetoothDevice bluetoothdevice)
    {
        log((new StringBuilder()).append("disconnect(").append(bluetoothdevice).append(")").toString());
        boolean flag;
        mServiceLock.readLock().lock();
        if(mService == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_83;
        flag = mService.disconnect(bluetoothdevice);
        mServiceLock.readLock().unlock();
        return flag;
        if(mService == null)
            Log.w("BluetoothA2dp", "Proxy not attached to service");
        mServiceLock.readLock().unlock();
        return false;
        bluetoothdevice;
        bluetoothdevice = JVM INSTR new #186 <Class StringBuilder>;
        bluetoothdevice.StringBuilder();
        StringBuilder stringbuilder = bluetoothdevice.append("Stack:");
        bluetoothdevice = JVM INSTR new #258 <Class Throwable>;
        bluetoothdevice.Throwable();
        Log.e("BluetoothA2dp", stringbuilder.append(Log.getStackTraceString(bluetoothdevice)).toString());
        mServiceLock.readLock().unlock();
        return false;
        bluetoothdevice;
        mServiceLock.readLock().unlock();
        throw bluetoothdevice;
    }

    boolean doBind()
    {
        Intent intent = new Intent(android/bluetooth/IBluetoothA2dp.getName());
        ComponentName componentname = intent.resolveSystemService(mContext.getPackageManager(), 0);
        intent.setComponent(componentname);
        if(componentname == null || mContext.bindServiceAsUser(intent, mConnection, 0, Process.myUserHandle()) ^ true)
        {
            Log.e("BluetoothA2dp", (new StringBuilder()).append("Could not bind to Bluetooth A2DP Service with ").append(intent).toString());
            return false;
        } else
        {
            return true;
        }
    }

    public void enableOptionalCodecs()
    {
        Log.d("BluetoothA2dp", "enableOptionalCodecs");
        enableDisableOptionalCodecs(true);
    }

    public void finalize()
    {
    }

    public BluetoothCodecStatus getCodecStatus()
    {
        Log.d("BluetoothA2dp", "getCodecStatus");
        BluetoothCodecStatus bluetoothcodecstatus;
        mServiceLock.readLock().lock();
        if(mService == null || !isEnabled())
            break MISSING_BLOCK_LABEL_55;
        bluetoothcodecstatus = mService.getCodecStatus();
        mServiceLock.readLock().unlock();
        return bluetoothcodecstatus;
        if(mService == null)
            Log.w("BluetoothA2dp", "Proxy not attached to service");
        mServiceLock.readLock().unlock();
        return null;
        Object obj;
        obj;
        Log.e("BluetoothA2dp", "Error talking to BT service in getCodecStatus()", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        return null;
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public List getConnectedDevices()
    {
        Object obj;
        mServiceLock.readLock().lock();
        if(mService == null || !isEnabled())
            break MISSING_BLOCK_LABEL_46;
        obj = mService.getConnectedDevices();
        mServiceLock.readLock().unlock();
        return ((List) (obj));
        if(mService == null)
            Log.w("BluetoothA2dp", "Proxy not attached to service");
        obj = new ArrayList();
        mServiceLock.readLock().unlock();
        return ((List) (obj));
        Object obj1;
        obj1;
        obj1 = JVM INSTR new #186 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        StringBuilder stringbuilder = ((StringBuilder) (obj1)).append("Stack:");
        obj1 = JVM INSTR new #258 <Class Throwable>;
        ((Throwable) (obj1)).Throwable();
        Log.e("BluetoothA2dp", stringbuilder.append(Log.getStackTraceString(((Throwable) (obj1)))).toString());
        obj1 = new ArrayList();
        mServiceLock.readLock().unlock();
        return ((List) (obj1));
        obj1;
        mServiceLock.readLock().unlock();
        throw obj1;
    }

    public int getConnectionState(BluetoothDevice bluetoothdevice)
    {
        int i;
        mServiceLock.readLock().lock();
        if(mService == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_55;
        i = mService.getConnectionState(bluetoothdevice);
        mServiceLock.readLock().unlock();
        return i;
        if(mService == null)
            Log.w("BluetoothA2dp", "Proxy not attached to service");
        mServiceLock.readLock().unlock();
        return 0;
        bluetoothdevice;
        bluetoothdevice = JVM INSTR new #186 <Class StringBuilder>;
        bluetoothdevice.StringBuilder();
        StringBuilder stringbuilder = bluetoothdevice.append("Stack:");
        bluetoothdevice = JVM INSTR new #258 <Class Throwable>;
        bluetoothdevice.Throwable();
        Log.e("BluetoothA2dp", stringbuilder.append(Log.getStackTraceString(bluetoothdevice)).toString());
        mServiceLock.readLock().unlock();
        return 0;
        bluetoothdevice;
        mServiceLock.readLock().unlock();
        throw bluetoothdevice;
    }

    public List getDevicesMatchingConnectionStates(int ai[])
    {
        mServiceLock.readLock().lock();
        if(mService == null || !isEnabled())
            break MISSING_BLOCK_LABEL_47;
        ai = mService.getDevicesMatchingConnectionStates(ai);
        mServiceLock.readLock().unlock();
        return ai;
        if(mService == null)
            Log.w("BluetoothA2dp", "Proxy not attached to service");
        ai = new ArrayList();
        mServiceLock.readLock().unlock();
        return ai;
        ai;
        ai = JVM INSTR new #186 <Class StringBuilder>;
        ai.StringBuilder();
        StringBuilder stringbuilder = ai.append("Stack:");
        ai = JVM INSTR new #258 <Class Throwable>;
        ai.Throwable();
        Log.e("BluetoothA2dp", stringbuilder.append(Log.getStackTraceString(ai)).toString());
        ai = new ArrayList();
        mServiceLock.readLock().unlock();
        return ai;
        ai;
        mServiceLock.readLock().unlock();
        throw ai;
    }

    public int getOptionalCodecsEnabled(BluetoothDevice bluetoothdevice)
    {
        int i;
        mServiceLock.readLock().lock();
        if(mService == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_55;
        i = mService.getOptionalCodecsEnabled(bluetoothdevice);
        mServiceLock.readLock().unlock();
        return i;
        if(mService == null)
            Log.w("BluetoothA2dp", "Proxy not attached to service");
        mServiceLock.readLock().unlock();
        return -1;
        bluetoothdevice;
        Log.e("BluetoothA2dp", "Error talking to BT service in getSupportsOptionalCodecs()", bluetoothdevice);
        mServiceLock.readLock().unlock();
        return -1;
        bluetoothdevice;
        mServiceLock.readLock().unlock();
        throw bluetoothdevice;
    }

    public int getPriority(BluetoothDevice bluetoothdevice)
    {
        int i;
        mServiceLock.readLock().lock();
        if(mService == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_55;
        i = mService.getPriority(bluetoothdevice);
        mServiceLock.readLock().unlock();
        return i;
        if(mService == null)
            Log.w("BluetoothA2dp", "Proxy not attached to service");
        mServiceLock.readLock().unlock();
        return 0;
        bluetoothdevice;
        bluetoothdevice = JVM INSTR new #186 <Class StringBuilder>;
        bluetoothdevice.StringBuilder();
        bluetoothdevice = bluetoothdevice.append("Stack:");
        Throwable throwable = JVM INSTR new #258 <Class Throwable>;
        throwable.Throwable();
        Log.e("BluetoothA2dp", bluetoothdevice.append(Log.getStackTraceString(throwable)).toString());
        mServiceLock.readLock().unlock();
        return 0;
        bluetoothdevice;
        mServiceLock.readLock().unlock();
        throw bluetoothdevice;
    }

    public boolean isA2dpPlaying(BluetoothDevice bluetoothdevice)
    {
        boolean flag;
        mServiceLock.readLock().lock();
        if(mService == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_55;
        flag = mService.isA2dpPlaying(bluetoothdevice);
        mServiceLock.readLock().unlock();
        return flag;
        if(mService == null)
            Log.w("BluetoothA2dp", "Proxy not attached to service");
        mServiceLock.readLock().unlock();
        return false;
        bluetoothdevice;
        bluetoothdevice = JVM INSTR new #186 <Class StringBuilder>;
        bluetoothdevice.StringBuilder();
        StringBuilder stringbuilder = bluetoothdevice.append("Stack:");
        bluetoothdevice = JVM INSTR new #258 <Class Throwable>;
        bluetoothdevice.Throwable();
        Log.e("BluetoothA2dp", stringbuilder.append(Log.getStackTraceString(bluetoothdevice)).toString());
        mServiceLock.readLock().unlock();
        return false;
        bluetoothdevice;
        mServiceLock.readLock().unlock();
        throw bluetoothdevice;
    }

    public boolean isAvrcpAbsoluteVolumeSupported()
    {
        Log.d("BluetoothA2dp", "isAvrcpAbsoluteVolumeSupported");
        boolean flag;
        mServiceLock.readLock().lock();
        if(mService == null || !isEnabled())
            break MISSING_BLOCK_LABEL_55;
        flag = mService.isAvrcpAbsoluteVolumeSupported();
        mServiceLock.readLock().unlock();
        return flag;
        if(mService == null)
            Log.w("BluetoothA2dp", "Proxy not attached to service");
        mServiceLock.readLock().unlock();
        return false;
        Object obj;
        obj;
        Log.e("BluetoothA2dp", "Error talking to BT service in isAvrcpAbsoluteVolumeSupported()", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        return false;
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public boolean selectStream(BluetoothDevice bluetoothdevice)
    {
        log((new StringBuilder()).append("selectStream(").append(bluetoothdevice).append(")").toString());
        if(mService != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = mService.selectStream(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothA2dp", (new StringBuilder()).append("Stack:").append(Log.getStackTraceString(new Throwable())).toString());
                return false;
            }
            return flag;
        }
        if(mService == null)
            Log.w("BluetoothA2dp", "Proxy not attached to service");
        return false;
    }

    public void setAvrcpAbsoluteVolume(int i)
    {
        Log.d("BluetoothA2dp", "setAvrcpAbsoluteVolume");
        mServiceLock.readLock().lock();
        if(mService != null && isEnabled())
            mService.setAvrcpAbsoluteVolume(i);
        if(mService == null)
            Log.w("BluetoothA2dp", "Proxy not attached to service");
        mServiceLock.readLock().unlock();
_L2:
        return;
        Object obj;
        obj;
        Log.e("BluetoothA2dp", "Error talking to BT service in setAvrcpAbsoluteVolume()", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public void setCodecConfigPreference(BluetoothCodecConfig bluetoothcodecconfig)
    {
        Log.d("BluetoothA2dp", "setCodecConfigPreference");
        mServiceLock.readLock().lock();
        if(mService != null && isEnabled())
            mService.setCodecConfigPreference(bluetoothcodecconfig);
        if(mService == null)
            Log.w("BluetoothA2dp", "Proxy not attached to service");
        mServiceLock.readLock().unlock();
        return;
        bluetoothcodecconfig;
        Log.e("BluetoothA2dp", "Error talking to BT service in setCodecConfigPreference()", bluetoothcodecconfig);
        mServiceLock.readLock().unlock();
        return;
        bluetoothcodecconfig;
        mServiceLock.readLock().unlock();
        throw bluetoothcodecconfig;
    }

    public void setOptionalCodecsEnabled(BluetoothDevice bluetoothdevice, int i)
    {
        if(i == -1 || i == 0 || i == 1)
            break MISSING_BLOCK_LABEL_53;
        bluetoothdevice = JVM INSTR new #186 <Class StringBuilder>;
        bluetoothdevice.StringBuilder();
        Log.e("BluetoothA2dp", bluetoothdevice.append("Invalid value passed to setOptionalCodecsEnabled: ").append(i).toString());
        mServiceLock.readLock().unlock();
        return;
        mServiceLock.readLock().lock();
        if(mService != null && isEnabled() && isValidDevice(bluetoothdevice))
            mService.setOptionalCodecsEnabled(bluetoothdevice, i);
        if(mService == null)
            Log.w("BluetoothA2dp", "Proxy not attached to service");
        mServiceLock.readLock().unlock();
        return;
        bluetoothdevice;
        bluetoothdevice = JVM INSTR new #186 <Class StringBuilder>;
        bluetoothdevice.StringBuilder();
        bluetoothdevice = bluetoothdevice.append("Stack:");
        Throwable throwable = JVM INSTR new #258 <Class Throwable>;
        throwable.Throwable();
        Log.e("BluetoothA2dp", bluetoothdevice.append(Log.getStackTraceString(throwable)).toString());
        mServiceLock.readLock().unlock();
        return;
        bluetoothdevice;
        mServiceLock.readLock().unlock();
        throw bluetoothdevice;
    }

    public boolean setPriority(BluetoothDevice bluetoothdevice, int i)
    {
        log((new StringBuilder()).append("setPriority(").append(bluetoothdevice).append(", ").append(i).append(")").toString());
        boolean flag;
        mServiceLock.readLock().lock();
        if(mService == null || !isEnabled())
            break MISSING_BLOCK_LABEL_118;
        flag = isValidDevice(bluetoothdevice);
        if(!flag)
            break MISSING_BLOCK_LABEL_118;
        if(i != 0 && i != 100)
        {
            mServiceLock.readLock().unlock();
            return false;
        }
        flag = mService.setPriority(bluetoothdevice, i);
        mServiceLock.readLock().unlock();
        return flag;
        if(mService == null)
            Log.w("BluetoothA2dp", "Proxy not attached to service");
        mServiceLock.readLock().unlock();
        return false;
        bluetoothdevice;
        bluetoothdevice = JVM INSTR new #186 <Class StringBuilder>;
        bluetoothdevice.StringBuilder();
        StringBuilder stringbuilder = bluetoothdevice.append("Stack:");
        bluetoothdevice = JVM INSTR new #258 <Class Throwable>;
        bluetoothdevice.Throwable();
        Log.e("BluetoothA2dp", stringbuilder.append(Log.getStackTraceString(bluetoothdevice)).toString());
        mServiceLock.readLock().unlock();
        return false;
        bluetoothdevice;
        mServiceLock.readLock().unlock();
        throw bluetoothdevice;
    }

    public boolean shouldSendVolumeKeys(BluetoothDevice bluetoothdevice)
    {
        if(isEnabled() && isValidDevice(bluetoothdevice))
        {
            bluetoothdevice = bluetoothdevice.getUuids();
            if(bluetoothdevice == null)
                return false;
            int i = bluetoothdevice.length;
            for(int j = 0; j < i; j++)
                if(BluetoothUuid.isAvrcpTarget(bluetoothdevice[j]))
                    return true;

        }
        return false;
    }

    public int supportsOptionalCodecs(BluetoothDevice bluetoothdevice)
    {
        int i;
        mServiceLock.readLock().lock();
        if(mService == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_55;
        i = mService.supportsOptionalCodecs(bluetoothdevice);
        mServiceLock.readLock().unlock();
        return i;
        if(mService == null)
            Log.w("BluetoothA2dp", "Proxy not attached to service");
        mServiceLock.readLock().unlock();
        return -1;
        bluetoothdevice;
        Log.e("BluetoothA2dp", "Error talking to BT service in getSupportsOptionalCodecs()", bluetoothdevice);
        mServiceLock.readLock().unlock();
        return -1;
        bluetoothdevice;
        mServiceLock.readLock().unlock();
        throw bluetoothdevice;
    }

    public static final String ACTION_AVRCP_CONNECTION_STATE_CHANGED = "android.bluetooth.a2dp.profile.action.AVRCP_CONNECTION_STATE_CHANGED";
    public static final String ACTION_CODEC_CONFIG_CHANGED = "android.bluetooth.a2dp.profile.action.CODEC_CONFIG_CHANGED";
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED";
    public static final String ACTION_PLAYING_STATE_CHANGED = "android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED";
    private static final boolean DBG = true;
    public static final int OPTIONAL_CODECS_NOT_SUPPORTED = 0;
    public static final int OPTIONAL_CODECS_PREF_DISABLED = 0;
    public static final int OPTIONAL_CODECS_PREF_ENABLED = 1;
    public static final int OPTIONAL_CODECS_PREF_UNKNOWN = -1;
    public static final int OPTIONAL_CODECS_SUPPORTED = 1;
    public static final int OPTIONAL_CODECS_SUPPORT_UNKNOWN = -1;
    public static final int STATE_NOT_PLAYING = 11;
    public static final int STATE_PLAYING = 10;
    private static final String TAG = "BluetoothA2dp";
    private static final boolean VDBG = false;
    private BluetoothAdapter mAdapter;
    private final IBluetoothStateChangeCallback mBluetoothStateChangeCallback = new IBluetoothStateChangeCallback.Stub() {

        public void onBluetoothStateChange(boolean flag)
        {
            Log.d("BluetoothA2dp", (new StringBuilder()).append("onBluetoothStateChange: up=").append(flag).toString());
            if(flag) goto _L2; else goto _L1
_L1:
            BluetoothA2dp._2D_get4(BluetoothA2dp.this).writeLock().lock();
            BluetoothA2dp._2D_set0(BluetoothA2dp.this, null);
            BluetoothA2dp._2D_get1(BluetoothA2dp.this).unbindService(BluetoothA2dp._2D_get0(BluetoothA2dp.this));
            BluetoothA2dp._2D_get4(BluetoothA2dp.this).writeLock().unlock();
_L3:
            return;
            Object obj;
            obj;
            Log.e("BluetoothA2dp", "", ((Throwable) (obj)));
            BluetoothA2dp._2D_get4(BluetoothA2dp.this).writeLock().unlock();
              goto _L3
            obj;
            BluetoothA2dp._2D_get4(BluetoothA2dp.this).writeLock().unlock();
            throw obj;
_L2:
            BluetoothA2dp._2D_get4(BluetoothA2dp.this).readLock().lock();
            if(BluetoothA2dp._2D_get2(BluetoothA2dp.this) == null)
                doBind();
            BluetoothA2dp._2D_get4(BluetoothA2dp.this).readLock().unlock();
              goto _L3
            obj;
            Log.e("BluetoothA2dp", "", ((Throwable) (obj)));
            BluetoothA2dp._2D_get4(BluetoothA2dp.this).readLock().unlock();
              goto _L3
            obj;
            BluetoothA2dp._2D_get4(BluetoothA2dp.this).readLock().unlock();
            throw obj;
        }

        final BluetoothA2dp this$0;

            
            {
                this$0 = BluetoothA2dp.this;
                super();
            }
    }
;
    private final ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            Log.d("BluetoothA2dp", "Proxy object connected");
            BluetoothA2dp._2D_get4(BluetoothA2dp.this).writeLock().lock();
            BluetoothA2dp._2D_set0(BluetoothA2dp.this, IBluetoothA2dp.Stub.asInterface(Binder.allowBlocking(ibinder)));
            BluetoothA2dp._2D_get4(BluetoothA2dp.this).writeLock().unlock();
            if(BluetoothA2dp._2D_get3(BluetoothA2dp.this) != null)
                BluetoothA2dp._2D_get3(BluetoothA2dp.this).onServiceConnected(2, BluetoothA2dp.this);
            return;
            componentname;
            BluetoothA2dp._2D_get4(BluetoothA2dp.this).writeLock().unlock();
            throw componentname;
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            Log.d("BluetoothA2dp", "Proxy object disconnected");
            BluetoothA2dp._2D_get4(BluetoothA2dp.this).writeLock().lock();
            BluetoothA2dp._2D_set0(BluetoothA2dp.this, null);
            BluetoothA2dp._2D_get4(BluetoothA2dp.this).writeLock().unlock();
            if(BluetoothA2dp._2D_get3(BluetoothA2dp.this) != null)
                BluetoothA2dp._2D_get3(BluetoothA2dp.this).onServiceDisconnected(2);
            return;
            componentname;
            BluetoothA2dp._2D_get4(BluetoothA2dp.this).writeLock().unlock();
            throw componentname;
        }

        final BluetoothA2dp this$0;

            
            {
                this$0 = BluetoothA2dp.this;
                super();
            }
    }
;
    private Context mContext;
    private IBluetoothA2dp mService;
    private BluetoothProfile.ServiceListener mServiceListener;
    private final ReentrantReadWriteLock mServiceLock = new ReentrantReadWriteLock();
}

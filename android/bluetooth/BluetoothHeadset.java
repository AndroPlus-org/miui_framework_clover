// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.content.ComponentName;
import android.content.Context;
import android.content.res.Resources;
import android.os.*;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// Referenced classes of package android.bluetooth:
//            BluetoothProfile, BluetoothAdapter, IBluetoothManager, BluetoothDevice, 
//            IBluetoothHeadset, IBluetoothStateChangeCallback, IBluetoothProfileServiceConnection

public final class BluetoothHeadset
    implements BluetoothProfile
{

    static IBluetoothProfileServiceConnection _2D_get0(BluetoothHeadset bluetoothheadset)
    {
        return bluetoothheadset.mConnection;
    }

    static Handler _2D_get1(BluetoothHeadset bluetoothheadset)
    {
        return bluetoothheadset.mHandler;
    }

    static IBluetoothHeadset _2D_get2(BluetoothHeadset bluetoothheadset)
    {
        return bluetoothheadset.mService;
    }

    static BluetoothProfile.ServiceListener _2D_get3(BluetoothHeadset bluetoothheadset)
    {
        return bluetoothheadset.mServiceListener;
    }

    static ReentrantReadWriteLock _2D_get4(BluetoothHeadset bluetoothheadset)
    {
        return bluetoothheadset.mServiceLock;
    }

    static IBluetoothHeadset _2D_set0(BluetoothHeadset bluetoothheadset, IBluetoothHeadset ibluetoothheadset)
    {
        bluetoothheadset.mService = ibluetoothheadset;
        return ibluetoothheadset;
    }

    BluetoothHeadset(Context context, BluetoothProfile.ServiceListener servicelistener)
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
                Log.e("BluetoothHeadset", "", context);
            }
        doBind();
    }

    public static boolean isBluetoothVoiceDialingEnabled(Context context)
    {
        return context.getResources().getBoolean(0x112002b);
    }

    private boolean isDisabled()
    {
        boolean flag;
        if(mAdapter.getState() == 10)
            flag = true;
        else
            flag = false;
        return flag;
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

    public static boolean isInbandRingingSupported(Context context)
    {
        return context.getResources().getBoolean(0x1120027);
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
        Log.d("BluetoothHeadset", s);
    }

    public boolean acceptIncomingConnect(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadset ibluetoothheadset;
        log("acceptIncomingConnect");
        ibluetoothheadset = mService;
        if(ibluetoothheadset == null || !isEnabled()) goto _L2; else goto _L1
_L1:
        boolean flag = ibluetoothheadset.acceptIncomingConnect(bluetoothdevice);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHeadset", bluetoothdevice.toString());
_L4:
        return false;
_L2:
        Log.w("BluetoothHeadset", "Proxy not attached to service");
        Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void bindResponse(int i, boolean flag)
    {
        IBluetoothHeadset ibluetoothheadset;
        ibluetoothheadset = mService;
        if(ibluetoothheadset == null || !isEnabled())
            break MISSING_BLOCK_LABEL_39;
        ibluetoothheadset.bindResponse(i, flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothHeadset", remoteexception.toString());
          goto _L1
        Log.w("BluetoothHeadset", "Proxy not attached to service");
        Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
          goto _L1
    }

    public void clccResponse(int i, int j, int k, int l, boolean flag, String s, int i1)
    {
        IBluetoothHeadset ibluetoothheadset;
        ibluetoothheadset = mService;
        if(ibluetoothheadset == null || !isEnabled())
            break MISSING_BLOCK_LABEL_53;
        ibluetoothheadset.clccResponse(i, j, k, l, flag, s, i1);
_L1:
        return;
        s;
        Log.e("BluetoothHeadset", s.toString());
          goto _L1
        Log.w("BluetoothHeadset", "Proxy not attached to service");
        Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
          goto _L1
    }

    void close()
    {
        IBluetoothManager ibluetoothmanager = mAdapter.getBluetoothManager();
        if(ibluetoothmanager != null)
            try
            {
                ibluetoothmanager.unregisterStateChangeCallback(mBluetoothStateChangeCallback);
            }
            catch(Exception exception)
            {
                Log.e("BluetoothHeadset", "", exception);
            }
        mServiceListener = null;
        doUnbind();
    }

    public boolean connect(BluetoothDevice bluetoothdevice)
    {
        log((new StringBuilder()).append("connect(").append(bluetoothdevice).append(")").toString());
        IBluetoothHeadset ibluetoothheadset = mService;
        if(ibluetoothheadset != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetoothheadset.connect(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
                return false;
            }
            return flag;
        }
        if(ibluetoothheadset == null)
            Log.w("BluetoothHeadset", "Proxy not attached to service");
        return false;
    }

    public boolean connectAudio()
    {
        IBluetoothHeadset ibluetoothheadset = mService;
        if(ibluetoothheadset == null || !isEnabled()) goto _L2; else goto _L1
_L1:
        boolean flag = ibluetoothheadset.connectAudio();
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothHeadset", remoteexception.toString());
_L4:
        return false;
_L2:
        Log.w("BluetoothHeadset", "Proxy not attached to service");
        Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean disableWBS()
    {
        IBluetoothHeadset ibluetoothheadset = mService;
        if(ibluetoothheadset == null || !isEnabled()) goto _L2; else goto _L1
_L1:
        boolean flag = ibluetoothheadset.disableWBS();
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothHeadset", remoteexception.toString());
_L4:
        return false;
_L2:
        Log.w("BluetoothHeadset", "Proxy not attached to service");
        Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean disconnect(BluetoothDevice bluetoothdevice)
    {
        log((new StringBuilder()).append("disconnect(").append(bluetoothdevice).append(")").toString());
        IBluetoothHeadset ibluetoothheadset = mService;
        if(ibluetoothheadset != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            boolean flag;
            try
            {
                flag = ibluetoothheadset.disconnect(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
                return false;
            }
            return flag;
        }
        if(ibluetoothheadset == null)
            Log.w("BluetoothHeadset", "Proxy not attached to service");
        return false;
    }

    public boolean disconnectAudio()
    {
        IBluetoothHeadset ibluetoothheadset = mService;
        if(ibluetoothheadset == null || !isEnabled()) goto _L2; else goto _L1
_L1:
        boolean flag = ibluetoothheadset.disconnectAudio();
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothHeadset", remoteexception.toString());
_L4:
        return false;
_L2:
        Log.w("BluetoothHeadset", "Proxy not attached to service");
        Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    boolean doBind()
    {
        boolean flag;
        try
        {
            flag = mAdapter.getBluetoothManager().bindBluetoothProfileService(1, mConnection);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothHeadset", "Unable to bind HeadsetService", remoteexception);
            return false;
        }
        return flag;
    }

    void doUnbind()
    {
        IBluetoothProfileServiceConnection ibluetoothprofileserviceconnection = mConnection;
        ibluetoothprofileserviceconnection;
        JVM INSTR monitorenter ;
        mAdapter.getBluetoothManager().unbindBluetoothProfileService(1, mConnection);
_L1:
        ibluetoothprofileserviceconnection;
        JVM INSTR monitorexit ;
        return;
        Object obj;
        obj;
        Log.e("BluetoothHeadset", "Unable to unbind HeadsetService", ((Throwable) (obj)));
          goto _L1
        obj;
        throw obj;
    }

    public boolean enableWBS()
    {
        IBluetoothHeadset ibluetoothheadset = mService;
        if(ibluetoothheadset == null || !isEnabled()) goto _L2; else goto _L1
_L1:
        boolean flag = ibluetoothheadset.enableWBS();
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothHeadset", remoteexception.toString());
_L4:
        return false;
_L2:
        Log.w("BluetoothHeadset", "Proxy not attached to service");
        Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void finalize()
    {
        close();
    }

    public boolean getAudioRouteAllowed()
    {
        IBluetoothHeadset ibluetoothheadset = mService;
        if(ibluetoothheadset == null || !isEnabled()) goto _L2; else goto _L1
_L1:
        boolean flag = ibluetoothheadset.getAudioRouteAllowed();
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothHeadset", remoteexception.toString());
_L4:
        return false;
_L2:
        Log.w("BluetoothHeadset", "Proxy not attached to service");
        Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int getAudioState(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadset ibluetoothheadset = mService;
        if(ibluetoothheadset == null || !(isDisabled() ^ true)) goto _L2; else goto _L1
_L1:
        int i = ibluetoothheadset.getAudioState(bluetoothdevice);
        return i;
        bluetoothdevice;
        Log.e("BluetoothHeadset", bluetoothdevice.toString());
_L4:
        return 10;
_L2:
        Log.w("BluetoothHeadset", "Proxy not attached to service");
        Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int getBatteryUsageHint(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadset ibluetoothheadset;
        ibluetoothheadset = mService;
        if(ibluetoothheadset == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_50;
        int i = ibluetoothheadset.getBatteryUsageHint(bluetoothdevice);
        return i;
        bluetoothdevice;
        Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        if(ibluetoothheadset == null)
            Log.w("BluetoothHeadset", "Proxy not attached to service");
        return -1;
    }

    public List getConnectedDevices()
    {
        Object obj;
        mServiceLock.readLock().lock();
        obj = mService;
        if(obj == null)
            break MISSING_BLOCK_LABEL_86;
        boolean flag = isEnabled();
        if(!flag)
            break MISSING_BLOCK_LABEL_86;
        obj = ((IBluetoothHeadset) (obj)).getConnectedDevices();
        mServiceLock.readLock().unlock();
        return ((List) (obj));
        obj;
        obj = JVM INSTR new #247 <Class Throwable>;
        ((Throwable) (obj)).Throwable();
        Log.e("BluetoothHeadset", Log.getStackTraceString(((Throwable) (obj))));
        obj = new ArrayList();
        mServiceLock.readLock().unlock();
        return ((List) (obj));
        if(obj != null)
            break MISSING_BLOCK_LABEL_98;
        Log.w("BluetoothHeadset", "Proxy not attached to service");
        obj = new ArrayList();
        mServiceLock.readLock().unlock();
        return ((List) (obj));
        Exception exception;
        exception;
        mServiceLock.readLock().unlock();
        throw exception;
    }

    public int getConnectionState(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadset ibluetoothheadset = mService;
        if(ibluetoothheadset != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            int i;
            try
            {
                i = ibluetoothheadset.getConnectionState(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
                return 0;
            }
            return i;
        }
        if(ibluetoothheadset == null)
            Log.w("BluetoothHeadset", "Proxy not attached to service");
        return 0;
    }

    public List getDevicesMatchingConnectionStates(int ai[])
    {
        IBluetoothHeadset ibluetoothheadset = mService;
        if(ibluetoothheadset != null && isEnabled())
        {
            try
            {
                ai = ibluetoothheadset.getDevicesMatchingConnectionStates(ai);
            }
            // Misplaced declaration of an exception variable
            catch(int ai[])
            {
                Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
                return new ArrayList();
            }
            return ai;
        }
        if(ibluetoothheadset == null)
            Log.w("BluetoothHeadset", "Proxy not attached to service");
        return new ArrayList();
    }

    public int getPriority(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadset ibluetoothheadset = mService;
        if(ibluetoothheadset != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            int i;
            try
            {
                i = ibluetoothheadset.getPriority(bluetoothdevice);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
                return 0;
            }
            return i;
        }
        if(ibluetoothheadset == null)
            Log.w("BluetoothHeadset", "Proxy not attached to service");
        return 0;
    }

    public boolean isAudioConnected(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadset ibluetoothheadset;
        ibluetoothheadset = mService;
        if(ibluetoothheadset == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_50;
        boolean flag = ibluetoothheadset.isAudioConnected(bluetoothdevice);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        if(ibluetoothheadset == null)
            Log.w("BluetoothHeadset", "Proxy not attached to service");
        return false;
    }

    public boolean isAudioOn()
    {
        IBluetoothHeadset ibluetoothheadset;
        ibluetoothheadset = mService;
        if(ibluetoothheadset == null || !isEnabled())
            break MISSING_BLOCK_LABEL_42;
        boolean flag = ibluetoothheadset.isAudioOn();
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        if(ibluetoothheadset == null)
            Log.w("BluetoothHeadset", "Proxy not attached to service");
        return false;
    }

    public void phoneStateChanged(int i, int j, int k, String s, int l)
    {
        IBluetoothHeadset ibluetoothheadset;
        ibluetoothheadset = mService;
        if(ibluetoothheadset == null || !isEnabled())
            break MISSING_BLOCK_LABEL_49;
        ibluetoothheadset.phoneStateChanged(i, j, k, s, l);
_L1:
        return;
        s;
        Log.e("BluetoothHeadset", s.toString());
          goto _L1
        Log.w("BluetoothHeadset", "Proxy not attached to service");
        Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
          goto _L1
    }

    public boolean rejectIncomingConnect(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadset ibluetoothheadset;
        log("rejectIncomingConnect");
        ibluetoothheadset = mService;
        if(ibluetoothheadset == null) goto _L2; else goto _L1
_L1:
        boolean flag = ibluetoothheadset.rejectIncomingConnect(bluetoothdevice);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHeadset", bluetoothdevice.toString());
_L4:
        return false;
_L2:
        Log.w("BluetoothHeadset", "Proxy not attached to service");
        Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean sendVendorSpecificResultCode(BluetoothDevice bluetoothdevice, String s, String s1)
    {
        IBluetoothHeadset ibluetoothheadset;
        log("sendVendorSpecificResultCode()");
        if(s == null)
            throw new IllegalArgumentException("command is null");
        ibluetoothheadset = mService;
        if(ibluetoothheadset == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_78;
        boolean flag = ibluetoothheadset.sendVendorSpecificResultCode(bluetoothdevice, s, s1);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        if(ibluetoothheadset == null)
            Log.w("BluetoothHeadset", "Proxy not attached to service");
        return false;
    }

    public void setAudioRouteAllowed(boolean flag)
    {
        IBluetoothHeadset ibluetoothheadset;
        ibluetoothheadset = mService;
        if(ibluetoothheadset == null || !isEnabled())
            break MISSING_BLOCK_LABEL_38;
        ibluetoothheadset.setAudioRouteAllowed(flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothHeadset", remoteexception.toString());
          goto _L1
        Log.w("BluetoothHeadset", "Proxy not attached to service");
        Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
          goto _L1
    }

    public void setForceScoAudio(boolean flag)
    {
        IBluetoothHeadset ibluetoothheadset;
        ibluetoothheadset = mService;
        if(ibluetoothheadset == null || !isEnabled())
            break MISSING_BLOCK_LABEL_38;
        ibluetoothheadset.setForceScoAudio(flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothHeadset", remoteexception.toString());
          goto _L1
        Log.w("BluetoothHeadset", "Proxy not attached to service");
        Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
          goto _L1
    }

    public boolean setPriority(BluetoothDevice bluetoothdevice, int i)
    {
        log((new StringBuilder()).append("setPriority(").append(bluetoothdevice).append(", ").append(i).append(")").toString());
        IBluetoothHeadset ibluetoothheadset = mService;
        if(ibluetoothheadset != null && isEnabled() && isValidDevice(bluetoothdevice))
        {
            if(i != 0 && i != 100)
                return false;
            boolean flag;
            try
            {
                flag = ibluetoothheadset.setPriority(bluetoothdevice, i);
            }
            // Misplaced declaration of an exception variable
            catch(BluetoothDevice bluetoothdevice)
            {
                Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
                return false;
            }
            return flag;
        }
        if(ibluetoothheadset == null)
            Log.w("BluetoothHeadset", "Proxy not attached to service");
        return false;
    }

    public boolean startScoUsingVirtualVoiceCall(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadset ibluetoothheadset;
        log("startScoUsingVirtualVoiceCall()");
        ibluetoothheadset = mService;
        if(ibluetoothheadset == null || !isEnabled() || !isValidDevice(bluetoothdevice)) goto _L2; else goto _L1
_L1:
        boolean flag = ibluetoothheadset.startScoUsingVirtualVoiceCall(bluetoothdevice);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHeadset", bluetoothdevice.toString());
_L4:
        return false;
_L2:
        Log.w("BluetoothHeadset", "Proxy not attached to service");
        Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean startVoiceRecognition(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadset ibluetoothheadset;
        log("startVoiceRecognition()");
        ibluetoothheadset = mService;
        if(ibluetoothheadset == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_56;
        boolean flag = ibluetoothheadset.startVoiceRecognition(bluetoothdevice);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        if(ibluetoothheadset == null)
            Log.w("BluetoothHeadset", "Proxy not attached to service");
        return false;
    }

    public boolean stopScoUsingVirtualVoiceCall(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadset ibluetoothheadset;
        log("stopScoUsingVirtualVoiceCall()");
        ibluetoothheadset = mService;
        if(ibluetoothheadset == null || !isEnabled() || !isValidDevice(bluetoothdevice)) goto _L2; else goto _L1
_L1:
        boolean flag = ibluetoothheadset.stopScoUsingVirtualVoiceCall(bluetoothdevice);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHeadset", bluetoothdevice.toString());
_L4:
        return false;
_L2:
        Log.w("BluetoothHeadset", "Proxy not attached to service");
        Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean stopVoiceRecognition(BluetoothDevice bluetoothdevice)
    {
        IBluetoothHeadset ibluetoothheadset;
        log("stopVoiceRecognition()");
        ibluetoothheadset = mService;
        if(ibluetoothheadset == null || !isEnabled() || !isValidDevice(bluetoothdevice))
            break MISSING_BLOCK_LABEL_56;
        boolean flag = ibluetoothheadset.stopVoiceRecognition(bluetoothdevice);
        return flag;
        bluetoothdevice;
        Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        if(ibluetoothheadset == null)
            Log.w("BluetoothHeadset", "Proxy not attached to service");
        return false;
    }

    public static final String ACTION_AUDIO_STATE_CHANGED = "android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED";
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED";
    public static final String ACTION_HF_INDICATORS_VALUE_CHANGED = "android.bluetooth.headset.action.HF_INDICATORS_VALUE_CHANGED";
    public static final String ACTION_VENDOR_SPECIFIC_HEADSET_EVENT = "android.bluetooth.headset.action.VENDOR_SPECIFIC_HEADSET_EVENT";
    public static final int AT_CMD_TYPE_ACTION = 4;
    public static final int AT_CMD_TYPE_BASIC = 3;
    public static final int AT_CMD_TYPE_READ = 0;
    public static final int AT_CMD_TYPE_SET = 2;
    public static final int AT_CMD_TYPE_TEST = 1;
    private static final boolean DBG = true;
    public static final String EXTRA_HF_INDICATORS_IND_ID = "android.bluetooth.headset.extra.HF_INDICATORS_IND_ID";
    public static final String EXTRA_HF_INDICATORS_IND_VALUE = "android.bluetooth.headset.extra.HF_INDICATORS_IND_VALUE";
    public static final String EXTRA_VENDOR_SPECIFIC_HEADSET_EVENT_ARGS = "android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_ARGS";
    public static final String EXTRA_VENDOR_SPECIFIC_HEADSET_EVENT_CMD = "android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_CMD";
    public static final String EXTRA_VENDOR_SPECIFIC_HEADSET_EVENT_CMD_TYPE = "android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_CMD_TYPE";
    private static final int MESSAGE_HEADSET_SERVICE_CONNECTED = 100;
    private static final int MESSAGE_HEADSET_SERVICE_DISCONNECTED = 101;
    public static final int STATE_AUDIO_CONNECTED = 12;
    public static final int STATE_AUDIO_CONNECTING = 11;
    public static final int STATE_AUDIO_DISCONNECTED = 10;
    private static final String TAG = "BluetoothHeadset";
    private static final boolean VDBG = false;
    public static final String VENDOR_RESULT_CODE_COMMAND_ANDROID = "+ANDROID";
    public static final String VENDOR_SPECIFIC_HEADSET_EVENT_COMPANY_ID_CATEGORY = "android.bluetooth.headset.intent.category.companyid";
    public static final String VENDOR_SPECIFIC_HEADSET_EVENT_IPHONEACCEV = "+IPHONEACCEV";
    public static final int VENDOR_SPECIFIC_HEADSET_EVENT_IPHONEACCEV_BATTERY_LEVEL = 1;
    public static final String VENDOR_SPECIFIC_HEADSET_EVENT_XAPL = "+XAPL";
    public static final String VENDOR_SPECIFIC_HEADSET_EVENT_XEVENT = "+XEVENT";
    public static final String VENDOR_SPECIFIC_HEADSET_EVENT_XEVENT_BATTERY_LEVEL = "BATTERY";
    private BluetoothAdapter mAdapter;
    private final IBluetoothStateChangeCallback mBluetoothStateChangeCallback = new IBluetoothStateChangeCallback.Stub() {

        public void onBluetoothStateChange(boolean flag)
        {
            Log.d("BluetoothHeadset", (new StringBuilder()).append("onBluetoothStateChange: up=").append(flag).toString());
            if(flag) goto _L2; else goto _L1
_L1:
            doUnbind();
_L5:
            return;
_L2:
            IBluetoothProfileServiceConnection ibluetoothprofileserviceconnection = BluetoothHeadset._2D_get0(BluetoothHeadset.this);
            ibluetoothprofileserviceconnection;
            JVM INSTR monitorenter ;
            if(BluetoothHeadset._2D_get2(BluetoothHeadset.this) == null)
                doBind();
_L3:
            ibluetoothprofileserviceconnection;
            JVM INSTR monitorexit ;
            continue; /* Loop/switch isn't completed */
            Object obj;
            obj;
            Log.e("BluetoothHeadset", "", ((Throwable) (obj)));
              goto _L3
            obj;
            throw obj;
            if(true) goto _L5; else goto _L4
_L4:
        }

        final BluetoothHeadset this$0;

            
            {
                this$0 = BluetoothHeadset.this;
                super();
            }
    }
;
    private final IBluetoothProfileServiceConnection mConnection = new IBluetoothProfileServiceConnection.Stub() {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            Log.d("BluetoothHeadset", "Proxy object connected");
            BluetoothHeadset._2D_get4(BluetoothHeadset.this).writeLock().lock();
            BluetoothHeadset._2D_set0(BluetoothHeadset.this, IBluetoothHeadset.Stub.asInterface(Binder.allowBlocking(ibinder)));
            BluetoothHeadset._2D_get1(BluetoothHeadset.this).sendMessage(BluetoothHeadset._2D_get1(BluetoothHeadset.this).obtainMessage(100));
            BluetoothHeadset._2D_get4(BluetoothHeadset.this).writeLock().unlock();
            return;
            componentname;
            BluetoothHeadset._2D_get4(BluetoothHeadset.this).writeLock().unlock();
            throw componentname;
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            Log.d("BluetoothHeadset", "Proxy object disconnected");
            BluetoothHeadset._2D_get4(BluetoothHeadset.this).writeLock().lock();
            BluetoothHeadset._2D_set0(BluetoothHeadset.this, null);
            BluetoothHeadset._2D_get1(BluetoothHeadset.this).sendMessage(BluetoothHeadset._2D_get1(BluetoothHeadset.this).obtainMessage(101));
            BluetoothHeadset._2D_get4(BluetoothHeadset.this).writeLock().unlock();
            return;
            componentname;
            BluetoothHeadset._2D_get4(BluetoothHeadset.this).writeLock().unlock();
            throw componentname;
        }

        final BluetoothHeadset this$0;

            
            {
                this$0 = BluetoothHeadset.this;
                super();
            }
    }
;
    private Context mContext;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 100 101: default 28
        //                       100 29
        //                       101 59;
               goto _L1 _L2 _L3
_L1:
            return;
_L2:
            if(BluetoothHeadset._2D_get3(BluetoothHeadset.this) != null)
                BluetoothHeadset._2D_get3(BluetoothHeadset.this).onServiceConnected(1, BluetoothHeadset.this);
            continue; /* Loop/switch isn't completed */
_L3:
            if(BluetoothHeadset._2D_get3(BluetoothHeadset.this) != null)
                BluetoothHeadset._2D_get3(BluetoothHeadset.this).onServiceDisconnected(1);
            if(true) goto _L1; else goto _L4
_L4:
        }

        final BluetoothHeadset this$0;

            
            {
                this$0 = BluetoothHeadset.this;
                super(looper);
            }
    }
;
    private volatile IBluetoothHeadset mService;
    private BluetoothProfile.ServiceListener mServiceListener;
    private final ReentrantReadWriteLock mServiceLock = new ReentrantReadWriteLock();
}

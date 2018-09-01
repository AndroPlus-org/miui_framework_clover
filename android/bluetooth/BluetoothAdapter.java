// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.app.ActivityThread;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.PeriodicAdvertisingManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.*;
import android.util.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// Referenced classes of package android.bluetooth:
//            IBluetoothManager, BluetoothServerSocket, BluetoothSocket, IBluetooth, 
//            BluetoothHeadset, BluetoothA2dp, BluetoothA2dpSink, BluetoothAvrcpController, 
//            BluetoothInputDevice, BluetoothPan, BluetoothDun, BluetoothHealth, 
//            BluetoothGatt, BluetoothGattServer, BluetoothMap, BluetoothHeadsetClient, 
//            BluetoothSap, BluetoothPbapClient, BluetoothMapClient, BluetoothInputHost, 
//            BluetoothDevice, BluetoothActivityEnergyInfo, IBluetoothGatt, IBluetoothManagerCallback, 
//            BluetoothProfile

public final class BluetoothAdapter
{
    public static interface BluetoothStateChangeCallback
    {

        public abstract void onBluetoothStateChange(boolean flag);
    }

    public static interface LeScanCallback
    {

        public abstract void onLeScan(BluetoothDevice bluetoothdevice, int i, byte abyte0[]);
    }

    public class StateChangeCallbackWrapper extends IBluetoothStateChangeCallback.Stub
    {

        public void onBluetoothStateChange(boolean flag)
        {
            mCallback.onBluetoothStateChange(flag);
        }

        private BluetoothStateChangeCallback mCallback;
        final BluetoothAdapter this$0;

        StateChangeCallbackWrapper(BluetoothStateChangeCallback bluetoothstatechangecallback)
        {
            this$0 = BluetoothAdapter.this;
            super();
            mCallback = bluetoothstatechangecallback;
        }
    }


    static Map _2D_get0(BluetoothAdapter bluetoothadapter)
    {
        return bluetoothadapter.mLeScanClients;
    }

    static ArrayList _2D_get1(BluetoothAdapter bluetoothadapter)
    {
        return bluetoothadapter.mProxyServiceStateCallbacks;
    }

    static IBluetooth _2D_get2(BluetoothAdapter bluetoothadapter)
    {
        return bluetoothadapter.mService;
    }

    static ReentrantReadWriteLock _2D_get3(BluetoothAdapter bluetoothadapter)
    {
        return bluetoothadapter.mServiceLock;
    }

    static BluetoothLeAdvertiser _2D_get4()
    {
        return sBluetoothLeAdvertiser;
    }

    static BluetoothLeScanner _2D_get5()
    {
        return sBluetoothLeScanner;
    }

    static IBluetooth _2D_set0(BluetoothAdapter bluetoothadapter, IBluetooth ibluetooth)
    {
        bluetoothadapter.mService = ibluetooth;
        return ibluetooth;
    }

    BluetoothAdapter(IBluetoothManager ibluetoothmanager)
    {
        mServiceLock = new ReentrantReadWriteLock();
        mLock = new Object();
        mManagerCallback = new IBluetoothManagerCallback.Stub() {

            public void onBluetoothServiceDown()
            {
                Log.d("BluetoothAdapter", (new StringBuilder()).append("onBluetoothServiceDown: ").append(BluetoothAdapter._2D_get2(BluetoothAdapter.this)).toString());
                BluetoothAdapter._2D_get3(BluetoothAdapter.this).writeLock().lock();
                BluetoothAdapter._2D_set0(BluetoothAdapter.this, null);
                if(BluetoothAdapter._2D_get0(BluetoothAdapter.this) != null)
                    BluetoothAdapter._2D_get0(BluetoothAdapter.this).clear();
                if(BluetoothAdapter._2D_get4() != null)
                    BluetoothAdapter._2D_get4().cleanup();
                if(BluetoothAdapter._2D_get5() != null)
                    BluetoothAdapter._2D_get5().cleanup();
                BluetoothAdapter._2D_get3(BluetoothAdapter.this).writeLock().unlock();
                Object obj = BluetoothAdapter._2D_get1(BluetoothAdapter.this);
                obj;
                JVM INSTR monitorenter ;
                Object obj1;
                obj1 = JVM INSTR new #23  <Class StringBuilder>;
                ((StringBuilder) (obj1)).StringBuilder();
                Log.d("BluetoothAdapter", ((StringBuilder) (obj1)).append("onBluetoothServiceDown: Sending callbacks to ").append(BluetoothAdapter._2D_get1(BluetoothAdapter.this).size()).append(" clients").toString());
                obj1 = BluetoothAdapter._2D_get1(BluetoothAdapter.this).iterator();
_L1:
                IBluetoothManagerCallback ibluetoothmanagercallback;
                if(!((Iterator) (obj1)).hasNext())
                    break MISSING_BLOCK_LABEL_253;
                ibluetoothmanagercallback = (IBluetoothManagerCallback)((Iterator) (obj1)).next();
                if(ibluetoothmanagercallback == null)
                    break MISSING_BLOCK_LABEL_242;
                ibluetoothmanagercallback.onBluetoothServiceDown();
                  goto _L1
                Exception exception1;
                exception1;
                Log.e("BluetoothAdapter", "", exception1);
                  goto _L1
                Exception exception;
                exception;
                throw exception;
                obj;
                BluetoothAdapter._2D_get3(BluetoothAdapter.this).writeLock().unlock();
                throw obj;
                Log.d("BluetoothAdapter", "onBluetoothServiceDown: cb is null!");
                  goto _L1
                obj;
                JVM INSTR monitorexit ;
                Log.d("BluetoothAdapter", "onBluetoothServiceDown: Finished sending callbacks to registered clients");
                return;
            }

            public void onBluetoothServiceUp(IBluetooth ibluetooth)
            {
                Log.d("BluetoothAdapter", (new StringBuilder()).append("onBluetoothServiceUp: ").append(ibluetooth).toString());
                BluetoothAdapter._2D_get3(BluetoothAdapter.this).writeLock().lock();
                BluetoothAdapter._2D_set0(BluetoothAdapter.this, ibluetooth);
                BluetoothAdapter._2D_get3(BluetoothAdapter.this).writeLock().unlock();
                ArrayList arraylist = BluetoothAdapter._2D_get1(BluetoothAdapter.this);
                arraylist;
                JVM INSTR monitorenter ;
                Iterator iterator = BluetoothAdapter._2D_get1(BluetoothAdapter.this).iterator();
_L1:
                IBluetoothManagerCallback ibluetoothmanagercallback;
                if(!iterator.hasNext())
                    break MISSING_BLOCK_LABEL_150;
                ibluetoothmanagercallback = (IBluetoothManagerCallback)iterator.next();
                if(ibluetoothmanagercallback == null)
                    break MISSING_BLOCK_LABEL_139;
                ibluetoothmanagercallback.onBluetoothServiceUp(ibluetooth);
                  goto _L1
                Exception exception;
                exception;
                Log.e("BluetoothAdapter", "", exception);
                  goto _L1
                ibluetooth;
                throw ibluetooth;
                Log.d("BluetoothAdapter", "onBluetoothServiceUp: cb is null!");
                  goto _L1
                arraylist;
                JVM INSTR monitorexit ;
            }

            public void onBrEdrDown()
            {
            }

            final BluetoothAdapter this$0;

            
            {
                this$0 = BluetoothAdapter.this;
                super();
            }
        }
;
        mProxyServiceStateCallbacks = new ArrayList();
        if(ibluetoothmanager == null)
            throw new IllegalArgumentException("bluetooth manager service is null");
        mServiceLock.writeLock().lock();
        mService = ibluetoothmanager.registerAdapter(mManagerCallback);
        mServiceLock.writeLock().unlock();
_L2:
        mManagerService = ibluetoothmanager;
        mLeScanClients = new HashMap();
        mToken = new Binder();
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothAdapter", "", remoteexception);
        mServiceLock.writeLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        ibluetoothmanager;
        mServiceLock.writeLock().unlock();
        throw ibluetoothmanager;
    }

    public static boolean checkBluetoothAddress(String s)
    {
        int i;
        if(s == null || s.length() != 17)
            return false;
        i = 0;
_L6:
        if(i >= 17) goto _L2; else goto _L1
_L1:
        char c = s.charAt(i);
        i % 3;
        JVM INSTR tableswitch 0 2: default 60
    //                   0 66
    //                   1 66
    //                   2 92;
           goto _L3 _L4 _L4 _L5
_L3:
        i++;
          goto _L6
_L4:
        if(c >= '0' && c <= '9' || c >= 'A' && c <= 'F') goto _L3; else goto _L7
_L7:
        return false;
_L5:
        if(c == ':') goto _L3; else goto _L8
_L8:
        return false;
_L2:
        return true;
    }

    private BluetoothServerSocket createNewRfcommSocketAndRecord(String s, UUID uuid, boolean flag, boolean flag1)
        throws IOException
    {
        uuid = new BluetoothServerSocket(1, flag, flag1, new ParcelUuid(uuid));
        uuid.setServiceName(s);
        int i = ((BluetoothServerSocket) (uuid)).mSocket.bindListen();
        if(i != 0)
            throw new IOException((new StringBuilder()).append("Error: ").append(i).toString());
        else
            return uuid;
    }

    public static BluetoothAdapter getDefaultAdapter()
    {
        android/bluetooth/BluetoothAdapter;
        JVM INSTR monitorenter ;
        Object obj;
        if(sAdapter != null)
            break MISSING_BLOCK_LABEL_37;
        obj = ServiceManager.getService("bluetooth_manager");
        if(obj == null)
            break MISSING_BLOCK_LABEL_46;
        IBluetoothManager ibluetoothmanager = IBluetoothManager.Stub.asInterface(((IBinder) (obj)));
        obj = JVM INSTR new #2   <Class BluetoothAdapter>;
        ((BluetoothAdapter) (obj)).BluetoothAdapter(ibluetoothmanager);
        sAdapter = ((BluetoothAdapter) (obj));
_L1:
        obj = sAdapter;
        android/bluetooth/BluetoothAdapter;
        JVM INSTR monitorexit ;
        return ((BluetoothAdapter) (obj));
        Log.e("BluetoothAdapter", "Bluetooth binder is null");
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public static BluetoothServerSocket listenUsingScoOn()
        throws IOException
    {
        BluetoothServerSocket bluetoothserversocket = new BluetoothServerSocket(2, false, false, -1);
        bluetoothserversocket.mSocket.bindListen();
        return bluetoothserversocket;
    }

    public static String nameForState(int i)
    {
        switch(i)
        {
        default:
            return (new StringBuilder()).append("?!?!? (").append(i).append(")").toString();

        case 10: // '\n'
            return "OFF";

        case 11: // '\013'
            return "TURNING_ON";

        case 12: // '\f'
            return "ON";

        case 13: // '\r'
            return "TURNING_OFF";

        case 14: // '\016'
            return "BLE_TURNING_ON";

        case 15: // '\017'
            return "BLE_ON";

        case 16: // '\020'
            return "BLE_TURNING_OFF";
        }
    }

    private Set toDeviceSet(BluetoothDevice abluetoothdevice[])
    {
        return Collections.unmodifiableSet(new HashSet(Arrays.asList(abluetoothdevice)));
    }

    public boolean cancelDiscovery()
    {
        if(getState() != 12)
            return false;
        boolean flag;
        mServiceLock.readLock().lock();
        if(mService == null)
            break MISSING_BLOCK_LABEL_50;
        flag = mService.cancelDiscovery();
        mServiceLock.readLock().unlock();
        return flag;
        mServiceLock.readLock().unlock();
_L2:
        return false;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public boolean changeApplicationBluetoothState(boolean flag, BluetoothStateChangeCallback bluetoothstatechangecallback)
    {
        return false;
    }

    public void closeProfileProxy(int i, BluetoothProfile bluetoothprofile)
    {
        if(bluetoothprofile == null)
            return;
        i;
        JVM INSTR tableswitch 1 20: default 100
    //                   1 101
    //                   2 111
    //                   3 171
    //                   4 141
    //                   5 151
    //                   6 100
    //                   7 181
    //                   8 191
    //                   9 201
    //                   10 221
    //                   11 121
    //                   12 131
    //                   13 100
    //                   14 100
    //                   15 100
    //                   16 211
    //                   17 231
    //                   18 241
    //                   19 251
    //                   20 161;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L1 _L7 _L8 _L9 _L10 _L11 _L12 _L1 _L1 _L1 _L13 _L14 _L15 _L16 _L17
_L1:
        return;
_L2:
        ((BluetoothHeadset)bluetoothprofile).close();
        continue; /* Loop/switch isn't completed */
_L3:
        ((BluetoothA2dp)bluetoothprofile).close();
        continue; /* Loop/switch isn't completed */
_L11:
        ((BluetoothA2dpSink)bluetoothprofile).close();
        continue; /* Loop/switch isn't completed */
_L12:
        ((BluetoothAvrcpController)bluetoothprofile).close();
        continue; /* Loop/switch isn't completed */
_L5:
        ((BluetoothInputDevice)bluetoothprofile).close();
        continue; /* Loop/switch isn't completed */
_L6:
        ((BluetoothPan)bluetoothprofile).close();
        continue; /* Loop/switch isn't completed */
_L17:
        ((BluetoothDun)bluetoothprofile).close();
        continue; /* Loop/switch isn't completed */
_L4:
        ((BluetoothHealth)bluetoothprofile).close();
        continue; /* Loop/switch isn't completed */
_L7:
        ((BluetoothGatt)bluetoothprofile).close();
        continue; /* Loop/switch isn't completed */
_L8:
        ((BluetoothGattServer)bluetoothprofile).close();
        continue; /* Loop/switch isn't completed */
_L9:
        ((BluetoothMap)bluetoothprofile).close();
        continue; /* Loop/switch isn't completed */
_L13:
        ((BluetoothHeadsetClient)bluetoothprofile).close();
        continue; /* Loop/switch isn't completed */
_L10:
        ((BluetoothSap)bluetoothprofile).close();
        continue; /* Loop/switch isn't completed */
_L14:
        ((BluetoothPbapClient)bluetoothprofile).close();
        continue; /* Loop/switch isn't completed */
_L15:
        ((BluetoothMapClient)bluetoothprofile).close();
        continue; /* Loop/switch isn't completed */
_L16:
        ((BluetoothInputHost)bluetoothprofile).close();
        if(true) goto _L1; else goto _L18
_L18:
    }

    public boolean disable()
    {
        SeempLog.record(57);
        boolean flag;
        try
        {
            flag = mManagerService.disable(ActivityThread.currentPackageName(), true);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothAdapter", "", remoteexception);
            return false;
        }
        return flag;
    }

    public boolean disable(boolean flag)
    {
        SeempLog.record(57);
        try
        {
            flag = mManagerService.disable(ActivityThread.currentPackageName(), flag);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothAdapter", "", remoteexception);
            return false;
        }
        return flag;
    }

    public boolean disableBLE()
    {
        if(!isBleScanAlwaysAvailable())
            return false;
        int i = getLeState();
        if(i == 12 || i == 15)
        {
            String s = ActivityThread.currentPackageName();
            Log.d("BluetoothAdapter", (new StringBuilder()).append("disableBLE(): de-registering ").append(s).toString());
            try
            {
                mManagerService.updateBleAppCount(mToken, false, s);
            }
            catch(RemoteException remoteexception)
            {
                Log.e("BluetoothAdapter", "", remoteexception);
            }
            return true;
        } else
        {
            Log.d("BluetoothAdapter", "disableBLE(): Already disabled");
            return false;
        }
    }

    public boolean enable()
    {
        SeempLog.record(56);
        if(isEnabled())
        {
            Log.d("BluetoothAdapter", "enable(): BT already enabled!");
            return true;
        }
        boolean flag;
        try
        {
            flag = mManagerService.enable(ActivityThread.currentPackageName());
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothAdapter", "", remoteexception);
            return false;
        }
        return flag;
    }

    public boolean enableBLE()
    {
        if(!isBleScanAlwaysAvailable())
            return false;
        String s;
        s = ActivityThread.currentPackageName();
        mManagerService.updateBleAppCount(mToken, true, s);
        if(!isLeEnabled())
            break MISSING_BLOCK_LABEL_47;
        Log.d("BluetoothAdapter", "enableBLE(): Bluetooth already enabled");
        return true;
        boolean flag;
        try
        {
            Log.d("BluetoothAdapter", "enableBLE(): Calling enable");
            flag = mManagerService.enable(s);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothAdapter", "", remoteexception);
            return false;
        }
        return flag;
    }

    public boolean enableNoAutoConnect()
    {
        if(isEnabled())
        {
            Log.d("BluetoothAdapter", "enableNoAutoConnect(): BT already enabled!");
            return true;
        }
        boolean flag;
        try
        {
            flag = mManagerService.enableNoAutoConnect(ActivityThread.currentPackageName());
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothAdapter", "", remoteexception);
            return false;
        }
        return flag;
    }

    public boolean factoryReset()
    {
        boolean flag;
        mServiceLock.readLock().lock();
        if(mManagerService == null)
            break MISSING_BLOCK_LABEL_48;
        SystemProperties.set("persist.bluetooth.factoryreset", "true");
        flag = mManagerService.factoryReset();
        mServiceLock.readLock().unlock();
        return flag;
        mServiceLock.readLock().unlock();
_L2:
        return false;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    protected void finalize()
        throws Throwable
    {
        mManagerService.unregisterAdapter(mManagerCallback);
        super.finalize();
_L2:
        return;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "", ((Throwable) (obj)));
        super.finalize();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        super.finalize();
        throw obj;
    }

    public String getAddress()
    {
        String s;
        try
        {
            s = mManagerService.getAddress();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothAdapter", "", remoteexception);
            return null;
        }
        return s;
    }

    public BluetoothLeAdvertiser getBluetoothLeAdvertiser()
    {
        if(!getLeAccess())
            return null;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(sBluetoothLeAdvertiser == null)
        {
            BluetoothLeAdvertiser bluetoothleadvertiser = JVM INSTR new #532 <Class BluetoothLeAdvertiser>;
            bluetoothleadvertiser.BluetoothLeAdvertiser(mManagerService);
            sBluetoothLeAdvertiser = bluetoothleadvertiser;
        }
        obj;
        JVM INSTR monitorexit ;
        return sBluetoothLeAdvertiser;
        Exception exception;
        exception;
        throw exception;
    }

    public BluetoothLeScanner getBluetoothLeScanner()
    {
        if(!getLeAccess())
            return null;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(sBluetoothLeScanner == null)
        {
            BluetoothLeScanner bluetoothlescanner = JVM INSTR new #536 <Class BluetoothLeScanner>;
            bluetoothlescanner.BluetoothLeScanner(mManagerService);
            sBluetoothLeScanner = bluetoothlescanner;
        }
        obj;
        JVM INSTR monitorexit ;
        return sBluetoothLeScanner;
        Exception exception;
        exception;
        throw exception;
    }

    IBluetoothManager getBluetoothManager()
    {
        return mManagerService;
    }

    IBluetooth getBluetoothService(IBluetoothManagerCallback ibluetoothmanagercallback)
    {
        ArrayList arraylist = mProxyServiceStateCallbacks;
        arraylist;
        JVM INSTR monitorenter ;
        if(ibluetoothmanagercallback != null) goto _L2; else goto _L1
_L1:
        Log.w("BluetoothAdapter", "getBluetoothService() called with no BluetoothManagerCallback");
_L4:
        arraylist;
        JVM INSTR monitorexit ;
        return mService;
_L2:
        if(mProxyServiceStateCallbacks.contains(ibluetoothmanagercallback)) goto _L4; else goto _L3
_L3:
        mProxyServiceStateCallbacks.add(ibluetoothmanagercallback);
          goto _L4
        ibluetoothmanagercallback;
        throw ibluetoothmanagercallback;
    }

    public Set getBondedDevices()
    {
        SeempLog.record(61);
        if(getState() != 12)
            return toDeviceSet(new BluetoothDevice[0]);
        Set set;
        mServiceLock.readLock().lock();
        if(mService == null)
            break MISSING_BLOCK_LABEL_67;
        set = toDeviceSet(mService.getBondedDevices());
        mServiceLock.readLock().unlock();
        return set;
        set = toDeviceSet(new BluetoothDevice[0]);
        mServiceLock.readLock().unlock();
        return set;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        return null;
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public int getConnectionState()
    {
        if(getState() != 12)
            return 0;
        int i;
        mServiceLock.readLock().lock();
        if(mService == null)
            break MISSING_BLOCK_LABEL_50;
        i = mService.getAdapterConnectionState();
        mServiceLock.readLock().unlock();
        return i;
        mServiceLock.readLock().unlock();
_L2:
        return 0;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "getConnectionState:", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public BluetoothActivityEnergyInfo getControllerActivityEnergyInfo(int i)
    {
        Object obj;
        obj = new SynchronousResultReceiver();
        requestControllerActivityEnergyInfo(((ResultReceiver) (obj)));
        obj = ((SynchronousResultReceiver) (obj)).awaitResult(1000L);
        if(((android.os.SynchronousResultReceiver.Result) (obj)).bundle == null)
            break MISSING_BLOCK_LABEL_54;
        obj = (BluetoothActivityEnergyInfo)((android.os.SynchronousResultReceiver.Result) (obj)).bundle.getParcelable("controller_activity");
        return ((BluetoothActivityEnergyInfo) (obj));
        TimeoutException timeoutexception;
        timeoutexception;
        Log.e("BluetoothAdapter", "getControllerActivityEnergyInfo timed out");
        return null;
    }

    public int getDiscoverableTimeout()
    {
        if(getState() != 12)
            return -1;
        int i;
        mServiceLock.readLock().lock();
        if(mService == null)
            break MISSING_BLOCK_LABEL_50;
        i = mService.getDiscoverableTimeout();
        mServiceLock.readLock().unlock();
        return i;
        mServiceLock.readLock().unlock();
_L2:
        return -1;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public long getDiscoveryEndMillis()
    {
        long l;
        mServiceLock.readLock().lock();
        if(mService == null)
            break MISSING_BLOCK_LABEL_39;
        l = mService.getDiscoveryEndMillis();
        mServiceLock.readLock().unlock();
        return l;
        mServiceLock.readLock().unlock();
_L2:
        return -1L;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    boolean getLeAccess()
    {
        if(getLeState() == 12)
            return true;
        return getLeState() == 15;
    }

    public int getLeMaximumAdvertisingDataLength()
    {
        if(!getLeAccess())
            return 0;
        int i;
        mServiceLock.readLock().lock();
        if(mService == null)
            break MISSING_BLOCK_LABEL_48;
        i = mService.getLeMaximumAdvertisingDataLength();
        mServiceLock.readLock().unlock();
        return i;
        mServiceLock.readLock().unlock();
_L2:
        return 0;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "failed to get getLeMaximumAdvertisingDataLength, error: ", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public int getLeState()
    {
        byte byte0 = 10;
        mServiceLock.readLock().lock();
        int i = byte0;
        if(mService != null)
            i = mService.getState();
        mServiceLock.readLock().unlock();
_L2:
        return i;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        i = byte0;
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public String getName()
    {
        String s;
        try
        {
            s = mManagerService.getName();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothAdapter", "", remoteexception);
            return null;
        }
        return s;
    }

    public PeriodicAdvertisingManager getPeriodicAdvertisingManager()
    {
        if(!getLeAccess())
            return null;
        if(!isLePeriodicAdvertisingSupported())
            return null;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(sPeriodicAdvertisingManager == null)
        {
            PeriodicAdvertisingManager periodicadvertisingmanager = JVM INSTR new #632 <Class PeriodicAdvertisingManager>;
            periodicadvertisingmanager.PeriodicAdvertisingManager(mManagerService);
            sPeriodicAdvertisingManager = periodicadvertisingmanager;
        }
        obj;
        JVM INSTR monitorexit ;
        return sPeriodicAdvertisingManager;
        Exception exception;
        exception;
        throw exception;
    }

    public int getProfileConnectionState(int i)
    {
        SeempLog.record(64);
        if(getState() != 12)
            return 0;
        mServiceLock.readLock().lock();
        if(mService == null)
            break MISSING_BLOCK_LABEL_57;
        i = mService.getProfileConnectionState(i);
        mServiceLock.readLock().unlock();
        return i;
        mServiceLock.readLock().unlock();
_L2:
        return 0;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "getProfileConnectionState:", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public boolean getProfileProxy(Context context, BluetoothProfile.ServiceListener servicelistener, int i)
    {
        if(context == null || servicelistener == null)
            return false;
        if(i == 1)
        {
            new BluetoothHeadset(context, servicelistener);
            return true;
        }
        if(i == 2)
        {
            new BluetoothA2dp(context, servicelistener);
            return true;
        }
        if(i == 11)
        {
            new BluetoothA2dpSink(context, servicelistener);
            return true;
        }
        if(i == 12)
        {
            new BluetoothAvrcpController(context, servicelistener);
            return true;
        }
        if(i == 4)
        {
            new BluetoothInputDevice(context, servicelistener);
            return true;
        }
        if(i == 5)
        {
            new BluetoothPan(context, servicelistener);
            return true;
        }
        if(i == 20)
        {
            new BluetoothDun(context, servicelistener);
            return true;
        }
        if(i == 3)
        {
            new BluetoothHealth(context, servicelistener);
            return true;
        }
        if(i == 9)
        {
            new BluetoothMap(context, servicelistener);
            return true;
        }
        if(i == 16)
        {
            new BluetoothHeadsetClient(context, servicelistener);
            return true;
        }
        if(i == 10)
        {
            new BluetoothSap(context, servicelistener);
            return true;
        }
        if(i == 17)
        {
            new BluetoothPbapClient(context, servicelistener);
            return true;
        }
        if(i == 18)
        {
            new BluetoothMapClient(context, servicelistener);
            return true;
        }
        if(i == 19)
        {
            new BluetoothInputHost(context, servicelistener);
            return true;
        } else
        {
            return false;
        }
    }

    public BluetoothDevice getRemoteDevice(String s)
    {
        SeempLog.record(62);
        return new BluetoothDevice(s);
    }

    public BluetoothDevice getRemoteDevice(byte abyte0[])
    {
        SeempLog.record(62);
        if(abyte0 == null || abyte0.length != 6)
            throw new IllegalArgumentException("Bluetooth address must have 6 bytes");
        else
            return new BluetoothDevice(String.format(Locale.US, "%02X:%02X:%02X:%02X:%02X:%02X", new Object[] {
                Byte.valueOf(abyte0[0]), Byte.valueOf(abyte0[1]), Byte.valueOf(abyte0[2]), Byte.valueOf(abyte0[3]), Byte.valueOf(abyte0[4]), Byte.valueOf(abyte0[5])
            }));
    }

    public int getScanMode()
    {
        if(getState() != 12)
            return 20;
        int i;
        mServiceLock.readLock().lock();
        if(mService == null)
            break MISSING_BLOCK_LABEL_51;
        i = mService.getScanMode();
        mServiceLock.readLock().unlock();
        return i;
        mServiceLock.readLock().unlock();
_L2:
        return 20;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public int getState()
    {
        int i;
        SeempLog.record(63);
        i = 10;
        mServiceLock.readLock().lock();
        int j = i;
        if(mService != null)
            j = mService.getState();
        mServiceLock.readLock().unlock();
_L3:
        if(j != 15 && j != 14) goto _L2; else goto _L1
_L1:
        i = 10;
_L4:
        return i;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        j = i;
          goto _L3
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
_L2:
        i = j;
        if(j != 16) goto _L4; else goto _L1
    }

    public List getSupportedProfiles()
    {
        ArrayList arraylist = new ArrayList();
        IBluetoothManagerCallback ibluetoothmanagercallback = mManagerCallback;
        ibluetoothmanagercallback;
        JVM INSTR monitorenter ;
        long l;
        if(mService == null)
            break MISSING_BLOCK_LABEL_70;
        l = mService.getSupportedProfiles();
        int i = 0;
_L3:
        if(i > 20) goto _L2; else goto _L1
_L1:
        if(((long)(1 << i) & l) == 0L)
            continue; /* Loop/switch isn't completed */
        arraylist.add(Integer.valueOf(i));
        i++;
          goto _L3
_L2:
        ibluetoothmanagercallback;
        JVM INSTR monitorexit ;
_L5:
        return arraylist;
        Exception exception;
        exception;
        ibluetoothmanagercallback;
        JVM INSTR monitorexit ;
        throw exception;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothAdapter", "getSupportedProfiles:", remoteexception);
        if(true) goto _L5; else goto _L4
_L4:
    }

    public ParcelUuid[] getUuids()
    {
        if(getState() != 12)
            return null;
        ParcelUuid aparceluuid[];
        mServiceLock.readLock().lock();
        if(mService == null)
            break MISSING_BLOCK_LABEL_50;
        aparceluuid = mService.getUuids();
        mServiceLock.readLock().unlock();
        return aparceluuid;
        mServiceLock.readLock().unlock();
_L2:
        return null;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public boolean isBleScanAlwaysAvailable()
    {
        boolean flag;
        try
        {
            flag = mManagerService.isBleScanAlwaysAvailable();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothAdapter", "remote expection when calling isBleScanAlwaysAvailable", remoteexception);
            return false;
        }
        return flag;
    }

    public boolean isDiscovering()
    {
        if(getState() != 12)
            return false;
        boolean flag;
        mServiceLock.readLock().lock();
        if(mService == null)
            break MISSING_BLOCK_LABEL_50;
        flag = mService.isDiscovering();
        mServiceLock.readLock().unlock();
        return flag;
        mServiceLock.readLock().unlock();
_L2:
        return false;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public boolean isEnabled()
    {
        boolean flag;
        mServiceLock.readLock().lock();
        if(mService == null)
            break MISSING_BLOCK_LABEL_39;
        flag = mService.isEnabled();
        mServiceLock.readLock().unlock();
        return flag;
        mServiceLock.readLock().unlock();
_L2:
        return false;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public boolean isHardwareTrackingFiltersAvailable()
    {
        boolean flag = false;
        if(!getLeAccess())
            return false;
        IBluetoothGatt ibluetoothgatt;
        int i;
        try
        {
            ibluetoothgatt = mManagerService.getBluetoothGatt();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothAdapter", "", remoteexception);
            return false;
        }
        if(ibluetoothgatt == null)
            return false;
        i = ibluetoothgatt.numHwTrackFiltersAvailable();
        if(i != 0)
            flag = true;
        return flag;
    }

    public boolean isLe2MPhySupported()
    {
        if(!getLeAccess())
            return false;
        boolean flag;
        mServiceLock.readLock().lock();
        if(mService == null)
            break MISSING_BLOCK_LABEL_48;
        flag = mService.isLe2MPhySupported();
        mServiceLock.readLock().unlock();
        return flag;
        mServiceLock.readLock().unlock();
_L2:
        return false;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "failed to get isExtendedAdvertisingSupported, error: ", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public boolean isLeCodedPhySupported()
    {
        if(!getLeAccess())
            return false;
        boolean flag;
        mServiceLock.readLock().lock();
        if(mService == null)
            break MISSING_BLOCK_LABEL_48;
        flag = mService.isLeCodedPhySupported();
        mServiceLock.readLock().unlock();
        return flag;
        mServiceLock.readLock().unlock();
_L2:
        return false;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "failed to get isLeCodedPhySupported, error: ", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public boolean isLeEnabled()
    {
        boolean flag = true;
        int i = getLeState();
        Log.d("BluetoothAdapter", (new StringBuilder()).append("isLeEnabled(): ").append(nameForState(i)).toString());
        boolean flag1 = flag;
        if(i != 12)
            if(i == 15)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public boolean isLeExtendedAdvertisingSupported()
    {
        if(!getLeAccess())
            return false;
        boolean flag;
        mServiceLock.readLock().lock();
        if(mService == null)
            break MISSING_BLOCK_LABEL_48;
        flag = mService.isLeExtendedAdvertisingSupported();
        mServiceLock.readLock().unlock();
        return flag;
        mServiceLock.readLock().unlock();
_L2:
        return false;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "failed to get isLeExtendedAdvertisingSupported, error: ", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public boolean isLePeriodicAdvertisingSupported()
    {
        if(!getLeAccess())
            return false;
        boolean flag;
        mServiceLock.readLock().lock();
        if(mService == null)
            break MISSING_BLOCK_LABEL_48;
        flag = mService.isLePeriodicAdvertisingSupported();
        mServiceLock.readLock().unlock();
        return flag;
        mServiceLock.readLock().unlock();
_L2:
        return false;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "failed to get isLePeriodicAdvertisingSupported, error: ", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public boolean isMultipleAdvertisementSupported()
    {
        if(getState() != 12)
            return false;
        boolean flag;
        mServiceLock.readLock().lock();
        if(mService == null)
            break MISSING_BLOCK_LABEL_50;
        flag = mService.isMultiAdvertisementSupported();
        mServiceLock.readLock().unlock();
        return flag;
        mServiceLock.readLock().unlock();
_L2:
        return false;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "failed to get isMultipleAdvertisementSupported, error: ", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public boolean isOffloadedFilteringSupported()
    {
        if(!getLeAccess())
            return false;
        boolean flag;
        mServiceLock.readLock().lock();
        if(mService == null)
            break MISSING_BLOCK_LABEL_48;
        flag = mService.isOffloadedFilteringSupported();
        mServiceLock.readLock().unlock();
        return flag;
        mServiceLock.readLock().unlock();
_L2:
        return false;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "failed to get isOffloadedFilteringSupported, error: ", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public boolean isOffloadedScanBatchingSupported()
    {
        if(!getLeAccess())
            return false;
        boolean flag;
        mServiceLock.readLock().lock();
        if(mService == null)
            break MISSING_BLOCK_LABEL_48;
        flag = mService.isOffloadedScanBatchingSupported();
        mServiceLock.readLock().unlock();
        return flag;
        mServiceLock.readLock().unlock();
_L2:
        return false;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "failed to get isOffloadedScanBatchingSupported, error: ", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public BluetoothServerSocket listenUsingEncryptedRfcommOn(int i)
        throws IOException
    {
        BluetoothServerSocket bluetoothserversocket = new BluetoothServerSocket(1, false, true, i);
        int j = bluetoothserversocket.mSocket.bindListen();
        if(i == -2)
            bluetoothserversocket.setChannel(bluetoothserversocket.mSocket.getPort());
        if(j < 0)
            throw new IOException((new StringBuilder()).append("Error: ").append(j).toString());
        else
            return bluetoothserversocket;
    }

    public BluetoothServerSocket listenUsingEncryptedRfcommWithServiceRecord(String s, UUID uuid)
        throws IOException
    {
        return createNewRfcommSocketAndRecord(s, uuid, false, true);
    }

    public BluetoothServerSocket listenUsingInsecureL2capOn(int i)
        throws IOException
    {
        BluetoothServerSocket bluetoothserversocket = new BluetoothServerSocket(3, false, false, i, false, false);
        int j = bluetoothserversocket.mSocket.bindListen();
        if(i == -2)
            bluetoothserversocket.setChannel(bluetoothserversocket.mSocket.getPort());
        if(j != 0)
            throw new IOException((new StringBuilder()).append("Error: ").append(j).toString());
        else
            return bluetoothserversocket;
    }

    public BluetoothServerSocket listenUsingInsecureRfcommOn(int i)
        throws IOException
    {
        BluetoothServerSocket bluetoothserversocket = new BluetoothServerSocket(1, false, false, i);
        int j = bluetoothserversocket.mSocket.bindListen();
        if(i == -2)
            bluetoothserversocket.setChannel(bluetoothserversocket.mSocket.getPort());
        if(j != 0)
            throw new IOException((new StringBuilder()).append("Error: ").append(j).toString());
        else
            return bluetoothserversocket;
    }

    public BluetoothServerSocket listenUsingInsecureRfcommWithServiceRecord(String s, UUID uuid)
        throws IOException
    {
        SeempLog.record(59);
        return createNewRfcommSocketAndRecord(s, uuid, false, false);
    }

    public BluetoothServerSocket listenUsingL2capOn(int i)
        throws IOException
    {
        return listenUsingL2capOn(i, false, false);
    }

    public BluetoothServerSocket listenUsingL2capOn(int i, boolean flag, boolean flag1)
        throws IOException
    {
        BluetoothServerSocket bluetoothserversocket = new BluetoothServerSocket(3, true, true, i, flag, flag1);
        int j = bluetoothserversocket.mSocket.bindListen();
        if(i == -2)
            bluetoothserversocket.setChannel(bluetoothserversocket.mSocket.getPort());
        if(j != 0)
            throw new IOException((new StringBuilder()).append("Error: ").append(j).toString());
        else
            return bluetoothserversocket;
    }

    public BluetoothServerSocket listenUsingRfcommOn(int i)
        throws IOException
    {
        return listenUsingRfcommOn(i, false, false);
    }

    public BluetoothServerSocket listenUsingRfcommOn(int i, boolean flag, boolean flag1)
        throws IOException
    {
        BluetoothServerSocket bluetoothserversocket = new BluetoothServerSocket(1, true, true, i, flag, flag1);
        int j = bluetoothserversocket.mSocket.bindListen();
        if(i == -2)
            bluetoothserversocket.setChannel(bluetoothserversocket.mSocket.getPort());
        if(j != 0)
            throw new IOException((new StringBuilder()).append("Error: ").append(j).toString());
        else
            return bluetoothserversocket;
    }

    public BluetoothServerSocket listenUsingRfcommWithServiceRecord(String s, UUID uuid)
        throws IOException
    {
        return createNewRfcommSocketAndRecord(s, uuid, true, true);
    }

    public Pair readOutOfBandData()
    {
        return null;
    }

    void removeServiceStateCallback(IBluetoothManagerCallback ibluetoothmanagercallback)
    {
        ArrayList arraylist = mProxyServiceStateCallbacks;
        arraylist;
        JVM INSTR monitorenter ;
        mProxyServiceStateCallbacks.remove(ibluetoothmanagercallback);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        ibluetoothmanagercallback;
        throw ibluetoothmanagercallback;
    }

    public void requestControllerActivityEnergyInfo(ResultReceiver resultreceiver)
    {
        mServiceLock.readLock().lock();
        ResultReceiver resultreceiver1 = resultreceiver;
        if(mService == null)
            break MISSING_BLOCK_LABEL_31;
        mService.requestActivityInfo(resultreceiver);
        resultreceiver1 = null;
        mServiceLock.readLock().unlock();
        if(resultreceiver1 != null)
            resultreceiver1.send(0, null);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        StringBuilder stringbuilder = JVM INSTR new #290 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e("BluetoothAdapter", stringbuilder.append("getControllerActivityEnergyInfoCallback: ").append(remoteexception).toString());
        mServiceLock.readLock().unlock();
        if(resultreceiver != null)
            resultreceiver.send(0, null);
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        mServiceLock.readLock().unlock();
        if(resultreceiver != null)
            resultreceiver.send(0, null);
        throw exception;
    }

    public void setDiscoverableTimeout(int i)
    {
        if(getState() != 12)
            return;
        mServiceLock.readLock().lock();
        if(mService != null)
            mService.setDiscoverableTimeout(i);
        mServiceLock.readLock().unlock();
_L2:
        return;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public boolean setName(String s)
    {
        if(getState() != 12)
            return false;
        boolean flag;
        mServiceLock.readLock().lock();
        if(mService == null)
            break MISSING_BLOCK_LABEL_51;
        flag = mService.setName(s);
        mServiceLock.readLock().unlock();
        return flag;
        mServiceLock.readLock().unlock();
_L2:
        return false;
        s;
        Log.e("BluetoothAdapter", "", s);
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        s;
        mServiceLock.readLock().unlock();
        throw s;
    }

    public boolean setScanMode(int i)
    {
        if(getState() != 12)
            return false;
        else
            return setScanMode(i, getDiscoverableTimeout());
    }

    public boolean setScanMode(int i, int j)
    {
        if(getState() != 12)
            return false;
        boolean flag;
        mServiceLock.readLock().lock();
        if(mService == null)
            break MISSING_BLOCK_LABEL_52;
        flag = mService.setScanMode(i, j);
        mServiceLock.readLock().unlock();
        return flag;
        mServiceLock.readLock().unlock();
_L2:
        return false;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public boolean startDiscovery()
    {
        SeempLog.record(58);
        if(getState() != 12)
            return false;
        boolean flag;
        mServiceLock.readLock().lock();
        if(mService == null)
            break MISSING_BLOCK_LABEL_56;
        flag = mService.startDiscovery();
        mServiceLock.readLock().unlock();
        return flag;
        mServiceLock.readLock().unlock();
_L2:
        return false;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "", ((Throwable) (obj)));
        mServiceLock.readLock().unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mServiceLock.readLock().unlock();
        throw obj;
    }

    public boolean startLeScan(LeScanCallback lescancallback)
    {
        return startLeScan(null, lescancallback);
    }

    public boolean startLeScan(UUID auuid[], LeScanCallback lescancallback)
    {
        BluetoothLeScanner bluetoothlescanner;
        Log.d("BluetoothAdapter", (new StringBuilder()).append("startLeScan(): ").append(Arrays.toString(auuid)).toString());
        if(lescancallback == null)
        {
            Log.e("BluetoothAdapter", "startLeScan: null callback");
            return false;
        }
        bluetoothlescanner = getBluetoothLeScanner();
        if(bluetoothlescanner == null)
        {
            Log.e("BluetoothAdapter", "startLeScan: cannot get BluetoothLeScanner");
            return false;
        }
        Map map = mLeScanClients;
        map;
        JVM INSTR monitorenter ;
        if(!mLeScanClients.containsKey(lescancallback))
            break MISSING_BLOCK_LABEL_100;
        Log.e("BluetoothAdapter", "LE Scan has already started");
        map;
        JVM INSTR monitorexit ;
        return false;
        Object obj = mManagerService.getBluetoothGatt();
        if(obj != null)
            break MISSING_BLOCK_LABEL_121;
        map;
        JVM INSTR monitorexit ;
        return false;
        android.bluetooth.le.ScanSettings scansettings;
        ArrayList arraylist;
        obj = JVM INSTR new #8   <Class BluetoothAdapter$2>;
        ((_cls2) (obj)).this. _cls2();
        android.bluetooth.le.ScanSettings.Builder builder = JVM INSTR new #846 <Class android.bluetooth.le.ScanSettings$Builder>;
        builder.android.bluetooth.le.ScanSettings.Builder();
        scansettings = builder.setCallbackType(1).setScanMode(2).build();
        arraylist = JVM INSTR new #206 <Class ArrayList>;
        arraylist.ArrayList();
        if(auuid == null)
            break MISSING_BLOCK_LABEL_219;
        if(auuid.length > 0)
        {
            android.bluetooth.le.ScanFilter.Builder builder1 = JVM INSTR new #859 <Class android.bluetooth.le.ScanFilter$Builder>;
            builder1.android.bluetooth.le.ScanFilter.Builder();
            ParcelUuid parceluuid = JVM INSTR new #270 <Class ParcelUuid>;
            parceluuid.ParcelUuid(auuid[0]);
            arraylist.add(builder1.setServiceUuid(parceluuid).build());
        }
        bluetoothlescanner.startScan(arraylist, scansettings, ((ScanCallback) (obj)));
        mLeScanClients.put(lescancallback, obj);
        map;
        JVM INSTR monitorexit ;
        return true;
        auuid;
        Log.e("BluetoothAdapter", "", auuid);
        map;
        JVM INSTR monitorexit ;
        return false;
        auuid;
        throw auuid;
    }

    public void stopLeScan(LeScanCallback lescancallback)
    {
        BluetoothLeScanner bluetoothlescanner;
        Log.d("BluetoothAdapter", "stopLeScan()");
        bluetoothlescanner = getBluetoothLeScanner();
        if(bluetoothlescanner == null)
            return;
        Map map = mLeScanClients;
        map;
        JVM INSTR monitorenter ;
        lescancallback = (ScanCallback)mLeScanClients.remove(lescancallback);
        if(lescancallback != null)
            break MISSING_BLOCK_LABEL_56;
        Log.d("BluetoothAdapter", "scan not started yet");
        map;
        JVM INSTR monitorexit ;
        return;
        bluetoothlescanner.stopScan(lescancallback);
        map;
        JVM INSTR monitorexit ;
        return;
        lescancallback;
        throw lescancallback;
    }

    public void unregisterAdapter()
    {
        if(mManagerService != null)
            mManagerService.unregisterAdapter(mManagerCallback);
_L1:
        return;
        Object obj;
        obj;
        Log.e("BluetoothAdapter", "", ((Throwable) (obj)));
          goto _L1
        obj;
        throw obj;
    }

    public static final String ACTION_BLE_ACL_CONNECTED = "android.bluetooth.adapter.action.BLE_ACL_CONNECTED";
    public static final String ACTION_BLE_ACL_DISCONNECTED = "android.bluetooth.adapter.action.BLE_ACL_DISCONNECTED";
    public static final String ACTION_BLE_STATE_CHANGED = "android.bluetooth.adapter.action.BLE_STATE_CHANGED";
    public static final String ACTION_BLUETOOTH_ADDRESS_CHANGED = "android.bluetooth.adapter.action.BLUETOOTH_ADDRESS_CHANGED";
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED";
    public static final String ACTION_DISCOVERY_FINISHED = "android.bluetooth.adapter.action.DISCOVERY_FINISHED";
    public static final String ACTION_DISCOVERY_STARTED = "android.bluetooth.adapter.action.DISCOVERY_STARTED";
    public static final String ACTION_LOCAL_NAME_CHANGED = "android.bluetooth.adapter.action.LOCAL_NAME_CHANGED";
    public static final String ACTION_REQUEST_BLE_SCAN_ALWAYS_AVAILABLE = "android.bluetooth.adapter.action.REQUEST_BLE_SCAN_ALWAYS_AVAILABLE";
    public static final String ACTION_REQUEST_DISABLE = "android.bluetooth.adapter.action.REQUEST_DISABLE";
    public static final String ACTION_REQUEST_DISCOVERABLE = "android.bluetooth.adapter.action.REQUEST_DISCOVERABLE";
    public static final String ACTION_REQUEST_ENABLE = "android.bluetooth.adapter.action.REQUEST_ENABLE";
    public static final String ACTION_SCAN_MODE_CHANGED = "android.bluetooth.adapter.action.SCAN_MODE_CHANGED";
    public static final String ACTION_STATE_CHANGED = "android.bluetooth.adapter.action.STATE_CHANGED";
    private static final int ADDRESS_LENGTH = 17;
    public static final String BLUETOOTH_MANAGER_SERVICE = "bluetooth_manager";
    private static final boolean DBG = true;
    public static final String DEFAULT_MAC_ADDRESS = "02:00:00:00:00:00";
    public static final int ERROR = 0x80000000;
    public static final String EXTRA_BLUETOOTH_ADDRESS = "android.bluetooth.adapter.extra.BLUETOOTH_ADDRESS";
    public static final String EXTRA_CONNECTION_STATE = "android.bluetooth.adapter.extra.CONNECTION_STATE";
    public static final String EXTRA_DISCOVERABLE_DURATION = "android.bluetooth.adapter.extra.DISCOVERABLE_DURATION";
    public static final String EXTRA_LOCAL_NAME = "android.bluetooth.adapter.extra.LOCAL_NAME";
    public static final String EXTRA_PREVIOUS_CONNECTION_STATE = "android.bluetooth.adapter.extra.PREVIOUS_CONNECTION_STATE";
    public static final String EXTRA_PREVIOUS_SCAN_MODE = "android.bluetooth.adapter.extra.PREVIOUS_SCAN_MODE";
    public static final String EXTRA_PREVIOUS_STATE = "android.bluetooth.adapter.extra.PREVIOUS_STATE";
    public static final String EXTRA_SCAN_MODE = "android.bluetooth.adapter.extra.SCAN_MODE";
    public static final String EXTRA_STATE = "android.bluetooth.adapter.extra.STATE";
    public static final int SCAN_MODE_CONNECTABLE = 21;
    public static final int SCAN_MODE_CONNECTABLE_DISCOVERABLE = 23;
    public static final int SCAN_MODE_NONE = 20;
    public static final int SOCKET_CHANNEL_AUTO_STATIC_NO_SDP = -2;
    public static final int STATE_BLE_ON = 15;
    public static final int STATE_BLE_TURNING_OFF = 16;
    public static final int STATE_BLE_TURNING_ON = 14;
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_DISCONNECTING = 3;
    public static final int STATE_OFF = 10;
    public static final int STATE_ON = 12;
    public static final int STATE_TURNING_OFF = 13;
    public static final int STATE_TURNING_ON = 11;
    private static final String TAG = "BluetoothAdapter";
    private static final boolean VDBG = false;
    private static BluetoothAdapter sAdapter;
    private static BluetoothLeAdvertiser sBluetoothLeAdvertiser;
    private static BluetoothLeScanner sBluetoothLeScanner;
    private static PeriodicAdvertisingManager sPeriodicAdvertisingManager;
    private final Map mLeScanClients;
    private final Object mLock;
    private final IBluetoothManagerCallback mManagerCallback;
    private final IBluetoothManager mManagerService;
    private final ArrayList mProxyServiceStateCallbacks;
    private IBluetooth mService;
    private final ReentrantReadWriteLock mServiceLock;
    private final IBinder mToken;

    // Unreferenced inner class android/bluetooth/BluetoothAdapter$2

/* anonymous class */
    class _cls2 extends ScanCallback
    {

        public void onScanResult(int i, ScanResult scanresult)
        {
            if(i != 1)
            {
                Log.e("BluetoothAdapter", "LE Scan has already started");
                return;
            }
            ScanRecord scanrecord = scanresult.getScanRecord();
            if(scanrecord == null)
                return;
            if(serviceUuids != null)
            {
                ArrayList arraylist = new ArrayList();
                UUID auuid[] = serviceUuids;
                i = 0;
                for(int j = auuid.length; i < j; i++)
                    arraylist.add(new ParcelUuid(auuid[i]));

                List list = scanrecord.getServiceUuids();
                if(list == null || list.containsAll(arraylist) ^ true)
                {
                    Log.d("BluetoothAdapter", "uuids does not match");
                    return;
                }
            }
            callback.onLeScan(scanresult.getDevice(), scanresult.getRssi(), scanrecord.getBytes());
        }

        final BluetoothAdapter this$0;
        final LeScanCallback val$callback;
        final UUID val$serviceUuids[];

            
            {
                this$0 = BluetoothAdapter.this;
                serviceUuids = auuid;
                callback = lescancallback;
                super();
            }
    }

}

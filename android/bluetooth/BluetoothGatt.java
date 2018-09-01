// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.*;
import android.util.Log;
import java.util.*;

// Referenced classes of package android.bluetooth:
//            BluetoothProfile, IBluetoothGatt, BluetoothDevice, BluetoothGattService, 
//            BluetoothGattCharacteristic, BluetoothGattDescriptor, IBluetoothGattCallback, BluetoothGattCallback

public final class BluetoothGatt
    implements BluetoothProfile
{

    static int _2D_get0(BluetoothGatt bluetoothgatt)
    {
        return bluetoothgatt.mAuthRetryState;
    }

    static boolean _2D_get1(BluetoothGatt bluetoothgatt)
    {
        return bluetoothgatt.mAutoConnect;
    }

    static Object _2D_get10(BluetoothGatt bluetoothgatt)
    {
        return bluetoothgatt.mStateLock;
    }

    static int _2D_get11(BluetoothGatt bluetoothgatt)
    {
        return bluetoothgatt.mTransport;
    }

    static BluetoothGattCallback _2D_get2(BluetoothGatt bluetoothgatt)
    {
        return bluetoothgatt.mCallback;
    }

    static int _2D_get3(BluetoothGatt bluetoothgatt)
    {
        return bluetoothgatt.mClientIf;
    }

    static BluetoothDevice _2D_get4(BluetoothGatt bluetoothgatt)
    {
        return bluetoothgatt.mDevice;
    }

    static Boolean _2D_get5(BluetoothGatt bluetoothgatt)
    {
        return bluetoothgatt.mDeviceBusy;
    }

    static boolean _2D_get6(BluetoothGatt bluetoothgatt)
    {
        return bluetoothgatt.mOpportunistic;
    }

    static int _2D_get7(BluetoothGatt bluetoothgatt)
    {
        return bluetoothgatt.mPhy;
    }

    static IBluetoothGatt _2D_get8(BluetoothGatt bluetoothgatt)
    {
        return bluetoothgatt.mService;
    }

    static List _2D_get9(BluetoothGatt bluetoothgatt)
    {
        return bluetoothgatt.mServices;
    }

    static int _2D_set0(BluetoothGatt bluetoothgatt, int i)
    {
        bluetoothgatt.mAuthRetryState = i;
        return i;
    }

    static int _2D_set1(BluetoothGatt bluetoothgatt, int i)
    {
        bluetoothgatt.mClientIf = i;
        return i;
    }

    static int _2D_set2(BluetoothGatt bluetoothgatt, int i)
    {
        bluetoothgatt.mConnState = i;
        return i;
    }

    static Boolean _2D_set3(BluetoothGatt bluetoothgatt, Boolean boolean1)
    {
        bluetoothgatt.mDeviceBusy = boolean1;
        return boolean1;
    }

    static void _2D_wrap0(BluetoothGatt bluetoothgatt, Runnable runnable)
    {
        bluetoothgatt.runOrQueueCallback(runnable);
    }

    BluetoothGatt(IBluetoothGatt ibluetoothgatt, BluetoothDevice bluetoothdevice, int i, boolean flag, int j)
    {
        mDeviceBusy = Boolean.valueOf(false);
        mService = ibluetoothgatt;
        mDevice = bluetoothdevice;
        mTransport = i;
        mPhy = j;
        mOpportunistic = flag;
        mServices = new ArrayList();
        mConnState = 0;
        mAuthRetryState = 0;
    }

    private boolean registerApp(BluetoothGattCallback bluetoothgattcallback, Handler handler)
    {
        Log.d("BluetoothGatt", "registerApp()");
        if(mService == null)
            return false;
        mCallback = bluetoothgattcallback;
        mHandler = handler;
        handler = UUID.randomUUID();
        Log.d("BluetoothGatt", (new StringBuilder()).append("registerApp() - UUID=").append(handler).toString());
        try
        {
            bluetoothgattcallback = mService;
            ParcelUuid parceluuid = JVM INSTR new #220 <Class ParcelUuid>;
            parceluuid.ParcelUuid(handler);
            bluetoothgattcallback.registerClient(parceluuid, mBluetoothGattCallback);
        }
        // Misplaced declaration of an exception variable
        catch(BluetoothGattCallback bluetoothgattcallback)
        {
            Log.e("BluetoothGatt", "", bluetoothgattcallback);
            return false;
        }
        return true;
    }

    private void runOrQueueCallback(Runnable runnable)
    {
        if(mHandler != null)
            break MISSING_BLOCK_LABEL_27;
        runnable.run();
_L1:
        return;
        runnable;
        Log.w("BluetoothGatt", "Unhandled exception in callback", runnable);
          goto _L1
        mHandler.post(runnable);
          goto _L1
    }

    private void unregisterApp()
    {
        Log.d("BluetoothGatt", (new StringBuilder()).append("unregisterApp() - mClientIf=").append(mClientIf).toString());
        if(mService == null || mClientIf == 0)
            return;
        mCallback = null;
        mService.unregisterClient(mClientIf);
        mClientIf = 0;
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothGatt", "", remoteexception);
          goto _L1
    }

    public void abortReliableWrite()
    {
        if(mService == null || mClientIf == 0)
            return;
        mService.endReliableWrite(mClientIf, mDevice.getAddress(), false);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothGatt", "", remoteexception);
          goto _L1
    }

    public void abortReliableWrite(BluetoothDevice bluetoothdevice)
    {
        abortReliableWrite();
    }

    public boolean beginReliableWrite()
    {
        if(mService == null || mClientIf == 0)
            return false;
        try
        {
            mService.beginReliableWrite(mClientIf, mDevice.getAddress());
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothGatt", "", remoteexception);
            return false;
        }
        return true;
    }

    public void close()
    {
        Log.d("BluetoothGatt", "close()");
        unregisterApp();
        mConnState = 4;
        mAuthRetryState = 0;
    }

    public boolean connect()
    {
        try
        {
            mService.clientConnect(mClientIf, mDevice.getAddress(), false, mTransport, mOpportunistic, mPhy);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothGatt", "", remoteexception);
            return false;
        }
        return true;
    }

    boolean connect(Boolean boolean1, BluetoothGattCallback bluetoothgattcallback, Handler handler)
    {
label0:
        {
            Log.d("BluetoothGatt", (new StringBuilder()).append("connect() - device: ").append(mDevice.getAddress()).append(", auto: ").append(boolean1).toString());
            synchronized(mStateLock)
            {
                if(mConnState != 0)
                {
                    boolean1 = JVM INSTR new #300 <Class IllegalStateException>;
                    boolean1.IllegalStateException("Not idle");
                    throw boolean1;
                }
                break label0;
            }
        }
        mConnState = 1;
        obj;
        JVM INSTR monitorexit ;
        mAutoConnect = boolean1.booleanValue();
        if(registerApp(bluetoothgattcallback, handler))
            break MISSING_BLOCK_LABEL_132;
        boolean1 = ((Boolean) (mStateLock));
        boolean1;
        JVM INSTR monitorenter ;
        mConnState = 0;
        boolean1;
        JVM INSTR monitorexit ;
        Log.e("BluetoothGatt", "Failed to register callback");
        return false;
        bluetoothgattcallback;
        throw bluetoothgattcallback;
        return true;
    }

    public void disconnect()
    {
        Log.d("BluetoothGatt", (new StringBuilder()).append("cancelOpen() - device: ").append(mDevice.getAddress()).toString());
        if(mService == null || mClientIf == 0)
            return;
        mService.clientDisconnect(mClientIf, mDevice.getAddress());
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothGatt", "", remoteexception);
          goto _L1
    }

    public boolean discoverServiceByUuid(UUID uuid)
    {
        Log.d("BluetoothGatt", (new StringBuilder()).append("discoverServiceByUuid() - device: ").append(mDevice.getAddress()).toString());
        if(mService == null || mClientIf == 0)
            return false;
        mServices.clear();
        try
        {
            IBluetoothGatt ibluetoothgatt = mService;
            int i = mClientIf;
            String s = mDevice.getAddress();
            ParcelUuid parceluuid = JVM INSTR new #220 <Class ParcelUuid>;
            parceluuid.ParcelUuid(uuid);
            ibluetoothgatt.discoverServiceByUuid(i, s, parceluuid);
        }
        // Misplaced declaration of an exception variable
        catch(UUID uuid)
        {
            Log.e("BluetoothGatt", "", uuid);
            return false;
        }
        return true;
    }

    public boolean discoverServices()
    {
        Log.d("BluetoothGatt", (new StringBuilder()).append("discoverServices() - device: ").append(mDevice.getAddress()).toString());
        if(mService == null || mClientIf == 0)
            return false;
        mServices.clear();
        try
        {
            mService.discoverServices(mClientIf, mDevice.getAddress());
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothGatt", "", remoteexception);
            return false;
        }
        return true;
    }

    public boolean executeReliableWrite()
    {
        if(mService == null || mClientIf == 0)
            return false;
        Boolean boolean1 = mDeviceBusy;
        boolean1;
        JVM INSTR monitorenter ;
        boolean flag = mDeviceBusy.booleanValue();
        if(!flag)
            break MISSING_BLOCK_LABEL_39;
        boolean1;
        JVM INSTR monitorexit ;
        return false;
        mDeviceBusy = Boolean.valueOf(true);
        boolean1;
        JVM INSTR monitorexit ;
        Exception exception;
        try
        {
            mService.endReliableWrite(mClientIf, mDevice.getAddress(), true);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothGatt", "", remoteexception);
            mDeviceBusy = Boolean.valueOf(false);
            return false;
        }
        return true;
        exception;
        throw exception;
    }

    BluetoothGattCharacteristic getCharacteristicById(BluetoothDevice bluetoothdevice, int i)
    {
        Iterator iterator = mServices.iterator();
        BluetoothGattCharacteristic bluetoothgattcharacteristic;
label0:
        do
            if(iterator.hasNext())
            {
                bluetoothdevice = ((BluetoothGattService)iterator.next()).getCharacteristics().iterator();
                do
                {
                    if(!bluetoothdevice.hasNext())
                        continue label0;
                    bluetoothgattcharacteristic = (BluetoothGattCharacteristic)bluetoothdevice.next();
                } while(bluetoothgattcharacteristic.getInstanceId() != i);
                break;
            } else
            {
                return null;
            }
        while(true);
        return bluetoothgattcharacteristic;
    }

    public List getConnectedDevices()
    {
        throw new UnsupportedOperationException("Use BluetoothManager#getConnectedDevices instead.");
    }

    public int getConnectionState(BluetoothDevice bluetoothdevice)
    {
        throw new UnsupportedOperationException("Use BluetoothManager#getConnectionState instead.");
    }

    BluetoothGattDescriptor getDescriptorById(BluetoothDevice bluetoothdevice, int i)
    {
        bluetoothdevice = mServices.iterator();
_L2:
        Iterator iterator;
        if(!bluetoothdevice.hasNext())
            break MISSING_BLOCK_LABEL_99;
        iterator = ((BluetoothGattService)bluetoothdevice.next()).getCharacteristics().iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        BluetoothGattDescriptor bluetoothgattdescriptor;
        Iterator iterator1 = ((BluetoothGattCharacteristic)iterator.next()).getDescriptors().iterator();
        do
        {
            if(!iterator1.hasNext())
                continue; /* Loop/switch isn't completed */
            bluetoothgattdescriptor = (BluetoothGattDescriptor)iterator1.next();
        } while(bluetoothgattdescriptor.getInstanceId() != i);
        break; /* Loop/switch isn't completed */
        if(true) goto _L4; else goto _L3
_L3:
        return bluetoothgattdescriptor;
        return null;
    }

    public BluetoothDevice getDevice()
    {
        return mDevice;
    }

    public List getDevicesMatchingConnectionStates(int ai[])
    {
        throw new UnsupportedOperationException("Use BluetoothManager#getDevicesMatchingConnectionStates instead.");
    }

    BluetoothGattService getService(BluetoothDevice bluetoothdevice, UUID uuid, int i, int j)
    {
        for(Iterator iterator = mServices.iterator(); iterator.hasNext();)
        {
            BluetoothGattService bluetoothgattservice = (BluetoothGattService)iterator.next();
            if(bluetoothgattservice.getDevice().equals(bluetoothdevice) && bluetoothgattservice.getType() == j && bluetoothgattservice.getInstanceId() == i && bluetoothgattservice.getUuid().equals(uuid))
                return bluetoothgattservice;
        }

        return null;
    }

    public BluetoothGattService getService(UUID uuid)
    {
        for(Iterator iterator = mServices.iterator(); iterator.hasNext();)
        {
            BluetoothGattService bluetoothgattservice = (BluetoothGattService)iterator.next();
            if(bluetoothgattservice.getDevice().equals(mDevice) && bluetoothgattservice.getUuid().equals(uuid))
                return bluetoothgattservice;
        }

        return null;
    }

    public List getServices()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = mServices.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            BluetoothGattService bluetoothgattservice = (BluetoothGattService)iterator.next();
            if(bluetoothgattservice.getDevice().equals(mDevice))
                arraylist.add(bluetoothgattservice);
        } while(true);
        return arraylist;
    }

    public boolean readCharacteristic(BluetoothGattCharacteristic bluetoothgattcharacteristic)
    {
        BluetoothDevice bluetoothdevice;
        if((bluetoothgattcharacteristic.getProperties() & 2) == 0)
            return false;
        if(mService == null || mClientIf == 0)
            return false;
        BluetoothGattService bluetoothgattservice = bluetoothgattcharacteristic.getService();
        if(bluetoothgattservice == null)
            return false;
        bluetoothdevice = bluetoothgattservice.getDevice();
        if(bluetoothdevice == null)
            return false;
        Boolean boolean1 = mDeviceBusy;
        boolean1;
        JVM INSTR monitorenter ;
        boolean flag = mDeviceBusy.booleanValue();
        if(!flag)
            break MISSING_BLOCK_LABEL_74;
        boolean1;
        JVM INSTR monitorexit ;
        return false;
        mDeviceBusy = Boolean.valueOf(true);
        boolean1;
        JVM INSTR monitorexit ;
        try
        {
            mService.readCharacteristic(mClientIf, bluetoothdevice.getAddress(), bluetoothgattcharacteristic.getInstanceId(), 0);
        }
        // Misplaced declaration of an exception variable
        catch(BluetoothGattCharacteristic bluetoothgattcharacteristic)
        {
            Log.e("BluetoothGatt", "", bluetoothgattcharacteristic);
            mDeviceBusy = Boolean.valueOf(false);
            return false;
        }
        return true;
        bluetoothgattcharacteristic;
        throw bluetoothgattcharacteristic;
    }

    public boolean readDescriptor(BluetoothGattDescriptor bluetoothgattdescriptor)
    {
        BluetoothDevice bluetoothdevice;
        if(mService == null || mClientIf == 0)
            return false;
        Object obj = bluetoothgattdescriptor.getCharacteristic();
        if(obj == null)
            return false;
        obj = ((BluetoothGattCharacteristic) (obj)).getService();
        if(obj == null)
            return false;
        bluetoothdevice = ((BluetoothGattService) (obj)).getDevice();
        if(bluetoothdevice == null)
            return false;
        Boolean boolean1 = mDeviceBusy;
        boolean1;
        JVM INSTR monitorenter ;
        boolean flag = mDeviceBusy.booleanValue();
        if(!flag)
            break MISSING_BLOCK_LABEL_74;
        boolean1;
        JVM INSTR monitorexit ;
        return false;
        mDeviceBusy = Boolean.valueOf(true);
        boolean1;
        JVM INSTR monitorexit ;
        try
        {
            mService.readDescriptor(mClientIf, bluetoothdevice.getAddress(), bluetoothgattdescriptor.getInstanceId(), 0);
        }
        // Misplaced declaration of an exception variable
        catch(BluetoothGattDescriptor bluetoothgattdescriptor)
        {
            Log.e("BluetoothGatt", "", bluetoothgattdescriptor);
            mDeviceBusy = Boolean.valueOf(false);
            return false;
        }
        return true;
        bluetoothgattdescriptor;
        throw bluetoothgattdescriptor;
    }

    public void readPhy()
    {
        mService.clientReadPhy(mClientIf, mDevice.getAddress());
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothGatt", "", remoteexception);
          goto _L1
    }

    public boolean readRemoteRssi()
    {
        Log.d("BluetoothGatt", (new StringBuilder()).append("readRssi() - device: ").append(mDevice.getAddress()).toString());
        if(mService == null || mClientIf == 0)
            return false;
        try
        {
            mService.readRemoteRssi(mClientIf, mDevice.getAddress());
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothGatt", "", remoteexception);
            return false;
        }
        return true;
    }

    public boolean readUsingCharacteristicUuid(UUID uuid, int i, int j)
    {
        if(mService == null || mClientIf == 0)
            return false;
        Object obj = mDeviceBusy;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mDeviceBusy.booleanValue();
        if(!flag)
            break MISSING_BLOCK_LABEL_44;
        obj;
        JVM INSTR monitorexit ;
        return false;
        mDeviceBusy = Boolean.valueOf(true);
        obj;
        JVM INSTR monitorexit ;
        try
        {
            IBluetoothGatt ibluetoothgatt = mService;
            int k = mClientIf;
            obj = mDevice.getAddress();
            ParcelUuid parceluuid = JVM INSTR new #220 <Class ParcelUuid>;
            parceluuid.ParcelUuid(uuid);
            ibluetoothgatt.readUsingCharacteristicUuid(k, ((String) (obj)), parceluuid, i, j, 0);
        }
        // Misplaced declaration of an exception variable
        catch(UUID uuid)
        {
            Log.e("BluetoothGatt", "", uuid);
            mDeviceBusy = Boolean.valueOf(false);
            return false;
        }
        return true;
        uuid;
        throw uuid;
    }

    public boolean refresh()
    {
        Log.d("BluetoothGatt", (new StringBuilder()).append("refresh() - device: ").append(mDevice.getAddress()).toString());
        if(mService == null || mClientIf == 0)
            return false;
        try
        {
            mService.refreshDevice(mClientIf, mDevice.getAddress());
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothGatt", "", remoteexception);
            return false;
        }
        return true;
    }

    public boolean requestConnectionPriority(int i)
    {
        if(i < 0 || i > 2)
            throw new IllegalArgumentException("connectionPriority not within valid range");
        Log.d("BluetoothGatt", (new StringBuilder()).append("requestConnectionPriority() - params: ").append(i).toString());
        if(mService == null || mClientIf == 0)
            return false;
        try
        {
            mService.connectionParameterUpdate(mClientIf, mDevice.getAddress(), i);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothGatt", "", remoteexception);
            return false;
        }
        return true;
    }

    public boolean requestMtu(int i)
    {
        Log.d("BluetoothGatt", (new StringBuilder()).append("configureMTU() - device: ").append(mDevice.getAddress()).append(" mtu: ").append(i).toString());
        if(mService == null || mClientIf == 0)
            return false;
        try
        {
            mService.configureMTU(mClientIf, mDevice.getAddress(), i);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothGatt", "", remoteexception);
            return false;
        }
        return true;
    }

    public boolean setCharacteristicNotification(BluetoothGattCharacteristic bluetoothgattcharacteristic, boolean flag)
    {
        Log.d("BluetoothGatt", (new StringBuilder()).append("setCharacteristicNotification() - uuid: ").append(bluetoothgattcharacteristic.getUuid()).append(" enable: ").append(flag).toString());
        if(mService == null || mClientIf == 0)
            return false;
        Object obj = bluetoothgattcharacteristic.getService();
        if(obj == null)
            return false;
        obj = ((BluetoothGattService) (obj)).getDevice();
        if(obj == null)
            return false;
        try
        {
            mService.registerForNotification(mClientIf, ((BluetoothDevice) (obj)).getAddress(), bluetoothgattcharacteristic.getInstanceId(), flag);
        }
        // Misplaced declaration of an exception variable
        catch(BluetoothGattCharacteristic bluetoothgattcharacteristic)
        {
            Log.e("BluetoothGatt", "", bluetoothgattcharacteristic);
            return false;
        }
        return true;
    }

    public void setPreferredPhy(int i, int j, int k)
    {
        mService.clientSetPreferredPhy(mClientIf, mDevice.getAddress(), i, j, k);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothGatt", "", remoteexception);
          goto _L1
    }

    public boolean writeCharacteristic(BluetoothGattCharacteristic bluetoothgattcharacteristic)
    {
        BluetoothDevice bluetoothdevice;
        if((bluetoothgattcharacteristic.getProperties() & 8) == 0 && (bluetoothgattcharacteristic.getProperties() & 4) == 0)
            return false;
        while(mService == null || mClientIf == 0 || bluetoothgattcharacteristic.getValue() == null) 
            return false;
        BluetoothGattService bluetoothgattservice = bluetoothgattcharacteristic.getService();
        if(bluetoothgattservice == null)
            return false;
        bluetoothdevice = bluetoothgattservice.getDevice();
        if(bluetoothdevice == null)
            return false;
        Boolean boolean1 = mDeviceBusy;
        boolean1;
        JVM INSTR monitorenter ;
        boolean flag = mDeviceBusy.booleanValue();
        if(!flag)
            break MISSING_BLOCK_LABEL_91;
        boolean1;
        JVM INSTR monitorexit ;
        return false;
        mDeviceBusy = Boolean.valueOf(true);
        boolean1;
        JVM INSTR monitorexit ;
        try
        {
            mService.writeCharacteristic(mClientIf, bluetoothdevice.getAddress(), bluetoothgattcharacteristic.getInstanceId(), bluetoothgattcharacteristic.getWriteType(), 0, bluetoothgattcharacteristic.getValue());
        }
        // Misplaced declaration of an exception variable
        catch(BluetoothGattCharacteristic bluetoothgattcharacteristic)
        {
            Log.e("BluetoothGatt", "", bluetoothgattcharacteristic);
            mDeviceBusy = Boolean.valueOf(false);
            return false;
        }
        return true;
        bluetoothgattcharacteristic;
        throw bluetoothgattcharacteristic;
    }

    public boolean writeDescriptor(BluetoothGattDescriptor bluetoothgattdescriptor)
    {
        BluetoothDevice bluetoothdevice;
        while(mService == null || mClientIf == 0 || bluetoothgattdescriptor.getValue() == null) 
            return false;
        Object obj = bluetoothgattdescriptor.getCharacteristic();
        if(obj == null)
            return false;
        obj = ((BluetoothGattCharacteristic) (obj)).getService();
        if(obj == null)
            return false;
        bluetoothdevice = ((BluetoothGattService) (obj)).getDevice();
        if(bluetoothdevice == null)
            return false;
        Boolean boolean1 = mDeviceBusy;
        boolean1;
        JVM INSTR monitorenter ;
        boolean flag = mDeviceBusy.booleanValue();
        if(!flag)
            break MISSING_BLOCK_LABEL_81;
        boolean1;
        JVM INSTR monitorexit ;
        return false;
        mDeviceBusy = Boolean.valueOf(true);
        boolean1;
        JVM INSTR monitorexit ;
        try
        {
            mService.writeDescriptor(mClientIf, bluetoothdevice.getAddress(), bluetoothgattdescriptor.getInstanceId(), 0, bluetoothgattdescriptor.getValue());
        }
        // Misplaced declaration of an exception variable
        catch(BluetoothGattDescriptor bluetoothgattdescriptor)
        {
            Log.e("BluetoothGatt", "", bluetoothgattdescriptor);
            mDeviceBusy = Boolean.valueOf(false);
            return false;
        }
        return true;
        bluetoothgattdescriptor;
        throw bluetoothgattdescriptor;
    }

    static final int AUTHENTICATION_MITM = 2;
    static final int AUTHENTICATION_NONE = 0;
    static final int AUTHENTICATION_NO_MITM = 1;
    private static final int AUTH_RETRY_STATE_IDLE = 0;
    private static final int AUTH_RETRY_STATE_MITM = 2;
    private static final int AUTH_RETRY_STATE_NO_MITM = 1;
    public static final int CONNECTION_PRIORITY_BALANCED = 0;
    public static final int CONNECTION_PRIORITY_HIGH = 1;
    public static final int CONNECTION_PRIORITY_LOW_POWER = 2;
    private static final int CONN_STATE_CLOSED = 4;
    private static final int CONN_STATE_CONNECTED = 2;
    private static final int CONN_STATE_CONNECTING = 1;
    private static final int CONN_STATE_DISCONNECTING = 3;
    private static final int CONN_STATE_IDLE = 0;
    private static final boolean DBG = true;
    public static final int GATT_CONNECTION_CONGESTED = 143;
    public static final int GATT_FAILURE = 257;
    public static final int GATT_INSUFFICIENT_AUTHENTICATION = 5;
    public static final int GATT_INSUFFICIENT_ENCRYPTION = 15;
    public static final int GATT_INVALID_ATTRIBUTE_LENGTH = 13;
    public static final int GATT_INVALID_OFFSET = 7;
    public static final int GATT_READ_NOT_PERMITTED = 2;
    public static final int GATT_REQUEST_NOT_SUPPORTED = 6;
    public static final int GATT_SUCCESS = 0;
    public static final int GATT_WRITE_NOT_PERMITTED = 3;
    private static final String TAG = "BluetoothGatt";
    private static final boolean VDBG = false;
    private int mAuthRetryState;
    private boolean mAutoConnect;
    private final IBluetoothGattCallback mBluetoothGattCallback = new IBluetoothGattCallback.Stub() {

        public void onCharacteristicRead(final String characteristic, final int status, int k, byte abyte0[])
        {
            if(!characteristic.equals(BluetoothGatt._2D_get4(BluetoothGatt.this).getAddress()))
                return;
            Boolean boolean1 = BluetoothGatt._2D_get5(BluetoothGatt.this);
            boolean1;
            JVM INSTR monitorenter ;
            BluetoothGatt._2D_set3(BluetoothGatt.this, Boolean.valueOf(false));
            boolean1;
            JVM INSTR monitorexit ;
            if(status != 5 && status != 15 || BluetoothGatt._2D_get0(BluetoothGatt.this) == 2)
                break MISSING_BLOCK_LABEL_142;
            int l;
            if(BluetoothGatt._2D_get0(BluetoothGatt.this) == 0)
                l = 1;
            else
                l = 2;
            try
            {
                BluetoothGatt._2D_get8(BluetoothGatt.this).readCharacteristic(BluetoothGatt._2D_get3(BluetoothGatt.this), characteristic, k, l);
                characteristic = BluetoothGatt.this;
                BluetoothGatt._2D_set0(characteristic, BluetoothGatt._2D_get0(characteristic) + 1);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(final String characteristic)
            {
                Log.e("BluetoothGatt", "", characteristic);
            }
            break MISSING_BLOCK_LABEL_142;
            characteristic;
            throw characteristic;
            BluetoothGatt._2D_set0(BluetoothGatt.this, 0);
            characteristic = getCharacteristicById(BluetoothGatt._2D_get4(BluetoothGatt.this), k);
            if(characteristic == null)
            {
                Log.w("BluetoothGatt", "onCharacteristicRead() failed to find characteristic!");
                return;
            } else
            {
                BluetoothGatt._2D_wrap0(BluetoothGatt.this, abyte0. new Runnable() {

                    public void run()
                    {
                        BluetoothGattCallback bluetoothgattcallback = BluetoothGatt._2D_get2(_fld0);
                        if(bluetoothgattcallback != null)
                        {
                            if(status == 0)
                                characteristic.setValue(value);
                            bluetoothgattcallback.onCharacteristicRead(_fld0, characteristic, status);
                        }
                    }

                    final _cls1 this$1;
                    final BluetoothGattCharacteristic val$characteristic;
                    final int val$status;
                    final byte val$value[];

            
            {
                this$1 = final__pcls1;
                status = i;
                characteristic = bluetoothgattcharacteristic;
                value = _5B_B.this;
                super();
            }
                }
);
                return;
            }
        }

        public void onCharacteristicWrite(String s, int k, int l)
        {
            if(!s.equals(BluetoothGatt._2D_get4(BluetoothGatt.this).getAddress()))
                return;
            final Object characteristic = BluetoothGatt._2D_get5(BluetoothGatt.this);
            characteristic;
            JVM INSTR monitorenter ;
            BluetoothGatt._2D_set3(BluetoothGatt.this, Boolean.valueOf(false));
            characteristic;
            JVM INSTR monitorexit ;
            characteristic = getCharacteristicById(BluetoothGatt._2D_get4(BluetoothGatt.this), l);
            if(characteristic == null)
                return;
            break MISSING_BLOCK_LABEL_74;
            s;
            throw s;
            if(k != 5 && k != 15 || BluetoothGatt._2D_get0(BluetoothGatt.this) == 2)
                break MISSING_BLOCK_LABEL_175;
            int i1;
            if(BluetoothGatt._2D_get0(BluetoothGatt.this) == 0)
                i1 = 1;
            else
                i1 = 2;
            try
            {
                BluetoothGatt._2D_get8(BluetoothGatt.this).writeCharacteristic(BluetoothGatt._2D_get3(BluetoothGatt.this), s, l, ((BluetoothGattCharacteristic) (characteristic)).getWriteType(), i1, ((BluetoothGattCharacteristic) (characteristic)).getValue());
                s = BluetoothGatt.this;
                BluetoothGatt._2D_set0(s, BluetoothGatt._2D_get0(s) + 1);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Log.e("BluetoothGatt", "", s);
            }
            BluetoothGatt._2D_set0(BluetoothGatt.this, 0);
            BluetoothGatt._2D_wrap0(BluetoothGatt.this, k. new Runnable() {

                public void run()
                {
                    BluetoothGattCallback bluetoothgattcallback = BluetoothGatt._2D_get2(_fld0);
                    if(bluetoothgattcallback != null)
                        bluetoothgattcallback.onCharacteristicWrite(_fld0, characteristic, status);
                }

                final _cls1 this$1;
                final BluetoothGattCharacteristic val$characteristic;
                final int val$status;

            
            {
                this$1 = final__pcls1;
                characteristic = bluetoothgattcharacteristic;
                status = I.this;
                super();
            }
            }
);
            return;
        }

        public void onClientConnectionState(final int status, int k, boolean flag1, String s)
        {
            Log.d("BluetoothGatt", (new StringBuilder()).append("onClientConnectionState() - status=").append(status).append(" clientIf=").append(k).append(" device=").append(s).toString());
            if(!s.equals(BluetoothGatt._2D_get4(BluetoothGatt.this).getAddress()))
                return;
            if(flag1)
                k = 2;
            else
                k = 0;
            BluetoothGatt._2D_wrap0(BluetoothGatt.this, k. new Runnable() {

                public void run()
                {
                    BluetoothGattCallback bluetoothgattcallback = BluetoothGatt._2D_get2(_fld0);
                    if(bluetoothgattcallback != null)
                        bluetoothgattcallback.onConnectionStateChange(_fld0, status, profileState);
                }

                final _cls1 this$1;
                final int val$profileState;
                final int val$status;

            
            {
                this$1 = final__pcls1;
                status = i;
                profileState = I.this;
                super();
            }
            }
);
            s = ((String) (BluetoothGatt._2D_get10(BluetoothGatt.this)));
            s;
            JVM INSTR monitorenter ;
            if(!flag1) goto _L2; else goto _L1
_L1:
            BluetoothGatt._2D_set2(BluetoothGatt.this, 2);
_L4:
            s;
            JVM INSTR monitorexit ;
            s = BluetoothGatt._2D_get5(BluetoothGatt.this);
            s;
            JVM INSTR monitorenter ;
            BluetoothGatt._2D_set3(BluetoothGatt.this, Boolean.valueOf(false));
            s;
            JVM INSTR monitorexit ;
            return;
_L2:
            BluetoothGatt._2D_set2(BluetoothGatt.this, 0);
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            throw exception;
            exception;
            throw exception;
        }

        public void onClientRegistered(int k, int l)
        {
            Log.d("BluetoothGatt", (new StringBuilder()).append("onClientRegistered() - status=").append(k).append(" clientIf=").append(l).toString());
            BluetoothGatt._2D_set1(BluetoothGatt.this, l);
            if(k == 0)
                break MISSING_BLOCK_LABEL_91;
            BluetoothGatt._2D_wrap0(BluetoothGatt.this, new Runnable() {

                public void run()
                {
                    BluetoothGattCallback bluetoothgattcallback = BluetoothGatt._2D_get2(_fld0);
                    if(bluetoothgattcallback != null)
                        bluetoothgattcallback.onConnectionStateChange(_fld0, 257, 0);
                }

                final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
            }
);
            Object obj = BluetoothGatt._2D_get10(BluetoothGatt.this);
            obj;
            JVM INSTR monitorenter ;
            BluetoothGatt._2D_set2(BluetoothGatt.this, 0);
            obj;
            JVM INSTR monitorexit ;
            return;
            Object obj1;
            obj1;
            throw obj1;
            BluetoothGatt._2D_get8(BluetoothGatt.this).clientConnect(BluetoothGatt._2D_get3(BluetoothGatt.this), BluetoothGatt._2D_get4(BluetoothGatt.this).getAddress(), BluetoothGatt._2D_get1(BluetoothGatt.this) ^ true, BluetoothGatt._2D_get11(BluetoothGatt.this), BluetoothGatt._2D_get6(BluetoothGatt.this), BluetoothGatt._2D_get7(BluetoothGatt.this));
_L1:
            return;
            obj1;
            Log.e("BluetoothGatt", "", ((Throwable) (obj1)));
              goto _L1
        }

        public void onConfigureMTU(String s, final int mtu, int k)
        {
            Log.d("BluetoothGatt", (new StringBuilder()).append("onConfigureMTU() - Device=").append(s).append(" mtu=").append(mtu).append(" status=").append(k).toString());
            if(!s.equals(BluetoothGatt._2D_get4(BluetoothGatt.this).getAddress()))
            {
                return;
            } else
            {
                BluetoothGatt._2D_wrap0(BluetoothGatt.this, k. new Runnable() {

                    public void run()
                    {
                        BluetoothGattCallback bluetoothgattcallback = BluetoothGatt._2D_get2(_fld0);
                        if(bluetoothgattcallback != null)
                            bluetoothgattcallback.onMtuChanged(_fld0, mtu, status);
                    }

                    final _cls1 this$1;
                    final int val$mtu;
                    final int val$status;

            
            {
                this$1 = final__pcls1;
                mtu = i;
                status = I.this;
                super();
            }
                }
);
                return;
            }
        }

        public void onConnectionUpdated(String s, final int interval, final int latency, final int timeout, int k)
        {
            Log.d("BluetoothGatt", (new StringBuilder()).append("onConnectionUpdated() - Device=").append(s).append(" interval=").append(interval).append(" latency=").append(latency).append(" timeout=").append(timeout).append(" status=").append(k).toString());
            if(!s.equals(BluetoothGatt._2D_get4(BluetoothGatt.this).getAddress()))
            {
                return;
            } else
            {
                BluetoothGatt._2D_wrap0(BluetoothGatt.this, k. new Runnable() {

                    public void run()
                    {
                        BluetoothGattCallback bluetoothgattcallback = BluetoothGatt._2D_get2(_fld0);
                        if(bluetoothgattcallback != null)
                            bluetoothgattcallback.onConnectionUpdated(_fld0, interval, latency, timeout, status);
                    }

                    final _cls1 this$1;
                    final int val$interval;
                    final int val$latency;
                    final int val$status;
                    final int val$timeout;

            
            {
                this$1 = final__pcls1;
                interval = i;
                latency = j;
                timeout = k;
                status = I.this;
                super();
            }
                }
);
                return;
            }
        }

        public void onDescriptorRead(String s, final int status, int k, byte abyte0[])
        {
            if(!s.equals(BluetoothGatt._2D_get4(BluetoothGatt.this).getAddress()))
                return;
            final Object descriptor = BluetoothGatt._2D_get5(BluetoothGatt.this);
            descriptor;
            JVM INSTR monitorenter ;
            BluetoothGatt._2D_set3(BluetoothGatt.this, Boolean.valueOf(false));
            descriptor;
            JVM INSTR monitorexit ;
            descriptor = getDescriptorById(BluetoothGatt._2D_get4(BluetoothGatt.this), k);
            if(descriptor == null)
                return;
            break MISSING_BLOCK_LABEL_74;
            s;
            throw s;
            if(status != 5 && status != 15 || BluetoothGatt._2D_get0(BluetoothGatt.this) == 2)
                break MISSING_BLOCK_LABEL_165;
            int l;
            if(BluetoothGatt._2D_get0(BluetoothGatt.this) == 0)
                l = 1;
            else
                l = 2;
            try
            {
                BluetoothGatt._2D_get8(BluetoothGatt.this).readDescriptor(BluetoothGatt._2D_get3(BluetoothGatt.this), s, k, l);
                s = BluetoothGatt.this;
                BluetoothGatt._2D_set0(s, BluetoothGatt._2D_get0(s) + 1);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Log.e("BluetoothGatt", "", s);
            }
            BluetoothGatt._2D_set0(BluetoothGatt.this, 0);
            BluetoothGatt._2D_wrap0(BluetoothGatt.this, abyte0. new Runnable() {

                public void run()
                {
                    BluetoothGattCallback bluetoothgattcallback = BluetoothGatt._2D_get2(_fld0);
                    if(bluetoothgattcallback != null)
                    {
                        if(status == 0)
                            descriptor.setValue(value);
                        bluetoothgattcallback.onDescriptorRead(_fld0, descriptor, status);
                    }
                }

                final _cls1 this$1;
                final BluetoothGattDescriptor val$descriptor;
                final int val$status;
                final byte val$value[];

            
            {
                this$1 = final__pcls1;
                status = i;
                descriptor = bluetoothgattdescriptor;
                value = _5B_B.this;
                super();
            }
            }
);
            return;
        }

        public void onDescriptorWrite(String s, int k, int l)
        {
            if(!s.equals(BluetoothGatt._2D_get4(BluetoothGatt.this).getAddress()))
                return;
            final Object descriptor = BluetoothGatt._2D_get5(BluetoothGatt.this);
            descriptor;
            JVM INSTR monitorenter ;
            BluetoothGatt._2D_set3(BluetoothGatt.this, Boolean.valueOf(false));
            descriptor;
            JVM INSTR monitorexit ;
            descriptor = getDescriptorById(BluetoothGatt._2D_get4(BluetoothGatt.this), l);
            if(descriptor == null)
                return;
            break MISSING_BLOCK_LABEL_74;
            s;
            throw s;
            if(k != 5 && k != 15 || BluetoothGatt._2D_get0(BluetoothGatt.this) == 2)
                break MISSING_BLOCK_LABEL_170;
            int i1;
            if(BluetoothGatt._2D_get0(BluetoothGatt.this) == 0)
                i1 = 1;
            else
                i1 = 2;
            try
            {
                BluetoothGatt._2D_get8(BluetoothGatt.this).writeDescriptor(BluetoothGatt._2D_get3(BluetoothGatt.this), s, l, i1, ((BluetoothGattDescriptor) (descriptor)).getValue());
                s = BluetoothGatt.this;
                BluetoothGatt._2D_set0(s, BluetoothGatt._2D_get0(s) + 1);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Log.e("BluetoothGatt", "", s);
            }
            BluetoothGatt._2D_set0(BluetoothGatt.this, 0);
            BluetoothGatt._2D_wrap0(BluetoothGatt.this, k. new Runnable() {

                public void run()
                {
                    BluetoothGattCallback bluetoothgattcallback = BluetoothGatt._2D_get2(_fld0);
                    if(bluetoothgattcallback != null)
                        bluetoothgattcallback.onDescriptorWrite(_fld0, descriptor, status);
                }

                final _cls1 this$1;
                final BluetoothGattDescriptor val$descriptor;
                final int val$status;

            
            {
                this$1 = final__pcls1;
                descriptor = bluetoothgattdescriptor;
                status = I.this;
                super();
            }
            }
);
            return;
        }

        public void onExecuteWrite(String s, int k)
        {
            if(!s.equals(BluetoothGatt._2D_get4(BluetoothGatt.this).getAddress()))
                return;
            Boolean boolean1 = BluetoothGatt._2D_get5(BluetoothGatt.this);
            boolean1;
            JVM INSTR monitorenter ;
            BluetoothGatt._2D_set3(BluetoothGatt.this, Boolean.valueOf(false));
            boolean1;
            JVM INSTR monitorexit ;
            BluetoothGatt._2D_wrap0(BluetoothGatt.this, k. new Runnable() {

                public void run()
                {
                    BluetoothGattCallback bluetoothgattcallback = BluetoothGatt._2D_get2(_fld0);
                    if(bluetoothgattcallback != null)
                        bluetoothgattcallback.onReliableWriteCompleted(_fld0, status);
                }

                final _cls1 this$1;
                final int val$status;

            
            {
                this$1 = final__pcls1;
                status = I.this;
                super();
            }
            }
);
            return;
            s;
            throw s;
        }

        public void onNotify(final String characteristic, int k, byte abyte0[])
        {
            if(!characteristic.equals(BluetoothGatt._2D_get4(BluetoothGatt.this).getAddress()))
                return;
            characteristic = getCharacteristicById(BluetoothGatt._2D_get4(BluetoothGatt.this), k);
            if(characteristic == null)
            {
                return;
            } else
            {
                BluetoothGatt._2D_wrap0(BluetoothGatt.this, abyte0. new Runnable() {

                    public void run()
                    {
                        BluetoothGattCallback bluetoothgattcallback = BluetoothGatt._2D_get2(_fld0);
                        if(bluetoothgattcallback != null)
                        {
                            characteristic.setValue(value);
                            bluetoothgattcallback.onCharacteristicChanged(_fld0, characteristic);
                        }
                    }

                    final _cls1 this$1;
                    final BluetoothGattCharacteristic val$characteristic;
                    final byte val$value[];

            
            {
                this$1 = final__pcls1;
                characteristic = bluetoothgattcharacteristic;
                value = _5B_B.this;
                super();
            }
                }
);
                return;
            }
        }

        public void onPhyRead(String s, final int txPhy, final int rxPhy, int k)
        {
            Log.d("BluetoothGatt", (new StringBuilder()).append("onPhyRead() - status=").append(k).append(" address=").append(s).append(" txPhy=").append(txPhy).append(" rxPhy=").append(rxPhy).toString());
            if(!s.equals(BluetoothGatt._2D_get4(BluetoothGatt.this).getAddress()))
            {
                return;
            } else
            {
                BluetoothGatt._2D_wrap0(BluetoothGatt.this, k. new Runnable() {

                    public void run()
                    {
                        BluetoothGattCallback bluetoothgattcallback = BluetoothGatt._2D_get2(_fld0);
                        if(bluetoothgattcallback != null)
                            bluetoothgattcallback.onPhyRead(_fld0, txPhy, rxPhy, status);
                    }

                    final _cls1 this$1;
                    final int val$rxPhy;
                    final int val$status;
                    final int val$txPhy;

            
            {
                this$1 = final__pcls1;
                txPhy = i;
                rxPhy = j;
                status = I.this;
                super();
            }
                }
);
                return;
            }
        }

        public void onPhyUpdate(String s, final int txPhy, final int rxPhy, int k)
        {
            Log.d("BluetoothGatt", (new StringBuilder()).append("onPhyUpdate() - status=").append(k).append(" address=").append(s).append(" txPhy=").append(txPhy).append(" rxPhy=").append(rxPhy).toString());
            if(!s.equals(BluetoothGatt._2D_get4(BluetoothGatt.this).getAddress()))
            {
                return;
            } else
            {
                BluetoothGatt._2D_wrap0(BluetoothGatt.this, k. new Runnable() {

                    public void run()
                    {
                        BluetoothGattCallback bluetoothgattcallback = BluetoothGatt._2D_get2(_fld0);
                        if(bluetoothgattcallback != null)
                            bluetoothgattcallback.onPhyUpdate(_fld0, txPhy, rxPhy, status);
                    }

                    final _cls1 this$1;
                    final int val$rxPhy;
                    final int val$status;
                    final int val$txPhy;

            
            {
                this$1 = final__pcls1;
                txPhy = i;
                rxPhy = j;
                status = I.this;
                super();
            }
                }
);
                return;
            }
        }

        public void onReadRemoteRssi(String s, final int rssi, int k)
        {
            if(!s.equals(BluetoothGatt._2D_get4(BluetoothGatt.this).getAddress()))
            {
                return;
            } else
            {
                BluetoothGatt._2D_wrap0(BluetoothGatt.this, k. new Runnable() {

                    public void run()
                    {
                        BluetoothGattCallback bluetoothgattcallback = BluetoothGatt._2D_get2(_fld0);
                        if(bluetoothgattcallback != null)
                            bluetoothgattcallback.onReadRemoteRssi(_fld0, rssi, status);
                    }

                    final _cls1 this$1;
                    final int val$rssi;
                    final int val$status;

            
            {
                this$1 = final__pcls1;
                rssi = i;
                status = I.this;
                super();
            }
                }
);
                return;
            }
        }

        public void onSearchComplete(String s, List list, int k)
        {
            Log.d("BluetoothGatt", (new StringBuilder()).append("onSearchComplete() = Device=").append(s).append(" Status=").append(k).toString());
            if(!s.equals(BluetoothGatt._2D_get4(BluetoothGatt.this).getAddress()))
                return;
            for(s = list.iterator(); s.hasNext(); ((BluetoothGattService)s.next()).setDevice(BluetoothGatt._2D_get4(BluetoothGatt.this)));
            BluetoothGatt._2D_get9(BluetoothGatt.this).addAll(list);
            for(s = BluetoothGatt._2D_get9(BluetoothGatt.this).iterator(); s.hasNext();)
            {
                list = (BluetoothGattService)s.next();
                Object obj = new ArrayList(list.getIncludedServices());
                list.getIncludedServices().clear();
                obj = ((Iterable) (obj)).iterator();
                while(((Iterator) (obj)).hasNext()) 
                {
                    BluetoothGattService bluetoothgattservice = (BluetoothGattService)((Iterator) (obj)).next();
                    bluetoothgattservice = getService(BluetoothGatt._2D_get4(BluetoothGatt.this), bluetoothgattservice.getUuid(), bluetoothgattservice.getInstanceId(), bluetoothgattservice.getType());
                    if(bluetoothgattservice != null)
                        list.addIncludedService(bluetoothgattservice);
                    else
                        Log.e("BluetoothGatt", "Broken GATT database: can't find included service.");
                }
            }

            BluetoothGatt._2D_wrap0(BluetoothGatt.this, k. new Runnable() {

                public void run()
                {
                    BluetoothGattCallback bluetoothgattcallback = BluetoothGatt._2D_get2(_fld0);
                    if(bluetoothgattcallback != null)
                        bluetoothgattcallback.onServicesDiscovered(_fld0, status);
                }

                final _cls1 this$1;
                final int val$status;

            
            {
                this$1 = final__pcls1;
                status = I.this;
                super();
            }
            }
);
        }

        final BluetoothGatt this$0;

            
            {
                this$0 = BluetoothGatt.this;
                super();
            }
    }
;
    private volatile BluetoothGattCallback mCallback;
    private int mClientIf;
    private int mConnState;
    private BluetoothDevice mDevice;
    private Boolean mDeviceBusy;
    private Handler mHandler;
    private boolean mOpportunistic;
    private int mPhy;
    private IBluetoothGatt mService;
    private List mServices;
    private final Object mStateLock = new Object();
    private int mTransport;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.ParcelUuid;
import android.os.RemoteException;
import android.util.Log;
import java.util.*;

// Referenced classes of package android.bluetooth:
//            BluetoothProfile, BluetoothAdapter, IBluetoothGatt, BluetoothGattService, 
//            BluetoothDevice, BluetoothGattCharacteristic, BluetoothGattDescriptor, IBluetoothGattServerCallback, 
//            BluetoothGattServerCallback

public final class BluetoothGattServer
    implements BluetoothProfile
{

    static BluetoothAdapter _2D_get0(BluetoothGattServer bluetoothgattserver)
    {
        return bluetoothgattserver.mAdapter;
    }

    static BluetoothGattServerCallback _2D_get1(BluetoothGattServer bluetoothgattserver)
    {
        return bluetoothgattserver.mCallback;
    }

    static BluetoothGattService _2D_get2(BluetoothGattServer bluetoothgattserver)
    {
        return bluetoothgattserver.mPendingService;
    }

    static Object _2D_get3(BluetoothGattServer bluetoothgattserver)
    {
        return bluetoothgattserver.mServerIfLock;
    }

    static List _2D_get4(BluetoothGattServer bluetoothgattserver)
    {
        return bluetoothgattserver.mServices;
    }

    static BluetoothGattService _2D_set0(BluetoothGattServer bluetoothgattserver, BluetoothGattService bluetoothgattservice)
    {
        bluetoothgattserver.mPendingService = bluetoothgattservice;
        return bluetoothgattservice;
    }

    static int _2D_set1(BluetoothGattServer bluetoothgattserver, int i)
    {
        bluetoothgattserver.mServerIf = i;
        return i;
    }

    BluetoothGattServer(IBluetoothGatt ibluetoothgatt, int i)
    {
        mServerIfLock = new Object();
        mService = ibluetoothgatt;
        mAdapter = BluetoothAdapter.getDefaultAdapter();
        mCallback = null;
        mServerIf = 0;
        mTransport = i;
        mServices = new ArrayList();
    }

    private void unregisterCallback()
    {
        Log.d("BluetoothGattServer", (new StringBuilder()).append("unregisterCallback() - mServerIf=").append(mServerIf).toString());
        if(mService == null || mServerIf == 0)
            return;
        mCallback = null;
        mService.unregisterServer(mServerIf);
        mServerIf = 0;
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothGattServer", "", remoteexception);
          goto _L1
    }

    public boolean addService(BluetoothGattService bluetoothgattservice)
    {
        Log.d("BluetoothGattServer", (new StringBuilder()).append("addService() - service: ").append(bluetoothgattservice.getUuid()).toString());
        if(mService == null || mServerIf == 0)
            return false;
        mPendingService = bluetoothgattservice;
        try
        {
            mService.addService(mServerIf, bluetoothgattservice);
        }
        // Misplaced declaration of an exception variable
        catch(BluetoothGattService bluetoothgattservice)
        {
            Log.e("BluetoothGattServer", "", bluetoothgattservice);
            return false;
        }
        return true;
    }

    public void cancelConnection(BluetoothDevice bluetoothdevice)
    {
        Log.d("BluetoothGattServer", (new StringBuilder()).append("cancelConnection() - device: ").append(bluetoothdevice.getAddress()).toString());
        if(mService == null || mServerIf == 0)
            return;
        mService.serverDisconnect(mServerIf, bluetoothdevice.getAddress());
_L1:
        return;
        bluetoothdevice;
        Log.e("BluetoothGattServer", "", bluetoothdevice);
          goto _L1
    }

    public void clearServices()
    {
        Log.d("BluetoothGattServer", "clearServices()");
        if(mService == null || mServerIf == 0)
            return;
        mService.clearServices(mServerIf);
        mServices.clear();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothGattServer", "", remoteexception);
          goto _L1
    }

    public void close()
    {
        Log.d("BluetoothGattServer", "close()");
        unregisterCallback();
    }

    public boolean connect(BluetoothDevice bluetoothdevice, boolean flag)
    {
        Log.d("BluetoothGattServer", (new StringBuilder()).append("connect() - device: ").append(bluetoothdevice.getAddress()).append(", auto: ").append(flag).toString());
        if(mService == null || mServerIf == 0)
            return false;
        IBluetoothGatt ibluetoothgatt;
        int i;
        try
        {
            ibluetoothgatt = mService;
            i = mServerIf;
            bluetoothdevice = bluetoothdevice.getAddress();
        }
        // Misplaced declaration of an exception variable
        catch(BluetoothDevice bluetoothdevice)
        {
            Log.e("BluetoothGattServer", "", bluetoothdevice);
            return false;
        }
        if(flag)
            flag = false;
        else
            flag = true;
        ibluetoothgatt.serverConnect(i, bluetoothdevice, flag, mTransport);
        return true;
    }

    BluetoothGattCharacteristic getCharacteristicByHandle(int i)
    {
        Iterator iterator = mServices.iterator();
        BluetoothGattCharacteristic bluetoothgattcharacteristic;
label0:
        do
            if(iterator.hasNext())
            {
                Iterator iterator1 = ((BluetoothGattService)iterator.next()).getCharacteristics().iterator();
                do
                {
                    if(!iterator1.hasNext())
                        continue label0;
                    bluetoothgattcharacteristic = (BluetoothGattCharacteristic)iterator1.next();
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

    BluetoothGattDescriptor getDescriptorByHandle(int i)
    {
        Iterator iterator = mServices.iterator();
_L2:
        Iterator iterator1;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_99;
        iterator1 = ((BluetoothGattService)iterator.next()).getCharacteristics().iterator();
_L4:
        if(!iterator1.hasNext()) goto _L2; else goto _L1
_L1:
        BluetoothGattDescriptor bluetoothgattdescriptor;
        Iterator iterator2 = ((BluetoothGattCharacteristic)iterator1.next()).getDescriptors().iterator();
        do
        {
            if(!iterator2.hasNext())
                continue; /* Loop/switch isn't completed */
            bluetoothgattdescriptor = (BluetoothGattDescriptor)iterator2.next();
        } while(bluetoothgattdescriptor.getInstanceId() != i);
        break; /* Loop/switch isn't completed */
        if(true) goto _L4; else goto _L3
_L3:
        return bluetoothgattdescriptor;
        return null;
    }

    public List getDevicesMatchingConnectionStates(int ai[])
    {
        throw new UnsupportedOperationException("Use BluetoothManager#getDevicesMatchingConnectionStates instead.");
    }

    public BluetoothGattService getService(UUID uuid)
    {
        for(Iterator iterator = mServices.iterator(); iterator.hasNext();)
        {
            BluetoothGattService bluetoothgattservice = (BluetoothGattService)iterator.next();
            if(bluetoothgattservice.getUuid().equals(uuid))
                return bluetoothgattservice;
        }

        return null;
    }

    BluetoothGattService getService(UUID uuid, int i, int j)
    {
        for(Iterator iterator = mServices.iterator(); iterator.hasNext();)
        {
            BluetoothGattService bluetoothgattservice = (BluetoothGattService)iterator.next();
            if(bluetoothgattservice.getType() == j && bluetoothgattservice.getInstanceId() == i && bluetoothgattservice.getUuid().equals(uuid))
                return bluetoothgattservice;
        }

        return null;
    }

    public List getServices()
    {
        return mServices;
    }

    public boolean notifyCharacteristicChanged(BluetoothDevice bluetoothdevice, BluetoothGattCharacteristic bluetoothgattcharacteristic, boolean flag)
    {
        if(mService == null || mServerIf == 0)
            return false;
        if(bluetoothgattcharacteristic.getService() == null)
            return false;
        if(bluetoothgattcharacteristic.getValue() == null)
            throw new IllegalArgumentException("Chracteristic value is empty. Use BluetoothGattCharacteristic#setvalue to update");
        try
        {
            mService.sendNotification(mServerIf, bluetoothdevice.getAddress(), bluetoothgattcharacteristic.getInstanceId(), flag, bluetoothgattcharacteristic.getValue());
        }
        // Misplaced declaration of an exception variable
        catch(BluetoothDevice bluetoothdevice)
        {
            Log.e("BluetoothGattServer", "", bluetoothdevice);
            return false;
        }
        return true;
    }

    public void readPhy(BluetoothDevice bluetoothdevice)
    {
        mService.serverReadPhy(mServerIf, bluetoothdevice.getAddress());
_L1:
        return;
        bluetoothdevice;
        Log.e("BluetoothGattServer", "", bluetoothdevice);
          goto _L1
    }

    boolean registerCallback(BluetoothGattServerCallback bluetoothgattservercallback)
    {
        UUID uuid;
        Log.d("BluetoothGattServer", "registerCallback()");
        if(mService == null)
        {
            Log.e("BluetoothGattServer", "GATT service not available");
            return false;
        }
        uuid = UUID.randomUUID();
        Log.d("BluetoothGattServer", (new StringBuilder()).append("registerCallback() - UUID=").append(uuid).toString());
        Object obj = mServerIfLock;
        obj;
        JVM INSTR monitorenter ;
        if(mCallback == null)
            break MISSING_BLOCK_LABEL_84;
        Log.e("BluetoothGattServer", "App can register callback only once");
        obj;
        JVM INSTR monitorexit ;
        return false;
        mCallback = bluetoothgattservercallback;
        bluetoothgattservercallback = mService;
        ParcelUuid parceluuid = JVM INSTR new #292 <Class ParcelUuid>;
        parceluuid.ParcelUuid(uuid);
        bluetoothgattservercallback.registerServer(parceluuid, mBluetoothGattServerCallback);
        mServerIfLock.wait(10000L);
_L1:
        if(mServerIf != 0)
            break MISSING_BLOCK_LABEL_203;
        mCallback = null;
        return false;
        bluetoothgattservercallback;
        Log.e("BluetoothGattServer", "", bluetoothgattservercallback);
        mCallback = null;
        obj;
        JVM INSTR monitorexit ;
        return false;
        bluetoothgattservercallback;
        StringBuilder stringbuilder = JVM INSTR new #92  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e("BluetoothGattServer", stringbuilder.append("").append(bluetoothgattservercallback).toString());
        mCallback = null;
          goto _L1
        bluetoothgattservercallback;
        throw bluetoothgattservercallback;
        obj;
        JVM INSTR monitorexit ;
        return true;
    }

    public boolean removeService(BluetoothGattService bluetoothgattservice)
    {
        Log.d("BluetoothGattServer", (new StringBuilder()).append("removeService() - service: ").append(bluetoothgattservice.getUuid()).toString());
        if(mService == null || mServerIf == 0)
            return false;
        BluetoothGattService bluetoothgattservice1 = getService(bluetoothgattservice.getUuid(), bluetoothgattservice.getInstanceId(), bluetoothgattservice.getType());
        if(bluetoothgattservice1 == null)
            return false;
        try
        {
            mService.removeService(mServerIf, bluetoothgattservice.getInstanceId());
            mServices.remove(bluetoothgattservice1);
        }
        // Misplaced declaration of an exception variable
        catch(BluetoothGattService bluetoothgattservice)
        {
            Log.e("BluetoothGattServer", "", bluetoothgattservice);
            return false;
        }
        return true;
    }

    public boolean sendResponse(BluetoothDevice bluetoothdevice, int i, int j, int k, byte abyte0[])
    {
        if(mService == null || mServerIf == 0)
            return false;
        try
        {
            mService.sendResponse(mServerIf, bluetoothdevice.getAddress(), i, j, k, abyte0);
        }
        // Misplaced declaration of an exception variable
        catch(BluetoothDevice bluetoothdevice)
        {
            Log.e("BluetoothGattServer", "", bluetoothdevice);
            return false;
        }
        return true;
    }

    public void setPreferredPhy(BluetoothDevice bluetoothdevice, int i, int j, int k)
    {
        mService.serverSetPreferredPhy(mServerIf, bluetoothdevice.getAddress(), i, j, k);
_L1:
        return;
        bluetoothdevice;
        Log.e("BluetoothGattServer", "", bluetoothdevice);
          goto _L1
    }

    private static final int CALLBACK_REG_TIMEOUT = 10000;
    private static final boolean DBG = true;
    private static final String TAG = "BluetoothGattServer";
    private static final boolean VDBG = false;
    private BluetoothAdapter mAdapter;
    private final IBluetoothGattServerCallback mBluetoothGattServerCallback = new IBluetoothGattServerCallback.Stub() {

        public void onCharacteristicReadRequest(String s, int j, int k, boolean flag, int l)
        {
            BluetoothDevice bluetoothdevice;
            bluetoothdevice = BluetoothGattServer._2D_get0(BluetoothGattServer.this).getRemoteDevice(s);
            s = getCharacteristicByHandle(l);
            if(s == null)
            {
                Log.w("BluetoothGattServer", (new StringBuilder()).append("onCharacteristicReadRequest() no char for handle ").append(l).toString());
                return;
            }
            BluetoothGattServer._2D_get1(BluetoothGattServer.this).onCharacteristicReadRequest(bluetoothdevice, j, k, s);
_L1:
            return;
            s;
            Log.w("BluetoothGattServer", "Unhandled exception in callback", s);
              goto _L1
        }

        public void onCharacteristicWriteRequest(String s, int j, int k, int l, boolean flag, boolean flag1, int i1, 
                byte abyte0[])
        {
            BluetoothDevice bluetoothdevice;
            bluetoothdevice = BluetoothGattServer._2D_get0(BluetoothGattServer.this).getRemoteDevice(s);
            s = getCharacteristicByHandle(i1);
            if(s == null)
            {
                Log.w("BluetoothGattServer", (new StringBuilder()).append("onCharacteristicWriteRequest() no char for handle ").append(i1).toString());
                return;
            }
            BluetoothGattServer._2D_get1(BluetoothGattServer.this).onCharacteristicWriteRequest(bluetoothdevice, j, s, flag, flag1, k, abyte0);
_L1:
            return;
            s;
            Log.w("BluetoothGattServer", "Unhandled exception in callback", s);
              goto _L1
        }

        public void onConnectionUpdated(String s, int j, int k, int l, int i1)
        {
            Log.d("BluetoothGattServer", (new StringBuilder()).append("onConnectionUpdated() - Device=").append(s).append(" interval=").append(j).append(" latency=").append(k).append(" timeout=").append(l).append(" status=").append(i1).toString());
            s = BluetoothGattServer._2D_get0(BluetoothGattServer.this).getRemoteDevice(s);
            if(s == null)
                return;
            BluetoothGattServer._2D_get1(BluetoothGattServer.this).onConnectionUpdated(s, j, k, l, i1);
_L1:
            return;
            s;
            Log.w("BluetoothGattServer", (new StringBuilder()).append("Unhandled exception: ").append(s).toString());
              goto _L1
        }

        public void onDescriptorReadRequest(String s, int j, int k, boolean flag, int l)
        {
            BluetoothDevice bluetoothdevice;
            bluetoothdevice = BluetoothGattServer._2D_get0(BluetoothGattServer.this).getRemoteDevice(s);
            s = getDescriptorByHandle(l);
            if(s == null)
            {
                Log.w("BluetoothGattServer", (new StringBuilder()).append("onDescriptorReadRequest() no desc for handle ").append(l).toString());
                return;
            }
            BluetoothGattServer._2D_get1(BluetoothGattServer.this).onDescriptorReadRequest(bluetoothdevice, j, k, s);
_L1:
            return;
            s;
            Log.w("BluetoothGattServer", "Unhandled exception in callback", s);
              goto _L1
        }

        public void onDescriptorWriteRequest(String s, int j, int k, int l, boolean flag, boolean flag1, int i1, 
                byte abyte0[])
        {
            BluetoothDevice bluetoothdevice;
            bluetoothdevice = BluetoothGattServer._2D_get0(BluetoothGattServer.this).getRemoteDevice(s);
            s = getDescriptorByHandle(i1);
            if(s == null)
            {
                Log.w("BluetoothGattServer", (new StringBuilder()).append("onDescriptorWriteRequest() no desc for handle ").append(i1).toString());
                return;
            }
            BluetoothGattServer._2D_get1(BluetoothGattServer.this).onDescriptorWriteRequest(bluetoothdevice, j, s, flag, flag1, k, abyte0);
_L1:
            return;
            s;
            Log.w("BluetoothGattServer", "Unhandled exception in callback", s);
              goto _L1
        }

        public void onExecuteWrite(String s, int j, boolean flag)
        {
            Log.d("BluetoothGattServer", (new StringBuilder()).append("onExecuteWrite() - device=").append(s).append(", transId=").append(j).append("execWrite=").append(flag).toString());
            s = BluetoothGattServer._2D_get0(BluetoothGattServer.this).getRemoteDevice(s);
            if(s == null)
                return;
            BluetoothGattServer._2D_get1(BluetoothGattServer.this).onExecuteWrite(s, j, flag);
_L1:
            return;
            s;
            Log.w("BluetoothGattServer", "Unhandled exception in callback", s);
              goto _L1
        }

        public void onMtuChanged(String s, int j)
        {
            Log.d("BluetoothGattServer", (new StringBuilder()).append("onMtuChanged() - device=").append(s).append(", mtu=").append(j).toString());
            s = BluetoothGattServer._2D_get0(BluetoothGattServer.this).getRemoteDevice(s);
            if(s == null)
                return;
            BluetoothGattServer._2D_get1(BluetoothGattServer.this).onMtuChanged(s, j);
_L1:
            return;
            s;
            Log.w("BluetoothGattServer", (new StringBuilder()).append("Unhandled exception: ").append(s).toString());
              goto _L1
        }

        public void onNotificationSent(String s, int j)
        {
            s = BluetoothGattServer._2D_get0(BluetoothGattServer.this).getRemoteDevice(s);
            if(s == null)
                return;
            BluetoothGattServer._2D_get1(BluetoothGattServer.this).onNotificationSent(s, j);
_L1:
            return;
            s;
            Log.w("BluetoothGattServer", (new StringBuilder()).append("Unhandled exception: ").append(s).toString());
              goto _L1
        }

        public void onPhyRead(String s, int j, int k, int l)
        {
            Log.d("BluetoothGattServer", (new StringBuilder()).append("onPhyUpdate() - device=").append(s).append(", txPHy=").append(j).append(", rxPHy=").append(k).toString());
            s = BluetoothGattServer._2D_get0(BluetoothGattServer.this).getRemoteDevice(s);
            if(s == null)
                return;
            BluetoothGattServer._2D_get1(BluetoothGattServer.this).onPhyRead(s, j, k, l);
_L1:
            return;
            s;
            Log.w("BluetoothGattServer", (new StringBuilder()).append("Unhandled exception: ").append(s).toString());
              goto _L1
        }

        public void onPhyUpdate(String s, int j, int k, int l)
        {
            Log.d("BluetoothGattServer", (new StringBuilder()).append("onPhyUpdate() - device=").append(s).append(", txPHy=").append(j).append(", rxPHy=").append(k).toString());
            s = BluetoothGattServer._2D_get0(BluetoothGattServer.this).getRemoteDevice(s);
            if(s == null)
                return;
            BluetoothGattServer._2D_get1(BluetoothGattServer.this).onPhyUpdate(s, j, k, l);
_L1:
            return;
            s;
            Log.w("BluetoothGattServer", (new StringBuilder()).append("Unhandled exception: ").append(s).toString());
              goto _L1
        }

        public void onServerConnectionState(int j, int k, boolean flag, String s)
        {
            Log.d("BluetoothGattServer", (new StringBuilder()).append("onServerConnectionState() - status=").append(j).append(" serverIf=").append(k).append(" device=").append(s).toString());
            BluetoothGattServerCallback bluetoothgattservercallback;
            bluetoothgattservercallback = BluetoothGattServer._2D_get1(BluetoothGattServer.this);
            s = BluetoothGattServer._2D_get0(BluetoothGattServer.this).getRemoteDevice(s);
            if(flag)
                k = 2;
            else
                k = 0;
            bluetoothgattservercallback.onConnectionStateChange(s, j, k);
_L1:
            return;
            s;
            Log.w("BluetoothGattServer", "Unhandled exception in callback", s);
              goto _L1
        }

        public void onServerRegistered(int j, int k)
        {
            Log.d("BluetoothGattServer", (new StringBuilder()).append("onServerRegistered() - status=").append(j).append(" serverIf=").append(k).toString());
            Object obj = BluetoothGattServer._2D_get3(BluetoothGattServer.this);
            obj;
            JVM INSTR monitorenter ;
            if(BluetoothGattServer._2D_get1(BluetoothGattServer.this) == null)
                break MISSING_BLOCK_LABEL_76;
            BluetoothGattServer._2D_set1(BluetoothGattServer.this, k);
            BluetoothGattServer._2D_get3(BluetoothGattServer.this).notify();
_L1:
            obj;
            JVM INSTR monitorexit ;
            return;
            Log.e("BluetoothGattServer", "onServerRegistered: mCallback is null");
              goto _L1
            Exception exception;
            exception;
            throw exception;
        }

        public void onServiceAdded(int j, BluetoothGattService bluetoothgattservice)
        {
            BluetoothGattService bluetoothgattservice1;
            Log.d("BluetoothGattServer", (new StringBuilder()).append("onServiceAdded() - handle=").append(bluetoothgattservice.getInstanceId()).append(" uuid=").append(bluetoothgattservice.getUuid()).append(" status=").append(j).toString());
            if(BluetoothGattServer._2D_get2(BluetoothGattServer.this) == null)
                return;
            bluetoothgattservice1 = BluetoothGattServer._2D_get2(BluetoothGattServer.this);
            BluetoothGattServer._2D_set0(BluetoothGattServer.this, null);
            bluetoothgattservice1.setInstanceId(bluetoothgattservice.getInstanceId());
            List list = bluetoothgattservice1.getCharacteristics();
            bluetoothgattservice = bluetoothgattservice.getCharacteristics();
            for(int k = 0; k < bluetoothgattservice.size(); k++)
            {
                Object obj = (BluetoothGattCharacteristic)list.get(k);
                Object obj1 = (BluetoothGattCharacteristic)bluetoothgattservice.get(k);
                ((BluetoothGattCharacteristic) (obj)).setInstanceId(((BluetoothGattCharacteristic) (obj1)).getInstanceId());
                obj = ((BluetoothGattCharacteristic) (obj)).getDescriptors();
                obj1 = ((BluetoothGattCharacteristic) (obj1)).getDescriptors();
                for(int l = 0; l < ((List) (obj1)).size(); l++)
                    ((BluetoothGattDescriptor)((List) (obj)).get(l)).setInstanceId(((BluetoothGattDescriptor)((List) (obj1)).get(l)).getInstanceId());

            }

            BluetoothGattServer._2D_get4(BluetoothGattServer.this).add(bluetoothgattservice1);
            BluetoothGattServer._2D_get1(BluetoothGattServer.this).onServiceAdded(j, bluetoothgattservice1);
_L1:
            return;
            bluetoothgattservice;
            Log.w("BluetoothGattServer", "Unhandled exception in callback", bluetoothgattservice);
              goto _L1
        }

        final BluetoothGattServer this$0;

            
            {
                this$0 = BluetoothGattServer.this;
                super();
            }
    }
;
    private BluetoothGattServerCallback mCallback;
    private BluetoothGattService mPendingService;
    private int mServerIf;
    private Object mServerIfLock;
    private IBluetoothGatt mService;
    private List mServices;
    private int mTransport;
}

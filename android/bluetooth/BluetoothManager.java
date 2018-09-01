// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import java.util.*;

// Referenced classes of package android.bluetooth:
//            BluetoothAdapter, IBluetoothManager, IBluetoothGatt, BluetoothDevice, 
//            BluetoothGattServer, BluetoothGattServerCallback

public final class BluetoothManager
{

    public BluetoothManager(Context context)
    {
        if(context.getApplicationContext() == null)
        {
            throw new IllegalArgumentException("context not associated with any application (using a mock context?)");
        } else
        {
            mAdapter = BluetoothAdapter.getDefaultAdapter();
            return;
        }
    }

    public BluetoothAdapter getAdapter()
    {
        return mAdapter;
    }

    public List getConnectedDevices(int i)
    {
        Object obj;
        Log.d("BluetoothManager", "getConnectedDevices");
        if(i != 7 && i != 8)
            throw new IllegalArgumentException((new StringBuilder()).append("Profile not supported: ").append(i).toString());
        obj = new ArrayList();
        Object obj1 = mAdapter.getBluetoothManager().getBluetoothGatt();
        if(obj1 == null)
            return ((List) (obj));
        obj1 = ((IBluetoothGatt) (obj1)).getDevicesMatchingConnectionStates(new int[] {
            2
        });
        obj = obj1;
_L2:
        return ((List) (obj));
        RemoteException remoteexception;
        remoteexception;
        Log.e("BluetoothManager", "", remoteexception);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int getConnectionState(BluetoothDevice bluetoothdevice, int i)
    {
        Log.d("BluetoothManager", "getConnectionState()");
        for(Iterator iterator = getConnectedDevices(i).iterator(); iterator.hasNext();)
            if(bluetoothdevice.equals((BluetoothDevice)iterator.next()))
                return 2;

        return 0;
    }

    public List getDevicesMatchingConnectionStates(int i, int ai[])
    {
        ArrayList arraylist;
        Log.d("BluetoothManager", "getDevicesMatchingConnectionStates");
        if(i != 7 && i != 8)
            throw new IllegalArgumentException((new StringBuilder()).append("Profile not supported: ").append(i).toString());
        arraylist = new ArrayList();
        IBluetoothGatt ibluetoothgatt = mAdapter.getBluetoothManager().getBluetoothGatt();
        if(ibluetoothgatt == null)
            return arraylist;
        try
        {
            ai = ibluetoothgatt.getDevicesMatchingConnectionStates(ai);
        }
        // Misplaced declaration of an exception variable
        catch(int ai[])
        {
            Log.e("BluetoothManager", "", ai);
            ai = arraylist;
        }
        return ai;
    }

    public BluetoothGattServer openGattServer(Context context, BluetoothGattServerCallback bluetoothgattservercallback)
    {
        return openGattServer(context, bluetoothgattservercallback, 0);
    }

    public BluetoothGattServer openGattServer(Context context, BluetoothGattServerCallback bluetoothgattservercallback, int i)
    {
        if(context == null || bluetoothgattservercallback == null)
            throw new IllegalArgumentException((new StringBuilder()).append("null parameter: ").append(context).append(" ").append(bluetoothgattservercallback).toString());
        IBluetoothGatt ibluetoothgatt;
        boolean flag;
        try
        {
            ibluetoothgatt = mAdapter.getBluetoothManager().getBluetoothGatt();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("BluetoothManager", "", context);
            return null;
        }
        if(ibluetoothgatt != null)
            break MISSING_BLOCK_LABEL_73;
        Log.e("BluetoothManager", "Fail to get GATT Server connection");
        return null;
        context = JVM INSTR new #145 <Class BluetoothGattServer>;
        context.BluetoothGattServer(ibluetoothgatt, i);
        flag = Boolean.valueOf(context.registerCallback(bluetoothgattservercallback)).booleanValue();
        if(!flag)
            context = null;
        return context;
    }

    private static final boolean DBG = true;
    private static final String TAG = "BluetoothManager";
    private static final boolean VDBG = true;
    private final BluetoothAdapter mAdapter;
}

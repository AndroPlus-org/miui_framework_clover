// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.util.Log;

// Referenced classes of package android.bluetooth:
//            BluetoothDevice, BluetoothHidDeviceAppConfiguration

public abstract class BluetoothHidDeviceCallback
{

    public BluetoothHidDeviceCallback()
    {
    }

    public void onAppStatusChanged(BluetoothDevice bluetoothdevice, BluetoothHidDeviceAppConfiguration bluetoothhiddeviceappconfiguration, boolean flag)
    {
        Log.d(TAG, (new StringBuilder()).append("onAppStatusChanged: pluggedDevice=").append(bluetoothdevice).append(" registered=").append(flag).toString());
    }

    public void onConnectionStateChanged(BluetoothDevice bluetoothdevice, int i)
    {
        Log.d(TAG, (new StringBuilder()).append("onConnectionStateChanged: device=").append(bluetoothdevice).append(" state=").append(i).toString());
    }

    public void onGetReport(BluetoothDevice bluetoothdevice, byte byte0, byte byte1, int i)
    {
        Log.d(TAG, (new StringBuilder()).append("onGetReport: device=").append(bluetoothdevice).append(" type=").append(byte0).append(" id=").append(byte1).append(" bufferSize=").append(i).toString());
    }

    public void onIntrData(BluetoothDevice bluetoothdevice, byte byte0, byte abyte0[])
    {
        Log.d(TAG, (new StringBuilder()).append("onIntrData: device=").append(bluetoothdevice).append(" reportId=").append(byte0).toString());
    }

    public void onSetProtocol(BluetoothDevice bluetoothdevice, byte byte0)
    {
        Log.d(TAG, (new StringBuilder()).append("onSetProtocol: device=").append(bluetoothdevice).append(" protocol=").append(byte0).toString());
    }

    public void onSetReport(BluetoothDevice bluetoothdevice, byte byte0, byte byte1, byte abyte0[])
    {
        Log.d(TAG, (new StringBuilder()).append("onSetReport: device=").append(bluetoothdevice).append(" type=").append(byte0).append(" id=").append(byte1).toString());
    }

    public void onVirtualCableUnplug(BluetoothDevice bluetoothdevice)
    {
        Log.d(TAG, (new StringBuilder()).append("onVirtualCableUnplug: device=").append(bluetoothdevice).toString());
    }

    private static final String TAG = android/bluetooth/BluetoothHidDeviceCallback.getSimpleName();

}

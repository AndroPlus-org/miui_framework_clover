// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;


// Referenced classes of package android.bluetooth:
//            BluetoothDevice, BluetoothGattCharacteristic, BluetoothGattDescriptor, BluetoothGattService

public abstract class BluetoothGattServerCallback
{

    public BluetoothGattServerCallback()
    {
    }

    public void onCharacteristicReadRequest(BluetoothDevice bluetoothdevice, int i, int j, BluetoothGattCharacteristic bluetoothgattcharacteristic)
    {
    }

    public void onCharacteristicWriteRequest(BluetoothDevice bluetoothdevice, int i, BluetoothGattCharacteristic bluetoothgattcharacteristic, boolean flag, boolean flag1, int j, byte abyte0[])
    {
    }

    public void onConnectionStateChange(BluetoothDevice bluetoothdevice, int i, int j)
    {
    }

    public void onConnectionUpdated(BluetoothDevice bluetoothdevice, int i, int j, int k, int l)
    {
    }

    public void onDescriptorReadRequest(BluetoothDevice bluetoothdevice, int i, int j, BluetoothGattDescriptor bluetoothgattdescriptor)
    {
    }

    public void onDescriptorWriteRequest(BluetoothDevice bluetoothdevice, int i, BluetoothGattDescriptor bluetoothgattdescriptor, boolean flag, boolean flag1, int j, byte abyte0[])
    {
    }

    public void onExecuteWrite(BluetoothDevice bluetoothdevice, int i, boolean flag)
    {
    }

    public void onMtuChanged(BluetoothDevice bluetoothdevice, int i)
    {
    }

    public void onNotificationSent(BluetoothDevice bluetoothdevice, int i)
    {
    }

    public void onPhyRead(BluetoothDevice bluetoothdevice, int i, int j, int k)
    {
    }

    public void onPhyUpdate(BluetoothDevice bluetoothdevice, int i, int j, int k)
    {
    }

    public void onServiceAdded(int i, BluetoothGattService bluetoothgattservice)
    {
    }
}

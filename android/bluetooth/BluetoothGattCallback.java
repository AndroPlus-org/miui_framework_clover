// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;


// Referenced classes of package android.bluetooth:
//            BluetoothGatt, BluetoothGattCharacteristic, BluetoothGattDescriptor

public abstract class BluetoothGattCallback
{

    public BluetoothGattCallback()
    {
    }

    public void onCharacteristicChanged(BluetoothGatt bluetoothgatt, BluetoothGattCharacteristic bluetoothgattcharacteristic)
    {
    }

    public void onCharacteristicRead(BluetoothGatt bluetoothgatt, BluetoothGattCharacteristic bluetoothgattcharacteristic, int i)
    {
    }

    public void onCharacteristicWrite(BluetoothGatt bluetoothgatt, BluetoothGattCharacteristic bluetoothgattcharacteristic, int i)
    {
    }

    public void onConnectionStateChange(BluetoothGatt bluetoothgatt, int i, int j)
    {
    }

    public void onConnectionUpdated(BluetoothGatt bluetoothgatt, int i, int j, int k, int l)
    {
    }

    public void onDescriptorRead(BluetoothGatt bluetoothgatt, BluetoothGattDescriptor bluetoothgattdescriptor, int i)
    {
    }

    public void onDescriptorWrite(BluetoothGatt bluetoothgatt, BluetoothGattDescriptor bluetoothgattdescriptor, int i)
    {
    }

    public void onMtuChanged(BluetoothGatt bluetoothgatt, int i, int j)
    {
    }

    public void onPhyRead(BluetoothGatt bluetoothgatt, int i, int j, int k)
    {
    }

    public void onPhyUpdate(BluetoothGatt bluetoothgatt, int i, int j, int k)
    {
    }

    public void onReadRemoteRssi(BluetoothGatt bluetoothgatt, int i, int j)
    {
    }

    public void onReliableWriteCompleted(BluetoothGatt bluetoothgatt, int i)
    {
    }

    public void onServicesDiscovered(BluetoothGatt bluetoothgatt, int i)
    {
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;

import android.bluetooth.IBluetoothGatt;
import android.bluetooth.IBluetoothManager;
import android.os.RemoteException;
import android.util.Log;

// Referenced classes of package android.bluetooth.le:
//            AdvertiseData, AdvertisingSetParameters, PeriodicAdvertisingParameters

public final class AdvertisingSet
{

    AdvertisingSet(int i, IBluetoothManager ibluetoothmanager)
    {
        advertiserId = i;
        try
        {
            gatt = ibluetoothmanager.getBluetoothGatt();
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IBluetoothManager ibluetoothmanager)
        {
            Log.e("AdvertisingSet", "Failed to get Bluetooth gatt - ", ibluetoothmanager);
        }
        throw new IllegalStateException("Failed to get Bluetooth");
    }

    public void enableAdvertising(boolean flag, int i, int j)
    {
        gatt.enableAdvertisingSet(advertiserId, flag, i, j);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("AdvertisingSet", "remote exception - ", remoteexception);
          goto _L1
    }

    public int getAdvertiserId()
    {
        return advertiserId;
    }

    public void getOwnAddress()
    {
        gatt.getOwnAddress(advertiserId);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("AdvertisingSet", "remote exception - ", remoteexception);
          goto _L1
    }

    void setAdvertiserId(int i)
    {
        advertiserId = i;
    }

    public void setAdvertisingData(AdvertiseData advertisedata)
    {
        gatt.setAdvertisingData(advertiserId, advertisedata);
_L1:
        return;
        advertisedata;
        Log.e("AdvertisingSet", "remote exception - ", advertisedata);
          goto _L1
    }

    public void setAdvertisingParameters(AdvertisingSetParameters advertisingsetparameters)
    {
        gatt.setAdvertisingParameters(advertiserId, advertisingsetparameters);
_L1:
        return;
        advertisingsetparameters;
        Log.e("AdvertisingSet", "remote exception - ", advertisingsetparameters);
          goto _L1
    }

    public void setPeriodicAdvertisingData(AdvertiseData advertisedata)
    {
        gatt.setPeriodicAdvertisingData(advertiserId, advertisedata);
_L1:
        return;
        advertisedata;
        Log.e("AdvertisingSet", "remote exception - ", advertisedata);
          goto _L1
    }

    public void setPeriodicAdvertisingEnabled(boolean flag)
    {
        gatt.setPeriodicAdvertisingEnable(advertiserId, flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("AdvertisingSet", "remote exception - ", remoteexception);
          goto _L1
    }

    public void setPeriodicAdvertisingParameters(PeriodicAdvertisingParameters periodicadvertisingparameters)
    {
        gatt.setPeriodicAdvertisingParameters(advertiserId, periodicadvertisingparameters);
_L1:
        return;
        periodicadvertisingparameters;
        Log.e("AdvertisingSet", "remote exception - ", periodicadvertisingparameters);
          goto _L1
    }

    public void setScanResponseData(AdvertiseData advertisedata)
    {
        gatt.setScanResponseData(advertiserId, advertisedata);
_L1:
        return;
        advertisedata;
        Log.e("AdvertisingSet", "remote exception - ", advertisedata);
          goto _L1
    }

    private static final String TAG = "AdvertisingSet";
    private int advertiserId;
    private final IBluetoothGatt gatt;
}

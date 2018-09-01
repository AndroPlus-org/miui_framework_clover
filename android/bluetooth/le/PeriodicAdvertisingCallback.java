// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;

import android.bluetooth.BluetoothDevice;

// Referenced classes of package android.bluetooth.le:
//            PeriodicAdvertisingReport

public abstract class PeriodicAdvertisingCallback
{

    public PeriodicAdvertisingCallback()
    {
    }

    public void onPeriodicAdvertisingReport(PeriodicAdvertisingReport periodicadvertisingreport)
    {
    }

    public void onSyncEstablished(int i, BluetoothDevice bluetoothdevice, int j, int k, int l, int i1)
    {
    }

    public void onSyncLost(int i)
    {
    }

    public static final int SYNC_NO_RESOURCES = 2;
    public static final int SYNC_NO_RESPONSE = 1;
    public static final int SYNC_SUCCESS = 0;
}

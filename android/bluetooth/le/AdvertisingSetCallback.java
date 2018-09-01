// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;


// Referenced classes of package android.bluetooth.le:
//            AdvertisingSet

public abstract class AdvertisingSetCallback
{

    public AdvertisingSetCallback()
    {
    }

    public void onAdvertisingDataSet(AdvertisingSet advertisingset, int i)
    {
    }

    public void onAdvertisingEnabled(AdvertisingSet advertisingset, boolean flag, int i)
    {
    }

    public void onAdvertisingParametersUpdated(AdvertisingSet advertisingset, int i, int j)
    {
    }

    public void onAdvertisingSetStarted(AdvertisingSet advertisingset, int i, int j)
    {
    }

    public void onAdvertisingSetStopped(AdvertisingSet advertisingset)
    {
    }

    public void onOwnAddressRead(AdvertisingSet advertisingset, int i, String s)
    {
    }

    public void onPeriodicAdvertisingDataSet(AdvertisingSet advertisingset, int i)
    {
    }

    public void onPeriodicAdvertisingEnabled(AdvertisingSet advertisingset, boolean flag, int i)
    {
    }

    public void onPeriodicAdvertisingParametersUpdated(AdvertisingSet advertisingset, int i)
    {
    }

    public void onScanResponseDataSet(AdvertisingSet advertisingset, int i)
    {
    }

    public static final int ADVERTISE_FAILED_ALREADY_STARTED = 3;
    public static final int ADVERTISE_FAILED_DATA_TOO_LARGE = 1;
    public static final int ADVERTISE_FAILED_FEATURE_UNSUPPORTED = 5;
    public static final int ADVERTISE_FAILED_INTERNAL_ERROR = 4;
    public static final int ADVERTISE_FAILED_TOO_MANY_ADVERTISERS = 2;
    public static final int ADVERTISE_SUCCESS = 0;
}

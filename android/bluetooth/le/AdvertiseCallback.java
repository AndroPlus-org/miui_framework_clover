// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;


// Referenced classes of package android.bluetooth.le:
//            AdvertiseSettings

public abstract class AdvertiseCallback
{

    public AdvertiseCallback()
    {
    }

    public void onStartFailure(int i)
    {
    }

    public void onStartSuccess(AdvertiseSettings advertisesettings)
    {
    }

    public static final int ADVERTISE_FAILED_ALREADY_STARTED = 3;
    public static final int ADVERTISE_FAILED_DATA_TOO_LARGE = 1;
    public static final int ADVERTISE_FAILED_FEATURE_UNSUPPORTED = 5;
    public static final int ADVERTISE_FAILED_INTERNAL_ERROR = 4;
    public static final int ADVERTISE_FAILED_TOO_MANY_ADVERTISERS = 2;
    public static final int ADVERTISE_SUCCESS = 0;
}

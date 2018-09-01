// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.companion;

import android.os.Parcelable;

public interface DeviceFilter
    extends Parcelable
{

    public static boolean matches(DeviceFilter devicefilter, Parcelable parcelable)
    {
        boolean flag;
        if(devicefilter != null)
            flag = devicefilter.matches(parcelable);
        else
            flag = true;
        return flag;
    }

    public abstract String getDeviceDisplayName(Parcelable parcelable);

    public abstract int getMediumType();

    public abstract boolean matches(Parcelable parcelable);

    public static final int MEDIUM_TYPE_BLUETOOTH = 0;
    public static final int MEDIUM_TYPE_BLUETOOTH_LE = 1;
    public static final int MEDIUM_TYPE_WIFI = 2;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;
import libcore.util.Objects;

public final class WifiDisplay
    implements Parcelable
{

    public WifiDisplay(String s, String s1, String s2, boolean flag, boolean flag1, boolean flag2)
    {
        if(s == null)
            throw new IllegalArgumentException("deviceAddress must not be null");
        if(s1 == null)
        {
            throw new IllegalArgumentException("deviceName must not be null");
        } else
        {
            mDeviceAddress = s;
            mDeviceName = s1;
            mDeviceAlias = s2;
            mIsAvailable = flag;
            mCanConnect = flag1;
            mIsRemembered = flag2;
            return;
        }
    }

    public boolean canConnect()
    {
        return mCanConnect;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(WifiDisplay wifidisplay)
    {
        boolean flag;
        if(wifidisplay != null && mDeviceAddress.equals(wifidisplay.mDeviceAddress) && mDeviceName.equals(wifidisplay.mDeviceName))
            flag = Objects.equal(mDeviceAlias, wifidisplay.mDeviceAlias);
        else
            flag = false;
        return flag;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        if(obj instanceof WifiDisplay)
            flag = equals((WifiDisplay)obj);
        else
            flag = false;
        return flag;
    }

    public String getDeviceAddress()
    {
        return mDeviceAddress;
    }

    public String getDeviceAlias()
    {
        return mDeviceAlias;
    }

    public String getDeviceName()
    {
        return mDeviceName;
    }

    public String getFriendlyDisplayName()
    {
        String s;
        if(mDeviceAlias != null)
            s = mDeviceAlias;
        else
            s = mDeviceName;
        return s;
    }

    public boolean hasSameAddress(WifiDisplay wifidisplay)
    {
        boolean flag;
        if(wifidisplay != null)
            flag = mDeviceAddress.equals(wifidisplay.mDeviceAddress);
        else
            flag = false;
        return flag;
    }

    public int hashCode()
    {
        return mDeviceAddress.hashCode();
    }

    public boolean isAvailable()
    {
        return mIsAvailable;
    }

    public boolean isRemembered()
    {
        return mIsRemembered;
    }

    public String toString()
    {
        String s = (new StringBuilder()).append(mDeviceName).append(" (").append(mDeviceAddress).append(")").toString();
        String s1 = s;
        if(mDeviceAlias != null)
            s1 = (new StringBuilder()).append(s).append(", alias ").append(mDeviceAlias).toString();
        return (new StringBuilder()).append(s1).append(", isAvailable ").append(mIsAvailable).append(", canConnect ").append(mCanConnect).append(", isRemembered ").append(mIsRemembered).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeString(mDeviceAddress);
        parcel.writeString(mDeviceName);
        parcel.writeString(mDeviceAlias);
        if(mIsAvailable)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mCanConnect)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mIsRemembered)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiDisplay createFromParcel(Parcel parcel)
        {
            String s = parcel.readString();
            String s1 = parcel.readString();
            String s2 = parcel.readString();
            boolean flag;
            boolean flag1;
            boolean flag2;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            if(parcel.readInt() != 0)
                flag2 = true;
            else
                flag2 = false;
            return new WifiDisplay(s, s1, s2, flag, flag1, flag2);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiDisplay[] newArray(int i)
        {
            WifiDisplay awifidisplay[];
            if(i == 0)
                awifidisplay = WifiDisplay.EMPTY_ARRAY;
            else
                awifidisplay = new WifiDisplay[i];
            return awifidisplay;
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final WifiDisplay EMPTY_ARRAY[] = new WifiDisplay[0];
    private final boolean mCanConnect;
    private final String mDeviceAddress;
    private final String mDeviceAlias;
    private final String mDeviceName;
    private final boolean mIsAvailable;
    private final boolean mIsRemembered;

}

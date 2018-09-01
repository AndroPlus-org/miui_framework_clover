// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.companion;

import android.net.wifi.ScanResult;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.OneTimeUseBuilder;
import java.util.Objects;
import java.util.regex.Pattern;

// Referenced classes of package android.companion:
//            DeviceFilter, BluetoothDeviceFilterUtils

public final class WifiDeviceFilter
    implements DeviceFilter
{
    public static final class Builder extends OneTimeUseBuilder
    {

        public WifiDeviceFilter build()
        {
            markUsed();
            return new WifiDeviceFilter(mNamePattern, null);
        }

        public volatile Object build()
        {
            return build();
        }

        public Builder setNamePattern(Pattern pattern)
        {
            checkNotUsed();
            mNamePattern = pattern;
            return this;
        }

        private Pattern mNamePattern;

        public Builder()
        {
        }
    }


    private WifiDeviceFilter(Parcel parcel)
    {
        this(BluetoothDeviceFilterUtils.patternFromString(parcel.readString()));
    }

    WifiDeviceFilter(Parcel parcel, WifiDeviceFilter wifidevicefilter)
    {
        this(parcel);
    }

    private WifiDeviceFilter(Pattern pattern)
    {
        mNamePattern = pattern;
    }

    WifiDeviceFilter(Pattern pattern, WifiDeviceFilter wifidevicefilter)
    {
        this(pattern);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
        {
            return false;
        } else
        {
            obj = (WifiDeviceFilter)obj;
            return Objects.equals(mNamePattern, ((WifiDeviceFilter) (obj)).mNamePattern);
        }
    }

    public String getDeviceDisplayName(ScanResult scanresult)
    {
        return BluetoothDeviceFilterUtils.getDeviceDisplayNameInternal(scanresult);
    }

    public volatile String getDeviceDisplayName(Parcelable parcelable)
    {
        return getDeviceDisplayName((ScanResult)parcelable);
    }

    public int getMediumType()
    {
        return 2;
    }

    public Pattern getNamePattern()
    {
        return mNamePattern;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            mNamePattern
        });
    }

    public boolean matches(ScanResult scanresult)
    {
        return BluetoothDeviceFilterUtils.matchesName(getNamePattern(), scanresult);
    }

    public volatile boolean matches(Parcelable parcelable)
    {
        return matches((ScanResult)parcelable);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(BluetoothDeviceFilterUtils.patternToString(getNamePattern()));
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiDeviceFilter createFromParcel(Parcel parcel)
        {
            return new WifiDeviceFilter(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiDeviceFilter[] newArray(int i)
        {
            return new WifiDeviceFilter[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final Pattern mNamePattern;

}

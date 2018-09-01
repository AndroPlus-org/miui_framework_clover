// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.companion;

import android.bluetooth.BluetoothDevice;
import android.os.*;
import android.provider.OneTimeUseBuilder;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.CollectionUtils;
import java.util.*;
import java.util.regex.Pattern;

// Referenced classes of package android.companion:
//            DeviceFilter, BluetoothDeviceFilterUtils

public final class BluetoothDeviceFilter
    implements DeviceFilter
{
    public static final class Builder extends OneTimeUseBuilder
    {

        public Builder addServiceUuid(ParcelUuid parceluuid, ParcelUuid parceluuid1)
        {
            checkNotUsed();
            mServiceUuid = ArrayUtils.add(mServiceUuid, parceluuid);
            mServiceUuidMask = ArrayUtils.add(mServiceUuidMask, parceluuid1);
            return this;
        }

        public BluetoothDeviceFilter build()
        {
            markUsed();
            return new BluetoothDeviceFilter(mNamePattern, mAddress, mServiceUuid, mServiceUuidMask, null);
        }

        public volatile Object build()
        {
            return build();
        }

        public Builder setAddress(String s)
        {
            checkNotUsed();
            mAddress = s;
            return this;
        }

        public Builder setNamePattern(Pattern pattern)
        {
            checkNotUsed();
            mNamePattern = pattern;
            return this;
        }

        private String mAddress;
        private Pattern mNamePattern;
        private ArrayList mServiceUuid;
        private ArrayList mServiceUuidMask;

        public Builder()
        {
        }
    }


    private BluetoothDeviceFilter(Parcel parcel)
    {
        this(BluetoothDeviceFilterUtils.patternFromString(parcel.readString()), parcel.readString(), readUuids(parcel), readUuids(parcel));
    }

    BluetoothDeviceFilter(Parcel parcel, BluetoothDeviceFilter bluetoothdevicefilter)
    {
        this(parcel);
    }

    private BluetoothDeviceFilter(Pattern pattern, String s, List list, List list1)
    {
        mNamePattern = pattern;
        mAddress = s;
        mServiceUuids = CollectionUtils.emptyIfNull(list);
        mServiceUuidMasks = CollectionUtils.emptyIfNull(list1);
    }

    BluetoothDeviceFilter(Pattern pattern, String s, List list, List list1, BluetoothDeviceFilter bluetoothdevicefilter)
    {
        this(pattern, s, list, list1);
    }

    private static List readUuids(Parcel parcel)
    {
        return parcel.readParcelableList(new ArrayList(), android/os/ParcelUuid.getClassLoader());
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (BluetoothDeviceFilter)obj;
        boolean flag1 = flag;
        if(Objects.equals(mNamePattern, ((BluetoothDeviceFilter) (obj)).mNamePattern))
        {
            flag1 = flag;
            if(Objects.equals(mAddress, ((BluetoothDeviceFilter) (obj)).mAddress))
            {
                flag1 = flag;
                if(Objects.equals(mServiceUuids, ((BluetoothDeviceFilter) (obj)).mServiceUuids))
                    flag1 = Objects.equals(mServiceUuidMasks, ((BluetoothDeviceFilter) (obj)).mServiceUuidMasks);
            }
        }
        return flag1;
    }

    public String getAddress()
    {
        return mAddress;
    }

    public String getDeviceDisplayName(BluetoothDevice bluetoothdevice)
    {
        return BluetoothDeviceFilterUtils.getDeviceDisplayNameInternal(bluetoothdevice);
    }

    public volatile String getDeviceDisplayName(Parcelable parcelable)
    {
        return getDeviceDisplayName((BluetoothDevice)parcelable);
    }

    public int getMediumType()
    {
        return 0;
    }

    public Pattern getNamePattern()
    {
        return mNamePattern;
    }

    public List getServiceUuidMasks()
    {
        return mServiceUuidMasks;
    }

    public List getServiceUuids()
    {
        return mServiceUuids;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            mNamePattern, mAddress, mServiceUuids, mServiceUuidMasks
        });
    }

    public boolean matches(BluetoothDevice bluetoothdevice)
    {
        boolean flag;
        if(BluetoothDeviceFilterUtils.matchesAddress(mAddress, bluetoothdevice) && BluetoothDeviceFilterUtils.matchesServiceUuids(mServiceUuids, mServiceUuidMasks, bluetoothdevice))
            flag = BluetoothDeviceFilterUtils.matchesName(getNamePattern(), bluetoothdevice);
        else
            flag = false;
        return flag;
    }

    public volatile boolean matches(Parcelable parcelable)
    {
        return matches((BluetoothDevice)parcelable);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(BluetoothDeviceFilterUtils.patternToString(getNamePattern()));
        parcel.writeString(mAddress);
        parcel.writeParcelableList(mServiceUuids, i);
        parcel.writeParcelableList(mServiceUuidMasks, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BluetoothDeviceFilter createFromParcel(Parcel parcel)
        {
            return new BluetoothDeviceFilter(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BluetoothDeviceFilter[] newArray(int i)
        {
            return new BluetoothDeviceFilter[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final String mAddress;
    private final Pattern mNamePattern;
    private final List mServiceUuidMasks;
    private final List mServiceUuids;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.*;
import java.util.UUID;

public class BluetoothGattIncludedService
    implements Parcelable
{

    private BluetoothGattIncludedService(Parcel parcel)
    {
        mUuid = ((ParcelUuid)parcel.readParcelable(null)).getUuid();
        mInstanceId = parcel.readInt();
        mServiceType = parcel.readInt();
    }

    BluetoothGattIncludedService(Parcel parcel, BluetoothGattIncludedService bluetoothgattincludedservice)
    {
        this(parcel);
    }

    public BluetoothGattIncludedService(UUID uuid, int i, int j)
    {
        mUuid = uuid;
        mInstanceId = i;
        mServiceType = j;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getInstanceId()
    {
        return mInstanceId;
    }

    public int getType()
    {
        return mServiceType;
    }

    public UUID getUuid()
    {
        return mUuid;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(new ParcelUuid(mUuid), 0);
        parcel.writeInt(mInstanceId);
        parcel.writeInt(mServiceType);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BluetoothGattIncludedService createFromParcel(Parcel parcel)
        {
            return new BluetoothGattIncludedService(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BluetoothGattIncludedService[] newArray(int i)
        {
            return new BluetoothGattIncludedService[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    protected int mInstanceId;
    protected int mServiceType;
    protected UUID mUuid;

}

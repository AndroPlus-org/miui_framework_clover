// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.*;
import java.util.UUID;

// Referenced classes of package android.bluetooth:
//            BluetoothGattCharacteristic

public class BluetoothGattDescriptor
    implements Parcelable
{

    BluetoothGattDescriptor(BluetoothGattCharacteristic bluetoothgattcharacteristic, UUID uuid, int i, int j)
    {
        initDescriptor(bluetoothgattcharacteristic, uuid, i, j);
    }

    private BluetoothGattDescriptor(Parcel parcel)
    {
        mUuid = ((ParcelUuid)parcel.readParcelable(null)).getUuid();
        mInstance = parcel.readInt();
        mPermissions = parcel.readInt();
    }

    BluetoothGattDescriptor(Parcel parcel, BluetoothGattDescriptor bluetoothgattdescriptor)
    {
        this(parcel);
    }

    public BluetoothGattDescriptor(UUID uuid, int i)
    {
        initDescriptor(null, uuid, 0, i);
    }

    public BluetoothGattDescriptor(UUID uuid, int i, int j)
    {
        initDescriptor(null, uuid, i, j);
    }

    private void initDescriptor(BluetoothGattCharacteristic bluetoothgattcharacteristic, UUID uuid, int i, int j)
    {
        mCharacteristic = bluetoothgattcharacteristic;
        mUuid = uuid;
        mInstance = i;
        mPermissions = j;
    }

    public int describeContents()
    {
        return 0;
    }

    public BluetoothGattCharacteristic getCharacteristic()
    {
        return mCharacteristic;
    }

    public int getInstanceId()
    {
        return mInstance;
    }

    public int getPermissions()
    {
        return mPermissions;
    }

    public UUID getUuid()
    {
        return mUuid;
    }

    public byte[] getValue()
    {
        return mValue;
    }

    void setCharacteristic(BluetoothGattCharacteristic bluetoothgattcharacteristic)
    {
        mCharacteristic = bluetoothgattcharacteristic;
    }

    public void setInstanceId(int i)
    {
        mInstance = i;
    }

    public boolean setValue(byte abyte0[])
    {
        mValue = abyte0;
        return true;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(new ParcelUuid(mUuid), 0);
        parcel.writeInt(mInstance);
        parcel.writeInt(mPermissions);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BluetoothGattDescriptor createFromParcel(Parcel parcel)
        {
            return new BluetoothGattDescriptor(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BluetoothGattDescriptor[] newArray(int i)
        {
            return new BluetoothGattDescriptor[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final byte DISABLE_NOTIFICATION_VALUE[] = {
        0, 0
    };
    public static final byte ENABLE_INDICATION_VALUE[] = {
        2, 0
    };
    public static final byte ENABLE_NOTIFICATION_VALUE[] = {
        1, 0
    };
    public static final int PERMISSION_READ = 1;
    public static final int PERMISSION_READ_ENCRYPTED = 2;
    public static final int PERMISSION_READ_ENCRYPTED_MITM = 4;
    public static final int PERMISSION_WRITE = 16;
    public static final int PERMISSION_WRITE_ENCRYPTED = 32;
    public static final int PERMISSION_WRITE_ENCRYPTED_MITM = 64;
    public static final int PERMISSION_WRITE_SIGNED = 128;
    public static final int PERMISSION_WRITE_SIGNED_MITM = 256;
    protected BluetoothGattCharacteristic mCharacteristic;
    protected int mInstance;
    protected int mPermissions;
    protected UUID mUuid;
    protected byte mValue[];

}

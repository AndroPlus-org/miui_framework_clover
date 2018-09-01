// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

public class OobData
    implements Parcelable
{

    public OobData()
    {
    }

    private OobData(Parcel parcel)
    {
        leBluetoothDeviceAddress = parcel.createByteArray();
        securityManagerTk = parcel.createByteArray();
        leSecureConnectionsConfirmation = parcel.createByteArray();
        leSecureConnectionsRandom = parcel.createByteArray();
    }

    OobData(Parcel parcel, OobData oobdata)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public byte[] getLeBluetoothDeviceAddress()
    {
        return leBluetoothDeviceAddress;
    }

    public byte[] getLeSecureConnectionsConfirmation()
    {
        return leSecureConnectionsConfirmation;
    }

    public byte[] getLeSecureConnectionsRandom()
    {
        return leSecureConnectionsRandom;
    }

    public byte[] getSecurityManagerTk()
    {
        return securityManagerTk;
    }

    public void setLeBluetoothDeviceAddress(byte abyte0[])
    {
        leBluetoothDeviceAddress = abyte0;
    }

    public void setLeSecureConnectionsConfirmation(byte abyte0[])
    {
        leSecureConnectionsConfirmation = abyte0;
    }

    public void setLeSecureConnectionsRandom(byte abyte0[])
    {
        leSecureConnectionsRandom = abyte0;
    }

    public void setSecurityManagerTk(byte abyte0[])
    {
        securityManagerTk = abyte0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeByteArray(leBluetoothDeviceAddress);
        parcel.writeByteArray(securityManagerTk);
        parcel.writeByteArray(leSecureConnectionsConfirmation);
        parcel.writeByteArray(leSecureConnectionsRandom);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public OobData createFromParcel(Parcel parcel)
        {
            return new OobData(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public OobData[] newArray(int i)
        {
            return new OobData[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private byte leBluetoothDeviceAddress[];
    private byte leSecureConnectionsConfirmation[];
    private byte leSecureConnectionsRandom[];
    private byte securityManagerTk[];

}

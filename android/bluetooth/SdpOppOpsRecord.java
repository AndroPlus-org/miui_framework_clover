// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public class SdpOppOpsRecord
    implements Parcelable
{

    public SdpOppOpsRecord(Parcel parcel)
    {
        mRfcommChannel = parcel.readInt();
        mL2capPsm = parcel.readInt();
        mProfileVersion = parcel.readInt();
        mServiceName = parcel.readString();
        int i = parcel.readInt();
        if(i > 0)
        {
            byte abyte0[] = new byte[i];
            parcel.readByteArray(abyte0);
            mFormatsList = abyte0;
        } else
        {
            mFormatsList = null;
        }
    }

    public SdpOppOpsRecord(String s, int i, int j, int k, byte abyte0[])
    {
        mServiceName = s;
        mRfcommChannel = i;
        mL2capPsm = j;
        mProfileVersion = k;
        mFormatsList = abyte0;
    }

    public int describeContents()
    {
        return 0;
    }

    public byte[] getFormatsList()
    {
        return mFormatsList;
    }

    public int getL2capPsm()
    {
        return mL2capPsm;
    }

    public int getProfileVersion()
    {
        return mProfileVersion;
    }

    public int getRfcommChannel()
    {
        return mRfcommChannel;
    }

    public String getServiceName()
    {
        return mServiceName;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("Bluetooth OPP Server SDP Record:\n");
        stringbuilder.append("  RFCOMM Chan Number: ").append(mRfcommChannel);
        stringbuilder.append("\n  L2CAP PSM: ").append(mL2capPsm);
        stringbuilder.append("\n  Profile version: ").append(mProfileVersion);
        stringbuilder.append("\n  Service Name: ").append(mServiceName);
        stringbuilder.append("\n  Formats List: ").append(Arrays.toString(mFormatsList));
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mRfcommChannel);
        parcel.writeInt(mL2capPsm);
        parcel.writeInt(mProfileVersion);
        parcel.writeString(mServiceName);
        if(mFormatsList != null && mFormatsList.length > 0)
        {
            parcel.writeInt(mFormatsList.length);
            parcel.writeByteArray(mFormatsList);
        } else
        {
            parcel.writeInt(0);
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SdpOppOpsRecord createFromParcel(Parcel parcel)
        {
            return new SdpOppOpsRecord(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SdpOppOpsRecord[] newArray(int i)
        {
            return new SdpOppOpsRecord[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final byte mFormatsList[];
    private final int mL2capPsm;
    private final int mProfileVersion;
    private final int mRfcommChannel;
    private final String mServiceName;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.bluetooth:
//            SdpRecord

public class SdpSapsRecord
    implements Parcelable
{

    public SdpSapsRecord(int i, int j, String s)
    {
        mRfcommChannelNumber = i;
        mProfileVersion = j;
        mServiceName = s;
    }

    public SdpSapsRecord(Parcel parcel)
    {
        mRfcommChannelNumber = parcel.readInt();
        mProfileVersion = parcel.readInt();
        mServiceName = parcel.readString();
    }

    public int describeContents()
    {
        return 0;
    }

    public int getProfileVersion()
    {
        return mProfileVersion;
    }

    public int getRfcommCannelNumber()
    {
        return mRfcommChannelNumber;
    }

    public String getServiceName()
    {
        return mServiceName;
    }

    public String toString()
    {
        String s = "Bluetooth MAS SDP Record:\n";
        if(mRfcommChannelNumber != -1)
            s = (new StringBuilder()).append("Bluetooth MAS SDP Record:\n").append("RFCOMM Chan Number: ").append(mRfcommChannelNumber).append("\n").toString();
        String s1 = s;
        if(mServiceName != null)
            s1 = (new StringBuilder()).append(s).append("Service Name: ").append(mServiceName).append("\n").toString();
        s = s1;
        if(mProfileVersion != -1)
            s = (new StringBuilder()).append(s1).append("Profile version: ").append(mProfileVersion).append("\n").toString();
        return s;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mRfcommChannelNumber);
        parcel.writeInt(mProfileVersion);
        parcel.writeString(mServiceName);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SdpSapsRecord createFromParcel(Parcel parcel)
        {
            return new SdpSapsRecord(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SdpRecord[] newArray(int i)
        {
            return new SdpRecord[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final int mProfileVersion;
    private final int mRfcommChannelNumber;
    private final String mServiceName;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

public class SdpMnsRecord
    implements Parcelable
{

    public SdpMnsRecord(int i, int j, int k, int l, String s)
    {
        mL2capPsm = i;
        mRfcommChannelNumber = j;
        mSupportedFeatures = l;
        mServiceName = s;
        mProfileVersion = k;
    }

    public SdpMnsRecord(Parcel parcel)
    {
        mRfcommChannelNumber = parcel.readInt();
        mL2capPsm = parcel.readInt();
        mServiceName = parcel.readString();
        mSupportedFeatures = parcel.readInt();
        mProfileVersion = parcel.readInt();
    }

    public int describeContents()
    {
        return 0;
    }

    public int getL2capPsm()
    {
        return mL2capPsm;
    }

    public int getProfileVersion()
    {
        return mProfileVersion;
    }

    public int getRfcommChannelNumber()
    {
        return mRfcommChannelNumber;
    }

    public String getServiceName()
    {
        return mServiceName;
    }

    public int getSupportedFeatures()
    {
        return mSupportedFeatures;
    }

    public String toString()
    {
        String s = "Bluetooth MNS SDP Record:\n";
        if(mRfcommChannelNumber != -1)
            s = (new StringBuilder()).append("Bluetooth MNS SDP Record:\n").append("RFCOMM Chan Number: ").append(mRfcommChannelNumber).append("\n").toString();
        String s1 = s;
        if(mL2capPsm != -1)
            s1 = (new StringBuilder()).append(s).append("L2CAP PSM: ").append(mL2capPsm).append("\n").toString();
        s = s1;
        if(mServiceName != null)
            s = (new StringBuilder()).append(s1).append("Service Name: ").append(mServiceName).append("\n").toString();
        s1 = s;
        if(mSupportedFeatures != -1)
            s1 = (new StringBuilder()).append(s).append("Supported features: ").append(mSupportedFeatures).append("\n").toString();
        s = s1;
        if(mProfileVersion != -1)
            s = (new StringBuilder()).append(s1).append("Profile_version: ").append(mProfileVersion).append("\n").toString();
        return s;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mRfcommChannelNumber);
        parcel.writeInt(mL2capPsm);
        parcel.writeString(mServiceName);
        parcel.writeInt(mSupportedFeatures);
        parcel.writeInt(mProfileVersion);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SdpMnsRecord createFromParcel(Parcel parcel)
        {
            return new SdpMnsRecord(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SdpMnsRecord[] newArray(int i)
        {
            return new SdpMnsRecord[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final int mL2capPsm;
    private final int mProfileVersion;
    private final int mRfcommChannelNumber;
    private final String mServiceName;
    private final int mSupportedFeatures;

}

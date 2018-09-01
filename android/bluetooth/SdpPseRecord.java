// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

public class SdpPseRecord
    implements Parcelable
{

    public SdpPseRecord(int i, int j, int k, int l, int i1, String s)
    {
        mL2capPsm = i;
        mRfcommChannelNumber = j;
        mProfileVersion = k;
        mSupportedFeatures = l;
        mSupportedRepositories = i1;
        mServiceName = s;
    }

    public SdpPseRecord(Parcel parcel)
    {
        mRfcommChannelNumber = parcel.readInt();
        mL2capPsm = parcel.readInt();
        mProfileVersion = parcel.readInt();
        mSupportedFeatures = parcel.readInt();
        mSupportedRepositories = parcel.readInt();
        mServiceName = parcel.readString();
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

    public int getSupportedRepositories()
    {
        return mSupportedRepositories;
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
        if(mProfileVersion != -1)
            s = (new StringBuilder()).append(s1).append("profile version: ").append(mProfileVersion).append("\n").toString();
        s1 = s;
        if(mServiceName != null)
            s1 = (new StringBuilder()).append(s).append("Service Name: ").append(mServiceName).append("\n").toString();
        s = s1;
        if(mSupportedFeatures != -1)
            s = (new StringBuilder()).append(s1).append("Supported features: ").append(mSupportedFeatures).append("\n").toString();
        s1 = s;
        if(mSupportedRepositories != -1)
            s1 = (new StringBuilder()).append(s).append("Supported repositories: ").append(mSupportedRepositories).append("\n").toString();
        return s1;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mRfcommChannelNumber);
        parcel.writeInt(mL2capPsm);
        parcel.writeInt(mProfileVersion);
        parcel.writeInt(mSupportedFeatures);
        parcel.writeInt(mSupportedRepositories);
        parcel.writeString(mServiceName);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SdpPseRecord createFromParcel(Parcel parcel)
        {
            return new SdpPseRecord(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SdpPseRecord[] newArray(int i)
        {
            return new SdpPseRecord[i];
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
    private final int mSupportedRepositories;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.bluetooth:
//            SdpRecord

public class SdpMasRecord
    implements Parcelable
{
    public static final class MessageType
    {

        public static final int EMAIL = 1;
        public static final int MMS = 8;
        public static final int SMS_CDMA = 4;
        public static final int SMS_GSM = 2;

        public MessageType()
        {
        }
    }


    public SdpMasRecord(int i, int j, int k, int l, int i1, int j1, String s)
    {
        mMasInstanceId = i;
        mL2capPsm = j;
        mRfcommChannelNumber = k;
        mProfileVersion = l;
        mSupportedFeatures = i1;
        mSupportedMessageTypes = j1;
        mServiceName = s;
    }

    public SdpMasRecord(Parcel parcel)
    {
        mMasInstanceId = parcel.readInt();
        mL2capPsm = parcel.readInt();
        mRfcommChannelNumber = parcel.readInt();
        mProfileVersion = parcel.readInt();
        mSupportedFeatures = parcel.readInt();
        mSupportedMessageTypes = parcel.readInt();
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

    public int getMasInstanceId()
    {
        return mMasInstanceId;
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

    public int getSupportedFeatures()
    {
        return mSupportedFeatures;
    }

    public int getSupportedMessageTypes()
    {
        return mSupportedMessageTypes;
    }

    public boolean msgSupported(int i)
    {
        boolean flag = false;
        if((mSupportedMessageTypes & i) != 0)
            flag = true;
        return flag;
    }

    public String toString()
    {
        String s = "Bluetooth MAS SDP Record:\n";
        if(mMasInstanceId != -1)
            s = (new StringBuilder()).append("Bluetooth MAS SDP Record:\n").append("Mas Instance Id: ").append(mMasInstanceId).append("\n").toString();
        String s1 = s;
        if(mRfcommChannelNumber != -1)
            s1 = (new StringBuilder()).append(s).append("RFCOMM Chan Number: ").append(mRfcommChannelNumber).append("\n").toString();
        String s2 = s1;
        if(mL2capPsm != -1)
            s2 = (new StringBuilder()).append(s1).append("L2CAP PSM: ").append(mL2capPsm).append("\n").toString();
        s = s2;
        if(mServiceName != null)
            s = (new StringBuilder()).append(s2).append("Service Name: ").append(mServiceName).append("\n").toString();
        s1 = s;
        if(mProfileVersion != -1)
            s1 = (new StringBuilder()).append(s).append("Profile version: ").append(mProfileVersion).append("\n").toString();
        s = s1;
        if(mSupportedMessageTypes != -1)
            s = (new StringBuilder()).append(s1).append("Supported msg types: ").append(mSupportedMessageTypes).append("\n").toString();
        s1 = s;
        if(mSupportedFeatures != -1)
            s1 = (new StringBuilder()).append(s).append("Supported features: ").append(mSupportedFeatures).append("\n").toString();
        return s1;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mMasInstanceId);
        parcel.writeInt(mL2capPsm);
        parcel.writeInt(mRfcommChannelNumber);
        parcel.writeInt(mProfileVersion);
        parcel.writeInt(mSupportedFeatures);
        parcel.writeInt(mSupportedMessageTypes);
        parcel.writeString(mServiceName);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SdpMasRecord createFromParcel(Parcel parcel)
        {
            return new SdpMasRecord(parcel);
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
    private final int mL2capPsm;
    private final int mMasInstanceId;
    private final int mProfileVersion;
    private final int mRfcommChannelNumber;
    private final String mServiceName;
    private final int mSupportedFeatures;
    private final int mSupportedMessageTypes;

}

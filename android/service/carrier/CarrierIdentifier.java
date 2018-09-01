// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.carrier;

import android.os.Parcel;
import android.os.Parcelable;

public class CarrierIdentifier
    implements Parcelable
{
    public static interface MatchType
    {

        public static final int ALL = 0;
        public static final int GID1 = 3;
        public static final int GID2 = 4;
        public static final int IMSI_PREFIX = 2;
        public static final int SPN = 1;
    }


    public CarrierIdentifier(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    public CarrierIdentifier(String s, String s1, String s2, String s3, String s4, String s5)
    {
        mMcc = s;
        mMnc = s1;
        mSpn = s2;
        mImsi = s3;
        mGid1 = s4;
        mGid2 = s5;
    }

    public int describeContents()
    {
        return 0;
    }

    public String getGid1()
    {
        return mGid1;
    }

    public String getGid2()
    {
        return mGid2;
    }

    public String getImsi()
    {
        return mImsi;
    }

    public String getMcc()
    {
        return mMcc;
    }

    public String getMnc()
    {
        return mMnc;
    }

    public String getSpn()
    {
        return mSpn;
    }

    public void readFromParcel(Parcel parcel)
    {
        mMcc = parcel.readString();
        mMnc = parcel.readString();
        mSpn = parcel.readString();
        mImsi = parcel.readString();
        mGid1 = parcel.readString();
        mGid2 = parcel.readString();
    }

    public String toString()
    {
        return (new StringBuilder()).append("CarrierIdentifier{mcc=").append(mMcc).append(",mnc=").append(mMnc).append(",spn=").append(mSpn).append(",imsi=").append(mImsi).append(",gid1=").append(mGid1).append(",gid2=").append(mGid2).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mMcc);
        parcel.writeString(mMnc);
        parcel.writeString(mSpn);
        parcel.writeString(mImsi);
        parcel.writeString(mGid1);
        parcel.writeString(mGid2);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CarrierIdentifier createFromParcel(Parcel parcel)
        {
            return new CarrierIdentifier(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CarrierIdentifier[] newArray(int i)
        {
            return new CarrierIdentifier[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private String mGid1;
    private String mGid2;
    private String mImsi;
    private String mMcc;
    private String mMnc;
    private String mSpn;

}

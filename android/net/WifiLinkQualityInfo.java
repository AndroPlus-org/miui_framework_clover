// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;

// Referenced classes of package android.net:
//            LinkQualityInfo

public class WifiLinkQualityInfo extends LinkQualityInfo
{

    public WifiLinkQualityInfo()
    {
        mType = 0x7fffffff;
        mRssi = 0x7fffffff;
        mTxGood = 0x7fffffffffffffffL;
        mTxBad = 0x7fffffffffffffffL;
    }

    public static WifiLinkQualityInfo createFromParcelBody(Parcel parcel)
    {
        WifiLinkQualityInfo wifilinkqualityinfo = new WifiLinkQualityInfo();
        wifilinkqualityinfo.initializeFromParcel(parcel);
        wifilinkqualityinfo.mType = parcel.readInt();
        wifilinkqualityinfo.mRssi = parcel.readInt();
        wifilinkqualityinfo.mTxGood = parcel.readLong();
        wifilinkqualityinfo.mTxBad = parcel.readLong();
        wifilinkqualityinfo.mBssid = parcel.readString();
        return wifilinkqualityinfo;
    }

    public String getBssid()
    {
        return mBssid;
    }

    public int getRssi()
    {
        return mRssi;
    }

    public long getTxBad()
    {
        return mTxBad;
    }

    public long getTxGood()
    {
        return mTxGood;
    }

    public int getType()
    {
        return mType;
    }

    public void setBssid(String s)
    {
        mBssid = s;
    }

    public void setRssi(int i)
    {
        mRssi = i;
    }

    public void setTxBad(long l)
    {
        mTxBad = l;
    }

    public void setTxGood(long l)
    {
        mTxGood = l;
    }

    public void setType(int i)
    {
        mType = i;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i, 2);
        parcel.writeInt(mType);
        parcel.writeInt(mRssi);
        parcel.writeLong(mTxGood);
        parcel.writeLong(mTxBad);
        parcel.writeString(mBssid);
    }

    private String mBssid;
    private int mRssi;
    private long mTxBad;
    private long mTxGood;
    private int mType;
}

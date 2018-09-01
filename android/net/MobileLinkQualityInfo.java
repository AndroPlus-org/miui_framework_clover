// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;

// Referenced classes of package android.net:
//            LinkQualityInfo

public class MobileLinkQualityInfo extends LinkQualityInfo
{

    public MobileLinkQualityInfo()
    {
        mMobileNetworkType = 0x7fffffff;
        mRssi = 0x7fffffff;
        mGsmErrorRate = 0x7fffffff;
        mCdmaDbm = 0x7fffffff;
        mCdmaEcio = 0x7fffffff;
        mEvdoDbm = 0x7fffffff;
        mEvdoEcio = 0x7fffffff;
        mEvdoSnr = 0x7fffffff;
        mLteSignalStrength = 0x7fffffff;
        mLteRsrp = 0x7fffffff;
        mLteRsrq = 0x7fffffff;
        mLteRssnr = 0x7fffffff;
        mLteCqi = 0x7fffffff;
    }

    public static MobileLinkQualityInfo createFromParcelBody(Parcel parcel)
    {
        MobileLinkQualityInfo mobilelinkqualityinfo = new MobileLinkQualityInfo();
        mobilelinkqualityinfo.initializeFromParcel(parcel);
        mobilelinkqualityinfo.mMobileNetworkType = parcel.readInt();
        mobilelinkqualityinfo.mRssi = parcel.readInt();
        mobilelinkqualityinfo.mGsmErrorRate = parcel.readInt();
        mobilelinkqualityinfo.mCdmaDbm = parcel.readInt();
        mobilelinkqualityinfo.mCdmaEcio = parcel.readInt();
        mobilelinkqualityinfo.mEvdoDbm = parcel.readInt();
        mobilelinkqualityinfo.mEvdoEcio = parcel.readInt();
        mobilelinkqualityinfo.mEvdoSnr = parcel.readInt();
        mobilelinkqualityinfo.mLteSignalStrength = parcel.readInt();
        mobilelinkqualityinfo.mLteRsrp = parcel.readInt();
        mobilelinkqualityinfo.mLteRsrq = parcel.readInt();
        mobilelinkqualityinfo.mLteRssnr = parcel.readInt();
        mobilelinkqualityinfo.mLteCqi = parcel.readInt();
        return mobilelinkqualityinfo;
    }

    public int getCdmaDbm()
    {
        return mCdmaDbm;
    }

    public int getCdmaEcio()
    {
        return mCdmaEcio;
    }

    public int getEvdoDbm()
    {
        return mEvdoDbm;
    }

    public int getEvdoEcio()
    {
        return mEvdoEcio;
    }

    public int getEvdoSnr()
    {
        return mEvdoSnr;
    }

    public int getGsmErrorRate()
    {
        return mGsmErrorRate;
    }

    public int getLteCqi()
    {
        return mLteCqi;
    }

    public int getLteRsrp()
    {
        return mLteRsrp;
    }

    public int getLteRsrq()
    {
        return mLteRsrq;
    }

    public int getLteRssnr()
    {
        return mLteRssnr;
    }

    public int getLteSignalStrength()
    {
        return mLteSignalStrength;
    }

    public int getMobileNetworkType()
    {
        return mMobileNetworkType;
    }

    public int getRssi()
    {
        return mRssi;
    }

    public void setCdmaDbm(int i)
    {
        mCdmaDbm = i;
    }

    public void setCdmaEcio(int i)
    {
        mCdmaEcio = i;
    }

    public void setEvdoDbm(int i)
    {
        mEvdoDbm = i;
    }

    public void setEvdoEcio(int i)
    {
        mEvdoEcio = i;
    }

    public void setEvdoSnr(int i)
    {
        mEvdoSnr = i;
    }

    public void setGsmErrorRate(int i)
    {
        mGsmErrorRate = i;
    }

    public void setLteCqi(int i)
    {
        mLteCqi = i;
    }

    public void setLteRsrp(int i)
    {
        mLteRsrp = i;
    }

    public void setLteRsrq(int i)
    {
        mLteRsrq = i;
    }

    public void setLteRssnr(int i)
    {
        mLteRssnr = i;
    }

    public void setLteSignalStrength(int i)
    {
        mLteSignalStrength = i;
    }

    public void setMobileNetworkType(int i)
    {
        mMobileNetworkType = i;
    }

    public void setRssi(int i)
    {
        mRssi = i;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i, 3);
        parcel.writeInt(mMobileNetworkType);
        parcel.writeInt(mRssi);
        parcel.writeInt(mGsmErrorRate);
        parcel.writeInt(mCdmaDbm);
        parcel.writeInt(mCdmaEcio);
        parcel.writeInt(mEvdoDbm);
        parcel.writeInt(mEvdoEcio);
        parcel.writeInt(mEvdoSnr);
        parcel.writeInt(mLteSignalStrength);
        parcel.writeInt(mLteRsrp);
        parcel.writeInt(mLteRsrq);
        parcel.writeInt(mLteRssnr);
        parcel.writeInt(mLteCqi);
    }

    private int mCdmaDbm;
    private int mCdmaEcio;
    private int mEvdoDbm;
    private int mEvdoEcio;
    private int mEvdoSnr;
    private int mGsmErrorRate;
    private int mLteCqi;
    private int mLteRsrp;
    private int mLteRsrq;
    private int mLteRssnr;
    private int mLteSignalStrength;
    private int mMobileNetworkType;
    private int mRssi;
}

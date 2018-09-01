// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.net:
//            WifiLinkQualityInfo, MobileLinkQualityInfo

public class LinkQualityInfo
    implements Parcelable
{

    public LinkQualityInfo()
    {
        mNetworkType = -1;
        mNormalizedSignalStrength = 0x7fffffff;
        mPacketCount = 0x7fffffffffffffffL;
        mPacketErrorCount = 0x7fffffffffffffffL;
        mTheoreticalTxBandwidth = 0x7fffffff;
        mTheoreticalRxBandwidth = 0x7fffffff;
        mTheoreticalLatency = 0x7fffffff;
        mLastDataSampleTime = 0x7fffffffffffffffL;
        mDataSampleDuration = 0x7fffffff;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getDataSampleDuration()
    {
        return mDataSampleDuration;
    }

    public long getLastDataSampleTime()
    {
        return mLastDataSampleTime;
    }

    public int getNetworkType()
    {
        return mNetworkType;
    }

    public int getNormalizedSignalStrength()
    {
        return mNormalizedSignalStrength;
    }

    public long getPacketCount()
    {
        return mPacketCount;
    }

    public long getPacketErrorCount()
    {
        return mPacketErrorCount;
    }

    public int getTheoreticalLatency()
    {
        return mTheoreticalLatency;
    }

    public int getTheoreticalRxBandwidth()
    {
        return mTheoreticalRxBandwidth;
    }

    public int getTheoreticalTxBandwidth()
    {
        return mTheoreticalTxBandwidth;
    }

    protected void initializeFromParcel(Parcel parcel)
    {
        mNetworkType = parcel.readInt();
        mNormalizedSignalStrength = parcel.readInt();
        mPacketCount = parcel.readLong();
        mPacketErrorCount = parcel.readLong();
        mTheoreticalTxBandwidth = parcel.readInt();
        mTheoreticalRxBandwidth = parcel.readInt();
        mTheoreticalLatency = parcel.readInt();
        mLastDataSampleTime = parcel.readLong();
        mDataSampleDuration = parcel.readInt();
    }

    public void setDataSampleDuration(int i)
    {
        mDataSampleDuration = i;
    }

    public void setLastDataSampleTime(long l)
    {
        mLastDataSampleTime = l;
    }

    public void setNetworkType(int i)
    {
        mNetworkType = i;
    }

    public void setNormalizedSignalStrength(int i)
    {
        mNormalizedSignalStrength = i;
    }

    public void setPacketCount(long l)
    {
        mPacketCount = l;
    }

    public void setPacketErrorCount(long l)
    {
        mPacketErrorCount = l;
    }

    public void setTheoreticalLatency(int i)
    {
        mTheoreticalLatency = i;
    }

    public void setTheoreticalRxBandwidth(int i)
    {
        mTheoreticalRxBandwidth = i;
    }

    public void setTheoreticalTxBandwidth(int i)
    {
        mTheoreticalTxBandwidth = i;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcel(parcel, i, 1);
    }

    public void writeToParcel(Parcel parcel, int i, int j)
    {
        parcel.writeInt(j);
        parcel.writeInt(mNetworkType);
        parcel.writeInt(mNormalizedSignalStrength);
        parcel.writeLong(mPacketCount);
        parcel.writeLong(mPacketErrorCount);
        parcel.writeInt(mTheoreticalTxBandwidth);
        parcel.writeInt(mTheoreticalRxBandwidth);
        parcel.writeInt(mTheoreticalLatency);
        parcel.writeLong(mLastDataSampleTime);
        parcel.writeInt(mDataSampleDuration);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public LinkQualityInfo createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            if(i == 1)
            {
                LinkQualityInfo linkqualityinfo = new LinkQualityInfo();
                linkqualityinfo.initializeFromParcel(parcel);
                return linkqualityinfo;
            }
            if(i == 2)
                return WifiLinkQualityInfo.createFromParcelBody(parcel);
            if(i == 3)
                return MobileLinkQualityInfo.createFromParcelBody(parcel);
            else
                return null;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public LinkQualityInfo[] newArray(int i)
        {
            return new LinkQualityInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int NORMALIZED_MAX_SIGNAL_STRENGTH = 99;
    public static final int NORMALIZED_MIN_SIGNAL_STRENGTH = 0;
    public static final int NORMALIZED_SIGNAL_STRENGTH_RANGE = 100;
    protected static final int OBJECT_TYPE_LINK_QUALITY_INFO = 1;
    protected static final int OBJECT_TYPE_MOBILE_LINK_QUALITY_INFO = 3;
    protected static final int OBJECT_TYPE_WIFI_LINK_QUALITY_INFO = 2;
    public static final int UNKNOWN_INT = 0x7fffffff;
    public static final long UNKNOWN_LONG = 0x7fffffffffffffffL;
    private int mDataSampleDuration;
    private long mLastDataSampleTime;
    private int mNetworkType;
    private int mNormalizedSignalStrength;
    private long mPacketCount;
    private long mPacketErrorCount;
    private int mTheoreticalLatency;
    private int mTheoreticalRxBandwidth;
    private int mTheoreticalTxBandwidth;

}

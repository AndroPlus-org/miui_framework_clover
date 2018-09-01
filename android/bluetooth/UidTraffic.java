// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

public class UidTraffic
    implements Cloneable, Parcelable
{

    public UidTraffic(int i)
    {
        mAppUid = i;
    }

    public UidTraffic(int i, long l, long l1)
    {
        mAppUid = i;
        mRxBytes = l;
        mTxBytes = l1;
    }

    UidTraffic(Parcel parcel)
    {
        mAppUid = parcel.readInt();
        mRxBytes = parcel.readLong();
        mTxBytes = parcel.readLong();
    }

    public void addRxBytes(long l)
    {
        mRxBytes = mRxBytes + l;
    }

    public void addTxBytes(long l)
    {
        mTxBytes = mTxBytes + l;
    }

    public UidTraffic clone()
    {
        return new UidTraffic(mAppUid, mRxBytes, mTxBytes);
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public int describeContents()
    {
        return 0;
    }

    public long getRxBytes()
    {
        return mRxBytes;
    }

    public long getTxBytes()
    {
        return mTxBytes;
    }

    public int getUid()
    {
        return mAppUid;
    }

    public void setRxBytes(long l)
    {
        mRxBytes = l;
    }

    public void setTxBytes(long l)
    {
        mTxBytes = l;
    }

    public String toString()
    {
        return (new StringBuilder()).append("UidTraffic{mAppUid=").append(mAppUid).append(", mRxBytes=").append(mRxBytes).append(", mTxBytes=").append(mTxBytes).append('}').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mAppUid);
        parcel.writeLong(mRxBytes);
        parcel.writeLong(mTxBytes);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public UidTraffic createFromParcel(Parcel parcel)
        {
            return new UidTraffic(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public UidTraffic[] newArray(int i)
        {
            return new UidTraffic[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final int mAppUid;
    private long mRxBytes;
    private long mTxBytes;

}

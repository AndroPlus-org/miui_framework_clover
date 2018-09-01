// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;

import android.os.Parcel;
import android.os.Parcelable;

public final class ScanSettings
    implements Parcelable
{
    public static final class Builder
    {

        private boolean isValidCallbackType(int i)
        {
            boolean flag;
            for(flag = true; i == 1 || i == 2 || i == 4 || i == 8;)
                return true;

            if(i != 6)
                flag = false;
            return flag;
        }

        public ScanSettings build()
        {
            return new ScanSettings(mScanMode, mCallbackType, mScanResultType, mReportDelayMillis, mMatchMode, mNumOfMatchesPerFilter, mLegacy, mPhy, null);
        }

        public Builder setCallbackType(int i)
        {
            if(!isValidCallbackType(i))
            {
                throw new IllegalArgumentException((new StringBuilder()).append("invalid callback type - ").append(i).toString());
            } else
            {
                mCallbackType = i;
                return this;
            }
        }

        public Builder setLegacy(boolean flag)
        {
            mLegacy = flag;
            return this;
        }

        public Builder setMatchMode(int i)
        {
            if(i < 1 || i > 2)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("invalid matchMode ").append(i).toString());
            } else
            {
                mMatchMode = i;
                return this;
            }
        }

        public Builder setNumOfMatches(int i)
        {
            if(i < 1 || i > 3)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("invalid numOfMatches ").append(i).toString());
            } else
            {
                mNumOfMatchesPerFilter = i;
                return this;
            }
        }

        public Builder setPhy(int i)
        {
            mPhy = i;
            return this;
        }

        public Builder setReportDelay(long l)
        {
            if(l < 0L)
            {
                throw new IllegalArgumentException("reportDelay must be > 0");
            } else
            {
                mReportDelayMillis = l;
                return this;
            }
        }

        public Builder setScanMode(int i)
        {
            if(i < -1 || i > 2)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("invalid scan mode ").append(i).toString());
            } else
            {
                mScanMode = i;
                return this;
            }
        }

        public Builder setScanResultType(int i)
        {
            if(i < 0 || i > 1)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("invalid scanResultType - ").append(i).toString());
            } else
            {
                mScanResultType = i;
                return this;
            }
        }

        private int mCallbackType;
        private boolean mLegacy;
        private int mMatchMode;
        private int mNumOfMatchesPerFilter;
        private int mPhy;
        private long mReportDelayMillis;
        private int mScanMode;
        private int mScanResultType;

        public Builder()
        {
            mScanMode = 0;
            mCallbackType = 1;
            mScanResultType = 0;
            mReportDelayMillis = 0L;
            mMatchMode = 1;
            mNumOfMatchesPerFilter = 3;
            mLegacy = true;
            mPhy = 255;
        }
    }


    private ScanSettings(int i, int j, int k, long l, int i1, int j1, 
            boolean flag, int k1)
    {
        mScanMode = i;
        mCallbackType = j;
        mScanResultType = k;
        mReportDelayMillis = l;
        mNumOfMatchesPerFilter = j1;
        mMatchMode = i1;
        mLegacy = flag;
        mPhy = k1;
    }

    ScanSettings(int i, int j, int k, long l, int i1, int j1, 
            boolean flag, int k1, ScanSettings scansettings)
    {
        this(i, j, k, l, i1, j1, flag, k1);
    }

    private ScanSettings(Parcel parcel)
    {
        boolean flag = false;
        super();
        mScanMode = parcel.readInt();
        mCallbackType = parcel.readInt();
        mScanResultType = parcel.readInt();
        mReportDelayMillis = parcel.readLong();
        mMatchMode = parcel.readInt();
        mNumOfMatchesPerFilter = parcel.readInt();
        if(parcel.readInt() != 0)
            flag = true;
        mLegacy = flag;
        mPhy = parcel.readInt();
    }

    ScanSettings(Parcel parcel, ScanSettings scansettings)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public int getCallbackType()
    {
        return mCallbackType;
    }

    public boolean getLegacy()
    {
        return mLegacy;
    }

    public int getMatchMode()
    {
        return mMatchMode;
    }

    public int getNumOfMatches()
    {
        return mNumOfMatchesPerFilter;
    }

    public int getPhy()
    {
        return mPhy;
    }

    public long getReportDelayMillis()
    {
        return mReportDelayMillis;
    }

    public int getScanMode()
    {
        return mScanMode;
    }

    public int getScanResultType()
    {
        return mScanResultType;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mScanMode);
        parcel.writeInt(mCallbackType);
        parcel.writeInt(mScanResultType);
        parcel.writeLong(mReportDelayMillis);
        parcel.writeInt(mMatchMode);
        parcel.writeInt(mNumOfMatchesPerFilter);
        if(mLegacy)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(mPhy);
    }

    public static final int CALLBACK_TYPE_ALL_MATCHES = 1;
    public static final int CALLBACK_TYPE_FIRST_MATCH = 2;
    public static final int CALLBACK_TYPE_MATCH_LOST = 4;
    public static final int CALLBACK_TYPE_SENSOR_ROUTING = 8;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ScanSettings createFromParcel(Parcel parcel)
        {
            return new ScanSettings(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ScanSettings[] newArray(int i)
        {
            return new ScanSettings[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int MATCH_MODE_AGGRESSIVE = 1;
    public static final int MATCH_MODE_STICKY = 2;
    public static final int MATCH_NUM_FEW_ADVERTISEMENT = 2;
    public static final int MATCH_NUM_MAX_ADVERTISEMENT = 3;
    public static final int MATCH_NUM_ONE_ADVERTISEMENT = 1;
    public static final int PHY_LE_ALL_SUPPORTED = 255;
    public static final int SCAN_MODE_BALANCED = 1;
    public static final int SCAN_MODE_LOW_LATENCY = 2;
    public static final int SCAN_MODE_LOW_POWER = 0;
    public static final int SCAN_MODE_OPPORTUNISTIC = -1;
    public static final int SCAN_RESULT_TYPE_ABBREVIATED = 1;
    public static final int SCAN_RESULT_TYPE_FULL = 0;
    private int mCallbackType;
    private boolean mLegacy;
    private int mMatchMode;
    private int mNumOfMatchesPerFilter;
    private int mPhy;
    private long mReportDelayMillis;
    private int mScanMode;
    private int mScanResultType;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;

import android.os.Parcel;
import android.os.Parcelable;

public final class AdvertiseSettings
    implements Parcelable
{
    public static final class Builder
    {

        public AdvertiseSettings build()
        {
            return new AdvertiseSettings(mMode, mTxPowerLevel, mConnectable, mTimeoutMillis, null);
        }

        public Builder setAdvertiseMode(int i)
        {
            if(i < 0 || i > 2)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("unknown mode ").append(i).toString());
            } else
            {
                mMode = i;
                return this;
            }
        }

        public Builder setConnectable(boolean flag)
        {
            mConnectable = flag;
            return this;
        }

        public Builder setTimeout(int i)
        {
            if(i < 0 || i > 0x2bf20)
            {
                throw new IllegalArgumentException("timeoutMillis invalid (must be 0-180000 milliseconds)");
            } else
            {
                mTimeoutMillis = i;
                return this;
            }
        }

        public Builder setTxPowerLevel(int i)
        {
            if(i < 0 || i > 3)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("unknown tx power level ").append(i).toString());
            } else
            {
                mTxPowerLevel = i;
                return this;
            }
        }

        private boolean mConnectable;
        private int mMode;
        private int mTimeoutMillis;
        private int mTxPowerLevel;

        public Builder()
        {
            mMode = 0;
            mTxPowerLevel = 2;
            mTimeoutMillis = 0;
            mConnectable = true;
        }
    }


    private AdvertiseSettings(int i, int j, boolean flag, int k)
    {
        mAdvertiseMode = i;
        mAdvertiseTxPowerLevel = j;
        mAdvertiseConnectable = flag;
        mAdvertiseTimeoutMillis = k;
    }

    AdvertiseSettings(int i, int j, boolean flag, int k, AdvertiseSettings advertisesettings)
    {
        this(i, j, flag, k);
    }

    private AdvertiseSettings(Parcel parcel)
    {
        boolean flag = false;
        super();
        mAdvertiseMode = parcel.readInt();
        mAdvertiseTxPowerLevel = parcel.readInt();
        if(parcel.readInt() != 0)
            flag = true;
        mAdvertiseConnectable = flag;
        mAdvertiseTimeoutMillis = parcel.readInt();
    }

    AdvertiseSettings(Parcel parcel, AdvertiseSettings advertisesettings)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public int getMode()
    {
        return mAdvertiseMode;
    }

    public int getTimeout()
    {
        return mAdvertiseTimeoutMillis;
    }

    public int getTxPowerLevel()
    {
        return mAdvertiseTxPowerLevel;
    }

    public boolean isConnectable()
    {
        return mAdvertiseConnectable;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Settings [mAdvertiseMode=").append(mAdvertiseMode).append(", mAdvertiseTxPowerLevel=").append(mAdvertiseTxPowerLevel).append(", mAdvertiseConnectable=").append(mAdvertiseConnectable).append(", mAdvertiseTimeoutMillis=").append(mAdvertiseTimeoutMillis).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mAdvertiseMode);
        parcel.writeInt(mAdvertiseTxPowerLevel);
        if(mAdvertiseConnectable)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(mAdvertiseTimeoutMillis);
    }

    public static final int ADVERTISE_MODE_BALANCED = 1;
    public static final int ADVERTISE_MODE_LOW_LATENCY = 2;
    public static final int ADVERTISE_MODE_LOW_POWER = 0;
    public static final int ADVERTISE_TX_POWER_HIGH = 3;
    public static final int ADVERTISE_TX_POWER_LOW = 1;
    public static final int ADVERTISE_TX_POWER_MEDIUM = 2;
    public static final int ADVERTISE_TX_POWER_ULTRA_LOW = 0;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AdvertiseSettings createFromParcel(Parcel parcel)
        {
            return new AdvertiseSettings(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AdvertiseSettings[] newArray(int i)
        {
            return new AdvertiseSettings[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int LIMITED_ADVERTISING_MAX_MILLIS = 0x2bf20;
    private final boolean mAdvertiseConnectable;
    private final int mAdvertiseMode;
    private final int mAdvertiseTimeoutMillis;
    private final int mAdvertiseTxPowerLevel;

}

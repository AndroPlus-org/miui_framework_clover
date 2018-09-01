// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.usage;

import android.content.res.Configuration;
import android.os.Parcel;
import android.os.Parcelable;

public final class ConfigurationStats
    implements Parcelable
{

    public ConfigurationStats()
    {
    }

    public ConfigurationStats(ConfigurationStats configurationstats)
    {
        mConfiguration = configurationstats.mConfiguration;
        mBeginTimeStamp = configurationstats.mBeginTimeStamp;
        mEndTimeStamp = configurationstats.mEndTimeStamp;
        mLastTimeActive = configurationstats.mLastTimeActive;
        mTotalTimeActive = configurationstats.mTotalTimeActive;
        mActivationCount = configurationstats.mActivationCount;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getActivationCount()
    {
        return mActivationCount;
    }

    public Configuration getConfiguration()
    {
        return mConfiguration;
    }

    public long getFirstTimeStamp()
    {
        return mBeginTimeStamp;
    }

    public long getLastTimeActive()
    {
        return mLastTimeActive;
    }

    public long getLastTimeStamp()
    {
        return mEndTimeStamp;
    }

    public long getTotalTimeActive()
    {
        return mTotalTimeActive;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(mConfiguration != null)
        {
            parcel.writeInt(1);
            mConfiguration.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeLong(mBeginTimeStamp);
        parcel.writeLong(mEndTimeStamp);
        parcel.writeLong(mLastTimeActive);
        parcel.writeLong(mTotalTimeActive);
        parcel.writeInt(mActivationCount);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ConfigurationStats createFromParcel(Parcel parcel)
        {
            ConfigurationStats configurationstats = new ConfigurationStats();
            if(parcel.readInt() != 0)
                configurationstats.mConfiguration = (Configuration)Configuration.CREATOR.createFromParcel(parcel);
            configurationstats.mBeginTimeStamp = parcel.readLong();
            configurationstats.mEndTimeStamp = parcel.readLong();
            configurationstats.mLastTimeActive = parcel.readLong();
            configurationstats.mTotalTimeActive = parcel.readLong();
            configurationstats.mActivationCount = parcel.readInt();
            return configurationstats;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ConfigurationStats[] newArray(int i)
        {
            return new ConfigurationStats[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public int mActivationCount;
    public long mBeginTimeStamp;
    public Configuration mConfiguration;
    public long mEndTimeStamp;
    public long mLastTimeActive;
    public long mTotalTimeActive;

}

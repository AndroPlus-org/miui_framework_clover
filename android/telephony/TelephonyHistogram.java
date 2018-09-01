// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public final class TelephonyHistogram
    implements Parcelable
{

    public TelephonyHistogram(int i, int j, int k)
    {
        if(k <= 1)
        {
            throw new IllegalArgumentException("Invalid number of buckets");
        } else
        {
            mCategory = i;
            mId = j;
            mMinTimeMs = 0x7fffffff;
            mMaxTimeMs = 0;
            mAverageTimeMs = 0;
            mSampleCount = 0;
            mInitialTimings = new int[10];
            mBucketCount = k;
            mBucketEndPoints = new int[k - 1];
            mBucketCounters = new int[k];
            return;
        }
    }

    public TelephonyHistogram(Parcel parcel)
    {
        mCategory = parcel.readInt();
        mId = parcel.readInt();
        mMinTimeMs = parcel.readInt();
        mMaxTimeMs = parcel.readInt();
        mAverageTimeMs = parcel.readInt();
        mSampleCount = parcel.readInt();
        if(parcel.readInt() == 1)
        {
            mInitialTimings = new int[10];
            parcel.readIntArray(mInitialTimings);
        }
        mBucketCount = parcel.readInt();
        mBucketEndPoints = new int[mBucketCount - 1];
        parcel.readIntArray(mBucketEndPoints);
        mBucketCounters = new int[mBucketCount];
        parcel.readIntArray(mBucketCounters);
    }

    public TelephonyHistogram(TelephonyHistogram telephonyhistogram)
    {
        mCategory = telephonyhistogram.getCategory();
        mId = telephonyhistogram.getId();
        mMinTimeMs = telephonyhistogram.getMinTime();
        mMaxTimeMs = telephonyhistogram.getMaxTime();
        mAverageTimeMs = telephonyhistogram.getAverageTime();
        mSampleCount = telephonyhistogram.getSampleCount();
        mInitialTimings = telephonyhistogram.getInitialTimings();
        mBucketCount = telephonyhistogram.getBucketCount();
        mBucketEndPoints = telephonyhistogram.getBucketEndPoints();
        mBucketCounters = telephonyhistogram.getBucketCounters();
    }

    private void addToBucketCounter(int ai[], int ai1[], int i)
    {
        int j;
        for(j = 0; j < ai.length; j++)
            if(i <= ai[j])
            {
                ai1[j] = ai1[j] + 1;
                return;
            }

        ai1[j] = ai1[j] + 1;
    }

    private void calculateBucketEndPoints(int ai[])
    {
        for(int i = 1; i < mBucketCount; i++)
            ai[i - 1] = mMinTimeMs + ((mMaxTimeMs - mMinTimeMs) * i) / mBucketCount;

    }

    private int[] getDeepCopyOfArray(int ai[])
    {
        int ai1[] = new int[ai.length];
        System.arraycopy(ai, 0, ai1, 0, ai.length);
        return ai1;
    }

    private int[] getInitialTimings()
    {
        return mInitialTimings;
    }

    public void addTimeTaken(int i)
    {
        if(mSampleCount == 0 || mSampleCount == 0x7fffffff)
        {
            if(mSampleCount == 0)
            {
                mMinTimeMs = i;
                mMaxTimeMs = i;
                mAverageTimeMs = i;
            } else
            {
                mInitialTimings = new int[10];
            }
            mSampleCount = 1;
            Arrays.fill(mInitialTimings, 0);
            mInitialTimings[0] = i;
            Arrays.fill(mBucketEndPoints, 0);
            Arrays.fill(mBucketCounters, 0);
        } else
        {
            if(i < mMinTimeMs)
                mMinTimeMs = i;
            if(i > mMaxTimeMs)
                mMaxTimeMs = i;
            long l = mAverageTimeMs;
            long l1 = mSampleCount;
            long l2 = i;
            int j = mSampleCount + 1;
            mSampleCount = j;
            mAverageTimeMs = (int)((l * l1 + l2) / (long)j);
            if(mSampleCount < 10)
                mInitialTimings[mSampleCount - 1] = i;
            else
            if(mSampleCount == 10)
            {
                mInitialTimings[mSampleCount - 1] = i;
                calculateBucketEndPoints(mBucketEndPoints);
                for(i = 0; i < 10; i++)
                    addToBucketCounter(mBucketEndPoints, mBucketCounters, mInitialTimings[i]);

                mInitialTimings = null;
            } else
            {
                addToBucketCounter(mBucketEndPoints, mBucketCounters, i);
            }
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public int getAverageTime()
    {
        return mAverageTimeMs;
    }

    public int getBucketCount()
    {
        return mBucketCount;
    }

    public int[] getBucketCounters()
    {
        if(mSampleCount > 1 && mSampleCount < 10)
        {
            int ai[] = new int[mBucketCount - 1];
            int ai1[] = new int[mBucketCount];
            calculateBucketEndPoints(ai);
            for(int i = 0; i < mSampleCount; i++)
                addToBucketCounter(ai, ai1, mInitialTimings[i]);

            return ai1;
        } else
        {
            return getDeepCopyOfArray(mBucketCounters);
        }
    }

    public int[] getBucketEndPoints()
    {
        if(mSampleCount > 1 && mSampleCount < 10)
        {
            int ai[] = new int[mBucketCount - 1];
            calculateBucketEndPoints(ai);
            return ai;
        } else
        {
            return getDeepCopyOfArray(mBucketEndPoints);
        }
    }

    public int getCategory()
    {
        return mCategory;
    }

    public int getId()
    {
        return mId;
    }

    public int getMaxTime()
    {
        return mMaxTimeMs;
    }

    public int getMinTime()
    {
        return mMinTimeMs;
    }

    public int getSampleCount()
    {
        return mSampleCount;
    }

    public String toString()
    {
        String s = (new StringBuilder()).append(" Histogram id = ").append(mId).append(" Time(ms): min = ").append(mMinTimeMs).append(" max = ").append(mMaxTimeMs).append(" avg = ").append(mAverageTimeMs).append(" Count = ").append(mSampleCount).toString();
        if(mSampleCount < 10)
            return s;
        StringBuffer stringbuffer = new StringBuffer(" Interval Endpoints:");
        for(int i = 0; i < mBucketEndPoints.length; i++)
            stringbuffer.append((new StringBuilder()).append(" ").append(mBucketEndPoints[i]).toString());

        stringbuffer.append(" Interval counters:");
        for(int j = 0; j < mBucketCounters.length; j++)
            stringbuffer.append((new StringBuilder()).append(" ").append(mBucketCounters[j]).toString());

        return (new StringBuilder()).append(s).append(stringbuffer).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mCategory);
        parcel.writeInt(mId);
        parcel.writeInt(mMinTimeMs);
        parcel.writeInt(mMaxTimeMs);
        parcel.writeInt(mAverageTimeMs);
        parcel.writeInt(mSampleCount);
        if(mInitialTimings == null)
        {
            parcel.writeInt(0);
        } else
        {
            parcel.writeInt(1);
            parcel.writeIntArray(mInitialTimings);
        }
        parcel.writeInt(mBucketCount);
        parcel.writeIntArray(mBucketEndPoints);
        parcel.writeIntArray(mBucketCounters);
    }

    private static final int ABSENT = 0;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public TelephonyHistogram createFromParcel(Parcel parcel)
        {
            return new TelephonyHistogram(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public TelephonyHistogram[] newArray(int i)
        {
            return new TelephonyHistogram[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int PRESENT = 1;
    private static final int RANGE_CALCULATION_COUNT = 10;
    public static final int TELEPHONY_CATEGORY_RIL = 1;
    private int mAverageTimeMs;
    private final int mBucketCount;
    private final int mBucketCounters[];
    private final int mBucketEndPoints[];
    private final int mCategory;
    private final int mId;
    private int mInitialTimings[];
    private int mMaxTimeMs;
    private int mMinTimeMs;
    private int mSampleCount;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.Parcel;
import android.os.Parcelable;

public class FusedBatchOptions
    implements Parcelable
{
    public static final class BatchFlags
    {

        public static int CALLBACK_ON_LOCATION_FIX = 2;
        public static int WAKEUP_ON_FIFO_FULL = 1;


        public BatchFlags()
        {
        }
    }

    public static final class SourceTechnologies
    {

        public static int BLUETOOTH = 16;
        public static int CELL = 8;
        public static int GNSS = 1;
        public static int SENSORS = 4;
        public static int WIFI = 2;


        public SourceTechnologies()
        {
        }
    }


    public FusedBatchOptions()
    {
        mPeriodInNS = 0L;
        mSourcesToUse = 0;
        mFlags = 0;
        mMaxPowerAllocationInMW = 0.0D;
        mSmallestDisplacementMeters = 0.0F;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getFlags()
    {
        return mFlags;
    }

    public double getMaxPowerAllocationInMW()
    {
        return mMaxPowerAllocationInMW;
    }

    public long getPeriodInNS()
    {
        return mPeriodInNS;
    }

    public float getSmallestDisplacementMeters()
    {
        return mSmallestDisplacementMeters;
    }

    public int getSourcesToUse()
    {
        return mSourcesToUse;
    }

    public boolean isFlagSet(int i)
    {
        boolean flag = false;
        if((mFlags & i) != 0)
            flag = true;
        return flag;
    }

    public boolean isSourceToUseSet(int i)
    {
        boolean flag = false;
        if((mSourcesToUse & i) != 0)
            flag = true;
        return flag;
    }

    public void resetFlag(int i)
    {
        mFlags = mFlags & i;
    }

    public void resetSourceToUse(int i)
    {
        mSourcesToUse = mSourcesToUse & i;
    }

    public void setFlag(int i)
    {
        mFlags = mFlags | i;
    }

    public void setMaxPowerAllocationInMW(double d)
    {
        mMaxPowerAllocationInMW = d;
    }

    public void setPeriodInNS(long l)
    {
        mPeriodInNS = l;
    }

    public void setSmallestDisplacementMeters(float f)
    {
        mSmallestDisplacementMeters = f;
    }

    public void setSourceToUse(int i)
    {
        mSourcesToUse = mSourcesToUse | i;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeDouble(mMaxPowerAllocationInMW);
        parcel.writeLong(mPeriodInNS);
        parcel.writeInt(mSourcesToUse);
        parcel.writeInt(mFlags);
        parcel.writeFloat(mSmallestDisplacementMeters);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public FusedBatchOptions createFromParcel(Parcel parcel)
        {
            FusedBatchOptions fusedbatchoptions = new FusedBatchOptions();
            fusedbatchoptions.setMaxPowerAllocationInMW(parcel.readDouble());
            fusedbatchoptions.setPeriodInNS(parcel.readLong());
            fusedbatchoptions.setSourceToUse(parcel.readInt());
            fusedbatchoptions.setFlag(parcel.readInt());
            fusedbatchoptions.setSmallestDisplacementMeters(parcel.readFloat());
            return fusedbatchoptions;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public FusedBatchOptions[] newArray(int i)
        {
            return new FusedBatchOptions[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private volatile int mFlags;
    private volatile double mMaxPowerAllocationInMW;
    private volatile long mPeriodInNS;
    private volatile float mSmallestDisplacementMeters;
    private volatile int mSourcesToUse;

}

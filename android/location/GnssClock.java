// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.Parcel;
import android.os.Parcelable;

public final class GnssClock
    implements Parcelable
{

    static double _2D_set0(GnssClock gnssclock, double d)
    {
        gnssclock.mBiasNanos = d;
        return d;
    }

    static double _2D_set1(GnssClock gnssclock, double d)
    {
        gnssclock.mBiasUncertaintyNanos = d;
        return d;
    }

    static double _2D_set2(GnssClock gnssclock, double d)
    {
        gnssclock.mDriftNanosPerSecond = d;
        return d;
    }

    static double _2D_set3(GnssClock gnssclock, double d)
    {
        gnssclock.mDriftUncertaintyNanosPerSecond = d;
        return d;
    }

    static int _2D_set4(GnssClock gnssclock, int i)
    {
        gnssclock.mFlags = i;
        return i;
    }

    static long _2D_set5(GnssClock gnssclock, long l)
    {
        gnssclock.mFullBiasNanos = l;
        return l;
    }

    static int _2D_set6(GnssClock gnssclock, int i)
    {
        gnssclock.mHardwareClockDiscontinuityCount = i;
        return i;
    }

    static int _2D_set7(GnssClock gnssclock, int i)
    {
        gnssclock.mLeapSecond = i;
        return i;
    }

    static long _2D_set8(GnssClock gnssclock, long l)
    {
        gnssclock.mTimeNanos = l;
        return l;
    }

    static double _2D_set9(GnssClock gnssclock, double d)
    {
        gnssclock.mTimeUncertaintyNanos = d;
        return d;
    }

    public GnssClock()
    {
        initialize();
    }

    private void initialize()
    {
        mFlags = 0;
        resetLeapSecond();
        setTimeNanos(0x8000000000000000L);
        resetTimeUncertaintyNanos();
        resetFullBiasNanos();
        resetBiasNanos();
        resetBiasUncertaintyNanos();
        resetDriftNanosPerSecond();
        resetDriftUncertaintyNanosPerSecond();
        setHardwareClockDiscontinuityCount(0x80000000);
    }

    private boolean isFlagSet(int i)
    {
        boolean flag;
        if((mFlags & i) == i)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void resetFlag(int i)
    {
        mFlags = mFlags & i;
    }

    private void setFlag(int i)
    {
        mFlags = mFlags | i;
    }

    public int describeContents()
    {
        return 0;
    }

    public double getBiasNanos()
    {
        return mBiasNanos;
    }

    public double getBiasUncertaintyNanos()
    {
        return mBiasUncertaintyNanos;
    }

    public double getDriftNanosPerSecond()
    {
        return mDriftNanosPerSecond;
    }

    public double getDriftUncertaintyNanosPerSecond()
    {
        return mDriftUncertaintyNanosPerSecond;
    }

    public long getFullBiasNanos()
    {
        return mFullBiasNanos;
    }

    public int getHardwareClockDiscontinuityCount()
    {
        return mHardwareClockDiscontinuityCount;
    }

    public int getLeapSecond()
    {
        return mLeapSecond;
    }

    public long getTimeNanos()
    {
        return mTimeNanos;
    }

    public double getTimeUncertaintyNanos()
    {
        return mTimeUncertaintyNanos;
    }

    public boolean hasBiasNanos()
    {
        return isFlagSet(8);
    }

    public boolean hasBiasUncertaintyNanos()
    {
        return isFlagSet(16);
    }

    public boolean hasDriftNanosPerSecond()
    {
        return isFlagSet(32);
    }

    public boolean hasDriftUncertaintyNanosPerSecond()
    {
        return isFlagSet(64);
    }

    public boolean hasFullBiasNanos()
    {
        return isFlagSet(4);
    }

    public boolean hasLeapSecond()
    {
        return isFlagSet(1);
    }

    public boolean hasTimeUncertaintyNanos()
    {
        return isFlagSet(2);
    }

    public void reset()
    {
        initialize();
    }

    public void resetBiasNanos()
    {
        resetFlag(8);
        mBiasNanos = (0.0D / 0.0D);
    }

    public void resetBiasUncertaintyNanos()
    {
        resetFlag(16);
        mBiasUncertaintyNanos = (0.0D / 0.0D);
    }

    public void resetDriftNanosPerSecond()
    {
        resetFlag(32);
        mDriftNanosPerSecond = (0.0D / 0.0D);
    }

    public void resetDriftUncertaintyNanosPerSecond()
    {
        resetFlag(64);
        mDriftUncertaintyNanosPerSecond = (0.0D / 0.0D);
    }

    public void resetFullBiasNanos()
    {
        resetFlag(4);
        mFullBiasNanos = 0x8000000000000000L;
    }

    public void resetLeapSecond()
    {
        resetFlag(1);
        mLeapSecond = 0x80000000;
    }

    public void resetTimeUncertaintyNanos()
    {
        resetFlag(2);
        mTimeUncertaintyNanos = (0.0D / 0.0D);
    }

    public void set(GnssClock gnssclock)
    {
        mFlags = gnssclock.mFlags;
        mLeapSecond = gnssclock.mLeapSecond;
        mTimeNanos = gnssclock.mTimeNanos;
        mTimeUncertaintyNanos = gnssclock.mTimeUncertaintyNanos;
        mFullBiasNanos = gnssclock.mFullBiasNanos;
        mBiasNanos = gnssclock.mBiasNanos;
        mBiasUncertaintyNanos = gnssclock.mBiasUncertaintyNanos;
        mDriftNanosPerSecond = gnssclock.mDriftNanosPerSecond;
        mDriftUncertaintyNanosPerSecond = gnssclock.mDriftUncertaintyNanosPerSecond;
        mHardwareClockDiscontinuityCount = gnssclock.mHardwareClockDiscontinuityCount;
    }

    public void setBiasNanos(double d)
    {
        setFlag(8);
        mBiasNanos = d;
    }

    public void setBiasUncertaintyNanos(double d)
    {
        setFlag(16);
        mBiasUncertaintyNanos = d;
    }

    public void setDriftNanosPerSecond(double d)
    {
        setFlag(32);
        mDriftNanosPerSecond = d;
    }

    public void setDriftUncertaintyNanosPerSecond(double d)
    {
        setFlag(64);
        mDriftUncertaintyNanosPerSecond = d;
    }

    public void setFullBiasNanos(long l)
    {
        setFlag(4);
        mFullBiasNanos = l;
    }

    public void setHardwareClockDiscontinuityCount(int i)
    {
        mHardwareClockDiscontinuityCount = i;
    }

    public void setLeapSecond(int i)
    {
        setFlag(1);
        mLeapSecond = i;
    }

    public void setTimeNanos(long l)
    {
        mTimeNanos = l;
    }

    public void setTimeUncertaintyNanos(double d)
    {
        setFlag(2);
        mTimeUncertaintyNanos = d;
    }

    public String toString()
    {
        Object obj = null;
        StringBuilder stringbuilder = new StringBuilder("GnssClock:\n");
        Object obj1;
        long l;
        Double double1;
        if(hasLeapSecond())
            obj1 = Integer.valueOf(mLeapSecond);
        else
            obj1 = null;
        stringbuilder.append(String.format("   %-15s = %s\n", new Object[] {
            "LeapSecond", obj1
        }));
        l = mTimeNanos;
        if(hasTimeUncertaintyNanos())
            obj1 = Double.valueOf(mTimeUncertaintyNanos);
        else
            obj1 = null;
        stringbuilder.append(String.format("   %-15s = %-25s   %-26s = %s\n", new Object[] {
            "TimeNanos", Long.valueOf(l), "TimeUncertaintyNanos", obj1
        }));
        if(hasFullBiasNanos())
            obj1 = Long.valueOf(mFullBiasNanos);
        else
            obj1 = null;
        stringbuilder.append(String.format("   %-15s = %s\n", new Object[] {
            "FullBiasNanos", obj1
        }));
        if(hasBiasNanos())
            obj1 = Double.valueOf(mBiasNanos);
        else
            obj1 = null;
        if(hasBiasUncertaintyNanos())
            double1 = Double.valueOf(mBiasUncertaintyNanos);
        else
            double1 = null;
        stringbuilder.append(String.format("   %-15s = %-25s   %-26s = %s\n", new Object[] {
            "BiasNanos", obj1, "BiasUncertaintyNanos", double1
        }));
        if(hasDriftNanosPerSecond())
            obj1 = Double.valueOf(mDriftNanosPerSecond);
        else
            obj1 = null;
        double1 = obj;
        if(hasDriftUncertaintyNanosPerSecond())
            double1 = Double.valueOf(mDriftUncertaintyNanosPerSecond);
        stringbuilder.append(String.format("   %-15s = %-25s   %-26s = %s\n", new Object[] {
            "DriftNanosPerSecond", obj1, "DriftUncertaintyNanosPerSecond", double1
        }));
        stringbuilder.append(String.format("   %-15s = %s\n", new Object[] {
            "HardwareClockDiscontinuityCount", Integer.valueOf(mHardwareClockDiscontinuityCount)
        }));
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mFlags);
        parcel.writeInt(mLeapSecond);
        parcel.writeLong(mTimeNanos);
        parcel.writeDouble(mTimeUncertaintyNanos);
        parcel.writeLong(mFullBiasNanos);
        parcel.writeDouble(mBiasNanos);
        parcel.writeDouble(mBiasUncertaintyNanos);
        parcel.writeDouble(mDriftNanosPerSecond);
        parcel.writeDouble(mDriftUncertaintyNanosPerSecond);
        parcel.writeInt(mHardwareClockDiscontinuityCount);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public GnssClock createFromParcel(Parcel parcel)
        {
            GnssClock gnssclock = new GnssClock();
            GnssClock._2D_set4(gnssclock, parcel.readInt());
            GnssClock._2D_set7(gnssclock, parcel.readInt());
            GnssClock._2D_set8(gnssclock, parcel.readLong());
            GnssClock._2D_set9(gnssclock, parcel.readDouble());
            GnssClock._2D_set5(gnssclock, parcel.readLong());
            GnssClock._2D_set0(gnssclock, parcel.readDouble());
            GnssClock._2D_set1(gnssclock, parcel.readDouble());
            GnssClock._2D_set2(gnssclock, parcel.readDouble());
            GnssClock._2D_set3(gnssclock, parcel.readDouble());
            GnssClock._2D_set6(gnssclock, parcel.readInt());
            return gnssclock;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public GnssClock[] newArray(int i)
        {
            return new GnssClock[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int HAS_BIAS = 8;
    private static final int HAS_BIAS_UNCERTAINTY = 16;
    private static final int HAS_DRIFT = 32;
    private static final int HAS_DRIFT_UNCERTAINTY = 64;
    private static final int HAS_FULL_BIAS = 4;
    private static final int HAS_LEAP_SECOND = 1;
    private static final int HAS_NO_FLAGS = 0;
    private static final int HAS_TIME_UNCERTAINTY = 2;
    private double mBiasNanos;
    private double mBiasUncertaintyNanos;
    private double mDriftNanosPerSecond;
    private double mDriftUncertaintyNanosPerSecond;
    private int mFlags;
    private long mFullBiasNanos;
    private int mHardwareClockDiscontinuityCount;
    private int mLeapSecond;
    private long mTimeNanos;
    private double mTimeUncertaintyNanos;

}

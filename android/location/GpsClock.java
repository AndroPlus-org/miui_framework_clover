// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.Parcel;
import android.os.Parcelable;

public class GpsClock
    implements Parcelable
{

    static double _2D_set0(GpsClock gpsclock, double d)
    {
        gpsclock.mBiasInNs = d;
        return d;
    }

    static double _2D_set1(GpsClock gpsclock, double d)
    {
        gpsclock.mBiasUncertaintyInNs = d;
        return d;
    }

    static double _2D_set2(GpsClock gpsclock, double d)
    {
        gpsclock.mDriftInNsPerSec = d;
        return d;
    }

    static double _2D_set3(GpsClock gpsclock, double d)
    {
        gpsclock.mDriftUncertaintyInNsPerSec = d;
        return d;
    }

    static short _2D_set4(GpsClock gpsclock, short word0)
    {
        gpsclock.mFlags = word0;
        return word0;
    }

    static long _2D_set5(GpsClock gpsclock, long l)
    {
        gpsclock.mFullBiasInNs = l;
        return l;
    }

    static short _2D_set6(GpsClock gpsclock, short word0)
    {
        gpsclock.mLeapSecond = word0;
        return word0;
    }

    static long _2D_set7(GpsClock gpsclock, long l)
    {
        gpsclock.mTimeInNs = l;
        return l;
    }

    static double _2D_set8(GpsClock gpsclock, double d)
    {
        gpsclock.mTimeUncertaintyInNs = d;
        return d;
    }

    static byte _2D_set9(GpsClock gpsclock, byte byte0)
    {
        gpsclock.mType = byte0;
        return byte0;
    }

    GpsClock()
    {
        initialize();
    }

    private String getTypeString()
    {
        switch(mType)
        {
        default:
            return (new StringBuilder()).append("<Invalid:").append(mType).append(">").toString();

        case 0: // '\0'
            return "Unknown";

        case 2: // '\002'
            return "GpsTime";

        case 1: // '\001'
            return "LocalHwClock";
        }
    }

    private void initialize()
    {
        mFlags = (short)0;
        resetLeapSecond();
        setType((byte)0);
        setTimeInNs(0x8000000000000000L);
        resetTimeUncertaintyInNs();
        resetFullBiasInNs();
        resetBiasInNs();
        resetBiasUncertaintyInNs();
        resetDriftInNsPerSec();
        resetDriftUncertaintyInNsPerSec();
    }

    private boolean isFlagSet(short word0)
    {
        boolean flag;
        if((mFlags & word0) == word0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void resetFlag(short word0)
    {
        mFlags = (short)(mFlags & word0);
    }

    private void setFlag(short word0)
    {
        mFlags = (short)(mFlags | word0);
    }

    public int describeContents()
    {
        return 0;
    }

    public double getBiasInNs()
    {
        return mBiasInNs;
    }

    public double getBiasUncertaintyInNs()
    {
        return mBiasUncertaintyInNs;
    }

    public double getDriftInNsPerSec()
    {
        return mDriftInNsPerSec;
    }

    public double getDriftUncertaintyInNsPerSec()
    {
        return mDriftUncertaintyInNsPerSec;
    }

    public long getFullBiasInNs()
    {
        return mFullBiasInNs;
    }

    public short getLeapSecond()
    {
        return mLeapSecond;
    }

    public long getTimeInNs()
    {
        return mTimeInNs;
    }

    public double getTimeUncertaintyInNs()
    {
        return mTimeUncertaintyInNs;
    }

    public byte getType()
    {
        return mType;
    }

    public boolean hasBiasInNs()
    {
        return isFlagSet((short)8);
    }

    public boolean hasBiasUncertaintyInNs()
    {
        return isFlagSet((short)16);
    }

    public boolean hasDriftInNsPerSec()
    {
        return isFlagSet((short)32);
    }

    public boolean hasDriftUncertaintyInNsPerSec()
    {
        return isFlagSet((short)64);
    }

    public boolean hasFullBiasInNs()
    {
        return isFlagSet((short)4);
    }

    public boolean hasLeapSecond()
    {
        return isFlagSet((short)1);
    }

    public boolean hasTimeUncertaintyInNs()
    {
        return isFlagSet((short)2);
    }

    public void reset()
    {
        initialize();
    }

    public void resetBiasInNs()
    {
        resetFlag((short)8);
        mBiasInNs = (0.0D / 0.0D);
    }

    public void resetBiasUncertaintyInNs()
    {
        resetFlag((short)16);
        mBiasUncertaintyInNs = (0.0D / 0.0D);
    }

    public void resetDriftInNsPerSec()
    {
        resetFlag((short)32);
        mDriftInNsPerSec = (0.0D / 0.0D);
    }

    public void resetDriftUncertaintyInNsPerSec()
    {
        resetFlag((short)64);
        mDriftUncertaintyInNsPerSec = (0.0D / 0.0D);
    }

    public void resetFullBiasInNs()
    {
        resetFlag((short)4);
        mFullBiasInNs = 0x8000000000000000L;
    }

    public void resetLeapSecond()
    {
        resetFlag((short)1);
        mLeapSecond = (short)-32768;
    }

    public void resetTimeUncertaintyInNs()
    {
        resetFlag((short)2);
        mTimeUncertaintyInNs = (0.0D / 0.0D);
    }

    public void set(GpsClock gpsclock)
    {
        mFlags = gpsclock.mFlags;
        mLeapSecond = gpsclock.mLeapSecond;
        mType = gpsclock.mType;
        mTimeInNs = gpsclock.mTimeInNs;
        mTimeUncertaintyInNs = gpsclock.mTimeUncertaintyInNs;
        mFullBiasInNs = gpsclock.mFullBiasInNs;
        mBiasInNs = gpsclock.mBiasInNs;
        mBiasUncertaintyInNs = gpsclock.mBiasUncertaintyInNs;
        mDriftInNsPerSec = gpsclock.mDriftInNsPerSec;
        mDriftUncertaintyInNsPerSec = gpsclock.mDriftUncertaintyInNsPerSec;
    }

    public void setBiasInNs(double d)
    {
        setFlag((short)8);
        mBiasInNs = d;
    }

    public void setBiasUncertaintyInNs(double d)
    {
        setFlag((short)16);
        mBiasUncertaintyInNs = d;
    }

    public void setDriftInNsPerSec(double d)
    {
        setFlag((short)32);
        mDriftInNsPerSec = d;
    }

    public void setDriftUncertaintyInNsPerSec(double d)
    {
        setFlag((short)64);
        mDriftUncertaintyInNsPerSec = d;
    }

    public void setFullBiasInNs(long l)
    {
        setFlag((short)4);
        mFullBiasInNs = l;
    }

    public void setLeapSecond(short word0)
    {
        setFlag((short)1);
        mLeapSecond = word0;
    }

    public void setTimeInNs(long l)
    {
        mTimeInNs = l;
    }

    public void setTimeUncertaintyInNs(double d)
    {
        setFlag((short)2);
        mTimeUncertaintyInNs = d;
    }

    public void setType(byte byte0)
    {
        mType = byte0;
    }

    public String toString()
    {
        Object obj = null;
        StringBuilder stringbuilder = new StringBuilder("GpsClock:\n");
        stringbuilder.append(String.format("   %-15s = %s\n", new Object[] {
            "Type", getTypeString()
        }));
        Object obj1;
        long l;
        Double double1;
        if(hasLeapSecond())
            obj1 = Short.valueOf(mLeapSecond);
        else
            obj1 = null;
        stringbuilder.append(String.format("   %-15s = %s\n", new Object[] {
            "LeapSecond", obj1
        }));
        l = mTimeInNs;
        if(hasTimeUncertaintyInNs())
            obj1 = Double.valueOf(mTimeUncertaintyInNs);
        else
            obj1 = null;
        stringbuilder.append(String.format("   %-15s = %-25s   %-26s = %s\n", new Object[] {
            "TimeInNs", Long.valueOf(l), "TimeUncertaintyInNs", obj1
        }));
        if(hasFullBiasInNs())
            obj1 = Long.valueOf(mFullBiasInNs);
        else
            obj1 = null;
        stringbuilder.append(String.format("   %-15s = %s\n", new Object[] {
            "FullBiasInNs", obj1
        }));
        if(hasBiasInNs())
            obj1 = Double.valueOf(mBiasInNs);
        else
            obj1 = null;
        if(hasBiasUncertaintyInNs())
            double1 = Double.valueOf(mBiasUncertaintyInNs);
        else
            double1 = null;
        stringbuilder.append(String.format("   %-15s = %-25s   %-26s = %s\n", new Object[] {
            "BiasInNs", obj1, "BiasUncertaintyInNs", double1
        }));
        if(hasDriftInNsPerSec())
            obj1 = Double.valueOf(mDriftInNsPerSec);
        else
            obj1 = null;
        double1 = obj;
        if(hasDriftUncertaintyInNsPerSec())
            double1 = Double.valueOf(mDriftUncertaintyInNsPerSec);
        stringbuilder.append(String.format("   %-15s = %-25s   %-26s = %s\n", new Object[] {
            "DriftInNsPerSec", obj1, "DriftUncertaintyInNsPerSec", double1
        }));
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mFlags);
        parcel.writeInt(mLeapSecond);
        parcel.writeByte(mType);
        parcel.writeLong(mTimeInNs);
        parcel.writeDouble(mTimeUncertaintyInNs);
        parcel.writeLong(mFullBiasInNs);
        parcel.writeDouble(mBiasInNs);
        parcel.writeDouble(mBiasUncertaintyInNs);
        parcel.writeDouble(mDriftInNsPerSec);
        parcel.writeDouble(mDriftUncertaintyInNsPerSec);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public GpsClock createFromParcel(Parcel parcel)
        {
            GpsClock gpsclock = new GpsClock();
            GpsClock._2D_set4(gpsclock, (short)parcel.readInt());
            GpsClock._2D_set6(gpsclock, (short)parcel.readInt());
            GpsClock._2D_set9(gpsclock, parcel.readByte());
            GpsClock._2D_set7(gpsclock, parcel.readLong());
            GpsClock._2D_set8(gpsclock, parcel.readDouble());
            GpsClock._2D_set5(gpsclock, parcel.readLong());
            GpsClock._2D_set0(gpsclock, parcel.readDouble());
            GpsClock._2D_set1(gpsclock, parcel.readDouble());
            GpsClock._2D_set2(gpsclock, parcel.readDouble());
            GpsClock._2D_set3(gpsclock, parcel.readDouble());
            return gpsclock;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public GpsClock[] newArray(int i)
        {
            return new GpsClock[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final short HAS_BIAS = 8;
    private static final short HAS_BIAS_UNCERTAINTY = 16;
    private static final short HAS_DRIFT = 32;
    private static final short HAS_DRIFT_UNCERTAINTY = 64;
    private static final short HAS_FULL_BIAS = 4;
    private static final short HAS_LEAP_SECOND = 1;
    private static final short HAS_NO_FLAGS = 0;
    private static final short HAS_TIME_UNCERTAINTY = 2;
    public static final byte TYPE_GPS_TIME = 2;
    public static final byte TYPE_LOCAL_HW_TIME = 1;
    public static final byte TYPE_UNKNOWN = 0;
    private double mBiasInNs;
    private double mBiasUncertaintyInNs;
    private double mDriftInNsPerSec;
    private double mDriftUncertaintyInNsPerSec;
    private short mFlags;
    private long mFullBiasInNs;
    private short mLeapSecond;
    private long mTimeInNs;
    private double mTimeUncertaintyInNs;
    private byte mType;

}

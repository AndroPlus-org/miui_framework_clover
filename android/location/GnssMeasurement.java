// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.Parcel;
import android.os.Parcelable;

public final class GnssMeasurement
    implements Parcelable
{

    static double _2D_set0(GnssMeasurement gnssmeasurement, double d)
    {
        gnssmeasurement.mAccumulatedDeltaRangeMeters = d;
        return d;
    }

    static int _2D_set1(GnssMeasurement gnssmeasurement, int i)
    {
        gnssmeasurement.mAccumulatedDeltaRangeState = i;
        return i;
    }

    static int _2D_set10(GnssMeasurement gnssmeasurement, int i)
    {
        gnssmeasurement.mFlags = i;
        return i;
    }

    static int _2D_set11(GnssMeasurement gnssmeasurement, int i)
    {
        gnssmeasurement.mMultipathIndicator = i;
        return i;
    }

    static double _2D_set12(GnssMeasurement gnssmeasurement, double d)
    {
        gnssmeasurement.mPseudorangeRateMetersPerSecond = d;
        return d;
    }

    static double _2D_set13(GnssMeasurement gnssmeasurement, double d)
    {
        gnssmeasurement.mPseudorangeRateUncertaintyMetersPerSecond = d;
        return d;
    }

    static long _2D_set14(GnssMeasurement gnssmeasurement, long l)
    {
        gnssmeasurement.mReceivedSvTimeNanos = l;
        return l;
    }

    static long _2D_set15(GnssMeasurement gnssmeasurement, long l)
    {
        gnssmeasurement.mReceivedSvTimeUncertaintyNanos = l;
        return l;
    }

    static double _2D_set16(GnssMeasurement gnssmeasurement, double d)
    {
        gnssmeasurement.mSnrInDb = d;
        return d;
    }

    static int _2D_set17(GnssMeasurement gnssmeasurement, int i)
    {
        gnssmeasurement.mState = i;
        return i;
    }

    static int _2D_set18(GnssMeasurement gnssmeasurement, int i)
    {
        gnssmeasurement.mSvid = i;
        return i;
    }

    static double _2D_set19(GnssMeasurement gnssmeasurement, double d)
    {
        gnssmeasurement.mTimeOffsetNanos = d;
        return d;
    }

    static double _2D_set2(GnssMeasurement gnssmeasurement, double d)
    {
        gnssmeasurement.mAccumulatedDeltaRangeUncertaintyMeters = d;
        return d;
    }

    static double _2D_set3(GnssMeasurement gnssmeasurement, double d)
    {
        gnssmeasurement.mAutomaticGainControlLevelInDb = d;
        return d;
    }

    static long _2D_set4(GnssMeasurement gnssmeasurement, long l)
    {
        gnssmeasurement.mCarrierCycles = l;
        return l;
    }

    static float _2D_set5(GnssMeasurement gnssmeasurement, float f)
    {
        gnssmeasurement.mCarrierFrequencyHz = f;
        return f;
    }

    static double _2D_set6(GnssMeasurement gnssmeasurement, double d)
    {
        gnssmeasurement.mCarrierPhase = d;
        return d;
    }

    static double _2D_set7(GnssMeasurement gnssmeasurement, double d)
    {
        gnssmeasurement.mCarrierPhaseUncertainty = d;
        return d;
    }

    static double _2D_set8(GnssMeasurement gnssmeasurement, double d)
    {
        gnssmeasurement.mCn0DbHz = d;
        return d;
    }

    static int _2D_set9(GnssMeasurement gnssmeasurement, int i)
    {
        gnssmeasurement.mConstellationType = i;
        return i;
    }

    public GnssMeasurement()
    {
        initialize();
    }

    private String getAccumulatedDeltaRangeStateString()
    {
        if(mAccumulatedDeltaRangeState == 0)
            return "Unknown";
        StringBuilder stringbuilder = new StringBuilder();
        if((mAccumulatedDeltaRangeState & 1) == 1)
            stringbuilder.append("Valid|");
        if((mAccumulatedDeltaRangeState & 2) == 2)
            stringbuilder.append("Reset|");
        if((mAccumulatedDeltaRangeState & 4) == 4)
            stringbuilder.append("CycleSlip|");
        int i = mAccumulatedDeltaRangeState & -8;
        if(i > 0)
        {
            stringbuilder.append("Other(");
            stringbuilder.append(Integer.toBinaryString(i));
            stringbuilder.append(")|");
        }
        stringbuilder.deleteCharAt(stringbuilder.length() - 1);
        return stringbuilder.toString();
    }

    private String getMultipathIndicatorString()
    {
        switch(mMultipathIndicator)
        {
        default:
            return (new StringBuilder()).append("<Invalid:").append(mMultipathIndicator).append(">").toString();

        case 0: // '\0'
            return "Unknown";

        case 1: // '\001'
            return "Detected";

        case 2: // '\002'
            return "NotDetected";
        }
    }

    private String getStateString()
    {
        if(mState == 0)
            return "Unknown";
        StringBuilder stringbuilder = new StringBuilder();
        if((mState & 1) != 0)
            stringbuilder.append("CodeLock|");
        if((mState & 2) != 0)
            stringbuilder.append("BitSync|");
        if((mState & 4) != 0)
            stringbuilder.append("SubframeSync|");
        if((mState & 8) != 0)
            stringbuilder.append("TowDecoded|");
        if((mState & 0x4000) != 0)
            stringbuilder.append("TowKnown|");
        if((mState & 0x10) != 0)
            stringbuilder.append("MsecAmbiguous|");
        if((mState & 0x20) != 0)
            stringbuilder.append("SymbolSync|");
        if((mState & 0x40) != 0)
            stringbuilder.append("GloStringSync|");
        if((mState & 0x80) != 0)
            stringbuilder.append("GloTodDecoded|");
        if((mState & 0x8000) != 0)
            stringbuilder.append("GloTodKnown|");
        if((mState & 0x100) != 0)
            stringbuilder.append("BdsD2BitSync|");
        if((mState & 0x200) != 0)
            stringbuilder.append("BdsD2SubframeSync|");
        if((mState & 0x400) != 0)
            stringbuilder.append("GalE1bcCodeLock|");
        if((mState & 0x800) != 0)
            stringbuilder.append("E1c2ndCodeLock|");
        if((mState & 0x1000) != 0)
            stringbuilder.append("GalE1bPageSync|");
        if((mState & 0x2000) != 0)
            stringbuilder.append("SbasSync|");
        int i = mState & 0xffffc000;
        if(i > 0)
        {
            stringbuilder.append("Other(");
            stringbuilder.append(Integer.toBinaryString(i));
            stringbuilder.append(")|");
        }
        stringbuilder.setLength(stringbuilder.length() - 1);
        return stringbuilder.toString();
    }

    private void initialize()
    {
        mFlags = 0;
        setSvid(0);
        setTimeOffsetNanos(-9.2233720368547758E+018D);
        setState(0);
        setReceivedSvTimeNanos(0x8000000000000000L);
        setReceivedSvTimeUncertaintyNanos(0x7fffffffffffffffL);
        setCn0DbHz(4.9406564584124654E-324D);
        setPseudorangeRateMetersPerSecond(4.9406564584124654E-324D);
        setPseudorangeRateUncertaintyMetersPerSecond(4.9406564584124654E-324D);
        setAccumulatedDeltaRangeState(0);
        setAccumulatedDeltaRangeMeters(4.9406564584124654E-324D);
        setAccumulatedDeltaRangeUncertaintyMeters(4.9406564584124654E-324D);
        resetCarrierFrequencyHz();
        resetCarrierCycles();
        resetCarrierPhase();
        resetCarrierPhaseUncertainty();
        setMultipathIndicator(0);
        resetSnrInDb();
        resetAutomaticGainControlLevel();
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

    public double getAccumulatedDeltaRangeMeters()
    {
        return mAccumulatedDeltaRangeMeters;
    }

    public int getAccumulatedDeltaRangeState()
    {
        return mAccumulatedDeltaRangeState;
    }

    public double getAccumulatedDeltaRangeUncertaintyMeters()
    {
        return mAccumulatedDeltaRangeUncertaintyMeters;
    }

    public double getAutomaticGainControlLevelDb()
    {
        return mAutomaticGainControlLevelInDb;
    }

    public long getCarrierCycles()
    {
        return mCarrierCycles;
    }

    public float getCarrierFrequencyHz()
    {
        return mCarrierFrequencyHz;
    }

    public double getCarrierPhase()
    {
        return mCarrierPhase;
    }

    public double getCarrierPhaseUncertainty()
    {
        return mCarrierPhaseUncertainty;
    }

    public double getCn0DbHz()
    {
        return mCn0DbHz;
    }

    public int getConstellationType()
    {
        return mConstellationType;
    }

    public int getMultipathIndicator()
    {
        return mMultipathIndicator;
    }

    public double getPseudorangeRateMetersPerSecond()
    {
        return mPseudorangeRateMetersPerSecond;
    }

    public double getPseudorangeRateUncertaintyMetersPerSecond()
    {
        return mPseudorangeRateUncertaintyMetersPerSecond;
    }

    public long getReceivedSvTimeNanos()
    {
        return mReceivedSvTimeNanos;
    }

    public long getReceivedSvTimeUncertaintyNanos()
    {
        return mReceivedSvTimeUncertaintyNanos;
    }

    public double getSnrInDb()
    {
        return mSnrInDb;
    }

    public int getState()
    {
        return mState;
    }

    public int getSvid()
    {
        return mSvid;
    }

    public double getTimeOffsetNanos()
    {
        return mTimeOffsetNanos;
    }

    public boolean hasAutomaticGainControlLevelDb()
    {
        return isFlagSet(8192);
    }

    public boolean hasCarrierCycles()
    {
        return isFlagSet(1024);
    }

    public boolean hasCarrierFrequencyHz()
    {
        return isFlagSet(512);
    }

    public boolean hasCarrierPhase()
    {
        return isFlagSet(2048);
    }

    public boolean hasCarrierPhaseUncertainty()
    {
        return isFlagSet(4096);
    }

    public boolean hasSnrInDb()
    {
        return isFlagSet(1);
    }

    public void reset()
    {
        initialize();
    }

    public void resetAutomaticGainControlLevel()
    {
        resetFlag(8192);
        mAutomaticGainControlLevelInDb = (0.0D / 0.0D);
    }

    public void resetCarrierCycles()
    {
        resetFlag(1024);
        mCarrierCycles = 0x8000000000000000L;
    }

    public void resetCarrierFrequencyHz()
    {
        resetFlag(512);
        mCarrierFrequencyHz = (0.0F / 0.0F);
    }

    public void resetCarrierPhase()
    {
        resetFlag(2048);
        mCarrierPhase = (0.0D / 0.0D);
    }

    public void resetCarrierPhaseUncertainty()
    {
        resetFlag(4096);
        mCarrierPhaseUncertainty = (0.0D / 0.0D);
    }

    public void resetSnrInDb()
    {
        resetFlag(1);
        mSnrInDb = (0.0D / 0.0D);
    }

    public void set(GnssMeasurement gnssmeasurement)
    {
        mFlags = gnssmeasurement.mFlags;
        mSvid = gnssmeasurement.mSvid;
        mConstellationType = gnssmeasurement.mConstellationType;
        mTimeOffsetNanos = gnssmeasurement.mTimeOffsetNanos;
        mState = gnssmeasurement.mState;
        mReceivedSvTimeNanos = gnssmeasurement.mReceivedSvTimeNanos;
        mReceivedSvTimeUncertaintyNanos = gnssmeasurement.mReceivedSvTimeUncertaintyNanos;
        mCn0DbHz = gnssmeasurement.mCn0DbHz;
        mPseudorangeRateMetersPerSecond = gnssmeasurement.mPseudorangeRateMetersPerSecond;
        mPseudorangeRateUncertaintyMetersPerSecond = gnssmeasurement.mPseudorangeRateUncertaintyMetersPerSecond;
        mAccumulatedDeltaRangeState = gnssmeasurement.mAccumulatedDeltaRangeState;
        mAccumulatedDeltaRangeMeters = gnssmeasurement.mAccumulatedDeltaRangeMeters;
        mAccumulatedDeltaRangeUncertaintyMeters = gnssmeasurement.mAccumulatedDeltaRangeUncertaintyMeters;
        mCarrierFrequencyHz = gnssmeasurement.mCarrierFrequencyHz;
        mCarrierCycles = gnssmeasurement.mCarrierCycles;
        mCarrierPhase = gnssmeasurement.mCarrierPhase;
        mCarrierPhaseUncertainty = gnssmeasurement.mCarrierPhaseUncertainty;
        mMultipathIndicator = gnssmeasurement.mMultipathIndicator;
        mSnrInDb = gnssmeasurement.mSnrInDb;
        mAutomaticGainControlLevelInDb = gnssmeasurement.mAutomaticGainControlLevelInDb;
    }

    public void setAccumulatedDeltaRangeMeters(double d)
    {
        mAccumulatedDeltaRangeMeters = d;
    }

    public void setAccumulatedDeltaRangeState(int i)
    {
        mAccumulatedDeltaRangeState = i;
    }

    public void setAccumulatedDeltaRangeUncertaintyMeters(double d)
    {
        mAccumulatedDeltaRangeUncertaintyMeters = d;
    }

    public void setAutomaticGainControlLevelInDb(double d)
    {
        setFlag(8192);
        mAutomaticGainControlLevelInDb = d;
    }

    public void setCarrierCycles(long l)
    {
        setFlag(1024);
        mCarrierCycles = l;
    }

    public void setCarrierFrequencyHz(float f)
    {
        setFlag(512);
        mCarrierFrequencyHz = f;
    }

    public void setCarrierPhase(double d)
    {
        setFlag(2048);
        mCarrierPhase = d;
    }

    public void setCarrierPhaseUncertainty(double d)
    {
        setFlag(4096);
        mCarrierPhaseUncertainty = d;
    }

    public void setCn0DbHz(double d)
    {
        mCn0DbHz = d;
    }

    public void setConstellationType(int i)
    {
        mConstellationType = i;
    }

    public void setMultipathIndicator(int i)
    {
        mMultipathIndicator = i;
    }

    public void setPseudorangeRateMetersPerSecond(double d)
    {
        mPseudorangeRateMetersPerSecond = d;
    }

    public void setPseudorangeRateUncertaintyMetersPerSecond(double d)
    {
        mPseudorangeRateUncertaintyMetersPerSecond = d;
    }

    public void setReceivedSvTimeNanos(long l)
    {
        mReceivedSvTimeNanos = l;
    }

    public void setReceivedSvTimeUncertaintyNanos(long l)
    {
        mReceivedSvTimeUncertaintyNanos = l;
    }

    public void setSnrInDb(double d)
    {
        setFlag(1);
        mSnrInDb = d;
    }

    public void setState(int i)
    {
        mState = i;
    }

    public void setSvid(int i)
    {
        mSvid = i;
    }

    public void setTimeOffsetNanos(double d)
    {
        mTimeOffsetNanos = d;
    }

    public String toString()
    {
        Object obj = null;
        StringBuilder stringbuilder = new StringBuilder("GnssMeasurement:\n");
        stringbuilder.append(String.format("   %-29s = %s\n", new Object[] {
            "Svid", Integer.valueOf(mSvid)
        }));
        stringbuilder.append(String.format("   %-29s = %s\n", new Object[] {
            "ConstellationType", Integer.valueOf(mConstellationType)
        }));
        stringbuilder.append(String.format("   %-29s = %s\n", new Object[] {
            "TimeOffsetNanos", Double.valueOf(mTimeOffsetNanos)
        }));
        stringbuilder.append(String.format("   %-29s = %s\n", new Object[] {
            "State", getStateString()
        }));
        stringbuilder.append(String.format("   %-29s = %-25s   %-40s = %s\n", new Object[] {
            "ReceivedSvTimeNanos", Long.valueOf(mReceivedSvTimeNanos), "ReceivedSvTimeUncertaintyNanos", Long.valueOf(mReceivedSvTimeUncertaintyNanos)
        }));
        stringbuilder.append(String.format("   %-29s = %s\n", new Object[] {
            "Cn0DbHz", Double.valueOf(mCn0DbHz)
        }));
        stringbuilder.append(String.format("   %-29s = %-25s   %-40s = %s\n", new Object[] {
            "PseudorangeRateMetersPerSecond", Double.valueOf(mPseudorangeRateMetersPerSecond), "PseudorangeRateUncertaintyMetersPerSecond", Double.valueOf(mPseudorangeRateUncertaintyMetersPerSecond)
        }));
        stringbuilder.append(String.format("   %-29s = %s\n", new Object[] {
            "AccumulatedDeltaRangeState", getAccumulatedDeltaRangeStateString()
        }));
        stringbuilder.append(String.format("   %-29s = %-25s   %-40s = %s\n", new Object[] {
            "AccumulatedDeltaRangeMeters", Double.valueOf(mAccumulatedDeltaRangeMeters), "AccumulatedDeltaRangeUncertaintyMeters", Double.valueOf(mAccumulatedDeltaRangeUncertaintyMeters)
        }));
        Object obj1;
        Double double1;
        if(hasCarrierFrequencyHz())
            obj1 = Float.valueOf(mCarrierFrequencyHz);
        else
            obj1 = null;
        stringbuilder.append(String.format("   %-29s = %s\n", new Object[] {
            "CarrierFrequencyHz", obj1
        }));
        if(hasCarrierCycles())
            obj1 = Long.valueOf(mCarrierCycles);
        else
            obj1 = null;
        stringbuilder.append(String.format("   %-29s = %s\n", new Object[] {
            "CarrierCycles", obj1
        }));
        if(hasCarrierPhase())
            obj1 = Double.valueOf(mCarrierPhase);
        else
            obj1 = null;
        if(hasCarrierPhaseUncertainty())
            double1 = Double.valueOf(mCarrierPhaseUncertainty);
        else
            double1 = null;
        stringbuilder.append(String.format("   %-29s = %-25s   %-40s = %s\n", new Object[] {
            "CarrierPhase", obj1, "CarrierPhaseUncertainty", double1
        }));
        stringbuilder.append(String.format("   %-29s = %s\n", new Object[] {
            "MultipathIndicator", getMultipathIndicatorString()
        }));
        if(hasSnrInDb())
            obj1 = Double.valueOf(mSnrInDb);
        else
            obj1 = null;
        stringbuilder.append(String.format("   %-29s = %s\n", new Object[] {
            "SnrInDb", obj1
        }));
        obj1 = obj;
        if(hasAutomaticGainControlLevelDb())
            obj1 = Double.valueOf(mAutomaticGainControlLevelInDb);
        stringbuilder.append(String.format("   %-29s = %s\n", new Object[] {
            "AgcLevelDb", obj1
        }));
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mFlags);
        parcel.writeInt(mSvid);
        parcel.writeInt(mConstellationType);
        parcel.writeDouble(mTimeOffsetNanos);
        parcel.writeInt(mState);
        parcel.writeLong(mReceivedSvTimeNanos);
        parcel.writeLong(mReceivedSvTimeUncertaintyNanos);
        parcel.writeDouble(mCn0DbHz);
        parcel.writeDouble(mPseudorangeRateMetersPerSecond);
        parcel.writeDouble(mPseudorangeRateUncertaintyMetersPerSecond);
        parcel.writeInt(mAccumulatedDeltaRangeState);
        parcel.writeDouble(mAccumulatedDeltaRangeMeters);
        parcel.writeDouble(mAccumulatedDeltaRangeUncertaintyMeters);
        parcel.writeFloat(mCarrierFrequencyHz);
        parcel.writeLong(mCarrierCycles);
        parcel.writeDouble(mCarrierPhase);
        parcel.writeDouble(mCarrierPhaseUncertainty);
        parcel.writeInt(mMultipathIndicator);
        parcel.writeDouble(mSnrInDb);
        parcel.writeDouble(mAutomaticGainControlLevelInDb);
    }

    private static final int ADR_ALL = 7;
    public static final int ADR_STATE_CYCLE_SLIP = 4;
    public static final int ADR_STATE_RESET = 2;
    public static final int ADR_STATE_UNKNOWN = 0;
    public static final int ADR_STATE_VALID = 1;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public GnssMeasurement createFromParcel(Parcel parcel)
        {
            GnssMeasurement gnssmeasurement = new GnssMeasurement();
            GnssMeasurement._2D_set10(gnssmeasurement, parcel.readInt());
            GnssMeasurement._2D_set18(gnssmeasurement, parcel.readInt());
            GnssMeasurement._2D_set9(gnssmeasurement, parcel.readInt());
            GnssMeasurement._2D_set19(gnssmeasurement, parcel.readDouble());
            GnssMeasurement._2D_set17(gnssmeasurement, parcel.readInt());
            GnssMeasurement._2D_set14(gnssmeasurement, parcel.readLong());
            GnssMeasurement._2D_set15(gnssmeasurement, parcel.readLong());
            GnssMeasurement._2D_set8(gnssmeasurement, parcel.readDouble());
            GnssMeasurement._2D_set12(gnssmeasurement, parcel.readDouble());
            GnssMeasurement._2D_set13(gnssmeasurement, parcel.readDouble());
            GnssMeasurement._2D_set1(gnssmeasurement, parcel.readInt());
            GnssMeasurement._2D_set0(gnssmeasurement, parcel.readDouble());
            GnssMeasurement._2D_set2(gnssmeasurement, parcel.readDouble());
            GnssMeasurement._2D_set5(gnssmeasurement, parcel.readFloat());
            GnssMeasurement._2D_set4(gnssmeasurement, parcel.readLong());
            GnssMeasurement._2D_set6(gnssmeasurement, parcel.readDouble());
            GnssMeasurement._2D_set7(gnssmeasurement, parcel.readDouble());
            GnssMeasurement._2D_set11(gnssmeasurement, parcel.readInt());
            GnssMeasurement._2D_set16(gnssmeasurement, parcel.readDouble());
            GnssMeasurement._2D_set3(gnssmeasurement, parcel.readDouble());
            return gnssmeasurement;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public GnssMeasurement[] newArray(int i)
        {
            return new GnssMeasurement[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int HAS_AUTOMATIC_GAIN_CONTROL = 8192;
    private static final int HAS_CARRIER_CYCLES = 1024;
    private static final int HAS_CARRIER_FREQUENCY = 512;
    private static final int HAS_CARRIER_PHASE = 2048;
    private static final int HAS_CARRIER_PHASE_UNCERTAINTY = 4096;
    private static final int HAS_NO_FLAGS = 0;
    private static final int HAS_SNR = 1;
    public static final int MULTIPATH_INDICATOR_DETECTED = 1;
    public static final int MULTIPATH_INDICATOR_NOT_DETECTED = 2;
    public static final int MULTIPATH_INDICATOR_UNKNOWN = 0;
    private static final int STATE_ALL = 16383;
    public static final int STATE_BDS_D2_BIT_SYNC = 256;
    public static final int STATE_BDS_D2_SUBFRAME_SYNC = 512;
    public static final int STATE_BIT_SYNC = 2;
    public static final int STATE_CODE_LOCK = 1;
    public static final int STATE_GAL_E1BC_CODE_LOCK = 1024;
    public static final int STATE_GAL_E1B_PAGE_SYNC = 4096;
    public static final int STATE_GAL_E1C_2ND_CODE_LOCK = 2048;
    public static final int STATE_GLO_STRING_SYNC = 64;
    public static final int STATE_GLO_TOD_DECODED = 128;
    public static final int STATE_GLO_TOD_KNOWN = 32768;
    public static final int STATE_MSEC_AMBIGUOUS = 16;
    public static final int STATE_SBAS_SYNC = 8192;
    public static final int STATE_SUBFRAME_SYNC = 4;
    public static final int STATE_SYMBOL_SYNC = 32;
    public static final int STATE_TOW_DECODED = 8;
    public static final int STATE_TOW_KNOWN = 16384;
    public static final int STATE_UNKNOWN = 0;
    private double mAccumulatedDeltaRangeMeters;
    private int mAccumulatedDeltaRangeState;
    private double mAccumulatedDeltaRangeUncertaintyMeters;
    private double mAutomaticGainControlLevelInDb;
    private long mCarrierCycles;
    private float mCarrierFrequencyHz;
    private double mCarrierPhase;
    private double mCarrierPhaseUncertainty;
    private double mCn0DbHz;
    private int mConstellationType;
    private int mFlags;
    private int mMultipathIndicator;
    private double mPseudorangeRateMetersPerSecond;
    private double mPseudorangeRateUncertaintyMetersPerSecond;
    private long mReceivedSvTimeNanos;
    private long mReceivedSvTimeUncertaintyNanos;
    private double mSnrInDb;
    private int mState;
    private int mSvid;
    private double mTimeOffsetNanos;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.Parcel;
import android.os.Parcelable;

public class GpsMeasurement
    implements Parcelable
{

    static double _2D_set0(GpsMeasurement gpsmeasurement, double d)
    {
        gpsmeasurement.mAccumulatedDeltaRangeInMeters = d;
        return d;
    }

    static short _2D_set1(GpsMeasurement gpsmeasurement, short word0)
    {
        gpsmeasurement.mAccumulatedDeltaRangeState = word0;
        return word0;
    }

    static double _2D_set10(GpsMeasurement gpsmeasurement, double d)
    {
        gpsmeasurement.mCn0InDbHz = d;
        return d;
    }

    static double _2D_set11(GpsMeasurement gpsmeasurement, double d)
    {
        gpsmeasurement.mCodePhaseInChips = d;
        return d;
    }

    static double _2D_set12(GpsMeasurement gpsmeasurement, double d)
    {
        gpsmeasurement.mCodePhaseUncertaintyInChips = d;
        return d;
    }

    static double _2D_set13(GpsMeasurement gpsmeasurement, double d)
    {
        gpsmeasurement.mDopplerShiftInHz = d;
        return d;
    }

    static double _2D_set14(GpsMeasurement gpsmeasurement, double d)
    {
        gpsmeasurement.mDopplerShiftUncertaintyInHz = d;
        return d;
    }

    static double _2D_set15(GpsMeasurement gpsmeasurement, double d)
    {
        gpsmeasurement.mElevationInDeg = d;
        return d;
    }

    static double _2D_set16(GpsMeasurement gpsmeasurement, double d)
    {
        gpsmeasurement.mElevationUncertaintyInDeg = d;
        return d;
    }

    static int _2D_set17(GpsMeasurement gpsmeasurement, int i)
    {
        gpsmeasurement.mFlags = i;
        return i;
    }

    static byte _2D_set18(GpsMeasurement gpsmeasurement, byte byte0)
    {
        gpsmeasurement.mLossOfLock = byte0;
        return byte0;
    }

    static byte _2D_set19(GpsMeasurement gpsmeasurement, byte byte0)
    {
        gpsmeasurement.mMultipathIndicator = byte0;
        return byte0;
    }

    static double _2D_set2(GpsMeasurement gpsmeasurement, double d)
    {
        gpsmeasurement.mAccumulatedDeltaRangeUncertaintyInMeters = d;
        return d;
    }

    static byte _2D_set20(GpsMeasurement gpsmeasurement, byte byte0)
    {
        gpsmeasurement.mPrn = byte0;
        return byte0;
    }

    static double _2D_set21(GpsMeasurement gpsmeasurement, double d)
    {
        gpsmeasurement.mPseudorangeInMeters = d;
        return d;
    }

    static double _2D_set22(GpsMeasurement gpsmeasurement, double d)
    {
        gpsmeasurement.mPseudorangeRateInMetersPerSec = d;
        return d;
    }

    static double _2D_set23(GpsMeasurement gpsmeasurement, double d)
    {
        gpsmeasurement.mPseudorangeRateUncertaintyInMetersPerSec = d;
        return d;
    }

    static double _2D_set24(GpsMeasurement gpsmeasurement, double d)
    {
        gpsmeasurement.mPseudorangeUncertaintyInMeters = d;
        return d;
    }

    static long _2D_set25(GpsMeasurement gpsmeasurement, long l)
    {
        gpsmeasurement.mReceivedGpsTowInNs = l;
        return l;
    }

    static long _2D_set26(GpsMeasurement gpsmeasurement, long l)
    {
        gpsmeasurement.mReceivedGpsTowUncertaintyInNs = l;
        return l;
    }

    static double _2D_set27(GpsMeasurement gpsmeasurement, double d)
    {
        gpsmeasurement.mSnrInDb = d;
        return d;
    }

    static short _2D_set28(GpsMeasurement gpsmeasurement, short word0)
    {
        gpsmeasurement.mState = word0;
        return word0;
    }

    static short _2D_set29(GpsMeasurement gpsmeasurement, short word0)
    {
        gpsmeasurement.mTimeFromLastBitInMs = word0;
        return word0;
    }

    static double _2D_set3(GpsMeasurement gpsmeasurement, double d)
    {
        gpsmeasurement.mAzimuthInDeg = d;
        return d;
    }

    static double _2D_set30(GpsMeasurement gpsmeasurement, double d)
    {
        gpsmeasurement.mTimeOffsetInNs = d;
        return d;
    }

    static boolean _2D_set31(GpsMeasurement gpsmeasurement, boolean flag)
    {
        gpsmeasurement.mUsedInFix = flag;
        return flag;
    }

    static double _2D_set4(GpsMeasurement gpsmeasurement, double d)
    {
        gpsmeasurement.mAzimuthUncertaintyInDeg = d;
        return d;
    }

    static int _2D_set5(GpsMeasurement gpsmeasurement, int i)
    {
        gpsmeasurement.mBitNumber = i;
        return i;
    }

    static long _2D_set6(GpsMeasurement gpsmeasurement, long l)
    {
        gpsmeasurement.mCarrierCycles = l;
        return l;
    }

    static float _2D_set7(GpsMeasurement gpsmeasurement, float f)
    {
        gpsmeasurement.mCarrierFrequencyInHz = f;
        return f;
    }

    static double _2D_set8(GpsMeasurement gpsmeasurement, double d)
    {
        gpsmeasurement.mCarrierPhase = d;
        return d;
    }

    static double _2D_set9(GpsMeasurement gpsmeasurement, double d)
    {
        gpsmeasurement.mCarrierPhaseUncertainty = d;
        return d;
    }

    GpsMeasurement()
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

    private String getLossOfLockString()
    {
        switch(mLossOfLock)
        {
        default:
            return (new StringBuilder()).append("<Invalid:").append(mLossOfLock).append(">").toString();

        case 0: // '\0'
            return "Unknown";

        case 1: // '\001'
            return "Ok";

        case 2: // '\002'
            return "CycleSlip";
        }
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
            return "NotUsed";
        }
    }

    private String getStateString()
    {
        if(mState == 0)
            return "Unknown";
        StringBuilder stringbuilder = new StringBuilder();
        if((mState & 1) == 1)
            stringbuilder.append("CodeLock|");
        if((mState & 2) == 2)
            stringbuilder.append("BitSync|");
        if((mState & 4) == 4)
            stringbuilder.append("SubframeSync|");
        if((mState & 8) == 8)
            stringbuilder.append("TowDecoded|");
        if((mState & 0x10) == 16)
            stringbuilder.append("MsecAmbiguous");
        int i = mState & 0xffffffe0;
        if(i > 0)
        {
            stringbuilder.append("Other(");
            stringbuilder.append(Integer.toBinaryString(i));
            stringbuilder.append(")|");
        }
        stringbuilder.deleteCharAt(stringbuilder.length() - 1);
        return stringbuilder.toString();
    }

    private void initialize()
    {
        mFlags = 0;
        setPrn((byte)-128);
        setTimeOffsetInNs(-9.2233720368547758E+018D);
        setState((short)0);
        setReceivedGpsTowInNs(0x8000000000000000L);
        setReceivedGpsTowUncertaintyInNs(0x7fffffffffffffffL);
        setCn0InDbHz(4.9406564584124654E-324D);
        setPseudorangeRateInMetersPerSec(4.9406564584124654E-324D);
        setPseudorangeRateUncertaintyInMetersPerSec(4.9406564584124654E-324D);
        setAccumulatedDeltaRangeState((short)0);
        setAccumulatedDeltaRangeInMeters(4.9406564584124654E-324D);
        setAccumulatedDeltaRangeUncertaintyInMeters(4.9406564584124654E-324D);
        resetPseudorangeInMeters();
        resetPseudorangeUncertaintyInMeters();
        resetCodePhaseInChips();
        resetCodePhaseUncertaintyInChips();
        resetCarrierFrequencyInHz();
        resetCarrierCycles();
        resetCarrierPhase();
        resetCarrierPhaseUncertainty();
        setLossOfLock((byte)0);
        resetBitNumber();
        resetTimeFromLastBitInMs();
        resetDopplerShiftInHz();
        resetDopplerShiftUncertaintyInHz();
        setMultipathIndicator((byte)0);
        resetSnrInDb();
        resetElevationInDeg();
        resetElevationUncertaintyInDeg();
        resetAzimuthInDeg();
        resetAzimuthUncertaintyInDeg();
        setUsedInFix(false);
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

    public double getAccumulatedDeltaRangeInMeters()
    {
        return mAccumulatedDeltaRangeInMeters;
    }

    public short getAccumulatedDeltaRangeState()
    {
        return mAccumulatedDeltaRangeState;
    }

    public double getAccumulatedDeltaRangeUncertaintyInMeters()
    {
        return mAccumulatedDeltaRangeUncertaintyInMeters;
    }

    public double getAzimuthInDeg()
    {
        return mAzimuthInDeg;
    }

    public double getAzimuthUncertaintyInDeg()
    {
        return mAzimuthUncertaintyInDeg;
    }

    public int getBitNumber()
    {
        return mBitNumber;
    }

    public long getCarrierCycles()
    {
        return mCarrierCycles;
    }

    public float getCarrierFrequencyInHz()
    {
        return mCarrierFrequencyInHz;
    }

    public double getCarrierPhase()
    {
        return mCarrierPhase;
    }

    public double getCarrierPhaseUncertainty()
    {
        return mCarrierPhaseUncertainty;
    }

    public double getCn0InDbHz()
    {
        return mCn0InDbHz;
    }

    public double getCodePhaseInChips()
    {
        return mCodePhaseInChips;
    }

    public double getCodePhaseUncertaintyInChips()
    {
        return mCodePhaseUncertaintyInChips;
    }

    public double getDopplerShiftInHz()
    {
        return mDopplerShiftInHz;
    }

    public double getDopplerShiftUncertaintyInHz()
    {
        return mDopplerShiftUncertaintyInHz;
    }

    public double getElevationInDeg()
    {
        return mElevationInDeg;
    }

    public double getElevationUncertaintyInDeg()
    {
        return mElevationUncertaintyInDeg;
    }

    public byte getLossOfLock()
    {
        return mLossOfLock;
    }

    public byte getMultipathIndicator()
    {
        return mMultipathIndicator;
    }

    public byte getPrn()
    {
        return mPrn;
    }

    public double getPseudorangeInMeters()
    {
        return mPseudorangeInMeters;
    }

    public double getPseudorangeRateInMetersPerSec()
    {
        return mPseudorangeRateInMetersPerSec;
    }

    public double getPseudorangeRateUncertaintyInMetersPerSec()
    {
        return mPseudorangeRateUncertaintyInMetersPerSec;
    }

    public double getPseudorangeUncertaintyInMeters()
    {
        return mPseudorangeUncertaintyInMeters;
    }

    public long getReceivedGpsTowInNs()
    {
        return mReceivedGpsTowInNs;
    }

    public long getReceivedGpsTowUncertaintyInNs()
    {
        return mReceivedGpsTowUncertaintyInNs;
    }

    public double getSnrInDb()
    {
        return mSnrInDb;
    }

    public short getState()
    {
        return mState;
    }

    public short getTimeFromLastBitInMs()
    {
        return mTimeFromLastBitInMs;
    }

    public double getTimeOffsetInNs()
    {
        return mTimeOffsetInNs;
    }

    public boolean hasAzimuthInDeg()
    {
        return isFlagSet(8);
    }

    public boolean hasAzimuthUncertaintyInDeg()
    {
        return isFlagSet(16);
    }

    public boolean hasBitNumber()
    {
        return isFlagSet(8192);
    }

    public boolean hasCarrierCycles()
    {
        return isFlagSet(1024);
    }

    public boolean hasCarrierFrequencyInHz()
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

    public boolean hasCodePhaseInChips()
    {
        return isFlagSet(128);
    }

    public boolean hasCodePhaseUncertaintyInChips()
    {
        return isFlagSet(256);
    }

    public boolean hasDopplerShiftInHz()
    {
        return isFlagSet(32768);
    }

    public boolean hasDopplerShiftUncertaintyInHz()
    {
        return isFlagSet(0x10000);
    }

    public boolean hasElevationInDeg()
    {
        return isFlagSet(2);
    }

    public boolean hasElevationUncertaintyInDeg()
    {
        return isFlagSet(4);
    }

    public boolean hasPseudorangeInMeters()
    {
        return isFlagSet(32);
    }

    public boolean hasPseudorangeUncertaintyInMeters()
    {
        return isFlagSet(64);
    }

    public boolean hasSnrInDb()
    {
        return isFlagSet(1);
    }

    public boolean hasTimeFromLastBitInMs()
    {
        return isFlagSet(16384);
    }

    public boolean isPseudorangeRateCorrected()
    {
        return isFlagSet(0x40000) ^ true;
    }

    public boolean isUsedInFix()
    {
        return mUsedInFix;
    }

    public void reset()
    {
        initialize();
    }

    public void resetAzimuthInDeg()
    {
        resetFlag(8);
        mAzimuthInDeg = (0.0D / 0.0D);
    }

    public void resetAzimuthUncertaintyInDeg()
    {
        resetFlag(16);
        mAzimuthUncertaintyInDeg = (0.0D / 0.0D);
    }

    public void resetBitNumber()
    {
        resetFlag(8192);
        mBitNumber = 0x80000000;
    }

    public void resetCarrierCycles()
    {
        resetFlag(1024);
        mCarrierCycles = 0x8000000000000000L;
    }

    public void resetCarrierFrequencyInHz()
    {
        resetFlag(512);
        mCarrierFrequencyInHz = (0.0F / 0.0F);
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

    public void resetCodePhaseInChips()
    {
        resetFlag(128);
        mCodePhaseInChips = (0.0D / 0.0D);
    }

    public void resetCodePhaseUncertaintyInChips()
    {
        resetFlag(256);
        mCodePhaseUncertaintyInChips = (0.0D / 0.0D);
    }

    public void resetDopplerShiftInHz()
    {
        resetFlag(32768);
        mDopplerShiftInHz = (0.0D / 0.0D);
    }

    public void resetDopplerShiftUncertaintyInHz()
    {
        resetFlag(0x10000);
        mDopplerShiftUncertaintyInHz = (0.0D / 0.0D);
    }

    public void resetElevationInDeg()
    {
        resetFlag(2);
        mElevationInDeg = (0.0D / 0.0D);
    }

    public void resetElevationUncertaintyInDeg()
    {
        resetFlag(4);
        mElevationUncertaintyInDeg = (0.0D / 0.0D);
    }

    public void resetPseudorangeInMeters()
    {
        resetFlag(32);
        mPseudorangeInMeters = (0.0D / 0.0D);
    }

    public void resetPseudorangeUncertaintyInMeters()
    {
        resetFlag(64);
        mPseudorangeUncertaintyInMeters = (0.0D / 0.0D);
    }

    public void resetSnrInDb()
    {
        resetFlag(1);
        mSnrInDb = (0.0D / 0.0D);
    }

    public void resetTimeFromLastBitInMs()
    {
        resetFlag(16384);
        mTimeFromLastBitInMs = (short)-32768;
    }

    public void set(GpsMeasurement gpsmeasurement)
    {
        mFlags = gpsmeasurement.mFlags;
        mPrn = gpsmeasurement.mPrn;
        mTimeOffsetInNs = gpsmeasurement.mTimeOffsetInNs;
        mState = gpsmeasurement.mState;
        mReceivedGpsTowInNs = gpsmeasurement.mReceivedGpsTowInNs;
        mReceivedGpsTowUncertaintyInNs = gpsmeasurement.mReceivedGpsTowUncertaintyInNs;
        mCn0InDbHz = gpsmeasurement.mCn0InDbHz;
        mPseudorangeRateInMetersPerSec = gpsmeasurement.mPseudorangeRateInMetersPerSec;
        mPseudorangeRateUncertaintyInMetersPerSec = gpsmeasurement.mPseudorangeRateUncertaintyInMetersPerSec;
        mAccumulatedDeltaRangeState = gpsmeasurement.mAccumulatedDeltaRangeState;
        mAccumulatedDeltaRangeInMeters = gpsmeasurement.mAccumulatedDeltaRangeInMeters;
        mAccumulatedDeltaRangeUncertaintyInMeters = gpsmeasurement.mAccumulatedDeltaRangeUncertaintyInMeters;
        mPseudorangeInMeters = gpsmeasurement.mPseudorangeInMeters;
        mPseudorangeUncertaintyInMeters = gpsmeasurement.mPseudorangeUncertaintyInMeters;
        mCodePhaseInChips = gpsmeasurement.mCodePhaseInChips;
        mCodePhaseUncertaintyInChips = gpsmeasurement.mCodePhaseUncertaintyInChips;
        mCarrierFrequencyInHz = gpsmeasurement.mCarrierFrequencyInHz;
        mCarrierCycles = gpsmeasurement.mCarrierCycles;
        mCarrierPhase = gpsmeasurement.mCarrierPhase;
        mCarrierPhaseUncertainty = gpsmeasurement.mCarrierPhaseUncertainty;
        mLossOfLock = gpsmeasurement.mLossOfLock;
        mBitNumber = gpsmeasurement.mBitNumber;
        mTimeFromLastBitInMs = gpsmeasurement.mTimeFromLastBitInMs;
        mDopplerShiftInHz = gpsmeasurement.mDopplerShiftInHz;
        mDopplerShiftUncertaintyInHz = gpsmeasurement.mDopplerShiftUncertaintyInHz;
        mMultipathIndicator = gpsmeasurement.mMultipathIndicator;
        mSnrInDb = gpsmeasurement.mSnrInDb;
        mElevationInDeg = gpsmeasurement.mElevationInDeg;
        mElevationUncertaintyInDeg = gpsmeasurement.mElevationUncertaintyInDeg;
        mAzimuthInDeg = gpsmeasurement.mAzimuthInDeg;
        mAzimuthUncertaintyInDeg = gpsmeasurement.mAzimuthUncertaintyInDeg;
        mUsedInFix = gpsmeasurement.mUsedInFix;
    }

    public void setAccumulatedDeltaRangeInMeters(double d)
    {
        mAccumulatedDeltaRangeInMeters = d;
    }

    public void setAccumulatedDeltaRangeState(short word0)
    {
        mAccumulatedDeltaRangeState = word0;
    }

    public void setAccumulatedDeltaRangeUncertaintyInMeters(double d)
    {
        mAccumulatedDeltaRangeUncertaintyInMeters = d;
    }

    public void setAzimuthInDeg(double d)
    {
        setFlag(8);
        mAzimuthInDeg = d;
    }

    public void setAzimuthUncertaintyInDeg(double d)
    {
        setFlag(16);
        mAzimuthUncertaintyInDeg = d;
    }

    public void setBitNumber(int i)
    {
        setFlag(8192);
        mBitNumber = i;
    }

    public void setCarrierCycles(long l)
    {
        setFlag(1024);
        mCarrierCycles = l;
    }

    public void setCarrierFrequencyInHz(float f)
    {
        setFlag(512);
        mCarrierFrequencyInHz = f;
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

    public void setCn0InDbHz(double d)
    {
        mCn0InDbHz = d;
    }

    public void setCodePhaseInChips(double d)
    {
        setFlag(128);
        mCodePhaseInChips = d;
    }

    public void setCodePhaseUncertaintyInChips(double d)
    {
        setFlag(256);
        mCodePhaseUncertaintyInChips = d;
    }

    public void setDopplerShiftInHz(double d)
    {
        setFlag(32768);
        mDopplerShiftInHz = d;
    }

    public void setDopplerShiftUncertaintyInHz(double d)
    {
        setFlag(0x10000);
        mDopplerShiftUncertaintyInHz = d;
    }

    public void setElevationInDeg(double d)
    {
        setFlag(2);
        mElevationInDeg = d;
    }

    public void setElevationUncertaintyInDeg(double d)
    {
        setFlag(4);
        mElevationUncertaintyInDeg = d;
    }

    public void setLossOfLock(byte byte0)
    {
        mLossOfLock = byte0;
    }

    public void setMultipathIndicator(byte byte0)
    {
        mMultipathIndicator = byte0;
    }

    public void setPrn(byte byte0)
    {
        mPrn = byte0;
    }

    public void setPseudorangeInMeters(double d)
    {
        setFlag(32);
        mPseudorangeInMeters = d;
    }

    public void setPseudorangeRateInMetersPerSec(double d)
    {
        mPseudorangeRateInMetersPerSec = d;
    }

    public void setPseudorangeRateUncertaintyInMetersPerSec(double d)
    {
        mPseudorangeRateUncertaintyInMetersPerSec = d;
    }

    public void setPseudorangeUncertaintyInMeters(double d)
    {
        setFlag(64);
        mPseudorangeUncertaintyInMeters = d;
    }

    public void setReceivedGpsTowInNs(long l)
    {
        mReceivedGpsTowInNs = l;
    }

    public void setReceivedGpsTowUncertaintyInNs(long l)
    {
        mReceivedGpsTowUncertaintyInNs = l;
    }

    public void setSnrInDb(double d)
    {
        setFlag(1);
        mSnrInDb = d;
    }

    public void setState(short word0)
    {
        mState = word0;
    }

    public void setTimeFromLastBitInMs(short word0)
    {
        setFlag(16384);
        mTimeFromLastBitInMs = word0;
    }

    public void setTimeOffsetInNs(double d)
    {
        mTimeOffsetInNs = d;
    }

    public void setUsedInFix(boolean flag)
    {
        mUsedInFix = flag;
    }

    public String toString()
    {
        Object obj = null;
        StringBuilder stringbuilder = new StringBuilder("GpsMeasurement:\n");
        stringbuilder.append(String.format("   %-29s = %s\n", new Object[] {
            "Prn", Byte.valueOf(mPrn)
        }));
        stringbuilder.append(String.format("   %-29s = %s\n", new Object[] {
            "TimeOffsetInNs", Double.valueOf(mTimeOffsetInNs)
        }));
        stringbuilder.append(String.format("   %-29s = %s\n", new Object[] {
            "State", getStateString()
        }));
        stringbuilder.append(String.format("   %-29s = %-25s   %-40s = %s\n", new Object[] {
            "ReceivedGpsTowInNs", Long.valueOf(mReceivedGpsTowInNs), "ReceivedGpsTowUncertaintyInNs", Long.valueOf(mReceivedGpsTowUncertaintyInNs)
        }));
        stringbuilder.append(String.format("   %-29s = %s\n", new Object[] {
            "Cn0InDbHz", Double.valueOf(mCn0InDbHz)
        }));
        stringbuilder.append(String.format("   %-29s = %-25s   %-40s = %s\n", new Object[] {
            "PseudorangeRateInMetersPerSec", Double.valueOf(mPseudorangeRateInMetersPerSec), "PseudorangeRateUncertaintyInMetersPerSec", Double.valueOf(mPseudorangeRateUncertaintyInMetersPerSec)
        }));
        stringbuilder.append(String.format("   %-29s = %s\n", new Object[] {
            "PseudorangeRateIsCorrected", Boolean.valueOf(isPseudorangeRateCorrected())
        }));
        stringbuilder.append(String.format("   %-29s = %s\n", new Object[] {
            "AccumulatedDeltaRangeState", getAccumulatedDeltaRangeStateString()
        }));
        stringbuilder.append(String.format("   %-29s = %-25s   %-40s = %s\n", new Object[] {
            "AccumulatedDeltaRangeInMeters", Double.valueOf(mAccumulatedDeltaRangeInMeters), "AccumulatedDeltaRangeUncertaintyInMeters", Double.valueOf(mAccumulatedDeltaRangeUncertaintyInMeters)
        }));
        Object obj1;
        Double double1;
        if(hasPseudorangeInMeters())
            obj1 = Double.valueOf(mPseudorangeInMeters);
        else
            obj1 = null;
        if(hasPseudorangeUncertaintyInMeters())
            double1 = Double.valueOf(mPseudorangeUncertaintyInMeters);
        else
            double1 = null;
        stringbuilder.append(String.format("   %-29s = %-25s   %-40s = %s\n", new Object[] {
            "PseudorangeInMeters", obj1, "PseudorangeUncertaintyInMeters", double1
        }));
        if(hasCodePhaseInChips())
            obj1 = Double.valueOf(mCodePhaseInChips);
        else
            obj1 = null;
        if(hasCodePhaseUncertaintyInChips())
            double1 = Double.valueOf(mCodePhaseUncertaintyInChips);
        else
            double1 = null;
        stringbuilder.append(String.format("   %-29s = %-25s   %-40s = %s\n", new Object[] {
            "CodePhaseInChips", obj1, "CodePhaseUncertaintyInChips", double1
        }));
        if(hasCarrierFrequencyInHz())
            obj1 = Float.valueOf(mCarrierFrequencyInHz);
        else
            obj1 = null;
        stringbuilder.append(String.format("   %-29s = %s\n", new Object[] {
            "CarrierFrequencyInHz", obj1
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
            "LossOfLock", getLossOfLockString()
        }));
        if(hasBitNumber())
            obj1 = Integer.valueOf(mBitNumber);
        else
            obj1 = null;
        stringbuilder.append(String.format("   %-29s = %s\n", new Object[] {
            "BitNumber", obj1
        }));
        if(hasTimeFromLastBitInMs())
            obj1 = Short.valueOf(mTimeFromLastBitInMs);
        else
            obj1 = null;
        stringbuilder.append(String.format("   %-29s = %s\n", new Object[] {
            "TimeFromLastBitInMs", obj1
        }));
        if(hasDopplerShiftInHz())
            obj1 = Double.valueOf(mDopplerShiftInHz);
        else
            obj1 = null;
        if(hasDopplerShiftUncertaintyInHz())
            double1 = Double.valueOf(mDopplerShiftUncertaintyInHz);
        else
            double1 = null;
        stringbuilder.append(String.format("   %-29s = %-25s   %-40s = %s\n", new Object[] {
            "DopplerShiftInHz", obj1, "DopplerShiftUncertaintyInHz", double1
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
        if(hasElevationInDeg())
            obj1 = Double.valueOf(mElevationInDeg);
        else
            obj1 = null;
        if(hasElevationUncertaintyInDeg())
            double1 = Double.valueOf(mElevationUncertaintyInDeg);
        else
            double1 = null;
        stringbuilder.append(String.format("   %-29s = %-25s   %-40s = %s\n", new Object[] {
            "ElevationInDeg", obj1, "ElevationUncertaintyInDeg", double1
        }));
        if(hasAzimuthInDeg())
            obj1 = Double.valueOf(mAzimuthInDeg);
        else
            obj1 = null;
        double1 = obj;
        if(hasAzimuthUncertaintyInDeg())
            double1 = Double.valueOf(mAzimuthUncertaintyInDeg);
        stringbuilder.append(String.format("   %-29s = %-25s   %-40s = %s\n", new Object[] {
            "AzimuthInDeg", obj1, "AzimuthUncertaintyInDeg", double1
        }));
        stringbuilder.append(String.format("   %-29s = %s\n", new Object[] {
            "UsedInFix", Boolean.valueOf(mUsedInFix)
        }));
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mFlags);
        parcel.writeByte(mPrn);
        parcel.writeDouble(mTimeOffsetInNs);
        parcel.writeInt(mState);
        parcel.writeLong(mReceivedGpsTowInNs);
        parcel.writeLong(mReceivedGpsTowUncertaintyInNs);
        parcel.writeDouble(mCn0InDbHz);
        parcel.writeDouble(mPseudorangeRateInMetersPerSec);
        parcel.writeDouble(mPseudorangeRateUncertaintyInMetersPerSec);
        parcel.writeInt(mAccumulatedDeltaRangeState);
        parcel.writeDouble(mAccumulatedDeltaRangeInMeters);
        parcel.writeDouble(mAccumulatedDeltaRangeUncertaintyInMeters);
        parcel.writeDouble(mPseudorangeInMeters);
        parcel.writeDouble(mPseudorangeUncertaintyInMeters);
        parcel.writeDouble(mCodePhaseInChips);
        parcel.writeDouble(mCodePhaseUncertaintyInChips);
        parcel.writeFloat(mCarrierFrequencyInHz);
        parcel.writeLong(mCarrierCycles);
        parcel.writeDouble(mCarrierPhase);
        parcel.writeDouble(mCarrierPhaseUncertainty);
        parcel.writeByte(mLossOfLock);
        parcel.writeInt(mBitNumber);
        parcel.writeInt(mTimeFromLastBitInMs);
        parcel.writeDouble(mDopplerShiftInHz);
        parcel.writeDouble(mDopplerShiftUncertaintyInHz);
        parcel.writeByte(mMultipathIndicator);
        parcel.writeDouble(mSnrInDb);
        parcel.writeDouble(mElevationInDeg);
        parcel.writeDouble(mElevationUncertaintyInDeg);
        parcel.writeDouble(mAzimuthInDeg);
        parcel.writeDouble(mAzimuthUncertaintyInDeg);
        if(mUsedInFix)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
    }

    private static final short ADR_ALL = 7;
    public static final short ADR_STATE_CYCLE_SLIP = 4;
    public static final short ADR_STATE_RESET = 2;
    public static final short ADR_STATE_UNKNOWN = 0;
    public static final short ADR_STATE_VALID = 1;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public GpsMeasurement createFromParcel(Parcel parcel)
        {
            boolean flag = false;
            GpsMeasurement gpsmeasurement = new GpsMeasurement();
            GpsMeasurement._2D_set17(gpsmeasurement, parcel.readInt());
            GpsMeasurement._2D_set20(gpsmeasurement, parcel.readByte());
            GpsMeasurement._2D_set30(gpsmeasurement, parcel.readDouble());
            GpsMeasurement._2D_set28(gpsmeasurement, (short)parcel.readInt());
            GpsMeasurement._2D_set25(gpsmeasurement, parcel.readLong());
            GpsMeasurement._2D_set26(gpsmeasurement, parcel.readLong());
            GpsMeasurement._2D_set10(gpsmeasurement, parcel.readDouble());
            GpsMeasurement._2D_set22(gpsmeasurement, parcel.readDouble());
            GpsMeasurement._2D_set23(gpsmeasurement, parcel.readDouble());
            GpsMeasurement._2D_set1(gpsmeasurement, (short)parcel.readInt());
            GpsMeasurement._2D_set0(gpsmeasurement, parcel.readDouble());
            GpsMeasurement._2D_set2(gpsmeasurement, parcel.readDouble());
            GpsMeasurement._2D_set21(gpsmeasurement, parcel.readDouble());
            GpsMeasurement._2D_set24(gpsmeasurement, parcel.readDouble());
            GpsMeasurement._2D_set11(gpsmeasurement, parcel.readDouble());
            GpsMeasurement._2D_set12(gpsmeasurement, parcel.readDouble());
            GpsMeasurement._2D_set7(gpsmeasurement, parcel.readFloat());
            GpsMeasurement._2D_set6(gpsmeasurement, parcel.readLong());
            GpsMeasurement._2D_set8(gpsmeasurement, parcel.readDouble());
            GpsMeasurement._2D_set9(gpsmeasurement, parcel.readDouble());
            GpsMeasurement._2D_set18(gpsmeasurement, parcel.readByte());
            GpsMeasurement._2D_set5(gpsmeasurement, parcel.readInt());
            GpsMeasurement._2D_set29(gpsmeasurement, (short)parcel.readInt());
            GpsMeasurement._2D_set13(gpsmeasurement, parcel.readDouble());
            GpsMeasurement._2D_set14(gpsmeasurement, parcel.readDouble());
            GpsMeasurement._2D_set19(gpsmeasurement, parcel.readByte());
            GpsMeasurement._2D_set27(gpsmeasurement, parcel.readDouble());
            GpsMeasurement._2D_set15(gpsmeasurement, parcel.readDouble());
            GpsMeasurement._2D_set16(gpsmeasurement, parcel.readDouble());
            GpsMeasurement._2D_set3(gpsmeasurement, parcel.readDouble());
            GpsMeasurement._2D_set4(gpsmeasurement, parcel.readDouble());
            if(parcel.readInt() != 0)
                flag = true;
            GpsMeasurement._2D_set31(gpsmeasurement, flag);
            return gpsmeasurement;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public GpsMeasurement[] newArray(int i)
        {
            return new GpsMeasurement[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int GPS_MEASUREMENT_HAS_UNCORRECTED_PSEUDORANGE_RATE = 0x40000;
    private static final int HAS_AZIMUTH = 8;
    private static final int HAS_AZIMUTH_UNCERTAINTY = 16;
    private static final int HAS_BIT_NUMBER = 8192;
    private static final int HAS_CARRIER_CYCLES = 1024;
    private static final int HAS_CARRIER_FREQUENCY = 512;
    private static final int HAS_CARRIER_PHASE = 2048;
    private static final int HAS_CARRIER_PHASE_UNCERTAINTY = 4096;
    private static final int HAS_CODE_PHASE = 128;
    private static final int HAS_CODE_PHASE_UNCERTAINTY = 256;
    private static final int HAS_DOPPLER_SHIFT = 32768;
    private static final int HAS_DOPPLER_SHIFT_UNCERTAINTY = 0x10000;
    private static final int HAS_ELEVATION = 2;
    private static final int HAS_ELEVATION_UNCERTAINTY = 4;
    private static final int HAS_NO_FLAGS = 0;
    private static final int HAS_PSEUDORANGE = 32;
    private static final int HAS_PSEUDORANGE_UNCERTAINTY = 64;
    private static final int HAS_SNR = 1;
    private static final int HAS_TIME_FROM_LAST_BIT = 16384;
    private static final int HAS_USED_IN_FIX = 0x20000;
    public static final byte LOSS_OF_LOCK_CYCLE_SLIP = 2;
    public static final byte LOSS_OF_LOCK_OK = 1;
    public static final byte LOSS_OF_LOCK_UNKNOWN = 0;
    public static final byte MULTIPATH_INDICATOR_DETECTED = 1;
    public static final byte MULTIPATH_INDICATOR_NOT_USED = 2;
    public static final byte MULTIPATH_INDICATOR_UNKNOWN = 0;
    private static final short STATE_ALL = 31;
    public static final short STATE_BIT_SYNC = 2;
    public static final short STATE_CODE_LOCK = 1;
    public static final short STATE_MSEC_AMBIGUOUS = 16;
    public static final short STATE_SUBFRAME_SYNC = 4;
    public static final short STATE_TOW_DECODED = 8;
    public static final short STATE_UNKNOWN = 0;
    private double mAccumulatedDeltaRangeInMeters;
    private short mAccumulatedDeltaRangeState;
    private double mAccumulatedDeltaRangeUncertaintyInMeters;
    private double mAzimuthInDeg;
    private double mAzimuthUncertaintyInDeg;
    private int mBitNumber;
    private long mCarrierCycles;
    private float mCarrierFrequencyInHz;
    private double mCarrierPhase;
    private double mCarrierPhaseUncertainty;
    private double mCn0InDbHz;
    private double mCodePhaseInChips;
    private double mCodePhaseUncertaintyInChips;
    private double mDopplerShiftInHz;
    private double mDopplerShiftUncertaintyInHz;
    private double mElevationInDeg;
    private double mElevationUncertaintyInDeg;
    private int mFlags;
    private byte mLossOfLock;
    private byte mMultipathIndicator;
    private byte mPrn;
    private double mPseudorangeInMeters;
    private double mPseudorangeRateInMetersPerSec;
    private double mPseudorangeRateUncertaintyInMetersPerSec;
    private double mPseudorangeUncertaintyInMeters;
    private long mReceivedGpsTowInNs;
    private long mReceivedGpsTowUncertaintyInNs;
    private double mSnrInDb;
    private short mState;
    private short mTimeFromLastBitInMs;
    private double mTimeOffsetInNs;
    private boolean mUsedInFix;

}

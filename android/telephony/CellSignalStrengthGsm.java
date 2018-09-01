// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.telephony:
//            CellSignalStrength, Rlog

public final class CellSignalStrengthGsm extends CellSignalStrength
    implements Parcelable
{

    public CellSignalStrengthGsm()
    {
        setDefaultValues();
    }

    public CellSignalStrengthGsm(int i, int j)
    {
        initialize(i, j);
    }

    private CellSignalStrengthGsm(Parcel parcel)
    {
        mSignalStrength = parcel.readInt();
        mBitErrorRate = parcel.readInt();
        mTimingAdvance = parcel.readInt();
    }

    CellSignalStrengthGsm(Parcel parcel, CellSignalStrengthGsm cellsignalstrengthgsm)
    {
        this(parcel);
    }

    public CellSignalStrengthGsm(CellSignalStrengthGsm cellsignalstrengthgsm)
    {
        copyFrom(cellsignalstrengthgsm);
    }

    private static void log(String s)
    {
        Rlog.w("CellSignalStrengthGsm", s);
    }

    public volatile CellSignalStrength copy()
    {
        return copy();
    }

    public CellSignalStrengthGsm copy()
    {
        return new CellSignalStrengthGsm(this);
    }

    protected void copyFrom(CellSignalStrengthGsm cellsignalstrengthgsm)
    {
        mSignalStrength = cellsignalstrengthgsm.mSignalStrength;
        mBitErrorRate = cellsignalstrengthgsm.mBitErrorRate;
        mTimingAdvance = cellsignalstrengthgsm.mTimingAdvance;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        CellSignalStrengthGsm cellsignalstrengthgsm;
        try
        {
            cellsignalstrengthgsm = (CellSignalStrengthGsm)obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        if(obj == null)
            return false;
        boolean flag1 = flag;
        if(mSignalStrength == cellsignalstrengthgsm.mSignalStrength)
        {
            flag1 = flag;
            if(mBitErrorRate == cellsignalstrengthgsm.mBitErrorRate)
            {
                flag1 = flag;
                if(cellsignalstrengthgsm.mTimingAdvance == mTimingAdvance)
                    flag1 = true;
            }
        }
        return flag1;
    }

    public int getAsuLevel()
    {
        return mSignalStrength;
    }

    public int getDbm()
    {
        int i = mSignalStrength;
        if(i == 99)
            i = 0x7fffffff;
        if(i != 0x7fffffff)
            i = i * 2 - 113;
        else
            i = 0x7fffffff;
        return i;
    }

    public int getLevel()
    {
        int i = mSignalStrength;
        if(i <= 2 || i == 99)
            i = 0;
        else
        if(i >= 12)
            i = 4;
        else
        if(i >= 8)
            i = 3;
        else
        if(i >= 5)
            i = 2;
        else
            i = 1;
        return i;
    }

    public int getTimingAdvance()
    {
        return mTimingAdvance;
    }

    public int hashCode()
    {
        return mSignalStrength * 31 + mBitErrorRate * 31;
    }

    public void initialize(int i, int j)
    {
        mSignalStrength = i;
        mBitErrorRate = j;
        mTimingAdvance = 0x7fffffff;
    }

    public void initialize(int i, int j, int k)
    {
        mSignalStrength = i;
        mBitErrorRate = j;
        mTimingAdvance = k;
    }

    public void setDefaultValues()
    {
        mSignalStrength = 0x7fffffff;
        mBitErrorRate = 0x7fffffff;
        mTimingAdvance = 0x7fffffff;
    }

    public String toString()
    {
        return (new StringBuilder()).append("CellSignalStrengthGsm: ss=").append(mSignalStrength).append(" ber=").append(mBitErrorRate).append(" mTa=").append(mTimingAdvance).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mSignalStrength);
        parcel.writeInt(mBitErrorRate);
        parcel.writeInt(mTimingAdvance);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CellSignalStrengthGsm createFromParcel(Parcel parcel)
        {
            return new CellSignalStrengthGsm(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CellSignalStrengthGsm[] newArray(int i)
        {
            return new CellSignalStrengthGsm[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DBG = false;
    private static final int GSM_SIGNAL_STRENGTH_GOOD = 8;
    private static final int GSM_SIGNAL_STRENGTH_GREAT = 12;
    private static final int GSM_SIGNAL_STRENGTH_MODERATE = 5;
    private static final String LOG_TAG = "CellSignalStrengthGsm";
    private int mBitErrorRate;
    private int mSignalStrength;
    private int mTimingAdvance;

}

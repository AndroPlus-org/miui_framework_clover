// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.telephony:
//            CellSignalStrength, Rlog

public final class CellSignalStrengthWcdma extends CellSignalStrength
    implements Parcelable
{

    public CellSignalStrengthWcdma()
    {
        setDefaultValues();
    }

    public CellSignalStrengthWcdma(int i, int j)
    {
        initialize(i, j);
    }

    private CellSignalStrengthWcdma(Parcel parcel)
    {
        mSignalStrength = parcel.readInt();
        mBitErrorRate = parcel.readInt();
    }

    CellSignalStrengthWcdma(Parcel parcel, CellSignalStrengthWcdma cellsignalstrengthwcdma)
    {
        this(parcel);
    }

    public CellSignalStrengthWcdma(CellSignalStrengthWcdma cellsignalstrengthwcdma)
    {
        copyFrom(cellsignalstrengthwcdma);
    }

    private static void log(String s)
    {
        Rlog.w("CellSignalStrengthWcdma", s);
    }

    public volatile CellSignalStrength copy()
    {
        return copy();
    }

    public CellSignalStrengthWcdma copy()
    {
        return new CellSignalStrengthWcdma(this);
    }

    protected void copyFrom(CellSignalStrengthWcdma cellsignalstrengthwcdma)
    {
        mSignalStrength = cellsignalstrengthwcdma.mSignalStrength;
        mBitErrorRate = cellsignalstrengthwcdma.mBitErrorRate;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        CellSignalStrengthWcdma cellsignalstrengthwcdma;
        try
        {
            cellsignalstrengthwcdma = (CellSignalStrengthWcdma)obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        if(obj == null)
            return false;
        boolean flag1 = flag;
        if(mSignalStrength == cellsignalstrengthwcdma.mSignalStrength)
        {
            flag1 = flag;
            if(mBitErrorRate == cellsignalstrengthwcdma.mBitErrorRate)
                flag1 = true;
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

    public int hashCode()
    {
        return mSignalStrength * 31 + mBitErrorRate * 31;
    }

    public void initialize(int i, int j)
    {
        mSignalStrength = i;
        mBitErrorRate = j;
    }

    public void setDefaultValues()
    {
        mSignalStrength = 0x7fffffff;
        mBitErrorRate = 0x7fffffff;
    }

    public String toString()
    {
        return (new StringBuilder()).append("CellSignalStrengthWcdma: ss=").append(mSignalStrength).append(" ber=").append(mBitErrorRate).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mSignalStrength);
        parcel.writeInt(mBitErrorRate);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CellSignalStrengthWcdma createFromParcel(Parcel parcel)
        {
            return new CellSignalStrengthWcdma(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CellSignalStrengthWcdma[] newArray(int i)
        {
            return new CellSignalStrengthWcdma[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DBG = false;
    private static final String LOG_TAG = "CellSignalStrengthWcdma";
    private static final int WCDMA_SIGNAL_STRENGTH_GOOD = 8;
    private static final int WCDMA_SIGNAL_STRENGTH_GREAT = 12;
    private static final int WCDMA_SIGNAL_STRENGTH_MODERATE = 5;
    private int mBitErrorRate;
    private int mSignalStrength;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.telephony:
//            CellSignalStrength, Rlog

public final class CellSignalStrengthCdma extends CellSignalStrength
    implements Parcelable
{

    public CellSignalStrengthCdma()
    {
        setDefaultValues();
    }

    public CellSignalStrengthCdma(int i, int j, int k, int l, int i1)
    {
        initialize(i, j, k, l, i1);
    }

    private CellSignalStrengthCdma(Parcel parcel)
    {
        mCdmaDbm = parcel.readInt();
        if(mCdmaDbm != 0x7fffffff)
            mCdmaDbm = mCdmaDbm * -1;
        mCdmaEcio = parcel.readInt();
        if(mCdmaEcio != 0x7fffffff)
            mCdmaEcio = mCdmaEcio * -1;
        mEvdoDbm = parcel.readInt();
        if(mEvdoDbm != 0x7fffffff)
            mEvdoDbm = mEvdoDbm * -1;
        mEvdoEcio = parcel.readInt();
        if(mEvdoEcio != 0x7fffffff)
            mEvdoEcio = mEvdoEcio * -1;
        mEvdoSnr = parcel.readInt();
    }

    CellSignalStrengthCdma(Parcel parcel, CellSignalStrengthCdma cellsignalstrengthcdma)
    {
        this(parcel);
    }

    public CellSignalStrengthCdma(CellSignalStrengthCdma cellsignalstrengthcdma)
    {
        copyFrom(cellsignalstrengthcdma);
    }

    private static void log(String s)
    {
        Rlog.w("CellSignalStrengthCdma", s);
    }

    public volatile CellSignalStrength copy()
    {
        return copy();
    }

    public CellSignalStrengthCdma copy()
    {
        return new CellSignalStrengthCdma(this);
    }

    protected void copyFrom(CellSignalStrengthCdma cellsignalstrengthcdma)
    {
        mCdmaDbm = cellsignalstrengthcdma.mCdmaDbm;
        mCdmaEcio = cellsignalstrengthcdma.mCdmaEcio;
        mEvdoDbm = cellsignalstrengthcdma.mEvdoDbm;
        mEvdoEcio = cellsignalstrengthcdma.mEvdoEcio;
        mEvdoSnr = cellsignalstrengthcdma.mEvdoSnr;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        CellSignalStrengthCdma cellsignalstrengthcdma;
        try
        {
            cellsignalstrengthcdma = (CellSignalStrengthCdma)obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        if(obj == null)
            return false;
        boolean flag1 = flag;
        if(mCdmaDbm == cellsignalstrengthcdma.mCdmaDbm)
        {
            flag1 = flag;
            if(mCdmaEcio == cellsignalstrengthcdma.mCdmaEcio)
            {
                flag1 = flag;
                if(mEvdoDbm == cellsignalstrengthcdma.mEvdoDbm)
                {
                    flag1 = flag;
                    if(mEvdoEcio == cellsignalstrengthcdma.mEvdoEcio)
                    {
                        flag1 = flag;
                        if(mEvdoSnr == cellsignalstrengthcdma.mEvdoSnr)
                            flag1 = true;
                    }
                }
            }
        }
        return flag1;
    }

    public int getAsuLevel()
    {
        int i = getCdmaDbm();
        int j = getCdmaEcio();
        if(i == 0x7fffffff)
            i = 99;
        else
        if(i >= -75)
            i = 16;
        else
        if(i >= -82)
            i = 8;
        else
        if(i >= -90)
            i = 4;
        else
        if(i >= -95)
            i = 2;
        else
        if(i >= -100)
            i = 1;
        else
            i = 99;
        if(j == 0x7fffffff)
            j = 99;
        else
        if(j >= -90)
            j = 16;
        else
        if(j >= -100)
            j = 8;
        else
        if(j >= -115)
            j = 4;
        else
        if(j >= -130)
            j = 2;
        else
        if(j >= -150)
            j = 1;
        else
            j = 99;
        if(i >= j)
            i = j;
        return i;
    }

    public int getCdmaDbm()
    {
        return mCdmaDbm;
    }

    public int getCdmaEcio()
    {
        return mCdmaEcio;
    }

    public int getCdmaLevel()
    {
        int i = getCdmaDbm();
        int j = getCdmaEcio();
        if(i == 0x7fffffff)
            i = 0;
        else
        if(i >= -75)
            i = 4;
        else
        if(i >= -85)
            i = 3;
        else
        if(i >= -95)
            i = 2;
        else
        if(i >= -100)
            i = 1;
        else
            i = 0;
        if(j == 0x7fffffff)
            j = 0;
        else
        if(j >= -90)
            j = 4;
        else
        if(j >= -110)
            j = 3;
        else
        if(j >= -130)
            j = 2;
        else
        if(j >= -150)
            j = 1;
        else
            j = 0;
        if(i >= j)
            i = j;
        return i;
    }

    public int getDbm()
    {
        int i = getCdmaDbm();
        int j = getEvdoDbm();
        if(i >= j)
            i = j;
        return i;
    }

    public int getEvdoDbm()
    {
        return mEvdoDbm;
    }

    public int getEvdoEcio()
    {
        return mEvdoEcio;
    }

    public int getEvdoLevel()
    {
        int i = getEvdoDbm();
        int j = getEvdoSnr();
        if(i == 0x7fffffff)
            i = 0;
        else
        if(i >= -65)
            i = 4;
        else
        if(i >= -75)
            i = 3;
        else
        if(i >= -90)
            i = 2;
        else
        if(i >= -105)
            i = 1;
        else
            i = 0;
        if(j == 0x7fffffff)
            j = 0;
        else
        if(j >= 7)
            j = 4;
        else
        if(j >= 5)
            j = 3;
        else
        if(j >= 3)
            j = 2;
        else
        if(j >= 1)
            j = 1;
        else
            j = 0;
        if(i >= j)
            i = j;
        return i;
    }

    public int getEvdoSnr()
    {
        return mEvdoSnr;
    }

    public int getLevel()
    {
        int i;
        int j;
        i = getCdmaLevel();
        j = getEvdoLevel();
        if(j != 0) goto _L2; else goto _L1
_L1:
        j = getCdmaLevel();
_L4:
        return j;
_L2:
        if(i == 0)
            j = getEvdoLevel();
        else
        if(i < j)
            j = i;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int hashCode()
    {
        return mCdmaDbm * 31 + mCdmaEcio * 31 + mEvdoDbm * 31 + mEvdoEcio * 31 + mEvdoSnr * 31;
    }

    public void initialize(int i, int j, int k, int l, int i1)
    {
        mCdmaDbm = i;
        mCdmaEcio = j;
        mEvdoDbm = k;
        mEvdoEcio = l;
        mEvdoSnr = i1;
    }

    public void setCdmaDbm(int i)
    {
        mCdmaDbm = i;
    }

    public void setCdmaEcio(int i)
    {
        mCdmaEcio = i;
    }

    public void setDefaultValues()
    {
        mCdmaDbm = 0x7fffffff;
        mCdmaEcio = 0x7fffffff;
        mEvdoDbm = 0x7fffffff;
        mEvdoEcio = 0x7fffffff;
        mEvdoSnr = 0x7fffffff;
    }

    public void setEvdoDbm(int i)
    {
        mEvdoDbm = i;
    }

    public void setEvdoEcio(int i)
    {
        mEvdoEcio = i;
    }

    public void setEvdoSnr(int i)
    {
        mEvdoSnr = i;
    }

    public String toString()
    {
        return (new StringBuilder()).append("CellSignalStrengthCdma: cdmaDbm=").append(mCdmaDbm).append(" cdmaEcio=").append(mCdmaEcio).append(" evdoDbm=").append(mEvdoDbm).append(" evdoEcio=").append(mEvdoEcio).append(" evdoSnr=").append(mEvdoSnr).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        byte byte0 = -1;
        int j = mCdmaDbm;
        if(mCdmaDbm != 0x7fffffff)
            i = -1;
        else
            i = 1;
        parcel.writeInt(i * j);
        j = mCdmaEcio;
        if(mCdmaEcio != 0x7fffffff)
            i = -1;
        else
            i = 1;
        parcel.writeInt(i * j);
        j = mEvdoDbm;
        if(mEvdoDbm != 0x7fffffff)
            i = -1;
        else
            i = 1;
        parcel.writeInt(i * j);
        j = mEvdoEcio;
        if(mEvdoEcio != 0x7fffffff)
            i = byte0;
        else
            i = 1;
        parcel.writeInt(j * i);
        parcel.writeInt(mEvdoSnr);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CellSignalStrengthCdma createFromParcel(Parcel parcel)
        {
            return new CellSignalStrengthCdma(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CellSignalStrengthCdma[] newArray(int i)
        {
            return new CellSignalStrengthCdma[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DBG = false;
    private static final String LOG_TAG = "CellSignalStrengthCdma";
    private int mCdmaDbm;
    private int mCdmaEcio;
    private int mEvdoDbm;
    private int mEvdoEcio;
    private int mEvdoSnr;

}

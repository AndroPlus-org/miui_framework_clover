// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.telephony:
//            CellSignalStrength, Rlog, SignalStrength

public final class CellSignalStrengthLte extends CellSignalStrength
    implements Parcelable
{

    public CellSignalStrengthLte()
    {
        setDefaultValues();
    }

    public CellSignalStrengthLte(int i, int j, int k, int l, int i1, int j1)
    {
        initialize(i, j, k, l, i1, j1);
    }

    private CellSignalStrengthLte(Parcel parcel)
    {
        mSignalStrength = parcel.readInt();
        mRsrp = parcel.readInt();
        if(mRsrp != 0x7fffffff)
            mRsrp = mRsrp * -1;
        mRsrq = parcel.readInt();
        if(mRsrq != 0x7fffffff)
            mRsrq = mRsrq * -1;
        mRssnr = parcel.readInt();
        mCqi = parcel.readInt();
        mTimingAdvance = parcel.readInt();
    }

    CellSignalStrengthLte(Parcel parcel, CellSignalStrengthLte cellsignalstrengthlte)
    {
        this(parcel);
    }

    public CellSignalStrengthLte(CellSignalStrengthLte cellsignalstrengthlte)
    {
        copyFrom(cellsignalstrengthlte);
    }

    private static void log(String s)
    {
        Rlog.w("CellSignalStrengthLte", s);
    }

    public volatile CellSignalStrength copy()
    {
        return copy();
    }

    public CellSignalStrengthLte copy()
    {
        return new CellSignalStrengthLte(this);
    }

    protected void copyFrom(CellSignalStrengthLte cellsignalstrengthlte)
    {
        mSignalStrength = cellsignalstrengthlte.mSignalStrength;
        mRsrp = cellsignalstrengthlte.mRsrp;
        mRsrq = cellsignalstrengthlte.mRsrq;
        mRssnr = cellsignalstrengthlte.mRssnr;
        mCqi = cellsignalstrengthlte.mCqi;
        mTimingAdvance = cellsignalstrengthlte.mTimingAdvance;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        CellSignalStrengthLte cellsignalstrengthlte;
        try
        {
            cellsignalstrengthlte = (CellSignalStrengthLte)obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        if(obj == null)
            return false;
        boolean flag1 = flag;
        if(mSignalStrength == cellsignalstrengthlte.mSignalStrength)
        {
            flag1 = flag;
            if(mRsrp == cellsignalstrengthlte.mRsrp)
            {
                flag1 = flag;
                if(mRsrq == cellsignalstrengthlte.mRsrq)
                {
                    flag1 = flag;
                    if(mRssnr == cellsignalstrengthlte.mRssnr)
                    {
                        flag1 = flag;
                        if(mCqi == cellsignalstrengthlte.mCqi)
                        {
                            flag1 = flag;
                            if(mTimingAdvance == cellsignalstrengthlte.mTimingAdvance)
                                flag1 = true;
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public int getAsuLevel()
    {
        int i = getDbm();
        if(i == 0x7fffffff)
            i = 99;
        else
        if(i <= -140)
            i = 0;
        else
        if(i >= -43)
            i = 97;
        else
            i += 140;
        return i;
    }

    public int getCqi()
    {
        return mCqi;
    }

    public int getDbm()
    {
        return mRsrp;
    }

    public int getLevel()
    {
        int i;
        byte byte0;
        if(mRsrp == 0x7fffffff)
            i = 0;
        else
        if(mRsrp >= -95)
            i = 4;
        else
        if(mRsrp >= -105)
            i = 3;
        else
        if(mRsrp >= -115)
            i = 2;
        else
            i = 1;
        if(mRssnr == 0x7fffffff)
            byte0 = 0;
        else
        if(mRssnr >= 45)
            byte0 = 4;
        else
        if(mRssnr >= 10)
            byte0 = 3;
        else
        if(mRssnr >= -30)
            byte0 = 2;
        else
            byte0 = 1;
        if(mRsrp != 0x7fffffff) goto _L2; else goto _L1
_L1:
        i = byte0;
_L4:
        return i;
_L2:
        if(mRssnr != 0x7fffffff && byte0 < i)
            i = byte0;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int getRsrp()
    {
        return mRsrp;
    }

    public int getRsrq()
    {
        return mRsrq;
    }

    public int getRssnr()
    {
        return mRssnr;
    }

    public int getTimingAdvance()
    {
        return mTimingAdvance;
    }

    public int hashCode()
    {
        return mSignalStrength * 31 + mRsrp * 31 + mRsrq * 31 + mRssnr * 31 + mCqi * 31 + mTimingAdvance * 31;
    }

    public void initialize(int i, int j, int k, int l, int i1, int j1)
    {
        mSignalStrength = i;
        mRsrp = j;
        mRsrq = k;
        mRssnr = l;
        mCqi = i1;
        mTimingAdvance = j1;
    }

    public void initialize(SignalStrength signalstrength, int i)
    {
        mSignalStrength = signalstrength.getLteSignalStrength();
        mRsrp = signalstrength.getLteRsrp();
        mRsrq = signalstrength.getLteRsrq();
        mRssnr = signalstrength.getLteRssnr();
        mCqi = signalstrength.getLteCqi();
        mTimingAdvance = i;
    }

    public void setDefaultValues()
    {
        mSignalStrength = 0x7fffffff;
        mRsrp = 0x7fffffff;
        mRsrq = 0x7fffffff;
        mRssnr = 0x7fffffff;
        mCqi = 0x7fffffff;
        mTimingAdvance = 0x7fffffff;
    }

    public String toString()
    {
        return (new StringBuilder()).append("CellSignalStrengthLte: ss=").append(mSignalStrength).append(" rsrp=").append(mRsrp).append(" rsrq=").append(mRsrq).append(" rssnr=").append(mRssnr).append(" cqi=").append(mCqi).append(" ta=").append(mTimingAdvance).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        byte byte0 = -1;
        parcel.writeInt(mSignalStrength);
        int j = mRsrp;
        if(mRsrp != 0x7fffffff)
            i = -1;
        else
            i = 1;
        parcel.writeInt(i * j);
        j = mRsrq;
        if(mRsrq != 0x7fffffff)
            i = byte0;
        else
            i = 1;
        parcel.writeInt(j * i);
        parcel.writeInt(mRssnr);
        parcel.writeInt(mCqi);
        parcel.writeInt(mTimingAdvance);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CellSignalStrengthLte createFromParcel(Parcel parcel)
        {
            return new CellSignalStrengthLte(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CellSignalStrengthLte[] newArray(int i)
        {
            return new CellSignalStrengthLte[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DBG = false;
    private static final String LOG_TAG = "CellSignalStrengthLte";
    private int mCqi;
    private int mRsrp;
    private int mRsrq;
    private int mRssnr;
    private int mSignalStrength;
    private int mTimingAdvance;

}

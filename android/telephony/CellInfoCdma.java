// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.telephony:
//            CellInfo, CellIdentityCdma, CellSignalStrengthCdma, Rlog

public final class CellInfoCdma extends CellInfo
    implements Parcelable
{

    public CellInfoCdma()
    {
        mCellIdentityCdma = new CellIdentityCdma();
        mCellSignalStrengthCdma = new CellSignalStrengthCdma();
    }

    private CellInfoCdma(Parcel parcel)
    {
        super(parcel);
        mCellIdentityCdma = (CellIdentityCdma)CellIdentityCdma.CREATOR.createFromParcel(parcel);
        mCellSignalStrengthCdma = (CellSignalStrengthCdma)CellSignalStrengthCdma.CREATOR.createFromParcel(parcel);
    }

    public CellInfoCdma(CellInfoCdma cellinfocdma)
    {
        super(cellinfocdma);
        mCellIdentityCdma = cellinfocdma.mCellIdentityCdma.copy();
        mCellSignalStrengthCdma = cellinfocdma.mCellSignalStrengthCdma.copy();
    }

    protected static CellInfoCdma createFromParcelBody(Parcel parcel)
    {
        return new CellInfoCdma(parcel);
    }

    private static void log(String s)
    {
        Rlog.w("CellInfoCdma", s);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(!super.equals(obj))
            return false;
        try
        {
            obj = (CellInfoCdma)obj;
            if(mCellIdentityCdma.equals(((CellInfoCdma) (obj)).mCellIdentityCdma))
                flag = mCellSignalStrengthCdma.equals(((CellInfoCdma) (obj)).mCellSignalStrengthCdma);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        return flag;
    }

    public CellIdentityCdma getCellIdentity()
    {
        return mCellIdentityCdma;
    }

    public CellSignalStrengthCdma getCellSignalStrength()
    {
        return mCellSignalStrengthCdma;
    }

    public int hashCode()
    {
        return super.hashCode() + mCellIdentityCdma.hashCode() + mCellSignalStrengthCdma.hashCode();
    }

    public void setCellIdentity(CellIdentityCdma cellidentitycdma)
    {
        mCellIdentityCdma = cellidentitycdma;
    }

    public void setCellSignalStrength(CellSignalStrengthCdma cellsignalstrengthcdma)
    {
        mCellSignalStrengthCdma = cellsignalstrengthcdma;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("CellInfoCdma:{");
        stringbuffer.append(super.toString());
        stringbuffer.append(" ").append(mCellIdentityCdma);
        stringbuffer.append(" ").append(mCellSignalStrengthCdma);
        stringbuffer.append("}");
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i, 2);
        mCellIdentityCdma.writeToParcel(parcel, i);
        mCellSignalStrengthCdma.writeToParcel(parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CellInfoCdma createFromParcel(Parcel parcel)
        {
            parcel.readInt();
            return CellInfoCdma.createFromParcelBody(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CellInfoCdma[] newArray(int i)
        {
            return new CellInfoCdma[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DBG = false;
    private static final String LOG_TAG = "CellInfoCdma";
    private CellIdentityCdma mCellIdentityCdma;
    private CellSignalStrengthCdma mCellSignalStrengthCdma;

}

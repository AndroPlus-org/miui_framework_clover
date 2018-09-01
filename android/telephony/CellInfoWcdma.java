// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.telephony:
//            CellInfo, CellIdentityWcdma, CellSignalStrengthWcdma, Rlog

public final class CellInfoWcdma extends CellInfo
    implements Parcelable
{

    public CellInfoWcdma()
    {
        mCellIdentityWcdma = new CellIdentityWcdma();
        mCellSignalStrengthWcdma = new CellSignalStrengthWcdma();
    }

    private CellInfoWcdma(Parcel parcel)
    {
        super(parcel);
        mCellIdentityWcdma = (CellIdentityWcdma)CellIdentityWcdma.CREATOR.createFromParcel(parcel);
        mCellSignalStrengthWcdma = (CellSignalStrengthWcdma)CellSignalStrengthWcdma.CREATOR.createFromParcel(parcel);
    }

    public CellInfoWcdma(CellInfoWcdma cellinfowcdma)
    {
        super(cellinfowcdma);
        mCellIdentityWcdma = cellinfowcdma.mCellIdentityWcdma.copy();
        mCellSignalStrengthWcdma = cellinfowcdma.mCellSignalStrengthWcdma.copy();
    }

    protected static CellInfoWcdma createFromParcelBody(Parcel parcel)
    {
        return new CellInfoWcdma(parcel);
    }

    private static void log(String s)
    {
        Rlog.w("CellInfoWcdma", s);
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
            obj = (CellInfoWcdma)obj;
            if(mCellIdentityWcdma.equals(((CellInfoWcdma) (obj)).mCellIdentityWcdma))
                flag = mCellSignalStrengthWcdma.equals(((CellInfoWcdma) (obj)).mCellSignalStrengthWcdma);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        return flag;
    }

    public CellIdentityWcdma getCellIdentity()
    {
        return mCellIdentityWcdma;
    }

    public CellSignalStrengthWcdma getCellSignalStrength()
    {
        return mCellSignalStrengthWcdma;
    }

    public int hashCode()
    {
        return super.hashCode() + mCellIdentityWcdma.hashCode() + mCellSignalStrengthWcdma.hashCode();
    }

    public void setCellIdentity(CellIdentityWcdma cellidentitywcdma)
    {
        mCellIdentityWcdma = cellidentitywcdma;
    }

    public void setCellSignalStrength(CellSignalStrengthWcdma cellsignalstrengthwcdma)
    {
        mCellSignalStrengthWcdma = cellsignalstrengthwcdma;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("CellInfoWcdma:{");
        stringbuffer.append(super.toString());
        stringbuffer.append(" ").append(mCellIdentityWcdma);
        stringbuffer.append(" ").append(mCellSignalStrengthWcdma);
        stringbuffer.append("}");
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i, 4);
        mCellIdentityWcdma.writeToParcel(parcel, i);
        mCellSignalStrengthWcdma.writeToParcel(parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CellInfoWcdma createFromParcel(Parcel parcel)
        {
            parcel.readInt();
            return CellInfoWcdma.createFromParcelBody(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CellInfoWcdma[] newArray(int i)
        {
            return new CellInfoWcdma[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DBG = false;
    private static final String LOG_TAG = "CellInfoWcdma";
    private CellIdentityWcdma mCellIdentityWcdma;
    private CellSignalStrengthWcdma mCellSignalStrengthWcdma;

}

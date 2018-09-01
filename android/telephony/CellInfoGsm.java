// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.telephony:
//            CellInfo, CellIdentityGsm, CellSignalStrengthGsm, Rlog

public final class CellInfoGsm extends CellInfo
    implements Parcelable
{

    public CellInfoGsm()
    {
        mCellIdentityGsm = new CellIdentityGsm();
        mCellSignalStrengthGsm = new CellSignalStrengthGsm();
    }

    private CellInfoGsm(Parcel parcel)
    {
        super(parcel);
        mCellIdentityGsm = (CellIdentityGsm)CellIdentityGsm.CREATOR.createFromParcel(parcel);
        mCellSignalStrengthGsm = (CellSignalStrengthGsm)CellSignalStrengthGsm.CREATOR.createFromParcel(parcel);
    }

    public CellInfoGsm(CellInfoGsm cellinfogsm)
    {
        super(cellinfogsm);
        mCellIdentityGsm = cellinfogsm.mCellIdentityGsm.copy();
        mCellSignalStrengthGsm = cellinfogsm.mCellSignalStrengthGsm.copy();
    }

    protected static CellInfoGsm createFromParcelBody(Parcel parcel)
    {
        return new CellInfoGsm(parcel);
    }

    private static void log(String s)
    {
        Rlog.w("CellInfoGsm", s);
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
            obj = (CellInfoGsm)obj;
            if(mCellIdentityGsm.equals(((CellInfoGsm) (obj)).mCellIdentityGsm))
                flag = mCellSignalStrengthGsm.equals(((CellInfoGsm) (obj)).mCellSignalStrengthGsm);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        return flag;
    }

    public CellIdentityGsm getCellIdentity()
    {
        return mCellIdentityGsm;
    }

    public CellSignalStrengthGsm getCellSignalStrength()
    {
        return mCellSignalStrengthGsm;
    }

    public int hashCode()
    {
        return super.hashCode() + mCellIdentityGsm.hashCode() + mCellSignalStrengthGsm.hashCode();
    }

    public void setCellIdentity(CellIdentityGsm cellidentitygsm)
    {
        mCellIdentityGsm = cellidentitygsm;
    }

    public void setCellSignalStrength(CellSignalStrengthGsm cellsignalstrengthgsm)
    {
        mCellSignalStrengthGsm = cellsignalstrengthgsm;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("CellInfoGsm:{");
        stringbuffer.append(super.toString());
        stringbuffer.append(" ").append(mCellIdentityGsm);
        stringbuffer.append(" ").append(mCellSignalStrengthGsm);
        stringbuffer.append("}");
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i, 1);
        mCellIdentityGsm.writeToParcel(parcel, i);
        mCellSignalStrengthGsm.writeToParcel(parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CellInfoGsm createFromParcel(Parcel parcel)
        {
            parcel.readInt();
            return CellInfoGsm.createFromParcelBody(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CellInfoGsm[] newArray(int i)
        {
            return new CellInfoGsm[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DBG = false;
    private static final String LOG_TAG = "CellInfoGsm";
    private CellIdentityGsm mCellIdentityGsm;
    private CellSignalStrengthGsm mCellSignalStrengthGsm;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.telephony:
//            CellInfo, CellIdentityLte, CellSignalStrengthLte, Rlog

public final class CellInfoLte extends CellInfo
    implements Parcelable
{

    public CellInfoLte()
    {
        mCellIdentityLte = new CellIdentityLte();
        mCellSignalStrengthLte = new CellSignalStrengthLte();
    }

    private CellInfoLte(Parcel parcel)
    {
        super(parcel);
        mCellIdentityLte = (CellIdentityLte)CellIdentityLte.CREATOR.createFromParcel(parcel);
        mCellSignalStrengthLte = (CellSignalStrengthLte)CellSignalStrengthLte.CREATOR.createFromParcel(parcel);
    }

    public CellInfoLte(CellInfoLte cellinfolte)
    {
        super(cellinfolte);
        mCellIdentityLte = cellinfolte.mCellIdentityLte.copy();
        mCellSignalStrengthLte = cellinfolte.mCellSignalStrengthLte.copy();
    }

    protected static CellInfoLte createFromParcelBody(Parcel parcel)
    {
        return new CellInfoLte(parcel);
    }

    private static void log(String s)
    {
        Rlog.w("CellInfoLte", s);
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
            obj = (CellInfoLte)obj;
            if(mCellIdentityLte.equals(((CellInfoLte) (obj)).mCellIdentityLte))
                flag = mCellSignalStrengthLte.equals(((CellInfoLte) (obj)).mCellSignalStrengthLte);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        return flag;
    }

    public CellIdentityLte getCellIdentity()
    {
        return mCellIdentityLte;
    }

    public CellSignalStrengthLte getCellSignalStrength()
    {
        return mCellSignalStrengthLte;
    }

    public int hashCode()
    {
        return super.hashCode() + mCellIdentityLte.hashCode() + mCellSignalStrengthLte.hashCode();
    }

    public void setCellIdentity(CellIdentityLte cellidentitylte)
    {
        mCellIdentityLte = cellidentitylte;
    }

    public void setCellSignalStrength(CellSignalStrengthLte cellsignalstrengthlte)
    {
        mCellSignalStrengthLte = cellsignalstrengthlte;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("CellInfoLte:{");
        stringbuffer.append(super.toString());
        stringbuffer.append(" ").append(mCellIdentityLte);
        stringbuffer.append(" ").append(mCellSignalStrengthLte);
        stringbuffer.append("}");
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i, 3);
        mCellIdentityLte.writeToParcel(parcel, i);
        mCellSignalStrengthLte.writeToParcel(parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CellInfoLte createFromParcel(Parcel parcel)
        {
            parcel.readInt();
            return CellInfoLte.createFromParcelBody(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CellInfoLte[] newArray(int i)
        {
            return new CellInfoLte[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DBG = false;
    private static final String LOG_TAG = "CellInfoLte";
    private CellIdentityLte mCellIdentityLte;
    private CellSignalStrengthLte mCellSignalStrengthLte;

}

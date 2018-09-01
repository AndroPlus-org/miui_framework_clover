// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;
import miui.telephony.PhoneDebug;

// Referenced classes of package android.telephony:
//            Rlog

public final class CellIdentityLte
    implements Parcelable
{

    public CellIdentityLte()
    {
        mMcc = 0x7fffffff;
        mMnc = 0x7fffffff;
        mCi = 0x7fffffff;
        mPci = 0x7fffffff;
        mTac = 0x7fffffff;
        mEarfcn = 0x7fffffff;
    }

    public CellIdentityLte(int i, int j, int k, int l, int i1)
    {
        this(i, j, k, l, i1, 0x7fffffff);
    }

    public CellIdentityLte(int i, int j, int k, int l, int i1, int j1)
    {
        mMcc = i;
        mMnc = j;
        mCi = k;
        mPci = l;
        mTac = i1;
        mEarfcn = j1;
    }

    private CellIdentityLte(Parcel parcel)
    {
        mMcc = parcel.readInt();
        mMnc = parcel.readInt();
        mCi = parcel.readInt();
        mPci = parcel.readInt();
        mTac = parcel.readInt();
        mEarfcn = parcel.readInt();
    }

    CellIdentityLte(Parcel parcel, CellIdentityLte cellidentitylte)
    {
        this(parcel);
    }

    private CellIdentityLte(CellIdentityLte cellidentitylte)
    {
        mMcc = cellidentitylte.mMcc;
        mMnc = cellidentitylte.mMnc;
        mCi = cellidentitylte.mCi;
        mPci = cellidentitylte.mPci;
        mTac = cellidentitylte.mTac;
        mEarfcn = cellidentitylte.mEarfcn;
    }

    private static void log(String s)
    {
        Rlog.w("CellIdentityLte", s);
    }

    CellIdentityLte copy()
    {
        return new CellIdentityLte(this);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(!(obj instanceof CellIdentityLte))
            return false;
        obj = (CellIdentityLte)obj;
        if(mMcc == ((CellIdentityLte) (obj)).mMcc && mMnc == ((CellIdentityLte) (obj)).mMnc && mCi == ((CellIdentityLte) (obj)).mCi && mPci == ((CellIdentityLte) (obj)).mPci && mTac == ((CellIdentityLte) (obj)).mTac)
        {
            if(mEarfcn != ((CellIdentityLte) (obj)).mEarfcn)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public int getCi()
    {
        return mCi;
    }

    public int getEarfcn()
    {
        return mEarfcn;
    }

    public int getMcc()
    {
        return mMcc;
    }

    public int getMnc()
    {
        return mMnc;
    }

    public int getPci()
    {
        return mPci;
    }

    public int getTac()
    {
        return mTac;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(mMcc), Integer.valueOf(mMnc), Integer.valueOf(mCi), Integer.valueOf(mPci), Integer.valueOf(mTac)
        });
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("CellIdentityLte:{");
        stringbuilder.append(" mMcc=");
        stringbuilder.append(mMcc);
        stringbuilder.append(" mMnc=");
        stringbuilder.append(mMnc);
        if(PhoneDebug.VDBG)
        {
            stringbuilder.append(" mCi=");
            stringbuilder.append(mCi);
            stringbuilder.append(" mPci=");
            stringbuilder.append(mPci);
            stringbuilder.append(" mTac=");
            stringbuilder.append(mTac);
            stringbuilder.append(" mEarfcn=");
            stringbuilder.append(mEarfcn);
        }
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mMcc);
        parcel.writeInt(mMnc);
        parcel.writeInt(mCi);
        parcel.writeInt(mPci);
        parcel.writeInt(mTac);
        parcel.writeInt(mEarfcn);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CellIdentityLte createFromParcel(Parcel parcel)
        {
            return new CellIdentityLte(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CellIdentityLte[] newArray(int i)
        {
            return new CellIdentityLte[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DBG = false;
    private static final String LOG_TAG = "CellIdentityLte";
    private final int mCi;
    private final int mEarfcn;
    private final int mMcc;
    private final int mMnc;
    private final int mPci;
    private final int mTac;

}

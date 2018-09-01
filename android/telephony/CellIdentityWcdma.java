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

public final class CellIdentityWcdma
    implements Parcelable
{

    public CellIdentityWcdma()
    {
        mMcc = 0x7fffffff;
        mMnc = 0x7fffffff;
        mLac = 0x7fffffff;
        mCid = 0x7fffffff;
        mPsc = 0x7fffffff;
        mUarfcn = 0x7fffffff;
    }

    public CellIdentityWcdma(int i, int j, int k, int l, int i1)
    {
        this(i, j, k, l, i1, 0x7fffffff);
    }

    public CellIdentityWcdma(int i, int j, int k, int l, int i1, int j1)
    {
        mMcc = i;
        mMnc = j;
        mLac = k;
        mCid = l;
        mPsc = i1;
        mUarfcn = j1;
    }

    private CellIdentityWcdma(Parcel parcel)
    {
        mMcc = parcel.readInt();
        mMnc = parcel.readInt();
        mLac = parcel.readInt();
        mCid = parcel.readInt();
        mPsc = parcel.readInt();
        mUarfcn = parcel.readInt();
    }

    CellIdentityWcdma(Parcel parcel, CellIdentityWcdma cellidentitywcdma)
    {
        this(parcel);
    }

    private CellIdentityWcdma(CellIdentityWcdma cellidentitywcdma)
    {
        mMcc = cellidentitywcdma.mMcc;
        mMnc = cellidentitywcdma.mMnc;
        mLac = cellidentitywcdma.mLac;
        mCid = cellidentitywcdma.mCid;
        mPsc = cellidentitywcdma.mPsc;
        mUarfcn = cellidentitywcdma.mUarfcn;
    }

    private static void log(String s)
    {
        Rlog.w("CellIdentityWcdma", s);
    }

    CellIdentityWcdma copy()
    {
        return new CellIdentityWcdma(this);
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
        if(!(obj instanceof CellIdentityWcdma))
            return false;
        obj = (CellIdentityWcdma)obj;
        if(mMcc == ((CellIdentityWcdma) (obj)).mMcc && mMnc == ((CellIdentityWcdma) (obj)).mMnc && mLac == ((CellIdentityWcdma) (obj)).mLac && mCid == ((CellIdentityWcdma) (obj)).mCid && mPsc == ((CellIdentityWcdma) (obj)).mPsc)
        {
            if(mUarfcn != ((CellIdentityWcdma) (obj)).mUarfcn)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public int getCid()
    {
        return mCid;
    }

    public int getLac()
    {
        return mLac;
    }

    public int getMcc()
    {
        return mMcc;
    }

    public int getMnc()
    {
        return mMnc;
    }

    public int getPsc()
    {
        return mPsc;
    }

    public int getUarfcn()
    {
        return mUarfcn;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(mMcc), Integer.valueOf(mMnc), Integer.valueOf(mLac), Integer.valueOf(mCid), Integer.valueOf(mPsc)
        });
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("CellIdentityWcdma:{");
        stringbuilder.append(" mMcc=").append(mMcc);
        stringbuilder.append(" mMnc=").append(mMnc);
        if(PhoneDebug.VDBG)
        {
            stringbuilder.append(" mLac=").append(mLac);
            stringbuilder.append(" mCid=").append(mCid);
            stringbuilder.append(" mPsc=").append(mPsc);
            stringbuilder.append(" mUarfcn=").append(mUarfcn);
        }
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mMcc);
        parcel.writeInt(mMnc);
        parcel.writeInt(mLac);
        parcel.writeInt(mCid);
        parcel.writeInt(mPsc);
        parcel.writeInt(mUarfcn);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CellIdentityWcdma createFromParcel(Parcel parcel)
        {
            return new CellIdentityWcdma(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CellIdentityWcdma[] newArray(int i)
        {
            return new CellIdentityWcdma[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DBG = false;
    private static final String LOG_TAG = "CellIdentityWcdma";
    private final int mCid;
    private final int mLac;
    private final int mMcc;
    private final int mMnc;
    private final int mPsc;
    private final int mUarfcn;

}

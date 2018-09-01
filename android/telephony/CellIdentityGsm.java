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

public final class CellIdentityGsm
    implements Parcelable
{

    public CellIdentityGsm()
    {
        mMcc = 0x7fffffff;
        mMnc = 0x7fffffff;
        mLac = 0x7fffffff;
        mCid = 0x7fffffff;
        mArfcn = 0x7fffffff;
        mBsic = 0x7fffffff;
    }

    public CellIdentityGsm(int i, int j, int k, int l)
    {
        this(i, j, k, l, 0x7fffffff, 0x7fffffff);
    }

    public CellIdentityGsm(int i, int j, int k, int l, int i1, int j1)
    {
        mMcc = i;
        mMnc = j;
        mLac = k;
        mCid = l;
        mArfcn = i1;
        mBsic = j1;
    }

    private CellIdentityGsm(Parcel parcel)
    {
        mMcc = parcel.readInt();
        mMnc = parcel.readInt();
        mLac = parcel.readInt();
        mCid = parcel.readInt();
        mArfcn = parcel.readInt();
        int i = parcel.readInt();
        int j = i;
        if(i == 255)
            j = 0x7fffffff;
        mBsic = j;
    }

    CellIdentityGsm(Parcel parcel, CellIdentityGsm cellidentitygsm)
    {
        this(parcel);
    }

    private CellIdentityGsm(CellIdentityGsm cellidentitygsm)
    {
        mMcc = cellidentitygsm.mMcc;
        mMnc = cellidentitygsm.mMnc;
        mLac = cellidentitygsm.mLac;
        mCid = cellidentitygsm.mCid;
        mArfcn = cellidentitygsm.mArfcn;
        mBsic = cellidentitygsm.mBsic;
    }

    private static void log(String s)
    {
        Rlog.w("CellIdentityGsm", s);
    }

    CellIdentityGsm copy()
    {
        return new CellIdentityGsm(this);
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
        if(!(obj instanceof CellIdentityGsm))
            return false;
        obj = (CellIdentityGsm)obj;
        if(mMcc == ((CellIdentityGsm) (obj)).mMcc && mMnc == ((CellIdentityGsm) (obj)).mMnc && mLac == ((CellIdentityGsm) (obj)).mLac && mCid == ((CellIdentityGsm) (obj)).mCid && mArfcn == ((CellIdentityGsm) (obj)).mArfcn)
        {
            if(mBsic != ((CellIdentityGsm) (obj)).mBsic)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public int getArfcn()
    {
        return mArfcn;
    }

    public int getBsic()
    {
        return mBsic;
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
        return 0x7fffffff;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(mMcc), Integer.valueOf(mMnc), Integer.valueOf(mLac), Integer.valueOf(mCid)
        });
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("CellIdentityGsm:{");
        stringbuilder.append(" mMcc=").append(mMcc);
        stringbuilder.append(" mMnc=").append(mMnc);
        if(PhoneDebug.VDBG)
        {
            stringbuilder.append(" mLac=").append(mLac);
            stringbuilder.append(" mCid=").append(mCid);
            stringbuilder.append(" mArfcn=").append(mArfcn);
            stringbuilder.append(" mBsic=").append("0x").append(Integer.toHexString(mBsic));
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
        parcel.writeInt(mArfcn);
        parcel.writeInt(mBsic);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CellIdentityGsm createFromParcel(Parcel parcel)
        {
            return new CellIdentityGsm(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CellIdentityGsm[] newArray(int i)
        {
            return new CellIdentityGsm[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DBG = false;
    private static final String LOG_TAG = "CellIdentityGsm";
    private final int mArfcn;
    private final int mBsic;
    private final int mCid;
    private final int mLac;
    private final int mMcc;
    private final int mMnc;

}

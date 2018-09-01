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

public final class CellIdentityCdma
    implements Parcelable
{

    public CellIdentityCdma()
    {
        mNetworkId = 0x7fffffff;
        mSystemId = 0x7fffffff;
        mBasestationId = 0x7fffffff;
        mLongitude = 0x7fffffff;
        mLatitude = 0x7fffffff;
    }

    public CellIdentityCdma(int i, int j, int k, int l, int i1)
    {
        mNetworkId = i;
        mSystemId = j;
        mBasestationId = k;
        mLongitude = l;
        mLatitude = i1;
    }

    private CellIdentityCdma(Parcel parcel)
    {
        mNetworkId = parcel.readInt();
        mSystemId = parcel.readInt();
        mBasestationId = parcel.readInt();
        mLongitude = parcel.readInt();
        mLatitude = parcel.readInt();
    }

    CellIdentityCdma(Parcel parcel, CellIdentityCdma cellidentitycdma)
    {
        this(parcel);
    }

    private CellIdentityCdma(CellIdentityCdma cellidentitycdma)
    {
        mNetworkId = cellidentitycdma.mNetworkId;
        mSystemId = cellidentitycdma.mSystemId;
        mBasestationId = cellidentitycdma.mBasestationId;
        mLongitude = cellidentitycdma.mLongitude;
        mLatitude = cellidentitycdma.mLatitude;
    }

    private static void log(String s)
    {
        Rlog.w("CellSignalStrengthCdma", s);
    }

    CellIdentityCdma copy()
    {
        return new CellIdentityCdma(this);
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
        if(!(obj instanceof CellIdentityCdma))
            return false;
        obj = (CellIdentityCdma)obj;
        if(mNetworkId == ((CellIdentityCdma) (obj)).mNetworkId && mSystemId == ((CellIdentityCdma) (obj)).mSystemId && mBasestationId == ((CellIdentityCdma) (obj)).mBasestationId && mLatitude == ((CellIdentityCdma) (obj)).mLatitude)
        {
            if(mLongitude != ((CellIdentityCdma) (obj)).mLongitude)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public int getBasestationId()
    {
        return mBasestationId;
    }

    public int getLatitude()
    {
        return mLatitude;
    }

    public int getLongitude()
    {
        return mLongitude;
    }

    public int getNetworkId()
    {
        return mNetworkId;
    }

    public int getSystemId()
    {
        return mSystemId;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(mNetworkId), Integer.valueOf(mSystemId), Integer.valueOf(mBasestationId), Integer.valueOf(mLatitude), Integer.valueOf(mLongitude)
        });
    }

    public String toString()
    {
        if(!PhoneDebug.VDBG)
        {
            return "CellIdentityCdma:{...}";
        } else
        {
            StringBuilder stringbuilder = new StringBuilder("CellIdentityCdma:{");
            stringbuilder.append(" mNetworkId=");
            stringbuilder.append(mNetworkId);
            stringbuilder.append(" mSystemId=");
            stringbuilder.append(mSystemId);
            stringbuilder.append(" mBasestationId=");
            stringbuilder.append(mBasestationId);
            stringbuilder.append(" mLongitude=");
            stringbuilder.append(mLongitude);
            stringbuilder.append(" mLatitude=");
            stringbuilder.append(mLatitude);
            stringbuilder.append("}");
            return stringbuilder.toString();
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mNetworkId);
        parcel.writeInt(mSystemId);
        parcel.writeInt(mBasestationId);
        parcel.writeInt(mLongitude);
        parcel.writeInt(mLatitude);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CellIdentityCdma createFromParcel(Parcel parcel)
        {
            return new CellIdentityCdma(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CellIdentityCdma[] newArray(int i)
        {
            return new CellIdentityCdma[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DBG = false;
    private static final String LOG_TAG = "CellSignalStrengthCdma";
    private final int mBasestationId;
    private final int mLatitude;
    private final int mLongitude;
    private final int mNetworkId;
    private final int mSystemId;

}

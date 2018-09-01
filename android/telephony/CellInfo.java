// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.telephony:
//            CellInfoGsm, CellInfoCdma, CellInfoLte, CellInfoWcdma

public abstract class CellInfo
    implements Parcelable
{

    protected CellInfo()
    {
        mRegistered = false;
        mTimeStampType = 0;
        mTimeStamp = 0x7fffffffffffffffL;
    }

    protected CellInfo(Parcel parcel)
    {
        boolean flag = true;
        super();
        if(parcel.readInt() != 1)
            flag = false;
        mRegistered = flag;
        mTimeStampType = parcel.readInt();
        mTimeStamp = parcel.readLong();
    }

    protected CellInfo(CellInfo cellinfo)
    {
        mRegistered = cellinfo.mRegistered;
        mTimeStampType = cellinfo.mTimeStampType;
        mTimeStamp = cellinfo.mTimeStamp;
    }

    private static String timeStampTypeToString(int i)
    {
        switch(i)
        {
        default:
            return "unknown";

        case 1: // '\001'
            return "antenna";

        case 2: // '\002'
            return "modem";

        case 3: // '\003'
            return "oem_ril";

        case 4: // '\004'
            return "java_ril";
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        flag = true;
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        obj = (CellInfo)obj;
        if(mRegistered != ((CellInfo) (obj)).mRegistered || mTimeStamp != ((CellInfo) (obj)).mTimeStamp) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        i = mTimeStampType;
        j = ((CellInfo) (obj)).mTimeStampType;
        if(i != j)
            flag = false;
_L4:
        return flag;
_L2:
        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
        obj;
        return false;
    }

    public long getTimeStamp()
    {
        return mTimeStamp;
    }

    public int getTimeStampType()
    {
        return mTimeStampType;
    }

    public int hashCode()
    {
        int i;
        if(mRegistered)
            i = 0;
        else
            i = 1;
        return i * 31 + (int)(mTimeStamp / 1000L) * 31 + mTimeStampType * 31;
    }

    public boolean isRegistered()
    {
        return mRegistered;
    }

    public void setRegistered(boolean flag)
    {
        mRegistered = flag;
    }

    public void setTimeStamp(long l)
    {
        mTimeStamp = l;
    }

    public void setTimeStampType(int i)
    {
        if(i < 0 || i > 4)
            mTimeStampType = 0;
        else
            mTimeStampType = i;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        StringBuffer stringbuffer1 = stringbuffer.append("mRegistered=");
        String s;
        if(mRegistered)
            s = "YES";
        else
            s = "NO";
        stringbuffer1.append(s);
        s = timeStampTypeToString(mTimeStampType);
        stringbuffer.append(" mTimeStampType=").append(s);
        stringbuffer.append(" mTimeStamp=").append(mTimeStamp).append("ns");
        return stringbuffer.toString();
    }

    public abstract void writeToParcel(Parcel parcel, int i);

    protected void writeToParcel(Parcel parcel, int i, int j)
    {
        parcel.writeInt(j);
        if(mRegistered)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(mTimeStampType);
        parcel.writeLong(mTimeStamp);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CellInfo createFromParcel(Parcel parcel)
        {
            switch(parcel.readInt())
            {
            default:
                throw new RuntimeException("Bad CellInfo Parcel");

            case 1: // '\001'
                return CellInfoGsm.createFromParcelBody(parcel);

            case 2: // '\002'
                return CellInfoCdma.createFromParcelBody(parcel);

            case 3: // '\003'
                return CellInfoLte.createFromParcelBody(parcel);

            case 4: // '\004'
                return CellInfoWcdma.createFromParcelBody(parcel);
            }
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CellInfo[] newArray(int i)
        {
            return new CellInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int TIMESTAMP_TYPE_ANTENNA = 1;
    public static final int TIMESTAMP_TYPE_JAVA_RIL = 4;
    public static final int TIMESTAMP_TYPE_MODEM = 2;
    public static final int TIMESTAMP_TYPE_OEM_RIL = 3;
    public static final int TIMESTAMP_TYPE_UNKNOWN = 0;
    protected static final int TYPE_CDMA = 2;
    protected static final int TYPE_GSM = 1;
    protected static final int TYPE_LTE = 3;
    protected static final int TYPE_WCDMA = 4;
    private boolean mRegistered;
    private long mTimeStamp;
    private int mTimeStampType;

}

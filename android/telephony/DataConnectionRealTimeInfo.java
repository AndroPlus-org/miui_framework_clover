// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;

public class DataConnectionRealTimeInfo
    implements Parcelable
{

    public DataConnectionRealTimeInfo()
    {
        mTime = 0x7fffffffffffffffL;
        mDcPowerState = 0x7fffffff;
    }

    public DataConnectionRealTimeInfo(long l, int i)
    {
        mTime = l;
        mDcPowerState = i;
    }

    private DataConnectionRealTimeInfo(Parcel parcel)
    {
        mTime = parcel.readLong();
        mDcPowerState = parcel.readInt();
    }

    DataConnectionRealTimeInfo(Parcel parcel, DataConnectionRealTimeInfo dataconnectionrealtimeinfo)
    {
        this(parcel);
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
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        obj = (DataConnectionRealTimeInfo)obj;
        if(mTime == ((DataConnectionRealTimeInfo) (obj)).mTime)
        {
            if(mDcPowerState != ((DataConnectionRealTimeInfo) (obj)).mDcPowerState)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public int getDcPowerState()
    {
        return mDcPowerState;
    }

    public long getTime()
    {
        return mTime;
    }

    public int hashCode()
    {
        long l = 17L + mTime;
        return (int)(l + (17L * l + (long)mDcPowerState));
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("mTime=").append(mTime);
        stringbuffer.append(" mDcPowerState=").append(mDcPowerState);
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(mTime);
        parcel.writeInt(mDcPowerState);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public DataConnectionRealTimeInfo createFromParcel(Parcel parcel)
        {
            return new DataConnectionRealTimeInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DataConnectionRealTimeInfo[] newArray(int i)
        {
            return new DataConnectionRealTimeInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DC_POWER_STATE_HIGH = 3;
    public static final int DC_POWER_STATE_LOW = 1;
    public static final int DC_POWER_STATE_MEDIUM = 2;
    public static final int DC_POWER_STATE_UNKNOWN = 0x7fffffff;
    private int mDcPowerState;
    private long mTime;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;

public class NetworkQuotaInfo
    implements Parcelable
{

    public NetworkQuotaInfo()
    {
    }

    public NetworkQuotaInfo(Parcel parcel)
    {
    }

    public int describeContents()
    {
        return 0;
    }

    public long getEstimatedBytes()
    {
        return 0L;
    }

    public long getHardLimitBytes()
    {
        return -1L;
    }

    public long getSoftLimitBytes()
    {
        return -1L;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NetworkQuotaInfo createFromParcel(Parcel parcel)
        {
            return new NetworkQuotaInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NetworkQuotaInfo[] newArray(int i)
        {
            return new NetworkQuotaInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final long NO_LIMIT = -1L;

}

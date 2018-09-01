// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Objects;

public class RssiCurve
    implements Parcelable
{

    public RssiCurve(int i, int j, byte abyte0[])
    {
        this(i, j, abyte0, 25);
    }

    public RssiCurve(int i, int j, byte abyte0[], int k)
    {
        start = i;
        bucketWidth = j;
        if(abyte0 == null || abyte0.length == 0)
        {
            throw new IllegalArgumentException("rssiBuckets must be at least one element large.");
        } else
        {
            rssiBuckets = abyte0;
            activeNetworkRssiBoost = k;
            return;
        }
    }

    private RssiCurve(Parcel parcel)
    {
        start = parcel.readInt();
        bucketWidth = parcel.readInt();
        rssiBuckets = new byte[parcel.readInt()];
        parcel.readByteArray(rssiBuckets);
        activeNetworkRssiBoost = parcel.readInt();
    }

    RssiCurve(Parcel parcel, RssiCurve rssicurve)
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
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (RssiCurve)obj;
        if(start == ((RssiCurve) (obj)).start && bucketWidth == ((RssiCurve) (obj)).bucketWidth && Arrays.equals(rssiBuckets, ((RssiCurve) (obj)).rssiBuckets))
        {
            if(activeNetworkRssiBoost != ((RssiCurve) (obj)).activeNetworkRssiBoost)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(start), Integer.valueOf(bucketWidth), Integer.valueOf(activeNetworkRssiBoost)
        }) ^ Arrays.hashCode(rssiBuckets);
    }

    public byte lookupScore(int i)
    {
        return lookupScore(i, false);
    }

    public byte lookupScore(int i, boolean flag)
    {
        int j;
        j = i;
        if(flag)
            j = i + activeNetworkRssiBoost;
        j = (j - start) / bucketWidth;
        if(j >= 0) goto _L2; else goto _L1
_L1:
        i = 0;
_L4:
        return rssiBuckets[i];
_L2:
        i = j;
        if(j > rssiBuckets.length - 1)
            i = rssiBuckets.length - 1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("RssiCurve[start=").append(start).append(",bucketWidth=").append(bucketWidth).append(",activeNetworkRssiBoost=").append(activeNetworkRssiBoost);
        stringbuilder.append(",buckets=");
        for(int i = 0; i < rssiBuckets.length; i++)
        {
            stringbuilder.append(rssiBuckets[i]);
            if(i < rssiBuckets.length - 1)
                stringbuilder.append(",");
        }

        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(start);
        parcel.writeInt(bucketWidth);
        parcel.writeInt(rssiBuckets.length);
        parcel.writeByteArray(rssiBuckets);
        parcel.writeInt(activeNetworkRssiBoost);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public RssiCurve createFromParcel(Parcel parcel)
        {
            return new RssiCurve(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RssiCurve[] newArray(int i)
        {
            return new RssiCurve[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int DEFAULT_ACTIVE_NETWORK_RSSI_BOOST = 25;
    public final int activeNetworkRssiBoost;
    public final int bucketWidth;
    public final byte rssiBuckets[];
    public final int start;

}

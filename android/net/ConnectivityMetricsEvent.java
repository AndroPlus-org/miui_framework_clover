// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.BitUtils;

// Referenced classes of package android.net:
//            NetworkCapabilities

public final class ConnectivityMetricsEvent
    implements Parcelable
{

    public ConnectivityMetricsEvent()
    {
    }

    private ConnectivityMetricsEvent(Parcel parcel)
    {
        timestamp = parcel.readLong();
        transports = parcel.readLong();
        netId = parcel.readInt();
        ifname = parcel.readString();
        data = parcel.readParcelable(null);
    }

    ConnectivityMetricsEvent(Parcel parcel, ConnectivityMetricsEvent connectivitymetricsevent)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        int i = 0;
        StringBuilder stringbuilder = new StringBuilder("ConnectivityMetricsEvent(");
        stringbuilder.append(String.format("%tT.%tL", new Object[] {
            Long.valueOf(timestamp), Long.valueOf(timestamp)
        }));
        if(netId != 0)
            stringbuilder.append(", ").append(netId);
        if(ifname != null)
            stringbuilder.append(", ").append(ifname);
        int ai[] = BitUtils.unpackBits(transports);
        for(int j = ai.length; i < j; i++)
        {
            int k = ai[i];
            stringbuilder.append(", ").append(NetworkCapabilities.transportNameOf(k));
        }

        stringbuilder.append("): ").append(data.toString());
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(timestamp);
        parcel.writeLong(transports);
        parcel.writeInt(netId);
        parcel.writeString(ifname);
        parcel.writeParcelable(data, 0);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ConnectivityMetricsEvent createFromParcel(Parcel parcel)
        {
            return new ConnectivityMetricsEvent(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ConnectivityMetricsEvent[] newArray(int i)
        {
            return new ConnectivityMetricsEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public Parcelable data;
    public String ifname;
    public int netId;
    public long timestamp;
    public long transports;

}

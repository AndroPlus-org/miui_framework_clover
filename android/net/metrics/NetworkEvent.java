// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.metrics;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.android.internal.util.MessageUtils;

public final class NetworkEvent
    implements Parcelable
{
    static final class Decoder
    {

        static final SparseArray constants = MessageUtils.findMessageNames(new Class[] {
            android/net/metrics/NetworkEvent
        }, new String[] {
            "NETWORK_"
        });


        Decoder()
        {
        }
    }


    public NetworkEvent(int i, int j)
    {
        this(i, j, 0L);
    }

    public NetworkEvent(int i, int j, long l)
    {
        netId = i;
        eventType = j;
        durationMs = l;
    }

    private NetworkEvent(Parcel parcel)
    {
        netId = parcel.readInt();
        eventType = parcel.readInt();
        durationMs = parcel.readLong();
    }

    NetworkEvent(Parcel parcel, NetworkEvent networkevent)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        return String.format("NetworkEvent(%d, %s, %dms)", new Object[] {
            Integer.valueOf(netId), Decoder.constants.get(eventType), Long.valueOf(durationMs)
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(netId);
        parcel.writeInt(eventType);
        parcel.writeLong(durationMs);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NetworkEvent createFromParcel(Parcel parcel)
        {
            return new NetworkEvent(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NetworkEvent[] newArray(int i)
        {
            return new NetworkEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int NETWORK_CAPTIVE_PORTAL_FOUND = 4;
    public static final int NETWORK_CONNECTED = 1;
    public static final int NETWORK_DISCONNECTED = 7;
    public static final int NETWORK_FIRST_VALIDATION_PORTAL_FOUND = 10;
    public static final int NETWORK_FIRST_VALIDATION_SUCCESS = 8;
    public static final int NETWORK_LINGER = 5;
    public static final int NETWORK_REVALIDATION_PORTAL_FOUND = 11;
    public static final int NETWORK_REVALIDATION_SUCCESS = 9;
    public static final int NETWORK_UNLINGER = 6;
    public static final int NETWORK_VALIDATED = 2;
    public static final int NETWORK_VALIDATION_FAILED = 3;
    public final long durationMs;
    public final int eventType;
    public final int netId;

}

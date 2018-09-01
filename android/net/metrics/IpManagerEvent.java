// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.metrics;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.android.internal.util.MessageUtils;

public final class IpManagerEvent
    implements Parcelable
{
    static final class Decoder
    {

        static final SparseArray constants = MessageUtils.findMessageNames(new Class[] {
            android/net/metrics/IpManagerEvent
        }, new String[] {
            "PROVISIONING_", "COMPLETE_", "ERROR_"
        });


        Decoder()
        {
        }
    }


    public IpManagerEvent(int i, long l)
    {
        eventType = i;
        durationMs = l;
    }

    private IpManagerEvent(Parcel parcel)
    {
        eventType = parcel.readInt();
        durationMs = parcel.readLong();
    }

    IpManagerEvent(Parcel parcel, IpManagerEvent ipmanagerevent)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        return String.format("IpManagerEvent(%s, %dms)", new Object[] {
            Decoder.constants.get(eventType), Long.valueOf(durationMs)
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(eventType);
        parcel.writeLong(durationMs);
    }

    public static final int COMPLETE_LIFECYCLE = 3;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public IpManagerEvent createFromParcel(Parcel parcel)
        {
            return new IpManagerEvent(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public IpManagerEvent[] newArray(int i)
        {
            return new IpManagerEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int ERROR_INVALID_PROVISIONING = 7;
    public static final int ERROR_STARTING_IPREACHABILITYMONITOR = 6;
    public static final int ERROR_STARTING_IPV4 = 4;
    public static final int ERROR_STARTING_IPV6 = 5;
    public static final int PROVISIONING_FAIL = 2;
    public static final int PROVISIONING_OK = 1;
    public final long durationMs;
    public final int eventType;

}

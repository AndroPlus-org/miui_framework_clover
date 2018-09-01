// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.metrics;

import android.os.Parcel;
import android.os.Parcelable;

public final class DhcpClientEvent
    implements Parcelable
{

    private DhcpClientEvent(Parcel parcel)
    {
        msg = parcel.readString();
        durationMs = parcel.readInt();
    }

    DhcpClientEvent(Parcel parcel, DhcpClientEvent dhcpclientevent)
    {
        this(parcel);
    }

    public DhcpClientEvent(String s, int i)
    {
        msg = s;
        durationMs = i;
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        return String.format("DhcpClientEvent(%s, %dms)", new Object[] {
            msg, Integer.valueOf(durationMs)
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(msg);
        parcel.writeInt(durationMs);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public DhcpClientEvent createFromParcel(Parcel parcel)
        {
            return new DhcpClientEvent(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DhcpClientEvent[] newArray(int i)
        {
            return new DhcpClientEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String INITIAL_BOUND = "InitialBoundState";
    public static final String RENEWING_BOUND = "RenewingBoundState";
    public final int durationMs;
    public final String msg;

}

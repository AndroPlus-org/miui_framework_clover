// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.metrics;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.android.internal.util.MessageUtils;

public final class IpReachabilityEvent
    implements Parcelable
{
    static final class Decoder
    {

        static final SparseArray constants = MessageUtils.findMessageNames(new Class[] {
            android/net/metrics/IpReachabilityEvent
        }, new String[] {
            "PROBE", "PROVISIONING_", "NUD_"
        });


        Decoder()
        {
        }
    }


    public IpReachabilityEvent(int i)
    {
        eventType = i;
    }

    private IpReachabilityEvent(Parcel parcel)
    {
        eventType = parcel.readInt();
    }

    IpReachabilityEvent(Parcel parcel, IpReachabilityEvent ipreachabilityevent)
    {
        this(parcel);
    }

    public static int nudFailureEventType(boolean flag, boolean flag1)
    {
        if(flag)
        {
            char c;
            if(flag1)
                c = '\u0300';
            else
                c = '\u0200';
            return c;
        }
        char c1;
        if(flag1)
            c1 = '\u0500';
        else
            c1 = '\u0400';
        return c1;
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        int i = eventType;
        int j = eventType;
        return String.format("IpReachabilityEvent(%s:%02x)", new Object[] {
            (String)Decoder.constants.get(i & 0xff00), Integer.valueOf(j & 0xff)
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(eventType);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public IpReachabilityEvent createFromParcel(Parcel parcel)
        {
            return new IpReachabilityEvent(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public IpReachabilityEvent[] newArray(int i)
        {
            return new IpReachabilityEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int NUD_FAILED = 512;
    public static final int NUD_FAILED_ORGANIC = 1024;
    public static final int PROBE = 256;
    public static final int PROVISIONING_LOST = 768;
    public static final int PROVISIONING_LOST_ORGANIC = 1280;
    public final int eventType;

}

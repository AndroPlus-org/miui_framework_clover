// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.metrics;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.android.internal.util.MessageUtils;

public final class ValidationProbeEvent
    implements Parcelable
{
    static final class Decoder
    {

        static final SparseArray constants = MessageUtils.findMessageNames(new Class[] {
            android/net/metrics/ValidationProbeEvent
        }, new String[] {
            "PROBE_", "FIRST_", "REVALIDATION"
        });


        Decoder()
        {
        }
    }


    public ValidationProbeEvent()
    {
    }

    private ValidationProbeEvent(Parcel parcel)
    {
        durationMs = parcel.readLong();
        probeType = parcel.readInt();
        returnCode = parcel.readInt();
    }

    ValidationProbeEvent(Parcel parcel, ValidationProbeEvent validationprobeevent)
    {
        this(parcel);
    }

    public static String getProbeName(int i)
    {
        return (String)Decoder.constants.get(i & 0xff, "PROBE_???");
    }

    public static String getValidationStage(int i)
    {
        return (String)Decoder.constants.get(0xff00 & i, "UNKNOWN");
    }

    public static int makeProbeType(int i, boolean flag)
    {
        char c;
        if(flag)
            c = '\u0100';
        else
            c = '\u0200';
        return c | i & 0xff;
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        return String.format("ValidationProbeEvent(%s:%d %s, %dms)", new Object[] {
            getProbeName(probeType), Integer.valueOf(returnCode), getValidationStage(probeType), Long.valueOf(durationMs)
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(durationMs);
        parcel.writeInt(probeType);
        parcel.writeInt(returnCode);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ValidationProbeEvent createFromParcel(Parcel parcel)
        {
            return new ValidationProbeEvent(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ValidationProbeEvent[] newArray(int i)
        {
            return new ValidationProbeEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DNS_FAILURE = 0;
    public static final int DNS_SUCCESS = 1;
    private static final int FIRST_VALIDATION = 256;
    public static final int PROBE_DNS = 0;
    public static final int PROBE_FALLBACK = 4;
    public static final int PROBE_HTTP = 1;
    public static final int PROBE_HTTPS = 2;
    public static final int PROBE_PAC = 3;
    private static final int REVALIDATION = 512;
    public long durationMs;
    public int probeType;
    public int returnCode;

}

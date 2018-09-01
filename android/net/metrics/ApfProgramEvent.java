// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.metrics;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseArray;
import com.android.internal.util.MessageUtils;
import java.util.*;

public final class ApfProgramEvent
    implements Parcelable
{
    static final class Decoder
    {

        static final SparseArray constants = MessageUtils.findMessageNames(new Class[] {
            android/net/metrics/ApfProgramEvent
        }, new String[] {
            "FLAG_"
        });


        Decoder()
        {
        }
    }


    public ApfProgramEvent()
    {
    }

    private ApfProgramEvent(Parcel parcel)
    {
        lifetime = parcel.readLong();
        actualLifetime = parcel.readLong();
        filteredRas = parcel.readInt();
        currentRas = parcel.readInt();
        programLength = parcel.readInt();
        flags = parcel.readInt();
    }

    ApfProgramEvent(Parcel parcel, ApfProgramEvent apfprogramevent)
    {
        this(parcel);
    }

    public static int flagsFor(boolean flag, boolean flag1)
    {
        byte byte0 = 0;
        if(flag)
            byte0 = 2;
        int i = byte0;
        if(flag1)
            i = byte0 | 1;
        return i;
    }

    private static String namesOf(int i)
    {
        ArrayList arraylist = new ArrayList(Integer.bitCount(i));
        BitSet bitset = BitSet.valueOf(new long[] {
            (long)(0x7fffffff & i)
        });
        for(i = bitset.nextSetBit(0); i >= 0; i = bitset.nextSetBit(i + 1))
            arraylist.add((String)Decoder.constants.get(i));

        return TextUtils.join("|", arraylist);
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        String s;
        if(lifetime < 0x7fffffffffffffffL)
            s = (new StringBuilder()).append(lifetime).append("s").toString();
        else
            s = "forever";
        return String.format("ApfProgramEvent(%d/%d RAs %dB %ds/%s %s)", new Object[] {
            Integer.valueOf(filteredRas), Integer.valueOf(currentRas), Integer.valueOf(programLength), Long.valueOf(actualLifetime), s, namesOf(flags)
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(lifetime);
        parcel.writeLong(actualLifetime);
        parcel.writeInt(filteredRas);
        parcel.writeInt(currentRas);
        parcel.writeInt(programLength);
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ApfProgramEvent createFromParcel(Parcel parcel)
        {
            return new ApfProgramEvent(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ApfProgramEvent[] newArray(int i)
        {
            return new ApfProgramEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FLAG_HAS_IPV4_ADDRESS = 1;
    public static final int FLAG_MULTICAST_FILTER_ON = 0;
    public long actualLifetime;
    public int currentRas;
    public int filteredRas;
    public int flags;
    public long lifetime;
    public int programLength;

}

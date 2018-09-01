// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.metrics;

import android.os.Parcel;
import android.os.Parcelable;

public final class ApfStats
    implements Parcelable
{

    public ApfStats()
    {
    }

    private ApfStats(Parcel parcel)
    {
        durationMs = parcel.readLong();
        receivedRas = parcel.readInt();
        matchingRas = parcel.readInt();
        droppedRas = parcel.readInt();
        zeroLifetimeRas = parcel.readInt();
        parseErrors = parcel.readInt();
        programUpdates = parcel.readInt();
        programUpdatesAll = parcel.readInt();
        programUpdatesAllowingMulticast = parcel.readInt();
        maxProgramSize = parcel.readInt();
    }

    ApfStats(Parcel parcel, ApfStats apfstats)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        return (new StringBuilder("ApfStats(")).append(String.format("%dms ", new Object[] {
            Long.valueOf(durationMs)
        })).append(String.format("%dB RA: {", new Object[] {
            Integer.valueOf(maxProgramSize)
        })).append(String.format("%d received, ", new Object[] {
            Integer.valueOf(receivedRas)
        })).append(String.format("%d matching, ", new Object[] {
            Integer.valueOf(matchingRas)
        })).append(String.format("%d dropped, ", new Object[] {
            Integer.valueOf(droppedRas)
        })).append(String.format("%d zero lifetime, ", new Object[] {
            Integer.valueOf(zeroLifetimeRas)
        })).append(String.format("%d parse errors}, ", new Object[] {
            Integer.valueOf(parseErrors)
        })).append(String.format("updates: {all: %d, RAs: %d, allow multicast: %d})", new Object[] {
            Integer.valueOf(programUpdatesAll), Integer.valueOf(programUpdates), Integer.valueOf(programUpdatesAllowingMulticast)
        })).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(durationMs);
        parcel.writeInt(receivedRas);
        parcel.writeInt(matchingRas);
        parcel.writeInt(droppedRas);
        parcel.writeInt(zeroLifetimeRas);
        parcel.writeInt(parseErrors);
        parcel.writeInt(programUpdates);
        parcel.writeInt(programUpdatesAll);
        parcel.writeInt(programUpdatesAllowingMulticast);
        parcel.writeInt(maxProgramSize);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ApfStats createFromParcel(Parcel parcel)
        {
            return new ApfStats(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ApfStats[] newArray(int i)
        {
            return new ApfStats[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public int droppedRas;
    public long durationMs;
    public int matchingRas;
    public int maxProgramSize;
    public int parseErrors;
    public int programUpdates;
    public int programUpdatesAll;
    public int programUpdatesAllowingMulticast;
    public int receivedRas;
    public int zeroLifetimeRas;

}

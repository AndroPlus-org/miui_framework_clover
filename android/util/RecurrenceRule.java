// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.*;
import java.net.ProtocolException;
import java.time.*;
import java.util.Iterator;
import java.util.Objects;

// Referenced classes of package android.util:
//            BackupUtils, Pair, Log

public class RecurrenceRule
    implements Parcelable
{
    private class NonrecurringIterator
        implements Iterator
    {

        public boolean hasNext()
        {
            return hasNext;
        }

        public Pair next()
        {
            hasNext = false;
            return new Pair(start, end);
        }

        public volatile Object next()
        {
            return next();
        }

        boolean hasNext;
        final RecurrenceRule this$0;

        public NonrecurringIterator()
        {
            boolean flag = false;
            this$0 = RecurrenceRule.this;
            super();
            boolean flag1 = flag;
            if(start != null)
            {
                flag1 = flag;
                if(end != null)
                    flag1 = true;
            }
            hasNext = flag1;
        }
    }

    private class RecurringIterator
        implements Iterator
    {

        private ZonedDateTime roundBoundaryTime(ZonedDateTime zoneddatetime)
        {
            if(isMonthly() && zoneddatetime.getDayOfMonth() < start.getDayOfMonth())
                return ZonedDateTime.of(zoneddatetime.toLocalDate(), LocalTime.MAX, start.getZone());
            else
                return zoneddatetime;
        }

        private void updateCycle()
        {
            cycleStart = roundBoundaryTime(start.plus(period.multipliedBy(i)));
            cycleEnd = roundBoundaryTime(start.plus(period.multipliedBy(i + 1)));
        }

        public boolean hasNext()
        {
            boolean flag;
            if(cycleStart.toEpochSecond() >= start.toEpochSecond())
                flag = true;
            else
                flag = false;
            return flag;
        }

        public Pair next()
        {
            Log.d("RecurrenceRule", (new StringBuilder()).append("Cycle ").append(i).append(" from ").append(cycleStart).append(" to ").append(cycleEnd).toString());
            Pair pair = new Pair(cycleStart, cycleEnd);
            i = i - 1;
            updateCycle();
            return pair;
        }

        public volatile Object next()
        {
            return next();
        }

        ZonedDateTime cycleEnd;
        ZonedDateTime cycleStart;
        int i;
        final RecurrenceRule this$0;

        public RecurringIterator()
        {
            this$0 = RecurrenceRule.this;
            super();
            if(end != null)
                recurrencerule = end;
            else
                recurrencerule = ZonedDateTime.now(RecurrenceRule.sClock).withZoneSameInstant(start.getZone());
            Log.d("RecurrenceRule", (new StringBuilder()).append("Resolving using anchor ").append(RecurrenceRule.this).toString());
            updateCycle();
            for(; toEpochSecond() > cycleEnd.toEpochSecond(); updateCycle())
                i = i + 1;

            for(; toEpochSecond() <= cycleStart.toEpochSecond(); updateCycle())
                i = i - 1;

        }
    }


    private RecurrenceRule(Parcel parcel)
    {
        start = convertZonedDateTime(parcel.readString());
        end = convertZonedDateTime(parcel.readString());
        period = convertPeriod(parcel.readString());
    }

    RecurrenceRule(Parcel parcel, RecurrenceRule recurrencerule)
    {
        this(parcel);
    }

    public RecurrenceRule(DataInputStream datainputstream)
        throws IOException
    {
        int i;
        i = datainputstream.readInt();
        i;
        JVM INSTR tableswitch 0 0: default 28
    //                   0 55;
           goto _L1 _L2
_L1:
        throw new ProtocolException((new StringBuilder()).append("Unknown version ").append(i).toString());
_L2:
        start = convertZonedDateTime(BackupUtils.readString(datainputstream));
        end = convertZonedDateTime(BackupUtils.readString(datainputstream));
        period = convertPeriod(BackupUtils.readString(datainputstream));
        if(true) goto _L1; else goto _L3
_L3:
    }

    public RecurrenceRule(ZonedDateTime zoneddatetime, ZonedDateTime zoneddatetime1, Period period1)
    {
        start = zoneddatetime;
        end = zoneddatetime1;
        period = period1;
    }

    public static RecurrenceRule buildNever()
    {
        return new RecurrenceRule(null, null, null);
    }

    public static RecurrenceRule buildRecurringMonthly(int i, ZoneId zoneid)
    {
        return new RecurrenceRule(ZonedDateTime.of(ZonedDateTime.now(sClock).withZoneSameInstant(zoneid).toLocalDate().minusYears(1L).withMonth(1).withDayOfMonth(i), LocalTime.MIDNIGHT, zoneid), null, Period.ofMonths(1));
    }

    public static String convertPeriod(Period period1)
    {
        String s = null;
        if(period1 != null)
            s = period1.toString();
        return s;
    }

    public static Period convertPeriod(String s)
    {
        Period period1 = null;
        if(s != null)
            period1 = Period.parse(s);
        return period1;
    }

    public static String convertZonedDateTime(ZonedDateTime zoneddatetime)
    {
        String s = null;
        if(zoneddatetime != null)
            s = zoneddatetime.toString();
        return s;
    }

    public static ZonedDateTime convertZonedDateTime(String s)
    {
        ZonedDateTime zoneddatetime = null;
        if(s != null)
            zoneddatetime = ZonedDateTime.parse(s);
        return zoneddatetime;
    }

    public Iterator cycleIterator()
    {
        if(period != null)
            return new RecurringIterator();
        else
            return new NonrecurringIterator();
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof RecurrenceRule)
        {
            obj = (RecurrenceRule)obj;
            boolean flag1 = flag;
            if(Objects.equals(start, ((RecurrenceRule) (obj)).start))
            {
                flag1 = flag;
                if(Objects.equals(end, ((RecurrenceRule) (obj)).end))
                    flag1 = Objects.equals(period, ((RecurrenceRule) (obj)).period);
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            start, end, period
        });
    }

    public boolean isMonthly()
    {
        boolean flag = true;
        if(start != null && period != null && period.getYears() == 0 && period.getMonths() == 1)
        {
            if(period.getDays() != 0)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public String toString()
    {
        return (new StringBuilder("RecurrenceRule{")).append("start=").append(start).append(" end=").append(end).append(" period=").append(period).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(convertZonedDateTime(start));
        parcel.writeString(convertZonedDateTime(end));
        parcel.writeString(convertPeriod(period));
    }

    public void writeToStream(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeInt(0);
        BackupUtils.writeString(dataoutputstream, convertZonedDateTime(start));
        BackupUtils.writeString(dataoutputstream, convertZonedDateTime(end));
        BackupUtils.writeString(dataoutputstream, convertPeriod(period));
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public RecurrenceRule createFromParcel(Parcel parcel)
        {
            return new RecurrenceRule(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RecurrenceRule[] newArray(int i)
        {
            return new RecurrenceRule[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DEBUG = true;
    private static final String TAG = "RecurrenceRule";
    private static final int VERSION_INIT = 0;
    public static Clock sClock = Clock.systemDefaultZone();
    public final ZonedDateTime end;
    public final Period period;
    public final ZonedDateTime start;

}

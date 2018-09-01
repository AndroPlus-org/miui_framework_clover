// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.RecurrenceRule;
import com.android.internal.util.Preconditions;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.Iterator;

public final class SubscriptionPlan
    implements Parcelable
{
    public static class Builder
    {

        public static Builder createNonrecurring(ZonedDateTime zoneddatetime, ZonedDateTime zoneddatetime1)
        {
            if(!zoneddatetime1.isAfter(zoneddatetime))
                throw new IllegalArgumentException((new StringBuilder()).append("End ").append(zoneddatetime1).append(" isn't after start ").append(zoneddatetime).toString());
            else
                return new Builder(zoneddatetime, zoneddatetime1, null);
        }

        public static Builder createRecurringDaily(ZonedDateTime zoneddatetime)
        {
            return new Builder(zoneddatetime, null, Period.ofDays(1));
        }

        public static Builder createRecurringMonthly(ZonedDateTime zoneddatetime)
        {
            return new Builder(zoneddatetime, null, Period.ofMonths(1));
        }

        public static Builder createRecurringWeekly(ZonedDateTime zoneddatetime)
        {
            return new Builder(zoneddatetime, null, Period.ofDays(7));
        }

        public SubscriptionPlan build()
        {
            return plan;
        }

        public Builder setDataLimit(long l, int i)
        {
            if(l < 0L)
                throw new IllegalArgumentException("Limit bytes must be positive");
            if(i < 0)
            {
                throw new IllegalArgumentException("Limit behavior must be defined");
            } else
            {
                SubscriptionPlan._2D_set1(plan, l);
                SubscriptionPlan._2D_set0(plan, i);
                return this;
            }
        }

        public Builder setDataUsage(long l, long l1)
        {
            if(l < 0L)
                throw new IllegalArgumentException("Usage bytes must be positive");
            if(l1 < 0L)
            {
                throw new IllegalArgumentException("Usage time must be positive");
            } else
            {
                SubscriptionPlan._2D_set2(plan, l);
                SubscriptionPlan._2D_set3(plan, l1);
                return this;
            }
        }

        public Builder setSummary(CharSequence charsequence)
        {
            SubscriptionPlan._2D_set4(plan, charsequence);
            return this;
        }

        public Builder setTitle(CharSequence charsequence)
        {
            SubscriptionPlan._2D_set5(plan, charsequence);
            return this;
        }

        private final SubscriptionPlan plan;

        public Builder(ZonedDateTime zoneddatetime, ZonedDateTime zoneddatetime1, Period period)
        {
            plan = new SubscriptionPlan(new RecurrenceRule(zoneddatetime, zoneddatetime1, period), null);
        }
    }


    static int _2D_set0(SubscriptionPlan subscriptionplan, int i)
    {
        subscriptionplan.dataLimitBehavior = i;
        return i;
    }

    static long _2D_set1(SubscriptionPlan subscriptionplan, long l)
    {
        subscriptionplan.dataLimitBytes = l;
        return l;
    }

    static long _2D_set2(SubscriptionPlan subscriptionplan, long l)
    {
        subscriptionplan.dataUsageBytes = l;
        return l;
    }

    static long _2D_set3(SubscriptionPlan subscriptionplan, long l)
    {
        subscriptionplan.dataUsageTime = l;
        return l;
    }

    static CharSequence _2D_set4(SubscriptionPlan subscriptionplan, CharSequence charsequence)
    {
        subscriptionplan.summary = charsequence;
        return charsequence;
    }

    static CharSequence _2D_set5(SubscriptionPlan subscriptionplan, CharSequence charsequence)
    {
        subscriptionplan.title = charsequence;
        return charsequence;
    }

    private SubscriptionPlan(Parcel parcel)
    {
        dataLimitBytes = -1L;
        dataLimitBehavior = -1;
        dataUsageBytes = -1L;
        dataUsageTime = -1L;
        cycleRule = (RecurrenceRule)parcel.readParcelable(null);
        title = parcel.readCharSequence();
        summary = parcel.readCharSequence();
        dataLimitBytes = parcel.readLong();
        dataLimitBehavior = parcel.readInt();
        dataUsageBytes = parcel.readLong();
        dataUsageTime = parcel.readLong();
    }

    SubscriptionPlan(Parcel parcel, SubscriptionPlan subscriptionplan)
    {
        this(parcel);
    }

    private SubscriptionPlan(RecurrenceRule recurrencerule)
    {
        dataLimitBytes = -1L;
        dataLimitBehavior = -1;
        dataUsageBytes = -1L;
        dataUsageTime = -1L;
        cycleRule = (RecurrenceRule)Preconditions.checkNotNull(recurrencerule);
    }

    SubscriptionPlan(RecurrenceRule recurrencerule, SubscriptionPlan subscriptionplan)
    {
        this(recurrencerule);
    }

    public Iterator cycleIterator()
    {
        return cycleRule.cycleIterator();
    }

    public int describeContents()
    {
        return 0;
    }

    public RecurrenceRule getCycleRule()
    {
        return cycleRule;
    }

    public int getDataLimitBehavior()
    {
        return dataLimitBehavior;
    }

    public long getDataLimitBytes()
    {
        return dataLimitBytes;
    }

    public long getDataUsageBytes()
    {
        return dataUsageBytes;
    }

    public long getDataUsageTime()
    {
        return dataUsageTime;
    }

    public CharSequence getSummary()
    {
        return summary;
    }

    public CharSequence getTitle()
    {
        return title;
    }

    public String toString()
    {
        return (new StringBuilder("SubscriptionPlan{")).append("cycleRule=").append(cycleRule).append(" title=").append(title).append(" summary=").append(summary).append(" dataLimitBytes=").append(dataLimitBytes).append(" dataLimitBehavior=").append(dataLimitBehavior).append(" dataUsageBytes=").append(dataUsageBytes).append(" dataUsageTime=").append(dataUsageTime).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(cycleRule, i);
        parcel.writeCharSequence(title);
        parcel.writeCharSequence(summary);
        parcel.writeLong(dataLimitBytes);
        parcel.writeInt(dataLimitBehavior);
        parcel.writeLong(dataUsageBytes);
        parcel.writeLong(dataUsageTime);
    }

    public static final long BYTES_UNKNOWN = -1L;
    public static final long BYTES_UNLIMITED = 0x7fffffffffffffffL;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SubscriptionPlan createFromParcel(Parcel parcel)
        {
            return new SubscriptionPlan(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SubscriptionPlan[] newArray(int i)
        {
            return new SubscriptionPlan[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int LIMIT_BEHAVIOR_BILLED = 1;
    public static final int LIMIT_BEHAVIOR_DISABLED = 0;
    public static final int LIMIT_BEHAVIOR_THROTTLED = 2;
    public static final int LIMIT_BEHAVIOR_UNKNOWN = -1;
    public static final long TIME_UNKNOWN = -1L;
    private final RecurrenceRule cycleRule;
    private int dataLimitBehavior;
    private long dataLimitBytes;
    private long dataUsageBytes;
    private long dataUsageTime;
    private CharSequence summary;
    private CharSequence title;

}

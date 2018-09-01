// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.BackupUtils;
import android.util.RecurrenceRule;
import com.android.internal.util.Preconditions;
import java.io.*;
import java.time.ZoneId;
import java.util.Iterator;
import java.util.Objects;

// Referenced classes of package android.net:
//            NetworkTemplate

public class NetworkPolicy
    implements Parcelable, Comparable
{

    public NetworkPolicy(NetworkTemplate networktemplate, int i, String s, long l, long l1, 
            long l2, long l3, boolean flag, boolean flag1)
    {
        this(networktemplate, buildRule(i, ZoneId.of(s)), l, l1, l2, l3, flag, flag1);
    }

    public NetworkPolicy(NetworkTemplate networktemplate, int i, String s, long l, long l1, 
            boolean flag)
    {
        this(networktemplate, i, s, l, l1, -1L, -1L, flag, false);
    }

    public NetworkPolicy(NetworkTemplate networktemplate, RecurrenceRule recurrencerule, long l, long l1, long l2, long l3, boolean flag, boolean flag1)
    {
        warningBytes = -1L;
        limitBytes = -1L;
        lastWarningSnooze = -1L;
        lastLimitSnooze = -1L;
        metered = true;
        inferred = false;
        template = (NetworkTemplate)Preconditions.checkNotNull(networktemplate, "missing NetworkTemplate");
        cycleRule = (RecurrenceRule)Preconditions.checkNotNull(recurrencerule, "missing RecurrenceRule");
        warningBytes = l;
        limitBytes = l1;
        lastWarningSnooze = l2;
        lastLimitSnooze = l3;
        metered = flag;
        inferred = flag1;
    }

    private NetworkPolicy(Parcel parcel)
    {
        boolean flag = true;
        super();
        warningBytes = -1L;
        limitBytes = -1L;
        lastWarningSnooze = -1L;
        lastLimitSnooze = -1L;
        metered = true;
        inferred = false;
        template = (NetworkTemplate)parcel.readParcelable(null);
        cycleRule = (RecurrenceRule)parcel.readParcelable(null);
        warningBytes = parcel.readLong();
        limitBytes = parcel.readLong();
        lastWarningSnooze = parcel.readLong();
        lastLimitSnooze = parcel.readLong();
        boolean flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        metered = flag1;
        if(parcel.readInt() != 0)
            flag1 = flag;
        else
            flag1 = false;
        inferred = flag1;
    }

    NetworkPolicy(Parcel parcel, NetworkPolicy networkpolicy)
    {
        this(parcel);
    }

    public static RecurrenceRule buildRule(int i, ZoneId zoneid)
    {
        if(i != -1)
            return RecurrenceRule.buildRecurringMonthly(i, zoneid);
        else
            return RecurrenceRule.buildNever();
    }

    public static NetworkPolicy getNetworkPolicyFromBackup(DataInputStream datainputstream)
        throws IOException, android.util.BackupUtils.BadVersionException
    {
        int i = datainputstream.readInt();
        NetworkTemplate networktemplate1;
        RecurrenceRule recurrencerule;
        long l1;
        long l3;
        long l5;
        long l7;
        switch(i)
        {
        default:
            throw new android.util.BackupUtils.BadVersionException((new StringBuilder()).append("Unknown backup version: ").append(i).toString());

        case 1: // '\001'
            NetworkTemplate networktemplate = NetworkTemplate.getNetworkTemplateFromBackup(datainputstream);
            int j = datainputstream.readInt();
            String s = BackupUtils.readString(datainputstream);
            long l = datainputstream.readLong();
            long l2 = datainputstream.readLong();
            long l4 = datainputstream.readLong();
            long l6 = datainputstream.readLong();
            boolean flag;
            boolean flag2;
            if(datainputstream.readInt() == 1)
                flag = true;
            else
                flag = false;
            if(datainputstream.readInt() == 1)
                flag2 = true;
            else
                flag2 = false;
            return new NetworkPolicy(networktemplate, j, s, l, l2, l4, l6, flag, flag2);

        case 2: // '\002'
            networktemplate1 = NetworkTemplate.getNetworkTemplateFromBackup(datainputstream);
            recurrencerule = new RecurrenceRule(datainputstream);
            l7 = datainputstream.readLong();
            l1 = datainputstream.readLong();
            l3 = datainputstream.readLong();
            l5 = datainputstream.readLong();
            break;
        }
        boolean flag1;
        boolean flag3;
        if(datainputstream.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        if(datainputstream.readInt() == 1)
            flag3 = true;
        else
            flag3 = false;
        return new NetworkPolicy(networktemplate1, recurrencerule, l7, l1, l3, l5, flag1, flag3);
    }

    public void clearSnooze()
    {
        lastWarningSnooze = -1L;
        lastLimitSnooze = -1L;
    }

    public int compareTo(NetworkPolicy networkpolicy)
    {
        if(networkpolicy == null || networkpolicy.limitBytes == -1L)
            return -1;
        return limitBytes != -1L && networkpolicy.limitBytes >= limitBytes ? 0 : 1;
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((NetworkPolicy)obj);
    }

    public Iterator cycleIterator()
    {
        return cycleRule.cycleIterator();
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof NetworkPolicy)
        {
            obj = (NetworkPolicy)obj;
            boolean flag1 = flag;
            if(warningBytes == ((NetworkPolicy) (obj)).warningBytes)
            {
                flag1 = flag;
                if(limitBytes == ((NetworkPolicy) (obj)).limitBytes)
                {
                    flag1 = flag;
                    if(lastWarningSnooze == ((NetworkPolicy) (obj)).lastWarningSnooze)
                    {
                        flag1 = flag;
                        if(lastLimitSnooze == ((NetworkPolicy) (obj)).lastLimitSnooze)
                        {
                            flag1 = flag;
                            if(metered == ((NetworkPolicy) (obj)).metered)
                            {
                                flag1 = flag;
                                if(inferred == ((NetworkPolicy) (obj)).inferred)
                                {
                                    flag1 = flag;
                                    if(Objects.equals(template, ((NetworkPolicy) (obj)).template))
                                        flag1 = Objects.equals(cycleRule, ((NetworkPolicy) (obj)).cycleRule);
                                }
                            }
                        }
                    }
                }
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    public byte[] getBytesForBackup()
        throws IOException
    {
        boolean flag = true;
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);
        dataoutputstream.writeInt(2);
        dataoutputstream.write(template.getBytesForBackup());
        cycleRule.writeToStream(dataoutputstream);
        dataoutputstream.writeLong(warningBytes);
        dataoutputstream.writeLong(limitBytes);
        dataoutputstream.writeLong(lastWarningSnooze);
        dataoutputstream.writeLong(lastLimitSnooze);
        int i;
        if(metered)
            i = 1;
        else
            i = 0;
        dataoutputstream.writeInt(i);
        if(inferred)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        dataoutputstream.writeInt(i);
        return bytearrayoutputstream.toByteArray();
    }

    public boolean hasCycle()
    {
        return cycleRule.cycleIterator().hasNext();
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            template, cycleRule, Long.valueOf(warningBytes), Long.valueOf(limitBytes), Long.valueOf(lastWarningSnooze), Long.valueOf(lastLimitSnooze), Boolean.valueOf(metered), Boolean.valueOf(inferred)
        });
    }

    public boolean isOverLimit(long l)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(limitBytes != -1L)
        {
            flag1 = flag;
            if(l + 3000L >= limitBytes)
                flag1 = true;
        }
        return flag1;
    }

    public boolean isOverWarning(long l)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(warningBytes != -1L)
        {
            flag1 = flag;
            if(l >= warningBytes)
                flag1 = true;
        }
        return flag1;
    }

    public String toString()
    {
        return (new StringBuilder("NetworkPolicy{")).append("template=").append(template).append(" cycleRule=").append(cycleRule).append(" warningBytes=").append(warningBytes).append(" limitBytes=").append(limitBytes).append(" lastWarningSnooze=").append(lastWarningSnooze).append(" lastLimitSnooze=").append(lastLimitSnooze).append(" metered=").append(metered).append(" inferred=").append(inferred).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeParcelable(template, i);
        parcel.writeParcelable(cycleRule, i);
        parcel.writeLong(warningBytes);
        parcel.writeLong(limitBytes);
        parcel.writeLong(lastWarningSnooze);
        parcel.writeLong(lastLimitSnooze);
        if(metered)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(inferred)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NetworkPolicy createFromParcel(Parcel parcel)
        {
            return new NetworkPolicy(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NetworkPolicy[] newArray(int i)
        {
            return new NetworkPolicy[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int CYCLE_NONE = -1;
    private static final long DEFAULT_MTU = 1500L;
    public static final long LIMIT_DISABLED = -1L;
    public static final long SNOOZE_NEVER = -1L;
    private static final int VERSION_INIT = 1;
    private static final int VERSION_RULE = 2;
    public static final long WARNING_DISABLED = -1L;
    public RecurrenceRule cycleRule;
    public boolean inferred;
    public long lastLimitSnooze;
    public long lastWarningSnooze;
    public long limitBytes;
    public boolean metered;
    public NetworkTemplate template;
    public long warningBytes;

}

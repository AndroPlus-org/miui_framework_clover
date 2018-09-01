// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.usage;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
import java.util.Objects;

// Referenced classes of package android.app.usage:
//            UsageStats

public final class CacheQuotaHint
    implements Parcelable
{
    public static final class Builder
    {

        static long _2D_get0(Builder builder)
        {
            return builder.mQuota;
        }

        static int _2D_get1(Builder builder)
        {
            return builder.mUid;
        }

        static UsageStats _2D_get2(Builder builder)
        {
            return builder.mUsageStats;
        }

        static String _2D_get3(Builder builder)
        {
            return builder.mUuid;
        }

        public CacheQuotaHint build()
        {
            return new CacheQuotaHint(this);
        }

        public Builder setQuota(long l)
        {
            boolean flag;
            if(l >= -1L)
                flag = true;
            else
                flag = false;
            Preconditions.checkArgument(flag);
            mQuota = l;
            return this;
        }

        public Builder setUid(int i)
        {
            Preconditions.checkArgumentNonnegative(i, "Proposed uid was negative.");
            mUid = i;
            return this;
        }

        public Builder setUsageStats(UsageStats usagestats)
        {
            mUsageStats = usagestats;
            return this;
        }

        public Builder setVolumeUuid(String s)
        {
            mUuid = s;
            return this;
        }

        private long mQuota;
        private int mUid;
        private UsageStats mUsageStats;
        private String mUuid;

        public Builder()
        {
        }

        public Builder(CacheQuotaHint cachequotahint)
        {
            setVolumeUuid(cachequotahint.getVolumeUuid());
            setUid(cachequotahint.getUid());
            setUsageStats(cachequotahint.getUsageStats());
            setQuota(cachequotahint.getQuota());
        }
    }


    public CacheQuotaHint(Builder builder)
    {
        mUuid = Builder._2D_get3(builder);
        mUid = Builder._2D_get1(builder);
        mUsageStats = Builder._2D_get2(builder);
        mQuota = Builder._2D_get0(builder);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof CacheQuotaHint)
        {
            obj = (CacheQuotaHint)obj;
            boolean flag1 = flag;
            if(Objects.equals(mUuid, ((CacheQuotaHint) (obj)).mUuid))
            {
                flag1 = flag;
                if(Objects.equals(mUsageStats, ((CacheQuotaHint) (obj)).mUsageStats))
                {
                    flag1 = flag;
                    if(mUid == ((CacheQuotaHint) (obj)).mUid)
                    {
                        flag1 = flag;
                        if(mQuota == ((CacheQuotaHint) (obj)).mQuota)
                            flag1 = true;
                    }
                }
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    public long getQuota()
    {
        return mQuota;
    }

    public int getUid()
    {
        return mUid;
    }

    public UsageStats getUsageStats()
    {
        return mUsageStats;
    }

    public String getVolumeUuid()
    {
        return mUuid;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            mUuid, Integer.valueOf(mUid), mUsageStats, Long.valueOf(mQuota)
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mUuid);
        parcel.writeInt(mUid);
        parcel.writeLong(mQuota);
        parcel.writeParcelable(mUsageStats, 0);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CacheQuotaHint createFromParcel(Parcel parcel)
        {
            return (new Builder()).setVolumeUuid(parcel.readString()).setUid(parcel.readInt()).setQuota(parcel.readLong()).setUsageStats((UsageStats)parcel.readParcelable(android/app/usage/UsageStats.getClassLoader())).build();
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CacheQuotaHint[] newArray(int i)
        {
            return new CacheQuotaHint[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final long QUOTA_NOT_SET = -1L;
    private final long mQuota;
    private final int mUid;
    private final UsageStats mUsageStats;
    private final String mUuid;

}

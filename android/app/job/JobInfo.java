// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.job;

import android.content.ClipData;
import android.content.ComponentName;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import android.util.TimeUtils;
import java.util.*;

public class JobInfo
    implements Parcelable
{
    public static final class Builder
    {

        static int _2D_get0(Builder builder)
        {
            return builder.mBackoffPolicy;
        }

        static ClipData _2D_get1(Builder builder)
        {
            return builder.mClipData;
        }

        static long _2D_get10(Builder builder)
        {
            return builder.mIntervalMillis;
        }

        static boolean _2D_get11(Builder builder)
        {
            return builder.mIsPeriodic;
        }

        static boolean _2D_get12(Builder builder)
        {
            return builder.mIsPersisted;
        }

        static int _2D_get13(Builder builder)
        {
            return builder.mJobId;
        }

        static ComponentName _2D_get14(Builder builder)
        {
            return builder.mJobService;
        }

        static long _2D_get15(Builder builder)
        {
            return builder.mMaxExecutionDelayMillis;
        }

        static long _2D_get16(Builder builder)
        {
            return builder.mMinLatencyMillis;
        }

        static int _2D_get17(Builder builder)
        {
            return builder.mNetworkType;
        }

        static int _2D_get18(Builder builder)
        {
            return builder.mPriority;
        }

        static Bundle _2D_get19(Builder builder)
        {
            return builder.mTransientExtras;
        }

        static int _2D_get2(Builder builder)
        {
            return builder.mClipGrantFlags;
        }

        static long _2D_get20(Builder builder)
        {
            return builder.mTriggerContentMaxDelay;
        }

        static long _2D_get21(Builder builder)
        {
            return builder.mTriggerContentUpdateDelay;
        }

        static ArrayList _2D_get22(Builder builder)
        {
            return builder.mTriggerContentUris;
        }

        static int _2D_get3(Builder builder)
        {
            return builder.mConstraintFlags;
        }

        static PersistableBundle _2D_get4(Builder builder)
        {
            return builder.mExtras;
        }

        static int _2D_get5(Builder builder)
        {
            return builder.mFlags;
        }

        static long _2D_get6(Builder builder)
        {
            return builder.mFlexMillis;
        }

        static boolean _2D_get7(Builder builder)
        {
            return builder.mHasEarlyConstraint;
        }

        static boolean _2D_get8(Builder builder)
        {
            return builder.mHasLateConstraint;
        }

        static long _2D_get9(Builder builder)
        {
            return builder.mInitialBackoffMillis;
        }

        public Builder addTriggerContentUri(TriggerContentUri triggercontenturi)
        {
            if(mTriggerContentUris == null)
                mTriggerContentUris = new ArrayList();
            mTriggerContentUris.add(triggercontenturi);
            return this;
        }

        public JobInfo build()
        {
            if(!mHasEarlyConstraint && mHasLateConstraint ^ true && mConstraintFlags == 0 && mNetworkType == 0 && mTriggerContentUris == null)
                throw new IllegalArgumentException("You're trying to build a job with no constraints, this is not allowed.");
            if(mIsPeriodic)
            {
                if(mMaxExecutionDelayMillis != 0L)
                    throw new IllegalArgumentException("Can't call setOverrideDeadline() on a periodic job.");
                if(mMinLatencyMillis != 0L)
                    throw new IllegalArgumentException("Can't call setMinimumLatency() on a periodic job");
                if(mTriggerContentUris != null)
                    throw new IllegalArgumentException("Can't call addTriggerContentUri() on a periodic job");
            }
            if(mIsPersisted)
            {
                if(mTriggerContentUris != null)
                    throw new IllegalArgumentException("Can't call addTriggerContentUri() on a persisted job");
                if(!mTransientExtras.isEmpty())
                    throw new IllegalArgumentException("Can't call setTransientExtras() on a persisted job");
                if(mClipData != null)
                    throw new IllegalArgumentException("Can't call setClipData() on a persisted job");
            }
            if(mBackoffPolicySet && (mConstraintFlags & 4) != 0)
                throw new IllegalArgumentException("An idle mode job will not respect any back-off policy, so calling setBackoffCriteria with setRequiresDeviceIdle is an error.");
            JobInfo jobinfo = new JobInfo(this, null);
            if(jobinfo.isPeriodic())
            {
                if(JobInfo._2D_get2(jobinfo) != jobinfo.getIntervalMillis())
                {
                    StringBuilder stringbuilder = new StringBuilder();
                    stringbuilder.append("Specified interval for ").append(String.valueOf(mJobId)).append(" is ");
                    TimeUtils.formatDuration(mIntervalMillis, stringbuilder);
                    stringbuilder.append(". Clamped to ");
                    TimeUtils.formatDuration(jobinfo.getIntervalMillis(), stringbuilder);
                    Log.w(JobInfo._2D_get0(), stringbuilder.toString());
                }
                if(JobInfo._2D_get1(jobinfo) != jobinfo.getFlexMillis())
                {
                    StringBuilder stringbuilder1 = new StringBuilder();
                    stringbuilder1.append("Specified flex for ").append(String.valueOf(mJobId)).append(" is ");
                    TimeUtils.formatDuration(mFlexMillis, stringbuilder1);
                    stringbuilder1.append(". Clamped to ");
                    TimeUtils.formatDuration(jobinfo.getFlexMillis(), stringbuilder1);
                    Log.w(JobInfo._2D_get0(), stringbuilder1.toString());
                }
            }
            return jobinfo;
        }

        public Builder setBackoffCriteria(long l, int i)
        {
            mBackoffPolicySet = true;
            mInitialBackoffMillis = l;
            mBackoffPolicy = i;
            return this;
        }

        public Builder setClipData(ClipData clipdata, int i)
        {
            mClipData = clipdata;
            mClipGrantFlags = i;
            return this;
        }

        public Builder setExtras(PersistableBundle persistablebundle)
        {
            mExtras = persistablebundle;
            return this;
        }

        public Builder setFlags(int i)
        {
            mFlags = i;
            return this;
        }

        public Builder setMinimumLatency(long l)
        {
            mMinLatencyMillis = l;
            mHasEarlyConstraint = true;
            return this;
        }

        public Builder setOverrideDeadline(long l)
        {
            mMaxExecutionDelayMillis = l;
            mHasLateConstraint = true;
            return this;
        }

        public Builder setPeriodic(long l)
        {
            return setPeriodic(l, l);
        }

        public Builder setPeriodic(long l, long l1)
        {
            mIsPeriodic = true;
            mIntervalMillis = l;
            mFlexMillis = l1;
            mHasLateConstraint = true;
            mHasEarlyConstraint = true;
            return this;
        }

        public Builder setPersisted(boolean flag)
        {
            mIsPersisted = flag;
            return this;
        }

        public Builder setPriority(int i)
        {
            mPriority = i;
            return this;
        }

        public Builder setRequiredNetworkType(int i)
        {
            mNetworkType = i;
            return this;
        }

        public Builder setRequiresBatteryNotLow(boolean flag)
        {
            int i = mConstraintFlags;
            byte byte0;
            if(flag)
                byte0 = 2;
            else
                byte0 = 0;
            mConstraintFlags = byte0 | i & -3;
            return this;
        }

        public Builder setRequiresCharging(boolean flag)
        {
            int i = mConstraintFlags;
            boolean flag1;
            if(flag)
                flag1 = true;
            else
                flag1 = false;
            mConstraintFlags = flag1 | i & -2;
            return this;
        }

        public Builder setRequiresDeviceIdle(boolean flag)
        {
            int i = mConstraintFlags;
            byte byte0;
            if(flag)
                byte0 = 4;
            else
                byte0 = 0;
            mConstraintFlags = byte0 | i & -5;
            return this;
        }

        public Builder setRequiresStorageNotLow(boolean flag)
        {
            int i = mConstraintFlags;
            byte byte0;
            if(flag)
                byte0 = 8;
            else
                byte0 = 0;
            mConstraintFlags = byte0 | i & -9;
            return this;
        }

        public Builder setTransientExtras(Bundle bundle)
        {
            mTransientExtras = bundle;
            return this;
        }

        public Builder setTriggerContentMaxDelay(long l)
        {
            mTriggerContentMaxDelay = l;
            return this;
        }

        public Builder setTriggerContentUpdateDelay(long l)
        {
            mTriggerContentUpdateDelay = l;
            return this;
        }

        private int mBackoffPolicy;
        private boolean mBackoffPolicySet;
        private ClipData mClipData;
        private int mClipGrantFlags;
        private int mConstraintFlags;
        private PersistableBundle mExtras;
        private int mFlags;
        private long mFlexMillis;
        private boolean mHasEarlyConstraint;
        private boolean mHasLateConstraint;
        private long mInitialBackoffMillis;
        private long mIntervalMillis;
        private boolean mIsPeriodic;
        private boolean mIsPersisted;
        private final int mJobId;
        private final ComponentName mJobService;
        private long mMaxExecutionDelayMillis;
        private long mMinLatencyMillis;
        private int mNetworkType;
        private int mPriority;
        private Bundle mTransientExtras;
        private long mTriggerContentMaxDelay;
        private long mTriggerContentUpdateDelay;
        private ArrayList mTriggerContentUris;

        public Builder(int i, ComponentName componentname)
        {
            mExtras = PersistableBundle.EMPTY;
            mTransientExtras = Bundle.EMPTY;
            mPriority = 0;
            mTriggerContentUpdateDelay = -1L;
            mTriggerContentMaxDelay = -1L;
            mInitialBackoffMillis = 30000L;
            mBackoffPolicy = 1;
            mBackoffPolicySet = false;
            mJobService = componentname;
            mJobId = i;
        }
    }

    public static final class TriggerContentUri
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(!(obj instanceof TriggerContentUri))
                return false;
            obj = (TriggerContentUri)obj;
            boolean flag1 = flag;
            if(Objects.equals(((TriggerContentUri) (obj)).mUri, mUri))
            {
                flag1 = flag;
                if(((TriggerContentUri) (obj)).mFlags == mFlags)
                    flag1 = true;
            }
            return flag1;
        }

        public int getFlags()
        {
            return mFlags;
        }

        public Uri getUri()
        {
            return mUri;
        }

        public int hashCode()
        {
            int i;
            if(mUri == null)
                i = 0;
            else
                i = mUri.hashCode();
            return i ^ mFlags;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            mUri.writeToParcel(parcel, i);
            parcel.writeInt(mFlags);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public TriggerContentUri createFromParcel(Parcel parcel)
            {
                return new TriggerContentUri(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public TriggerContentUri[] newArray(int i)
            {
                return new TriggerContentUri[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public static final int FLAG_NOTIFY_FOR_DESCENDANTS = 1;
        private final int mFlags;
        private final Uri mUri;


        public TriggerContentUri(Uri uri, int i)
        {
            mUri = uri;
            mFlags = i;
        }

        private TriggerContentUri(Parcel parcel)
        {
            mUri = (Uri)Uri.CREATOR.createFromParcel(parcel);
            mFlags = parcel.readInt();
        }

        TriggerContentUri(Parcel parcel, TriggerContentUri triggercontenturi)
        {
            this(parcel);
        }
    }


    static String _2D_get0()
    {
        return TAG;
    }

    static long _2D_get1(JobInfo jobinfo)
    {
        return jobinfo.flexMillis;
    }

    static long _2D_get2(JobInfo jobinfo)
    {
        return jobinfo.intervalMillis;
    }

    private JobInfo(Builder builder)
    {
        TriggerContentUri atriggercontenturi[] = null;
        super();
        jobId = Builder._2D_get13(builder);
        extras = Builder._2D_get4(builder).deepCopy();
        transientExtras = Builder._2D_get19(builder).deepCopy();
        clipData = Builder._2D_get1(builder);
        clipGrantFlags = Builder._2D_get2(builder);
        service = Builder._2D_get14(builder);
        constraintFlags = Builder._2D_get3(builder);
        if(Builder._2D_get22(builder) != null)
            atriggercontenturi = (TriggerContentUri[])Builder._2D_get22(builder).toArray(new TriggerContentUri[Builder._2D_get22(builder).size()]);
        triggerContentUris = atriggercontenturi;
        triggerContentUpdateDelay = Builder._2D_get21(builder);
        triggerContentMaxDelay = Builder._2D_get20(builder);
        networkType = Builder._2D_get17(builder);
        minLatencyMillis = Builder._2D_get16(builder);
        maxExecutionDelayMillis = Builder._2D_get15(builder);
        isPeriodic = Builder._2D_get11(builder);
        isPersisted = Builder._2D_get12(builder);
        intervalMillis = Builder._2D_get10(builder);
        flexMillis = Builder._2D_get6(builder);
        initialBackoffMillis = Builder._2D_get9(builder);
        backoffPolicy = Builder._2D_get0(builder);
        hasEarlyConstraint = Builder._2D_get7(builder);
        hasLateConstraint = Builder._2D_get8(builder);
        priority = Builder._2D_get18(builder);
        flags = Builder._2D_get5(builder);
    }

    JobInfo(Builder builder, JobInfo jobinfo)
    {
        this(builder);
    }

    private JobInfo(Parcel parcel)
    {
        boolean flag = true;
        super();
        jobId = parcel.readInt();
        extras = parcel.readPersistableBundle();
        transientExtras = parcel.readBundle();
        boolean flag1;
        if(parcel.readInt() != 0)
        {
            clipData = (ClipData)ClipData.CREATOR.createFromParcel(parcel);
            clipGrantFlags = parcel.readInt();
        } else
        {
            clipData = null;
            clipGrantFlags = 0;
        }
        service = (ComponentName)parcel.readParcelable(null);
        constraintFlags = parcel.readInt();
        triggerContentUris = (TriggerContentUri[])parcel.createTypedArray(TriggerContentUri.CREATOR);
        triggerContentUpdateDelay = parcel.readLong();
        triggerContentMaxDelay = parcel.readLong();
        networkType = parcel.readInt();
        minLatencyMillis = parcel.readLong();
        maxExecutionDelayMillis = parcel.readLong();
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        isPeriodic = flag1;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        isPersisted = flag1;
        intervalMillis = parcel.readLong();
        flexMillis = parcel.readLong();
        initialBackoffMillis = parcel.readLong();
        backoffPolicy = parcel.readInt();
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        hasEarlyConstraint = flag1;
        if(parcel.readInt() == 1)
            flag1 = flag;
        else
            flag1 = false;
        hasLateConstraint = flag1;
        priority = parcel.readInt();
        flags = parcel.readInt();
    }

    JobInfo(Parcel parcel, JobInfo jobinfo)
    {
        this(parcel);
    }

    public static final long getMinBackoffMillis()
    {
        return 10000L;
    }

    public static final long getMinFlexMillis()
    {
        return 0x493e0L;
    }

    public static final long getMinPeriodMillis()
    {
        return 0xdbba0L;
    }

    private static boolean kindofEqualsBundle(BaseBundle basebundle, BaseBundle basebundle1)
    {
        boolean flag;
        if(basebundle != basebundle1)
        {
            if(basebundle != null)
                flag = basebundle.kindofEquals(basebundle1);
            else
                flag = false;
        } else
        {
            flag = true;
        }
        return flag;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof JobInfo))
            return false;
        obj = (JobInfo)obj;
        if(jobId != ((JobInfo) (obj)).jobId)
            return false;
        if(!kindofEqualsBundle(extras, ((JobInfo) (obj)).extras))
            return false;
        if(!kindofEqualsBundle(transientExtras, ((JobInfo) (obj)).transientExtras))
            return false;
        if(clipData != ((JobInfo) (obj)).clipData)
            return false;
        if(clipGrantFlags != ((JobInfo) (obj)).clipGrantFlags)
            return false;
        if(!Objects.equals(service, ((JobInfo) (obj)).service))
            return false;
        if(constraintFlags != ((JobInfo) (obj)).constraintFlags)
            return false;
        if(!Arrays.equals(triggerContentUris, ((JobInfo) (obj)).triggerContentUris))
            return false;
        if(triggerContentUpdateDelay != ((JobInfo) (obj)).triggerContentUpdateDelay)
            return false;
        if(triggerContentMaxDelay != ((JobInfo) (obj)).triggerContentMaxDelay)
            return false;
        if(hasEarlyConstraint != ((JobInfo) (obj)).hasEarlyConstraint)
            return false;
        if(hasLateConstraint != ((JobInfo) (obj)).hasLateConstraint)
            return false;
        if(networkType != ((JobInfo) (obj)).networkType)
            return false;
        if(minLatencyMillis != ((JobInfo) (obj)).minLatencyMillis)
            return false;
        if(maxExecutionDelayMillis != ((JobInfo) (obj)).maxExecutionDelayMillis)
            return false;
        if(isPeriodic != ((JobInfo) (obj)).isPeriodic)
            return false;
        if(isPersisted != ((JobInfo) (obj)).isPersisted)
            return false;
        if(intervalMillis != ((JobInfo) (obj)).intervalMillis)
            return false;
        if(flexMillis != ((JobInfo) (obj)).flexMillis)
            return false;
        if(initialBackoffMillis != ((JobInfo) (obj)).initialBackoffMillis)
            return false;
        if(backoffPolicy != ((JobInfo) (obj)).backoffPolicy)
            return false;
        if(priority != ((JobInfo) (obj)).priority)
            return false;
        return flags == ((JobInfo) (obj)).flags;
    }

    public int getBackoffPolicy()
    {
        return backoffPolicy;
    }

    public ClipData getClipData()
    {
        return clipData;
    }

    public int getClipGrantFlags()
    {
        return clipGrantFlags;
    }

    public int getConstraintFlags()
    {
        return constraintFlags;
    }

    public PersistableBundle getExtras()
    {
        return extras;
    }

    public int getFlags()
    {
        return flags;
    }

    public long getFlexMillis()
    {
        long l = getIntervalMillis();
        long l1 = (5L * l) / 100L;
        l1 = Math.max(flexMillis, Math.max(l1, getMinFlexMillis()));
        if(l1 <= l)
            l = l1;
        return l;
    }

    public int getId()
    {
        return jobId;
    }

    public long getInitialBackoffMillis()
    {
        long l = getMinBackoffMillis();
        long l1 = l;
        if(initialBackoffMillis >= l)
            l1 = initialBackoffMillis;
        return l1;
    }

    public long getIntervalMillis()
    {
        long l = getMinPeriodMillis();
        long l1 = l;
        if(intervalMillis >= l)
            l1 = intervalMillis;
        return l1;
    }

    public long getMaxExecutionDelayMillis()
    {
        return maxExecutionDelayMillis;
    }

    public long getMinLatencyMillis()
    {
        return minLatencyMillis;
    }

    public int getNetworkType()
    {
        return networkType;
    }

    public int getPriority()
    {
        return priority;
    }

    public ComponentName getService()
    {
        return service;
    }

    public Bundle getTransientExtras()
    {
        return transientExtras;
    }

    public long getTriggerContentMaxDelay()
    {
        return triggerContentMaxDelay;
    }

    public long getTriggerContentUpdateDelay()
    {
        return triggerContentUpdateDelay;
    }

    public TriggerContentUri[] getTriggerContentUris()
    {
        return triggerContentUris;
    }

    public boolean hasEarlyConstraint()
    {
        return hasEarlyConstraint;
    }

    public boolean hasLateConstraint()
    {
        return hasLateConstraint;
    }

    public int hashCode()
    {
        int i = jobId;
        int j = i;
        if(extras != null)
            j = i * 31 + extras.hashCode();
        i = j;
        if(transientExtras != null)
            i = j * 31 + transientExtras.hashCode();
        j = i;
        if(clipData != null)
            j = i * 31 + clipData.hashCode();
        j = j * 31 + clipGrantFlags;
        i = j;
        if(service != null)
            i = j * 31 + service.hashCode();
        j = i * 31 + constraintFlags;
        i = j;
        if(triggerContentUris != null)
            i = j * 31 + Arrays.hashCode(triggerContentUris);
        return ((((((((((((((i * 31 + Long.hashCode(triggerContentUpdateDelay)) * 31 + Long.hashCode(triggerContentMaxDelay)) * 31 + Boolean.hashCode(hasEarlyConstraint)) * 31 + Boolean.hashCode(hasLateConstraint)) * 31 + networkType) * 31 + Long.hashCode(minLatencyMillis)) * 31 + Long.hashCode(maxExecutionDelayMillis)) * 31 + Boolean.hashCode(isPeriodic)) * 31 + Boolean.hashCode(isPersisted)) * 31 + Long.hashCode(intervalMillis)) * 31 + Long.hashCode(flexMillis)) * 31 + Long.hashCode(initialBackoffMillis)) * 31 + backoffPolicy) * 31 + priority) * 31 + flags;
    }

    public boolean isPeriodic()
    {
        return isPeriodic;
    }

    public boolean isPersisted()
    {
        return isPersisted;
    }

    public boolean isRequireBatteryNotLow()
    {
        boolean flag = false;
        if((constraintFlags & 2) != 0)
            flag = true;
        return flag;
    }

    public boolean isRequireCharging()
    {
        boolean flag = false;
        if((constraintFlags & 1) != 0)
            flag = true;
        return flag;
    }

    public boolean isRequireDeviceIdle()
    {
        boolean flag = false;
        if((constraintFlags & 4) != 0)
            flag = true;
        return flag;
    }

    public boolean isRequireStorageNotLow()
    {
        boolean flag = false;
        if((constraintFlags & 8) != 0)
            flag = true;
        return flag;
    }

    public String toString()
    {
        return (new StringBuilder()).append("(job:").append(jobId).append("/").append(service.flattenToShortString()).append(")").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(jobId);
        parcel.writePersistableBundle(extras);
        parcel.writeBundle(transientExtras);
        if(clipData != null)
        {
            parcel.writeInt(1);
            clipData.writeToParcel(parcel, i);
            parcel.writeInt(clipGrantFlags);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeParcelable(service, i);
        parcel.writeInt(constraintFlags);
        parcel.writeTypedArray(triggerContentUris, i);
        parcel.writeLong(triggerContentUpdateDelay);
        parcel.writeLong(triggerContentMaxDelay);
        parcel.writeInt(networkType);
        parcel.writeLong(minLatencyMillis);
        parcel.writeLong(maxExecutionDelayMillis);
        if(isPeriodic)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(isPersisted)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeLong(intervalMillis);
        parcel.writeLong(flexMillis);
        parcel.writeLong(initialBackoffMillis);
        parcel.writeInt(backoffPolicy);
        if(hasEarlyConstraint)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(hasLateConstraint)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(priority);
        parcel.writeInt(flags);
    }

    public static final int BACKOFF_POLICY_EXPONENTIAL = 1;
    public static final int BACKOFF_POLICY_LINEAR = 0;
    public static final int CONSTRAINT_FLAG_BATTERY_NOT_LOW = 2;
    public static final int CONSTRAINT_FLAG_CHARGING = 1;
    public static final int CONSTRAINT_FLAG_DEVICE_IDLE = 4;
    public static final int CONSTRAINT_FLAG_STORAGE_NOT_LOW = 8;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public JobInfo createFromParcel(Parcel parcel)
        {
            return new JobInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public JobInfo[] newArray(int i)
        {
            return new JobInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DEFAULT_BACKOFF_POLICY = 1;
    public static final long DEFAULT_INITIAL_BACKOFF_MILLIS = 30000L;
    public static final int FLAG_WILL_BE_FOREGROUND = 1;
    public static final long MAX_BACKOFF_DELAY_MILLIS = 0x112a880L;
    public static final long MIN_BACKOFF_MILLIS = 10000L;
    private static final long MIN_FLEX_MILLIS = 0x493e0L;
    private static final long MIN_PERIOD_MILLIS = 0xdbba0L;
    public static final int NETWORK_TYPE_ANY = 1;
    public static final int NETWORK_TYPE_METERED = 4;
    public static final int NETWORK_TYPE_NONE = 0;
    public static final int NETWORK_TYPE_NOT_ROAMING = 3;
    public static final int NETWORK_TYPE_UNMETERED = 2;
    public static final int PRIORITY_ADJ_ALWAYS_RUNNING = -80;
    public static final int PRIORITY_ADJ_OFTEN_RUNNING = -40;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_FOREGROUND_APP = 30;
    public static final int PRIORITY_SYNC_EXPEDITED = 10;
    public static final int PRIORITY_SYNC_INITIALIZATION = 20;
    public static final int PRIORITY_TOP_APP = 40;
    private static String TAG = "JobInfo";
    private final int backoffPolicy;
    private final ClipData clipData;
    private final int clipGrantFlags;
    private final int constraintFlags;
    private final PersistableBundle extras;
    private final int flags;
    private final long flexMillis;
    private final boolean hasEarlyConstraint;
    private final boolean hasLateConstraint;
    private final long initialBackoffMillis;
    private final long intervalMillis;
    private final boolean isPeriodic;
    private final boolean isPersisted;
    private final int jobId;
    private final long maxExecutionDelayMillis;
    private final long minLatencyMillis;
    private final int networkType;
    private final int priority;
    private final ComponentName service;
    private final Bundle transientExtras;
    private final long triggerContentMaxDelay;
    private final long triggerContentUpdateDelay;
    private final TriggerContentUri triggerContentUris[];

}

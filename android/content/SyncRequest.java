// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.accounts.Account;
import android.os.*;

// Referenced classes of package android.content:
//            ContentResolver

public class SyncRequest
    implements Parcelable
{
    public static class Builder
    {

        static Account _2D_get0(Builder builder)
        {
            return builder.mAccount;
        }

        static String _2D_get1(Builder builder)
        {
            return builder.mAuthority;
        }

        static Bundle _2D_get2(Builder builder)
        {
            return builder.mCustomExtras;
        }

        static boolean _2D_get3(Builder builder)
        {
            return builder.mDisallowMetered;
        }

        static boolean _2D_get4(Builder builder)
        {
            return builder.mExpedited;
        }

        static Bundle _2D_get5(Builder builder)
        {
            return builder.mSyncConfigExtras;
        }

        static long _2D_get6(Builder builder)
        {
            return builder.mSyncFlexTimeSecs;
        }

        static long _2D_get7(Builder builder)
        {
            return builder.mSyncRunTimeSecs;
        }

        static int _2D_get8(Builder builder)
        {
            return builder.mSyncTarget;
        }

        static int _2D_get9(Builder builder)
        {
            return builder.mSyncType;
        }

        private void setupInterval(long l, long l1)
        {
            if(l1 > l)
            {
                throw new IllegalArgumentException("Specified run time for the sync must be after the specified flex time.");
            } else
            {
                mSyncRunTimeSecs = l;
                mSyncFlexTimeSecs = l1;
                return;
            }
        }

        public SyncRequest build()
        {
            ContentResolver.validateSyncExtrasBundle(mCustomExtras);
            if(mCustomExtras == null)
                mCustomExtras = new Bundle();
            mSyncConfigExtras = new Bundle();
            if(mIgnoreBackoff)
                mSyncConfigExtras.putBoolean("ignore_backoff", true);
            if(mDisallowMetered)
                mSyncConfigExtras.putBoolean("allow_metered", true);
            if(mRequiresCharging)
                mSyncConfigExtras.putBoolean("require_charging", true);
            if(mIgnoreSettings)
                mSyncConfigExtras.putBoolean("ignore_settings", true);
            if(mNoRetry)
                mSyncConfigExtras.putBoolean("do_not_retry", true);
            if(mExpedited)
                mSyncConfigExtras.putBoolean("expedited", true);
            if(mIsManual)
            {
                mSyncConfigExtras.putBoolean("ignore_backoff", true);
                mSyncConfigExtras.putBoolean("ignore_settings", true);
            }
            if(mSyncType == 1 && (ContentResolver.invalidPeriodicExtras(mCustomExtras) || ContentResolver.invalidPeriodicExtras(mSyncConfigExtras)))
                throw new IllegalArgumentException("Illegal extras were set");
            if(mSyncTarget == 0)
                throw new IllegalArgumentException("Must specify an adapter with setSyncAdapter(Account, String");
            else
                return new SyncRequest(this);
        }

        public Builder setDisallowMetered(boolean flag)
        {
            if(mIgnoreSettings && flag)
            {
                throw new IllegalArgumentException("setDisallowMetered(true) after having specified that settings are ignored.");
            } else
            {
                mDisallowMetered = flag;
                return this;
            }
        }

        public Builder setExpedited(boolean flag)
        {
            mExpedited = flag;
            return this;
        }

        public Builder setExtras(Bundle bundle)
        {
            mCustomExtras = bundle;
            return this;
        }

        public Builder setIgnoreBackoff(boolean flag)
        {
            mIgnoreBackoff = flag;
            return this;
        }

        public Builder setIgnoreSettings(boolean flag)
        {
            if(mDisallowMetered && flag)
            {
                throw new IllegalArgumentException("setIgnoreSettings(true) after having specified sync settings with this builder.");
            } else
            {
                mIgnoreSettings = flag;
                return this;
            }
        }

        public Builder setManual(boolean flag)
        {
            mIsManual = flag;
            return this;
        }

        public Builder setNoRetry(boolean flag)
        {
            mNoRetry = flag;
            return this;
        }

        public Builder setRequiresCharging(boolean flag)
        {
            mRequiresCharging = flag;
            return this;
        }

        public Builder setSyncAdapter(Account account, String s)
        {
            if(mSyncTarget != 0)
                throw new IllegalArgumentException("Sync target has already been defined.");
            if(s != null && s.length() == 0)
            {
                throw new IllegalArgumentException("Authority must be non-empty");
            } else
            {
                mSyncTarget = 2;
                mAccount = account;
                mAuthority = s;
                return this;
            }
        }

        public Builder syncOnce()
        {
            if(mSyncType != 0)
            {
                throw new IllegalArgumentException("Sync type has already been defined.");
            } else
            {
                mSyncType = 2;
                setupInterval(0L, 0L);
                return this;
            }
        }

        public Builder syncPeriodic(long l, long l1)
        {
            if(mSyncType != 0)
            {
                throw new IllegalArgumentException("Sync type has already been defined.");
            } else
            {
                mSyncType = 1;
                setupInterval(l, l1);
                return this;
            }
        }

        private static final int SYNC_TARGET_ADAPTER = 2;
        private static final int SYNC_TARGET_UNKNOWN = 0;
        private static final int SYNC_TYPE_ONCE = 2;
        private static final int SYNC_TYPE_PERIODIC = 1;
        private static final int SYNC_TYPE_UNKNOWN = 0;
        private Account mAccount;
        private String mAuthority;
        private Bundle mCustomExtras;
        private boolean mDisallowMetered;
        private boolean mExpedited;
        private boolean mIgnoreBackoff;
        private boolean mIgnoreSettings;
        private boolean mIsManual;
        private boolean mNoRetry;
        private boolean mRequiresCharging;
        private Bundle mSyncConfigExtras;
        private long mSyncFlexTimeSecs;
        private long mSyncRunTimeSecs;
        private int mSyncTarget;
        private int mSyncType;

        public Builder()
        {
            mSyncType = 0;
            mSyncTarget = 0;
        }
    }


    protected SyncRequest(Builder builder)
    {
        boolean flag = true;
        super();
        mSyncFlexTimeSecs = Builder._2D_get6(builder);
        mSyncRunTimeSecs = Builder._2D_get7(builder);
        mAccountToSync = Builder._2D_get0(builder);
        mAuthority = Builder._2D_get1(builder);
        boolean flag1;
        if(Builder._2D_get9(builder) == 1)
            flag1 = true;
        else
            flag1 = false;
        mIsPeriodic = flag1;
        if(Builder._2D_get8(builder) == 2)
            flag1 = flag;
        else
            flag1 = false;
        mIsAuthority = flag1;
        mIsExpedited = Builder._2D_get4(builder);
        mExtras = new Bundle(Builder._2D_get2(builder));
        mExtras.putAll(Builder._2D_get5(builder));
        mDisallowMetered = Builder._2D_get3(builder);
    }

    private SyncRequest(Parcel parcel)
    {
        boolean flag = true;
        super();
        mExtras = Bundle.setDefusable(parcel.readBundle(), true);
        mSyncFlexTimeSecs = parcel.readLong();
        mSyncRunTimeSecs = parcel.readLong();
        boolean flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mIsPeriodic = flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mDisallowMetered = flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mIsAuthority = flag1;
        if(parcel.readInt() != 0)
            flag1 = flag;
        else
            flag1 = false;
        mIsExpedited = flag1;
        mAccountToSync = (Account)parcel.readParcelable(null);
        mAuthority = parcel.readString();
    }

    SyncRequest(Parcel parcel, SyncRequest syncrequest)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public Account getAccount()
    {
        return mAccountToSync;
    }

    public Bundle getBundle()
    {
        return mExtras;
    }

    public String getProvider()
    {
        return mAuthority;
    }

    public long getSyncFlexTime()
    {
        return mSyncFlexTimeSecs;
    }

    public long getSyncRunTime()
    {
        return mSyncRunTimeSecs;
    }

    public boolean isExpedited()
    {
        return mIsExpedited;
    }

    public boolean isPeriodic()
    {
        return mIsPeriodic;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeBundle(mExtras);
        parcel.writeLong(mSyncFlexTimeSecs);
        parcel.writeLong(mSyncRunTimeSecs);
        int j;
        if(mIsPeriodic)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(mDisallowMetered)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(mIsAuthority)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(mIsExpedited)
            j = ((flag) ? 1 : 0);
        else
            j = 0;
        parcel.writeInt(j);
        parcel.writeParcelable(mAccountToSync, i);
        parcel.writeString(mAuthority);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SyncRequest createFromParcel(Parcel parcel)
        {
            return new SyncRequest(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SyncRequest[] newArray(int i)
        {
            return new SyncRequest[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "SyncRequest";
    private final Account mAccountToSync;
    private final String mAuthority;
    private final boolean mDisallowMetered;
    private final Bundle mExtras;
    private final boolean mIsAuthority;
    private final boolean mIsExpedited;
    private final boolean mIsPeriodic;
    private final long mSyncFlexTimeSecs;
    private final long mSyncRunTimeSecs;

}

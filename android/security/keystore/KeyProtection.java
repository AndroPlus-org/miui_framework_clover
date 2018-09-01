// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import java.util.Date;

// Referenced classes of package android.security.keystore:
//            Utils, ArrayUtils

public final class KeyProtection
    implements java.security.KeyStore.ProtectionParameter
{
    public static final class Builder
    {

        public KeyProtection build()
        {
            return new KeyProtection(mKeyValidityStart, mKeyValidityForOriginationEnd, mKeyValidityForConsumptionEnd, mPurposes, mEncryptionPaddings, mSignaturePaddings, mDigests, mBlockModes, mRandomizedEncryptionRequired, mUserAuthenticationRequired, mUserAuthenticationValidityDurationSeconds, mUserAuthenticationValidWhileOnBody, mInvalidatedByBiometricEnrollment, mBoundToSecureUserId, mCriticalToDeviceEncryption, null);
        }

        public transient Builder setBlockModes(String as[])
        {
            mBlockModes = ArrayUtils.cloneIfNotEmpty(as);
            return this;
        }

        public Builder setBoundToSpecificSecureUserId(long l)
        {
            mBoundToSecureUserId = l;
            return this;
        }

        public Builder setCriticalToDeviceEncryption(boolean flag)
        {
            mCriticalToDeviceEncryption = flag;
            return this;
        }

        public transient Builder setDigests(String as[])
        {
            mDigests = ArrayUtils.cloneIfNotEmpty(as);
            return this;
        }

        public transient Builder setEncryptionPaddings(String as[])
        {
            mEncryptionPaddings = ArrayUtils.cloneIfNotEmpty(as);
            return this;
        }

        public Builder setInvalidatedByBiometricEnrollment(boolean flag)
        {
            mInvalidatedByBiometricEnrollment = flag;
            return this;
        }

        public Builder setKeyValidityEnd(Date date)
        {
            setKeyValidityForOriginationEnd(date);
            setKeyValidityForConsumptionEnd(date);
            return this;
        }

        public Builder setKeyValidityForConsumptionEnd(Date date)
        {
            mKeyValidityForConsumptionEnd = Utils.cloneIfNotNull(date);
            return this;
        }

        public Builder setKeyValidityForOriginationEnd(Date date)
        {
            mKeyValidityForOriginationEnd = Utils.cloneIfNotNull(date);
            return this;
        }

        public Builder setKeyValidityStart(Date date)
        {
            mKeyValidityStart = Utils.cloneIfNotNull(date);
            return this;
        }

        public Builder setRandomizedEncryptionRequired(boolean flag)
        {
            mRandomizedEncryptionRequired = flag;
            return this;
        }

        public transient Builder setSignaturePaddings(String as[])
        {
            mSignaturePaddings = ArrayUtils.cloneIfNotEmpty(as);
            return this;
        }

        public Builder setUserAuthenticationRequired(boolean flag)
        {
            mUserAuthenticationRequired = flag;
            return this;
        }

        public Builder setUserAuthenticationValidWhileOnBody(boolean flag)
        {
            mUserAuthenticationValidWhileOnBody = flag;
            return this;
        }

        public Builder setUserAuthenticationValidityDurationSeconds(int i)
        {
            if(i < -1)
            {
                throw new IllegalArgumentException("seconds must be -1 or larger");
            } else
            {
                mUserAuthenticationValidityDurationSeconds = i;
                return this;
            }
        }

        private String mBlockModes[];
        private long mBoundToSecureUserId;
        private boolean mCriticalToDeviceEncryption;
        private String mDigests[];
        private String mEncryptionPaddings[];
        private boolean mInvalidatedByBiometricEnrollment;
        private Date mKeyValidityForConsumptionEnd;
        private Date mKeyValidityForOriginationEnd;
        private Date mKeyValidityStart;
        private int mPurposes;
        private boolean mRandomizedEncryptionRequired;
        private String mSignaturePaddings[];
        private boolean mUserAuthenticationRequired;
        private boolean mUserAuthenticationValidWhileOnBody;
        private int mUserAuthenticationValidityDurationSeconds;

        public Builder(int i)
        {
            mRandomizedEncryptionRequired = true;
            mUserAuthenticationValidityDurationSeconds = -1;
            mInvalidatedByBiometricEnrollment = true;
            mBoundToSecureUserId = 0L;
            mCriticalToDeviceEncryption = false;
            mPurposes = i;
        }
    }


    private KeyProtection(Date date, Date date1, Date date2, int i, String as[], String as1[], String as2[], 
            String as3[], boolean flag, boolean flag1, int j, boolean flag2, boolean flag3, long l, boolean flag4)
    {
        mKeyValidityStart = Utils.cloneIfNotNull(date);
        mKeyValidityForOriginationEnd = Utils.cloneIfNotNull(date1);
        mKeyValidityForConsumptionEnd = Utils.cloneIfNotNull(date2);
        mPurposes = i;
        mEncryptionPaddings = ArrayUtils.cloneIfNotEmpty(ArrayUtils.nullToEmpty(as));
        mSignaturePaddings = ArrayUtils.cloneIfNotEmpty(ArrayUtils.nullToEmpty(as1));
        mDigests = ArrayUtils.cloneIfNotEmpty(as2);
        mBlockModes = ArrayUtils.cloneIfNotEmpty(ArrayUtils.nullToEmpty(as3));
        mRandomizedEncryptionRequired = flag;
        mUserAuthenticationRequired = flag1;
        mUserAuthenticationValidityDurationSeconds = j;
        mUserAuthenticationValidWhileOnBody = flag2;
        mInvalidatedByBiometricEnrollment = flag3;
        mBoundToSecureUserId = l;
        mCriticalToDeviceEncryption = flag4;
    }

    KeyProtection(Date date, Date date1, Date date2, int i, String as[], String as1[], String as2[], 
            String as3[], boolean flag, boolean flag1, int j, boolean flag2, boolean flag3, long l, boolean flag4, KeyProtection keyprotection)
    {
        this(date, date1, date2, i, as, as1, as2, as3, flag, flag1, j, flag2, flag3, l, flag4);
    }

    public String[] getBlockModes()
    {
        return ArrayUtils.cloneIfNotEmpty(mBlockModes);
    }

    public long getBoundToSpecificSecureUserId()
    {
        return mBoundToSecureUserId;
    }

    public String[] getDigests()
    {
        if(mDigests == null)
            throw new IllegalStateException("Digests not specified");
        else
            return ArrayUtils.cloneIfNotEmpty(mDigests);
    }

    public String[] getEncryptionPaddings()
    {
        return ArrayUtils.cloneIfNotEmpty(mEncryptionPaddings);
    }

    public Date getKeyValidityForConsumptionEnd()
    {
        return Utils.cloneIfNotNull(mKeyValidityForConsumptionEnd);
    }

    public Date getKeyValidityForOriginationEnd()
    {
        return Utils.cloneIfNotNull(mKeyValidityForOriginationEnd);
    }

    public Date getKeyValidityStart()
    {
        return Utils.cloneIfNotNull(mKeyValidityStart);
    }

    public int getPurposes()
    {
        return mPurposes;
    }

    public String[] getSignaturePaddings()
    {
        return ArrayUtils.cloneIfNotEmpty(mSignaturePaddings);
    }

    public int getUserAuthenticationValidityDurationSeconds()
    {
        return mUserAuthenticationValidityDurationSeconds;
    }

    public boolean isCriticalToDeviceEncryption()
    {
        return mCriticalToDeviceEncryption;
    }

    public boolean isDigestsSpecified()
    {
        boolean flag;
        if(mDigests != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isInvalidatedByBiometricEnrollment()
    {
        return mInvalidatedByBiometricEnrollment;
    }

    public boolean isRandomizedEncryptionRequired()
    {
        return mRandomizedEncryptionRequired;
    }

    public boolean isUserAuthenticationRequired()
    {
        return mUserAuthenticationRequired;
    }

    public boolean isUserAuthenticationValidWhileOnBody()
    {
        return mUserAuthenticationValidWhileOnBody;
    }

    private final String mBlockModes[];
    private final long mBoundToSecureUserId;
    private final boolean mCriticalToDeviceEncryption;
    private final String mDigests[];
    private final String mEncryptionPaddings[];
    private final boolean mInvalidatedByBiometricEnrollment;
    private final Date mKeyValidityForConsumptionEnd;
    private final Date mKeyValidityForOriginationEnd;
    private final Date mKeyValidityStart;
    private final int mPurposes;
    private final boolean mRandomizedEncryptionRequired;
    private final String mSignaturePaddings[];
    private final boolean mUserAuthenticationRequired;
    private final boolean mUserAuthenticationValidWhileOnBody;
    private final int mUserAuthenticationValidityDurationSeconds;
}

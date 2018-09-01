// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.text.TextUtils;
import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Date;
import javax.security.auth.x500.X500Principal;

// Referenced classes of package android.security.keystore:
//            Utils, ArrayUtils

public final class KeyGenParameterSpec
    implements AlgorithmParameterSpec
{
    public static final class Builder
    {

        public KeyGenParameterSpec build()
        {
            return new KeyGenParameterSpec(mKeystoreAlias, mUid, mKeySize, mSpec, mCertificateSubject, mCertificateSerialNumber, mCertificateNotBefore, mCertificateNotAfter, mKeyValidityStart, mKeyValidityForOriginationEnd, mKeyValidityForConsumptionEnd, mPurposes, mDigests, mEncryptionPaddings, mSignaturePaddings, mBlockModes, mRandomizedEncryptionRequired, mUserAuthenticationRequired, mUserAuthenticationValidityDurationSeconds, mAttestationChallenge, mUniqueIdIncluded, mUserAuthenticationValidWhileOnBody, mInvalidatedByBiometricEnrollment);
        }

        public Builder setAlgorithmParameterSpec(AlgorithmParameterSpec algorithmparameterspec)
        {
            if(algorithmparameterspec == null)
            {
                throw new NullPointerException("spec == null");
            } else
            {
                mSpec = algorithmparameterspec;
                return this;
            }
        }

        public Builder setAttestationChallenge(byte abyte0[])
        {
            mAttestationChallenge = abyte0;
            return this;
        }

        public transient Builder setBlockModes(String as[])
        {
            mBlockModes = ArrayUtils.cloneIfNotEmpty(as);
            return this;
        }

        public Builder setCertificateNotAfter(Date date)
        {
            if(date == null)
            {
                throw new NullPointerException("date == null");
            } else
            {
                mCertificateNotAfter = Utils.cloneIfNotNull(date);
                return this;
            }
        }

        public Builder setCertificateNotBefore(Date date)
        {
            if(date == null)
            {
                throw new NullPointerException("date == null");
            } else
            {
                mCertificateNotBefore = Utils.cloneIfNotNull(date);
                return this;
            }
        }

        public Builder setCertificateSerialNumber(BigInteger biginteger)
        {
            if(biginteger == null)
            {
                throw new NullPointerException("serialNumber == null");
            } else
            {
                mCertificateSerialNumber = biginteger;
                return this;
            }
        }

        public Builder setCertificateSubject(X500Principal x500principal)
        {
            if(x500principal == null)
            {
                throw new NullPointerException("subject == null");
            } else
            {
                mCertificateSubject = x500principal;
                return this;
            }
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

        public Builder setKeySize(int i)
        {
            if(i < 0)
            {
                throw new IllegalArgumentException("keySize < 0");
            } else
            {
                mKeySize = i;
                return this;
            }
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

        public Builder setUid(int i)
        {
            mUid = i;
            return this;
        }

        public Builder setUniqueIdIncluded(boolean flag)
        {
            mUniqueIdIncluded = flag;
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

        private byte mAttestationChallenge[];
        private String mBlockModes[];
        private Date mCertificateNotAfter;
        private Date mCertificateNotBefore;
        private BigInteger mCertificateSerialNumber;
        private X500Principal mCertificateSubject;
        private String mDigests[];
        private String mEncryptionPaddings[];
        private boolean mInvalidatedByBiometricEnrollment;
        private int mKeySize;
        private Date mKeyValidityForConsumptionEnd;
        private Date mKeyValidityForOriginationEnd;
        private Date mKeyValidityStart;
        private final String mKeystoreAlias;
        private int mPurposes;
        private boolean mRandomizedEncryptionRequired;
        private String mSignaturePaddings[];
        private AlgorithmParameterSpec mSpec;
        private int mUid;
        private boolean mUniqueIdIncluded;
        private boolean mUserAuthenticationRequired;
        private boolean mUserAuthenticationValidWhileOnBody;
        private int mUserAuthenticationValidityDurationSeconds;

        public Builder(String s, int i)
        {
            mUid = -1;
            mKeySize = -1;
            mRandomizedEncryptionRequired = true;
            mUserAuthenticationValidityDurationSeconds = -1;
            mAttestationChallenge = null;
            mUniqueIdIncluded = false;
            mInvalidatedByBiometricEnrollment = true;
            if(s == null)
                throw new NullPointerException("keystoreAlias == null");
            if(s.isEmpty())
            {
                throw new IllegalArgumentException("keystoreAlias must not be empty");
            } else
            {
                mKeystoreAlias = s;
                mPurposes = i;
                return;
            }
        }
    }


    public KeyGenParameterSpec(String s, int i, int j, AlgorithmParameterSpec algorithmparameterspec, X500Principal x500principal, BigInteger biginteger, Date date, 
            Date date1, Date date2, Date date3, Date date4, int k, String as[], String as1[], 
            String as2[], String as3[], boolean flag, boolean flag1, int l, byte abyte0[], boolean flag2, 
            boolean flag3, boolean flag4)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("keyStoreAlias must not be empty");
        X500Principal x500principal1 = x500principal;
        if(x500principal == null)
            x500principal1 = DEFAULT_CERT_SUBJECT;
        x500principal = date;
        if(date == null)
            x500principal = DEFAULT_CERT_NOT_BEFORE;
        date = date1;
        if(date1 == null)
            date = DEFAULT_CERT_NOT_AFTER;
        date1 = biginteger;
        if(biginteger == null)
            date1 = DEFAULT_CERT_SERIAL_NUMBER;
        if(date.before(x500principal))
        {
            throw new IllegalArgumentException("certificateNotAfter < certificateNotBefore");
        } else
        {
            mKeystoreAlias = s;
            mUid = i;
            mKeySize = j;
            mSpec = algorithmparameterspec;
            mCertificateSubject = x500principal1;
            mCertificateSerialNumber = date1;
            mCertificateNotBefore = Utils.cloneIfNotNull(x500principal);
            mCertificateNotAfter = Utils.cloneIfNotNull(date);
            mKeyValidityStart = Utils.cloneIfNotNull(date2);
            mKeyValidityForOriginationEnd = Utils.cloneIfNotNull(date3);
            mKeyValidityForConsumptionEnd = Utils.cloneIfNotNull(date4);
            mPurposes = k;
            mDigests = ArrayUtils.cloneIfNotEmpty(as);
            mEncryptionPaddings = ArrayUtils.cloneIfNotEmpty(ArrayUtils.nullToEmpty(as1));
            mSignaturePaddings = ArrayUtils.cloneIfNotEmpty(ArrayUtils.nullToEmpty(as2));
            mBlockModes = ArrayUtils.cloneIfNotEmpty(ArrayUtils.nullToEmpty(as3));
            mRandomizedEncryptionRequired = flag;
            mUserAuthenticationRequired = flag1;
            mUserAuthenticationValidityDurationSeconds = l;
            mAttestationChallenge = Utils.cloneIfNotNull(abyte0);
            mUniqueIdIncluded = flag2;
            mUserAuthenticationValidWhileOnBody = flag3;
            mInvalidatedByBiometricEnrollment = flag4;
            return;
        }
    }

    public AlgorithmParameterSpec getAlgorithmParameterSpec()
    {
        return mSpec;
    }

    public byte[] getAttestationChallenge()
    {
        return Utils.cloneIfNotNull(mAttestationChallenge);
    }

    public String[] getBlockModes()
    {
        return ArrayUtils.cloneIfNotEmpty(mBlockModes);
    }

    public Date getCertificateNotAfter()
    {
        return Utils.cloneIfNotNull(mCertificateNotAfter);
    }

    public Date getCertificateNotBefore()
    {
        return Utils.cloneIfNotNull(mCertificateNotBefore);
    }

    public BigInteger getCertificateSerialNumber()
    {
        return mCertificateSerialNumber;
    }

    public X500Principal getCertificateSubject()
    {
        return mCertificateSubject;
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

    public int getKeySize()
    {
        return mKeySize;
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

    public String getKeystoreAlias()
    {
        return mKeystoreAlias;
    }

    public int getPurposes()
    {
        return mPurposes;
    }

    public String[] getSignaturePaddings()
    {
        return ArrayUtils.cloneIfNotEmpty(mSignaturePaddings);
    }

    public int getUid()
    {
        return mUid;
    }

    public int getUserAuthenticationValidityDurationSeconds()
    {
        return mUserAuthenticationValidityDurationSeconds;
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

    public boolean isUniqueIdIncluded()
    {
        return mUniqueIdIncluded;
    }

    public boolean isUserAuthenticationRequired()
    {
        return mUserAuthenticationRequired;
    }

    public boolean isUserAuthenticationValidWhileOnBody()
    {
        return mUserAuthenticationValidWhileOnBody;
    }

    private static final Date DEFAULT_CERT_NOT_AFTER = new Date(0x23d19d43c00L);
    private static final Date DEFAULT_CERT_NOT_BEFORE = new Date(0L);
    private static final BigInteger DEFAULT_CERT_SERIAL_NUMBER = new BigInteger("1");
    private static final X500Principal DEFAULT_CERT_SUBJECT = new X500Principal("CN=fake");
    private final byte mAttestationChallenge[];
    private final String mBlockModes[];
    private final Date mCertificateNotAfter;
    private final Date mCertificateNotBefore;
    private final BigInteger mCertificateSerialNumber;
    private final X500Principal mCertificateSubject;
    private final String mDigests[];
    private final String mEncryptionPaddings[];
    private final boolean mInvalidatedByBiometricEnrollment;
    private final int mKeySize;
    private final Date mKeyValidityForConsumptionEnd;
    private final Date mKeyValidityForOriginationEnd;
    private final Date mKeyValidityStart;
    private final String mKeystoreAlias;
    private final int mPurposes;
    private final boolean mRandomizedEncryptionRequired;
    private final String mSignaturePaddings[];
    private final AlgorithmParameterSpec mSpec;
    private final int mUid;
    private final boolean mUniqueIdIncluded;
    private final boolean mUserAuthenticationRequired;
    private final boolean mUserAuthenticationValidWhileOnBody;
    private final int mUserAuthenticationValidityDurationSeconds;

}

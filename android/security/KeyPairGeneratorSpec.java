// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security;

import android.content.Context;
import android.text.TextUtils;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Date;
import javax.security.auth.x500.X500Principal;

public final class KeyPairGeneratorSpec
    implements AlgorithmParameterSpec
{
    public static final class Builder
    {

        public KeyPairGeneratorSpec build()
        {
            return new KeyPairGeneratorSpec(mContext, mKeystoreAlias, mKeyType, mKeySize, mSpec, mSubjectDN, mSerialNumber, mStartDate, mEndDate, mFlags);
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

        public Builder setAlias(String s)
        {
            if(s == null)
            {
                throw new NullPointerException("alias == null");
            } else
            {
                mKeystoreAlias = s;
                return this;
            }
        }

        public Builder setEncryptionRequired()
        {
            mFlags = mFlags | 1;
            return this;
        }

        public Builder setEndDate(Date date)
        {
            if(date == null)
            {
                throw new NullPointerException("endDate == null");
            } else
            {
                mEndDate = date;
                return this;
            }
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

        public Builder setKeyType(String s)
            throws NoSuchAlgorithmException
        {
            if(s == null)
                throw new NullPointerException("keyType == null");
            try
            {
                android.security.keystore.KeyProperties.KeyAlgorithm.toKeymasterAsymmetricKeyAlgorithm(s);
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                throw new NoSuchAlgorithmException((new StringBuilder()).append("Unsupported key type: ").append(s).toString());
            }
            mKeyType = s;
            return this;
        }

        public Builder setSerialNumber(BigInteger biginteger)
        {
            if(biginteger == null)
            {
                throw new NullPointerException("serialNumber == null");
            } else
            {
                mSerialNumber = biginteger;
                return this;
            }
        }

        public Builder setStartDate(Date date)
        {
            if(date == null)
            {
                throw new NullPointerException("startDate == null");
            } else
            {
                mStartDate = date;
                return this;
            }
        }

        public Builder setSubject(X500Principal x500principal)
        {
            if(x500principal == null)
            {
                throw new NullPointerException("subject == null");
            } else
            {
                mSubjectDN = x500principal;
                return this;
            }
        }

        private final Context mContext;
        private Date mEndDate;
        private int mFlags;
        private int mKeySize;
        private String mKeyType;
        private String mKeystoreAlias;
        private BigInteger mSerialNumber;
        private AlgorithmParameterSpec mSpec;
        private Date mStartDate;
        private X500Principal mSubjectDN;

        public Builder(Context context)
        {
            mKeySize = -1;
            if(context == null)
            {
                throw new NullPointerException("context == null");
            } else
            {
                mContext = context;
                return;
            }
        }
    }


    public KeyPairGeneratorSpec(Context context, String s, String s1, int i, AlgorithmParameterSpec algorithmparameterspec, X500Principal x500principal, BigInteger biginteger, 
            Date date, Date date1, int j)
    {
        if(context == null)
            throw new IllegalArgumentException("context == null");
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("keyStoreAlias must not be empty");
        if(x500principal == null)
            throw new IllegalArgumentException("subjectDN == null");
        if(biginteger == null)
            throw new IllegalArgumentException("serialNumber == null");
        if(date == null)
            throw new IllegalArgumentException("startDate == null");
        if(date1 == null)
            throw new IllegalArgumentException("endDate == null");
        if(date1.before(date))
            throw new IllegalArgumentException("endDate < startDate");
        if(date1.before(date))
        {
            throw new IllegalArgumentException("endDate < startDate");
        } else
        {
            mContext = context;
            mKeystoreAlias = s;
            mKeyType = s1;
            mKeySize = i;
            mSpec = algorithmparameterspec;
            mSubjectDN = x500principal;
            mSerialNumber = biginteger;
            mStartDate = date;
            mEndDate = date1;
            mFlags = j;
            return;
        }
    }

    public AlgorithmParameterSpec getAlgorithmParameterSpec()
    {
        return mSpec;
    }

    public Context getContext()
    {
        return mContext;
    }

    public Date getEndDate()
    {
        return mEndDate;
    }

    public int getFlags()
    {
        return mFlags;
    }

    public int getKeySize()
    {
        return mKeySize;
    }

    public String getKeyType()
    {
        return mKeyType;
    }

    public String getKeystoreAlias()
    {
        return mKeystoreAlias;
    }

    public BigInteger getSerialNumber()
    {
        return mSerialNumber;
    }

    public Date getStartDate()
    {
        return mStartDate;
    }

    public X500Principal getSubjectDN()
    {
        return mSubjectDN;
    }

    public boolean isEncryptionRequired()
    {
        boolean flag = false;
        if((mFlags & 1) != 0)
            flag = true;
        return flag;
    }

    private final Context mContext;
    private final Date mEndDate;
    private final int mFlags;
    private final int mKeySize;
    private final String mKeyType;
    private final String mKeystoreAlias;
    private final BigInteger mSerialNumber;
    private final AlgorithmParameterSpec mSpec;
    private final Date mStartDate;
    private final X500Principal mSubjectDN;
}

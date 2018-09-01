// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import java.security.spec.KeySpec;
import java.util.Date;

// Referenced classes of package android.security.keystore:
//            Utils, ArrayUtils

public class KeyInfo
    implements KeySpec
{

    public KeyInfo(String s, boolean flag, int i, int j, Date date, Date date1, Date date2, 
            int k, String as[], String as1[], String as2[], String as3[], boolean flag1, int l, 
            boolean flag2, boolean flag3, boolean flag4)
    {
        mKeystoreAlias = s;
        mInsideSecureHardware = flag;
        mOrigin = i;
        mKeySize = j;
        mKeyValidityStart = Utils.cloneIfNotNull(date);
        mKeyValidityForOriginationEnd = Utils.cloneIfNotNull(date1);
        mKeyValidityForConsumptionEnd = Utils.cloneIfNotNull(date2);
        mPurposes = k;
        mEncryptionPaddings = ArrayUtils.cloneIfNotEmpty(ArrayUtils.nullToEmpty(as));
        mSignaturePaddings = ArrayUtils.cloneIfNotEmpty(ArrayUtils.nullToEmpty(as1));
        mDigests = ArrayUtils.cloneIfNotEmpty(ArrayUtils.nullToEmpty(as2));
        mBlockModes = ArrayUtils.cloneIfNotEmpty(ArrayUtils.nullToEmpty(as3));
        mUserAuthenticationRequired = flag1;
        mUserAuthenticationValidityDurationSeconds = l;
        mUserAuthenticationRequirementEnforcedBySecureHardware = flag2;
        mUserAuthenticationValidWhileOnBody = flag3;
        mInvalidatedByBiometricEnrollment = flag4;
    }

    public String[] getBlockModes()
    {
        return ArrayUtils.cloneIfNotEmpty(mBlockModes);
    }

    public String[] getDigests()
    {
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

    public int getOrigin()
    {
        return mOrigin;
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

    public boolean isInsideSecureHardware()
    {
        return mInsideSecureHardware;
    }

    public boolean isInvalidatedByBiometricEnrollment()
    {
        return mInvalidatedByBiometricEnrollment;
    }

    public boolean isUserAuthenticationRequired()
    {
        return mUserAuthenticationRequired;
    }

    public boolean isUserAuthenticationRequirementEnforcedBySecureHardware()
    {
        return mUserAuthenticationRequirementEnforcedBySecureHardware;
    }

    public boolean isUserAuthenticationValidWhileOnBody()
    {
        return mUserAuthenticationValidWhileOnBody;
    }

    private final String mBlockModes[];
    private final String mDigests[];
    private final String mEncryptionPaddings[];
    private final boolean mInsideSecureHardware;
    private final boolean mInvalidatedByBiometricEnrollment;
    private final int mKeySize;
    private final Date mKeyValidityForConsumptionEnd;
    private final Date mKeyValidityForOriginationEnd;
    private final Date mKeyValidityStart;
    private final String mKeystoreAlias;
    private final int mOrigin;
    private final int mPurposes;
    private final String mSignaturePaddings[];
    private final boolean mUserAuthenticationRequired;
    private final boolean mUserAuthenticationRequirementEnforcedBySecureHardware;
    private final boolean mUserAuthenticationValidWhileOnBody;
    private final int mUserAuthenticationValidityDurationSeconds;
}

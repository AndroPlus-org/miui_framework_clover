// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import java.math.BigInteger;
import java.security.spec.RSAKeyGenParameterSpec;

public class SoterRSAKeyGenParameterSpec extends RSAKeyGenParameterSpec
{

    public SoterRSAKeyGenParameterSpec(int i, BigInteger biginteger, boolean flag, boolean flag1, boolean flag2, String s, boolean flag3, 
            boolean flag4, boolean flag5)
    {
        super(i, biginteger);
        isForSoter = false;
        isAutoSignedWithAttkWhenGetPublicKey = false;
        isAutoSignedWithCommonkWhenGetPublicKey = false;
        mAutoSignedKeyNameWhenGetPublicKey = "";
        isSecmsgFidCounterSignedWhenSign = false;
        isAutoAddCounterWhenGetPublicKey = false;
        isNeedUseNextAttk = false;
        isForSoter = flag;
        isAutoSignedWithAttkWhenGetPublicKey = flag1;
        isAutoSignedWithCommonkWhenGetPublicKey = flag2;
        mAutoSignedKeyNameWhenGetPublicKey = s;
        isSecmsgFidCounterSignedWhenSign = flag3;
        isAutoAddCounterWhenGetPublicKey = flag4;
        isNeedUseNextAttk = flag5;
    }

    public SoterRSAKeyGenParameterSpec(boolean flag, boolean flag1, boolean flag2, String s, boolean flag3, boolean flag4, boolean flag5)
    {
        this(2048, RSAKeyGenParameterSpec.F4, flag, flag1, flag2, s, flag3, flag4, flag5);
    }

    public String getAutoSignedKeyNameWhenGetPublicKey()
    {
        return mAutoSignedKeyNameWhenGetPublicKey;
    }

    public boolean isAutoAddCounterWhenGetPublicKey()
    {
        return isAutoAddCounterWhenGetPublicKey;
    }

    public boolean isAutoSignedWithAttkWhenGetPublicKey()
    {
        return isAutoSignedWithAttkWhenGetPublicKey;
    }

    public boolean isAutoSignedWithCommonkWhenGetPublicKey()
    {
        return isAutoSignedWithCommonkWhenGetPublicKey;
    }

    public boolean isForSoter()
    {
        return isForSoter;
    }

    public boolean isNeedUseNextAttk()
    {
        return isNeedUseNextAttk;
    }

    public boolean isSecmsgFidCounterSignedWhenSign()
    {
        return isSecmsgFidCounterSignedWhenSign;
    }

    public void setAutoSignedKeyNameWhenGetPublicKey(String s)
    {
        mAutoSignedKeyNameWhenGetPublicKey = s;
    }

    public void setIsAutoAddCounterWhenGetPublicKey(boolean flag)
    {
        isAutoAddCounterWhenGetPublicKey = flag;
    }

    public void setIsAutoSignedWithAttkWhenGetPublicKey(boolean flag)
    {
        isAutoSignedWithAttkWhenGetPublicKey = flag;
    }

    public void setIsAutoSignedWithCommonkWhenGetPublicKey(boolean flag)
    {
        isAutoSignedWithCommonkWhenGetPublicKey = flag;
    }

    public void setIsForSoter(boolean flag)
    {
        isForSoter = flag;
    }

    public void setIsNeedUseNextAttk(boolean flag)
    {
        isNeedUseNextAttk = flag;
    }

    public void setIsSecmsgFidCounterSignedWhenSign(boolean flag)
    {
        isSecmsgFidCounterSignedWhenSign = flag;
    }

    public String toString()
    {
        return (new StringBuilder()).append("SoterRSAKeyGenParameterSpec{isForSoter=").append(isForSoter).append(", isAutoSignedWithAttkWhenGetPublicKey=").append(isAutoSignedWithAttkWhenGetPublicKey).append(", isAutoSignedWithCommonkWhenGetPublicKey=").append(isAutoSignedWithCommonkWhenGetPublicKey).append(", mAutoSignedKeyNameWhenGetPublicKey='").append(mAutoSignedKeyNameWhenGetPublicKey).append('\'').append(", isSecmsgFidCounterSignedWhenSign=").append(isSecmsgFidCounterSignedWhenSign).append(", isAutoAddCounterWhenGetPublicKey=").append(isAutoAddCounterWhenGetPublicKey).append(", isNeedUseNextAttk=").append(isNeedUseNextAttk).append('}').toString();
    }

    private boolean isAutoAddCounterWhenGetPublicKey;
    private boolean isAutoSignedWithAttkWhenGetPublicKey;
    private boolean isAutoSignedWithCommonkWhenGetPublicKey;
    private boolean isForSoter;
    private boolean isNeedUseNextAttk;
    private boolean isSecmsgFidCounterSignedWhenSign;
    private String mAutoSignedKeyNameWhenGetPublicKey;
}

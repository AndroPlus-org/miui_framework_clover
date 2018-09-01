// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import java.math.BigInteger;
import java.security.interfaces.RSAKey;

// Referenced classes of package android.security.keystore:
//            AndroidKeyStorePrivateKey

public class AndroidKeyStoreRSAPrivateKey extends AndroidKeyStorePrivateKey
    implements RSAKey
{

    public AndroidKeyStoreRSAPrivateKey(String s, int i, BigInteger biginteger)
    {
        super(s, i, "RSA");
        mModulus = biginteger;
    }

    public BigInteger getModulus()
    {
        return mModulus;
    }

    private final BigInteger mModulus;
}

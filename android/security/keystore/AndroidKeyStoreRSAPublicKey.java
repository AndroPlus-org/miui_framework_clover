// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import java.math.BigInteger;
import java.security.interfaces.RSAPublicKey;

// Referenced classes of package android.security.keystore:
//            AndroidKeyStorePublicKey

public class AndroidKeyStoreRSAPublicKey extends AndroidKeyStorePublicKey
    implements RSAPublicKey
{

    public AndroidKeyStoreRSAPublicKey(String s, int i, RSAPublicKey rsapublickey)
    {
        this(s, i, rsapublickey.getEncoded(), rsapublickey.getModulus(), rsapublickey.getPublicExponent());
        if(!"X.509".equalsIgnoreCase(rsapublickey.getFormat()))
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported key export format: ").append(rsapublickey.getFormat()).toString());
        else
            return;
    }

    public AndroidKeyStoreRSAPublicKey(String s, int i, byte abyte0[], BigInteger biginteger, BigInteger biginteger1)
    {
        super(s, i, "RSA", abyte0);
        mModulus = biginteger;
        mPublicExponent = biginteger1;
    }

    public BigInteger getModulus()
    {
        return mModulus;
    }

    public BigInteger getPublicExponent()
    {
        return mPublicExponent;
    }

    private final BigInteger mModulus;
    private final BigInteger mPublicExponent;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;

// Referenced classes of package android.security.keystore:
//            AndroidKeyStorePublicKey

public class AndroidKeyStoreECPublicKey extends AndroidKeyStorePublicKey
    implements ECPublicKey
{

    public AndroidKeyStoreECPublicKey(String s, int i, ECPublicKey ecpublickey)
    {
        this(s, i, ecpublickey.getEncoded(), ecpublickey.getParams(), ecpublickey.getW());
        if(!"X.509".equalsIgnoreCase(ecpublickey.getFormat()))
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported key export format: ").append(ecpublickey.getFormat()).toString());
        else
            return;
    }

    public AndroidKeyStoreECPublicKey(String s, int i, byte abyte0[], ECParameterSpec ecparameterspec, ECPoint ecpoint)
    {
        super(s, i, "EC", abyte0);
        mParams = ecparameterspec;
        mW = ecpoint;
    }

    public ECParameterSpec getParams()
    {
        return mParams;
    }

    public ECPoint getW()
    {
        return mW;
    }

    private final ECParameterSpec mParams;
    private final ECPoint mW;
}

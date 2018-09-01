// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import java.security.interfaces.ECKey;
import java.security.spec.ECParameterSpec;

// Referenced classes of package android.security.keystore:
//            AndroidKeyStorePrivateKey

public class AndroidKeyStoreECPrivateKey extends AndroidKeyStorePrivateKey
    implements ECKey
{

    public AndroidKeyStoreECPrivateKey(String s, int i, ECParameterSpec ecparameterspec)
    {
        super(s, i, "EC");
        mParams = ecparameterspec;
    }

    public ECParameterSpec getParams()
    {
        return mParams;
    }

    private final ECParameterSpec mParams;
}

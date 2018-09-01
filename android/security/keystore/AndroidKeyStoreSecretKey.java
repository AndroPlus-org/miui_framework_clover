// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import javax.crypto.SecretKey;

// Referenced classes of package android.security.keystore:
//            AndroidKeyStoreKey

public class AndroidKeyStoreSecretKey extends AndroidKeyStoreKey
    implements SecretKey
{

    public AndroidKeyStoreSecretKey(String s, int i, String s1)
    {
        super(s, i, s1);
    }
}

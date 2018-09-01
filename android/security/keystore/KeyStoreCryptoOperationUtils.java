// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.security.KeyStore;
import java.security.*;
import libcore.util.EmptyArray;

// Referenced classes of package android.security.keystore:
//            AndroidKeyStoreKey, UserNotAuthenticatedException

abstract class KeyStoreCryptoOperationUtils
{

    private KeyStoreCryptoOperationUtils()
    {
    }

    public static GeneralSecurityException getExceptionForCipherInit(KeyStore keystore, AndroidKeyStoreKey androidkeystorekey, int i)
    {
        if(i == 1)
            return null;
        switch(i)
        {
        case -54: 
        case -53: 
        default:
            return getInvalidKeyExceptionForInit(keystore, androidkeystorekey, i);

        case -52: 
            return new InvalidAlgorithmParameterException("Invalid IV");

        case -55: 
            return new InvalidAlgorithmParameterException("Caller-provided IV not permitted");
        }
    }

    static InvalidKeyException getInvalidKeyExceptionForInit(KeyStore keystore, AndroidKeyStoreKey androidkeystorekey, int i)
    {
        if(i == 1)
            return null;
        keystore = keystore.getInvalidKeyException(androidkeystorekey.getAlias(), androidkeystorekey.getUid(), i);
        i;
        JVM INSTR tableswitch 15 15: default 40
    //                   15 42;
           goto _L1 _L2
_L1:
        return keystore;
_L2:
        if(keystore instanceof UserNotAuthenticatedException)
            return null;
        if(true) goto _L1; else goto _L3
_L3:
    }

    static byte[] getRandomBytesToMixIntoKeystoreRng(SecureRandom securerandom, int i)
    {
        if(i <= 0)
            return EmptyArray.BYTE;
        SecureRandom securerandom1 = securerandom;
        if(securerandom == null)
            securerandom1 = getRng();
        securerandom = new byte[i];
        securerandom1.nextBytes(securerandom);
        return securerandom;
    }

    private static SecureRandom getRng()
    {
        if(sRng == null)
            sRng = new SecureRandom();
        return sRng;
    }

    private static volatile SecureRandom sRng;
}

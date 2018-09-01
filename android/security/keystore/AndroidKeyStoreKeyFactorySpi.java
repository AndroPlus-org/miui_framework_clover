// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.security.KeyStore;
import java.security.*;
import java.security.spec.*;

// Referenced classes of package android.security.keystore:
//            KeyGenParameterSpec, AndroidKeyStorePrivateKey, AndroidKeyStorePublicKey, KeyInfo, 
//            AndroidKeyStoreSecretKeyFactorySpi, AndroidKeyStoreRSAPublicKey, AndroidKeyStoreECPublicKey

public class AndroidKeyStoreKeyFactorySpi extends KeyFactorySpi
{

    public AndroidKeyStoreKeyFactorySpi()
    {
    }

    protected PrivateKey engineGeneratePrivate(KeySpec keyspec)
        throws InvalidKeySpecException
    {
        throw new InvalidKeySpecException((new StringBuilder()).append("To generate a key pair in Android Keystore, use KeyPairGenerator initialized with ").append(android/security/keystore/KeyGenParameterSpec.getName()).toString());
    }

    protected PublicKey engineGeneratePublic(KeySpec keyspec)
        throws InvalidKeySpecException
    {
        throw new InvalidKeySpecException((new StringBuilder()).append("To generate a key pair in Android Keystore, use KeyPairGenerator initialized with ").append(android/security/keystore/KeyGenParameterSpec.getName()).toString());
    }

    protected KeySpec engineGetKeySpec(Key key, Class class1)
        throws InvalidKeySpecException
    {
        if(key == null)
            throw new InvalidKeySpecException("key == null");
        if(!(key instanceof AndroidKeyStorePrivateKey) && (key instanceof AndroidKeyStorePublicKey) ^ true)
            throw new InvalidKeySpecException((new StringBuilder()).append("Unsupported key type: ").append(key.getClass().getName()).append(". This KeyFactory supports only Android Keystore asymmetric keys").toString());
        if(class1 == null)
            throw new InvalidKeySpecException("keySpecClass == null");
        if(android/security/keystore/KeyInfo.equals(class1))
        {
            if(!(key instanceof AndroidKeyStorePrivateKey))
                throw new InvalidKeySpecException((new StringBuilder()).append("Unsupported key type: ").append(key.getClass().getName()).append(". KeyInfo can be obtained only for Android Keystore private keys").toString());
            AndroidKeyStorePrivateKey androidkeystoreprivatekey = (AndroidKeyStorePrivateKey)key;
            class1 = androidkeystoreprivatekey.getAlias();
            if(class1.startsWith("USRPKEY_"))
            {
                key = class1.substring("USRPKEY_".length());
                return AndroidKeyStoreSecretKeyFactorySpi.getKeyInfo(mKeyStore, key, class1, androidkeystoreprivatekey.getUid());
            } else
            {
                throw new InvalidKeySpecException((new StringBuilder()).append("Invalid key alias: ").append(class1).toString());
            }
        }
        if(java/security/spec/X509EncodedKeySpec.equals(class1))
            if(!(key instanceof AndroidKeyStorePublicKey))
                throw new InvalidKeySpecException((new StringBuilder()).append("Unsupported key type: ").append(key.getClass().getName()).append(". X509EncodedKeySpec can be obtained only for Android Keystore public").append(" keys").toString());
            else
                return new X509EncodedKeySpec(((AndroidKeyStorePublicKey)key).getEncoded());
        if(java/security/spec/PKCS8EncodedKeySpec.equals(class1))
            if(key instanceof AndroidKeyStorePrivateKey)
                throw new InvalidKeySpecException("Key material export of Android Keystore private keys is not supported");
            else
                throw new InvalidKeySpecException("Cannot export key material of public key in PKCS#8 format. Only X.509 format (X509EncodedKeySpec) supported for public keys.");
        if(java/security/spec/RSAPublicKeySpec.equals(class1))
        {
            if(key instanceof AndroidKeyStoreRSAPublicKey)
            {
                key = (AndroidKeyStoreRSAPublicKey)key;
                return new RSAPublicKeySpec(key.getModulus(), key.getPublicExponent());
            }
            class1 = (new StringBuilder()).append("Obtaining RSAPublicKeySpec not supported for ").append(key.getAlgorithm()).append(" ");
            if(key instanceof AndroidKeyStorePrivateKey)
                key = "private";
            else
                key = "public";
            throw new InvalidKeySpecException(class1.append(key).append(" key").toString());
        }
        if(java/security/spec/ECPublicKeySpec.equals(class1))
        {
            if(key instanceof AndroidKeyStoreECPublicKey)
            {
                key = (AndroidKeyStoreECPublicKey)key;
                return new ECPublicKeySpec(key.getW(), key.getParams());
            }
            class1 = (new StringBuilder()).append("Obtaining ECPublicKeySpec not supported for ").append(key.getAlgorithm()).append(" ");
            if(key instanceof AndroidKeyStorePrivateKey)
                key = "private";
            else
                key = "public";
            throw new InvalidKeySpecException(class1.append(key).append(" key").toString());
        } else
        {
            throw new InvalidKeySpecException((new StringBuilder()).append("Unsupported key spec: ").append(class1.getName()).toString());
        }
    }

    protected Key engineTranslateKey(Key key)
        throws InvalidKeyException
    {
        if(key == null)
            throw new InvalidKeyException("key == null");
        if(!(key instanceof AndroidKeyStorePrivateKey) && (key instanceof AndroidKeyStorePublicKey) ^ true)
            throw new InvalidKeyException("To import a key into Android Keystore, use KeyStore.setEntry");
        else
            return key;
    }

    private final KeyStore mKeyStore = KeyStore.getInstance();
}

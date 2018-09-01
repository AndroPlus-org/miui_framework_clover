// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.security.keymaster.ExportResult;
import android.security.keymaster.KeyCharacteristics;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.ProviderException;
import java.security.Security;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.interfaces.ECKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.Mac;

// Referenced classes of package android.security.keystore:
//            AndroidKeyStorePublicKey, AndroidKeyStoreECPrivateKey, AndroidKeyStoreRSAPrivateKey, AndroidKeyStoreECPublicKey, 
//            AndroidKeyStoreRSAPublicKey, AndroidKeyStoreLoadStoreParameter, KeyStoreCryptoOperation, AndroidKeyStoreBCWorkaroundProvider, 
//            AndroidKeyStorePrivateKey, AndroidKeyStoreSecretKey

public class AndroidKeyStoreProvider extends Provider
{

    public AndroidKeyStoreProvider()
    {
        super("AndroidKeyStore", 1.0D, "Android KeyStore security provider");
        put("KeyStore.AndroidKeyStore", "android.security.keystore.AndroidKeyStoreSpi");
        put("KeyPairGenerator.EC", "android.security.keystore.AndroidKeyStoreKeyPairGeneratorSpi$EC");
        put("KeyPairGenerator.RSA", "android.security.keystore.AndroidKeyStoreKeyPairGeneratorSpi$RSA");
        putKeyFactoryImpl("EC");
        putKeyFactoryImpl("RSA");
        put("KeyGenerator.AES", "android.security.keystore.AndroidKeyStoreKeyGeneratorSpi$AES");
        put("KeyGenerator.HmacSHA1", "android.security.keystore.AndroidKeyStoreKeyGeneratorSpi$HmacSHA1");
        put("KeyGenerator.HmacSHA224", "android.security.keystore.AndroidKeyStoreKeyGeneratorSpi$HmacSHA224");
        put("KeyGenerator.HmacSHA256", "android.security.keystore.AndroidKeyStoreKeyGeneratorSpi$HmacSHA256");
        put("KeyGenerator.HmacSHA384", "android.security.keystore.AndroidKeyStoreKeyGeneratorSpi$HmacSHA384");
        put("KeyGenerator.HmacSHA512", "android.security.keystore.AndroidKeyStoreKeyGeneratorSpi$HmacSHA512");
        putSecretKeyFactoryImpl("AES");
        putSecretKeyFactoryImpl("HmacSHA1");
        putSecretKeyFactoryImpl("HmacSHA224");
        putSecretKeyFactoryImpl("HmacSHA256");
        putSecretKeyFactoryImpl("HmacSHA384");
        putSecretKeyFactoryImpl("HmacSHA512");
    }

    public static AndroidKeyStorePrivateKey getAndroidKeyStorePrivateKey(AndroidKeyStorePublicKey androidkeystorepublickey)
    {
        String s = androidkeystorepublickey.getAlgorithm();
        if("EC".equalsIgnoreCase(s))
            return new AndroidKeyStoreECPrivateKey(androidkeystorepublickey.getAlias(), androidkeystorepublickey.getUid(), ((ECKey)androidkeystorepublickey).getParams());
        if("RSA".equalsIgnoreCase(s))
            return new AndroidKeyStoreRSAPrivateKey(androidkeystorepublickey.getAlias(), androidkeystorepublickey.getUid(), ((RSAKey)androidkeystorepublickey).getModulus());
        else
            throw new ProviderException((new StringBuilder()).append("Unsupported Android Keystore public key algorithm: ").append(s).toString());
    }

    public static AndroidKeyStorePublicKey getAndroidKeyStorePublicKey(String s, int i, String s1, byte abyte0[])
    {
        try
        {
            KeyFactory keyfactory = KeyFactory.getInstance(s1);
            X509EncodedKeySpec x509encodedkeyspec = JVM INSTR new #156 <Class X509EncodedKeySpec>;
            x509encodedkeyspec.X509EncodedKeySpec(abyte0);
            abyte0 = keyfactory.generatePublic(x509encodedkeyspec);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new ProviderException((new StringBuilder()).append("Failed to obtain ").append(s1).append(" KeyFactory").toString(), s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new ProviderException("Invalid X.509 encoding of public key", s);
        }
        if("EC".equalsIgnoreCase(s1))
            return new AndroidKeyStoreECPublicKey(s, i, (ECPublicKey)abyte0);
        if("RSA".equalsIgnoreCase(s1))
            return new AndroidKeyStoreRSAPublicKey(s, i, (RSAPublicKey)abyte0);
        else
            throw new ProviderException((new StringBuilder()).append("Unsupported Android Keystore public key algorithm: ").append(s1).toString());
    }

    public static KeyStore getKeyStoreForUid(int i)
        throws KeyStoreException, NoSuchProviderException
    {
        KeyStore keystore = KeyStore.getInstance("AndroidKeyStore", "AndroidKeyStore");
        try
        {
            AndroidKeyStoreLoadStoreParameter androidkeystoreloadstoreparameter = JVM INSTR new #203 <Class AndroidKeyStoreLoadStoreParameter>;
            androidkeystoreloadstoreparameter.AndroidKeyStoreLoadStoreParameter(i);
            keystore.load(androidkeystoreloadstoreparameter);
        }
        catch(Object obj)
        {
            throw new KeyStoreException((new StringBuilder()).append("Failed to load AndroidKeyStore KeyStore for UID ").append(i).toString(), ((Throwable) (obj)));
        }
        return keystore;
    }

    public static long getKeyStoreOperationHandle(Object obj)
    {
        if(obj == null)
            throw new NullPointerException();
        Object obj1;
        if(obj instanceof Signature)
            obj1 = ((Signature)obj).getCurrentSpi();
        else
        if(obj instanceof Mac)
            obj1 = ((Mac)obj).getCurrentSpi();
        else
        if(obj instanceof Cipher)
            obj1 = ((Cipher)obj).getCurrentSpi();
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported crypto primitive: ").append(obj).append(". Supported: Signature, Mac, Cipher").toString());
        if(obj1 == null)
            throw new IllegalStateException("Crypto primitive not initialized");
        if(!(obj1 instanceof KeyStoreCryptoOperation))
            throw new IllegalArgumentException((new StringBuilder()).append("Crypto primitive not backed by AndroidKeyStore provider: ").append(obj).append(", spi: ").append(obj1).toString());
        else
            return ((KeyStoreCryptoOperation)obj1).getOperationHandle();
    }

    public static void install()
    {
        Provider aprovider[] = Security.getProviders();
        byte byte0 = -1;
        int i = 0;
        do
        {
label0:
            {
                int j = byte0;
                if(i < aprovider.length)
                {
                    if(!"BC".equals(aprovider[i].getName()))
                        break label0;
                    j = i;
                }
                Security.addProvider(new AndroidKeyStoreProvider());
                AndroidKeyStoreBCWorkaroundProvider androidkeystorebcworkaroundprovider = new AndroidKeyStoreBCWorkaroundProvider();
                if(j != -1)
                    Security.insertProviderAt(androidkeystorebcworkaroundprovider, j + 1);
                else
                    Security.addProvider(androidkeystorebcworkaroundprovider);
                return;
            }
            i++;
        } while(true);
    }

    public static KeyPair loadAndroidKeyStoreKeyPairFromKeystore(android.security.KeyStore keystore, String s, int i)
        throws UnrecoverableKeyException
    {
        keystore = loadAndroidKeyStorePublicKeyFromKeystore(keystore, s, i);
        return new KeyPair(keystore, getAndroidKeyStorePrivateKey(keystore));
    }

    public static AndroidKeyStorePrivateKey loadAndroidKeyStorePrivateKeyFromKeystore(android.security.KeyStore keystore, String s, int i)
        throws UnrecoverableKeyException
    {
        return (AndroidKeyStorePrivateKey)loadAndroidKeyStoreKeyPairFromKeystore(keystore, s, i).getPrivate();
    }

    public static AndroidKeyStorePublicKey loadAndroidKeyStorePublicKeyFromKeystore(android.security.KeyStore keystore, String s, int i)
        throws UnrecoverableKeyException
    {
        Object obj = new KeyCharacteristics();
        int j = keystore.getKeyCharacteristics(s, null, null, i, ((KeyCharacteristics) (obj)));
        if(j != 1)
            throw (UnrecoverableKeyException)(new UnrecoverableKeyException("Failed to obtain information about private key")).initCause(android.security.KeyStore.getKeyStoreException(j));
        keystore = keystore.exportKey(s, 0, null, null, i);
        if(((ExportResult) (keystore)).resultCode != 1)
            throw (UnrecoverableKeyException)(new UnrecoverableKeyException("Failed to obtain X.509 form of public key")).initCause(android.security.KeyStore.getKeyStoreException(((ExportResult) (keystore)).resultCode));
        keystore = ((ExportResult) (keystore)).exportData;
        obj = ((KeyCharacteristics) (obj)).getEnum(0x10000002);
        if(obj == null)
            throw new UnrecoverableKeyException("Key algorithm unknown");
        try
        {
            obj = KeyProperties.KeyAlgorithm.fromKeymasterAsymmetricKeyAlgorithm(((Integer) (obj)).intValue());
        }
        // Misplaced declaration of an exception variable
        catch(android.security.KeyStore keystore)
        {
            throw (UnrecoverableKeyException)(new UnrecoverableKeyException("Failed to load private key")).initCause(keystore);
        }
        return getAndroidKeyStorePublicKey(s, i, ((String) (obj)), keystore);
    }

    public static AndroidKeyStoreSecretKey loadAndroidKeyStoreSecretKeyFromKeystore(android.security.KeyStore keystore, String s, int i)
        throws UnrecoverableKeyException
    {
        Object obj = new KeyCharacteristics();
        int j = keystore.getKeyCharacteristics(s, null, null, i, ((KeyCharacteristics) (obj)));
        if(j != 1)
            throw (UnrecoverableKeyException)(new UnrecoverableKeyException("Failed to obtain information about key")).initCause(android.security.KeyStore.getKeyStoreException(j));
        keystore = ((KeyCharacteristics) (obj)).getEnum(0x10000002);
        if(keystore == null)
            throw new UnrecoverableKeyException("Key algorithm unknown");
        obj = ((KeyCharacteristics) (obj)).getEnums(0x20000005);
        if(((List) (obj)).isEmpty())
            j = -1;
        else
            j = ((Integer)((List) (obj)).get(0)).intValue();
        try
        {
            keystore = KeyProperties.KeyAlgorithm.fromKeymasterSecretKeyAlgorithm(keystore.intValue(), j);
        }
        // Misplaced declaration of an exception variable
        catch(android.security.KeyStore keystore)
        {
            throw (UnrecoverableKeyException)(new UnrecoverableKeyException("Unsupported secret key type")).initCause(keystore);
        }
        return new AndroidKeyStoreSecretKey(s, i, keystore);
    }

    private void putKeyFactoryImpl(String s)
    {
        put((new StringBuilder()).append("KeyFactory.").append(s).toString(), "android.security.keystore.AndroidKeyStoreKeyFactorySpi");
    }

    private void putSecretKeyFactoryImpl(String s)
    {
        put((new StringBuilder()).append("SecretKeyFactory.").append(s).toString(), "android.security.keystore.AndroidKeyStoreSecretKeyFactorySpi");
    }

    private static final String PACKAGE_NAME = "android.security.keystore";
    public static final String PROVIDER_NAME = "AndroidKeyStore";
}

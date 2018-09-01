// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.os.SystemProperties;
import android.security.KeyStore;
import android.security.keymaster.ExportResult;
import android.security.keymaster.KeyCharacteristics;
import android.util.Log;
import java.security.*;
import java.security.interfaces.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import miui.util.FeatureParser;
import org.json.JSONException;

// Referenced classes of package android.security.keystore:
//            AndroidKeyStorePublicKey, AndroidKeyStoreECPrivateKey, AndroidKeyStoreRSAPrivateKey, SoterUtil, 
//            AndroidKeyStoreECPublicKey, AndroidKeyStoreRSAPublicKey, AndroidKeyStorePrivateKey

public class SoterKeyStoreProvider extends Provider
{

    public SoterKeyStoreProvider()
    {
        super("SoterKeyStore", 1.0D, "provider for soter");
        put("KeyPairGenerator.RSA", "android.security.keystore.SoterKeyStoreKeyPairRSAGeneratorSpi");
        put("KeyStore.SoterKeyStore", "android.security.keystore.SoterKeyStoreSpi");
        putKeyFactoryImpl("RSA");
    }

    public static AndroidKeyStorePrivateKey getAndroidKeyStorePrivateKey(AndroidKeyStorePublicKey androidkeystorepublickey)
    {
        String s = androidkeystorepublickey.getAlgorithm();
        if("EC".equalsIgnoreCase(s))
            return new AndroidKeyStoreECPrivateKey(androidkeystorepublickey.getAlias(), -1, ((ECKey)androidkeystorepublickey).getParams());
        if("RSA".equalsIgnoreCase(s))
            return new AndroidKeyStoreRSAPrivateKey(androidkeystorepublickey.getAlias(), -1, ((RSAKey)androidkeystorepublickey).getModulus());
        else
            throw new ProviderException((new StringBuilder()).append("Unsupported Android Keystore public key algorithm: ").append(s).toString());
    }

    public static AndroidKeyStorePublicKey getAndroidKeyStorePublicKey(String s, String s1, byte abyte0[])
    {
        KeyFactory keyfactory;
        byte abyte1[];
        try
        {
            keyfactory = KeyFactory.getInstance(s1);
            abyte1 = SoterUtil.getDataFromRaw(abyte0, "pub_key");
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
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new ProviderException("Not in json format");
        }
        if(abyte1 == null)
            break MISSING_BLOCK_LABEL_65;
        abyte0 = JVM INSTR new #125 <Class X509EncodedKeySpec>;
        abyte0.X509EncodedKeySpec(abyte1);
        abyte0 = keyfactory.generatePublic(abyte0);
        if("EC".equalsIgnoreCase(s1))
        {
            Log.d("Soter", "AndroidKeyStoreECPublicKey");
            return new AndroidKeyStoreECPublicKey(s, -1, (ECPublicKey)abyte0);
        }
        break MISSING_BLOCK_LABEL_134;
        s = JVM INSTR new #151 <Class NullPointerException>;
        s.NullPointerException("invalid soter public key");
        throw s;
        if("RSA".equalsIgnoreCase(s1))
        {
            Log.d("Soter", "AndroidKeyStoreRSAPublicKey");
            return new AndroidKeyStoreRSAPublicKey(s, -1, (RSAPublicKey)abyte0);
        } else
        {
            throw new ProviderException((new StringBuilder()).append("Unsupported Android Keystore public key algorithm: ").append(s1).toString());
        }
    }

    public static AndroidKeyStorePublicKey getJsonPublicKey(String s, String s1, byte abyte0[])
    {
        Object obj;
        KeyFactory keyfactory;
        byte abyte1[];
        try
        {
            keyfactory = KeyFactory.getInstance(s1);
            abyte1 = SoterUtil.getDataFromRaw(abyte0, "pub_key");
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
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new ProviderException("Not in json format");
        }
        if(abyte1 == null)
            break MISSING_BLOCK_LABEL_70;
        obj = JVM INSTR new #125 <Class X509EncodedKeySpec>;
        ((X509EncodedKeySpec) (obj)).X509EncodedKeySpec(abyte1);
        obj = keyfactory.generatePublic(((java.security.spec.KeySpec) (obj)));
        if("EC".equalsIgnoreCase(s1))
        {
            Log.d("Soter", "AndroidKeyStoreECPublicKey");
            return new AndroidKeyStoreECPublicKey(s, -1, (ECPublicKey)obj);
        }
        break MISSING_BLOCK_LABEL_139;
        s = JVM INSTR new #151 <Class NullPointerException>;
        s.NullPointerException("invalid soter public key");
        throw s;
        if("RSA".equalsIgnoreCase(s1))
        {
            Log.d("Soter", "getJsonPublicKey");
            s1 = (RSAPublicKey)obj;
            return new AndroidKeyStoreRSAPublicKey(s, -1, abyte0, s1.getModulus(), s1.getPublicExponent());
        } else
        {
            throw new ProviderException((new StringBuilder()).append("Unsupported Android Keystore public key algorithm: ").append(s1).toString());
        }
    }

    public static void install()
    {
        int i;
        int j;
        i = FeatureParser.getInteger("type_soter_finger_pay", 0);
        j = SystemProperties.getInt("persist.sys.pay.soter", 0);
        Log.i("Soter", (new StringBuilder()).append("SoterKeyStoreProvider install, type:").append(i).append(" key:").append(j).toString());
        i;
        JVM INSTR tableswitch 1 2: default 72
    //                   1 73
    //                   2 92;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        if(1 == j)
            Security.addProvider(new SoterKeyStoreProvider());
        continue; /* Loop/switch isn't completed */
_L3:
        Security.addProvider(new SoterKeyStoreProvider());
        if(true) goto _L1; else goto _L4
_L4:
    }

    public static KeyPair loadAndroidKeyStoreKeyPairFromKeystore(KeyStore keystore, String s)
        throws UnrecoverableKeyException
    {
        keystore = loadAndroidKeyStorePublicKeyFromKeystore(keystore, s);
        return new KeyPair(keystore, getAndroidKeyStorePrivateKey(keystore));
    }

    public static AndroidKeyStorePrivateKey loadAndroidKeyStorePrivateKeyFromKeystore(KeyStore keystore, String s)
        throws UnrecoverableKeyException
    {
        return (AndroidKeyStorePrivateKey)loadAndroidKeyStoreKeyPairFromKeystore(keystore, s).getPrivate();
    }

    public static AndroidKeyStorePublicKey loadAndroidKeyStorePublicKeyFromKeystore(KeyStore keystore, String s)
        throws UnrecoverableKeyException
    {
        Object obj = new KeyCharacteristics();
        int i = keystore.getKeyCharacteristics(s, null, null, ((KeyCharacteristics) (obj)));
        if(i != 1)
            throw (UnrecoverableKeyException)(new UnrecoverableKeyException("Failed to obtain information about private key")).initCause(KeyStore.getKeyStoreException(i));
        keystore = keystore.exportKey(s, 0, null, null);
        if(((ExportResult) (keystore)).resultCode != 1)
            throw (UnrecoverableKeyException)(new UnrecoverableKeyException("Failed to obtain X.509 form of public key")).initCause(KeyStore.getKeyStoreException(i));
        keystore = ((ExportResult) (keystore)).exportData;
        obj = ((KeyCharacteristics) (obj)).getEnum(0x10000002);
        if(obj == null)
            throw new UnrecoverableKeyException("Key algorithm unknown");
        try
        {
            obj = KeyProperties.KeyAlgorithm.fromKeymasterAsymmetricKeyAlgorithm(((Integer) (obj)).intValue());
        }
        // Misplaced declaration of an exception variable
        catch(KeyStore keystore)
        {
            throw (UnrecoverableKeyException)(new UnrecoverableKeyException("Failed to load private key")).initCause(keystore);
        }
        return getAndroidKeyStorePublicKey(s, ((String) (obj)), keystore);
    }

    public static AndroidKeyStorePublicKey loadJsonPublicKeyFromKeystore(KeyStore keystore, String s)
        throws UnrecoverableKeyException
    {
        Object obj = new KeyCharacteristics();
        int i = keystore.getKeyCharacteristics(s, null, null, ((KeyCharacteristics) (obj)));
        if(i != 1)
            throw (UnrecoverableKeyException)(new UnrecoverableKeyException("Failed to obtain information about private key")).initCause(KeyStore.getKeyStoreException(i));
        keystore = keystore.exportKey(s, 0, null, null);
        if(((ExportResult) (keystore)).resultCode != 1)
            throw (UnrecoverableKeyException)(new UnrecoverableKeyException("Failed to obtain X.509 form of public key")).initCause(KeyStore.getKeyStoreException(i));
        keystore = ((ExportResult) (keystore)).exportData;
        obj = ((KeyCharacteristics) (obj)).getEnum(0x10000002);
        if(obj == null)
            throw new UnrecoverableKeyException("Key algorithm unknown");
        try
        {
            obj = KeyProperties.KeyAlgorithm.fromKeymasterAsymmetricKeyAlgorithm(((Integer) (obj)).intValue());
        }
        // Misplaced declaration of an exception variable
        catch(KeyStore keystore)
        {
            throw (UnrecoverableKeyException)(new UnrecoverableKeyException("Failed to load private key")).initCause(keystore);
        }
        return getJsonPublicKey(s, ((String) (obj)), keystore);
    }

    private void putKeyFactoryImpl(String s)
    {
        put((new StringBuilder()).append("KeyFactory.").append(s).toString(), "android.security.keystore.AndroidKeyStoreKeyFactorySpi");
    }

    private static final String ANDROID_PACKAGE_NAME = "android.security.keystore";
    private static final int DEVICE_TYPE_NEW = 2;
    private static final int DEVICE_TYPE_STOCK = 1;
    public static final String PROVIDER_NAME = "SoterKeyStore";
    private static final String SOTER_PACKAGE_NAME = "android.security.keystore";
}

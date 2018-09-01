// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.security.*;
import android.security.keymaster.KeyCharacteristics;
import android.security.keymaster.KeymasterArguments;
import android.util.Log;
import java.io.*;
import java.security.*;
import java.security.cert.*;
import java.util.*;
import javax.crypto.SecretKey;
import libcore.util.EmptyArray;

// Referenced classes of package android.security.keystore:
//            KeyProtection, AndroidKeyStorePrivateKey, AndroidKeyStoreKey, KeymasterUtils, 
//            AndroidKeyStoreSecretKey, AndroidKeyStoreProvider, AndroidKeyStoreLoadStoreParameter, DelegatingX509Certificate

public class AndroidKeyStoreSpi extends KeyStoreSpi
{
    static class KeyStoreX509Certificate extends DelegatingX509Certificate
    {

        public PublicKey getPublicKey()
        {
            PublicKey publickey = super.getPublicKey();
            return AndroidKeyStoreProvider.getAndroidKeyStorePublicKey(mPrivateKeyAlias, mPrivateKeyUid, publickey.getAlgorithm(), publickey.getEncoded());
        }

        private final String mPrivateKeyAlias;
        private final int mPrivateKeyUid;

        KeyStoreX509Certificate(String s, int i, X509Certificate x509certificate)
        {
            super(x509certificate);
            mPrivateKeyAlias = s;
            mPrivateKeyUid = i;
        }
    }


    public AndroidKeyStoreSpi()
    {
        mUid = -1;
    }

    private Certificate getCertificateForPrivateKeyEntry(String s, byte abyte0[])
    {
        abyte0 = toCertificate(abyte0);
        if(abyte0 == null)
            return null;
        s = (new StringBuilder()).append("USRPKEY_").append(s).toString();
        if(mKeyStore.contains(s, mUid))
            return wrapIntoKeyStoreCertificate(s, mUid, abyte0);
        else
            return abyte0;
    }

    private Certificate getCertificateForTrustedCertificateEntry(byte abyte0[])
    {
        return toCertificate(abyte0);
    }

    private static KeyProtection getLegacyKeyProtectionParameter(PrivateKey privatekey)
        throws KeyStoreException
    {
        privatekey = privatekey.getAlgorithm();
        if("EC".equalsIgnoreCase(privatekey))
        {
            privatekey = new KeyProtection.Builder(12);
            privatekey.setDigests(new String[] {
                "NONE", "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512"
            });
        } else
        if("RSA".equalsIgnoreCase(privatekey))
        {
            privatekey = new KeyProtection.Builder(15);
            privatekey.setDigests(new String[] {
                "NONE", "MD5", "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512"
            });
            privatekey.setEncryptionPaddings(new String[] {
                "NoPadding", "PKCS1Padding", "OAEPPadding"
            });
            privatekey.setSignaturePaddings(new String[] {
                "PKCS1", "PSS"
            });
            privatekey.setRandomizedEncryptionRequired(false);
        } else
        {
            throw new KeyStoreException((new StringBuilder()).append("Unsupported key algorithm: ").append(privatekey).toString());
        }
        privatekey.setUserAuthenticationRequired(false);
        return privatekey.build();
    }

    private Date getModificationDate(String s)
    {
        long l = mKeyStore.getmtime(s, mUid);
        if(l == -1L)
            return null;
        else
            return new Date(l);
    }

    private Set getUniqueAliases()
    {
        String as[] = mKeyStore.list("", mUid);
        if(as == null)
            return new HashSet();
        HashSet hashset = new HashSet(as.length);
        int i = 0;
        int j = as.length;
        while(i < j) 
        {
            String s = as[i];
            int k = s.indexOf('_');
            if(k == -1 || s.length() <= k)
                Log.e("AndroidKeyStore", (new StringBuilder()).append("invalid alias: ").append(s).toString());
            else
                hashset.add(new String(s.substring(k + 1)));
            i++;
        }
        return hashset;
    }

    private boolean isCertificateEntry(String s)
    {
        if(s == null)
            throw new NullPointerException("alias == null");
        else
            return mKeyStore.contains((new StringBuilder()).append("CACERT_").append(s).toString(), mUid);
    }

    private boolean isKeyEntry(String s)
    {
        boolean flag;
        if(!isPrivateKeyEntry(s))
            flag = isSecretKeyEntry(s);
        else
            flag = true;
        return flag;
    }

    private boolean isPrivateKeyEntry(String s)
    {
        if(s == null)
            throw new NullPointerException("alias == null");
        else
            return mKeyStore.contains((new StringBuilder()).append("USRPKEY_").append(s).toString(), mUid);
    }

    private boolean isSecretKeyEntry(String s)
    {
        if(s == null)
            throw new NullPointerException("alias == null");
        else
            return mKeyStore.contains((new StringBuilder()).append("USRSKEY_").append(s).toString(), mUid);
    }

    private void setPrivateKeyEntry(String s, PrivateKey privatekey, Certificate acertificate[], java.security.KeyStore.ProtectionParameter protectionparameter)
        throws KeyStoreException
    {
        int i = 0;
        if(protectionparameter != null) goto _L2; else goto _L1
_L1:
        Object obj = getLegacyKeyProtectionParameter(privatekey);
_L4:
        if(acertificate == null || acertificate.length == 0)
            throw new KeyStoreException("Must supply at least one Certificate with PrivateKey");
        break MISSING_BLOCK_LABEL_170;
_L2:
        if(protectionparameter instanceof KeyStoreParameter)
        {
            KeyProtection keyprotection = getLegacyKeyProtectionParameter(privatekey);
            obj = keyprotection;
            if(((KeyStoreParameter)protectionparameter).isEncryptionRequired())
            {
                i = 1;
                obj = keyprotection;
            }
            continue; /* Loop/switch isn't completed */
        }
        if(!(protectionparameter instanceof KeyProtection))
            break; /* Loop/switch isn't completed */
        protectionparameter = (KeyProtection)protectionparameter;
        obj = protectionparameter;
        if(protectionparameter.isCriticalToDeviceEncryption())
        {
            i = 8;
            obj = protectionparameter;
        }
        if(true) goto _L4; else goto _L3
_L3:
        throw new KeyStoreException((new StringBuilder()).append("Unsupported protection parameter class:").append(protectionparameter.getClass().getName()).append(". Supported: ").append(android/security/keystore/KeyProtection.getName()).append(", ").append(android/security/KeyStoreParameter.getName()).toString());
        byte abyte2[];
        protectionparameter = new X509Certificate[acertificate.length];
        for(int j = 0; j < acertificate.length; j++)
        {
            if(!"X.509".equals(acertificate[j].getType()))
                throw new KeyStoreException((new StringBuilder()).append("Certificates must be in X.509 format: invalid cert #").append(j).toString());
            if(!(acertificate[j] instanceof X509Certificate))
                throw new KeyStoreException((new StringBuilder()).append("Certificates must be in X.509 format: invalid cert #").append(j).toString());
            protectionparameter[j] = (X509Certificate)acertificate[j];
        }

        try
        {
            abyte2 = protectionparameter[0].getEncoded();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new KeyStoreException("Failed to encode certificate #0", s);
        }
        if(acertificate.length > 1)
        {
            byte abyte0[][] = new byte[protectionparameter.length - 1][];
            int i1 = 0;
            int k = 0;
            while(k < abyte0.length) 
            {
                int l1;
                try
                {
                    abyte0[k] = protectionparameter[k + 1].getEncoded();
                    l1 = abyte0[k].length;
                }
                // Misplaced declaration of an exception variable
                catch(String s)
                {
                    throw new KeyStoreException((new StringBuilder()).append("Failed to encode certificate #").append(k).toString(), s);
                }
                i1 += l1;
                k++;
            }
            protectionparameter = new byte[i1];
            k = 0;
            i1 = 0;
            do
            {
                acertificate = protectionparameter;
                if(i1 >= abyte0.length)
                    break;
                int i2 = abyte0[i1].length;
                System.arraycopy(abyte0[i1], 0, protectionparameter, k, i2);
                k += i2;
                abyte0[i1] = null;
                i1++;
            } while(true);
        } else
        {
            acertificate = null;
        }
        if(privatekey instanceof AndroidKeyStorePrivateKey)
            protectionparameter = ((AndroidKeyStoreKey)privatekey).getAlias();
        else
            protectionparameter = null;
        if(protectionparameter == null || !protectionparameter.startsWith("USRPKEY_")) goto _L6; else goto _L5
_L5:
        int l;
        privatekey = protectionparameter.substring("USRPKEY_".length());
        if(!s.equals(privatekey))
            throw new KeyStoreException((new StringBuilder()).append("Can only replace keys with same alias: ").append(s).append(" != ").append(privatekey).toString());
        l = 0;
        protectionparameter = null;
        privatekey = null;
_L12:
        if(l == 0)
            break; /* Loop/switch isn't completed */
        int j1;
        Credentials.deleteAllTypesForAlias(mKeyStore, s, mUid);
        KeyCharacteristics keycharacteristics = JVM INSTR new #299 <Class KeyCharacteristics>;
        keycharacteristics.KeyCharacteristics();
        KeyStore keystore = mKeyStore;
        obj = JVM INSTR new #30  <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        j1 = keystore.importKey(((StringBuilder) (obj)).append("USRPKEY_").append(s).toString(), protectionparameter, 1, privatekey, mUid, i, keycharacteristics);
        if(j1 == 1)
            break MISSING_BLOCK_LABEL_1120;
        privatekey = JVM INSTR new #59  <Class KeyStoreException>;
        privatekey.KeyStoreException("Failed to store private key", KeyStore.getKeyStoreException(j1));
        throw privatekey;
        privatekey;
        byte abyte1[];
        int k1;
        int j2;
        int k2;
        if(true)
            if(l != 0)
            {
                Credentials.deleteAllTypesForAlias(mKeyStore, s, mUid);
            } else
            {
                Credentials.deleteCertificateTypesForAlias(mKeyStore, s, mUid);
                Credentials.deleteSecretKeyTypeForAlias(mKeyStore, s, mUid);
            }
        throw privatekey;
_L6:
        k1 = 1;
        protectionparameter = privatekey.getFormat();
        if(protectionparameter == null || "PKCS#8".equals(protectionparameter) ^ true)
            throw new KeyStoreException((new StringBuilder()).append("Unsupported private key export format: ").append(protectionparameter).append(". Only private keys which export their key material in PKCS#8 format are").append(" supported.").toString());
        abyte1 = privatekey.getEncoded();
        if(abyte1 == null)
            throw new KeyStoreException("Private key did not export any key material");
        protectionparameter = new KeymasterArguments();
        try
        {
            protectionparameter.addEnum(0x10000002, KeyProperties.KeyAlgorithm.toKeymasterAsymmetricKeyAlgorithm(privatekey.getAlgorithm()));
            l = ((KeyProtection) (obj)).getPurposes();
            protectionparameter.addEnums(0x20000001, KeyProperties.Purpose.allToKeymaster(l));
            if(((KeyProtection) (obj)).isDigestsSpecified())
                protectionparameter.addEnums(0x20000005, KeyProperties.Digest.allToKeymaster(((KeyProtection) (obj)).getDigests()));
            protectionparameter.addEnums(0x20000004, KeyProperties.BlockMode.allToKeymaster(((KeyProtection) (obj)).getBlockModes()));
            privatekey = KeyProperties.EncryptionPadding.allToKeymaster(((KeyProtection) (obj)).getEncryptionPaddings());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new KeyStoreException(s);
        }
        if((l & 1) == 0) goto _L8; else goto _L7
_L7:
        if(!((KeyProtection) (obj)).isRandomizedEncryptionRequired()) goto _L8; else goto _L9
_L9:
        l = 0;
        j2 = privatekey.length;
_L10:
        if(l >= j2)
            break; /* Loop/switch isn't completed */
        k2 = privatekey[l];
        if(!KeymasterUtils.isKeymasterPaddingSchemeIndCpaCompatibleWithAsymmetricCrypto(k2))
        {
            privatekey = JVM INSTR new #59  <Class KeyStoreException>;
            s = JVM INSTR new #30  <Class StringBuilder>;
            s.StringBuilder();
            privatekey.KeyStoreException(s.append("Randomized encryption (IND-CPA) required but is violated by encryption padding mode: ").append(KeyProperties.EncryptionPadding.fromKeymaster(k2)).append(". See KeyProtection documentation.").toString());
            throw privatekey;
        }
        l++;
        if(true) goto _L10; else goto _L8
_L8:
        protectionparameter.addEnums(0x20000006, privatekey);
        protectionparameter.addEnums(0x20000006, KeyProperties.SignaturePadding.allToKeymaster(((KeyProtection) (obj)).getSignaturePaddings()));
        KeymasterUtils.addUserAuthArgs(protectionparameter, ((KeyProtection) (obj)).isUserAuthenticationRequired(), ((KeyProtection) (obj)).getUserAuthenticationValidityDurationSeconds(), ((KeyProtection) (obj)).isUserAuthenticationValidWhileOnBody(), ((KeyProtection) (obj)).isInvalidatedByBiometricEnrollment(), ((KeyProtection) (obj)).getBoundToSpecificSecureUserId());
        protectionparameter.addDateIfNotNull(0x60000190, ((KeyProtection) (obj)).getKeyValidityStart());
        protectionparameter.addDateIfNotNull(0x60000191, ((KeyProtection) (obj)).getKeyValidityForOriginationEnd());
        protectionparameter.addDateIfNotNull(0x60000192, ((KeyProtection) (obj)).getKeyValidityForConsumptionEnd());
        privatekey = abyte1;
        l = k1;
        if(true) goto _L12; else goto _L11
_L11:
        Credentials.deleteCertificateTypesForAlias(mKeyStore, s, mUid);
        Credentials.deleteSecretKeyTypeForAlias(mKeyStore, s, mUid);
        privatekey = mKeyStore;
        protectionparameter = JVM INSTR new #30  <Class StringBuilder>;
        protectionparameter.StringBuilder();
        k1 = privatekey.insert(protectionparameter.append("USRCERT_").append(s).toString(), abyte2, mUid, i);
        if(k1 == 1)
            break MISSING_BLOCK_LABEL_1188;
        privatekey = JVM INSTR new #59  <Class KeyStoreException>;
        privatekey.KeyStoreException("Failed to store certificate #0", KeyStore.getKeyStoreException(k1));
        throw privatekey;
        privatekey = mKeyStore;
        protectionparameter = JVM INSTR new #30  <Class StringBuilder>;
        protectionparameter.StringBuilder();
        i = privatekey.insert(protectionparameter.append("CACERT_").append(s).toString(), acertificate, mUid, i);
        if(i == 1)
            break MISSING_BLOCK_LABEL_1254;
        privatekey = JVM INSTR new #59  <Class KeyStoreException>;
        privatekey.KeyStoreException("Failed to store certificate chain", KeyStore.getKeyStoreException(i));
        throw privatekey;
        if(false)
            if(l != 0)
            {
                Credentials.deleteAllTypesForAlias(mKeyStore, s, mUid);
            } else
            {
                Credentials.deleteCertificateTypesForAlias(mKeyStore, s, mUid);
                Credentials.deleteSecretKeyTypeForAlias(mKeyStore, s, mUid);
            }
        return;
    }

    private void setSecretKeyEntry(String s, SecretKey secretkey, java.security.KeyStore.ProtectionParameter protectionparameter)
        throws KeyStoreException
    {
        KeyProtection keyprotection;
        byte abyte0[];
        KeymasterArguments keymasterarguments;
        if(protectionparameter != null && (protectionparameter instanceof KeyProtection) ^ true)
            throw new KeyStoreException((new StringBuilder()).append("Unsupported protection parameter class: ").append(protectionparameter.getClass().getName()).append(". Supported: ").append(android/security/keystore/KeyProtection.getName()).toString());
        keyprotection = (KeyProtection)protectionparameter;
        if(secretkey instanceof AndroidKeyStoreSecretKey)
        {
            secretkey = ((AndroidKeyStoreSecretKey)secretkey).getAlias();
            if(secretkey == null)
                throw new KeyStoreException("KeyStore-backed secret key does not have an alias");
            if(!secretkey.startsWith("USRSKEY_"))
                throw new KeyStoreException((new StringBuilder()).append("KeyStore-backed secret key has invalid alias: ").append(secretkey).toString());
            secretkey = secretkey.substring("USRSKEY_".length());
            if(!s.equals(secretkey))
                throw new KeyStoreException((new StringBuilder()).append("Can only replace KeyStore-backed keys with same alias: ").append(s).append(" != ").append(secretkey).toString());
            if(keyprotection != null)
                throw new KeyStoreException("Modifying KeyStore-backed key using protection parameters not supported");
            else
                return;
        }
        if(keyprotection == null)
            throw new KeyStoreException("Protection parameters must be specified when importing a symmetric key");
        protectionparameter = secretkey.getFormat();
        if(protectionparameter == null)
            throw new KeyStoreException("Only secret keys that export their key material are supported");
        if(!"RAW".equals(protectionparameter))
            throw new KeyStoreException((new StringBuilder()).append("Unsupported secret key material export format: ").append(protectionparameter).toString());
        abyte0 = secretkey.getEncoded();
        if(abyte0 == null)
            throw new KeyStoreException("Key did not export its key material despite supporting RAW format export");
        keymasterarguments = new KeymasterArguments();
        int i;
        int j;
        try
        {
            i = KeyProperties.KeyAlgorithm.toKeymasterSecretKeyAlgorithm(secretkey.getAlgorithm());
            keymasterarguments.addEnum(0x10000002, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new KeyStoreException(s);
        }
        if(i != 128) goto _L2; else goto _L1
_L1:
        j = KeyProperties.KeyAlgorithm.toKeymasterDigest(secretkey.getAlgorithm());
        if(j != -1)
            break MISSING_BLOCK_LABEL_408;
        protectionparameter = JVM INSTR new #495 <Class ProviderException>;
        s = JVM INSTR new #30  <Class StringBuilder>;
        s.StringBuilder();
        protectionparameter.ProviderException(s.append("HMAC key algorithm digest unknown for key algorithm ").append(secretkey.getAlgorithm()).toString());
        throw protectionparameter;
        int ai[] = new int[1];
        ai[0] = j;
        protectionparameter = ai;
        if(!keyprotection.isDigestsSpecified()) goto _L4; else goto _L3
_L3:
        int ai1[] = KeyProperties.Digest.allToKeymaster(keyprotection.getDigests());
        if(ai1.length != 1) goto _L6; else goto _L5
_L5:
        protectionparameter = ai;
        if(ai1[0] == j) goto _L4; else goto _L6
_L6:
        s = JVM INSTR new #59  <Class KeyStoreException>;
        protectionparameter = JVM INSTR new #30  <Class StringBuilder>;
        protectionparameter.StringBuilder();
        s.KeyStoreException(protectionparameter.append("Unsupported digests specification: ").append(Arrays.asList(keyprotection.getDigests())).append(". Only ").append(KeyProperties.Digest.fromKeymaster(j)).append(" supported for HMAC key algorithm ").append(secretkey.getAlgorithm()).toString());
        throw s;
_L2:
        if(!keyprotection.isDigestsSpecified()) goto _L8; else goto _L7
_L7:
        protectionparameter = KeyProperties.Digest.allToKeymaster(keyprotection.getDigests());
_L4:
        int l;
        keymasterarguments.addEnums(0x20000005, protectionparameter);
        l = keyprotection.getPurposes();
        secretkey = KeyProperties.BlockMode.allToKeymaster(keyprotection.getBlockModes());
        if((l & 1) == 0) goto _L10; else goto _L9
_L9:
        if(!keyprotection.isRandomizedEncryptionRequired()) goto _L10; else goto _L11
_L11:
        j = 0;
        int i1 = secretkey.length;
_L14:
        if(j >= i1) goto _L10; else goto _L12
_L12:
        int j1 = secretkey[j];
        if(!KeymasterUtils.isKeymasterBlockModeIndCpaCompatibleWithSymmetricCrypto(j1))
        {
            s = JVM INSTR new #59  <Class KeyStoreException>;
            secretkey = JVM INSTR new #30  <Class StringBuilder>;
            secretkey.StringBuilder();
            s.KeyStoreException(secretkey.append("Randomized encryption (IND-CPA) required but may be violated by block mode: ").append(KeyProperties.BlockMode.fromKeymaster(j1)).append(". See KeyProtection documentation.").toString());
            throw s;
        }
          goto _L13
_L8:
        protectionparameter = EmptyArray.INT;
          goto _L4
_L13:
        j++;
          goto _L14
_L10:
        keymasterarguments.addEnums(0x20000001, KeyProperties.Purpose.allToKeymaster(l));
        keymasterarguments.addEnums(0x20000004, secretkey);
        if(keyprotection.getSignaturePaddings().length > 0)
        {
            s = JVM INSTR new #59  <Class KeyStoreException>;
            s.KeyStoreException("Signature paddings not supported for symmetric keys");
            throw s;
        }
        keymasterarguments.addEnums(0x20000006, KeyProperties.EncryptionPadding.allToKeymaster(keyprotection.getEncryptionPaddings()));
        KeymasterUtils.addUserAuthArgs(keymasterarguments, keyprotection.isUserAuthenticationRequired(), keyprotection.getUserAuthenticationValidityDurationSeconds(), keyprotection.isUserAuthenticationValidWhileOnBody(), keyprotection.isInvalidatedByBiometricEnrollment(), keyprotection.getBoundToSpecificSecureUserId());
        KeymasterUtils.addMinMacLengthAuthorizationIfNecessary(keymasterarguments, i, secretkey, protectionparameter);
        keymasterarguments.addDateIfNotNull(0x60000190, keyprotection.getKeyValidityStart());
        keymasterarguments.addDateIfNotNull(0x60000191, keyprotection.getKeyValidityForOriginationEnd());
        keymasterarguments.addDateIfNotNull(0x60000192, keyprotection.getKeyValidityForConsumptionEnd());
        if((l & 1) == 0)
            break MISSING_BLOCK_LABEL_830;
        if(keyprotection.isRandomizedEncryptionRequired() ^ true)
            keymasterarguments.addBoolean(0x70000007);
        int k = 0;
        if(keyprotection.isCriticalToDeviceEncryption())
            k = 8;
        Credentials.deleteAllTypesForAlias(mKeyStore, s, mUid);
        s = (new StringBuilder()).append("USRSKEY_").append(s).toString();
        k = mKeyStore.importKey(s, keymasterarguments, 3, abyte0, mUid, k, new KeyCharacteristics());
        if(k != 1)
            throw new KeyStoreException((new StringBuilder()).append("Failed to import secret key. Keystore error code: ").append(k).toString());
        return;
          goto _L4
    }

    private static X509Certificate toCertificate(byte abyte0[])
    {
        try
        {
            CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
            ByteArrayInputStream bytearrayinputstream = JVM INSTR new #548 <Class ByteArrayInputStream>;
            bytearrayinputstream.ByteArrayInputStream(abyte0);
            abyte0 = (X509Certificate)certificatefactory.generateCertificate(bytearrayinputstream);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Log.w("AndroidKeyStore", "Couldn't parse certificate in keystore", abyte0);
            return null;
        }
        return abyte0;
    }

    private static Collection toCertificates(byte abyte0[])
    {
        try
        {
            CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
            ByteArrayInputStream bytearrayinputstream = JVM INSTR new #548 <Class ByteArrayInputStream>;
            bytearrayinputstream.ByteArrayInputStream(abyte0);
            abyte0 = certificatefactory.generateCertificates(bytearrayinputstream);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Log.w("AndroidKeyStore", "Couldn't parse certificates in keystore", abyte0);
            return new ArrayList();
        }
        return abyte0;
    }

    private static KeyStoreX509Certificate wrapIntoKeyStoreCertificate(String s, int i, X509Certificate x509certificate)
    {
        KeyStoreX509Certificate keystorex509certificate = null;
        if(x509certificate != null)
            keystorex509certificate = new KeyStoreX509Certificate(s, i, x509certificate);
        return keystorex509certificate;
    }

    public Enumeration engineAliases()
    {
        return Collections.enumeration(getUniqueAliases());
    }

    public boolean engineContainsAlias(String s)
    {
        if(s == null)
            throw new NullPointerException("alias == null");
        boolean flag;
        if(!mKeyStore.contains((new StringBuilder()).append("USRPKEY_").append(s).toString(), mUid) && !mKeyStore.contains((new StringBuilder()).append("USRSKEY_").append(s).toString(), mUid) && !mKeyStore.contains((new StringBuilder()).append("USRCERT_").append(s).toString(), mUid))
            flag = mKeyStore.contains((new StringBuilder()).append("CACERT_").append(s).toString(), mUid);
        else
            flag = true;
        return flag;
    }

    public void engineDeleteEntry(String s)
        throws KeyStoreException
    {
        if(!Credentials.deleteAllTypesForAlias(mKeyStore, s, mUid))
            throw new KeyStoreException((new StringBuilder()).append("Failed to delete entry: ").append(s).toString());
        else
            return;
    }

    public Certificate engineGetCertificate(String s)
    {
        if(s == null)
            throw new NullPointerException("alias == null");
        byte abyte0[] = mKeyStore.get((new StringBuilder()).append("USRCERT_").append(s).toString(), mUid);
        if(abyte0 != null)
            return getCertificateForPrivateKeyEntry(s, abyte0);
        s = mKeyStore.get((new StringBuilder()).append("CACERT_").append(s).toString(), mUid);
        if(s != null)
            return getCertificateForTrustedCertificateEntry(s);
        else
            return null;
    }

    public String engineGetCertificateAlias(Certificate certificate)
    {
        boolean flag;
        byte abyte0[];
        Object aobj[];
        int i;
        int j;
        flag = false;
        if(certificate == null)
            return null;
        if(!"X.509".equalsIgnoreCase(certificate.getType()))
            return null;
        try
        {
            abyte0 = certificate.getEncoded();
        }
        // Misplaced declaration of an exception variable
        catch(Certificate certificate)
        {
            return null;
        }
        if(abyte0 == null)
            return null;
        certificate = new HashSet();
        aobj = mKeyStore.list("USRCERT_", mUid);
        if(aobj == null)
            break MISSING_BLOCK_LABEL_153;
        i = aobj.length;
        j = 0;
_L2:
        String s;
        byte abyte1[];
        if(j >= i)
            break MISSING_BLOCK_LABEL_153;
        s = aobj[j];
        abyte1 = mKeyStore.get((new StringBuilder()).append("USRCERT_").append(s).toString(), mUid);
        if(abyte1 != null)
            break; /* Loop/switch isn't completed */
_L4:
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        certificate.add(s);
        if(!Arrays.equals(abyte1, abyte0)) goto _L4; else goto _L3
_L3:
        return s;
        String as[];
        as = mKeyStore.list("CACERT_", mUid);
        if(aobj == null)
            break MISSING_BLOCK_LABEL_262;
        i = as.length;
        j = ((flag) ? 1 : 0);
_L6:
        String s1;
        if(j >= i)
            break MISSING_BLOCK_LABEL_262;
        s1 = as[j];
        if(!certificate.contains(s1))
            break; /* Loop/switch isn't completed */
_L8:
        j++;
        if(true) goto _L6; else goto _L5
_L5:
        if((aobj = mKeyStore.get((new StringBuilder()).append("CACERT_").append(s1).toString(), mUid)) == null || !Arrays.equals(((byte []) (aobj)), abyte0)) goto _L8; else goto _L7
_L7:
        return s1;
        return null;
    }

    public Certificate[] engineGetCertificateChain(String s)
    {
        if(s == null)
            throw new NullPointerException("alias == null");
        X509Certificate x509certificate = (X509Certificate)engineGetCertificate(s);
        if(x509certificate == null)
            return null;
        s = mKeyStore.get((new StringBuilder()).append("CACERT_").append(s).toString(), mUid);
        if(s != null)
        {
            s = toCertificates(s);
            Certificate acertificate[] = new Certificate[s.size() + 1];
            Iterator iterator = s.iterator();
            int i = 1;
            do
            {
                s = acertificate;
                if(!iterator.hasNext())
                    break;
                acertificate[i] = (Certificate)iterator.next();
                i++;
            } while(true);
        } else
        {
            s = new Certificate[1];
        }
        s[0] = x509certificate;
        return s;
    }

    public Date engineGetCreationDate(String s)
    {
        if(s == null)
            throw new NullPointerException("alias == null");
        Date date = getModificationDate((new StringBuilder()).append("USRPKEY_").append(s).toString());
        if(date != null)
            return date;
        date = getModificationDate((new StringBuilder()).append("USRSKEY_").append(s).toString());
        if(date != null)
            return date;
        date = getModificationDate((new StringBuilder()).append("USRCERT_").append(s).toString());
        if(date != null)
            return date;
        else
            return getModificationDate((new StringBuilder()).append("CACERT_").append(s).toString());
    }

    public Key engineGetKey(String s, char ac[])
        throws NoSuchAlgorithmException, UnrecoverableKeyException
    {
        if(isPrivateKeyEntry(s))
        {
            s = (new StringBuilder()).append("USRPKEY_").append(s).toString();
            return AndroidKeyStoreProvider.loadAndroidKeyStorePrivateKeyFromKeystore(mKeyStore, s, mUid);
        }
        if(isSecretKeyEntry(s))
        {
            s = (new StringBuilder()).append("USRSKEY_").append(s).toString();
            return AndroidKeyStoreProvider.loadAndroidKeyStoreSecretKeyFromKeystore(mKeyStore, s, mUid);
        } else
        {
            return null;
        }
    }

    public boolean engineIsCertificateEntry(String s)
    {
        boolean flag;
        if(!isKeyEntry(s))
            flag = isCertificateEntry(s);
        else
            flag = false;
        return flag;
    }

    public boolean engineIsKeyEntry(String s)
    {
        return isKeyEntry(s);
    }

    public void engineLoad(InputStream inputstream, char ac[])
        throws IOException, NoSuchAlgorithmException, CertificateException
    {
        if(inputstream != null)
            throw new IllegalArgumentException("InputStream not supported");
        if(ac != null)
        {
            throw new IllegalArgumentException("password not supported");
        } else
        {
            mKeyStore = KeyStore.getInstance();
            mUid = -1;
            return;
        }
    }

    public void engineLoad(java.security.KeyStore.LoadStoreParameter loadstoreparameter)
        throws IOException, NoSuchAlgorithmException, CertificateException
    {
label0:
        {
            int i = -1;
            if(loadstoreparameter != null)
            {
                if(!(loadstoreparameter instanceof AndroidKeyStoreLoadStoreParameter))
                    break label0;
                i = ((AndroidKeyStoreLoadStoreParameter)loadstoreparameter).getUid();
            }
            mKeyStore = KeyStore.getInstance();
            mUid = i;
            return;
        }
        throw new IllegalArgumentException((new StringBuilder()).append("Unsupported param type: ").append(loadstoreparameter.getClass()).toString());
    }

    public void engineSetCertificateEntry(String s, Certificate certificate)
        throws KeyStoreException
    {
        if(isKeyEntry(s))
            throw new KeyStoreException("Entry exists and is not a trusted certificate");
        if(certificate == null)
            throw new NullPointerException("cert == null");
        try
        {
            certificate = certificate.getEncoded();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new KeyStoreException(s);
        }
        if(!mKeyStore.put((new StringBuilder()).append("CACERT_").append(s).toString(), certificate, mUid, 0))
            throw new KeyStoreException("Couldn't insert certificate; is KeyStore initialized?");
        else
            return;
    }

    public void engineSetEntry(String s, java.security.KeyStore.Entry entry, java.security.KeyStore.ProtectionParameter protectionparameter)
        throws KeyStoreException
    {
        if(entry == null)
            throw new KeyStoreException("entry == null");
        Credentials.deleteAllTypesForAlias(mKeyStore, s, mUid);
        if(entry instanceof java.security.KeyStore.TrustedCertificateEntry)
        {
            engineSetCertificateEntry(s, ((java.security.KeyStore.TrustedCertificateEntry)entry).getTrustedCertificate());
            return;
        }
        if(entry instanceof java.security.KeyStore.PrivateKeyEntry)
        {
            entry = (java.security.KeyStore.PrivateKeyEntry)entry;
            setPrivateKeyEntry(s, entry.getPrivateKey(), entry.getCertificateChain(), protectionparameter);
        } else
        if(entry instanceof java.security.KeyStore.SecretKeyEntry)
            setSecretKeyEntry(s, ((java.security.KeyStore.SecretKeyEntry)entry).getSecretKey(), protectionparameter);
        else
            throw new KeyStoreException((new StringBuilder()).append("Entry must be a PrivateKeyEntry, SecretKeyEntry or TrustedCertificateEntry; was ").append(entry).toString());
    }

    public void engineSetKeyEntry(String s, Key key, char ac[], Certificate acertificate[])
        throws KeyStoreException
    {
        if(ac != null && ac.length > 0)
            throw new KeyStoreException("entries cannot be protected with passwords");
        if(key instanceof PrivateKey)
            setPrivateKeyEntry(s, (PrivateKey)key, acertificate, null);
        else
        if(key instanceof SecretKey)
            setSecretKeyEntry(s, (SecretKey)key, null);
        else
            throw new KeyStoreException("Only PrivateKey and SecretKey are supported");
    }

    public void engineSetKeyEntry(String s, byte abyte0[], Certificate acertificate[])
        throws KeyStoreException
    {
        throw new KeyStoreException("Operation not supported because key encoding is unknown");
    }

    public int engineSize()
    {
        return getUniqueAliases().size();
    }

    public void engineStore(OutputStream outputstream, char ac[])
        throws IOException, NoSuchAlgorithmException, CertificateException
    {
        throw new UnsupportedOperationException("Can not serialize AndroidKeyStore to OutputStream");
    }

    public static final String NAME = "AndroidKeyStore";
    private KeyStore mKeyStore;
    private int mUid;
}

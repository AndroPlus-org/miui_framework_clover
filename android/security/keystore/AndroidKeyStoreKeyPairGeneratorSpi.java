// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.security.*;
import android.security.keymaster.*;
import com.android.internal.util.ArrayUtils;
import com.android.org.bouncycastle.asn1.*;
import com.android.org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import com.android.org.bouncycastle.asn1.x509.*;
import com.android.org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import com.android.org.bouncycastle.jce.X509Principal;
import com.android.org.bouncycastle.jce.provider.X509CertificateObject;
import com.android.org.bouncycastle.x509.X509V3CertificateGenerator;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.*;
import java.security.spec.*;
import java.util.*;
import javax.security.auth.x500.X500Principal;
import libcore.util.EmptyArray;

// Referenced classes of package android.security.keystore:
//            KeyGenParameterSpec, KeymasterUtils, AndroidKeyStoreBCWorkaroundProvider, AndroidKeyStoreProvider, 
//            KeyStoreCryptoOperationUtils

public abstract class AndroidKeyStoreKeyPairGeneratorSpi extends KeyPairGeneratorSpi
{
    public static class EC extends AndroidKeyStoreKeyPairGeneratorSpi
    {

        public EC()
        {
            super(3);
        }
    }

    public static class RSA extends AndroidKeyStoreKeyPairGeneratorSpi
    {

        public RSA()
        {
            super(1);
        }
    }


    protected AndroidKeyStoreKeyPairGeneratorSpi(int i)
    {
        mKeymasterAlgorithm = -1;
        mOriginalKeymasterAlgorithm = i;
    }

    private void addAlgorithmSpecificParameters(KeymasterArguments keymasterarguments)
    {
        switch(mKeymasterAlgorithm)
        {
        case 2: // '\002'
        default:
            throw new ProviderException((new StringBuilder()).append("Unsupported algorithm: ").append(mKeymasterAlgorithm).toString());

        case 1: // '\001'
            keymasterarguments.addUnsignedLong(0x500000c8, mRSAPublicExponent);
            // fall through

        case 3: // '\003'
            return;
        }
    }

    private static void checkValidKeySize(int i, int j)
        throws InvalidAlgorithmParameterException
    {
        switch(i)
        {
        case 2: // '\002'
        default:
            throw new ProviderException((new StringBuilder()).append("Unsupported algorithm: ").append(i).toString());

        case 3: // '\003'
            if(!SUPPORTED_EC_NIST_CURVE_SIZES.contains(Integer.valueOf(j)))
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unsupported EC key size: ").append(j).append(" bits. Supported: ").append(SUPPORTED_EC_NIST_CURVE_SIZES).toString());
            break;

        case 1: // '\001'
            if(j < 512 || j > 8192)
                throw new InvalidAlgorithmParameterException("RSA key size must be >= 512 and <= 8192");
            break;
        }
    }

    private KeymasterArguments constructKeyGenerationArguments()
    {
        KeymasterArguments keymasterarguments = new KeymasterArguments();
        keymasterarguments.addUnsignedInt(0x30000003, mKeySizeBits);
        keymasterarguments.addEnum(0x10000002, mKeymasterAlgorithm);
        keymasterarguments.addEnums(0x20000001, mKeymasterPurposes);
        keymasterarguments.addEnums(0x20000004, mKeymasterBlockModes);
        keymasterarguments.addEnums(0x20000006, mKeymasterEncryptionPaddings);
        keymasterarguments.addEnums(0x20000006, mKeymasterSignaturePaddings);
        keymasterarguments.addEnums(0x20000005, mKeymasterDigests);
        KeymasterUtils.addUserAuthArgs(keymasterarguments, mSpec.isUserAuthenticationRequired(), mSpec.getUserAuthenticationValidityDurationSeconds(), mSpec.isUserAuthenticationValidWhileOnBody(), mSpec.isInvalidatedByBiometricEnrollment(), 0L);
        keymasterarguments.addDateIfNotNull(0x60000190, mSpec.getKeyValidityStart());
        keymasterarguments.addDateIfNotNull(0x60000191, mSpec.getKeyValidityForOriginationEnd());
        keymasterarguments.addDateIfNotNull(0x60000192, mSpec.getKeyValidityForConsumptionEnd());
        addAlgorithmSpecificParameters(keymasterarguments);
        if(mSpec.isUniqueIdIncluded())
            keymasterarguments.addBoolean(0x700000ca);
        return keymasterarguments;
    }

    private Iterable createCertificateChain(String s, KeyPair keypair)
        throws ProviderException
    {
        byte abyte0[] = mSpec.getAttestationChallenge();
        if(abyte0 != null)
        {
            KeymasterArguments keymasterarguments = new KeymasterArguments();
            keymasterarguments.addBytes(0x900002c4, abyte0);
            return getAttestationChain(s, keypair, keymasterarguments);
        } else
        {
            return Collections.singleton(generateSelfSignedCertificateBytes(keypair));
        }
    }

    private void generateKeystoreKeyPair(String s, KeymasterArguments keymasterarguments, byte abyte0[], int i)
        throws ProviderException
    {
        KeyCharacteristics keycharacteristics = new KeyCharacteristics();
        i = mKeyStore.generateKey(s, keymasterarguments, abyte0, mEntryUid, i, keycharacteristics);
        if(i != 1)
            throw new ProviderException("Failed to generate key pair", KeyStore.getKeyStoreException(i));
        else
            return;
    }

    private X509Certificate generateSelfSignedCertificate(PrivateKey privatekey, PublicKey publickey)
        throws CertificateParsingException, IOException
    {
        String s = getCertificateSignatureAlgorithm(mKeymasterAlgorithm, mKeySizeBits, mSpec);
        if(s == null)
            return generateSelfSignedCertificateWithFakeSignature(publickey);
        try
        {
            privatekey = generateSelfSignedCertificateWithValidSignature(privatekey, publickey, s);
        }
        // Misplaced declaration of an exception variable
        catch(PrivateKey privatekey)
        {
            return generateSelfSignedCertificateWithFakeSignature(publickey);
        }
        return privatekey;
    }

    private byte[] generateSelfSignedCertificateBytes(KeyPair keypair)
        throws ProviderException
    {
        try
        {
            keypair = generateSelfSignedCertificate(keypair.getPrivate(), keypair.getPublic()).getEncoded();
        }
        // Misplaced declaration of an exception variable
        catch(KeyPair keypair)
        {
            throw new ProviderException("Failed to generate self-signed certificate", keypair);
        }
        // Misplaced declaration of an exception variable
        catch(KeyPair keypair)
        {
            throw new ProviderException("Failed to obtain encoded form of self-signed certificate", keypair);
        }
        return keypair;
    }

    private X509Certificate generateSelfSignedCertificateWithFakeSignature(PublicKey publickey)
        throws IOException, CertificateParsingException
    {
        V3TBSCertificateGenerator v3tbscertificategenerator = new V3TBSCertificateGenerator();
        mKeymasterAlgorithm;
        JVM INSTR tableswitch 1 3: default 40
    //                   1 191
    //                   2 40
    //                   3 71;
           goto _L1 _L2 _L1 _L3
_L1:
        throw new ProviderException((new StringBuilder()).append("Unsupported key algorithm: ").append(mKeymasterAlgorithm).toString());
_L3:
        Object obj;
        Object obj1;
        obj = new AlgorithmIdentifier(X9ObjectIdentifiers.ecdsa_with_SHA256);
        obj1 = new ASN1EncodableVector();
        ((ASN1EncodableVector) (obj1)).add(new DERInteger(0L));
        ((ASN1EncodableVector) (obj1)).add(new DERInteger(0L));
        obj1 = (new DERSequence()).getEncoded();
_L11:
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        obj5 = null;
        Object obj6;
        obj6 = JVM INSTR new #389 <Class ASN1InputStream>;
        ((ASN1InputStream) (obj6)).ASN1InputStream(publickey.getEncoded());
        v3tbscertificategenerator.setSubjectPublicKeyInfo(SubjectPublicKeyInfo.getInstance(((ASN1InputStream) (obj6)).readObject()));
        publickey = obj3;
        if(obj6 == null)
            break MISSING_BLOCK_LABEL_185;
        ((ASN1InputStream) (obj6)).close();
        publickey = obj3;
_L4:
        if(publickey != null)
        {
            throw publickey;
        } else
        {
            v3tbscertificategenerator.setSerialNumber(new ASN1Integer(mSpec.getCertificateSerialNumber()));
            publickey = new X509Principal(mSpec.getCertificateSubject().getEncoded());
            v3tbscertificategenerator.setSubject(publickey);
            v3tbscertificategenerator.setIssuer(publickey);
            v3tbscertificategenerator.setStartDate(new Time(mSpec.getCertificateNotBefore()));
            v3tbscertificategenerator.setEndDate(new Time(mSpec.getCertificateNotAfter()));
            v3tbscertificategenerator.setSignature(((AlgorithmIdentifier) (obj)));
            obj6 = v3tbscertificategenerator.generateTBSCertificate();
            publickey = new ASN1EncodableVector();
            publickey.add(((com.android.org.bouncycastle.asn1.ASN1Encodable) (obj6)));
            publickey.add(((com.android.org.bouncycastle.asn1.ASN1Encodable) (obj)));
            publickey.add(new DERBitString(((byte []) (obj1))));
            return new X509CertificateObject(Certificate.getInstance(new DERSequence(publickey)));
        }
_L2:
        obj = new AlgorithmIdentifier(PKCSObjectIdentifiers.sha256WithRSAEncryption, DERNull.INSTANCE);
        obj1 = new byte[1];
        continue; /* Loop/switch isn't completed */
        publickey;
          goto _L4
        publickey;
        obj = obj5;
_L9:
        throw publickey;
        obj6;
        obj1 = publickey;
        publickey = ((PublicKey) (obj6));
_L7:
        obj6 = obj1;
        if(obj == null)
            break MISSING_BLOCK_LABEL_247;
        ((ASN1InputStream) (obj)).close();
        obj6 = obj1;
_L5:
        if(obj6 != null)
            throw obj6;
        else
            throw publickey;
        obj;
        if(obj1 == null)
        {
            obj6 = obj;
        } else
        {
            obj6 = obj1;
            if(obj1 != obj)
            {
                ((Throwable) (obj1)).addSuppressed(((Throwable) (obj)));
                obj6 = obj1;
            }
        }
          goto _L5
        publickey;
        obj = obj4;
        obj1 = obj2;
        continue; /* Loop/switch isn't completed */
        publickey;
        obj = obj6;
        obj1 = obj2;
        if(true) goto _L7; else goto _L6
_L6:
        publickey;
        obj = obj6;
        if(true) goto _L9; else goto _L8
_L8:
        if(true) goto _L11; else goto _L10
_L10:
    }

    private X509Certificate generateSelfSignedCertificateWithValidSignature(PrivateKey privatekey, PublicKey publickey, String s)
        throws Exception
    {
        X509V3CertificateGenerator x509v3certificategenerator = new X509V3CertificateGenerator();
        x509v3certificategenerator.setPublicKey(publickey);
        x509v3certificategenerator.setSerialNumber(mSpec.getCertificateSerialNumber());
        x509v3certificategenerator.setSubjectDN(mSpec.getCertificateSubject());
        x509v3certificategenerator.setIssuerDN(mSpec.getCertificateSubject());
        x509v3certificategenerator.setNotBefore(mSpec.getCertificateNotBefore());
        x509v3certificategenerator.setNotAfter(mSpec.getCertificateNotAfter());
        x509v3certificategenerator.setSignatureAlgorithm(s);
        return x509v3certificategenerator.generate(privatekey);
    }

    private Iterable getAttestationChain(String s, KeyPair keypair, KeymasterArguments keymasterarguments)
        throws ProviderException
    {
        keypair = new KeymasterCertificateChain();
        int i = mKeyStore.attestKey(s, keymasterarguments, keypair);
        if(i != 1)
            throw new ProviderException("Failed to generate attestation certificate chain", KeyStore.getKeyStoreException(i));
        s = keypair.getCertificates();
        if(s.size() < 2)
            throw new ProviderException((new StringBuilder()).append("Attestation certificate chain contained ").append(s.size()).append(" entries. At least two are required.").toString());
        else
            return s;
    }

    private static Set getAvailableKeymasterSignatureDigests(String as[], String as1[])
    {
        boolean flag = false;
        HashSet hashset = new HashSet();
        as = KeyProperties.Digest.allToKeymaster(as);
        int i = as.length;
        for(int j = 0; j < i; j++)
            hashset.add(Integer.valueOf(as[j]));

        as = new HashSet();
        as1 = KeyProperties.Digest.allToKeymaster(as1);
        i = as1.length;
        for(int k = ((flag) ? 1 : 0); k < i; k++)
            as.add(Integer.valueOf(as1[k]));

        as = new HashSet(as);
        as.retainAll(hashset);
        return as;
    }

    private static String getCertificateSignatureAlgorithm(int i, int j, KeyGenParameterSpec keygenparameterspec)
    {
        if((keygenparameterspec.getPurposes() & 4) == 0)
            return null;
        if(keygenparameterspec.isUserAuthenticationRequired())
            return null;
        if(!keygenparameterspec.isDigestsSpecified())
            return null;
        i;
        JVM INSTR tableswitch 1 3: default 56
    //                   1 232
    //                   2 56
    //                   3 83;
           goto _L1 _L2 _L1 _L3
_L1:
        throw new ProviderException((new StringBuilder()).append("Unsupported algorithm: ").append(i).toString());
_L3:
        int k;
        keygenparameterspec = getAvailableKeymasterSignatureDigests(keygenparameterspec.getDigests(), AndroidKeyStoreBCWorkaroundProvider.getSupportedEcdsaSignatureDigests());
        i = -1;
        k = -1;
        keygenparameterspec = keygenparameterspec.iterator();
_L7:
        int i1 = i;
        if(!keygenparameterspec.hasNext()) goto _L5; else goto _L4
_L4:
        int k1;
        i1 = ((Integer)keygenparameterspec.next()).intValue();
        k1 = KeymasterUtils.getDigestOutputSizeBits(i1);
        if(k1 != j) goto _L6; else goto _L5
_L5:
        if(i1 == -1)
            return null;
        else
            return (new StringBuilder()).append(KeyProperties.Digest.fromKeymasterToSignatureAlgorithmDigest(i1)).append("WithECDSA").toString();
_L6:
        if(i == -1)
        {
            i = i1;
            k = k1;
        } else
        if(k < j)
        {
            if(k1 > k)
            {
                i = i1;
                k = k1;
            }
        } else
        if(k1 < k && k1 >= j)
        {
            i = i1;
            k = k1;
        }
          goto _L7
_L2:
        if(!ArrayUtils.contains(KeyProperties.SignaturePadding.allToKeymaster(keygenparameterspec.getSignaturePaddings()), 5))
            return null;
        keygenparameterspec = getAvailableKeymasterSignatureDigests(keygenparameterspec.getDigests(), AndroidKeyStoreBCWorkaroundProvider.getSupportedEcdsaSignatureDigests());
        int l = -1;
        i = -1;
        keygenparameterspec = keygenparameterspec.iterator();
        do
        {
            if(!keygenparameterspec.hasNext())
                break;
            int l1 = ((Integer)keygenparameterspec.next()).intValue();
            int j1 = KeymasterUtils.getDigestOutputSizeBits(l1);
            if(j1 <= j - 240)
                if(l == -1)
                {
                    l = l1;
                    i = j1;
                } else
                if(j1 > i)
                {
                    l = l1;
                    i = j1;
                }
        } while(true);
        if(l == -1)
            return null;
        else
            return (new StringBuilder()).append(KeyProperties.Digest.fromKeymasterToSignatureAlgorithmDigest(l)).append("WithRSA").toString();
    }

    private static int getDefaultKeySize(int i)
    {
        switch(i)
        {
        case 2: // '\002'
        default:
            throw new ProviderException((new StringBuilder()).append("Unsupported algorithm: ").append(i).toString());

        case 3: // '\003'
            return 256;

        case 1: // '\001'
            return 2048;
        }
    }

    private void initAlgorithmSpecificParameters()
        throws InvalidAlgorithmParameterException
    {
        Object obj = mSpec.getAlgorithmParameterSpec();
        mKeymasterAlgorithm;
        JVM INSTR tableswitch 1 3: default 40
    //                   1 70
    //                   2 40
    //                   3 303;
           goto _L1 _L2 _L1 _L3
_L1:
        throw new ProviderException((new StringBuilder()).append("Unsupported algorithm: ").append(mKeymasterAlgorithm).toString());
_L2:
        Object obj1 = null;
        if(!(obj instanceof RSAKeyGenParameterSpec)) goto _L5; else goto _L4
_L4:
        obj1 = (RSAKeyGenParameterSpec)obj;
        if(mKeySizeBits != -1) goto _L7; else goto _L6
_L6:
        mKeySizeBits = ((RSAKeyGenParameterSpec) (obj1)).getKeysize();
_L10:
        obj1 = ((RSAKeyGenParameterSpec) (obj1)).getPublicExponent();
          goto _L8
_L7:
        if(mKeySizeBits == ((RSAKeyGenParameterSpec) (obj1)).getKeysize()) goto _L10; else goto _L9
_L9:
        throw new InvalidAlgorithmParameterException((new StringBuilder()).append("RSA key size must match  between ").append(mSpec).append(" and ").append(obj).append(": ").append(mKeySizeBits).append(" vs ").append(((RSAKeyGenParameterSpec) (obj1)).getKeysize()).toString());
_L5:
        if(obj != null)
            throw new InvalidAlgorithmParameterException("RSA may only use RSAKeyGenParameterSpec");
_L8:
        obj = obj1;
        if(obj1 == null)
            obj = RSAKeyGenParameterSpec.F4;
        if(((BigInteger) (obj)).compareTo(BigInteger.ZERO) < 1)
            throw new InvalidAlgorithmParameterException((new StringBuilder()).append("RSA public exponent must be positive: ").append(obj).toString());
        if(((BigInteger) (obj)).compareTo(KeymasterArguments.UINT64_MAX_VALUE) > 0)
            throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unsupported RSA public exponent: ").append(obj).append(". Maximum supported value: ").append(KeymasterArguments.UINT64_MAX_VALUE).toString());
        mRSAPublicExponent = ((BigInteger) (obj));
_L12:
        return;
_L3:
        Integer integer;
        if(!(obj instanceof ECGenParameterSpec))
            continue; /* Loop/switch isn't completed */
        String s = ((ECGenParameterSpec)obj).getName();
        integer = (Integer)SUPPORTED_EC_NIST_CURVE_NAME_TO_SIZE.get(s.toLowerCase(Locale.US));
        if(integer == null)
            throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unsupported EC curve name: ").append(s).append(". Supported: ").append(SUPPORTED_EC_NIST_CURVE_NAMES).toString());
        if(mKeySizeBits != -1)
            continue; /* Loop/switch isn't completed */
        mKeySizeBits = integer.intValue();
        if(true) goto _L12; else goto _L11
_L11:
        if(mKeySizeBits == integer.intValue()) goto _L12; else goto _L13
_L13:
        throw new InvalidAlgorithmParameterException((new StringBuilder()).append("EC key size must match  between ").append(mSpec).append(" and ").append(obj).append(": ").append(mKeySizeBits).append(" vs ").append(integer).toString());
        if(obj == null) goto _L12; else goto _L14
_L14:
        throw new InvalidAlgorithmParameterException("EC may only use ECGenParameterSpec");
    }

    private KeyPair loadKeystoreKeyPair(String s)
        throws ProviderException
    {
        KeyPair keypair;
        try
        {
            keypair = AndroidKeyStoreProvider.loadAndroidKeyStoreKeyPairFromKeystore(mKeyStore, s, mEntryUid);
            if(!mJcaKeyAlgorithm.equalsIgnoreCase(keypair.getPrivate().getAlgorithm()))
            {
                ProviderException providerexception = JVM INSTR new #132 <Class ProviderException>;
                s = JVM INSTR new #134 <Class StringBuilder>;
                s.StringBuilder();
                providerexception.ProviderException(s.append("Generated key pair algorithm does not match requested algorithm: ").append(keypair.getPrivate().getAlgorithm()).append(" vs ").append(mJcaKeyAlgorithm).toString());
                throw providerexception;
            }
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new ProviderException("Failed to load generated key pair from keystore", s);
        }
        return keypair;
    }

    private void resetAll()
    {
        mEntryAlias = null;
        mEntryUid = -1;
        mJcaKeyAlgorithm = null;
        mKeymasterAlgorithm = -1;
        mKeymasterPurposes = null;
        mKeymasterBlockModes = null;
        mKeymasterEncryptionPaddings = null;
        mKeymasterSignaturePaddings = null;
        mKeymasterDigests = null;
        mKeySizeBits = 0;
        mSpec = null;
        mRSAPublicExponent = null;
        mEncryptionAtRestRequired = false;
        mRng = null;
        mKeyStore = null;
    }

    private void storeCertificate(String s, byte abyte0[], int i, String s1)
        throws ProviderException
    {
        i = mKeyStore.insert((new StringBuilder()).append(s).append(mEntryAlias).toString(), abyte0, mEntryUid, i);
        if(i != 1)
            throw new ProviderException(s1, KeyStore.getKeyStoreException(i));
        else
            return;
    }

    private void storeCertificateChain(int i, Iterable iterable)
        throws ProviderException
    {
        iterable = iterable.iterator();
        storeCertificate("USRCERT_", (byte[])iterable.next(), i, "Failed to store certificate");
        if(!iterable.hasNext())
            return;
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        byte abyte0[];
        for(; iterable.hasNext(); bytearrayoutputstream.write(abyte0, 0, abyte0.length))
            abyte0 = (byte[])iterable.next();

        storeCertificate("CACERT_", bytearrayoutputstream.toByteArray(), i, "Failed to store attestation CA certificate");
    }

    public KeyPair generateKeyPair()
    {
        int i;
        byte abyte0[];
        String s;
        if(mKeyStore == null || mSpec == null)
            throw new IllegalStateException("Not initialized");
        if(mEncryptionAtRestRequired)
            i = 1;
        else
            i = 0;
        if((i & 1) != 0 && mKeyStore.state() != android.security.KeyStore.State.UNLOCKED)
            throw new IllegalStateException("Encryption at rest using secure lock screen credential requested for key pair, but the user has not yet entered the credential");
        abyte0 = KeyStoreCryptoOperationUtils.getRandomBytesToMixIntoKeystoreRng(mRng, (mKeySizeBits + 7) / 8);
        Credentials.deleteAllTypesForAlias(mKeyStore, mEntryAlias, mEntryUid);
        s = (new StringBuilder()).append("USRPKEY_").append(mEntryAlias).toString();
        KeyPair keypair;
        generateKeystoreKeyPair(s, constructKeyGenerationArguments(), abyte0, i);
        keypair = loadKeystoreKeyPair(s);
        storeCertificateChain(i, createCertificateChain(s, keypair));
        if(false)
            Credentials.deleteAllTypesForAlias(mKeyStore, mEntryAlias, mEntryUid);
        return keypair;
        Exception exception;
        exception;
        if(true)
            Credentials.deleteAllTypesForAlias(mKeyStore, mEntryAlias, mEntryUid);
        throw exception;
    }

    public void initialize(int i, SecureRandom securerandom)
    {
        throw new IllegalArgumentException((new StringBuilder()).append(android/security/keystore/KeyGenParameterSpec.getName()).append(" or ").append(android/security/KeyPairGeneratorSpec.getName()).append(" required to initialize this KeyPairGenerator").toString());
    }

    public void initialize(AlgorithmParameterSpec algorithmparameterspec, SecureRandom securerandom)
        throws InvalidAlgorithmParameterException
    {
        resetAll();
        if(algorithmparameterspec != null)
            break MISSING_BLOCK_LABEL_70;
        algorithmparameterspec = JVM INSTR new #164 <Class InvalidAlgorithmParameterException>;
        securerandom = JVM INSTR new #134 <Class StringBuilder>;
        securerandom.StringBuilder();
        algorithmparameterspec.InvalidAlgorithmParameterException(securerandom.append("Must supply params of type ").append(android/security/keystore/KeyGenParameterSpec.getName()).append(" or ").append(android/security/KeyPairGeneratorSpec.getName()).toString());
        throw algorithmparameterspec;
        algorithmparameterspec;
        if(true)
            resetAll();
        throw algorithmparameterspec;
        boolean flag = false;
        int i = mOriginalKeymasterAlgorithm;
        if(!(algorithmparameterspec instanceof KeyGenParameterSpec)) goto _L2; else goto _L1
_L1:
        algorithmparameterspec = (KeyGenParameterSpec)algorithmparameterspec;
_L6:
        mEntryAlias = algorithmparameterspec.getKeystoreAlias();
        mEntryUid = algorithmparameterspec.getUid();
        mSpec = algorithmparameterspec;
        mKeymasterAlgorithm = i;
        mEncryptionAtRestRequired = flag;
        mKeySizeBits = algorithmparameterspec.getKeySize();
        initAlgorithmSpecificParameters();
        if(mKeySizeBits == -1)
            mKeySizeBits = getDefaultKeySize(i);
        checkValidKeySize(i, mKeySizeBits);
        if(algorithmparameterspec.getKeystoreAlias() == null)
        {
            algorithmparameterspec = JVM INSTR new #164 <Class InvalidAlgorithmParameterException>;
            algorithmparameterspec.InvalidAlgorithmParameterException("KeyStore entry alias not provided");
            throw algorithmparameterspec;
        }
        break MISSING_BLOCK_LABEL_650;
_L2:
        Object obj;
        if(!(algorithmparameterspec instanceof KeyPairGeneratorSpec))
            break MISSING_BLOCK_LABEL_580;
        obj = (KeyPairGeneratorSpec)algorithmparameterspec;
        algorithmparameterspec = ((KeyPairGeneratorSpec) (obj)).getKeyType();
        if(algorithmparameterspec == null)
            break MISSING_BLOCK_LABEL_209;
        i = KeyProperties.KeyAlgorithm.toKeymasterAsymmetricKeyAlgorithm(algorithmparameterspec);
        i;
        JVM INSTR tableswitch 1 3: default 236
    //                   1 455
    //                   2 236
    //                   3 297;
           goto _L3 _L4 _L3 _L5
_L4:
        break MISSING_BLOCK_LABEL_455;
_L3:
        try
        {
            algorithmparameterspec = JVM INSTR new #132 <Class ProviderException>;
            securerandom = JVM INSTR new #134 <Class StringBuilder>;
            securerandom.StringBuilder();
            algorithmparameterspec.ProviderException(securerandom.append("Unsupported algorithm: ").append(mKeymasterAlgorithm).toString());
            throw algorithmparameterspec;
        }
        // Misplaced declaration of an exception variable
        catch(AlgorithmParameterSpec algorithmparameterspec) { }
        securerandom = JVM INSTR new #164 <Class InvalidAlgorithmParameterException>;
        securerandom.InvalidAlgorithmParameterException(algorithmparameterspec);
        throw securerandom;
        securerandom;
        algorithmparameterspec = JVM INSTR new #164 <Class InvalidAlgorithmParameterException>;
        algorithmparameterspec.InvalidAlgorithmParameterException("Invalid key type in parameters", securerandom);
        throw algorithmparameterspec;
_L5:
        algorithmparameterspec = JVM INSTR new #863 <Class KeyGenParameterSpec$Builder>;
        algorithmparameterspec.KeyGenParameterSpec.Builder(((KeyPairGeneratorSpec) (obj)).getKeystoreAlias(), 12);
        algorithmparameterspec.setDigests(new String[] {
            "NONE", "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512"
        });
_L7:
        if(((KeyPairGeneratorSpec) (obj)).getKeySize() != -1)
            algorithmparameterspec.setKeySize(((KeyPairGeneratorSpec) (obj)).getKeySize());
        if(((KeyPairGeneratorSpec) (obj)).getAlgorithmParameterSpec() != null)
            algorithmparameterspec.setAlgorithmParameterSpec(((KeyPairGeneratorSpec) (obj)).getAlgorithmParameterSpec());
        algorithmparameterspec.setCertificateSubject(((KeyPairGeneratorSpec) (obj)).getSubjectDN());
        algorithmparameterspec.setCertificateSerialNumber(((KeyPairGeneratorSpec) (obj)).getSerialNumber());
        algorithmparameterspec.setCertificateNotBefore(((KeyPairGeneratorSpec) (obj)).getStartDate());
        algorithmparameterspec.setCertificateNotAfter(((KeyPairGeneratorSpec) (obj)).getEndDate());
        flag = ((KeyPairGeneratorSpec) (obj)).isEncryptionRequired();
        algorithmparameterspec.setUserAuthenticationRequired(false);
        algorithmparameterspec = algorithmparameterspec.build();
          goto _L6
        algorithmparameterspec = JVM INSTR new #863 <Class KeyGenParameterSpec$Builder>;
        algorithmparameterspec.KeyGenParameterSpec.Builder(((KeyPairGeneratorSpec) (obj)).getKeystoreAlias(), 15);
        algorithmparameterspec.setDigests(new String[] {
            "NONE", "MD5", "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512"
        });
        algorithmparameterspec.setEncryptionPaddings(new String[] {
            "NoPadding", "PKCS1Padding", "OAEPPadding"
        });
        algorithmparameterspec.setSignaturePaddings(new String[] {
            "PKCS1", "PSS"
        });
        algorithmparameterspec.setRandomizedEncryptionRequired(false);
          goto _L7
        securerandom = JVM INSTR new #164 <Class InvalidAlgorithmParameterException>;
        obj = JVM INSTR new #134 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        securerandom.InvalidAlgorithmParameterException(((StringBuilder) (obj)).append("Unsupported params class: ").append(algorithmparameterspec.getClass().getName()).append(". Supported: ").append(android/security/keystore/KeyGenParameterSpec.getName()).append(", ").append(android/security/KeyPairGeneratorSpec.getName()).toString());
        throw securerandom;
          goto _L6
        obj = KeyProperties.KeyAlgorithm.fromKeymasterAsymmetricKeyAlgorithm(i);
        mKeymasterPurposes = KeyProperties.Purpose.allToKeymaster(algorithmparameterspec.getPurposes());
        mKeymasterBlockModes = KeyProperties.BlockMode.allToKeymaster(algorithmparameterspec.getBlockModes());
        mKeymasterEncryptionPaddings = KeyProperties.EncryptionPadding.allToKeymaster(algorithmparameterspec.getEncryptionPaddings());
        if((algorithmparameterspec.getPurposes() & 1) == 0 || !algorithmparameterspec.isRandomizedEncryptionRequired()) goto _L9; else goto _L8
_L8:
        int ai[] = mKeymasterEncryptionPaddings;
        i = 0;
        int j = ai.length;
_L10:
        int k;
        if(i >= j)
            break; /* Loop/switch isn't completed */
        k = ai[i];
        try
        {
            if(!KeymasterUtils.isKeymasterPaddingSchemeIndCpaCompatibleWithAsymmetricCrypto(k))
            {
                securerandom = JVM INSTR new #164 <Class InvalidAlgorithmParameterException>;
                algorithmparameterspec = JVM INSTR new #134 <Class StringBuilder>;
                algorithmparameterspec.StringBuilder();
                securerandom.InvalidAlgorithmParameterException(algorithmparameterspec.append("Randomized encryption (IND-CPA) required but may be violated by padding scheme: ").append(KeyProperties.EncryptionPadding.fromKeymaster(k)).append(". See ").append(android/security/keystore/KeyGenParameterSpec.getName()).append(" documentation.").toString());
                throw securerandom;
            }
            break MISSING_BLOCK_LABEL_810;
        }
        // Misplaced declaration of an exception variable
        catch(AlgorithmParameterSpec algorithmparameterspec) { }
        securerandom = JVM INSTR new #164 <Class InvalidAlgorithmParameterException>;
        securerandom.InvalidAlgorithmParameterException(algorithmparameterspec);
        throw securerandom;
        i++;
        if(true) goto _L10; else goto _L9
_L9:
        mKeymasterSignaturePaddings = KeyProperties.SignaturePadding.allToKeymaster(algorithmparameterspec.getSignaturePaddings());
        if(!algorithmparameterspec.isDigestsSpecified())
            break MISSING_BLOCK_LABEL_913;
        mKeymasterDigests = KeyProperties.Digest.allToKeymaster(algorithmparameterspec.getDigests());
_L11:
        algorithmparameterspec = JVM INSTR new #156 <Class KeymasterArguments>;
        algorithmparameterspec.KeymasterArguments();
        KeymasterUtils.addUserAuthArgs(algorithmparameterspec, mSpec.isUserAuthenticationRequired(), mSpec.getUserAuthenticationValidityDurationSeconds(), mSpec.isUserAuthenticationValidWhileOnBody(), mSpec.isInvalidatedByBiometricEnrollment(), 0L);
        mJcaKeyAlgorithm = ((String) (obj));
        mRng = securerandom;
        mKeyStore = KeyStore.getInstance();
        if(false)
            resetAll();
        return;
        mKeymasterDigests = EmptyArray.INT;
          goto _L11
    }

    private static final int EC_DEFAULT_KEY_SIZE = 256;
    private static final int RSA_DEFAULT_KEY_SIZE = 2048;
    private static final int RSA_MAX_KEY_SIZE = 8192;
    private static final int RSA_MIN_KEY_SIZE = 512;
    private static final List SUPPORTED_EC_NIST_CURVE_NAMES;
    private static final Map SUPPORTED_EC_NIST_CURVE_NAME_TO_SIZE;
    private static final List SUPPORTED_EC_NIST_CURVE_SIZES;
    private boolean mEncryptionAtRestRequired;
    private String mEntryAlias;
    private int mEntryUid;
    private String mJcaKeyAlgorithm;
    private int mKeySizeBits;
    private KeyStore mKeyStore;
    private int mKeymasterAlgorithm;
    private int mKeymasterBlockModes[];
    private int mKeymasterDigests[];
    private int mKeymasterEncryptionPaddings[];
    private int mKeymasterPurposes[];
    private int mKeymasterSignaturePaddings[];
    private final int mOriginalKeymasterAlgorithm;
    private BigInteger mRSAPublicExponent;
    private SecureRandom mRng;
    private KeyGenParameterSpec mSpec;

    static 
    {
        SUPPORTED_EC_NIST_CURVE_NAME_TO_SIZE = new HashMap();
        SUPPORTED_EC_NIST_CURVE_NAMES = new ArrayList();
        SUPPORTED_EC_NIST_CURVE_SIZES = new ArrayList();
        SUPPORTED_EC_NIST_CURVE_NAME_TO_SIZE.put("p-224", Integer.valueOf(224));
        SUPPORTED_EC_NIST_CURVE_NAME_TO_SIZE.put("secp224r1", Integer.valueOf(224));
        SUPPORTED_EC_NIST_CURVE_NAME_TO_SIZE.put("p-256", Integer.valueOf(256));
        SUPPORTED_EC_NIST_CURVE_NAME_TO_SIZE.put("secp256r1", Integer.valueOf(256));
        SUPPORTED_EC_NIST_CURVE_NAME_TO_SIZE.put("prime256v1", Integer.valueOf(256));
        SUPPORTED_EC_NIST_CURVE_NAME_TO_SIZE.put("p-384", Integer.valueOf(384));
        SUPPORTED_EC_NIST_CURVE_NAME_TO_SIZE.put("secp384r1", Integer.valueOf(384));
        SUPPORTED_EC_NIST_CURVE_NAME_TO_SIZE.put("p-521", Integer.valueOf(521));
        SUPPORTED_EC_NIST_CURVE_NAME_TO_SIZE.put("secp521r1", Integer.valueOf(521));
        SUPPORTED_EC_NIST_CURVE_NAMES.addAll(SUPPORTED_EC_NIST_CURVE_NAME_TO_SIZE.keySet());
        Collections.sort(SUPPORTED_EC_NIST_CURVE_NAMES);
        SUPPORTED_EC_NIST_CURVE_SIZES.addAll(new HashSet(SUPPORTED_EC_NIST_CURVE_NAME_TO_SIZE.values()));
        Collections.sort(SUPPORTED_EC_NIST_CURVE_SIZES);
    }
}

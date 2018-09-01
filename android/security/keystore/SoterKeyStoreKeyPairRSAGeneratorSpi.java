// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.app.ActivityThread;
import android.content.Context;
import android.os.*;
import android.security.*;
import android.security.keymaster.KeyCharacteristics;
import android.security.keymaster.KeymasterArguments;
import android.service.gatekeeper.IGateKeeperService;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import com.android.org.bouncycastle.asn1.*;
import com.android.org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import com.android.org.bouncycastle.asn1.x509.*;
import com.android.org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import com.android.org.bouncycastle.jce.X509Principal;
import com.android.org.bouncycastle.jce.provider.X509CertificateObject;
import com.android.org.bouncycastle.x509.X509V3CertificateGenerator;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.RSAKeyGenParameterSpec;
import java.util.*;
import javax.security.auth.x500.X500Principal;
import libcore.util.EmptyArray;
import miui.securityspace.XSpaceUserHandle;

// Referenced classes of package android.security.keystore:
//            SoterUtil, KeyGenParameterSpec, SoterRSAKeyGenParameterSpec, KeymasterUtils, 
//            SoterKeyStoreProvider

public class SoterKeyStoreKeyPairRSAGeneratorSpi extends KeyPairGeneratorSpi
{

    public SoterKeyStoreKeyPairRSAGeneratorSpi()
    {
        mKeymasterAlgorithm = -1;
        isForSoter = false;
        isAutoSignedWithAttkWhenGetPublicKey = false;
        isAutoSignedWithCommonkWhenGetPublicKey = false;
        mAutoSignedKeyNameWhenGetPublicKey = "";
        isSecmsgFidCounterSignedWhenSign = false;
        isAutoAddCounterWhenGetPublicKey = false;
        isNeedNextAttk = false;
    }

    private void addAlgorithmSpecificParameters(KeymasterArguments keymasterarguments)
    {
        if(mRSAPublicExponent != null)
            keymasterarguments.addUnsignedLong(0x500000c8, mRSAPublicExponent);
        if(isForSoter)
        {
            keymasterarguments.addBoolean(0x70002af8);
            keymasterarguments.addUnsignedInt(0x30002aff, Process.myUid());
        }
        if(isAutoSignedWithAttkWhenGetPublicKey)
            keymasterarguments.addBoolean(0x70002af9);
        if(isAutoSignedWithCommonkWhenGetPublicKey)
        {
            keymasterarguments.addBoolean(0x70002afa);
            if(!SoterUtil.isNullOrNil(mAutoSignedKeyNameWhenGetPublicKey))
                keymasterarguments.addBytes(0x90002afb, (new StringBuilder()).append("USRPKEY_").append(mAutoSignedKeyNameWhenGetPublicKey).toString().getBytes());
        }
        if(isAutoAddCounterWhenGetPublicKey)
            keymasterarguments.addBoolean(0x70002afc);
        if(isSecmsgFidCounterSignedWhenSign)
            keymasterarguments.addBoolean(0x70002afd);
        if(isNeedNextAttk)
            keymasterarguments.addBoolean(0x70002afe);
    }

    private static void checkValidKeySize(int i, int j)
        throws InvalidAlgorithmParameterException
    {
        if(j < 512 || j > 8192)
            throw new InvalidAlgorithmParameterException("RSA key size must be >= 512 and <= 8192");
        else
            return;
    }

    private X509Certificate generateSelfSignedCertificate(PrivateKey privatekey, PublicKey publickey)
        throws Exception
    {
        Log.d("Soter", "generateSelfSignedCertificate");
        String s = getCertificateSignatureAlgorithm(mKeymasterAlgorithm, mKeySizeBits, mSpec);
        if(s == null)
        {
            Log.d("Soter", "generateSelfSignedCertificateWithFakeSignature1");
            return generateSelfSignedCertificateWithFakeSignature(publickey);
        }
        try
        {
            Log.d("Soter", "generateSelfSignedCertificateWithValidSignature");
            privatekey = generateSelfSignedCertificateWithValidSignature(privatekey, publickey, s);
        }
        // Misplaced declaration of an exception variable
        catch(PrivateKey privatekey)
        {
            Log.d("Soter", "generateSelfSignedCertificateWithFakeSignature2");
            return generateSelfSignedCertificateWithFakeSignature(publickey);
        }
        return privatekey;
    }

    private X509Certificate generateSelfSignedCertificateWithFakeSignature(PublicKey publickey)
        throws Exception
    {
        V3TBSCertificateGenerator v3tbscertificategenerator = new V3TBSCertificateGenerator();
        mKeymasterAlgorithm;
        JVM INSTR tableswitch 1 3: default 40
    //                   1 190
    //                   2 40
    //                   3 70;
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
        obj6 = JVM INSTR new #255 <Class ASN1InputStream>;
        ((ASN1InputStream) (obj6)).ASN1InputStream(publickey.getEncoded());
        v3tbscertificategenerator.setSubjectPublicKeyInfo(SubjectPublicKeyInfo.getInstance(((ASN1InputStream) (obj6)).readObject()));
        publickey = obj3;
        if(obj6 == null)
            break MISSING_BLOCK_LABEL_184;
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
        obj1;
        publickey = obj5;
_L9:
        throw obj1;
        obj6;
        obj = obj1;
        obj1 = obj6;
_L7:
        obj6 = obj;
        if(publickey == null)
            break MISSING_BLOCK_LABEL_247;
        publickey.close();
        obj6 = obj;
_L5:
        if(obj6 != null)
            throw obj6;
        else
            throw obj1;
        publickey;
        if(obj == null)
        {
            obj6 = publickey;
        } else
        {
            obj6 = obj;
            if(obj != publickey)
            {
                ((Throwable) (obj)).addSuppressed(publickey);
                obj6 = obj;
            }
        }
          goto _L5
        obj1;
        publickey = obj4;
        obj = obj2;
        continue; /* Loop/switch isn't completed */
        obj1;
        publickey = ((PublicKey) (obj6));
        obj = obj2;
        if(true) goto _L7; else goto _L6
_L6:
        obj1;
        publickey = ((PublicKey) (obj6));
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

    public static Context getApplicationContext()
    {
        android.app.Application application = ActivityThread.currentApplication();
        if(application == null)
            throw new IllegalStateException("Failed to obtain application Context from ActivityThread");
        else
            return application;
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
        if(!ArrayUtils.contains(KeyProperties.SignaturePadding.allToKeymaster(keygenparameterspec.getSignaturePaddings()), 5))
            return null;
        keygenparameterspec = getAvailableKeymasterSignatureDigests(keygenparameterspec.getDigests(), getSupportedEcdsaSignatureDigests());
        int k = -1;
        i = -1;
        keygenparameterspec = keygenparameterspec.iterator();
        do
        {
            if(!keygenparameterspec.hasNext())
                break;
            int l = ((Integer)keygenparameterspec.next()).intValue();
            int i1 = getDigestOutputSizeBits(l);
            if(i1 <= j - 240)
                if(k == -1)
                {
                    k = l;
                    i = i1;
                } else
                if(i1 > i)
                {
                    k = l;
                    i = i1;
                }
        } while(true);
        if(k == -1)
            return null;
        else
            return (new StringBuilder()).append(KeyProperties.Digest.fromKeymasterToSignatureAlgorithmDigest(k)).append("WithRSA").toString();
    }

    private static int getDefaultKeySize(int i)
    {
        return 2048;
    }

    public static int getDigestOutputSizeBits(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown digest: ").append(i).toString());

        case 0: // '\0'
            return -1;

        case 1: // '\001'
            return 128;

        case 2: // '\002'
            return 160;

        case 3: // '\003'
            return 224;

        case 4: // '\004'
            return 256;

        case 5: // '\005'
            return 384;

        case 6: // '\006'
            return 512;
        }
    }

    private IGateKeeperService getGateKeeperService()
        throws RemoteException
    {
        this;
        JVM INSTR monitorenter ;
        Object obj;
        if(mGateKeeperService == null)
            break MISSING_BLOCK_LABEL_18;
        obj = mGateKeeperService;
        this;
        JVM INSTR monitorexit ;
        return ((IGateKeeperService) (obj));
        obj = ServiceManager.getService("android.service.gatekeeper.IGateKeeperService");
        if(obj == null)
            break MISSING_BLOCK_LABEL_46;
        mGateKeeperService = android.service.gatekeeper.IGateKeeperService.Stub.asInterface(((android.os.IBinder) (obj)));
        obj = mGateKeeperService;
        this;
        JVM INSTR monitorexit ;
        return ((IGateKeeperService) (obj));
        Log.e("Soter", "Unable to acquire GateKeeperService");
        this;
        JVM INSTR monitorexit ;
        return null;
        Exception exception;
        exception;
        throw exception;
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

    private byte[] getRealKeyBlobByKeyName(String s)
    {
        Log.d("Soter", (new StringBuilder()).append("start retrieve key blob by key name: ").append(s).toString());
        return mKeyStore.get((new StringBuilder()).append("USRPKEY_").append(s).toString());
    }

    private static SecureRandom getRng()
    {
        if(sRng == null)
            sRng = new SecureRandom();
        return sRng;
    }

    private static String[] getSupportedEcdsaSignatureDigests()
    {
        return (new String[] {
            "NONE", "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512"
        });
    }

    private void initAlgorithmSpecificParameters()
        throws InvalidAlgorithmParameterException
    {
        Object obj = mSpec.getAlgorithmParameterSpec();
        Object obj1 = RSAKeyGenParameterSpec.F4;
        if(obj instanceof RSAKeyGenParameterSpec)
        {
            obj1 = (RSAKeyGenParameterSpec)obj;
            if(mKeySizeBits == -1)
                mKeySizeBits = ((RSAKeyGenParameterSpec) (obj1)).getKeysize();
            else
            if(mKeySizeBits != ((RSAKeyGenParameterSpec) (obj1)).getKeysize())
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("RSA key size must match  between ").append(mSpec).append(" and ").append(obj).append(": ").append(mKeySizeBits).append(" vs ").append(((RSAKeyGenParameterSpec) (obj1)).getKeysize()).toString());
            obj = ((RSAKeyGenParameterSpec) (obj1)).getPublicExponent();
            if(((BigInteger) (obj)).compareTo(BigInteger.ZERO) < 1)
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("RSA public exponent must be positive: ").append(obj).toString());
            obj1 = obj;
            if(((BigInteger) (obj)).compareTo(UINT64_MAX_VALUE) > 0)
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unsupported RSA public exponent: ").append(obj).append(". Maximum supported value: ").append(UINT64_MAX_VALUE).toString());
        }
        mRSAPublicExponent = ((BigInteger) (obj1));
        obj1 = SoterUtil.convertKeyNameToParameterSpec(mSpec.getKeystoreAlias());
        if(obj1 != null)
        {
            isForSoter = ((SoterRSAKeyGenParameterSpec) (obj1)).isForSoter();
            isAutoSignedWithAttkWhenGetPublicKey = ((SoterRSAKeyGenParameterSpec) (obj1)).isAutoSignedWithAttkWhenGetPublicKey();
            isAutoSignedWithCommonkWhenGetPublicKey = ((SoterRSAKeyGenParameterSpec) (obj1)).isAutoSignedWithCommonkWhenGetPublicKey();
            mAutoSignedKeyNameWhenGetPublicKey = ((SoterRSAKeyGenParameterSpec) (obj1)).getAutoSignedKeyNameWhenGetPublicKey();
            isSecmsgFidCounterSignedWhenSign = ((SoterRSAKeyGenParameterSpec) (obj1)).isSecmsgFidCounterSignedWhenSign();
            isAutoAddCounterWhenGetPublicKey = ((SoterRSAKeyGenParameterSpec) (obj1)).isAutoAddCounterWhenGetPublicKey();
            isNeedNextAttk = ((SoterRSAKeyGenParameterSpec) (obj1)).isNeedUseNextAttk();
        }
    }

    public static byte[] intToByteArray(int i)
    {
        ByteBuffer bytebuffer = ByteBuffer.allocate(4);
        bytebuffer.order(ByteOrder.nativeOrder());
        bytebuffer.putInt(i);
        return bytebuffer.array();
    }

    public static boolean isKeymasterPaddingSchemeIndCpaCompatibleWithAsymmetricCrypto(int i)
    {
        switch(i)
        {
        case 3: // '\003'
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported asymmetric encryption padding scheme: ").append(i).toString());

        case 1: // '\001'
            return false;

        case 2: // '\002'
        case 4: // '\004'
            return true;
        }
    }

    private void resetAll()
    {
        mEntryAlias = null;
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
        isForSoter = false;
        isAutoSignedWithAttkWhenGetPublicKey = false;
        isAutoSignedWithCommonkWhenGetPublicKey = false;
        mAutoSignedKeyNameWhenGetPublicKey = "";
        isSecmsgFidCounterSignedWhenSign = false;
        isAutoAddCounterWhenGetPublicKey = false;
        isNeedNextAttk = false;
    }

    public static BigInteger toUint64(long l)
    {
        if(l >= 0L)
            return BigInteger.valueOf(l);
        else
            return BigInteger.valueOf(l).add(UINT64_RANGE);
    }

    public KeyPair generateKeyPair()
    {
        int i;
        String s;
        if(mKeyStore == null || mSpec == null)
            throw new IllegalStateException("Not initialized");
        if(mEncryptionAtRestRequired)
            i = 1;
        else
            i = 0;
        if((i & 1) != 0 && mKeyStore.state() != android.security.KeyStore.State.UNLOCKED)
            throw new IllegalStateException("Encryption at rest using secure lock screen credential requested for key pair, but the user has not yet entered the credential");
        KeymasterArguments keymasterarguments = new KeymasterArguments();
        keymasterarguments.addUnsignedInt(0x30000003, mKeySizeBits);
        keymasterarguments.addEnum(0x10000002, mKeymasterAlgorithm);
        keymasterarguments.addEnums(0x20000001, mKeymasterPurposes);
        keymasterarguments.addEnums(0x20000004, mKeymasterBlockModes);
        keymasterarguments.addEnums(0x20000006, mKeymasterEncryptionPaddings);
        keymasterarguments.addEnums(0x20000006, mKeymasterSignaturePaddings);
        keymasterarguments.addEnums(0x20000005, mKeymasterDigests);
        byte abyte0[];
        Object obj1;
        Exception exception;
        int j;
        if(XSpaceUserHandle.isXSpaceUserId(UserHandle.myUserId()))
            try
            {
                KeymasterUtils.addUserAuthArgs(keymasterarguments, mSpec.isUserAuthenticationRequired(), mSpec.getUserAuthenticationValidityDurationSeconds(), false, false, getGateKeeperService().getSecureUserId(0));
            }
            catch(RemoteException remoteexception)
            {
                throw new ProviderException("Failed to connect with GateKeeper service", remoteexception);
            }
        else
            KeymasterUtils.addUserAuthArgs(keymasterarguments, mSpec.isUserAuthenticationRequired(), mSpec.getUserAuthenticationValidityDurationSeconds(), false, false, 0L);
        if(mSpec.getKeyValidityStart() != null)
            keymasterarguments.addDate(0x60000190, mSpec.getKeyValidityStart());
        if(mSpec.getKeyValidityForOriginationEnd() != null)
            keymasterarguments.addDate(0x60000191, mSpec.getKeyValidityForOriginationEnd());
        if(mSpec.getKeyValidityForConsumptionEnd() != null)
            keymasterarguments.addDate(0x60000192, mSpec.getKeyValidityForConsumptionEnd());
        addAlgorithmSpecificParameters(keymasterarguments);
        abyte0 = getRandomBytesToMixIntoKeystoreRng(mRng, (mKeySizeBits + 7) / 8);
        s = (new StringBuilder()).append("USRPKEY_").append(mEntryAlias).toString();
        Credentials.deleteAllTypesForAlias(mKeyStore, mEntryAlias);
        obj1 = JVM INSTR new #784 <Class KeyCharacteristics>;
        ((KeyCharacteristics) (obj1)).KeyCharacteristics();
        j = mKeyStore.generateKey(s, keymasterarguments, abyte0, i, ((KeyCharacteristics) (obj1)));
        if(j == 1)
            break MISSING_BLOCK_LABEL_438;
        obj1 = JVM INSTR new #218 <Class ProviderException>;
        ((ProviderException) (obj1)).ProviderException("Failed to generate key pair", KeyStore.getKeyStoreException(j));
        throw obj1;
        exception;
        if(true)
            Credentials.deleteAllTypesForAlias(mKeyStore, mEntryAlias);
        throw exception;
        Object obj2 = SoterKeyStoreProvider.loadAndroidKeyStoreKeyPairFromKeystore(mKeyStore, s);
        if(!mJcaKeyAlgorithm.equalsIgnoreCase(((KeyPair) (obj2)).getPrivate().getAlgorithm()))
        {
            ProviderException providerexception = JVM INSTR new #218 <Class ProviderException>;
            StringBuilder stringbuilder1 = JVM INSTR new #146 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            providerexception.ProviderException(stringbuilder1.append("Generated key pair algorithm does not match requested algorithm: ").append(((KeyPair) (obj2)).getPrivate().getAlgorithm()).append(" vs ").append(mJcaKeyAlgorithm).toString());
            throw providerexception;
        }
        break MISSING_BLOCK_LABEL_540;
        obj2;
        ProviderException providerexception1 = JVM INSTR new #218 <Class ProviderException>;
        providerexception1.ProviderException("Failed to load generated key pair from keystore", ((Throwable) (obj2)));
        throw providerexception1;
        X509Certificate x509certificate = generateSelfSignedCertificate(((KeyPair) (obj2)).getPrivate(), ((KeyPair) (obj2)).getPublic());
        byte abyte1[] = x509certificate.getEncoded();
        KeyStore keystore = mKeyStore;
        StringBuilder stringbuilder = JVM INSTR new #146 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        if(!keystore.put(stringbuilder.append("USRCERT_").append(mEntryAlias).toString(), abyte1, -1, i))
        {
            obj2 = JVM INSTR new #218 <Class ProviderException>;
            ((ProviderException) (obj2)).ProviderException("Failed to store self-signed certificate");
            throw obj2;
        }
        break MISSING_BLOCK_LABEL_654;
        Object obj;
        obj;
        obj2 = JVM INSTR new #218 <Class ProviderException>;
        ((ProviderException) (obj2)).ProviderException("Failed to generate self-signed certificate", ((Throwable) (obj)));
        throw obj2;
        obj;
        obj2 = JVM INSTR new #218 <Class ProviderException>;
        ((ProviderException) (obj2)).ProviderException("Failed to obtain encoded form of self-signed certificate", ((Throwable) (obj)));
        throw obj2;
        if(false)
            Credentials.deleteAllTypesForAlias(mKeyStore, mEntryAlias);
        return ((KeyPair) (obj2));
    }

    public void initialize(int i, SecureRandom securerandom)
    {
        throw new IllegalArgumentException((new StringBuilder()).append(android/security/keystore/KeyGenParameterSpec.getName()).append(" required to initialize this KeyPairGenerator").toString());
    }

    public void initialize(AlgorithmParameterSpec algorithmparameterspec, SecureRandom securerandom)
        throws InvalidAlgorithmParameterException
    {
        resetAll();
        if(algorithmparameterspec != null)
            break MISSING_BLOCK_LABEL_71;
        algorithmparameterspec = JVM INSTR new #171 <Class InvalidAlgorithmParameterException>;
        securerandom = JVM INSTR new #146 <Class StringBuilder>;
        securerandom.StringBuilder();
        algorithmparameterspec.InvalidAlgorithmParameterException(securerandom.append("Must supply params of type ").append(android/security/keystore/KeyGenParameterSpec.getName()).append(" or ").append(android/security/KeyPairGeneratorSpec.getName()).toString());
        throw algorithmparameterspec;
        algorithmparameterspec;
        if(true)
            resetAll();
        throw algorithmparameterspec;
        int i;
        Object obj;
        i = mOriginalKeymasterAlgorithm;
        if(algorithmparameterspec instanceof KeyGenParameterSpec)
        {
            obj = (KeyGenParameterSpec)algorithmparameterspec;
            mEntryAlias = SoterUtil.getPureKeyAliasFromKeyName(((KeyGenParameterSpec) (obj)).getKeystoreAlias());
            mSpec = ((KeyGenParameterSpec) (obj));
            mKeymasterAlgorithm = i;
            mEncryptionAtRestRequired = false;
            mKeySizeBits = ((KeyGenParameterSpec) (obj)).getKeySize();
            initAlgorithmSpecificParameters();
            if(mKeySizeBits == -1)
                mKeySizeBits = getDefaultKeySize(i);
            checkValidKeySize(i, mKeySizeBits);
            if(((KeyGenParameterSpec) (obj)).getKeystoreAlias() == null)
            {
                algorithmparameterspec = JVM INSTR new #171 <Class InvalidAlgorithmParameterException>;
                algorithmparameterspec.InvalidAlgorithmParameterException("KeyStore entry alias not provided");
                throw algorithmparameterspec;
            }
        } else
        {
            securerandom = JVM INSTR new #171 <Class InvalidAlgorithmParameterException>;
            obj = JVM INSTR new #146 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            securerandom.InvalidAlgorithmParameterException(((StringBuilder) (obj)).append("Unsupported params class: ").append(algorithmparameterspec.getClass().getName()).append(". Supported: ").append(android/security/keystore/KeyGenParameterSpec.getName()).append(", ").append(android/security/KeyPairGeneratorSpec.getName()).toString());
            throw securerandom;
        }
        algorithmparameterspec = KeyProperties.KeyAlgorithm.fromKeymasterAsymmetricKeyAlgorithm(i);
        mKeymasterPurposes = KeyProperties.Purpose.allToKeymaster(((KeyGenParameterSpec) (obj)).getPurposes());
        mKeymasterBlockModes = KeyProperties.BlockMode.allToKeymaster(((KeyGenParameterSpec) (obj)).getBlockModes());
        mKeymasterEncryptionPaddings = KeyProperties.EncryptionPadding.allToKeymaster(((KeyGenParameterSpec) (obj)).getEncryptionPaddings());
        if((((KeyGenParameterSpec) (obj)).getPurposes() & 1) == 0 || !((KeyGenParameterSpec) (obj)).isRandomizedEncryptionRequired()) goto _L2; else goto _L1
_L1:
        int ai[] = mKeymasterEncryptionPaddings;
        i = 0;
        int j = ai.length;
_L3:
        int k;
        if(i >= j)
            break; /* Loop/switch isn't completed */
        k = ai[i];
        try
        {
            if(!isKeymasterPaddingSchemeIndCpaCompatibleWithAsymmetricCrypto(k))
            {
                algorithmparameterspec = JVM INSTR new #171 <Class InvalidAlgorithmParameterException>;
                securerandom = JVM INSTR new #146 <Class StringBuilder>;
                securerandom.StringBuilder();
                algorithmparameterspec.InvalidAlgorithmParameterException(securerandom.append("Randomized encryption (IND-CPA) required but may be violated by padding scheme: ").append(KeyProperties.EncryptionPadding.fromKeymaster(k)).append(". See ").append(android/security/keystore/KeyGenParameterSpec.getName()).append(" documentation.").toString());
                throw algorithmparameterspec;
            }
            break MISSING_BLOCK_LABEL_407;
        }
        // Misplaced declaration of an exception variable
        catch(SecureRandom securerandom) { }
        algorithmparameterspec = JVM INSTR new #171 <Class InvalidAlgorithmParameterException>;
        algorithmparameterspec.InvalidAlgorithmParameterException(securerandom);
        throw algorithmparameterspec;
        i++;
        if(true) goto _L3; else goto _L2
_L2:
        mKeymasterSignaturePaddings = KeyProperties.SignaturePadding.allToKeymaster(((KeyGenParameterSpec) (obj)).getSignaturePaddings());
        if(!((KeyGenParameterSpec) (obj)).isDigestsSpecified()) goto _L5; else goto _L4
_L4:
        mKeymasterDigests = KeyProperties.Digest.allToKeymaster(((KeyGenParameterSpec) (obj)).getDigests());
_L6:
        boolean flag = XSpaceUserHandle.isXSpaceUserId(UserHandle.myUserId());
        if(!flag)
            break MISSING_BLOCK_LABEL_590;
        Object obj1 = JVM INSTR new #146 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.d("Soter", ((StringBuilder) (obj1)).append("Dual app bounding authkey with user_0: ").append(getGateKeeperService().getSecureUserId(0)).toString());
        obj1 = JVM INSTR new #120 <Class KeymasterArguments>;
        ((KeymasterArguments) (obj1)).KeymasterArguments();
        KeymasterUtils.addUserAuthArgs(((KeymasterArguments) (obj1)), mSpec.isUserAuthenticationRequired(), mSpec.getUserAuthenticationValidityDurationSeconds(), false, false, getGateKeeperService().getSecureUserId(0));
_L7:
        mJcaKeyAlgorithm = algorithmparameterspec;
        mRng = securerandom;
        mKeyStore = KeyStore.getInstance();
        if(false)
            resetAll();
        return;
_L5:
        mKeymasterDigests = EmptyArray.INT;
          goto _L6
        algorithmparameterspec;
        securerandom = JVM INSTR new #171 <Class InvalidAlgorithmParameterException>;
        securerandom.InvalidAlgorithmParameterException("Failed to connect with GateKeeper service", algorithmparameterspec);
        throw securerandom;
        Log.d("Soter", "Not dual app, bounding authkey with root sid");
        KeymasterArguments keymasterarguments = JVM INSTR new #120 <Class KeymasterArguments>;
        keymasterarguments.KeymasterArguments();
        KeymasterUtils.addUserAuthArgs(keymasterarguments, mSpec.isUserAuthenticationRequired(), mSpec.getUserAuthenticationValidityDurationSeconds(), false, false, 0L);
          goto _L7
    }

    public static final int KM_TAG_SOTER_AUTO_ADD_COUNTER_WHEN_GET_PUBLIC_KEY = 0x70002afc;
    public static final int KM_TAG_SOTER_AUTO_SIGNED_COMMON_KEY_WHEN_GET_PUBLIC_KEY = 0x90002afb;
    public static final int KM_TAG_SOTER_AUTO_SIGNED_COMMON_KEY_WHEN_GET_PUBLIC_KEY_BLOB = 0x90002b00;
    public static final int KM_TAG_SOTER_IS_AUTO_SIGNED_WITH_ATTK_WHEN_GET_PUBLIC_KEY = 0x70002af9;
    public static final int KM_TAG_SOTER_IS_AUTO_SIGNED_WITH_COMMON_KEY_WHEN_GET_PUBLIC_KEY = 0x70002afa;
    public static final int KM_TAG_SOTER_IS_FROM_SOTER = 0x70002af8;
    public static final int KM_TAG_SOTER_IS_SECMSG_FID_COUNTER_SIGNED_WHEN_SIGN = 0x70002afd;
    public static final int KM_TAG_SOTER_UID = 0x30002aff;
    public static final int KM_TAG_SOTER_USE_NEXT_ATTK = 0x70002afe;
    private static final int RSA_DEFAULT_KEY_SIZE = 2048;
    private static final int RSA_MAX_KEY_SIZE = 8192;
    private static final int RSA_MIN_KEY_SIZE = 512;
    public static final long UINT32_MAX_VALUE = 0xffffffffL;
    private static final long UINT32_RANGE = 0x100000000L;
    public static final BigInteger UINT64_MAX_VALUE;
    private static final BigInteger UINT64_RANGE;
    private static volatile SecureRandom sRng;
    private boolean isAutoAddCounterWhenGetPublicKey;
    private boolean isAutoSignedWithAttkWhenGetPublicKey;
    private boolean isAutoSignedWithCommonkWhenGetPublicKey;
    private boolean isForSoter;
    private boolean isNeedNextAttk;
    private boolean isSecmsgFidCounterSignedWhenSign;
    private String mAutoSignedKeyNameWhenGetPublicKey;
    private boolean mEncryptionAtRestRequired;
    private String mEntryAlias;
    private IGateKeeperService mGateKeeperService;
    private String mJcaKeyAlgorithm;
    private int mKeySizeBits;
    private KeyStore mKeyStore;
    private int mKeymasterAlgorithm;
    private int mKeymasterBlockModes[];
    private int mKeymasterDigests[];
    private int mKeymasterEncryptionPaddings[];
    private int mKeymasterPurposes[];
    private int mKeymasterSignaturePaddings[];
    private final int mOriginalKeymasterAlgorithm = 1;
    private BigInteger mRSAPublicExponent;
    private SecureRandom mRng;
    private KeyGenParameterSpec mSpec;

    static 
    {
        UINT64_RANGE = BigInteger.ONE.shiftLeft(64);
        UINT64_MAX_VALUE = UINT64_RANGE.subtract(BigInteger.ONE);
    }
}

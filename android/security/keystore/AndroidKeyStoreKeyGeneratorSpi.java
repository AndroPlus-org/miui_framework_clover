// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.security.Credentials;
import android.security.KeyStore;
import android.security.keymaster.KeyCharacteristics;
import android.security.keymaster.KeymasterArguments;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import javax.crypto.KeyGeneratorSpi;
import javax.crypto.SecretKey;
import libcore.util.EmptyArray;

// Referenced classes of package android.security.keystore:
//            KeyGenParameterSpec, KeymasterUtils, KeyStoreCryptoOperationUtils, AndroidKeyStoreSecretKey

public abstract class AndroidKeyStoreKeyGeneratorSpi extends KeyGeneratorSpi
{
    public static class AES extends AndroidKeyStoreKeyGeneratorSpi
    {

        protected void engineInit(AlgorithmParameterSpec algorithmparameterspec, SecureRandom securerandom)
            throws InvalidAlgorithmParameterException
        {
            engineInit(algorithmparameterspec, securerandom);
            if(mKeySizeBits != 128 && mKeySizeBits != 192 && mKeySizeBits != 256)
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unsupported key size: ").append(mKeySizeBits).append(". Supported: 128, 192, 256.").toString());
            else
                return;
        }

        public AES()
        {
            super(32, 128);
        }
    }

    protected static abstract class HmacBase extends AndroidKeyStoreKeyGeneratorSpi
    {

        protected HmacBase(int i)
        {
            super(128, i, KeymasterUtils.getDigestOutputSizeBits(i));
        }
    }

    public static class HmacSHA1 extends HmacBase
    {

        public HmacSHA1()
        {
            super(2);
        }
    }

    public static class HmacSHA224 extends HmacBase
    {

        public HmacSHA224()
        {
            super(3);
        }
    }

    public static class HmacSHA256 extends HmacBase
    {

        public HmacSHA256()
        {
            super(4);
        }
    }

    public static class HmacSHA384 extends HmacBase
    {

        public HmacSHA384()
        {
            super(5);
        }
    }

    public static class HmacSHA512 extends HmacBase
    {

        public HmacSHA512()
        {
            super(6);
        }
    }


    protected AndroidKeyStoreKeyGeneratorSpi(int i, int j)
    {
        this(i, -1, j);
    }

    protected AndroidKeyStoreKeyGeneratorSpi(int i, int j, int k)
    {
        mKeyStore = KeyStore.getInstance();
        mKeymasterAlgorithm = i;
        mKeymasterDigest = j;
        mDefaultKeySizeBits = k;
        if(mDefaultKeySizeBits <= 0)
            throw new IllegalArgumentException("Default key size must be positive");
        if(mKeymasterAlgorithm == 128 && mKeymasterDigest == -1)
            throw new IllegalArgumentException("Digest algorithm must be specified for HMAC key");
        else
            return;
    }

    private void resetAll()
    {
        mSpec = null;
        mRng = null;
        mKeySizeBits = -1;
        mKeymasterPurposes = null;
        mKeymasterPaddings = null;
        mKeymasterBlockModes = null;
    }

    protected SecretKey engineGenerateKey()
    {
        KeyGenParameterSpec keygenparameterspec;
        KeymasterArguments keymasterarguments;
        byte abyte0[];
        Object obj;
        KeyCharacteristics keycharacteristics;
        keygenparameterspec = mSpec;
        if(keygenparameterspec == null)
            throw new IllegalStateException("Not initialized");
        keymasterarguments = new KeymasterArguments();
        keymasterarguments.addUnsignedInt(0x30000003, mKeySizeBits);
        keymasterarguments.addEnum(0x10000002, mKeymasterAlgorithm);
        keymasterarguments.addEnums(0x20000001, mKeymasterPurposes);
        keymasterarguments.addEnums(0x20000004, mKeymasterBlockModes);
        keymasterarguments.addEnums(0x20000006, mKeymasterPaddings);
        keymasterarguments.addEnums(0x20000005, mKeymasterDigests);
        KeymasterUtils.addUserAuthArgs(keymasterarguments, keygenparameterspec.isUserAuthenticationRequired(), keygenparameterspec.getUserAuthenticationValidityDurationSeconds(), keygenparameterspec.isUserAuthenticationValidWhileOnBody(), keygenparameterspec.isInvalidatedByBiometricEnrollment(), 0L);
        KeymasterUtils.addMinMacLengthAuthorizationIfNecessary(keymasterarguments, mKeymasterAlgorithm, mKeymasterBlockModes, mKeymasterDigests);
        keymasterarguments.addDateIfNotNull(0x60000190, keygenparameterspec.getKeyValidityStart());
        keymasterarguments.addDateIfNotNull(0x60000191, keygenparameterspec.getKeyValidityForOriginationEnd());
        keymasterarguments.addDateIfNotNull(0x60000192, keygenparameterspec.getKeyValidityForConsumptionEnd());
        if((keygenparameterspec.getPurposes() & 1) != 0 && keygenparameterspec.isRandomizedEncryptionRequired() ^ true)
            keymasterarguments.addBoolean(0x70000007);
        abyte0 = KeyStoreCryptoOperationUtils.getRandomBytesToMixIntoKeystoreRng(mRng, (mKeySizeBits + 7) / 8);
        obj = (new StringBuilder()).append("USRSKEY_").append(keygenparameterspec.getKeystoreAlias()).toString();
        keycharacteristics = new KeyCharacteristics();
        int i;
        Credentials.deleteAllTypesForAlias(mKeyStore, keygenparameterspec.getKeystoreAlias(), keygenparameterspec.getUid());
        i = mKeyStore.generateKey(((String) (obj)), keymasterarguments, abyte0, keygenparameterspec.getUid(), 0, keycharacteristics);
        if(i == 1)
            break MISSING_BLOCK_LABEL_317;
        obj = JVM INSTR new #209 <Class ProviderException>;
        ((ProviderException) (obj)).ProviderException("Keystore operation failed", KeyStore.getKeyStoreException(i));
        throw obj;
        obj;
        if(true)
            Credentials.deleteAllTypesForAlias(mKeyStore, keygenparameterspec.getKeystoreAlias(), keygenparameterspec.getUid());
        throw obj;
        String s = KeyProperties.KeyAlgorithm.fromKeymasterSecretKeyAlgorithm(mKeymasterAlgorithm, mKeymasterDigest);
        obj = new AndroidKeyStoreSecretKey(((String) (obj)), keygenparameterspec.getUid(), s);
        if(false)
            Credentials.deleteAllTypesForAlias(mKeyStore, keygenparameterspec.getKeystoreAlias(), keygenparameterspec.getUid());
        return ((SecretKey) (obj));
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        ProviderException providerexception = JVM INSTR new #209 <Class ProviderException>;
        providerexception.ProviderException("Failed to obtain JCA secret key algorithm name", illegalargumentexception);
        throw providerexception;
    }

    protected void engineInit(int i, SecureRandom securerandom)
    {
        throw new UnsupportedOperationException((new StringBuilder()).append("Cannot initialize without a ").append(android/security/keystore/KeyGenParameterSpec.getName()).append(" parameter").toString());
    }

    protected void engineInit(SecureRandom securerandom)
    {
        throw new UnsupportedOperationException((new StringBuilder()).append("Cannot initialize without a ").append(android/security/keystore/KeyGenParameterSpec.getName()).append(" parameter").toString());
    }

    protected void engineInit(AlgorithmParameterSpec algorithmparameterspec, SecureRandom securerandom)
        throws InvalidAlgorithmParameterException
    {
        int i;
        i = 0;
        resetAll();
        if(algorithmparameterspec == null)
            break MISSING_BLOCK_LABEL_19;
        if(!((algorithmparameterspec instanceof KeyGenParameterSpec) ^ true))
            break MISSING_BLOCK_LABEL_70;
        algorithmparameterspec = JVM INSTR new #249 <Class InvalidAlgorithmParameterException>;
        securerandom = JVM INSTR new #177 <Class StringBuilder>;
        securerandom.StringBuilder();
        algorithmparameterspec.InvalidAlgorithmParameterException(securerandom.append("Cannot initialize without a ").append(android/security/keystore/KeyGenParameterSpec.getName()).append(" parameter").toString());
        throw algorithmparameterspec;
        algorithmparameterspec;
        if(true)
            resetAll();
        throw algorithmparameterspec;
        algorithmparameterspec = (KeyGenParameterSpec)algorithmparameterspec;
        if(algorithmparameterspec.getKeystoreAlias() == null)
        {
            algorithmparameterspec = JVM INSTR new #249 <Class InvalidAlgorithmParameterException>;
            algorithmparameterspec.InvalidAlgorithmParameterException("KeyStore entry alias not provided");
            throw algorithmparameterspec;
        }
        mRng = securerandom;
        mSpec = algorithmparameterspec;
        int j;
        if(algorithmparameterspec.getKeySize() != -1)
            j = algorithmparameterspec.getKeySize();
        else
            j = mDefaultKeySizeBits;
        mKeySizeBits = j;
        if(mKeySizeBits <= 0)
        {
            securerandom = JVM INSTR new #249 <Class InvalidAlgorithmParameterException>;
            algorithmparameterspec = JVM INSTR new #177 <Class StringBuilder>;
            algorithmparameterspec.StringBuilder();
            securerandom.InvalidAlgorithmParameterException(algorithmparameterspec.append("Key size must be positive: ").append(mKeySizeBits).toString());
            throw securerandom;
        }
        if(mKeySizeBits % 8 != 0)
        {
            securerandom = JVM INSTR new #249 <Class InvalidAlgorithmParameterException>;
            algorithmparameterspec = JVM INSTR new #177 <Class StringBuilder>;
            algorithmparameterspec.StringBuilder();
            securerandom.InvalidAlgorithmParameterException(algorithmparameterspec.append("Key size must be a multiple of 8: ").append(mKeySizeBits).toString());
            throw securerandom;
        }
        try
        {
            mKeymasterPurposes = KeyProperties.Purpose.allToKeymaster(algorithmparameterspec.getPurposes());
            mKeymasterPaddings = KeyProperties.EncryptionPadding.allToKeymaster(algorithmparameterspec.getEncryptionPaddings());
            if(algorithmparameterspec.getSignaturePaddings().length > 0)
            {
                algorithmparameterspec = JVM INSTR new #249 <Class InvalidAlgorithmParameterException>;
                algorithmparameterspec.InvalidAlgorithmParameterException("Signature paddings not supported for symmetric key algorithms");
                throw algorithmparameterspec;
            }
            break MISSING_BLOCK_LABEL_275;
        }
        // Misplaced declaration of an exception variable
        catch(AlgorithmParameterSpec algorithmparameterspec) { }
        securerandom = JVM INSTR new #249 <Class InvalidAlgorithmParameterException>;
        securerandom.InvalidAlgorithmParameterException(algorithmparameterspec);
        throw securerandom;
        mKeymasterBlockModes = KeyProperties.BlockMode.allToKeymaster(algorithmparameterspec.getBlockModes());
        if((algorithmparameterspec.getPurposes() & 1) == 0 || !algorithmparameterspec.isRandomizedEncryptionRequired()) goto _L2; else goto _L1
_L1:
        int l;
        securerandom = mKeymasterBlockModes;
        l = securerandom.length;
        int k = i;
_L3:
        if(k >= l)
            break; /* Loop/switch isn't completed */
        i = securerandom[k];
        if(!KeymasterUtils.isKeymasterBlockModeIndCpaCompatibleWithSymmetricCrypto(i))
        {
            algorithmparameterspec = JVM INSTR new #249 <Class InvalidAlgorithmParameterException>;
            securerandom = JVM INSTR new #177 <Class StringBuilder>;
            securerandom.StringBuilder();
            algorithmparameterspec.InvalidAlgorithmParameterException(securerandom.append("Randomized encryption (IND-CPA) required but may be violated by block mode: ").append(KeyProperties.BlockMode.fromKeymaster(i)).append(". See ").append(android/security/keystore/KeyGenParameterSpec.getName()).append(" documentation.").toString());
            throw algorithmparameterspec;
        }
        k++;
        if(true) goto _L3; else goto _L2
_L2:
        if(mKeymasterAlgorithm == 128)
        {
            if(mKeySizeBits < 64)
            {
                algorithmparameterspec = JVM INSTR new #249 <Class InvalidAlgorithmParameterException>;
                algorithmparameterspec.InvalidAlgorithmParameterException("HMAC key size must be at least 64 bits.");
                throw algorithmparameterspec;
            }
            mKeymasterDigests = (new int[] {
                mKeymasterDigest
            });
            if(algorithmparameterspec.isDigestsSpecified())
            {
                securerandom = KeyProperties.Digest.allToKeymaster(algorithmparameterspec.getDigests());
                if(securerandom.length != 1 || securerandom[0] != mKeymasterDigest)
                {
                    securerandom = JVM INSTR new #249 <Class InvalidAlgorithmParameterException>;
                    StringBuilder stringbuilder = JVM INSTR new #177 <Class StringBuilder>;
                    stringbuilder.StringBuilder();
                    securerandom.InvalidAlgorithmParameterException(stringbuilder.append("Unsupported digests specification: ").append(Arrays.asList(algorithmparameterspec.getDigests())).append(". Only ").append(KeyProperties.Digest.fromKeymaster(mKeymasterDigest)).append(" supported for this HMAC key algorithm").toString());
                    throw securerandom;
                }
            }
        } else
        {
            if(!algorithmparameterspec.isDigestsSpecified())
                break MISSING_BLOCK_LABEL_590;
            mKeymasterDigests = KeyProperties.Digest.allToKeymaster(algorithmparameterspec.getDigests());
        }
_L4:
        securerandom = JVM INSTR new #95  <Class KeymasterArguments>;
        securerandom.KeymasterArguments();
        KeymasterUtils.addUserAuthArgs(securerandom, algorithmparameterspec.isUserAuthenticationRequired(), algorithmparameterspec.getUserAuthenticationValidityDurationSeconds(), algorithmparameterspec.isUserAuthenticationValidWhileOnBody(), algorithmparameterspec.isInvalidatedByBiometricEnrollment(), 0L);
        if(false)
            resetAll();
        return;
        mKeymasterDigests = EmptyArray.INT;
          goto _L4
    }

    private final int mDefaultKeySizeBits;
    protected int mKeySizeBits;
    private final KeyStore mKeyStore;
    private final int mKeymasterAlgorithm;
    private int mKeymasterBlockModes[];
    private final int mKeymasterDigest;
    private int mKeymasterDigests[];
    private int mKeymasterPaddings[];
    private int mKeymasterPurposes[];
    private SecureRandom mRng;
    private KeyGenParameterSpec mSpec;
}

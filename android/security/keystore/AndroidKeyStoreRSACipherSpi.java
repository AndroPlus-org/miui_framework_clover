// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.security.KeyStore;
import android.security.keymaster.KeyCharacteristics;
import android.security.keymaster.KeymasterArguments;
import java.security.*;
import java.security.spec.*;
import javax.crypto.spec.OAEPParameterSpec;

// Referenced classes of package android.security.keystore:
//            AndroidKeyStoreCipherSpiBase, AndroidKeyStorePrivateKey, AndroidKeyStoreKey, AndroidKeyStorePublicKey, 
//            KeymasterUtils

abstract class AndroidKeyStoreRSACipherSpi extends AndroidKeyStoreCipherSpiBase
{
    public static final class NoPadding extends AndroidKeyStoreRSACipherSpi
    {

        protected boolean adjustConfigForEncryptingWithPrivateKey()
        {
            setKeymasterPurposeOverride(2);
            return true;
        }

        protected AlgorithmParameters engineGetParameters()
        {
            return null;
        }

        public volatile void finalize()
        {
            finalize();
        }

        protected final int getAdditionalEntropyAmountForBegin()
        {
            return 0;
        }

        protected final int getAdditionalEntropyAmountForFinish()
        {
            return 0;
        }

        protected void initAlgorithmSpecificParameters()
            throws InvalidKeyException
        {
        }

        protected void initAlgorithmSpecificParameters(AlgorithmParameters algorithmparameters)
            throws InvalidAlgorithmParameterException
        {
            if(algorithmparameters != null)
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unexpected parameters: ").append(algorithmparameters).append(". No parameters supported").toString());
            else
                return;
        }

        protected void initAlgorithmSpecificParameters(AlgorithmParameterSpec algorithmparameterspec)
            throws InvalidAlgorithmParameterException
        {
            if(algorithmparameterspec != null)
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unexpected parameters: ").append(algorithmparameterspec).append(". No parameters supported").toString());
            else
                return;
        }

        public NoPadding()
        {
            super(1);
        }
    }

    static abstract class OAEPWithMGF1Padding extends AndroidKeyStoreRSACipherSpi
    {

        protected final void addAlgorithmSpecificParametersToBegin(KeymasterArguments keymasterarguments)
        {
            addAlgorithmSpecificParametersToBegin(keymasterarguments);
            keymasterarguments.addEnum(0x20000005, mKeymasterDigest);
        }

        protected final AlgorithmParameters engineGetParameters()
        {
            OAEPParameterSpec oaepparameterspec = new OAEPParameterSpec(KeyProperties.Digest.fromKeymaster(mKeymasterDigest), "MGF1", MGF1ParameterSpec.SHA1, javax.crypto.spec.PSource.PSpecified.DEFAULT);
            AlgorithmParameters algorithmparameters;
            try
            {
                algorithmparameters = AlgorithmParameters.getInstance("OAEP");
                algorithmparameters.init(oaepparameterspec);
            }
            catch(NoSuchAlgorithmException nosuchalgorithmexception)
            {
                throw new ProviderException("Failed to obtain OAEP AlgorithmParameters", nosuchalgorithmexception);
            }
            catch(InvalidParameterSpecException invalidparameterspecexception)
            {
                throw new ProviderException("Failed to initialize OAEP AlgorithmParameters with an IV", invalidparameterspecexception);
            }
            return algorithmparameters;
        }

        protected final int getAdditionalEntropyAmountForBegin()
        {
            return 0;
        }

        protected final int getAdditionalEntropyAmountForFinish()
        {
            int i;
            if(isEncrypting())
                i = mDigestOutputSizeBytes;
            else
                i = 0;
            return i;
        }

        protected final void initAlgorithmSpecificParameters()
            throws InvalidKeyException
        {
        }

        protected final void initAlgorithmSpecificParameters(AlgorithmParameters algorithmparameters)
            throws InvalidAlgorithmParameterException
        {
            if(algorithmparameters == null)
                return;
            Object obj;
            try
            {
                obj = (OAEPParameterSpec)algorithmparameters.getParameterSpec(javax/crypto/spec/OAEPParameterSpec);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("OAEP parameters required, but not found in parameters: ").append(algorithmparameters).toString(), ((Throwable) (obj)));
            }
            if(obj == null)
            {
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("OAEP parameters required, but not provided in parameters: ").append(algorithmparameters).toString());
            } else
            {
                initAlgorithmSpecificParameters(((AlgorithmParameterSpec) (obj)));
                return;
            }
        }

        protected final void initAlgorithmSpecificParameters(AlgorithmParameterSpec algorithmparameterspec)
            throws InvalidAlgorithmParameterException
        {
            if(algorithmparameterspec == null)
                return;
            if(!(algorithmparameterspec instanceof OAEPParameterSpec))
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unsupported parameter spec: ").append(algorithmparameterspec).append(". Only OAEPParameterSpec supported").toString());
            algorithmparameterspec = (OAEPParameterSpec)algorithmparameterspec;
            if(!"MGF1".equalsIgnoreCase(algorithmparameterspec.getMGFAlgorithm()))
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unsupported MGF: ").append(algorithmparameterspec.getMGFAlgorithm()).append(". Only ").append("MGF1").append(" supported").toString());
            Object obj = algorithmparameterspec.getDigestAlgorithm();
            int i;
            try
            {
                i = KeyProperties.Digest.toKeymaster(((String) (obj)));
            }
            // Misplaced declaration of an exception variable
            catch(AlgorithmParameterSpec algorithmparameterspec)
            {
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unsupported digest: ").append(((String) (obj))).toString(), algorithmparameterspec);
            }
            switch(i)
            {
            default:
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unsupported digest: ").append(((String) (obj))).toString());

            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
            case 5: // '\005'
            case 6: // '\006'
                obj = algorithmparameterspec.getMGFParameters();
                break;
            }
            if(obj == null)
                throw new InvalidAlgorithmParameterException("MGF parameters must be provided");
            if(!(obj instanceof MGF1ParameterSpec))
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unsupported MGF parameters: ").append(obj).append(". Only MGF1ParameterSpec supported").toString());
            obj = ((MGF1ParameterSpec)obj).getDigestAlgorithm();
            if(!"SHA-1".equalsIgnoreCase(((String) (obj))))
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unsupported MGF1 digest: ").append(((String) (obj))).append(". Only ").append("SHA-1").append(" supported").toString());
            obj = algorithmparameterspec.getPSource();
            if(!(obj instanceof javax.crypto.spec.PSource.PSpecified))
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unsupported source of encoding input P: ").append(obj).append(". Only pSpecifiedEmpty (PSource.PSpecified.DEFAULT) supported").toString());
            algorithmparameterspec = ((javax.crypto.spec.PSource.PSpecified)obj).getValue();
            if(algorithmparameterspec != null && algorithmparameterspec.length > 0)
            {
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unsupported source of encoding input P: ").append(obj).append(". Only pSpecifiedEmpty (PSource.PSpecified.DEFAULT) supported").toString());
            } else
            {
                mKeymasterDigest = i;
                mDigestOutputSizeBytes = (KeymasterUtils.getDigestOutputSizeBits(i) + 7) / 8;
                return;
            }
        }

        protected final void loadAlgorithmSpecificParametersFromBeginResult(KeymasterArguments keymasterarguments)
        {
            loadAlgorithmSpecificParametersFromBeginResult(keymasterarguments);
        }

        private static final String MGF_ALGORITGM_MGF1 = "MGF1";
        private int mDigestOutputSizeBytes;
        private int mKeymasterDigest;

        OAEPWithMGF1Padding(int i)
        {
            super(2);
            mKeymasterDigest = -1;
            mKeymasterDigest = i;
            mDigestOutputSizeBytes = (KeymasterUtils.getDigestOutputSizeBits(i) + 7) / 8;
        }
    }

    public static class OAEPWithSHA1AndMGF1Padding extends OAEPWithMGF1Padding
    {

        public volatile void finalize()
        {
            super.finalize();
        }

        public OAEPWithSHA1AndMGF1Padding()
        {
            super(2);
        }
    }

    public static class OAEPWithSHA224AndMGF1Padding extends OAEPWithMGF1Padding
    {

        public volatile void finalize()
        {
            super.finalize();
        }

        public OAEPWithSHA224AndMGF1Padding()
        {
            super(3);
        }
    }

    public static class OAEPWithSHA256AndMGF1Padding extends OAEPWithMGF1Padding
    {

        public volatile void finalize()
        {
            super.finalize();
        }

        public OAEPWithSHA256AndMGF1Padding()
        {
            super(4);
        }
    }

    public static class OAEPWithSHA384AndMGF1Padding extends OAEPWithMGF1Padding
    {

        public volatile void finalize()
        {
            super.finalize();
        }

        public OAEPWithSHA384AndMGF1Padding()
        {
            super(5);
        }
    }

    public static class OAEPWithSHA512AndMGF1Padding extends OAEPWithMGF1Padding
    {

        public volatile void finalize()
        {
            super.finalize();
        }

        public OAEPWithSHA512AndMGF1Padding()
        {
            super(6);
        }
    }

    public static final class PKCS1Padding extends AndroidKeyStoreRSACipherSpi
    {

        protected boolean adjustConfigForEncryptingWithPrivateKey()
        {
            setKeymasterPurposeOverride(2);
            setKeymasterPaddingOverride(5);
            return true;
        }

        protected AlgorithmParameters engineGetParameters()
        {
            return null;
        }

        public volatile void finalize()
        {
            finalize();
        }

        protected final int getAdditionalEntropyAmountForBegin()
        {
            return 0;
        }

        protected final int getAdditionalEntropyAmountForFinish()
        {
            int i;
            if(isEncrypting())
                i = getModulusSizeBytes();
            else
                i = 0;
            return i;
        }

        protected void initAlgorithmSpecificParameters()
            throws InvalidKeyException
        {
        }

        protected void initAlgorithmSpecificParameters(AlgorithmParameters algorithmparameters)
            throws InvalidAlgorithmParameterException
        {
            if(algorithmparameters != null)
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unexpected parameters: ").append(algorithmparameters).append(". No parameters supported").toString());
            else
                return;
        }

        protected void initAlgorithmSpecificParameters(AlgorithmParameterSpec algorithmparameterspec)
            throws InvalidAlgorithmParameterException
        {
            if(algorithmparameterspec != null)
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unexpected parameters: ").append(algorithmparameterspec).append(". No parameters supported").toString());
            else
                return;
        }

        public PKCS1Padding()
        {
            super(4);
        }
    }


    AndroidKeyStoreRSACipherSpi(int i)
    {
        mModulusSizeBytes = -1;
        mKeymasterPadding = i;
    }

    protected void addAlgorithmSpecificParametersToBegin(KeymasterArguments keymasterarguments)
    {
        keymasterarguments.addEnum(0x10000002, 1);
        int i = getKeymasterPaddingOverride();
        int j = i;
        if(i == -1)
            j = mKeymasterPadding;
        keymasterarguments.addEnum(0x20000006, j);
        j = getKeymasterPurposeOverride();
        if(j != -1 && (j == 2 || j == 3))
            keymasterarguments.addEnum(0x20000005, 0);
    }

    protected boolean adjustConfigForEncryptingWithPrivateKey()
    {
        return false;
    }

    protected final int engineGetBlockSize()
    {
        return 0;
    }

    protected final byte[] engineGetIV()
    {
        return null;
    }

    protected final int engineGetOutputSize(int i)
    {
        return getModulusSizeBytes();
    }

    protected final int getKeymasterPaddingOverride()
    {
        return mKeymasterPaddingOverride;
    }

    protected final int getModulusSizeBytes()
    {
        if(mModulusSizeBytes == -1)
            throw new IllegalStateException("Not initialized");
        else
            return mModulusSizeBytes;
    }

    protected final void initKey(int i, Key key)
        throws InvalidKeyException
    {
        if(key == null)
            throw new InvalidKeyException("Unsupported key: null");
        if(!"RSA".equalsIgnoreCase(key.getAlgorithm()))
            throw new InvalidKeyException((new StringBuilder()).append("Unsupported key algorithm: ").append(key.getAlgorithm()).append(". Only ").append("RSA").append(" supported").toString());
        if(key instanceof AndroidKeyStorePrivateKey)
            key = (AndroidKeyStoreKey)key;
        else
        if(key instanceof AndroidKeyStorePublicKey)
            key = (AndroidKeyStoreKey)key;
        else
            throw new InvalidKeyException((new StringBuilder()).append("Unsupported key type: ").append(key).toString());
        if(key instanceof PrivateKey)
            switch(i)
            {
            default:
                throw new InvalidKeyException((new StringBuilder()).append("RSA private keys cannot be used with opmode: ").append(i).toString());

            case 1: // '\001'
            case 3: // '\003'
                if(!adjustConfigForEncryptingWithPrivateKey())
                    throw new InvalidKeyException((new StringBuilder()).append("RSA private keys cannot be used with ").append(opmodeToString(i)).append(" and padding ").append(KeyProperties.EncryptionPadding.fromKeymaster(mKeymasterPadding)).append(". Only RSA public keys supported for this mode").toString());
                break;

            case 2: // '\002'
            case 4: // '\004'
                break;
            }
        else
            switch(i)
            {
            default:
                throw new InvalidKeyException((new StringBuilder()).append("RSA public keys cannot be used with ").append(opmodeToString(i)).toString());

            case 2: // '\002'
            case 4: // '\004'
                throw new InvalidKeyException((new StringBuilder()).append("RSA public keys cannot be used with ").append(opmodeToString(i)).append(" and padding ").append(KeyProperties.EncryptionPadding.fromKeymaster(mKeymasterPadding)).append(". Only RSA private keys supported for this opmode.").toString());

            case 1: // '\001'
            case 3: // '\003'
                break;
            }
        KeyCharacteristics keycharacteristics = new KeyCharacteristics();
        i = getKeyStore().getKeyCharacteristics(key.getAlias(), null, null, key.getUid(), keycharacteristics);
        if(i != 1)
            throw getKeyStore().getInvalidKeyException(key.getAlias(), key.getUid(), i);
        long l = keycharacteristics.getUnsignedInt(0x30000003, -1L);
        if(l == -1L)
            throw new InvalidKeyException("Size of key not known");
        if(l > 0x7fffffffL)
        {
            throw new InvalidKeyException((new StringBuilder()).append("Key too large: ").append(l).append(" bits").toString());
        } else
        {
            mModulusSizeBytes = (int)((7L + l) / 8L);
            setKey(key);
            return;
        }
    }

    protected void loadAlgorithmSpecificParametersFromBeginResult(KeymasterArguments keymasterarguments)
    {
    }

    protected final void resetAll()
    {
        mModulusSizeBytes = -1;
        mKeymasterPaddingOverride = -1;
        super.resetAll();
    }

    protected final void resetWhilePreservingInitState()
    {
        super.resetWhilePreservingInitState();
    }

    protected final void setKeymasterPaddingOverride(int i)
    {
        mKeymasterPaddingOverride = i;
    }

    private final int mKeymasterPadding;
    private int mKeymasterPaddingOverride;
    private int mModulusSizeBytes;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.security.keymaster.KeymasterArguments;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
import javax.crypto.spec.IvParameterSpec;

// Referenced classes of package android.security.keystore:
//            AndroidKeyStoreCipherSpiBase, ArrayUtils, AndroidKeyStoreSecretKey

class AndroidKeyStoreUnauthenticatedAESCipherSpi extends AndroidKeyStoreCipherSpiBase
{
    static abstract class CBC extends AndroidKeyStoreUnauthenticatedAESCipherSpi
    {

        protected CBC(int i)
        {
            super(2, i, true);
        }
    }

    public static class CBC.NoPadding extends CBC
    {

        public volatile void finalize()
        {
            super.finalize();
        }

        public CBC.NoPadding()
        {
            super(1);
        }
    }

    public static class CBC.PKCS7Padding extends CBC
    {

        public volatile void finalize()
        {
            super.finalize();
        }

        public CBC.PKCS7Padding()
        {
            super(64);
        }
    }

    static abstract class CTR extends AndroidKeyStoreUnauthenticatedAESCipherSpi
    {

        protected CTR(int i)
        {
            super(3, i, true);
        }
    }

    public static class CTR.NoPadding extends CTR
    {

        public volatile void finalize()
        {
            super.finalize();
        }

        public CTR.NoPadding()
        {
            super(1);
        }
    }

    static abstract class ECB extends AndroidKeyStoreUnauthenticatedAESCipherSpi
    {

        protected ECB(int i)
        {
            super(1, i, false);
        }
    }

    public static class ECB.NoPadding extends ECB
    {

        public volatile void finalize()
        {
            super.finalize();
        }

        public ECB.NoPadding()
        {
            super(1);
        }
    }

    public static class ECB.PKCS7Padding extends ECB
    {

        public volatile void finalize()
        {
            super.finalize();
        }

        public ECB.PKCS7Padding()
        {
            super(64);
        }
    }


    AndroidKeyStoreUnauthenticatedAESCipherSpi(int i, int j, boolean flag)
    {
        mKeymasterBlockMode = i;
        mKeymasterPadding = j;
        mIvRequired = flag;
    }

    protected final void addAlgorithmSpecificParametersToBegin(KeymasterArguments keymasterarguments)
    {
        if(isEncrypting() && mIvRequired && mIvHasBeenUsed)
            throw new IllegalStateException("IV has already been used. Reusing IV in encryption mode violates security best practices.");
        keymasterarguments.addEnum(0x10000002, 32);
        keymasterarguments.addEnum(0x20000004, mKeymasterBlockMode);
        keymasterarguments.addEnum(0x20000006, mKeymasterPadding);
        if(mIvRequired && mIv != null)
            keymasterarguments.addBytes(0x900003e9, mIv);
    }

    protected final int engineGetBlockSize()
    {
        return 16;
    }

    protected final byte[] engineGetIV()
    {
        return ArrayUtils.cloneIfNotEmpty(mIv);
    }

    protected final int engineGetOutputSize(int i)
    {
        return i + 48;
    }

    protected final AlgorithmParameters engineGetParameters()
    {
        if(!mIvRequired)
            return null;
        if(mIv != null && mIv.length > 0)
        {
            AlgorithmParameters algorithmparameters;
            try
            {
                algorithmparameters = AlgorithmParameters.getInstance("AES");
                IvParameterSpec ivparameterspec = JVM INSTR new #106 <Class IvParameterSpec>;
                ivparameterspec.IvParameterSpec(mIv);
                algorithmparameters.init(ivparameterspec);
            }
            catch(NoSuchAlgorithmException nosuchalgorithmexception)
            {
                throw new ProviderException("Failed to obtain AES AlgorithmParameters", nosuchalgorithmexception);
            }
            catch(InvalidParameterSpecException invalidparameterspecexception)
            {
                throw new ProviderException("Failed to initialize AES AlgorithmParameters with an IV", invalidparameterspecexception);
            }
            return algorithmparameters;
        } else
        {
            return null;
        }
    }

    protected final int getAdditionalEntropyAmountForBegin()
    {
        return !mIvRequired || mIv != null || !isEncrypting() ? 0 : 16;
    }

    protected final int getAdditionalEntropyAmountForFinish()
    {
        return 0;
    }

    protected final void initAlgorithmSpecificParameters()
        throws InvalidKeyException
    {
        if(!mIvRequired)
            return;
        if(!isEncrypting())
            throw new InvalidKeyException("IV required when decrypting. Use IvParameterSpec or AlgorithmParameters to provide it.");
        else
            return;
    }

    protected final void initAlgorithmSpecificParameters(AlgorithmParameters algorithmparameters)
        throws InvalidAlgorithmParameterException
    {
        if(!mIvRequired)
            if(algorithmparameters != null)
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unsupported parameters: ").append(algorithmparameters).toString());
            else
                return;
        if(algorithmparameters == null)
            if(!isEncrypting())
                throw new InvalidAlgorithmParameterException("IV required when decrypting. Use IvParameterSpec or AlgorithmParameters to provide it.");
            else
                return;
        if(!"AES".equalsIgnoreCase(algorithmparameters.getAlgorithm()))
            throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unsupported AlgorithmParameters algorithm: ").append(algorithmparameters.getAlgorithm()).append(". Supported: AES").toString());
        IvParameterSpec ivparameterspec;
        try
        {
            ivparameterspec = (IvParameterSpec)algorithmparameters.getParameterSpec(javax/crypto/spec/IvParameterSpec);
        }
        catch(InvalidParameterSpecException invalidparameterspecexception)
        {
            if(!isEncrypting())
            {
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("IV required when decrypting, but not found in parameters: ").append(algorithmparameters).toString(), invalidparameterspecexception);
            } else
            {
                mIv = null;
                return;
            }
        }
        mIv = ivparameterspec.getIV();
        if(mIv == null)
            throw new InvalidAlgorithmParameterException("Null IV in AlgorithmParameters");
        else
            return;
    }

    protected final void initAlgorithmSpecificParameters(AlgorithmParameterSpec algorithmparameterspec)
        throws InvalidAlgorithmParameterException
    {
        if(!mIvRequired)
            if(algorithmparameterspec != null)
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unsupported parameters: ").append(algorithmparameterspec).toString());
            else
                return;
        if(algorithmparameterspec == null)
            if(!isEncrypting())
                throw new InvalidAlgorithmParameterException("IvParameterSpec must be provided when decrypting");
            else
                return;
        if(!(algorithmparameterspec instanceof IvParameterSpec))
            throw new InvalidAlgorithmParameterException("Only IvParameterSpec supported");
        mIv = ((IvParameterSpec)algorithmparameterspec).getIV();
        if(mIv == null)
            throw new InvalidAlgorithmParameterException("Null IV in IvParameterSpec");
        else
            return;
    }

    protected final void initKey(int i, Key key)
        throws InvalidKeyException
    {
        if(!(key instanceof AndroidKeyStoreSecretKey))
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("Unsupported key: ");
            if(key != null)
                key = key.getClass().getName();
            else
                key = "null";
            throw new InvalidKeyException(stringbuilder.append(key).toString());
        }
        if(!"AES".equalsIgnoreCase(key.getAlgorithm()))
        {
            throw new InvalidKeyException((new StringBuilder()).append("Unsupported key algorithm: ").append(key.getAlgorithm()).append(". Only ").append("AES").append(" supported").toString());
        } else
        {
            setKey((AndroidKeyStoreSecretKey)key);
            return;
        }
    }

    protected final void loadAlgorithmSpecificParametersFromBeginResult(KeymasterArguments keymasterarguments)
    {
        mIvHasBeenUsed = true;
        byte abyte0[] = keymasterarguments.getBytes(0x900003e9, null);
        keymasterarguments = abyte0;
        if(abyte0 != null)
        {
            keymasterarguments = abyte0;
            if(abyte0.length == 0)
                keymasterarguments = null;
        }
        if(!mIvRequired) goto _L2; else goto _L1
_L1:
        if(mIv != null) goto _L4; else goto _L3
_L3:
        mIv = keymasterarguments;
_L6:
        return;
_L4:
        if(keymasterarguments != null && Arrays.equals(keymasterarguments, mIv) ^ true)
            throw new ProviderException("IV in use differs from provided IV");
        continue; /* Loop/switch isn't completed */
_L2:
        if(keymasterarguments != null)
            throw new ProviderException("IV in use despite IV not being used by this transformation");
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected final void resetAll()
    {
        mIv = null;
        mIvHasBeenUsed = false;
        super.resetAll();
    }

    protected final void resetWhilePreservingInitState()
    {
        super.resetWhilePreservingInitState();
    }

    private static final int BLOCK_SIZE_BYTES = 16;
    private byte mIv[];
    private boolean mIvHasBeenUsed;
    private final boolean mIvRequired;
    private final int mKeymasterBlockMode;
    private final int mKeymasterPadding;
}

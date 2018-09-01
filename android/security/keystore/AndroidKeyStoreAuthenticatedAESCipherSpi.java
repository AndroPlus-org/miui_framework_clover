// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.os.IBinder;
import android.security.KeyStore;
import android.security.KeyStoreException;
import android.security.keymaster.KeymasterArguments;
import android.security.keymaster.OperationResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
import javax.crypto.spec.GCMParameterSpec;
import libcore.util.EmptyArray;

// Referenced classes of package android.security.keystore:
//            AndroidKeyStoreCipherSpiBase, ArrayUtils, AndroidKeyStoreSecretKey, KeyStoreCryptoOperationStreamer, 
//            KeyStoreCryptoOperationChunkedStreamer

abstract class AndroidKeyStoreAuthenticatedAESCipherSpi extends AndroidKeyStoreCipherSpiBase
{
    private static class AdditionalAuthenticationDataStream
        implements KeyStoreCryptoOperationChunkedStreamer.Stream
    {

        public OperationResult finish(byte abyte0[], byte abyte1[])
        {
            if(abyte1 != null && abyte1.length > 0)
                throw new ProviderException("AAD stream does not support additional entropy");
            else
                return new OperationResult(1, mOperationToken, 0L, 0, EmptyArray.BYTE, new KeymasterArguments());
        }

        public OperationResult update(byte abyte0[])
        {
            Object obj = new KeymasterArguments();
            ((KeymasterArguments) (obj)).addBytes(0x900003e8, abyte0);
            obj = mKeyStore.update(mOperationToken, ((KeymasterArguments) (obj)), null);
            if(((OperationResult) (obj)).resultCode == 1)
                abyte0 = new OperationResult(((OperationResult) (obj)).resultCode, ((OperationResult) (obj)).token, ((OperationResult) (obj)).operationHandle, abyte0.length, ((OperationResult) (obj)).output, ((OperationResult) (obj)).outParams);
            else
                abyte0 = ((byte []) (obj));
            return abyte0;
        }

        private final KeyStore mKeyStore;
        private final IBinder mOperationToken;

        private AdditionalAuthenticationDataStream(KeyStore keystore, IBinder ibinder)
        {
            mKeyStore = keystore;
            mOperationToken = ibinder;
        }

        AdditionalAuthenticationDataStream(KeyStore keystore, IBinder ibinder, AdditionalAuthenticationDataStream additionalauthenticationdatastream)
        {
            this(keystore, ibinder);
        }
    }

    private static class BufferAllOutputUntilDoFinalStreamer
        implements KeyStoreCryptoOperationStreamer
    {

        public byte[] doFinal(byte abyte0[], int i, int j, byte abyte1[], byte abyte2[])
            throws KeyStoreException
        {
            abyte0 = mDelegate.doFinal(abyte0, i, j, abyte1, abyte2);
            if(abyte0 != null)
                try
                {
                    mBufferedOutput.write(abyte0);
                }
                // Misplaced declaration of an exception variable
                catch(byte abyte0[])
                {
                    throw new ProviderException("Failed to buffer output", abyte0);
                }
            abyte0 = mBufferedOutput.toByteArray();
            mBufferedOutput.reset();
            mProducedOutputSizeBytes = mProducedOutputSizeBytes + (long)abyte0.length;
            return abyte0;
        }

        public long getConsumedInputSizeBytes()
        {
            return mDelegate.getConsumedInputSizeBytes();
        }

        public long getProducedOutputSizeBytes()
        {
            return mProducedOutputSizeBytes;
        }

        public byte[] update(byte abyte0[], int i, int j)
            throws KeyStoreException
        {
            abyte0 = mDelegate.update(abyte0, i, j);
            if(abyte0 != null)
                try
                {
                    mBufferedOutput.write(abyte0);
                }
                // Misplaced declaration of an exception variable
                catch(byte abyte0[])
                {
                    throw new ProviderException("Failed to buffer output", abyte0);
                }
            return EmptyArray.BYTE;
        }

        private ByteArrayOutputStream mBufferedOutput;
        private final KeyStoreCryptoOperationStreamer mDelegate;
        private long mProducedOutputSizeBytes;

        private BufferAllOutputUntilDoFinalStreamer(KeyStoreCryptoOperationStreamer keystorecryptooperationstreamer)
        {
            mBufferedOutput = new ByteArrayOutputStream();
            mDelegate = keystorecryptooperationstreamer;
        }

        BufferAllOutputUntilDoFinalStreamer(KeyStoreCryptoOperationStreamer keystorecryptooperationstreamer, BufferAllOutputUntilDoFinalStreamer bufferalloutputuntildofinalstreamer)
        {
            this(keystorecryptooperationstreamer);
        }
    }

    static abstract class GCM extends AndroidKeyStoreAuthenticatedAESCipherSpi
    {

        protected final void addAlgorithmSpecificParametersToBegin(KeymasterArguments keymasterarguments)
        {
            addAlgorithmSpecificParametersToBegin(keymasterarguments);
            keymasterarguments.addUnsignedInt(0x300003eb, mTagLengthBits);
        }

        protected final KeyStoreCryptoOperationStreamer createAdditionalAuthenticationDataStreamer(KeyStore keystore, IBinder ibinder)
        {
            return new KeyStoreCryptoOperationChunkedStreamer(new AdditionalAuthenticationDataStream(keystore, ibinder, null));
        }

        protected KeyStoreCryptoOperationStreamer createMainDataStreamer(KeyStore keystore, IBinder ibinder)
        {
            keystore = new KeyStoreCryptoOperationChunkedStreamer(new KeyStoreCryptoOperationChunkedStreamer.MainDataStream(keystore, ibinder));
            if(isEncrypting())
                return keystore;
            else
                return new BufferAllOutputUntilDoFinalStreamer(keystore, null);
        }

        protected final AlgorithmParameters engineGetParameters()
        {
            byte abyte0[] = getIv();
            if(abyte0 != null && abyte0.length > 0)
            {
                AlgorithmParameters algorithmparameters;
                try
                {
                    algorithmparameters = AlgorithmParameters.getInstance("GCM");
                    GCMParameterSpec gcmparameterspec = JVM INSTR new #82  <Class GCMParameterSpec>;
                    gcmparameterspec.GCMParameterSpec(mTagLengthBits, abyte0);
                    algorithmparameters.init(gcmparameterspec);
                }
                catch(NoSuchAlgorithmException nosuchalgorithmexception)
                {
                    throw new ProviderException("Failed to obtain GCM AlgorithmParameters", nosuchalgorithmexception);
                }
                catch(InvalidParameterSpecException invalidparameterspecexception)
                {
                    throw new ProviderException("Failed to initialize GCM AlgorithmParameters", invalidparameterspecexception);
                }
                return algorithmparameters;
            } else
            {
                return null;
            }
        }

        protected final int getAdditionalEntropyAmountForBegin()
        {
            return getIv() != null || !isEncrypting() ? 0 : 12;
        }

        protected final int getAdditionalEntropyAmountForFinish()
        {
            return 0;
        }

        protected final int getTagLengthBits()
        {
            return mTagLengthBits;
        }

        protected final void initAlgorithmSpecificParameters()
            throws InvalidKeyException
        {
            if(!isEncrypting())
                throw new InvalidKeyException("IV required when decrypting. Use IvParameterSpec or AlgorithmParameters to provide it.");
            else
                return;
        }

        protected final void initAlgorithmSpecificParameters(AlgorithmParameters algorithmparameters)
            throws InvalidAlgorithmParameterException
        {
            if(algorithmparameters == null)
                if(!isEncrypting())
                    throw new InvalidAlgorithmParameterException("IV required when decrypting. Use GCMParameterSpec or GCM AlgorithmParameters to provide it.");
                else
                    return;
            if(!"GCM".equalsIgnoreCase(algorithmparameters.getAlgorithm()))
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unsupported AlgorithmParameters algorithm: ").append(algorithmparameters.getAlgorithm()).append(". Supported: GCM").toString());
            GCMParameterSpec gcmparameterspec;
            try
            {
                gcmparameterspec = (GCMParameterSpec)algorithmparameters.getParameterSpec(javax/crypto/spec/GCMParameterSpec);
            }
            catch(InvalidParameterSpecException invalidparameterspecexception)
            {
                if(!isEncrypting())
                {
                    throw new InvalidAlgorithmParameterException((new StringBuilder()).append("IV and tag length required when decrypting, but not found in parameters: ").append(algorithmparameters).toString(), invalidparameterspecexception);
                } else
                {
                    setIv(null);
                    return;
                }
            }
            initAlgorithmSpecificParameters(((AlgorithmParameterSpec) (gcmparameterspec)));
        }

        protected final void initAlgorithmSpecificParameters(AlgorithmParameterSpec algorithmparameterspec)
            throws InvalidAlgorithmParameterException
        {
            if(algorithmparameterspec == null)
                if(!isEncrypting())
                    throw new InvalidAlgorithmParameterException("GCMParameterSpec must be provided when decrypting");
                else
                    return;
            if(!(algorithmparameterspec instanceof GCMParameterSpec))
                throw new InvalidAlgorithmParameterException("Only GCMParameterSpec supported");
            GCMParameterSpec gcmparameterspec = (GCMParameterSpec)algorithmparameterspec;
            algorithmparameterspec = gcmparameterspec.getIV();
            if(algorithmparameterspec == null)
                throw new InvalidAlgorithmParameterException("Null IV in GCMParameterSpec");
            if(algorithmparameterspec.length != 12)
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unsupported IV length: ").append(algorithmparameterspec.length).append(" bytes. Only ").append(12).append(" bytes long IV supported").toString());
            int i;
            for(i = gcmparameterspec.getTLen(); i < 96 || i > 128 || i % 8 != 0;)
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unsupported tag length: ").append(i).append(" bits").append(". Supported lengths: 96, 104, 112, 120, 128").toString());

            setIv(algorithmparameterspec);
            mTagLengthBits = i;
        }

        protected final void resetAll()
        {
            mTagLengthBits = 128;
            resetAll();
        }

        protected final void resetWhilePreservingInitState()
        {
            resetWhilePreservingInitState();
        }

        private static final int DEFAULT_TAG_LENGTH_BITS = 128;
        private static final int IV_LENGTH_BYTES = 12;
        private static final int MAX_SUPPORTED_TAG_LENGTH_BITS = 128;
        static final int MIN_SUPPORTED_TAG_LENGTH_BITS = 96;
        private int mTagLengthBits;

        GCM(int i)
        {
            super(32, i);
            mTagLengthBits = 128;
        }
    }

    public static final class GCM.NoPadding extends GCM
    {

        protected final int engineGetOutputSize(int i)
        {
            int j = (getTagLengthBits() + 7) / 8;
            long l;
            if(isEncrypting())
                l = (getConsumedInputSizeBytes() - getProducedOutputSizeBytes()) + (long)i + (long)j;
            else
                l = ((getConsumedInputSizeBytes() - getProducedOutputSizeBytes()) + (long)i) - (long)j;
            if(l < 0L)
                return 0;
            if(l > 0x7fffffffL)
                return 0x7fffffff;
            else
                return (int)l;
        }

        public volatile void finalize()
        {
            super.finalize();
        }

        public GCM.NoPadding()
        {
            super(1);
        }
    }


    AndroidKeyStoreAuthenticatedAESCipherSpi(int i, int j)
    {
        mKeymasterBlockMode = i;
        mKeymasterPadding = j;
    }

    protected void addAlgorithmSpecificParametersToBegin(KeymasterArguments keymasterarguments)
    {
        if(isEncrypting() && mIvHasBeenUsed)
            throw new IllegalStateException("IV has already been used. Reusing IV in encryption mode violates security best practices.");
        keymasterarguments.addEnum(0x10000002, 32);
        keymasterarguments.addEnum(0x20000004, mKeymasterBlockMode);
        keymasterarguments.addEnum(0x20000006, mKeymasterPadding);
        if(mIv != null)
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

    protected byte[] getIv()
    {
        return mIv;
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
        if(mIv == null)
            mIv = keymasterarguments;
        else
        if(keymasterarguments != null && Arrays.equals(keymasterarguments, mIv) ^ true)
            throw new ProviderException("IV in use differs from provided IV");
    }

    protected void resetAll()
    {
        mIv = null;
        mIvHasBeenUsed = false;
        super.resetAll();
    }

    protected void setIv(byte abyte0[])
    {
        mIv = abyte0;
    }

    private static final int BLOCK_SIZE_BYTES = 16;
    private byte mIv[];
    private boolean mIvHasBeenUsed;
    private final int mKeymasterBlockMode;
    private final int mKeymasterPadding;
}

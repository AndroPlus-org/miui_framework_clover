// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.os.IBinder;
import android.security.KeyStore;
import android.security.KeyStoreException;
import android.security.keymaster.KeyCharacteristics;
import android.security.keymaster.KeymasterArguments;
import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import libcore.util.EmptyArray;

// Referenced classes of package android.security.keystore:
//            AndroidKeyStoreSignatureSpiBase, AndroidKeyStoreKey, KeyStoreCryptoOperationStreamer

abstract class AndroidKeyStoreECDSASignatureSpi extends AndroidKeyStoreSignatureSpiBase
{
    public static final class NONE extends AndroidKeyStoreECDSASignatureSpi
    {

        protected KeyStoreCryptoOperationStreamer createMainDataStreamer(KeyStore keystore, IBinder ibinder)
        {
            return new TruncateToFieldSizeMessageStreamer(createMainDataStreamer(keystore, ibinder), getGroupSizeBits(), null);
        }

        public NONE()
        {
            super(0);
        }
    }

    private static class NONE.TruncateToFieldSizeMessageStreamer
        implements KeyStoreCryptoOperationStreamer
    {

        public byte[] doFinal(byte abyte0[], int i, int j, byte abyte1[], byte abyte2[])
            throws KeyStoreException
        {
            if(j > 0)
            {
                mConsumedInputSizeBytes = mConsumedInputSizeBytes + (long)j;
                mInputBuffer.write(abyte0, i, j);
            }
            abyte0 = mInputBuffer.toByteArray();
            mInputBuffer.reset();
            return mDelegate.doFinal(abyte0, 0, Math.min(abyte0.length, (mGroupSizeBits + 7) / 8), abyte1, abyte2);
        }

        public long getConsumedInputSizeBytes()
        {
            return mConsumedInputSizeBytes;
        }

        public long getProducedOutputSizeBytes()
        {
            return mDelegate.getProducedOutputSizeBytes();
        }

        public byte[] update(byte abyte0[], int i, int j)
            throws KeyStoreException
        {
            if(j > 0)
            {
                mInputBuffer.write(abyte0, i, j);
                mConsumedInputSizeBytes = mConsumedInputSizeBytes + (long)j;
            }
            return EmptyArray.BYTE;
        }

        private long mConsumedInputSizeBytes;
        private final KeyStoreCryptoOperationStreamer mDelegate;
        private final int mGroupSizeBits;
        private final ByteArrayOutputStream mInputBuffer;

        private NONE.TruncateToFieldSizeMessageStreamer(KeyStoreCryptoOperationStreamer keystorecryptooperationstreamer, int i)
        {
            mInputBuffer = new ByteArrayOutputStream();
            mDelegate = keystorecryptooperationstreamer;
            mGroupSizeBits = i;
        }

        NONE.TruncateToFieldSizeMessageStreamer(KeyStoreCryptoOperationStreamer keystorecryptooperationstreamer, int i, NONE.TruncateToFieldSizeMessageStreamer truncatetofieldsizemessagestreamer)
        {
            this(keystorecryptooperationstreamer, i);
        }
    }

    public static final class SHA1 extends AndroidKeyStoreECDSASignatureSpi
    {

        public SHA1()
        {
            super(2);
        }
    }

    public static final class SHA224 extends AndroidKeyStoreECDSASignatureSpi
    {

        public SHA224()
        {
            super(3);
        }
    }

    public static final class SHA256 extends AndroidKeyStoreECDSASignatureSpi
    {

        public SHA256()
        {
            super(4);
        }
    }

    public static final class SHA384 extends AndroidKeyStoreECDSASignatureSpi
    {

        public SHA384()
        {
            super(5);
        }
    }

    public static final class SHA512 extends AndroidKeyStoreECDSASignatureSpi
    {

        public SHA512()
        {
            super(6);
        }
    }


    AndroidKeyStoreECDSASignatureSpi(int i)
    {
        mGroupSizeBits = -1;
        mKeymasterDigest = i;
    }

    protected final void addAlgorithmSpecificParametersToBegin(KeymasterArguments keymasterarguments)
    {
        keymasterarguments.addEnum(0x10000002, 3);
        keymasterarguments.addEnum(0x20000005, mKeymasterDigest);
    }

    protected final int getAdditionalEntropyAmountForSign()
    {
        return (mGroupSizeBits + 7) / 8;
    }

    protected final int getGroupSizeBits()
    {
        if(mGroupSizeBits == -1)
            throw new IllegalStateException("Not initialized");
        else
            return mGroupSizeBits;
    }

    protected final void initKey(AndroidKeyStoreKey androidkeystorekey)
        throws InvalidKeyException
    {
        if(!"EC".equalsIgnoreCase(androidkeystorekey.getAlgorithm()))
            throw new InvalidKeyException((new StringBuilder()).append("Unsupported key algorithm: ").append(androidkeystorekey.getAlgorithm()).append(". Only").append("EC").append(" supported").toString());
        KeyCharacteristics keycharacteristics = new KeyCharacteristics();
        int i = getKeyStore().getKeyCharacteristics(androidkeystorekey.getAlias(), null, null, androidkeystorekey.getUid(), keycharacteristics);
        if(i != 1)
            throw getKeyStore().getInvalidKeyException(androidkeystorekey.getAlias(), androidkeystorekey.getUid(), i);
        long l = keycharacteristics.getUnsignedInt(0x30000003, -1L);
        if(l == -1L)
            throw new InvalidKeyException("Size of key not known");
        if(l > 0x7fffffffL)
        {
            throw new InvalidKeyException((new StringBuilder()).append("Key too large: ").append(l).append(" bits").toString());
        } else
        {
            mGroupSizeBits = (int)l;
            super.initKey(androidkeystorekey);
            return;
        }
    }

    protected final void resetAll()
    {
        mGroupSizeBits = -1;
        super.resetAll();
    }

    protected final void resetWhilePreservingInitState()
    {
        super.resetWhilePreservingInitState();
    }

    private int mGroupSizeBits;
    private final int mKeymasterDigest;
}

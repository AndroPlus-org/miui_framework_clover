// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.os.IBinder;
import android.security.KeyStore;
import android.security.KeyStoreException;
import android.security.keymaster.OperationResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.ProviderException;
import libcore.util.EmptyArray;

// Referenced classes of package android.security.keystore:
//            KeyStoreCryptoOperationStreamer, ArrayUtils, KeyStoreConnectException

class KeyStoreCryptoOperationChunkedStreamer
    implements KeyStoreCryptoOperationStreamer
{
    public static class MainDataStream
        implements Stream
    {

        public OperationResult finish(byte abyte0[], byte abyte1[])
        {
            return mKeyStore.finish(mOperationToken, null, abyte0, abyte1);
        }

        public OperationResult update(byte abyte0[])
        {
            return mKeyStore.update(mOperationToken, null, abyte0);
        }

        private final KeyStore mKeyStore;
        private final IBinder mOperationToken;

        public MainDataStream(KeyStore keystore, IBinder ibinder)
        {
            mKeyStore = keystore;
            mOperationToken = ibinder;
        }
    }

    static interface Stream
    {

        public abstract OperationResult finish(byte abyte0[], byte abyte1[]);

        public abstract OperationResult update(byte abyte0[]);
    }


    public KeyStoreCryptoOperationChunkedStreamer(Stream stream)
    {
        this(stream, 0x10000);
    }

    public KeyStoreCryptoOperationChunkedStreamer(Stream stream, int i)
    {
        mBuffered = EmptyArray.BYTE;
        mKeyStoreStream = stream;
        mMaxChunkSize = i;
    }

    public byte[] doFinal(byte abyte0[], int i, int j, byte abyte1[], byte abyte2[])
        throws KeyStoreException
    {
        if(j == 0)
        {
            abyte0 = EmptyArray.BYTE;
            i = 0;
        }
        abyte0 = ArrayUtils.concat(update(abyte0, i, j), flush());
        abyte1 = mKeyStoreStream.finish(abyte1, abyte2);
        if(abyte1 == null)
            throw new KeyStoreConnectException();
        if(((OperationResult) (abyte1)).resultCode != 1)
        {
            throw KeyStore.getKeyStoreException(((OperationResult) (abyte1)).resultCode);
        } else
        {
            mProducedOutputSizeBytes = mProducedOutputSizeBytes + (long)((OperationResult) (abyte1)).output.length;
            return ArrayUtils.concat(abyte0, ((OperationResult) (abyte1)).output);
        }
    }

    public byte[] flush()
        throws KeyStoreException
    {
        if(mBufferedLength <= 0)
            return EmptyArray.BYTE;
        Object obj = null;
label0:
        do
        {
label1:
            {
                byte abyte1[];
                OperationResult operationresult;
                if(mBufferedLength > 0)
                {
                    abyte1 = ArrayUtils.subarray(mBuffered, mBufferedOffset, mBufferedLength);
                    operationresult = mKeyStoreStream.update(abyte1);
                    if(operationresult == null)
                        throw new KeyStoreConnectException();
                    if(operationresult.resultCode != 1)
                        throw KeyStore.getKeyStoreException(operationresult.resultCode);
                    if(operationresult.inputConsumed > 0)
                        break label1;
                }
                if(mBufferedLength > 0)
                {
                    abyte1 = (new StringBuilder()).append("Keystore failed to consume last ");
                    Object obj1;
                    if(mBufferedLength != 1)
                        obj = (new StringBuilder()).append(mBufferedLength).append(" bytes").toString();
                    else
                        obj = "byte";
                    throw new KeyStoreException(-21, abyte1.append(((String) (obj))).append(" of input").toString());
                }
                break label0;
            }
            if(operationresult.inputConsumed >= abyte1.length)
            {
                mBuffered = EmptyArray.BYTE;
                mBufferedOffset = 0;
                mBufferedLength = 0;
            } else
            {
                mBuffered = abyte1;
                mBufferedOffset = operationresult.inputConsumed;
                mBufferedLength = abyte1.length - operationresult.inputConsumed;
            }
            if(operationresult.inputConsumed > abyte1.length)
                throw new KeyStoreException(-1000, (new StringBuilder()).append("Keystore consumed more input than provided. Provided: ").append(abyte1.length).append(", consumed: ").append(operationresult.inputConsumed).toString());
            if(operationresult.output != null && operationresult.output.length > 0)
            {
                obj1 = obj;
                if(obj == null)
                {
                    if(mBufferedLength == 0)
                    {
                        mProducedOutputSizeBytes = mProducedOutputSizeBytes + (long)operationresult.output.length;
                        return operationresult.output;
                    }
                    obj1 = new ByteArrayOutputStream();
                }
                try
                {
                    ((ByteArrayOutputStream) (obj1)).write(operationresult.output);
                }
                // Misplaced declaration of an exception variable
                catch(Object obj)
                {
                    throw new ProviderException("Failed to buffer output", ((Throwable) (obj)));
                }
                obj = obj1;
            }
        } while(true);
        byte abyte0[];
        if(obj != null)
            abyte0 = ((ByteArrayOutputStream) (obj)).toByteArray();
        else
            abyte0 = EmptyArray.BYTE;
        mProducedOutputSizeBytes = mProducedOutputSizeBytes + (long)abyte0.length;
        return abyte0;
    }

    public long getConsumedInputSizeBytes()
    {
        return mConsumedInputSizeBytes;
    }

    public long getProducedOutputSizeBytes()
    {
        return mProducedOutputSizeBytes;
    }

    public byte[] update(byte abyte0[], int i, int j)
        throws KeyStoreException
    {
        if(j == 0)
            return EmptyArray.BYTE;
        ByteArrayOutputStream bytearrayoutputstream = null;
        int k = i;
        while(j > 0) 
        {
            byte abyte1[];
            int l;
            int i1;
            OperationResult operationresult;
            if(mBufferedLength + j > mMaxChunkSize)
            {
                i = mMaxChunkSize - mBufferedLength;
                abyte1 = ArrayUtils.concat(mBuffered, mBufferedOffset, mBufferedLength, abyte0, k, i);
            } else
            if(mBufferedLength == 0 && k == 0 && j == abyte0.length)
            {
                abyte1 = abyte0;
                i = abyte0.length;
            } else
            {
                i = j;
                abyte1 = ArrayUtils.concat(mBuffered, mBufferedOffset, mBufferedLength, abyte0, k, i);
            }
            l = k + i;
            i1 = j - i;
            mConsumedInputSizeBytes = mConsumedInputSizeBytes + (long)i;
            operationresult = mKeyStoreStream.update(abyte1);
            if(operationresult == null)
                throw new KeyStoreConnectException();
            if(operationresult.resultCode != 1)
                throw KeyStore.getKeyStoreException(operationresult.resultCode);
            if(operationresult.inputConsumed == abyte1.length)
            {
                mBuffered = EmptyArray.BYTE;
                mBufferedOffset = 0;
                mBufferedLength = 0;
            } else
            if(operationresult.inputConsumed <= 0)
            {
                if(i1 > 0)
                    throw new KeyStoreException(-1000, (new StringBuilder()).append("Keystore consumed nothing from max-sized chunk: ").append(abyte1.length).append(" bytes").toString());
                mBuffered = abyte1;
                mBufferedOffset = 0;
                mBufferedLength = abyte1.length;
            } else
            if(operationresult.inputConsumed < abyte1.length)
            {
                mBuffered = abyte1;
                mBufferedOffset = operationresult.inputConsumed;
                mBufferedLength = abyte1.length - operationresult.inputConsumed;
            } else
            {
                throw new KeyStoreException(-1000, (new StringBuilder()).append("Keystore consumed more input than provided. Provided: ").append(abyte1.length).append(", consumed: ").append(operationresult.inputConsumed).toString());
            }
            k = l;
            j = i1;
            if(operationresult.output != null)
            {
                k = l;
                j = i1;
                if(operationresult.output.length > 0)
                    if(i1 > 0)
                    {
                        k = l;
                        j = i1;
                        if(bytearrayoutputstream == null)
                        {
                            bytearrayoutputstream = new ByteArrayOutputStream();
                            try
                            {
                                bytearrayoutputstream.write(operationresult.output);
                            }
                            // Misplaced declaration of an exception variable
                            catch(byte abyte0[])
                            {
                                throw new ProviderException("Failed to buffer output", abyte0);
                            }
                            k = l;
                            j = i1;
                        }
                    } else
                    {
                        if(bytearrayoutputstream == null)
                        {
                            abyte0 = operationresult.output;
                        } else
                        {
                            try
                            {
                                bytearrayoutputstream.write(operationresult.output);
                            }
                            // Misplaced declaration of an exception variable
                            catch(byte abyte0[])
                            {
                                throw new ProviderException("Failed to buffer output", abyte0);
                            }
                            abyte0 = bytearrayoutputstream.toByteArray();
                        }
                        mProducedOutputSizeBytes = mProducedOutputSizeBytes + (long)abyte0.length;
                        return abyte0;
                    }
            }
        }
        if(bytearrayoutputstream == null)
            abyte0 = EmptyArray.BYTE;
        else
            abyte0 = bytearrayoutputstream.toByteArray();
        mProducedOutputSizeBytes = mProducedOutputSizeBytes + (long)abyte0.length;
        return abyte0;
    }

    private static final int DEFAULT_MAX_CHUNK_SIZE = 0x10000;
    private byte mBuffered[];
    private int mBufferedLength;
    private int mBufferedOffset;
    private long mConsumedInputSizeBytes;
    private final Stream mKeyStoreStream;
    private final int mMaxChunkSize;
    private long mProducedOutputSizeBytes;
}

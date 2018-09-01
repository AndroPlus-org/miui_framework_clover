// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.os.IBinder;
import android.security.KeyStore;
import android.security.KeyStoreException;
import android.security.keymaster.KeymasterArguments;
import android.security.keymaster.OperationResult;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.MacSpi;

// Referenced classes of package android.security.keystore:
//            KeyStoreCryptoOperation, KeymasterUtils, AndroidKeyStoreSecretKey, KeyStoreConnectException, 
//            KeyStoreCryptoOperationUtils, KeyStoreCryptoOperationChunkedStreamer

public abstract class AndroidKeyStoreHmacSpi extends MacSpi
    implements KeyStoreCryptoOperation
{
    public static class HmacSHA1 extends AndroidKeyStoreHmacSpi
    {

        public HmacSHA1()
        {
            super(2);
        }
    }

    public static class HmacSHA224 extends AndroidKeyStoreHmacSpi
    {

        public HmacSHA224()
        {
            super(3);
        }
    }

    public static class HmacSHA256 extends AndroidKeyStoreHmacSpi
    {

        public HmacSHA256()
        {
            super(4);
        }
    }

    public static class HmacSHA384 extends AndroidKeyStoreHmacSpi
    {

        public HmacSHA384()
        {
            super(5);
        }
    }

    public static class HmacSHA512 extends AndroidKeyStoreHmacSpi
    {

        public HmacSHA512()
        {
            super(6);
        }
    }


    protected AndroidKeyStoreHmacSpi(int i)
    {
        mKeymasterDigest = i;
        mMacSizeBits = KeymasterUtils.getDigestOutputSizeBits(i);
    }

    private void ensureKeystoreOperationInitialized()
        throws InvalidKeyException
    {
        if(mChunkedStreamer != null)
            return;
        if(mKey == null)
            throw new IllegalStateException("Not initialized");
        Object obj = new KeymasterArguments();
        ((KeymasterArguments) (obj)).addEnum(0x10000002, 128);
        ((KeymasterArguments) (obj)).addEnum(0x20000005, mKeymasterDigest);
        ((KeymasterArguments) (obj)).addUnsignedInt(0x300003eb, mMacSizeBits);
        obj = mKeyStore.begin(mKey.getAlias(), 2, true, ((KeymasterArguments) (obj)), null, mKey.getUid());
        if(obj == null)
            throw new KeyStoreConnectException();
        mOperationToken = ((OperationResult) (obj)).token;
        mOperationHandle = ((OperationResult) (obj)).operationHandle;
        obj = KeyStoreCryptoOperationUtils.getInvalidKeyExceptionForInit(mKeyStore, mKey, ((OperationResult) (obj)).resultCode);
        if(obj != null)
            throw obj;
        if(mOperationToken == null)
            throw new ProviderException("Keystore returned null operation token");
        if(mOperationHandle == 0L)
        {
            throw new ProviderException("Keystore returned invalid operation handle");
        } else
        {
            mChunkedStreamer = new KeyStoreCryptoOperationChunkedStreamer(new KeyStoreCryptoOperationChunkedStreamer.MainDataStream(mKeyStore, mOperationToken));
            return;
        }
    }

    private void init(Key key, AlgorithmParameterSpec algorithmparameterspec)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        if(key == null)
            throw new InvalidKeyException("key == null");
        if(!(key instanceof AndroidKeyStoreSecretKey))
            throw new InvalidKeyException((new StringBuilder()).append("Only Android KeyStore secret keys supported. Key: ").append(key).toString());
        mKey = (AndroidKeyStoreSecretKey)key;
        if(algorithmparameterspec != null)
            throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unsupported algorithm parameters: ").append(algorithmparameterspec).toString());
        else
            return;
    }

    private void resetAll()
    {
        mKey = null;
        IBinder ibinder = mOperationToken;
        if(ibinder != null)
            mKeyStore.abort(ibinder);
        mOperationToken = null;
        mOperationHandle = 0L;
        mChunkedStreamer = null;
    }

    private void resetWhilePreservingInitState()
    {
        IBinder ibinder = mOperationToken;
        if(ibinder != null)
            mKeyStore.abort(ibinder);
        mOperationToken = null;
        mOperationHandle = 0L;
        mChunkedStreamer = null;
    }

    protected byte[] engineDoFinal()
    {
        byte abyte0[];
        try
        {
            ensureKeystoreOperationInitialized();
        }
        catch(InvalidKeyException invalidkeyexception)
        {
            throw new ProviderException("Failed to reinitialize MAC", invalidkeyexception);
        }
        try
        {
            abyte0 = mChunkedStreamer.doFinal(null, 0, 0, null, null);
        }
        catch(KeyStoreException keystoreexception)
        {
            throw new ProviderException("Keystore operation failed", keystoreexception);
        }
        resetWhilePreservingInitState();
        return abyte0;
    }

    protected int engineGetMacLength()
    {
        return (mMacSizeBits + 7) / 8;
    }

    protected void engineInit(Key key, AlgorithmParameterSpec algorithmparameterspec)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        resetAll();
        init(key, algorithmparameterspec);
        ensureKeystoreOperationInitialized();
        if(false)
            resetAll();
        return;
        key;
        if(true)
            resetAll();
        throw key;
    }

    protected void engineReset()
    {
        resetWhilePreservingInitState();
    }

    protected void engineUpdate(byte byte0)
    {
        engineUpdate(new byte[] {
            byte0
        }, 0, 1);
    }

    protected void engineUpdate(byte abyte0[], int i, int j)
    {
        try
        {
            ensureKeystoreOperationInitialized();
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw new ProviderException("Failed to reinitialize MAC", abyte0);
        }
        try
        {
            abyte0 = mChunkedStreamer.update(abyte0, i, j);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw new ProviderException("Keystore operation failed", abyte0);
        }
        if(abyte0 != null && abyte0.length != 0)
            throw new ProviderException("Update operation unexpectedly produced output");
        else
            return;
    }

    public void finalize()
        throws Throwable
    {
        IBinder ibinder = mOperationToken;
        if(ibinder == null)
            break MISSING_BLOCK_LABEL_18;
        mKeyStore.abort(ibinder);
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public long getOperationHandle()
    {
        return mOperationHandle;
    }

    private KeyStoreCryptoOperationChunkedStreamer mChunkedStreamer;
    private AndroidKeyStoreSecretKey mKey;
    private final KeyStore mKeyStore = KeyStore.getInstance();
    private final int mKeymasterDigest;
    private final int mMacSizeBits;
    private long mOperationHandle;
    private IBinder mOperationToken;
}

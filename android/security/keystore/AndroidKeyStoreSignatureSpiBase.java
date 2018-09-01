// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.os.IBinder;
import android.security.KeyStore;
import android.security.KeyStoreException;
import android.security.keymaster.KeymasterArguments;
import android.security.keymaster.OperationResult;
import java.nio.ByteBuffer;
import java.security.*;
import libcore.util.EmptyArray;

// Referenced classes of package android.security.keystore:
//            KeyStoreCryptoOperation, AndroidKeyStoreKey, KeyStoreConnectException, KeyStoreCryptoOperationUtils, 
//            KeyStoreCryptoOperationChunkedStreamer, AndroidKeyStorePrivateKey, AndroidKeyStorePublicKey, KeyStoreCryptoOperationStreamer, 
//            ArrayUtils

abstract class AndroidKeyStoreSignatureSpiBase extends SignatureSpi
    implements KeyStoreCryptoOperation
{

    AndroidKeyStoreSignatureSpiBase()
    {
    }

    private void ensureKeystoreOperationInitialized()
        throws InvalidKeyException
    {
        if(mMessageStreamer != null)
            return;
        if(mCachedException != null)
            return;
        if(mKey == null)
            throw new IllegalStateException("Not initialized");
        Object obj = new KeymasterArguments();
        addAlgorithmSpecificParametersToBegin(((KeymasterArguments) (obj)));
        Object obj1 = mKeyStore;
        String s = mKey.getAlias();
        byte byte0;
        if(mSigning)
            byte0 = 2;
        else
            byte0 = 3;
        obj1 = ((KeyStore) (obj1)).begin(s, byte0, true, ((KeymasterArguments) (obj)), null, mKey.getUid());
        if(obj1 == null)
            throw new KeyStoreConnectException();
        mOperationToken = ((OperationResult) (obj1)).token;
        mOperationHandle = ((OperationResult) (obj1)).operationHandle;
        obj = KeyStoreCryptoOperationUtils.getInvalidKeyExceptionForInit(mKeyStore, mKey, ((OperationResult) (obj1)).resultCode);
        if(obj != null)
            throw obj;
        if(mOperationToken == null)
            throw new ProviderException("Keystore returned null operation token");
        if(mOperationHandle == 0L)
        {
            throw new ProviderException("Keystore returned invalid operation handle");
        } else
        {
            mMessageStreamer = createMainDataStreamer(mKeyStore, ((OperationResult) (obj1)).token);
            return;
        }
    }

    protected abstract void addAlgorithmSpecificParametersToBegin(KeymasterArguments keymasterarguments);

    protected KeyStoreCryptoOperationStreamer createMainDataStreamer(KeyStore keystore, IBinder ibinder)
    {
        return new KeyStoreCryptoOperationChunkedStreamer(new KeyStoreCryptoOperationChunkedStreamer.MainDataStream(keystore, ibinder));
    }

    protected final Object engineGetParameter(String s)
        throws InvalidParameterException
    {
        throw new InvalidParameterException();
    }

    protected final void engineInitSign(PrivateKey privatekey)
        throws InvalidKeyException
    {
        engineInitSign(privatekey, null);
    }

    protected final void engineInitSign(PrivateKey privatekey, SecureRandom securerandom)
        throws InvalidKeyException
    {
        resetAll();
        if(privatekey != null)
            break MISSING_BLOCK_LABEL_31;
        privatekey = JVM INSTR new #36  <Class InvalidKeyException>;
        privatekey.InvalidKeyException("Unsupported key: null");
        throw privatekey;
        privatekey;
        if(true)
            resetAll();
        throw privatekey;
        if(!(privatekey instanceof AndroidKeyStorePrivateKey))
            break MISSING_BLOCK_LABEL_71;
        privatekey = (AndroidKeyStoreKey)privatekey;
        mSigning = true;
        initKey(privatekey);
        appRandom = securerandom;
        ensureKeystoreOperationInitialized();
        if(false)
            resetAll();
        return;
        InvalidKeyException invalidkeyexception = JVM INSTR new #36  <Class InvalidKeyException>;
        securerandom = JVM INSTR new #151 <Class StringBuilder>;
        securerandom.StringBuilder();
        invalidkeyexception.InvalidKeyException(securerandom.append("Unsupported private key type: ").append(privatekey).toString());
        throw invalidkeyexception;
    }

    protected final void engineInitVerify(PublicKey publickey)
        throws InvalidKeyException
    {
        resetAll();
        if(publickey != null)
            break MISSING_BLOCK_LABEL_31;
        publickey = JVM INSTR new #36  <Class InvalidKeyException>;
        publickey.InvalidKeyException("Unsupported key: null");
        throw publickey;
        publickey;
        if(true)
            resetAll();
        throw publickey;
        if(!(publickey instanceof AndroidKeyStorePublicKey))
            break MISSING_BLOCK_LABEL_71;
        publickey = (AndroidKeyStorePublicKey)publickey;
        mSigning = false;
        initKey(publickey);
        appRandom = null;
        ensureKeystoreOperationInitialized();
        if(false)
            resetAll();
        return;
        InvalidKeyException invalidkeyexception = JVM INSTR new #36  <Class InvalidKeyException>;
        StringBuilder stringbuilder = JVM INSTR new #151 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        invalidkeyexception.InvalidKeyException(stringbuilder.append("Unsupported public key type: ").append(publickey).toString());
        throw invalidkeyexception;
    }

    protected final void engineSetParameter(String s, Object obj)
        throws InvalidParameterException
    {
        throw new InvalidParameterException();
    }

    protected final int engineSign(byte abyte0[], int i, int j)
        throws SignatureException
    {
        return super.engineSign(abyte0, i, j);
    }

    protected final byte[] engineSign()
        throws SignatureException
    {
        if(mCachedException != null)
            throw new SignatureException(mCachedException);
        byte abyte0[];
        try
        {
            ensureKeystoreOperationInitialized();
            abyte0 = KeyStoreCryptoOperationUtils.getRandomBytesToMixIntoKeystoreRng(appRandom, getAdditionalEntropyAmountForSign());
            abyte0 = mMessageStreamer.doFinal(EmptyArray.BYTE, 0, 0, null, abyte0);
        }
        catch(Object obj)
        {
            throw new SignatureException(((Throwable) (obj)));
        }
        resetWhilePreservingInitState();
        return abyte0;
    }

    protected final void engineUpdate(byte byte0)
        throws SignatureException
    {
        engineUpdate(new byte[] {
            byte0
        }, 0, 1);
    }

    protected final void engineUpdate(ByteBuffer bytebuffer)
    {
        int i = bytebuffer.remaining();
        int j;
        if(bytebuffer.hasArray())
        {
            byte abyte0[] = bytebuffer.array();
            j = bytebuffer.arrayOffset() + bytebuffer.position();
            bytebuffer.position(bytebuffer.limit());
            bytebuffer = abyte0;
        } else
        {
            byte abyte1[] = new byte[i];
            j = 0;
            bytebuffer.get(abyte1);
            bytebuffer = abyte1;
        }
        engineUpdate(((byte []) (bytebuffer)), j, i);
_L1:
        return;
        bytebuffer;
        mCachedException = bytebuffer;
          goto _L1
    }

    protected final void engineUpdate(byte abyte0[], int i, int j)
        throws SignatureException
    {
        if(mCachedException != null)
            throw new SignatureException(mCachedException);
        try
        {
            ensureKeystoreOperationInitialized();
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw new SignatureException(abyte0);
        }
        if(j == 0)
            return;
        try
        {
            abyte0 = mMessageStreamer.update(abyte0, i, j);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw new SignatureException(abyte0);
        }
        if(abyte0.length != 0)
            throw new ProviderException((new StringBuilder()).append("Update operation unexpectedly produced output: ").append(abyte0.length).append(" bytes").toString());
        else
            return;
    }

    protected final boolean engineVerify(byte abyte0[])
        throws SignatureException
    {
        if(mCachedException != null)
            throw new SignatureException(mCachedException);
        ProviderException providerexception;
        StringBuilder stringbuilder;
        try
        {
            ensureKeystoreOperationInitialized();
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw new SignatureException(abyte0);
        }
        abyte0 = mMessageStreamer.doFinal(EmptyArray.BYTE, 0, 0, abyte0, null);
        if(abyte0.length != 0)
        {
            providerexception = JVM INSTR new #99  <Class ProviderException>;
            stringbuilder = JVM INSTR new #151 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            providerexception.ProviderException(stringbuilder.append("Signature verification unexpected produced output: ").append(abyte0.length).append(" bytes").toString());
            throw providerexception;
        }
          goto _L1
        abyte0;
        boolean flag;
        switch(abyte0.getErrorCode())
        {
        default:
            throw new SignatureException(abyte0);

        case -30: 
            flag = false;
            break;
        }
          goto _L2
_L1:
        flag = true;
_L4:
        resetWhilePreservingInitState();
        return flag;
_L2:
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected final boolean engineVerify(byte abyte0[], int i, int j)
        throws SignatureException
    {
        return engineVerify(ArrayUtils.subarray(abyte0, i, j));
    }

    protected abstract int getAdditionalEntropyAmountForSign();

    protected final KeyStore getKeyStore()
    {
        return mKeyStore;
    }

    public final long getOperationHandle()
    {
        return mOperationHandle;
    }

    protected void initKey(AndroidKeyStoreKey androidkeystorekey)
        throws InvalidKeyException
    {
        mKey = androidkeystorekey;
    }

    protected final boolean isSigning()
    {
        return mSigning;
    }

    protected void resetAll()
    {
        IBinder ibinder = mOperationToken;
        if(ibinder != null)
        {
            mOperationToken = null;
            mKeyStore.abort(ibinder);
        }
        mSigning = false;
        mKey = null;
        appRandom = null;
        mOperationToken = null;
        mOperationHandle = 0L;
        mMessageStreamer = null;
        mCachedException = null;
    }

    protected void resetWhilePreservingInitState()
    {
        IBinder ibinder = mOperationToken;
        if(ibinder != null)
        {
            mOperationToken = null;
            mKeyStore.abort(ibinder);
        }
        mOperationHandle = 0L;
        mMessageStreamer = null;
        mCachedException = null;
    }

    private Exception mCachedException;
    private AndroidKeyStoreKey mKey;
    private final KeyStore mKeyStore = KeyStore.getInstance();
    private KeyStoreCryptoOperationStreamer mMessageStreamer;
    private long mOperationHandle;
    private IBinder mOperationToken;
    private boolean mSigning;
}

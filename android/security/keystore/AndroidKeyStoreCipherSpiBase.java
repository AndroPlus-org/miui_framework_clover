// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.os.IBinder;
import android.security.KeyStore;
import android.security.KeyStoreException;
import android.security.keymaster.KeymasterArguments;
import android.security.keymaster.OperationResult;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import libcore.util.EmptyArray;

// Referenced classes of package android.security.keystore:
//            KeyStoreCryptoOperation, KeyStoreCryptoOperationUtils, AndroidKeyStoreKey, KeyStoreConnectException, 
//            KeyStoreCryptoOperationStreamer, KeyStoreCryptoOperationChunkedStreamer

abstract class AndroidKeyStoreCipherSpiBase extends CipherSpi
    implements KeyStoreCryptoOperation
{

    AndroidKeyStoreCipherSpiBase()
    {
        mKeymasterPurposeOverride = -1;
    }

    private void ensureKeystoreOperationInitialized()
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        if(mMainDataStreamer != null)
            return;
        if(mCachedException != null)
            return;
        if(mKey == null)
            throw new IllegalStateException("Not initialized");
        Object obj = new KeymasterArguments();
        addAlgorithmSpecificParametersToBegin(((KeymasterArguments) (obj)));
        byte abyte0[] = KeyStoreCryptoOperationUtils.getRandomBytesToMixIntoKeystoreRng(mRng, getAdditionalEntropyAmountForBegin());
        int i;
        if(mKeymasterPurposeOverride != -1)
            i = mKeymasterPurposeOverride;
        else
        if(mEncrypting)
            i = 0;
        else
            i = 1;
        obj = mKeyStore.begin(mKey.getAlias(), i, true, ((KeymasterArguments) (obj)), abyte0, mKey.getUid());
        if(obj == null)
            throw new KeyStoreConnectException();
        mOperationToken = ((OperationResult) (obj)).token;
        mOperationHandle = ((OperationResult) (obj)).operationHandle;
        java.security.GeneralSecurityException generalsecurityexception = KeyStoreCryptoOperationUtils.getExceptionForCipherInit(mKeyStore, mKey, ((OperationResult) (obj)).resultCode);
        if(generalsecurityexception != null)
        {
            if(generalsecurityexception instanceof InvalidKeyException)
                throw (InvalidKeyException)generalsecurityexception;
            if(generalsecurityexception instanceof InvalidAlgorithmParameterException)
                throw (InvalidAlgorithmParameterException)generalsecurityexception;
            else
                throw new ProviderException("Unexpected exception type", generalsecurityexception);
        }
        if(mOperationToken == null)
            throw new ProviderException("Keystore returned null operation token");
        if(mOperationHandle == 0L)
        {
            throw new ProviderException("Keystore returned invalid operation handle");
        } else
        {
            loadAlgorithmSpecificParametersFromBeginResult(((OperationResult) (obj)).outParams);
            mMainDataStreamer = createMainDataStreamer(mKeyStore, ((OperationResult) (obj)).token);
            mAdditionalAuthenticationDataStreamer = createAdditionalAuthenticationDataStreamer(mKeyStore, ((OperationResult) (obj)).token);
            mAdditionalAuthenticationDataStreamerClosed = false;
            return;
        }
    }

    private void flushAAD()
        throws KeyStoreException
    {
        if(mAdditionalAuthenticationDataStreamer == null || !(mAdditionalAuthenticationDataStreamerClosed ^ true))
            break MISSING_BLOCK_LABEL_88;
        byte abyte0[] = mAdditionalAuthenticationDataStreamer.doFinal(EmptyArray.BYTE, 0, 0, null, null);
        mAdditionalAuthenticationDataStreamerClosed = true;
        if(abyte0 != null && abyte0.length > 0)
            throw new ProviderException((new StringBuilder()).append("AAD update unexpectedly returned data: ").append(abyte0.length).append(" bytes").toString());
        break MISSING_BLOCK_LABEL_88;
        Exception exception;
        exception;
        mAdditionalAuthenticationDataStreamerClosed = true;
        throw exception;
    }

    private void init(int i, Key key, SecureRandom securerandom)
        throws InvalidKeyException
    {
        switch(i)
        {
        default:
            throw new InvalidParameterException((new StringBuilder()).append("Unsupported opmode: ").append(i).toString());

        case 2: // '\002'
        case 4: // '\004'
            break MISSING_BLOCK_LABEL_87;

        case 1: // '\001'
        case 3: // '\003'
            mEncrypting = true;
            break;
        }
_L1:
        initKey(i, key);
        if(mKey == null)
        {
            throw new ProviderException("initKey did not initialize the key");
        } else
        {
            mRng = securerandom;
            return;
        }
        mEncrypting = false;
          goto _L1
    }

    static String opmodeToString(int i)
    {
        switch(i)
        {
        default:
            return String.valueOf(i);

        case 1: // '\001'
            return "ENCRYPT_MODE";

        case 2: // '\002'
            return "DECRYPT_MODE";

        case 3: // '\003'
            return "WRAP_MODE";

        case 4: // '\004'
            return "UNWRAP_MODE";
        }
    }

    protected abstract void addAlgorithmSpecificParametersToBegin(KeymasterArguments keymasterarguments);

    protected KeyStoreCryptoOperationStreamer createAdditionalAuthenticationDataStreamer(KeyStore keystore, IBinder ibinder)
    {
        return null;
    }

    protected KeyStoreCryptoOperationStreamer createMainDataStreamer(KeyStore keystore, IBinder ibinder)
    {
        return new KeyStoreCryptoOperationChunkedStreamer(new KeyStoreCryptoOperationChunkedStreamer.MainDataStream(keystore, ibinder));
    }

    protected final int engineDoFinal(ByteBuffer bytebuffer, ByteBuffer bytebuffer1)
        throws ShortBufferException, IllegalBlockSizeException, BadPaddingException
    {
        if(bytebuffer == null)
            throw new NullPointerException("input == null");
        if(bytebuffer1 == null)
            throw new NullPointerException("output == null");
        int i = bytebuffer.remaining();
        if(bytebuffer.hasArray())
        {
            byte abyte0[] = engineDoFinal(bytebuffer.array(), bytebuffer.arrayOffset() + bytebuffer.position(), i);
            bytebuffer.position(bytebuffer.position() + i);
            bytebuffer = abyte0;
        } else
        {
            byte abyte1[] = new byte[i];
            bytebuffer.get(abyte1);
            bytebuffer = engineDoFinal(abyte1, 0, i);
        }
        if(bytebuffer != null)
            i = bytebuffer.length;
        else
            i = 0;
        if(i > 0)
        {
            int j = bytebuffer1.remaining();
            try
            {
                bytebuffer1.put(bytebuffer);
            }
            // Misplaced declaration of an exception variable
            catch(ByteBuffer bytebuffer)
            {
                throw new ShortBufferException((new StringBuilder()).append("Output buffer too small. Produced: ").append(i).append(", available: ").append(j).toString());
            }
        }
        return i;
    }

    protected final int engineDoFinal(byte abyte0[], int i, int j, byte abyte1[], int k)
        throws ShortBufferException, IllegalBlockSizeException, BadPaddingException
    {
        abyte0 = engineDoFinal(abyte0, i, j);
        if(abyte0 == null)
            return 0;
        i = abyte1.length - k;
        if(abyte0.length > i)
        {
            throw new ShortBufferException((new StringBuilder()).append("Output buffer too short. Produced: ").append(abyte0.length).append(", available: ").append(i).toString());
        } else
        {
            System.arraycopy(abyte0, 0, abyte1, k, abyte0.length);
            return abyte0.length;
        }
    }

    protected final byte[] engineDoFinal(byte abyte0[], int i, int j)
        throws IllegalBlockSizeException, BadPaddingException
    {
        if(mCachedException != null)
            throw (IllegalBlockSizeException)(new IllegalBlockSizeException()).initCause(mCachedException);
        try
        {
            ensureKeystoreOperationInitialized();
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw (IllegalBlockSizeException)(new IllegalBlockSizeException()).initCause(abyte0);
        }
        try
        {
            flushAAD();
            byte abyte1[] = KeyStoreCryptoOperationUtils.getRandomBytesToMixIntoKeystoreRng(mRng, getAdditionalEntropyAmountForFinish());
            abyte0 = mMainDataStreamer.doFinal(abyte0, i, j, null, abyte1);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            switch(abyte0.getErrorCode())
            {
            default:
                throw (IllegalBlockSizeException)(new IllegalBlockSizeException()).initCause(abyte0);

            case -21: 
                throw (IllegalBlockSizeException)(new IllegalBlockSizeException()).initCause(abyte0);

            case -38: 
                throw (BadPaddingException)(new BadPaddingException()).initCause(abyte0);

            case -30: 
                throw (AEADBadTagException)(new AEADBadTagException()).initCause(abyte0);
            }
        }
        resetWhilePreservingInitState();
        return abyte0;
    }

    protected final int engineGetKeySize(Key key)
        throws InvalidKeyException
    {
        throw new UnsupportedOperationException();
    }

    protected abstract AlgorithmParameters engineGetParameters();

    protected final void engineInit(int i, Key key, AlgorithmParameters algorithmparameters, SecureRandom securerandom)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        resetAll();
        init(i, key, securerandom);
        initAlgorithmSpecificParameters(algorithmparameters);
        ensureKeystoreOperationInitialized();
        if(false)
            resetAll();
        return;
        key;
        if(true)
            resetAll();
        throw key;
    }

    protected final void engineInit(int i, Key key, SecureRandom securerandom)
        throws InvalidKeyException
    {
        resetAll();
        init(i, key, securerandom);
        initAlgorithmSpecificParameters();
        ensureKeystoreOperationInitialized();
        if(false)
            resetAll();
        return;
        securerandom;
        key = JVM INSTR new #44  <Class InvalidKeyException>;
        key.InvalidKeyException(securerandom);
        throw key;
        key;
        if(true)
            resetAll();
        throw key;
    }

    protected final void engineInit(int i, Key key, AlgorithmParameterSpec algorithmparameterspec, SecureRandom securerandom)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        resetAll();
        init(i, key, securerandom);
        initAlgorithmSpecificParameters(algorithmparameterspec);
        ensureKeystoreOperationInitialized();
        if(false)
            resetAll();
        return;
        key;
        if(true)
            resetAll();
        throw key;
    }

    protected final void engineSetMode(String s)
        throws NoSuchAlgorithmException
    {
        throw new UnsupportedOperationException();
    }

    protected final void engineSetPadding(String s)
        throws NoSuchPaddingException
    {
        throw new UnsupportedOperationException();
    }

    protected final Key engineUnwrap(byte abyte0[], String s, int i)
        throws InvalidKeyException, NoSuchAlgorithmException
    {
        if(mKey == null)
            throw new IllegalStateException("Not initilized");
        if(isEncrypting())
            throw new IllegalStateException("Cipher must be initialized in Cipher.WRAP_MODE to wrap keys");
        if(abyte0 == null)
            throw new NullPointerException("wrappedKey == null");
        try
        {
            abyte0 = engineDoFinal(abyte0, 0, abyte0.length);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw new InvalidKeyException("Failed to unwrap key", abyte0);
        }
        KeyFactory keyfactory;
        switch(i)
        {
        default:
            throw new InvalidParameterException((new StringBuilder()).append("Unsupported wrappedKeyType: ").append(i).toString());

        case 3: // '\003'
            return new SecretKeySpec(abyte0, s);

        case 2: // '\002'
            s = KeyFactory.getInstance(s);
            try
            {
                PKCS8EncodedKeySpec pkcs8encodedkeyspec = JVM INSTR new #366 <Class PKCS8EncodedKeySpec>;
                pkcs8encodedkeyspec.PKCS8EncodedKeySpec(abyte0);
                abyte0 = s.generatePrivate(pkcs8encodedkeyspec);
            }
            // Misplaced declaration of an exception variable
            catch(byte abyte0[])
            {
                throw new InvalidKeyException("Failed to create private key from its PKCS#8 encoded form", abyte0);
            }
            return abyte0;

        case 1: // '\001'
            keyfactory = KeyFactory.getInstance(s);
            break;
        }
        try
        {
            s = JVM INSTR new #377 <Class X509EncodedKeySpec>;
            s.X509EncodedKeySpec(abyte0);
            abyte0 = keyfactory.generatePublic(s);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw new InvalidKeyException("Failed to create public key from its X.509 encoded form", abyte0);
        }
        return abyte0;
    }

    protected final int engineUpdate(ByteBuffer bytebuffer, ByteBuffer bytebuffer1)
        throws ShortBufferException
    {
        if(bytebuffer == null)
            throw new NullPointerException("input == null");
        if(bytebuffer1 == null)
            throw new NullPointerException("output == null");
        int i = bytebuffer.remaining();
        if(bytebuffer.hasArray())
        {
            byte abyte0[] = engineUpdate(bytebuffer.array(), bytebuffer.arrayOffset() + bytebuffer.position(), i);
            bytebuffer.position(bytebuffer.position() + i);
            bytebuffer = abyte0;
        } else
        {
            byte abyte1[] = new byte[i];
            bytebuffer.get(abyte1);
            bytebuffer = engineUpdate(abyte1, 0, i);
        }
        if(bytebuffer != null)
            i = bytebuffer.length;
        else
            i = 0;
        if(i > 0)
        {
            int j = bytebuffer1.remaining();
            try
            {
                bytebuffer1.put(bytebuffer);
            }
            // Misplaced declaration of an exception variable
            catch(ByteBuffer bytebuffer)
            {
                throw new ShortBufferException((new StringBuilder()).append("Output buffer too small. Produced: ").append(i).append(", available: ").append(j).toString());
            }
        }
        return i;
    }

    protected final int engineUpdate(byte abyte0[], int i, int j, byte abyte1[], int k)
        throws ShortBufferException
    {
        abyte0 = engineUpdate(abyte0, i, j);
        if(abyte0 == null)
            return 0;
        i = abyte1.length - k;
        if(abyte0.length > i)
        {
            throw new ShortBufferException((new StringBuilder()).append("Output buffer too short. Produced: ").append(abyte0.length).append(", available: ").append(i).toString());
        } else
        {
            System.arraycopy(abyte0, 0, abyte1, k, abyte0.length);
            return abyte0.length;
        }
    }

    protected final byte[] engineUpdate(byte abyte0[], int i, int j)
    {
        if(mCachedException != null)
            return null;
        try
        {
            ensureKeystoreOperationInitialized();
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            mCachedException = abyte0;
            return null;
        }
        if(j == 0)
            return null;
        try
        {
            flushAAD();
            abyte0 = mMainDataStreamer.update(abyte0, i, j);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            mCachedException = abyte0;
            return null;
        }
        if(abyte0.length == 0)
            return null;
        else
            return abyte0;
    }

    protected final void engineUpdateAAD(ByteBuffer bytebuffer)
    {
        if(bytebuffer == null)
            throw new IllegalArgumentException("src == null");
        if(!bytebuffer.hasRemaining())
            return;
        int i;
        int j;
        if(bytebuffer.hasArray())
        {
            byte abyte0[] = bytebuffer.array();
            i = bytebuffer.arrayOffset() + bytebuffer.position();
            j = bytebuffer.remaining();
            bytebuffer.position(bytebuffer.limit());
            bytebuffer = abyte0;
        } else
        {
            byte abyte1[] = new byte[bytebuffer.remaining()];
            i = 0;
            j = abyte1.length;
            bytebuffer.get(abyte1);
            bytebuffer = abyte1;
        }
        engineUpdateAAD(((byte []) (bytebuffer)), i, j);
    }

    protected final void engineUpdateAAD(byte abyte0[], int i, int j)
    {
        if(mCachedException != null)
            return;
        try
        {
            ensureKeystoreOperationInitialized();
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            mCachedException = abyte0;
            return;
        }
        if(mAdditionalAuthenticationDataStreamerClosed)
            throw new IllegalStateException("AAD can only be provided before Cipher.update is invoked");
        if(mAdditionalAuthenticationDataStreamer == null)
            throw new IllegalStateException("This cipher does not support AAD");
        try
        {
            abyte0 = mAdditionalAuthenticationDataStreamer.update(abyte0, i, j);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            mCachedException = abyte0;
            return;
        }
        if(abyte0 != null && abyte0.length > 0)
            throw new ProviderException((new StringBuilder()).append("AAD update unexpectedly produced output: ").append(abyte0.length).append(" bytes").toString());
        else
            return;
    }

    protected final byte[] engineWrap(Key key)
        throws IllegalBlockSizeException, InvalidKeyException
    {
        byte abyte0[];
        Object obj;
        byte abyte1[];
        if(mKey == null)
            throw new IllegalStateException("Not initilized");
        if(!isEncrypting())
            throw new IllegalStateException("Cipher must be initialized in Cipher.WRAP_MODE to wrap keys");
        if(key == null)
            throw new NullPointerException("key == null");
        abyte0 = null;
        obj = null;
        abyte1 = null;
        if(!(key instanceof SecretKey)) goto _L2; else goto _L1
_L1:
        if("RAW".equalsIgnoreCase(key.getFormat()))
            abyte1 = key.getEncoded();
        abyte0 = abyte1;
        if(abyte1 != null)
            break MISSING_BLOCK_LABEL_122;
        try
        {
            abyte0 = ((SecretKeySpec)SecretKeyFactory.getInstance(key.getAlgorithm()).getKeySpec((SecretKey)key, javax/crypto/spec/SecretKeySpec)).getEncoded();
        }
        // Misplaced declaration of an exception variable
        catch(Key key)
        {
            throw new InvalidKeyException("Failed to wrap key because it does not export its key material", key);
        }
_L4:
        if(abyte0 == null)
            throw new InvalidKeyException("Failed to wrap key because it does not export its key material");
        break MISSING_BLOCK_LABEL_344;
_L2:
        if(!(key instanceof PrivateKey))
            break MISSING_BLOCK_LABEL_230;
        byte abyte2[] = abyte0;
        if("PKCS8".equalsIgnoreCase(key.getFormat()))
            abyte2 = key.getEncoded();
        abyte0 = abyte2;
        if(abyte2 != null)
            continue; /* Loop/switch isn't completed */
        try
        {
            abyte0 = ((PKCS8EncodedKeySpec)KeyFactory.getInstance(key.getAlgorithm()).getKeySpec(key, java/security/spec/PKCS8EncodedKeySpec)).getEncoded();
        }
        // Misplaced declaration of an exception variable
        catch(Key key)
        {
            throw new InvalidKeyException("Failed to wrap key because it does not export its key material", key);
        }
        continue; /* Loop/switch isn't completed */
        if(!(key instanceof PublicKey))
            break; /* Loop/switch isn't completed */
        byte abyte3[] = obj;
        if("X.509".equalsIgnoreCase(key.getFormat()))
            abyte3 = key.getEncoded();
        abyte0 = abyte3;
        if(abyte3 != null)
            continue; /* Loop/switch isn't completed */
        try
        {
            abyte0 = ((X509EncodedKeySpec)KeyFactory.getInstance(key.getAlgorithm()).getKeySpec(key, java/security/spec/X509EncodedKeySpec)).getEncoded();
        }
        // Misplaced declaration of an exception variable
        catch(Key key)
        {
            throw new InvalidKeyException("Failed to wrap key because it does not export its key material", key);
        }
        if(true) goto _L4; else goto _L3
_L3:
        throw new InvalidKeyException((new StringBuilder()).append("Unsupported key type: ").append(key.getClass().getName()).toString());
        try
        {
            key = engineDoFinal(abyte0, 0, abyte0.length);
        }
        // Misplaced declaration of an exception variable
        catch(Key key)
        {
            throw (IllegalBlockSizeException)(new IllegalBlockSizeException()).initCause(key);
        }
        return key;
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

    protected abstract int getAdditionalEntropyAmountForBegin();

    protected abstract int getAdditionalEntropyAmountForFinish();

    protected final long getConsumedInputSizeBytes()
    {
        if(mMainDataStreamer == null)
            throw new IllegalStateException("Not initialized");
        else
            return mMainDataStreamer.getConsumedInputSizeBytes();
    }

    protected final KeyStore getKeyStore()
    {
        return mKeyStore;
    }

    protected final int getKeymasterPurposeOverride()
    {
        return mKeymasterPurposeOverride;
    }

    public final long getOperationHandle()
    {
        return mOperationHandle;
    }

    protected final long getProducedOutputSizeBytes()
    {
        if(mMainDataStreamer == null)
            throw new IllegalStateException("Not initialized");
        else
            return mMainDataStreamer.getProducedOutputSizeBytes();
    }

    protected abstract void initAlgorithmSpecificParameters()
        throws InvalidKeyException;

    protected abstract void initAlgorithmSpecificParameters(AlgorithmParameters algorithmparameters)
        throws InvalidAlgorithmParameterException;

    protected abstract void initAlgorithmSpecificParameters(AlgorithmParameterSpec algorithmparameterspec)
        throws InvalidAlgorithmParameterException;

    protected abstract void initKey(int i, Key key)
        throws InvalidKeyException;

    protected final boolean isEncrypting()
    {
        return mEncrypting;
    }

    protected abstract void loadAlgorithmSpecificParametersFromBeginResult(KeymasterArguments keymasterarguments);

    protected void resetAll()
    {
        IBinder ibinder = mOperationToken;
        if(ibinder != null)
            mKeyStore.abort(ibinder);
        mEncrypting = false;
        mKeymasterPurposeOverride = -1;
        mKey = null;
        mRng = null;
        mOperationToken = null;
        mOperationHandle = 0L;
        mMainDataStreamer = null;
        mAdditionalAuthenticationDataStreamer = null;
        mAdditionalAuthenticationDataStreamerClosed = false;
        mCachedException = null;
    }

    protected void resetWhilePreservingInitState()
    {
        IBinder ibinder = mOperationToken;
        if(ibinder != null)
            mKeyStore.abort(ibinder);
        mOperationToken = null;
        mOperationHandle = 0L;
        mMainDataStreamer = null;
        mAdditionalAuthenticationDataStreamer = null;
        mAdditionalAuthenticationDataStreamerClosed = false;
        mCachedException = null;
    }

    protected final void setKey(AndroidKeyStoreKey androidkeystorekey)
    {
        mKey = androidkeystorekey;
    }

    protected final void setKeymasterPurposeOverride(int i)
    {
        mKeymasterPurposeOverride = i;
    }

    private KeyStoreCryptoOperationStreamer mAdditionalAuthenticationDataStreamer;
    private boolean mAdditionalAuthenticationDataStreamerClosed;
    private Exception mCachedException;
    private boolean mEncrypting;
    private AndroidKeyStoreKey mKey;
    private final KeyStore mKeyStore = KeyStore.getInstance();
    private int mKeymasterPurposeOverride;
    private KeyStoreCryptoOperationStreamer mMainDataStreamer;
    private long mOperationHandle;
    private IBinder mOperationToken;
    private SecureRandom mRng;
}

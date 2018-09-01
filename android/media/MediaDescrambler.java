// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.hardware.cas.V1_0.IDescramblerBase;
import android.hardware.cas.V1_0.IMediaCasService;
import android.os.*;
import android.util.Log;
import java.nio.ByteBuffer;

// Referenced classes of package android.media:
//            MediaCas, MediaCasStateException

public final class MediaDescrambler
    implements AutoCloseable
{

    public MediaDescrambler(int i)
        throws MediaCasException.UnsupportedCasException
    {
        mIDescrambler = MediaCas.getService().createDescrambler(i);
        if(mIDescrambler == null)
            throw new MediaCasException.UnsupportedCasException((new StringBuilder()).append("Unsupported CA_system_id ").append(i).toString());
        break MISSING_BLOCK_LABEL_155;
        Object obj;
        obj;
        StringBuilder stringbuilder = JVM INSTR new #52  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e("MediaDescrambler", stringbuilder.append("Failed to create descrambler: ").append(obj).toString());
        mIDescrambler = null;
        if(mIDescrambler == null)
            throw new MediaCasException.UnsupportedCasException((new StringBuilder()).append("Unsupported CA_system_id ").append(i).toString());
        break MISSING_BLOCK_LABEL_155;
        obj;
        if(mIDescrambler == null)
            throw new MediaCasException.UnsupportedCasException((new StringBuilder()).append("Unsupported CA_system_id ").append(i).toString());
        else
            throw obj;
        native_setup(mIDescrambler.asBinder());
        return;
    }

    private final void cleanupAndRethrowIllegalState()
    {
        mIDescrambler = null;
        throw new IllegalStateException();
    }

    private final native int native_descramble(byte byte0, int i, int ai[], int ai1[], ByteBuffer bytebuffer, int j, int k, 
            ByteBuffer bytebuffer1, int l, int i1)
        throws RemoteException;

    private static final native void native_init();

    private final native void native_release();

    private final native void native_setup(IHwBinder ihwbinder);

    private final void validateInternalStates()
    {
        if(mIDescrambler == null)
            throw new IllegalStateException();
        else
            return;
    }

    public void close()
    {
        if(mIDescrambler == null)
            break MISSING_BLOCK_LABEL_22;
        Exception exception;
        try
        {
            mIDescrambler.release();
        }
        catch(RemoteException remoteexception) { }
        mIDescrambler = null;
        native_release();
        return;
        exception;
        mIDescrambler = null;
        throw exception;
    }

    public final int descramble(ByteBuffer bytebuffer, ByteBuffer bytebuffer1, MediaCodec.CryptoInfo cryptoinfo)
    {
        validateInternalStates();
        if(cryptoinfo.numSubSamples <= 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid CryptoInfo: invalid numSubSamples=").append(cryptoinfo.numSubSamples).toString());
        if(cryptoinfo.numBytesOfClearData == null && cryptoinfo.numBytesOfEncryptedData == null)
            throw new IllegalArgumentException("Invalid CryptoInfo: clearData and encryptedData size arrays are both null!");
        if(cryptoinfo.numBytesOfClearData != null && cryptoinfo.numBytesOfClearData.length < cryptoinfo.numSubSamples)
            throw new IllegalArgumentException("Invalid CryptoInfo: numBytesOfClearData is too small!");
        if(cryptoinfo.numBytesOfEncryptedData != null && cryptoinfo.numBytesOfEncryptedData.length < cryptoinfo.numSubSamples)
            throw new IllegalArgumentException("Invalid CryptoInfo: numBytesOfEncryptedData is too small!");
        if(cryptoinfo.key == null || cryptoinfo.key.length != 16)
            throw new IllegalArgumentException("Invalid CryptoInfo: key array is invalid!");
        int i = native_descramble(cryptoinfo.key[0], cryptoinfo.numSubSamples, cryptoinfo.numBytesOfClearData, cryptoinfo.numBytesOfEncryptedData, bytebuffer, bytebuffer.position(), bytebuffer.limit(), bytebuffer1, bytebuffer1.position(), bytebuffer1.limit());
        return i;
        bytebuffer;
        cleanupAndRethrowIllegalState();
_L2:
        return -1;
        bytebuffer;
        MediaCasStateException.throwExceptionIfNeeded(((ServiceSpecificException) (bytebuffer)).errorCode, bytebuffer.getMessage());
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected void finalize()
    {
        close();
    }

    IHwBinder getBinder()
    {
        validateInternalStates();
        return mIDescrambler.asBinder();
    }

    public final boolean requiresSecureDecoderComponent(String s)
    {
        validateInternalStates();
        boolean flag;
        try
        {
            flag = mIDescrambler.requiresSecureDecoderComponent(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            cleanupAndRethrowIllegalState();
            return true;
        }
        return flag;
    }

    public final void setMediaCasSession(MediaCas.Session session)
    {
        validateInternalStates();
        MediaCasStateException.throwExceptionIfNeeded(mIDescrambler.setMediaCasSession(session.mSessionId));
_L1:
        return;
        session;
        cleanupAndRethrowIllegalState();
          goto _L1
    }

    private static final String TAG = "MediaDescrambler";
    private IDescramblerBase mIDescrambler;
    private long mNativeContext;

    static 
    {
        System.loadLibrary("media_jni");
        native_init();
    }
}

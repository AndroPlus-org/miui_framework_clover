// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.graphics.Rect;
import android.os.*;
import android.view.Surface;
import java.io.IOException;
import java.nio.*;
import java.util.*;

// Referenced classes of package android.media:
//            MediaFormat, AudioSystem, MediaDescrambler, MediaCodecList, 
//            MediaCrypto, Image, MediaCodecInfo, Utils

public final class MediaCodec
{
    public static final class BufferInfo
    {

        public BufferInfo dup()
        {
            BufferInfo bufferinfo = new BufferInfo();
            bufferinfo.set(offset, size, presentationTimeUs, flags);
            return bufferinfo;
        }

        public void set(int i, int j, long l, int k)
        {
            offset = i;
            size = j;
            presentationTimeUs = l;
            flags = k;
        }

        public int flags;
        public int offset;
        public long presentationTimeUs;
        public int size;

        public BufferInfo()
        {
        }
    }

    private static class BufferMap
    {

        public void clear()
        {
            for(Iterator iterator = mMap.values().iterator(); iterator.hasNext(); ((CodecBuffer)iterator.next()).free());
            mMap.clear();
        }

        public void put(int i, Image image)
        {
            CodecBuffer codecbuffer = (CodecBuffer)mMap.get(Integer.valueOf(i));
            CodecBuffer codecbuffer1 = codecbuffer;
            if(codecbuffer == null)
            {
                codecbuffer1 = new CodecBuffer(null);
                mMap.put(Integer.valueOf(i), codecbuffer1);
            }
            codecbuffer1.setImage(image);
        }

        public void put(int i, ByteBuffer bytebuffer)
        {
            CodecBuffer codecbuffer = (CodecBuffer)mMap.get(Integer.valueOf(i));
            CodecBuffer codecbuffer1 = codecbuffer;
            if(codecbuffer == null)
            {
                codecbuffer1 = new CodecBuffer(null);
                mMap.put(Integer.valueOf(i), codecbuffer1);
            }
            codecbuffer1.setByteBuffer(bytebuffer);
        }

        public void remove(int i)
        {
            CodecBuffer codecbuffer = (CodecBuffer)mMap.get(Integer.valueOf(i));
            if(codecbuffer != null)
            {
                codecbuffer.free();
                mMap.remove(Integer.valueOf(i));
            }
        }

        private final Map mMap;

        private BufferMap()
        {
            mMap = new HashMap();
        }

        BufferMap(BufferMap buffermap)
        {
            this();
        }
    }

    private static class BufferMap.CodecBuffer
    {

        public void free()
        {
            if(mByteBuffer != null)
            {
                NioUtils.freeDirectBuffer(mByteBuffer);
                mByteBuffer = null;
            }
            if(mImage != null)
            {
                mImage.close();
                mImage = null;
            }
        }

        public void setByteBuffer(ByteBuffer bytebuffer)
        {
            free();
            mByteBuffer = bytebuffer;
        }

        public void setImage(Image image)
        {
            free();
            mImage = image;
        }

        private ByteBuffer mByteBuffer;
        private Image mImage;

        private BufferMap.CodecBuffer()
        {
        }

        BufferMap.CodecBuffer(BufferMap.CodecBuffer codecbuffer)
        {
            this();
        }
    }

    public static abstract class Callback
    {

        public abstract void onError(MediaCodec mediacodec, CodecException codecexception);

        public abstract void onInputBufferAvailable(MediaCodec mediacodec, int i);

        public abstract void onOutputBufferAvailable(MediaCodec mediacodec, int i, BufferInfo bufferinfo);

        public abstract void onOutputFormatChanged(MediaCodec mediacodec, MediaFormat mediaformat);

        public Callback()
        {
        }
    }

    public static final class CodecException extends IllegalStateException
    {

        public String getDiagnosticInfo()
        {
            return mDiagnosticInfo;
        }

        public int getErrorCode()
        {
            return mErrorCode;
        }

        public boolean isRecoverable()
        {
            boolean flag;
            if(mActionCode == 2)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean isTransient()
        {
            boolean flag = true;
            if(mActionCode != 1)
                flag = false;
            return flag;
        }

        private static final int ACTION_RECOVERABLE = 2;
        private static final int ACTION_TRANSIENT = 1;
        public static final int ERROR_INSUFFICIENT_RESOURCE = 1100;
        public static final int ERROR_RECLAIMED = 1101;
        private final int mActionCode;
        private final String mDiagnosticInfo;
        private final int mErrorCode;

        CodecException(int i, int j, String s)
        {
            super(s);
            mErrorCode = i;
            mActionCode = j;
            if(i < 0)
                s = "neg_";
            else
                s = "";
            mDiagnosticInfo = (new StringBuilder()).append("android.media.MediaCodec.error_").append(s).append(Math.abs(i)).toString();
        }
    }

    public static final class CryptoException extends RuntimeException
    {

        public int getErrorCode()
        {
            return mErrorCode;
        }

        public static final int ERROR_INSUFFICIENT_OUTPUT_PROTECTION = 4;
        public static final int ERROR_KEY_EXPIRED = 2;
        public static final int ERROR_NO_KEY = 1;
        public static final int ERROR_RESOURCE_BUSY = 3;
        public static final int ERROR_SESSION_NOT_OPENED = 5;
        public static final int ERROR_UNSUPPORTED_OPERATION = 6;
        private int mErrorCode;

        public CryptoException(int i, String s)
        {
            super(s);
            mErrorCode = i;
        }
    }

    public static final class CryptoInfo
    {

        public void set(int i, int ai[], int ai1[], byte abyte0[], byte abyte1[], int j)
        {
            numSubSamples = i;
            numBytesOfClearData = ai;
            numBytesOfEncryptedData = ai1;
            key = abyte0;
            iv = abyte1;
            mode = j;
            pattern = zeroPattern;
        }

        public void setPattern(Pattern pattern1)
        {
            pattern = pattern1;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(numSubSamples).append(" subsamples, key [");
            for(int i = 0; i < key.length; i++)
            {
                stringbuilder.append("0123456789abcdef".charAt((key[i] & 0xf0) >> 4));
                stringbuilder.append("0123456789abcdef".charAt(key[i] & 0xf));
            }

            stringbuilder.append("], iv [");
            for(int j = 0; j < key.length; j++)
            {
                stringbuilder.append("0123456789abcdef".charAt((iv[j] & 0xf0) >> 4));
                stringbuilder.append("0123456789abcdef".charAt(iv[j] & 0xf));
            }

            stringbuilder.append("], clear ");
            stringbuilder.append(Arrays.toString(numBytesOfClearData));
            stringbuilder.append(", encrypted ");
            stringbuilder.append(Arrays.toString(numBytesOfEncryptedData));
            return stringbuilder.toString();
        }

        public byte iv[];
        public byte key[];
        public int mode;
        public int numBytesOfClearData[];
        public int numBytesOfEncryptedData[];
        public int numSubSamples;
        private Pattern pattern;
        private final Pattern zeroPattern = new Pattern(0, 0);

        public CryptoInfo()
        {
        }
    }

    public static final class CryptoInfo.Pattern
    {

        public int getEncryptBlocks()
        {
            return mEncryptBlocks;
        }

        public int getSkipBlocks()
        {
            return mSkipBlocks;
        }

        public void set(int i, int j)
        {
            mEncryptBlocks = i;
            mSkipBlocks = j;
        }

        private int mEncryptBlocks;
        private int mSkipBlocks;

        public CryptoInfo.Pattern(int i, int j)
        {
            set(i, j);
        }
    }

    private class EventHandler extends Handler
    {

        private void handleCallback(Message message)
        {
            if(MediaCodec._2D_get3(MediaCodec.this) == null)
                return;
            message.arg1;
            JVM INSTR tableswitch 1 4: default 44
        //                       1 45
        //                       2 100
        //                       3 165
        //                       4 189;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            return;
_L2:
            int i = message.arg2;
            Object obj = MediaCodec._2D_get0(MediaCodec.this);
            obj;
            JVM INSTR monitorenter ;
            MediaCodec._2D_wrap1(MediaCodec.this, MediaCodec._2D_get1(MediaCodec.this), i);
            obj;
            JVM INSTR monitorexit ;
            MediaCodec._2D_get3(MediaCodec.this).onInputBufferAvailable(mCodec, i);
            continue; /* Loop/switch isn't completed */
            message;
            throw message;
_L3:
            i = message.arg2;
            obj = (BufferInfo)message.obj;
            message = ((Message) (MediaCodec._2D_get0(MediaCodec.this)));
            message;
            JVM INSTR monitorenter ;
            MediaCodec._2D_wrap2(MediaCodec.this, MediaCodec._2D_get2(MediaCodec.this), i, ((BufferInfo) (obj)));
            message;
            JVM INSTR monitorexit ;
            MediaCodec._2D_get3(MediaCodec.this).onOutputBufferAvailable(mCodec, i, ((BufferInfo) (obj)));
            continue; /* Loop/switch isn't completed */
            Exception exception;
            exception;
            throw exception;
_L4:
            MediaCodec._2D_get3(MediaCodec.this).onError(mCodec, (CodecException)message.obj);
            continue; /* Loop/switch isn't completed */
_L5:
            MediaCodec._2D_get3(MediaCodec.this).onOutputFormatChanged(mCodec, new MediaFormat((Map)message.obj));
            if(true) goto _L1; else goto _L6
_L6:
        }

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 3: default 32
        //                       1 33
        //                       2 41
        //                       3 59;
               goto _L1 _L2 _L3 _L4
_L1:
            return;
_L2:
            handleCallback(message);
            continue; /* Loop/switch isn't completed */
_L3:
            MediaCodec._2D_set0(MediaCodec.this, (Callback)message.obj);
            continue; /* Loop/switch isn't completed */
_L4:
            Object obj = MediaCodec._2D_get4(MediaCodec.this);
            obj;
            JVM INSTR monitorenter ;
            message = (Map)message.obj;
            int i = 0;
_L8:
            Object obj1;
            Object obj2;
            obj1 = JVM INSTR new #102 <Class StringBuilder>;
            ((StringBuilder) (obj1)).StringBuilder();
            obj1 = message.get(((StringBuilder) (obj1)).append(i).append("-media-time-us").toString());
            obj2 = JVM INSTR new #102 <Class StringBuilder>;
            ((StringBuilder) (obj2)).StringBuilder();
            obj2 = message.get(((StringBuilder) (obj2)).append(i).append("-system-nano").toString());
            if(obj1 != null && obj2 != null) goto _L6; else goto _L5
_L5:
            obj;
            JVM INSTR monitorexit ;
            continue; /* Loop/switch isn't completed */
_L6:
            if(MediaCodec._2D_get5(MediaCodec.this) == null) goto _L5; else goto _L7
_L7:
            MediaCodec._2D_get5(MediaCodec.this).onFrameRendered(mCodec, ((Long)obj1).longValue(), ((Long)obj2).longValue());
            i++;
              goto _L8
            message;
            throw message;
            if(true) goto _L1; else goto _L9
_L9:
        }

        private MediaCodec mCodec;
        final MediaCodec this$0;

        public EventHandler(MediaCodec mediacodec1, Looper looper)
        {
            this$0 = MediaCodec.this;
            super(looper);
            mCodec = mediacodec1;
        }
    }

    public static class MediaImage extends Image
    {

        public void close()
        {
            if(mIsImageValid)
            {
                NioUtils.freeDirectBuffer(mBuffer);
                mIsImageValid = false;
            }
        }

        public int getFormat()
        {
            throwISEIfImageIsInvalid();
            return mFormat;
        }

        public int getHeight()
        {
            throwISEIfImageIsInvalid();
            return mHeight;
        }

        public Image.Plane[] getPlanes()
        {
            throwISEIfImageIsInvalid();
            return (Image.Plane[])Arrays.copyOf(mPlanes, mPlanes.length);
        }

        public long getTimestamp()
        {
            throwISEIfImageIsInvalid();
            return mTimestamp;
        }

        public int getWidth()
        {
            throwISEIfImageIsInvalid();
            return mWidth;
        }

        public void setCropRect(Rect rect)
        {
            if(mIsReadOnly)
            {
                throw new ReadOnlyBufferException();
            } else
            {
                super.setCropRect(rect);
                return;
            }
        }

        private static final int TYPE_YUV = 1;
        private final ByteBuffer mBuffer;
        private final int mFormat = 35;
        private final int mHeight;
        private final ByteBuffer mInfo;
        private final boolean mIsReadOnly;
        private final Image.Plane mPlanes[];
        private long mTimestamp;
        private final int mWidth;
        private final int mXOffset;
        private final int mYOffset;

        public MediaImage(ByteBuffer bytebuffer, ByteBuffer bytebuffer1, boolean flag, long l, int i, int j, 
                Rect rect)
        {
            mTimestamp = l;
            mIsImageValid = true;
            mIsReadOnly = bytebuffer.isReadOnly();
            mBuffer = bytebuffer.duplicate();
            mXOffset = i;
            mYOffset = j;
            mInfo = bytebuffer1;
            if(bytebuffer1.remaining() == 104)
            {
                int k = bytebuffer1.getInt();
                if(k != 1)
                    throw new UnsupportedOperationException((new StringBuilder()).append("unsupported type: ").append(k).toString());
                int i1 = bytebuffer1.getInt();
                if(i1 != 3)
                    throw new RuntimeException((new StringBuilder()).append("unexpected number of planes: ").append(i1).toString());
                mWidth = bytebuffer1.getInt();
                mHeight = bytebuffer1.getInt();
                if(mWidth < 1 || mHeight < 1)
                    throw new UnsupportedOperationException((new StringBuilder()).append("unsupported size: ").append(mWidth).append("x").append(mHeight).toString());
                int j1 = bytebuffer1.getInt();
                if(j1 != 8)
                    throw new UnsupportedOperationException((new StringBuilder()).append("unsupported bit depth: ").append(j1).toString());
                k = bytebuffer1.getInt();
                if(k != 8)
                    throw new UnsupportedOperationException((new StringBuilder()).append("unsupported allocated bit depth: ").append(k).toString());
                mPlanes = new MediaPlane[i1];
                k = 0;
label0:
                do
                {
                    int k1;
                    int l1;
                    int i2;
                    int j2;
                    int k2;
label1:
                    {
                        if(k >= i1)
                            break label0;
                        k1 = bytebuffer1.getInt();
                        l1 = bytebuffer1.getInt();
                        i2 = bytebuffer1.getInt();
                        j2 = bytebuffer1.getInt();
                        k2 = bytebuffer1.getInt();
                        if(j2 == k2)
                        {
                            int l2;
                            if(k == 0)
                                l2 = 1;
                            else
                                l2 = 2;
                            if(j2 == l2)
                                break label1;
                        }
                        throw new UnsupportedOperationException((new StringBuilder()).append("unexpected subsampling: ").append(j2).append("x").append(k2).append(" on plane ").append(k).toString());
                    }
                    if(l1 < 1 || i2 < 1)
                        throw new UnsupportedOperationException((new StringBuilder()).append("unexpected strides: ").append(l1).append(" pixel, ").append(i2).append(" row on plane ").append(k).toString());
                    bytebuffer.clear();
                    bytebuffer.position(mBuffer.position() + k1 + (i / j2) * l1 + (j / k2) * i2);
                    bytebuffer.limit(bytebuffer.position() + Utils.divUp(j1, 8) + (mHeight / k2 - 1) * i2 + (mWidth / j2 - 1) * l1);
                    mPlanes[k] = new MediaPlane(bytebuffer.slice(), i2, l1);
                    k++;
                } while(true);
            } else
            {
                throw new UnsupportedOperationException((new StringBuilder()).append("unsupported info length: ").append(bytebuffer1.remaining()).toString());
            }
            bytebuffer = rect;
            if(rect == null)
                bytebuffer = new Rect(0, 0, mWidth, mHeight);
            bytebuffer.offset(-i, -j);
            super.setCropRect(bytebuffer);
        }
    }

    private class MediaImage.MediaPlane extends Image.Plane
    {

        public ByteBuffer getBuffer()
        {
            throwISEIfImageIsInvalid();
            return mData;
        }

        public int getPixelStride()
        {
            throwISEIfImageIsInvalid();
            return mColInc;
        }

        public int getRowStride()
        {
            throwISEIfImageIsInvalid();
            return mRowInc;
        }

        private final int mColInc;
        private final ByteBuffer mData;
        private final int mRowInc;
        final MediaImage this$1;

        public MediaImage.MediaPlane(ByteBuffer bytebuffer, int i, int j)
        {
            this$1 = MediaImage.this;
            super();
            mData = bytebuffer;
            mRowInc = i;
            mColInc = j;
        }
    }

    public static final class MetricsConstants
    {

        public static final String CODEC = "android.media.mediacodec.codec";
        public static final String ENCODER = "android.media.mediacodec.encoder";
        public static final String HEIGHT = "android.media.mediacodec.height";
        public static final String MIME_TYPE = "android.media.mediacodec.mime";
        public static final String MODE = "android.media.mediacodec.mode";
        public static final String MODE_AUDIO = "audio";
        public static final String MODE_VIDEO = "video";
        public static final String ROTATION = "android.media.mediacodec.rotation";
        public static final String SECURE = "android.media.mediacodec.secure";
        public static final String WIDTH = "android.media.mediacodec.width";

        private MetricsConstants()
        {
        }
    }

    public static interface OnFrameRenderedListener
    {

        public abstract void onFrameRendered(MediaCodec mediacodec, long l, long l1);
    }

    static class PersistentSurface extends Surface
    {

        public void release()
        {
            MediaCodec._2D_wrap0(this);
            super.release();
        }

        private long mPersistentObject;

        PersistentSurface()
        {
        }
    }


    static Object _2D_get0(MediaCodec mediacodec)
    {
        return mediacodec.mBufferLock;
    }

    static ByteBuffer[] _2D_get1(MediaCodec mediacodec)
    {
        return mediacodec.mCachedInputBuffers;
    }

    static ByteBuffer[] _2D_get2(MediaCodec mediacodec)
    {
        return mediacodec.mCachedOutputBuffers;
    }

    static Callback _2D_get3(MediaCodec mediacodec)
    {
        return mediacodec.mCallback;
    }

    static Object _2D_get4(MediaCodec mediacodec)
    {
        return mediacodec.mListenerLock;
    }

    static OnFrameRenderedListener _2D_get5(MediaCodec mediacodec)
    {
        return mediacodec.mOnFrameRenderedListener;
    }

    static Callback _2D_set0(MediaCodec mediacodec, Callback callback)
    {
        mediacodec.mCallback = callback;
        return callback;
    }

    static void _2D_wrap0(Surface surface)
    {
        native_releasePersistentInputSurface(surface);
    }

    static void _2D_wrap1(MediaCodec mediacodec, ByteBuffer abytebuffer[], int i)
    {
        mediacodec.validateInputByteBuffer(abytebuffer, i);
    }

    static void _2D_wrap2(MediaCodec mediacodec, ByteBuffer abytebuffer[], int i, BufferInfo bufferinfo)
    {
        mediacodec.validateOutputByteBuffer(abytebuffer, i, bufferinfo);
    }

    private MediaCodec(String s, boolean flag, boolean flag1)
    {
        mListenerLock = new Object();
        mHasSurface = false;
        Looper looper = Looper.myLooper();
        if(looper != null)
        {
            mEventHandler = new EventHandler(this, looper);
        } else
        {
            Looper looper1 = Looper.getMainLooper();
            if(looper1 != null)
                mEventHandler = new EventHandler(this, looper1);
            else
                mEventHandler = null;
        }
        mCallbackHandler = mEventHandler;
        mOnFrameRenderedHandler = mEventHandler;
        native_setup(s, flag, flag1);
    }

    private final void cacheBuffers(boolean flag)
    {
        ByteBuffer abytebuffer[] = null;
        ByteBuffer abytebuffer1[] = getBuffers(flag);
        abytebuffer = abytebuffer1;
        invalidateByteBuffers(abytebuffer1);
        abytebuffer = abytebuffer1;
_L2:
        if(flag)
            mCachedInputBuffers = abytebuffer;
        else
            mCachedOutputBuffers = abytebuffer;
        return;
        IllegalStateException illegalstateexception;
        illegalstateexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void configure(MediaFormat mediaformat, Surface surface, MediaCrypto mediacrypto, IHwBinder ihwbinder, int i)
    {
        if(mediacrypto != null && ihwbinder != null)
            throw new IllegalArgumentException("Can't use crypto and descrambler together!");
        String as[] = null;
        MediaFormat mediaformat1 = null;
        if(mediaformat != null)
        {
            as = mediaformat.getMap();
            String as1[] = new String[as.size()];
            mediaformat = ((MediaFormat) (new Object[as.size()]));
            int j = 0;
            Iterator iterator = as.entrySet().iterator();
            do
            {
                as = as1;
                mediaformat1 = mediaformat;
                if(!iterator.hasNext())
                    break;
                as = (java.util.Map.Entry)iterator.next();
                if(((String)as.getKey()).equals("audio-session-id"))
                {
                    int k;
                    try
                    {
                        k = ((Integer)as.getValue()).intValue();
                    }
                    // Misplaced declaration of an exception variable
                    catch(MediaFormat mediaformat)
                    {
                        throw new IllegalArgumentException("Wrong Session ID Parameter!");
                    }
                    as1[j] = "audio-hw-sync";
                    mediaformat[j] = Integer.valueOf(AudioSystem.getAudioHwSyncForSession(k));
                } else
                {
                    as1[j] = (String)as.getKey();
                    mediaformat[j] = as.getValue();
                }
                j++;
            } while(true);
        }
        boolean flag;
        if(surface != null)
            flag = true;
        else
            flag = false;
        mHasSurface = flag;
        native_configure(as, mediaformat1, surface, mediacrypto, ihwbinder, i);
    }

    public static MediaCodec createByCodecName(String s)
        throws IOException
    {
        return new MediaCodec(s, false, false);
    }

    public static MediaCodec createDecoderByType(String s)
        throws IOException
    {
        return new MediaCodec(s, true, false);
    }

    public static MediaCodec createEncoderByType(String s)
        throws IOException
    {
        return new MediaCodec(s, true, true);
    }

    public static Surface createPersistentInputSurface()
    {
        return native_createPersistentInputSurface();
    }

    private final void freeAllTrackedBuffers()
    {
        Object obj = mBufferLock;
        obj;
        JVM INSTR monitorenter ;
        freeByteBuffers(mCachedInputBuffers);
        freeByteBuffers(mCachedOutputBuffers);
        mCachedInputBuffers = null;
        mCachedOutputBuffers = null;
        mDequeuedInputBuffers.clear();
        mDequeuedOutputBuffers.clear();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private final void freeByteBuffer(ByteBuffer bytebuffer)
    {
        if(bytebuffer != null)
            NioUtils.freeDirectBuffer(bytebuffer);
    }

    private final void freeByteBuffers(ByteBuffer abytebuffer[])
    {
        if(abytebuffer != null)
        {
            int i = 0;
            for(int j = abytebuffer.length; i < j; i++)
                freeByteBuffer(abytebuffer[i]);

        }
    }

    private final native ByteBuffer getBuffer(boolean flag, int i);

    private final native ByteBuffer[] getBuffers(boolean flag);

    private EventHandler getEventHandlerOn(Handler handler, EventHandler eventhandler)
    {
        if(handler == null)
            return mEventHandler;
        handler = handler.getLooper();
        if(eventhandler.getLooper() == handler)
            return eventhandler;
        else
            return new EventHandler(this, handler);
    }

    private final native Map getFormatNative(boolean flag);

    private final native Image getImage(boolean flag, int i);

    private final native Map getOutputFormatNative(int i);

    private final void invalidateByteBuffer(ByteBuffer abytebuffer[], int i)
    {
        if(abytebuffer != null && i >= 0 && i < abytebuffer.length)
        {
            abytebuffer = abytebuffer[i];
            if(abytebuffer != null)
                abytebuffer.setAccessible(false);
        }
    }

    private final void invalidateByteBuffers(ByteBuffer abytebuffer[])
    {
        if(abytebuffer != null)
        {
            int i = abytebuffer.length;
            for(int j = 0; j < i; j++)
            {
                ByteBuffer bytebuffer = abytebuffer[j];
                if(bytebuffer != null)
                    bytebuffer.setAccessible(false);
            }

        }
    }

    private final native void native_configure(String as[], Object aobj[], Surface surface, MediaCrypto mediacrypto, IHwBinder ihwbinder, int i);

    private static final native PersistentSurface native_createPersistentInputSurface();

    private final native int native_dequeueInputBuffer(long l);

    private final native int native_dequeueOutputBuffer(BufferInfo bufferinfo, long l);

    private native void native_enableOnFrameRenderedListener(boolean flag);

    private final native void native_finalize();

    private final native void native_flush();

    private native PersistableBundle native_getMetrics();

    private static final native void native_init();

    private final native void native_queueInputBuffer(int i, int j, int k, long l, int i1)
        throws CryptoException;

    private final native void native_queueSecureInputBuffer(int i, int j, CryptoInfo cryptoinfo, long l, int k)
        throws CryptoException;

    private final native void native_release();

    private static final native void native_releasePersistentInputSurface(Surface surface);

    private final native void native_reset();

    private final native void native_setCallback(Callback callback);

    private final native void native_setInputSurface(Surface surface);

    private native void native_setSurface(Surface surface);

    private final native void native_setup(String s, boolean flag, boolean flag1);

    private final native void native_start();

    private final native void native_stop();

    private void postEventFromNative(int i, int j, int k, Object obj)
    {
        Object obj1 = mListenerLock;
        obj1;
        JVM INSTR monitorenter ;
        EventHandler eventhandler = mEventHandler;
        if(i != 1) goto _L2; else goto _L1
_L1:
        eventhandler = mCallbackHandler;
_L4:
        if(eventhandler == null)
            break MISSING_BLOCK_LABEL_47;
        eventhandler.sendMessage(eventhandler.obtainMessage(i, j, k, obj));
        obj1;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(i != 3)
            continue; /* Loop/switch isn't completed */
        eventhandler = mOnFrameRenderedHandler;
        if(true) goto _L4; else goto _L3
_L3:
        obj;
        throw obj;
    }

    private final native void releaseOutputBuffer(int i, boolean flag, boolean flag1, long l);

    private final void revalidateByteBuffer(ByteBuffer abytebuffer[], int i)
    {
        Object obj = mBufferLock;
        obj;
        JVM INSTR monitorenter ;
        if(abytebuffer == null || i < 0)
            break MISSING_BLOCK_LABEL_34;
        if(i >= abytebuffer.length)
            break MISSING_BLOCK_LABEL_34;
        abytebuffer = abytebuffer[i];
        if(abytebuffer == null)
            break MISSING_BLOCK_LABEL_34;
        abytebuffer.setAccessible(true);
        obj;
        JVM INSTR monitorexit ;
        return;
        abytebuffer;
        throw abytebuffer;
    }

    private final native void setParameters(String as[], Object aobj[]);

    private final void validateInputByteBuffer(ByteBuffer abytebuffer[], int i)
    {
        if(abytebuffer != null && i >= 0 && i < abytebuffer.length)
        {
            abytebuffer = abytebuffer[i];
            if(abytebuffer != null)
            {
                abytebuffer.setAccessible(true);
                abytebuffer.clear();
            }
        }
    }

    private final void validateOutputByteBuffer(ByteBuffer abytebuffer[], int i, BufferInfo bufferinfo)
    {
        if(abytebuffer != null && i >= 0 && i < abytebuffer.length)
        {
            abytebuffer = abytebuffer[i];
            if(abytebuffer != null)
            {
                abytebuffer.setAccessible(true);
                abytebuffer.limit(bufferinfo.offset + bufferinfo.size).position(bufferinfo.offset);
            }
        }
    }

    public void configure(MediaFormat mediaformat, Surface surface, int i, MediaDescrambler mediadescrambler)
    {
        if(mediadescrambler != null)
            mediadescrambler = mediadescrambler.getBinder();
        else
            mediadescrambler = null;
        configure(mediaformat, surface, null, ((IHwBinder) (mediadescrambler)), i);
    }

    public void configure(MediaFormat mediaformat, Surface surface, MediaCrypto mediacrypto, int i)
    {
        configure(mediaformat, surface, mediacrypto, null, i);
    }

    public final native Surface createInputSurface();

    public final int dequeueInputBuffer(long l)
    {
        int i = native_dequeueInputBuffer(l);
        if(i < 0) goto _L2; else goto _L1
_L1:
        Object obj = mBufferLock;
        obj;
        JVM INSTR monitorenter ;
        validateInputByteBuffer(mCachedInputBuffers, i);
        obj;
        JVM INSTR monitorexit ;
_L2:
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public final int dequeueOutputBuffer(BufferInfo bufferinfo, long l)
    {
        int i = native_dequeueOutputBuffer(bufferinfo, l);
        Object obj = mBufferLock;
        obj;
        JVM INSTR monitorenter ;
        if(i != -3) goto _L2; else goto _L1
_L1:
        cacheBuffers(false);
_L4:
        obj;
        JVM INSTR monitorexit ;
        return i;
_L2:
        if(i < 0)
            continue; /* Loop/switch isn't completed */
        validateOutputByteBuffer(mCachedOutputBuffers, i, bufferinfo);
        if(mHasSurface)
            mDequeuedOutputInfos.put(Integer.valueOf(i), bufferinfo.dup());
        if(true) goto _L4; else goto _L3
_L3:
        bufferinfo;
        throw bufferinfo;
    }

    protected void finalize()
    {
        native_finalize();
    }

    public final void flush()
    {
        Object obj = mBufferLock;
        obj;
        JVM INSTR monitorenter ;
        invalidateByteBuffers(mCachedInputBuffers);
        invalidateByteBuffers(mCachedOutputBuffers);
        mDequeuedInputBuffers.clear();
        mDequeuedOutputBuffers.clear();
        obj;
        JVM INSTR monitorexit ;
        native_flush();
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public MediaCodecInfo getCodecInfo()
    {
        return MediaCodecList.getInfoFor(getName());
    }

    public ByteBuffer getInputBuffer(int i)
    {
        ByteBuffer bytebuffer = getBuffer(true, i);
        Object obj = mBufferLock;
        obj;
        JVM INSTR monitorenter ;
        invalidateByteBuffer(mCachedInputBuffers, i);
        mDequeuedInputBuffers.put(i, bytebuffer);
        obj;
        JVM INSTR monitorexit ;
        return bytebuffer;
        Exception exception;
        exception;
        throw exception;
    }

    public ByteBuffer[] getInputBuffers()
    {
        if(mCachedInputBuffers == null)
            throw new IllegalStateException();
        else
            return mCachedInputBuffers;
    }

    public final MediaFormat getInputFormat()
    {
        return new MediaFormat(getFormatNative(true));
    }

    public Image getInputImage(int i)
    {
        Image image = getImage(true, i);
        Object obj = mBufferLock;
        obj;
        JVM INSTR monitorenter ;
        invalidateByteBuffer(mCachedInputBuffers, i);
        mDequeuedInputBuffers.put(i, image);
        obj;
        JVM INSTR monitorexit ;
        return image;
        Exception exception;
        exception;
        throw exception;
    }

    public PersistableBundle getMetrics()
    {
        return native_getMetrics();
    }

    public final native String getName();

    public ByteBuffer getOutputBuffer(int i)
    {
        ByteBuffer bytebuffer = getBuffer(false, i);
        Object obj = mBufferLock;
        obj;
        JVM INSTR monitorenter ;
        invalidateByteBuffer(mCachedOutputBuffers, i);
        mDequeuedOutputBuffers.put(i, bytebuffer);
        obj;
        JVM INSTR monitorexit ;
        return bytebuffer;
        Exception exception;
        exception;
        throw exception;
    }

    public ByteBuffer[] getOutputBuffers()
    {
        if(mCachedOutputBuffers == null)
            throw new IllegalStateException();
        else
            return mCachedOutputBuffers;
    }

    public final MediaFormat getOutputFormat()
    {
        return new MediaFormat(getFormatNative(false));
    }

    public final MediaFormat getOutputFormat(int i)
    {
        return new MediaFormat(getOutputFormatNative(i));
    }

    public Image getOutputImage(int i)
    {
        Image image = getImage(false, i);
        Object obj = mBufferLock;
        obj;
        JVM INSTR monitorenter ;
        invalidateByteBuffer(mCachedOutputBuffers, i);
        mDequeuedOutputBuffers.put(i, image);
        obj;
        JVM INSTR monitorexit ;
        return image;
        Exception exception;
        exception;
        throw exception;
    }

    public final void queueInputBuffer(int i, int j, int k, long l, int i1)
        throws CryptoException
    {
        Object obj = mBufferLock;
        obj;
        JVM INSTR monitorenter ;
        invalidateByteBuffer(mCachedInputBuffers, i);
        mDequeuedInputBuffers.remove(i);
        obj;
        JVM INSTR monitorexit ;
        Exception exception;
        try
        {
            native_queueInputBuffer(i, j, k, l, i1);
            return;
        }
        catch(Object obj1)
        {
            revalidateByteBuffer(mCachedInputBuffers, i);
            throw obj1;
        }
        exception;
        throw exception;
    }

    public final void queueSecureInputBuffer(int i, int j, CryptoInfo cryptoinfo, long l, int k)
        throws CryptoException
    {
        Object obj = mBufferLock;
        obj;
        JVM INSTR monitorenter ;
        invalidateByteBuffer(mCachedInputBuffers, i);
        mDequeuedInputBuffers.remove(i);
        obj;
        JVM INSTR monitorexit ;
        try
        {
            native_queueSecureInputBuffer(i, j, cryptoinfo, l, k);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(CryptoInfo cryptoinfo)
        {
            revalidateByteBuffer(mCachedInputBuffers, i);
        }
        break MISSING_BLOCK_LABEL_57;
        cryptoinfo;
        throw cryptoinfo;
        throw cryptoinfo;
    }

    public final void release()
    {
        freeAllTrackedBuffers();
        native_release();
    }

    public final void releaseOutputBuffer(int i, long l)
    {
        Object obj = mBufferLock;
        obj;
        JVM INSTR monitorenter ;
        invalidateByteBuffer(mCachedOutputBuffers, i);
        mDequeuedOutputBuffers.remove(i);
        BufferInfo bufferinfo;
        if(mHasSurface)
            bufferinfo = (BufferInfo)mDequeuedOutputInfos.remove(Integer.valueOf(i));
        obj;
        JVM INSTR monitorexit ;
        releaseOutputBuffer(i, true, true, l);
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void releaseOutputBuffer(int i, boolean flag)
    {
        Object obj = mBufferLock;
        obj;
        JVM INSTR monitorenter ;
        invalidateByteBuffer(mCachedOutputBuffers, i);
        mDequeuedOutputBuffers.remove(i);
        BufferInfo bufferinfo;
        if(mHasSurface)
            bufferinfo = (BufferInfo)mDequeuedOutputInfos.remove(Integer.valueOf(i));
        obj;
        JVM INSTR monitorexit ;
        releaseOutputBuffer(i, flag, false, 0L);
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void reset()
    {
        freeAllTrackedBuffers();
        native_reset();
    }

    public void setCallback(Callback callback)
    {
        setCallback(callback, null);
    }

    public void setCallback(Callback callback, Handler handler)
    {
        if(callback == null) goto _L2; else goto _L1
_L1:
        Object obj = mListenerLock;
        obj;
        JVM INSTR monitorenter ;
        handler = getEventHandlerOn(handler, mCallbackHandler);
        if(handler != mCallbackHandler)
        {
            mCallbackHandler.removeMessages(2);
            mCallbackHandler.removeMessages(1);
            mCallbackHandler = handler;
        }
        obj;
        JVM INSTR monitorexit ;
_L4:
        if(mCallbackHandler != null)
        {
            handler = mCallbackHandler.obtainMessage(2, 0, 0, callback);
            mCallbackHandler.sendMessage(handler);
            native_setCallback(callback);
        }
        return;
        callback;
        throw callback;
_L2:
        if(mCallbackHandler != null)
        {
            mCallbackHandler.removeMessages(2);
            mCallbackHandler.removeMessages(1);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setInputSurface(Surface surface)
    {
        if(!(surface instanceof PersistentSurface))
        {
            throw new IllegalArgumentException("not a PersistentSurface");
        } else
        {
            native_setInputSurface(surface);
            return;
        }
    }

    public void setOnFrameRenderedListener(OnFrameRenderedListener onframerenderedlistener, Handler handler)
    {
        Object obj = mListenerLock;
        obj;
        JVM INSTR monitorenter ;
        mOnFrameRenderedListener = onframerenderedlistener;
        if(onframerenderedlistener == null) goto _L2; else goto _L1
_L1:
        handler = getEventHandlerOn(handler, mOnFrameRenderedHandler);
        if(handler != mOnFrameRenderedHandler)
            mOnFrameRenderedHandler.removeMessages(3);
        mOnFrameRenderedHandler = handler;
_L4:
        boolean flag;
        if(onframerenderedlistener != null)
            flag = true;
        else
            flag = false;
        native_enableOnFrameRenderedListener(flag);
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(mOnFrameRenderedHandler == null) goto _L4; else goto _L3
_L3:
        mOnFrameRenderedHandler.removeMessages(3);
          goto _L4
        onframerenderedlistener;
        throw onframerenderedlistener;
    }

    public void setOutputSurface(Surface surface)
    {
        if(!mHasSurface)
        {
            throw new IllegalStateException("codec was not configured for an output surface");
        } else
        {
            native_setSurface(surface);
            return;
        }
    }

    public final void setParameters(Bundle bundle)
    {
        if(bundle == null)
            return;
        String as[] = new String[bundle.size()];
        Object aobj[] = new Object[bundle.size()];
        int i = 0;
        for(Iterator iterator = bundle.keySet().iterator(); iterator.hasNext();)
        {
            String s = (String)iterator.next();
            as[i] = s;
            aobj[i] = bundle.get(s);
            i++;
        }

        setParameters(as, aobj);
    }

    public final native void setVideoScalingMode(int i);

    public final native void signalEndOfInputStream();

    public final void start()
    {
        native_start();
        Object obj = mBufferLock;
        obj;
        JVM INSTR monitorenter ;
        cacheBuffers(true);
        cacheBuffers(false);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void stop()
    {
        native_stop();
        freeAllTrackedBuffers();
        Object obj = mListenerLock;
        obj;
        JVM INSTR monitorenter ;
        if(mCallbackHandler != null)
        {
            mCallbackHandler.removeMessages(2);
            mCallbackHandler.removeMessages(1);
        }
        if(mOnFrameRenderedHandler != null)
            mOnFrameRenderedHandler.removeMessages(3);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static final int BUFFER_FLAG_CODEC_CONFIG = 2;
    public static final int BUFFER_FLAG_END_OF_STREAM = 4;
    public static final int BUFFER_FLAG_KEY_FRAME = 1;
    public static final int BUFFER_FLAG_PARTIAL_FRAME = 8;
    public static final int BUFFER_FLAG_SYNC_FRAME = 1;
    private static final int CB_ERROR = 3;
    private static final int CB_INPUT_AVAILABLE = 1;
    private static final int CB_OUTPUT_AVAILABLE = 2;
    private static final int CB_OUTPUT_FORMAT_CHANGE = 4;
    public static final int CONFIGURE_FLAG_ENCODE = 1;
    public static final int CRYPTO_MODE_AES_CBC = 2;
    public static final int CRYPTO_MODE_AES_CTR = 1;
    public static final int CRYPTO_MODE_UNENCRYPTED = 0;
    private static final int EVENT_CALLBACK = 1;
    private static final int EVENT_FRAME_RENDERED = 3;
    private static final int EVENT_SET_CALLBACK = 2;
    public static final int INFO_OUTPUT_BUFFERS_CHANGED = -3;
    public static final int INFO_OUTPUT_FORMAT_CHANGED = -2;
    public static final int INFO_TRY_AGAIN_LATER = -1;
    public static final String PARAMETER_KEY_REQUEST_SYNC_FRAME = "request-sync";
    public static final String PARAMETER_KEY_SUSPEND = "drop-input-frames";
    public static final String PARAMETER_KEY_VIDEO_BITRATE = "video-bitrate";
    public static final int VIDEO_SCALING_MODE_SCALE_TO_FIT = 1;
    public static final int VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING = 2;
    private final Object mBufferLock = new Object();
    private ByteBuffer mCachedInputBuffers[];
    private ByteBuffer mCachedOutputBuffers[];
    private Callback mCallback;
    private EventHandler mCallbackHandler;
    private final BufferMap mDequeuedInputBuffers = new BufferMap(null);
    private final BufferMap mDequeuedOutputBuffers = new BufferMap(null);
    private final Map mDequeuedOutputInfos = new HashMap();
    private EventHandler mEventHandler;
    private boolean mHasSurface;
    private Object mListenerLock;
    private long mNativeContext;
    private EventHandler mOnFrameRenderedHandler;
    private OnFrameRenderedListener mOnFrameRenderedListener;

    static 
    {
        System.loadLibrary("media_jni");
        native_init();
    }
}

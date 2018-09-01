// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.TimeUnit;

// Referenced classes of package android.media:
//            AudioTrack, MediaTimestamp, PlaybackParams, SyncParams

public final class MediaSync
{
    private static class AudioBuffer
    {

        public int mBufferIndex;
        public ByteBuffer mByteBuffer;
        long mPresentationTimeUs;

        public AudioBuffer(ByteBuffer bytebuffer, int i, long l)
        {
            mByteBuffer = bytebuffer;
            mBufferIndex = i;
            mPresentationTimeUs = l;
        }
    }

    public static abstract class Callback
    {

        public abstract void onAudioBufferConsumed(MediaSync mediasync, ByteBuffer bytebuffer, int i);

        public Callback()
        {
        }
    }

    public static interface OnErrorListener
    {

        public abstract void onError(MediaSync mediasync, int i, int j);
    }


    static List _2D_get0(MediaSync mediasync)
    {
        return mediasync.mAudioBuffers;
    }

    static Object _2D_get1(MediaSync mediasync)
    {
        return mediasync.mAudioLock;
    }

    static AudioTrack _2D_get2(MediaSync mediasync)
    {
        return mediasync.mAudioTrack;
    }

    static Callback _2D_get3(MediaSync mediasync)
    {
        return mediasync.mCallback;
    }

    static Handler _2D_get4(MediaSync mediasync)
    {
        return mediasync.mCallbackHandler;
    }

    static Object _2D_get5(MediaSync mediasync)
    {
        return mediasync.mCallbackLock;
    }

    static float _2D_get6(MediaSync mediasync)
    {
        return mediasync.mPlaybackRate;
    }

    static Handler _2D_set0(MediaSync mediasync, Handler handler)
    {
        mediasync.mAudioHandler = handler;
        return handler;
    }

    static Looper _2D_set1(MediaSync mediasync, Looper looper)
    {
        mediasync.mAudioLooper = looper;
        return looper;
    }

    static long _2D_wrap0(MediaSync mediasync)
    {
        return mediasync.native_getPlayTimeForPendingAudioFrames();
    }

    static void _2D_wrap1(MediaSync mediasync, int i, long l)
    {
        mediasync.native_updateQueuedAudioData(i, l);
    }

    static void _2D_wrap2(MediaSync mediasync, long l)
    {
        mediasync.postRenderAudio(l);
    }

    static void _2D_wrap3(MediaSync mediasync, AudioBuffer audiobuffer)
    {
        mediasync.postReturnByteBuffer(audiobuffer);
    }

    public MediaSync()
    {
        mCallbackHandler = null;
        mCallback = null;
        mOnErrorListenerHandler = null;
        mOnErrorListener = null;
        mAudioThread = null;
        mAudioHandler = null;
        mAudioLooper = null;
        mAudioTrack = null;
        mAudioBuffers = new LinkedList();
        mPlaybackRate = 0.0F;
        native_setup();
    }

    private void createAudioThread()
    {
        mAudioThread = new Thread() {

            public void run()
            {
                Looper.prepare();
                Object obj1 = MediaSync._2D_get1(MediaSync.this);
                obj1;
                JVM INSTR monitorenter ;
                MediaSync._2D_set1(MediaSync.this, Looper.myLooper());
                MediaSync mediasync = MediaSync.this;
                Handler handler = JVM INSTR new #38  <Class Handler>;
                handler.Handler();
                MediaSync._2D_set0(mediasync, handler);
                MediaSync._2D_get1(MediaSync.this).notify();
                obj1;
                JVM INSTR monitorexit ;
                Looper.loop();
                return;
                Exception exception1;
                exception1;
                throw exception1;
            }

            final MediaSync this$0;

            
            {
                this$0 = MediaSync.this;
                super();
            }
        }
;
        mAudioThread.start();
        Object obj = mAudioLock;
        obj;
        JVM INSTR monitorenter ;
        Exception exception;
        try
        {
            mAudioLock.wait();
        }
        catch(InterruptedException interruptedexception) { }
        obj;
        JVM INSTR monitorexit ;
        return;
        exception;
        throw exception;
    }

    private final native void native_finalize();

    private final native void native_flush();

    private final native long native_getPlayTimeForPendingAudioFrames();

    private final native boolean native_getTimestamp(MediaTimestamp mediatimestamp);

    private static final native void native_init();

    private final native void native_release();

    private final native void native_setAudioTrack(AudioTrack audiotrack);

    private native float native_setPlaybackParams(PlaybackParams playbackparams);

    private final native void native_setSurface(Surface surface);

    private native float native_setSyncParams(SyncParams syncparams);

    private final native void native_setup();

    private final native void native_updateQueuedAudioData(int i, long l);

    private void postRenderAudio(long l)
    {
        mAudioHandler.postDelayed(new Runnable() {

            public void run()
            {
                Object obj = MediaSync._2D_get1(MediaSync.this);
                obj;
                JVM INSTR monitorenter ;
                float f = MediaSync._2D_get6(MediaSync.this);
                if((double)f != 0.0D)
                    break MISSING_BLOCK_LABEL_28;
                obj;
                JVM INSTR monitorexit ;
                return;
                boolean flag = MediaSync._2D_get0(MediaSync.this).isEmpty();
                if(!flag)
                    break MISSING_BLOCK_LABEL_48;
                obj;
                JVM INSTR monitorexit ;
                return;
                AudioBuffer audiobuffer;
                int i;
                audiobuffer = (AudioBuffer)MediaSync._2D_get0(MediaSync.this).get(0);
                i = audiobuffer.mByteBuffer.remaining();
                if(i <= 0)
                    break MISSING_BLOCK_LABEL_109;
                int j = MediaSync._2D_get2(MediaSync.this).getPlayState();
                if(j == 3)
                    break MISSING_BLOCK_LABEL_109;
                MediaSync._2D_get2(MediaSync.this).play();
_L1:
                j = MediaSync._2D_get2(MediaSync.this).write(audiobuffer.mByteBuffer, i, 1);
                if(j <= 0)
                    break MISSING_BLOCK_LABEL_244;
                if(audiobuffer.mPresentationTimeUs != -1L)
                {
                    MediaSync._2D_wrap1(MediaSync.this, i, audiobuffer.mPresentationTimeUs);
                    audiobuffer.mPresentationTimeUs = -1L;
                }
                if(j != i)
                    break MISSING_BLOCK_LABEL_244;
                MediaSync._2D_wrap3(MediaSync.this, audiobuffer);
                MediaSync._2D_get0(MediaSync.this).remove(0);
                if(!MediaSync._2D_get0(MediaSync.this).isEmpty())
                    MediaSync._2D_wrap2(MediaSync.this, 0L);
                obj;
                JVM INSTR monitorexit ;
                return;
                Object obj1;
                obj1;
                Log.w("MediaSync", "could not start audio track");
                  goto _L1
                obj1;
                throw obj1;
                long l1 = TimeUnit.MICROSECONDS.toMillis(MediaSync._2D_wrap0(MediaSync.this));
                MediaSync._2D_wrap2(MediaSync.this, l1 / 2L);
                obj;
                JVM INSTR monitorexit ;
            }

            final MediaSync this$0;

            
            {
                this$0 = MediaSync.this;
                super();
            }
        }
, l);
    }

    private final void postReturnByteBuffer(AudioBuffer audiobuffer)
    {
        Object obj = mCallbackLock;
        obj;
        JVM INSTR monitorenter ;
        if(mCallbackHandler != null)
        {
            Handler handler = mCallbackHandler;
            Runnable runnable = JVM INSTR new #8   <Class MediaSync$2>;
            runnable.this. _cls2();
            handler.post(runnable);
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        audiobuffer;
        throw audiobuffer;
    }

    private final void returnAudioBuffers()
    {
        Object obj = mAudioLock;
        obj;
        JVM INSTR monitorenter ;
        for(Iterator iterator = mAudioBuffers.iterator(); iterator.hasNext(); postReturnByteBuffer((AudioBuffer)iterator.next()));
        break MISSING_BLOCK_LABEL_47;
        Exception exception;
        exception;
        throw exception;
        mAudioBuffers.clear();
        obj;
        JVM INSTR monitorexit ;
    }

    public final native Surface createInputSurface();

    protected void finalize()
    {
        native_finalize();
    }

    public void flush()
    {
        Object obj = mAudioLock;
        obj;
        JVM INSTR monitorenter ;
        mAudioBuffers.clear();
        mCallbackHandler.removeCallbacksAndMessages(null);
        obj;
        JVM INSTR monitorexit ;
        if(mAudioTrack != null)
        {
            mAudioTrack.pause();
            mAudioTrack.flush();
            mAudioTrack.stop();
        }
        native_flush();
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public native PlaybackParams getPlaybackParams();

    public native SyncParams getSyncParams();

    public MediaTimestamp getTimestamp()
    {
        MediaTimestamp mediatimestamp;
        boolean flag;
        try
        {
            mediatimestamp = JVM INSTR new #241 <Class MediaTimestamp>;
            mediatimestamp.MediaTimestamp();
            flag = native_getTimestamp(mediatimestamp);
        }
        catch(IllegalStateException illegalstateexception)
        {
            return null;
        }
        if(flag)
            return mediatimestamp;
        else
            return null;
    }

    public void queueAudio(ByteBuffer bytebuffer, int i, long l)
    {
        if(mAudioTrack == null || mAudioThread == null)
            throw new IllegalStateException("AudioTrack is NOT set or audio thread is not created");
        Object obj = mAudioLock;
        obj;
        JVM INSTR monitorenter ;
        List list = mAudioBuffers;
        AudioBuffer audiobuffer = JVM INSTR new #12  <Class MediaSync$AudioBuffer>;
        audiobuffer.AudioBuffer(bytebuffer, i, l);
        list.add(audiobuffer);
        obj;
        JVM INSTR monitorexit ;
        if((double)mPlaybackRate != 0.0D)
            postRenderAudio(0L);
        return;
        bytebuffer;
        throw bytebuffer;
    }

    public final void release()
    {
        returnAudioBuffers();
        if(mAudioThread != null && mAudioLooper != null)
            mAudioLooper.quit();
        setCallback(null, null);
        native_release();
    }

    public void setAudioTrack(AudioTrack audiotrack)
    {
        native_setAudioTrack(audiotrack);
        mAudioTrack = audiotrack;
        if(audiotrack != null && mAudioThread == null)
            createAudioThread();
    }

    public void setCallback(Callback callback, Handler handler)
    {
        Object obj = mCallbackLock;
        obj;
        JVM INSTR monitorenter ;
        if(handler == null) goto _L2; else goto _L1
_L1:
        mCallbackHandler = handler;
_L3:
        mCallback = callback;
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
        Looper looper = Looper.myLooper();
        handler = looper;
        if(looper != null)
            break MISSING_BLOCK_LABEL_41;
        handler = Looper.getMainLooper();
        if(handler != null)
            break MISSING_BLOCK_LABEL_58;
        mCallbackHandler = null;
          goto _L3
        callback;
        throw callback;
        Handler handler1 = JVM INSTR new #176 <Class Handler>;
        handler1.Handler(handler);
        mCallbackHandler = handler1;
          goto _L3
    }

    public void setOnErrorListener(OnErrorListener onerrorlistener, Handler handler)
    {
        Object obj = mOnErrorListenerLock;
        obj;
        JVM INSTR monitorenter ;
        if(handler == null) goto _L2; else goto _L1
_L1:
        mOnErrorListenerHandler = handler;
_L3:
        mOnErrorListener = onerrorlistener;
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
        Looper looper = Looper.myLooper();
        handler = looper;
        if(looper != null)
            break MISSING_BLOCK_LABEL_41;
        handler = Looper.getMainLooper();
        if(handler != null)
            break MISSING_BLOCK_LABEL_58;
        mOnErrorListenerHandler = null;
          goto _L3
        onerrorlistener;
        throw onerrorlistener;
        Handler handler1 = JVM INSTR new #176 <Class Handler>;
        handler1.Handler(handler);
        mOnErrorListenerHandler = handler1;
          goto _L3
    }

    public void setPlaybackParams(PlaybackParams playbackparams)
    {
        Object obj = mAudioLock;
        obj;
        JVM INSTR monitorenter ;
        mPlaybackRate = native_setPlaybackParams(playbackparams);
        obj;
        JVM INSTR monitorexit ;
        if((double)mPlaybackRate != 0.0D && mAudioThread != null)
            postRenderAudio(0L);
        return;
        playbackparams;
        throw playbackparams;
    }

    public void setSurface(Surface surface)
    {
        native_setSurface(surface);
    }

    public void setSyncParams(SyncParams syncparams)
    {
        Object obj = mAudioLock;
        obj;
        JVM INSTR monitorenter ;
        mPlaybackRate = native_setSyncParams(syncparams);
        obj;
        JVM INSTR monitorexit ;
        if((double)mPlaybackRate != 0.0D && mAudioThread != null)
            postRenderAudio(0L);
        return;
        syncparams;
        throw syncparams;
    }

    private static final int CB_RETURN_AUDIO_BUFFER = 1;
    private static final int EVENT_CALLBACK = 1;
    private static final int EVENT_SET_CALLBACK = 2;
    public static final int MEDIASYNC_ERROR_AUDIOTRACK_FAIL = 1;
    public static final int MEDIASYNC_ERROR_SURFACE_FAIL = 2;
    private static final String TAG = "MediaSync";
    private List mAudioBuffers;
    private Handler mAudioHandler;
    private final Object mAudioLock = new Object();
    private Looper mAudioLooper;
    private Thread mAudioThread;
    private AudioTrack mAudioTrack;
    private Callback mCallback;
    private Handler mCallbackHandler;
    private final Object mCallbackLock = new Object();
    private long mNativeContext;
    private OnErrorListener mOnErrorListener;
    private Handler mOnErrorListenerHandler;
    private final Object mOnErrorListenerLock = new Object();
    private float mPlaybackRate;

    static 
    {
        System.loadLibrary("media_jni");
        native_init();
    }

    // Unreferenced inner class android/media/MediaSync$2

/* anonymous class */
    class _cls2
        implements Runnable
    {

        public void run()
        {
            Object obj = MediaSync._2D_get5(MediaSync.this);
            obj;
            JVM INSTR monitorenter ;
            Callback callback;
            Thread thread;
            Thread thread1;
            callback = MediaSync._2D_get3(MediaSync.this);
            if(MediaSync._2D_get4(MediaSync.this) == null)
                break MISSING_BLOCK_LABEL_53;
            thread = MediaSync._2D_get4(MediaSync.this).getLooper().getThread();
            thread1 = Thread.currentThread();
            if(thread == thread1)
                break MISSING_BLOCK_LABEL_56;
            obj;
            JVM INSTR monitorexit ;
            return;
            obj;
            JVM INSTR monitorexit ;
            if(callback != null)
                callback.onAudioBufferConsumed(sync, audioBuffer.mByteBuffer, audioBuffer.mBufferIndex);
            return;
            Exception exception;
            exception;
            throw exception;
        }

        final MediaSync this$0;
        final AudioBuffer val$audioBuffer;
        final MediaSync val$sync;

            
            {
                this$0 = MediaSync.this;
                sync = mediasync1;
                audioBuffer = audiobuffer;
                super();
            }
    }

}

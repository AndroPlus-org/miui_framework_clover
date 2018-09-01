// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.os.*;
import android.util.AndroidRuntimeException;
import android.util.Log;
import java.io.*;
import java.lang.ref.WeakReference;

// Referenced classes of package android.media:
//            PlayerBase, AudioAttributes

public class SoundPool extends PlayerBase
{
    public static class Builder
    {

        public SoundPool build()
        {
            if(mAudioAttributes == null)
                mAudioAttributes = (new AudioAttributes.Builder()).setUsage(1).build();
            return new SoundPool(mMaxStreams, mAudioAttributes, null);
        }

        public Builder setAudioAttributes(AudioAttributes audioattributes)
            throws IllegalArgumentException
        {
            if(audioattributes == null)
            {
                throw new IllegalArgumentException("Invalid null AudioAttributes");
            } else
            {
                mAudioAttributes = audioattributes;
                return this;
            }
        }

        public Builder setMaxStreams(int i)
            throws IllegalArgumentException
        {
            if(i <= 0)
            {
                throw new IllegalArgumentException("Strictly positive value required for the maximum number of streams");
            } else
            {
                mMaxStreams = i;
                return this;
            }
        }

        private AudioAttributes mAudioAttributes;
        private int mMaxStreams;

        public Builder()
        {
            mMaxStreams = 1;
        }
    }

    private final class EventHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            switch(message.what)
            {
            default:
                Log.e("SoundPool", (new StringBuilder()).append("Unknown message type ").append(message.what).toString());
                return;

            case 1: // '\001'
                break;
            }
            if(SoundPool._2D_get0())
                Log.d("SoundPool", (new StringBuilder()).append("Sample ").append(message.arg1).append(" loaded").toString());
            Object obj = SoundPool._2D_get1(SoundPool.this);
            obj;
            JVM INSTR monitorenter ;
            if(SoundPool._2D_get2(SoundPool.this) != null)
                SoundPool._2D_get2(SoundPool.this).onLoadComplete(SoundPool.this, message.arg1, message.arg2);
            obj;
            JVM INSTR monitorexit ;
            return;
            message;
            throw message;
        }

        final SoundPool this$0;

        public EventHandler(Looper looper)
        {
            this$0 = SoundPool.this;
            super(looper);
        }
    }

    public static interface OnLoadCompleteListener
    {

        public abstract void onLoadComplete(SoundPool soundpool, int i, int j);
    }


    static boolean _2D_get0()
    {
        return DEBUG;
    }

    static Object _2D_get1(SoundPool soundpool)
    {
        return soundpool.mLock;
    }

    static OnLoadCompleteListener _2D_get2(SoundPool soundpool)
    {
        return soundpool.mOnLoadCompleteListener;
    }

    public SoundPool(int i, int j, int k)
    {
        this(i, (new AudioAttributes.Builder()).setInternalLegacyStreamType(j).build());
        PlayerBase.deprecateStreamTypeForPlayback(j, "SoundPool", "SoundPool()");
    }

    private SoundPool(int i, AudioAttributes audioattributes)
    {
        super(audioattributes, 3);
        if(native_setup(new WeakReference(this), i, audioattributes) != 0)
        {
            throw new RuntimeException("Native setup failed");
        } else
        {
            mLock = new Object();
            mAttributes = audioattributes;
            baseRegisterPlayer();
            return;
        }
    }

    SoundPool(int i, AudioAttributes audioattributes, SoundPool soundpool)
    {
        this(i, audioattributes);
    }

    private final native int _load(FileDescriptor filedescriptor, long l, long l1, int i);

    private final native void _mute(boolean flag);

    private final native int _play(int i, float f, float f1, int j, int k, float f2);

    private final native void _setVolume(int i, float f, float f1);

    private final native void native_release();

    private final native int native_setup(Object obj, int i, Object obj1);

    private static void postEventFromNative(Object obj, int i, int j, int k, Object obj1)
    {
        obj = (SoundPool)((WeakReference)obj).get();
        if(obj == null)
            return;
        if(((SoundPool) (obj)).mEventHandler != null)
        {
            obj1 = ((SoundPool) (obj)).mEventHandler.obtainMessage(i, j, k, obj1);
            ((SoundPool) (obj)).mEventHandler.sendMessage(((Message) (obj1)));
        }
    }

    public final native void autoPause();

    public final native void autoResume();

    protected void finalize()
    {
        release();
    }

    public int load(Context context, int i, int j)
    {
        context = context.getResources().openRawResourceFd(i);
        i = 0;
        if(context != null)
        {
            i = _load(context.getFileDescriptor(), context.getStartOffset(), context.getLength(), j);
            try
            {
                context.close();
            }
            // Misplaced declaration of an exception variable
            catch(Context context) { }
        }
        return i;
    }

    public int load(AssetFileDescriptor assetfiledescriptor, int i)
    {
        if(assetfiledescriptor != null)
        {
            long l = assetfiledescriptor.getLength();
            if(l < 0L)
                throw new AndroidRuntimeException("no length for fd");
            else
                return _load(assetfiledescriptor.getFileDescriptor(), assetfiledescriptor.getStartOffset(), l, i);
        } else
        {
            return 0;
        }
    }

    public int load(FileDescriptor filedescriptor, long l, long l1, int i)
    {
        return _load(filedescriptor, l, l1, i);
    }

    public int load(String s, int i)
    {
        boolean flag;
        boolean flag1;
        int j;
        flag = false;
        flag1 = false;
        j = ((flag) ? 1 : 0);
        File file = JVM INSTR new #186 <Class File>;
        j = ((flag) ? 1 : 0);
        file.File(s);
        j = ((flag) ? 1 : 0);
        ParcelFileDescriptor parcelfiledescriptor = ParcelFileDescriptor.open(file, 0x10000000);
        j = ((flag1) ? 1 : 0);
        if(parcelfiledescriptor == null)
            break MISSING_BLOCK_LABEL_74;
        j = ((flag) ? 1 : 0);
        i = _load(parcelfiledescriptor.getFileDescriptor(), 0L, file.length(), i);
        j = i;
        parcelfiledescriptor.close();
        j = i;
_L2:
        return j;
        IOException ioexception;
        ioexception;
        Log.e("SoundPool", (new StringBuilder()).append("error loading ").append(s).toString());
        if(true) goto _L2; else goto _L1
_L1:
    }

    public final native void pause(int i);

    public final int play(int i, float f, float f1, int j, int k, float f2)
    {
        baseStart();
        return _play(i, f, f1, j, k, f2);
    }

    int playerApplyVolumeShaper(VolumeShaper.Configuration configuration, VolumeShaper.Operation operation)
    {
        return -1;
    }

    VolumeShaper.State playerGetVolumeShaperState(int i)
    {
        return null;
    }

    void playerPause()
    {
    }

    int playerSetAuxEffectSendLevel(boolean flag, float f)
    {
        return 0;
    }

    void playerSetVolume(boolean flag, float f, float f1)
    {
        _mute(flag);
    }

    void playerStart()
    {
    }

    void playerStop()
    {
    }

    public final void release()
    {
        baseRelease();
        native_release();
    }

    public final native void resume(int i);

    public final native void setLoop(int i, int j);

    public void setOnLoadCompleteListener(OnLoadCompleteListener onloadcompletelistener)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(onloadcompletelistener == null)
            break MISSING_BLOCK_LABEL_87;
        Looper looper = Looper.myLooper();
        if(looper == null) goto _L2; else goto _L1
_L1:
        EventHandler eventhandler1 = JVM INSTR new #9   <Class SoundPool$EventHandler>;
        eventhandler1.this. EventHandler(looper);
        mEventHandler = eventhandler1;
_L3:
        mOnLoadCompleteListener = onloadcompletelistener;
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
        Looper looper1 = Looper.getMainLooper();
        if(looper1 == null)
            break MISSING_BLOCK_LABEL_79;
        EventHandler eventhandler = JVM INSTR new #9   <Class SoundPool$EventHandler>;
        eventhandler.this. EventHandler(looper1);
        mEventHandler = eventhandler;
          goto _L3
        onloadcompletelistener;
        throw onloadcompletelistener;
        mEventHandler = null;
          goto _L3
        mEventHandler = null;
          goto _L3
    }

    public final native void setPriority(int i, int j);

    public final native void setRate(int i, float f);

    public void setVolume(int i, float f)
    {
        setVolume(i, f, f);
    }

    public final void setVolume(int i, float f, float f1)
    {
        _setVolume(i, f, f1);
    }

    public final native void stop(int i);

    public final native boolean unload(int i);

    private static final boolean DEBUG = Log.isLoggable("SoundPool", 3);
    private static final int SAMPLE_LOADED = 1;
    private static final String TAG = "SoundPool";
    private final AudioAttributes mAttributes;
    private EventHandler mEventHandler;
    private boolean mHasAppOpsPlayAudio;
    private final Object mLock;
    private long mNativeContext;
    private OnLoadCompleteListener mOnLoadCompleteListener;

    static 
    {
        System.loadLibrary("soundpool");
    }
}

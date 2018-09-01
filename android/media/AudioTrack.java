// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.*;
import android.util.ArrayMap;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.nio.*;
import java.util.Iterator;

// Referenced classes of package android.media:
//            PlayerBase, AudioRouting, VolumeAutomation, AudioAttributes, 
//            AudioFormat, AudioManager, VolumeShaper, AudioDeviceInfo, 
//            AudioTimestamp, PlaybackParams

public class AudioTrack extends PlayerBase
    implements AudioRouting, VolumeAutomation
{
    public static class Builder
    {

        public AudioTrack build()
            throws UnsupportedOperationException
        {
            if(mAttributes == null)
                mAttributes = (new AudioAttributes.Builder()).setUsage(1).build();
            mPerformanceMode;
            JVM INSTR tableswitch 0 2: default 56
        //                       0 229
        //                       1 190
        //                       2 251;
               goto _L1 _L2 _L3 _L4
_L1:
            Object obj;
            if(mFormat == null)
                mFormat = (new AudioFormat.Builder()).setChannelMask(12).setEncoding(1).build();
            try
            {
                if(mMode == 1 && mBufferSizeInBytes == 0)
                {
                    int i = mFormat.getChannelCount();
                    AudioFormat audioformat = mFormat;
                    mBufferSizeInBytes = i * AudioFormat.getBytesPerSample(mFormat.getEncoding());
                }
                obj = JVM INSTR new #6   <Class AudioTrack>;
                ((AudioTrack) (obj)).AudioTrack(mAttributes, mFormat, mBufferSizeInBytes, mMode, mSessionId);
                if(((AudioTrack) (obj)).getState() == 0)
                {
                    obj = JVM INSTR new #31  <Class UnsupportedOperationException>;
                    ((UnsupportedOperationException) (obj)).UnsupportedOperationException("Cannot create AudioTrack");
                    throw obj;
                }
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                throw new UnsupportedOperationException(((IllegalArgumentException) (obj)).getMessage());
            }
            break; /* Loop/switch isn't completed */
_L3:
            mAttributes = (new AudioAttributes.Builder(mAttributes)).replaceFlags((mAttributes.getAllFlags() | 0x100) & 0xfffffdff).build();
            continue; /* Loop/switch isn't completed */
_L2:
            if(!AudioTrack._2D_wrap0(mAttributes, mFormat, mBufferSizeInBytes, mMode))
                continue; /* Loop/switch isn't completed */
_L4:
            mAttributes = (new AudioAttributes.Builder(mAttributes)).replaceFlags((mAttributes.getAllFlags() | 0x200) & 0xfffffeff).build();
            if(true) goto _L1; else goto _L5
_L5:
            return ((AudioTrack) (obj));
        }

        public Builder setAudioAttributes(AudioAttributes audioattributes)
            throws IllegalArgumentException
        {
            if(audioattributes == null)
            {
                throw new IllegalArgumentException("Illegal null AudioAttributes argument");
            } else
            {
                mAttributes = audioattributes;
                return this;
            }
        }

        public Builder setAudioFormat(AudioFormat audioformat)
            throws IllegalArgumentException
        {
            if(audioformat == null)
            {
                throw new IllegalArgumentException("Illegal null AudioFormat argument");
            } else
            {
                mFormat = audioformat;
                return this;
            }
        }

        public Builder setBufferSizeInBytes(int i)
            throws IllegalArgumentException
        {
            if(i <= 0)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid buffer size ").append(i).toString());
            } else
            {
                mBufferSizeInBytes = i;
                return this;
            }
        }

        public Builder setPerformanceMode(int i)
        {
            switch(i)
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid performance mode ").append(i).toString());

            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
                mPerformanceMode = i;
                break;
            }
            return this;
        }

        public Builder setSessionId(int i)
            throws IllegalArgumentException
        {
            if(i != 0 && i < 1)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid audio session ID ").append(i).toString());
            } else
            {
                mSessionId = i;
                return this;
            }
        }

        public Builder setTransferMode(int i)
            throws IllegalArgumentException
        {
            switch(i)
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid transfer mode ").append(i).toString());

            case 0: // '\0'
            case 1: // '\001'
                mMode = i;
                break;
            }
            return this;
        }

        private AudioAttributes mAttributes;
        private int mBufferSizeInBytes;
        private AudioFormat mFormat;
        private int mMode;
        private int mPerformanceMode;
        private int mSessionId;

        public Builder()
        {
            mSessionId = 0;
            mMode = 1;
            mPerformanceMode = 0;
        }
    }

    private class NativePositionEventHandlerDelegate
    {

        Handler getHandler()
        {
            return mHandler;
        }

        private final Handler mHandler;
        final AudioTrack this$0;

        NativePositionEventHandlerDelegate(AudioTrack audiotrack, OnPlaybackPositionUpdateListener onplaybackpositionupdatelistener, Handler handler)
        {
            this$0 = AudioTrack.this;
            super();
            if(handler != null)
                final_looper = handler.getLooper();
            else
                final_looper = AudioTrack._2D_get0(AudioTrack.this);
            if(AudioTrack.this != null)
                mHandler = audiotrack. new _cls1(onplaybackpositionupdatelistener);
            else
                mHandler = null;
        }
    }

    private class NativeRoutingEventHandlerDelegate
    {

        Handler getHandler()
        {
            return mHandler;
        }

        private final Handler mHandler;
        final AudioTrack this$0;

        NativeRoutingEventHandlerDelegate(AudioTrack audiotrack, AudioRouting.OnRoutingChangedListener onroutingchangedlistener, Handler handler)
        {
            this$0 = AudioTrack.this;
            super();
            if(handler != null)
                final_looper = handler.getLooper();
            else
                final_looper = AudioTrack._2D_get0(AudioTrack.this);
            if(AudioTrack.this != null)
                mHandler = audiotrack. new _cls1(onroutingchangedlistener);
            else
                mHandler = null;
        }
    }

    public static interface OnPlaybackPositionUpdateListener
    {

        public abstract void onMarkerReached(AudioTrack audiotrack);

        public abstract void onPeriodicNotification(AudioTrack audiotrack);
    }

    public static interface OnRoutingChangedListener
        extends AudioRouting.OnRoutingChangedListener
    {

        public void onRoutingChanged(AudioRouting audiorouting)
        {
            if(audiorouting instanceof AudioTrack)
                onRoutingChanged((AudioTrack)audiorouting);
        }

        public abstract void onRoutingChanged(AudioTrack audiotrack);
    }


    static Looper _2D_get0(AudioTrack audiotrack)
    {
        return audiotrack.mInitializationLooper;
    }

    static boolean _2D_wrap0(AudioAttributes audioattributes, AudioFormat audioformat, int i, int j)
    {
        return shouldEnablePowerSaving(audioattributes, audioformat, i, j);
    }

    static void _2D_wrap1(String s)
    {
        loge(s);
    }

    static void _2D_wrap2(AudioTrack audiotrack)
    {
        audiotrack.startImpl();
    }

    public AudioTrack(int i, int j, int k, int l, int i1, int j1)
        throws IllegalArgumentException
    {
        this(i, j, k, l, i1, j1, 0);
    }

    public AudioTrack(int i, int j, int k, int l, int i1, int j1, int k1)
        throws IllegalArgumentException
    {
        this((new AudioAttributes.Builder()).setLegacyStreamType(i).build(), (new AudioFormat.Builder()).setChannelMask(k).setEncoding(l).setSampleRate(j).build(), i1, j1, k1);
        deprecateStreamTypeForPlayback(i, "AudioTrack", "AudioTrack()");
    }

    AudioTrack(long l)
    {
        super((new AudioAttributes.Builder()).build(), 1);
        mState = 0;
        mPlayState = 1;
        mPlayStateLock = new Object();
        mNativeBufferSizeInBytes = 0;
        mNativeBufferSizeInFrames = 0;
        mChannelCount = 1;
        mChannelMask = 4;
        mStreamType = 3;
        mDataLoadMode = 1;
        mChannelConfiguration = 4;
        mChannelIndexMask = 0;
        mSessionId = 0;
        mAvSyncHeader = null;
        mAvSyncBytesRemaining = 0;
        mOffset = 0;
        mPreferredDevice = null;
        mRoutingChangeListeners = new ArrayMap();
        mNativeTrackInJavaObj = 0L;
        mJniData = 0L;
        Looper looper = Looper.myLooper();
        Looper looper1 = looper;
        if(looper == null)
            looper1 = Looper.getMainLooper();
        mInitializationLooper = looper1;
        if(l != 0L)
        {
            baseRegisterPlayer();
            deferred_connect(l);
        } else
        {
            mState = 0;
        }
    }

    public AudioTrack(AudioAttributes audioattributes, AudioFormat audioformat, int i, int j, int k)
        throws IllegalArgumentException
    {
        int l;
        int i1;
        int j1;
        super(audioattributes, 1);
        mState = 0;
        mPlayState = 1;
        mPlayStateLock = new Object();
        mNativeBufferSizeInBytes = 0;
        mNativeBufferSizeInFrames = 0;
        mChannelCount = 1;
        mChannelMask = 4;
        mStreamType = 3;
        mDataLoadMode = 1;
        mChannelConfiguration = 4;
        mChannelIndexMask = 0;
        mSessionId = 0;
        mAvSyncHeader = null;
        mAvSyncBytesRemaining = 0;
        mOffset = 0;
        mPreferredDevice = null;
        mRoutingChangeListeners = new ArrayMap();
        if(audioformat == null)
            throw new IllegalArgumentException("Illegal null AudioFormat");
        if(shouldEnablePowerSaving(mAttributes, audioformat, i, j))
            mAttributes = (new AudioAttributes.Builder(mAttributes)).replaceFlags((mAttributes.getAllFlags() | 0x200) & 0xfffffeff).build();
        Looper looper = Looper.myLooper();
        audioattributes = looper;
        if(looper == null)
            audioattributes = Looper.getMainLooper();
        l = audioformat.getSampleRate();
        i1 = l;
        if(l == 0)
            i1 = 0;
        j1 = 0;
        if((audioformat.getPropertySetMask() & 8) != 0)
            j1 = audioformat.getChannelIndexMask();
        l = 0;
        if((audioformat.getPropertySetMask() & 4) == 0) goto _L2; else goto _L1
_L1:
        l = audioformat.getChannelMask();
_L4:
        int k1 = 1;
        if((audioformat.getPropertySetMask() & 1) != 0)
            k1 = audioformat.getEncoding();
        audioParamCheck(i1, l, j1, k1, j);
        mStreamType = -1;
        audioBuffSizeCheck(i);
        mInitializationLooper = audioattributes;
        if(k < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid audio session ID: ").append(k).toString());
        break; /* Loop/switch isn't completed */
_L2:
        if(j1 == 0)
            l = 12;
        if(true) goto _L4; else goto _L3
_L3:
        audioattributes = new int[1];
        audioattributes[0] = mSampleRate;
        audioformat = new int[1];
        audioformat[0] = k;
        i = native_setup(new WeakReference(this), mAttributes, audioattributes, mChannelMask, mChannelIndexMask, mAudioFormat, mNativeBufferSizeInBytes, mDataLoadMode, audioformat, 0L);
        if(i != 0)
        {
            loge((new StringBuilder()).append("Error code ").append(i).append(" when initializing AudioTrack.").toString());
            return;
        }
        mSampleRate = audioattributes[0];
        mSessionId = audioformat[0];
        if((mAttributes.getFlags() & 0x10) != 0)
        {
            if(AudioFormat.isEncodingLinearFrames(mAudioFormat))
                i = mChannelCount * AudioFormat.getBytesPerSample(mAudioFormat);
            else
                i = 1;
            mOffset = (int)Math.ceil(20F / (float)i) * i;
        }
        if(mDataLoadMode == 0)
            mState = 2;
        else
            mState = 1;
        baseRegisterPlayer();
        return;
    }

    private void audioBuffSizeCheck(int i)
    {
        int j;
        if(AudioFormat.isEncodingLinearFrames(mAudioFormat))
            j = mChannelCount * AudioFormat.getBytesPerSample(mAudioFormat);
        else
            j = 1;
        if(i % j != 0 || i < 1)
        {
            throw new IllegalArgumentException("Invalid audio buffer size.");
        } else
        {
            mNativeBufferSizeInBytes = i;
            mNativeBufferSizeInFrames = i / j;
            return;
        }
    }

    private void audioParamCheck(int i, int j, int k, int l, int i1)
    {
        if((i < 4000 || i > 0x2ee00) && i != 0)
            throw new IllegalArgumentException((new StringBuilder()).append(i).append("Hz is not a supported sample rate.").toString());
        mSampleRate = i;
        if(l == 13 && j != 12)
            throw new IllegalArgumentException("ENCODING_IEC61937 must be configured as CHANNEL_OUT_STEREO");
        mChannelConfiguration = j;
        j;
        JVM INSTR lookupswitch 5: default 132
    //                   1: 197
    //                   2: 197
    //                   3: 210
    //                   4: 197
    //                   12: 210;
           goto _L1 _L2 _L2 _L3 _L2 _L3
_L1:
        if(j == 0 && k != 0)
        {
            mChannelCount = 0;
        } else
        {
            if(!isMultichannelConfigSupported(j))
                throw new IllegalArgumentException("Unsupported channel configuration.");
            mChannelMask = j;
            mChannelCount = AudioFormat.channelCountFromOutChannelMask(j);
        }
_L5:
        mChannelIndexMask = k;
        if(mChannelIndexMask == 0)
            break MISSING_BLOCK_LABEL_275;
        if(((1 << CHANNEL_COUNT_MAX) - 1 & k) != 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported channel index configuration ").append(k).toString());
        break; /* Loop/switch isn't completed */
_L2:
        mChannelCount = 1;
        mChannelMask = 4;
        continue; /* Loop/switch isn't completed */
_L3:
        mChannelCount = 2;
        mChannelMask = 12;
        if(true) goto _L5; else goto _L4
_L4:
        i = Integer.bitCount(k);
        if(mChannelCount == 0)
            mChannelCount = i;
        else
        if(mChannelCount != i)
            throw new IllegalArgumentException("Channel count must match");
        i = l;
        if(l == 1)
            i = 2;
        if(!AudioFormat.isPublicEncoding(i))
            throw new IllegalArgumentException("Unsupported audio encoding.");
        for(mAudioFormat = i; i1 != 1 && i1 != 0 || i1 != 1 && AudioFormat.isEncodingLinearPcm(mAudioFormat) ^ true;)
            throw new IllegalArgumentException("Invalid mode.");

        mDataLoadMode = i1;
        return;
    }

    private void broadcastRoutingChange()
    {
        AudioManager.resetAudioPortGeneration();
        ArrayMap arraymap = mRoutingChangeListeners;
        arraymap;
        JVM INSTR monitorenter ;
        Iterator iterator = mRoutingChangeListeners.values().iterator();
_L2:
        Handler handler;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_66;
            handler = ((NativeRoutingEventHandlerDelegate)iterator.next()).getHandler();
        } while(handler == null);
        handler.sendEmptyMessage(1000);
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
        arraymap;
        JVM INSTR monitorexit ;
    }

    private static float clampGainOrLevel(float f)
    {
        if(Float.isNaN(f))
            throw new IllegalArgumentException();
        if(f >= 0.0F) goto _L2; else goto _L1
_L1:
        float f1 = 0.0F;
_L4:
        return f1;
_L2:
        f1 = f;
        if(f > 1.0F)
            f1 = 1.0F;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static float getMaxVolume()
    {
        return 1.0F;
    }

    public static int getMinBufferSize(int i, int j, int k)
    {
        j;
        JVM INSTR lookupswitch 4: default 44
    //                   2: 60
    //                   3: 78
    //                   4: 60
    //                   12: 78;
           goto _L1 _L2 _L3 _L2 _L3
_L1:
        if(!isMultichannelConfigSupported(j))
        {
            loge("getMinBufferSize(): Invalid channel configuration.");
            return -2;
        }
        break; /* Loop/switch isn't completed */
_L2:
        for(j = 1; !AudioFormat.isPublicEncoding(k); j = AudioFormat.channelCountFromOutChannelMask(j))
        {
            loge("getMinBufferSize(): Invalid audio format.");
            return -2;
        }

        break MISSING_BLOCK_LABEL_91;
_L3:
        j = 2;
          goto _L2
        if(i < 4000 || i > 0x2ee00)
        {
            loge((new StringBuilder()).append("getMinBufferSize(): ").append(i).append(" Hz is not a supported sample rate.").toString());
            return -2;
        }
        i = native_get_min_buff_size(i, j, k);
        if(i <= 0)
        {
            loge("getMinBufferSize(): error querying hardware");
            return -1;
        } else
        {
            return i;
        }
    }

    public static float getMinVolume()
    {
        return 0.0F;
    }

    public static int getNativeOutputSampleRate(int i)
    {
        return native_get_output_sample_rate(i);
    }

    private static boolean isMultichannelConfigSupported(int i)
    {
        if((i & 0x1cfc) != i)
        {
            loge("Channel configuration features unsupported channels");
            return false;
        }
        int j = AudioFormat.channelCountFromOutChannelMask(i);
        if(j > CHANNEL_COUNT_MAX)
        {
            loge((new StringBuilder()).append("Channel configuration contains too many channels ").append(j).append(">").append(CHANNEL_COUNT_MAX).toString());
            return false;
        }
        if((i & 0xc) != 12)
        {
            loge("Front channels must be present in multichannel configurations");
            return false;
        }
        if((i & 0xc0) != 0 && (i & 0xc0) != 192)
        {
            loge("Rear channels can't be used independently");
            return false;
        }
        if((i & 0x1800) != 0 && (i & 0x1800) != 6144)
        {
            loge("Side channels can't be used independently");
            return false;
        } else
        {
            return true;
        }
    }

    private static void logd(String s)
    {
        Log.d("android.media.AudioTrack", s);
    }

    private static void loge(String s)
    {
        Log.e("android.media.AudioTrack", s);
    }

    private native int native_applyVolumeShaper(VolumeShaper.Configuration configuration, VolumeShaper.Operation operation);

    private final native int native_attachAuxEffect(int i);

    private final native void native_disableDeviceCallback();

    private final native void native_enableDeviceCallback();

    private final native void native_finalize();

    private final native void native_flush();

    private final native int native_getRoutedDeviceId();

    private native VolumeShaper.State native_getVolumeShaperState(int i);

    private static native int native_get_FCC_8();

    private final native int native_get_buffer_capacity_frames();

    private final native int native_get_buffer_size_frames();

    private final native int native_get_flags();

    private final native int native_get_latency();

    private final native int native_get_marker_pos();

    private static final native int native_get_min_buff_size(int i, int j, int k);

    private static final native int native_get_output_sample_rate(int i);

    private final native PlaybackParams native_get_playback_params();

    private final native int native_get_playback_rate();

    private final native int native_get_pos_update_period();

    private final native int native_get_position();

    private final native int native_get_timestamp(long al[]);

    private final native int native_get_underrun_count();

    private final native void native_pause();

    private final native int native_reload_static();

    private final native int native_setAuxEffectSendLevel(float f);

    private final native boolean native_setOutputDevice(int i);

    private final native void native_setVolume(float f, float f1);

    private final native int native_set_buffer_size_frames(int i);

    private final native int native_set_loop(int i, int j, int k);

    private final native int native_set_marker_pos(int i);

    private final native void native_set_playback_params(PlaybackParams playbackparams);

    private final native int native_set_playback_rate(int i);

    private final native int native_set_pos_update_period(int i);

    private final native int native_set_position(int i);

    private final native int native_setup(Object obj, Object obj1, int ai[], int i, int j, int k, int l, 
            int i1, int ai1[], long l1);

    private final native void native_start();

    private final native void native_stop();

    private final native int native_write_byte(byte abyte0[], int i, int j, int k, boolean flag);

    private final native int native_write_float(float af[], int i, int j, int k, boolean flag);

    private final native int native_write_native_bytes(Object obj, int i, int j, int k, boolean flag);

    private final native int native_write_short(short aword0[], int i, int j, int k, boolean flag);

    private static void postEventFromNative(Object obj, int i, int j, int k, Object obj1)
    {
        obj = (AudioTrack)((WeakReference)obj).get();
        if(obj == null)
            return;
        if(i == 1000)
        {
            ((AudioTrack) (obj)).broadcastRoutingChange();
            return;
        }
        obj = ((AudioTrack) (obj)).mEventHandlerDelegate;
        if(obj != null)
        {
            obj = ((NativePositionEventHandlerDelegate) (obj)).getHandler();
            if(obj != null)
                ((Handler) (obj)).sendMessage(((Handler) (obj)).obtainMessage(i, j, k, obj1));
        }
    }

    private static boolean shouldEnablePowerSaving(AudioAttributes audioattributes, AudioFormat audioformat, int i, int j)
    {
        while(audioattributes != null && (audioattributes.getAllFlags() != 0 || audioattributes.getUsage() != 1 || audioattributes.getContentType() != 0 && audioattributes.getContentType() != 2 && audioattributes.getContentType() != 3)) 
            return false;
        while(audioformat == null || audioformat.getSampleRate() == 0 || AudioFormat.isEncodingLinearPcm(audioformat.getEncoding()) ^ true || AudioFormat.isValidEncoding(audioformat.getEncoding()) ^ true || audioformat.getChannelCount() < 1) 
            return false;
        if(j != 1)
            return false;
        if(i != 0)
        {
            long l = ((long)audioformat.getChannelCount() * 100L * (long)AudioFormat.getBytesPerSample(audioformat.getEncoding()) * (long)audioformat.getSampleRate()) / 1000L;
            if((long)i < l)
                return false;
        }
        return true;
    }

    private void startImpl()
    {
        Object obj = mPlayStateLock;
        obj;
        JVM INSTR monitorenter ;
        baseStart();
        native_start();
        mPlayState = 3;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void testDisableNativeRoutingCallbacksLocked()
    {
        if(mRoutingChangeListeners.size() == 0)
            native_disableDeviceCallback();
    }

    private void testEnableNativeRoutingCallbacksLocked()
    {
        if(mRoutingChangeListeners.size() == 0)
            native_enableDeviceCallback();
    }

    public void addOnRoutingChangedListener(AudioRouting.OnRoutingChangedListener onroutingchangedlistener, Handler handler)
    {
        ArrayMap arraymap = mRoutingChangeListeners;
        arraymap;
        JVM INSTR monitorenter ;
        if(onroutingchangedlistener == null) goto _L2; else goto _L1
_L1:
        if(!(mRoutingChangeListeners.containsKey(onroutingchangedlistener) ^ true)) goto _L2; else goto _L3
_L3:
        ArrayMap arraymap1;
        NativeRoutingEventHandlerDelegate nativeroutingeventhandlerdelegate;
        testEnableNativeRoutingCallbacksLocked();
        arraymap1 = mRoutingChangeListeners;
        nativeroutingeventhandlerdelegate = JVM INSTR new #20  <Class AudioTrack$NativeRoutingEventHandlerDelegate>;
        if(handler == null) goto _L5; else goto _L4
_L4:
        nativeroutingeventhandlerdelegate.this. NativeRoutingEventHandlerDelegate(this, onroutingchangedlistener, handler);
        arraymap1.put(onroutingchangedlistener, nativeroutingeventhandlerdelegate);
_L2:
        arraymap;
        JVM INSTR monitorexit ;
        return;
_L5:
        handler = new Handler(mInitializationLooper);
        if(true) goto _L4; else goto _L6
_L6:
        onroutingchangedlistener;
        throw onroutingchangedlistener;
    }

    public void addOnRoutingChangedListener(OnRoutingChangedListener onroutingchangedlistener, Handler handler)
    {
        addOnRoutingChangedListener(((AudioRouting.OnRoutingChangedListener) (onroutingchangedlistener)), handler);
    }

    public int attachAuxEffect(int i)
    {
        if(mState == 0)
            return -3;
        else
            return native_attachAuxEffect(i);
    }

    public VolumeShaper createVolumeShaper(VolumeShaper.Configuration configuration)
    {
        return new VolumeShaper(configuration, this);
    }

    void deferred_connect(long l)
    {
        if(mState != 1)
        {
            int ai[] = new int[1];
            ai[0] = 0;
            int i = native_setup(new WeakReference(this), null, new int[] {
                0
            }, 0, 0, 0, 0, 0, ai, l);
            if(i != 0)
            {
                loge((new StringBuilder()).append("Error code ").append(i).append(" when initializing AudioTrack.").toString());
                return;
            }
            mSessionId = ai[0];
            mState = 1;
        }
    }

    protected void finalize()
    {
        baseRelease();
        native_finalize();
    }

    public void flush()
    {
        if(mState == 1)
        {
            native_flush();
            mAvSyncHeader = null;
            mAvSyncBytesRemaining = 0;
        }
    }

    public int getAudioFormat()
    {
        return mAudioFormat;
    }

    public int getAudioSessionId()
    {
        return mSessionId;
    }

    public int getBufferCapacityInFrames()
    {
        return native_get_buffer_capacity_frames();
    }

    public int getBufferSizeInFrames()
    {
        return native_get_buffer_size_frames();
    }

    public int getChannelConfiguration()
    {
        return mChannelConfiguration;
    }

    public int getChannelCount()
    {
        return mChannelCount;
    }

    public AudioFormat getFormat()
    {
        AudioFormat.Builder builder = (new AudioFormat.Builder()).setSampleRate(mSampleRate).setEncoding(mAudioFormat);
        if(mChannelConfiguration != 0)
            builder.setChannelMask(mChannelConfiguration);
        if(mChannelIndexMask != 0)
            builder.setChannelIndexMask(mChannelIndexMask);
        return builder.build();
    }

    public int getLatency()
    {
        return native_get_latency();
    }

    protected int getNativeFrameCount()
    {
        return native_get_buffer_capacity_frames();
    }

    public int getNotificationMarkerPosition()
    {
        return native_get_marker_pos();
    }

    public int getPerformanceMode()
    {
        int i = native_get_flags();
        if((i & 4) != 0)
            return 1;
        return (i & 8) == 0 ? 0 : 2;
    }

    public int getPlayState()
    {
        Object obj = mPlayStateLock;
        obj;
        JVM INSTR monitorenter ;
        int i = mPlayState;
        obj;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int getPlaybackHeadPosition()
    {
        return native_get_position();
    }

    public PlaybackParams getPlaybackParams()
    {
        return native_get_playback_params();
    }

    public int getPlaybackRate()
    {
        return native_get_playback_rate();
    }

    public int getPositionNotificationPeriod()
    {
        return native_get_pos_update_period();
    }

    public AudioDeviceInfo getPreferredDevice()
    {
        this;
        JVM INSTR monitorenter ;
        AudioDeviceInfo audiodeviceinfo = mPreferredDevice;
        this;
        JVM INSTR monitorexit ;
        return audiodeviceinfo;
        Exception exception;
        exception;
        throw exception;
    }

    public AudioDeviceInfo getRoutedDevice()
    {
        int i = native_getRoutedDeviceId();
        if(i == 0)
            return null;
        AudioDeviceInfo aaudiodeviceinfo[] = AudioManager.getDevicesStatic(2);
        for(int j = 0; j < aaudiodeviceinfo.length; j++)
            if(aaudiodeviceinfo[j].getId() == i)
                return aaudiodeviceinfo[j];

        return null;
    }

    public int getSampleRate()
    {
        return mSampleRate;
    }

    public int getState()
    {
        return mState;
    }

    public int getStreamType()
    {
        return mStreamType;
    }

    public boolean getTimestamp(AudioTimestamp audiotimestamp)
    {
        if(audiotimestamp == null)
            throw new IllegalArgumentException();
        long al[] = new long[2];
        if(native_get_timestamp(al) != 0)
        {
            return false;
        } else
        {
            audiotimestamp.framePosition = al[0];
            audiotimestamp.nanoTime = al[1];
            return true;
        }
    }

    public int getTimestampWithStatus(AudioTimestamp audiotimestamp)
    {
        if(audiotimestamp == null)
        {
            throw new IllegalArgumentException();
        } else
        {
            long al[] = new long[2];
            int i = native_get_timestamp(al);
            audiotimestamp.framePosition = al[0];
            audiotimestamp.nanoTime = al[1];
            return i;
        }
    }

    public int getUnderrunCount()
    {
        return native_get_underrun_count();
    }

    public final native void native_release();

    public void pause()
        throws IllegalStateException
    {
        if(mState != 1)
            throw new IllegalStateException("pause() called on uninitialized AudioTrack.");
        Object obj = mPlayStateLock;
        obj;
        JVM INSTR monitorenter ;
        native_pause();
        basePause();
        mPlayState = 2;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void play()
        throws IllegalStateException
    {
        if(mState != 1)
            throw new IllegalStateException("play() called on uninitialized AudioTrack.");
        final int delay = getStartDelayMs();
        if(delay == 0)
            startImpl();
        else
            (new Thread() {

                public void run()
                {
                    try
                    {
                        Thread.sleep(delay);
                    }
                    catch(InterruptedException interruptedexception)
                    {
                        interruptedexception.printStackTrace();
                    }
                    baseSetStartDelayMs(0);
                    AudioTrack._2D_wrap2(AudioTrack.this);
_L2:
                    return;
                    IllegalStateException illegalstateexception;
                    illegalstateexception;
                    if(true) goto _L2; else goto _L1
_L1:
                }

                final AudioTrack this$0;
                final int val$delay;

            
            {
                this$0 = AudioTrack.this;
                delay = i;
                super();
            }
            }
).start();
    }

    int playerApplyVolumeShaper(VolumeShaper.Configuration configuration, VolumeShaper.Operation operation)
    {
        return native_applyVolumeShaper(configuration, operation);
    }

    VolumeShaper.State playerGetVolumeShaperState(int i)
    {
        return native_getVolumeShaperState(i);
    }

    void playerPause()
    {
        pause();
    }

    int playerSetAuxEffectSendLevel(boolean flag, float f)
    {
        int i = 0;
        if(flag)
            f = 0.0F;
        if(native_setAuxEffectSendLevel(clampGainOrLevel(f)) != 0)
            i = -1;
        return i;
    }

    void playerSetVolume(boolean flag, float f, float f1)
    {
        float f2 = 0.0F;
        if(flag)
            f = 0.0F;
        f = clampGainOrLevel(f);
        if(flag)
            f1 = f2;
        native_setVolume(f, clampGainOrLevel(f1));
    }

    void playerStart()
    {
        play();
    }

    void playerStop()
    {
        stop();
    }

    public void release()
    {
        try
        {
            stop();
        }
        catch(IllegalStateException illegalstateexception) { }
        baseRelease();
        native_release();
        mState = 0;
    }

    public int reloadStaticData()
    {
        if(mDataLoadMode == 1 || mState != 1)
            return -3;
        else
            return native_reload_static();
    }

    public void removeOnRoutingChangedListener(AudioRouting.OnRoutingChangedListener onroutingchangedlistener)
    {
        ArrayMap arraymap = mRoutingChangeListeners;
        arraymap;
        JVM INSTR monitorenter ;
        if(mRoutingChangeListeners.containsKey(onroutingchangedlistener))
            mRoutingChangeListeners.remove(onroutingchangedlistener);
        testDisableNativeRoutingCallbacksLocked();
        arraymap;
        JVM INSTR monitorexit ;
        return;
        onroutingchangedlistener;
        throw onroutingchangedlistener;
    }

    public void removeOnRoutingChangedListener(OnRoutingChangedListener onroutingchangedlistener)
    {
        removeOnRoutingChangedListener(((AudioRouting.OnRoutingChangedListener) (onroutingchangedlistener)));
    }

    public int setAuxEffectSendLevel(float f)
    {
        if(mState == 0)
            return -3;
        else
            return baseSetAuxEffectSendLevel(f);
    }

    public int setBufferSizeInFrames(int i)
    {
        if(mDataLoadMode == 0 || mState == 0)
            return -3;
        if(i < 0)
            return -2;
        else
            return native_set_buffer_size_frames(i);
    }

    public int setLoopPoints(int i, int j, int k)
    {
        while(mDataLoadMode == 1 || mState == 0 || getPlayState() == 3) 
            return -3;
        break MISSING_BLOCK_LABEL_26;
        while(k != 0 && (i < 0 || i >= mNativeBufferSizeInFrames || i >= j || j > mNativeBufferSizeInFrames)) 
            return -2;
        return native_set_loop(i, j, k);
    }

    public int setNotificationMarkerPosition(int i)
    {
        if(mState == 0)
            return -3;
        else
            return native_set_marker_pos(i);
    }

    public int setPlaybackHeadPosition(int i)
    {
        while(mDataLoadMode == 1 || mState == 0 || getPlayState() == 3) 
            return -3;
        if(i < 0 || i > mNativeBufferSizeInFrames)
            return -2;
        else
            return native_set_position(i);
    }

    public void setPlaybackParams(PlaybackParams playbackparams)
    {
        if(playbackparams == null)
        {
            throw new IllegalArgumentException("params is null");
        } else
        {
            native_set_playback_params(playbackparams);
            return;
        }
    }

    public void setPlaybackPositionUpdateListener(OnPlaybackPositionUpdateListener onplaybackpositionupdatelistener)
    {
        setPlaybackPositionUpdateListener(onplaybackpositionupdatelistener, null);
    }

    public void setPlaybackPositionUpdateListener(OnPlaybackPositionUpdateListener onplaybackpositionupdatelistener, Handler handler)
    {
        if(onplaybackpositionupdatelistener != null)
            mEventHandlerDelegate = new NativePositionEventHandlerDelegate(this, onplaybackpositionupdatelistener, handler);
        else
            mEventHandlerDelegate = null;
    }

    public int setPlaybackRate(int i)
    {
        if(mState != 1)
            return -3;
        if(i <= 0)
            return -2;
        else
            return native_set_playback_rate(i);
    }

    public int setPositionNotificationPeriod(int i)
    {
        if(mState == 0)
            return -3;
        else
            return native_set_pos_update_period(i);
    }

    public boolean setPreferredDevice(AudioDeviceInfo audiodeviceinfo)
    {
        if(audiodeviceinfo != null && audiodeviceinfo.isSink() ^ true)
            return false;
        int i;
        boolean flag;
        if(audiodeviceinfo != null)
            i = audiodeviceinfo.getId();
        else
            i = 0;
        flag = native_setOutputDevice(i);
        if(!flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        mPreferredDevice = audiodeviceinfo;
        this;
        JVM INSTR monitorexit ;
_L2:
        return flag;
        audiodeviceinfo;
        throw audiodeviceinfo;
    }

    protected void setState(int i)
    {
        mState = i;
    }

    public int setStereoVolume(float f, float f1)
    {
        if(mState == 0)
        {
            return -3;
        } else
        {
            baseSetVolume(f, f1);
            return 0;
        }
    }

    public int setVolume(float f)
    {
        return setStereoVolume(f, f);
    }

    public void stop()
        throws IllegalStateException
    {
        if(mState != 1)
            throw new IllegalStateException("stop() called on uninitialized AudioTrack.");
        Object obj = mPlayStateLock;
        obj;
        JVM INSTR monitorenter ;
        native_stop();
        baseStop();
        mPlayState = 1;
        mAvSyncHeader = null;
        mAvSyncBytesRemaining = 0;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public int write(ByteBuffer bytebuffer, int i, int j)
    {
        boolean flag = false;
        boolean flag1 = false;
        if(mState == 0)
        {
            Log.e("android.media.AudioTrack", "AudioTrack.write() called in invalid state STATE_UNINITIALIZED");
            return -3;
        }
        if(j != 0 && j != 1)
        {
            Log.e("android.media.AudioTrack", "AudioTrack.write() called with invalid blocking mode");
            return -2;
        }
        while(bytebuffer == null || i < 0 || i > bytebuffer.remaining()) 
        {
            Log.e("android.media.AudioTrack", (new StringBuilder()).append("AudioTrack.write() called with invalid size (").append(i).append(") value").toString());
            return -2;
        }
        if(bytebuffer.isDirect())
        {
            int k = bytebuffer.position();
            int i1 = mAudioFormat;
            if(j == 0)
                flag1 = true;
            i = native_write_native_bytes(bytebuffer, k, i, i1, flag1);
        } else
        {
            byte abyte0[] = NioUtils.unsafeArray(bytebuffer);
            int l = NioUtils.unsafeArrayOffset(bytebuffer);
            int k1 = bytebuffer.position();
            int j1 = mAudioFormat;
            boolean flag2 = flag;
            if(j == 0)
                flag2 = true;
            i = native_write_byte(abyte0, k1 + l, i, j1, flag2);
        }
        if(mDataLoadMode == 0 && mState == 2 && i > 0)
            mState = 1;
        if(i > 0)
            bytebuffer.position(bytebuffer.position() + i);
        return i;
    }

    public int write(ByteBuffer bytebuffer, int i, int j, long l)
    {
        if(mState == 0)
        {
            Log.e("android.media.AudioTrack", "AudioTrack.write() called in invalid state STATE_UNINITIALIZED");
            return -3;
        }
        if(j != 0 && j != 1)
        {
            Log.e("android.media.AudioTrack", "AudioTrack.write() called with invalid blocking mode");
            return -2;
        }
        if(mDataLoadMode != 1)
        {
            Log.e("android.media.AudioTrack", "AudioTrack.write() with timestamp called for non-streaming mode track");
            return -3;
        }
        if((mAttributes.getFlags() & 0x10) == 0)
        {
            Log.d("android.media.AudioTrack", "AudioTrack.write() called on a regular AudioTrack. Ignoring pts...");
            return write(bytebuffer, i, j);
        }
        while(bytebuffer == null || i < 0 || i > bytebuffer.remaining()) 
        {
            Log.e("android.media.AudioTrack", (new StringBuilder()).append("AudioTrack.write() called with invalid size (").append(i).append(") value").toString());
            return -2;
        }
        if(mAvSyncHeader == null)
        {
            mAvSyncHeader = ByteBuffer.allocate(mOffset);
            mAvSyncHeader.order(ByteOrder.BIG_ENDIAN);
            mAvSyncHeader.putInt(0x55550002);
        }
        if(mAvSyncBytesRemaining == 0)
        {
            mAvSyncHeader.putInt(4, i);
            mAvSyncHeader.putLong(8, l);
            mAvSyncHeader.putInt(16, mOffset);
            mAvSyncHeader.position(0);
            mAvSyncBytesRemaining = i;
        }
        if(mAvSyncHeader.remaining() != 0)
        {
            int k = write(mAvSyncHeader, mAvSyncHeader.remaining(), j);
            if(k < 0)
            {
                Log.e("android.media.AudioTrack", "AudioTrack.write() could not write timestamp header!");
                mAvSyncHeader = null;
                mAvSyncBytesRemaining = 0;
                return k;
            }
            if(mAvSyncHeader.remaining() > 0)
            {
                Log.v("android.media.AudioTrack", "AudioTrack.write() partial timestamp header written.");
                return 0;
            }
        }
        i = write(bytebuffer, Math.min(mAvSyncBytesRemaining, i), j);
        if(i < 0)
        {
            Log.e("android.media.AudioTrack", "AudioTrack.write() could not write audio data!");
            mAvSyncHeader = null;
            mAvSyncBytesRemaining = 0;
            return i;
        } else
        {
            mAvSyncBytesRemaining = mAvSyncBytesRemaining - i;
            return i;
        }
    }

    public int write(byte abyte0[], int i, int j)
    {
        return write(abyte0, i, j, 0);
    }

    public int write(byte abyte0[], int i, int j, int k)
    {
        boolean flag = false;
        if(mState == 0 || mAudioFormat == 4)
            return -3;
        if(k != 0 && k != 1)
        {
            Log.e("android.media.AudioTrack", "AudioTrack.write() called with invalid blocking mode");
            return -2;
        }
        while(abyte0 == null || i < 0 || j < 0 || i + j < 0 || i + j > abyte0.length) 
            return -2;
        int l = mAudioFormat;
        if(k == 0)
            flag = true;
        i = native_write_byte(abyte0, i, j, l, flag);
        if(mDataLoadMode == 0 && mState == 2 && i > 0)
            mState = 1;
        return i;
    }

    public int write(float af[], int i, int j, int k)
    {
        boolean flag = false;
        if(mState == 0)
        {
            Log.e("android.media.AudioTrack", "AudioTrack.write() called in invalid state STATE_UNINITIALIZED");
            return -3;
        }
        if(mAudioFormat != 4)
        {
            Log.e("android.media.AudioTrack", "AudioTrack.write(float[] ...) requires format ENCODING_PCM_FLOAT");
            return -3;
        }
        if(k != 0 && k != 1)
        {
            Log.e("android.media.AudioTrack", "AudioTrack.write() called with invalid blocking mode");
            return -2;
        }
        while(af == null || i < 0 || j < 0 || i + j < 0 || i + j > af.length) 
        {
            Log.e("android.media.AudioTrack", "AudioTrack.write() called with invalid array, offset, or size");
            return -2;
        }
        int l = mAudioFormat;
        if(k == 0)
            flag = true;
        i = native_write_float(af, i, j, l, flag);
        if(mDataLoadMode == 0 && mState == 2 && i > 0)
            mState = 1;
        return i;
    }

    public int write(short aword0[], int i, int j)
    {
        return write(aword0, i, j, 0);
    }

    public int write(short aword0[], int i, int j, int k)
    {
        boolean flag = false;
        if(mState == 0 || mAudioFormat == 4)
            return -3;
        if(k != 0 && k != 1)
        {
            Log.e("android.media.AudioTrack", "AudioTrack.write() called with invalid blocking mode");
            return -2;
        }
        while(aword0 == null || i < 0 || j < 0 || i + j < 0 || i + j > aword0.length) 
            return -2;
        int l = mAudioFormat;
        if(k == 0)
            flag = true;
        i = native_write_short(aword0, i, j, l, flag);
        if(mDataLoadMode == 0 && mState == 2 && i > 0)
            mState = 1;
        return i;
    }

    private static final int AUDIO_OUTPUT_FLAG_DEEP_BUFFER = 8;
    private static final int AUDIO_OUTPUT_FLAG_FAST = 4;
    public static final int CHANNEL_COUNT_MAX = native_get_FCC_8();
    public static final int ERROR = -1;
    public static final int ERROR_BAD_VALUE = -2;
    public static final int ERROR_DEAD_OBJECT = -6;
    public static final int ERROR_INVALID_OPERATION = -3;
    private static final int ERROR_NATIVESETUP_AUDIOSYSTEM = -16;
    private static final int ERROR_NATIVESETUP_INVALIDCHANNELMASK = -17;
    private static final int ERROR_NATIVESETUP_INVALIDFORMAT = -18;
    private static final int ERROR_NATIVESETUP_INVALIDSTREAMTYPE = -19;
    private static final int ERROR_NATIVESETUP_NATIVEINITFAILED = -20;
    public static final int ERROR_WOULD_BLOCK = -7;
    private static final float GAIN_MAX = 1F;
    private static final float GAIN_MIN = 0F;
    private static final float HEADER_V2_SIZE_BYTES = 20F;
    public static final int MODE_STATIC = 0;
    public static final int MODE_STREAM = 1;
    private static final int NATIVE_EVENT_MARKER = 3;
    private static final int NATIVE_EVENT_NEW_POS = 4;
    public static final int PERFORMANCE_MODE_LOW_LATENCY = 1;
    public static final int PERFORMANCE_MODE_NONE = 0;
    public static final int PERFORMANCE_MODE_POWER_SAVING = 2;
    public static final int PLAYSTATE_PAUSED = 2;
    public static final int PLAYSTATE_PLAYING = 3;
    public static final int PLAYSTATE_STOPPED = 1;
    public static final int STATE_INITIALIZED = 1;
    public static final int STATE_NO_STATIC_DATA = 2;
    public static final int STATE_UNINITIALIZED = 0;
    public static final int SUCCESS = 0;
    private static final int SUPPORTED_OUT_CHANNELS = 7420;
    private static final String TAG = "android.media.AudioTrack";
    public static final int WRITE_BLOCKING = 0;
    public static final int WRITE_NON_BLOCKING = 1;
    private int mAudioFormat;
    private int mAvSyncBytesRemaining;
    private ByteBuffer mAvSyncHeader;
    private int mChannelConfiguration;
    private int mChannelCount;
    private int mChannelIndexMask;
    private int mChannelMask;
    private int mDataLoadMode;
    private NativePositionEventHandlerDelegate mEventHandlerDelegate;
    private final Looper mInitializationLooper;
    private long mJniData;
    private int mNativeBufferSizeInBytes;
    private int mNativeBufferSizeInFrames;
    protected long mNativeTrackInJavaObj;
    private int mOffset;
    private int mPlayState;
    private final Object mPlayStateLock;
    private AudioDeviceInfo mPreferredDevice;
    private ArrayMap mRoutingChangeListeners;
    private int mSampleRate;
    private int mSessionId;
    private int mState;
    private int mStreamType;


    // Unreferenced inner class android/media/AudioTrack$NativePositionEventHandlerDelegate$1

/* anonymous class */
    class NativePositionEventHandlerDelegate._cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            if(track == null)
                return;
            message.what;
            JVM INSTR tableswitch 3 4: default 36
        //                       3 62
        //                       4 85;
               goto _L1 _L2 _L3
_L1:
            AudioTrack._2D_wrap1((new StringBuilder()).append("Unknown native event type: ").append(message.what).toString());
_L5:
            return;
_L2:
            if(listener != null)
                listener.onMarkerReached(track);
            continue; /* Loop/switch isn't completed */
_L3:
            if(listener != null)
                listener.onPeriodicNotification(track);
            if(true) goto _L5; else goto _L4
_L4:
        }

        final NativePositionEventHandlerDelegate this$1;
        final OnPlaybackPositionUpdateListener val$listener;
        final AudioTrack val$track;

            
            {
                this$1 = final_nativepositioneventhandlerdelegate;
                track = AudioTrack.this;
                listener = onplaybackpositionupdatelistener;
                super(final_looper);
            }
    }


    // Unreferenced inner class android/media/AudioTrack$NativeRoutingEventHandlerDelegate$1

/* anonymous class */
    class NativeRoutingEventHandlerDelegate._cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            if(track == null)
                return;
            message.what;
            JVM INSTR tableswitch 1000 1000: default 32
        //                       1000 58;
               goto _L1 _L2
_L1:
            AudioTrack._2D_wrap1((new StringBuilder()).append("Unknown native event type: ").append(message.what).toString());
_L4:
            return;
_L2:
            if(listener != null)
                listener.onRoutingChanged(track);
            if(true) goto _L4; else goto _L3
_L3:
        }

        final NativeRoutingEventHandlerDelegate this$1;
        final AudioRouting.OnRoutingChangedListener val$listener;
        final AudioTrack val$track;

            
            {
                this$1 = final_nativeroutingeventhandlerdelegate;
                track = AudioTrack.this;
                listener = onroutingchangedlistener;
                super(final_looper);
            }
    }

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.app.ActivityThread;
import android.os.*;
import android.util.*;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package android.media:
//            AudioRouting, AudioAttributes, AudioFormat, MediaRecorder, 
//            AudioManager, IAudioService, AudioDeviceInfo, MediaSyncEvent, 
//            AudioTimestamp

public class AudioRecord
    implements AudioRouting
{
    public static class Builder
    {

        public AudioRecord build()
            throws UnsupportedOperationException
        {
            if(mFormat != null) goto _L2; else goto _L1
_L1:
            mFormat = (new AudioFormat.Builder()).setEncoding(2).setChannelMask(16).build();
_L4:
            Object obj;
            if(mAttributes == null)
                mAttributes = (new AudioAttributes.Builder()).setInternalCapturePreset(0).build();
            try
            {
                if(mBufferSizeInBytes == 0)
                {
                    int i = mFormat.getChannelCount();
                    AudioFormat audioformat = mFormat;
                    mBufferSizeInBytes = i * AudioFormat.getBytesPerSample(mFormat.getEncoding());
                }
                obj = JVM INSTR new #6   <Class AudioRecord>;
                ((AudioRecord) (obj)).AudioRecord(mAttributes, mFormat, mBufferSizeInBytes, mSessionId);
                if(((AudioRecord) (obj)).getState() == 0)
                {
                    obj = JVM INSTR new #25  <Class UnsupportedOperationException>;
                    ((UnsupportedOperationException) (obj)).UnsupportedOperationException("Cannot create AudioRecord");
                    throw obj;
                }
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                throw new UnsupportedOperationException(((IllegalArgumentException) (obj)).getMessage());
            }
            break; /* Loop/switch isn't completed */
_L2:
            if(mFormat.getEncoding() == 0)
                mFormat = (new AudioFormat.Builder(mFormat)).setEncoding(2).build();
            if(mFormat.getChannelMask() == 0 && mFormat.getChannelIndexMask() == 0)
                mFormat = (new AudioFormat.Builder(mFormat)).setChannelMask(16).build();
            if(true) goto _L4; else goto _L3
_L3:
            return ((AudioRecord) (obj));
        }

        public Builder setAudioAttributes(AudioAttributes audioattributes)
            throws IllegalArgumentException
        {
            if(audioattributes == null)
                throw new IllegalArgumentException("Illegal null AudioAttributes argument");
            if(audioattributes.getCapturePreset() == -1)
            {
                throw new IllegalArgumentException("No valid capture preset in AudioAttributes argument");
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

        public Builder setAudioSource(int i)
            throws IllegalArgumentException
        {
            if(i < 0 || i > MediaRecorder.getAudioSourceMax())
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid audio source ").append(i).toString());
            } else
            {
                mAttributes = (new AudioAttributes.Builder()).setInternalCapturePreset(i).build();
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

        public Builder setSessionId(int i)
            throws IllegalArgumentException
        {
            if(i < 0)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid session ID ").append(i).toString());
            } else
            {
                mSessionId = i;
                return this;
            }
        }

        private AudioAttributes mAttributes;
        private int mBufferSizeInBytes;
        private AudioFormat mFormat;
        private int mSessionId;

        public Builder()
        {
            mSessionId = 0;
        }
    }

    private class NativeEventHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            Object obj = AudioRecord._2D_get2(AudioRecord.this);
            obj;
            JVM INSTR monitorenter ;
            OnRecordPositionUpdateListener onrecordpositionupdatelistener = AudioRecord._2D_get1(mAudioRecord);
            obj;
            JVM INSTR monitorexit ;
            message.what;
            JVM INSTR tableswitch 2 3: default 48
        //                       2 79
        //                       3 96;
               goto _L1 _L2 _L3
_L1:
            AudioRecord._2D_wrap0((new StringBuilder()).append("Unknown native event type: ").append(message.what).toString());
_L5:
            return;
            message;
            throw message;
_L2:
            if(onrecordpositionupdatelistener != null)
                onrecordpositionupdatelistener.onMarkerReached(mAudioRecord);
            continue; /* Loop/switch isn't completed */
_L3:
            if(onrecordpositionupdatelistener != null)
                onrecordpositionupdatelistener.onPeriodicNotification(mAudioRecord);
            if(true) goto _L5; else goto _L4
_L4:
        }

        private final AudioRecord mAudioRecord;
        final AudioRecord this$0;

        NativeEventHandler(AudioRecord audiorecord1, Looper looper)
        {
            this$0 = AudioRecord.this;
            super(looper);
            mAudioRecord = audiorecord1;
        }
    }

    private class NativeRoutingEventHandlerDelegate
    {

        Handler getHandler()
        {
            return mHandler;
        }

        private final Handler mHandler;
        final AudioRecord this$0;

        NativeRoutingEventHandlerDelegate(AudioRecord audiorecord, AudioRouting.OnRoutingChangedListener onroutingchangedlistener, Handler handler)
        {
            this$0 = AudioRecord.this;
            super();
            if(handler != null)
                final_looper = handler.getLooper();
            else
                final_looper = AudioRecord._2D_get0(AudioRecord.this);
            if(AudioRecord.this != null)
                mHandler = audiorecord. new _cls1(onroutingchangedlistener);
            else
                mHandler = null;
        }
    }

    public static interface OnRecordPositionUpdateListener
    {

        public abstract void onMarkerReached(AudioRecord audiorecord);

        public abstract void onPeriodicNotification(AudioRecord audiorecord);
    }

    public static interface OnRoutingChangedListener
        extends AudioRouting.OnRoutingChangedListener
    {

        public abstract void onRoutingChanged(AudioRecord audiorecord);

        public void onRoutingChanged(AudioRouting audiorouting)
        {
            if(audiorouting instanceof AudioRecord)
                onRoutingChanged((AudioRecord)audiorouting);
        }
    }


    static Looper _2D_get0(AudioRecord audiorecord)
    {
        return audiorecord.mInitializationLooper;
    }

    static OnRecordPositionUpdateListener _2D_get1(AudioRecord audiorecord)
    {
        return audiorecord.mPositionListener;
    }

    static Object _2D_get2(AudioRecord audiorecord)
    {
        return audiorecord.mPositionListenerLock;
    }

    static void _2D_wrap0(String s)
    {
        loge(s);
    }

    public AudioRecord(int i, int j, int k, int l, int i1)
        throws IllegalArgumentException
    {
        this((new AudioAttributes.Builder()).setInternalCapturePreset(i).build(), (new AudioFormat.Builder()).setChannelMask(getChannelMaskFromLegacyConfig(k, true)).setEncoding(l).setSampleRate(j).build(), i1, 0);
    }

    AudioRecord(long l)
    {
        mState = 0;
        mRecordingState = 1;
        mRecordingStateLock = new Object();
        mPositionListener = null;
        mPositionListenerLock = new Object();
        mEventHandler = null;
        mInitializationLooper = null;
        mNativeBufferSizeInBytes = 0;
        mSessionId = 0;
        mIsSubmixFullVolume = false;
        mICallBack = new Binder();
        mRoutingChangeListeners = new ArrayMap();
        mPreferredDevice = null;
        mNativeRecorderInJavaObj = 0L;
        mNativeCallbackCookie = 0L;
        mNativeDeviceCallback = 0L;
        if(l != 0L)
            deferred_connect(l);
        else
            mState = 0;
    }

    public AudioRecord(AudioAttributes audioattributes, AudioFormat audioformat, int i, int j)
        throws IllegalArgumentException
    {
        mState = 0;
        mRecordingState = 1;
        mRecordingStateLock = new Object();
        mPositionListener = null;
        mPositionListenerLock = new Object();
        mEventHandler = null;
        mInitializationLooper = null;
        mNativeBufferSizeInBytes = 0;
        mSessionId = 0;
        mIsSubmixFullVolume = false;
        mICallBack = new Binder();
        mRoutingChangeListeners = new ArrayMap();
        mPreferredDevice = null;
        mRecordingState = 1;
        if(audioattributes == null)
            throw new IllegalArgumentException("Illegal null AudioAttributes");
        if(audioformat == null)
            throw new IllegalArgumentException("Illegal null AudioFormat");
        Looper looper = Looper.myLooper();
        mInitializationLooper = looper;
        if(looper == null)
            mInitializationLooper = Looper.getMainLooper();
        int k;
        int l;
        if(audioattributes.getCapturePreset() == 8)
        {
            AudioAttributes.Builder builder = new AudioAttributes.Builder();
            for(Iterator iterator = audioattributes.getTags().iterator(); iterator.hasNext();)
            {
                String s = (String)iterator.next();
                if(s.equalsIgnoreCase("fixedVolume"))
                {
                    mIsSubmixFullVolume = true;
                    Log.v("android.media.AudioRecord", "Will record from REMOTE_SUBMIX at full fixed volume");
                } else
                {
                    builder.addTag(s);
                }
            }

            builder.setInternalCapturePreset(audioattributes.getCapturePreset());
            mAudioAttributes = builder.build();
        } else
        {
            mAudioAttributes = audioattributes;
        }
        k = audioformat.getSampleRate();
        l = k;
        if(k == 0)
            l = 0;
        k = 1;
        if((audioformat.getPropertySetMask() & 1) != 0)
            k = audioformat.getEncoding();
        audioParamCheck(audioattributes.getCapturePreset(), l, k);
        if((audioformat.getPropertySetMask() & 8) != 0)
        {
            mChannelIndexMask = audioformat.getChannelIndexMask();
            mChannelCount = audioformat.getChannelCount();
        }
        if((audioformat.getPropertySetMask() & 4) != 0)
        {
            mChannelMask = getChannelMaskFromLegacyConfig(audioformat.getChannelMask(), false);
            mChannelCount = audioformat.getChannelCount();
        } else
        if(mChannelIndexMask == 0)
        {
            mChannelMask = getChannelMaskFromLegacyConfig(1, false);
            mChannelCount = AudioFormat.channelCountFromInChannelMask(mChannelMask);
        }
        audioBuffSizeCheck(i);
        audioformat = new int[1];
        audioformat[0] = mSampleRate;
        audioattributes = new int[1];
        audioattributes[0] = j;
        i = native_setup(new WeakReference(this), mAudioAttributes, audioformat, mChannelMask, mChannelIndexMask, mAudioFormat, mNativeBufferSizeInBytes, audioattributes, ActivityThread.currentOpPackageName(), 0L);
        if(i != 0)
        {
            loge((new StringBuilder()).append("Error code ").append(i).append(" when initializing native AudioRecord object.").toString());
            return;
        } else
        {
            mSampleRate = audioformat[0];
            mSessionId = audioattributes[0];
            mState = 1;
            return;
        }
    }

    private void audioBuffSizeCheck(int i)
        throws IllegalArgumentException
    {
        int j = mChannelCount * AudioFormat.getBytesPerSample(mAudioFormat);
        if(i % j != 0 || i < 1)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid audio buffer size ").append(i).append(" (frame size ").append(j).append(")").toString());
        } else
        {
            mNativeBufferSizeInBytes = i;
            return;
        }
    }

    private void audioParamCheck(int i, int j, int k)
        throws IllegalArgumentException
    {
        if(i < 0 || i > MediaRecorder.getAudioSourceMax() && i != 1998 && i != 1999)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid audio source ").append(i).toString());
        mRecordSource = i;
        if((j < 4000 || j > 0x2ee00) && j != 0)
            throw new IllegalArgumentException((new StringBuilder()).append(j).append("Hz is not a supported sample rate.").toString());
        mSampleRate = j;
        k;
        JVM INSTR lookupswitch 10: default 200
    //                   1: 234
    //                   2: 240
    //                   3: 240
    //                   4: 240
    //                   100: 240
    //                   101: 240
    //                   102: 240
    //                   103: 240
    //                   104: 240
    //                   105: 240;
           goto _L1 _L2 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Unsupported sample encoding ").append(k).append(". Should be ENCODING_PCM_8BIT, ENCODING_PCM_16BIT, or ENCODING_PCM_FLOAT.").toString());
_L2:
        mAudioFormat = 2;
_L5:
        return;
_L3:
        mAudioFormat = k;
        if(true) goto _L5; else goto _L4
_L4:
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

    private static int getChannelMaskFromLegacyConfig(int i, boolean flag)
    {
        i;
        JVM INSTR lookupswitch 6: default 60
    //                   1: 71
    //                   2: 71
    //                   3: 99
    //                   12: 99
    //                   16: 71
    //                   48: 105;
           goto _L1 _L2 _L2 _L3 _L3 _L2 _L4
_L4:
        break MISSING_BLOCK_LABEL_105;
_L1:
        throw new IllegalArgumentException("Unsupported channel configuration.");
_L2:
        int j = 16;
_L5:
        if(!flag && (i == 2 || i == 3))
            throw new IllegalArgumentException("Unsupported deprecated configuration.");
        else
            return j;
_L3:
        j = 12;
          goto _L5
        j = i;
          goto _L5
    }

    public static int getMinBufferSize(int i, int j, int k)
    {
        j;
        JVM INSTR lookupswitch 7: default 68
    //                   1: 77
    //                   2: 77
    //                   3: 93
    //                   12: 93
    //                   16: 77
    //                   48: 93
    //                   252: 98;
           goto _L1 _L2 _L2 _L3 _L3 _L2 _L3 _L4
_L1:
        loge("getMinBufferSize(): Invalid channel configuration.");
        return -2;
_L2:
        j = 1;
_L6:
        i = native_get_min_buff_size(i, j, k);
        if(i == 0)
            return -2;
        break; /* Loop/switch isn't completed */
_L3:
        j = 2;
        continue; /* Loop/switch isn't completed */
_L4:
        j = 6;
        if(true) goto _L6; else goto _L5
_L5:
        if(i == -1)
            return -1;
        else
            return i;
    }

    private void handleFullVolumeRec(boolean flag)
    {
        IAudioService iaudioservice;
        if(!mIsSubmixFullVolume)
            return;
        iaudioservice = IAudioService.Stub.asInterface(ServiceManager.getService("audio"));
        iaudioservice.forceRemoteSubmixFullVolume(flag, mICallBack);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("android.media.AudioRecord", "Error talking to AudioService when handling full submix volume", remoteexception);
          goto _L1
    }

    private static void logd(String s)
    {
        Log.d("android.media.AudioRecord", s);
    }

    private static void loge(String s)
    {
        Log.e("android.media.AudioRecord", s);
    }

    private final native void native_disableDeviceCallback();

    private final native void native_enableDeviceCallback();

    private final native void native_finalize();

    private final native int native_getRoutedDeviceId();

    private final native int native_get_buffer_size_in_frames();

    private final native int native_get_marker_pos();

    private static final native int native_get_min_buff_size(int i, int j, int k);

    private final native int native_get_pos_update_period();

    private final native int native_get_timestamp(AudioTimestamp audiotimestamp, int i);

    private final native int native_read_in_byte_array(byte abyte0[], int i, int j, boolean flag);

    private final native int native_read_in_direct_buffer(Object obj, int i, boolean flag);

    private final native int native_read_in_float_array(float af[], int i, int j, boolean flag);

    private final native int native_read_in_short_array(short aword0[], int i, int j, boolean flag);

    private final native boolean native_setInputDevice(int i);

    private final native int native_set_marker_pos(int i);

    private final native int native_set_pos_update_period(int i);

    private final native int native_setup(Object obj, Object obj1, int ai[], int i, int j, int k, int l, 
            int ai1[], String s, long l1);

    private final native int native_start(int i, int j);

    private final native void native_stop();

    private static void postEventFromNative(Object obj, int i, int j, int k, Object obj1)
    {
        obj = (AudioRecord)((WeakReference)obj).get();
        if(obj == null)
            return;
        if(i == 1000)
        {
            ((AudioRecord) (obj)).broadcastRoutingChange();
            return;
        }
        if(((AudioRecord) (obj)).mEventHandler != null)
        {
            obj1 = ((AudioRecord) (obj)).mEventHandler.obtainMessage(i, j, k, obj1);
            ((AudioRecord) (obj)).mEventHandler.sendMessage(((Message) (obj1)));
        }
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

    public void addOnRoutingChangedListener(OnRoutingChangedListener onroutingchangedlistener, Handler handler)
    {
        addOnRoutingChangedListener(((AudioRouting.OnRoutingChangedListener) (onroutingchangedlistener)), handler);
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
        nativeroutingeventhandlerdelegate = JVM INSTR new #14  <Class AudioRecord$NativeRoutingEventHandlerDelegate>;
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

    void deferred_connect(long l)
    {
        if(mState != 1)
        {
            int ai[] = new int[1];
            ai[0] = 0;
            WeakReference weakreference = new WeakReference(this);
            String s = ActivityThread.currentOpPackageName();
            int i = native_setup(weakreference, null, new int[] {
                0
            }, 0, 0, 0, 0, ai, s, l);
            if(i != 0)
            {
                loge((new StringBuilder()).append("Error code ").append(i).append(" when initializing native AudioRecord object.").toString());
                return;
            }
            mSessionId = ai[0];
            mState = 1;
        }
    }

    protected void finalize()
    {
        release();
    }

    public int getAudioFormat()
    {
        return mAudioFormat;
    }

    public int getAudioSessionId()
    {
        return mSessionId;
    }

    public int getAudioSource()
    {
        return mRecordSource;
    }

    public int getBufferSizeInFrames()
    {
        return native_get_buffer_size_in_frames();
    }

    public int getChannelConfiguration()
    {
        return mChannelMask;
    }

    public int getChannelCount()
    {
        return mChannelCount;
    }

    public AudioFormat getFormat()
    {
        AudioFormat.Builder builder = (new AudioFormat.Builder()).setSampleRate(mSampleRate).setEncoding(mAudioFormat);
        if(mChannelMask != 0)
            builder.setChannelMask(mChannelMask);
        if(mChannelIndexMask != 0)
            builder.setChannelIndexMask(mChannelIndexMask);
        return builder.build();
    }

    public int getNotificationMarkerPosition()
    {
        return native_get_marker_pos();
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

    public int getRecordingState()
    {
        Object obj = mRecordingStateLock;
        obj;
        JVM INSTR monitorenter ;
        int i = mRecordingState;
        obj;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public AudioDeviceInfo getRoutedDevice()
    {
        int i = native_getRoutedDeviceId();
        if(i == 0)
            return null;
        AudioDeviceInfo aaudiodeviceinfo[] = AudioManager.getDevicesStatic(1);
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

    public int getTimestamp(AudioTimestamp audiotimestamp, int i)
    {
        if(audiotimestamp == null || i != 1 && i != 0)
            throw new IllegalArgumentException();
        else
            return native_get_timestamp(audiotimestamp, i);
    }

    public final native void native_release();

    public int read(ByteBuffer bytebuffer, int i)
    {
        return read(bytebuffer, i, 0);
    }

    public int read(ByteBuffer bytebuffer, int i, int j)
    {
        boolean flag = true;
        if(mState != 1)
            return -3;
        if(j != 0 && j != 1)
        {
            Log.e("android.media.AudioRecord", "AudioRecord.read() called with invalid blocking mode");
            return -2;
        }
        if(bytebuffer == null || i < 0)
            return -2;
        if(j != 0)
            flag = false;
        return native_read_in_direct_buffer(bytebuffer, i, flag);
    }

    public int read(byte abyte0[], int i, int j)
    {
        return read(abyte0, i, j, 0);
    }

    public int read(byte abyte0[], int i, int j, int k)
    {
        boolean flag = true;
        if(mState != 1 || mAudioFormat == 4)
            return -3;
        if(k != 0 && k != 1)
        {
            Log.e("android.media.AudioRecord", "AudioRecord.read() called with invalid blocking mode");
            return -2;
        }
        while(abyte0 == null || i < 0 || j < 0 || i + j < 0 || i + j > abyte0.length) 
            return -2;
        if(k != 0)
            flag = false;
        return native_read_in_byte_array(abyte0, i, j, flag);
    }

    public int read(float af[], int i, int j, int k)
    {
        boolean flag = true;
        if(mState == 0)
        {
            Log.e("android.media.AudioRecord", "AudioRecord.read() called in invalid state STATE_UNINITIALIZED");
            return -3;
        }
        if(mAudioFormat != 4)
        {
            Log.e("android.media.AudioRecord", "AudioRecord.read(float[] ...) requires format ENCODING_PCM_FLOAT");
            return -3;
        }
        if(k != 0 && k != 1)
        {
            Log.e("android.media.AudioRecord", "AudioRecord.read() called with invalid blocking mode");
            return -2;
        }
        while(af == null || i < 0 || j < 0 || i + j < 0 || i + j > af.length) 
            return -2;
        if(k != 0)
            flag = false;
        return native_read_in_float_array(af, i, j, flag);
    }

    public int read(short aword0[], int i, int j)
    {
        return read(aword0, i, j, 0);
    }

    public int read(short aword0[], int i, int j, int k)
    {
        boolean flag = true;
        if(mState != 1 || mAudioFormat == 4)
            return -3;
        if(k != 0 && k != 1)
        {
            Log.e("android.media.AudioRecord", "AudioRecord.read() called with invalid blocking mode");
            return -2;
        }
        while(aword0 == null || i < 0 || j < 0 || i + j < 0 || i + j > aword0.length) 
            return -2;
        if(k != 0)
            flag = false;
        return native_read_in_short_array(aword0, i, j, flag);
    }

    public void release()
    {
        try
        {
            stop();
        }
        catch(IllegalStateException illegalstateexception) { }
        native_release();
        mState = 0;
    }

    public void removeOnRoutingChangedListener(OnRoutingChangedListener onroutingchangedlistener)
    {
        removeOnRoutingChangedListener(((AudioRouting.OnRoutingChangedListener) (onroutingchangedlistener)));
    }

    public void removeOnRoutingChangedListener(AudioRouting.OnRoutingChangedListener onroutingchangedlistener)
    {
        ArrayMap arraymap = mRoutingChangeListeners;
        arraymap;
        JVM INSTR monitorenter ;
        if(mRoutingChangeListeners.containsKey(onroutingchangedlistener))
        {
            mRoutingChangeListeners.remove(onroutingchangedlistener);
            testDisableNativeRoutingCallbacksLocked();
        }
        arraymap;
        JVM INSTR monitorexit ;
        return;
        onroutingchangedlistener;
        throw onroutingchangedlistener;
    }

    public int setNotificationMarkerPosition(int i)
    {
        if(mState == 0)
            return -3;
        else
            return native_set_marker_pos(i);
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
        if(audiodeviceinfo != null && audiodeviceinfo.isSource() ^ true)
            return false;
        int i;
        boolean flag;
        if(audiodeviceinfo != null)
            i = audiodeviceinfo.getId();
        else
            i = 0;
        flag = native_setInputDevice(i);
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

    public void setRecordPositionUpdateListener(OnRecordPositionUpdateListener onrecordpositionupdatelistener)
    {
        setRecordPositionUpdateListener(onrecordpositionupdatelistener, null);
    }

    public void setRecordPositionUpdateListener(OnRecordPositionUpdateListener onrecordpositionupdatelistener, Handler handler)
    {
        Object obj = mPositionListenerLock;
        obj;
        JVM INSTR monitorenter ;
        mPositionListener = onrecordpositionupdatelistener;
        if(onrecordpositionupdatelistener == null)
            break MISSING_BLOCK_LABEL_69;
        if(handler == null) goto _L2; else goto _L1
_L1:
        onrecordpositionupdatelistener = JVM INSTR new #11  <Class AudioRecord$NativeEventHandler>;
        onrecordpositionupdatelistener.this. NativeEventHandler(this, handler.getLooper());
        mEventHandler = onrecordpositionupdatelistener;
_L3:
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
        onrecordpositionupdatelistener = JVM INSTR new #11  <Class AudioRecord$NativeEventHandler>;
        onrecordpositionupdatelistener.this. NativeEventHandler(this, mInitializationLooper);
        mEventHandler = onrecordpositionupdatelistener;
          goto _L3
        onrecordpositionupdatelistener;
        throw onrecordpositionupdatelistener;
        mEventHandler = null;
          goto _L3
    }

    public void startRecording()
        throws IllegalStateException
    {
        SeempLog.record(70);
        if(mState != 1)
            throw new IllegalStateException("startRecording() called on an uninitialized AudioRecord.");
        Object obj = mRecordingStateLock;
        obj;
        JVM INSTR monitorenter ;
        if(native_start(0, 0) == 0)
        {
            handleFullVolumeRec(true);
            mRecordingState = 3;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void startRecording(MediaSyncEvent mediasyncevent)
        throws IllegalStateException
    {
        SeempLog.record(70);
        if(mState != 1)
            throw new IllegalStateException("startRecording() called on an uninitialized AudioRecord.");
        Object obj = mRecordingStateLock;
        obj;
        JVM INSTR monitorenter ;
        if(native_start(mediasyncevent.getType(), mediasyncevent.getAudioSessionId()) == 0)
        {
            handleFullVolumeRec(true);
            mRecordingState = 3;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        mediasyncevent;
        throw mediasyncevent;
    }

    public void stop()
        throws IllegalStateException
    {
        if(mState != 1)
            throw new IllegalStateException("stop() called on an uninitialized AudioRecord.");
        Object obj = mRecordingStateLock;
        obj;
        JVM INSTR monitorenter ;
        handleFullVolumeRec(false);
        native_stop();
        mRecordingState = 1;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static final int AUDIORECORD_ERROR_SETUP_INVALIDCHANNELMASK = -17;
    private static final int AUDIORECORD_ERROR_SETUP_INVALIDFORMAT = -18;
    private static final int AUDIORECORD_ERROR_SETUP_INVALIDSOURCE = -19;
    private static final int AUDIORECORD_ERROR_SETUP_NATIVEINITFAILED = -20;
    private static final int AUDIORECORD_ERROR_SETUP_ZEROFRAMECOUNT = -16;
    public static final int ERROR = -1;
    public static final int ERROR_BAD_VALUE = -2;
    public static final int ERROR_DEAD_OBJECT = -6;
    public static final int ERROR_INVALID_OPERATION = -3;
    private static final int NATIVE_EVENT_MARKER = 2;
    private static final int NATIVE_EVENT_NEW_POS = 3;
    public static final int READ_BLOCKING = 0;
    public static final int READ_NON_BLOCKING = 1;
    public static final int RECORDSTATE_RECORDING = 3;
    public static final int RECORDSTATE_STOPPED = 1;
    public static final int STATE_INITIALIZED = 1;
    public static final int STATE_UNINITIALIZED = 0;
    public static final String SUBMIX_FIXED_VOLUME = "fixedVolume";
    public static final int SUCCESS = 0;
    private static final String TAG = "android.media.AudioRecord";
    private AudioAttributes mAudioAttributes;
    private int mAudioFormat;
    private int mChannelCount;
    private int mChannelIndexMask;
    private int mChannelMask;
    private NativeEventHandler mEventHandler;
    private final IBinder mICallBack;
    private Looper mInitializationLooper;
    private boolean mIsSubmixFullVolume;
    private int mNativeBufferSizeInBytes;
    private long mNativeCallbackCookie;
    private long mNativeDeviceCallback;
    private long mNativeRecorderInJavaObj;
    private OnRecordPositionUpdateListener mPositionListener;
    private final Object mPositionListenerLock;
    private AudioDeviceInfo mPreferredDevice;
    private int mRecordSource;
    private int mRecordingState;
    private final Object mRecordingStateLock;
    private ArrayMap mRoutingChangeListeners;
    private int mSampleRate;
    private int mSessionId;
    private int mState;

    // Unreferenced inner class android/media/AudioRecord$NativeRoutingEventHandlerDelegate$1

/* anonymous class */
    class NativeRoutingEventHandlerDelegate._cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            if(record == null)
                return;
            message.what;
            JVM INSTR tableswitch 1000 1000: default 32
        //                       1000 58;
               goto _L1 _L2
_L1:
            AudioRecord._2D_wrap0((new StringBuilder()).append("Unknown native event type: ").append(message.what).toString());
_L4:
            return;
_L2:
            if(listener != null)
                listener.onRoutingChanged(record);
            if(true) goto _L4; else goto _L3
_L3:
        }

        final NativeRoutingEventHandlerDelegate this$1;
        final AudioRouting.OnRoutingChangedListener val$listener;
        final AudioRecord val$record;

            
            {
                this$1 = final_nativeroutingeventhandlerdelegate;
                record = AudioRecord.this;
                listener = onroutingchangedlistener;
                super(final_looper);
            }
    }

}

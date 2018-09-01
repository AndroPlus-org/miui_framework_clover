// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.app.ActivityThread;
import android.hardware.Camera;
import android.os.*;
import android.util.Log;
import android.view.Surface;
import java.io.*;
import java.lang.ref.WeakReference;

// Referenced classes of package android.media:
//            CamcorderProfile

public class MediaRecorder
{
    public final class AudioEncoder
    {

        public static final int AAC = 3;
        public static final int AAC_ELD = 5;
        public static final int AMR_NB = 1;
        public static final int AMR_WB = 2;
        public static final int DEFAULT = 0;
        public static final int EVRC = 10;
        public static final int HE_AAC = 4;
        public static final int LPCM = 12;
        public static final int MPEGH = 13;
        public static final int QCELP = 11;
        public static final int VORBIS = 6;
        final MediaRecorder this$0;

        private AudioEncoder()
        {
            this$0 = MediaRecorder.this;
            super();
        }
    }

    public final class AudioSource
    {

        public static final int AUDIO_SOURCE_INVALID = -1;
        public static final int CAMCORDER = 5;
        public static final int DEFAULT = 0;
        public static final int HOTWORD = 1999;
        public static final int MIC = 1;
        public static final int RADIO_TUNER = 1998;
        public static final int REMOTE_SUBMIX = 8;
        public static final int UNPROCESSED = 9;
        public static final int VOICE_CALL = 4;
        public static final int VOICE_COMMUNICATION = 7;
        public static final int VOICE_DOWNLINK = 3;
        public static final int VOICE_RECOGNITION = 6;
        public static final int VOICE_UPLINK = 2;
        final MediaRecorder this$0;

        private AudioSource()
        {
            this$0 = MediaRecorder.this;
            super();
        }
    }

    private class EventHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            if(MediaRecorder._2D_get0(mMediaRecorder) == 0L)
            {
                Log.w("MediaRecorder", "mediarecorder went away with unhandled events");
                return;
            }
            switch(message.what)
            {
            default:
                Log.e("MediaRecorder", (new StringBuilder()).append("Unknown message type ").append(message.what).toString());
                return;

            case 1: // '\001'
            case 100: // 'd'
                if(MediaRecorder._2D_get1(MediaRecorder.this) != null)
                    MediaRecorder._2D_get1(MediaRecorder.this).onError(mMediaRecorder, message.arg1, message.arg2);
                return;

            case 2: // '\002'
            case 101: // 'e'
                break;
            }
            if(MediaRecorder._2D_get2(MediaRecorder.this) != null)
                MediaRecorder._2D_get2(MediaRecorder.this).onInfo(mMediaRecorder, message.arg1, message.arg2);
        }

        private static final int MEDIA_RECORDER_EVENT_ERROR = 1;
        private static final int MEDIA_RECORDER_EVENT_INFO = 2;
        private static final int MEDIA_RECORDER_EVENT_LIST_END = 99;
        private static final int MEDIA_RECORDER_EVENT_LIST_START = 1;
        private static final int MEDIA_RECORDER_TRACK_EVENT_ERROR = 100;
        private static final int MEDIA_RECORDER_TRACK_EVENT_INFO = 101;
        private static final int MEDIA_RECORDER_TRACK_EVENT_LIST_END = 1000;
        private static final int MEDIA_RECORDER_TRACK_EVENT_LIST_START = 100;
        private MediaRecorder mMediaRecorder;
        final MediaRecorder this$0;

        public EventHandler(MediaRecorder mediarecorder1, Looper looper)
        {
            this$0 = MediaRecorder.this;
            super(looper);
            mMediaRecorder = mediarecorder1;
        }
    }

    public static final class MetricsConstants
    {

        public static final String AUDIO_BITRATE = "android.media.mediarecorder.audio-bitrate";
        public static final String AUDIO_CHANNELS = "android.media.mediarecorder.audio-channels";
        public static final String AUDIO_SAMPLERATE = "android.media.mediarecorder.audio-samplerate";
        public static final String AUDIO_TIMESCALE = "android.media.mediarecorder.audio-timescale";
        public static final String CAPTURE_FPS = "android.media.mediarecorder.capture-fps";
        public static final String CAPTURE_FPS_ENABLE = "android.media.mediarecorder.capture-fpsenable";
        public static final String FRAMERATE = "android.media.mediarecorder.frame-rate";
        public static final String HEIGHT = "android.media.mediarecorder.height";
        public static final String MOVIE_TIMESCALE = "android.media.mediarecorder.movie-timescale";
        public static final String ROTATION = "android.media.mediarecorder.rotation";
        public static final String VIDEO_BITRATE = "android.media.mediarecorder.video-bitrate";
        public static final String VIDEO_IFRAME_INTERVAL = "android.media.mediarecorder.video-iframe-interval";
        public static final String VIDEO_LEVEL = "android.media.mediarecorder.video-encoder-level";
        public static final String VIDEO_PROFILE = "android.media.mediarecorder.video-encoder-profile";
        public static final String VIDEO_TIMESCALE = "android.media.mediarecorder.video-timescale";
        public static final String WIDTH = "android.media.mediarecorder.width";

        private MetricsConstants()
        {
        }
    }

    public static interface OnErrorListener
    {

        public abstract void onError(MediaRecorder mediarecorder, int i, int j);
    }

    public static interface OnInfoListener
    {

        public abstract void onInfo(MediaRecorder mediarecorder, int i, int j);
    }

    public final class OutputFormat
    {

        public static final int AAC_ADIF = 5;
        public static final int AAC_ADTS = 6;
        public static final int AMR_NB = 3;
        public static final int AMR_WB = 4;
        public static final int DEFAULT = 0;
        public static final int MPEG_2_TS = 8;
        public static final int MPEG_4 = 2;
        public static final int OUTPUT_FORMAT_RTP_AVP = 7;
        public static final int QCP = 20;
        public static final int RAW_AMR = 3;
        public static final int THREE_GPP = 1;
        public static final int WAVE = 21;
        public static final int WEBM = 9;
        final MediaRecorder this$0;

        private OutputFormat()
        {
            this$0 = MediaRecorder.this;
            super();
        }
    }

    public final class VideoEncoder
    {

        public static final int DEFAULT = 0;
        public static final int H263 = 1;
        public static final int H264 = 2;
        public static final int HEVC = 5;
        public static final int MPEG_4_SP = 3;
        public static final int VP8 = 4;
        final MediaRecorder this$0;

        private VideoEncoder()
        {
            this$0 = MediaRecorder.this;
            super();
        }
    }

    public final class VideoSource
    {

        public static final int CAMERA = 1;
        public static final int DEFAULT = 0;
        public static final int SURFACE = 2;
        final MediaRecorder this$0;

        private VideoSource()
        {
            this$0 = MediaRecorder.this;
            super();
        }
    }


    static long _2D_get0(MediaRecorder mediarecorder)
    {
        return mediarecorder.mNativeContext;
    }

    static OnErrorListener _2D_get1(MediaRecorder mediarecorder)
    {
        return mediarecorder.mOnErrorListener;
    }

    static OnInfoListener _2D_get2(MediaRecorder mediarecorder)
    {
        return mediarecorder.mOnInfoListener;
    }

    public MediaRecorder()
    {
        Object obj = Looper.myLooper();
        if(obj != null)
        {
            mEventHandler = new EventHandler(this, ((Looper) (obj)));
        } else
        {
            Looper looper = Looper.getMainLooper();
            if(looper != null)
                mEventHandler = new EventHandler(this, looper);
            else
                mEventHandler = null;
        }
        obj = ActivityThread.currentPackageName();
        native_setup(new WeakReference(this), ((String) (obj)), ActivityThread.currentOpPackageName());
    }

    private native void _prepare()
        throws IllegalStateException, IOException;

    private native void _setNextOutputFile(FileDescriptor filedescriptor)
        throws IllegalStateException, IOException;

    private native void _setOutputFile(FileDescriptor filedescriptor)
        throws IllegalStateException, IOException;

    public static final int getAudioSourceMax()
    {
        return 9;
    }

    public static boolean isSystemOnlyAudioSource(int i)
    {
        switch(i)
        {
        case 8: // '\b'
        default:
            return true;

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 9: // '\t'
            return false;
        }
    }

    private final native void native_finalize();

    private native PersistableBundle native_getMetrics();

    private static final native void native_init();

    private native void native_reset();

    private final native void native_setInputSurface(Surface surface);

    private final native void native_setup(Object obj, String s, String s1)
        throws IllegalStateException;

    private static void postEventFromNative(Object obj, int i, int j, int k, Object obj1)
    {
        obj = (MediaRecorder)((WeakReference)obj).get();
        if(obj == null)
            return;
        if(((MediaRecorder) (obj)).mEventHandler != null)
        {
            obj1 = ((MediaRecorder) (obj)).mEventHandler.obtainMessage(i, j, k, obj1);
            ((MediaRecorder) (obj)).mEventHandler.sendMessage(((Message) (obj1)));
        }
    }

    private native void setParameter(String s);

    public static final String toLogFriendlyAudioSource(int i)
    {
        switch(i)
        {
        default:
            return (new StringBuilder()).append("unknown source ").append(i).toString();

        case 0: // '\0'
            return "DEFAULT";

        case 1: // '\001'
            return "MIC";

        case 2: // '\002'
            return "VOICE_UPLINK";

        case 3: // '\003'
            return "VOICE_DOWNLINK";

        case 4: // '\004'
            return "VOICE_CALL";

        case 5: // '\005'
            return "CAMCORDER";

        case 6: // '\006'
            return "VOICE_RECOGNITION";

        case 7: // '\007'
            return "VOICE_COMMUNICATION";

        case 8: // '\b'
            return "REMOTE_SUBMIX";

        case 9: // '\t'
            return "UNPROCESSED";

        case 1998: 
            return "RADIO_TUNER";

        case 1999: 
            return "HOTWORD";

        case -1: 
            return "AUDIO_SOURCE_INVALID";
        }
    }

    protected void finalize()
    {
        native_finalize();
    }

    public native int getMaxAmplitude()
        throws IllegalStateException;

    public PersistableBundle getMetrics()
    {
        return native_getMetrics();
    }

    public native Surface getSurface();

    public native void pause()
        throws IllegalStateException;

    public void prepare()
        throws IllegalStateException, IOException
    {
        if(mPath == null) goto _L2; else goto _L1
_L1:
        RandomAccessFile randomaccessfile = new RandomAccessFile(mPath, "rws");
        _setOutputFile(randomaccessfile.getFD());
        randomaccessfile.close();
_L3:
        _prepare();
        return;
        Exception exception1;
        exception1;
        randomaccessfile.close();
        throw exception1;
_L2:
label0:
        {
            if(mFd == null)
                break label0;
            _setOutputFile(mFd);
        }
          goto _L3
        RandomAccessFile randomaccessfile1;
        if(mFile == null)
            break MISSING_BLOCK_LABEL_106;
        randomaccessfile1 = new RandomAccessFile(mFile, "rws");
        _setOutputFile(randomaccessfile1.getFD());
        randomaccessfile1.close();
          goto _L3
        Exception exception;
        exception;
        randomaccessfile1.close();
        throw exception;
        throw new IOException("No valid output file");
    }

    public native void release();

    public void reset()
    {
        native_reset();
        mEventHandler.removeCallbacksAndMessages(null);
    }

    public native void resume()
        throws IllegalStateException;

    public void setAudioChannels(int i)
    {
        if(i <= 0)
        {
            throw new IllegalArgumentException("Number of channels is not positive");
        } else
        {
            setParameter((new StringBuilder()).append("audio-param-number-of-channels=").append(i).toString());
            return;
        }
    }

    public native void setAudioEncoder(int i)
        throws IllegalStateException;

    public void setAudioEncodingBitRate(int i)
    {
        if(i <= 0)
        {
            throw new IllegalArgumentException("Audio encoding bit rate is not positive");
        } else
        {
            setParameter((new StringBuilder()).append("audio-param-encoding-bitrate=").append(i).toString());
            return;
        }
    }

    public void setAudioSamplingRate(int i)
    {
        if(i <= 0)
        {
            throw new IllegalArgumentException("Audio sampling rate is not positive");
        } else
        {
            setParameter((new StringBuilder()).append("audio-param-sampling-rate=").append(i).toString());
            return;
        }
    }

    public native void setAudioSource(int i)
        throws IllegalStateException;

    public void setAuxiliaryOutputFile(FileDescriptor filedescriptor)
    {
        Log.w("MediaRecorder", "setAuxiliaryOutputFile(FileDescriptor) is no longer supported.");
    }

    public void setAuxiliaryOutputFile(String s)
    {
        Log.w("MediaRecorder", "setAuxiliaryOutputFile(String) is no longer supported.");
    }

    public native void setCamera(Camera camera);

    public void setCaptureRate(double d)
    {
        setParameter("time-lapse-enable=1");
        setParameter((new StringBuilder()).append("time-lapse-fps=").append(d).toString());
    }

    public void setInputSurface(Surface surface)
    {
        if(!(surface instanceof MediaCodec.PersistentSurface))
        {
            throw new IllegalArgumentException("not a PersistentSurface");
        } else
        {
            native_setInputSurface(surface);
            return;
        }
    }

    public void setLocation(float f, float f1)
    {
        int i = (int)((double)(f * 10000F) + 0.5D);
        int j = (int)((double)(f1 * 10000F) + 0.5D);
        if(i > 0xdbba0 || i < 0xfff24460)
            throw new IllegalArgumentException((new StringBuilder()).append("Latitude: ").append(f).append(" out of range.").toString());
        if(j > 0x1b7740 || j < 0xffe488c0)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Longitude: ").append(f1).append(" out of range").toString());
        } else
        {
            setParameter((new StringBuilder()).append("param-geotag-latitude=").append(i).toString());
            setParameter((new StringBuilder()).append("param-geotag-longitude=").append(j).toString());
            return;
        }
    }

    public native void setMaxDuration(int i)
        throws IllegalArgumentException;

    public native void setMaxFileSize(long l)
        throws IllegalArgumentException;

    public void setNextOutputFile(File file)
        throws IOException
    {
        file = new RandomAccessFile(file, "rws");
        _setNextOutputFile(file.getFD());
        file.close();
        return;
        Exception exception;
        exception;
        file.close();
        throw exception;
    }

    public void setNextOutputFile(FileDescriptor filedescriptor)
        throws IOException
    {
        _setNextOutputFile(filedescriptor);
    }

    public void setOnErrorListener(OnErrorListener onerrorlistener)
    {
        mOnErrorListener = onerrorlistener;
    }

    public void setOnInfoListener(OnInfoListener oninfolistener)
    {
        mOnInfoListener = oninfolistener;
    }

    public void setOrientationHint(int i)
    {
        if(i != 0 && i != 90 && i != 180 && i != 270)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported angle: ").append(i).toString());
        } else
        {
            setParameter((new StringBuilder()).append("video-param-rotation-angle-degrees=").append(i).toString());
            return;
        }
    }

    public void setOutputFile(File file)
    {
        mPath = null;
        mFd = null;
        mFile = file;
    }

    public void setOutputFile(FileDescriptor filedescriptor)
        throws IllegalStateException
    {
        mPath = null;
        mFile = null;
        mFd = filedescriptor;
    }

    public void setOutputFile(String s)
        throws IllegalStateException
    {
        mFd = null;
        mFile = null;
        mPath = s;
    }

    public native void setOutputFormat(int i)
        throws IllegalStateException;

    public void setPreviewDisplay(Surface surface)
    {
        mSurface = surface;
    }

    public void setProfile(CamcorderProfile camcorderprofile)
    {
        setOutputFormat(camcorderprofile.fileFormat);
        setVideoFrameRate(camcorderprofile.videoFrameRate);
        setVideoSize(camcorderprofile.videoFrameWidth, camcorderprofile.videoFrameHeight);
        setVideoEncodingBitRate(camcorderprofile.videoBitRate);
        setVideoEncoder(camcorderprofile.videoCodec);
        break MISSING_BLOCK_LABEL_44;
        if((camcorderprofile.quality < 1000 || camcorderprofile.quality > 1008) && (camcorderprofile.quality < 10002 || camcorderprofile.quality > 10003))
        {
            setAudioEncodingBitRate(camcorderprofile.audioBitRate);
            setAudioChannels(camcorderprofile.audioChannels);
            setAudioSamplingRate(camcorderprofile.audioSampleRate);
            setAudioEncoder(camcorderprofile.audioCodec);
        }
        return;
    }

    public native void setVideoEncoder(int i)
        throws IllegalStateException;

    public void setVideoEncodingBitRate(int i)
    {
        if(i <= 0)
        {
            throw new IllegalArgumentException("Video encoding bit rate is not positive");
        } else
        {
            setParameter((new StringBuilder()).append("video-param-encoding-bitrate=").append(i).toString());
            return;
        }
    }

    public void setVideoEncodingProfileLevel(int i, int j)
    {
        if(i <= 0)
            throw new IllegalArgumentException("Video encoding profile is not positive");
        if(j <= 0)
        {
            throw new IllegalArgumentException("Video encoding level is not positive");
        } else
        {
            setParameter((new StringBuilder()).append("video-param-encoder-profile=").append(i).toString());
            setParameter((new StringBuilder()).append("video-param-encoder-level=").append(j).toString());
            return;
        }
    }

    public native void setVideoFrameRate(int i)
        throws IllegalStateException;

    public native void setVideoSize(int i, int j)
        throws IllegalStateException;

    public native void setVideoSource(int i)
        throws IllegalStateException;

    public native void start()
        throws IllegalStateException;

    public native void stop()
        throws IllegalStateException;

    public static final int MEDIA_ERROR_SERVER_DIED = 100;
    public static final int MEDIA_RECORDER_ERROR_UNKNOWN = 1;
    public static final int MEDIA_RECORDER_INFO_MAX_DURATION_REACHED = 800;
    public static final int MEDIA_RECORDER_INFO_MAX_FILESIZE_APPROACHING = 802;
    public static final int MEDIA_RECORDER_INFO_MAX_FILESIZE_REACHED = 801;
    public static final int MEDIA_RECORDER_INFO_NEXT_OUTPUT_FILE_STARTED = 803;
    public static final int MEDIA_RECORDER_INFO_UNKNOWN = 1;
    public static final int MEDIA_RECORDER_TRACK_INFO_COMPLETION_STATUS = 1000;
    public static final int MEDIA_RECORDER_TRACK_INFO_DATA_KBYTES = 1009;
    public static final int MEDIA_RECORDER_TRACK_INFO_DURATION_MS = 1003;
    public static final int MEDIA_RECORDER_TRACK_INFO_ENCODED_FRAMES = 1005;
    public static final int MEDIA_RECORDER_TRACK_INFO_INITIAL_DELAY_MS = 1007;
    public static final int MEDIA_RECORDER_TRACK_INFO_LIST_END = 2000;
    public static final int MEDIA_RECORDER_TRACK_INFO_LIST_START = 1000;
    public static final int MEDIA_RECORDER_TRACK_INFO_MAX_CHUNK_DUR_MS = 1004;
    public static final int MEDIA_RECORDER_TRACK_INFO_PROGRESS_IN_TIME = 1001;
    public static final int MEDIA_RECORDER_TRACK_INFO_START_OFFSET_MS = 1008;
    public static final int MEDIA_RECORDER_TRACK_INFO_TYPE = 1002;
    public static final int MEDIA_RECORDER_TRACK_INTER_CHUNK_TIME_MS = 1006;
    private static final String TAG = "MediaRecorder";
    private EventHandler mEventHandler;
    private FileDescriptor mFd;
    private File mFile;
    private long mNativeContext;
    private OnErrorListener mOnErrorListener;
    private OnInfoListener mOnInfoListener;
    private String mPath;
    private Surface mSurface;

    static 
    {
        System.loadLibrary("media_jni");
        native_init();
    }
}

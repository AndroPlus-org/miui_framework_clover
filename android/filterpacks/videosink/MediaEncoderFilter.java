// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.videosink;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;
import android.filterfw.geometry.Point;
import android.filterfw.geometry.Quad;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.IOException;

// Referenced classes of package android.filterpacks.videosink:
//            MediaRecorderStopException

public class MediaEncoderFilter extends Filter
{
    public static interface OnRecordingDoneListener
    {

        public abstract void onRecordingDone();
    }


    public MediaEncoderFilter(String s)
    {
        super(s);
        mRecording = true;
        mOutputFile = new String("/sdcard/MediaEncoderOut.mp4");
        mFd = null;
        mAudioSource = -1;
        mInfoListener = null;
        mErrorListener = null;
        mRecordingDoneListener = null;
        mOrientationHint = 0;
        mProfile = null;
        mWidth = 0;
        mHeight = 0;
        mFps = 30;
        mOutputFormat = 2;
        mVideoEncoder = 2;
        mMaxFileSize = 0L;
        mMaxDurationMs = 0;
        mTimeBetweenTimeLapseFrameCaptureUs = 0L;
        mRecordingActive = false;
        mTimestampNs = 0L;
        mLastTimeLapseFrameRealTimestampNs = 0L;
        mNumFramesEncoded = 0;
        mCaptureTimeLapse = false;
        mSourceRegion = new Quad(new Point(0.0F, 0.0F), new Point(1.0F, 0.0F), new Point(0.0F, 1.0F), new Point(1.0F, 1.0F));
        mLogVerbose = Log.isLoggable("MediaEncoderFilter", 2);
    }

    private void startRecording(FilterContext filtercontext)
    {
        if(mLogVerbose)
            Log.v("MediaEncoderFilter", "Starting recording");
        MutableFrameFormat mutableframeformat = new MutableFrameFormat(2, 3);
        mutableframeformat.setBytesPerSample(4);
        int i;
        int j;
        if(mWidth > 0 && mHeight > 0)
            i = 1;
        else
            i = 0;
        if(mProfile != null && i ^ true)
        {
            i = mProfile.videoFrameWidth;
            j = mProfile.videoFrameHeight;
        } else
        {
            i = mWidth;
            j = mHeight;
        }
        mutableframeformat.setDimensions(i, j);
        mScreen = (GLFrame)filtercontext.getFrameManager().newBoundFrame(mutableframeformat, 101, 0L);
        mMediaRecorder = new MediaRecorder();
        updateMediaRecorderParams();
        try
        {
            mMediaRecorder.prepare();
        }
        // Misplaced declaration of an exception variable
        catch(FilterContext filtercontext)
        {
            throw filtercontext;
        }
        // Misplaced declaration of an exception variable
        catch(FilterContext filtercontext)
        {
            throw new RuntimeException("IOException inMediaRecorder.prepare()!", filtercontext);
        }
        // Misplaced declaration of an exception variable
        catch(FilterContext filtercontext)
        {
            throw new RuntimeException("Unknown Exception inMediaRecorder.prepare()!", filtercontext);
        }
        mMediaRecorder.start();
        if(mLogVerbose)
            Log.v("MediaEncoderFilter", "Open: registering surface from Mediarecorder");
        mSurfaceId = filtercontext.getGLEnvironment().registerSurfaceFromMediaRecorder(mMediaRecorder);
        mNumFramesEncoded = 0;
        mRecordingActive = true;
    }

    private void stopRecording(FilterContext filtercontext)
    {
        if(mLogVerbose)
            Log.v("MediaEncoderFilter", "Stopping recording");
        mRecordingActive = false;
        mNumFramesEncoded = 0;
        filtercontext = filtercontext.getGLEnvironment();
        if(mLogVerbose)
            Log.v("MediaEncoderFilter", String.format("Unregistering surface %d", new Object[] {
                Integer.valueOf(mSurfaceId)
            }));
        filtercontext.unregisterSurfaceId(mSurfaceId);
        try
        {
            mMediaRecorder.stop();
        }
        // Misplaced declaration of an exception variable
        catch(FilterContext filtercontext)
        {
            throw new MediaRecorderStopException("MediaRecorder.stop() failed!", filtercontext);
        }
        mMediaRecorder.release();
        mMediaRecorder = null;
        mScreen.release();
        mScreen = null;
        if(mRecordingDoneListener != null)
            mRecordingDoneListener.onRecordingDone();
    }

    private void updateMediaRecorderParams()
    {
        boolean flag = false;
        if(mTimeBetweenTimeLapseFrameCaptureUs > 0L)
            flag = true;
        mCaptureTimeLapse = flag;
        mMediaRecorder.setVideoSource(2);
        if(!mCaptureTimeLapse && mAudioSource != -1)
            mMediaRecorder.setAudioSource(mAudioSource);
        if(mProfile != null)
        {
            mMediaRecorder.setProfile(mProfile);
            mFps = mProfile.videoFrameRate;
            if(mWidth > 0 && mHeight > 0)
                mMediaRecorder.setVideoSize(mWidth, mHeight);
        } else
        {
            mMediaRecorder.setOutputFormat(mOutputFormat);
            mMediaRecorder.setVideoEncoder(mVideoEncoder);
            mMediaRecorder.setVideoSize(mWidth, mHeight);
            mMediaRecorder.setVideoFrameRate(mFps);
        }
        mMediaRecorder.setOrientationHint(mOrientationHint);
        mMediaRecorder.setOnInfoListener(mInfoListener);
        mMediaRecorder.setOnErrorListener(mErrorListener);
        if(mFd != null)
            mMediaRecorder.setOutputFile(mFd);
        else
            mMediaRecorder.setOutputFile(mOutputFile);
        try
        {
            mMediaRecorder.setMaxFileSize(mMaxFileSize);
        }
        catch(Exception exception)
        {
            Log.w("MediaEncoderFilter", (new StringBuilder()).append("Setting maxFileSize on MediaRecorder unsuccessful! ").append(exception.getMessage()).toString());
        }
        mMediaRecorder.setMaxDuration(mMaxDurationMs);
    }

    private void updateSourceRegion()
    {
        Quad quad = new Quad();
        quad.p0 = mSourceRegion.p2;
        quad.p1 = mSourceRegion.p3;
        quad.p2 = mSourceRegion.p0;
        quad.p3 = mSourceRegion.p1;
        mProgram.setSourceRegion(quad);
    }

    public void close(FilterContext filtercontext)
    {
        if(mLogVerbose)
            Log.v("MediaEncoderFilter", "Closing");
        if(mRecordingActive)
            stopRecording(filtercontext);
    }

    public void fieldPortValueUpdated(String s, FilterContext filtercontext)
    {
        if(mLogVerbose)
            Log.v("MediaEncoderFilter", (new StringBuilder()).append("Port ").append(s).append(" has been updated").toString());
        if(s.equals("recording"))
            return;
        if(s.equals("inputRegion"))
        {
            if(isOpen())
                updateSourceRegion();
            return;
        }
        if(isOpen() && mRecordingActive)
            throw new RuntimeException("Cannot change recording parameters when the filter is recording!");
        else
            return;
    }

    public void open(FilterContext filtercontext)
    {
        if(mLogVerbose)
            Log.v("MediaEncoderFilter", "Opening");
        updateSourceRegion();
        if(mRecording)
            startRecording(filtercontext);
    }

    public void prepare(FilterContext filtercontext)
    {
        if(mLogVerbose)
            Log.v("MediaEncoderFilter", "Preparing");
        mProgram = ShaderProgram.createIdentity(filtercontext);
        mRecordingActive = false;
    }

    public void process(FilterContext filtercontext)
    {
        GLEnvironment glenvironment = filtercontext.getGLEnvironment();
        Frame frame = pullInput("videoframe");
        if(!mRecordingActive && mRecording)
            startRecording(filtercontext);
        if(mRecordingActive && mRecording ^ true)
            stopRecording(filtercontext);
        if(!mRecordingActive)
            return;
        if(mCaptureTimeLapse)
        {
            if(skipFrameAndModifyTimestamp(frame.getTimestamp()))
                return;
        } else
        {
            mTimestampNs = frame.getTimestamp();
        }
        glenvironment.activateSurfaceWithId(mSurfaceId);
        mProgram.process(frame, mScreen);
        glenvironment.setSurfaceTimestamp(mTimestampNs);
        glenvironment.swapBuffers();
        mNumFramesEncoded = mNumFramesEncoded + 1;
    }

    public void setupPorts()
    {
        addMaskedInputPort("videoframe", ImageFormat.create(3, 3));
    }

    public boolean skipFrameAndModifyTimestamp(long l)
    {
        if(mNumFramesEncoded == 0)
        {
            mLastTimeLapseFrameRealTimestampNs = l;
            mTimestampNs = l;
            if(mLogVerbose)
                Log.v("MediaEncoderFilter", (new StringBuilder()).append("timelapse: FIRST frame, last real t= ").append(mLastTimeLapseFrameRealTimestampNs).append(", setting t = ").append(mTimestampNs).toString());
            return false;
        }
        if(mNumFramesEncoded >= 2 && l < mLastTimeLapseFrameRealTimestampNs + mTimeBetweenTimeLapseFrameCaptureUs * 1000L)
        {
            if(mLogVerbose)
                Log.v("MediaEncoderFilter", "timelapse: skipping intermediate frame");
            return true;
        }
        if(mLogVerbose)
            Log.v("MediaEncoderFilter", (new StringBuilder()).append("timelapse: encoding frame, Timestamp t = ").append(l).append(", last real t= ").append(mLastTimeLapseFrameRealTimestampNs).append(", interval = ").append(mTimeBetweenTimeLapseFrameCaptureUs).toString());
        mLastTimeLapseFrameRealTimestampNs = l;
        mTimestampNs = mTimestampNs + 0x3b9aca00L / (long)mFps;
        if(mLogVerbose)
            Log.v("MediaEncoderFilter", (new StringBuilder()).append("timelapse: encoding frame, setting t = ").append(mTimestampNs).append(", delta t = ").append(0x3b9aca00L / (long)mFps).append(", fps = ").append(mFps).toString());
        return false;
    }

    public void tearDown(FilterContext filtercontext)
    {
        if(mMediaRecorder != null)
            mMediaRecorder.release();
        if(mScreen != null)
            mScreen.release();
    }

    private static final int NO_AUDIO_SOURCE = -1;
    private static final String TAG = "MediaEncoderFilter";
    private int mAudioSource;
    private boolean mCaptureTimeLapse;
    private android.media.MediaRecorder.OnErrorListener mErrorListener;
    private FileDescriptor mFd;
    private int mFps;
    private int mHeight;
    private android.media.MediaRecorder.OnInfoListener mInfoListener;
    private long mLastTimeLapseFrameRealTimestampNs;
    private boolean mLogVerbose;
    private int mMaxDurationMs;
    private long mMaxFileSize;
    private MediaRecorder mMediaRecorder;
    private int mNumFramesEncoded;
    private int mOrientationHint;
    private String mOutputFile;
    private int mOutputFormat;
    private CamcorderProfile mProfile;
    private ShaderProgram mProgram;
    private boolean mRecording;
    private boolean mRecordingActive;
    private OnRecordingDoneListener mRecordingDoneListener;
    private GLFrame mScreen;
    private Quad mSourceRegion;
    private int mSurfaceId;
    private long mTimeBetweenTimeLapseFrameCaptureUs;
    private long mTimestampNs;
    private int mVideoEncoder;
    private int mWidth;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.videosrc;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.opengl.Matrix;
import android.util.Log;
import android.view.Surface;
import java.io.IOException;

public class MediaSource extends Filter
{

    static boolean _2D_get0(MediaSource mediasource)
    {
        return mediasource.mGotSize;
    }

    static boolean _2D_get1(MediaSource mediasource)
    {
        return mediasource.mLogVerbose;
    }

    static int _2D_get2(MediaSource mediasource)
    {
        return mediasource.mOrientation;
    }

    static MutableFrameFormat _2D_get3(MediaSource mediasource)
    {
        return mediasource.mOutputFormat;
    }

    static boolean _2D_set0(MediaSource mediasource, boolean flag)
    {
        mediasource.mCompleted = flag;
        return flag;
    }

    static boolean _2D_set1(MediaSource mediasource, boolean flag)
    {
        mediasource.mGotSize = flag;
        return flag;
    }

    static int _2D_set2(MediaSource mediasource, int i)
    {
        mediasource.mHeight = i;
        return i;
    }

    static boolean _2D_set3(MediaSource mediasource, boolean flag)
    {
        mediasource.mNewFrameAvailable = flag;
        return flag;
    }

    static boolean _2D_set4(MediaSource mediasource, boolean flag)
    {
        mediasource.mPrepared = flag;
        return flag;
    }

    static int _2D_set5(MediaSource mediasource, int i)
    {
        mediasource.mWidth = i;
        return i;
    }

    public MediaSource(String s)
    {
        super(s);
        mSourceUrl = "";
        mSourceAsset = null;
        mContext = null;
        mSelectedIsUrl = false;
        mWaitForNewFrame = true;
        mLooping = true;
        mVolume = 0.0F;
        mOrientation = 0;
        onVideoSizeChangedListener = new android.media.MediaPlayer.OnVideoSizeChangedListener() {

            public void onVideoSizeChanged(MediaPlayer mediaplayer, int i, int j)
            {
                if(MediaSource._2D_get1(MediaSource.this))
                    Log.v("MediaSource", (new StringBuilder()).append("MediaPlayer sent dimensions: ").append(i).append(" x ").append(j).toString());
                if(MediaSource._2D_get0(MediaSource.this)) goto _L2; else goto _L1
_L1:
                if(MediaSource._2D_get2(MediaSource.this) == 0 || MediaSource._2D_get2(MediaSource.this) == 180)
                    MediaSource._2D_get3(MediaSource.this).setDimensions(i, j);
                else
                    MediaSource._2D_get3(MediaSource.this).setDimensions(j, i);
                MediaSource._2D_set5(MediaSource.this, i);
                MediaSource._2D_set2(MediaSource.this, j);
_L4:
                mediaplayer = MediaSource.this;
                mediaplayer;
                JVM INSTR monitorenter ;
                MediaSource._2D_set1(MediaSource.this, true);
                notify();
                mediaplayer;
                JVM INSTR monitorexit ;
                return;
_L2:
                if(MediaSource._2D_get3(MediaSource.this).getWidth() != i || MediaSource._2D_get3(MediaSource.this).getHeight() != j)
                    Log.e("MediaSource", "Multiple video size change events received!");
                if(true) goto _L4; else goto _L3
_L3:
                Exception exception;
                exception;
                throw exception;
            }

            final MediaSource this$0;

            
            {
                this$0 = MediaSource.this;
                super();
            }
        }
;
        onPreparedListener = new android.media.MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaplayer)
            {
                if(MediaSource._2D_get1(MediaSource.this))
                    Log.v("MediaSource", "MediaPlayer is prepared");
                mediaplayer = MediaSource.this;
                mediaplayer;
                JVM INSTR monitorenter ;
                MediaSource._2D_set4(MediaSource.this, true);
                notify();
                mediaplayer;
                JVM INSTR monitorexit ;
                return;
                Exception exception;
                exception;
                throw exception;
            }

            final MediaSource this$0;

            
            {
                this$0 = MediaSource.this;
                super();
            }
        }
;
        onCompletionListener = new android.media.MediaPlayer.OnCompletionListener() {

            public void onCompletion(MediaPlayer mediaplayer)
            {
                if(MediaSource._2D_get1(MediaSource.this))
                    Log.v("MediaSource", "MediaPlayer has completed playback");
                MediaSource mediasource = MediaSource.this;
                mediasource;
                JVM INSTR monitorenter ;
                MediaSource._2D_set0(MediaSource.this, true);
                mediasource;
                JVM INSTR monitorexit ;
                return;
                mediaplayer;
                throw mediaplayer;
            }

            final MediaSource this$0;

            
            {
                this$0 = MediaSource.this;
                super();
            }
        }
;
        onMediaFrameAvailableListener = new android.graphics.SurfaceTexture.OnFrameAvailableListener() {

            public void onFrameAvailable(SurfaceTexture surfacetexture)
            {
                if(MediaSource._2D_get1(MediaSource.this))
                    Log.v("MediaSource", "New frame from media player");
                MediaSource mediasource = MediaSource.this;
                mediasource;
                JVM INSTR monitorenter ;
                if(MediaSource._2D_get1(MediaSource.this))
                    Log.v("MediaSource", "New frame: notify");
                MediaSource._2D_set3(MediaSource.this, true);
                notify();
                if(MediaSource._2D_get1(MediaSource.this))
                    Log.v("MediaSource", "New frame: notify done");
                mediasource;
                JVM INSTR monitorexit ;
                return;
                surfacetexture;
                throw surfacetexture;
            }

            final MediaSource this$0;

            
            {
                this$0 = MediaSource.this;
                super();
            }
        }
;
        mNewFrameAvailable = false;
    }

    private void createFormats()
    {
        mOutputFormat = ImageFormat.create(3, 3);
    }

    private boolean setupMediaPlayer(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        mPrepared = false;
        mGotSize = false;
        mPlaying = false;
        mPaused = false;
        mCompleted = false;
        mNewFrameAvailable = false;
        if(mLogVerbose)
            Log.v("MediaSource", "Setting up playback.");
        if(mMediaPlayer == null)
            break MISSING_BLOCK_LABEL_100;
        if(mLogVerbose)
            Log.v("MediaSource", "Resetting existing MediaPlayer.");
        mMediaPlayer.reset();
_L1:
        if(mMediaPlayer == null)
        {
            RuntimeException runtimeexception = JVM INSTR new #204 <Class RuntimeException>;
            runtimeexception.RuntimeException("Unable to create a MediaPlayer!");
            throw runtimeexception;
        }
        break MISSING_BLOCK_LABEL_131;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if(mLogVerbose)
            Log.v("MediaSource", "Creating new MediaPlayer.");
        MediaPlayer mediaplayer = JVM INSTR new #199 <Class MediaPlayer>;
        mediaplayer.MediaPlayer();
        mMediaPlayer = mediaplayer;
          goto _L1
        if(!flag)
            break MISSING_BLOCK_LABEL_380;
        if(mLogVerbose)
        {
            StringBuilder stringbuilder = JVM INSTR new #213 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.v("MediaSource", stringbuilder.append("Setting MediaPlayer source to URI ").append(mSourceUrl).toString());
        }
        if(mContext != null) goto _L3; else goto _L2
_L2:
        mMediaPlayer.setDataSource(mSourceUrl);
_L4:
        mMediaPlayer.setLooping(mLooping);
        mMediaPlayer.setVolume(mVolume, mVolume);
        Surface surface = JVM INSTR new #237 <Class Surface>;
        surface.Surface(mSurfaceTexture);
        mMediaPlayer.setSurface(surface);
        surface.release();
        mMediaPlayer.setOnVideoSizeChangedListener(onVideoSizeChangedListener);
        mMediaPlayer.setOnPreparedListener(onPreparedListener);
        mMediaPlayer.setOnCompletionListener(onCompletionListener);
        mSurfaceTexture.setOnFrameAvailableListener(onMediaFrameAvailableListener);
        if(mLogVerbose)
            Log.v("MediaSource", "Preparing MediaPlayer.");
        mMediaPlayer.prepareAsync();
        this;
        JVM INSTR monitorexit ;
        return true;
_L3:
        mMediaPlayer.setDataSource(mContext, Uri.parse(mSourceUrl.toString()));
          goto _L4
        Object obj;
        obj;
        mMediaPlayer.release();
        mMediaPlayer = null;
        if(!flag)
            break MISSING_BLOCK_LABEL_522;
        RuntimeException runtimeexception1 = JVM INSTR new #204 <Class RuntimeException>;
        runtimeexception1.RuntimeException(String.format("Unable to set MediaPlayer to URL %s!", new Object[] {
            mSourceUrl
        }), ((Throwable) (obj)));
        throw runtimeexception1;
        if(mLogVerbose)
        {
            obj = JVM INSTR new #213 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            Log.v("MediaSource", ((StringBuilder) (obj)).append("Setting MediaPlayer source to asset ").append(mSourceAsset).toString());
        }
        mMediaPlayer.setDataSource(mSourceAsset.getFileDescriptor(), mSourceAsset.getStartOffset(), mSourceAsset.getLength());
          goto _L4
        obj;
        mMediaPlayer.release();
        mMediaPlayer = null;
        if(!flag)
            break MISSING_BLOCK_LABEL_494;
        RuntimeException runtimeexception2 = JVM INSTR new #204 <Class RuntimeException>;
        runtimeexception2.RuntimeException(String.format("Unable to set MediaPlayer to URL %s!", new Object[] {
            mSourceUrl
        }), ((Throwable) (obj)));
        throw runtimeexception2;
        RuntimeException runtimeexception3 = JVM INSTR new #204 <Class RuntimeException>;
        runtimeexception3.RuntimeException(String.format("Unable to set MediaPlayer to asset %s!", new Object[] {
            mSourceAsset
        }), ((Throwable) (obj)));
        throw runtimeexception3;
        RuntimeException runtimeexception4 = JVM INSTR new #204 <Class RuntimeException>;
        runtimeexception4.RuntimeException(String.format("Unable to set MediaPlayer to asset %s!", new Object[] {
            mSourceAsset
        }), ((Throwable) (obj)));
        throw runtimeexception4;
    }

    public void close(FilterContext filtercontext)
    {
        if(mMediaPlayer.isPlaying())
            mMediaPlayer.stop();
        mPrepared = false;
        mGotSize = false;
        mPlaying = false;
        mPaused = false;
        mCompleted = false;
        mNewFrameAvailable = false;
        mMediaPlayer.release();
        mMediaPlayer = null;
        mSurfaceTexture.release();
        mSurfaceTexture = null;
        if(mLogVerbose)
            Log.v("MediaSource", "MediaSource closed");
    }

    public void fieldPortValueUpdated(String s, FilterContext filtercontext)
    {
        if(mLogVerbose)
            Log.v("MediaSource", "Parameter update");
        if(!s.equals("sourceUrl")) goto _L2; else goto _L1
_L1:
        if(isOpen())
        {
            if(mLogVerbose)
                Log.v("MediaSource", "Opening new source URL");
            if(mSelectedIsUrl)
                setupMediaPlayer(mSelectedIsUrl);
        }
_L9:
        return;
_L2:
        if(s.equals("sourceAsset"))
        {
            if(isOpen())
            {
                if(mLogVerbose)
                    Log.v("MediaSource", "Opening new source FD");
                if(!mSelectedIsUrl)
                    setupMediaPlayer(mSelectedIsUrl);
            }
            continue; /* Loop/switch isn't completed */
        }
        if(s.equals("loop"))
        {
            if(isOpen())
                mMediaPlayer.setLooping(mLooping);
            continue; /* Loop/switch isn't completed */
        }
        if(!s.equals("sourceIsUrl")) goto _L4; else goto _L3
_L3:
        if(!isOpen())
            continue; /* Loop/switch isn't completed */
        if(!mSelectedIsUrl) goto _L6; else goto _L5
_L5:
        if(mLogVerbose)
            Log.v("MediaSource", "Opening new source URL");
_L7:
        setupMediaPlayer(mSelectedIsUrl);
        continue; /* Loop/switch isn't completed */
_L6:
        if(mLogVerbose)
            Log.v("MediaSource", "Opening new source Asset");
        if(true) goto _L7; else goto _L4
_L4:
        if(s.equals("volume"))
        {
            if(isOpen())
                mMediaPlayer.setVolume(mVolume, mVolume);
        } else
        if(s.equals("orientation") && mGotSize)
        {
            if(mOrientation == 0 || mOrientation == 180)
                mOutputFormat.setDimensions(mWidth, mHeight);
            else
                mOutputFormat.setDimensions(mHeight, mWidth);
            mOrientationUpdated = true;
        }
        if(true) goto _L9; else goto _L8
_L8:
    }

    public void open(FilterContext filtercontext)
    {
        if(mLogVerbose)
        {
            Log.v("MediaSource", "Opening MediaSource");
            if(mSelectedIsUrl)
                Log.v("MediaSource", (new StringBuilder()).append("Current URL is ").append(mSourceUrl).toString());
            else
                Log.v("MediaSource", "Current source is Asset!");
        }
        mMediaFrame = (GLFrame)filtercontext.getFrameManager().newBoundFrame(mOutputFormat, 104, 0L);
        mSurfaceTexture = new SurfaceTexture(mMediaFrame.getTextureId());
        if(!setupMediaPlayer(mSelectedIsUrl))
            throw new RuntimeException("Error setting up MediaPlayer!");
        else
            return;
    }

    public void pauseVideo(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        if(!isOpen()) goto _L2; else goto _L1
_L1:
        if(!flag) goto _L4; else goto _L3
_L3:
        if(!(mPaused ^ true)) goto _L4; else goto _L5
_L5:
        mMediaPlayer.pause();
_L2:
        mPaused = flag;
        this;
        JVM INSTR monitorexit ;
        return;
_L4:
        if(flag) goto _L2; else goto _L6
_L6:
        if(!mPaused) goto _L2; else goto _L7
_L7:
        mMediaPlayer.start();
          goto _L2
        Exception exception;
        exception;
        throw exception;
    }

    protected void prepare(FilterContext filtercontext)
    {
        if(mLogVerbose)
            Log.v("MediaSource", "Preparing MediaSource");
        mFrameExtractor = new ShaderProgram(filtercontext, "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES tex_sampler_0;\nvarying vec2 v_texcoord;\nvoid main() {\n  gl_FragColor = texture2D(tex_sampler_0, v_texcoord);\n}\n");
        mFrameExtractor.setSourceRect(0.0F, 1.0F, 1.0F, -1F);
        createFormats();
    }

    public void process(FilterContext filtercontext)
    {
        if(mLogVerbose)
            Log.v("MediaSource", "Processing new frame");
        if(mMediaPlayer == null)
            throw new NullPointerException("Unexpected null media player!");
        if(mCompleted)
        {
            closeOutputPort("video");
            return;
        }
        if(!mPlaying)
        {
            boolean flag = false;
            int j = ((flag) ? 1 : 0);
            if(mLogVerbose)
            {
                Log.v("MediaSource", "Waiting for preparation to complete");
                j = ((flag) ? 1 : 0);
            }
            while(!mGotSize || mPrepared ^ true) 
            {
                try
                {
                    wait(100L);
                }
                catch(InterruptedException interruptedexception) { }
                if(mCompleted)
                {
                    closeOutputPort("video");
                    return;
                }
                int i = j + 1;
                j = i;
                if(i == 100)
                {
                    mMediaPlayer.release();
                    throw new RuntimeException("MediaPlayer timed out while preparing!");
                }
            }
            if(mLogVerbose)
                Log.v("MediaSource", "Starting playback");
            mMediaPlayer.start();
        }
        if(!mPaused || mPlaying ^ true)
        {
            if(mWaitForNewFrame)
            {
                if(mLogVerbose)
                    Log.v("MediaSource", "Waiting for new frame");
                int k = 0;
                do
                {
                    if(mNewFrameAvailable)
                        break;
                    if(k == 10)
                        if(mCompleted)
                        {
                            closeOutputPort("video");
                            return;
                        } else
                        {
                            throw new RuntimeException("Timeout waiting for new frame!");
                        }
                    try
                    {
                        wait(100L);
                    }
                    catch(InterruptedException interruptedexception1)
                    {
                        if(mLogVerbose)
                            Log.v("MediaSource", "interrupted");
                    }
                    k++;
                } while(true);
                mNewFrameAvailable = false;
                if(mLogVerbose)
                    Log.v("MediaSource", "Got new frame");
            }
            mSurfaceTexture.updateTexImage();
            mOrientationUpdated = true;
        }
        if(!mOrientationUpdated) goto _L2; else goto _L1
_L1:
        float af[];
        float af1[];
        af1 = new float[16];
        mSurfaceTexture.getTransformMatrix(af1);
        af = new float[16];
        mOrientation;
        JVM INSTR lookupswitch 4: default 396
    //                   0: 396
    //                   90: 700
    //                   180: 716
    //                   270: 732;
           goto _L3 _L3 _L4 _L5 _L6
_L3:
        Matrix.multiplyMM(af, 0, af1, 0, mSourceCoords_0, 0);
_L8:
        if(mLogVerbose)
        {
            Log.v("MediaSource", (new StringBuilder()).append("OrientationHint = ").append(mOrientation).toString());
            Log.v("MediaSource", String.format("SetSourceRegion: %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f", new Object[] {
                Float.valueOf(af[4]), Float.valueOf(af[5]), Float.valueOf(af[0]), Float.valueOf(af[1]), Float.valueOf(af[12]), Float.valueOf(af[13]), Float.valueOf(af[8]), Float.valueOf(af[9])
            }));
        }
        mFrameExtractor.setSourceRegion(af[4], af[5], af[0], af[1], af[12], af[13], af[8], af[9]);
        mOrientationUpdated = false;
_L2:
        filtercontext = filtercontext.getFrameManager().newFrame(mOutputFormat);
        mFrameExtractor.process(mMediaFrame, filtercontext);
        long l = mSurfaceTexture.getTimestamp();
        if(mLogVerbose)
            Log.v("MediaSource", (new StringBuilder()).append("Timestamp: ").append((double)l / 1000000000D).append(" s").toString());
        filtercontext.setTimestamp(l);
        pushOutput("video", filtercontext);
        filtercontext.release();
        mPlaying = true;
        return;
_L4:
        Matrix.multiplyMM(af, 0, af1, 0, mSourceCoords_90, 0);
        continue; /* Loop/switch isn't completed */
_L5:
        Matrix.multiplyMM(af, 0, af1, 0, mSourceCoords_180, 0);
        continue; /* Loop/switch isn't completed */
_L6:
        Matrix.multiplyMM(af, 0, af1, 0, mSourceCoords_270, 0);
        if(true) goto _L8; else goto _L7
_L7:
    }

    public void setupPorts()
    {
        addOutputPort("video", ImageFormat.create(3, 3));
    }

    public void tearDown(FilterContext filtercontext)
    {
        if(mMediaFrame != null)
            mMediaFrame.release();
    }

    private static final int NEWFRAME_TIMEOUT = 100;
    private static final int NEWFRAME_TIMEOUT_REPEAT = 10;
    private static final int PREP_TIMEOUT = 100;
    private static final int PREP_TIMEOUT_REPEAT = 100;
    private static final String TAG = "MediaSource";
    private static final float mSourceCoords_0[] = {
        1.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 
        0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F
    };
    private static final float mSourceCoords_180[] = {
        0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 
        0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 1.0F
    };
    private static final float mSourceCoords_270[] = {
        0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 
        0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F
    };
    private static final float mSourceCoords_90[] = {
        1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 
        0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F
    };
    private boolean mCompleted;
    private Context mContext;
    private ShaderProgram mFrameExtractor;
    private final String mFrameShader = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES tex_sampler_0;\nvarying vec2 v_texcoord;\nvoid main() {\n  gl_FragColor = texture2D(tex_sampler_0, v_texcoord);\n}\n";
    private boolean mGotSize;
    private int mHeight;
    private final boolean mLogVerbose = Log.isLoggable("MediaSource", 2);
    private boolean mLooping;
    private GLFrame mMediaFrame;
    private MediaPlayer mMediaPlayer;
    private boolean mNewFrameAvailable;
    private int mOrientation;
    private boolean mOrientationUpdated;
    private MutableFrameFormat mOutputFormat;
    private boolean mPaused;
    private boolean mPlaying;
    private boolean mPrepared;
    private boolean mSelectedIsUrl;
    private AssetFileDescriptor mSourceAsset;
    private String mSourceUrl;
    private SurfaceTexture mSurfaceTexture;
    private float mVolume;
    private boolean mWaitForNewFrame;
    private int mWidth;
    private android.media.MediaPlayer.OnCompletionListener onCompletionListener;
    private android.graphics.SurfaceTexture.OnFrameAvailableListener onMediaFrameAvailableListener;
    private android.media.MediaPlayer.OnPreparedListener onPreparedListener;
    private android.media.MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.videosrc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;
import android.graphics.SurfaceTexture;
import android.opengl.Matrix;
import android.os.ConditionVariable;
import android.util.Log;

public class SurfaceTextureSource extends Filter
{
    public static interface SurfaceTextureSourceListener
    {

        public abstract void onSurfaceTextureSourceReady(SurfaceTexture surfacetexture);
    }


    static boolean _2D_get0()
    {
        return mLogVerbose;
    }

    static ConditionVariable _2D_get1(SurfaceTextureSource surfacetexturesource)
    {
        return surfacetexturesource.mNewFrameAvailable;
    }

    public SurfaceTextureSource(String s)
    {
        super(s);
        mWaitForNewFrame = true;
        mWaitTimeout = 1000;
        mCloseOnTimeout = false;
        onFrameAvailableListener = new android.graphics.SurfaceTexture.OnFrameAvailableListener() {

            public void onFrameAvailable(SurfaceTexture surfacetexture)
            {
                if(SurfaceTextureSource._2D_get0())
                    Log.v("SurfaceTextureSource", "New frame from SurfaceTexture");
                SurfaceTextureSource._2D_get1(SurfaceTextureSource.this).open();
            }

            final SurfaceTextureSource this$0;

            
            {
                this$0 = SurfaceTextureSource.this;
                super();
            }
        }
;
        mNewFrameAvailable = new ConditionVariable();
        mFrameTransform = new float[16];
        mMappedCoords = new float[16];
    }

    private void createFormats()
    {
        mOutputFormat = ImageFormat.create(mWidth, mHeight, 3, 3);
    }

    public void close(FilterContext filtercontext)
    {
        if(mLogVerbose)
            Log.v("SurfaceTextureSource", "SurfaceTextureSource closed");
        mSourceListener.onSurfaceTextureSourceReady(null);
        mSurfaceTexture.release();
        mSurfaceTexture = null;
    }

    public void fieldPortValueUpdated(String s, FilterContext filtercontext)
    {
        if(s.equals("width") || s.equals("height"))
            mOutputFormat.setDimensions(mWidth, mHeight);
    }

    public void open(FilterContext filtercontext)
    {
        if(mLogVerbose)
            Log.v("SurfaceTextureSource", "Opening SurfaceTextureSource");
        mSurfaceTexture = new SurfaceTexture(mMediaFrame.getTextureId());
        mSurfaceTexture.setOnFrameAvailableListener(onFrameAvailableListener);
        mSourceListener.onSurfaceTextureSourceReady(mSurfaceTexture);
        mFirstFrame = true;
    }

    protected void prepare(FilterContext filtercontext)
    {
        if(mLogVerbose)
            Log.v("SurfaceTextureSource", "Preparing SurfaceTextureSource");
        createFormats();
        mMediaFrame = (GLFrame)filtercontext.getFrameManager().newBoundFrame(mOutputFormat, 104, 0L);
        mFrameExtractor = new ShaderProgram(filtercontext, "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES tex_sampler_0;\nvarying vec2 v_texcoord;\nvoid main() {\n  gl_FragColor = texture2D(tex_sampler_0, v_texcoord);\n}\n");
    }

    public void process(FilterContext filtercontext)
    {
        if(mLogVerbose)
            Log.v("SurfaceTextureSource", "Processing new frame");
        if(mWaitForNewFrame || mFirstFrame)
        {
            if(mWaitTimeout != 0)
            {
                if(!mNewFrameAvailable.block(mWaitTimeout))
                {
                    if(!mCloseOnTimeout)
                        throw new RuntimeException("Timeout waiting for new frame");
                    if(mLogVerbose)
                        Log.v("SurfaceTextureSource", "Timeout waiting for a new frame. Closing.");
                    closeOutputPort("video");
                    return;
                }
            } else
            {
                mNewFrameAvailable.block();
            }
            mNewFrameAvailable.close();
            mFirstFrame = false;
        }
        mSurfaceTexture.updateTexImage();
        mSurfaceTexture.getTransformMatrix(mFrameTransform);
        Matrix.multiplyMM(mMappedCoords, 0, mFrameTransform, 0, mSourceCoords, 0);
        mFrameExtractor.setSourceRegion(mMappedCoords[0], mMappedCoords[1], mMappedCoords[4], mMappedCoords[5], mMappedCoords[8], mMappedCoords[9], mMappedCoords[12], mMappedCoords[13]);
        filtercontext = filtercontext.getFrameManager().newFrame(mOutputFormat);
        mFrameExtractor.process(mMediaFrame, filtercontext);
        filtercontext.setTimestamp(mSurfaceTexture.getTimestamp());
        pushOutput("video", filtercontext);
        filtercontext.release();
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

    private static final String TAG = "SurfaceTextureSource";
    private static final boolean mLogVerbose = Log.isLoggable("SurfaceTextureSource", 2);
    private static final float mSourceCoords[] = {
        0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 
        0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F
    };
    private boolean mCloseOnTimeout;
    private boolean mFirstFrame;
    private ShaderProgram mFrameExtractor;
    private float mFrameTransform[];
    private int mHeight;
    private float mMappedCoords[];
    private GLFrame mMediaFrame;
    private ConditionVariable mNewFrameAvailable;
    private MutableFrameFormat mOutputFormat;
    private final String mRenderShader = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES tex_sampler_0;\nvarying vec2 v_texcoord;\nvoid main() {\n  gl_FragColor = texture2D(tex_sampler_0, v_texcoord);\n}\n";
    private SurfaceTextureSourceListener mSourceListener;
    private SurfaceTexture mSurfaceTexture;
    private boolean mWaitForNewFrame;
    private int mWaitTimeout;
    private int mWidth;
    private android.graphics.SurfaceTexture.OnFrameAvailableListener onFrameAvailableListener;

}

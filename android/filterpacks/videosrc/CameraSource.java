// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.videosrc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.opengl.Matrix;
import android.util.Log;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class CameraSource extends Filter
{

    static boolean _2D_get0(CameraSource camerasource)
    {
        return camerasource.mLogVerbose;
    }

    static boolean _2D_set0(CameraSource camerasource, boolean flag)
    {
        camerasource.mNewFrameAvailable = flag;
        return flag;
    }

    public CameraSource(String s)
    {
        super(s);
        mCameraId = 0;
        mWidth = 320;
        mHeight = 240;
        mFps = 30;
        mWaitForNewFrame = true;
        onCameraFrameAvailableListener = new android.graphics.SurfaceTexture.OnFrameAvailableListener() {

            public void onFrameAvailable(SurfaceTexture surfacetexture)
            {
                if(CameraSource._2D_get0(CameraSource.this))
                    Log.v("CameraSource", "New frame from camera");
                CameraSource camerasource = CameraSource.this;
                camerasource;
                JVM INSTR monitorenter ;
                CameraSource._2D_set0(CameraSource.this, true);
                notify();
                camerasource;
                JVM INSTR monitorexit ;
                return;
                surfacetexture;
                throw surfacetexture;
            }

            final CameraSource this$0;

            
            {
                this$0 = CameraSource.this;
                super();
            }
        }
;
        mCameraTransform = new float[16];
        mMappedCoords = new float[16];
    }

    private void createFormats()
    {
        mOutputFormat = ImageFormat.create(mWidth, mHeight, 3, 3);
    }

    private int[] findClosestFpsRange(int i, android.hardware.Camera.Parameters parameters)
    {
        List list = parameters.getSupportedPreviewFpsRange();
        parameters = (int[])list.get(0);
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            int ai[] = (int[])iterator.next();
            if(ai[0] < i * 1000 && ai[1] > i * 1000 && ai[0] > parameters[0] && ai[1] < parameters[1])
                parameters = ai;
        } while(true);
        if(mLogVerbose)
            Log.v("CameraSource", (new StringBuilder()).append("Requested fps: ").append(i).append(".Closest frame rate range: [").append((double)parameters[0] / 1000D).append(",").append((double)parameters[1] / 1000D).append("]").toString());
        return parameters;
    }

    private int[] findClosestSize(int i, int j, android.hardware.Camera.Parameters parameters)
    {
        parameters = parameters.getSupportedPreviewSizes();
        int k = -1;
        int l = -1;
        int i1 = ((android.hardware.Camera.Size)parameters.get(0)).width;
        int j1 = ((android.hardware.Camera.Size)parameters.get(0)).height;
        Iterator iterator = parameters.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            parameters = (android.hardware.Camera.Size)iterator.next();
            int k1 = l;
            int l1 = k;
            if(((android.hardware.Camera.Size) (parameters)).width <= i)
            {
                k1 = l;
                l1 = k;
                if(((android.hardware.Camera.Size) (parameters)).height <= j)
                {
                    k1 = l;
                    l1 = k;
                    if(((android.hardware.Camera.Size) (parameters)).width >= k)
                    {
                        k1 = l;
                        l1 = k;
                        if(((android.hardware.Camera.Size) (parameters)).height >= l)
                        {
                            l1 = ((android.hardware.Camera.Size) (parameters)).width;
                            k1 = ((android.hardware.Camera.Size) (parameters)).height;
                        }
                    }
                }
            }
            l = k1;
            k = l1;
            if(((android.hardware.Camera.Size) (parameters)).width < i1)
            {
                l = k1;
                k = l1;
                if(((android.hardware.Camera.Size) (parameters)).height < j1)
                {
                    i1 = ((android.hardware.Camera.Size) (parameters)).width;
                    j1 = ((android.hardware.Camera.Size) (parameters)).height;
                    l = k1;
                    k = l1;
                }
            }
        } while(true);
        int i2 = l;
        l = k;
        if(k == -1)
        {
            l = i1;
            i2 = j1;
        }
        if(mLogVerbose)
            Log.v("CameraSource", (new StringBuilder()).append("Requested resolution: (").append(i).append(", ").append(j).append("). Closest match: (").append(l).append(", ").append(i2).append(").").toString());
        return (new int[] {
            l, i2
        });
    }

    public void close(FilterContext filtercontext)
    {
        if(mLogVerbose)
            Log.v("CameraSource", "Closing");
        mCamera.release();
        mCamera = null;
        mSurfaceTexture.release();
        mSurfaceTexture = null;
    }

    public void fieldPortValueUpdated(String s, FilterContext filtercontext)
    {
        if(s.equals("framerate"))
        {
            getCameraParameters();
            s = findClosestFpsRange(mFps, mCameraParameters);
            mCameraParameters.setPreviewFpsRange(s[0], s[1]);
            mCamera.setParameters(mCameraParameters);
        }
    }

    public android.hardware.Camera.Parameters getCameraParameters()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = false;
        if(mCameraParameters != null)
            break MISSING_BLOCK_LABEL_58;
        if(mCamera != null)
            break MISSING_BLOCK_LABEL_31;
        mCamera = Camera.open(mCameraId);
        flag = true;
        mCameraParameters = mCamera.getParameters();
        if(!flag)
            break MISSING_BLOCK_LABEL_58;
        mCamera.release();
        mCamera = null;
        android.hardware.Camera.Parameters parameters;
        int ai[] = findClosestSize(mWidth, mHeight, mCameraParameters);
        mWidth = ai[0];
        mHeight = ai[1];
        mCameraParameters.setPreviewSize(mWidth, mHeight);
        ai = findClosestFpsRange(mFps, mCameraParameters);
        mCameraParameters.setPreviewFpsRange(ai[0], ai[1]);
        parameters = mCameraParameters;
        this;
        JVM INSTR monitorexit ;
        return parameters;
        Exception exception;
        exception;
        throw exception;
    }

    public void open(FilterContext filtercontext)
    {
        if(mLogVerbose)
            Log.v("CameraSource", "Opening");
        mCamera = Camera.open(mCameraId);
        getCameraParameters();
        mCamera.setParameters(mCameraParameters);
        createFormats();
        mCameraFrame = (GLFrame)filtercontext.getFrameManager().newBoundFrame(mOutputFormat, 104, 0L);
        mSurfaceTexture = new SurfaceTexture(mCameraFrame.getTextureId());
        try
        {
            mCamera.setPreviewTexture(mSurfaceTexture);
        }
        // Misplaced declaration of an exception variable
        catch(FilterContext filtercontext)
        {
            throw new RuntimeException((new StringBuilder()).append("Could not bind camera surface texture: ").append(filtercontext.getMessage()).append("!").toString());
        }
        mSurfaceTexture.setOnFrameAvailableListener(onCameraFrameAvailableListener);
        mNewFrameAvailable = false;
        mCamera.startPreview();
    }

    public void prepare(FilterContext filtercontext)
    {
        if(mLogVerbose)
            Log.v("CameraSource", "Preparing");
        mFrameExtractor = new ShaderProgram(filtercontext, "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES tex_sampler_0;\nvarying vec2 v_texcoord;\nvoid main() {\n  gl_FragColor = texture2D(tex_sampler_0, v_texcoord);\n}\n");
    }

    public void process(FilterContext filtercontext)
    {
        if(mLogVerbose)
            Log.v("CameraSource", "Processing new frame");
        if(mWaitForNewFrame)
        {
            do
            {
                if(mNewFrameAvailable)
                    break;
                try
                {
                    wait(100L);
                }
                catch(InterruptedException interruptedexception)
                {
                    if(mLogVerbose)
                        Log.v("CameraSource", "Interrupted while waiting for new frame");
                }
            } while(true);
            mNewFrameAvailable = false;
            if(mLogVerbose)
                Log.v("CameraSource", "Got new frame");
        }
        mSurfaceTexture.updateTexImage();
        if(mLogVerbose)
            Log.v("CameraSource", (new StringBuilder()).append("Using frame extractor in thread: ").append(Thread.currentThread()).toString());
        mSurfaceTexture.getTransformMatrix(mCameraTransform);
        Matrix.multiplyMM(mMappedCoords, 0, mCameraTransform, 0, mSourceCoords, 0);
        mFrameExtractor.setSourceRegion(mMappedCoords[0], mMappedCoords[1], mMappedCoords[4], mMappedCoords[5], mMappedCoords[8], mMappedCoords[9], mMappedCoords[12], mMappedCoords[13]);
        filtercontext = filtercontext.getFrameManager().newFrame(mOutputFormat);
        mFrameExtractor.process(mCameraFrame, filtercontext);
        long l = mSurfaceTexture.getTimestamp();
        if(mLogVerbose)
            Log.v("CameraSource", (new StringBuilder()).append("Timestamp: ").append((double)l / 1000000000D).append(" s").toString());
        filtercontext.setTimestamp(l);
        pushOutput("video", filtercontext);
        filtercontext.release();
        if(mLogVerbose)
            Log.v("CameraSource", "Done processing new frame");
    }

    public void setCameraParameters(android.hardware.Camera.Parameters parameters)
    {
        this;
        JVM INSTR monitorenter ;
        parameters.setPreviewSize(mWidth, mHeight);
        mCameraParameters = parameters;
        if(isOpen())
            mCamera.setParameters(mCameraParameters);
        this;
        JVM INSTR monitorexit ;
        return;
        parameters;
        throw parameters;
    }

    public void setupPorts()
    {
        addOutputPort("video", ImageFormat.create(3, 3));
    }

    public void tearDown(FilterContext filtercontext)
    {
        if(mCameraFrame != null)
            mCameraFrame.release();
    }

    private static final int NEWFRAME_TIMEOUT = 100;
    private static final int NEWFRAME_TIMEOUT_REPEAT = 10;
    private static final String TAG = "CameraSource";
    private static final String mFrameShader = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES tex_sampler_0;\nvarying vec2 v_texcoord;\nvoid main() {\n  gl_FragColor = texture2D(tex_sampler_0, v_texcoord);\n}\n";
    private static final float mSourceCoords[] = {
        0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 
        0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F
    };
    private Camera mCamera;
    private GLFrame mCameraFrame;
    private int mCameraId;
    private android.hardware.Camera.Parameters mCameraParameters;
    private float mCameraTransform[];
    private int mFps;
    private ShaderProgram mFrameExtractor;
    private int mHeight;
    private final boolean mLogVerbose = Log.isLoggable("CameraSource", 2);
    private float mMappedCoords[];
    private boolean mNewFrameAvailable;
    private MutableFrameFormat mOutputFormat;
    private SurfaceTexture mSurfaceTexture;
    private boolean mWaitForNewFrame;
    private int mWidth;
    private android.graphics.SurfaceTexture.OnFrameAvailableListener onCameraFrameAvailableListener;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.videosrc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;
import android.filterfw.geometry.Point;
import android.filterfw.geometry.Quad;
import android.graphics.SurfaceTexture;
import android.util.Log;

public class SurfaceTextureTarget extends Filter
{

    public SurfaceTextureTarget(String s)
    {
        super(s);
        mSourceQuad = new Quad(new Point(0.0F, 1.0F), new Point(1.0F, 1.0F), new Point(0.0F, 0.0F), new Point(1.0F, 0.0F));
        mTargetQuad = new Quad(new Point(0.0F, 0.0F), new Point(1.0F, 0.0F), new Point(0.0F, 1.0F), new Point(1.0F, 1.0F));
        mRenderMode = 1;
        mAspectRatio = 1.0F;
        mLogVerbose = Log.isLoggable("SurfaceTextureTarget", 2);
    }

    private void updateTargetRect()
    {
        if(mLogVerbose)
            Log.v("SurfaceTextureTarget", (new StringBuilder()).append("updateTargetRect. Thread: ").append(Thread.currentThread()).toString());
        if(mScreenWidth <= 0 || mScreenHeight <= 0 || mProgram == null) goto _L2; else goto _L1
_L1:
        float f1;
        float f = (float)mScreenWidth / (float)mScreenHeight;
        f1 = f / mAspectRatio;
        if(mLogVerbose)
            Log.v("SurfaceTextureTarget", (new StringBuilder()).append("UTR. screen w = ").append(mScreenWidth).append(" x screen h = ").append(mScreenHeight).append(" Screen AR: ").append(f).append(", frame AR: ").append(mAspectRatio).append(", relative AR: ").append(f1).toString());
        if(f1 != 1.0F || mRenderMode == 3) goto _L4; else goto _L3
_L3:
        mProgram.setTargetRect(0.0F, 0.0F, 1.0F, 1.0F);
        mProgram.setClearsOutput(false);
_L2:
        return;
_L4:
        mRenderMode;
        JVM INSTR tableswitch 0 3: default 220
    //                   0 269
    //                   1 328
    //                   2 492
    //                   3 656;
           goto _L5 _L6 _L7 _L8 _L9
_L9:
        break MISSING_BLOCK_LABEL_656;
_L5:
        break; /* Loop/switch isn't completed */
_L6:
        break; /* Loop/switch isn't completed */
_L11:
        if(mLogVerbose)
            Log.v("SurfaceTextureTarget", (new StringBuilder()).append("UTR. quad: ").append(mTargetQuad).toString());
        mProgram.setTargetRegion(mTargetQuad);
        if(true) goto _L2; else goto _L10
_L10:
        mTargetQuad.p0.set(0.0F, 0.0F);
        mTargetQuad.p1.set(1.0F, 0.0F);
        mTargetQuad.p2.set(0.0F, 1.0F);
        mTargetQuad.p3.set(1.0F, 1.0F);
        mProgram.setClearsOutput(false);
          goto _L11
_L7:
        if(f1 > 1.0F)
        {
            mTargetQuad.p0.set(0.5F - 0.5F / f1, 0.0F);
            mTargetQuad.p1.set(0.5F / f1 + 0.5F, 0.0F);
            mTargetQuad.p2.set(0.5F - 0.5F / f1, 1.0F);
            mTargetQuad.p3.set(0.5F / f1 + 0.5F, 1.0F);
        } else
        {
            mTargetQuad.p0.set(0.0F, 0.5F - 0.5F * f1);
            mTargetQuad.p1.set(1.0F, 0.5F - 0.5F * f1);
            mTargetQuad.p2.set(0.0F, 0.5F * f1 + 0.5F);
            mTargetQuad.p3.set(1.0F, 0.5F * f1 + 0.5F);
        }
        mProgram.setClearsOutput(true);
          goto _L11
_L8:
        if(f1 > 1.0F)
        {
            mTargetQuad.p0.set(0.0F, 0.5F - 0.5F * f1);
            mTargetQuad.p1.set(1.0F, 0.5F - 0.5F * f1);
            mTargetQuad.p2.set(0.0F, 0.5F * f1 + 0.5F);
            mTargetQuad.p3.set(1.0F, 0.5F * f1 + 0.5F);
        } else
        {
            mTargetQuad.p0.set(0.5F - 0.5F / f1, 0.0F);
            mTargetQuad.p1.set(0.5F / f1 + 0.5F, 0.0F);
            mTargetQuad.p2.set(0.5F - 0.5F / f1, 1.0F);
            mTargetQuad.p3.set(0.5F / f1 + 0.5F, 1.0F);
        }
        mProgram.setClearsOutput(true);
          goto _L11
        mProgram.setSourceRegion(mSourceQuad);
          goto _L11
    }

    public void close(FilterContext filtercontext)
    {
        this;
        JVM INSTR monitorenter ;
        if(mSurfaceId > 0)
        {
            filtercontext.getGLEnvironment().unregisterSurfaceId(mSurfaceId);
            mSurfaceId = -1;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        filtercontext;
        throw filtercontext;
    }

    public void disconnect(FilterContext filtercontext)
    {
        this;
        JVM INSTR monitorenter ;
        if(mLogVerbose)
            Log.v("SurfaceTextureTarget", "disconnect");
        if(mSurfaceTexture != null)
            break MISSING_BLOCK_LABEL_35;
        Log.d("SurfaceTextureTarget", "SurfaceTexture is already null. Nothing to disconnect.");
        this;
        JVM INSTR monitorexit ;
        return;
        mSurfaceTexture = null;
        if(mSurfaceId > 0)
        {
            filtercontext.getGLEnvironment().unregisterSurfaceId(mSurfaceId);
            mSurfaceId = -1;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        filtercontext;
        throw filtercontext;
    }

    public void fieldPortValueUpdated(String s, FilterContext filtercontext)
    {
        if(mLogVerbose)
            Log.v("SurfaceTextureTarget", (new StringBuilder()).append("FPVU. Thread: ").append(Thread.currentThread()).toString());
        updateRenderMode();
    }

    public void open(FilterContext filtercontext)
    {
        this;
        JVM INSTR monitorenter ;
        if(mSurfaceTexture == null)
        {
            Log.e("SurfaceTextureTarget", "SurfaceTexture is null!!");
            filtercontext = JVM INSTR new #205 <Class RuntimeException>;
            StringBuilder stringbuilder = JVM INSTR new #85  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            filtercontext.RuntimeException(stringbuilder.append("Could not register SurfaceTexture: ").append(mSurfaceTexture).toString());
            throw filtercontext;
        }
        break MISSING_BLOCK_LABEL_56;
        filtercontext;
        this;
        JVM INSTR monitorexit ;
        throw filtercontext;
        mSurfaceId = filtercontext.getGLEnvironment().registerSurfaceTexture(mSurfaceTexture, mScreenWidth, mScreenHeight);
        if(mSurfaceId <= 0)
        {
            filtercontext = JVM INSTR new #205 <Class RuntimeException>;
            StringBuilder stringbuilder1 = JVM INSTR new #85  <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            filtercontext.RuntimeException(stringbuilder1.append("Could not register SurfaceTexture: ").append(mSurfaceTexture).toString());
            throw filtercontext;
        }
        this;
        JVM INSTR monitorexit ;
    }

    public void prepare(FilterContext filtercontext)
    {
        if(mLogVerbose)
            Log.v("SurfaceTextureTarget", (new StringBuilder()).append("Prepare. Thread: ").append(Thread.currentThread()).toString());
        mProgram = ShaderProgram.createIdentity(filtercontext);
        mProgram.setSourceRect(0.0F, 1.0F, 1.0F, -1F);
        mProgram.setClearColor(0.0F, 0.0F, 0.0F);
        updateRenderMode();
        MutableFrameFormat mutableframeformat = new MutableFrameFormat(2, 3);
        mutableframeformat.setBytesPerSample(4);
        mutableframeformat.setDimensions(mScreenWidth, mScreenHeight);
        mScreen = (GLFrame)filtercontext.getFrameManager().newBoundFrame(mutableframeformat, 101, 0L);
    }

    public void process(FilterContext filtercontext)
    {
        this;
        JVM INSTR monitorenter ;
        int i = mSurfaceId;
        if(i > 0)
            break MISSING_BLOCK_LABEL_14;
        this;
        JVM INSTR monitorexit ;
        return;
        GLEnvironment glenvironment;
        Frame frame;
        glenvironment = filtercontext.getGLEnvironment();
        frame = pullInput("frame");
        i = 0;
        float f = (float)frame.getFormat().getWidth() / (float)frame.getFormat().getHeight();
        if(f != mAspectRatio)
        {
            if(mLogVerbose)
            {
                StringBuilder stringbuilder = JVM INSTR new #85  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.v("SurfaceTextureTarget", stringbuilder.append("Process. New aspect ratio: ").append(f).append(", previously: ").append(mAspectRatio).append(". Thread: ").append(Thread.currentThread()).toString());
            }
            mAspectRatio = f;
            updateTargetRect();
        }
        if(frame.getFormat().getTarget() == 3) goto _L2; else goto _L1
_L1:
        filtercontext = filtercontext.getFrameManager().duplicateFrameToTarget(frame, 3);
        i = 1;
_L4:
        glenvironment.activateSurfaceWithId(mSurfaceId);
        mProgram.process(filtercontext, mScreen);
        glenvironment.setSurfaceTimestamp(frame.getTimestamp());
        glenvironment.swapBuffers();
        if(!i)
            break MISSING_BLOCK_LABEL_201;
        filtercontext.release();
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        filtercontext = frame;
        if(true) goto _L4; else goto _L3
_L3:
        filtercontext;
        throw filtercontext;
    }

    public void setupPorts()
    {
        this;
        JVM INSTR monitorenter ;
        if(mSurfaceTexture == null)
        {
            RuntimeException runtimeexception = JVM INSTR new #205 <Class RuntimeException>;
            runtimeexception.RuntimeException("Null SurfaceTexture passed to SurfaceTextureTarget");
            throw runtimeexception;
        }
        break MISSING_BLOCK_LABEL_27;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        addMaskedInputPort("frame", ImageFormat.create(3));
        this;
        JVM INSTR monitorexit ;
    }

    public void tearDown(FilterContext filtercontext)
    {
        if(mScreen != null)
            mScreen.release();
    }

    public void updateRenderMode()
    {
        if(mLogVerbose)
            Log.v("SurfaceTextureTarget", (new StringBuilder()).append("updateRenderMode. Thread: ").append(Thread.currentThread()).toString());
        if(mRenderModeString != null)
            if(mRenderModeString.equals("stretch"))
                mRenderMode = 0;
            else
            if(mRenderModeString.equals("fit"))
                mRenderMode = 1;
            else
            if(mRenderModeString.equals("fill_crop"))
                mRenderMode = 2;
            else
            if(mRenderModeString.equals("customize"))
                mRenderMode = 3;
            else
                throw new RuntimeException((new StringBuilder()).append("Unknown render mode '").append(mRenderModeString).append("'!").toString());
        updateTargetRect();
    }

    private static final String TAG = "SurfaceTextureTarget";
    private final int RENDERMODE_CUSTOMIZE = 3;
    private final int RENDERMODE_FILL_CROP = 2;
    private final int RENDERMODE_FIT = 1;
    private final int RENDERMODE_STRETCH = 0;
    private float mAspectRatio;
    private boolean mLogVerbose;
    private ShaderProgram mProgram;
    private int mRenderMode;
    private String mRenderModeString;
    private GLFrame mScreen;
    private int mScreenHeight;
    private int mScreenWidth;
    private Quad mSourceQuad;
    private int mSurfaceId;
    private SurfaceTexture mSurfaceTexture;
    private Quad mTargetQuad;
}

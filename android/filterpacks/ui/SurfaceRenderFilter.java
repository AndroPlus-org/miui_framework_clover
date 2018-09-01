// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.ui;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;
import android.util.Log;
import android.view.SurfaceHolder;

public class SurfaceRenderFilter extends Filter
    implements android.view.SurfaceHolder.Callback
{

    public SurfaceRenderFilter(String s)
    {
        super(s);
        mIsBound = false;
        mRenderMode = 1;
        mAspectRatio = 1.0F;
        mLogVerbose = Log.isLoggable("SurfaceRenderFilter", 2);
    }

    private void updateTargetRect()
    {
        if(mScreenWidth <= 0 || mScreenHeight <= 0 || mProgram == null) goto _L2; else goto _L1
_L1:
        float f = (float)mScreenWidth / (float)mScreenHeight / mAspectRatio;
        mRenderMode;
        JVM INSTR tableswitch 0 2: default 68
    //                   0 69
    //                   1 83
    //                   2 131;
           goto _L2 _L3 _L4 _L5
_L2:
        return;
_L3:
        mProgram.setTargetRect(0.0F, 0.0F, 1.0F, 1.0F);
        continue; /* Loop/switch isn't completed */
_L4:
        if(f > 1.0F)
            mProgram.setTargetRect(0.5F - 0.5F / f, 0.0F, 1.0F / f, 1.0F);
        else
            mProgram.setTargetRect(0.0F, 0.5F - 0.5F * f, 1.0F, f);
        continue; /* Loop/switch isn't completed */
_L5:
        if(f > 1.0F)
            mProgram.setTargetRect(0.0F, 0.5F - 0.5F * f, 1.0F, f);
        else
            mProgram.setTargetRect(0.5F - 0.5F / f, 0.0F, 1.0F / f, 1.0F);
        if(true) goto _L2; else goto _L6
_L6:
    }

    public void close(FilterContext filtercontext)
    {
        mSurfaceView.unbind();
    }

    public void fieldPortValueUpdated(String s, FilterContext filtercontext)
    {
        updateTargetRect();
    }

    public void open(FilterContext filtercontext)
    {
        mSurfaceView.unbind();
        mSurfaceView.bindToListener(this, filtercontext.getGLEnvironment());
    }

    public void prepare(FilterContext filtercontext)
    {
        mProgram = ShaderProgram.createIdentity(filtercontext);
        mProgram.setSourceRect(0.0F, 1.0F, 1.0F, -1F);
        mProgram.setClearsOutput(true);
        mProgram.setClearColor(0.0F, 0.0F, 0.0F);
        updateRenderMode();
        android.filterfw.core.MutableFrameFormat mutableframeformat = ImageFormat.create(mSurfaceView.getWidth(), mSurfaceView.getHeight(), 3, 3);
        mScreen = (GLFrame)filtercontext.getFrameManager().newBoundFrame(mutableframeformat, 101, 0L);
    }

    public void process(FilterContext filtercontext)
    {
        if(!mIsBound)
        {
            Log.w("SurfaceRenderFilter", (new StringBuilder()).append(this).append(": Ignoring frame as there is no surface to render to!").toString());
            return;
        }
        if(mLogVerbose)
            Log.v("SurfaceRenderFilter", "Starting frame processing");
        GLEnvironment glenvironment = mSurfaceView.getGLEnv();
        if(glenvironment != filtercontext.getGLEnvironment())
            throw new RuntimeException("Surface created under different GLEnvironment!");
        Frame frame = pullInput("frame");
        boolean flag = false;
        float f = (float)frame.getFormat().getWidth() / (float)frame.getFormat().getHeight();
        if(f != mAspectRatio)
        {
            if(mLogVerbose)
                Log.v("SurfaceRenderFilter", (new StringBuilder()).append("New aspect ratio: ").append(f).append(", previously: ").append(mAspectRatio).toString());
            mAspectRatio = f;
            updateTargetRect();
        }
        if(mLogVerbose)
            Log.v("SurfaceRenderFilter", (new StringBuilder()).append("Got input format: ").append(frame.getFormat()).toString());
        if(frame.getFormat().getTarget() != 3)
        {
            filtercontext = filtercontext.getFrameManager().duplicateFrameToTarget(frame, 3);
            flag = true;
        } else
        {
            filtercontext = frame;
        }
        glenvironment.activateSurfaceWithId(mSurfaceView.getSurfaceId());
        mProgram.process(filtercontext, mScreen);
        glenvironment.swapBuffers();
        if(flag)
            filtercontext.release();
    }

    public void setupPorts()
    {
        if(mSurfaceView == null)
        {
            throw new RuntimeException("NULL SurfaceView passed to SurfaceRenderFilter");
        } else
        {
            addMaskedInputPort("frame", ImageFormat.create(3));
            return;
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
    {
        this;
        JVM INSTR monitorenter ;
        if(mScreen != null)
        {
            mScreenWidth = j;
            mScreenHeight = k;
            mScreen.setViewport(0, 0, mScreenWidth, mScreenHeight);
            updateTargetRect();
        }
        this;
        JVM INSTR monitorexit ;
        return;
        surfaceholder;
        throw surfaceholder;
    }

    public void surfaceCreated(SurfaceHolder surfaceholder)
    {
        this;
        JVM INSTR monitorenter ;
        mIsBound = true;
        this;
        JVM INSTR monitorexit ;
        return;
        surfaceholder;
        throw surfaceholder;
    }

    public void surfaceDestroyed(SurfaceHolder surfaceholder)
    {
        this;
        JVM INSTR monitorenter ;
        mIsBound = false;
        this;
        JVM INSTR monitorexit ;
        return;
        surfaceholder;
        throw surfaceholder;
    }

    public void tearDown(FilterContext filtercontext)
    {
        if(mScreen != null)
            mScreen.release();
    }

    public void updateRenderMode()
    {
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
                throw new RuntimeException((new StringBuilder()).append("Unknown render mode '").append(mRenderModeString).append("'!").toString());
        updateTargetRect();
    }

    private static final String TAG = "SurfaceRenderFilter";
    private final int RENDERMODE_FILL_CROP = 2;
    private final int RENDERMODE_FIT = 1;
    private final int RENDERMODE_STRETCH = 0;
    private float mAspectRatio;
    private boolean mIsBound;
    private boolean mLogVerbose;
    private ShaderProgram mProgram;
    private int mRenderMode;
    private String mRenderModeString;
    private GLFrame mScreen;
    private int mScreenHeight;
    private int mScreenWidth;
    private FilterSurfaceView mSurfaceView;
}

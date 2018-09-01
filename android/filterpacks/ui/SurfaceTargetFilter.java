// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.ui;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;
import android.util.Log;
import android.view.Surface;

public class SurfaceTargetFilter extends Filter
{

    public SurfaceTargetFilter(String s)
    {
        super(s);
        mRenderMode = 1;
        mAspectRatio = 1.0F;
        mSurfaceId = -1;
        mLogVerbose = Log.isLoggable("SurfaceRenderFilter", 2);
    }

    private void registerSurface()
    {
        mSurfaceId = mGlEnv.registerSurface(mSurface);
        if(mSurfaceId < 0)
            throw new RuntimeException((new StringBuilder()).append("Could not register Surface: ").append(mSurface).toString());
        else
            return;
    }

    private void unregisterSurface()
    {
        if(mSurfaceId > 0)
            mGlEnv.unregisterSurfaceId(mSurfaceId);
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
        unregisterSurface();
    }

    public void fieldPortValueUpdated(String s, FilterContext filtercontext)
    {
        mScreen.setViewport(0, 0, mScreenWidth, mScreenHeight);
        updateTargetRect();
    }

    public void open(FilterContext filtercontext)
    {
        registerSurface();
    }

    public void prepare(FilterContext filtercontext)
    {
        mGlEnv = filtercontext.getGLEnvironment();
        mProgram = ShaderProgram.createIdentity(filtercontext);
        mProgram.setSourceRect(0.0F, 1.0F, 1.0F, -1F);
        mProgram.setClearsOutput(true);
        mProgram.setClearColor(0.0F, 0.0F, 0.0F);
        android.filterfw.core.MutableFrameFormat mutableframeformat = ImageFormat.create(mScreenWidth, mScreenHeight, 3, 3);
        mScreen = (GLFrame)filtercontext.getFrameManager().newBoundFrame(mutableframeformat, 101, 0L);
        updateRenderMode();
    }

    public void process(FilterContext filtercontext)
    {
        if(mLogVerbose)
            Log.v("SurfaceRenderFilter", "Starting frame processing");
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
        mGlEnv.activateSurfaceWithId(mSurfaceId);
        mProgram.process(filtercontext, mScreen);
        mGlEnv.swapBuffers();
        if(flag)
            filtercontext.release();
    }

    public void setupPorts()
    {
        if(mSurface == null)
        {
            throw new RuntimeException("NULL Surface passed to SurfaceTargetFilter");
        } else
        {
            addMaskedInputPort("frame", ImageFormat.create(3));
            return;
        }
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
    private GLEnvironment mGlEnv;
    private boolean mLogVerbose;
    private ShaderProgram mProgram;
    private int mRenderMode;
    private String mRenderModeString;
    private GLFrame mScreen;
    private int mScreenHeight;
    private int mScreenWidth;
    private Surface mSurface;
    private int mSurfaceId;
}

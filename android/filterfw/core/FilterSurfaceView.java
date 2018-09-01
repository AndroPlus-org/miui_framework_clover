// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

// Referenced classes of package android.filterfw.core:
//            GLEnvironment

public class FilterSurfaceView extends SurfaceView
    implements android.view.SurfaceHolder.Callback
{

    public FilterSurfaceView(Context context)
    {
        super(context);
        mState = STATE_ALLOCATED;
        mSurfaceId = -1;
        getHolder().addCallback(this);
    }

    public FilterSurfaceView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mState = STATE_ALLOCATED;
        mSurfaceId = -1;
        getHolder().addCallback(this);
    }

    private void registerSurface()
    {
        mSurfaceId = mGLEnv.registerSurface(getHolder().getSurface());
        if(mSurfaceId < 0)
            throw new RuntimeException((new StringBuilder()).append("Could not register Surface: ").append(getHolder().getSurface()).append(" in FilterSurfaceView!").toString());
        else
            return;
    }

    private void unregisterSurface()
    {
        if(mGLEnv != null && mSurfaceId > 0)
            mGLEnv.unregisterSurfaceId(mSurfaceId);
    }

    public void bindToListener(android.view.SurfaceHolder.Callback callback, GLEnvironment glenvironment)
    {
        this;
        JVM INSTR monitorenter ;
        if(callback != null)
            break MISSING_BLOCK_LABEL_23;
        callback = JVM INSTR new #94  <Class NullPointerException>;
        callback.NullPointerException("Attempting to bind null filter to SurfaceView!");
        throw callback;
        callback;
        this;
        JVM INSTR monitorexit ;
        throw callback;
        if(mListener != null && mListener != callback)
        {
            RuntimeException runtimeexception = JVM INSTR new #63  <Class RuntimeException>;
            glenvironment = JVM INSTR new #65  <Class StringBuilder>;
            glenvironment.StringBuilder();
            runtimeexception.RuntimeException(glenvironment.append("Attempting to bind filter ").append(callback).append(" to SurfaceView with another open ").append("filter ").append(mListener).append(" attached already!").toString());
            throw runtimeexception;
        }
        mListener = callback;
        if(mGLEnv != null && mGLEnv != glenvironment)
            mGLEnv.unregisterSurfaceId(mSurfaceId);
        mGLEnv = glenvironment;
        if(mState >= STATE_CREATED)
        {
            registerSurface();
            mListener.surfaceCreated(getHolder());
            if(mState == STATE_INITIALIZED)
                mListener.surfaceChanged(getHolder(), mFormat, mWidth, mHeight);
        }
        this;
        JVM INSTR monitorexit ;
    }

    public GLEnvironment getGLEnv()
    {
        this;
        JVM INSTR monitorenter ;
        GLEnvironment glenvironment = mGLEnv;
        this;
        JVM INSTR monitorexit ;
        return glenvironment;
        Exception exception;
        exception;
        throw exception;
    }

    public int getSurfaceId()
    {
        this;
        JVM INSTR monitorenter ;
        int i = mSurfaceId;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
    {
        this;
        JVM INSTR monitorenter ;
        mFormat = i;
        mWidth = j;
        mHeight = k;
        mState = STATE_INITIALIZED;
        if(mListener != null)
            mListener.surfaceChanged(surfaceholder, i, j, k);
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
        mState = STATE_CREATED;
        if(mGLEnv != null)
            registerSurface();
        if(mListener != null)
            mListener.surfaceCreated(surfaceholder);
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
        mState = STATE_ALLOCATED;
        if(mListener != null)
            mListener.surfaceDestroyed(surfaceholder);
        unregisterSurface();
        this;
        JVM INSTR monitorexit ;
        return;
        surfaceholder;
        throw surfaceholder;
    }

    public void unbind()
    {
        this;
        JVM INSTR monitorenter ;
        mListener = null;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static int STATE_ALLOCATED = 0;
    private static int STATE_CREATED = 1;
    private static int STATE_INITIALIZED = 2;
    private int mFormat;
    private GLEnvironment mGLEnv;
    private int mHeight;
    private android.view.SurfaceHolder.Callback mListener;
    private int mState;
    private int mSurfaceId;
    private int mWidth;

}

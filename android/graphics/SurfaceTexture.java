// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import android.os.*;
import java.lang.ref.WeakReference;

public class SurfaceTexture
{
    public static interface OnFrameAvailableListener
    {

        public abstract void onFrameAvailable(SurfaceTexture surfacetexture);
    }

    public static class OutOfResourcesException extends Exception
    {

        public OutOfResourcesException()
        {
        }

        public OutOfResourcesException(String s)
        {
            super(s);
        }
    }


    public SurfaceTexture(int i)
    {
        this(i, false);
    }

    public SurfaceTexture(int i, boolean flag)
    {
        mCreatorLooper = Looper.myLooper();
        mIsSingleBuffered = flag;
        nativeInit(false, i, flag, new WeakReference(this));
    }

    public SurfaceTexture(boolean flag)
    {
        mCreatorLooper = Looper.myLooper();
        mIsSingleBuffered = flag;
        nativeInit(true, 0, flag, new WeakReference(this));
    }

    private native int nativeAttachToGLContext(int i);

    private native int nativeDetachFromGLContext();

    private native void nativeFinalize();

    private native long nativeGetTimestamp();

    private native void nativeGetTransformMatrix(float af[]);

    private native void nativeInit(boolean flag, int i, boolean flag1, WeakReference weakreference)
        throws android.view.Surface.OutOfResourcesException;

    private native boolean nativeIsReleased();

    private native void nativeRelease();

    private native void nativeReleaseTexImage();

    private native void nativeSetDefaultBufferSize(int i, int j);

    private native void nativeUpdateTexImage();

    private static void postEventFromNative(WeakReference weakreference)
    {
        weakreference = (SurfaceTexture)weakreference.get();
        if(weakreference != null)
        {
            weakreference = ((SurfaceTexture) (weakreference)).mOnFrameAvailableHandler;
            if(weakreference != null)
                weakreference.sendEmptyMessage(0);
        }
    }

    public void attachToGLContext(int i)
    {
        if(nativeAttachToGLContext(i) != 0)
            throw new RuntimeException("Error during attachToGLContext (see logcat for details)");
        else
            return;
    }

    public void detachFromGLContext()
    {
        if(nativeDetachFromGLContext() != 0)
            throw new RuntimeException("Error during detachFromGLContext (see logcat for details)");
        else
            return;
    }

    protected void finalize()
        throws Throwable
    {
        nativeFinalize();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public long getTimestamp()
    {
        return nativeGetTimestamp();
    }

    public void getTransformMatrix(float af[])
    {
        if(af.length != 16)
        {
            throw new IllegalArgumentException();
        } else
        {
            nativeGetTransformMatrix(af);
            return;
        }
    }

    public boolean isReleased()
    {
        return nativeIsReleased();
    }

    public boolean isSingleBuffered()
    {
        return mIsSingleBuffered;
    }

    public void release()
    {
        nativeRelease();
    }

    public void releaseTexImage()
    {
        nativeReleaseTexImage();
    }

    public void setDefaultBufferSize(int i, int j)
    {
        nativeSetDefaultBufferSize(i, j);
    }

    public void setOnFrameAvailableListener(OnFrameAvailableListener onframeavailablelistener)
    {
        setOnFrameAvailableListener(onframeavailablelistener, null);
    }

    public void setOnFrameAvailableListener(OnFrameAvailableListener onframeavailablelistener, final Handler final_looper)
    {
        if(onframeavailablelistener != null)
        {
            if(final_looper != null)
                final_looper = final_looper.getLooper();
            else
            if(mCreatorLooper != null)
                final_looper = mCreatorLooper;
            else
                final_looper = Looper.getMainLooper();
            mOnFrameAvailableHandler = new Handler(null, true, onframeavailablelistener) {

                public void handleMessage(Message message)
                {
                    listener.onFrameAvailable(SurfaceTexture.this);
                }

                final SurfaceTexture this$0;
                final OnFrameAvailableListener val$listener;

            
            {
                this$0 = SurfaceTexture.this;
                listener = onframeavailablelistener;
                super(final_looper, callback, flag);
            }
            }
;
        } else
        {
            mOnFrameAvailableHandler = null;
        }
    }

    public void updateTexImage()
    {
        nativeUpdateTexImage();
    }

    private final Looper mCreatorLooper;
    private long mFrameAvailableListener;
    private boolean mIsSingleBuffered;
    private Handler mOnFrameAvailableHandler;
    private long mProducer;
    private long mSurfaceTexture;
}

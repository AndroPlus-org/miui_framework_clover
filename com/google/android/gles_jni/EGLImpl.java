// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gles_jni;

import android.graphics.SurfaceTexture;
import android.view.*;
import javax.microedition.khronos.egl.*;

// Referenced classes of package com.google.android.gles_jni:
//            EGLContextImpl, EGLDisplayImpl, EGLSurfaceImpl

public class EGLImpl
    implements EGL10
{

    public EGLImpl()
    {
        mContext = new EGLContextImpl(-1L);
        mDisplay = new EGLDisplayImpl(-1L);
        mSurface = new EGLSurfaceImpl(-1L);
    }

    private native long _eglCreateContext(EGLDisplay egldisplay, EGLConfig eglconfig, EGLContext eglcontext, int ai[]);

    private native long _eglCreatePbufferSurface(EGLDisplay egldisplay, EGLConfig eglconfig, int ai[]);

    private native void _eglCreatePixmapSurface(EGLSurface eglsurface, EGLDisplay egldisplay, EGLConfig eglconfig, Object obj, int ai[]);

    private native long _eglCreateWindowSurface(EGLDisplay egldisplay, EGLConfig eglconfig, Object obj, int ai[]);

    private native long _eglCreateWindowSurfaceTexture(EGLDisplay egldisplay, EGLConfig eglconfig, Object obj, int ai[]);

    private native long _eglGetCurrentContext();

    private native long _eglGetCurrentDisplay();

    private native long _eglGetCurrentSurface(int i);

    private native long _eglGetDisplay(Object obj);

    private static native void _nativeClassInit();

    public static native int getInitCount(EGLDisplay egldisplay);

    public native boolean eglChooseConfig(EGLDisplay egldisplay, int ai[], EGLConfig aeglconfig[], int i, int ai1[]);

    public native boolean eglCopyBuffers(EGLDisplay egldisplay, EGLSurface eglsurface, Object obj);

    public EGLContext eglCreateContext(EGLDisplay egldisplay, EGLConfig eglconfig, EGLContext eglcontext, int ai[])
    {
        long l = _eglCreateContext(egldisplay, eglconfig, eglcontext, ai);
        if(l == 0L)
            return EGL10.EGL_NO_CONTEXT;
        else
            return new EGLContextImpl(l);
    }

    public EGLSurface eglCreatePbufferSurface(EGLDisplay egldisplay, EGLConfig eglconfig, int ai[])
    {
        long l = _eglCreatePbufferSurface(egldisplay, eglconfig, ai);
        if(l == 0L)
            return EGL10.EGL_NO_SURFACE;
        else
            return new EGLSurfaceImpl(l);
    }

    public EGLSurface eglCreatePixmapSurface(EGLDisplay egldisplay, EGLConfig eglconfig, Object obj, int ai[])
    {
        EGLSurfaceImpl eglsurfaceimpl = new EGLSurfaceImpl();
        _eglCreatePixmapSurface(eglsurfaceimpl, egldisplay, eglconfig, obj, ai);
        if(eglsurfaceimpl.mEGLSurface == 0L)
            return EGL10.EGL_NO_SURFACE;
        else
            return eglsurfaceimpl;
    }

    public EGLSurface eglCreateWindowSurface(EGLDisplay egldisplay, EGLConfig eglconfig, Object obj, int ai[])
    {
        Surface surface = null;
        if(!(obj instanceof SurfaceView)) goto _L2; else goto _L1
_L1:
        surface = ((SurfaceView)obj).getHolder().getSurface();
_L4:
        long l;
        if(surface != null)
        {
            l = _eglCreateWindowSurface(egldisplay, eglconfig, surface, ai);
        } else
        {
label0:
            {
                if(!(obj instanceof SurfaceTexture))
                    break label0;
                l = _eglCreateWindowSurfaceTexture(egldisplay, eglconfig, obj, ai);
            }
        }
        if(l == 0L)
            return EGL10.EGL_NO_SURFACE;
        else
            return new EGLSurfaceImpl(l);
_L2:
        if(obj instanceof SurfaceHolder)
            surface = ((SurfaceHolder)obj).getSurface();
        else
        if(obj instanceof Surface)
            surface = (Surface)obj;
        continue; /* Loop/switch isn't completed */
        throw new UnsupportedOperationException("eglCreateWindowSurface() can only be called with an instance of Surface, SurfaceView, SurfaceHolder or SurfaceTexture at the moment.");
        if(true) goto _L4; else goto _L3
_L3:
    }

    public native boolean eglDestroyContext(EGLDisplay egldisplay, EGLContext eglcontext);

    public native boolean eglDestroySurface(EGLDisplay egldisplay, EGLSurface eglsurface);

    public native boolean eglGetConfigAttrib(EGLDisplay egldisplay, EGLConfig eglconfig, int i, int ai[]);

    public native boolean eglGetConfigs(EGLDisplay egldisplay, EGLConfig aeglconfig[], int i, int ai[]);

    public EGLContext eglGetCurrentContext()
    {
        this;
        JVM INSTR monitorenter ;
        long l = _eglGetCurrentContext();
        if(l != 0L)
            break MISSING_BLOCK_LABEL_21;
        Object obj = EGL10.EGL_NO_CONTEXT;
        this;
        JVM INSTR monitorexit ;
        return ((EGLContext) (obj));
        if(mContext.mEGLContext != l)
        {
            obj = JVM INSTR new #23  <Class EGLContextImpl>;
            ((EGLContextImpl) (obj)).EGLContextImpl(l);
            mContext = ((EGLContextImpl) (obj));
        }
        obj = mContext;
        this;
        JVM INSTR monitorexit ;
        return ((EGLContext) (obj));
        Exception exception;
        exception;
        throw exception;
    }

    public EGLDisplay eglGetCurrentDisplay()
    {
        this;
        JVM INSTR monitorenter ;
        long l = _eglGetCurrentDisplay();
        if(l != 0L)
            break MISSING_BLOCK_LABEL_21;
        Object obj = EGL10.EGL_NO_DISPLAY;
        this;
        JVM INSTR monitorexit ;
        return ((EGLDisplay) (obj));
        if(mDisplay.mEGLDisplay != l)
        {
            obj = JVM INSTR new #32  <Class EGLDisplayImpl>;
            ((EGLDisplayImpl) (obj)).EGLDisplayImpl(l);
            mDisplay = ((EGLDisplayImpl) (obj));
        }
        obj = mDisplay;
        this;
        JVM INSTR monitorexit ;
        return ((EGLDisplay) (obj));
        Exception exception;
        exception;
        throw exception;
    }

    public EGLSurface eglGetCurrentSurface(int i)
    {
        this;
        JVM INSTR monitorenter ;
        long l = _eglGetCurrentSurface(i);
        if(l != 0L)
            break MISSING_BLOCK_LABEL_24;
        Object obj = EGL10.EGL_NO_SURFACE;
        this;
        JVM INSTR monitorexit ;
        return ((EGLSurface) (obj));
        if(mSurface.mEGLSurface != l)
        {
            obj = JVM INSTR new #37  <Class EGLSurfaceImpl>;
            ((EGLSurfaceImpl) (obj)).EGLSurfaceImpl(l);
            mSurface = ((EGLSurfaceImpl) (obj));
        }
        obj = mSurface;
        this;
        JVM INSTR monitorexit ;
        return ((EGLSurface) (obj));
        Exception exception;
        exception;
        throw exception;
    }

    public EGLDisplay eglGetDisplay(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        long l = _eglGetDisplay(obj);
        if(l != 0L)
            break MISSING_BLOCK_LABEL_22;
        obj = EGL10.EGL_NO_DISPLAY;
        this;
        JVM INSTR monitorexit ;
        return ((EGLDisplay) (obj));
        if(mDisplay.mEGLDisplay != l)
        {
            obj = JVM INSTR new #32  <Class EGLDisplayImpl>;
            ((EGLDisplayImpl) (obj)).EGLDisplayImpl(l);
            mDisplay = ((EGLDisplayImpl) (obj));
        }
        obj = mDisplay;
        this;
        JVM INSTR monitorexit ;
        return ((EGLDisplay) (obj));
        obj;
        throw obj;
    }

    public native int eglGetError();

    public native boolean eglInitialize(EGLDisplay egldisplay, int ai[]);

    public native boolean eglMakeCurrent(EGLDisplay egldisplay, EGLSurface eglsurface, EGLSurface eglsurface1, EGLContext eglcontext);

    public native boolean eglQueryContext(EGLDisplay egldisplay, EGLContext eglcontext, int i, int ai[]);

    public native String eglQueryString(EGLDisplay egldisplay, int i);

    public native boolean eglQuerySurface(EGLDisplay egldisplay, EGLSurface eglsurface, int i, int ai[]);

    public native boolean eglReleaseThread();

    public native boolean eglSwapBuffers(EGLDisplay egldisplay, EGLSurface eglsurface);

    public native boolean eglTerminate(EGLDisplay egldisplay);

    public native boolean eglWaitGL();

    public native boolean eglWaitNative(int i, Object obj);

    private EGLContextImpl mContext;
    private EGLDisplayImpl mDisplay;
    private EGLSurfaceImpl mSurface;

    static 
    {
        _nativeClassInit();
    }
}

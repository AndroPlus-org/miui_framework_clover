// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.opengl;


// Referenced classes of package android.opengl:
//            EGLDisplay, EGLSurface

public class EGLExt
{

    public EGLExt()
    {
    }

    private static native void _nativeClassInit();

    public static native boolean eglPresentationTimeANDROID(EGLDisplay egldisplay, EGLSurface eglsurface, long l);

    public static final int EGL_CONTEXT_FLAGS_KHR = 12540;
    public static final int EGL_CONTEXT_MAJOR_VERSION_KHR = 12440;
    public static final int EGL_CONTEXT_MINOR_VERSION_KHR = 12539;
    public static final int EGL_OPENGL_ES3_BIT_KHR = 64;
    public static final int EGL_RECORDABLE_ANDROID = 12610;

    static 
    {
        _nativeClassInit();
    }
}

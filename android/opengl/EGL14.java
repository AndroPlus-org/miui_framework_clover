// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.opengl;

import android.graphics.SurfaceTexture;
import android.view.*;

// Referenced classes of package android.opengl:
//            EGLContext, EGLDisplay, EGLSurface, EGLConfig

public class EGL14
{

    public EGL14()
    {
    }

    private static native EGLSurface _eglCreateWindowSurface(EGLDisplay egldisplay, EGLConfig eglconfig, Object obj, int ai[], int i);

    private static native EGLSurface _eglCreateWindowSurfaceTexture(EGLDisplay egldisplay, EGLConfig eglconfig, Object obj, int ai[], int i);

    private static native void _nativeClassInit();

    public static native boolean eglBindAPI(int i);

    public static native boolean eglBindTexImage(EGLDisplay egldisplay, EGLSurface eglsurface, int i);

    public static native boolean eglChooseConfig(EGLDisplay egldisplay, int ai[], int i, EGLConfig aeglconfig[], int j, int k, int ai1[], int l);

    public static native boolean eglCopyBuffers(EGLDisplay egldisplay, EGLSurface eglsurface, int i);

    public static native EGLContext eglCreateContext(EGLDisplay egldisplay, EGLConfig eglconfig, EGLContext eglcontext, int ai[], int i);

    public static native EGLSurface eglCreatePbufferFromClientBuffer(EGLDisplay egldisplay, int i, int j, EGLConfig eglconfig, int ai[], int k);

    public static native EGLSurface eglCreatePbufferFromClientBuffer(EGLDisplay egldisplay, int i, long l, EGLConfig eglconfig, int ai[], int j);

    public static native EGLSurface eglCreatePbufferSurface(EGLDisplay egldisplay, EGLConfig eglconfig, int ai[], int i);

    public static native EGLSurface eglCreatePixmapSurface(EGLDisplay egldisplay, EGLConfig eglconfig, int i, int ai[], int j);

    public static EGLSurface eglCreateWindowSurface(EGLDisplay egldisplay, EGLConfig eglconfig, Object obj, int ai[], int i)
    {
        Surface surface = null;
        if(!(obj instanceof SurfaceView)) goto _L2; else goto _L1
_L1:
        surface = ((SurfaceView)obj).getHolder().getSurface();
_L8:
        if(surface == null) goto _L4; else goto _L3
_L3:
        egldisplay = _eglCreateWindowSurface(egldisplay, eglconfig, surface, ai, i);
_L6:
        return egldisplay;
_L2:
        if(obj instanceof SurfaceHolder)
            surface = ((SurfaceHolder)obj).getSurface();
        else
        if(obj instanceof Surface)
            surface = (Surface)obj;
        continue; /* Loop/switch isn't completed */
_L4:
        if(!(obj instanceof SurfaceTexture))
            break; /* Loop/switch isn't completed */
        egldisplay = _eglCreateWindowSurfaceTexture(egldisplay, eglconfig, obj, ai, i);
        if(true) goto _L6; else goto _L5
_L5:
        throw new UnsupportedOperationException("eglCreateWindowSurface() can only be called with an instance of Surface, SurfaceView, SurfaceTexture or SurfaceHolder at the moment, this will be fixed later.");
        if(true) goto _L8; else goto _L7
_L7:
    }

    public static native boolean eglDestroyContext(EGLDisplay egldisplay, EGLContext eglcontext);

    public static native boolean eglDestroySurface(EGLDisplay egldisplay, EGLSurface eglsurface);

    public static native boolean eglGetConfigAttrib(EGLDisplay egldisplay, EGLConfig eglconfig, int i, int ai[], int j);

    public static native boolean eglGetConfigs(EGLDisplay egldisplay, EGLConfig aeglconfig[], int i, int j, int ai[], int k);

    public static native EGLContext eglGetCurrentContext();

    public static native EGLDisplay eglGetCurrentDisplay();

    public static native EGLSurface eglGetCurrentSurface(int i);

    public static native EGLDisplay eglGetDisplay(int i);

    public static native EGLDisplay eglGetDisplay(long l);

    public static native int eglGetError();

    public static native boolean eglInitialize(EGLDisplay egldisplay, int ai[], int i, int ai1[], int j);

    public static native boolean eglMakeCurrent(EGLDisplay egldisplay, EGLSurface eglsurface, EGLSurface eglsurface1, EGLContext eglcontext);

    public static native int eglQueryAPI();

    public static native boolean eglQueryContext(EGLDisplay egldisplay, EGLContext eglcontext, int i, int ai[], int j);

    public static native String eglQueryString(EGLDisplay egldisplay, int i);

    public static native boolean eglQuerySurface(EGLDisplay egldisplay, EGLSurface eglsurface, int i, int ai[], int j);

    public static native boolean eglReleaseTexImage(EGLDisplay egldisplay, EGLSurface eglsurface, int i);

    public static native boolean eglReleaseThread();

    public static native boolean eglSurfaceAttrib(EGLDisplay egldisplay, EGLSurface eglsurface, int i, int j);

    public static native boolean eglSwapBuffers(EGLDisplay egldisplay, EGLSurface eglsurface);

    public static native boolean eglSwapInterval(EGLDisplay egldisplay, int i);

    public static native boolean eglTerminate(EGLDisplay egldisplay);

    public static native boolean eglWaitClient();

    public static native boolean eglWaitGL();

    public static native boolean eglWaitNative(int i);

    public static final int EGL_ALPHA_MASK_SIZE = 12350;
    public static final int EGL_ALPHA_SIZE = 12321;
    public static final int EGL_BACK_BUFFER = 12420;
    public static final int EGL_BAD_ACCESS = 12290;
    public static final int EGL_BAD_ALLOC = 12291;
    public static final int EGL_BAD_ATTRIBUTE = 12292;
    public static final int EGL_BAD_CONFIG = 12293;
    public static final int EGL_BAD_CONTEXT = 12294;
    public static final int EGL_BAD_CURRENT_SURFACE = 12295;
    public static final int EGL_BAD_DISPLAY = 12296;
    public static final int EGL_BAD_MATCH = 12297;
    public static final int EGL_BAD_NATIVE_PIXMAP = 12298;
    public static final int EGL_BAD_NATIVE_WINDOW = 12299;
    public static final int EGL_BAD_PARAMETER = 12300;
    public static final int EGL_BAD_SURFACE = 12301;
    public static final int EGL_BIND_TO_TEXTURE_RGB = 12345;
    public static final int EGL_BIND_TO_TEXTURE_RGBA = 12346;
    public static final int EGL_BLUE_SIZE = 12322;
    public static final int EGL_BUFFER_DESTROYED = 12437;
    public static final int EGL_BUFFER_PRESERVED = 12436;
    public static final int EGL_BUFFER_SIZE = 12320;
    public static final int EGL_CLIENT_APIS = 12429;
    public static final int EGL_COLOR_BUFFER_TYPE = 12351;
    public static final int EGL_CONFIG_CAVEAT = 12327;
    public static final int EGL_CONFIG_ID = 12328;
    public static final int EGL_CONFORMANT = 12354;
    public static final int EGL_CONTEXT_CLIENT_TYPE = 12439;
    public static final int EGL_CONTEXT_CLIENT_VERSION = 12440;
    public static final int EGL_CONTEXT_LOST = 12302;
    public static final int EGL_CORE_NATIVE_ENGINE = 12379;
    public static final int EGL_DEFAULT_DISPLAY = 0;
    public static final int EGL_DEPTH_SIZE = 12325;
    public static final int EGL_DISPLAY_SCALING = 10000;
    public static final int EGL_DRAW = 12377;
    public static final int EGL_EXTENSIONS = 12373;
    public static final int EGL_FALSE = 0;
    public static final int EGL_GREEN_SIZE = 12323;
    public static final int EGL_HEIGHT = 12374;
    public static final int EGL_HORIZONTAL_RESOLUTION = 12432;
    public static final int EGL_LARGEST_PBUFFER = 12376;
    public static final int EGL_LEVEL = 12329;
    public static final int EGL_LUMINANCE_BUFFER = 12431;
    public static final int EGL_LUMINANCE_SIZE = 12349;
    public static final int EGL_MATCH_NATIVE_PIXMAP = 12353;
    public static final int EGL_MAX_PBUFFER_HEIGHT = 12330;
    public static final int EGL_MAX_PBUFFER_PIXELS = 12331;
    public static final int EGL_MAX_PBUFFER_WIDTH = 12332;
    public static final int EGL_MAX_SWAP_INTERVAL = 12348;
    public static final int EGL_MIN_SWAP_INTERVAL = 12347;
    public static final int EGL_MIPMAP_LEVEL = 12419;
    public static final int EGL_MIPMAP_TEXTURE = 12418;
    public static final int EGL_MULTISAMPLE_RESOLVE = 12441;
    public static final int EGL_MULTISAMPLE_RESOLVE_BOX = 12443;
    public static final int EGL_MULTISAMPLE_RESOLVE_BOX_BIT = 512;
    public static final int EGL_MULTISAMPLE_RESOLVE_DEFAULT = 12442;
    public static final int EGL_NATIVE_RENDERABLE = 12333;
    public static final int EGL_NATIVE_VISUAL_ID = 12334;
    public static final int EGL_NATIVE_VISUAL_TYPE = 12335;
    public static final int EGL_NONE = 12344;
    public static final int EGL_NON_CONFORMANT_CONFIG = 12369;
    public static final int EGL_NOT_INITIALIZED = 12289;
    public static EGLContext EGL_NO_CONTEXT = null;
    public static EGLDisplay EGL_NO_DISPLAY = null;
    public static EGLSurface EGL_NO_SURFACE = null;
    public static final int EGL_NO_TEXTURE = 12380;
    public static final int EGL_OPENGL_API = 12450;
    public static final int EGL_OPENGL_BIT = 8;
    public static final int EGL_OPENGL_ES2_BIT = 4;
    public static final int EGL_OPENGL_ES_API = 12448;
    public static final int EGL_OPENGL_ES_BIT = 1;
    public static final int EGL_OPENVG_API = 12449;
    public static final int EGL_OPENVG_BIT = 2;
    public static final int EGL_OPENVG_IMAGE = 12438;
    public static final int EGL_PBUFFER_BIT = 1;
    public static final int EGL_PIXEL_ASPECT_RATIO = 12434;
    public static final int EGL_PIXMAP_BIT = 2;
    public static final int EGL_READ = 12378;
    public static final int EGL_RED_SIZE = 12324;
    public static final int EGL_RENDERABLE_TYPE = 12352;
    public static final int EGL_RENDER_BUFFER = 12422;
    public static final int EGL_RGB_BUFFER = 12430;
    public static final int EGL_SAMPLES = 12337;
    public static final int EGL_SAMPLE_BUFFERS = 12338;
    public static final int EGL_SINGLE_BUFFER = 12421;
    public static final int EGL_SLOW_CONFIG = 12368;
    public static final int EGL_STENCIL_SIZE = 12326;
    public static final int EGL_SUCCESS = 12288;
    public static final int EGL_SURFACE_TYPE = 12339;
    public static final int EGL_SWAP_BEHAVIOR = 12435;
    public static final int EGL_SWAP_BEHAVIOR_PRESERVED_BIT = 1024;
    public static final int EGL_TEXTURE_2D = 12383;
    public static final int EGL_TEXTURE_FORMAT = 12416;
    public static final int EGL_TEXTURE_RGB = 12381;
    public static final int EGL_TEXTURE_RGBA = 12382;
    public static final int EGL_TEXTURE_TARGET = 12417;
    public static final int EGL_TRANSPARENT_BLUE_VALUE = 12341;
    public static final int EGL_TRANSPARENT_GREEN_VALUE = 12342;
    public static final int EGL_TRANSPARENT_RED_VALUE = 12343;
    public static final int EGL_TRANSPARENT_RGB = 12370;
    public static final int EGL_TRANSPARENT_TYPE = 12340;
    public static final int EGL_TRUE = 1;
    public static final int EGL_VENDOR = 12371;
    public static final int EGL_VERSION = 12372;
    public static final int EGL_VERTICAL_RESOLUTION = 12433;
    public static final int EGL_VG_ALPHA_FORMAT = 12424;
    public static final int EGL_VG_ALPHA_FORMAT_NONPRE = 12427;
    public static final int EGL_VG_ALPHA_FORMAT_PRE = 12428;
    public static final int EGL_VG_ALPHA_FORMAT_PRE_BIT = 64;
    public static final int EGL_VG_COLORSPACE = 12423;
    public static final int EGL_VG_COLORSPACE_LINEAR = 12426;
    public static final int EGL_VG_COLORSPACE_LINEAR_BIT = 32;
    public static final int EGL_VG_COLORSPACE_sRGB = 12425;
    public static final int EGL_WIDTH = 12375;
    public static final int EGL_WINDOW_BIT = 4;

    static 
    {
        _nativeClassInit();
    }
}

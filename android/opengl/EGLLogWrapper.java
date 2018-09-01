// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.opengl;

import java.io.IOException;
import java.io.Writer;
import javax.microedition.khronos.egl.*;

// Referenced classes of package android.opengl:
//            GLException

class EGLLogWrapper
    implements EGL11
{

    public EGLLogWrapper(EGL egl, int i, Writer writer)
    {
        boolean flag = true;
        super();
        mEgl10 = (EGL10)egl;
        mLog = writer;
        boolean flag1;
        if((i & 4) != 0)
            flag1 = true;
        else
            flag1 = false;
        mLogArgumentNames = flag1;
        if((i & 1) != 0)
            flag1 = flag;
        else
            flag1 = false;
        mCheckError = flag1;
    }

    private void arg(String s, int i)
    {
        arg(s, Integer.toString(i));
    }

    private void arg(String s, Object obj)
    {
        arg(s, toString(obj));
    }

    private void arg(String s, String s1)
    {
        int i = mArgCount;
        mArgCount = i + 1;
        if(i > 0)
            log(", ");
        if(mLogArgumentNames)
            log((new StringBuilder()).append(s).append("=").toString());
        log(s1);
    }

    private void arg(String s, EGLContext eglcontext)
    {
        if(eglcontext == EGL10.EGL_NO_CONTEXT)
            arg(s, "EGL10.EGL_NO_CONTEXT");
        else
            arg(s, toString(eglcontext));
    }

    private void arg(String s, EGLDisplay egldisplay)
    {
        if(egldisplay == EGL10.EGL_DEFAULT_DISPLAY)
            arg(s, "EGL10.EGL_DEFAULT_DISPLAY");
        else
        if(egldisplay == EGL_NO_DISPLAY)
            arg(s, "EGL10.EGL_NO_DISPLAY");
        else
            arg(s, toString(egldisplay));
    }

    private void arg(String s, EGLSurface eglsurface)
    {
        if(eglsurface == EGL10.EGL_NO_SURFACE)
            arg(s, "EGL10.EGL_NO_SURFACE");
        else
            arg(s, toString(eglsurface));
    }

    private void arg(String s, int ai[])
    {
        if(ai == null)
            arg(s, "null");
        else
            arg(s, toString(ai.length, ai, 0));
    }

    private void arg(String s, Object aobj[])
    {
        if(aobj == null)
            arg(s, "null");
        else
            arg(s, toString(aobj.length, aobj, 0));
    }

    private void begin(String s)
    {
        log((new StringBuilder()).append(s).append('(').toString());
        mArgCount = 0;
    }

    private void checkError()
    {
        int i = mEgl10.eglGetError();
        if(i != 12288)
        {
            String s = (new StringBuilder()).append("eglError: ").append(getErrorString(i)).toString();
            logLine(s);
            if(mCheckError)
                throw new GLException(i, s);
        }
    }

    private void end()
    {
        log(");\n");
        flush();
    }

    private void flush()
    {
        mLog.flush();
_L1:
        return;
        IOException ioexception;
        ioexception;
        mLog = null;
          goto _L1
    }

    public static String getErrorString(int i)
    {
        switch(i)
        {
        default:
            return getHex(i);

        case 12288: 
            return "EGL_SUCCESS";

        case 12289: 
            return "EGL_NOT_INITIALIZED";

        case 12290: 
            return "EGL_BAD_ACCESS";

        case 12291: 
            return "EGL_BAD_ALLOC";

        case 12292: 
            return "EGL_BAD_ATTRIBUTE";

        case 12293: 
            return "EGL_BAD_CONFIG";

        case 12294: 
            return "EGL_BAD_CONTEXT";

        case 12295: 
            return "EGL_BAD_CURRENT_SURFACE";

        case 12296: 
            return "EGL_BAD_DISPLAY";

        case 12297: 
            return "EGL_BAD_MATCH";

        case 12298: 
            return "EGL_BAD_NATIVE_PIXMAP";

        case 12299: 
            return "EGL_BAD_NATIVE_WINDOW";

        case 12300: 
            return "EGL_BAD_PARAMETER";

        case 12301: 
            return "EGL_BAD_SURFACE";

        case 12302: 
            return "EGL_CONTEXT_LOST";
        }
    }

    private static String getHex(int i)
    {
        return (new StringBuilder()).append("0x").append(Integer.toHexString(i)).toString();
    }

    private void log(String s)
    {
        mLog.write(s);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void logLine(String s)
    {
        log((new StringBuilder()).append(s).append('\n').toString());
    }

    private void returns(int i)
    {
        returns(Integer.toString(i));
    }

    private void returns(Object obj)
    {
        returns(toString(obj));
    }

    private void returns(String s)
    {
        log((new StringBuilder()).append(" returns ").append(s).append(";\n").toString());
        flush();
    }

    private void returns(boolean flag)
    {
        returns(Boolean.toString(flag));
    }

    private String toString(int i, int ai[], int j)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("{\n");
        int k = ai.length;
        int l = 0;
        while(l < i) 
        {
            int i1 = j + l;
            stringbuilder.append(" [").append(i1).append("] = ");
            if(i1 < 0 || i1 >= k)
                stringbuilder.append("out of bounds");
            else
                stringbuilder.append(ai[i1]);
            stringbuilder.append('\n');
            l++;
        }
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    private String toString(int i, Object aobj[], int j)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("{\n");
        int k = aobj.length;
        int l = 0;
        while(l < i) 
        {
            int i1 = j + l;
            stringbuilder.append(" [").append(i1).append("] = ");
            if(i1 < 0 || i1 >= k)
                stringbuilder.append("out of bounds");
            else
                stringbuilder.append(aobj[i1]);
            stringbuilder.append('\n');
            l++;
        }
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    private String toString(Object obj)
    {
        if(obj == null)
            return "null";
        else
            return obj.toString();
    }

    public boolean eglChooseConfig(EGLDisplay egldisplay, int ai[], EGLConfig aeglconfig[], int i, int ai1[])
    {
        begin("eglChooseConfig");
        arg("display", egldisplay);
        arg("attrib_list", ai);
        arg("config_size", i);
        end();
        boolean flag = mEgl10.eglChooseConfig(egldisplay, ai, aeglconfig, i, ai1);
        arg("configs", aeglconfig);
        arg("num_config", ai1);
        returns(flag);
        checkError();
        return flag;
    }

    public boolean eglCopyBuffers(EGLDisplay egldisplay, EGLSurface eglsurface, Object obj)
    {
        begin("eglCopyBuffers");
        arg("display", egldisplay);
        arg("surface", eglsurface);
        arg("native_pixmap", obj);
        end();
        boolean flag = mEgl10.eglCopyBuffers(egldisplay, eglsurface, obj);
        returns(flag);
        checkError();
        return flag;
    }

    public EGLContext eglCreateContext(EGLDisplay egldisplay, EGLConfig eglconfig, EGLContext eglcontext, int ai[])
    {
        begin("eglCreateContext");
        arg("display", egldisplay);
        arg("config", eglconfig);
        arg("share_context", eglcontext);
        arg("attrib_list", ai);
        end();
        egldisplay = mEgl10.eglCreateContext(egldisplay, eglconfig, eglcontext, ai);
        returns(egldisplay);
        checkError();
        return egldisplay;
    }

    public EGLSurface eglCreatePbufferSurface(EGLDisplay egldisplay, EGLConfig eglconfig, int ai[])
    {
        begin("eglCreatePbufferSurface");
        arg("display", egldisplay);
        arg("config", eglconfig);
        arg("attrib_list", ai);
        end();
        egldisplay = mEgl10.eglCreatePbufferSurface(egldisplay, eglconfig, ai);
        returns(egldisplay);
        checkError();
        return egldisplay;
    }

    public EGLSurface eglCreatePixmapSurface(EGLDisplay egldisplay, EGLConfig eglconfig, Object obj, int ai[])
    {
        begin("eglCreatePixmapSurface");
        arg("display", egldisplay);
        arg("config", eglconfig);
        arg("native_pixmap", obj);
        arg("attrib_list", ai);
        end();
        egldisplay = mEgl10.eglCreatePixmapSurface(egldisplay, eglconfig, obj, ai);
        returns(egldisplay);
        checkError();
        return egldisplay;
    }

    public EGLSurface eglCreateWindowSurface(EGLDisplay egldisplay, EGLConfig eglconfig, Object obj, int ai[])
    {
        begin("eglCreateWindowSurface");
        arg("display", egldisplay);
        arg("config", eglconfig);
        arg("native_window", obj);
        arg("attrib_list", ai);
        end();
        egldisplay = mEgl10.eglCreateWindowSurface(egldisplay, eglconfig, obj, ai);
        returns(egldisplay);
        checkError();
        return egldisplay;
    }

    public boolean eglDestroyContext(EGLDisplay egldisplay, EGLContext eglcontext)
    {
        begin("eglDestroyContext");
        arg("display", egldisplay);
        arg("context", eglcontext);
        end();
        boolean flag = mEgl10.eglDestroyContext(egldisplay, eglcontext);
        returns(flag);
        checkError();
        return flag;
    }

    public boolean eglDestroySurface(EGLDisplay egldisplay, EGLSurface eglsurface)
    {
        begin("eglDestroySurface");
        arg("display", egldisplay);
        arg("surface", eglsurface);
        end();
        boolean flag = mEgl10.eglDestroySurface(egldisplay, eglsurface);
        returns(flag);
        checkError();
        return flag;
    }

    public boolean eglGetConfigAttrib(EGLDisplay egldisplay, EGLConfig eglconfig, int i, int ai[])
    {
        begin("eglGetConfigAttrib");
        arg("display", egldisplay);
        arg("config", eglconfig);
        arg("attribute", i);
        end();
        boolean flag = mEgl10.eglGetConfigAttrib(egldisplay, eglconfig, i, ai);
        arg("value", ai);
        returns(flag);
        checkError();
        return false;
    }

    public boolean eglGetConfigs(EGLDisplay egldisplay, EGLConfig aeglconfig[], int i, int ai[])
    {
        begin("eglGetConfigs");
        arg("display", egldisplay);
        arg("config_size", i);
        end();
        boolean flag = mEgl10.eglGetConfigs(egldisplay, aeglconfig, i, ai);
        arg("configs", aeglconfig);
        arg("num_config", ai);
        returns(flag);
        checkError();
        return flag;
    }

    public EGLContext eglGetCurrentContext()
    {
        begin("eglGetCurrentContext");
        end();
        EGLContext eglcontext = mEgl10.eglGetCurrentContext();
        returns(eglcontext);
        checkError();
        return eglcontext;
    }

    public EGLDisplay eglGetCurrentDisplay()
    {
        begin("eglGetCurrentDisplay");
        end();
        EGLDisplay egldisplay = mEgl10.eglGetCurrentDisplay();
        returns(egldisplay);
        checkError();
        return egldisplay;
    }

    public EGLSurface eglGetCurrentSurface(int i)
    {
        begin("eglGetCurrentSurface");
        arg("readdraw", i);
        end();
        EGLSurface eglsurface = mEgl10.eglGetCurrentSurface(i);
        returns(eglsurface);
        checkError();
        return eglsurface;
    }

    public EGLDisplay eglGetDisplay(Object obj)
    {
        begin("eglGetDisplay");
        arg("native_display", obj);
        end();
        obj = mEgl10.eglGetDisplay(obj);
        returns(obj);
        checkError();
        return ((EGLDisplay) (obj));
    }

    public int eglGetError()
    {
        begin("eglGetError");
        end();
        int i = mEgl10.eglGetError();
        returns(getErrorString(i));
        return i;
    }

    public boolean eglInitialize(EGLDisplay egldisplay, int ai[])
    {
        begin("eglInitialize");
        arg("display", egldisplay);
        end();
        boolean flag = mEgl10.eglInitialize(egldisplay, ai);
        returns(flag);
        arg("major_minor", ai);
        checkError();
        return flag;
    }

    public boolean eglMakeCurrent(EGLDisplay egldisplay, EGLSurface eglsurface, EGLSurface eglsurface1, EGLContext eglcontext)
    {
        begin("eglMakeCurrent");
        arg("display", egldisplay);
        arg("draw", eglsurface);
        arg("read", eglsurface1);
        arg("context", eglcontext);
        end();
        boolean flag = mEgl10.eglMakeCurrent(egldisplay, eglsurface, eglsurface1, eglcontext);
        returns(flag);
        checkError();
        return flag;
    }

    public boolean eglQueryContext(EGLDisplay egldisplay, EGLContext eglcontext, int i, int ai[])
    {
        begin("eglQueryContext");
        arg("display", egldisplay);
        arg("context", eglcontext);
        arg("attribute", i);
        end();
        boolean flag = mEgl10.eglQueryContext(egldisplay, eglcontext, i, ai);
        returns(ai[0]);
        returns(flag);
        checkError();
        return flag;
    }

    public String eglQueryString(EGLDisplay egldisplay, int i)
    {
        begin("eglQueryString");
        arg("display", egldisplay);
        arg("name", i);
        end();
        egldisplay = mEgl10.eglQueryString(egldisplay, i);
        returns(egldisplay);
        checkError();
        return egldisplay;
    }

    public boolean eglQuerySurface(EGLDisplay egldisplay, EGLSurface eglsurface, int i, int ai[])
    {
        begin("eglQuerySurface");
        arg("display", egldisplay);
        arg("surface", eglsurface);
        arg("attribute", i);
        end();
        boolean flag = mEgl10.eglQuerySurface(egldisplay, eglsurface, i, ai);
        returns(ai[0]);
        returns(flag);
        checkError();
        return flag;
    }

    public boolean eglReleaseThread()
    {
        begin("eglReleaseThread");
        end();
        boolean flag = mEgl10.eglReleaseThread();
        returns(flag);
        checkError();
        return flag;
    }

    public boolean eglSwapBuffers(EGLDisplay egldisplay, EGLSurface eglsurface)
    {
        begin("eglSwapBuffers");
        arg("display", egldisplay);
        arg("surface", eglsurface);
        end();
        boolean flag = mEgl10.eglSwapBuffers(egldisplay, eglsurface);
        returns(flag);
        checkError();
        return flag;
    }

    public boolean eglTerminate(EGLDisplay egldisplay)
    {
        begin("eglTerminate");
        arg("display", egldisplay);
        end();
        boolean flag = mEgl10.eglTerminate(egldisplay);
        returns(flag);
        checkError();
        return flag;
    }

    public boolean eglWaitGL()
    {
        begin("eglWaitGL");
        end();
        boolean flag = mEgl10.eglWaitGL();
        returns(flag);
        checkError();
        return flag;
    }

    public boolean eglWaitNative(int i, Object obj)
    {
        begin("eglWaitNative");
        arg("engine", i);
        arg("bindTarget", obj);
        end();
        boolean flag = mEgl10.eglWaitNative(i, obj);
        returns(flag);
        checkError();
        return flag;
    }

    private int mArgCount;
    boolean mCheckError;
    private EGL10 mEgl10;
    Writer mLog;
    boolean mLogArgumentNames;
}

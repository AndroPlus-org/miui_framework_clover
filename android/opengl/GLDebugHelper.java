// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.opengl;

import java.io.Writer;
import javax.microedition.khronos.egl.EGL;
import javax.microedition.khronos.opengles.GL;

// Referenced classes of package android.opengl:
//            EGLLogWrapper, GLErrorWrapper, GLLogWrapper

public class GLDebugHelper
{

    public GLDebugHelper()
    {
    }

    public static EGL wrap(EGL egl, int i, Writer writer)
    {
        Object obj = egl;
        if(writer != null)
            obj = new EGLLogWrapper(egl, i, writer);
        return ((EGL) (obj));
    }

    public static GL wrap(GL gl, int i, Writer writer)
    {
        if(i != 0)
            gl = new GLErrorWrapper(gl, i);
        if(writer != null)
        {
            boolean flag;
            if((i & 4) != 0)
                flag = true;
            else
                flag = false;
            gl = new GLLogWrapper(gl, writer, flag);
        }
        return gl;
    }

    public static final int CONFIG_CHECK_GL_ERROR = 1;
    public static final int CONFIG_CHECK_THREAD = 2;
    public static final int CONFIG_LOG_ARGUMENT_NAMES = 4;
    public static final int ERROR_WRONG_THREAD = 28672;
}

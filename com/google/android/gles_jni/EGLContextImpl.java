// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gles_jni;

import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.opengles.GL;

// Referenced classes of package com.google.android.gles_jni:
//            GLImpl

public class EGLContextImpl extends EGLContext
{

    public EGLContextImpl(long l)
    {
        mEGLContext = l;
        mGLContext = new GLImpl();
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (EGLContextImpl)obj;
        if(mEGLContext != ((EGLContextImpl) (obj)).mEGLContext)
            flag = false;
        return flag;
    }

    public GL getGL()
    {
        return mGLContext;
    }

    public int hashCode()
    {
        return (int)(mEGLContext ^ mEGLContext >>> 32) + 527;
    }

    long mEGLContext;
    private GLImpl mGLContext;
}

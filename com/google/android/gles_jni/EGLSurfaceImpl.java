// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gles_jni;

import javax.microedition.khronos.egl.EGLSurface;

public class EGLSurfaceImpl extends EGLSurface
{

    public EGLSurfaceImpl()
    {
        mEGLSurface = 0L;
    }

    public EGLSurfaceImpl(long l)
    {
        mEGLSurface = l;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (EGLSurfaceImpl)obj;
        if(mEGLSurface != ((EGLSurfaceImpl) (obj)).mEGLSurface)
            flag = false;
        return flag;
    }

    public int hashCode()
    {
        return (int)(mEGLSurface ^ mEGLSurface >>> 32) + 527;
    }

    long mEGLSurface;
}

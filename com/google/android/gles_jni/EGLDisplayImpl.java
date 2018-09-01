// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gles_jni;

import javax.microedition.khronos.egl.EGLDisplay;

public class EGLDisplayImpl extends EGLDisplay
{

    public EGLDisplayImpl(long l)
    {
        mEGLDisplay = l;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (EGLDisplayImpl)obj;
        if(mEGLDisplay != ((EGLDisplayImpl) (obj)).mEGLDisplay)
            flag = false;
        return flag;
    }

    public int hashCode()
    {
        return (int)(mEGLDisplay ^ mEGLDisplay >>> 32) + 527;
    }

    long mEGLDisplay;
}

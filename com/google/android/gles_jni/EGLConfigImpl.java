// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gles_jni;

import javax.microedition.khronos.egl.EGLConfig;

public class EGLConfigImpl extends EGLConfig
{

    EGLConfigImpl(long l)
    {
        mEGLConfig = l;
    }

    long get()
    {
        return mEGLConfig;
    }

    private long mEGLConfig;
}

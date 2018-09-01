// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;


public final class VirtualRefBasePtr
{

    public VirtualRefBasePtr(long l)
    {
        mNativePtr = l;
        nIncStrong(mNativePtr);
    }

    private static native void nDecStrong(long l);

    private static native void nIncStrong(long l);

    protected void finalize()
        throws Throwable
    {
        release();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public long get()
    {
        return mNativePtr;
    }

    public void release()
    {
        if(mNativePtr != 0L)
        {
            nDecStrong(mNativePtr);
            mNativePtr = 0L;
        }
    }

    private long mNativePtr;
}

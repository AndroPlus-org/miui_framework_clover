// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


public class DrawFilter
{

    public DrawFilter()
    {
    }

    private static native void nativeDestructor(long l);

    protected void finalize()
        throws Throwable
    {
        nativeDestructor(mNativeInt);
        mNativeInt = 0L;
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public long mNativeInt;
}

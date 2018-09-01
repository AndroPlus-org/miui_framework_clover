// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


public class PathEffect
{

    public PathEffect()
    {
    }

    private static native void nativeDestructor(long l);

    protected void finalize()
        throws Throwable
    {
        nativeDestructor(native_instance);
        native_instance = 0L;
    }

    long native_instance;
}

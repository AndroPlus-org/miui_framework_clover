// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.utils;


public class UncheckedThrow
{

    public UncheckedThrow()
    {
    }

    public static void throwAnyException(Exception exception)
    {
        throwAnyImpl(exception);
    }

    public static void throwAnyException(Throwable throwable)
    {
        throwAnyImpl(throwable);
    }

    private static void throwAnyImpl(Throwable throwable)
        throws Throwable
    {
        throw throwable;
    }
}

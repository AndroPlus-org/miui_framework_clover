// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;


public class AndroidRuntimeException extends RuntimeException
{

    public AndroidRuntimeException()
    {
    }

    public AndroidRuntimeException(Exception exception)
    {
        super(exception);
    }

    public AndroidRuntimeException(String s)
    {
        super(s);
    }

    public AndroidRuntimeException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
}

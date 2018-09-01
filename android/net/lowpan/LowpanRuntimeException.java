// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;

import android.util.AndroidRuntimeException;

public class LowpanRuntimeException extends AndroidRuntimeException
{

    public LowpanRuntimeException()
    {
    }

    public LowpanRuntimeException(Exception exception)
    {
        super(exception);
    }

    public LowpanRuntimeException(String s)
    {
        super(s);
    }

    public LowpanRuntimeException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
}

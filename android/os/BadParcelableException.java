// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.AndroidRuntimeException;

public class BadParcelableException extends AndroidRuntimeException
{

    public BadParcelableException(Exception exception)
    {
        super(exception);
    }

    public BadParcelableException(String s)
    {
        super(s);
    }
}

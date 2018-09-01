// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import java.security.InvalidKeyException;

public class KeyExpiredException extends InvalidKeyException
{

    public KeyExpiredException()
    {
        super("Key expired");
    }

    public KeyExpiredException(String s)
    {
        super(s);
    }

    public KeyExpiredException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
}

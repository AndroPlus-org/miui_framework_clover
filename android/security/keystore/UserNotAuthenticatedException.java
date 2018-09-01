// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import java.security.InvalidKeyException;

public class UserNotAuthenticatedException extends InvalidKeyException
{

    public UserNotAuthenticatedException()
    {
        super("User not authenticated");
    }

    public UserNotAuthenticatedException(String s)
    {
        super(s);
    }

    public UserNotAuthenticatedException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
}

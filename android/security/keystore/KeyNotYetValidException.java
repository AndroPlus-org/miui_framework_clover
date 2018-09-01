// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import java.security.InvalidKeyException;

public class KeyNotYetValidException extends InvalidKeyException
{

    public KeyNotYetValidException()
    {
        super("Key not yet valid");
    }

    public KeyNotYetValidException(String s)
    {
        super(s);
    }

    public KeyNotYetValidException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
}

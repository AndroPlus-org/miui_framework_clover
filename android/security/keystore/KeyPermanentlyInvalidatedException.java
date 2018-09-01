// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import java.security.InvalidKeyException;

public class KeyPermanentlyInvalidatedException extends InvalidKeyException
{

    public KeyPermanentlyInvalidatedException()
    {
        super("Key permanently invalidated");
    }

    public KeyPermanentlyInvalidatedException(String s)
    {
        super(s);
    }

    public KeyPermanentlyInvalidatedException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security;


public class KeyChainException extends Exception
{

    public KeyChainException()
    {
    }

    public KeyChainException(String s)
    {
        super(s);
    }

    public KeyChainException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public KeyChainException(Throwable throwable)
    {
        String s = null;
        if(throwable != null)
            s = throwable.toString();
        super(s, throwable);
    }
}

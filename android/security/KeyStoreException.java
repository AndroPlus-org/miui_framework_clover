// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security;


public class KeyStoreException extends Exception
{

    public KeyStoreException(int i, String s)
    {
        super(s);
        mErrorCode = i;
    }

    public int getErrorCode()
    {
        return mErrorCode;
    }

    private final int mErrorCode;
}

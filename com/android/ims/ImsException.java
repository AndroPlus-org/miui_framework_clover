// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims;


public class ImsException extends Exception
{

    public ImsException()
    {
    }

    public ImsException(String s, int i)
    {
        super((new StringBuilder()).append(s).append("(").append(i).append(")").toString());
        mCode = i;
    }

    public ImsException(String s, Throwable throwable, int i)
    {
        super(s, throwable);
        mCode = i;
    }

    public int getCode()
    {
        return mCode;
    }

    private int mCode;
}

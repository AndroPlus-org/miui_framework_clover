// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;


public class EncodeException extends Exception
{

    public EncodeException()
    {
    }

    public EncodeException(char c)
    {
        super((new StringBuilder()).append("Unencodable char: '").append(c).append("'").toString());
    }

    public EncodeException(String s)
    {
        super(s);
    }
}

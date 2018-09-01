// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.exception;


public class CloneFailedException extends RuntimeException
{

    public CloneFailedException(String s)
    {
        super(s);
    }

    public CloneFailedException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public CloneFailedException(Throwable throwable)
    {
        super(throwable);
    }

    private static final long serialVersionUID = 0x1329157L;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.http.conn;

import java.io.InterruptedIOException;

public class ConnectTimeoutException extends InterruptedIOException
{

    public ConnectTimeoutException()
    {
    }

    public ConnectTimeoutException(String s)
    {
        super(s);
    }

    private static final long serialVersionUID = 0xbd27b46b62131d0bL;
}

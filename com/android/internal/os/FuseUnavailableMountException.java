// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;


public class FuseUnavailableMountException extends Exception
{

    public FuseUnavailableMountException(int i)
    {
        super((new StringBuilder()).append("AppFuse mount point ").append(i).append(" is unavailable").toString());
    }
}

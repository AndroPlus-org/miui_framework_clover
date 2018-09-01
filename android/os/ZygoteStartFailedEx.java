// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


class ZygoteStartFailedEx extends Exception
{

    ZygoteStartFailedEx(String s)
    {
        super(s);
    }

    ZygoteStartFailedEx(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    ZygoteStartFailedEx(Throwable throwable)
    {
        super(throwable);
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.util.AndroidRuntimeException;

final class ServiceConnectionLeaked extends AndroidRuntimeException
{

    public ServiceConnectionLeaked(String s)
    {
        super(s);
    }
}

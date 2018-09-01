// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.util.AndroidRuntimeException;

final class WindowLeaked extends AndroidRuntimeException
{

    public WindowLeaked(String s)
    {
        super(s);
    }
}

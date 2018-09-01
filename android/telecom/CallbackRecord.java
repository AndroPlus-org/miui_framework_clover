// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.os.Handler;

class CallbackRecord
{

    public CallbackRecord(Object obj, Handler handler)
    {
        mCallback = obj;
        mHandler = handler;
    }

    public Object getCallback()
    {
        return mCallback;
    }

    public Handler getHandler()
    {
        return mHandler;
    }

    private final Object mCallback;
    private final Handler mHandler;
}

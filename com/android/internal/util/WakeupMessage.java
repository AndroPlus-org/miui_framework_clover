// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.app.AlarmManager;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class WakeupMessage
    implements android.app.AlarmManager.OnAlarmListener
{

    public WakeupMessage(Context context, Handler handler, String s, int i)
    {
        this(context, handler, s, i, 0, 0, null);
    }

    public WakeupMessage(Context context, Handler handler, String s, int i, int j)
    {
        this(context, handler, s, i, j, 0, null);
    }

    public WakeupMessage(Context context, Handler handler, String s, int i, int j, int k)
    {
        this(context, handler, s, i, j, k, null);
    }

    public WakeupMessage(Context context, Handler handler, String s, int i, int j, int k, Object obj)
    {
        mAlarmManager = (AlarmManager)context.getSystemService("alarm");
        mHandler = handler;
        mCmdName = s;
        mCmd = i;
        mArg1 = j;
        mArg2 = k;
        mObj = obj;
    }

    public void cancel()
    {
        this;
        JVM INSTR monitorenter ;
        if(mScheduled)
        {
            mAlarmManager.cancel(this);
            mScheduled = false;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void onAlarm()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        flag = mScheduled;
        mScheduled = false;
        this;
        JVM INSTR monitorexit ;
        if(flag)
        {
            Message message = mHandler.obtainMessage(mCmd, mArg1, mArg2, mObj);
            mHandler.dispatchMessage(message);
            message.recycle();
        }
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void schedule(long l)
    {
        this;
        JVM INSTR monitorenter ;
        mAlarmManager.setExact(2, l, mCmdName, this, mHandler);
        mScheduled = true;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private final AlarmManager mAlarmManager;
    protected final int mArg1;
    protected final int mArg2;
    protected final int mCmd;
    protected final String mCmdName;
    protected final Handler mHandler;
    protected final Object mObj;
    private boolean mScheduled;
}

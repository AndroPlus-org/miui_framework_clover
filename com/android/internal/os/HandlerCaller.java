// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.content.Context;
import android.os.*;

// Referenced classes of package com.android.internal.os:
//            SomeArgs

public class HandlerCaller
{
    public static interface Callback
    {

        public abstract void executeMessage(Message message);
    }

    class MyHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            mCallback.executeMessage(message);
        }

        final HandlerCaller this$0;

        MyHandler(Looper looper, boolean flag)
        {
            this$0 = HandlerCaller.this;
            super(looper, null, flag);
        }
    }


    public HandlerCaller(Context context, Looper looper, Callback callback, boolean flag)
    {
        if(looper == null)
            looper = context.getMainLooper();
        mMainLooper = looper;
        mH = new MyHandler(mMainLooper, flag);
        mCallback = callback;
    }

    public void executeOrSendMessage(Message message)
    {
        if(Looper.myLooper() == mMainLooper)
        {
            mCallback.executeMessage(message);
            message.recycle();
            return;
        } else
        {
            mH.sendMessage(message);
            return;
        }
    }

    public Handler getHandler()
    {
        return mH;
    }

    public boolean hasMessages(int i)
    {
        return mH.hasMessages(i);
    }

    public Message obtainMessage(int i)
    {
        return mH.obtainMessage(i);
    }

    public Message obtainMessageBO(int i, boolean flag, Object obj)
    {
        Handler handler = mH;
        int j;
        if(flag)
            j = 1;
        else
            j = 0;
        return handler.obtainMessage(i, j, 0, obj);
    }

    public Message obtainMessageBOO(int i, boolean flag, Object obj, Object obj1)
    {
        SomeArgs someargs = SomeArgs.obtain();
        someargs.arg1 = obj;
        someargs.arg2 = obj1;
        obj = mH;
        int j;
        if(flag)
            j = 1;
        else
            j = 0;
        return ((Handler) (obj)).obtainMessage(i, j, 0, someargs);
    }

    public Message obtainMessageI(int i, int j)
    {
        return mH.obtainMessage(i, j, 0);
    }

    public Message obtainMessageII(int i, int j, int k)
    {
        return mH.obtainMessage(i, j, k);
    }

    public Message obtainMessageIIII(int i, int j, int k, int l, int i1)
    {
        SomeArgs someargs = SomeArgs.obtain();
        someargs.argi1 = j;
        someargs.argi2 = k;
        someargs.argi3 = l;
        someargs.argi4 = i1;
        return mH.obtainMessage(i, 0, 0, someargs);
    }

    public Message obtainMessageIIIIII(int i, int j, int k, int l, int i1, int j1, int k1)
    {
        SomeArgs someargs = SomeArgs.obtain();
        someargs.argi1 = j;
        someargs.argi2 = k;
        someargs.argi3 = l;
        someargs.argi4 = i1;
        someargs.argi5 = j1;
        someargs.argi6 = k1;
        return mH.obtainMessage(i, 0, 0, someargs);
    }

    public Message obtainMessageIIIIO(int i, int j, int k, int l, int i1, Object obj)
    {
        SomeArgs someargs = SomeArgs.obtain();
        someargs.arg1 = obj;
        someargs.argi1 = j;
        someargs.argi2 = k;
        someargs.argi3 = l;
        someargs.argi4 = i1;
        return mH.obtainMessage(i, 0, 0, someargs);
    }

    public Message obtainMessageIIO(int i, int j, int k, Object obj)
    {
        return mH.obtainMessage(i, j, k, obj);
    }

    public Message obtainMessageIIOO(int i, int j, int k, Object obj, Object obj1)
    {
        SomeArgs someargs = SomeArgs.obtain();
        someargs.arg1 = obj;
        someargs.arg2 = obj1;
        return mH.obtainMessage(i, j, k, someargs);
    }

    public Message obtainMessageIIOOO(int i, int j, int k, Object obj, Object obj1, Object obj2)
    {
        SomeArgs someargs = SomeArgs.obtain();
        someargs.arg1 = obj;
        someargs.arg2 = obj1;
        someargs.arg3 = obj2;
        return mH.obtainMessage(i, j, k, someargs);
    }

    public Message obtainMessageIIOOOO(int i, int j, int k, Object obj, Object obj1, Object obj2, Object obj3)
    {
        SomeArgs someargs = SomeArgs.obtain();
        someargs.arg1 = obj;
        someargs.arg2 = obj1;
        someargs.arg3 = obj2;
        someargs.arg4 = obj3;
        return mH.obtainMessage(i, j, k, someargs);
    }

    public Message obtainMessageIO(int i, int j, Object obj)
    {
        return mH.obtainMessage(i, j, 0, obj);
    }

    public Message obtainMessageIOO(int i, int j, Object obj, Object obj1)
    {
        SomeArgs someargs = SomeArgs.obtain();
        someargs.arg1 = obj;
        someargs.arg2 = obj1;
        return mH.obtainMessage(i, j, 0, someargs);
    }

    public Message obtainMessageIOOO(int i, int j, Object obj, Object obj1, Object obj2)
    {
        SomeArgs someargs = SomeArgs.obtain();
        someargs.arg1 = obj;
        someargs.arg2 = obj1;
        someargs.arg3 = obj2;
        return mH.obtainMessage(i, j, 0, someargs);
    }

    public Message obtainMessageO(int i, Object obj)
    {
        return mH.obtainMessage(i, 0, 0, obj);
    }

    public Message obtainMessageOO(int i, Object obj, Object obj1)
    {
        SomeArgs someargs = SomeArgs.obtain();
        someargs.arg1 = obj;
        someargs.arg2 = obj1;
        return mH.obtainMessage(i, 0, 0, someargs);
    }

    public Message obtainMessageOOO(int i, Object obj, Object obj1, Object obj2)
    {
        SomeArgs someargs = SomeArgs.obtain();
        someargs.arg1 = obj;
        someargs.arg2 = obj1;
        someargs.arg3 = obj2;
        return mH.obtainMessage(i, 0, 0, someargs);
    }

    public Message obtainMessageOOOO(int i, Object obj, Object obj1, Object obj2, Object obj3)
    {
        SomeArgs someargs = SomeArgs.obtain();
        someargs.arg1 = obj;
        someargs.arg2 = obj1;
        someargs.arg3 = obj2;
        someargs.arg4 = obj3;
        return mH.obtainMessage(i, 0, 0, someargs);
    }

    public Message obtainMessageOOOOII(int i, Object obj, Object obj1, Object obj2, Object obj3, int j, int k)
    {
        SomeArgs someargs = SomeArgs.obtain();
        someargs.arg1 = obj;
        someargs.arg2 = obj1;
        someargs.arg3 = obj2;
        someargs.arg4 = obj3;
        someargs.argi5 = j;
        someargs.argi6 = k;
        return mH.obtainMessage(i, 0, 0, someargs);
    }

    public Message obtainMessageOOOOO(int i, Object obj, Object obj1, Object obj2, Object obj3, Object obj4)
    {
        SomeArgs someargs = SomeArgs.obtain();
        someargs.arg1 = obj;
        someargs.arg2 = obj1;
        someargs.arg3 = obj2;
        someargs.arg4 = obj3;
        someargs.arg5 = obj4;
        return mH.obtainMessage(i, 0, 0, someargs);
    }

    public void removeMessages(int i)
    {
        mH.removeMessages(i);
    }

    public void removeMessages(int i, Object obj)
    {
        mH.removeMessages(i, obj);
    }

    public void sendMessage(Message message)
    {
        mH.sendMessage(message);
    }

    public SomeArgs sendMessageAndWait(Message message)
    {
        SomeArgs someargs;
        if(Looper.myLooper() == mH.getLooper())
            throw new IllegalStateException("Can't wait on same thread as looper");
        someargs = (SomeArgs)message.obj;
        someargs.mWaitState = 1;
        mH.sendMessage(message);
        someargs;
        JVM INSTR monitorenter ;
_L2:
        int i = someargs.mWaitState;
        if(i != 1)
            break; /* Loop/switch isn't completed */
        try
        {
            someargs.wait();
        }
        // Misplaced declaration of an exception variable
        catch(Message message)
        {
            return null;
        }
        if(true) goto _L2; else goto _L1
_L1:
        someargs;
        JVM INSTR monitorexit ;
        someargs.mWaitState = 0;
        return someargs;
        message;
        throw message;
    }

    public void sendMessageDelayed(Message message, long l)
    {
        mH.sendMessageDelayed(message, l);
    }

    final Callback mCallback;
    final Handler mH;
    final Looper mMainLooper;
}

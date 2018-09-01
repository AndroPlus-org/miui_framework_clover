// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.legacy;

import android.os.*;

public class RequestHandlerThread extends HandlerThread
{

    static ConditionVariable _2D_get0(RequestHandlerThread requesthandlerthread)
    {
        return requesthandlerthread.mIdle;
    }

    public RequestHandlerThread(String s, android.os.Handler.Callback callback)
    {
        super(s, 10);
        mCallback = callback;
    }

    public Handler getHandler()
    {
        return mHandler;
    }

    public boolean hasAnyMessages(int ai[])
    {
        MessageQueue messagequeue = mHandler.getLooper().getQueue();
        messagequeue;
        JVM INSTR monitorenter ;
        int i = ai.length;
        int j = 0;
_L2:
        int k;
        if(j >= i)
            break; /* Loop/switch isn't completed */
        k = ai[j];
        boolean flag = mHandler.hasMessages(k);
        if(!flag)
            break MISSING_BLOCK_LABEL_51;
        messagequeue;
        JVM INSTR monitorexit ;
        return true;
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        return false;
        ai;
        throw ai;
    }

    protected void onLooperPrepared()
    {
        mHandler = new Handler(getLooper(), mCallback);
        mStarted.open();
    }

    public void removeMessages(int ai[])
    {
        MessageQueue messagequeue = mHandler.getLooper().getQueue();
        messagequeue;
        JVM INSTR monitorenter ;
        int i = 0;
        int j = ai.length;
_L2:
        int k;
        if(i >= j)
            break; /* Loop/switch isn't completed */
        k = ai[i];
        mHandler.removeMessages(k);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        return;
        ai;
        throw ai;
    }

    public Handler waitAndGetHandler()
    {
        waitUntilStarted();
        return getHandler();
    }

    public void waitUntilIdle()
    {
        Handler handler = waitAndGetHandler();
        MessageQueue messagequeue = handler.getLooper().getQueue();
        if(messagequeue.isIdle())
            return;
        mIdle.close();
        messagequeue.addIdleHandler(mIdleHandler);
        handler.sendEmptyMessage(-1);
        if(messagequeue.isIdle())
        {
            return;
        } else
        {
            mIdle.block();
            return;
        }
    }

    public void waitUntilStarted()
    {
        mStarted.block();
    }

    public static final int MSG_POKE_IDLE_HANDLER = -1;
    private android.os.Handler.Callback mCallback;
    private volatile Handler mHandler;
    private final ConditionVariable mIdle = new ConditionVariable(true);
    private final android.os.MessageQueue.IdleHandler mIdleHandler = new android.os.MessageQueue.IdleHandler() {

        public boolean queueIdle()
        {
            RequestHandlerThread._2D_get0(RequestHandlerThread.this).open();
            return false;
        }

        final RequestHandlerThread this$0;

            
            {
                this$0 = RequestHandlerThread.this;
                super();
            }
    }
;
    private final ConditionVariable mStarted = new ConditionVariable(false);
}

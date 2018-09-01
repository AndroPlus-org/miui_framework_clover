// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            Handler, Looper, Process

public class HandlerThread extends Thread
{

    public HandlerThread(String s)
    {
        super(s);
        mTid = -1;
        mPriority = 0;
    }

    public HandlerThread(String s, int i)
    {
        super(s);
        mTid = -1;
        mPriority = i;
    }

    public Looper getLooper()
    {
        if(!isAlive())
            return null;
        this;
        JVM INSTR monitorenter ;
_L2:
        Looper looper;
        if(!isAlive())
            break; /* Loop/switch isn't completed */
        looper = mLooper;
        if(looper != null)
            break; /* Loop/switch isn't completed */
        try
        {
            wait();
        }
        catch(InterruptedException interruptedexception) { }
        if(true) goto _L2; else goto _L1
_L1:
        return mLooper;
        Exception exception;
        exception;
        throw exception;
    }

    public Handler getThreadHandler()
    {
        if(mHandler == null)
            mHandler = new Handler(getLooper());
        return mHandler;
    }

    public int getThreadId()
    {
        return mTid;
    }

    protected void onLooperPrepared()
    {
    }

    public boolean quit()
    {
        Looper looper = getLooper();
        if(looper != null)
        {
            looper.quit();
            return true;
        } else
        {
            return false;
        }
    }

    public boolean quitSafely()
    {
        Looper looper = getLooper();
        if(looper != null)
        {
            looper.quitSafely();
            return true;
        } else
        {
            return false;
        }
    }

    public void run()
    {
        mTid = Process.myTid();
        Looper.prepare();
        this;
        JVM INSTR monitorenter ;
        mLooper = Looper.myLooper();
        notifyAll();
        this;
        JVM INSTR monitorexit ;
        Process.setThreadPriority(mPriority);
        onLooperPrepared();
        Looper.loop();
        mTid = -1;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private Handler mHandler;
    Looper mLooper;
    int mPriority;
    int mTid;
}

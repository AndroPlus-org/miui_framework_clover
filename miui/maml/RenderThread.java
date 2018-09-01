// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.os.*;
import android.util.Log;
import java.util.ArrayList;

// Referenced classes of package miui.maml:
//            RendererController

public class RenderThread extends Thread
{
    private class CommandThreadHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 0 1: default 28
        //                       0 29
        //                       1 40;
               goto _L1 _L2 _L3
_L1:
            return;
_L2:
            RenderThread._2D_wrap0(RenderThread.this, true);
            continue; /* Loop/switch isn't completed */
_L3:
            RenderThread._2D_wrap0(RenderThread.this, false);
            if(true) goto _L1; else goto _L4
_L4:
        }

        public void setPause(boolean flag)
        {
            Message message = new Message();
            int i;
            if(flag)
                i = 0;
            else
                i = 1;
            message.what = i;
            sendMessage(message);
        }

        private static final int MSG_PAUSE = 0;
        private static final int MSG_RESUME = 1;
        final RenderThread this$0;

        public CommandThreadHandler(Looper looper)
        {
            this$0 = RenderThread.this;
            super(looper);
        }
    }


    static void _2D_wrap0(RenderThread renderthread, boolean flag)
    {
        renderthread.setPausedImpl(flag);
    }

    public RenderThread()
    {
        super("MAML RenderThread");
        mRendererControllerList = new ArrayList();
        mPaused = true;
        mResumeSignal = new Object();
        mSleepSignal = new Object();
        initCmdThread();
    }

    public RenderThread(RendererController renderercontroller)
    {
        super("MAML RenderThread");
        mRendererControllerList = new ArrayList();
        mPaused = true;
        mResumeSignal = new Object();
        mSleepSignal = new Object();
        addRendererController(renderercontroller);
        initCmdThread();
    }

    private void doFinish()
    {
        ArrayList arraylist = mRendererControllerList;
        arraylist;
        JVM INSTR monitorenter ;
        int i = mRendererControllerList.size();
        if(i != 0)
            break MISSING_BLOCK_LABEL_22;
        arraylist;
        JVM INSTR monitorexit ;
        return;
        int j = mRendererControllerList.size();
        i = 0;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        ((RendererController)mRendererControllerList.get(i)).finish();
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void doInit()
    {
        ArrayList arraylist = mRendererControllerList;
        arraylist;
        JVM INSTR monitorenter ;
        int i = mRendererControllerList.size();
        if(i != 0)
            break MISSING_BLOCK_LABEL_22;
        arraylist;
        JVM INSTR monitorexit ;
        return;
        int j = mRendererControllerList.size();
        i = 0;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        RendererController renderercontroller = (RendererController)mRendererControllerList.get(i);
        renderercontroller.init();
        renderercontroller.requestUpdate();
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void doPause()
    {
        ArrayList arraylist = mRendererControllerList;
        arraylist;
        JVM INSTR monitorenter ;
        int i = mRendererControllerList.size();
        if(i != 0)
            break MISSING_BLOCK_LABEL_22;
        arraylist;
        JVM INSTR monitorexit ;
        return;
        int j = mRendererControllerList.size();
        i = 0;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        ((RendererController)mRendererControllerList.get(i)).pause();
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void doResume()
    {
        ArrayList arraylist = mRendererControllerList;
        arraylist;
        JVM INSTR monitorenter ;
        int i = mRendererControllerList.size();
        if(i != 0)
            break MISSING_BLOCK_LABEL_22;
        arraylist;
        JVM INSTR monitorexit ;
        return;
        int j = mRendererControllerList.size();
        i = 0;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        ((RendererController)mRendererControllerList.get(i)).resume();
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static RenderThread globalThread()
    {
        return globalThread(false);
    }

    public static RenderThread globalThread(boolean flag)
    {
        Object obj = sGlobalThreadLock;
        obj;
        JVM INSTR monitorenter ;
        if(sGlobalThread == null)
        {
            RenderThread renderthread = JVM INSTR new #2   <Class RenderThread>;
            renderthread.RenderThread();
            sGlobalThread = renderthread;
        }
        if(!flag)
            break MISSING_BLOCK_LABEL_47;
        flag = sGlobalThread.isStarted();
        if(!(flag ^ true))
            break MISSING_BLOCK_LABEL_47;
        RenderThread renderthread1;
        Exception exception;
        try
        {
            sGlobalThread.start();
        }
        catch(IllegalThreadStateException illegalthreadstateexception) { }
        renderthread1 = sGlobalThread;
        obj;
        JVM INSTR monitorexit ;
        return renderthread1;
        exception;
        throw exception;
    }

    public static void globalThreadStop()
    {
        Object obj = sGlobalThreadLock;
        obj;
        JVM INSTR monitorenter ;
        if(sGlobalThread != null)
        {
            sGlobalThread.setStop();
            sGlobalThread = null;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void initCmdThread()
    {
        mCmdThread = new HandlerThread("cmd");
        mCmdThread.start();
        mCmdHanlder = new CommandThreadHandler(mCmdThread.getLooper());
    }

    private void setPausedImpl(boolean flag)
    {
        if(mPaused == flag)
            return;
        Object obj = mResumeSignal;
        obj;
        JVM INSTR monitorenter ;
        mPaused = flag;
        if(flag)
            break MISSING_BLOCK_LABEL_32;
        mResumeSignal.notify();
        obj;
        JVM INSTR monitorexit ;
        signal();
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private final void waitSleep(long l)
    {
        if(mSignaled || l <= 0L)
            return;
        Object obj = mSleepSignal;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mSignaled;
        if(flag)
            break MISSING_BLOCK_LABEL_40;
        mSleepSignal.wait(l);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        obj1;
        ((InterruptedException) (obj1)).printStackTrace();
          goto _L1
        obj1;
        throw obj1;
    }

    private void waiteForResume()
    {
        mResumeSignal.wait();
_L1:
        return;
        InterruptedException interruptedexception;
        interruptedexception;
        interruptedexception.printStackTrace();
          goto _L1
    }

    public void addRendererController(RendererController renderercontroller)
    {
        ArrayList arraylist = mRendererControllerList;
        arraylist;
        JVM INSTR monitorenter ;
        if(!mRendererControllerList.contains(renderercontroller))
            break MISSING_BLOCK_LABEL_29;
        Log.w("RenderThread", "addRendererController: RendererController already exists");
        arraylist;
        JVM INSTR monitorexit ;
        return;
        renderercontroller.setRenderThread(this);
        mRendererControllerList.add(renderercontroller);
        arraylist;
        JVM INSTR monitorexit ;
        setPaused(false);
        return;
        renderercontroller;
        throw renderercontroller;
    }

    public boolean isStarted()
    {
        return mStarted;
    }

    public void removeRendererController(RendererController renderercontroller)
    {
        ArrayList arraylist = mRendererControllerList;
        arraylist;
        JVM INSTR monitorenter ;
        mRendererControllerList.remove(renderercontroller);
        renderercontroller.setRenderThread(null);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        renderercontroller;
        throw renderercontroller;
    }

    public void run()
    {
        Log.i("RenderThread", "RenderThread started");
        doInit();
        mStarted = true;
_L6:
        if(mStop) goto _L2; else goto _L1
_L1:
        if(!mPaused) goto _L4; else goto _L3
_L3:
        Object obj = mResumeSignal;
        obj;
        JVM INSTR monitorenter ;
        if(mPaused)
        {
            doPause();
            Log.i("RenderThread", "RenderThread paused, waiting for signal");
            waiteForResume();
            Log.i("RenderThread", "RenderThread resumed");
            doResume();
        }
        obj;
        JVM INSTR monitorexit ;
_L4:
        boolean flag = mStop;
        if(!flag) goto _L5; else goto _L2
_L2:
        doFinish();
        mCmdThread.quit();
        Log.i("RenderThread", "RenderThread stopped");
        return;
        Exception exception1;
        exception1;
        obj;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        exception.printStackTrace();
        Log.e("RenderThread", exception.toString());
          goto _L2
_L5:
        long l = SystemClock.elapsedRealtime();
        long l1 = 0x7fffffffffffffffL;
        exception = mRendererControllerList;
        exception;
        JVM INSTR monitorenter ;
        int i = mRendererControllerList.size();
        boolean flag1;
        int j;
        flag1 = true;
        j = 0;
_L9:
        if(j >= i)
            break MISSING_BLOCK_LABEL_174;
        if(!mPaused)
            break MISSING_BLOCK_LABEL_220;
        if(i != 0 && !flag1)
            break MISSING_BLOCK_LABEL_312;
        mPaused = true;
        Log.i("RenderThread", "All controllers paused.");
        exception;
        JVM INSTR monitorexit ;
          goto _L6
        exception;
        exception.printStackTrace();
        Log.e("RenderThread", exception.toString());
          goto _L2
        RendererController renderercontroller = (RendererController)mRendererControllerList.get(j);
        if(!renderercontroller.isSelfPaused() || !(renderercontroller.hasRunnable() ^ true)) goto _L8; else goto _L7
_L7:
        long l2 = l1;
_L10:
        j++;
        l1 = l2;
          goto _L9
_L8:
        boolean flag2 = false;
        long l3;
        if(!renderercontroller.hasInited())
            renderercontroller.init();
        l3 = renderercontroller.updateIfNeeded(l);
        flag1 = flag2;
        l2 = l1;
        if(l3 < l1)
        {
            l2 = l3;
            flag1 = flag2;
        }
          goto _L10
        exception;
        JVM INSTR monitorexit ;
        waitSleep(l1);
        mSignaled = false;
          goto _L6
        renderercontroller;
        exception;
        JVM INSTR monitorexit ;
        throw renderercontroller;
    }

    public void setPaused(boolean flag)
    {
        mCmdHanlder.setPause(flag);
    }

    public void setStop()
    {
        mStop = true;
        setPaused(false);
    }

    public void signal()
    {
        if(mSignaled)
            return;
        Object obj = mSleepSignal;
        obj;
        JVM INSTR monitorenter ;
        mSignaled = true;
        mSleepSignal.notify();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static final String LOG_TAG = "RenderThread";
    private static RenderThread sGlobalThread;
    private static Object sGlobalThreadLock = new Object();
    private CommandThreadHandler mCmdHanlder;
    private HandlerThread mCmdThread;
    private boolean mPaused;
    private ArrayList mRendererControllerList;
    private Object mResumeSignal;
    private boolean mSignaled;
    private Object mSleepSignal;
    private boolean mStarted;
    private boolean mStop;

}

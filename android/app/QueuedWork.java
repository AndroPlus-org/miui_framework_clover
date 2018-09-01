// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.*;
import com.android.internal.util.ExponentiallyBucketedHistogram;
import java.util.Iterator;
import java.util.LinkedList;

public class QueuedWork
{
    private static class QueuedWorkHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            if(message.what == 1)
                QueuedWork._2D_wrap0();
        }

        static final int MSG_RUN = 1;

        QueuedWorkHandler(Looper looper)
        {
            super(looper);
        }
    }


    static void _2D_wrap0()
    {
        processPendingWork();
    }

    public QueuedWork()
    {
    }

    public static void addFinisher(Runnable runnable)
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        sFinishers.add(runnable);
        obj;
        JVM INSTR monitorexit ;
        return;
        runnable;
        throw runnable;
    }

    private static Handler getHandler()
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        Handler handler;
        if(sHandler == null)
        {
            HandlerThread handlerthread = JVM INSTR new #87  <Class HandlerThread>;
            handlerthread.HandlerThread("queued-work-looper", -2);
            handlerthread.start();
            QueuedWorkHandler queuedworkhandler = JVM INSTR new #6   <Class QueuedWork$QueuedWorkHandler>;
            queuedworkhandler.QueuedWorkHandler(handlerthread.getLooper());
            sHandler = queuedworkhandler;
        }
        handler = sHandler;
        obj;
        JVM INSTR monitorexit ;
        return handler;
        Exception exception;
        exception;
        throw exception;
    }

    public static boolean hasPendingWork()
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = sWork.isEmpty();
        obj;
        JVM INSTR monitorexit ;
        return flag ^ true;
        Exception exception;
        exception;
        throw exception;
    }

    private static void processPendingWork()
    {
        Object obj = sProcessingWork;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = sLock;
        obj1;
        JVM INSTR monitorenter ;
        LinkedList linkedlist;
        linkedlist = (LinkedList)sWork.clone();
        sWork.clear();
        getHandler().removeMessages(1);
        obj1;
        JVM INSTR monitorexit ;
        if(linkedlist.size() > 0)
            for(obj1 = linkedlist.iterator(); ((Iterator) (obj1)).hasNext(); ((Runnable)((Iterator) (obj1)).next()).run());
        break MISSING_BLOCK_LABEL_87;
        obj1;
        throw obj1;
        Exception exception;
        exception;
        obj1;
        JVM INSTR monitorexit ;
        throw exception;
        obj;
        JVM INSTR monitorexit ;
    }

    public static void queue(Runnable runnable, boolean flag)
    {
        Handler handler = getHandler();
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        sWork.add(runnable);
        if(!flag)
            break MISSING_BLOCK_LABEL_40;
        if(!sCanDelay)
            break MISSING_BLOCK_LABEL_40;
        handler.sendEmptyMessageDelayed(1, 100L);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        handler.sendEmptyMessage(1);
          goto _L1
        runnable;
        throw runnable;
    }

    public static void removeFinisher(Runnable runnable)
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        sFinishers.remove(runnable);
        obj;
        JVM INSTR monitorexit ;
        return;
        runnable;
        throw runnable;
    }

    public static void waitToFinish()
    {
        long l;
        Object obj;
        l = System.currentTimeMillis();
        obj = getHandler();
        Object obj1 = sLock;
        obj1;
        JVM INSTR monitorenter ;
        if(((Handler) (obj)).hasMessages(1))
            ((Handler) (obj)).removeMessages(1);
        sCanDelay = false;
        obj1;
        JVM INSTR monitorexit ;
        obj1 = StrictMode.allowThreadDiskWrites();
        processPendingWork();
        StrictMode.setThreadPolicy(((android.os.StrictMode.ThreadPolicy) (obj1)));
_L5:
        obj1 = sLock;
        obj1;
        JVM INSTR monitorenter ;
        obj = (Runnable)sFinishers.poll();
        obj1;
        JVM INSTR monitorexit ;
        if(obj != null) goto _L2; else goto _L1
_L1:
        sCanDelay = true;
        obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        long l1 = System.currentTimeMillis();
        l = l1 - l;
        if(l > 0L) goto _L4; else goto _L3
_L3:
        obj;
        JVM INSTR monitorexit ;
        return;
        obj;
        throw obj;
        obj;
        StrictMode.setThreadPolicy(((android.os.StrictMode.ThreadPolicy) (obj1)));
        throw obj;
        obj;
        obj1;
        JVM INSTR monitorexit ;
        throw obj;
        Exception exception;
        exception;
        sCanDelay = true;
        throw exception;
_L2:
        ((Runnable) (obj)).run();
          goto _L5
_L4:
        mWaitTimes.add(Long.valueOf(l).intValue());
        mNumWaits++;
          goto _L6
_L7:
        mWaitTimes.log(LOG_TAG, "waited: ");
          goto _L3
        exception;
        throw exception;
_L6:
        if(mNumWaits % 1024 != 0 && l <= 512L) goto _L3; else goto _L7
    }

    private static final boolean DEBUG = false;
    private static final long DELAY = 100L;
    private static final String LOG_TAG = android/app/QueuedWork.getSimpleName();
    private static final long MAX_WAIT_TIME_MILLIS = 512L;
    private static int mNumWaits = 0;
    private static final ExponentiallyBucketedHistogram mWaitTimes = new ExponentiallyBucketedHistogram(16);
    private static boolean sCanDelay = true;
    private static final LinkedList sFinishers = new LinkedList();
    private static Handler sHandler = null;
    private static final Object sLock = new Object();
    private static Object sProcessingWork = new Object();
    private static final LinkedList sWork = new LinkedList();

}

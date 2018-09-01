// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.*;
import android.util.proto.ProtoOutputStream;

// Referenced classes of package android.os:
//            MessageQueue, Binder, Message, Trace, 
//            Handler, SystemClock

public final class Looper
{

    private Looper(boolean flag)
    {
        mQueue = new MessageQueue(flag);
    }

    public static Looper getMainLooper()
    {
        android/os/Looper;
        JVM INSTR monitorenter ;
        Looper looper = sMainLooper;
        android/os/Looper;
        JVM INSTR monitorexit ;
        return looper;
        Exception exception;
        exception;
        throw exception;
    }

    public static void loop()
    {
        Looper looper;
        MessageQueue messagequeue;
        long l;
        looper = myLooper();
        if(looper == null)
            throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
        messagequeue = looper.mQueue;
        Binder.clearCallingIdentity();
        l = Binder.clearCallingIdentity();
_L1:
        long l2;
        long l4;
        Message message = messagequeue.next();
        if(message == null)
            return;
        Printer printer = looper.mLogging;
        if(printer != null)
            printer.println((new StringBuilder()).append(">>>>> Dispatching to ").append(message.target).append(" ").append(message.callback).append(": ").append(message.what).toString());
        long l1 = looper.mSlowDispatchThresholdMs;
        l2 = looper.mTraceTag;
        if(l2 != 0L && Trace.isTagEnabled(l2))
            Trace.traceBegin(l2, message.target.getTraceName(message));
        long l3;
        if(l1 == 0L)
            l3 = 0L;
        else
            l3 = SystemClock.uptimeMillis();
        message.target.dispatchMessage(message);
        if(l1 != 0L)
            break MISSING_BLOCK_LABEL_457;
        l4 = 0L;
_L2:
        if(l2 != 0L)
            Trace.traceEnd(l2);
        if(l1 > 0L)
        {
            l3 = l4 - l3;
            if(l3 > l1)
                Slog.w("Looper", (new StringBuilder()).append("Dispatch took ").append(l3).append("ms on ").append(Thread.currentThread().getName()).append(", h=").append(message.target).append(" cb=").append(message.callback).append(" msg=").append(message.what).toString());
        }
        if(printer != null)
            printer.println((new StringBuilder()).append("<<<<< Finished to ").append(message.target).append(" ").append(message.callback).toString());
        l3 = Binder.clearCallingIdentity();
        if(l != l3)
            Log.wtf("Looper", (new StringBuilder()).append("Thread identity changed from 0x").append(Long.toHexString(l)).append(" to 0x").append(Long.toHexString(l3)).append(" while dispatching to ").append(message.target.getClass().getName()).append(" ").append(message.callback).append(" what=").append(message.what).toString());
        message.recycleUnchecked();
          goto _L1
        l4 = SystemClock.uptimeMillis();
          goto _L2
        Exception exception;
        exception;
        if(l2 != 0L)
            Trace.traceEnd(l2);
        throw exception;
    }

    public static Looper myLooper()
    {
        return (Looper)sThreadLocal.get();
    }

    public static MessageQueue myQueue()
    {
        return myLooper().mQueue;
    }

    public static void prepare()
    {
        prepare(true);
    }

    private static void prepare(boolean flag)
    {
        if(sThreadLocal.get() != null)
        {
            throw new RuntimeException("Only one Looper may be created per thread");
        } else
        {
            sThreadLocal.set(new Looper(flag));
            return;
        }
    }

    public static void prepareMainLooper()
    {
        prepare(false);
        android/os/Looper;
        JVM INSTR monitorenter ;
        if(sMainLooper != null)
        {
            IllegalStateException illegalstateexception = JVM INSTR new #222 <Class IllegalStateException>;
            illegalstateexception.IllegalStateException("The main Looper has already been prepared.");
            throw illegalstateexception;
        }
        break MISSING_BLOCK_LABEL_31;
        Exception exception;
        exception;
        android/os/Looper;
        JVM INSTR monitorexit ;
        throw exception;
        sMainLooper = myLooper();
        android/os/Looper;
        JVM INSTR monitorexit ;
    }

    public void dump(Printer printer, String s)
    {
        printer.println((new StringBuilder()).append(s).append(toString()).toString());
        mQueue.dump(printer, (new StringBuilder()).append(s).append("  ").toString(), null);
    }

    public void dump(Printer printer, String s, Handler handler)
    {
        printer.println((new StringBuilder()).append(s).append(toString()).toString());
        mQueue.dump(printer, (new StringBuilder()).append(s).append("  ").toString(), handler);
    }

    public MessageQueue getQueue()
    {
        return mQueue;
    }

    public Thread getThread()
    {
        return mThread;
    }

    public boolean isCurrentThread()
    {
        boolean flag;
        if(Thread.currentThread() == mThread)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void quit()
    {
        mQueue.quit(false);
    }

    public void quitSafely()
    {
        mQueue.quit(true);
    }

    public void setMessageLogging(Printer printer)
    {
        mLogging = printer;
    }

    public void setSlowDispatchThresholdMs(long l)
    {
        mSlowDispatchThresholdMs = l;
    }

    public void setTraceTag(long l)
    {
        mTraceTag = l;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Looper (").append(mThread.getName()).append(", tid ").append(mThread.getId()).append(") {").append(Integer.toHexString(System.identityHashCode(this))).append("}").toString();
    }

    public void writeToProto(ProtoOutputStream protooutputstream, long l)
    {
        l = protooutputstream.start(l);
        protooutputstream.write(0x10e00000001L, mThread.getName());
        protooutputstream.write(0x10400000002L, mThread.getId());
        protooutputstream.write(0x10300000003L, System.identityHashCode(this));
        mQueue.writeToProto(protooutputstream, 0x11100000004L);
        protooutputstream.end(l);
    }

    private static final String TAG = "Looper";
    private static Looper sMainLooper;
    static final ThreadLocal sThreadLocal = new ThreadLocal();
    private Printer mLogging;
    final MessageQueue mQueue;
    private long mSlowDispatchThresholdMs;
    final Thread mThread = Thread.currentThread();
    private long mTraceTag;

}

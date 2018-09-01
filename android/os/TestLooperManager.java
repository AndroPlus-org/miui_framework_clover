// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.ArraySet;
import java.util.concurrent.LinkedBlockingQueue;

// Referenced classes of package android.os:
//            Looper, Handler, Message, MessageQueue

public class TestLooperManager
{
    private class LooperHolder
        implements Runnable
    {

        private void processMessage(MessageExecution messageexecution)
        {
            messageexecution;
            JVM INSTR monitorenter ;
            MessageExecution._2D_get0(messageexecution).target.dispatchMessage(MessageExecution._2D_get0(messageexecution));
            MessageExecution._2D_set1(messageexecution, null);
_L1:
            messageexecution.notifyAll();
            messageexecution;
            JVM INSTR monitorexit ;
            return;
            Object obj;
            obj;
            MessageExecution._2D_set1(messageexecution, ((Throwable) (obj)));
              goto _L1
            obj;
            throw obj;
        }

        public void run()
        {
            Object obj = TestLooperManager.this;
            obj;
            JVM INSTR monitorenter ;
            TestLooperManager._2D_set0(TestLooperManager.this, true);
            notify();
            obj;
            JVM INSTR monitorexit ;
            do
            {
                if(TestLooperManager._2D_get1(TestLooperManager.this))
                    break;
                try
                {
                    obj = (MessageExecution)TestLooperManager._2D_get0(TestLooperManager.this).take();
                    if(MessageExecution._2D_get0(((MessageExecution) (obj))) != null)
                        processMessage(((MessageExecution) (obj)));
                }
                // Misplaced declaration of an exception variable
                catch(Object obj) { }
            } while(true);
              goto _L1
            Exception exception1;
            exception1;
            throw exception1;
_L1:
            TestLooperManager testloopermanager = TestLooperManager.this;
            testloopermanager;
            JVM INSTR monitorenter ;
            TestLooperManager._2D_set0(TestLooperManager.this, false);
            testloopermanager;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        final TestLooperManager this$0;

        private LooperHolder()
        {
            this$0 = TestLooperManager.this;
            super();
        }

        LooperHolder(LooperHolder looperholder)
        {
            this();
        }
    }

    private static class MessageExecution
    {

        static Message _2D_get0(MessageExecution messageexecution)
        {
            return messageexecution.m;
        }

        static Throwable _2D_get1(MessageExecution messageexecution)
        {
            return messageexecution.response;
        }

        static Message _2D_set0(MessageExecution messageexecution, Message message)
        {
            messageexecution.m = message;
            return message;
        }

        static Throwable _2D_set1(MessageExecution messageexecution, Throwable throwable)
        {
            messageexecution.response = throwable;
            return throwable;
        }

        private Message m;
        private Throwable response;

        private MessageExecution()
        {
        }

        MessageExecution(MessageExecution messageexecution)
        {
            this();
        }
    }


    static LinkedBlockingQueue _2D_get0(TestLooperManager testloopermanager)
    {
        return testloopermanager.mExecuteQueue;
    }

    static boolean _2D_get1(TestLooperManager testloopermanager)
    {
        return testloopermanager.mReleased;
    }

    static boolean _2D_set0(TestLooperManager testloopermanager, boolean flag)
    {
        testloopermanager.mLooperBlocked = flag;
        return flag;
    }

    public TestLooperManager(Looper looper)
    {
label0:
        {
            mExecuteQueue = new LinkedBlockingQueue();
            synchronized(sHeldLoopers)
            {
                if(sHeldLoopers.contains(looper))
                {
                    looper = JVM INSTR new #56  <Class RuntimeException>;
                    looper.RuntimeException("TestLooperManager already held for this looper");
                    throw looper;
                }
                break label0;
            }
        }
        sHeldLoopers.add(looper);
        arrayset;
        JVM INSTR monitorexit ;
        mLooper = looper;
        mQueue = mLooper.getQueue();
        (new Handler(looper)).post(new LooperHolder(null));
        return;
    }

    private void checkReleased()
    {
        if(mReleased)
            throw new RuntimeException("release() has already be called");
        else
            return;
    }

    public void execute(Message message)
    {
        checkReleased();
        if(Looper.myLooper() != mLooper) goto _L2; else goto _L1
_L1:
        message.target.dispatchMessage(message);
_L4:
        return;
_L2:
        MessageExecution messageexecution;
        messageexecution = new MessageExecution(null);
        MessageExecution._2D_set0(messageexecution, message);
        messageexecution;
        JVM INSTR monitorenter ;
        mExecuteQueue.add(messageexecution);
        try
        {
            messageexecution.wait();
        }
        // Misplaced declaration of an exception variable
        catch(Message message) { }
        if(MessageExecution._2D_get1(messageexecution) != null)
        {
            message = JVM INSTR new #56  <Class RuntimeException>;
            message.RuntimeException(MessageExecution._2D_get1(messageexecution));
            throw message;
        }
        break MISSING_BLOCK_LABEL_83;
        message;
        messageexecution;
        JVM INSTR monitorexit ;
        throw message;
        messageexecution;
        JVM INSTR monitorexit ;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public MessageQueue getMessageQueue()
    {
        checkReleased();
        return mQueue;
    }

    public MessageQueue getQueue()
    {
        return getMessageQueue();
    }

    public boolean hasMessages(Handler handler, Object obj, int i)
    {
        checkReleased();
        return mQueue.hasMessages(handler, i, obj);
    }

    public boolean hasMessages(Handler handler, Object obj, Runnable runnable)
    {
        checkReleased();
        return mQueue.hasMessages(handler, runnable, obj);
    }

    public Message next()
    {
_L2:
        if(mLooperBlocked)
            break; /* Loop/switch isn't completed */
        this;
        JVM INSTR monitorenter ;
        try
        {
            wait();
        }
        catch(InterruptedException interruptedexception) { }
        this;
        JVM INSTR monitorexit ;
        if(true) goto _L2; else goto _L1
        Exception exception;
        exception;
        throw exception;
_L1:
        checkReleased();
        return mQueue.next();
    }

    public void recycle(Message message)
    {
        checkReleased();
        message.recycleUnchecked();
    }

    public void release()
    {
        ArraySet arrayset = sHeldLoopers;
        arrayset;
        JVM INSTR monitorenter ;
        sHeldLoopers.remove(mLooper);
        arrayset;
        JVM INSTR monitorexit ;
        checkReleased();
        mReleased = true;
        mExecuteQueue.add(new MessageExecution(null));
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static final ArraySet sHeldLoopers = new ArraySet();
    private final LinkedBlockingQueue mExecuteQueue;
    private final Looper mLooper;
    private boolean mLooperBlocked;
    private final MessageQueue mQueue;
    private boolean mReleased;

}

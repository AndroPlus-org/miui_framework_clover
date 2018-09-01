// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.Log;
import android.util.Printer;

// Referenced classes of package android.os:
//            Looper, Message, MessageQueue, SystemClock, 
//            IMessenger, Binder

public class Handler
{
    private static final class BlockingRunnable
        implements Runnable
    {

        public boolean postAndWait(Handler handler, long l)
        {
            if(!handler.post(this))
                return false;
            this;
            JVM INSTR monitorenter ;
            if(l <= 0L) goto _L2; else goto _L1
_L1:
            long l1 = SystemClock.uptimeMillis();
_L5:
            if(mDone) goto _L4; else goto _L3
_L3:
            long l2 = SystemClock.uptimeMillis();
            l2 = (l1 + l) - l2;
            if(l2 <= 0L)
                return false;
            try
            {
                wait(l2);
            }
            // Misplaced declaration of an exception variable
            catch(Handler handler) { }
              goto _L5
_L2:
            boolean flag = mDone;
            if(flag)
                break; /* Loop/switch isn't completed */
            try
            {
                wait();
            }
            // Misplaced declaration of an exception variable
            catch(Handler handler) { }
            if(true) goto _L2; else goto _L4
_L4:
            this;
            JVM INSTR monitorexit ;
            return true;
            handler;
            throw handler;
        }

        public void run()
        {
            mTask.run();
            this;
            JVM INSTR monitorenter ;
            mDone = true;
            notifyAll();
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
            exception;
            this;
            JVM INSTR monitorenter ;
            mDone = true;
            notifyAll();
            this;
            JVM INSTR monitorexit ;
            throw exception;
            exception;
            throw exception;
        }

        private boolean mDone;
        private final Runnable mTask;

        public BlockingRunnable(Runnable runnable)
        {
            mTask = runnable;
        }
    }

    public static interface Callback
    {

        public abstract boolean handleMessage(Message message);
    }

    private final class MessengerImpl extends IMessenger.Stub
    {

        public void send(Message message)
        {
            message.sendingUid = Binder.getCallingUid();
            sendMessage(message);
        }

        final Handler this$0;

        private MessengerImpl()
        {
            this$0 = Handler.this;
            super();
        }

        MessengerImpl(MessengerImpl messengerimpl)
        {
            this();
        }
    }


    public Handler()
    {
        this(((Callback) (null)), false);
    }

    public Handler(Callback callback)
    {
        this(callback, false);
    }

    public Handler(Callback callback, boolean flag)
    {
        mLooper = Looper.myLooper();
        if(mLooper == null)
        {
            throw new RuntimeException("Can't create handler inside thread that has not called Looper.prepare()");
        } else
        {
            mQueue = mLooper.mQueue;
            mCallback = callback;
            mAsynchronous = flag;
            return;
        }
    }

    public Handler(Looper looper)
    {
        this(looper, null, false);
    }

    public Handler(Looper looper, Callback callback)
    {
        this(looper, callback, false);
    }

    public Handler(Looper looper, Callback callback, boolean flag)
    {
        mLooper = looper;
        mQueue = looper.mQueue;
        mCallback = callback;
        mAsynchronous = flag;
    }

    public Handler(boolean flag)
    {
        this(((Callback) (null)), flag);
    }

    private boolean enqueueMessage(MessageQueue messagequeue, Message message, long l)
    {
        message.target = this;
        if(mAsynchronous)
            message.setAsynchronous(true);
        return messagequeue.enqueueMessage(message, l);
    }

    public static Handler getMain()
    {
        if(MAIN_THREAD_HANDLER == null)
            MAIN_THREAD_HANDLER = new Handler(Looper.getMainLooper());
        return MAIN_THREAD_HANDLER;
    }

    private static Message getPostMessage(Runnable runnable)
    {
        Message message = Message.obtain();
        message.callback = runnable;
        return message;
    }

    private static Message getPostMessage(Runnable runnable, Object obj)
    {
        Message message = Message.obtain();
        message.obj = obj;
        message.callback = runnable;
        return message;
    }

    private static void handleCallback(Message message)
    {
        message.callback.run();
    }

    public static Handler mainIfNull(Handler handler)
    {
        Handler handler1 = handler;
        if(handler == null)
            handler1 = getMain();
        return handler1;
    }

    public void dispatchMessage(Message message)
    {
        if(message.callback != null)
        {
            handleCallback(message);
        } else
        {
            if(mCallback != null && mCallback.handleMessage(message))
                return;
            handleMessage(message);
        }
    }

    public final void dump(Printer printer, String s)
    {
        printer.println((new StringBuilder()).append(s).append(this).append(" @ ").append(SystemClock.uptimeMillis()).toString());
        if(mLooper == null)
            printer.println((new StringBuilder()).append(s).append("looper uninitialized").toString());
        else
            mLooper.dump(printer, (new StringBuilder()).append(s).append("  ").toString());
    }

    public final void dumpMine(Printer printer, String s)
    {
        printer.println((new StringBuilder()).append(s).append(this).append(" @ ").append(SystemClock.uptimeMillis()).toString());
        if(mLooper == null)
            printer.println((new StringBuilder()).append(s).append("looper uninitialized").toString());
        else
            mLooper.dump(printer, (new StringBuilder()).append(s).append("  ").toString(), this);
    }

    final IMessenger getIMessenger()
    {
        MessageQueue messagequeue = mQueue;
        messagequeue;
        JVM INSTR monitorenter ;
        Object obj;
        if(mMessenger == null)
            break MISSING_BLOCK_LABEL_23;
        obj = mMessenger;
        messagequeue;
        JVM INSTR monitorexit ;
        return ((IMessenger) (obj));
        obj = JVM INSTR new #12  <Class Handler$MessengerImpl>;
        ((MessengerImpl) (obj)).this. MessengerImpl(null);
        mMessenger = ((IMessenger) (obj));
        obj = mMessenger;
        messagequeue;
        JVM INSTR monitorexit ;
        return ((IMessenger) (obj));
        Exception exception;
        exception;
        throw exception;
    }

    public final Looper getLooper()
    {
        return mLooper;
    }

    public String getMessageName(Message message)
    {
        if(message.callback != null)
            return message.callback.getClass().getName();
        else
            return (new StringBuilder()).append("0x").append(Integer.toHexString(message.what)).toString();
    }

    public String getTraceName(Message message)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getClass().getName()).append(": ");
        if(message.callback != null)
            stringbuilder.append(message.callback.getClass().getName());
        else
            stringbuilder.append("#").append(message.what);
        return stringbuilder.toString();
    }

    public void handleMessage(Message message)
    {
    }

    public final boolean hasCallbacks(Runnable runnable)
    {
        return mQueue.hasMessages(this, runnable, null);
    }

    public final boolean hasMessages(int i)
    {
        return mQueue.hasMessages(this, i, null);
    }

    public final boolean hasMessages(int i, Object obj)
    {
        return mQueue.hasMessages(this, i, obj);
    }

    public final boolean hasMessagesOrCallbacks()
    {
        return mQueue.hasMessages(this);
    }

    public final Message obtainMessage()
    {
        return Message.obtain(this);
    }

    public final Message obtainMessage(int i)
    {
        return Message.obtain(this, i);
    }

    public final Message obtainMessage(int i, int j, int k)
    {
        return Message.obtain(this, i, j, k);
    }

    public final Message obtainMessage(int i, int j, int k, Object obj)
    {
        return Message.obtain(this, i, j, k, obj);
    }

    public final Message obtainMessage(int i, Object obj)
    {
        return Message.obtain(this, i, obj);
    }

    public final boolean post(Runnable runnable)
    {
        return sendMessageDelayed(getPostMessage(runnable), 0L);
    }

    public final boolean postAtFrontOfQueue(Runnable runnable)
    {
        return sendMessageAtFrontOfQueue(getPostMessage(runnable));
    }

    public final boolean postAtTime(Runnable runnable, long l)
    {
        return sendMessageAtTime(getPostMessage(runnable), l);
    }

    public final boolean postAtTime(Runnable runnable, Object obj, long l)
    {
        return sendMessageAtTime(getPostMessage(runnable, obj), l);
    }

    public final boolean postDelayed(Runnable runnable, long l)
    {
        return sendMessageDelayed(getPostMessage(runnable), l);
    }

    public final void removeCallbacks(Runnable runnable)
    {
        mQueue.removeMessages(this, runnable, null);
    }

    public final void removeCallbacks(Runnable runnable, Object obj)
    {
        mQueue.removeMessages(this, runnable, obj);
    }

    public final void removeCallbacksAndMessages(Object obj)
    {
        mQueue.removeCallbacksAndMessages(this, obj);
    }

    public final void removeMessages(int i)
    {
        mQueue.removeMessages(this, i, null);
    }

    public final void removeMessages(int i, Object obj)
    {
        mQueue.removeMessages(this, i, obj);
    }

    public final boolean runWithScissors(Runnable runnable, long l)
    {
        if(runnable == null)
            throw new IllegalArgumentException("runnable must not be null");
        if(l < 0L)
            throw new IllegalArgumentException("timeout must be non-negative");
        if(Looper.myLooper() == mLooper)
        {
            runnable.run();
            return true;
        } else
        {
            return (new BlockingRunnable(runnable)).postAndWait(this, l);
        }
    }

    public final boolean sendEmptyMessage(int i)
    {
        return sendEmptyMessageDelayed(i, 0L);
    }

    public final boolean sendEmptyMessageAtTime(int i, long l)
    {
        Message message = Message.obtain();
        message.what = i;
        return sendMessageAtTime(message, l);
    }

    public final boolean sendEmptyMessageDelayed(int i, long l)
    {
        Message message = Message.obtain();
        message.what = i;
        return sendMessageDelayed(message, l);
    }

    public final boolean sendMessage(Message message)
    {
        return sendMessageDelayed(message, 0L);
    }

    public final boolean sendMessageAtFrontOfQueue(Message message)
    {
        MessageQueue messagequeue = mQueue;
        if(messagequeue == null)
        {
            message = new RuntimeException((new StringBuilder()).append(this).append(" sendMessageAtTime() called with no mQueue").toString());
            Log.w("Looper", message.getMessage(), message);
            return false;
        } else
        {
            return enqueueMessage(messagequeue, message, 0L);
        }
    }

    public boolean sendMessageAtTime(Message message, long l)
    {
        MessageQueue messagequeue = mQueue;
        if(messagequeue == null)
        {
            message = new RuntimeException((new StringBuilder()).append(this).append(" sendMessageAtTime() called with no mQueue").toString());
            Log.w("Looper", message.getMessage(), message);
            return false;
        } else
        {
            return enqueueMessage(messagequeue, message, l);
        }
    }

    public final boolean sendMessageDelayed(Message message, long l)
    {
        long l1 = l;
        if(l < 0L)
            l1 = 0L;
        return sendMessageAtTime(message, SystemClock.uptimeMillis() + l1);
    }

    public String toString()
    {
        return (new StringBuilder()).append("Handler (").append(getClass().getName()).append(") {").append(Integer.toHexString(System.identityHashCode(this))).append("}").toString();
    }

    private static final boolean FIND_POTENTIAL_LEAKS = false;
    private static Handler MAIN_THREAD_HANDLER = null;
    private static final String TAG = "Handler";
    final boolean mAsynchronous;
    final Callback mCallback;
    final Looper mLooper;
    IMessenger mMessenger;
    final MessageQueue mQueue;

}

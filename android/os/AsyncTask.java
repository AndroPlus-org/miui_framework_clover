// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.Log;
import java.util.ArrayDeque;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package android.os:
//            Looper, Handler, Message, Process, 
//            Binder

public abstract class AsyncTask
{
    private static class AsyncTaskResult
    {

        final Object mData[];
        final AsyncTask mTask;

        transient AsyncTaskResult(AsyncTask asynctask, Object aobj[])
        {
            mTask = asynctask;
            mData = aobj;
        }
    }

    private static class InternalHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            AsyncTaskResult asynctaskresult = (AsyncTaskResult)message.obj;
            message.what;
            JVM INSTR tableswitch 1 2: default 36
        //                       1 37
        //                       2 53;
               goto _L1 _L2 _L3
_L1:
            return;
_L2:
            AsyncTask._2D_wrap1(asynctaskresult.mTask, asynctaskresult.mData[0]);
            continue; /* Loop/switch isn't completed */
_L3:
            asynctaskresult.mTask.onProgressUpdate(asynctaskresult.mData);
            if(true) goto _L1; else goto _L4
_L4:
        }

        public InternalHandler(Looper looper)
        {
            super(looper);
        }
    }

    private static class SerialExecutor
        implements Executor
    {

        public void execute(Runnable runnable)
        {
            this;
            JVM INSTR monitorenter ;
            ArrayDeque arraydeque = mTasks;
            Runnable runnable1 = JVM INSTR new #11  <Class AsyncTask$SerialExecutor$1>;
            runnable. super();
            arraydeque.offer(runnable1);
            if(mActive == null)
                scheduleNext();
            this;
            JVM INSTR monitorexit ;
            return;
            runnable;
            throw runnable;
        }

        protected void scheduleNext()
        {
            this;
            JVM INSTR monitorenter ;
            Runnable runnable;
            runnable = (Runnable)mTasks.poll();
            mActive = runnable;
            if(runnable == null)
                break MISSING_BLOCK_LABEL_34;
            AsyncTask.THREAD_POOL_EXECUTOR.execute(mActive);
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        Runnable mActive;
        final ArrayDeque mTasks;

        private SerialExecutor()
        {
            mTasks = new ArrayDeque();
        }

        SerialExecutor(SerialExecutor serialexecutor)
        {
            this();
        }
    }

    public static final class Status extends Enum
    {

        public static Status valueOf(String s)
        {
            return (Status)Enum.valueOf(android/os/AsyncTask$Status, s);
        }

        public static Status[] values()
        {
            return $VALUES;
        }

        private static final Status $VALUES[];
        public static final Status FINISHED;
        public static final Status PENDING;
        public static final Status RUNNING;

        static 
        {
            PENDING = new Status("PENDING", 0);
            RUNNING = new Status("RUNNING", 1);
            FINISHED = new Status("FINISHED", 2);
            $VALUES = (new Status[] {
                PENDING, RUNNING, FINISHED
            });
        }

        private Status(String s, int i)
        {
            super(s, i);
        }
    }

    private static abstract class WorkerRunnable
        implements Callable
    {

        Object mParams[];

        private WorkerRunnable()
        {
        }

        WorkerRunnable(WorkerRunnable workerrunnable)
        {
            this();
        }
    }


    static AtomicBoolean _2D_get0(AsyncTask asynctask)
    {
        return asynctask.mCancelled;
    }

    static AtomicBoolean _2D_get1(AsyncTask asynctask)
    {
        return asynctask.mTaskInvoked;
    }

    private static int[] _2D_getandroid_2D_os_2D_AsyncTask$StatusSwitchesValues()
    {
        if(_2D_android_2D_os_2D_AsyncTask$StatusSwitchesValues != null)
            return _2D_android_2D_os_2D_AsyncTask$StatusSwitchesValues;
        int ai[] = new int[Status.values().length];
        try
        {
            ai[Status.FINISHED.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[Status.PENDING.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[Status.RUNNING.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_android_2D_os_2D_AsyncTask$StatusSwitchesValues = ai;
        return ai;
    }

    static Object _2D_wrap0(AsyncTask asynctask, Object obj)
    {
        return asynctask.postResult(obj);
    }

    static void _2D_wrap1(AsyncTask asynctask, Object obj)
    {
        asynctask.finish(obj);
    }

    static void _2D_wrap2(AsyncTask asynctask, Object obj)
    {
        asynctask.postResultIfNotInvoked(obj);
    }

    public AsyncTask()
    {
        this((Looper)null);
    }

    public AsyncTask(Handler handler)
    {
        Looper looper = null;
        if(handler != null)
            looper = handler.getLooper();
        this(looper);
    }

    public AsyncTask(Looper looper)
    {
        mStatus = Status.PENDING;
        mCancelled = new AtomicBoolean();
        mTaskInvoked = new AtomicBoolean();
        if(looper == null || looper == Looper.getMainLooper())
            looper = getMainHandler();
        else
            looper = new Handler(looper);
        mHandler = looper;
        mWorker = new WorkerRunnable() {

            public Object call()
                throws Exception
            {
                Object obj;
                Object obj1;
                Object obj2;
                Object obj3;
                AsyncTask._2D_get1(AsyncTask.this).set(true);
                obj = null;
                obj1 = null;
                obj2 = obj1;
                obj3 = obj;
                Process.setThreadPriority(10);
                obj2 = obj1;
                obj3 = obj;
                obj1 = doInBackground(mParams);
                obj2 = obj1;
                obj3 = obj1;
                Binder.flushPendingCommands();
                AsyncTask._2D_wrap0(AsyncTask.this, obj1);
                return obj1;
                Throwable throwable;
                throwable;
                obj3 = obj2;
                AsyncTask._2D_get0(AsyncTask.this).set(true);
                obj3 = obj2;
                throw throwable;
                Exception exception;
                exception;
                AsyncTask._2D_wrap0(AsyncTask.this, obj3);
                throw exception;
            }

            final AsyncTask this$0;

            
            {
                this$0 = AsyncTask.this;
                super(null);
            }
        }
;
        mFuture = new FutureTask(mWorker) {

            protected void done()
            {
                AsyncTask._2D_wrap2(AsyncTask.this, get());
_L1:
                return;
                Object obj;
                obj;
                AsyncTask._2D_wrap2(AsyncTask.this, null);
                  goto _L1
                obj;
                throw new RuntimeException("An error occurred while executing doInBackground()", ((ExecutionException) (obj)).getCause());
                obj;
                Log.w("AsyncTask", ((Throwable) (obj)));
                  goto _L1
            }

            final AsyncTask this$0;

            
            {
                this$0 = AsyncTask.this;
                super(callable);
            }
        }
;
    }

    public static void execute(Runnable runnable)
    {
        sDefaultExecutor.execute(runnable);
    }

    private void finish(Object obj)
    {
        if(isCancelled())
            onCancelled(obj);
        else
            onPostExecute(obj);
        mStatus = Status.FINISHED;
    }

    private Handler getHandler()
    {
        return mHandler;
    }

    private static Handler getMainHandler()
    {
        android/os/AsyncTask;
        JVM INSTR monitorenter ;
        InternalHandler internalhandler1;
        if(sHandler == null)
        {
            InternalHandler internalhandler = JVM INSTR new #16  <Class AsyncTask$InternalHandler>;
            internalhandler.InternalHandler(Looper.getMainLooper());
            sHandler = internalhandler;
        }
        internalhandler1 = sHandler;
        android/os/AsyncTask;
        JVM INSTR monitorexit ;
        return internalhandler1;
        Exception exception;
        exception;
        throw exception;
    }

    private Object postResult(Object obj)
    {
        getHandler().obtainMessage(1, new AsyncTaskResult(this, new Object[] {
            obj
        })).sendToTarget();
        return obj;
    }

    private void postResultIfNotInvoked(Object obj)
    {
        if(!mTaskInvoked.get())
            postResult(obj);
    }

    public static void setDefaultExecutor(Executor executor)
    {
        sDefaultExecutor = executor;
    }

    public final boolean cancel(boolean flag)
    {
        mCancelled.set(true);
        return mFuture.cancel(flag);
    }

    protected transient abstract Object doInBackground(Object aobj[]);

    public final transient AsyncTask execute(Object aobj[])
    {
        return executeOnExecutor(sDefaultExecutor, aobj);
    }

    public final transient AsyncTask executeOnExecutor(Executor executor, Object aobj[])
    {
        if(mStatus == Status.PENDING) goto _L2; else goto _L1
_L1:
        _2D_getandroid_2D_os_2D_AsyncTask$StatusSwitchesValues()[mStatus.ordinal()];
        JVM INSTR tableswitch 1 2: default 44
    //                   1 86
    //                   2 75;
           goto _L2 _L3 _L4
_L2:
        mStatus = Status.RUNNING;
        onPreExecute();
        mWorker.mParams = aobj;
        executor.execute(mFuture);
        return this;
_L4:
        throw new IllegalStateException("Cannot execute task: the task is already running.");
_L3:
        throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
    }

    public final Object get()
        throws InterruptedException, ExecutionException
    {
        return mFuture.get();
    }

    public final Object get(long l, TimeUnit timeunit)
        throws InterruptedException, ExecutionException, TimeoutException
    {
        return mFuture.get(l, timeunit);
    }

    public final Status getStatus()
    {
        return mStatus;
    }

    public final boolean isCancelled()
    {
        return mCancelled.get();
    }

    protected void onCancelled()
    {
    }

    protected void onCancelled(Object obj)
    {
        onCancelled();
    }

    protected void onPostExecute(Object obj)
    {
    }

    protected void onPreExecute()
    {
    }

    protected transient void onProgressUpdate(Object aobj[])
    {
    }

    protected final transient void publishProgress(Object aobj[])
    {
        if(!isCancelled())
            getHandler().obtainMessage(2, new AsyncTaskResult(this, aobj)).sendToTarget();
    }

    private static final int _2D_android_2D_os_2D_AsyncTask$StatusSwitchesValues[];
    private static final int CORE_POOL_SIZE;
    private static final int CPU_COUNT;
    private static final int KEEP_ALIVE_SECONDS = 30;
    private static final String LOG_TAG = "AsyncTask";
    private static final int MAXIMUM_POOL_SIZE;
    private static final int MESSAGE_POST_PROGRESS = 2;
    private static final int MESSAGE_POST_RESULT = 1;
    public static final Executor SERIAL_EXECUTOR;
    public static final Executor THREAD_POOL_EXECUTOR;
    private static volatile Executor sDefaultExecutor;
    private static InternalHandler sHandler;
    private static final BlockingQueue sPoolWorkQueue;
    private static final ThreadFactory sThreadFactory;
    private final AtomicBoolean mCancelled;
    private final FutureTask mFuture;
    private final Handler mHandler;
    private volatile Status mStatus;
    private final AtomicBoolean mTaskInvoked;
    private final WorkerRunnable mWorker;

    static 
    {
        CPU_COUNT = Runtime.getRuntime().availableProcessors();
        CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
        MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
        sThreadFactory = new ThreadFactory() {

            public Thread newThread(Runnable runnable)
            {
                return new Thread(runnable, (new StringBuilder()).append("AsyncTask #").append(mCount.getAndIncrement()).toString());
            }

            private final AtomicInteger mCount = new AtomicInteger(1);

        }
;
        sPoolWorkQueue = new LinkedBlockingQueue(128);
        ThreadPoolExecutor threadpoolexecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 30L, TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);
        threadpoolexecutor.allowCoreThreadTimeOut(true);
        THREAD_POOL_EXECUTOR = threadpoolexecutor;
        SERIAL_EXECUTOR = new SerialExecutor(null);
        sDefaultExecutor = SERIAL_EXECUTOR;
    }

    // Unreferenced inner class android/os/AsyncTask$SerialExecutor$1

/* anonymous class */
    class SerialExecutor._cls1
        implements Runnable
    {

        public void run()
        {
            r.run();
            scheduleNext();
            return;
            Exception exception;
            exception;
            scheduleNext();
            throw exception;
        }

        final SerialExecutor this$1;
        final Runnable val$r;

            
            {
                this$1 = final_serialexecutor;
                r = Runnable.this;
                super();
            }
    }

}

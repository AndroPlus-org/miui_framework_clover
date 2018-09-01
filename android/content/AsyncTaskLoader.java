// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.os.*;
import android.util.TimeUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

// Referenced classes of package android.content:
//            Loader, Context

public abstract class AsyncTaskLoader extends Loader
{
    final class LoadTask extends AsyncTask
        implements Runnable
    {

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((Void[])aobj);
        }

        protected transient Object doInBackground(Void avoid[])
        {
            try
            {
                avoid = ((Void []) (onLoadInBackground()));
            }
            // Misplaced declaration of an exception variable
            catch(Void avoid[])
            {
                if(!isCancelled())
                    throw avoid;
                else
                    return null;
            }
            return avoid;
        }

        protected void onCancelled(Object obj)
        {
            dispatchOnCancelled(this, obj);
            mDone.countDown();
            return;
            obj;
            mDone.countDown();
            throw obj;
        }

        protected void onPostExecute(Object obj)
        {
            dispatchOnLoadComplete(this, obj);
            mDone.countDown();
            return;
            obj;
            mDone.countDown();
            throw obj;
        }

        public void run()
        {
            waiting = false;
            executePendingTask();
        }

        public void waitForLoader()
        {
            mDone.await();
_L2:
            return;
            InterruptedException interruptedexception;
            interruptedexception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        private final CountDownLatch mDone = new CountDownLatch(1);
        final AsyncTaskLoader this$0;
        boolean waiting;

        LoadTask()
        {
            this$0 = AsyncTaskLoader.this;
            super();
        }
    }


    public AsyncTaskLoader(Context context)
    {
        this(context, AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public AsyncTaskLoader(Context context, Executor executor)
    {
        super(context);
        mLastLoadCompleteTime = -10000L;
        mExecutor = executor;
    }

    public void cancelLoadInBackground()
    {
    }

    void dispatchOnCancelled(LoadTask loadtask, Object obj)
    {
        onCanceled(obj);
        if(mCancellingTask == loadtask)
        {
            rollbackContentChanged();
            mLastLoadCompleteTime = SystemClock.uptimeMillis();
            mCancellingTask = null;
            deliverCancellation();
            executePendingTask();
        }
    }

    void dispatchOnLoadComplete(LoadTask loadtask, Object obj)
    {
        if(mTask != loadtask)
            dispatchOnCancelled(loadtask, obj);
        else
        if(isAbandoned())
        {
            onCanceled(obj);
        } else
        {
            commitContentChanged();
            mLastLoadCompleteTime = SystemClock.uptimeMillis();
            mTask = null;
            deliverResult(obj);
        }
    }

    public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        super.dump(s, filedescriptor, printwriter, as);
        if(mTask != null)
        {
            printwriter.print(s);
            printwriter.print("mTask=");
            printwriter.print(mTask);
            printwriter.print(" waiting=");
            printwriter.println(mTask.waiting);
        }
        if(mCancellingTask != null)
        {
            printwriter.print(s);
            printwriter.print("mCancellingTask=");
            printwriter.print(mCancellingTask);
            printwriter.print(" waiting=");
            printwriter.println(mCancellingTask.waiting);
        }
        if(mUpdateThrottle != 0L)
        {
            printwriter.print(s);
            printwriter.print("mUpdateThrottle=");
            TimeUtils.formatDuration(mUpdateThrottle, printwriter);
            printwriter.print(" mLastLoadCompleteTime=");
            TimeUtils.formatDuration(mLastLoadCompleteTime, SystemClock.uptimeMillis(), printwriter);
            printwriter.println();
        }
    }

    void executePendingTask()
    {
        if(mCancellingTask == null && mTask != null)
        {
            if(mTask.waiting)
            {
                mTask.waiting = false;
                mHandler.removeCallbacks(mTask);
            }
            if(mUpdateThrottle > 0L && SystemClock.uptimeMillis() < mLastLoadCompleteTime + mUpdateThrottle)
            {
                mTask.waiting = true;
                mHandler.postAtTime(mTask, mLastLoadCompleteTime + mUpdateThrottle);
                return;
            }
            mTask.executeOnExecutor(mExecutor, (Void[])null);
        }
    }

    public boolean isLoadInBackgroundCanceled()
    {
        boolean flag;
        if(mCancellingTask != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public abstract Object loadInBackground();

    protected boolean onCancelLoad()
    {
        if(mTask != null)
        {
            if(!mStarted)
                mContentChanged = true;
            if(mCancellingTask != null)
            {
                if(mTask.waiting)
                {
                    mTask.waiting = false;
                    mHandler.removeCallbacks(mTask);
                }
                mTask = null;
                return false;
            }
            if(mTask.waiting)
            {
                mTask.waiting = false;
                mHandler.removeCallbacks(mTask);
                mTask = null;
                return false;
            }
            boolean flag = mTask.cancel(false);
            if(flag)
            {
                mCancellingTask = mTask;
                cancelLoadInBackground();
            }
            mTask = null;
            return flag;
        } else
        {
            return false;
        }
    }

    public void onCanceled(Object obj)
    {
    }

    protected void onForceLoad()
    {
        super.onForceLoad();
        cancelLoad();
        mTask = new LoadTask();
        executePendingTask();
    }

    protected Object onLoadInBackground()
    {
        return loadInBackground();
    }

    public void setUpdateThrottle(long l)
    {
        mUpdateThrottle = l;
        if(l != 0L)
            mHandler = new Handler();
    }

    public void waitForLoader()
    {
        LoadTask loadtask = mTask;
        if(loadtask != null)
            loadtask.waitForLoader();
    }

    static final boolean DEBUG = false;
    static final String TAG = "AsyncTaskLoader";
    volatile LoadTask mCancellingTask;
    private final Executor mExecutor;
    Handler mHandler;
    long mLastLoadCompleteTime;
    volatile LoadTask mTask;
    long mUpdateThrottle;
}

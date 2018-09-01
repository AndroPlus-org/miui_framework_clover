// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import android.os.AsyncTask;
import android.util.Log;

// Referenced classes of package android.filterfw.core:
//            GraphRunner, SimpleScheduler, SyncRunner, FilterContext, 
//            FilterGraph

public class AsyncRunner extends GraphRunner
{
    private class AsyncRunnerTask extends AsyncTask
    {

        protected transient RunnerResult doInBackground(SyncRunner asyncrunner[])
        {
            RunnerResult runnerresult = new RunnerResult(null);
            if(asyncrunner.length > 1)
            {
                asyncrunner = JVM INSTR new #35  <Class RuntimeException>;
                asyncrunner.RuntimeException("More than one runner received!");
                throw asyncrunner;
            }
              goto _L1
            asyncrunner;
            runnerresult.exception = asyncrunner;
            runnerresult.status = 6;
_L3:
            try
            {
                deactivateGlContext();
            }
            // Misplaced declaration of an exception variable
            catch(SyncRunner asyncrunner[])
            {
                runnerresult.exception = asyncrunner;
                runnerresult.status = 6;
            }
            if(AsyncRunner._2D_get1(AsyncRunner.this))
                Log.v("AsyncRunnerTask", "Done with background graph processing.");
            return runnerresult;
_L1:
            asyncrunner[0].assertReadyToStep();
            if(AsyncRunner._2D_get1(AsyncRunner.this))
                Log.v("AsyncRunnerTask", "Starting background graph processing.");
            activateGlContext();
            if(AsyncRunner._2D_get1(AsyncRunner.this))
                Log.v("AsyncRunnerTask", "Preparing filter graph for processing.");
            asyncrunner[0].beginProcessing();
            if(AsyncRunner._2D_get1(AsyncRunner.this))
                Log.v("AsyncRunnerTask", "Running graph.");
            runnerresult.status = 1;
            do
            {
                if(isCancelled() || runnerresult.status != 1)
                    break;
                if(!asyncrunner[0].performStep())
                {
                    runnerresult.status = asyncrunner[0].determinePostRunState();
                    if(runnerresult.status == 3)
                    {
                        asyncrunner[0].waitUntilWake();
                        runnerresult.status = 1;
                    }
                }
            } while(true);
            if(!isCancelled()) goto _L3; else goto _L2
_L2:
            runnerresult.status = 5;
              goto _L3
        }

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((SyncRunner[])aobj);
        }

        protected void onCancelled(RunnerResult runnerresult)
        {
            onPostExecute(runnerresult);
        }

        protected volatile void onCancelled(Object obj)
        {
            onCancelled((RunnerResult)obj);
        }

        protected void onPostExecute(RunnerResult runnerresult)
        {
            if(AsyncRunner._2D_get1(AsyncRunner.this))
                Log.v("AsyncRunnerTask", "Starting post-execute.");
            AsyncRunner._2D_wrap1(AsyncRunner.this, false);
            RunnerResult runnerresult1 = runnerresult;
            if(runnerresult == null)
            {
                runnerresult1 = new RunnerResult(null);
                runnerresult1.status = 5;
            }
            AsyncRunner._2D_wrap0(AsyncRunner.this, runnerresult1.exception);
            if(runnerresult1.status == 5 || runnerresult1.status == 6)
            {
                if(AsyncRunner._2D_get1(AsyncRunner.this))
                    Log.v("AsyncRunnerTask", "Closing filters.");
                try
                {
                    AsyncRunner._2D_get2(AsyncRunner.this).close();
                }
                // Misplaced declaration of an exception variable
                catch(RunnerResult runnerresult)
                {
                    runnerresult1.status = 6;
                    AsyncRunner._2D_wrap0(AsyncRunner.this, runnerresult);
                }
            }
            if(AsyncRunner._2D_get0(AsyncRunner.this) != null)
            {
                if(AsyncRunner._2D_get1(AsyncRunner.this))
                    Log.v("AsyncRunnerTask", "Calling graph done callback.");
                AsyncRunner._2D_get0(AsyncRunner.this).onRunnerDone(runnerresult1.status);
            }
            if(AsyncRunner._2D_get1(AsyncRunner.this))
                Log.v("AsyncRunnerTask", "Completed post-execute.");
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((RunnerResult)obj);
        }

        private static final String TAG = "AsyncRunnerTask";
        final AsyncRunner this$0;

        private AsyncRunnerTask()
        {
            this$0 = AsyncRunner.this;
            super();
        }

        AsyncRunnerTask(AsyncRunnerTask asyncrunnertask)
        {
            this();
        }
    }

    private class RunnerResult
    {

        public Exception exception;
        public int status;
        final AsyncRunner this$0;

        private RunnerResult()
        {
            this$0 = AsyncRunner.this;
            super();
            status = 0;
        }

        RunnerResult(RunnerResult runnerresult)
        {
            this();
        }
    }


    static GraphRunner.OnRunnerDoneListener _2D_get0(AsyncRunner asyncrunner)
    {
        return asyncrunner.mDoneListener;
    }

    static boolean _2D_get1(AsyncRunner asyncrunner)
    {
        return asyncrunner.mLogVerbose;
    }

    static SyncRunner _2D_get2(AsyncRunner asyncrunner)
    {
        return asyncrunner.mRunner;
    }

    static void _2D_wrap0(AsyncRunner asyncrunner, Exception exception)
    {
        asyncrunner.setException(exception);
    }

    static void _2D_wrap1(AsyncRunner asyncrunner, boolean flag)
    {
        asyncrunner.setRunning(flag);
    }

    public AsyncRunner(FilterContext filtercontext)
    {
        super(filtercontext);
        mSchedulerClass = android/filterfw/core/SimpleScheduler;
        mLogVerbose = Log.isLoggable("AsyncRunner", 2);
    }

    public AsyncRunner(FilterContext filtercontext, Class class1)
    {
        super(filtercontext);
        mSchedulerClass = class1;
        mLogVerbose = Log.isLoggable("AsyncRunner", 2);
    }

    private void setException(Exception exception)
    {
        this;
        JVM INSTR monitorenter ;
        mException = exception;
        this;
        JVM INSTR monitorexit ;
        return;
        exception;
        throw exception;
    }

    private void setRunning(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        isProcessing = flag;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void close()
    {
        this;
        JVM INSTR monitorenter ;
        if(isRunning())
        {
            RuntimeException runtimeexception = JVM INSTR new #79  <Class RuntimeException>;
            runtimeexception.RuntimeException("Cannot close graph while it is running!");
            throw runtimeexception;
        }
        break MISSING_BLOCK_LABEL_26;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if(mLogVerbose)
            Log.v("AsyncRunner", "Closing filters.");
        mRunner.close();
        this;
        JVM INSTR monitorexit ;
    }

    public Exception getError()
    {
        this;
        JVM INSTR monitorenter ;
        Exception exception = mException;
        this;
        JVM INSTR monitorexit ;
        return exception;
        Exception exception1;
        exception1;
        throw exception1;
    }

    public FilterGraph getGraph()
    {
        FilterGraph filtergraph = null;
        if(mRunner != null)
            filtergraph = mRunner.getGraph();
        return filtergraph;
    }

    public boolean isRunning()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = isProcessing;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public void run()
    {
        this;
        JVM INSTR monitorenter ;
        if(mLogVerbose)
            Log.v("AsyncRunner", "Running graph.");
        setException(null);
        if(isRunning())
        {
            RuntimeException runtimeexception = JVM INSTR new #79  <Class RuntimeException>;
            runtimeexception.RuntimeException("Graph is already running!");
            throw runtimeexception;
        }
        break MISSING_BLOCK_LABEL_46;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if(mRunner == null)
        {
            RuntimeException runtimeexception1 = JVM INSTR new #79  <Class RuntimeException>;
            runtimeexception1.RuntimeException("Cannot run before a graph is set!");
            throw runtimeexception1;
        }
        AsyncRunnerTask asyncrunnertask = JVM INSTR new #6   <Class AsyncRunner$AsyncRunnerTask>;
        getClass();
        asyncrunnertask.this. AsyncRunnerTask(null);
        mRunTask = asyncrunnertask;
        setRunning(true);
        mRunTask.execute(new SyncRunner[] {
            mRunner
        });
        this;
        JVM INSTR monitorexit ;
    }

    public void setDoneCallback(GraphRunner.OnRunnerDoneListener onrunnerdonelistener)
    {
        mDoneListener = onrunnerdonelistener;
    }

    public void setGraph(FilterGraph filtergraph)
    {
        this;
        JVM INSTR monitorenter ;
        if(isRunning())
        {
            filtergraph = JVM INSTR new #79  <Class RuntimeException>;
            filtergraph.RuntimeException("Graph is already running!");
            throw filtergraph;
        }
        break MISSING_BLOCK_LABEL_26;
        filtergraph;
        this;
        JVM INSTR monitorexit ;
        throw filtergraph;
        SyncRunner syncrunner = JVM INSTR new #92  <Class SyncRunner>;
        syncrunner.SyncRunner(mFilterContext, filtergraph, mSchedulerClass);
        mRunner = syncrunner;
        this;
        JVM INSTR monitorexit ;
    }

    public void stop()
    {
        this;
        JVM INSTR monitorenter ;
        if(mRunTask != null && mRunTask.isCancelled() ^ true)
        {
            if(mLogVerbose)
                Log.v("AsyncRunner", "Stopping graph.");
            mRunTask.cancel(false);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static final String TAG = "AsyncRunner";
    private boolean isProcessing;
    private GraphRunner.OnRunnerDoneListener mDoneListener;
    private Exception mException;
    private boolean mLogVerbose;
    private AsyncRunnerTask mRunTask;
    private SyncRunner mRunner;
    private Class mSchedulerClass;
}

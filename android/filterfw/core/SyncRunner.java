// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import android.os.ConditionVariable;
import android.util.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// Referenced classes of package android.filterfw.core:
//            GraphRunner, Scheduler, FilterGraph, FilterContext, 
//            StopWatchMap, Filter

public class SyncRunner extends GraphRunner
{

    public SyncRunner(FilterContext filtercontext, FilterGraph filtergraph, Class class1)
    {
        super(filtercontext);
        mScheduler = null;
        mDoneListener = null;
        mWakeExecutor = new ScheduledThreadPoolExecutor(1);
        mWakeCondition = new ConditionVariable();
        mTimer = null;
        if(mLogVerbose)
            Log.v("SyncRunner", "Initializing SyncRunner");
        if(android/filterfw/core/Scheduler.isAssignableFrom(class1))
        {
            try
            {
                mScheduler = (Scheduler)class1.getConstructor(new Class[] {
                    android/filterfw/core/FilterGraph
                }).newInstance(new Object[] {
                    filtergraph
                });
            }
            // Misplaced declaration of an exception variable
            catch(FilterContext filtercontext)
            {
                throw new RuntimeException("Scheduler does not have constructor <init>(FilterGraph)!", filtercontext);
            }
            // Misplaced declaration of an exception variable
            catch(FilterContext filtercontext)
            {
                throw new RuntimeException("Could not instantiate the Scheduler instance!", filtercontext);
            }
            // Misplaced declaration of an exception variable
            catch(FilterContext filtercontext)
            {
                throw new RuntimeException("Cannot access Scheduler constructor!", filtercontext);
            }
            // Misplaced declaration of an exception variable
            catch(FilterContext filtercontext)
            {
                throw new RuntimeException("Scheduler constructor threw an exception", filtercontext);
            }
            // Misplaced declaration of an exception variable
            catch(FilterContext filtercontext)
            {
                throw new RuntimeException("Could not instantiate Scheduler", filtercontext);
            }
            mFilterContext = filtercontext;
            mFilterContext.addGraph(filtergraph);
            mTimer = new StopWatchMap();
            if(mLogVerbose)
                Log.v("SyncRunner", "Setting up filters");
            filtergraph.setupFilters();
            return;
        } else
        {
            throw new IllegalArgumentException("Class provided is not a Scheduler subclass!");
        }
    }

    void assertReadyToStep()
    {
        if(mScheduler == null)
            throw new RuntimeException("Attempting to run schedule with no scheduler in place!");
        if(getGraph() == null)
            throw new RuntimeException("Calling step on scheduler with no graph in place!");
        else
            return;
    }

    public void beginProcessing()
    {
        mScheduler.reset();
        getGraph().beginProcessing();
    }

    public void close()
    {
        if(mLogVerbose)
            Log.v("SyncRunner", "Closing graph.");
        getGraph().closeFilters(mFilterContext);
        mScheduler.reset();
    }

    protected int determinePostRunState()
    {
        for(Iterator iterator = mScheduler.getGraph().getFilters().iterator(); iterator.hasNext();)
        {
            Filter filter = (Filter)iterator.next();
            if(filter.isOpen())
                return filter.getStatus() != 4 ? 4 : 3;
        }

        return 2;
    }

    public Exception getError()
    {
        this;
        JVM INSTR monitorenter ;
        return null;
    }

    public FilterGraph getGraph()
    {
        FilterGraph filtergraph = null;
        if(mScheduler != null)
            filtergraph = mScheduler.getGraph();
        return filtergraph;
    }

    public boolean isRunning()
    {
        return false;
    }

    boolean performStep()
    {
        if(mLogVerbose)
            Log.v("SyncRunner", "Performing one step.");
        Filter filter = mScheduler.scheduleNextNode();
        if(filter != null)
        {
            mTimer.start(filter.getName());
            processFilterNode(filter);
            mTimer.stop(filter.getName());
            return true;
        } else
        {
            return false;
        }
    }

    protected void processFilterNode(Filter filter)
    {
        if(mLogVerbose)
            Log.v("SyncRunner", "Processing filter node");
        filter.performProcess(mFilterContext);
        if(filter.getStatus() == 6)
            throw new RuntimeException((new StringBuilder()).append("There was an error executing ").append(filter).append("!").toString());
        if(filter.getStatus() == 4)
        {
            if(mLogVerbose)
                Log.v("SyncRunner", "Scheduling filter wakeup");
            scheduleFilterWake(filter, filter.getSleepDelay());
        }
    }

    public void run()
    {
        if(mLogVerbose)
            Log.v("SyncRunner", "Beginning run.");
        assertReadyToStep();
        beginProcessing();
        boolean flag = activateGlContext();
        for(boolean flag1 = true; flag1; flag1 = performStep());
        if(flag)
            deactivateGlContext();
        if(mDoneListener != null)
        {
            if(mLogVerbose)
                Log.v("SyncRunner", "Calling completion listener.");
            mDoneListener.onRunnerDone(determinePostRunState());
        }
        if(mLogVerbose)
            Log.v("SyncRunner", "Run complete");
    }

    protected void scheduleFilterWake(final Filter filterToSchedule, int i)
    {
        mWakeCondition.close();
        final ConditionVariable conditionToWake = mWakeCondition;
        mWakeExecutor.schedule(new Runnable() {

            public void run()
            {
                filterToSchedule.unsetStatus(4);
                conditionToWake.open();
            }

            final SyncRunner this$0;
            final ConditionVariable val$conditionToWake;
            final Filter val$filterToSchedule;

            
            {
                this$0 = SyncRunner.this;
                filterToSchedule = filter;
                conditionToWake = conditionvariable;
                super();
            }
        }
, i, TimeUnit.MILLISECONDS);
    }

    public void setDoneCallback(GraphRunner.OnRunnerDoneListener onrunnerdonelistener)
    {
        mDoneListener = onrunnerdonelistener;
    }

    public int step()
    {
        assertReadyToStep();
        if(!getGraph().isReady())
            throw new RuntimeException("Trying to process graph that is not open!");
        int i;
        if(performStep())
            i = 1;
        else
            i = determinePostRunState();
        return i;
    }

    public void stop()
    {
        throw new RuntimeException("SyncRunner does not support stopping a graph!");
    }

    protected void waitUntilWake()
    {
        mWakeCondition.block();
    }

    private static final String TAG = "SyncRunner";
    private GraphRunner.OnRunnerDoneListener mDoneListener;
    private final boolean mLogVerbose = Log.isLoggable("SyncRunner", 2);
    private Scheduler mScheduler;
    private StopWatchMap mTimer;
    private ConditionVariable mWakeCondition;
    private ScheduledThreadPoolExecutor mWakeExecutor;
}

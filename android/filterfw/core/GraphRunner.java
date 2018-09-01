// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;


// Referenced classes of package android.filterfw.core:
//            FilterContext, GLEnvironment, FilterGraph

public abstract class GraphRunner
{
    public static interface OnRunnerDoneListener
    {

        public abstract void onRunnerDone(int i);
    }


    public GraphRunner(FilterContext filtercontext)
    {
        mFilterContext = null;
        mFilterContext = filtercontext;
    }

    protected boolean activateGlContext()
    {
        GLEnvironment glenvironment = mFilterContext.getGLEnvironment();
        if(glenvironment != null && glenvironment.isActive() ^ true)
        {
            glenvironment.activate();
            return true;
        } else
        {
            return false;
        }
    }

    public abstract void close();

    protected void deactivateGlContext()
    {
        GLEnvironment glenvironment = mFilterContext.getGLEnvironment();
        if(glenvironment != null)
            glenvironment.deactivate();
    }

    public FilterContext getContext()
    {
        return mFilterContext;
    }

    public abstract Exception getError();

    public abstract FilterGraph getGraph();

    public abstract boolean isRunning();

    public abstract void run();

    public abstract void setDoneCallback(OnRunnerDoneListener onrunnerdonelistener);

    public abstract void stop();

    public static final int RESULT_BLOCKED = 4;
    public static final int RESULT_ERROR = 6;
    public static final int RESULT_FINISHED = 2;
    public static final int RESULT_RUNNING = 1;
    public static final int RESULT_SLEEPING = 3;
    public static final int RESULT_STOPPED = 5;
    public static final int RESULT_UNKNOWN = 0;
    protected FilterContext mFilterContext;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw;

import android.content.Context;
import android.filterfw.core.AsyncRunner;
import android.filterfw.core.FilterContext;
import android.filterfw.core.FilterGraph;
import android.filterfw.core.FrameManager;
import android.filterfw.core.GraphRunner;
import android.filterfw.core.RoundRobinScheduler;
import android.filterfw.core.SyncRunner;
import android.filterfw.io.GraphIOException;
import android.filterfw.io.GraphReader;
import android.filterfw.io.TextGraphReader;
import java.util.ArrayList;

// Referenced classes of package android.filterfw:
//            MffEnvironment

public class GraphEnvironment extends MffEnvironment
{
    private class GraphHandle
    {

        public AsyncRunner getAsyncRunner(FilterContext filtercontext)
        {
            if(mAsyncRunner == null)
            {
                mAsyncRunner = new AsyncRunner(filtercontext, android/filterfw/core/RoundRobinScheduler);
                mAsyncRunner.setGraph(mGraph);
            }
            return mAsyncRunner;
        }

        public FilterGraph getGraph()
        {
            return mGraph;
        }

        public GraphRunner getSyncRunner(FilterContext filtercontext)
        {
            if(mSyncRunner == null)
                mSyncRunner = new SyncRunner(filtercontext, mGraph, android/filterfw/core/RoundRobinScheduler);
            return mSyncRunner;
        }

        private AsyncRunner mAsyncRunner;
        private FilterGraph mGraph;
        private SyncRunner mSyncRunner;
        final GraphEnvironment this$0;

        public GraphHandle(FilterGraph filtergraph)
        {
            this$0 = GraphEnvironment.this;
            super();
            mGraph = filtergraph;
        }
    }


    public GraphEnvironment()
    {
        super(null);
        mGraphs = new ArrayList();
    }

    public GraphEnvironment(FrameManager framemanager, GraphReader graphreader)
    {
        super(framemanager);
        mGraphs = new ArrayList();
        mGraphReader = graphreader;
    }

    public int addGraph(FilterGraph filtergraph)
    {
        filtergraph = new GraphHandle(filtergraph);
        mGraphs.add(filtergraph);
        return mGraphs.size() - 1;
    }

    public transient void addReferences(Object aobj[])
    {
        getGraphReader().addReferencesByKeysAndValues(aobj);
    }

    public FilterGraph getGraph(int i)
    {
        if(i < 0 || i >= mGraphs.size())
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid graph ID ").append(i).append(" specified in runGraph()!").toString());
        else
            return ((GraphHandle)mGraphs.get(i)).getGraph();
    }

    public GraphReader getGraphReader()
    {
        if(mGraphReader == null)
            mGraphReader = new TextGraphReader();
        return mGraphReader;
    }

    public GraphRunner getRunner(int i, int j)
    {
        switch(j)
        {
        default:
            throw new RuntimeException((new StringBuilder()).append("Invalid execution mode ").append(j).append(" specified in getRunner()!").toString());

        case 1: // '\001'
            return ((GraphHandle)mGraphs.get(i)).getAsyncRunner(getContext());

        case 2: // '\002'
            return ((GraphHandle)mGraphs.get(i)).getSyncRunner(getContext());
        }
    }

    public int loadGraph(Context context, int i)
    {
        try
        {
            context = getGraphReader().readGraphResource(context, i);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new RuntimeException((new StringBuilder()).append("Could not read graph: ").append(context.getMessage()).toString());
        }
        return addGraph(context);
    }

    public static final int MODE_ASYNCHRONOUS = 1;
    public static final int MODE_SYNCHRONOUS = 2;
    private GraphReader mGraphReader;
    private ArrayList mGraphs;
}

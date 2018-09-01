// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;


// Referenced classes of package android.filterfw.core:
//            FilterGraph, Filter

public abstract class Scheduler
{

    Scheduler(FilterGraph filtergraph)
    {
        mGraph = filtergraph;
    }

    boolean finished()
    {
        return true;
    }

    FilterGraph getGraph()
    {
        return mGraph;
    }

    abstract void reset();

    abstract Filter scheduleNextNode();

    private FilterGraph mGraph;
}

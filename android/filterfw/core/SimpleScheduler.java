// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import java.util.Iterator;

// Referenced classes of package android.filterfw.core:
//            Scheduler, FilterGraph, Filter

public class SimpleScheduler extends Scheduler
{

    public SimpleScheduler(FilterGraph filtergraph)
    {
        super(filtergraph);
    }

    public void reset()
    {
    }

    public Filter scheduleNextNode()
    {
        for(Iterator iterator = getGraph().getFilters().iterator(); iterator.hasNext();)
        {
            Filter filter = (Filter)iterator.next();
            if(filter.canProcess())
                return filter;
        }

        return null;
    }
}

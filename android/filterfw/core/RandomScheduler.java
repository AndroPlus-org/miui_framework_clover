// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import java.util.*;

// Referenced classes of package android.filterfw.core:
//            Scheduler, FilterGraph, Filter

public class RandomScheduler extends Scheduler
{

    public RandomScheduler(FilterGraph filtergraph)
    {
        super(filtergraph);
        mRand = new Random();
    }

    public void reset()
    {
    }

    public Filter scheduleNextNode()
    {
        Vector vector = new Vector();
        Iterator iterator = getGraph().getFilters().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Filter filter = (Filter)iterator.next();
            if(filter.canProcess())
                vector.add(filter);
        } while(true);
        if(vector.size() > 0)
            return (Filter)vector.elementAt(mRand.nextInt(vector.size()));
        else
            return null;
    }

    private Random mRand;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import java.util.Iterator;
import java.util.Set;

// Referenced classes of package android.filterfw.core:
//            Scheduler, FilterGraph, Filter

public class RoundRobinScheduler extends Scheduler
{

    public RoundRobinScheduler(FilterGraph filtergraph)
    {
        super(filtergraph);
        mLastPos = -1;
    }

    public void reset()
    {
        mLastPos = -1;
    }

    public Filter scheduleNextNode()
    {
        Filter filter1;
        int j;
label0:
        {
            Set set = getGraph().getFilters();
            if(mLastPos >= set.size())
                mLastPos = -1;
            int i = 0;
            filter1 = null;
            j = -1;
            Iterator iterator = set.iterator();
            Filter filter2;
            do
            {
                if(!iterator.hasNext())
                    break label0;
                filter2 = (Filter)iterator.next();
                Filter filter = filter1;
                int k = j;
                if(filter2.canProcess())
                {
                    if(i > mLastPos)
                        break;
                    filter = filter1;
                    k = j;
                    if(filter1 == null)
                    {
                        filter = filter2;
                        k = i;
                    }
                }
                i++;
                filter1 = filter;
                j = k;
            } while(true);
            mLastPos = i;
            return filter2;
        }
        if(filter1 != null)
        {
            mLastPos = j;
            return filter1;
        } else
        {
            return null;
        }
    }

    private int mLastPos;
}

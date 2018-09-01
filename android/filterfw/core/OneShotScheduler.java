// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import android.util.Log;
import java.util.HashMap;

// Referenced classes of package android.filterfw.core:
//            RoundRobinScheduler, Filter, FilterGraph

public class OneShotScheduler extends RoundRobinScheduler
{

    public OneShotScheduler(FilterGraph filtergraph)
    {
        super(filtergraph);
        scheduled = new HashMap();
    }

    public void reset()
    {
        super.reset();
        scheduled.clear();
    }

    public Filter scheduleNextNode()
    {
        Filter filter = null;
        do
        {
            Filter filter1;
            do
            {
                filter1 = super.scheduleNextNode();
                if(filter1 == null)
                {
                    if(mLogVerbose)
                        Log.v("OneShotScheduler", "No filters available to run.");
                    return null;
                }
                if(!scheduled.containsKey(filter1.getName()))
                {
                    if(filter1.getNumberOfConnectedInputs() == 0)
                        scheduled.put(filter1.getName(), Integer.valueOf(1));
                    if(mLogVerbose)
                        Log.v("OneShotScheduler", (new StringBuilder()).append("Scheduling filter \"").append(filter1.getName()).append("\" of type ").append(filter1.getFilterClassName()).toString());
                    return filter1;
                }
                if(filter == filter1)
                {
                    if(mLogVerbose)
                        Log.v("OneShotScheduler", "One pass through graph completed.");
                    return null;
                }
            } while(filter != null);
            filter = filter1;
        } while(true);
    }

    private static final String TAG = "OneShotScheduler";
    private final boolean mLogVerbose = Log.isLoggable("OneShotScheduler", 2);
    private HashMap scheduled;
}

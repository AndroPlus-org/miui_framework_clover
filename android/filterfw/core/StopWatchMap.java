// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import java.util.HashMap;

// Referenced classes of package android.filterfw.core:
//            StopWatch

public class StopWatchMap
{

    public StopWatchMap()
    {
        LOG_MFF_RUNNING_TIMES = false;
        mStopWatches = null;
        mStopWatches = new HashMap();
    }

    public void start(String s)
    {
        if(!LOG_MFF_RUNNING_TIMES)
            return;
        if(!mStopWatches.containsKey(s))
            mStopWatches.put(s, new StopWatch(s));
        ((StopWatch)mStopWatches.get(s)).start();
    }

    public void stop(String s)
    {
        if(!LOG_MFF_RUNNING_TIMES)
            return;
        if(!mStopWatches.containsKey(s))
        {
            throw new RuntimeException((new StringBuilder()).append("Calling stop with unknown stopWatchName: ").append(s).toString());
        } else
        {
            ((StopWatch)mStopWatches.get(s)).stop();
            return;
        }
    }

    public boolean LOG_MFF_RUNNING_TIMES;
    private HashMap mStopWatches;
}

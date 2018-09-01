// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.metrics;

import android.os.SystemClock;

// Referenced classes of package android.net.metrics:
//            WakeupEvent

public class WakeupStats
{

    public WakeupStats(String s)
    {
        totalWakeups = 0L;
        rootWakeups = 0L;
        systemWakeups = 0L;
        nonApplicationWakeups = 0L;
        applicationWakeups = 0L;
        noUidWakeups = 0L;
        durationSec = 0L;
        iface = s;
    }

    public void countEvent(WakeupEvent wakeupevent)
    {
        totalWakeups = totalWakeups + 1L;
        wakeupevent.uid;
        JVM INSTR lookupswitch 3: default 48
    //                   -1: 95
    //                   0: 69
    //                   1000: 82;
           goto _L1 _L2 _L3 _L4
_L1:
        if(wakeupevent.uid >= 10000)
            applicationWakeups = applicationWakeups + 1L;
        else
            nonApplicationWakeups = nonApplicationWakeups + 1L;
_L6:
        return;
_L3:
        rootWakeups = rootWakeups + 1L;
        continue; /* Loop/switch isn't completed */
_L4:
        systemWakeups = systemWakeups + 1L;
        continue; /* Loop/switch isn't completed */
_L2:
        noUidWakeups = noUidWakeups + 1L;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public String toString()
    {
        updateDuration();
        return (new StringBuilder()).append("WakeupStats(").append(iface).append(", total: ").append(totalWakeups).append(", root: ").append(rootWakeups).append(", system: ").append(systemWakeups).append(", apps: ").append(applicationWakeups).append(", non-apps: ").append(nonApplicationWakeups).append(", no uid: ").append(noUidWakeups).append(", ").append(durationSec).append("s)").toString();
    }

    public void updateDuration()
    {
        durationSec = (SystemClock.elapsedRealtime() - creationTimeMs) / 1000L;
    }

    private static final int NO_UID = -1;
    public long applicationWakeups;
    public final long creationTimeMs = SystemClock.elapsedRealtime();
    public long durationSec;
    public final String iface;
    public long noUidWakeups;
    public long nonApplicationWakeups;
    public long rootWakeups;
    public long systemWakeups;
    public long totalWakeups;
}

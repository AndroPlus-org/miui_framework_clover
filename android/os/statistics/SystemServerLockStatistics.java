// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.os.AnrMonitor;
import android.os.SystemClock;

// Referenced classes of package android.os.statistics:
//            FastPerfEventList, MicroscopicEvent, PerfEvent, PerfEventFilter

class SystemServerLockStatistics
{

    SystemServerLockStatistics()
    {
    }

    public static void report(FastPerfEventList fastperfeventlist, long l)
    {
        android/os/statistics/SystemServerLockStatistics;
        JVM INSTR monitorenter ;
        int i = fastperfeventlist.size;
        int j = 0;
_L2:
        if(j >= i)
            break MISSING_BLOCK_LABEL_106;
        Object obj = fastperfeventlist.events[j];
        if(obj != null)
            break; /* Loop/switch isn't completed */
_L5:
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        if(!(obj instanceof MonitorSuperviser.SingleLockWait)) goto _L4; else goto _L3
_L3:
        obj = (MonitorSuperviser.SingleLockWait)obj;
        if(((MonitorSuperviser.SingleLockWait) (obj)).endUptimeMillis - ((MonitorSuperviser.SingleLockWait) (obj)).beginUptimeMillis >= AnrMonitor.LOCK_WAIT_THRESHOLD)
            tempLockWaits.add(((PerfEvent) (obj)));
          goto _L5
        fastperfeventlist;
        throw fastperfeventlist;
_L4:
        if(!(obj instanceof MonitorSuperviser.SingleLockHold)) goto _L5; else goto _L6
_L6:
        tempLockHolds.add(((PerfEvent) (obj)));
          goto _L5
        reportLocks(tempLockWaits, tempLockHolds, l);
        tempLockWaits.clear();
        tempLockWaits.trimTo(32);
        tempLockHolds.clear();
        tempLockHolds.trimTo(32);
        android/os/statistics/SystemServerLockStatistics;
        JVM INSTR monitorexit ;
    }

    private static void reportLocks(FastPerfEventList fastperfeventlist, FastPerfEventList fastperfeventlist1, long l)
    {
        android/os/statistics/SystemServerLockStatistics;
        JVM INSTR monitorenter ;
        if(filter == null)
        {
            PerfEventFilter perfeventfilter = JVM INSTR new #86  <Class PerfEventFilter>;
            perfeventfilter.PerfEventFilter(true);
            filter = perfeventfilter;
        }
        filter.filter(fastperfeventlist, fastperfeventlist1, l, tempCompletedEvents);
        int i = 0;
_L2:
        if(i >= tempCompletedEvents.size)
            break; /* Loop/switch isn't completed */
        fastperfeventlist = tempCompletedEvents.events[i];
        if(fastperfeventlist instanceof MonitorSuperviser.SingleLockHold)
            lockHolds.add((MonitorSuperviser.SingleLockHold)fastperfeventlist);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        i = 0;
_L4:
        if(i >= tempCompletedEvents.size)
            break MISSING_BLOCK_LABEL_305;
        fastperfeventlist = tempCompletedEvents.events[i];
        if(fastperfeventlist instanceof MonitorSuperviser.SingleLockWait)
            break; /* Loop/switch isn't completed */
_L7:
        i++;
        if(true) goto _L4; else goto _L3
_L3:
        MonitorSuperviser.SingleLockWait singlelockwait;
        singlelockwait = (MonitorSuperviser.SingleLockWait)fastperfeventlist;
        singlelockwait.resolveLazyInfo();
        int j;
        long l1;
        int k;
        j = 0;
        l1 = 0L;
        l = 0L;
        fastperfeventlist = null;
        k = 0;
_L6:
        MonitorSuperviser.SingleLockHold singlelockhold;
        if(k >= lockHolds.size)
            break; /* Loop/switch isn't completed */
        singlelockhold = (MonitorSuperviser.SingleLockHold)lockHolds.get(k);
        int i1;
        long l2;
        long l3;
        i1 = j;
        l2 = l1;
        l3 = l;
        fastperfeventlist1 = fastperfeventlist;
        if(!singlelockwait.isBlockedBy(singlelockhold))
            break MISSING_BLOCK_LABEL_258;
        j++;
        long l4 = singlelockhold.endUptimeMillis - Math.max(singlelockhold.beginUptimeMillis, singlelockwait.beginUptimeMillis);
        l1 += l4;
        i1 = j;
        l2 = l1;
        l3 = l;
        fastperfeventlist1 = fastperfeventlist;
        if(l4 > l)
        {
            l3 = l4;
            fastperfeventlist1 = singlelockhold;
            l2 = l1;
            i1 = j;
        }
        k++;
        j = i1;
        l1 = l2;
        l = l3;
        fastperfeventlist = fastperfeventlist1;
        if(true) goto _L6; else goto _L5
_L5:
        if(fastperfeventlist == null)
            break MISSING_BLOCK_LABEL_285;
        fastperfeventlist.resolveLazyInfo();
        AnrMonitor.checkLockWaitTime(singlelockwait, j, l1, l, fastperfeventlist);
          goto _L7
        fastperfeventlist;
        throw fastperfeventlist;
        tempCompletedEvents.clear();
        tempCompletedEvents.trimTo(32);
        l = SystemClock.uptimeMillis();
        i = lockHolds.size - 1;
_L10:
        if(i < 0) goto _L9; else goto _L8
_L8:
        if(((MonitorSuperviser.SingleLockHold)lockHolds.get(i)).endUptimeMillis < l - 60000L)
            ((MonitorSuperviser.SingleLockHold[])lockHolds.events)[i] = null;
        i--;
          goto _L10
_L9:
        lockHolds.compact();
        lockHolds.trimTo(32);
        android/os/statistics/SystemServerLockStatistics;
        JVM INSTR monitorexit ;
    }

    private static final int DEFAULT_CAPACITY = 32;
    private static volatile PerfEventFilter filter;
    private static final FastPerfEventList lockHolds = new FastPerfEventList(android/os/statistics/MonitorSuperviser$SingleLockHold, 32);
    private static final FastPerfEventList tempCompletedEvents = new FastPerfEventList(android/os/statistics/PerfEvent, 32);
    private static final FastPerfEventList tempLockHolds = new FastPerfEventList(android/os/statistics/PerfEvent, 32);
    private static final FastPerfEventList tempLockWaits = new FastPerfEventList(android/os/statistics/MicroscopicEvent, 32);

}

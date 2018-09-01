// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.os.SystemClock;

// Referenced classes of package android.os.statistics:
//            FastPerfEventList, MicroscopicEvent, MacroscopicEvent, NativeBackTrace, 
//            PerfEvent, PerfSupervisionSettings

public class PerfEventFilter
{

    public PerfEventFilter(boolean flag)
    {
        lastBatchBeginTimeMillis = 0L;
        nativeBacktraceMapUpdated = false;
        isAppSideFilter = flag;
    }

    private void addCompletedEvent(MicroscopicEvent microscopicevent)
    {
        boolean flag;
        MicroscopicEvent amicroscopicevent[];
        int i;
        if(isAppSideFilter)
            microscopicevent.resolveLazyInfo();
        flag = false;
        amicroscopicevent = (MicroscopicEvent[])tempCompletedMicroEvents.events;
        i = tempCompletedMicroEvents.size - 1;
_L5:
        int j = ((flag) ? 1 : 0);
        if(i < 0) goto _L2; else goto _L1
_L1:
        MicroscopicEvent microscopicevent1 = amicroscopicevent[i];
        if(microscopicevent1.endUptimeMillis <= microscopicevent.endUptimeMillis) goto _L4; else goto _L3
_L3:
        i--;
          goto _L5
_L4:
        if(microscopicevent1.endUptimeMillis < microscopicevent.endUptimeMillis)
        {
            j = i + 1;
        } else
        {
            if(Boolean.compare(microscopicevent1.isRootEvent(), microscopicevent.isRootEvent()) > 0)
                continue; /* Loop/switch isn't completed */
            j = i + 1;
        }
_L2:
        tempCompletedMicroEvents.add(j, microscopicevent);
        return;
        if(Boolean.compare(microscopicevent1.isMasterEvent(), microscopicevent.isMasterEvent()) > 0) goto _L3; else goto _L6
_L6:
        j = i + 1;
          goto _L2
    }

    private boolean checkEffectivePerfEvent(MicroscopicEvent microscopicevent)
    {
        int i;
        if(isAppSideFilter)
        {
            if(microscopicevent.hasNativeStack() && nativeBacktraceMapUpdated ^ true)
            {
                NativeBackTrace.updateBacktraceMap();
                nativeBacktraceMapUpdated = true;
            }
            microscopicevent.resolveLazyInfo();
            if(!microscopicevent.isMeaningful())
                return false;
        }
        if(microscopicevent.hasPeerBlockingEvent())
        {
            if(!isAppSideFilter || microscopicevent.isBlockedBySameProcess())
                eventsWaitingBlockingPeer.add(microscopicevent);
            return true;
        }
        i = 0;
_L5:
        if(i >= suspectedPerfEvents.size) goto _L2; else goto _L1
_L1:
        MicroscopicEvent microscopicevent1 = ((MicroscopicEvent[])suspectedPerfEvents.events)[i];
        if(microscopicevent1 != null) goto _L4; else goto _L3
_L3:
        i++;
          goto _L5
_L4:
        if(microscopicevent1.beginUptimeMillis < microscopicevent.endUptimeMillis)
            break MISSING_BLOCK_LABEL_126;
_L2:
        return true;
        if(microscopicevent1.endUptimeMillis > microscopicevent.beginUptimeMillis)
        {
            boolean flag;
            if(!microscopicevent1.isPeerBlockingEvent())
                flag = isIncludedEnough(microscopicevent, microscopicevent1);
            else
                flag = false;
            if(flag)
            {
                ((MicroscopicEvent[])suspectedPerfEvents.events)[i] = null;
                if((microscopicevent1.eventFlags & 3) == 0)
                    microscopicevent1.eventFlags = microscopicevent1.eventFlags | microscopicevent.eventFlags & 3;
                effectivePerfEvents.add(microscopicevent1);
            }
        }
          goto _L3
    }

    private void checkEffectivePerfEvents(int i, int j)
    {
        while(i < j) 
        {
            MicroscopicEvent microscopicevent = ((MicroscopicEvent[])effectivePerfEvents.events)[i];
            if(microscopicevent == null)
            {
                i++;
            } else
            {
                boolean flag = checkEffectivePerfEvent(microscopicevent);
                ((MicroscopicEvent[])effectivePerfEvents.events)[i] = null;
                if(flag)
                    addCompletedEvent(microscopicevent);
                i++;
            }
        }
    }

    private boolean checkEventWaitingBlockingPeer(MicroscopicEvent microscopicevent, long l)
    {
        long l1;
        long l2;
        int i;
        long l3;
        long l4;
        MicroscopicEvent amicroscopicevent[];
        int j;
        l1 = microscopicevent.matchedPeerBlockingDuration;
        l2 = 0L;
        i = suspectedPerfEvents.size;
        l3 = microscopicevent.beginUptimeMillis;
        l4 = microscopicevent.endUptimeMillis;
        amicroscopicevent = (MicroscopicEvent[])suspectedPerfEvents.events;
        j = 0;
_L5:
        if(j >= i) goto _L2; else goto _L1
_L1:
        MicroscopicEvent microscopicevent1 = amicroscopicevent[j];
        if(microscopicevent1 != null) goto _L4; else goto _L3
_L3:
        long l5;
        long l6;
        l5 = l1;
        l6 = l2;
_L6:
        j++;
        l2 = l6;
        l1 = l5;
          goto _L5
_L4:
        if(microscopicevent1.beginUptimeMillis < l4)
            break MISSING_BLOCK_LABEL_215;
_L2:
        microscopicevent.matchedPeerBlockingDuration = l1;
        l6 = l4 - l3;
        boolean flag;
        if(isAppSideFilter)
        {
            l5 = l6;
            if(l6 < 2000L)
                l5 = 2000L;
        } else
        {
            l3 = l6 << 1;
            l5 = l3;
            if(l3 < 10000L)
                l5 = 10000L;
        }
        l3 = (l6 >> 1) + (l6 >> 2) + (l6 >> 3) + (l6 >> 4);
        if(microscopicevent.hasMultiplePeerBlockingEvents())
        {
            if(l2 < (l6 >> 1) + (l6 >> 2) + (l6 >> 4))
            {
                if(l1 >= l3)
                    j = 1;
                else
                    j = 0;
            } else
            {
                j = 1;
            }
        } else
        if(l1 >= l3)
            j = 1;
        else
            j = 0;
        if(j != 0 || l4 + l5 < l)
            flag = true;
        else
            flag = false;
        return flag;
        l6 = l2;
        l5 = l1;
        if(microscopicevent1.endUptimeMillis > l3)
        {
            if(microscopicevent1.isPeerBlockingEvent() && microscopicevent.isBlockedBy(microscopicevent1))
                flag = isTimeIncludedEnough(microscopicevent, microscopicevent1);
            else
                flag = false;
            l6 = l2;
            l5 = l1;
            if(flag)
            {
                l6 = microscopicevent1.endUptimeMillis;
                if(microscopicevent1.beginUptimeMillis >= microscopicevent.beginUptimeMillis)
                    l5 = microscopicevent1.beginUptimeMillis;
                else
                    l5 = microscopicevent.beginUptimeMillis;
                l6 -= l5;
                if(microscopicevent.hasMultiplePeerBlockingEvents())
                {
                    l5 = l1 + l6;
                } else
                {
                    l5 = l1;
                    if(l1 < l6)
                        l5 = l6;
                }
                l1 = l2;
                if(l2 < l6)
                    l1 = l6;
                amicroscopicevent[j] = null;
                if((microscopicevent1.eventFlags & 3) == 0)
                    microscopicevent1.eventFlags = microscopicevent1.eventFlags | microscopicevent.eventFlags & 3;
                effectivePerfEvents.add(microscopicevent1);
                l6 = l1;
            }
        }
          goto _L6
    }

    private void checkEventsWaitingBlockingPeer(int i, int j, long l)
    {
        MicroscopicEvent amicroscopicevent[] = (MicroscopicEvent[])eventsWaitingBlockingPeer.events;
        while(i < j) 
        {
            MicroscopicEvent microscopicevent = amicroscopicevent[i];
            if(microscopicevent == null)
            {
                i++;
            } else
            {
                if(checkEventWaitingBlockingPeer(microscopicevent, l))
                    amicroscopicevent[i] = null;
                i++;
            }
        }
    }

    private void checkSuspectedPerfEvents(long l, long l1)
    {
        int i = 0;
        do
        {
            if(i < suspectedPerfEvents.size)
            {
                MicroscopicEvent microscopicevent = ((MicroscopicEvent[])suspectedPerfEvents.events)[i];
                if(microscopicevent != null)
                    if(microscopicevent.endUptimeMillis < l)
                    {
                        ((MicroscopicEvent[])suspectedPerfEvents.events)[i] = null;
                    } else
                    {
                        long l2 = microscopicevent.endUptimeMillis - microscopicevent.beginUptimeMillis;
                        if(isAppSideFilter)
                        {
                            if(10000L > l2 << 6)
                                l2 = 10000L;
                            else
                                l2 <<= 6;
                            l2 = l1 - l2;
                        } else
                        {
                            if(20000L > l2 << 7)
                                l2 = 20000L;
                            else
                                l2 <<= 7;
                            l2 = l1 - l2;
                        }
                        if(microscopicevent.endUptimeMillis < l2)
                            ((MicroscopicEvent[])suspectedPerfEvents.events)[i] = null;
                    }
            } else
            {
                return;
            }
            i++;
        } while(true);
    }

    private long getEarliestEventBeginUptimeMillisInBatch(FastPerfEventList fastperfeventlist)
    {
        long l = 0x7fffffffffffffffL;
        int i = fastperfeventlist.size;
        fastperfeventlist = fastperfeventlist.events;
        for(int j = 0; j < i;)
        {
            long l1 = fastperfeventlist[j].getBeginUptimeMillis();
            long l2 = l;
            if(l > l1)
                l2 = l1;
            j++;
            l = l2;
        }

        long l3 = l;
        if(l == 0x7fffffffffffffffL)
            l3 = 0L;
        return l3;
    }

    private boolean isIncludedEnough(MicroscopicEvent microscopicevent, MicroscopicEvent microscopicevent1)
    {
        if(microscopicevent.pid != microscopicevent1.pid || microscopicevent.threadId != microscopicevent1.threadId)
            return false;
        else
            return isTimeIncludedEnough(microscopicevent, microscopicevent1);
    }

    private boolean isTimeIncludedEnough(MicroscopicEvent microscopicevent, MicroscopicEvent microscopicevent1)
    {
        boolean flag;
        long l;
        long l1;
        long l4;
        boolean flag1;
        flag = true;
        l = microscopicevent.endUptimeMillis;
        l1 = microscopicevent.beginUptimeMillis;
        long l2 = microscopicevent1.endUptimeMillis;
        long l3 = microscopicevent1.beginUptimeMillis;
        if(microscopicevent.beginUptimeMillis > microscopicevent1.beginUptimeMillis)
        {
            if(microscopicevent.beginUptimeMillis < microscopicevent1.endUptimeMillis && microscopicevent.endUptimeMillis >= microscopicevent1.endUptimeMillis)
                l4 = microscopicevent1.endUptimeMillis - microscopicevent.beginUptimeMillis;
            else
                l4 = 0L;
        } else
        if(microscopicevent.beginUptimeMillis == microscopicevent1.beginUptimeMillis)
        {
            if(microscopicevent.endUptimeMillis >= microscopicevent1.endUptimeMillis)
                l4 = microscopicevent1.endUptimeMillis - microscopicevent1.beginUptimeMillis;
            else
                l4 = 0L;
        } else
        if(microscopicevent.endUptimeMillis > microscopicevent1.beginUptimeMillis)
            l4 = Math.min(microscopicevent.endUptimeMillis, microscopicevent1.endUptimeMillis) - microscopicevent1.beginUptimeMillis;
        else
            l4 = 0L;
        flag1 = flag;
        if(l4 == l2 - l3) goto _L2; else goto _L1
_L1:
        if((long)PerfSupervisionSettings.sPerfSupervisionDivisionRatio * l4 < (long)PerfSupervisionSettings.sPerfSupervisionSoftThreshold) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(5L * l4 < l - l1)
            flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    private void mergeSuspectedEvents(FastPerfEventList fastperfeventlist)
    {
        int i;
        PerfEvent aperfevent[];
        int j;
        int k;
        i = fastperfeventlist.size;
        aperfevent = fastperfeventlist.events;
        if(i == 0)
            return;
        j = suspectedPerfEvents.size;
        k = i + j;
        if(k > ((MicroscopicEvent[])suspectedPerfEvents.events).length)
            suspectedPerfEvents.ensureCapacity(k);
        fastperfeventlist = (MicroscopicEvent[])suspectedPerfEvents.events;
        k = 0;
_L2:
        Object obj;
        if(k >= i)
            break MISSING_BLOCK_LABEL_189;
        obj = aperfevent[k];
        if(!(obj instanceof MacroscopicEvent))
            break; /* Loop/switch isn't completed */
        if(isAppSideFilter)
            ((PerfEvent) (obj)).resolveLazyInfo();
        tempCompletedMacroEvents.add((MacroscopicEvent)obj);
_L3:
        k++;
        if(true) goto _L2; else goto _L1
_L1:
        boolean flag;
        int l;
        obj = (MicroscopicEvent)obj;
        flag = false;
        l = j - 1;
_L4:
label0:
        {
            int i1 = ((flag) ? 1 : 0);
            if(l >= 0)
            {
                if(((MicroscopicEvent) (fastperfeventlist[l])).beginUptimeMillis > ((MicroscopicEvent) (obj)).beginUptimeMillis)
                    break label0;
                i1 = l + 1;
            }
            suspectedPerfEvents.add(i1, ((PerfEvent) (obj)));
            j++;
        }
          goto _L3
        l--;
          goto _L4
          goto _L3
    }

    public void filter(FastPerfEventList fastperfeventlist, FastPerfEventList fastperfeventlist1, long l, FastPerfEventList fastperfeventlist2)
    {
        nativeBacktraceMapUpdated = false;
        long l1 = Math.min(getEarliestEventBeginUptimeMillisInBatch(fastperfeventlist), getEarliestEventBeginUptimeMillisInBatch(fastperfeventlist1));
        long l2 = l1;
        if(l1 == 0L)
            l2 = SystemClock.uptimeMillis();
        l2 = Math.max(lastBatchBeginTimeMillis, l2);
        lastBatchBeginTimeMillis = l2;
        if(fastperfeventlist.size > 0)
            effectivePerfEvents.addAll(fastperfeventlist);
        mergeSuspectedEvents(fastperfeventlist1);
        int i = 0;
        int j = 0;
        int k = eventsWaitingBlockingPeer.size;
        int j1;
        for(int i1 = effectivePerfEvents.size; k != j || i1 != i; k = j1)
        {
            checkEventsWaitingBlockingPeer(j, k, l2);
            checkEffectivePerfEvents(i, i1);
            j1 = eventsWaitingBlockingPeer.size;
            int k1 = effectivePerfEvents.size;
            i = i1;
            j = k;
            i1 = k1;
        }

        checkSuspectedPerfEvents(l, l2);
        fastperfeventlist2.ensureCapacity(tempCompletedMacroEvents.size + tempCompletedMicroEvents.size);
        fastperfeventlist2.addAll(tempCompletedMacroEvents);
        fastperfeventlist2.addAll(tempCompletedMicroEvents);
        effectivePerfEvents.compact();
        effectivePerfEvents.trimTo(64);
        eventsWaitingBlockingPeer.compact();
        eventsWaitingBlockingPeer.trimTo(64);
        suspectedPerfEvents.compact();
        suspectedPerfEvents.trimTo(128);
        tempCompletedMacroEvents.clear();
        tempCompletedMacroEvents.trimTo(64);
        tempCompletedMicroEvents.clear();
        tempCompletedMicroEvents.trimTo(64);
    }

    public boolean hasPendingPerfEvents()
    {
        boolean flag;
        if(effectivePerfEvents.isEmpty() && !(eventsWaitingBlockingPeer.isEmpty() ^ true))
            flag = suspectedPerfEvents.isEmpty() ^ true;
        else
            flag = true;
        return flag;
    }

    private static final int DEFAULT_CAPACITY = 64;
    private static final int DEFAULT_SUSPECTED_CAPACITY = 128;
    private final FastPerfEventList effectivePerfEvents = new FastPerfEventList(android/os/statistics/MicroscopicEvent, 64);
    private final FastPerfEventList eventsWaitingBlockingPeer = new FastPerfEventList(android/os/statistics/MicroscopicEvent, 64);
    private final boolean isAppSideFilter;
    private long lastBatchBeginTimeMillis;
    private boolean nativeBacktraceMapUpdated;
    private final FastPerfEventList suspectedPerfEvents = new FastPerfEventList(android/os/statistics/MicroscopicEvent, 128);
    private final FastPerfEventList tempCompletedMacroEvents = new FastPerfEventList(android/os/statistics/MacroscopicEvent, 64);
    private final FastPerfEventList tempCompletedMicroEvents = new FastPerfEventList(android/os/statistics/MicroscopicEvent, 64);
}

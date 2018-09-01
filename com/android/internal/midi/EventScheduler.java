// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.midi;

import java.util.SortedMap;
import java.util.TreeMap;

public class EventScheduler
{
    private class FastEventQueue
    {

        public void add(SchedulableEvent schedulableevent)
        {
            SchedulableEvent._2D_set0(schedulableevent, null);
            SchedulableEvent._2D_set0(mLast, schedulableevent);
            mLast = schedulableevent;
            mEventsAdded = mEventsAdded + 1L;
        }

        public SchedulableEvent remove()
        {
            mEventsRemoved = mEventsRemoved + 1L;
            SchedulableEvent schedulableevent = mFirst;
            mFirst = SchedulableEvent._2D_get0(schedulableevent);
            SchedulableEvent._2D_set0(schedulableevent, null);
            return schedulableevent;
        }

        int size()
        {
            return (int)(mEventsAdded - mEventsRemoved);
        }

        volatile long mEventsAdded;
        volatile long mEventsRemoved;
        volatile SchedulableEvent mFirst;
        volatile SchedulableEvent mLast;
        final EventScheduler this$0;

        FastEventQueue(SchedulableEvent schedulableevent)
        {
            this$0 = EventScheduler.this;
            super();
            mFirst = schedulableevent;
            mLast = mFirst;
            mEventsAdded = 1L;
            mEventsRemoved = 0L;
        }
    }

    public static class SchedulableEvent
    {

        static SchedulableEvent _2D_get0(SchedulableEvent schedulableevent)
        {
            return schedulableevent.mNext;
        }

        static SchedulableEvent _2D_set0(SchedulableEvent schedulableevent, SchedulableEvent schedulableevent1)
        {
            schedulableevent.mNext = schedulableevent1;
            return schedulableevent1;
        }

        public long getTimestamp()
        {
            return mTimestamp;
        }

        public void setTimestamp(long l)
        {
            mTimestamp = l;
        }

        private volatile SchedulableEvent mNext;
        private long mTimestamp;

        public SchedulableEvent(long l)
        {
            mNext = null;
            mTimestamp = l;
        }
    }


    public EventScheduler()
    {
        mEventPool = null;
        mMaxPoolSize = 200;
        mEventBuffer = new TreeMap();
    }

    private SchedulableEvent removeNextEventLocked(long l)
    {
        FastEventQueue fasteventqueue = (FastEventQueue)mEventBuffer.get(Long.valueOf(l));
        if(fasteventqueue.size() == 1)
            mEventBuffer.remove(Long.valueOf(l));
        return fasteventqueue.remove();
    }

    public void add(SchedulableEvent schedulableevent)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        FastEventQueue fasteventqueue = (FastEventQueue)mEventBuffer.get(Long.valueOf(schedulableevent.getTimestamp()));
        if(fasteventqueue != null) goto _L2; else goto _L1
_L1:
        if(!mEventBuffer.isEmpty()) goto _L4; else goto _L3
_L3:
        long l = 0x7fffffffffffffffL;
_L7:
        fasteventqueue = JVM INSTR new #6   <Class EventScheduler$FastEventQueue>;
        fasteventqueue.this. FastEventQueue(schedulableevent);
        mEventBuffer.put(Long.valueOf(schedulableevent.getTimestamp()), fasteventqueue);
        if(schedulableevent.getTimestamp() < l)
            mLock.notify();
_L5:
        obj;
        JVM INSTR monitorexit ;
        return;
_L4:
        l = ((Long)mEventBuffer.firstKey()).longValue();
        continue; /* Loop/switch isn't completed */
_L2:
        fasteventqueue.add(schedulableevent);
          goto _L5
        schedulableevent;
        throw schedulableevent;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public void addEventToPool(SchedulableEvent schedulableevent)
    {
        if(mEventPool != null) goto _L2; else goto _L1
_L1:
        mEventPool = new FastEventQueue(schedulableevent);
_L4:
        return;
_L2:
        if(mEventPool.size() < mMaxPoolSize)
            mEventPool.add(schedulableevent);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void close()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mClosed = true;
        mLock.notify();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected void flush()
    {
        mEventBuffer = new TreeMap();
    }

    public SchedulableEvent getNextEvent(long l)
    {
        Object obj = null;
        Object obj1 = mLock;
        obj1;
        JVM INSTR monitorenter ;
        SchedulableEvent schedulableevent = obj;
        long l1;
        if(mEventBuffer.isEmpty())
            break MISSING_BLOCK_LABEL_61;
        l1 = ((Long)mEventBuffer.firstKey()).longValue();
        schedulableevent = obj;
        if(l1 > l)
            break MISSING_BLOCK_LABEL_61;
        schedulableevent = removeNextEventLocked(l1);
        obj1;
        JVM INSTR monitorexit ;
        return schedulableevent;
        Exception exception;
        exception;
        throw exception;
    }

    public SchedulableEvent removeEventfromPool()
    {
        Object obj = null;
        SchedulableEvent schedulableevent = obj;
        if(mEventPool != null)
        {
            schedulableevent = obj;
            if(mEventPool.size() > 1)
                schedulableevent = mEventPool.remove();
        }
        return schedulableevent;
    }

    public SchedulableEvent waitNextEvent()
        throws InterruptedException
    {
        Object obj = null;
        Object obj1 = mLock;
        obj1;
        JVM INSTR monitorenter ;
_L2:
        SchedulableEvent schedulableevent = obj;
        if(mClosed)
            break MISSING_BLOCK_LABEL_72;
        long l = 0x7fffffffL;
        long l1;
        if(mEventBuffer.isEmpty())
            break MISSING_BLOCK_LABEL_107;
        l = System.nanoTime();
        l1 = ((Long)mEventBuffer.firstKey()).longValue();
        if(l1 > l)
            break MISSING_BLOCK_LABEL_76;
        schedulableevent = removeNextEventLocked(l1);
        obj1;
        JVM INSTR monitorexit ;
        return schedulableevent;
        l1 = 1L + (l1 - l) / 0xf4240L;
        l = l1;
        if(l1 > 0x7fffffffL)
            l = 0x7fffffffL;
        mLock.wait((int)l);
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    private static final long NANOS_PER_MILLI = 0xf4240L;
    private boolean mClosed;
    private volatile SortedMap mEventBuffer;
    private FastEventQueue mEventPool;
    private final Object mLock = new Object();
    private int mMaxPoolSize;
}

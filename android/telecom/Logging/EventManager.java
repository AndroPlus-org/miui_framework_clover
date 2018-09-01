// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom.Logging;

import android.telecom.Log;
import android.text.TextUtils;
import android.util.Pair;
import com.android.internal.util.IndentingPrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

// Referenced classes of package android.telecom.Logging:
//            TimedEvent

public class EventManager
{
    public static class Event
    {

        public Object data;
        public String eventId;
        public String sessionId;
        public long time;

        public Event(String s, String s1, long l, Object obj)
        {
            eventId = s;
            sessionId = s1;
            time = l;
            data = obj;
        }
    }

    public static interface EventListener
    {

        public abstract void eventRecordAdded(EventRecord eventrecord);
    }

    public class EventRecord
    {

        public void addEvent(String s, String s1, Object obj)
        {
            mEvents.add(new Event(s, s1, System.currentTimeMillis(), obj));
            Log.i("Event", "RecordEntry %s: %s, %s", new Object[] {
                mRecordEntry.getId(), s, obj
            });
        }

        public void dump(IndentingPrintWriter indentingprintwriter)
        {
            indentingprintwriter.print(mRecordEntry.getDescription());
            indentingprintwriter.increaseIndent();
            for(Iterator iterator = mEvents.iterator(); iterator.hasNext(); indentingprintwriter.println())
            {
                Event event1 = (Event)iterator.next();
                indentingprintwriter.print(EventManager._2D_get2(EventManager.this).format(new Date(event1.time)));
                indentingprintwriter.print(" - ");
                indentingprintwriter.print(event1.eventId);
                if(event1.data != null)
                {
                    indentingprintwriter.print(" (");
                    Object obj = event1.data;
                    Object obj2 = obj;
                    if(obj instanceof Loggable)
                    {
                        EventRecord eventrecord = (EventRecord)EventManager._2D_get0(EventManager.this).get(obj);
                        obj2 = obj;
                        if(eventrecord != null)
                            obj2 = (new StringBuilder()).append("RecordEntry ").append(eventrecord.mRecordEntry.getId()).toString();
                    }
                    indentingprintwriter.print(obj2);
                    indentingprintwriter.print(")");
                }
                if(!TextUtils.isEmpty(event1.sessionId))
                {
                    indentingprintwriter.print(":");
                    indentingprintwriter.print(event1.sessionId);
                }
            }

            indentingprintwriter.println("Timings (average for this call, milliseconds):");
            indentingprintwriter.increaseIndent();
            Map map = EventTiming.averageTimings(extractEventTimings());
            Object obj1 = new ArrayList(map.keySet());
            Collections.sort(((List) (obj1)));
            for(Iterator iterator1 = ((Iterable) (obj1)).iterator(); iterator1.hasNext(); indentingprintwriter.printf("%s: %.2f\n", new Object[] {
        obj1, map.get(obj1)
    }))
                obj1 = (String)iterator1.next();

            indentingprintwriter.decreaseIndent();
            indentingprintwriter.decreaseIndent();
        }

        public List extractEventTimings()
        {
            if(mEvents == null)
                return Collections.emptyList();
            LinkedList linkedlist = new LinkedList();
            HashMap hashmap = new HashMap();
            Iterator iterator = mEvents.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Event event1 = (Event)iterator.next();
                if(EventManager._2D_get1(EventManager.this).containsKey(event1.eventId))
                {
                    TimedEventPair timedeventpair;
                    for(Iterator iterator1 = ((List)EventManager._2D_get1(EventManager.this).get(event1.eventId)).iterator(); iterator1.hasNext(); hashmap.put(timedeventpair.mResponse, new PendingResponse(event1.eventId, event1.time, timedeventpair.mTimeoutMillis, timedeventpair.mName)))
                        timedeventpair = (TimedEventPair)iterator1.next();

                }
                PendingResponse pendingresponse = (PendingResponse)hashmap.remove(event1.eventId);
                if(pendingresponse != null)
                {
                    long l = event1.time - pendingresponse.requestEventTimeMillis;
                    if(l < pendingresponse.timeoutMillis)
                        linkedlist.add(new EventTiming(pendingresponse.name, l));
                }
            } while(true);
            return linkedlist;
        }

        public List getEvents()
        {
            return mEvents;
        }

        public Loggable getRecordEntry()
        {
            return mRecordEntry;
        }

        private final List mEvents = new LinkedList();
        private final Loggable mRecordEntry;
        final EventManager this$0;

        public EventRecord(Loggable loggable)
        {
            this$0 = EventManager.this;
            super();
            mRecordEntry = loggable;
        }
    }

    public class EventRecord.EventTiming extends TimedEvent
    {

        public volatile Object getKey()
        {
            return getKey();
        }

        public String getKey()
        {
            return name;
        }

        public long getTime()
        {
            return time;
        }

        public String name;
        final EventRecord this$1;
        public long time;

        public EventRecord.EventTiming(String s, long l)
        {
            this$1 = EventRecord.this;
            super();
            name = s;
            time = l;
        }
    }

    private class EventRecord.PendingResponse
    {

        String name;
        String requestEventId;
        long requestEventTimeMillis;
        final EventRecord this$1;
        long timeoutMillis;

        public EventRecord.PendingResponse(String s, long l, long l1, String s1)
        {
            this$1 = EventRecord.this;
            super();
            requestEventId = s;
            requestEventTimeMillis = l;
            timeoutMillis = l1;
            name = s1;
        }
    }

    public static interface Loggable
    {

        public abstract String getDescription();

        public abstract String getId();
    }

    public static class TimedEventPair
    {

        private static final long DEFAULT_TIMEOUT = 3000L;
        String mName;
        String mRequest;
        String mResponse;
        long mTimeoutMillis;

        public TimedEventPair(String s, String s1, String s2)
        {
            mTimeoutMillis = 3000L;
            mRequest = s;
            mResponse = s1;
            mName = s2;
        }

        public TimedEventPair(String s, String s1, String s2, long l)
        {
            mTimeoutMillis = 3000L;
            mRequest = s;
            mResponse = s1;
            mName = s2;
            mTimeoutMillis = l;
        }
    }


    static Map _2D_get0(EventManager eventmanager)
    {
        return eventmanager.mCallEventRecordMap;
    }

    static Map _2D_get1(EventManager eventmanager)
    {
        return eventmanager.requestResponsePairs;
    }

    static DateFormat _2D_get2(EventManager eventmanager)
    {
        return eventmanager.sDateFormat;
    }

    public EventManager(SessionManager.ISessionIdQueryHandler isessionidqueryhandler)
    {
        mEventRecords = new LinkedBlockingQueue(10);
        mEventListeners = new ArrayList();
        mSessionIdHandler = isessionidqueryhandler;
        sDateFormat.setTimeZone(TimeZone.getDefault());
    }

    private void addEventRecord(EventRecord eventrecord)
    {
        Loggable loggable = eventrecord.getRecordEntry();
        if(mEventRecords.remainingCapacity() == 0)
        {
            EventRecord eventrecord1 = (EventRecord)mEventRecords.poll();
            if(eventrecord1 != null)
                mCallEventRecordMap.remove(eventrecord1.getRecordEntry());
        }
        mEventRecords.add(eventrecord);
        mCallEventRecordMap.put(loggable, eventrecord);
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        for(Iterator iterator = mEventListeners.iterator(); iterator.hasNext(); ((EventListener)iterator.next()).eventRecordAdded(eventrecord));
        break MISSING_BLOCK_LABEL_113;
        eventrecord;
        throw eventrecord;
        obj;
        JVM INSTR monitorexit ;
    }

    static int lambda$_2D_android_telecom_Logging_EventManager_11918(Pair pair, Pair pair1)
    {
        return Long.compare(((Event)pair.second).time, ((Event)pair1.second).time);
    }

    public void addRequestResponsePair(TimedEventPair timedeventpair)
    {
        if(requestResponsePairs.containsKey(timedeventpair.mRequest))
        {
            ((List)requestResponsePairs.get(timedeventpair.mRequest)).add(timedeventpair);
        } else
        {
            ArrayList arraylist = new ArrayList();
            arraylist.add(timedeventpair);
            requestResponsePairs.put(timedeventpair.mRequest, arraylist);
        }
    }

    public void changeEventCacheSize(int i)
    {
        LinkedBlockingQueue linkedblockingqueue = mEventRecords;
        mEventRecords = new LinkedBlockingQueue(i);
        mCallEventRecordMap.clear();
        linkedblockingqueue.forEach(new _.Lambda.Bho_6fQ_lBTm8N3FcbHLVOfu_sY._cls1(this));
    }

    public void dumpEvents(IndentingPrintWriter indentingprintwriter)
    {
        indentingprintwriter.println("Historical Events:");
        indentingprintwriter.increaseIndent();
        for(Iterator iterator = mEventRecords.iterator(); iterator.hasNext(); ((EventRecord)iterator.next()).dump(indentingprintwriter));
        indentingprintwriter.decreaseIndent();
    }

    public void dumpEventsTimeline(IndentingPrintWriter indentingprintwriter)
    {
        indentingprintwriter.println("Historical Events (sorted by time):");
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = mEventRecords.iterator(); iterator.hasNext();)
        {
            EventRecord eventrecord = (EventRecord)iterator.next();
            Iterator iterator2 = eventrecord.getEvents().iterator();
            while(iterator2.hasNext()) 
            {
                Event event1 = (Event)iterator2.next();
                arraylist.add(new Pair(eventrecord.getRecordEntry(), event1));
            }
        }

        arraylist.sort(_.Lambda.Bho_6fQ_lBTm8N3FcbHLVOfu_sY.$INST$0);
        indentingprintwriter.increaseIndent();
        Pair pair;
        for(Iterator iterator1 = arraylist.iterator(); iterator1.hasNext(); indentingprintwriter.println(((Event)pair.second).data))
        {
            pair = (Pair)iterator1.next();
            indentingprintwriter.print(sDateFormat.format(new Date(((Event)pair.second).time)));
            indentingprintwriter.print(",");
            indentingprintwriter.print(((Loggable)pair.first).getId());
            indentingprintwriter.print(",");
            indentingprintwriter.print(((Event)pair.second).eventId);
            indentingprintwriter.print(",");
        }

        indentingprintwriter.decreaseIndent();
    }

    public void event(Loggable loggable, String s, Object obj)
    {
        String s1;
        s1 = mSessionIdHandler.getSessionId();
        if(loggable == null)
        {
            Log.i("Logging.Events", "Non-call EVENT: %s, %s", new Object[] {
                s, obj
            });
            return;
        }
        LinkedBlockingQueue linkedblockingqueue = mEventRecords;
        linkedblockingqueue;
        JVM INSTR monitorenter ;
        if(!mCallEventRecordMap.containsKey(loggable))
        {
            EventRecord eventrecord = JVM INSTR new #12  <Class EventManager$EventRecord>;
            eventrecord.this. EventRecord(loggable);
            addEventRecord(eventrecord);
        }
        ((EventRecord)mCallEventRecordMap.get(loggable)).addEvent(s, s1, obj);
        linkedblockingqueue;
        JVM INSTR monitorexit ;
        return;
        loggable;
        throw loggable;
    }

    public transient void event(Loggable loggable, String s, String s1, Object aobj[])
    {
        if(aobj == null) goto _L2; else goto _L1
_L1:
        int i = aobj.length;
        if(i != 0) goto _L3; else goto _L2
_L2:
        event(loggable, s, s1);
        return;
_L3:
        String s2 = String.format(Locale.US, s1, aobj);
        s1 = s2;
        continue; /* Loop/switch isn't completed */
        IllegalFormatException illegalformatexception;
        illegalformatexception;
        Log.e(this, illegalformatexception, "IllegalFormatException: formatString='%s' numArgs=%d", new Object[] {
            s1, Integer.valueOf(aobj.length)
        });
        s1 = (new StringBuilder()).append(s1).append(" (An error occurred while formatting the message.)").toString();
        if(true) goto _L2; else goto _L4
_L4:
    }

    public Map getCallEventRecordMap()
    {
        return mCallEventRecordMap;
    }

    public LinkedBlockingQueue getEventRecords()
    {
        return mEventRecords;
    }

    void lambda$_2D_android_telecom_Logging_EventManager_12735(EventRecord eventrecord)
    {
        Loggable loggable = eventrecord.getRecordEntry();
        if(mEventRecords.remainingCapacity() == 0)
        {
            EventRecord eventrecord1 = (EventRecord)mEventRecords.poll();
            if(eventrecord1 != null)
                mCallEventRecordMap.remove(eventrecord1.getRecordEntry());
        }
        mEventRecords.add(eventrecord);
        mCallEventRecordMap.put(loggable, eventrecord);
    }

    public void registerEventListener(EventListener eventlistener)
    {
        if(eventlistener == null) goto _L2; else goto _L1
_L1:
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        mEventListeners.add(eventlistener);
        obj;
        JVM INSTR monitorexit ;
_L2:
        return;
        eventlistener;
        throw eventlistener;
    }

    public static final int DEFAULT_EVENTS_TO_CACHE = 10;
    public static final String TAG = "Logging.Events";
    private static final Object mSync = new Object();
    private final Map mCallEventRecordMap = new HashMap();
    private List mEventListeners;
    private LinkedBlockingQueue mEventRecords;
    private SessionManager.ISessionIdQueryHandler mSessionIdHandler;
    private final Map requestResponsePairs = new HashMap();
    private final DateFormat sDateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

}

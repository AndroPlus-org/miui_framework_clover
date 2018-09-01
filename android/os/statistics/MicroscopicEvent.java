// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.os.Parcel;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package android.os.statistics:
//            PerfEvent

public abstract class MicroscopicEvent extends PerfEvent
{

    public MicroscopicEvent(int i, String s)
    {
        super(i, s);
    }

    private void copyFrom(MicroscopicEvent microscopicevent)
    {
        super.copyFrom(microscopicevent);
        threadId = microscopicevent.threadId;
        threadName = microscopicevent.threadName;
        beginUptimeMillis = microscopicevent.beginUptimeMillis;
        endUptimeMillis = microscopicevent.endUptimeMillis;
        schedPolicy = microscopicevent.schedPolicy;
        schedPriority = microscopicevent.schedPriority;
        schedGroup = microscopicevent.schedGroup;
        runningTimeMs = microscopicevent.runningTimeMs;
        runnableTimeMs = microscopicevent.runnableTimeMs;
        sleepingTimeMs = microscopicevent.sleepingTimeMs;
        endRealTimeMs = microscopicevent.endRealTimeMs;
        eventFlags = microscopicevent.eventFlags;
        matchedPeerBlockingDuration = microscopicevent.matchedPeerBlockingDuration;
    }

    public void copyFrom(PerfEvent perfevent)
    {
        copyFrom((MicroscopicEvent)perfevent);
    }

    public long getBeginUptimeMillis()
    {
        return beginUptimeMillis;
    }

    public long getEndUptimeMillis()
    {
        return endUptimeMillis;
    }

    public abstract boolean hasMultiplePeerBlockingEvents();

    boolean hasNativeStack()
    {
        return false;
    }

    public abstract boolean hasPeerBlockingEvent();

    public abstract boolean isBlockedBy(MicroscopicEvent microscopicevent);

    public abstract boolean isBlockedBySameProcess();

    public abstract boolean isBlockingMultiplePeer();

    public abstract boolean isBlockingSameProcess();

    public boolean isMasterEvent()
    {
        boolean flag = true;
        if((eventFlags & 3) != 1)
            flag = false;
        return flag;
    }

    public abstract boolean isPeerBlockingEvent();

    public abstract boolean isRootEvent();

    public void readFromJson(JSONObject jsonobject)
    {
        super.readFromJson(jsonobject);
        threadId = jsonobject.optInt("threadId", 0);
        threadName = jsonobject.optString("threadName", "");
        beginUptimeMillis = jsonobject.optLong("beginTime", 0L);
        endUptimeMillis = jsonobject.optLong("endTime", 0L);
        schedPolicy = jsonobject.optInt("policy", -1);
        schedPriority = jsonobject.optInt("priority", 0);
        schedGroup = jsonobject.optInt("schedGroup", -1);
        runningTimeMs = jsonobject.optLong("runningTime", 0L);
        runnableTimeMs = jsonobject.optLong("runnableTime", 0L);
        sleepingTimeMs = jsonobject.optLong("sleepingTime", 0L);
        endRealTimeMs = jsonobject.optLong("endRealTime", 0L);
        eventFlags = jsonobject.optInt("eventFlags", 0);
        matchedPeerBlockingDuration = 0L;
    }

    public void readFromParcel(Parcel parcel)
    {
        super.readFromParcel(parcel);
        threadId = parcel.readInt();
        threadName = parcel.readString();
        if(threadName == null)
            threadName = "";
        beginUptimeMillis = parcel.readLong();
        endUptimeMillis = parcel.readLong();
        schedPolicy = parcel.readInt();
        schedPriority = parcel.readInt();
        schedGroup = parcel.readInt();
        runningTimeMs = parcel.readLong();
        runnableTimeMs = parcel.readLong();
        sleepingTimeMs = parcel.readLong();
        endRealTimeMs = parcel.readLong();
        eventFlags = parcel.readInt();
        matchedPeerBlockingDuration = 0L;
    }

    public void reset()
    {
        super.reset();
        threadId = 0;
        threadName = "";
        beginUptimeMillis = 0L;
        endUptimeMillis = 0L;
        schedPolicy = -1;
        schedPriority = 0;
        schedGroup = -1;
        runningTimeMs = 0L;
        runnableTimeMs = 0L;
        sleepingTimeMs = 0L;
        endRealTimeMs = 0L;
        eventFlags = 0;
        matchedPeerBlockingDuration = 0L;
    }

    public void writeToJson(JSONObject jsonobject)
    {
        super.writeToJson(jsonobject);
        jsonobject.put("threadId", threadId);
        jsonobject.put("threadName", threadName);
        jsonobject.put("beginTime", beginUptimeMillis);
        jsonobject.put("endTime", endUptimeMillis);
        jsonobject.put("policy", schedPolicy);
        jsonobject.put("priority", schedPriority);
        jsonobject.put("schedGroup", schedGroup);
        jsonobject.put("runningTime", runningTimeMs);
        jsonobject.put("runnableTime", runnableTimeMs);
        jsonobject.put("sleepingTime", sleepingTimeMs);
        jsonobject.put("endRealTime", endRealTimeMs);
        jsonobject.put("eventFlags", eventFlags);
_L1:
        return;
        jsonobject;
        jsonobject.printStackTrace();
          goto _L1
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeInt(threadId);
        parcel.writeString(threadName);
        parcel.writeLong(beginUptimeMillis);
        parcel.writeLong(endUptimeMillis);
        parcel.writeInt(schedPolicy);
        parcel.writeInt(schedPriority);
        parcel.writeInt(schedGroup);
        parcel.writeLong(runningTimeMs);
        parcel.writeLong(runnableTimeMs);
        parcel.writeLong(sleepingTimeMs);
        parcel.writeLong(endRealTimeMs);
        parcel.writeInt(eventFlags);
    }

    public static final int FLAG_INITIATOR_POSITION_MASK = 3;
    public static final int FLAG_INITIATOR_POSITION_MASTER = 1;
    public static final int FLAG_INITIATOR_POSITION_SLAVE = 2;
    public static final int FLAG_INITIATOR_POSITION_UNKNOWN = 0;
    public static final int MICRO_EVENT_TYPE_COUNT = 12;
    public static final int MICRO_EVENT_TYPE_START = 0;
    public static final int SCHED_POLICY_UNKNOWN = -1;
    public static final int TYPE_LOOPER_ONCE = 11;
    public static final int TYPE_SINGLE_BINDER_CALL = 4;
    public static final int TYPE_SINGLE_BINDER_EXECUTION = 5;
    public static final int TYPE_SINGLE_CONDITION_AWAKEN = 2;
    public static final int TYPE_SINGLE_CONDITION_WAIT = 3;
    public static final int TYPE_SINGLE_INPUT_EVENT = 9;
    public static final int TYPE_SINGLE_JNI_METHOD = 10;
    public static final int TYPE_SINGLE_LOCK_HOLD = 0;
    public static final int TYPE_SINGLE_LOCK_WAIT = 1;
    public static final int TYPE_SINGLE_LOOPER_MESSAGE = 8;
    public static final int TYPE_SINGLE_SYSTEM_TRACE_EVENT = 7;
    public static final int TYPE_SINGLE_TRACE_POINT = 6;
    public long beginUptimeMillis;
    public long endRealTimeMs;
    public long endUptimeMillis;
    public int eventFlags;
    long matchedPeerBlockingDuration;
    public long runnableTimeMs;
    public long runningTimeMs;
    public int schedGroup;
    public int schedPolicy;
    public int schedPriority;
    public long sleepingTimeMs;
    public int threadId;
    public String threadName;
}

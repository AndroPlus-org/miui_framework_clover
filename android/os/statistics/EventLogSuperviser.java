// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.os.Parcel;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.EventLog;
import java.util.Arrays;
import org.json.*;

// Referenced classes of package android.os.statistics:
//            PerfSupervisionSettings, PerfEventReporter, MacroscopicEvent, ParcelUtils, 
//            PerfEvent, JniParcel, NativeBackTrace

public class EventLogSuperviser
{
    private static class EventLogTags
    {

        public static final int AM_ACTIVITY_LAUNCH_TIME = 30009;
        public static final int AM_ACTIVITY_LAUNCH_TIME_MIUI = 30088;
        public static final int AM_ANR = 30008;
        public static final int AM_BIND_SERVICE = 30071;
        public static final int AM_CRASH = 30039;
        public static final int AM_CREATE_SERVICE = 30030;
        public static final int AM_LOW_MEMORY = 30017;
        public static final int AM_MEMINFO = 30046;
        public static final int AM_MEM_FACTOR = 30050;
        public static final int AM_PROCESS_START_TIMEOUT = 30037;
        public static final int AM_PROC_START = 30014;
        public static final int AM_PROVIDER_LOST_PROCESS = 30036;
        public static final int AM_RELAUNCH_RESUME_ACTIVITY = 30019;
        public static final int AM_RESUME_ACTIVITY = 30007;
        public static final int AM_START_SERVICE = 30070;
        public static final int AM_SWITCH_USER = 30041;
        public static final int CONTENT_QUERY_SAMPLE = 52002;
        public static final int CONTENT_UPDATE_SAMPLE = 52003;
        public static final int POWER_SCREEN_STATE = 2728;

        private EventLogTags()
        {
        }
    }

    public static class SingleEventLogItem extends MacroscopicEvent
    {

        static Object _2D_set0(SingleEventLogItem singleeventlogitem, Object obj)
        {
            singleeventlogitem.eventLogValueObject = obj;
            return obj;
        }

        private void copyFrom(SingleEventLogItem singleeventlogitem)
        {
            super.copyFrom(singleeventlogitem);
            currentTimeMillis = singleeventlogitem.currentTimeMillis;
            eventLogTagId = singleeventlogitem.eventLogTagId;
            eventLogTagName = singleeventlogitem.eventLogTagName;
            eventLogValueStrs = singleeventlogitem.eventLogValueStrs;
            eventLogValueObject = singleeventlogitem.eventLogValueObject;
        }

        public void copyFrom(PerfEvent perfevent)
        {
            copyFrom((SingleEventLogItem)perfevent);
        }

        void fillIn(JniParcel jniparcel, Object obj, NativeBackTrace nativebacktrace)
        {
        }

        boolean isUserPerceptible()
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(eventLogTagId != 30008)
                if(eventLogTagId == 30088)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        public void readFromJson(JSONObject jsonobject)
        {
            super.readFromJson(jsonobject);
            currentTimeMillis = jsonobject.optLong("eventLogTime", 0L);
            eventLogTagId = jsonobject.optInt("eventLogTagId", 0);
            eventLogTagName = jsonobject.optString("eventLogTagName", "");
            jsonobject = jsonobject.optJSONArray("eventlogValues");
            if(jsonobject != null)
            {
                eventLogValueStrs = new String[jsonobject.length()];
                for(int i = 0; i < jsonobject.length(); i++)
                    eventLogValueStrs[i] = jsonobject.optString(i);

            } else
            {
                eventLogValueStrs = EventLogSuperviser._2D_get0();
            }
            eventLogValueObject = null;
        }

        public void readFromParcel(Parcel parcel)
        {
            super.readFromParcel(parcel);
            currentTimeMillis = parcel.readLong();
            eventLogTagId = parcel.readInt();
            eventLogTagName = parcel.readString();
            if(eventLogTagName == null)
                eventLogTagName = "";
            eventLogValueStrs = ParcelUtils.readStringArray(parcel);
            if(eventLogValueStrs == null)
                eventLogValueStrs = EventLogSuperviser._2D_get0();
            eventLogValueObject = null;
        }

        public void reset()
        {
            super.reset();
            currentTimeMillis = 0L;
            eventLogTagId = 0;
            eventLogTagName = "";
            eventLogValueStrs = EventLogSuperviser._2D_get0();
            eventLogValueObject = null;
        }

        void resolveLazyInfo()
        {
            if(lazyInfoResolved)
                return;
            super.resolveLazyInfo();
            if(eventLogTagId != 0 && TextUtils.isEmpty(eventLogTagName))
            {
                eventLogTagName = EventLog.getTagName(eventLogTagId);
                if(TextUtils.isEmpty(eventLogTagName))
                    eventLogTagName = (new StringBuilder()).append("[").append(String.valueOf(eventLogTagId)).append("]").toString();
            }
            if(eventLogValueObject != null)
            {
                if(eventLogValueObject instanceof Object[])
                {
                    Object aobj[] = (Object[])eventLogValueObject;
                    eventLogValueStrs = new String[aobj.length];
                    for(int i = 0; i < aobj.length; i++)
                        eventLogValueStrs[i] = String.valueOf(aobj[i]);

                } else
                {
                    eventLogValueStrs = new String[1];
                    eventLogValueStrs[0] = eventLogValueObject.toString();
                }
            } else
            {
                eventLogValueStrs = EventLogSuperviser._2D_get0();
            }
            eventLogValueObject = null;
        }

        public void writeToJson(JSONObject jsonobject)
        {
            super.writeToJson(jsonobject);
            jsonobject.put("eventLogTime", currentTimeMillis);
            jsonobject.put("eventLogTagId", eventLogTagId);
            jsonobject.put("eventLogTagName", eventLogTagName);
            if(eventLogValueStrs != null)
            {
                JSONArray jsonarray = JVM INSTR new #95  <Class JSONArray>;
                jsonarray.JSONArray(eventLogValueStrs);
                jsonobject.put("eventlogValues", jsonarray);
            }
_L1:
            return;
            jsonobject;
            jsonobject.printStackTrace();
              goto _L1
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeLong(currentTimeMillis);
            parcel.writeInt(eventLogTagId);
            parcel.writeString(eventLogTagName);
            ParcelUtils.writeStringArray(parcel, eventLogValueStrs);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SingleEventLogItem createFromParcel(Parcel parcel)
            {
                SingleEventLogItem singleeventlogitem = new SingleEventLogItem();
                singleeventlogitem.readFromParcel(parcel);
                return singleeventlogitem;
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SingleEventLogItem[] newArray(int i)
            {
                return new SingleEventLogItem[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private static final String FIELD_EVENT_LOG_TAG_ID = "eventLogTagId";
        private static final String FIELD_EVENT_LOG_TAG_NAME = "eventLogTagName";
        private static final String FIELD_EVENT_LOG_TIME = "eventLogTime";
        private static final String FIELD_EVENT_LOG_VALUE_STRS = "eventlogValues";
        public long currentTimeMillis;
        public int eventLogTagId;
        public String eventLogTagName;
        private Object eventLogValueObject;
        public String eventLogValueStrs[];


        public SingleEventLogItem()
        {
            super(0x10000, "EventLog");
        }
    }


    static String[] _2D_get0()
    {
        return emptyEventValueStrs;
    }

    public EventLogSuperviser()
    {
    }

    private static boolean isSupervised(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(PerfSupervisionSettings.getSupervisionLevel() != 0)
        {
            flag1 = flag;
            if(Arrays.binarySearch(supervisedEventLogTags, i) >= 0)
                flag1 = true;
        }
        return flag1;
    }

    public static void notifyEvent(int i, float f)
    {
        if(isSupervised(i))
            notifyEventWithObject(i, Float.valueOf(f));
    }

    public static void notifyEvent(int i, int j)
    {
        if(isSupervised(i))
            notifyEventWithObject(i, Integer.valueOf(j));
    }

    public static void notifyEvent(int i, long l)
    {
        if(isSupervised(i))
            notifyEventWithObject(i, Long.valueOf(l));
    }

    public static void notifyEvent(int i, String s)
    {
        if(isSupervised(i))
            notifyEventWithObject(i, s);
    }

    public static transient void notifyEvent(int i, Object aobj[])
    {
        if(isSupervised(i))
            notifyEventWithObject(i, ((Object) (aobj)));
    }

    private static void notifyEventWithObject(int i, Object obj)
    {
        SingleEventLogItem singleeventlogitem = new SingleEventLogItem();
        singleeventlogitem.occurUptimeMillis = SystemClock.uptimeMillis();
        singleeventlogitem.currentTimeMillis = System.currentTimeMillis();
        singleeventlogitem.eventLogTagId = i;
        SingleEventLogItem._2D_set0(singleeventlogitem, obj);
        PerfEventReporter.report(singleeventlogitem);
    }

    private static final String emptyEventValueStrs[] = new String[0];
    private static final int supervisedEventLogTags[] = {
        2728, 30008, 30017, 30037, 30039, 30041, 30050, 30088
    };

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.os.Parcel;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package android.os.statistics:
//            PerfSupervisionSettings, MicroscopicEvent, JniParcel, OsUtils, 
//            PerfEvent, NativeBackTrace

public class PerfTracer
{
    public static class SingleTracePoint extends MicroscopicEvent
    {

        private void copyFrom(SingleTracePoint singletracepoint)
        {
            super.copyFrom(singletracepoint);
            level = singletracepoint.level;
            tag = singletracepoint.tag;
            numberValue = singletracepoint.numberValue;
            textValue = singletracepoint.textValue;
            objectValue = singletracepoint.objectValue;
        }

        public void copyFrom(PerfEvent perfevent)
        {
            copyFrom((SingleTracePoint)perfevent);
        }

        void fillIn(JniParcel jniparcel, Object obj, NativeBackTrace nativebacktrace)
        {
            int i = 0;
            threadId = (int)jniparcel.readLong();
            threadName = jniparcel.readString();
            beginUptimeMillis = jniparcel.readLong();
            endUptimeMillis = jniparcel.readLong();
            schedPolicy = OsUtils.decodeThreadSchedulePolicy((int)jniparcel.readLong());
            schedPriority = (int)jniparcel.readLong();
            schedGroup = (int)jniparcel.readLong();
            runningTimeMs = jniparcel.readLong();
            runnableTimeMs = jniparcel.readLong();
            sleepingTimeMs = jniparcel.readLong();
            endRealTimeMs = jniparcel.readLong();
            level = (int)jniparcel.readLong();
            if(level == 0)
                i = 1;
            eventFlags = i;
            numberValue = jniparcel.readLong();
            tag = jniparcel.readString();
            objectValue = jniparcel.readObject();
        }

        public boolean hasMultiplePeerBlockingEvents()
        {
            return false;
        }

        public boolean hasPeerBlockingEvent()
        {
            return false;
        }

        public boolean isBlockedBy(MicroscopicEvent microscopicevent)
        {
            return false;
        }

        public boolean isBlockedBySameProcess()
        {
            return false;
        }

        public boolean isBlockingMultiplePeer()
        {
            return false;
        }

        public boolean isBlockingSameProcess()
        {
            return false;
        }

        public boolean isPeerBlockingEvent()
        {
            return false;
        }

        public boolean isRootEvent()
        {
            boolean flag = false;
            if(level == 0)
                flag = true;
            return flag;
        }

        boolean isUserPerceptible()
        {
            return true;
        }

        public void readFromJson(JSONObject jsonobject)
        {
            super.readFromJson(jsonobject);
            level = jsonobject.optInt("level", 0);
            tag = jsonobject.optString("tag", "");
            numberValue = jsonobject.optLong("numberValue", 0L);
            textValue = jsonobject.optString("textValue", "");
            objectValue = null;
        }

        public void readFromParcel(Parcel parcel)
        {
            super.readFromParcel(parcel);
            level = parcel.readInt();
            tag = parcel.readString();
            if(tag == null)
                tag = "";
            numberValue = parcel.readLong();
            textValue = parcel.readString();
            if(textValue == null)
                textValue = "";
            objectValue = null;
        }

        public void reset()
        {
            super.reset();
            level = 0;
            tag = "";
            numberValue = 0L;
            textValue = "";
            objectValue = null;
        }

        void resolveLazyInfo()
        {
            if(lazyInfoResolved)
                return;
            super.resolveLazyInfo();
            if(objectValue == null)
                textValue = "";
            else
                textValue = objectValue.toString();
            if(textValue == null)
                textValue = "";
            objectValue = null;
        }

        public void writeToJson(JSONObject jsonobject)
        {
            super.writeToJson(jsonobject);
            jsonobject.put("level", level);
            jsonobject.put("reason", tag);
            jsonobject.put("tag", tag);
            jsonobject.put("numberValue", numberValue);
            jsonobject.put("textValue", textValue);
_L1:
            return;
            jsonobject;
            jsonobject.printStackTrace();
              goto _L1
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(level);
            parcel.writeString(tag);
            parcel.writeLong(numberValue);
            parcel.writeString(textValue);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SingleTracePoint createFromParcel(Parcel parcel)
            {
                SingleTracePoint singletracepoint = new SingleTracePoint();
                singletracepoint.readFromParcel(parcel);
                return singletracepoint;
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SingleTracePoint[] newArray(int i)
            {
                return new SingleTracePoint[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private static final String FIELD_LEVEL = "level";
        private static final String FIELD_NUMBER_VALUE = "numberValue";
        private static final String FIELD_REASON = "reason";
        private static final String FIELD_TAG = "tag";
        private static final String FIELD_TEXT_VALUE = "textValue";
        public int level;
        public long numberValue;
        private Object objectValue;
        public String tag;
        public String textValue;


        public SingleTracePoint()
        {
            super(6, "TracePoint");
        }
    }


    public PerfTracer()
    {
    }

    public static void beginTracePoint(String s)
    {
        if(!PerfSupervisionSettings.isSupervisionOn())
        {
            return;
        } else
        {
            nativeBeginTracePoint(s);
            return;
        }
    }

    public static void endTracePoint(String s)
    {
        endTracePoint(s, 0L, null, 0);
    }

    public static void endTracePoint(String s, int i)
    {
        endTracePoint(s, 0L, null, i);
    }

    public static void endTracePoint(String s, long l)
    {
        endTracePoint(s, l, null, 0);
    }

    public static void endTracePoint(String s, long l, int i)
    {
        endTracePoint(s, l, null, i);
    }

    public static void endTracePoint(String s, long l, Object obj)
    {
        endTracePoint(s, l, obj, 0);
    }

    public static void endTracePoint(String s, long l, Object obj, int i)
    {
        if(!PerfSupervisionSettings.isSupervisionOn())
        {
            return;
        } else
        {
            nativeEndTracePoint(s, l, obj, i);
            return;
        }
    }

    public static void endTracePoint(String s, Object obj)
    {
        endTracePoint(s, 0L, obj, 0);
    }

    public static void endTracePoint(String s, Object obj, int i)
    {
        endTracePoint(s, 0L, obj, i);
    }

    private static native void nativeBeginTracePoint(String s);

    private static native void nativeEndTracePoint(String s, long l, Object obj, int i);

    private static final boolean DEBUGGING = false;
    private static final String TAG = "PerfTracer";
}

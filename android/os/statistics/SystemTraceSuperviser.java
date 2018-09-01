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

public class SystemTraceSuperviser
{
    public static class SingleSystemTraceEvent extends MicroscopicEvent
    {

        private void copyFrom(SingleSystemTraceEvent singlesystemtraceevent)
        {
            super.copyFrom(singlesystemtraceevent);
            traceTag = singlesystemtraceevent.traceTag;
            traceName = singlesystemtraceevent.traceName;
            traceValue = singlesystemtraceevent.traceValue;
        }

        public void copyFrom(PerfEvent perfevent)
        {
            copyFrom((SingleSystemTraceEvent)perfevent);
        }

        void fillIn(JniParcel jniparcel, Object obj, NativeBackTrace nativebacktrace)
        {
            threadId = (int)jniparcel.readLong();
            threadName = jniparcel.readString();
            beginUptimeMillis = jniparcel.readLong();
            endUptimeMillis = jniparcel.readLong();
            schedPolicy = OsUtils.decodeThreadSchedulePolicy((int)jniparcel.readLong());
            schedPriority = (int)jniparcel.readLong();
            traceTag = jniparcel.readLong();
            traceName = jniparcel.readString();
            traceValue = jniparcel.readObject();
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
            return false;
        }

        public void readFromJson(JSONObject jsonobject)
        {
            super.readFromJson(jsonobject);
            traceTag = jsonobject.optLong("traceTag", 0L);
            traceName = jsonobject.optString("traceName", "");
            traceValue = null;
        }

        public void readFromParcel(Parcel parcel)
        {
            super.readFromParcel(parcel);
            traceTag = parcel.readLong();
            traceName = parcel.readString();
            if(traceName == null)
                traceName = "";
            traceValue = null;
        }

        public void reset()
        {
            super.reset();
            traceTag = 0L;
            traceName = "";
            traceValue = null;
        }

        void resolveLazyInfo()
        {
            if(lazyInfoResolved)
                return;
            super.resolveLazyInfo();
            if(traceValue != null)
                traceName = (new StringBuilder()).append(traceName).append(":{").append(traceValue.toString()).append("}").toString();
            traceValue = null;
        }

        public void writeToJson(JSONObject jsonobject)
        {
            super.writeToJson(jsonobject);
            jsonobject.put("traceTag", traceTag);
            jsonobject.put("traceName", traceName);
_L1:
            return;
            jsonobject;
            jsonobject.printStackTrace();
              goto _L1
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeLong(traceTag);
            parcel.writeString(traceName);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SingleSystemTraceEvent createFromParcel(Parcel parcel)
            {
                SingleSystemTraceEvent singlesystemtraceevent = new SingleSystemTraceEvent();
                singlesystemtraceevent.readFromParcel(parcel);
                return singlesystemtraceevent;
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SingleSystemTraceEvent[] newArray(int i)
            {
                return new SingleSystemTraceEvent[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private static final String FIELD_TRACE_NAME = "traceName";
        private static final String FIELD_TRACE_TAG = "traceTag";
        public String traceName;
        public long traceTag;
        private Object traceValue;


        public SingleSystemTraceEvent()
        {
            super(7, "SystemTraceEvent");
        }
    }


    public SystemTraceSuperviser()
    {
    }

    public static void asyncBeginSupervisedTrace(long l, String s, int i)
    {
        if(!PerfSupervisionSettings.isSupervisionOn())
        {
            return;
        } else
        {
            nativeAsyncBeginTrace(l, s, i);
            return;
        }
    }

    public static void asyncBeginTrace(long l, String s, int i)
    {
        if(!isAllowSystemTraceSupervision())
        {
            return;
        } else
        {
            nativeAsyncBeginTrace(l, s, i);
            return;
        }
    }

    public static void asyncEndSupervisedTrace(long l, String s, int i, Object obj)
    {
        if(!PerfSupervisionSettings.isSupervisionOn())
        {
            return;
        } else
        {
            nativeAsyncEndTrace(l, s, i, obj);
            return;
        }
    }

    public static void asyncEndTrace(long l, String s, int i)
    {
        if(!isAllowSystemTraceSupervision())
        {
            return;
        } else
        {
            nativeAsyncEndTrace(l, s, i, null);
            return;
        }
    }

    public static void beginSupervisedTrace(long l, String s)
    {
        if(!PerfSupervisionSettings.isSupervisionOn())
        {
            return;
        } else
        {
            nativeBeginTrace(l, null);
            return;
        }
    }

    public static void beginTrace(long l, String s)
    {
        if(!isAllowSystemTraceSupervision())
        {
            return;
        } else
        {
            nativeBeginTrace(l, s);
            return;
        }
    }

    public static void endSupervisedTrace(long l, String s, Object obj)
    {
        if(!PerfSupervisionSettings.isSupervisionOn())
        {
            return;
        } else
        {
            nativeEndTrace(l, s, obj);
            return;
        }
    }

    public static void endTrace(long l)
    {
        if(!isAllowSystemTraceSupervision())
        {
            return;
        } else
        {
            nativeEndTrace(l, null, null);
            return;
        }
    }

    private static boolean isAllowSystemTraceSupervision()
    {
        return PerfSupervisionSettings.isInHeavyMode();
    }

    private static native void nativeAsyncBeginTrace(long l, String s, int i);

    private static native void nativeAsyncEndTrace(long l, String s, int i, Object obj);

    private static native void nativeBeginTrace(long l, String s);

    private static native void nativeEndTrace(long l, String s, Object obj);

    private static final boolean DEBUGGING = false;
    private static final String TAG = "SystemTraceSuperviser";
}

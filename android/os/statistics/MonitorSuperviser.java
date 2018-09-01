// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.os.Parcel;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package android.os.statistics:
//            MicroscopicEvent, JniParcel, OsUtils, StackUtils, 
//            ParcelUtils, JavaBackTrace, PerfEvent, NativeBackTrace

public class MonitorSuperviser
{
    public static final class SingleConditionAwaken extends MicroscopicEvent
    {

        private void copyFrom(SingleConditionAwaken singleconditionawaken)
        {
            super.copyFrom(singleconditionawaken);
            monitorId = singleconditionawaken.monitorId;
            peerThreadId = singleconditionawaken.peerThreadId;
            stackTrace = singleconditionawaken.stackTrace;
            javaBackTrace = singleconditionawaken.javaBackTrace;
        }

        public void copyFrom(PerfEvent perfevent)
        {
            copyFrom((SingleConditionAwaken)perfevent);
        }

        void fillIn(JniParcel jniparcel, Object obj, NativeBackTrace nativebacktrace)
        {
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
            monitorId = jniparcel.readLong();
            peerThreadId = (int)jniparcel.readLong();
            javaBackTrace = obj;
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
            return true;
        }

        public boolean isBlockingSameProcess()
        {
            return true;
        }

        public boolean isPeerBlockingEvent()
        {
            return true;
        }

        public boolean isRootEvent()
        {
            return false;
        }

        public void readFromJson(JSONObject jsonobject)
        {
            super.readFromJson(jsonobject);
            monitorId = jsonobject.optLong("monitorId", 0L);
            peerThreadId = jsonobject.optInt("peerThreadId", 0);
            stackTrace = StackUtils.getStackTrace(jsonobject.optJSONArray("stack"));
            javaBackTrace = null;
        }

        public void readFromParcel(Parcel parcel)
        {
            super.readFromParcel(parcel);
            monitorId = parcel.readLong();
            peerThreadId = parcel.readInt();
            stackTrace = ParcelUtils.readStringArray(parcel);
            if(stackTrace == null)
                stackTrace = StackUtils.emptyStack;
            javaBackTrace = null;
        }

        public void reset()
        {
            super.reset();
            monitorId = 0L;
            peerThreadId = 0;
            stackTrace = StackUtils.emptyStack;
            javaBackTrace = null;
        }

        void resolveLazyInfo()
        {
            if(lazyInfoResolved)
            {
                return;
            } else
            {
                super.resolveLazyInfo();
                stackTrace = StackUtils.getStackTrace(JavaBackTrace.resolve(javaBackTrace), JavaBackTrace.resolveClasses(javaBackTrace), null);
                javaBackTrace = null;
                return;
            }
        }

        public void writeToJson(JSONObject jsonobject)
        {
            super.writeToJson(jsonobject);
            jsonobject.put("monitorId", monitorId);
            jsonobject.put("peerThreadId", peerThreadId);
            jsonobject.put("stack", JSONObject.wrap(stackTrace));
_L1:
            return;
            jsonobject;
            jsonobject.printStackTrace();
              goto _L1
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeLong(monitorId);
            parcel.writeInt(peerThreadId);
            ParcelUtils.writeStringArray(parcel, stackTrace);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SingleConditionAwaken createFromParcel(Parcel parcel)
            {
                SingleConditionAwaken singleconditionawaken = new SingleConditionAwaken();
                singleconditionawaken.readFromParcel(parcel);
                return singleconditionawaken;
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SingleConditionAwaken[] newArray(int i)
            {
                return new SingleConditionAwaken[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private static final String FIELD_MONITOR_ID = "monitorId";
        private static final String FIELD_PEER_THREAD_ID = "peerThreadId";
        private static final String FIELD_STACK = "stack";
        private Object javaBackTrace;
        public long monitorId;
        public int peerThreadId;
        public String stackTrace[];


        public SingleConditionAwaken()
        {
            super(2, "ConditionAwaken");
        }
    }

    public static final class SingleConditionWait extends MicroscopicEvent
    {

        private void copyFrom(SingleConditionWait singleconditionwait)
        {
            super.copyFrom(singleconditionwait);
            monitorId = singleconditionwait.monitorId;
            awakenReason = singleconditionwait.awakenReason;
            stackTrace = singleconditionwait.stackTrace;
            javaBackTrace = singleconditionwait.javaBackTrace;
        }

        public void copyFrom(PerfEvent perfevent)
        {
            copyFrom((SingleConditionWait)perfevent);
        }

        void fillIn(JniParcel jniparcel, Object obj, NativeBackTrace nativebacktrace)
        {
            threadId = (int)jniparcel.readLong();
            threadName = jniparcel.readString();
            beginUptimeMillis = jniparcel.readLong();
            endUptimeMillis = jniparcel.readLong();
            schedPolicy = OsUtils.decodeThreadSchedulePolicy((int)jniparcel.readLong());
            schedPriority = (int)jniparcel.readLong();
            monitorId = jniparcel.readLong();
            awakenReason = (int)jniparcel.readLong();
            javaBackTrace = obj;
        }

        public boolean hasMultiplePeerBlockingEvents()
        {
            return false;
        }

        public boolean hasPeerBlockingEvent()
        {
            boolean flag = false;
            if(awakenReason == 0)
                flag = true;
            return flag;
        }

        public boolean isBlockedBy(MicroscopicEvent microscopicevent)
        {
            boolean flag = true;
            if(!(microscopicevent instanceof SingleConditionAwaken))
                return false;
            microscopicevent = (SingleConditionAwaken)microscopicevent;
            boolean flag1;
            if(((SingleConditionAwaken) (microscopicevent)).pid == pid && ((SingleConditionAwaken) (microscopicevent)).monitorId == monitorId && ((SingleConditionAwaken) (microscopicevent)).beginUptimeMillis <= beginUptimeMillis && ((SingleConditionAwaken) (microscopicevent)).endUptimeMillis > beginUptimeMillis && ((SingleConditionAwaken) (microscopicevent)).endUptimeMillis <= endUptimeMillis)
            {
                flag1 = flag;
                if(((SingleConditionAwaken) (microscopicevent)).peerThreadId != 0)
                    if(((SingleConditionAwaken) (microscopicevent)).peerThreadId == threadId)
                        flag1 = flag;
                    else
                        flag1 = false;
            } else
            {
                flag1 = false;
            }
            return flag1;
        }

        public boolean isBlockedBySameProcess()
        {
            return true;
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
            monitorId = jsonobject.optLong("monitorId", 0L);
            awakenReason = jsonobject.optInt("awakenReason", 0);
            stackTrace = StackUtils.getStackTrace(jsonobject.optJSONArray("stack"));
            javaBackTrace = null;
        }

        public void readFromParcel(Parcel parcel)
        {
            super.readFromParcel(parcel);
            monitorId = parcel.readLong();
            awakenReason = parcel.readInt();
            stackTrace = ParcelUtils.readStringArray(parcel);
            if(stackTrace == null)
                stackTrace = StackUtils.emptyStack;
            javaBackTrace = null;
        }

        public void reset()
        {
            super.reset();
            monitorId = 0L;
            awakenReason = 0;
            stackTrace = StackUtils.emptyStack;
            javaBackTrace = null;
        }

        void resolveLazyInfo()
        {
            if(lazyInfoResolved)
            {
                return;
            } else
            {
                super.resolveLazyInfo();
                stackTrace = StackUtils.getStackTrace(JavaBackTrace.resolve(javaBackTrace), JavaBackTrace.resolveClasses(javaBackTrace), null);
                javaBackTrace = null;
                return;
            }
        }

        public void writeToJson(JSONObject jsonobject)
        {
            super.writeToJson(jsonobject);
            jsonobject.put("monitorId", monitorId);
            jsonobject.put("awakenReason", awakenReason);
            jsonobject.put("stack", JSONObject.wrap(stackTrace));
_L1:
            return;
            jsonobject;
            jsonobject.printStackTrace();
              goto _L1
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeLong(monitorId);
            parcel.writeInt(awakenReason);
            ParcelUtils.writeStringArray(parcel, stackTrace);
        }

        public static final int COMDITION_AWAKEN_INTERRUPTED = 2;
        public static final int COMDITION_AWAKEN_TIMEDOUT = 1;
        public static final int CONDITION_AWAKEN_NOTIFIED = 0;
        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SingleConditionWait createFromParcel(Parcel parcel)
            {
                SingleConditionWait singleconditionwait = new SingleConditionWait();
                singleconditionwait.readFromParcel(parcel);
                return singleconditionwait;
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SingleConditionWait[] newArray(int i)
            {
                return new SingleConditionWait[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private static final String FIELD_AWAKEN_REASON = "awakenReason";
        private static final String FIELD_MONITOR_ID = "monitorId";
        private static final String FIELD_STACK = "stack";
        public int awakenReason;
        private Object javaBackTrace;
        public long monitorId;
        public String stackTrace[];


        public SingleConditionWait()
        {
            super(3, "ConditionWait");
        }
    }

    public static final class SingleLockHold extends MicroscopicEvent
    {

        private void copyFrom(SingleLockHold singlelockhold)
        {
            super.copyFrom(singlelockhold);
            monitorId = singlelockhold.monitorId;
            stackTrace = singlelockhold.stackTrace;
            javaBackTrace = singlelockhold.javaBackTrace;
        }

        public void copyFrom(PerfEvent perfevent)
        {
            copyFrom((SingleLockHold)perfevent);
        }

        void fillIn(JniParcel jniparcel, Object obj, NativeBackTrace nativebacktrace)
        {
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
            monitorId = jniparcel.readLong();
            javaBackTrace = obj;
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
            return true;
        }

        public boolean isBlockingSameProcess()
        {
            return true;
        }

        public boolean isPeerBlockingEvent()
        {
            return true;
        }

        public boolean isRootEvent()
        {
            return false;
        }

        public void readFromJson(JSONObject jsonobject)
        {
            super.readFromJson(jsonobject);
            monitorId = jsonobject.optLong("monitorId", 0L);
            stackTrace = StackUtils.getStackTrace(jsonobject.optJSONArray("stack"));
            javaBackTrace = null;
        }

        public void readFromParcel(Parcel parcel)
        {
            super.readFromParcel(parcel);
            monitorId = parcel.readLong();
            stackTrace = ParcelUtils.readStringArray(parcel);
            if(stackTrace == null)
                stackTrace = StackUtils.emptyStack;
            javaBackTrace = null;
        }

        public void reset()
        {
            super.reset();
            monitorId = 0L;
            stackTrace = StackUtils.emptyStack;
            javaBackTrace = null;
        }

        void resolveLazyInfo()
        {
            if(lazyInfoResolved)
            {
                return;
            } else
            {
                super.resolveLazyInfo();
                stackTrace = StackUtils.getStackTrace(JavaBackTrace.resolve(javaBackTrace), JavaBackTrace.resolveClasses(javaBackTrace), null);
                javaBackTrace = null;
                return;
            }
        }

        public void writeToJson(JSONObject jsonobject)
        {
            super.writeToJson(jsonobject);
            jsonobject.put("monitorId", monitorId);
            jsonobject.put("stack", JSONObject.wrap(stackTrace));
_L1:
            return;
            jsonobject;
            jsonobject.printStackTrace();
              goto _L1
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeLong(monitorId);
            ParcelUtils.writeStringArray(parcel, stackTrace);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SingleLockHold createFromParcel(Parcel parcel)
            {
                SingleLockHold singlelockhold = new SingleLockHold();
                singlelockhold.readFromParcel(parcel);
                return singlelockhold;
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SingleLockHold[] newArray(int i)
            {
                return new SingleLockHold[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private static final String FIELD_MONITOR_ID = "monitorId";
        private static final String FIELD_STACK = "stack";
        private Object javaBackTrace;
        public long monitorId;
        public String stackTrace[];


        public SingleLockHold()
        {
            super(0, "LockHold");
        }
    }

    public static final class SingleLockWait extends MicroscopicEvent
    {

        private void copyFrom(SingleLockWait singlelockwait)
        {
            super.copyFrom(singlelockwait);
            monitorId = singlelockwait.monitorId;
            stackTrace = singlelockwait.stackTrace;
            javaBackTrace = singlelockwait.javaBackTrace;
        }

        public void copyFrom(PerfEvent perfevent)
        {
            copyFrom((SingleLockWait)perfevent);
        }

        void fillIn(JniParcel jniparcel, Object obj, NativeBackTrace nativebacktrace)
        {
            threadId = (int)jniparcel.readLong();
            threadName = jniparcel.readString();
            beginUptimeMillis = jniparcel.readLong();
            endUptimeMillis = jniparcel.readLong();
            schedPolicy = OsUtils.decodeThreadSchedulePolicy((int)jniparcel.readLong());
            schedPriority = (int)jniparcel.readLong();
            monitorId = jniparcel.readLong();
            javaBackTrace = obj;
        }

        public boolean hasMultiplePeerBlockingEvents()
        {
            return true;
        }

        public boolean hasPeerBlockingEvent()
        {
            return true;
        }

        public boolean isBlockedBy(MicroscopicEvent microscopicevent)
        {
            boolean flag = false;
            if(!(microscopicevent instanceof SingleLockHold))
                return false;
            microscopicevent = (SingleLockHold)microscopicevent;
            boolean flag1 = flag;
            if(((SingleLockHold) (microscopicevent)).pid == pid)
            {
                flag1 = flag;
                if(((SingleLockHold) (microscopicevent)).monitorId == monitorId)
                {
                    flag1 = flag;
                    if(((SingleLockHold) (microscopicevent)).endUptimeMillis > beginUptimeMillis)
                    {
                        flag1 = flag;
                        if(((SingleLockHold) (microscopicevent)).endUptimeMillis <= endUptimeMillis)
                            flag1 = true;
                    }
                }
            }
            return flag1;
        }

        public boolean isBlockedBySameProcess()
        {
            return true;
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
            monitorId = jsonobject.optLong("monitorId", 0L);
            stackTrace = StackUtils.getStackTrace(jsonobject.optJSONArray("stack"));
            javaBackTrace = null;
        }

        public void readFromParcel(Parcel parcel)
        {
            super.readFromParcel(parcel);
            monitorId = parcel.readLong();
            stackTrace = ParcelUtils.readStringArray(parcel);
            if(stackTrace == null)
                stackTrace = StackUtils.emptyStack;
            javaBackTrace = null;
        }

        public void reset()
        {
            super.reset();
            monitorId = 0L;
            stackTrace = StackUtils.emptyStack;
            javaBackTrace = null;
        }

        void resolveLazyInfo()
        {
            if(lazyInfoResolved)
            {
                return;
            } else
            {
                super.resolveLazyInfo();
                stackTrace = StackUtils.getStackTrace(JavaBackTrace.resolve(javaBackTrace), JavaBackTrace.resolveClasses(javaBackTrace), null);
                javaBackTrace = null;
                return;
            }
        }

        public void writeToJson(JSONObject jsonobject)
        {
            super.writeToJson(jsonobject);
            jsonobject.put("monitorId", monitorId);
            jsonobject.put("stack", JSONObject.wrap(stackTrace));
_L1:
            return;
            jsonobject;
            jsonobject.printStackTrace();
              goto _L1
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeLong(monitorId);
            ParcelUtils.writeStringArray(parcel, stackTrace);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SingleLockWait createFromParcel(Parcel parcel)
            {
                SingleLockWait singlelockwait = new SingleLockWait();
                singlelockwait.readFromParcel(parcel);
                return singlelockwait;
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SingleLockWait[] newArray(int i)
            {
                return new SingleLockWait[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private static final String FIELD_MONITOR_ID = "monitorId";
        private static final String FIELD_STACK = "stack";
        private Object javaBackTrace;
        public long monitorId;
        public String stackTrace[];


        public SingleLockWait()
        {
            super(1, "LockWait");
        }
    }


    public MonitorSuperviser()
    {
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.os.*;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package android.os.statistics:
//            MacroscopicEvent, JniParcel, PerfEvent, NativeBackTrace, 
//            MicroscopicEvent, OsUtils, StackUtils, ParcelUtils, 
//            JavaBackTrace

public class BinderSuperviser
{
    public static class BinderStarvation extends MacroscopicEvent
    {

        private void copyFrom(BinderStarvation binderstarvation)
        {
            super.copyFrom(binderstarvation);
            maxThreads = binderstarvation.maxThreads;
            starvationTimeMs = binderstarvation.starvationTimeMs;
        }

        public void copyFrom(PerfEvent perfevent)
        {
            copyFrom((BinderStarvation)perfevent);
        }

        void fillIn(JniParcel jniparcel, Object obj, NativeBackTrace nativebacktrace)
        {
            occurUptimeMillis = jniparcel.readLong();
            maxThreads = (int)jniparcel.readLong();
            starvationTimeMs = jniparcel.readLong();
        }

        public void readFromJson(JSONObject jsonobject)
        {
            super.readFromJson(jsonobject);
            maxThreads = jsonobject.optInt("maxThreads", 0);
            starvationTimeMs = jsonobject.optLong("starvationTimeMs", 0L);
        }

        public void readFromParcel(Parcel parcel)
        {
            super.readFromParcel(parcel);
            maxThreads = parcel.readInt();
            starvationTimeMs = parcel.readLong();
        }

        public void reset()
        {
            super.reset();
            maxThreads = 0;
            starvationTimeMs = 0L;
        }

        public void writeToJson(JSONObject jsonobject)
        {
            super.writeToJson(jsonobject);
            jsonobject.put("maxThreads", maxThreads);
            jsonobject.put("starvationTimeMs", starvationTimeMs);
_L1:
            return;
            jsonobject;
            jsonobject.printStackTrace();
              goto _L1
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(maxThreads);
            parcel.writeLong(starvationTimeMs);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public BinderStarvation createFromParcel(Parcel parcel)
            {
                BinderStarvation binderstarvation = new BinderStarvation();
                binderstarvation.readFromParcel(parcel);
                return binderstarvation;
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public BinderStarvation[] newArray(int i)
            {
                return new BinderStarvation[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private static final String FIELD_MAX_THREADS = "maxThreads";
        private static final String FIELD_STARVATION_TIME_MS = "starvationTimeMs";
        public int maxThreads;
        public long starvationTimeMs;


        public BinderStarvation()
        {
            super(0x10004, "BinderStarvation");
        }
    }

    public static class SingleBinderCall extends MicroscopicEvent
    {

        private void copyFrom(SingleBinderCall singlebindercall)
        {
            super.copyFrom(singlebindercall);
            interfaceDescriptor = singlebindercall.interfaceDescriptor;
            code = singlebindercall.code;
            stackTrace = singlebindercall.stackTrace;
            binder = singlebindercall.binder;
            javaBackTrace = singlebindercall.javaBackTrace;
            nativeBackTrace = singlebindercall.nativeBackTrace;
        }

        private static void initClasses()
        {
            if(sBinderProxyClass != null)
                break MISSING_BLOCK_LABEL_14;
            sBinderProxyClass = Class.forName("android.os.BinderProxy");
_L1:
            return;
            ClassNotFoundException classnotfoundexception;
            classnotfoundexception;
            classnotfoundexception.printStackTrace();
              goto _L1
        }

        private static boolean isJavaBinderCall(StackTraceElement astacktraceelement[], Class aclass[])
        {
            boolean flag;
            for(flag = false; astacktraceelement == null || astacktraceelement.length == 0 || aclass == null || aclass.length == 0;)
                return false;

            if(aclass[0] == sBinderProxyClass)
                flag = true;
            return flag;
        }

        public void copyFrom(PerfEvent perfevent)
        {
            copyFrom((SingleBinderCall)perfevent);
        }

        void fillIn(JniParcel jniparcel, Object obj, NativeBackTrace nativebacktrace)
        {
            threadId = (int)jniparcel.readLong();
            threadName = jniparcel.readString();
            beginUptimeMillis = jniparcel.readLong();
            endUptimeMillis = jniparcel.readLong();
            schedPolicy = OsUtils.decodeThreadSchedulePolicy((int)jniparcel.readLong());
            schedPriority = (int)jniparcel.readLong();
            binder = jniparcel.readBinder();
            interfaceDescriptor = jniparcel.readString();
            code = (int)jniparcel.readLong();
            javaBackTrace = obj;
            nativeBackTrace = nativebacktrace;
        }

        public boolean hasMultiplePeerBlockingEvents()
        {
            return false;
        }

        boolean hasNativeStack()
        {
            boolean flag;
            if(nativeBackTrace != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean hasPeerBlockingEvent()
        {
            return true;
        }

        public boolean isBlockedBy(MicroscopicEvent microscopicevent)
        {
            boolean flag = false;
            if(!(microscopicevent instanceof SingleBinderExecution))
                return false;
            microscopicevent = (SingleBinderExecution)microscopicevent;
            boolean flag1 = flag;
            if(((SingleBinderExecution) (microscopicevent)).callingPid == pid)
            {
                flag1 = flag;
                if(((SingleBinderExecution) (microscopicevent)).callingUid == uid)
                {
                    flag1 = flag;
                    if(((SingleBinderExecution) (microscopicevent)).beginUptimeMillis >= beginUptimeMillis)
                    {
                        flag1 = flag;
                        if(((SingleBinderExecution) (microscopicevent)).endUptimeMillis <= endUptimeMillis)
                        {
                            flag1 = flag;
                            if(((SingleBinderExecution) (microscopicevent)).code == code)
                                flag1 = TextUtils.equals(((SingleBinderExecution) (microscopicevent)).interfaceDescriptor, interfaceDescriptor);
                        }
                    }
                }
            }
            return flag1;
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
            interfaceDescriptor = jsonobject.optString("interface", "");
            code = jsonobject.optInt("code", 0);
            stackTrace = StackUtils.getStackTrace(jsonobject.optJSONArray("stack"));
            binder = null;
            javaBackTrace = null;
            nativeBackTrace = null;
        }

        public void readFromParcel(Parcel parcel)
        {
            super.readFromParcel(parcel);
            interfaceDescriptor = parcel.readString();
            if(interfaceDescriptor == null)
                interfaceDescriptor = "";
            code = parcel.readInt();
            stackTrace = ParcelUtils.readStringArray(parcel);
            if(stackTrace == null)
                stackTrace = StackUtils.emptyStack;
            binder = null;
            javaBackTrace = null;
            nativeBackTrace = null;
        }

        public void reset()
        {
            super.reset();
            interfaceDescriptor = "";
            code = 0;
            stackTrace = StackUtils.emptyStack;
            binder = null;
            javaBackTrace = null;
            nativeBackTrace = null;
        }

        void resolveLazyInfo()
        {
            if(lazyInfoResolved)
                return;
            super.resolveLazyInfo();
            initClasses();
            StackTraceElement astacktraceelement[];
            Class aclass[];
            if(binder != null)
                try
                {
                    interfaceDescriptor = binder.getInterfaceDescriptor();
                }
                catch(RemoteException remoteexception) { }
            if(interfaceDescriptor == null)
                interfaceDescriptor = "";
            astacktraceelement = JavaBackTrace.resolve(javaBackTrace);
            aclass = JavaBackTrace.resolveClasses(javaBackTrace);
            if(isJavaBinderCall(astacktraceelement, aclass))
            {
                for(int i = 0; i < aclass.length; i++)
                    if(aclass[i] == sBinderProxyClass)
                    {
                        aclass[i] = null;
                        astacktraceelement[i] = null;
                    }

                stackTrace = StackUtils.getStackTrace(astacktraceelement, aclass, null);
            } else
            {
                String as[] = NativeBackTrace.resolve(nativeBackTrace);
                if(as != null)
                {
                    int j = 0;
                    do
                    {
                        if(j >= as.length)
                            break;
                        String s = as[j];
                        if(s == null || !s.contains("BinderSuperviser") && !s.contains("libbinder.so"))
                            break;
                        as[j] = null;
                        j++;
                    } while(true);
                }
                stackTrace = StackUtils.getStackTrace(astacktraceelement, aclass, as);
            }
            binder = null;
            javaBackTrace = null;
            if(nativeBackTrace != null)
                nativeBackTrace.dispose();
            nativeBackTrace = null;
        }

        public void writeToJson(JSONObject jsonobject)
        {
            super.writeToJson(jsonobject);
            jsonobject.put("interface", interfaceDescriptor);
            jsonobject.put("code", code);
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
            parcel.writeString(interfaceDescriptor);
            parcel.writeInt(code);
            ParcelUtils.writeStringArray(parcel, stackTrace);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SingleBinderCall createFromParcel(Parcel parcel)
            {
                SingleBinderCall singlebindercall = new SingleBinderCall();
                singlebindercall.readFromParcel(parcel);
                return singlebindercall;
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SingleBinderCall[] newArray(int i)
            {
                return new SingleBinderCall[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private static final String FIELD_CODE = "code";
        private static final String FIELD_INTERFACE_DESCRIPTOR = "interface";
        private static final String FIELD_STACK = "stack";
        private static Class sBinderProxyClass;
        private IBinder binder;
        public int code;
        public String interfaceDescriptor;
        private Object javaBackTrace;
        private NativeBackTrace nativeBackTrace;
        public String stackTrace[];


        public SingleBinderCall()
        {
            super(4, "BinderCall");
        }
    }

    public static class SingleBinderExecution extends MicroscopicEvent
    {

        private void copyFrom(SingleBinderExecution singlebinderexecution)
        {
            super.copyFrom(singlebinderexecution);
            interfaceDescriptor = singlebinderexecution.interfaceDescriptor;
            code = singlebinderexecution.code;
            callingPid = singlebinderexecution.callingPid;
            callingUid = singlebinderexecution.callingUid;
        }

        public void copyFrom(PerfEvent perfevent)
        {
            copyFrom((SingleBinderExecution)perfevent);
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
            endRealTimeMs = jniparcel.readLong();
            obj = jniparcel.readBinder();
            interfaceDescriptor = jniparcel.readString();
            if(obj != null)
                try
                {
                    interfaceDescriptor = ((IBinder) (obj)).getInterfaceDescriptor();
                }
                // Misplaced declaration of an exception variable
                catch(Object obj) { }
            if(interfaceDescriptor == null)
                interfaceDescriptor = "";
            code = (int)jniparcel.readLong();
            callingPid = (int)jniparcel.readLong();
            callingUid = (int)jniparcel.readLong();
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
            return true;
        }

        public boolean isRootEvent()
        {
            return true;
        }

        public void readFromJson(JSONObject jsonobject)
        {
            super.readFromJson(jsonobject);
            interfaceDescriptor = jsonobject.optString("interface", "");
            code = jsonobject.optInt("code", 0);
            callingPid = jsonobject.optInt("callingPid", 0);
            callingUid = jsonobject.optInt("callingUid", 0);
        }

        public void readFromParcel(Parcel parcel)
        {
            super.readFromParcel(parcel);
            interfaceDescriptor = parcel.readString();
            if(interfaceDescriptor == null)
                interfaceDescriptor = "";
            code = parcel.readInt();
            callingPid = parcel.readInt();
            callingUid = parcel.readInt();
        }

        public void reset()
        {
            super.reset();
            interfaceDescriptor = "";
            code = 0;
            callingPid = 0;
            callingUid = 0;
        }

        public void writeToJson(JSONObject jsonobject)
        {
            super.writeToJson(jsonobject);
            jsonobject.put("interface", interfaceDescriptor);
            jsonobject.put("code", code);
            jsonobject.put("callingPid", callingPid);
            jsonobject.put("callingUid", callingUid);
_L1:
            return;
            jsonobject;
            jsonobject.printStackTrace();
              goto _L1
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeString(interfaceDescriptor);
            parcel.writeInt(code);
            parcel.writeInt(callingPid);
            parcel.writeInt(callingUid);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SingleBinderExecution createFromParcel(Parcel parcel)
            {
                SingleBinderExecution singlebinderexecution = new SingleBinderExecution();
                singlebinderexecution.readFromParcel(parcel);
                return singlebinderexecution;
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SingleBinderExecution[] newArray(int i)
            {
                return new SingleBinderExecution[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private static final String FIELD_CALLING_PID = "callingPid";
        private static final String FIELD_CALLING_UID = "callingUid";
        private static final String FIELD_CODE = "code";
        private static final String FIELD_INTERFACE_DESCRIPTOR = "interface";
        public int callingPid;
        public int callingUid;
        public int code;
        public String interfaceDescriptor;


        public SingleBinderExecution()
        {
            super(5, "BinderExecution");
            eventFlags = 2;
        }
    }


    public BinderSuperviser()
    {
    }
}

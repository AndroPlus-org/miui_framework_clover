// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package android.os.statistics:
//            MicroscopicEvent, StackUtils, JniParcel, ParcelUtils, 
//            JavaBackTrace, PerfEvent, NativeBackTrace

public final class SingleJniMethod extends MicroscopicEvent
{

    public SingleJniMethod()
    {
        super(10, "JniMethod");
    }

    private static String[] buildStackTrace(StackTraceElement astacktraceelement[], Class aclass[])
    {
        if(!exceptionalClassMethodSimpleNames.isEmpty()) goto _L2; else goto _L1
_L1:
        HashMap hashmap = exceptionalClassMethodSimpleNames;
        hashmap;
        JVM INSTR monitorenter ;
        String as[] = exceptionalJniMethodFullNames;
        int i = 0;
        int j = as.length;
_L3:
        Object obj;
        if(i >= j)
            break; /* Loop/switch isn't completed */
        obj = as[i];
        String s;
        Class class1;
        ArrayList arraylist;
        int k = ((String) (obj)).lastIndexOf(".");
        s = ((String) (obj)).substring(k + 1);
        class1 = Class.forName(((String) (obj)).substring(0, k));
        arraylist = (ArrayList)exceptionalClassMethodSimpleNames.get(class1);
        obj = arraylist;
        if(arraylist != null)
            break MISSING_BLOCK_LABEL_115;
        obj = JVM INSTR new #86  <Class ArrayList>;
        ((ArrayList) (obj)).ArrayList();
        exceptionalClassMethodSimpleNames.put(class1, obj);
        ((ArrayList) (obj)).add(s);
_L4:
        i++;
        if(true) goto _L3; else goto _L2
        astacktraceelement;
        throw astacktraceelement;
_L2:
        if(astacktraceelement == null || astacktraceelement.length == 0 || aclass == null || aclass.length == 0)
            return StackUtils.emptyStack;
        StackTraceElement stacktraceelement = astacktraceelement[0];
        Object obj1 = aclass[0];
        if(stacktraceelement.getClassName().startsWith("android.os.statistics."))
            return StackUtils.emptyStack;
        obj1 = (ArrayList)exceptionalClassMethodSimpleNames.get(obj1);
        if(obj1 != null && ((ArrayList) (obj1)).contains(stacktraceelement.getMethodName()))
            return StackUtils.emptyStack;
        else
            return StackUtils.getStackTrace(astacktraceelement, aclass, null);
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
          goto _L4
    }

    private void copyFrom(SingleJniMethod singlejnimethod)
    {
        super.copyFrom(singlejnimethod);
        stackTrace = singlejnimethod.stackTrace;
        javaBackTrace = singlejnimethod.javaBackTrace;
    }

    public void copyFrom(PerfEvent perfevent)
    {
        copyFrom((SingleJniMethod)perfevent);
    }

    void fillIn(JniParcel jniparcel, Object obj, NativeBackTrace nativebacktrace)
    {
        threadId = (int)jniparcel.readLong();
        beginUptimeMillis = jniparcel.readLong();
        endUptimeMillis = jniparcel.readLong();
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
        return false;
    }

    public boolean isBlockingSameProcess()
    {
        return false;
    }

    boolean isMeaningful()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(stackTrace != null)
        {
            flag1 = flag;
            if(stackTrace.length > 0)
                flag1 = true;
        }
        return flag1;
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
        stackTrace = StackUtils.getStackTrace(jsonobject.optJSONArray("stack"));
        javaBackTrace = null;
    }

    public void readFromParcel(Parcel parcel)
    {
        super.readFromParcel(parcel);
        stackTrace = ParcelUtils.readStringArray(parcel);
        if(stackTrace == null)
            stackTrace = StackUtils.emptyStack;
        javaBackTrace = null;
    }

    public void reset()
    {
        super.reset();
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
            stackTrace = buildStackTrace(JavaBackTrace.resolve(javaBackTrace), JavaBackTrace.resolveClasses(javaBackTrace));
            javaBackTrace = null;
            return;
        }
    }

    public void writeToJson(JSONObject jsonobject)
    {
        super.writeToJson(jsonobject);
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
        ParcelUtils.writeStringArray(parcel, stackTrace);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SingleJniMethod createFromParcel(Parcel parcel)
        {
            SingleJniMethod singlejnimethod = new SingleJniMethod();
            singlejnimethod.readFromParcel(parcel);
            return singlejnimethod;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SingleJniMethod[] newArray(int i)
        {
            return new SingleJniMethod[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String FIELD_STACK = "stack";
    private static final HashMap exceptionalClassMethodSimpleNames = new HashMap();
    private static final String exceptionalJniMethodFullNames[] = {
        "java.lang.Object.wait", "java.lang.Object.notify", "java.lang.Object.notifyAll", "java.lang.Thread.sleep", "android.os.BinderProxy.transactNative"
    };
    private Object javaBackTrace;
    public String stackTrace[];

}

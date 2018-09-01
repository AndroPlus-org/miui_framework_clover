// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.app.ActivityThread;
import android.os.*;
import android.text.TextUtils;
import java.io.*;
import java.util.Comparator;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package android.os.statistics:
//            PerfSuperviser, JniParcel, NativeBackTrace, MacroscopicEvent, 
//            MicroscopicEvent

public abstract class PerfEvent
    implements Parcelable
{
    private static class EndTimeComparator
        implements Comparator
    {

        public int compare(PerfEvent perfevent, PerfEvent perfevent1)
        {
            int i = Long.compare(perfevent.getEndUptimeMillis(), perfevent1.getEndUptimeMillis());
            if(i != 0)
                return i;
            i = Long.compare(perfevent1.getBeginUptimeMillis(), perfevent.getBeginUptimeMillis());
            if(i != 0)
                return i;
            if(perfevent instanceof MacroscopicEvent)
                return -1;
            if(perfevent1 instanceof MacroscopicEvent)
                return 1;
            perfevent = (MicroscopicEvent)perfevent;
            perfevent1 = (MicroscopicEvent)perfevent1;
            i = Boolean.compare(perfevent.isRootEvent(), perfevent1.isRootEvent());
            if(i != 0)
                return i;
            else
                return Boolean.compare(perfevent.isMasterEvent(), perfevent1.isMasterEvent());
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((PerfEvent)obj, (PerfEvent)obj1);
        }

        private EndTimeComparator()
        {
        }

        EndTimeComparator(EndTimeComparator endtimecomparator)
        {
            this();
        }
    }


    public PerfEvent(int i, String s)
    {
        eventType = i;
        eventTypeName = s;
    }

    public static int getPerfEventType(JSONObject jsonobject)
    {
        return jsonobject.optInt("eventType", -1);
    }

    public void copyFrom(PerfEvent perfevent)
    {
        pid = perfevent.pid;
        uid = perfevent.uid;
        processName = perfevent.processName;
        packageName = perfevent.packageName;
        lazyInfoResolved = perfevent.lazyInfoResolved;
    }

    public int describeContents()
    {
        return 0;
    }

    abstract void fillIn(JniParcel jniparcel, Object obj, NativeBackTrace nativebacktrace);

    final void fillInCurrentProcessId()
    {
        if(occursInCurrentProcess())
            pid = PerfSuperviser.MY_PID;
    }

    final void fillInCurrentProcessInfo()
    {
        if(!occursInCurrentProcess())
            return;
        pid = PerfSuperviser.MY_PID;
        if(MY_UID == -1)
            MY_UID = Process.myUid();
        uid = MY_UID;
        processName = ActivityThread.currentProcessName();
        if(!TextUtils.isEmpty(processName)) goto _L2; else goto _L1
_L1:
        if(processNameFromCmdlineReady) goto _L4; else goto _L3
_L3:
        Object obj;
        Object obj2;
        Object obj3;
        obj = null;
        obj2 = null;
        obj3 = obj;
        Object obj4 = JVM INSTR new #119 <Class BufferedReader>;
        obj3 = obj;
        InputStreamReader inputstreamreader = JVM INSTR new #121 <Class InputStreamReader>;
        obj3 = obj;
        FileInputStream fileinputstream = JVM INSTR new #123 <Class FileInputStream>;
        obj3 = obj;
        fileinputstream.FileInputStream("/proc/self/cmdline");
        obj3 = obj;
        inputstreamreader.InputStreamReader(fileinputstream);
        obj3 = obj;
        ((BufferedReader) (obj4)).BufferedReader(inputstreamreader);
        processNameFromCmdline = ((BufferedReader) (obj4)).readLine();
        if(!TextUtils.isEmpty(processNameFromCmdline))
            processNameFromCmdline = processNameFromCmdline.replace('\0', ' ').trim();
        try
        {
            ((BufferedReader) (obj4)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj3) { }
_L5:
        if(!TextUtils.isEmpty(processNameFromCmdline) && processNameFromCmdline.startsWith("<") ^ true && processNameFromCmdline.startsWith("zygote") ^ true)
            processNameFromCmdlineReady = true;
_L4:
        processName = processNameFromCmdline;
_L2:
        if(processName == null)
            processName = "";
        packageName = ActivityThread.currentPackageName();
        if(packageName == null)
            packageName = "";
        return;
        Object obj1;
        obj1;
        obj4 = obj2;
_L8:
        obj3 = obj4;
        ((Exception) (obj1)).printStackTrace();
        try
        {
            ((BufferedReader) (obj4)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj3) { }
          goto _L5
        obj4;
        obj1 = obj3;
_L7:
        try
        {
            ((BufferedReader) (obj1)).close();
        }
        catch(Exception exception) { }
        throw obj4;
        Exception exception1;
        exception1;
        obj1 = obj4;
        obj4 = exception1;
        if(true) goto _L7; else goto _L6
_L6:
        obj1;
          goto _L8
    }

    public abstract long getBeginUptimeMillis();

    public abstract long getEndUptimeMillis();

    boolean isConcerned()
    {
        return true;
    }

    boolean isMeaningful()
    {
        return true;
    }

    boolean isUserPerceptible()
    {
        return false;
    }

    boolean occursInCurrentProcess()
    {
        return true;
    }

    public void readFromJson(JSONObject jsonobject)
    {
        pid = jsonobject.optInt("pid", 0);
        uid = jsonobject.optInt("uid", 0);
        processName = jsonobject.optString("processName", "");
        packageName = jsonobject.optString("packageName", "");
        lazyInfoResolved = true;
    }

    public void readFromParcel(Parcel parcel)
    {
        pid = parcel.readInt();
        uid = parcel.readInt();
        processName = parcel.readString();
        if(processName == null)
            processName = "";
        packageName = parcel.readString();
        if(packageName == null)
            packageName = "";
        lazyInfoResolved = true;
    }

    public void reset()
    {
        pid = 0;
        uid = 0;
        processName = "";
        packageName = "";
        lazyInfoResolved = false;
    }

    void resolveLazyInfo()
    {
        lazyInfoResolved = true;
    }

    public final JSONObject toJson()
    {
        JSONObject jsonobject = new JSONObject();
        writeToJson(jsonobject);
        return jsonobject;
    }

    public void writeToJson(JSONObject jsonobject)
    {
        jsonobject.put("eventType", eventType);
        jsonobject.put("eventTypeName", eventTypeName);
        jsonobject.put("pid", pid);
        jsonobject.put("uid", uid);
        jsonobject.put("processName", processName);
        jsonobject.put("packageName", packageName);
_L1:
        return;
        jsonobject;
        jsonobject.printStackTrace();
          goto _L1
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(pid);
        parcel.writeInt(uid);
        parcel.writeString(processName);
        parcel.writeString(packageName);
    }

    public static final String FIELD_CURRENT_PACKAGE_NAME = "packageName";
    public static final String FIELD_CURRENT_PID = "pid";
    public static final String FIELD_CURRENT_PROCESS_NAME = "processName";
    public static final String FIELD_CURRENT_UID = "uid";
    public static final String FIELD_EVENT_SEQ = "seq";
    public static final String FIELD_EVENT_TYPE = "eventType";
    public static final String FIELD_EVENT_TYPE_NAME = "eventTypeName";
    private static int MY_UID = 0;
    public static final int TYPE_UNKNOWN = -1;
    public static final Comparator endUptimeComparator = new EndTimeComparator(null);
    private static String processNameFromCmdline;
    private static boolean processNameFromCmdlineReady;
    public final int eventType;
    public final String eventTypeName;
    protected boolean lazyInfoResolved;
    public String packageName;
    public int pid;
    public String processName;
    public int uid;

    static 
    {
        MY_UID = -1;
    }
}

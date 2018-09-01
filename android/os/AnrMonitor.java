// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.app.ActivityThread;
import android.app.Application;
import android.content.Context;
import android.content.pm.*;
import android.os.statistics.MicroscopicEvent;
import android.os.statistics.PerfEvent;
import android.os.statistics.PerfSupervisionSettings;
import android.os.statistics.SingleJniMethod;
import android.text.TextUtils;
import android.util.*;
import android.view.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import miui.mqsas.sdk.MQSEventManagerDelegate;
import miui.os.Build;
import org.json.*;

// Referenced classes of package android.os:
//            SystemProperties, Message, Handler, Debug, 
//            SystemClock, SELinux, FileUtils, RemoteException, 
//            ServiceManager, IBinder, HandlerThread, BaseLooper, 
//            Process, Bundle, Looper

public class AnrMonitor
{
    public static class FileInfo
        implements Comparable
    {

        public int compareTo(long l, long l1)
        {
            byte byte0;
            if(l < l1)
                byte0 = -1;
            else
            if(l == l1)
                byte0 = 0;
            else
                byte0 = 1;
            return byte0;
        }

        public int compareTo(FileInfo fileinfo)
        {
            return compareTo(fileinfo.getModifiedTime(), mModifiedTime);
        }

        public volatile int compareTo(Object obj)
        {
            return compareTo((FileInfo)obj);
        }

        public File getFile()
        {
            return mFile;
        }

        public long getModifiedTime()
        {
            return mModifiedTime;
        }

        private File mFile;
        private long mModifiedTime;

        public FileInfo(File file, long l)
        {
            mFile = file;
            mModifiedTime = l;
        }
    }

    public static class TimerThread extends Thread
    {

        public void finishRun()
        {
            this;
            JVM INSTR monitorenter ;
            completed = true;
            notify();
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void run()
        {
            super.run();
            finishRun();
        }

        public void startAndWait()
        {
            this;
            JVM INSTR monitorenter ;
            start();
            wait(timeout);
            if(!completed)
                interrupt();
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
            Object obj;
            obj;
            ((InterruptedException) (obj)).printStackTrace();
              goto _L1
            obj;
            throw obj;
        }

        private boolean completed;
        private long timeout;

        public TimerThread(long l)
        {
            timeout = l;
        }
    }

    public static class UploadInfo
    {

        static String _2D_wrap0(UploadInfo auploadinfo[])
        {
            return createJsonString(auploadinfo);
        }

        private static String createJsonString(UploadInfo auploadinfo[])
        {
            int i = 0;
            JSONObject jsonobject;
            JSONObject jsonobject2;
            JSONArray jsonarray;
            int j;
            UploadInfo uploadinfo;
            try
            {
                jsonobject = JVM INSTR new #51  <Class JSONObject>;
                jsonobject.JSONObject();
                JSONObject jsonobject1 = JVM INSTR new #51  <Class JSONObject>;
                jsonobject1.JSONObject();
                jsonobject1.put("processName", AnrMonitor.currentProcessName());
                jsonobject1.put("packageName", AnrMonitor.currentPackageName());
                jsonobject1.put("versionName", AnrMonitor.currentVersionName());
                jsonobject1.put("versionCode", AnrMonitor.currentVersionCode());
                jsonobject.put("baseInfo", jsonobject1);
                jsonarray = JVM INSTR new #75  <Class JSONArray>;
                jsonarray.JSONArray();
            }
            // Misplaced declaration of an exception variable
            catch(UploadInfo auploadinfo[])
            {
                auploadinfo.printStackTrace();
                return "";
            }
            if(auploadinfo == null) goto _L2; else goto _L1
_L1:
            if(auploadinfo.length <= 0) goto _L2; else goto _L3
_L3:
            j = auploadinfo.length;
_L4:
            if(i >= j)
                break; /* Loop/switch isn't completed */
            uploadinfo = auploadinfo[i];
            jsonobject2 = JVM INSTR new #51  <Class JSONObject>;
            jsonobject2.JSONObject();
            jsonobject2.put("msg", uploadinfo.info);
            jsonobject2.put("threadName", uploadinfo.threadName);
            jsonobject2.put("tookTime", uploadinfo.tookTime);
            jsonarray.put(jsonobject2);
            i++;
            if(true) goto _L4; else goto _L2
_L2:
            jsonobject.put("msgs", jsonarray);
            if(AnrMonitor.DBG)
            {
                auploadinfo = JVM INSTR new #94  <Class StringBuilder>;
                auploadinfo.StringBuilder();
                Slog.d("AnrMonitor", auploadinfo.append("createJsonString ").append(jsonobject.toString()).toString());
            }
            auploadinfo = jsonobject.toString();
            return auploadinfo;
        }

        public static String getBaseInfo(String s)
        {
            try
            {
                JSONObject jsonobject = JVM INSTR new #51  <Class JSONObject>;
                jsonobject.JSONObject(s);
                s = jsonobject.getJSONObject("baseInfo").toString();
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                s.printStackTrace();
                return null;
            }
            return s;
        }

        public static UploadInfo[] getInfoArray(String s)
        {
            Object obj;
            Object obj1;
            String s1;
            String s2;
            int i;
            UploadInfo auploadinfo[];
            int j;
            Object obj2;
            UploadInfo uploadinfo;
            try
            {
                obj = JVM INSTR new #51  <Class JSONObject>;
                ((JSONObject) (obj)).JSONObject(s);
                s = ((JSONObject) (obj)).getJSONArray("msgs");
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                s.printStackTrace();
                return null;
            }
            if(s == null)
                return null;
            obj1 = ((JSONObject) (obj)).getJSONObject("baseInfo");
            s1 = ((JSONObject) (obj1)).optString("processName");
            s2 = ((JSONObject) (obj1)).optString("packageName");
            obj = ((JSONObject) (obj1)).optString("versionName");
            obj1 = ((JSONObject) (obj1)).optString("versionCode");
            i = s.length();
            auploadinfo = new UploadInfo[i];
            j = 0;
_L3:
            if(j >= i) goto _L2; else goto _L1
_L1:
            obj2 = s.getJSONObject(j);
            uploadinfo = JVM INSTR new #2   <Class AnrMonitor$UploadInfo>;
            uploadinfo.UploadInfo();
            uploadinfo.info = ((JSONObject) (obj2)).optString("msg");
            uploadinfo.processName = s1;
            uploadinfo.packageName = s2;
            uploadinfo.versionName = ((String) (obj));
            uploadinfo.versionCode = ((String) (obj1));
            uploadinfo.threadName = ((JSONObject) (obj2)).optString("threadName");
            uploadinfo.tookTime = ((JSONObject) (obj2)).optLong("tookTime");
            auploadinfo[j] = uploadinfo;
            if(AnrMonitor.DBG)
            {
                obj2 = JVM INSTR new #94  <Class StringBuilder>;
                ((StringBuilder) (obj2)).StringBuilder();
                Slog.d("AnrMonitor", ((StringBuilder) (obj2)).append("getInfoArray i ").append(j).append(" info : ").append(uploadinfo).toString());
            }
            j++;
              goto _L3
_L2:
            return auploadinfo;
        }

        public static UploadInfo getInfoByJsonString(String s)
        {
            Object obj;
            try
            {
                JSONObject jsonobject = JVM INSTR new #51  <Class JSONObject>;
                jsonobject.JSONObject(s);
                obj = jsonobject.getJSONObject("baseInfo");
                String s1 = ((JSONObject) (obj)).optString("processName");
                s = ((JSONObject) (obj)).optString("packageName");
                String s2 = ((JSONObject) (obj)).optString("versionName");
                String s3 = ((JSONObject) (obj)).optString("versionCode");
                obj = JVM INSTR new #2   <Class AnrMonitor$UploadInfo>;
                ((UploadInfo) (obj)).UploadInfo();
                obj.info = jsonobject.optString("msg");
                obj.processName = s1;
                obj.packageName = s;
                obj.versionName = s2;
                obj.versionCode = s3;
                obj.threadName = jsonobject.optString("threadName");
                obj.tookTime = jsonobject.optLong("tookTime");
                if(AnrMonitor.DBG)
                {
                    s = JVM INSTR new #94  <Class StringBuilder>;
                    s.StringBuilder();
                    Slog.d("AnrMonitor", s.append("getInfo info : ").append(obj).toString());
                }
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                s.printStackTrace();
                return null;
            }
            return ((UploadInfo) (obj));
        }

        public static String getMatchTag(UploadInfo uploadinfo)
        {
            try
            {
                uploadinfo = getSaveContent(uploadinfo);
                uploadinfo = uploadinfo.substring(0, uploadinfo.lastIndexOf(","));
            }
            // Misplaced declaration of an exception variable
            catch(UploadInfo uploadinfo)
            {
                uploadinfo.printStackTrace();
                return null;
            }
            return uploadinfo;
        }

        public static String getSaveContent(UploadInfo uploadinfo)
        {
            try
            {
                JSONObject jsonobject = JVM INSTR new #51  <Class JSONObject>;
                jsonobject.JSONObject();
                JSONObject jsonobject1 = JVM INSTR new #51  <Class JSONObject>;
                jsonobject1.JSONObject();
                jsonobject1.put("processName", uploadinfo.processName);
                jsonobject1.put("packageName", uploadinfo.packageName);
                jsonobject1.put("versionName", uploadinfo.versionName);
                jsonobject1.put("versionCode", uploadinfo.versionCode);
                jsonobject.put("baseInfo", jsonobject1);
                jsonobject.put("msg", uploadinfo.info);
                jsonobject.put("threadName", uploadinfo.threadName);
                jsonobject.put("tookTime", uploadinfo.tookTime);
                uploadinfo = jsonobject.toString();
            }
            // Misplaced declaration of an exception variable
            catch(UploadInfo uploadinfo)
            {
                uploadinfo.printStackTrace();
                return null;
            }
            return uploadinfo;
        }

        public String getInfo()
        {
            return info;
        }

        public String getKeyWord()
        {
            Object obj;
            try
            {
                obj = JVM INSTR new #51  <Class JSONObject>;
                ((JSONObject) (obj)).JSONObject();
                ((JSONObject) (obj)).put("tookTime", getTookTime());
                obj = ((JSONObject) (obj)).toString();
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
                return "";
            }
            return ((String) (obj));
        }

        public String getPackageName()
        {
            return packageName;
        }

        public String getProcessName()
        {
            return processName;
        }

        public String getThreadName()
        {
            return threadName;
        }

        public long getTookTime()
        {
            return tookTime;
        }

        public String getVersionCode()
        {
            return versionCode;
        }

        public String getVersionName()
        {
            return versionName;
        }

        public String toString()
        {
            return (new StringBuilder()).append("info : ").append(info).append(" processName : ").append(processName).append(" versionName ").append(versionName).append(" versionCode ").append(versionCode).append(" tookTime : ").append(tookTime).toString();
        }

        private static final String JSON_BASE_INFO_TAG = "baseInfo";
        private static final String JSON_INFO_ARRAY_TAG = "msgs";
        private static final String JSON_INFO_TAG = "msg";
        private static final String JSON_PACKAGE_NAME_TAG = "packageName";
        private static final String JSON_PROCESS_NAME_TAG = "processName";
        private static final String JSON_THREAD_NAME_TAG = "threadName";
        private static final String JSON_TOOKTIME_TAG = "tookTime";
        private static final String JSON_VERSION_CODE_TAG = "versionCode";
        private static final String JSON_VERSION_NAME_TAG = "versionName";
        String info;
        String packageName;
        String processName;
        String threadName;
        long tookTime;
        String versionCode;
        String versionName;

        public UploadInfo()
        {
        }
    }

    private static final class WorkHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 2: default 28
        //                       1 29
        //                       2 239;
               goto _L1 _L2 _L3
_L1:
            return;
_L2:
            message = message.getData();
            if(message != null && AnrMonitor._2D_wrap0() && AnrMonitor.isSystemServer() ^ true)
            {
                String s = message.getString("monitor_msg", "no monitor message");
                try
                {
                    JSONObject jsonobject = JVM INSTR new #50  <Class JSONObject>;
                    jsonobject.JSONObject();
                    jsonobject.put("dump_service", "activity");
                    message = JVM INSTR new #63  <Class JSONArray>;
                    message.JSONArray();
                    Object obj = JVM INSTR new #50  <Class JSONObject>;
                    ((JSONObject) (obj)).JSONObject();
                    ((JSONObject) (obj)).put("opt", "dump-app-trace");
                    message.put(obj);
                    JSONObject jsonobject1 = JVM INSTR new #50  <Class JSONObject>;
                    jsonobject1.JSONObject();
                    obj = JVM INSTR new #73  <Class StringBuilder>;
                    ((StringBuilder) (obj)).StringBuilder();
                    jsonobject1.put("opt", ((StringBuilder) (obj)).append(Process.myPid()).append(",").append(AnrMonitor.currentProcessName()).append(",").append(AnrMonitor.currentPackageName()).append(",").append("Event Time : ").append(AnrMonitor.toCalendarTime(System.currentTimeMillis())).append("\n").append(s).toString());
                    message.put(jsonobject1);
                    jsonobject.put("args", message);
                    MQSEventManagerDelegate.getInstance().reportSimpleEvent(-1, jsonobject.toString());
                }
                // Misplaced declaration of an exception variable
                catch(Message message)
                {
                    message.printStackTrace();
                }
            }
            continue; /* Loop/switch isn't completed */
_L3:
            try
            {
                message = (String)message.obj;
                if(!TextUtils.isEmpty(message) && AnrMonitor._2D_wrap0())
                    MQSEventManagerDelegate.getInstance().reportSimpleEvent(3, message);
            }
            // Misplaced declaration of an exception variable
            catch(Message message)
            {
                Log.e("AnrMonitor", "report record error.", message);
            }
            if(true) goto _L1; else goto _L4
_L4:
        }

        static final int MONITOR_EXECUTION_TIMEOUT_MSG = 1;
        static final int REPORT_RECORDS = 2;

        public WorkHandler(Looper looper)
        {
            super(looper, null);
        }
    }


    static boolean _2D_wrap0()
    {
        return isSystemBootCompleted();
    }

    public AnrMonitor()
    {
    }

    private static void addBinderCallTimeToHistory(long l)
    {
        android/os/AnrMonitor;
        JVM INSTR monitorenter ;
        UploadInfo uploadinfo;
        UploadInfo auploadinfo[];
        int i;
        uploadinfo = JVM INSTR new #12  <Class AnrMonitor$UploadInfo>;
        uploadinfo.UploadInfo();
        StringBuilder stringbuilder = JVM INSTR new #232 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        stringbuilder.append("The binder call ").append(binderCallToString());
        uploadinfo.info = stringbuilder.toString();
        uploadinfo.tookTime = l;
        uploadinfo.threadName = currentThreadName();
        auploadinfo = sUploadBinderRecords;
        i = sBinderRecordIndex;
        sBinderRecordIndex = i + 1;
        auploadinfo[i] = uploadinfo;
        if(sBinderRecordIndex >= sUploadBinderRecords.length)
        {
            String s = UploadInfo._2D_wrap0(sUploadBinderRecords);
            if(!TextUtils.isEmpty(s))
            {
                Message message = Message.obtain();
                message.what = 2;
                message.obj = s;
                if(MONITOR_MSG_EXECUTION)
                {
                    StringBuilder stringbuilder1 = JVM INSTR new #232 <Class StringBuilder>;
                    stringbuilder1.StringBuilder();
                    Log.d("AnrMonitor", stringbuilder1.append("binder call report content : ").append(s).toString());
                }
                getWorkHandler().sendMessageAtFrontOfQueue(message);
            }
            sBinderRecordIndex = 0;
        }
        android/os/AnrMonitor;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static void addInputToHistory(InputEvent inputevent, InputEventReceiver inputeventreceiver, InputChannel inputchannel, long l)
    {
        android/os/AnrMonitor;
        JVM INSTR monitorenter ;
        UploadInfo uploadinfo;
        int i;
        uploadinfo = JVM INSTR new #12  <Class AnrMonitor$UploadInfo>;
        uploadinfo.UploadInfo();
        StringBuilder stringbuilder = JVM INSTR new #232 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        stringbuilder.append("The inputEvent ").append(inputEventToString(inputevent)).append(" {");
        stringbuilder.append(" target reveicer = ");
        stringbuilder.append(inputeventreceiver.getClass().getName());
        stringbuilder.append(" target InputChannel = ");
        stringbuilder.append(inputChannelToString(inputchannel));
        stringbuilder.append("}");
        uploadinfo.info = stringbuilder.toString();
        uploadinfo.tookTime = l;
        inputevent = sUploadInputRecords;
        i = sInputRecordIndex;
        sInputRecordIndex = i + 1;
        inputevent[i] = uploadinfo;
        if(sInputRecordIndex >= sUploadInputRecords.length)
        {
            inputchannel = UploadInfo._2D_wrap0(sUploadInputRecords);
            if(!TextUtils.isEmpty(inputchannel))
            {
                inputeventreceiver = Message.obtain();
                inputeventreceiver.what = 2;
                inputeventreceiver.obj = inputchannel;
                if(MONITOR_MSG_EXECUTION)
                {
                    inputevent = JVM INSTR new #232 <Class StringBuilder>;
                    inputevent.StringBuilder();
                    Log.d("AnrMonitor", inputevent.append("report content : ").append(inputchannel).toString());
                }
                getWorkHandler().sendMessageAtFrontOfQueue(inputeventreceiver);
            }
            sInputRecordIndex = 0;
        }
        android/os/AnrMonitor;
        JVM INSTR monitorexit ;
        return;
        inputevent;
        throw inputevent;
    }

    private static void addLockWaitTimeToHistory(android.os.statistics.MonitorSuperviser.SingleLockWait singlelockwait, int i, long l, long l1, android.os.statistics.MonitorSuperviser.SingleLockHold singlelockhold)
    {
        android/os/AnrMonitor;
        JVM INSTR monitorenter ;
    }

    private static void addMessageToHistory(Message message, BaseLooper.MessageMonitorInfo messagemonitorinfo)
    {
        android/os/AnrMonitor;
        JVM INSTR monitorenter ;
        if(message.target == null) goto _L2; else goto _L1
_L1:
        UploadInfo uploadinfo;
        StringBuilder stringbuilder;
        uploadinfo = JVM INSTR new #12  <Class AnrMonitor$UploadInfo>;
        uploadinfo.UploadInfo();
        stringbuilder = JVM INSTR new #232 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        stringbuilder.append("The message {");
        if(message.callback == null)
            break MISSING_BLOCK_LABEL_276;
        stringbuilder.append(" callback=");
        stringbuilder.append(message.callback.getClass().getName());
_L3:
        int i;
        stringbuilder.append(" target=");
        stringbuilder.append(message.target.getClass().getName());
        stringbuilder.append("}");
        uploadinfo.info = stringbuilder.toString();
        uploadinfo.threadName = currentThreadName();
        uploadinfo.tookTime = messagemonitorinfo.getTookTimeAfterDispatch();
        if(MONITOR_MSG_EXECUTION)
        {
            message = JVM INSTR new #232 <Class StringBuilder>;
            message.StringBuilder();
            Log.d("AnrMonitor", message.append("The msg ").append(messagemonitorinfo.getMonitorMessage()).append(" add to history sMsgRecordIndex ").append(sMsgRecordIndex).toString());
        }
        message = sUploadMsgRecords;
        i = sMsgRecordIndex;
        sMsgRecordIndex = i + 1;
        message[i] = uploadinfo;
        if(sMsgRecordIndex >= sUploadMsgRecords.length)
        {
            String s = UploadInfo._2D_wrap0(sUploadMsgRecords);
            if(!TextUtils.isEmpty(s))
            {
                message = Message.obtain();
                message.what = 2;
                message.obj = s;
                if(MONITOR_MSG_EXECUTION)
                {
                    messagemonitorinfo = JVM INSTR new #232 <Class StringBuilder>;
                    messagemonitorinfo.StringBuilder();
                    Log.d("AnrMonitor", messagemonitorinfo.append("report content : ").append(s).toString());
                }
                getWorkHandler().sendMessageAtFrontOfQueue(message);
            }
            sMsgRecordIndex = 0;
        }
_L2:
        android/os/AnrMonitor;
        JVM INSTR monitorexit ;
        return;
        stringbuilder.append(" what=");
        stringbuilder.append(message.what);
          goto _L3
        message;
        throw message;
    }

    private static String binderCallToString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        String as[] = Debug.getCallers(3, 1).split(":");
        if(as.length == 2 && !TextUtils.isEmpty(as[0]))
        {
            stringbuilder.append("{ ");
            stringbuilder.append(as[0]);
            stringbuilder.append(" }");
            return stringbuilder.toString();
        } else
        {
            return "";
        }
    }

    public static boolean canMonitorAnr()
    {
        return Build.IS_STABLE_VERSION ^ true;
    }

    static boolean canMonitorMessage()
    {
        if(MONITOR_MSG_EXECUTION)
            return true;
        boolean flag;
        if(!sMonitorList.isEmpty())
            flag = sMonitorList.contains(currentPackageName());
        else
            flag = false;
        return flag;
    }

    public static void checkBinderCallTime(long l)
    {
        if(!canMonitorAnr())
            return;
        l = SystemClock.uptimeMillis() - l;
        if(l > 1000L)
        {
            if(!PerfSupervisionSettings.isSupervisionOn() && l >= 3000L)
                logAnr((new StringBuilder()).append("The binder call took ").append(l).append("ms.").toString(), new Throwable());
            addBinderCallTimeToHistory(l);
        }
    }

    public static void checkInputTime(long l, InputEvent inputevent)
    {
        if(!canMonitorAnr())
            return;
        l = SystemClock.uptimeMillis() - l;
        if(l <= 1000L)
            break MISSING_BLOCK_LABEL_63;
        StringBuilder stringbuilder = JVM INSTR new #232 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        logAnr(stringbuilder.append("The input: ").append(inputevent).append(" took ").append(l).append("ms.").toString(), null);
_L1:
        return;
        inputevent;
        logAnr((new StringBuilder()).append("checkInputTime failed and took time is : ").append(l).append("ms.").toString(), inputevent);
          goto _L1
    }

    public static void checkInputTime(InputEvent inputevent, InputEventReceiver inputeventreceiver, InputChannel inputchannel)
    {
        if(!canMonitorAnr())
            return;
        SparseLongArray sparselongarray = mInputEvnetStartTimeSeqMap;
        sparselongarray;
        JVM INSTR monitorenter ;
        int i = mInputEvnetStartTimeSeqMap.indexOfKey(inputevent.getSequenceNumber());
        if(i >= 0)
            break MISSING_BLOCK_LABEL_33;
        sparselongarray;
        JVM INSTR monitorexit ;
        return;
        long l;
        l = SystemClock.uptimeMillis() - mInputEvnetStartTimeSeqMap.valueAt(i);
        mInputEvnetStartTimeSeqMap.removeAt(i);
        sparselongarray;
        JVM INSTR monitorexit ;
        if(l > 1000L)
        {
            addInputToHistory(inputevent, inputeventreceiver, inputchannel, l);
            logAnr((new StringBuilder()).append("The input: ").append(inputEventToString(inputevent)).append(" took ").append(l).append("ms. Send to InputChannel ").append(inputchannel.getName()).toString(), null);
        }
        return;
        inputevent;
        throw inputevent;
    }

    public static void checkLockWaitTime(android.os.statistics.MonitorSuperviser.SingleLockWait singlelockwait, int i, long l, long l1, android.os.statistics.MonitorSuperviser.SingleLockHold singlelockhold)
    {
    }

    public static void checkMsgTime(Message message, BaseLooper.MessageMonitorInfo messagemonitorinfo)
    {
        if(!canMonitorAnr())
            return;
        long l = messagemonitorinfo.getTookTime();
        long l1 = messagemonitorinfo.getTookTimeAfterDispatch();
        if(l > 0x186a0L || l < 0L)
            return;
        if(l1 > MESSAGE_MONITOR_TIMEOUT)
            logAnr((new StringBuilder()).append("The msg ").append(messagemonitorinfo.getMonitorMessage()).append(" took ").append(l).append("ms and took ").append(l1).append("ms after dispatch.").toString(), null);
        if(l1 > 3000L)
            addMessageToHistory(message, messagemonitorinfo);
    }

    public static void checkPerfEvent(PerfEvent perfevent)
    {
        if(!canMonitorAnr())
            return;
        if(!(perfevent instanceof MicroscopicEvent))
            return;
        MicroscopicEvent microscopicevent = (MicroscopicEvent)perfevent;
        if(!microscopicevent.isMasterEvent())
            return;
        long l = microscopicevent.endUptimeMillis - microscopicevent.beginUptimeMillis;
        if(l < 3000L)
            return;
        if(!(perfevent instanceof android.os.statistics.BinderSuperviser.SingleBinderCall) && (perfevent instanceof SingleJniMethod) ^ true && (perfevent instanceof android.os.statistics.MonitorSuperviser.SingleLockWait) ^ true && (perfevent instanceof android.os.statistics.MonitorSuperviser.SingleLockHold) ^ true && (perfevent instanceof android.os.statistics.MonitorSuperviser.SingleConditionWait) ^ true && (perfevent instanceof android.os.statistics.MonitorSuperviser.SingleConditionAwaken) ^ true)
        {
            return;
        } else
        {
            logPerfEvent((new StringBuilder()).append("The").append(microscopicevent.eventTypeName).append(" at thread ").append(microscopicevent.threadId).append(" took ").append(l).append("ms.").toString(), microscopicevent);
            return;
        }
    }

    public static File createFile(String s)
    {
        return createFile(s, true);
    }

    public static File createFile(String s, boolean flag)
    {
        File file = new File(s);
        File file1;
        file1 = file.getParentFile();
        if(file1.exists())
            break MISSING_BLOCK_LABEL_35;
        file1.mkdirs();
        if(!SELinux.restorecon(file1))
            return null;
        try
        {
            FileUtils.setPermissions(file1.getPath(), 509, -1, -1);
        }
        catch(IOException ioexception)
        {
            Slog.w("AnrMonitor", (new StringBuilder()).append("Unable to create file: ").append(s).toString(), ioexception);
            return null;
        }
        if(!flag)
            break MISSING_BLOCK_LABEL_64;
        if(file.exists())
            file.delete();
        file.createNewFile();
        FileUtils.setPermissions(file.getPath(), 438, -1, -1);
        return file;
    }

    static String currentPackageName()
    {
        android/os/AnrMonitor;
        JVM INSTR monitorenter ;
        String s;
        s = ActivityThread.currentPackageName();
        if(!TextUtils.isEmpty(s))
            break MISSING_BLOCK_LABEL_29;
        sPkgName = "system_server";
_L1:
        s = sPkgName;
        android/os/AnrMonitor;
        JVM INSTR monitorexit ;
        return s;
        sPkgName = s;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    static String currentProcessName()
    {
        if(TextUtils.isEmpty(sProcessName))
        {
            String s = ActivityThread.currentProcessName();
            if(TextUtils.isEmpty(s))
                sProcessName = "system_server";
            else
                sProcessName = s;
        }
        return sProcessName;
    }

    static String currentThreadName()
    {
        return Thread.currentThread().getName();
    }

    static int currentVersionCode()
    {
        if(sVersionCode != 0)
            break MISSING_BLOCK_LABEL_48;
        Object obj = ActivityThread.currentApplication().getApplicationContext();
        if(obj == null)
            break MISSING_BLOCK_LABEL_48;
        obj = ((Context) (obj)).getPackageManager().getPackageInfo(((Context) (obj)).getPackageName(), 0);
        if(obj != null)
            try
            {
                if(((PackageInfo) (obj)).versionCode != 0)
                    sVersionCode = ((PackageInfo) (obj)).versionCode;
            }
            catch(Exception exception) { }
        return sVersionCode;
    }

    static String currentVersionName()
    {
        if(!TextUtils.isEmpty(sVersionName))
            break MISSING_BLOCK_LABEL_51;
        Object obj = ActivityThread.currentApplication().getApplicationContext();
        if(obj == null)
            break MISSING_BLOCK_LABEL_51;
        obj = ((Context) (obj)).getPackageManager().getPackageInfo(((Context) (obj)).getPackageName(), 0);
        if(obj != null)
            try
            {
                if(((PackageInfo) (obj)).versionName != null)
                    sVersionName = ((PackageInfo) (obj)).versionName;
            }
            catch(Exception exception) { }
        return sVersionName;
    }

    private static void deleteUnnecessaryFileIfNeeded(File file, String s, int i)
    {
        if(file == null || file.isDirectory() ^ true)
            return;
        ArrayList arraylist = new ArrayList();
        file = file.listFiles();
        int j = 0;
        for(int k = file.length; j < k; j++)
        {
            File file1 = file[j];
            String s1 = file1.getName();
            if(s1 != null && s1.contains(s))
                arraylist.add(new FileInfo(file1, file1.lastModified()));
        }

        try
        {
            Collections.sort(arraylist);
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            file.printStackTrace();
        }
        for(; i < arraylist.size(); i++)
        {
            file = ((FileInfo)arraylist.get(i)).getFile();
            if(file != null)
                file.delete();
        }

    }

    public static void dispatchInputEventStart(InputEvent inputevent)
    {
        SparseLongArray sparselongarray = mInputEvnetStartTimeSeqMap;
        sparselongarray;
        JVM INSTR monitorenter ;
        mInputEvnetStartTimeSeqMap.put(inputevent.getSequenceNumber(), SystemClock.uptimeMillis());
        sparselongarray;
        JVM INSTR monitorexit ;
        return;
        inputevent;
        throw inputevent;
    }

    public static void doDump(String s)
    {
        Object obj;
        Object obj1;
        int i;
        obj = JVM INSTR new #681 <Class JSONObject>;
        ((JSONObject) (obj)).JSONObject(s);
        obj1 = ((JSONObject) (obj)).optString("dump_service");
        obj = ((JSONObject) (obj)).getJSONArray("args");
        i = ((JSONArray) (obj)).length();
        s = new String[((JSONArray) (obj)).length()];
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        s[j] = ((JSONArray) (obj)).getJSONObject(j).optString("opt");
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        obj1 = ServiceManager.getService(((String) (obj1)));
        ((IBinder) (obj1)).dump(FileDescriptor.out, s);
_L3:
        return;
        s;
        try
        {
            Log.e("AnrMonitor", "dump failed", s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s.printStackTrace();
        }
          goto _L3
    }

    public static File dumpAnrInfo(StringBuilder stringbuilder, String s, String s1, int i, ArrayList arraylist, SparseArray sparsearray, String as[], boolean flag, 
            boolean flag1)
    {
        as = null;
        sparsearray = null;
        arraylist = as;
        long l = SystemClock.uptimeMillis();
        arraylist = as;
        File file = createFile(getAnrPath(flag1, s1));
        arraylist = as;
        if(!isFileAvailable(file)) goto _L2; else goto _L1
_L1:
        arraylist = as;
        s1 = JVM INSTR new #742 <Class FileWriter>;
        arraylist = as;
        s1.FileWriter(file, true);
        s1.write(stringbuilder.toString());
        if(!flag)
            break MISSING_BLOCK_LABEL_80;
        stringbuilder.append(s);
        s1.write(s);
        stringbuilder = JVM INSTR new #232 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        s1.write(stringbuilder.append(LINE_SEPARATOR).append("(dump anr info success and took ").append(SystemClock.uptimeMillis() - l).append("ms)").toString());
        if(s1 != null)
            try
            {
                s1.close();
            }
            // Misplaced declaration of an exception variable
            catch(StringBuilder stringbuilder) { }
        return file;
        s;
        stringbuilder = sparsearray;
_L6:
        arraylist = stringbuilder;
        Slog.e("AnrMonitor", "Error happens when dumping anr info", s);
        if(stringbuilder != null)
            try
            {
                stringbuilder.close();
            }
            // Misplaced declaration of an exception variable
            catch(StringBuilder stringbuilder) { }
_L2:
        return null;
        stringbuilder;
_L4:
        if(arraylist != null)
            try
            {
                arraylist.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        throw stringbuilder;
        stringbuilder;
        arraylist = s1;
        if(true) goto _L4; else goto _L3
_L3:
        s;
        stringbuilder = s1;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static void dumpBinderInfo(int i, Writer writer)
    {
        readFileToWriter("/sys/kernel/debug/binder/failed_transaction_log", writer);
        readFileToWriter("/sys/kernel/debug/binder/transaction_log", writer);
        readFileToWriter("/sys/kernel/debug/binder/transactions", writer);
        readFileToWriter("/sys/kernel/debug/binder/stats", writer);
        StringBuilder stringbuilder = JVM INSTR new #232 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        readFileToWriter(stringbuilder.append("/sys/kernel/debug/binder/proc/").append(i).toString(), writer);
_L1:
        return;
        writer;
        Log.e("AnrMonitor", "dumpBinderInfo fail", writer);
          goto _L1
    }

    public static void dumpCpuInfo(int i, Writer writer)
    {
        StringBuilder stringbuilder = JVM INSTR new #232 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        readFileToWriter(stringbuilder.append("/proc/").append(i).append("/schedstat").toString(), writer);
        stringbuilder = JVM INSTR new #232 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        readFileToWriter(stringbuilder.append("/proc/").append(i).append("/stat").toString(), writer);
_L1:
        return;
        writer;
        Log.e("AnrMonitor", "dumpCpuInfo fail", writer);
          goto _L1
    }

    static void finishMonitor(Message message, BaseLooper.MessageMonitorInfo messagemonitorinfo)
    {
        if(!canMonitorMessage())
        {
            return;
        } else
        {
            message = messagemonitorinfo.getMonitorDigest();
            getWorkHandler().removeMessages(1, message);
            return;
        }
    }

    private static String getAnrPath(boolean flag, String s)
    {
        if(flag)
            s = "anr_info.txt";
        else
            s = (new StringBuilder()).append("anr_info_").append(s).append("_").append(toCalendarTime(System.currentTimeMillis())).append(".txt").toString();
        return (new StringBuilder()).append("/data/anr/").append(s).toString();
    }

    public static Handler getWorkHandler()
    {
        if(sWorkHandler != null) goto _L2; else goto _L1
_L1:
        Object obj = sInstanceSync;
        obj;
        JVM INSTR monitorenter ;
        if(sWorkHandler == null)
        {
            HandlerThread handlerthread = JVM INSTR new #812 <Class HandlerThread>;
            handlerthread.HandlerThread("work-thread");
            handlerthread.start();
            WorkHandler workhandler = JVM INSTR new #15  <Class AnrMonitor$WorkHandler>;
            workhandler.WorkHandler(handlerthread.getLooper());
            sWorkHandler = workhandler;
        }
        obj;
        JVM INSTR monitorexit ;
_L2:
        return sWorkHandler;
        Exception exception;
        exception;
        throw exception;
    }

    private static String inputChannelToString(InputChannel inputchannel)
    {
        if(inputchannel == null)
            return "null";
        inputchannel = inputchannel.getName();
label0:
        {
            if(inputchannel.equals("uninitialized"))
                return "uninitialized";
            try
            {
                String as[] = inputchannel.split(" ");
                if(as.length < 2)
                    break label0;
                inputchannel = as[as.length - 2];
            }
            // Misplaced declaration of an exception variable
            catch(InputChannel inputchannel)
            {
                logAnr("Error getting inputChannel name ", inputchannel);
                return "null";
            }
            return inputchannel;
        }
        return inputchannel;
    }

    private static String inputEventToString(InputEvent inputevent)
    {
        StringBuilder stringbuilder = new StringBuilder();
        if(inputevent instanceof KeyEvent)
        {
            inputevent = (KeyEvent)inputevent;
            stringbuilder.append("KeyEvent { action=").append(KeyEvent.actionToString(inputevent.getAction()));
            stringbuilder.append(", keyCode=").append(KeyEvent.keyCodeToString(inputevent.getKeyCode()));
            stringbuilder.append(" }");
            return stringbuilder.toString();
        } else
        {
            inputevent = (MotionEvent)inputevent;
            stringbuilder.append("MotionEvent { action=").append(MotionEvent.actionToString(inputevent.getAction()));
            stringbuilder.append(" }");
            return stringbuilder.toString();
        }
    }

    private static boolean isAllowedMonitor(BaseLooper baselooper)
    {
        if(baselooper != null)
            return baselooper.isMonitorLooper();
        boolean flag;
        if(Process.myPid() == Process.myTid())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isAllowedMonitorBinderCall(BaseLooper baselooper)
    {
        return isAllowedMonitor(baselooper);
    }

    public static boolean isAllowedMonitorBinderCallSize(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(canMonitorAnr())
        {
            flag1 = flag;
            if(CHECK_PARCEL_SIZE_ENABLE)
            {
                flag1 = flag;
                if(i >= CHECK_PARCEL_SIZE_KB * 1024)
                    flag1 = true;
            }
        }
        return flag1;
    }

    public static boolean isAllowedMonitorInput(BaseLooper baselooper)
    {
        return isAllowedMonitor(baselooper);
    }

    public static boolean isFileAvailable(File file)
    {
        return file != null && !(file.exists() ^ true);
    }

    public static boolean isLongTimeMsg(BaseLooper.MessageMonitorInfo messagemonitorinfo)
    {
        long l = messagemonitorinfo.getTookTime();
        return l > 200L && l < 0x186a0L;
    }

    static boolean isSystemApp()
    {
        boolean flag = false;
        android/os/AnrMonitor;
        JVM INSTR monitorenter ;
        Object obj = sIsSystemApp;
        if(obj != null)
            break MISSING_BLOCK_LABEL_95;
        obj = ActivityThread.currentApplication().getApplicationContext();
        if(obj == null)
            break MISSING_BLOCK_LABEL_95;
        obj = ((Context) (obj)).getPackageManager().getPackageInfo(((Context) (obj)).getPackageName(), 0);
        if(obj == null)
            break MISSING_BLOCK_LABEL_95;
        if(((PackageInfo) (obj)).applicationInfo == null)
            break MISSING_BLOCK_LABEL_95;
        boolean flag1;
        boolean flag2;
        if((((PackageInfo) (obj)).applicationInfo.flags & 1) != 0)
            flag1 = true;
        else
            flag1 = false;
        if((((PackageInfo) (obj)).applicationInfo.flags & 0x80) != 0)
            flag2 = true;
        else
            flag2 = false;
        obj = JVM INSTR new #900 <Class Boolean>;
        if(flag1)
            flag2 = true;
        Exception exception;
        try
        {
            ((Boolean) (obj)).Boolean(flag2);
            sIsSystemApp = ((Boolean) (obj));
        }
        catch(Exception exception1) { }
        obj = sIsSystemApp;
        if(obj != null) goto _L2; else goto _L1
_L1:
        flag2 = flag;
_L4:
        android/os/AnrMonitor;
        JVM INSTR monitorexit ;
        return flag2;
_L2:
        flag2 = sIsSystemApp.booleanValue();
        if(true) goto _L4; else goto _L3
_L3:
        exception;
        throw exception;
    }

    private static boolean isSystemBootCompleted()
    {
        if(!sSystemBootCompleted)
            sSystemBootCompleted = "1".equals(SystemProperties.get("sys.boot_completed"));
        return sSystemBootCompleted;
    }

    static boolean isSystemServer()
    {
        android/os/AnrMonitor;
        JVM INSTR monitorenter ;
        boolean flag = "system_server".equals(currentPackageName());
        android/os/AnrMonitor;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public static void logAnr(String s, Throwable throwable)
    {
        if(throwable != null)
            Slog.w("MIUI-BLOCK-MONITOR", s, throwable);
        else
            Slog.w("MIUI-BLOCK-MONITOR", s);
    }

    public static void logDumpTrace(String s, Throwable throwable)
    {
        if(throwable != null)
            Slog.w("DUMP_APP_TRACE", s, throwable);
        else
            Slog.d("DUMP_APP_TRACE", s);
    }

    public static void logPerfEvent(String s, PerfEvent perfevent)
    {
        Slog.w("MIUI-BLOCK-MONITOR", s);
        if(perfevent != null)
            Slog.w("MIUI-BLOCK-MONITOR", perfevent.toJson().toString());
    }

    public static String readFile(File file)
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        obj5 = obj2;
        obj6 = obj;
        Object obj7;
        try
        {
            obj7 = JVM INSTR new #930 <Class FileReader>;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj3)
        {
            obj7 = obj4;
            file = obj1;
            continue; /* Loop/switch isn't completed */
        }
        obj5 = obj2;
        obj6 = obj;
        ((FileReader) (obj7)).FileReader(file);
        file = JVM INSTR new #935 <Class BufferedReader>;
        file.BufferedReader(((java.io.Reader) (obj7)));
        obj5 = JVM INSTR new #232 <Class StringBuilder>;
        ((StringBuilder) (obj5)).StringBuilder();
_L3:
        obj6 = file.readLine();
        if(obj6 == null) goto _L2; else goto _L1
_L1:
        ((StringBuilder) (obj5)).append(((String) (obj6))).append(LINE_SEPARATOR);
          goto _L3
        obj3;
        obj5 = file;
        file = ((File) (obj7));
        obj7 = obj5;
_L7:
        obj5 = obj7;
        obj6 = file;
        ((Exception) (obj3)).printStackTrace();
        if(obj7 == null)
            break MISSING_BLOCK_LABEL_117;
        ((BufferedReader) (obj7)).close();
        if(file != null)
            try
            {
                file.close();
            }
            // Misplaced declaration of an exception variable
            catch(File file)
            {
                file.printStackTrace();
            }
        return null;
_L2:
        obj5 = ((StringBuilder) (obj5)).toString();
        if(file == null)
            break MISSING_BLOCK_LABEL_142;
        file.close();
        if(obj7 != null)
            try
            {
                ((FileReader) (obj7)).close();
            }
            // Misplaced declaration of an exception variable
            catch(File file)
            {
                file.printStackTrace();
            }
        return ((String) (obj5));
        file;
_L5:
        if(obj5 == null)
            break MISSING_BLOCK_LABEL_182;
        ((BufferedReader) (obj5)).close();
        if(obj6 != null)
            try
            {
                ((FileReader) (obj6)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj7)
            {
                ((Exception) (obj7)).printStackTrace();
            }
        throw file;
        file;
        obj5 = obj3;
        obj6 = obj7;
        continue; /* Loop/switch isn't completed */
        obj3;
        obj5 = file;
        obj6 = obj7;
        file = ((File) (obj3));
        if(true) goto _L5; else goto _L4
        obj3;
        file = ((File) (obj7));
        obj7 = obj4;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public static void readFileToWriter(String s, Writer writer)
        throws IOException
    {
        File file = new File(s);
        if(isFileAvailable(file) && writer != null)
        {
            writer.write((new StringBuilder()).append(LINE_SEPARATOR).append("------ cat ").append(s).append(" ------").append(LINE_SEPARATOR).toString());
            writer.write(readFile(file));
        }
    }

    public static void renameTraces(String s)
    {
        Object obj = SystemProperties.get("dalvik.vm.stack-trace-file", null);
        if(obj != null && ((String) (obj)).length() != 0)
        {
            (new File(((String) (obj)))).renameTo(new File((new StringBuilder()).append("/data/anr/traces_").append(s).append("_").append(toCalendarTime(System.currentTimeMillis())).append(".txt").toString()));
            obj = new File(((String) (obj)).substring(0, ((String) (obj)).lastIndexOf("/")));
            deleteUnnecessaryFileIfNeeded(((File) (obj)), (new StringBuilder()).append("traces_").append(s).toString(), 5);
            deleteUnnecessaryFileIfNeeded(((File) (obj)), (new StringBuilder()).append("anr_info_").append(s).toString(), 5);
        }
    }

    static void startMonitor(Message message, BaseLooper.MessageMonitorInfo messagemonitorinfo)
    {
        if(!canMonitorMessage())
            return;
        message = messagemonitorinfo.getMonitorMessage();
        if(TextUtils.isEmpty(message))
        {
            return;
        } else
        {
            Message message1 = getWorkHandler().obtainMessage();
            message1.what = 1;
            message1.obj = messagemonitorinfo.createMonitorDigest();
            messagemonitorinfo = new Bundle();
            messagemonitorinfo.putString("monitor_msg", message);
            message1.setData(messagemonitorinfo);
            getWorkHandler().sendMessageDelayed(message1, 2000L);
            return;
        }
    }

    public static String toCalendarTime(long l)
    {
        android/os/AnrMonitor;
        JVM INSTR monitorenter ;
        String s;
        DATE.setTime(l);
        s = DATE_FORMATTER.format(DATE);
        android/os/AnrMonitor;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        throw exception;
    }

    public static final String ANR_DIRECTORY = "/data/anr/";
    public static final String ANR_INFO_HEAD = "anr_info_";
    public static final int ANR_INFO_LIMIT = 5;
    public static final int ANR_TRACES_LIMIT = 5;
    public static final long BINDER_CALL_MONITOR_TIMEOUT = 1000L;
    private static final boolean CHECK_PARCEL_SIZE_ENABLE;
    private static final int CHECK_PARCEL_SIZE_KB;
    private static final Date DATE = new Date();
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss.SSS");
    public static final boolean DBG = SystemProperties.getBoolean("anr.monitor.debug.on", false);
    private static final long DEFAULT_LOCK_WAIT_THRESHOLD = 500L;
    public static final int DEFAULT_MESSAGE_HISTORY_DUMP_DURATION = 10000;
    public static final String DUMP_APP_TRACE_TAG = "dump-app-trace";
    public static final long DUMP_MESSAGE_TIMEOUT = 200L;
    private static final String DUMP_TRACE_TAG = "DUMP_APP_TRACE";
    public static final int INPUT_DISPATCHING_TIMEOUT = 8000;
    public static final long INPUT_MONITOR_TIMEOUT = 1000L;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");
    public static final long LOCK_WAIT_THRESHOLD = SystemProperties.getLong("persist.vm.lockprof.threshold", 500L);
    public static final long LT_MESSAGE_CHECK_TIME = 200L;
    public static final int MAX_MESSAGE_SUMMARY_HISTORY = 50;
    public static final long MAX_TIMEOUT = 0x186a0L;
    public static final long MESSAGE_EXECUTION_TIMEOUT = 2000L;
    public static final long MESSAGE_MONITOR_TIMEOUT;
    private static final long MESSAGE_UPLOAD_CHECK_TIME = 3000L;
    private static final boolean MONITOR_MSG_EXECUTION = SystemProperties.getBoolean("monitor.msg.execution", false);
    private static final String MONITOR_TAG = "MIUI-BLOCK-MONITOR";
    public static final long PERF_EVENT_LOGGING_TIMEOUT = 3000L;
    public static final String SEPARATOR = ",";
    private static final String TAG = "AnrMonitor";
    public static final String TRACES_FILE_TYPE = ".txt";
    public static final String TRACES_HEAD = "traces_";
    private static final String TRACE_DIR = "/data/anr/";
    private static final SparseLongArray mInputEvnetStartTimeSeqMap = new SparseLongArray();
    private static int sBinderRecordIndex = 0;
    private static int sInputRecordIndex = 0;
    private static final Object sInstanceSync = new Object();
    private static Boolean sIsSystemApp;
    private static final ArrayList sMonitorList;
    private static int sMsgRecordIndex = 0;
    private static String sPkgName;
    private static String sProcessName;
    private static boolean sSystemBootCompleted;
    private static final UploadInfo sUploadBinderRecords[] = new UploadInfo[10];
    private static final UploadInfo sUploadInputRecords[] = new UploadInfo[10];
    private static final UploadInfo sUploadMsgRecords[] = new UploadInfo[10];
    private static int sVersionCode;
    private static String sVersionName;
    private static volatile WorkHandler sWorkHandler;

    static 
    {
        int i;
        boolean flag;
        if(Build.IS_ALPHA_BUILD)
            i = 2000;
        else
            i = 3000;
        MESSAGE_MONITOR_TIMEOUT = i;
        CHECK_PARCEL_SIZE_KB = SystemProperties.getInt("persist.binder.check.size", 200);
        if(CHECK_PARCEL_SIZE_KB > 0)
            flag = true;
        else
            flag = false;
        CHECK_PARCEL_SIZE_ENABLE = flag;
        sMonitorList = new ArrayList();
        if(Build.IS_ALPHA_BUILD)
        {
            sMonitorList.add("com.android.systemui");
            sMonitorList.add("com.android.settings");
            sMonitorList.add("com.android.phone");
            sMonitorList.add("com.miui.home");
        }
    }
}

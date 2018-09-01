// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.log;

import android.os.FileObserver;
import android.text.TextUtils;
import android.util.Log;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

// Referenced classes of package miui.log:
//            LogSwitchesConfigApplier, AppLogSwitches, Utils

public final class LogSwitchesConfigMonitor
{

    static LogSwitchesConfigApplier _2D_get0(LogSwitchesConfigMonitor logswitchesconfigmonitor)
    {
        return logswitchesconfigmonitor.applier;
    }

    static String _2D_get1(LogSwitchesConfigMonitor logswitchesconfigmonitor)
    {
        return logswitchesconfigmonitor.logSwitchesFileName;
    }

    static String _2D_get2(LogSwitchesConfigMonitor logswitchesconfigmonitor)
    {
        return logswitchesconfigmonitor.logSwitchesFilePath;
    }

    static HashMap _2D_set0(LogSwitchesConfigMonitor logswitchesconfigmonitor, HashMap hashmap)
    {
        logswitchesconfigmonitor.currentLogSwitchesConfig = hashmap;
        return hashmap;
    }

    public LogSwitchesConfigMonitor(String s, String s1)
    {
        isWatchingSwitches = false;
        currentLogSwitchesConfig = new HashMap();
        logSwitchesFolder = s;
        logSwitchesFileName = s1;
        logSwitchesFilePath = (new StringBuilder()).append(s).append("/").append(s1).toString();
    }

    private String getCurrentProgramName()
    {
        Object obj;
        Object obj1;
        Object obj3;
        Object obj4;
        obj = null;
        obj1 = null;
        obj3 = null;
        obj4 = obj1;
        Object obj5 = JVM INSTR new #75  <Class BufferedReader>;
        obj4 = obj1;
        FileReader filereader = JVM INSTR new #77  <Class FileReader>;
        obj4 = obj1;
        filereader.FileReader("/proc/self/cmdline");
        obj4 = obj1;
        ((BufferedReader) (obj5)).BufferedReader(filereader);
        obj4 = ((BufferedReader) (obj5)).readLine();
        if(TextUtils.isEmpty(((CharSequence) (obj4))))
        {
            if(obj5 != null)
                try
                {
                    ((BufferedReader) (obj5)).close();
                }
                // Misplaced declaration of an exception variable
                catch(Object obj4) { }
            return "";
        }
        obj4 = ((String) (obj4)).split("\\u0000")[0];
        if(obj5 != null)
            try
            {
                ((BufferedReader) (obj5)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj5) { }
        return ((String) (obj4));
        Object obj2;
        obj2;
        obj5 = obj3;
_L6:
        obj4 = obj5;
        Log.e("LogSwitchesConfigMonitor", "failed to read /proc/self/cmdline", ((Throwable) (obj2)));
        if(obj5 != null)
            try
            {
                ((BufferedReader) (obj5)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj4) { }
        return "";
        obj2;
        obj5 = obj;
_L4:
        obj4 = obj5;
        Log.e("LogSwitchesConfigMonitor", "cannot found /proc/self/cmdline", ((Throwable) (obj2)));
        if(obj5 != null)
            try
            {
                ((BufferedReader) (obj5)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj4) { }
        return "";
        obj5;
        obj2 = obj4;
_L2:
        if(obj2 != null)
            try
            {
                ((BufferedReader) (obj2)).close();
            }
            catch(IOException ioexception) { }
        throw obj5;
        Exception exception;
        exception;
        obj2 = obj5;
        obj5 = exception;
        if(true) goto _L2; else goto _L1
_L1:
        obj2;
        if(true) goto _L4; else goto _L3
_L3:
        obj2;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public boolean isWatching()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = isWatchingSwitches;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public HashMap retrieveCurrentLogSwitches()
    {
        this;
        JVM INSTR monitorenter ;
        HashMap hashmap;
        hashmap = JVM INSTR new #49  <Class HashMap>;
        hashmap.HashMap();
        AppLogSwitches applogswitches;
        for(Iterator iterator = currentLogSwitchesConfig.values().iterator(); iterator.hasNext(); hashmap.put(applogswitches.uniqueName, applogswitches))
            applogswitches = (AppLogSwitches)((AppLogSwitches)iterator.next()).clone();

        break MISSING_BLOCK_LABEL_66;
        Exception exception;
        exception;
        throw exception;
        this;
        JVM INSTR monitorexit ;
        return hashmap;
    }

    public void startMonitoring(boolean flag, boolean flag1)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag2 = isWatchingSwitches;
        if(!flag2)
            break MISSING_BLOCK_LABEL_14;
        this;
        JVM INSTR monitorexit ;
        return;
        if(!flag)
            break MISSING_BLOCK_LABEL_29;
        Utils.createLogSwitchesFileIfNotExisted(logSwitchesFolder, logSwitchesFilePath);
        if(logSwitchsObserver == null)
        {
            FileObserver fileobserver = JVM INSTR new #6   <Class LogSwitchesConfigMonitor$1>;
            fileobserver.this. _cls1(logSwitchesFolder, 520);
            logSwitchsObserver = fileobserver;
        }
        logSwitchsObserver.startWatching();
        isWatchingSwitches = true;
        if(!flag1)
            break MISSING_BLOCK_LABEL_99;
        Log.w("LogSwitchesConfigMonitor", "Read log switches for config file synchronously");
        applier.apply(logSwitchesFilePath);
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        Thread thread = JVM INSTR new #183 <Class Thread>;
        Runnable runnable = JVM INSTR new #8   <Class LogSwitchesConfigMonitor$2>;
        runnable.this. _cls2();
        thread.Thread(runnable);
        thread.start();
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public void stopMonitoring()
    {
        this;
        JVM INSTR monitorenter ;
        FileObserver fileobserver = logSwitchsObserver;
        if(fileobserver != null)
            break MISSING_BLOCK_LABEL_14;
        this;
        JVM INSTR monitorexit ;
        return;
        boolean flag = isWatchingSwitches;
        if(flag)
            break MISSING_BLOCK_LABEL_26;
        this;
        JVM INSTR monitorexit ;
        return;
        logSwitchsObserver.stopWatching();
        isWatchingSwitches = false;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void updatePackageName(String s)
    {
        this;
        JVM INSTR monitorenter ;
        applier.updatePackageName(s);
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    public void updateProgramName()
    {
        this;
        JVM INSTR monitorenter ;
        updateProgramName(getCurrentProgramName());
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void updateProgramName(String s)
    {
        this;
        JVM INSTR monitorenter ;
        applier.updateProgramName(s);
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    protected static final String TAG = "LogSwitchesConfigMonitor";
    private final LogSwitchesConfigApplier applier = new LogSwitchesConfigApplier();
    private HashMap currentLogSwitchesConfig;
    private boolean isWatchingSwitches;
    private final String logSwitchesFileName;
    private final String logSwitchesFilePath;
    private final String logSwitchesFolder;
    private FileObserver logSwitchsObserver;

    // Unreferenced inner class miui/log/LogSwitchesConfigMonitor$1

/* anonymous class */
    class _cls1 extends FileObserver
    {

        public void onEvent(int i, String s)
        {
            if(!s.equals(LogSwitchesConfigMonitor._2D_get1(LogSwitchesConfigMonitor.this))) goto _L2; else goto _L1
_L1:
            s = LogSwitchesConfigMonitor.this;
            s;
            JVM INSTR monitorenter ;
            LogSwitchesConfigMonitor._2D_set0(LogSwitchesConfigMonitor.this, LogSwitchesConfigMonitor._2D_get0(LogSwitchesConfigMonitor.this).apply(LogSwitchesConfigMonitor._2D_get2(LogSwitchesConfigMonitor.this)));
            s;
            JVM INSTR monitorexit ;
_L2:
            return;
            Exception exception;
            exception;
            throw exception;
        }

        final LogSwitchesConfigMonitor this$0;

            
            {
                this$0 = LogSwitchesConfigMonitor.this;
                super(s, i);
            }
    }


    // Unreferenced inner class miui/log/LogSwitchesConfigMonitor$2

/* anonymous class */
    class _cls2
        implements Runnable
    {

        public void run()
        {
            LogSwitchesConfigMonitor logswitchesconfigmonitor = LogSwitchesConfigMonitor.this;
            logswitchesconfigmonitor;
            JVM INSTR monitorenter ;
            LogSwitchesConfigMonitor._2D_set0(LogSwitchesConfigMonitor.this, LogSwitchesConfigMonitor._2D_get0(LogSwitchesConfigMonitor.this).apply(LogSwitchesConfigMonitor._2D_get2(LogSwitchesConfigMonitor.this)));
            logswitchesconfigmonitor;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        final LogSwitchesConfigMonitor this$0;

            
            {
                this$0 = LogSwitchesConfigMonitor.this;
                super();
            }
    }

}

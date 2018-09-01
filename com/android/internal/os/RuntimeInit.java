// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.app.*;
import android.ddm.DdmRegister;
import android.miui.Shell;
import android.os.*;
import android.util.Log;
import android.util.Slog;
import com.android.internal.logging.AndroidConfig;
import com.android.server.NetworkManagementSocketTagger;
import dalvik.system.VMRuntime;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.*;
import java.util.TimeZone;
import java.util.logging.LogManager;
import org.apache.harmony.luni.internal.util.TimezoneGetter;

// Referenced classes of package com.android.internal.os:
//            RuntimeInitInjector, AndroidPrintStream

public class RuntimeInit
{
    static class Arguments
    {

        private void parseArgs(String as[])
            throws IllegalArgumentException
        {
            int i = 0;
_L7:
            int k = i;
            if(i >= as.length) goto _L2; else goto _L1
_L1:
            String s = as[i];
            if(!s.equals("--")) goto _L4; else goto _L3
_L3:
            k = i + 1;
_L2:
            if(k == as.length)
            {
                throw new IllegalArgumentException("Missing classname argument to RuntimeInit!");
            } else
            {
                int j = k + 1;
                startClass = as[k];
                startArgs = new String[as.length - j];
                System.arraycopy(as, j, startArgs, 0, startArgs.length);
                return;
            }
_L4:
            k = i;
            if(!s.startsWith("--")) goto _L2; else goto _L5
_L5:
            i++;
            if(true) goto _L7; else goto _L6
_L6:
        }

        String startArgs[];
        String startClass;

        Arguments(String as[])
            throws IllegalArgumentException
        {
            parseArgs(as);
        }
    }

    private static class KillApplicationHandler
        implements Thread.UncaughtExceptionHandler
    {

        public void uncaughtException(Thread thread, Throwable throwable)
        {
            boolean flag = RuntimeInit._2D_get1();
            if(flag)
            {
                Process.killProcess(Process.myPid());
                System.exit(10);
                return;
            }
            RuntimeInit._2D_set0(true);
            if(ActivityThread.currentActivityThread() != null)
                ActivityThread.currentActivityThread().stopProfiling();
            IActivityManager iactivitymanager = ActivityManager.getService();
            thread = RuntimeInit._2D_get0();
            android.app.ApplicationErrorReport.ParcelableCrashInfo parcelablecrashinfo = JVM INSTR new #64  <Class android.app.ApplicationErrorReport$ParcelableCrashInfo>;
            parcelablecrashinfo.android.app.ApplicationErrorReport.ParcelableCrashInfo(throwable);
            iactivitymanager.handleApplicationCrash(thread, parcelablecrashinfo);
            Process.killProcess(Process.myPid());
            System.exit(10);
_L2:
            return;
            thread;
            flag = thread instanceof DeadObjectException;
            if(!flag)
                break; /* Loop/switch isn't completed */
_L3:
            Process.killProcess(Process.myPid());
            System.exit(10);
            if(true) goto _L2; else goto _L1
_L1:
            try
            {
                RuntimeInit._2D_wrap0("AndroidRuntime", "Error reporting crash", thread);
            }
            // Misplaced declaration of an exception variable
            catch(Thread thread) { }
              goto _L3
            thread;
            Process.killProcess(Process.myPid());
            System.exit(10);
            throw thread;
        }

        private KillApplicationHandler()
        {
        }

        KillApplicationHandler(KillApplicationHandler killapplicationhandler)
        {
            this();
        }
    }

    private static class LoggingHandler
        implements Thread.UncaughtExceptionHandler
    {

        public void uncaughtException(Thread thread, Throwable throwable)
        {
            if(RuntimeInit._2D_get1())
                return;
            if(RuntimeInit._2D_get0() == null && 1000 == Process.myUid())
            {
                RuntimeInit._2D_wrap0("AndroidRuntime", (new StringBuilder()).append("*** FATAL EXCEPTION IN SYSTEM PROCESS: ").append(thread.getName()).toString(), throwable);
                if(SystemProperties.getInt("sys.is_mem_low_level", 0) == 3)
                    if(!"true".equals(SystemProperties.get("sys.is_mem_low_retried", "false")))
                    {
                        Slog.wtf("AndroidRuntime", "*** NO SPACE FOR SYSTEM, AUTO CLEAN SOME FILES, FIRST TRY.");
                        RuntimeInit.removeFileForLowMem();
                        SystemProperties.set("sys.is_mem_low_retried", "true");
                    } else
                    {
                        Slog.wtf("AndroidRuntime", "*** NO SPACE FOR SYSTEM, THE SYSTEM IS DEAD, LAST TRY.");
                        RuntimeInit.removeFileForLowMem();
                    }
                RuntimeInitInjector.onJE(Process.myPid(), "system_server", "system_server", thread.getName(), throwable, "*** FATAL EXCEPTION IN SYSTEM PROCESS: ", true);
            } else
            {
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append("FATAL EXCEPTION: ").append(thread.getName()).append("\n");
                String s = ActivityThread.currentProcessName();
                if(s != null)
                    stringbuilder.append("Process: ").append(s).append(", ");
                stringbuilder.append("PID: ").append(Process.myPid());
                RuntimeInit._2D_wrap0("AndroidRuntime", stringbuilder.toString(), throwable);
                RuntimeInitInjector.onJE(Process.myPid(), ActivityThread.currentProcessName(), ActivityThread.currentPackageName(), thread.getName(), throwable, "FATAL EXCEPTION: ", false);
            }
        }

        private LoggingHandler()
        {
        }

        LoggingHandler(LoggingHandler logginghandler)
        {
            this();
        }
    }

    static class MethodAndArgsCaller
        implements Runnable
    {

        public void run()
        {
            try
            {
                mMethod.invoke(null, new Object[] {
                    mArgs
                });
                return;
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                throw new RuntimeException(illegalaccessexception);
            }
            catch(InvocationTargetException invocationtargetexception)
            {
                Throwable throwable = invocationtargetexception.getCause();
                if(throwable instanceof RuntimeException)
                    throw (RuntimeException)throwable;
                if(throwable instanceof Error)
                    throw (Error)throwable;
                else
                    throw new RuntimeException(invocationtargetexception);
            }
        }

        private final String mArgs[];
        private final Method mMethod;

        public MethodAndArgsCaller(Method method, String as[])
        {
            mMethod = method;
            mArgs = as;
        }
    }


    static IBinder _2D_get0()
    {
        return mApplicationObject;
    }

    static boolean _2D_get1()
    {
        return mCrashing;
    }

    static boolean _2D_set0(boolean flag)
    {
        mCrashing = flag;
        return flag;
    }

    static int _2D_wrap0(String s, String s1, Throwable throwable)
    {
        return Clog_e(s, s1, throwable);
    }

    public RuntimeInit()
    {
    }

    private static int Clog_e(String s, String s1, Throwable throwable)
    {
        return Log.printlns(4, 6, s, s1, throwable);
    }

    protected static Runnable applicationInit(int i, String as[], ClassLoader classloader)
    {
        nativeSetExitWithoutCleanup(true);
        VMRuntime.getRuntime().setTargetHeapUtilization(0.75F);
        VMRuntime.getRuntime().setTargetSdkVersion(i);
        as = new Arguments(as);
        Trace.traceEnd(64L);
        return findStaticMain(((Arguments) (as)).startClass, ((Arguments) (as)).startArgs, classloader);
    }

    protected static final void commonInit()
    {
        Thread.setUncaughtExceptionPreHandler(new LoggingHandler(null));
        Thread.setDefaultUncaughtExceptionHandler(new KillApplicationHandler(null));
        TimezoneGetter.setInstance(new TimezoneGetter() {

            public String getId()
            {
                return SystemProperties.get("persist.sys.timezone");
            }

        }
);
        TimeZone.setDefault(null);
        LogManager.getLogManager().reset();
        new AndroidConfig();
        System.setProperty("http.agent", RuntimeInitInjector.getDefaultUserAgent());
        NetworkManagementSocketTagger.install();
        if(SystemProperties.get("ro.kernel.android.tracing").equals("1"))
        {
            Slog.i("AndroidRuntime", "NOTE: emulator trace profiling enabled");
            Debug.enableEmulatorTraceOutput();
        }
        initialized = true;
    }

    static final void enableDdms()
    {
        DdmRegister.registerHandlers();
    }

    protected static Runnable findStaticMain(String s, String as[], ClassLoader classloader)
    {
        boolean flag = false;
        int i;
        try
        {
            classloader = Class.forName(s, true, classloader);
        }
        // Misplaced declaration of an exception variable
        catch(String as[])
        {
            throw new RuntimeException((new StringBuilder()).append("Missing class when invoking static main ").append(s).toString(), as);
        }
        try
        {
            classloader = classloader.getMethod("main", new Class[] {
                [Ljava/lang/String;
            });
        }
        // Misplaced declaration of an exception variable
        catch(String as[])
        {
            throw new RuntimeException((new StringBuilder()).append("Missing static main on ").append(s).toString(), as);
        }
        // Misplaced declaration of an exception variable
        catch(String as[])
        {
            throw new RuntimeException((new StringBuilder()).append("Problem getting static main on ").append(s).toString(), as);
        }
        i = classloader.getModifiers();
        if(Modifier.isStatic(i))
            flag = Modifier.isPublic(i);
        if(!flag)
            throw new RuntimeException((new StringBuilder()).append("Main method is not public and static on ").append(s).toString());
        else
            return new MethodAndArgsCaller(classloader, as);
    }

    public static final IBinder getApplicationObject()
    {
        return mApplicationObject;
    }

    private static String getDefaultUserAgent()
    {
        StringBuilder stringbuilder = new StringBuilder(64);
        stringbuilder.append("Dalvik/");
        stringbuilder.append(System.getProperty("java.vm.version"));
        stringbuilder.append(" (Linux; U; Android ");
        String s = android.os.Build.VERSION.RELEASE;
        if(s.length() <= 0)
            s = "1.0";
        stringbuilder.append(s);
        if("REL".equals(android.os.Build.VERSION.CODENAME))
        {
            s = Build.MODEL;
            if(s.length() > 0)
            {
                stringbuilder.append("; ");
                stringbuilder.append(s);
            }
        }
        s = Build.ID;
        if(s.length() > 0)
        {
            stringbuilder.append(" Build/");
            stringbuilder.append(s);
        }
        stringbuilder.append(")");
        return stringbuilder.toString();
    }

    public static final void main(String args[])
    {
        enableDdms();
        if(args.length == 2 && args[1].equals("application"))
            redirectLogStreams();
        commonInit();
        nativeFinishInit();
    }

    private static final native void nativeFinishInit();

    private static final native void nativeSetExitWithoutCleanup(boolean flag);

    public static void redirectLogStreams()
    {
        System.out.close();
        System.setOut(new AndroidPrintStream(4, "System.out"));
        System.err.close();
        System.setErr(new AndroidPrintStream(5, "System.err"));
    }

    public static final void removeFileForLowMem()
    {
        boolean flag = false;
        Object obj;
        StringBuilder stringbuilder;
        String as[];
        String s2;
        String as1[];
        int i;
        int j;
        try
        {
            String s = Environment.getDataDirectory().getAbsolutePath();
            String s1 = Environment.getExternalStorageDirectory().getAbsolutePath();
            as = new String[17];
            StringBuilder stringbuilder1 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            as[0] = stringbuilder1.append(s1).append("/downloaded_rom/").toString();
            stringbuilder1 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            as[1] = stringbuilder1.append(s1).append("/ramdump/").toString();
            stringbuilder1 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            as[2] = stringbuilder1.append(s1).append("/MIUI/debug_log/").toString();
            stringbuilder1 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            as[3] = stringbuilder1.append(s1).append("/miliao/").toString();
            stringbuilder1 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            as[4] = stringbuilder1.append(s1).append("/.xiaomi/").toString();
            stringbuilder1 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            as[5] = stringbuilder1.append(s1).append("/step_log/").toString();
            stringbuilder1 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            as[6] = stringbuilder1.append(s1).append("/Android/data/com.miui.cleanmaster/cache/").toString();
            stringbuilder1 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            as[7] = stringbuilder1.append(s1).append("/MIUI//music/album/").toString();
            stringbuilder1 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            as[8] = stringbuilder1.append(s1).append("/MIUI/music/avatar/").toString();
            stringbuilder1 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            as[9] = stringbuilder1.append(s1).append("/MIUI/music/lyric/").toString();
            stringbuilder1 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            as[10] = stringbuilder1.append(s1).append("/MIUI/.cache/resource/").toString();
            stringbuilder1 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            as[11] = stringbuilder1.append(s1).append("/MIUI/Gallery/cloud/.cache/").toString();
            stringbuilder1 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            as[12] = stringbuilder1.append(s1).append("/MIUI/Gallery/cloud/.microthumbnailFile/").toString();
            stringbuilder1 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            as[13] = stringbuilder1.append(s1).append("/MIUI/assistant/").toString();
            stringbuilder1 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            as[14] = stringbuilder1.append(s1).append("/DuoKan/Cache/").toString();
            stringbuilder1 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            as[15] = stringbuilder1.append(s1).append("/DuoKan/Downloads/Covers/").toString();
            stringbuilder1 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            as[16] = stringbuilder1.append(s1).append("/browser/MediaCache/").toString();
            as1 = new String[6];
            StringBuilder stringbuilder2 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder2.StringBuilder();
            as1[0] = stringbuilder2.append(s).append("/anr/").toString();
            stringbuilder2 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder2.StringBuilder();
            as1[1] = stringbuilder2.append(s).append("/tombstones/").toString();
            stringbuilder2 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder2.StringBuilder();
            as1[2] = stringbuilder2.append(s).append("/system/dropbox/").toString();
            stringbuilder2 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder2.StringBuilder();
            as1[3] = stringbuilder2.append(s).append("/system/app_screenshot/").toString();
            stringbuilder2 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder2.StringBuilder();
            as1[4] = stringbuilder2.append(s).append("/system/nativedebug/").toString();
            stringbuilder2 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder2.StringBuilder();
            as1[5] = stringbuilder2.append(s).append("/mqsas/").toString();
            stringbuilder2 = JVM INSTR new #234 <Class StringBuilder>;
            stringbuilder2.StringBuilder();
            Slog.d("AndroidRuntime", stringbuilder2.append("removeFileForLowMem: ").append(s).append(", ").append(s1).toString());
            i = as.length;
        }
        catch(Throwable throwable)
        {
            break; /* Loop/switch isn't completed */
        }
        j = 0;
        if(j >= i)
            break; /* Loop/switch isn't completed */
        obj = as[j];
        stringbuilder = JVM INSTR new #234 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Slog.d("AndroidRuntime", stringbuilder.append("removeFileForLowMem: ").append(((String) (obj))).toString());
        Shell.remove(((String) (obj)));
        j++;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_738;
_L1:
        i = as1.length;
        j = ((flag) ? 1 : 0);
_L4:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        s2 = as1[j];
        obj = JVM INSTR new #234 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.d("AndroidRuntime", ((StringBuilder) (obj)).append("removeFileForLowMem: ").append(s2).toString());
        obj = JVM INSTR new #351 <Class File>;
        ((File) (obj)).File(s2);
        FileUtils.deleteContents(((File) (obj)));
        j++;
        continue; /* Loop/switch isn't completed */
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static final void setApplicationObject(IBinder ibinder)
    {
        mApplicationObject = ibinder;
    }

    public static void wtf(String s, Throwable throwable, boolean flag)
    {
        IActivityManager iactivitymanager = ActivityManager.getService();
        IBinder ibinder = mApplicationObject;
        android.app.ApplicationErrorReport.ParcelableCrashInfo parcelablecrashinfo = JVM INSTR new #435 <Class android.app.ApplicationErrorReport$ParcelableCrashInfo>;
        parcelablecrashinfo.android.app.ApplicationErrorReport.ParcelableCrashInfo(throwable);
        if(iactivitymanager.handleApplicationWtf(ibinder, s, flag, parcelablecrashinfo))
        {
            Process.killProcess(Process.myPid());
            System.exit(10);
        }
_L1:
        return;
        s;
        if(!(s instanceof DeadObjectException))
        {
            Slog.e("AndroidRuntime", "Error reporting WTF", s);
            Slog.e("AndroidRuntime", "Original WTF:", throwable);
        }
          goto _L1
    }

    static final boolean DEBUG = false;
    static final String TAG = "AndroidRuntime";
    private static boolean initialized;
    private static IBinder mApplicationObject;
    private static volatile boolean mCrashing = false;

}

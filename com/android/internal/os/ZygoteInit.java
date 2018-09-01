// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.icu.impl.CacheValue;
import android.icu.text.DecimalFormatSymbols;
import android.icu.util.ULocale;
import android.opengl.EGL14;
import android.os.*;
import android.os.storage.StorageManager;
import android.security.keystore.AndroidKeyStoreProvider;
import android.system.*;
import android.text.Hyphenator;
import android.util.*;
import android.webkit.WebViewFactory;
import android.widget.TextView;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.util.Preconditions;
import dalvik.system.*;
import java.io.*;
import java.security.Provider;
import java.security.Security;
import libcore.io.IoUtils;
import miui.security.SecurityManager;

// Referenced classes of package com.android.internal.os:
//            ClassLoaderFactory, ZygoteConnection, Zygote, ZygoteServer, 
//            WrapperInit, RuntimeInit, ZygoteInitInjector

public class ZygoteInit
{

    private ZygoteInit()
    {
    }

    private static void beginIcuCachePinning()
    {
        int i = 0;
        Log.i("Zygote", "Installing ICU cache reference pinning...");
        CacheValue.setStrength(android.icu.impl.CacheValue.Strength.STRONG);
        Log.i("Zygote", "Preloading ICU data...");
        ULocale aulocale[] = new ULocale[3];
        aulocale[0] = ULocale.ROOT;
        aulocale[1] = ULocale.US;
        aulocale[2] = ULocale.getDefault();
        for(int j = aulocale.length; i < j; i++)
            new DecimalFormatSymbols(aulocale[i]);

    }

    static ClassLoader createPathClassLoader(String s, int i)
    {
        String s1 = System.getProperty("java.library.path");
        return ClassLoaderFactory.createClassLoader(s, s1, s1, ClassLoader.getSystemClassLoader(), i, true, null);
    }

    private static String encodeSystemServerClassPath(String s, String s1)
    {
        String s2 = s1;
        if(s != null)
            if(s.isEmpty())
                s2 = s1;
            else
                s2 = (new StringBuilder()).append(s).append(":").append(s1).toString();
        return s2;
    }

    private static void endIcuCachePinning()
    {
        CacheValue.setStrength(android.icu.impl.CacheValue.Strength.SOFT);
        Log.i("Zygote", "Uninstalled ICU cache reference pinning...");
    }

    private static Runnable forkSystemServer(String s, String s1, ZygoteServer zygoteserver)
    {
        String s2;
        long l = posixCapabilitiesAsBits(new int[] {
            OsConstants.CAP_IPC_LOCK, OsConstants.CAP_KILL, OsConstants.CAP_NET_ADMIN, OsConstants.CAP_NET_BIND_SERVICE, OsConstants.CAP_NET_BROADCAST, OsConstants.CAP_NET_RAW, OsConstants.CAP_SYS_MODULE, OsConstants.CAP_SYS_NICE, OsConstants.CAP_SYS_RESOURCE, OsConstants.CAP_SYS_PTRACE, 
            OsConstants.CAP_SYS_TIME, OsConstants.CAP_SYS_TTY_CONFIG, OsConstants.CAP_WAKE_ALARM
        });
        long l1 = l;
        if(!SystemProperties.getBoolean("ro.boot.container", false))
            l1 = l | posixCapabilitiesAsBits(new int[] {
                OsConstants.CAP_BLOCK_SUSPEND
            });
        s2 = (new StringBuilder()).append("--capabilities=").append(l1).append(",").append(l1).toString();
        ZygoteConnection.Arguments arguments;
        arguments = JVM INSTR new #214 <Class ZygoteConnection$Arguments>;
        arguments.ZygoteConnection.Arguments(new String[] {
            "--setuid=1000", "--setgid=1000", "--setgroups=1001,1002,1003,1004,1005,1006,1007,1008,1009,1010,1018,1021,1023,1032,1065,3001,3002,3003,3006,3007,3009,3010,9801", s2, "--nice-name=system_server", "--runtime-args", "com.android.server.SystemServer"
        });
        int i;
        ZygoteConnection.applyDebuggerSystemProperty(arguments);
        ZygoteConnection.applyInvokeWithSystemProperty(arguments);
        i = Zygote.forkSystemServer(arguments.uid, arguments.gid, arguments.gids, arguments.debugFlags, null, arguments.permittedCapabilities, arguments.effectiveCapabilities);
        if(i == 0)
        {
            if(hasSecondZygote(s))
                waitForSecondaryZygote(s1);
            zygoteserver.closeServerSocket();
            return handleSystemServerProcess(arguments);
        } else
        {
            return null;
        }
        s;
_L2:
        throw new RuntimeException(s);
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    static void gcAndFinalize()
    {
        VMRuntime vmruntime = VMRuntime.getRuntime();
        System.gc();
        vmruntime.runFinalizationSync();
        System.gc();
    }

    private static String getSystemServerClassLoaderContext(String s)
    {
        if(s == null)
            s = "PCL[]";
        else
            s = (new StringBuilder()).append("PCL[").append(s).append("]").toString();
        return s;
    }

    private static Runnable handleSystemServerProcess(ZygoteConnection.Arguments arguments)
    {
        Os.umask(OsConstants.S_IRWXG | OsConstants.S_IRWXO);
        if(arguments.niceName != null)
            Process.setArgV0(arguments.niceName);
        String s = Os.getenv("SYSTEMSERVERCLASSPATH");
        if(s != null)
        {
            performSystemServerDexOpt(s);
            String as1[];
            String as2[];
            if(SystemProperties.getBoolean("dalvik.vm.profilesystemserver", false) && (Build.IS_USERDEBUG || Build.IS_ENG))
                try
                {
                    File file = Environment.getDataProfilesDePackageDirectory(1000, "system_server");
                    File file1 = JVM INSTR new #354 <Class File>;
                    file1.File(file, "primary.prof");
                    file1.getParentFile().mkdirs();
                    file1.createNewFile();
                    String as[] = s.split(":");
                    VMRuntime.registerAppInfo(file1.getPath(), as);
                }
                catch(Exception exception)
                {
                    Log.wtf("Zygote", "Failed to set up system server profile", exception);
                }
        }
        if(arguments.invokeWith != null)
        {
            as1 = arguments.remainingArgs;
            as2 = as1;
            if(s != null)
            {
                as2 = new String[as1.length + 2];
                as2[0] = "-cp";
                as2[1] = s;
                System.arraycopy(as1, 0, as2, 2, as1.length);
            }
            WrapperInit.execApplication(arguments.invokeWith, arguments.niceName, arguments.targetSdkVersion, VMRuntime.getCurrentInstructionSet(), null, as2);
            throw new IllegalStateException("Unexpected return from WrapperInit.execApplication");
        }
        ClassLoader classloader = null;
        if(s != null)
        {
            classloader = createPathClassLoader(s, arguments.targetSdkVersion);
            Thread.currentThread().setContextClassLoader(classloader);
        }
        return zygoteInit(arguments.targetSdkVersion, arguments.remainingArgs, classloader);
    }

    private static boolean hasSecondZygote(String s)
    {
        return SystemProperties.get("ro.product.cpu.abilist").equals(s) ^ true;
    }

    static boolean isPreloadComplete()
    {
        return sPreloadComplete;
    }

    public static void lazyPreload()
    {
        Preconditions.checkState(sPreloadComplete ^ true);
        Log.i("Zygote", "Lazily preloading resources.");
        preload(new TimingsTraceLog("ZygoteInitTiming_lazy", 16384L));
    }

    public static void main(String args[])
    {
        ZygoteServer zygoteserver;
        Object obj;
        TimingsTraceLog timingstracelog;
        boolean flag;
        Object obj1;
        boolean flag1;
        int i;
        zygoteserver = new ZygoteServer();
        ZygoteHooks.startZygoteNoThreadCreation();
        boolean flag2;
        try
        {
            Os.setpgid(0, 0);
        }
        // Misplaced declaration of an exception variable
        catch(String args[])
        {
            throw new RuntimeException("Failed to setpgid(0,0)", args);
        }
        if(!"1".equals(SystemProperties.get("sys.boot_completed")))
            MetricsLogger.histogram(null, "boot_zygote_init", (int)SystemClock.elapsedRealtime());
        if(Process.is64Bit())
            obj = "Zygote64Timing";
        else
            obj = "Zygote32Timing";
        timingstracelog = JVM INSTR new #456 <Class TimingsTraceLog>;
        timingstracelog.TimingsTraceLog(((String) (obj)), 16384L);
        timingstracelog.traceBegin("ZygoteInit");
        RuntimeInit.enableDdms();
        SecurityManager.init();
        flag = false;
        obj1 = "zygote";
        obj = null;
        flag1 = false;
        i = 1;
_L2:
        if(i >= args.length)
            break MISSING_BLOCK_LABEL_280;
        flag2 = "start-system-server".equals(args[i]);
        if(!flag2)
            break; /* Loop/switch isn't completed */
        flag = true;
_L3:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        if(!"--enable-lazy-preload".equals(args[i]))
            break MISSING_BLOCK_LABEL_165;
        flag1 = true;
          goto _L3
label0:
        {
            if(!args[i].startsWith("--abi-list="))
                break label0;
            obj = args[i].substring("--abi-list=".length());
        }
          goto _L3
label1:
        {
            if(!args[i].startsWith("--socket-name="))
                break label1;
            obj1 = args[i].substring("--socket-name=".length());
        }
          goto _L3
        try
        {
            obj1 = JVM INSTR new #281 <Class RuntimeException>;
            obj = JVM INSTR new #130 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            ((RuntimeException) (obj1)).RuntimeException(((StringBuilder) (obj)).append("Unknown command line argument: ").append(args[i]).toString());
            throw obj1;
        }
        // Misplaced declaration of an exception variable
        catch(String args[]) { }
        Log.e("Zygote", "System zygote died with exception", args);
        throw args;
        args;
        zygoteserver.closeServerSocket();
        throw args;
        if(obj != null)
            break MISSING_BLOCK_LABEL_297;
        args = JVM INSTR new #281 <Class RuntimeException>;
        args.RuntimeException("No ABI list supplied.");
        throw args;
        zygoteserver.registerServerSocket(((String) (obj1)));
        if(flag1)
            break MISSING_BLOCK_LABEL_403;
        timingstracelog.traceBegin("ZygotePreload");
        EventLog.writeEvent(3020, SystemClock.uptimeMillis());
        preload(timingstracelog);
        EventLog.writeEvent(3030, SystemClock.uptimeMillis());
        timingstracelog.traceEnd();
_L4:
        timingstracelog.traceBegin("PostZygoteInitGC");
        gcAndFinalize();
        timingstracelog.traceEnd();
        timingstracelog.traceEnd();
        Trace.setTracingEnabled(false, 0);
        Zygote.nativeUnmountStorageOnInit();
        Seccomp.setPolicy();
        ZygoteHooks.stopZygoteNoThreadCreation();
        if(!flag)
            break MISSING_BLOCK_LABEL_409;
        args = forkSystemServer(((String) (obj)), ((String) (obj1)), zygoteserver);
        if(args == null)
            break MISSING_BLOCK_LABEL_409;
        args.run();
        zygoteserver.closeServerSocket();
        return;
        Zygote.resetNicePriority();
          goto _L4
        Log.i("Zygote", "Accepting command socket connections");
        args = zygoteserver.runSelectLoop(((String) (obj)));
        zygoteserver.closeServerSocket();
        if(args != null)
            args.run();
        return;
    }

    private static native void nativePreloadAppProcessHALs();

    private static final native void nativeZygoteInit();

    private static void performSystemServerDexOpt(String s)
    {
        String as[];
        IInstalld iinstalld;
        String s1;
        int i;
        int j;
        as = s.split(":");
        iinstalld = android.os.IInstalld.Stub.asInterface(ServiceManager.getService("installd"));
        s1 = VMRuntime.getRuntime().vmInstructionSet();
        s = "";
        i = as.length;
        j = 0;
_L4:
        String s2;
        String s3;
        if(j >= i)
            break MISSING_BLOCK_LABEL_230;
        s2 = as[j];
        s3 = SystemProperties.get("dalvik.vm.systemservercompilerfilter", "speed");
        int k = DexFile.getDexOptNeeded(s2, s1, s3, false, false);
_L1:
        String s4;
        String s5;
        if(k == 0)
            break MISSING_BLOCK_LABEL_114;
        s4 = StorageManager.UUID_PRIVATE_INTERNAL;
        s5 = getSystemServerClassLoaderContext(s);
        FileNotFoundException filenotfoundexception;
        IOException ioexception;
        try
        {
            iinstalld.dexopt(s2, 1000, "*", s1, k, null, 0, s3, s4, s5, null, false);
        }
        catch(Object obj)
        {
            Log.w("Zygote", (new StringBuilder()).append("Failed compiling classpath element for system server: ").append(s2).toString(), ((Throwable) (obj)));
        }
        s = encodeSystemServerClassPath(s, s2);
_L2:
        j++;
        continue; /* Loop/switch isn't completed */
        ioexception;
        Log.w("Zygote", (new StringBuilder()).append("Error checking classpath element for system server: ").append(s2).toString(), ioexception);
        k = 0;
          goto _L1
        filenotfoundexception;
        Log.w("Zygote", (new StringBuilder()).append("Missing classpath element for system server: ").append(s2).toString());
          goto _L2
        return;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static transient long posixCapabilitiesAsBits(int ai[])
    {
        int i = 0;
        long l = 0L;
        for(int j = ai.length; i < j; i++)
        {
            int k = ai[i];
            if(k < 0 || k > OsConstants.CAP_LAST_CAP)
                throw new IllegalArgumentException(String.valueOf(k));
            l |= 1L << k;
        }

        return l;
    }

    static void preload(TimingsTraceLog timingstracelog)
    {
        Log.d("Zygote", "begin preload");
        timingstracelog.traceBegin("BeginIcuCachePinning");
        beginIcuCachePinning();
        timingstracelog.traceEnd();
        timingstracelog.traceBegin("PreloadClasses");
        preloadClasses();
        timingstracelog.traceEnd();
        timingstracelog.traceBegin("PreloadResources");
        preloadResources();
        timingstracelog.traceEnd();
        Trace.traceBegin(16384L, "PreloadAppProcessHALs");
        nativePreloadAppProcessHALs();
        Trace.traceEnd(16384L);
        Trace.traceBegin(16384L, "PreloadOpenGL");
        preloadOpenGL();
        Trace.traceEnd(16384L);
        preloadSharedLibraries();
        preloadTextResources();
        WebViewFactory.prepareWebViewInZygote();
        endIcuCachePinning();
        warmUpJcaProviders();
        Log.d("Zygote", "end preload");
        sPreloadComplete = true;
    }

    private static void preloadClasses()
    {
        VMRuntime vmruntime;
        Object obj;
        long l;
        int k;
        boolean flag;
        float f;
        Object obj2;
        vmruntime = VMRuntime.getRuntime();
        int i;
        int j;
        try
        {
            obj = new FileInputStream("/system/etc/preloaded-classes");
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.e("Zygote", "Couldn't find /system/etc/preloaded-classes.");
            return;
        }
        Log.i("Zygote", "Preloading classes...");
        l = SystemClock.uptimeMillis();
        i = Os.getuid();
        j = Os.getgid();
        k = 0;
        flag = k;
        if(i == 0)
        {
            flag = k;
            if(j == 0)
            {
                BufferedReader bufferedreader;
                IOException ioexception;
                try
                {
                    Os.setregid(0, 9999);
                    Os.setreuid(0, 9999);
                }
                // Misplaced declaration of an exception variable
                catch(Object obj)
                {
                    throw new RuntimeException("Failed to drop root", ((Throwable) (obj)));
                }
                flag = true;
            }
        }
        f = vmruntime.getTargetHeapUtilization();
        vmruntime.setTargetHeapUtilization(0.8F);
        bufferedreader = JVM INSTR new #768 <Class BufferedReader>;
        obj2 = JVM INSTR new #770 <Class InputStreamReader>;
        ((InputStreamReader) (obj2)).InputStreamReader(((java.io.InputStream) (obj)));
        bufferedreader.BufferedReader(((java.io.Reader) (obj2)), 256);
        k = 0;
_L4:
        obj2 = bufferedreader.readLine();
        if(obj2 == null) goto _L2; else goto _L1
_L1:
        obj2 = ((String) (obj2)).trim();
        if(((String) (obj2)).startsWith("#") || ((String) (obj2)).equals("")) goto _L4; else goto _L3
_L3:
        Trace.traceBegin(16384L, ((String) (obj2)));
        Class.forName(((String) (obj2)), true, null);
        k++;
_L5:
        Trace.traceEnd(16384L);
          goto _L4
        ioexception;
        Log.e("Zygote", "Error reading /system/etc/preloaded-classes.", ioexception);
        IoUtils.closeQuietly(((AutoCloseable) (obj)));
        vmruntime.setTargetHeapUtilization(f);
        Trace.traceBegin(16384L, "PreloadDexCaches");
        vmruntime.preloadDexCaches();
        Trace.traceEnd(16384L);
        if(!flag)
            break MISSING_BLOCK_LABEL_245;
        Os.setreuid(0, 0);
        Os.setregid(0, 0);
_L6:
        return;
        Object obj1;
        obj1;
        StringBuilder stringbuilder1 = JVM INSTR new #130 <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        Log.e("Zygote", stringbuilder1.append("Error preloading ").append(((String) (obj2))).append(".").toString(), ((Throwable) (obj1)));
        if(obj1 instanceof Error)
            throw (Error)obj1;
        break MISSING_BLOCK_LABEL_376;
        obj1;
        IoUtils.closeQuietly(((AutoCloseable) (obj)));
        vmruntime.setTargetHeapUtilization(f);
        Trace.traceBegin(16384L, "PreloadDexCaches");
        vmruntime.preloadDexCaches();
        Trace.traceEnd(16384L);
        ErrnoException errnoexception;
        StringBuilder stringbuilder;
        Object obj3;
        StringBuilder stringbuilder2;
        StringBuilder stringbuilder3;
        if(flag)
            try
            {
                Os.setreuid(0, 0);
                Os.setregid(0, 0);
            }
            catch(ErrnoException errnoexception1)
            {
                throw new RuntimeException("Failed to restore root", errnoexception1);
            }
        throw obj1;
        if(obj1 instanceof RuntimeException)
        {
            throw (RuntimeException)obj1;
        } else
        {
            obj2 = JVM INSTR new #281 <Class RuntimeException>;
            ((RuntimeException) (obj2)).RuntimeException(((Throwable) (obj1)));
            throw obj2;
        }
        obj3;
        stringbuilder3 = JVM INSTR new #130 <Class StringBuilder>;
        stringbuilder3.StringBuilder();
        Log.w("Zygote", stringbuilder3.append("Problem preloading ").append(((String) (obj2))).append(": ").append(obj3).toString());
          goto _L5
        obj3;
        stringbuilder2 = JVM INSTR new #130 <Class StringBuilder>;
        stringbuilder2.StringBuilder();
        Log.w("Zygote", stringbuilder2.append("Class not found for preloading: ").append(((String) (obj2))).toString());
          goto _L5
_L2:
        stringbuilder = JVM INSTR new #130 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.i("Zygote", stringbuilder.append("...preloaded ").append(k).append(" classes in ").append(SystemClock.uptimeMillis() - l).append("ms.").toString());
        IoUtils.closeQuietly(((AutoCloseable) (obj)));
        vmruntime.setTargetHeapUtilization(f);
        Trace.traceBegin(16384L, "PreloadDexCaches");
        vmruntime.preloadDexCaches();
        Trace.traceEnd(16384L);
        if(flag)
            try
            {
                Os.setreuid(0, 0);
                Os.setregid(0, 0);
            }
            // Misplaced declaration of an exception variable
            catch(ErrnoException errnoexception)
            {
                throw new RuntimeException("Failed to restore root", errnoexception);
            }
          goto _L6
        errnoexception;
        throw new RuntimeException("Failed to restore root", errnoexception);
          goto _L5
    }

    private static int preloadColorStateLists(TypedArray typedarray)
    {
        int i = typedarray.length();
        for(int j = 0; j < i; j++)
        {
            int k = typedarray.getResourceId(j, 0);
            if(k != 0 && mResources.getColorStateList(k, null) == null)
                throw new IllegalArgumentException((new StringBuilder()).append("Unable to find preloaded color resource #0x").append(Integer.toHexString(k)).append(" (").append(typedarray.getString(j)).append(")").toString());
        }

        return i;
    }

    private static int preloadDrawables(TypedArray typedarray)
    {
        int i = typedarray.length();
        for(int j = 0; j < i; j++)
        {
            int k = typedarray.getResourceId(j, 0);
            if(k != 0 && mResources.getDrawable(k, null) == null)
                throw new IllegalArgumentException((new StringBuilder()).append("Unable to find preloaded drawable resource #0x").append(Integer.toHexString(k)).append(" (").append(typedarray.getString(j)).append(")").toString());
        }

        return i;
    }

    private static void preloadOpenGL()
    {
        String s = SystemProperties.get("ro.gfx.driver.0");
        if(!SystemProperties.getBoolean("ro.zygote.disable_gl_preload", false) && (s == null || s.isEmpty()))
            EGL14.eglGetDisplay(0);
    }

    private static void preloadResources()
    {
        VMRuntime.getRuntime();
        mResources = Resources.getSystem();
        mResources.startPreloading();
        Log.i("Zygote", "Preloading resources...");
        long l = SystemClock.uptimeMillis();
        Object obj = mResources.obtainTypedArray(0x107005e);
        int i = preloadDrawables(((TypedArray) (obj)));
        ((TypedArray) (obj)).recycle();
        obj = JVM INSTR new #130 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Log.i("Zygote", ((StringBuilder) (obj)).append("...preloaded ").append(i).append(" resources in ").append(SystemClock.uptimeMillis() - l).append("ms.").toString());
        l = SystemClock.uptimeMillis();
        obj = mResources.obtainTypedArray(0x107005d);
        i = preloadColorStateLists(((TypedArray) (obj)));
        ((TypedArray) (obj)).recycle();
        obj = JVM INSTR new #130 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Log.i("Zygote", ((StringBuilder) (obj)).append("...preloaded ").append(i).append(" resources in ").append(SystemClock.uptimeMillis() - l).append("ms.").toString());
        ZygoteInitInjector.preloadMiuiResources(mResources);
        if(mResources.getBoolean(0x1120070))
        {
            long l1 = SystemClock.uptimeMillis();
            Object obj1 = mResources.obtainTypedArray(0x107005f);
            int j = preloadDrawables(((TypedArray) (obj1)));
            ((TypedArray) (obj1)).recycle();
            obj1 = JVM INSTR new #130 <Class StringBuilder>;
            ((StringBuilder) (obj1)).StringBuilder();
            Log.i("Zygote", ((StringBuilder) (obj1)).append("...preloaded ").append(j).append(" resource in ").append(SystemClock.uptimeMillis() - l1).append("ms.").toString());
        }
        mResources.finishPreloading();
_L1:
        return;
        RuntimeException runtimeexception;
        runtimeexception;
        Log.w("Zygote", "Failure preloading resources", runtimeexception);
          goto _L1
    }

    private static void preloadSharedLibraries()
    {
        Log.i("Zygote", "Preloading shared libraries...");
        System.loadLibrary("android");
        System.loadLibrary("compiler_rt");
        System.loadLibrary("jnigraphics");
    }

    private static void preloadTextResources()
    {
        Hyphenator.init();
        TextView.preloadFontCache();
    }

    private static void waitForSecondaryZygote(String s)
    {
        if("zygote".equals(s))
            s = "zygote_secondary";
        else
            s = "zygote";
        ZygoteProcess.waitForConnectionToZygote(s);
    }

    private static void warmUpJcaProviders()
    {
        long l = SystemClock.uptimeMillis();
        Trace.traceBegin(16384L, "Starting installation of AndroidKeyStoreProvider");
        AndroidKeyStoreProvider.install();
        Log.i("Zygote", (new StringBuilder()).append("Installed AndroidKeyStoreProvider in ").append(SystemClock.uptimeMillis() - l).append("ms.").toString());
        Trace.traceEnd(16384L);
        l = SystemClock.uptimeMillis();
        Trace.traceBegin(16384L, "Starting warm up of JCA providers");
        Provider aprovider[] = Security.getProviders();
        int i = 0;
        for(int j = aprovider.length; i < j; i++)
            aprovider[i].warmUpServiceProvision();

        Log.i("Zygote", (new StringBuilder()).append("Warmed up JCA providers in ").append(SystemClock.uptimeMillis() - l).append("ms.").toString());
        Trace.traceEnd(16384L);
    }

    public static final Runnable zygoteInit(int i, String as[], ClassLoader classloader)
    {
        Trace.traceBegin(64L, "ZygoteInit");
        RuntimeInit.redirectLogStreams();
        RuntimeInit.commonInit();
        nativeZygoteInit();
        return RuntimeInit.applicationInit(i, as, classloader);
    }

    private static final String ABI_LIST_ARG = "--abi-list=";
    public static long BOOT_START_TIME = 0L;
    private static final int LOG_BOOT_PROGRESS_PRELOAD_END = 3030;
    private static final int LOG_BOOT_PROGRESS_PRELOAD_START = 3020;
    private static final String PRELOADED_CLASSES = "/system/etc/preloaded-classes";
    private static final int PRELOAD_GC_THRESHOLD = 50000;
    public static final boolean PRELOAD_RESOURCES = true;
    private static final String PROPERTY_DISABLE_OPENGL_PRELOADING = "ro.zygote.disable_gl_preload";
    private static final String PROPERTY_GFX_DRIVER = "ro.gfx.driver.0";
    private static final String PROPERTY_RUNNING_IN_CONTAINER = "ro.boot.container";
    private static final int ROOT_GID = 0;
    private static final int ROOT_UID = 0;
    private static final String SOCKET_NAME_ARG = "--socket-name=";
    private static final String TAG = "Zygote";
    private static final int UNPRIVILEGED_GID = 9999;
    private static final int UNPRIVILEGED_UID = 9999;
    private static Resources mResources;
    private static boolean sPreloadComplete;

    static 
    {
        BOOT_START_TIME = 0L;
    }
}

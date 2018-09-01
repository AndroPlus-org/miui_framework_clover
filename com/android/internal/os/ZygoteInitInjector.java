// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.*;
import android.util.Log;
import dalvik.system.VMRuntime;
import java.io.*;
import java.lang.reflect.Constructor;
import libcore.io.IoUtils;
import miui.util.ObjectReference;
import miui.util.ReflectionUtils;

// Referenced classes of package com.android.internal.os:
//            ZygoteInit

class ZygoteInitInjector
{

    ZygoteInitInjector()
    {
    }

    static void preloadBoostFramework()
    {
        Object obj = Class.forName("android.util.BoostFramework");
        if(obj == null)
            return;
        obj = ((Class) (obj)).getConstructor(new Class[0]);
        if(obj == null)
            break MISSING_BLOCK_LABEL_33;
        ((Constructor) (obj)).newInstance(new Object[0]);
        Log.d("ZygoteInitInjector", "preload BoostFramework succeed.");
_L1:
        return;
        Exception exception;
        exception;
        Log.e("ZygoteInitInjector", "preload class android.util.BoostFramework failed");
          goto _L1
    }

    static void preloadGralloc()
    {
        String s;
        Object obj;
        s = SystemProperties.get("ro.board.platform", "default");
        obj = (new StringBuilder()).append("/system/lib/hw/gralloc.").append(s).append(".so").toString();
        s = (new StringBuilder()).append("/system/lib64/hw/gralloc.").append(s).append(".so").toString();
        System.load(((String) (obj)));
        obj = JVM INSTR new #102 <Class File>;
        ((File) (obj)).File(s);
        if(((File) (obj)).exists())
            System.load(s);
_L1:
        return;
        UnsatisfiedLinkError unsatisfiedlinkerror;
        unsatisfiedlinkerror;
        Log.e("ZygoteInitInjector", (new StringBuilder()).append("failed load gralloc lib : ").append(unsatisfiedlinkerror).toString());
          goto _L1
    }

    static void preloadMiuiClasses()
    {
        VMRuntime vmruntime;
        java.io.InputStream inputstream;
        vmruntime = VMRuntime.getRuntime();
        Log.i("ZygoteInitInjector", "Preload miui classes...");
        inputstream = ClassLoader.getSystemClassLoader().getResourceAsStream("preloaded-miui-classes");
        if(inputstream != null) goto _L2; else goto _L1
_L1:
        Log.e("ZygoteInitInjector", "Couldn't find preloaded-miui-classes.");
_L9:
        return;
_L2:
        long l;
        ObjectReference objectreference;
        ObjectReference objectreference1;
        float f;
        Log.i("ZygoteInitInjector", "Preloading miui classes...");
        l = SystemClock.uptimeMillis();
        objectreference = ReflectionUtils.tryGetStaticObjectField(com/android/internal/os/ZygoteInit, "UNPRIVILEGED_GID", java/lang/Integer);
        objectreference1 = ReflectionUtils.tryGetStaticObjectField(com/android/internal/os/ZygoteInit, "UNPRIVILEGED_UID", java/lang/Integer);
        ReflectionUtils.tryCallStaticMethod(com/android/internal/os/ZygoteInit, "setEffectiveGroup", java/lang/Void, new Object[] {
            Integer.valueOf(((Integer)objectreference.get()).intValue())
        });
        ReflectionUtils.tryCallStaticMethod(com/android/internal/os/ZygoteInit, "setEffectiveGroup", java/lang/Void, new Object[] {
            Integer.valueOf(((Integer)objectreference1.get()).intValue())
        });
        f = vmruntime.getTargetHeapUtilization();
        vmruntime.setTargetHeapUtilization(0.8F);
        System.gc();
        vmruntime.runFinalizationSync();
        Debug.startAllocCounting();
        BufferedReader bufferedreader;
        bufferedreader = JVM INSTR new #206 <Class BufferedReader>;
        InputStreamReader inputstreamreader = JVM INSTR new #208 <Class InputStreamReader>;
        inputstreamreader.InputStreamReader(inputstream);
        bufferedreader.BufferedReader(inputstreamreader, 256);
        int i = 0;
_L4:
        Object obj1 = bufferedreader.readLine();
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_623;
        obj1 = ((String) (obj1)).trim();
        if(((String) (obj1)).startsWith("#")) goto _L4; else goto _L3
_L3:
        boolean flag = ((String) (obj1)).equals("");
        if(flag) goto _L4; else goto _L5
_L5:
        Class.forName(((String) (obj1)));
        ObjectReference objectreference2 = ReflectionUtils.tryGetStaticObjectField(com/android/internal/os/ZygoteInit, "PRELOAD_GC_THRESHOLD", java/lang/Integer);
        if(Debug.getGlobalAllocSize() > ((Integer)objectreference2.get()).intValue())
        {
            System.gc();
            vmruntime.runFinalizationSync();
            Debug.resetGlobalAllocSize();
        }
        i++;
          goto _L4
        Object obj;
        obj;
        StringBuilder stringbuilder1 = JVM INSTR new #79  <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        Log.e("ZygoteInitInjector", stringbuilder1.append("Error preloading ").append(((String) (obj1))).append(".").toString(), ((Throwable) (obj)));
        if(obj instanceof Error)
            throw (Error)obj;
          goto _L6
_L7:
        Log.e("ZygoteInitInjector", "Error reading preloaded-miui-classes.", ((Throwable) (obj)));
        IoUtils.closeQuietly(inputstream);
        vmruntime.setTargetHeapUtilization(f);
        vmruntime.preloadDexCaches();
        Debug.stopAllocCounting();
        ReflectionUtils.tryCallStaticMethod(com/android/internal/os/ZygoteInit, "setEffectiveGroup", java/lang/Void, new Object[] {
            Integer.valueOf(((Integer)objectreference.get()).intValue())
        });
        ReflectionUtils.tryCallStaticMethod(com/android/internal/os/ZygoteInit, "setEffectiveGroup", java/lang/Void, new Object[] {
            Integer.valueOf(((Integer)objectreference1.get()).intValue())
        });
        continue; /* Loop/switch isn't completed */
_L6:
        if(obj instanceof RuntimeException)
            throw (RuntimeException)obj;
        break MISSING_BLOCK_LABEL_523;
        obj;
        IoUtils.closeQuietly(inputstream);
        vmruntime.setTargetHeapUtilization(f);
        vmruntime.preloadDexCaches();
        Debug.stopAllocCounting();
        ReflectionUtils.tryCallStaticMethod(com/android/internal/os/ZygoteInit, "setEffectiveGroup", java/lang/Void, new Object[] {
            Integer.valueOf(((Integer)objectreference.get()).intValue())
        });
        ReflectionUtils.tryCallStaticMethod(com/android/internal/os/ZygoteInit, "setEffectiveGroup", java/lang/Void, new Object[] {
            Integer.valueOf(((Integer)objectreference1.get()).intValue())
        });
        throw obj;
        try
        {
            obj1 = JVM INSTR new #267 <Class RuntimeException>;
            ((RuntimeException) (obj1)).RuntimeException(((Throwable) (obj)));
            throw obj1;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj) { }
          goto _L7
        Object obj2;
        obj2;
        StringBuilder stringbuilder3 = JVM INSTR new #79  <Class StringBuilder>;
        stringbuilder3.StringBuilder();
        Log.w("ZygoteInitInjector", stringbuilder3.append("Problem preloading ").append(((String) (obj1))).append(": ").append(obj2).toString());
          goto _L4
        obj2;
        StringBuilder stringbuilder2 = JVM INSTR new #79  <Class StringBuilder>;
        stringbuilder2.StringBuilder();
        Log.w("ZygoteInitInjector", stringbuilder2.append("Class not found for preloading: ").append(((String) (obj1))).toString());
          goto _L4
        StringBuilder stringbuilder = JVM INSTR new #79  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.i("ZygoteInitInjector", stringbuilder.append("...preloaded ").append(i).append(" classes in ").append(SystemClock.uptimeMillis() - l).append("ms.").toString());
        IoUtils.closeQuietly(inputstream);
        vmruntime.setTargetHeapUtilization(f);
        vmruntime.preloadDexCaches();
        Debug.stopAllocCounting();
        ReflectionUtils.tryCallStaticMethod(com/android/internal/os/ZygoteInit, "setEffectiveGroup", java/lang/Void, new Object[] {
            Integer.valueOf(((Integer)objectreference.get()).intValue())
        });
        ReflectionUtils.tryCallStaticMethod(com/android/internal/os/ZygoteInit, "setEffectiveGroup", java/lang/Void, new Object[] {
            Integer.valueOf(((Integer)objectreference1.get()).intValue())
        });
        if(true) goto _L9; else goto _L8
_L8:
    }

    private static void preloadMiuiClassesHigherThanL()
    {
        VMRuntime vmruntime;
        FileInputStream fileinputstream;
        long l;
        Object obj;
        boolean flag1;
        float f;
        vmruntime = VMRuntime.getRuntime();
        try
        {
            fileinputstream = new FileInputStream("/system/etc/preloaded-miui-classes");
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.e("ZygoteInitInjector", "Couldn't find /system/etc/preloaded-miui-classes.");
            return;
        }
        Log.i("ZygoteInitInjector", "Preloading classes...");
        l = SystemClock.uptimeMillis();
        obj = ReflectionUtils.tryFindClass("android.system.Os", null);
        if(obj == null)
        {
            Log.e("ZygoteInitInjector", "class android.system.Os is not found.");
            IoUtils.closeQuietly(fileinputstream);
            return;
        }
        int i = ((Integer)ReflectionUtils.tryCallStaticMethod(((Class) (obj)), "getuid", java/lang/Integer, new Object[0]).get()).intValue();
        int j = ((Integer)ReflectionUtils.tryCallStaticMethod(((Class) (obj)), "getgid", java/lang/Integer, new Object[0]).get()).intValue();
        boolean flag = false;
        flag1 = flag;
        if(i == 0)
        {
            flag1 = flag;
            if(j == 0)
            {
                ReflectionUtils.tryCallStaticMethod(((Class) (obj)), "setregid", java/lang/Void, new Object[] {
                    Integer.valueOf(0), Integer.valueOf(9999)
                });
                ReflectionUtils.tryCallStaticMethod(((Class) (obj)), "setreuid", java/lang/Void, new Object[] {
                    Integer.valueOf(0), Integer.valueOf(9999)
                });
                flag1 = true;
            }
        }
        f = vmruntime.getTargetHeapUtilization();
        vmruntime.setTargetHeapUtilization(0.8F);
        BufferedReader bufferedreader;
        bufferedreader = JVM INSTR new #206 <Class BufferedReader>;
        InputStreamReader inputstreamreader = JVM INSTR new #208 <Class InputStreamReader>;
        inputstreamreader.InputStreamReader(fileinputstream);
        bufferedreader.BufferedReader(inputstreamreader, 256);
        int k = 0;
_L4:
        Object obj2 = bufferedreader.readLine();
        if(obj2 == null) goto _L2; else goto _L1
_L1:
        obj2 = ((String) (obj2)).trim();
        if(((String) (obj2)).startsWith("#") || ((String) (obj2)).equals("")) goto _L4; else goto _L3
_L3:
        StringBuilder stringbuilder1 = JVM INSTR new #79  <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        Trace.traceBegin(16384L, stringbuilder1.append("PreloadClass ").append(((String) (obj2))).toString());
        Class.forName(((String) (obj2)), true, null);
        k++;
_L5:
        Trace.traceEnd(16384L);
          goto _L4
        Object obj1;
        obj1;
        Log.e("ZygoteInitInjector", "Error reading /system/etc/preloaded-miui-classes.", ((Throwable) (obj1)));
        IoUtils.closeQuietly(fileinputstream);
        vmruntime.setTargetHeapUtilization(f);
        Trace.traceBegin(16384L, "PreloadDexCaches");
        vmruntime.preloadDexCaches();
        Trace.traceEnd(16384L);
        if(flag1)
        {
            ReflectionUtils.tryCallStaticMethod(((Class) (obj)), "setreuid", java/lang/Void, new Object[] {
                Integer.valueOf(0), Integer.valueOf(0)
            });
            ReflectionUtils.tryCallStaticMethod(((Class) (obj)), "setregid", java/lang/Void, new Object[] {
                Integer.valueOf(0), Integer.valueOf(0)
            });
        }
_L6:
        return;
        obj1;
        StringBuilder stringbuilder2 = JVM INSTR new #79  <Class StringBuilder>;
        stringbuilder2.StringBuilder();
        Log.e("ZygoteInitInjector", stringbuilder2.append("Error preloading ").append(((String) (obj2))).append(".").toString(), ((Throwable) (obj1)));
        if(obj1 instanceof Error)
            throw (Error)obj1;
        break MISSING_BLOCK_LABEL_595;
        obj1;
        IoUtils.closeQuietly(fileinputstream);
        vmruntime.setTargetHeapUtilization(f);
        Trace.traceBegin(16384L, "PreloadDexCaches");
        vmruntime.preloadDexCaches();
        Trace.traceEnd(16384L);
        if(flag1)
        {
            ReflectionUtils.tryCallStaticMethod(((Class) (obj)), "setreuid", java/lang/Void, new Object[] {
                Integer.valueOf(0), Integer.valueOf(0)
            });
            ReflectionUtils.tryCallStaticMethod(((Class) (obj)), "setregid", java/lang/Void, new Object[] {
                Integer.valueOf(0), Integer.valueOf(0)
            });
        }
        throw obj1;
        if(obj1 instanceof RuntimeException)
        {
            throw (RuntimeException)obj1;
        } else
        {
            obj2 = JVM INSTR new #267 <Class RuntimeException>;
            ((RuntimeException) (obj2)).RuntimeException(((Throwable) (obj1)));
            throw obj2;
        }
        Object obj3;
        obj3;
        StringBuilder stringbuilder4 = JVM INSTR new #79  <Class StringBuilder>;
        stringbuilder4.StringBuilder();
        Log.w("ZygoteInitInjector", stringbuilder4.append("Problem preloading ").append(((String) (obj2))).append(": ").append(obj3).toString());
          goto _L5
        obj3;
        StringBuilder stringbuilder3 = JVM INSTR new #79  <Class StringBuilder>;
        stringbuilder3.StringBuilder();
        Log.w("ZygoteInitInjector", stringbuilder3.append("Class not found for preloading: ").append(((String) (obj2))).toString());
          goto _L5
_L2:
        StringBuilder stringbuilder = JVM INSTR new #79  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.i("ZygoteInitInjector", stringbuilder.append("...preloaded ").append(k).append(" classes in ").append(SystemClock.uptimeMillis() - l).append("ms.").toString());
        IoUtils.closeQuietly(fileinputstream);
        vmruntime.setTargetHeapUtilization(f);
        Trace.traceBegin(16384L, "PreloadDexCaches");
        vmruntime.preloadDexCaches();
        Trace.traceEnd(16384L);
        if(flag1)
        {
            ReflectionUtils.tryCallStaticMethod(((Class) (obj)), "setreuid", java/lang/Void, new Object[] {
                Integer.valueOf(0), Integer.valueOf(0)
            });
            ReflectionUtils.tryCallStaticMethod(((Class) (obj)), "setregid", java/lang/Void, new Object[] {
                Integer.valueOf(0), Integer.valueOf(0)
            });
        }
          goto _L6
    }

    static void preloadMiuiFeatures()
    {
        if(android.os.Build.VERSION.SDK_INT < 26)
            preloadBoostFramework();
        if(android.os.Build.VERSION.SDK_INT >= 23)
            preloadMiuiClassesHigherThanL();
    }

    static void preloadMiuiResources(Resources resources)
    {
        if("zygote_secondary".equals(processName))
        {
            Log.i("ZygoteInitInjector", "skip the second zygote 32 preload miui resource");
            return;
        }
        long l = SystemClock.uptimeMillis();
        TypedArray typedarray = resources.obtainTypedArray(miui.R.array.preloaded_drawables);
        resources = ReflectionUtils.tryCallStaticMethod(com/android/internal/os/ZygoteInit, "preloadDrawables", java/lang/Integer, new Object[] {
            typedarray
        });
        int i = 0;
        if(resources != null)
            i = ((Integer)resources.get()).intValue();
        typedarray.recycle();
        Log.i("ZygoteInitInjector", (new StringBuilder()).append("...preloaded ").append(i).append(" miui sdk resources in ").append(SystemClock.uptimeMillis() - l).append("ms.").toString());
        preloadMiuiFeatures();
    }

    static void preloadSharedLibraries()
    {
        if(!"zygote_secondary".equals(processName))
        {
            System.loadLibrary("themeutils_jni");
            System.loadLibrary("shell");
            System.loadLibrary("shell_jni");
        }
    }

    static void setProcessName(String s)
    {
        processName = s;
    }

    private static final String PRELOADED_MIUI_CLASSES = "preloaded-miui-classes";
    private static final String PRELOADED_MIUI_CLASSES_FILE = "/system/etc/preloaded-miui-classes";
    private static final int ROOT_GID = 0;
    private static final int ROOT_UID = 0;
    private static final String SECOND_ZYGOTE_NAME = "zygote_secondary";
    private static final String TAG = "ZygoteInitInjector";
    private static final int UNPRIVILEGED_GID = 9999;
    private static final int UNPRIVILEGED_UID = 9999;
    private static String processName;
}

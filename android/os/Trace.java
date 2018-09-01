// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            SystemProperties

public final class Trace
{

    private Trace()
    {
    }

    public static void asyncTraceBegin(long l, String s, int i)
    {
        if(isTagEnabled(l))
            nativeAsyncTraceBegin(l, s, i);
    }

    public static void asyncTraceEnd(long l, String s, int i)
    {
        if(isTagEnabled(l))
            nativeAsyncTraceEnd(l, s, i);
    }

    public static void beginSection(String s)
    {
        if(isTagEnabled(4096L))
        {
            if(s.length() > 127)
                throw new IllegalArgumentException("sectionName is too long");
            nativeTraceBegin(4096L, s);
        }
    }

    private static long cacheEnabledTags()
    {
        long l = nativeGetEnabledTags();
        sEnabledTags = l;
        return l;
    }

    public static void endSection()
    {
        if(isTagEnabled(4096L))
            nativeTraceEnd(4096L);
    }

    public static boolean isTagEnabled(long l)
    {
        long l1 = sEnabledTags;
        long l2 = l1;
        if(l1 == 0x8000000000000000L)
            l2 = cacheEnabledTags();
        boolean flag;
        if((l2 & l) != 0L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    static void lambda$_2D_android_os_Trace_5027()
    {
        cacheEnabledTags();
        if((sZygoteDebugFlags & 0x100) != 0)
            traceCounter(1L, "java_debuggable", 1);
    }

    private static native void nativeAsyncTraceBegin(long l, String s, int i);

    private static native void nativeAsyncTraceEnd(long l, String s, int i);

    private static native long nativeGetEnabledTags();

    private static native void nativeSetAppTracingAllowed(boolean flag);

    private static native void nativeSetTracingEnabled(boolean flag);

    private static native void nativeTraceBegin(long l, String s);

    private static native void nativeTraceCounter(long l, String s, int i);

    private static native void nativeTraceEnd(long l);

    public static void setAppTracingAllowed(boolean flag)
    {
        nativeSetAppTracingAllowed(flag);
        cacheEnabledTags();
    }

    public static void setTracingEnabled(boolean flag, int i)
    {
        nativeSetTracingEnabled(flag);
        sZygoteDebugFlags = i;
        cacheEnabledTags();
    }

    public static void traceBegin(long l, String s)
    {
        if(isTagEnabled(l))
            nativeTraceBegin(l, s);
    }

    public static void traceCounter(long l, String s, int i)
    {
        if(isTagEnabled(l))
            nativeTraceCounter(l, s, i);
    }

    public static void traceEnd(long l)
    {
        if(isTagEnabled(l))
            nativeTraceEnd(l);
    }

    private static final int MAX_SECTION_NAME_LEN = 127;
    private static final String TAG = "Trace";
    public static final long TRACE_TAG_ACTIVITY_MANAGER = 64L;
    public static final long TRACE_TAG_ADB = 0x400000L;
    public static final long TRACE_TAG_ALWAYS = 1L;
    public static final long TRACE_TAG_APP = 4096L;
    public static final long TRACE_TAG_AUDIO = 256L;
    public static final long TRACE_TAG_BIONIC = 0x10000L;
    public static final long TRACE_TAG_CAMERA = 1024L;
    public static final long TRACE_TAG_DALVIK = 16384L;
    public static final long TRACE_TAG_DATABASE = 0x100000L;
    public static final long TRACE_TAG_GRAPHICS = 2L;
    public static final long TRACE_TAG_HAL = 2048L;
    public static final long TRACE_TAG_INPUT = 4L;
    public static final long TRACE_TAG_NETWORK = 0x200000L;
    public static final long TRACE_TAG_NEVER = 0L;
    private static final long TRACE_TAG_NOT_READY = 0x8000000000000000L;
    public static final long TRACE_TAG_PACKAGE_MANAGER = 0x40000L;
    public static final long TRACE_TAG_POWER = 0x20000L;
    public static final long TRACE_TAG_RESOURCES = 8192L;
    public static final long TRACE_TAG_RS = 32768L;
    public static final long TRACE_TAG_SYNC_MANAGER = 128L;
    public static final long TRACE_TAG_SYSTEM_SERVER = 0x80000L;
    public static final long TRACE_TAG_VIDEO = 512L;
    public static final long TRACE_TAG_VIEW = 8L;
    public static final long TRACE_TAG_WEBVIEW = 16L;
    public static final long TRACE_TAG_WINDOW_MANAGER = 32L;
    private static volatile long sEnabledTags = 0x8000000000000000L;
    private static int sZygoteDebugFlags = 0;

    static 
    {
        SystemProperties.addChangeCallback(_.Lambda.BcGBlsGjMZMF6Ej78rWJ608MYSM.$INST$1);
    }
}

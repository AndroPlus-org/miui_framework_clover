// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.Trace;
import android.system.ErrnoException;
import android.system.Os;
import dalvik.system.ZygoteHooks;

public final class Zygote
{

    private Zygote()
    {
    }

    public static void appendQuotedShellArgs(StringBuilder stringbuilder, String as[])
    {
        int i = 0;
        for(int j = as.length; i < j; i++)
        {
            String s = as[i];
            stringbuilder.append(" '").append(s.replace("'", "'\\''")).append("'");
        }

    }

    private static void callPostForkChildHooks(int i, boolean flag, String s)
    {
        VM_HOOKS.postForkChild(i, flag, s);
    }

    public static void execShell(String s)
    {
        String as[] = new String[3];
        as[0] = "/system/bin/sh";
        as[1] = "-c";
        as[2] = s;
        try
        {
            Os.execv(as[0], as);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException(s);
        }
    }

    public static int forkAndSpecialize(int i, int j, int ai[], int k, int ai1[][], int l, String s, String s1, 
            int ai2[], int ai3[], String s2, String s3)
    {
        VM_HOOKS.preFork();
        resetNicePriority();
        i = nativeForkAndSpecialize(i, j, ai, k, ai1, l, s, s1, ai2, ai3, s2, s3);
        if(i == 0)
        {
            Trace.setTracingEnabled(true, k);
            Trace.traceBegin(64L, "PostFork");
        }
        VM_HOOKS.postForkCommon();
        return i;
    }

    public static int forkSystemServer(int i, int j, int ai[], int k, int ai1[][], long l, long l1)
    {
        VM_HOOKS.preFork();
        resetNicePriority();
        i = nativeForkSystemServer(i, j, ai, k, ai1, l, l1);
        if(i == 0)
            Trace.setTracingEnabled(true, k);
        VM_HOOKS.postForkCommon();
        return i;
    }

    protected static native void nativeAllowFileAcrossFork(String s);

    private static native int nativeForkAndSpecialize(int i, int j, int ai[], int k, int ai1[][], int l, String s, String s1, 
            int ai2[], int ai3[], String s2, String s3);

    private static native int nativeForkSystemServer(int i, int j, int ai[], int k, int ai1[][], long l, long l1);

    static native void nativePreApplicationInit();

    protected static native void nativeUnmountStorageOnInit();

    static void resetNicePriority()
    {
        Thread.currentThread().setPriority(5);
    }

    public static final int DEBUG_ALWAYS_JIT = 64;
    public static final int DEBUG_ENABLE_ASSERT = 4;
    public static final int DEBUG_ENABLE_CHECKJNI = 2;
    public static final int DEBUG_ENABLE_JDWP = 1;
    public static final int DEBUG_ENABLE_JNI_LOGGING = 16;
    public static final int DEBUG_ENABLE_SAFEMODE = 8;
    public static final int DEBUG_GENERATE_DEBUG_INFO = 32;
    public static final int DEBUG_JAVA_DEBUGGABLE = 256;
    public static final int DEBUG_NATIVE_DEBUGGABLE = 128;
    public static final int MOUNT_EXTERNAL_DEFAULT = 1;
    public static final int MOUNT_EXTERNAL_NONE = 0;
    public static final int MOUNT_EXTERNAL_READ = 2;
    public static final int MOUNT_EXTERNAL_WRITE = 3;
    private static final ZygoteHooks VM_HOOKS = new ZygoteHooks();

}

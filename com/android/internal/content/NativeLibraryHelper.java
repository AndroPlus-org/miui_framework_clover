// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.content;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageParser;
import android.os.*;
import android.system.*;
import android.util.Slog;
import dalvik.system.CloseGuard;
import dalvik.system.VMRuntime;
import java.io.*;
import java.util.List;

public class NativeLibraryHelper
{
    public static class Handle
        implements Closeable
    {

        public static Handle create(android.content.pm.PackageParser.Package package1)
            throws IOException
        {
            boolean flag = true;
            List list = package1.getAllCodePaths();
            boolean flag1;
            boolean flag2;
            if((package1.applicationInfo.flags & 0x80000000) != 0)
                flag1 = true;
            else
                flag1 = false;
            if((package1.applicationInfo.flags & 0x10000000) != 0)
                flag2 = true;
            else
                flag2 = false;
            if((package1.applicationInfo.flags & 2) == 0)
                flag = false;
            return create(list, flag1, flag2, flag);
        }

        public static Handle create(android.content.pm.PackageParser.PackageLite packagelite)
            throws IOException
        {
            return create(packagelite.getAllCodePaths(), packagelite.multiArch, packagelite.extractNativeLibs, packagelite.debuggable);
        }

        public static Handle create(File file)
            throws IOException
        {
            Handle handle;
            try
            {
                handle = create(PackageParser.parsePackageLite(file, 0));
            }
            catch(android.content.pm.PackageParser.PackageParserException packageparserexception)
            {
                throw new IOException((new StringBuilder()).append("Failed to parse package: ").append(file).toString(), packageparserexception);
            }
            return handle;
        }

        private static Handle create(List list, boolean flag, boolean flag1, boolean flag2)
            throws IOException
        {
            int i = list.size();
            long al[] = new long[i];
            for(int k = 0; k < i; k++)
            {
                String s = (String)list.get(k);
                al[k] = NativeLibraryHelper._2D_wrap0(s);
                if(al[k] == 0L)
                {
                    for(int j = 0; j < k; j++)
                        NativeLibraryHelper._2D_wrap1(al[j]);

                    throw new IOException((new StringBuilder()).append("Unable to open APK: ").append(s).toString());
                }
            }

            return new Handle(al, flag, flag1, flag2);
        }

        public void close()
        {
            long al[] = apkHandles;
            int i = 0;
            for(int j = al.length; i < j; i++)
                NativeLibraryHelper._2D_wrap1(al[i]);

            mGuard.close();
            mClosed = true;
        }

        protected void finalize()
            throws Throwable
        {
            if(mGuard != null)
                mGuard.warnIfOpen();
            if(!mClosed)
                close();
            super.finalize();
            return;
            Exception exception;
            exception;
            super.finalize();
            throw exception;
        }

        final long apkHandles[];
        final boolean debuggable;
        final boolean extractNativeLibs;
        private volatile boolean mClosed;
        private final CloseGuard mGuard = CloseGuard.get();
        final boolean multiArch;

        Handle(long al[], boolean flag, boolean flag1, boolean flag2)
        {
            apkHandles = al;
            multiArch = flag;
            extractNativeLibs = flag1;
            debuggable = flag2;
            mGuard.open("close");
        }
    }


    static long _2D_wrap0(String s)
    {
        return nativeOpenApk(s);
    }

    static void _2D_wrap1(long l)
    {
        nativeClose(l);
    }

    public NativeLibraryHelper()
    {
    }

    public static int copyNativeBinaries(Handle handle, File file, String s)
    {
        long al[] = handle.apkHandles;
        int i = al.length;
        for(int j = 0; j < i; j++)
        {
            int k = nativeCopyNativeBinaries(al[j], file.getPath(), s, handle.extractNativeLibs, HAS_NATIVE_BRIDGE, handle.debuggable);
            if(k != 1)
                return k;
        }

        return 1;
    }

    public static int copyNativeBinariesForSupportedAbi(Handle handle, File file, String as[], boolean flag)
        throws IOException
    {
        createNativeLibrarySubdir(file);
        int i = findSupportedAbi(handle, as);
        if(i >= 0)
        {
            String s = VMRuntime.getInstructionSet(as[i]);
            if(flag)
            {
                file = new File(file, s);
                createNativeLibrarySubdir(file);
            }
            int j = copyNativeBinaries(handle, file, as[i]);
            if(j != 1)
                return j;
        }
        return i;
    }

    public static int copyNativeBinariesWithOverride(Handle handle, File file, String s)
    {
        if(!handle.multiArch) goto _L2; else goto _L1
_L1:
        if(s == null)
            break MISSING_BLOCK_LABEL_30;
        if("-".equals(s) ^ true)
            Slog.w("NativeHelper", "Ignoring abiOverride for multi arch application.");
        int i;
        if(Build.SUPPORTED_32_BIT_ABIS.length <= 0)
            break MISSING_BLOCK_LABEL_92;
        i = copyNativeBinariesForSupportedAbi(handle, file, Build.SUPPORTED_32_BIT_ABIS, true);
        if(i >= 0 || i == -114 || i == -113)
            break MISSING_BLOCK_LABEL_92;
        handle = JVM INSTR new #129 <Class StringBuilder>;
        handle.StringBuilder();
        Slog.w("NativeHelper", handle.append("Failure copying 32 bit native libraries; copyRet=").append(i).toString());
        return i;
        if(Build.SUPPORTED_64_BIT_ABIS.length <= 0) goto _L4; else goto _L3
_L3:
        i = copyNativeBinariesForSupportedAbi(handle, file, Build.SUPPORTED_64_BIT_ABIS, true);
        if(i >= 0 || i == -114 || i == -113) goto _L4; else goto _L5
_L5:
        handle = JVM INSTR new #129 <Class StringBuilder>;
        handle.StringBuilder();
        Slog.w("NativeHelper", handle.append("Failure copying 64 bit native libraries; copyRet=").append(i).toString());
        return i;
_L2:
        String s1 = null;
        if(!"-".equals(s)) goto _L7; else goto _L6
_L6:
        s1 = null;
_L12:
        if(s1 == null) goto _L9; else goto _L8
_L8:
        s = new String[1];
        s[0] = s1;
_L10:
        String as[] = s;
        if(Build.SUPPORTED_64_BIT_ABIS.length <= 0)
            break MISSING_BLOCK_LABEL_217;
        as = s;
        if(s1 != null)
            break MISSING_BLOCK_LABEL_217;
        as = s;
        if(hasRenderscriptBitcode(handle))
            as = Build.SUPPORTED_32_BIT_ABIS;
        i = copyNativeBinariesForSupportedAbi(handle, file, as, true);
        if(i >= 0 || i == -114)
            break; /* Loop/switch isn't completed */
        handle = JVM INSTR new #129 <Class StringBuilder>;
        handle.StringBuilder();
        Slog.w("NativeHelper", handle.append("Failure copying native libraries [errorCode=").append(i).append("]").toString());
        return i;
_L7:
        if(s != null)
            s1 = s;
        continue; /* Loop/switch isn't completed */
_L9:
        s = Build.SUPPORTED_ABIS;
        if(true) goto _L10; else goto _L4
_L4:
        return 1;
        handle;
        Slog.e("NativeHelper", "Copying native libraries failed", handle);
        return -110;
        if(true) goto _L12; else goto _L11
_L11:
    }

    private static void createNativeLibrarySubdir(File file)
        throws IOException
    {
        if(file.isDirectory())
            break MISSING_BLOCK_LABEL_108;
        file.delete();
        if(!file.mkdir())
            throw new IOException((new StringBuilder()).append("Cannot create ").append(file.getPath()).toString());
        Os.chmod(file.getPath(), OsConstants.S_IRWXU | OsConstants.S_IRGRP | OsConstants.S_IXGRP | OsConstants.S_IROTH | OsConstants.S_IXOTH);
_L1:
        return;
        ErrnoException errnoexception;
        errnoexception;
        throw new IOException((new StringBuilder()).append("Cannot chmod native library directory ").append(file.getPath()).toString(), errnoexception);
        if(!SELinux.restorecon(file))
            throw new IOException((new StringBuilder()).append("Cannot set SELinux context for ").append(file.getPath()).toString());
          goto _L1
    }

    public static int findSupportedAbi(Handle handle, String as[])
    {
        int i;
        byte byte0;
        long al[];
        int j;
        i = 0;
        byte0 = -114;
        al = handle.apkHandles;
        j = al.length;
_L2:
        int k;
        int l;
        if(i >= j)
            break MISSING_BLOCK_LABEL_104;
        k = nativeFindSupportedAbi(al[i], as, handle.debuggable);
        if(k != -114)
            break; /* Loop/switch isn't completed */
        l = byte0;
_L3:
        i++;
        byte0 = l;
        if(true) goto _L2; else goto _L1
_L1:
label0:
        {
            if(k != -113)
                break label0;
            l = byte0;
            if(byte0 < 0)
                l = -113;
        }
          goto _L3
        if(k < 0)
            break MISSING_BLOCK_LABEL_101;
        if(byte0 < 0) goto _L5; else goto _L4
_L4:
        l = byte0;
        if(k >= byte0) goto _L3; else goto _L5
_L5:
        l = k;
          goto _L3
        return k;
        return byte0;
    }

    private static native int hasRenderscriptBitcode(long l);

    public static boolean hasRenderscriptBitcode(Handle handle)
        throws IOException
    {
        handle = handle.apkHandles;
        int i = handle.length;
        for(int j = 0; j < i; j++)
        {
            int k = hasRenderscriptBitcode(handle[j]);
            if(k < 0)
                throw new IOException((new StringBuilder()).append("Error scanning APK, code: ").append(k).toString());
            if(k == 1)
                return true;
        }

        return false;
    }

    private static native void nativeClose(long l);

    private static native int nativeCopyNativeBinaries(long l, String s, String s1, boolean flag, boolean flag1, boolean flag2);

    private static native int nativeFindSupportedAbi(long l, String as[], boolean flag);

    private static native long nativeOpenApk(String s);

    private static native long nativeSumNativeBinaries(long l, String s, boolean flag);

    public static void removeNativeBinariesFromDirLI(File file, boolean flag)
    {
        if(file.exists())
        {
            File afile[] = file.listFiles();
            if(afile != null)
            {
                int i = 0;
                while(i < afile.length) 
                {
                    if(afile[i].isDirectory())
                        removeNativeBinariesFromDirLI(afile[i], true);
                    else
                    if(!afile[i].delete())
                        Slog.w("NativeHelper", (new StringBuilder()).append("Could not delete native binary: ").append(afile[i].getPath()).toString());
                    i++;
                }
            }
            if(flag && !file.delete())
                Slog.w("NativeHelper", (new StringBuilder()).append("Could not delete native binary directory: ").append(file.getPath()).toString());
        }
    }

    public static void removeNativeBinariesLI(String s)
    {
        if(s == null)
        {
            return;
        } else
        {
            removeNativeBinariesFromDirLI(new File(s), false);
            return;
        }
    }

    private static long sumNativeBinaries(Handle handle, String s)
    {
        long l = 0L;
        long al[] = handle.apkHandles;
        int i = 0;
        for(int j = al.length; i < j; i++)
            l += nativeSumNativeBinaries(al[i], s, handle.debuggable);

        return l;
    }

    private static long sumNativeBinariesForSupportedAbi(Handle handle, String as[])
    {
        int i = findSupportedAbi(handle, as);
        if(i >= 0)
            return sumNativeBinaries(handle, as[i]);
        else
            return 0L;
    }

    public static long sumNativeBinariesWithOverride(Handle handle, String s)
        throws IOException
    {
        long l = 0L;
        if(!handle.multiArch) goto _L2; else goto _L1
_L1:
        long l1;
        if(s != null && "-".equals(s) ^ true)
            Slog.w("NativeHelper", "Ignoring abiOverride for multi arch application.");
        if(Build.SUPPORTED_32_BIT_ABIS.length > 0)
            l = 0L + sumNativeBinariesForSupportedAbi(handle, Build.SUPPORTED_32_BIT_ABIS);
        l1 = l;
        if(Build.SUPPORTED_64_BIT_ABIS.length > 0)
            l1 = l + sumNativeBinariesForSupportedAbi(handle, Build.SUPPORTED_64_BIT_ABIS);
_L5:
        return l1;
_L2:
        String s1 = null;
        if(!"-".equals(s)) goto _L4; else goto _L3
_L3:
        s1 = null;
_L6:
        String as[];
        if(s1 != null)
        {
            s = new String[1];
            s[0] = s1;
        } else
        {
            s = Build.SUPPORTED_ABIS;
        }
        as = s;
        if(Build.SUPPORTED_64_BIT_ABIS.length > 0)
        {
            as = s;
            if(s1 == null)
            {
                as = s;
                if(hasRenderscriptBitcode(handle))
                    as = Build.SUPPORTED_32_BIT_ABIS;
            }
        }
        l1 = 0L + sumNativeBinariesForSupportedAbi(handle, as);
        if(true) goto _L5; else goto _L4
_L4:
        if(s != null)
            s1 = s;
          goto _L6
    }

    private static final int BITCODE_PRESENT = 1;
    public static final String CLEAR_ABI_OVERRIDE = "-";
    private static final boolean DEBUG_NATIVE = false;
    private static final boolean HAS_NATIVE_BRIDGE = "0".equals(SystemProperties.get("ro.dalvik.vm.native.bridge", "0")) ^ true;
    public static final String LIB64_DIR_NAME = "lib64";
    public static final String LIB_DIR_NAME = "lib";
    private static final String TAG = "NativeHelper";

}

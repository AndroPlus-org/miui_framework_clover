// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.app.ActivityManagerInternal;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import com.android.server.LocalServices;
import dalvik.system.VMRuntime;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

// Referenced classes of package android.webkit:
//            WebViewFactory, IWebViewUpdateService

class WebViewLibraryLoader
{
    private static class RelroFileCreator
    {

        public static void main(String args[])
        {
            boolean flag = VMRuntime.getRuntime().is64Bit();
              goto _L1
_L3:
            String s = WebViewLibraryLoader._2D_get0();
            StringBuilder stringbuilder1 = JVM INSTR new #32  <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            Log.e(s, stringbuilder1.append("Invalid RelroFileCreator args: ").append(Arrays.toString(args)).toString());
            StringBuilder stringbuilder;
            String s1;
            try
            {
                WebViewFactory.getUpdateService().notifyRelroCreationCompleted();
            }
            // Misplaced declaration of an exception variable
            catch(String args[])
            {
                Log.e(WebViewLibraryLoader._2D_get0(), "error notifying update service", args);
            }
            if(true)
                Log.e(WebViewLibraryLoader._2D_get0(), "failed to create relro file");
            System.exit(0);
            return;
_L1:
            if(args.length != 2 || args[0] == null || args[1] == null) goto _L3; else goto _L2
_L2:
            s1 = WebViewLibraryLoader._2D_get0();
            stringbuilder = JVM INSTR new #32  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.v(s1, stringbuilder.append("RelroFileCreator (64bit = ").append(flag).append("), ").append(" 32-bit lib: ").append(args[0]).append(", 64-bit lib: ").append(args[1]).toString());
            if(WebViewLibraryLoader._2D_get1())
                break MISSING_BLOCK_LABEL_210;
            Log.e(WebViewLibraryLoader._2D_get0(), "can't create relro file; address space not reserved");
            try
            {
                WebViewFactory.getUpdateService().notifyRelroCreationCompleted();
            }
            // Misplaced declaration of an exception variable
            catch(String args[])
            {
                Log.e(WebViewLibraryLoader._2D_get0(), "error notifying update service", args);
            }
            if(true)
                Log.e(WebViewLibraryLoader._2D_get0(), "failed to create relro file");
            System.exit(0);
            return;
            flag = WebViewLibraryLoader.nativeCreateRelroFile(args[0], args[1], "/data/misc/shared_relro/libwebviewchromium32.relro", "/data/misc/shared_relro/libwebviewchromium64.relro");
            try
            {
                WebViewFactory.getUpdateService().notifyRelroCreationCompleted();
            }
            // Misplaced declaration of an exception variable
            catch(String args[])
            {
                Log.e(WebViewLibraryLoader._2D_get0(), "error notifying update service", args);
            }
            if(!flag)
                Log.e(WebViewLibraryLoader._2D_get0(), "failed to create relro file");
            System.exit(0);
            return;
            Exception exception;
            exception;
            try
            {
                WebViewFactory.getUpdateService().notifyRelroCreationCompleted();
            }
            // Misplaced declaration of an exception variable
            catch(String args[])
            {
                Log.e(WebViewLibraryLoader._2D_get0(), "error notifying update service", args);
            }
            if(true)
                Log.e(WebViewLibraryLoader._2D_get0(), "failed to create relro file");
            System.exit(0);
            throw exception;
        }

        private RelroFileCreator()
        {
        }
    }


    static String _2D_get0()
    {
        return LOGTAG;
    }

    static boolean _2D_get1()
    {
        return sAddressSpaceReserved;
    }

    WebViewLibraryLoader()
    {
    }

    static void createRelroFile(boolean flag, String as[])
    {
        try
        {
            as = JVM INSTR new #59  <Class IllegalArgumentException>;
            as.IllegalArgumentException("Native library paths to the WebView RelRo process must not be null!");
            throw as;
        }
        // Misplaced declaration of an exception variable
        catch(String as[])
        {
            Log.e(LOGTAG, (new StringBuilder()).append("error starting relro file creator for abi ").append(s).toString(), as);
        }
        runnable.run();
_L1:
        return;
        if(flag)
            s = Build.SUPPORTED_64_BIT_ABIS[0];
        else
            s = Build.SUPPORTED_32_BIT_ABIS[0];
        runnable = new Runnable(s) {

            public void run()
            {
                String s2 = WebViewLibraryLoader._2D_get0();
                StringBuilder stringbuilder1 = JVM INSTR new #30  <Class StringBuilder>;
                stringbuilder1.StringBuilder();
                Log.e(s2, stringbuilder1.append("relro file creator for ").append(abi).append(" crashed. Proceeding without").toString());
                WebViewFactory.getUpdateService().notifyRelroCreationCompleted();
_L1:
                return;
                RemoteException remoteexception;
                remoteexception;
                Log.e(WebViewLibraryLoader._2D_get0(), (new StringBuilder()).append("Cannot reach WebViewUpdateService. ").append(remoteexception.getMessage()).toString());
                  goto _L1
            }

            final String val$abi;

            
            {
                abi = s;
                super();
            }
        }
;
        if(as == null || as[0] == null || as[1] == null)
            break MISSING_BLOCK_LABEL_29;
        ActivityManagerInternal activitymanagerinternal = (ActivityManagerInternal)LocalServices.getService(android/app/ActivityManagerInternal);
        String s1 = android/webkit/WebViewLibraryLoader$RelroFileCreator.getName();
        StringBuilder stringbuilder = JVM INSTR new #64  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        if(activitymanagerinternal.startIsolatedProcess(s1, as, stringbuilder.append("WebViewLoader-").append(s).toString(), s, 1037, runnable) <= 0)
        {
            as = JVM INSTR new #107 <Class Exception>;
            as.Exception("Failed to start the relro file creator process");
            throw as;
        }
          goto _L1
    }

    private static String getLoadFromApkPath(String s, String as[], String s1)
        throws WebViewFactory.MissingWebViewPackageException
    {
        int i;
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        i = 0;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        Object obj5;
        obj5 = JVM INSTR new #118 <Class ZipFile>;
        ((ZipFile) (obj5)).ZipFile(s);
        int j = as.length;
_L6:
        if(i >= j) goto _L2; else goto _L1
_L1:
        obj3 = as[i];
        obj4 = JVM INSTR new #64  <Class StringBuilder>;
        ((StringBuilder) (obj4)).StringBuilder();
        obj4 = ((StringBuilder) (obj4)).append("lib/").append(((String) (obj3))).append("/").append(s1).toString();
        obj3 = ((ZipFile) (obj5)).getEntry(((String) (obj4)));
        if(obj3 == null)
            continue; /* Loop/switch isn't completed */
        if(((ZipEntry) (obj3)).getMethod() != 0)
            continue; /* Loop/switch isn't completed */
        as = JVM INSTR new #64  <Class StringBuilder>;
        as.StringBuilder();
        as = as.append(s).append("!/").append(((String) (obj4))).toString();
        s = obj2;
        if(obj5 == null)
            break MISSING_BLOCK_LABEL_144;
        ((ZipFile) (obj5)).close();
        s = obj2;
_L5:
        if(s == null) goto _L4; else goto _L3
_L3:
        try
        {
            throw s;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
_L9:
        throw new WebViewFactory.MissingWebViewPackageException(s);
        s;
          goto _L5
_L4:
        return as;
        i++;
          goto _L6
_L2:
        s = obj;
        if(obj5 == null)
            break MISSING_BLOCK_LABEL_188;
        ((ZipFile) (obj5)).close();
        s = obj;
_L8:
        if(s == null)
            break; /* Loop/switch isn't completed */
        throw s;
        s;
        if(true) goto _L8; else goto _L7
        s;
        as = ((String []) (obj4));
_L13:
        throw s;
        obj5;
        s1 = s;
        s = ((String) (obj5));
_L12:
        obj5 = s1;
        if(as == null)
            break MISSING_BLOCK_LABEL_225;
        as.close();
        obj5 = s1;
_L10:
        if(obj5 == null)
            break MISSING_BLOCK_LABEL_267;
        try
        {
            throw obj5;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
          goto _L9
        as;
label0:
        {
            if(s1 != null)
                break label0;
            obj5 = as;
        }
          goto _L10
        obj5 = s1;
        if(s1 == as) goto _L10; else goto _L11
_L11:
        s1.addSuppressed(as);
        obj5 = s1;
          goto _L10
        throw s;
_L7:
        return "";
        s;
        as = ((String []) (obj3));
        s1 = obj1;
          goto _L12
        s;
        as = ((String []) (obj5));
        s1 = obj1;
          goto _L12
        s;
        as = ((String []) (obj5));
          goto _L13
    }

    static String[] getWebViewNativeLibraryPaths(PackageInfo packageinfo)
        throws WebViewFactory.MissingWebViewPackageException
    {
        ApplicationInfo applicationinfo = packageinfo.applicationInfo;
        String s = WebViewFactory.getWebViewLibrary(applicationinfo);
        boolean flag = VMRuntime.is64BitAbi(applicationinfo.primaryCpuAbi);
        Object obj;
        String s1;
        if(!TextUtils.isEmpty(applicationinfo.secondaryCpuAbi))
        {
            if(flag)
            {
                packageinfo = applicationinfo.nativeLibraryDir;
                obj = applicationinfo.secondaryNativeLibraryDir;
            } else
            {
                packageinfo = applicationinfo.secondaryNativeLibraryDir;
                obj = applicationinfo.nativeLibraryDir;
            }
        } else
        if(flag)
        {
            packageinfo = applicationinfo.nativeLibraryDir;
            obj = "";
        } else
        {
            obj = applicationinfo.nativeLibraryDir;
            packageinfo = "";
        }
        s1 = ((String) (obj));
        if(!TextUtils.isEmpty(((CharSequence) (obj))))
        {
            obj = (new StringBuilder()).append(((String) (obj))).append("/").append(s).toString();
            s1 = ((String) (obj));
            if(!(new File(((String) (obj)))).exists())
                s1 = getLoadFromApkPath(applicationinfo.sourceDir, Build.SUPPORTED_32_BIT_ABIS, s);
        }
        obj = packageinfo;
        if(!TextUtils.isEmpty(packageinfo))
        {
            packageinfo = (new StringBuilder()).append(packageinfo).append("/").append(s).toString();
            obj = packageinfo;
            if(!(new File(packageinfo)).exists())
                obj = getLoadFromApkPath(applicationinfo.sourceDir, Build.SUPPORTED_64_BIT_ABIS, s);
        }
        return (new String[] {
            s1, obj
        });
    }

    static int loadNativeLibrary(ClassLoader classloader, PackageInfo packageinfo)
        throws WebViewFactory.MissingWebViewPackageException
    {
        if(!sAddressSpaceReserved)
        {
            Log.e(LOGTAG, "can't load with relro file; address space not reserved");
            return 2;
        }
        int i = nativeLoadWithRelroFile(WebViewFactory.getWebViewLibrary(packageinfo.applicationInfo), "/data/misc/shared_relro/libwebviewchromium32.relro", "/data/misc/shared_relro/libwebviewchromium64.relro", classloader);
        if(i != 0)
            Log.w(LOGTAG, "failed to load with relro file, proceeding without");
        return i;
    }

    static native boolean nativeCreateRelroFile(String s, String s1, String s2, String s3);

    static native int nativeLoadWithRelroFile(String s, String s1, String s2, ClassLoader classloader);

    static native boolean nativeReserveAddressSpace(long l);

    static void reserveAddressSpaceInZygote()
    {
        System.loadLibrary("webviewchromium_loader");
        long l = SystemProperties.getLong("persist.sys.webview.vmsize", 0x6400000L);
        sAddressSpaceReserved = nativeReserveAddressSpace(l);
        if(!sAddressSpaceReserved)
            Log.e(LOGTAG, (new StringBuilder()).append("reserving ").append(l).append(" bytes of address space failed").toString());
    }

    private static void setWebViewZygoteVmSize(long l)
    {
        SystemProperties.set("persist.sys.webview.vmsize", Long.toString(l));
    }

    static String[] updateWebViewZygoteVmSize(PackageInfo packageinfo)
        throws WebViewFactory.MissingWebViewPackageException
    {
        String as[] = getWebViewNativeLibraryPaths(packageinfo);
        if(as == null) goto _L2; else goto _L1
_L1:
        long l;
        int i;
        int j;
        l = 0L;
        i = as.length;
        j = 0;
_L4:
        String s;
        long l1;
        if(j >= i)
            break; /* Loop/switch isn't completed */
        s = as[j];
        l1 = l;
        if(s != null)
            if(TextUtils.isEmpty(s))
            {
                l1 = l;
            } else
            {
label0:
                {
                    packageinfo = new File(s);
                    if(!packageinfo.exists())
                        break label0;
                    l1 = Math.max(l, packageinfo.length());
                }
            }
_L12:
        j++;
        l = l1;
        if(true) goto _L4; else goto _L3
        l1 = l;
        if(!s.contains("!/")) goto _L6; else goto _L5
_L5:
        String as1[];
        as1 = TextUtils.split(s, "!/");
        l1 = l;
        if(as1.length != 2) goto _L6; else goto _L7
_L7:
        Object obj;
        Object obj1;
        Object obj2;
        Object obj4;
        Object obj5;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj4 = null;
        obj5 = null;
        packageinfo = JVM INSTR new #118 <Class ZipFile>;
        packageinfo.ZipFile(as1[0]);
        obj5 = packageinfo.getEntry(as1[1]);
        if(obj5 == null) goto _L9; else goto _L8
_L8:
        if(((ZipEntry) (obj5)).getMethod() != 0) goto _L9; else goto _L10
_L10:
        l1 = Math.max(l, ((ZipEntry) (obj5)).getSize());
        l = l1;
        obj5 = obj2;
        if(packageinfo == null)
            break MISSING_BLOCK_LABEL_204;
        l1 = l;
        packageinfo.close();
        obj5 = obj2;
_L13:
        l1 = l;
        if(obj5 == null) goto _L12; else goto _L11
_L11:
        l1 = l;
        try
        {
            throw obj5;
        }
        // Misplaced declaration of an exception variable
        catch(PackageInfo packageinfo)
        {
            l = l1;
        }
_L16:
        Log.e(LOGTAG, (new StringBuilder()).append("error reading APK file ").append(as1[0]).append(", ").toString(), packageinfo);
        l1 = l;
_L6:
        Log.e(LOGTAG, (new StringBuilder()).append("error sizing load for ").append(s).toString());
          goto _L12
        obj5;
          goto _L13
_L9:
        obj5 = obj;
        if(packageinfo == null)
            break MISSING_BLOCK_LABEL_317;
        l1 = l;
        packageinfo.close();
        obj5 = obj;
_L15:
        l1 = l;
        if(obj5 == null) goto _L6; else goto _L14
_L14:
        l1 = l;
        throw obj5;
        obj5;
          goto _L15
        packageinfo;
_L20:
        throw packageinfo;
        obj1;
        obj2 = packageinfo;
        packageinfo = ((PackageInfo) (obj1));
_L19:
        obj1 = obj2;
        if(obj5 == null)
            break MISSING_BLOCK_LABEL_365;
        ((ZipFile) (obj5)).close();
        obj1 = obj2;
_L17:
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_416;
        try
        {
            throw obj1;
        }
        // Misplaced declaration of an exception variable
        catch(PackageInfo packageinfo) { }
          goto _L16
        obj5;
label1:
        {
            if(obj2 != null)
                break label1;
            obj1 = obj5;
        }
          goto _L17
        obj1 = obj2;
        if(obj2 == obj5) goto _L17; else goto _L18
_L18:
        ((Throwable) (obj2)).addSuppressed(((Throwable) (obj5)));
        obj1 = obj2;
          goto _L17
        throw packageinfo;
_L3:
        l = Math.max(2L * l, 0x6400000L);
        Log.d(LOGTAG, (new StringBuilder()).append("Setting new address space to ").append(l).toString());
        setWebViewZygoteVmSize(l);
_L2:
        return as;
        packageinfo;
        obj5 = obj4;
        obj2 = obj1;
          goto _L19
        Object obj3;
        obj3;
        obj5 = packageinfo;
        packageinfo = ((PackageInfo) (obj3));
        obj3 = obj1;
          goto _L19
        obj3;
        obj5 = packageinfo;
        packageinfo = ((PackageInfo) (obj3));
          goto _L20
    }

    private static final long CHROMIUM_WEBVIEW_DEFAULT_VMSIZE_BYTES = 0x6400000L;
    private static final String CHROMIUM_WEBVIEW_NATIVE_RELRO_32 = "/data/misc/shared_relro/libwebviewchromium32.relro";
    private static final String CHROMIUM_WEBVIEW_NATIVE_RELRO_64 = "/data/misc/shared_relro/libwebviewchromium64.relro";
    private static final boolean DEBUG = false;
    private static final String LOGTAG = android/webkit/WebViewLibraryLoader.getSimpleName();
    private static boolean sAddressSpaceReserved = false;

}

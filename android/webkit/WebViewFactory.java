// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.app.*;
import android.content.Context;
import android.content.pm.*;
import android.content.res.AssetManager;
import android.os.*;
import android.util.*;
import java.lang.reflect.Method;

// Referenced classes of package android.webkit:
//            WebViewDelegate, WebViewFactoryProvider, WebViewLibraryLoader, IWebViewUpdateService, 
//            WebViewProviderResponse, WebViewZygote

public final class WebViewFactory
{
    static class MissingWebViewPackageException extends Exception
    {

        public MissingWebViewPackageException(Exception exception)
        {
            super(exception);
        }

        public MissingWebViewPackageException(String s)
        {
            super(s);
        }
    }


    public WebViewFactory()
    {
    }

    private static void fixupStubApplicationInfo(ApplicationInfo applicationinfo, PackageManager packagemanager)
        throws MissingWebViewPackageException
    {
        String s = null;
        if(applicationinfo.metaData != null)
            s = applicationinfo.metaData.getString("com.android.webview.WebViewDonorPackage");
        if(s != null)
        {
            try
            {
                packagemanager = packagemanager.getPackageInfo(s, 0x10202400);
            }
            // Misplaced declaration of an exception variable
            catch(ApplicationInfo applicationinfo)
            {
                throw new MissingWebViewPackageException((new StringBuilder()).append("Failed to find donor package: ").append(s).toString());
            }
            packagemanager = ((PackageInfo) (packagemanager)).applicationInfo;
            applicationinfo.sourceDir = ((ApplicationInfo) (packagemanager)).sourceDir;
            applicationinfo.splitSourceDirs = ((ApplicationInfo) (packagemanager)).splitSourceDirs;
            applicationinfo.nativeLibraryDir = ((ApplicationInfo) (packagemanager)).nativeLibraryDir;
            applicationinfo.secondaryNativeLibraryDir = ((ApplicationInfo) (packagemanager)).secondaryNativeLibraryDir;
            applicationinfo.primaryCpuAbi = ((ApplicationInfo) (packagemanager)).primaryCpuAbi;
            applicationinfo.secondaryCpuAbi = ((ApplicationInfo) (packagemanager)).secondaryCpuAbi;
        }
    }

    public static PackageInfo getLoadedPackageInfo()
    {
        Object obj = sProviderLock;
        obj;
        JVM INSTR monitorenter ;
        PackageInfo packageinfo = sPackageInfo;
        obj;
        JVM INSTR monitorexit ;
        return packageinfo;
        Exception exception;
        exception;
        throw exception;
    }

    static WebViewFactoryProvider getProvider()
    {
        Object obj = sProviderLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        if(sProviderInstance == null)
            break MISSING_BLOCK_LABEL_20;
        obj1 = sProviderInstance;
        obj;
        JVM INSTR monitorexit ;
        return ((WebViewFactoryProvider) (obj1));
        int i = Process.myUid();
          goto _L1
_L3:
        obj1 = JVM INSTR new #150 <Class UnsupportedOperationException>;
        ((UnsupportedOperationException) (obj1)).UnsupportedOperationException("For security reasons, WebView is not allowed in privileged processes");
        throw obj1;
        Object obj2;
        obj2;
        obj;
        JVM INSTR monitorexit ;
        throw obj2;
_L1:
        if(i == 0 || i == 1000 || i == 1001 || i == 1027 || i == 1002) goto _L3; else goto _L2
_L2:
        android.os.StrictMode.ThreadPolicy threadpolicy;
        threadpolicy = StrictMode.allowThreadDiskReads();
        Trace.traceBegin(16L, "WebViewFactory.getProvider()");
        Object obj3 = getProviderClass();
        obj2 = null;
        obj3 = ((Class) (obj3)).getMethod("create", new Class[] {
            android/webkit/WebViewDelegate
        });
        obj2 = obj3;
_L5:
        Trace.traceBegin(16L, "WebViewFactoryProvider invocation");
        WebViewDelegate webviewdelegate = JVM INSTR new #177 <Class WebViewDelegate>;
        webviewdelegate.WebViewDelegate();
        sProviderInstance = (WebViewFactoryProvider)((Method) (obj2)).invoke(null, new Object[] {
            webviewdelegate
        });
        obj2 = sProviderInstance;
        Trace.traceEnd(16L);
        Trace.traceEnd(16L);
        StrictMode.setThreadPolicy(threadpolicy);
        obj;
        JVM INSTR monitorexit ;
        return ((WebViewFactoryProvider) (obj2));
        obj2;
        Log.e("WebViewFactory", "error instantiating provider", ((Throwable) (obj2)));
        AndroidRuntimeException androidruntimeexception = JVM INSTR new #210 <Class AndroidRuntimeException>;
        androidruntimeexception.AndroidRuntimeException(((Exception) (obj2)));
        throw androidruntimeexception;
        obj2;
        Trace.traceEnd(16L);
        throw obj2;
        obj2;
        Trace.traceEnd(16L);
        StrictMode.setThreadPolicy(threadpolicy);
        throw obj2;
        Exception exception;
        exception;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private static Class getProviderClass()
    {
        Application application = AppGlobals.getInitialApplication();
        Trace.traceBegin(16L, "WebViewFactory.getWebViewContextAndSetProvider()");
        Context context = getWebViewContextAndSetProvider();
        Trace.traceEnd(16L);
        StringBuilder stringbuilder = JVM INSTR new #117 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.i("WebViewFactory", stringbuilder.append("Loading ").append(sPackageInfo.packageName).append(" version ").append(sPackageInfo.versionName).append(" (code ").append(sPackageInfo.versionCode).append(")").toString());
        Trace.traceBegin(16L, "WebViewFactory.getChromiumProviderClass()");
        Object obj1;
        application.getAssets().addAssetPathAsSharedLibrary(context.getApplicationInfo().sourceDir);
        obj1 = context.getClassLoader();
        Trace.traceBegin(16L, "WebViewFactory.loadNativeLibrary()");
        WebViewLibraryLoader.loadNativeLibrary(((ClassLoader) (obj1)), sPackageInfo);
        Trace.traceEnd(16L);
        Trace.traceBegin(16L, "Class.forName()");
        obj1 = getWebViewProviderClass(((ClassLoader) (obj1)));
        Trace.traceEnd(16L);
        Trace.traceEnd(16L);
        return ((Class) (obj1));
        Exception exception;
        exception;
        Object obj;
        try
        {
            Trace.traceEnd(16L);
            throw exception;
        }
        catch(Object obj2)
        {
            try
            {
                obj = Class.forName("com.android.webview.nullwebview.NullWebViewFactoryProvider");
            }
            catch(ClassNotFoundException classnotfoundexception)
            {
                Log.e("WebViewFactory", "Chromium WebView package does not exist", ((Throwable) (obj2)));
                throw new AndroidRuntimeException(((Exception) (obj2)));
            }
        }
        return ((Class) (obj));
        obj2;
        try
        {
            Trace.traceEnd(16L);
            throw obj2;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj2) { }
        Log.e("WebViewFactory", "error loading provider", ((Throwable) (obj2)));
        obj = JVM INSTR new #210 <Class AndroidRuntimeException>;
        ((AndroidRuntimeException) (obj)).AndroidRuntimeException(((Exception) (obj2)));
        throw obj;
        obj2;
        Trace.traceEnd(16L);
        throw obj2;
    }

    public static IWebViewUpdateService getUpdateService()
    {
        return IWebViewUpdateService.Stub.asInterface(ServiceManager.getService(WEBVIEW_UPDATE_SERVICE_NAME));
    }

    private static Context getWebViewContextAndSetProvider()
        throws MissingWebViewPackageException
    {
        Application application = AppGlobals.getInitialApplication();
        Object obj;
        try
        {
            Trace.traceBegin(16L, "WebViewUpdateService.waitForAndGetProvider()");
        }
        catch(Object obj1)
        {
            throw new MissingWebViewPackageException((new StringBuilder()).append("Failed to load WebView provider: ").append(obj1).toString());
        }
        obj = getUpdateService().waitForAndGetProvider();
        Trace.traceEnd(16L);
        if(((WebViewProviderResponse) (obj)).status != 0 && ((WebViewProviderResponse) (obj)).status != 3)
        {
            MissingWebViewPackageException missingwebviewpackageexception = JVM INSTR new #6   <Class WebViewFactory$MissingWebViewPackageException>;
            StringBuilder stringbuilder = JVM INSTR new #117 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            missingwebviewpackageexception.MissingWebViewPackageException(stringbuilder.append("Failed to load WebView provider: ").append(getWebViewPreparationErrorReason(((WebViewProviderResponse) (obj)).status)).toString());
            throw missingwebviewpackageexception;
        }
        break MISSING_BLOCK_LABEL_119;
        Exception exception;
        exception;
        Trace.traceEnd(16L);
        throw exception;
        Trace.traceBegin(16L, "ActivityManager.addPackageDependency()");
        ActivityManager.getService().addPackageDependency(((WebViewProviderResponse) (obj)).packageInfo.packageName);
        PackageManager packagemanager;
        Trace.traceEnd(16L);
        packagemanager = application.getPackageManager();
        Trace.traceBegin(16L, "PackageManager.getPackageInfo()");
        PackageInfo packageinfo = packagemanager.getPackageInfo(((WebViewProviderResponse) (obj)).packageInfo.packageName, 0x100024c0);
        Trace.traceEnd(16L);
        verifyPackageInfo(((WebViewProviderResponse) (obj)).packageInfo, packageinfo);
        obj = packageinfo.applicationInfo;
        fixupStubApplicationInfo(((ApplicationInfo) (obj)), packagemanager);
        Trace.traceBegin(16L, "initialApplication.createApplicationContext");
        obj = application.createApplicationContext(((ApplicationInfo) (obj)), 3);
        sPackageInfo = packageinfo;
        Trace.traceEnd(16L);
        return ((Context) (obj));
        packageinfo;
        Trace.traceEnd(16L);
        throw packageinfo;
        packageinfo;
        Trace.traceEnd(16L);
        throw packageinfo;
        packageinfo;
        Trace.traceEnd(16L);
        throw packageinfo;
    }

    public static String getWebViewLibrary(ApplicationInfo applicationinfo)
    {
        if(applicationinfo.metaData != null)
            return applicationinfo.metaData.getString("com.android.webview.WebViewLibrary");
        else
            return null;
    }

    private static String getWebViewPreparationErrorReason(int i)
    {
        switch(i)
        {
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        default:
            return "Unknown";

        case 3: // '\003'
            return "Time out waiting for Relro files being created";

        case 4: // '\004'
            return "No WebView installed";

        case 8: // '\b'
            return "Crashed for unknown reason";
        }
    }

    public static Class getWebViewProviderClass(ClassLoader classloader)
        throws ClassNotFoundException
    {
        return Class.forName("com.android.webview.chromium.WebViewChromiumFactoryProviderForOMR1", true, classloader);
    }

    public static int loadWebViewNativeLibraryFromPackage(String s, ClassLoader classloader)
    {
        WebViewProviderResponse webviewproviderresponse;
        try
        {
            webviewproviderresponse = getUpdateService().waitForAndGetProvider();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("WebViewFactory", "error waiting for relro creation", s);
            return 8;
        }
        if(webviewproviderresponse.status != 0 && webviewproviderresponse.status != 3)
            return webviewproviderresponse.status;
        if(!webviewproviderresponse.packageInfo.packageName.equals(s))
            return 1;
        Object obj = AppGlobals.getInitialApplication().getPackageManager();
        int i;
        try
        {
            obj = ((PackageManager) (obj)).getPackageInfo(s, 0x10000080);
        }
        // Misplaced declaration of an exception variable
        catch(ClassLoader classloader)
        {
            Log.e("WebViewFactory", (new StringBuilder()).append("Couldn't find package ").append(s).toString());
            return 1;
        }
        try
        {
            i = WebViewLibraryLoader.loadNativeLibrary(classloader, ((PackageInfo) (obj)));
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("WebViewFactory", (new StringBuilder()).append("Couldn't load native library: ").append(s).toString());
            return 6;
        }
        if(i != 0)
            break MISSING_BLOCK_LABEL_125;
        i = webviewproviderresponse.status;
        return i;
        return i;
    }

    public static int onWebViewProviderChanged(PackageInfo packageinfo)
    {
        String as[];
        String s;
        as = null;
        s = packageinfo.applicationInfo.sourceDir;
        String as1[];
        fixupStubApplicationInfo(packageinfo.applicationInfo, AppGlobals.getInitialApplication().getPackageManager());
        as1 = WebViewLibraryLoader.updateWebViewZygoteVmSize(packageinfo);
        as = as1;
_L2:
        WebViewZygote.onWebViewProviderChanged(packageinfo, s);
        return prepareWebViewInSystemServer(as);
        Throwable throwable;
        throwable;
        Log.e("WebViewFactory", "error preparing webview native library", throwable);
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static int prepareWebViewInSystemServer(String as[])
    {
        int i = 0;
        if(Build.SUPPORTED_32_BIT_ABIS.length > 0)
        {
            WebViewLibraryLoader.createRelroFile(false, as);
            i = 1;
        }
        int j = i;
        if(Build.SUPPORTED_64_BIT_ABIS.length > 0)
        {
            WebViewLibraryLoader.createRelroFile(true, as);
            j = i + 1;
        }
        return j;
    }

    public static void prepareWebViewInZygote()
    {
        WebViewLibraryLoader.reserveAddressSpaceInZygote();
_L1:
        return;
        Throwable throwable;
        throwable;
        Log.e("WebViewFactory", "error preparing native loader", throwable);
          goto _L1
    }

    private static boolean signaturesEquals(Signature asignature[], Signature asignature1[])
    {
        boolean flag = false;
        boolean flag1 = false;
        if(asignature == null)
        {
            if(asignature1 == null)
                flag1 = true;
            return flag1;
        }
        if(asignature1 == null)
            return false;
        ArraySet arrayset = new ArraySet();
        int i = asignature.length;
        for(int j = 0; j < i; j++)
            arrayset.add(asignature[j]);

        asignature = new ArraySet();
        i = asignature1.length;
        for(int k = ((flag) ? 1 : 0); k < i; k++)
            asignature.add(asignature1[k]);

        return arrayset.equals(asignature);
    }

    private static void verifyPackageInfo(PackageInfo packageinfo, PackageInfo packageinfo1)
        throws MissingWebViewPackageException
    {
        if(!packageinfo.packageName.equals(packageinfo1.packageName))
            throw new MissingWebViewPackageException((new StringBuilder()).append("Failed to verify WebView provider, packageName mismatch, expected: ").append(packageinfo.packageName).append(" actual: ").append(packageinfo1.packageName).toString());
        if(packageinfo.versionCode > packageinfo1.versionCode)
            throw new MissingWebViewPackageException((new StringBuilder()).append("Failed to verify WebView provider, version code is lower than expected: ").append(packageinfo.versionCode).append(" actual: ").append(packageinfo1.versionCode).toString());
        if(getWebViewLibrary(packageinfo1.applicationInfo) == null)
            throw new MissingWebViewPackageException((new StringBuilder()).append("Tried to load an invalid WebView provider: ").append(packageinfo1.packageName).toString());
        if(!signaturesEquals(packageinfo.signatures, packageinfo1.signatures))
            throw new MissingWebViewPackageException("Failed to verify WebView provider, signature mismatch");
        else
            return;
    }

    private static final String CHROMIUM_WEBVIEW_FACTORY = "com.android.webview.chromium.WebViewChromiumFactoryProviderForOMR1";
    private static final String CHROMIUM_WEBVIEW_FACTORY_METHOD = "create";
    public static final String CHROMIUM_WEBVIEW_VMSIZE_SIZE_PROPERTY = "persist.sys.webview.vmsize";
    private static final boolean DEBUG = false;
    public static final int LIBLOAD_ADDRESS_SPACE_NOT_RESERVED = 2;
    public static final int LIBLOAD_FAILED_JNI_CALL = 7;
    public static final int LIBLOAD_FAILED_LISTING_WEBVIEW_PACKAGES = 4;
    public static final int LIBLOAD_FAILED_TO_FIND_NAMESPACE = 10;
    public static final int LIBLOAD_FAILED_TO_LOAD_LIBRARY = 6;
    public static final int LIBLOAD_FAILED_TO_OPEN_RELRO_FILE = 5;
    public static final int LIBLOAD_FAILED_WAITING_FOR_RELRO = 3;
    public static final int LIBLOAD_FAILED_WAITING_FOR_WEBVIEW_REASON_UNKNOWN = 8;
    public static final int LIBLOAD_SUCCESS = 0;
    public static final int LIBLOAD_WRONG_PACKAGE_NAME = 1;
    private static final String LOGTAG = "WebViewFactory";
    private static final String NULL_WEBVIEW_FACTORY = "com.android.webview.nullwebview.NullWebViewFactoryProvider";
    private static String WEBVIEW_UPDATE_SERVICE_NAME = "webviewupdate";
    private static PackageInfo sPackageInfo;
    private static WebViewFactoryProvider sProviderInstance;
    private static final Object sProviderLock = new Object();

}

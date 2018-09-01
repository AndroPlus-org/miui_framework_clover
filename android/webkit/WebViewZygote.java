// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.app.LoadedApk;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.*;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.Log;
import java.io.File;
import java.util.*;
import java.util.concurrent.TimeoutException;

public class WebViewZygote
{

    public WebViewZygote()
    {
    }

    private static void connectToZygoteIfNeededLocked()
    {
        String s;
        if(sZygote != null)
            return;
        if(sPackage == null)
        {
            Log.e("WebViewZygote", "Cannot connect to zygote, no package specified");
            return;
        }
        s = getServiceNameLocked();
        if(!SystemService.isRunning(s))
        {
            Log.e("WebViewZygote", (new StringBuilder()).append(s).append(" is not running").toString());
            return;
        }
        Object obj;
        Object obj1;
        obj = JVM INSTR new #80  <Class ZygoteProcess>;
        ((ZygoteProcess) (obj)).ZygoteProcess("webview_zygote", null);
        sZygote = ((ZygoteProcess) (obj));
        obj = JVM INSTR new #85  <Class ArrayList>;
        ((ArrayList) (obj)).ArrayList(10);
        obj1 = JVM INSTR new #85  <Class ArrayList>;
        ((ArrayList) (obj1)).ArrayList(10);
        LoadedApk.makePaths(null, false, sPackage.applicationInfo, ((List) (obj)), ((List) (obj1)));
        obj1 = TextUtils.join(File.pathSeparator, ((Iterable) (obj1)));
        if(((List) (obj)).size() != 1) goto _L2; else goto _L1
_L1:
        obj = (String)((List) (obj)).get(0);
_L3:
        ZygoteProcess.waitForConnectionToZygote("webview_zygote");
        StringBuilder stringbuilder = JVM INSTR new #68  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.d("WebViewZygote", stringbuilder.append("Preloading package ").append(((String) (obj))).append(" ").append(((String) (obj1))).toString());
        sZygote.preloadPackageForAbi(((String) (obj)), ((String) (obj1)), sPackageCacheKey, Build.SUPPORTED_ABIS[0]);
_L4:
        return;
_L2:
        obj = TextUtils.join(File.pathSeparator, ((Iterable) (obj)));
          goto _L3
        Exception exception;
        exception;
        Log.e("WebViewZygote", (new StringBuilder()).append("Error connecting to ").append(s).toString(), exception);
        sZygote = null;
          goto _L4
    }

    public static String getPackageName()
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        String s = sPackage.packageName;
        obj;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        throw exception;
    }

    public static ZygoteProcess getProcess()
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        ZygoteProcess zygoteprocess;
        if(sZygote == null)
            break MISSING_BLOCK_LABEL_20;
        zygoteprocess = sZygote;
        obj;
        JVM INSTR monitorexit ;
        return zygoteprocess;
        waitForServiceStartAndConnect();
        zygoteprocess = sZygote;
        obj;
        JVM INSTR monitorexit ;
        return zygoteprocess;
        Exception exception;
        exception;
        throw exception;
    }

    private static String getServiceNameLocked()
    {
        if(sPackage == null)
            return null;
        if(Arrays.asList(Build.SUPPORTED_64_BIT_ABIS).contains(sPackage.applicationInfo.primaryCpuAbi))
            return "webview_zygote64";
        else
            return "webview_zygote32";
    }

    public static boolean isMultiprocessEnabled()
    {
        boolean flag = false;
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag1 = flag;
        PackageInfo packageinfo;
        if(!sMultiprocessEnabled)
            break MISSING_BLOCK_LABEL_28;
        packageinfo = sPackage;
        flag1 = flag;
        if(packageinfo != null)
            flag1 = true;
        obj;
        JVM INSTR monitorexit ;
        return flag1;
        Exception exception;
        exception;
        throw exception;
    }

    public static void onWebViewProviderChanged(PackageInfo packageinfo, String s)
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag;
        sPackage = packageinfo;
        sPackageCacheKey = s;
        flag = sMultiprocessEnabled;
        if(flag)
            break MISSING_BLOCK_LABEL_25;
        obj;
        JVM INSTR monitorexit ;
        return;
        packageinfo = getServiceNameLocked();
        sZygote = null;
        if(!SystemService.isStopped(packageinfo))
            break MISSING_BLOCK_LABEL_51;
        SystemService.start(packageinfo);
_L1:
        sStartedService = true;
        obj;
        JVM INSTR monitorexit ;
        return;
        SystemService.restart(packageinfo);
          goto _L1
        packageinfo;
        throw packageinfo;
    }

    public static void setMultiprocessEnabled(boolean flag)
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        String s;
        sMultiprocessEnabled = flag;
        s = getServiceNameLocked();
        if(s != null)
            break MISSING_BLOCK_LABEL_21;
        obj;
        JVM INSTR monitorexit ;
        return;
        if(!flag)
            break MISSING_BLOCK_LABEL_42;
        if(!sStartedService)
        {
            SystemService.start(s);
            sStartedService = true;
        }
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        SystemService.stop(s);
        sStartedService = false;
        sZygote = null;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    private static void waitForServiceStartAndConnect()
    {
        if(!sStartedService)
            throw new AndroidRuntimeException("Tried waiting for the WebView Zygote Service to start running without first starting the service.");
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = getServiceNameLocked();
        obj;
        JVM INSTR monitorexit ;
        try
        {
            SystemService.waitForState(((String) (obj1)), android.os.SystemService.State.RUNNING, 5000L);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.e("WebViewZygote", (new StringBuilder()).append("Timed out waiting for ").append(((String) (obj1))).toString());
            return;
        }
        obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        connectToZygoteIfNeededLocked();
        obj;
        JVM INSTR monitorexit ;
        return;
        obj1;
        throw obj1;
        Exception exception;
        exception;
        throw exception;
    }

    private static final String LOGTAG = "WebViewZygote";
    private static final String WEBVIEW_ZYGOTE_SERVICE_32 = "webview_zygote32";
    private static final String WEBVIEW_ZYGOTE_SERVICE_64 = "webview_zygote64";
    private static final String WEBVIEW_ZYGOTE_SOCKET = "webview_zygote";
    private static final Object sLock = new Object();
    private static boolean sMultiprocessEnabled = false;
    private static PackageInfo sPackage;
    private static String sPackageCacheKey;
    private static boolean sStartedService = false;
    private static ZygoteProcess sZygote;

}

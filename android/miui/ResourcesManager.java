// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.miui;

import android.content.res.*;
import android.os.Process;
import android.os.StrictMode;
import java.io.File;
import miui.util.ReflectionUtils;

public class ResourcesManager
{

    public ResourcesManager()
    {
    }

    public static void addSystemAssets(AssetManager assetmanager)
    {
        android.os.StrictMode.ThreadPolicy threadpolicy = null;
        int i = Process.myUid();
        if(i != 0)
            threadpolicy = StrictMode.allowThreadDiskReads();
        ReflectionUtils.tryCallMethod(assetmanager, "getResourceName", java/lang/String, new Object[] {
            Integer.valueOf(0)
        });
        if((new File("/data/app/com.miui.core-1.apk")).exists())
            assetmanager.addAssetPath("/data/app/com.miui.core-1.apk");
        else
        if((new File("/data/app/com.miui.core-2.apk")).exists())
            assetmanager.addAssetPath("/data/app/com.miui.core-2.apk");
        else
            assetmanager.addAssetPath(MIUI_SDK_RES_PATH);
        if((new File("/data/app/com.miui.system-1.apk")).exists())
            assetmanager.addAssetPath("/data/app/com.miui.system-1.apk");
        else
        if((new File("/data/app/com.miui.system-2.apk")).exists())
            assetmanager.addAssetPath("/data/app/com.miui.system-2.apk");
        else
            assetmanager.addAssetPath(MIUI_FRAMEWORK_RES_PATH);
        assetmanager.addAssetPath(FRAMEWORK_EXT_RES_PATH);
        if(i != 0)
            StrictMode.setThreadPolicy(threadpolicy);
    }

    public static boolean belongToMiuiFrameworkThemePath(String s)
    {
        boolean flag;
        if(!isMiuiExtFrameworkPath(s) && !isMiuiSystemSdkPath(s))
            flag = isMiuiSdkPath(s);
        else
            flag = true;
        return flag;
    }

    public static void initMiuiResource(Resources resources, String s)
    {
        if(resources instanceof MiuiResources)
            ((MiuiResources)resources).init(s);
    }

    public static boolean isMiuiExtFrameworkPath(String s)
    {
        return FRAMEWORK_EXT_RES_PATH.equals(s);
    }

    public static boolean isMiuiSdkPath(String s)
    {
        boolean flag;
        if(!MIUI_SDK_RES_PATH.equals(s) && !"/data/app/com.miui.core-1.apk".equals(s))
            flag = "/data/app/com.miui.core-2.apk".equals(s);
        else
            flag = true;
        return flag;
    }

    public static boolean isMiuiSystemSdkPath(String s)
    {
        boolean flag;
        if(!MIUI_FRAMEWORK_RES_PATH.equals(s) && !"/data/app/com.miui.system-1.apk".equals(s))
            flag = "/data/app/com.miui.system-2.apk".equals(s);
        else
            flag = true;
        return flag;
    }

    public static final String FRAMEWORK_EXT_RES_PATH;
    public static final String FRAMEWORK_RES_PATH = "/system/framework/framework-res.apk";
    public static final String MIUI_FRAMEWORK_RES_DATA_PATH_1 = "/data/app/com.miui.system-1.apk";
    public static final String MIUI_FRAMEWORK_RES_DATA_PATH_2 = "/data/app/com.miui.system-2.apk";
    public static final String MIUI_FRAMEWORK_RES_PATH;
    public static final String MIUI_SDK_RES_DATA_PATH_1 = "/data/app/com.miui.core-1.apk";
    public static final String MIUI_SDK_RES_DATA_PATH_2 = "/data/app/com.miui.core-2.apk";
    public static final String MIUI_SDK_RES_PATH;
    private static final boolean VERSION_ABOVE_5;

    static 
    {
        boolean flag;
        String s;
        if(android.os.Build.VERSION.SDK_INT > 19)
            flag = true;
        else
            flag = false;
        VERSION_ABOVE_5 = flag;
        if(VERSION_ABOVE_5)
            s = "/system/app/miui/miui.apk";
        else
            s = "/system/app/miui.apk";
        MIUI_SDK_RES_PATH = s;
        if(VERSION_ABOVE_5)
            s = "/system/app/miuisystem/miuisystem.apk";
        else
            s = "/system/app/miuisystem.apk";
        MIUI_FRAMEWORK_RES_PATH = s;
        if(VERSION_ABOVE_5)
            s = "/system/framework/framework-ext-res/framework-ext-res.apk";
        else
            s = "/system/framework/framework-ext-res.apk";
        FRAMEWORK_EXT_RES_PATH = s;
    }
}

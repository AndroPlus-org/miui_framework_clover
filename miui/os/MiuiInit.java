// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.os;

import android.content.res.Resources;
import android.os.*;
import android.util.Log;
import miui.util.CustomizeUtil;

// Referenced classes of package miui.os:
//            IMiuiInit, Build, IMiuiInitObserver

public class MiuiInit
{

    public MiuiInit()
    {
    }

    public static void doFactoryReset(boolean flag)
    {
        getService().doFactoryReset(flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("MiuiInit", "Occur RemoteException when removing preinstall app history file");
          goto _L1
    }

    public static float getAspectRatio(String s)
    {
        if(s == null || needAspectSettings() ^ true)
            return 3F;
        float f;
        try
        {
            f = getService().getAspectRatio(s);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("MiuiInit", (new StringBuilder()).append("Occur RemoteException when getAspectRatio:").append(s).toString());
            return 3F;
        }
        return f;
    }

    public static String[] getCustVariants()
    {
        String as[];
        try
        {
            as = getService().getCustVariants();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("MiuiInit", "Occur RemoteException when fetch cust variants");
            return null;
        }
        return as;
    }

    public static int getDefaultAspectType(String s)
    {
        if(s == null || needAspectSettings() ^ true)
            return 0;
        int i;
        try
        {
            i = getService().getDefaultAspectType(s);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("MiuiInit", (new StringBuilder()).append("Occur RemoteException when getDefaultAspectType:").append(s).toString());
            return 0;
        }
        return i;
    }

    public static String getMiuiChannelPath(String s)
    {
        try
        {
            s = getService().getMiuiChannelPath(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("MiuiInit", "Occur RemoteException when checking preinstalled channel");
            return "";
        }
        return s;
    }

    public static String getMiuiPreinstallAppPath(String s)
    {
        try
        {
            s = getService().getMiuiPreinstallAppPath(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("MiuiInit", "Occur RemoteException when checking preinstalled app path");
            return "";
        }
        return s;
    }

    public static int getNotchConfig(String s)
    {
        if(s == null || CustomizeUtil.HAS_NOTCH ^ true)
            return 0;
        int i;
        try
        {
            i = getService().getNotchConfig(s);
        }
        catch(Exception exception)
        {
            Log.e("MiuiInit", (new StringBuilder()).append("Occur RemoteException when getNotchConfig:").append(s).toString());
            return 0;
        }
        return i;
    }

    public static int getPreinstalledAppVersion(String s)
    {
        IMiuiInit imiuiinit = getService();
        if(imiuiinit == null)
            break MISSING_BLOCK_LABEL_27;
        int i = imiuiinit.getPreinstalledAppVersion(s);
        return i;
        s;
        Log.e("MiuiInit", "Occur RemoteException when get preinstalled package version");
        return -1;
    }

    private static IMiuiInit getService()
    {
        miui/os/MiuiInit;
        JVM INSTR monitorenter ;
        IMiuiInit imiuiinit;
        if(sService == null)
            sService = IMiuiInit.Stub.asInterface(ServiceManager.getService("MiuiInit"));
        imiuiinit = sService;
        miui/os/MiuiInit;
        JVM INSTR monitorexit ;
        return imiuiinit;
        Exception exception;
        exception;
        throw exception;
    }

    public static boolean initCustEnvironment(String s, IMiuiInitObserver imiuiinitobserver)
    {
        boolean flag;
        try
        {
            flag = getService().initCustEnvironment(s, imiuiinitobserver);
        }
        // Misplaced declaration of an exception variable
        catch(IMiuiInitObserver imiuiinitobserver)
        {
            Log.e("MiuiInit", (new StringBuilder()).append("Occur RemoteException when init cust environment for [").append(s).append("]").toString());
            flag = false;
        }
        return flag;
    }

    public static boolean installPreinstallApp()
    {
        try
        {
            getService().installPreinstallApp();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("MiuiInit", "Occur RemoteException when install preinstall app");
            return false;
        }
        return true;
    }

    public static boolean isPreinstalledPackage(String s)
    {
        IMiuiInit imiuiinit = getService();
        if(imiuiinit == null)
            break MISSING_BLOCK_LABEL_27;
        boolean flag = imiuiinit.isPreinstalledPackage(s);
        return flag;
        s;
        Log.e("MiuiInit", "Occur RemoteException when checking preinstalled package");
        return false;
    }

    public static boolean isRestrictAspect(String s)
    {
        boolean flag = false;
        if(s == null || needAspectSettings() ^ true)
        {
            if(android.os.Build.VERSION.SDK_INT >= 26)
                flag = true;
            return flag;
        }
        try
        {
            flag = getService().isRestrictAspect(s);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("MiuiInit", (new StringBuilder()).append("Occur RemoteException when isRestrictAspect:").append(s).toString());
            return false;
        }
        return flag;
    }

    private static boolean needAspectSettings()
    {
        boolean flag = false;
        if(!sNeedAspectSettingsInited)
        {
            String s = SystemProperties.get("qemu.hw.mainkeys");
            if("1".equals(s))
                sNeedAspectSettings = false;
            else
            if("0".equals(s))
                sNeedAspectSettings = true;
            else
                sNeedAspectSettings = Resources.getSystem().getBoolean(0x1120099);
            if(sNeedAspectSettings)
                flag = Build.IS_TABLET ^ true;
            sNeedAspectSettings = flag;
            sNeedAspectSettingsInited = true;
        }
        return sNeedAspectSettings;
    }

    public static void removeFromPreinstallList(String s)
    {
        getService().removeFromPreinstallList(s);
_L1:
        return;
        s;
        Log.e("MiuiInit", "Occur RemoteException when removing from preinstall list");
          goto _L1
    }

    public static void setNotchSpecialMode(String s, boolean flag)
    {
        if(s == null || CustomizeUtil.HAS_NOTCH ^ true)
            return;
        getService().setNotchSpecialMode(s, flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("MiuiInit", (new StringBuilder()).append("Occur RemoteException when setNotchSpecailMode:").append(s).append(" ").append(flag).toString());
          goto _L1
    }

    public static void setRestrictAspect(String s, boolean flag)
    {
        if(s == null || needAspectSettings() ^ true)
            return;
        getService().setRestrictAspect(s, flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("MiuiInit", (new StringBuilder()).append("Occur RemoteException when setRestrictAspect:").append(s).append(" ").append(flag).toString());
          goto _L1
    }

    public static final String ACTION_MIUI_INIT_COMPLETED = "miui.intent.action.MIUI_INIT_COMPLETED";
    public static final String REGION = "region";
    public static final String SERVICE_NAME = "MiuiInit";
    private static final String TAG = "MiuiInit";
    private static boolean sNeedAspectSettings;
    private static boolean sNeedAspectSettingsInited;
    private static IMiuiInit sService;
}

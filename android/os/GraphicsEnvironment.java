// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.opengl.EGL14;
import android.util.Log;
import dalvik.system.VMRuntime;
import java.io.File;

// Referenced classes of package android.os:
//            SystemProperties

public final class GraphicsEnvironment
{

    public GraphicsEnvironment()
    {
    }

    private static String chooseAbi(ApplicationInfo applicationinfo)
    {
        String s = VMRuntime.getCurrentInstructionSet();
        if(applicationinfo.primaryCpuAbi != null && s.equals(VMRuntime.getInstructionSet(applicationinfo.primaryCpuAbi)))
            return applicationinfo.primaryCpuAbi;
        if(applicationinfo.secondaryCpuAbi != null && s.equals(VMRuntime.getInstructionSet(applicationinfo.secondaryCpuAbi)))
            return applicationinfo.secondaryCpuAbi;
        else
            return null;
    }

    public static void chooseDriver(Context context)
    {
        String s = SystemProperties.get("ro.gfx.driver.0");
        if(s == null || s.isEmpty())
            return;
        ApplicationInfo applicationinfo = context.getApplicationInfo();
        if(applicationinfo.isPrivilegedApp() || applicationinfo.isSystemApp() && applicationinfo.isUpdatedSystemApp() ^ true)
            return;
        try
        {
            context = context.getPackageManager().getApplicationInfo(s, 0x100000);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.w("GraphicsEnvironment", (new StringBuilder()).append("driver package '").append(s).append("' not installed").toString());
            return;
        }
        s = chooseAbi(context);
        if(s == null)
            return;
        if(((ApplicationInfo) (context)).targetSdkVersion < 26)
        {
            Log.w("GraphicsEnvironment", "updated driver package is not known to be compatible with O");
            return;
        } else
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(((ApplicationInfo) (context)).nativeLibraryDir).append(File.pathSeparator);
            stringbuilder.append(((ApplicationInfo) (context)).sourceDir).append("!/lib/").append(s);
            setDriverPath(stringbuilder.toString());
            return;
        }
    }

    public static void earlyInitEGL()
    {
        (new Thread(_.Lambda.BcGBlsGjMZMF6Ej78rWJ608MYSM.$INST$0, "EGL Init")).start();
    }

    static void lambda$_2D_android_os_GraphicsEnvironment_4205()
    {
        EGL14.eglGetDisplay(0);
    }

    private static native void setDriverPath(String s);

    private static final boolean DEBUG = false;
    private static final String PROPERTY_GFX_DRIVER = "ro.gfx.driver.0";
    private static final String TAG = "GraphicsEnvironment";
}

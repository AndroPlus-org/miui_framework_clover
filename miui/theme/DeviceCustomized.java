// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.theme;

import android.content.Context;
import android.os.FileUtils;
import android.os.SystemProperties;
import android.text.TextUtils;
import java.io.File;
import miui.util.FeatureParser;

public class DeviceCustomized
{

    public DeviceCustomized()
    {
    }

    private static void createThemeRuntimeFolder()
    {
        (new File("/data/system/theme/compatibility-v8/")).mkdirs();
        FileUtils.setPermissions("/data/system/theme/", 493, -1, -1);
        FileUtils.setPermissions("/data/system/theme/compatibility-v8/", 493, -1, -1);
    }

    private static boolean isDeviceIsProvisioned(Context context)
    {
        boolean flag = false;
        if(android.provider.Settings.Secure.getInt(context.getContentResolver(), "device_provisioned", 0) != 0)
            flag = true;
        return flag;
    }

    public static void setCustomizedWallpaper(Context context, File file)
    {
        if(context != null && file != null)
        {
            String s = SystemProperties.get("sys.panel.color", "");
            if(!TextUtils.isEmpty(s) && isDeviceIsProvisioned(context) ^ true)
            {
                context = FeatureParser.getString((new StringBuilder()).append("customized_wallpaper_").append(s).toString());
                if(!TextUtils.isEmpty(context) && (new File(context)).exists())
                    FileUtils.copyFile(new File(context), file);
                context = FeatureParser.getString((new StringBuilder()).append("customized_lockscreen_").append(s).toString());
                if(!TextUtils.isEmpty(context) && (new File(context)).exists())
                {
                    createThemeRuntimeFolder();
                    FileUtils.copyFile(new File(context), new File("/data/system/theme/lock_wallpaper"));
                }
            }
        }
    }
}

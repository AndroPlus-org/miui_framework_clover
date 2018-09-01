// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.ExtraGLES20Canvas;
import java.io.File;
import miui.content.res.IconCustomizer;
import miui.content.res.ThemeFontChangeHelper;
import miui.os.Build;

// Referenced classes of package android.app:
//            ApplicationPackageManager

public class MiuiThemeHelper
{

    private MiuiThemeHelper()
    {
    }

    public static void addExtraAssetPaths(AssetManager assetmanager)
    {
        assetmanager.addAssetPath("/system/framework/framework-miui-res.apk");
    }

    public static boolean canKeepActivityAlive(String s, int i, Configuration configuration, Configuration configuration1)
    {
        if(i == 0x80000000)
        {
            configuration = configuration.extraConfig;
            configuration1 = configuration1.extraConfig;
            if(((MiuiConfiguration) (configuration1)).themeChanged - ((MiuiConfiguration) (configuration)).themeChanged == 1)
                return MiuiConfiguration.needRestartActivity(s, ((MiuiConfiguration) (configuration1)).themeChangedFlags) ^ true;
        }
        return false;
    }

    public static void copyExtraConfigurations(Configuration configuration, Configuration configuration1)
    {
        configuration1.extraConfig.themeChanged = configuration.extraConfig.themeChanged;
    }

    public static Drawable getDrawable(PackageManager packagemanager, String s, String s1, int i, ApplicationInfo applicationinfo)
    {
        android.graphics.drawable.BitmapDrawable bitmapdrawable = null;
        if(TextUtils.isEmpty(s))
            return null;
        Object obj;
        boolean flag;
        try
        {
            obj = (ApplicationPackageManager)packagemanager;
        }
        // Misplaced declaration of an exception variable
        catch(String s1)
        {
            s1 = bitmapdrawable;
            if(i != 0)
                s1 = packagemanager.getDrawable(s, i, applicationinfo);
            return s1;
        }
        if(android.os.Build.VERSION.SDK_INT <= 24)
        {
            if(s1 == null)
                flag = true;
            else
                flag = false;
        } else
        if(s1 != null)
        {
            if(applicationinfo != null)
                flag = s1.equals(applicationinfo.name);
            else
                flag = false;
        } else
        {
            flag = true;
        }
        if(flag)
            s1 = null;
        obj = IconCustomizer.getCustomizedIconFromCache(s, s1);
        bitmapdrawable = ((android.graphics.drawable.BitmapDrawable) (obj));
        if(obj == null)
        {
            bitmapdrawable = ((android.graphics.drawable.BitmapDrawable) (obj));
            if(packagemanager instanceof ApplicationPackageManager)
                bitmapdrawable = IconCustomizer.getCustomizedIcon(((ApplicationPackageManager)packagemanager).getContext(), s, s1, i, applicationinfo, flag);
        }
        return bitmapdrawable;
    }

    public static void handleExtraConfigurationChanges(int i, Configuration configuration)
    {
        if((0x80000000 & i) != 0)
        {
            long l = configuration.extraConfig.themeChangedFlags;
            Canvas.freeCaches();
            if((16L & l) != 0L)
            {
                ThemeFontChangeHelper.quitProcessIfNeed(configuration);
                Canvas.freeTextLayoutCaches();
                ExtraGLES20Canvas.freeCaches();
            }
            if((8L & l) != 0L)
                IconCustomizer.clearCache();
        }
    }

    public static void handleExtraConfigurationChangesForSystem(int i, Configuration configuration)
    {
        if((0x80000000 & i) != 0)
            handleExtraConfigurationChanges(i, configuration);
    }

    public static boolean isInternationalBuildWithDefaultTheme()
    {
        boolean flag;
        if(Build.IS_INTERNATIONAL_BUILD)
            flag = (new File("/data/system/theme/icons")).exists() ^ true;
        else
            flag = false;
        return flag;
    }

    public static boolean isScreenshotMode()
    {
        return (new File("/data/system/themeScreenshotMode")).exists();
    }

    public static Integer parseDimension(String s)
    {
        int i;
        int j;
        int k;
        byte byte0;
        int l;
        i = -4;
        j = -3;
        k = -2;
        byte0 = -1;
        l = 0;
_L11:
        int i1;
        int j1;
        int k1;
        i1 = j;
        j1 = k;
        k1 = byte0;
        if(l >= s.length()) goto _L2; else goto _L1
_L1:
        i1 = s.charAt(l);
        k1 = i;
        if(i == -4)
        {
            k1 = i;
            if(i1 >= 48)
            {
                k1 = i;
                if(i1 <= 57)
                    k1 = l;
            }
        }
        i = j;
        if(j == -3)
        {
            i = j;
            if(i1 == 46)
                i = l;
        }
        j1 = k;
        if(i != -3)
        {
            j1 = k;
            if(i1 >= 48)
            {
                j1 = k;
                if(i1 <= 57)
                    j1 = l;
            }
        }
        if(i1 < 97 || i1 > 122) goto _L4; else goto _L3
_L3:
        k1 = l;
        i1 = i;
_L2:
        j = 0;
        k = 0;
        if(k1 == -1 || i1 >= j1 || j1 >= k1) goto _L6; else goto _L5
_L5:
        float f;
        StringBuilder stringbuilder;
        try
        {
            f = Float.parseFloat(s.substring(0, k1));
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        l = j;
        if(i1 == -3) goto _L8; else goto _L7
_L7:
        l = j;
        if(j1 == -2) goto _L8; else goto _L9
_L9:
        l = i1 + 1;
        try
        {
            j = Math.min(4, j1 - i1);
            stringbuilder = JVM INSTR new #175 <Class StringBuilder>;
            stringbuilder.StringBuilder(s.substring(l, l + j));
            for(; stringbuilder.length() < 4; stringbuilder.append('0'));
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
          goto _L10
_L4:
        l++;
        j = i;
        k = j1;
        i = k1;
          goto _L11
_L10:
        l = Integer.parseInt(stringbuilder.toString());
_L8:
        if(l >= 256) goto _L13; else goto _L12
_L12:
        float f1 = f * 256F;
_L15:
        s = s.substring(k1);
        if(s.equals("px"))
            l = 0;
        else
        if(s.equals("dp") || s.equals("dip"))
            l = 1;
        else
        if(s.equals("sp"))
            l = 2;
        else
        if(s.equals("pt"))
            l = 3;
        else
        if(s.equals("in"))
            l = 4;
        else
        if(s.equals("mm"))
            l = 5;
        else
            return null;
        return Integer.valueOf(Integer.valueOf(Integer.valueOf(Integer.valueOf((int)f1).intValue() & 0xffffff00).intValue() | k << 4).intValue() | l);
_L13:
        if(l < 32768)
        {
            f1 = f * 32768F;
            k = 1;
        } else
        if(l < 0x800000)
        {
            f1 = f * 8388608F;
            k = 2;
        } else
        {
            f1 = f;
            if((long)l < 0x80000000L)
            {
                f1 = f * 2.147484E+009F;
                k = 3;
            }
        }
        continue; /* Loop/switch isn't completed */
_L6:
        return null;
        if(true) goto _L15; else goto _L14
_L14:
    }

    private static final String ICON_THEME = "/data/system/theme/icons";
    public static final String MIUI_RES_PATH = "/system/framework/framework-miui-res.apk";
    private static final String MIUI_SCREENSHOT_MODE_RES_PATH = "/data/system/themeScreenshotMode";
}

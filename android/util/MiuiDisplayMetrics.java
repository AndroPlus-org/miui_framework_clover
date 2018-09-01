// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import android.content.res.Resources;
import android.os.SystemProperties;

// Referenced classes of package android.util:
//            DisplayMetrics

public final class MiuiDisplayMetrics
{

    public MiuiDisplayMetrics()
    {
    }

    public static int getFactoryDeviceDensity()
    {
        return SystemProperties.getInt("ro.sf.lcd_density", 160);
    }

    private static int getMiuiDeviceDensity()
    {
        return SystemProperties.getInt("persist.miui.density_v2", getFactoryDeviceDensity());
    }

    public static final int getNxhdpiDensity()
    {
        return 440;
    }

    public static boolean setOverlayDensity(int i)
    {
        boolean flag = false;
        int j = getFactoryDeviceDensity();
        if((float)j / 1.5F <= (float)i && (double)i <= (double)j * 1.5D && Resources.getSystem().getDisplayMetrics().densityDpi != i)
        {
            SystemProperties.set("persist.miui.density_v2", String.valueOf(i));
            if(i == SystemProperties.getInt("persist.miui.density_v2", -1))
                flag = true;
            return flag;
        } else
        {
            return false;
        }
    }

    public static int DENSITY_DEVICE = 0;
    public static final int DENSITY_NXHGITH = 440;
    public static final String PROP_MIUI_DENSITY = "persist.miui.density_v2";

    static 
    {
        DENSITY_DEVICE = getMiuiDeviceDensity();
    }
}

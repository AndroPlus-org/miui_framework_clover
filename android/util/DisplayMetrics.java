// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import android.os.SystemProperties;

// Referenced classes of package android.util:
//            MiuiDisplayMetrics

public class DisplayMetrics
{

    public DisplayMetrics()
    {
    }

    private static int getDeviceDensity()
    {
        return SystemProperties.getInt("qemu.sf.lcd_density", SystemProperties.getInt("ro.sf.lcd_density", 160));
    }

    public boolean equals(DisplayMetrics displaymetrics)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(equalsPhysical(displaymetrics))
        {
            flag1 = flag;
            if(scaledDensity == displaymetrics.scaledDensity)
            {
                flag1 = flag;
                if(noncompatScaledDensity == displaymetrics.noncompatScaledDensity)
                    flag1 = true;
            }
        }
        return flag1;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        if(obj instanceof DisplayMetrics)
            flag = equals((DisplayMetrics)obj);
        else
            flag = false;
        return flag;
    }

    public boolean equalsPhysical(DisplayMetrics displaymetrics)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(displaymetrics != null)
        {
            flag1 = flag;
            if(widthPixels == displaymetrics.widthPixels)
            {
                flag1 = flag;
                if(heightPixels == displaymetrics.heightPixels)
                {
                    flag1 = flag;
                    if(density == displaymetrics.density)
                    {
                        flag1 = flag;
                        if(densityDpi == displaymetrics.densityDpi)
                        {
                            flag1 = flag;
                            if(xdpi == displaymetrics.xdpi)
                            {
                                flag1 = flag;
                                if(ydpi == displaymetrics.ydpi)
                                {
                                    flag1 = flag;
                                    if(noncompatWidthPixels == displaymetrics.noncompatWidthPixels)
                                    {
                                        flag1 = flag;
                                        if(noncompatHeightPixels == displaymetrics.noncompatHeightPixels)
                                        {
                                            flag1 = flag;
                                            if(noncompatDensity == displaymetrics.noncompatDensity)
                                            {
                                                flag1 = flag;
                                                if(noncompatDensityDpi == displaymetrics.noncompatDensityDpi)
                                                {
                                                    flag1 = flag;
                                                    if(noncompatXdpi == displaymetrics.noncompatXdpi)
                                                    {
                                                        flag1 = flag;
                                                        if(noncompatYdpi == displaymetrics.noncompatYdpi)
                                                            flag1 = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public int hashCode()
    {
        return widthPixels * heightPixels * densityDpi;
    }

    public void setTo(DisplayMetrics displaymetrics)
    {
        if(this == displaymetrics)
        {
            return;
        } else
        {
            widthPixels = displaymetrics.widthPixels;
            heightPixels = displaymetrics.heightPixels;
            density = displaymetrics.density;
            densityDpi = displaymetrics.densityDpi;
            scaledDensity = displaymetrics.scaledDensity;
            xdpi = displaymetrics.xdpi;
            ydpi = displaymetrics.ydpi;
            noncompatWidthPixels = displaymetrics.noncompatWidthPixels;
            noncompatHeightPixels = displaymetrics.noncompatHeightPixels;
            noncompatDensity = displaymetrics.noncompatDensity;
            noncompatDensityDpi = displaymetrics.noncompatDensityDpi;
            noncompatScaledDensity = displaymetrics.noncompatScaledDensity;
            noncompatXdpi = displaymetrics.noncompatXdpi;
            noncompatYdpi = displaymetrics.noncompatYdpi;
            return;
        }
    }

    public void setToDefaults()
    {
        widthPixels = 0;
        heightPixels = 0;
        density = (float)DENSITY_DEVICE / 160F;
        densityDpi = DENSITY_DEVICE;
        scaledDensity = density;
        xdpi = DENSITY_DEVICE;
        ydpi = DENSITY_DEVICE;
        noncompatWidthPixels = widthPixels;
        noncompatHeightPixels = heightPixels;
        noncompatDensity = density;
        noncompatDensityDpi = densityDpi;
        noncompatScaledDensity = scaledDensity;
        noncompatXdpi = xdpi;
        noncompatYdpi = ydpi;
    }

    public String toString()
    {
        return (new StringBuilder()).append("DisplayMetrics{density=").append(density).append(", width=").append(widthPixels).append(", height=").append(heightPixels).append(", scaledDensity=").append(scaledDensity).append(", xdpi=").append(xdpi).append(", ydpi=").append(ydpi).append("}").toString();
    }

    public static final int DENSITY_260 = 260;
    public static final int DENSITY_280 = 280;
    public static final int DENSITY_300 = 300;
    public static final int DENSITY_340 = 340;
    public static final int DENSITY_360 = 360;
    public static final int DENSITY_400 = 400;
    public static final int DENSITY_420 = 420;
    public static final int DENSITY_560 = 560;
    public static final int DENSITY_DEFAULT = 160;
    public static final float DENSITY_DEFAULT_SCALE = 0.00625F;
    public static int DENSITY_DEVICE = 0;
    public static final int DENSITY_DEVICE_STABLE = getDeviceDensity();
    public static final int DENSITY_HIGH = 240;
    public static final int DENSITY_LOW = 120;
    public static final int DENSITY_MEDIUM = 160;
    public static final int DENSITY_NXHGITH = MiuiDisplayMetrics.getNxhdpiDensity();
    public static final int DENSITY_TV = 213;
    public static final int DENSITY_XHIGH = 320;
    public static final int DENSITY_XXHIGH = 480;
    public static final int DENSITY_XXXHIGH = 640;
    public float density;
    public int densityDpi;
    public int heightPixels;
    public float noncompatDensity;
    public int noncompatDensityDpi;
    public int noncompatHeightPixels;
    public float noncompatScaledDensity;
    public int noncompatWidthPixels;
    public float noncompatXdpi;
    public float noncompatYdpi;
    public float scaledDensity;
    public int widthPixels;
    public float xdpi;
    public float ydpi;

    static 
    {
        DENSITY_DEVICE = getDeviceDensity();
    }
}

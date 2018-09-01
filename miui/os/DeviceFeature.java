// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.os;

import android.content.res.Resources;
import android.os.SystemProperties;
import miui.util.FeatureParser;

public class DeviceFeature
{

    public DeviceFeature()
    {
    }

    public static final int BACKLIGHT_BIT = Resources.getSystem().getInteger(0x1107001a);
    public static final boolean PERSIST_SCREEN_EFFECT = SystemProperties.getBoolean("sys.persist_screen_effect", false);
    public static final boolean SCREEN_EFFECT_CONFLICT;
    public static final boolean SUPPORT_AUTO_BRIGHTNESS_OPTIMIZE;
    public static final boolean SUPPORT_DISPLAYFEATURE_HIDL = SystemProperties.getBoolean("sys.displayfeature_hidl", false);
    public static final boolean SUPPORT_NIGHT_LIGHT = Resources.getSystem().getBoolean(0x110a0020);
    public static final boolean SUPPORT_NIGHT_LIGHT_ADJ = Resources.getSystem().getBoolean(0x110a0021);

    static 
    {
        boolean flag = true;
        boolean flag1;
        if(FeatureParser.getBoolean("support_autobrightness_optimize", false) && android.os.Build.VERSION.SDK_INT > 23)
            flag1 = true;
        else
            flag1 = SystemProperties.getBoolean("sys.autobrightness_optimize", false);
        SUPPORT_AUTO_BRIGHTNESS_OPTIMIZE = flag1;
        if(SystemProperties.getInt("ro.df.effect.conflict", 0) == 1)
            flag1 = flag;
        else
            flag1 = false;
        SCREEN_EFFECT_CONFLICT = flag1;
    }
}

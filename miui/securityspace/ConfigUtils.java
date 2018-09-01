// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securityspace;

import miui.util.FeatureParser;

public class ConfigUtils
{

    public ConfigUtils()
    {
    }

    private static boolean isKitKat()
    {
        boolean flag;
        if(android.os.Build.VERSION.SDK_INT == 19)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isPad()
    {
        return FeatureParser.getBoolean("is_pad", false);
    }

    public static boolean isSupportSecuritySpace()
    {
        boolean flag;
        if(!isPad())
            flag = isKitKat() ^ true;
        else
            flag = false;
        return flag;
    }

    public static boolean isSupportXSpace()
    {
        boolean flag;
        if(!isPad())
            flag = isKitKat() ^ true;
        else
            flag = false;
        return flag;
    }
}

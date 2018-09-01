// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import miui.os.Build;

public final class SettingsInjector
{

    public SettingsInjector()
    {
    }

    public static String elderAltSettingName(String s)
    {
        String s1 = s;
        if(Build.getUserMode() == 1)
        {
            s1 = s;
            if("ringtone".equals(s))
                s1 = "elder-ringtone";
        }
        return s1;
    }

    public static final String ELDER_RINGTONE_ALT = "elder-ringtone";
    public static final String RINGTONE = "ringtone";
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.hardware;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.SystemProperties;
import android.text.TextUtils;

public class AmbientDisplayConfiguration
{

    public AmbientDisplayConfiguration(Context context)
    {
        mContext = context;
    }

    private boolean alwaysOnDisplayAvailable()
    {
        return mContext.getResources().getBoolean(0x1120049);
    }

    private boolean alwaysOnDisplayDebuggingEnabled()
    {
        boolean flag = false;
        if(SystemProperties.getBoolean("debug.doze.aod", false))
            flag = Build.IS_DEBUGGABLE;
        return flag;
    }

    private boolean ambientDisplayAvailable()
    {
        return TextUtils.isEmpty(ambientDisplayComponent()) ^ true;
    }

    private boolean boolSetting(String s, int i, int j)
    {
        boolean flag = false;
        if(android.provider.Settings.Secure.getIntForUser(mContext.getContentResolver(), s, j, i) != 0)
            flag = true;
        return flag;
    }

    private boolean boolSettingDefaultOff(String s, int i)
    {
        return boolSetting(s, i, 0);
    }

    private boolean boolSettingDefaultOn(String s, int i)
    {
        return boolSetting(s, i, 1);
    }

    private boolean pulseOnLongPressAvailable()
    {
        return TextUtils.isEmpty(longPressSensorType()) ^ true;
    }

    public boolean accessibilityInversionEnabled(int i)
    {
        return boolSettingDefaultOff("accessibility_display_inversion_enabled", i);
    }

    public boolean alwaysOnAvailable()
    {
        boolean flag;
        if(alwaysOnDisplayDebuggingEnabled() || alwaysOnDisplayAvailable())
            flag = ambientDisplayAvailable();
        else
            flag = false;
        return flag;
    }

    public boolean alwaysOnAvailableForUser(int i)
    {
        boolean flag;
        if(alwaysOnAvailable())
            flag = accessibilityInversionEnabled(i) ^ true;
        else
            flag = false;
        return flag;
    }

    public boolean alwaysOnEnabled(int i)
    {
        boolean flag;
        if(boolSettingDefaultOn("doze_always_on", i) && alwaysOnAvailable())
            flag = accessibilityInversionEnabled(i) ^ true;
        else
            flag = false;
        return flag;
    }

    public String ambientDisplayComponent()
    {
        return mContext.getResources().getString(0x1040134);
    }

    public boolean available()
    {
        boolean flag;
        if(!pulseOnNotificationAvailable() && !pulseOnPickupAvailable())
            flag = pulseOnDoubleTapAvailable();
        else
            flag = true;
        return flag;
    }

    public String doubleTapSensorType()
    {
        return mContext.getResources().getString(0x1040135);
    }

    public boolean enabled(int i)
    {
        boolean flag;
        if(!pulseOnNotificationEnabled(i) && !pulseOnPickupEnabled(i) && !pulseOnDoubleTapEnabled(i) && !pulseOnLongPressEnabled(i))
            flag = alwaysOnEnabled(i);
        else
            flag = true;
        return flag;
    }

    public String longPressSensorType()
    {
        return mContext.getResources().getString(0x1040136);
    }

    public boolean pulseOnDoubleTapAvailable()
    {
        boolean flag;
        if(!TextUtils.isEmpty(doubleTapSensorType()))
            flag = ambientDisplayAvailable();
        else
            flag = false;
        return flag;
    }

    public boolean pulseOnDoubleTapEnabled(int i)
    {
        boolean flag;
        if(boolSettingDefaultOn("doze_pulse_on_double_tap", i))
            flag = pulseOnDoubleTapAvailable();
        else
            flag = false;
        return flag;
    }

    public boolean pulseOnLongPressEnabled(int i)
    {
        boolean flag;
        if(pulseOnLongPressAvailable())
            flag = boolSettingDefaultOff("doze_pulse_on_long_press", i);
        else
            flag = false;
        return flag;
    }

    public boolean pulseOnNotificationAvailable()
    {
        return ambientDisplayAvailable();
    }

    public boolean pulseOnNotificationEnabled(int i)
    {
        boolean flag;
        if(boolSettingDefaultOn("doze_enabled", i))
            flag = pulseOnNotificationAvailable();
        else
            flag = false;
        return flag;
    }

    public boolean pulseOnPickupAvailable()
    {
        boolean flag;
        if(mContext.getResources().getBoolean(0x112004a))
            flag = ambientDisplayAvailable();
        else
            flag = false;
        return flag;
    }

    public boolean pulseOnPickupCanBeModified(int i)
    {
        return alwaysOnEnabled(i) ^ true;
    }

    public boolean pulseOnPickupEnabled(int i)
    {
        boolean flag;
        if(boolSettingDefaultOn("doze_pulse_on_pick_up", i) || alwaysOnEnabled(i))
            flag = pulseOnPickupAvailable();
        else
            flag = false;
        return flag;
    }

    private final Context mContext;
}

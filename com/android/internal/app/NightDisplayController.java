// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.*;
import android.util.Slog;
import java.time.*;
import java.time.format.DateTimeParseException;

public final class NightDisplayController
{
    public static interface Callback
    {

        public void onActivated(boolean flag)
        {
        }

        public void onAutoModeChanged(int i)
        {
        }

        public void onColorTemperatureChanged(int i)
        {
        }

        public void onCustomEndTimeChanged(LocalTime localtime)
        {
        }

        public void onCustomStartTimeChanged(LocalTime localtime)
        {
        }

        public void onDisplayColorModeChanged(int i)
        {
        }
    }


    static void _2D_wrap0(NightDisplayController nightdisplaycontroller, String s)
    {
        nightdisplaycontroller.onSettingChanged(s);
    }

    public NightDisplayController(Context context)
    {
        this(context, ActivityManager.getCurrentUser());
    }

    public NightDisplayController(Context context, int i)
    {
        mContext = context.getApplicationContext();
        mUserId = i;
        mContentObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {

            public void onChange(boolean flag, Uri uri)
            {
                super.onChange(flag, uri);
                if(uri == null)
                    uri = null;
                else
                    uri = uri.getLastPathSegment();
                if(uri != null)
                    NightDisplayController._2D_wrap0(NightDisplayController.this, uri);
            }

            final NightDisplayController this$0;

            
            {
                this$0 = NightDisplayController.this;
                super(handler);
            }
        }
;
    }

    public static boolean isAvailable(Context context)
    {
        return context.getResources().getBoolean(0x1120081);
    }

    private void onSettingChanged(String s)
    {
        if(mCallback == null) goto _L2; else goto _L1
_L1:
        if(!s.equals("night_display_activated")) goto _L4; else goto _L3
_L3:
        mCallback.onActivated(isActivated());
_L2:
        return;
_L4:
        if(s.equals("night_display_auto_mode"))
            mCallback.onAutoModeChanged(getAutoMode());
        else
        if(s.equals("night_display_custom_start_time"))
            mCallback.onCustomStartTimeChanged(getCustomStartTime());
        else
        if(s.equals("night_display_custom_end_time"))
            mCallback.onCustomEndTimeChanged(getCustomEndTime());
        else
        if(s.equals("night_display_color_temperature"))
            mCallback.onColorTemperatureChanged(getColorTemperature());
        else
        if(s.equals("display_color_mode"))
            mCallback.onDisplayColorModeChanged(getColorMode());
        if(true) goto _L2; else goto _L5
_L5:
    }

    public int getAutoMode()
    {
        int i = android.provider.Settings.Secure.getIntForUser(mContext.getContentResolver(), "night_display_auto_mode", -1, mUserId);
        int j = i;
        if(i == -1)
            j = mContext.getResources().getInteger(0x10e003e);
        i = j;
        if(j != 0)
        {
            i = j;
            if(j != 1)
            {
                i = j;
                if(j != 2)
                {
                    Slog.e("NightDisplayController", (new StringBuilder()).append("Invalid autoMode: ").append(j).toString());
                    i = 0;
                }
            }
        }
        return i;
    }

    public int getColorMode()
    {
        int i = 0;
        int j = android.provider.Settings.System.getIntForUser(mContext.getContentResolver(), "display_color_mode", -1, mUserId);
        if(j < 0 || j > 2)
        {
            if("1".equals(SystemProperties.get("persist.sys.sf.native_mode")))
                return 2;
            if(!"1.0".equals(SystemProperties.get("persist.sys.sf.color_saturation")))
                i = 1;
            return i;
        } else
        {
            return j;
        }
    }

    public int getColorTemperature()
    {
        int i;
        int j;
        int k;
        i = android.provider.Settings.Secure.getIntForUser(mContext.getContentResolver(), "night_display_color_temperature", -1, mUserId);
        j = i;
        if(i == -1)
            j = getDefaultColorTemperature();
        i = getMinimumColorTemperature();
        k = getMaximumColorTemperature();
        if(j >= i) goto _L2; else goto _L1
_L1:
        return i;
_L2:
        i = j;
        if(j > k)
            i = k;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public LocalTime getCustomEndTime()
    {
        int i = android.provider.Settings.Secure.getIntForUser(mContext.getContentResolver(), "night_display_custom_end_time", -1, mUserId);
        int j = i;
        if(i == -1)
            j = mContext.getResources().getInteger(0x10e003f);
        return LocalTime.ofSecondOfDay(j / 1000);
    }

    public LocalTime getCustomStartTime()
    {
        int i = android.provider.Settings.Secure.getIntForUser(mContext.getContentResolver(), "night_display_custom_start_time", -1, mUserId);
        int j = i;
        if(i == -1)
            j = mContext.getResources().getInteger(0x10e0040);
        return LocalTime.ofSecondOfDay(j / 1000);
    }

    public int getDefaultColorTemperature()
    {
        return mContext.getResources().getInteger(0x10e007d);
    }

    public LocalDateTime getLastActivatedTime()
    {
        Object obj;
        obj = android.provider.Settings.Secure.getStringForUser(mContext.getContentResolver(), "night_display_last_activated_time", mUserId);
        if(obj == null)
            break MISSING_BLOCK_LABEL_46;
        LocalDateTime localdatetime = LocalDateTime.parse(((CharSequence) (obj)));
        return localdatetime;
        DateTimeParseException datetimeparseexception;
        datetimeparseexception;
        obj = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(((String) (obj)))), ZoneId.systemDefault());
        return ((LocalDateTime) (obj));
        Object obj1;
        obj1;
        return null;
    }

    public int getMaximumColorTemperature()
    {
        return mContext.getResources().getInteger(0x10e007e);
    }

    public int getMinimumColorTemperature()
    {
        return mContext.getResources().getInteger(0x10e007f);
    }

    public boolean isActivated()
    {
        boolean flag = true;
        if(android.provider.Settings.Secure.getIntForUser(mContext.getContentResolver(), "night_display_activated", 0, mUserId) != 1)
            flag = false;
        return flag;
    }

    public boolean setActivated(boolean flag)
    {
        if(isActivated() != flag)
            android.provider.Settings.Secure.putStringForUser(mContext.getContentResolver(), "night_display_last_activated_time", LocalDateTime.now().toString(), mUserId);
        ContentResolver contentresolver = mContext.getContentResolver();
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        return android.provider.Settings.Secure.putIntForUser(contentresolver, "night_display_activated", i, mUserId);
    }

    public boolean setAutoMode(int i)
    {
        if(i != 0 && i != 1 && i != 2)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid autoMode: ").append(i).toString());
        if(getAutoMode() != i)
            android.provider.Settings.Secure.putStringForUser(mContext.getContentResolver(), "night_display_last_activated_time", null, mUserId);
        return android.provider.Settings.Secure.putIntForUser(mContext.getContentResolver(), "night_display_auto_mode", i, mUserId);
    }

    public void setColorMode(int i)
    {
        if(i < 0 || i > 2)
        {
            return;
        } else
        {
            android.provider.Settings.System.putIntForUser(mContext.getContentResolver(), "display_color_mode", i, mUserId);
            return;
        }
    }

    public boolean setColorTemperature(int i)
    {
        return android.provider.Settings.Secure.putIntForUser(mContext.getContentResolver(), "night_display_color_temperature", i, mUserId);
    }

    public boolean setCustomEndTime(LocalTime localtime)
    {
        if(localtime == null)
            throw new IllegalArgumentException("endTime cannot be null");
        else
            return android.provider.Settings.Secure.putIntForUser(mContext.getContentResolver(), "night_display_custom_end_time", localtime.toSecondOfDay() * 1000, mUserId);
    }

    public boolean setCustomStartTime(LocalTime localtime)
    {
        if(localtime == null)
            throw new IllegalArgumentException("startTime cannot be null");
        else
            return android.provider.Settings.Secure.putIntForUser(mContext.getContentResolver(), "night_display_custom_start_time", localtime.toSecondOfDay() * 1000, mUserId);
    }

    public void setListener(Callback callback)
    {
        Callback callback1 = mCallback;
        if(callback1 == callback) goto _L2; else goto _L1
_L1:
        mCallback = callback;
        if(callback != null) goto _L4; else goto _L3
_L3:
        mContext.getContentResolver().unregisterContentObserver(mContentObserver);
_L2:
        return;
_L4:
        if(callback1 == null)
        {
            callback = mContext.getContentResolver();
            callback.registerContentObserver(android.provider.Settings.Secure.getUriFor("night_display_activated"), false, mContentObserver, mUserId);
            callback.registerContentObserver(android.provider.Settings.Secure.getUriFor("night_display_auto_mode"), false, mContentObserver, mUserId);
            callback.registerContentObserver(android.provider.Settings.Secure.getUriFor("night_display_custom_start_time"), false, mContentObserver, mUserId);
            callback.registerContentObserver(android.provider.Settings.Secure.getUriFor("night_display_custom_end_time"), false, mContentObserver, mUserId);
            callback.registerContentObserver(android.provider.Settings.Secure.getUriFor("night_display_color_temperature"), false, mContentObserver, mUserId);
            callback.registerContentObserver(android.provider.Settings.System.getUriFor("display_color_mode"), false, mContentObserver, mUserId);
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public static final int AUTO_MODE_CUSTOM = 1;
    public static final int AUTO_MODE_DISABLED = 0;
    public static final int AUTO_MODE_TWILIGHT = 2;
    public static final int COLOR_MODE_BOOSTED = 1;
    public static final int COLOR_MODE_NATURAL = 0;
    public static final int COLOR_MODE_SATURATED = 2;
    private static final boolean DEBUG = false;
    private static final String PERSISTENT_PROPERTY_NATIVE_MODE = "persist.sys.sf.native_mode";
    private static final String PERSISTENT_PROPERTY_SATURATION = "persist.sys.sf.color_saturation";
    private static final String TAG = "NightDisplayController";
    private Callback mCallback;
    private final ContentObserver mContentObserver;
    private final Context mContext;
    private final int mUserId;
}

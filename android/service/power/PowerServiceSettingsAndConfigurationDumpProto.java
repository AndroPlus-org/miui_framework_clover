// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.power;


public final class PowerServiceSettingsAndConfigurationDumpProto
{
    public final class ScreenBrightnessSettingLimitsProto
    {

        public static final long SETTING_DEFAULT = 0x10300000003L;
        public static final long SETTING_FOR_VR_DEFAULT = 0x10300000004L;
        public static final long SETTING_MAXIMUM = 0x10300000002L;
        public static final long SETTING_MINIMUM = 0x10300000001L;
        final PowerServiceSettingsAndConfigurationDumpProto this$0;

        public ScreenBrightnessSettingLimitsProto()
        {
            this$0 = PowerServiceSettingsAndConfigurationDumpProto.this;
            super();
        }
    }

    public final class StayOnWhilePluggedInProto
    {

        public static final long IS_STAY_ON_WHILE_PLUGGED_IN_AC = 0x10d00000001L;
        public static final long IS_STAY_ON_WHILE_PLUGGED_IN_USB = 0x10d00000002L;
        public static final long IS_STAY_ON_WHILE_PLUGGED_IN_WIRELESS = 0x10d00000003L;
        final PowerServiceSettingsAndConfigurationDumpProto this$0;

        public StayOnWhilePluggedInProto()
        {
            this$0 = PowerServiceSettingsAndConfigurationDumpProto.this;
            super();
        }
    }


    public PowerServiceSettingsAndConfigurationDumpProto()
    {
    }

    public static final long ARE_DREAMS_ACTIVATED_ON_DOCK_BY_DEFAULT_CONFIG = 0x10d0000000aL;
    public static final long ARE_DREAMS_ACTIVATED_ON_SLEEP_BY_DEFAULT_CONFIG = 0x10d00000009L;
    public static final long ARE_DREAMS_ACTIVATE_ON_DOCK_SETTING = 0x10d00000011L;
    public static final long ARE_DREAMS_ACTIVATE_ON_SLEEP_SETTING = 0x10d00000010L;
    public static final long ARE_DREAMS_ENABLED_BY_DEFAULT_CONFIG = 0x10d00000008L;
    public static final long ARE_DREAMS_ENABLED_ON_BATTERY_CONFIG = 0x10d0000000bL;
    public static final long ARE_DREAMS_ENABLED_SETTING = 0x10d0000000fL;
    public static final long ARE_DREAMS_SUPPORTED_CONFIG = 0x10d00000007L;
    public static final int DISPLAY_STATE_DOZE = 3;
    public static final int DISPLAY_STATE_DOZE_SUSPEND = 4;
    public static final int DISPLAY_STATE_OFF = 1;
    public static final int DISPLAY_STATE_ON = 2;
    public static final int DISPLAY_STATE_UNKNOWN = 0;
    public static final int DISPLAY_STATE_VR = 5;
    public static final long DOZED_SCREEN_BRIGHTNESS_OVERRIDE_FROM_DREAM_MANAGER = 0x10200000027L;
    public static final long DOZE_SCREEN_STATE_OVERRIDE_FROM_DREAM_MANAGER = 0x11000000026L;
    public static final long DREAMS_BATTERY_LEVEL_DRAIN_CUTOFF_CONFIG = 0x1070000000eL;
    public static final long DREAMS_BATTERY_LEVEL_MINIMUM_WHEN_NOT_POWERED_CONFIG = 0x1070000000dL;
    public static final long DREAMS_BATTERY_LEVEL_MINIMUM_WHEN_POWERED_CONFIG = 0x1070000000cL;
    public static final long IS_AUTO_LOW_POWER_MODE_CONFIGURED = 0x10d00000014L;
    public static final long IS_AUTO_LOW_POWER_MODE_SNOOZING = 0x10d00000015L;
    public static final long IS_DECOUPLE_HAL_AUTO_SUSPEND_MODE_FROM_DISPLAY_CONFIG = 0x10d00000001L;
    public static final long IS_DECOUPLE_HAL_INTERACTIVE_MODE_FROM_DISPLAY_CONFIG = 0x10d00000002L;
    public static final long IS_DOUBLE_TAP_WAKE_ENABLED = 0x10d0000002aL;
    public static final long IS_DOZE_AFTER_SCREEN_OFF_CONFIG = 0x10d00000012L;
    public static final long IS_LOW_POWER_MODE_SETTING = 0x10d00000013L;
    public static final long IS_MAXIMUM_SCREEN_OFF_TIMEOUT_FROM_DEVICE_ADMIN_ENFORCED_LOCKED = 0x10d0000001cL;
    public static final long IS_SUSPEND_WHEN_SCREEN_OFF_DUE_TO_PROXIMITY_CONFIG = 0x10d00000006L;
    public static final long IS_THEATER_MODE_ENABLED = 0x10d00000005L;
    public static final long IS_USER_INACTIVE_OVERRIDE_FROM_WINDOW_MANAGER = 0x10d00000023L;
    public static final long IS_VR_MODE_ENABLED = 0x10d0000002bL;
    public static final long IS_WAKE_UP_WHEN_PLUGGED_OR_UNPLUGGED_CONFIG = 0x10d00000003L;
    public static final long IS_WAKE_UP_WHEN_PLUGGED_OR_UNPLUGGED_IN_THEATER_MODE_CONFIG = 0x10d00000004L;
    public static final long MAXIMUM_SCREEN_DIM_DURATION_CONFIG_MS = 0x10300000017L;
    public static final long MAXIMUM_SCREEN_DIM_RATIO_CONFIG = 0x10200000018L;
    public static final long MAXIMUM_SCREEN_OFF_TIMEOUT_FROM_DEVICE_ADMIN_MS = 0x1030000001bL;
    public static final long MINIMUM_SCREEN_OFF_TIMEOUT_CONFIG_MS = 0x10300000016L;
    public static final long SCREEN_AUTO_BRIGHTNESS_ADJUSTMENT_SETTING = 0x1020000001fL;
    public static final long SCREEN_BRIGHTNESS_FOR_VR_SETTING = 0x10300000029L;
    public static final int SCREEN_BRIGHTNESS_MODE_AUTOMATIC = 1;
    public static final int SCREEN_BRIGHTNESS_MODE_MANUAL = 0;
    public static final long SCREEN_BRIGHTNESS_MODE_SETTING = 0x11000000020L;
    public static final long SCREEN_BRIGHTNESS_OVERRIDE_FROM_WINDOW_MANAGER = 0x10700000021L;
    public static final long SCREEN_BRIGHTNESS_SETTING = 0x1070000001eL;
    public static final long SCREEN_BRIGHTNESS_SETTING_LIMITS = 0x11100000028L;
    public static final long SCREEN_OFF_TIMEOUT_SETTING_MS = 0x10300000019L;
    public static final long SLEEP_TIMEOUT_SETTING_MS = 0x1070000001aL;
    public static final long STAY_ON_WHILE_PLUGGED_IN = 0x1110000001dL;
    public static final long TEMPORARY_SCREEN_AUTO_BRIGHTNESS_ADJUSTMENT_SETTING_OVERRIDE = 0x10200000025L;
    public static final long TEMPORARY_SCREEN_BRIGHTNESS_SETTING_OVERRIDE = 0x10700000024L;
    public static final long USER_ACTIVITY_TIMEOUT_OVERRIDE_FROM_WINDOW_MANAGER_MS = 0x10800000022L;
}

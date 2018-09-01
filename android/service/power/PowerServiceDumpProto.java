// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.power;


public final class PowerServiceDumpProto
{
    public final class ActiveWakeLocksProto
    {

        public static final long IS_BUTTON_BRIGHT = 0x10d00000004L;
        public static final long IS_CPU = 0x10d00000001L;
        public static final long IS_DOZE = 0x10d00000007L;
        public static final long IS_DRAW = 0x10d00000008L;
        public static final long IS_PROXIMITY_SCREEN_OFF = 0x10d00000005L;
        public static final long IS_SCREEN_BRIGHT = 0x10d00000002L;
        public static final long IS_SCREEN_DIM = 0x10d00000003L;
        public static final long IS_STAY_AWAKE = 0x10d00000006L;
        final PowerServiceDumpProto this$0;

        public ActiveWakeLocksProto()
        {
            this$0 = PowerServiceDumpProto.this;
            super();
        }
    }

    public final class ConstantsProto
    {

        public static final long IS_NO_CACHED_WAKE_LOCKS = 0x10d00000001L;
        final PowerServiceDumpProto this$0;

        public ConstantsProto()
        {
            this$0 = PowerServiceDumpProto.this;
            super();
        }
    }

    public final class UidProto
    {

        public static final long IS_ACTIVE = 0x10d00000003L;
        public static final long IS_PROCESS_STATE_UNKNOWN = 0x10d00000005L;
        public static final long NUM_WAKE_LOCKS = 0x10300000004L;
        public static final long PROCESS_STATE = 0x11000000006L;
        public static final int PROCESS_STATE_BACKUP = 8;
        public static final int PROCESS_STATE_BOUND_FOREGROUND_SERVICE = 3;
        public static final int PROCESS_STATE_CACHED_ACTIVITY = 14;
        public static final int PROCESS_STATE_CACHED_ACTIVITY_CLIENT = 15;
        public static final int PROCESS_STATE_CACHED_EMPTY = 16;
        public static final int PROCESS_STATE_FOREGROUND_SERVICE = 4;
        public static final int PROCESS_STATE_HEAVY_WEIGHT = 9;
        public static final int PROCESS_STATE_HOME = 12;
        public static final int PROCESS_STATE_IMPORTANT_BACKGROUND = 7;
        public static final int PROCESS_STATE_IMPORTANT_FOREGROUND = 6;
        public static final int PROCESS_STATE_LAST_ACTIVITY = 13;
        public static final int PROCESS_STATE_NONEXISTENT = 17;
        public static final int PROCESS_STATE_PERSISTENT = 0;
        public static final int PROCESS_STATE_PERSISTENT_UI = 1;
        public static final int PROCESS_STATE_RECEIVER = 11;
        public static final int PROCESS_STATE_SERVICE = 10;
        public static final int PROCESS_STATE_TOP = 2;
        public static final int PROCESS_STATE_TOP_SLEEPING = 5;
        public static final long UID = 0x10300000001L;
        public static final long UID_STRING = 0x10e00000002L;
        final PowerServiceDumpProto this$0;

        public UidProto()
        {
            this$0 = PowerServiceDumpProto.this;
            super();
        }
    }

    public final class UserActivityProto
    {

        public static final long IS_SCREEN_BRIGHT = 0x10d00000001L;
        public static final long IS_SCREEN_DIM = 0x10d00000002L;
        public static final long IS_SCREEN_DREAM = 0x10d00000003L;
        final PowerServiceDumpProto this$0;

        public UserActivityProto()
        {
            this$0 = PowerServiceDumpProto.this;
            super();
        }
    }


    public PowerServiceDumpProto()
    {
    }

    public static final long ACTIVE_WAKE_LOCKS = 0x11100000010L;
    public static final long ARE_UIDS_CHANGED = 0x10d0000002dL;
    public static final long ARE_UIDS_CHANGING = 0x10d0000002cL;
    public static final long BATTERY_LEVEL = 0x10300000007L;
    public static final long BATTERY_LEVEL_WHEN_DREAM_STARTED = 0x10300000008L;
    public static final long CONSTANTS = 0x11100000001L;
    public static final long DEVICE_IDLE_TEMP_WHITELIST = 0x2030000001dL;
    public static final long DEVICE_IDLE_WHITELIST = 0x2030000001cL;
    public static final long DIRTY = 0x10300000002L;
    public static final long DOCK_STATE = 0x11000000009L;
    public static final int DOCK_STATE_CAR = 2;
    public static final int DOCK_STATE_DESK = 1;
    public static final int DOCK_STATE_HE_DESK = 4;
    public static final int DOCK_STATE_LE_DESK = 3;
    public static final int DOCK_STATE_UNDOCKED = 0;
    public static final long IS_BATTERY_LEVEL_LOW = 0x10d00000019L;
    public static final long IS_BOOT_COMPLETED = 0x10d0000000cL;
    public static final long IS_DEVICE_IDLE_MODE = 0x10d0000001bL;
    public static final long IS_DISPLAY_READY = 0x10d00000025L;
    public static final long IS_HAL_AUTO_INTERACTIVE_MODE_ENABLED = 0x10d0000000fL;
    public static final long IS_HAL_AUTO_SUSPEND_MODE_ENABLED = 0x10d0000000eL;
    public static final long IS_HOLDING_DISPLAY_SUSPEND_BLOCKER = 0x10d00000027L;
    public static final long IS_HOLDING_WAKE_LOCK_SUSPEND_BLOCKER = 0x10d00000026L;
    public static final long IS_LIGHT_DEVICE_IDLE_MODE = 0x10d0000001aL;
    public static final long IS_LOW_POWER_MODE_ENABLED = 0x10d00000018L;
    public static final long IS_POWERED = 0x10d00000005L;
    public static final long IS_PROXIMITY_POSITIVE = 0x10d0000000bL;
    public static final long IS_REQUEST_WAIT_FOR_NEGATIVE_PROXIMITY = 0x10d00000015L;
    public static final long IS_SANDMAN_SCHEDULED = 0x10d00000016L;
    public static final long IS_SANDMAN_SUMMONED = 0x10d00000017L;
    public static final long IS_SCREEN_BRIGHTNESS_BOOST_IN_PROGRESS = 0x10d00000024L;
    public static final long IS_STAY_ON = 0x10d0000000aL;
    public static final long IS_SYSTEM_READY = 0x10d0000000dL;
    public static final long IS_WAKEFULNESS_CHANGING = 0x10d00000004L;
    public static final long LAST_INTERACTIVE_POWER_HINT_TIME_MS = 0x10400000022L;
    public static final long LAST_SCREEN_BRIGHTNESS_BOOST_TIME_MS = 0x10400000023L;
    public static final long LAST_SLEEP_TIME_MS = 0x1040000001fL;
    public static final long LAST_USER_ACTIVITY_TIME_MS = 0x10400000020L;
    public static final long LAST_USER_ACTIVITY_TIME_NO_CHANGE_LIGHTS_MS = 0x10400000021L;
    public static final long LAST_WAKE_TIME_MS = 0x1040000001eL;
    public static final long LOOPER = 0x1110000002fL;
    public static final long NOTIFY_LONG_DISPATCHED_MS = 0x10400000012L;
    public static final long NOTIFY_LONG_NEXT_CHECK_MS = 0x10400000013L;
    public static final long NOTIFY_LONG_SCHEDULED_MS = 0x10400000011L;
    public static final long PLUG_TYPE = 0x11000000006L;
    public static final int PLUG_TYPE_NONE = 0;
    public static final int PLUG_TYPE_PLUGGED_AC = 1;
    public static final int PLUG_TYPE_PLUGGED_USB = 2;
    public static final int PLUG_TYPE_PLUGGED_WIRELESS = 4;
    public static final long SCREEN_DIM_DURATION_MS = 0x1030000002bL;
    public static final long SCREEN_OFF_TIMEOUT_MS = 0x1030000002aL;
    public static final long SETTINGS_AND_CONFIGURATION = 0x11100000028L;
    public static final long SLEEP_TIMEOUT_MS = 0x10700000029L;
    public static final long SUSPEND_BLOCKERS = 0x21100000031L;
    public static final long UIDS = 0x2110000002eL;
    public static final long USER_ACTIVITY = 0x11100000014L;
    public static final long WAKEFULNESS = 0x11000000003L;
    public static final int WAKEFULNESS_ASLEEP = 0;
    public static final int WAKEFULNESS_AWAKE = 1;
    public static final int WAKEFULNESS_DOZING = 3;
    public static final int WAKEFULNESS_DREAMING = 2;
    public static final int WAKEFULNESS_UNKNOWN = 4;
    public static final long WAKE_LOCKS = 0x21100000030L;
    public static final long WIRELESS_CHARGER_DETECTOR = 0x11100000032L;
}

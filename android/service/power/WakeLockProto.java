// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.power;


public final class WakeLockProto
{
    public final class WakeLockFlagsProto
    {

        public static final long IS_ACQUIRE_CAUSES_WAKEUP = 0x10d00000001L;
        public static final long IS_ON_AFTER_RELEASE = 0x10d00000002L;
        final WakeLockProto this$0;

        public WakeLockFlagsProto()
        {
            this$0 = WakeLockProto.this;
            super();
        }
    }


    public WakeLockProto()
    {
    }

    public static final long ACQ_MS = 0x10400000005L;
    public static final int DOZE_WAKE_LOCK = 64;
    public static final int DRAW_WAKE_LOCK = 128;
    public static final long FLAGS = 0x11100000003L;
    public static final int FULL_WAKE_LOCK = 26;
    public static final long IS_DISABLED = 0x10d00000004L;
    public static final long IS_NOTIFIED_LONG = 0x10d00000006L;
    public static final long LOCK_LEVEL = 0x11000000001L;
    public static final int PARTIAL_WAKE_LOCK = 1;
    public static final long PID = 0x10300000008L;
    public static final int PROXIMITY_SCREEN_OFF_WAKE_LOCK = 32;
    public static final int SCREEN_BRIGHT_WAKE_LOCK = 10;
    public static final int SCREEN_DIM_WAKE_LOCK = 6;
    public static final long TAG = 0x10e00000002L;
    public static final long UID = 0x10300000007L;
    public static final int WAKE_LOCK_INVALID = 0;
    public static final long WORK_SOURCE = 0x11100000009L;
}

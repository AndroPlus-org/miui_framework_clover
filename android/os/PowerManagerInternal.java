// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            PowerSaveState

public abstract class PowerManagerInternal
{
    public static interface LowPowerModeListener
    {

        public abstract int getServiceType();

        public abstract void onLowPowerModeChanged(PowerSaveState powersavestate);
    }


    public PowerManagerInternal()
    {
    }

    public static boolean isInteractive(int i)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(i != 1)
            if(i == 2)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static String wakefulnessToString(int i)
    {
        switch(i)
        {
        default:
            return Integer.toString(i);

        case 0: // '\0'
            return "Asleep";

        case 1: // '\001'
            return "Awake";

        case 2: // '\002'
            return "Dreaming";

        case 3: // '\003'
            return "Dozing";
        }
    }

    public abstract void addVisibleWindowUids(int i);

    public abstract void clearVisibleWindowUids();

    public abstract void finishUidChanges();

    public abstract PowerSaveState getLowPowerState(int i);

    public abstract void powerHint(int i, int j);

    public abstract void registerLowPowerModeObserver(LowPowerModeListener lowpowermodelistener);

    public abstract boolean setDeviceIdleMode(boolean flag);

    public abstract void setDeviceIdleTempWhitelist(int ai[]);

    public abstract void setDeviceIdleWhitelist(int ai[]);

    public abstract void setDozeOverrideFromDreamManager(int i, int j);

    public abstract boolean setLightDeviceIdleMode(boolean flag);

    public abstract void setMaximumScreenOffTimeoutFromDeviceAdmin(int i);

    public abstract void setScreenBrightnessOverrideFromWindowManager(int i);

    public abstract void setUserActivityTimeoutOverrideFromWindowManager(long l);

    public abstract void setUserInactiveOverrideFromWindowManager();

    public abstract void startUidChanges();

    public abstract void uidActive(int i);

    public abstract void uidGone(int i);

    public abstract void uidIdle(int i);

    public abstract void updateUidProcState(int i, int j);

    public static final int WAKEFULNESS_ASLEEP = 0;
    public static final int WAKEFULNESS_AWAKE = 1;
    public static final int WAKEFULNESS_DOZING = 3;
    public static final int WAKEFULNESS_DREAMING = 2;
}

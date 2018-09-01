// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import com.android.internal.app.IBatteryStats;

// Referenced classes of package android.os:
//            ServiceManager, RemoteException, BatteryProperty, IBatteryPropertiesRegistrar

public class BatteryManager
{

    public BatteryManager()
    {
        mBatteryStats = com.android.internal.app.IBatteryStats.Stub.asInterface(ServiceManager.getService("batterystats"));
        mBatteryPropertiesRegistrar = IBatteryPropertiesRegistrar.Stub.asInterface(ServiceManager.getService("batteryproperties"));
    }

    public BatteryManager(IBatteryStats ibatterystats, IBatteryPropertiesRegistrar ibatterypropertiesregistrar)
    {
        mBatteryStats = ibatterystats;
        mBatteryPropertiesRegistrar = ibatterypropertiesregistrar;
    }

    private long queryProperty(int i)
    {
        if(mBatteryPropertiesRegistrar == null)
            return 0x8000000000000000L;
        BatteryProperty batteryproperty;
        batteryproperty = JVM INSTR new #129 <Class BatteryProperty>;
        batteryproperty.BatteryProperty();
        if(mBatteryPropertiesRegistrar.getProperty(i, batteryproperty) != 0) goto _L2; else goto _L1
_L1:
        long l = batteryproperty.getLong();
_L4:
        return l;
_L2:
        l = 0x8000000000000000L;
        if(true) goto _L4; else goto _L3
_L3:
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    public int getIntProperty(int i)
    {
        return (int)queryProperty(i);
    }

    public long getLongProperty(int i)
    {
        return queryProperty(i);
    }

    public boolean isCharging()
    {
        boolean flag;
        try
        {
            flag = mBatteryStats.isCharging();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public static final String ACTION_CHARGING = "android.os.action.CHARGING";
    public static final String ACTION_DISCHARGING = "android.os.action.DISCHARGING";
    public static final int BATTERY_HEALTH_COLD = 7;
    public static final int BATTERY_HEALTH_DEAD = 4;
    public static final int BATTERY_HEALTH_GOOD = 2;
    public static final int BATTERY_HEALTH_OVERHEAT = 3;
    public static final int BATTERY_HEALTH_OVER_VOLTAGE = 5;
    public static final int BATTERY_HEALTH_UNKNOWN = 1;
    public static final int BATTERY_HEALTH_UNSPECIFIED_FAILURE = 6;
    public static final int BATTERY_PLUGGED_AC = 1;
    public static final int BATTERY_PLUGGED_ANY = 7;
    public static final int BATTERY_PLUGGED_USB = 2;
    public static final int BATTERY_PLUGGED_WIRELESS = 4;
    public static final int BATTERY_PROPERTY_CAPACITY = 4;
    public static final int BATTERY_PROPERTY_CHARGE_COUNTER = 1;
    public static final int BATTERY_PROPERTY_CURRENT_AVERAGE = 3;
    public static final int BATTERY_PROPERTY_CURRENT_NOW = 2;
    public static final int BATTERY_PROPERTY_ENERGY_COUNTER = 5;
    public static final int BATTERY_PROPERTY_STATUS = 6;
    public static final int BATTERY_STATUS_CHARGING = 2;
    public static final int BATTERY_STATUS_DISCHARGING = 3;
    public static final int BATTERY_STATUS_FULL = 5;
    public static final int BATTERY_STATUS_NOT_CHARGING = 4;
    public static final int BATTERY_STATUS_UNKNOWN = 1;
    public static final String EXTRA_CHARGE_COUNTER = "charge_counter";
    public static final String EXTRA_HEALTH = "health";
    public static final String EXTRA_ICON_SMALL = "icon-small";
    public static final String EXTRA_INVALID_CHARGER = "invalid_charger";
    public static final String EXTRA_LEVEL = "level";
    public static final String EXTRA_MAX_CHARGING_CURRENT = "max_charging_current";
    public static final String EXTRA_MAX_CHARGING_VOLTAGE = "max_charging_voltage";
    public static final String EXTRA_PLUGGED = "plugged";
    public static final String EXTRA_PRESENT = "present";
    public static final String EXTRA_SCALE = "scale";
    public static final String EXTRA_SEQUENCE = "seq";
    public static final String EXTRA_STATUS = "status";
    public static final String EXTRA_TECHNOLOGY = "technology";
    public static final String EXTRA_TEMPERATURE = "temperature";
    public static final String EXTRA_VOLTAGE = "voltage";
    private final IBatteryPropertiesRegistrar mBatteryPropertiesRegistrar;
    private final IBatteryStats mBatteryStats;
}

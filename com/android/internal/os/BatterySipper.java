// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;


public class BatterySipper
    implements Comparable
{
    public static final class DrainType extends Enum
    {

        public static DrainType valueOf(String s)
        {
            return (DrainType)Enum.valueOf(com/android/internal/os/BatterySipper$DrainType, s);
        }

        public static DrainType[] values()
        {
            return $VALUES;
        }

        private static final DrainType $VALUES[];
        public static final DrainType APP;
        public static final DrainType BLUETOOTH;
        public static final DrainType CAMERA;
        public static final DrainType CELL;
        public static final DrainType FLASHLIGHT;
        public static final DrainType IDLE;
        public static final DrainType MEMORY;
        public static final DrainType OVERCOUNTED;
        public static final DrainType PHONE;
        public static final DrainType SCREEN;
        public static final DrainType UNACCOUNTED;
        public static final DrainType USER;
        public static final DrainType WIFI;

        static 
        {
            IDLE = new DrainType("IDLE", 0);
            CELL = new DrainType("CELL", 1);
            PHONE = new DrainType("PHONE", 2);
            WIFI = new DrainType("WIFI", 3);
            BLUETOOTH = new DrainType("BLUETOOTH", 4);
            FLASHLIGHT = new DrainType("FLASHLIGHT", 5);
            SCREEN = new DrainType("SCREEN", 6);
            APP = new DrainType("APP", 7);
            USER = new DrainType("USER", 8);
            UNACCOUNTED = new DrainType("UNACCOUNTED", 9);
            OVERCOUNTED = new DrainType("OVERCOUNTED", 10);
            CAMERA = new DrainType("CAMERA", 11);
            MEMORY = new DrainType("MEMORY", 12);
            $VALUES = (new DrainType[] {
                IDLE, CELL, PHONE, WIFI, BLUETOOTH, FLASHLIGHT, SCREEN, APP, USER, UNACCOUNTED, 
                OVERCOUNTED, CAMERA, MEMORY
            });
        }

        private DrainType(String s, int i)
        {
            super(s, i);
        }
    }


    public BatterySipper(DrainType draintype, android.os.BatteryStats.Uid uid, double d)
    {
        totalPowerMah = d;
        drainType = draintype;
        uidObj = uid;
    }

    public void add(BatterySipper batterysipper)
    {
        totalPowerMah = totalPowerMah + batterysipper.totalPowerMah;
        usageTimeMs = usageTimeMs + batterysipper.usageTimeMs;
        usagePowerMah = usagePowerMah + batterysipper.usagePowerMah;
        cpuTimeMs = cpuTimeMs + batterysipper.cpuTimeMs;
        gpsTimeMs = gpsTimeMs + batterysipper.gpsTimeMs;
        wifiRunningTimeMs = wifiRunningTimeMs + batterysipper.wifiRunningTimeMs;
        cpuFgTimeMs = cpuFgTimeMs + batterysipper.cpuFgTimeMs;
        wakeLockTimeMs = wakeLockTimeMs + batterysipper.wakeLockTimeMs;
        cameraTimeMs = cameraTimeMs + batterysipper.cameraTimeMs;
        flashlightTimeMs = flashlightTimeMs + batterysipper.flashlightTimeMs;
        bluetoothRunningTimeMs = bluetoothRunningTimeMs + batterysipper.bluetoothRunningTimeMs;
        mobileRxPackets = mobileRxPackets + batterysipper.mobileRxPackets;
        mobileTxPackets = mobileTxPackets + batterysipper.mobileTxPackets;
        mobileActive = mobileActive + batterysipper.mobileActive;
        mobileActiveCount = mobileActiveCount + batterysipper.mobileActiveCount;
        wifiRxPackets = wifiRxPackets + batterysipper.wifiRxPackets;
        wifiTxPackets = wifiTxPackets + batterysipper.wifiTxPackets;
        mobileRxBytes = mobileRxBytes + batterysipper.mobileRxBytes;
        mobileTxBytes = mobileTxBytes + batterysipper.mobileTxBytes;
        wifiRxBytes = wifiRxBytes + batterysipper.wifiRxBytes;
        wifiTxBytes = wifiTxBytes + batterysipper.wifiTxBytes;
        btRxBytes = btRxBytes + batterysipper.btRxBytes;
        btTxBytes = btTxBytes + batterysipper.btTxBytes;
        wifiPowerMah = wifiPowerMah + batterysipper.wifiPowerMah;
        gpsPowerMah = gpsPowerMah + batterysipper.gpsPowerMah;
        cpuPowerMah = cpuPowerMah + batterysipper.cpuPowerMah;
        sensorPowerMah = sensorPowerMah + batterysipper.sensorPowerMah;
        mobileRadioPowerMah = mobileRadioPowerMah + batterysipper.mobileRadioPowerMah;
        wakeLockPowerMah = wakeLockPowerMah + batterysipper.wakeLockPowerMah;
        cameraPowerMah = cameraPowerMah + batterysipper.cameraPowerMah;
        flashlightPowerMah = flashlightPowerMah + batterysipper.flashlightPowerMah;
        bluetoothPowerMah = bluetoothPowerMah + batterysipper.bluetoothPowerMah;
        screenPowerMah = screenPowerMah + batterysipper.screenPowerMah;
        proportionalSmearMah = proportionalSmearMah + batterysipper.proportionalSmearMah;
        totalSmearedPowerMah = totalSmearedPowerMah + batterysipper.totalSmearedPowerMah;
    }

    public int compareTo(BatterySipper batterysipper)
    {
        if(drainType != batterysipper.drainType)
        {
            if(drainType == DrainType.OVERCOUNTED)
                return 1;
            if(batterysipper.drainType == DrainType.OVERCOUNTED)
                return -1;
        }
        return Double.compare(batterysipper.totalPowerMah, totalPowerMah);
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((BatterySipper)obj);
    }

    public void computeMobilemspp()
    {
        long l = mobileRxPackets + mobileTxPackets;
        double d;
        if(l > 0L)
            d = (double)mobileActive / (double)l;
        else
            d = 0.0D;
        mobilemspp = d;
    }

    public String[] getPackages()
    {
        return mPackages;
    }

    public int getUid()
    {
        if(uidObj == null)
            return 0;
        else
            return uidObj.getUid();
    }

    public double sumPower()
    {
        totalPowerMah = usagePowerMah + wifiPowerMah + gpsPowerMah + cpuPowerMah + sensorPowerMah + mobileRadioPowerMah + wakeLockPowerMah + cameraPowerMah + flashlightPowerMah + bluetoothPowerMah;
        totalSmearedPowerMah = totalPowerMah + screenPowerMah + proportionalSmearMah;
        return totalPowerMah;
    }

    public double bluetoothPowerMah;
    public long bluetoothRunningTimeMs;
    public long btRxBytes;
    public long btTxBytes;
    public double cameraPowerMah;
    public long cameraTimeMs;
    public long cpuFgTimeMs;
    public double cpuPowerMah;
    public long cpuTimeMs;
    public DrainType drainType;
    public double flashlightPowerMah;
    public long flashlightTimeMs;
    public double gpsPowerMah;
    public long gpsTimeMs;
    public String mPackages[];
    public long mobileActive;
    public int mobileActiveCount;
    public double mobileRadioPowerMah;
    public long mobileRxBytes;
    public long mobileRxPackets;
    public long mobileTxBytes;
    public long mobileTxPackets;
    public double mobilemspp;
    public double noCoveragePercent;
    public String packageWithHighestDrain;
    public double percent;
    public double proportionalSmearMah;
    public double screenPowerMah;
    public double sensorPowerMah;
    public boolean shouldHide;
    public double totalPowerMah;
    public double totalSmearedPowerMah;
    public android.os.BatteryStats.Uid uidObj;
    public double usagePowerMah;
    public long usageTimeMs;
    public int userId;
    public double wakeLockPowerMah;
    public long wakeLockTimeMs;
    public double wifiPowerMah;
    public long wifiRunningTimeMs;
    public long wifiRxBytes;
    public long wifiRxPackets;
    public long wifiTxBytes;
    public long wifiTxPackets;
}

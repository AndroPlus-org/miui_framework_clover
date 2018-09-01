// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securitycenter.powercenter;


public class HistoryItemWrapper
{

    public HistoryItemWrapper()
    {
        cmd = (byte)-1;
    }

    public Object getObjectValue(String s)
    {
        if(s.equals("time"))
            return Long.valueOf(time);
        if(s.equals("cmd"))
            return Integer.valueOf(cmd);
        if(s.equals("batteryLevel"))
            return Integer.valueOf(batteryLevel);
        if(s.equals("batteryStatus"))
            return Integer.valueOf(batteryStatus);
        if(s.equals("batteryHealth"))
            return Integer.valueOf(batteryHealth);
        if(s.equals("batteryPlugType"))
            return Integer.valueOf(batteryPlugType);
        if(s.equals("batteryTemperature"))
            return Integer.valueOf(batteryTemperature);
        if(s.equals("batteryVoltage"))
            return Integer.valueOf(batteryVoltage);
        if(s.equals("wifiOn"))
            return Boolean.valueOf(wifiOn);
        if(s.equals("gpsOn"))
            return Boolean.valueOf(gpsOn);
        if(s.equals("charging"))
            return Boolean.valueOf(charging);
        if(s.equals("screenOn"))
            return Boolean.valueOf(screenOn);
        if(s.equals("wakelockOn"))
            return Boolean.valueOf(wakelockOn);
        if(s.equals("phoneSignalStrength"))
            return Integer.valueOf(phoneSignalStrength);
        if(s.equals("cpuRunning"))
            return Boolean.valueOf(cpuRunning);
        else
            return null;
    }

    public long getTime()
    {
        return time;
    }

    public boolean isDeltaData()
    {
        boolean flag = true;
        boolean flag1 = true;
        if(android.os.Build.VERSION.SDK_INT >= 21)
        {
            if(cmd != 0)
                flag1 = false;
            return flag1;
        }
        if(cmd == 1)
            flag1 = flag;
        else
            flag1 = false;
        return flag1;
    }

    public boolean isOverflow()
    {
        boolean flag;
        if(cmd == 6)
            flag = true;
        else
            flag = false;
        return flag;
    }

    static final byte CMD_CURRENT_TIME = 5;
    static final byte CMD_NULL = -1;
    static final byte CMD_OVERFLOW = 6;
    static final byte CMD_RESET = 7;
    static final byte CMD_SHUTDOWN = 8;
    static final byte CMD_START = 4;
    static final byte CMD_UPDATE = 0;
    byte batteryHealth;
    byte batteryLevel;
    byte batteryPlugType;
    byte batteryStatus;
    short batteryTemperature;
    char batteryVoltage;
    boolean charging;
    byte cmd;
    boolean cpuRunning;
    boolean gpsOn;
    int phoneSignalStrength;
    boolean screenOn;
    long time;
    boolean wakelockOn;
    boolean wifiOn;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            Parcelable, Parcel

public class BatteryProperties
    implements Parcelable
{

    public BatteryProperties()
    {
    }

    private BatteryProperties(Parcel parcel)
    {
        boolean flag = true;
        super();
        boolean flag1;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        chargerAcOnline = flag1;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        chargerUsbOnline = flag1;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        chargerWirelessOnline = flag1;
        maxChargingCurrent = parcel.readInt();
        maxChargingVoltage = parcel.readInt();
        batteryStatus = parcel.readInt();
        batteryHealth = parcel.readInt();
        if(parcel.readInt() == 1)
            flag1 = flag;
        else
            flag1 = false;
        batteryPresent = flag1;
        batteryLevel = parcel.readInt();
        batteryVoltage = parcel.readInt();
        batteryTemperature = parcel.readInt();
        batteryFullCharge = parcel.readInt();
        batteryChargeCounter = parcel.readInt();
        batteryTechnology = parcel.readString();
    }

    BatteryProperties(Parcel parcel, BatteryProperties batteryproperties)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public void set(BatteryProperties batteryproperties)
    {
        chargerAcOnline = batteryproperties.chargerAcOnline;
        chargerUsbOnline = batteryproperties.chargerUsbOnline;
        chargerWirelessOnline = batteryproperties.chargerWirelessOnline;
        maxChargingCurrent = batteryproperties.maxChargingCurrent;
        maxChargingVoltage = batteryproperties.maxChargingVoltage;
        batteryStatus = batteryproperties.batteryStatus;
        batteryHealth = batteryproperties.batteryHealth;
        batteryPresent = batteryproperties.batteryPresent;
        batteryLevel = batteryproperties.batteryLevel;
        batteryVoltage = batteryproperties.batteryVoltage;
        batteryTemperature = batteryproperties.batteryTemperature;
        batteryFullCharge = batteryproperties.batteryFullCharge;
        batteryChargeCounter = batteryproperties.batteryChargeCounter;
        batteryTechnology = batteryproperties.batteryTechnology;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        if(chargerAcOnline)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(chargerUsbOnline)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(chargerWirelessOnline)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(maxChargingCurrent);
        parcel.writeInt(maxChargingVoltage);
        parcel.writeInt(batteryStatus);
        parcel.writeInt(batteryHealth);
        if(batteryPresent)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(batteryLevel);
        parcel.writeInt(batteryVoltage);
        parcel.writeInt(batteryTemperature);
        parcel.writeInt(batteryFullCharge);
        parcel.writeInt(batteryChargeCounter);
        parcel.writeString(batteryTechnology);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public BatteryProperties createFromParcel(Parcel parcel)
        {
            return new BatteryProperties(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BatteryProperties[] newArray(int i)
        {
            return new BatteryProperties[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public int batteryChargeCounter;
    public int batteryFullCharge;
    public int batteryHealth;
    public int batteryLevel;
    public boolean batteryPresent;
    public int batteryStatus;
    public String batteryTechnology;
    public int batteryTemperature;
    public int batteryVoltage;
    public boolean chargerAcOnline;
    public boolean chargerUsbOnline;
    public boolean chargerWirelessOnline;
    public int maxChargingCurrent;
    public int maxChargingVoltage;

}

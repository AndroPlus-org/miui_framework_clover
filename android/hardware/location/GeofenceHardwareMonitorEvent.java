// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

public class GeofenceHardwareMonitorEvent
    implements Parcelable
{

    public GeofenceHardwareMonitorEvent(int i, int j, int k, Location location)
    {
        mMonitoringType = i;
        mMonitoringStatus = j;
        mSourceTechnologies = k;
        mLocation = location;
    }

    public int describeContents()
    {
        return 0;
    }

    public Location getLocation()
    {
        return mLocation;
    }

    public int getMonitoringStatus()
    {
        return mMonitoringStatus;
    }

    public int getMonitoringType()
    {
        return mMonitoringType;
    }

    public int getSourceTechnologies()
    {
        return mSourceTechnologies;
    }

    public String toString()
    {
        return String.format("GeofenceHardwareMonitorEvent: type=%d, status=%d, sources=%d, location=%s", new Object[] {
            Integer.valueOf(mMonitoringType), Integer.valueOf(mMonitoringStatus), Integer.valueOf(mSourceTechnologies), mLocation
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mMonitoringType);
        parcel.writeInt(mMonitoringStatus);
        parcel.writeInt(mSourceTechnologies);
        parcel.writeParcelable(mLocation, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public GeofenceHardwareMonitorEvent createFromParcel(Parcel parcel)
        {
            ClassLoader classloader = android/hardware/location/GeofenceHardwareMonitorEvent.getClassLoader();
            return new GeofenceHardwareMonitorEvent(parcel.readInt(), parcel.readInt(), parcel.readInt(), (Location)parcel.readParcelable(classloader));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public GeofenceHardwareMonitorEvent[] newArray(int i)
        {
            return new GeofenceHardwareMonitorEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final Location mLocation;
    private final int mMonitoringStatus;
    private final int mMonitoringType;
    private final int mSourceTechnologies;

}

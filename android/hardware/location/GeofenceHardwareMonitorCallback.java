// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.location.Location;

// Referenced classes of package android.hardware.location:
//            GeofenceHardwareMonitorEvent

public abstract class GeofenceHardwareMonitorCallback
{

    public GeofenceHardwareMonitorCallback()
    {
    }

    public void onMonitoringSystemChange(int i, boolean flag, Location location)
    {
    }

    public void onMonitoringSystemChange(GeofenceHardwareMonitorEvent geofencehardwaremonitorevent)
    {
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

// Referenced classes of package android.hardware.location:
//            GeofenceHardwareRequest

public final class GeofenceHardwareRequestParcelable
    implements Parcelable
{

    public GeofenceHardwareRequestParcelable(int i, GeofenceHardwareRequest geofencehardwarerequest)
    {
        mId = i;
        mRequest = geofencehardwarerequest;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getId()
    {
        return mId;
    }

    public int getLastTransition()
    {
        return mRequest.getLastTransition();
    }

    public double getLatitude()
    {
        return mRequest.getLatitude();
    }

    public double getLongitude()
    {
        return mRequest.getLongitude();
    }

    public int getMonitorTransitions()
    {
        return mRequest.getMonitorTransitions();
    }

    public int getNotificationResponsiveness()
    {
        return mRequest.getNotificationResponsiveness();
    }

    public double getRadius()
    {
        return mRequest.getRadius();
    }

    int getSourceTechnologies()
    {
        return mRequest.getSourceTechnologies();
    }

    int getType()
    {
        return mRequest.getType();
    }

    public int getUnknownTimer()
    {
        return mRequest.getUnknownTimer();
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("id=");
        stringbuilder.append(mId);
        stringbuilder.append(", type=");
        stringbuilder.append(mRequest.getType());
        stringbuilder.append(", latitude=");
        stringbuilder.append(mRequest.getLatitude());
        stringbuilder.append(", longitude=");
        stringbuilder.append(mRequest.getLongitude());
        stringbuilder.append(", radius=");
        stringbuilder.append(mRequest.getRadius());
        stringbuilder.append(", lastTransition=");
        stringbuilder.append(mRequest.getLastTransition());
        stringbuilder.append(", unknownTimer=");
        stringbuilder.append(mRequest.getUnknownTimer());
        stringbuilder.append(", monitorTransitions=");
        stringbuilder.append(mRequest.getMonitorTransitions());
        stringbuilder.append(", notificationResponsiveness=");
        stringbuilder.append(mRequest.getNotificationResponsiveness());
        stringbuilder.append(", sourceTechnologies=");
        stringbuilder.append(mRequest.getSourceTechnologies());
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(getType());
        parcel.writeDouble(getLatitude());
        parcel.writeDouble(getLongitude());
        parcel.writeDouble(getRadius());
        parcel.writeInt(getLastTransition());
        parcel.writeInt(getMonitorTransitions());
        parcel.writeInt(getUnknownTimer());
        parcel.writeInt(getNotificationResponsiveness());
        parcel.writeInt(getSourceTechnologies());
        parcel.writeInt(getId());
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public GeofenceHardwareRequestParcelable createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            if(i != 0)
            {
                Log.e("GeofenceHardwareRequest", String.format("Invalid Geofence type: %d", new Object[] {
                    Integer.valueOf(i)
                }));
                return null;
            } else
            {
                GeofenceHardwareRequest geofencehardwarerequest = GeofenceHardwareRequest.createCircularGeofence(parcel.readDouble(), parcel.readDouble(), parcel.readDouble());
                geofencehardwarerequest.setLastTransition(parcel.readInt());
                geofencehardwarerequest.setMonitorTransitions(parcel.readInt());
                geofencehardwarerequest.setUnknownTimer(parcel.readInt());
                geofencehardwarerequest.setNotificationResponsiveness(parcel.readInt());
                geofencehardwarerequest.setSourceTechnologies(parcel.readInt());
                return new GeofenceHardwareRequestParcelable(parcel.readInt(), geofencehardwarerequest);
            }
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public GeofenceHardwareRequestParcelable[] newArray(int i)
        {
            return new GeofenceHardwareRequestParcelable[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private int mId;
    private GeofenceHardwareRequest mRequest;

}

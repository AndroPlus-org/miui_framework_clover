// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.Parcel;
import android.os.Parcelable;
import java.security.InvalidParameterException;
import java.util.*;

// Referenced classes of package android.location:
//            GpsClock, GpsMeasurement

public class GpsMeasurementsEvent
    implements Parcelable
{
    public static interface Listener
    {

        public abstract void onGpsMeasurementsReceived(GpsMeasurementsEvent gpsmeasurementsevent);

        public abstract void onStatusChanged(int i);
    }


    public GpsMeasurementsEvent(GpsClock gpsclock, GpsMeasurement agpsmeasurement[])
    {
        if(gpsclock == null)
            throw new InvalidParameterException("Parameter 'clock' must not be null.");
        if(agpsmeasurement == null || agpsmeasurement.length == 0)
        {
            throw new InvalidParameterException("Parameter 'measurements' must not be null or empty.");
        } else
        {
            mClock = gpsclock;
            mReadOnlyMeasurements = Collections.unmodifiableCollection(Arrays.asList(agpsmeasurement));
            return;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public GpsClock getClock()
    {
        return mClock;
    }

    public Collection getMeasurements()
    {
        return mReadOnlyMeasurements;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("[ GpsMeasurementsEvent:\n\n");
        stringbuilder.append(mClock.toString());
        stringbuilder.append("\n");
        for(Iterator iterator = mReadOnlyMeasurements.iterator(); iterator.hasNext(); stringbuilder.append("\n"))
            stringbuilder.append(((GpsMeasurement)iterator.next()).toString());

        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mClock, i);
        int j = mReadOnlyMeasurements.size();
        GpsMeasurement agpsmeasurement[] = (GpsMeasurement[])mReadOnlyMeasurements.toArray(new GpsMeasurement[j]);
        parcel.writeInt(agpsmeasurement.length);
        parcel.writeTypedArray(agpsmeasurement, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public GpsMeasurementsEvent createFromParcel(Parcel parcel)
        {
            GpsClock gpsclock = (GpsClock)parcel.readParcelable(getClass().getClassLoader());
            GpsMeasurement agpsmeasurement[] = new GpsMeasurement[parcel.readInt()];
            parcel.readTypedArray(agpsmeasurement, GpsMeasurement.CREATOR);
            return new GpsMeasurementsEvent(gpsclock, agpsmeasurement);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public GpsMeasurementsEvent[] newArray(int i)
        {
            return new GpsMeasurementsEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int STATUS_GPS_LOCATION_DISABLED = 2;
    public static final int STATUS_NOT_SUPPORTED = 0;
    public static final int STATUS_READY = 1;
    private final GpsClock mClock;
    private final Collection mReadOnlyMeasurements;

}

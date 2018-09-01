// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.Parcel;
import android.os.Parcelable;
import java.security.InvalidParameterException;
import java.util.*;

// Referenced classes of package android.location:
//            GnssClock, GnssMeasurement

public final class GnssMeasurementsEvent
    implements Parcelable
{
    public static abstract class Callback
    {

        public void onGnssMeasurementsReceived(GnssMeasurementsEvent gnssmeasurementsevent)
        {
        }

        public void onStatusChanged(int i)
        {
        }

        public static final int STATUS_LOCATION_DISABLED = 2;
        public static final int STATUS_NOT_SUPPORTED = 0;
        public static final int STATUS_READY = 1;

        public Callback()
        {
        }
    }


    public GnssMeasurementsEvent(GnssClock gnssclock, GnssMeasurement agnssmeasurement[])
    {
        if(gnssclock == null)
            throw new InvalidParameterException("Parameter 'clock' must not be null.");
        if(agnssmeasurement == null || agnssmeasurement.length == 0)
            mReadOnlyMeasurements = Collections.emptyList();
        else
            mReadOnlyMeasurements = Collections.unmodifiableCollection(Arrays.asList(agnssmeasurement));
        mClock = gnssclock;
    }

    public int describeContents()
    {
        return 0;
    }

    public GnssClock getClock()
    {
        return mClock;
    }

    public Collection getMeasurements()
    {
        return mReadOnlyMeasurements;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("[ GnssMeasurementsEvent:\n\n");
        stringbuilder.append(mClock.toString());
        stringbuilder.append("\n");
        for(Iterator iterator = mReadOnlyMeasurements.iterator(); iterator.hasNext(); stringbuilder.append("\n"))
            stringbuilder.append(((GnssMeasurement)iterator.next()).toString());

        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mClock, i);
        int j = mReadOnlyMeasurements.size();
        GnssMeasurement agnssmeasurement[] = (GnssMeasurement[])mReadOnlyMeasurements.toArray(new GnssMeasurement[j]);
        parcel.writeInt(agnssmeasurement.length);
        parcel.writeTypedArray(agnssmeasurement, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public GnssMeasurementsEvent createFromParcel(Parcel parcel)
        {
            GnssClock gnssclock = (GnssClock)parcel.readParcelable(getClass().getClassLoader());
            GnssMeasurement agnssmeasurement[] = new GnssMeasurement[parcel.readInt()];
            parcel.readTypedArray(agnssmeasurement, GnssMeasurement.CREATOR);
            return new GnssMeasurementsEvent(gnssclock, agnssmeasurement);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public GnssMeasurementsEvent[] newArray(int i)
        {
            return new GnssMeasurementsEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final GnssClock mClock;
    private final Collection mReadOnlyMeasurements;

}

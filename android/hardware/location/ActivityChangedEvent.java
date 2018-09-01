// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.os.Parcel;
import android.os.Parcelable;
import java.security.InvalidParameterException;
import java.util.*;

// Referenced classes of package android.hardware.location:
//            ActivityRecognitionEvent

public class ActivityChangedEvent
    implements Parcelable
{

    public ActivityChangedEvent(ActivityRecognitionEvent aactivityrecognitionevent[])
    {
        if(aactivityrecognitionevent == null)
        {
            throw new InvalidParameterException("Parameter 'activityRecognitionEvents' must not be null.");
        } else
        {
            mActivityRecognitionEvents = Arrays.asList(aactivityrecognitionevent);
            return;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public Iterable getActivityRecognitionEvents()
    {
        return mActivityRecognitionEvents;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("[ ActivityChangedEvent:");
        ActivityRecognitionEvent activityrecognitionevent;
        for(Iterator iterator = mActivityRecognitionEvents.iterator(); iterator.hasNext(); stringbuilder.append(activityrecognitionevent.toString()))
        {
            activityrecognitionevent = (ActivityRecognitionEvent)iterator.next();
            stringbuilder.append("\n    ");
        }

        stringbuilder.append("\n]");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        ActivityRecognitionEvent aactivityrecognitionevent[] = (ActivityRecognitionEvent[])mActivityRecognitionEvents.toArray(new ActivityRecognitionEvent[0]);
        parcel.writeInt(aactivityrecognitionevent.length);
        parcel.writeTypedArray(aactivityrecognitionevent, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ActivityChangedEvent createFromParcel(Parcel parcel)
        {
            ActivityRecognitionEvent aactivityrecognitionevent[] = new ActivityRecognitionEvent[parcel.readInt()];
            parcel.readTypedArray(aactivityrecognitionevent, ActivityRecognitionEvent.CREATOR);
            return new ActivityChangedEvent(aactivityrecognitionevent);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ActivityChangedEvent[] newArray(int i)
        {
            return new ActivityChangedEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final List mActivityRecognitionEvents;

}

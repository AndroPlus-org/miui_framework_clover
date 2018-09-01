// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.os.Parcel;
import android.os.Parcelable;

public class ActivityRecognitionEvent
    implements Parcelable
{

    public ActivityRecognitionEvent(String s, int i, long l)
    {
        mActivity = s;
        mEventType = i;
        mTimestampNs = l;
    }

    public int describeContents()
    {
        return 0;
    }

    public String getActivity()
    {
        return mActivity;
    }

    public int getEventType()
    {
        return mEventType;
    }

    public long getTimestampNs()
    {
        return mTimestampNs;
    }

    public String toString()
    {
        return String.format("Activity='%s', EventType=%s, TimestampNs=%s", new Object[] {
            mActivity, Integer.valueOf(mEventType), Long.valueOf(mTimestampNs)
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mActivity);
        parcel.writeInt(mEventType);
        parcel.writeLong(mTimestampNs);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ActivityRecognitionEvent createFromParcel(Parcel parcel)
        {
            return new ActivityRecognitionEvent(parcel.readString(), parcel.readInt(), parcel.readLong());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ActivityRecognitionEvent[] newArray(int i)
        {
            return new ActivityRecognitionEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final String mActivity;
    private final int mEventType;
    private final long mTimestampNs;

}

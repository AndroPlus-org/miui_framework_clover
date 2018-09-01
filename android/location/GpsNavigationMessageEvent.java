// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.Parcel;
import android.os.Parcelable;
import java.security.InvalidParameterException;

// Referenced classes of package android.location:
//            GpsNavigationMessage

public class GpsNavigationMessageEvent
    implements Parcelable
{
    public static interface Listener
    {

        public abstract void onGpsNavigationMessageReceived(GpsNavigationMessageEvent gpsnavigationmessageevent);

        public abstract void onStatusChanged(int i);
    }


    public GpsNavigationMessageEvent(GpsNavigationMessage gpsnavigationmessage)
    {
        if(gpsnavigationmessage == null)
        {
            throw new InvalidParameterException("Parameter 'message' must not be null.");
        } else
        {
            mNavigationMessage = gpsnavigationmessage;
            return;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public GpsNavigationMessage getNavigationMessage()
    {
        return mNavigationMessage;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("[ GpsNavigationMessageEvent:\n\n");
        stringbuilder.append(mNavigationMessage.toString());
        stringbuilder.append("\n]");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mNavigationMessage, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public GpsNavigationMessageEvent createFromParcel(Parcel parcel)
        {
            return new GpsNavigationMessageEvent((GpsNavigationMessage)parcel.readParcelable(getClass().getClassLoader()));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public GpsNavigationMessageEvent[] newArray(int i)
        {
            return new GpsNavigationMessageEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static int STATUS_GPS_LOCATION_DISABLED = 2;
    public static int STATUS_NOT_SUPPORTED = 0;
    public static int STATUS_READY = 1;
    private final GpsNavigationMessage mNavigationMessage;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.*;

// Referenced classes of package android.telephony.mbms:
//            ServiceInfo

public final class StreamingServiceInfo extends ServiceInfo
    implements Parcelable
{

    private StreamingServiceInfo(Parcel parcel)
    {
        super(parcel);
    }

    StreamingServiceInfo(Parcel parcel, StreamingServiceInfo streamingserviceinfo)
    {
        this(parcel);
    }

    public StreamingServiceInfo(Map map, String s, List list, String s1, Date date, Date date1)
    {
        super(map, s, list, s1, date, date1);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public StreamingServiceInfo createFromParcel(Parcel parcel)
        {
            return new StreamingServiceInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public StreamingServiceInfo[] newArray(int i)
        {
            return new StreamingServiceInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;

}

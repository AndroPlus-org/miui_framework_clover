// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.admin;

import android.os.*;

// Referenced classes of package android.app.admin:
//            DnsEvent, ConnectEvent

public abstract class NetworkEvent
    implements Parcelable
{

    NetworkEvent()
    {
    }

    NetworkEvent(String s, long l)
    {
        packageName = s;
        timestamp = l;
    }

    public int describeContents()
    {
        return 0;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public abstract void writeToParcel(Parcel parcel, int i);

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NetworkEvent createFromParcel(Parcel parcel)
        {
            int i = parcel.dataPosition();
            int j = parcel.readInt();
            parcel.setDataPosition(i);
            switch(j)
            {
            default:
                throw new ParcelFormatException((new StringBuilder()).append("Unexpected NetworkEvent token in parcel: ").append(j).toString());

            case 1: // '\001'
                return (NetworkEvent)DnsEvent.CREATOR.createFromParcel(parcel);

            case 2: // '\002'
                return (NetworkEvent)ConnectEvent.CREATOR.createFromParcel(parcel);
            }
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NetworkEvent[] newArray(int i)
        {
            return new NetworkEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    static final int PARCEL_TOKEN_CONNECT_EVENT = 2;
    static final int PARCEL_TOKEN_DNS_EVENT = 1;
    String packageName;
    long timestamp;

}

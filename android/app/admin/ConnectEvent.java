// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.admin;

import android.os.Parcel;
import android.os.Parcelable;
import java.net.InetAddress;
import java.net.UnknownHostException;

// Referenced classes of package android.app.admin:
//            NetworkEvent

public final class ConnectEvent extends NetworkEvent
    implements Parcelable
{

    private ConnectEvent(Parcel parcel)
    {
        ipAddress = parcel.readString();
        port = parcel.readInt();
        packageName = parcel.readString();
        timestamp = parcel.readLong();
    }

    ConnectEvent(Parcel parcel, ConnectEvent connectevent)
    {
        this(parcel);
    }

    public ConnectEvent(String s, int i, String s1, long l)
    {
        super(s1, l);
        ipAddress = s;
        port = i;
    }

    public int describeContents()
    {
        return 0;
    }

    public InetAddress getInetAddress()
    {
        InetAddress inetaddress;
        try
        {
            inetaddress = InetAddress.getByName(ipAddress);
        }
        catch(UnknownHostException unknownhostexception)
        {
            return InetAddress.getLoopbackAddress();
        }
        return inetaddress;
    }

    public int getPort()
    {
        return port;
    }

    public String toString()
    {
        return String.format("ConnectEvent(%s, %d, %d, %s)", new Object[] {
            ipAddress, Integer.valueOf(port), Long.valueOf(timestamp), packageName
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(2);
        parcel.writeString(ipAddress);
        parcel.writeInt(port);
        parcel.writeString(packageName);
        parcel.writeLong(timestamp);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ConnectEvent createFromParcel(Parcel parcel)
        {
            if(parcel.readInt() != 2)
                return null;
            else
                return new ConnectEvent(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ConnectEvent[] newArray(int i)
        {
            return new ConnectEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final String ipAddress;
    private final int port;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.net.InetAddress;
import java.net.UnknownHostException;

// Referenced classes of package android.net:
//            Network, IpSecAlgorithm

public final class IpSecConfig
    implements Parcelable
{
    public static class Flow
    {

        IpSecAlgorithm authentication;
        IpSecAlgorithm encryption;
        int spiResourceId;

        public Flow()
        {
        }
    }


    IpSecConfig()
    {
    }

    private IpSecConfig(Parcel parcel)
    {
        localAddress = readInetAddressFromParcel(parcel);
        remoteAddress = readInetAddressFromParcel(parcel);
        network = (Network)parcel.readParcelable(android/net/Network.getClassLoader());
        flow[0].spiResourceId = parcel.readInt();
        flow[0].encryption = (IpSecAlgorithm)parcel.readParcelable(android/net/IpSecAlgorithm.getClassLoader());
        flow[0].authentication = (IpSecAlgorithm)parcel.readParcelable(android/net/IpSecAlgorithm.getClassLoader());
        flow[1].spiResourceId = parcel.readInt();
        flow[1].encryption = (IpSecAlgorithm)parcel.readParcelable(android/net/IpSecAlgorithm.getClassLoader());
        flow[1].authentication = (IpSecAlgorithm)parcel.readParcelable(android/net/IpSecAlgorithm.getClassLoader());
        encapType = parcel.readInt();
        encapLocalPortResourceId = parcel.readInt();
        encapRemotePort = parcel.readInt();
    }

    IpSecConfig(Parcel parcel, IpSecConfig ipsecconfig)
    {
        this(parcel);
    }

    private static InetAddress readInetAddressFromParcel(Parcel parcel)
    {
        parcel = parcel.readString();
        if(parcel == null)
            return null;
        InetAddress inetaddress;
        try
        {
            inetaddress = InetAddress.getByName(parcel);
        }
        catch(UnknownHostException unknownhostexception)
        {
            Log.wtf("IpSecConfig", (new StringBuilder()).append("Invalid IpAddress ").append(parcel).toString());
            return null;
        }
        return inetaddress;
    }

    public int describeContents()
    {
        return 0;
    }

    public IpSecAlgorithm getAuthentication(int i)
    {
        return flow[i].authentication;
    }

    public int getEncapLocalResourceId()
    {
        return encapLocalPortResourceId;
    }

    public int getEncapRemotePort()
    {
        return encapRemotePort;
    }

    public int getEncapType()
    {
        return encapType;
    }

    public IpSecAlgorithm getEncryption(int i)
    {
        return flow[i].encryption;
    }

    public InetAddress getLocalAddress()
    {
        return localAddress;
    }

    public int getMode()
    {
        return mode;
    }

    public int getNattKeepaliveInterval()
    {
        return nattKeepaliveInterval;
    }

    public Network getNetwork()
    {
        return network;
    }

    public InetAddress getRemoteAddress()
    {
        return remoteAddress;
    }

    public int getSpiResourceId(int i)
    {
        return flow[i].spiResourceId;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        Object obj = null;
        String s;
        if(localAddress != null)
            s = localAddress.getHostAddress();
        else
            s = null;
        parcel.writeString(s);
        s = obj;
        if(remoteAddress != null)
            s = remoteAddress.getHostAddress();
        parcel.writeString(s);
        parcel.writeParcelable(network, i);
        parcel.writeInt(flow[0].spiResourceId);
        parcel.writeParcelable(flow[0].encryption, i);
        parcel.writeParcelable(flow[0].authentication, i);
        parcel.writeInt(flow[1].spiResourceId);
        parcel.writeParcelable(flow[1].encryption, i);
        parcel.writeParcelable(flow[1].authentication, i);
        parcel.writeInt(encapType);
        parcel.writeInt(encapLocalPortResourceId);
        parcel.writeInt(encapRemotePort);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public IpSecConfig createFromParcel(Parcel parcel)
        {
            return new IpSecConfig(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public IpSecConfig[] newArray(int i)
        {
            return new IpSecConfig[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "IpSecConfig";
    int encapLocalPortResourceId;
    int encapRemotePort;
    int encapType;
    Flow flow[] = {
        new Flow(), new Flow()
    };
    InetAddress localAddress;
    int mode;
    int nattKeepaliveInterval;
    Network network;
    InetAddress remoteAddress;

}

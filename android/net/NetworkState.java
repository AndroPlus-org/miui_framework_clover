// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.net:
//            NetworkInfo, LinkProperties, NetworkCapabilities, Network

public class NetworkState
    implements Parcelable
{

    public NetworkState(NetworkInfo networkinfo, LinkProperties linkproperties, NetworkCapabilities networkcapabilities, Network network1, String s, String s1)
    {
        networkInfo = networkinfo;
        linkProperties = linkproperties;
        networkCapabilities = networkcapabilities;
        network = network1;
        subscriberId = s;
        networkId = s1;
    }

    public NetworkState(Parcel parcel)
    {
        networkInfo = (NetworkInfo)parcel.readParcelable(null);
        linkProperties = (LinkProperties)parcel.readParcelable(null);
        networkCapabilities = (NetworkCapabilities)parcel.readParcelable(null);
        network = (Network)parcel.readParcelable(null);
        subscriberId = parcel.readString();
        networkId = parcel.readString();
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(networkInfo, i);
        parcel.writeParcelable(linkProperties, i);
        parcel.writeParcelable(networkCapabilities, i);
        parcel.writeParcelable(network, i);
        parcel.writeString(subscriberId);
        parcel.writeString(networkId);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NetworkState createFromParcel(Parcel parcel)
        {
            return new NetworkState(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NetworkState[] newArray(int i)
        {
            return new NetworkState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final NetworkState EMPTY = new NetworkState(null, null, null, null, null, null);
    public final LinkProperties linkProperties;
    public final Network network;
    public final NetworkCapabilities networkCapabilities;
    public final String networkId;
    public final NetworkInfo networkInfo;
    public final String subscriberId;

}

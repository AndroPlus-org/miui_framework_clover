// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.aware;

import android.net.NetworkSpecifier;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Objects;

// Referenced classes of package android.net.wifi.aware:
//            WifiAwareAgentNetworkSpecifier

public final class WifiAwareNetworkSpecifier extends NetworkSpecifier
    implements Parcelable
{

    public WifiAwareNetworkSpecifier(int i, int j, int k, int l, int i1, byte abyte0[], byte abyte1[], 
            String s, int j1)
    {
        type = i;
        role = j;
        clientId = k;
        sessionId = l;
        peerId = i1;
        peerMac = abyte0;
        pmk = abyte1;
        passphrase = s;
        requestorUid = j1;
    }

    public void assertValidFromUid(int i)
    {
        if(requestorUid != i)
            throw new SecurityException("mismatched UIDs");
        else
            return;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(!(obj instanceof WifiAwareNetworkSpecifier))
            return false;
        obj = (WifiAwareNetworkSpecifier)obj;
        if(type == ((WifiAwareNetworkSpecifier) (obj)).type && role == ((WifiAwareNetworkSpecifier) (obj)).role && clientId == ((WifiAwareNetworkSpecifier) (obj)).clientId && sessionId == ((WifiAwareNetworkSpecifier) (obj)).sessionId && peerId == ((WifiAwareNetworkSpecifier) (obj)).peerId && Arrays.equals(peerMac, ((WifiAwareNetworkSpecifier) (obj)).peerMac) && Arrays.equals(pmk, ((WifiAwareNetworkSpecifier) (obj)).pmk) && Objects.equals(passphrase, ((WifiAwareNetworkSpecifier) (obj)).passphrase))
        {
            if(requestorUid != ((WifiAwareNetworkSpecifier) (obj)).requestorUid)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public int hashCode()
    {
        return ((((((((type + 527) * 31 + role) * 31 + clientId) * 31 + sessionId) * 31 + peerId) * 31 + Arrays.hashCode(peerMac)) * 31 + Arrays.hashCode(pmk)) * 31 + Objects.hashCode(passphrase)) * 31 + requestorUid;
    }

    public boolean isOutOfBand()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(type != 2)
            if(type == 3)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public boolean satisfiedBy(NetworkSpecifier networkspecifier)
    {
        if(networkspecifier instanceof WifiAwareAgentNetworkSpecifier)
            return ((WifiAwareAgentNetworkSpecifier)networkspecifier).satisfiesAwareNetworkSpecifier(this);
        else
            return equals(networkspecifier);
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("WifiAwareNetworkSpecifier [");
        StringBuilder stringbuilder1 = stringbuilder.append("type=").append(type).append(", role=").append(role).append(", clientId=").append(clientId).append(", sessionId=").append(sessionId).append(", peerId=").append(peerId).append(", peerMac=");
        String s;
        if(peerMac == null)
            s = "<null>";
        else
            s = "<non-null>";
        stringbuilder1 = stringbuilder1.append(s).append(", pmk=");
        if(pmk == null)
            s = "<null>";
        else
            s = "<non-null>";
        stringbuilder1 = stringbuilder1.append(s).append(", passphrase=");
        if(passphrase == null)
            s = "<null>";
        else
            s = "<non-null>";
        stringbuilder1.append(s).append(", requestorUid=").append(requestorUid).append("]");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(type);
        parcel.writeInt(role);
        parcel.writeInt(clientId);
        parcel.writeInt(sessionId);
        parcel.writeInt(peerId);
        parcel.writeByteArray(peerMac);
        parcel.writeByteArray(pmk);
        parcel.writeString(passphrase);
        parcel.writeInt(requestorUid);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiAwareNetworkSpecifier createFromParcel(Parcel parcel)
        {
            return new WifiAwareNetworkSpecifier(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createByteArray(), parcel.createByteArray(), parcel.readString(), parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiAwareNetworkSpecifier[] newArray(int i)
        {
            return new WifiAwareNetworkSpecifier[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int NETWORK_SPECIFIER_TYPE_IB = 0;
    public static final int NETWORK_SPECIFIER_TYPE_IB_ANY_PEER = 1;
    public static final int NETWORK_SPECIFIER_TYPE_MAX_VALID = 3;
    public static final int NETWORK_SPECIFIER_TYPE_OOB = 2;
    public static final int NETWORK_SPECIFIER_TYPE_OOB_ANY_PEER = 3;
    public final int clientId;
    public final String passphrase;
    public final int peerId;
    public final byte peerMac[];
    public final byte pmk[];
    public final int requestorUid;
    public final int role;
    public final int sessionId;
    public final int type;

}

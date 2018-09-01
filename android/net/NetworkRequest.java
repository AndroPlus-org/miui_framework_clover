// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.Objects;

// Referenced classes of package android.net:
//            NetworkCapabilities, MatchAllNetworkSpecifier, StringNetworkSpecifier, NetworkSpecifier

public class NetworkRequest
    implements Parcelable
{
    public static class Builder
    {

        public Builder addCapability(int i)
        {
            mNetworkCapabilities.addCapability(i);
            return this;
        }

        public Builder addTransportType(int i)
        {
            mNetworkCapabilities.addTransportType(i);
            return this;
        }

        public NetworkRequest build()
        {
            NetworkCapabilities networkcapabilities = new NetworkCapabilities(mNetworkCapabilities);
            networkcapabilities.maybeMarkCapabilitiesRestricted();
            return new NetworkRequest(networkcapabilities, -1, 0, Type.NONE);
        }

        public Builder clearCapabilities()
        {
            mNetworkCapabilities.clearAll();
            return this;
        }

        public Builder removeCapability(int i)
        {
            mNetworkCapabilities.removeCapability(i);
            return this;
        }

        public Builder removeTransportType(int i)
        {
            mNetworkCapabilities.removeTransportType(i);
            return this;
        }

        public Builder setCapabilities(NetworkCapabilities networkcapabilities)
        {
            mNetworkCapabilities.clearAll();
            mNetworkCapabilities.combineCapabilities(networkcapabilities);
            return this;
        }

        public Builder setLinkDownstreamBandwidthKbps(int i)
        {
            mNetworkCapabilities.setLinkDownstreamBandwidthKbps(i);
            return this;
        }

        public Builder setLinkUpstreamBandwidthKbps(int i)
        {
            mNetworkCapabilities.setLinkUpstreamBandwidthKbps(i);
            return this;
        }

        public Builder setNetworkSpecifier(NetworkSpecifier networkspecifier)
        {
            MatchAllNetworkSpecifier.checkNotMatchAllNetworkSpecifier(networkspecifier);
            mNetworkCapabilities.setNetworkSpecifier(networkspecifier);
            return this;
        }

        public Builder setNetworkSpecifier(String s)
        {
            if(TextUtils.isEmpty(s))
                s = null;
            else
                s = new StringNetworkSpecifier(s);
            return setNetworkSpecifier(((NetworkSpecifier) (s)));
        }

        public Builder setSignalStrength(int i)
        {
            mNetworkCapabilities.setSignalStrength(i);
            return this;
        }

        private final NetworkCapabilities mNetworkCapabilities = new NetworkCapabilities();

        public Builder()
        {
        }
    }

    public static final class Type extends Enum
    {

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(android/net/NetworkRequest$Type, s);
        }

        public static Type[] values()
        {
            return $VALUES;
        }

        private static final Type $VALUES[];
        public static final Type BACKGROUND_REQUEST;
        public static final Type LISTEN;
        public static final Type NONE;
        public static final Type REQUEST;
        public static final Type TRACK_DEFAULT;

        static 
        {
            NONE = new Type("NONE", 0);
            LISTEN = new Type("LISTEN", 1);
            TRACK_DEFAULT = new Type("TRACK_DEFAULT", 2);
            REQUEST = new Type("REQUEST", 3);
            BACKGROUND_REQUEST = new Type("BACKGROUND_REQUEST", 4);
            $VALUES = (new Type[] {
                NONE, LISTEN, TRACK_DEFAULT, REQUEST, BACKGROUND_REQUEST
            });
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }


    public NetworkRequest(NetworkCapabilities networkcapabilities, int i, int j, Type type1)
    {
        if(networkcapabilities == null)
        {
            throw new NullPointerException();
        } else
        {
            requestId = j;
            networkCapabilities = networkcapabilities;
            legacyType = i;
            type = type1;
            return;
        }
    }

    public NetworkRequest(NetworkRequest networkrequest)
    {
        networkCapabilities = new NetworkCapabilities(networkrequest.networkCapabilities);
        requestId = networkrequest.requestId;
        legacyType = networkrequest.legacyType;
        type = networkrequest.type;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(!(obj instanceof NetworkRequest))
            return false;
        obj = (NetworkRequest)obj;
        boolean flag1 = flag;
        if(((NetworkRequest) (obj)).legacyType == legacyType)
        {
            flag1 = flag;
            if(((NetworkRequest) (obj)).requestId == requestId)
            {
                flag1 = flag;
                if(((NetworkRequest) (obj)).type == type)
                    flag1 = Objects.equals(((NetworkRequest) (obj)).networkCapabilities, networkCapabilities);
            }
        }
        return flag1;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(requestId), Integer.valueOf(legacyType), networkCapabilities, type
        });
    }

    public boolean isBackgroundRequest()
    {
        boolean flag;
        if(type == Type.BACKGROUND_REQUEST)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isForegroundRequest()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(type != Type.TRACK_DEFAULT)
            if(type == Type.REQUEST)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public boolean isListen()
    {
        boolean flag;
        if(type == Type.LISTEN)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isRequest()
    {
        boolean flag;
        if(!isForegroundRequest())
            flag = isBackgroundRequest();
        else
            flag = true;
        return flag;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("NetworkRequest [ ").append(type).append(" id=").append(requestId);
        String s;
        if(legacyType != -1)
            s = (new StringBuilder()).append(", legacyType=").append(legacyType).toString();
        else
            s = "";
        return stringbuilder.append(s).append(", ").append(networkCapabilities.toString()).append(" ]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(networkCapabilities, i);
        parcel.writeInt(legacyType);
        parcel.writeInt(requestId);
        parcel.writeString(type.name());
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NetworkRequest createFromParcel(Parcel parcel)
        {
            return new NetworkRequest((NetworkCapabilities)parcel.readParcelable(null), parcel.readInt(), parcel.readInt(), Type.valueOf(parcel.readString()));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NetworkRequest[] newArray(int i)
        {
            return new NetworkRequest[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final int legacyType;
    public final NetworkCapabilities networkCapabilities;
    public final int requestId;
    public final Type type;

}

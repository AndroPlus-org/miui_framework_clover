// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;
import java.net.*;
import java.util.*;

// Referenced classes of package android.net:
//            IpPrefix, LinkAddress, NetworkUtils

public final class RouteInfo
    implements Parcelable
{

    public RouteInfo(IpPrefix ipprefix)
    {
        this(ipprefix, null, null);
    }

    public RouteInfo(IpPrefix ipprefix, int i)
    {
        this(ipprefix, null, null, i);
    }

    public RouteInfo(IpPrefix ipprefix, InetAddress inetaddress)
    {
        this(ipprefix, inetaddress, null);
    }

    public RouteInfo(IpPrefix ipprefix, InetAddress inetaddress, String s)
    {
        this(ipprefix, inetaddress, s, 1);
    }

    public RouteInfo(IpPrefix ipprefix, InetAddress inetaddress, String s, int i)
    {
        IpPrefix ipprefix1;
label0:
        {
label1:
            {
                super();
                switch(i)
                {
                default:
                    throw new IllegalArgumentException((new StringBuilder()).append("Unknown route type ").append(i).toString());

                case 1: // '\001'
                case 7: // '\007'
                case 9: // '\t'
                    ipprefix1 = ipprefix;
                    break;
                }
                if(ipprefix == null)
                {
                    if(inetaddress == null)
                        break label1;
                    if(inetaddress instanceof Inet4Address)
                        ipprefix1 = new IpPrefix(Inet4Address.ANY, 0);
                    else
                        ipprefix1 = new IpPrefix(Inet6Address.ANY, 0);
                }
                ipprefix = inetaddress;
                if(inetaddress == null)
                    if(ipprefix1.getAddress() instanceof Inet4Address)
                        ipprefix = Inet4Address.ANY;
                    else
                        ipprefix = Inet6Address.ANY;
                mHasGateway = ipprefix.isAnyLocalAddress() ^ true;
                break label0;
            }
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid arguments passed in: ").append(inetaddress).append(",").append(ipprefix).toString());
        }
        if((ipprefix1.getAddress() instanceof Inet4Address) && !(ipprefix instanceof Inet4Address) || (ipprefix1.getAddress() instanceof Inet6Address) && !(ipprefix instanceof Inet6Address))
        {
            throw new IllegalArgumentException("address family mismatch in RouteInfo constructor");
        } else
        {
            mDestination = ipprefix1;
            mGateway = ipprefix;
            mInterface = s;
            mType = i;
            mIsHost = isHost();
            return;
        }
    }

    public RouteInfo(LinkAddress linkaddress)
    {
        this(linkaddress, null, null);
    }

    public RouteInfo(LinkAddress linkaddress, InetAddress inetaddress)
    {
        this(linkaddress, inetaddress, null);
    }

    public RouteInfo(LinkAddress linkaddress, InetAddress inetaddress, String s)
    {
        Object obj = null;
        if(linkaddress == null)
            linkaddress = obj;
        else
            linkaddress = new IpPrefix(linkaddress.getAddress(), linkaddress.getPrefixLength());
        this(((IpPrefix) (linkaddress)), inetaddress, s);
    }

    public RouteInfo(InetAddress inetaddress)
    {
        this((IpPrefix)null, inetaddress, null);
    }

    private boolean isHost()
    {
        boolean flag = true;
        if(!(mDestination.getAddress() instanceof Inet4Address) || mDestination.getPrefixLength() != 32) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(mDestination.getAddress() instanceof Inet6Address)
        {
            if(mDestination.getPrefixLength() != 128)
                flag = false;
        } else
        {
            flag = false;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static RouteInfo makeHostRoute(InetAddress inetaddress, String s)
    {
        return makeHostRoute(inetaddress, null, s);
    }

    public static RouteInfo makeHostRoute(InetAddress inetaddress, InetAddress inetaddress1, String s)
    {
        if(inetaddress == null)
            return null;
        if(inetaddress instanceof Inet4Address)
            return new RouteInfo(new IpPrefix(inetaddress, 32), inetaddress1, s);
        else
            return new RouteInfo(new IpPrefix(inetaddress, 128), inetaddress1, s);
    }

    public static RouteInfo selectBestRoute(Collection collection, InetAddress inetaddress)
    {
        if(collection == null || inetaddress == null)
            return null;
        Object obj = null;
        Iterator iterator = collection.iterator();
        collection = obj;
        do
        {
            if(!iterator.hasNext())
                break;
            RouteInfo routeinfo = (RouteInfo)iterator.next();
            if(NetworkUtils.addressTypeMatches(routeinfo.mDestination.getAddress(), inetaddress) && (collection == null || ((RouteInfo) (collection)).mDestination.getPrefixLength() < routeinfo.mDestination.getPrefixLength()) && routeinfo.matches(inetaddress))
                collection = routeinfo;
        } while(true);
        return collection;
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
        if(!(obj instanceof RouteInfo))
            return false;
        obj = (RouteInfo)obj;
        if(Objects.equals(mDestination, ((RouteInfo) (obj)).getDestination()) && Objects.equals(mGateway, ((RouteInfo) (obj)).getGateway()) && Objects.equals(mInterface, ((RouteInfo) (obj)).getInterface()))
        {
            if(mType != ((RouteInfo) (obj)).getType())
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public IpPrefix getDestination()
    {
        return mDestination;
    }

    public LinkAddress getDestinationLinkAddress()
    {
        return new LinkAddress(mDestination.getAddress(), mDestination.getPrefixLength());
    }

    public InetAddress getGateway()
    {
        return mGateway;
    }

    public String getInterface()
    {
        return mInterface;
    }

    public int getType()
    {
        return mType;
    }

    public boolean hasGateway()
    {
        return mHasGateway;
    }

    public int hashCode()
    {
        int i = 0;
        int j = mDestination.hashCode();
        int k;
        if(mGateway == null)
            k = 0;
        else
            k = mGateway.hashCode() * 47;
        if(mInterface != null)
            i = mInterface.hashCode() * 67;
        return k + j * 41 + i + mType * 71;
    }

    public boolean isDefaultRoute()
    {
        boolean flag = true;
        if(mType != 1 || mDestination.getPrefixLength() != 0)
            flag = false;
        return flag;
    }

    public boolean isHostRoute()
    {
        return mIsHost;
    }

    public boolean isIPv4Default()
    {
        boolean flag;
        if(isDefaultRoute())
            flag = mDestination.getAddress() instanceof Inet4Address;
        else
            flag = false;
        return flag;
    }

    public boolean isIPv6Default()
    {
        boolean flag;
        if(isDefaultRoute())
            flag = mDestination.getAddress() instanceof Inet6Address;
        else
            flag = false;
        return flag;
    }

    public boolean matches(InetAddress inetaddress)
    {
        return mDestination.contains(inetaddress);
    }

    public String toString()
    {
        String s;
        s = "";
        if(mDestination != null)
            s = mDestination.toString();
        if(mType != 7) goto _L2; else goto _L1
_L1:
        s = (new StringBuilder()).append(s).append(" unreachable").toString();
_L4:
        return s;
_L2:
        if(mType == 9)
        {
            s = (new StringBuilder()).append(s).append(" throw").toString();
        } else
        {
            String s1 = (new StringBuilder()).append(s).append(" ->").toString();
            s = s1;
            if(mGateway != null)
                s = (new StringBuilder()).append(s1).append(" ").append(mGateway.getHostAddress()).toString();
            s1 = s;
            if(mInterface != null)
                s1 = (new StringBuilder()).append(s).append(" ").append(mInterface).toString();
            s = s1;
            if(mType != 1)
                s = (new StringBuilder()).append(s1).append(" unknown type ").append(mType).toString();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mDestination, i);
        byte abyte0[];
        if(mGateway == null)
            abyte0 = null;
        else
            abyte0 = mGateway.getAddress();
        parcel.writeByteArray(abyte0);
        parcel.writeString(mInterface);
        parcel.writeInt(mType);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public RouteInfo createFromParcel(Parcel parcel)
        {
            IpPrefix ipprefix;
            InetAddress inetaddress;
            byte abyte0[];
            ipprefix = (IpPrefix)parcel.readParcelable(null);
            inetaddress = null;
            abyte0 = parcel.createByteArray();
            InetAddress inetaddress1 = InetAddress.getByAddress(abyte0);
            inetaddress = inetaddress1;
_L2:
            return new RouteInfo(ipprefix, inetaddress, parcel.readString(), parcel.readInt());
            UnknownHostException unknownhostexception;
            unknownhostexception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RouteInfo[] newArray(int i)
        {
            return new RouteInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int RTN_THROW = 9;
    public static final int RTN_UNICAST = 1;
    public static final int RTN_UNREACHABLE = 7;
    private final IpPrefix mDestination;
    private final InetAddress mGateway;
    private final boolean mHasGateway;
    private final String mInterface;
    private final boolean mIsHost;
    private final int mType;

}

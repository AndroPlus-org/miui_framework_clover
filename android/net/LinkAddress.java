// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;
import android.system.OsConstants;
import android.util.Pair;
import java.net.*;

// Referenced classes of package android.net:
//            NetworkUtils

public class LinkAddress
    implements Parcelable
{

    public LinkAddress(String s)
    {
        this(s, 0, 0);
        scope = scopeForUnicastAddress(address);
    }

    public LinkAddress(String s, int i, int j)
    {
        s = NetworkUtils.parseIpAndMask(s);
        init((InetAddress)((Pair) (s)).first, ((Integer)((Pair) (s)).second).intValue(), i, j);
    }

    public LinkAddress(InetAddress inetaddress, int i)
    {
        this(inetaddress, i, 0, 0);
        scope = scopeForUnicastAddress(inetaddress);
    }

    public LinkAddress(InetAddress inetaddress, int i, int j, int k)
    {
        init(inetaddress, i, j, k);
    }

    public LinkAddress(InterfaceAddress interfaceaddress)
    {
        this(interfaceaddress.getAddress(), interfaceaddress.getNetworkPrefixLength());
    }

    private void init(InetAddress inetaddress, int i, int j, int k)
    {
        while(inetaddress == null || inetaddress.isMulticastAddress() || i < 0 || (inetaddress instanceof Inet4Address) && i > 32 || i > 128) 
            throw new IllegalArgumentException((new StringBuilder()).append("Bad LinkAddress params ").append(inetaddress).append("/").append(i).toString());
        address = inetaddress;
        prefixLength = i;
        flags = j;
        scope = k;
    }

    private boolean isIPv6ULA()
    {
        boolean flag = false;
        if(isIPv6())
        {
            if((address.getAddress()[0] & -2) == -4)
                flag = true;
            return flag;
        } else
        {
            return false;
        }
    }

    private static int scopeForUnicastAddress(InetAddress inetaddress)
    {
        if(inetaddress.isAnyLocalAddress())
            return OsConstants.RT_SCOPE_HOST;
        if(inetaddress.isLoopbackAddress() || inetaddress.isLinkLocalAddress())
            return OsConstants.RT_SCOPE_LINK;
        if(!(inetaddress instanceof Inet4Address) && inetaddress.isSiteLocalAddress())
            return OsConstants.RT_SCOPE_SITE;
        else
            return OsConstants.RT_SCOPE_UNIVERSE;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(!(obj instanceof LinkAddress))
            return false;
        obj = (LinkAddress)obj;
        boolean flag1 = flag;
        if(address.equals(((LinkAddress) (obj)).address))
        {
            flag1 = flag;
            if(prefixLength == ((LinkAddress) (obj)).prefixLength)
            {
                flag1 = flag;
                if(flags == ((LinkAddress) (obj)).flags)
                {
                    flag1 = flag;
                    if(scope == ((LinkAddress) (obj)).scope)
                        flag1 = true;
                }
            }
        }
        return flag1;
    }

    public InetAddress getAddress()
    {
        return address;
    }

    public int getFlags()
    {
        return flags;
    }

    public int getNetworkPrefixLength()
    {
        return getPrefixLength();
    }

    public int getPrefixLength()
    {
        return prefixLength;
    }

    public int getScope()
    {
        return scope;
    }

    public int hashCode()
    {
        return address.hashCode() + prefixLength * 11 + flags * 19 + scope * 43;
    }

    public boolean isGlobalPreferred()
    {
        boolean flag = true;
        boolean flag1;
        if(scope == OsConstants.RT_SCOPE_UNIVERSE && isIPv6ULA() ^ true && (long)(flags & (OsConstants.IFA_F_DADFAILED | OsConstants.IFA_F_DEPRECATED)) == 0L)
        {
            flag1 = flag;
            if((long)(flags & OsConstants.IFA_F_TENTATIVE) != 0L)
                if((long)(flags & OsConstants.IFA_F_OPTIMISTIC) != 0L)
                    flag1 = flag;
                else
                    flag1 = false;
        } else
        {
            flag1 = false;
        }
        return flag1;
    }

    public boolean isIPv4()
    {
        return address instanceof Inet4Address;
    }

    public boolean isIPv6()
    {
        return address instanceof Inet6Address;
    }

    public boolean isSameAddressAs(LinkAddress linkaddress)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(address.equals(linkaddress.address))
        {
            flag1 = flag;
            if(prefixLength == linkaddress.prefixLength)
                flag1 = true;
        }
        return flag1;
    }

    public String toString()
    {
        return (new StringBuilder()).append(address.getHostAddress()).append("/").append(prefixLength).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeByteArray(address.getAddress());
        parcel.writeInt(prefixLength);
        parcel.writeInt(flags);
        parcel.writeInt(scope);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public LinkAddress createFromParcel(Parcel parcel)
        {
            InetAddress inetaddress = null;
            InetAddress inetaddress1 = InetAddress.getByAddress(parcel.createByteArray());
            inetaddress = inetaddress1;
_L2:
            return new LinkAddress(inetaddress, parcel.readInt(), parcel.readInt(), parcel.readInt());
            UnknownHostException unknownhostexception;
            unknownhostexception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public LinkAddress[] newArray(int i)
        {
            return new LinkAddress[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private InetAddress address;
    private int flags;
    private int prefixLength;
    private int scope;

}

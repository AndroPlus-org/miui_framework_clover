// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import java.net.*;
import java.util.Arrays;

// Referenced classes of package android.net:
//            NetworkUtils

public final class IpPrefix
    implements Parcelable
{

    public IpPrefix(String s)
    {
        s = NetworkUtils.parseIpAndMask(s);
        address = ((InetAddress)((Pair) (s)).first).getAddress();
        prefixLength = ((Integer)((Pair) (s)).second).intValue();
        checkAndMaskAddressAndPrefixLength();
    }

    public IpPrefix(InetAddress inetaddress, int i)
    {
        address = inetaddress.getAddress();
        prefixLength = i;
        checkAndMaskAddressAndPrefixLength();
    }

    public IpPrefix(byte abyte0[], int i)
    {
        address = (byte[])abyte0.clone();
        prefixLength = i;
        checkAndMaskAddressAndPrefixLength();
    }

    private void checkAndMaskAddressAndPrefixLength()
    {
        if(address.length != 4 && address.length != 16)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("IpPrefix has ").append(address.length).append(" bytes which is neither 4 nor 16").toString());
        } else
        {
            NetworkUtils.maskRawAddress(address, prefixLength);
            return;
        }
    }

    public boolean contains(InetAddress inetaddress)
    {
        if(inetaddress == null)
            inetaddress = null;
        else
            inetaddress = inetaddress.getAddress();
        if(inetaddress == null || inetaddress.length != address.length)
        {
            return false;
        } else
        {
            NetworkUtils.maskRawAddress(inetaddress, prefixLength);
            return Arrays.equals(address, inetaddress);
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(!(obj instanceof IpPrefix))
            return false;
        obj = (IpPrefix)obj;
        boolean flag1 = flag;
        if(Arrays.equals(address, ((IpPrefix) (obj)).address))
        {
            flag1 = flag;
            if(prefixLength == ((IpPrefix) (obj)).prefixLength)
                flag1 = true;
        }
        return flag1;
    }

    public InetAddress getAddress()
    {
        InetAddress inetaddress;
        try
        {
            inetaddress = InetAddress.getByAddress(address);
        }
        catch(UnknownHostException unknownhostexception)
        {
            return null;
        }
        return inetaddress;
    }

    public int getPrefixLength()
    {
        return prefixLength;
    }

    public byte[] getRawAddress()
    {
        return (byte[])address.clone();
    }

    public int hashCode()
    {
        return Arrays.hashCode(address) + prefixLength * 11;
    }

    public boolean isIPv4()
    {
        return getAddress() instanceof Inet4Address;
    }

    public boolean isIPv6()
    {
        return getAddress() instanceof Inet6Address;
    }

    public String toString()
    {
        Object obj;
        try
        {
            obj = JVM INSTR new #70  <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            obj = ((StringBuilder) (obj)).append(InetAddress.getByAddress(address).getHostAddress()).append("/").append(prefixLength).toString();
        }
        catch(UnknownHostException unknownhostexception)
        {
            throw new IllegalStateException("IpPrefix with invalid address! Shouldn't happen.", unknownhostexception);
        }
        return ((String) (obj));
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeByteArray(address);
        parcel.writeInt(prefixLength);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public IpPrefix createFromParcel(Parcel parcel)
        {
            return new IpPrefix(parcel.createByteArray(), parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public IpPrefix[] newArray(int i)
        {
            return new IpPrefix[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final byte address[];
    private final int prefixLength;

}

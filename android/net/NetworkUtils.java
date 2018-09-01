// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.util.Log;
import android.util.Pair;
import java.io.FileDescriptor;
import java.net.*;
import java.util.*;

public class NetworkUtils
{

    public NetworkUtils()
    {
    }

    public static boolean addressTypeMatches(InetAddress inetaddress, InetAddress inetaddress1)
    {
        boolean flag;
        if(!(inetaddress instanceof Inet4Address) || !(inetaddress1 instanceof Inet4Address))
        {
            if(inetaddress instanceof Inet6Address)
                flag = inetaddress1 instanceof Inet6Address;
            else
                flag = false;
        } else
        {
            flag = true;
        }
        return flag;
    }

    public static native void attachControlPacketFilter(FileDescriptor filedescriptor, int i)
        throws SocketException;

    public static native void attachDhcpFilter(FileDescriptor filedescriptor)
        throws SocketException;

    public static native void attachRaFilter(FileDescriptor filedescriptor, int i)
        throws SocketException;

    public static native boolean bindProcessToNetwork(int i);

    public static native boolean bindProcessToNetworkForHostResolution(int i);

    public static native int bindSocketToNetwork(int i, int j);

    public static native int getBoundNetworkForProcess();

    public static int getImplicitNetmask(Inet4Address inet4address)
    {
        int i = inet4address.getAddress()[0] & 0xff;
        if(i < 128)
            return 8;
        if(i < 192)
            return 16;
        return i >= 224 ? 32 : 24;
    }

    public static InetAddress getNetworkPart(InetAddress inetaddress, int i)
    {
        inetaddress = inetaddress.getAddress();
        maskRawAddress(inetaddress, i);
        try
        {
            inetaddress = InetAddress.getByAddress(inetaddress);
        }
        // Misplaced declaration of an exception variable
        catch(InetAddress inetaddress)
        {
            throw new RuntimeException((new StringBuilder()).append("getNetworkPart error - ").append(inetaddress.toString()).toString());
        }
        return inetaddress;
    }

    public static InetAddress hexToInet6Address(String s)
        throws IllegalArgumentException
    {
        InetAddress inetaddress;
        try
        {
            inetaddress = numericToInetAddress(String.format(Locale.US, "%s:%s:%s:%s:%s:%s:%s:%s", new Object[] {
                s.substring(0, 4), s.substring(4, 8), s.substring(8, 12), s.substring(12, 16), s.substring(16, 20), s.substring(20, 24), s.substring(24, 28), s.substring(28, 32)
            }));
        }
        catch(Exception exception)
        {
            Log.e("NetworkUtils", (new StringBuilder()).append("error in hexToInet6Address(").append(s).append("): ").append(exception).toString());
            throw new IllegalArgumentException(exception);
        }
        return inetaddress;
    }

    public static int inetAddressToInt(Inet4Address inet4address)
        throws IllegalArgumentException
    {
        inet4address = inet4address.getAddress();
        return (inet4address[3] & 0xff) << 24 | (inet4address[2] & 0xff) << 16 | (inet4address[1] & 0xff) << 8 | inet4address[0] & 0xff;
    }

    public static InetAddress intToInetAddress(int i)
    {
        byte byte0 = (byte)(i & 0xff);
        byte byte1 = (byte)(i >> 8 & 0xff);
        byte byte2 = (byte)(i >> 16 & 0xff);
        byte byte3 = (byte)(i >> 24 & 0xff);
        InetAddress inetaddress;
        try
        {
            inetaddress = InetAddress.getByAddress(new byte[] {
                byte0, byte1, byte2, byte3
            });
        }
        catch(UnknownHostException unknownhostexception)
        {
            throw new AssertionError();
        }
        return inetaddress;
    }

    public static String[] makeStrings(Collection collection)
    {
        String as[] = new String[collection.size()];
        int i = 0;
        for(collection = collection.iterator(); collection.hasNext();)
        {
            as[i] = ((InetAddress)collection.next()).getHostAddress();
            i++;
        }

        return as;
    }

    public static void maskRawAddress(byte abyte0[], int i)
    {
        if(i < 0 || i > abyte0.length * 8)
            throw new RuntimeException((new StringBuilder()).append("IP address with ").append(abyte0.length).append(" bytes has invalid prefix length ").append(i).toString());
        int j = i / 8;
        i = (byte)(255 << 8 - i % 8);
        if(j < abyte0.length)
            abyte0[j] = (byte)(abyte0[j] & i);
        for(i = j + 1; i < abyte0.length; i++)
            abyte0[i] = (byte)0;

    }

    public static int netmaskIntToPrefixLength(int i)
    {
        return Integer.bitCount(i);
    }

    public static int netmaskToPrefixLength(Inet4Address inet4address)
    {
        int i = Integer.reverseBytes(inetAddressToInt(inet4address));
        int j = Integer.bitCount(i);
        if(Integer.numberOfTrailingZeros(i) != 32 - j)
            throw new IllegalArgumentException((new StringBuilder()).append("Non-contiguous netmask: ").append(Integer.toHexString(i)).toString());
        else
            return j;
    }

    public static InetAddress numericToInetAddress(String s)
        throws IllegalArgumentException
    {
        return InetAddress.parseNumericAddress(s);
    }

    protected static void parcelInetAddress(Parcel parcel, InetAddress inetaddress, int i)
    {
        if(inetaddress != null)
            inetaddress = inetaddress.getAddress();
        else
            inetaddress = null;
        parcel.writeByteArray(inetaddress);
    }

    public static Pair parseIpAndMask(String s)
    {
        InetAddress inetaddress;
        int i;
        int j;
        int k;
        int l;
        int i1;
        inetaddress = null;
        i = -1;
        j = i;
        k = i;
        l = i;
        i1 = i;
        String as[] = s.split("/", 2);
        j = i;
        k = i;
        l = i;
        i1 = i;
        i = Integer.parseInt(as[1]);
        j = i;
        k = i;
        l = i;
        i1 = i;
        InetAddress inetaddress1 = InetAddress.parseNumericAddress(as[0]);
        inetaddress = inetaddress1;
_L2:
        if(inetaddress == null || i == -1)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid IP address and mask ").append(s).toString());
        else
            return new Pair(inetaddress, Integer.valueOf(i));
        Object obj;
        obj;
        i = j;
        continue; /* Loop/switch isn't completed */
        obj;
        i = k;
        continue; /* Loop/switch isn't completed */
        obj;
        i = l;
        continue; /* Loop/switch isn't completed */
        obj;
        i = i1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static int prefixLengthToNetmaskInt(int i)
        throws IllegalArgumentException
    {
        if(i < 0 || i > 32)
            throw new IllegalArgumentException("Invalid prefix length (0 <= prefix <= 32)");
        else
            return Integer.reverseBytes(-1 << 32 - i);
    }

    public static native boolean protectFromVpn(int i);

    public static boolean protectFromVpn(FileDescriptor filedescriptor)
    {
        return protectFromVpn(filedescriptor.getInt$());
    }

    public static native boolean queryUserAccess(int i, int j);

    public static native void setupRaSocket(FileDescriptor filedescriptor, int i)
        throws SocketException;

    public static String trimV4AddrZeros(String s)
    {
        String as[];
        StringBuilder stringbuilder;
        int i;
        if(s == null)
            return null;
        as = s.split("\\.");
        if(as.length != 4)
            return s;
        stringbuilder = new StringBuilder(16);
        i = 0;
_L3:
        if(i >= 4) goto _L2; else goto _L1
_L1:
        if(as[i].length() > 3)
            return s;
        try
        {
            stringbuilder.append(Integer.parseInt(as[i]));
        }
        catch(NumberFormatException numberformatexception)
        {
            return s;
        }
        if(i < 3)
            stringbuilder.append('.');
        i++;
          goto _L3
_L2:
        return stringbuilder.toString();
    }

    protected static InetAddress unparcelInetAddress(Parcel parcel)
    {
        parcel = parcel.createByteArray();
        if(parcel == null)
            return null;
        try
        {
            parcel = InetAddress.getByAddress(parcel);
        }
        // Misplaced declaration of an exception variable
        catch(Parcel parcel)
        {
            return null;
        }
        return parcel;
    }

    private static final String TAG = "NetworkUtils";
}

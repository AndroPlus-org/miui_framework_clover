// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securitycenter.net;

import android.content.Context;
import android.net.*;
import android.os.SystemClock;
import android.system.*;
import android.util.Log;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import libcore.io.IoUtils;

public class NetworkDiagnostics
{
    public static final class DnsResponseCode extends Enum
    {

        public static DnsResponseCode valueOf(String s)
        {
            return (DnsResponseCode)Enum.valueOf(miui/securitycenter/net/NetworkDiagnostics$DnsResponseCode, s);
        }

        public static DnsResponseCode[] values()
        {
            return $VALUES;
        }

        private static final DnsResponseCode $VALUES[];
        public static final DnsResponseCode FORMERR;
        public static final DnsResponseCode NOERROR;
        public static final DnsResponseCode NOTIMP;
        public static final DnsResponseCode NXDOMAIN;
        public static final DnsResponseCode REFUSED;
        public static final DnsResponseCode SERVFAIL;

        static 
        {
            NOERROR = new DnsResponseCode("NOERROR", 0);
            FORMERR = new DnsResponseCode("FORMERR", 1);
            SERVFAIL = new DnsResponseCode("SERVFAIL", 2);
            NXDOMAIN = new DnsResponseCode("NXDOMAIN", 3);
            NOTIMP = new DnsResponseCode("NOTIMP", 4);
            REFUSED = new DnsResponseCode("REFUSED", 5);
            $VALUES = (new DnsResponseCode[] {
                NOERROR, FORMERR, SERVFAIL, NXDOMAIN, NOTIMP, REFUSED
            });
        }

        private DnsResponseCode(String s, int i)
        {
            super(s, i);
        }
    }

    private static class DnsUdpCheck extends SimpleSocketCheck
    {

        private byte[] getDnsQueryPacket(String s)
        {
            s = s.getBytes(StandardCharsets.US_ASCII);
            return (new byte[] {
                (byte)mRandom.nextInt(), (byte)mRandom.nextInt(), 1, 0, 0, 1, 0, 0, 0, 0, 
                0, 0, 17, s[0], s[1], s[2], s[3], s[4], s[5], 45, 
                97, 110, 100, 114, 111, 105, 100, 45, 100, 115, 
                6, 109, 101, 116, 114, 105, 99, 7, 103, 115, 
                116, 97, 116, 105, 99, 3, 99, 111, 109, 0, 
                0, (byte)mQueryType, 0, 1
            });
        }

        public boolean call()
        {
            boolean flag = false;
            boolean flag1;
            byte abyte0[];
            int i;
            ByteBuffer bytebuffer;
            try
            {
                setupSocket(OsConstants.SOCK_DGRAM, OsConstants.IPPROTO_UDP, 100L, 500L, 53);
            }
            catch(Object obj)
            {
                Log.e("NetworkDiagnostics", "DnsUdpCheck", ((Throwable) (obj)));
                return false;
            }
            abyte0 = getDnsQueryPacket(Integer.valueOf(mRandom.nextInt(0xdbba0) + 0x186a0).toString());
            i = 0;
_L4:
            flag1 = flag;
            if(NetworkDiagnostics._2D_wrap1() >= mDeadlineTime - 1000L)
                break MISSING_BLOCK_LABEL_99;
            i++;
            Os.write(mFileDescriptor, abyte0, 0, abyte0.length);
            bytebuffer = ByteBuffer.allocate(512);
            Os.read(mFileDescriptor, bytebuffer);
            flag1 = true;
_L2:
            close();
            return flag1;
            Object obj1;
            obj1;
            Log.e("NetworkDiagnostics", "DnsUdpCheck", ((Throwable) (obj1)));
            flag1 = flag;
            if(true) goto _L2; else goto _L1
_L1:
            Object obj2;
            obj2;
            if(true) goto _L4; else goto _L3
_L3:
        }

        private static final int DNS_SERVER_PORT = 53;
        private static final int PACKET_BUFSIZE = 512;
        private static final int RR_TYPE_A = 1;
        private static final int RR_TYPE_AAAA = 28;
        private static final int TIMEOUT_RECV = 500;
        private static final int TIMEOUT_SEND = 100;
        private final int mQueryType;
        private final Random mRandom = new Random();

        public DnsUdpCheck(Network network, LinkProperties linkproperties, InetAddress inetaddress, long l)
        {
            super(network, linkproperties, inetaddress, l);
            if(mAddressFamily == OsConstants.AF_INET6)
                mQueryType = 28;
            else
                mQueryType = 1;
        }
    }

    private static class IcmpCheck extends SimpleSocketCheck
    {

        public boolean call()
            throws Exception
        {
            boolean flag = false;
            boolean flag1;
            byte abyte0[];
            int i;
            ByteBuffer bytebuffer;
            try
            {
                setupSocket(OsConstants.SOCK_DGRAM, mProtocol, 100L, 300L, 0);
            }
            catch(Object obj)
            {
                Log.e("NetworkDiagnostics", "IcmpCheck", ((Throwable) (obj)));
                return false;
            }
            abyte0 = new byte[8];
            abyte0[0] = (byte)mIcmpType;
            abyte0[1] = (byte)0;
            abyte0[2] = (byte)0;
            abyte0[3] = (byte)0;
            abyte0[4] = (byte)0;
            abyte0[5] = (byte)0;
            abyte0[6] = (byte)0;
            abyte0[7] = (byte)0;
            i = 0;
_L4:
            flag1 = flag;
            if(NetworkDiagnostics._2D_wrap1() >= mDeadlineTime - 400L)
                break MISSING_BLOCK_LABEL_136;
            i++;
            abyte0[abyte0.length - 1] = (byte)i;
            Os.write(mFileDescriptor, abyte0, 0, abyte0.length);
            bytebuffer = ByteBuffer.allocate(512);
            Os.read(mFileDescriptor, bytebuffer);
            flag1 = true;
_L2:
            close();
            return flag1;
            Object obj1;
            obj1;
            Log.e("NetworkDiagnostics", "IcmpCheck", ((Throwable) (obj1)));
            flag1 = flag;
            if(true) goto _L2; else goto _L1
_L1:
            Object obj2;
            obj2;
            if(true) goto _L4; else goto _L3
_L3:
        }

        private static final int ICMPV4_ECHO_REQUEST = 8;
        private static final int ICMPV6_ECHO_REQUEST = 128;
        private static final int PACKET_BUFSIZE = 512;
        private static final int TIMEOUT_RECV = 300;
        private static final int TIMEOUT_SEND = 100;
        private final int mIcmpType;
        private final int mProtocol;

        public IcmpCheck(Network network, LinkProperties linkproperties, InetAddress inetaddress, long l)
        {
            this(network, linkproperties, null, inetaddress, l);
        }

        public IcmpCheck(Network network, LinkProperties linkproperties, InetAddress inetaddress, InetAddress inetaddress1, long l)
        {
            super(network, linkproperties, inetaddress, inetaddress1, l);
            if(mAddressFamily == OsConstants.AF_INET6)
            {
                mProtocol = OsConstants.IPPROTO_ICMPV6;
                mIcmpType = 128;
            } else
            {
                mProtocol = OsConstants.IPPROTO_ICMP;
                mIcmpType = 8;
            }
        }
    }

    private static class SimpleSocketCheck
        implements Closeable
    {

        public void close()
        {
            IoUtils.closeQuietly(mFileDescriptor);
        }

        protected String getSocketAddressString()
        {
            InetSocketAddress inetsocketaddress = (InetSocketAddress)mSocketAddress;
            InetAddress inetaddress = inetsocketaddress.getAddress();
            String s;
            if(inetaddress instanceof Inet6Address)
                s = "[%s]:%d";
            else
                s = "%s:%d";
            return String.format(s, new Object[] {
                inetaddress.getHostAddress(), Integer.valueOf(inetsocketaddress.getPort())
            });
        }

        protected void setupSocket(int i, int j, long l, long l1, int k)
            throws ErrnoException, IOException
        {
            mFileDescriptor = Os.socket(mAddressFamily, i, j);
            Os.setsockoptTimeval(mFileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_SNDTIMEO, StructTimeval.fromMillis(l));
            Os.setsockoptTimeval(mFileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_RCVTIMEO, StructTimeval.fromMillis(l1));
            mNetwork.bindSocket(mFileDescriptor);
            if(mSource != null)
                Os.bind(mFileDescriptor, mSource, 0);
            Os.connect(mFileDescriptor, mTarget, k);
            mSocketAddress = Os.getsockname(mFileDescriptor);
        }

        protected final int mAddressFamily;
        protected final long mDeadlineTime;
        protected FileDescriptor mFileDescriptor;
        protected final Integer mInterfaceIndex;
        protected Network mNetwork;
        protected SocketAddress mSocketAddress;
        protected final InetAddress mSource;
        protected final InetAddress mTarget;

        protected SimpleSocketCheck(Network network, LinkProperties linkproperties, InetAddress inetaddress, long l)
        {
            this(network, linkproperties, null, inetaddress, l);
        }

        protected SimpleSocketCheck(Network network, LinkProperties linkproperties, InetAddress inetaddress, InetAddress inetaddress1, long l)
        {
            mNetwork = network;
            mDeadlineTime = NetworkDiagnostics._2D_wrap1() + l;
            if(linkproperties != null)
                mInterfaceIndex = NetworkDiagnostics._2D_wrap0(linkproperties.getInterfaceName());
            else
                mInterfaceIndex = null;
            if(inetaddress1 instanceof Inet6Address)
            {
                linkproperties = null;
                network = linkproperties;
                if(inetaddress1.isLinkLocalAddress())
                {
                    network = linkproperties;
                    if(mInterfaceIndex != null)
                        try
                        {
                            network = Inet6Address.getByAddress(null, inetaddress1.getAddress(), mInterfaceIndex.intValue());
                        }
                        // Misplaced declaration of an exception variable
                        catch(Network network)
                        {
                            Log.e("NetworkDiagnostics", "SimpleSocketCheck", network);
                            network = linkproperties;
                        }
                }
                if(network == null)
                    network = inetaddress1;
                mTarget = network;
                mAddressFamily = OsConstants.AF_INET6;
            } else
            {
                mTarget = inetaddress1;
                mAddressFamily = OsConstants.AF_INET;
            }
            mSource = inetaddress;
        }
    }


    static Integer _2D_wrap0(String s)
    {
        return getInterfaceIndex(s);
    }

    static long _2D_wrap1()
    {
        return now();
    }

    public NetworkDiagnostics()
    {
    }

    public static Boolean activeNetworkDnsCheck(Context context, InetAddress inetaddress, Long long1)
    {
        if(context == null || long1.longValue() == 0L)
        {
            Log.i("NetworkDiagnostics", "activeNetworkIcmpCheck. invalidate parameter");
            return Boolean.valueOf(false);
        }
        ConnectivityManager connectivitymanager = (ConnectivityManager)context.getSystemService("connectivity");
        if(connectivitymanager == null)
            return Boolean.valueOf(false);
        LinkProperties linkproperties = connectivitymanager.getActiveLinkProperties();
        context = inetaddress;
        if(inetaddress == null)
        {
            context = inetaddress;
            if(linkproperties != null)
                if(linkproperties.hasGlobalIPv6Address() || linkproperties.hasIPv6DefaultRoute())
                    context = TEST_DNS4;
                else
                    context = TEST_DNS6;
        }
        return Boolean.valueOf(dnsCheck(connectivitymanager.getActiveNetwork(), linkproperties, context, long1));
    }

    public static Boolean activeNetworkIcmpCheck(Context context, InetAddress inetaddress, Long long1)
    {
        if(context == null || long1.longValue() == 0L)
        {
            Log.i("NetworkDiagnostics", "activeNetworkIcmpCheck. invalidate parameter");
            return Boolean.valueOf(false);
        }
        ConnectivityManager connectivitymanager = (ConnectivityManager)context.getSystemService("connectivity");
        if(connectivitymanager == null)
            return Boolean.valueOf(false);
        LinkProperties linkproperties = connectivitymanager.getActiveLinkProperties();
        context = inetaddress;
        if(inetaddress == null)
        {
            context = inetaddress;
            if(linkproperties != null)
                if(linkproperties.hasGlobalIPv6Address() || linkproperties.hasIPv6DefaultRoute())
                    context = Inet6Address.LOOPBACK;
                else
                    context = Inet4Address.LOOPBACK;
        }
        return Boolean.valueOf(icmpCheck(connectivitymanager.getActiveNetwork(), linkproperties, context, long1.longValue()));
    }

    public static boolean dnsCheck(Network network, LinkProperties linkproperties, InetAddress inetaddress, Long long1)
    {
_L2:
        LinkProperties linkproperties1;
        boolean flag;
        try
        {
            Log.i("NetworkDiagnostics", "dnsCheck. invalidate parameter");
        }
        // Misplaced declaration of an exception variable
        catch(Network network)
        {
            Log.d("NetworkDiagnostics", "dnsCheck", network);
            return false;
        }
        return false;
        if(network == null || linkproperties == null || inetaddress == null) goto _L2; else goto _L1
_L1:
        if(long1.longValue() == 0L) goto _L2; else goto _L3
_L3:
        linkproperties1 = JVM INSTR new #90  <Class LinkProperties>;
        linkproperties1.LinkProperties(linkproperties);
        linkproperties = JVM INSTR new #9   <Class NetworkDiagnostics$DnsUdpCheck>;
        linkproperties.DnsUdpCheck(network, linkproperties1, inetaddress, long1.longValue());
        flag = linkproperties.call();
        return flag;
    }

    private static Integer getInterfaceIndex(String s)
    {
        int i;
        try
        {
            i = NetworkInterface.getByName(s).getIndex();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        return Integer.valueOf(i);
    }

    public static boolean icmpCheck(Network network, LinkProperties linkproperties, InetAddress inetaddress, long l)
    {
        while(network == null || linkproperties == null || inetaddress == null || l == 0L) 
        {
            LinkProperties linkproperties1;
            boolean flag;
            try
            {
                Log.i("NetworkDiagnostics", "icmpCheck. invalidate parameter");
            }
            // Misplaced declaration of an exception variable
            catch(Network network)
            {
                Log.d("NetworkDiagnostics", "icmpCheck", network);
                return false;
            }
            return false;
        }
        linkproperties1 = JVM INSTR new #90  <Class LinkProperties>;
        linkproperties1.LinkProperties(linkproperties);
        linkproperties = JVM INSTR new #12  <Class NetworkDiagnostics$IcmpCheck>;
        linkproperties.IcmpCheck(network, linkproperties1, inetaddress, l);
        flag = linkproperties.call();
        return flag;
    }

    private static final long now()
    {
        return SystemClock.elapsedRealtime();
    }

    private static final String TAG = "NetworkDiagnostics";
    private static final InetAddress TEST_DNS4 = NetworkUtils.numericToInetAddress("8.8.8.8");
    private static final InetAddress TEST_DNS6 = NetworkUtils.numericToInetAddress("2001:4860:4860::8888");

}

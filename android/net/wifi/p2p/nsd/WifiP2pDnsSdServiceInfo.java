// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.p2p.nsd;

import android.net.nsd.DnsSdTxtRecord;
import android.text.TextUtils;
import java.util.*;

// Referenced classes of package android.net.wifi.p2p.nsd:
//            WifiP2pServiceInfo

public class WifiP2pDnsSdServiceInfo extends WifiP2pServiceInfo
{

    private WifiP2pDnsSdServiceInfo(List list)
    {
        super(list);
    }

    private static String compressDnsName(String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
_L6:
        String s1 = (String)sVmPacket.get(s);
        if(s1 == null) goto _L2; else goto _L1
_L1:
        stringbuffer.append(s1);
_L4:
        return stringbuffer.toString();
_L2:
        int i;
        i = s.indexOf('.');
        if(i != -1)
            break; /* Loop/switch isn't completed */
        if(s.length() > 0)
        {
            stringbuffer.append(String.format(Locale.US, "%02x", new Object[] {
                Integer.valueOf(s.length())
            }));
            stringbuffer.append(WifiP2pServiceInfo.bin2HexStr(s.getBytes()));
        }
        stringbuffer.append("00");
        if(true) goto _L4; else goto _L3
_L3:
        String s2 = s.substring(0, i);
        s = s.substring(i + 1);
        stringbuffer.append(String.format(Locale.US, "%02x", new Object[] {
            Integer.valueOf(s2.length())
        }));
        stringbuffer.append(WifiP2pServiceInfo.bin2HexStr(s2.getBytes()));
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static String createPtrServiceQuery(String s, String s1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("bonjour ");
        stringbuffer.append(createRequest((new StringBuilder()).append(s1).append(".local.").toString(), 12, 1));
        stringbuffer.append(" ");
        s = s.getBytes();
        stringbuffer.append(String.format(Locale.US, "%02x", new Object[] {
            Integer.valueOf(s.length)
        }));
        stringbuffer.append(WifiP2pServiceInfo.bin2HexStr(s));
        stringbuffer.append("c027");
        return stringbuffer.toString();
    }

    static String createRequest(String s, int i, int j)
    {
        StringBuffer stringbuffer = new StringBuffer();
        String s1 = s;
        if(i == 16)
            s1 = s.toLowerCase(Locale.ROOT);
        stringbuffer.append(compressDnsName(s1));
        stringbuffer.append(String.format(Locale.US, "%04x", new Object[] {
            Integer.valueOf(i)
        }));
        stringbuffer.append(String.format(Locale.US, "%02x", new Object[] {
            Integer.valueOf(j)
        }));
        return stringbuffer.toString();
    }

    private static String createTxtServiceQuery(String s, String s1, DnsSdTxtRecord dnssdtxtrecord)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("bonjour ");
        stringbuffer.append(createRequest((new StringBuilder()).append(s).append(".").append(s1).append(".local.").toString(), 16, 1));
        stringbuffer.append(" ");
        s = dnssdtxtrecord.getRawData();
        if(s.length == 0)
            stringbuffer.append("00");
        else
            stringbuffer.append(bin2HexStr(s));
        return stringbuffer.toString();
    }

    public static WifiP2pDnsSdServiceInfo newInstance(String s, String s1, Map map)
    {
        if(TextUtils.isEmpty(s) || TextUtils.isEmpty(s1))
            throw new IllegalArgumentException("instance name or service type cannot be empty");
        DnsSdTxtRecord dnssdtxtrecord = new DnsSdTxtRecord();
        if(map != null)
        {
            String s2;
            for(Iterator iterator = map.keySet().iterator(); iterator.hasNext(); dnssdtxtrecord.set(s2, (String)map.get(s2)))
                s2 = (String)iterator.next();

        }
        map = new ArrayList();
        map.add(createPtrServiceQuery(s, s1));
        map.add(createTxtServiceQuery(s, s1, dnssdtxtrecord));
        return new WifiP2pDnsSdServiceInfo(map);
    }

    public static final int DNS_TYPE_PTR = 12;
    public static final int DNS_TYPE_TXT = 16;
    public static final int VERSION_1 = 1;
    private static final Map sVmPacket;

    static 
    {
        sVmPacket = new HashMap();
        sVmPacket.put("_tcp.local.", "c00c");
        sVmPacket.put("local.", "c011");
        sVmPacket.put("_udp.local.", "c01c");
    }
}

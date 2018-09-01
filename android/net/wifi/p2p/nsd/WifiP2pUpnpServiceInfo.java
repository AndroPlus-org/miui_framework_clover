// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.p2p.nsd;

import java.util.*;

// Referenced classes of package android.net.wifi.p2p.nsd:
//            WifiP2pServiceInfo

public class WifiP2pUpnpServiceInfo extends WifiP2pServiceInfo
{

    private WifiP2pUpnpServiceInfo(List list)
    {
        super(list);
    }

    private static String createSupplicantQuery(String s, String s1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("upnp ");
        stringbuffer.append(String.format(Locale.US, "%02x ", new Object[] {
            Integer.valueOf(16)
        }));
        stringbuffer.append("uuid:");
        stringbuffer.append(s);
        if(s1 != null)
        {
            stringbuffer.append("::");
            stringbuffer.append(s1);
        }
        return stringbuffer.toString();
    }

    public static WifiP2pUpnpServiceInfo newInstance(String s, String s1, List list)
    {
        if(s == null || s1 == null)
            throw new IllegalArgumentException("uuid or device cannnot be null");
        UUID.fromString(s);
        ArrayList arraylist = new ArrayList();
        arraylist.add(createSupplicantQuery(s, null));
        arraylist.add(createSupplicantQuery(s, "upnp:rootdevice"));
        arraylist.add(createSupplicantQuery(s, s1));
        if(list != null)
            for(s1 = list.iterator(); s1.hasNext(); arraylist.add(createSupplicantQuery(s, (String)s1.next())));
        return new WifiP2pUpnpServiceInfo(arraylist);
    }

    public static final int VERSION_1_0 = 16;
}

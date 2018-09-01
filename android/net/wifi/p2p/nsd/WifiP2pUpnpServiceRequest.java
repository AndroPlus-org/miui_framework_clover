// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.p2p.nsd;

import java.util.Locale;

// Referenced classes of package android.net.wifi.p2p.nsd:
//            WifiP2pServiceRequest, WifiP2pServiceInfo

public class WifiP2pUpnpServiceRequest extends WifiP2pServiceRequest
{

    protected WifiP2pUpnpServiceRequest()
    {
        super(2, null);
    }

    protected WifiP2pUpnpServiceRequest(String s)
    {
        super(2, s);
    }

    public static WifiP2pUpnpServiceRequest newInstance()
    {
        return new WifiP2pUpnpServiceRequest();
    }

    public static WifiP2pUpnpServiceRequest newInstance(String s)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("search target cannot be null");
        } else
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append(String.format(Locale.US, "%02x", new Object[] {
                Integer.valueOf(16)
            }));
            stringbuffer.append(WifiP2pServiceInfo.bin2HexStr(s.getBytes()));
            return new WifiP2pUpnpServiceRequest(stringbuffer.toString());
        }
    }
}

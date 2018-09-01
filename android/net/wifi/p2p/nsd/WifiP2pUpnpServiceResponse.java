// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.p2p.nsd;

import android.net.wifi.p2p.WifiP2pDevice;
import java.util.*;

// Referenced classes of package android.net.wifi.p2p.nsd:
//            WifiP2pServiceResponse

public class WifiP2pUpnpServiceResponse extends WifiP2pServiceResponse
{

    protected WifiP2pUpnpServiceResponse(int i, int j, WifiP2pDevice wifip2pdevice, byte abyte0[])
    {
        super(2, i, j, wifip2pdevice, abyte0);
        if(!parse())
            throw new IllegalArgumentException("Malformed upnp service response");
        else
            return;
    }

    static WifiP2pUpnpServiceResponse newInstance(int i, int j, WifiP2pDevice wifip2pdevice, byte abyte0[])
    {
        if(i != 0)
            return new WifiP2pUpnpServiceResponse(i, j, wifip2pdevice, null);
        try
        {
            wifip2pdevice = new WifiP2pUpnpServiceResponse(i, j, wifip2pdevice, abyte0);
        }
        // Misplaced declaration of an exception variable
        catch(WifiP2pDevice wifip2pdevice)
        {
            wifip2pdevice.printStackTrace();
            return null;
        }
        return wifip2pdevice;
    }

    private boolean parse()
    {
        int i = 0;
        if(mData == null)
            return true;
        if(mData.length < 1)
            return false;
        mVersion = mData[0] & 0xff;
        String as[] = (new String(mData, 1, mData.length - 1)).split(",");
        mUniqueServiceNames = new ArrayList();
        for(int j = as.length; i < j; i++)
        {
            String s = as[i];
            mUniqueServiceNames.add(s);
        }

        return true;
    }

    public List getUniqueServiceNames()
    {
        return mUniqueServiceNames;
    }

    public int getVersion()
    {
        return mVersion;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("serviceType:UPnP(").append(mServiceType).append(")");
        stringbuffer.append(" status:").append(WifiP2pServiceResponse.Status.toString(mStatus));
        stringbuffer.append(" srcAddr:").append(mDevice.deviceAddress);
        stringbuffer.append(" version:").append(String.format("%02x", new Object[] {
            Integer.valueOf(mVersion)
        }));
        if(mUniqueServiceNames != null)
        {
            String s;
            for(Iterator iterator = mUniqueServiceNames.iterator(); iterator.hasNext(); stringbuffer.append(" usn:").append(s))
                s = (String)iterator.next();

        }
        return stringbuffer.toString();
    }

    private List mUniqueServiceNames;
    private int mVersion;
}

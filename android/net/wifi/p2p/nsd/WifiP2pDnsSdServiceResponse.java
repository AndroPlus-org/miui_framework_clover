// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.p2p.nsd;

import android.net.wifi.p2p.WifiP2pDevice;
import java.io.*;
import java.util.*;

// Referenced classes of package android.net.wifi.p2p.nsd:
//            WifiP2pServiceResponse

public class WifiP2pDnsSdServiceResponse extends WifiP2pServiceResponse
{

    protected WifiP2pDnsSdServiceResponse(int i, int j, WifiP2pDevice wifip2pdevice, byte abyte0[])
    {
        super(1, i, j, wifip2pdevice, abyte0);
        if(!parse())
            throw new IllegalArgumentException("Malformed bonjour service response");
        else
            return;
    }

    static WifiP2pDnsSdServiceResponse newInstance(int i, int j, WifiP2pDevice wifip2pdevice, byte abyte0[])
    {
        if(i != 0)
            return new WifiP2pDnsSdServiceResponse(i, j, wifip2pdevice, null);
        try
        {
            wifip2pdevice = new WifiP2pDnsSdServiceResponse(i, j, wifip2pdevice, abyte0);
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
        if(mData == null)
            return true;
        Object obj = new DataInputStream(new ByteArrayInputStream(mData));
        mDnsQueryName = readDnsName(((DataInputStream) (obj)));
        if(mDnsQueryName == null)
            return false;
        try
        {
            mDnsType = ((DataInputStream) (obj)).readUnsignedShort();
            mVersion = ((DataInputStream) (obj)).readUnsignedByte();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            ((IOException) (obj)).printStackTrace();
            return false;
        }
        if(mDnsType == 12)
        {
            obj = readDnsName(((DataInputStream) (obj)));
            if(obj == null)
                return false;
            if(((String) (obj)).length() <= mDnsQueryName.length())
            {
                return false;
            } else
            {
                mInstanceName = ((String) (obj)).substring(0, ((String) (obj)).length() - mDnsQueryName.length() - 1);
                return true;
            }
        }
        if(mDnsType == 16)
            return readTxtData(((DataInputStream) (obj)));
        else
            return false;
    }

    private String readDnsName(DataInputStream datainputstream)
    {
        StringBuffer stringbuffer;
        HashMap hashmap;
        stringbuffer = new StringBuffer();
        hashmap = new HashMap(sVmpack);
        if(mDnsQueryName != null)
            hashmap.put(Integer.valueOf(39), mDnsQueryName);
_L2:
        int i = datainputstream.readUnsignedByte();
        if(i == 0)
        {
            byte abyte0[];
            String s;
            try
            {
                return stringbuffer.toString();
            }
            // Misplaced declaration of an exception variable
            catch(DataInputStream datainputstream)
            {
                datainputstream.printStackTrace();
            }
            break; /* Loop/switch isn't completed */
        }
        if(i != 192)
            break MISSING_BLOCK_LABEL_96;
        datainputstream = (String)hashmap.get(Integer.valueOf(datainputstream.readUnsignedByte()));
        if(datainputstream == null)
            return null;
        stringbuffer.append(datainputstream);
        return stringbuffer.toString();
        abyte0 = new byte[i];
        datainputstream.readFully(abyte0);
        s = JVM INSTR new #104 <Class String>;
        s.String(abyte0);
        stringbuffer.append(s);
        stringbuffer.append(".");
        if(true) goto _L2; else goto _L1
_L1:
        return null;
    }

    private boolean readTxtData(DataInputStream datainputstream)
    {
_L2:
        int i;
        if(datainputstream.available() <= 0)
            break MISSING_BLOCK_LABEL_16;
        i = datainputstream.readUnsignedByte();
        if(i != 0)
            break MISSING_BLOCK_LABEL_18;
        return true;
        String as[];
        byte abyte0[] = new byte[i];
        datainputstream.readFully(abyte0);
        String s = JVM INSTR new #104 <Class String>;
        s.String(abyte0);
        as = s.split("=");
        if(as.length != 2)
            return false;
        try
        {
            mTxtRecord.put(as[0], as[1]);
        }
        // Misplaced declaration of an exception variable
        catch(DataInputStream datainputstream)
        {
            datainputstream.printStackTrace();
            return false;
        }
        if(true) goto _L2; else goto _L1
_L1:
    }

    public String getDnsQueryName()
    {
        return mDnsQueryName;
    }

    public int getDnsType()
    {
        return mDnsType;
    }

    public String getInstanceName()
    {
        return mInstanceName;
    }

    public Map getTxtRecord()
    {
        return mTxtRecord;
    }

    public int getVersion()
    {
        return mVersion;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("serviceType:DnsSd(").append(mServiceType).append(")");
        stringbuffer.append(" status:").append(WifiP2pServiceResponse.Status.toString(mStatus));
        stringbuffer.append(" srcAddr:").append(mDevice.deviceAddress);
        stringbuffer.append(" version:").append(String.format("%02x", new Object[] {
            Integer.valueOf(mVersion)
        }));
        stringbuffer.append(" dnsName:").append(mDnsQueryName);
        stringbuffer.append(" TxtRecord:");
        String s;
        for(Iterator iterator = mTxtRecord.keySet().iterator(); iterator.hasNext(); stringbuffer.append(" key:").append(s).append(" value:").append((String)mTxtRecord.get(s)))
            s = (String)iterator.next();

        if(mInstanceName != null)
            stringbuffer.append(" InsName:").append(mInstanceName);
        return stringbuffer.toString();
    }

    private static final Map sVmpack;
    private String mDnsQueryName;
    private int mDnsType;
    private String mInstanceName;
    private final HashMap mTxtRecord = new HashMap();
    private int mVersion;

    static 
    {
        sVmpack = new HashMap();
        sVmpack.put(Integer.valueOf(12), "_tcp.local.");
        sVmpack.put(Integer.valueOf(17), "local.");
        sVmpack.put(Integer.valueOf(28), "_udp.local.");
    }
}

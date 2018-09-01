// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server.net;

import android.net.LinkProperties;
import java.net.InetAddress;
import java.util.*;

// Referenced classes of package com.android.server.net:
//            DnsServerEntry

class DnsServerRepository
{

    public DnsServerRepository()
    {
        mCurrentServers = new HashSet();
        mAllServers = new ArrayList(12);
        mIndex = new HashMap(12);
    }

    private boolean updateCurrentServers()
    {
        this;
        JVM INSTR monitorenter ;
        long l = System.currentTimeMillis();
        boolean flag = false;
        int i = mAllServers.size() - 1;
_L2:
        if(i < 0)
            break; /* Loop/switch isn't completed */
        if(i >= 12)
            break MISSING_BLOCK_LABEL_51;
        if(((DnsServerEntry)mAllServers.get(i)).expiry >= l)
            break; /* Loop/switch isn't completed */
        DnsServerEntry dnsserverentry = (DnsServerEntry)mAllServers.remove(i);
        mIndex.remove(dnsserverentry.address);
        flag |= mCurrentServers.remove(dnsserverentry.address);
        i--;
        if(true) goto _L2; else goto _L1
_L1:
        Iterator iterator = mAllServers.iterator();
_L3:
        boolean flag1;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_171;
        DnsServerEntry dnsserverentry1 = (DnsServerEntry)iterator.next();
        if(mCurrentServers.size() >= 3)
            break MISSING_BLOCK_LABEL_171;
        flag1 = mCurrentServers.add(dnsserverentry1.address);
        flag |= flag1;
          goto _L3
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    private boolean updateExistingEntry(InetAddress inetaddress, long l)
    {
        this;
        JVM INSTR monitorenter ;
        inetaddress = (DnsServerEntry)mIndex.get(inetaddress);
        if(inetaddress == null)
            break MISSING_BLOCK_LABEL_27;
        inetaddress.expiry = l;
        this;
        JVM INSTR monitorexit ;
        return true;
        this;
        JVM INSTR monitorexit ;
        return false;
        inetaddress;
        throw inetaddress;
    }

    public boolean addServers(long l, String as[])
    {
        this;
        JVM INSTR monitorenter ;
        long l1 = System.currentTimeMillis();
        int i;
        l = l1 + 1000L * l;
        i = 0;
        int j = as.length;
_L2:
        Object obj;
        if(i >= j)
            break; /* Loop/switch isn't completed */
        obj = as[i];
        obj = InetAddress.parseNumericAddress(((String) (obj)));
        if(updateExistingEntry(((InetAddress) (obj)), l) || l <= l1)
            break MISSING_BLOCK_LABEL_95;
        DnsServerEntry dnsserverentry = JVM INSTR new #62  <Class DnsServerEntry>;
        dnsserverentry.DnsServerEntry(((InetAddress) (obj)), l);
        mAllServers.add(dnsserverentry);
        mIndex.put(obj, dnsserverentry);
_L3:
        i++;
        if(true) goto _L2; else goto _L1
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
          goto _L3
_L1:
        boolean flag;
        Collections.sort(mAllServers);
        flag = updateCurrentServers();
        this;
        JVM INSTR monitorexit ;
        return flag;
        as;
        throw as;
    }

    public void setDnsServersOn(LinkProperties linkproperties)
    {
        this;
        JVM INSTR monitorenter ;
        linkproperties.setDnsServers(mCurrentServers);
        this;
        JVM INSTR monitorexit ;
        return;
        linkproperties;
        throw linkproperties;
    }

    public static final int NUM_CURRENT_SERVERS = 3;
    public static final int NUM_SERVERS = 12;
    public static final String TAG = "DnsServerRepository";
    private ArrayList mAllServers;
    private Set mCurrentServers;
    private HashMap mIndex;
}

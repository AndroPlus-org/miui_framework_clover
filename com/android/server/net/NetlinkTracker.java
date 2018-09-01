// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server.net;

import android.net.*;
import java.util.Arrays;

// Referenced classes of package com.android.server.net:
//            BaseNetworkObserver, DnsServerRepository

public class NetlinkTracker extends BaseNetworkObserver
{
    public static interface Callback
    {

        public abstract void update();
    }


    public NetlinkTracker(String s, Callback callback)
    {
        TAG = (new StringBuilder()).append("NetlinkTracker/").append(s).toString();
        mInterfaceName = s;
        mCallback = callback;
        mLinkProperties.setInterfaceName(mInterfaceName);
        mDnsServerRepository = new DnsServerRepository();
    }

    private void maybeLog(String s, Object obj)
    {
    }

    private void maybeLog(String s, String s1, LinkAddress linkaddress)
    {
    }

    public void addressRemoved(String s, LinkAddress linkaddress)
    {
        if(!mInterfaceName.equals(s))
            break MISSING_BLOCK_LABEL_45;
        maybeLog("addressRemoved", s, linkaddress);
        this;
        JVM INSTR monitorenter ;
        boolean flag = mLinkProperties.removeLinkAddress(linkaddress);
        this;
        JVM INSTR monitorexit ;
        if(flag)
            mCallback.update();
        return;
        s;
        throw s;
    }

    public void addressUpdated(String s, LinkAddress linkaddress)
    {
        if(!mInterfaceName.equals(s))
            break MISSING_BLOCK_LABEL_45;
        maybeLog("addressUpdated", s, linkaddress);
        this;
        JVM INSTR monitorenter ;
        boolean flag = mLinkProperties.addLinkAddress(linkaddress);
        this;
        JVM INSTR monitorexit ;
        if(flag)
            mCallback.update();
        return;
        s;
        throw s;
    }

    public void clearLinkProperties()
    {
        this;
        JVM INSTR monitorenter ;
        DnsServerRepository dnsserverrepository = JVM INSTR new #54  <Class DnsServerRepository>;
        dnsserverrepository.DnsServerRepository();
        mDnsServerRepository = dnsserverrepository;
        mLinkProperties.clear();
        mLinkProperties.setInterfaceName(mInterfaceName);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public LinkProperties getLinkProperties()
    {
        this;
        JVM INSTR monitorenter ;
        LinkProperties linkproperties = new LinkProperties(mLinkProperties);
        this;
        JVM INSTR monitorexit ;
        return linkproperties;
        Exception exception;
        exception;
        throw exception;
    }

    public void interfaceDnsServerInfo(String s, long l, String as[])
    {
        if(!mInterfaceName.equals(s))
            break MISSING_BLOCK_LABEL_59;
        maybeLog("interfaceDnsServerInfo", Arrays.toString(as));
        if(!mDnsServerRepository.addServers(l, as))
            break MISSING_BLOCK_LABEL_59;
        this;
        JVM INSTR monitorenter ;
        mDnsServerRepository.setDnsServersOn(mLinkProperties);
        this;
        JVM INSTR monitorexit ;
        mCallback.update();
        return;
        s;
        throw s;
    }

    public void interfaceRemoved(String s)
    {
        maybeLog("interfaceRemoved", s);
        if(mInterfaceName.equals(s))
        {
            clearLinkProperties();
            mCallback.update();
        }
    }

    public void routeRemoved(RouteInfo routeinfo)
    {
        if(!mInterfaceName.equals(routeinfo.getInterface()))
            break MISSING_BLOCK_LABEL_47;
        maybeLog("routeRemoved", routeinfo);
        this;
        JVM INSTR monitorenter ;
        boolean flag = mLinkProperties.removeRoute(routeinfo);
        this;
        JVM INSTR monitorexit ;
        if(flag)
            mCallback.update();
        return;
        routeinfo;
        throw routeinfo;
    }

    public void routeUpdated(RouteInfo routeinfo)
    {
        if(!mInterfaceName.equals(routeinfo.getInterface()))
            break MISSING_BLOCK_LABEL_47;
        maybeLog("routeUpdated", routeinfo);
        this;
        JVM INSTR monitorenter ;
        boolean flag = mLinkProperties.addRoute(routeinfo);
        this;
        JVM INSTR monitorexit ;
        if(flag)
            mCallback.update();
        return;
        routeinfo;
        throw routeinfo;
    }

    private static final boolean DBG = false;
    private final String TAG;
    private final Callback mCallback;
    private DnsServerRepository mDnsServerRepository;
    private final String mInterfaceName;
    private final LinkProperties mLinkProperties = new LinkProperties();
}

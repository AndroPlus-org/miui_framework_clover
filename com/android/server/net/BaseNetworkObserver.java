// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server.net;

import android.net.LinkAddress;
import android.net.RouteInfo;

public class BaseNetworkObserver extends android.net.INetworkManagementEventObserver.Stub
{

    public BaseNetworkObserver()
    {
    }

    public void addressRemoved(String s, LinkAddress linkaddress)
    {
    }

    public void addressUpdated(String s, LinkAddress linkaddress)
    {
    }

    public void interfaceAdded(String s)
    {
    }

    public void interfaceClassDataActivityChanged(String s, boolean flag, long l)
    {
    }

    public void interfaceConfigurationLost()
    {
    }

    public void interfaceDnsServerInfo(String s, long l, String as[])
    {
    }

    public void interfaceLinkStateChanged(String s, boolean flag)
    {
    }

    public void interfaceRemoved(String s)
    {
    }

    public void interfaceStatusChanged(String s, boolean flag)
    {
    }

    public void limitReached(String s, String s1)
    {
    }

    public void routeRemoved(RouteInfo routeinfo)
    {
    }

    public void routeUpdated(RouteInfo routeinfo)
    {
    }
}

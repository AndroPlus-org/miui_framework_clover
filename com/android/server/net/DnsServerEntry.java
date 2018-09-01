// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server.net;

import java.net.InetAddress;

class DnsServerEntry
    implements Comparable
{

    public DnsServerEntry(InetAddress inetaddress, long l)
        throws IllegalArgumentException
    {
        address = inetaddress;
        expiry = l;
    }

    public int compareTo(DnsServerEntry dnsserverentry)
    {
        return Long.compare(dnsserverentry.expiry, expiry);
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((DnsServerEntry)obj);
    }

    public final InetAddress address;
    public long expiry;
}

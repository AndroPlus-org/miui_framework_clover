// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.net;

import android.app.PendingIntent;
import android.content.*;
import android.content.pm.*;
import android.content.res.Resources;
import android.net.*;
import android.os.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class VpnConfig
    implements Parcelable
{

    public VpnConfig()
    {
        mtu = -1;
        addresses = new ArrayList();
        routes = new ArrayList();
        startTime = -1L;
    }

    public static Intent getIntentForConfirmation()
    {
        Intent intent = new Intent();
        ComponentName componentname = ComponentName.unflattenFromString(Resources.getSystem().getString(0x1040125));
        intent.setClassName(componentname.getPackageName(), componentname.getClassName());
        return intent;
    }

    public static PendingIntent getIntentForStatusPanel(Context context)
    {
        Intent intent = new Intent();
        intent.setClassName("com.android.vpndialogs", "com.android.vpndialogs.ManageDialog");
        intent.addFlags(0x50800000);
        return PendingIntent.getActivityAsUser(context, 0, intent, 0, null, UserHandle.CURRENT);
    }

    public static CharSequence getVpnLabel(Context context, String s)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        context = context.getPackageManager();
        Object obj = new Intent("android.net.VpnService");
        ((Intent) (obj)).setPackage(s);
        obj = context.queryIntentServices(((Intent) (obj)), 0);
        if(obj != null && ((List) (obj)).size() == 1)
            return ((ResolveInfo)((List) (obj)).get(0)).loadLabel(context);
        else
            return context.getApplicationInfo(s, 0).loadLabel(context);
    }

    public void addLegacyAddresses(String s)
    {
        if(s.trim().equals(""))
            return;
        s = s.trim().split(" ");
        int i = 0;
        for(int j = s.length; i < j; i++)
        {
            LinkAddress linkaddress = new LinkAddress(s[i]);
            addresses.add(linkaddress);
            updateAllowedFamilies(linkaddress.getAddress());
        }

    }

    public void addLegacyRoutes(String s)
    {
        if(s.trim().equals(""))
            return;
        s = s.trim().split(" ");
        int i = 0;
        for(int j = s.length; i < j; i++)
        {
            RouteInfo routeinfo = new RouteInfo(new IpPrefix(s[i]), null);
            routes.add(routeinfo);
            updateAllowedFamilies(routeinfo.getDestination().getAddress());
        }

    }

    public int describeContents()
    {
        return 0;
    }

    public void updateAllowedFamilies(InetAddress inetaddress)
    {
        if(inetaddress instanceof Inet4Address)
            allowIPv4 = true;
        else
            allowIPv6 = true;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeString(user);
        parcel.writeString(interfaze);
        parcel.writeString(session);
        parcel.writeInt(mtu);
        parcel.writeTypedList(addresses);
        parcel.writeTypedList(routes);
        parcel.writeStringList(dnsServers);
        parcel.writeStringList(searchDomains);
        parcel.writeStringList(allowedApplications);
        parcel.writeStringList(disallowedApplications);
        parcel.writeParcelable(configureIntent, i);
        parcel.writeLong(startTime);
        int j;
        if(legacy)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(blocking)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(allowBypass)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(allowIPv4)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(allowIPv6)
            j = ((flag) ? 1 : 0);
        else
            j = 0;
        parcel.writeInt(j);
        parcel.writeTypedArray(underlyingNetworks, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public VpnConfig createFromParcel(Parcel parcel)
        {
            boolean flag = true;
            VpnConfig vpnconfig = new VpnConfig();
            vpnconfig.user = parcel.readString();
            vpnconfig.interfaze = parcel.readString();
            vpnconfig.session = parcel.readString();
            vpnconfig.mtu = parcel.readInt();
            parcel.readTypedList(vpnconfig.addresses, LinkAddress.CREATOR);
            parcel.readTypedList(vpnconfig.routes, RouteInfo.CREATOR);
            vpnconfig.dnsServers = parcel.createStringArrayList();
            vpnconfig.searchDomains = parcel.createStringArrayList();
            vpnconfig.allowedApplications = parcel.createStringArrayList();
            vpnconfig.disallowedApplications = parcel.createStringArrayList();
            vpnconfig.configureIntent = (PendingIntent)parcel.readParcelable(null);
            vpnconfig.startTime = parcel.readLong();
            boolean flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            vpnconfig.legacy = flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            vpnconfig.blocking = flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            vpnconfig.allowBypass = flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            vpnconfig.allowIPv4 = flag1;
            if(parcel.readInt() != 0)
                flag1 = flag;
            else
                flag1 = false;
            vpnconfig.allowIPv6 = flag1;
            vpnconfig.underlyingNetworks = (Network[])parcel.createTypedArray(Network.CREATOR);
            return vpnconfig;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public VpnConfig[] newArray(int i)
        {
            return new VpnConfig[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String DIALOGS_PACKAGE = "com.android.vpndialogs";
    public static final String LEGACY_VPN = "[Legacy VPN]";
    public static final String SERVICE_INTERFACE = "android.net.VpnService";
    public List addresses;
    public boolean allowBypass;
    public boolean allowIPv4;
    public boolean allowIPv6;
    public List allowedApplications;
    public boolean blocking;
    public PendingIntent configureIntent;
    public List disallowedApplications;
    public List dnsServers;
    public String interfaze;
    public boolean legacy;
    public int mtu;
    public List routes;
    public List searchDomains;
    public String session;
    public long startTime;
    public Network underlyingNetworks[];
    public String user;

}

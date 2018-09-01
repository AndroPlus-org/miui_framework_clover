// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.IPackageManager;
import android.os.*;
import android.system.OsConstants;
import com.android.internal.net.VpnConfig;
import java.io.FileDescriptor;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.net:
//            IConnectivityManager, NetworkUtils, Network, LinkAddress, 
//            RouteInfo, IpPrefix

public class VpnService extends Service
{
    public class Builder
    {

        private void verifyApp(String s)
            throws android.content.pm.PackageManager.NameNotFoundException
        {
            IPackageManager ipackagemanager = android.content.pm.IPackageManager.Stub.asInterface(ServiceManager.getService("package"));
            try
            {
                ipackagemanager.getApplicationInfo(s, 0, UserHandle.getCallingUserId());
                return;
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw new IllegalStateException(s);
            }
        }

        public Builder addAddress(String s, int i)
        {
            return addAddress(InetAddress.parseNumericAddress(s), i);
        }

        public Builder addAddress(InetAddress inetaddress, int i)
        {
            VpnService._2D_wrap1(inetaddress, i);
            if(inetaddress.isAnyLocalAddress())
            {
                throw new IllegalArgumentException("Bad address");
            } else
            {
                mAddresses.add(new LinkAddress(inetaddress, i));
                mConfig.updateAllowedFamilies(inetaddress);
                return this;
            }
        }

        public Builder addAllowedApplication(String s)
            throws android.content.pm.PackageManager.NameNotFoundException
        {
            if(mConfig.disallowedApplications != null)
                throw new UnsupportedOperationException("addDisallowedApplication already called");
            verifyApp(s);
            if(mConfig.allowedApplications == null)
                mConfig.allowedApplications = new ArrayList();
            mConfig.allowedApplications.add(s);
            return this;
        }

        public Builder addDisallowedApplication(String s)
            throws android.content.pm.PackageManager.NameNotFoundException
        {
            if(mConfig.allowedApplications != null)
                throw new UnsupportedOperationException("addAllowedApplication already called");
            verifyApp(s);
            if(mConfig.disallowedApplications == null)
                mConfig.disallowedApplications = new ArrayList();
            mConfig.disallowedApplications.add(s);
            return this;
        }

        public Builder addDnsServer(String s)
        {
            return addDnsServer(InetAddress.parseNumericAddress(s));
        }

        public Builder addDnsServer(InetAddress inetaddress)
        {
            if(inetaddress.isLoopbackAddress() || inetaddress.isAnyLocalAddress())
                throw new IllegalArgumentException("Bad address");
            if(mConfig.dnsServers == null)
                mConfig.dnsServers = new ArrayList();
            mConfig.dnsServers.add(inetaddress.getHostAddress());
            return this;
        }

        public Builder addRoute(String s, int i)
        {
            return addRoute(InetAddress.parseNumericAddress(s), i);
        }

        public Builder addRoute(InetAddress inetaddress, int i)
        {
            VpnService._2D_wrap1(inetaddress, i);
            int j = i / 8;
            byte abyte0[] = inetaddress.getAddress();
            if(j < abyte0.length)
            {
                abyte0[j] = (byte)(abyte0[j] << i % 8);
                for(; j < abyte0.length; j++)
                    if(abyte0[j] != 0)
                        throw new IllegalArgumentException("Bad address");

            }
            mRoutes.add(new RouteInfo(new IpPrefix(inetaddress, i), null));
            mConfig.updateAllowedFamilies(inetaddress);
            return this;
        }

        public Builder addSearchDomain(String s)
        {
            if(mConfig.searchDomains == null)
                mConfig.searchDomains = new ArrayList();
            mConfig.searchDomains.add(s);
            return this;
        }

        public Builder allowBypass()
        {
            mConfig.allowBypass = true;
            return this;
        }

        public Builder allowFamily(int i)
        {
            if(i == OsConstants.AF_INET)
                mConfig.allowIPv4 = true;
            else
            if(i == OsConstants.AF_INET6)
                mConfig.allowIPv6 = true;
            else
                throw new IllegalArgumentException((new StringBuilder()).append(i).append(" is neither ").append(OsConstants.AF_INET).append(" nor ").append(OsConstants.AF_INET6).toString());
            return this;
        }

        public ParcelFileDescriptor establish()
        {
            mConfig.addresses = mAddresses;
            mConfig.routes = mRoutes;
            ParcelFileDescriptor parcelfiledescriptor;
            try
            {
                parcelfiledescriptor = VpnService._2D_wrap0().establishVpn(mConfig);
            }
            catch(RemoteException remoteexception)
            {
                throw new IllegalStateException(remoteexception);
            }
            return parcelfiledescriptor;
        }

        public Builder setBlocking(boolean flag)
        {
            mConfig.blocking = flag;
            return this;
        }

        public Builder setConfigureIntent(PendingIntent pendingintent)
        {
            mConfig.configureIntent = pendingintent;
            return this;
        }

        public Builder setMtu(int i)
        {
            if(i <= 0)
            {
                throw new IllegalArgumentException("Bad mtu");
            } else
            {
                mConfig.mtu = i;
                return this;
            }
        }

        public Builder setSession(String s)
        {
            mConfig.session = s;
            return this;
        }

        public Builder setUnderlyingNetworks(Network anetwork[])
        {
            Network anetwork1[] = null;
            VpnConfig vpnconfig = mConfig;
            if(anetwork != null)
                anetwork1 = (Network[])anetwork.clone();
            vpnconfig.underlyingNetworks = anetwork1;
            return this;
        }

        private final List mAddresses = new ArrayList();
        private final VpnConfig mConfig = new VpnConfig();
        private final List mRoutes = new ArrayList();
        final VpnService this$0;

        public Builder()
        {
            this$0 = VpnService.this;
            super();
            mConfig.user = getClass().getName();
        }
    }

    private class Callback extends Binder
    {

        protected boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
        {
            if(i == 0xffffff)
            {
                onRevoke();
                return true;
            } else
            {
                return false;
            }
        }

        final VpnService this$0;

        private Callback()
        {
            this$0 = VpnService.this;
            super();
        }

        Callback(Callback callback)
        {
            this();
        }
    }


    static IConnectivityManager _2D_wrap0()
    {
        return getService();
    }

    static void _2D_wrap1(InetAddress inetaddress, int i)
    {
        check(inetaddress, i);
    }

    public VpnService()
    {
    }

    private static void check(InetAddress inetaddress, int i)
    {
        if(inetaddress.isLoopbackAddress())
            throw new IllegalArgumentException("Bad address");
        if(inetaddress instanceof Inet4Address)
        {
            if(i < 0 || i > 32)
                throw new IllegalArgumentException("Bad prefixLength");
        } else
        if(inetaddress instanceof Inet6Address)
        {
            if(i < 0 || i > 128)
                throw new IllegalArgumentException("Bad prefixLength");
        } else
        {
            throw new IllegalArgumentException("Unsupported family");
        }
    }

    private static IConnectivityManager getService()
    {
        return IConnectivityManager.Stub.asInterface(ServiceManager.getService("connectivity"));
    }

    public static Intent prepare(Context context)
    {
        boolean flag;
        try
        {
            flag = getService().prepareVpn(context.getPackageName(), null, UserHandle.myUserId());
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return VpnConfig.getIntentForConfirmation();
        }
label0:
        {
            if(flag)
                return null;
            break label0;
        }
    }

    public static void prepareAndAuthorize(Context context)
    {
        IConnectivityManager iconnectivitymanager;
        iconnectivitymanager = getService();
        context = context.getPackageName();
        int i = UserHandle.myUserId();
        if(!iconnectivitymanager.prepareVpn(context, null, i))
            iconnectivitymanager.prepareVpn(null, context, i);
        iconnectivitymanager.setVpnPackageAuthorization(context, i, true);
_L2:
        return;
        context;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean addAddress(InetAddress inetaddress, int i)
    {
        check(inetaddress, i);
        boolean flag;
        try
        {
            flag = getService().addVpnAddress(inetaddress.getHostAddress(), i);
        }
        // Misplaced declaration of an exception variable
        catch(InetAddress inetaddress)
        {
            throw new IllegalStateException(inetaddress);
        }
        return flag;
    }

    public IBinder onBind(Intent intent)
    {
        if(intent != null && "android.net.VpnService".equals(intent.getAction()))
            return new Callback(null);
        else
            return null;
    }

    public void onRevoke()
    {
        stopSelf();
    }

    public boolean protect(int i)
    {
        return NetworkUtils.protectFromVpn(i);
    }

    public boolean protect(DatagramSocket datagramsocket)
    {
        return protect(datagramsocket.getFileDescriptor$().getInt$());
    }

    public boolean protect(Socket socket)
    {
        return protect(socket.getFileDescriptor$().getInt$());
    }

    public boolean removeAddress(InetAddress inetaddress, int i)
    {
        check(inetaddress, i);
        boolean flag;
        try
        {
            flag = getService().removeVpnAddress(inetaddress.getHostAddress(), i);
        }
        // Misplaced declaration of an exception variable
        catch(InetAddress inetaddress)
        {
            throw new IllegalStateException(inetaddress);
        }
        return flag;
    }

    public boolean setUnderlyingNetworks(Network anetwork[])
    {
        boolean flag;
        try
        {
            flag = getService().setUnderlyingNetworksForVpn(anetwork);
        }
        // Misplaced declaration of an exception variable
        catch(Network anetwork[])
        {
            throw new IllegalStateException(anetwork);
        }
        return flag;
    }

    public static final String SERVICE_INTERFACE = "android.net.VpnService";
    public static final String SERVICE_META_DATA_SUPPORTS_ALWAYS_ON = "android.net.VpnService.SUPPORTS_ALWAYS_ON";
}

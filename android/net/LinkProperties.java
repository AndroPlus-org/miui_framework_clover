// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.net.*;
import java.util.*;

// Referenced classes of package android.net:
//            LinkAddress, RouteInfo, ProxyInfo

public final class LinkProperties
    implements Parcelable
{
    public static class CompareResult
    {

        public String toString()
        {
            String s = "removed=[";
            for(Iterator iterator = removed.iterator(); iterator.hasNext();)
            {
                Object obj = iterator.next();
                s = (new StringBuilder()).append(s).append(obj.toString()).append(",").toString();
            }

            s = (new StringBuilder()).append(s).append("] added=[").toString();
            for(Iterator iterator1 = added.iterator(); iterator1.hasNext();)
            {
                Object obj1 = iterator1.next();
                s = (new StringBuilder()).append(s).append(obj1.toString()).append(",").toString();
            }

            return (new StringBuilder()).append(s).append("]").toString();
        }

        public List added;
        public List removed;

        public CompareResult()
        {
            removed = new ArrayList();
            added = new ArrayList();
        }
    }

    public static final class ProvisioningChange extends Enum
    {

        public static ProvisioningChange valueOf(String s)
        {
            return (ProvisioningChange)Enum.valueOf(android/net/LinkProperties$ProvisioningChange, s);
        }

        public static ProvisioningChange[] values()
        {
            return $VALUES;
        }

        private static final ProvisioningChange $VALUES[];
        public static final ProvisioningChange GAINED_PROVISIONING;
        public static final ProvisioningChange LOST_PROVISIONING;
        public static final ProvisioningChange STILL_NOT_PROVISIONED;
        public static final ProvisioningChange STILL_PROVISIONED;

        static 
        {
            STILL_NOT_PROVISIONED = new ProvisioningChange("STILL_NOT_PROVISIONED", 0);
            LOST_PROVISIONING = new ProvisioningChange("LOST_PROVISIONING", 1);
            GAINED_PROVISIONING = new ProvisioningChange("GAINED_PROVISIONING", 2);
            STILL_PROVISIONED = new ProvisioningChange("STILL_PROVISIONED", 3);
            $VALUES = (new ProvisioningChange[] {
                STILL_NOT_PROVISIONED, LOST_PROVISIONING, GAINED_PROVISIONING, STILL_PROVISIONED
            });
        }

        private ProvisioningChange(String s, int i)
        {
            super(s, i);
        }
    }


    public LinkProperties()
    {
        mLinkAddresses = new ArrayList();
        mDnses = new ArrayList();
        mRoutes = new ArrayList();
        mStackedLinks = new Hashtable();
    }

    public LinkProperties(LinkProperties linkproperties)
    {
        Object obj = null;
        super();
        mLinkAddresses = new ArrayList();
        mDnses = new ArrayList();
        mRoutes = new ArrayList();
        mStackedLinks = new Hashtable();
        if(linkproperties != null)
        {
            mIfaceName = linkproperties.getInterfaceName();
            LinkAddress linkaddress;
            for(Iterator iterator = linkproperties.getLinkAddresses().iterator(); iterator.hasNext(); mLinkAddresses.add(linkaddress))
                linkaddress = (LinkAddress)iterator.next();

            InetAddress inetaddress;
            for(Iterator iterator1 = linkproperties.getDnsServers().iterator(); iterator1.hasNext(); mDnses.add(inetaddress))
                inetaddress = (InetAddress)iterator1.next();

            mDomains = linkproperties.getDomains();
            RouteInfo routeinfo;
            for(Iterator iterator2 = linkproperties.getRoutes().iterator(); iterator2.hasNext(); mRoutes.add(routeinfo))
                routeinfo = (RouteInfo)iterator2.next();

            if(linkproperties.getHttpProxy() != null)
                obj = new ProxyInfo(linkproperties.getHttpProxy());
            mHttpProxy = ((ProxyInfo) (obj));
            for(obj = linkproperties.mStackedLinks.values().iterator(); ((Iterator) (obj)).hasNext(); addStackedLink((LinkProperties)((Iterator) (obj)).next()));
            setMtu(linkproperties.getMtu());
            mTcpBufferSizes = linkproperties.mTcpBufferSizes;
        }
    }

    public static ProvisioningChange compareProvisioning(LinkProperties linkproperties, LinkProperties linkproperties1)
    {
        if(linkproperties.isProvisioned() && linkproperties1.isProvisioned())
            if(linkproperties.isIPv4Provisioned() && linkproperties1.isIPv4Provisioned() ^ true || linkproperties.isIPv6Provisioned() && linkproperties1.isIPv6Provisioned() ^ true)
                return ProvisioningChange.LOST_PROVISIONING;
            else
                return ProvisioningChange.STILL_PROVISIONED;
        if(linkproperties.isProvisioned() && linkproperties1.isProvisioned() ^ true)
            return ProvisioningChange.LOST_PROVISIONING;
        if(!linkproperties.isProvisioned() && linkproperties1.isProvisioned())
            return ProvisioningChange.GAINED_PROVISIONING;
        else
            return ProvisioningChange.STILL_NOT_PROVISIONED;
    }

    private int findLinkAddressIndex(LinkAddress linkaddress)
    {
        for(int i = 0; i < mLinkAddresses.size(); i++)
            if(((LinkAddress)mLinkAddresses.get(i)).isSameAddressAs(linkaddress))
                return i;

        return -1;
    }

    private boolean hasIPv4AddressOnInterface(String s)
    {
        boolean flag;
        if(!Objects.equals(s, mIfaceName) || !hasIPv4Address())
        {
            if(s != null && mStackedLinks.containsKey(s))
                flag = ((LinkProperties)mStackedLinks.get(s)).hasIPv4Address();
            else
                flag = false;
        } else
        {
            flag = true;
        }
        return flag;
    }

    public static boolean isValidMtu(int i, boolean flag)
    {
        if(flag)
        {
            if(i >= 1280 && i <= 10000)
                return true;
        } else
        if(i >= 68 && i <= 10000)
            return true;
        return false;
    }

    private RouteInfo routeWithInterface(RouteInfo routeinfo)
    {
        return new RouteInfo(routeinfo.getDestination(), routeinfo.getGateway(), mIfaceName, routeinfo.getType());
    }

    public boolean addDnsServer(InetAddress inetaddress)
    {
        if(inetaddress != null && mDnses.contains(inetaddress) ^ true)
        {
            mDnses.add(inetaddress);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean addLinkAddress(LinkAddress linkaddress)
    {
        if(linkaddress == null)
            return false;
        int i = findLinkAddressIndex(linkaddress);
        if(i < 0)
        {
            mLinkAddresses.add(linkaddress);
            return true;
        }
        if(((LinkAddress)mLinkAddresses.get(i)).equals(linkaddress))
        {
            return false;
        } else
        {
            mLinkAddresses.set(i, linkaddress);
            return true;
        }
    }

    public boolean addRoute(RouteInfo routeinfo)
    {
        if(routeinfo != null)
        {
            String s = routeinfo.getInterface();
            if(s != null && s.equals(mIfaceName) ^ true)
                throw new IllegalArgumentException((new StringBuilder()).append("Route added with non-matching interface: ").append(s).append(" vs. ").append(mIfaceName).toString());
            routeinfo = routeWithInterface(routeinfo);
            if(!mRoutes.contains(routeinfo))
            {
                mRoutes.add(routeinfo);
                return true;
            }
        }
        return false;
    }

    public boolean addStackedLink(LinkProperties linkproperties)
    {
        if(linkproperties != null && linkproperties.getInterfaceName() != null)
        {
            mStackedLinks.put(linkproperties.getInterfaceName(), linkproperties);
            return true;
        } else
        {
            return false;
        }
    }

    public void clear()
    {
        mIfaceName = null;
        mLinkAddresses.clear();
        mDnses.clear();
        mDomains = null;
        mRoutes.clear();
        mHttpProxy = null;
        mStackedLinks.clear();
        mMtu = 0;
        mTcpBufferSizes = null;
    }

    public CompareResult compareAddresses(LinkProperties linkproperties)
    {
        CompareResult compareresult = new CompareResult();
        compareresult.removed = new ArrayList(mLinkAddresses);
        compareresult.added.clear();
        if(linkproperties != null)
        {
            linkproperties = linkproperties.getLinkAddresses().iterator();
            do
            {
                if(!linkproperties.hasNext())
                    break;
                LinkAddress linkaddress = (LinkAddress)linkproperties.next();
                if(!compareresult.removed.remove(linkaddress))
                    compareresult.added.add(linkaddress);
            } while(true);
        }
        return compareresult;
    }

    public CompareResult compareAllInterfaceNames(LinkProperties linkproperties)
    {
        CompareResult compareresult = new CompareResult();
        compareresult.removed = getAllInterfaceNames();
        compareresult.added.clear();
        if(linkproperties != null)
        {
            linkproperties = linkproperties.getAllInterfaceNames().iterator();
            do
            {
                if(!linkproperties.hasNext())
                    break;
                String s = (String)linkproperties.next();
                if(!compareresult.removed.remove(s))
                    compareresult.added.add(s);
            } while(true);
        }
        return compareresult;
    }

    public CompareResult compareAllRoutes(LinkProperties linkproperties)
    {
        CompareResult compareresult = new CompareResult();
        compareresult.removed = getAllRoutes();
        compareresult.added.clear();
        if(linkproperties != null)
        {
            Iterator iterator = linkproperties.getAllRoutes().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                linkproperties = (RouteInfo)iterator.next();
                if(!compareresult.removed.remove(linkproperties))
                    compareresult.added.add(linkproperties);
            } while(true);
        }
        return compareresult;
    }

    public CompareResult compareDnses(LinkProperties linkproperties)
    {
        CompareResult compareresult = new CompareResult();
        compareresult.removed = new ArrayList(mDnses);
        compareresult.added.clear();
        if(linkproperties != null)
        {
            linkproperties = linkproperties.getDnsServers().iterator();
            do
            {
                if(!linkproperties.hasNext())
                    break;
                InetAddress inetaddress = (InetAddress)linkproperties.next();
                if(!compareresult.removed.remove(inetaddress))
                    compareresult.added.add(inetaddress);
            } while(true);
        }
        return compareresult;
    }

    public int describeContents()
    {
        return 0;
    }

    public void ensureDirectlyConnectedRoutes()
    {
        for(Iterator iterator = mLinkAddresses.iterator(); iterator.hasNext(); addRoute(new RouteInfo((LinkAddress)iterator.next(), null, mIfaceName)));
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(this == obj)
            return true;
        if(!(obj instanceof LinkProperties))
            return false;
        obj = (LinkProperties)obj;
        boolean flag1 = flag;
        if(isIdenticalInterfaceName(((LinkProperties) (obj))))
        {
            flag1 = flag;
            if(isIdenticalAddresses(((LinkProperties) (obj))))
            {
                flag1 = flag;
                if(isIdenticalDnses(((LinkProperties) (obj))))
                {
                    flag1 = flag;
                    if(isIdenticalRoutes(((LinkProperties) (obj))))
                    {
                        flag1 = flag;
                        if(isIdenticalHttpProxy(((LinkProperties) (obj))))
                        {
                            flag1 = flag;
                            if(isIdenticalStackedLinks(((LinkProperties) (obj))))
                            {
                                flag1 = flag;
                                if(isIdenticalMtu(((LinkProperties) (obj))))
                                    flag1 = isIdenticalTcpBufferSizes(((LinkProperties) (obj)));
                            }
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public List getAddresses()
    {
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = mLinkAddresses.iterator(); iterator.hasNext(); arraylist.add(((LinkAddress)iterator.next()).getAddress()));
        return Collections.unmodifiableList(arraylist);
    }

    public List getAllAddresses()
    {
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = mLinkAddresses.iterator(); iterator.hasNext(); arraylist.add(((LinkAddress)iterator.next()).getAddress()));
        for(Iterator iterator1 = mStackedLinks.values().iterator(); iterator1.hasNext(); arraylist.addAll(((LinkProperties)iterator1.next()).getAllAddresses()));
        return arraylist;
    }

    public List getAllInterfaceNames()
    {
        ArrayList arraylist = new ArrayList(mStackedLinks.size() + 1);
        if(mIfaceName != null)
            arraylist.add(new String(mIfaceName));
        for(Iterator iterator = mStackedLinks.values().iterator(); iterator.hasNext(); arraylist.addAll(((LinkProperties)iterator.next()).getAllInterfaceNames()));
        return arraylist;
    }

    public List getAllLinkAddresses()
    {
        ArrayList arraylist = new ArrayList();
        arraylist.addAll(mLinkAddresses);
        for(Iterator iterator = mStackedLinks.values().iterator(); iterator.hasNext(); arraylist.addAll(((LinkProperties)iterator.next()).getAllLinkAddresses()));
        return arraylist;
    }

    public List getAllRoutes()
    {
        ArrayList arraylist = new ArrayList();
        arraylist.addAll(mRoutes);
        for(Iterator iterator = mStackedLinks.values().iterator(); iterator.hasNext(); arraylist.addAll(((LinkProperties)iterator.next()).getAllRoutes()));
        return arraylist;
    }

    public List getDnsServers()
    {
        return Collections.unmodifiableList(mDnses);
    }

    public String getDomains()
    {
        return mDomains;
    }

    public ProxyInfo getHttpProxy()
    {
        return mHttpProxy;
    }

    public String getInterfaceName()
    {
        return mIfaceName;
    }

    public List getLinkAddresses()
    {
        return Collections.unmodifiableList(mLinkAddresses);
    }

    public int getMtu()
    {
        return mMtu;
    }

    public List getRoutes()
    {
        return Collections.unmodifiableList(mRoutes);
    }

    public List getStackedLinks()
    {
        if(mStackedLinks.isEmpty())
            return Collections.EMPTY_LIST;
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = mStackedLinks.values().iterator(); iterator.hasNext(); arraylist.add(new LinkProperties((LinkProperties)iterator.next())));
        return Collections.unmodifiableList(arraylist);
    }

    public String getTcpBufferSizes()
    {
        return mTcpBufferSizes;
    }

    public boolean hasGlobalIPv6Address()
    {
        for(Iterator iterator = mLinkAddresses.iterator(); iterator.hasNext();)
        {
            LinkAddress linkaddress = (LinkAddress)iterator.next();
            if((linkaddress.getAddress() instanceof Inet6Address) && linkaddress.isGlobalPreferred())
                return true;
        }

        return false;
    }

    public boolean hasIPv4Address()
    {
        for(Iterator iterator = mLinkAddresses.iterator(); iterator.hasNext();)
            if(((LinkAddress)iterator.next()).getAddress() instanceof Inet4Address)
                return true;

        return false;
    }

    public boolean hasIPv4DefaultRoute()
    {
        for(Iterator iterator = mRoutes.iterator(); iterator.hasNext();)
            if(((RouteInfo)iterator.next()).isIPv4Default())
                return true;

        return false;
    }

    public boolean hasIPv4DnsServer()
    {
        for(Iterator iterator = mDnses.iterator(); iterator.hasNext();)
            if((InetAddress)iterator.next() instanceof Inet4Address)
                return true;

        return false;
    }

    public boolean hasIPv6DefaultRoute()
    {
        for(Iterator iterator = mRoutes.iterator(); iterator.hasNext();)
            if(((RouteInfo)iterator.next()).isIPv6Default())
                return true;

        return false;
    }

    public boolean hasIPv6DnsServer()
    {
        for(Iterator iterator = mDnses.iterator(); iterator.hasNext();)
            if((InetAddress)iterator.next() instanceof Inet6Address)
                return true;

        return false;
    }

    public int hashCode()
    {
        boolean flag = false;
        int i;
        int j;
        int l;
        if(mIfaceName == null)
        {
            i = 0;
        } else
        {
            int i1 = mIfaceName.hashCode();
            int k = mLinkAddresses.size();
            int j1 = mDnses.size();
            int k1;
            if(mDomains == null)
                i = 0;
            else
                i = mDomains.hashCode();
            k1 = mRoutes.size();
            if(mHttpProxy == null)
                l = 0;
            else
                l = mHttpProxy.hashCode();
            i = l + (k1 * 41 + (i + (j1 * 37 + (i1 + k * 31)))) + mStackedLinks.hashCode() * 47;
        }
        j = mMtu;
        if(mTcpBufferSizes == null)
            l = ((flag) ? 1 : 0);
        else
            l = mTcpBufferSizes.hashCode();
        return i + j * 51 + l;
    }

    public boolean isIPv4Provisioned()
    {
        boolean flag;
        if(hasIPv4Address() && hasIPv4DefaultRoute())
            flag = hasIPv4DnsServer();
        else
            flag = false;
        return flag;
    }

    public boolean isIPv6Provisioned()
    {
        boolean flag;
        if(hasGlobalIPv6Address() && hasIPv6DefaultRoute())
            flag = hasIPv6DnsServer();
        else
            flag = false;
        return flag;
    }

    public boolean isIdenticalAddresses(LinkProperties linkproperties)
    {
        linkproperties = linkproperties.getAddresses();
        List list = getAddresses();
        boolean flag;
        if(list.size() == linkproperties.size())
            flag = list.containsAll(linkproperties);
        else
            flag = false;
        return flag;
    }

    public boolean isIdenticalDnses(LinkProperties linkproperties)
    {
        boolean flag = false;
        List list = linkproperties.getDnsServers();
        linkproperties = linkproperties.getDomains();
        if(mDomains == null)
        {
            if(linkproperties != null)
                return false;
        } else
        if(!mDomains.equals(linkproperties))
            return false;
        if(mDnses.size() == list.size())
            flag = mDnses.containsAll(list);
        return flag;
    }

    public boolean isIdenticalHttpProxy(LinkProperties linkproperties)
    {
        boolean flag;
        if(getHttpProxy() == null)
        {
            if(linkproperties.getHttpProxy() == null)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = getHttpProxy().equals(linkproperties.getHttpProxy());
        }
        return flag;
    }

    public boolean isIdenticalInterfaceName(LinkProperties linkproperties)
    {
        return TextUtils.equals(getInterfaceName(), linkproperties.getInterfaceName());
    }

    public boolean isIdenticalMtu(LinkProperties linkproperties)
    {
        boolean flag;
        if(getMtu() == linkproperties.getMtu())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isIdenticalRoutes(LinkProperties linkproperties)
    {
        linkproperties = linkproperties.getRoutes();
        boolean flag;
        if(mRoutes.size() == linkproperties.size())
            flag = mRoutes.containsAll(linkproperties);
        else
            flag = false;
        return flag;
    }

    public boolean isIdenticalStackedLinks(LinkProperties linkproperties)
    {
        if(!mStackedLinks.keySet().equals(linkproperties.mStackedLinks.keySet()))
            return false;
        for(Iterator iterator = mStackedLinks.values().iterator(); iterator.hasNext();)
        {
            LinkProperties linkproperties1 = (LinkProperties)iterator.next();
            String s = linkproperties1.getInterfaceName();
            if(!linkproperties1.equals(linkproperties.mStackedLinks.get(s)))
                return false;
        }

        return true;
    }

    public boolean isIdenticalTcpBufferSizes(LinkProperties linkproperties)
    {
        return Objects.equals(mTcpBufferSizes, linkproperties.mTcpBufferSizes);
    }

    public boolean isProvisioned()
    {
        boolean flag;
        if(!isIPv4Provisioned())
            flag = isIPv6Provisioned();
        else
            flag = true;
        return flag;
    }

    public boolean isReachable(InetAddress inetaddress)
    {
        boolean flag = true;
        boolean flag1 = true;
        RouteInfo routeinfo = RouteInfo.selectBestRoute(getAllRoutes(), inetaddress);
        if(routeinfo == null)
            return false;
        if(inetaddress instanceof Inet4Address)
            return hasIPv4AddressOnInterface(routeinfo.getInterface());
        if(inetaddress instanceof Inet6Address)
        {
            if(inetaddress.isLinkLocalAddress())
            {
                if(((Inet6Address)inetaddress).getScopeId() == 0)
                    flag1 = false;
                return flag1;
            }
            flag1 = flag;
            if(routeinfo.hasGateway())
                flag1 = hasGlobalIPv6Address();
            return flag1;
        } else
        {
            return false;
        }
    }

    public boolean removeDnsServer(InetAddress inetaddress)
    {
        if(inetaddress != null)
            return mDnses.remove(inetaddress);
        else
            return false;
    }

    public boolean removeLinkAddress(LinkAddress linkaddress)
    {
        int i = findLinkAddressIndex(linkaddress);
        if(i >= 0)
        {
            mLinkAddresses.remove(i);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean removeRoute(RouteInfo routeinfo)
    {
        boolean flag;
        if(routeinfo != null && Objects.equals(mIfaceName, routeinfo.getInterface()))
            flag = mRoutes.remove(routeinfo);
        else
            flag = false;
        return flag;
    }

    public boolean removeStackedLink(String s)
    {
        boolean flag = false;
        if(s != null)
        {
            if((LinkProperties)mStackedLinks.remove(s) != null)
                flag = true;
            return flag;
        } else
        {
            return false;
        }
    }

    public void setDnsServers(Collection collection)
    {
        mDnses.clear();
        for(collection = collection.iterator(); collection.hasNext(); addDnsServer((InetAddress)collection.next()));
    }

    public void setDomains(String s)
    {
        mDomains = s;
    }

    public void setHttpProxy(ProxyInfo proxyinfo)
    {
        mHttpProxy = proxyinfo;
    }

    public void setInterfaceName(String s)
    {
        mIfaceName = s;
        ArrayList arraylist = new ArrayList(mRoutes.size());
        for(s = mRoutes.iterator(); s.hasNext(); arraylist.add(routeWithInterface((RouteInfo)s.next())));
        mRoutes = arraylist;
    }

    public void setLinkAddresses(Collection collection)
    {
        mLinkAddresses.clear();
        for(collection = collection.iterator(); collection.hasNext(); addLinkAddress((LinkAddress)collection.next()));
    }

    public void setMtu(int i)
    {
        mMtu = i;
    }

    public void setTcpBufferSizes(String s)
    {
        mTcpBufferSizes = s;
    }

    public String toString()
    {
        String s;
        String s1;
        if(mIfaceName == null)
            s = "";
        else
            s = (new StringBuilder()).append("InterfaceName: ").append(mIfaceName).append(" ").toString();
        s1 = "LinkAddresses: [";
        for(Iterator iterator = mLinkAddresses.iterator(); iterator.hasNext();)
        {
            LinkAddress linkaddress = (LinkAddress)iterator.next();
            s1 = (new StringBuilder()).append(s1).append(linkaddress.toString()).append(",").toString();
        }

        String s4 = (new StringBuilder()).append(s1).append("] ").toString();
        s1 = "DnsAddresses: [";
        for(Iterator iterator1 = mDnses.iterator(); iterator1.hasNext();)
        {
            InetAddress inetaddress = (InetAddress)iterator1.next();
            s1 = (new StringBuilder()).append(s1).append(inetaddress.getHostAddress()).append(",").toString();
        }

        String s5 = (new StringBuilder()).append(s1).append("] ").toString();
        String s6 = (new StringBuilder()).append("Domains: ").append(mDomains).toString();
        String s7 = (new StringBuilder()).append(" MTU: ").append(mMtu).toString();
        s1 = "";
        if(mTcpBufferSizes != null)
            s1 = (new StringBuilder()).append(" TcpBufferSizes: ").append(mTcpBufferSizes).toString();
        String s2 = " Routes: [";
        for(Iterator iterator2 = mRoutes.iterator(); iterator2.hasNext();)
        {
            RouteInfo routeinfo = (RouteInfo)iterator2.next();
            s2 = (new StringBuilder()).append(s2).append(routeinfo.toString()).append(",").toString();
        }

        String s8 = (new StringBuilder()).append(s2).append("] ").toString();
        String s3;
        if(mHttpProxy == null)
            s2 = "";
        else
            s2 = (new StringBuilder()).append(" HttpProxy: ").append(mHttpProxy.toString()).append(" ").toString();
        s3 = "";
        if(mStackedLinks.values().size() > 0)
        {
            s3 = (new StringBuilder()).append("").append(" Stacked: [").toString();
            for(Iterator iterator3 = mStackedLinks.values().iterator(); iterator3.hasNext();)
            {
                LinkProperties linkproperties = (LinkProperties)iterator3.next();
                s3 = (new StringBuilder()).append(s3).append(" [").append(linkproperties.toString()).append(" ],").toString();
            }

            s3 = (new StringBuilder()).append(s3).append("] ").toString();
        }
        return (new StringBuilder()).append("{").append(s).append(s4).append(s8).append(s5).append(s6).append(s7).append(s1).append(s2).append(s3).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(getInterfaceName());
        parcel.writeInt(mLinkAddresses.size());
        for(Iterator iterator = mLinkAddresses.iterator(); iterator.hasNext(); parcel.writeParcelable((LinkAddress)iterator.next(), i));
        parcel.writeInt(mDnses.size());
        for(Iterator iterator1 = mDnses.iterator(); iterator1.hasNext(); parcel.writeByteArray(((InetAddress)iterator1.next()).getAddress()));
        parcel.writeString(mDomains);
        parcel.writeInt(mMtu);
        parcel.writeString(mTcpBufferSizes);
        parcel.writeInt(mRoutes.size());
        for(Iterator iterator2 = mRoutes.iterator(); iterator2.hasNext(); parcel.writeParcelable((RouteInfo)iterator2.next(), i));
        if(mHttpProxy != null)
        {
            parcel.writeByte((byte)1);
            parcel.writeParcelable(mHttpProxy, i);
        } else
        {
            parcel.writeByte((byte)0);
        }
        parcel.writeList(new ArrayList(mStackedLinks.values()));
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public LinkProperties createFromParcel(Parcel parcel)
        {
            LinkProperties linkproperties = new LinkProperties();
            String s = parcel.readString();
            if(s != null)
                linkproperties.setInterfaceName(s);
            int i = parcel.readInt();
            for(int k = 0; k < i; k++)
                linkproperties.addLinkAddress((LinkAddress)parcel.readParcelable(null));

            i = parcel.readInt();
            int l = 0;
            while(l < i) 
            {
                ArrayList arraylist;
                int j;
                try
                {
                    linkproperties.addDnsServer(InetAddress.getByAddress(parcel.createByteArray()));
                }
                catch(UnknownHostException unknownhostexception) { }
                l++;
            }
            linkproperties.setDomains(parcel.readString());
            linkproperties.setMtu(parcel.readInt());
            linkproperties.setTcpBufferSizes(parcel.readString());
            j = parcel.readInt();
            for(l = 0; l < j; l++)
                linkproperties.addRoute((RouteInfo)parcel.readParcelable(null));

            if(parcel.readByte() == 1)
                linkproperties.setHttpProxy((ProxyInfo)parcel.readParcelable(null));
            arraylist = new ArrayList();
            parcel.readList(arraylist, android/net/LinkProperties.getClassLoader());
            for(parcel = arraylist.iterator(); parcel.hasNext(); linkproperties.addStackedLink((LinkProperties)parcel.next()));
            return linkproperties;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public LinkProperties[] newArray(int i)
        {
            return new LinkProperties[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int MAX_MTU = 10000;
    private static final int MIN_MTU = 68;
    private static final int MIN_MTU_V6 = 1280;
    private ArrayList mDnses;
    private String mDomains;
    private ProxyInfo mHttpProxy;
    private String mIfaceName;
    private ArrayList mLinkAddresses;
    private int mMtu;
    private ArrayList mRoutes;
    private Hashtable mStackedLinks;
    private String mTcpBufferSizes;

}

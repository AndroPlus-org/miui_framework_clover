// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.hotspot2.omadm;

import android.net.wifi.hotspot2.PasspointConfiguration;
import android.net.wifi.hotspot2.pps.*;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import java.io.IOException;
import java.text.*;
import java.util.*;
import org.xml.sax.SAXException;

// Referenced classes of package android.net.wifi.hotspot2.omadm:
//            XMLNode, XMLParser

public final class PpsMoParser
{
    private static class InternalNode extends PPSNode
    {

        public List getChildren()
        {
            return mChildren;
        }

        public String getValue()
        {
            return null;
        }

        public boolean isLeaf()
        {
            return false;
        }

        private final List mChildren;

        public InternalNode(String s, List list)
        {
            super(s);
            mChildren = list;
        }
    }

    private static class LeafNode extends PPSNode
    {

        public List getChildren()
        {
            return null;
        }

        public String getValue()
        {
            return mValue;
        }

        public boolean isLeaf()
        {
            return true;
        }

        private final String mValue;

        public LeafNode(String s, String s1)
        {
            super(s);
            mValue = s1;
        }
    }

    private static abstract class PPSNode
    {

        public abstract List getChildren();

        public String getName()
        {
            return mName;
        }

        public abstract String getValue();

        public abstract boolean isLeaf();

        private final String mName;

        public PPSNode(String s)
        {
            mName = s;
        }
    }

    private static class ParsingException extends Exception
    {

        public ParsingException(String s)
        {
            super(s);
        }
    }


    public PpsMoParser()
    {
    }

    private static PPSNode buildPpsNode(XMLNode xmlnode)
        throws ParsingException
    {
        String s = null;
        Object obj = null;
        ArrayList arraylist = new ArrayList();
        HashSet hashset = new HashSet();
        Iterator iterator = xmlnode.getChildren().iterator();
        xmlnode = obj;
        while(iterator.hasNext()) 
        {
            Object obj1 = (XMLNode)iterator.next();
            String s1 = ((XMLNode) (obj1)).getTag();
            if(TextUtils.equals(s1, "NodeName"))
            {
                if(s != null)
                    throw new ParsingException("Duplicate NodeName node");
                s = ((XMLNode) (obj1)).getText();
            } else
            if(TextUtils.equals(s1, "Node"))
            {
                obj1 = buildPpsNode(((XMLNode) (obj1)));
                if(hashset.contains(((PPSNode) (obj1)).getName()))
                    throw new ParsingException((new StringBuilder()).append("Duplicate node: ").append(((PPSNode) (obj1)).getName()).toString());
                hashset.add(((PPSNode) (obj1)).getName());
                arraylist.add(obj1);
            } else
            if(TextUtils.equals(s1, "Value"))
            {
                if(xmlnode != null)
                    throw new ParsingException("Duplicate Value node");
                xmlnode = ((XMLNode) (obj1)).getText();
            } else
            {
                throw new ParsingException((new StringBuilder()).append("Unknown tag: ").append(s1).toString());
            }
        }
        if(s == null)
            throw new ParsingException("Invalid node: missing NodeName");
        if(xmlnode == null && arraylist.size() == 0)
            throw new ParsingException((new StringBuilder()).append("Invalid node: ").append(s).append(" missing both value and children").toString());
        if(xmlnode != null && arraylist.size() > 0)
            throw new ParsingException((new StringBuilder()).append("Invalid node: ").append(s).append(" contained both value and children").toString());
        if(xmlnode != null)
            return new LeafNode(s, xmlnode);
        else
            return new InternalNode(s, arraylist);
    }

    private static long[] convertFromLongList(List list)
    {
        Long along[] = (Long[])list.toArray(new Long[list.size()]);
        list = new long[along.length];
        for(int i = 0; i < along.length; i++)
            list[i] = along[i].longValue();

        return list;
    }

    private static String getPpsNodeValue(PPSNode ppsnode)
        throws ParsingException
    {
        if(!ppsnode.isLeaf())
            throw new ParsingException((new StringBuilder()).append("Cannot get value from a non-leaf node: ").append(ppsnode.getName()).toString());
        else
            return ppsnode.getValue();
    }

    private static Map parseAAAServerTrustRootList(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for AAAServerTrustRoot");
        HashMap hashmap = new HashMap();
        Pair pair;
        for(ppsnode = ppsnode.getChildren().iterator(); ppsnode.hasNext(); hashmap.put((String)pair.first, (byte[])pair.second))
            pair = parseTrustRoot((PPSNode)ppsnode.next());

        return hashmap;
    }

    private static android.net.wifi.hotspot2.pps.Credential.CertificateCredential parseCertificateCredential(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for DigitalCertificate");
        android.net.wifi.hotspot2.pps.Credential.CertificateCredential certificatecredential = new android.net.wifi.hotspot2.pps.Credential.CertificateCredential();
        for(Iterator iterator = ppsnode.getChildren().iterator(); iterator.hasNext();)
        {
            PPSNode ppsnode1 = (PPSNode)iterator.next();
            ppsnode = ppsnode1.getName();
            if(ppsnode.equals("CertificateType"))
                certificatecredential.setCertType(getPpsNodeValue(ppsnode1));
            else
            if(ppsnode.equals("CertSHA256Fingerprint"))
                certificatecredential.setCertSha256Fingerprint(parseHexString(getPpsNodeValue(ppsnode1)));
            else
                throw new ParsingException((new StringBuilder()).append("Unknown node under DigitalCertificate: ").append(ppsnode1.getName()).toString());
        }

        return certificatecredential;
    }

    private static Credential parseCredential(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for HomeSP");
        Credential credential = new Credential();
        for(ppsnode = ppsnode.getChildren().iterator(); ppsnode.hasNext();)
        {
            PPSNode ppsnode1 = (PPSNode)ppsnode.next();
            String s = ppsnode1.getName();
            if(s.equals("CreationDate"))
                credential.setCreationTimeInMillis(parseDate(getPpsNodeValue(ppsnode1)));
            else
            if(s.equals("ExpirationDate"))
                credential.setExpirationTimeInMillis(parseDate(getPpsNodeValue(ppsnode1)));
            else
            if(s.equals("UsernamePassword"))
                credential.setUserCredential(parseUserCredential(ppsnode1));
            else
            if(s.equals("DigitalCertificate"))
                credential.setCertCredential(parseCertificateCredential(ppsnode1));
            else
            if(s.equals("Realm"))
                credential.setRealm(getPpsNodeValue(ppsnode1));
            else
            if(s.equals("CheckAAAServerCertStatus"))
                credential.setCheckAaaServerCertStatus(Boolean.parseBoolean(getPpsNodeValue(ppsnode1)));
            else
            if(s.equals("SIM"))
                credential.setSimCredential(parseSimCredential(ppsnode1));
            else
                throw new ParsingException((new StringBuilder()).append("Unknown node under Credential: ").append(ppsnode1.getName()).toString());
        }

        return credential;
    }

    private static long parseDate(String s)
        throws ParsingException
    {
        long l;
        try
        {
            SimpleDateFormat simpledateformat = JVM INSTR new #506 <Class SimpleDateFormat>;
            simpledateformat.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            l = simpledateformat.parse(s).getTime();
        }
        catch(ParseException parseexception)
        {
            throw new ParsingException((new StringBuilder()).append("Badly formatted time: ").append(s).toString());
        }
        return l;
    }

    private static void parseEAPMethod(PPSNode ppsnode, android.net.wifi.hotspot2.pps.Credential.UserCredential usercredential)
        throws ParsingException
    {
        Iterator iterator;
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for EAPMethod");
        iterator = ppsnode.getChildren().iterator();
_L3:
        PPSNode ppsnode1;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        ppsnode1 = (PPSNode)iterator.next();
        ppsnode = ppsnode1.getName();
        if(ppsnode.equals("EAPType"))
        {
            usercredential.setEapType(parseInteger(getPpsNodeValue(ppsnode1)));
            continue; /* Loop/switch isn't completed */
        }
        if(ppsnode.equals("InnerMethod"))
        {
            usercredential.setNonEapInnerMethod(getPpsNodeValue(ppsnode1));
            continue; /* Loop/switch isn't completed */
        }
          goto _L1
_L5:
        Log.d("PpsMoParser", (new StringBuilder()).append("Ignore unsupported EAP method parameter: ").append(ppsnode1.getName()).toString());
        if(true) goto _L3; else goto _L2
_L1:
        if(ppsnode.equals("VendorId") || ppsnode.equals("VendorType") || ppsnode.equals("InnerEAPType") || ppsnode.equals("InnerVendorID") || ppsnode.equals("InnerVendorType")) goto _L5; else goto _L4
_L4:
        throw new ParsingException((new StringBuilder()).append("Unknown node under EAPMethod: ").append(ppsnode1.getName()).toString());
_L2:
    }

    private static byte[] parseHexString(String s)
        throws ParsingException
    {
        if((s.length() & 1) == 1)
            throw new ParsingException((new StringBuilder()).append("Odd length hex string: ").append(s.length()).toString());
        Object obj = new byte[s.length() / 2];
        int i = 0;
        while(i < obj.length) 
        {
            int j = i * 2;
            try
            {
                obj[i] = (byte)Integer.parseInt(s.substring(j, j + 2), 16);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                throw new ParsingException((new StringBuilder()).append("Invalid hex string: ").append(s).toString());
            }
            i++;
        }
        return ((byte []) (obj));
    }

    private static Pair parseHomeOIInstance(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for HomeOI instance");
        Long long1 = null;
        Object obj = null;
        Iterator iterator = ppsnode.getChildren().iterator();
        ppsnode = obj;
        while(iterator.hasNext()) 
        {
            PPSNode ppsnode1 = (PPSNode)iterator.next();
            String s = ppsnode1.getName();
            if(s.equals("HomeOI"))
                try
                {
                    long1 = Long.valueOf(getPpsNodeValue(ppsnode1), 16);
                }
                // Misplaced declaration of an exception variable
                catch(PPSNode ppsnode)
                {
                    throw new ParsingException((new StringBuilder()).append("Invalid HomeOI: ").append(getPpsNodeValue(ppsnode1)).toString());
                }
            else
            if(s.equals("HomeOIRequired"))
                ppsnode = Boolean.valueOf(getPpsNodeValue(ppsnode1));
            else
                throw new ParsingException((new StringBuilder()).append("Unknown node under NetworkID instance: ").append(ppsnode1.getName()).toString());
        }
        if(long1 == null)
            throw new ParsingException("HomeOI instance missing OI field");
        if(ppsnode == null)
            throw new ParsingException("HomeOI instance missing required field");
        else
            return new Pair(long1, ppsnode);
    }

    private static Pair parseHomeOIList(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for HomeOIList");
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        for(ppsnode = ppsnode.getChildren().iterator(); ppsnode.hasNext();)
        {
            Pair pair = parseHomeOIInstance((PPSNode)ppsnode.next());
            if(((Boolean)pair.second).booleanValue())
                arraylist.add((Long)pair.first);
            else
                arraylist1.add((Long)pair.first);
        }

        return new Pair(arraylist, arraylist1);
    }

    private static HomeSp parseHomeSP(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for HomeSP");
        HomeSp homesp = new HomeSp();
        for(ppsnode = ppsnode.getChildren().iterator(); ppsnode.hasNext();)
        {
            PPSNode ppsnode1 = (PPSNode)ppsnode.next();
            Object obj = ppsnode1.getName();
            if(((String) (obj)).equals("FQDN"))
                homesp.setFqdn(getPpsNodeValue(ppsnode1));
            else
            if(((String) (obj)).equals("FriendlyName"))
                homesp.setFriendlyName(getPpsNodeValue(ppsnode1));
            else
            if(((String) (obj)).equals("RoamingConsortiumOI"))
                homesp.setRoamingConsortiumOis(parseRoamingConsortiumOI(getPpsNodeValue(ppsnode1)));
            else
            if(((String) (obj)).equals("IconURL"))
                homesp.setIconUrl(getPpsNodeValue(ppsnode1));
            else
            if(((String) (obj)).equals("NetworkID"))
                homesp.setHomeNetworkIds(parseNetworkIds(ppsnode1));
            else
            if(((String) (obj)).equals("HomeOIList"))
            {
                obj = parseHomeOIList(ppsnode1);
                homesp.setMatchAllOis(convertFromLongList((List)((Pair) (obj)).first));
                homesp.setMatchAnyOis(convertFromLongList((List)((Pair) (obj)).second));
            } else
            if(((String) (obj)).equals("OtherHomePartners"))
                homesp.setOtherHomePartners(parseOtherHomePartners(ppsnode1));
            else
                throw new ParsingException((new StringBuilder()).append("Unknown node under HomeSP: ").append(ppsnode1.getName()).toString());
        }

        return homesp;
    }

    private static int parseInteger(String s)
        throws ParsingException
    {
        int i;
        try
        {
            i = Integer.parseInt(s);
        }
        catch(NumberFormatException numberformatexception)
        {
            throw new ParsingException((new StringBuilder()).append("Invalid integer value: ").append(s).toString());
        }
        return i;
    }

    private static long parseLong(String s, int i)
        throws ParsingException
    {
        long l;
        try
        {
            l = Long.parseLong(s, i);
        }
        catch(NumberFormatException numberformatexception)
        {
            throw new ParsingException((new StringBuilder()).append("Invalid long integer value: ").append(s).toString());
        }
        return l;
    }

    private static void parseMinBackhaulThreshold(PPSNode ppsnode, Policy policy)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for MinBackhaulThreshold");
        for(ppsnode = ppsnode.getChildren().iterator(); ppsnode.hasNext(); parseMinBackhaulThresholdInstance((PPSNode)ppsnode.next(), policy));
    }

    private static void parseMinBackhaulThresholdInstance(PPSNode ppsnode, Policy policy)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for MinBackhaulThreshold instance");
        Object obj = null;
        long l = 0x8000000000000000L;
        long l1 = 0x8000000000000000L;
        Iterator iterator = ppsnode.getChildren().iterator();
        ppsnode = obj;
        while(iterator.hasNext()) 
        {
            PPSNode ppsnode1 = (PPSNode)iterator.next();
            String s = ppsnode1.getName();
            if(s.equals("NetworkType"))
                ppsnode = getPpsNodeValue(ppsnode1);
            else
            if(s.equals("DLBandwidth"))
                l = parseLong(getPpsNodeValue(ppsnode1), 10);
            else
            if(s.equals("ULBandwidth"))
                l1 = parseLong(getPpsNodeValue(ppsnode1), 10);
            else
                throw new ParsingException((new StringBuilder()).append("Unknown node under MinBackhaulThreshold instance ").append(ppsnode1.getName()).toString());
        }
        if(ppsnode == null)
            throw new ParsingException("Missing NetworkType field");
        if(TextUtils.equals(ppsnode, "home"))
        {
            policy.setMinHomeDownlinkBandwidth(l);
            policy.setMinHomeUplinkBandwidth(l1);
        } else
        if(TextUtils.equals(ppsnode, "roaming"))
        {
            policy.setMinRoamingDownlinkBandwidth(l);
            policy.setMinRoamingUplinkBandwidth(l1);
        } else
        {
            throw new ParsingException((new StringBuilder()).append("Invalid network type: ").append(ppsnode).toString());
        }
    }

    public static PasspointConfiguration parseMoText(String s)
    {
        Object obj = new XMLParser();
label0:
        {
            Object obj1;
            try
            {
                obj1 = ((XMLParser) (obj)).parse(s);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                return null;
            }
            if(obj1 == null)
                return null;
            if(((XMLNode) (obj1)).getTag() != "MgmtTree")
            {
                Log.e("PpsMoParser", "Root is not a MgmtTree");
                return null;
            }
            s = null;
            obj = null;
            obj1 = ((XMLNode) (obj1)).getChildren().iterator();
            XMLNode xmlnode;
label1:
            do
            {
label2:
                {
                    if(!((Iterator) (obj1)).hasNext())
                        break label0;
                    xmlnode = (XMLNode)((Iterator) (obj1)).next();
                    String s1 = xmlnode.getTag();
                    if(s1.equals("VerDTD"))
                    {
                        if(s != null)
                        {
                            Log.e("PpsMoParser", "Duplicate VerDTD element");
                            return null;
                        }
                    } else
                    {
                        if(!s1.equals("Node"))
                            break label1;
                        if(obj != null)
                        {
                            Log.e("PpsMoParser", "Unexpected multiple Node element under MgmtTree");
                            return null;
                        }
                        break label2;
                    }
                    s = xmlnode.getText();
                    continue;
                }
                try
                {
                    obj = parsePpsNode(xmlnode);
                }
                // Misplaced declaration of an exception variable
                catch(String s)
                {
                    Log.e("PpsMoParser", s.getMessage());
                    return null;
                }
            } while(true);
            Log.e("PpsMoParser", (new StringBuilder()).append("Unknown node: ").append(xmlnode.getTag()).toString());
            return null;
        }
        return ((PasspointConfiguration) (obj));
    }

    private static Pair parseNetworkIdInstance(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for NetworkID instance");
        Object obj = null;
        Long long1 = null;
        Iterator iterator = ppsnode.getChildren().iterator();
        ppsnode = obj;
        while(iterator.hasNext()) 
        {
            PPSNode ppsnode1 = (PPSNode)iterator.next();
            String s = ppsnode1.getName();
            if(s.equals("SSID"))
                ppsnode = getPpsNodeValue(ppsnode1);
            else
            if(s.equals("HESSID"))
                long1 = Long.valueOf(parseLong(getPpsNodeValue(ppsnode1), 16));
            else
                throw new ParsingException((new StringBuilder()).append("Unknown node under NetworkID instance: ").append(ppsnode1.getName()).toString());
        }
        if(ppsnode == null)
            throw new ParsingException("NetworkID instance missing SSID");
        else
            return new Pair(ppsnode, long1);
    }

    private static Map parseNetworkIds(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for NetworkID");
        HashMap hashmap = new HashMap();
        Pair pair;
        for(ppsnode = ppsnode.getChildren().iterator(); ppsnode.hasNext(); hashmap.put((String)pair.first, (Long)pair.second))
            pair = parseNetworkIdInstance((PPSNode)ppsnode.next());

        return hashmap;
    }

    private static String parseOtherHomePartnerInstance(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for OtherHomePartner instance");
        Object obj = null;
        Iterator iterator = ppsnode.getChildren().iterator();
        ppsnode = obj;
        while(iterator.hasNext()) 
        {
            ppsnode = (PPSNode)iterator.next();
            if(ppsnode.getName().equals("FQDN"))
                ppsnode = getPpsNodeValue(ppsnode);
            else
                throw new ParsingException((new StringBuilder()).append("Unknown node under OtherHomePartner instance: ").append(ppsnode.getName()).toString());
        }
        if(ppsnode == null)
            throw new ParsingException("OtherHomePartner instance missing FQDN field");
        else
            return ppsnode;
    }

    private static String[] parseOtherHomePartners(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for OtherHomePartners");
        ArrayList arraylist = new ArrayList();
        for(ppsnode = ppsnode.getChildren().iterator(); ppsnode.hasNext(); arraylist.add(parseOtherHomePartnerInstance((PPSNode)ppsnode.next())));
        return (String[])arraylist.toArray(new String[arraylist.size()]);
    }

    private static Policy parsePolicy(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for Policy");
        Policy policy = new Policy();
        for(Iterator iterator = ppsnode.getChildren().iterator(); iterator.hasNext();)
        {
            ppsnode = (PPSNode)iterator.next();
            String s = ppsnode.getName();
            if(s.equals("PreferredRoamingPartnerList"))
                policy.setPreferredRoamingPartnerList(parsePreferredRoamingPartnerList(ppsnode));
            else
            if(s.equals("MinBackhaulThreshold"))
                parseMinBackhaulThreshold(ppsnode, policy);
            else
            if(s.equals("PolicyUpdate"))
                policy.setPolicyUpdate(parseUpdateParameter(ppsnode));
            else
            if(s.equals("SPExclusionList"))
                policy.setExcludedSsidList(parseSpExclusionList(ppsnode));
            else
            if(s.equals("RequiredProtoPortTuple"))
                policy.setRequiredProtoPortMap(parseRequiredProtoPortTuple(ppsnode));
            else
            if(s.equals("MaximumBSSLoadValue"))
                policy.setMaximumBssLoadValue(parseInteger(getPpsNodeValue(ppsnode)));
            else
                throw new ParsingException((new StringBuilder()).append("Unknown node under Policy: ").append(ppsnode.getName()).toString());
        }

        return policy;
    }

    private static PasspointConfiguration parsePpsInstance(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for PPS instance");
        PasspointConfiguration passpointconfiguration = new PasspointConfiguration();
        for(ppsnode = ppsnode.getChildren().iterator(); ppsnode.hasNext();)
        {
            PPSNode ppsnode1 = (PPSNode)ppsnode.next();
            String s = ppsnode1.getName();
            if(s.equals("HomeSP"))
                passpointconfiguration.setHomeSp(parseHomeSP(ppsnode1));
            else
            if(s.equals("Credential"))
                passpointconfiguration.setCredential(parseCredential(ppsnode1));
            else
            if(s.equals("Policy"))
                passpointconfiguration.setPolicy(parsePolicy(ppsnode1));
            else
            if(s.equals("AAAServerTrustRoot"))
                passpointconfiguration.setTrustRootCertList(parseAAAServerTrustRootList(ppsnode1));
            else
            if(s.equals("SubscriptionUpdate"))
                passpointconfiguration.setSubscriptionUpdate(parseUpdateParameter(ppsnode1));
            else
            if(s.equals("SubscriptionParameter"))
                parseSubscriptionParameter(ppsnode1, passpointconfiguration);
            else
            if(s.equals("CredentialPriority"))
                passpointconfiguration.setCredentialPriority(parseInteger(getPpsNodeValue(ppsnode1)));
            else
            if(s.equals("Extension"))
                Log.d("PpsMoParser", "Ignore Extension node for vendor specific information");
            else
                throw new ParsingException((new StringBuilder()).append("Unknown node: ").append(ppsnode1.getName()).toString());
        }

        return passpointconfiguration;
    }

    private static PasspointConfiguration parsePpsNode(XMLNode xmlnode)
        throws ParsingException
    {
        PasspointConfiguration passpointconfiguration = null;
        Object obj = null;
        int i = 0x80000000;
        Iterator iterator = xmlnode.getChildren().iterator();
        xmlnode = obj;
label0:
        do
        {
label1:
            {
label2:
                {
                    if(!iterator.hasNext())
                        break label0;
                    Object obj1 = (XMLNode)iterator.next();
                    String s = ((XMLNode) (obj1)).getTag();
                    if(s.equals("NodeName"))
                    {
                        if(xmlnode != null)
                            throw new ParsingException((new StringBuilder()).append("Duplicate NodeName: ").append(((XMLNode) (obj1)).getText()).toString());
                    } else
                    {
                        if(s.equals("Node"))
                        {
                            obj1 = buildPpsNode(((XMLNode) (obj1)));
                            if(!TextUtils.equals(((PPSNode) (obj1)).getName(), "UpdateIdentifier"))
                                break label1;
                            if(i != 0x80000000)
                                throw new ParsingException("Multiple node for UpdateIdentifier");
                        } else
                        {
                            if(s.equals("RTProperties"))
                            {
                                obj1 = parseUrn(((XMLNode) (obj1)));
                                if(!TextUtils.equals(((CharSequence) (obj1)), "urn:wfa:mo:hotspot2dot0-perprovidersubscription:1.0"))
                                    throw new ParsingException((new StringBuilder()).append("Unknown URN: ").append(((String) (obj1))).toString());
                            } else
                            {
                                throw new ParsingException((new StringBuilder()).append("Unknown tag under PPS node: ").append(((XMLNode) (obj1)).getTag()).toString());
                            }
                            continue;
                        }
                        break label2;
                    }
                    obj1 = ((XMLNode) (obj1)).getText();
                    xmlnode = ((XMLNode) (obj1));
                    if(!TextUtils.equals(((CharSequence) (obj1)), "PerProviderSubscription"))
                        throw new ParsingException((new StringBuilder()).append("Unexpected NodeName: ").append(((String) (obj1))).toString());
                    continue;
                }
                i = parseInteger(getPpsNodeValue(((PPSNode) (obj1))));
                continue;
            }
            if(passpointconfiguration != null)
                throw new ParsingException("Multiple PPS instance");
            passpointconfiguration = parsePpsInstance(((PPSNode) (obj1)));
        } while(true);
        if(passpointconfiguration != null && i != 0x80000000)
            passpointconfiguration.setUpdateIdentifier(i);
        return passpointconfiguration;
    }

    private static android.net.wifi.hotspot2.pps.Policy.RoamingPartner parsePreferredRoamingPartner(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for PreferredRoamingPartner instance");
        android.net.wifi.hotspot2.pps.Policy.RoamingPartner roamingpartner = new android.net.wifi.hotspot2.pps.Policy.RoamingPartner();
        ppsnode = ppsnode.getChildren().iterator();
        do
        {
            if(!ppsnode.hasNext())
                break;
            Object obj = (PPSNode)ppsnode.next();
            String s = ((PPSNode) (obj)).getName();
            if(s.equals("FQDN_Match"))
            {
                s = getPpsNodeValue(((PPSNode) (obj)));
                obj = s.split(",");
                if(obj.length != 2)
                    throw new ParsingException((new StringBuilder()).append("Invalid FQDN_Match: ").append(s).toString());
            } else
            {
                if(s.equals("Priority"))
                    roamingpartner.setPriority(parseInteger(getPpsNodeValue(((PPSNode) (obj)))));
                else
                if(s.equals("Country"))
                    roamingpartner.setCountries(getPpsNodeValue(((PPSNode) (obj))));
                else
                    throw new ParsingException((new StringBuilder()).append("Unknown node under PreferredRoamingPartnerList instance ").append(((PPSNode) (obj)).getName()).toString());
                continue;
            }
            roamingpartner.setFqdn(obj[0]);
            if(TextUtils.equals(obj[1], "exactMatch"))
                roamingpartner.setFqdnExactMatch(true);
            else
            if(TextUtils.equals(obj[1], "includeSubdomains"))
                roamingpartner.setFqdnExactMatch(false);
            else
                throw new ParsingException((new StringBuilder()).append("Invalid FQDN_Match: ").append(s).toString());
        } while(true);
        return roamingpartner;
    }

    private static List parsePreferredRoamingPartnerList(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for PreferredRoamingPartnerList");
        ArrayList arraylist = new ArrayList();
        for(ppsnode = ppsnode.getChildren().iterator(); ppsnode.hasNext(); arraylist.add(parsePreferredRoamingPartner((PPSNode)ppsnode.next())));
        return arraylist;
    }

    private static Pair parseProtoPortTuple(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for RequiredProtoPortTuple instance");
        int i = 0x80000000;
        Object obj = null;
        Iterator iterator = ppsnode.getChildren().iterator();
        ppsnode = obj;
        while(iterator.hasNext()) 
        {
            PPSNode ppsnode1 = (PPSNode)iterator.next();
            String s = ppsnode1.getName();
            if(s.equals("IPProtocol"))
                i = parseInteger(getPpsNodeValue(ppsnode1));
            else
            if(s.equals("PortNumber"))
                ppsnode = getPpsNodeValue(ppsnode1);
            else
                throw new ParsingException((new StringBuilder()).append("Unknown node under RequiredProtoPortTuple instance").append(ppsnode1.getName()).toString());
        }
        if(i == 0x80000000)
            throw new ParsingException("Missing IPProtocol field");
        if(ppsnode == null)
            throw new ParsingException("Missing PortNumber field");
        else
            return Pair.create(Integer.valueOf(i), ppsnode);
    }

    private static Map parseRequiredProtoPortTuple(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for RequiredProtoPortTuple");
        HashMap hashmap = new HashMap();
        for(Iterator iterator = ppsnode.getChildren().iterator(); iterator.hasNext(); hashmap.put((Integer)((Pair) (ppsnode)).first, (String)((Pair) (ppsnode)).second))
            ppsnode = parseProtoPortTuple((PPSNode)iterator.next());

        return hashmap;
    }

    private static long[] parseRoamingConsortiumOI(String s)
        throws ParsingException
    {
        s = s.split(",");
        long al[] = new long[s.length];
        for(int i = 0; i < s.length; i++)
            al[i] = parseLong(s[i], 16);

        return al;
    }

    private static android.net.wifi.hotspot2.pps.Credential.SimCredential parseSimCredential(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for SIM");
        android.net.wifi.hotspot2.pps.Credential.SimCredential simcredential = new android.net.wifi.hotspot2.pps.Credential.SimCredential();
        for(Iterator iterator = ppsnode.getChildren().iterator(); iterator.hasNext();)
        {
            ppsnode = (PPSNode)iterator.next();
            String s = ppsnode.getName();
            if(s.equals("IMSI"))
                simcredential.setImsi(getPpsNodeValue(ppsnode));
            else
            if(s.equals("EAPType"))
                simcredential.setEapType(parseInteger(getPpsNodeValue(ppsnode)));
            else
                throw new ParsingException((new StringBuilder()).append("Unknown node under SIM: ").append(ppsnode.getName()).toString());
        }

        return simcredential;
    }

    private static String parseSpExclusionInstance(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for SPExclusion instance");
        Object obj = null;
        Iterator iterator = ppsnode.getChildren().iterator();
        ppsnode = obj;
        while(iterator.hasNext()) 
        {
            ppsnode = (PPSNode)iterator.next();
            if(ppsnode.getName().equals("SSID"))
                ppsnode = getPpsNodeValue(ppsnode);
            else
                throw new ParsingException("Unknown node under SPExclusion instance");
        }
        return ppsnode;
    }

    private static String[] parseSpExclusionList(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for SPExclusionList");
        ArrayList arraylist = new ArrayList();
        for(ppsnode = ppsnode.getChildren().iterator(); ppsnode.hasNext(); arraylist.add(parseSpExclusionInstance((PPSNode)ppsnode.next())));
        return (String[])arraylist.toArray(new String[arraylist.size()]);
    }

    private static void parseSubscriptionParameter(PPSNode ppsnode, PasspointConfiguration passpointconfiguration)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for SubscriptionParameter");
        for(Iterator iterator = ppsnode.getChildren().iterator(); iterator.hasNext();)
        {
            ppsnode = (PPSNode)iterator.next();
            String s = ppsnode.getName();
            if(s.equals("CreationDate"))
                passpointconfiguration.setSubscriptionCreationTimeInMillis(parseDate(getPpsNodeValue(ppsnode)));
            else
            if(s.equals("ExpirationDate"))
                passpointconfiguration.setSubscriptionExpirationTimeInMillis(parseDate(getPpsNodeValue(ppsnode)));
            else
            if(s.equals("TypeOfSubscription"))
                passpointconfiguration.setSubscriptionType(getPpsNodeValue(ppsnode));
            else
            if(s.equals("UsageLimits"))
                parseUsageLimits(ppsnode, passpointconfiguration);
            else
                throw new ParsingException((new StringBuilder()).append("Unknown node under SubscriptionParameter").append(ppsnode.getName()).toString());
        }

    }

    private static Pair parseTrustRoot(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for TrustRoot");
        Object obj = null;
        byte abyte0[] = null;
        Iterator iterator = ppsnode.getChildren().iterator();
        ppsnode = obj;
        while(iterator.hasNext()) 
        {
            PPSNode ppsnode1 = (PPSNode)iterator.next();
            String s = ppsnode1.getName();
            if(s.equals("CertURL"))
                ppsnode = getPpsNodeValue(ppsnode1);
            else
            if(s.equals("CertSHA256Fingerprint"))
                abyte0 = parseHexString(getPpsNodeValue(ppsnode1));
            else
                throw new ParsingException((new StringBuilder()).append("Unknown node under TrustRoot: ").append(ppsnode1.getName()).toString());
        }
        return Pair.create(ppsnode, abyte0);
    }

    private static UpdateParameter parseUpdateParameter(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for Update Parameters");
        UpdateParameter updateparameter = new UpdateParameter();
        for(ppsnode = ppsnode.getChildren().iterator(); ppsnode.hasNext();)
        {
            PPSNode ppsnode1 = (PPSNode)ppsnode.next();
            Object obj = ppsnode1.getName();
            if(((String) (obj)).equals("UpdateInterval"))
                updateparameter.setUpdateIntervalInMinutes(parseLong(getPpsNodeValue(ppsnode1), 10));
            else
            if(((String) (obj)).equals("UpdateMethod"))
                updateparameter.setUpdateMethod(getPpsNodeValue(ppsnode1));
            else
            if(((String) (obj)).equals("Restriction"))
                updateparameter.setRestriction(getPpsNodeValue(ppsnode1));
            else
            if(((String) (obj)).equals("URI"))
                updateparameter.setServerUri(getPpsNodeValue(ppsnode1));
            else
            if(((String) (obj)).equals("UsernamePassword"))
            {
                obj = parseUpdateUserCredential(ppsnode1);
                updateparameter.setUsername((String)((Pair) (obj)).first);
                updateparameter.setBase64EncodedPassword((String)((Pair) (obj)).second);
            } else
            if(((String) (obj)).equals("TrustRoot"))
            {
                obj = parseTrustRoot(ppsnode1);
                updateparameter.setTrustRootCertUrl((String)((Pair) (obj)).first);
                updateparameter.setTrustRootCertSha256Fingerprint((byte[])((Pair) (obj)).second);
            } else
            if(((String) (obj)).equals("Other"))
                Log.d("PpsMoParser", (new StringBuilder()).append("Ignore unsupported paramter: ").append(ppsnode1.getName()).toString());
            else
                throw new ParsingException((new StringBuilder()).append("Unknown node under Update Parameters: ").append(ppsnode1.getName()).toString());
        }

        return updateparameter;
    }

    private static Pair parseUpdateUserCredential(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for UsernamePassword");
        Object obj = null;
        String s1 = null;
        Iterator iterator = ppsnode.getChildren().iterator();
        ppsnode = obj;
        while(iterator.hasNext()) 
        {
            PPSNode ppsnode1 = (PPSNode)iterator.next();
            String s = ppsnode1.getName();
            if(s.equals("Username"))
                ppsnode = getPpsNodeValue(ppsnode1);
            else
            if(s.equals("Password"))
                s1 = getPpsNodeValue(ppsnode1);
            else
                throw new ParsingException((new StringBuilder()).append("Unknown node under UsernamePassword: ").append(ppsnode1.getName()).toString());
        }
        return Pair.create(ppsnode, s1);
    }

    private static String parseUrn(XMLNode xmlnode)
        throws ParsingException
    {
        if(xmlnode.getChildren().size() != 1)
            throw new ParsingException("Expect RTPProperties node to only have one child");
        xmlnode = (XMLNode)xmlnode.getChildren().get(0);
        if(xmlnode.getChildren().size() != 1)
            throw new ParsingException("Expect Type node to only have one child");
        if(!TextUtils.equals(xmlnode.getTag(), "Type"))
            throw new ParsingException((new StringBuilder()).append("Unexpected tag for Type: ").append(xmlnode.getTag()).toString());
        xmlnode = (XMLNode)xmlnode.getChildren().get(0);
        if(!xmlnode.getChildren().isEmpty())
            throw new ParsingException("Expect DDFName node to have no child");
        if(!TextUtils.equals(xmlnode.getTag(), "DDFName"))
            throw new ParsingException((new StringBuilder()).append("Unexpected tag for DDFName: ").append(xmlnode.getTag()).toString());
        else
            return xmlnode.getText();
    }

    private static void parseUsageLimits(PPSNode ppsnode, PasspointConfiguration passpointconfiguration)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for UsageLimits");
        for(Iterator iterator = ppsnode.getChildren().iterator(); iterator.hasNext();)
        {
            ppsnode = (PPSNode)iterator.next();
            String s = ppsnode.getName();
            if(s.equals("DataLimit"))
                passpointconfiguration.setUsageLimitDataLimit(parseLong(getPpsNodeValue(ppsnode), 10));
            else
            if(s.equals("StartDate"))
                passpointconfiguration.setUsageLimitStartTimeInMillis(parseDate(getPpsNodeValue(ppsnode)));
            else
            if(s.equals("TimeLimit"))
                passpointconfiguration.setUsageLimitTimeLimitInMinutes(parseLong(getPpsNodeValue(ppsnode), 10));
            else
            if(s.equals("UsageTimePeriod"))
                passpointconfiguration.setUsageLimitUsageTimePeriodInMinutes(parseLong(getPpsNodeValue(ppsnode), 10));
            else
                throw new ParsingException((new StringBuilder()).append("Unknown node under UsageLimits").append(ppsnode.getName()).toString());
        }

    }

    private static android.net.wifi.hotspot2.pps.Credential.UserCredential parseUserCredential(PPSNode ppsnode)
        throws ParsingException
    {
        if(ppsnode.isLeaf())
            throw new ParsingException("Leaf node not expected for UsernamePassword");
        android.net.wifi.hotspot2.pps.Credential.UserCredential usercredential = new android.net.wifi.hotspot2.pps.Credential.UserCredential();
        for(Iterator iterator = ppsnode.getChildren().iterator(); iterator.hasNext();)
        {
            PPSNode ppsnode1 = (PPSNode)iterator.next();
            ppsnode = ppsnode1.getName();
            if(ppsnode.equals("Username"))
                usercredential.setUsername(getPpsNodeValue(ppsnode1));
            else
            if(ppsnode.equals("Password"))
                usercredential.setPassword(getPpsNodeValue(ppsnode1));
            else
            if(ppsnode.equals("MachineManaged"))
                usercredential.setMachineManaged(Boolean.parseBoolean(getPpsNodeValue(ppsnode1)));
            else
            if(ppsnode.equals("SoftTokenApp"))
                usercredential.setSoftTokenApp(getPpsNodeValue(ppsnode1));
            else
            if(ppsnode.equals("AbleToShare"))
                usercredential.setAbleToShare(Boolean.parseBoolean(getPpsNodeValue(ppsnode1)));
            else
            if(ppsnode.equals("EAPMethod"))
                parseEAPMethod(ppsnode1, usercredential);
            else
                throw new ParsingException((new StringBuilder()).append("Unknown node under UsernamPassword: ").append(ppsnode1.getName()).toString());
        }

        return usercredential;
    }

    private static final String NODE_AAA_SERVER_TRUST_ROOT = "AAAServerTrustRoot";
    private static final String NODE_ABLE_TO_SHARE = "AbleToShare";
    private static final String NODE_CERTIFICATE_TYPE = "CertificateType";
    private static final String NODE_CERT_SHA256_FINGERPRINT = "CertSHA256Fingerprint";
    private static final String NODE_CERT_URL = "CertURL";
    private static final String NODE_CHECK_AAA_SERVER_CERT_STATUS = "CheckAAAServerCertStatus";
    private static final String NODE_COUNTRY = "Country";
    private static final String NODE_CREATION_DATE = "CreationDate";
    private static final String NODE_CREDENTIAL = "Credential";
    private static final String NODE_CREDENTIAL_PRIORITY = "CredentialPriority";
    private static final String NODE_DATA_LIMIT = "DataLimit";
    private static final String NODE_DIGITAL_CERTIFICATE = "DigitalCertificate";
    private static final String NODE_DOWNLINK_BANDWIDTH = "DLBandwidth";
    private static final String NODE_EAP_METHOD = "EAPMethod";
    private static final String NODE_EAP_TYPE = "EAPType";
    private static final String NODE_EXPIRATION_DATE = "ExpirationDate";
    private static final String NODE_EXTENSION = "Extension";
    private static final String NODE_FQDN = "FQDN";
    private static final String NODE_FQDN_MATCH = "FQDN_Match";
    private static final String NODE_FRIENDLY_NAME = "FriendlyName";
    private static final String NODE_HESSID = "HESSID";
    private static final String NODE_HOMESP = "HomeSP";
    private static final String NODE_HOME_OI = "HomeOI";
    private static final String NODE_HOME_OI_LIST = "HomeOIList";
    private static final String NODE_HOME_OI_REQUIRED = "HomeOIRequired";
    private static final String NODE_ICON_URL = "IconURL";
    private static final String NODE_INNER_EAP_TYPE = "InnerEAPType";
    private static final String NODE_INNER_METHOD = "InnerMethod";
    private static final String NODE_INNER_VENDOR_ID = "InnerVendorID";
    private static final String NODE_INNER_VENDOR_TYPE = "InnerVendorType";
    private static final String NODE_IP_PROTOCOL = "IPProtocol";
    private static final String NODE_MACHINE_MANAGED = "MachineManaged";
    private static final String NODE_MAXIMUM_BSS_LOAD_VALUE = "MaximumBSSLoadValue";
    private static final String NODE_MIN_BACKHAUL_THRESHOLD = "MinBackhaulThreshold";
    private static final String NODE_NETWORK_ID = "NetworkID";
    private static final String NODE_NETWORK_TYPE = "NetworkType";
    private static final String NODE_OTHER = "Other";
    private static final String NODE_OTHER_HOME_PARTNERS = "OtherHomePartners";
    private static final String NODE_PASSWORD = "Password";
    private static final String NODE_PER_PROVIDER_SUBSCRIPTION = "PerProviderSubscription";
    private static final String NODE_POLICY = "Policy";
    private static final String NODE_POLICY_UPDATE = "PolicyUpdate";
    private static final String NODE_PORT_NUMBER = "PortNumber";
    private static final String NODE_PREFERRED_ROAMING_PARTNER_LIST = "PreferredRoamingPartnerList";
    private static final String NODE_PRIORITY = "Priority";
    private static final String NODE_REALM = "Realm";
    private static final String NODE_REQUIRED_PROTO_PORT_TUPLE = "RequiredProtoPortTuple";
    private static final String NODE_RESTRICTION = "Restriction";
    private static final String NODE_ROAMING_CONSORTIUM_OI = "RoamingConsortiumOI";
    private static final String NODE_SIM = "SIM";
    private static final String NODE_SIM_IMSI = "IMSI";
    private static final String NODE_SOFT_TOKEN_APP = "SoftTokenApp";
    private static final String NODE_SP_EXCLUSION_LIST = "SPExclusionList";
    private static final String NODE_SSID = "SSID";
    private static final String NODE_START_DATE = "StartDate";
    private static final String NODE_SUBSCRIPTION_PARAMETER = "SubscriptionParameter";
    private static final String NODE_SUBSCRIPTION_UPDATE = "SubscriptionUpdate";
    private static final String NODE_TIME_LIMIT = "TimeLimit";
    private static final String NODE_TRUST_ROOT = "TrustRoot";
    private static final String NODE_TYPE_OF_SUBSCRIPTION = "TypeOfSubscription";
    private static final String NODE_UPDATE_IDENTIFIER = "UpdateIdentifier";
    private static final String NODE_UPDATE_INTERVAL = "UpdateInterval";
    private static final String NODE_UPDATE_METHOD = "UpdateMethod";
    private static final String NODE_UPLINK_BANDWIDTH = "ULBandwidth";
    private static final String NODE_URI = "URI";
    private static final String NODE_USAGE_LIMITS = "UsageLimits";
    private static final String NODE_USAGE_TIME_PERIOD = "UsageTimePeriod";
    private static final String NODE_USERNAME = "Username";
    private static final String NODE_USERNAME_PASSWORD = "UsernamePassword";
    private static final String NODE_VENDOR_ID = "VendorId";
    private static final String NODE_VENDOR_TYPE = "VendorType";
    private static final String PPS_MO_URN = "urn:wfa:mo:hotspot2dot0-perprovidersubscription:1.0";
    private static final String TAG = "PpsMoParser";
    private static final String TAG_DDF_NAME = "DDFName";
    private static final String TAG_MANAGEMENT_TREE = "MgmtTree";
    private static final String TAG_NODE = "Node";
    private static final String TAG_NODE_NAME = "NodeName";
    private static final String TAG_RT_PROPERTIES = "RTProperties";
    private static final String TAG_TYPE = "Type";
    private static final String TAG_VALUE = "Value";
    private static final String TAG_VER_DTD = "VerDTD";
}

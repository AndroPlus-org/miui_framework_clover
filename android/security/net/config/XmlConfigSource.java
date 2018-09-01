// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.net.config;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.*;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.security.net.config:
//            ConfigSource, ResourceCertificateSource, CertificatesEntryRef, SystemCertificateSource, 
//            UserCertificateSource, Domain, NetworkSecurityConfig, Pin, 
//            PinSet

public class XmlConfigSource
    implements ConfigSource
{
    public static class ParserException extends Exception
    {

        public ParserException(XmlPullParser xmlpullparser, String s)
        {
            this(xmlpullparser, s, null);
        }

        public ParserException(XmlPullParser xmlpullparser, String s, Throwable throwable)
        {
            super((new StringBuilder()).append(s).append(" at: ").append(xmlpullparser.getPositionDescription()).toString(), throwable);
        }
    }


    public XmlConfigSource(Context context, int i)
    {
        this(context, i, false);
    }

    public XmlConfigSource(Context context, int i, boolean flag)
    {
        this(context, i, flag, 10000);
    }

    public XmlConfigSource(Context context, int i, boolean flag, int j)
    {
        this(context, i, flag, j, 1);
    }

    public XmlConfigSource(Context context, int i, boolean flag, int j, int k)
    {
        mLock = new Object();
        mResourceId = i;
        mContext = context;
        mDebugBuild = flag;
        mTargetSdkVersion = j;
        mTargetSandboxVesrsion = k;
    }

    private void addDebugAnchorsIfNeeded(NetworkSecurityConfig.Builder builder, NetworkSecurityConfig.Builder builder1)
    {
        if(builder == null || builder.hasCertificatesEntryRefs() ^ true)
            return;
        if(!builder1.hasCertificatesEntryRefs())
        {
            return;
        } else
        {
            builder1.addCertificatesEntryRefs(builder.getCertificatesEntryRefs());
            return;
        }
    }

    private void ensureInitialized()
    {
        Object obj;
        Object obj1;
        obj = null;
        obj1 = null;
        Object obj2 = mLock;
        obj2;
        JVM INSTR monitorenter ;
        boolean flag = mInitialized;
        if(!flag)
            break MISSING_BLOCK_LABEL_25;
        obj2;
        JVM INSTR monitorexit ;
        return;
        Object obj3;
        Object obj4;
        obj3 = null;
        obj4 = null;
        XmlResourceParser xmlresourceparser = mContext.getResources().getXml(mResourceId);
        obj4 = xmlresourceparser;
        obj3 = xmlresourceparser;
        parseNetworkSecurityConfig(xmlresourceparser);
        obj4 = xmlresourceparser;
        obj3 = xmlresourceparser;
        mContext = null;
        obj4 = xmlresourceparser;
        obj3 = xmlresourceparser;
        mInitialized = true;
        obj4 = obj1;
        if(xmlresourceparser == null)
            break MISSING_BLOCK_LABEL_105;
        xmlresourceparser.close();
        obj4 = obj1;
_L2:
        if(obj4 == null)
            break; /* Loop/switch isn't completed */
        throw obj4;
        Object obj5;
        obj5;
        obj4 = JVM INSTR new #108 <Class RuntimeException>;
        obj3 = JVM INSTR new #110 <Class StringBuilder>;
        ((StringBuilder) (obj3)).StringBuilder();
        ((RuntimeException) (obj4)).RuntimeException(((StringBuilder) (obj3)).append("Failed to parse XML configuration from ").append(mContext.getResources().getResourceEntryName(mResourceId)).toString(), ((Throwable) (obj5)));
        throw obj4;
        obj4;
        obj2;
        JVM INSTR monitorexit ;
        throw obj4;
        obj4;
        if(true) goto _L2; else goto _L1
        obj3;
        throw obj3;
        obj5;
_L6:
        obj = obj3;
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_204;
        ((XmlResourceParser) (obj4)).close();
        obj = obj3;
_L4:
        if(obj == null)
            break; /* Loop/switch isn't completed */
        throw obj;
        obj4;
        if(obj3 == null)
        {
            obj = obj4;
            continue; /* Loop/switch isn't completed */
        }
        obj = obj3;
        if(obj3 == obj4)
            continue; /* Loop/switch isn't completed */
        ((Throwable) (obj3)).addSuppressed(((Throwable) (obj4)));
        obj = obj3;
        if(true) goto _L4; else goto _L3
_L3:
        throw obj5;
_L1:
        return;
        obj5;
        obj4 = obj3;
        obj3 = obj;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static final String getConfigString(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown config type: ").append(i).toString());

        case 0: // '\0'
            return "base-config";

        case 1: // '\001'
            return "domain-config";

        case 2: // '\002'
            return "debug-overrides";
        }
    }

    private CertificatesEntryRef parseCertificatesEntry(XmlResourceParser xmlresourceparser, boolean flag)
        throws IOException, XmlPullParserException, ParserException
    {
        flag = xmlresourceparser.getAttributeBooleanValue(null, "overridePins", flag);
        int i = xmlresourceparser.getAttributeResourceValue(null, "src", -1);
        Object obj = xmlresourceparser.getAttributeValue(null, "src");
        if(obj == null)
            throw new ParserException(xmlresourceparser, "certificates element missing src attribute");
        if(i != -1)
            obj = new ResourceCertificateSource(i, mContext);
        else
        if("system".equals(obj))
            obj = SystemCertificateSource.getInstance();
        else
        if("user".equals(obj))
            obj = UserCertificateSource.getInstance();
        else
            throw new ParserException(xmlresourceparser, "Unknown certificates src. Should be one of system|user|@resourceVal");
        XmlUtils.skipCurrentTag(xmlresourceparser);
        return new CertificatesEntryRef(((CertificateSource) (obj)), flag);
    }

    private List parseConfigEntry(XmlResourceParser xmlresourceparser, Set set, NetworkSecurityConfig.Builder builder, int i)
        throws IOException, XmlPullParserException, ParserException
    {
        ArrayList arraylist;
        NetworkSecurityConfig.Builder builder1;
        boolean flag2;
        int j;
        boolean flag3;
        boolean flag4;
        arraylist = new ArrayList();
        builder1 = new NetworkSecurityConfig.Builder();
        builder1.setParent(builder);
        builder = new ArraySet();
        boolean flag = false;
        boolean flag1 = false;
        int k;
        if(i == 2)
            flag2 = true;
        else
            flag2 = false;
        xmlresourceparser.getName();
        j = xmlresourceparser.getDepth();
        arraylist.add(new Pair(builder1, builder));
        k = 0;
        do
        {
            flag3 = flag;
            flag4 = flag1;
            if(k >= xmlresourceparser.getAttributeCount())
                break;
            String s = xmlresourceparser.getAttributeName(k);
            if("hstsEnforced".equals(s))
                builder1.setHstsEnforced(xmlresourceparser.getAttributeBooleanValue(k, false));
            else
            if("cleartextTrafficPermitted".equals(s))
                builder1.setCleartextTrafficPermitted(xmlresourceparser.getAttributeBooleanValue(k, true));
            k++;
        } while(true);
          goto _L1
_L3:
        builder.add(parseDomain(xmlresourceparser, set));
_L1:
        do
        {
            if(!XmlUtils.nextElementWithin(xmlresourceparser, j))
                break;
            String s1 = xmlresourceparser.getName();
            if("domain".equals(s1))
            {
                if(i != 1)
                    throw new ParserException(xmlresourceparser, (new StringBuilder()).append("domain element not allowed in ").append(getConfigString(i)).toString());
                continue; /* Loop/switch isn't completed */
            }
            if("trust-anchors".equals(s1))
            {
                if(flag4)
                    throw new ParserException(xmlresourceparser, "Multiple trust-anchor elements not allowed");
                builder1.addCertificatesEntryRefs(parseTrustAnchors(xmlresourceparser, flag2));
                flag4 = true;
            } else
            if("pin-set".equals(s1))
            {
                if(i != 1)
                    throw new ParserException(xmlresourceparser, (new StringBuilder()).append("pin-set element not allowed in ").append(getConfigString(i)).toString());
                if(flag3)
                    throw new ParserException(xmlresourceparser, "Multiple pin-set elements not allowed");
                builder1.setPinSet(parsePinSet(xmlresourceparser));
                flag3 = true;
            } else
            if("domain-config".equals(s1))
            {
                if(i != 1)
                    throw new ParserException(xmlresourceparser, (new StringBuilder()).append("Nested domain-config not allowed in ").append(getConfigString(i)).toString());
                arraylist.addAll(parseConfigEntry(xmlresourceparser, set, builder1, i));
            } else
            {
                XmlUtils.skipCurrentTag(xmlresourceparser);
            }
        } while(true);
        if(i == 1 && builder.isEmpty())
            throw new ParserException(xmlresourceparser, "No domain elements in domain-config");
        else
            return arraylist;
        if(true) goto _L3; else goto _L2
_L2:
    }

    private NetworkSecurityConfig.Builder parseDebugOverridesResource()
        throws IOException, XmlPullParserException, ParserException
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        int i;
        Object obj5;
        obj = null;
        obj1 = null;
        obj2 = mContext.getResources();
        obj3 = ((Resources) (obj2)).getResourcePackageName(mResourceId);
        obj4 = ((Resources) (obj2)).getResourceEntryName(mResourceId);
        i = ((Resources) (obj2)).getIdentifier((new StringBuilder()).append(((String) (obj4))).append("_debug").toString(), "xml", ((String) (obj3)));
        if(i == 0)
            return null;
        obj5 = null;
        obj4 = null;
        obj3 = null;
        obj2 = ((Resources) (obj2)).getXml(i);
        obj3 = obj2;
        obj4 = obj2;
        XmlUtils.beginDocument(((XmlPullParser) (obj2)), "network-security-config");
        obj3 = obj2;
        obj4 = obj2;
        int j = ((XmlResourceParser) (obj2)).getDepth();
        i = 0;
_L16:
        obj3 = obj2;
        obj4 = obj2;
        if(!XmlUtils.nextElementWithin(((XmlPullParser) (obj2)), j)) goto _L2; else goto _L1
_L1:
        obj3 = obj2;
        obj4 = obj2;
        if(!"debug-overrides".equals(((XmlResourceParser) (obj2)).getName())) goto _L4; else goto _L3
_L3:
        if(!i) goto _L6; else goto _L5
_L5:
        obj3 = obj2;
        obj4 = obj2;
        obj5 = JVM INSTR new #8   <Class XmlConfigSource$ParserException>;
        obj3 = obj2;
        obj4 = obj2;
        ((ParserException) (obj5)).ParserException(((XmlPullParser) (obj2)), "Only one debug-overrides allowed");
        obj3 = obj2;
        obj4 = obj2;
        try
        {
            throw obj5;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj4) { }
        throw obj4;
        obj2;
_L11:
        obj5 = obj4;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_218;
        ((XmlResourceParser) (obj3)).close();
        obj5 = obj4;
_L14:
        Throwable throwable;
        if(obj5 != null)
            throw obj5;
        else
            throw obj2;
_L6:
        obj3 = obj2;
        obj4 = obj2;
        if(!mDebugBuild) goto _L8; else goto _L7
_L7:
        obj3 = obj2;
        obj4 = obj2;
        obj5 = (NetworkSecurityConfig.Builder)((Pair)parseConfigEntry(((XmlResourceParser) (obj2)), null, null, 2).get(0)).first;
_L10:
        i = 1;
        continue; /* Loop/switch isn't completed */
_L8:
        obj3 = obj2;
        obj4 = obj2;
        XmlUtils.skipCurrentTag(((XmlPullParser) (obj2)));
        if(true) goto _L10; else goto _L9
_L9:
        obj2;
        obj3 = obj4;
        obj4 = obj1;
          goto _L11
_L4:
        obj3 = obj2;
        obj4 = obj2;
        XmlUtils.skipCurrentTag(((XmlPullParser) (obj2)));
        continue; /* Loop/switch isn't completed */
_L2:
        obj3 = obj;
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_329;
        ((XmlResourceParser) (obj2)).close();
        obj3 = obj;
_L13:
        if(obj3 != null)
            throw obj3;
        break MISSING_BLOCK_LABEL_383;
        obj3;
        if(true) goto _L13; else goto _L12
_L12:
        throwable;
        if(obj4 == null)
        {
            obj5 = throwable;
        } else
        {
            obj5 = obj4;
            if(obj4 != throwable)
            {
                ((Throwable) (obj4)).addSuppressed(throwable);
                obj5 = obj4;
            }
        }
          goto _L14
        return ((NetworkSecurityConfig.Builder) (obj5));
        if(true) goto _L16; else goto _L15
_L15:
    }

    private Domain parseDomain(XmlResourceParser xmlresourceparser, Set set)
        throws IOException, XmlPullParserException, ParserException
    {
        boolean flag = xmlresourceparser.getAttributeBooleanValue(null, "includeSubdomains", false);
        if(xmlresourceparser.next() != 4)
            throw new ParserException(xmlresourceparser, "Domain name missing");
        String s = xmlresourceparser.getText().trim().toLowerCase(Locale.US);
        if(xmlresourceparser.next() != 3)
            throw new ParserException(xmlresourceparser, "domain contains additional elements");
        if(!set.add(s))
            throw new ParserException(xmlresourceparser, (new StringBuilder()).append(s).append(" has already been specified").toString());
        else
            return new Domain(s, flag);
    }

    private void parseNetworkSecurityConfig(XmlResourceParser xmlresourceparser)
        throws IOException, XmlPullParserException, ParserException
    {
        ArraySet arrayset = new ArraySet();
        Object obj1 = new ArrayList();
        Object obj2 = null;
        Object obj3 = null;
        boolean flag = false;
        boolean flag1 = false;
        XmlUtils.beginDocument(xmlresourceparser, "network-security-config");
        for(int i = xmlresourceparser.getDepth(); XmlUtils.nextElementWithin(xmlresourceparser, i);)
            if("base-config".equals(xmlresourceparser.getName()))
            {
                if(flag1)
                    throw new ParserException(xmlresourceparser, "Only one base-config allowed");
                flag1 = true;
                obj2 = (NetworkSecurityConfig.Builder)((Pair)parseConfigEntry(xmlresourceparser, arrayset, null, 0).get(0)).first;
            } else
            if("domain-config".equals(xmlresourceparser.getName()))
                ((List) (obj1)).addAll(parseConfigEntry(xmlresourceparser, arrayset, ((NetworkSecurityConfig.Builder) (obj2)), 1));
            else
            if("debug-overrides".equals(xmlresourceparser.getName()))
            {
                if(flag)
                    throw new ParserException(xmlresourceparser, "Only one debug-overrides allowed");
                if(mDebugBuild)
                    obj3 = (NetworkSecurityConfig.Builder)((Pair)parseConfigEntry(xmlresourceparser, null, null, 2).get(0)).first;
                else
                    XmlUtils.skipCurrentTag(xmlresourceparser);
                flag = true;
            } else
            {
                XmlUtils.skipCurrentTag(xmlresourceparser);
            }

        xmlresourceparser = ((XmlResourceParser) (obj3));
        if(mDebugBuild)
        {
            xmlresourceparser = ((XmlResourceParser) (obj3));
            if(obj3 == null)
                xmlresourceparser = parseDebugOverridesResource();
        }
        obj3 = NetworkSecurityConfig.getDefaultBuilder(mTargetSdkVersion, mTargetSandboxVesrsion);
        addDebugAnchorsIfNeeded(xmlresourceparser, ((NetworkSecurityConfig.Builder) (obj3)));
        if(obj2 != null)
        {
            ((NetworkSecurityConfig.Builder) (obj2)).setParent(((NetworkSecurityConfig.Builder) (obj3)));
            addDebugAnchorsIfNeeded(xmlresourceparser, ((NetworkSecurityConfig.Builder) (obj2)));
        } else
        {
            obj2 = obj3;
        }
        obj3 = new ArraySet();
        for(obj1 = ((Iterable) (obj1)).iterator(); ((Iterator) (obj1)).hasNext();)
        {
            Object obj = (Pair)((Iterator) (obj1)).next();
            Object obj4 = (NetworkSecurityConfig.Builder)((Pair) (obj)).first;
            obj = (Set)((Pair) (obj)).second;
            if(((NetworkSecurityConfig.Builder) (obj4)).getParent() == null)
                ((NetworkSecurityConfig.Builder) (obj4)).setParent(((NetworkSecurityConfig.Builder) (obj2)));
            addDebugAnchorsIfNeeded(xmlresourceparser, ((NetworkSecurityConfig.Builder) (obj4)));
            obj4 = ((NetworkSecurityConfig.Builder) (obj4)).build();
            obj = ((Iterable) (obj)).iterator();
            while(((Iterator) (obj)).hasNext()) 
                ((Set) (obj3)).add(new Pair((Domain)((Iterator) (obj)).next(), obj4));
        }

        mDefaultConfig = ((NetworkSecurityConfig.Builder) (obj2)).build();
        mDomainMap = ((Set) (obj3));
    }

    private Pin parsePin(XmlResourceParser xmlresourceparser)
        throws IOException, XmlPullParserException, ParserException
    {
        Object obj = xmlresourceparser.getAttributeValue(null, "digest");
        if(!Pin.isSupportedDigestAlgorithm(((String) (obj))))
            throw new ParserException(xmlresourceparser, (new StringBuilder()).append("Unsupported pin digest algorithm: ").append(((String) (obj))).toString());
        if(xmlresourceparser.next() != 4)
            throw new ParserException(xmlresourceparser, "Missing pin digest");
        String s = xmlresourceparser.getText().trim();
        byte abyte0[];
        int i;
        try
        {
            abyte0 = Base64.decode(s, 0);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new ParserException(xmlresourceparser, "Invalid pin digest", ((Throwable) (obj)));
        }
        i = Pin.getDigestLength(((String) (obj)));
        if(abyte0.length != i)
            throw new ParserException(xmlresourceparser, (new StringBuilder()).append("digest length ").append(abyte0.length).append(" does not match expected length for ").append(((String) (obj))).append(" of ").append(i).toString());
        if(xmlresourceparser.next() != 3)
            throw new ParserException(xmlresourceparser, "pin contains additional elements");
        else
            return new Pin(((String) (obj)), abyte0);
    }

    private PinSet parsePinSet(XmlResourceParser xmlresourceparser)
        throws IOException, XmlPullParserException, ParserException
    {
        Object obj;
        long l;
        obj = xmlresourceparser.getAttributeValue(null, "expiration");
        l = 0x7fffffffffffffffL;
        if(obj == null)
            break MISSING_BLOCK_LABEL_82;
        try
        {
            SimpleDateFormat simpledateformat = JVM INSTR new #464 <Class SimpleDateFormat>;
            simpledateformat.SimpleDateFormat("yyyy-MM-dd");
            simpledateformat.setLenient(false);
            obj = simpledateformat.parse(((String) (obj)));
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new ParserException(xmlresourceparser, "Invalid expiration date in pin-set", ((Throwable) (obj)));
        }
        if(obj != null)
            break MISSING_BLOCK_LABEL_77;
        obj = JVM INSTR new #8   <Class XmlConfigSource$ParserException>;
        ((ParserException) (obj)).ParserException(xmlresourceparser, "Invalid expiration date in pin-set");
        throw obj;
        l = ((Date) (obj)).getTime();
        int i = xmlresourceparser.getDepth();
        ArraySet arrayset = new ArraySet();
        while(XmlUtils.nextElementWithin(xmlresourceparser, i)) 
            if(xmlresourceparser.getName().equals("pin"))
                arrayset.add(parsePin(xmlresourceparser));
            else
                XmlUtils.skipCurrentTag(xmlresourceparser);
        return new PinSet(arrayset, l);
    }

    private Collection parseTrustAnchors(XmlResourceParser xmlresourceparser, boolean flag)
        throws IOException, XmlPullParserException, ParserException
    {
        int i = xmlresourceparser.getDepth();
        ArrayList arraylist = new ArrayList();
        while(XmlUtils.nextElementWithin(xmlresourceparser, i)) 
            if(xmlresourceparser.getName().equals("certificates"))
                arraylist.add(parseCertificatesEntry(xmlresourceparser, flag));
            else
                XmlUtils.skipCurrentTag(xmlresourceparser);
        return arraylist;
    }

    public NetworkSecurityConfig getDefaultConfig()
    {
        ensureInitialized();
        return mDefaultConfig;
    }

    public Set getPerDomainConfigs()
    {
        ensureInitialized();
        return mDomainMap;
    }

    private static final int CONFIG_BASE = 0;
    private static final int CONFIG_DEBUG = 2;
    private static final int CONFIG_DOMAIN = 1;
    private Context mContext;
    private final boolean mDebugBuild;
    private NetworkSecurityConfig mDefaultConfig;
    private Set mDomainMap;
    private boolean mInitialized;
    private final Object mLock;
    private final int mResourceId;
    private final int mTargetSandboxVesrsion;
    private final int mTargetSdkVersion;
}

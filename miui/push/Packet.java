// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.push;

import android.os.Bundle;
import android.text.TextUtils;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package miui.push:
//            StringUtils, CommonPacketExtension, XMPPError, PacketExtension

public abstract class Packet
{

    public Packet()
    {
        xmlns = DEFAULT_XML_NS;
        packetID = null;
        to = null;
        from = null;
        chId = null;
        packetExtensions = new CopyOnWriteArrayList();
        properties = new HashMap();
        error = null;
    }

    public Packet(Bundle bundle)
    {
        xmlns = DEFAULT_XML_NS;
        packetID = null;
        to = null;
        from = null;
        chId = null;
        packetExtensions = new CopyOnWriteArrayList();
        properties = new HashMap();
        error = null;
        to = bundle.getString("ext_to");
        from = bundle.getString("ext_from");
        chId = bundle.getString("ext_chid");
        packetID = bundle.getString("ext_pkt_id");
        android.os.Parcelable aparcelable[] = bundle.getParcelableArray("ext_exts");
        if(aparcelable != null)
        {
            packetExtensions = new ArrayList(aparcelable.length);
            int i = 0;
            for(int j = aparcelable.length; i < j; i++)
            {
                CommonPacketExtension commonpacketextension = CommonPacketExtension.parseFromBundle((Bundle)aparcelable[i]);
                if(commonpacketextension != null)
                    packetExtensions.add(commonpacketextension);
            }

        }
        bundle = bundle.getBundle("ext_ERROR");
        if(bundle != null)
            error = new XMPPError(bundle);
    }

    public static String getDefaultLanguage()
    {
        return DEFAULT_LANGUAGE;
    }

    public static String nextID()
    {
        miui/push/Packet;
        JVM INSTR monitorenter ;
        Object obj;
        obj = JVM INSTR new #74  <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        obj = ((StringBuilder) (obj)).append(prefix);
        long l = id;
        id = 1L + l;
        obj = ((StringBuilder) (obj)).append(Long.toString(l)).toString();
        miui/push/Packet;
        JVM INSTR monitorexit ;
        return ((String) (obj));
        Exception exception;
        exception;
        throw exception;
    }

    public static void setDefaultXmlns(String s)
    {
        DEFAULT_XML_NS = s;
    }

    public void addExtension(CommonPacketExtension commonpacketextension)
    {
        packetExtensions.add(commonpacketextension);
    }

    public void deleteProperty(String s)
    {
        this;
        JVM INSTR monitorenter ;
        Map map = properties;
        if(map != null)
            break MISSING_BLOCK_LABEL_14;
        this;
        JVM INSTR monitorexit ;
        return;
        properties.remove(s);
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        flag = true;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (Packet)obj;
        if(error == null ? ((Packet) (obj)).error != null : error.equals(((Packet) (obj)).error) ^ true)
            return false;
        if(from == null ? ((Packet) (obj)).from != null : from.equals(((Packet) (obj)).from) ^ true)
            return false;
        if(!packetExtensions.equals(((Packet) (obj)).packetExtensions))
            return false;
        if(packetID == null ? ((Packet) (obj)).packetID != null : packetID.equals(((Packet) (obj)).packetID) ^ true)
            return false;
        if(chId == null ? ((Packet) (obj)).chId != null : chId.equals(((Packet) (obj)).chId) ^ true)
            return false;
        if(properties == null ? ((Packet) (obj)).properties != null : properties.equals(((Packet) (obj)).properties) ^ true)
            return false;
        if(to == null ? ((Packet) (obj)).to != null : to.equals(((Packet) (obj)).to) ^ true)
            return false;
        if(xmlns == null) goto _L2; else goto _L1
_L1:
        flag = xmlns.equals(((Packet) (obj)).xmlns) ^ true;
_L4:
        return flag ^ true;
_L2:
        if(((Packet) (obj)).xmlns == null)
            flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getChannelId()
    {
        return chId;
    }

    public XMPPError getError()
    {
        return error;
    }

    public CommonPacketExtension getExtension(String s)
    {
        return getExtension(s, null);
    }

    public CommonPacketExtension getExtension(String s, String s1)
    {
        for(Iterator iterator = packetExtensions.iterator(); iterator.hasNext();)
        {
            CommonPacketExtension commonpacketextension = (CommonPacketExtension)iterator.next();
            if((s1 == null || s1.equals(commonpacketextension.getNamespace())) && s.equals(commonpacketextension.getElementName()))
                return commonpacketextension;
        }

        return null;
    }

    public Collection getExtensions()
    {
        this;
        JVM INSTR monitorenter ;
        Object obj;
        if(packetExtensions != null)
            break MISSING_BLOCK_LABEL_17;
        obj = Collections.emptyList();
        this;
        JVM INSTR monitorexit ;
        return ((Collection) (obj));
        obj = JVM INSTR new #142 <Class ArrayList>;
        ((ArrayList) (obj)).ArrayList(packetExtensions);
        obj = Collections.unmodifiableList(((List) (obj)));
        this;
        JVM INSTR monitorexit ;
        return ((Collection) (obj));
        Exception exception;
        exception;
        throw exception;
    }

    protected String getExtensionsXML()
    {
        this;
        JVM INSTR monitorenter ;
        StringBuilder stringbuilder;
        stringbuilder = JVM INSTR new #74  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        for(Iterator iterator = getExtensions().iterator(); iterator.hasNext(); stringbuilder.append(((CommonPacketExtension)iterator.next()).toXML()));
        break MISSING_BLOCK_LABEL_56;
        Exception exception;
        exception;
        throw exception;
        if(properties == null || !(properties.isEmpty() ^ true)) goto _L2; else goto _L1
_L1:
        Iterator iterator1;
        stringbuilder.append("<properties xmlns=\"http://www.jivesoftware.com/xmlns/xmpp/properties\">");
        iterator1 = getPropertyNames().iterator();
_L7:
        if(!iterator1.hasNext()) goto _L4; else goto _L3
_L3:
        Object obj1;
        String s = (String)iterator1.next();
        obj1 = getProperty(s);
        stringbuilder.append("<property>");
        stringbuilder.append("<name>").append(StringUtils.escapeForXML(s)).append("</name>");
        stringbuilder.append("<value type=\"");
        if(!(obj1 instanceof Integer)) goto _L6; else goto _L5
_L5:
        stringbuilder.append("integer\">").append(obj1).append("</value>");
_L8:
        stringbuilder.append("</property>");
          goto _L7
_L6:
label0:
        {
            if(!(obj1 instanceof Long))
                break label0;
            stringbuilder.append("long\">").append(obj1).append("</value>");
        }
          goto _L8
label1:
        {
            if(!(obj1 instanceof Float))
                break label1;
            stringbuilder.append("float\">").append(obj1).append("</value>");
        }
          goto _L8
label2:
        {
            if(!(obj1 instanceof Double))
                break label2;
            stringbuilder.append("double\">").append(obj1).append("</value>");
        }
          goto _L8
label3:
        {
            if(!(obj1 instanceof Boolean))
                break label3;
            stringbuilder.append("boolean\">").append(obj1).append("</value>");
        }
          goto _L8
label4:
        {
            if(!(obj1 instanceof String))
                break label4;
            stringbuilder.append("string\">");
            stringbuilder.append(StringUtils.escapeForXML((String)obj1));
            stringbuilder.append("</value>");
        }
        if(true) goto _L8; else goto _L9
_L9:
        Object obj2;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        Object obj9;
        obj2 = null;
        obj4 = null;
        obj5 = null;
        obj6 = null;
        obj7 = null;
        obj8 = obj2;
        obj9 = obj5;
        Object obj = JVM INSTR new #304 <Class ByteArrayOutputStream>;
        obj8 = obj2;
        obj9 = obj5;
        ((ByteArrayOutputStream) (obj)).ByteArrayOutputStream();
        obj8 = JVM INSTR new #307 <Class ObjectOutputStream>;
        ((ObjectOutputStream) (obj8)).ObjectOutputStream(((java.io.OutputStream) (obj)));
        ((ObjectOutputStream) (obj8)).writeObject(obj1);
        stringbuilder.append("java-object\">");
        stringbuilder.append(StringUtils.encodeBase64(((ByteArrayOutputStream) (obj)).toByteArray())).append("</value>");
        if(obj8 == null) goto _L11; else goto _L10
_L10:
        try
        {
            ((ObjectOutputStream) (obj8)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj8) { }
_L11:
        if(obj == null) goto _L8; else goto _L12
_L12:
        try
        {
            ((ByteArrayOutputStream) (obj)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj) { }
          goto _L8
        Object obj3;
        obj3;
        obj = obj4;
_L18:
        obj8 = obj;
        obj9 = obj7;
        ((Exception) (obj3)).printStackTrace();
        if(obj7 == null) goto _L14; else goto _L13
_L13:
        try
        {
            ((ObjectOutputStream) (obj7)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj8) { }
_L14:
        if(obj == null) goto _L8; else goto _L15
_L15:
        try
        {
            ((ByteArrayOutputStream) (obj)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj) { }
          goto _L8
        obj;
_L17:
        if(obj9 == null)
            break MISSING_BLOCK_LABEL_527;
        try
        {
            ((ObjectOutputStream) (obj9)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj9) { }
        if(obj8 == null)
            break MISSING_BLOCK_LABEL_537;
        try
        {
            ((ByteArrayOutputStream) (obj8)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj8) { }
        throw obj;
_L4:
        stringbuilder.append("</properties>");
_L2:
        obj = stringbuilder.toString();
        this;
        JVM INSTR monitorexit ;
        return ((String) (obj));
        obj3;
        obj8 = obj;
        obj9 = obj6;
        obj = obj3;
        continue; /* Loop/switch isn't completed */
        obj3;
        obj9 = obj8;
        obj8 = obj;
        obj = obj3;
        if(true) goto _L17; else goto _L16
_L16:
        obj3;
          goto _L18
        obj3;
        obj7 = obj8;
          goto _L18
    }

    public String getFrom()
    {
        return from;
    }

    public String getPacketID()
    {
        if("ID_NOT_AVAILABLE".equals(packetID))
            return null;
        if(packetID == null)
            packetID = nextID();
        return packetID;
    }

    public Object getProperty(String s)
    {
        this;
        JVM INSTR monitorenter ;
        Map map = properties;
        if(map != null)
            break MISSING_BLOCK_LABEL_15;
        this;
        JVM INSTR monitorexit ;
        return null;
        s = ((String) (properties.get(s)));
        this;
        JVM INSTR monitorexit ;
        return s;
        s;
        throw s;
    }

    public Collection getPropertyNames()
    {
        this;
        JVM INSTR monitorenter ;
        Object obj;
        if(properties != null)
            break MISSING_BLOCK_LABEL_17;
        obj = Collections.emptySet();
        this;
        JVM INSTR monitorexit ;
        return ((Collection) (obj));
        obj = JVM INSTR new #346 <Class HashSet>;
        ((HashSet) (obj)).HashSet(properties.keySet());
        obj = Collections.unmodifiableSet(((java.util.Set) (obj)));
        this;
        JVM INSTR monitorexit ;
        return ((Collection) (obj));
        Exception exception;
        exception;
        throw exception;
    }

    public String getTo()
    {
        return to;
    }

    public String getXmlns()
    {
        return xmlns;
    }

    public int hashCode()
    {
        int i = 0;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        if(xmlns != null)
            j = xmlns.hashCode();
        else
            j = 0;
        if(packetID != null)
            k = packetID.hashCode();
        else
            k = 0;
        if(to != null)
            l = to.hashCode();
        else
            l = 0;
        if(from != null)
            i1 = from.hashCode();
        else
            i1 = 0;
        if(chId != null)
            j1 = chId.hashCode();
        else
            j1 = 0;
        k1 = packetExtensions.hashCode();
        l1 = properties.hashCode();
        if(error != null)
            i = error.hashCode();
        return ((((((j * 31 + k) * 31 + l) * 31 + i1) * 31 + j1) * 31 + k1) * 31 + l1) * 31 + i;
    }

    public void removeExtension(CommonPacketExtension commonpacketextension)
    {
        packetExtensions.remove(commonpacketextension);
    }

    public void setChannelId(String s)
    {
        chId = s;
    }

    public void setError(XMPPError xmpperror)
    {
        error = xmpperror;
    }

    public void setFrom(String s)
    {
        from = s;
    }

    public void setPacketID(String s)
    {
        packetID = s;
    }

    public void setProperty(String s, Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        if(!(obj instanceof Serializable))
        {
            s = JVM INSTR new #378 <Class IllegalArgumentException>;
            s.IllegalArgumentException("Value must be serialiazble");
            throw s;
        }
        break MISSING_BLOCK_LABEL_27;
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        properties.put(s, obj);
        this;
        JVM INSTR monitorexit ;
    }

    public void setTo(String s)
    {
        to = s;
    }

    public Bundle toBundle()
    {
        Bundle bundle = new Bundle();
        if(!TextUtils.isEmpty(xmlns))
            bundle.putString("ext_ns", xmlns);
        if(!TextUtils.isEmpty(from))
            bundle.putString("ext_from", from);
        if(!TextUtils.isEmpty(to))
            bundle.putString("ext_to", to);
        if(!TextUtils.isEmpty(packetID))
            bundle.putString("ext_pkt_id", packetID);
        if(!TextUtils.isEmpty(chId))
            bundle.putString("ext_chid", chId);
        if(error != null)
            bundle.putBundle("ext_ERROR", error.toBundle());
        if(packetExtensions != null)
        {
            Bundle abundle[] = new Bundle[packetExtensions.size()];
            int i = 0;
            Iterator iterator = packetExtensions.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Bundle bundle1 = ((CommonPacketExtension)iterator.next()).toBundle();
                if(bundle1 != null)
                {
                    abundle[i] = bundle1;
                    i++;
                }
            } while(true);
            bundle.putParcelableArray("ext_exts", abundle);
        }
        return bundle;
    }

    public abstract String toXML();

    protected static final String DEFAULT_LANGUAGE = Locale.getDefault().getLanguage().toLowerCase();
    private static String DEFAULT_XML_NS = null;
    public static final String ID_NOT_AVAILABLE = "ID_NOT_AVAILABLE";
    public static final DateFormat XEP_0082_UTC_FORMAT;
    private static long id = 0L;
    private static String prefix = (new StringBuilder()).append(StringUtils.randomString(5)).append("-").toString();
    private String chId;
    private XMPPError error;
    private String from;
    private List packetExtensions;
    private String packetID;
    private final Map properties;
    private String to;
    private String xmlns;

    static 
    {
        XEP_0082_UTC_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        XEP_0082_UTC_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
}

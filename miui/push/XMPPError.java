// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.push;

import android.os.Bundle;
import java.util.*;

// Referenced classes of package miui.push:
//            CommonPacketExtension, PacketExtension

public class XMPPError
{
    public static class Condition
    {

        static String _2D_get0(Condition condition1)
        {
            return condition1.value;
        }

        public String toString()
        {
            return value;
        }

        public static final Condition bad_request = new Condition("bad-request");
        public static final Condition conflict = new Condition("conflict");
        public static final Condition feature_not_implemented = new Condition("feature-not-implemented");
        public static final Condition forbidden = new Condition("forbidden");
        public static final Condition gone = new Condition("gone");
        public static final Condition interna_server_error = new Condition("internal-server-error");
        public static final Condition item_not_found = new Condition("item-not-found");
        public static final Condition jid_malformed = new Condition("jid-malformed");
        public static final Condition no_acceptable = new Condition("not-acceptable");
        public static final Condition not_allowed = new Condition("not-allowed");
        public static final Condition not_authorized = new Condition("not-authorized");
        public static final Condition payment_required = new Condition("payment-required");
        public static final Condition recipient_unavailable = new Condition("recipient-unavailable");
        public static final Condition redirect = new Condition("redirect");
        public static final Condition registration_required = new Condition("registration-required");
        public static final Condition remote_server_error = new Condition("remote-server-error");
        public static final Condition remote_server_not_found = new Condition("remote-server-not-found");
        public static final Condition remote_server_timeout = new Condition("remote-server-timeout");
        public static final Condition request_timeout = new Condition("request-timeout");
        public static final Condition resource_constraint = new Condition("resource-constraint");
        public static final Condition service_unavailable = new Condition("service-unavailable");
        public static final Condition subscription_required = new Condition("subscription-required");
        public static final Condition undefined_condition = new Condition("undefined-condition");
        public static final Condition unexpected_request = new Condition("unexpected-request");
        private String value;


        public Condition(String s)
        {
            value = s;
        }
    }


    public XMPPError(int i)
    {
        applicationExtensions = null;
        code = i;
        message = null;
    }

    public XMPPError(int i, String s)
    {
        applicationExtensions = null;
        code = i;
        message = s;
    }

    public XMPPError(int i, String s, String s1, String s2, String s3, List list)
    {
        applicationExtensions = null;
        code = i;
        type = s;
        reason = s1;
        condition = s2;
        message = s3;
        applicationExtensions = list;
    }

    public XMPPError(Bundle bundle)
    {
        applicationExtensions = null;
        code = bundle.getInt("ext_err_code");
        if(bundle.containsKey("ext_err_type"))
            type = bundle.getString("ext_err_type");
        condition = bundle.getString("ext_err_cond");
        reason = bundle.getString("ext_err_reason");
        message = bundle.getString("ext_err_msg");
        android.os.Parcelable aparcelable[] = bundle.getParcelableArray("ext_exts");
        if(aparcelable != null)
        {
            applicationExtensions = new ArrayList(aparcelable.length);
            int i = 0;
            for(int j = aparcelable.length; i < j; i++)
            {
                bundle = CommonPacketExtension.parseFromBundle((Bundle)aparcelable[i]);
                if(bundle != null)
                    applicationExtensions.add(bundle);
            }

        }
    }

    public XMPPError(Condition condition1)
    {
        applicationExtensions = null;
        init(condition1);
        message = null;
    }

    public XMPPError(Condition condition1, String s)
    {
        applicationExtensions = null;
        init(condition1);
        message = s;
    }

    private void init(Condition condition1)
    {
        condition = Condition._2D_get0(condition1);
    }

    public void addExtension(CommonPacketExtension commonpacketextension)
    {
        this;
        JVM INSTR monitorenter ;
        if(applicationExtensions == null)
        {
            ArrayList arraylist = JVM INSTR new #72  <Class ArrayList>;
            arraylist.ArrayList();
            applicationExtensions = arraylist;
        }
        applicationExtensions.add(commonpacketextension);
        this;
        JVM INSTR monitorexit ;
        return;
        commonpacketextension;
        throw commonpacketextension;
    }

    public int getCode()
    {
        return code;
    }

    public String getCondition()
    {
        return condition;
    }

    public PacketExtension getExtension(String s, String s1)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = applicationExtensions;
          goto _L1
_L3:
        this;
        JVM INSTR monitorexit ;
        return null;
_L1:
        if(obj == null || s == null || s1 == null) goto _L3; else goto _L2
_L2:
        Iterator iterator = applicationExtensions.iterator();
        boolean flag;
        do
        {
            do
            {
                if(!iterator.hasNext())
                    break MISSING_BLOCK_LABEL_89;
                obj = (CommonPacketExtension)iterator.next();
            } while(!s.equals(((PacketExtension) (obj)).getElementName()));
            flag = s1.equals(((PacketExtension) (obj)).getNamespace());
        } while(!flag);
        this;
        JVM INSTR monitorexit ;
        return ((PacketExtension) (obj));
        this;
        JVM INSTR monitorexit ;
        return null;
        s;
        throw s;
    }

    public List getExtensions()
    {
        this;
        JVM INSTR monitorenter ;
        List list;
        if(applicationExtensions != null)
            break MISSING_BLOCK_LABEL_17;
        list = Collections.emptyList();
        this;
        JVM INSTR monitorexit ;
        return list;
        list = Collections.unmodifiableList(applicationExtensions);
        this;
        JVM INSTR monitorexit ;
        return list;
        Exception exception;
        exception;
        throw exception;
    }

    public String getMessage()
    {
        return message;
    }

    public String getReason()
    {
        return reason;
    }

    public String getType()
    {
        return type;
    }

    public void setExtension(List list)
    {
        this;
        JVM INSTR monitorenter ;
        applicationExtensions = list;
        this;
        JVM INSTR monitorexit ;
        return;
        list;
        throw list;
    }

    public Bundle toBundle()
    {
        Bundle bundle = new Bundle();
        if(type != null)
            bundle.putString("ext_err_type", type);
        bundle.putInt("ext_err_code", code);
        if(reason != null)
            bundle.putString("ext_err_reason", reason);
        if(condition != null)
            bundle.putString("ext_err_cond", condition);
        if(message != null)
            bundle.putString("ext_err_msg", message);
        if(applicationExtensions != null)
        {
            Bundle abundle[] = new Bundle[applicationExtensions.size()];
            int i = 0;
            Iterator iterator = applicationExtensions.iterator();
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

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        if(condition != null)
            stringbuilder.append(condition);
        stringbuilder.append("(").append(code).append(")");
        if(message != null)
            stringbuilder.append(" ").append(message);
        return stringbuilder.toString();
    }

    public String toXML()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("<error code=\"").append(code).append("\"");
        if(type != null)
        {
            stringbuilder.append(" type=\"");
            stringbuilder.append(type);
            stringbuilder.append("\"");
        }
        if(reason != null)
        {
            stringbuilder.append(" reason=\"");
            stringbuilder.append(reason);
            stringbuilder.append("\"");
        }
        stringbuilder.append(">");
        if(condition != null)
        {
            stringbuilder.append("<").append(condition);
            stringbuilder.append(" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\"/>");
        }
        if(message != null)
        {
            stringbuilder.append("<text xml:lang=\"en\" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\">");
            stringbuilder.append(message);
            stringbuilder.append("</text>");
        }
        for(Iterator iterator = getExtensions().iterator(); iterator.hasNext(); stringbuilder.append(((CommonPacketExtension)iterator.next()).toXML()));
        stringbuilder.append("</error>");
        return stringbuilder.toString();
    }

    private List applicationExtensions;
    private int code;
    private String condition;
    private String message;
    private String reason;
    private String type;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.push;

import android.os.Bundle;

// Referenced classes of package miui.push:
//            Packet, StringUtils, XMPPError

public class IQ extends Packet
{
    public static class Type
    {

        public static Type fromString(String s)
        {
            if(s == null)
                return null;
            s = s.toLowerCase();
            if(GET.toString().equals(s))
                return GET;
            if(SET.toString().equals(s))
                return SET;
            if(ERROR.toString().equals(s))
                return ERROR;
            if(RESULT.toString().equals(s))
                return RESULT;
            else
                return null;
        }

        public String toString()
        {
            return value;
        }

        public static final Type ERROR = new Type("error");
        public static final Type GET = new Type("get");
        public static final Type RESULT = new Type("result");
        public static final Type SET = new Type("set");
        private String value;


        private Type(String s)
        {
            value = s;
        }
    }


    public IQ()
    {
        type = Type.GET;
    }

    public IQ(Bundle bundle)
    {
        super(bundle);
        type = Type.GET;
        if(bundle.containsKey("ext_iq_type"))
            type = Type.fromString(bundle.getString("ext_iq_type"));
    }

    public static IQ createErrorResponse(IQ iq, XMPPError xmpperror)
    {
        if(iq.getType() != Type.GET && iq.getType() != Type.SET)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("IQ must be of type 'set' or 'get'. Original IQ: ").append(iq.toXML()).toString());
        } else
        {
            IQ iq1 = new IQ(iq) {

                public String getChildElementXML()
                {
                    return request.getChildElementXML();
                }

                final IQ val$request;

            
            {
                request = iq;
                super();
            }
            }
;
            iq1.setType(Type.ERROR);
            iq1.setPacketID(iq.getPacketID());
            iq1.setFrom(iq.getTo());
            iq1.setTo(iq.getFrom());
            iq1.setError(xmpperror);
            return iq1;
        }
    }

    public static IQ createResultIQ(IQ iq)
    {
        if(iq.getType() != Type.GET && iq.getType() != Type.SET)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("IQ must be of type 'set' or 'get'. Original IQ: ").append(iq.toXML()).toString());
        } else
        {
            IQ iq1 = new IQ() {

                public String getChildElementXML()
                {
                    return null;
                }

            }
;
            iq1.setType(Type.RESULT);
            iq1.setPacketID(iq.getPacketID());
            iq1.setFrom(iq.getTo());
            iq1.setTo(iq.getFrom());
            return iq1;
        }
    }

    public String getChildElementXML()
    {
        return null;
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type1)
    {
        if(type1 == null)
            type = Type.GET;
        else
            type = type1;
    }

    public Bundle toBundle()
    {
        Bundle bundle = super.toBundle();
        if(type != null)
            bundle.putString("ext_iq_type", type.toString());
        return bundle;
    }

    public String toXML()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("<iq ");
        if(getPacketID() != null)
            stringbuilder.append("id=\"").append(getPacketID()).append("\" ");
        if(getTo() != null)
            stringbuilder.append("to=\"").append(StringUtils.escapeForXML(getTo())).append("\" ");
        if(getFrom() != null)
            stringbuilder.append("from=\"").append(StringUtils.escapeForXML(getFrom())).append("\" ");
        if(getChannelId() != null)
            stringbuilder.append("chid=\"").append(StringUtils.escapeForXML(getChannelId())).append("\" ");
        Object obj;
        if(type == null)
            stringbuilder.append("type=\"get\">");
        else
            stringbuilder.append("type=\"").append(getType()).append("\">");
        obj = getChildElementXML();
        if(obj != null)
            stringbuilder.append(((String) (obj)));
        stringbuilder.append(getExtensionsXML());
        obj = getError();
        if(obj != null)
            stringbuilder.append(((XMPPError) (obj)).toXML());
        stringbuilder.append("</iq>");
        return stringbuilder.toString();
    }

    private Type type;
}

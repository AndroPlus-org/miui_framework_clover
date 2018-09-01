// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.push;

import android.os.Bundle;

// Referenced classes of package miui.push:
//            Packet, StringUtils, XMPPError

public class Presence extends Packet
{
    public static final class Mode extends Enum
    {

        public static Mode valueOf(String s)
        {
            return (Mode)Enum.valueOf(miui/push/Presence$Mode, s);
        }

        public static Mode[] values()
        {
            return $VALUES;
        }

        private static final Mode $VALUES[];
        public static final Mode available;
        public static final Mode away;
        public static final Mode chat;
        public static final Mode dnd;
        public static final Mode xa;

        static 
        {
            chat = new Mode("chat", 0);
            available = new Mode("available", 1);
            away = new Mode("away", 2);
            xa = new Mode("xa", 3);
            dnd = new Mode("dnd", 4);
            $VALUES = (new Mode[] {
                chat, available, away, xa, dnd
            });
        }

        private Mode(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class Type extends Enum
    {

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(miui/push/Presence$Type, s);
        }

        public static Type[] values()
        {
            return $VALUES;
        }

        private static final Type $VALUES[];
        public static final Type available;
        public static final Type error;
        public static final Type probe;
        public static final Type subscribe;
        public static final Type subscribed;
        public static final Type unavailable;
        public static final Type unsubscribe;
        public static final Type unsubscribed;

        static 
        {
            available = new Type("available", 0);
            unavailable = new Type("unavailable", 1);
            subscribe = new Type("subscribe", 2);
            subscribed = new Type("subscribed", 3);
            unsubscribe = new Type("unsubscribe", 4);
            unsubscribed = new Type("unsubscribed", 5);
            error = new Type("error", 6);
            probe = new Type("probe", 7);
            $VALUES = (new Type[] {
                available, unavailable, subscribe, subscribed, unsubscribe, unsubscribed, error, probe
            });
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }


    public Presence(Bundle bundle)
    {
        super(bundle);
        type = Type.available;
        status = null;
        priority = 0x80000000;
        mode = null;
        if(bundle.containsKey("ext_pres_type"))
            type = Type.valueOf(bundle.getString("ext_pres_type"));
        if(bundle.containsKey("ext_pres_status"))
            status = bundle.getString("ext_pres_status");
        if(bundle.containsKey("ext_pres_prio"))
            priority = bundle.getInt("ext_pres_prio");
        if(bundle.containsKey("ext_pres_mode"))
            mode = Mode.valueOf(bundle.getString("ext_pres_mode"));
    }

    public Presence(Type type1)
    {
        type = Type.available;
        status = null;
        priority = 0x80000000;
        mode = null;
        setType(type1);
    }

    public Presence(Type type1, String s, int i, Mode mode1)
    {
        type = Type.available;
        status = null;
        priority = 0x80000000;
        mode = null;
        setType(type1);
        setStatus(s);
        setPriority(i);
        setMode(mode1);
    }

    public Mode getMode()
    {
        return mode;
    }

    public int getPriority()
    {
        return priority;
    }

    public String getStatus()
    {
        return status;
    }

    public Type getType()
    {
        return type;
    }

    public boolean isAvailable()
    {
        boolean flag;
        if(type == Type.available)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isAway()
    {
        boolean flag = true;
        if(type != Type.available) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
        if(mode == Mode.away) goto _L4; else goto _L3
_L3:
        if(mode != Mode.xa) goto _L6; else goto _L5
_L5:
        flag1 = flag;
_L4:
        return flag1;
_L6:
        flag1 = flag;
        if(mode == Mode.dnd)
            continue; /* Loop/switch isn't completed */
_L2:
        flag1 = false;
        if(true) goto _L4; else goto _L7
_L7:
    }

    public void setMode(Mode mode1)
    {
        mode = mode1;
    }

    public void setPriority(int i)
    {
        if(i < -128 || i > 128)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Priority value ").append(i).append(" is not valid. Valid range is -128 through 128.").toString());
        } else
        {
            priority = i;
            return;
        }
    }

    public void setStatus(String s)
    {
        status = s;
    }

    public void setType(Type type1)
    {
        if(type1 == null)
        {
            throw new NullPointerException("Type cannot be null");
        } else
        {
            type = type1;
            return;
        }
    }

    public Bundle toBundle()
    {
        Bundle bundle = super.toBundle();
        if(type != null)
            bundle.putString("ext_pres_type", type.toString());
        if(status != null)
            bundle.putString("ext_pres_status", status);
        if(priority != 0x80000000)
            bundle.putInt("ext_pres_prio", priority);
        if(mode != null && mode != Mode.available)
            bundle.putString("ext_pres_mode", mode.toString());
        return bundle;
    }

    public String toXML()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("<presence");
        if(getXmlns() != null)
            stringbuilder.append(" xmlns=\"").append(getXmlns()).append("\"");
        if(getPacketID() != null)
            stringbuilder.append(" id=\"").append(getPacketID()).append("\"");
        if(getTo() != null)
            stringbuilder.append(" to=\"").append(StringUtils.escapeForXML(getTo())).append("\"");
        if(getFrom() != null)
            stringbuilder.append(" from=\"").append(StringUtils.escapeForXML(getFrom())).append("\"");
        if(getChannelId() != null)
            stringbuilder.append(" chid=\"").append(StringUtils.escapeForXML(getChannelId())).append("\"");
        if(type != null)
            stringbuilder.append(" type=\"").append(type).append("\"");
        stringbuilder.append(">");
        if(status != null)
            stringbuilder.append("<status>").append(StringUtils.escapeForXML(status)).append("</status>");
        if(priority != 0x80000000)
            stringbuilder.append("<priority>").append(priority).append("</priority>");
        if(mode != null && mode != Mode.available)
            stringbuilder.append("<show>").append(mode).append("</show>");
        stringbuilder.append(getExtensionsXML());
        XMPPError xmpperror = getError();
        if(xmpperror != null)
            stringbuilder.append(xmpperror.toXML());
        stringbuilder.append("</presence>");
        return stringbuilder.toString();
    }

    private Mode mode;
    private int priority;
    private String status;
    private Type type;
}

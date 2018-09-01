// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.push;

import android.os.Bundle;
import android.text.TextUtils;

// Referenced classes of package miui.push:
//            Packet, StringUtils, XMPPError

public class Message extends Packet
{

    public Message()
    {
        type = null;
        thread = null;
        mTransient = false;
        seq = "";
        mseq = "";
        fseq = "";
        status = "";
        mEncrypted = false;
    }

    public Message(Bundle bundle)
    {
        super(bundle);
        type = null;
        thread = null;
        mTransient = false;
        seq = "";
        mseq = "";
        fseq = "";
        status = "";
        mEncrypted = false;
        type = bundle.getString("ext_msg_type");
        language = bundle.getString("ext_msg_lang");
        thread = bundle.getString("ext_msg_thread");
        mSubject = bundle.getString("ext_msg_sub");
        mBody = bundle.getString("ext_msg_body");
        mBodyEncoding = bundle.getString("ext_body_encode");
        mAppId = bundle.getString("ext_msg_appid");
        mTransient = bundle.getBoolean("ext_msg_trans", false);
        seq = bundle.getString("ext_msg_seq");
        mseq = bundle.getString("ext_msg_mseq");
        fseq = bundle.getString("ext_msg_fseq");
        status = bundle.getString("ext_msg_status");
    }

    public Message(String s)
    {
        type = null;
        thread = null;
        mTransient = false;
        seq = "";
        mseq = "";
        fseq = "";
        status = "";
        mEncrypted = false;
        setTo(s);
    }

    public Message(String s, String s1)
    {
        type = null;
        thread = null;
        mTransient = false;
        seq = "";
        mseq = "";
        fseq = "";
        status = "";
        mEncrypted = false;
        setTo(s);
        type = s1;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (Message)obj;
        if(!super.equals(obj))
            return false;
        if(mBody == null ? ((Message) (obj)).mBody != null : mBody.equals(((Message) (obj)).mBody) ^ true)
            return false;
        if(language == null ? ((Message) (obj)).language != null : language.equals(((Message) (obj)).language) ^ true)
            return false;
        if(mSubject == null ? ((Message) (obj)).mSubject != null : mSubject.equals(((Message) (obj)).mSubject) ^ true)
            return false;
        if(thread == null ? ((Message) (obj)).thread != null : thread.equals(((Message) (obj)).thread) ^ true)
            return false;
        if(type != ((Message) (obj)).type)
            flag = false;
        return flag;
    }

    public String getAppId()
    {
        return mAppId;
    }

    public String getBody()
    {
        return mBody;
    }

    public String getBodyEncoding()
    {
        return mBodyEncoding;
    }

    public boolean getEncrypted()
    {
        return mEncrypted;
    }

    public String getFSeq()
    {
        return fseq;
    }

    public String getLanguage()
    {
        return language;
    }

    public String getMSeq()
    {
        return mseq;
    }

    public String getSeq()
    {
        return seq;
    }

    public String getStatus()
    {
        return status;
    }

    public String getSubject()
    {
        return mSubject;
    }

    public String getThread()
    {
        return thread;
    }

    public String getType()
    {
        return type;
    }

    public int hashCode()
    {
        int i = 0;
        int j;
        int k;
        int l;
        int i1;
        if(type != null)
            j = type.hashCode();
        else
            j = 0;
        if(mBody != null)
            k = mBody.hashCode();
        else
            k = 0;
        if(thread != null)
            l = thread.hashCode();
        else
            l = 0;
        if(language != null)
            i1 = language.hashCode();
        else
            i1 = 0;
        if(mSubject != null)
            i = mSubject.hashCode();
        return (((j * 31 + k) * 31 + l) * 31 + i1) * 31 + i;
    }

    public void setAppId(String s)
    {
        mAppId = s;
    }

    public void setBody(String s)
    {
        mBody = s;
    }

    public void setBody(String s, String s1)
    {
        mBody = s;
        mBodyEncoding = s1;
    }

    public void setEncrypted(boolean flag)
    {
        mEncrypted = flag;
    }

    public void setFSeq(String s)
    {
        fseq = s;
    }

    public void setIsTransient(boolean flag)
    {
        mTransient = flag;
    }

    public void setLanguage(String s)
    {
        language = s;
    }

    public void setMSeq(String s)
    {
        mseq = s;
    }

    public void setSeq(String s)
    {
        seq = s;
    }

    public void setStatus(String s)
    {
        status = s;
    }

    public void setSubject(String s)
    {
        mSubject = s;
    }

    public void setThread(String s)
    {
        thread = s;
    }

    public void setType(String s)
    {
        type = s;
    }

    public Bundle toBundle()
    {
        Bundle bundle = super.toBundle();
        if(!TextUtils.isEmpty(type))
            bundle.putString("ext_msg_type", type);
        if(language != null)
            bundle.putString("ext_msg_lang", language);
        if(mSubject != null)
            bundle.putString("ext_msg_sub", mSubject);
        if(mBody != null)
            bundle.putString("ext_msg_body", mBody);
        if(!TextUtils.isEmpty(mBodyEncoding))
            bundle.putString("ext_body_encode", mBodyEncoding);
        if(thread != null)
            bundle.putString("ext_msg_thread", thread);
        if(mAppId != null)
            bundle.putString("ext_msg_appid", mAppId);
        if(mTransient)
            bundle.putBoolean("ext_msg_trans", true);
        if(!TextUtils.isEmpty(seq))
            bundle.putString("ext_msg_seq", seq);
        if(!TextUtils.isEmpty(mseq))
            bundle.putString("ext_msg_mseq", mseq);
        if(!TextUtils.isEmpty(fseq))
            bundle.putString("ext_msg_fseq", fseq);
        if(!TextUtils.isEmpty(status))
            bundle.putString("ext_msg_status", status);
        return bundle;
    }

    public String toXML()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("<message");
        if(getXmlns() != null)
            stringbuilder.append(" xmlns=\"").append(getXmlns()).append("\"");
        if(language != null)
            stringbuilder.append(" xml:lang=\"").append(getLanguage()).append("\"");
        if(getPacketID() != null)
            stringbuilder.append(" id=\"").append(getPacketID()).append("\"");
        if(getTo() != null)
            stringbuilder.append(" to=\"").append(StringUtils.escapeForXML(getTo())).append("\"");
        if(!TextUtils.isEmpty(getSeq()))
            stringbuilder.append(" seq=\"").append(getSeq()).append("\"");
        if(!TextUtils.isEmpty(getMSeq()))
            stringbuilder.append(" mseq=\"").append(getMSeq()).append("\"");
        if(!TextUtils.isEmpty(getFSeq()))
            stringbuilder.append(" fseq=\"").append(getFSeq()).append("\"");
        if(!TextUtils.isEmpty(getStatus()))
            stringbuilder.append(" status=\"").append(getStatus()).append("\"");
        if(getFrom() != null)
            stringbuilder.append(" from=\"").append(StringUtils.escapeForXML(getFrom())).append("\"");
        if(getChannelId() != null)
            stringbuilder.append(" chid=\"").append(StringUtils.escapeForXML(getChannelId())).append("\"");
        if(mTransient)
            stringbuilder.append(" transient=\"true\"");
        if(!TextUtils.isEmpty(mAppId))
            stringbuilder.append(" appid=\"").append(getAppId()).append("\"");
        if(!TextUtils.isEmpty(type))
            stringbuilder.append(" type=\"").append(type).append("\"");
        if(mEncrypted)
            stringbuilder.append(" s=\"1\"");
        stringbuilder.append(">");
        if(mSubject != null)
        {
            stringbuilder.append("<subject>").append(StringUtils.escapeForXML(mSubject));
            stringbuilder.append("</subject>");
        }
        if(mBody != null)
        {
            stringbuilder.append("<body");
            if(!TextUtils.isEmpty(mBodyEncoding))
                stringbuilder.append(" encode=\"").append(mBodyEncoding).append("\"");
            stringbuilder.append(">").append(StringUtils.escapeForXML(mBody)).append("</body>");
        }
        if(thread != null)
            stringbuilder.append("<thread>").append(thread).append("</thread>");
        if("error".equalsIgnoreCase(type))
        {
            XMPPError xmpperror = getError();
            if(xmpperror != null)
                stringbuilder.append(xmpperror.toXML());
        }
        stringbuilder.append(getExtensionsXML());
        stringbuilder.append("</message>");
        return stringbuilder.toString();
    }

    public static final String MSG_TYPE_CHAT = "chat";
    public static final String MSG_TYPE_ERROR = "error";
    public static final String MSG_TYPE_GROUPCHAT = "groupchat";
    public static final String MSG_TYPE_HEADLINE = "hearline";
    public static final String MSG_TYPE_NORMAL = "normal";
    public static final String MSG_TYPE_PPL = "ppl";
    private String fseq;
    private String language;
    private String mAppId;
    private String mBody;
    private String mBodyEncoding;
    private boolean mEncrypted;
    private String mSubject;
    private boolean mTransient;
    private String mseq;
    private String seq;
    private String status;
    private String thread;
    private String type;
}

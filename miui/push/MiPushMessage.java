// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.push;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class MiPushMessage
{

    public MiPushMessage()
    {
        extra = new HashMap();
    }

    public static MiPushMessage fromBundle(Bundle bundle)
    {
        MiPushMessage mipushmessage = new MiPushMessage();
        mipushmessage.messageId = bundle.getString("messageId");
        mipushmessage.messageType = bundle.getInt("messageType");
        mipushmessage.passThrough = bundle.getInt("passThrough");
        mipushmessage.alias = bundle.getString("alias");
        mipushmessage.topic = bundle.getString("topic");
        mipushmessage.content = bundle.getString("content");
        mipushmessage.description = bundle.getString("description");
        mipushmessage.title = bundle.getString("title");
        mipushmessage.isNotified = bundle.getBoolean("isNotified");
        mipushmessage.notifyId = bundle.getInt("notifyId");
        mipushmessage.notifyType = bundle.getInt("notifyType");
        mipushmessage.category = bundle.getString("category");
        mipushmessage.extra = (HashMap)bundle.getSerializable("extra");
        return mipushmessage;
    }

    public String getAlias()
    {
        return alias;
    }

    public String getCategory()
    {
        return category;
    }

    public String getContent()
    {
        return content;
    }

    public String getDescription()
    {
        return description;
    }

    public Map getExtra()
    {
        return extra;
    }

    public String getMessageId()
    {
        return messageId;
    }

    public int getMessageType()
    {
        return messageType;
    }

    public int getNotifyId()
    {
        return notifyId;
    }

    public int getNotifyType()
    {
        return notifyType;
    }

    public int getPassThrough()
    {
        return passThrough;
    }

    public String getTitle()
    {
        return title;
    }

    public String getTopic()
    {
        return topic;
    }

    public boolean isNotified()
    {
        return isNotified;
    }

    public void setAlias(String s)
    {
        alias = s;
    }

    public void setCategory(String s)
    {
        category = s;
    }

    public void setContent(String s)
    {
        content = s;
    }

    public void setDescription(String s)
    {
        description = s;
    }

    public void setExtra(Map map)
    {
        extra.clear();
        if(map != null)
            extra.putAll(map);
    }

    public void setMessageId(String s)
    {
        messageId = s;
    }

    public void setMessageType(int i)
    {
        messageType = i;
    }

    public void setNotified(boolean flag)
    {
        isNotified = flag;
    }

    public void setNotifyId(int i)
    {
        notifyId = i;
    }

    public void setNotifyType(int i)
    {
        notifyType = i;
    }

    public void setPassThrough(int i)
    {
        passThrough = i;
    }

    public void setTitle(String s)
    {
        title = s;
    }

    public void setTopic(String s)
    {
        topic = s;
    }

    public Bundle toBundle()
    {
        Bundle bundle = new Bundle();
        bundle.putString("messageId", messageId);
        bundle.putInt("passThrough", passThrough);
        bundle.putInt("messageType", messageType);
        if(!TextUtils.isEmpty(alias))
            bundle.putString("alias", alias);
        if(!TextUtils.isEmpty(topic))
            bundle.putString("topic", topic);
        bundle.putString("content", content);
        if(!TextUtils.isEmpty(description))
            bundle.putString("description", description);
        if(!TextUtils.isEmpty(title))
            bundle.putString("title", title);
        bundle.putBoolean("isNotified", isNotified);
        bundle.putInt("notifyId", notifyId);
        bundle.putInt("notifyType", notifyType);
        if(!TextUtils.isEmpty(category))
            bundle.putString("category", category);
        if(extra != null)
            bundle.putSerializable("extra", extra);
        return bundle;
    }

    public String toString()
    {
        return (new StringBuilder()).append("messageId={").append(messageId).append("},passThrough={").append(passThrough).append("},alias={").append(alias).append("},topic={").append(topic).append("},content={").append(content).append("},description={").append(description).append("},title={").append(title).append("},isNotified={").append(isNotified).append("},notifyId={").append(notifyId).append("},notifyType={").append(notifyType).append("}, category={").append(category).append("}, extra={").append(extra).append("}").toString();
    }

    private static final String KEY_ALIAS = "alias";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DESC = "description";
    private static final String KEY_EXTRA = "extra";
    private static final String KEY_MESSAGE_ID = "messageId";
    private static final String KEY_MESSAGE_TYPE = "messageType";
    private static final String KEY_NOTIFIED = "isNotified";
    private static final String KEY_NOTIFY_ID = "notifyId";
    private static final String KEY_NOTIFY_TYPE = "notifyType";
    private static final String KEY_PASS_THROUGH = "passThrough";
    private static final String KEY_TITLE = "title";
    private static final String KEY_TOPIC = "topic";
    public static final int MESSAGE_TYPE_ALIAS = 1;
    public static final int MESSAGE_TYPE_REG = 0;
    public static final int MESSAGE_TYPE_TOPIC = 2;
    private static final long serialVersionUID = 1L;
    private String alias;
    private String category;
    private String content;
    private String description;
    private HashMap extra;
    private boolean isNotified;
    private String messageId;
    private int messageType;
    private int notifyId;
    private int notifyType;
    private int passThrough;
    private String title;
    private String topic;
}

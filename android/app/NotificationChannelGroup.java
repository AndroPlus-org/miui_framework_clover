// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlSerializer;

// Referenced classes of package android.app:
//            NotificationChannel

public final class NotificationChannelGroup
    implements Parcelable
{

    protected NotificationChannelGroup(Parcel parcel)
    {
        mChannels = new ArrayList();
        if(parcel.readByte() != 0)
            mId = parcel.readString();
        else
            mId = null;
        mName = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        parcel.readParcelableList(mChannels, android/app/NotificationChannel.getClassLoader());
    }

    public NotificationChannelGroup(String s, CharSequence charsequence)
    {
        Object obj = null;
        super();
        mChannels = new ArrayList();
        mId = getTrimmedString(s);
        s = obj;
        if(charsequence != null)
            s = getTrimmedString(charsequence.toString());
        mName = s;
    }

    private String getTrimmedString(String s)
    {
        if(s != null && s.length() > 1000)
            return s.substring(0, 1000);
        else
            return s;
    }

    public void addChannel(NotificationChannel notificationchannel)
    {
        mChannels.add(notificationchannel);
    }

    public NotificationChannelGroup clone()
    {
        return new NotificationChannelGroup(getId(), getName());
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (NotificationChannelGroup)obj;
        if(getId() == null ? ((NotificationChannelGroup) (obj)).getId() != null : getId().equals(((NotificationChannelGroup) (obj)).getId()) ^ true)
            return false;
        return getName() == null ? ((NotificationChannelGroup) (obj)).getName() != null : getName().equals(((NotificationChannelGroup) (obj)).getName()) ^ true;
    }

    public List getChannels()
    {
        return mChannels;
    }

    public String getId()
    {
        return mId;
    }

    public CharSequence getName()
    {
        return mName;
    }

    public int hashCode()
    {
        int i;
        int j;
        if(getId() != null)
            i = getId().hashCode();
        else
            i = 0;
        if(getName() != null)
            j = getName().hashCode();
        else
            j = 0;
        return i * 31 + j;
    }

    public JSONObject toJson()
        throws JSONException
    {
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("id", getId());
        jsonobject.put("name", getName());
        return jsonobject;
    }

    public String toString()
    {
        return (new StringBuilder()).append("NotificationChannelGroup{mId='").append(mId).append('\'').append(", mName=").append(mName).append(", mChannels=").append(mChannels).append('}').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(mId != null)
        {
            parcel.writeByte((byte)1);
            parcel.writeString(mId);
        } else
        {
            parcel.writeByte((byte)0);
        }
        TextUtils.writeToParcel(mName, parcel, i);
        parcel.writeParcelableList(mChannels, i);
    }

    public void writeXml(XmlSerializer xmlserializer)
        throws IOException
    {
        xmlserializer.startTag(null, "channelGroup");
        xmlserializer.attribute(null, "id", getId());
        if(getName() != null)
            xmlserializer.attribute(null, "name", getName().toString());
        xmlserializer.endTag(null, "channelGroup");
    }

    private static final String ATT_ID = "id";
    private static final String ATT_NAME = "name";
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NotificationChannelGroup createFromParcel(Parcel parcel)
        {
            return new NotificationChannelGroup(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NotificationChannelGroup[] newArray(int i)
        {
            return new NotificationChannelGroup[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int MAX_TEXT_LENGTH = 1000;
    private static final String TAG_GROUP = "channelGroup";
    private List mChannels;
    private final String mId;
    private CharSequence mName;

}

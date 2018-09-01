// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.ContentResolver;
import android.content.Context;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.internal.util.Preconditions;
import java.io.IOException;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

// Referenced classes of package android.app:
//            Notification

public final class NotificationChannel
    implements Parcelable
{

    protected NotificationChannel(Parcel parcel)
    {
        boolean flag = true;
        super();
        mImportance = -1000;
        mLockscreenVisibility = -1000;
        mSound = android.provider.Settings.System.DEFAULT_NOTIFICATION_URI;
        mLightColor = 0;
        mShowBadge = true;
        mDeleted = false;
        mAudioAttributes = Notification.AUDIO_ATTRIBUTES_DEFAULT;
        mBlockableSystem = false;
        boolean flag1;
        AudioAttributes audioattributes;
        if(parcel.readByte() != 0)
            mId = parcel.readString();
        else
            mId = null;
        if(parcel.readByte() != 0)
            mName = parcel.readString();
        else
            mName = null;
        if(parcel.readByte() != 0)
            mDesc = parcel.readString();
        else
            mDesc = null;
        mImportance = parcel.readInt();
        if(parcel.readByte() != 0)
            flag1 = true;
        else
            flag1 = false;
        mBypassDnd = flag1;
        mLockscreenVisibility = parcel.readInt();
        if(parcel.readByte() != 0)
            mSound = (Uri)Uri.CREATOR.createFromParcel(parcel);
        else
            mSound = null;
        if(parcel.readByte() != 0)
            flag1 = true;
        else
            flag1 = false;
        mLights = flag1;
        mVibration = parcel.createLongArray();
        mUserLockedFields = parcel.readInt();
        if(parcel.readByte() != 0)
            flag1 = true;
        else
            flag1 = false;
        mVibrationEnabled = flag1;
        if(parcel.readByte() != 0)
            flag1 = true;
        else
            flag1 = false;
        mShowBadge = flag1;
        if(parcel.readByte() != 0)
            flag1 = flag;
        else
            flag1 = false;
        mDeleted = flag1;
        if(parcel.readByte() != 0)
            mGroup = parcel.readString();
        else
            mGroup = null;
        if(parcel.readInt() > 0)
            audioattributes = (AudioAttributes)AudioAttributes.CREATOR.createFromParcel(parcel);
        else
            audioattributes = null;
        mAudioAttributes = audioattributes;
        mLightColor = parcel.readInt();
        mBlockableSystem = parcel.readBoolean();
    }

    public NotificationChannel(String s, CharSequence charsequence, int i)
    {
        Object obj = null;
        super();
        mImportance = -1000;
        mLockscreenVisibility = -1000;
        mSound = android.provider.Settings.System.DEFAULT_NOTIFICATION_URI;
        mLightColor = 0;
        mShowBadge = true;
        mDeleted = false;
        mAudioAttributes = Notification.AUDIO_ATTRIBUTES_DEFAULT;
        mBlockableSystem = false;
        mId = getTrimmedString(s);
        s = obj;
        if(charsequence != null)
            s = getTrimmedString(charsequence.toString());
        mName = s;
        mImportance = i;
    }

    private Uri getSoundForBackup(Context context)
    {
        Uri uri = getSound();
        if(uri == null)
            return null;
        context = context.getContentResolver().canonicalize(uri);
        if(context == null)
            return android.provider.Settings.System.DEFAULT_NOTIFICATION_URI;
        else
            return context;
    }

    private String getTrimmedString(String s)
    {
        if(s != null && s.length() > 1000)
            return s.substring(0, 1000);
        else
            return s;
    }

    private static String longArrayToString(long al[])
    {
        StringBuffer stringbuffer = new StringBuffer();
        if(al != null && al.length > 0)
        {
            for(int i = 0; i < al.length - 1; i++)
                stringbuffer.append(al[i]).append(",");

            stringbuffer.append(al[al.length - 1]);
        }
        return stringbuffer.toString();
    }

    private void populateFromXml(XmlPullParser xmlpullparser, boolean flag, Context context)
    {
        boolean flag1 = true;
        boolean flag2;
        Uri uri;
        Uri uri1;
        if(!flag || context != null)
            flag2 = true;
        else
            flag2 = false;
        Preconditions.checkArgument(flag2, "forRestore is true but got null context");
        setDescription(xmlpullparser.getAttributeValue(null, "desc"));
        if(safeInt(xmlpullparser, "priority", 0) != 0)
            flag2 = flag1;
        else
            flag2 = false;
        setBypassDnd(flag2);
        setLockscreenVisibility(safeInt(xmlpullparser, "visibility", -1000));
        uri = safeUri(xmlpullparser, "sound");
        uri1 = uri;
        if(flag)
            uri1 = restoreSoundUri(context, uri);
        setSound(uri1, safeAudioAttributes(xmlpullparser));
        enableLights(safeBool(xmlpullparser, "lights", false));
        setLightColor(safeInt(xmlpullparser, "light_color", 0));
        setVibrationPattern(safeLongArray(xmlpullparser, "vibration", null));
        enableVibration(safeBool(xmlpullparser, "vibration_enabled", false));
        setShowBadge(safeBool(xmlpullparser, "show_badge", false));
        setDeleted(safeBool(xmlpullparser, "deleted", false));
        setGroup(xmlpullparser.getAttributeValue(null, "group"));
        lockFields(safeInt(xmlpullparser, "locked", 0));
        setBlockableSystem(safeBool(xmlpullparser, "blockable_system", false));
    }

    private Uri restoreSoundUri(Context context, Uri uri)
    {
        if(uri == null)
            return null;
        context = context.getContentResolver();
        uri = context.canonicalize(uri);
        if(uri == null)
            return android.provider.Settings.System.DEFAULT_NOTIFICATION_URI;
        else
            return context.uncanonicalize(uri);
    }

    private static AudioAttributes safeAudioAttributes(XmlPullParser xmlpullparser)
    {
        int i = safeInt(xmlpullparser, "usage", 5);
        int j = safeInt(xmlpullparser, "content_type", 4);
        int k = safeInt(xmlpullparser, "flags", 0);
        return (new android.media.AudioAttributes.Builder()).setUsage(i).setContentType(j).setFlags(k).build();
    }

    private static boolean safeBool(XmlPullParser xmlpullparser, String s, boolean flag)
    {
        xmlpullparser = xmlpullparser.getAttributeValue(null, s);
        if(TextUtils.isEmpty(xmlpullparser))
            return flag;
        else
            return Boolean.parseBoolean(xmlpullparser);
    }

    private static int safeInt(XmlPullParser xmlpullparser, String s, int i)
    {
        return tryParseInt(xmlpullparser.getAttributeValue(null, s), i);
    }

    private static long[] safeLongArray(XmlPullParser xmlpullparser, String s, long al[])
    {
        xmlpullparser = xmlpullparser.getAttributeValue(null, s);
        if(TextUtils.isEmpty(xmlpullparser))
            return al;
        xmlpullparser = xmlpullparser.split(",");
        al = new long[xmlpullparser.length];
        int i = 0;
        while(i < xmlpullparser.length) 
        {
            try
            {
                al[i] = Long.parseLong(xmlpullparser[i]);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                al[i] = 0L;
            }
            i++;
        }
        return al;
    }

    private static Uri safeUri(XmlPullParser xmlpullparser, String s)
    {
        Object obj = null;
        xmlpullparser = xmlpullparser.getAttributeValue(null, s);
        if(xmlpullparser == null)
            xmlpullparser = obj;
        else
            xmlpullparser = Uri.parse(xmlpullparser);
        return xmlpullparser;
    }

    private static int tryParseInt(String s, int i)
    {
        if(TextUtils.isEmpty(s))
            return i;
        int j;
        try
        {
            j = Integer.parseInt(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return i;
        }
        return j;
    }

    private void writeXml(XmlSerializer xmlserializer, boolean flag, Context context)
        throws IOException
    {
        boolean flag1 = true;
        boolean flag2 = flag1;
        if(flag)
            if(context != null)
                flag2 = flag1;
            else
                flag2 = false;
        Preconditions.checkArgument(flag2, "forBackup is true but got null context");
        xmlserializer.startTag(null, "channel");
        xmlserializer.attribute(null, "id", getId());
        if(getName() != null)
            xmlserializer.attribute(null, "name", getName().toString());
        if(getDescription() != null)
            xmlserializer.attribute(null, "desc", getDescription());
        if(getImportance() != -1000)
            xmlserializer.attribute(null, "importance", Integer.toString(getImportance()));
        if(canBypassDnd())
            xmlserializer.attribute(null, "priority", Integer.toString(2));
        if(getLockscreenVisibility() != -1000)
            xmlserializer.attribute(null, "visibility", Integer.toString(getLockscreenVisibility()));
        if(flag)
            context = getSoundForBackup(context);
        else
            context = getSound();
        if(context != null)
            xmlserializer.attribute(null, "sound", context.toString());
        if(getAudioAttributes() != null)
        {
            xmlserializer.attribute(null, "usage", Integer.toString(getAudioAttributes().getUsage()));
            xmlserializer.attribute(null, "content_type", Integer.toString(getAudioAttributes().getContentType()));
            xmlserializer.attribute(null, "flags", Integer.toString(getAudioAttributes().getFlags()));
        }
        if(shouldShowLights())
            xmlserializer.attribute(null, "lights", Boolean.toString(shouldShowLights()));
        if(getLightColor() != 0)
            xmlserializer.attribute(null, "light_color", Integer.toString(getLightColor()));
        if(shouldVibrate())
            xmlserializer.attribute(null, "vibration_enabled", Boolean.toString(shouldVibrate()));
        if(getVibrationPattern() != null)
            xmlserializer.attribute(null, "vibration", longArrayToString(getVibrationPattern()));
        if(getUserLockedFields() != 0)
            xmlserializer.attribute(null, "locked", Integer.toString(getUserLockedFields()));
        if(canShowBadge())
            xmlserializer.attribute(null, "show_badge", Boolean.toString(canShowBadge()));
        if(isDeleted())
            xmlserializer.attribute(null, "deleted", Boolean.toString(isDeleted()));
        if(getGroup() != null)
            xmlserializer.attribute(null, "group", getGroup());
        if(isBlockableSystem())
            xmlserializer.attribute(null, "blockable_system", Boolean.toString(isBlockableSystem()));
        xmlserializer.endTag(null, "channel");
    }

    public boolean canBypassDnd()
    {
        return mBypassDnd;
    }

    public boolean canShowBadge()
    {
        return mShowBadge;
    }

    public int describeContents()
    {
        return 0;
    }

    public void enableLights(boolean flag)
    {
        mLights = flag;
    }

    public void enableVibration(boolean flag)
    {
        mVibrationEnabled = flag;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        flag = true;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (NotificationChannel)obj;
        if(getImportance() != ((NotificationChannel) (obj)).getImportance())
            return false;
        if(mBypassDnd != ((NotificationChannel) (obj)).mBypassDnd)
            return false;
        if(getLockscreenVisibility() != ((NotificationChannel) (obj)).getLockscreenVisibility())
            return false;
        if(mLights != ((NotificationChannel) (obj)).mLights)
            return false;
        if(getLightColor() != ((NotificationChannel) (obj)).getLightColor())
            return false;
        if(getUserLockedFields() != ((NotificationChannel) (obj)).getUserLockedFields())
            return false;
        if(mVibrationEnabled != ((NotificationChannel) (obj)).mVibrationEnabled)
            return false;
        if(mShowBadge != ((NotificationChannel) (obj)).mShowBadge)
            return false;
        if(isDeleted() != ((NotificationChannel) (obj)).isDeleted())
            return false;
        if(isBlockableSystem() != ((NotificationChannel) (obj)).isBlockableSystem())
            return false;
        if(getId() == null ? ((NotificationChannel) (obj)).getId() != null : getId().equals(((NotificationChannel) (obj)).getId()) ^ true)
            return false;
        if(getName() == null ? ((NotificationChannel) (obj)).getName() != null : getName().equals(((NotificationChannel) (obj)).getName()) ^ true)
            return false;
        if(getDescription() == null ? ((NotificationChannel) (obj)).getDescription() != null : getDescription().equals(((NotificationChannel) (obj)).getDescription()) ^ true)
            return false;
        if(getSound() == null ? ((NotificationChannel) (obj)).getSound() != null : getSound().equals(((NotificationChannel) (obj)).getSound()) ^ true)
            return false;
        if(!Arrays.equals(mVibration, ((NotificationChannel) (obj)).mVibration))
            return false;
        if(getGroup() == null ? ((NotificationChannel) (obj)).getGroup() != null : getGroup().equals(((NotificationChannel) (obj)).getGroup()) ^ true)
            return false;
        if(getAudioAttributes() == null) goto _L2; else goto _L1
_L1:
        flag = getAudioAttributes().equals(((NotificationChannel) (obj)).getAudioAttributes());
_L4:
        return flag;
_L2:
        if(((NotificationChannel) (obj)).getAudioAttributes() != null)
            flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public AudioAttributes getAudioAttributes()
    {
        return mAudioAttributes;
    }

    public String getDescription()
    {
        return mDesc;
    }

    public String getGroup()
    {
        return mGroup;
    }

    public String getId()
    {
        return mId;
    }

    public int getImportance()
    {
        return mImportance;
    }

    public int getLightColor()
    {
        return mLightColor;
    }

    public int getLockscreenVisibility()
    {
        return mLockscreenVisibility;
    }

    public CharSequence getName()
    {
        return mName;
    }

    public Uri getSound()
    {
        return mSound;
    }

    public int getUserLockedFields()
    {
        return mUserLockedFields;
    }

    public long[] getVibrationPattern()
    {
        return mVibration;
    }

    public int hashCode()
    {
        int i = 1;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        if(getId() != null)
            j = getId().hashCode();
        else
            j = 0;
        if(getName() != null)
            k = getName().hashCode();
        else
            k = 0;
        if(getDescription() != null)
            l = getDescription().hashCode();
        else
            l = 0;
        i1 = getImportance();
        if(mBypassDnd)
            j1 = 1;
        else
            j1 = 0;
        k1 = getLockscreenVisibility();
        if(getSound() != null)
            l1 = getSound().hashCode();
        else
            l1 = 0;
        if(mLights)
            i2 = 1;
        else
            i2 = 0;
        j2 = getLightColor();
        k2 = Arrays.hashCode(mVibration);
        l2 = getUserLockedFields();
        if(mVibrationEnabled)
            i3 = 1;
        else
            i3 = 0;
        if(mShowBadge)
            j3 = 1;
        else
            j3 = 0;
        if(isDeleted())
            k3 = 1;
        else
            k3 = 0;
        if(getGroup() != null)
            l3 = getGroup().hashCode();
        else
            l3 = 0;
        if(getAudioAttributes() != null)
            i4 = getAudioAttributes().hashCode();
        else
            i4 = 0;
        if(!isBlockableSystem())
            i = 0;
        return (((((((((((((((j * 31 + k) * 31 + l) * 31 + i1) * 31 + j1) * 31 + k1) * 31 + l1) * 31 + i2) * 31 + j2) * 31 + k2) * 31 + l2) * 31 + i3) * 31 + j3) * 31 + k3) * 31 + l3) * 31 + i4) * 31 + i;
    }

    public boolean isBlockableSystem()
    {
        return mBlockableSystem;
    }

    public boolean isDeleted()
    {
        return mDeleted;
    }

    public void lockFields(int i)
    {
        mUserLockedFields = mUserLockedFields | i;
    }

    public void populateFromXml(XmlPullParser xmlpullparser)
    {
        populateFromXml(xmlpullparser, false, null);
    }

    public void populateFromXmlForRestore(XmlPullParser xmlpullparser, Context context)
    {
        populateFromXml(xmlpullparser, true, context);
    }

    public void setBlockableSystem(boolean flag)
    {
        mBlockableSystem = flag;
    }

    public void setBypassDnd(boolean flag)
    {
        mBypassDnd = flag;
    }

    public void setDeleted(boolean flag)
    {
        mDeleted = flag;
    }

    public void setDescription(String s)
    {
        mDesc = getTrimmedString(s);
    }

    public void setGroup(String s)
    {
        mGroup = s;
    }

    public void setImportance(int i)
    {
        mImportance = i;
    }

    public void setLightColor(int i)
    {
        mLightColor = i;
    }

    public void setLockscreenVisibility(int i)
    {
        mLockscreenVisibility = i;
    }

    public void setName(CharSequence charsequence)
    {
        String s = null;
        if(charsequence != null)
            s = getTrimmedString(charsequence.toString());
        mName = s;
    }

    public void setShowBadge(boolean flag)
    {
        mShowBadge = flag;
    }

    public void setSound(Uri uri, AudioAttributes audioattributes)
    {
        mSound = uri;
        mAudioAttributes = audioattributes;
    }

    public void setVibrationPattern(long al[])
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(al != null)
        {
            flag1 = flag;
            if(al.length > 0)
                flag1 = true;
        }
        mVibrationEnabled = flag1;
        mVibration = al;
    }

    public boolean shouldShowLights()
    {
        return mLights;
    }

    public boolean shouldVibrate()
    {
        return mVibrationEnabled;
    }

    public JSONObject toJson()
        throws JSONException
    {
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("id", getId());
        jsonobject.put("name", getName());
        jsonobject.put("desc", getDescription());
        if(getImportance() != -1000)
            jsonobject.put("importance", android.service.notification.NotificationListenerService.Ranking.importanceToString(getImportance()));
        if(canBypassDnd())
            jsonobject.put("priority", 2);
        if(getLockscreenVisibility() != -1000)
            jsonobject.put("visibility", Notification.visibilityToString(getLockscreenVisibility()));
        if(getSound() != null)
            jsonobject.put("sound", getSound().toString());
        if(getAudioAttributes() != null)
        {
            jsonobject.put("usage", Integer.toString(getAudioAttributes().getUsage()));
            jsonobject.put("content_type", Integer.toString(getAudioAttributes().getContentType()));
            jsonobject.put("flags", Integer.toString(getAudioAttributes().getFlags()));
        }
        jsonobject.put("lights", Boolean.toString(shouldShowLights()));
        jsonobject.put("light_color", Integer.toString(getLightColor()));
        jsonobject.put("vibration_enabled", Boolean.toString(shouldVibrate()));
        jsonobject.put("locked", Integer.toString(getUserLockedFields()));
        jsonobject.put("vibration", longArrayToString(getVibrationPattern()));
        jsonobject.put("show_badge", Boolean.toString(canShowBadge()));
        jsonobject.put("deleted", Boolean.toString(isDeleted()));
        jsonobject.put("group", getGroup());
        jsonobject.put("blockable_system", isBlockableSystem());
        return jsonobject;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("NotificationChannel{mId='").append(mId).append('\'').append(", mName=").append(mName).append(", mDescription=");
        String s;
        if(!TextUtils.isEmpty(mDesc))
            s = "hasDescription ";
        else
            s = "";
        return stringbuilder.append(s).append(", mImportance=").append(mImportance).append(", mBypassDnd=").append(mBypassDnd).append(", mLockscreenVisibility=").append(mLockscreenVisibility).append(", mSound=").append(mSound).append(", mLights=").append(mLights).append(", mLightColor=").append(mLightColor).append(", mVibration=").append(Arrays.toString(mVibration)).append(", mUserLockedFields=").append(mUserLockedFields).append(", mVibrationEnabled=").append(mVibrationEnabled).append(", mShowBadge=").append(mShowBadge).append(", mDeleted=").append(mDeleted).append(", mGroup='").append(mGroup).append('\'').append(", mAudioAttributes=").append(mAudioAttributes).append(", mBlockableSystem=").append(mBlockableSystem).append('}').toString();
    }

    public void unlockFields(int i)
    {
        mUserLockedFields = mUserLockedFields & i;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        int j;
        if(mId != null)
        {
            parcel.writeByte((byte)1);
            parcel.writeString(mId);
        } else
        {
            parcel.writeByte((byte)0);
        }
        if(mName != null)
        {
            parcel.writeByte((byte)1);
            parcel.writeString(mName);
        } else
        {
            parcel.writeByte((byte)0);
        }
        if(mDesc != null)
        {
            parcel.writeByte((byte)1);
            parcel.writeString(mDesc);
        } else
        {
            parcel.writeByte((byte)0);
        }
        parcel.writeInt(mImportance);
        if(mBypassDnd)
        {
            i = 1;
            j = i;
        } else
        {
            i = 0;
            j = i;
        }
        parcel.writeByte(j);
        parcel.writeInt(mLockscreenVisibility);
        if(mSound != null)
        {
            parcel.writeByte((byte)1);
            mSound.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeByte((byte)0);
        }
        if(mLights)
        {
            i = 1;
            j = i;
        } else
        {
            i = 0;
            j = i;
        }
        parcel.writeByte(j);
        parcel.writeLongArray(mVibration);
        parcel.writeInt(mUserLockedFields);
        if(mVibrationEnabled)
        {
            i = 1;
            j = i;
        } else
        {
            i = 0;
            j = i;
        }
        parcel.writeByte(j);
        if(mShowBadge)
        {
            i = 1;
            j = i;
        } else
        {
            i = 0;
            j = i;
        }
        parcel.writeByte(j);
        if(mDeleted)
        {
            i = 1;
            j = i;
        } else
        {
            i = 0;
            j = i;
        }
        parcel.writeByte(j);
        if(mGroup != null)
        {
            parcel.writeByte((byte)1);
            parcel.writeString(mGroup);
        } else
        {
            parcel.writeByte((byte)0);
        }
        if(mAudioAttributes != null)
        {
            parcel.writeInt(1);
            mAudioAttributes.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(mLightColor);
        parcel.writeBoolean(mBlockableSystem);
    }

    public void writeXml(XmlSerializer xmlserializer)
        throws IOException
    {
        writeXml(xmlserializer, false, null);
    }

    public void writeXmlForBackup(XmlSerializer xmlserializer, Context context)
        throws IOException
    {
        writeXml(xmlserializer, true, context);
    }

    private static final String ATT_BLOCKABLE_SYSTEM = "blockable_system";
    private static final String ATT_CONTENT_TYPE = "content_type";
    private static final String ATT_DELETED = "deleted";
    private static final String ATT_DESC = "desc";
    private static final String ATT_FLAGS = "flags";
    private static final String ATT_GROUP = "group";
    private static final String ATT_ID = "id";
    private static final String ATT_IMPORTANCE = "importance";
    private static final String ATT_LIGHTS = "lights";
    private static final String ATT_LIGHT_COLOR = "light_color";
    private static final String ATT_NAME = "name";
    private static final String ATT_PRIORITY = "priority";
    private static final String ATT_SHOW_BADGE = "show_badge";
    private static final String ATT_SOUND = "sound";
    private static final String ATT_USAGE = "usage";
    private static final String ATT_USER_LOCKED = "locked";
    private static final String ATT_VIBRATION = "vibration";
    private static final String ATT_VIBRATION_ENABLED = "vibration_enabled";
    private static final String ATT_VISIBILITY = "visibility";
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NotificationChannel createFromParcel(Parcel parcel)
        {
            return new NotificationChannel(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NotificationChannel[] newArray(int i)
        {
            return new NotificationChannel[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String DEFAULT_CHANNEL_ID = "miscellaneous";
    private static final boolean DEFAULT_DELETED = false;
    private static final int DEFAULT_IMPORTANCE = -1000;
    private static final int DEFAULT_LIGHT_COLOR = 0;
    private static final boolean DEFAULT_SHOW_BADGE = true;
    private static final int DEFAULT_VISIBILITY = -1000;
    private static final String DELIMITER = ",";
    public static final int LOCKABLE_FIELDS[] = {
        1, 2, 4, 8, 16, 32, 128
    };
    private static final int MAX_TEXT_LENGTH = 1000;
    private static final String TAG_CHANNEL = "channel";
    public static final int USER_LOCKED_IMPORTANCE = 4;
    public static final int USER_LOCKED_LIGHTS = 8;
    public static final int USER_LOCKED_PRIORITY = 1;
    public static final int USER_LOCKED_SHOW_BADGE = 128;
    public static final int USER_LOCKED_SOUND = 32;
    public static final int USER_LOCKED_VIBRATION = 16;
    public static final int USER_LOCKED_VISIBILITY = 2;
    private AudioAttributes mAudioAttributes;
    private boolean mBlockableSystem;
    private boolean mBypassDnd;
    private boolean mDeleted;
    private String mDesc;
    private String mGroup;
    private final String mId;
    private int mImportance;
    private int mLightColor;
    private boolean mLights;
    private int mLockscreenVisibility;
    private String mName;
    private boolean mShowBadge;
    private Uri mSound;
    private int mUserLockedFields;
    private long mVibration[];
    private boolean mVibrationEnabled;

}

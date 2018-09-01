// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.contentcatcher.sdk.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.*;

public class FeatureInfo
    implements Parcelable
{

    public FeatureInfo()
    {
        enable = true;
        mParams = new HashMap();
    }

    public FeatureInfo(Parcel parcel)
    {
        boolean flag = true;
        super();
        enable = true;
        mParams = new HashMap();
        name = parcel.readString();
        target = parcel.readString();
        jobTag = parcel.readString();
        if(parcel.readInt() != 1)
            flag = false;
        enable = flag;
        mParams = parcel.readHashMap(ClassLoader.getSystemClassLoader());
    }

    public int describeContents()
    {
        return 0;
    }

    public Set getBlackList()
    {
        if(!mParams.containsKey("blacklist"))
            return null;
        String s = (String)mParams.get("blacklist");
        if(TextUtils.isEmpty(s))
            return null;
        else
            return new HashSet(Arrays.asList(s.split(",")));
    }

    public List getCatcherNameList()
    {
        if(!mParams.containsKey("catchers"))
            return null;
        String s = (String)mParams.get("catchers");
        if(TextUtils.isEmpty(s))
            return null;
        else
            return Arrays.asList(s.split(","));
    }

    public Set getWhiteList()
    {
        if(!mParams.containsKey("whitelist"))
            return null;
        String s = (String)mParams.get("whitelist");
        if(TextUtils.isEmpty(s))
            return null;
        else
            return new HashSet(Arrays.asList(s.split(",")));
    }

    public boolean isMatched(String s, String s1, int i)
    {
        s1 = getWhiteList();
        if(s1 != null && s1.isEmpty() ^ true)
            return s1.contains(s);
        s1 = getBlackList();
        if(s1 != null && s1.isEmpty() ^ true)
            return s1.contains(s) ^ true;
        else
            return true;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("{name:").append(name).append(", enable:").append(enable).append(",").toString());
        Iterator iterator = mParams.keySet().iterator();
        while(iterator.hasNext()) 
        {
            String s = (String)iterator.next();
            Object obj = mParams.get(s);
            String s1 = "non-string";
            if(obj == null)
                s1 = null;
            else
            if(obj instanceof String)
                s1 = (String)obj;
            stringbuffer.append((new StringBuilder()).append(s).append(":").append(s1).append(",").toString());
        }
        if(stringbuffer.charAt(stringbuffer.length() - 1) == ',')
            stringbuffer.setLength(stringbuffer.length() - 1);
        stringbuffer.append("}");
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(name);
        parcel.writeString(target);
        parcel.writeString(jobTag);
        if(enable)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeMap(mParams);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public FeatureInfo createFromParcel(Parcel parcel)
        {
            return new FeatureInfo(parcel);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public FeatureInfo[] newArray(int i)
        {
            return new FeatureInfo[i];
        }

    }
;
    public static final String KEY_BLACKLIST = "blacklist";
    public static final String KEY_CATCHER_NAME = "catchers";
    public static final String KEY_WHITELIST = "whitelist";
    public static final String NAME_VALUE_FAV_MODE = "fav_mode";
    public static final String NAME_VALUE_PICK_MODE = "pick_mode";
    public boolean enable;
    public String jobTag;
    public HashMap mParams;
    public String name;
    public String target;

}

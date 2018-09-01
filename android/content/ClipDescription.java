// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.os.*;
import android.text.TextUtils;
import android.util.TimeUtils;
import java.util.ArrayList;
import java.util.Arrays;

public class ClipDescription
    implements Parcelable
{

    public ClipDescription(ClipDescription clipdescription)
    {
        mLabel = clipdescription.mLabel;
        mMimeTypes = new ArrayList(clipdescription.mMimeTypes);
        mTimeStamp = clipdescription.mTimeStamp;
    }

    ClipDescription(Parcel parcel)
    {
        mLabel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mMimeTypes = parcel.createStringArrayList();
        mExtras = parcel.readPersistableBundle();
        mTimeStamp = parcel.readLong();
    }

    public ClipDescription(CharSequence charsequence, String as[])
    {
        if(as == null)
        {
            throw new NullPointerException("mimeTypes is null");
        } else
        {
            mLabel = charsequence;
            mMimeTypes = new ArrayList(Arrays.asList(as));
            return;
        }
    }

    public static boolean compareMimeTypes(String s, String s1)
    {
        int i = s1.length();
        if(i == 3 && s1.equals("*/*"))
            return true;
        int j = s1.indexOf('/');
        if(j > 0)
            if(i == j + 2 && s1.charAt(j + 1) == '*')
            {
                if(s1.regionMatches(0, s, 0, j + 1))
                    return true;
            } else
            if(s1.equals(s))
                return true;
        return false;
    }

    void addMimeTypes(String as[])
    {
        for(int i = 0; i != as.length; i++)
        {
            String s = as[i];
            if(!mMimeTypes.contains(s))
                mMimeTypes.add(s);
        }

    }

    public int describeContents()
    {
        return 0;
    }

    public String[] filterMimeTypes(String s)
    {
        ArrayList arraylist = null;
        int i = mMimeTypes.size();
        for(int j = 0; j < i;)
        {
            ArrayList arraylist1 = arraylist;
            if(compareMimeTypes((String)mMimeTypes.get(j), s))
            {
                arraylist1 = arraylist;
                if(arraylist == null)
                    arraylist1 = new ArrayList();
                arraylist1.add((String)mMimeTypes.get(j));
            }
            j++;
            arraylist = arraylist1;
        }

        if(arraylist == null)
        {
            return null;
        } else
        {
            s = new String[arraylist.size()];
            arraylist.toArray(s);
            return s;
        }
    }

    public PersistableBundle getExtras()
    {
        return mExtras;
    }

    public CharSequence getLabel()
    {
        return mLabel;
    }

    public String getMimeType(int i)
    {
        return (String)mMimeTypes.get(i);
    }

    public int getMimeTypeCount()
    {
        return mMimeTypes.size();
    }

    public long getTimestamp()
    {
        return mTimeStamp;
    }

    public boolean hasMimeType(String s)
    {
        int i = mMimeTypes.size();
        for(int j = 0; j < i; j++)
            if(compareMimeTypes((String)mMimeTypes.get(j), s))
                return true;

        return false;
    }

    public void setExtras(PersistableBundle persistablebundle)
    {
        mExtras = new PersistableBundle(persistablebundle);
    }

    public void setTimestamp(long l)
    {
        mTimeStamp = l;
    }

    public boolean toShortString(StringBuilder stringbuilder)
    {
        boolean flag = toShortStringTypesOnly(stringbuilder) ^ true;
        boolean flag1 = flag;
        if(mLabel != null)
        {
            if(!flag)
                stringbuilder.append(' ');
            flag1 = false;
            stringbuilder.append('"');
            stringbuilder.append(mLabel);
            stringbuilder.append('"');
        }
        flag = flag1;
        if(mExtras != null)
        {
            if(!flag1)
                stringbuilder.append(' ');
            flag = false;
            stringbuilder.append(mExtras.toString());
        }
        flag1 = flag;
        if(mTimeStamp > 0L)
        {
            if(!flag)
                stringbuilder.append(' ');
            flag1 = false;
            stringbuilder.append('<');
            stringbuilder.append(TimeUtils.logTimeOfDay(mTimeStamp));
            stringbuilder.append('>');
        }
        return flag1 ^ true;
    }

    public boolean toShortStringTypesOnly(StringBuilder stringbuilder)
    {
        boolean flag = true;
        int i = mMimeTypes.size();
        for(int j = 0; j < i; j++)
        {
            if(!flag)
                stringbuilder.append(' ');
            flag = false;
            stringbuilder.append((String)mMimeTypes.get(j));
        }

        return flag ^ true;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("ClipDescription { ");
        toShortString(stringbuilder);
        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    public void validate()
    {
        if(mMimeTypes == null)
            throw new NullPointerException("null mime types");
        int i = mMimeTypes.size();
        if(i <= 0)
            throw new IllegalArgumentException("must have at least 1 mime type");
        for(int j = 0; j < i; j++)
            if(mMimeTypes.get(j) == null)
                throw new NullPointerException((new StringBuilder()).append("mime type at ").append(j).append(" is null").toString());

    }

    public void writeToParcel(Parcel parcel, int i)
    {
        TextUtils.writeToParcel(mLabel, parcel, i);
        parcel.writeStringList(mMimeTypes);
        parcel.writePersistableBundle(mExtras);
        parcel.writeLong(mTimeStamp);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ClipDescription createFromParcel(Parcel parcel)
        {
            return new ClipDescription(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ClipDescription[] newArray(int i)
        {
            return new ClipDescription[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String EXTRA_TARGET_COMPONENT_NAME = "android.content.extra.TARGET_COMPONENT_NAME";
    public static final String EXTRA_USER_SERIAL_NUMBER = "android.content.extra.USER_SERIAL_NUMBER";
    public static final String MIMETYPE_TEXT_HTML = "text/html";
    public static final String MIMETYPE_TEXT_INTENT = "text/vnd.android.intent";
    public static final String MIMETYPE_TEXT_PLAIN = "text/plain";
    public static final String MIMETYPE_TEXT_URILIST = "text/uri-list";
    private PersistableBundle mExtras;
    final CharSequence mLabel;
    private final ArrayList mMimeTypes;
    private long mTimeStamp;

}

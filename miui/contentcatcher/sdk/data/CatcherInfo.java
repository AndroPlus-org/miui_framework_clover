// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.contentcatcher.sdk.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.*;

public class CatcherInfo
    implements Parcelable
{

    public CatcherInfo()
    {
        mParams = new HashMap();
    }

    public CatcherInfo(Parcel parcel)
    {
        mParams = new HashMap();
        name = parcel.readString();
        mParams = parcel.readHashMap(ClassLoader.getSystemClassLoader());
    }

    public CatcherInfo(String s, Map map)
    {
        mParams = new HashMap();
        name = s;
        mParams = map;
    }

    public int describeContents()
    {
        return 0;
    }

    public void merge(CatcherInfo catcherinfo)
    {
        if(catcherinfo.name.equals(name))
            mParams.putAll(catcherinfo.mParams);
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("{name:").append(name).append(",").toString());
        String s;
        String s1;
        for(Iterator iterator = mParams.keySet().iterator(); iterator.hasNext(); stringbuffer.append((new StringBuilder()).append(s).append(":").append(s1).append(",").toString()))
        {
            s = (String)iterator.next();
            s1 = (String)mParams.get(s);
        }

        if(stringbuffer.charAt(stringbuffer.length() - 1) == ',')
            stringbuffer.setLength(stringbuffer.length() - 1);
        stringbuffer.append("}");
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(name);
        parcel.writeMap(mParams);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CatcherInfo createFromParcel(Parcel parcel)
        {
            return new CatcherInfo(parcel);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public CatcherInfo[] newArray(int i)
        {
            return new CatcherInfo[i];
        }

    }
;
    public Map mParams;
    public String name;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.contentcatcher.sdk;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;

public class ClientToken
    implements Parcelable
{

    public ClientToken(Parcel parcel)
    {
        mParams = new HashMap();
        mPkgName = parcel.readString();
        mJobTag = parcel.readString();
        mParams = parcel.readHashMap(ClassLoader.getSystemClassLoader());
    }

    public ClientToken(String s)
    {
        mParams = new HashMap();
        mPkgName = s;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(!(obj instanceof ClientToken))
            return false;
        if(hashCode() == obj.hashCode())
            flag = true;
        return flag;
    }

    public String getJobTag()
    {
        return mJobTag;
    }

    public HashMap getParams()
    {
        return mParams;
    }

    public String getPkgName()
    {
        return mPkgName;
    }

    public int hashCode()
    {
        int i = 0;
        int j;
        if(mPkgName == null)
            j = 0;
        else
            j = mPkgName.hashCode() * 47;
        if(mJobTag != null)
            i = mJobTag.hashCode() * 67;
        return j + i;
    }

    public boolean isMatch(ClientToken clienttoken)
    {
        boolean flag;
        if(this != clienttoken)
        {
            if(clienttoken != null && clienttoken.getPkgName().equals(mPkgName))
                flag = clienttoken.getJobTag().equals(mJobTag);
            else
                flag = false;
        } else
        {
            flag = true;
        }
        return flag;
    }

    public void setJobTag(String s)
    {
        mJobTag = s;
    }

    public void setParams(HashMap hashmap)
    {
        mParams = hashmap;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Token {pkg name:").append(mPkgName).append(", mJobTag:").append(mJobTag).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mPkgName);
        parcel.writeString(mJobTag);
        parcel.writeMap(mParams);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ClientToken createFromParcel(Parcel parcel)
        {
            return new ClientToken(parcel);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public ClientToken[] newArray(int i)
        {
            return new ClientToken[i];
        }

    }
;
    private String mJobTag;
    private HashMap mParams;
    private String mPkgName;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.presence;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package com.android.ims.internal.uce.presence:
//            PresResInstanceInfo

public class PresResInfo
    implements Parcelable
{

    public PresResInfo()
    {
        mResUri = "";
        mDisplayName = "";
        mInstanceInfo = new PresResInstanceInfo();
    }

    private PresResInfo(Parcel parcel)
    {
        mResUri = "";
        mDisplayName = "";
        readFromParcel(parcel);
    }

    PresResInfo(Parcel parcel, PresResInfo presresinfo)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public String getDisplayName()
    {
        return mDisplayName;
    }

    public PresResInstanceInfo getInstanceInfo()
    {
        return mInstanceInfo;
    }

    public String getResUri()
    {
        return mResUri;
    }

    public void readFromParcel(Parcel parcel)
    {
        mResUri = parcel.readString();
        mDisplayName = parcel.readString();
        mInstanceInfo = (PresResInstanceInfo)parcel.readParcelable(com/android/ims/internal/uce/presence/PresResInstanceInfo.getClassLoader());
    }

    public void setDisplayName(String s)
    {
        mDisplayName = s;
    }

    public void setInstanceInfo(PresResInstanceInfo presresinstanceinfo)
    {
        mInstanceInfo = presresinstanceinfo;
    }

    public void setResUri(String s)
    {
        mResUri = s;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mResUri);
        parcel.writeString(mDisplayName);
        parcel.writeParcelable(mInstanceInfo, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PresResInfo createFromParcel(Parcel parcel)
        {
            return new PresResInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PresResInfo[] newArray(int i)
        {
            return new PresResInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private String mDisplayName;
    private PresResInstanceInfo mInstanceInfo;
    private String mResUri;

}

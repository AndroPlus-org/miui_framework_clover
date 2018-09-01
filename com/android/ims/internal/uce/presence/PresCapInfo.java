// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.presence;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.ims.internal.uce.common.CapInfo;

public class PresCapInfo
    implements Parcelable
{

    public PresCapInfo()
    {
        mContactUri = "";
        mCapInfo = new CapInfo();
    }

    private PresCapInfo(Parcel parcel)
    {
        mContactUri = "";
        readFromParcel(parcel);
    }

    PresCapInfo(Parcel parcel, PresCapInfo prescapinfo)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public CapInfo getCapInfo()
    {
        return mCapInfo;
    }

    public String getContactUri()
    {
        return mContactUri;
    }

    public void readFromParcel(Parcel parcel)
    {
        mContactUri = parcel.readString();
        mCapInfo = (CapInfo)parcel.readParcelable(com/android/ims/internal/uce/common/CapInfo.getClassLoader());
    }

    public void setCapInfo(CapInfo capinfo)
    {
        mCapInfo = capinfo;
    }

    public void setContactUri(String s)
    {
        mContactUri = s;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mContactUri);
        parcel.writeParcelable(mCapInfo, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PresCapInfo createFromParcel(Parcel parcel)
        {
            return new PresCapInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PresCapInfo[] newArray(int i)
        {
            return new PresCapInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private CapInfo mCapInfo;
    private String mContactUri;

}

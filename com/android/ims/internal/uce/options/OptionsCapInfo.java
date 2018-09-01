// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.options;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.ims.internal.uce.common.CapInfo;

public class OptionsCapInfo
    implements Parcelable
{

    public OptionsCapInfo()
    {
        mSdp = "";
        mCapInfo = new CapInfo();
    }

    private OptionsCapInfo(Parcel parcel)
    {
        mSdp = "";
        readFromParcel(parcel);
    }

    OptionsCapInfo(Parcel parcel, OptionsCapInfo optionscapinfo)
    {
        this(parcel);
    }

    public static OptionsCapInfo getOptionsCapInfoInstance()
    {
        return new OptionsCapInfo();
    }

    public int describeContents()
    {
        return 0;
    }

    public CapInfo getCapInfo()
    {
        return mCapInfo;
    }

    public String getSdp()
    {
        return mSdp;
    }

    public void readFromParcel(Parcel parcel)
    {
        mSdp = parcel.readString();
        mCapInfo = (CapInfo)parcel.readParcelable(com/android/ims/internal/uce/common/CapInfo.getClassLoader());
    }

    public void setCapInfo(CapInfo capinfo)
    {
        mCapInfo = capinfo;
    }

    public void setSdp(String s)
    {
        mSdp = s;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mSdp);
        parcel.writeParcelable(mCapInfo, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public OptionsCapInfo createFromParcel(Parcel parcel)
        {
            return new OptionsCapInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public OptionsCapInfo[] newArray(int i)
        {
            return new OptionsCapInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private CapInfo mCapInfo;
    private String mSdp;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.presence;

import android.os.Parcel;
import android.os.Parcelable;

public class PresServiceInfo
    implements Parcelable
{

    public PresServiceInfo()
    {
        mMediaCap = 0;
        mServiceID = "";
        mServiceDesc = "";
        mServiceVer = "";
    }

    private PresServiceInfo(Parcel parcel)
    {
        mMediaCap = 0;
        mServiceID = "";
        mServiceDesc = "";
        mServiceVer = "";
        readFromParcel(parcel);
    }

    PresServiceInfo(Parcel parcel, PresServiceInfo presserviceinfo)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public int getMediaType()
    {
        return mMediaCap;
    }

    public String getServiceDesc()
    {
        return mServiceDesc;
    }

    public String getServiceId()
    {
        return mServiceID;
    }

    public String getServiceVer()
    {
        return mServiceVer;
    }

    public void readFromParcel(Parcel parcel)
    {
        mServiceID = parcel.readString();
        mServiceDesc = parcel.readString();
        mServiceVer = parcel.readString();
        mMediaCap = parcel.readInt();
    }

    public void setMediaType(int i)
    {
        mMediaCap = i;
    }

    public void setServiceDesc(String s)
    {
        mServiceDesc = s;
    }

    public void setServiceId(String s)
    {
        mServiceID = s;
    }

    public void setServiceVer(String s)
    {
        mServiceVer = s;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mServiceID);
        parcel.writeString(mServiceDesc);
        parcel.writeString(mServiceVer);
        parcel.writeInt(mMediaCap);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PresServiceInfo createFromParcel(Parcel parcel)
        {
            return new PresServiceInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PresServiceInfo[] newArray(int i)
        {
            return new PresServiceInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int UCE_PRES_MEDIA_CAP_FULL_AUDIO_AND_VIDEO = 2;
    public static final int UCE_PRES_MEDIA_CAP_FULL_AUDIO_ONLY = 1;
    public static final int UCE_PRES_MEDIA_CAP_NONE = 0;
    public static final int UCE_PRES_MEDIA_CAP_UNKNOWN = 3;
    private int mMediaCap;
    private String mServiceDesc;
    private String mServiceID;
    private String mServiceVer;

}

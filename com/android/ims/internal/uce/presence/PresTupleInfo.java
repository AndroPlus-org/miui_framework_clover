// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.presence;

import android.os.Parcel;
import android.os.Parcelable;

public class PresTupleInfo
    implements Parcelable
{

    public PresTupleInfo()
    {
        mFeatureTag = "";
        mContactUri = "";
        mTimestamp = "";
    }

    private PresTupleInfo(Parcel parcel)
    {
        mFeatureTag = "";
        mContactUri = "";
        mTimestamp = "";
        readFromParcel(parcel);
    }

    PresTupleInfo(Parcel parcel, PresTupleInfo prestupleinfo)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public String getContactUri()
    {
        return mContactUri;
    }

    public String getFeatureTag()
    {
        return mFeatureTag;
    }

    public String getTimestamp()
    {
        return mTimestamp;
    }

    public void readFromParcel(Parcel parcel)
    {
        mFeatureTag = parcel.readString();
        mContactUri = parcel.readString();
        mTimestamp = parcel.readString();
    }

    public void setContactUri(String s)
    {
        mContactUri = s;
    }

    public void setFeatureTag(String s)
    {
        mFeatureTag = s;
    }

    public void setTimestamp(String s)
    {
        mTimestamp = s;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mFeatureTag);
        parcel.writeString(mContactUri);
        parcel.writeString(mTimestamp);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PresTupleInfo createFromParcel(Parcel parcel)
        {
            return new PresTupleInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PresTupleInfo[] newArray(int i)
        {
            return new PresTupleInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private String mContactUri;
    private String mFeatureTag;
    private String mTimestamp;

}

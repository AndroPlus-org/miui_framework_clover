// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.common;

import android.os.Parcel;
import android.os.Parcelable;

public class UceLong
    implements Parcelable
{

    public UceLong()
    {
        mClientId = 1001;
    }

    private UceLong(Parcel parcel)
    {
        mClientId = 1001;
        readFromParcel(parcel);
    }

    UceLong(Parcel parcel, UceLong ucelong)
    {
        this(parcel);
    }

    public static UceLong getUceLongInstance()
    {
        return new UceLong();
    }

    private void writeToParcel(Parcel parcel)
    {
        parcel.writeLong(mUceLong);
        parcel.writeInt(mClientId);
    }

    public int describeContents()
    {
        return 0;
    }

    public int getClientId()
    {
        return mClientId;
    }

    public long getUceLong()
    {
        return mUceLong;
    }

    public void readFromParcel(Parcel parcel)
    {
        mUceLong = parcel.readLong();
        mClientId = parcel.readInt();
    }

    public void setClientId(int i)
    {
        mClientId = i;
    }

    public void setUceLong(long l)
    {
        mUceLong = l;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcel(parcel);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public UceLong createFromParcel(Parcel parcel)
        {
            return new UceLong(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public UceLong[] newArray(int i)
        {
            return new UceLong[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private int mClientId;
    private long mUceLong;

}

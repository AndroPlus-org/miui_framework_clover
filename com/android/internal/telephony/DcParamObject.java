// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.os.Parcel;
import android.os.Parcelable;

public class DcParamObject
    implements Parcelable
{

    public DcParamObject(int i)
    {
        mSubId = i;
    }

    public DcParamObject(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    private void readFromParcel(Parcel parcel)
    {
        mSubId = parcel.readInt();
    }

    public int describeContents()
    {
        return 0;
    }

    public int getSubId()
    {
        return mSubId;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mSubId);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public DcParamObject createFromParcel(Parcel parcel)
        {
            return new DcParamObject(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DcParamObject[] newArray(int i)
        {
            return new DcParamObject[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private int mSubId;

}

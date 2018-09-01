// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.os.Parcel;
import android.os.Parcelable;

public class WhetstoneResult
    implements Parcelable
{

    public WhetstoneResult(int i)
    {
        this(i, ((String) (null)));
    }

    public WhetstoneResult(int i, String s)
    {
        mCode = i;
        mResult = s;
    }

    private WhetstoneResult(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    WhetstoneResult(Parcel parcel, WhetstoneResult whetstoneresult)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public int getCode()
    {
        return mCode;
    }

    public String getResult()
    {
        return mResult;
    }

    public void readFromParcel(Parcel parcel)
    {
        mCode = parcel.readInt();
        mResult = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mCode);
        parcel.writeString(mResult);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WhetstoneResult createFromParcel(Parcel parcel)
        {
            return new WhetstoneResult(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WhetstoneResult[] newArray(int i)
        {
            return new WhetstoneResult[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private int mCode;
    private String mResult;

}

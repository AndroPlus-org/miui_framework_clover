// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims;

import android.os.Parcel;
import android.os.Parcelable;

public class ImsCallForwardInfo
    implements Parcelable
{

    public ImsCallForwardInfo()
    {
    }

    public ImsCallForwardInfo(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    private void readFromParcel(Parcel parcel)
    {
        mCondition = parcel.readInt();
        mStatus = parcel.readInt();
        mToA = parcel.readInt();
        mNumber = parcel.readString();
        mTimeSeconds = parcel.readInt();
        mServiceClass = parcel.readInt();
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append(super.toString()).append(", Condition: ").append(mCondition).append(", Status: ");
        String s;
        if(mStatus == 0)
            s = "disabled";
        else
            s = "enabled";
        return stringbuilder.append(s).append(", ToA: ").append(mToA).append(", Service Class: ").append(mServiceClass).append(", Number=").append(mNumber).append(", Time (seconds): ").append(mTimeSeconds).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mCondition);
        parcel.writeInt(mStatus);
        parcel.writeInt(mToA);
        parcel.writeString(mNumber);
        parcel.writeInt(mTimeSeconds);
        parcel.writeInt(mServiceClass);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ImsCallForwardInfo createFromParcel(Parcel parcel)
        {
            return new ImsCallForwardInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ImsCallForwardInfo[] newArray(int i)
        {
            return new ImsCallForwardInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public int mCondition;
    public String mNumber;
    public int mServiceClass;
    public int mStatus;
    public int mTimeSeconds;
    public int mToA;

}

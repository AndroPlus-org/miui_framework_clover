// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims;

import android.os.Parcel;
import android.os.Parcelable;

public class ImsSsInfo
    implements Parcelable
{

    public ImsSsInfo()
    {
    }

    public ImsSsInfo(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    private void readFromParcel(Parcel parcel)
    {
        mStatus = parcel.readInt();
        mIcbNum = parcel.readString();
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append(super.toString()).append(", Status: ");
        String s;
        if(mStatus == 0)
            s = "disabled";
        else
            s = "enabled";
        return stringbuilder.append(s).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mStatus);
        parcel.writeString(mIcbNum);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ImsSsInfo createFromParcel(Parcel parcel)
        {
            return new ImsSsInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ImsSsInfo[] newArray(int i)
        {
            return new ImsSsInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DISABLED = 0;
    public static final int ENABLED = 1;
    public static final int NOT_REGISTERED = -1;
    public String mIcbNum;
    public int mStatus;

}

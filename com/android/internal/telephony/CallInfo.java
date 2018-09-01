// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.os.Parcel;
import android.os.Parcelable;

public class CallInfo
    implements Parcelable
{

    public CallInfo(String s)
    {
        handle = s;
    }

    public int describeContents()
    {
        return 0;
    }

    public String getHandle()
    {
        return handle;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(handle);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CallInfo createFromParcel(Parcel parcel)
        {
            return new CallInfo(parcel.readString());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CallInfo[] newArray(int i)
        {
            return new CallInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private String handle;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.os.Parcel;
import android.os.Parcelable;

public class BroadcastPolicy
    implements Parcelable
{

    public BroadcastPolicy(int i, String as[])
    {
        mUid = i;
        mRestrictTypes = as;
    }

    private BroadcastPolicy(Parcel parcel)
    {
        mUid = parcel.readInt();
        mRestrictTypes = parcel.createStringArray();
    }

    BroadcastPolicy(Parcel parcel, BroadcastPolicy broadcastpolicy)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mUid);
        parcel.writeStringArray(mRestrictTypes);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BroadcastPolicy createFromParcel(Parcel parcel)
        {
            return new BroadcastPolicy(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BroadcastPolicy[] newArray(int i)
        {
            return new BroadcastPolicy[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public String mRestrictTypes[];
    public int mUid;

}

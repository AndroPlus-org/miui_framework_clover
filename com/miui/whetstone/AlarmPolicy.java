// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.os.Parcel;
import android.os.Parcelable;

public class AlarmPolicy
    implements Parcelable
{

    public AlarmPolicy(int i, int ai[])
    {
        mUid = i;
        mRestrictTypes = ai;
    }

    private AlarmPolicy(Parcel parcel)
    {
        mUid = parcel.readInt();
        mRestrictTypes = parcel.createIntArray();
    }

    AlarmPolicy(Parcel parcel, AlarmPolicy alarmpolicy)
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
        parcel.writeIntArray(mRestrictTypes);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AlarmPolicy createFromParcel(Parcel parcel)
        {
            return new AlarmPolicy(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AlarmPolicy[] newArray(int i)
        {
            return new AlarmPolicy[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public int mRestrictTypes[];
    public int mUid;

}

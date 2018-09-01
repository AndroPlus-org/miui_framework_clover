// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.content;

import android.content.Intent;
import android.os.Parcel;

public class ReferrerIntent extends Intent
{

    public ReferrerIntent(Intent intent, String s)
    {
        super(intent);
        mReferrer = s;
    }

    ReferrerIntent(Parcel parcel)
    {
        readFromParcel(parcel);
        mReferrer = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeString(mReferrer);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ReferrerIntent createFromParcel(Parcel parcel)
        {
            return new ReferrerIntent(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ReferrerIntent[] newArray(int i)
        {
            return new ReferrerIntent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final String mReferrer;

}

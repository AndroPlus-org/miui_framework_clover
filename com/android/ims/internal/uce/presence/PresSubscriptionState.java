// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.presence;

import android.os.Parcel;
import android.os.Parcelable;

public class PresSubscriptionState
    implements Parcelable
{

    public PresSubscriptionState()
    {
        mPresSubscriptionState = 3;
    }

    private PresSubscriptionState(Parcel parcel)
    {
        mPresSubscriptionState = 3;
        readFromParcel(parcel);
    }

    PresSubscriptionState(Parcel parcel, PresSubscriptionState pressubscriptionstate)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public int getPresSubscriptionStateValue()
    {
        return mPresSubscriptionState;
    }

    public void readFromParcel(Parcel parcel)
    {
        mPresSubscriptionState = parcel.readInt();
    }

    public void setPresSubscriptionState(int i)
    {
        mPresSubscriptionState = i;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mPresSubscriptionState);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PresSubscriptionState createFromParcel(Parcel parcel)
        {
            return new PresSubscriptionState(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PresSubscriptionState[] newArray(int i)
        {
            return new PresSubscriptionState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int UCE_PRES_SUBSCRIPTION_STATE_ACTIVE = 0;
    public static final int UCE_PRES_SUBSCRIPTION_STATE_PENDING = 1;
    public static final int UCE_PRES_SUBSCRIPTION_STATE_TERMINATED = 2;
    public static final int UCE_PRES_SUBSCRIPTION_STATE_UNKNOWN = 3;
    private int mPresSubscriptionState;

}

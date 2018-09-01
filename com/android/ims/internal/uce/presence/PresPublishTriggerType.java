// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.presence;

import android.os.Parcel;
import android.os.Parcelable;

public class PresPublishTriggerType
    implements Parcelable
{

    public PresPublishTriggerType()
    {
        mPublishTriggerType = 9;
    }

    private PresPublishTriggerType(Parcel parcel)
    {
        mPublishTriggerType = 9;
        readFromParcel(parcel);
    }

    PresPublishTriggerType(Parcel parcel, PresPublishTriggerType prespublishtriggertype)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public int getPublishTrigeerType()
    {
        return mPublishTriggerType;
    }

    public void readFromParcel(Parcel parcel)
    {
        mPublishTriggerType = parcel.readInt();
    }

    public void setPublishTrigeerType(int i)
    {
        mPublishTriggerType = i;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mPublishTriggerType);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PresPublishTriggerType createFromParcel(Parcel parcel)
        {
            return new PresPublishTriggerType(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PresPublishTriggerType[] newArray(int i)
        {
            return new PresPublishTriggerType[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int UCE_PRES_PUBLISH_TRIGGER_ETAG_EXPIRED = 0;
    public static final int UCE_PRES_PUBLISH_TRIGGER_MOVE_TO_2G = 6;
    public static final int UCE_PRES_PUBLISH_TRIGGER_MOVE_TO_3G = 5;
    public static final int UCE_PRES_PUBLISH_TRIGGER_MOVE_TO_EHRPD = 3;
    public static final int UCE_PRES_PUBLISH_TRIGGER_MOVE_TO_HSPAPLUS = 4;
    public static final int UCE_PRES_PUBLISH_TRIGGER_MOVE_TO_IWLAN = 8;
    public static final int UCE_PRES_PUBLISH_TRIGGER_MOVE_TO_LTE_VOPS_DISABLED = 1;
    public static final int UCE_PRES_PUBLISH_TRIGGER_MOVE_TO_LTE_VOPS_ENABLED = 2;
    public static final int UCE_PRES_PUBLISH_TRIGGER_MOVE_TO_WLAN = 7;
    public static final int UCE_PRES_PUBLISH_TRIGGER_UNKNOWN = 9;
    private int mPublishTriggerType;

}

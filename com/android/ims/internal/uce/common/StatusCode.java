// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.common;

import android.os.Parcel;
import android.os.Parcelable;

public class StatusCode
    implements Parcelable
{

    public StatusCode()
    {
        mStatusCode = 0;
    }

    private StatusCode(Parcel parcel)
    {
        mStatusCode = 0;
        readFromParcel(parcel);
    }

    StatusCode(Parcel parcel, StatusCode statuscode)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public int getStatusCode()
    {
        return mStatusCode;
    }

    public void readFromParcel(Parcel parcel)
    {
        mStatusCode = parcel.readInt();
    }

    public void setStatusCode(int i)
    {
        mStatusCode = i;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mStatusCode);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public StatusCode createFromParcel(Parcel parcel)
        {
            return new StatusCode(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public StatusCode[] newArray(int i)
        {
            return new StatusCode[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int UCE_FAILURE = 1;
    public static final int UCE_FETCH_ERROR = 6;
    public static final int UCE_INSUFFICIENT_MEMORY = 8;
    public static final int UCE_INVALID_FEATURE_TAG = 15;
    public static final int UCE_INVALID_LISTENER_HANDLE = 4;
    public static final int UCE_INVALID_PARAM = 5;
    public static final int UCE_INVALID_SERVICE_HANDLE = 3;
    public static final int UCE_LOST_NET = 9;
    public static final int UCE_NOT_FOUND = 11;
    public static final int UCE_NOT_SUPPORTED = 10;
    public static final int UCE_NO_CHANGE_IN_CAP = 13;
    public static final int UCE_REQUEST_TIMEOUT = 7;
    public static final int UCE_SERVICE_AVAILABLE = 16;
    public static final int UCE_SERVICE_UNAVAILABLE = 12;
    public static final int UCE_SERVICE_UNKNOWN = 14;
    public static final int UCE_SUCCESS = 0;
    public static final int UCE_SUCCESS_ASYC_UPDATE = 2;
    private int mStatusCode;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.options;

import android.os.Parcel;
import android.os.Parcelable;

public class OptionsCmdId
    implements Parcelable
{

    public OptionsCmdId()
    {
        mCmdId = 6;
    }

    private OptionsCmdId(Parcel parcel)
    {
        mCmdId = 6;
        readFromParcel(parcel);
    }

    OptionsCmdId(Parcel parcel, OptionsCmdId optionscmdid)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public int getCmdId()
    {
        return mCmdId;
    }

    public void readFromParcel(Parcel parcel)
    {
        mCmdId = parcel.readInt();
    }

    public void setCmdId(int i)
    {
        mCmdId = i;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mCmdId);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public OptionsCmdId createFromParcel(Parcel parcel)
        {
            return new OptionsCmdId(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public OptionsCmdId[] newArray(int i)
        {
            return new OptionsCmdId[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int UCE_OPTIONS_CMD_GETCONTACTCAP = 2;
    public static final int UCE_OPTIONS_CMD_GETCONTACTLISTCAP = 3;
    public static final int UCE_OPTIONS_CMD_GETMYCDINFO = 0;
    public static final int UCE_OPTIONS_CMD_GET_VERSION = 5;
    public static final int UCE_OPTIONS_CMD_RESPONSEINCOMINGOPTIONS = 4;
    public static final int UCE_OPTIONS_CMD_SETMYCDINFO = 1;
    public static final int UCE_OPTIONS_CMD_UNKNOWN = 6;
    private int mCmdId;

}

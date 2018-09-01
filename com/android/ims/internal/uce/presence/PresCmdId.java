// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.presence;

import android.os.Parcel;
import android.os.Parcelable;

public class PresCmdId
    implements Parcelable
{

    public PresCmdId()
    {
        mCmdId = 6;
    }

    private PresCmdId(Parcel parcel)
    {
        mCmdId = 6;
        readFromParcel(parcel);
    }

    PresCmdId(Parcel parcel, PresCmdId prescmdid)
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

        public PresCmdId createFromParcel(Parcel parcel)
        {
            return new PresCmdId(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PresCmdId[] newArray(int i)
        {
            return new PresCmdId[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int UCE_PRES_CMD_GETCONTACTCAP = 2;
    public static final int UCE_PRES_CMD_GETCONTACTLISTCAP = 3;
    public static final int UCE_PRES_CMD_GET_VERSION = 0;
    public static final int UCE_PRES_CMD_PUBLISHMYCAP = 1;
    public static final int UCE_PRES_CMD_REENABLE_SERVICE = 5;
    public static final int UCE_PRES_CMD_SETNEWFEATURETAG = 4;
    public static final int UCE_PRES_CMD_UNKNOWN = 6;
    private int mCmdId;

}

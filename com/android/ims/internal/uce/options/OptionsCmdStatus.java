// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.options;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.ims.internal.uce.common.CapInfo;
import com.android.ims.internal.uce.common.StatusCode;

// Referenced classes of package com.android.ims.internal.uce.options:
//            OptionsCmdId

public class OptionsCmdStatus
    implements Parcelable
{

    public OptionsCmdStatus()
    {
        mStatus = new StatusCode();
        mCapInfo = new CapInfo();
        mCmdId = new OptionsCmdId();
        mUserData = 0;
    }

    private OptionsCmdStatus(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    OptionsCmdStatus(Parcel parcel, OptionsCmdStatus optionscmdstatus)
    {
        this(parcel);
    }

    public static OptionsCmdStatus getOptionsCmdStatusInstance()
    {
        return new OptionsCmdStatus();
    }

    public int describeContents()
    {
        return 0;
    }

    public CapInfo getCapInfo()
    {
        return mCapInfo;
    }

    public OptionsCmdId getCmdId()
    {
        return mCmdId;
    }

    public StatusCode getStatus()
    {
        return mStatus;
    }

    public int getUserData()
    {
        return mUserData;
    }

    public void readFromParcel(Parcel parcel)
    {
        mUserData = parcel.readInt();
        mCmdId = (OptionsCmdId)parcel.readParcelable(com/android/ims/internal/uce/options/OptionsCmdId.getClassLoader());
        mStatus = (StatusCode)parcel.readParcelable(com/android/ims/internal/uce/common/StatusCode.getClassLoader());
        mCapInfo = (CapInfo)parcel.readParcelable(com/android/ims/internal/uce/common/CapInfo.getClassLoader());
    }

    public void setCapInfo(CapInfo capinfo)
    {
        mCapInfo = capinfo;
    }

    public void setCmdId(OptionsCmdId optionscmdid)
    {
        mCmdId = optionscmdid;
    }

    public void setStatus(StatusCode statuscode)
    {
        mStatus = statuscode;
    }

    public void setUserData(int i)
    {
        mUserData = i;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mUserData);
        parcel.writeParcelable(mCmdId, i);
        parcel.writeParcelable(mStatus, i);
        parcel.writeParcelable(mCapInfo, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public OptionsCmdStatus createFromParcel(Parcel parcel)
        {
            return new OptionsCmdStatus(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public OptionsCmdStatus[] newArray(int i)
        {
            return new OptionsCmdStatus[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private CapInfo mCapInfo;
    private OptionsCmdId mCmdId;
    private StatusCode mStatus;
    private int mUserData;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.presence;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.ims.internal.uce.common.StatusCode;

// Referenced classes of package com.android.ims.internal.uce.presence:
//            PresCmdId

public class PresCmdStatus
    implements Parcelable
{

    public PresCmdStatus()
    {
        mCmdId = new PresCmdId();
        mStatus = new StatusCode();
        mStatus = new StatusCode();
    }

    private PresCmdStatus(Parcel parcel)
    {
        mCmdId = new PresCmdId();
        mStatus = new StatusCode();
        readFromParcel(parcel);
    }

    PresCmdStatus(Parcel parcel, PresCmdStatus prescmdstatus)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public PresCmdId getCmdId()
    {
        return mCmdId;
    }

    public int getRequestId()
    {
        return mRequestId;
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
        mRequestId = parcel.readInt();
        mCmdId = (PresCmdId)parcel.readParcelable(com/android/ims/internal/uce/presence/PresCmdId.getClassLoader());
        mStatus = (StatusCode)parcel.readParcelable(com/android/ims/internal/uce/common/StatusCode.getClassLoader());
    }

    public void setCmdId(PresCmdId prescmdid)
    {
        mCmdId = prescmdid;
    }

    public void setRequestId(int i)
    {
        mRequestId = i;
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
        parcel.writeInt(mRequestId);
        parcel.writeParcelable(mCmdId, i);
        parcel.writeParcelable(mStatus, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PresCmdStatus createFromParcel(Parcel parcel)
        {
            return new PresCmdStatus(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PresCmdStatus[] newArray(int i)
        {
            return new PresCmdStatus[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private PresCmdId mCmdId;
    private int mRequestId;
    private StatusCode mStatus;
    private int mUserData;

}

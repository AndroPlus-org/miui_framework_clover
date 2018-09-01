// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.presence;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

// Referenced classes of package com.android.ims.internal.uce.presence:
//            PresTupleInfo

public class PresResInstanceInfo
    implements Parcelable
{

    public PresResInstanceInfo()
    {
        mId = "";
        mReason = "";
        mPresentityUri = "";
    }

    private PresResInstanceInfo(Parcel parcel)
    {
        mId = "";
        mReason = "";
        mPresentityUri = "";
        readFromParcel(parcel);
    }

    PresResInstanceInfo(Parcel parcel, PresResInstanceInfo presresinstanceinfo)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public String getPresentityUri()
    {
        return mPresentityUri;
    }

    public String getReason()
    {
        return mReason;
    }

    public String getResId()
    {
        return mId;
    }

    public int getResInstanceState()
    {
        return mResInstanceState;
    }

    public PresTupleInfo[] getTupleInfo()
    {
        return mTupleInfoArray;
    }

    public void readFromParcel(Parcel parcel)
    {
        mId = parcel.readString();
        mReason = parcel.readString();
        mResInstanceState = parcel.readInt();
        mPresentityUri = parcel.readString();
        parcel = parcel.readParcelableArray(com/android/ims/internal/uce/presence/PresTupleInfo.getClassLoader());
        mTupleInfoArray = new PresTupleInfo[0];
        if(parcel != null)
            mTupleInfoArray = (PresTupleInfo[])Arrays.copyOf(parcel, parcel.length, [Lcom/android/ims/internal/uce/presence/PresTupleInfo;);
    }

    public void setPresentityUri(String s)
    {
        mPresentityUri = s;
    }

    public void setReason(String s)
    {
        mReason = s;
    }

    public void setResId(String s)
    {
        mId = s;
    }

    public void setResInstanceState(int i)
    {
        mResInstanceState = i;
    }

    public void setTupleInfo(PresTupleInfo aprestupleinfo[])
    {
        mTupleInfoArray = new PresTupleInfo[aprestupleinfo.length];
        mTupleInfoArray = aprestupleinfo;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mId);
        parcel.writeString(mReason);
        parcel.writeInt(mResInstanceState);
        parcel.writeString(mPresentityUri);
        parcel.writeParcelableArray(mTupleInfoArray, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PresResInstanceInfo createFromParcel(Parcel parcel)
        {
            return new PresResInstanceInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PresResInstanceInfo[] newArray(int i)
        {
            return new PresResInstanceInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int UCE_PRES_RES_INSTANCE_STATE_ACTIVE = 0;
    public static final int UCE_PRES_RES_INSTANCE_STATE_PENDING = 1;
    public static final int UCE_PRES_RES_INSTANCE_STATE_TERMINATED = 2;
    public static final int UCE_PRES_RES_INSTANCE_STATE_UNKNOWN = 3;
    public static final int UCE_PRES_RES_INSTANCE_UNKNOWN = 4;
    private String mId;
    private String mPresentityUri;
    private String mReason;
    private int mResInstanceState;
    private PresTupleInfo mTupleInfoArray[];

}

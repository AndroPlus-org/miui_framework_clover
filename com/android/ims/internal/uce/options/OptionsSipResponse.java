// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.options;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package com.android.ims.internal.uce.options:
//            OptionsCmdId

public class OptionsSipResponse
    implements Parcelable
{

    public OptionsSipResponse()
    {
        mRequestId = 0;
        mSipResponseCode = 0;
        mRetryAfter = 0;
        mReasonPhrase = "";
        mCmdId = new OptionsCmdId();
    }

    private OptionsSipResponse(Parcel parcel)
    {
        mRequestId = 0;
        mSipResponseCode = 0;
        mRetryAfter = 0;
        mReasonPhrase = "";
        readFromParcel(parcel);
    }

    OptionsSipResponse(Parcel parcel, OptionsSipResponse optionssipresponse)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public OptionsCmdId getCmdId()
    {
        return mCmdId;
    }

    public String getReasonPhrase()
    {
        return mReasonPhrase;
    }

    public int getRequestId()
    {
        return mRequestId;
    }

    public int getRetryAfter()
    {
        return mRetryAfter;
    }

    public int getSipResponseCode()
    {
        return mSipResponseCode;
    }

    public void readFromParcel(Parcel parcel)
    {
        mRequestId = parcel.readInt();
        mSipResponseCode = parcel.readInt();
        mReasonPhrase = parcel.readString();
        mCmdId = (OptionsCmdId)parcel.readParcelable(com/android/ims/internal/uce/options/OptionsCmdId.getClassLoader());
        mRetryAfter = parcel.readInt();
    }

    public void setCmdId(OptionsCmdId optionscmdid)
    {
        mCmdId = optionscmdid;
    }

    public void setReasonPhrase(String s)
    {
        mReasonPhrase = s;
    }

    public void setRequestId(int i)
    {
        mRequestId = i;
    }

    public void setRetryAfter(int i)
    {
        mRetryAfter = i;
    }

    public void setSipResponseCode(int i)
    {
        mSipResponseCode = i;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mRequestId);
        parcel.writeInt(mSipResponseCode);
        parcel.writeString(mReasonPhrase);
        parcel.writeParcelable(mCmdId, i);
        parcel.writeInt(mRetryAfter);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public OptionsSipResponse createFromParcel(Parcel parcel)
        {
            return new OptionsSipResponse(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public OptionsSipResponse[] newArray(int i)
        {
            return new OptionsSipResponse[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private OptionsCmdId mCmdId;
    private String mReasonPhrase;
    private int mRequestId;
    private int mRetryAfter;
    private int mSipResponseCode;

}

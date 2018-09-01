// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.presence;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package com.android.ims.internal.uce.presence:
//            PresCmdId

public class PresSipResponse
    implements Parcelable
{

    public PresSipResponse()
    {
        mCmdId = new PresCmdId();
        mRequestId = 0;
        mSipResponseCode = 0;
        mRetryAfter = 0;
        mReasonPhrase = "";
    }

    private PresSipResponse(Parcel parcel)
    {
        mCmdId = new PresCmdId();
        mRequestId = 0;
        mSipResponseCode = 0;
        mRetryAfter = 0;
        mReasonPhrase = "";
        readFromParcel(parcel);
    }

    PresSipResponse(Parcel parcel, PresSipResponse pressipresponse)
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
        mCmdId = (PresCmdId)parcel.readParcelable(com/android/ims/internal/uce/presence/PresCmdId.getClassLoader());
        mRetryAfter = parcel.readInt();
    }

    public void setCmdId(PresCmdId prescmdid)
    {
        mCmdId = prescmdid;
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

        public PresSipResponse createFromParcel(Parcel parcel)
        {
            return new PresSipResponse(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PresSipResponse[] newArray(int i)
        {
            return new PresSipResponse[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private PresCmdId mCmdId;
    private String mReasonPhrase;
    private int mRequestId;
    private int mRetryAfter;
    private int mSipResponseCode;

}

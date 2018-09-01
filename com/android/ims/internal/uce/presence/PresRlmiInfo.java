// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.presence;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package com.android.ims.internal.uce.presence:
//            PresSubscriptionState

public class PresRlmiInfo
    implements Parcelable
{

    public PresRlmiInfo()
    {
        mUri = "";
        mListName = "";
    }

    private PresRlmiInfo(Parcel parcel)
    {
        mUri = "";
        mListName = "";
        readFromParcel(parcel);
    }

    PresRlmiInfo(Parcel parcel, PresRlmiInfo presrlmiinfo)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public String getListName()
    {
        return mListName;
    }

    public PresSubscriptionState getPresSubscriptionState()
    {
        return mPresSubscriptionState;
    }

    public int getRequestId()
    {
        return mRequestId;
    }

    public int getSubscriptionExpireTime()
    {
        return mSubscriptionExpireTime;
    }

    public String getSubscriptionTerminatedReason()
    {
        return mSubscriptionTerminatedReason;
    }

    public String getUri()
    {
        return mUri;
    }

    public int getVersion()
    {
        return mVersion;
    }

    public boolean isFullState()
    {
        return mFullState;
    }

    public void readFromParcel(Parcel parcel)
    {
        boolean flag = false;
        mUri = parcel.readString();
        mVersion = parcel.readInt();
        if(parcel.readInt() != 0)
            flag = true;
        mFullState = flag;
        mListName = parcel.readString();
        mRequestId = parcel.readInt();
        mPresSubscriptionState = (PresSubscriptionState)parcel.readParcelable(com/android/ims/internal/uce/presence/PresSubscriptionState.getClassLoader());
        mSubscriptionExpireTime = parcel.readInt();
        mSubscriptionTerminatedReason = parcel.readString();
    }

    public void setFullState(boolean flag)
    {
        mFullState = flag;
    }

    public void setListName(String s)
    {
        mListName = s;
    }

    public void setPresSubscriptionState(PresSubscriptionState pressubscriptionstate)
    {
        mPresSubscriptionState = pressubscriptionstate;
    }

    public void setRequestId(int i)
    {
        mRequestId = i;
    }

    public void setSubscriptionExpireTime(int i)
    {
        mSubscriptionExpireTime = i;
    }

    public void setSubscriptionTerminatedReason(String s)
    {
        mSubscriptionTerminatedReason = s;
    }

    public void setUri(String s)
    {
        mUri = s;
    }

    public void setVersion(int i)
    {
        mVersion = i;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mUri);
        parcel.writeInt(mVersion);
        int j;
        if(mFullState)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        parcel.writeString(mListName);
        parcel.writeInt(mRequestId);
        parcel.writeParcelable(mPresSubscriptionState, i);
        parcel.writeInt(mSubscriptionExpireTime);
        parcel.writeString(mSubscriptionTerminatedReason);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PresRlmiInfo createFromParcel(Parcel parcel)
        {
            return new PresRlmiInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PresRlmiInfo[] newArray(int i)
        {
            return new PresRlmiInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private boolean mFullState;
    private String mListName;
    private PresSubscriptionState mPresSubscriptionState;
    private int mRequestId;
    private int mSubscriptionExpireTime;
    private String mSubscriptionTerminatedReason;
    private String mUri;
    private int mVersion;

}

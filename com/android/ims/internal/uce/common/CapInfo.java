// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.common;

import android.os.Parcel;
import android.os.Parcelable;

public class CapInfo
    implements Parcelable
{

    public CapInfo()
    {
        mImSupported = false;
        mFtSupported = false;
        mFtThumbSupported = false;
        mFtSnFSupported = false;
        mFtHttpSupported = false;
        mIsSupported = false;
        mVsDuringCSSupported = false;
        mVsSupported = false;
        mSpSupported = false;
        mCdViaPresenceSupported = false;
        mIpVoiceSupported = false;
        mIpVideoSupported = false;
        mGeoPullFtSupported = false;
        mGeoPullSupported = false;
        mGeoPushSupported = false;
        mSmSupported = false;
        mFullSnFGroupChatSupported = false;
        mRcsIpVoiceCallSupported = false;
        mRcsIpVideoCallSupported = false;
        mRcsIpVideoOnlyCallSupported = false;
        mExts = new String[10];
        mCapTimestamp = 0L;
    }

    private CapInfo(Parcel parcel)
    {
        mImSupported = false;
        mFtSupported = false;
        mFtThumbSupported = false;
        mFtSnFSupported = false;
        mFtHttpSupported = false;
        mIsSupported = false;
        mVsDuringCSSupported = false;
        mVsSupported = false;
        mSpSupported = false;
        mCdViaPresenceSupported = false;
        mIpVoiceSupported = false;
        mIpVideoSupported = false;
        mGeoPullFtSupported = false;
        mGeoPullSupported = false;
        mGeoPushSupported = false;
        mSmSupported = false;
        mFullSnFGroupChatSupported = false;
        mRcsIpVoiceCallSupported = false;
        mRcsIpVideoCallSupported = false;
        mRcsIpVideoOnlyCallSupported = false;
        mExts = new String[10];
        mCapTimestamp = 0L;
        readFromParcel(parcel);
    }

    CapInfo(Parcel parcel, CapInfo capinfo)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public long getCapTimestamp()
    {
        return mCapTimestamp;
    }

    public String[] getExts()
    {
        return mExts;
    }

    public boolean isCdViaPresenceSupported()
    {
        return mCdViaPresenceSupported;
    }

    public boolean isFtHttpSupported()
    {
        return mFtHttpSupported;
    }

    public boolean isFtSnFSupported()
    {
        return mFtSnFSupported;
    }

    public boolean isFtSupported()
    {
        return mFtSupported;
    }

    public boolean isFtThumbSupported()
    {
        return mFtThumbSupported;
    }

    public boolean isFullSnFGroupChatSupported()
    {
        return mFullSnFGroupChatSupported;
    }

    public boolean isGeoPullFtSupported()
    {
        return mGeoPullFtSupported;
    }

    public boolean isGeoPullSupported()
    {
        return mGeoPullSupported;
    }

    public boolean isGeoPushSupported()
    {
        return mGeoPushSupported;
    }

    public boolean isImSupported()
    {
        return mImSupported;
    }

    public boolean isIpVideoSupported()
    {
        return mIpVideoSupported;
    }

    public boolean isIpVoiceSupported()
    {
        return mIpVoiceSupported;
    }

    public boolean isIsSupported()
    {
        return mIsSupported;
    }

    public boolean isRcsIpVideoCallSupported()
    {
        return mRcsIpVideoCallSupported;
    }

    public boolean isRcsIpVideoOnlyCallSupported()
    {
        return mRcsIpVideoOnlyCallSupported;
    }

    public boolean isRcsIpVoiceCallSupported()
    {
        return mRcsIpVoiceCallSupported;
    }

    public boolean isSmSupported()
    {
        return mSmSupported;
    }

    public boolean isSpSupported()
    {
        return mSpSupported;
    }

    public boolean isVsDuringCSSupported()
    {
        return mVsDuringCSSupported;
    }

    public boolean isVsSupported()
    {
        return mVsSupported;
    }

    public void readFromParcel(Parcel parcel)
    {
        boolean flag = false;
        boolean flag1;
        if(parcel.readInt() == 0)
            flag1 = false;
        else
            flag1 = true;
        mImSupported = flag1;
        if(parcel.readInt() == 0)
            flag1 = false;
        else
            flag1 = true;
        mFtSupported = flag1;
        if(parcel.readInt() == 0)
            flag1 = false;
        else
            flag1 = true;
        mFtThumbSupported = flag1;
        if(parcel.readInt() == 0)
            flag1 = false;
        else
            flag1 = true;
        mFtSnFSupported = flag1;
        if(parcel.readInt() == 0)
            flag1 = false;
        else
            flag1 = true;
        mFtHttpSupported = flag1;
        if(parcel.readInt() == 0)
            flag1 = false;
        else
            flag1 = true;
        mIsSupported = flag1;
        if(parcel.readInt() == 0)
            flag1 = false;
        else
            flag1 = true;
        mVsDuringCSSupported = flag1;
        if(parcel.readInt() == 0)
            flag1 = false;
        else
            flag1 = true;
        mVsSupported = flag1;
        if(parcel.readInt() == 0)
            flag1 = false;
        else
            flag1 = true;
        mSpSupported = flag1;
        if(parcel.readInt() == 0)
            flag1 = false;
        else
            flag1 = true;
        mCdViaPresenceSupported = flag1;
        if(parcel.readInt() == 0)
            flag1 = false;
        else
            flag1 = true;
        mIpVoiceSupported = flag1;
        if(parcel.readInt() == 0)
            flag1 = false;
        else
            flag1 = true;
        mIpVideoSupported = flag1;
        if(parcel.readInt() == 0)
            flag1 = false;
        else
            flag1 = true;
        mGeoPullFtSupported = flag1;
        if(parcel.readInt() == 0)
            flag1 = false;
        else
            flag1 = true;
        mGeoPullSupported = flag1;
        if(parcel.readInt() == 0)
            flag1 = false;
        else
            flag1 = true;
        mGeoPushSupported = flag1;
        if(parcel.readInt() == 0)
            flag1 = false;
        else
            flag1 = true;
        mSmSupported = flag1;
        if(parcel.readInt() == 0)
            flag1 = false;
        else
            flag1 = true;
        mFullSnFGroupChatSupported = flag1;
        if(parcel.readInt() == 0)
            flag1 = false;
        else
            flag1 = true;
        mRcsIpVoiceCallSupported = flag1;
        if(parcel.readInt() == 0)
            flag1 = false;
        else
            flag1 = true;
        mRcsIpVideoCallSupported = flag1;
        if(parcel.readInt() == 0)
            flag1 = flag;
        else
            flag1 = true;
        mRcsIpVideoOnlyCallSupported = flag1;
        mExts = parcel.createStringArray();
        mCapTimestamp = parcel.readLong();
    }

    public void setCapTimestamp(long l)
    {
        mCapTimestamp = l;
    }

    public void setCdViaPresenceSupported(boolean flag)
    {
        mCdViaPresenceSupported = flag;
    }

    public void setExts(String as[])
    {
        mExts = as;
    }

    public void setFtHttpSupported(boolean flag)
    {
        mFtHttpSupported = flag;
    }

    public void setFtSnFSupported(boolean flag)
    {
        mFtSnFSupported = flag;
    }

    public void setFtSupported(boolean flag)
    {
        mFtSupported = flag;
    }

    public void setFtThumbSupported(boolean flag)
    {
        mFtThumbSupported = flag;
    }

    public void setFullSnFGroupChatSupported(boolean flag)
    {
        mFullSnFGroupChatSupported = flag;
    }

    public void setGeoPullFtSupported(boolean flag)
    {
        mGeoPullFtSupported = flag;
    }

    public void setGeoPullSupported(boolean flag)
    {
        mGeoPullSupported = flag;
    }

    public void setGeoPushSupported(boolean flag)
    {
        mGeoPushSupported = flag;
    }

    public void setImSupported(boolean flag)
    {
        mImSupported = flag;
    }

    public void setIpVideoSupported(boolean flag)
    {
        mIpVideoSupported = flag;
    }

    public void setIpVoiceSupported(boolean flag)
    {
        mIpVoiceSupported = flag;
    }

    public void setIsSupported(boolean flag)
    {
        mIsSupported = flag;
    }

    public void setRcsIpVideoCallSupported(boolean flag)
    {
        mRcsIpVideoCallSupported = flag;
    }

    public void setRcsIpVideoOnlyCallSupported(boolean flag)
    {
        mRcsIpVideoOnlyCallSupported = flag;
    }

    public void setRcsIpVoiceCallSupported(boolean flag)
    {
        mRcsIpVoiceCallSupported = flag;
    }

    public void setSmSupported(boolean flag)
    {
        mSmSupported = flag;
    }

    public void setSpSupported(boolean flag)
    {
        mSpSupported = flag;
    }

    public void setVsDuringCSSupported(boolean flag)
    {
        mVsDuringCSSupported = flag;
    }

    public void setVsSupported(boolean flag)
    {
        mVsSupported = flag;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        if(mImSupported)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mFtSupported)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mFtThumbSupported)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mFtSnFSupported)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mFtHttpSupported)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mIsSupported)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mVsDuringCSSupported)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mVsSupported)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mSpSupported)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mCdViaPresenceSupported)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mIpVoiceSupported)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mIpVideoSupported)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mGeoPullFtSupported)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mGeoPullSupported)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mGeoPushSupported)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mSmSupported)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mFullSnFGroupChatSupported)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mRcsIpVoiceCallSupported)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mRcsIpVideoCallSupported)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mRcsIpVideoOnlyCallSupported)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeStringArray(mExts);
        parcel.writeLong(mCapTimestamp);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CapInfo createFromParcel(Parcel parcel)
        {
            return new CapInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CapInfo[] newArray(int i)
        {
            return new CapInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private long mCapTimestamp;
    private boolean mCdViaPresenceSupported;
    private String mExts[];
    private boolean mFtHttpSupported;
    private boolean mFtSnFSupported;
    private boolean mFtSupported;
    private boolean mFtThumbSupported;
    private boolean mFullSnFGroupChatSupported;
    private boolean mGeoPullFtSupported;
    private boolean mGeoPullSupported;
    private boolean mGeoPushSupported;
    private boolean mImSupported;
    private boolean mIpVideoSupported;
    private boolean mIpVoiceSupported;
    private boolean mIsSupported;
    private boolean mRcsIpVideoCallSupported;
    private boolean mRcsIpVideoOnlyCallSupported;
    private boolean mRcsIpVoiceCallSupported;
    private boolean mSmSupported;
    private boolean mSpSupported;
    private boolean mVsDuringCSSupported;
    private boolean mVsSupported;

}

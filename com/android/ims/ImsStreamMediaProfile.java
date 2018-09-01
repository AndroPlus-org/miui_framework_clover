// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims;

import android.os.Parcel;
import android.os.Parcelable;

public class ImsStreamMediaProfile
    implements Parcelable
{

    public ImsStreamMediaProfile()
    {
        mAudioQuality = 0;
        mAudioDirection = 3;
        mVideoQuality = 0;
        mVideoDirection = -1;
        mRttMode = 0;
    }

    public ImsStreamMediaProfile(int i)
    {
        mRttMode = i;
    }

    public ImsStreamMediaProfile(int i, int j, int k, int l)
    {
        mAudioQuality = i;
        mAudioDirection = j;
        mVideoQuality = k;
        mVideoDirection = l;
    }

    public ImsStreamMediaProfile(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    private void readFromParcel(Parcel parcel)
    {
        mAudioQuality = parcel.readInt();
        mAudioDirection = parcel.readInt();
        mVideoQuality = parcel.readInt();
        mVideoDirection = parcel.readInt();
        mRttMode = parcel.readInt();
    }

    public void copyFrom(ImsStreamMediaProfile imsstreammediaprofile)
    {
        mAudioQuality = imsstreammediaprofile.mAudioQuality;
        mAudioDirection = imsstreammediaprofile.mAudioDirection;
        mVideoQuality = imsstreammediaprofile.mVideoQuality;
        mVideoDirection = imsstreammediaprofile.mVideoDirection;
        mRttMode = imsstreammediaprofile.mRttMode;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean isRttCall()
    {
        boolean flag = true;
        if(mRttMode != 1)
            flag = false;
        return flag;
    }

    public void setRttMode(int i)
    {
        mRttMode = i;
    }

    public String toString()
    {
        return (new StringBuilder()).append("{ audioQuality=").append(mAudioQuality).append(", audioDirection=").append(mAudioDirection).append(", videoQuality=").append(mVideoQuality).append(", videoDirection=").append(mVideoDirection).append(", rttMode=").append(mRttMode).append(" }").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mAudioQuality);
        parcel.writeInt(mAudioDirection);
        parcel.writeInt(mVideoQuality);
        parcel.writeInt(mVideoDirection);
        parcel.writeInt(mRttMode);
    }

    public static final int AUDIO_QUALITY_AMR = 1;
    public static final int AUDIO_QUALITY_AMR_WB = 2;
    public static final int AUDIO_QUALITY_EVRC = 4;
    public static final int AUDIO_QUALITY_EVRC_B = 5;
    public static final int AUDIO_QUALITY_EVRC_NW = 7;
    public static final int AUDIO_QUALITY_EVRC_WB = 6;
    public static final int AUDIO_QUALITY_EVS_FB = 20;
    public static final int AUDIO_QUALITY_EVS_NB = 17;
    public static final int AUDIO_QUALITY_EVS_SWB = 19;
    public static final int AUDIO_QUALITY_EVS_WB = 18;
    public static final int AUDIO_QUALITY_G711A = 13;
    public static final int AUDIO_QUALITY_G711AB = 15;
    public static final int AUDIO_QUALITY_G711U = 11;
    public static final int AUDIO_QUALITY_G722 = 14;
    public static final int AUDIO_QUALITY_G723 = 12;
    public static final int AUDIO_QUALITY_G729 = 16;
    public static final int AUDIO_QUALITY_GSM_EFR = 8;
    public static final int AUDIO_QUALITY_GSM_FR = 9;
    public static final int AUDIO_QUALITY_GSM_HR = 10;
    public static final int AUDIO_QUALITY_NONE = 0;
    public static final int AUDIO_QUALITY_QCELP13K = 3;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ImsStreamMediaProfile createFromParcel(Parcel parcel)
        {
            return new ImsStreamMediaProfile(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ImsStreamMediaProfile[] newArray(int i)
        {
            return new ImsStreamMediaProfile[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DIRECTION_INACTIVE = 0;
    public static final int DIRECTION_INVALID = -1;
    public static final int DIRECTION_RECEIVE = 1;
    public static final int DIRECTION_SEND = 2;
    public static final int DIRECTION_SEND_RECEIVE = 3;
    public static final int RTT_MODE_DISABLED = 0;
    public static final int RTT_MODE_FULL = 1;
    private static final String TAG = "ImsStreamMediaProfile";
    public static final int VIDEO_QUALITY_NONE = 0;
    public static final int VIDEO_QUALITY_QCIF = 1;
    public static final int VIDEO_QUALITY_QVGA_LANDSCAPE = 2;
    public static final int VIDEO_QUALITY_QVGA_PORTRAIT = 4;
    public static final int VIDEO_QUALITY_VGA_LANDSCAPE = 8;
    public static final int VIDEO_QUALITY_VGA_PORTRAIT = 16;
    public int mAudioDirection;
    public int mAudioQuality;
    public int mRttMode;
    public int mVideoDirection;
    public int mVideoQuality;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.Parcel;

public final class SubtitleData
{

    public SubtitleData(Parcel parcel)
    {
        if(!parseParcel(parcel))
            throw new IllegalArgumentException("parseParcel() fails");
        else
            return;
    }

    private boolean parseParcel(Parcel parcel)
    {
        parcel.setDataPosition(0);
        if(parcel.dataAvail() == 0)
        {
            return false;
        } else
        {
            mTrackIndex = parcel.readInt();
            mStartTimeUs = parcel.readLong();
            mDurationUs = parcel.readLong();
            mData = new byte[parcel.readInt()];
            parcel.readByteArray(mData);
            return true;
        }
    }

    public byte[] getData()
    {
        return mData;
    }

    public long getDurationUs()
    {
        return mDurationUs;
    }

    public long getStartTimeUs()
    {
        return mStartTimeUs;
    }

    public int getTrackIndex()
    {
        return mTrackIndex;
    }

    private static final String TAG = "SubtitleData";
    private byte mData[];
    private long mDurationUs;
    private long mStartTimeUs;
    private int mTrackIndex;
}

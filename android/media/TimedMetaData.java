// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.Parcel;

public final class TimedMetaData
{

    private TimedMetaData(Parcel parcel)
    {
        if(!parseParcel(parcel))
            throw new IllegalArgumentException("parseParcel() fails");
        else
            return;
    }

    static TimedMetaData createTimedMetaDataFromParcel(Parcel parcel)
    {
        return new TimedMetaData(parcel);
    }

    private boolean parseParcel(Parcel parcel)
    {
        parcel.setDataPosition(0);
        if(parcel.dataAvail() == 0)
        {
            return false;
        } else
        {
            mTimestampUs = parcel.readLong();
            mMetaData = new byte[parcel.readInt()];
            parcel.readByteArray(mMetaData);
            return true;
        }
    }

    public byte[] getMetaData()
    {
        return mMetaData;
    }

    public long getTimestamp()
    {
        return mTimestampUs;
    }

    private static final String TAG = "TimedMetaData";
    private byte mMetaData[];
    private long mTimestampUs;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.utils;

import android.os.Parcel;
import android.os.Parcelable;

public class SubmitInfo
    implements Parcelable
{

    public SubmitInfo()
    {
        mRequestId = -1;
        mLastFrameNumber = -1L;
    }

    public SubmitInfo(int i, long l)
    {
        mRequestId = i;
        mLastFrameNumber = l;
    }

    private SubmitInfo(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    SubmitInfo(Parcel parcel, SubmitInfo submitinfo)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public long getLastFrameNumber()
    {
        return mLastFrameNumber;
    }

    public int getRequestId()
    {
        return mRequestId;
    }

    public void readFromParcel(Parcel parcel)
    {
        mRequestId = parcel.readInt();
        mLastFrameNumber = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mRequestId);
        parcel.writeLong(mLastFrameNumber);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SubmitInfo createFromParcel(Parcel parcel)
        {
            return new SubmitInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SubmitInfo[] newArray(int i)
        {
            return new SubmitInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private long mLastFrameNumber;
    private int mRequestId;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.process;

import android.os.Parcel;
import android.os.Parcelable;

public class ForegroundInfo
    implements Parcelable
{

    public ForegroundInfo()
    {
        mForegroundUid = -1;
        mLastForegroundUid = -1;
        mMultiWindowForegroundUid = -1;
    }

    private ForegroundInfo(Parcel parcel)
    {
        mForegroundUid = -1;
        mLastForegroundUid = -1;
        mMultiWindowForegroundUid = -1;
        mForegroundPackageName = parcel.readString();
        mForegroundUid = parcel.readInt();
        mLastForegroundPackageName = parcel.readString();
        mLastForegroundUid = parcel.readInt();
        mMultiWindowForegroundPackageName = parcel.readString();
        mMultiWindowForegroundUid = parcel.readInt();
    }

    ForegroundInfo(Parcel parcel, ForegroundInfo foregroundinfo)
    {
        this(parcel);
    }

    public ForegroundInfo(ForegroundInfo foregroundinfo)
    {
        mForegroundUid = -1;
        mLastForegroundUid = -1;
        mMultiWindowForegroundUid = -1;
        mForegroundPackageName = foregroundinfo.mForegroundPackageName;
        mForegroundUid = foregroundinfo.mForegroundUid;
        mLastForegroundPackageName = foregroundinfo.mLastForegroundPackageName;
        mLastForegroundUid = foregroundinfo.mLastForegroundUid;
        mMultiWindowForegroundPackageName = foregroundinfo.mMultiWindowForegroundPackageName;
        mMultiWindowForegroundUid = foregroundinfo.mMultiWindowForegroundUid;
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        return (new StringBuilder()).append("ForegroundInfo{mForegroundPackageName='").append(mForegroundPackageName).append('\'').append(", mForegroundUid=").append(mForegroundUid).append(", mLastForegroundPackageName='").append(mLastForegroundPackageName).append('\'').append(", mLastForegroundUid=").append(mLastForegroundUid).append(", mMultiWindowForegroundPackageName='").append(mMultiWindowForegroundPackageName).append('\'').append(", mMultiWindowForegroundUid=").append(mMultiWindowForegroundUid).append('}').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mForegroundPackageName);
        parcel.writeInt(mForegroundUid);
        parcel.writeString(mLastForegroundPackageName);
        parcel.writeInt(mLastForegroundUid);
        parcel.writeString(mMultiWindowForegroundPackageName);
        parcel.writeInt(mMultiWindowForegroundUid);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ForegroundInfo createFromParcel(Parcel parcel)
        {
            return new ForegroundInfo(parcel, null);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public ForegroundInfo[] newArray(int i)
        {
            return new ForegroundInfo[i];
        }

    }
;
    public String mForegroundPackageName;
    public int mForegroundUid;
    public String mLastForegroundPackageName;
    public int mLastForegroundUid;
    public String mMultiWindowForegroundPackageName;
    public int mMultiWindowForegroundUid;

}

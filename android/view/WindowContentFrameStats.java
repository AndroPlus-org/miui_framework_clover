// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.view:
//            FrameStats

public final class WindowContentFrameStats extends FrameStats
    implements Parcelable
{

    public WindowContentFrameStats()
    {
    }

    private WindowContentFrameStats(Parcel parcel)
    {
        mRefreshPeriodNano = parcel.readLong();
        mFramesPostedTimeNano = parcel.createLongArray();
        mFramesPresentedTimeNano = parcel.createLongArray();
        mFramesReadyTimeNano = parcel.createLongArray();
    }

    WindowContentFrameStats(Parcel parcel, WindowContentFrameStats windowcontentframestats)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public long getFramePostedTimeNano(int i)
    {
        if(mFramesPostedTimeNano == null)
            throw new IndexOutOfBoundsException();
        else
            return mFramesPostedTimeNano[i];
    }

    public long getFrameReadyTimeNano(int i)
    {
        if(mFramesReadyTimeNano == null)
            throw new IndexOutOfBoundsException();
        else
            return mFramesReadyTimeNano[i];
    }

    public void init(long l, long al[], long al1[], long al2[])
    {
        mRefreshPeriodNano = l;
        mFramesPostedTimeNano = al;
        mFramesPresentedTimeNano = al1;
        mFramesReadyTimeNano = al2;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("WindowContentFrameStats[");
        stringbuilder.append("frameCount:").append(getFrameCount());
        stringbuilder.append(", fromTimeNano:").append(getStartTimeNano());
        stringbuilder.append(", toTimeNano:").append(getEndTimeNano());
        stringbuilder.append(']');
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(mRefreshPeriodNano);
        parcel.writeLongArray(mFramesPostedTimeNano);
        parcel.writeLongArray(mFramesPresentedTimeNano);
        parcel.writeLongArray(mFramesReadyTimeNano);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WindowContentFrameStats createFromParcel(Parcel parcel)
        {
            return new WindowContentFrameStats(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WindowContentFrameStats[] newArray(int i)
        {
            return new WindowContentFrameStats[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private long mFramesPostedTimeNano[];
    private long mFramesReadyTimeNano[];

}

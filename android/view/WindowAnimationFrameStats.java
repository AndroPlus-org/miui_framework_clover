// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.view:
//            FrameStats

public final class WindowAnimationFrameStats extends FrameStats
    implements Parcelable
{

    public WindowAnimationFrameStats()
    {
    }

    private WindowAnimationFrameStats(Parcel parcel)
    {
        mRefreshPeriodNano = parcel.readLong();
        mFramesPresentedTimeNano = parcel.createLongArray();
    }

    WindowAnimationFrameStats(Parcel parcel, WindowAnimationFrameStats windowanimationframestats)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public void init(long l, long al[])
    {
        mRefreshPeriodNano = l;
        mFramesPresentedTimeNano = al;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("WindowAnimationFrameStats[");
        stringbuilder.append("frameCount:").append(getFrameCount());
        stringbuilder.append(", fromTimeNano:").append(getStartTimeNano());
        stringbuilder.append(", toTimeNano:").append(getEndTimeNano());
        stringbuilder.append(']');
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(mRefreshPeriodNano);
        parcel.writeLongArray(mFramesPresentedTimeNano);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WindowAnimationFrameStats createFromParcel(Parcel parcel)
        {
            return new WindowAnimationFrameStats(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WindowAnimationFrameStats[] newArray(int i)
        {
            return new WindowAnimationFrameStats[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;

}

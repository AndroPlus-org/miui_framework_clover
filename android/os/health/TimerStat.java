// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.health;

import android.os.Parcel;
import android.os.Parcelable;

public final class TimerStat
    implements Parcelable
{

    public TimerStat()
    {
    }

    public TimerStat(int i, long l)
    {
        mCount = i;
        mTime = l;
    }

    public TimerStat(Parcel parcel)
    {
        mCount = parcel.readInt();
        mTime = parcel.readLong();
    }

    public int describeContents()
    {
        return 0;
    }

    public int getCount()
    {
        return mCount;
    }

    public long getTime()
    {
        return mTime;
    }

    public void setCount(int i)
    {
        mCount = i;
    }

    public void setTime(long l)
    {
        mTime = l;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mCount);
        parcel.writeLong(mTime);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public TimerStat createFromParcel(Parcel parcel)
        {
            return new TimerStat(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public TimerStat[] newArray(int i)
        {
            return new TimerStat[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private int mCount;
    private long mTime;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            Parcelable, Parcel

public final class CpuUsageInfo
    implements Parcelable
{

    public CpuUsageInfo(long l, long l1)
    {
        mActive = l;
        mTotal = l1;
    }

    private CpuUsageInfo(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    CpuUsageInfo(Parcel parcel, CpuUsageInfo cpuusageinfo)
    {
        this(parcel);
    }

    private void readFromParcel(Parcel parcel)
    {
        mActive = parcel.readLong();
        mTotal = parcel.readLong();
    }

    public int describeContents()
    {
        return 0;
    }

    public long getActive()
    {
        return mActive;
    }

    public long getTotal()
    {
        return mTotal;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(mActive);
        parcel.writeLong(mTotal);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public CpuUsageInfo createFromParcel(Parcel parcel)
        {
            return new CpuUsageInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CpuUsageInfo[] newArray(int i)
        {
            return new CpuUsageInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private long mActive;
    private long mTotal;

}

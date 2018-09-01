// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.os.Parcel;
import android.os.Parcelable;

public class MemoryRegion
    implements Parcelable
{

    public MemoryRegion(Parcel parcel)
    {
        boolean flag = true;
        super();
        mSizeBytes = parcel.readInt();
        mSizeBytesFree = parcel.readInt();
        boolean flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mIsReadable = flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mIsWritable = flag1;
        if(parcel.readInt() != 0)
            flag1 = flag;
        else
            flag1 = false;
        mIsExecutable = flag1;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getCapacityBytes()
    {
        return mSizeBytes;
    }

    public int getFreeCapacityBytes()
    {
        return mSizeBytesFree;
    }

    public boolean isExecutable()
    {
        return mIsExecutable;
    }

    public boolean isReadable()
    {
        return mIsReadable;
    }

    public boolean isWritable()
    {
        return mIsWritable;
    }

    public String toString()
    {
        String s;
        if(isReadable())
            s = (new StringBuilder()).append("").append("r").toString();
        else
            s = (new StringBuilder()).append("").append("-").toString();
        if(isWritable())
            s = (new StringBuilder()).append(s).append("w").toString();
        else
            s = (new StringBuilder()).append(s).append("-").toString();
        if(isExecutable())
            s = (new StringBuilder()).append(s).append("x").toString();
        else
            s = (new StringBuilder()).append(s).append("-").toString();
        return (new StringBuilder()).append("[ ").append(mSizeBytesFree).append("/ ").append(mSizeBytes).append(" ] : ").append(s).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(mSizeBytes);
        parcel.writeInt(mSizeBytesFree);
        if(mIsReadable)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mIsWritable)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mIsExecutable)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public MemoryRegion createFromParcel(Parcel parcel)
        {
            return new MemoryRegion(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public MemoryRegion[] newArray(int i)
        {
            return new MemoryRegion[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private boolean mIsExecutable;
    private boolean mIsReadable;
    private boolean mIsWritable;
    private int mSizeBytes;
    private int mSizeBytesFree;

}

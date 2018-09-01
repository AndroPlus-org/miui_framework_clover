// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;

public final class UidRange
    implements Parcelable
{

    public UidRange(int i, int j)
    {
        if(i < 0)
            throw new IllegalArgumentException("Invalid start UID.");
        if(j < 0)
            throw new IllegalArgumentException("Invalid stop UID.");
        if(i > j)
        {
            throw new IllegalArgumentException("Invalid UID range.");
        } else
        {
            start = i;
            stop = j;
            return;
        }
    }

    public static UidRange createForUser(int i)
    {
        return new UidRange(i * 0x186a0, (i + 1) * 0x186a0 - 1);
    }

    public boolean contains(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(start <= i)
        {
            flag1 = flag;
            if(i <= stop)
                flag1 = true;
        }
        return flag1;
    }

    public boolean containsRange(UidRange uidrange)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(start <= uidrange.start)
        {
            flag1 = flag;
            if(uidrange.stop <= stop)
                flag1 = true;
        }
        return flag1;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(obj instanceof UidRange)
        {
            obj = (UidRange)obj;
            if(start != ((UidRange) (obj)).start || stop != ((UidRange) (obj)).stop)
                flag = false;
            return flag;
        } else
        {
            return false;
        }
    }

    public int getStartUser()
    {
        return start / 0x186a0;
    }

    public int hashCode()
    {
        return (start + 527) * 31 + stop;
    }

    public String toString()
    {
        return (new StringBuilder()).append(start).append("-").append(stop).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(start);
        parcel.writeInt(stop);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public UidRange createFromParcel(Parcel parcel)
        {
            return new UidRange(parcel.readInt(), parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public UidRange[] newArray(int i)
        {
            return new UidRange[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final int start;
    public final int stop;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.os.Parcel;
import android.os.Parcelable;

public final class PageRange
    implements Parcelable
{

    public PageRange(int i, int j)
    {
        if(i < 0)
            throw new IllegalArgumentException("start cannot be less than zero.");
        if(j < 0)
            throw new IllegalArgumentException("end cannot be less than zero.");
        if(i > j)
        {
            throw new IllegalArgumentException("start must be lesser than end.");
        } else
        {
            mStart = i;
            mEnd = j;
            return;
        }
    }

    private PageRange(Parcel parcel)
    {
        this(parcel.readInt(), parcel.readInt());
    }

    PageRange(Parcel parcel, PageRange pagerange)
    {
        this(parcel);
    }

    public boolean contains(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i >= mStart)
        {
            flag1 = flag;
            if(i <= mEnd)
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
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        obj = (PageRange)obj;
        if(mEnd != ((PageRange) (obj)).mEnd)
            return false;
        return mStart == ((PageRange) (obj)).mStart;
    }

    public int getEnd()
    {
        return mEnd;
    }

    public int getSize()
    {
        return (mEnd - mStart) + 1;
    }

    public int getStart()
    {
        return mStart;
    }

    public int hashCode()
    {
        return (mEnd + 31) * 31 + mStart;
    }

    public String toString()
    {
        if(mStart == 0 && mEnd == 0x7fffffff)
        {
            return "PageRange[<all pages>]";
        } else
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("PageRange[").append(mStart).append(" - ").append(mEnd).append("]");
            return stringbuilder.toString();
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mStart);
        parcel.writeInt(mEnd);
    }

    public static final PageRange ALL_PAGES;
    public static final PageRange ALL_PAGES_ARRAY[];
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PageRange createFromParcel(Parcel parcel)
        {
            return new PageRange(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PageRange[] newArray(int i)
        {
            return new PageRange[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final int mEnd;
    private final int mStart;

    static 
    {
        ALL_PAGES = new PageRange(0, 0x7fffffff);
        ALL_PAGES_ARRAY = (new PageRange[] {
            ALL_PAGES
        });
    }
}

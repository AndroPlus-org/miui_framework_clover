// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.utils;

import android.os.Parcel;
import android.os.Parcelable;

public class LongParcelable
    implements Parcelable
{

    public LongParcelable()
    {
        number = 0L;
    }

    public LongParcelable(long l)
    {
        number = l;
    }

    private LongParcelable(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    LongParcelable(Parcel parcel, LongParcelable longparcelable)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public long getNumber()
    {
        return number;
    }

    public void readFromParcel(Parcel parcel)
    {
        number = parcel.readLong();
    }

    public void setNumber(long l)
    {
        number = l;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(number);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public LongParcelable createFromParcel(Parcel parcel)
        {
            return new LongParcelable(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public LongParcelable[] newArray(int i)
        {
            return new LongParcelable[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private long number;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;

import android.os.Parcel;
import android.os.Parcelable;

public final class ResultStorageDescriptor
    implements Parcelable
{

    public ResultStorageDescriptor(int i, int j, int k)
    {
        mType = i;
        mOffset = j;
        mLength = k;
    }

    private ResultStorageDescriptor(Parcel parcel)
    {
        ReadFromParcel(parcel);
    }

    ResultStorageDescriptor(Parcel parcel, ResultStorageDescriptor resultstoragedescriptor)
    {
        this(parcel);
    }

    private void ReadFromParcel(Parcel parcel)
    {
        mType = parcel.readInt();
        mOffset = parcel.readInt();
        mLength = parcel.readInt();
    }

    public int describeContents()
    {
        return 0;
    }

    public int getLength()
    {
        return mLength;
    }

    public int getOffset()
    {
        return mOffset;
    }

    public int getType()
    {
        return mType;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mType);
        parcel.writeInt(mOffset);
        parcel.writeInt(mLength);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ResultStorageDescriptor createFromParcel(Parcel parcel)
        {
            return new ResultStorageDescriptor(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ResultStorageDescriptor[] newArray(int i)
        {
            return new ResultStorageDescriptor[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private int mLength;
    private int mOffset;
    private int mType;

}

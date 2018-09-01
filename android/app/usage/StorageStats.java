// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.usage;

import android.os.Parcel;
import android.os.Parcelable;

public final class StorageStats
    implements Parcelable
{

    public StorageStats()
    {
    }

    public StorageStats(Parcel parcel)
    {
        codeBytes = parcel.readLong();
        dataBytes = parcel.readLong();
        cacheBytes = parcel.readLong();
    }

    public int describeContents()
    {
        return 0;
    }

    public long getAppBytes()
    {
        return codeBytes;
    }

    public long getCacheBytes()
    {
        return cacheBytes;
    }

    public long getCodeBytes()
    {
        return getAppBytes();
    }

    public long getDataBytes()
    {
        return dataBytes;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(codeBytes);
        parcel.writeLong(dataBytes);
        parcel.writeLong(cacheBytes);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public StorageStats createFromParcel(Parcel parcel)
        {
            return new StorageStats(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public StorageStats[] newArray(int i)
        {
            return new StorageStats[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public long cacheBytes;
    public long codeBytes;
    public long dataBytes;

}

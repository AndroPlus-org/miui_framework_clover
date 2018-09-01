// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.usage;

import android.os.Parcel;
import android.os.Parcelable;

public final class ExternalStorageStats
    implements Parcelable
{

    public ExternalStorageStats()
    {
    }

    public ExternalStorageStats(Parcel parcel)
    {
        totalBytes = parcel.readLong();
        audioBytes = parcel.readLong();
        videoBytes = parcel.readLong();
        imageBytes = parcel.readLong();
        appBytes = parcel.readLong();
        obbBytes = parcel.readLong();
    }

    public int describeContents()
    {
        return 0;
    }

    public long getAppBytes()
    {
        return appBytes;
    }

    public long getAudioBytes()
    {
        return audioBytes;
    }

    public long getImageBytes()
    {
        return imageBytes;
    }

    public long getObbBytes()
    {
        return obbBytes;
    }

    public long getTotalBytes()
    {
        return totalBytes;
    }

    public long getVideoBytes()
    {
        return videoBytes;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(totalBytes);
        parcel.writeLong(audioBytes);
        parcel.writeLong(videoBytes);
        parcel.writeLong(imageBytes);
        parcel.writeLong(appBytes);
        parcel.writeLong(obbBytes);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ExternalStorageStats createFromParcel(Parcel parcel)
        {
            return new ExternalStorageStats(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ExternalStorageStats[] newArray(int i)
        {
            return new ExternalStorageStats[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public long appBytes;
    public long audioBytes;
    public long imageBytes;
    public long obbBytes;
    public long totalBytes;
    public long videoBytes;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.session;

import android.media.AudioAttributes;
import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableVolumeInfo
    implements Parcelable
{

    public ParcelableVolumeInfo(int i, AudioAttributes audioattributes, int j, int k, int l)
    {
        volumeType = i;
        audioAttrs = audioattributes;
        controlType = j;
        maxVolume = k;
        currentVolume = l;
    }

    public ParcelableVolumeInfo(Parcel parcel)
    {
        volumeType = parcel.readInt();
        controlType = parcel.readInt();
        maxVolume = parcel.readInt();
        currentVolume = parcel.readInt();
        audioAttrs = (AudioAttributes)AudioAttributes.CREATOR.createFromParcel(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(volumeType);
        parcel.writeInt(controlType);
        parcel.writeInt(maxVolume);
        parcel.writeInt(currentVolume);
        audioAttrs.writeToParcel(parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ParcelableVolumeInfo createFromParcel(Parcel parcel)
        {
            return new ParcelableVolumeInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ParcelableVolumeInfo[] newArray(int i)
        {
            return new ParcelableVolumeInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public AudioAttributes audioAttrs;
    public int controlType;
    public int currentVolume;
    public int maxVolume;
    public int volumeType;

}

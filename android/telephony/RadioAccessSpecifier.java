// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public final class RadioAccessSpecifier
    implements Parcelable
{

    public RadioAccessSpecifier(int i, int ai[], int ai1[])
    {
        radioAccessNetwork = i;
        bands = ai;
        channels = ai1;
    }

    private RadioAccessSpecifier(Parcel parcel)
    {
        radioAccessNetwork = parcel.readInt();
        bands = parcel.createIntArray();
        channels = parcel.createIntArray();
    }

    RadioAccessSpecifier(Parcel parcel, RadioAccessSpecifier radioaccessspecifier)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        RadioAccessSpecifier radioaccessspecifier;
        try
        {
            radioaccessspecifier = (RadioAccessSpecifier)obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        if(obj == null)
            return false;
        boolean flag1 = flag;
        if(radioAccessNetwork == radioaccessspecifier.radioAccessNetwork)
        {
            flag1 = flag;
            if(Arrays.equals(bands, radioaccessspecifier.bands))
                flag1 = Arrays.equals(channels, radioaccessspecifier.channels);
        }
        return flag1;
    }

    public int hashCode()
    {
        return radioAccessNetwork * 31 + Arrays.hashCode(bands) * 37 + Arrays.hashCode(channels) * 39;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(radioAccessNetwork);
        parcel.writeIntArray(bands);
        parcel.writeIntArray(channels);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public RadioAccessSpecifier createFromParcel(Parcel parcel)
        {
            return new RadioAccessSpecifier(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RadioAccessSpecifier[] newArray(int i)
        {
            return new RadioAccessSpecifier[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public int bands[];
    public int channels[];
    public int radioAccessNetwork;

}

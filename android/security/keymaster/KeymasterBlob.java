// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keymaster;

import android.os.Parcel;
import android.os.Parcelable;

public class KeymasterBlob
    implements Parcelable
{

    protected KeymasterBlob(Parcel parcel)
    {
        blob = parcel.createByteArray();
    }

    public KeymasterBlob(byte abyte0[])
    {
        blob = abyte0;
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeByteArray(blob);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public KeymasterBlob createFromParcel(Parcel parcel)
        {
            return new KeymasterBlob(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public KeymasterBlob[] newArray(int i)
        {
            return new KeymasterBlob[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public byte blob[];

}

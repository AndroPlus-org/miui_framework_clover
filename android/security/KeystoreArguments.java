// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security;

import android.os.Parcel;
import android.os.Parcelable;

public class KeystoreArguments
    implements Parcelable
{

    public KeystoreArguments()
    {
        args = null;
    }

    private KeystoreArguments(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    KeystoreArguments(Parcel parcel, KeystoreArguments keystorearguments)
    {
        this(parcel);
    }

    public KeystoreArguments(byte abyte0[][])
    {
        args = abyte0;
    }

    private void readFromParcel(Parcel parcel)
    {
        int i = parcel.readInt();
        args = new byte[i][];
        for(int j = 0; j < i; j++)
            args[j] = parcel.createByteArray();

    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        i = 0;
        if(args == null)
        {
            parcel.writeInt(0);
        } else
        {
            parcel.writeInt(args.length);
            byte abyte0[][] = args;
            int j = abyte0.length;
            while(i < j) 
            {
                parcel.writeByteArray(abyte0[i]);
                i++;
            }
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public KeystoreArguments createFromParcel(Parcel parcel)
        {
            return new KeystoreArguments(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public KeystoreArguments[] newArray(int i)
        {
            return new KeystoreArguments[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public byte args[][];

}

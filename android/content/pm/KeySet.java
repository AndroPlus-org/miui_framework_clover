// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.*;

public class KeySet
    implements Parcelable
{

    static KeySet _2D_wrap0(Parcel parcel)
    {
        return readFromParcel(parcel);
    }

    public KeySet(IBinder ibinder)
    {
        if(ibinder == null)
        {
            throw new NullPointerException("null value for KeySet IBinder token");
        } else
        {
            token = ibinder;
            return;
        }
    }

    private static KeySet readFromParcel(Parcel parcel)
    {
        return new KeySet(parcel.readStrongBinder());
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof KeySet)
        {
            obj = (KeySet)obj;
            if(token == ((KeySet) (obj)).token)
                flag = true;
            return flag;
        } else
        {
            return false;
        }
    }

    public IBinder getToken()
    {
        return token;
    }

    public int hashCode()
    {
        return token.hashCode();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeStrongBinder(token);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public KeySet createFromParcel(Parcel parcel)
        {
            return KeySet._2D_wrap0(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public KeySet[] newArray(int i)
        {
            return new KeySet[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private IBinder token;

}

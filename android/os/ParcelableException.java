// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import java.lang.reflect.Constructor;

// Referenced classes of package android.os:
//            Parcelable, Parcel

public final class ParcelableException extends RuntimeException
    implements Parcelable
{

    public ParcelableException(Throwable throwable)
    {
        super(throwable);
    }

    public static Throwable readFromParcel(Parcel parcel)
    {
        String s;
        s = parcel.readString();
        parcel = parcel.readString();
        Object obj;
        obj = Class.forName(s, true, android/os/Parcelable.getClassLoader());
        if(!java/lang/Throwable.isAssignableFrom(((Class) (obj))))
            break MISSING_BLOCK_LABEL_61;
        obj = (Throwable)((Class) (obj)).getConstructor(new Class[] {
            java/lang/String
        }).newInstance(new Object[] {
            parcel
        });
        return ((Throwable) (obj));
        ReflectiveOperationException reflectiveoperationexception;
        reflectiveoperationexception;
        return new RuntimeException((new StringBuilder()).append(s).append(": ").append(parcel).toString());
    }

    public static void writeToParcel(Parcel parcel, Throwable throwable)
    {
        parcel.writeString(throwable.getClass().getName());
        parcel.writeString(throwable.getMessage());
    }

    public int describeContents()
    {
        return 0;
    }

    public void maybeRethrow(Class class1)
        throws Throwable
    {
        if(class1.isAssignableFrom(getCause().getClass()))
            throw getCause();
        else
            return;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcel(parcel, getCause());
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public ParcelableException createFromParcel(Parcel parcel)
        {
            return new ParcelableException(ParcelableException.readFromParcel(parcel));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ParcelableException[] newArray(int i)
        {
            return new ParcelableException[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;

}

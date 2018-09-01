// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.MathUtils;

// Referenced classes of package android.os:
//            Parcelable, Parcel

public class ParcelableParcel
    implements Parcelable
{

    public ParcelableParcel(Parcel parcel, ClassLoader classloader)
    {
        mParcel = Parcel.obtain();
        mClassLoader = classloader;
        int i = parcel.readInt();
        if(i < 0)
        {
            throw new IllegalArgumentException("Negative size read from parcel");
        } else
        {
            int j = parcel.dataPosition();
            parcel.setDataPosition(MathUtils.addOrThrow(j, i));
            mParcel.appendFrom(parcel, j, i);
            return;
        }
    }

    public ParcelableParcel(ClassLoader classloader)
    {
        mParcel = Parcel.obtain();
        mClassLoader = classloader;
    }

    public int describeContents()
    {
        return 0;
    }

    public ClassLoader getClassLoader()
    {
        return mClassLoader;
    }

    public Parcel getParcel()
    {
        mParcel.setDataPosition(0);
        return mParcel;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mParcel.dataSize());
        parcel.appendFrom(mParcel, 0, mParcel.dataSize());
    }

    public static final Parcelable.ClassLoaderCreator CREATOR = new Parcelable.ClassLoaderCreator() {

        public ParcelableParcel createFromParcel(Parcel parcel)
        {
            return new ParcelableParcel(parcel, null);
        }

        public ParcelableParcel createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return new ParcelableParcel(parcel, classloader);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return createFromParcel(parcel, classloader);
        }

        public ParcelableParcel[] newArray(int i)
        {
            return new ParcelableParcel[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    final ClassLoader mClassLoader;
    final Parcel mParcel;

}

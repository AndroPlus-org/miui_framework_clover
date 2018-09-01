// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public final class ChangedPackages
    implements Parcelable
{

    public ChangedPackages(int i, List list)
    {
        mSequenceNumber = i;
        mPackageNames = list;
    }

    protected ChangedPackages(Parcel parcel)
    {
        mSequenceNumber = parcel.readInt();
        mPackageNames = parcel.createStringArrayList();
    }

    public int describeContents()
    {
        return 0;
    }

    public List getPackageNames()
    {
        return mPackageNames;
    }

    public int getSequenceNumber()
    {
        return mSequenceNumber;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mSequenceNumber);
        parcel.writeStringList(mPackageNames);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ChangedPackages createFromParcel(Parcel parcel)
        {
            return new ChangedPackages(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ChangedPackages[] newArray(int i)
        {
            return new ChangedPackages[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final List mPackageNames;
    private final int mSequenceNumber;

}

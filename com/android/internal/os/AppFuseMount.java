// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.*;
import com.android.internal.util.Preconditions;

public class AppFuseMount
    implements Parcelable
{

    public AppFuseMount(int i, ParcelFileDescriptor parcelfiledescriptor)
    {
        Preconditions.checkNotNull(parcelfiledescriptor);
        mountPointId = i;
        fd = parcelfiledescriptor;
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mountPointId);
        parcel.writeParcelable(fd, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AppFuseMount createFromParcel(Parcel parcel)
        {
            return new AppFuseMount(parcel.readInt(), (ParcelFileDescriptor)parcel.readParcelable(null));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AppFuseMount[] newArray(int i)
        {
            return new AppFuseMount[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final ParcelFileDescriptor fd;
    public final int mountPointId;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.framework.protobuf.nano.android;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.framework.protobuf.nano.ExtendableMessageNano;

// Referenced classes of package com.android.framework.protobuf.nano.android:
//            ParcelableMessageNanoCreator

public abstract class ParcelableExtendableMessageNano extends ExtendableMessageNano
    implements Parcelable
{

    public ParcelableExtendableMessageNano()
    {
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        ParcelableMessageNanoCreator.writeToParcel(getClass(), this, parcel);
    }
}

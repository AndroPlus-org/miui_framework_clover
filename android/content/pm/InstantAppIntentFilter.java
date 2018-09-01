// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public final class InstantAppIntentFilter
    implements Parcelable
{

    InstantAppIntentFilter(Parcel parcel)
    {
        mFilters = new ArrayList();
        mSplitName = parcel.readString();
        parcel.readList(mFilters, null);
    }

    public InstantAppIntentFilter(String s, List list)
    {
        mFilters = new ArrayList();
        if(list == null || list.size() == 0)
        {
            throw new IllegalArgumentException();
        } else
        {
            mSplitName = s;
            mFilters.addAll(list);
            return;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public List getFilters()
    {
        return mFilters;
    }

    public String getSplitName()
    {
        return mSplitName;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mSplitName);
        parcel.writeList(mFilters);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public InstantAppIntentFilter createFromParcel(Parcel parcel)
        {
            return new InstantAppIntentFilter(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public InstantAppIntentFilter[] newArray(int i)
        {
            return new InstantAppIntentFilter[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final List mFilters;
    private final String mSplitName;

}

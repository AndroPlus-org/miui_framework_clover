// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

// Referenced classes of package android.content.pm:
//            InstantAppIntentFilter

public final class EphemeralIntentFilter
    implements Parcelable
{

    EphemeralIntentFilter(InstantAppIntentFilter instantappintentfilter)
    {
        mInstantAppIntentFilter = instantappintentfilter;
    }

    EphemeralIntentFilter(Parcel parcel)
    {
        mInstantAppIntentFilter = (InstantAppIntentFilter)parcel.readParcelable(null);
    }

    public EphemeralIntentFilter(String s, List list)
    {
        mInstantAppIntentFilter = new InstantAppIntentFilter(s, list);
    }

    public int describeContents()
    {
        return 0;
    }

    public List getFilters()
    {
        return mInstantAppIntentFilter.getFilters();
    }

    InstantAppIntentFilter getInstantAppIntentFilter()
    {
        return mInstantAppIntentFilter;
    }

    public String getSplitName()
    {
        return mInstantAppIntentFilter.getSplitName();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mInstantAppIntentFilter, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public EphemeralIntentFilter createFromParcel(Parcel parcel)
        {
            return new EphemeralIntentFilter(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public EphemeralIntentFilter[] newArray(int i)
        {
            return new EphemeralIntentFilter[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final InstantAppIntentFilter mInstantAppIntentFilter;

}

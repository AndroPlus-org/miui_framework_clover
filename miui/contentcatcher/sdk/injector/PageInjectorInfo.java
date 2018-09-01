// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.contentcatcher.sdk.injector;

import android.os.Parcel;
import android.os.Parcelable;

public class PageInjectorInfo
    implements Parcelable
{

    private PageInjectorInfo(Parcel parcel)
    {
    }

    PageInjectorInfo(Parcel parcel, PageInjectorInfo pageinjectorinfo)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PageInjectorInfo createFromParcel(Parcel parcel)
        {
            return new PageInjectorInfo(parcel, null);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public PageInjectorInfo[] newArray(int i)
        {
            return new PageInjectorInfo[i];
        }

    }
;

}

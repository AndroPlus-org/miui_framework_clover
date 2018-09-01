// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.contentcatcher.sdk;

import android.os.Parcel;
import android.os.Parcelable;

public class DecorateContentParam
    implements Parcelable
{

    private DecorateContentParam(Parcel parcel)
    {
    }

    DecorateContentParam(Parcel parcel, DecorateContentParam decoratecontentparam)
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

        public DecorateContentParam createFromParcel(Parcel parcel)
        {
            return new DecorateContentParam(parcel, null);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public DecorateContentParam[] newArray(int i)
        {
            return new DecorateContentParam[i];
        }

    }
;

}

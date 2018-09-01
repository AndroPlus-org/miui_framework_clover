// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public final class TvContentRatingSystemInfo
    implements Parcelable
{

    private TvContentRatingSystemInfo(Uri uri, ApplicationInfo applicationinfo)
    {
        mXmlUri = uri;
        mApplicationInfo = applicationinfo;
    }

    private TvContentRatingSystemInfo(Parcel parcel)
    {
        mXmlUri = (Uri)parcel.readParcelable(null);
        mApplicationInfo = (ApplicationInfo)parcel.readParcelable(null);
    }

    TvContentRatingSystemInfo(Parcel parcel, TvContentRatingSystemInfo tvcontentratingsysteminfo)
    {
        this(parcel);
    }

    public static final TvContentRatingSystemInfo createTvContentRatingSystemInfo(int i, ApplicationInfo applicationinfo)
    {
        return new TvContentRatingSystemInfo((new android.net.Uri.Builder()).scheme("android.resource").authority(applicationinfo.packageName).appendPath(String.valueOf(i)).build(), applicationinfo);
    }

    public int describeContents()
    {
        return 0;
    }

    public final Uri getXmlUri()
    {
        return mXmlUri;
    }

    public final boolean isSystemDefined()
    {
        boolean flag = false;
        if((mApplicationInfo.flags & 1) != 0)
            flag = true;
        return flag;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mXmlUri, i);
        parcel.writeParcelable(mApplicationInfo, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public TvContentRatingSystemInfo createFromParcel(Parcel parcel)
        {
            return new TvContentRatingSystemInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public TvContentRatingSystemInfo[] newArray(int i)
        {
            return new TvContentRatingSystemInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final ApplicationInfo mApplicationInfo;
    private final Uri mXmlUri;

}

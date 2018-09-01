// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public final class UriPathPair
    implements Parcelable
{

    public UriPathPair(Uri uri, Uri uri1)
    {
        if(uri == null || "file".equals(uri.getScheme()) ^ true)
            throw new IllegalArgumentException("File URI must have file scheme");
        if(uri1 == null || "content".equals(uri1.getScheme()) ^ true)
        {
            throw new IllegalArgumentException("Content URI must have content scheme");
        } else
        {
            mFilePathUri = uri;
            mContentUri = uri1;
            return;
        }
    }

    private UriPathPair(Parcel parcel)
    {
        mFilePathUri = (Uri)parcel.readParcelable(android/net/Uri.getClassLoader());
        mContentUri = (Uri)parcel.readParcelable(android/net/Uri.getClassLoader());
    }

    UriPathPair(Parcel parcel, UriPathPair uripathpair)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public Uri getContentUri()
    {
        return mContentUri;
    }

    public Uri getFilePathUri()
    {
        return mFilePathUri;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mFilePathUri, i);
        parcel.writeParcelable(mContentUri, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public UriPathPair createFromParcel(Parcel parcel)
        {
            return new UriPathPair(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public UriPathPair[] newArray(int i)
        {
            return new UriPathPair[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final Uri mContentUri;
    private final Uri mFilePathUri;

}

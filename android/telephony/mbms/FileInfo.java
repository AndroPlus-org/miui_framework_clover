// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public final class FileInfo
    implements Parcelable
{

    public FileInfo(Uri uri1, String s)
    {
        uri = uri1;
        mimeType = s;
    }

    private FileInfo(Parcel parcel)
    {
        uri = (Uri)parcel.readParcelable(null);
        mimeType = parcel.readString();
    }

    FileInfo(Parcel parcel, FileInfo fileinfo)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public String getMimeType()
    {
        return mimeType;
    }

    public Uri getUri()
    {
        return uri;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(uri, i);
        parcel.writeString(mimeType);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public FileInfo createFromParcel(Parcel parcel)
        {
            return new FileInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public FileInfo[] newArray(int i)
        {
            return new FileInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final String mimeType;
    private final Uri uri;

}

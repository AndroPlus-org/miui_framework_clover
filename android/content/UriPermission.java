// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public final class UriPermission
    implements Parcelable
{

    public UriPermission(Uri uri, int i, long l)
    {
        mUri = uri;
        mModeFlags = i;
        mPersistedTime = l;
    }

    public UriPermission(Parcel parcel)
    {
        mUri = (Uri)parcel.readParcelable(null);
        mModeFlags = parcel.readInt();
        mPersistedTime = parcel.readLong();
    }

    public int describeContents()
    {
        return 0;
    }

    public long getPersistedTime()
    {
        return mPersistedTime;
    }

    public Uri getUri()
    {
        return mUri;
    }

    public boolean isReadPermission()
    {
        boolean flag = false;
        if((mModeFlags & 1) != 0)
            flag = true;
        return flag;
    }

    public boolean isWritePermission()
    {
        boolean flag = false;
        if((mModeFlags & 2) != 0)
            flag = true;
        return flag;
    }

    public String toString()
    {
        return (new StringBuilder()).append("UriPermission {uri=").append(mUri).append(", modeFlags=").append(mModeFlags).append(", persistedTime=").append(mPersistedTime).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mUri, i);
        parcel.writeInt(mModeFlags);
        parcel.writeLong(mPersistedTime);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public UriPermission createFromParcel(Parcel parcel)
        {
            return new UriPermission(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public UriPermission[] newArray(int i)
        {
            return new UriPermission[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final long INVALID_TIME = 0x8000000000000000L;
    private final int mModeFlags;
    private final long mPersistedTime;
    private final Uri mUri;

}

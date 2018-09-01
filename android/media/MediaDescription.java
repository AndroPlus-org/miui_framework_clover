// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.*;

public class MediaDescription
    implements Parcelable
{
    public static class Builder
    {

        public MediaDescription build()
        {
            return new MediaDescription(mMediaId, mTitle, mSubtitle, mDescription, mIcon, mIconUri, mExtras, mMediaUri, null);
        }

        public Builder setDescription(CharSequence charsequence)
        {
            mDescription = charsequence;
            return this;
        }

        public Builder setExtras(Bundle bundle)
        {
            mExtras = bundle;
            return this;
        }

        public Builder setIconBitmap(Bitmap bitmap)
        {
            mIcon = bitmap;
            return this;
        }

        public Builder setIconUri(Uri uri)
        {
            mIconUri = uri;
            return this;
        }

        public Builder setMediaId(String s)
        {
            mMediaId = s;
            return this;
        }

        public Builder setMediaUri(Uri uri)
        {
            mMediaUri = uri;
            return this;
        }

        public Builder setSubtitle(CharSequence charsequence)
        {
            mSubtitle = charsequence;
            return this;
        }

        public Builder setTitle(CharSequence charsequence)
        {
            mTitle = charsequence;
            return this;
        }

        private CharSequence mDescription;
        private Bundle mExtras;
        private Bitmap mIcon;
        private Uri mIconUri;
        private String mMediaId;
        private Uri mMediaUri;
        private CharSequence mSubtitle;
        private CharSequence mTitle;

        public Builder()
        {
        }
    }


    private MediaDescription(Parcel parcel)
    {
        mMediaId = parcel.readString();
        mTitle = parcel.readCharSequence();
        mSubtitle = parcel.readCharSequence();
        mDescription = parcel.readCharSequence();
        mIcon = (Bitmap)parcel.readParcelable(null);
        mIconUri = (Uri)parcel.readParcelable(null);
        mExtras = parcel.readBundle();
        mMediaUri = (Uri)parcel.readParcelable(null);
    }

    MediaDescription(Parcel parcel, MediaDescription mediadescription)
    {
        this(parcel);
    }

    private MediaDescription(String s, CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2, Bitmap bitmap, Uri uri, Bundle bundle, 
            Uri uri1)
    {
        mMediaId = s;
        mTitle = charsequence;
        mSubtitle = charsequence1;
        mDescription = charsequence2;
        mIcon = bitmap;
        mIconUri = uri;
        mExtras = bundle;
        mMediaUri = uri1;
    }

    MediaDescription(String s, CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2, Bitmap bitmap, Uri uri, Bundle bundle, 
            Uri uri1, MediaDescription mediadescription)
    {
        this(s, charsequence, charsequence1, charsequence2, bitmap, uri, bundle, uri1);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(!(obj instanceof MediaDescription))
            return false;
        obj = (MediaDescription)obj;
        if(!String.valueOf(mTitle).equals(String.valueOf(((MediaDescription) (obj)).mTitle)))
            return false;
        if(!String.valueOf(mSubtitle).equals(String.valueOf(((MediaDescription) (obj)).mSubtitle)))
            return false;
        return String.valueOf(mDescription).equals(String.valueOf(((MediaDescription) (obj)).mDescription));
    }

    public CharSequence getDescription()
    {
        return mDescription;
    }

    public Bundle getExtras()
    {
        return mExtras;
    }

    public Bitmap getIconBitmap()
    {
        return mIcon;
    }

    public Uri getIconUri()
    {
        return mIconUri;
    }

    public String getMediaId()
    {
        return mMediaId;
    }

    public Uri getMediaUri()
    {
        return mMediaUri;
    }

    public CharSequence getSubtitle()
    {
        return mSubtitle;
    }

    public CharSequence getTitle()
    {
        return mTitle;
    }

    public String toString()
    {
        return (new StringBuilder()).append(mTitle).append(", ").append(mSubtitle).append(", ").append(mDescription).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mMediaId);
        parcel.writeCharSequence(mTitle);
        parcel.writeCharSequence(mSubtitle);
        parcel.writeCharSequence(mDescription);
        parcel.writeParcelable(mIcon, i);
        parcel.writeParcelable(mIconUri, i);
        parcel.writeBundle(mExtras);
        parcel.writeParcelable(mMediaUri, i);
    }

    public static final long BT_FOLDER_TYPE_ALBUMS = 2L;
    public static final long BT_FOLDER_TYPE_ARTISTS = 3L;
    public static final long BT_FOLDER_TYPE_GENRES = 4L;
    public static final long BT_FOLDER_TYPE_MIXED = 0L;
    public static final long BT_FOLDER_TYPE_PLAYLISTS = 5L;
    public static final long BT_FOLDER_TYPE_TITLES = 1L;
    public static final long BT_FOLDER_TYPE_YEARS = 6L;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public MediaDescription createFromParcel(Parcel parcel)
        {
            return new MediaDescription(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public MediaDescription[] newArray(int i)
        {
            return new MediaDescription[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String EXTRA_BT_FOLDER_TYPE = "android.media.extra.BT_FOLDER_TYPE";
    private final CharSequence mDescription;
    private final Bundle mExtras;
    private final Bitmap mIcon;
    private final Uri mIconUri;
    private final String mMediaId;
    private final Uri mMediaUri;
    private final CharSequence mSubtitle;
    private final CharSequence mTitle;

}

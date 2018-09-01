// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.util.*;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package android.media:
//            Rating, MediaDescription

public final class MediaMetadata
    implements Parcelable
{
    public static final class Builder
    {

        private Bitmap scaleBitmap(Bitmap bitmap, int i)
        {
            float f = i;
            f = Math.min(f / (float)bitmap.getWidth(), f / (float)bitmap.getHeight());
            i = (int)((float)bitmap.getHeight() * f);
            return Bitmap.createScaledBitmap(bitmap, (int)((float)bitmap.getWidth() * f), i, true);
        }

        public MediaMetadata build()
        {
            return new MediaMetadata(mBundle, null);
        }

        public Builder putBitmap(String s, Bitmap bitmap)
        {
            if(MediaMetadata._2D_get0().containsKey(s) && ((Integer)MediaMetadata._2D_get0().get(s)).intValue() != 2)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("The ").append(s).append(" key cannot be used to put a Bitmap").toString());
            } else
            {
                mBundle.putParcelable(s, bitmap);
                return this;
            }
        }

        public Builder putLong(String s, long l)
        {
            if(MediaMetadata._2D_get0().containsKey(s) && ((Integer)MediaMetadata._2D_get0().get(s)).intValue() != 0)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("The ").append(s).append(" key cannot be used to put a long").toString());
            } else
            {
                mBundle.putLong(s, l);
                return this;
            }
        }

        public Builder putRating(String s, Rating rating)
        {
            if(MediaMetadata._2D_get0().containsKey(s) && ((Integer)MediaMetadata._2D_get0().get(s)).intValue() != 3)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("The ").append(s).append(" key cannot be used to put a Rating").toString());
            } else
            {
                mBundle.putParcelable(s, rating);
                return this;
            }
        }

        public Builder putString(String s, String s1)
        {
            if(MediaMetadata._2D_get0().containsKey(s) && ((Integer)MediaMetadata._2D_get0().get(s)).intValue() != 1)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("The ").append(s).append(" key cannot be used to put a String").toString());
            } else
            {
                mBundle.putCharSequence(s, s1);
                return this;
            }
        }

        public Builder putText(String s, CharSequence charsequence)
        {
            if(MediaMetadata._2D_get0().containsKey(s) && ((Integer)MediaMetadata._2D_get0().get(s)).intValue() != 1)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("The ").append(s).append(" key cannot be used to put a CharSequence").toString());
            } else
            {
                mBundle.putCharSequence(s, charsequence);
                return this;
            }
        }

        private final Bundle mBundle;

        public Builder()
        {
            mBundle = new Bundle();
        }

        public Builder(MediaMetadata mediametadata)
        {
            mBundle = new Bundle(MediaMetadata._2D_get1(mediametadata));
        }

        public Builder(MediaMetadata mediametadata, int i)
        {
            this(mediametadata);
            Iterator iterator = mBundle.keySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                mediametadata = (String)iterator.next();
                Object obj = mBundle.get(mediametadata);
                if(obj != null && (obj instanceof Bitmap))
                {
                    obj = (Bitmap)obj;
                    if(((Bitmap) (obj)).getHeight() > i || ((Bitmap) (obj)).getWidth() > i)
                        putBitmap(mediametadata, scaleBitmap(((Bitmap) (obj)), i));
                }
            } while(true);
        }
    }


    static ArrayMap _2D_get0()
    {
        return METADATA_KEYS_TYPE;
    }

    static Bundle _2D_get1(MediaMetadata mediametadata)
    {
        return mediametadata.mBundle;
    }

    private MediaMetadata(Bundle bundle)
    {
        mBundle = new Bundle(bundle);
    }

    MediaMetadata(Bundle bundle, MediaMetadata mediametadata)
    {
        this(bundle);
    }

    private MediaMetadata(Parcel parcel)
    {
        mBundle = Bundle.setDefusable(parcel.readBundle(), true);
    }

    MediaMetadata(Parcel parcel, MediaMetadata mediametadata)
    {
        this(parcel);
    }

    public static String getKeyFromMetadataEditorKey(int i)
    {
        return (String)EDITOR_KEY_MAPPING.get(i, null);
    }

    public boolean containsKey(String s)
    {
        return mBundle.containsKey(s);
    }

    public int describeContents()
    {
        return 0;
    }

    public Bitmap getBitmap(String s)
    {
        Object obj = null;
        try
        {
            s = (Bitmap)mBundle.getParcelable(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("MediaMetadata", "Failed to retrieve a key as Bitmap.", s);
            s = obj;
        }
        return s;
    }

    public MediaDescription getDescription()
    {
        int i;
        if(mDescription != null)
            return mDescription;
        String s = getString("android.media.metadata.MEDIA_ID");
        CharSequence acharsequence[] = new CharSequence[3];
        Object obj = null;
        Uri uri = null;
        Object obj1 = getText("android.media.metadata.DISPLAY_TITLE");
        Object obj2;
        if(!TextUtils.isEmpty(((CharSequence) (obj1))))
        {
            acharsequence[0] = ((CharSequence) (obj1));
            acharsequence[1] = getText("android.media.metadata.DISPLAY_SUBTITLE");
            acharsequence[2] = getText("android.media.metadata.DISPLAY_DESCRIPTION");
        } else
        {
            int j = 0;
            i = 0;
            while(j < acharsequence.length && i < PREFERRED_DESCRIPTION_ORDER.length) 
            {
                CharSequence charsequence = getText(PREFERRED_DESCRIPTION_ORDER[i]);
                int k = j;
                if(!TextUtils.isEmpty(charsequence))
                {
                    acharsequence[j] = charsequence;
                    k = j + 1;
                }
                i++;
                j = k;
            }
        }
        i = 0;
_L4:
        obj1 = obj;
        if(i >= PREFERRED_BITMAP_ORDER.length) goto _L2; else goto _L1
_L1:
        obj1 = getBitmap(PREFERRED_BITMAP_ORDER[i]);
        if(obj1 == null) goto _L3; else goto _L2
_L2:
        i = 0;
_L5:
        obj = uri;
        if(i < PREFERRED_URI_ORDER.length)
        {
            obj = getString(PREFERRED_URI_ORDER[i]);
            if(TextUtils.isEmpty(((CharSequence) (obj))))
                break MISSING_BLOCK_LABEL_361;
            obj = Uri.parse(((String) (obj)));
        }
        uri = null;
        obj2 = getString("android.media.metadata.MEDIA_URI");
        if(!TextUtils.isEmpty(((CharSequence) (obj2))))
            uri = Uri.parse(((String) (obj2)));
        obj2 = new MediaDescription.Builder();
        ((MediaDescription.Builder) (obj2)).setMediaId(s);
        ((MediaDescription.Builder) (obj2)).setTitle(acharsequence[0]);
        ((MediaDescription.Builder) (obj2)).setSubtitle(acharsequence[1]);
        ((MediaDescription.Builder) (obj2)).setDescription(acharsequence[2]);
        ((MediaDescription.Builder) (obj2)).setIconBitmap(((Bitmap) (obj1)));
        ((MediaDescription.Builder) (obj2)).setIconUri(((Uri) (obj)));
        ((MediaDescription.Builder) (obj2)).setMediaUri(uri);
        if(mBundle.containsKey("android.media.metadata.BT_FOLDER_TYPE"))
        {
            obj1 = new Bundle();
            ((Bundle) (obj1)).putLong("android.media.extra.BT_FOLDER_TYPE", getLong("android.media.metadata.BT_FOLDER_TYPE"));
            ((MediaDescription.Builder) (obj2)).setExtras(((Bundle) (obj1)));
        }
        mDescription = ((MediaDescription.Builder) (obj2)).build();
        return mDescription;
_L3:
        i++;
          goto _L4
        i++;
          goto _L5
    }

    public long getLong(String s)
    {
        return mBundle.getLong(s, 0L);
    }

    public Rating getRating(String s)
    {
        Object obj = null;
        try
        {
            s = (Rating)mBundle.getParcelable(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("MediaMetadata", "Failed to retrieve a key as Rating.", s);
            s = obj;
        }
        return s;
    }

    public String getString(String s)
    {
        s = getText(s);
        if(s != null)
            return s.toString();
        else
            return null;
    }

    public CharSequence getText(String s)
    {
        return mBundle.getCharSequence(s);
    }

    public Set keySet()
    {
        return mBundle.keySet();
    }

    public int size()
    {
        return mBundle.size();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeBundle(mBundle);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public MediaMetadata createFromParcel(Parcel parcel)
        {
            return new MediaMetadata(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public MediaMetadata[] newArray(int i)
        {
            return new MediaMetadata[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final SparseArray EDITOR_KEY_MAPPING;
    private static final ArrayMap METADATA_KEYS_TYPE;
    public static final String METADATA_KEY_ALBUM = "android.media.metadata.ALBUM";
    public static final String METADATA_KEY_ALBUM_ART = "android.media.metadata.ALBUM_ART";
    public static final String METADATA_KEY_ALBUM_ARTIST = "android.media.metadata.ALBUM_ARTIST";
    public static final String METADATA_KEY_ALBUM_ART_URI = "android.media.metadata.ALBUM_ART_URI";
    public static final String METADATA_KEY_ART = "android.media.metadata.ART";
    public static final String METADATA_KEY_ARTIST = "android.media.metadata.ARTIST";
    public static final String METADATA_KEY_ART_URI = "android.media.metadata.ART_URI";
    public static final String METADATA_KEY_AUTHOR = "android.media.metadata.AUTHOR";
    public static final String METADATA_KEY_BT_FOLDER_TYPE = "android.media.metadata.BT_FOLDER_TYPE";
    public static final String METADATA_KEY_COMPILATION = "android.media.metadata.COMPILATION";
    public static final String METADATA_KEY_COMPOSER = "android.media.metadata.COMPOSER";
    public static final String METADATA_KEY_DATE = "android.media.metadata.DATE";
    public static final String METADATA_KEY_DISC_NUMBER = "android.media.metadata.DISC_NUMBER";
    public static final String METADATA_KEY_DISPLAY_DESCRIPTION = "android.media.metadata.DISPLAY_DESCRIPTION";
    public static final String METADATA_KEY_DISPLAY_ICON = "android.media.metadata.DISPLAY_ICON";
    public static final String METADATA_KEY_DISPLAY_ICON_URI = "android.media.metadata.DISPLAY_ICON_URI";
    public static final String METADATA_KEY_DISPLAY_SUBTITLE = "android.media.metadata.DISPLAY_SUBTITLE";
    public static final String METADATA_KEY_DISPLAY_TITLE = "android.media.metadata.DISPLAY_TITLE";
    public static final String METADATA_KEY_DURATION = "android.media.metadata.DURATION";
    public static final String METADATA_KEY_GENRE = "android.media.metadata.GENRE";
    public static final String METADATA_KEY_LYRIC = "android.media.metadata.LYRIC";
    public static final String METADATA_KEY_MEDIA_ID = "android.media.metadata.MEDIA_ID";
    public static final String METADATA_KEY_MEDIA_URI = "android.media.metadata.MEDIA_URI";
    public static final String METADATA_KEY_NUM_TRACKS = "android.media.metadata.NUM_TRACKS";
    public static final String METADATA_KEY_RATING = "android.media.metadata.RATING";
    public static final String METADATA_KEY_TITLE = "android.media.metadata.TITLE";
    public static final String METADATA_KEY_TRACK_NUMBER = "android.media.metadata.TRACK_NUMBER";
    public static final String METADATA_KEY_USER_RATING = "android.media.metadata.USER_RATING";
    public static final String METADATA_KEY_WRITER = "android.media.metadata.WRITER";
    public static final String METADATA_KEY_YEAR = "android.media.metadata.YEAR";
    private static final int METADATA_TYPE_BITMAP = 2;
    private static final int METADATA_TYPE_INVALID = -1;
    private static final int METADATA_TYPE_LONG = 0;
    private static final int METADATA_TYPE_RATING = 3;
    private static final int METADATA_TYPE_TEXT = 1;
    private static final String PREFERRED_BITMAP_ORDER[] = {
        "android.media.metadata.DISPLAY_ICON", "android.media.metadata.ART", "android.media.metadata.ALBUM_ART"
    };
    private static final String PREFERRED_DESCRIPTION_ORDER[] = {
        "android.media.metadata.TITLE", "android.media.metadata.ARTIST", "android.media.metadata.ALBUM", "android.media.metadata.ALBUM_ARTIST", "android.media.metadata.WRITER", "android.media.metadata.AUTHOR", "android.media.metadata.COMPOSER"
    };
    private static final String PREFERRED_URI_ORDER[] = {
        "android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.ART_URI", "android.media.metadata.ALBUM_ART_URI"
    };
    private static final String TAG = "MediaMetadata";
    private final Bundle mBundle;
    private MediaDescription mDescription;

    static 
    {
        METADATA_KEYS_TYPE = new ArrayMap();
        METADATA_KEYS_TYPE.put("android.media.metadata.TITLE", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.ARTIST", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.DURATION", Integer.valueOf(0));
        METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.AUTHOR", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.WRITER", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.COMPOSER", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.COMPILATION", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.DATE", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.YEAR", Integer.valueOf(0));
        METADATA_KEYS_TYPE.put("android.media.metadata.GENRE", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.TRACK_NUMBER", Integer.valueOf(0));
        METADATA_KEYS_TYPE.put("android.media.metadata.NUM_TRACKS", Integer.valueOf(0));
        METADATA_KEYS_TYPE.put("android.media.metadata.DISC_NUMBER", Integer.valueOf(0));
        METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM_ARTIST", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.ART", Integer.valueOf(2));
        METADATA_KEYS_TYPE.put("android.media.metadata.ART_URI", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM_ART", Integer.valueOf(2));
        METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM_ART_URI", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.USER_RATING", Integer.valueOf(3));
        METADATA_KEYS_TYPE.put("android.media.metadata.RATING", Integer.valueOf(3));
        METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_TITLE", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_SUBTITLE", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_DESCRIPTION", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_ICON", Integer.valueOf(2));
        METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_ICON_URI", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.BT_FOLDER_TYPE", Integer.valueOf(0));
        METADATA_KEYS_TYPE.put("android.media.metadata.MEDIA_ID", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.MEDIA_URI", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.LYRIC", Integer.valueOf(1));
        EDITOR_KEY_MAPPING = new SparseArray();
        EDITOR_KEY_MAPPING.put(100, "android.media.metadata.ART");
        EDITOR_KEY_MAPPING.put(101, "android.media.metadata.RATING");
        EDITOR_KEY_MAPPING.put(0x10000001, "android.media.metadata.USER_RATING");
        EDITOR_KEY_MAPPING.put(1, "android.media.metadata.ALBUM");
        EDITOR_KEY_MAPPING.put(13, "android.media.metadata.ALBUM_ARTIST");
        EDITOR_KEY_MAPPING.put(2, "android.media.metadata.ARTIST");
        EDITOR_KEY_MAPPING.put(3, "android.media.metadata.AUTHOR");
        EDITOR_KEY_MAPPING.put(0, "android.media.metadata.TRACK_NUMBER");
        EDITOR_KEY_MAPPING.put(4, "android.media.metadata.COMPOSER");
        EDITOR_KEY_MAPPING.put(15, "android.media.metadata.COMPILATION");
        EDITOR_KEY_MAPPING.put(5, "android.media.metadata.DATE");
        EDITOR_KEY_MAPPING.put(14, "android.media.metadata.DISC_NUMBER");
        EDITOR_KEY_MAPPING.put(9, "android.media.metadata.DURATION");
        EDITOR_KEY_MAPPING.put(6, "android.media.metadata.GENRE");
        EDITOR_KEY_MAPPING.put(10, "android.media.metadata.NUM_TRACKS");
        EDITOR_KEY_MAPPING.put(7, "android.media.metadata.TITLE");
        EDITOR_KEY_MAPPING.put(11, "android.media.metadata.WRITER");
        EDITOR_KEY_MAPPING.put(8, "android.media.metadata.YEAR");
        EDITOR_KEY_MAPPING.put(1000, "android.media.metadata.LYRIC");
    }
}

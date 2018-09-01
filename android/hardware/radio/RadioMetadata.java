// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.radio;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.*;
import android.util.*;
import java.util.Iterator;
import java.util.Set;

public final class RadioMetadata
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

        public RadioMetadata build()
        {
            return new RadioMetadata(mBundle, null);
        }

        public Builder putBitmap(String s, Bitmap bitmap)
        {
            if(!RadioMetadata._2D_get0().containsKey(s) || ((Integer)RadioMetadata._2D_get0().get(s)).intValue() != 2)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("The ").append(s).append(" key cannot be used to put a Bitmap").toString());
            } else
            {
                mBundle.putParcelable(s, bitmap);
                return this;
            }
        }

        public Builder putClock(String s, long l, int i)
        {
            if(!RadioMetadata._2D_get0().containsKey(s) || ((Integer)RadioMetadata._2D_get0().get(s)).intValue() != 3)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("The ").append(s).append(" key cannot be used to put a RadioMetadata.Clock.").toString());
            } else
            {
                mBundle.putParcelable(s, new Clock(l, i));
                return this;
            }
        }

        public Builder putInt(String s, int i)
        {
            RadioMetadata._2D_wrap0(mBundle, s, i);
            return this;
        }

        public Builder putString(String s, String s1)
        {
            if(!RadioMetadata._2D_get0().containsKey(s) || ((Integer)RadioMetadata._2D_get0().get(s)).intValue() != 1)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("The ").append(s).append(" key cannot be used to put a String").toString());
            } else
            {
                mBundle.putString(s, s1);
                return this;
            }
        }

        private final Bundle mBundle;

        public Builder()
        {
            mBundle = new Bundle();
        }

        public Builder(RadioMetadata radiometadata)
        {
            mBundle = new Bundle(RadioMetadata._2D_get1(radiometadata));
        }

        public Builder(RadioMetadata radiometadata, int i)
        {
            this(radiometadata);
            Iterator iterator = mBundle.keySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                radiometadata = (String)iterator.next();
                Object obj = mBundle.get(radiometadata);
                if(obj != null && (obj instanceof Bitmap))
                {
                    obj = (Bitmap)obj;
                    if(((Bitmap) (obj)).getHeight() > i || ((Bitmap) (obj)).getWidth() > i)
                        putBitmap(radiometadata, scaleBitmap(((Bitmap) (obj)), i));
                }
            } while(true);
        }
    }

    public static final class Clock
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public int getTimezoneOffsetMinutes()
        {
            return mTimezoneOffsetMinutes;
        }

        public long getUtcEpochSeconds()
        {
            return mUtcEpochSeconds;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeLong(mUtcEpochSeconds);
            parcel.writeInt(mTimezoneOffsetMinutes);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Clock createFromParcel(Parcel parcel)
            {
                return new Clock(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Clock[] newArray(int i)
            {
                return new Clock[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final int mTimezoneOffsetMinutes;
        private final long mUtcEpochSeconds;


        public Clock(long l, int i)
        {
            mUtcEpochSeconds = l;
            mTimezoneOffsetMinutes = i;
        }

        private Clock(Parcel parcel)
        {
            mUtcEpochSeconds = parcel.readLong();
            mTimezoneOffsetMinutes = parcel.readInt();
        }

        Clock(Parcel parcel, Clock clock)
        {
            this(parcel);
        }
    }


    static ArrayMap _2D_get0()
    {
        return METADATA_KEYS_TYPE;
    }

    static Bundle _2D_get1(RadioMetadata radiometadata)
    {
        return radiometadata.mBundle;
    }

    static void _2D_wrap0(Bundle bundle, String s, int i)
    {
        putInt(bundle, s, i);
    }

    RadioMetadata()
    {
        mBundle = new Bundle();
    }

    private RadioMetadata(Bundle bundle)
    {
        mBundle = new Bundle(bundle);
    }

    RadioMetadata(Bundle bundle, RadioMetadata radiometadata)
    {
        this(bundle);
    }

    private RadioMetadata(Parcel parcel)
    {
        mBundle = parcel.readBundle();
    }

    RadioMetadata(Parcel parcel, RadioMetadata radiometadata)
    {
        this(parcel);
    }

    public static String getKeyFromNativeKey(int i)
    {
        return (String)NATIVE_KEY_MAPPING.get(i, null);
    }

    private static void putInt(Bundle bundle, String s, int i)
    {
        int j = ((Integer)METADATA_KEYS_TYPE.getOrDefault(s, Integer.valueOf(-1))).intValue();
        if(j != 0 && j != 2)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("The ").append(s).append(" key cannot be used to put an int").toString());
        } else
        {
            bundle.putInt(s, i);
            return;
        }
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
            Log.w("BroadcastRadio.metadata", "Failed to retrieve a key as Bitmap.", s);
            s = obj;
        }
        return s;
    }

    public int getBitmapId(String s)
    {
        if(!"android.hardware.radio.metadata.ICON".equals(s) && "android.hardware.radio.metadata.ART".equals(s) ^ true)
            return 0;
        else
            return getInt(s);
    }

    public Clock getClock(String s)
    {
        Object obj = null;
        try
        {
            s = (Clock)mBundle.getParcelable(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("BroadcastRadio.metadata", "Failed to retrieve a key as Clock.", s);
            s = obj;
        }
        return s;
    }

    public int getInt(String s)
    {
        return mBundle.getInt(s, 0);
    }

    public String getString(String s)
    {
        return mBundle.getString(s);
    }

    public Set keySet()
    {
        return mBundle.keySet();
    }

    int putBitmapFromNative(int i, byte abyte0[])
    {
        String s;
        s = getKeyFromNativeKey(i);
        if(!METADATA_KEYS_TYPE.containsKey(s) || ((Integer)METADATA_KEYS_TYPE.get(s)).intValue() != 2)
            return -1;
        abyte0 = BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length);
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_58;
        mBundle.putParcelable(s, abyte0);
        return 0;
        abyte0;
        return -1;
    }

    int putClockFromNative(int i, long l, int j)
    {
        String s = getKeyFromNativeKey(i);
        if(!METADATA_KEYS_TYPE.containsKey(s) || ((Integer)METADATA_KEYS_TYPE.get(s)).intValue() != 3)
        {
            return -1;
        } else
        {
            mBundle.putParcelable(s, new Clock(l, j));
            return 0;
        }
    }

    int putIntFromNative(int i, int j)
    {
        String s = getKeyFromNativeKey(i);
        try
        {
            putInt(mBundle, s, j);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            return -1;
        }
        return 0;
    }

    int putStringFromNative(int i, String s)
    {
        String s1 = getKeyFromNativeKey(i);
        if(!METADATA_KEYS_TYPE.containsKey(s1) || ((Integer)METADATA_KEYS_TYPE.get(s1)).intValue() != 1)
        {
            return -1;
        } else
        {
            mBundle.putString(s1, s);
            return 0;
        }
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

        public RadioMetadata createFromParcel(Parcel parcel)
        {
            return new RadioMetadata(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RadioMetadata[] newArray(int i)
        {
            return new RadioMetadata[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final ArrayMap METADATA_KEYS_TYPE;
    public static final String METADATA_KEY_ALBUM = "android.hardware.radio.metadata.ALBUM";
    public static final String METADATA_KEY_ART = "android.hardware.radio.metadata.ART";
    public static final String METADATA_KEY_ARTIST = "android.hardware.radio.metadata.ARTIST";
    public static final String METADATA_KEY_CLOCK = "android.hardware.radio.metadata.CLOCK";
    public static final String METADATA_KEY_GENRE = "android.hardware.radio.metadata.GENRE";
    public static final String METADATA_KEY_ICON = "android.hardware.radio.metadata.ICON";
    public static final String METADATA_KEY_RBDS_PTY = "android.hardware.radio.metadata.RBDS_PTY";
    public static final String METADATA_KEY_RDS_PI = "android.hardware.radio.metadata.RDS_PI";
    public static final String METADATA_KEY_RDS_PS = "android.hardware.radio.metadata.RDS_PS";
    public static final String METADATA_KEY_RDS_PTY = "android.hardware.radio.metadata.RDS_PTY";
    public static final String METADATA_KEY_RDS_RT = "android.hardware.radio.metadata.RDS_RT";
    public static final String METADATA_KEY_TITLE = "android.hardware.radio.metadata.TITLE";
    private static final int METADATA_TYPE_BITMAP = 2;
    private static final int METADATA_TYPE_CLOCK = 3;
    private static final int METADATA_TYPE_INT = 0;
    private static final int METADATA_TYPE_INVALID = -1;
    private static final int METADATA_TYPE_TEXT = 1;
    private static final int NATIVE_KEY_ALBUM = 7;
    private static final int NATIVE_KEY_ART = 10;
    private static final int NATIVE_KEY_ARTIST = 6;
    private static final int NATIVE_KEY_CLOCK = 11;
    private static final int NATIVE_KEY_GENRE = 8;
    private static final int NATIVE_KEY_ICON = 9;
    private static final int NATIVE_KEY_INVALID = -1;
    private static final SparseArray NATIVE_KEY_MAPPING;
    private static final int NATIVE_KEY_RBDS_PTY = 3;
    private static final int NATIVE_KEY_RDS_PI = 0;
    private static final int NATIVE_KEY_RDS_PS = 1;
    private static final int NATIVE_KEY_RDS_PTY = 2;
    private static final int NATIVE_KEY_RDS_RT = 4;
    private static final int NATIVE_KEY_TITLE = 5;
    private static final String TAG = "BroadcastRadio.metadata";
    private final Bundle mBundle;

    static 
    {
        METADATA_KEYS_TYPE = new ArrayMap();
        METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.RDS_PI", Integer.valueOf(0));
        METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.RDS_PS", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.RDS_PTY", Integer.valueOf(0));
        METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.RBDS_PTY", Integer.valueOf(0));
        METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.RDS_RT", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.TITLE", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.ARTIST", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.ALBUM", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.GENRE", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.ICON", Integer.valueOf(2));
        METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.ART", Integer.valueOf(2));
        METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.CLOCK", Integer.valueOf(3));
        NATIVE_KEY_MAPPING = new SparseArray();
        NATIVE_KEY_MAPPING.put(0, "android.hardware.radio.metadata.RDS_PI");
        NATIVE_KEY_MAPPING.put(1, "android.hardware.radio.metadata.RDS_PS");
        NATIVE_KEY_MAPPING.put(2, "android.hardware.radio.metadata.RDS_PTY");
        NATIVE_KEY_MAPPING.put(3, "android.hardware.radio.metadata.RBDS_PTY");
        NATIVE_KEY_MAPPING.put(4, "android.hardware.radio.metadata.RDS_RT");
        NATIVE_KEY_MAPPING.put(5, "android.hardware.radio.metadata.TITLE");
        NATIVE_KEY_MAPPING.put(6, "android.hardware.radio.metadata.ARTIST");
        NATIVE_KEY_MAPPING.put(7, "android.hardware.radio.metadata.ALBUM");
        NATIVE_KEY_MAPPING.put(8, "android.hardware.radio.metadata.GENRE");
        NATIVE_KEY_MAPPING.put(9, "android.hardware.radio.metadata.ICON");
        NATIVE_KEY_MAPPING.put(10, "android.hardware.radio.metadata.ART");
        NATIVE_KEY_MAPPING.put(11, "android.hardware.radio.metadata.CLOCK");
    }
}

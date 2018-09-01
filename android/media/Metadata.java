// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.Parcel;
import android.util.Log;
import android.util.MathUtils;
import java.util.*;

public class Metadata
{

    public Metadata()
    {
    }

    private boolean checkMetadataId(int i)
    {
        if(i <= 0 || 31 < i && i < 8192)
        {
            Log.e("media.Metadata", (new StringBuilder()).append("Invalid metadata ID ").append(i).toString());
            return false;
        } else
        {
            return true;
        }
    }

    private void checkType(int i, int j)
    {
        i = ((Integer)mKeyToPosMap.get(Integer.valueOf(i))).intValue();
        mParcel.setDataPosition(i);
        i = mParcel.readInt();
        if(i != j)
            throw new IllegalStateException((new StringBuilder()).append("Wrong type ").append(j).append(" but got ").append(i).toString());
        else
            return;
    }

    public static int firstCustomId()
    {
        return 8192;
    }

    public static int lastSytemId()
    {
        return 31;
    }

    public static int lastType()
    {
        return 7;
    }

    private boolean scanAllRecords(Parcel parcel, int i)
    {
        int j;
        boolean flag;
        int k;
        j = 0;
        flag = false;
        mKeyToPosMap.clear();
        k = i;
_L1:
        int l;
        i = ((flag) ? 1 : 0);
        if(k > 12)
        {
            l = parcel.dataPosition();
            i = parcel.readInt();
            if(i <= 12)
            {
                Log.e("media.Metadata", "Record is too short");
                i = 1;
            } else
            {
                int i1 = parcel.readInt();
                if(!checkMetadataId(i1))
                    i = 1;
                else
                if(mKeyToPosMap.containsKey(Integer.valueOf(i1)))
                {
                    Log.e("media.Metadata", "Duplicate metadata ID found");
                    i = 1;
                } else
                {
label0:
                    {
                        mKeyToPosMap.put(Integer.valueOf(i1), Integer.valueOf(parcel.dataPosition()));
                        i1 = parcel.readInt();
                        if(i1 > 0 && i1 <= 7)
                            break label0;
                        Log.e("media.Metadata", (new StringBuilder()).append("Invalid metadata type ").append(i1).toString());
                        i = 1;
                    }
                }
            }
        }
_L2:
        if(k != 0 || i != 0)
        {
            Log.e("media.Metadata", (new StringBuilder()).append("Ran out of data or error on record ").append(j).toString());
            mKeyToPosMap.clear();
            return false;
        } else
        {
            return true;
        }
        parcel.setDataPosition(MathUtils.addOrThrow(l, i));
        k -= i;
        j++;
          goto _L1
        parcel;
        Log.e("media.Metadata", (new StringBuilder()).append("Invalid size: ").append(parcel.getMessage()).toString());
        i = 1;
          goto _L2
    }

    public boolean getBoolean(int i)
    {
        boolean flag = true;
        checkType(i, 3);
        if(mParcel.readInt() != 1)
            flag = false;
        return flag;
    }

    public byte[] getByteArray(int i)
    {
        checkType(i, 7);
        return mParcel.createByteArray();
    }

    public Date getDate(int i)
    {
        checkType(i, 6);
        long l = mParcel.readLong();
        Object obj = mParcel.readString();
        if(((String) (obj)).length() == 0)
        {
            return new Date(l);
        } else
        {
            obj = Calendar.getInstance(TimeZone.getTimeZone(((String) (obj))));
            ((Calendar) (obj)).setTimeInMillis(l);
            return ((Calendar) (obj)).getTime();
        }
    }

    public double getDouble(int i)
    {
        checkType(i, 5);
        return mParcel.readDouble();
    }

    public int getInt(int i)
    {
        checkType(i, 2);
        return mParcel.readInt();
    }

    public long getLong(int i)
    {
        checkType(i, 4);
        return mParcel.readLong();
    }

    public String getString(int i)
    {
        checkType(i, 1);
        return mParcel.readString();
    }

    public boolean has(int i)
    {
        if(!checkMetadataId(i))
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid key: ").append(i).toString());
        else
            return mKeyToPosMap.containsKey(Integer.valueOf(i));
    }

    public Set keySet()
    {
        return mKeyToPosMap.keySet();
    }

    public boolean parse(Parcel parcel)
    {
        if(parcel.dataAvail() < 8)
        {
            Log.e("media.Metadata", (new StringBuilder()).append("Not enough data ").append(parcel.dataAvail()).toString());
            return false;
        }
        int i = parcel.dataPosition();
        int j = parcel.readInt();
        if(parcel.dataAvail() + 4 < j || j < 8)
        {
            Log.e("media.Metadata", (new StringBuilder()).append("Bad size ").append(j).append(" avail ").append(parcel.dataAvail()).append(" position ").append(i).toString());
            parcel.setDataPosition(i);
            return false;
        }
        int k = parcel.readInt();
        if(k != 0x4d455441)
        {
            Log.e("media.Metadata", (new StringBuilder()).append("Marker missing ").append(Integer.toHexString(k)).toString());
            parcel.setDataPosition(i);
            return false;
        }
        if(!scanAllRecords(parcel, j - 8))
        {
            parcel.setDataPosition(i);
            return false;
        } else
        {
            mParcel = parcel;
            return true;
        }
    }

    public static final int ALBUM = 8;
    public static final int ALBUM_ART = 18;
    public static final int ANY = 0;
    public static final int ARTIST = 9;
    public static final int AUDIO_BIT_RATE = 21;
    public static final int AUDIO_CODEC = 26;
    public static final int AUDIO_SAMPLE_RATE = 23;
    public static final int AUTHOR = 10;
    public static final int BIT_RATE = 20;
    public static final int BOOLEAN_VAL = 3;
    public static final int BYTE_ARRAY_VAL = 7;
    public static final int CD_TRACK_MAX = 16;
    public static final int CD_TRACK_NUM = 15;
    public static final int COMMENT = 6;
    public static final int COMPOSER = 11;
    public static final int COPYRIGHT = 7;
    public static final int DATE = 13;
    public static final int DATE_VAL = 6;
    public static final int DOUBLE_VAL = 5;
    public static final int DRM_CRIPPLED = 31;
    public static final int DURATION = 14;
    private static final int FIRST_CUSTOM = 8192;
    public static final int GENRE = 12;
    public static final int INTEGER_VAL = 2;
    private static final int LAST_SYSTEM = 31;
    private static final int LAST_TYPE = 7;
    public static final int LONG_VAL = 4;
    public static final Set MATCH_ALL = Collections.singleton(Integer.valueOf(0));
    public static final Set MATCH_NONE;
    public static final int MIME_TYPE = 25;
    public static final int NUM_TRACKS = 30;
    public static final int PAUSE_AVAILABLE = 1;
    public static final int RATING = 17;
    public static final int SEEK_AVAILABLE = 4;
    public static final int SEEK_BACKWARD_AVAILABLE = 2;
    public static final int SEEK_FORWARD_AVAILABLE = 3;
    public static final int STRING_VAL = 1;
    private static final String TAG = "media.Metadata";
    public static final int TITLE = 5;
    public static final int VIDEO_BIT_RATE = 22;
    public static final int VIDEO_CODEC = 27;
    public static final int VIDEO_FRAME = 19;
    public static final int VIDEO_FRAME_RATE = 24;
    public static final int VIDEO_HEIGHT = 28;
    public static final int VIDEO_WIDTH = 29;
    private static final int kInt32Size = 4;
    private static final int kMetaHeaderSize = 8;
    private static final int kMetaMarker = 0x4d455441;
    private static final int kRecordHeaderSize = 12;
    private final HashMap mKeyToPosMap = new HashMap();
    private Parcel mParcel;

    static 
    {
        MATCH_NONE = Collections.EMPTY_SET;
    }
}

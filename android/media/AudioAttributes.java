// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseIntArray;
import java.util.*;

public final class AudioAttributes
    implements Parcelable
{
    public static class Builder
    {

        public Builder addBundle(Bundle bundle)
        {
            if(bundle == null)
                throw new IllegalArgumentException("Illegal null bundle");
            if(mBundle == null)
                mBundle = new Bundle(bundle);
            else
                mBundle.putAll(bundle);
            return this;
        }

        public Builder addTag(String s)
        {
            mTags.add(s);
            return this;
        }

        public AudioAttributes build()
        {
            AudioAttributes audioattributes = new AudioAttributes(null);
            AudioAttributes._2D_set1(audioattributes, mContentType);
            AudioAttributes._2D_set6(audioattributes, mUsage);
            AudioAttributes._2D_set4(audioattributes, mSource);
            AudioAttributes._2D_set2(audioattributes, mFlags);
            AudioAttributes._2D_set5(audioattributes, (HashSet)mTags.clone());
            AudioAttributes._2D_set3(audioattributes, TextUtils.join(";", mTags));
            if(mBundle != null)
                AudioAttributes._2D_set0(audioattributes, new Bundle(mBundle));
            return audioattributes;
        }

        public Builder replaceFlags(int i)
        {
            mFlags = i & 0x3ff;
            return this;
        }

        public Builder setCapturePreset(int i)
        {
            i;
            JVM INSTR tableswitch 0 9: default 56
        //                       0 88
        //                       1 88
        //                       2 56
        //                       3 88
        //                       4 88
        //                       5 88
        //                       6 88
        //                       7 88
        //                       8 56
        //                       9 88;
               goto _L1 _L2 _L2 _L1 _L2 _L2 _L2 _L2 _L2 _L1 _L2
_L1:
            Log.e("AudioAttributes", (new StringBuilder()).append("Invalid capture preset ").append(i).append(" for AudioAttributes").toString());
_L4:
            return this;
_L2:
            mSource = i;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public Builder setContentType(int i)
        {
            i;
            JVM INSTR tableswitch 0 4: default 36
        //                       0 43
        //                       1 43
        //                       2 43
        //                       3 43
        //                       4 43;
               goto _L1 _L2 _L2 _L2 _L2 _L2
_L1:
            mUsage = 0;
_L4:
            return this;
_L2:
            mContentType = i;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public Builder setFlags(int i)
        {
            mFlags = mFlags | i & 0x3ff;
            return this;
        }

        public Builder setInternalCapturePreset(int i)
        {
            if(i == 1999 || i == 8 || i == 1998)
                mSource = i;
            else
                setCapturePreset(i);
            return this;
        }

        public Builder setInternalLegacyStreamType(int i)
        {
            i;
            JVM INSTR tableswitch 0 10: default 60
        //                       0 100
        //                       1 118
        //                       2 126
        //                       3 134
        //                       4 142
        //                       5 150
        //                       6 158
        //                       7 108
        //                       8 176
        //                       9 184
        //                       10 192;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L1:
            Log.e("AudioAttributes", (new StringBuilder()).append("Invalid stream type ").append(i).append(" for AudioAttributes").toString());
_L14:
            mUsage = AudioAttributes._2D_wrap0(i);
            return this;
_L2:
            mContentType = 1;
            continue; /* Loop/switch isn't completed */
_L9:
            mFlags = mFlags | 1;
_L3:
            mContentType = 4;
            continue; /* Loop/switch isn't completed */
_L4:
            mContentType = 4;
            continue; /* Loop/switch isn't completed */
_L5:
            mContentType = 2;
            continue; /* Loop/switch isn't completed */
_L6:
            mContentType = 4;
            continue; /* Loop/switch isn't completed */
_L7:
            mContentType = 4;
            continue; /* Loop/switch isn't completed */
_L8:
            mContentType = 1;
            mFlags = mFlags | 4;
            continue; /* Loop/switch isn't completed */
_L10:
            mContentType = 4;
            continue; /* Loop/switch isn't completed */
_L11:
            mContentType = 4;
            continue; /* Loop/switch isn't completed */
_L12:
            mContentType = 1;
            if(true) goto _L14; else goto _L13
_L13:
        }

        public Builder setLegacyStreamType(int i)
        {
            if(i == 10)
                throw new IllegalArgumentException("STREAM_ACCESSIBILITY is not a legacy stream type that was used for audio playback");
            else
                return setInternalLegacyStreamType(i);
        }

        public Builder setUsage(int i)
        {
            i;
            JVM INSTR tableswitch 0 16: default 84
        //                       0 91
        //                       1 91
        //                       2 91
        //                       3 91
        //                       4 91
        //                       5 91
        //                       6 91
        //                       7 91
        //                       8 91
        //                       9 91
        //                       10 91
        //                       11 91
        //                       12 91
        //                       13 91
        //                       14 91
        //                       15 91
        //                       16 91;
               goto _L1 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2
_L1:
            mUsage = 0;
_L4:
            return this;
_L2:
            mUsage = i;
            if(true) goto _L4; else goto _L3
_L3:
        }

        private Bundle mBundle;
        private int mContentType;
        private int mFlags;
        private int mSource;
        private HashSet mTags;
        private int mUsage;

        public Builder()
        {
            mUsage = 0;
            mContentType = 0;
            mSource = -1;
            mFlags = 0;
            mTags = new HashSet();
        }

        public Builder(AudioAttributes audioattributes)
        {
            mUsage = 0;
            mContentType = 0;
            mSource = -1;
            mFlags = 0;
            mTags = new HashSet();
            mUsage = AudioAttributes._2D_get3(audioattributes);
            mContentType = AudioAttributes._2D_get0(audioattributes);
            mFlags = AudioAttributes._2D_get1(audioattributes);
            mTags = (HashSet)AudioAttributes._2D_get2(audioattributes).clone();
        }
    }


    static int _2D_get0(AudioAttributes audioattributes)
    {
        return audioattributes.mContentType;
    }

    static int _2D_get1(AudioAttributes audioattributes)
    {
        return audioattributes.mFlags;
    }

    static HashSet _2D_get2(AudioAttributes audioattributes)
    {
        return audioattributes.mTags;
    }

    static int _2D_get3(AudioAttributes audioattributes)
    {
        return audioattributes.mUsage;
    }

    static Bundle _2D_set0(AudioAttributes audioattributes, Bundle bundle)
    {
        audioattributes.mBundle = bundle;
        return bundle;
    }

    static int _2D_set1(AudioAttributes audioattributes, int i)
    {
        audioattributes.mContentType = i;
        return i;
    }

    static int _2D_set2(AudioAttributes audioattributes, int i)
    {
        audioattributes.mFlags = i;
        return i;
    }

    static String _2D_set3(AudioAttributes audioattributes, String s)
    {
        audioattributes.mFormattedTags = s;
        return s;
    }

    static int _2D_set4(AudioAttributes audioattributes, int i)
    {
        audioattributes.mSource = i;
        return i;
    }

    static HashSet _2D_set5(AudioAttributes audioattributes, HashSet hashset)
    {
        audioattributes.mTags = hashset;
        return hashset;
    }

    static int _2D_set6(AudioAttributes audioattributes, int i)
    {
        audioattributes.mUsage = i;
        return i;
    }

    static int _2D_wrap0(int i)
    {
        return usageForStreamType(i);
    }

    private AudioAttributes()
    {
        mUsage = 0;
        mContentType = 0;
        mSource = -1;
        mFlags = 0;
    }

    AudioAttributes(AudioAttributes audioattributes)
    {
        this();
    }

    private AudioAttributes(Parcel parcel)
    {
        mUsage = 0;
        mContentType = 0;
        mSource = -1;
        mFlags = 0;
        mUsage = parcel.readInt();
        mContentType = parcel.readInt();
        mSource = parcel.readInt();
        mFlags = parcel.readInt();
        boolean flag;
        if((parcel.readInt() & 1) == 1)
            flag = true;
        else
            flag = false;
        mTags = new HashSet();
        if(flag)
        {
            mFormattedTags = new String(parcel.readString());
            mTags.add(mFormattedTags);
        } else
        {
            String as[] = parcel.readStringArray();
            for(int i = as.length - 1; i >= 0; i--)
                mTags.add(as[i]);

            mFormattedTags = TextUtils.join(";", mTags);
        }
        parcel.readInt();
        JVM INSTR lookupswitch 2: default 140
    //                   -1977: 201
    //                   1980: 209;
           goto _L1 _L2 _L3
_L1:
        Log.e("AudioAttributes", "Illegal value unmarshalling AudioAttributes, can't initialize bundle");
_L5:
        return;
_L2:
        mBundle = null;
        continue; /* Loop/switch isn't completed */
_L3:
        mBundle = new Bundle(parcel.readBundle());
        if(true) goto _L5; else goto _L4
_L4:
    }

    AudioAttributes(Parcel parcel, AudioAttributes audioattributes)
    {
        this(parcel);
    }

    public static int toLegacyStreamType(AudioAttributes audioattributes)
    {
        return toVolumeStreamType(false, audioattributes);
    }

    private static int toVolumeStreamType(boolean flag, AudioAttributes audioattributes)
    {
        int i = 1;
        boolean flag1 = false;
        if((audioattributes.getFlags() & 1) == 1)
        {
            if(!flag)
                i = 7;
            return i;
        }
        if((audioattributes.getFlags() & 4) == 4)
        {
            if(flag)
                i = 0;
            else
                i = 6;
            return i;
        }
        switch(audioattributes.getUsage())
        {
        case 15: // '\017'
        default:
            if(flag)
                throw new IllegalArgumentException((new StringBuilder()).append("Unknown usage value ").append(audioattributes.getUsage()).append(" in audio attributes").toString());
            else
                return 3;

        case 1: // '\001'
        case 12: // '\f'
        case 14: // '\016'
        case 16: // '\020'
            return 3;

        case 13: // '\r'
            return 1;

        case 2: // '\002'
            return 0;

        case 3: // '\003'
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 8;
            return i;

        case 4: // '\004'
            return 4;

        case 6: // '\006'
            return 2;

        case 5: // '\005'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
            return 5;

        case 11: // '\013'
            return 10;

        case 0: // '\0'
            break;
        }
        if(flag)
            i = 0x80000000;
        else
            i = 3;
        return i;
    }

    private static int usageForStreamType(int i)
    {
        switch(i)
        {
        case 9: // '\t'
        default:
            return 0;

        case 0: // '\0'
            return 2;

        case 1: // '\001'
        case 7: // '\007'
            return 13;

        case 2: // '\002'
            return 6;

        case 3: // '\003'
            return 1;

        case 4: // '\004'
            return 4;

        case 5: // '\005'
            return 5;

        case 6: // '\006'
            return 2;

        case 8: // '\b'
            return 3;

        case 10: // '\n'
            return 11;
        }
    }

    public static String usageToString(int i)
    {
        switch(i)
        {
        case 15: // '\017'
        default:
            return new String((new StringBuilder()).append("unknown usage ").append(i).toString());

        case 0: // '\0'
            return new String("USAGE_UNKNOWN");

        case 1: // '\001'
            return new String("USAGE_MEDIA");

        case 2: // '\002'
            return new String("USAGE_VOICE_COMMUNICATION");

        case 3: // '\003'
            return new String("USAGE_VOICE_COMMUNICATION_SIGNALLING");

        case 4: // '\004'
            return new String("USAGE_ALARM");

        case 5: // '\005'
            return new String("USAGE_NOTIFICATION");

        case 6: // '\006'
            return new String("USAGE_NOTIFICATION_RINGTONE");

        case 7: // '\007'
            return new String("USAGE_NOTIFICATION_COMMUNICATION_REQUEST");

        case 8: // '\b'
            return new String("USAGE_NOTIFICATION_COMMUNICATION_INSTANT");

        case 9: // '\t'
            return new String("USAGE_NOTIFICATION_COMMUNICATION_DELAYED");

        case 10: // '\n'
            return new String("USAGE_NOTIFICATION_EVENT");

        case 11: // '\013'
            return new String("USAGE_ASSISTANCE_ACCESSIBILITY");

        case 12: // '\f'
            return new String("USAGE_ASSISTANCE_NAVIGATION_GUIDANCE");

        case 13: // '\r'
            return new String("USAGE_ASSISTANCE_SONIFICATION");

        case 14: // '\016'
            return new String("USAGE_GAME");

        case 16: // '\020'
            return new String("USAGE_ASSISTANT");
        }
    }

    public String contentTypeToString()
    {
        switch(mContentType)
        {
        default:
            return new String((new StringBuilder()).append("unknown content type ").append(mContentType).toString());

        case 0: // '\0'
            return new String("CONTENT_TYPE_UNKNOWN");

        case 1: // '\001'
            return new String("CONTENT_TYPE_SPEECH");

        case 2: // '\002'
            return new String("CONTENT_TYPE_MUSIC");

        case 3: // '\003'
            return new String("CONTENT_TYPE_MOVIE");

        case 4: // '\004'
            return new String("CONTENT_TYPE_SONIFICATION");
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (AudioAttributes)obj;
        boolean flag1 = flag;
        if(mContentType == ((AudioAttributes) (obj)).mContentType)
        {
            flag1 = flag;
            if(mFlags == ((AudioAttributes) (obj)).mFlags)
            {
                flag1 = flag;
                if(mSource == ((AudioAttributes) (obj)).mSource)
                {
                    flag1 = flag;
                    if(mUsage == ((AudioAttributes) (obj)).mUsage)
                        flag1 = mFormattedTags.equals(((AudioAttributes) (obj)).mFormattedTags);
                }
            }
        }
        return flag1;
    }

    public int getAllFlags()
    {
        return mFlags & 0x3ff;
    }

    public Bundle getBundle()
    {
        if(mBundle == null)
            return mBundle;
        else
            return new Bundle(mBundle);
    }

    public int getCapturePreset()
    {
        return mSource;
    }

    public int getContentType()
    {
        return mContentType;
    }

    public int getFlags()
    {
        return mFlags & 0x111;
    }

    public Set getTags()
    {
        return Collections.unmodifiableSet(mTags);
    }

    public int getUsage()
    {
        return mUsage;
    }

    public int getVolumeControlStream()
    {
        return toVolumeStreamType(true, this);
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(mContentType), Integer.valueOf(mFlags), Integer.valueOf(mSource), Integer.valueOf(mUsage), mFormattedTags, mBundle
        });
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("AudioAttributes: usage=").append(usageToString()).append(" content=").append(contentTypeToString()).append(" flags=0x").append(Integer.toHexString(mFlags).toUpperCase()).append(" tags=").append(mFormattedTags).append(" bundle=");
        String s;
        if(mBundle == null)
            s = "null";
        else
            s = mBundle.toString();
        return new String(stringbuilder.append(s).toString());
    }

    public String usageToString()
    {
        return usageToString(mUsage);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mUsage);
        parcel.writeInt(mContentType);
        parcel.writeInt(mSource);
        parcel.writeInt(mFlags);
        parcel.writeInt(i & 1);
        if((i & 1) == 0)
        {
            String as[] = new String[mTags.size()];
            mTags.toArray(as);
            parcel.writeStringArray(as);
        } else
        if((i & 1) == 1)
            parcel.writeString(mFormattedTags);
        if(mBundle == null)
        {
            parcel.writeInt(-1977);
        } else
        {
            parcel.writeInt(1980);
            parcel.writeBundle(mBundle);
        }
    }

    private static final int ALL_PARCEL_FLAGS = 1;
    private static final int ATTR_PARCEL_IS_NULL_BUNDLE = -1977;
    private static final int ATTR_PARCEL_IS_VALID_BUNDLE = 1980;
    public static final int CONTENT_TYPE_MOVIE = 3;
    public static final int CONTENT_TYPE_MUSIC = 2;
    public static final int CONTENT_TYPE_SONIFICATION = 4;
    public static final int CONTENT_TYPE_SPEECH = 1;
    public static final int CONTENT_TYPE_UNKNOWN = 0;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AudioAttributes createFromParcel(Parcel parcel)
        {
            return new AudioAttributes(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AudioAttributes[] newArray(int i)
        {
            return new AudioAttributes[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int FLAG_ALL = 1023;
    private static final int FLAG_ALL_PUBLIC = 273;
    public static final int FLAG_AUDIBILITY_ENFORCED = 1;
    public static final int FLAG_BEACON = 8;
    public static final int FLAG_BYPASS_INTERRUPTION_POLICY = 64;
    public static final int FLAG_BYPASS_MUTE = 128;
    public static final int FLAG_DEEP_BUFFER = 512;
    public static final int FLAG_HW_AV_SYNC = 16;
    public static final int FLAG_HW_HOTWORD = 32;
    public static final int FLAG_LOW_LATENCY = 256;
    public static final int FLAG_SCO = 4;
    public static final int FLAG_SECURE = 2;
    public static final int FLATTEN_TAGS = 1;
    public static final int SDK_USAGES[] = {
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
        10, 11, 12, 13, 14, 16
    };
    public static final int SUPPRESSIBLE_CALL = 2;
    public static final int SUPPRESSIBLE_NEVER = 3;
    public static final int SUPPRESSIBLE_NOTIFICATION = 1;
    public static final SparseIntArray SUPPRESSIBLE_USAGES;
    private static final String TAG = "AudioAttributes";
    public static final int USAGE_ALARM = 4;
    public static final int USAGE_ASSISTANCE_ACCESSIBILITY = 11;
    public static final int USAGE_ASSISTANCE_NAVIGATION_GUIDANCE = 12;
    public static final int USAGE_ASSISTANCE_SONIFICATION = 13;
    public static final int USAGE_ASSISTANT = 16;
    public static final int USAGE_GAME = 14;
    public static final int USAGE_MEDIA = 1;
    public static final int USAGE_NOTIFICATION = 5;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_DELAYED = 9;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_INSTANT = 8;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_REQUEST = 7;
    public static final int USAGE_NOTIFICATION_EVENT = 10;
    public static final int USAGE_NOTIFICATION_RINGTONE = 6;
    public static final int USAGE_UNKNOWN = 0;
    public static final int USAGE_VIRTUAL_SOURCE = 15;
    public static final int USAGE_VOICE_COMMUNICATION = 2;
    public static final int USAGE_VOICE_COMMUNICATION_SIGNALLING = 3;
    private Bundle mBundle;
    private int mContentType;
    private int mFlags;
    private String mFormattedTags;
    private int mSource;
    private HashSet mTags;
    private int mUsage;

    static 
    {
        SUPPRESSIBLE_USAGES = new SparseIntArray();
        SUPPRESSIBLE_USAGES.put(5, 1);
        SUPPRESSIBLE_USAGES.put(6, 2);
        SUPPRESSIBLE_USAGES.put(7, 2);
        SUPPRESSIBLE_USAGES.put(8, 1);
        SUPPRESSIBLE_USAGES.put(9, 1);
        SUPPRESSIBLE_USAGES.put(10, 1);
        SUPPRESSIBLE_USAGES.put(11, 3);
        SUPPRESSIBLE_USAGES.put(2, 3);
    }
}

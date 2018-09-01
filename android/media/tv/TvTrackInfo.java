// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.os.*;
import android.text.TextUtils;
import com.android.internal.util.Preconditions;
import java.util.Objects;

public final class TvTrackInfo
    implements Parcelable
{
    public static final class Builder
    {

        public TvTrackInfo build()
        {
            return new TvTrackInfo(mType, mId, mLanguage, mDescription, mAudioChannelCount, mAudioSampleRate, mVideoWidth, mVideoHeight, mVideoFrameRate, mVideoPixelAspectRatio, mVideoActiveFormatDescription, mExtra, null);
        }

        public final Builder setAudioChannelCount(int i)
        {
            if(mType != 0)
            {
                throw new IllegalStateException("Not an audio track");
            } else
            {
                mAudioChannelCount = i;
                return this;
            }
        }

        public final Builder setAudioSampleRate(int i)
        {
            if(mType != 0)
            {
                throw new IllegalStateException("Not an audio track");
            } else
            {
                mAudioSampleRate = i;
                return this;
            }
        }

        public final Builder setDescription(CharSequence charsequence)
        {
            mDescription = charsequence;
            return this;
        }

        public final Builder setExtra(Bundle bundle)
        {
            mExtra = new Bundle(bundle);
            return this;
        }

        public final Builder setLanguage(String s)
        {
            mLanguage = s;
            return this;
        }

        public final Builder setVideoActiveFormatDescription(byte byte0)
        {
            if(mType != 1)
            {
                throw new IllegalStateException("Not a video track");
            } else
            {
                mVideoActiveFormatDescription = byte0;
                return this;
            }
        }

        public final Builder setVideoFrameRate(float f)
        {
            if(mType != 1)
            {
                throw new IllegalStateException("Not a video track");
            } else
            {
                mVideoFrameRate = f;
                return this;
            }
        }

        public final Builder setVideoHeight(int i)
        {
            if(mType != 1)
            {
                throw new IllegalStateException("Not a video track");
            } else
            {
                mVideoHeight = i;
                return this;
            }
        }

        public final Builder setVideoPixelAspectRatio(float f)
        {
            if(mType != 1)
            {
                throw new IllegalStateException("Not a video track");
            } else
            {
                mVideoPixelAspectRatio = f;
                return this;
            }
        }

        public final Builder setVideoWidth(int i)
        {
            if(mType != 1)
            {
                throw new IllegalStateException("Not a video track");
            } else
            {
                mVideoWidth = i;
                return this;
            }
        }

        private int mAudioChannelCount;
        private int mAudioSampleRate;
        private CharSequence mDescription;
        private Bundle mExtra;
        private final String mId;
        private String mLanguage;
        private final int mType;
        private byte mVideoActiveFormatDescription;
        private float mVideoFrameRate;
        private int mVideoHeight;
        private float mVideoPixelAspectRatio;
        private int mVideoWidth;

        public Builder(int i, String s)
        {
            mVideoPixelAspectRatio = 1.0F;
            if(i != 0 && i != 1 && i != 2)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Unknown type: ").append(i).toString());
            } else
            {
                Preconditions.checkNotNull(s);
                mType = i;
                mId = s;
                return;
            }
        }
    }


    private TvTrackInfo(int i, String s, String s1, CharSequence charsequence, int j, int k, int l, 
            int i1, float f, float f1, byte byte0, Bundle bundle)
    {
        mType = i;
        mId = s;
        mLanguage = s1;
        mDescription = charsequence;
        mAudioChannelCount = j;
        mAudioSampleRate = k;
        mVideoWidth = l;
        mVideoHeight = i1;
        mVideoFrameRate = f;
        mVideoPixelAspectRatio = f1;
        mVideoActiveFormatDescription = byte0;
        mExtra = bundle;
    }

    TvTrackInfo(int i, String s, String s1, CharSequence charsequence, int j, int k, int l, 
            int i1, float f, float f1, byte byte0, Bundle bundle, TvTrackInfo tvtrackinfo)
    {
        this(i, s, s1, charsequence, j, k, l, i1, f, f1, byte0, bundle);
    }

    private TvTrackInfo(Parcel parcel)
    {
        mType = parcel.readInt();
        mId = parcel.readString();
        mLanguage = parcel.readString();
        mDescription = parcel.readString();
        mAudioChannelCount = parcel.readInt();
        mAudioSampleRate = parcel.readInt();
        mVideoWidth = parcel.readInt();
        mVideoHeight = parcel.readInt();
        mVideoFrameRate = parcel.readFloat();
        mVideoPixelAspectRatio = parcel.readFloat();
        mVideoActiveFormatDescription = parcel.readByte();
        mExtra = parcel.readBundle();
    }

    TvTrackInfo(Parcel parcel, TvTrackInfo tvtrackinfo)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        flag = true;
        flag1 = false;
        if(this == obj)
            return true;
        if(!(obj instanceof TvTrackInfo))
            return false;
        obj = (TvTrackInfo)obj;
        flag2 = flag1;
        if(!TextUtils.equals(mId, ((TvTrackInfo) (obj)).mId)) goto _L2; else goto _L1
_L1:
        flag2 = flag1;
        if(mType != ((TvTrackInfo) (obj)).mType) goto _L2; else goto _L3
_L3:
        flag2 = flag1;
        if(!TextUtils.equals(mLanguage, ((TvTrackInfo) (obj)).mLanguage)) goto _L2; else goto _L4
_L4:
        flag2 = flag1;
        if(!TextUtils.equals(mDescription, ((TvTrackInfo) (obj)).mDescription)) goto _L2; else goto _L5
_L5:
        flag2 = flag1;
        if(!Objects.equals(mExtra, ((TvTrackInfo) (obj)).mExtra)) goto _L2; else goto _L6
_L6:
        if(mType != 0) goto _L8; else goto _L7
_L7:
        flag2 = flag1;
        if(mAudioChannelCount == ((TvTrackInfo) (obj)).mAudioChannelCount)
        {
            flag2 = flag1;
            if(mAudioSampleRate == ((TvTrackInfo) (obj)).mAudioSampleRate)
                flag2 = true;
        }
_L2:
        return flag2;
_L8:
        flag2 = flag;
        if(mType == 1)
        {
            flag2 = flag1;
            if(mVideoWidth == ((TvTrackInfo) (obj)).mVideoWidth)
            {
                flag2 = flag1;
                if(mVideoHeight == ((TvTrackInfo) (obj)).mVideoHeight)
                {
                    flag2 = flag1;
                    if(mVideoFrameRate == ((TvTrackInfo) (obj)).mVideoFrameRate)
                        if(mVideoPixelAspectRatio == ((TvTrackInfo) (obj)).mVideoPixelAspectRatio)
                            flag2 = flag;
                        else
                            flag2 = false;
                }
            }
        }
        if(true) goto _L2; else goto _L9
_L9:
    }

    public final int getAudioChannelCount()
    {
        if(mType != 0)
            throw new IllegalStateException("Not an audio track");
        else
            return mAudioChannelCount;
    }

    public final int getAudioSampleRate()
    {
        if(mType != 0)
            throw new IllegalStateException("Not an audio track");
        else
            return mAudioSampleRate;
    }

    public final CharSequence getDescription()
    {
        return mDescription;
    }

    public final Bundle getExtra()
    {
        return mExtra;
    }

    public final String getId()
    {
        return mId;
    }

    public final String getLanguage()
    {
        return mLanguage;
    }

    public final int getType()
    {
        return mType;
    }

    public final byte getVideoActiveFormatDescription()
    {
        if(mType != 1)
            throw new IllegalStateException("Not a video track");
        else
            return mVideoActiveFormatDescription;
    }

    public final float getVideoFrameRate()
    {
        if(mType != 1)
            throw new IllegalStateException("Not a video track");
        else
            return mVideoFrameRate;
    }

    public final int getVideoHeight()
    {
        if(mType != 1)
            throw new IllegalStateException("Not a video track");
        else
            return mVideoHeight;
    }

    public final float getVideoPixelAspectRatio()
    {
        if(mType != 1)
            throw new IllegalStateException("Not a video track");
        else
            return mVideoPixelAspectRatio;
    }

    public final int getVideoWidth()
    {
        if(mType != 1)
            throw new IllegalStateException("Not a video track");
        else
            return mVideoWidth;
    }

    public int hashCode()
    {
        return Objects.hashCode(mId);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        String s = null;
        parcel.writeInt(mType);
        parcel.writeString(mId);
        parcel.writeString(mLanguage);
        if(mDescription != null)
            s = mDescription.toString();
        parcel.writeString(s);
        parcel.writeInt(mAudioChannelCount);
        parcel.writeInt(mAudioSampleRate);
        parcel.writeInt(mVideoWidth);
        parcel.writeInt(mVideoHeight);
        parcel.writeFloat(mVideoFrameRate);
        parcel.writeFloat(mVideoPixelAspectRatio);
        parcel.writeByte(mVideoActiveFormatDescription);
        parcel.writeBundle(mExtra);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public TvTrackInfo createFromParcel(Parcel parcel)
        {
            return new TvTrackInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public TvTrackInfo[] newArray(int i)
        {
            return new TvTrackInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int TYPE_AUDIO = 0;
    public static final int TYPE_SUBTITLE = 2;
    public static final int TYPE_VIDEO = 1;
    private final int mAudioChannelCount;
    private final int mAudioSampleRate;
    private final CharSequence mDescription;
    private final Bundle mExtra;
    private final String mId;
    private final String mLanguage;
    private final int mType;
    private final byte mVideoActiveFormatDescription;
    private final float mVideoFrameRate;
    private final int mVideoHeight;
    private final float mVideoPixelAspectRatio;
    private final int mVideoWidth;

}

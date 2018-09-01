// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.Parcel;
import android.os.Parcelable;

public final class PlaybackParams
    implements Parcelable
{

    public PlaybackParams()
    {
        mSet = 0;
        mAudioFallbackMode = 0;
        mAudioStretchMode = 0;
        mPitch = 1.0F;
        mSpeed = 1.0F;
    }

    private PlaybackParams(Parcel parcel)
    {
        mSet = 0;
        mAudioFallbackMode = 0;
        mAudioStretchMode = 0;
        mPitch = 1.0F;
        mSpeed = 1.0F;
        mSet = parcel.readInt();
        mAudioFallbackMode = parcel.readInt();
        mAudioStretchMode = parcel.readInt();
        mPitch = parcel.readFloat();
        if(mPitch < 0.0F)
            mPitch = 0.0F;
        mSpeed = parcel.readFloat();
    }

    PlaybackParams(Parcel parcel, PlaybackParams playbackparams)
    {
        this(parcel);
    }

    public PlaybackParams allowDefaults()
    {
        mSet = mSet | 0xf;
        return this;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getAudioFallbackMode()
    {
        if((mSet & 4) == 0)
            throw new IllegalStateException("audio fallback mode not set");
        else
            return mAudioFallbackMode;
    }

    public int getAudioStretchMode()
    {
        if((mSet & 8) == 0)
            throw new IllegalStateException("audio stretch mode not set");
        else
            return mAudioStretchMode;
    }

    public float getPitch()
    {
        if((mSet & 2) == 0)
            throw new IllegalStateException("pitch not set");
        else
            return mPitch;
    }

    public float getSpeed()
    {
        if((mSet & 1) == 0)
            throw new IllegalStateException("speed not set");
        else
            return mSpeed;
    }

    public PlaybackParams setAudioFallbackMode(int i)
    {
        mAudioFallbackMode = i;
        mSet = mSet | 4;
        return this;
    }

    public PlaybackParams setAudioStretchMode(int i)
    {
        mAudioStretchMode = i;
        mSet = mSet | 8;
        return this;
    }

    public PlaybackParams setPitch(float f)
    {
        if(f < 0.0F)
        {
            throw new IllegalArgumentException("pitch must not be negative");
        } else
        {
            mPitch = f;
            mSet = mSet | 2;
            return this;
        }
    }

    public PlaybackParams setSpeed(float f)
    {
        mSpeed = f;
        mSet = mSet | 1;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mSet);
        parcel.writeInt(mAudioFallbackMode);
        parcel.writeInt(mAudioStretchMode);
        parcel.writeFloat(mPitch);
        parcel.writeFloat(mSpeed);
    }

    public static final int AUDIO_FALLBACK_MODE_DEFAULT = 0;
    public static final int AUDIO_FALLBACK_MODE_FAIL = 2;
    public static final int AUDIO_FALLBACK_MODE_MUTE = 1;
    public static final int AUDIO_STRETCH_MODE_DEFAULT = 0;
    public static final int AUDIO_STRETCH_MODE_VOICE = 1;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PlaybackParams createFromParcel(Parcel parcel)
        {
            return new PlaybackParams(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PlaybackParams[] newArray(int i)
        {
            return new PlaybackParams[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int SET_AUDIO_FALLBACK_MODE = 4;
    private static final int SET_AUDIO_STRETCH_MODE = 8;
    private static final int SET_PITCH = 2;
    private static final int SET_SPEED = 1;
    private int mAudioFallbackMode;
    private int mAudioStretchMode;
    private float mPitch;
    private int mSet;
    private float mSpeed;

}

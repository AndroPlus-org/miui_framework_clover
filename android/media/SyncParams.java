// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


public final class SyncParams
{

    public SyncParams()
    {
        mSet = 0;
        mAudioAdjustMode = 0;
        mSyncSource = 0;
        mTolerance = 0.0F;
        mFrameRate = 0.0F;
    }

    public SyncParams allowDefaults()
    {
        mSet = mSet | 7;
        return this;
    }

    public int getAudioAdjustMode()
    {
        if((mSet & 2) == 0)
            throw new IllegalStateException("audio adjust mode not set");
        else
            return mAudioAdjustMode;
    }

    public float getFrameRate()
    {
        if((mSet & 8) == 0)
            throw new IllegalStateException("frame rate not set");
        else
            return mFrameRate;
    }

    public int getSyncSource()
    {
        if((mSet & 1) == 0)
            throw new IllegalStateException("sync source not set");
        else
            return mSyncSource;
    }

    public float getTolerance()
    {
        if((mSet & 4) == 0)
            throw new IllegalStateException("tolerance not set");
        else
            return mTolerance;
    }

    public SyncParams setAudioAdjustMode(int i)
    {
        mAudioAdjustMode = i;
        mSet = mSet | 2;
        return this;
    }

    public SyncParams setFrameRate(float f)
    {
        mFrameRate = f;
        mSet = mSet | 8;
        return this;
    }

    public SyncParams setSyncSource(int i)
    {
        mSyncSource = i;
        mSet = mSet | 1;
        return this;
    }

    public SyncParams setTolerance(float f)
    {
        if(f < 0.0F || f >= 1.0F)
        {
            throw new IllegalArgumentException("tolerance must be less than one and non-negative");
        } else
        {
            mTolerance = f;
            mSet = mSet | 4;
            return this;
        }
    }

    public static final int AUDIO_ADJUST_MODE_DEFAULT = 0;
    public static final int AUDIO_ADJUST_MODE_RESAMPLE = 2;
    public static final int AUDIO_ADJUST_MODE_STRETCH = 1;
    private static final int SET_AUDIO_ADJUST_MODE = 2;
    private static final int SET_FRAME_RATE = 8;
    private static final int SET_SYNC_SOURCE = 1;
    private static final int SET_TOLERANCE = 4;
    public static final int SYNC_SOURCE_AUDIO = 2;
    public static final int SYNC_SOURCE_DEFAULT = 0;
    public static final int SYNC_SOURCE_SYSTEM_CLOCK = 1;
    public static final int SYNC_SOURCE_VSYNC = 3;
    private int mAudioAdjustMode;
    private float mFrameRate;
    private int mSet;
    private int mSyncSource;
    private float mTolerance;
}

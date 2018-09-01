// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


public abstract class VolumeProvider
{
    public static abstract class Callback
    {

        public abstract void onVolumeChanged(VolumeProvider volumeprovider);

        public Callback()
        {
        }
    }


    public VolumeProvider(int i, int j, int k)
    {
        mControlType = i;
        mMaxVolume = j;
        mCurrentVolume = k;
    }

    public final int getCurrentVolume()
    {
        return mCurrentVolume;
    }

    public final int getMaxVolume()
    {
        return mMaxVolume;
    }

    public final int getVolumeControl()
    {
        return mControlType;
    }

    public void onAdjustVolume(int i)
    {
    }

    public void onSetVolumeTo(int i)
    {
    }

    public void setCallback(Callback callback)
    {
        mCallback = callback;
    }

    public final void setCurrentVolume(int i)
    {
        mCurrentVolume = i;
        if(mCallback != null)
            mCallback.onVolumeChanged(this);
    }

    public static final int VOLUME_CONTROL_ABSOLUTE = 2;
    public static final int VOLUME_CONTROL_FIXED = 0;
    public static final int VOLUME_CONTROL_RELATIVE = 1;
    private Callback mCallback;
    private final int mControlType;
    private int mCurrentVolume;
    private final int mMaxVolume;
}

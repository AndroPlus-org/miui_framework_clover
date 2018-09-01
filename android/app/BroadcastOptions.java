// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.Bundle;

public class BroadcastOptions
{

    private BroadcastOptions()
    {
        mMinManifestReceiverApiLevel = 0;
        mMaxManifestReceiverApiLevel = 10000;
    }

    public BroadcastOptions(Bundle bundle)
    {
        mMinManifestReceiverApiLevel = 0;
        mMaxManifestReceiverApiLevel = 10000;
        mTemporaryAppWhitelistDuration = bundle.getLong("android:broadcast.temporaryAppWhitelistDuration");
        mMinManifestReceiverApiLevel = bundle.getInt("android:broadcast.minManifestReceiverApiLevel", 0);
        mMaxManifestReceiverApiLevel = bundle.getInt("android:broadcast.maxManifestReceiverApiLevel", 10000);
    }

    public static BroadcastOptions makeBasic()
    {
        return new BroadcastOptions();
    }

    public int getMaxManifestReceiverApiLevel()
    {
        return mMaxManifestReceiverApiLevel;
    }

    public int getMinManifestReceiverApiLevel()
    {
        return mMinManifestReceiverApiLevel;
    }

    public long getTemporaryAppWhitelistDuration()
    {
        return mTemporaryAppWhitelistDuration;
    }

    public void setMaxManifestReceiverApiLevel(int i)
    {
        mMaxManifestReceiverApiLevel = i;
    }

    public void setMinManifestReceiverApiLevel(int i)
    {
        mMinManifestReceiverApiLevel = i;
    }

    public void setTemporaryAppWhitelistDuration(long l)
    {
        mTemporaryAppWhitelistDuration = l;
    }

    public Bundle toBundle()
    {
        Bundle bundle = new Bundle();
        if(mTemporaryAppWhitelistDuration > 0L)
            bundle.putLong("android:broadcast.temporaryAppWhitelistDuration", mTemporaryAppWhitelistDuration);
        if(mMinManifestReceiverApiLevel != 0)
            bundle.putInt("android:broadcast.minManifestReceiverApiLevel", mMinManifestReceiverApiLevel);
        if(mMaxManifestReceiverApiLevel != 10000)
            bundle.putInt("android:broadcast.maxManifestReceiverApiLevel", mMaxManifestReceiverApiLevel);
        Bundle bundle1 = bundle;
        if(bundle.isEmpty())
            bundle1 = null;
        return bundle1;
    }

    static final String KEY_MAX_MANIFEST_RECEIVER_API_LEVEL = "android:broadcast.maxManifestReceiverApiLevel";
    static final String KEY_MIN_MANIFEST_RECEIVER_API_LEVEL = "android:broadcast.minManifestReceiverApiLevel";
    static final String KEY_TEMPORARY_APP_WHITELIST_DURATION = "android:broadcast.temporaryAppWhitelistDuration";
    private int mMaxManifestReceiverApiLevel;
    private int mMinManifestReceiverApiLevel;
    private long mTemporaryAppWhitelistDuration;
}

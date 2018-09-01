// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.util.IntArray;

// Referenced classes of package android.media:
//            VolumePolicy

public abstract class AudioManagerInternal
{
    public static interface RingerModeDelegate
    {

        public abstract boolean canVolumeDownEnterSilent();

        public abstract int getRingerModeAffectedStreams(int i);

        public abstract int onSetRingerModeExternal(int i, int j, String s, int k, VolumePolicy volumepolicy);

        public abstract int onSetRingerModeInternal(int i, int j, String s, int k, VolumePolicy volumepolicy);
    }


    public AudioManagerInternal()
    {
    }

    public abstract void adjustStreamVolumeForUid(int i, int j, int k, String s, int l);

    public abstract void adjustSuggestedStreamVolumeForUid(int i, int j, int k, String s, int l);

    public abstract int getRingerModeInternal();

    public abstract void setAccessibilityServiceUids(IntArray intarray);

    public abstract void setRingerModeDelegate(RingerModeDelegate ringermodedelegate);

    public abstract void setRingerModeInternal(int i, String s);

    public abstract void setStreamVolumeForUid(int i, int j, int k, String s, int l);

    public abstract void updateRingerModeAffectedStreamsInternal();
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.util.Log;

// Referenced classes of package android.media:
//            SoundPool

public class MediaActionSound
{
    private class SoundState
    {

        public int id;
        public final int name;
        public int state;
        final MediaActionSound this$0;

        public SoundState(int i)
        {
            this$0 = MediaActionSound.this;
            super();
            name = i;
            id = 0;
            state = 0;
        }
    }


    static SoundState[] _2D_get0(MediaActionSound mediaactionsound)
    {
        return mediaactionsound.mSounds;
    }

    public MediaActionSound()
    {
        mLoadCompleteListener = new SoundPool.OnLoadCompleteListener() {

            public void onLoadComplete(SoundPool soundpool, int j, int k)
            {
                SoundState soundstate;
                SoundState asoundstate[] = MediaActionSound._2D_get0(MediaActionSound.this);
                int l = asoundstate.length;
                for(int i1 = 0; i1 < l; i1++)
                {
                    soundstate = asoundstate[i1];
                    if(soundstate.id == j)
                        break MISSING_BLOCK_LABEL_46;
                }

                if(false) goto _L2; else goto _L1
_L2:
                j = 0;
                soundstate;
                JVM INSTR monitorenter ;
                if(k == 0)
                    break MISSING_BLOCK_LABEL_111;
                soundstate.state = 0;
                soundstate.id = 0;
                soundpool = JVM INSTR new #35  <Class StringBuilder>;
                soundpool.StringBuilder();
                Log.e("MediaActionSound", soundpool.append("OnLoadCompleteListener() error: ").append(k).append(" loading sound: ").append(soundstate.name).toString());
                soundstate;
                JVM INSTR monitorexit ;
                return;
                soundstate.state;
                JVM INSTR tableswitch 1 2: default 140
            //                           1 206
            //                           2 221;
                   goto _L3 _L4 _L5
_L3:
                StringBuilder stringbuilder = JVM INSTR new #35  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.e("MediaActionSound", stringbuilder.append("OnLoadCompleteListener() called in wrong state: ").append(soundstate.state).append(" for sound: ").append(soundstate.name).toString());
_L6:
                soundstate;
                JVM INSTR monitorexit ;
                if(j != 0)
                    soundpool.play(j, 1.0F, 1.0F, 0, 0, 1.0F);
_L1:
                return;
_L4:
                soundstate.state = 3;
                  goto _L6
                soundpool;
                throw soundpool;
_L5:
                j = soundstate.id;
                soundstate.state = 3;
                  goto _L6
            }

            final MediaActionSound this$0;

            
            {
                this$0 = MediaActionSound.this;
                super();
            }
        }
;
        mSoundPool = (new SoundPool.Builder()).setMaxStreams(1).setAudioAttributes((new AudioAttributes.Builder()).setUsage(13).setFlags(1).setContentType(4).build()).build();
        mSoundPool.setOnLoadCompleteListener(mLoadCompleteListener);
        mSounds = new SoundState[SOUND_FILES.length];
        for(int i = 0; i < mSounds.length; i++)
            mSounds[i] = new SoundState(i);

    }

    private int loadSound(SoundState soundstate)
    {
        int i = mSoundPool.load(SOUND_FILES[soundstate.name], 1);
        if(i > 0)
        {
            soundstate.state = 1;
            soundstate.id = i;
        }
        return i;
    }

    public void load(int i)
    {
        if(i < 0 || i >= SOUND_FILES.length)
            throw new RuntimeException((new StringBuilder()).append("Unknown sound requested: ").append(i).toString());
        SoundState soundstate = mSounds[i];
        soundstate;
        JVM INSTR monitorenter ;
        soundstate.state;
        JVM INSTR tableswitch 0 0: default 72
    //                   0 111;
           goto _L1 _L2
_L1:
        StringBuilder stringbuilder = JVM INSTR new #124 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e("MediaActionSound", stringbuilder.append("load() called in wrong state: ").append(soundstate).append(" for sound: ").append(i).toString());
_L4:
        soundstate;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(loadSound(soundstate) > 0) goto _L4; else goto _L3
_L3:
        StringBuilder stringbuilder1 = JVM INSTR new #124 <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        Log.e("MediaActionSound", stringbuilder1.append("load() error loading sound: ").append(i).toString());
          goto _L4
        Exception exception;
        exception;
        throw exception;
    }

    public void play(int i)
    {
        if(i < 0 || i >= SOUND_FILES.length)
            throw new RuntimeException((new StringBuilder()).append("Unknown sound requested: ").append(i).toString());
        SoundState soundstate = mSounds[i];
        soundstate;
        JVM INSTR monitorenter ;
        soundstate.state;
        JVM INSTR tableswitch 0 3: default 84
    //                   0 126
    //                   1 175
    //                   2 84
    //                   3 183;
           goto _L1 _L2 _L3 _L1 _L4
_L1:
        StringBuilder stringbuilder = JVM INSTR new #124 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e("MediaActionSound", stringbuilder.append("play() called in wrong state: ").append(soundstate.state).append(" for sound: ").append(i).toString());
_L6:
        soundstate;
        JVM INSTR monitorexit ;
        return;
_L2:
        loadSound(soundstate);
        if(loadSound(soundstate) > 0) goto _L3; else goto _L5
_L5:
        StringBuilder stringbuilder1 = JVM INSTR new #124 <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        Log.e("MediaActionSound", stringbuilder1.append("play() error loading sound: ").append(i).toString());
          goto _L6
        Exception exception;
        exception;
        throw exception;
_L3:
        soundstate.state = 2;
          goto _L6
_L4:
        mSoundPool.play(soundstate.id, 1.0F, 1.0F, 0, 0, 1.0F);
          goto _L6
    }

    public void release()
    {
        int i;
        SoundState asoundstate[];
        int j;
        i = 0;
        if(mSoundPool == null)
            break MISSING_BLOCK_LABEL_69;
        asoundstate = mSounds;
        j = asoundstate.length;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        SoundState soundstate = asoundstate[i];
        soundstate;
        JVM INSTR monitorenter ;
        soundstate.state = 0;
        soundstate.id = 0;
        soundstate;
        JVM INSTR monitorexit ;
        i++;
        if(true) goto _L2; else goto _L1
        Exception exception;
        exception;
        throw exception;
_L1:
        mSoundPool.release();
        mSoundPool = null;
    }

    public static final int FOCUS_COMPLETE = 1;
    private static final int NUM_MEDIA_SOUND_STREAMS = 1;
    public static final int SHUTTER_CLICK = 0;
    private static final String SOUND_FILES[] = {
        "/system/media/audio/ui/camera_click.ogg", "/system/media/audio/ui/camera_focus.ogg", "/system/media/audio/ui/VideoRecord.ogg", "/system/media/audio/ui/VideoStop.ogg"
    };
    public static final int START_VIDEO_RECORDING = 2;
    private static final int STATE_LOADED = 3;
    private static final int STATE_LOADING = 1;
    private static final int STATE_LOADING_PLAY_REQUESTED = 2;
    private static final int STATE_NOT_LOADED = 0;
    public static final int STOP_VIDEO_RECORDING = 3;
    private static final String TAG = "MediaActionSound";
    private SoundPool.OnLoadCompleteListener mLoadCompleteListener;
    private SoundPool mSoundPool;
    private SoundState mSounds[];

}

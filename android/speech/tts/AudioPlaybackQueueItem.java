// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech.tts;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.ConditionVariable;
import android.util.Log;

// Referenced classes of package android.speech.tts:
//            PlaybackQueueItem

class AudioPlaybackQueueItem extends PlaybackQueueItem
{

    static ConditionVariable _2D_get0(AudioPlaybackQueueItem audioplaybackqueueitem)
    {
        return audioplaybackqueueitem.mDone;
    }

    static boolean _2D_set0(AudioPlaybackQueueItem audioplaybackqueueitem, boolean flag)
    {
        audioplaybackqueueitem.mFinished = flag;
        return flag;
    }

    AudioPlaybackQueueItem(TextToSpeechService.UtteranceProgressDispatcher utteranceprogressdispatcher, Object obj, Context context, Uri uri, TextToSpeechService.AudioOutputParams audiooutputparams)
    {
        super(utteranceprogressdispatcher, obj);
        mContext = context;
        mUri = uri;
        mAudioParams = audiooutputparams;
        mPlayer = null;
        mFinished = false;
    }

    private static final float clip(float f, float f1, float f2)
    {
        if(f >= f1)
            if(f < f2)
                f1 = f;
            else
                f1 = f2;
        return f1;
    }

    private void finish()
    {
        try
        {
            mPlayer.stop();
        }
        catch(IllegalStateException illegalstateexception) { }
        mPlayer.release();
    }

    private static void setupVolume(MediaPlayer mediaplayer, float f, float f1)
    {
        float f2;
        float f3;
        float f4;
        f2 = clip(f, 0.0F, 1.0F);
        f3 = clip(f1, -1F, 1.0F);
        f = f2;
        f4 = f2;
        if(f3 <= 0.0F) goto _L2; else goto _L1
_L1:
        f1 = f2 * (1.0F - f3);
_L4:
        mediaplayer.setVolume(f1, f4);
        return;
_L2:
        f1 = f;
        if(f3 < 0.0F)
        {
            f4 = f2 * (1.0F + f3);
            f1 = f;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void run()
    {
        TextToSpeechService.UtteranceProgressDispatcher utteranceprogressdispatcher = getDispatcher();
        utteranceprogressdispatcher.dispatchOnStart();
        int i = mAudioParams.mSessionId;
        Context context = mContext;
        Uri uri = mUri;
        android.media.AudioAttributes audioattributes = mAudioParams.mAudioAttributes;
        if(i <= 0)
            i = 0;
        mPlayer = MediaPlayer.create(context, uri, null, audioattributes, i);
        if(mPlayer == null)
        {
            utteranceprogressdispatcher.dispatchOnError(-5);
            return;
        }
        try
        {
            MediaPlayer mediaplayer = mPlayer;
            Object obj = JVM INSTR new #6   <Class AudioPlaybackQueueItem$1>;
            ((_cls1) (obj)).this. _cls1();
            mediaplayer.setOnErrorListener(((android.media.MediaPlayer.OnErrorListener) (obj)));
            mediaplayer = mPlayer;
            obj = JVM INSTR new #8   <Class AudioPlaybackQueueItem$2>;
            ((_cls2) (obj)).this. _cls2();
            mediaplayer.setOnCompletionListener(((android.media.MediaPlayer.OnCompletionListener) (obj)));
            setupVolume(mPlayer, mAudioParams.mVolume, mAudioParams.mPan);
            mPlayer.start();
            mDone.block();
            finish();
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            Log.w("TTS.AudioQueueItem", "MediaPlayer failed", illegalargumentexception);
            mDone.open();
        }
        if(mFinished)
            utteranceprogressdispatcher.dispatchOnSuccess();
        else
            utteranceprogressdispatcher.dispatchOnStop();
    }

    void stop(int i)
    {
        mDone.open();
    }

    private static final String TAG = "TTS.AudioQueueItem";
    private final TextToSpeechService.AudioOutputParams mAudioParams;
    private final Context mContext;
    private final ConditionVariable mDone = new ConditionVariable();
    private volatile boolean mFinished;
    private MediaPlayer mPlayer;
    private final Uri mUri;

    // Unreferenced inner class android/speech/tts/AudioPlaybackQueueItem$1

/* anonymous class */
    class _cls1
        implements android.media.MediaPlayer.OnErrorListener
    {

        public boolean onError(MediaPlayer mediaplayer, int i, int j)
        {
            Log.w("TTS.AudioQueueItem", (new StringBuilder()).append("Audio playback error: ").append(i).append(", ").append(j).toString());
            AudioPlaybackQueueItem._2D_get0(AudioPlaybackQueueItem.this).open();
            return true;
        }

        final AudioPlaybackQueueItem this$0;

            
            {
                this$0 = AudioPlaybackQueueItem.this;
                super();
            }
    }


    // Unreferenced inner class android/speech/tts/AudioPlaybackQueueItem$2

/* anonymous class */
    class _cls2
        implements android.media.MediaPlayer.OnCompletionListener
    {

        public void onCompletion(MediaPlayer mediaplayer)
        {
            AudioPlaybackQueueItem._2D_set0(AudioPlaybackQueueItem.this, true);
            AudioPlaybackQueueItem._2D_get0(AudioPlaybackQueueItem.this).open();
        }

        final AudioPlaybackQueueItem this$0;

            
            {
                this$0 = AudioPlaybackQueueItem.this;
                super();
            }
    }

}

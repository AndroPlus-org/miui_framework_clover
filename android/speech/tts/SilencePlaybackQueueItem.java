// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech.tts;

import android.os.ConditionVariable;

// Referenced classes of package android.speech.tts:
//            PlaybackQueueItem

class SilencePlaybackQueueItem extends PlaybackQueueItem
{

    SilencePlaybackQueueItem(TextToSpeechService.UtteranceProgressDispatcher utteranceprogressdispatcher, Object obj, long l)
    {
        super(utteranceprogressdispatcher, obj);
        mSilenceDurationMs = l;
    }

    public void run()
    {
        getDispatcher().dispatchOnStart();
        boolean flag = false;
        if(mSilenceDurationMs > 0L)
            flag = mCondVar.block(mSilenceDurationMs);
        if(flag)
            getDispatcher().dispatchOnStop();
        else
            getDispatcher().dispatchOnSuccess();
    }

    void stop(int i)
    {
        mCondVar.open();
    }

    private final ConditionVariable mCondVar = new ConditionVariable();
    private final long mSilenceDurationMs;
}

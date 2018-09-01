// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech.tts;


abstract class PlaybackQueueItem
    implements Runnable
{

    PlaybackQueueItem(TextToSpeechService.UtteranceProgressDispatcher utteranceprogressdispatcher, Object obj)
    {
        mDispatcher = utteranceprogressdispatcher;
        mCallerIdentity = obj;
    }

    Object getCallerIdentity()
    {
        return mCallerIdentity;
    }

    protected TextToSpeechService.UtteranceProgressDispatcher getDispatcher()
    {
        return mDispatcher;
    }

    public abstract void run();

    abstract void stop(int i);

    private final Object mCallerIdentity;
    private final TextToSpeechService.UtteranceProgressDispatcher mDispatcher;
}

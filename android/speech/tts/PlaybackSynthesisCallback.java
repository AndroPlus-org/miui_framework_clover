// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech.tts;

import android.util.Log;

// Referenced classes of package android.speech.tts:
//            AbstractSynthesisCallback, SynthesisPlaybackQueueItem, AbstractEventLogger, BlockingAudioTrack, 
//            AudioPlaybackHandler

class PlaybackSynthesisCallback extends AbstractSynthesisCallback
{

    PlaybackSynthesisCallback(TextToSpeechService.AudioOutputParams audiooutputparams, AudioPlaybackHandler audioplaybackhandler, TextToSpeechService.UtteranceProgressDispatcher utteranceprogressdispatcher, Object obj, AbstractEventLogger abstracteventlogger, boolean flag)
    {
        super(flag);
        mItem = null;
        mDone = false;
        mAudioParams = audiooutputparams;
        mAudioTrackHandler = audioplaybackhandler;
        mDispatcher = utteranceprogressdispatcher;
        mCallerIdentity = obj;
        mLogger = abstracteventlogger;
        mStatusCode = 0;
    }

    public int audioAvailable(byte abyte0[], int i, int j)
    {
        if(j > getMaxBufferSize() || j <= 0)
            throw new IllegalArgumentException((new StringBuilder()).append("buffer is too large or of zero length (").append(j).append(" bytes)").toString());
        byte abyte1[] = ((byte []) (mStateLock));
        abyte1;
        JVM INSTR monitorenter ;
        if(mItem != null)
            break MISSING_BLOCK_LABEL_71;
        mStatusCode = -5;
        abyte1;
        JVM INSTR monitorexit ;
        return -1;
        int k = mStatusCode;
        if(k == 0)
            break MISSING_BLOCK_LABEL_87;
        abyte1;
        JVM INSTR monitorexit ;
        return -1;
        if(mStatusCode != -2)
            break MISSING_BLOCK_LABEL_106;
        i = errorCodeOnStop();
        abyte1;
        JVM INSTR monitorexit ;
        return i;
        SynthesisPlaybackQueueItem synthesisplaybackqueueitem = mItem;
        abyte1;
        JVM INSTR monitorexit ;
        abyte1 = new byte[j];
        System.arraycopy(abyte0, i, abyte1, 0, j);
        mDispatcher.dispatchOnAudioAvailable(abyte1);
        synthesisplaybackqueueitem.put(abyte1);
        mLogger.onEngineDataReceived();
        return 0;
        abyte0;
        throw abyte0;
        abyte0;
        abyte0 = ((byte []) (mStateLock));
        abyte0;
        JVM INSTR monitorenter ;
        mStatusCode = -5;
        abyte0;
        JVM INSTR monitorexit ;
        return -1;
        Exception exception;
        exception;
        throw exception;
    }

    public int done()
    {
        Object obj = mStateLock;
        obj;
        JVM INSTR monitorenter ;
        if(!mDone)
            break MISSING_BLOCK_LABEL_26;
        Log.w("PlaybackSynthesisRequest", "Duplicate call to done()");
        obj;
        JVM INSTR monitorexit ;
        return -1;
        int i;
        if(mStatusCode != -2)
            break MISSING_BLOCK_LABEL_44;
        i = errorCodeOnStop();
        obj;
        JVM INSTR monitorexit ;
        return i;
        mDone = true;
        if(mItem != null)
            break MISSING_BLOCK_LABEL_112;
        Log.w("PlaybackSynthesisRequest", "done() was called before start() call");
        if(mStatusCode != 0)
            break MISSING_BLOCK_LABEL_91;
        mDispatcher.dispatchOnSuccess();
_L1:
        mLogger.onEngineComplete();
        obj;
        JVM INSTR monitorexit ;
        return -1;
        mDispatcher.dispatchOnError(mStatusCode);
          goto _L1
        Exception exception;
        exception;
        throw exception;
        SynthesisPlaybackQueueItem synthesisplaybackqueueitem;
        synthesisplaybackqueueitem = mItem;
        i = mStatusCode;
        obj;
        JVM INSTR monitorexit ;
        if(i == 0)
            synthesisplaybackqueueitem.done();
        else
            synthesisplaybackqueueitem.stop(i);
        mLogger.onEngineComplete();
        return 0;
    }

    public void error()
    {
        error(-3);
    }

    public void error(int i)
    {
        Object obj = mStateLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mDone;
        if(!flag)
            break MISSING_BLOCK_LABEL_19;
        obj;
        JVM INSTR monitorexit ;
        return;
        mStatusCode = i;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public int getMaxBufferSize()
    {
        return 8192;
    }

    public boolean hasFinished()
    {
        Object obj = mStateLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mDone;
        obj;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean hasStarted()
    {
        Object obj = mStateLock;
        obj;
        JVM INSTR monitorenter ;
        SynthesisPlaybackQueueItem synthesisplaybackqueueitem = mItem;
        boolean flag;
        if(synthesisplaybackqueueitem != null)
            flag = true;
        else
            flag = false;
        obj;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public void rangeStart(int i, int j, int k)
    {
        if(mItem == null)
        {
            Log.e("PlaybackSynthesisRequest", "mItem is null");
            return;
        } else
        {
            mItem.rangeStart(i, j, k);
            return;
        }
    }

    public int start(int i, int j, int k)
    {
        int l;
        if(j != 3 && j != 2 && j != 4)
            Log.w("PlaybackSynthesisRequest", (new StringBuilder()).append("Audio format encoding ").append(j).append(" not supported. Please use one ").append("of AudioFormat.ENCODING_PCM_8BIT, AudioFormat.ENCODING_PCM_16BIT or ").append("AudioFormat.ENCODING_PCM_FLOAT").toString());
        mDispatcher.dispatchOnBeginSynthesis(i, j, k);
        l = BlockingAudioTrack.getChannelConfig(k);
        Object obj = mStateLock;
        obj;
        JVM INSTR monitorenter ;
        if(l != 0)
            break MISSING_BLOCK_LABEL_128;
        StringBuilder stringbuilder = JVM INSTR new #70  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e("PlaybackSynthesisRequest", stringbuilder.append("Unsupported number of channels :").append(k).toString());
        mStatusCode = -5;
        obj;
        JVM INSTR monitorexit ;
        return -1;
        if(mStatusCode != -2)
            break MISSING_BLOCK_LABEL_147;
        i = errorCodeOnStop();
        obj;
        JVM INSTR monitorexit ;
        return i;
        l = mStatusCode;
        if(l == 0)
            break MISSING_BLOCK_LABEL_163;
        obj;
        JVM INSTR monitorexit ;
        return -1;
        if(mItem == null)
            break MISSING_BLOCK_LABEL_183;
        Log.e("PlaybackSynthesisRequest", "Start called twice");
        obj;
        JVM INSTR monitorexit ;
        return -1;
        SynthesisPlaybackQueueItem synthesisplaybackqueueitem = JVM INSTR new #106 <Class SynthesisPlaybackQueueItem>;
        synthesisplaybackqueueitem.SynthesisPlaybackQueueItem(mAudioParams, i, j, k, mDispatcher, mCallerIdentity, mLogger);
        mAudioTrackHandler.enqueue(synthesisplaybackqueueitem);
        mItem = synthesisplaybackqueueitem;
        obj;
        JVM INSTR monitorexit ;
        return 0;
        Exception exception;
        exception;
        throw exception;
    }

    void stop()
    {
        Object obj = mStateLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mDone;
        if(!flag)
            break MISSING_BLOCK_LABEL_19;
        obj;
        JVM INSTR monitorexit ;
        return;
        if(mStatusCode != -2)
            break MISSING_BLOCK_LABEL_39;
        Log.w("PlaybackSynthesisRequest", "stop() called twice");
        obj;
        JVM INSTR monitorexit ;
        return;
        SynthesisPlaybackQueueItem synthesisplaybackqueueitem;
        synthesisplaybackqueueitem = mItem;
        mStatusCode = -2;
        obj;
        JVM INSTR monitorexit ;
        Exception exception;
        if(synthesisplaybackqueueitem != null)
        {
            synthesisplaybackqueueitem.stop(-2);
        } else
        {
            mLogger.onCompleted(-2);
            mDispatcher.dispatchOnStop();
        }
        return;
        exception;
        throw exception;
    }

    private static final boolean DBG = false;
    private static final int MIN_AUDIO_BUFFER_SIZE = 8192;
    private static final String TAG = "PlaybackSynthesisRequest";
    private final TextToSpeechService.AudioOutputParams mAudioParams;
    private final AudioPlaybackHandler mAudioTrackHandler;
    private final Object mCallerIdentity;
    private final TextToSpeechService.UtteranceProgressDispatcher mDispatcher;
    private volatile boolean mDone;
    private SynthesisPlaybackQueueItem mItem;
    private final AbstractEventLogger mLogger;
    private final Object mStateLock = new Object();
    protected int mStatusCode;
}

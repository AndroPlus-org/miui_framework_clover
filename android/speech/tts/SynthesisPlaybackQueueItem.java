// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech.tts;

import android.media.AudioTrack;
import android.util.Log;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.*;

// Referenced classes of package android.speech.tts:
//            PlaybackQueueItem, BlockingAudioTrack, AbstractEventLogger

final class SynthesisPlaybackQueueItem extends PlaybackQueueItem
    implements android.media.AudioTrack.OnPlaybackPositionUpdateListener
{
    static final class ListEntry
    {

        final byte mBytes[];

        ListEntry(byte abyte0[])
        {
            mBytes = abyte0;
        }
    }

    private class ProgressMarker
    {

        public final int end;
        public final int frames;
        public final int start;
        final SynthesisPlaybackQueueItem this$0;

        public ProgressMarker(int i, int j, int k)
        {
            this$0 = SynthesisPlaybackQueueItem.this;
            super();
            frames = i;
            start = j;
            end = k;
        }
    }


    SynthesisPlaybackQueueItem(TextToSpeechService.AudioOutputParams audiooutputparams, int i, int j, int k, TextToSpeechService.UtteranceProgressDispatcher utteranceprogressdispatcher, Object obj, AbstractEventLogger abstracteventlogger)
    {
        super(utteranceprogressdispatcher, obj);
        mReadReady = mListLock.newCondition();
        mNotFull = mListLock.newCondition();
        markerList = new ConcurrentLinkedQueue();
        mUnconsumedBytes = 0;
        mStopped = false;
        mDone = false;
        mStatusCode = 0;
        mAudioTrack = new BlockingAudioTrack(audiooutputparams, i, j, k);
        mLogger = abstracteventlogger;
    }

    private byte[] take()
        throws InterruptedException
    {
        mListLock.lock();
        for(; mDataBufferList.size() == 0 && mStopped ^ true && mDone ^ true; mReadReady.await());
        break MISSING_BLOCK_LABEL_61;
        Exception exception;
        exception;
        mListLock.unlock();
        throw exception;
        boolean flag = mStopped;
        if(flag)
        {
            mListLock.unlock();
            return null;
        }
        ListEntry listentry = (ListEntry)mDataBufferList.poll();
        if(listentry == null)
        {
            mListLock.unlock();
            return null;
        }
        byte abyte0[];
        mUnconsumedBytes = mUnconsumedBytes - listentry.mBytes.length;
        mNotFull.signal();
        abyte0 = listentry.mBytes;
        mListLock.unlock();
        return abyte0;
    }

    void done()
    {
        mListLock.lock();
        mDone = true;
        mReadReady.signal();
        mNotFull.signal();
        mListLock.unlock();
        return;
        Exception exception;
        exception;
        mListLock.unlock();
        throw exception;
    }

    public void onMarkerReached(AudioTrack audiotrack)
    {
        audiotrack = (ProgressMarker)markerList.poll();
        if(audiotrack == null)
        {
            Log.e("TTS.SynthQueueItem", "onMarkerReached reached called but no marker in queue");
            return;
        } else
        {
            getDispatcher().dispatchOnRangeStart(((ProgressMarker) (audiotrack)).start, ((ProgressMarker) (audiotrack)).end, ((ProgressMarker) (audiotrack)).frames);
            updateMarker();
            return;
        }
    }

    public void onPeriodicNotification(AudioTrack audiotrack)
    {
    }

    void put(byte abyte0[])
        throws InterruptedException
    {
        mListLock.lock();
        for(; mAudioTrack.getAudioLengthMs(mUnconsumedBytes) > 500L && mStopped ^ true; mNotFull.await());
        break MISSING_BLOCK_LABEL_60;
        abyte0;
        mListLock.unlock();
        throw abyte0;
        boolean flag = mStopped;
        if(flag)
        {
            mListLock.unlock();
            return;
        }
        LinkedList linkedlist = mDataBufferList;
        ListEntry listentry = JVM INSTR new #8   <Class SynthesisPlaybackQueueItem$ListEntry>;
        listentry.ListEntry(abyte0);
        linkedlist.add(listentry);
        mUnconsumedBytes = mUnconsumedBytes + abyte0.length;
        mReadReady.signal();
        mListLock.unlock();
        return;
    }

    void rangeStart(int i, int j, int k)
    {
        markerList.add(new ProgressMarker(i, j, k));
        updateMarker();
    }

    public void run()
    {
        TextToSpeechService.UtteranceProgressDispatcher utteranceprogressdispatcher;
        utteranceprogressdispatcher = getDispatcher();
        utteranceprogressdispatcher.dispatchOnStart();
        if(!mAudioTrack.init())
        {
            utteranceprogressdispatcher.dispatchOnError(-5);
            return;
        }
        mAudioTrack.setPlaybackPositionUpdateListener(this);
        updateMarker();
_L1:
        byte abyte0[] = take();
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_71;
        mAudioTrack.write(abyte0);
        mLogger.onAudioDataWritten();
          goto _L1
        InterruptedException interruptedexception;
        interruptedexception;
        mAudioTrack.waitAndRelease();
        if(mStatusCode == 0)
            utteranceprogressdispatcher.dispatchOnSuccess();
        else
        if(mStatusCode == -2)
            utteranceprogressdispatcher.dispatchOnStop();
        else
            utteranceprogressdispatcher.dispatchOnError(mStatusCode);
        mLogger.onCompleted(mStatusCode);
        return;
    }

    void stop(int i)
    {
        mListLock.lock();
        mStopped = true;
        mStatusCode = i;
        mReadReady.signal();
        mNotFull.signal();
        mListLock.unlock();
        mAudioTrack.stop();
        return;
        Exception exception;
        exception;
        mListLock.unlock();
        throw exception;
    }

    void updateMarker()
    {
        ProgressMarker progressmarker = (ProgressMarker)markerList.peek();
        if(progressmarker != null)
        {
            int i;
            if(progressmarker.frames == 0)
                i = 1;
            else
                i = progressmarker.frames;
            mAudioTrack.setNotificationMarkerPosition(i);
        }
    }

    private static final boolean DBG = false;
    private static final long MAX_UNCONSUMED_AUDIO_MS = 500L;
    private static final String TAG = "TTS.SynthQueueItem";
    private final BlockingAudioTrack mAudioTrack;
    private final LinkedList mDataBufferList = new LinkedList();
    private volatile boolean mDone;
    private final Lock mListLock = new ReentrantLock();
    private final AbstractEventLogger mLogger;
    private final Condition mNotFull;
    private final Condition mReadReady;
    private volatile int mStatusCode;
    private volatile boolean mStopped;
    private int mUnconsumedBytes;
    private ConcurrentLinkedQueue markerList;
}

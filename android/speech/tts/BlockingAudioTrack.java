// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech.tts;

import android.media.AudioFormat;
import android.media.AudioTrack;
import android.util.Log;

class BlockingAudioTrack
{

    BlockingAudioTrack(TextToSpeechService.AudioOutputParams audiooutputparams, int i, int j, int k)
    {
        mBytesWritten = 0;
        mAudioTrackLock = new Object();
        mAudioParams = audiooutputparams;
        mSampleRateInHz = i;
        mAudioFormat = j;
        mChannelCount = k;
        mBytesPerFrame = AudioFormat.getBytesPerSample(mAudioFormat) * mChannelCount;
        mIsShortUtterance = false;
        mAudioBufferSize = 0;
        mBytesWritten = 0;
        mAudioTrack = null;
        mStopped = false;
    }

    private void blockUntilCompletion(AudioTrack audiotrack)
    {
        int i;
        int j;
        long l;
        i = mBytesWritten / mBytesPerFrame;
        j = -1;
        l = 0L;
_L7:
        int k = audiotrack.getPlaybackHeadPosition();
        if(k >= i || audiotrack.getPlayState() != 3 || !(mStopped ^ true)) goto _L2; else goto _L1
_L1:
        long l1 = clip(((i - k) * 1000) / audiotrack.getSampleRate(), 20L, 2500L);
        if(k != j) goto _L4; else goto _L3
_L3:
        long l2;
        l2 = l + l1;
        l = l2;
        if(l2 <= 2500L) goto _L6; else goto _L5
_L5:
        Log.w("TTS.BlockingAudioTrack", "Waited unsuccessfully for 2500ms for AudioTrack to make progress, Aborting");
_L2:
        return;
_L4:
        l = 0L;
_L6:
        j = k;
        Thread.sleep(l1);
          goto _L7
        audiotrack;
          goto _L2
    }

    private void blockUntilDone(AudioTrack audiotrack)
    {
        if(mBytesWritten <= 0)
            return;
        if(mIsShortUtterance)
            blockUntilEstimatedCompletion();
        else
            blockUntilCompletion(audiotrack);
    }

    private void blockUntilEstimatedCompletion()
    {
        long l = ((mBytesWritten / mBytesPerFrame) * 1000) / mSampleRateInHz;
        Thread.sleep(l);
_L2:
        return;
        InterruptedException interruptedexception;
        interruptedexception;
        if(true) goto _L2; else goto _L1
_L1:
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

    private static final long clip(long l, long l1, long l2)
    {
        if(l >= l1)
            if(l < l2)
                l1 = l;
            else
                l1 = l2;
        return l1;
    }

    private AudioTrack createStreamingAudioTrack()
    {
        int i = getChannelConfig(mChannelCount);
        int j = Math.max(8192, AudioTrack.getMinBufferSize(mSampleRateInHz, i, mAudioFormat));
        Object obj = (new android.media.AudioFormat.Builder()).setChannelMask(i).setEncoding(mAudioFormat).setSampleRate(mSampleRateInHz).build();
        obj = new AudioTrack(mAudioParams.mAudioAttributes, ((AudioFormat) (obj)), j, 1, mAudioParams.mSessionId);
        if(((AudioTrack) (obj)).getState() != 1)
        {
            Log.w("TTS.BlockingAudioTrack", "Unable to create audio track.");
            ((AudioTrack) (obj)).release();
            return null;
        } else
        {
            mAudioBufferSize = j;
            setupVolume(((AudioTrack) (obj)), mAudioParams.mVolume, mAudioParams.mPan);
            return ((AudioTrack) (obj));
        }
    }

    static int getChannelConfig(int i)
    {
        if(i == 1)
            return 4;
        return i != 2 ? 0 : 12;
    }

    private static void setupVolume(AudioTrack audiotrack, float f, float f1)
    {
        float f2;
        float f3;
        float f4;
        f = clip(f, 0.0F, 1.0F);
        f2 = clip(f1, -1F, 1.0F);
        f3 = f;
        f4 = f;
        if(f2 <= 0.0F) goto _L2; else goto _L1
_L1:
        f1 = f * (1.0F - f2);
_L4:
        if(audiotrack.setStereoVolume(f1, f4) != 0)
            Log.e("TTS.BlockingAudioTrack", "Failed to set volume");
        return;
_L2:
        f1 = f3;
        if(f2 < 0.0F)
        {
            f4 = f * (1.0F + f2);
            f1 = f3;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static int writeToAudioTrack(AudioTrack audiotrack, byte abyte0[])
    {
        if(audiotrack.getPlayState() != 3)
            audiotrack.play();
        int i = 0;
        do
        {
            int j;
label0:
            {
                if(i < abyte0.length)
                {
                    j = audiotrack.write(abyte0, i, abyte0.length);
                    if(j > 0)
                        break label0;
                }
                return i;
            }
            i += j;
        } while(true);
    }

    long getAudioLengthMs(int i)
    {
        return (long)(((i / mBytesPerFrame) * 1000) / mSampleRateInHz);
    }

    public boolean init()
    {
        AudioTrack audiotrack = createStreamingAudioTrack();
        Object obj = mAudioTrackLock;
        obj;
        JVM INSTR monitorenter ;
        mAudioTrack = audiotrack;
        obj;
        JVM INSTR monitorexit ;
        Exception exception;
        return audiotrack != null;
        exception;
        throw exception;
    }

    public void setNotificationMarkerPosition(int i)
    {
        Object obj = mAudioTrackLock;
        obj;
        JVM INSTR monitorenter ;
        if(mAudioTrack != null)
            mAudioTrack.setNotificationMarkerPosition(i);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setPlaybackPositionUpdateListener(android.media.AudioTrack.OnPlaybackPositionUpdateListener onplaybackpositionupdatelistener)
    {
        Object obj = mAudioTrackLock;
        obj;
        JVM INSTR monitorenter ;
        if(mAudioTrack != null)
            mAudioTrack.setPlaybackPositionUpdateListener(onplaybackpositionupdatelistener);
        obj;
        JVM INSTR monitorexit ;
        return;
        onplaybackpositionupdatelistener;
        throw onplaybackpositionupdatelistener;
    }

    public void stop()
    {
        Object obj = mAudioTrackLock;
        obj;
        JVM INSTR monitorenter ;
        if(mAudioTrack != null)
            mAudioTrack.stop();
        mStopped = true;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void waitAndRelease()
    {
        Object obj = mAudioTrackLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = mAudioTrack;
        obj;
        JVM INSTR monitorexit ;
        if(obj1 == null)
            return;
        break MISSING_BLOCK_LABEL_24;
        obj1;
        throw obj1;
        if(mBytesWritten < mAudioBufferSize && mStopped ^ true)
        {
            mIsShortUtterance = true;
            ((AudioTrack) (obj1)).stop();
        }
        if(!mStopped)
            blockUntilDone(mAudioTrack);
        obj = mAudioTrackLock;
        obj;
        JVM INSTR monitorenter ;
        mAudioTrack = null;
        obj;
        JVM INSTR monitorexit ;
        ((AudioTrack) (obj1)).release();
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public int write(byte abyte0[])
    {
        Object obj = mAudioTrackLock;
        obj;
        JVM INSTR monitorenter ;
        AudioTrack audiotrack = mAudioTrack;
        obj;
        JVM INSTR monitorexit ;
        if(audiotrack == null || mStopped)
        {
            return -1;
        } else
        {
            int i = writeToAudioTrack(audiotrack, abyte0);
            mBytesWritten = mBytesWritten + i;
            return i;
        }
        abyte0;
        throw abyte0;
    }

    private static final boolean DBG = false;
    private static final long MAX_PROGRESS_WAIT_MS = 2500L;
    private static final long MAX_SLEEP_TIME_MS = 2500L;
    private static final int MIN_AUDIO_BUFFER_SIZE = 8192;
    private static final long MIN_SLEEP_TIME_MS = 20L;
    private static final String TAG = "TTS.BlockingAudioTrack";
    private int mAudioBufferSize;
    private final int mAudioFormat;
    private final TextToSpeechService.AudioOutputParams mAudioParams;
    private AudioTrack mAudioTrack;
    private Object mAudioTrackLock;
    private final int mBytesPerFrame;
    private int mBytesWritten;
    private final int mChannelCount;
    private boolean mIsShortUtterance;
    private final int mSampleRateInHz;
    private int mSessionId;
    private volatile boolean mStopped;
}

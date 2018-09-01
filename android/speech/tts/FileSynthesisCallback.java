// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech.tts;

import android.media.AudioFormat;
import android.util.Log;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

// Referenced classes of package android.speech.tts:
//            AbstractSynthesisCallback

class FileSynthesisCallback extends AbstractSynthesisCallback
{

    FileSynthesisCallback(FileChannel filechannel, TextToSpeechService.UtteranceProgressDispatcher utteranceprogressdispatcher, boolean flag)
    {
        super(flag);
        mStarted = false;
        mDone = false;
        mFileChannel = filechannel;
        mDispatcher = utteranceprogressdispatcher;
        mStatusCode = 0;
    }

    private void cleanUp()
    {
        closeFile();
    }

    private void closeFile()
    {
        mFileChannel = null;
    }

    private ByteBuffer makeWavHeader(int i, int j, int k, int l)
    {
        j = AudioFormat.getBytesPerSample(j);
        short word0 = (short)(j * k);
        short word1 = (short)(j * 8);
        ByteBuffer bytebuffer = ByteBuffer.wrap(new byte[44]);
        bytebuffer.order(ByteOrder.LITTLE_ENDIAN);
        bytebuffer.put(new byte[] {
            82, 73, 70, 70
        });
        bytebuffer.putInt((l + 44) - 8);
        bytebuffer.put(new byte[] {
            87, 65, 86, 69
        });
        bytebuffer.put(new byte[] {
            102, 109, 116, 32
        });
        bytebuffer.putInt(16);
        bytebuffer.putShort((short)1);
        bytebuffer.putShort((short)k);
        bytebuffer.putInt(i);
        bytebuffer.putInt(i * j * k);
        bytebuffer.putShort(word0);
        bytebuffer.putShort(word1);
        bytebuffer.put(new byte[] {
            100, 97, 116, 97
        });
        bytebuffer.putInt(l);
        bytebuffer.flip();
        return bytebuffer;
    }

    public int audioAvailable(byte abyte0[], int i, int j)
    {
        byte abyte1[] = ((byte []) (mStateLock));
        abyte1;
        JVM INSTR monitorenter ;
        if(mStatusCode != -2)
            break MISSING_BLOCK_LABEL_28;
        i = errorCodeOnStop();
        abyte1;
        JVM INSTR monitorexit ;
        return i;
        int k = mStatusCode;
        if(k == 0)
            break MISSING_BLOCK_LABEL_44;
        abyte1;
        JVM INSTR monitorexit ;
        return -1;
        if(mFileChannel != null)
            break MISSING_BLOCK_LABEL_70;
        Log.e("FileSynthesisRequest", "File not open");
        mStatusCode = -5;
        abyte1;
        JVM INSTR monitorexit ;
        return -1;
        if(mStarted)
            break MISSING_BLOCK_LABEL_90;
        Log.e("FileSynthesisRequest", "Start method was not called");
        abyte1;
        JVM INSTR monitorexit ;
        return -1;
        FileChannel filechannel = mFileChannel;
        abyte1;
        JVM INSTR monitorexit ;
        abyte1 = new byte[j];
        System.arraycopy(abyte0, i, abyte1, 0, j);
        mDispatcher.dispatchOnAudioAvailable(abyte1);
        filechannel.write(ByteBuffer.wrap(abyte0, i, j));
        return 0;
        abyte0;
        throw abyte0;
        abyte0;
        Log.e("FileSynthesisRequest", "Failed to write to output file descriptor", abyte0);
        abyte0 = ((byte []) (mStateLock));
        abyte0;
        JVM INSTR monitorenter ;
        cleanUp();
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
        Log.w("FileSynthesisRequest", "Duplicate call to done()");
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
        if(mStatusCode == 0 || mStatusCode == -2)
            break MISSING_BLOCK_LABEL_77;
        mDispatcher.dispatchOnError(mStatusCode);
        obj;
        JVM INSTR monitorexit ;
        return -1;
        if(mFileChannel != null)
            break MISSING_BLOCK_LABEL_96;
        Log.e("FileSynthesisRequest", "File not open");
        obj;
        JVM INSTR monitorexit ;
        return -1;
        Object obj2;
        int j;
        int k;
        mDone = true;
        obj2 = mFileChannel;
        i = mSampleRateInHz;
        j = mAudioFormat;
        k = mChannelCount;
        obj;
        JVM INSTR monitorexit ;
        ((FileChannel) (obj2)).position(0L);
        ((FileChannel) (obj2)).write(makeWavHeader(i, j, k, (int)(((FileChannel) (obj2)).size() - 44L)));
        obj = mStateLock;
        obj;
        JVM INSTR monitorenter ;
        closeFile();
        mDispatcher.dispatchOnSuccess();
        obj;
        JVM INSTR monitorexit ;
        return 0;
        Exception exception;
        exception;
        throw exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        Object obj1;
        obj1;
        Log.e("FileSynthesisRequest", "Failed to write to output file descriptor", ((Throwable) (obj1)));
        exception = ((Exception) (mStateLock));
        exception;
        JVM INSTR monitorenter ;
        cleanUp();
        exception;
        JVM INSTR monitorexit ;
        return -1;
        obj1;
        throw obj1;
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
        cleanUp();
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
        boolean flag = mStarted;
        obj;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public void rangeStart(int i, int j, int k)
    {
        mDispatcher.dispatchOnRangeStart(i, j, k);
    }

    public int start(int i, int j, int k)
    {
        if(j != 3 && j != 2 && j != 4)
            Log.e("FileSynthesisRequest", (new StringBuilder()).append("Audio format encoding ").append(j).append(" not supported. Please use one ").append("of AudioFormat.ENCODING_PCM_8BIT, AudioFormat.ENCODING_PCM_16BIT or ").append("AudioFormat.ENCODING_PCM_FLOAT").toString());
        mDispatcher.dispatchOnBeginSynthesis(i, j, k);
        Object obj = mStateLock;
        obj;
        JVM INSTR monitorenter ;
        if(mStatusCode != -2)
            break MISSING_BLOCK_LABEL_95;
        i = errorCodeOnStop();
        obj;
        JVM INSTR monitorexit ;
        return i;
        int l = mStatusCode;
        if(l == 0)
            break MISSING_BLOCK_LABEL_111;
        obj;
        JVM INSTR monitorexit ;
        return -1;
        if(!mStarted)
            break MISSING_BLOCK_LABEL_131;
        Log.e("FileSynthesisRequest", "Start called twice");
        obj;
        JVM INSTR monitorexit ;
        return -1;
        FileChannel filechannel;
        mStarted = true;
        mSampleRateInHz = i;
        mAudioFormat = j;
        mChannelCount = k;
        mDispatcher.dispatchOnStart();
        filechannel = mFileChannel;
        obj;
        JVM INSTR monitorexit ;
        filechannel.write(ByteBuffer.allocate(44));
        return 0;
        Exception exception;
        exception;
        throw exception;
        IOException ioexception;
        ioexception;
        Log.e("FileSynthesisRequest", "Failed to write wav header to output file descriptor", ioexception);
        ioexception = ((IOException) (mStateLock));
        ioexception;
        JVM INSTR monitorenter ;
        cleanUp();
        mStatusCode = -5;
        ioexception;
        JVM INSTR monitorexit ;
        return -1;
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
        int i = mStatusCode;
        if(i != -2)
            break MISSING_BLOCK_LABEL_33;
        obj;
        JVM INSTR monitorexit ;
        return;
        mStatusCode = -2;
        cleanUp();
        mDispatcher.dispatchOnStop();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static final boolean DBG = false;
    private static final int MAX_AUDIO_BUFFER_SIZE = 8192;
    private static final String TAG = "FileSynthesisRequest";
    private static final short WAV_FORMAT_PCM = 1;
    private static final int WAV_HEADER_LENGTH = 44;
    private int mAudioFormat;
    private int mChannelCount;
    private final TextToSpeechService.UtteranceProgressDispatcher mDispatcher;
    private boolean mDone;
    private FileChannel mFileChannel;
    private int mSampleRateInHz;
    private boolean mStarted;
    private final Object mStateLock = new Object();
    protected int mStatusCode;
}

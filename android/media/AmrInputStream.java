// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

// Referenced classes of package android.media:
//            MediaFormat, MediaCodecList, MediaCodec

public final class AmrInputStream extends InputStream
{

    public AmrInputStream(InputStream inputstream)
    {
        mBufIn = 0;
        mBufOut = 0;
        mOneByte = new byte[1];
        mInputStream = inputstream;
        inputstream = new MediaFormat();
        inputstream.setString("mime", "audio/3gpp");
        inputstream.setInteger("sample-rate", 8000);
        inputstream.setInteger("channel-count", 1);
        inputstream.setInteger("bitrate", 12200);
        String s = (new MediaCodecList(0)).findEncoderForFormat(inputstream);
        if(s != null)
            try
            {
                mCodec = MediaCodec.createByCodecName(s);
                mCodec.configure(inputstream, null, null, 1);
                mCodec.start();
            }
            // Misplaced declaration of an exception variable
            catch(InputStream inputstream)
            {
                if(mCodec != null)
                    mCodec.release();
                mCodec = null;
            }
        mInfo = new MediaCodec.BufferInfo();
    }

    public void close()
        throws IOException
    {
        if(mInputStream != null)
            mInputStream.close();
        mInputStream = null;
        if(mCodec != null)
            mCodec.release();
        mCodec = null;
        return;
        Exception exception;
        exception;
        mCodec = null;
        throw exception;
        exception;
        mInputStream = null;
        if(mCodec != null)
            mCodec.release();
        mCodec = null;
        throw exception;
        exception;
        mCodec = null;
        throw exception;
    }

    protected void finalize()
        throws Throwable
    {
        if(mCodec != null)
        {
            Log.w("AmrInputStream", "AmrInputStream wasn't closed");
            mCodec.release();
        }
    }

    public int read()
        throws IOException
    {
        int i;
        if(read(mOneByte, 0, 1) == 1)
            i = mOneByte[0] & 0xff;
        else
            i = -1;
        return i;
    }

    public int read(byte abyte0[])
        throws IOException
    {
        return read(abyte0, 0, abyte0.length);
    }

    public int read(byte abyte0[], int i, int j)
        throws IOException
    {
        if(mCodec == null)
            throw new IllegalStateException("not open");
        if(mBufOut < mBufIn || !(mSawOutputEOS ^ true)) goto _L2; else goto _L1
_L1:
        mBufOut = 0;
        mBufIn = 0;
_L7:
        if(mSawInputEOS) goto _L4; else goto _L3
_L3:
        int k = mCodec.dequeueInputBuffer(0L);
        if(k >= 0) goto _L5; else goto _L4
_L4:
        int l;
        do
            l = mCodec.dequeueOutputBuffer(mInfo, -1L);
        while(l < 0);
        mBufIn = mInfo.size;
        mCodec.getOutputBuffer(l).get(mBuf, 0, mBufIn);
        mCodec.releaseOutputBuffer(l, false);
        if((mInfo.flags & 4) != 0)
            mSawOutputEOS = true;
          goto _L2
_L5:
        int j1 = 0;
        do
        {
label0:
            {
                int k1;
                if(j1 < 320)
                {
                    k1 = mInputStream.read(mBuf, j1, 320 - j1);
                    if(k1 != -1)
                        break label0;
                    mSawInputEOS = true;
                }
                mCodec.getInputBuffer(k).put(mBuf, 0, j1);
                MediaCodec mediacodec = mCodec;
                if(mSawInputEOS)
                    k1 = 4;
                else
                    k1 = 0;
                mediacodec.queueInputBuffer(k, 0, j1, 0L, k1);
                continue; /* Loop/switch isn't completed */
            }
            j1 += k1;
        } while(true);
_L2:
        if(mBufOut < mBufIn)
        {
            int i1 = j;
            if(j > mBufIn - mBufOut)
                i1 = mBufIn - mBufOut;
            System.arraycopy(mBuf, mBufOut, abyte0, i, i1);
            mBufOut = mBufOut + i1;
            return i1;
        }
        if(mSawInputEOS && mSawOutputEOS)
            return -1;
        return 0;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private static final int SAMPLES_PER_FRAME = 160;
    private static final String TAG = "AmrInputStream";
    private final byte mBuf[] = new byte[320];
    private int mBufIn;
    private int mBufOut;
    MediaCodec mCodec;
    MediaCodec.BufferInfo mInfo;
    private InputStream mInputStream;
    private byte mOneByte[];
    boolean mSawInputEOS;
    boolean mSawOutputEOS;
}

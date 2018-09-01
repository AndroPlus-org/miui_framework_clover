// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import java.io.IOException;
import java.io.InputStream;

public final class ResampleInputStream extends InputStream
{

    public ResampleInputStream(InputStream inputstream, int i, int j)
    {
        if(i != j * 2)
        {
            throw new IllegalArgumentException("only support 2:1 at the moment");
        } else
        {
            mInputStream = inputstream;
            mRateIn = 2;
            mRateOut = 1;
            return;
        }
    }

    private static native void fir21(byte abyte0[], int i, byte abyte1[], int j, int k);

    public void close()
        throws IOException
    {
        if(mInputStream != null)
            mInputStream.close();
        mInputStream = null;
        return;
        Exception exception;
        exception;
        mInputStream = null;
        throw exception;
    }

    protected void finalize()
        throws Throwable
    {
        if(mInputStream != null)
        {
            close();
            throw new IllegalStateException("someone forgot to close ResampleInputStream");
        } else
        {
            return;
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
        if(mInputStream == null)
            throw new IllegalStateException("not open");
        int k = (((j / 2) * mRateIn) / mRateOut + 29) * 2;
        if(mBuf == null)
        {
            mBuf = new byte[k];
            break MISSING_BLOCK_LABEL_52;
        } else
        {
            if(k > mBuf.length)
            {
                byte abyte1[] = new byte[k];
                System.arraycopy(mBuf, 0, abyte1, 0, mBufCount);
                mBuf = abyte1;
            }
            continue;
        }
        do
        {
            k = (((mBufCount / 2 - 29) * mRateOut) / mRateIn) * 2;
            if(k > 0)
            {
                if(k < j)
                    j = k;
                else
                    j = (j / 2) * 2;
                fir21(mBuf, 0, abyte0, i, j / 2);
                i = (mRateIn * j) / mRateOut;
                mBufCount = mBufCount - i;
                if(mBufCount > 0)
                    System.arraycopy(mBuf, i, mBuf, 0, mBufCount);
                return j;
            }
            int l = mInputStream.read(mBuf, mBufCount, mBuf.length - mBufCount);
            if(l == -1)
                return -1;
            mBufCount = mBufCount + l;
        } while(true);
    }

    private static final String TAG = "ResampleInputStream";
    private static final int mFirLength = 29;
    private byte mBuf[];
    private int mBufCount;
    private InputStream mInputStream;
    private final byte mOneByte[] = new byte[1];
    private final int mRateIn;
    private final int mRateOut;

    static 
    {
        System.loadLibrary("media_jni");
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util.proto;

import android.util.Log;
import java.util.ArrayList;

// Referenced classes of package android.util.proto:
//            ProtoParseException

public final class EncodedBuffer
{

    public EncodedBuffer()
    {
        this(0);
    }

    public EncodedBuffer(int i)
    {
        mBuffers = new ArrayList();
        mReadLimit = -1;
        mReadableSize = -1;
        int j = i;
        if(i <= 0)
            j = 8192;
        mChunkSize = j;
        mWriteBuffer = new byte[mChunkSize];
        mBuffers.add(mWriteBuffer);
        mBufferCount = 1;
    }

    private static int dumpByteString(String s, String s1, int i, byte abyte0[])
    {
        StringBuffer stringbuffer = new StringBuffer();
        int j = abyte0.length;
        int k = 0;
        while(k < j) 
        {
            byte byte0;
            byte byte1;
            if(k % 16 == 0)
            {
                StringBuffer stringbuffer1 = stringbuffer;
                if(k != 0)
                {
                    Log.d(s, stringbuffer.toString());
                    stringbuffer1 = new StringBuffer();
                }
                stringbuffer1.append(s1);
                stringbuffer1.append('[');
                stringbuffer1.append(i + k);
                stringbuffer1.append(']');
                stringbuffer1.append(' ');
                stringbuffer = stringbuffer1;
            } else
            {
                stringbuffer.append(' ');
            }
            byte0 = abyte0[k];
            byte1 = (byte)(byte0 >> 4 & 0xf);
            if(byte1 < 10)
                stringbuffer.append((char)(byte1 + 48));
            else
                stringbuffer.append((char)(byte1 + 87));
            byte0 &= 0xf;
            if(byte0 < 10)
                stringbuffer.append((char)(byte0 + 48));
            else
                stringbuffer.append((char)(byte0 + 87));
            k++;
        }
        Log.d(s, stringbuffer.toString());
        return j;
    }

    public static void dumpByteString(String s, String s1, byte abyte0[])
    {
        dumpByteString(s, s1, 0, abyte0);
    }

    public static int getRawVarint32Size(int i)
    {
        if((i & 0xffffff80) == 0)
            return 1;
        if((i & 0xffffc000) == 0)
            return 2;
        if((0xffe00000 & i) == 0)
            return 3;
        return (0xf0000000 & i) != 0 ? 5 : 4;
    }

    public static int getRawVarint64Size(long l)
    {
        if((-128L & l) == 0L)
            return 1;
        if((-16384L & l) == 0L)
            return 2;
        if((0xffffffffffe00000L & l) == 0L)
            return 3;
        if((0xfffffffff0000000L & l) == 0L)
            return 4;
        if((0xfffffff800000000L & l) == 0L)
            return 5;
        if((0xfffffc0000000000L & l) == 0L)
            return 6;
        if((0xfffe000000000000L & l) == 0L)
            return 7;
        if((0xff00000000000000L & l) == 0L)
            return 8;
        return (0x8000000000000000L & l) != 0L ? 10 : 9;
    }

    public static int getRawZigZag32Size(int i)
    {
        return getRawVarint32Size(zigZag32(i));
    }

    public static int getRawZigZag64Size(long l)
    {
        return getRawVarint64Size(zigZag64(l));
    }

    private void nextWriteBuffer()
    {
        mWriteBufIndex = mWriteBufIndex + 1;
        if(mWriteBufIndex >= mBufferCount)
        {
            mWriteBuffer = new byte[mChunkSize];
            mBuffers.add(mWriteBuffer);
            mBufferCount = mBufferCount + 1;
        } else
        {
            mWriteBuffer = (byte[])mBuffers.get(mWriteBufIndex);
        }
        mWriteIndex = 0;
    }

    private static int zigZag32(int i)
    {
        return i << 1 ^ i >> 31;
    }

    private static long zigZag64(long l)
    {
        return l << 1 ^ l >> 63;
    }

    public void dumpBuffers(String s)
    {
        int i = mBuffers.size();
        int j = 0;
        for(int k = 0; k < i; k++)
            j += dumpByteString(s, (new StringBuilder()).append("{").append(k).append("} ").toString(), j, (byte[])mBuffers.get(k));

    }

    public void editRawFixed32(int i, int j)
    {
        ((byte[])mBuffers.get(i / mChunkSize))[i % mChunkSize] = (byte)j;
        ((byte[])mBuffers.get((i + 1) / mChunkSize))[(i + 1) % mChunkSize] = (byte)(j >> 8);
        ((byte[])mBuffers.get((i + 2) / mChunkSize))[(i + 2) % mChunkSize] = (byte)(j >> 16);
        ((byte[])mBuffers.get((i + 3) / mChunkSize))[(i + 3) % mChunkSize] = (byte)(j >> 24);
    }

    public byte[] getBytes(int i)
    {
        byte abyte0[] = new byte[i];
        int j = i / mChunkSize;
        int k = 0;
        int l;
        for(l = 0; l < j; l++)
        {
            System.arraycopy((byte[])mBuffers.get(l), 0, abyte0, k, mChunkSize);
            k += mChunkSize;
        }

        i -= mChunkSize * j;
        if(i > 0)
            System.arraycopy((byte[])mBuffers.get(l), 0, abyte0, k, i);
        return abyte0;
    }

    public int getChunkCount()
    {
        return mBuffers.size();
    }

    public String getDebugString()
    {
        return (new StringBuilder()).append("EncodedBuffer( mChunkSize=").append(mChunkSize).append(" mBuffers.size=").append(mBuffers.size()).append(" mBufferCount=").append(mBufferCount).append(" mWriteIndex=").append(mWriteIndex).append(" mWriteBufIndex=").append(mWriteBufIndex).append(" mReadBufIndex=").append(mReadBufIndex).append(" mReadIndex=").append(mReadIndex).append(" mReadableSize=").append(mReadableSize).append(" mReadLimit=").append(mReadLimit).append(" )").toString();
    }

    public int getRawFixed32At(int i)
    {
        byte byte0 = ((byte[])mBuffers.get(i / mChunkSize))[i % mChunkSize];
        byte byte1 = ((byte[])mBuffers.get((i + 1) / mChunkSize))[(i + 1) % mChunkSize];
        byte byte2 = ((byte[])mBuffers.get((i + 2) / mChunkSize))[(i + 2) % mChunkSize];
        return (((byte[])mBuffers.get((i + 3) / mChunkSize))[(i + 3) % mChunkSize] & 0xff) << 24 | (byte0 & 0xff | (byte1 & 0xff) << 8 | (byte2 & 0xff) << 16);
    }

    public int getReadPos()
    {
        return mReadBufIndex * mChunkSize + mReadIndex;
    }

    public int getReadableSize()
    {
        return mReadableSize;
    }

    public int getWriteBufIndex()
    {
        return mWriteBufIndex;
    }

    public int getWriteIndex()
    {
        return mWriteIndex;
    }

    public int getWritePos()
    {
        return mWriteBufIndex * mChunkSize + mWriteIndex;
    }

    public byte readRawByte()
    {
        if(mReadBufIndex > mBufferCount || mReadBufIndex == mBufferCount - 1 && mReadIndex >= mReadLimit)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("Trying to read too much data mReadBufIndex=").append(mReadBufIndex).append(" mBufferCount=").append(mBufferCount).append(" mReadIndex=").append(mReadIndex).append(" mReadLimit=").append(mReadLimit).toString());
        if(mReadIndex >= mChunkSize)
        {
            mReadBufIndex = mReadBufIndex + 1;
            mReadBuffer = (byte[])mBuffers.get(mReadBufIndex);
            mReadIndex = 0;
        }
        byte abyte0[] = mReadBuffer;
        int i = mReadIndex;
        mReadIndex = i + 1;
        return abyte0[i];
    }

    public int readRawFixed32()
    {
        return readRawByte() & 0xff | (readRawByte() & 0xff) << 8 | (readRawByte() & 0xff) << 16 | (readRawByte() & 0xff) << 24;
    }

    public long readRawUnsigned()
    {
        int i = 0;
        long l = 0L;
        int j;
        do
        {
            j = readRawByte();
            l |= (long)(j & 0x7f) << i;
            if((j & 0x80) == 0)
                return l;
            j = i + 7;
            i = j;
        } while(j <= 64);
        throw new ProtoParseException((new StringBuilder()).append("Varint too long -- ").append(getDebugString()).toString());
    }

    public void rewindRead()
    {
        mReadBuffer = (byte[])mBuffers.get(0);
        mReadBufIndex = 0;
        mReadIndex = 0;
    }

    public void rewindWriteTo(int i)
    {
        if(i > getWritePos())
            throw new RuntimeException((new StringBuilder()).append("rewindWriteTo only can go backwards").append(i).toString());
        mWriteBufIndex = i / mChunkSize;
        mWriteIndex = i % mChunkSize;
        if(mWriteIndex == 0 && mWriteBufIndex != 0)
        {
            mWriteIndex = mChunkSize;
            mWriteBufIndex = mWriteBufIndex - 1;
        }
        mWriteBuffer = (byte[])mBuffers.get(mWriteBufIndex);
    }

    public void skipRead(int i)
    {
        if(i < 0)
            throw new RuntimeException((new StringBuilder()).append("skipRead with negative amount=").append(i).toString());
        if(i == 0)
            return;
        if(i <= mChunkSize - mReadIndex)
        {
            mReadIndex = mReadIndex + i;
        } else
        {
            i -= mChunkSize - mReadIndex;
            mReadIndex = i % mChunkSize;
            if(mReadIndex == 0)
            {
                mReadIndex = mChunkSize;
                mReadBufIndex = mReadBufIndex + i / mChunkSize;
            } else
            {
                mReadBufIndex = mReadBufIndex + (i / mChunkSize + 1);
            }
            mReadBuffer = (byte[])mBuffers.get(mReadBufIndex);
        }
    }

    public void startEditing()
    {
        mReadableSize = mWriteBufIndex * mChunkSize + mWriteIndex;
        mReadLimit = mWriteIndex;
        mWriteBuffer = (byte[])mBuffers.get(0);
        mWriteIndex = 0;
        mWriteBufIndex = 0;
        mReadBuffer = mWriteBuffer;
        mReadBufIndex = 0;
        mReadIndex = 0;
    }

    public void writeFromThisBuffer(int i, int j)
    {
        if(mReadLimit < 0)
            throw new IllegalStateException("writeFromThisBuffer before startEditing");
        if(i < getWritePos())
            throw new IllegalArgumentException((new StringBuilder()).append("Can only move forward in the buffer -- srcOffset=").append(i).append(" size=").append(j).append(" ").append(getDebugString()).toString());
        if(i + j > mReadableSize)
            throw new IllegalArgumentException((new StringBuilder()).append("Trying to move more data than there is -- srcOffset=").append(i).append(" size=").append(j).append(" ").append(getDebugString()).toString());
        if(j == 0)
            return;
        if(i == mWriteBufIndex * mChunkSize + mWriteIndex)
        {
            if(j <= mChunkSize - mWriteIndex)
            {
                mWriteIndex = mWriteIndex + j;
            } else
            {
                i = j - (mChunkSize - mWriteIndex);
                mWriteIndex = i % mChunkSize;
                if(mWriteIndex == 0)
                {
                    mWriteIndex = mChunkSize;
                    mWriteBufIndex = mWriteBufIndex + i / mChunkSize;
                } else
                {
                    mWriteBufIndex = mWriteBufIndex + (i / mChunkSize + 1);
                }
                mWriteBuffer = (byte[])mBuffers.get(mWriteBufIndex);
            }
        } else
        {
            int k = i / mChunkSize;
            byte abyte0[] = (byte[])mBuffers.get(k);
            i %= mChunkSize;
            while(j > 0) 
            {
                if(mWriteIndex >= mChunkSize)
                    nextWriteBuffer();
                int l = k;
                int i1 = i;
                if(i >= mChunkSize)
                {
                    l = k + 1;
                    abyte0 = (byte[])mBuffers.get(l);
                    i1 = 0;
                }
                k = Math.min(j, Math.min(mChunkSize - mWriteIndex, mChunkSize - i1));
                System.arraycopy(abyte0, i1, mWriteBuffer, mWriteIndex, k);
                mWriteIndex = mWriteIndex + k;
                i = i1 + k;
                j -= k;
                k = l;
            }
        }
    }

    public void writeRawBuffer(byte abyte0[])
    {
        if(abyte0 != null && abyte0.length > 0)
            writeRawBuffer(abyte0, 0, abyte0.length);
    }

    public void writeRawBuffer(byte abyte0[], int i, int j)
    {
        if(abyte0 == null)
            return;
        int k;
        int l;
        int i1;
        if(j < mChunkSize - mWriteIndex)
            k = j;
        else
            k = mChunkSize - mWriteIndex;
        l = i;
        i1 = j;
        if(k > 0)
        {
            System.arraycopy(abyte0, i, mWriteBuffer, mWriteIndex, k);
            mWriteIndex = mWriteIndex + k;
            i1 = j - k;
            l = i + k;
        }
        while(i1 > 0) 
        {
            nextWriteBuffer();
            if(i1 < mChunkSize)
                i = i1;
            else
                i = mChunkSize;
            System.arraycopy(abyte0, l, mWriteBuffer, mWriteIndex, i);
            mWriteIndex = mWriteIndex + i;
            i1 -= i;
            l += i;
        }
    }

    public void writeRawByte(byte byte0)
    {
        if(mWriteIndex >= mChunkSize)
            nextWriteBuffer();
        byte abyte0[] = mWriteBuffer;
        int i = mWriteIndex;
        mWriteIndex = i + 1;
        abyte0[i] = byte0;
    }

    public void writeRawFixed32(int i)
    {
        writeRawByte((byte)i);
        writeRawByte((byte)(i >> 8));
        writeRawByte((byte)(i >> 16));
        writeRawByte((byte)(i >> 24));
    }

    public void writeRawFixed64(long l)
    {
        writeRawByte((byte)(int)l);
        writeRawByte((byte)(int)(l >> 8));
        writeRawByte((byte)(int)(l >> 16));
        writeRawByte((byte)(int)(l >> 24));
        writeRawByte((byte)(int)(l >> 32));
        writeRawByte((byte)(int)(l >> 40));
        writeRawByte((byte)(int)(l >> 48));
        writeRawByte((byte)(int)(l >> 56));
    }

    public void writeRawVarint32(int i)
    {
        do
        {
            if((i & 0xffffff80) == 0)
            {
                writeRawByte((byte)i);
                return;
            }
            writeRawByte((byte)(i & 0x7f | 0x80));
            i >>>= 7;
        } while(true);
    }

    public void writeRawVarint64(long l)
    {
        do
        {
            if((-128L & l) == 0L)
            {
                writeRawByte((byte)(int)l);
                return;
            }
            writeRawByte((byte)(int)(127L & l | 128L));
            l >>>= 7;
        } while(true);
    }

    public void writeRawZigZag32(int i)
    {
        writeRawVarint32(zigZag32(i));
    }

    public void writeRawZigZag64(long l)
    {
        writeRawVarint64(zigZag64(l));
    }

    private static final String TAG = "EncodedBuffer";
    private int mBufferCount;
    private final ArrayList mBuffers;
    private final int mChunkSize;
    private int mReadBufIndex;
    private byte mReadBuffer[];
    private int mReadIndex;
    private int mReadLimit;
    private int mReadableSize;
    private int mWriteBufIndex;
    private byte mWriteBuffer[];
    private int mWriteIndex;
}

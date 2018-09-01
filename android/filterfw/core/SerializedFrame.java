// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import android.filterfw.format.ObjectFormat;
import android.graphics.Bitmap;
import java.io.*;
import java.nio.ByteBuffer;

// Referenced classes of package android.filterfw.core:
//            Frame, FrameFormat, FrameManager

public class SerializedFrame extends Frame
{
    private class DirectByteInputStream extends InputStream
    {

        public final int available()
        {
            return mSize - mPos;
        }

        public final int read()
        {
            int i;
            if(mPos < mSize)
            {
                byte abyte0[] = mBuffer;
                i = mPos;
                mPos = i + 1;
                i = abyte0[i] & 0xff;
            } else
            {
                i = -1;
            }
            return i;
        }

        public final int read(byte abyte0[], int i, int j)
        {
            if(mPos >= mSize)
                return -1;
            int k = j;
            if(mPos + j > mSize)
                k = mSize - mPos;
            System.arraycopy(mBuffer, mPos, abyte0, i, k);
            mPos = mPos + k;
            return k;
        }

        public final long skip(long l)
        {
            long l1 = l;
            if((long)mPos + l > (long)mSize)
                l1 = mSize - mPos;
            if(l1 < 0L)
            {
                return 0L;
            } else
            {
                mPos = (int)((long)mPos + l1);
                return l1;
            }
        }

        private byte mBuffer[];
        private int mPos;
        private int mSize;
        final SerializedFrame this$0;

        public DirectByteInputStream(byte abyte0[], int i)
        {
            this$0 = SerializedFrame.this;
            super();
            mPos = 0;
            mBuffer = abyte0;
            mSize = i;
        }
    }

    private class DirectByteOutputStream extends OutputStream
    {

        private final void ensureFit(int i)
        {
            if(mOffset + i > mBuffer.length)
            {
                byte abyte0[] = mBuffer;
                mBuffer = new byte[Math.max(mOffset + i, mBuffer.length * 2)];
                System.arraycopy(abyte0, 0, mBuffer, 0, mOffset);
            }
        }

        public byte[] getByteArray()
        {
            return mBuffer;
        }

        public final DirectByteInputStream getInputStream()
        {
            return new DirectByteInputStream(mBuffer, mOffset);
        }

        public final int getSize()
        {
            return mOffset;
        }

        public final void markHeaderEnd()
        {
            mDataOffset = mOffset;
        }

        public final void reset()
        {
            mOffset = mDataOffset;
        }

        public final void write(int i)
        {
            ensureFit(1);
            byte abyte0[] = mBuffer;
            int j = mOffset;
            mOffset = j + 1;
            abyte0[j] = (byte)i;
        }

        public final void write(byte abyte0[])
        {
            write(abyte0, 0, abyte0.length);
        }

        public final void write(byte abyte0[], int i, int j)
        {
            ensureFit(j);
            System.arraycopy(abyte0, i, mBuffer, mOffset, j);
            mOffset = mOffset + j;
        }

        private byte mBuffer[];
        private int mDataOffset;
        private int mOffset;
        final SerializedFrame this$0;

        public DirectByteOutputStream(int i)
        {
            this$0 = SerializedFrame.this;
            super();
            mBuffer = null;
            mOffset = 0;
            mDataOffset = 0;
            mBuffer = new byte[i];
        }
    }


    SerializedFrame(FrameFormat frameformat, FrameManager framemanager)
    {
        super(frameformat, framemanager);
        setReusable(false);
        try
        {
            frameformat = JVM INSTR new #9   <Class SerializedFrame$DirectByteOutputStream>;
            frameformat.this. DirectByteOutputStream(64);
            mByteOutputStream = frameformat;
            frameformat = JVM INSTR new #34  <Class ObjectOutputStream>;
            frameformat.ObjectOutputStream(mByteOutputStream);
            mObjectOut = frameformat;
            mByteOutputStream.markHeaderEnd();
            return;
        }
        // Misplaced declaration of an exception variable
        catch(FrameFormat frameformat)
        {
            throw new RuntimeException("Could not create serialization streams for SerializedFrame!", frameformat);
        }
    }

    private final Object deserializeObjectValue()
    {
        Object obj;
        try
        {
            obj = mByteOutputStream.getInputStream();
            ObjectInputStream objectinputstream = JVM INSTR new #61  <Class ObjectInputStream>;
            objectinputstream.ObjectInputStream(((InputStream) (obj)));
            obj = objectinputstream.readObject();
        }
        catch(IOException ioexception)
        {
            throw new RuntimeException((new StringBuilder()).append("Could not deserialize object in ").append(this).append("!").toString(), ioexception);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new RuntimeException((new StringBuilder()).append("Unable to deserialize object of unknown class in ").append(this).append("!").toString(), classnotfoundexception);
        }
        return obj;
    }

    private final void serializeObjectValue(Object obj)
    {
        try
        {
            mByteOutputStream.reset();
            mObjectOut.writeObject(obj);
            mObjectOut.flush();
            mObjectOut.close();
            return;
        }
        catch(IOException ioexception)
        {
            throw new RuntimeException((new StringBuilder()).append("Could not serialize object ").append(obj).append(" in ").append(this).append("!").toString(), ioexception);
        }
    }

    static SerializedFrame wrapObject(Object obj, FrameManager framemanager)
    {
        framemanager = new SerializedFrame(ObjectFormat.fromObject(obj, 1), framemanager);
        framemanager.setObjectValue(obj);
        return framemanager;
    }

    public Bitmap getBitmap()
    {
        Object obj = deserializeObjectValue();
        if(obj instanceof Bitmap)
            obj = (Bitmap)obj;
        else
            obj = null;
        return ((Bitmap) (obj));
    }

    public ByteBuffer getData()
    {
        Object obj = deserializeObjectValue();
        if(obj instanceof ByteBuffer)
            obj = (ByteBuffer)obj;
        else
            obj = null;
        return ((ByteBuffer) (obj));
    }

    public float[] getFloats()
    {
        Object obj = deserializeObjectValue();
        float af[];
        if(obj instanceof float[])
            af = (float[])obj;
        else
            af = null;
        return af;
    }

    public int[] getInts()
    {
        Object obj = deserializeObjectValue();
        int ai[];
        if(obj instanceof int[])
            ai = (int[])obj;
        else
            ai = null;
        return ai;
    }

    public Object getObjectValue()
    {
        return deserializeObjectValue();
    }

    protected boolean hasNativeAllocation()
    {
        return false;
    }

    protected void releaseNativeAllocation()
    {
    }

    public void setBitmap(Bitmap bitmap)
    {
        assertFrameMutable();
        setGenericObjectValue(bitmap);
    }

    public void setData(ByteBuffer bytebuffer, int i, int j)
    {
        assertFrameMutable();
        setGenericObjectValue(ByteBuffer.wrap(bytebuffer.array(), i, j));
    }

    public void setFloats(float af[])
    {
        assertFrameMutable();
        setGenericObjectValue(af);
    }

    protected void setGenericObjectValue(Object obj)
    {
        serializeObjectValue(obj);
    }

    public void setInts(int ai[])
    {
        assertFrameMutable();
        setGenericObjectValue(ai);
    }

    public String toString()
    {
        return (new StringBuilder()).append("SerializedFrame (").append(getFormat()).append(")").toString();
    }

    private static final int INITIAL_CAPACITY = 64;
    private DirectByteOutputStream mByteOutputStream;
    private ObjectOutputStream mObjectOut;
}

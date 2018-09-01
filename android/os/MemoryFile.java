// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.system.ErrnoException;
import java.io.*;
import java.nio.ByteBuffer;

// Referenced classes of package android.os:
//            SharedMemory

public class MemoryFile
{
    private class MemoryInputStream extends InputStream
    {

        public int available()
            throws IOException
        {
            if(mOffset >= MemoryFile._2D_get0(MemoryFile.this).getSize())
                return 0;
            else
                return MemoryFile._2D_get0(MemoryFile.this).getSize() - mOffset;
        }

        public void mark(int i)
        {
            mMark = mOffset;
        }

        public boolean markSupported()
        {
            return true;
        }

        public int read()
            throws IOException
        {
            if(mSingleByte == null)
                mSingleByte = new byte[1];
            if(read(mSingleByte, 0, 1) != 1)
                return -1;
            else
                return mSingleByte[0];
        }

        public int read(byte abyte0[], int i, int j)
            throws IOException
        {
            while(i < 0 || j < 0 || i + j > abyte0.length) 
                throw new IndexOutOfBoundsException();
            j = Math.min(j, available());
            if(j < 1)
                return -1;
            i = readBytes(abyte0, mOffset, i, j);
            if(i > 0)
                mOffset = mOffset + i;
            return i;
        }

        public void reset()
            throws IOException
        {
            mOffset = mMark;
        }

        public long skip(long l)
            throws IOException
        {
            long l1 = l;
            if((long)mOffset + l > (long)MemoryFile._2D_get0(MemoryFile.this).getSize())
                l1 = MemoryFile._2D_get0(MemoryFile.this).getSize() - mOffset;
            mOffset = (int)((long)mOffset + l1);
            return l1;
        }

        private int mMark;
        private int mOffset;
        private byte mSingleByte[];
        final MemoryFile this$0;

        private MemoryInputStream()
        {
            this$0 = MemoryFile.this;
            super();
            mMark = 0;
            mOffset = 0;
        }

        MemoryInputStream(MemoryInputStream memoryinputstream)
        {
            this();
        }
    }

    private class MemoryOutputStream extends OutputStream
    {

        public void write(int i)
            throws IOException
        {
            if(mSingleByte == null)
                mSingleByte = new byte[1];
            mSingleByte[0] = (byte)i;
            write(mSingleByte, 0, 1);
        }

        public void write(byte abyte0[], int i, int j)
            throws IOException
        {
            writeBytes(abyte0, i, mOffset, j);
            mOffset = mOffset + j;
        }

        private int mOffset;
        private byte mSingleByte[];
        final MemoryFile this$0;

        private MemoryOutputStream()
        {
            this$0 = MemoryFile.this;
            super();
            mOffset = 0;
        }

        MemoryOutputStream(MemoryOutputStream memoryoutputstream)
        {
            this();
        }
    }


    static SharedMemory _2D_get0(MemoryFile memoryfile)
    {
        return memoryfile.mSharedMemory;
    }

    public MemoryFile(String s, int i)
        throws IOException
    {
        mAllowPurging = false;
        mSharedMemory = SharedMemory.create(s, i);
        mMapping = mSharedMemory.mapReadWrite();
_L1:
        return;
        s;
        s.rethrowAsIOException();
          goto _L1
    }

    private void beginAccess()
        throws IOException
    {
        checkActive();
        if(mAllowPurging && native_pin(mSharedMemory.getFileDescriptor(), true))
            throw new IOException("MemoryFile has been purged");
        else
            return;
    }

    private void checkActive()
        throws IOException
    {
        if(mMapping == null)
            throw new IOException("MemoryFile has been deactivated");
        else
            return;
    }

    private void endAccess()
        throws IOException
    {
        if(mAllowPurging)
            native_pin(mSharedMemory.getFileDescriptor(), false);
    }

    public static int getSize(FileDescriptor filedescriptor)
        throws IOException
    {
        return native_get_size(filedescriptor);
    }

    private static native int native_get_size(FileDescriptor filedescriptor)
        throws IOException;

    private static native boolean native_pin(FileDescriptor filedescriptor, boolean flag)
        throws IOException;

    public boolean allowPurging(boolean flag)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag1 = mAllowPurging;
        if(flag1 == flag)
            break MISSING_BLOCK_LABEL_31;
        native_pin(mSharedMemory.getFileDescriptor(), flag ^ true);
        mAllowPurging = flag;
        this;
        JVM INSTR monitorexit ;
        return flag1;
        Exception exception;
        exception;
        throw exception;
    }

    public void close()
    {
        deactivate();
        mSharedMemory.close();
    }

    void deactivate()
    {
        if(mMapping != null)
        {
            SharedMemory.unmap(mMapping);
            mMapping = null;
        }
    }

    public FileDescriptor getFileDescriptor()
        throws IOException
    {
        return mSharedMemory.getFileDescriptor();
    }

    public InputStream getInputStream()
    {
        return new MemoryInputStream(null);
    }

    public OutputStream getOutputStream()
    {
        return new MemoryOutputStream(null);
    }

    public boolean isPurgingAllowed()
    {
        return mAllowPurging;
    }

    public int length()
    {
        return mSharedMemory.getSize();
    }

    public int readBytes(byte abyte0[], int i, int j, int k)
        throws IOException
    {
        beginAccess();
        mMapping.position(i);
        mMapping.get(abyte0, j, k);
        endAccess();
        return k;
        abyte0;
        endAccess();
        throw abyte0;
    }

    public void writeBytes(byte abyte0[], int i, int j, int k)
        throws IOException
    {
        beginAccess();
        mMapping.position(j);
        mMapping.put(abyte0, i, k);
        endAccess();
        return;
        abyte0;
        endAccess();
        throw abyte0;
    }

    private static String TAG = "MemoryFile";
    private boolean mAllowPurging;
    private ByteBuffer mMapping;
    private SharedMemory mSharedMemory;

}

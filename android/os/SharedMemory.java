// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.system.*;
import dalvik.system.VMRuntime;
import java.io.Closeable;
import java.io.FileDescriptor;
import java.nio.*;
import sun.misc.Cleaner;

// Referenced classes of package android.os:
//            Parcelable, Parcel

public final class SharedMemory
    implements Parcelable, Closeable
{
    private static final class Closer
        implements Runnable
    {

        public void run()
        {
            try
            {
                Os.close(mFd);
            }
            catch(ErrnoException errnoexception) { }
            mMemoryReference.release();
            mMemoryReference = null;
        }

        private FileDescriptor mFd;
        private MemoryRegistration mMemoryReference;

        private Closer(FileDescriptor filedescriptor, MemoryRegistration memoryregistration)
        {
            mFd = filedescriptor;
            mMemoryReference = memoryregistration;
        }

        Closer(FileDescriptor filedescriptor, MemoryRegistration memoryregistration, Closer closer)
        {
            this(filedescriptor, memoryregistration);
        }
    }

    private static final class MemoryRegistration
    {

        public MemoryRegistration acquire()
        {
            this;
            JVM INSTR monitorenter ;
            mReferenceCount = mReferenceCount + 1;
            this;
            JVM INSTR monitorexit ;
            return this;
            Exception exception;
            exception;
            throw exception;
        }

        public void release()
        {
            this;
            JVM INSTR monitorenter ;
            mReferenceCount = mReferenceCount - 1;
            if(mReferenceCount == 0)
                VMRuntime.getRuntime().registerNativeFree(mSize);
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private int mReferenceCount;
        private int mSize;

        private MemoryRegistration(int i)
        {
            mSize = i;
            mReferenceCount = 1;
            VMRuntime.getRuntime().registerNativeAllocation(mSize);
        }

        MemoryRegistration(int i, MemoryRegistration memoryregistration)
        {
            this(i);
        }
    }

    private static final class Unmapper
        implements Runnable
    {

        public void run()
        {
            try
            {
                Os.munmap(mAddress, mSize);
            }
            catch(ErrnoException errnoexception) { }
            mMemoryReference.release();
            mMemoryReference = null;
        }

        private long mAddress;
        private MemoryRegistration mMemoryReference;
        private int mSize;

        private Unmapper(long l, int i, MemoryRegistration memoryregistration)
        {
            mAddress = l;
            mSize = i;
            mMemoryReference = memoryregistration;
        }

        Unmapper(long l, int i, MemoryRegistration memoryregistration, Unmapper unmapper)
        {
            this(l, i, memoryregistration);
        }
    }


    private SharedMemory(FileDescriptor filedescriptor)
    {
        if(filedescriptor == null)
            throw new IllegalArgumentException("Unable to create SharedMemory from a null FileDescriptor");
        if(!filedescriptor.valid())
            throw new IllegalArgumentException("Unable to create SharedMemory from closed FileDescriptor");
        mFileDescriptor = filedescriptor;
        mSize = nGetSize(mFileDescriptor);
        if(mSize <= 0)
        {
            throw new IllegalArgumentException("FileDescriptor is not a valid ashmem fd");
        } else
        {
            mMemoryRegistration = new MemoryRegistration(mSize, null);
            mCleaner = Cleaner.create(mFileDescriptor, new Closer(mFileDescriptor, mMemoryRegistration, null));
            return;
        }
    }

    SharedMemory(FileDescriptor filedescriptor, SharedMemory sharedmemory)
    {
        this(filedescriptor);
    }

    private void checkOpen()
    {
        if(!mFileDescriptor.valid())
            throw new IllegalStateException("SharedMemory is closed");
        else
            return;
    }

    public static SharedMemory create(String s, int i)
        throws ErrnoException
    {
        if(i <= 0)
            throw new IllegalArgumentException("Size must be greater than zero");
        else
            return new SharedMemory(nCreate(s, i));
    }

    private static native FileDescriptor nCreate(String s, int i)
        throws ErrnoException;

    private static native int nGetSize(FileDescriptor filedescriptor);

    private static native int nSetProt(FileDescriptor filedescriptor, int i);

    public static void unmap(ByteBuffer bytebuffer)
    {
        if(bytebuffer instanceof DirectByteBuffer)
        {
            NioUtils.freeDirectBuffer(bytebuffer);
            return;
        } else
        {
            throw new IllegalArgumentException("ByteBuffer wasn't created by #map(int, int, int); can't unmap");
        }
    }

    private static void validateProt(int i)
    {
        if((PROT_MASK & i) != 0)
            throw new IllegalArgumentException("Invalid prot value");
        else
            return;
    }

    public void close()
    {
        if(mCleaner != null)
        {
            mCleaner.clean();
            mCleaner = null;
        }
    }

    public int describeContents()
    {
        return 1;
    }

    public int getFd()
    {
        return mFileDescriptor.getInt$();
    }

    public FileDescriptor getFileDescriptor()
    {
        return mFileDescriptor;
    }

    public int getSize()
    {
        checkOpen();
        return mSize;
    }

    public ByteBuffer map(int i, int j, int k)
        throws ErrnoException
    {
        checkOpen();
        validateProt(i);
        if(j < 0)
            throw new IllegalArgumentException("Offset must be >= 0");
        if(k <= 0)
            throw new IllegalArgumentException("Length must be > 0");
        if(j + k > mSize)
            throw new IllegalArgumentException("offset + length must not exceed getSize()");
        long l = Os.mmap(0L, k, i, OsConstants.MAP_SHARED, mFileDescriptor, j);
        boolean flag;
        Unmapper unmapper;
        if((OsConstants.PROT_WRITE & i) == 0)
            flag = true;
        else
            flag = false;
        unmapper = new Unmapper(l, k, mMemoryRegistration.acquire(), null);
        return new DirectByteBuffer(k, l, mFileDescriptor, unmapper, flag);
    }

    public ByteBuffer mapReadOnly()
        throws ErrnoException
    {
        return map(OsConstants.PROT_READ, 0, mSize);
    }

    public ByteBuffer mapReadWrite()
        throws ErrnoException
    {
        return map(OsConstants.PROT_READ | OsConstants.PROT_WRITE, 0, mSize);
    }

    public boolean setProtect(int i)
    {
        boolean flag = false;
        checkOpen();
        validateProt(i);
        if(nSetProt(mFileDescriptor, i) == 0)
            flag = true;
        return flag;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        checkOpen();
        parcel.writeFileDescriptor(mFileDescriptor);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public SharedMemory createFromParcel(Parcel parcel)
        {
            return new SharedMemory(parcel.readRawFileDescriptor(), null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SharedMemory[] newArray(int i)
        {
            return new SharedMemory[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int PROT_MASK;
    private Cleaner mCleaner;
    private final FileDescriptor mFileDescriptor;
    private final MemoryRegistration mMemoryRegistration;
    private final int mSize;

    static 
    {
        PROT_MASK = OsConstants.PROT_READ | OsConstants.PROT_WRITE | OsConstants.PROT_EXEC | OsConstants.PROT_NONE;
    }
}
